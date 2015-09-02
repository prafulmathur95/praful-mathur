package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class FollowingAxis
  extends TreeScanner
{
  public static FollowingAxis make(NodePredicate paramNodePredicate)
  {
    FollowingAxis localFollowingAxis = new FollowingAxis();
    localFollowingAxis.type = paramNodePredicate;
    return localFollowingAxis;
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer)
  {
    int j = paramAbstractSequence.endPos();
    int i = paramAbstractSequence.nextPos(paramInt);
    paramInt = i;
    if (i != 0)
    {
      paramInt = i;
      if (this.type.isInstancePos(paramAbstractSequence, i))
      {
        paramPositionConsumer.writePosition(paramAbstractSequence, i);
        paramInt = i;
      }
    }
    for (;;)
    {
      paramInt = paramAbstractSequence.nextMatching(paramInt, this.type, j, true);
      if (paramInt == 0) {
        return;
      }
      paramPositionConsumer.writePosition(paramAbstractSequence, paramInt);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\FollowingAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */