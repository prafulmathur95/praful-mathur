package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class F64Vector
  extends SimpleVector
  implements Externalizable, Comparable
{
  protected static double[] empty = new double[0];
  double[] data;
  
  public F64Vector()
  {
    this.data = empty;
  }
  
  public F64Vector(int paramInt)
  {
    this.data = new double[paramInt];
    this.size = paramInt;
  }
  
  public F64Vector(int paramInt, double paramDouble)
  {
    double[] arrayOfDouble = new double[paramInt];
    this.data = arrayOfDouble;
    this.size = paramInt;
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      arrayOfDouble[paramInt] = paramDouble;
    }
  }
  
  public F64Vector(Sequence paramSequence)
  {
    this.data = new double[paramSequence.size()];
    addAll(paramSequence);
  }
  
  public F64Vector(double[] paramArrayOfDouble)
  {
    this.data = paramArrayOfDouble;
    this.size = paramArrayOfDouble.length;
  }
  
  protected void clearBuffer(int paramInt1, int paramInt2)
  {
    for (;;)
    {
      paramInt2 -= 1;
      if (paramInt2 < 0) {
        break;
      }
      this.data[paramInt1] = 0.0D;
      paramInt1 += 1;
    }
  }
  
  public int compareTo(Object paramObject)
  {
    paramObject = (F64Vector)paramObject;
    double[] arrayOfDouble1 = this.data;
    double[] arrayOfDouble2 = ((F64Vector)paramObject).data;
    int k = this.size;
    int m = ((F64Vector)paramObject).size;
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
        break label97;
      }
      double d1 = arrayOfDouble1[j];
      double d2 = arrayOfDouble2[j];
      if (d1 != d2)
      {
        if (d1 > d2)
        {
          return 1;
          i = k;
          break;
        }
        return -1;
      }
      j += 1;
    }
    label97:
    return k - m;
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer)
  {
    paramInt >>>= 1;
    if (paramInt >= this.size) {
      return false;
    }
    paramConsumer.writeDouble(this.data[paramInt]);
    return true;
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer)
  {
    if (paramConsumer.ignoring()) {}
    for (;;)
    {
      return;
      paramInt1 >>>= 1;
      while (paramInt1 < paramInt2 >>> 1)
      {
        paramConsumer.writeDouble(this.data[paramInt1]);
        paramInt1 += 1;
      }
    }
  }
  
  public final double doubleAt(int paramInt)
  {
    if (paramInt >= this.size) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return this.data[paramInt];
  }
  
  public final double doubleAtBuffer(int paramInt)
  {
    return this.data[paramInt];
  }
  
  public final Object get(int paramInt)
  {
    if (paramInt > this.size) {
      throw new IndexOutOfBoundsException();
    }
    return Convert.toObject(this.data[paramInt]);
  }
  
  protected Object getBuffer()
  {
    return this.data;
  }
  
  public final Object getBuffer(int paramInt)
  {
    return Convert.toObject(this.data[paramInt]);
  }
  
  public int getBufferLength()
  {
    return this.data.length;
  }
  
  public int getElementKind()
  {
    return 26;
  }
  
  public String getTag()
  {
    return "f64";
  }
  
  public final int intAtBuffer(int paramInt)
  {
    return (int)this.data[paramInt];
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    int j = paramObjectInput.readInt();
    double[] arrayOfDouble = new double[j];
    int i = 0;
    while (i < j)
    {
      arrayOfDouble[i] = paramObjectInput.readDouble();
      i += 1;
    }
    this.data = arrayOfDouble;
    this.size = j;
  }
  
  public final Object setBuffer(int paramInt, Object paramObject)
  {
    Object localObject = Convert.toObject(this.data[paramInt]);
    this.data[paramInt] = Convert.toDouble(paramObject);
    return localObject;
  }
  
  public void setBufferLength(int paramInt)
  {
    int i = this.data.length;
    double[] arrayOfDouble1;
    double[] arrayOfDouble2;
    if (i != paramInt)
    {
      arrayOfDouble1 = new double[paramInt];
      arrayOfDouble2 = this.data;
      if (i >= paramInt) {
        break label45;
      }
      paramInt = i;
    }
    label45:
    for (;;)
    {
      System.arraycopy(arrayOfDouble2, 0, arrayOfDouble1, 0, paramInt);
      this.data = arrayOfDouble1;
      return;
    }
  }
  
  public final void setDoubleAt(int paramInt, double paramDouble)
  {
    if (paramInt > this.size) {
      throw new IndexOutOfBoundsException();
    }
    this.data[paramInt] = paramDouble;
  }
  
  public final void setDoubleAtBuffer(int paramInt, double paramDouble)
  {
    this.data[paramInt] = paramDouble;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    int j = this.size;
    paramObjectOutput.writeInt(j);
    int i = 0;
    while (i < j)
    {
      paramObjectOutput.writeDouble(this.data[i]);
      i += 1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\F64Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */