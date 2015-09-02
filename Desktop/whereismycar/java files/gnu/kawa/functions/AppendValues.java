package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.Special;
import gnu.expr.Target;
import gnu.lists.Consumable;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;

public class AppendValues
  extends MethodProc
  implements Inlineable
{
  public static final AppendValues appendValues = new AppendValues();
  
  public AppendValues()
  {
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyAppendValues");
  }
  
  public void apply(CallContext paramCallContext)
  {
    Special localSpecial = Special.dfault;
    for (;;)
    {
      Object localObject = paramCallContext.getNextArg(localSpecial);
      if (localObject == localSpecial) {
        return;
      }
      if ((localObject instanceof Consumable)) {
        ((Consumable)localObject).consume(paramCallContext.consumer);
      } else {
        paramCallContext.writeValue(localObject);
      }
    }
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int j = arrayOfExpression.length;
    int i;
    if (((paramTarget instanceof ConsumerTarget)) || ((paramTarget instanceof IgnoreTarget))) {
      i = 0;
    }
    while (i < j)
    {
      arrayOfExpression[i].compileWithPosition(paramCompilation, paramTarget);
      i += 1;
      continue;
      ConsumerTarget.compileUsingConsumer(paramApplyExp, paramCompilation, paramTarget);
    }
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return Compilation.typeObject;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\AppendValues.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */