package gnu.math;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class FixedRealFormat
  extends Format
{
  private int d;
  private int i;
  public boolean internalPad;
  public char overflowChar;
  public char padChar;
  public int scale;
  public boolean showPlus;
  public int width;
  
  private void format(StringBuffer paramStringBuffer, FieldPosition paramFieldPosition, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    int j = getMinimumIntegerDigits();
    if ((paramInt2 >= 0) && (paramInt2 > j)) {}
    for (int k = 0;; k = j - paramInt2)
    {
      j = k;
      if (paramInt2 + k <= 0) {
        if (this.width > 0)
        {
          j = k;
          if (this.width <= paramInt3 + 1 + paramInt4) {}
        }
        else
        {
          j = k + 1;
        }
      }
      k = this.width - (paramInt4 + paramInt1 + j + 1);
      for (;;)
      {
        j -= 1;
        if (j < 0) {
          break;
        }
        paramStringBuffer.insert(paramInt5 + paramInt4, '0');
      }
    }
    if (k >= 0)
    {
      paramInt1 = paramInt5;
      paramInt2 = k;
      if (this.internalPad)
      {
        paramInt1 = paramInt5;
        paramInt2 = k;
        if (paramInt4 > 0)
        {
          paramInt1 = paramInt5 + 1;
          paramInt2 = k;
        }
      }
      for (;;)
      {
        paramInt2 -= 1;
        if (paramInt2 < 0) {
          break;
        }
        paramStringBuffer.insert(paramInt1, this.padChar);
      }
    }
    if (this.overflowChar != 0)
    {
      paramStringBuffer.setLength(paramInt5);
      this.i = this.width;
      for (;;)
      {
        paramInt1 = this.i - 1;
        this.i = paramInt1;
        if (paramInt1 < 0) {
          break;
        }
        paramStringBuffer.append(this.overflowChar);
      }
    }
    paramStringBuffer.insert(paramStringBuffer.length() - paramInt3, '.');
  }
  
  public StringBuffer format(double paramDouble, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    if ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))) {
      return paramStringBuffer.append(paramDouble);
    }
    if (getMaximumFractionDigits() >= 0)
    {
      format(DFloNum.toExact(paramDouble), paramStringBuffer, paramFieldPosition);
      return paramStringBuffer;
    }
    int i1;
    int i7;
    int i2;
    if (paramDouble < 0.0D)
    {
      i1 = 1;
      paramDouble = -paramDouble;
      i7 = paramStringBuffer.length();
      i2 = 1;
      if (i1 == 0) {
        break label282;
      }
      paramStringBuffer.append('-');
    }
    Object localObject2;
    for (;;)
    {
      localObject2 = Double.toString(paramDouble);
      k = this.scale;
      n = ((String)localObject2).indexOf('E');
      j = k;
      localObject1 = localObject2;
      if (n >= 0)
      {
        m = n + 1;
        j = m;
        if (((String)localObject2).charAt(m) == '+') {
          j = m + 1;
        }
        j = k + Integer.parseInt(((String)localObject2).substring(j));
        localObject1 = ((String)localObject2).substring(0, n);
      }
      k = ((String)localObject1).indexOf('.');
      n = ((String)localObject1).length();
      m = j;
      localObject2 = localObject1;
      if (k >= 0)
      {
        m = j - (n - k - 1);
        localObject2 = ((String)localObject1).substring(0, k) + ((String)localObject1).substring(k + 1);
      }
      n = ((String)localObject2).length();
      k = 0;
      while ((k < n - 1) && (((String)localObject2).charAt(k) == '0')) {
        k += 1;
      }
      i1 = 0;
      break;
      label282:
      if (this.showPlus) {
        paramStringBuffer.append('+');
      } else {
        i2 = 0;
      }
    }
    int j = n;
    Object localObject1 = localObject2;
    if (k > 0)
    {
      localObject1 = ((String)localObject2).substring(k);
      j = n - k;
    }
    int k = j + m;
    if (this.width > 0)
    {
      while (k < 0)
      {
        paramStringBuffer.append('0');
        k += 1;
        j += 1;
      }
      n = this.width - i2 - 1 - k;
      i3 = n;
      if (n < 0) {
        i3 = 0;
      }
      paramStringBuffer.append((String)localObject1);
      while (m > 0)
      {
        paramStringBuffer.append('0');
        m -= 1;
        j += 1;
      }
    }
    if (j > 16) {}
    for (int n = 16;; n = j)
    {
      n -= k;
      break;
    }
    int i8 = i7 + i2;
    j = i8 + k + i3;
    int m = paramStringBuffer.length();
    label511:
    int i4;
    if (j >= m)
    {
      j = m;
      m = 48;
      if (m < 53) {
        break label567;
      }
      i3 = 1;
      if (i3 == 0) {
        break label573;
      }
      m = 57;
      i4 = j;
    }
    for (;;)
    {
      if ((i4 <= i8 + k) || (paramStringBuffer.charAt(i4 - 1) != m)) {
        break label584;
      }
      i4 -= 1;
      continue;
      m = paramStringBuffer.charAt(j);
      break;
      label567:
      i3 = 0;
      break label511;
      label573:
      m = 48;
      i4 = j;
    }
    label584:
    int i5 = i4 - i8;
    int i6 = i5 - k;
    m = i5;
    n = k;
    j = i6;
    if (i3 != 0)
    {
      m = i5;
      n = k;
      j = i6;
      if (ExponentialFormat.addOne(paramStringBuffer, i8, i4))
      {
        n = k + 1;
        j = 0;
        m = n;
      }
    }
    int i3 = m;
    k = j;
    if (j == 0) {
      if (this.width > 0)
      {
        i3 = m;
        k = j;
        if (i2 + n + 1 >= this.width) {}
      }
      else
      {
        k = 1;
        i3 = m + 1;
        paramStringBuffer.insert(i8 + n, '0');
      }
    }
    paramStringBuffer.setLength(i8 + i3);
    if (i1 != 0) {}
    for (j = 1;; j = 0)
    {
      format(paramStringBuffer, paramFieldPosition, i3, n, k, j, i7);
      return paramStringBuffer;
    }
  }
  
  public StringBuffer format(long paramLong, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    format(IntNum.make(paramLong), paramStringBuffer, paramFieldPosition);
    return paramStringBuffer;
  }
  
  public StringBuffer format(Object paramObject, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    RealNum localRealNum2 = RealNum.asRealNumOrNull(paramObject);
    RealNum localRealNum1 = localRealNum2;
    if (localRealNum2 == null)
    {
      if ((paramObject instanceof Complex))
      {
        paramObject = paramObject.toString();
        int j = this.width - ((String)paramObject).length();
        for (;;)
        {
          j -= 1;
          if (j < 0) {
            break;
          }
          paramStringBuffer.append(' ');
        }
        paramStringBuffer.append((String)paramObject);
        return paramStringBuffer;
      }
      localRealNum1 = (RealNum)paramObject;
    }
    return format(localRealNum1.doubleValue(), paramStringBuffer, paramFieldPosition);
  }
  
  public void format(RealNum paramRealNum, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    if ((paramRealNum instanceof RatNum))
    {
      int k = getMaximumFractionDigits();
      if (k >= 0)
      {
        RatNum localRatNum = (RatNum)paramRealNum;
        boolean bool = localRatNum.isNegative();
        paramRealNum = localRatNum;
        if (bool) {
          paramRealNum = localRatNum.rneg();
        }
        int m = paramStringBuffer.length();
        int j = 1;
        if (bool) {
          paramStringBuffer.append('-');
        }
        for (;;)
        {
          paramRealNum = RealNum.toScaledInt(paramRealNum, this.scale + k).toString();
          paramStringBuffer.append(paramRealNum);
          int n = paramRealNum.length();
          format(paramStringBuffer, paramFieldPosition, n, n - k, k, j, m);
          return;
          if (this.showPlus) {
            paramStringBuffer.append('+');
          } else {
            j = 0;
          }
        }
      }
    }
    format(paramRealNum.doubleValue(), paramStringBuffer, paramFieldPosition);
  }
  
  public int getMaximumFractionDigits()
  {
    return this.d;
  }
  
  public int getMinimumIntegerDigits()
  {
    return this.i;
  }
  
  public Number parse(String paramString, ParsePosition paramParsePosition)
  {
    throw new Error("RealFixedFormat.parse - not implemented");
  }
  
  public Object parseObject(String paramString, ParsePosition paramParsePosition)
  {
    throw new Error("RealFixedFormat.parseObject - not implemented");
  }
  
  public void setMaximumFractionDigits(int paramInt)
  {
    this.d = paramInt;
  }
  
  public void setMinimumIntegerDigits(int paramInt)
  {
    this.i = paramInt;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\FixedRealFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */