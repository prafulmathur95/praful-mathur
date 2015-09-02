package gnu.lists;

public class GeneralArray
  extends AbstractSequence
  implements Array
{
  static final int[] zeros = new int[8];
  SimpleVector base;
  int[] dimensions;
  int[] lowBounds;
  int offset;
  boolean simple = true;
  int[] strides;
  
  public GeneralArray() {}
  
  public GeneralArray(int[] paramArrayOfInt)
  {
    int i = 1;
    int j = paramArrayOfInt.length;
    if (j <= zeros.length) {}
    for (this.lowBounds = zeros;; this.lowBounds = new int[j])
    {
      int[] arrayOfInt = new int[j];
      for (;;)
      {
        j -= 1;
        if (j < 0) {
          break;
        }
        arrayOfInt[j] = i;
        i *= paramArrayOfInt[j];
      }
    }
    this.base = new FVector(i);
    this.dimensions = paramArrayOfInt;
    this.offset = 0;
  }
  
  public static Array makeSimple(int[] paramArrayOfInt1, int[] paramArrayOfInt2, SimpleVector paramSimpleVector)
  {
    int j = paramArrayOfInt2.length;
    int[] arrayOfInt1 = paramArrayOfInt1;
    if (paramArrayOfInt1 == null)
    {
      paramArrayOfInt1 = zeros;
      arrayOfInt1 = paramArrayOfInt1;
      if (j > paramArrayOfInt1.length) {
        arrayOfInt1 = new int[j];
      }
    }
    if ((j == 1) && (arrayOfInt1[0] == 0)) {
      return paramSimpleVector;
    }
    paramArrayOfInt1 = new GeneralArray();
    int[] arrayOfInt2 = new int[j];
    int i = 1;
    for (;;)
    {
      j -= 1;
      if (j < 0) {
        break;
      }
      arrayOfInt2[j] = i;
      i *= paramArrayOfInt2[j];
    }
    paramArrayOfInt1.strides = arrayOfInt2;
    paramArrayOfInt1.dimensions = paramArrayOfInt2;
    paramArrayOfInt1.lowBounds = arrayOfInt1;
    paramArrayOfInt1.base = paramSimpleVector;
    return paramArrayOfInt1;
  }
  
  public static void toString(Array paramArray, StringBuffer paramStringBuffer)
  {
    paramStringBuffer.append("#<array");
    int j = paramArray.rank();
    int i = 0;
    while (i < j)
    {
      paramStringBuffer.append(' ');
      int k = paramArray.getLowBound(i);
      int m = paramArray.getSize(i);
      if (k != 0)
      {
        paramStringBuffer.append(k);
        paramStringBuffer.append(':');
      }
      paramStringBuffer.append(k + m);
      i += 1;
    }
    paramStringBuffer.append('>');
  }
  
  public int createPos(int paramInt, boolean paramBoolean)
  {
    int i = this.offset;
    int j = this.dimensions.length;
    for (;;)
    {
      int k = paramInt;
      j -= 1;
      if (j < 0) {
        break;
      }
      int m = this.dimensions[j];
      paramInt = k / m;
      i += this.strides[j] * (k % m);
    }
    if (paramBoolean) {}
    for (paramInt = 1;; paramInt = 0) {
      return paramInt | i << 1;
    }
  }
  
  public Object get(int paramInt)
  {
    return getRowMajor(paramInt);
  }
  
  public Object get(int[] paramArrayOfInt)
  {
    return this.base.get(getEffectiveIndex(paramArrayOfInt));
  }
  
  public int getEffectiveIndex(int[] paramArrayOfInt)
  {
    int i = this.offset;
    int j = this.dimensions.length;
    for (;;)
    {
      j -= 1;
      if (j < 0) {
        break;
      }
      int k = paramArrayOfInt[j];
      int m = this.lowBounds[j];
      if (k >= m)
      {
        k -= m;
        if (k < this.dimensions[j]) {}
      }
      else
      {
        throw new IndexOutOfBoundsException();
      }
      i += this.strides[j] * k;
    }
    return i;
  }
  
  public int getLowBound(int paramInt)
  {
    return this.lowBounds[paramInt];
  }
  
  public Object getRowMajor(int paramInt)
  {
    if (this.simple) {
      return this.base.get(paramInt);
    }
    int i = this.offset;
    int j = this.dimensions.length;
    for (;;)
    {
      int k = paramInt;
      j -= 1;
      if (j < 0) {
        break;
      }
      int m = this.dimensions[j];
      paramInt = k / m;
      i += this.strides[j] * (k % m);
    }
    return this.base.get(i);
  }
  
  public int getSize(int paramInt)
  {
    return this.dimensions[paramInt];
  }
  
  public int rank()
  {
    return this.dimensions.length;
  }
  
  public Object set(int[] paramArrayOfInt, Object paramObject)
  {
    return this.base.set(getEffectiveIndex(paramArrayOfInt), paramObject);
  }
  
  public int size()
  {
    int i = 1;
    int j = this.dimensions.length;
    for (;;)
    {
      j -= 1;
      if (j < 0) {
        break;
      }
      i *= this.dimensions[j];
    }
    return i;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    toString(this, localStringBuffer);
    return localStringBuffer.toString();
  }
  
  public Array transpose(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt, int[] paramArrayOfInt3)
  {
    if ((paramArrayOfInt2.length == 1) && (paramArrayOfInt1[0] == 0)) {}
    for (Object localObject = new GeneralArray1();; localObject = new GeneralArray())
    {
      ((GeneralArray)localObject).offset = paramInt;
      ((GeneralArray)localObject).strides = paramArrayOfInt3;
      ((GeneralArray)localObject).dimensions = paramArrayOfInt2;
      ((GeneralArray)localObject).lowBounds = paramArrayOfInt1;
      ((GeneralArray)localObject).base = this.base;
      ((GeneralArray)localObject).simple = false;
      return (Array)localObject;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\GeneralArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */