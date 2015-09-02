package gnu.expr;

import gnu.text.SourceLocator;
import gnu.text.SourceMessages;

public class ExpVisitor<R, D>
  implements SourceLocator
{
  Compilation comp;
  protected LambdaExp currentLambda = null;
  protected Object exitValue = null;
  protected SourceMessages messages;
  
  protected R defaultValue(Expression paramExpression, D paramD)
  {
    return null;
  }
  
  public void error(char paramChar, String paramString)
  {
    char c = paramChar;
    if (paramChar == 'w')
    {
      c = paramChar;
      if (this.comp.warnAsError()) {
        c = 'e';
      }
    }
    if (this.messages != null)
    {
      this.messages.error(c, paramString);
      return;
    }
    new Error("internal error: " + paramString);
  }
  
  public final int getColumnNumber()
  {
    return this.messages.getColumnNumber();
  }
  
  public Compilation getCompilation()
  {
    return this.comp;
  }
  
  public final LambdaExp getCurrentLambda()
  {
    return this.currentLambda;
  }
  
  public Object getExitValue()
  {
    return this.exitValue;
  }
  
  public final String getFileName()
  {
    return this.messages.getFileName();
  }
  
  public final int getLineNumber()
  {
    return this.messages.getLineNumber();
  }
  
  public SourceMessages getMessages()
  {
    return this.messages;
  }
  
  public String getPublicId()
  {
    return this.messages.getPublicId();
  }
  
  public String getSystemId()
  {
    return this.messages.getSystemId();
  }
  
  public boolean isStableSourceLocation()
  {
    return false;
  }
  
  public Expression noteError(String paramString)
  {
    if (this.messages != null) {
      this.messages.error('e', paramString);
    }
    return new ErrorExp(paramString);
  }
  
  public void setColumn(int paramInt)
  {
    this.messages.setColumn(paramInt);
  }
  
  public void setContext(Compilation paramCompilation)
  {
    this.comp = paramCompilation;
    this.messages = paramCompilation.getMessages();
  }
  
  public void setFile(String paramString)
  {
    this.messages.setFile(paramString);
  }
  
  public void setLine(int paramInt)
  {
    this.messages.setLine(paramInt);
  }
  
  public void setLine(String paramString, int paramInt1, int paramInt2)
  {
    this.messages.setLine(paramString, paramInt1, paramInt2);
  }
  
  protected Expression update(Expression paramExpression, R paramR)
  {
    return paramExpression;
  }
  
  public R visit(Expression paramExpression, D paramD)
  {
    int i = paramExpression.getLineNumber();
    if ((this.messages != null) && (i > 0))
    {
      String str = this.messages.getFileName();
      int j = this.messages.getLineNumber();
      int k = this.messages.getColumnNumber();
      this.messages.setLine(paramExpression.getFileName(), i, paramExpression.getColumnNumber());
      paramExpression = paramExpression.visit(this, paramD);
      this.messages.setLine(str, j, k);
      return paramExpression;
    }
    return (R)paramExpression.visit(this, paramD);
  }
  
  public Expression visitAndUpdate(Expression paramExpression, D paramD)
  {
    return update(paramExpression, visit(paramExpression, paramD));
  }
  
  protected R visitApplyExp(ApplyExp paramApplyExp, D paramD)
  {
    return (R)visitExpression(paramApplyExp, paramD);
  }
  
  protected R visitBeginExp(BeginExp paramBeginExp, D paramD)
  {
    return (R)visitExpression(paramBeginExp, paramD);
  }
  
  protected R visitBlockExp(BlockExp paramBlockExp, D paramD)
  {
    return (R)visitExpression(paramBlockExp, paramD);
  }
  
  protected R visitClassExp(ClassExp paramClassExp, D paramD)
  {
    return (R)visitLambdaExp(paramClassExp, paramD);
  }
  
  protected final void visitDeclarationType(Declaration paramDeclaration)
  {
    Expression localExpression1 = paramDeclaration.typeExp;
    if (localExpression1 != null)
    {
      Expression localExpression2 = visitAndUpdate(localExpression1, null);
      if (localExpression2 != localExpression1) {
        paramDeclaration.setTypeExp(localExpression2);
      }
    }
  }
  
  protected final void visitDeclarationTypes(ScopeExp paramScopeExp)
  {
    for (paramScopeExp = paramScopeExp.firstDecl(); paramScopeExp != null; paramScopeExp = paramScopeExp.nextDecl()) {
      visitDeclarationType(paramScopeExp);
    }
  }
  
  public void visitDefaultArgs(LambdaExp paramLambdaExp, D paramD)
  {
    paramLambdaExp.defaultArgs = visitExps(paramLambdaExp.defaultArgs, paramD);
  }
  
  protected R visitExitExp(ExitExp paramExitExp, D paramD)
  {
    return (R)visitExpression(paramExitExp, paramD);
  }
  
  protected R visitExpression(Expression paramExpression, D paramD)
  {
    paramExpression.visitChildren(this, paramD);
    return (R)defaultValue(paramExpression, paramD);
  }
  
  public Expression[] visitExps(Expression[] paramArrayOfExpression, int paramInt, D paramD)
  {
    int i = 0;
    while ((i < paramInt) && (this.exitValue == null))
    {
      paramArrayOfExpression[i] = visitAndUpdate(paramArrayOfExpression[i], paramD);
      i += 1;
    }
    return paramArrayOfExpression;
  }
  
  public Expression[] visitExps(Expression[] paramArrayOfExpression, D paramD)
  {
    if (paramArrayOfExpression == null) {
      return null;
    }
    return visitExps(paramArrayOfExpression, paramArrayOfExpression.length, paramD);
  }
  
  protected R visitFluidLetExp(FluidLetExp paramFluidLetExp, D paramD)
  {
    return (R)visitLetExp(paramFluidLetExp, paramD);
  }
  
  protected R visitIfExp(IfExp paramIfExp, D paramD)
  {
    return (R)visitExpression(paramIfExp, paramD);
  }
  
  protected R visitLambdaExp(LambdaExp paramLambdaExp, D paramD)
  {
    return (R)visitScopeExp(paramLambdaExp, paramD);
  }
  
  protected R visitLangExp(LangExp paramLangExp, D paramD)
  {
    return (R)visitExpression(paramLangExp, paramD);
  }
  
  protected R visitLetExp(LetExp paramLetExp, D paramD)
  {
    return (R)visitScopeExp(paramLetExp, paramD);
  }
  
  protected R visitModuleExp(ModuleExp paramModuleExp, D paramD)
  {
    return (R)visitLambdaExp(paramModuleExp, paramD);
  }
  
  protected R visitObjectExp(ObjectExp paramObjectExp, D paramD)
  {
    return (R)visitClassExp(paramObjectExp, paramD);
  }
  
  protected R visitQuoteExp(QuoteExp paramQuoteExp, D paramD)
  {
    return (R)visitExpression(paramQuoteExp, paramD);
  }
  
  protected R visitReferenceExp(ReferenceExp paramReferenceExp, D paramD)
  {
    return (R)visitExpression(paramReferenceExp, paramD);
  }
  
  protected R visitScopeExp(ScopeExp paramScopeExp, D paramD)
  {
    visitDeclarationTypes(paramScopeExp);
    return (R)visitExpression(paramScopeExp, paramD);
  }
  
  protected R visitSetExp(SetExp paramSetExp, D paramD)
  {
    Declaration localDeclaration = paramSetExp.binding;
    if ((localDeclaration != null) && (localDeclaration.value == paramSetExp.new_value)) {}
    for (int i = 1;; i = 0)
    {
      paramSetExp.new_value = visitSetExpValue(paramSetExp.new_value, paramD, paramSetExp.getBinding());
      if ((i != 0) && (paramSetExp.isDefining()))
      {
        localDeclaration.value = paramSetExp.new_value;
        if ((paramSetExp.new_value instanceof LambdaExp)) {
          ((LambdaExp)paramSetExp.new_value).nameDecl = localDeclaration;
        }
      }
      return (R)defaultValue(paramSetExp, paramD);
    }
  }
  
  protected Expression visitSetExpValue(Expression paramExpression, D paramD, Declaration paramDeclaration)
  {
    return visitAndUpdate(paramExpression, paramD);
  }
  
  protected R visitSynchronizedExp(SynchronizedExp paramSynchronizedExp, D paramD)
  {
    return (R)visitExpression(paramSynchronizedExp, paramD);
  }
  
  protected R visitThisExp(ThisExp paramThisExp, D paramD)
  {
    return (R)visitReferenceExp(paramThisExp, paramD);
  }
  
  protected R visitTryExp(TryExp paramTryExp, D paramD)
  {
    return (R)visitExpression(paramTryExp, paramD);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ExpVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */