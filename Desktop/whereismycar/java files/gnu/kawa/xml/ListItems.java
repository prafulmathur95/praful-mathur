package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import java.util.Iterator;
import java.util.List;

public class ListItems
  extends MethodProc
{
  public static ListItems listItems = new ListItems();
  
  public void apply(CallContext paramCallContext)
  {
    Consumer localConsumer = paramCallContext.consumer;
    Object localObject = paramCallContext.getNextArg();
    paramCallContext.lastArg();
    paramCallContext = (List)localObject;
    if ((localObject instanceof AbstractSequence)) {
      ((AbstractSequence)localObject).consumePosRange(0, -1, localConsumer);
    }
    for (;;)
    {
      return;
      paramCallContext = paramCallContext.iterator();
      while (paramCallContext.hasNext()) {
        Values.writeValues(paramCallContext.next(), localConsumer);
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\ListItems.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */