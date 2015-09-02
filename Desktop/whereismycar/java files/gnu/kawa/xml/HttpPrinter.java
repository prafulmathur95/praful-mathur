package gnu.kawa.xml;

import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.FilterConsumer;
import gnu.lists.UnescapedData;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.xml.XMLPrinter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

public class HttpPrinter
  extends FilterConsumer
{
  Object currentHeader;
  private int elementNesting;
  Vector headers = new Vector();
  protected OutputStream ostream;
  protected String sawContentType;
  StringBuilder sbuf = new StringBuilder(100);
  private int seenStartDocument;
  boolean seenXmlHeader;
  OutPort writer;
  
  public HttpPrinter(OutPort paramOutPort)
  {
    super(null);
    this.writer = paramOutPort;
  }
  
  public HttpPrinter(OutputStream paramOutputStream)
  {
    super(null);
    this.ostream = paramOutputStream;
  }
  
  public static HttpPrinter make(OutPort paramOutPort)
  {
    return new HttpPrinter(paramOutPort);
  }
  
  private void writeRaw(String paramString)
    throws IOException
  {
    if (this.writer != null) {
      this.writer.write(paramString);
    }
    for (;;)
    {
      return;
      int j = paramString.length();
      int i = 0;
      while (i < j)
      {
        this.ostream.write((byte)paramString.charAt(i));
        i += 1;
      }
    }
  }
  
  public void addHeader(String paramString1, String paramString2)
  {
    if (paramString1.equalsIgnoreCase("Content-type")) {
      this.sawContentType = paramString2;
    }
    this.headers.addElement(paramString1);
    this.headers.addElement(paramString2);
  }
  
  protected void beforeNode()
  {
    if (this.sawContentType == null) {
      addHeader("Content-type", "text/xml");
    }
    beginData();
  }
  
  public void beginData()
  {
    String str;
    if (this.base == null)
    {
      if (this.sawContentType == null) {
        addHeader("Content-type", "text/plain");
      }
      if (this.writer == null) {
        this.writer = new OutPort(this.ostream);
      }
      str = null;
      if (!"text/html".equalsIgnoreCase(this.sawContentType)) {
        break label116;
      }
      str = "html";
    }
    for (;;)
    {
      this.base = XMLPrinter.make(this.writer, str);
      if (this.seenStartDocument == 0)
      {
        this.base.startDocument();
        this.seenStartDocument = 1;
      }
      try
      {
        printHeaders();
        append(this.sbuf);
        this.sbuf.setLength(0);
        return;
      }
      catch (Throwable localThrowable)
      {
        label116:
        throw new RuntimeException(localThrowable.toString());
      }
      if ("application/xhtml+xml".equalsIgnoreCase(this.sawContentType)) {
        str = "xhtml";
      } else if ("text/plain".equalsIgnoreCase(this.sawContentType)) {
        str = "plain";
      }
    }
  }
  
  public void endAttribute()
  {
    if (this.currentHeader != null)
    {
      addHeader(this.currentHeader.toString(), this.sbuf.toString());
      this.sbuf.setLength(0);
      this.currentHeader = null;
      return;
    }
    this.base.endAttribute();
  }
  
  public void endDocument()
  {
    if (this.base != null) {
      this.base.endDocument();
    }
    try
    {
      if (this.sawContentType == null) {
        addHeader("Content-type", "text/plain");
      }
      String str;
      if (this.sbuf.length() > 0)
      {
        str = this.sbuf.toString();
        this.sbuf.setLength(0);
        if (this.writer == null) {
          break label101;
        }
        this.writer.write(str);
      }
      for (;;)
      {
        if (this.writer != null) {
          this.writer.close();
        }
        if (this.ostream == null) {
          break;
        }
        this.ostream.flush();
        return;
        label101:
        this.ostream.write(str.getBytes());
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public void endElement()
  {
    super.endElement();
    this.elementNesting -= 1;
    if ((this.elementNesting == 0) && (this.seenStartDocument == 1)) {
      endDocument();
    }
  }
  
  public void printHeader(String paramString1, String paramString2)
    throws IOException
  {
    writeRaw(paramString1);
    writeRaw(": ");
    writeRaw(paramString2);
    writeRaw("\n");
  }
  
  public void printHeaders()
    throws IOException
  {
    int j = this.headers.size();
    int i = 0;
    while (i < j)
    {
      printHeader(this.headers.elementAt(i).toString(), this.headers.elementAt(i + 1).toString());
      i += 2;
    }
    writeRaw("\n");
  }
  
  public boolean reset(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.headers.clear();
      this.sawContentType = null;
      this.currentHeader = null;
      this.elementNesting = 0;
    }
    this.sbuf.setLength(0);
    this.base = null;
    paramBoolean = true;
    if (this.ostream != null) {
      if (this.writer != null) {
        break label64;
      }
    }
    label64:
    for (paramBoolean = true;; paramBoolean = false)
    {
      this.writer = null;
      return paramBoolean;
    }
  }
  
  public void startAttribute(Object paramObject)
  {
    if (this.base == null)
    {
      this.currentHeader = paramObject;
      return;
    }
    this.base.startAttribute(paramObject);
  }
  
  public void startDocument()
  {
    if (this.base != null) {
      this.base.startDocument();
    }
    this.seenStartDocument = 2;
  }
  
  public void startElement(Object paramObject)
  {
    String str;
    if (this.sawContentType == null)
    {
      if (this.seenXmlHeader) {
        break label49;
      }
      str = "text/html";
    }
    for (;;)
    {
      addHeader("Content-type", str);
      beginData();
      this.base.startElement(paramObject);
      this.elementNesting += 1;
      return;
      label49:
      if (((paramObject instanceof Symbol)) && ("html".equals(((Symbol)paramObject).getLocalPart()))) {
        str = "text/xhtml";
      } else {
        str = "text/xml";
      }
    }
  }
  
  public void write(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if (this.base == null)
    {
      this.sbuf.append(paramCharSequence, paramInt1, paramInt1 + paramInt2);
      return;
    }
    this.base.write(paramCharSequence, paramInt1, paramInt2);
  }
  
  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (this.base == null)
    {
      this.sbuf.append(paramArrayOfChar, paramInt1, paramInt2);
      return;
    }
    this.base.write(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void writeObject(Object paramObject)
  {
    if (((paramObject instanceof Consumable)) && (!(paramObject instanceof UnescapedData)))
    {
      ((Consumable)paramObject).consume(this);
      return;
    }
    beginData();
    super.writeObject(paramObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\HttpPrinter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */