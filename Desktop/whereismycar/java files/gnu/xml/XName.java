package gnu.xml;

import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class XName
  extends Symbol
  implements Externalizable
{
  NamespaceBinding namespaceNodes;
  
  public XName() {}
  
  public XName(Symbol paramSymbol, NamespaceBinding paramNamespaceBinding)
  {
    super(paramSymbol.getNamespace(), paramSymbol.getName());
    this.namespaceNodes = paramNamespaceBinding;
  }
  
  public static int checkName(String paramString)
  {
    int i2 = paramString.length();
    int k;
    if (i2 == 0) {
      k = -1;
    }
    int i;
    int j;
    do
    {
      return k;
      i = 2;
      j = 0;
      k = i;
    } while (j >= i2);
    int m;
    label34:
    int n;
    if (j == 0)
    {
      m = 1;
      k = j + 1;
      int i1 = paramString.charAt(j);
      n = i1;
      j = k;
      if (i1 >= 55296)
      {
        n = i1;
        j = k;
        if (i1 < 56320)
        {
          n = i1;
          j = k;
          if (k < i2)
          {
            n = (i1 - 55296) * 1024 + (paramString.charAt(k) - 56320) + 65536;
            j = k + 1;
          }
        }
      }
      if (n != 58) {
        break label137;
      }
      k = i;
      if (i == 2) {
        k = 1;
      }
    }
    for (;;)
    {
      i = k;
      break;
      m = 0;
      break label34;
      label137:
      if (!isNamePart(n)) {
        return -1;
      }
      k = i;
      if (m != 0)
      {
        k = i;
        if (!isNameStart(n)) {
          k = 0;
        }
      }
    }
  }
  
  public static boolean isNCName(String paramString)
  {
    return checkName(paramString) > 1;
  }
  
  public static boolean isName(String paramString)
  {
    return checkName(paramString) > 0;
  }
  
  public static boolean isNamePart(int paramInt)
  {
    return (Character.isUnicodeIdentifierPart(paramInt)) || (paramInt == 45) || (paramInt == 46);
  }
  
  public static boolean isNameStart(int paramInt)
  {
    return (Character.isUnicodeIdentifierStart(paramInt)) || (paramInt == 95);
  }
  
  public static boolean isNmToken(String paramString)
  {
    return checkName(paramString) >= 0;
  }
  
  public final NamespaceBinding getNamespaceNodes()
  {
    return this.namespaceNodes;
  }
  
  String lookupNamespaceURI(String paramString)
  {
    for (NamespaceBinding localNamespaceBinding = this.namespaceNodes; localNamespaceBinding != null; localNamespaceBinding = localNamespaceBinding.next) {
      if (paramString == localNamespaceBinding.prefix) {
        return localNamespaceBinding.uri;
      }
    }
    return null;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    super.readExternal(paramObjectInput);
    this.namespaceNodes = ((NamespaceBinding)paramObjectInput.readObject());
  }
  
  public final void setNamespaceNodes(NamespaceBinding paramNamespaceBinding)
  {
    this.namespaceNodes = paramNamespaceBinding;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    super.writeExternal(paramObjectOutput);
    paramObjectOutput.writeObject(this.namespaceNodes);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\xml\XName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */