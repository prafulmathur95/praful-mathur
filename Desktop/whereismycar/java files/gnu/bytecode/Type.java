package gnu.bytecode;

import gnu.kawa.util.AbstractWeakHashTable;
import java.io.PrintWriter;
import java.util.HashMap;

public abstract class Type
  implements java.lang.reflect.Type
{
  public static final PrimType booleanType;
  public static final Method booleanValue_method;
  public static final ClassType boolean_ctype;
  public static final PrimType boolean_type;
  public static final PrimType byteType = new PrimType("byte", "B", 1, Byte.TYPE);
  public static final PrimType byte_type;
  public static final PrimType charType;
  public static final PrimType char_type;
  public static final Method clone_method;
  public static final PrimType doubleType;
  public static final Method doubleValue_method;
  public static final PrimType double_type;
  public static final ObjectType errorType;
  public static final PrimType floatType;
  public static final Method floatValue_method;
  public static final PrimType float_type;
  public static final PrimType intType;
  public static final Method intValue_method;
  public static final PrimType int_type;
  public static final ClassType java_lang_Class_type;
  public static final ClassType javalangBooleanType;
  public static final ClassType javalangClassType;
  public static final ClassType javalangNumberType;
  public static final ClassType javalangObjectType;
  public static ClassType javalangStringType;
  public static final ClassType javalangThrowableType;
  public static final PrimType longType;
  public static final Method longValue_method;
  public static final PrimType long_type;
  static ClassToTypeMap mapClassToType;
  static HashMap<String, Type> mapNameToType;
  public static final PrimType neverReturnsType;
  public static final ObjectType nullType;
  public static final ClassType number_type = javalangNumberType;
  public static final ClassType objectType;
  public static final ClassType pointer_type;
  public static final PrimType shortType = new PrimType("short", "S", 2, Short.TYPE);
  public static final PrimType short_type;
  public static final ClassType string_type;
  public static final ClassType throwable_type;
  public static final ClassType toStringType;
  public static final Method toString_method;
  public static final ClassType tostring_type;
  public static final Type[] typeArray0;
  public static final PrimType voidType;
  public static final PrimType void_type;
  ArrayType array_type;
  protected Class reflectClass;
  String signature;
  int size;
  String this_name;
  
  static
  {
    intType = new PrimType("int", "I", 4, Integer.TYPE);
    longType = new PrimType("long", "J", 8, Long.TYPE);
    floatType = new PrimType("float", "F", 4, Float.TYPE);
    doubleType = new PrimType("double", "D", 8, Double.TYPE);
    booleanType = new PrimType("boolean", "Z", 1, Boolean.TYPE);
    charType = new PrimType("char", "C", 2, Character.TYPE);
    voidType = new PrimType("void", "V", 0, Void.TYPE);
    byte_type = byteType;
    short_type = shortType;
    int_type = intType;
    long_type = longType;
    float_type = floatType;
    double_type = doubleType;
    boolean_type = booleanType;
    char_type = charType;
    void_type = voidType;
    mapNameToType = new HashMap();
    mapNameToType.put("byte", byteType);
    mapNameToType.put("short", shortType);
    mapNameToType.put("int", intType);
    mapNameToType.put("long", longType);
    mapNameToType.put("float", floatType);
    mapNameToType.put("double", doubleType);
    mapNameToType.put("boolean", booleanType);
    mapNameToType.put("char", charType);
    mapNameToType.put("void", voidType);
    neverReturnsType = new PrimType(voidType);
    neverReturnsType.this_name = "(never-returns)";
    nullType = new ObjectType("(type of null)");
    errorType = new ClassType("(error type)");
    javalangStringType = ClassType.make("java.lang.String");
    toStringType = new ClassType("java.lang.String");
    javalangObjectType = ClassType.make("java.lang.Object");
    objectType = javalangObjectType;
    javalangBooleanType = ClassType.make("java.lang.Boolean");
    javalangThrowableType = ClassType.make("java.lang.Throwable");
    typeArray0 = new Type[0];
    toString_method = objectType.getDeclaredMethod("toString", 0);
    javalangNumberType = ClassType.make("java.lang.Number");
    clone_method = Method.makeCloneMethod(objectType);
    intValue_method = javalangNumberType.addMethod("intValue", typeArray0, intType, 1);
    longValue_method = javalangNumberType.addMethod("longValue", typeArray0, longType, 1);
    floatValue_method = javalangNumberType.addMethod("floatValue", typeArray0, floatType, 1);
    doubleValue_method = javalangNumberType.addMethod("doubleValue", typeArray0, doubleType, 1);
    booleanValue_method = javalangBooleanType.addMethod("booleanValue", typeArray0, booleanType, 1);
    javalangClassType = ClassType.make("java.lang.Class");
    pointer_type = javalangObjectType;
    string_type = javalangStringType;
    tostring_type = toStringType;
    java_lang_Class_type = javalangClassType;
    boolean_ctype = javalangBooleanType;
    throwable_type = javalangThrowableType;
  }
  
  protected Type() {}
  
  public Type(Type paramType)
  {
    this.this_name = paramType.this_name;
    this.signature = paramType.signature;
    this.size = paramType.size;
    this.reflectClass = paramType.reflectClass;
  }
  
  Type(String paramString1, String paramString2)
  {
    this.this_name = paramString1;
    this.signature = paramString2;
  }
  
  public static Type getType(String paramString)
  {
    synchronized (mapNameToType)
    {
      Type localType = (Type)???.get(paramString);
      Object localObject = localType;
      if (localType == null)
      {
        if (paramString.endsWith("[]"))
        {
          localObject = ArrayType.make(paramString);
          ???.put(paramString, localObject);
        }
      }
      else {
        return (Type)localObject;
      }
      localObject = new ClassType(paramString);
      ((ClassType)localObject).flags |= 0x10;
    }
  }
  
  public static boolean isMoreSpecific(Type[] paramArrayOfType1, Type[] paramArrayOfType2)
  {
    if (paramArrayOfType1.length != paramArrayOfType2.length) {
      return false;
    }
    int i = paramArrayOfType1.length;
    int j;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
    } while (paramArrayOfType1[j].isSubtype(paramArrayOfType2[j]));
    return false;
    return true;
  }
  
  public static boolean isValidJavaTypeName(String paramString)
  {
    int i = 0;
    int j = paramString.length();
    while ((j > 2) && (paramString.charAt(j - 1) == ']') && (paramString.charAt(j - 2) == '[')) {
      j -= 2;
    }
    int k = 0;
    for (;;)
    {
      if (k < j)
      {
        c = paramString.charAt(k);
        if (c == '.')
        {
          if (i != 0)
          {
            i = 0;
            k += 1;
          }
        }
        else if (i != 0) {
          if (!Character.isJavaIdentifierPart(c)) {
            break;
          }
        } else {
          while (Character.isJavaIdentifierStart(c))
          {
            i = 1;
            break;
          }
        }
      }
    }
    while (k != j)
    {
      char c;
      return false;
    }
    return true;
  }
  
  public static Type lookupType(String paramString)
  {
    synchronized (mapNameToType)
    {
      paramString = (Type)???.get(paramString);
      return paramString;
    }
  }
  
  public static Type lowestCommonSuperType(Type paramType1, Type paramType2)
  {
    Type localType;
    if (paramType1 == neverReturnsType) {
      localType = paramType2;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return localType;
            localType = paramType1;
          } while (paramType2 == neverReturnsType);
          if ((paramType1 == null) || (paramType2 == null)) {
            return null;
          }
          if ((!(paramType1 instanceof PrimType)) || (!(paramType2 instanceof PrimType))) {
            break;
          }
          localType = paramType1;
        } while (paramType1 == paramType2);
        paramType1 = ((PrimType)paramType1).promotedType();
        localType = paramType1;
      } while (paramType1 == ((PrimType)paramType2).promotedType());
      return null;
      if (paramType1.isSubtype(paramType2)) {
        return paramType2;
      }
      localType = paramType1;
    } while (paramType2.isSubtype(paramType1));
    if ((!(paramType1 instanceof ClassType)) || (!(paramType2 instanceof ClassType))) {
      return objectType;
    }
    paramType1 = (ClassType)paramType1;
    paramType2 = (ClassType)paramType2;
    if ((paramType1.isInterface()) || (paramType2.isInterface())) {
      return objectType;
    }
    return lowestCommonSuperType(paramType1.getSuperclass(), paramType2.getSuperclass());
  }
  
  public static Type make(Class paramClass)
  {
    for (;;)
    {
      Object localObject;
      try
      {
        if (mapClassToType != null)
        {
          localObject = (Type)mapClassToType.get(paramClass);
          if (localObject != null)
          {
            paramClass = (Class)localObject;
            return paramClass;
          }
        }
        if (paramClass.isArray())
        {
          localObject = ArrayType.make(make(paramClass.getComponentType()));
          registerTypeForClass(paramClass, (Type)localObject);
          paramClass = (Class)localObject;
          continue;
        }
        if (paramClass.isPrimitive()) {
          throw new Error("internal error - primitive type not found");
        }
      }
      finally {}
      String str = paramClass.getName();
      synchronized (mapNameToType)
      {
        Type localType = (Type)???.get(str);
        if (localType != null)
        {
          localObject = localType;
          if (localType.reflectClass != paramClass)
          {
            localObject = localType;
            if (localType.reflectClass == null) {}
          }
        }
        else
        {
          localObject = new ClassType(str);
          ((ClassType)localObject).flags |= 0x10;
          mapNameToType.put(str, localObject);
        }
      }
    }
  }
  
  public static void printSignature(String paramString, int paramInt1, int paramInt2, PrintWriter paramPrintWriter)
  {
    if (paramInt2 == 0) {}
    char c;
    do
    {
      return;
      c = paramString.charAt(paramInt1);
      if (paramInt2 != 1) {
        break;
      }
      paramString = signatureToPrimitive(c);
    } while (paramString == null);
    paramPrintWriter.print(paramString.getName());
    return;
    if (c == '[')
    {
      printSignature(paramString, paramInt1 + 1, paramInt2 - 1, paramPrintWriter);
      paramPrintWriter.print("[]");
      return;
    }
    if ((c == 'L') && (paramInt2 > 2) && (paramString.indexOf(';', paramInt1) == paramInt2 - 1 + paramInt1))
    {
      paramPrintWriter.print(paramString.substring(paramInt1 + 1, paramInt2 - 1 + paramInt1).replace('/', '.'));
      return;
    }
    paramPrintWriter.append(paramString, paramInt1, paramInt2 - paramInt1);
  }
  
  public static void registerTypeForClass(Class paramClass, Type paramType)
  {
    try
    {
      ClassToTypeMap localClassToTypeMap2 = mapClassToType;
      ClassToTypeMap localClassToTypeMap1 = localClassToTypeMap2;
      if (localClassToTypeMap2 == null)
      {
        localClassToTypeMap1 = new ClassToTypeMap();
        mapClassToType = localClassToTypeMap1;
      }
      paramType.reflectClass = paramClass;
      localClassToTypeMap1.put(paramClass, paramType);
      return;
    }
    finally {}
  }
  
  public static int signatureLength(String paramString)
  {
    return signatureLength(paramString, 0);
  }
  
  public static int signatureLength(String paramString, int paramInt)
  {
    if (paramString.length() <= paramInt) {}
    int i;
    int j;
    do
    {
      char c;
      do
      {
        return -1;
        c = paramString.charAt(paramInt);
        i = 0;
        while (c == '[')
        {
          i += 1;
          paramInt += 1;
          c = paramString.charAt(paramInt);
        }
        if (signatureToPrimitive(c) != null) {
          return i + 1;
        }
      } while (c != 'L');
      j = paramString.indexOf(';', paramInt);
    } while (j <= 0);
    return i + j + 1 - paramInt;
  }
  
  public static String signatureToName(String paramString)
  {
    int k = paramString.length();
    if (k == 0) {}
    char c;
    do
    {
      int i;
      do
      {
        return null;
        c = paramString.charAt(0);
        if (k == 1)
        {
          localObject = signatureToPrimitive(c);
          if (localObject != null) {
            return ((Type)localObject).getName();
          }
        }
        if (c != '[') {
          break;
        }
        int j = 1;
        i = j;
        if (1 < k)
        {
          i = j;
          if (paramString.charAt(1) == '[') {
            i = 1 + 1;
          }
        }
        paramString = signatureToName(paramString.substring(i));
      } while (paramString == null);
      Object localObject = new StringBuffer(50);
      ((StringBuffer)localObject).append(paramString);
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          break;
        }
        ((StringBuffer)localObject).append("[]");
      }
      return ((StringBuffer)localObject).toString();
    } while ((c != 'L') || (k <= 2) || (paramString.indexOf(';') != k - 1));
    return paramString.substring(1, k - 1).replace('/', '.');
  }
  
  public static PrimType signatureToPrimitive(char paramChar)
  {
    switch (paramChar)
    {
    default: 
      return null;
    case 'B': 
      return byteType;
    case 'C': 
      return charType;
    case 'D': 
      return doubleType;
    case 'F': 
      return floatType;
    case 'S': 
      return shortType;
    case 'I': 
      return intType;
    case 'J': 
      return longType;
    case 'Z': 
      return booleanType;
    }
    return voidType;
  }
  
  public static Type signatureToType(String paramString)
  {
    return signatureToType(paramString, 0, paramString.length());
  }
  
  public static Type signatureToType(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {}
    char c;
    do
    {
      do
      {
        return null;
        c = paramString.charAt(paramInt1);
        if (paramInt2 == 1)
        {
          PrimType localPrimType = signatureToPrimitive(c);
          if (localPrimType != null) {
            return localPrimType;
          }
        }
        if (c != '[') {
          break;
        }
        paramString = signatureToType(paramString, paramInt1 + 1, paramInt2 - 1);
      } while (paramString == null);
      return ArrayType.make(paramString);
    } while ((c != 'L') || (paramInt2 <= 2) || (paramString.indexOf(';', paramInt1) != paramInt2 - 1 + paramInt1));
    return ClassType.make(paramString.substring(paramInt1 + 1, paramInt2 - 1 + paramInt1).replace('/', '.'));
  }
  
  protected static int swappedCompareResult(int paramInt)
  {
    int i;
    if (paramInt == 1) {
      i = -1;
    }
    do
    {
      return i;
      i = paramInt;
    } while (paramInt != -1);
    return 1;
  }
  
  public abstract Object coerceFromObject(Object paramObject);
  
  public Object coerceToObject(Object paramObject)
  {
    return paramObject;
  }
  
  public abstract int compare(Type paramType);
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr)
  {
    throw new Error("unimplemented emitCoerceFromObject for " + this);
  }
  
  public void emitCoerceToObject(CodeAttr paramCodeAttr) {}
  
  public void emitConvertFromPrimitive(Type paramType, CodeAttr paramCodeAttr)
  {
    paramType.emitCoerceToObject(paramCodeAttr);
  }
  
  public void emitIsInstance(CodeAttr paramCodeAttr)
  {
    paramCodeAttr.emitInstanceof(this);
  }
  
  public Type getImplementationType()
  {
    return this;
  }
  
  public final String getName()
  {
    return this.this_name;
  }
  
  public Type getRealType()
  {
    return this;
  }
  
  public Class getReflectClass()
  {
    return this.reflectClass;
  }
  
  public String getSignature()
  {
    return this.signature;
  }
  
  public final int getSize()
  {
    return this.size;
  }
  
  public int getSizeInWords()
  {
    if (this.size > 4) {
      return 2;
    }
    return 1;
  }
  
  public int hashCode()
  {
    String str = toString();
    if (str == null) {
      return 0;
    }
    return str.hashCode();
  }
  
  public boolean isExisting()
  {
    return true;
  }
  
  public boolean isInstance(Object paramObject)
  {
    return getReflectClass().isInstance(paramObject);
  }
  
  public final boolean isSubtype(Type paramType)
  {
    int i = compare(paramType);
    return (i == -1) || (i == 0);
  }
  
  public final boolean isVoid()
  {
    return this.size == 0;
  }
  
  public Type promote()
  {
    Object localObject = this;
    if (this.size < 4) {
      localObject = intType;
    }
    return (Type)localObject;
  }
  
  protected void setName(String paramString)
  {
    this.this_name = paramString;
  }
  
  public void setReflectClass(Class paramClass)
  {
    this.reflectClass = paramClass;
  }
  
  protected void setSignature(String paramString)
  {
    this.signature = paramString;
  }
  
  public String toString()
  {
    return "Type " + getName();
  }
  
  static class ClassToTypeMap
    extends AbstractWeakHashTable<Class, Type>
  {
    protected Class getKeyFromValue(Type paramType)
    {
      return paramType.reflectClass;
    }
    
    protected boolean matches(Class paramClass1, Class paramClass2)
    {
      return paramClass1 == paramClass2;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\Type.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */