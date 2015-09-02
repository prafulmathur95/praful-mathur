package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class IntersectNodes
  extends Procedure2
{
  public static final IntersectNodes exceptNodes = new IntersectNodes(true);
  public static final IntersectNodes intersectNodes = new IntersectNodes(false);
  boolean isExcept;
  
  public IntersectNodes(boolean paramBoolean)
  {
    this.isExcept = paramBoolean;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
  {
    SortedNodes localSortedNodes1 = new SortedNodes();
    SortedNodes localSortedNodes2 = new SortedNodes();
    SortedNodes localSortedNodes3 = new SortedNodes();
    Values.writeValues(paramObject1, localSortedNodes1);
    Values.writeValues(paramObject2, localSortedNodes2);
    int i = 0;
    paramObject1 = null;
    int k = 0;
    int j = 0;
    int m = 0;
    paramObject2 = localSortedNodes1.getSeq(m);
    if (paramObject2 == null) {
      return localSortedNodes3;
    }
    int i1 = localSortedNodes1.getPos(m);
    int n;
    if (j == -1)
    {
      j = AbstractSequence.compare((AbstractSequence)paramObject2, i1, (AbstractSequence)paramObject1, k);
      n = k;
    }
    for (;;)
    {
      label94:
      k = j;
      if (j > 0)
      {
        paramObject1 = localSortedNodes2.getSeq(i);
        if (paramObject1 == null) {
          k = -2;
        }
      }
      else
      {
        if (k != 0) {
          break label205;
        }
      }
      label205:
      for (int i2 = 1;; i2 = 0)
      {
        if (i2 != this.isExcept) {
          localSortedNodes3.writePosition((AbstractSequence)paramObject2, i1);
        }
        m += 1;
        j = k;
        k = n;
        break;
        if (j != 0) {
          break label211;
        }
        j = 1;
        n = k;
        break label94;
        n = localSortedNodes2.getPos(i);
        j = AbstractSequence.compare((AbstractSequence)paramObject2, i1, (AbstractSequence)paramObject1, n);
        i += 1;
        break label94;
      }
      label211:
      n = k;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\IntersectNodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */