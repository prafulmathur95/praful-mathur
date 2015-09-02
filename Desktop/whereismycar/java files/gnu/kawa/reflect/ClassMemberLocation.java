package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import java.lang.reflect.Field;

public abstract class ClassMemberLocation
  extends Location
{
  Object instance;
  String mname;
  Field rfield;
  ClassType type;
  
  public ClassMemberLocation(Object paramObject, ClassType paramClassType, String paramString)
  {
    this.instance = paramObject;
    this.type = paramClassType;
    this.mname = paramString;
  }
  
  public ClassMemberLocation(Object paramObject, Class paramClass, String paramString)
  {
    this.instance = paramObject;
    this.type = ((ClassType)Type.make(paramClass));
    this.mname = paramString;
  }
  
  public ClassMemberLocation(Object paramObject, Field paramField)
  {
    this.instance = paramObject;
    this.rfield = paramField;
    this.mname = paramField.getName();
  }
  
  public static void define(Object paramObject, Field paramField, String paramString, Language paramLanguage, Environment paramEnvironment)
    throws IllegalAccessException
  {
    Object localObject2 = paramField.get(paramObject);
    Object localObject1 = Type.make(paramField.getType());
    boolean bool = ((Type)localObject1).isSubtype(Compilation.typeLocation);
    ((Type)localObject1).isSubtype(Compilation.typeProcedure);
    int j = paramField.getModifiers();
    int i;
    label82:
    label97:
    String str;
    if ((j & 0x10) != 0)
    {
      i = 1;
      if ((i == 0) || (!(localObject2 instanceof Named)) || (bool)) {
        break label135;
      }
      localObject1 = ((Named)localObject2).getSymbol();
      if (!(localObject1 instanceof Symbol)) {
        break label148;
      }
      localObject1 = (Symbol)localObject1;
      str = null;
      paramString = null;
      if ((!bool) || (i == 0)) {
        break label177;
      }
      paramObject = (Location)localObject2;
    }
    for (;;)
    {
      paramEnvironment.addLocation((Symbol)localObject1, paramString, (Location)paramObject);
      return;
      i = 0;
      break;
      label135:
      localObject1 = Compilation.demangleName(paramField.getName(), true);
      break label82;
      label148:
      str = paramString;
      if (paramString == null) {
        str = "";
      }
      localObject1 = Symbol.make(str, localObject1.toString().intern());
      break label97;
      label177:
      paramString = str;
      if (i != 0) {
        paramString = paramLanguage.getEnvPropertyFor(paramField, localObject2);
      }
      if ((j & 0x8) != 0) {}
      for (i = 1;; i = 0)
      {
        if (i == 0) {
          break label227;
        }
        paramObject = new StaticFieldLocation(paramField);
        break;
      }
      label227:
      paramObject = new FieldLocation(paramObject, paramField);
    }
  }
  
  public static void defineAll(Object paramObject, Language paramLanguage, Environment paramEnvironment)
    throws IllegalAccessException
  {
    Field[] arrayOfField = paramObject.getClass().getFields();
    int i = arrayOfField.length;
    for (;;)
    {
      int j = i - 1;
      if (j < 0) {
        break;
      }
      Field localField = arrayOfField[j];
      String str = localField.getName();
      i = j;
      if (!str.startsWith("$Prvt$"))
      {
        i = j;
        if (!str.endsWith("$instance"))
        {
          define(paramObject, localField, null, paramLanguage, paramEnvironment);
          i = j;
        }
      }
    }
  }
  
  public Object get(Object paramObject)
  {
    Field localField = getRField();
    if (localField == null) {
      return paramObject;
    }
    try
    {
      paramObject = localField.get(this.instance);
      return paramObject;
    }
    catch (IllegalAccessException paramObject)
    {
      throw WrappedException.wrapIfNeeded((Throwable)paramObject);
    }
  }
  
  public ClassType getDeclaringClass()
  {
    return this.type;
  }
  
  public final Object getInstance()
  {
    return this.instance;
  }
  
  public String getMemberName()
  {
    return this.mname;
  }
  
  public Class getRClass()
  {
    Object localObject = this.rfield;
    if (localObject != null) {
      return ((Field)localObject).getDeclaringClass();
    }
    try
    {
      localObject = this.type.getReflectClass();
      return (Class)localObject;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public Field getRField()
  {
    Field localField2 = this.rfield;
    Field localField1 = localField2;
    if (localField2 == null) {}
    try
    {
      localField1 = this.type.getReflectClass().getField(this.mname);
      this.rfield = localField1;
      return localField1;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public boolean isBound()
  {
    return getRField() != null;
  }
  
  public boolean isConstant()
  {
    return (getRField() != null) && ((this.rfield.getModifiers() & 0x10) != 0);
  }
  
  public void set(Object paramObject)
  {
    setup();
    try
    {
      this.rfield.set(this.instance, paramObject);
      return;
    }
    catch (IllegalAccessException paramObject)
    {
      throw WrappedException.wrapIfNeeded((Throwable)paramObject);
    }
  }
  
  public final void setInstance(Object paramObject)
  {
    this.instance = paramObject;
  }
  
  /* Error */
  void setup()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 36	gnu/kawa/reflect/ClassMemberLocation:rfield	Ljava/lang/reflect/Field;
    //   4: ifnonnull +23 -> 27
    //   7: aload_0
    //   8: getfield 21	gnu/kawa/reflect/ClassMemberLocation:type	Lgnu/bytecode/ClassType;
    //   11: invokevirtual 165	gnu/bytecode/ClassType:getReflectClass	()Ljava/lang/Class;
    //   14: astore_1
    //   15: aload_0
    //   16: aload_1
    //   17: aload_0
    //   18: getfield 23	gnu/kawa/reflect/ClassMemberLocation:mname	Ljava/lang/String;
    //   21: invokevirtual 169	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   24: putfield 36	gnu/kawa/reflect/ClassMemberLocation:rfield	Ljava/lang/reflect/Field;
    //   27: return
    //   28: astore_1
    //   29: new 187	gnu/mapping/UnboundLocationException
    //   32: dup
    //   33: aconst_null
    //   34: new 189	java/lang/StringBuilder
    //   37: dup
    //   38: invokespecial 190	java/lang/StringBuilder:<init>	()V
    //   41: ldc -64
    //   43: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: aload_1
    //   47: invokevirtual 197	java/lang/RuntimeException:toString	()Ljava/lang/String;
    //   50: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokespecial 201	gnu/mapping/UnboundLocationException:<init>	(Ljava/lang/Object;Ljava/lang/String;)V
    //   59: astore_2
    //   60: aload_2
    //   61: aload_1
    //   62: invokevirtual 205	java/lang/RuntimeException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   65: pop
    //   66: aload_2
    //   67: athrow
    //   68: astore_1
    //   69: new 187	gnu/mapping/UnboundLocationException
    //   72: dup
    //   73: aconst_null
    //   74: new 189	java/lang/StringBuilder
    //   77: dup
    //   78: invokespecial 190	java/lang/StringBuilder:<init>	()V
    //   81: ldc -49
    //   83: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: aload_0
    //   87: getfield 23	gnu/kawa/reflect/ClassMemberLocation:mname	Ljava/lang/String;
    //   90: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: ldc -47
    //   95: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: aload_0
    //   99: getfield 21	gnu/kawa/reflect/ClassMemberLocation:type	Lgnu/bytecode/ClassType;
    //   102: invokevirtual 210	gnu/bytecode/ClassType:getName	()Ljava/lang/String;
    //   105: invokevirtual 196	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   111: invokespecial 201	gnu/mapping/UnboundLocationException:<init>	(Ljava/lang/Object;Ljava/lang/String;)V
    //   114: astore_2
    //   115: aload_2
    //   116: aload_1
    //   117: invokevirtual 205	java/lang/RuntimeException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   120: pop
    //   121: aload_2
    //   122: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	123	0	this	ClassMemberLocation
    //   14	3	1	localClass	Class
    //   28	34	1	localRuntimeException	RuntimeException
    //   68	49	1	localNoSuchFieldException	NoSuchFieldException
    //   59	63	2	localUnboundLocationException	gnu.mapping.UnboundLocationException
    // Exception table:
    //   from	to	target	type
    //   7	15	28	java/lang/RuntimeException
    //   15	27	68	java/lang/NoSuchFieldException
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\ClassMemberLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */