package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;
import gnu.lists.TreeList;

public class DescendantAxis
  extends TreeScanner
{
  public static DescendantAxis make(NodePredicate paramNodePredicate)
  {
    DescendantAxis localDescendantAxis = new DescendantAxis();
    localDescendantAxis.type = paramNodePredicate;
    return localDescendantAxis;
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer)
  {
    if (!(paramAbstractSequence instanceof TreeList)) {
      for (paramInt = paramAbstractSequence.firstChildPos(paramInt); paramInt != 0; paramInt = paramAbstractSequence.nextPos(paramInt))
      {
        if (this.type.isInstancePos(paramAbstractSequence, paramInt)) {
          paramPositionConsumer.writePosition(paramAbstractSequence, paramInt);
        }
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


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\DescendantAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */