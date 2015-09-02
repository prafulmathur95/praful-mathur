package gnu.lists;

import gnu.text.Char;
import java.io.PrintWriter;

public class TreeList
  extends AbstractSequence
  implements Appendable, XConsumer, PositionConsumer, Consumable
{
  protected static final int BEGIN_ATTRIBUTE_LONG = 61705;
  public static final int BEGIN_ATTRIBUTE_LONG_SIZE = 5;
  protected static final int BEGIN_DOCUMENT = 61712;
  protected static final int BEGIN_ELEMENT_LONG = 61704;
  protected static final int BEGIN_ELEMENT_SHORT = 40960;
  protected static final int BEGIN_ELEMENT_SHORT_INDEX_MAX = 4095;
  public static final int BEGIN_ENTITY = 61714;
  public static final int BEGIN_ENTITY_SIZE = 5;
  static final char BOOL_FALSE = '';
  static final char BOOL_TRUE = '';
  static final int BYTE_PREFIX = 61440;
  static final int CDATA_SECTION = 61717;
  static final int CHAR_FOLLOWS = 61702;
  static final int COMMENT = 61719;
  protected static final int DOCUMENT_URI = 61720;
  static final int DOUBLE_FOLLOWS = 61701;
  static final int END_ATTRIBUTE = 61706;
  public static final int END_ATTRIBUTE_SIZE = 1;
  protected static final int END_DOCUMENT = 61713;
  protected static final int END_ELEMENT_LONG = 61708;
  protected static final int END_ELEMENT_SHORT = 61707;
  protected static final int END_ENTITY = 61715;
  static final int FLOAT_FOLLOWS = 61700;
  public static final int INT_FOLLOWS = 61698;
  static final int INT_SHORT_ZERO = 49152;
  static final int JOINER = 61718;
  static final int LONG_FOLLOWS = 61699;
  public static final int MAX_CHAR_SHORT = 40959;
  static final int MAX_INT_SHORT = 8191;
  static final int MIN_INT_SHORT = -4096;
  static final char OBJECT_REF_FOLLOWS = '';
  static final int OBJECT_REF_SHORT = 57344;
  static final int OBJECT_REF_SHORT_INDEX_MAX = 4095;
  protected static final char POSITION_PAIR_FOLLOWS = '';
  static final char POSITION_REF_FOLLOWS = '';
  protected static final int PROCESSING_INSTRUCTION = 61716;
  public int attrStart;
  int currentParent = -1;
  public char[] data;
  public int docStart;
  public int gapEnd;
  public int gapStart;
  public Object[] objects;
  public int oindex;
  
  public TreeList()
  {
    resizeObjects();
    this.gapEnd = 200;
    this.data = new char[this.gapEnd];
  }
  
  public TreeList(TreeList paramTreeList)
  {
    this(paramTreeList, 0, paramTreeList.data.length);
  }
  
  public TreeList(TreeList paramTreeList, int paramInt1, int paramInt2)
  {
    this();
    paramTreeList.consumeIRange(paramInt1, paramInt2, this);
  }
  
  private Object copyToList(int paramInt1, int paramInt2)
  {
    return new TreeList(this, paramInt1, paramInt2);
  }
  
  public Consumer append(char paramChar)
  {
    write(paramChar);
    return this;
  }
  
  public Consumer append(CharSequence paramCharSequence)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null) {
      localObject = "null";
    }
    return append((CharSequence)localObject, 0, ((CharSequence)localObject).length());
  }
  
  public Consumer append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null) {
      localObject = "null";
    }
    while (paramInt1 < paramInt2)
    {
      append(((CharSequence)localObject).charAt(paramInt1));
      paramInt1 += 1;
    }
    return this;
  }
  
  public void beginEntity(Object paramObject)
  {
    int i = -1;
    if (this.gapStart != 0) {
      return;
    }
    ensureSpace(6);
    this.gapEnd -= 1;
    int j = this.gapStart;
    this.data[j] = 61714;
    setIntN(j + 1, find(paramObject));
    if (this.currentParent == -1) {}
    for (;;)
    {
      setIntN(j + 3, i);
      this.gapStart = (j + 5);
      this.currentParent = j;
      this.data[this.gapEnd] = 61715;
      return;
      i = this.currentParent - j;
    }
  }
  
  public void clear()
  {
    this.gapStart = 0;
    this.gapEnd = this.data.length;
    this.attrStart = 0;
    if (this.gapEnd > 1500)
    {
      this.gapEnd = 200;
      this.data = new char[this.gapEnd];
    }
    this.objects = null;
    this.oindex = 0;
    resizeObjects();
  }
  
  public int compare(int paramInt1, int paramInt2)
  {
    paramInt1 = posToDataIndex(paramInt1);
    paramInt2 = posToDataIndex(paramInt2);
    if (paramInt1 < paramInt2) {
      return -1;
    }
    if (paramInt1 > paramInt2) {
      return 1;
    }
    return 0;
  }
  
  public void consume(Consumer paramConsumer)
  {
    consumeIRange(0, this.data.length, paramConsumer);
  }
  
  public void consume(SeqPosition paramSeqPosition)
  {
    ensureSpace(3);
    int i = find(paramSeqPosition.copy());
    paramSeqPosition = this.data;
    int j = this.gapStart;
    this.gapStart = (j + 1);
    paramSeqPosition[j] = 61710;
    setIntN(this.gapStart, i);
    this.gapStart += 2;
  }
  
  public int consumeIRange(int paramInt1, int paramInt2, Consumer paramConsumer)
  {
    int j = paramInt1;
    int i;
    int k;
    Object localObject;
    if ((paramInt1 <= this.gapStart) && (paramInt2 > this.gapStart))
    {
      i = this.gapStart;
      paramInt1 = j;
      j = i;
      k = paramInt1;
      if (paramInt1 >= i)
      {
        if ((paramInt1 != this.gapStart) || (paramInt2 <= this.gapEnd)) {
          break label1167;
        }
        k = this.gapEnd;
        j = paramInt2;
      }
      localObject = this.data;
      paramInt1 = k + 1;
      i = localObject[k];
      if (i > 40959) {
        break label162;
      }
      k = paramInt1 - 1;
    }
    for (;;)
    {
      if (paramInt1 >= j) {}
      for (;;)
      {
        paramConsumer.write(this.data, k, paramInt1 - k);
        i = j;
        break;
        i = paramInt2;
        paramInt1 = j;
        break;
        localObject = this.data;
        i = paramInt1 + 1;
        if (localObject[paramInt1] <= 40959) {
          break label1176;
        }
        paramInt1 = i - 1;
      }
      label162:
      if ((i >= 57344) && (i <= 61439))
      {
        paramConsumer.writeObject(this.objects[(i - 57344)]);
        i = j;
        break;
      }
      if ((i >= 40960) && (i <= 45055))
      {
        paramConsumer.startElement(this.objects[(i - 40960)]);
        paramInt1 += 2;
        i = j;
        break;
      }
      if ((i >= 45056) && (i <= 57343))
      {
        paramConsumer.writeInt(i - 49152);
        i = j;
        break;
      }
      switch (i)
      {
      case 61703: 
      default: 
        throw new Error("unknown code:" + i);
      case 61712: 
        paramConsumer.startDocument();
        paramInt1 += 4;
        i = j;
        break;
      case 61713: 
        paramConsumer.endDocument();
        i = j;
        break;
      case 61714: 
        if ((paramConsumer instanceof TreeList)) {
          ((TreeList)paramConsumer).beginEntity(this.objects[getIntN(paramInt1)]);
        }
        paramInt1 += 4;
        i = j;
        break;
      case 61715: 
        if ((paramConsumer instanceof TreeList))
        {
          ((TreeList)paramConsumer).endEntity();
          i = j;
        }
        break;
      case 61720: 
        if ((paramConsumer instanceof TreeList)) {
          ((TreeList)paramConsumer).writeDocumentUri(this.objects[getIntN(paramInt1)]);
        }
        paramInt1 += 2;
        i = j;
        break;
      case 61719: 
        i = getIntN(paramInt1);
        paramInt1 += 2;
        if ((paramConsumer instanceof XConsumer)) {
          ((XConsumer)paramConsumer).writeComment(this.data, paramInt1, i);
        }
        paramInt1 += i;
        i = j;
        break;
      case 61717: 
        i = getIntN(paramInt1);
        paramInt1 += 2;
        if ((paramConsumer instanceof XConsumer)) {
          ((XConsumer)paramConsumer).writeCDATA(this.data, paramInt1, i);
        }
        for (;;)
        {
          paramInt1 += i;
          i = j;
          break;
          paramConsumer.write(this.data, paramInt1, i);
        }
      case 61716: 
        localObject = (String)this.objects[getIntN(paramInt1)];
        i = getIntN(paramInt1 + 2);
        paramInt1 += 4;
        if ((paramConsumer instanceof XConsumer)) {
          ((XConsumer)paramConsumer).writeProcessingInstruction((String)localObject, this.data, paramInt1, i);
        }
        paramInt1 += i;
        i = j;
        break;
      case 61696: 
      case 61697: 
        if (i != 61696) {}
        for (boolean bool = true;; bool = false)
        {
          paramConsumer.writeBoolean(bool);
          i = j;
          break;
        }
      case 61718: 
        paramConsumer.write("");
        i = j;
        break;
      case 61702: 
        paramConsumer.write(this.data, paramInt1, i + 1 - 61702);
        paramInt1 += 1;
        i = j;
        break;
      case 61711: 
        localObject = (AbstractSequence)this.objects[getIntN(paramInt1)];
        i = getIntN(paramInt1 + 2);
        if ((paramConsumer instanceof PositionConsumer)) {
          ((PositionConsumer)paramConsumer).writePosition((AbstractSequence)localObject, i);
        }
        for (;;)
        {
          paramInt1 += 4;
          i = j;
          break;
          paramConsumer.writeObject(((AbstractSequence)localObject).getIteratorAtPos(i));
        }
      case 61710: 
        if ((paramConsumer instanceof PositionConsumer))
        {
          ((PositionConsumer)paramConsumer).consume((SeqPosition)this.objects[getIntN(paramInt1)]);
          paramInt1 += 2;
          i = j;
        }
        break;
      case 61709: 
        paramConsumer.writeObject(this.objects[getIntN(paramInt1)]);
        paramInt1 += 2;
        i = j;
        break;
      case 61707: 
        paramInt1 += 1;
        paramConsumer.endElement();
        i = j;
        break;
      case 61704: 
        k = getIntN(paramInt1);
        if (k >= 0) {}
        for (i = paramInt1 - 1;; i = this.data.length)
        {
          paramInt1 += 2;
          i = getIntN(k + i + 1);
          paramConsumer.startElement(this.objects[i]);
          i = j;
          break;
        }
      case 61708: 
        getIntN(paramInt1);
        paramConsumer.endElement();
        paramInt1 += 6;
        i = j;
        break;
      case 61705: 
        i = getIntN(paramInt1);
        paramConsumer.startAttribute(this.objects[i]);
        paramInt1 += 4;
        i = j;
        break;
      case 61706: 
        paramConsumer.endAttribute();
        i = j;
        break;
      case 61698: 
        paramConsumer.writeInt(getIntN(paramInt1));
        paramInt1 += 2;
        i = j;
        break;
      case 61700: 
        paramConsumer.writeFloat(Float.intBitsToFloat(getIntN(paramInt1)));
        paramInt1 += 2;
        i = j;
        break;
      case 61699: 
        paramConsumer.writeLong(getLongN(paramInt1));
        paramInt1 += 4;
        i = j;
        break;
      case 61701: 
        paramConsumer.writeDouble(Double.longBitsToDouble(getLongN(paramInt1)));
        paramInt1 += 4;
        i = j;
        break;
        label1167:
        return paramInt1;
        i = j;
        break;
        label1176:
        paramInt1 = i;
      }
    }
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer)
  {
    if (!hasNext(paramInt)) {
      return false;
    }
    int j = posToDataIndex(paramInt);
    int i = nextNodeIndex(j, Integer.MAX_VALUE);
    paramInt = i;
    if (i == j) {
      paramInt = nextDataIndex(j);
    }
    if (paramInt >= 0) {
      consumeIRange(j, paramInt, paramConsumer);
    }
    return true;
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer)
  {
    consumeIRange(posToDataIndex(paramInt1), posToDataIndex(paramInt2), paramConsumer);
  }
  
  public int createPos(int paramInt, boolean paramBoolean)
  {
    return createRelativePos(0, paramInt, paramBoolean);
  }
  
  public int createRelativePos(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int i = paramInt2;
    if (paramBoolean)
    {
      if (paramInt2 == 0)
      {
        if ((paramInt1 & 0x1) != 0) {
          return paramInt1;
        }
        if (paramInt1 == 0) {
          return 1;
        }
      }
      i = paramInt2 - 1;
    }
    if (i < 0) {
      throw unsupported("backwards createRelativePos");
    }
    paramInt1 = posToDataIndex(paramInt1);
    do
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      paramInt2 = nextDataIndex(paramInt1);
      paramInt1 = paramInt2;
    } while (paramInt2 >= 0);
    throw new IndexOutOfBoundsException();
    paramInt2 = paramInt1;
    if (paramInt1 >= this.gapEnd) {
      paramInt2 = paramInt1 - (this.gapEnd - this.gapStart);
    }
    if (paramBoolean) {}
    for (paramInt1 = paramInt2 + 1 << 1 | 0x1;; paramInt1 = paramInt2 << 1) {
      return paramInt1;
    }
  }
  
  public Object documentUriOfPos(int paramInt)
  {
    paramInt = posToDataIndex(paramInt);
    if (paramInt == this.data.length) {}
    do
    {
      do
      {
        return null;
      } while (this.data[paramInt] != 61712);
      int i = paramInt + 5;
      paramInt = i;
      if (i == this.gapStart) {
        paramInt = this.gapEnd;
      }
    } while ((paramInt >= this.data.length) || (this.data[paramInt] != 61720));
    return this.objects[getIntN(paramInt + 1)];
  }
  
  public void dump()
  {
    PrintWriter localPrintWriter = new PrintWriter(System.out);
    dump(localPrintWriter);
    localPrintWriter.flush();
  }
  
  public void dump(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.println(getClass().getName() + " @" + Integer.toHexString(System.identityHashCode(this)) + " gapStart:" + this.gapStart + " gapEnd:" + this.gapEnd + " length:" + this.data.length);
    dump(paramPrintWriter, 0, this.data.length);
  }
  
  public void dump(PrintWriter paramPrintWriter, int paramInt1, int paramInt2)
  {
    int j = 0;
    int i = paramInt1;
    paramInt1 = j;
    if (i < paramInt2)
    {
      int k;
      if (i >= this.gapStart)
      {
        k = i;
        j = paramInt1;
        if (i < this.gapEnd) {}
      }
      else
      {
        k = this.data[i];
        paramPrintWriter.print("" + i + ": 0x" + Integer.toHexString(k) + '=' + (short)k);
        j = paramInt1 - 1;
        paramInt1 = j;
        if (j < 0)
        {
          if (k > 40959) {
            break label275;
          }
          if ((k < 32) || (k >= 127)) {
            break label215;
          }
          paramPrintWriter.print("='" + (char)k + "'");
          paramInt1 = j;
        }
      }
      label215:
      label275:
      Object localObject1;
      for (;;)
      {
        paramPrintWriter.println();
        k = i;
        j = paramInt1;
        if (1 != 0)
        {
          k = i;
          j = paramInt1;
          if (paramInt1 > 0)
          {
            k = i + paramInt1;
            j = 0;
          }
        }
        i = k + 1;
        paramInt1 = j;
        break;
        if (k == 10)
        {
          paramPrintWriter.print("='\\n'");
          paramInt1 = j;
        }
        else
        {
          paramPrintWriter.print("='\\u" + Integer.toHexString(k) + "'");
          paramInt1 = j;
          continue;
          if ((k >= 57344) && (k <= 61439))
          {
            paramInt1 = k - 57344;
            localObject1 = this.objects[paramInt1];
            paramPrintWriter.print("=Object#" + paramInt1 + '=' + localObject1 + ':' + localObject1.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(localObject1)));
            paramInt1 = j;
          }
          else if ((k >= 40960) && (k <= 45055))
          {
            paramInt1 = k - 40960;
            j = this.data[(i + 1)];
            paramPrintWriter.print("=BEGIN_ELEMENT_SHORT end:" + (j + i) + " index#" + paramInt1 + "=<" + this.objects[paramInt1] + '>');
            paramInt1 = 2;
          }
          else if ((k >= 45056) && (k <= 57343))
          {
            paramPrintWriter.print("= INT_SHORT:" + (k - 49152));
            paramInt1 = j;
          }
          else
          {
            long l;
            switch (k)
            {
            case 61703: 
            default: 
              paramInt1 = j;
              break;
            case 61696: 
              paramPrintWriter.print("= false");
              paramInt1 = j;
              break;
            case 61698: 
              paramInt1 = getIntN(i + 1);
              paramPrintWriter.print("=INT_FOLLOWS value:" + paramInt1);
              paramInt1 = 2;
              break;
            case 61699: 
              l = getLongN(i + 1);
              paramPrintWriter.print("=LONG_FOLLOWS value:" + l);
              paramInt1 = 4;
              break;
            case 61700: 
              paramInt1 = getIntN(i + 1);
              paramPrintWriter.write("=FLOAT_FOLLOWS value:" + Float.intBitsToFloat(paramInt1));
              paramInt1 = 2;
              break;
            case 61701: 
              l = getLongN(i + 1);
              paramPrintWriter.print("=DOUBLE_FOLLOWS value:" + Double.longBitsToDouble(l));
              paramInt1 = 4;
              break;
            case 61712: 
              j = getIntN(i + 1);
              if (j < 0) {}
              for (paramInt1 = this.data.length;; paramInt1 = i)
              {
                paramPrintWriter.print("=BEGIN_DOCUMENT end:");
                paramPrintWriter.print(j + paramInt1);
                paramPrintWriter.print(" parent:");
                paramPrintWriter.print(getIntN(i + 3));
                paramInt1 = 4;
                break;
              }
            case 61714: 
              paramInt1 = getIntN(i + 1);
              paramPrintWriter.print("=BEGIN_ENTITY base:");
              paramPrintWriter.print(paramInt1);
              paramPrintWriter.print(" parent:");
              paramPrintWriter.print(getIntN(i + 3));
              paramInt1 = 4;
              break;
            case 61715: 
              paramPrintWriter.print("=END_ENTITY");
              paramInt1 = j;
              break;
            case 61720: 
              paramPrintWriter.print("=DOCUMENT_URI: ");
              paramInt1 = getIntN(i + 1);
              paramPrintWriter.print(this.objects[paramInt1]);
              paramInt1 = 2;
              break;
            case 61719: 
              paramPrintWriter.print("=COMMENT: '");
              paramInt1 = getIntN(i + 1);
              paramPrintWriter.write(this.data, i + 3, paramInt1);
              paramPrintWriter.print('\'');
              paramInt1 += 2;
              break;
            case 61717: 
              paramPrintWriter.print("=CDATA: '");
              paramInt1 = getIntN(i + 1);
              paramPrintWriter.write(this.data, i + 3, paramInt1);
              paramPrintWriter.print('\'');
              paramInt1 += 2;
              break;
            case 61716: 
              paramPrintWriter.print("=PROCESSING_INSTRUCTION: ");
              paramInt1 = getIntN(i + 1);
              paramPrintWriter.print(this.objects[paramInt1]);
              paramPrintWriter.print(" '");
              paramInt1 = getIntN(i + 3);
              paramPrintWriter.write(this.data, i + 5, paramInt1);
              paramPrintWriter.print('\'');
              paramInt1 += 4;
              break;
            case 61713: 
              paramPrintWriter.print("=END_DOCUMENT");
              paramInt1 = j;
              break;
            case 61697: 
              paramPrintWriter.print("= true");
              paramInt1 = j;
              break;
            case 61718: 
              paramPrintWriter.print("= joiner");
              paramInt1 = j;
              break;
            case 61702: 
              paramPrintWriter.print("=CHAR_FOLLOWS");
              paramInt1 = 1;
              break;
            case 61709: 
            case 61710: 
              paramInt1 = 2;
              break;
            case 61707: 
              paramPrintWriter.print("=END_ELEMENT_SHORT begin:");
              paramInt1 = i - this.data[(i + 1)];
              paramPrintWriter.print(paramInt1);
              paramInt1 = this.data[paramInt1] - 40960;
              paramPrintWriter.print(" -> #");
              paramPrintWriter.print(paramInt1);
              paramPrintWriter.print("=<");
              paramPrintWriter.print(this.objects[paramInt1]);
              paramPrintWriter.print('>');
              paramInt1 = 1;
              break;
            case 61704: 
              j = getIntN(i + 1);
              if (j < 0)
              {
                paramInt1 = this.data.length;
                paramInt1 = j + paramInt1;
                paramPrintWriter.print("=BEGIN_ELEMENT_LONG end:");
                paramPrintWriter.print(paramInt1);
                paramInt1 = getIntN(paramInt1 + 1);
                paramPrintWriter.print(" -> #");
                paramPrintWriter.print(paramInt1);
                if ((paramInt1 < 0) || (paramInt1 + 1 >= this.objects.length)) {
                  break label1369;
                }
                paramPrintWriter.print("=<" + this.objects[paramInt1] + '>');
              }
              for (;;)
              {
                paramInt1 = 2;
                break;
                paramInt1 = i;
                break label1272;
                paramPrintWriter.print("=<out-of-bounds>");
              }
            case 61708: 
              paramInt1 = getIntN(i + 1);
              paramPrintWriter.print("=END_ELEMENT_LONG name:" + paramInt1 + "=<" + this.objects[paramInt1] + '>');
              j = getIntN(i + 3);
              paramInt1 = j;
              if (j < 0) {
                paramInt1 = j + i;
              }
              paramPrintWriter.print(" begin:" + paramInt1);
              j = getIntN(i + 5);
              paramInt1 = j;
              if (j < 0) {
                paramInt1 = j + i;
              }
              paramPrintWriter.print(" parent:" + paramInt1);
              paramInt1 = 6;
              break;
            case 61705: 
              paramInt1 = getIntN(i + 1);
              paramPrintWriter.print("=BEGIN_ATTRIBUTE name:" + paramInt1 + "=" + this.objects[paramInt1]);
              j = getIntN(i + 3);
              if (j < 0) {}
              for (paramInt1 = this.data.length;; paramInt1 = i)
              {
                paramPrintWriter.print(" end:" + (j + paramInt1));
                paramInt1 = 4;
                break;
              }
            case 61706: 
              label1272:
              label1369:
              paramPrintWriter.print("=END_ATTRIBUTE");
              paramInt1 = j;
            }
          }
        }
      }
      paramPrintWriter.print("=POSITION_PAIR_FOLLOWS seq:");
      paramInt1 = getIntN(i + 1);
      paramPrintWriter.print(paramInt1);
      paramPrintWriter.print('=');
      Object localObject2 = this.objects[paramInt1];
      if (localObject2 == null)
      {
        localObject1 = null;
        label1697:
        paramPrintWriter.print((String)localObject1);
        paramPrintWriter.print('@');
        if (localObject2 != null) {
          break label1757;
        }
        paramPrintWriter.print("null");
      }
      for (;;)
      {
        paramPrintWriter.print(" ipos:");
        paramPrintWriter.print(getIntN(i + 3));
        paramInt1 = 4;
        break;
        localObject1 = localObject2.getClass().getName();
        break label1697;
        label1757:
        paramPrintWriter.print(Integer.toHexString(System.identityHashCode(localObject2)));
      }
    }
  }
  
  public void endAttribute()
  {
    if (this.attrStart <= 0) {
      return;
    }
    if (this.data[this.gapEnd] != 61706) {
      throw new Error("unexpected endAttribute");
    }
    this.gapEnd += 1;
    setIntN(this.attrStart + 2, this.gapStart - this.attrStart + 1);
    this.attrStart = 0;
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = 61706;
  }
  
  public void endDocument()
  {
    if ((this.data[this.gapEnd] != 61713) || (this.docStart <= 0) || (this.data[this.currentParent] != 61712)) {
      throw new Error("unexpected endDocument");
    }
    this.gapEnd += 1;
    setIntN(this.docStart, this.gapStart - this.docStart + 1);
    this.docStart = 0;
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = 61713;
    i = getIntN(this.currentParent + 3);
    if (i >= -1) {}
    for (;;)
    {
      this.currentParent = i;
      return;
      i += this.currentParent;
    }
  }
  
  public void endElement()
  {
    if (this.data[this.gapEnd] != 61708) {
      throw new Error("unexpected endElement");
    }
    int i = getIntN(this.gapEnd + 1);
    int k = getIntN(this.gapEnd + 3);
    int j = getIntN(this.gapEnd + 5);
    this.currentParent = j;
    this.gapEnd += 7;
    int m = this.gapStart - k;
    int n = k - j;
    if ((i < 4095) && (m < 65536) && (n < 65536))
    {
      this.data[k] = ((char)(0xA000 | i));
      this.data[(k + 1)] = ((char)m);
      this.data[(k + 2)] = ((char)n);
      this.data[this.gapStart] = 61707;
      this.data[(this.gapStart + 1)] = ((char)m);
      this.gapStart += 2;
      return;
    }
    this.data[k] = 61704;
    setIntN(k + 1, m);
    this.data[this.gapStart] = 61708;
    setIntN(this.gapStart + 1, i);
    setIntN(this.gapStart + 3, -m);
    if (j < this.gapStart)
    {
      i = j;
      if (k > this.gapStart) {}
    }
    else
    {
      i = j - this.gapStart;
    }
    setIntN(this.gapStart + 5, i);
    this.gapStart += 7;
  }
  
  public void endEntity()
  {
    if ((this.gapEnd + 1 != this.data.length) || (this.data[this.gapEnd] != 61715)) {
      return;
    }
    if (this.data[this.currentParent] != 61714) {
      throw new Error("unexpected endEntity");
    }
    this.gapEnd += 1;
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = 61715;
    i = getIntN(this.currentParent + 3);
    if (i >= -1) {}
    for (;;)
    {
      this.currentParent = i;
      return;
      i += this.currentParent;
    }
  }
  
  public void ensureSpace(int paramInt)
  {
    int i = this.gapEnd - this.gapStart;
    if (paramInt > i)
    {
      int k = this.data.length;
      i = k - i + paramInt;
      int j = k * 2;
      paramInt = j;
      if (j < i) {
        paramInt = i;
      }
      char[] arrayOfChar = new char[paramInt];
      if (this.gapStart > 0) {
        System.arraycopy(this.data, 0, arrayOfChar, 0, this.gapStart);
      }
      i = k - this.gapEnd;
      if (i > 0) {
        System.arraycopy(this.data, this.gapEnd, arrayOfChar, paramInt - i, i);
      }
      this.gapEnd = (paramInt - i);
      this.data = arrayOfChar;
    }
  }
  
  public int find(Object paramObject)
  {
    if (this.oindex == this.objects.length) {
      resizeObjects();
    }
    this.objects[this.oindex] = paramObject;
    int i = this.oindex;
    this.oindex = (i + 1);
    return i;
  }
  
  public int firstAttributePos(int paramInt)
  {
    paramInt = gotoAttributesStart(posToDataIndex(paramInt));
    if (paramInt < 0) {
      return 0;
    }
    return paramInt << 1;
  }
  
  public int firstChildPos(int paramInt)
  {
    paramInt = gotoChildrenStart(posToDataIndex(paramInt));
    if (paramInt < 0) {
      return 0;
    }
    return paramInt << 1;
  }
  
  public Object get(int paramInt)
  {
    int j = 0;
    int i = paramInt;
    paramInt = j;
    do
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      j = nextPos(paramInt);
      paramInt = j;
    } while (j != 0);
    throw new IndexOutOfBoundsException();
    return getPosNext(paramInt);
  }
  
  public int getAttributeCount(int paramInt)
  {
    int i = 0;
    for (paramInt = firstAttributePos(paramInt); (paramInt != 0) && (getNextKind(paramInt) == 35); paramInt = nextPos(paramInt)) {
      i += 1;
    }
    return i;
  }
  
  protected int getIndexDifference(int paramInt1, int paramInt2)
  {
    int j = posToDataIndex(paramInt2);
    int k = posToDataIndex(paramInt1);
    paramInt2 = 0;
    paramInt1 = j;
    int i = k;
    if (j > k)
    {
      paramInt2 = 1;
      i = j;
      paramInt1 = k;
    }
    k = 0;
    j = paramInt1;
    paramInt1 = k;
    while (j < i)
    {
      j = nextDataIndex(j);
      paramInt1 += 1;
    }
    i = paramInt1;
    if (paramInt2 != 0) {
      i = -paramInt1;
    }
    return i;
  }
  
  protected final int getIntN(int paramInt)
  {
    return this.data[paramInt] << '\020' | this.data[(paramInt + 1)] & 0xFFFF;
  }
  
  protected final long getLongN(int paramInt)
  {
    char[] arrayOfChar = this.data;
    return (arrayOfChar[paramInt] & 0xFFFF) << 48 | (arrayOfChar[(paramInt + 1)] & 0xFFFF) << 32 | (arrayOfChar[(paramInt + 2)] & 0xFFFF) << 16 | arrayOfChar[(paramInt + 3)] & 0xFFFF;
  }
  
  public int getNextKind(int paramInt)
  {
    return getNextKindI(posToDataIndex(paramInt));
  }
  
  public int getNextKindI(int paramInt)
  {
    if (paramInt == this.data.length) {
      return 0;
    }
    int i = this.data[paramInt];
    if (i <= 40959) {
      return 29;
    }
    if ((i >= 57344) && (i <= 61439)) {
      return 32;
    }
    if ((i >= 40960) && (i <= 45055)) {
      return 33;
    }
    if ((0xFF00 & i) == 61440) {
      return 28;
    }
    if ((i >= 45056) && (i <= 57343)) {
      return 22;
    }
    switch (i)
    {
    case 61706: 
    case 61707: 
    case 61708: 
    case 61713: 
    case 61715: 
    case 61703: 
    case 61709: 
    case 61710: 
    case 61711: 
    case 61718: 
    default: 
      return 32;
    case 61696: 
    case 61697: 
      return 27;
    case 61698: 
      return 22;
    case 61699: 
      return 24;
    case 61700: 
      return 25;
    case 61701: 
      return 26;
    case 61702: 
    case 61712: 
      return 34;
    case 61714: 
      return getNextKind(paramInt + 5 << 1);
    case 61704: 
      return 33;
    case 61705: 
      return 35;
    case 61717: 
      return 31;
    case 61719: 
      return 36;
    }
    return 37;
  }
  
  public String getNextTypeName(int paramInt)
  {
    Object localObject = getNextTypeObject(paramInt);
    if (localObject == null) {
      return null;
    }
    return localObject.toString();
  }
  
  public Object getNextTypeObject(int paramInt)
  {
    paramInt = posToDataIndex(paramInt);
    if (paramInt == this.data.length) {}
    label15:
    label65:
    label134:
    for (;;)
    {
      return null;
      int i = this.data[paramInt];
      if (i != 61714)
      {
        if ((i < 40960) || (i > 45055)) {
          break label65;
        }
        paramInt = i - 40960;
      }
      for (;;)
      {
        if (paramInt < 0) {
          break label134;
        }
        return this.objects[paramInt];
        paramInt += 5;
        break;
        if (i == 61704)
        {
          i = getIntN(paramInt + 1);
          if (i < 0) {
            paramInt = this.data.length;
          }
          paramInt = getIntN(i + paramInt + 1);
        }
        else if (i == 61705)
        {
          paramInt = getIntN(paramInt + 1);
        }
        else
        {
          if (i != 61716) {
            break label15;
          }
          paramInt = getIntN(paramInt + 1);
        }
      }
    }
  }
  
  public Object getPosNext(int paramInt)
  {
    paramInt = posToDataIndex(paramInt);
    if (paramInt == this.data.length) {
      return Sequence.eofValue;
    }
    int i = this.data[paramInt];
    if (i <= 40959) {
      return Convert.toObject(i);
    }
    if ((i >= 57344) && (i <= 61439)) {
      return this.objects[(i - 57344)];
    }
    if ((i >= 40960) && (i <= 45055)) {
      return copyToList(paramInt, this.data[(paramInt + 1)] + paramInt + 2);
    }
    if ((i >= 45056) && (i <= 57343)) {
      return Convert.toObject(i - 49152);
    }
    int k;
    int j;
    switch (i)
    {
    case 61703: 
    case 61714: 
    case 61715: 
    case 61716: 
    case 61717: 
    default: 
      throw unsupported("getPosNext, code=" + Integer.toHexString(i));
    case 61712: 
      k = getIntN(paramInt + 1);
      if (k < 0) {}
      for (j = this.data.length;; j = paramInt) {
        return copyToList(paramInt, k + j + 1);
      }
    case 61696: 
    case 61697: 
      if (i != 61696) {}
      for (boolean bool = true;; bool = false) {
        return Convert.toObject(bool);
      }
    case 61698: 
      return Convert.toObject(getIntN(paramInt + 1));
    case 61699: 
      return Convert.toObject(getLongN(paramInt + 1));
    case 61700: 
      return Convert.toObject(Float.intBitsToFloat(getIntN(paramInt + 1)));
    case 61701: 
      return Convert.toObject(Double.longBitsToDouble(getLongN(paramInt + 1)));
    case 61702: 
      return Convert.toObject(this.data[(paramInt + 1)]);
    case 61705: 
      k = getIntN(paramInt + 3);
      if (k < 0) {}
      for (j = this.data.length;; j = paramInt) {
        return copyToList(paramInt, k + j + 1);
      }
    case 61704: 
      k = getIntN(paramInt + 1);
      if (k < 0) {}
      for (j = this.data.length;; j = paramInt) {
        return copyToList(paramInt, k + j + 7);
      }
    case 61706: 
    case 61707: 
    case 61708: 
    case 61713: 
      return Sequence.eofValue;
    case 61709: 
    case 61710: 
      return this.objects[getIntN(paramInt + 1)];
    case 61718: 
      return "";
    }
    return ((AbstractSequence)this.objects[getIntN(paramInt + 1)]).getIteratorAtPos(getIntN(paramInt + 3));
  }
  
  public int getPosNextInt(int paramInt)
  {
    int i = posToDataIndex(paramInt);
    if (i < this.data.length)
    {
      int j = this.data[i];
      if ((j >= 45056) && (j <= 57343)) {
        return j - 49152;
      }
      if (j == 61698) {
        return getIntN(i + 1);
      }
    }
    return ((Number)getPosNext(paramInt)).intValue();
  }
  
  public Object getPosPrevious(int paramInt)
  {
    if (((paramInt & 0x1) != 0) && (paramInt != -1)) {
      return getPosNext(paramInt - 3);
    }
    return super.getPosPrevious(paramInt);
  }
  
  public int gotoAttributesStart(int paramInt)
  {
    int i = paramInt;
    if (paramInt >= this.gapStart) {
      i = paramInt + (this.gapEnd - this.gapStart);
    }
    if (i == this.data.length) {}
    do
    {
      return -1;
      paramInt = this.data[i];
    } while (((paramInt < 40960) || (paramInt > 45055)) && (paramInt != 61704));
    return i + 3;
  }
  
  public boolean gotoAttributesStart(TreePosition paramTreePosition)
  {
    int i = gotoAttributesStart(paramTreePosition.ipos >> 1);
    if (i < 0) {
      return false;
    }
    paramTreePosition.push(this, i << 1);
    return true;
  }
  
  public final int gotoChildrenStart(int paramInt)
  {
    if (paramInt == this.data.length) {
      return -1;
    }
    int i = this.data[paramInt];
    if (((i >= 40960) && (i <= 45055)) || (i == 61704)) {
      paramInt += 3;
    }
    for (;;)
    {
      i = paramInt;
      if (paramInt >= this.gapStart) {
        i = paramInt + (this.gapEnd - this.gapStart);
      }
      paramInt = this.data[i];
      if (paramInt == 61705)
      {
        paramInt = getIntN(i + 3);
        if (paramInt < 0) {
          i = this.data.length;
        }
        paramInt = i + paramInt;
        continue;
        if ((i != 61712) && (i != 61714)) {
          break;
        }
        paramInt += 5;
        continue;
      }
      if ((paramInt == 61706) || (paramInt == 61718))
      {
        paramInt = i + 1;
      }
      else
      {
        if (paramInt != 61720) {
          return i;
        }
        paramInt = i + 3;
      }
    }
    return i;
  }
  
  public boolean hasNext(int paramInt)
  {
    paramInt = posToDataIndex(paramInt);
    if (paramInt == this.data.length) {}
    do
    {
      return false;
      paramInt = this.data[paramInt];
    } while ((paramInt == 61706) || (paramInt == 61707) || (paramInt == 61708) || (paramInt == 61713));
    return true;
  }
  
  public int hashCode()
  {
    return System.identityHashCode(this);
  }
  
  public boolean ignoring()
  {
    return false;
  }
  
  public boolean isEmpty()
  {
    boolean bool = false;
    if (this.gapStart == 0) {}
    for (int i = this.gapEnd;; i = 0)
    {
      if (i == this.data.length) {
        bool = true;
      }
      return bool;
    }
  }
  
  public final int nextDataIndex(int paramInt)
  {
    int i = paramInt;
    if (paramInt == this.gapStart) {
      i = this.gapEnd;
    }
    if (i == this.data.length) {
      return -1;
    }
    char[] arrayOfChar = this.data;
    paramInt = i + 1;
    i = arrayOfChar[i];
    if ((i <= 40959) || ((i >= 57344) && (i <= 61439)) || ((i >= 45056) && (i <= 57343))) {
      return paramInt;
    }
    if ((i >= 40960) && (i <= 45055)) {
      return this.data[paramInt] + paramInt + 1;
    }
    switch (i)
    {
    case 61703: 
    default: 
      throw new Error("unknown code:" + Integer.toHexString(i));
    case 61712: 
      i = getIntN(paramInt);
      if (i < 0) {
        paramInt = this.data.length;
      }
      for (;;)
      {
        return i + paramInt + 1;
        paramInt -= 1;
      }
    case 61714: 
      paramInt += 4;
      for (;;)
      {
        i = paramInt;
        if (paramInt == this.gapStart) {
          i = this.gapEnd;
        }
        if (i == this.data.length) {
          return -1;
        }
        if (this.data[i] == 61715) {
          return i + 1;
        }
        paramInt = nextDataIndex(i);
      }
    case 61696: 
    case 61697: 
    case 61718: 
      return paramInt;
    case 61702: 
      return paramInt + 1;
    case 61698: 
    case 61700: 
    case 61709: 
    case 61710: 
      return paramInt + 2;
    case 61711: 
      return paramInt + 4;
    case 61706: 
    case 61707: 
    case 61708: 
    case 61713: 
    case 61715: 
      return -1;
    case 61704: 
      i = getIntN(paramInt);
      if (i < 0) {
        paramInt = this.data.length;
      }
      for (;;)
      {
        return i + paramInt + 7;
        paramInt -= 1;
      }
    case 61705: 
      i = getIntN(paramInt + 2);
      if (i < 0) {
        paramInt = this.data.length;
      }
      for (;;)
      {
        return i + paramInt + 1;
        paramInt -= 1;
      }
    case 61699: 
    case 61701: 
      return paramInt + 4;
    case 61716: 
      paramInt += 2;
    }
    for (;;)
    {
      return paramInt + 2 + getIntN(paramInt);
    }
  }
  
  public int nextMatching(int paramInt1, ItemPredicate paramItemPredicate, int paramInt2, boolean paramBoolean)
  {
    int m = posToDataIndex(paramInt1);
    int n = posToDataIndex(paramInt2);
    paramInt2 = m;
    paramInt1 = paramInt2;
    if ((paramItemPredicate instanceof NodePredicate)) {
      paramInt1 = nextNodeIndex(paramInt2, n);
    }
    int j;
    int k;
    if ((paramItemPredicate instanceof ElementPredicate))
    {
      j = 1;
      k = 0;
    }
    int i;
    for (;;)
    {
      i = paramInt1;
      if (paramInt1 == this.gapStart) {
        i = this.gapEnd;
      }
      if ((i >= n) && (n != -1))
      {
        return 0;
        if ((paramItemPredicate instanceof AttributePredicate))
        {
          j = 0;
          k = 0;
        }
        else
        {
          j = 1;
          k = 1;
        }
      }
      else
      {
        paramInt1 = this.data[i];
        if ((paramInt1 > 40959) && ((paramInt1 < 57344) || (paramInt1 > 61439)) && ((paramInt1 < 45056) || (paramInt1 > 57343))) {
          break;
        }
        if ((k != 0) && (paramItemPredicate.isInstancePos(this, i << 1)))
        {
          paramInt1 = i;
          if (i >= this.gapEnd) {
            paramInt1 = i - (this.gapEnd - this.gapStart);
          }
          return paramInt1 << 1;
        }
        paramInt1 = i + 1;
      }
    }
    switch (paramInt1)
    {
    case 61703: 
    default: 
      if ((paramInt1 < 40960) || (paramInt1 > 45055)) {
        break label742;
      }
      if (!paramBoolean) {
        break;
      }
    }
    for (paramInt2 = i + 3;; paramInt2 = this.data[(i + 1)] + i + 2)
    {
      paramInt1 = paramInt2;
      if (j == 0) {
        break;
      }
      for (;;)
      {
        label341:
        paramInt1 = paramInt2;
        if (i <= m) {
          break;
        }
        paramInt1 = paramInt2;
        if (!paramItemPredicate.isInstancePos(this, i << 1)) {
          break;
        }
        paramInt1 = i;
        if (i >= this.gapEnd) {
          paramInt1 = i - (this.gapEnd - this.gapStart);
        }
        return paramInt1 << 1;
        paramInt1 = i + 3;
        break;
        paramInt2 = i + 5;
        paramInt1 = paramInt2;
        if (1 == 0) {
          break;
        }
        continue;
        paramInt1 = i + 5;
        break;
        paramInt2 = i + 3;
        paramInt1 = paramInt2;
        if (k == 0) {
          break;
        }
        continue;
        paramInt1 = i + 2;
        break;
        if (!paramBoolean) {
          return 0;
        }
        paramInt1 = i + 2;
        break;
        paramInt2 = i + 5;
        paramInt1 = paramInt2;
        if (k == 0) {
          break;
        }
        continue;
        if (!paramBoolean) {
          return 0;
        }
        paramInt1 = i + 7;
        break;
        if (!paramBoolean) {
          return 0;
        }
        paramInt1 = i + 1;
        break;
        if (1 != 0)
        {
          paramInt2 = getIntN(i + 3);
          if (paramInt2 < 0) {
            paramInt1 = this.data.length;
          }
        }
        label532:
        for (paramInt2 = paramInt2 + 1 + paramInt1;; paramInt2 = i + 5)
        {
          paramInt1 = paramInt2;
          if (0 == 0) {
            break;
          }
          break label341;
          paramInt1 = i;
          break label532;
        }
        paramInt2 = i + 1;
        paramInt1 = paramInt2;
        if (k == 0) {
          break;
        }
        continue;
        paramInt1 = i + 1;
        break;
        paramInt2 = i + 5;
        paramInt1 = paramInt2;
        if (k == 0) {
          break;
        }
        continue;
        paramInt2 = i + 5 + getIntN(i + 3);
        paramInt1 = paramInt2;
        if (1 == 0) {
          break;
        }
        continue;
        paramInt2 = i + 3 + getIntN(i + 1);
        paramInt1 = paramInt2;
        if (1 == 0) {
          break;
        }
        continue;
        paramInt2 = i + 3 + getIntN(i + 1);
        paramInt1 = paramInt2;
        if (k == 0) {
          break;
        }
        continue;
        if (!paramBoolean) {
          break label689;
        }
        paramInt2 = i + 3;
        paramInt1 = paramInt2;
        if (j == 0) {
          break;
        }
      }
      label689:
      paramInt2 = getIntN(i + 1);
      if (paramInt2 < 0) {}
      for (paramInt1 = this.data.length;; paramInt1 = i)
      {
        paramInt2 = paramInt1 + paramInt2 + 7;
        break;
      }
    }
    label742:
    throw new Error("unknown code:" + paramInt1);
  }
  
  public final int nextNodeIndex(int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    int j = paramInt2;
    if ((0x80000000 | paramInt2) == -1)
    {
      j = this.data.length;
      i = paramInt1;
    }
    for (;;)
    {
      paramInt1 = i;
      if (i == this.gapStart) {
        paramInt1 = this.gapEnd;
      }
      if (paramInt1 >= j) {}
      do
      {
        return paramInt1;
        paramInt2 = this.data[paramInt1];
        if ((paramInt2 <= 40959) || ((paramInt2 >= 57344) && (paramInt2 <= 61439)) || ((paramInt2 >= 45056) && (paramInt2 <= 57343)) || ((0xFF00 & paramInt2) == 61440))
        {
          i = paramInt1 + 1;
          break;
        }
      } while ((paramInt2 >= 40960) && (paramInt2 <= 45055));
      switch (paramInt2)
      {
      case 61704: 
      case 61705: 
      case 61706: 
      case 61707: 
      case 61708: 
      case 61712: 
      case 61713: 
      case 61715: 
      case 61716: 
      case 61719: 
      case 61709: 
      case 61710: 
      case 61711: 
      case 61717: 
      default: 
        i = nextDataIndex(paramInt1);
        break;
      case 61720: 
        i = paramInt1 + 3;
        break;
      case 61718: 
        i = paramInt1 + 1;
        break;
      case 61714: 
        i = paramInt1 + 5;
      }
    }
  }
  
  public int nextPos(int paramInt)
  {
    int i = posToDataIndex(paramInt);
    if (i == this.data.length) {
      return 0;
    }
    paramInt = i;
    if (i >= this.gapEnd) {
      paramInt = i - (this.gapEnd - this.gapStart);
    }
    return (paramInt << 1) + 3;
  }
  
  public int parentOrEntityI(int paramInt)
  {
    if (paramInt == this.data.length) {}
    do
    {
      return -1;
      j = this.data[paramInt];
      if ((j == 61712) || (j == 61714))
      {
        i = getIntN(paramInt + 3);
        if (i >= -1) {
          return i;
        }
        return paramInt + i;
      }
      if ((j < 40960) || (j > 45055)) {
        break;
      }
      i = this.data[(paramInt + 2)];
    } while (i == 0);
    return paramInt - i;
    int i = paramInt;
    if (j == 61704)
    {
      i = getIntN(paramInt + 1);
      if (i < 0) {
        paramInt = this.data.length;
      }
      for (;;)
      {
        j = i + paramInt;
        i = getIntN(j + 5);
        if (i == 0) {
          break;
        }
        paramInt = i;
        if (i < 0) {
          paramInt = i + j;
        }
        return paramInt;
      }
      i = paramInt + 1;
    }
    do
    {
      paramInt = i;
      if (i == this.gapStart) {
        paramInt = this.gapEnd;
      }
      if (paramInt == this.data.length) {
        break;
      }
      switch (this.data[paramInt])
      {
      case '': 
      case '': 
      case '': 
      case '': 
      case '': 
      case '': 
      default: 
        paramInt = nextDataIndex(paramInt);
        i = paramInt;
      }
    } while (paramInt >= 0);
    return -1;
    return paramInt - this.data[(paramInt + 1)];
    int j = getIntN(paramInt + 3);
    i = j;
    if (j < 0) {
      i = j + paramInt;
    }
    return i;
  }
  
  public int parentOrEntityPos(int paramInt)
  {
    paramInt = parentOrEntityI(posToDataIndex(paramInt));
    if (paramInt < 0) {
      return -1;
    }
    return paramInt << 1;
  }
  
  public int parentPos(int paramInt)
  {
    paramInt = posToDataIndex(paramInt);
    int i;
    do
    {
      i = parentOrEntityI(paramInt);
      if (i == -1) {
        return -1;
      }
      paramInt = i;
    } while (this.data[i] == 61714);
    return i << 1;
  }
  
  public final int posToDataIndex(int paramInt)
  {
    int i;
    if (paramInt == -1) {
      i = this.data.length;
    }
    do
    {
      int j;
      do
      {
        return i;
        j = paramInt >>> 1;
        i = j;
        if ((paramInt & 0x1) != 0) {
          i = j - 1;
        }
        j = i;
        if (i >= this.gapStart) {
          j = i + (this.gapEnd - this.gapStart);
        }
        i = j;
      } while ((paramInt & 0x1) == 0);
      paramInt = nextDataIndex(j);
      if (paramInt < 0) {
        return this.data.length;
      }
      i = paramInt;
    } while (paramInt != this.gapStart);
    return paramInt + (this.gapEnd - this.gapStart);
  }
  
  public final void resizeObjects()
  {
    Object[] arrayOfObject;
    if (this.objects == null) {
      arrayOfObject = new Object[100];
    }
    for (;;)
    {
      this.objects = arrayOfObject;
      return;
      int i = this.objects.length;
      arrayOfObject = new Object[i * 2];
      System.arraycopy(this.objects, 0, arrayOfObject, 0, i);
    }
  }
  
  public void setAttributeName(int paramInt1, int paramInt2)
  {
    setIntN(paramInt1 + 1, paramInt2);
  }
  
  public void setElementName(int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (this.data[paramInt1] == 61704)
    {
      i = getIntN(paramInt1 + 1);
      if (i < 0) {
        paramInt1 = this.data.length;
      }
      i = paramInt1 + i;
    }
    if (i < this.gapEnd) {
      throw new Error("setElementName before gapEnd");
    }
    setIntN(i + 1, paramInt2);
  }
  
  public final void setIntN(int paramInt1, int paramInt2)
  {
    this.data[paramInt1] = ((char)(paramInt2 >> 16));
    this.data[(paramInt1 + 1)] = ((char)paramInt2);
  }
  
  public int size()
  {
    int i = 0;
    int j = 0;
    for (;;)
    {
      j = nextPos(j);
      if (j == 0) {
        return i;
      }
      i += 1;
    }
  }
  
  public void startAttribute(int paramInt)
  {
    ensureSpace(6);
    this.gapEnd -= 1;
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = 61705;
    if (this.attrStart != 0) {
      throw new Error("nested attribute");
    }
    this.attrStart = this.gapStart;
    setIntN(this.gapStart, paramInt);
    setIntN(this.gapStart + 2, this.gapEnd - this.data.length);
    this.gapStart += 4;
    this.data[this.gapEnd] = 61706;
  }
  
  public void startAttribute(Object paramObject)
  {
    startAttribute(find(paramObject));
  }
  
  public void startDocument()
  {
    int i = -1;
    ensureSpace(6);
    this.gapEnd -= 1;
    int j = this.gapStart;
    this.data[j] = 61712;
    if (this.docStart != 0) {
      throw new Error("nested document");
    }
    this.docStart = (j + 1);
    setIntN(j + 1, this.gapEnd - this.data.length);
    if (this.currentParent == -1) {}
    for (;;)
    {
      setIntN(j + 3, i);
      this.currentParent = j;
      this.gapStart = (j + 5);
      this.currentParent = j;
      this.data[this.gapEnd] = 61713;
      return;
      i = this.currentParent - j;
    }
  }
  
  public void startElement(int paramInt)
  {
    ensureSpace(10);
    this.gapEnd -= 7;
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = 61704;
    setIntN(this.gapStart, this.gapEnd - this.data.length);
    this.gapStart += 2;
    this.data[this.gapEnd] = 61708;
    setIntN(this.gapEnd + 1, paramInt);
    setIntN(this.gapEnd + 3, this.gapStart - 3);
    setIntN(this.gapEnd + 5, this.currentParent);
    this.currentParent = (this.gapStart - 3);
  }
  
  public void startElement(Object paramObject)
  {
    startElement(find(paramObject));
  }
  
  public void statistics()
  {
    PrintWriter localPrintWriter = new PrintWriter(System.out);
    statistics(localPrintWriter);
    localPrintWriter.flush();
  }
  
  public void statistics(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.print("data array length: ");
    paramPrintWriter.println(this.data.length);
    paramPrintWriter.print("data array gap: ");
    paramPrintWriter.println(this.gapEnd - this.gapStart);
    paramPrintWriter.print("object array length: ");
    paramPrintWriter.println(this.objects.length);
  }
  
  public int stringValue(int paramInt, StringBuffer paramStringBuffer)
  {
    int i = nextNodeIndex(paramInt, Integer.MAX_VALUE);
    if (i > paramInt)
    {
      stringValue(paramInt, i, paramStringBuffer);
      return paramInt;
    }
    return stringValue(false, paramInt, paramStringBuffer);
  }
  
  public int stringValue(boolean paramBoolean, int paramInt, StringBuffer paramStringBuffer)
  {
    Object localObject = null;
    int m = 0;
    int k = 0;
    int j = paramInt;
    if (paramInt >= this.gapStart) {
      j = paramInt + (this.gapEnd - this.gapStart);
    }
    if (j == this.data.length) {
      return -1;
    }
    int i = this.data[j];
    j += 1;
    if (i <= 40959)
    {
      paramStringBuffer.append(i);
      return j;
    }
    if ((i >= 57344) && (i <= 61439))
    {
      if (0 != 0) {
        paramStringBuffer.append(' ');
      }
      localObject = this.objects[(i - 57344)];
      paramInt = k;
    }
    for (;;)
    {
      if (localObject != null) {
        paramStringBuffer.append(localObject);
      }
      if (paramInt > 0) {
        do
        {
          k = stringValue(true, paramInt, paramStringBuffer);
          paramInt = k;
        } while (k >= 0);
      }
      return j;
      if ((i >= 40960) && (i <= 45055))
      {
        paramInt = j + 2;
        j = this.data[j] + j + 1;
      }
      else
      {
        if ((0xFF00 & i) == 61440)
        {
          paramStringBuffer.append(i & 0xFF);
          return j;
        }
        if ((i >= 45056) && (i <= 57343))
        {
          paramStringBuffer.append(i - 49152);
          return j;
        }
        paramInt = j;
        switch (i)
        {
        case 61703: 
        case 61709: 
        case 61710: 
        default: 
          throw new Error("unimplemented: " + Integer.toHexString(i) + " at:" + j);
        case 61720: 
          return j + 2;
        case 61716: 
          paramInt = j + 2;
        case 61717: 
        case 61719: 
          j = getIntN(paramInt);
          paramInt += 2;
          if ((!paramBoolean) || (i == 61717)) {
            paramStringBuffer.append(this.data, paramInt, j);
          }
          return paramInt + j;
        case 61696: 
        case 61697: 
          if (0 != 0) {
            paramStringBuffer.append(' ');
          }
          if (i != 61696) {}
          for (paramBoolean = true;; paramBoolean = false)
          {
            paramStringBuffer.append(paramBoolean);
            return j;
          }
        case 61698: 
          if (0 != 0) {
            paramStringBuffer.append(' ');
          }
          paramStringBuffer.append(getIntN(j));
          return j + 2;
        case 61699: 
          if (0 != 0) {
            paramStringBuffer.append(' ');
          }
          paramStringBuffer.append(getLongN(j));
          return j + 4;
        case 61700: 
          if (0 != 0) {
            paramStringBuffer.append(' ');
          }
          paramStringBuffer.append(Float.intBitsToFloat(getIntN(j)));
          return j + 2;
        case 61701: 
          if (0 != 0) {
            paramStringBuffer.append(' ');
          }
          paramStringBuffer.append(Double.longBitsToDouble(getLongN(j)));
          return j + 4;
        case 61702: 
          paramStringBuffer.append(this.data[j]);
          return j + 1;
        case 61712: 
        case 61714: 
          paramInt = j + 4;
          j = nextDataIndex(j - 1);
          break;
        case 61704: 
          k = j + 2;
          m = getIntN(j);
          if (m < 0) {}
          for (paramInt = this.data.length;; paramInt = j - 1)
          {
            j = m + paramInt + 7;
            paramInt = k;
            break;
          }
        case 61718: 
          paramInt = k;
          break;
        case 61706: 
        case 61707: 
        case 61708: 
        case 61713: 
        case 61715: 
          return -1;
        case 61705: 
          paramInt = m;
          if (!paramBoolean) {
            paramInt = j + 4;
          }
          k = getIntN(j + 2);
          if (k < 0) {
            j = this.data.length + 1;
          }
          j += k;
          break;
        case 61711: 
          AbstractSequence localAbstractSequence = (AbstractSequence)this.objects[getIntN(j)];
          paramInt = getIntN(j + 2);
          ((TreeList)localAbstractSequence).stringValue(paramBoolean, paramInt >> 1, paramStringBuffer);
          j += 4;
          paramInt = k;
        }
      }
    }
  }
  
  public void stringValue(int paramInt1, int paramInt2, StringBuffer paramStringBuffer)
  {
    while ((paramInt1 < paramInt2) && (paramInt1 >= 0)) {
      paramInt1 = stringValue(false, paramInt1, paramStringBuffer);
    }
  }
  
  public void toString(String paramString, StringBuffer paramStringBuffer)
  {
    int i = 0;
    int n = this.gapStart;
    int k = 0;
    int j = 0;
    int m = n;
    int i1 = i;
    if (i >= n) {
      if (i == this.gapStart)
      {
        i = this.gapEnd;
        n = this.data.length;
        m = n;
        i1 = i;
        if (i != n) {}
      }
      else
      {
        return;
      }
    }
    char[] arrayOfChar = this.data;
    i = i1 + 1;
    i1 = arrayOfChar[i1];
    if (i1 <= 40959) {
      n = i - 1;
    }
    for (;;)
    {
      if (i >= m) {}
      for (;;)
      {
        k = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          k = 0;
        }
        paramStringBuffer.append(this.data, n, i - n);
        i1 = 0;
        j = k;
        n = m;
        k = i1;
        break;
        arrayOfChar = this.data;
        k = i + 1;
        if (arrayOfChar[i] <= 40959) {
          break label1727;
        }
        i = k - 1;
      }
      if ((i1 >= 57344) && (i1 <= 61439))
      {
        n = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          n = 0;
        }
        if (k != 0) {
          paramStringBuffer.append(paramString);
        }
        for (;;)
        {
          paramStringBuffer.append(this.objects[(i1 - 57344)]);
          j = n;
          n = m;
          break;
          k = 1;
        }
      }
      if ((i1 >= 40960) && (i1 <= 45055))
      {
        if (j != 0) {
          paramStringBuffer.append('>');
        }
        if (k != 0) {
          paramStringBuffer.append(paramString);
        }
        paramStringBuffer.append('<');
        paramStringBuffer.append(this.objects[(i1 - 40960)].toString());
        i += 2;
        k = 0;
        j = 1;
        n = m;
        break;
      }
      if ((i1 >= 45056) && (i1 <= 57343))
      {
        n = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          n = 0;
        }
        if (k != 0) {
          paramStringBuffer.append(paramString);
        }
        for (;;)
        {
          paramStringBuffer.append(i1 - 49152);
          j = n;
          n = m;
          break;
          k = 1;
        }
      }
      switch (i1)
      {
      case 61703: 
      default: 
        throw new Error("unknown code:" + i1);
      case 61712: 
      case 61714: 
        i += 4;
        n = m;
        break;
      case 61720: 
        i += 2;
        n = m;
        break;
      case 61719: 
        n = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          n = 0;
        }
        j = getIntN(i);
        i += 2;
        paramStringBuffer.append("<!--");
        paramStringBuffer.append(this.data, i, j);
        paramStringBuffer.append("-->");
        i += j;
        j = n;
        n = m;
        break;
      case 61717: 
        n = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          n = 0;
        }
        j = getIntN(i);
        i += 2;
        paramStringBuffer.append("<![CDATA[");
        paramStringBuffer.append(this.data, i, j);
        paramStringBuffer.append("]]>");
        i += j;
        j = n;
        n = m;
        break;
      case 61716: 
        n = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          n = 0;
        }
        paramStringBuffer.append("<?");
        j = getIntN(i);
        i += 2;
        paramStringBuffer.append(this.objects[j]);
        i1 = getIntN(i);
        j = i + 2;
        i = j;
        if (i1 > 0)
        {
          paramStringBuffer.append(' ');
          paramStringBuffer.append(this.data, j, i1);
          i = j + i1;
        }
        paramStringBuffer.append("?>");
        j = n;
        n = m;
        break;
      case 61713: 
      case 61715: 
        n = m;
        break;
      case 61696: 
      case 61697: 
        n = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          n = 0;
        }
        if (k != 0)
        {
          paramStringBuffer.append(paramString);
          if (i1 == 61696) {
            break label939;
          }
        }
        for (boolean bool = true;; bool = false)
        {
          paramStringBuffer.append(bool);
          j = n;
          n = m;
          break;
          k = 1;
          break label905;
        }
      case 61718: 
        n = m;
        break;
      case 61702: 
        k = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          k = 0;
        }
        paramStringBuffer.append(this.data, i, i1 + 1 - 61702);
        i1 = 0;
        i += 1;
        j = k;
        n = m;
        k = i1;
        break;
      case 61711: 
        n = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          n = 0;
        }
        if (k != 0) {
          paramStringBuffer.append(paramString);
        }
        for (;;)
        {
          paramStringBuffer.append(((AbstractSequence)this.objects[getIntN(i)]).getIteratorAtPos(getIntN(i + 2)));
          i += 4;
          j = n;
          n = m;
          break;
          k = 1;
        }
      case 61709: 
      case 61710: 
        n = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          n = 0;
        }
        if (k != 0) {
          paramStringBuffer.append(paramString);
        }
        for (;;)
        {
          paramStringBuffer.append(this.objects[getIntN(i)]);
          i += 2;
          j = n;
          n = m;
          break;
          k = 1;
        }
      case 61704: 
        i1 = getIntN(i);
        if (i1 >= 0)
        {
          n = i - 1;
          i += 2;
          n = getIntN(i1 + n + 1);
          if (j == 0) {
            break label1257;
          }
          paramStringBuffer.append('>');
        }
        for (;;)
        {
          paramStringBuffer.append('<');
          paramStringBuffer.append(this.objects[n]);
          k = 0;
          j = 1;
          n = m;
          break;
          n = this.data.length;
          break label1184;
          if (k != 0) {
            paramStringBuffer.append(paramString);
          }
        }
      case 61707: 
      case 61708: 
        if (i1 == 61707)
        {
          arrayOfChar = this.data;
          k = i + 1;
          i = arrayOfChar[i];
          n = this.data[(k - 2 - i)] - 40960;
          i = k;
          k = n;
          if (j == 0) {
            break label1364;
          }
          paramStringBuffer.append("/>");
        }
        for (;;)
        {
          j = 0;
          k = 1;
          n = m;
          break;
          k = getIntN(i);
          i += 6;
          break label1320;
          paramStringBuffer.append("</");
          paramStringBuffer.append(this.objects[k]);
          paramStringBuffer.append('>');
        }
      case 61705: 
        j = getIntN(i);
        paramStringBuffer.append(' ');
        paramStringBuffer.append(this.objects[j]);
        paramStringBuffer.append("=\"");
        j = 0;
        i += 4;
        n = m;
        break;
      case 61706: 
        paramStringBuffer.append('"');
        j = 1;
        k = 0;
        n = m;
        break;
      case 61698: 
        n = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          n = 0;
        }
        if (k != 0) {
          paramStringBuffer.append(paramString);
        }
        for (;;)
        {
          paramStringBuffer.append(getIntN(i));
          i += 2;
          j = n;
          n = m;
          break;
          k = 1;
        }
      case 61700: 
        n = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          n = 0;
        }
        if (k != 0) {
          paramStringBuffer.append(paramString);
        }
        for (;;)
        {
          paramStringBuffer.append(Float.intBitsToFloat(getIntN(i)));
          i += 2;
          j = n;
          n = m;
          break;
          k = 1;
        }
      case 61699: 
        n = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          n = 0;
        }
        if (k != 0) {
          paramStringBuffer.append(paramString);
        }
        for (;;)
        {
          paramStringBuffer.append(getLongN(i));
          i += 4;
          j = n;
          n = m;
          break;
          k = 1;
        }
      case 61701: 
        label905:
        label939:
        label1184:
        label1257:
        label1320:
        label1364:
        n = j;
        if (j != 0)
        {
          paramStringBuffer.append('>');
          n = 0;
        }
        if (k != 0) {
          paramStringBuffer.append(paramString);
        }
        for (;;)
        {
          paramStringBuffer.append(Double.longBitsToDouble(getLongN(i)));
          i += 4;
          j = n;
          n = m;
          break;
          k = 1;
        }
        label1727:
        i = k;
      }
    }
  }
  
  public void write(int paramInt)
  {
    ensureSpace(3);
    char[] arrayOfChar;
    int i;
    if (paramInt <= 40959)
    {
      arrayOfChar = this.data;
      i = this.gapStart;
      this.gapStart = (i + 1);
      arrayOfChar[i] = ((char)paramInt);
      return;
    }
    if (paramInt < 65536)
    {
      arrayOfChar = this.data;
      i = this.gapStart;
      this.gapStart = (i + 1);
      arrayOfChar[i] = 61702;
      arrayOfChar = this.data;
      i = this.gapStart;
      this.gapStart = (i + 1);
      arrayOfChar[i] = ((char)paramInt);
      return;
    }
    Char.print(paramInt, this);
  }
  
  public void write(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      writeJoiner();
    }
    ensureSpace(paramInt2);
    if (paramInt2 > 0)
    {
      int i = paramCharSequence.charAt(paramInt1);
      paramInt2 -= 1;
      if (i <= 40959)
      {
        char[] arrayOfChar = this.data;
        int j = this.gapStart;
        this.gapStart = (j + 1);
        arrayOfChar[j] = i;
      }
      for (;;)
      {
        paramInt1 += 1;
        break;
        write(i);
        ensureSpace(paramInt2);
      }
    }
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
    ensureSpace(paramInt2);
    if (paramInt2 > 0)
    {
      int i = paramArrayOfChar[paramInt1];
      paramInt2 -= 1;
      if (i <= 40959)
      {
        char[] arrayOfChar = this.data;
        int j = this.gapStart;
        this.gapStart = (j + 1);
        arrayOfChar[j] = i;
      }
      for (;;)
      {
        paramInt1 += 1;
        break;
        write(i);
        ensureSpace(paramInt2);
      }
    }
  }
  
  public void writeBoolean(boolean paramBoolean)
  {
    ensureSpace(1);
    char[] arrayOfChar = this.data;
    int j = this.gapStart;
    this.gapStart = (j + 1);
    if (paramBoolean) {}
    for (int i = 61697;; i = 61696)
    {
      arrayOfChar[j] = i;
      return;
    }
  }
  
  public void writeByte(int paramInt)
  {
    ensureSpace(1);
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = ((char)(61440 + (paramInt & 0xFF)));
  }
  
  public void writeCDATA(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    ensureSpace(paramInt2 + 3);
    int i = this.gapStart;
    char[] arrayOfChar = this.data;
    int j = i + 1;
    arrayOfChar[i] = 61717;
    setIntN(j, paramInt2);
    i = j + 2;
    System.arraycopy(paramArrayOfChar, paramInt1, this.data, i, paramInt2);
    this.gapStart = (i + paramInt2);
  }
  
  public void writeComment(String paramString, int paramInt1, int paramInt2)
  {
    ensureSpace(paramInt2 + 3);
    int i = this.gapStart;
    char[] arrayOfChar = this.data;
    int j = i + 1;
    arrayOfChar[i] = 61719;
    setIntN(j, paramInt2);
    i = j + 2;
    paramString.getChars(paramInt1, paramInt1 + paramInt2, this.data, i);
    this.gapStart = (i + paramInt2);
  }
  
  public void writeComment(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    ensureSpace(paramInt2 + 3);
    int i = this.gapStart;
    char[] arrayOfChar = this.data;
    int j = i + 1;
    arrayOfChar[i] = 61719;
    setIntN(j, paramInt2);
    i = j + 2;
    System.arraycopy(paramArrayOfChar, paramInt1, this.data, i, paramInt2);
    this.gapStart = (i + paramInt2);
  }
  
  public void writeDocumentUri(Object paramObject)
  {
    ensureSpace(3);
    int i = find(paramObject);
    paramObject = this.data;
    int j = this.gapStart;
    this.gapStart = (j + 1);
    paramObject[j] = 61720;
    setIntN(this.gapStart, i);
    this.gapStart += 2;
  }
  
  public void writeDouble(double paramDouble)
  {
    ensureSpace(5);
    long l = Double.doubleToLongBits(paramDouble);
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = 61701;
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = ((char)(int)(l >>> 48));
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = ((char)(int)(l >>> 32));
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = ((char)(int)(l >>> 16));
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = ((char)(int)l);
  }
  
  public void writeFloat(float paramFloat)
  {
    ensureSpace(3);
    int i = Float.floatToIntBits(paramFloat);
    char[] arrayOfChar = this.data;
    int j = this.gapStart;
    this.gapStart = (j + 1);
    arrayOfChar[j] = 61700;
    arrayOfChar = this.data;
    j = this.gapStart;
    this.gapStart = (j + 1);
    arrayOfChar[j] = ((char)(i >>> 16));
    arrayOfChar = this.data;
    j = this.gapStart;
    this.gapStart = (j + 1);
    arrayOfChar[j] = ((char)i);
  }
  
  public void writeInt(int paramInt)
  {
    ensureSpace(3);
    if ((paramInt >= 61440) && (paramInt <= 8191))
    {
      arrayOfChar = this.data;
      i = this.gapStart;
      this.gapStart = (i + 1);
      arrayOfChar[i] = ((char)(49152 + paramInt));
      return;
    }
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = 61698;
    setIntN(this.gapStart, paramInt);
    this.gapStart += 2;
  }
  
  public void writeJoiner()
  {
    ensureSpace(1);
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = 61718;
  }
  
  public void writeLong(long paramLong)
  {
    ensureSpace(5);
    char[] arrayOfChar = this.data;
    int i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = 61699;
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = ((char)(int)(paramLong >>> 48));
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = ((char)(int)(paramLong >>> 32));
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = ((char)(int)(paramLong >>> 16));
    arrayOfChar = this.data;
    i = this.gapStart;
    this.gapStart = (i + 1);
    arrayOfChar[i] = ((char)(int)paramLong);
  }
  
  public void writeObject(Object paramObject)
  {
    ensureSpace(3);
    int i = find(paramObject);
    if (i < 4096)
    {
      paramObject = this.data;
      j = this.gapStart;
      this.gapStart = (j + 1);
      paramObject[j] = ((char)(0xE000 | i));
      return;
    }
    paramObject = this.data;
    int j = this.gapStart;
    this.gapStart = (j + 1);
    paramObject[j] = 61709;
    setIntN(this.gapStart, i);
    this.gapStart += 2;
  }
  
  public void writePosition(AbstractSequence paramAbstractSequence, int paramInt)
  {
    ensureSpace(5);
    this.data[this.gapStart] = 61711;
    int i = find(paramAbstractSequence);
    setIntN(this.gapStart + 1, i);
    setIntN(this.gapStart + 3, paramInt);
    this.gapStart += 5;
  }
  
  public void writeProcessingInstruction(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    ensureSpace(paramInt2 + 5);
    int i = this.gapStart;
    char[] arrayOfChar = this.data;
    int j = i + 1;
    arrayOfChar[i] = 61716;
    setIntN(j, find(paramString1));
    setIntN(j + 2, paramInt2);
    i = j + 4;
    paramString2.getChars(paramInt1, paramInt1 + paramInt2, this.data, i);
    this.gapStart = (i + paramInt2);
  }
  
  public void writeProcessingInstruction(String paramString, char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    ensureSpace(paramInt2 + 5);
    int i = this.gapStart;
    char[] arrayOfChar = this.data;
    int j = i + 1;
    arrayOfChar[i] = 61716;
    setIntN(j, find(paramString));
    setIntN(j + 2, paramInt2);
    i = j + 4;
    System.arraycopy(paramArrayOfChar, paramInt1, this.data, i, paramInt2);
    this.gapStart = (i + paramInt2);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\TreeList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */