package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class AncestorOrSelfAxis
  extends TreeScanner
{
  public static AncestorOrSelfAxis make(NodePredicate paramNodePredicate)
  {
    AncestorOrSelfAxis localAncestorOrSelfAxis = new AncestorOrSelfAxis();
    localAncestorOrSelfAxis.type = paramNodePredicate;
    return localAncestorOrSelfAxis;
  }
  
  private static void scan(AbstractSequence paramAbstractSequence, int paramInt1, int paramInt2, NodePredicate paramNodePredicate, PositionConsumer paramPositionConsumer)
  {
    if (paramInt1 != paramInt2)
    {
      scan(paramAbstractSequence, paramAbstractSequence.parentPos(paramInt1), paramInt2, paramNodePredicate, paramPositionConsumer);
      if (paramNodePredicate.isInstancePos(paramAbstractSequence, paramInt1)) {
        paramPositionConsumer.writePosition(paramAbstractSequence, paramInt1);
      }
    }
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer)
  {
    scan(paramAbstractSequence, paramInt, paramAbstractSequence.endPos(), this.type, paramPositionConsumer);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\AncestorOrSelfAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */