package gnu.kawa.functions;

import gnu.math.ExponentialFormat;
import gnu.math.FixedRealFormat;
import gnu.math.RealNum;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

class LispRealFormat
  extends ReportFormat
{
  int arg1;
  int arg2;
  int arg3;
  int arg4;
  int arg5;
  int arg6;
  int arg7;
  int argsUsed;
  boolean internalPad;
  char op;
  boolean showPlus;
  
  LispRealFormat()
  {
    if ((this.arg1 == -1342177280) || (this.arg2 == -1342177280) || (this.arg3 == -1342177280) || (this.arg4 == -1342177280) || (this.arg5 == -1342177280) || (this.arg6 == -1342177280) || (this.arg7 == -1342177280)) {}
    for (int i = 1;; i = 0)
    {
      this.argsUsed = i;
      if (this.arg1 == -1610612736) {
        this.argsUsed += 2;
      }
      if (this.arg2 == -1610612736) {
        this.argsUsed += 2;
      }
      if (this.arg3 == -1610612736) {
        this.argsUsed += 2;
      }
      if (this.arg4 == -1610612736) {
        this.argsUsed += 2;
      }
      if (this.arg5 == -1610612736) {
        this.argsUsed += 2;
      }
      if (this.arg6 == -1610612736) {
        this.argsUsed += 2;
      }
      if (this.arg7 == -1610612736) {
        this.argsUsed += 2;
      }
      return;
    }
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    Format localFormat = resolve(paramArrayOfObject, paramInt);
    paramInt += (this.argsUsed >> 1);
    localFormat.format((RealNum)paramArrayOfObject[paramInt], localStringBuffer, paramFieldPosition);
    paramWriter.write(localStringBuffer.toString());
    return paramInt + 1;
  }
  
  public Format resolve(Object[] paramArrayOfObject, int paramInt)
  {
    int j;
    int k;
    int m;
    char c;
    if (this.op == '$')
    {
      localObject = new FixedRealFormat();
      j = getParam(this.arg1, 2, paramArrayOfObject, paramInt);
      i = paramInt;
      if (this.arg1 == -1610612736) {
        i = paramInt + 1;
      }
      k = getParam(this.arg2, 1, paramArrayOfObject, i);
      paramInt = i;
      if (this.arg2 == -1610612736) {
        paramInt = i + 1;
      }
      m = getParam(this.arg3, 0, paramArrayOfObject, paramInt);
      i = paramInt;
      if (this.arg3 == -1610612736) {
        i = paramInt + 1;
      }
      c = getParam(this.arg4, ' ', paramArrayOfObject, i);
      if (this.arg4 == -1610612736) {}
      ((FixedRealFormat)localObject).setMaximumFractionDigits(j);
      ((FixedRealFormat)localObject).setMinimumIntegerDigits(k);
      ((FixedRealFormat)localObject).width = m;
      ((FixedRealFormat)localObject).padChar = c;
      ((FixedRealFormat)localObject).internalPad = this.internalPad;
      ((FixedRealFormat)localObject).showPlus = this.showPlus;
      return (Format)localObject;
    }
    if (this.op == 'F')
    {
      localObject = new FixedRealFormat();
      j = getParam(this.arg1, 0, paramArrayOfObject, paramInt);
      i = paramInt;
      if (this.arg1 == -1610612736) {
        i = paramInt + 1;
      }
      k = getParam(this.arg2, -1, paramArrayOfObject, i);
      paramInt = i;
      if (this.arg2 == -1610612736) {
        paramInt = i + 1;
      }
      m = getParam(this.arg3, 0, paramArrayOfObject, paramInt);
      i = paramInt;
      if (this.arg3 == -1610612736) {
        i = paramInt + 1;
      }
      ((FixedRealFormat)localObject).overflowChar = getParam(this.arg4, '\000', paramArrayOfObject, i);
      paramInt = i;
      if (this.arg4 == -1610612736) {
        paramInt = i + 1;
      }
      c = getParam(this.arg5, ' ', paramArrayOfObject, paramInt);
      if (this.arg5 == -1610612736) {}
      ((FixedRealFormat)localObject).setMaximumFractionDigits(k);
      ((FixedRealFormat)localObject).setMinimumIntegerDigits(0);
      ((FixedRealFormat)localObject).width = j;
      ((FixedRealFormat)localObject).scale = m;
      ((FixedRealFormat)localObject).padChar = c;
      ((FixedRealFormat)localObject).internalPad = this.internalPad;
      ((FixedRealFormat)localObject).showPlus = this.showPlus;
      return (Format)localObject;
    }
    Object localObject = new ExponentialFormat();
    ((ExponentialFormat)localObject).exponentShowSign = true;
    ((ExponentialFormat)localObject).width = getParam(this.arg1, 0, paramArrayOfObject, paramInt);
    int i = paramInt;
    if (this.arg1 == -1610612736) {
      i = paramInt + 1;
    }
    ((ExponentialFormat)localObject).fracDigits = getParam(this.arg2, -1, paramArrayOfObject, i);
    paramInt = i;
    if (this.arg2 == -1610612736) {
      paramInt = i + 1;
    }
    ((ExponentialFormat)localObject).expDigits = getParam(this.arg3, 0, paramArrayOfObject, paramInt);
    i = paramInt;
    if (this.arg3 == -1610612736) {
      i = paramInt + 1;
    }
    ((ExponentialFormat)localObject).intDigits = getParam(this.arg4, 1, paramArrayOfObject, i);
    paramInt = i;
    if (this.arg4 == -1610612736) {
      paramInt = i + 1;
    }
    ((ExponentialFormat)localObject).overflowChar = getParam(this.arg5, '\000', paramArrayOfObject, paramInt);
    i = paramInt;
    if (this.arg5 == -1610612736) {
      i = paramInt + 1;
    }
    ((ExponentialFormat)localObject).padChar = getParam(this.arg6, ' ', paramArrayOfObject, i);
    paramInt = i;
    if (this.arg6 == -1610612736) {
      paramInt = i + 1;
    }
    ((ExponentialFormat)localObject).exponentChar = getParam(this.arg7, 'E', paramArrayOfObject, paramInt);
    if ((this.arg7 != -1610612736) || (this.op == 'G')) {}
    for (boolean bool = true;; bool = false)
    {
      ((ExponentialFormat)localObject).general = bool;
      ((ExponentialFormat)localObject).showPlus = this.showPlus;
      return (Format)localObject;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\LispRealFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */