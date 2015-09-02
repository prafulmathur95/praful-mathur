package gnu.kawa.functions;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class CountValues
  extends Procedure1
{
  public static final CountValues countValues = new CountValues();
  
  public static int countValues(Object paramObject)
  {
    if ((paramObject instanceof Values)) {
      return ((Values)paramObject).size();
    }
    return 1;
  }
  
  public void apply(CallContext paramCallContext)
  {
    Consumer localConsumer = paramCallContext.consumer;
    Object localObject = paramCallContext.getNextArg();
    paramCallContext.lastArg();
    localConsumer.writeInt(countValues(localObject));
  }
  
  public Object apply1(Object paramObject)
  {
    return IntNum.make(countValues(paramObject));
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\CountValues.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */