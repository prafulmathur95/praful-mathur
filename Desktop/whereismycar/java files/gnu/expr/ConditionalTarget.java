package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Type;

public class ConditionalTarget
  extends Target
{
  public Label ifFalse;
  public Label ifTrue;
  Language language;
  public boolean trueBranchComesFirst = true;
  
  public ConditionalTarget(Label paramLabel1, Label paramLabel2, Language paramLanguage)
  {
    this.ifTrue = paramLabel1;
    this.ifFalse = paramLabel2;
    this.language = paramLanguage;
  }
  
  public void compileFromStack(Compilation paramCompilation, Type paramType)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    switch (paramType.getSignature().charAt(0))
    {
    default: 
      if (this.trueBranchComesFirst)
      {
        localCodeAttr.emitGotoIfIntEqZero(this.ifFalse);
        localCodeAttr.emitGoto(this.ifTrue);
        return;
      }
      break;
    case 'J': 
      localCodeAttr.emitPushLong(0L);
      if (this.trueBranchComesFirst) {
        localCodeAttr.emitGotoIfEq(this.ifFalse);
      }
      break;
    }
    for (;;)
    {
      emitGotoFirstBranch(localCodeAttr);
      return;
      localCodeAttr.emitPushDouble(0.0D);
      break;
      localCodeAttr.emitPushFloat(0.0F);
      break;
      localCodeAttr.emitGotoIfIntNeZero(this.ifTrue);
      localCodeAttr.emitGoto(this.ifFalse);
      return;
      if (this.language == null) {}
      for (paramType = Boolean.FALSE;; paramType = this.language.booleanObject(false))
      {
        paramCompilation.compileConstant(paramType);
        break;
      }
      localCodeAttr.emitGotoIfNE(this.ifTrue);
    }
  }
  
  public final void emitGotoFirstBranch(CodeAttr paramCodeAttr)
  {
    if (this.trueBranchComesFirst) {}
    for (Label localLabel = this.ifTrue;; localLabel = this.ifFalse)
    {
      paramCodeAttr.emitGoto(localLabel);
      return;
    }
  }
  
  public Type getType()
  {
    return Type.booleanType;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ConditionalTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */