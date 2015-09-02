package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreePosition;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.xml.NodeTree;
import gnu.xml.XMLPrinter;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public abstract class KNode
  extends SeqPosition
  implements Node, Consumable
{
  public KNode(NodeTree paramNodeTree, int paramInt)
  {
    super(paramNodeTree, paramInt);
  }
  
  public static Object atomicValue(Object paramObject)
  {
    Object localObject = paramObject;
    if ((paramObject instanceof KNode))
    {
      paramObject = (KNode)paramObject;
      localObject = ((NodeTree)((KNode)paramObject).sequence).typedValue(((KNode)paramObject).ipos);
    }
    return localObject;
  }
  
  public static KNode coerce(Object paramObject)
  {
    if ((paramObject instanceof KNode)) {
      return (KNode)paramObject;
    }
    if ((paramObject instanceof NodeTree))
    {
      paramObject = (NodeTree)paramObject;
      return make((NodeTree)paramObject, ((NodeTree)paramObject).startPos());
    }
    if (((paramObject instanceof SeqPosition)) && (!(paramObject instanceof TreePosition)))
    {
      paramObject = (SeqPosition)paramObject;
      if ((((SeqPosition)paramObject).sequence instanceof NodeTree)) {
        return make((NodeTree)((SeqPosition)paramObject).sequence, ((SeqPosition)paramObject).ipos);
      }
    }
    return null;
  }
  
  public static String getNodeValue(NodeTree paramNodeTree, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    getNodeValue(paramNodeTree, paramInt, localStringBuffer);
    return localStringBuffer.toString();
  }
  
  public static void getNodeValue(NodeTree paramNodeTree, int paramInt, StringBuffer paramStringBuffer)
  {
    paramNodeTree.stringValue(paramNodeTree.posToDataIndex(paramInt), paramStringBuffer);
  }
  
  public static KNode make(NodeTree paramNodeTree)
  {
    return make(paramNodeTree, 0);
  }
  
  public static KNode make(NodeTree paramNodeTree, int paramInt)
  {
    int j = paramNodeTree.posToDataIndex(paramInt);
    int i = paramInt;
    paramInt = j;
    while ((paramInt < paramNodeTree.data.length) && (paramNodeTree.data[paramInt] == 61714))
    {
      i = paramInt + 5;
      paramInt = i;
      if (i == paramNodeTree.gapStart) {
        paramInt = paramNodeTree.gapEnd;
      }
      i = paramInt << 1;
    }
    switch (paramNodeTree.getNextKindI(paramNodeTree.posToDataIndex(i)))
    {
    }
    do
    {
      return new KText(paramNodeTree, i);
      return new KElement(paramNodeTree, i);
      return new KAttr(paramNodeTree, i);
      return new KDocument(paramNodeTree, i);
      return new KCDATASection(paramNodeTree, i);
      return new KComment(paramNodeTree, i);
      return new KProcessingInstruction(paramNodeTree, i);
    } while (paramNodeTree.isEmpty());
    return null;
  }
  
  public Node appendChild(Node paramNode)
    throws DOMException
  {
    throw new DOMException((short)7, "appendChild not supported");
  }
  
  public Path baseURI()
  {
    return ((NodeTree)this.sequence).baseUriOfPos(this.ipos, true);
  }
  
  public Node cloneNode(boolean paramBoolean)
  {
    if (!paramBoolean) {
      throw new UnsupportedOperationException("shallow cloneNode not implemented");
    }
    NodeTree localNodeTree = new NodeTree();
    ((NodeTree)this.sequence).consumeNext(this.ipos, localNodeTree);
    return make(localNodeTree);
  }
  
  public short compareDocumentPosition(Node paramNode)
    throws DOMException
  {
    if (!(paramNode instanceof KNode)) {
      throw new DOMException((short)9, "other Node is a " + paramNode.getClass().getName());
    }
    paramNode = (KNode)paramNode;
    AbstractSequence localAbstractSequence = paramNode.sequence;
    if (this.sequence == localAbstractSequence) {}
    for (int i = localAbstractSequence.compare(this.ipos, paramNode.ipos);; i = this.sequence.stableCompare(localAbstractSequence)) {
      return (short)i;
    }
  }
  
  public void consume(Consumer paramConsumer)
  {
    if ((paramConsumer instanceof PositionConsumer))
    {
      ((PositionConsumer)paramConsumer).consume(this);
      return;
    }
    ((NodeTree)this.sequence).consumeNext(this.ipos, paramConsumer);
  }
  
  public KNode copy()
  {
    return make((NodeTree)this.sequence, this.sequence.copyPos(getPos()));
  }
  
  public NamedNodeMap getAttributes()
  {
    throw new UnsupportedOperationException("getAttributes not implemented yet");
  }
  
  public String getBaseURI()
  {
    Path localPath = ((NodeTree)this.sequence).baseUriOfPos(this.ipos, true);
    if (localPath == null) {
      return null;
    }
    return localPath.toString();
  }
  
  public NodeList getChildNodes()
  {
    SortedNodes localSortedNodes = new SortedNodes();
    for (int i = this.sequence.firstChildPos(this.ipos); i != 0; i = this.sequence.nextPos(i)) {
      localSortedNodes.writePosition(this.sequence, i);
    }
    return localSortedNodes;
  }
  
  public NodeList getElementsByTagName(String paramString)
  {
    throw new UnsupportedOperationException("getElementsByTagName not implemented yet");
  }
  
  public Object getFeature(String paramString1, String paramString2)
  {
    return null;
  }
  
  public Node getFirstChild()
  {
    int i = ((NodeTree)this.sequence).posFirstChild(this.ipos);
    return make((NodeTree)this.sequence, i);
  }
  
  public Node getLastChild()
  {
    int j = 0;
    for (int i = this.sequence.firstChildPos(this.ipos); i != 0; i = this.sequence.nextPos(i)) {
      j = i;
    }
    if (j == 0) {
      return null;
    }
    return make((NodeTree)this.sequence, j);
  }
  
  public String getLocalName()
  {
    return ((NodeTree)this.sequence).posLocalName(this.ipos);
  }
  
  public String getNamespaceURI()
  {
    return ((NodeTree)this.sequence).posNamespaceURI(this.ipos);
  }
  
  public Node getNextSibling()
  {
    int i = ((NodeTree)this.sequence).nextPos(this.ipos);
    if (i == 0) {
      return null;
    }
    return make((NodeTree)this.sequence, i);
  }
  
  public String getNodeName()
  {
    return this.sequence.getNextTypeName(this.ipos);
  }
  
  public Object getNodeNameObject()
  {
    return ((NodeTree)this.sequence).getNextTypeObject(this.ipos);
  }
  
  public Symbol getNodeSymbol()
  {
    Object localObject = ((NodeTree)this.sequence).getNextTypeObject(this.ipos);
    if (localObject == null) {
      return null;
    }
    if ((localObject instanceof Symbol)) {
      return (Symbol)localObject;
    }
    return Namespace.EmptyNamespace.getSymbol(localObject.toString().intern());
  }
  
  public abstract short getNodeType();
  
  public String getNodeValue()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    getNodeValue(localStringBuffer);
    return localStringBuffer.toString();
  }
  
  public void getNodeValue(StringBuffer paramStringBuffer)
  {
    getNodeValue((NodeTree)this.sequence, this.ipos, paramStringBuffer);
  }
  
  public Document getOwnerDocument()
  {
    if (this.sequence.getNextKind(this.ipos) == 34) {
      return new KDocument((NodeTree)this.sequence, 0);
    }
    return null;
  }
  
  public Node getParentNode()
  {
    int i = this.sequence.parentPos(this.ipos);
    if (i == -1) {
      return null;
    }
    return make((NodeTree)this.sequence, i);
  }
  
  public String getPrefix()
  {
    return ((NodeTree)this.sequence).posPrefix(this.ipos);
  }
  
  public Node getPreviousSibling()
  {
    int j = this.sequence.parentPos(this.ipos);
    int i = j;
    if (j == -1) {
      i = 0;
    }
    int m = ((NodeTree)this.sequence).posToDataIndex(this.ipos);
    i = this.sequence.firstChildPos(i);
    j = i;
    int k = this.sequence.nextPos(j);
    if (k == 0) {}
    for (;;)
    {
      if (j != 0) {
        break label88;
      }
      return null;
      i = k;
      if (((NodeTree)this.sequence).posToDataIndex(k) != m) {
        break;
      }
    }
    label88:
    return make((NodeTree)this.sequence, j);
  }
  
  public String getTextContent()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    getTextContent(localStringBuffer);
    return localStringBuffer.toString();
  }
  
  protected void getTextContent(StringBuffer paramStringBuffer)
  {
    getNodeValue(paramStringBuffer);
  }
  
  public Object getUserData(String paramString)
  {
    return null;
  }
  
  public boolean hasAttributes()
  {
    return false;
  }
  
  public boolean hasChildNodes()
  {
    return ((NodeTree)this.sequence).posFirstChild(this.ipos) >= 0;
  }
  
  public Node insertBefore(Node paramNode1, Node paramNode2)
    throws DOMException
  {
    throw new DOMException((short)7, "insertBefore not supported");
  }
  
  public boolean isDefaultNamespace(String paramString)
  {
    return ((NodeTree)this.sequence).posIsDefaultNamespace(this.ipos, paramString);
  }
  
  public boolean isEqualNode(Node paramNode)
  {
    throw new UnsupportedOperationException("getAttributesisEqualNode not implemented yet");
  }
  
  public boolean isSameNode(Node paramNode)
  {
    if (!(paramNode instanceof KNode)) {}
    do
    {
      return false;
      paramNode = (KNode)paramNode;
    } while (this.sequence != paramNode.sequence);
    return this.sequence.equals(this.ipos, paramNode.ipos);
  }
  
  public boolean isSupported(String paramString1, String paramString2)
  {
    return false;
  }
  
  public String lookupNamespaceURI(String paramString)
  {
    return ((NodeTree)this.sequence).posLookupNamespaceURI(this.ipos, paramString);
  }
  
  public String lookupPrefix(String paramString)
  {
    return ((NodeTree)this.sequence).posLookupPrefix(this.ipos, paramString);
  }
  
  public void normalize() {}
  
  public Node removeChild(Node paramNode)
    throws DOMException
  {
    throw new DOMException((short)7, "removeChild not supported");
  }
  
  public Node replaceChild(Node paramNode1, Node paramNode2)
    throws DOMException
  {
    throw new DOMException((short)7, "replaceChild not supported");
  }
  
  public void setNodeValue(String paramString)
    throws DOMException
  {
    throw new DOMException((short)7, "setNodeValue not supported");
  }
  
  public void setPrefix(String paramString)
    throws DOMException
  {
    throw new DOMException((short)7, "setPrefix not supported");
  }
  
  public void setTextContent(String paramString)
    throws DOMException
  {
    throw new DOMException((short)7, "setTextContent not supported");
  }
  
  public Object setUserData(String paramString, Object paramObject, UserDataHandler paramUserDataHandler)
  {
    throw new UnsupportedOperationException("setUserData not implemented yet");
  }
  
  public String toString()
  {
    CharArrayOutPort localCharArrayOutPort = new CharArrayOutPort();
    XMLPrinter localXMLPrinter = new XMLPrinter(localCharArrayOutPort);
    ((NodeTree)this.sequence).consumeNext(this.ipos, localXMLPrinter);
    localXMLPrinter.close();
    localCharArrayOutPort.close();
    return localCharArrayOutPort.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\KNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */