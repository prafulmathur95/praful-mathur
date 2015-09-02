package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.lists.TreePosition;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import java.io.PrintStream;

public class Attributes
  extends MethodProc
{
  public static final Attributes attributes = new Attributes();
  
  public static void attributes(TreeList paramTreeList, int paramInt, Consumer paramConsumer)
  {
    paramInt = paramTreeList.gotoAttributesStart(paramInt);
    System.out.print("Attributes called, at:" + paramInt + " ");
    paramTreeList.dump();
    int j;
    if (paramInt >= 0)
    {
      j = paramInt << 1;
      if (paramTreeList.getNextKind(j) == 35) {}
    }
    else
    {
      return;
    }
    int i = paramTreeList.nextDataIndex(paramInt);
    if ((paramConsumer instanceof PositionConsumer)) {
      ((PositionConsumer)paramConsumer).writePosition(paramTreeList, j);
    }
    for (;;)
    {
      paramInt = i;
      break;
      paramTreeList.consumeIRange(paramInt, i, paramConsumer);
    }
  }
  
  public static void attributes(Object paramObject, Consumer paramConsumer)
  {
    if ((paramObject instanceof TreeList)) {
      attributes((TreeList)paramObject, 0, paramConsumer);
    }
    do
    {
      do
      {
        return;
      } while ((!(paramObject instanceof SeqPosition)) || ((paramObject instanceof TreePosition)));
      paramObject = (SeqPosition)paramObject;
    } while (!(((SeqPosition)paramObject).sequence instanceof TreeList));
    attributes((TreeList)((SeqPosition)paramObject).sequence, ((SeqPosition)paramObject).ipos >> 1, paramConsumer);
  }
  
  public void apply(CallContext paramCallContext)
  {
    Consumer localConsumer = paramCallContext.consumer;
    Object localObject = paramCallContext.getNextArg();
    paramCallContext.lastArg();
    if ((localObject instanceof Values))
    {
      paramCallContext = (TreeList)localObject;
      int i = 0;
      int j = paramCallContext.getNextKind(i << 1);
      if (j == 0) {
        return;
      }
      if (j == 32) {
        attributes(paramCallContext.getPosNext(i << 1), localConsumer);
      }
      for (;;)
      {
        i = paramCallContext.nextDataIndex(i);
        break;
        attributes(paramCallContext, i, localConsumer);
      }
    }
    attributes(localObject, localConsumer);
  }
  
  public int numArgs()
  {
    return 4097;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\Attributes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */