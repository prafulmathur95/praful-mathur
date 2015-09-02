package gnu.expr;

import gnu.bytecode.Type;
import gnu.lists.Consumer;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.text.Options;
import java.util.Vector;

public class BeginExp
  extends Expression
{
  Vector compileOptions;
  Expression[] exps;
  int length;
  
  public BeginExp() {}
  
  public BeginExp(Expression paramExpression1, Expression paramExpression2)
  {
    this.exps = new Expression[2];
    this.exps[0] = paramExpression1;
    this.exps[1] = paramExpression2;
    this.length = 2;
  }
  
  public BeginExp(Expression[] paramArrayOfExpression)
  {
    this.exps = paramArrayOfExpression;
    this.length = paramArrayOfExpression.length;
  }
  
  public static final Expression canonicalize(Expression paramExpression)
  {
    BeginExp localBeginExp;
    if ((paramExpression instanceof BeginExp))
    {
      localBeginExp = (BeginExp)paramExpression;
      if (localBeginExp.compileOptions == null) {
        break label21;
      }
    }
    label21:
    int i;
    do
    {
      return paramExpression;
      i = localBeginExp.length;
      if (i == 0) {
        return QuoteExp.voidExp;
      }
    } while (i != 1);
    return canonicalize(localBeginExp.exps[0]);
  }
  
  public static final Expression canonicalize(Expression[] paramArrayOfExpression)
  {
    int i = paramArrayOfExpression.length;
    if (i == 0) {
      return QuoteExp.voidExp;
    }
    if (i == 1) {
      return canonicalize(paramArrayOfExpression[0]);
    }
    return new BeginExp(paramArrayOfExpression);
  }
  
  public final void add(Expression paramExpression)
  {
    if (this.exps == null) {
      this.exps = new Expression[8];
    }
    if (this.length == this.exps.length)
    {
      arrayOfExpression = new Expression[this.length * 2];
      System.arraycopy(this.exps, 0, arrayOfExpression, 0, this.length);
      this.exps = arrayOfExpression;
    }
    Expression[] arrayOfExpression = this.exps;
    int i = this.length;
    this.length = (i + 1);
    arrayOfExpression[i] = paramExpression;
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    int j = this.length;
    localConsumer = paramCallContext.consumer;
    paramCallContext.consumer = VoidConsumer.instance;
    int i = 0;
    for (;;)
    {
      if (i < j - 1) {}
      try
      {
        this.exps[i].eval(paramCallContext);
        i += 1;
      }
      finally
      {
        paramCallContext.consumer = localConsumer;
      }
    }
    paramCallContext.consumer = localConsumer;
    this.exps[i].apply(paramCallContext);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    pushOptions(paramCompilation);
    try
    {
      int j = this.length;
      int i = 0;
      while (i < j - 1)
      {
        this.exps[i].compileWithPosition(paramCompilation, Target.Ignore);
        i += 1;
      }
      this.exps[i].compileWithPosition(paramCompilation, paramTarget);
      return;
    }
    finally
    {
      popOptions(paramCompilation);
    }
  }
  
  public final int getExpressionCount()
  {
    return this.length;
  }
  
  public final Expression[] getExpressions()
  {
    return this.exps;
  }
  
  public Type getType()
  {
    return this.exps[(this.length - 1)].getType();
  }
  
  protected boolean mustCompile()
  {
    return false;
  }
  
  public void popOptions(Compilation paramCompilation)
  {
    if ((this.compileOptions != null) && (paramCompilation != null)) {
      paramCompilation.currentOptions.popOptionValues(this.compileOptions);
    }
  }
  
  public void print(OutPort paramOutPort)
  {
    paramOutPort.startLogicalBlock("(Begin", ")", 2);
    paramOutPort.writeSpaceFill();
    printLineColumn(paramOutPort);
    if (this.compileOptions != null)
    {
      paramOutPort.writeSpaceFill();
      paramOutPort.startLogicalBlock("(CompileOptions", ")", 2);
      j = this.compileOptions.size();
      i = 0;
      while (i < j)
      {
        Object localObject1 = this.compileOptions.elementAt(i);
        Object localObject2 = this.compileOptions.elementAt(i + 2);
        paramOutPort.writeSpaceFill();
        paramOutPort.startLogicalBlock("", "", 2);
        paramOutPort.print(localObject1);
        paramOutPort.print(':');
        paramOutPort.writeSpaceLinear();
        paramOutPort.print(localObject2);
        paramOutPort.endLogicalBlock("");
        i += 3;
      }
      paramOutPort.endLogicalBlock(")");
    }
    int j = this.length;
    int i = 0;
    while (i < j)
    {
      paramOutPort.writeSpaceLinear();
      this.exps[i].print(paramOutPort);
      i += 1;
    }
    paramOutPort.endLogicalBlock(")");
  }
  
  public void pushOptions(Compilation paramCompilation)
  {
    if ((this.compileOptions != null) && (paramCompilation != null)) {
      paramCompilation.currentOptions.pushOptionValues(this.compileOptions);
    }
  }
  
  public void setCompileOptions(Vector paramVector)
  {
    this.compileOptions = paramVector;
  }
  
  public final void setExpressions(Expression[] paramArrayOfExpression)
  {
    this.exps = paramArrayOfExpression;
    this.length = paramArrayOfExpression.length;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    pushOptions(paramExpVisitor.comp);
    try
    {
      paramD = paramExpVisitor.visitBeginExp(this, paramD);
      return paramD;
    }
    finally
    {
      popOptions(paramExpVisitor.comp);
    }
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    this.exps = paramExpVisitor.visitExps(this.exps, this.length, paramD);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\BeginExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */