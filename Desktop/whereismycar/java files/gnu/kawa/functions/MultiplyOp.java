package gnu.kawa.functions;

import gnu.mapping.LazyPropertyKey;
import gnu.mapping.Procedure;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import java.math.BigDecimal;
import java.math.BigInteger;

public class MultiplyOp
  extends ArithOp
{
  public static final MultiplyOp $St = new MultiplyOp("*");
  
  public MultiplyOp(String paramString)
  {
    super(paramString, 3);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
    Procedure.compilerKey.set(this, "*gnu.kawa.functions.CompileArith:forMul");
  }
  
  public static Object apply(Object paramObject1, Object paramObject2)
  {
    return ((Numeric)paramObject1).mul(paramObject2);
  }
  
  public Object applyN(Object[] paramArrayOfObject)
  {
    int n = paramArrayOfObject.length;
    if (n == 0) {
      localObject2 = IntNum.one();
    }
    Object localObject1;
    int j;
    int i;
    do
    {
      return localObject2;
      localObject1 = (Number)paramArrayOfObject[0];
      j = Arithmetic.classifyValue(localObject1);
      i = 1;
      localObject2 = localObject1;
    } while (i >= n);
    Object localObject2 = paramArrayOfObject[i];
    int m = Arithmetic.classifyValue(localObject2);
    int k = j;
    if (j < m) {
      k = m;
    }
    switch (k)
    {
    default: 
      localObject1 = Arithmetic.asNumeric(localObject1).mul(Arithmetic.asNumeric(localObject2));
    }
    for (;;)
    {
      i += 1;
      j = k;
      break;
      localObject1 = new Integer(Arithmetic.asInt(localObject1) * Arithmetic.asInt(localObject2));
      continue;
      localObject1 = new Long(Arithmetic.asLong(localObject1) * Arithmetic.asLong(localObject2));
      continue;
      localObject1 = Arithmetic.asBigInteger(localObject1).multiply(Arithmetic.asBigInteger(localObject2));
      continue;
      localObject1 = IntNum.times(Arithmetic.asIntNum(localObject1), Arithmetic.asIntNum(localObject2));
      continue;
      localObject1 = Arithmetic.asBigDecimal(localObject1).multiply(Arithmetic.asBigDecimal(localObject2));
      continue;
      localObject1 = RatNum.times(Arithmetic.asRatNum(localObject1), Arithmetic.asRatNum(localObject2));
      continue;
      localObject1 = new Float(Arithmetic.asFloat(localObject1) * Arithmetic.asFloat(localObject2));
      continue;
      localObject1 = new Double(Arithmetic.asDouble(localObject1) * Arithmetic.asDouble(localObject2));
      continue;
      localObject1 = new DFloNum(Arithmetic.asDouble(localObject1) * Arithmetic.asDouble(localObject2));
    }
  }
  
  public Object defaultResult()
  {
    return IntNum.one();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\MultiplyOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */