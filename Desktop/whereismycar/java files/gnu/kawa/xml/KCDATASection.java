package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.CDATASection;

public class KCDATASection
  extends KText
  implements CDATASection
{
  public KCDATASection(NodeTree paramNodeTree, int paramInt)
  {
    super(paramNodeTree, paramInt);
  }
  
  public String getData()
  {
    return getNodeValue();
  }
  
  public int getLength()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    NodeTree localNodeTree = (NodeTree)this.sequence;
    localNodeTree.stringValue(localNodeTree.posToDataIndex(this.ipos), localStringBuffer);
    return localStringBuffer.length();
  }
  
  public String getNodeName()
  {
    return "#cdata-section";
  }
  
  public short getNodeType()
  {
    return 4;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\KCDATASection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */