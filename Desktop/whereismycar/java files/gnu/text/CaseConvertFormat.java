package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

public class CaseConvertFormat
  extends ReportFormat
{
  Format baseFormat;
  char code;
  
  public CaseConvertFormat(Format paramFormat, char paramChar)
  {
    this.baseFormat = paramFormat;
    this.code = paramChar;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    int i = format(this.baseFormat, paramArrayOfObject, paramInt, localStringBuffer, paramFieldPosition);
    int j = localStringBuffer.length();
    char c1 = ' ';
    paramInt = 0;
    if (paramInt < j)
    {
      char c2 = localStringBuffer.charAt(paramInt);
      if (this.code == 'U') {
        c1 = Character.toUpperCase(c2);
      }
      for (;;)
      {
        c2 = c1;
        paramWriter.write(c1);
        paramInt += 1;
        c1 = c2;
        break;
        if (((this.code == 'T') && (paramInt == 0)) || ((this.code == 'C') && (!Character.isLetterOrDigit(c1)))) {
          c1 = Character.toTitleCase(c2);
        } else {
          c1 = Character.toLowerCase(c2);
        }
      }
    }
    return i;
  }
  
  public Format getBaseFormat()
  {
    return this.baseFormat;
  }
  
  public void setBaseFormat(Format paramFormat)
  {
    this.baseFormat = paramFormat;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\CaseConvertFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */