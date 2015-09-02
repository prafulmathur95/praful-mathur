package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.kawa.reflect.Invoke;

class SetListExp
  extends ApplyExp
{
  public SetListExp(Expression paramExpression, Expression[] paramArrayOfExpression)
  {
    super(paramExpression, paramArrayOfExpression);
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramDeclaration = paramApplyExp.getArgs();
    if (paramDeclaration.length == 2)
    {
      paramApplyExp = getArgs()[0];
      QuoteExp localQuoteExp = QuoteExp.getInstance("set");
      Expression localExpression = Compilation.makeCoercion(paramDeclaration[0], Type.intType);
      paramDeclaration = paramDeclaration[1];
      paramApplyExp = Compilation.makeCoercion(paramInlineCalls.visitApplyOnly(new ApplyExp(Invoke.invoke, new Expression[] { paramApplyExp, localQuoteExp, localExpression, paramDeclaration }), paramType), Type.voidType);
    }
    return paramApplyExp;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\SetListExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */