package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.ParsePosition;

public class ObjectFormat
  extends ReportFormat
{
  private static ObjectFormat plainFormat;
  private static ObjectFormat readableFormat;
  int maxChars;
  boolean readable;
  
  public ObjectFormat(boolean paramBoolean)
  {
    this.readable = paramBoolean;
    this.maxChars = -1073741824;
  }
  
  public ObjectFormat(boolean paramBoolean, int paramInt)
  {
    this.readable = paramBoolean;
    this.maxChars = paramInt;
  }
  
  public static int format(Object[] paramArrayOfObject, int paramInt1, Writer paramWriter, int paramInt2, boolean paramBoolean)
    throws IOException
  {
    if (paramInt1 >= paramArrayOfObject.length)
    {
      paramArrayOfObject = "#<missing format argument>";
      paramInt1 -= 1;
      paramBoolean = false;
      paramInt2 = -1;
    }
    for (;;)
    {
      format(paramArrayOfObject, paramWriter, paramInt2, paramBoolean);
      return paramInt1 + 1;
      paramArrayOfObject = paramArrayOfObject[paramInt1];
    }
  }
  
  public static boolean format(Object paramObject, Writer paramWriter, int paramInt, boolean paramBoolean)
    throws IOException
  {
    if ((paramInt < 0) && ((paramWriter instanceof OutPort)))
    {
      print(paramObject, (OutPort)paramWriter, paramBoolean);
      return true;
    }
    if ((paramInt < 0) && ((paramWriter instanceof CharArrayWriter)))
    {
      paramWriter = new OutPort(paramWriter);
      print(paramObject, paramWriter, paramBoolean);
      paramWriter.close();
      return true;
    }
    CharArrayWriter localCharArrayWriter = new CharArrayWriter();
    OutPort localOutPort = new OutPort(localCharArrayWriter);
    print(paramObject, localOutPort, paramBoolean);
    localOutPort.close();
    int i = localCharArrayWriter.size();
    if ((paramInt < 0) || (i <= paramInt))
    {
      localCharArrayWriter.writeTo(paramWriter);
      return true;
    }
    paramWriter.write(localCharArrayWriter.toCharArray(), 0, paramInt);
    return false;
  }
  
  public static ObjectFormat getInstance(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (readableFormat == null) {
        readableFormat = new ObjectFormat(true);
      }
      return readableFormat;
    }
    if (plainFormat == null) {
      plainFormat = new ObjectFormat(false);
    }
    return plainFormat;
  }
  
  /* Error */
  private static void print(Object paramObject, OutPort paramOutPort, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 76	gnu/mapping/OutPort:printReadable	Z
    //   4: istore 5
    //   6: aload_1
    //   7: getfield 80	gnu/mapping/OutPort:objectFormat	Lgnu/lists/AbstractFormat;
    //   10: astore 4
    //   12: aload_1
    //   13: iload_2
    //   14: putfield 76	gnu/mapping/OutPort:printReadable	Z
    //   17: iload_2
    //   18: ifeq +31 -> 49
    //   21: getstatic 85	kawa/standard/Scheme:writeFormat	Lgnu/lists/AbstractFormat;
    //   24: astore_3
    //   25: aload_1
    //   26: aload_3
    //   27: putfield 80	gnu/mapping/OutPort:objectFormat	Lgnu/lists/AbstractFormat;
    //   30: aload_3
    //   31: aload_0
    //   32: aload_1
    //   33: invokevirtual 91	gnu/lists/AbstractFormat:writeObject	(Ljava/lang/Object;Lgnu/lists/Consumer;)V
    //   36: aload_1
    //   37: iload 5
    //   39: putfield 76	gnu/mapping/OutPort:printReadable	Z
    //   42: aload_1
    //   43: aload 4
    //   45: putfield 80	gnu/mapping/OutPort:objectFormat	Lgnu/lists/AbstractFormat;
    //   48: return
    //   49: getstatic 94	kawa/standard/Scheme:displayFormat	Lgnu/lists/AbstractFormat;
    //   52: astore_3
    //   53: goto -28 -> 25
    //   56: astore_0
    //   57: aload_1
    //   58: iload 5
    //   60: putfield 76	gnu/mapping/OutPort:printReadable	Z
    //   63: aload_1
    //   64: aload 4
    //   66: putfield 80	gnu/mapping/OutPort:objectFormat	Lgnu/lists/AbstractFormat;
    //   69: aload_0
    //   70: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	71	0	paramObject	Object
    //   0	71	1	paramOutPort	OutPort
    //   0	71	2	paramBoolean	boolean
    //   24	29	3	localAbstractFormat1	gnu.lists.AbstractFormat
    //   10	55	4	localAbstractFormat2	gnu.lists.AbstractFormat
    //   4	55	5	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   12	17	56	finally
    //   21	25	56	finally
    //   25	36	56	finally
    //   49	53	56	finally
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    int j = getParam(this.maxChars, -1, paramArrayOfObject, paramInt);
    int i = paramInt;
    if (this.maxChars == -1610612736) {
      i = paramInt + 1;
    }
    return format(paramArrayOfObject, i, paramWriter, j, this.readable);
  }
  
  public Object parseObject(String paramString, ParsePosition paramParsePosition)
  {
    throw new RuntimeException("ObjectFormat.parseObject - not implemented");
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\ObjectFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */