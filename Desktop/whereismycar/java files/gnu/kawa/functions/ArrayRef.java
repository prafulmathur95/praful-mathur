package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.Sequence;
import gnu.mapping.ProcedureN;

public class ArrayRef
  extends ProcedureN
{
  public static final ArrayRef arrayRef = new ArrayRef();
  
  public static Object arrayRef(Array paramArray, Sequence paramSequence)
  {
    int j = paramSequence.size();
    int[] arrayOfInt = new int[j];
    int i = 0;
    while (i < j)
    {
      arrayOfInt[i] = ((Number)paramSequence.get(i)).intValue();
      i += 1;
    }
    return paramArray.get(arrayOfInt);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
    throws Throwable
  {
    if ((paramObject2 instanceof Sequence)) {
      return arrayRef((Array)paramObject1, (Sequence)paramObject2);
    }
    return super.apply2(paramObject1, paramObject2);
  }
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    Array localArray = (Array)paramArrayOfObject[0];
    if (paramArrayOfObject.length == 2)
    {
      localObject = paramArrayOfObject[1];
      if ((localObject instanceof Sequence)) {
        return arrayRef(localArray, (Sequence)localObject);
      }
    }
    Object localObject = new int[paramArrayOfObject.length - 1];
    int i = paramArrayOfObject.length - 1;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      localObject[i] = ((Number)paramArrayOfObject[(i + 1)]).intValue();
    }
    return localArray.get((int[])localObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\ArrayRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */