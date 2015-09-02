package gnu.bytecode;

public class SwitchState
{
  Label after_label;
  Label cases_label;
  Label defaultLabel;
  Label[] labels;
  int maxValue;
  int minValue;
  int numCases;
  TryState outerTry;
  Label switch_label;
  int[] values;
  
  public SwitchState(CodeAttr paramCodeAttr)
  {
    this.switch_label = new Label(paramCodeAttr);
    this.cases_label = new Label(paramCodeAttr);
    this.after_label = new Label(paramCodeAttr);
    this.outerTry = paramCodeAttr.try_stack;
    this.numCases = 0;
  }
  
  public boolean addCase(int paramInt, CodeAttr paramCodeAttr)
  {
    Label localLabel = new Label(paramCodeAttr);
    localLabel.setTypes(this.cases_label);
    localLabel.define(paramCodeAttr);
    return insertCase(paramInt, localLabel, paramCodeAttr);
  }
  
  public boolean addCaseGoto(int paramInt, CodeAttr paramCodeAttr, Label paramLabel)
  {
    boolean bool = insertCase(paramInt, paramLabel, paramCodeAttr);
    paramLabel.setTypes(this.cases_label);
    paramCodeAttr.setUnreachable();
    return bool;
  }
  
  public void addDefault(CodeAttr paramCodeAttr)
  {
    Label localLabel = new Label(paramCodeAttr);
    localLabel.setTypes(this.cases_label);
    localLabel.define(paramCodeAttr);
    if (this.defaultLabel != null) {
      throw new Error();
    }
    this.defaultLabel = localLabel;
  }
  
  public void exitSwitch(CodeAttr paramCodeAttr)
  {
    if (this.outerTry != paramCodeAttr.try_stack) {
      throw new Error("exitSwitch cannot exit through a try");
    }
    paramCodeAttr.emitGoto(this.after_label);
  }
  
  public void finish(CodeAttr paramCodeAttr)
  {
    Object localObject;
    if (this.defaultLabel == null)
    {
      this.defaultLabel = new Label(paramCodeAttr);
      this.defaultLabel.define(paramCodeAttr);
      localObject = ClassType.make("java.lang.RuntimeException");
      paramCodeAttr.emitNew((ClassType)localObject);
      paramCodeAttr.emitDup((Type)localObject);
      paramCodeAttr.emitPushString("bad case value!");
      ClassType localClassType = Type.string_type;
      PrimType localPrimType = Type.voidType;
      paramCodeAttr.emitInvokeSpecial(((ClassType)localObject).addMethod("<init>", 1, new Type[] { localClassType }, localPrimType));
      paramCodeAttr.emitThrow();
    }
    paramCodeAttr.fixupChain(this.switch_label, this.after_label);
    if (this.numCases <= 1)
    {
      paramCodeAttr.pushType(Type.intType);
      if (this.numCases == 1) {
        if (this.minValue == 0)
        {
          paramCodeAttr.emitIfIntEqZero();
          paramCodeAttr.emitGoto(this.labels[0]);
          paramCodeAttr.emitElse();
          paramCodeAttr.emitGoto(this.defaultLabel);
          paramCodeAttr.emitFi();
        }
      }
    }
    for (;;)
    {
      paramCodeAttr.fixupChain(this.after_label, this.cases_label);
      return;
      paramCodeAttr.emitPushInt(this.minValue);
      paramCodeAttr.emitIfEq();
      break;
      paramCodeAttr.emitPop(1);
      paramCodeAttr.emitGoto(this.defaultLabel);
      continue;
      if (this.numCases * 2 >= this.maxValue - this.minValue)
      {
        paramCodeAttr.reserve((this.maxValue - this.minValue + 1) * 4 + 13);
        paramCodeAttr.fixupAdd(2, null);
        paramCodeAttr.put1(170);
        paramCodeAttr.fixupAdd(3, this.defaultLabel);
        paramCodeAttr.PC += 4;
        paramCodeAttr.put4(this.minValue);
        paramCodeAttr.put4(this.maxValue);
        int j = 0;
        i = this.minValue;
        label294:
        if (i <= this.maxValue)
        {
          if (this.values[j] != i) {
            break label354;
          }
          localObject = this.labels[j];
          j += 1;
        }
        for (;;)
        {
          paramCodeAttr.fixupAdd(3, (Label)localObject);
          paramCodeAttr.PC += 4;
          i += 1;
          break label294;
          break;
          label354:
          localObject = this.defaultLabel;
        }
      }
      paramCodeAttr.reserve(this.numCases * 8 + 9);
      paramCodeAttr.fixupAdd(2, null);
      paramCodeAttr.put1(171);
      paramCodeAttr.fixupAdd(3, this.defaultLabel);
      paramCodeAttr.PC += 4;
      paramCodeAttr.put4(this.numCases);
      int i = 0;
      while (i < this.numCases)
      {
        paramCodeAttr.put4(this.values[i]);
        paramCodeAttr.fixupAdd(3, this.labels[i]);
        paramCodeAttr.PC += 4;
        i += 1;
      }
    }
  }
  
  public int getMaxValue()
  {
    return this.maxValue;
  }
  
  public int getNumCases()
  {
    return this.numCases;
  }
  
  public boolean insertCase(int paramInt, Label paramLabel, CodeAttr paramCodeAttr)
  {
    if (this.values == null)
    {
      this.values = new int[10];
      this.labels = new Label[10];
      this.numCases = 1;
      this.maxValue = paramInt;
      this.minValue = paramInt;
      this.values[0] = paramInt;
      this.labels[0] = paramLabel;
      return true;
    }
    paramCodeAttr = this.values;
    Label[] arrayOfLabel = this.labels;
    if (this.numCases >= this.values.length)
    {
      this.values = new int[this.numCases * 2];
      this.labels = new Label[this.numCases * 2];
    }
    int j;
    if (paramInt < this.minValue)
    {
      j = 0;
      this.minValue = paramInt;
    }
    int i;
    do
    {
      for (;;)
      {
        i = this.numCases - j;
        System.arraycopy(paramCodeAttr, j, this.values, j + 1, i);
        System.arraycopy(paramCodeAttr, 0, this.values, 0, j);
        this.values[j] = paramInt;
        System.arraycopy(arrayOfLabel, j, this.labels, j + 1, i);
        System.arraycopy(arrayOfLabel, 0, this.labels, 0, j);
        this.labels[j] = paramLabel;
        this.numCases += 1;
        return true;
        if (paramInt <= this.maxValue) {
          break;
        }
        j = this.numCases;
        this.maxValue = paramInt;
      }
      int k = 0;
      j = this.numCases - 1;
      i = 0;
      while (k <= j)
      {
        i = k + j >>> 1;
        if (paramCodeAttr[i] >= paramInt)
        {
          j = i - 1;
        }
        else
        {
          i += 1;
          k = i;
        }
      }
      j = i;
    } while (paramInt != paramCodeAttr[i]);
    return false;
  }
  
  public void switchValuePushed(CodeAttr paramCodeAttr)
  {
    paramCodeAttr.popType();
    this.cases_label.setTypes(paramCodeAttr);
    paramCodeAttr.fixupChain(this.cases_label, this.switch_label);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\SwitchState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */