package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.text.Char;

public class characters
  extends ModuleBody
{
  public static final characters $instance;
  static final SimpleSymbol Lit0;
  static final SimpleSymbol Lit1;
  static final SimpleSymbol Lit2;
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7 = (SimpleSymbol)new SimpleSymbol("char>=?").readResolve();
  public static final ModuleMethod char$Eq$Qu;
  public static final ModuleMethod char$Gr$Eq$Qu;
  public static final ModuleMethod char$Gr$Qu;
  public static final ModuleMethod char$Ls$Eq$Qu;
  public static final ModuleMethod char$Ls$Qu;
  public static final ModuleMethod char$Mn$Grinteger;
  public static final ModuleMethod char$Qu;
  public static final ModuleMethod integer$Mn$Grchar;
  
  static
  {
    Lit6 = (SimpleSymbol)new SimpleSymbol("char<=?").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("char>?").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("char<?").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("char=?").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("integer->char").readResolve();
    Lit1 = (SimpleSymbol)new SimpleSymbol("char->integer").readResolve();
    Lit0 = (SimpleSymbol)new SimpleSymbol("char?").readResolve();
    $instance = new characters();
    characters localcharacters = $instance;
    char$Qu = new ModuleMethod(localcharacters, 1, Lit0, 4097);
    char$Mn$Grinteger = new ModuleMethod(localcharacters, 2, Lit1, 4097);
    integer$Mn$Grchar = new ModuleMethod(localcharacters, 3, Lit2, 4097);
    char$Eq$Qu = new ModuleMethod(localcharacters, 4, Lit3, 8194);
    char$Ls$Qu = new ModuleMethod(localcharacters, 5, Lit4, 8194);
    char$Gr$Qu = new ModuleMethod(localcharacters, 6, Lit5, 8194);
    char$Ls$Eq$Qu = new ModuleMethod(localcharacters, 7, Lit6, 8194);
    char$Gr$Eq$Qu = new ModuleMethod(localcharacters, 8, Lit7, 8194);
    $instance.run();
  }
  
  public characters()
  {
    ModuleInfo.register(this);
  }
  
  public static int char$To$Integer(Char paramChar)
  {
    return paramChar.intValue();
  }
  
  public static Char integer$To$Char(int paramInt)
  {
    return Char.make(paramInt);
  }
  
  public static boolean isChar(Object paramObject)
  {
    return paramObject instanceof Char;
  }
  
  public static boolean isChar$Eq(Char paramChar1, Char paramChar2)
  {
    return paramChar1.intValue() == paramChar2.intValue();
  }
  
  public static boolean isChar$Gr(Char paramChar1, Char paramChar2)
  {
    return paramChar1.intValue() > paramChar2.intValue();
  }
  
  public static boolean isChar$Gr$Eq(Char paramChar1, Char paramChar2)
  {
    return paramChar1.intValue() >= paramChar2.intValue();
  }
  
  public static boolean isChar$Ls(Char paramChar1, Char paramChar2)
  {
    return paramChar1.intValue() < paramChar2.intValue();
  }
  
  public static boolean isChar$Ls$Eq(Char paramChar1, Char paramChar2)
  {
    return paramChar1.intValue() <= paramChar2.intValue();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 1: 
      if (isChar(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    try
    {
      paramModuleMethod = (Char)paramObject;
      return Integer.valueOf(char$To$Integer(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        int i = ((Number)paramObject).intValue();
        return integer$To$Char(i);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "integer->char", 1, paramObject);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "char->integer", 1, paramObject);
    }
  }
  
  /* Error */
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 133	gnu/expr/ModuleMethod:selector	I
    //   4: tableswitch	default:+36->40, 4:+44->48, 5:+70->74, 6:+96->100, 7:+122->126, 8:+148->152
    //   40: aload_0
    //   41: aload_1
    //   42: aload_2
    //   43: aload_3
    //   44: invokespecial 168	gnu/expr/ModuleBody:apply2	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   47: areturn
    //   48: aload_2
    //   49: checkcast 108	gnu/text/Char
    //   52: astore_1
    //   53: aload_3
    //   54: checkcast 108	gnu/text/Char
    //   57: astore_2
    //   58: aload_1
    //   59: aload_2
    //   60: invokestatic 170	kawa/lib/characters:isChar$Eq	(Lgnu/text/Char;Lgnu/text/Char;)Z
    //   63: ifeq +7 -> 70
    //   66: getstatic 143	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   69: areturn
    //   70: getstatic 146	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   73: areturn
    //   74: aload_2
    //   75: checkcast 108	gnu/text/Char
    //   78: astore_1
    //   79: aload_3
    //   80: checkcast 108	gnu/text/Char
    //   83: astore_2
    //   84: aload_1
    //   85: aload_2
    //   86: invokestatic 172	kawa/lib/characters:isChar$Ls	(Lgnu/text/Char;Lgnu/text/Char;)Z
    //   89: ifeq +7 -> 96
    //   92: getstatic 143	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   95: areturn
    //   96: getstatic 146	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   99: areturn
    //   100: aload_2
    //   101: checkcast 108	gnu/text/Char
    //   104: astore_1
    //   105: aload_3
    //   106: checkcast 108	gnu/text/Char
    //   109: astore_2
    //   110: aload_1
    //   111: aload_2
    //   112: invokestatic 174	kawa/lib/characters:isChar$Gr	(Lgnu/text/Char;Lgnu/text/Char;)Z
    //   115: ifeq +7 -> 122
    //   118: getstatic 143	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   121: areturn
    //   122: getstatic 146	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   125: areturn
    //   126: aload_2
    //   127: checkcast 108	gnu/text/Char
    //   130: astore_1
    //   131: aload_3
    //   132: checkcast 108	gnu/text/Char
    //   135: astore_2
    //   136: aload_1
    //   137: aload_2
    //   138: invokestatic 176	kawa/lib/characters:isChar$Ls$Eq	(Lgnu/text/Char;Lgnu/text/Char;)Z
    //   141: ifeq +7 -> 148
    //   144: getstatic 143	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   147: areturn
    //   148: getstatic 146	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   151: areturn
    //   152: aload_2
    //   153: checkcast 108	gnu/text/Char
    //   156: astore_1
    //   157: aload_3
    //   158: checkcast 108	gnu/text/Char
    //   161: astore_2
    //   162: aload_1
    //   163: aload_2
    //   164: invokestatic 178	kawa/lib/characters:isChar$Gr$Eq	(Lgnu/text/Char;Lgnu/text/Char;)Z
    //   167: ifeq +7 -> 174
    //   170: getstatic 143	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   173: areturn
    //   174: getstatic 146	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   177: areturn
    //   178: astore_1
    //   179: new 161	gnu/mapping/WrongType
    //   182: dup
    //   183: aload_1
    //   184: ldc 54
    //   186: iconst_1
    //   187: aload_2
    //   188: invokespecial 164	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   191: athrow
    //   192: astore_1
    //   193: new 161	gnu/mapping/WrongType
    //   196: dup
    //   197: aload_1
    //   198: ldc 54
    //   200: iconst_2
    //   201: aload_3
    //   202: invokespecial 164	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   205: athrow
    //   206: astore_1
    //   207: new 161	gnu/mapping/WrongType
    //   210: dup
    //   211: aload_1
    //   212: ldc 50
    //   214: iconst_1
    //   215: aload_2
    //   216: invokespecial 164	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   219: athrow
    //   220: astore_1
    //   221: new 161	gnu/mapping/WrongType
    //   224: dup
    //   225: aload_1
    //   226: ldc 50
    //   228: iconst_2
    //   229: aload_3
    //   230: invokespecial 164	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   233: athrow
    //   234: astore_1
    //   235: new 161	gnu/mapping/WrongType
    //   238: dup
    //   239: aload_1
    //   240: ldc 46
    //   242: iconst_1
    //   243: aload_2
    //   244: invokespecial 164	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   247: athrow
    //   248: astore_1
    //   249: new 161	gnu/mapping/WrongType
    //   252: dup
    //   253: aload_1
    //   254: ldc 46
    //   256: iconst_2
    //   257: aload_3
    //   258: invokespecial 164	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   261: athrow
    //   262: astore_1
    //   263: new 161	gnu/mapping/WrongType
    //   266: dup
    //   267: aload_1
    //   268: ldc 42
    //   270: iconst_1
    //   271: aload_2
    //   272: invokespecial 164	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   275: athrow
    //   276: astore_1
    //   277: new 161	gnu/mapping/WrongType
    //   280: dup
    //   281: aload_1
    //   282: ldc 42
    //   284: iconst_2
    //   285: aload_3
    //   286: invokespecial 164	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   289: athrow
    //   290: astore_1
    //   291: new 161	gnu/mapping/WrongType
    //   294: dup
    //   295: aload_1
    //   296: ldc 30
    //   298: iconst_1
    //   299: aload_2
    //   300: invokespecial 164	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   303: athrow
    //   304: astore_1
    //   305: new 161	gnu/mapping/WrongType
    //   308: dup
    //   309: aload_1
    //   310: ldc 30
    //   312: iconst_2
    //   313: aload_3
    //   314: invokespecial 164	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   317: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	318	0	this	characters
    //   0	318	1	paramModuleMethod	ModuleMethod
    //   0	318	2	paramObject1	Object
    //   0	318	3	paramObject2	Object
    // Exception table:
    //   from	to	target	type
    //   48	53	178	java/lang/ClassCastException
    //   53	58	192	java/lang/ClassCastException
    //   74	79	206	java/lang/ClassCastException
    //   79	84	220	java/lang/ClassCastException
    //   100	105	234	java/lang/ClassCastException
    //   105	110	248	java/lang/ClassCastException
    //   126	131	262	java/lang/ClassCastException
    //   131	136	276	java/lang/ClassCastException
    //   152	157	290	java/lang/ClassCastException
    //   157	162	304	java/lang/ClassCastException
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 3: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 2: 
      if (!(paramObject instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    }
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    default: 
      i = super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
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
              return i;
            } while (!(paramObject1 instanceof Char));
            paramCallContext.value1 = paramObject1;
            if (!(paramObject2 instanceof Char)) {
              return -786430;
            }
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } while (!(paramObject1 instanceof Char));
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Char)) {
            return -786430;
          }
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } while (!(paramObject1 instanceof Char));
        paramCallContext.value1 = paramObject1;
        if (!(paramObject2 instanceof Char)) {
          return -786430;
        }
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      } while (!(paramObject1 instanceof Char));
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Char)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } while (!(paramObject1 instanceof Char));
    paramCallContext.value1 = paramObject1;
    if (!(paramObject2 instanceof Char)) {
      return -786430;
    }
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\characters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */