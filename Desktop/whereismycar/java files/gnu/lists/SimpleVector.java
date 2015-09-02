package gnu.lists;

import java.util.Collection;
import java.util.Iterator;

public abstract class SimpleVector
  extends AbstractSequence
  implements Sequence, Array
{
  public int size;
  
  protected static int compareToInt(SimpleVector paramSimpleVector1, SimpleVector paramSimpleVector2)
  {
    int k = paramSimpleVector1.size;
    int m = paramSimpleVector2.size;
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
        break label74;
      }
      int n = paramSimpleVector1.intAtBuffer(j);
      int i1 = paramSimpleVector2.intAtBuffer(j);
      if (11 != i1)
      {
        if (n > i1)
        {
          return 1;
          i = k;
          break;
        }
        return -1;
      }
      j += 1;
    }
    label74:
    return k - m;
  }
  
  protected static int compareToLong(SimpleVector paramSimpleVector1, SimpleVector paramSimpleVector2)
  {
    int k = paramSimpleVector1.size;
    int m = paramSimpleVector2.size;
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
        break label76;
      }
      long l1 = paramSimpleVector1.longAtBuffer(j);
      long l2 = paramSimpleVector2.longAtBuffer(j);
      if (l1 != l2)
      {
        if (l1 > l2)
        {
          return 1;
          i = k;
          break;
        }
        return -1;
      }
      j += 1;
    }
    label76:
    return k - m;
  }
  
  public void add(int paramInt, Object paramObject)
  {
    int i = 16;
    int j = this.size + 1;
    this.size = j;
    int k = getBufferLength();
    if (j > k) {
      if (k >= 16) {
        break label78;
      }
    }
    for (;;)
    {
      setBufferLength(i);
      this.size = j;
      if (this.size != paramInt) {
        shift(paramInt, paramInt + 1, this.size - paramInt);
      }
      set(paramInt, paramObject);
      return;
      label78:
      i = k * 2;
    }
  }
  
  public boolean add(Object paramObject)
  {
    add(this.size, paramObject);
    return true;
  }
  
  public boolean addAll(int paramInt, Collection paramCollection)
  {
    boolean bool = false;
    int i = paramCollection.size();
    setSize(this.size + i);
    shift(paramInt, paramInt + i, this.size - i - paramInt);
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      set(paramInt, paramCollection.next());
      bool = true;
      paramInt += 1;
    }
    return bool;
  }
  
  protected int addPos(int paramInt, Object paramObject)
  {
    paramInt >>>= 1;
    add(paramInt, paramObject);
    return (paramInt << 1) + 3;
  }
  
  public void clear()
  {
    setSize(0);
  }
  
  protected abstract void clearBuffer(int paramInt1, int paramInt2);
  
  public void consume(int paramInt1, int paramInt2, Consumer paramConsumer)
  {
    consumePosRange(paramInt1 << 1, paramInt1 + paramInt2 << 1, paramConsumer);
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer)
  {
    paramInt >>>= 1;
    if (paramInt >= this.size) {
      return false;
    }
    paramConsumer.writeObject(getBuffer(paramInt));
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
        paramConsumer.writeObject(getBuffer(paramInt2));
        paramInt2 += 1;
      }
    }
  }
  
  public int createPos(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0) {
      return i | paramInt << 1;
    }
  }
  
  public void fill(int paramInt1, int paramInt2, Object paramObject)
  {
    if ((paramInt1 < 0) || (paramInt2 > this.size)) {
      throw new IndexOutOfBoundsException();
    }
    while (paramInt1 < paramInt2)
    {
      setBuffer(paramInt1, paramObject);
      paramInt1 += 1;
    }
  }
  
  public void fill(Object paramObject)
  {
    int i = this.size;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      setBuffer(i, paramObject);
    }
  }
  
  public void fillPosRange(int paramInt1, int paramInt2, Object paramObject)
  {
    if (paramInt1 == -1)
    {
      paramInt1 = this.size;
      if (paramInt2 != -1) {
        break label46;
      }
      paramInt2 = this.size;
    }
    for (;;)
    {
      if (paramInt1 >= paramInt2) {
        return;
      }
      setBuffer(paramInt1, paramObject);
      paramInt1 += 1;
      continue;
      paramInt1 >>>= 1;
      break;
      label46:
      paramInt2 >>>= 1;
    }
  }
  
  public Object get(int paramInt)
  {
    if (paramInt >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    return getBuffer(paramInt);
  }
  
  protected abstract Object getBuffer();
  
  protected abstract Object getBuffer(int paramInt);
  
  public abstract int getBufferLength();
  
  public int getElementKind()
  {
    return 32;
  }
  
  public int getNextKind(int paramInt)
  {
    if (hasNext(paramInt)) {
      return getElementKind();
    }
    return 0;
  }
  
  public Object getPosNext(int paramInt)
  {
    paramInt >>>= 1;
    if (paramInt >= this.size) {
      return eofValue;
    }
    return getBuffer(paramInt);
  }
  
  public Object getRowMajor(int paramInt)
  {
    return get(paramInt);
  }
  
  public String getTag()
  {
    return null;
  }
  
  public int intAt(int paramInt)
  {
    if (paramInt >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    return intAtBuffer(paramInt);
  }
  
  public int intAtBuffer(int paramInt)
  {
    return Convert.toInt(getBuffer(paramInt));
  }
  
  protected boolean isAfterPos(int paramInt)
  {
    return (paramInt & 0x1) != 0;
  }
  
  public long longAt(int paramInt)
  {
    if (paramInt >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    return longAtBuffer(paramInt);
  }
  
  public long longAtBuffer(int paramInt)
  {
    return Convert.toLong(getBuffer(paramInt));
  }
  
  protected int nextIndex(int paramInt)
  {
    if (paramInt == -1) {
      return this.size;
    }
    return paramInt >>> 1;
  }
  
  public int nextPos(int paramInt)
  {
    if (paramInt == -1) {}
    do
    {
      return 0;
      paramInt >>>= 1;
    } while (paramInt == this.size);
    return (paramInt << 1) + 3;
  }
  
  public Object remove(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.size)) {
      throw new IndexOutOfBoundsException();
    }
    Object localObject = get(paramInt);
    shift(paramInt + 1, paramInt, 1);
    this.size -= 1;
    clearBuffer(this.size, 1);
    return localObject;
  }
  
  public boolean remove(Object paramObject)
  {
    int i = indexOf(paramObject);
    if (i < 0) {
      return false;
    }
    shift(i + 1, i, 1);
    this.size -= 1;
    clearBuffer(this.size, 1);
    return true;
  }
  
  public boolean removeAll(Collection paramCollection)
  {
    boolean bool = false;
    int j = 0;
    int i = 0;
    if (i < this.size)
    {
      Object localObject = get(i);
      if (paramCollection.contains(localObject)) {
        bool = true;
      }
      for (;;)
      {
        i += 1;
        break;
        if (bool) {
          set(j, localObject);
        }
        j += 1;
      }
    }
    setSize(j);
    return bool;
  }
  
  public void removePos(int paramInt1, int paramInt2)
  {
    int i = paramInt1 >>> 1;
    paramInt1 = i;
    if (i > this.size) {
      paramInt1 = this.size;
    }
    if (paramInt2 >= 0)
    {
      i = paramInt1;
      paramInt1 += paramInt2;
    }
    while ((i < 0) || (paramInt1 >= this.size))
    {
      throw new IndexOutOfBoundsException();
      i = paramInt1 + paramInt2;
      paramInt2 = -paramInt2;
    }
    shift(paramInt1, i, this.size - paramInt1);
    this.size -= paramInt2;
    clearBuffer(this.size, paramInt2);
  }
  
  protected void removePosRange(int paramInt1, int paramInt2)
  {
    int i = paramInt1 >>> 1;
    paramInt2 >>>= 1;
    if (i >= paramInt2) {
      return;
    }
    paramInt1 = paramInt2;
    if (paramInt2 > this.size) {
      paramInt1 = this.size;
    }
    shift(paramInt1, i, this.size - paramInt1);
    paramInt1 -= i;
    this.size -= paramInt1;
    clearBuffer(this.size, paramInt1);
  }
  
  protected void resizeShift(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = paramInt4 - paramInt3;
    int j = getBufferLength();
    int k = j - (paramInt2 - paramInt1) + i;
    if (k > j)
    {
      setBufferLength(k);
      this.size = k;
    }
    int m = paramInt1 - paramInt3;
    if (m >= 0)
    {
      paramInt1 = j - paramInt2;
      shift(paramInt2, k - paramInt1, paramInt1);
      if (m > 0) {
        shift(paramInt3, paramInt4, m);
      }
    }
    for (;;)
    {
      clearBuffer(paramInt3, i);
      return;
      k -= paramInt4;
      shift(j - k, paramInt4, k);
      shift(paramInt2, paramInt1, paramInt3 - paramInt1);
    }
  }
  
  public boolean retainAll(Collection paramCollection)
  {
    boolean bool = false;
    int j = 0;
    int i = 0;
    if (i < this.size)
    {
      Object localObject = get(i);
      if (!paramCollection.contains(localObject)) {
        bool = true;
      }
      for (;;)
      {
        i += 1;
        break;
        if (bool) {
          set(j, localObject);
        }
        j += 1;
      }
    }
    setSize(j);
    return bool;
  }
  
  public Object set(int paramInt, Object paramObject)
  {
    if (paramInt >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    Object localObject = getBuffer(paramInt);
    setBuffer(paramInt, paramObject);
    return localObject;
  }
  
  protected abstract Object setBuffer(int paramInt, Object paramObject);
  
  public abstract void setBufferLength(int paramInt);
  
  public void setSize(int paramInt)
  {
    int i = 16;
    int j = this.size;
    this.size = paramInt;
    if (paramInt < j) {
      clearBuffer(paramInt, j - paramInt);
    }
    do
    {
      return;
      j = getBufferLength();
    } while (paramInt <= j);
    if (j < 16) {
      if (paramInt <= i) {
        break label61;
      }
    }
    for (;;)
    {
      setBufferLength(paramInt);
      return;
      i = j * 2;
      break;
      label61:
      paramInt = i;
    }
  }
  
  public void shift(int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject = getBuffer();
    System.arraycopy(localObject, paramInt1, localObject, paramInt2, paramInt3);
  }
  
  public final int size()
  {
    return this.size;
  }
  
  public Array transpose(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt, int[] paramArrayOfInt3)
  {
    GeneralArray localGeneralArray = new GeneralArray();
    localGeneralArray.strides = paramArrayOfInt3;
    localGeneralArray.dimensions = paramArrayOfInt2;
    localGeneralArray.lowBounds = paramArrayOfInt1;
    localGeneralArray.offset = paramInt;
    localGeneralArray.base = this;
    localGeneralArray.simple = false;
    return localGeneralArray;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\SimpleVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */