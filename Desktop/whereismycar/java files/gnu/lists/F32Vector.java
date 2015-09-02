package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class F32Vector
  extends SimpleVector
  implements Externalizable, Comparable
{
  protected static float[] empty = new float[0];
  float[] data;
  
  public F32Vector()
  {
    this.data = empty;
  }
  
  public F32Vector(int paramInt)
  {
    this.data = new float[paramInt];
    this.size = paramInt;
  }
  
  public F32Vector(int paramInt, float paramFloat)
  {
    float[] arrayOfFloat = new float[paramInt];
    this.data = arrayOfFloat;
    this.size = paramInt;
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      arrayOfFloat[paramInt] = paramFloat;
    }
  }
  
  public F32Vector(Sequence paramSequence)
  {
    this.data = new float[paramSequence.size()];
    addAll(paramSequence);
  }
  
  public F32Vector(float[] paramArrayOfFloat)
  {
    this.data = paramArrayOfFloat;
    this.size = paramArrayOfFloat.length;
  }
  
  protected void clearBuffer(int paramInt1, int paramInt2)
  {
    for (;;)
    {
      paramInt2 -= 1;
      if (paramInt2 < 0) {
        break;
      }
      this.data[paramInt1] = 0.0F;
      paramInt1 += 1;
    }
  }
  
  public int compareTo(Object paramObject)
  {
    paramObject = (F32Vector)paramObject;
    float[] arrayOfFloat1 = this.data;
    float[] arrayOfFloat2 = ((F32Vector)paramObject).data;
    int k = this.size;
    int m = ((F32Vector)paramObject).size;
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
        break label94;
      }
      float f1 = arrayOfFloat1[j];
      float f2 = arrayOfFloat2[j];
      if (f1 != f2)
      {
        if (f1 > f2)
        {
          return 1;
          i = k;
          break;
        }
        return -1;
      }
      j += 1;
    }
    label94:
    return k - m;
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer)
  {
    paramInt >>>= 1;
    if (paramInt >= this.size) {
      return false;
    }
    paramConsumer.writeFloat(this.data[paramInt]);
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
        paramConsumer.writeFloat(this.data[paramInt1]);
        paramInt1 += 1;
      }
    }
  }
  
  public final float floatAt(int paramInt)
  {
    if (paramInt >= this.size) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return this.data[paramInt];
  }
  
  public final float floatAtBuffer(int paramInt)
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
    return 25;
  }
  
  public String getTag()
  {
    return "f32";
  }
  
  public final int intAtBuffer(int paramInt)
  {
    return (int)this.data[paramInt];
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    int j = paramObjectInput.readInt();
    float[] arrayOfFloat = new float[j];
    int i = 0;
    while (i < j)
    {
      arrayOfFloat[i] = paramObjectInput.readFloat();
      i += 1;
    }
    this.data = arrayOfFloat;
    this.size = j;
  }
  
  public final Object setBuffer(int paramInt, Object paramObject)
  {
    Object localObject = Convert.toObject(this.data[paramInt]);
    this.data[paramInt] = Convert.toFloat(paramObject);
    return localObject;
  }
  
  public void setBufferLength(int paramInt)
  {
    int i = this.data.length;
    float[] arrayOfFloat1;
    float[] arrayOfFloat2;
    if (i != paramInt)
    {
      arrayOfFloat1 = new float[paramInt];
      arrayOfFloat2 = this.data;
      if (i >= paramInt) {
        break label45;
      }
      paramInt = i;
    }
    label45:
    for (;;)
    {
      System.arraycopy(arrayOfFloat2, 0, arrayOfFloat1, 0, paramInt);
      this.data = arrayOfFloat1;
      return;
    }
  }
  
  public final void setFloatAt(int paramInt, float paramFloat)
  {
    if (paramInt > this.size) {
      throw new IndexOutOfBoundsException();
    }
    this.data[paramInt] = paramFloat;
  }
  
  public final void setFloatAtBuffer(int paramInt, float paramFloat)
  {
    this.data[paramInt] = paramFloat;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    int j = this.size;
    paramObjectOutput.writeInt(j);
    int i = 0;
    while (i < j)
    {
      paramObjectOutput.writeFloat(this.data[i]);
      i += 1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\F32Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */