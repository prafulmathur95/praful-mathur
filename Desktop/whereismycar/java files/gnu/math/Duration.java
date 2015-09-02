package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Duration
  extends Quantity
  implements Externalizable
{
  int months;
  int nanos;
  long seconds;
  public Unit unit;
  
  public static Duration add(Duration paramDuration1, Duration paramDuration2, int paramInt)
  {
    long l1 = paramDuration1.months;
    long l2 = paramInt;
    long l3 = paramDuration2.months;
    long l4 = paramDuration1.seconds * 1000000000L + paramDuration1.nanos + paramInt * (paramDuration2.seconds * 1000000000L + paramDuration2.nanos);
    Duration localDuration = new Duration();
    localDuration.months = ((int)(l1 + l2 * l3));
    localDuration.seconds = ((int)(l4 / 1000000000L));
    localDuration.nanos = ((int)(l4 % 1000000000L));
    if ((paramDuration1.unit != paramDuration2.unit) || (paramDuration1.unit == Unit.duration)) {
      throw new ArithmeticException("cannot add these duration types");
    }
    localDuration.unit = paramDuration1.unit;
    return localDuration;
  }
  
  static void appendNanoSeconds(int paramInt, StringBuffer paramStringBuffer)
  {
    if (paramInt == 0) {
      return;
    }
    paramStringBuffer.append('.');
    int i = paramStringBuffer.length();
    paramStringBuffer.append(paramInt);
    paramInt = i + 9 - paramStringBuffer.length();
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      paramStringBuffer.insert(i, '0');
    }
    paramInt = i + 9;
    do
    {
      i = paramInt - 1;
      paramInt = i;
    } while (paramStringBuffer.charAt(i) == '0');
    paramStringBuffer.setLength(i + 1);
  }
  
  public static int compare(Duration paramDuration1, Duration paramDuration2)
  {
    long l1 = paramDuration1.months - paramDuration2.months;
    long l2 = paramDuration1.seconds * 1000000000L + paramDuration1.nanos - (paramDuration2.seconds * 1000000000L + paramDuration2.nanos);
    if ((l1 < 0L) && (l2 <= 0L)) {}
    do
    {
      return -1;
      if ((l1 > 0L) && (l2 >= 0L)) {
        return 1;
      }
      if (l1 != 0L) {
        break;
      }
    } while (l2 < 0L);
    if (l2 > 0L) {
      return 1;
    }
    return 0;
    return -2;
  }
  
  public static double div(Duration paramDuration1, Duration paramDuration2)
  {
    int i = paramDuration1.months;
    int j = paramDuration2.months;
    double d1 = paramDuration1.seconds + paramDuration1.nanos * 1.0E-9D;
    double d2 = paramDuration2.seconds + paramDuration1.nanos * 1.0E-9D;
    if ((j == 0) && (d2 == 0.0D)) {
      throw new ArithmeticException("divide duration by zero");
    }
    if (j == 0)
    {
      if (i == 0) {
        return d1 / d2;
      }
    }
    else if ((d2 == 0.0D) && (d1 == 0.0D)) {
      return i / j;
    }
    throw new ArithmeticException("divide of incompatible durations");
  }
  
  public static boolean equals(Duration paramDuration1, Duration paramDuration2)
  {
    return (paramDuration1.months == paramDuration2.months) && (paramDuration1.seconds == paramDuration2.seconds) && (paramDuration1.nanos == paramDuration2.nanos);
  }
  
  public static Duration make(int paramInt1, long paramLong, int paramInt2, Unit paramUnit)
  {
    Duration localDuration = new Duration();
    localDuration.months = paramInt1;
    localDuration.seconds = paramLong;
    localDuration.nanos = paramInt2;
    localDuration.unit = paramUnit;
    return localDuration;
  }
  
  public static Duration makeMinutes(int paramInt)
  {
    Duration localDuration = new Duration();
    localDuration.unit = Unit.second;
    localDuration.seconds = (paramInt * 60);
    return localDuration;
  }
  
  public static Duration makeMonths(int paramInt)
  {
    Duration localDuration = new Duration();
    localDuration.unit = Unit.month;
    localDuration.months = paramInt;
    return localDuration;
  }
  
  public static Duration parse(String paramString, Unit paramUnit)
  {
    Duration localDuration = valueOf(paramString, paramUnit);
    if (localDuration == null) {
      throw new IllegalArgumentException("not a valid " + paramUnit.getName() + " duration: '" + paramString + "'");
    }
    return localDuration;
  }
  
  public static Duration parseDayTimeDuration(String paramString)
  {
    return parse(paramString, Unit.second);
  }
  
  public static Duration parseDuration(String paramString)
  {
    return parse(paramString, Unit.duration);
  }
  
  public static Duration parseYearMonthDuration(String paramString)
  {
    return parse(paramString, Unit.month);
  }
  
  private static long scanPart(String paramString, int paramInt)
  {
    long l3 = -1L;
    int i = paramInt;
    long l2 = -1L;
    int j = paramString.length();
    char c;
    int k;
    long l1;
    if (i < j)
    {
      c = paramString.charAt(i);
      i += 1;
      k = Character.digit(c, 10);
      if (k < 0) {
        if (l2 < 0L) {
          l1 = paramInt << 16;
        }
      }
    }
    do
    {
      return l1;
      return l2 << 32 | i << 16 | c;
      if (l2 < 0L) {}
      for (l1 = k;; l1 = 10L * l2 + k)
      {
        l2 = l1;
        if (l1 <= 2147483647L) {
          break;
        }
        return -1L;
      }
      l1 = l3;
    } while (l2 >= 0L);
    return paramInt << 16;
  }
  
  public static Duration times(Duration paramDuration, double paramDouble)
  {
    if (paramDuration.unit == Unit.duration) {
      throw new IllegalArgumentException("cannot multiply general duration");
    }
    double d = paramDuration.months * paramDouble;
    if ((Double.isInfinite(d)) || (Double.isNaN(d))) {
      throw new ArithmeticException("overflow/NaN when multiplying a duration");
    }
    paramDouble = (paramDuration.seconds * 1000000000L + paramDuration.nanos) * paramDouble;
    Duration localDuration = new Duration();
    localDuration.months = ((int)Math.floor(0.5D + d));
    localDuration.seconds = ((int)(paramDouble / 1.0E9D));
    localDuration.nanos = ((int)(paramDouble % 1.0E9D));
    localDuration.unit = paramDuration.unit;
    return localDuration;
  }
  
  public static Duration valueOf(String paramString, Unit paramUnit)
  {
    paramString = paramString.trim();
    int j = 0;
    int i5 = paramString.length();
    int i2;
    if ((i5 < 0) && (paramString.charAt(0) == '-'))
    {
      i2 = 1;
      j = 0 + 1;
    }
    while ((j + 1 >= i5) || (paramString.charAt(j) != 'P'))
    {
      return null;
      i2 = 0;
    }
    int m = 0;
    int i3 = 0;
    int i4 = 0;
    long l4 = 0L;
    long l2 = scanPart(paramString, j + 1);
    int n = (int)l2 >> 16;
    int k = (char)(int)l2;
    if ((paramUnit == Unit.second) && ((k == 89) || (k == 77))) {
      return null;
    }
    j = k;
    long l1 = l2;
    if (k == 89)
    {
      m = (int)(l2 >> 32) * 12;
      n = (int)l2 >> 16;
      l1 = scanPart(paramString, n);
      j = (char)(int)l1;
    }
    int i1 = j;
    k = m;
    l2 = l1;
    if (j == 77)
    {
      k = (int)(m + (l1 >> 32));
      n = (int)l1 >> 16;
      l2 = scanPart(paramString, n);
      i1 = (char)(int)l2;
    }
    if ((paramUnit == Unit.month) && (n != i5)) {
      return null;
    }
    long l3 = l2;
    l1 = l4;
    if (i1 == 68)
    {
      if (paramUnit == Unit.month) {
        return null;
      }
      l1 = 86400L * (int)(l2 >> 32);
      n = (int)l2 >> 16;
      l3 = scanPart(paramString, n);
    }
    if (l3 != n << 16) {
      return null;
    }
    if (n == i5)
    {
      i1 = n;
      m = i4;
    }
    int i;
    for (;;)
    {
      if (i1 != i5)
      {
        return null;
        if (paramString.charAt(n) == 'T')
        {
          m = n + 1;
          if (m != i5) {}
        }
        else
        {
          return null;
        }
        if (paramUnit == Unit.month) {
          return null;
        }
        l4 = scanPart(paramString, m);
        n = (char)(int)l4;
        j = n;
        l2 = l4;
        l3 = l1;
        if (n == 72)
        {
          l3 = l1 + (int)(l4 >> 32) * 3600;
          m = (int)l4 >> 16;
          l2 = scanPart(paramString, m);
          j = (char)(int)l2;
        }
        n = j;
        l4 = l2;
        l1 = l3;
        if (j == 77)
        {
          l1 = l3 + (int)(l2 >> 32) * 60;
          m = (int)l2 >> 16;
          l4 = scanPart(paramString, m);
          n = (char)(int)l4;
        }
        if (n != 83)
        {
          j = m;
          l2 = l1;
          if (n != 46) {}
        }
        else
        {
          l2 = l1 + (int)(l4 >> 32);
          j = (int)l4 >> 16;
        }
        m = i4;
        i1 = j;
        l1 = l2;
        if (n == 46)
        {
          m = i4;
          i1 = j;
          l1 = l2;
          if (j + 1 < i5)
          {
            m = i4;
            i1 = j;
            l1 = l2;
            if (Character.digit(paramString.charAt(j), 10) >= 0)
            {
              m = 0;
              i1 = j;
              j = m;
              m = i3;
              i3 = n;
              if (i1 >= i5) {
                break label805;
              }
              i3 = i1 + 1;
              i = paramString.charAt(i1);
              i1 = Character.digit(i, 10);
              if (i1 < 0)
              {
                n = j;
                j = i3;
                i3 = i;
              }
            }
          }
        }
      }
    }
    for (;;)
    {
      if (n < 9)
      {
        m *= 10;
        n += 1;
        continue;
        if (j < 9) {
          n = m * 10 + i1;
        }
        for (;;)
        {
          j += 1;
          i1 = i3;
          i3 = i;
          m = n;
          break;
          n = m;
          if (j == 9)
          {
            n = m;
            if (i1 >= 5) {
              n = m + 1;
            }
          }
        }
      }
      else
      {
        i1 = j;
        l1 = l2;
        if (i3 == 83) {
          break;
        }
        return null;
        paramString = new Duration();
        n = k;
        j = m;
        l2 = l1;
        if (i2 != 0)
        {
          n = -k;
          l2 = -l1;
          j = -m;
        }
        paramString.months = n;
        paramString.seconds = l2;
        paramString.nanos = j;
        paramString.unit = paramUnit;
        return paramString;
        label805:
        n = j;
        j = i1;
      }
    }
  }
  
  public Numeric add(Object paramObject, int paramInt)
  {
    if ((paramObject instanceof Duration)) {
      return add(this, (Duration)paramObject, paramInt);
    }
    if (((paramObject instanceof DateTime)) && (paramInt == 1)) {
      return DateTime.add((DateTime)paramObject, this, 1);
    }
    throw new IllegalArgumentException();
  }
  
  public int compare(Object paramObject)
  {
    if ((paramObject instanceof Duration)) {
      return compare(this, (Duration)paramObject);
    }
    throw new IllegalArgumentException();
  }
  
  public Numeric div(Object paramObject)
  {
    if ((paramObject instanceof RealNum))
    {
      double d = ((RealNum)paramObject).doubleValue();
      if ((d == 0.0D) || (Double.isNaN(d))) {
        throw new ArithmeticException("divide of duration by 0 or NaN");
      }
      return times(this, 1.0D / d);
    }
    if ((paramObject instanceof Duration)) {
      return new DFloNum(div(this, (Duration)paramObject));
    }
    return ((Numeric)paramObject).divReversed(this);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof Duration))) {
      return false;
    }
    return equals(this, (Duration)paramObject);
  }
  
  public int getDays()
  {
    return (int)(this.seconds / 86400L);
  }
  
  public int getHours()
  {
    return (int)(this.seconds / 3600L % 24L);
  }
  
  public int getMinutes()
  {
    return (int)(this.seconds / 60L % 60L);
  }
  
  public int getMonths()
  {
    return this.months % 12;
  }
  
  public long getNanoSeconds()
  {
    return this.seconds * 1000000000L + this.nanos;
  }
  
  public int getNanoSecondsOnly()
  {
    return this.nanos;
  }
  
  public int getSecondsOnly()
  {
    return (int)(this.seconds % 60L);
  }
  
  public long getTotalMinutes()
  {
    return this.seconds / 60L;
  }
  
  public int getTotalMonths()
  {
    return this.months;
  }
  
  public long getTotalSeconds()
  {
    return this.seconds;
  }
  
  public int getYears()
  {
    return this.months / 12;
  }
  
  public int hashCode()
  {
    return this.months ^ (int)this.seconds ^ this.nanos;
  }
  
  public boolean isExact()
  {
    return false;
  }
  
  public boolean isZero()
  {
    return (this.months == 0) && (this.seconds == 0L) && (this.nanos == 0);
  }
  
  public Numeric mul(Object paramObject)
  {
    if ((paramObject instanceof RealNum)) {
      return times(this, ((RealNum)paramObject).doubleValue());
    }
    return ((Numeric)paramObject).mulReversed(this);
  }
  
  public Numeric mulReversed(Numeric paramNumeric)
  {
    if (!(paramNumeric instanceof RealNum)) {
      throw new IllegalArgumentException();
    }
    return times(this, ((RealNum)paramNumeric).doubleValue());
  }
  
  public Complex number()
  {
    throw new Error("number needs to be implemented!");
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.months = paramObjectInput.readInt();
    this.seconds = paramObjectInput.readLong();
    this.nanos = paramObjectInput.readInt();
    this.unit = ((Unit)paramObjectInput.readObject());
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int n = this.months;
    long l1 = this.seconds;
    int m = this.nanos;
    int k;
    if ((n < 0) || (l1 < 0L) || (m < 0))
    {
      k = 1;
      int i = n;
      int j = m;
      long l2 = l1;
      if (k != 0)
      {
        i = -n;
        l2 = -l1;
        j = -m;
        localStringBuffer.append('-');
      }
      localStringBuffer.append('P');
      m = i / 12;
      k = i;
      if (m != 0)
      {
        localStringBuffer.append(m);
        localStringBuffer.append('Y');
        k = i - m * 12;
      }
      if (k != 0)
      {
        localStringBuffer.append(k);
        localStringBuffer.append('M');
      }
      long l3 = l2 / 86400L;
      l1 = l2;
      if (l3 != 0L)
      {
        localStringBuffer.append(l3);
        localStringBuffer.append('D');
        l1 = l2 - 86400L * l3;
      }
      if ((l1 == 0L) && (j == 0)) {
        break label340;
      }
      localStringBuffer.append('T');
      l3 = l1 / 3600L;
      l2 = l1;
      if (l3 != 0L)
      {
        localStringBuffer.append(l3);
        localStringBuffer.append('H');
        l2 = l1 - 3600L * l3;
      }
      l3 = l2 / 60L;
      l1 = l2;
      if (l3 != 0L)
      {
        localStringBuffer.append(l3);
        localStringBuffer.append('M');
        l1 = l2 - 60L * l3;
      }
      if ((l1 != 0L) || (j != 0))
      {
        localStringBuffer.append(l1);
        appendNanoSeconds(j, localStringBuffer);
        localStringBuffer.append('S');
      }
    }
    label340:
    while (localStringBuffer.length() != 1)
    {
      return localStringBuffer.toString();
      k = 0;
      break;
    }
    if (this.unit == Unit.month) {}
    for (String str = "0M";; str = "T0S")
    {
      localStringBuffer.append(str);
      break;
    }
  }
  
  public Unit unit()
  {
    return this.unit;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeInt(this.months);
    paramObjectOutput.writeLong(this.seconds);
    paramObjectOutput.writeInt(this.nanos);
    paramObjectOutput.writeObject(this.unit);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\Duration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */