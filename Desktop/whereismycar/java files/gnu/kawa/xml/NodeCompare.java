package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.SeqPosition;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import gnu.mapping.WrongType;

public class NodeCompare
  extends Procedure2
{
  public static final NodeCompare $Eq = make("is", 8);
  public static final NodeCompare $Gr = make(">>", 16);
  public static final NodeCompare $Ls = make("<<", 4);
  public static final NodeCompare $Ne = make("isnot", 20);
  static final int RESULT_EQU = 0;
  static final int RESULT_GRT = 1;
  static final int RESULT_LSS = -1;
  static final int TRUE_IF_EQU = 8;
  static final int TRUE_IF_GRT = 16;
  static final int TRUE_IF_LSS = 4;
  int flags;
  
  public static NodeCompare make(String paramString, int paramInt)
  {
    NodeCompare localNodeCompare = new NodeCompare();
    localNodeCompare.setName(paramString);
    localNodeCompare.flags = paramInt;
    return localNodeCompare;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
  {
    Object localObject;
    if ((paramObject1 == null) || (paramObject2 == null)) {
      localObject = null;
    }
    do
    {
      return localObject;
      localObject = paramObject1;
    } while (paramObject1 == Values.empty);
    if (paramObject2 == Values.empty) {
      return paramObject2;
    }
    int j;
    if ((paramObject1 instanceof AbstractSequence))
    {
      paramObject1 = (AbstractSequence)paramObject1;
      i = ((AbstractSequence)paramObject1).startPos();
      if (!(paramObject2 instanceof AbstractSequence)) {
        break label132;
      }
      paramObject2 = (AbstractSequence)paramObject2;
      j = ((AbstractSequence)paramObject2).startPos();
      if (paramObject1 != paramObject2) {
        break label165;
      }
    }
    for (int i = ((AbstractSequence)paramObject1).compare(i, j);; i = ((AbstractSequence)paramObject1).stableCompare((AbstractSequence)paramObject2))
    {
      if ((1 << i + 3 & this.flags) == 0) {
        break label197;
      }
      return Boolean.TRUE;
      SeqPosition localSeqPosition;
      try
      {
        localSeqPosition = (SeqPosition)paramObject1;
        localObject = localSeqPosition.sequence;
        i = localSeqPosition.getPos();
        paramObject1 = localObject;
      }
      catch (ClassCastException paramObject2)
      {
        throw WrongType.make((ClassCastException)paramObject2, this, 1, paramObject1);
      }
      try
      {
        label132:
        localSeqPosition = (SeqPosition)paramObject2;
        localObject = localSeqPosition.sequence;
        j = localSeqPosition.getPos();
        paramObject2 = localObject;
      }
      catch (ClassCastException paramObject1)
      {
        throw WrongType.make((ClassCastException)paramObject1, this, 2, paramObject2);
      }
      label165:
      if (this == $Eq) {
        return Boolean.FALSE;
      }
      if (this == $Ne) {
        return Boolean.TRUE;
      }
    }
    label197:
    return Boolean.FALSE;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\NodeCompare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */