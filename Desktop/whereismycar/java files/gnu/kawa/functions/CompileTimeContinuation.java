package gnu.kawa.functions;

import gnu.bytecode.ExitableBlock;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.ProcedureN;

class CompileTimeContinuation
  extends ProcedureN
  implements Inlineable
{
  Target blockTarget;
  ExitableBlock exitableBlock;
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    throw new Error("internal error");
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    paramCompilation.getCode();
    paramApplyExp = paramApplyExp.getArgs();
    int j = paramApplyExp.length;
    int i;
    if (((this.blockTarget instanceof IgnoreTarget)) || ((this.blockTarget instanceof ConsumerTarget)))
    {
      i = 1;
      if (i == 0) {
        break label90;
      }
    }
    for (;;)
    {
      if ((i == 0) && (j != 1)) {
        break label98;
      }
      i = 0;
      while (i < j)
      {
        paramApplyExp[i].compileWithPosition(paramCompilation, this.blockTarget);
        i += 1;
      }
      i = 0;
      break;
      label90:
      paramTarget.getType();
    }
    label98:
    paramTarget = AppendValues.appendValues;
    paramTarget.compile(new ApplyExp(paramTarget, paramApplyExp), paramCompilation, this.blockTarget);
    this.exitableBlock.exit();
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return Type.neverReturnsType;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\CompileTimeContinuation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */