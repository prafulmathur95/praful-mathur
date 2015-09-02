package gnu.text;

import gnu.lists.Consumer;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;

public abstract class ReportFormat
  extends Format
{
  public static final int PARAM_FROM_COUNT = -1342177280;
  public static final int PARAM_FROM_LIST = -1610612736;
  public static final int PARAM_UNSPECIFIED = -1073741824;
  
  public static int format(Format paramFormat, Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    if ((paramFormat instanceof ReportFormat)) {
      return ((ReportFormat)paramFormat).format(paramArrayOfObject, paramInt, paramWriter, paramFieldPosition);
    }
    StringBuffer localStringBuffer = new StringBuffer();
    if ((paramFormat instanceof MessageFormat)) {
      paramInt = format(paramFormat, paramArrayOfObject, paramInt, localStringBuffer, paramFieldPosition);
    }
    for (;;)
    {
      int i = localStringBuffer.length();
      paramFormat = new char[i];
      localStringBuffer.getChars(0, i, paramFormat, 0);
      paramWriter.write(paramFormat);
      return paramInt;
      paramFormat.format(paramArrayOfObject[paramInt], localStringBuffer, paramFieldPosition);
      paramInt += 1;
    }
  }
  
  public static int format(Format paramFormat, Object[] paramArrayOfObject, int paramInt, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    if ((paramFormat instanceof ReportFormat)) {
      return ((ReportFormat)paramFormat).format(paramArrayOfObject, paramInt, paramStringBuffer, paramFieldPosition);
    }
    int i;
    if ((paramFormat instanceof MessageFormat))
    {
      i = paramArrayOfObject.length - paramInt;
      if (paramInt > 0)
      {
        Object[] arrayOfObject = new Object[paramArrayOfObject.length - paramInt];
        System.arraycopy(paramArrayOfObject, paramInt, arrayOfObject, 0, arrayOfObject.length);
        paramArrayOfObject = arrayOfObject;
      }
    }
    for (;;)
    {
      paramFormat.format(paramArrayOfObject, paramStringBuffer, paramFieldPosition);
      return paramInt + i;
      continue;
      paramArrayOfObject = paramArrayOfObject[paramInt];
      i = 1;
    }
  }
  
  protected static char getParam(int paramInt1, char paramChar, Object[] paramArrayOfObject, int paramInt2)
  {
    return (char)getParam(paramInt1, paramChar, paramArrayOfObject, paramInt2);
  }
  
  protected static int getParam(int paramInt1, int paramInt2, Object[] paramArrayOfObject, int paramInt3)
  {
    int i;
    if (paramInt1 == -1342177280) {
      i = paramArrayOfObject.length - paramInt3;
    }
    do
    {
      do
      {
        return i;
        if (paramInt1 != -1610612736) {
          break;
        }
        i = paramInt2;
      } while (paramArrayOfObject == null);
      return getParam(paramArrayOfObject[paramInt3], paramInt2);
      i = paramInt2;
    } while (paramInt1 == -1073741824);
    return paramInt1;
  }
  
  public static int getParam(Object paramObject, int paramInt)
  {
    if ((paramObject instanceof Number)) {
      paramInt = ((Number)paramObject).intValue();
    }
    do
    {
      return paramInt;
      if ((paramObject instanceof Character)) {
        return ((Character)paramObject).charValue();
      }
    } while (!(paramObject instanceof Char));
    return ((Char)paramObject).charValue();
  }
  
  public static int nextArg(int paramInt)
  {
    return 0xFFFFFF & paramInt;
  }
  
  public static void print(Writer paramWriter, String paramString)
    throws IOException
  {
    if ((paramWriter instanceof PrintWriter))
    {
      ((PrintWriter)paramWriter).print(paramString);
      return;
    }
    paramWriter.write(paramString.toCharArray());
  }
  
  public static void print(Object paramObject, Consumer paramConsumer)
  {
    if ((paramObject instanceof Printable))
    {
      ((Printable)paramObject).print(paramConsumer);
      return;
    }
    if (paramObject == null) {}
    for (paramObject = "null";; paramObject = paramObject.toString())
    {
      paramConsumer.write((String)paramObject);
      return;
    }
  }
  
  public static int result(int paramInt1, int paramInt2)
  {
    return paramInt1 << 24 | paramInt2;
  }
  
  public static int resultCode(int paramInt)
  {
    return paramInt >>> 24;
  }
  
  public int format(Object paramObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    if ((paramObject instanceof Object[])) {
      return format((Object[])paramObject, paramInt, paramWriter, paramFieldPosition);
    }
    return format(new Object[] { paramObject }, paramInt, paramWriter, paramFieldPosition);
  }
  
  public abstract int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException;
  
  public int format(Object[] paramArrayOfObject, int paramInt, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    CharArrayWriter localCharArrayWriter = new CharArrayWriter();
    try
    {
      paramInt = format(paramArrayOfObject, paramInt, localCharArrayWriter, paramFieldPosition);
      if (paramInt < 0) {
        return paramInt;
      }
    }
    catch (IOException paramArrayOfObject)
    {
      throw new Error("unexpected exception: " + paramArrayOfObject);
    }
    paramStringBuffer.append(localCharArrayWriter.toCharArray());
    return paramInt;
  }
  
  public StringBuffer format(Object paramObject, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    format((Object[])paramObject, 0, paramStringBuffer, paramFieldPosition);
    return paramStringBuffer;
  }
  
  public Object parseObject(String paramString, ParsePosition paramParsePosition)
  {
    throw new Error("ReportFormat.parseObject - not implemented");
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\ReportFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */