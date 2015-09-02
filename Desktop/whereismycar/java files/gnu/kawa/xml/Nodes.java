package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.lists.Sequence;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.xml.NodeTree;
import gnu.xml.XMLFilter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Nodes
  extends Values
  implements NodeList
{
  static final int POS_SIZE = 5;
  int count;
  XMLFilter curFragment;
  NodeTree curNode;
  boolean inAttribute;
  int nesting = 0;
  
  private void maybeEndNonTextNode()
  {
    int i = this.nesting - 1;
    this.nesting = i;
    if (i == 0) {
      finishFragment();
    }
  }
  
  private void maybeStartNonTextNode()
  {
    if ((this.curFragment != null) && (this.nesting == 0)) {
      finishFragment();
    }
    if (this.curFragment == null) {
      startFragment();
    }
    this.nesting += 1;
  }
  
  public static KNode root(NodeTree paramNodeTree, int paramInt)
  {
    if ((paramNodeTree.gapStart > 5) && (paramNodeTree.data[0] == 61714)) {}
    for (paramInt = 10;; paramInt = 0) {
      return KNode.make(paramNodeTree, paramInt);
    }
  }
  
  public Consumer append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    maybeStartTextNode();
    this.curFragment.write(paramCharSequence, paramInt1, paramInt2);
    return this;
  }
  
  public void beginEntity(Object paramObject)
  {
    maybeStartNonTextNode();
    this.curFragment.beginEntity(paramObject);
  }
  
  public void endAttribute()
  {
    if (!this.inAttribute) {
      return;
    }
    this.inAttribute = false;
    this.curFragment.endAttribute();
    maybeEndNonTextNode();
  }
  
  public void endDocument()
  {
    this.curFragment.endDocument();
    maybeEndNonTextNode();
  }
  
  public void endElement()
  {
    this.curFragment.endElement();
    maybeEndNonTextNode();
  }
  
  public void endEntity()
  {
    this.curFragment.endEntity();
    maybeEndNonTextNode();
  }
  
  public int find(Object paramObject)
  {
    int i;
    if (this.gapStart > 0)
    {
      i = getIntN(this.gapStart - 5 + 1);
      if (this.objects[i] != paramObject) {}
    }
    int j;
    do
    {
      return i;
      if (this.gapEnd >= this.data.length) {
        break;
      }
      j = getIntN(this.gapEnd + 1);
      i = j;
    } while (this.objects[j] == paramObject);
    return super.find(paramObject);
  }
  
  void finishFragment()
  {
    this.curNode = null;
    this.curFragment = null;
  }
  
  public Object get(int paramInt)
  {
    int i = paramInt * 5;
    paramInt = i;
    if (i >= this.gapStart) {
      paramInt = i + (this.gapEnd - this.gapStart);
    }
    if ((paramInt < 0) || (paramInt >= this.data.length)) {
      throw new IndexOutOfBoundsException();
    }
    if (this.data[paramInt] != 61711) {
      throw new RuntimeException("internal error - unexpected data");
    }
    return KNode.make((NodeTree)this.objects[getIntN(paramInt + 1)], getIntN(paramInt + 3));
  }
  
  public int getLength()
  {
    return this.count;
  }
  
  public int getPos(int paramInt)
  {
    int i = paramInt * 5;
    paramInt = i;
    if (i >= this.gapStart) {
      paramInt = i + (this.gapEnd - this.gapStart);
    }
    if (this.data[paramInt] != 61711) {
      throw new RuntimeException("internal error - unexpected data");
    }
    return getIntN(paramInt + 3);
  }
  
  public Object getPosNext(int paramInt)
  {
    paramInt = posToDataIndex(paramInt);
    if (paramInt == this.data.length) {
      return Sequence.eofValue;
    }
    if (this.data[paramInt] != 61711) {
      throw new RuntimeException("internal error - unexpected data");
    }
    return KNode.make((NodeTree)this.objects[getIntN(paramInt + 1)], getIntN(paramInt + 3));
  }
  
  public AbstractSequence getSeq(int paramInt)
  {
    int i = paramInt * 5;
    paramInt = i;
    if (i >= this.gapStart) {
      paramInt = i + (this.gapEnd - this.gapStart);
    }
    if ((paramInt < 0) || (paramInt >= this.data.length)) {
      return null;
    }
    if (this.data[paramInt] != 61711) {
      throw new RuntimeException("internal error - unexpected data");
    }
    return (AbstractSequence)this.objects[getIntN(paramInt + 1)];
  }
  
  void handleNonNode()
  {
    if (this.curFragment == null) {
      throw new ClassCastException("atomic value where node is required");
    }
  }
  
  public Node item(int paramInt)
  {
    if (paramInt >= this.count) {
      return null;
    }
    return (Node)get(paramInt);
  }
  
  void maybeStartTextNode()
  {
    if (this.curFragment == null) {
      throw new IllegalArgumentException("non-node where node required");
    }
  }
  
  public int size()
  {
    return this.count;
  }
  
  public void startAttribute(Object paramObject)
  {
    maybeStartNonTextNode();
    this.curFragment.startAttribute(paramObject);
    this.inAttribute = true;
  }
  
  public void startDocument()
  {
    maybeStartNonTextNode();
    this.curFragment.startDocument();
  }
  
  public void startElement(Object paramObject)
  {
    maybeStartNonTextNode();
    this.curFragment.startElement(paramObject);
  }
  
  void startFragment()
  {
    this.curNode = new NodeTree();
    this.curFragment = new XMLFilter(this.curNode);
    writePosition(this.curNode, 0);
  }
  
  public void write(int paramInt)
  {
    maybeStartTextNode();
    this.curFragment.write(paramInt);
  }
  
  public void write(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    maybeStartTextNode();
    this.curFragment.write(paramCharSequence, paramInt1, paramInt2);
  }
  
  public void write(String paramString)
  {
    maybeStartTextNode();
    this.curFragment.write(paramString);
  }
  
  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    maybeStartTextNode();
    this.curFragment.write(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void writeBoolean(boolean paramBoolean)
  {
    handleNonNode();
    this.curFragment.writeBoolean(paramBoolean);
  }
  
  public void writeCDATA(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    maybeStartNonTextNode();
    this.curFragment.writeCDATA(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void writeComment(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    maybeStartNonTextNode();
    this.curFragment.writeComment(paramArrayOfChar, paramInt1, paramInt2);
    maybeEndNonTextNode();
  }
  
  public void writeDouble(double paramDouble)
  {
    handleNonNode();
    this.curFragment.writeDouble(paramDouble);
  }
  
  public void writeFloat(float paramFloat)
  {
    handleNonNode();
    this.curFragment.writeFloat(paramFloat);
  }
  
  public void writeInt(int paramInt)
  {
    handleNonNode();
    this.curFragment.writeInt(paramInt);
  }
  
  public void writeLong(long paramLong)
  {
    handleNonNode();
    this.curFragment.writeLong(paramLong);
  }
  
  public void writeObject(Object paramObject)
  {
    if (this.curFragment != null)
    {
      if ((this.nesting == 0) && (((paramObject instanceof SeqPosition)) || ((paramObject instanceof TreeList)))) {
        finishFragment();
      }
    }
    else
    {
      if (!(paramObject instanceof SeqPosition)) {
        break label66;
      }
      paramObject = (SeqPosition)paramObject;
      writePosition(((SeqPosition)paramObject).sequence, ((SeqPosition)paramObject).ipos);
      return;
    }
    this.curFragment.writeObject(paramObject);
    return;
    label66:
    if ((paramObject instanceof TreeList))
    {
      writePosition((TreeList)paramObject, 0);
      return;
    }
    handleNonNode();
    this.curFragment.writeObject(paramObject);
  }
  
  public void writePosition(AbstractSequence paramAbstractSequence, int paramInt)
  {
    this.count += 1;
    super.writePosition(paramAbstractSequence, paramInt);
  }
  
  public void writeProcessingInstruction(String paramString, char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    maybeStartNonTextNode();
    this.curFragment.writeProcessingInstruction(paramString, paramArrayOfChar, paramInt1, paramInt2);
    maybeEndNonTextNode();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\Nodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */