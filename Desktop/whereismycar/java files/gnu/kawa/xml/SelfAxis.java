package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class SelfAxis
  extends TreeScanner
{
  public static SelfAxis make(NodePredicate paramNodePredicate)
  {
    SelfAxis localSelfAxis = new SelfAxis();
    localSelfAxis.type = paramNodePredicate;
    return localSelfAxis;
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer)
  {
    if (this.type.isInstancePos(paramAbstractSequence, paramInt)) {
      paramPositionConsumer.writePosition(paramAbstractSequence, paramInt);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\SelfAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */