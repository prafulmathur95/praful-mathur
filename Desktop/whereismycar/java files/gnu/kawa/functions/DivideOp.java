package gnu.kawa.functions;

import gnu.mapping.LazyPropertyKey;
import gnu.mapping.Procedure;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import gnu.math.RealNum;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DivideOp
  extends ArithOp
{
  public static final DivideOp $Sl = new DivideOp("/", 4);
  public static final DivideOp div;
  public static final DivideOp div0;
  public static final DivideOp idiv = new DivideOp("idiv", 7);
  public static final DivideOp mod;
  public static final DivideOp mod0;
  public static final DivideOp modulo;
  public static final DivideOp quotient = new DivideOp("quotient", 6);
  public static final DivideOp remainder = new DivideOp("remainder", 8);
  int rounding_mode;
  
  static
  {
    modulo = new DivideOp("modulo", 8);
    div = new DivideOp("div", 6);
    mod = new DivideOp("mod", 8);
    div0 = new DivideOp("div0", 6);
    mod0 = new DivideOp("mod0", 8);
    idiv.rounding_mode = 3;
    quotient.rounding_mode = 3;
    remainder.rounding_mode = 3;
    modulo.rounding_mode = 1;
    div.rounding_mode = 5;
    mod.rounding_mode = 5;
    div0.rounding_mode = 4;
    mod0.rounding_mode = 4;
  }
  
  public DivideOp(String paramString, int paramInt)
  {
    super(paramString, paramInt);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
    Procedure.compilerKey.set(this, "*gnu.kawa.functions.CompileArith:forDiv");
  }
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    int i1 = paramArrayOfObject.length;
    if (i1 == 0)
    {
      paramArrayOfObject = IntNum.one();
      label13:
      return paramArrayOfObject;
    }
    Object localObject2 = (Number)paramArrayOfObject[0];
    if (i1 == 1) {
      return apply2(IntNum.one(), localObject2);
    }
    int j = Arithmetic.classifyValue(localObject2);
    int n = 1;
    if (n < i1)
    {
      Object localObject1 = paramArrayOfObject[n];
      int k = Arithmetic.classifyValue(localObject1);
      int i = j;
      if (j < k) {
        i = k;
      }
      j = i;
      int m = i;
      k = j;
      if (i < 4) {}
      switch (this.op)
      {
      default: 
        if (this.rounding_mode == 3)
        {
          m = i;
          k = j;
          if (i != 1)
          {
            if (i == 2)
            {
              k = j;
              m = i;
            }
          }
          else
          {
            label164:
            if ((this.op != 5) || (m > 10)) {
              break label339;
            }
            k = 10;
            i = m;
            j = k;
            if (m != 8)
            {
              i = m;
              j = k;
              if (m != 7)
              {
                i = 9;
                j = k;
              }
            }
          }
        }
        break;
      }
      Object localObject4;
      for (;;)
      {
        switch (j)
        {
        case 3: 
        case 6: 
        case 7: 
        case 8: 
        default: 
          localObject2 = Arithmetic.asNumeric(localObject2);
          localObject4 = Arithmetic.asNumeric(localObject1);
          if ((this.op != 8) || (!((Numeric)localObject4).isZero())) {
            break label1105;
          }
          paramArrayOfObject = (Object[])localObject2;
          if (((Numeric)localObject4).isExact()) {
            break label13;
          }
          return ((Numeric)localObject2).toInexact();
          m = 4;
          k = 4;
          break label164;
          k = 4;
          m = i;
          break label164;
          label339:
          if (k != 8)
          {
            i = m;
            j = k;
            if (k != 7) {}
          }
          else
          {
            k = 9;
            i = m;
            j = k;
            if (this.op == 7)
            {
              i = 9;
              j = k;
            }
          }
          break;
        }
      }
      k = Arithmetic.asInt(localObject2);
      m = Arithmetic.asInt(localObject1);
      switch (this.op)
      {
      default: 
        k /= m;
        label435:
        localObject1 = Integer.valueOf(k);
        localObject2 = localObject1;
        if (i != j)
        {
          localObject2 = localObject1;
          switch (i)
          {
          default: 
            localObject2 = localObject1;
          }
        }
        break;
      }
      for (;;)
      {
        n += 1;
        j = i;
        break;
        k %= m;
        break label435;
        long l1 = Arithmetic.asLong(localObject2);
        long l2 = Arithmetic.asLong(localObject1);
        switch (this.op)
        {
        }
        for (l1 /= l2;; l1 %= l2)
        {
          localObject1 = Long.valueOf(l1);
          break;
        }
        switch (this.op)
        {
        case 5: 
        default: 
          localObject1 = localObject2;
          break;
        case 4: 
          localObject1 = RatNum.make(Arithmetic.asIntNum(localObject2), Arithmetic.asIntNum(localObject1));
          if (!(localObject1 instanceof IntNum)) {}
        case 6: 
        case 7: 
        case 8: 
          for (i = 4;; i = 6)
          {
            j = i;
            break;
            localObject1 = IntNum.quotient(Arithmetic.asIntNum(localObject2), Arithmetic.asIntNum(localObject1), getRoundingMode());
            break;
            localObject1 = IntNum.remainder(Arithmetic.asIntNum(localObject2), Arithmetic.asIntNum(localObject1), getRoundingMode());
            break;
          }
          Object localObject3 = Arithmetic.asBigDecimal(localObject2);
          localObject4 = Arithmetic.asBigDecimal(localObject1);
          switch (getRoundingMode())
          {
          case 4: 
          default: 
            localObject1 = RoundingMode.HALF_EVEN;
          case 1: 
          case 2: 
          case 3: 
            for (;;)
            {
              localObject1 = new MathContext(0, (RoundingMode)localObject1);
              switch (this.op)
              {
              case 5: 
              default: 
                localObject1 = localObject2;
                break;
              case 4: 
                localObject1 = ((BigDecimal)localObject3).divide((BigDecimal)localObject4);
                break;
                localObject1 = RoundingMode.FLOOR;
                continue;
                localObject1 = RoundingMode.CEILING;
                continue;
                localObject1 = RoundingMode.DOWN;
              }
            }
          }
          if (((BigDecimal)localObject4).signum() < 0) {}
          for (localObject1 = RoundingMode.CEILING;; localObject1 = RoundingMode.FLOOR) {
            break;
          }
          localObject1 = ((BigDecimal)localObject3).divideToIntegralValue((BigDecimal)localObject4, (MathContext)localObject1);
          break;
          localObject1 = ((BigDecimal)localObject3).divideToIntegralValue((BigDecimal)localObject4, (MathContext)localObject1).toBigInteger();
          j = 3;
          i = 3;
          break;
          localObject1 = ((BigDecimal)localObject3).remainder((BigDecimal)localObject4, (MathContext)localObject1);
          break;
          double d2 = Arithmetic.asDouble(localObject2);
          double d3 = Arithmetic.asDouble(localObject1);
          switch (this.op)
          {
          default: 
            localObject1 = localObject2;
            break;
          case 4: 
          case 5: 
            localObject1 = DFloNum.make(d2 / d3);
            break;
          case 6: 
            localObject1 = Double.valueOf(RealNum.toInt(d2 / d3, getRoundingMode()));
            break;
          case 7: 
            localObject1 = RealNum.toExactInt(d2 / d3, getRoundingMode());
            j = 4;
            i = 4;
            break;
          case 8: 
            double d1 = d2;
            if (d3 != 0.0D) {
              d1 = d2 - RealNum.toInt(d2 / d3, getRoundingMode()) * d3;
            }
            localObject1 = DFloNum.make(d1);
            break;
            label1105:
            localObject3 = ((Numeric)localObject2).div(localObject4);
            localObject1 = localObject3;
            if (this.op == 8) {
              localObject1 = ((Numeric)localObject2).sub(((RealNum)localObject3).toInt(getRoundingMode()).mul(localObject4));
            }
            switch (this.op)
            {
            default: 
              break;
            case 7: 
              localObject1 = ((RealNum)localObject1).toExactInt(this.rounding_mode);
              i = 4;
              j = 4;
              break;
            case 6: 
              localObject1 = ((RealNum)localObject1).toInt(this.rounding_mode);
              break;
            case 5: 
              localObject1 = ((Numeric)localObject1).toInexact();
              break;
              localObject2 = Integer.valueOf(((Number)localObject1).intValue());
              continue;
              localObject2 = Long.valueOf(((Number)localObject1).longValue());
              continue;
              localObject2 = Float.valueOf(((Number)localObject1).floatValue());
              continue;
              localObject2 = Double.valueOf(((Number)localObject1).doubleValue());
              continue;
              localObject2 = Arithmetic.asBigInteger(localObject1);
            }
            break;
          }
          break;
        }
      }
    }
    return localObject2;
  }
  
  public int getRoundingMode()
  {
    return this.rounding_mode;
  }
  
  public int numArgs()
  {
    if (this.op == 4) {
      return 61441;
    }
    return 8194;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\DivideOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */