package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.ExitableBlock;
import gnu.bytecode.Label;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class BlockExp
  extends Expression
{
  Expression body;
  Expression exitBody;
  Target exitTarget;
  ExitableBlock exitableBlock;
  Declaration label;
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    try
    {
      Object localObject1 = this.body.eval(paramCallContext);
      paramCallContext.writeValue(localObject1);
      return;
    }
    catch (BlockExitException localBlockExitException)
    {
      for (;;)
      {
        if (localBlockExitException.exit.block != this) {
          throw localBlockExitException;
        }
        Object localObject2 = localBlockExitException.exit.result;
        if (this.exitBody != null) {
          localObject2 = this.exitBody.eval(paramCallContext);
        }
      }
    }
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Object localObject;
    if ((this.exitBody == null) && ((paramTarget instanceof StackTarget)))
    {
      localObject = paramTarget.getType();
      this.exitableBlock = localCodeAttr.startExitableBlock((Type)localObject, true);
      if (this.exitBody != null) {
        break label113;
      }
      localObject = paramTarget;
      label45:
      this.exitTarget = ((Target)localObject);
      this.body.compileWithPosition(paramCompilation, paramTarget);
      if (this.exitBody == null) {
        break label120;
      }
      localObject = new Label(localCodeAttr);
      localCodeAttr.emitGoto((Label)localObject);
      localCodeAttr.endExitableBlock();
      this.exitBody.compileWithPosition(paramCompilation, paramTarget);
      ((Label)localObject).define(localCodeAttr);
    }
    for (;;)
    {
      this.exitableBlock = null;
      return;
      localObject = null;
      break;
      label113:
      localObject = Target.Ignore;
      break label45;
      label120:
      localCodeAttr.endExitableBlock();
    }
  }
  
  protected boolean mustCompile()
  {
    return false;
  }
  
  public void print(OutPort paramOutPort)
  {
    paramOutPort.startLogicalBlock("(Block", ")", 2);
    if (this.label != null)
    {
      paramOutPort.print(' ');
      paramOutPort.print(this.label.getName());
    }
    paramOutPort.writeSpaceLinear();
    this.body.print(paramOutPort);
    if (this.exitBody != null)
    {
      paramOutPort.writeSpaceLinear();
      paramOutPort.print("else ");
      this.exitBody.print(paramOutPort);
    }
    paramOutPort.endLogicalBlock(")");
  }
  
  public void setBody(Expression paramExpression)
  {
    this.body = paramExpression;
  }
  
  public void setBody(Expression paramExpression1, Expression paramExpression2)
  {
    this.body = paramExpression1;
    this.exitBody = paramExpression2;
  }
  
  public void setLabel(Declaration paramDeclaration)
  {
    this.label = paramDeclaration;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitBlockExp(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    this.body = paramExpVisitor.visitAndUpdate(this.body, paramD);
    if ((paramExpVisitor.exitValue == null) && (this.exitBody != null)) {
      this.exitBody = paramExpVisitor.visitAndUpdate(this.exitBody, paramD);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\BlockExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */