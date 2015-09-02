package gnu.expr;

import gnu.bytecode.ExitableBlock;
import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

public class ExitExp
  extends Expression
{
  BlockExp block;
  Expression result;
  
  public ExitExp(BlockExp paramBlockExp)
  {
    this.result = QuoteExp.voidExp;
    this.block = paramBlockExp;
  }
  
  public ExitExp(Expression paramExpression, BlockExp paramBlockExp)
  {
    this.result = paramExpression;
    this.block = paramBlockExp;
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    throw new BlockExitException(this, this.result.eval(paramCallContext));
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    paramCompilation.getCode();
    if (this.result == null) {}
    for (paramTarget = QuoteExp.voidExp;; paramTarget = this.result)
    {
      paramTarget.compileWithPosition(paramCompilation, this.block.exitTarget);
      this.block.exitableBlock.exit();
      return;
    }
  }
  
  protected Expression deepCopy(IdentityHashTable paramIdentityHashTable)
  {
    Expression localExpression = deepCopy(this.result, paramIdentityHashTable);
    if ((localExpression == null) && (this.result != null)) {
      return null;
    }
    paramIdentityHashTable = paramIdentityHashTable.get(this.block);
    if (paramIdentityHashTable == null) {}
    for (paramIdentityHashTable = this.block;; paramIdentityHashTable = (BlockExp)paramIdentityHashTable)
    {
      paramIdentityHashTable = new ExitExp(localExpression, paramIdentityHashTable);
      paramIdentityHashTable.flags = getFlags();
      return paramIdentityHashTable;
    }
  }
  
  public Type getType()
  {
    return Type.neverReturnsType;
  }
  
  protected boolean mustCompile()
  {
    return false;
  }
  
  public void print(OutPort paramOutPort)
  {
    paramOutPort.startLogicalBlock("(Exit", false, ")");
    paramOutPort.writeSpaceFill();
    if ((this.block == null) || (this.block.label == null)) {
      paramOutPort.print("<unknown>");
    }
    for (;;)
    {
      if (this.result != null)
      {
        paramOutPort.writeSpaceLinear();
        this.result.print(paramOutPort);
      }
      paramOutPort.endLogicalBlock(")");
      return;
      paramOutPort.print(this.block.label.getName());
    }
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitExitExp(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    this.result = paramExpVisitor.visitAndUpdate(this.result, paramD);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ExitExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */