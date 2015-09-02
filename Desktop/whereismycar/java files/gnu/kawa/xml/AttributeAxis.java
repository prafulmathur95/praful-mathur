package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

public class AttributeAxis
  extends TreeScanner
{
  public static AttributeAxis make(NodePredicate paramNodePredicate)
  {
    AttributeAxis localAttributeAxis = new AttributeAxis();
    localAttributeAxis.type = paramNodePredicate;
    return localAttributeAxis;
  }
  
  public void scan(AbstractSequence paramAbstractSequence, int paramInt, PositionConsumer paramPositionConsumer)
  {
    paramInt = paramAbstractSequence.firstAttributePos(paramInt);
    if ((paramInt != 0) && (paramAbstractSequence.getNextKind(paramInt) == 35))
    {
      if (this.type.isInstancePos(paramAbstractSequence, paramInt)) {
        paramPositionConsumer.writePosition(paramAbstractSequence, paramInt);
      }
      while (paramAbstractSequence.getNextKind(paramInt) == 35)
      {
        paramInt = paramAbstractSequence.nextPos(paramInt);
        break;
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\AttributeAxis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */