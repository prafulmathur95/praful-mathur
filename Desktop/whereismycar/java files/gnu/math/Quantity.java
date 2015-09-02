package gnu.math;

public abstract class Quantity
  extends Numeric
{
  public static Quantity add(Quantity paramQuantity1, Quantity paramQuantity2, int paramInt)
  {
    if (paramQuantity1.unit() == paramQuantity2.unit()) {
      return make(Complex.add(paramQuantity1.number(), paramQuantity2.number(), paramInt), paramQuantity1.unit());
    }
    if (paramQuantity1.dimensions() != paramQuantity2.dimensions()) {
      throw new ArithmeticException("units mis-match");
    }
    double d = paramQuantity1.unit().doubleValue();
    return make((paramQuantity1.reValue() + paramInt * paramQuantity2.reValue()) / d, (paramQuantity1.imValue() + paramInt * paramQuantity2.imValue()) / d, paramQuantity1.unit());
  }
  
  public static int compare(Quantity paramQuantity1, Quantity paramQuantity2)
  {
    if (paramQuantity1.unit() == paramQuantity2.unit()) {
      return Complex.compare(paramQuantity1.number(), paramQuantity2.number());
    }
    if ((paramQuantity1.dimensions() != paramQuantity2.dimensions()) || (paramQuantity1.imValue() != paramQuantity2.imValue())) {
      return -3;
    }
    return DFloNum.compare(paramQuantity1.reValue(), paramQuantity2.reValue());
  }
  
  public static Quantity divide(Quantity paramQuantity1, Quantity paramQuantity2)
  {
    Unit localUnit = Unit.divide(paramQuantity1.unit(), paramQuantity2.unit());
    return make((Complex)paramQuantity1.number().div(paramQuantity2.number()), localUnit);
  }
  
  public static Quantity make(double paramDouble1, double paramDouble2, Unit paramUnit)
  {
    if (paramUnit == Unit.Empty) {
      return Complex.make(paramDouble1, paramDouble2);
    }
    if (paramDouble2 == 0.0D) {
      return new DQuantity(paramDouble1, paramUnit);
    }
    return new CQuantity(new DFloNum(paramDouble1), new DFloNum(paramDouble2), paramUnit);
  }
  
  public static Quantity make(Complex paramComplex, Unit paramUnit)
  {
    if (paramUnit == Unit.Empty) {
      return paramComplex;
    }
    if ((paramComplex instanceof DFloNum)) {
      return new DQuantity(paramComplex.doubleValue(), paramUnit);
    }
    return new CQuantity(paramComplex, paramUnit);
  }
  
  public static Quantity make(RealNum paramRealNum1, RealNum paramRealNum2, Unit paramUnit)
  {
    if (paramUnit == Unit.Empty) {
      return Complex.make(paramRealNum1, paramRealNum2);
    }
    if ((paramRealNum2.isZero()) && ((!paramRealNum1.isExact()) || (!paramRealNum2.isExact()))) {
      return new DQuantity(paramRealNum1.doubleValue(), paramUnit);
    }
    return new CQuantity(paramRealNum1, paramRealNum2, paramUnit);
  }
  
  public static Quantity times(Quantity paramQuantity1, Quantity paramQuantity2)
  {
    Unit localUnit = Unit.times(paramQuantity1.unit(), paramQuantity2.unit());
    return make((Complex)paramQuantity1.number().mul(paramQuantity2.number()), localUnit);
  }
  
  public Numeric abs()
  {
    return make((Complex)number().abs(), unit());
  }
  
  public Numeric add(Object paramObject, int paramInt)
  {
    if ((paramObject instanceof Quantity)) {
      return add(this, (Quantity)paramObject, paramInt);
    }
    return ((Numeric)paramObject).addReversed(this, paramInt);
  }
  
  public Numeric addReversed(Numeric paramNumeric, int paramInt)
  {
    if ((paramNumeric instanceof Quantity)) {
      return add((Quantity)paramNumeric, this, paramInt);
    }
    throw new IllegalArgumentException();
  }
  
  public int compare(Object paramObject)
  {
    if (!(paramObject instanceof Quantity)) {
      return ((Numeric)paramObject).compareReversed(this);
    }
    return compare(this, (Quantity)paramObject);
  }
  
  public int compareReversed(Numeric paramNumeric)
  {
    if ((paramNumeric instanceof Quantity)) {
      return compare((Quantity)paramNumeric, this);
    }
    throw new IllegalArgumentException();
  }
  
  public Dimensions dimensions()
  {
    return unit().dimensions();
  }
  
  public Numeric div(Object paramObject)
  {
    if ((paramObject instanceof Quantity)) {
      return divide(this, (Quantity)paramObject);
    }
    return ((Numeric)paramObject).divReversed(this);
  }
  
  public Numeric divReversed(Numeric paramNumeric)
  {
    if ((paramNumeric instanceof Quantity)) {
      return divide((Quantity)paramNumeric, this);
    }
    throw new IllegalArgumentException();
  }
  
  public double doubleImagValue()
  {
    return unit().doubleValue() * im().doubleValue();
  }
  
  public double doubleValue()
  {
    return unit().doubleValue() * re().doubleValue();
  }
  
  public RealNum im()
  {
    return number().im();
  }
  
  public final double imValue()
  {
    return doubleImagValue();
  }
  
  public Numeric mul(Object paramObject)
  {
    if ((paramObject instanceof Quantity)) {
      return times(this, (Quantity)paramObject);
    }
    return ((Numeric)paramObject).mulReversed(this);
  }
  
  public Numeric mulReversed(Numeric paramNumeric)
  {
    if ((paramNumeric instanceof Quantity)) {
      return times((Quantity)paramNumeric, this);
    }
    throw new IllegalArgumentException();
  }
  
  public Numeric neg()
  {
    return make((Complex)number().neg(), unit());
  }
  
  public abstract Complex number();
  
  public RealNum re()
  {
    return number().re();
  }
  
  public final double reValue()
  {
    return doubleValue();
  }
  
  public String toString(int paramInt)
  {
    String str = number().toString(paramInt);
    if (unit() == Unit.Empty) {
      return str;
    }
    return str + unit().toString();
  }
  
  public Unit unit()
  {
    return Unit.Empty;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\Quantity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */