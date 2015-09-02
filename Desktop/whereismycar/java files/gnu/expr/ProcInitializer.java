package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.LocalVarsAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.mapping.Environment;
import gnu.mapping.PropertySet;
import gnu.mapping.Symbol;

public class ProcInitializer
  extends Initializer
{
  LambdaExp proc;
  
  public ProcInitializer(LambdaExp paramLambdaExp, Compilation paramCompilation, Field paramField)
  {
    this.field = paramField;
    this.proc = paramLambdaExp;
    if (paramField.getStaticFlag()) {}
    for (paramLambdaExp = paramCompilation.getModule(); ((paramLambdaExp instanceof ModuleExp)) && (paramCompilation.isStatic()); paramLambdaExp = paramLambdaExp.getOwningLambda())
    {
      this.next = paramCompilation.clinitChain;
      paramCompilation.clinitChain = this;
      return;
    }
    this.next = paramLambdaExp.initChain;
    paramLambdaExp.initChain = this;
  }
  
  public static void emitLoadModuleMethod(LambdaExp paramLambdaExp, Compilation paramCompilation)
  {
    Object localObject3 = paramLambdaExp.nameDecl;
    Object localObject1;
    CodeAttr localCodeAttr;
    Object localObject2;
    label61:
    label129:
    label150:
    label178:
    int i;
    if (localObject3 == null)
    {
      localObject1 = paramLambdaExp.getName();
      localCodeAttr = null;
      localObject2 = localCodeAttr;
      if (paramCompilation.immediate)
      {
        localObject2 = localCodeAttr;
        if (localObject1 != null)
        {
          localObject2 = localCodeAttr;
          if (localObject3 != null)
          {
            localObject3 = Environment.getCurrent();
            if (!(localObject1 instanceof Symbol)) {
              break label353;
            }
            localObject2 = (Symbol)localObject1;
            localObject3 = ((Environment)localObject3).get((Symbol)localObject2, paramCompilation.getLanguage().getEnvPropertyFor(paramLambdaExp.nameDecl), null);
            localObject2 = localCodeAttr;
            if ((localObject3 instanceof ModuleMethod)) {
              localObject2 = (ModuleMethod)localObject3;
            }
          }
        }
      }
      localCodeAttr = paramCompilation.getCode();
      localObject3 = Compilation.typeModuleMethod;
      if (localObject2 != null) {
        break label369;
      }
      localCodeAttr.emitNew((ClassType)localObject3);
      localCodeAttr.emitDup(1);
      localObject2 = "<init>";
      localObject3 = ((ClassType)localObject3).getDeclaredMethod((String)localObject2, 4);
      if (!paramLambdaExp.getNeedsClosureEnv()) {
        break label385;
      }
      localObject2 = paramLambdaExp.getOwningLambda();
      if ((!(localObject2 instanceof ClassExp)) || (((LambdaExp)localObject2).staticLinkField == null)) {
        break label393;
      }
      localCodeAttr.emitLoad(localCodeAttr.getCurrentScope().getVariable(1));
      localCodeAttr.emitPushInt(paramLambdaExp.getSelectorValue(paramCompilation));
      paramCompilation.compileConstant(localObject1, Target.pushObject);
      int j = paramLambdaExp.min_args;
      if (paramLambdaExp.keywords != null) {
        break label583;
      }
      i = paramLambdaExp.max_args;
      label215:
      localCodeAttr.emitPushInt(i << 12 | j);
      localCodeAttr.emitInvoke((Method)localObject3);
      if (paramLambdaExp.properties == null) {
        return;
      }
      j = paramLambdaExp.properties.length;
      i = 0;
      label252:
      if (i >= j) {
        return;
      }
      localObject2 = paramLambdaExp.properties[i];
      if ((localObject2 != null) && (localObject2 != PropertySet.nameKey))
      {
        localObject1 = paramLambdaExp.properties[(i + 1)];
        localCodeAttr.emitDup(1);
        paramCompilation.compileConstant(localObject2);
        localObject2 = Target.pushObject;
        if (!(localObject1 instanceof Expression)) {
          break label589;
        }
        ((Expression)localObject1).compile(paramCompilation, (Target)localObject2);
      }
    }
    for (;;)
    {
      localCodeAttr.emitInvokeVirtual(ClassType.make("gnu.mapping.PropertySet").getDeclaredMethod("setProperty", 2));
      i += 2;
      break label252;
      localObject1 = ((Declaration)localObject3).getSymbol();
      break;
      label353:
      localObject2 = Symbol.make("", localObject1.toString().intern());
      break label61;
      label369:
      paramCompilation.compileConstant(localObject2, Target.pushValue((Type)localObject3));
      localObject2 = "init";
      break label129;
      label385:
      localObject2 = paramCompilation.getModule();
      break label150;
      label393:
      if ((!(localObject2 instanceof ModuleExp)) || ((paramCompilation.moduleClass == paramCompilation.mainClass) && (!paramCompilation.method.getStaticFlag())))
      {
        localCodeAttr.emitPushThis();
        break label178;
      }
      if (paramCompilation.moduleInstanceVar == null)
      {
        paramCompilation.moduleInstanceVar = localCodeAttr.locals.current_scope.addVariable(localCodeAttr, paramCompilation.moduleClass, "$instance");
        if ((paramCompilation.moduleClass == paramCompilation.mainClass) || (paramCompilation.isStatic())) {
          break label571;
        }
        localCodeAttr.emitNew(paramCompilation.moduleClass);
        localCodeAttr.emitDup(paramCompilation.moduleClass);
        localCodeAttr.emitInvokeSpecial(paramCompilation.moduleClass.constructor);
        paramCompilation.moduleInstanceMainField = paramCompilation.moduleClass.addField("$main", paramCompilation.mainClass, 0);
        localCodeAttr.emitDup(paramCompilation.moduleClass);
        localCodeAttr.emitPushThis();
        localCodeAttr.emitPutField(paramCompilation.moduleInstanceMainField);
      }
      for (;;)
      {
        localCodeAttr.emitStore(paramCompilation.moduleInstanceVar);
        localCodeAttr.emitLoad(paramCompilation.moduleInstanceVar);
        break;
        label571:
        localCodeAttr.emitGetStatic(paramCompilation.moduleInstanceMainField);
      }
      label583:
      i = -1;
      break label215;
      label589:
      paramCompilation.compileConstant(localObject1, (Target)localObject2);
    }
  }
  
  public void emit(Compilation paramCompilation)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    if (!this.field.getStaticFlag()) {
      localCodeAttr.emitPushThis();
    }
    emitLoadModuleMethod(this.proc, paramCompilation);
    if (this.field.getStaticFlag())
    {
      localCodeAttr.emitPutStatic(this.field);
      return;
    }
    localCodeAttr.emitPutField(this.field);
  }
  
  public void reportError(String paramString, Compilation paramCompilation)
  {
    String str1 = paramCompilation.getFileName();
    int i = paramCompilation.getLineNumber();
    int j = paramCompilation.getColumnNumber();
    paramCompilation.setLocation(this.proc);
    String str2 = this.proc.getName();
    paramString = new StringBuffer(paramString);
    if (str2 == null) {
      paramString.append("unnamed procedure");
    }
    for (;;)
    {
      paramCompilation.error('e', paramString.toString());
      paramCompilation.setLine(str1, i, j);
      return;
      paramString.append("procedure ");
      paramString.append(str2);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ProcInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */