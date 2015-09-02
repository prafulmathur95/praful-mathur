package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolNameAndType
  extends CpoolEntry
{
  CpoolUtf8 name;
  CpoolUtf8 type;
  
  CpoolNameAndType() {}
  
  CpoolNameAndType(ConstantPool paramConstantPool, int paramInt, CpoolUtf8 paramCpoolUtf81, CpoolUtf8 paramCpoolUtf82)
  {
    super(paramConstantPool, paramInt);
    this.name = paramCpoolUtf81;
    this.type = paramCpoolUtf82;
  }
  
  static final int hashCode(CpoolUtf8 paramCpoolUtf81, CpoolUtf8 paramCpoolUtf82)
  {
    return paramCpoolUtf81.hashCode() ^ paramCpoolUtf82.hashCode();
  }
  
  public final CpoolUtf8 getName()
  {
    return this.name;
  }
  
  public int getTag()
  {
    return 12;
  }
  
  public final CpoolUtf8 getType()
  {
    return this.type;
  }
  
  public int hashCode()
  {
    if (this.hash == 0) {
      this.hash = hashCode(this.name, this.type);
    }
    return this.hash;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter, int paramInt)
  {
    if (paramInt == 1)
    {
      paramClassTypeWriter.print("NameAndType ");
      paramClassTypeWriter.printName(this.name.string);
      if (paramInt > 1) {
        break label67;
      }
      paramClassTypeWriter.print(' ');
    }
    for (;;)
    {
      paramClassTypeWriter.printSignature(this.type.string);
      return;
      if (paramInt <= 1) {
        break;
      }
      paramClassTypeWriter.print("NameAndType name: ");
      paramClassTypeWriter.printOptionalIndex(this.name);
      break;
      label67:
      paramClassTypeWriter.print(", signature: ");
      paramClassTypeWriter.printOptionalIndex(this.type);
    }
  }
  
  void write(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    paramDataOutputStream.writeByte(12);
    paramDataOutputStream.writeShort(this.name.index);
    paramDataOutputStream.writeShort(this.type.index);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\CpoolNameAndType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */