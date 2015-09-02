package gnu.kawa.functions;

import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import java.math.BigDecimal;
import java.math.BigInteger;

public class AddOp
  extends ArithOp
{
  public static final AddOp $Mn = new AddOp("-", -1);
  public static final AddOp $Pl = new AddOp("+", 1);
  int plusOrMinus;
  
  public static Object $Mn(Object paramObject)
  {
    switch (Arithmetic.classifyValue(paramObject))
    {
    default: 
      return Arithmetic.asNumeric(paramObject).neg();
    case 1: 
      return new Integer(-Arithmetic.asInt(paramObject));
    case 2: 
      return new Long(-Arithmetic.asLong(paramObject));
    case 3: 
      return Arithmetic.asBigInteger(paramObject).negate();
    case 4: 
      return IntNum.neg(Arithmetic.asIntNum(paramObject));
    case 5: 
      return Arithmetic.asBigDecimal(paramObject).negate();
    case 6: 
      return RatNum.neg(Arithmetic.asRatNum(paramObject));
    case 7: 
      return new Float(-Arithmetic.asFloat(paramObject));
    case 8: 
      return new Double(-Arithmetic.asDouble(paramObject));
    }
    return new DFloNum(-Arithmetic.asDouble(paramObject));
  }
  
  public static Object $Mn(Object paramObject1, Object paramObject2)
  {
    return apply2(-1, paramObject1, paramObject2);
  }
  
  public static Object $Mn$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject)
  {
    return applyN(-1, apply2(-1, apply2(-1, paramObject1, paramObject2), paramObject3), paramArrayOfObject);
  }
  
  public static Object $Pl(Object paramObject1, Object paramObject2)
  {
    return apply2(1, paramObject1, paramObject2);
  }
  
  public static Object $Pl$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject)
  {
    return applyN(1, apply2(1, apply2(1, paramObject1, paramObject2), paramObject3), paramArrayOfObject);
  }
  
  public AddOp(String paramString, int paramInt) {}
  
  public static Object apply2(int paramInt, Object paramObject1, Object paramObject2)
  {
    int i = Arithmetic.classifyValue(paramObject1);
    int j = Arithmetic.classifyValue(paramObject2);
    if (i < j) {
      i = j;
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return Arithmetic.asNumeric(paramObject1).add(Arithmetic.asNumeric(paramObject2), paramInt);
      }
    }
    i = Arithmetic.asInt(paramObject1);
    j = Arithmetic.asInt(paramObject2);
    if (paramInt > 0) {}
    for (paramInt = i + j;; paramInt = i - j) {
      return new Integer(paramInt);
    }
    long l1 = Arithmetic.asLong(paramObject1);
    long l2 = Arithmetic.asLong(paramObject2);
    if (paramInt > 0) {}
    for (l1 += l2;; l1 -= l2) {
      return new Long(l1);
    }
    paramObject1 = Arithmetic.asBigInteger(paramObject1);
    paramObject2 = Arithmetic.asBigInteger(paramObject2);
    if (paramInt > 0) {
      return ((BigInteger)paramObject1).add((BigInteger)paramObject2);
    }
    return ((BigInteger)paramObject1).subtract((BigInteger)paramObject2);
    return IntNum.add(Arithmetic.asIntNum(paramObject1), Arithmetic.asIntNum(paramObject2), paramInt);
    paramObject1 = Arithmetic.asBigDecimal(paramObject1);
    paramObject2 = Arithmetic.asBigDecimal(paramObject2);
    if (paramInt > 0) {
      return ((BigDecimal)paramObject1).add((BigDecimal)paramObject2);
    }
    return ((BigDecimal)paramObject1).subtract((BigDecimal)paramObject2);
    return RatNum.add(Arithmetic.asRatNum(paramObject1), Arithmetic.asRatNum(paramObject2), paramInt);
    float f1 = Arithmetic.asFloat(paramObject1);
    float f2 = Arithmetic.asFloat(paramObject2);
    if (paramInt > 0) {}
    for (f1 += f2;; f1 -= f2) {
      return new Float(f1);
    }
    double d1 = Arithmetic.asDouble(paramObject1);
    double d2 = Arithmetic.asDouble(paramObject2);
    if (paramInt > 0) {}
    for (d1 += d2;; d1 -= d2) {
      return new Double(d1);
    }
    d1 = Arithmetic.asDouble(paramObject1);
    d2 = Arithmetic.asDouble(paramObject2);
    if (paramInt > 0) {}
    for (d1 += d2;; d1 -= d2) {
      return new DFloNum(d1);
    }
  }
  
  public static Object applyN(int paramInt, Object paramObject, Object[] paramArrayOfObject)
  {
    int j = paramArrayOfObject.length;
    int i = 0;
    while (i < j)
    {
      paramObject = apply2(paramInt, paramObject, paramArrayOfObject[i]);
      i += 1;
    }
    return paramObject;
  }
  
  public static Object applyN(int paramInt, Object[] paramArrayOfObject)
  {
    int j = paramArrayOfObject.length;
    Object localObject2;
    if (j == 0)
    {
      localObject2 = IntNum.zero();
      return localObject2;
    }
    Object localObject1 = paramArrayOfObject[0];
    if ((j == 1) && (paramInt < 0)) {
      return $Mn(localObject1);
    }
    int i = 1;
    for (;;)
    {
      localObject2 = localObject1;
      if (i >= j) {
        break;
      }
      localObject1 = apply2(paramInt, localObject1, paramArrayOfObject[i]);
      i += 1;
    }
  }
  
  public Object applyN(Object[] paramArrayOfObject)
  {
    return applyN(this.plusOrMinus, paramArrayOfObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\AddOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */