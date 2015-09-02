package gnu.text;

import gnu.lists.FString;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

public class FilePath
  extends Path
  implements Comparable<FilePath>
{
  final File file;
  final String path;
  
  private FilePath(File paramFile)
  {
    this.file = paramFile;
    this.path = paramFile.toString();
  }
  
  private FilePath(File paramFile, String paramString)
  {
    this.file = paramFile;
    this.path = paramString;
  }
  
  public static FilePath coerceToFilePathOrNull(Object paramObject)
  {
    if ((paramObject instanceof FilePath)) {
      return (FilePath)paramObject;
    }
    if ((paramObject instanceof URIPath)) {
      return valueOf(new File(((URIPath)paramObject).uri));
    }
    if ((paramObject instanceof URI)) {
      return valueOf(new File((URI)paramObject));
    }
    if ((paramObject instanceof File)) {
      return valueOf((File)paramObject);
    }
    if ((paramObject instanceof FString)) {}
    for (paramObject = paramObject.toString();; paramObject = (String)paramObject)
    {
      return valueOf((String)paramObject);
      if (!(paramObject instanceof String)) {
        break;
      }
    }
    return null;
  }
  
  public static FilePath makeFilePath(Object paramObject)
  {
    FilePath localFilePath = coerceToFilePathOrNull(paramObject);
    if (localFilePath == null) {
      throw new WrongType((String)null, -4, paramObject, "filepath");
    }
    return localFilePath;
  }
  
  private static URI toUri(File paramFile)
  {
    try
    {
      if (paramFile.isAbsolute()) {
        return paramFile.toURI();
      }
      String str = paramFile.toString();
      char c = File.separatorChar;
      paramFile = str;
      if (c != '/') {
        paramFile = str.replace(c, '/');
      }
      paramFile = new URI(null, null, paramFile, null);
      return paramFile;
    }
    catch (Throwable paramFile)
    {
      throw WrappedException.wrapIfNeeded(paramFile);
    }
  }
  
  public static FilePath valueOf(File paramFile)
  {
    return new FilePath(paramFile);
  }
  
  public static FilePath valueOf(String paramString)
  {
    return new FilePath(new File(paramString), paramString);
  }
  
  public int compareTo(FilePath paramFilePath)
  {
    return this.file.compareTo(paramFilePath.file);
  }
  
  public boolean delete()
  {
    return toFile().delete();
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof FilePath)) && (this.file.equals(((FilePath)paramObject).file));
  }
  
  public boolean exists()
  {
    return this.file.exists();
  }
  
  public Path getCanonical()
  {
    try
    {
      File localFile = this.file.getCanonicalFile();
      FilePath localFilePath = this;
      if (!localFile.equals(this.file)) {
        localFilePath = valueOf(localFile);
      }
      return localFilePath;
    }
    catch (Throwable localThrowable) {}
    return this;
  }
  
  public long getContentLength()
  {
    long l2 = this.file.length();
    long l1 = l2;
    if (l2 == 0L)
    {
      l1 = l2;
      if (!this.file.exists()) {
        l1 = -1L;
      }
    }
    return l1;
  }
  
  public String getLast()
  {
    return this.file.getName();
  }
  
  public long getLastModified()
  {
    return this.file.lastModified();
  }
  
  public FilePath getParent()
  {
    File localFile = this.file.getParentFile();
    if (localFile == null) {
      return null;
    }
    return valueOf(localFile);
  }
  
  public String getPath()
  {
    return this.file.getPath();
  }
  
  public String getScheme()
  {
    if (isAbsolute()) {
      return "file";
    }
    return null;
  }
  
  public int hashCode()
  {
    return this.file.hashCode();
  }
  
  public boolean isAbsolute()
  {
    return (this == Path.userDirPath) || (this.file.isAbsolute());
  }
  
  public boolean isDirectory()
  {
    if (this.file.isDirectory()) {}
    int i;
    do
    {
      return true;
      if (this.file.exists()) {
        break;
      }
      i = this.path.length();
      if (i <= 0) {
        break;
      }
      i = this.path.charAt(i - 1);
    } while ((i == 47) || (i == File.separatorChar));
    return false;
  }
  
  public InputStream openInputStream()
    throws IOException
  {
    return new FileInputStream(this.file);
  }
  
  public OutputStream openOutputStream()
    throws IOException
  {
    return new FileOutputStream(this.file);
  }
  
  public Path resolve(String paramString)
  {
    if (Path.uriSchemeSpecified(paramString)) {
      return URLPath.valueOf(paramString);
    }
    Object localObject = new File(paramString);
    if (((File)localObject).isAbsolute()) {
      return valueOf((File)localObject);
    }
    char c = File.separatorChar;
    localObject = paramString;
    if (c != '/') {
      localObject = paramString.replace('/', c);
    }
    if (this == Path.userDirPath)
    {
      paramString = new File(System.getProperty("user.dir"), (String)localObject);
      return valueOf(paramString);
    }
    if (isDirectory()) {}
    for (paramString = this.file;; paramString = this.file.getParentFile())
    {
      paramString = new File(paramString, (String)localObject);
      break;
    }
  }
  
  public File toFile()
  {
    return this.file;
  }
  
  public String toString()
  {
    return this.path;
  }
  
  public URL toURL()
  {
    if (this == Path.userDirPath) {
      return resolve("").toURL();
    }
    if (!isAbsolute()) {
      return getAbsolute().toURL();
    }
    try
    {
      URL localURL = this.file.toURI().toURL();
      return localURL;
    }
    catch (Throwable localThrowable)
    {
      throw WrappedException.wrapIfNeeded(localThrowable);
    }
  }
  
  public URI toUri()
  {
    if (this == Path.userDirPath) {
      return resolve("").toURI();
    }
    return toUri(this.file);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\FilePath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */