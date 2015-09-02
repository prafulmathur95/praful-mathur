package gnu.xml;

import gnu.expr.Keyword;
import gnu.kawa.sax.ContentConsumer;
import gnu.lists.AbstractSequence;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.lists.UnescapedData;
import gnu.lists.XConsumer;
import gnu.mapping.Symbol;
import gnu.text.Char;
import gnu.text.LineBufferedReader;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import java.util.Iterator;
import java.util.List;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class XMLFilter
  implements DocumentHandler, ContentHandler, SourceLocator, XConsumer, PositionConsumer
{
  public static final int COPY_NAMESPACES_INHERIT = 2;
  public static final int COPY_NAMESPACES_PRESERVE = 1;
  private static final int SAW_KEYWORD = 1;
  private static final int SAW_WORD = 2;
  int attrCount = -1;
  String attrLocalName;
  String attrPrefix;
  Consumer base;
  public transient int copyNamespacesMode = 1;
  String currentNamespacePrefix;
  protected int ignoringLevel;
  LineBufferedReader in;
  boolean inStartTag;
  SourceLocator locator;
  MappingInfo[] mappingTable = new MappingInfo[''];
  int mappingTableMask = this.mappingTable.length - 1;
  private SourceMessages messages;
  boolean mismatchReported;
  NamespaceBinding namespaceBindings;
  public boolean namespacePrefixes = false;
  protected int nesting;
  public Consumer out;
  int previous = 0;
  int[] startIndexes = null;
  protected int stringizingElementNesting = -1;
  protected int stringizingLevel;
  TreeList tlist;
  Object[] workStack;
  
  public XMLFilter(Consumer paramConsumer)
  {
    this.base = paramConsumer;
    this.out = paramConsumer;
    if ((paramConsumer instanceof NodeTree)) {}
    for (this.tlist = ((NodeTree)paramConsumer);; this.tlist = new TreeList())
    {
      this.namespaceBindings = NamespaceBinding.predefinedXML;
      return;
    }
  }
  
  public static String duplicateAttributeMessage(Symbol paramSymbol, Object paramObject)
  {
    StringBuffer localStringBuffer = new StringBuffer("duplicate attribute: ");
    String str = paramSymbol.getNamespaceURI();
    if ((str != null) && (str.length() > 0))
    {
      localStringBuffer.append('{');
      localStringBuffer.append('}');
      localStringBuffer.append(str);
    }
    localStringBuffer.append(paramSymbol.getLocalPart());
    if (paramObject != null)
    {
      localStringBuffer.append(" in <");
      localStringBuffer.append(paramObject);
      localStringBuffer.append('>');
    }
    return localStringBuffer.toString();
  }
  
  private void ensureSpaceInStartIndexes(int paramInt)
  {
    if (this.startIndexes == null) {
      this.startIndexes = new int[20];
    }
    while (paramInt < this.startIndexes.length) {
      return;
    }
    int[] arrayOfInt = new int[this.startIndexes.length * 2];
    System.arraycopy(this.startIndexes, 0, arrayOfInt, 0, paramInt);
    this.startIndexes = arrayOfInt;
  }
  
  private void ensureSpaceInWorkStack(int paramInt)
  {
    if (this.workStack == null) {
      this.workStack = new Object[20];
    }
    while (paramInt < this.workStack.length) {
      return;
    }
    Object[] arrayOfObject = new Object[this.workStack.length * 2];
    System.arraycopy(this.workStack, 0, arrayOfObject, 0, paramInt);
    this.workStack = arrayOfObject;
  }
  
  private NamespaceBinding mergeHelper(NamespaceBinding paramNamespaceBinding1, NamespaceBinding paramNamespaceBinding2)
  {
    if (paramNamespaceBinding2 == NamespaceBinding.predefinedXML) {
      return paramNamespaceBinding1;
    }
    Object localObject = mergeHelper(paramNamespaceBinding1, paramNamespaceBinding2.next);
    String str = paramNamespaceBinding2.uri;
    paramNamespaceBinding1 = (NamespaceBinding)localObject;
    if (localObject == null)
    {
      if (str == null) {
        return (NamespaceBinding)localObject;
      }
      paramNamespaceBinding1 = NamespaceBinding.predefinedXML;
    }
    paramNamespaceBinding2 = paramNamespaceBinding2.prefix;
    localObject = paramNamespaceBinding1.resolve(paramNamespaceBinding2);
    if (localObject == null)
    {
      if (str != null) {}
    }
    else {
      while (((String)localObject).equals(str)) {
        return paramNamespaceBinding1;
      }
    }
    return findNamespaceBinding(paramNamespaceBinding2, str, paramNamespaceBinding1);
  }
  
  private String resolve(String paramString, boolean paramBoolean)
  {
    Object localObject;
    if ((paramBoolean) && (paramString == null)) {
      localObject = "";
    }
    String str;
    do
    {
      return (String)localObject;
      str = this.namespaceBindings.resolve(paramString);
      localObject = str;
    } while (str != null);
    if (paramString != null) {
      error('e', "unknown namespace prefix '" + paramString + '\'');
    }
    return "";
  }
  
  private boolean startAttributeCommon()
  {
    if (this.stringizingElementNesting >= 0) {
      this.ignoringLevel += 1;
    }
    int i = this.stringizingLevel;
    this.stringizingLevel = (i + 1);
    if (i > 0) {
      return false;
    }
    if (this.attrCount < 0) {
      this.attrCount = 0;
    }
    ensureSpaceInWorkStack(this.nesting + this.attrCount);
    ensureSpaceInStartIndexes(this.attrCount);
    this.startIndexes[this.attrCount] = this.tlist.gapStart;
    this.attrCount += 1;
    return true;
  }
  
  public XMLFilter append(char paramChar)
  {
    write(paramChar);
    return this;
  }
  
  public XMLFilter append(CharSequence paramCharSequence)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null) {
      localObject = "null";
    }
    append((CharSequence)localObject, 0, ((CharSequence)localObject).length());
    return this;
  }
  
  public XMLFilter append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null) {
      localObject = "null";
    }
    write((CharSequence)localObject, paramInt1, paramInt2 - paramInt1);
    return this;
  }
  
  public void beginEntity(Object paramObject)
  {
    if ((this.base instanceof XConsumer)) {
      ((XConsumer)this.base).beginEntity(paramObject);
    }
  }
  
  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws SAXException
  {
    write(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  protected void checkValidComment(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i = 1;
    for (;;)
    {
      int j = paramInt2 - 1;
      if (j >= 0) {
        if (paramArrayOfChar[(paramInt1 + j)] != '-') {
          break label44;
        }
      }
      label44:
      for (paramInt2 = 1; (i != 0) && (paramInt2 != 0); paramInt2 = 0)
      {
        error('e', "consecutive or final hyphen in XML comment");
        return;
      }
      i = paramInt2;
      paramInt2 = j;
    }
  }
  
  protected boolean checkWriteAtomic()
  {
    this.previous = 0;
    if (this.ignoringLevel > 0) {
      return false;
    }
    closeStartTag();
    return true;
  }
  
  void closeStartTag()
  {
    if ((this.attrCount < 0) || (this.stringizingLevel > 0)) {
      return;
    }
    this.inStartTag = false;
    this.previous = 0;
    if (this.attrLocalName != null) {
      endAttribute();
    }
    if (this.nesting == 0) {}
    Object localObject1;
    Object localObject3;
    Object localObject2;
    Object localObject7;
    Object localObject5;
    for (Object localObject4 = NamespaceBinding.predefinedXML;; localObject4 = (NamespaceBinding)this.workStack[(this.nesting - 2)])
    {
      localObject1 = this.namespaceBindings;
      i = 0;
      for (;;)
      {
        if (i > this.attrCount) {
          break label411;
        }
        localObject3 = this.workStack[(this.nesting + i - 1)];
        localObject2 = localObject1;
        if ((localObject3 instanceof Symbol))
        {
          localObject7 = (Symbol)localObject3;
          localObject2 = ((Symbol)localObject7).getPrefix();
          localObject5 = localObject2;
          if (localObject2 == "") {
            localObject5 = null;
          }
          localObject2 = ((Symbol)localObject7).getNamespaceURI();
          localObject3 = localObject2;
          if (localObject2 == "") {
            localObject3 = null;
          }
          if ((i <= 0) || (localObject5 != null) || (localObject3 != null)) {
            break;
          }
          localObject2 = localObject1;
        }
        i += 1;
        localObject1 = localObject2;
      }
    }
    int j = 0;
    for (Object localObject6 = localObject1;; localObject6 = ((NamespaceBinding)localObject6).next)
    {
      if (localObject6 == localObject4) {
        j = 1;
      }
      if (localObject6 == null)
      {
        if (localObject5 == null)
        {
          localObject2 = localObject1;
          if (localObject3 == null) {
            break;
          }
        }
        localObject2 = findNamespaceBinding((String)localObject5, (String)localObject3, (NamespaceBinding)localObject1);
        break;
      }
      if (((NamespaceBinding)localObject6).prefix == localObject5)
      {
        localObject2 = localObject1;
        if (((NamespaceBinding)localObject6).uri == localObject3) {
          break;
        }
        if (j != 0)
        {
          localObject2 = findNamespaceBinding((String)localObject5, (String)localObject3, (NamespaceBinding)localObject1);
          break;
        }
        label268:
        label391:
        for (localObject5 = localObject1;; localObject5 = ((NamespaceBinding)localObject5).next)
        {
          if (localObject5 == null)
          {
            j = 1;
            localObject2 = ("_ns_" + j).intern();
            if (((NamespaceBinding)localObject1).resolve((String)localObject2) != null) {}
          }
          do
          {
            localObject5 = findNamespaceBinding((String)localObject2, (String)localObject3, (NamespaceBinding)localObject1);
            localObject6 = ((Symbol)localObject7).getLocalName();
            localObject1 = localObject3;
            if (localObject3 == null) {
              localObject1 = "";
            }
            this.workStack[(this.nesting + i - 1)] = Symbol.make((String)localObject1, (String)localObject6, (String)localObject2);
            localObject2 = localObject5;
            break;
            j += 1;
            break label268;
            if (((NamespaceBinding)localObject5).uri != localObject3) {
              break label391;
            }
            localObject6 = ((NamespaceBinding)localObject5).prefix;
            localObject2 = localObject6;
          } while (((NamespaceBinding)localObject1).resolve((String)localObject6) == localObject3);
        }
      }
    }
    label411:
    j = 0;
    if (j <= this.attrCount)
    {
      localObject7 = this.workStack[(this.nesting + j - 1)];
      int k = 0;
      i = 0;
      int m;
      if (((localObject7 instanceof MappingInfo)) || (this.out == this.tlist)) {
        if ((localObject7 instanceof MappingInfo))
        {
          localObject5 = (MappingInfo)localObject7;
          localObject4 = ((MappingInfo)localObject5).prefix;
          localObject3 = ((MappingInfo)localObject5).local;
          if ((j > 0) && (((localObject4 == null) && (localObject3 == "xmlns")) || (localObject4 == "xmlns")))
          {
            i = 1;
            localObject2 = "(namespace-node)";
            k = ((MappingInfo)localObject5).tagHash;
            m = k & this.mappingTableMask;
            localObject5 = this.mappingTable[m];
            if (localObject5 != null) {
              break label851;
            }
            localObject5 = new MappingInfo();
            ((MappingInfo)localObject5).tagHash = k;
            ((MappingInfo)localObject5).prefix = ((String)localObject4);
            ((MappingInfo)localObject5).local = ((String)localObject3);
            ((MappingInfo)localObject5).nextInBucket = this.mappingTable[m];
            this.mappingTable[m] = localObject5;
            ((MappingInfo)localObject5).uri = ((String)localObject2);
            ((MappingInfo)localObject5).qname = Symbol.make((String)localObject2, (String)localObject3, (String)localObject4);
            localObject4 = localObject5;
            if (j == 0)
            {
              ((MappingInfo)localObject5).type = new XName(((MappingInfo)localObject5).qname, (NamespaceBinding)localObject1);
              ((MappingInfo)localObject5).namespaces = ((NamespaceBinding)localObject1);
              localObject4 = localObject5;
            }
            label659:
            this.workStack[(this.nesting + j - 1)] = localObject4;
            label675:
            k = 1;
            label678:
            if (k >= j) {
              break label1078;
            }
            localObject5 = this.workStack[(this.nesting + k - 1)];
            if (!(localObject5 instanceof Symbol)) {
              break label1057;
            }
          }
        }
      }
      for (localObject5 = (Symbol)localObject5;; localObject5 = ((MappingInfo)localObject5).qname)
      {
        if ((localObject3 == ((Symbol)localObject5).getLocalPart()) && (localObject2 == ((Symbol)localObject5).getNamespaceURI()))
        {
          Object localObject8 = this.workStack[(this.nesting - 1)];
          localObject6 = localObject8;
          if ((localObject8 instanceof MappingInfo)) {
            localObject6 = ((MappingInfo)localObject8).qname;
          }
          error('e', duplicateAttributeMessage((Symbol)localObject5, localObject6));
        }
        label851:
        label994:
        label1017:
        label1057:
        do
        {
          k += 1;
          break label678;
          if (j > 0) {}
          for (boolean bool = true;; bool = false)
          {
            localObject2 = resolve((String)localObject4, bool);
            break;
          }
          localObject2 = (Symbol)localObject7;
          localObject5 = lookupTag((Symbol)localObject2);
          localObject4 = ((MappingInfo)localObject5).prefix;
          localObject3 = ((MappingInfo)localObject5).local;
          localObject2 = ((Symbol)localObject2).getNamespaceURI();
          break;
          if ((((MappingInfo)localObject5).tagHash == k) && (((MappingInfo)localObject5).local == localObject3) && (((MappingInfo)localObject5).prefix == localObject4)) {
            if (((MappingInfo)localObject5).uri == null)
            {
              ((MappingInfo)localObject5).uri = ((String)localObject2);
              ((MappingInfo)localObject5).qname = Symbol.make((String)localObject2, (String)localObject3, (String)localObject4);
            }
          }
          for (;;)
          {
            if (j != 0) {
              break label1017;
            }
            if ((((MappingInfo)localObject5).namespaces == localObject1) || (((MappingInfo)localObject5).namespaces == null))
            {
              localObject6 = ((MappingInfo)localObject5).type;
              ((MappingInfo)localObject5).namespaces = ((NamespaceBinding)localObject1);
              localObject4 = localObject5;
              if (localObject6 != null) {
                break label659;
              }
              ((MappingInfo)localObject5).type = new XName(((MappingInfo)localObject5).qname, (NamespaceBinding)localObject1);
              localObject4 = localObject5;
              break label659;
              if (((MappingInfo)localObject5).uri == localObject2) {
                break label994;
              }
            }
            localObject5 = ((MappingInfo)localObject5).nextInBucket;
            break;
            if (((MappingInfo)localObject5).qname == null) {
              ((MappingInfo)localObject5).qname = Symbol.make((String)localObject2, (String)localObject3, (String)localObject4);
            }
          }
          localObject4 = ((MappingInfo)localObject5).qname;
          localObject4 = localObject5;
          break label659;
          localObject3 = (Symbol)localObject7;
          localObject2 = ((Symbol)localObject3).getNamespaceURI();
          localObject3 = ((Symbol)localObject3).getLocalName();
          localObject4 = null;
          i = k;
          break label675;
        } while (!(localObject5 instanceof MappingInfo));
      }
      label1078:
      if (this.out == this.tlist) {
        if (j == 0)
        {
          localObject2 = ((MappingInfo)localObject4).type;
          label1100:
          m = ((MappingInfo)localObject4).index;
          if (m > 0)
          {
            k = m;
            if (this.tlist.objects[m] == localObject2) {}
          }
          else
          {
            k = this.tlist.find(localObject2);
            ((MappingInfo)localObject4).index = k;
          }
          if (j != 0) {
            break label1186;
          }
          this.tlist.setElementName(this.tlist.gapEnd, k);
        }
      }
      label1186:
      label1268:
      do
      {
        for (;;)
        {
          j += 1;
          break;
          localObject2 = ((MappingInfo)localObject4).qname;
          break label1100;
          if ((i == 0) || (this.namespacePrefixes)) {
            this.tlist.setAttributeName(this.startIndexes[(j - 1)], k);
          }
        }
        if (localObject4 == null) {
          localObject2 = localObject7;
        }
        for (;;)
        {
          if (j != 0) {
            break label1268;
          }
          this.out.startElement(localObject2);
          break;
          if (j == 0) {
            localObject2 = ((MappingInfo)localObject4).type;
          } else {
            localObject2 = ((MappingInfo)localObject4).qname;
          }
        }
      } while ((i != 0) && (!this.namespacePrefixes));
      this.out.startAttribute(localObject2);
      k = this.startIndexes[(j - 1)];
      if (j < this.attrCount) {}
      for (i = this.startIndexes[j];; i = this.tlist.gapStart)
      {
        this.tlist.consumeIRange(k + 5, i - 1, this.out);
        this.out.endAttribute();
        break;
      }
    }
    if ((this.out instanceof ContentConsumer)) {
      ((ContentConsumer)this.out).endStartTag();
    }
    int i = 1;
    while (i <= this.attrCount)
    {
      this.workStack[(this.nesting + i - 1)] = null;
      i += 1;
    }
    if (this.out != this.tlist)
    {
      this.base = this.out;
      this.tlist.clear();
    }
    this.attrCount = -1;
  }
  
  public void commentFromParser(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (this.stringizingLevel == 0)
    {
      closeStartTag();
      if ((this.base instanceof XConsumer)) {
        ((XConsumer)this.base).writeComment(paramArrayOfChar, paramInt1, paramInt2);
      }
    }
    while (this.stringizingElementNesting >= 0) {
      return;
    }
    this.base.write(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void consume(SeqPosition paramSeqPosition)
  {
    writePosition(paramSeqPosition.sequence, paramSeqPosition.ipos);
  }
  
  public void emitCharacterReference(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
  {
    if (paramInt1 >= 65536)
    {
      Char.print(paramInt1, this);
      return;
    }
    write(paramInt1);
  }
  
  public void emitDoctypeDecl(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public void emitEndAttributes()
  {
    if (this.attrLocalName != null) {
      endAttribute();
    }
    closeStartTag();
  }
  
  public void emitEndElement(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (this.attrLocalName != null)
    {
      error('e', "unclosed attribute");
      endAttribute();
    }
    if (!inElement()) {
      error('e', "unmatched end element");
    }
    do
    {
      return;
      if (paramArrayOfChar != null)
      {
        Object localObject2 = lookupTag(paramArrayOfChar, paramInt1, paramInt2);
        Object localObject1 = this.workStack[(this.nesting - 1)];
        if (((localObject1 instanceof MappingInfo)) && (!this.mismatchReported))
        {
          localObject1 = (MappingInfo)localObject1;
          if ((((MappingInfo)localObject2).local != ((MappingInfo)localObject1).local) || (((MappingInfo)localObject2).prefix != ((MappingInfo)localObject1).prefix))
          {
            localObject2 = new StringBuffer("</");
            ((StringBuffer)localObject2).append(paramArrayOfChar, paramInt1, paramInt2);
            ((StringBuffer)localObject2).append("> matching <");
            paramArrayOfChar = ((MappingInfo)localObject1).prefix;
            if (paramArrayOfChar != null)
            {
              ((StringBuffer)localObject2).append(paramArrayOfChar);
              ((StringBuffer)localObject2).append(':');
            }
            ((StringBuffer)localObject2).append(((MappingInfo)localObject1).local);
            ((StringBuffer)localObject2).append('>');
            error('e', ((StringBuffer)localObject2).toString());
            this.mismatchReported = true;
          }
        }
      }
      closeStartTag();
    } while (this.nesting <= 0);
    endElement();
  }
  
  public void emitEntityReference(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int k = paramArrayOfChar[paramInt1];
    int j = 63;
    int i;
    if ((paramInt2 == 2) && (paramArrayOfChar[(paramInt1 + 1)] == 't')) {
      if (k == 108) {
        i = 60;
      }
    }
    for (;;)
    {
      write(i);
      return;
      i = j;
      if (k == 103)
      {
        i = 62;
        continue;
        if (paramInt2 == 3)
        {
          i = j;
          if (k == 97)
          {
            i = j;
            if (paramArrayOfChar[(paramInt1 + 1)] == 'm')
            {
              i = j;
              if (paramArrayOfChar[(paramInt1 + 2)] == 'p') {
                i = 38;
              }
            }
          }
        }
        else
        {
          i = j;
          if (paramInt2 == 4)
          {
            paramInt2 = paramArrayOfChar[(paramInt1 + 1)];
            int m = paramArrayOfChar[(paramInt1 + 2)];
            paramInt1 = paramArrayOfChar[(paramInt1 + 3)];
            if ((k == 113) && (paramInt2 == 117) && (m == 111) && (paramInt1 == 116))
            {
              i = 34;
            }
            else
            {
              i = j;
              if (k == 97)
              {
                i = j;
                if (paramInt2 == 112)
                {
                  i = j;
                  if (m == 111)
                  {
                    i = j;
                    if (paramInt1 == 115) {
                      i = 39;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public void emitStartAttribute(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (this.attrLocalName != null) {
      endAttribute();
    }
    if (!startAttributeCommon()) {}
    for (;;)
    {
      return;
      Object localObject = lookupTag(paramArrayOfChar, paramInt1, paramInt2);
      this.workStack[(this.nesting + this.attrCount - 1)] = localObject;
      paramArrayOfChar = ((MappingInfo)localObject).prefix;
      localObject = ((MappingInfo)localObject).local;
      this.attrLocalName = ((String)localObject);
      this.attrPrefix = paramArrayOfChar;
      if (paramArrayOfChar != null) {
        if (paramArrayOfChar != "xmlns") {}
      }
      for (this.currentNamespacePrefix = ((String)localObject); (this.currentNamespacePrefix == null) || (this.namespacePrefixes); this.currentNamespacePrefix = "")
      {
        label87:
        this.tlist.startAttribute(0);
        return;
        if ((localObject != "xmlns") || (paramArrayOfChar != null)) {
          break label87;
        }
      }
    }
  }
  
  public void emitStartElement(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    closeStartTag();
    paramArrayOfChar = lookupTag(paramArrayOfChar, paramInt1, paramInt2);
    startElementCommon();
    ensureSpaceInWorkStack(this.nesting - 1);
    this.workStack[(this.nesting - 1)] = paramArrayOfChar;
  }
  
  public void endAttribute()
  {
    if (this.attrLocalName == null) {}
    label116:
    do
    {
      do
      {
        return;
        if (this.previous == 1)
        {
          this.previous = 0;
          return;
        }
        if (this.stringizingElementNesting >= 0) {
          this.ignoringLevel -= 1;
        }
        i = this.stringizingLevel - 1;
        this.stringizingLevel = i;
      } while (i != 0);
      if ((this.attrLocalName == "id") && (this.attrPrefix == "xml"))
      {
        j = this.startIndexes[(this.attrCount - 1)] + 5;
        m = this.tlist.gapStart;
        localObject1 = this.tlist.data;
        i = j;
        if (i < m) {
          break;
        }
      }
      this.attrLocalName = null;
      this.attrPrefix = null;
      if ((this.currentNamespacePrefix == null) || (this.namespacePrefixes)) {
        this.tlist.endAttribute();
      }
    } while (this.currentNamespacePrefix == null);
    int i2 = this.startIndexes[(this.attrCount - 1)];
    int i = i2;
    int i4 = this.tlist.gapStart;
    int i3 = i4 - i;
    Object localObject2 = this.tlist.data;
    int j = 0;
    int k = i;
    label199:
    Object localObject1 = localObject2;
    int i1 = i;
    int n = i3;
    int m = j;
    if (k < i4)
    {
      m = localObject2[k];
      if ((0xFFFF & m) > 40959)
      {
        localObject2 = new StringBuffer();
        this.tlist.stringValue(i, i4, (StringBuffer)localObject2);
        m = localObject2.hashCode();
        i1 = 0;
        n = ((StringBuffer)localObject2).length();
        localObject1 = new char[((StringBuffer)localObject2).length()];
        ((StringBuffer)localObject2).getChars(0, n, (char[])localObject1, 0);
      }
    }
    else
    {
      this.tlist.gapStart = i2;
      if (this.currentNamespacePrefix != "") {
        break label468;
      }
    }
    label468:
    for (localObject2 = null;; localObject2 = this.currentNamespacePrefix)
    {
      this.namespaceBindings = lookupNamespaceBinding((String)localObject2, (char[])localObject1, i1, n, m, this.namespaceBindings).namespaces;
      this.currentNamespacePrefix = null;
      return;
      k = i + 1;
      i = localObject1[i];
      if (((0xFFFF & i) > 40959) || (i == 9) || (i == 13) || (i == 10) || ((i == 32) && ((k == m) || (localObject1[k] == ' '))))
      {
        localObject1 = new StringBuffer();
        this.tlist.stringValue(j, m, (StringBuffer)localObject1);
        this.tlist.gapStart = j;
        this.tlist.write(TextUtils.replaceWhitespace(((StringBuffer)localObject1).toString(), true));
        break label116;
      }
      i = k;
      break;
      j = j * 31 + m;
      k += 1;
      break label199;
    }
  }
  
  public void endDocument()
  {
    if (this.stringizingLevel > 0)
    {
      writeJoiner();
      return;
    }
    this.nesting -= 2;
    this.namespaceBindings = ((NamespaceBinding)this.workStack[this.nesting]);
    this.workStack[this.nesting] = null;
    this.workStack[(this.nesting + 1)] = null;
    if (this.nesting == 0)
    {
      this.base.endDocument();
      return;
    }
    writeJoiner();
  }
  
  public void endElement()
  {
    closeStartTag();
    this.nesting -= 2;
    this.previous = 0;
    if (this.stringizingLevel == 0)
    {
      this.namespaceBindings = ((NamespaceBinding)this.workStack[this.nesting]);
      this.workStack[this.nesting] = null;
      this.workStack[(this.nesting + 1)] = null;
      this.base.endElement();
    }
    while (this.stringizingElementNesting != this.nesting) {
      return;
    }
    this.stringizingElementNesting = -1;
    this.previous = 2;
  }
  
  public void endElement(String paramString)
    throws SAXException
  {
    endElement();
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3)
  {
    endElement();
  }
  
  public void endEntity()
  {
    if ((this.base instanceof XConsumer)) {
      ((XConsumer)this.base).endEntity();
    }
  }
  
  public void endPrefixMapping(String paramString)
  {
    this.namespaceBindings = this.namespaceBindings.getNext();
  }
  
  public void error(char paramChar, String paramString)
  {
    if (this.messages == null) {
      throw new RuntimeException(paramString);
    }
    if (this.locator != null)
    {
      this.messages.error(paramChar, this.locator, paramString);
      return;
    }
    this.messages.error(paramChar, paramString);
  }
  
  public NamespaceBinding findNamespaceBinding(String paramString1, String paramString2, NamespaceBinding paramNamespaceBinding)
  {
    int i;
    int j;
    if (paramString2 == null)
    {
      i = 0;
      j = i;
      if (paramString1 != null) {
        j = i ^ paramString1.hashCode();
      }
      i = j & this.mappingTableMask;
    }
    for (Object localObject1 = this.mappingTable[i];; localObject1 = ((MappingInfo)localObject1).nextInBucket)
    {
      Object localObject2;
      if (localObject1 == null)
      {
        localObject2 = new MappingInfo();
        ((MappingInfo)localObject2).nextInBucket = this.mappingTable[i];
        this.mappingTable[i] = localObject2;
        ((MappingInfo)localObject2).tagHash = j;
        ((MappingInfo)localObject2).prefix = paramString1;
        ((MappingInfo)localObject2).local = paramString2;
        ((MappingInfo)localObject2).uri = paramString2;
        localObject1 = paramString2;
        if (paramString2 == "") {
          localObject1 = null;
        }
        ((MappingInfo)localObject2).namespaces = new NamespaceBinding(paramString1, (String)localObject1, paramNamespaceBinding);
        return ((MappingInfo)localObject2).namespaces;
        i = paramString2.hashCode();
        break;
      }
      if ((((MappingInfo)localObject1).tagHash == j) && (((MappingInfo)localObject1).prefix == paramString1))
      {
        localObject2 = ((MappingInfo)localObject1).namespaces;
        if ((localObject2 != null) && (((NamespaceBinding)localObject2).getNext() == this.namespaceBindings) && (((NamespaceBinding)localObject2).getPrefix() == paramString1) && (((MappingInfo)localObject1).uri == paramString2)) {
          return ((MappingInfo)localObject1).namespaces;
        }
      }
    }
  }
  
  public int getColumnNumber()
  {
    if (this.in != null)
    {
      int i = this.in.getColumnNumber();
      if (i > 0) {
        return i;
      }
    }
    return -1;
  }
  
  public String getFileName()
  {
    if (this.in == null) {
      return null;
    }
    return this.in.getName();
  }
  
  public int getLineNumber()
  {
    if (this.in == null) {}
    int i;
    do
    {
      return -1;
      i = this.in.getLineNumber();
    } while (i < 0);
    return i + 1;
  }
  
  public String getPublicId()
  {
    return null;
  }
  
  public String getSystemId()
  {
    if (this.in == null) {
      return null;
    }
    return this.in.getName();
  }
  
  public void ignorableWhitespace(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws SAXException
  {
    write(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public boolean ignoring()
  {
    return this.ignoringLevel > 0;
  }
  
  final boolean inElement()
  {
    int i = this.nesting;
    while ((i > 0) && (this.workStack[(i - 1)] == null)) {
      i -= 2;
    }
    return i != 0;
  }
  
  public boolean isStableSourceLocation()
  {
    return false;
  }
  
  public void linefeedFromParser()
  {
    if ((inElement()) && (checkWriteAtomic())) {
      this.base.write(10);
    }
  }
  
  public MappingInfo lookupNamespaceBinding(String paramString, char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3, NamespaceBinding paramNamespaceBinding)
  {
    int i;
    if (paramString == null) {
      i = paramInt3 & this.mappingTableMask;
    }
    for (Object localObject1 = this.mappingTable[i];; localObject1 = ((MappingInfo)localObject1).nextInBucket)
    {
      Object localObject2;
      if (localObject1 == null)
      {
        localObject2 = new MappingInfo();
        ((MappingInfo)localObject2).nextInBucket = this.mappingTable[i];
        this.mappingTable[i] = localObject2;
        localObject1 = new String(paramArrayOfChar, paramInt1, paramInt2).intern();
        ((MappingInfo)localObject2).tagHash = paramInt3;
        ((MappingInfo)localObject2).prefix = paramString;
        ((MappingInfo)localObject2).local = ((String)localObject1);
        ((MappingInfo)localObject2).uri = ((String)localObject1);
        paramArrayOfChar = (char[])localObject1;
        if (localObject1 == "") {
          paramArrayOfChar = null;
        }
        ((MappingInfo)localObject2).namespaces = new NamespaceBinding(paramString, paramArrayOfChar, paramNamespaceBinding);
        return (MappingInfo)localObject2;
        paramInt3 = paramString.hashCode() ^ paramInt3;
        break;
      }
      if ((((MappingInfo)localObject1).tagHash == paramInt3) && (((MappingInfo)localObject1).prefix == paramString))
      {
        localObject2 = ((MappingInfo)localObject1).namespaces;
        if ((localObject2 != null) && (((NamespaceBinding)localObject2).getNext() == this.namespaceBindings) && (((NamespaceBinding)localObject2).getPrefix() == paramString) && (MappingInfo.equals(((MappingInfo)localObject1).uri, paramArrayOfChar, paramInt1, paramInt2))) {
          return (MappingInfo)localObject1;
        }
      }
    }
  }
  
  MappingInfo lookupTag(Symbol paramSymbol)
  {
    String str1 = paramSymbol.getLocalPart();
    Object localObject2 = paramSymbol.getPrefix();
    Object localObject1 = localObject2;
    if (localObject2 == "") {
      localObject1 = null;
    }
    String str2 = paramSymbol.getNamespaceURI();
    int i = MappingInfo.hash((String)localObject1, str1);
    int j = i & this.mappingTableMask;
    MappingInfo localMappingInfo = this.mappingTable[j];
    for (localObject2 = localMappingInfo;; localObject2 = ((MappingInfo)localObject2).nextInBucket)
    {
      if (localObject2 == null)
      {
        localObject2 = new MappingInfo();
        ((MappingInfo)localObject2).qname = paramSymbol;
        ((MappingInfo)localObject2).prefix = ((String)localObject1);
        ((MappingInfo)localObject2).uri = str2;
        ((MappingInfo)localObject2).local = str1;
        ((MappingInfo)localObject2).tagHash = i;
        ((MappingInfo)localObject2).nextInBucket = localMappingInfo;
        this.mappingTable[j] = localMappingInfo;
        return (MappingInfo)localObject2;
      }
      if (paramSymbol == ((MappingInfo)localObject2).qname) {
        return (MappingInfo)localObject2;
      }
      if ((str1 == ((MappingInfo)localObject2).local) && (((MappingInfo)localObject2).qname == null) && ((str2 == ((MappingInfo)localObject2).uri) || (((MappingInfo)localObject2).uri == null)) && (localObject1 == ((MappingInfo)localObject2).prefix))
      {
        ((MappingInfo)localObject2).uri = str2;
        ((MappingInfo)localObject2).qname = paramSymbol;
        return (MappingInfo)localObject2;
      }
    }
  }
  
  MappingInfo lookupTag(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int j = 0;
    int m = 0;
    int k = -1;
    int i = 0;
    if (i < paramInt2)
    {
      int n = paramArrayOfChar[(paramInt1 + i)];
      if ((n == 58) && (k < 0))
      {
        k = i;
        n = 0;
        m = j;
      }
      for (j = n;; j = j * 31 + n)
      {
        i += 1;
        break;
      }
    }
    j ^= m;
    i = j & this.mappingTableMask;
    MappingInfo localMappingInfo2 = this.mappingTable[i];
    for (MappingInfo localMappingInfo1 = localMappingInfo2;; localMappingInfo1 = localMappingInfo1.nextInBucket)
    {
      if (localMappingInfo1 == null)
      {
        localMappingInfo1 = new MappingInfo();
        localMappingInfo1.tagHash = j;
        if (k >= 0)
        {
          localMappingInfo1.prefix = new String(paramArrayOfChar, paramInt1, k).intern();
          j = k + 1;
        }
        for (localMappingInfo1.local = new String(paramArrayOfChar, paramInt1 + j, paramInt2 - j).intern();; localMappingInfo1.local = new String(paramArrayOfChar, paramInt1, paramInt2).intern())
        {
          localMappingInfo1.nextInBucket = localMappingInfo2;
          this.mappingTable[i] = localMappingInfo2;
          return localMappingInfo1;
          localMappingInfo1.prefix = null;
        }
      }
      if ((j == localMappingInfo1.tagHash) && (localMappingInfo1.match(paramArrayOfChar, paramInt1, paramInt2))) {
        return localMappingInfo1;
      }
    }
  }
  
  public void processingInstruction(String paramString1, String paramString2)
  {
    paramString2 = paramString2.toCharArray();
    processingInstructionCommon(paramString1, paramString2, 0, paramString2.length);
  }
  
  void processingInstructionCommon(String paramString, char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (this.stringizingLevel == 0)
    {
      closeStartTag();
      if ((this.base instanceof XConsumer)) {
        ((XConsumer)this.base).writeProcessingInstruction(paramString, paramArrayOfChar, paramInt1, paramInt2);
      }
    }
    while (this.stringizingElementNesting >= 0) {
      return;
    }
    this.base.write(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void processingInstructionFromParser(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt2 == 3) && (!inElement()) && (paramArrayOfChar[paramInt1] == 'x') && (paramArrayOfChar[(paramInt1 + 1)] == 'm') && (paramArrayOfChar[(paramInt1 + 2)] == 'l')) {
      return;
    }
    processingInstructionCommon(new String(paramArrayOfChar, paramInt1, paramInt2), paramArrayOfChar, paramInt3, paramInt4);
  }
  
  public void setDocumentLocator(Locator paramLocator)
  {
    if ((paramLocator instanceof SourceLocator)) {
      this.locator = ((SourceLocator)paramLocator);
    }
  }
  
  public void setMessages(SourceMessages paramSourceMessages)
  {
    this.messages = paramSourceMessages;
  }
  
  public void setSourceLocator(LineBufferedReader paramLineBufferedReader)
  {
    this.in = paramLineBufferedReader;
    this.locator = this;
  }
  
  public void setSourceLocator(SourceLocator paramSourceLocator)
  {
    this.locator = paramSourceLocator;
  }
  
  public void skippedEntity(String paramString) {}
  
  public void startAttribute(Object paramObject)
  {
    this.previous = 0;
    if ((paramObject instanceof Symbol))
    {
      Object localObject = (Symbol)paramObject;
      String str = ((Symbol)localObject).getLocalPart();
      this.attrLocalName = str;
      this.attrPrefix = ((Symbol)localObject).getPrefix();
      localObject = ((Symbol)localObject).getNamespaceURI();
      if ((localObject == "http://www.w3.org/2000/xmlns/") || ((localObject == "") && (str == "xmlns"))) {
        error('e', "arttribute name cannot be 'xmlns' or in xmlns namespace");
      }
    }
    if ((this.nesting == 2) && (this.workStack[1] == null)) {
      error('e', "attribute not allowed at document level");
    }
    if ((this.attrCount < 0) && (this.nesting > 0)) {
      error('e', "attribute '" + paramObject + "' follows non-attribute content");
    }
    if (!startAttributeCommon()) {
      return;
    }
    this.workStack[(this.nesting + this.attrCount - 1)] = paramObject;
    if (this.nesting == 0)
    {
      this.base.startAttribute(paramObject);
      return;
    }
    this.tlist.startAttribute(0);
  }
  
  public void startDocument()
  {
    closeStartTag();
    if (this.stringizingLevel > 0)
    {
      writeJoiner();
      return;
    }
    if (this.nesting == 0) {
      this.base.startDocument();
    }
    for (;;)
    {
      ensureSpaceInWorkStack(this.nesting);
      this.workStack[this.nesting] = this.namespaceBindings;
      this.workStack[(this.nesting + 1)] = null;
      this.nesting += 2;
      return;
      writeJoiner();
    }
  }
  
  public void startElement(Object paramObject)
  {
    startElementCommon();
    if (this.stringizingLevel == 0)
    {
      ensureSpaceInWorkStack(this.nesting - 1);
      this.workStack[(this.nesting - 1)] = paramObject;
      if (this.copyNamespacesMode == 0) {
        this.namespaceBindings = NamespaceBinding.predefinedXML;
      }
    }
    else
    {
      return;
    }
    if ((this.copyNamespacesMode == 1) || (this.nesting == 2))
    {
      if ((paramObject instanceof XName)) {}
      for (paramObject = ((XName)paramObject).getNamespaceNodes();; paramObject = NamespaceBinding.predefinedXML)
      {
        this.namespaceBindings = ((NamespaceBinding)paramObject);
        return;
      }
    }
    int i = 2;
    NamespaceBinding localNamespaceBinding;
    if (i == this.nesting)
    {
      localNamespaceBinding = null;
      label104:
      if (localNamespaceBinding != null) {
        break label167;
      }
      if (!(paramObject instanceof XName)) {
        break label160;
      }
    }
    label160:
    for (paramObject = ((XName)paramObject).getNamespaceNodes();; paramObject = NamespaceBinding.predefinedXML)
    {
      this.namespaceBindings = ((NamespaceBinding)paramObject);
      return;
      if (this.workStack[(i + 1)] != null)
      {
        localNamespaceBinding = (NamespaceBinding)this.workStack[i];
        break label104;
      }
      i += 2;
      break;
    }
    label167:
    if (this.copyNamespacesMode == 2)
    {
      this.namespaceBindings = localNamespaceBinding;
      return;
    }
    if ((paramObject instanceof XName))
    {
      paramObject = ((XName)paramObject).getNamespaceNodes();
      if (NamespaceBinding.commonAncestor(localNamespaceBinding, (NamespaceBinding)paramObject) == localNamespaceBinding)
      {
        this.namespaceBindings = ((NamespaceBinding)paramObject);
        return;
      }
      this.namespaceBindings = mergeHelper(localNamespaceBinding, (NamespaceBinding)paramObject);
      return;
    }
    this.namespaceBindings = localNamespaceBinding;
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
  {
    startElement(Symbol.make(paramString1, paramString2));
    int j = paramAttributes.getLength();
    int i = 0;
    while (i < j)
    {
      startAttribute(Symbol.make(paramAttributes.getURI(i), paramAttributes.getLocalName(i)));
      write(paramAttributes.getValue(i));
      endAttribute();
      i += 1;
    }
  }
  
  public void startElement(String paramString, AttributeList paramAttributeList)
  {
    startElement(paramString.intern());
    int j = paramAttributeList.getLength();
    int i = 0;
    while (i < j)
    {
      paramString = paramAttributeList.getName(i).intern();
      paramAttributeList.getType(i);
      String str = paramAttributeList.getValue(i);
      startAttribute(paramString);
      write(str);
      endAttribute();
      i += 1;
    }
  }
  
  protected void startElementCommon()
  {
    closeStartTag();
    if (this.stringizingLevel == 0)
    {
      ensureSpaceInWorkStack(this.nesting);
      this.workStack[this.nesting] = this.namespaceBindings;
      this.tlist.startElement(0);
      this.base = this.tlist;
      this.attrCount = 0;
    }
    for (;;)
    {
      this.nesting += 2;
      return;
      if ((this.previous == 2) && (this.stringizingElementNesting < 0)) {
        write(32);
      }
      this.previous = 0;
      if (this.stringizingElementNesting < 0) {
        this.stringizingElementNesting = this.nesting;
      }
    }
  }
  
  public void startPrefixMapping(String paramString1, String paramString2)
  {
    this.namespaceBindings = findNamespaceBinding(paramString1.intern(), paramString2.intern(), this.namespaceBindings);
  }
  
  public void textFromParser(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (!inElement())
    {
      i = 0;
      if (i != paramInt2) {}
    }
    while ((paramInt2 <= 0) || (!checkWriteAtomic())) {
      for (;;)
      {
        int i;
        return;
        if (!Character.isWhitespace(paramArrayOfChar[(paramInt1 + i)]))
        {
          error('e', "text at document level");
          return;
        }
        i += 1;
      }
    }
    this.base.write(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void write(int paramInt)
  {
    if (checkWriteAtomic()) {
      this.base.write(paramInt);
    }
  }
  
  public void write(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      writeJoiner();
    }
    while (!checkWriteAtomic()) {
      return;
    }
    this.base.write(paramCharSequence, paramInt1, paramInt2);
  }
  
  public void write(String paramString)
  {
    write(paramString, 0, paramString.length());
  }
  
  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      writeJoiner();
    }
    while (!checkWriteAtomic()) {
      return;
    }
    this.base.write(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void writeBoolean(boolean paramBoolean)
  {
    if (checkWriteAtomic()) {
      this.base.writeBoolean(paramBoolean);
    }
  }
  
  public void writeCDATA(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (checkWriteAtomic())
    {
      if ((this.base instanceof XConsumer)) {
        ((XConsumer)this.base).writeCDATA(paramArrayOfChar, paramInt1, paramInt2);
      }
    }
    else {
      return;
    }
    write(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void writeComment(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    checkValidComment(paramArrayOfChar, paramInt1, paramInt2);
    commentFromParser(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void writeDocumentUri(Object paramObject)
  {
    if ((this.nesting == 2) && ((this.base instanceof TreeList))) {
      ((TreeList)this.base).writeDocumentUri(paramObject);
    }
  }
  
  public void writeDouble(double paramDouble)
  {
    if (checkWriteAtomic()) {
      this.base.writeDouble(paramDouble);
    }
  }
  
  public void writeFloat(float paramFloat)
  {
    if (checkWriteAtomic()) {
      this.base.writeFloat(paramFloat);
    }
  }
  
  public void writeInt(int paramInt)
  {
    if (checkWriteAtomic()) {
      this.base.writeInt(paramInt);
    }
  }
  
  protected void writeJoiner()
  {
    this.previous = 0;
    if (this.ignoringLevel == 0) {
      ((TreeList)this.base).writeJoiner();
    }
  }
  
  public void writeLong(long paramLong)
  {
    if (checkWriteAtomic()) {
      this.base.writeLong(paramLong);
    }
  }
  
  public void writeObject(Object paramObject)
  {
    if (this.ignoringLevel > 0) {}
    for (;;)
    {
      return;
      if ((paramObject instanceof SeqPosition))
      {
        paramObject = (SeqPosition)paramObject;
        writePosition(((SeqPosition)paramObject).sequence, ((SeqPosition)paramObject).getPos());
        return;
      }
      if ((paramObject instanceof TreeList))
      {
        ((TreeList)paramObject).consume(this);
        return;
      }
      if ((!(paramObject instanceof List)) || ((paramObject instanceof CharSeq))) {
        break;
      }
      paramObject = ((List)paramObject).iterator();
      int i = 0;
      while (((Iterator)paramObject).hasNext())
      {
        writeObject(((Iterator)paramObject).next());
        i += 1;
      }
    }
    if ((paramObject instanceof Keyword))
    {
      startAttribute(((Keyword)paramObject).asSymbol());
      this.previous = 1;
      return;
    }
    closeStartTag();
    if ((paramObject instanceof UnescapedData))
    {
      this.base.writeObject(paramObject);
      this.previous = 0;
      return;
    }
    if (this.previous == 2) {
      write(32);
    }
    TextUtils.textValue(paramObject, this);
    this.previous = 2;
  }
  
  public void writePosition(AbstractSequence paramAbstractSequence, int paramInt)
  {
    if (this.ignoringLevel > 0) {}
    do
    {
      return;
      if ((this.stringizingLevel > 0) && (this.previous == 2))
      {
        if (this.stringizingElementNesting < 0) {
          write(32);
        }
        this.previous = 0;
      }
      paramAbstractSequence.consumeNext(paramInt, this);
    } while ((this.stringizingLevel <= 0) || (this.stringizingElementNesting >= 0));
    this.previous = 2;
  }
  
  public void writeProcessingInstruction(String paramString, char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    paramString = TextUtils.replaceWhitespace(paramString, true);
    int j = paramInt1 + paramInt2;
    for (;;)
    {
      int i = j - 1;
      if (i < paramInt1) {
        break;
      }
      int k = paramArrayOfChar[i];
      int m;
      do
      {
        j = i;
        if (k != 62) {
          break;
        }
        m = i - 1;
        j = m;
        if (m < paramInt1) {
          break;
        }
        j = paramArrayOfChar[m];
        k = j;
        i = m;
      } while (j != 63);
      error('e', "'?>' is not allowed in a processing-instruction");
      j = m;
    }
    if ("xml".equalsIgnoreCase(paramString)) {
      error('e', "processing-instruction target may not be 'xml' (ignoring case)");
    }
    if (!XName.isNCName(paramString)) {
      error('e', "processing-instruction target '" + paramString + "' is not a valid Name");
    }
    processingInstructionCommon(paramString, paramArrayOfChar, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\xml\XMLFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */