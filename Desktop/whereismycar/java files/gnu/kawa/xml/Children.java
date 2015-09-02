package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.lists.TreePosition;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;

public class Children
  extends MethodProc
{
  public static final Children children = new Children();
  
  public static void children(TreeList paramTreeList, int paramInt, Consumer paramConsumer)
  {
    int i = paramTreeList.gotoChildrenStart(paramInt);
    if (i < 0) {}
    do
    {
      return;
      int k = paramTreeList.nextDataIndex(paramInt);
      int j = paramTreeList.nextNodeIndex(i, k);
      paramInt = j;
      if (j == i) {
        paramInt = paramTreeList.nextDataIndex(i);
      }
    } while (paramInt < 0);
    if ((paramConsumer instanceof PositionConsumer)) {
      ((PositionConsumer)paramConsumer).writePosition(paramTreeList, i << 1);
    }
    for (;;)
    {
      i = paramInt;
      break;
      paramTreeList.consumeIRange(i, paramInt, paramConsumer);
    }
  }
  
  public static void children(Object paramObject, Consumer paramConsumer)
  {
    if ((paramObject instanceof TreeList)) {
      children((TreeList)paramObject, 0, paramConsumer);
    }
    do
    {
      do
      {
        return;
      } while ((!(paramObject instanceof SeqPosition)) || ((paramObject instanceof TreePosition)));
      paramObject = (SeqPosition)paramObject;
    } while (!(((SeqPosition)paramObject).sequence instanceof TreeList));
    children((TreeList)((SeqPosition)paramObject).sequence, ((SeqPosition)paramObject).ipos >> 1, paramConsumer);
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
        children(paramCallContext.getPosNext(i << 1), localConsumer);
      }
      for (;;)
      {
        i = paramCallContext.nextDataIndex(i);
        break;
        children(paramCallContext, i, localConsumer);
      }
    }
    children(localObject, localConsumer);
  }
  
  public int numArgs()
  {
    return 4097;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\Children.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */