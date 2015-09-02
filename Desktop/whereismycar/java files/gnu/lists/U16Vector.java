package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class U16Vector
  extends SimpleVector
  implements Externalizable, Comparable
{
  short[] data;
  
  public U16Vector()
  {
    this.data = S16Vector.empty;
  }
  
  public U16Vector(int paramInt)
  {
    this.data = new short[paramInt];
    this.size = paramInt;
  }
  
  public U16Vector(int paramInt, short paramShort)
  {
    short[] arrayOfShort = new short[paramInt];
    this.data = arrayOfShort;
    this.size = paramInt;
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      arrayOfShort[paramInt] = paramShort;
    }
  }
  
  public U16Vector(Sequence paramSequence)
  {
    this.data = new short[paramSequence.size()];
    addAll(paramSequence);
  }
  
  public U16Vector(short[] paramArrayOfShort)
  {
    this.data = paramArrayOfShort;
    this.size = paramArrayOfShort.length;
  }
  
  protected void clearBuffer(int paramInt1, int paramInt2)
  {
    for (;;)
    {
      paramInt2 -= 1;
      if (paramInt2 < 0) {
        break;
      }
      this.data[paramInt1] = 0;
      paramInt1 += 1;
    }
  }
  
  public int compareTo(Object paramObject)
  {
    return compareToInt(this, (U16Vector)paramObject);
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer)
  {
    paramInt >>>= 1;
    if (paramInt >= this.size) {
      return false;
    }
    paramConsumer.writeInt(this.data[paramInt] & 0xFFFF);
    return true;
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer)
  {
    if (paramConsumer.ignoring()) {}
    for (;;)
    {
      return;
      int i = paramInt1 >>> 1;
      int j = paramInt2 >>> 1;
      paramInt1 = j;
      paramInt2 = i;
      if (j > this.size)
      {
        paramInt1 = this.size;
        paramInt2 = i;
      }
      while (paramInt2 < paramInt1)
      {
        paramConsumer.writeInt(this.data[paramInt2] & 0xFFFF);
        paramInt2 += 1;
      }
    }
  }
  
  public final Object get(int paramInt)
  {
    if (paramInt > this.size) {
      throw new IndexOutOfBoundsException();
    }
    return Convert.toObjectUnsigned(this.data[paramInt]);
  }
  
  protected Object getBuffer()
  {
    return this.data;
  }
  
  public final Object getBuffer(int paramInt)
  {
    return Convert.toObjectUnsigned(this.data[paramInt]);
  }
  
  public int getBufferLength()
  {
    return this.data.length;
  }
  
  public int getElementKind()
  {
    return 19;
  }
  
  public String getTag()
  {
    return "u16";
  }
  
  public final int intAtBuffer(int paramInt)
  {
    return this.data[paramInt] & 0xFFFF;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    int j = paramObjectInput.readInt();
    short[] arrayOfShort = new short[j];
    int i = 0;
    while (i < j)
    {
      arrayOfShort[i] = paramObjectInput.readShort();
      i += 1;
    }
    this.data = arrayOfShort;
    this.size = j;
  }
  
  public Object setBuffer(int paramInt, Object paramObject)
  {
    short s = this.data[paramInt];
    this.data[paramInt] = Convert.toShortUnsigned(paramObject);
    return Convert.toObjectUnsigned(s);
  }
  
  public void setBufferLength(int paramInt)
  {
    int i = this.data.length;
    short[] arrayOfShort1;
    short[] arrayOfShort2;
    if (i != paramInt)
    {
      arrayOfShort1 = new short[paramInt];
      arrayOfShort2 = this.data;
      if (i >= paramInt) {
        break label45;
      }
      paramInt = i;
    }
    label45:
    for (;;)
    {
      System.arraycopy(arrayOfShort2, 0, arrayOfShort1, 0, paramInt);
      this.data = arrayOfShort1;
      return;
    }
  }
  
  public final void setShortAt(int paramInt, short paramShort)
  {
    if (paramInt > this.size) {
      throw new IndexOutOfBoundsException();
    }
    this.data[paramInt] = paramShort;
  }
  
  public final void setShortAtBuffer(int paramInt, short paramShort)
  {
    this.data[paramInt] = paramShort;
  }
  
  public final short shortAt(int paramInt)
  {
    if (paramInt > this.size) {
      throw new IndexOutOfBoundsException();
    }
    return this.data[paramInt];
  }
  
  public final short shortAtBuffer(int paramInt)
  {
    return this.data[paramInt];
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    int j = this.size;
    paramObjectOutput.writeInt(j);
    int i = 0;
    while (i < j)
    {
      paramObjectOutput.writeShort(this.data[i]);
      i += 1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\U16Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */