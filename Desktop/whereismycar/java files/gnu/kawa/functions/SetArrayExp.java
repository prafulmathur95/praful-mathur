package gnu.kawa.functions;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.kawa.reflect.ArraySet;
import gnu.kawa.reflect.Invoke;

class SetArrayExp
  extends ApplyExp
{
  public static final ClassType typeSetArray = ClassType.make("gnu.kawa.functions.SetArray");
  Type elementType;
  
  public SetArrayExp(Expression paramExpression, ArrayType paramArrayType)
  {
    super(Invoke.make, new Expression[] { new QuoteExp(typeSetArray), paramExpression });
    this.elementType = paramArrayType.getComponentType();
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramDeclaration = paramApplyExp.getArgs();
    if (paramDeclaration.length == 2)
    {
      paramApplyExp = getArgs()[1];
      Object localObject = paramDeclaration[0];
      paramDeclaration = paramDeclaration[1];
      paramApplyExp = paramInlineCalls.visitApplyOnly(new ApplyExp(new ArraySet(this.elementType), new Expression[] { paramApplyExp, localObject, paramDeclaration }), paramType);
    }
    return paramApplyExp;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\SetArrayExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */