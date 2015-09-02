package gnu.expr;

import gnu.bytecode.Type;
import gnu.kawa.functions.AppendValues;
import java.util.HashSet;
import java.util.Set;

public class FindTailCalls
  extends ExpExpVisitor<Expression>
{
  public static void findTailCalls(Expression paramExpression, Compilation paramCompilation)
  {
    FindTailCalls localFindTailCalls = new FindTailCalls();
    localFindTailCalls.setContext(paramCompilation);
    localFindTailCalls.visit(paramExpression, paramExpression);
  }
  
  public void postVisitDecls(ScopeExp paramScopeExp)
  {
    for (paramScopeExp = paramScopeExp.firstDecl(); paramScopeExp != null; paramScopeExp = paramScopeExp.nextDecl())
    {
      Object localObject = paramScopeExp.getValue();
      if ((localObject instanceof LambdaExp))
      {
        LambdaExp localLambdaExp = (LambdaExp)localObject;
        if (paramScopeExp.getCanRead()) {
          localLambdaExp.setCanRead(true);
        }
        if (paramScopeExp.getCanCall()) {
          localLambdaExp.setCanCall(true);
        }
      }
      if ((paramScopeExp.getFlag(1024L)) && ((localObject instanceof ReferenceExp)))
      {
        localObject = ((ReferenceExp)localObject).contextDecl();
        if ((localObject != null) && (((Declaration)localObject).isPrivate())) {
          ((Declaration)localObject).setFlag(524288L);
        }
      }
    }
  }
  
  protected Expression visitApplyExp(ApplyExp paramApplyExp, Expression paramExpression)
  {
    int i;
    Object localObject2;
    Object localObject1;
    if (paramExpression == this.currentLambda.body)
    {
      i = 1;
      if (i != 0) {
        paramApplyExp.setTailCall(true);
      }
      paramApplyExp.context = this.currentLambda;
      localObject2 = null;
      if (!(paramApplyExp.func instanceof ReferenceExp)) {
        break label172;
      }
      Object localObject3 = Declaration.followAliases(((ReferenceExp)paramApplyExp.func).binding);
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        if (!((Declaration)localObject3).getFlag(2048L))
        {
          paramApplyExp.nextCall = ((Declaration)localObject3).firstCall;
          ((Declaration)localObject3).firstCall = paramApplyExp;
        }
        localObject1 = getCompilation();
        ((Declaration)localObject3).setCanCall();
        if (!((Compilation)localObject1).mustCompile) {
          ((Declaration)localObject3).setCanRead();
        }
        localObject3 = ((Declaration)localObject3).getValue();
        localObject1 = localObject2;
        if ((localObject3 instanceof LambdaExp)) {
          localObject1 = (LambdaExp)localObject3;
        }
      }
      label140:
      if ((localObject1 != null) && (((LambdaExp)localObject1).returnContinuation != paramExpression)) {
        break label268;
      }
    }
    for (;;)
    {
      paramApplyExp.args = visitExps(paramApplyExp.args);
      return paramApplyExp;
      i = 0;
      break;
      label172:
      if (((paramApplyExp.func instanceof LambdaExp)) && (!(paramApplyExp.func instanceof ClassExp)))
      {
        localObject1 = (LambdaExp)paramApplyExp.func;
        visitLambdaExp((LambdaExp)localObject1, false);
        ((LambdaExp)localObject1).setCanCall(true);
        break label140;
      }
      if (((paramApplyExp.func instanceof QuoteExp)) && (((QuoteExp)paramApplyExp.func).getValue() == AppendValues.appendValues))
      {
        localObject1 = localObject2;
        break label140;
      }
      paramApplyExp.func = visitExpression(paramApplyExp.func, paramApplyExp.func);
      localObject1 = localObject2;
      break label140;
      label268:
      if ((localObject1 != this.currentLambda) || (i == 0)) {
        if (i != 0)
        {
          if (((LambdaExp)localObject1).tailCallers == null) {
            ((LambdaExp)localObject1).tailCallers = new HashSet();
          }
          ((LambdaExp)localObject1).tailCallers.add(this.currentLambda);
        }
        else if (((LambdaExp)localObject1).returnContinuation == null)
        {
          ((LambdaExp)localObject1).returnContinuation = paramExpression;
          ((LambdaExp)localObject1).inlineHome = this.currentLambda;
        }
        else
        {
          ((LambdaExp)localObject1).returnContinuation = LambdaExp.unknownContinuation;
          ((LambdaExp)localObject1).inlineHome = null;
        }
      }
    }
  }
  
  protected Expression visitBeginExp(BeginExp paramBeginExp, Expression paramExpression)
  {
    int j = paramBeginExp.length - 1;
    int i = 0;
    if (i <= j)
    {
      Expression[] arrayOfExpression = paramBeginExp.exps;
      Expression localExpression2 = paramBeginExp.exps[i];
      if (i == j) {}
      for (Expression localExpression1 = paramExpression;; localExpression1 = paramBeginExp.exps[i])
      {
        arrayOfExpression[i] = ((Expression)localExpression2.visit(this, localExpression1));
        i += 1;
        break;
      }
    }
    return paramBeginExp;
  }
  
  protected Expression visitBlockExp(BlockExp paramBlockExp, Expression paramExpression)
  {
    paramBlockExp.body = ((Expression)paramBlockExp.body.visit(this, paramExpression));
    if (paramBlockExp.exitBody != null) {
      paramBlockExp.exitBody = ((Expression)paramBlockExp.exitBody.visit(this, paramBlockExp.exitBody));
    }
    return paramBlockExp;
  }
  
  protected Expression visitClassExp(ClassExp paramClassExp, Expression paramExpression)
  {
    LambdaExp localLambdaExp = this.currentLambda;
    this.currentLambda = paramClassExp;
    try
    {
      for (paramExpression = paramClassExp.firstChild; (paramExpression != null) && (this.exitValue == null); paramExpression = paramExpression.nextSibling) {
        visitLambdaExp(paramExpression, false);
      }
      return paramClassExp;
    }
    finally
    {
      this.currentLambda = localLambdaExp;
    }
  }
  
  protected Expression visitExpression(Expression paramExpression1, Expression paramExpression2)
  {
    return (Expression)super.visitExpression(paramExpression1, paramExpression1);
  }
  
  public Expression[] visitExps(Expression[] paramArrayOfExpression)
  {
    int j = paramArrayOfExpression.length;
    int i = 0;
    while (i < j)
    {
      Expression localExpression = paramArrayOfExpression[i];
      paramArrayOfExpression[i] = ((Expression)visit(localExpression, localExpression));
      i += 1;
    }
    return paramArrayOfExpression;
  }
  
  protected Expression visitFluidLetExp(FluidLetExp paramFluidLetExp, Expression paramExpression)
  {
    for (paramExpression = paramFluidLetExp.firstDecl(); paramExpression != null; paramExpression = paramExpression.nextDecl())
    {
      paramExpression.setCanRead(true);
      if (paramExpression.base != null) {
        paramExpression.base.setCanRead(true);
      }
    }
    visitLetDecls(paramFluidLetExp);
    paramFluidLetExp.body = ((Expression)paramFluidLetExp.body.visit(this, paramFluidLetExp.body));
    postVisitDecls(paramFluidLetExp);
    return paramFluidLetExp;
  }
  
  protected Expression visitIfExp(IfExp paramIfExp, Expression paramExpression)
  {
    paramIfExp.test = ((Expression)paramIfExp.test.visit(this, paramIfExp.test));
    paramIfExp.then_clause = ((Expression)paramIfExp.then_clause.visit(this, paramExpression));
    Expression localExpression = paramIfExp.else_clause;
    if (localExpression != null) {
      paramIfExp.else_clause = ((Expression)localExpression.visit(this, paramExpression));
    }
    return paramIfExp;
  }
  
  protected Expression visitLambdaExp(LambdaExp paramLambdaExp, Expression paramExpression)
  {
    visitLambdaExp(paramLambdaExp, true);
    return paramLambdaExp;
  }
  
  /* Error */
  final void visitLambdaExp(LambdaExp paramLambdaExp, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 80	gnu/expr/FindTailCalls:currentLambda	Lgnu/expr/LambdaExp;
    //   4: astore 4
    //   6: aload_0
    //   7: aload_1
    //   8: putfield 80	gnu/expr/FindTailCalls:currentLambda	Lgnu/expr/LambdaExp;
    //   11: iload_2
    //   12: ifeq +8 -> 20
    //   15: aload_1
    //   16: iconst_1
    //   17: invokevirtual 45	gnu/expr/LambdaExp:setCanRead	(Z)V
    //   20: aload_1
    //   21: getfield 269	gnu/expr/LambdaExp:defaultArgs	[Lgnu/expr/Expression;
    //   24: ifnull +15 -> 39
    //   27: aload_1
    //   28: aload_0
    //   29: aload_1
    //   30: getfield 269	gnu/expr/LambdaExp:defaultArgs	[Lgnu/expr/Expression;
    //   33: invokevirtual 137	gnu/expr/FindTailCalls:visitExps	([Lgnu/expr/Expression;)[Lgnu/expr/Expression;
    //   36: putfield 269	gnu/expr/LambdaExp:defaultArgs	[Lgnu/expr/Expression;
    //   39: aload_0
    //   40: getfield 219	gnu/expr/FindTailCalls:exitValue	Ljava/lang/Object;
    //   43: ifnonnull +39 -> 82
    //   46: aload_1
    //   47: getfield 84	gnu/expr/LambdaExp:body	Lgnu/expr/Expression;
    //   50: ifnull +32 -> 82
    //   53: aload_1
    //   54: getfield 84	gnu/expr/LambdaExp:body	Lgnu/expr/Expression;
    //   57: astore 5
    //   59: aload_1
    //   60: invokevirtual 272	gnu/expr/LambdaExp:getInlineOnly	()Z
    //   63: ifeq +31 -> 94
    //   66: aload_1
    //   67: astore_3
    //   68: aload_1
    //   69: aload 5
    //   71: aload_0
    //   72: aload_3
    //   73: invokevirtual 196	gnu/expr/Expression:visit	(Lgnu/expr/ExpVisitor;Ljava/lang/Object;)Ljava/lang/Object;
    //   76: checkcast 180	gnu/expr/Expression
    //   79: putfield 84	gnu/expr/LambdaExp:body	Lgnu/expr/Expression;
    //   82: aload_0
    //   83: aload 4
    //   85: putfield 80	gnu/expr/FindTailCalls:currentLambda	Lgnu/expr/LambdaExp;
    //   88: aload_0
    //   89: aload_1
    //   90: invokevirtual 243	gnu/expr/FindTailCalls:postVisitDecls	(Lgnu/expr/ScopeExp;)V
    //   93: return
    //   94: aload_1
    //   95: getfield 84	gnu/expr/LambdaExp:body	Lgnu/expr/Expression;
    //   98: astore_3
    //   99: goto -31 -> 68
    //   102: astore_1
    //   103: aload_0
    //   104: aload 4
    //   106: putfield 80	gnu/expr/FindTailCalls:currentLambda	Lgnu/expr/LambdaExp;
    //   109: aload_1
    //   110: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	111	0	this	FindTailCalls
    //   0	111	1	paramLambdaExp	LambdaExp
    //   0	111	2	paramBoolean	boolean
    //   67	32	3	localObject	Object
    //   4	101	4	localLambdaExp	LambdaExp
    //   57	13	5	localExpression	Expression
    // Exception table:
    //   from	to	target	type
    //   20	39	102	finally
    //   39	66	102	finally
    //   68	82	102	finally
    //   94	99	102	finally
  }
  
  void visitLetDecls(LetExp paramLetExp)
  {
    Declaration localDeclaration = paramLetExp.firstDecl();
    int j = paramLetExp.inits.length;
    int i = 0;
    while (i < j)
    {
      Expression localExpression1 = visitSetExp(localDeclaration, paramLetExp.inits[i]);
      Object localObject = localExpression1;
      if (localExpression1 == QuoteExp.undefined_exp)
      {
        Expression localExpression2 = localDeclaration.getValue();
        if (!(localExpression2 instanceof LambdaExp))
        {
          localObject = localExpression1;
          if (localExpression2 != localExpression1)
          {
            localObject = localExpression1;
            if (!(localExpression2 instanceof QuoteExp)) {}
          }
        }
        else
        {
          localObject = localExpression2;
        }
      }
      paramLetExp.inits[i] = localObject;
      i += 1;
      localDeclaration = localDeclaration.nextDecl();
    }
  }
  
  protected Expression visitLetExp(LetExp paramLetExp, Expression paramExpression)
  {
    visitLetDecls(paramLetExp);
    paramLetExp.body = ((Expression)paramLetExp.body.visit(this, paramExpression));
    postVisitDecls(paramLetExp);
    return paramLetExp;
  }
  
  protected Expression visitReferenceExp(ReferenceExp paramReferenceExp, Expression paramExpression)
  {
    paramExpression = Declaration.followAliases(paramReferenceExp.binding);
    Object localObject;
    if (paramExpression != null)
    {
      localObject = paramExpression.type;
      if ((localObject != null) && (((Type)localObject).isVoid())) {
        paramExpression = QuoteExp.voidExp;
      }
    }
    do
    {
      return paramExpression;
      paramExpression.setCanRead(true);
      localObject = paramReferenceExp.contextDecl();
      paramExpression = paramReferenceExp;
    } while (localObject == null);
    ((Declaration)localObject).setCanRead(true);
    return paramReferenceExp;
  }
  
  final Expression visitSetExp(Declaration paramDeclaration, Expression paramExpression)
  {
    if ((paramDeclaration != null) && (paramDeclaration.getValue() == paramExpression) && ((paramExpression instanceof LambdaExp)) && (!(paramExpression instanceof ClassExp)) && (!paramDeclaration.isPublic()))
    {
      paramDeclaration = (LambdaExp)paramExpression;
      visitLambdaExp(paramDeclaration, false);
      return paramDeclaration;
    }
    return (Expression)paramExpression.visit(this, paramExpression);
  }
  
  protected Expression visitSetExp(SetExp paramSetExp, Expression paramExpression)
  {
    Object localObject = paramSetExp.binding;
    paramExpression = (Expression)localObject;
    if (localObject != null)
    {
      paramExpression = (Expression)localObject;
      if (((Declaration)localObject).isAlias())
      {
        if (paramSetExp.isDefining())
        {
          paramSetExp.new_value = ((Expression)paramSetExp.new_value.visit(this, paramSetExp.new_value));
          return paramSetExp;
        }
        paramExpression = Declaration.followAliases((Declaration)localObject);
      }
    }
    localObject = paramSetExp.contextDecl();
    if (localObject != null) {
      ((Declaration)localObject).setCanRead(true);
    }
    localObject = visitSetExp(paramExpression, paramSetExp.new_value);
    if ((paramExpression != null) && ((paramExpression.context instanceof LetExp)) && (localObject == paramExpression.getValue()) && (((localObject instanceof LambdaExp)) || ((localObject instanceof QuoteExp)))) {
      return QuoteExp.voidExp;
    }
    paramSetExp.new_value = ((Expression)localObject);
    return paramSetExp;
  }
  
  protected Expression visitSynchronizedExp(SynchronizedExp paramSynchronizedExp, Expression paramExpression)
  {
    paramSynchronizedExp.object = ((Expression)paramSynchronizedExp.object.visit(this, paramSynchronizedExp.object));
    paramSynchronizedExp.body = ((Expression)paramSynchronizedExp.body.visit(this, paramSynchronizedExp.body));
    return paramSynchronizedExp;
  }
  
  protected Expression visitTryExp(TryExp paramTryExp, Expression paramExpression)
  {
    Object localObject;
    if (paramTryExp.finally_clause == null)
    {
      localObject = paramExpression;
      paramTryExp.try_clause = ((Expression)paramTryExp.try_clause.visit(this, localObject));
      localObject = paramTryExp.catch_clauses;
      label30:
      if ((this.exitValue != null) || (localObject == null)) {
        break label93;
      }
      if (paramTryExp.finally_clause != null) {
        break label84;
      }
    }
    label84:
    for (Expression localExpression = paramExpression;; localExpression = ((CatchClause)localObject).body)
    {
      ((CatchClause)localObject).body = ((Expression)((CatchClause)localObject).body.visit(this, localExpression));
      localObject = ((CatchClause)localObject).getNext();
      break label30;
      localObject = paramTryExp.try_clause;
      break;
    }
    label93:
    paramExpression = paramTryExp.finally_clause;
    if (paramExpression != null) {
      paramTryExp.finally_clause = ((Expression)paramExpression.visit(this, paramExpression));
    }
    return paramTryExp;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\FindTailCalls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */