package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.ParsePosition;

public class LiteralFormat
  extends ReportFormat
{
  char[] text;
  
  public LiteralFormat(String paramString)
  {
    this.text = paramString.toCharArray();
  }
  
  public LiteralFormat(StringBuffer paramStringBuffer)
  {
    int i = paramStringBuffer.length();
    this.text = new char[i];
    paramStringBuffer.getChars(0, i, this.text, 0);
  }
  
  public LiteralFormat(char[] paramArrayOfChar)
  {
    this.text = paramArrayOfChar;
  }
  
  public String content()
  {
    return new String(this.text);
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    paramWriter.write(this.text);
    return paramInt;
  }
  
  public Object parseObject(String paramString, ParsePosition paramParsePosition)
  {
    throw new Error("LiteralFormat.parseObject - not implemented");
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("LiteralFormat[\"");
    localStringBuffer.append(this.text);
    localStringBuffer.append("\"]");
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\LiteralFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */