package gnu.kawa.sax;

import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.xml.XMLFilter;
import gnu.xml.XMLParser;
import java.io.IOException;
import java.io.Reader;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class KawaXMLReader
  extends ContentConsumer
  implements XMLReader
{
  ErrorHandler errorHandler;
  
  public DTDHandler getDTDHandler()
  {
    return null;
  }
  
  public EntityResolver getEntityResolver()
  {
    return null;
  }
  
  public ErrorHandler getErrorHandler()
  {
    return this.errorHandler;
  }
  
  public boolean getFeature(String paramString)
  {
    return false;
  }
  
  public Object getProperty(String paramString)
  {
    return null;
  }
  
  public void parse(String paramString) {}
  
  public void parse(InputSource paramInputSource)
    throws IOException, SAXException
  {
    Object localObject2 = paramInputSource.getCharacterStream();
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = XMLParser.XMLStreamReader(paramInputSource.getByteStream());
    }
    localObject2 = new SourceMessages();
    paramInputSource = new XMLFilter(this);
    localObject1 = new LineBufferedReader((Reader)localObject1);
    paramInputSource.setSourceLocator((LineBufferedReader)localObject1);
    getContentHandler().setDocumentLocator(paramInputSource);
    XMLParser.parse((LineBufferedReader)localObject1, (SourceMessages)localObject2, paramInputSource);
    localObject1 = ((SourceMessages)localObject2).toString(20);
    if (localObject1 != null) {
      throw new SAXParseException((String)localObject1, paramInputSource);
    }
  }
  
  public void setDTDHandler(DTDHandler paramDTDHandler) {}
  
  public void setEntityResolver(EntityResolver paramEntityResolver) {}
  
  public void setErrorHandler(ErrorHandler paramErrorHandler)
  {
    this.errorHandler = paramErrorHandler;
  }
  
  public void setFeature(String paramString, boolean paramBoolean) {}
  
  public void setProperty(String paramString, Object paramObject) {}
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\sax\KawaXMLReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */