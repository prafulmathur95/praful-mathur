package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

class LispIterationFormat
  extends ReportFormat
{
  boolean atLeastOnce;
  Format body;
  int maxIterations;
  boolean seenAt;
  boolean seenColon;
  
  public static int format(Format paramFormat, int paramInt1, Object[] paramArrayOfObject, int paramInt2, Writer paramWriter, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
    int i = 0;
    int j;
    if ((i == paramInt1) && (paramInt1 != -1)) {
      j = paramInt2;
    }
    int k;
    do
    {
      do
      {
        do
        {
          return j;
          if (paramInt2 != paramArrayOfObject.length) {
            break;
          }
          j = paramInt2;
        } while (i > 0);
        j = paramInt2;
      } while (!paramBoolean2);
      if (!paramBoolean1) {
        break;
      }
      Object[] arrayOfObject = LispFormat.asArray(paramArrayOfObject[paramInt2]);
      if (arrayOfObject == null) {}
      k = ReportFormat.format(paramFormat, arrayOfObject, 0, paramWriter, null);
      paramInt2 += 1;
      j = paramInt2;
    } while (ReportFormat.resultCode(k) == 242);
    do
    {
      i += 1;
      break;
      j = ReportFormat.format(paramFormat, paramArrayOfObject, paramInt2, paramWriter, null);
      paramInt2 = j;
    } while (j >= 0);
    return ReportFormat.nextArg(j);
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    int j = getParam(this.maxIterations, -1, paramArrayOfObject, paramInt);
    int i = paramInt;
    if (this.maxIterations == -1610612736) {
      i = paramInt + 1;
    }
    Object localObject = this.body;
    paramFieldPosition = (FieldPosition)localObject;
    paramInt = i;
    if (localObject == null)
    {
      paramInt = i + 1;
      paramFieldPosition = paramArrayOfObject[i];
      if (!(paramFieldPosition instanceof Format)) {
        break label99;
      }
      paramFieldPosition = (Format)paramFieldPosition;
    }
    while (this.seenAt)
    {
      return format(paramFieldPosition, j, paramArrayOfObject, paramInt, paramWriter, this.seenColon, this.atLeastOnce);
      try
      {
        label99:
        paramFieldPosition = new LispFormat(paramFieldPosition.toString());
      }
      catch (Exception paramFieldPosition)
      {
        print(paramWriter, "<invalid argument for \"~{~}\" format>");
        return paramArrayOfObject.length;
      }
    }
    paramArrayOfObject = paramArrayOfObject[paramInt];
    localObject = LispFormat.asArray(paramArrayOfObject);
    if (localObject == null) {
      paramWriter.write("{" + paramArrayOfObject + "}".toString());
    }
    for (;;)
    {
      return paramInt + 1;
      format(paramFieldPosition, j, (Object[])localObject, 0, paramWriter, this.seenColon, this.atLeastOnce);
    }
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("LispIterationFormat[");
    localStringBuffer.append(this.body);
    localStringBuffer.append("]");
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\LispIterationFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */