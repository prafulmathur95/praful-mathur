package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispRepositionFormat
  extends ReportFormat
{
  boolean absolute;
  boolean backwards;
  int count;
  
  public LispRepositionFormat(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.count = paramInt;
    this.backwards = paramBoolean1;
    this.absolute = paramBoolean2;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    int j = this.count;
    if (this.absolute) {}
    for (int i = 0;; i = 1)
    {
      i = getParam(j, i, paramArrayOfObject, paramInt);
      j = i;
      if (!this.absolute)
      {
        j = i;
        if (this.backwards) {
          j = -i;
        }
        j += paramInt;
      }
      if (j >= 0) {
        break;
      }
      return 0;
    }
    if (j > paramArrayOfObject.length) {
      return paramArrayOfObject.length;
    }
    return j;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\LispRepositionFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */