package gnu.math;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTime
  extends Quantity
  implements Cloneable
{
  public static final int DATE_MASK = 14;
  static final int DAY_COMPONENT = 3;
  public static final int DAY_MASK = 8;
  public static TimeZone GMT = TimeZone.getTimeZone("GMT");
  static final int HOURS_COMPONENT = 4;
  public static final int HOURS_MASK = 16;
  static final int MINUTES_COMPONENT = 5;
  public static final int MINUTES_MASK = 32;
  static final int MONTH_COMPONENT = 2;
  public static final int MONTH_MASK = 4;
  static final int SECONDS_COMPONENT = 6;
  public static final int SECONDS_MASK = 64;
  static final int TIMEZONE_COMPONENT = 7;
  public static final int TIMEZONE_MASK = 128;
  public static final int TIME_MASK = 112;
  static final int YEAR_COMPONENT = 1;
  public static final int YEAR_MASK = 2;
  private static final Date minDate = new Date(Long.MIN_VALUE);
  GregorianCalendar calendar;
  int mask;
  int nanoSeconds;
  Unit unit = Unit.date;
  
  public DateTime(int paramInt)
  {
    this.calendar = new GregorianCalendar();
    this.calendar.setGregorianChange(minDate);
    this.calendar.clear();
    this.mask = paramInt;
  }
  
  public DateTime(int paramInt, GregorianCalendar paramGregorianCalendar)
  {
    this.calendar = paramGregorianCalendar;
    this.mask = paramInt;
  }
  
  public static DateTime add(DateTime paramDateTime, Duration paramDuration, int paramInt)
  {
    if ((paramDuration.unit == Unit.duration) || ((paramDuration.unit == Unit.month) && ((paramDateTime.mask & 0xE) != 14))) {
      throw new IllegalArgumentException("invalid date/time +/- duration combinatuion");
    }
    DateTime localDateTime = new DateTime(paramDateTime.mask, (GregorianCalendar)paramDateTime.calendar.clone());
    int n;
    int k;
    int j;
    if (paramDuration.months != 0)
    {
      i = localDateTime.getYear() * 12 + localDateTime.calendar.get(2) + paramDuration.months * paramInt;
      n = localDateTime.calendar.get(5);
      if (i < 12) {
        break label299;
      }
      k = i / 12;
      j = i % 12;
      localDateTime.calendar.set(0, 1);
    }
    for (int i = daysInMonth(j, k);; i = daysInMonth(j, 1))
    {
      int m = n;
      if (n > i) {
        m = i;
      }
      localDateTime.calendar.set(k, j, m);
      long l2 = paramDateTime.nanoSeconds + paramInt * (paramDuration.seconds * 1000000000L + paramDuration.nanos);
      if (l2 != 0L)
      {
        long l1 = l2;
        if ((paramDateTime.mask & 0x70) == 0)
        {
          l3 = l2 % 86400000000000L;
          l1 = l3;
          if (l3 < 0L) {
            l1 = l3 + 86400000000000L;
          }
          l1 = l2 - l1;
        }
        l2 = localDateTime.calendar.getTimeInMillis();
        long l3 = l1 / 1000000000L;
        localDateTime.calendar.setTimeInMillis(l2 + l3 * 1000L);
        localDateTime.nanoSeconds = ((int)(l1 % 1000000000L));
      }
      return localDateTime;
      label299:
      i = 11 - i;
      localDateTime.calendar.set(0, 0);
      k = i / 12 + 1;
      j = 11 - i % 12;
    }
  }
  
  public static DateTime addMinutes(DateTime paramDateTime, int paramInt)
  {
    return addSeconds(paramDateTime, paramInt * 60);
  }
  
  public static DateTime addSeconds(DateTime paramDateTime, int paramInt)
  {
    DateTime localDateTime = new DateTime(paramDateTime.mask, (GregorianCalendar)paramDateTime.calendar.clone());
    long l1 = paramInt * 1000000000L;
    if (l1 != 0L)
    {
      l1 += paramDateTime.nanoSeconds;
      long l2 = paramDateTime.calendar.getTimeInMillis();
      long l3 = l1 / 1000000L;
      localDateTime.calendar.setTimeInMillis(l2 + l3);
      localDateTime.nanoSeconds = ((int)(l1 % 1000000L));
    }
    return localDateTime;
  }
  
  private static void append(int paramInt1, StringBuffer paramStringBuffer, int paramInt2)
  {
    int i = paramStringBuffer.length();
    paramStringBuffer.append(paramInt1);
    paramInt1 = i + paramInt2 - paramStringBuffer.length();
    for (;;)
    {
      paramInt1 -= 1;
      if (paramInt1 < 0) {
        break;
      }
      paramStringBuffer.insert(i, '0');
    }
  }
  
  public static int compare(DateTime paramDateTime1, DateTime paramDateTime2)
  {
    long l3 = paramDateTime1.calendar.getTimeInMillis();
    long l5 = paramDateTime2.calendar.getTimeInMillis();
    long l4 = l3;
    long l2 = l5;
    if (((paramDateTime1.mask | paramDateTime2.mask) & 0xE) == 0)
    {
      l1 = l3;
      if (l3 < 0L) {
        l1 = l3 + 86400000L;
      }
      l4 = l1;
      l2 = l5;
      if (l5 < 0L)
      {
        l2 = l5 + 86400000L;
        l4 = l1;
      }
    }
    int j = paramDateTime1.nanoSeconds;
    int i = paramDateTime2.nanoSeconds;
    long l1 = l4 + j / 1000000;
    l2 += i / 1000000;
    j %= 1000000;
    i %= 1000000;
    if (l1 < l2) {
      return -1;
    }
    if (l1 > l2) {
      return 1;
    }
    if (j < i) {
      return -1;
    }
    if (j > i) {
      return 1;
    }
    return 0;
  }
  
  public static int daysInMonth(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    case 2: 
    case 4: 
    case 6: 
    case 7: 
    case 9: 
    default: 
      return 31;
    case 3: 
    case 5: 
    case 8: 
    case 10: 
      return 30;
    }
    if (isLeapYear(paramInt2)) {
      return 29;
    }
    return 28;
  }
  
  public static boolean isLeapYear(int paramInt)
  {
    return (paramInt % 4 == 0) && ((paramInt % 100 != 0) || (paramInt % 400 == 0));
  }
  
  public static TimeZone minutesToTimeZone(int paramInt)
  {
    if (paramInt == 0) {
      return GMT;
    }
    StringBuffer localStringBuffer = new StringBuffer("GMT");
    toStringZone(paramInt, localStringBuffer);
    return TimeZone.getTimeZone(localStringBuffer.toString());
  }
  
  public static DateTime parse(String paramString, int paramInt)
  {
    int j = 1;
    DateTime localDateTime = new DateTime(paramInt);
    paramString = paramString.trim();
    int m = paramString.length();
    int i = 0;
    int k;
    if ((paramInt & 0xE) != 0)
    {
      k = 1;
      if ((paramInt & 0x70) == 0) {
        break label147;
      }
      label42:
      if (k != 0)
      {
        paramInt = localDateTime.parseDate(paramString, 0, paramInt);
        i = paramInt;
        if (j != 0) {
          if ((paramInt >= 0) && (paramInt < m) && (paramString.charAt(paramInt) == 'T')) {
            break label153;
          }
        }
      }
    }
    label147:
    label153:
    for (i = -1;; i = paramInt + 1)
    {
      paramInt = i;
      if (j != 0) {
        paramInt = localDateTime.parseTime(paramString, i);
      }
      if (localDateTime.parseZone(paramString, paramInt) == m) {
        return localDateTime;
      }
      throw new NumberFormatException("Unrecognized date/time '" + paramString + '\'');
      k = 0;
      break;
      j = 0;
      break label42;
    }
    return localDateTime;
  }
  
  private static int parseDigits(String paramString, int paramInt)
  {
    int i = paramInt;
    paramInt = -1;
    int k = paramString.length();
    int j;
    if (i < k)
    {
      j = Character.digit(paramString.charAt(i), 10);
      if (j >= 0) {}
    }
    else
    {
      if (paramInt >= 0) {
        break label69;
      }
      return i;
    }
    if (paramInt > 20000) {
      return 0;
    }
    if (paramInt < 0) {}
    for (paramInt = j;; paramInt = paramInt * 10 + j)
    {
      i += 1;
      break;
    }
    label69:
    return i | paramInt << 16;
  }
  
  public static Duration sub(DateTime paramDateTime1, DateTime paramDateTime2)
  {
    long l1 = paramDateTime1.calendar.getTimeInMillis();
    long l2 = paramDateTime2.calendar.getTimeInMillis();
    int i = paramDateTime1.nanoSeconds;
    int j = paramDateTime2.nanoSeconds;
    long l3 = i / 1000000;
    long l4 = j / 1000000;
    i = j % 1000000;
    l1 = l1 + l3 - (l2 + l4);
    l2 = l1 / 1000L;
    i = (int)(l1 % 1000L * 1000000L + i - i);
    return Duration.make(0, l2 + i / 1000000000, i % 1000000000, Unit.second);
  }
  
  public static void toStringZone(int paramInt, StringBuffer paramStringBuffer)
  {
    if (paramInt == 0)
    {
      paramStringBuffer.append('Z');
      return;
    }
    if (paramInt < 0)
    {
      paramStringBuffer.append('-');
      paramInt = -paramInt;
    }
    for (;;)
    {
      append(paramInt / 60, paramStringBuffer, 2);
      paramStringBuffer.append(':');
      append(paramInt % 60, paramStringBuffer, 2);
      return;
      paramStringBuffer.append('+');
    }
  }
  
  public Numeric add(Object paramObject, int paramInt)
  {
    if ((paramObject instanceof Duration)) {
      return add(this, (Duration)paramObject, paramInt);
    }
    if (((paramObject instanceof DateTime)) && (paramInt == -1)) {
      return sub(this, (DateTime)paramObject);
    }
    throw new IllegalArgumentException();
  }
  
  public Numeric addReversed(Numeric paramNumeric, int paramInt)
  {
    if (((paramNumeric instanceof Duration)) && (paramInt == 1)) {
      return add(this, (Duration)paramNumeric, paramInt);
    }
    throw new IllegalArgumentException();
  }
  
  public DateTime adjustTimezone(int paramInt)
  {
    DateTime localDateTime = new DateTime(this.mask, (GregorianCalendar)this.calendar.clone());
    if (paramInt == 0) {}
    for (Object localObject = GMT;; localObject = TimeZone.getTimeZone(((StringBuffer)localObject).toString()))
    {
      localDateTime.calendar.setTimeZone((TimeZone)localObject);
      if ((localDateTime.mask & 0x80) == 0) {
        break;
      }
      long l = this.calendar.getTimeInMillis();
      localDateTime.calendar.setTimeInMillis(l);
      if ((this.mask & 0x70) == 0)
      {
        localDateTime.calendar.set(11, 0);
        localDateTime.calendar.set(12, 0);
        localDateTime.calendar.set(13, 0);
        localDateTime.nanoSeconds = 0;
      }
      return localDateTime;
      localObject = new StringBuffer("GMT");
      toStringZone(paramInt, (StringBuffer)localObject);
    }
    localDateTime.mask |= 0x80;
    return localDateTime;
  }
  
  public DateTime cast(int paramInt)
  {
    int i = this.mask & 0xFF7F;
    if (paramInt == i) {
      return this;
    }
    DateTime localDateTime = new DateTime(paramInt, (GregorianCalendar)this.calendar.clone());
    if ((((i ^ 0xFFFFFFFF) & paramInt) != 0) && ((i != 14) || (paramInt != 126))) {
      throw new ClassCastException("cannot cast DateTime - missing conponents");
    }
    if (isZoneUnspecified())
    {
      localDateTime.mask &= 0xFF7F;
      paramInt = i & (paramInt ^ 0xFFFFFFFF);
      if ((paramInt & 0x70) == 0) {
        break label193;
      }
      localDateTime.calendar.clear(11);
      localDateTime.calendar.clear(12);
      localDateTime.calendar.clear(13);
    }
    for (;;)
    {
      if ((paramInt & 0x2) != 0)
      {
        localDateTime.calendar.clear(1);
        localDateTime.calendar.clear(0);
      }
      if ((paramInt & 0x4) != 0) {
        localDateTime.calendar.clear(2);
      }
      if ((paramInt & 0x8) != 0) {
        localDateTime.calendar.clear(5);
      }
      return localDateTime;
      localDateTime.mask |= 0x80;
      break;
      label193:
      localDateTime.nanoSeconds = this.nanoSeconds;
    }
  }
  
  public int compare(Object paramObject)
  {
    if ((paramObject instanceof DateTime)) {
      return compare(this, (DateTime)paramObject);
    }
    return ((Numeric)paramObject).compareReversed(this);
  }
  
  public int components()
  {
    return this.mask & 0xFF7F;
  }
  
  public int getDay()
  {
    return this.calendar.get(5);
  }
  
  public int getHours()
  {
    return this.calendar.get(11);
  }
  
  public int getMinutes()
  {
    return this.calendar.get(12);
  }
  
  public int getMonth()
  {
    return this.calendar.get(2) + 1;
  }
  
  public int getNanoSecondsOnly()
  {
    return this.nanoSeconds;
  }
  
  public int getSecondsOnly()
  {
    return this.calendar.get(13);
  }
  
  public int getWholeSeconds()
  {
    return this.calendar.get(13);
  }
  
  public int getYear()
  {
    int j = this.calendar.get(1);
    int i = j;
    if (this.calendar.get(0) == 0) {
      i = 1 - j;
    }
    return i;
  }
  
  public int getZoneMinutes()
  {
    return this.calendar.getTimeZone().getRawOffset() / 60000;
  }
  
  public boolean isExact()
  {
    return (this.mask & 0x70) == 0;
  }
  
  public boolean isZero()
  {
    throw new Error("DateTime.isZero not meaningful!");
  }
  
  public boolean isZoneUnspecified()
  {
    return (this.mask & 0x80) == 0;
  }
  
  public Complex number()
  {
    throw new Error("number needs to be implemented!");
  }
  
  int parseDate(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0) {
      i = paramInt1;
    }
    int n;
    label216:
    do
    {
      return i;
      n = paramString.length();
      k = 0;
      j = k;
      i = paramInt1;
      if (paramInt1 < n)
      {
        j = k;
        i = paramInt1;
        if (paramString.charAt(paramInt1) == '-')
        {
          i = paramInt1 + 1;
          j = 1;
        }
      }
      k = i;
      if ((paramInt2 & 0x2) == 0)
      {
        if (j == 0) {
          return -1;
        }
        paramInt1 = -1;
        j = k;
      }
      for (;;)
      {
        i = j;
        if ((paramInt2 & 0xC) == 0) {
          break;
        }
        if ((j < n) && (paramString.charAt(j) == '-')) {
          break label216;
        }
        return -1;
        k = parseDigits(paramString, k);
        paramInt1 = k >> 16;
        k &= 0xFFFF;
        if ((k != i + 4) && ((k <= i + 4) || (paramString.charAt(i) == '0'))) {
          return -1;
        }
        if ((j != 0) || (paramInt1 == 0))
        {
          this.calendar.set(0, 0);
          this.calendar.set(1, paramInt1 + 1);
          j = k;
        }
        else
        {
          this.calendar.set(1, paramInt1);
          j = k;
        }
      }
      k = j + 1;
      if ((paramInt2 & 0x4) == 0) {
        break;
      }
      i = parseDigits(paramString, k);
      m = i >> 16;
      j = i & 0xFFFF;
      if ((m <= 0) || (m > 12) || (j != k + 2)) {
        return -1;
      }
      this.calendar.set(2, m - 1);
      i = j;
    } while ((paramInt2 & 0x8) == 0);
    int i = m;
    while ((j >= n) || (paramString.charAt(j) != '-'))
    {
      return -1;
      i = -1;
      j = k;
    }
    int m = j + 1;
    int k = parseDigits(paramString, m);
    int j = k >> 16;
    k &= 0xFFFF;
    if ((j > 0) && (k == m + 2)) {
      if ((paramInt2 & 0x4) == 0)
      {
        paramInt1 = 31;
        if (j <= paramInt1)
        {
          this.calendar.set(5, j);
          return k;
        }
      }
      else
      {
        if ((paramInt2 & 0x2) != 0) {}
        for (;;)
        {
          paramInt1 = daysInMonth(i - 1, paramInt1);
          break;
          paramInt1 = 2000;
        }
      }
    }
    return -1;
  }
  
  int parseTime(String paramString, int paramInt)
  {
    if (paramInt < 0) {
      return paramInt;
    }
    int i1 = paramString.length();
    int i = parseDigits(paramString, paramInt);
    int m = i >> 16;
    i &= 0xFFFF;
    int n;
    int i2;
    int j;
    int i3;
    if ((m <= 24) && (i == paramInt + 2) && (i != i1) && (paramString.charAt(i) == ':'))
    {
      paramInt = i + 1;
      i = parseDigits(paramString, paramInt);
      n = i >> 16;
      i &= 0xFFFF;
      if ((n < 60) && (i == paramInt + 2) && (i != i1) && (paramString.charAt(i) == ':'))
      {
        i += 1;
        paramInt = parseDigits(paramString, i);
        i2 = paramInt >> 16;
        paramInt &= 0xFFFF;
        if ((i2 < 60) && (paramInt == i + 2))
        {
          i = paramInt;
          if (paramInt + 1 < i1)
          {
            i = paramInt;
            if (paramString.charAt(paramInt) == '.')
            {
              i = paramInt;
              if (Character.digit(paramString.charAt(paramInt + 1), 10) >= 0)
              {
                i = paramInt + 1;
                j = 0;
                paramInt = 0;
                if (i >= i1) {
                  break label368;
                }
                i3 = Character.digit(paramString.charAt(i), 10);
                if (i3 >= 0) {}
              }
            }
          }
        }
      }
    }
    label368:
    for (;;)
    {
      if (paramInt < 9)
      {
        j *= 10;
        paramInt += 1;
        continue;
        int k;
        if (paramInt < 9) {
          k = j * 10 + i3;
        }
        for (;;)
        {
          paramInt += 1;
          i += 1;
          j = k;
          break;
          k = j;
          if (paramInt == 9)
          {
            k = j;
            if (i3 >= 5) {
              k = j + 1;
            }
          }
        }
      }
      else
      {
        this.nanoSeconds = j;
        if ((m == 24) && ((n != 0) || (i2 != 0) || (this.nanoSeconds != 0))) {
          return -1;
        }
        this.calendar.set(11, m);
        this.calendar.set(12, n);
        this.calendar.set(13, i2);
        return i;
        return -1;
      }
    }
  }
  
  int parseZone(String paramString, int paramInt)
  {
    if (paramInt < 0) {}
    int i;
    do
    {
      return paramInt;
      i = parseZoneMinutes(paramString, paramInt);
      if (i == 0) {
        return -1;
      }
    } while (i == paramInt);
    int j = i & 0xFFFF;
    if (i >> 16 == 0) {}
    for (paramString = GMT;; paramString = TimeZone.getTimeZone("GMT" + paramString.substring(paramInt, j)))
    {
      this.calendar.setTimeZone(paramString);
      this.mask |= 0x80;
      return j;
    }
  }
  
  int parseZoneMinutes(String paramString, int paramInt)
  {
    int k = 0;
    int j = paramString.length();
    if ((paramInt == j) || (paramInt < 0)) {
      i = paramInt;
    }
    int i1;
    label204:
    do
    {
      int n;
      do
      {
        int i2;
        int m;
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return i;
                  i1 = paramString.charAt(paramInt);
                  if (i1 == 90) {
                    return paramInt + 1;
                  }
                  if ((i1 != 43) && (i1 != 45)) {
                    return paramInt;
                  }
                  paramInt += 1;
                  n = parseDigits(paramString, paramInt);
                  i2 = n >> 16;
                  i = k;
                } while (i2 > 14);
                m = i2 * 60;
                n &= 0xFFFF;
                i = k;
              } while (n != paramInt + 2);
              i = k;
            } while (n >= j);
            paramInt = m;
            j = n;
            if (paramString.charAt(n) != ':') {
              break label204;
            }
            n += 1;
            paramInt = parseDigits(paramString, n);
            j = paramInt & 0xFFFF;
            paramInt >>= 16;
            if (paramInt <= 0) {
              break;
            }
            i = k;
          } while (paramInt >= 60);
          i = k;
        } while (i2 == 14);
        paramInt = m + paramInt;
        i = k;
      } while (j != n + 2);
      i = k;
    } while (paramInt > 840);
    int i = paramInt;
    if (i1 == 45) {
      i = -paramInt;
    }
    return i << 16 | j;
  }
  
  public void setTimeZone(TimeZone paramTimeZone)
  {
    this.calendar.setTimeZone(paramTimeZone);
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    toString(localStringBuffer);
    return localStringBuffer.toString();
  }
  
  public void toString(StringBuffer paramStringBuffer)
  {
    int j = 1;
    int k = components();
    int i;
    if ((k & 0xE) != 0)
    {
      i = 1;
      if ((k & 0x70) == 0) {
        break label66;
      }
    }
    for (;;)
    {
      if (i != 0)
      {
        toStringDate(paramStringBuffer);
        if (j != 0) {
          paramStringBuffer.append('T');
        }
      }
      if (j != 0) {
        toStringTime(paramStringBuffer);
      }
      toStringZone(paramStringBuffer);
      return;
      i = 0;
      break;
      label66:
      j = 0;
    }
  }
  
  public void toStringDate(StringBuffer paramStringBuffer)
  {
    int k = components();
    if ((k & 0x2) != 0)
    {
      int j = this.calendar.get(1);
      int i = j;
      if (this.calendar.get(0) == 0)
      {
        j -= 1;
        i = j;
        if (j != 0)
        {
          paramStringBuffer.append('-');
          i = j;
        }
      }
      append(i, paramStringBuffer, 4);
    }
    for (;;)
    {
      if ((k & 0xC) != 0)
      {
        paramStringBuffer.append('-');
        if ((k & 0x4) != 0) {
          append(getMonth(), paramStringBuffer, 2);
        }
        if ((k & 0x8) != 0)
        {
          paramStringBuffer.append('-');
          append(getDay(), paramStringBuffer, 2);
        }
      }
      return;
      paramStringBuffer.append('-');
    }
  }
  
  public void toStringTime(StringBuffer paramStringBuffer)
  {
    append(getHours(), paramStringBuffer, 2);
    paramStringBuffer.append(':');
    append(getMinutes(), paramStringBuffer, 2);
    paramStringBuffer.append(':');
    append(getWholeSeconds(), paramStringBuffer, 2);
    Duration.appendNanoSeconds(this.nanoSeconds, paramStringBuffer);
  }
  
  public void toStringZone(StringBuffer paramStringBuffer)
  {
    if (isZoneUnspecified()) {
      return;
    }
    toStringZone(getZoneMinutes(), paramStringBuffer);
  }
  
  public Unit unit()
  {
    return this.unit;
  }
  
  public DateTime withZoneUnspecified()
  {
    if (isZoneUnspecified()) {
      return this;
    }
    DateTime localDateTime = new DateTime(this.mask, (GregorianCalendar)this.calendar.clone());
    localDateTime.calendar.setTimeZone(TimeZone.getDefault());
    localDateTime.mask &= 0xFF7F;
    return localDateTime;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\DateTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */