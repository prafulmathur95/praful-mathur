package gnu.kawa.functions;

import gnu.text.Char;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispCharacterFormat
  extends ReportFormat
{
  int charVal;
  int count;
  boolean seenAt;
  boolean seenColon;
  
  public static LispCharacterFormat getInstance(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    LispCharacterFormat localLispCharacterFormat = new LispCharacterFormat();
    localLispCharacterFormat.count = paramInt2;
    localLispCharacterFormat.charVal = paramInt1;
    localLispCharacterFormat.seenAt = paramBoolean1;
    localLispCharacterFormat.seenColon = paramBoolean2;
    return localLispCharacterFormat;
  }
  
  public static void printChar(int paramInt, boolean paramBoolean1, boolean paramBoolean2, Writer paramWriter)
    throws IOException
  {
    if (paramBoolean1)
    {
      print(paramWriter, Char.toScmReadableString(paramInt));
      return;
    }
    if (paramBoolean2)
    {
      if (paramInt < 32)
      {
        paramWriter.write(94);
        paramWriter.write(paramInt + 64);
        return;
      }
      if (paramInt >= 127)
      {
        print(paramWriter, "#\\x");
        print(paramWriter, Integer.toString(paramInt, 16));
        return;
      }
      paramWriter.write(paramInt);
      return;
    }
    paramWriter.write(paramInt);
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    int k = getParam(this.count, 1, paramArrayOfObject, paramInt);
    int i = paramInt;
    if (this.count == -1610612736) {
      i = paramInt + 1;
    }
    int m = getParam(this.charVal, '?', paramArrayOfObject, i);
    int j = k;
    paramInt = i;
    if (this.charVal == -1610612736)
    {
      paramInt = i + 1;
      j = k;
    }
    for (;;)
    {
      j -= 1;
      if (j < 0) {
        break;
      }
      printChar(m, this.seenAt, this.seenColon, paramWriter);
    }
    return paramInt;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\LispCharacterFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */