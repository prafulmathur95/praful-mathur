package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class ExceptionsAttr
  extends Attribute
{
  short[] exception_table;
  ClassType[] exceptions;
  
  public ExceptionsAttr(Method paramMethod)
  {
    super("Exceptions");
    addToFrontOf(paramMethod);
  }
  
  public void assignConstants(ClassType paramClassType)
  {
    super.assignConstants(paramClassType);
    paramClassType = paramClassType.getConstants();
    int i = this.exceptions.length;
    this.exception_table = new short[i];
    i -= 1;
    while (i >= 0)
    {
      this.exception_table[i] = ((short)paramClassType.addClass(this.exceptions[i]).index);
      i -= 1;
    }
  }
  
  public final ClassType[] getExceptions()
  {
    return this.exceptions;
  }
  
  public final int getLength()
  {
    if (this.exceptions == null) {}
    for (int i = 0;; i = this.exceptions.length) {
      return i * 2 + 2;
    }
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter)
  {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", count: ");
    int j = this.exceptions.length;
    paramClassTypeWriter.println(j);
    int i = 0;
    while (i < j)
    {
      int k = this.exception_table[i] & 0xFFFF;
      paramClassTypeWriter.print("  ");
      paramClassTypeWriter.printOptionalIndex(k);
      paramClassTypeWriter.printConstantTersely(k, 7);
      paramClassTypeWriter.println();
      i += 1;
    }
  }
  
  public void setExceptions(ClassType[] paramArrayOfClassType)
  {
    this.exceptions = paramArrayOfClassType;
  }
  
  public void setExceptions(short[] paramArrayOfShort, ClassType paramClassType)
  {
    this.exception_table = paramArrayOfShort;
    this.exceptions = new ClassType[paramArrayOfShort.length];
    paramClassType = paramClassType.getConstants();
    int i = paramArrayOfShort.length - 1;
    while (i >= 0)
    {
      this.exceptions[i] = ((ClassType)((CpoolClass)paramClassType.getPoolEntry(paramArrayOfShort[i])).getClassType());
      i -= 1;
    }
  }
  
  public void write(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    int j = this.exceptions.length;
    paramDataOutputStream.writeShort(j);
    int i = 0;
    while (i < j)
    {
      paramDataOutputStream.writeShort(this.exception_table[i]);
      i += 1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\ExceptionsAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */