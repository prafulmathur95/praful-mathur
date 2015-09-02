package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Variable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class CatchClause
  extends LetExp
{
  CatchClause next;
  
  public CatchClause()
  {
    super(new Expression[] { QuoteExp.voidExp });
  }
  
  public CatchClause(LambdaExp paramLambdaExp)
  {
    this();
    Declaration localDeclaration = paramLambdaExp.firstDecl();
    paramLambdaExp.remove(null, localDeclaration);
    add(localDeclaration);
    this.body = paramLambdaExp.body;
  }
  
  public CatchClause(Object paramObject, ClassType paramClassType)
  {
    this();
    addDeclaration(paramObject, paramClassType);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Variable localVariable = firstDecl().allocateVariable(localCodeAttr);
    localCodeAttr.enterScope(getVarScope());
    localCodeAttr.emitCatchStart(localVariable);
    this.body.compileWithPosition(paramCompilation, paramTarget);
    localCodeAttr.emitCatchEnd();
    localCodeAttr.popScope();
  }
  
  protected Object evalVariable(int paramInt, CallContext paramCallContext)
    throws Throwable
  {
    return paramCallContext.value1;
  }
  
  public final Expression getBody()
  {
    return this.body;
  }
  
  public final CatchClause getNext()
  {
    return this.next;
  }
  
  protected boolean mustCompile()
  {
    return false;
  }
  
  public void print(OutPort paramOutPort)
  {
    paramOutPort.writeSpaceLinear();
    paramOutPort.startLogicalBlock("(Catch", ")", 2);
    paramOutPort.writeSpaceFill();
    this.decls.printInfo(paramOutPort);
    paramOutPort.writeSpaceLinear();
    this.body.print(paramOutPort);
    paramOutPort.endLogicalBlock(")");
  }
  
  public final void setBody(Expression paramExpression)
  {
    this.body = paramExpression;
  }
  
  public final void setNext(CatchClause paramCatchClause)
  {
    this.next = paramCatchClause;
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    this.body = paramExpVisitor.visitAndUpdate(this.body, paramD);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\CatchClause.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */