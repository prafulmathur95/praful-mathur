package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class SynchronizedExp
  extends Expression
{
  Expression body;
  Expression object;
  
  public SynchronizedExp(Expression paramExpression1, Expression paramExpression2)
  {
    this.object = paramExpression1;
    this.body = paramExpression2;
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    synchronized (this.object.eval(paramCallContext))
    {
      Object localObject2 = this.body.eval(paramCallContext);
      paramCallContext.writeValue(localObject2);
      return;
    }
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    this.object.compile(paramCompilation, Target.pushObject);
    localCodeAttr.emitDup(1);
    Variable localVariable = localCodeAttr.pushScope().addVariable(localCodeAttr, Type.pointer_type, null);
    localCodeAttr.emitStore(localVariable);
    localCodeAttr.emitMonitorEnter();
    if (((paramTarget instanceof IgnoreTarget)) || ((paramTarget instanceof ConsumerTarget))) {}
    for (Type localType = null;; localType = paramTarget.getType())
    {
      localCodeAttr.emitTryStart(false, localType);
      this.body.compileWithPosition(paramCompilation, paramTarget);
      localCodeAttr.emitLoad(localVariable);
      localCodeAttr.emitMonitorExit();
      localCodeAttr.emitTryEnd();
      localCodeAttr.emitCatchStart(null);
      localCodeAttr.emitLoad(localVariable);
      localCodeAttr.emitMonitorExit();
      localCodeAttr.emitThrow();
      localCodeAttr.emitCatchEnd();
      localCodeAttr.emitTryCatchEnd();
      localCodeAttr.popScope();
      return;
    }
  }
  
  protected boolean mustCompile()
  {
    return false;
  }
  
  public void print(OutPort paramOutPort)
  {
    paramOutPort.print("(Synchronized ");
    this.object.print(paramOutPort);
    paramOutPort.print(" ");
    this.body.print(paramOutPort);
    paramOutPort.print(")");
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitSynchronizedExp(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    this.object = paramExpVisitor.visitAndUpdate(this.object, paramD);
    if (paramExpVisitor.exitValue == null) {
      this.body = paramExpVisitor.visitAndUpdate(this.body, paramD);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\SynchronizedExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */