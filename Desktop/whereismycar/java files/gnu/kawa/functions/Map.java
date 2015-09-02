package gnu.kawa.functions;

import gnu.expr.Declaration;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;

public class Map
  extends ProcedureN
{
  final Declaration applyFieldDecl;
  final ApplyToArgs applyToArgs;
  boolean collect;
  final IsEq isEq;
  
  public Map(boolean paramBoolean, ApplyToArgs paramApplyToArgs, Declaration paramDeclaration, IsEq paramIsEq) {}
  
  public static void forEach1(Procedure paramProcedure, Object paramObject)
    throws Throwable
  {
    while (paramObject != LList.Empty)
    {
      paramObject = (Pair)paramObject;
      paramProcedure.apply1(((Pair)paramObject).getCar());
      paramObject = ((Pair)paramObject).getCdr();
    }
  }
  
  public static Object map1(Procedure paramProcedure, Object paramObject)
    throws Throwable
  {
    Object localObject1 = LList.Empty;
    Pair localPair = null;
    Object localObject2 = paramObject;
    paramObject = localPair;
    if (localObject2 != LList.Empty)
    {
      localPair = (Pair)localObject2;
      localObject2 = new Pair(paramProcedure.apply1(localPair.getCar()), LList.Empty);
      if (paramObject == null) {
        localObject1 = localObject2;
      }
      for (;;)
      {
        paramObject = localObject2;
        localObject2 = localPair.getCdr();
        break;
        ((Pair)paramObject).setCdr(localObject2);
      }
    }
    return localObject1;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
    throws Throwable
  {
    if ((paramObject1 instanceof Procedure))
    {
      paramObject1 = (Procedure)paramObject1;
      if (this.collect) {
        return map1((Procedure)paramObject1, paramObject2);
      }
      forEach1((Procedure)paramObject1, paramObject2);
      return Values.empty;
    }
    return applyN(new Object[] { paramObject1, paramObject2 });
  }
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    int k = paramArrayOfObject.length - 1;
    if ((k == 1) && ((paramArrayOfObject[0] instanceof Procedure)))
    {
      localObject1 = (Procedure)paramArrayOfObject[0];
      if (this.collect)
      {
        localObject1 = map1((Procedure)localObject1, paramArrayOfObject[1]);
        return localObject1;
      }
      forEach1((Procedure)localObject1, paramArrayOfObject[1]);
      return Values.empty;
    }
    Object localObject3 = null;
    label73:
    Object[] arrayOfObject2;
    int i;
    Object[] arrayOfObject1;
    Object localObject2;
    if (this.collect)
    {
      localObject1 = LList.Empty;
      arrayOfObject2 = new Object[k];
      System.arraycopy(paramArrayOfObject, 1, arrayOfObject2, 0, k);
      if (!(paramArrayOfObject[0] instanceof Procedure)) {
        break label187;
      }
      i = 0;
      arrayOfObject1 = new Object[k];
      localObject2 = (Procedure)paramArrayOfObject[0];
      paramArrayOfObject = (Object[])localObject1;
    }
    label187:
    label215:
    do
    {
      for (;;)
      {
        int j = 0;
        for (;;)
        {
          if (j >= k) {
            break label215;
          }
          Object localObject4 = arrayOfObject2[j];
          localObject1 = paramArrayOfObject;
          if (localObject4 == LList.Empty) {
            break;
          }
          localObject1 = (Pair)localObject4;
          arrayOfObject1[(i + j)] = ((Pair)localObject1).getCar();
          arrayOfObject2[j] = ((Pair)localObject1).getCdr();
          j += 1;
        }
        localObject1 = Values.empty;
        break label73;
        i = 1;
        arrayOfObject1 = new Object[k + 1];
        arrayOfObject1[0] = paramArrayOfObject[0];
        localObject2 = this.applyToArgs;
        paramArrayOfObject = (Object[])localObject1;
      }
      localObject1 = ((Procedure)localObject2).applyN(arrayOfObject1);
    } while (!this.collect);
    Object localObject1 = new Pair(localObject1, LList.Empty);
    if (localObject3 == null) {
      paramArrayOfObject = (Object[])localObject1;
    }
    for (;;)
    {
      localObject3 = localObject1;
      break;
      ((Pair)localObject3).setCdr(localObject1);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\Map.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */