package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

class LispChoiceFormat
  extends ReportFormat
{
  Format[] choices;
  boolean lastIsDefault;
  int param;
  boolean skipIfFalse;
  boolean testBoolean;
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    int i = 0;
    Object localObject;
    if (this.testBoolean)
    {
      localObject = this.choices;
      if (paramArrayOfObject[paramInt] == Boolean.FALSE)
      {
        localObject = localObject[i];
        paramInt += 1;
      }
    }
    for (;;)
    {
      return ReportFormat.format((Format)localObject, paramArrayOfObject, paramInt, paramWriter, paramFieldPosition);
      i = 1;
      break;
      if (!this.skipIfFalse)
      {
        int j = getParam(this.param, -1610612736, paramArrayOfObject, paramInt);
        i = paramInt;
        if (this.param == -1610612736) {
          i = paramInt + 1;
        }
        if (j >= 0)
        {
          paramInt = j;
          if (j < this.choices.length) {}
        }
        else
        {
          if (!this.lastIsDefault) {
            break label137;
          }
          paramInt = this.choices.length - 1;
        }
        localObject = this.choices[paramInt];
        paramInt = i;
        continue;
        label137:
        return i;
      }
      else
      {
        if (paramArrayOfObject[paramInt] == Boolean.FALSE) {
          return paramInt + 1;
        }
        localObject = this.choices[0];
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\LispChoiceFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */