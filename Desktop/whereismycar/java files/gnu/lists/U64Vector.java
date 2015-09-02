package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class U64Vector
  extends SimpleVector
  implements Externalizable, Comparable
{
  long[] data;
  
  public U64Vector()
  {
    this.data = S64Vector.empty;
  }
  
  public U64Vector(int paramInt)
  {
    this.data = new long[paramInt];
    this.size = paramInt;
  }
  
  public U64Vector(int paramInt, long paramLong)
  {
    long[] arrayOfLong = new long[paramInt];
    this.data = arrayOfLong;
    this.size = paramInt;
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      arrayOfLong[paramInt] = paramLong;
    }
  }
  
  public U64Vector(Sequence paramSequence)
  {
    this.data = new long[paramSequence.size()];
    addAll(paramSequence);
  }
  
  public U64Vector(long[] paramArrayOfLong)
  {
    this.data = paramArrayOfLong;
    this.size = paramArrayOfLong.length;
  }
  
  protected void clearBuffer(int paramInt1, int paramInt2)
  {
    for (;;)
    {
      paramInt2 -= 1;
      if (paramInt2 < 0) {
        break;
      }
      this.data[paramInt1] = 0L;
      paramInt1 += 1;
    }
  }
  
  public int compareTo(Object paramObject)
  {
    paramObject = (U64Vector)paramObject;
    long[] arrayOfLong1 = this.data;
    long[] arrayOfLong2 = ((U64Vector)paramObject).data;
    int k = this.size;
    int m = ((U64Vector)paramObject).size;
    int i;
    int j;
    if (k > m)
    {
      i = m;
      j = 0;
    }
    for (;;)
    {
      if (j >= i) {
        break label104;
      }
      long l1 = arrayOfLong1[j];
      long l2 = arrayOfLong2[j];
      if (l1 != l2)
      {
        if ((0x8000000000000000 ^ l1) > (0x8000000000000000 ^ l2))
        {
          return 1;
          i = k;
          break;
        }
        return -1;
      }
      j += 1;
    }
    label104:
    return k - m;
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer)
  {
    paramInt >>>= 1;
    if (paramInt >= this.size) {
      return false;
    }
    paramConsumer.writeLong(this.data[paramInt]);
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
        paramConsumer.writeLong(this.data[paramInt2]);
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
    return 23;
  }
  
  public String getTag()
  {
    return "u64";
  }
  
  public final int intAtBuffer(int paramInt)
  {
    return (int)this.data[paramInt];
  }
  
  public final long longAt(int paramInt)
  {
    if (paramInt > this.size) {
      throw new IndexOutOfBoundsException();
    }
    return this.data[paramInt];
  }
  
  public final long longAtBuffer(int paramInt)
  {
    return this.data[paramInt];
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    int j = paramObjectInput.readInt();
    long[] arrayOfLong = new long[j];
    int i = 0;
    while (i < j)
    {
      arrayOfLong[i] = paramObjectInput.readLong();
      i += 1;
    }
    this.data = arrayOfLong;
    this.size = j;
  }
  
  public Object setBuffer(int paramInt, Object paramObject)
  {
    long l = this.data[paramInt];
    this.data[paramInt] = Convert.toLongUnsigned(paramObject);
    return Convert.toObjectUnsigned(l);
  }
  
  public void setBufferLength(int paramInt)
  {
    int i = this.data.length;
    long[] arrayOfLong1;
    long[] arrayOfLong2;
    if (i != paramInt)
    {
      arrayOfLong1 = new long[paramInt];
      arrayOfLong2 = this.data;
      if (i >= paramInt) {
        break label45;
      }
      paramInt = i;
    }
    label45:
    for (;;)
    {
      System.arraycopy(arrayOfLong2, 0, arrayOfLong1, 0, paramInt);
      this.data = arrayOfLong1;
      return;
    }
  }
  
  public final void setLongAt(int paramInt, long paramLong)
  {
    if (paramInt > this.size) {
      throw new IndexOutOfBoundsException();
    }
    this.data[paramInt] = paramLong;
  }
  
  public final void setLongAtBuffer(int paramInt, long paramLong)
  {
    this.data[paramInt] = paramLong;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    int j = this.size;
    paramObjectOutput.writeInt(j);
    int i = 0;
    while (i < j)
    {
      paramObjectOutput.writeLong(this.data[i]);
      i += 1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\U64Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */