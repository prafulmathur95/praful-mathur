package kawa.standard;

import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongType;

public class append
  extends ProcedureN
{
  public static final append append = new append();
  
  static
  {
    append.setName("append");
  }
  
  public static Object append$V(Object[] paramArrayOfObject)
  {
    int i = paramArrayOfObject.length;
    if (i == 0) {
      localObject2 = LList.Empty;
    }
    Object localObject1;
    do
    {
      return localObject2;
      localObject1 = paramArrayOfObject[(i - 1)];
      i -= 1;
      i -= 1;
      localObject2 = localObject1;
    } while (i < 0);
    Object localObject4 = paramArrayOfObject[i];
    Object localObject3 = null;
    Object localObject2 = null;
    if ((localObject4 instanceof Pair))
    {
      Pair localPair = (Pair)localObject4;
      localObject4 = new Pair(localPair.getCar(), null);
      if (localObject3 == null) {
        localObject2 = localObject4;
      }
      for (;;)
      {
        localObject3 = localObject4;
        localObject4 = localPair.getCdr();
        break;
        ((Pair)localObject3).setCdr(localObject4);
      }
    }
    if (localObject4 != LList.Empty) {
      throw new WrongType(append, i + 1, paramArrayOfObject[i], "list");
    }
    if (localObject3 != null) {
      ((Pair)localObject3).setCdr(localObject1);
    }
    for (;;)
    {
      localObject1 = localObject2;
      break;
      localObject2 = localObject1;
    }
  }
  
  public Object applyN(Object[] paramArrayOfObject)
  {
    return append$V(paramArrayOfObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\append.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */