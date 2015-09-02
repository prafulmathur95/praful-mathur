package gnu.kawa.reflect;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;

public class TypeSwitch
  extends MethodProc
  implements Inlineable
{
  public static final TypeSwitch typeSwitch = new TypeSwitch("typeswitch");
  
  public TypeSwitch(String paramString)
  {
    setName(paramString);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplyTypeSwitch");
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    Object[] arrayOfObject = paramCallContext.getArgs();
    Object localObject = arrayOfObject[0];
    int j = arrayOfObject.length - 1;
    int i = 1;
    while (i < j)
    {
      if (((MethodProc)arrayOfObject[i]).match1(localObject, paramCallContext) >= 0) {
        return;
      }
      i += 1;
    }
    ((Procedure)arrayOfObject[j]).check1(localObject, paramCallContext);
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    CodeAttr localCodeAttr = paramCompilation.getCode();
    localCodeAttr.pushScope();
    Variable localVariable = localCodeAttr.addLocal(Type.pointer_type);
    arrayOfExpression[0].compile(paramCompilation, Target.pushObject);
    localCodeAttr.emitStore(localVariable);
    int i = 1;
    if (i < arrayOfExpression.length)
    {
      if (i > 1) {
        localCodeAttr.emitElse();
      }
      int j = i + 1;
      paramApplyExp = arrayOfExpression[i];
      if ((paramApplyExp instanceof LambdaExp))
      {
        LambdaExp localLambdaExp = (LambdaExp)paramApplyExp;
        paramApplyExp = localLambdaExp.firstDecl();
        Type localType = paramApplyExp.getType();
        if (!paramApplyExp.getCanRead())
        {
          paramApplyExp = null;
          label114:
          if (!(localType instanceof TypeValue)) {
            break label169;
          }
          ((TypeValue)localType).emitTestIf(localVariable, paramApplyExp, paramCompilation);
        }
        for (;;)
        {
          localLambdaExp.allocChildClasses(paramCompilation);
          localLambdaExp.body.compileWithPosition(paramCompilation, paramTarget);
          i = j;
          break;
          paramApplyExp.allocateVariable(localCodeAttr);
          break label114;
          label169:
          if (j < arrayOfExpression.length)
          {
            localCodeAttr.emitLoad(localVariable);
            localType.emitIsInstance(localCodeAttr);
            localCodeAttr.emitIfIntNotZero();
          }
          if (paramApplyExp != null)
          {
            localCodeAttr.emitLoad(localVariable);
            paramApplyExp.compileStore(paramCompilation);
          }
        }
      }
      throw new Error("not implemented: typeswitch arg not LambdaExp");
    }
    i = arrayOfExpression.length - 2;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      localCodeAttr.emitFi();
    }
    localCodeAttr.popScope();
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return Type.pointer_type;
  }
  
  public int numArgs()
  {
    return 61442;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\TypeSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */