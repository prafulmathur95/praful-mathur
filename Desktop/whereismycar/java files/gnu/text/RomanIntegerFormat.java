package gnu.text;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class RomanIntegerFormat
  extends NumberFormat
{
  static final String codes = "IVXLCDM";
  private static RomanIntegerFormat newRoman;
  private static RomanIntegerFormat oldRoman;
  public boolean oldStyle;
  
  public RomanIntegerFormat() {}
  
  public RomanIntegerFormat(boolean paramBoolean)
  {
    this.oldStyle = paramBoolean;
  }
  
  public static String format(int paramInt)
  {
    return format(paramInt, false);
  }
  
  public static String format(int paramInt, boolean paramBoolean)
  {
    if ((paramInt <= 0) || (paramInt >= 4999)) {
      return Integer.toString(paramInt);
    }
    StringBuffer localStringBuffer = new StringBuffer(20);
    int i = 3;
    int j = 1000;
    if (i >= 0)
    {
      int k = paramInt / j;
      int m = paramInt - k * j;
      if (k == 0) {}
      for (;;)
      {
        j /= 10;
        i -= 1;
        paramInt = m;
        break;
        if ((paramBoolean) || ((k != 4) && (k != 9))) {
          break label127;
        }
        localStringBuffer.append("IVXLCDM".charAt(i * 2));
        localStringBuffer.append("IVXLCDM".charAt(i * 2 + (k + 1) / 5));
      }
      label127:
      paramInt = k;
      if (k >= 5)
      {
        localStringBuffer.append("IVXLCDM".charAt(i * 2 + 1));
        paramInt = k - 5;
      }
      for (;;)
      {
        paramInt -= 1;
        if (paramInt < 0) {
          break;
        }
        localStringBuffer.append("IVXLCDM".charAt(i * 2));
      }
    }
    return localStringBuffer.toString();
  }
  
  public static RomanIntegerFormat getInstance(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (oldRoman == null) {
        oldRoman = new RomanIntegerFormat(true);
      }
      return oldRoman;
    }
    if (newRoman == null) {
      newRoman = new RomanIntegerFormat(false);
    }
    return newRoman;
  }
  
  public StringBuffer format(double paramDouble, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    long l = paramDouble;
    if (l == paramDouble) {
      return format(l, paramStringBuffer, paramFieldPosition);
    }
    paramStringBuffer.append(Double.toString(paramDouble));
    return paramStringBuffer;
  }
  
  public StringBuffer format(long paramLong, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    int i;
    String str;
    if (paramLong > 0L) {
      if (this.oldStyle)
      {
        i = 4999;
        if (paramLong >= i) {
          break label87;
        }
        str = format((int)paramLong, this.oldStyle);
      }
    }
    for (;;)
    {
      if (paramFieldPosition != null)
      {
        paramLong = 1L;
        int j = str.length();
        i = j;
        for (;;)
        {
          i -= 1;
          if (i <= 0) {
            break;
          }
          paramLong = 10L * paramLong + 9L;
        }
        i = 3999;
        break;
        label87:
        str = Long.toString(paramLong);
        continue;
        StringBuffer localStringBuffer = new StringBuffer(j);
        new DecimalFormat("0").format(paramLong, localStringBuffer, paramFieldPosition);
      }
    }
    paramStringBuffer.append(str);
    return paramStringBuffer;
  }
  
  public Number parse(String paramString, ParsePosition paramParsePosition)
  {
    throw new Error("RomanIntegerFormat.parseObject - not implemented");
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\RomanIntegerFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */