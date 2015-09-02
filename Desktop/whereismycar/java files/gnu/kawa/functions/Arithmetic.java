package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import gnu.math.RealNum;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Arithmetic
{
  public static final int BIGDECIMAL_CODE = 5;
  public static final int BIGINTEGER_CODE = 3;
  public static final int DOUBLE_CODE = 8;
  public static final int FLOAT_CODE = 7;
  public static final int FLONUM_CODE = 9;
  public static final int INTNUM_CODE = 4;
  public static final int INT_CODE = 1;
  public static final int LONG_CODE = 2;
  public static final int NUMERIC_CODE = 11;
  public static final int RATNUM_CODE = 6;
  public static final int REALNUM_CODE = 10;
  static LangObjType typeDFloNum = LangObjType.dflonumType;
  static LangObjType typeIntNum = LangObjType.integerType;
  static ClassType typeNumber;
  static ClassType typeNumeric;
  static LangObjType typeRatNum = LangObjType.rationalType;
  static LangObjType typeRealNum = LangObjType.realType;
  
  static
  {
    typeNumber = ClassType.make("java.lang.Number");
    typeNumeric = ClassType.make("gnu.math.Numeric");
  }
  
  public static BigDecimal asBigDecimal(Object paramObject)
  {
    if ((paramObject instanceof BigDecimal)) {
      return (BigDecimal)paramObject;
    }
    if ((paramObject instanceof BigInteger)) {
      return new BigDecimal((BigInteger)paramObject);
    }
    if (((paramObject instanceof Long)) || ((paramObject instanceof Integer)) || ((paramObject instanceof Short)) || ((paramObject instanceof Byte))) {
      return BigDecimal.valueOf(((Number)paramObject).longValue());
    }
    return new BigDecimal(paramObject.toString());
  }
  
  public static BigInteger asBigInteger(Object paramObject)
  {
    if ((paramObject instanceof BigInteger)) {
      return (BigInteger)paramObject;
    }
    if ((paramObject instanceof IntNum)) {
      return new BigInteger(paramObject.toString());
    }
    return BigInteger.valueOf(((Number)paramObject).longValue());
  }
  
  public static double asDouble(Object paramObject)
  {
    return ((Number)paramObject).doubleValue();
  }
  
  public static float asFloat(Object paramObject)
  {
    return ((Number)paramObject).floatValue();
  }
  
  public static int asInt(Object paramObject)
  {
    return ((Number)paramObject).intValue();
  }
  
  public static IntNum asIntNum(Object paramObject)
  {
    if ((paramObject instanceof IntNum)) {
      return (IntNum)paramObject;
    }
    if ((paramObject instanceof BigInteger)) {
      return IntNum.valueOf(paramObject.toString(), 10);
    }
    if ((paramObject instanceof BigDecimal)) {
      return asIntNum((BigDecimal)paramObject);
    }
    return IntNum.make(((Number)paramObject).longValue());
  }
  
  public static IntNum asIntNum(BigDecimal paramBigDecimal)
  {
    return IntNum.valueOf(paramBigDecimal.toBigInteger().toString(), 10);
  }
  
  public static IntNum asIntNum(BigInteger paramBigInteger)
  {
    return IntNum.valueOf(paramBigInteger.toString(), 10);
  }
  
  public static long asLong(Object paramObject)
  {
    return ((Number)paramObject).longValue();
  }
  
  public static Numeric asNumeric(Object paramObject)
  {
    Numeric localNumeric = Numeric.asNumericOrNull(paramObject);
    if (localNumeric != null) {
      return localNumeric;
    }
    return (Numeric)paramObject;
  }
  
  public static RatNum asRatNum(Object paramObject)
  {
    if ((paramObject instanceof RatNum)) {
      return (RatNum)paramObject;
    }
    if ((paramObject instanceof BigInteger)) {
      return IntNum.valueOf(paramObject.toString(), 10);
    }
    if ((paramObject instanceof BigDecimal)) {
      return RatNum.valueOf((BigDecimal)paramObject);
    }
    return IntNum.make(((Number)paramObject).longValue());
  }
  
  public static int classifyType(Type paramType)
  {
    int i = 8;
    int j;
    if ((paramType instanceof PrimType))
    {
      j = paramType.getSignature().charAt(0);
      if ((j == 86) || (j == 90) || (j == 67)) {
        i = 0;
      }
    }
    String str;
    do
    {
      do
      {
        return i;
      } while (j == 68);
      if (j == 70) {
        return 7;
      }
      if (j == 74) {
        return 2;
      }
      return 1;
      str = paramType.getName();
      if (paramType.isSubtype(typeIntNum)) {
        return 4;
      }
      if (paramType.isSubtype(typeRatNum)) {
        return 6;
      }
      if (paramType.isSubtype(typeDFloNum)) {
        return 9;
      }
    } while ("java.lang.Double".equals(str));
    if ("java.lang.Float".equals(str)) {
      return 7;
    }
    if ("java.lang.Long".equals(str)) {
      return 2;
    }
    if (("java.lang.Integer".equals(str)) || ("java.lang.Short".equals(str)) || ("java.lang.Byte".equals(str))) {
      return 1;
    }
    if ("java.math.BigInteger".equals(str)) {
      return 3;
    }
    if ("java.math.BigDecimal".equals(str)) {
      return 5;
    }
    if (paramType.isSubtype(typeRealNum)) {
      return 10;
    }
    if (paramType.isSubtype(typeNumeric)) {
      return 11;
    }
    return 0;
  }
  
  public static int classifyValue(Object paramObject)
  {
    int j = -1;
    int i;
    if ((paramObject instanceof Numeric)) {
      if ((paramObject instanceof IntNum)) {
        i = 4;
      }
    }
    do
    {
      do
      {
        return i;
        if ((paramObject instanceof RatNum)) {
          return 6;
        }
        if ((paramObject instanceof DFloNum)) {
          return 9;
        }
        if ((paramObject instanceof RealNum)) {
          return 10;
        }
        return 11;
        i = j;
      } while (!(paramObject instanceof Number));
      if (((paramObject instanceof Integer)) || ((paramObject instanceof Short)) || ((paramObject instanceof Byte))) {
        return 1;
      }
      if ((paramObject instanceof Long)) {
        return 2;
      }
      if ((paramObject instanceof Float)) {
        return 7;
      }
      if ((paramObject instanceof Double)) {
        return 8;
      }
      if ((paramObject instanceof BigInteger)) {
        return 3;
      }
      i = j;
    } while (!(paramObject instanceof BigDecimal));
    return 5;
  }
  
  public static Object convert(Object paramObject, int paramInt)
  {
    Object localObject;
    switch (paramInt)
    {
    default: 
      localObject = (Number)paramObject;
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return localObject;
                localObject = paramObject;
              } while ((paramObject instanceof Integer));
              return Integer.valueOf(((Number)paramObject).intValue());
              localObject = paramObject;
            } while ((paramObject instanceof Long));
            return Long.valueOf(((Number)paramObject).longValue());
            return asBigInteger(paramObject);
            return asIntNum(paramObject);
            return asBigDecimal(paramObject);
            return asRatNum(paramObject);
            localObject = paramObject;
          } while ((paramObject instanceof Float));
          return Float.valueOf(asFloat(paramObject));
          localObject = paramObject;
        } while ((paramObject instanceof Double));
        return Double.valueOf(asDouble(paramObject));
        localObject = paramObject;
      } while ((paramObject instanceof DFloNum));
      return DFloNum.make(asDouble(paramObject));
    case 11: 
      return asNumeric(paramObject);
    }
    return (RealNum)asNumeric(paramObject);
  }
  
  public static boolean isExact(Number paramNumber)
  {
    if ((paramNumber instanceof Numeric)) {
      return ((Numeric)paramNumber).isExact();
    }
    return (!(paramNumber instanceof Double)) && (!(paramNumber instanceof Float)) && (!(paramNumber instanceof BigDecimal));
  }
  
  public static Type kindType(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return Type.pointer_type;
    case 1: 
      return LangPrimType.intType;
    case 2: 
      return LangPrimType.longType;
    case 3: 
      return ClassType.make("java.math.BigInteger");
    case 4: 
      return typeIntNum;
    case 5: 
      return ClassType.make("java.math.BigDecimal");
    case 6: 
      return typeRatNum;
    case 7: 
      return LangPrimType.floatType;
    case 8: 
      return LangPrimType.doubleType;
    case 9: 
      return typeDFloNum;
    case 10: 
      return typeRealNum;
    }
    return typeNumeric;
  }
  
  public static Number toExact(Number paramNumber)
  {
    Object localObject;
    if ((paramNumber instanceof Numeric)) {
      localObject = ((Numeric)paramNumber).toExact();
    }
    do
    {
      return (Number)localObject;
      if (((paramNumber instanceof Double)) || ((paramNumber instanceof Float))) {
        break;
      }
      localObject = paramNumber;
    } while (!(paramNumber instanceof BigDecimal));
    return DFloNum.toExact(paramNumber.doubleValue());
  }
  
  public static Number toInexact(Number paramNumber)
  {
    Object localObject;
    if ((paramNumber instanceof Numeric)) {
      localObject = ((Numeric)paramNumber).toInexact();
    }
    do
    {
      do
      {
        do
        {
          return (Number)localObject;
          localObject = paramNumber;
        } while ((paramNumber instanceof Double));
        localObject = paramNumber;
      } while ((paramNumber instanceof Float));
      localObject = paramNumber;
    } while ((paramNumber instanceof BigDecimal));
    return Double.valueOf(paramNumber.doubleValue());
  }
  
  public static String toString(Object paramObject, int paramInt)
  {
    switch (classifyValue(paramObject))
    {
    }
    do
    {
      return asNumeric(paramObject).toString(paramInt);
      return Integer.toString(asInt(paramObject), paramInt);
      return Long.toString(asLong(paramObject), paramInt);
      return asBigInteger(paramObject).toString(paramInt);
      return asIntNum(paramObject).toString(paramInt);
      if (paramInt == 10) {
        return asBigDecimal(paramObject).toString();
      }
      if (paramInt == 10) {
        return Float.toString(asFloat(paramObject));
      }
    } while (paramInt != 10);
    return Double.toString(asDouble(paramObject));
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\Arithmetic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */