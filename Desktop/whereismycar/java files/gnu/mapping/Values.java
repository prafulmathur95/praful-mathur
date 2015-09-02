package gnu.mapping;

import gnu.lists.Consumer;
import gnu.lists.TreeList;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Iterator;
import java.util.List;

public class Values
  extends TreeList
  implements Printable, Externalizable
{
  public static final Values empty = new Values(noArgs);
  public static final Object[] noArgs = new Object[0];
  
  public Values() {}
  
  public Values(Object[] paramArrayOfObject)
  {
    int i = 0;
    while (i < paramArrayOfObject.length)
    {
      writeObject(paramArrayOfObject[i]);
      i += 1;
    }
  }
  
  public static int countValues(Object paramObject)
  {
    if ((paramObject instanceof Values)) {
      return ((Values)paramObject).size();
    }
    return 1;
  }
  
  public static Values make()
  {
    return new Values();
  }
  
  public static Object make(TreeList paramTreeList)
  {
    return make(paramTreeList, 0, paramTreeList.data.length);
  }
  
  public static Object make(TreeList paramTreeList, int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt1 != paramInt2)
    {
      i = paramTreeList.nextDataIndex(paramInt1);
      if (i > 0) {}
    }
    else
    {
      return empty;
    }
    if ((i == paramInt2) || (paramTreeList.nextDataIndex(i) < 0)) {
      return paramTreeList.getPosNext(paramInt1 << 1);
    }
    Values localValues = new Values();
    paramTreeList.consumeIRange(paramInt1, paramInt2, localValues);
    return localValues;
  }
  
  public static Object make(List paramList)
  {
    if (paramList == null) {}
    for (int i = 0; i == 0; i = paramList.size())
    {
      paramList = empty;
      return paramList;
    }
    if (i == 1) {
      return paramList.get(0);
    }
    Values localValues = new Values();
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      paramList = localValues;
      if (!localIterator.hasNext()) {
        break;
      }
      localValues.writeObject(localIterator.next());
    }
  }
  
  public static Object make(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.length == 1) {
      return paramArrayOfObject[0];
    }
    if (paramArrayOfObject.length == 0) {
      return empty;
    }
    return new Values(paramArrayOfObject);
  }
  
  public static int nextIndex(Object paramObject, int paramInt)
  {
    if ((paramObject instanceof Values)) {
      return ((Values)paramObject).nextDataIndex(paramInt);
    }
    if (paramInt == 0) {
      return 1;
    }
    return -1;
  }
  
  public static Object nextValue(Object paramObject, int paramInt)
  {
    Object localObject = paramObject;
    if ((paramObject instanceof Values))
    {
      localObject = (Values)paramObject;
      int i = paramInt;
      if (paramInt >= ((Values)localObject).gapEnd) {
        i = paramInt - (((Values)localObject).gapEnd - ((Values)localObject).gapStart);
      }
      localObject = ((Values)paramObject).getPosNext(i << 1);
    }
    return localObject;
  }
  
  public static Object values(Object... paramVarArgs)
  {
    return make(paramVarArgs);
  }
  
  public static void writeValues(Object paramObject, Consumer paramConsumer)
  {
    if ((paramObject instanceof Values))
    {
      ((Values)paramObject).consume(paramConsumer);
      return;
    }
    paramConsumer.writeObject(paramObject);
  }
  
  public Object call_with(Procedure paramProcedure)
    throws Throwable
  {
    return paramProcedure.applyN(toArray());
  }
  
  public final Object canonicalize()
  {
    Values localValues = this;
    if (this.gapEnd == this.data.length)
    {
      if (this.gapStart != 0) {
        break label27;
      }
      localValues = empty;
    }
    label27:
    do
    {
      return localValues;
      localValues = this;
    } while (nextDataIndex(0) != this.gapStart);
    return getPosNext(0);
  }
  
  public Object[] getValues()
  {
    if (isEmpty()) {
      return noArgs;
    }
    return toArray();
  }
  
  public void print(Consumer paramConsumer)
  {
    if (this == empty) {
      paramConsumer.write("#!void");
    }
    int i;
    int k;
    do
    {
      return;
      i = toArray().length;
      if (1 != 0) {
        paramConsumer.write("#<values");
      }
      i = 0;
      k = nextDataIndex(i);
      if (k >= 0) {
        break;
      }
    } while (1 == 0);
    paramConsumer.write(62);
    return;
    paramConsumer.write(32);
    int j = i;
    if (i >= this.gapEnd) {
      j = i - (this.gapEnd - this.gapStart);
    }
    Object localObject = getPosNext(j << 1);
    if ((localObject instanceof Printable)) {
      ((Printable)localObject).print(paramConsumer);
    }
    for (;;)
    {
      i = k;
      break;
      paramConsumer.writeObject(localObject);
    }
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    int j = paramObjectInput.readInt();
    int i = 0;
    while (i < j)
    {
      writeObject(paramObjectInput.readObject());
      i += 1;
    }
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    Values localValues = this;
    if (isEmpty()) {
      localValues = empty;
    }
    return localValues;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    Object[] arrayOfObject = toArray();
    int j = arrayOfObject.length;
    paramObjectOutput.writeInt(j);
    int i = 0;
    while (i < j)
    {
      paramObjectOutput.writeObject(arrayOfObject[i]);
      i += 1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\Values.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */