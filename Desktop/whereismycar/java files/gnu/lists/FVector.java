package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

public class FVector
  extends SimpleVector
  implements Externalizable, Consumable, Comparable
{
  protected static Object[] empty = new Object[0];
  public Object[] data;
  
  public FVector()
  {
    this.data = empty;
  }
  
  public FVector(int paramInt)
  {
    this.size = paramInt;
    this.data = new Object[paramInt];
  }
  
  public FVector(int paramInt, Object paramObject)
  {
    Object[] arrayOfObject = new Object[paramInt];
    if (paramObject != null)
    {
      int i = 0;
      while (i < paramInt)
      {
        arrayOfObject[i] = paramObject;
        i += 1;
      }
    }
    this.data = arrayOfObject;
    this.size = paramInt;
  }
  
  public FVector(List paramList)
  {
    this.data = new Object[paramList.size()];
    addAll(paramList);
  }
  
  public FVector(Object[] paramArrayOfObject)
  {
    this.size = paramArrayOfObject.length;
    this.data = paramArrayOfObject;
  }
  
  public static FVector make(Object... paramVarArgs)
  {
    return new FVector(paramVarArgs);
  }
  
  protected void clearBuffer(int paramInt1, int paramInt2)
  {
    Object[] arrayOfObject = this.data;
    for (;;)
    {
      paramInt2 -= 1;
      if (paramInt2 < 0) {
        break;
      }
      arrayOfObject[paramInt1] = null;
      paramInt1 += 1;
    }
  }
  
  public int compareTo(Object paramObject)
  {
    paramObject = (FVector)paramObject;
    Object[] arrayOfObject1 = this.data;
    Object[] arrayOfObject2 = ((FVector)paramObject).data;
    int k = this.size;
    int m = ((FVector)paramObject).size;
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
        break label93;
      }
      int n = ((Comparable)arrayOfObject1[j]).compareTo((Comparable)arrayOfObject2[j]);
      if (n != 0)
      {
        return n;
        i = k;
        break;
      }
      j += 1;
    }
    label93:
    return k - m;
  }
  
  public void consume(Consumer paramConsumer)
  {
    paramConsumer.startElement("#vector");
    int j = this.size;
    int i = 0;
    while (i < j)
    {
      paramConsumer.writeObject(this.data[i]);
      i += 1;
    }
    paramConsumer.endElement();
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer)
  {
    paramInt >>>= 1;
    if (paramInt >= this.size) {
      return false;
    }
    paramConsumer.writeObject(this.data[paramInt]);
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
        paramConsumer.writeObject(this.data[paramInt2]);
        paramInt2 += 1;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof FVector))) {}
    int j;
    do
    {
      return false;
      localObject = (FVector)paramObject;
      j = this.size;
    } while ((((FVector)localObject).data == null) || (((FVector)localObject).size != j));
    paramObject = this.data;
    Object localObject = ((FVector)localObject).data;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label77;
      }
      if (!paramObject[i].equals(localObject[i])) {
        break;
      }
      i += 1;
    }
    label77:
    return true;
  }
  
  public final Object get(int paramInt)
  {
    if (paramInt >= this.size) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return this.data[paramInt];
  }
  
  protected Object getBuffer()
  {
    return this.data;
  }
  
  public final Object getBuffer(int paramInt)
  {
    return this.data[paramInt];
  }
  
  public int getBufferLength()
  {
    return this.data.length;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    int j = paramObjectInput.readInt();
    Object[] arrayOfObject = new Object[j];
    int i = 0;
    while (i < j)
    {
      arrayOfObject[i] = paramObjectInput.readObject();
      i += 1;
    }
    this.size = j;
    this.data = arrayOfObject;
  }
  
  public final void setAll(Object paramObject)
  {
    Object[] arrayOfObject = this.data;
    int i = this.size;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      arrayOfObject[i] = paramObject;
    }
  }
  
  public final Object setBuffer(int paramInt, Object paramObject)
  {
    Object localObject = this.data[paramInt];
    this.data[paramInt] = paramObject;
    return localObject;
  }
  
  public void setBufferLength(int paramInt)
  {
    int i = this.data.length;
    Object[] arrayOfObject1;
    Object[] arrayOfObject2;
    if (i != paramInt)
    {
      arrayOfObject1 = new Object[paramInt];
      arrayOfObject2 = this.data;
      if (i >= paramInt) {
        break label46;
      }
      paramInt = i;
    }
    label46:
    for (;;)
    {
      System.arraycopy(arrayOfObject2, 0, arrayOfObject1, 0, paramInt);
      this.data = arrayOfObject1;
      return;
    }
  }
  
  public void shift(int paramInt1, int paramInt2, int paramInt3)
  {
    System.arraycopy(this.data, paramInt1, this.data, paramInt2, paramInt3);
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    int j = this.size;
    paramObjectOutput.writeInt(j);
    int i = 0;
    while (i < j)
    {
      paramObjectOutput.writeObject(this.data[i]);
      i += 1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\FVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */