package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Iterator;
import java.util.List;

public class LList
  extends ExtSequence
  implements Sequence, Externalizable, Comparable
{
  public static final LList Empty = new LList();
  
  public static Pair chain1(Pair paramPair, Object paramObject)
  {
    paramObject = new Pair(paramObject, Empty);
    paramPair.cdr = paramObject;
    return (Pair)paramObject;
  }
  
  public static Pair chain4(Pair paramPair, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    paramObject4 = new Pair(paramObject4, Empty);
    paramPair.cdr = new Pair(paramObject1, new Pair(paramObject2, new Pair(paramObject3, paramObject4)));
    return (Pair)paramObject4;
  }
  
  public static Object checkNonList(Object paramObject)
  {
    Object localObject = paramObject;
    if ((paramObject instanceof LList)) {
      localObject = "#<not a pair>";
    }
    return localObject;
  }
  
  public static Object consX(Object[] paramArrayOfObject)
  {
    Object localObject = paramArrayOfObject[0];
    int j = paramArrayOfObject.length - 1;
    if (j <= 0) {
      return localObject;
    }
    Pair localPair1 = new Pair(localObject, null);
    localObject = localPair1;
    int i = 1;
    while (i < j)
    {
      Pair localPair2 = new Pair(paramArrayOfObject[i], null);
      ((Pair)localObject).cdr = localPair2;
      localObject = localPair2;
      i += 1;
    }
    ((Pair)localObject).cdr = paramArrayOfObject[j];
    return localPair1;
  }
  
  public static final int length(Object paramObject)
  {
    int i = 0;
    while ((paramObject instanceof Pair))
    {
      i += 1;
      paramObject = ((Pair)paramObject).cdr;
    }
    return i;
  }
  
  public static Pair list1(Object paramObject)
  {
    return new Pair(paramObject, Empty);
  }
  
  public static Pair list2(Object paramObject1, Object paramObject2)
  {
    return new Pair(paramObject1, new Pair(paramObject2, Empty));
  }
  
  public static Pair list3(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return new Pair(paramObject1, new Pair(paramObject2, new Pair(paramObject3, Empty)));
  }
  
  public static Pair list4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    return new Pair(paramObject1, new Pair(paramObject2, new Pair(paramObject3, new Pair(paramObject4, Empty))));
  }
  
  public static int listLength(Object paramObject, boolean paramBoolean)
  {
    int i = 0;
    Object localObject = paramObject;
    for (;;)
    {
      if (paramObject == Empty) {
        return i;
      }
      if (!(paramObject instanceof Pair))
      {
        if (((paramObject instanceof Sequence)) && (paramBoolean))
        {
          int k = ((Sequence)paramObject).size();
          int j = k;
          if (k >= 0) {
            j = k + i;
          }
          return j;
        }
        return -2;
      }
      Pair localPair = (Pair)paramObject;
      if (localPair.cdr == Empty) {
        return i + 1;
      }
      if ((paramObject == localObject) && (i > 0)) {
        return -1;
      }
      if (!(localPair.cdr instanceof Pair))
      {
        i += 1;
        paramObject = localPair.cdr;
      }
      else
      {
        if (!(localObject instanceof Pair)) {
          return -2;
        }
        localObject = ((Pair)localObject).cdr;
        paramObject = ((Pair)localPair.cdr).cdr;
        i += 2;
      }
    }
  }
  
  public static Object listTail(Object paramObject, int paramInt)
  {
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      if (!(paramObject instanceof Pair)) {
        throw new IndexOutOfBoundsException("List is too short.");
      }
      paramObject = ((Pair)paramObject).cdr;
    }
    return paramObject;
  }
  
  public static LList makeList(List paramList)
  {
    Iterator localIterator = paramList.iterator();
    Object localObject = Empty;
    List localList = null;
    if (localIterator.hasNext())
    {
      paramList = new Pair(localIterator.next(), Empty);
      if (localList == null) {
        localObject = paramList;
      }
      for (;;)
      {
        localList = paramList;
        break;
        localList.cdr = paramList;
      }
    }
    return (LList)localObject;
  }
  
  public static LList makeList(Object[] paramArrayOfObject, int paramInt)
  {
    Object localObject = Empty;
    int i = paramArrayOfObject.length - paramInt;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      localObject = new Pair(paramArrayOfObject[(paramInt + i)], localObject);
    }
    return (LList)localObject;
  }
  
  public static LList makeList(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
  {
    for (Object localObject = Empty;; localObject = new Pair(paramArrayOfObject[(paramInt1 + paramInt2)], localObject))
    {
      paramInt2 -= 1;
      if (paramInt2 < 0) {
        break;
      }
    }
    return (LList)localObject;
  }
  
  public static LList reverseInPlace(Object paramObject)
  {
    Object localObject2 = Empty;
    Object localObject1 = paramObject;
    for (paramObject = localObject2; localObject1 != Empty; paramObject = localObject2)
    {
      localObject2 = (Pair)localObject1;
      localObject1 = ((Pair)localObject2).cdr;
      ((Pair)localObject2).cdr = paramObject;
    }
    return (LList)paramObject;
  }
  
  public int compareTo(Object paramObject)
  {
    if (paramObject == Empty) {
      return 0;
    }
    return -1;
  }
  
  public void consume(Consumer paramConsumer)
  {
    Object localObject = this;
    paramConsumer.startElement("list");
    while ((localObject instanceof Pair))
    {
      if (localObject != this) {
        paramConsumer.write(32);
      }
      localObject = (Pair)localObject;
      paramConsumer.writeObject(((Pair)localObject).car);
      localObject = ((Pair)localObject).cdr;
    }
    if (localObject != Empty)
    {
      paramConsumer.write(32);
      paramConsumer.write(". ");
      paramConsumer.writeObject(checkNonList(localObject));
    }
    paramConsumer.endElement();
  }
  
  public int createPos(int paramInt, boolean paramBoolean)
  {
    LListPosition localLListPosition = new LListPosition(this, paramInt, paramBoolean);
    return PositionManager.manager.register(localLListPosition);
  }
  
  public int createRelativePos(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    boolean bool = isAfterPos(paramInt1);
    if ((paramInt2 < 0) || (paramInt1 == 0)) {
      return super.createRelativePos(paramInt1, paramInt2, paramBoolean);
    }
    if (paramInt2 == 0)
    {
      if (paramBoolean == bool) {
        return copyPos(paramInt1);
      }
      if ((paramBoolean) && (!bool)) {
        return super.createRelativePos(paramInt1, paramInt2, paramBoolean);
      }
    }
    if (paramInt1 < 0) {
      throw new IndexOutOfBoundsException();
    }
    Object localObject1 = (LListPosition)PositionManager.getPositionObject(paramInt1);
    if (((LListPosition)localObject1).xpos == null) {
      return super.createRelativePos(paramInt1, paramInt2, paramBoolean);
    }
    LListPosition localLListPosition = new LListPosition((LListPosition)localObject1);
    Object localObject2 = localLListPosition.xpos;
    int j = localLListPosition.ipos;
    paramInt1 = j;
    int i = paramInt2;
    if (paramBoolean)
    {
      paramInt1 = j;
      i = paramInt2;
      if (!bool)
      {
        i = paramInt2 - 1;
        paramInt1 = j + 3;
      }
    }
    paramInt2 = paramInt1;
    localObject1 = localObject2;
    j = i;
    if (!paramBoolean)
    {
      paramInt2 = paramInt1;
      localObject1 = localObject2;
      j = i;
      if (bool)
      {
        j = i + 1;
        paramInt2 = paramInt1 - 3;
      }
    }
    for (localObject1 = localObject2;; localObject1 = ((Pair)localObject1).cdr)
    {
      if (!(localObject1 instanceof Pair)) {
        throw new IndexOutOfBoundsException();
      }
      j -= 1;
      if (j < 0)
      {
        localLListPosition.ipos = paramInt2;
        localLListPosition.xpos = localObject1;
        return PositionManager.manager.register(localLListPosition);
      }
      localObject1 = (Pair)localObject1;
      paramInt2 += 2;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return this == paramObject;
  }
  
  public Object get(int paramInt)
  {
    throw new IndexOutOfBoundsException();
  }
  
  public SeqPosition getIterator(int paramInt)
  {
    return new LListPosition(this, paramInt, false);
  }
  
  public Object getPosNext(int paramInt)
  {
    return eofValue;
  }
  
  public Object getPosPrevious(int paramInt)
  {
    return eofValue;
  }
  
  public boolean hasNext(int paramInt)
  {
    return false;
  }
  
  public boolean isEmpty()
  {
    return true;
  }
  
  public int nextPos(int paramInt)
  {
    return 0;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {}
  
  public Object readResolve()
    throws ObjectStreamException
  {
    return Empty;
  }
  
  protected void setPosNext(int paramInt, Object paramObject)
  {
    if (paramInt <= 0)
    {
      if ((paramInt == -1) || (!(this instanceof Pair))) {
        throw new IndexOutOfBoundsException();
      }
      ((Pair)this).car = paramObject;
      return;
    }
    PositionManager.getPositionObject(paramInt).setNext(paramObject);
  }
  
  protected void setPosPrevious(int paramInt, Object paramObject)
  {
    if (paramInt <= 0)
    {
      if ((paramInt == 0) || (!(this instanceof Pair))) {
        throw new IndexOutOfBoundsException();
      }
      ((Pair)this).lastPair().car = paramObject;
      return;
    }
    PositionManager.getPositionObject(paramInt).setPrevious(paramObject);
  }
  
  public int size()
  {
    return 0;
  }
  
  public String toString()
  {
    Object localObject = this;
    int i = 0;
    StringBuffer localStringBuffer = new StringBuffer(100);
    localStringBuffer.append('(');
    if (localObject == Empty) {}
    for (;;)
    {
      localStringBuffer.append(')');
      return localStringBuffer.toString();
      if (i > 0) {
        localStringBuffer.append(' ');
      }
      if (i >= 10)
      {
        localStringBuffer.append("...");
      }
      else
      {
        if ((localObject instanceof Pair))
        {
          localObject = (Pair)localObject;
          localStringBuffer.append(((Pair)localObject).car);
          localObject = ((Pair)localObject).cdr;
          i += 1;
          break;
        }
        localStringBuffer.append(". ");
        localStringBuffer.append(checkNonList(localObject));
      }
    }
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {}
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\LList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */