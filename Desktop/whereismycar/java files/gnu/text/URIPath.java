package gnu.text;

import gnu.lists.FString;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class URIPath
  extends Path
  implements Comparable<URIPath>
{
  final URI uri;
  
  URIPath(URI paramURI)
  {
    this.uri = paramURI;
  }
  
  public static URIPath coerceToURIPathOrNull(Object paramObject)
  {
    if ((paramObject instanceof URIPath)) {
      return (URIPath)paramObject;
    }
    if ((paramObject instanceof URL)) {
      return URLPath.valueOf((URL)paramObject);
    }
    if ((paramObject instanceof URI)) {
      return valueOf((URI)paramObject);
    }
    if (((paramObject instanceof File)) || ((paramObject instanceof Path)) || ((paramObject instanceof FString))) {}
    for (paramObject = paramObject.toString();; paramObject = (String)paramObject)
    {
      return valueOf((String)paramObject);
      if (!(paramObject instanceof String)) {
        break;
      }
    }
    return null;
  }
  
  public static String encodeForUri(String paramString, char paramChar)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i2 = paramString.length();
    int i = 0;
    if (i < i2)
    {
      int j = i + 1;
      int m = paramString.charAt(i);
      int k = m;
      i = j;
      if (m >= 55296)
      {
        k = m;
        i = j;
        if (m < 56320)
        {
          k = m;
          i = j;
          if (j < i2)
          {
            k = (m - 55296) * 1024 + (paramString.charAt(j) - 56320) + 65536;
            i = j + 1;
          }
        }
      }
      if (paramChar == 'H') {
        if ((k < 32) || (k > 126)) {
          break label345;
        }
      }
      label345:
      label371:
      label410:
      label419:
      label505:
      label534:
      label547:
      label570:
      for (;;)
      {
        localStringBuffer.append((char)k);
        for (;;)
        {
          break;
          if (((k >= 97) && (k <= 122)) || ((k >= 65) && (k <= 90)) || ((k >= 48) && (k <= 57)) || (k == 45) || (k == 95) || (k == 46) || (k == 126) || ((paramChar == 'I') && ((k == 59) || (k == 47) || (k == 63) || (k == 58) || (k == 42) || (k == 39) || (k == 40) || (k == 41) || (k == 64) || (k == 38) || (k == 61) || (k == 43) || (k == 36) || (k == 44) || (k == 91) || (k == 93) || (k == 35) || (k == 33) || (k == 37)))) {
            break label570;
          }
          int i3 = localStringBuffer.length();
          m = 0;
          if (k < 128) {}
          do
          {
            int i1;
            int n;
            if (m == 0)
            {
              j = 7;
              if (k >= 1 << j) {
                break label505;
              }
              j = k;
              k = j;
              if (m > 0) {
                k = j | 65408 >> m & 0xFF;
              }
              j = 0;
              i1 = m + 1;
              m = 0;
              if (m > 1) {
                break label547;
              }
              n = k & 0xF;
              if (n > 9) {
                break label534;
              }
              n += 48;
            }
            for (;;)
            {
              localStringBuffer.insert(i3, (char)n);
              k >>= 4;
              m += 1;
              break label419;
              if ((k < 2048) || (k < 65536)) {
                break;
              }
              break;
              j = 6 - m;
              break label371;
              j = k & 0x3F | 0x80;
              n = k >> 6;
              k = j;
              j = n;
              break label410;
              n = n - 10 + 65;
            }
            localStringBuffer.insert(i3, '%');
            k = j;
            m = i1;
          } while (j != 0);
        }
      }
    }
    return localStringBuffer.toString();
  }
  
  public static URIPath makeURI(Object paramObject)
  {
    URIPath localURIPath = coerceToURIPathOrNull(paramObject);
    if (localURIPath == null) {
      throw new WrongType((String)null, -4, paramObject, "URI");
    }
    return localURIPath;
  }
  
  public static URIPath valueOf(String paramString)
  {
    try
    {
      paramString = new URIStringPath(new URI(encodeForUri(paramString, 'I')), paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      throw WrappedException.wrapIfNeeded(paramString);
    }
  }
  
  public static URIPath valueOf(URI paramURI)
  {
    return new URIPath(paramURI);
  }
  
  public int compareTo(URIPath paramURIPath)
  {
    return this.uri.compareTo(paramURIPath.uri);
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof URIPath)) && (this.uri.equals(((URIPath)paramObject).uri));
  }
  
  public boolean exists()
  {
    boolean bool = true;
    try
    {
      URLConnection localURLConnection = toURL().openConnection();
      if ((localURLConnection instanceof HttpURLConnection))
      {
        if (((HttpURLConnection)localURLConnection).getResponseCode() != 200) {
          break label53;
        }
        return true;
      }
      long l = localURLConnection.getLastModified();
      if (l == 0L) {
        return false;
      }
    }
    catch (Throwable localThrowable)
    {
      bool = false;
    }
    return bool;
    label53:
    return false;
  }
  
  public String getAuthority()
  {
    return this.uri.getAuthority();
  }
  
  public Path getCanonical()
  {
    if (isAbsolute())
    {
      URI localURI = this.uri.normalize();
      if (localURI == this.uri) {
        return this;
      }
      return valueOf(localURI);
    }
    return getAbsolute().getCanonical();
  }
  
  public long getContentLength()
  {
    return URLPath.getContentLength(toURL());
  }
  
  public String getFragment()
  {
    return this.uri.getFragment();
  }
  
  public String getHost()
  {
    return this.uri.getHost();
  }
  
  public long getLastModified()
  {
    return URLPath.getLastModified(toURL());
  }
  
  public String getPath()
  {
    return this.uri.getPath();
  }
  
  public int getPort()
  {
    return this.uri.getPort();
  }
  
  public String getQuery()
  {
    return this.uri.getQuery();
  }
  
  public String getScheme()
  {
    return this.uri.getScheme();
  }
  
  public String getUserInfo()
  {
    return this.uri.getUserInfo();
  }
  
  public int hashCode()
  {
    return this.uri.hashCode();
  }
  
  public boolean isAbsolute()
  {
    return this.uri.isAbsolute();
  }
  
  public InputStream openInputStream()
    throws IOException
  {
    return URLPath.openInputStream(toURL());
  }
  
  public OutputStream openOutputStream()
    throws IOException
  {
    return URLPath.openOutputStream(toURL());
  }
  
  public Path resolve(String paramString)
  {
    if (Path.uriSchemeSpecified(paramString)) {
      return valueOf(paramString);
    }
    char c = File.separatorChar;
    String str = paramString;
    if (c != '/')
    {
      if ((paramString.length() >= 2) && (((paramString.charAt(1) == ':') && (Character.isLetter(paramString.charAt(0)))) || ((paramString.charAt(0) == c) && (paramString.charAt(1) == c)))) {
        return FilePath.valueOf(new File(paramString));
      }
      str = paramString.replace(c, '/');
    }
    try
    {
      paramString = this.uri.resolve(new URI(null, str, null));
      return valueOf(paramString);
    }
    catch (Throwable paramString)
    {
      throw WrappedException.wrapIfNeeded(paramString);
    }
  }
  
  public String toString()
  {
    return toURIString();
  }
  
  public String toURIString()
  {
    return this.uri.toString();
  }
  
  public URL toURL()
  {
    return Path.toURL(this.uri.toString());
  }
  
  public URI toUri()
  {
    return this.uri;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\URIPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */