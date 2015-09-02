package gnu.bytecode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipArchive
{
  public static long copy(InputStream paramInputStream, OutputStream paramOutputStream, byte[] paramArrayOfByte)
    throws IOException
  {
    int i;
    for (long l = 0L;; l += i)
    {
      i = paramInputStream.read(paramArrayOfByte);
      if (i <= 0) {
        return l;
      }
      paramOutputStream.write(paramArrayOfByte, 0, i);
    }
  }
  
  public static void copy(InputStream paramInputStream, String paramString, byte[] paramArrayOfByte)
    throws IOException
  {
    File localFile = new File(paramString);
    Object localObject = localFile.getParent();
    if (localObject != null)
    {
      localObject = new File((String)localObject);
      if (!((File)localObject).exists()) {
        System.err.println("mkdirs:" + ((File)localObject).mkdirs());
      }
    }
    if (paramString.charAt(paramString.length() - 1) != '/')
    {
      paramString = new BufferedOutputStream(new FileOutputStream(localFile));
      copy(paramInputStream, paramString, paramArrayOfByte);
      paramString.close();
    }
  }
  
  public static void main(String[] paramArrayOfString)
    throws IOException
  {
    if (paramArrayOfString.length < 2) {
      usage();
    }
    Object localObject1 = paramArrayOfString[0];
    Object localObject3 = paramArrayOfString[1];
    Object localObject2;
    byte[] arrayOfByte;
    Object localObject4;
    try
    {
      if ((((String)localObject1).equals("t")) || (((String)localObject1).equals("p")) || (((String)localObject1).equals("x")))
      {
        localObject2 = System.out;
        arrayOfByte = new byte['Ѐ'];
        if (paramArrayOfString.length == 2)
        {
          paramArrayOfString = new ZipInputStream(new BufferedInputStream(new FileInputStream((String)localObject3)));
          for (;;)
          {
            localObject3 = paramArrayOfString.getNextEntry();
            if (localObject3 == null) {
              break;
            }
            localObject4 = ((ZipEntry)localObject3).getName();
            if (!((String)localObject1).equals("t")) {
              break label163;
            }
            ((PrintStream)localObject2).print((String)localObject4);
            ((PrintStream)localObject2).print(" size: ");
            ((PrintStream)localObject2).println(((ZipEntry)localObject3).getSize());
          }
          return;
        }
      }
    }
    catch (IOException paramArrayOfString)
    {
      System.err.println("I/O Exception:  " + paramArrayOfString);
    }
    for (;;)
    {
      label163:
      if (((String)localObject1).equals("p"))
      {
        copy(paramArrayOfString, (OutputStream)localObject2, arrayOfByte);
        break;
      }
      copy(paramArrayOfString, (String)localObject4, arrayOfByte);
      break;
      localObject4 = new ZipFile((String)localObject3);
      int i = 2;
      while (i < paramArrayOfString.length)
      {
        String str = paramArrayOfString[i];
        ZipEntry localZipEntry = ((ZipFile)localObject4).getEntry(str);
        if (localZipEntry == null)
        {
          System.err.println("zipfile " + (String)localObject3 + ":" + paramArrayOfString[i] + " - not found");
          System.exit(-1);
        }
        else if (((String)localObject1).equals("t"))
        {
          ((PrintStream)localObject2).print(str);
          ((PrintStream)localObject2).print(" size: ");
          ((PrintStream)localObject2).println(localZipEntry.getSize());
        }
        else if (((String)localObject1).equals("p"))
        {
          copy(((ZipFile)localObject4).getInputStream(localZipEntry), (OutputStream)localObject2, arrayOfByte);
        }
        else
        {
          copy(((ZipFile)localObject4).getInputStream(localZipEntry), str, arrayOfByte);
          break label609;
          if (((String)localObject1).equals("q"))
          {
            localObject1 = new ZipOutputStream(new FileOutputStream((String)localObject3));
            i = 2;
            while (i < paramArrayOfString.length)
            {
              localObject2 = new File(paramArrayOfString[i]);
              if (!((File)localObject2).exists()) {
                throw new IOException(paramArrayOfString[i] + " - not found");
              }
              if (!((File)localObject2).canRead()) {
                throw new IOException(paramArrayOfString[i] + " - not readable");
              }
              int j = (int)((File)localObject2).length();
              localObject3 = new FileInputStream((File)localObject2);
              arrayOfByte = new byte[j];
              if (((FileInputStream)localObject3).read(arrayOfByte) != j) {
                throw new IOException(paramArrayOfString[i] + " - read error");
              }
              ((FileInputStream)localObject3).close();
              localObject3 = new ZipEntry(paramArrayOfString[i]);
              ((ZipEntry)localObject3).setSize(j);
              ((ZipEntry)localObject3).setTime(((File)localObject2).lastModified());
              ((ZipOutputStream)localObject1).putNextEntry((ZipEntry)localObject3);
              ((ZipOutputStream)localObject1).write(arrayOfByte, 0, j);
              i += 1;
            }
            ((ZipOutputStream)localObject1).close();
            return;
          }
          usage();
          return;
        }
        label609:
        i += 1;
      }
    }
  }
  
  private static void usage()
  {
    System.err.println("zipfile [ptxq] archive [file ...]");
    System.exit(-1);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\ZipArchive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */