package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolClass
  extends CpoolEntry
{
  ObjectType clas;
  CpoolUtf8 name;
  
  CpoolClass() {}
  
  CpoolClass(ConstantPool paramConstantPool, int paramInt, CpoolUtf8 paramCpoolUtf8)
  {
    super(paramConstantPool, paramInt);
    this.name = paramCpoolUtf8;
  }
  
  static final int hashCode(CpoolUtf8 paramCpoolUtf8)
  {
    return paramCpoolUtf8.hashCode() ^ 0xF0F;
  }
  
  public final String getClassName()
  {
    return this.name.string.replace('/', '.');
  }
  
  public final ObjectType getClassType()
  {
    Object localObject = this.clas;
    if (localObject != null) {
      return (ObjectType)localObject;
    }
    localObject = this.name.string;
    if (((String)localObject).charAt(0) == '[') {}
    for (localObject = (ObjectType)Type.signatureToType((String)localObject);; localObject = ClassType.make(((String)localObject).replace('/', '.')))
    {
      this.clas = ((ObjectType)localObject);
      return (ObjectType)localObject;
    }
  }
  
  public final CpoolUtf8 getName()
  {
    return this.name;
  }
  
  public final String getStringName()
  {
    return this.name.string;
  }
  
  public int getTag()
  {
    return 7;
  }
  
  public int hashCode()
  {
    if (this.hash == 0) {
      this.hash = hashCode(this.name);
    }
    return this.hash;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter, int paramInt)
  {
    if (paramInt == 1) {
      paramClassTypeWriter.print("Class ");
    }
    String str;
    for (;;)
    {
      str = this.name.string;
      paramInt = str.length();
      if ((paramInt <= 1) || (str.charAt(0) != '[')) {
        break;
      }
      Type.printSignature(str, 0, paramInt, paramClassTypeWriter);
      return;
      if (paramInt > 1)
      {
        paramClassTypeWriter.print("Class name: ");
        paramClassTypeWriter.printOptionalIndex(this.name);
      }
    }
    paramClassTypeWriter.print(str.replace('/', '.'));
  }
  
  void write(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    paramDataOutputStream.writeByte(7);
    paramDataOutputStream.writeShort(this.name.index);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\CpoolClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */