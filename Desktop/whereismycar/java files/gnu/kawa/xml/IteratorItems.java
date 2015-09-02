package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import java.util.Iterator;

public class IteratorItems
  extends MethodProc
{
  public static IteratorItems iteratorItems = new IteratorItems();
  
  public void apply(CallContext paramCallContext)
  {
    Consumer localConsumer = paramCallContext.consumer;
    Object localObject = paramCallContext.getNextArg();
    paramCallContext.lastArg();
    paramCallContext = (Iterator)localObject;
    while (paramCallContext.hasNext()) {
      Values.writeValues(paramCallContext.next(), localConsumer);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\IteratorItems.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */