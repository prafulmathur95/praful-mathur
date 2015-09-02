package gnu.kawa.xml;

import gnu.mapping.Namespace;
import gnu.xml.NamespaceBinding;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Hashtable;

public class XmlNamespace
  extends Namespace
  implements Externalizable
{
  public static final XmlNamespace HTML = valueOf("http://www.w3.org/1999/xhtml", "");
  public static final NamespaceBinding HTML_BINDINGS = new NamespaceBinding(null, "http://www.w3.org/1999/xhtml", NamespaceBinding.predefinedXML);
  public static final String XHTML_NAMESPACE = "http://www.w3.org/1999/xhtml";
  
  public static XmlNamespace getInstance(String paramString1, String paramString2)
  {
    String str = paramString1 + " [xml] -> " + paramString2;
    synchronized (nsTable)
    {
      Object localObject = nsTable.get(str);
      if ((localObject instanceof XmlNamespace))
      {
        paramString1 = (XmlNamespace)localObject;
        return paramString1;
      }
      localObject = new XmlNamespace();
      ((XmlNamespace)localObject).setName(paramString2.intern());
      ((XmlNamespace)localObject).prefix = paramString1.intern();
      nsTable.put(str, localObject);
      return (XmlNamespace)localObject;
    }
  }
  
  public static XmlNamespace valueOf(String paramString1, String paramString2)
  {
    return getInstance(paramString2, paramString1);
  }
  
  public Object get(String paramString)
  {
    paramString = ElementType.make(getSymbol(paramString));
    if (this == HTML) {
      paramString.setNamespaceNodes(HTML_BINDINGS);
    }
    return paramString;
  }
  
  public boolean isConstant(String paramString)
  {
    return true;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    setName((String)paramObjectInput.readObject());
    this.prefix = ((String)paramObjectInput.readObject());
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    String str = this.prefix + " -> " + getName();
    Namespace localNamespace = (Namespace)nsTable.get(str);
    if ((localNamespace instanceof XmlNamespace)) {
      return localNamespace;
    }
    nsTable.put(str, this);
    return this;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(getName());
    paramObjectOutput.writeObject(this.prefix);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\XmlNamespace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */