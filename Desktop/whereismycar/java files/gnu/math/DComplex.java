package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DComplex
  extends Complex
  implements Externalizable
{
  double imag;
  double real;
  
  public DComplex() {}
  
  public DComplex(double paramDouble1, double paramDouble2)
  {
    this.real = paramDouble1;
    this.imag = paramDouble2;
  }
  
  public static DComplex div(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d;
    if (Math.abs(paramDouble3) <= Math.abs(paramDouble4))
    {
      d = paramDouble3 / paramDouble4;
      paramDouble4 *= (1.0D + d * d);
      paramDouble3 = paramDouble1 * d + paramDouble2;
      paramDouble2 = paramDouble2 * d - paramDouble1;
    }
    for (paramDouble1 = paramDouble4;; paramDouble1 = paramDouble4)
    {
      return new DComplex(paramDouble3 / paramDouble1, paramDouble2 / paramDouble1);
      d = paramDouble4 / paramDouble3;
      paramDouble4 = paramDouble3 * (1.0D + d * d);
      paramDouble3 = paramDouble1 + paramDouble2 * d;
      paramDouble2 -= paramDouble1 * d;
    }
  }
  
  public static Complex log(double paramDouble1, double paramDouble2)
  {
    return make(Math.log(Math.hypot(paramDouble1, paramDouble2)), Math.atan2(paramDouble2, paramDouble1));
  }
  
  public static DComplex power(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d = Math.log(Math.hypot(paramDouble1, paramDouble2));
    paramDouble1 = Math.atan2(paramDouble2, paramDouble1);
    return Complex.polar(Math.exp(d * paramDouble3 - paramDouble4 * paramDouble1), paramDouble4 * d + paramDouble3 * paramDouble1);
  }
  
  public static Complex sqrt(double paramDouble1, double paramDouble2)
  {
    double d = Math.hypot(paramDouble1, paramDouble2);
    if (d == 0.0D)
    {
      paramDouble1 = d;
      paramDouble2 = d;
    }
    for (;;)
    {
      return new DComplex(paramDouble2, paramDouble1);
      if (paramDouble1 > 0.0D)
      {
        d = Math.sqrt(0.5D * (d + paramDouble1));
        paramDouble1 = paramDouble2 / d / 2.0D;
        paramDouble2 = d;
      }
      else
      {
        d = Math.sqrt(0.5D * (d - paramDouble1));
        paramDouble1 = d;
        if (paramDouble2 < 0.0D) {
          paramDouble1 = -d;
        }
        paramDouble2 = paramDouble2 / paramDouble1 / 2.0D;
      }
    }
  }
  
  public Numeric add(Object paramObject, int paramInt)
  {
    if ((paramObject instanceof Complex))
    {
      paramObject = (Complex)paramObject;
      if (((Complex)paramObject).dimensions() != Dimensions.Empty) {
        throw new ArithmeticException("units mis-match");
      }
      return new DComplex(this.real + paramInt * ((Complex)paramObject).reValue(), this.imag + paramInt * ((Complex)paramObject).imValue());
    }
    return ((Numeric)paramObject).addReversed(this, paramInt);
  }
  
  public Numeric div(Object paramObject)
  {
    if ((paramObject instanceof Complex))
    {
      paramObject = (Complex)paramObject;
      return div(this.real, this.imag, ((Complex)paramObject).doubleValue(), ((Complex)paramObject).doubleImagValue());
    }
    return ((Numeric)paramObject).divReversed(this);
  }
  
  public double doubleImagValue()
  {
    return this.imag;
  }
  
  public double doubleValue()
  {
    return this.real;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof Complex))) {}
    do
    {
      return false;
      paramObject = (Complex)paramObject;
    } while ((((Complex)paramObject).unit() != Unit.Empty) || (Double.doubleToLongBits(this.real) != Double.doubleToLongBits(((Complex)paramObject).reValue())) || (Double.doubleToLongBits(this.imag) != Double.doubleToLongBits(((Complex)paramObject).imValue())));
    return true;
  }
  
  public RealNum im()
  {
    return new DFloNum(this.imag);
  }
  
  public boolean isExact()
  {
    return false;
  }
  
  public Numeric mul(Object paramObject)
  {
    if ((paramObject instanceof Complex))
    {
      paramObject = (Complex)paramObject;
      if (((Complex)paramObject).unit() == Unit.Empty)
      {
        double d1 = ((Complex)paramObject).reValue();
        double d2 = ((Complex)paramObject).imValue();
        return new DComplex(this.real * d1 - this.imag * d2, this.real * d2 + this.imag * d1);
      }
      return Complex.times(this, (Complex)paramObject);
    }
    return ((Numeric)paramObject).mulReversed(this);
  }
  
  public final Numeric neg()
  {
    return new DComplex(-this.real, -this.imag);
  }
  
  public RealNum re()
  {
    return new DFloNum(this.real);
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.real = paramObjectInput.readDouble();
    this.imag = paramObjectInput.readDouble();
  }
  
  public Complex toExact()
  {
    return new CComplex(DFloNum.toExact(this.real), DFloNum.toExact(this.imag));
  }
  
  public String toString()
  {
    Object localObject2 = "";
    String str1;
    if (this.real == Double.POSITIVE_INFINITY)
    {
      localObject2 = "#i";
      str1 = "1/0";
    }
    while (Double.doubleToLongBits(this.imag) == 0L)
    {
      return (String)localObject2 + str1;
      if (this.real == Double.NEGATIVE_INFINITY)
      {
        localObject2 = "#i";
        str1 = "-1/0";
      }
      else if (Double.isNaN(this.real))
      {
        localObject2 = "#i";
        str1 = "0/0";
      }
      else
      {
        str1 = Double.toString(this.real);
      }
    }
    Object localObject3;
    Object localObject1;
    if (this.imag == Double.POSITIVE_INFINITY)
    {
      localObject3 = "#i";
      localObject1 = "+1/0i";
      localObject2 = new StringBuilder();
      if (Double.doubleToLongBits(this.real) != 0L) {
        break label264;
      }
    }
    for (;;)
    {
      return (String)localObject3 + (String)localObject1;
      if (this.imag == Double.NEGATIVE_INFINITY)
      {
        localObject3 = "#i";
        localObject1 = "-1/0i";
        break;
      }
      if (Double.isNaN(this.imag))
      {
        localObject3 = "#i";
        localObject1 = "+0/0i";
        break;
      }
      String str2 = Double.toString(this.imag) + "i";
      localObject1 = str2;
      localObject3 = localObject2;
      if (str2.charAt(0) == '-') {
        break;
      }
      localObject1 = "+" + str2;
      localObject3 = localObject2;
      break;
      label264:
      localObject3 = (String)localObject3 + str1;
    }
  }
  
  public String toString(int paramInt)
  {
    if (paramInt == 10) {
      return toString();
    }
    return "#d" + toString();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeDouble(this.real);
    paramObjectOutput.writeDouble(this.imag);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\DComplex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */