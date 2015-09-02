package gnu.kawa.xml;

import gnu.bytecode.Type;
import gnu.kawa.reflect.OccurrenceType;

public class NodeSetType
  extends OccurrenceType
{
  public NodeSetType(Type paramType)
  {
    super(paramType, 0, -1);
  }
  
  public static Type getInstance(Type paramType)
  {
    return new NodeSetType(paramType);
  }
  
  public String toString()
  {
    return super.toString() + "node-set";
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\NodeSetType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */