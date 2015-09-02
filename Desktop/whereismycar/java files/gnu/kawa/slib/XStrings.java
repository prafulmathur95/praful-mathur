package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class XStrings
  extends ModuleBody
{
  public static final XStrings $instance;
  static final IntNum Lit0;
  static final SimpleSymbol Lit1;
  static final SimpleSymbol Lit2 = (SimpleSymbol)new SimpleSymbol("string-length").readResolve();
  public static final ModuleMethod string$Mnlength;
  public static final ModuleMethod substring;
  
  static
  {
    Lit1 = (SimpleSymbol)new SimpleSymbol("substring").readResolve();
    Lit0 = IntNum.make(Integer.MAX_VALUE);
    $instance = new XStrings();
    XStrings localXStrings = $instance;
    substring = new ModuleMethod(localXStrings, 1, Lit1, 12290);
    string$Mnlength = new ModuleMethod(localXStrings, 3, Lit2, 4097);
    $instance.run();
  }
  
  public XStrings()
  {
    ModuleInfo.register(this);
  }
  
  public static Object stringLength(Object paramObject)
  {
    if (paramObject == Values.empty) {
      return Values.empty;
    }
    return Integer.valueOf(((String)paramObject).length());
  }
  
  public static Object substring(Object paramObject1, Object paramObject2)
  {
    return substring(paramObject1, paramObject2, Lit0);
  }
  
  /* Error */
  public static Object substring(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    // Byte code:
    //   0: aload_0
    //   1: getstatic 74	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   4: if_acmpne +20 -> 24
    //   7: iconst_1
    //   8: istore 4
    //   10: iload 4
    //   12: ifeq +18 -> 30
    //   15: iload 4
    //   17: ifeq +33 -> 50
    //   20: getstatic 74	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   23: areturn
    //   24: iconst_0
    //   25: istore 4
    //   27: goto -17 -> 10
    //   30: aload_1
    //   31: getstatic 74	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   34: if_acmpne +81 -> 115
    //   37: iconst_1
    //   38: istore 4
    //   40: iload 4
    //   42: ifeq +79 -> 121
    //   45: iload 4
    //   47: ifne -27 -> 20
    //   50: aload_0
    //   51: checkcast 76	java/lang/String
    //   54: astore_3
    //   55: aload_3
    //   56: invokevirtual 80	java/lang/String:length	()I
    //   59: istore 5
    //   61: aload_1
    //   62: checkcast 94	java/lang/Number
    //   65: invokevirtual 97	java/lang/Number:intValue	()I
    //   68: istore 4
    //   70: iload 4
    //   72: iconst_1
    //   73: isub
    //   74: istore 6
    //   76: aload_2
    //   77: checkcast 94	java/lang/Number
    //   80: invokevirtual 97	java/lang/Number:intValue	()I
    //   83: istore 4
    //   85: iload 5
    //   87: iload 6
    //   89: isub
    //   90: istore 5
    //   92: iload 4
    //   94: iload 5
    //   96: if_icmple +35 -> 131
    //   99: iload 5
    //   101: istore 4
    //   103: aload_3
    //   104: iload 6
    //   106: iload 6
    //   108: iload 4
    //   110: iadd
    //   111: invokevirtual 100	java/lang/String:substring	(II)Ljava/lang/String;
    //   114: areturn
    //   115: iconst_0
    //   116: istore 4
    //   118: goto -78 -> 40
    //   121: aload_2
    //   122: getstatic 74	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   125: if_acmpne -75 -> 50
    //   128: goto -108 -> 20
    //   131: goto -28 -> 103
    //   134: astore_1
    //   135: new 102	gnu/mapping/WrongType
    //   138: dup
    //   139: aload_1
    //   140: ldc 104
    //   142: bipush -2
    //   144: aload_0
    //   145: invokespecial 107	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   148: athrow
    //   149: astore_0
    //   150: new 102	gnu/mapping/WrongType
    //   153: dup
    //   154: aload_0
    //   155: ldc 109
    //   157: bipush -2
    //   159: aload_1
    //   160: invokespecial 107	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   163: athrow
    //   164: astore_0
    //   165: new 102	gnu/mapping/WrongType
    //   168: dup
    //   169: aload_0
    //   170: ldc 111
    //   172: bipush -2
    //   174: aload_2
    //   175: invokespecial 107	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   178: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	179	0	paramObject1	Object
    //   0	179	1	paramObject2	Object
    //   0	179	2	paramObject3	Object
    //   54	50	3	str	String
    //   8	109	4	i	int
    //   59	41	5	j	int
    //   74	37	6	k	int
    // Exception table:
    //   from	to	target	type
    //   50	55	134	java/lang/ClassCastException
    //   61	70	149	java/lang/ClassCastException
    //   76	85	164	java/lang/ClassCastException
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    if (paramModuleMethod.selector == 3) {
      return stringLength(paramObject);
    }
    return super.apply1(paramModuleMethod, paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    if (paramModuleMethod.selector == 1) {
      return substring(paramObject1, paramObject2);
    }
    return super.apply2(paramModuleMethod, paramObject1, paramObject2);
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    if (paramModuleMethod.selector == 1) {
      return substring(paramObject1, paramObject2, paramObject3);
    }
    return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 3)
    {
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    }
    return super.match1(paramModuleMethod, paramObject, paramCallContext);
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 1)
    {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    }
    return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 1)
    {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    }
    return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\XStrings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */