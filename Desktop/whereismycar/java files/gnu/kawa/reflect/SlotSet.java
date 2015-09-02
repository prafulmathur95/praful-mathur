package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.CheckedTarget;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.lists.FString;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure3;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import kawa.standard.Scheme;

public class SlotSet
  extends Procedure3
  implements Inlineable
{
  public static final SlotSet set$Mnfield$Ex = new SlotSet("set-field!", false);
  public static final SlotSet set$Mnstatic$Mnfield$Ex = new SlotSet("set-static-field!", true);
  public static final SlotSet setFieldReturnObject = new SlotSet("set-field-return-object!", false);
  static final Type[] type1Array = new Type[1];
  boolean isStatic;
  boolean returnSelf;
  
  static
  {
    setFieldReturnObject.returnSelf = true;
  }
  
  public SlotSet(String paramString, boolean paramBoolean)
  {
    super(paramString);
    this.isStatic = paramBoolean;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplySlotSet");
  }
  
  /* Error */
  public static void apply(boolean paramBoolean, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    // Byte code:
    //   0: invokestatic 75	gnu/expr/Language:getDefaultLanguage	()Lgnu/expr/Language;
    //   3: astore 8
    //   5: iconst_0
    //   6: istore 9
    //   8: aload_2
    //   9: instanceof 77
    //   12: ifne +17 -> 29
    //   15: aload_2
    //   16: instanceof 79
    //   19: ifne +10 -> 29
    //   22: aload_2
    //   23: instanceof 81
    //   26: ifeq +69 -> 95
    //   29: aload_2
    //   30: invokevirtual 87	java/lang/Object:toString	()Ljava/lang/String;
    //   33: astore 6
    //   35: aload 6
    //   37: invokestatic 93	gnu/expr/Compilation:mangleNameIfNeeded	(Ljava/lang/String;)Ljava/lang/String;
    //   40: astore 5
    //   42: iload_0
    //   43: ifeq +43 -> 86
    //   46: aload_1
    //   47: invokestatic 99	gnu/kawa/reflect/SlotGet:coerceToClass	(Ljava/lang/Object;)Ljava/lang/Class;
    //   50: astore 4
    //   52: aload_2
    //   53: instanceof 101
    //   56: ifeq +60 -> 116
    //   59: aload_2
    //   60: checkcast 101	gnu/bytecode/Field
    //   63: invokevirtual 105	gnu/bytecode/Field:getReflectField	()Ljava/lang/reflect/Field;
    //   66: astore 7
    //   68: aload 7
    //   70: aload_1
    //   71: aload 8
    //   73: aload 7
    //   75: invokevirtual 111	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   78: aload_3
    //   79: invokevirtual 115	gnu/expr/Language:coerceFromObject	(Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/Object;
    //   82: invokevirtual 118	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   85: return
    //   86: aload_1
    //   87: invokevirtual 121	java/lang/Object:getClass	()Ljava/lang/Class;
    //   90: astore 4
    //   92: goto -40 -> 52
    //   95: aload_2
    //   96: checkcast 123	gnu/bytecode/Member
    //   99: invokeinterface 126 1 0
    //   104: astore 6
    //   106: aload 6
    //   108: astore 5
    //   110: aconst_null
    //   111: astore 4
    //   113: goto -61 -> 52
    //   116: aload 4
    //   118: aload 5
    //   120: invokevirtual 132	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   123: astore 7
    //   125: goto -57 -> 68
    //   128: astore 7
    //   130: iconst_1
    //   131: istore 9
    //   133: aload_2
    //   134: instanceof 134
    //   137: istore 10
    //   139: iload 10
    //   141: ifeq +128 -> 269
    //   144: aload 5
    //   146: astore_2
    //   147: iload 10
    //   149: istore_0
    //   150: iload 10
    //   152: ifeq +21 -> 173
    //   155: aload_2
    //   156: ldc -121
    //   158: invokevirtual 139	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   161: istore 11
    //   163: iload 10
    //   165: istore_0
    //   166: iload 11
    //   168: ifne +5 -> 173
    //   171: iconst_0
    //   172: istore_0
    //   173: iload_0
    //   174: ifeq +106 -> 280
    //   177: new 141	java/lang/StringBuilder
    //   180: dup
    //   181: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   184: ldc -111
    //   186: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: aload_2
    //   190: iconst_3
    //   191: invokevirtual 153	java/lang/String:substring	(I)Ljava/lang/String;
    //   194: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   200: astore 5
    //   202: aload 4
    //   204: aload 5
    //   206: getstatic 158	gnu/kawa/reflect/SlotGet:noClasses	[Ljava/lang/Class;
    //   209: invokevirtual 162	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   212: astore 5
    //   214: iconst_1
    //   215: anewarray 128	java/lang/Class
    //   218: astore 7
    //   220: aload 7
    //   222: iconst_0
    //   223: aload 5
    //   225: invokevirtual 167	java/lang/reflect/Method:getReturnType	()Ljava/lang/Class;
    //   228: aastore
    //   229: aload 4
    //   231: aload_2
    //   232: aload 7
    //   234: invokevirtual 162	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   237: aload_1
    //   238: iconst_1
    //   239: anewarray 83	java/lang/Object
    //   242: dup
    //   243: iconst_0
    //   244: aload 8
    //   246: aload 7
    //   248: iconst_0
    //   249: aaload
    //   250: aload_3
    //   251: invokevirtual 115	gnu/expr/Language:coerceFromObject	(Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/Object;
    //   254: aastore
    //   255: invokevirtual 171	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   258: pop
    //   259: return
    //   260: astore_1
    //   261: aload_1
    //   262: invokevirtual 175	java/lang/reflect/InvocationTargetException:getTargetException	()Ljava/lang/Throwable;
    //   265: invokestatic 181	gnu/mapping/WrappedException:wrapIfNeeded	(Ljava/lang/Throwable;)Ljava/lang/RuntimeException;
    //   268: athrow
    //   269: ldc -121
    //   271: aload 6
    //   273: invokestatic 187	gnu/expr/ClassExp:slotToMethodName	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   276: astore_2
    //   277: goto -130 -> 147
    //   280: ldc -111
    //   282: aload 6
    //   284: invokestatic 187	gnu/expr/ClassExp:slotToMethodName	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   287: astore 5
    //   289: goto -87 -> 202
    //   292: astore 5
    //   294: iload_0
    //   295: ifeq +43 -> 338
    //   298: new 141	java/lang/StringBuilder
    //   301: dup
    //   302: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   305: ldc -67
    //   307: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: aload_2
    //   311: iconst_3
    //   312: invokevirtual 153	java/lang/String:substring	(I)Ljava/lang/String;
    //   315: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   321: astore 5
    //   323: aload 4
    //   325: aload 5
    //   327: getstatic 158	gnu/kawa/reflect/SlotGet:noClasses	[Ljava/lang/Class;
    //   330: invokevirtual 162	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   333: astore 5
    //   335: goto -121 -> 214
    //   338: ldc -67
    //   340: aload 6
    //   342: invokestatic 187	gnu/expr/ClassExp:slotToMethodName	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   345: astore 5
    //   347: goto -24 -> 323
    //   350: astore_1
    //   351: iconst_1
    //   352: istore 9
    //   354: iload 9
    //   356: ifeq +31 -> 387
    //   359: new 191	java/lang/RuntimeException
    //   362: dup
    //   363: new 141	java/lang/StringBuilder
    //   366: dup
    //   367: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   370: ldc -63
    //   372: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   375: aload 6
    //   377: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   380: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   383: invokespecial 194	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   386: athrow
    //   387: new 191	java/lang/RuntimeException
    //   390: dup
    //   391: new 141	java/lang/StringBuilder
    //   394: dup
    //   395: invokespecial 143	java/lang/StringBuilder:<init>	()V
    //   398: ldc -60
    //   400: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: aload 6
    //   405: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: ldc -58
    //   410: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   413: aload 4
    //   415: invokevirtual 199	java/lang/Class:getName	()Ljava/lang/String;
    //   418: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   421: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   424: invokespecial 194	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   427: athrow
    //   428: astore_1
    //   429: goto -75 -> 354
    //   432: astore 7
    //   434: goto -301 -> 133
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	437	0	paramBoolean	boolean
    //   0	437	1	paramObject1	Object
    //   0	437	2	paramObject2	Object
    //   0	437	3	paramObject3	Object
    //   50	364	4	localClass	Class
    //   40	248	5	localObject1	Object
    //   292	1	5	localException	Exception
    //   321	25	5	localObject2	Object
    //   33	371	6	str	String
    //   66	58	7	localField	java.lang.reflect.Field
    //   128	1	7	localIllegalAccessException	IllegalAccessException
    //   218	29	7	arrayOfClass	Class[]
    //   432	1	7	localNoSuchFieldException	NoSuchFieldException
    //   3	242	8	localLanguage	Language
    //   6	349	9	i	int
    //   137	27	10	bool1	boolean
    //   161	6	11	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   52	68	128	java/lang/IllegalAccessException
    //   68	85	128	java/lang/IllegalAccessException
    //   116	125	128	java/lang/IllegalAccessException
    //   133	139	260	java/lang/reflect/InvocationTargetException
    //   155	163	260	java/lang/reflect/InvocationTargetException
    //   177	202	260	java/lang/reflect/InvocationTargetException
    //   202	214	260	java/lang/reflect/InvocationTargetException
    //   214	259	260	java/lang/reflect/InvocationTargetException
    //   269	277	260	java/lang/reflect/InvocationTargetException
    //   280	289	260	java/lang/reflect/InvocationTargetException
    //   298	323	260	java/lang/reflect/InvocationTargetException
    //   323	335	260	java/lang/reflect/InvocationTargetException
    //   338	347	260	java/lang/reflect/InvocationTargetException
    //   177	202	292	java/lang/Exception
    //   202	214	292	java/lang/Exception
    //   280	289	292	java/lang/Exception
    //   133	139	350	java/lang/IllegalAccessException
    //   155	163	350	java/lang/IllegalAccessException
    //   177	202	350	java/lang/IllegalAccessException
    //   202	214	350	java/lang/IllegalAccessException
    //   214	259	350	java/lang/IllegalAccessException
    //   269	277	350	java/lang/IllegalAccessException
    //   280	289	350	java/lang/IllegalAccessException
    //   298	323	350	java/lang/IllegalAccessException
    //   323	335	350	java/lang/IllegalAccessException
    //   338	347	350	java/lang/IllegalAccessException
    //   133	139	428	java/lang/NoSuchMethodException
    //   155	163	428	java/lang/NoSuchMethodException
    //   177	202	428	java/lang/NoSuchMethodException
    //   202	214	428	java/lang/NoSuchMethodException
    //   214	259	428	java/lang/NoSuchMethodException
    //   269	277	428	java/lang/NoSuchMethodException
    //   280	289	428	java/lang/NoSuchMethodException
    //   298	323	428	java/lang/NoSuchMethodException
    //   323	335	428	java/lang/NoSuchMethodException
    //   338	347	428	java/lang/NoSuchMethodException
    //   52	68	432	java/lang/NoSuchFieldException
    //   68	85	432	java/lang/NoSuchFieldException
    //   116	125	432	java/lang/NoSuchFieldException
  }
  
  static void compileSet(Procedure paramProcedure, ObjectType paramObjectType, Expression paramExpression, Object paramObject, Compilation paramCompilation)
  {
    paramObjectType = paramCompilation.getCode();
    Object localObject = paramCompilation.getLanguage();
    int i;
    boolean bool;
    if (((paramProcedure instanceof SlotSet)) && (((SlotSet)paramProcedure).isStatic))
    {
      i = 1;
      if (!(paramObject instanceof Field)) {
        break label152;
      }
      paramObject = (Field)paramObject;
      bool = ((Field)paramObject).getStaticFlag();
      localObject = ((Language)localObject).getLangTypeFor(((Field)paramObject).getType());
      if ((i != 0) && (!bool)) {
        paramCompilation.error('e', "cannot access non-static field `" + ((Field)paramObject).getName() + "' using `" + paramProcedure.getName() + '\'');
      }
      paramExpression.compile(paramCompilation, CheckedTarget.getInstance((Type)localObject));
      if (!bool) {
        break label146;
      }
      paramObjectType.emitPutStatic((Field)paramObject);
    }
    for (;;)
    {
      return;
      i = 0;
      break;
      label146:
      paramObjectType.emitPutField((Field)paramObject);
      return;
      label152:
      if ((paramObject instanceof Method))
      {
        paramObject = (Method)paramObject;
        bool = ((Method)paramObject).getStaticFlag();
        if ((i != 0) && (!bool)) {
          paramCompilation.error('e', "cannot call non-static getter method `" + ((Method)paramObject).getName() + "' using `" + paramProcedure.getName() + '\'');
        }
        paramExpression.compile(paramCompilation, CheckedTarget.getInstance(((Language)localObject).getLangTypeFor(paramObject.getParameterTypes()[0])));
        if (bool) {
          paramObjectType.emitInvokeStatic((Method)paramObject);
        }
        while (!((Method)paramObject).getReturnType().isVoid())
        {
          paramObjectType.emitPop(1);
          return;
          paramObjectType.emitInvoke((Method)paramObject);
        }
      }
    }
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
      paramObjectType = paramObjectType.getMethod(ClassExp.slotToMethodName("set", paramString), type1Array);
    } while (paramObjectType == null);
    return paramObjectType;
  }
  
  public static void setField(Object paramObject1, String paramString, Object paramObject2)
  {
    apply(false, paramObject1, paramString, paramObject2);
  }
  
  public static void setStaticField(Object paramObject1, String paramString, Object paramObject2)
  {
    apply(true, paramObject1, paramString, paramObject2);
  }
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    apply(this.isStatic, paramObject1, paramObject2, paramObject3);
    if (this.returnSelf) {
      return paramObject1;
    }
    return Values.empty;
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int i = arrayOfExpression.length;
    if (i != 3)
    {
      if (i < 3) {}
      for (paramApplyExp = "too few";; paramApplyExp = "too many")
      {
        paramCompilation.error('e', paramApplyExp + " arguments to `" + getName() + '\'');
        paramCompilation.compileConstant(null, paramTarget);
        return;
      }
    }
    Object localObject1 = arrayOfExpression[0];
    Object localObject2 = arrayOfExpression[1];
    Object localObject3 = arrayOfExpression[2];
    ObjectType localObjectType;
    ClassType localClassType;
    if (this.isStatic)
    {
      localObject3 = Scheme.exp2Type((Expression)localObject1);
      localObject1 = null;
      if ((!(localObject3 instanceof ObjectType)) || (!(localObject2 instanceof QuoteExp))) {
        break label530;
      }
      localObject2 = ((QuoteExp)localObject2).getValue();
      localObjectType = (ObjectType)localObject3;
      if (paramCompilation.curClass == null) {
        break label464;
      }
      localClassType = paramCompilation.curClass;
      label160:
      if ((!(localObject2 instanceof String)) && (!(localObject2 instanceof FString)) && (!(localObject2 instanceof Symbol))) {
        break label473;
      }
      String str = localObject2.toString();
      Member localMember = lookupMember(localObjectType, str, localClassType);
      localObject2 = str;
      localObject1 = localMember;
      if (localMember == null)
      {
        localObject2 = str;
        localObject1 = localMember;
        if (localObject3 != Type.pointer_type)
        {
          localObject2 = str;
          localObject1 = localMember;
          if (paramCompilation.warnUnknownMember())
          {
            paramCompilation.error('w', "no slot `" + str + "' in " + localObjectType.getName());
            localObject1 = localMember;
            localObject2 = str;
          }
        }
      }
      label295:
      if (localObject1 == null) {
        break label530;
      }
      if ((((Member)localObject1).getModifiers() & 0x8) == 0) {
        break label506;
      }
      i = 1;
      label316:
      if ((localClassType != null) && (!localClassType.isAccessible((Member)localObject1, localObjectType))) {
        paramCompilation.error('e', "slot '" + (String)localObject2 + "' in " + ((Member)localObject1).getDeclaringClass().getName() + " not accessible here");
      }
      localObject2 = arrayOfExpression[0];
      if (i == 0) {
        break label512;
      }
    }
    label464:
    label473:
    label506:
    label512:
    for (paramApplyExp = Target.Ignore;; paramApplyExp = Target.pushValue(localObjectType))
    {
      ((Expression)localObject2).compile(paramCompilation, paramApplyExp);
      if (this.returnSelf) {
        paramCompilation.getCode().emitDup(localObjectType.getImplementationType());
      }
      compileSet(this, localObjectType, arrayOfExpression[2], localObject1, paramCompilation);
      if (!this.returnSelf) {
        break label521;
      }
      paramTarget.compileFromStack(paramCompilation, localObjectType);
      return;
      localObject3 = ((Expression)localObject1).getType();
      break;
      localClassType = paramCompilation.mainClass;
      break label160;
      if ((localObject2 instanceof Member))
      {
        localObject1 = (Member)localObject2;
        localObject2 = ((Member)localObject1).getName();
        break label295;
      }
      localObject2 = null;
      break label295;
      i = 0;
      break label316;
    }
    label521:
    paramCompilation.compileConstant(Values.empty, paramTarget);
    return;
    label530:
    ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\SlotSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */