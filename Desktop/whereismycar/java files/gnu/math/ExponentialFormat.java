package gnu.math;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class ExponentialFormat
  extends Format
{
  static final double LOG10 = Math.log(10.0D);
  public int expDigits;
  public char exponentChar = 'E';
  public boolean exponentShowSign;
  public int fracDigits = -1;
  public boolean general;
  public int intDigits;
  public char overflowChar;
  public char padChar;
  public boolean showPlus;
  public int width;
  
  static boolean addOne(StringBuffer paramStringBuffer, int paramInt1, int paramInt2)
  {
    for (;;)
    {
      if (paramInt2 == paramInt1)
      {
        paramStringBuffer.insert(paramInt2, '1');
        return true;
      }
      paramInt2 -= 1;
      int i = paramStringBuffer.charAt(paramInt2);
      if (i != 57)
      {
        paramStringBuffer.setCharAt(paramInt2, (char)(i + 1));
        return false;
      }
      paramStringBuffer.setCharAt(paramInt2, '0');
    }
  }
  
  StringBuffer format(double paramDouble, String paramString, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    int i6 = this.intDigits;
    int i10 = this.fracDigits;
    double d;
    int i13;
    int i7;
    label69:
    int i5;
    if (paramDouble < 0.0D)
    {
      m = 1;
      d = paramDouble;
      if (m != 0) {
        d = -paramDouble;
      }
      i13 = paramStringBuffer.length();
      i = 1;
      if (m == 0) {
        break label230;
      }
      i7 = i;
      if (i10 >= 0)
      {
        paramStringBuffer.append('-');
        i7 = i;
      }
      k = paramStringBuffer.length();
      if ((!Double.isNaN(d)) && (!Double.isInfinite(d))) {
        break label258;
      }
      i5 = 1;
      label95:
      if ((i10 >= 0) && (i5 == 0)) {
        break label595;
      }
      paramFieldPosition = paramString;
      if (paramString == null) {
        paramFieldPosition = Double.toString(d);
      }
      i = paramFieldPosition.indexOf('E');
      if (i < 0) {
        break label583;
      }
      paramStringBuffer.append(paramFieldPosition);
      i2 = i + k;
      if (paramFieldPosition.charAt(i2 + 1) != '-') {
        break label264;
      }
      n = 1;
      label165:
      i1 = 0;
      if (n == 0) {
        break label270;
      }
    }
    label230:
    label258:
    label264:
    label270:
    for (int i = 2;; i = 1)
    {
      j = i2 + i;
      i = i1;
      while (j < paramStringBuffer.length())
      {
        i = i * 10 + (paramStringBuffer.charAt(j) - '0');
        j += 1;
      }
      m = 0;
      break;
      if (this.showPlus)
      {
        paramStringBuffer.append('+');
        i7 = i;
        break label69;
      }
      i7 = 0;
      break label69;
      i5 = 0;
      break label95;
      n = 0;
      break label165;
    }
    int j = i;
    if (n != 0) {
      j = -i;
    }
    paramStringBuffer.setLength(i2);
    i = j;
    int n = k;
    if (m != 0) {
      n = k + 1;
    }
    paramStringBuffer.deleteCharAt(n + 1);
    j = paramStringBuffer.length() - n;
    int k = j;
    if (j > 1)
    {
      k = j;
      if (paramStringBuffer.charAt(n + j - 1) == '0')
      {
        k = j - 1;
        paramStringBuffer.setLength(n + k);
      }
    }
    int i2 = k - i - 1;
    int i11 = i - (i6 - 1);
    int i8;
    label411:
    label422:
    int i12;
    label454:
    int i9;
    label462:
    int i3;
    int i4;
    if (i11 < 0)
    {
      i8 = -i11;
      if (i8 < 1000) {
        break label704;
      }
      i = 4;
      i1 = i;
      if (this.expDigits > i) {
        i1 = this.expDigits;
      }
      i12 = 1;
      if (this.general) {
        break label736;
      }
      j = 0;
      if (i10 >= 0) {
        break label760;
      }
      i9 = 1;
      if (!this.general)
      {
        m = k;
        i3 = i6;
        i4 = i12;
        if (i9 == 0) {}
      }
      else
      {
        i3 = k - i2;
        i = i10;
        if (i9 != 0)
        {
          if (i3 >= 7) {
            break label766;
          }
          m = i3;
          label513:
          i = m;
          if (k > m) {
            i = k;
          }
        }
        if ((!this.general) || (i3 < 0) || (i - i3 < 0)) {
          break label773;
        }
        i4 = 0;
        m = i;
      }
    }
    label583:
    label595:
    label603:
    label686:
    label704:
    label736:
    label760:
    label766:
    label773:
    do
    {
      m = n + m;
      while (paramStringBuffer.length() < m) {
        paramStringBuffer.append('0');
      }
      i = RealNum.toStringScientific(paramFieldPosition, paramStringBuffer);
      break;
      if (i6 > 0)
      {
        i = 1;
        j = i10 + i;
        i = (int)(Math.log(d) / LOG10 + 1000.0D);
        if (i != Integer.MIN_VALUE) {
          break label686;
        }
        i = 0;
      }
      for (;;)
      {
        i2 = j - i - 1;
        RealNum.toScaledInt(d, i2).format(10, paramStringBuffer);
        i = j - 1 - i2;
        n = k;
        k = j;
        break;
        i = i6;
        break label603;
        i -= 1000;
      }
      i8 = i11;
      break label411;
      if (i8 >= 100)
      {
        i = 3;
        break label422;
      }
      if (i8 >= 10)
      {
        i = 2;
        break label422;
      }
      i = 1;
      break label422;
      if (this.expDigits > 0)
      {
        j = this.expDigits + 2;
        break label454;
      }
      j = 4;
      break label454;
      i9 = 0;
      break label462;
      m = 7;
      break label513;
      m = k;
      i3 = i6;
      i4 = i12;
    } while (i9 == 0);
    if (this.width <= 0) {
      k = i;
    }
    for (;;)
    {
      m = k;
      i3 = i6;
      i4 = i12;
      if (k > 0) {
        break;
      }
      m = 1;
      i3 = i6;
      i4 = i12;
      break;
      k = this.width - i7 - i1 - 3;
      m = k;
      if (i6 < 0) {
        m = k - i6;
      }
      k = m;
      if (m > i) {
        k = i;
      }
    }
    if (m == paramStringBuffer.length())
    {
      i = 48;
      if (i < 53) {
        break label1009;
      }
    }
    label1009:
    for (i = 1;; i = 0)
    {
      k = i2;
      if (i != 0)
      {
        k = i2;
        if (addOne(paramStringBuffer, n, m)) {
          k = i2 + 1;
        }
      }
      paramStringBuffer.length();
      paramStringBuffer.setLength(m);
      k = n;
      i = m;
      if (i3 >= 0) {
        break label1015;
      }
      for (i = i3;; i = m)
      {
        m = i + 1;
        i = k;
        if (m > 0) {
          break;
        }
        paramStringBuffer.insert(n, '0');
      }
      i = paramStringBuffer.charAt(m);
      break;
    }
    label1015:
    while (n + i3 > i)
    {
      paramStringBuffer.append('0');
      i += 1;
    }
    i = k + i3;
    if (i5 != 0)
    {
      i4 = 0;
      if (i4 == 0) {
        break label1192;
      }
      paramStringBuffer.append(this.exponentChar);
      if ((this.exponentShowSign) || (i11 < 0)) {
        if (i11 < 0) {
          break label1185;
        }
      }
    }
    label1185:
    for (char c = '+';; c = '-')
    {
      paramStringBuffer.append(c);
      i2 = paramStringBuffer.length();
      paramStringBuffer.append(i8);
      k = paramStringBuffer.length();
      k = this.expDigits - (k - i2);
      m = i1;
      if (k <= 0) {
        break label1195;
      }
      for (;;)
      {
        k -= 1;
        m = i1;
        if (k < 0) {
          break;
        }
        paramStringBuffer.insert(i2, '0');
      }
      paramStringBuffer.insert(i, '.');
      break;
    }
    label1192:
    int m = 0;
    label1195:
    k = paramStringBuffer.length();
    int i1 = this.width - (k - i13);
    k = i1;
    if (i9 != 0) {
      if (i + 1 != paramStringBuffer.length())
      {
        k = i1;
        if (paramStringBuffer.charAt(i + 1) != this.exponentChar) {}
      }
      else if (this.width > 0)
      {
        k = i1;
        if (i1 <= 0) {}
      }
      else
      {
        k = i1 - 1;
        paramStringBuffer.insert(i + 1, '0');
      }
    }
    if (((k >= 0) || (this.width <= 0)) && ((i4 == 0) || (m <= this.expDigits) || (this.expDigits <= 0) || (this.overflowChar == 0)))
    {
      i = k;
      if (i3 <= 0) {
        if (k <= 0)
        {
          i = k;
          if (this.width > 0) {}
        }
        else
        {
          paramStringBuffer.insert(n, '0');
          i = k - 1;
        }
      }
      k = i;
      if (i4 == 0)
      {
        k = i;
        if (this.width > 0) {
          for (;;)
          {
            j -= 1;
            k = i;
            if (j < 0) {
              break;
            }
            paramStringBuffer.append(' ');
            i -= 1;
          }
        }
      }
      for (;;)
      {
        k -= 1;
        if (k < 0) {
          break;
        }
        paramStringBuffer.insert(i13, this.padChar);
      }
    }
    if (this.overflowChar != 0)
    {
      paramStringBuffer.setLength(i13);
      i = this.width;
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          break;
        }
        paramStringBuffer.append(this.overflowChar);
      }
    }
    return paramStringBuffer;
  }
  
  public StringBuffer format(double paramDouble, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    if (this.fracDigits < 0) {}
    for (String str = Double.toString(paramDouble);; str = null) {
      return format(paramDouble, str, paramStringBuffer, paramFieldPosition);
    }
  }
  
  public StringBuffer format(float paramFloat, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    double d = paramFloat;
    if (this.fracDigits < 0) {}
    for (String str = Float.toString(paramFloat);; str = null) {
      return format(d, str, paramStringBuffer, paramFieldPosition);
    }
  }
  
  public StringBuffer format(long paramLong, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    return format(paramLong, paramStringBuffer, paramFieldPosition);
  }
  
  public StringBuffer format(Object paramObject, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    return format(((RealNum)paramObject).doubleValue(), paramStringBuffer, paramFieldPosition);
  }
  
  public Number parse(String paramString, ParsePosition paramParsePosition)
  {
    throw new Error("ExponentialFormat.parse - not implemented");
  }
  
  public Object parseObject(String paramString, ParsePosition paramParsePosition)
  {
    throw new Error("ExponentialFormat.parseObject - not implemented");
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\ExponentialFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */