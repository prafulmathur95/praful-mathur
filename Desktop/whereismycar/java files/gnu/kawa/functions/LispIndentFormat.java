package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispIndentFormat
  extends ReportFormat
{
  int columns;
  boolean current;
  
  public static LispIndentFormat getInstance(int paramInt, boolean paramBoolean)
  {
    LispIndentFormat localLispIndentFormat = new LispIndentFormat();
    localLispIndentFormat.columns = paramInt;
    localLispIndentFormat.current = paramBoolean;
    return localLispIndentFormat;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    int j = getParam(this.columns, 0, paramArrayOfObject, paramInt);
    int i = paramInt;
    if (this.columns == -1610612736) {
      i = paramInt + 1;
    }
    if ((paramWriter instanceof OutPort)) {
      ((OutPort)paramWriter).setIndentation(j, this.current);
    }
    return i;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\LispIndentFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */