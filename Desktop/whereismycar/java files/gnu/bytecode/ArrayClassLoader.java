package gnu.bytecode;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;

public class ArrayClassLoader
  extends ClassLoader
{
  Hashtable cmap = new Hashtable(100);
  URL context;
  Hashtable map = new Hashtable(100);
  
  public ArrayClassLoader() {}
  
  public ArrayClassLoader(ClassLoader paramClassLoader)
  {
    super(paramClassLoader);
  }
  
  public ArrayClassLoader(String[] paramArrayOfString, byte[][] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      addClass(paramArrayOfString[i], paramArrayOfByte[i]);
    }
  }
  
  public ArrayClassLoader(byte[][] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      addClass("lambda" + i, paramArrayOfByte[i]);
    }
  }
  
  public static Package getContextPackage(String paramString)
  {
    try
    {
      Object localObject = Thread.currentThread().getContextClassLoader();
      if ((localObject instanceof ArrayClassLoader))
      {
        localObject = ((ArrayClassLoader)localObject).getPackage(paramString);
        return (Package)localObject;
      }
    }
    catch (SecurityException localSecurityException) {}
    return Package.getPackage(paramString);
  }
  
  public void addClass(ClassType paramClassType)
  {
    this.map.put(paramClassType.getName(), paramClassType);
  }
  
  public void addClass(Class paramClass)
  {
    this.cmap.put(paramClass.getName(), paramClass);
  }
  
  public void addClass(String paramString, byte[] paramArrayOfByte)
  {
    this.map.put(paramString, paramArrayOfByte);
  }
  
  protected URL findResource(String paramString)
  {
    if (this.context != null) {
      try
      {
        URL localURL = new URL(this.context, paramString);
        localURL.openConnection().connect();
        return localURL;
      }
      catch (Throwable localThrowable) {}
    }
    return super.findResource(paramString);
  }
  
  public InputStream getResourceAsStream(String paramString)
  {
    InputStream localInputStream = super.getResourceAsStream(paramString);
    Object localObject = localInputStream;
    if (localInputStream == null)
    {
      localObject = localInputStream;
      if (paramString.endsWith(".class"))
      {
        paramString = paramString.substring(0, paramString.length() - 6).replace('/', '.');
        paramString = this.map.get(paramString);
        localObject = localInputStream;
        if ((paramString instanceof byte[])) {
          localObject = new ByteArrayInputStream((byte[])paramString);
        }
      }
    }
    return (InputStream)localObject;
  }
  
  public URL getResourceContext()
  {
    return this.context;
  }
  
  public Class loadClass(String paramString)
    throws ClassNotFoundException
  {
    Object localObject1 = this.cmap.get(paramString);
    if (localObject1 != null) {
      return (Class)localObject1;
    }
    Object localObject2 = this.map.get(paramString);
    localObject1 = localObject2;
    if ((localObject2 instanceof ClassType))
    {
      localObject1 = (ClassType)localObject2;
      if (((ClassType)localObject1).isExisting()) {
        localObject1 = ((ClassType)localObject1).reflectClass;
      }
    }
    else
    {
      if (!(localObject1 instanceof byte[])) {
        break label133;
      }
    }
    for (;;)
    {
      try
      {
        localObject1 = this.map.get(paramString);
        if ((localObject1 instanceof byte[]))
        {
          localObject1 = (byte[])localObject1;
          localObject1 = defineClass(paramString, (byte[])localObject1, 0, localObject1.length);
          this.cmap.put(paramString, localObject1);
          paramString = (String)localObject1;
          return paramString;
          localObject1 = ((ClassType)localObject1).writeToArray();
          break;
        }
        paramString = (Class)localObject1;
        continue;
        if (localObject1 != null) {
          break label149;
        }
      }
      finally {}
      label133:
      paramString = getParent().loadClass(paramString);
      continue;
      label149:
      paramString = (Class)localObject1;
    }
  }
  
  public Class loadClass(String paramString, boolean paramBoolean)
    throws ClassNotFoundException
  {
    paramString = loadClass(paramString);
    if (paramBoolean) {
      resolveClass(paramString);
    }
    return paramString;
  }
  
  public void setResourceContext(URL paramURL)
  {
    this.context = paramURL;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\ArrayClassLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */