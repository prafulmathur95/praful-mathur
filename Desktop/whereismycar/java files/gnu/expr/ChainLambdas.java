package gnu.expr;

public class ChainLambdas
  extends ExpExpVisitor<ScopeExp>
{
  public static void chainLambdas(Expression paramExpression, Compilation paramCompilation)
  {
    ChainLambdas localChainLambdas = new ChainLambdas();
    localChainLambdas.setContext(paramCompilation);
    localChainLambdas.visit(paramExpression, null);
  }
  
  protected Expression visitClassExp(ClassExp paramClassExp, ScopeExp paramScopeExp)
  {
    LambdaExp localLambdaExp = this.currentLambda;
    if ((localLambdaExp != null) && (!(localLambdaExp instanceof ClassExp)))
    {
      paramClassExp.nextSibling = localLambdaExp.firstChild;
      localLambdaExp.firstChild = paramClassExp;
    }
    visitScopeExp(paramClassExp, paramScopeExp);
    return paramClassExp;
  }
  
  protected Expression visitLambdaExp(LambdaExp paramLambdaExp, ScopeExp paramScopeExp)
  {
    Object localObject = this.currentLambda;
    if ((localObject != null) && (!(localObject instanceof ClassExp)))
    {
      paramLambdaExp.nextSibling = ((LambdaExp)localObject).firstChild;
      ((LambdaExp)localObject).firstChild = paramLambdaExp;
    }
    paramLambdaExp.outer = paramScopeExp;
    paramLambdaExp.firstChild = null;
    paramLambdaExp.visitChildrenOnly(this, paramLambdaExp);
    paramLambdaExp.visitProperties(this, paramLambdaExp);
    localObject = null;
    LambdaExp localLambdaExp;
    for (paramScopeExp = paramLambdaExp.firstChild; paramScopeExp != null; paramScopeExp = localLambdaExp)
    {
      localLambdaExp = paramScopeExp.nextSibling;
      paramScopeExp.nextSibling = ((LambdaExp)localObject);
      localObject = paramScopeExp;
    }
    paramLambdaExp.firstChild = ((LambdaExp)localObject);
    if ((paramLambdaExp.getName() == null) && (paramLambdaExp.nameDecl != null)) {
      paramLambdaExp.setName(paramLambdaExp.nameDecl.getName());
    }
    paramLambdaExp.setIndexes();
    if (paramLambdaExp.mustCompile()) {
      this.comp.mustCompileHere();
    }
    return paramLambdaExp;
  }
  
  protected Expression visitScopeExp(ScopeExp paramScopeExp1, ScopeExp paramScopeExp2)
  {
    paramScopeExp1.outer = paramScopeExp2;
    paramScopeExp1.visitChildren(this, paramScopeExp1);
    paramScopeExp1.setIndexes();
    if (paramScopeExp1.mustCompile()) {
      this.comp.mustCompileHere();
    }
    return paramScopeExp1;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ChainLambdas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */