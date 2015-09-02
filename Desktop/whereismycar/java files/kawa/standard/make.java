package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.Keyword;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;
import kawa.lang.Record;

public class make
  extends ProcedureN
{
  public Object applyN(Object[] paramArrayOfObject)
  {
    int j = paramArrayOfObject.length;
    if (j == 0) {
      throw new WrongArguments(this, j);
    }
    Object localObject2 = paramArrayOfObject[0];
    if ((localObject2 instanceof Class)) {
      localObject1 = (Class)localObject2;
    }
    while (localObject1 == null)
    {
      throw new WrongType(this, 1, localObject2, "class");
      if ((localObject2 instanceof ClassType)) {
        localObject1 = ((ClassType)localObject2).getReflectClass();
      } else {
        localObject1 = null;
      }
    }
    try
    {
      localObject1 = ((Class)localObject1).newInstance();
      int i = 1;
      while (i < j)
      {
        int k = i + 1;
        localObject2 = (Keyword)paramArrayOfObject[i];
        i = k + 1;
        Record.set1(paramArrayOfObject[k], ((Keyword)localObject2).getName(), localObject1);
      }
      return localObject1;
    }
    catch (Exception paramArrayOfObject)
    {
      throw new WrappedException(paramArrayOfObject);
    }
  }
  
  public int numArgs()
  {
    return 61441;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\make.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */