package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.mapping.CallContext;
import gnu.mapping.Future;
import gnu.mapping.Procedure;
import gnu.mapping.RunnableClosure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Quantity;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.sleep;

public class thread
  extends ModuleBody
{
  public static final ModuleMethod $Prvt$$Pcmake$Mnfuture;
  public static final thread $instance;
  static final SimpleSymbol Lit0;
  static final SimpleSymbol Lit1;
  static final SyntaxRules Lit2;
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit4 = (SimpleSymbol)new SimpleSymbol("runnable").readResolve();
  public static final Macro future;
  public static final ModuleMethod runnable;
  public static final ModuleMethod sleep;
  
  public static Future $PcMakeFuture(Procedure paramProcedure)
  {
    paramProcedure = new Future(paramProcedure);
    paramProcedure.start();
    return paramProcedure;
  }
  
  static
  {
    Lit3 = (SimpleSymbol)new SimpleSymbol("%make-future").readResolve();
    Object localObject = (SimpleSymbol)new SimpleSymbol("future").readResolve();
    Lit1 = (SimpleSymbol)localObject;
    SyntaxRule localSyntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\b\021\030\f\t\020\b\003", new Object[] { Lit3, (SimpleSymbol)new SimpleSymbol("lambda").readResolve() }, 0);
    Lit2 = new SyntaxRules(new Object[] { localObject }, new SyntaxRule[] { localSyntaxRule }, 1);
    Lit0 = (SimpleSymbol)new SimpleSymbol("sleep").readResolve();
    $instance = new thread();
    localObject = $instance;
    sleep = new ModuleMethod((ModuleBody)localObject, 1, Lit0, 4097);
    future = Macro.make(Lit1, Lit2, $instance);
    $Prvt$$Pcmake$Mnfuture = new ModuleMethod((ModuleBody)localObject, 2, Lit3, 4097);
    runnable = new ModuleMethod((ModuleBody)localObject, 3, Lit4, 4097);
    $instance.run();
  }
  
  public thread()
  {
    ModuleInfo.register(this);
  }
  
  public static RunnableClosure runnable(Procedure paramProcedure)
  {
    return new RunnableClosure(paramProcedure);
  }
  
  public static void sleep(Quantity paramQuantity)
  {
    sleep.sleep(paramQuantity);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    }
    try
    {
      paramModuleMethod = (Quantity)paramObject;
      sleep(paramModuleMethod);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (Procedure)paramObject;
        return $PcMakeFuture(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "%make-future", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (Procedure)paramObject;
        return runnable(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "runnable", 1, paramObject);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "sleep", 1, paramObject);
    }
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    default: 
      i = super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
    do
    {
      do
      {
        do
        {
          return i;
        } while (!(paramObject instanceof Procedure));
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      } while (!(paramObject instanceof Procedure));
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    } while (!(paramObject instanceof Quantity));
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\thread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */