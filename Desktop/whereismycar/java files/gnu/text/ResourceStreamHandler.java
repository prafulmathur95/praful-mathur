package gnu.text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class ResourceStreamHandler
  extends URLStreamHandler
{
  public static final String CLASS_RESOURCE_URI_PREFIX = "class-resource:/";
  public static final int CLASS_RESOURCE_URI_PREFIX_LENGTH = 16;
  ClassLoader cloader;
  
  public ResourceStreamHandler(ClassLoader paramClassLoader)
  {
    this.cloader = paramClassLoader;
  }
  
  public static URL makeURL(Class paramClass)
    throws MalformedURLException
  {
    String str2 = paramClass.getName();
    int i = str2.lastIndexOf('.');
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("class-resource:/");
    String str1 = str2;
    if (i >= 0)
    {
      localStringBuilder.append(str2.substring(0, i));
      localStringBuilder.append('/');
      str1 = str2.substring(i + 1);
    }
    localStringBuilder.append(str1);
    return new URL(null, localStringBuilder.toString(), new ResourceStreamHandler(paramClass.getClassLoader()));
  }
  
  public URLConnection openConnection(URL paramURL)
    throws IOException
  {
    String str2 = paramURL.toString();
    String str1 = str2.substring(16);
    int i = str1.indexOf('/');
    paramURL = str1;
    if (i > 0) {
      paramURL = str1.substring(0, i).replace('.', '/') + str1.substring(i);
    }
    paramURL = this.cloader.getResource(paramURL);
    if (paramURL == null) {
      throw new FileNotFoundException(str2);
    }
    return paramURL.openConnection();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\ResourceStreamHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */