package gnu.kawa.xml;

import gnu.mapping.OutPort;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import gnu.text.Path;
import gnu.xml.XMLPrinter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class WriteTo
  extends Procedure2
{
  public static final WriteTo writeTo = new WriteTo();
  public static final WriteTo writeToIfChanged = new WriteTo();
  boolean ifChanged;
  
  static
  {
    writeToIfChanged.ifChanged = true;
  }
  
  public static void writeTo(Object paramObject, Path paramPath, OutputStream paramOutputStream)
    throws Throwable
  {
    paramOutputStream = new OutPort(paramOutputStream, paramPath);
    XMLPrinter localXMLPrinter = new XMLPrinter(paramOutputStream, false);
    if ("html".equals(paramPath.getExtension())) {
      localXMLPrinter.setStyle("html");
    }
    Values.writeValues(paramObject, localXMLPrinter);
    paramOutputStream.close();
  }
  
  public static void writeTo(Object paramObject1, Object paramObject2)
    throws Throwable
  {
    paramObject2 = Path.valueOf(paramObject2);
    writeTo(paramObject1, (Path)paramObject2, ((Path)paramObject2).openOutputStream());
  }
  
  public static void writeToIfChanged(Object paramObject1, Object paramObject2)
    throws Throwable
  {
    paramObject2 = Path.valueOf(paramObject2);
    Object localObject = new ByteArrayOutputStream();
    writeTo(paramObject1, (Path)paramObject2, (OutputStream)localObject);
    paramObject1 = ((ByteArrayOutputStream)localObject).toByteArray();
    for (;;)
    {
      try
      {
        localObject = new BufferedInputStream(((Path)paramObject2).openInputStream());
        i = 0;
        k = ((InputStream)localObject).read();
        if (i != paramObject1.length) {
          continue;
        }
        j = 1;
      }
      catch (Throwable localThrowable)
      {
        int i;
        int k;
        int j;
        continue;
      }
      ((InputStream)localObject).close();
      paramObject2 = new BufferedOutputStream(((Path)paramObject2).openOutputStream());
      ((OutputStream)paramObject2).write((byte[])paramObject1);
      ((OutputStream)paramObject2).close();
      return;
      j = 0;
      continue;
      ((InputStream)localObject).close();
      return;
      if (j == 0)
      {
        j = paramObject1[i];
        if (j == k) {
          i += 1;
        }
      }
      else
      {
        continue;
        if (k < 0) {
          if (j != 0) {}
        }
      }
    }
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
    throws Throwable
  {
    if (this.ifChanged) {
      writeToIfChanged(paramObject1, paramObject2.toString());
    }
    for (;;)
    {
      return Values.empty;
      writeTo(paramObject1, paramObject2.toString());
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\WriteTo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */