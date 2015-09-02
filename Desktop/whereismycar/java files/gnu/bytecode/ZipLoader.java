package gnu.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipLoader
  extends ClassLoader
{
  private Vector<Object> loadedClasses;
  int size;
  ZipFile zar;
  private String zipname;
  
  public ZipLoader(String paramString)
    throws IOException
  {
    this.zipname = paramString;
    this.zar = new ZipFile(paramString);
    this.size = 0;
    paramString = this.zar.entries();
    while (paramString.hasMoreElements()) {
      if (!((ZipEntry)paramString.nextElement()).isDirectory()) {
        this.size += 1;
      }
    }
    this.loadedClasses = new Vector(this.size);
  }
  
  public void close()
    throws IOException
  {
    if (this.zar != null) {
      this.zar.close();
    }
    this.zar = null;
  }
  
  public Class loadAllClasses()
    throws IOException
  {
    Enumeration localEnumeration = this.zar.entries();
    Object localObject2;
    for (Object localObject1 = null; localEnumeration.hasMoreElements(); localObject1 = localObject2)
    {
      localObject2 = (ZipEntry)localEnumeration.nextElement();
      Object localObject3 = ((ZipEntry)localObject2).getName().replace('/', '.');
      String str = ((String)localObject3).substring(0, ((String)localObject3).length() - "/class".length());
      int i = (int)((ZipEntry)localObject2).getSize();
      localObject2 = this.zar.getInputStream((ZipEntry)localObject2);
      localObject3 = new byte[i];
      new DataInputStream((InputStream)localObject2).readFully((byte[])localObject3);
      localObject3 = defineClass(str, (byte[])localObject3, 0, i);
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = localObject3;
      }
      this.loadedClasses.addElement(str);
      this.loadedClasses.addElement(localObject3);
    }
    close();
    return (Class)localObject1;
  }
  
  public Class loadClass(String paramString, boolean paramBoolean)
    throws ClassNotFoundException
  {
    int i = this.loadedClasses.indexOf(paramString);
    Object localObject1;
    if (i >= 0) {
      localObject1 = (Class)this.loadedClasses.elementAt(i + 1);
    }
    for (;;)
    {
      if (paramBoolean) {
        resolveClass((Class)localObject1);
      }
      return (Class)localObject1;
      if ((this.zar == null) && (this.loadedClasses.size() == this.size * 2))
      {
        localObject1 = Class.forName(paramString);
        continue;
      }
      i = 0;
      String str = paramString.replace('.', '/') + ".class";
      if (this.zar == null) {}
      try
      {
        this.zar = new ZipFile(this.zipname);
        i = 1;
        localObject1 = this.zar.getEntry(str);
        if (localObject1 == null) {
          if (i == 0) {}
        }
      }
      catch (IOException localIOException1)
      {
        try
        {
          close();
          localObject1 = Class.forName(paramString);
        }
        catch (IOException paramString)
        {
          throw new RuntimeException("failed to close \"" + this.zipname + "\"");
        }
        localIOException1 = localIOException1;
        throw new ClassNotFoundException("IOException while loading " + str + " from ziparchive \"" + paramString + "\": " + localIOException1.toString());
      }
      try
      {
        i = (int)localIOException1.getSize();
        Object localObject2 = this.zar.getInputStream(localIOException1);
        Object localObject3 = new byte[i];
        new DataInputStream((InputStream)localObject2).readFully((byte[])localObject3);
        localObject3 = defineClass(paramString, (byte[])localObject3, 0, i);
        this.loadedClasses.addElement(paramString);
        this.loadedClasses.addElement(localObject3);
        localObject2 = localObject3;
        if (this.size * 2 != this.loadedClasses.size()) {
          continue;
        }
        close();
        localObject2 = localObject3;
      }
      catch (IOException localIOException2)
      {
        throw new ClassNotFoundException("IOException while loading " + str + " from ziparchive \"" + paramString + "\": " + localIOException2.toString());
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\ZipLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */