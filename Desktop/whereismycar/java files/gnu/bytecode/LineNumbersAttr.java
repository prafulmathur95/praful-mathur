package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class LineNumbersAttr
  extends Attribute
{
  int linenumber_count;
  short[] linenumber_table;
  
  public LineNumbersAttr(CodeAttr paramCodeAttr)
  {
    super("LineNumberTable");
    addToFrontOf(paramCodeAttr);
    paramCodeAttr.lines = this;
  }
  
  public LineNumbersAttr(short[] paramArrayOfShort, CodeAttr paramCodeAttr)
  {
    this(paramCodeAttr);
    this.linenumber_table = paramArrayOfShort;
    this.linenumber_count = (paramArrayOfShort.length >> 1);
  }
  
  public final int getLength()
  {
    return this.linenumber_count * 4 + 2;
  }
  
  public int getLineCount()
  {
    return this.linenumber_count;
  }
  
  public short[] getLineNumberTable()
  {
    return this.linenumber_table;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter)
  {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", count: ");
    paramClassTypeWriter.println(this.linenumber_count);
    int i = 0;
    while (i < this.linenumber_count)
    {
      paramClassTypeWriter.print("  line: ");
      paramClassTypeWriter.print(this.linenumber_table[(i * 2 + 1)] & 0xFFFF);
      paramClassTypeWriter.print(" at pc: ");
      paramClassTypeWriter.println(this.linenumber_table[(i * 2)] & 0xFFFF);
      i += 1;
    }
  }
  
  public void put(int paramInt1, int paramInt2)
  {
    if (this.linenumber_table == null) {
      this.linenumber_table = new short[32];
    }
    for (;;)
    {
      this.linenumber_table[(this.linenumber_count * 2)] = ((short)paramInt2);
      this.linenumber_table[(this.linenumber_count * 2 + 1)] = ((short)paramInt1);
      this.linenumber_count += 1;
      return;
      if (this.linenumber_count * 2 >= this.linenumber_table.length)
      {
        short[] arrayOfShort = new short[this.linenumber_table.length * 2];
        System.arraycopy(this.linenumber_table, 0, arrayOfShort, 0, this.linenumber_count * 2);
        this.linenumber_table = arrayOfShort;
      }
    }
  }
  
  public void write(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    paramDataOutputStream.writeShort(this.linenumber_count);
    int j = this.linenumber_count;
    int i = 0;
    while (i < j * 2)
    {
      paramDataOutputStream.writeShort(this.linenumber_table[i]);
      i += 1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\LineNumbersAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */