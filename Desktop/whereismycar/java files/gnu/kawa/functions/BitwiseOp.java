package gnu.kawa.functions;

import gnu.kawa.lispexpr.LangObjType;
import gnu.mapping.LazyPropertyKey;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import gnu.math.BitOps;
import gnu.math.IntNum;
import java.math.BigInteger;

public class BitwiseOp
  extends ArithOp
{
  public static final BitwiseOp and = new BitwiseOp("bitwise-and", 13);
  public static final BitwiseOp ashift = new BitwiseOp("bitwise-arithmetic-shift", 9);
  public static final BitwiseOp ashiftl = new BitwiseOp("bitwise-arithmetic-shift-left", 10);
  public static final BitwiseOp ashiftr = new BitwiseOp("bitwise-arithmetic-shift-right", 11);
  public static final BitwiseOp ior = new BitwiseOp("bitwise-ior", 14);
  public static final BitwiseOp not = new BitwiseOp("bitwise-not", 16);
  public static final BitwiseOp xor = new BitwiseOp("bitwise-xor", 15);
  
  public BitwiseOp(String paramString, int paramInt)
  {
    super(paramString, paramInt);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
    Procedure.compilerKey.set(this, "*gnu.kawa.functions.CompileArith:forBitwise");
  }
  
  public static int checkNonNegativeShift(Procedure paramProcedure, int paramInt)
  {
    if (paramInt < 0) {
      throw new WrongType(paramProcedure, 2, Integer.valueOf(paramInt), "non-negative integer");
    }
    return paramInt;
  }
  
  public static IntNum shiftLeft(IntNum paramIntNum, int paramInt)
  {
    return IntNum.shift(paramIntNum, checkNonNegativeShift(ashiftl, paramInt));
  }
  
  public static IntNum shiftRight(IntNum paramIntNum, int paramInt)
  {
    return IntNum.shift(paramIntNum, -checkNonNegativeShift(ashiftr, paramInt));
  }
  
  public Object adjustResult(IntNum paramIntNum, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return paramIntNum;
    case 1: 
      return Integer.valueOf(paramIntNum.intValue());
    case 2: 
      return Long.valueOf(paramIntNum.longValue());
    }
    return new BigInteger(paramIntNum.toString());
  }
  
  public Object apply1(Object paramObject)
  {
    if (this.op == 16)
    {
      int i = Arithmetic.classifyValue(paramObject);
      return adjustResult(BitOps.not(LangObjType.coerceIntNum(paramObject)), i);
    }
    return apply2(defaultResult(), paramObject);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
  {
    int i = Arithmetic.classifyValue(paramObject1);
    int j = Arithmetic.classifyValue(paramObject2);
    if (((this.op >= 9) && (this.op <= 12)) || (i <= 0) || ((i > j) && (j > 0))) {}
    for (;;)
    {
      paramObject1 = LangObjType.coerceIntNum(paramObject1);
      paramObject2 = LangObjType.coerceIntNum(paramObject2);
      switch (this.op)
      {
      case 12: 
      default: 
        throw new Error();
        i = j;
      }
    }
    paramObject1 = BitOps.and((IntNum)paramObject1, (IntNum)paramObject2);
    for (;;)
    {
      return adjustResult((IntNum)paramObject1, i);
      paramObject1 = BitOps.ior((IntNum)paramObject1, (IntNum)paramObject2);
      continue;
      paramObject1 = BitOps.xor((IntNum)paramObject1, (IntNum)paramObject2);
      continue;
      int k = ((IntNum)paramObject2).intValue();
      if (this.op != 11)
      {
        j = k;
        if (this.op != 10) {}
      }
      else
      {
        checkNonNegativeShift(this, k);
        j = k;
        if (this.op == 11) {
          j = -k;
        }
      }
      paramObject1 = IntNum.shift((IntNum)paramObject1, j);
    }
  }
  
  public Object applyN(Object[] paramArrayOfObject)
  {
    int j = paramArrayOfObject.length;
    Object localObject2;
    if (j == 0)
    {
      localObject2 = defaultResult();
      return localObject2;
    }
    if (j == 1) {
      return apply1(paramArrayOfObject[0]);
    }
    Object localObject1 = paramArrayOfObject[0];
    int i = 1;
    for (;;)
    {
      localObject2 = localObject1;
      if (i >= j) {
        break;
      }
      localObject1 = apply2(localObject1, paramArrayOfObject[i]);
      i += 1;
    }
  }
  
  public Object defaultResult()
  {
    if (this.op == 13) {
      return IntNum.minusOne();
    }
    return IntNum.zero();
  }
  
  public int numArgs()
  {
    if ((this.op >= 9) && (this.op <= 12)) {
      return 8194;
    }
    if (this.op == 16) {
      return 4097;
    }
    return 61440;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\BitwiseOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */