package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class IntFraction
  extends RatNum
  implements Externalizable
{
  IntNum den;
  IntNum num;
  
  IntFraction() {}
  
  public IntFraction(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    this.num = paramIntNum1;
    this.den = paramIntNum2;
  }
  
  public static IntFraction neg(IntFraction paramIntFraction)
  {
    return new IntFraction(IntNum.neg(paramIntFraction.numerator()), paramIntFraction.denominator());
  }
  
  public Numeric add(Object paramObject, int paramInt)
  {
    if ((paramObject instanceof RatNum)) {
      return RatNum.add(this, (RatNum)paramObject, paramInt);
    }
    if (!(paramObject instanceof Numeric)) {
      throw new IllegalArgumentException();
    }
    return ((Numeric)paramObject).addReversed(this, paramInt);
  }
  
  public Numeric addReversed(Numeric paramNumeric, int paramInt)
  {
    if (!(paramNumeric instanceof RatNum)) {
      throw new IllegalArgumentException();
    }
    return RatNum.add((RatNum)paramNumeric, this, paramInt);
  }
  
  public final int compare(Object paramObject)
  {
    if ((paramObject instanceof RatNum)) {
      return RatNum.compare(this, (RatNum)paramObject);
    }
    return ((RealNum)paramObject).compareReversed(this);
  }
  
  public int compareReversed(Numeric paramNumeric)
  {
    return RatNum.compare((RatNum)paramNumeric, this);
  }
  
  public final IntNum denominator()
  {
    return this.den;
  }
  
  public Numeric div(Object paramObject)
  {
    if ((paramObject instanceof RatNum)) {
      return RatNum.divide(this, (RatNum)paramObject);
    }
    if (!(paramObject instanceof Numeric)) {
      throw new IllegalArgumentException();
    }
    return ((Numeric)paramObject).divReversed(this);
  }
  
  public Numeric divReversed(Numeric paramNumeric)
  {
    if (!(paramNumeric instanceof RatNum)) {
      throw new IllegalArgumentException();
    }
    return RatNum.divide((RatNum)paramNumeric, this);
  }
  
  public double doubleValue()
  {
    boolean bool2 = this.num.isNegative();
    if (this.den.isZero())
    {
      if (bool2) {
        return Double.NEGATIVE_INFINITY;
      }
      if (this.num.isZero()) {
        return NaN.0D;
      }
      return Double.POSITIVE_INFINITY;
    }
    Object localObject2 = this.num;
    Object localObject1 = localObject2;
    if (bool2) {
      localObject1 = IntNum.neg((IntNum)localObject2);
    }
    int j = ((IntNum)localObject1).intLength();
    int k = this.den.intLength();
    int i = 0;
    localObject2 = localObject1;
    if (j < k + 54)
    {
      i = k + 54 - j;
      localObject2 = IntNum.shift((IntNum)localObject1, i);
      i = -i;
    }
    IntNum localIntNum = new IntNum();
    localObject1 = new IntNum();
    IntNum.divide((IntNum)localObject2, this.den, localIntNum, (IntNum)localObject1, 3);
    localObject2 = localIntNum.canonicalize();
    if (!((IntNum)localObject1).canonicalize().isZero()) {}
    for (boolean bool1 = true;; bool1 = false) {
      return ((IntNum)localObject2).roundToDouble(i, bool2, bool1);
    }
  }
  
  public final boolean isNegative()
  {
    return this.num.isNegative();
  }
  
  public long longValue()
  {
    return toExactInt(4).longValue();
  }
  
  public Numeric mul(Object paramObject)
  {
    if ((paramObject instanceof RatNum)) {
      return RatNum.times(this, (RatNum)paramObject);
    }
    if (!(paramObject instanceof Numeric)) {
      throw new IllegalArgumentException();
    }
    return ((Numeric)paramObject).mulReversed(this);
  }
  
  public Numeric mulReversed(Numeric paramNumeric)
  {
    if (!(paramNumeric instanceof RatNum)) {
      throw new IllegalArgumentException();
    }
    return RatNum.times((RatNum)paramNumeric, this);
  }
  
  public Numeric neg()
  {
    return neg(this);
  }
  
  public final IntNum numerator()
  {
    return this.num;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.num = ((IntNum)paramObjectInput.readObject());
    this.den = ((IntNum)paramObjectInput.readObject());
  }
  
  public final int sign()
  {
    return this.num.sign();
  }
  
  public String toString(int paramInt)
  {
    return this.num.toString(paramInt) + '/' + this.den.toString(paramInt);
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.num);
    paramObjectOutput.writeObject(this.den);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\IntFraction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */