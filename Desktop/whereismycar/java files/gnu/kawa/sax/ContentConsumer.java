package gnu.kawa.sax;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.mapping.Symbol;
import gnu.text.Char;
import gnu.xml.XName;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class ContentConsumer
  implements Consumer
{
  String attrLocalName;
  String attrQName;
  String attrURI;
  AttributesImpl attributes = new AttributesImpl();
  char[] chBuffer;
  int inStartTag;
  String[] names = new String[15];
  int nesting = 0;
  ContentHandler out;
  StringBuilder strBuffer = new StringBuilder(200);
  
  public ContentConsumer() {}
  
  public ContentConsumer(ContentHandler paramContentHandler)
  {
    this.out = paramContentHandler;
  }
  
  public ContentConsumer append(char paramChar)
  {
    write(paramChar);
    return this;
  }
  
  public ContentConsumer append(CharSequence paramCharSequence)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null) {
      localObject = "null";
    }
    write((CharSequence)localObject, 0, ((CharSequence)localObject).length());
    return this;
  }
  
  public ContentConsumer append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null) {
      localObject = "null";
    }
    write((CharSequence)localObject, paramInt1, paramInt2);
    return this;
  }
  
  public void endAttribute()
  {
    this.attributes.addAttribute(this.attrURI, this.attrLocalName, this.attrQName, "CDATA", this.strBuffer.toString());
    this.strBuffer.setLength(0);
    this.inStartTag = 1;
  }
  
  public void endDocument()
  {
    try
    {
      this.out.endDocument();
      return;
    }
    catch (SAXException localSAXException)
    {
      error("endDocument", localSAXException);
    }
  }
  
  public void endElement()
  {
    endStartTag();
    flushStrBuffer();
    this.nesting -= 1;
    int i = this.nesting * 3;
    try
    {
      this.out.endElement(this.names[i], this.names[(i + 1)], this.names[(i + 2)]);
      this.names[i] = null;
      this.names[(i + 1)] = null;
      this.names[(i + 2)] = null;
      return;
    }
    catch (SAXException localSAXException)
    {
      for (;;)
      {
        error("endElement", localSAXException);
      }
    }
  }
  
  public void endStartTag()
  {
    if (this.inStartTag != 1) {
      return;
    }
    int i = (this.nesting - 1) * 3;
    try
    {
      this.out.startElement(this.names[i], this.names[(i + 1)], this.names[(i + 2)], this.attributes);
      this.attributes.clear();
      this.inStartTag = 0;
      return;
    }
    catch (SAXException localSAXException)
    {
      for (;;)
      {
        error("startElement", localSAXException);
      }
    }
  }
  
  public void error(String paramString, SAXException paramSAXException)
  {
    throw new RuntimeException("caught " + paramSAXException + " in " + paramString);
  }
  
  public void finalize()
  {
    flushStrBuffer();
  }
  
  void flushStrBuffer()
  {
    if (this.strBuffer.length() > 0)
    {
      if (this.chBuffer == null) {
        this.chBuffer = new char['È'];
      }
      try
      {
        int m = this.strBuffer.length();
        int i = 0;
        for (;;)
        {
          int k = m - i;
          if (k <= 0)
          {
            this.strBuffer.setLength(0);
            return;
          }
          int j = k;
          if (k > this.chBuffer.length) {
            j = this.chBuffer.length;
          }
          this.strBuffer.getChars(i, i + j, this.chBuffer, i);
          this.out.characters(this.chBuffer, 0, j);
          i += j;
        }
        return;
      }
      catch (SAXException localSAXException)
      {
        error("characters", localSAXException);
      }
    }
  }
  
  public ContentHandler getContentHandler()
  {
    return this.out;
  }
  
  public boolean ignoring()
  {
    return false;
  }
  
  public void setContentHandler(ContentHandler paramContentHandler)
  {
    this.out = paramContentHandler;
  }
  
  public void startAttribute(Object paramObject)
  {
    this.attrURI = ((Symbol)paramObject).getNamespaceURI();
    this.attrLocalName = ((Symbol)paramObject).getLocalName();
    this.attrQName = paramObject.toString();
    this.inStartTag = 2;
  }
  
  public void startDocument()
  {
    try
    {
      this.out.startDocument();
      return;
    }
    catch (SAXException localSAXException)
    {
      error("startDocument", localSAXException);
    }
  }
  
  public void startElement(Object paramObject)
  {
    if (this.inStartTag == 1) {
      endStartTag();
    }
    flushStrBuffer();
    int i = this.nesting * 3;
    Object localObject;
    if (i >= this.names.length)
    {
      localObject = new String[i * 2];
      System.arraycopy(this.names, 0, localObject, 0, i);
      this.names = ((String[])localObject);
    }
    String str;
    if ((paramObject instanceof Symbol))
    {
      localObject = (Symbol)paramObject;
      str = ((Symbol)localObject).getNamespaceURI();
      localObject = ((Symbol)localObject).getLocalName();
    }
    for (;;)
    {
      this.names[i] = str;
      this.names[(i + 1)] = localObject;
      this.names[(i + 2)] = paramObject.toString();
      this.inStartTag = 1;
      this.nesting += 1;
      return;
      if ((paramObject instanceof XName))
      {
        localObject = (XName)paramObject;
        str = ((XName)localObject).getNamespaceURI();
        localObject = ((XName)localObject).getLocalName();
      }
      else
      {
        str = "";
        localObject = paramObject.toString();
      }
    }
  }
  
  public void write(int paramInt)
  {
    if (this.inStartTag == 1) {
      endStartTag();
    }
    int i = paramInt;
    if (paramInt >= 65536)
    {
      this.strBuffer.append((char)((paramInt - 65536 >> 10) + 55296));
      i = (paramInt & 0x3FF) + 56320;
    }
    this.strBuffer.append((char)i);
  }
  
  public void write(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if (this.inStartTag == 1) {
      endStartTag();
    }
    this.strBuffer.append(paramCharSequence, paramInt1, paramInt2);
  }
  
  public void write(String paramString)
  {
    if (this.inStartTag == 1) {
      endStartTag();
    }
    this.strBuffer.append(paramString);
  }
  
  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (this.inStartTag == 1) {
      endStartTag();
    }
    if (this.inStartTag == 2)
    {
      this.strBuffer.append(paramArrayOfChar, paramInt1, paramInt2);
      return;
    }
    flushStrBuffer();
    try
    {
      this.out.characters(paramArrayOfChar, paramInt1, paramInt2);
      return;
    }
    catch (SAXException paramArrayOfChar)
    {
      error("characters", paramArrayOfChar);
    }
  }
  
  public void writeBoolean(boolean paramBoolean)
  {
    if (this.inStartTag == 1) {
      endStartTag();
    }
    this.strBuffer.append(paramBoolean);
  }
  
  public void writeDouble(double paramDouble)
  {
    if (this.inStartTag == 1) {
      endStartTag();
    }
    this.strBuffer.append(paramDouble);
  }
  
  public void writeFloat(float paramFloat)
  {
    if (this.inStartTag == 1) {
      endStartTag();
    }
    this.strBuffer.append(paramFloat);
  }
  
  public void writeInt(int paramInt)
  {
    if (this.inStartTag == 1) {
      endStartTag();
    }
    this.strBuffer.append(paramInt);
  }
  
  public void writeLong(long paramLong)
  {
    if (this.inStartTag == 1) {
      endStartTag();
    }
    this.strBuffer.append(paramLong);
  }
  
  public void writeObject(Object paramObject)
  {
    if ((paramObject instanceof Consumable))
    {
      ((Consumable)paramObject).consume(this);
      return;
    }
    if ((paramObject instanceof SeqPosition))
    {
      paramObject = (SeqPosition)paramObject;
      ((SeqPosition)paramObject).sequence.consumeNext(((SeqPosition)paramObject).ipos, this);
      return;
    }
    if ((paramObject instanceof Char))
    {
      ((Char)paramObject).print(this);
      return;
    }
    if (paramObject == null) {}
    for (paramObject = "(null)";; paramObject = paramObject.toString())
    {
      write((String)paramObject);
      return;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\sax\ContentConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */