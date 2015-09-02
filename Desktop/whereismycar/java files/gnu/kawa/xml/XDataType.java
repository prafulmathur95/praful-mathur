package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.reflect.InstanceOf;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.DateTime;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.math.RealNum;
import gnu.math.Unit;
import gnu.text.Path;
import gnu.text.Printable;
import gnu.text.URIPath;
import gnu.xml.TextUtils;
import java.math.BigDecimal;

public class XDataType
  extends Type
  implements TypeValue
{
  public static final int ANY_ATOMIC_TYPE_CODE = 3;
  public static final int ANY_SIMPLE_TYPE_CODE = 2;
  public static final int ANY_URI_TYPE_CODE = 33;
  public static final int BASE64_BINARY_TYPE_CODE = 34;
  public static final int BOOLEAN_TYPE_CODE = 31;
  public static final int BYTE_TYPE_CODE = 11;
  public static final int DATE_TIME_TYPE_CODE = 20;
  public static final int DATE_TYPE_CODE = 21;
  public static final int DAY_TIME_DURATION_TYPE_CODE = 30;
  public static final BigDecimal DECIMAL_ONE = BigDecimal.valueOf(1L);
  public static final int DECIMAL_TYPE_CODE = 4;
  public static final Double DOUBLE_ONE;
  public static final int DOUBLE_TYPE_CODE = 19;
  public static final Double DOUBLE_ZERO;
  public static final int DURATION_TYPE_CODE = 28;
  public static final int ENTITY_TYPE_CODE = 47;
  public static final Float FLOAT_ONE;
  public static final int FLOAT_TYPE_CODE = 18;
  public static final Float FLOAT_ZERO;
  public static final int G_DAY_TYPE_CODE = 26;
  public static final int G_MONTH_DAY_TYPE_CODE = 25;
  public static final int G_MONTH_TYPE_CODE = 27;
  public static final int G_YEAR_MONTH_TYPE_CODE = 23;
  public static final int G_YEAR_TYPE_CODE = 24;
  public static final int HEX_BINARY_TYPE_CODE = 35;
  public static final int IDREF_TYPE_CODE = 46;
  public static final int ID_TYPE_CODE = 45;
  public static final int INTEGER_TYPE_CODE = 5;
  public static final int INT_TYPE_CODE = 9;
  public static final int LANGUAGE_TYPE_CODE = 41;
  public static final int LONG_TYPE_CODE = 8;
  public static final int NAME_TYPE_CODE = 43;
  public static final int NCNAME_TYPE_CODE = 44;
  public static final int NEGATIVE_INTEGER_TYPE_CODE = 7;
  public static final int NMTOKEN_TYPE_CODE = 42;
  public static final int NONNEGATIVE_INTEGER_TYPE_CODE = 12;
  public static final int NON_POSITIVE_INTEGER_TYPE_CODE = 6;
  public static final int NORMALIZED_STRING_TYPE_CODE = 39;
  public static final int NOTATION_TYPE_CODE = 36;
  public static final XDataType NotationType;
  public static final int POSITIVE_INTEGER_TYPE_CODE = 17;
  public static final int QNAME_TYPE_CODE = 32;
  public static final int SHORT_TYPE_CODE = 10;
  public static final int STRING_TYPE_CODE = 38;
  public static final int TIME_TYPE_CODE = 22;
  public static final int TOKEN_TYPE_CODE = 40;
  public static final int UNSIGNED_BYTE_TYPE_CODE = 16;
  public static final int UNSIGNED_INT_TYPE_CODE = 14;
  public static final int UNSIGNED_LONG_TYPE_CODE = 13;
  public static final int UNSIGNED_SHORT_TYPE_CODE = 15;
  public static final int UNTYPED_ATOMIC_TYPE_CODE = 37;
  public static final int UNTYPED_TYPE_CODE = 48;
  public static final int YEAR_MONTH_DURATION_TYPE_CODE = 29;
  public static final XDataType anyAtomicType;
  public static final XDataType anySimpleType = new XDataType("anySimpleType", Type.objectType, 2);
  public static final XDataType anyURIType;
  public static final XDataType base64BinaryType;
  public static final XDataType booleanType;
  public static final XDataType dayTimeDurationType;
  public static final XDataType decimalType;
  public static final XDataType doubleType;
  public static final XDataType durationType;
  public static final XDataType floatType;
  public static final XDataType hexBinaryType;
  public static final XDataType stringStringType;
  public static final XDataType stringType;
  public static final XDataType untypedAtomicType;
  public static final XDataType untypedType;
  public static final XDataType yearMonthDurationType;
  XDataType baseType;
  Type implementationType;
  Object name;
  int typeCode;
  
  static
  {
    anyAtomicType = new XDataType("anyAtomicType", Type.objectType, 3);
    stringType = new XDataType("string", ClassType.make("java.lang.CharSequence"), 38);
    stringStringType = new XDataType("String", ClassType.make("java.lang.String"), 38);
    untypedAtomicType = new XDataType("string", ClassType.make("gnu.kawa.xml.UntypedAtomic"), 37);
    base64BinaryType = new XDataType("base64Binary", ClassType.make("gnu.kawa.xml.Base64Binary"), 34);
    hexBinaryType = new XDataType("hexBinary", ClassType.make("gnu.kawa.xml.HexBinary"), 35);
    booleanType = new XDataType("boolean", Type.booleanType, 31);
    anyURIType = new XDataType("anyURI", ClassType.make("gnu.text.Path"), 33);
    NotationType = new XDataType("NOTATION", ClassType.make("gnu.kawa.xml.Notation"), 36);
    decimalType = new XDataType("decimal", ClassType.make("java.lang.Number"), 4);
    floatType = new XDataType("float", ClassType.make("java.lang.Float"), 18);
    doubleType = new XDataType("double", ClassType.make("java.lang.Double"), 19);
    durationType = new XDataType("duration", ClassType.make("gnu.math.Duration"), 28);
    yearMonthDurationType = new XDataType("yearMonthDuration", ClassType.make("gnu.math.Duration"), 29);
    dayTimeDurationType = new XDataType("dayTimeDuration", ClassType.make("gnu.math.Duration"), 30);
    untypedType = new XDataType("untyped", Type.objectType, 48);
    DOUBLE_ZERO = makeDouble(0.0D);
    DOUBLE_ONE = makeDouble(1.0D);
    FLOAT_ZERO = makeFloat(0.0F);
    FLOAT_ONE = makeFloat(1.0F);
  }
  
  public XDataType(Object paramObject, Type paramType, int paramInt)
  {
    super(paramType);
    this.name = paramObject;
    if (paramObject != null) {
      setName(paramObject.toString());
    }
    this.implementationType = paramType;
    this.typeCode = paramInt;
  }
  
  public static Double makeDouble(double paramDouble)
  {
    return Double.valueOf(paramDouble);
  }
  
  public static Float makeFloat(float paramFloat)
  {
    return Float.valueOf(paramFloat);
  }
  
  public Object cast(Object paramObject)
  {
    paramObject = KNode.atomicValue(paramObject);
    if ((paramObject instanceof UntypedAtomic))
    {
      if (this.typeCode == 37) {
        return paramObject;
      }
      return valueOf(paramObject.toString());
    }
    if ((paramObject instanceof String)) {
      return valueOf(paramObject.toString());
    }
    switch (this.typeCode)
    {
    }
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
              do
              {
                do
                {
                  return coerceFromObject(paramObject);
                  return TextUtils.asString(paramObject);
                  return new UntypedAtomic(TextUtils.stringValue(paramObject));
                  return URIPath.makeURI(paramObject);
                  if ((paramObject instanceof Boolean))
                  {
                    if (((Boolean)paramObject).booleanValue()) {}
                    for (paramObject = Boolean.TRUE;; paramObject = Boolean.FALSE) {
                      return paramObject;
                    }
                  }
                } while (!(paramObject instanceof Number));
                double d = ((Number)paramObject).doubleValue();
                if ((d == 0.0D) || (Double.isNaN(d))) {}
                for (paramObject = Boolean.FALSE;; paramObject = Boolean.TRUE) {
                  return paramObject;
                }
                if ((paramObject instanceof BigDecimal)) {
                  break;
                }
                if ((paramObject instanceof RealNum)) {
                  return ((RealNum)paramObject).asBigDecimal();
                }
                if (((paramObject instanceof Float)) || ((paramObject instanceof Double))) {
                  return BigDecimal.valueOf(((Number)paramObject).doubleValue());
                }
              } while (!(paramObject instanceof Boolean));
              if (((Boolean)paramObject).booleanValue()) {}
              for (paramObject = IntNum.one();; paramObject = IntNum.zero()) {
                return cast(paramObject);
              }
              if ((paramObject instanceof Float)) {
                break;
              }
              if ((paramObject instanceof Number)) {
                return makeFloat(((Number)paramObject).floatValue());
              }
            } while (!(paramObject instanceof Boolean));
            if (((Boolean)paramObject).booleanValue()) {}
            for (paramObject = FLOAT_ONE;; paramObject = FLOAT_ZERO) {
              return paramObject;
            }
            if ((paramObject instanceof Double)) {
              break;
            }
            if ((paramObject instanceof Number)) {
              return makeDouble(((Number)paramObject).doubleValue());
            }
          } while (!(paramObject instanceof Boolean));
          if (((Boolean)paramObject).booleanValue()) {}
          for (paramObject = DOUBLE_ONE;; paramObject = DOUBLE_ZERO) {
            return paramObject;
          }
        } while (!(paramObject instanceof DateTime));
        i = XTimeType.components(((XTimeType)this).typeCode);
        paramObject = (DateTime)paramObject;
        int j = ((DateTime)paramObject).components();
        if ((i == j) || ((j & 0xE) == 14)) {
          return ((DateTime)paramObject).cast(i);
        }
        throw new ClassCastException();
      } while (!(paramObject instanceof DateTime));
      int i = XTimeType.components(((XTimeType)this).typeCode);
      return ((DateTime)paramObject).cast(i);
      return castToDuration(paramObject, Unit.duration);
      return castToDuration(paramObject, Unit.month);
      return castToDuration(paramObject, Unit.second);
      if ((paramObject instanceof BinaryObject)) {
        return new Base64Binary(((BinaryObject)paramObject).getBytes());
      }
    } while (!(paramObject instanceof BinaryObject));
    return new HexBinary(((BinaryObject)paramObject).getBytes());
  }
  
  Duration castToDuration(Object paramObject, Unit paramUnit)
  {
    if ((paramObject instanceof Duration))
    {
      paramObject = (Duration)paramObject;
      if (((Duration)paramObject).unit() == paramUnit) {
        return (Duration)paramObject;
      }
      int i = ((Duration)paramObject).getTotalMonths();
      long l = ((Duration)paramObject).getTotalSeconds();
      int j = ((Duration)paramObject).getNanoSecondsOnly();
      if (paramUnit == Unit.second) {
        i = 0;
      }
      if (paramUnit == Unit.month)
      {
        l = 0L;
        j = 0;
      }
      return Duration.make(i, l, j, paramUnit);
    }
    return (Duration)coerceFromObject(paramObject);
  }
  
  public boolean castable(Object paramObject)
  {
    try
    {
      cast(paramObject);
      return true;
    }
    catch (Throwable paramObject) {}
    return false;
  }
  
  public Object coerceFromObject(Object paramObject)
  {
    if (!isInstance(paramObject)) {
      throw new ClassCastException("cannot cast " + paramObject + " to " + this.name);
    }
    return paramObject;
  }
  
  public int compare(Type paramType)
  {
    if ((this == paramType) || ((this == stringStringType) && (paramType == stringType)) || ((this == stringType) && (paramType == stringStringType))) {
      return 0;
    }
    return this.implementationType.compare(paramType);
  }
  
  public Expression convertValue(Expression paramExpression)
  {
    return null;
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr)
  {
    Compilation.getCurrent().compileConstant(this, Target.pushObject);
    Method localMethod = ClassType.make("gnu.kawa.xml.XDataType").getDeclaredMethod("coerceFromObject", 1);
    paramCodeAttr.emitSwap();
    paramCodeAttr.emitInvokeVirtual(localMethod);
    this.implementationType.emitCoerceFromObject(paramCodeAttr);
  }
  
  public void emitCoerceToObject(CodeAttr paramCodeAttr)
  {
    if (this.typeCode == 31)
    {
      this.implementationType.emitCoerceToObject(paramCodeAttr);
      return;
    }
    super.emitCoerceToObject(paramCodeAttr);
  }
  
  public void emitIsInstance(Variable paramVariable, Compilation paramCompilation, Target paramTarget)
  {
    InstanceOf.emitIsInstance(this, paramVariable, paramCompilation, paramTarget);
  }
  
  public void emitTestIf(Variable paramVariable, Declaration paramDeclaration, Compilation paramCompilation)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    if (this.typeCode == 31)
    {
      if (paramVariable != null) {
        localCodeAttr.emitLoad(paramVariable);
      }
      Type.javalangBooleanType.emitIsInstance(localCodeAttr);
      localCodeAttr.emitIfIntNotZero();
      if (paramDeclaration != null)
      {
        localCodeAttr.emitLoad(paramVariable);
        Type.booleanType.emitCoerceFromObject(localCodeAttr);
        paramDeclaration.compileStore(paramCompilation);
      }
      return;
    }
    paramCompilation.compileConstant(this, Target.pushObject);
    if (paramVariable == null) {
      localCodeAttr.emitSwap();
    }
    for (;;)
    {
      localCodeAttr.emitInvokeVirtual(Compilation.typeType.getDeclaredMethod("isInstance", 1));
      localCodeAttr.emitIfIntNotZero();
      if (paramDeclaration == null) {
        break;
      }
      localCodeAttr.emitLoad(paramVariable);
      emitCoerceFromObject(localCodeAttr);
      paramDeclaration.compileStore(paramCompilation);
      return;
      localCodeAttr.emitLoad(paramVariable);
    }
  }
  
  public Procedure getConstructor()
  {
    return null;
  }
  
  public Type getImplementationType()
  {
    return this.implementationType;
  }
  
  public Class getReflectClass()
  {
    return this.implementationType.getReflectClass();
  }
  
  public boolean isInstance(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool3 = true;
    boolean bool1 = bool3;
    switch (this.typeCode)
    {
    default: 
      bool1 = super.isInstance(paramObject);
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return bool1;
            if ((paramObject instanceof SeqPosition)) {
              break;
            }
            bool1 = bool3;
          } while (!(paramObject instanceof Nodes));
          return false;
          if ((paramObject instanceof Values)) {
            break;
          }
          bool1 = bool3;
        } while (!(paramObject instanceof SeqPosition));
        return false;
        return paramObject instanceof CharSequence;
        return paramObject instanceof UntypedAtomic;
        return paramObject instanceof Path;
        return paramObject instanceof Boolean;
        return paramObject instanceof Float;
        return paramObject instanceof Double;
        if (!(paramObject instanceof BigDecimal))
        {
          bool1 = bool2;
          if (!(paramObject instanceof IntNum)) {}
        }
        else
        {
          bool1 = true;
        }
        return bool1;
        return paramObject instanceof Duration;
        if (!(paramObject instanceof Duration)) {
          break;
        }
        bool1 = bool3;
      } while (((Duration)paramObject).unit() == Unit.month);
      return false;
      if (!(paramObject instanceof Duration)) {
        break;
      }
      bool1 = bool3;
    } while (((Duration)paramObject).unit() == Unit.second);
    return false;
  }
  
  public void print(Object paramObject, Consumer paramConsumer)
  {
    if ((paramObject instanceof Printable))
    {
      ((Printable)paramObject).print(paramConsumer);
      return;
    }
    paramConsumer.write(toString(paramObject));
  }
  
  public String toString(Object paramObject)
  {
    return paramObject.toString();
  }
  
  public Object valueOf(String paramString)
  {
    switch (this.typeCode)
    {
    default: 
      throw new RuntimeException("valueOf not implemented for " + this.name);
    case 38: 
      return paramString;
    case 37: 
      return new UntypedAtomic(paramString);
    case 33: 
      return URIPath.makeURI(TextUtils.replaceWhitespace(paramString, true));
    case 31: 
      paramString = paramString.trim();
      if ((paramString.equals("true")) || (paramString.equals("1"))) {
        return Boolean.TRUE;
      }
      if ((paramString.equals("false")) || (paramString.equals("0"))) {
        return Boolean.FALSE;
      }
      throw new IllegalArgumentException("not a valid boolean: '" + paramString + "'");
    case 18: 
    case 19: 
      String str = paramString.trim();
      if ("INF".equals(str)) {
        paramString = "Infinity";
      }
      while (this.typeCode == 18)
      {
        return Float.valueOf(paramString);
        paramString = str;
        if ("-INF".equals(str)) {
          paramString = "-Infinity";
        }
      }
      return Double.valueOf(paramString);
    case 4: 
      paramString = paramString.trim();
      int i = paramString.length();
      int j;
      do
      {
        i -= 1;
        if (i < 0) {
          break;
        }
        j = paramString.charAt(i);
      } while ((j != 101) && (j != 69));
      throw new IllegalArgumentException("not a valid decimal: '" + paramString + "'");
      return new BigDecimal(paramString);
    case 28: 
      return Duration.parseDuration(paramString);
    case 29: 
      return Duration.parseYearMonthDuration(paramString);
    case 30: 
      return Duration.parseDayTimeDuration(paramString);
    case 34: 
      return Base64Binary.valueOf(paramString);
    }
    return HexBinary.valueOf(paramString);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\XDataType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */