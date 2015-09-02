package gnu.kawa.functions;

import gnu.math.IntNum;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispPluralFormat
  extends ReportFormat
{
  boolean backup;
  boolean y;
  
  public static LispPluralFormat getInstance(boolean paramBoolean1, boolean paramBoolean2)
  {
    LispPluralFormat localLispPluralFormat = new LispPluralFormat();
    localLispPluralFormat.backup = paramBoolean1;
    localLispPluralFormat.y = paramBoolean2;
    return localLispPluralFormat;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    int i = paramInt;
    if (this.backup) {
      i = paramInt - 1;
    }
    int j = i + 1;
    if (paramArrayOfObject[i] != IntNum.one())
    {
      paramInt = 1;
      if (!this.y) {
        break label66;
      }
      if (paramInt == 0) {
        break label60;
      }
      paramArrayOfObject = "ies";
      print(paramWriter, paramArrayOfObject);
    }
    label60:
    label66:
    while (paramInt == 0) {
      for (;;)
      {
        return j;
        paramInt = 0;
        break;
        paramArrayOfObject = "y";
      }
    }
    paramWriter.write(115);
    return j;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\LispPluralFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */