package kawa.standard;

import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class call_with_values
  extends Procedure2
{
  public static final call_with_values callWithValues = new call_with_values();
  
  static
  {
    callWithValues.setName("call-with-values");
  }
  
  public static Object callWithValues(Procedure paramProcedure1, Procedure paramProcedure2)
    throws Throwable
  {
    paramProcedure1 = paramProcedure1.apply0();
    if ((paramProcedure1 instanceof Values)) {
      return ((Values)paramProcedure1).call_with(paramProcedure2);
    }
    return paramProcedure2.apply1(paramProcedure1);
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    Procedure.checkArgCount(this, 2);
    Object localObject2 = paramCallContext.getArgs();
    Object localObject1 = ((Procedure)localObject2[0]).apply0();
    localObject2 = (Procedure)localObject2[1];
    if ((localObject1 instanceof Values))
    {
      ((Procedure)localObject2).checkN(((Values)localObject1).getValues(), paramCallContext);
      return;
    }
    ((Procedure)localObject2).check1(localObject1, paramCallContext);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
    throws Throwable
  {
    return callWithValues((Procedure)paramObject1, (Procedure)paramObject2);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\call_with_values.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */