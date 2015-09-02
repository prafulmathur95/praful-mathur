package gnu.kawa.functions;

import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispEscapeFormat
  extends ReportFormat
{
  public static final int ESCAPE_ALL = 242;
  public static final int ESCAPE_NORMAL = 241;
  public static final LispEscapeFormat alwaysTerminate = new LispEscapeFormat(0, -1073741824);
  boolean escapeAll;
  int param1;
  int param2;
  int param3;
  
  public LispEscapeFormat(int paramInt1, int paramInt2)
  {
    this.param1 = paramInt1;
    this.param2 = paramInt2;
    this.param3 = -1073741824;
  }
  
  public LispEscapeFormat(int paramInt1, int paramInt2, int paramInt3)
  {
    this.param1 = paramInt1;
    this.param2 = paramInt2;
    this.param3 = paramInt3;
  }
  
  static Numeric getParam(int paramInt1, Object[] paramArrayOfObject, int paramInt2)
  {
    if (paramInt1 == -1342177280) {
      return IntNum.make(paramArrayOfObject.length - paramInt2);
    }
    if (paramInt1 == -1610612736)
    {
      paramArrayOfObject = paramArrayOfObject[paramInt2];
      if ((paramArrayOfObject instanceof Numeric)) {
        return (Numeric)paramArrayOfObject;
      }
      if ((paramArrayOfObject instanceof Number))
      {
        if (((paramArrayOfObject instanceof Float)) || ((paramArrayOfObject instanceof Double))) {
          return new DFloNum(((Number)paramArrayOfObject).doubleValue());
        }
        return IntNum.make(((Number)paramArrayOfObject).longValue());
      }
      if ((paramArrayOfObject instanceof Char)) {
        return new IntNum(((Char)paramArrayOfObject).intValue());
      }
      if ((paramArrayOfObject instanceof Character)) {
        return new IntNum(((Character)paramArrayOfObject).charValue());
      }
      return new DFloNum(NaN.0D);
    }
    return IntNum.make(paramInt1);
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    boolean bool2 = true;
    boolean bool1 = true;
    int j = 0;
    int i;
    if (this.param1 == -1073741824) {
      if (paramInt == paramArrayOfObject.length)
      {
        if (bool1) {
          break label226;
        }
        i = j;
      }
    }
    for (;;)
    {
      return result(i, paramInt);
      bool1 = false;
      break;
      if ((this.param2 == -1073741824) && (this.param1 == 0))
      {
        bool1 = true;
        break;
      }
      paramWriter = getParam(this.param1, paramArrayOfObject, paramInt);
      i = paramInt;
      if (this.param1 == -1610612736) {
        i = paramInt + 1;
      }
      if (this.param2 == -1073741824)
      {
        bool1 = paramWriter.isZero();
        paramInt = i;
        break;
      }
      paramFieldPosition = getParam(this.param2, paramArrayOfObject, i);
      paramInt = i;
      if (this.param2 == -1610612736) {
        paramInt = i + 1;
      }
      if (this.param3 == -1073741824)
      {
        bool1 = paramWriter.equals(paramFieldPosition);
        break;
      }
      paramArrayOfObject = getParam(this.param3, paramArrayOfObject, paramInt);
      i = paramInt;
      if (this.param3 == -1610612736) {
        i = paramInt + 1;
      }
      if ((paramFieldPosition.geq(paramWriter)) && (paramArrayOfObject.geq(paramFieldPosition))) {}
      for (bool1 = bool2;; bool1 = false)
      {
        paramInt = i;
        break;
      }
      label226:
      if (this.escapeAll) {
        i = 242;
      } else {
        i = 241;
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\LispEscapeFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */