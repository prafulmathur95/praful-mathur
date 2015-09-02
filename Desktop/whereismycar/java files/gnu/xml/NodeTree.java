package gnu.xml;

import gnu.kawa.xml.ElementType;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.lists.AbstractSequence;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.text.URIPath;

public class NodeTree
  extends TreeList
{
  static int counter;
  int id;
  int idCount;
  String[] idNames;
  int[] idOffsets;
  
  public static NodeTree make()
  {
    return new NodeTree();
  }
  
  public int ancestorAttribute(int paramInt, String paramString1, String paramString2)
  {
    for (;;)
    {
      int i;
      if (paramInt == -1) {
        i = 0;
      }
      int j;
      do
      {
        return i;
        j = getAttributeI(paramInt, paramString1, paramString2);
        i = j;
      } while (j != 0);
      paramInt = parentPos(paramInt);
    }
  }
  
  public Path baseUriOfPos(int paramInt, boolean paramBoolean)
  {
    Object localObject2 = null;
    int j = posToDataIndex(paramInt);
    int i = paramInt;
    paramInt = j;
    for (;;)
    {
      if (paramInt == this.data.length) {
        return null;
      }
      j = this.data[paramInt];
      Object localObject3 = null;
      Object localObject1;
      if (j == 61714)
      {
        i = getIntN(paramInt + 1);
        localObject1 = localObject3;
        if (i >= 0) {
          localObject1 = URIPath.makeURI(this.objects[i]);
        }
        localObject3 = localObject2;
        if (localObject1 == null) {
          break label173;
        }
        if ((localObject2 != null) && (paramBoolean)) {
          break label163;
        }
      }
      for (;;)
      {
        if (!((Path)localObject1).isAbsolute())
        {
          localObject3 = localObject1;
          if (paramBoolean) {
            break label173;
          }
        }
        return (Path)localObject1;
        if ((j < 40960) || (j > 45055))
        {
          localObject1 = localObject3;
          if (j != 61704) {
            break;
          }
        }
        i = getAttributeI(i, "http://www.w3.org/XML/1998/namespace", "base");
        localObject1 = localObject3;
        if (i == 0) {
          break;
        }
        localObject1 = URIPath.valueOf(KNode.getNodeValue(this, i));
        break;
        label163:
        localObject1 = ((Path)localObject1).resolve((Path)localObject2);
      }
      label173:
      paramInt = parentOrEntityI(paramInt);
      if (paramInt == -1) {
        return (Path)localObject3;
      }
      i = paramInt << 1;
      localObject2 = localObject3;
    }
  }
  
  void enterID(String paramString, int paramInt)
  {
    String[] arrayOfString = this.idNames;
    int[] arrayOfInt2 = this.idOffsets;
    int[] arrayOfInt1;
    Object localObject;
    int j;
    int k;
    if (arrayOfString == null)
    {
      i = 64;
      this.idNames = new String[64];
      this.idOffsets = new int[64];
      arrayOfInt1 = arrayOfInt2;
      localObject = arrayOfString;
      j = paramString.hashCode();
      k = i - 1;
    }
    for (int i = j & k;; i = i + ((j ^ 0xFFFFFFFF) << 1 | 0x1) & k)
    {
      arrayOfString = localObject[i];
      if (arrayOfString == null)
      {
        localObject[i] = paramString;
        arrayOfInt1[i] = paramInt;
        this.idCount += 1;
      }
      while (arrayOfString.equals(paramString))
      {
        return;
        k = this.idCount;
        j = this.idNames.length;
        i = j;
        localObject = arrayOfString;
        arrayOfInt1 = arrayOfInt2;
        if (k * 4 < j * 3) {
          break;
        }
        this.idNames = new String[j * 2];
        this.idOffsets = new int[j * 2];
        this.idCount = 0;
        i = j;
        for (;;)
        {
          k = i - 1;
          if (k < 0) {
            break;
          }
          localObject = arrayOfString[k];
          i = k;
          if (localObject != null)
          {
            enterID((String)localObject, arrayOfInt2[k]);
            i = k;
          }
        }
        localObject = this.idNames;
        arrayOfInt1 = this.idOffsets;
        i = j * 2;
        break;
      }
    }
  }
  
  public int getAttribute(int paramInt, String paramString1, String paramString2)
  {
    Object localObject = null;
    if (paramString1 == null)
    {
      paramString1 = null;
      if (paramString2 != null) {
        break label32;
      }
    }
    label32:
    for (paramString2 = (String)localObject;; paramString2 = paramString2.intern())
    {
      return getAttributeI(paramInt, paramString1, paramString2);
      paramString1 = paramString1.intern();
      break;
    }
  }
  
  public int getAttributeI(int paramInt, String paramString1, String paramString2)
  {
    for (paramInt = firstAttributePos(paramInt);; paramInt = nextPos(paramInt))
    {
      int i;
      if ((paramInt == 0) || (getNextKind(paramInt) != 35)) {
        i = 0;
      }
      do
      {
        do
        {
          return i;
          if ((paramString2 != null) && (posLocalName(paramInt) != paramString2)) {
            break;
          }
          i = paramInt;
        } while (paramString1 == null);
        i = paramInt;
      } while (posNamespaceURI(paramInt) == paramString1);
    }
  }
  
  public int getId()
  {
    if (this.id == 0)
    {
      int i = counter + 1;
      counter = i;
      this.id = i;
    }
    return this.id;
  }
  
  public SeqPosition getIteratorAtPos(int paramInt)
  {
    return KNode.make(this, paramInt);
  }
  
  public int lookupID(String paramString)
  {
    String[] arrayOfString = this.idNames;
    int[] arrayOfInt = this.idOffsets;
    int i = this.idNames.length;
    int j = paramString.hashCode();
    int k = i - 1;
    for (i = j & k;; i = i + ((j ^ 0xFFFFFFFF) << 1 | 0x1) & k)
    {
      String str = arrayOfString[i];
      if (str == null) {
        return -1;
      }
      if (str.equals(paramString)) {
        return arrayOfInt[i];
      }
    }
  }
  
  public void makeIDtableIfNeeded()
  {
    if (this.idNames != null) {
      return;
    }
    this.idNames = new String[64];
    this.idOffsets = new int[64];
    int k = endPos();
    int i = 0;
    for (;;)
    {
      int j = nextMatching(i, ElementType.anyElement, k, true);
      if (j == 0) {
        break;
      }
      int m = getAttributeI(j, "http://www.w3.org/XML/1998/namespace", "id");
      i = j;
      if (m != 0)
      {
        enterID(KNode.getNodeValue(this, m), j);
        i = j;
      }
    }
  }
  
  public int nextPos(int paramInt)
  {
    int i = 0;
    int j;
    if ((paramInt & 0x1) != 0)
    {
      j = posToDataIndex(paramInt);
      paramInt = nextNodeIndex(j, Integer.MAX_VALUE);
      if (paramInt == j) {
        break label36;
      }
      paramInt <<= 1;
    }
    label36:
    do
    {
      return paramInt;
      break;
      paramInt = i;
    } while (j == this.data.length);
    return (j << 1) + 3;
  }
  
  public int posFirstChild(int paramInt)
  {
    paramInt = gotoChildrenStart(posToDataIndex(paramInt));
    if (paramInt < 0) {}
    int i;
    do
    {
      return -1;
      i = this.data[paramInt];
    } while ((i == 61707) || (i == 61708) || (i == 61713));
    return paramInt << 1;
  }
  
  public boolean posHasAttributes(int paramInt)
  {
    paramInt = gotoAttributesStart(posToDataIndex(paramInt));
    if (paramInt < 0) {}
    while ((paramInt < 0) || (this.data[paramInt] != 61705)) {
      return false;
    }
    return true;
  }
  
  public boolean posIsDefaultNamespace(int paramInt, String paramString)
  {
    throw new Error("posIsDefaultNamespace not implemented");
  }
  
  public String posLocalName(int paramInt)
  {
    Object localObject = getNextTypeObject(paramInt);
    if ((localObject instanceof XName)) {
      return ((XName)localObject).getLocalPart();
    }
    if ((localObject instanceof Symbol)) {
      return ((Symbol)localObject).getLocalName();
    }
    return getNextTypeName(paramInt);
  }
  
  public String posLookupNamespaceURI(int paramInt, String paramString)
  {
    if (getNextKind(paramInt) != 33) {
      throw new IllegalArgumentException("argument must be an element");
    }
    Object localObject = getNextTypeObject(paramInt);
    if ((localObject instanceof XName)) {
      return ((XName)localObject).lookupNamespaceURI(paramString);
    }
    return null;
  }
  
  public String posLookupPrefix(int paramInt, String paramString)
  {
    throw new Error("posLookupPrefix not implemented");
  }
  
  public String posNamespaceURI(int paramInt)
  {
    Object localObject = getNextTypeObject(paramInt);
    if ((localObject instanceof XName)) {
      return ((XName)localObject).getNamespaceURI();
    }
    if ((localObject instanceof Symbol)) {
      return ((Symbol)localObject).getNamespaceURI();
    }
    return null;
  }
  
  public String posPrefix(int paramInt)
  {
    String str = getNextTypeName(paramInt);
    if (str == null) {}
    do
    {
      return null;
      paramInt = str.indexOf(':');
    } while (paramInt < 0);
    return str.substring(0, paramInt);
  }
  
  public String posTarget(int paramInt)
  {
    paramInt = posToDataIndex(paramInt);
    if (this.data[paramInt] != 61716) {
      throw new ClassCastException("expected process-instruction");
    }
    return (String)this.objects[getIntN(paramInt + 1)];
  }
  
  public int stableCompare(AbstractSequence paramAbstractSequence)
  {
    if (this == paramAbstractSequence) {
      return 0;
    }
    int j = super.stableCompare(paramAbstractSequence);
    int i = j;
    if (j == 0)
    {
      i = j;
      if ((paramAbstractSequence instanceof NodeTree))
      {
        i = getId();
        j = ((NodeTree)paramAbstractSequence).getId();
        if (i >= j) {
          break label50;
        }
        i = -1;
      }
    }
    for (;;)
    {
      return i;
      label50:
      if (i > j) {
        i = 1;
      } else {
        i = 0;
      }
    }
  }
  
  public String toString()
  {
    CharArrayOutPort localCharArrayOutPort = new CharArrayOutPort();
    consume(new XMLPrinter(localCharArrayOutPort));
    localCharArrayOutPort.close();
    return localCharArrayOutPort.toString();
  }
  
  public Object typedValue(int paramInt)
  {
    Object localObject = new StringBuffer();
    stringValue(posToDataIndex(paramInt), (StringBuffer)localObject);
    localObject = ((StringBuffer)localObject).toString();
    paramInt = getNextKind(paramInt);
    if ((paramInt == 37) || (paramInt == 36)) {
      return localObject;
    }
    return new UntypedAtomic((String)localObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\xml\NodeTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */