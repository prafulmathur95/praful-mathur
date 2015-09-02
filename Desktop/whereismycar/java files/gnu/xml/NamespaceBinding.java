package gnu.xml;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public final class NamespaceBinding
  implements Externalizable
{
  public static final String XML_NAMESPACE = "http://www.w3.org/XML/1998/namespace";
  public static final NamespaceBinding predefinedXML = new NamespaceBinding("xml", "http://www.w3.org/XML/1998/namespace", null);
  int depth;
  NamespaceBinding next;
  String prefix;
  String uri;
  
  public NamespaceBinding(String paramString1, String paramString2, NamespaceBinding paramNamespaceBinding)
  {
    this.prefix = paramString1;
    this.uri = paramString2;
    setNext(paramNamespaceBinding);
  }
  
  public static NamespaceBinding commonAncestor(NamespaceBinding paramNamespaceBinding1, NamespaceBinding paramNamespaceBinding2)
  {
    NamespaceBinding localNamespaceBinding2 = paramNamespaceBinding1;
    NamespaceBinding localNamespaceBinding1 = paramNamespaceBinding2;
    if (paramNamespaceBinding1.depth > paramNamespaceBinding2.depth)
    {
      localNamespaceBinding1 = paramNamespaceBinding1;
      localNamespaceBinding2 = paramNamespaceBinding2;
    }
    for (;;)
    {
      paramNamespaceBinding1 = localNamespaceBinding2;
      paramNamespaceBinding2 = localNamespaceBinding1;
      if (localNamespaceBinding1.depth <= localNamespaceBinding2.depth) {
        break;
      }
      localNamespaceBinding1 = localNamespaceBinding1.next;
    }
    while (paramNamespaceBinding1 != paramNamespaceBinding2)
    {
      paramNamespaceBinding1 = paramNamespaceBinding1.next;
      paramNamespaceBinding2 = paramNamespaceBinding2.next;
    }
    return paramNamespaceBinding1;
  }
  
  public static NamespaceBinding maybeAdd(String paramString1, String paramString2, NamespaceBinding paramNamespaceBinding)
  {
    NamespaceBinding localNamespaceBinding = paramNamespaceBinding;
    if (paramNamespaceBinding == null)
    {
      if (paramString2 == null) {
        return paramNamespaceBinding;
      }
      localNamespaceBinding = predefinedXML;
    }
    paramNamespaceBinding = localNamespaceBinding.resolve(paramString1);
    if (paramNamespaceBinding == null)
    {
      if (paramString2 != null) {}
    }
    else {
      while (paramNamespaceBinding.equals(paramString2)) {
        return localNamespaceBinding;
      }
    }
    return new NamespaceBinding(paramString1, paramString2, localNamespaceBinding);
  }
  
  public static final NamespaceBinding nconc(NamespaceBinding paramNamespaceBinding1, NamespaceBinding paramNamespaceBinding2)
  {
    if (paramNamespaceBinding1 == null) {
      return paramNamespaceBinding2;
    }
    paramNamespaceBinding1.setNext(nconc(paramNamespaceBinding1.next, paramNamespaceBinding2));
    return paramNamespaceBinding1;
  }
  
  public int count(NamespaceBinding paramNamespaceBinding)
  {
    int i = 0;
    for (NamespaceBinding localNamespaceBinding = this; localNamespaceBinding != paramNamespaceBinding; localNamespaceBinding = localNamespaceBinding.next) {
      i += 1;
    }
    return i;
  }
  
  public final NamespaceBinding getNext()
  {
    return this.next;
  }
  
  public final String getPrefix()
  {
    return this.prefix;
  }
  
  public final String getUri()
  {
    return this.uri;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.prefix = paramObjectInput.readUTF();
    this.uri = paramObjectInput.readUTF();
    this.next = ((NamespaceBinding)paramObjectInput.readObject());
  }
  
  public String resolve(String paramString)
  {
    for (NamespaceBinding localNamespaceBinding = this; localNamespaceBinding != null; localNamespaceBinding = localNamespaceBinding.next) {
      if (localNamespaceBinding.prefix == paramString) {
        return localNamespaceBinding.uri;
      }
    }
    return null;
  }
  
  public String resolve(String paramString, NamespaceBinding paramNamespaceBinding)
  {
    for (NamespaceBinding localNamespaceBinding = this; localNamespaceBinding != paramNamespaceBinding; localNamespaceBinding = localNamespaceBinding.next) {
      if (localNamespaceBinding.prefix == paramString) {
        return localNamespaceBinding.uri;
      }
    }
    return null;
  }
  
  public NamespaceBinding reversePrefix(NamespaceBinding paramNamespaceBinding)
  {
    Object localObject2 = paramNamespaceBinding;
    Object localObject1 = this;
    int i;
    if (paramNamespaceBinding == null) {
      i = -1;
    }
    while (localObject1 != paramNamespaceBinding)
    {
      NamespaceBinding localNamespaceBinding = ((NamespaceBinding)localObject1).next;
      ((NamespaceBinding)localObject1).next = ((NamespaceBinding)localObject2);
      localObject2 = localObject1;
      i += 1;
      ((NamespaceBinding)localObject1).depth = i;
      localObject1 = localNamespaceBinding;
      continue;
      i = paramNamespaceBinding.depth;
    }
    return (NamespaceBinding)localObject2;
  }
  
  public final void setNext(NamespaceBinding paramNamespaceBinding)
  {
    this.next = paramNamespaceBinding;
    if (paramNamespaceBinding == null) {}
    for (int i = 0;; i = paramNamespaceBinding.depth + 1)
    {
      this.depth = i;
      return;
    }
  }
  
  public final void setPrefix(String paramString)
  {
    this.prefix = paramString;
  }
  
  public final void setUri(String paramString)
  {
    this.uri = paramString;
  }
  
  public String toString()
  {
    return "Namespace{" + this.prefix + "=" + this.uri + ", depth:" + this.depth + "}";
  }
  
  public String toStringAll()
  {
    StringBuffer localStringBuffer = new StringBuffer("Namespaces{");
    NamespaceBinding localNamespaceBinding = this;
    if (localNamespaceBinding != null)
    {
      localStringBuffer.append(localNamespaceBinding.prefix);
      localStringBuffer.append("=\"");
      localStringBuffer.append(localNamespaceBinding.uri);
      if (localNamespaceBinding == null) {}
      for (String str = "\"";; str = "\", ")
      {
        localStringBuffer.append(str);
        localNamespaceBinding = localNamespaceBinding.next;
        break;
      }
    }
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeUTF(this.prefix);
    paramObjectOutput.writeUTF(this.uri);
    paramObjectOutput.writeObject(this.next);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\xml\NamespaceBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */