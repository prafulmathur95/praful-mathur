package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class MiscAttr
  extends Attribute
{
  byte[] data;
  int dataLength;
  int offset;
  
  public MiscAttr(String paramString, byte[] paramArrayOfByte)
  {
    this(paramString, paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public MiscAttr(String paramString, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    super(paramString);
    this.data = paramArrayOfByte;
    this.offset = paramInt1;
    this.dataLength = paramInt2;
  }
  
  public int getLength()
  {
    return this.dataLength;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter)
  {
    super.print(paramClassTypeWriter);
    int k = getLength();
    int i = 0;
    while (i < k)
    {
      int j = this.data[i];
      if (i % 20 == 0) {
        paramClassTypeWriter.print(' ');
      }
      paramClassTypeWriter.print(' ');
      paramClassTypeWriter.print(Character.forDigit(j >> 4 & 0xF, 16));
      paramClassTypeWriter.print(Character.forDigit(j & 0xF, 16));
      j = i + 1;
      if (j % 20 != 0)
      {
        i = j;
        if (j != k) {}
      }
      else
      {
        paramClassTypeWriter.println();
        i = j;
      }
    }
  }
  
  protected void put1(int paramInt)
  {
    if (this.data == null) {
      this.data = new byte[20];
    }
    for (;;)
    {
      byte[] arrayOfByte = this.data;
      int i = this.dataLength;
      this.dataLength = (i + 1);
      arrayOfByte[i] = ((byte)paramInt);
      return;
      if (this.dataLength >= this.data.length)
      {
        arrayOfByte = new byte[this.data.length * 2];
        System.arraycopy(this.data, 0, arrayOfByte, 0, this.dataLength);
        this.data = arrayOfByte;
      }
    }
  }
  
  protected void put2(int paramInt)
  {
    put1((byte)(paramInt >> 8));
    put1((byte)paramInt);
  }
  
  protected void put2(int paramInt1, int paramInt2)
  {
    this.data[paramInt1] = ((byte)(paramInt2 >> 8));
    this.data[(paramInt1 + 1)] = ((byte)paramInt2);
  }
  
  protected int u1()
  {
    int i = this.offset;
    this.offset = (i + 1);
    return u1(i);
  }
  
  protected int u1(int paramInt)
  {
    return this.data[paramInt] & 0xFF;
  }
  
  protected int u2()
  {
    int i = u2(this.offset);
    this.offset += 2;
    return i;
  }
  
  protected int u2(int paramInt)
  {
    return ((this.data[paramInt] & 0xFF) << 8) + (this.data[(paramInt + 1)] & 0xFF);
  }
  
  public void write(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    paramDataOutputStream.write(this.data, this.offset, this.dataLength);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\MiscAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */