package gnu.math;

public abstract class Unit
  extends Quantity
{
  public static BaseUnit Empty;
  public static double NON_COMBINABLE;
  public static final Unit cm;
  public static final NamedUnit date;
  public static final BaseUnit duration;
  public static final BaseUnit gram;
  public static final Unit hour = define("hour", 60.0D, minute);
  public static final Unit in;
  public static final BaseUnit meter;
  public static final Unit minute;
  public static final Unit mm;
  public static final NamedUnit month;
  public static final Unit pica;
  public static final Unit pt;
  public static final Unit radian;
  public static final NamedUnit second;
  static NamedUnit[] table = new NamedUnit[100];
  Unit base;
  Dimensions dims;
  double factor = 1.0D;
  MulUnit products;
  
  static
  {
    Empty = new BaseUnit();
    Dimensions.Empty.bases[0] = Empty;
    NON_COMBINABLE = 0.0D;
    meter = new BaseUnit("m", "Length");
    duration = new BaseUnit("duration", "Time");
    gram = new BaseUnit("g", "Mass");
    cm = define("cm", 0.01D, meter);
    mm = define("mm", 0.1D, cm);
    in = define("in", 0.0254D, meter);
    pt = define("pt", 3.527778E-4D, meter);
    pica = define("pica", 0.004233333D, meter);
    radian = define("rad", 1.0D, Empty);
    date = new NamedUnit("date", NON_COMBINABLE, duration);
    second = new NamedUnit("s", NON_COMBINABLE, duration);
    month = new NamedUnit("month", NON_COMBINABLE, duration);
    minute = define("min", 60.0D, second);
  }
  
  public static Unit define(String paramString, double paramDouble, Unit paramUnit)
  {
    return new NamedUnit(paramString, paramDouble, paramUnit);
  }
  
  public static Unit define(String paramString, DQuantity paramDQuantity)
  {
    return new NamedUnit(paramString, paramDQuantity);
  }
  
  public static Unit divide(Unit paramUnit1, Unit paramUnit2)
  {
    return times(paramUnit1, 1, paramUnit2, -1);
  }
  
  public static NamedUnit lookup(String paramString)
  {
    return NamedUnit.lookup(paramString);
  }
  
  public static NamedUnit make(String paramString, Quantity paramQuantity)
  {
    return NamedUnit.make(paramString, paramQuantity);
  }
  
  public static Unit pow(Unit paramUnit, int paramInt)
  {
    return times(paramUnit, paramInt, Empty, 0);
  }
  
  static Unit times(Unit paramUnit1, int paramInt1, Unit paramUnit2, int paramInt2)
  {
    int j = paramInt1;
    Object localObject1 = paramUnit2;
    int i = paramInt2;
    if (paramUnit1 == paramUnit2)
    {
      j = paramInt1 + paramInt2;
      localObject1 = Empty;
      i = 0;
    }
    Object localObject2;
    if (j != 0)
    {
      localObject2 = paramUnit1;
      paramUnit2 = (Unit)localObject1;
      paramInt1 = i;
      if (paramUnit1 != Empty) {}
    }
    else
    {
      paramUnit2 = Empty;
      paramInt1 = 0;
      j = i;
      localObject2 = localObject1;
    }
    if ((paramInt1 == 0) || (paramUnit2 == Empty))
    {
      if (j == 1) {
        return (Unit)localObject2;
      }
      if (j == 0) {
        return Empty;
      }
    }
    if ((localObject2 instanceof MulUnit))
    {
      paramUnit1 = (MulUnit)localObject2;
      if (paramUnit1.unit1 == paramUnit2) {
        return times(paramUnit2, paramUnit1.power1 * j + paramInt1, paramUnit1.unit2, paramUnit1.power2 * j);
      }
      if (paramUnit1.unit2 == paramUnit2) {
        return times(paramUnit1.unit1, paramUnit1.power1 * j, paramUnit2, paramUnit1.power2 * j + paramInt1);
      }
      if ((paramUnit2 instanceof MulUnit))
      {
        localObject1 = (MulUnit)paramUnit2;
        if ((paramUnit1.unit1 == ((MulUnit)localObject1).unit1) && (paramUnit1.unit2 == ((MulUnit)localObject1).unit2)) {
          return times(paramUnit1.unit1, paramUnit1.power1 * j + ((MulUnit)localObject1).power1 * paramInt1, paramUnit1.unit2, paramUnit1.power2 * j + ((MulUnit)localObject1).power2 * paramInt1);
        }
        if ((paramUnit1.unit1 == ((MulUnit)localObject1).unit2) && (paramUnit1.unit2 == ((MulUnit)localObject1).unit1)) {
          return times(paramUnit1.unit1, paramUnit1.power1 * j + ((MulUnit)localObject1).power2 * paramInt1, paramUnit1.unit2, paramUnit1.power2 * j + ((MulUnit)localObject1).power1 * paramInt1);
        }
      }
    }
    if ((paramUnit2 instanceof MulUnit))
    {
      paramUnit1 = (MulUnit)paramUnit2;
      if (paramUnit1.unit1 == localObject2) {
        return times((Unit)localObject2, paramUnit1.power1 * paramInt1 + j, paramUnit1.unit2, paramUnit1.power2 * paramInt1);
      }
      if (paramUnit1.unit2 == localObject2) {
        return times(paramUnit1.unit1, paramUnit1.power1 * paramInt1, (Unit)localObject2, paramUnit1.power2 * paramInt1 + j);
      }
    }
    return MulUnit.make((Unit)localObject2, j, paramUnit2, paramInt1);
  }
  
  public static Unit times(Unit paramUnit1, Unit paramUnit2)
  {
    return times(paramUnit1, 1, paramUnit2, 1);
  }
  
  public final Dimensions dimensions()
  {
    return this.dims;
  }
  
  public final double doubleValue()
  {
    return this.factor;
  }
  
  public String getName()
  {
    return null;
  }
  
  public int hashCode()
  {
    return this.dims.hashCode();
  }
  
  public boolean isExact()
  {
    return false;
  }
  
  public final boolean isZero()
  {
    return false;
  }
  
  public Complex number()
  {
    return DFloNum.one();
  }
  
  public Numeric power(IntNum paramIntNum)
  {
    if (paramIntNum.words != null) {
      throw new ArithmeticException("Unit raised to bignum power");
    }
    return pow(this, paramIntNum.ival);
  }
  
  public Unit sqrt()
  {
    if (this == Empty) {
      return this;
    }
    throw new RuntimeException("unimplemented Unit.sqrt");
  }
  
  public String toString()
  {
    String str = getName();
    if (str != null) {
      return str;
    }
    if (this == Empty) {
      return "unit";
    }
    return Double.toString(this.factor) + "<unnamed unit>";
  }
  
  public String toString(double paramDouble)
  {
    String str = Double.toString(paramDouble);
    if (this == Empty) {
      return str;
    }
    return str + toString();
  }
  
  public String toString(RealNum paramRealNum)
  {
    return toString(paramRealNum.doubleValue());
  }
  
  public Unit unit()
  {
    return this;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\Unit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */