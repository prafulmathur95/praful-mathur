package gnu.expr;

public class PushApply
  extends ExpVisitor<Expression, Void>
{
  public static void pushApply(Expression paramExpression)
  {
    new PushApply().visit(paramExpression, null);
  }
  
  protected Expression defaultValue(Expression paramExpression, Void paramVoid)
  {
    return paramExpression;
  }
  
  protected Expression update(Expression paramExpression1, Expression paramExpression2)
  {
    return paramExpression2;
  }
  
  protected Expression visitApplyExp(ApplyExp paramApplyExp, Void paramVoid)
  {
    Object localObject1 = paramApplyExp.func;
    Object localObject2;
    if (((localObject1 instanceof LetExp)) && (!(localObject1 instanceof FluidLetExp)))
    {
      localObject1 = (LetExp)localObject1;
      localObject2 = ((LetExp)localObject1).body;
      ((LetExp)localObject1).body = paramApplyExp;
      paramApplyExp.func = ((Expression)localObject2);
      return (Expression)visit((Expression)localObject1, paramVoid);
    }
    if ((localObject1 instanceof BeginExp))
    {
      localObject1 = (BeginExp)localObject1;
      localObject2 = ((BeginExp)localObject1).exps;
      int i = ((BeginExp)localObject1).exps.length - 1;
      paramApplyExp.func = localObject2[i];
      localObject2[i] = paramApplyExp;
      return (Expression)visit((Expression)localObject1, paramVoid);
    }
    paramApplyExp.visitChildren(this, paramVoid);
    return paramApplyExp;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\PushApply.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */