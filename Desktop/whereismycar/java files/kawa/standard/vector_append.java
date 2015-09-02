package kawa.standard;

import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongType;

public class vector_append
  extends ProcedureN
{
  public static final vector_append vectorAppend = new vector_append("vector-append");
  
  public vector_append(String paramString)
  {
    super(paramString);
  }
  
  public static FVector apply$V(Object[] paramArrayOfObject)
  {
    int i = 0;
    int m = paramArrayOfObject.length;
    int j = m;
    Object localObject;
    for (;;)
    {
      j -= 1;
      if (j < 0) {
        break;
      }
      localObject = paramArrayOfObject[j];
      if ((localObject instanceof FVector))
      {
        i += ((FVector)localObject).size();
      }
      else
      {
        k = LList.listLength(localObject, false);
        if (k < 0) {
          throw new WrongType(vectorAppend, j, localObject, "list or vector");
        }
        i += k;
      }
    }
    Object[] arrayOfObject = new Object[i];
    i = 0;
    int k = 0;
    if (k < m)
    {
      localObject = paramArrayOfObject[k];
      if ((localObject instanceof FVector))
      {
        localObject = (FVector)localObject;
        int n = ((FVector)localObject).size();
        j = 0;
        while (j < n)
        {
          arrayOfObject[i] = ((FVector)localObject).get(j);
          j += 1;
          i += 1;
        }
        j = i;
      }
      do
      {
        k += 1;
        i = j;
        break;
        j = i;
      } while (!(localObject instanceof Pair));
      for (;;)
      {
        j = i;
        if (localObject == LList.Empty) {
          break;
        }
        localObject = (Pair)localObject;
        arrayOfObject[i] = ((Pair)localObject).getCar();
        localObject = ((Pair)localObject).getCdr();
        i += 1;
      }
    }
    return new FVector(arrayOfObject);
  }
  
  public Object applyN(Object[] paramArrayOfObject)
  {
    return apply$V(paramArrayOfObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\vector_append.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */