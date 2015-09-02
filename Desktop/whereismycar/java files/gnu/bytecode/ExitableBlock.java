package gnu.bytecode;

public class ExitableBlock
{
  CodeAttr code;
  TryState currentTryState;
  Label endLabel;
  TryState initialTryState;
  ExitableBlock nextCase;
  ExitableBlock outer;
  Type resultType;
  Variable resultVariable;
  int switchCase;
  
  ExitableBlock(Type paramType, CodeAttr paramCodeAttr, boolean paramBoolean)
  {
    this.code = paramCodeAttr;
    this.resultType = paramType;
    this.initialTryState = paramCodeAttr.try_stack;
    if ((paramBoolean) && (paramType != null))
    {
      paramCodeAttr.pushScope();
      paramType = paramCodeAttr.addLocal(paramType);
      this.resultVariable = paramType;
      paramCodeAttr.emitStoreDefaultValue(paramType);
      int i = paramCodeAttr.exitableBlockLevel + 1;
      paramCodeAttr.exitableBlockLevel = i;
      this.switchCase = i;
    }
    this.endLabel = new Label(paramCodeAttr);
  }
  
  public void exit()
  {
    if (this.resultVariable != null) {
      this.code.emitStore(this.resultVariable);
    }
    exit(TryState.outerHandler(this.code.try_stack, this.initialTryState));
  }
  
  void exit(TryState paramTryState)
  {
    if (paramTryState == this.initialTryState)
    {
      this.code.emitGoto(this.endLabel);
      return;
    }
    if (this.code.useJsr())
    {
      for (paramTryState = this.code.try_stack; paramTryState != this.initialTryState; paramTryState = paramTryState.previous) {
        if ((paramTryState.finally_subr != null) && (paramTryState.finally_ret_addr == null)) {
          this.code.emitJsr(paramTryState.finally_subr);
        }
      }
      this.code.emitGoto(this.endLabel);
      return;
    }
    if (this.currentTryState == null) {
      linkCase(paramTryState);
    }
    if (paramTryState.saved_result != null) {
      this.code.emitStoreDefaultValue(paramTryState.saved_result);
    }
    this.code.emitPushInt(this.switchCase);
    this.code.emitPushNull();
    this.code.emitGoto(paramTryState.finally_subr);
  }
  
  public Label exitIsGoto()
  {
    if (TryState.outerHandler(this.code.try_stack, this.initialTryState) == this.initialTryState) {
      return this.endLabel;
    }
    return null;
  }
  
  void finish()
  {
    if ((this.resultVariable != null) && (this.code.reachableHere())) {
      this.code.emitStore(this.resultVariable);
    }
    this.endLabel.define(this.code);
    if (this.resultVariable != null)
    {
      this.code.emitLoad(this.resultVariable);
      this.code.popScope();
      CodeAttr localCodeAttr = this.code;
      localCodeAttr.exitableBlockLevel -= 1;
    }
  }
  
  void linkCase(TryState paramTryState)
  {
    if (this.currentTryState != paramTryState)
    {
      if (this.currentTryState != null) {
        throw new Error();
      }
      this.nextCase = paramTryState.exitCases;
      paramTryState.exitCases = this;
      this.currentTryState = paramTryState;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\ExitableBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */