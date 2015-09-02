package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Filter;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.PairClassType;
import gnu.expr.PrimProcedure;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.functions.MakeList;
import gnu.kawa.reflect.InstanceOf;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import gnu.math.RealNum;
import gnu.text.FilePath;
import gnu.text.Path;
import gnu.text.URIPath;
import java.util.List;

public class LangObjType
  extends ObjectType
  implements TypeValue
{
  private static final int CLASSTYPE_TYPE_CODE = 6;
  private static final int CLASS_TYPE_CODE = 4;
  private static final int DFLONUM_TYPE_CODE = 15;
  private static final int FILEPATH_TYPE_CODE = 2;
  private static final int INTEGER_TYPE_CODE = 7;
  private static final int LIST_TYPE_CODE = 11;
  private static final int NUMERIC_TYPE_CODE = 10;
  private static final int PATH_TYPE_CODE = 1;
  private static final int RATIONAL_TYPE_CODE = 8;
  private static final int REAL_TYPE_CODE = 9;
  private static final int REGEX_TYPE_CODE = 14;
  private static final int STRING_TYPE_CODE = 13;
  private static final int TYPE_TYPE_CODE = 5;
  public static final LangObjType URIType;
  private static final int URI_TYPE_CODE = 3;
  static final String VARARGS_SUFFIX = "";
  private static final int VECTOR_TYPE_CODE = 12;
  public static final LangObjType dflonumType;
  public static final LangObjType filepathType;
  public static final LangObjType integerType;
  public static final LangObjType listType;
  static PrimProcedure makeFilepathProc = new PrimProcedure("gnu.text.FilePath", "makeFilePath", 1);
  static PrimProcedure makePathProc;
  static PrimProcedure makeURIProc = new PrimProcedure("gnu.text.URIPath", "makeURI", 1);
  public static final LangObjType numericType;
  public static final LangObjType pathType = new LangObjType("path", "gnu.text.Path", 1);
  public static final LangObjType rationalType;
  public static final LangObjType realType;
  public static final LangObjType regexType;
  public static final LangObjType stringType;
  static final ClassType typeArithmetic;
  public static final LangObjType typeClass;
  public static final LangObjType typeClassType;
  public static final ClassType typeLangObjType = ClassType.make("gnu.kawa.lispexpr.LangObjType");
  public static final LangObjType typeType;
  public static final LangObjType vectorType;
  ClassType implementationType;
  final int typeCode;
  
  static
  {
    filepathType = new LangObjType("filepath", "gnu.text.FilePath", 2);
    URIType = new LangObjType("URI", "gnu.text.URIPath", 3);
    typeClass = new LangObjType("class", "java.lang.Class", 4);
    typeType = new LangObjType("type", "gnu.bytecode.Type", 5);
    typeClassType = new LangObjType("class-type", "gnu.bytecode.ClassType", 6);
    numericType = new LangObjType("number", "gnu.math.Numeric", 10);
    realType = new LangObjType("real", "gnu.math.RealNum", 9);
    rationalType = new LangObjType("rational", "gnu.math.RatNum", 8);
    integerType = new LangObjType("integer", "gnu.math.IntNum", 7);
    dflonumType = new LangObjType("DFloNum", "gnu.math.DFloNum", 15);
    vectorType = new LangObjType("vector", "gnu.lists.FVector", 12);
    regexType = new LangObjType("regex", "java.util.regex.Pattern", 14);
    stringType = new LangObjType("string", "java.lang.CharSequence", 13);
    listType = new LangObjType("list", "gnu.lists.LList", 11);
    typeArithmetic = ClassType.make("gnu.kawa.functions.Arithmetic");
    makePathProc = new PrimProcedure("gnu.text.Path", "valueOf", 1);
  }
  
  LangObjType(String paramString1, String paramString2, int paramInt)
  {
    super(paramString1);
    this.implementationType = ClassType.make(paramString2);
    this.typeCode = paramInt;
    setSignature(this.implementationType.getSignature());
  }
  
  public static DFloNum coerceDFloNum(Object paramObject)
  {
    DFloNum localDFloNum = DFloNum.asDFloNumOrNull(paramObject);
    if ((localDFloNum == null) && (paramObject != null)) {
      throw new WrongType(-4, paramObject, dflonumType);
    }
    return localDFloNum;
  }
  
  public static IntNum coerceIntNum(Object paramObject)
  {
    IntNum localIntNum = IntNum.asIntNumOrNull(paramObject);
    if ((localIntNum == null) && (paramObject != null)) {
      throw new WrongType(-4, paramObject, integerType);
    }
    return localIntNum;
  }
  
  public static Numeric coerceNumeric(Object paramObject)
  {
    Numeric localNumeric = Numeric.asNumericOrNull(paramObject);
    if ((localNumeric == null) && (paramObject != null)) {
      throw new WrongType(-4, paramObject, numericType);
    }
    return localNumeric;
  }
  
  public static RatNum coerceRatNum(Object paramObject)
  {
    RatNum localRatNum = RatNum.asRatNumOrNull(paramObject);
    if ((localRatNum == null) && (paramObject != null)) {
      throw new WrongType(-4, paramObject, rationalType);
    }
    return localRatNum;
  }
  
  public static RealNum coerceRealNum(Object paramObject)
  {
    RealNum localRealNum = RealNum.asRealNumOrNull(paramObject);
    if ((localRealNum == null) && (paramObject != null)) {
      throw new WrongType(-4, paramObject, realType);
    }
    return localRealNum;
  }
  
  public static Class coerceToClass(Object paramObject)
  {
    Class localClass = coerceToClassOrNull(paramObject);
    if ((localClass == null) && (paramObject != null)) {
      throw new ClassCastException("cannot cast " + paramObject + " to type");
    }
    return localClass;
  }
  
  public static Class coerceToClassOrNull(Object paramObject)
  {
    if ((paramObject instanceof Class)) {
      return (Class)paramObject;
    }
    if (((paramObject instanceof Type)) && ((paramObject instanceof ClassType)) && (!(paramObject instanceof PairClassType))) {
      return ((ClassType)paramObject).getReflectClass();
    }
    return null;
  }
  
  public static ClassType coerceToClassType(Object paramObject)
  {
    ClassType localClassType = coerceToClassTypeOrNull(paramObject);
    if ((localClassType == null) && (paramObject != null)) {
      throw new ClassCastException("cannot cast " + paramObject + " to class-type");
    }
    return localClassType;
  }
  
  public static ClassType coerceToClassTypeOrNull(Object paramObject)
  {
    if ((paramObject instanceof ClassType)) {
      return (ClassType)paramObject;
    }
    if ((paramObject instanceof Class))
    {
      paramObject = Language.getDefaultLanguage().getTypeFor((Class)paramObject);
      if ((paramObject instanceof ClassType)) {
        return (ClassType)paramObject;
      }
    }
    return null;
  }
  
  public static Type coerceToType(Object paramObject)
  {
    Type localType = coerceToTypeOrNull(paramObject);
    if ((localType == null) && (paramObject != null)) {
      throw new ClassCastException("cannot cast " + paramObject + " to type");
    }
    return localType;
  }
  
  public static Type coerceToTypeOrNull(Object paramObject)
  {
    if ((paramObject instanceof Type)) {
      return (Type)paramObject;
    }
    if ((paramObject instanceof Class)) {
      return Language.getDefaultLanguage().getTypeFor((Class)paramObject);
    }
    return null;
  }
  
  public Object coerceFromObject(Object paramObject)
  {
    switch (this.typeCode)
    {
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    default: 
      return super.coerceFromObject(paramObject);
    case 1: 
      return Path.valueOf(paramObject);
    case 2: 
      return FilePath.makeFilePath(paramObject);
    case 3: 
      return URIPath.makeURI(paramObject);
    case 4: 
      return coerceToClass(paramObject);
    case 6: 
      return coerceToClassType(paramObject);
    case 5: 
      return coerceToType(paramObject);
    case 10: 
      return coerceNumeric(paramObject);
    case 9: 
      return coerceRealNum(paramObject);
    case 8: 
      return coerceRatNum(paramObject);
    case 7: 
      return coerceIntNum(paramObject);
    }
    return coerceDFloNum(paramObject);
  }
  
  Method coercionMethod()
  {
    switch (this.typeCode)
    {
    default: 
      return ((PrimProcedure)getConstructor()).getMethod();
    case 4: 
      return typeLangObjType.getDeclaredMethod("coerceToClass", 1);
    case 6: 
      return typeLangObjType.getDeclaredMethod("coerceToClassType", 1);
    case 5: 
      return typeLangObjType.getDeclaredMethod("coerceToType", 1);
    case 10: 
      return typeLangObjType.getDeclaredMethod("coerceNumeric", 1);
    case 9: 
      return typeLangObjType.getDeclaredMethod("coerceRealNum", 1);
    case 8: 
      return typeLangObjType.getDeclaredMethod("coerceRatNum", 1);
    case 7: 
      return typeLangObjType.getDeclaredMethod("coerceIntNum", 1);
    case 15: 
      return typeLangObjType.getDeclaredMethod("coerceDFloNum", 1);
    }
    return null;
  }
  
  Method coercionOrNullMethod()
  {
    ClassType localClassType = this.implementationType;
    String str;
    switch (this.typeCode)
    {
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    default: 
      return null;
    case 1: 
      str = "coerceToPathOrNull";
    }
    for (;;)
    {
      return localClassType.getDeclaredMethod(str, 1);
      str = "coerceToFilePathOrNull";
      continue;
      str = "coerceToURIPathOrNull";
      continue;
      localClassType = typeLangObjType;
      str = "coerceToClassOrNull";
      continue;
      localClassType = typeLangObjType;
      str = "coerceToClassTypeOrNull";
      continue;
      localClassType = typeLangObjType;
      str = "coerceToTypeOrNull";
      continue;
      localClassType = this.implementationType;
      str = "asNumericOrNull";
      continue;
      localClassType = this.implementationType;
      str = "asDFloNumOrNull";
      continue;
      localClassType = this.implementationType;
      str = "asRealNumOrNull";
      continue;
      localClassType = this.implementationType;
      str = "asRatNumOrNull";
      continue;
      localClassType = this.implementationType;
      str = "asIntNumOrNull";
    }
  }
  
  public int compare(Type paramType)
  {
    int j = -1;
    switch (this.typeCode)
    {
    }
    for (;;)
    {
      int i = getImplementationType().compare(paramType.getImplementationType());
      do
      {
        do
        {
          do
          {
            do
            {
              return i;
              i = j;
            } while (paramType == typeType);
            i = j;
          } while (paramType == typeClassType);
          i = j;
        } while (paramType == typeType.implementationType);
        if (paramType != typeClassType.implementationType) {
          break;
        }
        return -1;
        if ((paramType == typeClass) || (paramType == typeClassType) || (paramType == typeClass.implementationType) || (paramType == typeClassType.implementationType)) {
          return 1;
        }
        if ((paramType == typeClass) || (paramType == typeClass.implementationType)) {
          return 1;
        }
        i = j;
      } while (paramType == typeType);
      if (paramType == typeClass.implementationType)
      {
        return -1;
        if ((paramType instanceof PrimType)) {}
        switch (paramType.getSignature().charAt(0))
        {
        default: 
          if ((paramType instanceof PrimType)) {
            switch (paramType.getSignature().charAt(0))
            {
            }
          }
          break;
        }
      }
    }
    return 1;
    return 1;
  }
  
  public Expression convertValue(Expression paramExpression)
  {
    if ((this.typeCode == 7) || (this.typeCode == 10) || (this.typeCode == 9) || (this.typeCode == 8) || (this.typeCode == 15)) {}
    Method localMethod;
    do
    {
      return null;
      localMethod = coercionMethod();
    } while (localMethod == null);
    paramExpression = new ApplyExp(localMethod, new Expression[] { paramExpression });
    paramExpression.setType(this);
    return paramExpression;
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr)
  {
    switch (this.typeCode)
    {
    default: 
      paramCodeAttr.emitInvoke(coercionMethod());
      return;
    }
    paramCodeAttr.emitCheckcast(this.implementationType);
  }
  
  public void emitConvertFromPrimitive(Type paramType, CodeAttr paramCodeAttr)
  {
    Object localObject5 = null;
    Object localObject6 = null;
    Object localObject1 = localObject5;
    Object localObject2 = localObject6;
    Object localObject4 = paramType;
    switch (this.typeCode)
    {
    default: 
      localObject4 = paramType;
      localObject2 = localObject6;
      localObject1 = localObject5;
    }
    while (localObject2 != null)
    {
      paramCodeAttr.emitInvokeStatic(ClassType.make((String)localObject2).getDeclaredMethod("make", new Type[] { localObject1 }));
      return;
      localObject1 = localObject5;
      localObject2 = localObject6;
      localObject4 = paramType;
      if ((paramType instanceof PrimType))
      {
        Object localObject3;
        if ((paramType != Type.intType) && (paramType != Type.byteType) && (paramType != Type.shortType) && (paramType != Type.longType))
        {
          localObject3 = paramType;
          if (paramType != Type.floatType) {}
        }
        else
        {
          paramCodeAttr.emitConvert(paramType, Type.doubleType);
          localObject3 = Type.doubleType;
        }
        localObject1 = localObject5;
        localObject2 = localObject6;
        localObject4 = localObject3;
        if (localObject3 == Type.doubleType)
        {
          localObject2 = "gnu.math.DFloNum";
          localObject1 = localObject3;
          localObject4 = localObject3;
          continue;
          localObject1 = localObject5;
          localObject2 = localObject6;
          localObject4 = paramType;
          if ((paramType instanceof PrimType)) {
            if ((paramType == Type.intType) || (paramType == Type.byteType) || (paramType == Type.shortType))
            {
              localObject2 = "gnu.math.IntNum";
              localObject1 = Type.int_type;
              localObject4 = paramType;
            }
            else if (paramType == Type.longType)
            {
              localObject2 = "gnu.math.IntNum";
              localObject1 = Type.long_type;
              localObject4 = paramType;
            }
            else if (this.typeCode != 9)
            {
              localObject1 = localObject5;
              localObject2 = localObject6;
              localObject4 = paramType;
              if (this.typeCode != 10) {}
            }
            else
            {
              localObject3 = paramType;
              if (paramType == Type.floatType)
              {
                paramCodeAttr.emitConvert(Type.float_type, Type.double_type);
                localObject3 = Type.doubleType;
              }
              localObject1 = localObject5;
              localObject2 = localObject6;
              localObject4 = localObject3;
              if (localObject3 == Type.doubleType)
              {
                localObject2 = "gnu.math.DFloNum";
                localObject1 = Type.doubleType;
                localObject4 = localObject3;
              }
            }
          }
        }
      }
    }
    super.emitConvertFromPrimitive((Type)localObject4, paramCodeAttr);
  }
  
  public void emitIsInstance(Variable paramVariable, Compilation paramCompilation, Target paramTarget)
  {
    switch (this.typeCode)
    {
    default: 
      InstanceOf.emitIsInstance(this, paramVariable, paramCompilation, paramTarget);
      return;
    }
    this.implementationType.emitIsInstance(paramCompilation.getCode());
    paramTarget.compileFromStack(paramCompilation, paramCompilation.getLanguage().getTypeFor(Boolean.TYPE));
  }
  
  public void emitTestIf(Variable paramVariable, Declaration paramDeclaration, Compilation paramCompilation)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    if (paramVariable != null) {
      localCodeAttr.emitLoad(paramVariable);
    }
    paramVariable = coercionOrNullMethod();
    if (paramVariable != null) {
      localCodeAttr.emitInvokeStatic(paramVariable);
    }
    if (paramDeclaration != null)
    {
      localCodeAttr.emitDup();
      paramDeclaration.compileStore(paramCompilation);
    }
    if (paramVariable != null)
    {
      localCodeAttr.emitIfNotNull();
      return;
    }
    this.implementationType.emitIsInstance(localCodeAttr);
    localCodeAttr.emitIfIntNotZero();
  }
  
  public Procedure getConstructor()
  {
    switch (this.typeCode)
    {
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    default: 
      return null;
    case 1: 
      return makePathProc;
    case 2: 
      return makeFilepathProc;
    case 3: 
      return makeURIProc;
    case 12: 
      return new PrimProcedure("gnu.lists.FVector", "make", 1);
    case 11: 
      return MakeList.list;
    case 13: 
      return new PrimProcedure("kawa.lib.strings", "$make$string$", 1);
    }
    return new PrimProcedure("java.util.regex.Pattern", "compile", 1);
  }
  
  public Method getDeclaredMethod(String paramString, int paramInt)
  {
    return this.implementationType.getDeclaredMethod(paramString, paramInt);
  }
  
  public Field getField(String paramString, int paramInt)
  {
    return this.implementationType.getField(paramString, paramInt);
  }
  
  public Type getImplementationType()
  {
    return this.implementationType;
  }
  
  public Method getMethod(String paramString, Type[] paramArrayOfType)
  {
    return this.implementationType.getMethod(paramString, paramArrayOfType);
  }
  
  public int getMethods(Filter paramFilter, int paramInt, List<Method> paramList)
  {
    return this.implementationType.getMethods(paramFilter, paramInt, paramList);
  }
  
  public Type getRealType()
  {
    return this.implementationType;
  }
  
  public Class getReflectClass()
  {
    return this.implementationType.getReflectClass();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\LangObjType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */