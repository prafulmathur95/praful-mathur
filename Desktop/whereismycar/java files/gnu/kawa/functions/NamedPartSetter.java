package gnu.kawa.functions;

import gnu.mapping.Procedure;
import gnu.mapping.Setter;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

class NamedPartSetter
  extends Setter
  implements Externalizable
{
  public NamedPartSetter(NamedPart paramNamedPart)
  {
    super(paramNamedPart);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateNamedPartSetter");
  }
  
  Procedure getGetter()
  {
    return this.getter;
  }
  
  public int numArgs()
  {
    if (((NamedPart)this.getter).kind == 'D') {
      return 8193;
    }
    return 61440;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.getter = ((Procedure)paramObjectInput.readObject());
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.getter);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\NamedPartSetter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */