package gnu.kawa.functions;

import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;

public class Apply
  extends ProcedureN
{
  ApplyToArgs applyToArgs;
  
  public Apply(String paramString, ApplyToArgs paramApplyToArgs)
  {
    super(paramString);
    this.applyToArgs = paramApplyToArgs;
  }
  
  public static Object[] getArguments(Object[] paramArrayOfObject, int paramInt, Procedure paramProcedure)
  {
    int k = paramArrayOfObject.length;
    if (k < paramInt + 1) {
      throw new WrongArguments("apply", 2, "(apply proc [args] args) [count:" + k + " skip:" + paramInt + "]");
    }
    Object localObject = paramArrayOfObject[(k - 1)];
    int i;
    if ((localObject instanceof Object[]))
    {
      Object[] arrayOfObject = (Object[])localObject;
      if (k == 2) {
        return arrayOfObject;
      }
      i = arrayOfObject.length;
    }
    while (i < 0)
    {
      throw new WrongType(paramProcedure, k, localObject, "sequence or array");
      if ((localObject instanceof Sequence)) {
        i = ((Sequence)localObject).size();
      } else {
        i = -1;
      }
    }
    paramProcedure = new Object[i + (k - paramInt - 1)];
    int j = 0;
    while (j < k - paramInt - 1)
    {
      paramProcedure[j] = paramArrayOfObject[(j + paramInt)];
      j += 1;
    }
    paramInt = j;
    paramArrayOfObject = (Object[])localObject;
    k = i;
    if ((localObject instanceof Object[])) {
      System.arraycopy((Object[])localObject, 0, paramProcedure, j, i);
    }
    for (;;)
    {
      return paramProcedure;
      while ((paramArrayOfObject instanceof Pair))
      {
        paramArrayOfObject = (Pair)paramArrayOfObject;
        paramProcedure[paramInt] = paramArrayOfObject.getCar();
        paramArrayOfObject = paramArrayOfObject.getCdr();
        k -= 1;
        paramInt += 1;
      }
      if (k > 0)
      {
        paramArrayOfObject = (Sequence)paramArrayOfObject;
        i = 0;
        while (i < k)
        {
          paramProcedure[paramInt] = paramArrayOfObject.get(i);
          i += 1;
          paramInt += 1;
        }
      }
    }
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    Object[] arrayOfObject = paramCallContext.getArgs();
    this.applyToArgs.checkN(getArguments(arrayOfObject, 0, this), paramCallContext);
  }
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    return this.applyToArgs.applyN(getArguments(paramArrayOfObject, 0, this));
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\Apply.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */