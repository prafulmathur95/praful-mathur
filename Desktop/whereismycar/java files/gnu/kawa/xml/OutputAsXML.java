package gnu.kawa.xml;

import gnu.lists.FString;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Procedure1;
import gnu.xml.XMLPrinter;

public class OutputAsXML
  extends Procedure1
{
  public Object apply1(Object paramObject)
  {
    CharArrayOutPort localCharArrayOutPort = new CharArrayOutPort();
    XMLPrinter localXMLPrinter = new XMLPrinter(localCharArrayOutPort);
    localXMLPrinter.writeObject(paramObject);
    localXMLPrinter.flush();
    return new FString(localCharArrayOutPort.toCharArray());
  }
  
  public int numArgs()
  {
    return 4097;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\OutputAsXML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */