package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.Comment;

public class KComment
  extends KCharacterData
  implements Comment
{
  public KComment(NodeTree paramNodeTree, int paramInt)
  {
    super(paramNodeTree, paramInt);
  }
  
  public static KComment valueOf(String paramString)
  {
    NodeTree localNodeTree = new NodeTree();
    localNodeTree.writeComment(paramString, 0, paramString.length());
    return new KComment(localNodeTree, 0);
  }
  
  public String getNodeName()
  {
    return "#comment";
  }
  
  public short getNodeType()
  {
    return 8;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\KComment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */