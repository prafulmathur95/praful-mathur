package gnu.math;

public abstract class Complex
  extends Quantity
{
  private static CComplex imMinusOne;
  private static CComplex imOne;
  
  public static Complex add(Complex paramComplex1, Complex paramComplex2, int paramInt)
  {
    return make(RealNum.add(paramComplex1.re(), paramComplex2.re(), paramInt), RealNum.add(paramComplex1.im(), paramComplex2.im(), paramInt));
  }
  
  public static int compare(Complex paramComplex1, Complex paramComplex2)
  {
    int i = paramComplex1.im().compare(paramComplex2.im());
    if (i != 0) {
      return i;
    }
    return paramComplex1.re().compare(paramComplex2.re());
  }
  
  public static Complex divide(Complex paramComplex1, Complex paramComplex2)
  {
    if ((!paramComplex1.isExact()) || (!paramComplex2.isExact())) {
      return DComplex.div(paramComplex1.doubleRealValue(), paramComplex1.doubleImagValue(), paramComplex2.doubleRealValue(), paramComplex2.doubleImagValue());
    }
    RealNum localRealNum1 = paramComplex1.re();
    paramComplex1 = paramComplex1.im();
    RealNum localRealNum2 = paramComplex2.re();
    RealNum localRealNum4 = paramComplex2.im();
    paramComplex2 = RealNum.add(RealNum.times(localRealNum2, localRealNum2), RealNum.times(localRealNum4, localRealNum4), 1);
    RealNum localRealNum3 = RealNum.add(RealNum.times(localRealNum1, localRealNum2), RealNum.times(paramComplex1, localRealNum4), 1);
    paramComplex1 = RealNum.add(RealNum.times(paramComplex1, localRealNum2), RealNum.times(localRealNum1, localRealNum4), -1);
    return make(RealNum.divide(localRealNum3, paramComplex2), RealNum.divide(paramComplex1, paramComplex2));
  }
  
  public static boolean equals(Complex paramComplex1, Complex paramComplex2)
  {
    return (paramComplex1.re().equals(paramComplex2.re())) && (paramComplex1.im().equals(paramComplex1.im()));
  }
  
  public static CComplex imMinusOne()
  {
    if (imMinusOne == null) {
      imMinusOne = new CComplex(IntNum.zero(), IntNum.minusOne());
    }
    return imMinusOne;
  }
  
  public static CComplex imOne()
  {
    if (imOne == null) {
      imOne = new CComplex(IntNum.zero(), IntNum.one());
    }
    return imOne;
  }
  
  public static Complex make(double paramDouble1, double paramDouble2)
  {
    if (paramDouble2 == 0.0D) {
      return new DFloNum(paramDouble1);
    }
    return new DComplex(paramDouble1, paramDouble2);
  }
  
  public static Complex make(RealNum paramRealNum1, RealNum paramRealNum2)
  {
    if (paramRealNum2.isZero()) {
      return paramRealNum1;
    }
    if ((!paramRealNum1.isExact()) || (!paramRealNum2.isExact())) {
      return new DComplex(paramRealNum1.doubleValue(), paramRealNum2.doubleValue());
    }
    return new CComplex(paramRealNum1, paramRealNum2);
  }
  
  public static Complex neg(Complex paramComplex)
  {
    return make(paramComplex.re().rneg(), paramComplex.im().rneg());
  }
  
  public static DComplex polar(double paramDouble1, double paramDouble2)
  {
    return new DComplex(Math.cos(paramDouble2) * paramDouble1, Math.sin(paramDouble2) * paramDouble1);
  }
  
  public static DComplex polar(RealNum paramRealNum1, RealNum paramRealNum2)
  {
    return polar(paramRealNum1.doubleValue(), paramRealNum2.doubleValue());
  }
  
  public static Complex power(Complex paramComplex1, Complex paramComplex2)
  {
    if ((paramComplex2 instanceof IntNum)) {
      return (Complex)paramComplex1.power((IntNum)paramComplex2);
    }
    double d1 = paramComplex1.doubleRealValue();
    double d2 = paramComplex1.doubleImagValue();
    double d3 = paramComplex2.doubleRealValue();
    double d4 = paramComplex2.doubleImagValue();
    if ((d2 == 0.0D) && (d4 == 0.0D) && ((d1 >= 0.0D) || (Double.isInfinite(d1)) || (Double.isNaN(d1)))) {
      return new DFloNum(Math.pow(d1, d3));
    }
    return DComplex.power(d1, d2, d3, d4);
  }
  
  public static Complex times(Complex paramComplex1, Complex paramComplex2)
  {
    RealNum localRealNum1 = paramComplex1.re();
    paramComplex1 = paramComplex1.im();
    RealNum localRealNum2 = paramComplex2.re();
    paramComplex2 = paramComplex2.im();
    return make(RealNum.add(RealNum.times(localRealNum1, localRealNum2), RealNum.times(paramComplex1, paramComplex2), -1), RealNum.add(RealNum.times(localRealNum1, paramComplex2), RealNum.times(paramComplex1, localRealNum2), 1));
  }
  
  public Numeric abs()
  {
    return new DFloNum(Math.hypot(doubleRealValue(), doubleImagValue()));
  }
  
  public Numeric add(Object paramObject, int paramInt)
  {
    if ((paramObject instanceof Complex)) {
      return add(this, (Complex)paramObject, paramInt);
    }
    return ((Numeric)paramObject).addReversed(this, paramInt);
  }
  
  public Numeric addReversed(Numeric paramNumeric, int paramInt)
  {
    if ((paramNumeric instanceof Complex)) {
      return add((Complex)paramNumeric, this, paramInt);
    }
    throw new IllegalArgumentException();
  }
  
  public RealNum angle()
  {
    return new DFloNum(Math.atan2(doubleImagValue(), doubleRealValue()));
  }
  
  public int compare(Object paramObject)
  {
    if (!(paramObject instanceof Complex)) {
      return ((Numeric)paramObject).compareReversed(this);
    }
    return compare(this, (Complex)paramObject);
  }
  
  public Numeric div(Object paramObject)
  {
    if ((paramObject instanceof Complex)) {
      return divide(this, (Complex)paramObject);
    }
    return ((Numeric)paramObject).divReversed(this);
  }
  
  public Numeric divReversed(Numeric paramNumeric)
  {
    if ((paramNumeric instanceof Complex)) {
      return divide((Complex)paramNumeric, this);
    }
    throw new IllegalArgumentException();
  }
  
  public double doubleImagValue()
  {
    return im().doubleValue();
  }
  
  public final double doubleRealValue()
  {
    return doubleValue();
  }
  
  public double doubleValue()
  {
    return re().doubleValue();
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof Complex))) {
      return false;
    }
    return equals(this, (Complex)paramObject);
  }
  
  public Complex exp()
  {
    return polar(Math.exp(doubleRealValue()), doubleImagValue());
  }
  
  public boolean isExact()
  {
    return (re().isExact()) && (im().isExact());
  }
  
  public boolean isZero()
  {
    return (re().isZero()) && (im().isZero());
  }
  
  public Complex log()
  {
    return DComplex.log(doubleRealValue(), doubleImagValue());
  }
  
  public long longValue()
  {
    return re().longValue();
  }
  
  public Numeric mul(Object paramObject)
  {
    if ((paramObject instanceof Complex)) {
      return times(this, (Complex)paramObject);
    }
    return ((Numeric)paramObject).mulReversed(this);
  }
  
  public Numeric mulReversed(Numeric paramNumeric)
  {
    if ((paramNumeric instanceof Complex)) {
      return times((Complex)paramNumeric, this);
    }
    throw new IllegalArgumentException();
  }
  
  public Numeric neg()
  {
    return neg(this);
  }
  
  public Complex number()
  {
    return this;
  }
  
  public Complex sqrt()
  {
    return DComplex.sqrt(doubleRealValue(), doubleImagValue());
  }
  
  public Complex toExact()
  {
    RealNum localRealNum1 = re();
    RealNum localRealNum2 = im();
    RatNum localRatNum1 = localRealNum1.toExact();
    RatNum localRatNum2 = localRealNum2.toExact();
    if ((localRatNum1 == localRealNum1) && (localRatNum2 == localRealNum2)) {
      return this;
    }
    return new CComplex(localRatNum1, localRatNum2);
  }
  
  public Complex toInexact()
  {
    if (isExact()) {
      return this;
    }
    return new DComplex(re().doubleValue(), im().doubleValue());
  }
  
  public String toString(int paramInt)
  {
    Object localObject2;
    if (im().isZero()) {
      localObject2 = re().toString(paramInt);
    }
    Object localObject1;
    do
    {
      return (String)localObject2;
      localObject2 = im().toString(paramInt) + "i";
      localObject1 = localObject2;
      if (((String)localObject2).charAt(0) != '-') {
        localObject1 = "+" + (String)localObject2;
      }
      localObject2 = localObject1;
    } while (re().isZero());
    return re().toString(paramInt) + (String)localObject1;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\Complex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */