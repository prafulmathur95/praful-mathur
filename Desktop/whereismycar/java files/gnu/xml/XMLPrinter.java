package gnu.xml;

import gnu.expr.Keyword;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumable;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.UnescapedData;
import gnu.lists.XConsumer;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.math.DFloNum;
import gnu.math.RealNum;
import gnu.text.Char;
import gnu.text.Path;
import gnu.text.PrettyWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;

public class XMLPrinter
  extends OutPort
  implements PositionConsumer, XConsumer
{
  private static final int COMMENT = -5;
  private static final int ELEMENT_END = -4;
  private static final int ELEMENT_START = -3;
  static final String HtmlEmptyTags = "/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/";
  private static final int KEYWORD = -6;
  private static final int PROC_INST = -7;
  private static final int WORD = -2;
  public static final ThreadLocation doctypePublic = new ThreadLocation("doctype-public");
  public static final ThreadLocation doctypeSystem = new ThreadLocation("doctype-system");
  public static final ThreadLocation indentLoc = new ThreadLocation("xml-indent");
  boolean canonicalize = true;
  public boolean canonicalizeCDATA;
  Object[] elementNameStack = new Object[20];
  int elementNesting;
  public boolean escapeNonAscii = true;
  public boolean escapeText = true;
  boolean inAttribute = false;
  int inComment;
  boolean inDocument;
  boolean inStartTag = false;
  public boolean indentAttributes;
  boolean isHtml = false;
  boolean isHtmlOrXhtml = false;
  NamespaceBinding namespaceBindings = NamespaceBinding.predefinedXML;
  NamespaceBinding[] namespaceSaveStack = new NamespaceBinding[20];
  boolean needXMLdecl = false;
  int prev = 32;
  public int printIndent = -1;
  boolean printXMLdecl = false;
  char savedHighSurrogate;
  public boolean strict;
  Object style;
  boolean undeclareNamespaces = false;
  public int useEmptyElementTag = 2;
  
  public XMLPrinter(OutPort paramOutPort, boolean paramBoolean)
  {
    super(paramOutPort, paramBoolean);
  }
  
  public XMLPrinter(OutputStream paramOutputStream)
  {
    super(new OutputStreamWriter(paramOutputStream), false, false);
  }
  
  public XMLPrinter(OutputStream paramOutputStream, Path paramPath)
  {
    super(new OutputStreamWriter(paramOutputStream), true, false, paramPath);
  }
  
  public XMLPrinter(OutputStream paramOutputStream, boolean paramBoolean)
  {
    super(new OutputStreamWriter(paramOutputStream), true, paramBoolean);
  }
  
  public XMLPrinter(Writer paramWriter)
  {
    super(paramWriter);
  }
  
  public XMLPrinter(Writer paramWriter, boolean paramBoolean)
  {
    super(paramWriter, paramBoolean);
  }
  
  static String formatDecimal(String paramString)
  {
    int j;
    if (paramString.indexOf('.') >= 0)
    {
      int k = paramString.length();
      j = k;
      int i;
      int m;
      do
      {
        i = j - 1;
        m = paramString.charAt(i);
        j = i;
      } while (m == 48);
      j = i;
      if (m != 46) {
        j = i + 1;
      }
      if (j != k) {}
    }
    else
    {
      return paramString;
    }
    return paramString.substring(0, j);
  }
  
  public static String formatDecimal(BigDecimal paramBigDecimal)
  {
    return formatDecimal(paramBigDecimal.toPlainString());
  }
  
  public static String formatDouble(double paramDouble)
  {
    if (Double.isNaN(paramDouble)) {
      return "NaN";
    }
    int i;
    if (paramDouble < 0.0D) {
      i = 1;
    }
    while (Double.isInfinite(paramDouble)) {
      if (i != 0)
      {
        return "-INF";
        i = 0;
      }
      else
      {
        return "INF";
      }
    }
    if (i != 0) {}
    String str;
    for (double d = -paramDouble;; d = paramDouble)
    {
      str = Double.toString(paramDouble);
      if (((d < 1000000.0D) && (d >= 1.0E-6D)) || (d == 0.0D)) {
        break;
      }
      return RealNum.toStringScientific(str);
    }
    return formatDecimal(RealNum.toStringDecimal(str));
  }
  
  public static String formatFloat(float paramFloat)
  {
    if (Float.isNaN(paramFloat)) {
      return "NaN";
    }
    int i;
    if (paramFloat < 0.0F) {
      i = 1;
    }
    while (Float.isInfinite(paramFloat)) {
      if (i != 0)
      {
        return "-INF";
        i = 0;
      }
      else
      {
        return "INF";
      }
    }
    if (i != 0) {}
    String str;
    for (float f = -paramFloat;; f = paramFloat)
    {
      str = Float.toString(paramFloat);
      if (((f < 1000000.0F) && (f >= 1.0E-6D)) || (f == 0.0D)) {
        break;
      }
      return RealNum.toStringScientific(str);
    }
    return formatDecimal(RealNum.toStringDecimal(str));
  }
  
  public static boolean isHtmlEmptyElementTag(String paramString)
  {
    int i = "/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/".indexOf(paramString);
    return (i > 0) && ("/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/".charAt(i - 1) == '/') && ("/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/".charAt(paramString.length() + i) == '/');
  }
  
  public static XMLPrinter make(OutPort paramOutPort, Object paramObject)
  {
    paramOutPort = new XMLPrinter(paramOutPort, true);
    paramOutPort.setStyle(paramObject);
    return paramOutPort;
  }
  
  private void startWord()
  {
    closeTag();
    writeWordStart();
  }
  
  public static String toString(Object paramObject)
  {
    StringWriter localStringWriter = new StringWriter();
    new XMLPrinter(localStringWriter).writeObject(paramObject);
    return localStringWriter.toString();
  }
  
  public void beginComment()
  {
    closeTag();
    if ((this.printIndent >= 0) && ((this.prev == -3) || (this.prev == -4) || (this.prev == -5))) {
      if (this.printIndent <= 0) {
        break label68;
      }
    }
    label68:
    for (int i = 82;; i = 78)
    {
      writeBreak(i);
      this.bout.write("<!--");
      this.inComment = 1;
      return;
    }
  }
  
  public void beginEntity(Object paramObject) {}
  
  public void closeTag()
  {
    if ((this.inStartTag) && (!this.inAttribute))
    {
      if ((this.printIndent >= 0) && (this.indentAttributes)) {
        endLogicalBlock("");
      }
      this.bout.write(62);
      this.inStartTag = false;
      this.prev = -3;
    }
    while (!this.needXMLdecl) {
      return;
    }
    this.bout.write("<?xml version=\"1.0\"?>\n");
    if (this.printIndent >= 0) {
      startLogicalBlock("", "", 2);
    }
    this.needXMLdecl = false;
    this.prev = 62;
  }
  
  public void consume(SeqPosition paramSeqPosition)
  {
    paramSeqPosition.sequence.consumeNext(paramSeqPosition.ipos, this);
  }
  
  public void endAttribute()
  {
    if (this.inAttribute)
    {
      if (this.prev != -6)
      {
        this.bout.write(34);
        this.inAttribute = false;
      }
      this.prev = 32;
    }
  }
  
  public void endComment()
  {
    this.bout.write("-->");
    this.prev = -5;
    this.inComment = 0;
  }
  
  public void endDocument()
  {
    this.inDocument = false;
    if (this.printIndent >= 0) {
      endLogicalBlock("");
    }
    freshLine();
  }
  
  public void endElement()
  {
    if (this.useEmptyElementTag == 0) {
      closeTag();
    }
    Object localObject3 = this.elementNameStack[(this.elementNesting - 1)];
    String str = getHtmlTag(localObject3);
    if (this.inStartTag)
    {
      if ((this.printIndent >= 0) && (this.indentAttributes)) {
        endLogicalBlock("");
      }
      Object localObject2 = null;
      Object localObject1;
      Object localObject4;
      if ((str != null) && (isHtmlEmptyElementTag(str)))
      {
        i = 1;
        if (this.useEmptyElementTag != 0)
        {
          localObject1 = localObject2;
          if (str != null)
          {
            localObject1 = localObject2;
            if (i != 0) {}
          }
        }
        else
        {
          localObject1 = localObject2;
          if ((localObject3 instanceof Symbol))
          {
            localObject4 = (Symbol)localObject3;
            localObject1 = ((Symbol)localObject4).getPrefix();
            localObject3 = ((Symbol)localObject4).getNamespaceURI();
            localObject4 = ((Symbol)localObject4).getLocalName();
            if (localObject1 == "") {
              break label322;
            }
            localObject1 = "></" + (String)localObject1 + ":" + (String)localObject4 + ">";
          }
        }
        label177:
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          if ((i == 0) || (!this.isHtml)) {
            break label368;
          }
          localObject2 = ">";
        }
      }
      for (;;)
      {
        this.bout.write((String)localObject2);
        this.inStartTag = false;
        if (this.printIndent >= 0) {
          endLogicalBlock("");
        }
        this.prev = -4;
        if ((str != null) && (!this.escapeText) && (("script".equals(str)) || ("style".equals(str)))) {
          this.escapeText = true;
        }
        localObject1 = this.namespaceSaveStack;
        i = this.elementNesting - 1;
        this.elementNesting = i;
        this.namespaceBindings = localObject1[i];
        this.namespaceSaveStack[this.elementNesting] = null;
        this.elementNameStack[this.elementNesting] = null;
        return;
        i = 0;
        break;
        label322:
        if (localObject3 != "")
        {
          localObject1 = localObject2;
          if (localObject3 != null) {
            break label177;
          }
        }
        localObject1 = "></" + (String)localObject4 + ">";
        break label177;
        label368:
        if (this.useEmptyElementTag == 2) {
          localObject2 = " />";
        } else {
          localObject2 = "/>";
        }
      }
    }
    if (this.printIndent >= 0)
    {
      setIndentation(0, false);
      if (this.prev == -4) {
        if (this.printIndent <= 0) {
          break label458;
        }
      }
    }
    label458:
    for (int i = 82;; i = 78)
    {
      writeBreak(i);
      this.bout.write("</");
      writeQName(localObject3);
      this.bout.write(">");
      break;
    }
  }
  
  public void endEntity() {}
  
  protected void endNumber()
  {
    writeWordEnd();
  }
  
  public void error(String paramString1, String paramString2)
  {
    throw new RuntimeException("serialization error: " + paramString1 + " [" + paramString2 + ']');
  }
  
  protected String getHtmlTag(Object paramObject)
  {
    if ((paramObject instanceof Symbol))
    {
      paramObject = (Symbol)paramObject;
      String str = ((Symbol)paramObject).getNamespaceURI();
      if ((str == "http://www.w3.org/1999/xhtml") || ((this.isHtmlOrXhtml) && (str == ""))) {
        return ((Symbol)paramObject).getLocalPart();
      }
    }
    else if (this.isHtmlOrXhtml)
    {
      return paramObject.toString();
    }
    return null;
  }
  
  public boolean ignoring()
  {
    return false;
  }
  
  boolean mustHexEscape(int paramInt)
  {
    return ((paramInt >= 127) && ((paramInt <= 159) || (this.escapeNonAscii))) || (paramInt == 8232) || ((paramInt < 32) && ((this.inAttribute) || ((paramInt != 9) && (paramInt != 10))));
  }
  
  public void print(Object paramObject)
  {
    Object localObject;
    if ((paramObject instanceof BigDecimal))
    {
      localObject = formatDecimal((BigDecimal)paramObject);
      if (localObject != null) {
        break label80;
      }
    }
    label80:
    for (paramObject = "(null)";; paramObject = localObject.toString())
    {
      write((String)paramObject);
      return;
      if (((paramObject instanceof Double)) || ((paramObject instanceof DFloNum)))
      {
        localObject = formatDouble(((Number)paramObject).doubleValue());
        break;
      }
      localObject = paramObject;
      if (!(paramObject instanceof Float)) {
        break;
      }
      localObject = formatFloat(((Float)paramObject).floatValue());
      break;
    }
  }
  
  void setIndentMode()
  {
    String str = null;
    Object localObject = indentLoc.get(null);
    if (localObject == null) {}
    while (str == null)
    {
      this.printIndent = -1;
      return;
      str = localObject.toString();
    }
    if (str.equals("pretty"))
    {
      this.printIndent = 0;
      return;
    }
    if ((str.equals("always")) || (str.equals("yes")))
    {
      this.printIndent = 1;
      return;
    }
    this.printIndent = -1;
  }
  
  public void setPrintXMLdecl(boolean paramBoolean)
  {
    this.printXMLdecl = paramBoolean;
  }
  
  public void setStyle(Object paramObject)
  {
    this.style = paramObject;
    int i;
    if (this.canonicalize)
    {
      i = 0;
      this.useEmptyElementTag = i;
      if (!"html".equals(paramObject)) {
        break label102;
      }
      this.isHtml = true;
      this.isHtmlOrXhtml = true;
      this.useEmptyElementTag = 2;
      if (this.namespaceBindings == NamespaceBinding.predefinedXML) {
        this.namespaceBindings = XmlNamespace.HTML_BINDINGS;
      }
    }
    for (;;)
    {
      if ("xhtml".equals(paramObject))
      {
        this.isHtmlOrXhtml = true;
        this.useEmptyElementTag = 2;
      }
      if ("plain".equals(paramObject)) {
        this.escapeText = false;
      }
      return;
      i = 1;
      break;
      label102:
      if (this.namespaceBindings == XmlNamespace.HTML_BINDINGS) {
        this.namespaceBindings = NamespaceBinding.predefinedXML;
      }
    }
  }
  
  public void startAttribute(Object paramObject)
  {
    if ((!this.inStartTag) && (this.strict)) {
      error("attribute not in element", "SENR0001");
    }
    if (this.inAttribute) {
      this.bout.write(34);
    }
    this.inAttribute = true;
    this.bout.write(32);
    if (this.printIndent >= 0) {
      writeBreakFill();
    }
    this.bout.write(paramObject.toString());
    this.bout.write("=\"");
    this.prev = 32;
  }
  
  public void startDocument()
  {
    if (this.printXMLdecl) {
      this.needXMLdecl = true;
    }
    setIndentMode();
    this.inDocument = true;
    if ((this.printIndent >= 0) && (!this.needXMLdecl)) {
      startLogicalBlock("", "", 2);
    }
  }
  
  public void startElement(Object paramObject)
  {
    closeTag();
    Object localObject1;
    Object localObject2;
    if (this.elementNesting == 0)
    {
      if (!this.inDocument) {
        setIndentMode();
      }
      if (this.prev == -7) {
        write(10);
      }
      localObject1 = doctypeSystem.get(null);
      if (localObject1 != null)
      {
        localObject2 = localObject1.toString();
        if (((String)localObject2).length() > 0)
        {
          localObject1 = doctypePublic.get(null);
          this.bout.write("<!DOCTYPE ");
          this.bout.write(paramObject.toString());
          if (localObject1 != null) {
            break label406;
          }
          localObject1 = null;
          if ((localObject1 == null) || (((String)localObject1).length() <= 0)) {
            break label414;
          }
          this.bout.write(" PUBLIC \"");
          this.bout.write((String)localObject1);
          this.bout.write("\" \"");
          label135:
          this.bout.write((String)localObject2);
          this.bout.write("\">");
          println();
        }
      }
    }
    int i;
    label202:
    NamespaceBinding localNamespaceBinding;
    label327:
    Object localObject3;
    boolean bool;
    label345:
    String str1;
    if (this.printIndent >= 0)
    {
      if ((this.prev == -3) || (this.prev == -4) || (this.prev == -5))
      {
        if (this.printIndent > 0)
        {
          i = 82;
          writeBreak(i);
        }
      }
      else {
        startLogicalBlock("", "", 2);
      }
    }
    else
    {
      this.bout.write(60);
      writeQName(paramObject);
      if ((this.printIndent >= 0) && (this.indentAttributes)) {
        startLogicalBlock("", "", 2);
      }
      this.elementNameStack[this.elementNesting] = paramObject;
      localObject1 = this.namespaceSaveStack;
      i = this.elementNesting;
      this.elementNesting = (i + 1);
      localObject1[i] = this.namespaceBindings;
      if (!(paramObject instanceof XName)) {
        break label791;
      }
      localObject2 = ((XName)paramObject).namespaceNodes;
      localNamespaceBinding = NamespaceBinding.commonAncestor((NamespaceBinding)localObject2, this.namespaceBindings);
      if (localObject2 != null) {
        break label434;
      }
      i = 0;
      localObject3 = new NamespaceBinding[i];
      i = 0;
      bool = this.canonicalize;
      localObject1 = localObject2;
      if (localObject1 == localNamespaceBinding) {
        break label523;
      }
      j = i;
      ((NamespaceBinding)localObject1).getUri();
      str1 = ((NamespaceBinding)localObject1).getPrefix();
    }
    int k;
    Object localObject4;
    String str2;
    label398:
    label406:
    label414:
    label434:
    do
    {
      k = j - 1;
      if (k < 0) {
        break label459;
      }
      localObject4 = localObject3[k];
      str2 = ((NamespaceBinding)localObject4).getPrefix();
      if (str1 == str2)
      {
        localObject1 = ((NamespaceBinding)localObject1).next;
        break label345;
        localObject1 = localObject1.toString();
        break;
        this.bout.write(" SYSTEM \"");
        break label135;
        i = 78;
        break label202;
        i = ((NamespaceBinding)localObject2).count(localNamespaceBinding);
        break label327;
      }
      j = k;
    } while (!bool);
    if (str1 == null) {
      label459:
      if (!bool) {
        break label516;
      }
    }
    label516:
    for (int j = k + 1;; j = i)
    {
      localObject3[j] = localObject1;
      i += 1;
      break label398;
      if ((str2 != null) && (str1.compareTo(str2) <= 0)) {
        break label459;
      }
      localObject3[(k + 1)] = localObject4;
      j = k;
      break;
    }
    label523:
    do
    {
      do
      {
        j = i - 1;
        if (j < 0) {
          break;
        }
        str1 = localObject3[j];
        localObject1 = str1.prefix;
        str1 = str1.uri;
        i = j;
      } while (str1 == this.namespaceBindings.resolve((String)localObject1));
      if ((str1 != null) || (localObject1 == null)) {
        break;
      }
      i = j;
    } while (!this.undeclareNamespaces);
    this.bout.write(32);
    if (localObject1 == null) {
      this.bout.write("xmlns");
    }
    for (;;)
    {
      this.bout.write("=\"");
      this.inAttribute = true;
      if (str1 != null) {
        write(str1);
      }
      this.inAttribute = false;
      this.bout.write(34);
      i = j;
      break;
      this.bout.write("xmlns:");
      this.bout.write((String)localObject1);
    }
    if (this.undeclareNamespaces)
    {
      localObject1 = this.namespaceBindings;
      if (localObject1 != localNamespaceBinding)
      {
        localObject3 = ((NamespaceBinding)localObject1).prefix;
        if ((((NamespaceBinding)localObject1).uri != null) && (((NamespaceBinding)localObject2).resolve((String)localObject3) == null))
        {
          this.bout.write(32);
          if (localObject3 != null) {
            break label764;
          }
          this.bout.write("xmlns");
        }
        for (;;)
        {
          this.bout.write("=\"\"");
          localObject1 = ((NamespaceBinding)localObject1).next;
          break;
          label764:
          this.bout.write("xmlns:");
          this.bout.write((String)localObject3);
        }
      }
    }
    this.namespaceBindings = ((NamespaceBinding)localObject2);
    label791:
    if (this.elementNesting >= this.namespaceSaveStack.length)
    {
      localObject1 = new NamespaceBinding[this.elementNesting * 2];
      System.arraycopy(this.namespaceSaveStack, 0, localObject1, 0, this.elementNesting);
      this.namespaceSaveStack = ((NamespaceBinding[])localObject1);
      localObject1 = new Object[this.elementNesting * 2];
      System.arraycopy(this.elementNameStack, 0, localObject1, 0, this.elementNesting);
      this.elementNameStack = ((Object[])localObject1);
    }
    this.inStartTag = true;
    paramObject = getHtmlTag(paramObject);
    if (("script".equals(paramObject)) || ("style".equals(paramObject))) {
      this.escapeText = false;
    }
  }
  
  protected void startNumber()
  {
    startWord();
  }
  
  public void write(int paramInt)
  {
    closeTag();
    if ((this.printIndent >= 0) && ((paramInt == 13) || (paramInt == 10)))
    {
      if ((paramInt != 10) || (this.prev != 13)) {
        writeBreak(82);
      }
      if (this.inComment > 0) {
        this.inComment = 1;
      }
      return;
    }
    if (!this.escapeText)
    {
      this.bout.write(paramInt);
      this.prev = paramInt;
      return;
    }
    if (this.inComment > 0)
    {
      if (paramInt == 45) {
        if (this.inComment == 1) {
          this.inComment = 2;
        }
      }
      for (;;)
      {
        super.write(paramInt);
        return;
        this.bout.write(32);
        continue;
        this.inComment = 1;
      }
    }
    this.prev = 59;
    if ((paramInt == 60) && ((!this.isHtml) || (!this.inAttribute)))
    {
      this.bout.write("&lt;");
      return;
    }
    if (paramInt == 62)
    {
      this.bout.write("&gt;");
      return;
    }
    if (paramInt == 38)
    {
      this.bout.write("&amp;");
      return;
    }
    if ((paramInt == 34) && (this.inAttribute))
    {
      this.bout.write("&quot;");
      return;
    }
    if (mustHexEscape(paramInt))
    {
      int i = paramInt;
      int j = i;
      if (paramInt >= 55296)
      {
        if (paramInt < 56320)
        {
          this.savedHighSurrogate = ((char)paramInt);
          return;
        }
        j = i;
        if (paramInt < 57344)
        {
          j = (this.savedHighSurrogate - 55296) * 1024 + (i - 56320) + 65536;
          this.savedHighSurrogate = '\000';
        }
      }
      this.bout.write("&#x" + Integer.toHexString(j).toUpperCase() + ";");
      return;
    }
    this.bout.write(paramInt);
    this.prev = paramInt;
  }
  
  public void write(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 > 0)
    {
      closeTag();
      int j = paramInt1 + paramInt2;
      int i = 0;
      paramInt2 = paramInt1;
      paramInt1 = i;
      if (paramInt2 < j)
      {
        i = paramInt2 + 1;
        paramInt2 = paramString.charAt(paramInt2);
        if (!mustHexEscape(paramInt2))
        {
          if (this.inComment > 0) {
            if ((paramInt2 != 45) && (this.inComment != 2)) {
              break label136;
            }
          }
        }
        else
        {
          label67:
          if (paramInt1 > 0) {
            this.bout.write(paramString, i - 1 - paramInt1, paramInt1);
          }
          write(paramInt2);
          paramInt1 = 0;
        }
        for (;;)
        {
          paramInt2 = i;
          break;
          if ((paramInt2 == 60) || (paramInt2 == 62) || (paramInt2 == 38) || ((this.inAttribute) && ((paramInt2 == 34) || (paramInt2 < 32)))) {
            break label67;
          }
          label136:
          paramInt1 += 1;
        }
      }
      if (paramInt1 > 0) {
        this.bout.write(paramString, j - paramInt1, paramInt1);
      }
    }
    this.prev = 45;
  }
  
  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (paramInt2 > 0)
    {
      closeTag();
      int j = paramInt1 + paramInt2;
      int i = 0;
      paramInt2 = paramInt1;
      paramInt1 = i;
      if (paramInt2 < j)
      {
        i = paramInt2 + 1;
        paramInt2 = paramArrayOfChar[paramInt2];
        if (!mustHexEscape(paramInt2))
        {
          if (this.inComment > 0) {
            if ((paramInt2 != 45) && (this.inComment != 2)) {
              break label134;
            }
          }
        }
        else
        {
          label65:
          if (paramInt1 > 0) {
            this.bout.write(paramArrayOfChar, i - 1 - paramInt1, paramInt1);
          }
          write(paramInt2);
          paramInt1 = 0;
        }
        for (;;)
        {
          paramInt2 = i;
          break;
          if ((paramInt2 == 60) || (paramInt2 == 62) || (paramInt2 == 38) || ((this.inAttribute) && ((paramInt2 == 34) || (paramInt2 < 32)))) {
            break label65;
          }
          label134:
          paramInt1 += 1;
        }
      }
      if (paramInt1 > 0) {
        this.bout.write(paramArrayOfChar, j - paramInt1, paramInt1);
      }
    }
    this.prev = 45;
  }
  
  public void writeBaseUri(Object paramObject) {}
  
  public void writeBoolean(boolean paramBoolean)
  {
    startWord();
    super.print(paramBoolean);
    writeWordEnd();
  }
  
  public void writeCDATA(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (this.canonicalizeCDATA)
    {
      write(paramArrayOfChar, paramInt1, paramInt2);
      return;
    }
    closeTag();
    this.bout.write("<![CDATA[");
    int n = paramInt1 + paramInt2;
    int j = paramInt1;
    int i = paramInt2;
    int k = paramInt1;
    paramInt1 = j;
    while (paramInt1 < n - 2)
    {
      int m = paramInt1;
      j = k;
      paramInt2 = i;
      if (paramArrayOfChar[paramInt1] == ']')
      {
        m = paramInt1;
        j = k;
        paramInt2 = i;
        if (paramArrayOfChar[(paramInt1 + 1)] == ']')
        {
          m = paramInt1;
          j = k;
          paramInt2 = i;
          if (paramArrayOfChar[(paramInt1 + 2)] == '>')
          {
            if (paramInt1 > k) {
              this.bout.write(paramArrayOfChar, k, paramInt1 - k);
            }
            print("]]]><![CDATA[]>");
            j = paramInt1 + 3;
            paramInt2 = n - j;
            m = paramInt1 + 2;
          }
        }
      }
      paramInt1 = m + 1;
      k = j;
      i = paramInt2;
    }
    this.bout.write(paramArrayOfChar, k, i);
    this.bout.write("]]>");
    this.prev = 62;
  }
  
  public void writeComment(String paramString)
  {
    beginComment();
    write(paramString);
    endComment();
  }
  
  public void writeComment(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    beginComment();
    write(paramArrayOfChar, paramInt1, paramInt2);
    endComment();
  }
  
  public void writeDouble(double paramDouble)
  {
    startWord();
    this.bout.write(formatDouble(paramDouble));
  }
  
  public void writeFloat(float paramFloat)
  {
    startWord();
    this.bout.write(formatFloat(paramFloat));
  }
  
  public void writeObject(Object paramObject)
  {
    if ((paramObject instanceof SeqPosition))
    {
      this.bout.clearWordEnd();
      paramObject = (SeqPosition)paramObject;
      ((SeqPosition)paramObject).sequence.consumeNext(((SeqPosition)paramObject).ipos, this);
      if ((((SeqPosition)paramObject).sequence instanceof NodeTree)) {
        this.prev = 45;
      }
      return;
    }
    if (((paramObject instanceof Consumable)) && (!(paramObject instanceof UnescapedData)))
    {
      ((Consumable)paramObject).consume(this);
      return;
    }
    if ((paramObject instanceof Keyword))
    {
      startAttribute(((Keyword)paramObject).getName());
      this.prev = -6;
      return;
    }
    closeTag();
    if ((paramObject instanceof UnescapedData))
    {
      this.bout.clearWordEnd();
      this.bout.write(((UnescapedData)paramObject).getData());
      this.prev = 45;
      return;
    }
    if ((paramObject instanceof Char))
    {
      Char.print(((Char)paramObject).intValue(), this);
      return;
    }
    startWord();
    this.prev = 32;
    print(paramObject);
    writeWordEnd();
    this.prev = -2;
  }
  
  public void writePosition(AbstractSequence paramAbstractSequence, int paramInt)
  {
    paramAbstractSequence.consumeNext(paramInt, this);
  }
  
  public void writeProcessingInstruction(String paramString, char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if ("xml".equals(paramString)) {
      this.needXMLdecl = false;
    }
    closeTag();
    this.bout.write("<?");
    print(paramString);
    print(' ');
    this.bout.write(paramArrayOfChar, paramInt1, paramInt2);
    this.bout.write("?>");
    this.prev = -7;
  }
  
  protected void writeQName(Object paramObject)
  {
    if ((paramObject instanceof Symbol))
    {
      paramObject = (Symbol)paramObject;
      localObject = ((Symbol)paramObject).getPrefix();
      if ((localObject != null) && (((String)localObject).length() > 0))
      {
        this.bout.write((String)localObject);
        this.bout.write(58);
      }
      this.bout.write(((Symbol)paramObject).getLocalPart());
      return;
    }
    Object localObject = this.bout;
    if (paramObject == null) {}
    for (paramObject = "{null name}";; paramObject = (String)paramObject)
    {
      ((PrettyWriter)localObject).write((String)paramObject);
      return;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\xml\XMLPrinter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */