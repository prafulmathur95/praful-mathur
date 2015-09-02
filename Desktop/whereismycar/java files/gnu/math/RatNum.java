package gnu.math;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class RatNum
  extends RealNum
{
  public static final IntNum ten_exp_9 = IntNum.make(1000000000);
  
  public static RatNum add(RatNum paramRatNum1, RatNum paramRatNum2, int paramInt)
  {
    IntNum localIntNum1 = paramRatNum1.numerator();
    paramRatNum1 = paramRatNum1.denominator();
    IntNum localIntNum2 = paramRatNum2.numerator();
    paramRatNum2 = paramRatNum2.denominator();
    if (IntNum.equals(paramRatNum1, paramRatNum2)) {
      return make(IntNum.add(localIntNum1, localIntNum2, paramInt), paramRatNum1);
    }
    return make(IntNum.add(IntNum.times(paramRatNum2, localIntNum1), IntNum.times(localIntNum2, paramRatNum1), paramInt), IntNum.times(paramRatNum1, paramRatNum2));
  }
  
  public static RatNum asRatNumOrNull(Object paramObject)
  {
    if ((paramObject instanceof RatNum)) {
      return (RatNum)paramObject;
    }
    if ((paramObject instanceof BigDecimal)) {
      return valueOf((BigDecimal)paramObject);
    }
    return IntNum.asIntNumOrNull(paramObject);
  }
  
  public static int compare(RatNum paramRatNum1, RatNum paramRatNum2)
  {
    return IntNum.compare(IntNum.times(paramRatNum1.numerator(), paramRatNum2.denominator()), IntNum.times(paramRatNum2.numerator(), paramRatNum1.denominator()));
  }
  
  public static RatNum divide(RatNum paramRatNum1, RatNum paramRatNum2)
  {
    return make(IntNum.times(paramRatNum1.numerator(), paramRatNum2.denominator()), IntNum.times(paramRatNum1.denominator(), paramRatNum2.numerator()));
  }
  
  public static boolean equals(RatNum paramRatNum1, RatNum paramRatNum2)
  {
    return (IntNum.equals(paramRatNum1.numerator(), paramRatNum2.numerator())) && (IntNum.equals(paramRatNum1.denominator(), paramRatNum2.denominator()));
  }
  
  public static RatNum infinity(int paramInt)
  {
    return new IntFraction(IntNum.make(paramInt), IntNum.zero());
  }
  
  public static RatNum make(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    IntNum localIntNum2 = IntNum.gcd(paramIntNum1, paramIntNum2);
    IntNum localIntNum1 = localIntNum2;
    if (paramIntNum2.isNegative()) {
      localIntNum1 = IntNum.neg(localIntNum2);
    }
    IntNum localIntNum3 = paramIntNum1;
    localIntNum2 = paramIntNum2;
    if (!localIntNum1.isOne())
    {
      localIntNum3 = IntNum.quotient(paramIntNum1, localIntNum1);
      localIntNum2 = IntNum.quotient(paramIntNum2, localIntNum1);
    }
    if (localIntNum2.isOne()) {
      return localIntNum3;
    }
    return new IntFraction(localIntNum3, localIntNum2);
  }
  
  public static RatNum neg(RatNum paramRatNum)
  {
    IntNum localIntNum = paramRatNum.numerator();
    paramRatNum = paramRatNum.denominator();
    return make(IntNum.neg(localIntNum), paramRatNum);
  }
  
  public static RealNum rationalize(RealNum paramRealNum1, RealNum paramRealNum2)
  {
    RealNum localRealNum;
    if (paramRealNum1.grt(paramRealNum2)) {
      localRealNum = simplest_rational2(paramRealNum2, paramRealNum1);
    }
    do
    {
      return localRealNum;
      localRealNum = paramRealNum1;
    } while (!paramRealNum2.grt(paramRealNum1));
    if (paramRealNum1.sign() > 0) {
      return simplest_rational2(paramRealNum1, paramRealNum2);
    }
    if (paramRealNum2.isNegative()) {
      return (RealNum)simplest_rational2((RealNum)paramRealNum2.neg(), (RealNum)paramRealNum1.neg()).neg();
    }
    return IntNum.zero();
  }
  
  private static RealNum simplest_rational2(RealNum paramRealNum1, RealNum paramRealNum2)
  {
    RealNum localRealNum1 = paramRealNum1.toInt(1);
    RealNum localRealNum2 = paramRealNum2.toInt(1);
    if (!paramRealNum1.grt(localRealNum1)) {
      return localRealNum1;
    }
    if (localRealNum1.equals(localRealNum2))
    {
      paramRealNum2 = (RealNum)IntNum.one().div(paramRealNum2.sub(localRealNum2));
      paramRealNum1 = (RealNum)IntNum.one().div(paramRealNum1.sub(localRealNum1));
      return (RealNum)localRealNum1.add(IntNum.one().div(simplest_rational2(paramRealNum2, paramRealNum1)), 1);
    }
    return (RealNum)localRealNum1.add(IntNum.one(), 1);
  }
  
  public static RatNum times(RatNum paramRatNum1, RatNum paramRatNum2)
  {
    return make(IntNum.times(paramRatNum1.numerator(), paramRatNum2.numerator()), IntNum.times(paramRatNum1.denominator(), paramRatNum2.denominator()));
  }
  
  public static RatNum valueOf(BigDecimal paramBigDecimal)
  {
    Object localObject = IntNum.valueOf(paramBigDecimal.unscaledValue().toString(), 10);
    int j = paramBigDecimal.scale();
    paramBigDecimal = (BigDecimal)localObject;
    int i;
    for (;;)
    {
      i = j;
      localObject = paramBigDecimal;
      if (j < 9) {
        break;
      }
      paramBigDecimal = divide(paramBigDecimal, ten_exp_9);
      j -= 9;
    }
    while (i <= -9)
    {
      localObject = times((RatNum)localObject, ten_exp_9);
      i += 9;
    }
    if (i > 0) {}
    for (j = i;; j = -i) {
      switch (j)
      {
      default: 
        return (RatNum)localObject;
      }
    }
    paramBigDecimal = IntNum.make(10);
    while (i > 0)
    {
      return divide((RatNum)localObject, paramBigDecimal);
      paramBigDecimal = IntNum.make(100);
      continue;
      paramBigDecimal = IntNum.make(1000);
      continue;
      paramBigDecimal = IntNum.make(10000);
      continue;
      paramBigDecimal = IntNum.make(100000);
      continue;
      paramBigDecimal = IntNum.make(1000000);
      continue;
      paramBigDecimal = IntNum.make(10000000);
      continue;
      paramBigDecimal = IntNum.make(100000000);
    }
    return times((RatNum)localObject, paramBigDecimal);
  }
  
  public abstract IntNum denominator();
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof RatNum))) {
      return false;
    }
    return equals(this, (RatNum)paramObject);
  }
  
  public boolean isExact()
  {
    return true;
  }
  
  public boolean isZero()
  {
    return numerator().isZero();
  }
  
  public abstract IntNum numerator();
  
  public Numeric power(IntNum paramIntNum)
  {
    int i;
    if (paramIntNum.isNegative())
    {
      i = 1;
      paramIntNum = IntNum.neg(paramIntNum);
    }
    while (paramIntNum.words == null)
    {
      IntNum localIntNum = IntNum.power(numerator(), paramIntNum.ival);
      paramIntNum = IntNum.power(denominator(), paramIntNum.ival);
      if (i != 0)
      {
        return make(paramIntNum, localIntNum);
        i = 0;
      }
      else
      {
        return make(localIntNum, paramIntNum);
      }
    }
    double d1 = doubleValue();
    if ((d1 < 0.0D) && (paramIntNum.isOdd())) {}
    for (int j = 1;; j = 0)
    {
      double d2 = Math.pow(d1, paramIntNum.doubleValue());
      d1 = d2;
      if (i != 0) {
        d1 = 1.0D / d2;
      }
      d2 = d1;
      if (j != 0) {
        d2 = -d1;
      }
      return new DFloNum(d2);
    }
  }
  
  public final RatNum rneg()
  {
    return neg(this);
  }
  
  public final RatNum toExact()
  {
    return this;
  }
  
  public IntNum toExactInt(int paramInt)
  {
    return IntNum.quotient(numerator(), denominator(), paramInt);
  }
  
  public RealNum toInt(int paramInt)
  {
    return IntNum.quotient(numerator(), denominator(), paramInt);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\RatNum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */