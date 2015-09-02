package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class ChildAxis
  extends TreeScanner
{
  public static ChildAxis make(NodePredicate paramNodePredicate)
  {
    ChildAxis localChildAxis = new ChildAxis();
    localChildAxis.type = paramNodePredicate;
    return localChildAxis;
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer)
  {
    for (paramInt = paramAbstractSequence.firstChildPos(paramInt, this.type); paramInt != 0; paramInt = paramAbstractSequence.nextMatching(paramInt, this.type, -1, false)) {
      paramPositionConsumer.writePosition(paramAbstractSequence, paramInt);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\ChildAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */