package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Pair
  extends LList
  implements Externalizable
{
  protected Object car;
  protected Object cdr;
  
  public Pair() {}
  
  public Pair(Object paramObject1, Object paramObject2)
  {
    this.car = paramObject1;
    this.cdr = paramObject2;
  }
  
  public static int compareTo(Pair paramPair1, Pair paramPair2)
  {
    int i;
    if (paramPair1 == paramPair2)
    {
      i = 0;
      return i;
    }
    if (paramPair1 == null) {
      return -1;
    }
    Pair localPair = paramPair1;
    paramPair1 = paramPair2;
    if (paramPair2 == null) {
      return 1;
    }
    do
    {
      localPair = (Pair)paramPair2;
      paramPair1 = (Pair)paramPair1;
      paramPair2 = localPair.car;
      Object localObject = paramPair1.car;
      int j = ((Comparable)paramPair2).compareTo((Comparable)localObject);
      i = j;
      if (j != 0) {
        break;
      }
      paramPair2 = localPair.cdr;
      paramPair1 = paramPair1.cdr;
      if (paramPair2 == paramPair1) {
        return 0;
      }
      if (paramPair2 == null) {
        return -1;
      }
      if (paramPair1 == null) {
        return 1;
      }
    } while (((paramPair2 instanceof Pair)) && ((paramPair1 instanceof Pair)));
    return ((Comparable)paramPair2).compareTo((Comparable)paramPair1);
  }
  
  public static boolean equals(Pair paramPair1, Pair paramPair2)
  {
    if (paramPair1 == paramPair2) {
      return true;
    }
    Pair localPair;
    if (paramPair1 != null)
    {
      localPair = paramPair1;
      paramPair1 = paramPair2;
      if (paramPair2 != null) {}
    }
    else
    {
      return false;
    }
    do
    {
      localPair = (Pair)paramPair2;
      paramPair1 = (Pair)paramPair1;
      paramPair2 = localPair.car;
      Object localObject = paramPair1.car;
      if ((paramPair2 != localObject) && ((paramPair2 == null) || (!paramPair2.equals(localObject)))) {
        return false;
      }
      paramPair2 = localPair.cdr;
      paramPair1 = paramPair1.cdr;
      if (paramPair2 == paramPair1) {
        break;
      }
      if ((paramPair2 == null) || (paramPair1 == null)) {
        return false;
      }
    } while (((paramPair2 instanceof Pair)) && ((paramPair1 instanceof Pair)));
    return paramPair2.equals(paramPair1);
  }
  
  public static Pair make(Object paramObject1, Object paramObject2)
  {
    return new Pair(paramObject1, paramObject2);
  }
  
  public int compareTo(Object paramObject)
  {
    if (paramObject == Empty) {
      return 1;
    }
    return compareTo(this, (Pair)paramObject);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject != null) && ((paramObject instanceof Pair))) {
      return equals(this, (Pair)paramObject);
    }
    return false;
  }
  
  public Object get(int paramInt)
  {
    for (Pair localPair = this;; localPair = (Pair)localPair.cdr)
    {
      i = paramInt;
      if (paramInt <= 0) {
        break label59;
      }
      paramInt -= 1;
      if (!(localPair.cdr instanceof Pair)) {
        break;
      }
    }
    int i = paramInt;
    if ((localPair.cdr instanceof Sequence)) {
      return ((Sequence)localPair.cdr).get(paramInt);
    }
    label59:
    if (i == 0) {
      return localPair.car;
    }
    throw new IndexOutOfBoundsException();
  }
  
  public Object getCar()
  {
    return this.car;
  }
  
  public Object getCdr()
  {
    return this.cdr;
  }
  
  public Object getPosNext(int paramInt)
  {
    if (paramInt <= 0)
    {
      if (paramInt == 0) {
        return this.car;
      }
      return eofValue;
    }
    return PositionManager.getPositionObject(paramInt).getNext();
  }
  
  public Object getPosPrevious(int paramInt)
  {
    if (paramInt <= 0)
    {
      if (paramInt == 0) {
        return eofValue;
      }
      return lastPair().car;
    }
    return PositionManager.getPositionObject(paramInt).getPrevious();
  }
  
  public boolean hasNext(int paramInt)
  {
    if (paramInt <= 0) {
      return paramInt == 0;
    }
    return PositionManager.getPositionObject(paramInt).hasNext();
  }
  
  public int hashCode()
  {
    int i = 1;
    Object localObject1 = this;
    if ((localObject1 instanceof Pair))
    {
      localObject1 = (Pair)localObject1;
      Object localObject2 = ((Pair)localObject1).car;
      if (localObject2 == null) {}
      for (j = 0;; j = localObject2.hashCode())
      {
        i = i * 31 + j;
        localObject1 = ((Pair)localObject1).cdr;
        break;
      }
    }
    int j = i;
    if (localObject1 != LList.Empty)
    {
      j = i;
      if (localObject1 != null) {
        j = i ^ localObject1.hashCode();
      }
    }
    return j;
  }
  
  public boolean isEmpty()
  {
    return false;
  }
  
  public final Pair lastPair()
  {
    Object localObject;
    for (Pair localPair = this;; localPair = (Pair)localObject)
    {
      localObject = localPair.cdr;
      if (!(localObject instanceof Pair)) {
        break;
      }
    }
    return localPair;
  }
  
  public int length()
  {
    int i = 0;
    Object localObject1 = this;
    Object localObject2 = this;
    for (;;)
    {
      if (localObject1 == Empty) {
        return i;
      }
      if (!(localObject1 instanceof Pair))
      {
        if ((localObject1 instanceof Sequence))
        {
          int k = ((Sequence)localObject1).size();
          int j = k;
          if (k >= 0) {
            j = k + i;
          }
          return j;
        }
        return -2;
      }
      Pair localPair = (Pair)localObject1;
      if (localPair.cdr == Empty) {
        return i + 1;
      }
      if ((localObject1 == localObject2) && (i > 0)) {
        return -1;
      }
      if (!(localPair.cdr instanceof Pair))
      {
        i += 1;
        localObject1 = localPair.cdr;
      }
      else
      {
        if (!(localObject2 instanceof Pair)) {
          return -2;
        }
        localObject2 = ((Pair)localObject2).cdr;
        localObject1 = ((Pair)localPair.cdr).cdr;
        i += 2;
      }
    }
  }
  
  public int nextPos(int paramInt)
  {
    if (paramInt <= 0)
    {
      if (paramInt < 0) {
        return 0;
      }
      return PositionManager.manager.register(new LListPosition(this, 1, true));
    }
    if (((LListPosition)PositionManager.getPositionObject(paramInt)).gotoNext()) {}
    for (;;)
    {
      return paramInt;
      paramInt = 0;
    }
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.car = paramObjectInput.readObject();
    this.cdr = paramObjectInput.readObject();
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    return this;
  }
  
  public void setCar(Object paramObject)
  {
    this.car = paramObject;
  }
  
  public void setCdr(Object paramObject)
  {
    this.cdr = paramObject;
  }
  
  public void setCdrBackdoor(Object paramObject)
  {
    this.cdr = paramObject;
  }
  
  public int size()
  {
    int i = listLength(this, true);
    if (i >= 0) {
      return i;
    }
    if (i == -1) {
      return Integer.MAX_VALUE;
    }
    throw new RuntimeException("not a true list");
  }
  
  public Object[] toArray()
  {
    int k = size();
    Object[] arrayOfObject = new Object[k];
    int i = 0;
    Object localObject = this;
    while ((i < k) && ((localObject instanceof Pair)))
    {
      localObject = (Pair)localObject;
      arrayOfObject[i] = ((Pair)localObject).car;
      localObject = (Sequence)((Pair)localObject).cdr;
      i += 1;
    }
    int j = i;
    while (j < k)
    {
      arrayOfObject[j] = ((Sequence)localObject).get(j - i);
      j += 1;
    }
    return arrayOfObject;
  }
  
  public Object[] toArray(Object[] paramArrayOfObject)
  {
    int i = paramArrayOfObject.length;
    int m = length();
    int k = i;
    if (m > i)
    {
      paramArrayOfObject = new Object[m];
      k = m;
    }
    i = 0;
    Object localObject = this;
    while ((i < m) && ((localObject instanceof Pair)))
    {
      localObject = (Pair)localObject;
      paramArrayOfObject[i] = ((Pair)localObject).car;
      localObject = (Sequence)((Pair)localObject).cdr;
      i += 1;
    }
    int j = i;
    while (j < m)
    {
      paramArrayOfObject[j] = ((Sequence)localObject).get(j - i);
      j += 1;
    }
    if (m < k) {
      paramArrayOfObject[m] = null;
    }
    return paramArrayOfObject;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.car);
    paramObjectOutput.writeObject(this.cdr);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\Pair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */