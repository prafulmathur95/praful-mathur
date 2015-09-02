package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.text.FieldPosition;

public class IntegerFormat
  extends ReportFormat
{
  public static final int MIN_DIGITS = 64;
  public static final int PAD_RIGHT = 16;
  public static final int SHOW_BASE = 8;
  public static final int SHOW_GROUPS = 1;
  public static final int SHOW_PLUS = 2;
  public static final int SHOW_SPACE = 4;
  public static final int UPPERCASE = 32;
  public int base = 10;
  public int commaChar = 44;
  public int commaInterval = 3;
  public int flags = 0;
  public int minWidth = 1;
  public int padChar = 32;
  
  public String convertToIntegerString(Object paramObject, int paramInt)
  {
    if (!(paramObject instanceof Number)) {
      return null;
    }
    if ((paramObject instanceof BigInteger)) {
      return ((BigInteger)paramObject).toString(paramInt);
    }
    return Long.toString(((Number)paramObject).longValue(), paramInt);
  }
  
  public int format(Object paramObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    int m;
    int k;
    int i7;
    int j;
    int i8;
    int i9;
    int n;
    int i1;
    label154:
    int i2;
    label167:
    int i3;
    if ((paramObject instanceof Object[]))
    {
      paramFieldPosition = (Object[])paramObject;
      m = getParam(this.minWidth, 1, paramFieldPosition, paramInt);
      k = paramInt;
      if (this.minWidth == -1610612736) {
        k = paramInt + 1;
      }
      i7 = getParam(this.padChar, ' ', paramFieldPosition, k);
      j = k;
      if (this.padChar == -1610612736) {
        j = k + 1;
      }
      i8 = getParam(this.commaChar, ',', paramFieldPosition, j);
      paramInt = j;
      if (this.commaChar == -1610612736) {
        paramInt = j + 1;
      }
      i9 = getParam(this.commaInterval, 3, paramFieldPosition, paramInt);
      n = paramInt;
      if (this.commaInterval == -1610612736) {
        n = paramInt + 1;
      }
      if ((this.flags & 0x1) == 0) {
        break label205;
      }
      i1 = 1;
      if ((this.flags & 0x10) == 0) {
        break label211;
      }
      i2 = 1;
      if (i7 != 48) {
        break label217;
      }
      i3 = 1;
    }
    for (;;)
    {
      if (paramFieldPosition != null)
      {
        if (n >= paramFieldPosition.length)
        {
          paramWriter.write("#<missing format argument>");
          return n;
          paramFieldPosition = null;
          break;
          label205:
          i1 = 0;
          break label154;
          label211:
          i2 = 0;
          break label167;
          label217:
          i3 = 0;
          continue;
        }
        paramObject = paramFieldPosition[n];
      }
    }
    paramFieldPosition = convertToIntegerString(paramObject, this.base);
    int i5;
    label281:
    label294:
    int i4;
    if (paramFieldPosition != null)
    {
      int i10 = paramFieldPosition.charAt(0);
      if (i10 == 45)
      {
        i5 = 1;
        i6 = paramFieldPosition.length();
        if (i5 == 0) {
          break label466;
        }
        j = i6 - 1;
        if (i1 == 0) {
          break label473;
        }
        paramInt = (j - 1) / i9;
        paramInt = j + paramInt;
        if (i5 == 0)
        {
          k = paramInt;
          if ((this.flags & 0x6) == 0) {}
        }
        else
        {
          k = paramInt + 1;
        }
        paramInt = k;
        if ((this.flags & 0x8) != 0)
        {
          if (this.base != 16) {
            break label478;
          }
          paramInt = k + 2;
        }
      }
      for (;;)
      {
        k = i6;
        i4 = paramInt;
        if ((this.flags & 0x40) != 0)
        {
          k = i6;
          i4 = j;
          if (i6 == 1)
          {
            k = i6;
            i4 = j;
            if (i10 == 48)
            {
              k = i6;
              i4 = j;
              if (m == 0)
              {
                k = 0;
                i4 = j;
              }
            }
          }
        }
        paramInt = m;
        if (i2 != 0) {
          break label508;
        }
        paramInt = m;
        if (i3 != 0) {
          break label508;
        }
        j = m;
        for (;;)
        {
          paramInt = j;
          if (j <= i4) {
            break;
          }
          paramWriter.write(i7);
          j -= 1;
        }
        i5 = 0;
        break;
        label466:
        j = i6;
        break label281;
        label473:
        paramInt = 0;
        break label294;
        label478:
        paramInt = k;
        if (this.base == 8)
        {
          paramInt = k;
          if (i10 != 48) {
            paramInt = k + 1;
          }
        }
      }
      label508:
      int i6 = 0;
      if (i5 != 0)
      {
        paramWriter.write(45);
        m = 0 + 1;
        j = k - 1;
        if ((this.base <= 10) || ((this.flags & 0x20) == 0)) {
          break label685;
        }
        i5 = 1;
        label555:
        if ((this.flags & 0x8) != 0)
        {
          if (this.base != 16) {
            break label698;
          }
          paramWriter.write(48);
          if (i5 == 0) {
            break label691;
          }
          k = 88;
          label589:
          paramWriter.write(k);
        }
      }
      for (;;)
      {
        k = paramInt;
        if (i3 == 0) {
          break label832;
        }
        for (;;)
        {
          k = paramInt;
          if (paramInt <= i4) {
            break;
          }
          paramWriter.write(i7);
          paramInt -= 1;
        }
        if ((this.flags & 0x2) != 0)
        {
          paramWriter.write(43);
          m = i6;
          j = k;
          break;
        }
        m = i6;
        j = k;
        if ((this.flags & 0x4) == 0) {
          break;
        }
        paramWriter.write(32);
        m = i6;
        j = k;
        break;
        label685:
        i5 = 0;
        break label555;
        label691:
        k = 120;
        break label589;
        label698:
        if ((this.base == 8) && (i10 != 48)) {
          paramWriter.write(48);
        }
      }
    }
    for (;;)
    {
      int i = paramFieldPosition.charAt(paramInt);
      m = i;
      if (i5 != 0) {
        m = Character.toUpperCase(i);
      }
      paramWriter.write(m);
      j -= 1;
      if ((i1 != 0) && (j > 0) && (j % i9 == 0)) {
        paramWriter.write(i8);
      }
      paramInt += 1;
      while (j == 0)
      {
        if (i2 != 0) {
          while (k > i4)
          {
            paramWriter.write(i7);
            k -= 1;
            continue;
            print(paramWriter, paramObject.toString());
          }
        }
        return n + 1;
        label832:
        paramInt = m;
      }
    }
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition)
    throws IOException
  {
    return format(paramArrayOfObject, paramInt, paramWriter, paramFieldPosition);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\IntegerFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */