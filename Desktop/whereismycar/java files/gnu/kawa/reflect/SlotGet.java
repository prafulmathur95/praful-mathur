package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;

public class SlotGet
  extends Procedure2
  implements HasSetter, Inlineable
{
  public static final SlotGet field = new SlotGet("field", false, SlotSet.set$Mnfield$Ex);
  static Class[] noClasses = new Class[0];
  public static final SlotGet slotRef = new SlotGet("slot-ref", false, SlotSet.set$Mnfield$Ex);
  public static final SlotGet staticField = new SlotGet("static-field", true, SlotSet.set$Mnstatic$Mnfield$Ex);
  boolean isStatic;
  Procedure setter;
  
  public SlotGet(String paramString, boolean paramBoolean)
  {
    super(paramString);
    this.isStatic = paramBoolean;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplySlotGet");
  }
  
  public SlotGet(String paramString, boolean paramBoolean, Procedure paramProcedure)
  {
    this(paramString, paramBoolean);
    this.setter = paramProcedure;
  }
  
  static Class coerceToClass(Object paramObject)
  {
    if ((paramObject instanceof Class)) {
      return (Class)paramObject;
    }
    if ((paramObject instanceof Type)) {
      return ((Type)paramObject).getReflectClass();
    }
    throw new RuntimeException("argument is neither Class nor Type");
  }
  
  public static Object field(Object paramObject, String paramString)
  {
    return field.apply2(paramObject, paramString);
  }
  
  /* Error */
  public static Object getSlotValue(boolean paramBoolean, Object paramObject, String paramString1, String paramString2, String paramString3, String paramString4, Language paramLanguage)
  {
    // Byte code:
    //   0: iload_0
    //   1: ifeq +35 -> 36
    //   4: aload_1
    //   5: invokestatic 101	gnu/kawa/reflect/SlotGet:coerceToClass	(Ljava/lang/Object;)Ljava/lang/Class;
    //   8: astore 7
    //   10: aload_3
    //   11: ldc 103
    //   13: if_acmpne +32 -> 45
    //   16: aload 7
    //   18: invokevirtual 107	java/lang/Class:isArray	()Z
    //   21: ifeq +24 -> 45
    //   24: aload_1
    //   25: invokestatic 113	java/lang/reflect/Array:getLength	(Ljava/lang/Object;)I
    //   28: invokestatic 119	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   31: astore 8
    //   33: aload 8
    //   35: areturn
    //   36: aload_1
    //   37: invokevirtual 124	java/lang/Object:getClass	()Ljava/lang/Class;
    //   40: astore 7
    //   42: goto -32 -> 10
    //   45: aload 7
    //   47: astore 8
    //   49: aload_3
    //   50: ldc 126
    //   52: if_acmpeq -19 -> 33
    //   55: iconst_0
    //   56: istore 10
    //   58: iload 10
    //   60: istore 9
    //   62: aload_3
    //   63: ifnull +101 -> 164
    //   66: aload 7
    //   68: aload_3
    //   69: invokevirtual 130	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   72: astore 8
    //   74: iload 10
    //   76: istore 9
    //   78: aload 8
    //   80: ifnull +84 -> 164
    //   83: iload_0
    //   84: ifeq +54 -> 138
    //   87: aload 8
    //   89: invokevirtual 136	java/lang/reflect/Field:getModifiers	()I
    //   92: bipush 8
    //   94: iand
    //   95: ifne +43 -> 138
    //   98: new 81	java/lang/RuntimeException
    //   101: dup
    //   102: new 138	java/lang/StringBuilder
    //   105: dup
    //   106: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   109: ldc -114
    //   111: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: aload_3
    //   115: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: bipush 39
    //   120: invokevirtual 149	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   123: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   126: invokespecial 84	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   129: athrow
    //   130: astore 8
    //   132: aconst_null
    //   133: astore 8
    //   135: goto -61 -> 74
    //   138: aload 6
    //   140: aload 8
    //   142: invokevirtual 156	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   145: aload 8
    //   147: aload_1
    //   148: invokevirtual 160	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   151: invokevirtual 166	gnu/expr/Language:coerceToObject	(Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/Object;
    //   154: astore 8
    //   156: aload 8
    //   158: areturn
    //   159: astore 8
    //   161: iconst_1
    //   162: istore 9
    //   164: aload 4
    //   166: ifnull +96 -> 262
    //   169: aload 7
    //   171: aload 4
    //   173: getstatic 24	gnu/kawa/reflect/SlotGet:noClasses	[Ljava/lang/Class;
    //   176: invokevirtual 170	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   179: astore 8
    //   181: aload 8
    //   183: astore_2
    //   184: aload 4
    //   186: astore 5
    //   188: aload_2
    //   189: astore 4
    //   191: iload_0
    //   192: ifeq +118 -> 310
    //   195: aload 4
    //   197: invokevirtual 173	java/lang/reflect/Method:getModifiers	()I
    //   200: bipush 8
    //   202: iand
    //   203: ifne +107 -> 310
    //   206: new 81	java/lang/RuntimeException
    //   209: dup
    //   210: new 138	java/lang/StringBuilder
    //   213: dup
    //   214: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   217: ldc -81
    //   219: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: aload 5
    //   224: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: bipush 39
    //   229: invokevirtual 149	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   232: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   235: invokespecial 84	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   238: athrow
    //   239: astore_1
    //   240: aload_1
    //   241: invokevirtual 179	java/lang/reflect/InvocationTargetException:getTargetException	()Ljava/lang/Throwable;
    //   244: invokestatic 185	gnu/mapping/WrappedException:wrapIfNeeded	(Ljava/lang/Throwable;)Ljava/lang/RuntimeException;
    //   247: athrow
    //   248: astore 8
    //   250: aload 8
    //   252: invokevirtual 188	java/lang/Exception:printStackTrace	()V
    //   255: iload 10
    //   257: istore 9
    //   259: goto -95 -> 164
    //   262: ldc -67
    //   264: aload_2
    //   265: invokestatic 195	gnu/expr/ClassExp:slotToMethodName	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   268: astore 4
    //   270: goto -101 -> 169
    //   273: astore 4
    //   275: aload 5
    //   277: ifnull +23 -> 300
    //   280: aload 5
    //   282: astore_2
    //   283: aload 7
    //   285: aload_2
    //   286: getstatic 24	gnu/kawa/reflect/SlotGet:noClasses	[Ljava/lang/Class;
    //   289: invokevirtual 170	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   292: astore 4
    //   294: aload_2
    //   295: astore 5
    //   297: goto -106 -> 191
    //   300: ldc -59
    //   302: aload_2
    //   303: invokestatic 195	gnu/expr/ClassExp:slotToMethodName	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   306: astore_2
    //   307: goto -24 -> 283
    //   310: aload 4
    //   312: aload_1
    //   313: getstatic 203	gnu/mapping/Values:noArgs	[Ljava/lang/Object;
    //   316: invokevirtual 207	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   319: astore_1
    //   320: aload 6
    //   322: aload 4
    //   324: invokevirtual 210	java/lang/reflect/Method:getReturnType	()Ljava/lang/Class;
    //   327: aload_1
    //   328: invokevirtual 166	gnu/expr/Language:coerceToObject	(Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/Object;
    //   331: astore_1
    //   332: aload_1
    //   333: areturn
    //   334: astore_1
    //   335: iconst_1
    //   336: istore 9
    //   338: iload 9
    //   340: ifeq +30 -> 370
    //   343: new 81	java/lang/RuntimeException
    //   346: dup
    //   347: new 138	java/lang/StringBuilder
    //   350: dup
    //   351: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   354: ldc -44
    //   356: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   359: aload_3
    //   360: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   363: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   366: invokespecial 84	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   369: athrow
    //   370: new 81	java/lang/RuntimeException
    //   373: dup
    //   374: new 138	java/lang/StringBuilder
    //   377: dup
    //   378: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   381: ldc -42
    //   383: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   386: aload_3
    //   387: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   390: ldc -40
    //   392: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   395: aload 7
    //   397: invokevirtual 219	java/lang/Class:getName	()Ljava/lang/String;
    //   400: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   406: invokespecial 84	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   409: athrow
    //   410: astore_1
    //   411: goto -73 -> 338
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	414	0	paramBoolean	boolean
    //   0	414	1	paramObject	Object
    //   0	414	2	paramString1	String
    //   0	414	3	paramString2	String
    //   0	414	4	paramString3	String
    //   0	414	5	paramString4	String
    //   0	414	6	paramLanguage	Language
    //   8	388	7	localClass	Class
    //   31	57	8	localObject1	Object
    //   130	1	8	localException1	Exception
    //   133	24	8	localObject2	Object
    //   159	1	8	localIllegalAccessException	IllegalAccessException
    //   179	3	8	localMethod	java.lang.reflect.Method
    //   248	3	8	localException2	Exception
    //   60	279	9	i	int
    //   56	200	10	j	int
    // Exception table:
    //   from	to	target	type
    //   66	74	130	java/lang/Exception
    //   138	156	159	java/lang/IllegalAccessException
    //   169	181	239	java/lang/reflect/InvocationTargetException
    //   195	239	239	java/lang/reflect/InvocationTargetException
    //   262	270	239	java/lang/reflect/InvocationTargetException
    //   283	294	239	java/lang/reflect/InvocationTargetException
    //   300	307	239	java/lang/reflect/InvocationTargetException
    //   310	332	239	java/lang/reflect/InvocationTargetException
    //   138	156	248	java/lang/Exception
    //   169	181	273	java/lang/Exception
    //   262	270	273	java/lang/Exception
    //   169	181	334	java/lang/IllegalAccessException
    //   195	239	334	java/lang/IllegalAccessException
    //   262	270	334	java/lang/IllegalAccessException
    //   283	294	334	java/lang/IllegalAccessException
    //   300	307	334	java/lang/IllegalAccessException
    //   310	332	334	java/lang/IllegalAccessException
    //   169	181	410	java/lang/NoSuchMethodException
    //   195	239	410	java/lang/NoSuchMethodException
    //   262	270	410	java/lang/NoSuchMethodException
    //   283	294	410	java/lang/NoSuchMethodException
    //   300	307	410	java/lang/NoSuchMethodException
    //   310	332	410	java/lang/NoSuchMethodException
  }
  
  public static Member lookupMember(ObjectType paramObjectType, String paramString, ClassType paramClassType)
  {
    Field localField = paramObjectType.getField(Compilation.mangleNameIfNeeded(paramString), -1);
    if (localField != null)
    {
      ClassType localClassType = paramClassType;
      if (paramClassType == null) {
        localClassType = Type.pointer_type;
      }
      if (!localClassType.isAccessible(localField, paramObjectType)) {}
    }
    do
    {
      return localField;
      paramObjectType = paramObjectType.getMethod(ClassExp.slotToMethodName("get", paramString), Type.typeArray0);
    } while (paramObjectType == null);
    return paramObjectType;
  }
  
  public static ApplyExp makeGetField(Expression paramExpression, String paramString)
  {
    paramString = new QuoteExp(paramString);
    return new ApplyExp(field, new Expression[] { paramExpression, paramString });
  }
  
  public static Object staticField(Object paramObject, String paramString)
  {
    return staticField.apply2(paramObject, paramString);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
  {
    Object localObject1 = null;
    Object localObject4 = null;
    Object localObject5 = null;
    Object localObject3 = null;
    Object localObject2;
    if ((paramObject2 instanceof Field))
    {
      paramObject2 = ((Field)paramObject2).getName();
      localObject1 = Compilation.demangleName((String)paramObject2, true);
      if (!"class".equals(paramObject2)) {
        break label180;
      }
      localObject2 = "class";
    }
    for (;;)
    {
      return getSlotValue(this.isStatic, paramObject1, (String)localObject1, (String)localObject2, (String)localObject4, (String)localObject3, Language.getDefaultLanguage());
      if ((paramObject2 instanceof Method))
      {
        paramObject2 = ((Method)paramObject2).getName();
        localObject4 = Compilation.demangleName((String)paramObject2, false);
        if (((String)paramObject2).startsWith("get"))
        {
          localObject3 = localObject5;
          localObject2 = paramObject2;
        }
        for (;;)
        {
          paramObject2 = null;
          localObject1 = localObject4;
          localObject4 = localObject2;
          break;
          localObject2 = localObject1;
          localObject3 = localObject5;
          if (((String)paramObject2).startsWith("is"))
          {
            localObject2 = localObject1;
            localObject3 = paramObject2;
          }
        }
      }
      if (((paramObject2 instanceof SimpleSymbol)) || ((paramObject2 instanceof CharSequence)))
      {
        localObject1 = paramObject2.toString();
        paramObject2 = Compilation.mangleNameIfNeeded((String)localObject1);
        break;
      }
      throw new WrongType(this, 2, paramObject2, "string");
      label180:
      localObject2 = paramObject2;
      if ("length".equals(paramObject2)) {
        localObject2 = "length";
      }
    }
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    Object localObject3 = paramApplyExp.getArgs();
    Object localObject1 = localObject3[0];
    Expression localExpression = localObject3[1];
    Object localObject2 = paramCompilation.getLanguage();
    CodeAttr localCodeAttr;
    ObjectType localObjectType;
    Object localObject4;
    int i;
    if (this.isStatic)
    {
      localObject1 = ((Language)localObject2).getTypeFor((Expression)localObject1);
      localCodeAttr = paramCompilation.getCode();
      if ((!(localObject1 instanceof ObjectType)) || (!(localExpression instanceof QuoteExp))) {
        break label289;
      }
      localObjectType = (ObjectType)localObject1;
      localObject4 = ((QuoteExp)localExpression).getValue();
      if (!(localObject4 instanceof Field)) {
        break label197;
      }
      localObject1 = (Field)localObject4;
      if ((((Field)localObject1).getModifiers() & 0x8) == 0) {
        break label172;
      }
      i = 1;
      label108:
      localObject3 = localObject3[0];
      if (i == 0) {
        break label178;
      }
      paramApplyExp = Target.Ignore;
      label123:
      ((Expression)localObject3).compile(paramCompilation, paramApplyExp);
      if (i == 0) {
        break label187;
      }
      if (0 == 0) {
        localCodeAttr.emitGetStatic((Field)localObject1);
      }
    }
    for (;;)
    {
      paramTarget.compileFromStack(paramCompilation, ((Language)localObject2).getLangTypeFor(((Field)localObject1).getType()));
      return;
      localObject1 = ((Expression)localObject1).getType();
      break;
      label172:
      i = 0;
      break label108;
      label178:
      paramApplyExp = Target.pushValue(localObjectType);
      break label123;
      label187:
      localCodeAttr.emitGetField((Field)localObject1);
    }
    label197:
    if ((localObject4 instanceof Method))
    {
      localObject1 = (Method)localObject4;
      ((Method)localObject1).getModifiers();
      boolean bool = ((Method)localObject1).getStaticFlag();
      localObject2 = localObject3[0];
      if (bool)
      {
        paramApplyExp = Target.Ignore;
        ((Expression)localObject2).compile(paramCompilation, paramApplyExp);
        if (!bool) {
          break label279;
        }
        localCodeAttr.emitInvokeStatic((Method)localObject1);
      }
      for (;;)
      {
        paramTarget.compileFromStack(paramCompilation, ((Method)localObject1).getReturnType());
        return;
        paramApplyExp = Target.pushValue(localObjectType);
        break;
        label279:
        localCodeAttr.emitInvoke((Method)localObject1);
      }
    }
    label289:
    localObject2 = ClassMethods.checkName(localExpression);
    if (((localObject1 instanceof ArrayType)) && ("length".equals(localObject2)) && (!this.isStatic))
    {
      localObject3[0].compile(paramCompilation, Target.pushValue((Type)localObject1));
      localCodeAttr.emitArrayLength();
      paramTarget.compileFromStack(paramCompilation, LangPrimType.intType);
      return;
    }
    ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    if (paramArrayOfExpression.length == 2)
    {
      Expression localExpression = paramArrayOfExpression[0];
      paramArrayOfExpression = paramArrayOfExpression[1];
      if ((paramArrayOfExpression instanceof QuoteExp))
      {
        Object localObject = ((QuoteExp)paramArrayOfExpression).getValue();
        if ((localObject instanceof Field)) {
          return ((Field)localObject).getType();
        }
        if ((localObject instanceof Method)) {
          return ((Method)localObject).getReturnType();
        }
        if ((!this.isStatic) && ((localExpression.getType() instanceof ArrayType)) && ("length".equals(ClassMethods.checkName(paramArrayOfExpression, true)))) {
          return LangPrimType.intType;
        }
      }
    }
    return Type.pointer_type;
  }
  
  public Procedure getSetter()
  {
    if (this.setter == null) {
      return super.getSetter();
    }
    return this.setter;
  }
  
  public void set2(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    SlotSet.apply(this.isStatic, paramObject1, (String)paramObject2, paramObject3);
  }
  
  public void setN(Object[] paramArrayOfObject)
  {
    int i = paramArrayOfObject.length;
    if (i != 3) {
      throw new WrongArguments(getSetter(), i);
    }
    set2(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2]);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\SlotGet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */