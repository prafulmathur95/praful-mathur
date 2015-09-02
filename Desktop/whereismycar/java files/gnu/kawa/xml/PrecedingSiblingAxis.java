package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class PrecedingSiblingAxis
  extends TreeScanner
{
  public static PrecedingSiblingAxis make(NodePredicate paramNodePredicate)
  {
    PrecedingSiblingAxis localPrecedingSiblingAxis = new PrecedingSiblingAxis();
    localPrecedingSiblingAxis.type = paramNodePredicate;
    return localPrecedingSiblingAxis;
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer)
  {
    int i = paramAbstractSequence.endPos();
    int j = paramAbstractSequence.parentPos(paramInt);
    if (j == i) {}
    do
    {
      return;
      j = paramAbstractSequence.firstChildPos(j);
    } while (j == 0);
    i = j;
    if (this.type.isInstancePos(paramAbstractSequence, j))
    {
      paramPositionConsumer.writePosition(paramAbstractSequence, j);
      i = j;
    }
    for (;;)
    {
      i = paramAbstractSequence.nextMatching(i, this.type, paramInt, false);
      if (i == 0) {
        break;
      }
      paramPositionConsumer.writePosition(paramAbstractSequence, i);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\PrecedingSiblingAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */