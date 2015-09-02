package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class EnclosingMethodAttr
  extends Attribute
{
  int class_index;
  Method method;
  int method_index;
  
  public EnclosingMethodAttr(int paramInt1, int paramInt2, ClassType paramClassType)
  {
    this(paramClassType);
    this.class_index = paramInt1;
    this.method_index = paramInt2;
  }
  
  public EnclosingMethodAttr(ClassType paramClassType)
  {
    super("EnclosingMethod");
    addToFrontOf(paramClassType);
  }
  
  public static EnclosingMethodAttr getFirstEnclosingMethod(Attribute paramAttribute)
  {
    for (;;)
    {
      if ((paramAttribute == null) || ((paramAttribute instanceof EnclosingMethodAttr))) {
        return (EnclosingMethodAttr)paramAttribute;
      }
      paramAttribute = paramAttribute.next;
    }
  }
  
  public void assignConstants(ClassType paramClassType)
  {
    super.assignConstants(paramClassType);
    if (this.method != null)
    {
      paramClassType = paramClassType.getConstants();
      this.class_index = paramClassType.addClass(this.method.getDeclaringClass()).getIndex();
      this.method_index = paramClassType.addNameAndType(this.method).getIndex();
    }
  }
  
  public int getLength()
  {
    return 4;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter)
  {
    ConstantPool localConstantPool = ((ClassType)this.container).getConstants();
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.println(getLength());
    paramClassTypeWriter.print("  class: ");
    paramClassTypeWriter.printOptionalIndex(this.class_index);
    paramClassTypeWriter.print(((CpoolClass)localConstantPool.getForced(this.class_index, 7)).getStringName());
    paramClassTypeWriter.print(", method: ");
    paramClassTypeWriter.printOptionalIndex(this.method_index);
    localConstantPool.getForced(this.method_index, 12).print(paramClassTypeWriter, 0);
    paramClassTypeWriter.println();
  }
  
  public void write(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    paramDataOutputStream.writeShort(this.class_index);
    paramDataOutputStream.writeShort(this.method_index);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\EnclosingMethodAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */