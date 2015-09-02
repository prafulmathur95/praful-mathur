package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;
import gnu.lists.TreeList;

public class DescendantOrSelfAxis
  extends TreeScanner
{
  public static final DescendantOrSelfAxis anyNode = new DescendantOrSelfAxis(NodeType.anyNodeTest);
  
  private DescendantOrSelfAxis(NodePredicate paramNodePredicate)
  {
    this.type = paramNodePredicate;
  }
  
  public static DescendantOrSelfAxis make(NodePredicate paramNodePredicate)
  {
    if (paramNodePredicate == NodeType.anyNodeTest) {
      return anyNode;
    }
    return new DescendantOrSelfAxis(paramNodePredicate);
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer)
  {
    if (this.type.isInstancePos(paramAbstractSequence, paramInt)) {
      paramPositionConsumer.writePosition(paramAbstractSequence, paramInt);
    }
    if (!(paramAbstractSequence instanceof TreeList)) {
      for (paramInt = paramAbstractSequence.firstChildPos(paramInt); paramInt != 0; paramInt = paramAbstractSequence.nextPos(paramInt)) {
        scan(paramAbstractSequence, paramInt, paramPositionConsumer);
      }
    }
    int i = paramAbstractSequence.nextPos(paramInt);
    for (;;)
    {
      paramInt = paramAbstractSequence.nextMatching(paramInt, this.type, i, true);
      if (paramInt == 0) {
        return;
      }
      paramPositionConsumer.writePosition(paramAbstractSequence, paramInt);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\DescendantOrSelfAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */