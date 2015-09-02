package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

class LispPrettyFormat
  extends ReportFormat
{
  Format body;
  boolean perLine;
  String prefix;
  boolean seenAt;
  Format[] segments;
  String suffix;
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    String str2 = this.prefix;
    String str1 = this.suffix;
    OutPort localOutPort;
    Object localObject1;
    if ((paramWriter instanceof OutPort))
    {
      localOutPort = (OutPort)paramWriter;
      localObject1 = str1;
    }
    for (;;)
    {
      try
      {
        if (this.seenAt)
        {
          if (localOutPort != null)
          {
            localObject1 = str1;
            localOutPort.startLogicalBlock(str2, this.perLine, this.suffix);
          }
          localObject1 = str1;
          paramInt = ReportFormat.format(this.body, paramArrayOfObject, paramInt, paramWriter, paramFieldPosition);
          paramArrayOfObject = str1;
          if (localOutPort != null) {
            localOutPort.endLogicalBlock(paramArrayOfObject);
          }
          return paramInt;
          localOutPort = null;
          break;
        }
        Object localObject2 = paramArrayOfObject[paramInt];
        localObject1 = str1;
        Object[] arrayOfObject = LispFormat.asArray(localObject2);
        paramArrayOfObject = str1;
        if (arrayOfObject == null)
        {
          paramArrayOfObject = "";
          str2 = "";
        }
        if (localOutPort != null)
        {
          localObject1 = paramArrayOfObject;
          localOutPort.startLogicalBlock(str2, this.perLine, this.suffix);
        }
        if (arrayOfObject == null)
        {
          localObject1 = paramArrayOfObject;
          ObjectFormat.format(localObject2, paramWriter, -1, true);
        }
        else
        {
          localObject1 = paramArrayOfObject;
          ReportFormat.format(this.body, arrayOfObject, 0, paramWriter, paramFieldPosition);
        }
      }
      finally
      {
        if (localOutPort != null) {
          localOutPort.endLogicalBlock((String)localObject1);
        }
      }
      paramInt += 1;
    }
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("LispPrettyFormat[");
    localStringBuffer.append("prefix: \"");
    localStringBuffer.append(this.prefix);
    localStringBuffer.append("\", suffix: \"");
    localStringBuffer.append(this.suffix);
    localStringBuffer.append("\", body: ");
    localStringBuffer.append(this.body);
    localStringBuffer.append("]");
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\LispPrettyFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */