package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class CompoundFormat
  extends ReportFormat
{
  protected Format[] formats;
  protected int length;
  
  public CompoundFormat(Format[] paramArrayOfFormat)
  {
    this.formats = paramArrayOfFormat;
    this.length = paramArrayOfFormat.length;
  }
  
  public CompoundFormat(Format[] paramArrayOfFormat, int paramInt)
  {
    this.formats = paramArrayOfFormat;
    this.length = paramInt;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    int i = 0;
    if (i < this.length)
    {
      Format localFormat = this.formats[i];
      if ((localFormat instanceof ReportFormat))
      {
        int j = ((ReportFormat)localFormat).format(paramArrayOfObject, paramInt, paramWriter, paramFieldPosition);
        paramInt = j;
        if (j < 0) {
          return j;
        }
      }
      else
      {
        if (paramInt < paramArrayOfObject.length) {
          break label76;
        }
        paramWriter.write("#<missing format argument>");
      }
      for (;;)
      {
        i += 1;
        break;
        label76:
        StringBuffer localStringBuffer = new StringBuffer();
        localFormat.format(paramArrayOfObject[paramInt], localStringBuffer, paramFieldPosition);
        paramWriter.write(localStringBuffer.toString());
        paramInt += 1;
      }
    }
    return paramInt;
  }
  
  public final int format(Object[] paramArrayOfObject, int paramInt, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    int i = 0;
    while (i < this.length)
    {
      Format localFormat = this.formats[i];
      if ((localFormat instanceof ReportFormat))
      {
        int j = ((ReportFormat)localFormat).format(paramArrayOfObject, paramInt, paramStringBuffer, paramFieldPosition);
        paramInt = j;
        if (j < 0) {
          return j;
        }
      }
      else
      {
        localFormat.format(paramArrayOfObject[paramInt], paramStringBuffer, paramFieldPosition);
        paramInt += 1;
      }
      i += 1;
    }
    return paramInt;
  }
  
  public Object parseObject(String paramString, ParsePosition paramParsePosition)
  {
    throw new Error("CompoundFormat.parseObject - not implemented");
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("CompoundFormat[");
    int i = 0;
    while (i < this.length)
    {
      if (i > 0) {
        localStringBuffer.append(", ");
      }
      localStringBuffer.append(this.formats[i]);
      i += 1;
    }
    localStringBuffer.append("]");
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\CompoundFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */