package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;

public class ObjectExp
  extends ClassExp
{
  public ObjectExp()
  {
    super(true);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    compileMembers(paramCompilation);
    CodeAttr localCodeAttr = paramCompilation.getCode();
    localCodeAttr.emitNew(this.type);
    localCodeAttr.emitDup(1);
    Method localMethod = Compilation.getConstructor(this.type, this);
    Object localObject;
    if (this.closureEnvField != null)
    {
      localObject = outerLambda();
      if (Compilation.defaultCallConvention >= 2) {
        break label91;
      }
      localObject = getOwningLambda().heapFrame;
      if (localObject != null) {
        break label114;
      }
      localCodeAttr.emitPushThis();
    }
    for (;;)
    {
      localCodeAttr.emitInvokeSpecial(localMethod);
      paramTarget.compileFromStack(paramCompilation, getCompiledClassType(paramCompilation));
      return;
      label91:
      if (((LambdaExp)localObject).heapFrame != null)
      {
        localObject = ((LambdaExp)localObject).heapFrame;
        break;
      }
      localObject = ((LambdaExp)localObject).closureEnv;
      break;
      label114:
      localCodeAttr.emitLoad((Variable)localObject);
    }
  }
  
  public Type getType()
  {
    return this.type;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitObjectExp(this, paramD);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ObjectExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */