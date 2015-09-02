package gnu.bytecode;

public class Scope
{
  Label end;
  Scope firstChild;
  Scope lastChild;
  Variable last_var;
  Scope nextSibling;
  Scope parent;
  boolean preserved;
  Label start;
  Variable vars;
  
  public Scope() {}
  
  public Scope(Label paramLabel1, Label paramLabel2)
  {
    this.start = paramLabel1;
    this.end = paramLabel2;
  }
  
  static boolean equals(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    if (paramArrayOfByte1.length != paramArrayOfByte2.length) {
      return false;
    }
    if (paramArrayOfByte1 == paramArrayOfByte2) {
      return true;
    }
    int i = paramArrayOfByte1.length;
    int j;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
    } while (paramArrayOfByte1[j] == paramArrayOfByte2[j]);
    return false;
    return true;
  }
  
  public Variable addVariable(CodeAttr paramCodeAttr, Type paramType, String paramString)
  {
    paramType = new Variable(paramString, paramType);
    addVariable(paramCodeAttr, paramType);
    return paramType;
  }
  
  public void addVariable(CodeAttr paramCodeAttr, Variable paramVariable)
  {
    addVariable(paramVariable);
    if ((paramVariable.isSimple()) && (paramCodeAttr != null)) {
      paramVariable.allocateLocal(paramCodeAttr);
    }
  }
  
  public void addVariable(Variable paramVariable)
  {
    if (this.last_var == null) {
      this.vars = paramVariable;
    }
    for (;;)
    {
      this.last_var = paramVariable;
      paramVariable.scope = this;
      return;
      this.last_var.next = paramVariable;
    }
  }
  
  public void addVariableAfter(Variable paramVariable1, Variable paramVariable2)
  {
    if (paramVariable1 == null)
    {
      paramVariable2.next = this.vars;
      this.vars = paramVariable2;
    }
    for (;;)
    {
      if (this.last_var == paramVariable1) {
        this.last_var = paramVariable2;
      }
      if (paramVariable2.next != paramVariable2) {
        break;
      }
      throw new Error("cycle");
      paramVariable2.next = paramVariable1.next;
      paramVariable1.next = paramVariable2;
    }
    paramVariable2.scope = this;
  }
  
  public VarEnumerator allVars()
  {
    return new VarEnumerator(this);
  }
  
  public final Variable firstVar()
  {
    return this.vars;
  }
  
  void freeLocals(CodeAttr paramCodeAttr)
  {
    if (this.preserved) {}
    for (;;)
    {
      return;
      for (Object localObject = this.vars; localObject != null; localObject = ((Variable)localObject).next) {
        if ((((Variable)localObject).isSimple()) && (!((Variable)localObject).dead())) {
          ((Variable)localObject).freeLocal(paramCodeAttr);
        }
      }
      for (localObject = this.firstChild; localObject != null; localObject = ((Scope)localObject).nextSibling) {
        if (((Scope)localObject).preserved)
        {
          ((Scope)localObject).preserved = false;
          ((Scope)localObject).freeLocals(paramCodeAttr);
        }
      }
    }
  }
  
  public Variable getVariable(int paramInt)
  {
    for (Variable localVariable = this.vars;; localVariable = localVariable.next)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
    }
    return localVariable;
  }
  
  public void linkChild(Scope paramScope)
  {
    this.parent = paramScope;
    if (paramScope == null) {
      return;
    }
    if (paramScope.lastChild == null) {
      paramScope.firstChild = this;
    }
    for (;;)
    {
      paramScope.lastChild = this;
      return;
      paramScope.lastChild.nextSibling = this;
    }
  }
  
  public Variable lookup(String paramString)
  {
    for (Variable localVariable = this.vars; localVariable != null; localVariable = localVariable.next) {
      if (paramString.equals(localVariable.name)) {
        return localVariable;
      }
    }
    return null;
  }
  
  public void noteStartFunction(CodeAttr paramCodeAttr)
  {
    setStartPC(paramCodeAttr);
    this.start.setTypes(paramCodeAttr);
  }
  
  public void setStartPC(CodeAttr paramCodeAttr)
  {
    if (this.start == null) {
      this.start = new Label();
    }
    boolean bool = paramCodeAttr.reachableHere();
    this.start.define(paramCodeAttr);
    paramCodeAttr.setReachable(bool);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\Scope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */