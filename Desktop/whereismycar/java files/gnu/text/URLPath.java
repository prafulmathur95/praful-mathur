package gnu.text;

import gnu.mapping.WrappedException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class URLPath
  extends URIPath
{
  final URL url;
  
  URLPath(URL paramURL)
  {
    super(toUri(paramURL));
    this.url = paramURL;
  }
  
  public static URLPath classResourcePath(Class paramClass)
  {
    try
    {
      URL localURL = ResourceStreamHandler.makeURL(paramClass);
      paramClass = localURL;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        String str = paramClass.getName().replace('.', '/') + ".class";
        paramClass = paramClass.getClassLoader().getResource(str);
      }
    }
    catch (Throwable paramClass)
    {
      throw WrappedException.wrapIfNeeded(paramClass);
    }
    return valueOf(paramClass);
  }
  
  public static int getContentLength(URL paramURL)
  {
    try
    {
      int i = paramURL.openConnection().getContentLength();
      return i;
    }
    catch (Throwable paramURL) {}
    return -1;
  }
  
  public static long getLastModified(URL paramURL)
  {
    try
    {
      long l = paramURL.openConnection().getLastModified();
      return l;
    }
    catch (Throwable paramURL) {}
    return 0L;
  }
  
  public static InputStream openInputStream(URL paramURL)
    throws IOException
  {
    return paramURL.openConnection().getInputStream();
  }
  
  public static OutputStream openOutputStream(URL paramURL)
    throws IOException
  {
    Object localObject = paramURL.toString();
    if (((String)localObject).startsWith("file:")) {
      try
      {
        localObject = new FileOutputStream(new File(new URI((String)localObject)));
        return (OutputStream)localObject;
      }
      catch (Throwable localThrowable) {}
    }
    paramURL = paramURL.openConnection();
    paramURL.setDoInput(false);
    paramURL.setDoOutput(true);
    return paramURL.getOutputStream();
  }
  
  public static URI toUri(URL paramURL)
  {
    try
    {
      paramURL = paramURL.toURI();
      return paramURL;
    }
    catch (Throwable paramURL)
    {
      throw WrappedException.wrapIfNeeded(paramURL);
    }
  }
  
  public static URLPath valueOf(URL paramURL)
  {
    return new URLPath(paramURL);
  }
  
  public long getContentLength()
  {
    return getContentLength(this.url);
  }
  
  public long getLastModified()
  {
    return getLastModified(this.url);
  }
  
  public boolean isAbsolute()
  {
    return true;
  }
  
  public InputStream openInputStream()
    throws IOException
  {
    return openInputStream(this.url);
  }
  
  public OutputStream openOutputStream()
    throws IOException
  {
    return openOutputStream(this.url);
  }
  
  public Path resolve(String paramString)
  {
    try
    {
      paramString = valueOf(new URL(this.url, paramString));
      return paramString;
    }
    catch (Throwable paramString)
    {
      throw WrappedException.wrapIfNeeded(paramString);
    }
  }
  
  public String toURIString()
  {
    return this.url.toString();
  }
  
  public URL toURL()
  {
    return this.url;
  }
  
  public URI toUri()
  {
    return toUri(this.url);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\URLPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */