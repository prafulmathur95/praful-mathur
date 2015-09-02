package gnu.text;

import gnu.lists.FString;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class Path
{
  public static Path defaultPath = userDirPath;
  private static ThreadLocal<Path> pathLocation = new ThreadLocal();
  public static final FilePath userDirPath = FilePath.valueOf(new File("."));
  
  public static Path coerceToPathOrNull(Object paramObject)
  {
    if ((paramObject instanceof Path)) {
      return (Path)paramObject;
    }
    if ((paramObject instanceof URL)) {
      return URLPath.valueOf((URL)paramObject);
    }
    if ((paramObject instanceof URI)) {
      return URIPath.valueOf((URI)paramObject);
    }
    if ((paramObject instanceof File)) {
      return FilePath.valueOf((File)paramObject);
    }
    if ((paramObject instanceof FString)) {}
    for (paramObject = paramObject.toString(); uriSchemeSpecified((String)paramObject); paramObject = (String)paramObject)
    {
      return URIPath.valueOf((String)paramObject);
      if (!(paramObject instanceof String)) {
        return null;
      }
    }
    return FilePath.valueOf((String)paramObject);
  }
  
  public static Path currentPath()
  {
    Path localPath = (Path)pathLocation.get();
    if (localPath != null) {
      return localPath;
    }
    return defaultPath;
  }
  
  public static InputStream openInputStream(Object paramObject)
    throws IOException
  {
    return valueOf(paramObject).openInputStream();
  }
  
  public static String relativize(String paramString1, String paramString2)
    throws URISyntaxException, IOException
  {
    String str = new URI(paramString2).normalize().toString();
    Object localObject = URLPath.valueOf(paramString1).toURI().normalize().toString();
    int m = str.length();
    int n = ((String)localObject).length();
    int i = 0;
    int k = 0;
    int j = 0;
    for (;;)
    {
      int i1;
      if ((i < m) && (i < n))
      {
        i1 = str.charAt(i);
        if (i1 == ((String)localObject).charAt(i)) {}
      }
      else
      {
        paramString2 = paramString1;
        if (j <= 0) {
          return paramString2;
        }
        if ((k <= j + 2) && (m > j + 2))
        {
          paramString2 = paramString1;
          if (str.charAt(j + 2) == '/') {
            return paramString2;
          }
        }
        paramString1 = str.substring(k + 1);
        paramString2 = ((String)localObject).substring(k + 1);
        localObject = new StringBuilder();
        i = paramString1.length();
        for (;;)
        {
          j = i - 1;
          if (j < 0) {
            break;
          }
          i = j;
          if (paramString1.charAt(j) == '/')
          {
            ((StringBuilder)localObject).append("../");
            i = j;
          }
        }
      }
      if (i1 == 47) {
        k = i;
      }
      if (i1 == 58) {
        j = i;
      }
      i += 1;
    }
    ((StringBuilder)localObject).append(paramString2);
    paramString2 = ((StringBuilder)localObject).toString();
    return paramString2;
  }
  
  public static void setCurrentPath(Path paramPath)
  {
    pathLocation.set(paramPath);
  }
  
  public static URL toURL(String paramString)
  {
    String str = paramString;
    try
    {
      if (!uriSchemeSpecified(paramString))
      {
        paramString = currentPath().resolve(paramString);
        if (paramString.isAbsolute()) {
          return paramString.toURL();
        }
        str = paramString.toString();
      }
      paramString = new URL(str);
      return paramString;
    }
    catch (Throwable paramString)
    {
      throw WrappedException.wrapIfNeeded(paramString);
    }
  }
  
  public static int uriSchemeLength(String paramString)
  {
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      char c = paramString.charAt(i);
      if (c == ':') {
        return i;
      }
      if (i == 0)
      {
        if (Character.isLetter(c)) {}
      }
      else {
        while ((!Character.isLetterOrDigit(c)) && (c != '+') && (c != '-') && (c != '.')) {
          return -1;
        }
      }
      i += 1;
    }
    return -1;
  }
  
  public static boolean uriSchemeSpecified(String paramString)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    int i = uriSchemeLength(paramString);
    if ((i == 1) && (File.separatorChar == '\\'))
    {
      i = paramString.charAt(0);
      if (i >= 97)
      {
        bool1 = bool2;
        if (i <= 122) {}
      }
      else if (i >= 65)
      {
        bool1 = bool2;
        if (i <= 90) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    if (i > 0) {}
    for (;;)
    {
      return bool1;
      bool1 = false;
    }
  }
  
  public static Path valueOf(Object paramObject)
  {
    Path localPath = coerceToPathOrNull(paramObject);
    if (localPath == null) {
      throw new WrongType((String)null, -4, paramObject, "path");
    }
    return localPath;
  }
  
  public boolean delete()
  {
    return false;
  }
  
  public boolean exists()
  {
    return getLastModified() != 0L;
  }
  
  public Path getAbsolute()
  {
    if (this == userDirPath) {
      return resolve("");
    }
    return currentPath().resolve(this);
  }
  
  public String getAuthority()
  {
    return null;
  }
  
  public Path getCanonical()
  {
    return getAbsolute();
  }
  
  public CharSequence getCharContent(boolean paramBoolean)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public long getContentLength()
  {
    return -1L;
  }
  
  public Path getDirectory()
  {
    if (isDirectory()) {
      return this;
    }
    return resolve("");
  }
  
  public String getExtension()
  {
    String str = getPath();
    if (str == null) {
      return null;
    }
    int j = str.length();
    int k;
    int i;
    do
    {
      k = j - 1;
      if (k <= 0) {
        break;
      }
      int m = str.charAt(k);
      i = 0;
      j = m;
      if (m == 46)
      {
        j = str.charAt(k - 1);
        i = 1;
      }
      if ((j == 47) || (((this instanceof FilePath)) && (j == File.separatorChar))) {
        break;
      }
      j = k;
    } while (i == 0);
    return str.substring(k + 1);
  }
  
  public String getFragment()
  {
    return null;
  }
  
  public String getHost()
  {
    return null;
  }
  
  public String getLast()
  {
    String str = getPath();
    if (str == null) {
      return null;
    }
    int i = str.length();
    int m = i;
    int j = i;
    int k;
    for (;;)
    {
      k = j - 1;
      if (k <= 0) {
        return "";
      }
      int n = str.charAt(k);
      if (n != 47)
      {
        j = k;
        if ((this instanceof FilePath))
        {
          j = k;
          if (n != File.separatorChar) {}
        }
      }
      else
      {
        if (k + 1 != i) {
          break;
        }
        m = k;
        j = k;
      }
    }
    return str.substring(k + 1, m);
  }
  
  public abstract long getLastModified();
  
  public String getName()
  {
    return toString();
  }
  
  public Path getParent()
  {
    if (isDirectory()) {}
    for (String str = "..";; str = "") {
      return resolve(str);
    }
  }
  
  public abstract String getPath();
  
  public int getPort()
  {
    return -1;
  }
  
  public String getQuery()
  {
    return null;
  }
  
  public abstract String getScheme();
  
  public String getUserInfo()
  {
    return null;
  }
  
  public abstract boolean isAbsolute();
  
  public boolean isDirectory()
  {
    String str = toString();
    int i = str.length();
    if (i > 0)
    {
      i = str.charAt(i - 1);
      if ((i == 47) || (i == File.separatorChar)) {
        return true;
      }
    }
    return false;
  }
  
  public abstract InputStream openInputStream()
    throws IOException;
  
  public abstract OutputStream openOutputStream()
    throws IOException;
  
  public Reader openReader(boolean paramBoolean)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public Writer openWriter()
    throws IOException
  {
    return new OutputStreamWriter(openOutputStream());
  }
  
  public Path resolve(Path paramPath)
  {
    if (paramPath.isAbsolute()) {
      return paramPath;
    }
    return resolve(paramPath.toString());
  }
  
  public abstract Path resolve(String paramString);
  
  public final URI toURI()
  {
    return toUri();
  }
  
  public String toURIString()
  {
    return toUri().toString();
  }
  
  public abstract URL toURL();
  
  public abstract URI toUri();
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\Path.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */