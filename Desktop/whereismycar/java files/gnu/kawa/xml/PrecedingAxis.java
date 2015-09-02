package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class PrecedingAxis
  extends TreeScanner
{
  public static PrecedingAxis make(NodePredicate paramNodePredicate)
  {
    PrecedingAxis localPrecedingAxis = new PrecedingAxis();
    localPrecedingAxis.type = paramNodePredicate;
    return localPrecedingAxis;
  }
  
  private static void scan(AbstractSequence paramAbstractSequence, int paramInt1, int paramInt2, NodePredicate paramNodePredicate, PositionConsumer paramPositionConsumer)
  {
    int i = paramAbstractSequence.parentPos(paramInt1);
    if (i == paramInt2) {}
    do
    {
      return;
      scan(paramAbstractSequence, i, paramInt2, paramNodePredicate, paramPositionConsumer);
      i = paramAbstractSequence.firstChildPos(i);
    } while (i == 0);
    paramInt2 = i;
    if (paramNodePredicate.isInstancePos(paramAbstractSequence, i))
    {
      paramPositionConsumer.writePosition(paramAbstractSequence, i);
      paramInt2 = i;
    }
    for (;;)
    {
      paramInt2 = paramAbstractSequence.nextMatching(paramInt2, paramNodePredicate, paramInt1, true);
      if (paramInt2 == 0) {
        break;
      }
      paramPositionConsumer.writePosition(paramAbstractSequence, paramInt2);
    }
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer)
  {
    scan(paramAbstractSequence, paramInt, paramAbstractSequence.endPos(), this.type, paramPositionConsumer);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\PrecedingAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */