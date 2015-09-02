package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.OutPort;

public class FluidLetExp
  extends LetExp
{
  public FluidLetExp(Expression[] paramArrayOfExpression)
  {
    super(paramArrayOfExpression);
  }
  
  private void doInits(Declaration paramDeclaration, int paramInt, Variable[] paramArrayOfVariable, Compilation paramCompilation, Variable paramVariable)
  {
    if (paramInt >= this.inits.length) {
      return;
    }
    CodeAttr localCodeAttr = paramCompilation.getCode();
    paramArrayOfVariable[paramInt] = localCodeAttr.addLocal(Type.pointer_type);
    paramDeclaration.allocateVariable(localCodeAttr);
    paramDeclaration.base.load(null, 2, paramCompilation, Target.pushObject);
    localCodeAttr.emitDup();
    localCodeAttr.emitStore(paramDeclaration.getVariable());
    this.inits[paramInt].compile(paramCompilation, Target.pushObject);
    doInits(paramDeclaration.nextDecl(), paramInt + 1, paramArrayOfVariable, paramCompilation, paramVariable);
    localCodeAttr.emitInvokeVirtual(Compilation.typeLocation.getDeclaredMethod("setWithSave", 1));
    localCodeAttr.emitStore(paramArrayOfVariable[paramInt]);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Type localType;
    Object localObject1;
    if ((paramTarget instanceof IgnoreTarget))
    {
      localType = null;
      if (localType != null) {
        break label185;
      }
      localObject1 = Target.Ignore;
    }
    for (;;)
    {
      Object localObject2 = getVarScope();
      localCodeAttr.enterScope((Scope)localObject2);
      Variable localVariable = ((Scope)localObject2).addVariable(localCodeAttr, Compilation.typeCallContext, null);
      paramCompilation.loadCallContext();
      localCodeAttr.emitStore(localVariable);
      Variable[] arrayOfVariable = new Variable[this.inits.length];
      localObject2 = firstDecl();
      doInits((Declaration)localObject2, 0, arrayOfVariable, paramCompilation, localVariable);
      localCodeAttr.emitTryStart(true, localType);
      this.body.compileWithPosition(paramCompilation, (Target)localObject1);
      localCodeAttr.emitFinallyStart();
      int i = 0;
      for (localObject1 = localObject2; i < this.inits.length; localObject1 = ((Declaration)localObject1).nextDecl())
      {
        ((Declaration)localObject1).load(null, 2, paramCompilation, Target.pushObject);
        localCodeAttr.emitLoad(arrayOfVariable[i]);
        localCodeAttr.emitInvokeVirtual(Compilation.typeLocation.getDeclaredMethod("setRestore", 1));
        i += 1;
      }
      localType = getType();
      break;
      label185:
      if (localType == Type.pointer_type) {
        localObject1 = Target.pushObject;
      } else {
        localObject1 = new StackTarget(localType);
      }
    }
    localCodeAttr.emitTryCatchEnd();
    popScope(localCodeAttr);
    if (localType != null) {
      paramTarget.compileFromStack(paramCompilation, localType);
    }
  }
  
  protected boolean mustCompile()
  {
    return true;
  }
  
  public void print(OutPort paramOutPort)
  {
    print(paramOutPort, "(FluidLet", ")");
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitFluidLetExp(this, paramD);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\FluidLetExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */