package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1or2;
import gnu.text.SourceMessages;
import java.util.Stack;

public class Eval
  extends Procedure1or2
{
  public static final Eval eval = new Eval();
  static final String evalFunctionName = "atEvalLevel$";
  
  static
  {
    eval.setName("eval");
  }
  
  public static Object eval(Object paramObject, Environment paramEnvironment)
    throws Throwable
  {
    CallContext localCallContext = CallContext.getInstance();
    int i = localCallContext.startFromContext();
    try
    {
      eval(paramObject, paramEnvironment, localCallContext);
      paramObject = localCallContext.getFromContext(i);
      return paramObject;
    }
    catch (Throwable paramObject)
    {
      localCallContext.cleanupFromContext(i);
      throw ((Throwable)paramObject);
    }
  }
  
  public static void eval(Object paramObject, Environment paramEnvironment, CallContext paramCallContext)
    throws Throwable
  {
    if ((paramObject instanceof PairWithPosition)) {
      paramObject = new PairWithPosition((PairWithPosition)paramObject, paramObject, LList.Empty);
    }
    for (;;)
    {
      evalBody(paramObject, paramEnvironment, new SourceMessages(), paramCallContext);
      return;
      paramObject = new PairWithPosition(paramObject, LList.Empty);
      ((PairWithPosition)paramObject).setFile("<eval>");
    }
  }
  
  public static Object evalBody(Object paramObject, Environment paramEnvironment, SourceMessages paramSourceMessages)
    throws Throwable
  {
    CallContext localCallContext = CallContext.getInstance();
    int i = localCallContext.startFromContext();
    try
    {
      evalBody(paramObject, paramEnvironment, paramSourceMessages, localCallContext);
      paramObject = localCallContext.getFromContext(i);
      return paramObject;
    }
    catch (Throwable paramObject)
    {
      localCallContext.cleanupFromContext(i);
      throw ((Throwable)paramObject);
    }
  }
  
  public static void evalBody(Object paramObject, Environment paramEnvironment, SourceMessages paramSourceMessages, CallContext paramCallContext)
    throws Throwable
  {
    Object localObject = Language.getDefaultLanguage();
    Environment localEnvironment = Environment.getCurrent();
    if (paramEnvironment != localEnvironment) {}
    try
    {
      Environment.setCurrent(paramEnvironment);
      localObject = new Translator((Language)localObject, paramSourceMessages, NameLookup.getInstance(paramEnvironment, (Language)localObject));
      ((Translator)localObject).immediate = true;
      ((Translator)localObject).setState(3);
      ((Translator)localObject).setSharedModuleDefs(true);
      ModuleExp localModuleExp = ((Translator)localObject).pushNewModule((String)null);
      Compilation localCompilation = Compilation.setSaveCurrent((Compilation)localObject);
      int i;
      if (paramEnvironment == localEnvironment) {
        return;
      }
    }
    finally
    {
      try
      {
        i = ((Translator)localObject).formStack.size();
        ((Translator)localObject).scanBody(paramObject, localModuleExp, false);
        ((Translator)localObject).firstForm = i;
        ((Translator)localObject).finishModule(localModuleExp);
        Compilation.restoreCurrent(localCompilation);
        if ((paramObject instanceof PairWithPosition)) {
          localModuleExp.setFile(((PairWithPosition)paramObject).getFileName());
        }
        paramObject = new StringBuilder().append("atEvalLevel$");
        i = ModuleExp.interactiveCounter + 1;
        ModuleExp.interactiveCounter = i;
        localModuleExp.setName(i);
        ModuleExp.evalModule(paramEnvironment, paramCallContext, (Compilation)localObject, null, null);
        if (!paramSourceMessages.seenErrors()) {
          break label242;
        }
        throw new RuntimeException("invalid syntax in eval form:\n" + paramSourceMessages.toString(20));
      }
      finally
      {
        Compilation.restoreCurrent(localCompilation);
      }
      paramObject = finally;
      if (paramEnvironment != localEnvironment) {
        Environment.setCurrent(localEnvironment);
      }
    }
    label242:
    Environment.setCurrent(localEnvironment);
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    Procedure.checkArgCount(this, paramCallContext.count);
    Object localObject = paramCallContext.getNextArg();
    Environment localEnvironment2 = (Environment)paramCallContext.getNextArg(null);
    Environment localEnvironment1 = localEnvironment2;
    if (localEnvironment2 == null) {
      localEnvironment1 = Environment.user();
    }
    paramCallContext.lastArg();
    eval(localObject, localEnvironment1, paramCallContext);
  }
  
  public Object apply1(Object paramObject)
    throws Throwable
  {
    return eval(paramObject, Environment.user());
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
    throws Throwable
  {
    return eval(paramObject1, (Environment)paramObject2);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\Eval.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */