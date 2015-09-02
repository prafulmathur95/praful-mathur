package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.Sequence;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;

public class ArraySet
  extends ProcedureN
{
  public static final ArraySet arraySet = new ArraySet();
  
  public static void arraySet(Array paramArray, Sequence paramSequence, Object paramObject)
  {
    int j = paramSequence.size();
    int[] arrayOfInt = new int[j];
    int i = 0;
    while (i < j)
    {
      arrayOfInt[i] = ((Number)paramSequence.get(i)).intValue();
      i += 1;
    }
    paramArray.set(arrayOfInt, paramObject);
  }
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3)
    throws Throwable
  {
    if ((paramObject2 instanceof Sequence))
    {
      arraySet((Array)paramObject1, (Sequence)paramObject2, paramObject3);
      return Values.empty;
    }
    return super.apply3(paramObject1, paramObject2, paramObject3);
  }
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    Array localArray = (Array)paramArrayOfObject[0];
    if (paramArrayOfObject.length == 3)
    {
      localObject = paramArrayOfObject[1];
      if ((localObject instanceof Sequence))
      {
        arraySet(localArray, (Sequence)localObject, paramArrayOfObject[2]);
        return Values.empty;
      }
    }
    int j = paramArrayOfObject.length - 2;
    Object localObject = new int[j];
    int i = j;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      localObject[i] = ((Number)paramArrayOfObject[(i + 1)]).intValue();
    }
    localArray.set((int[])localObject, paramArrayOfObject[(j + 1)]);
    return Values.empty;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\ArraySet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */