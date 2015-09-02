package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class LocalVarsAttr
  extends Attribute
{
  public Scope current_scope;
  private Method method;
  Scope parameter_scope;
  Variable[] used;
  
  public LocalVarsAttr(CodeAttr paramCodeAttr)
  {
    super("LocalVariableTable");
    addToFrontOf(paramCodeAttr);
    this.method = ((Method)paramCodeAttr.getContainer());
    paramCodeAttr.locals = this;
  }
  
  public LocalVarsAttr(Method paramMethod)
  {
    super("LocalVariableTable");
    CodeAttr localCodeAttr = paramMethod.code;
    this.method = paramMethod;
    localCodeAttr.locals = this;
  }
  
  public VarEnumerator allVars()
  {
    return new VarEnumerator(this.parameter_scope);
  }
  
  public void assignConstants(ClassType paramClassType)
  {
    super.assignConstants(paramClassType);
    VarEnumerator localVarEnumerator = allVars();
    for (;;)
    {
      Variable localVariable = localVarEnumerator.nextVar();
      if (localVariable == null) {
        break;
      }
      if ((localVariable.isSimple()) && (localVariable.name != null))
      {
        if (localVariable.name_index == 0) {
          localVariable.name_index = paramClassType.getConstants().addUtf8(localVariable.getName()).index;
        }
        if (localVariable.signature_index == 0) {
          localVariable.signature_index = paramClassType.getConstants().addUtf8(localVariable.getType().getSignature()).index;
        }
      }
    }
  }
  
  public void enterScope(Scope paramScope)
  {
    paramScope.linkChild(this.current_scope);
    this.current_scope = paramScope;
    CodeAttr localCodeAttr = this.method.getCode();
    paramScope = paramScope.firstVar();
    if (paramScope != null)
    {
      if (paramScope.isSimple())
      {
        if (paramScope.isAssigned()) {
          break label57;
        }
        paramScope.allocateLocal(localCodeAttr);
      }
      label57:
      label82:
      do
      {
        for (;;)
        {
          paramScope = paramScope.nextVar();
          break;
          if (this.used[paramScope.offset] != null) {
            break label82;
          }
          this.used[paramScope.offset] = paramScope;
        }
      } while (this.used[paramScope.offset] == paramScope);
      throw new Error("inconsistent local variable assignments for " + paramScope + " != " + this.used[paramScope.offset]);
    }
  }
  
  public final int getCount()
  {
    int i = 0;
    VarEnumerator localVarEnumerator = allVars();
    for (;;)
    {
      Variable localVariable = localVarEnumerator.nextVar();
      if (localVariable == null) {
        break;
      }
      if (localVariable.shouldEmit()) {
        i += 1;
      }
    }
    return i;
  }
  
  public final int getLength()
  {
    return getCount() * 10 + 2;
  }
  
  public final Method getMethod()
  {
    return this.method;
  }
  
  public final boolean isEmpty()
  {
    VarEnumerator localVarEnumerator = allVars();
    Variable localVariable;
    do
    {
      localVariable = localVarEnumerator.nextVar();
      if (localVariable == null) {
        break;
      }
    } while ((!localVariable.isSimple()) || (localVariable.name == null));
    return false;
    return true;
  }
  
  public void preserveVariablesUpto(Scope paramScope)
  {
    for (Scope localScope = this.current_scope; localScope != paramScope; localScope = localScope.parent) {
      localScope.preserved = true;
    }
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter)
  {
    VarEnumerator localVarEnumerator = allVars();
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", count: ");
    paramClassTypeWriter.println(getCount());
    localVarEnumerator.reset();
    do
    {
      localObject = localVarEnumerator.nextVar();
      if (localObject == null) {
        break;
      }
    } while ((!((Variable)localObject).isSimple()) || (((Variable)localObject).name == null));
    paramClassTypeWriter.print("  slot#");
    paramClassTypeWriter.print(((Variable)localObject).offset);
    paramClassTypeWriter.print(": name: ");
    paramClassTypeWriter.printOptionalIndex(((Variable)localObject).name_index);
    paramClassTypeWriter.print(((Variable)localObject).getName());
    paramClassTypeWriter.print(", type: ");
    paramClassTypeWriter.printOptionalIndex(((Variable)localObject).signature_index);
    paramClassTypeWriter.printSignature(((Variable)localObject).getType());
    paramClassTypeWriter.print(" (pc: ");
    Object localObject = ((Variable)localObject).scope;
    int i;
    int j;
    if ((localObject != null) && (((Scope)localObject).start != null) && (((Scope)localObject).end != null))
    {
      i = ((Scope)localObject).start.position;
      if (i >= 0)
      {
        j = ((Scope)localObject).end.position;
        if (j >= 0) {
          break label204;
        }
      }
    }
    paramClassTypeWriter.print("unknown");
    for (;;)
    {
      paramClassTypeWriter.println(')');
      break;
      label204:
      paramClassTypeWriter.print(i);
      paramClassTypeWriter.print(" length: ");
      paramClassTypeWriter.print(j - i);
    }
  }
  
  public void write(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    VarEnumerator localVarEnumerator = allVars();
    paramDataOutputStream.writeShort(getCount());
    localVarEnumerator.reset();
    for (;;)
    {
      Variable localVariable = localVarEnumerator.nextVar();
      if (localVariable == null) {
        break;
      }
      if (localVariable.shouldEmit())
      {
        Scope localScope = localVariable.scope;
        int i = localScope.start.position;
        int j = localScope.end.position;
        paramDataOutputStream.writeShort(i);
        paramDataOutputStream.writeShort(j - i);
        paramDataOutputStream.writeShort(localVariable.name_index);
        paramDataOutputStream.writeShort(localVariable.signature_index);
        paramDataOutputStream.writeShort(localVariable.offset);
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\LocalVarsAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */