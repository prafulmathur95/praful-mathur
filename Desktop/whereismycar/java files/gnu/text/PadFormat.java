package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;

public class PadFormat
  extends ReportFormat
{
  Format fmt;
  int minWidth;
  char padChar;
  int where;
  
  public PadFormat(Format paramFormat, int paramInt)
  {
    this(paramFormat, paramInt, ' ', 100);
  }
  
  public PadFormat(Format paramFormat, int paramInt1, char paramChar, int paramInt2)
  {
    this.fmt = paramFormat;
    this.minWidth = paramInt1;
    this.padChar = paramChar;
    this.where = paramInt2;
  }
  
  public static int format(Format paramFormat, Object[] paramArrayOfObject, int paramInt1, Writer paramWriter, char paramChar, int paramInt2, int paramInt3, int paramInt4, int paramInt5, FieldPosition paramFieldPosition)
    throws IOException
  {
    StringBuffer localStringBuffer = new StringBuffer(200);
    if ((paramFormat instanceof ReportFormat)) {
      paramInt1 = ((ReportFormat)paramFormat).format(paramArrayOfObject, paramInt1, localStringBuffer, paramFieldPosition);
    }
    for (;;)
    {
      int i = localStringBuffer.length();
      paramInt4 = padNeeded(i, paramInt2, paramInt3, paramInt4);
      paramInt3 = 0;
      paramArrayOfObject = localStringBuffer.toString();
      if (paramInt4 <= 0) {
        break label321;
      }
      paramFormat = paramArrayOfObject;
      paramInt2 = paramInt5;
      if (paramInt5 == -1)
      {
        paramFormat = paramArrayOfObject;
        if (i > 0)
        {
          paramInt2 = paramArrayOfObject.charAt(0);
          if ((paramInt2 == 45) || (paramInt2 == 43))
          {
            paramInt3 = 0 + 1;
            paramWriter.write(paramInt2);
          }
          paramInt2 = paramInt3;
          if (i - paramInt3 > 2)
          {
            paramInt2 = paramInt3;
            if (paramArrayOfObject.charAt(paramInt3) == '0')
            {
              paramWriter.write(48);
              paramInt3 += 1;
              paramInt5 = paramArrayOfObject.charAt(paramInt3);
              if (paramInt5 != 120)
              {
                paramInt2 = paramInt3;
                if (paramInt5 != 88) {}
              }
              else
              {
                paramInt2 = paramInt3 + 1;
                paramWriter.write(paramInt5);
              }
            }
          }
          paramFormat = paramArrayOfObject;
          if (paramInt2 > 0) {
            paramFormat = paramArrayOfObject.substring(paramInt2);
          }
        }
        paramInt2 = 0;
      }
      paramInt3 = paramInt4 * paramInt2 / 100;
      paramInt2 = paramInt4 - paramInt3;
      for (;;)
      {
        paramInt2 -= 1;
        if (paramInt2 < 0) {
          break;
        }
        paramWriter.write(paramChar);
      }
      if ((paramFormat instanceof MessageFormat))
      {
        paramFormat.format(paramArrayOfObject, localStringBuffer, paramFieldPosition);
        paramInt1 = paramArrayOfObject.length;
      }
      else
      {
        paramFormat.format(paramArrayOfObject[paramInt1], localStringBuffer, paramFieldPosition);
        paramInt1 += 1;
      }
    }
    paramWriter.write(paramFormat);
    paramInt2 = paramInt3;
    for (;;)
    {
      paramInt2 -= 1;
      if (paramInt2 < 0) {
        break;
      }
      paramWriter.write(paramChar);
    }
    label321:
    paramWriter.write(paramArrayOfObject);
    return paramInt1;
  }
  
  public static int padNeeded(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = paramInt1 + paramInt4;
    int i = j;
    paramInt4 = paramInt3;
    if (paramInt3 <= 1)
    {
      paramInt4 = paramInt2 - j;
      i = j;
    }
    while (i < paramInt2) {
      i += paramInt4;
    }
    return i - paramInt1;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    return format(this.fmt, paramArrayOfObject, paramInt, paramWriter, this.padChar, this.minWidth, 1, 0, this.where, paramFieldPosition);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\PadFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */