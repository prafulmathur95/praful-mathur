package gnu.math;

import java.math.BigDecimal;

public abstract class RealNum
  extends Complex
  implements Comparable
{
  public static RealNum add(RealNum paramRealNum1, RealNum paramRealNum2, int paramInt)
  {
    return (RealNum)paramRealNum1.add(paramRealNum2, paramInt);
  }
  
  public static RealNum asRealNumOrNull(Object paramObject)
  {
    if ((paramObject instanceof RealNum)) {
      return (RealNum)paramObject;
    }
    if (((paramObject instanceof Float)) || ((paramObject instanceof Double))) {
      return new DFloNum(((Number)paramObject).doubleValue());
    }
    return RatNum.asRatNumOrNull(paramObject);
  }
  
  public static RealNum divide(RealNum paramRealNum1, RealNum paramRealNum2)
  {
    return (RealNum)paramRealNum1.div(paramRealNum2);
  }
  
  public static RealNum times(RealNum paramRealNum1, RealNum paramRealNum2)
  {
    return (RealNum)paramRealNum1.mul(paramRealNum2);
  }
  
  public static IntNum toExactInt(double paramDouble)
  {
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble))) {
      throw new ArithmeticException("cannot convert " + paramDouble + " to exact integer");
    }
    long l = Double.doubleToLongBits(paramDouble);
    int i;
    int j;
    if (l < 0L)
    {
      i = 1;
      j = (int)(l >> 52) & 0x7FF;
      l &= 0xFFFFFFFFFFFFF;
      if (j != 0) {
        break label118;
      }
      l <<= 1;
    }
    for (;;)
    {
      if (j <= 1075)
      {
        j = 1075 - j;
        if (j > 53)
        {
          return IntNum.zero();
          i = 0;
          break;
          label118:
          l |= 0x10000000000000;
          continue;
        }
        l >>= j;
        if (i != 0) {
          l = -l;
        }
        for (;;)
        {
          return IntNum.make(l);
        }
      }
    }
    if (i != 0) {
      l = -l;
    }
    for (;;)
    {
      return IntNum.shift(IntNum.make(l), j - 1075);
    }
  }
  
  public static IntNum toExactInt(double paramDouble, int paramInt)
  {
    return toExactInt(toInt(paramDouble, paramInt));
  }
  
  public static double toInt(double paramDouble, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return paramDouble;
    case 1: 
      return Math.floor(paramDouble);
    case 2: 
      return Math.ceil(paramDouble);
    case 3: 
      if (paramDouble < 0.0D) {
        return Math.ceil(paramDouble);
      }
      return Math.floor(paramDouble);
    }
    return Math.rint(paramDouble);
  }
  
  public static IntNum toScaledInt(double paramDouble, int paramInt)
  {
    return toScaledInt(DFloNum.toExact(paramDouble), paramInt);
  }
  
  public static IntNum toScaledInt(RatNum paramRatNum, int paramInt)
  {
    Object localObject = paramRatNum;
    int i;
    IntNum localIntNum;
    if (paramInt != 0)
    {
      localObject = IntNum.ten();
      if (paramInt >= 0) {
        break label57;
      }
      i = -paramInt;
      localIntNum = IntNum.power((IntNum)localObject, i);
      localObject = paramRatNum.numerator();
      paramRatNum = paramRatNum.denominator();
      if (paramInt < 0) {
        break label63;
      }
      localObject = IntNum.times((IntNum)localObject, localIntNum);
    }
    for (;;)
    {
      localObject = RatNum.make((IntNum)localObject, paramRatNum);
      return ((RatNum)localObject).toExactInt(4);
      label57:
      i = paramInt;
      break;
      label63:
      paramRatNum = IntNum.times(paramRatNum, localIntNum);
    }
  }
  
  public static String toStringDecimal(String paramString)
  {
    int m = paramString.indexOf('E');
    if (m < 0) {}
    int n;
    do
    {
      return paramString;
      n = paramString.length();
      i = paramString.charAt(n - 1);
    } while ((i == 121) || (i == 78));
    StringBuffer localStringBuffer = new StringBuffer(n + 10);
    if (paramString.charAt(0) == '-') {}
    for (int j = 1; paramString.charAt(m + 1) != '-'; j = 0) {
      throw new Error("not implemented: toStringDecimal given non-negative exponent: " + paramString);
    }
    int i = 0;
    int k = m + 2;
    while (k < n)
    {
      i = i * 10 + (paramString.charAt(k) - '0');
      k += 1;
    }
    if (j != 0) {
      localStringBuffer.append('-');
    }
    localStringBuffer.append("0.");
    for (;;)
    {
      i -= 1;
      if (i <= 0) {
        break;
      }
      localStringBuffer.append('0');
    }
    i = 0;
    for (;;)
    {
      j = i + 1;
      char c = paramString.charAt(i);
      if (c != 'E')
      {
        if (c != '-')
        {
          i = 1;
          label217:
          if (c == '.') {
            break label263;
          }
        }
        label263:
        for (k = 1;; k = 0)
        {
          if (((k & i) == 0) || ((c == '0') && (j >= m))) {
            break label274;
          }
          localStringBuffer.append(c);
          i = j;
          break;
          i = 0;
          break label217;
        }
      }
      return localStringBuffer.toString();
      label274:
      i = j;
    }
  }
  
  public static int toStringScientific(String paramString, StringBuffer paramStringBuffer)
  {
    if (paramString.charAt(0) == '-')
    {
      j = 1;
      if (j != 0) {
        paramStringBuffer.append('-');
      }
      if (j == 0) {
        break label110;
      }
    }
    int n;
    int k;
    label67:
    int m;
    label110:
    for (int i = 1;; i = 0)
    {
      n = paramString.length();
      if (paramString.charAt(i) != '0') {
        break label242;
      }
      k = i;
      if (k != n) {
        break label115;
      }
      paramStringBuffer.append("0");
      i = 0;
      k = paramStringBuffer.length();
      for (j = -1;; j = k)
      {
        k -= 1;
        m = paramStringBuffer.charAt(k);
        if (m != 48) {
          break;
        }
      }
      j = 0;
      break;
    }
    label115:
    int j = k + 1;
    char c = paramString.charAt(k);
    if ((c >= '0') && (c <= '9') && ((c != '0') || (j == n)))
    {
      paramStringBuffer.append(c);
      paramStringBuffer.append('.');
      if (c == '0') {}
      for (i = 0;; i = i - j + 2)
      {
        if (j != n) {
          break label368;
        }
        paramStringBuffer.append('0');
        break;
      }
    }
    for (;;)
    {
      j = i;
      m = k;
      if (k < n)
      {
        paramStringBuffer.append(paramString.charAt(k));
        k += 1;
        continue;
        k = j;
        break;
        label242:
        if (j != 0) {}
        for (j = 2;; j = 1)
        {
          k = n - j - n + paramString.indexOf('.');
          j = i + 1;
          paramStringBuffer.append(paramString.charAt(i));
          paramStringBuffer.append('.');
          i = j;
          for (;;)
          {
            j = k;
            m = i;
            if (i >= n) {
              break;
            }
            c = paramString.charAt(i);
            if (c != '.') {
              paramStringBuffer.append(c);
            }
            i += 1;
          }
        }
        if (m == 46) {
          j = k + 2;
        }
        if (j >= 0) {
          paramStringBuffer.setLength(j);
        }
        return i;
      }
      i = j;
      break label67;
      label368:
      k = j;
    }
  }
  
  public static String toStringScientific(double paramDouble)
  {
    return toStringScientific(Double.toString(paramDouble));
  }
  
  public static String toStringScientific(float paramFloat)
  {
    return toStringScientific(Float.toString(paramFloat));
  }
  
  public static String toStringScientific(String paramString)
  {
    if (paramString.indexOf('E') >= 0) {}
    int j;
    do
    {
      return paramString;
      i = paramString.length();
      j = paramString.charAt(i - 1);
    } while ((j == 121) || (j == 78));
    StringBuffer localStringBuffer = new StringBuffer(i + 10);
    int i = toStringScientific(paramString, localStringBuffer);
    localStringBuffer.append('E');
    localStringBuffer.append(i);
    return localStringBuffer.toString();
  }
  
  public Numeric abs()
  {
    Object localObject = this;
    if (isNegative()) {
      localObject = neg();
    }
    return (Numeric)localObject;
  }
  
  public abstract Numeric add(Object paramObject, int paramInt);
  
  public BigDecimal asBigDecimal()
  {
    return new BigDecimal(doubleValue());
  }
  
  public int compareTo(Object paramObject)
  {
    return compare(paramObject);
  }
  
  public abstract Numeric div(Object paramObject);
  
  public Complex exp()
  {
    return new DFloNum(Math.exp(doubleValue()));
  }
  
  public final RealNum im()
  {
    return IntNum.zero();
  }
  
  public abstract boolean isNegative();
  
  public boolean isZero()
  {
    return sign() == 0;
  }
  
  public Complex log()
  {
    double d = doubleValue();
    if (d < 0.0D) {
      return DComplex.log(d, 0.0D);
    }
    return new DFloNum(Math.log(d));
  }
  
  public RealNum max(RealNum paramRealNum)
  {
    int i;
    if ((isExact()) && (paramRealNum.isExact()))
    {
      i = 1;
      if (!grt(paramRealNum)) {
        break label60;
      }
      paramRealNum = this;
    }
    label60:
    for (;;)
    {
      Object localObject = paramRealNum;
      if (i == 0)
      {
        localObject = paramRealNum;
        if (paramRealNum.isExact()) {
          localObject = new DFloNum(paramRealNum.doubleValue());
        }
      }
      return (RealNum)localObject;
      i = 0;
      break;
    }
  }
  
  public RealNum min(RealNum paramRealNum)
  {
    int i;
    if ((isExact()) && (paramRealNum.isExact()))
    {
      i = 1;
      if (!grt(paramRealNum)) {
        break label58;
      }
    }
    for (;;)
    {
      Object localObject = paramRealNum;
      if (i == 0)
      {
        localObject = paramRealNum;
        if (paramRealNum.isExact()) {
          localObject = new DFloNum(paramRealNum.doubleValue());
        }
      }
      return (RealNum)localObject;
      i = 0;
      break;
      label58:
      paramRealNum = this;
    }
  }
  
  public abstract Numeric mul(Object paramObject);
  
  public final RealNum re()
  {
    return this;
  }
  
  public RealNum rneg()
  {
    return (RealNum)neg();
  }
  
  public abstract int sign();
  
  public final Complex sin()
  {
    return new DFloNum(Math.sin(doubleValue()));
  }
  
  public final Complex sqrt()
  {
    double d = doubleValue();
    if (d >= 0.0D) {
      return new DFloNum(Math.sqrt(d));
    }
    return DComplex.sqrt(d, 0.0D);
  }
  
  public RatNum toExact()
  {
    return DFloNum.toExact(doubleValue());
  }
  
  public IntNum toExactInt(int paramInt)
  {
    return toExactInt(doubleValue(), paramInt);
  }
  
  public RealNum toInexact()
  {
    Object localObject = this;
    if (isExact()) {
      localObject = new DFloNum(doubleValue());
    }
    return (RealNum)localObject;
  }
  
  public RealNum toInt(int paramInt)
  {
    return new DFloNum(toInt(doubleValue(), paramInt));
  }
  
  public IntNum toScaledInt(int paramInt)
  {
    return toScaledInt(toExact(), paramInt);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\RealNum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */