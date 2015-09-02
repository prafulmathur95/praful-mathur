package kawa.lib;

import gnu.expr.ApplicationMainSupport;
import gnu.expr.Compilation;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.IsEqual;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.InputStream;
import kawa.lang.CompileFile;
import kawa.lang.NamedException;

public class system
  extends ModuleBody
{
  public static final system $instance;
  static final IntNum Lit0;
  static final IntNum Lit1;
  static final SimpleSymbol Lit10;
  static final SimpleSymbol Lit11 = (SimpleSymbol)new SimpleSymbol("process-command-line-assignments").readResolve();
  static final SimpleSymbol Lit2;
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod jdField_catch;
  public static Procedure command$Mnparse;
  public static final ModuleMethod compile$Mnfile;
  public static final ModuleMethod convert$Mnlist$Mnto$Mnstring$Mnarray;
  public static final ModuleMethod convert$Mnvector$Mnto$Mnstring$Mnarray;
  public static final ModuleMethod make$Mnprocess;
  public static final ModuleMethod open$Mninput$Mnpipe;
  public static final ModuleMethod process$Mncommand$Mnline$Mnassignments;
  public static final ModuleMethod system;
  public static final ModuleMethod tokenize$Mnstring$Mnto$Mnstring$Mnarray;
  public static final ModuleMethod tokenize$Mnstring$Mnusing$Mnshell;
  
  static
  {
    Lit10 = (SimpleSymbol)new SimpleSymbol("catch").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("compile-file").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("tokenize-string-using-shell").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("tokenize-string-to-string-array").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("convert-list-to-string-array").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("convert-vector-to-string-array").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("system").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("open-input-pipe").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("make-process").readResolve();
    Lit1 = IntNum.make(1);
    Lit0 = IntNum.make(0);
    $instance = new system();
    system localsystem = $instance;
    make$Mnprocess = new ModuleMethod(localsystem, 1, Lit2, 8194);
    open$Mninput$Mnpipe = new ModuleMethod(localsystem, 2, Lit3, 4097);
    system = new ModuleMethod(localsystem, 3, Lit4, 4097);
    convert$Mnvector$Mnto$Mnstring$Mnarray = new ModuleMethod(localsystem, 4, Lit5, 4097);
    convert$Mnlist$Mnto$Mnstring$Mnarray = new ModuleMethod(localsystem, 5, Lit6, 4097);
    tokenize$Mnstring$Mnto$Mnstring$Mnarray = new ModuleMethod(localsystem, 6, Lit7, 4097);
    tokenize$Mnstring$Mnusing$Mnshell = new ModuleMethod(localsystem, 7, Lit8, 4097);
    compile$Mnfile = new ModuleMethod(localsystem, 8, Lit9, 8194);
    catch = new ModuleMethod(localsystem, 9, Lit10, 12291);
    process$Mncommand$Mnline$Mnassignments = new ModuleMethod(localsystem, 10, Lit11, 0);
    $instance.run();
  }
  
  public system()
  {
    ModuleInfo.register(this);
  }
  
  public static Object jdMethod_catch(Object paramObject, Procedure paramProcedure1, Procedure paramProcedure2)
  {
    try
    {
      paramProcedure1 = paramProcedure1.apply0();
      return paramProcedure1;
    }
    catch (NamedException paramProcedure1) {}
    return paramProcedure1.applyHandler(paramObject, paramProcedure2);
  }
  
  public static void compileFile(CharSequence paramCharSequence, String paramString)
  {
    SourceMessages localSourceMessages = new SourceMessages();
    paramCharSequence = CompileFile.read(paramCharSequence.toString(), localSourceMessages);
    paramCharSequence.explicit = true;
    if (localSourceMessages.seenErrors()) {
      throw ((Throwable)new SyntaxException(localSourceMessages));
    }
    paramCharSequence.compileToArchive(paramCharSequence.getModule(), paramString);
    if (localSourceMessages.seenErrors()) {
      throw ((Throwable)new SyntaxException(localSourceMessages));
    }
  }
  
  /* Error */
  public static Object convertListToStringArray(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: checkcast 193	gnu/lists/LList
    //   4: astore_1
    //   5: aload_1
    //   6: invokestatic 199	kawa/lib/lists:length	(Lgnu/lists/LList;)I
    //   9: anewarray 201	java/lang/String
    //   12: astore_1
    //   13: iconst_0
    //   14: istore_3
    //   15: aload_0
    //   16: invokestatic 205	kawa/lib/lists:isNull	(Ljava/lang/Object;)Z
    //   19: ifeq +5 -> 24
    //   22: aload_1
    //   23: areturn
    //   24: aload_0
    //   25: checkcast 207	gnu/lists/Pair
    //   28: astore_2
    //   29: aload_2
    //   30: invokevirtual 210	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   33: astore_0
    //   34: aload_0
    //   35: ifnonnull +21 -> 56
    //   38: aconst_null
    //   39: astore_0
    //   40: aload_1
    //   41: iload_3
    //   42: aload_0
    //   43: aastore
    //   44: aload_2
    //   45: invokevirtual 213	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   48: astore_0
    //   49: iload_3
    //   50: iconst_1
    //   51: iadd
    //   52: istore_3
    //   53: goto -38 -> 15
    //   56: aload_0
    //   57: invokevirtual 216	java/lang/Object:toString	()Ljava/lang/String;
    //   60: astore_0
    //   61: goto -21 -> 40
    //   64: astore_1
    //   65: new 218	gnu/mapping/WrongType
    //   68: dup
    //   69: aload_1
    //   70: ldc -37
    //   72: iconst_1
    //   73: aload_0
    //   74: invokespecial 222	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   77: athrow
    //   78: astore_1
    //   79: new 218	gnu/mapping/WrongType
    //   82: dup
    //   83: aload_1
    //   84: ldc -32
    //   86: bipush -2
    //   88: aload_0
    //   89: invokespecial 222	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   92: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	paramObject	Object
    //   4	37	1	localObject	Object
    //   64	6	1	localClassCastException1	ClassCastException
    //   78	6	1	localClassCastException2	ClassCastException
    //   28	17	2	localPair	gnu.lists.Pair
    //   14	39	3	i	int
    // Exception table:
    //   from	to	target	type
    //   0	5	64	java/lang/ClassCastException
    //   24	29	78	java/lang/ClassCastException
  }
  
  /* Error */
  public static Object convertVectorToStringArray(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: checkcast 227	gnu/lists/FVector
    //   4: astore_1
    //   5: aload_1
    //   6: invokestatic 233	kawa/lib/vectors:vectorLength	(Lgnu/lists/FVector;)I
    //   9: istore 4
    //   11: iload 4
    //   13: anewarray 201	java/lang/String
    //   16: astore_3
    //   17: getstatic 93	kawa/lib/system:Lit0	Lgnu/math/IntNum;
    //   20: astore_1
    //   21: getstatic 239	kawa/standard/Scheme:numEqu	Lgnu/kawa/functions/NumberCompare;
    //   24: aload_1
    //   25: iload 4
    //   27: invokestatic 245	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   30: invokevirtual 249	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   33: getstatic 255	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   36: if_acmpne +111 -> 147
    //   39: aload_1
    //   40: checkcast 257	java/lang/Number
    //   43: invokevirtual 261	java/lang/Number:intValue	()I
    //   46: istore 5
    //   48: aload_0
    //   49: checkcast 227	gnu/lists/FVector
    //   52: astore_2
    //   53: aload_1
    //   54: checkcast 257	java/lang/Number
    //   57: invokevirtual 261	java/lang/Number:intValue	()I
    //   60: istore 6
    //   62: aload_2
    //   63: iload 6
    //   65: invokestatic 265	kawa/lib/vectors:vectorRef	(Lgnu/lists/FVector;I)Ljava/lang/Object;
    //   68: astore_2
    //   69: aload_2
    //   70: ifnonnull +24 -> 94
    //   73: aconst_null
    //   74: astore_2
    //   75: aload_3
    //   76: iload 5
    //   78: aload_2
    //   79: aastore
    //   80: getstatic 271	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   83: aload_1
    //   84: getstatic 91	kawa/lib/system:Lit1	Lgnu/math/IntNum;
    //   87: invokevirtual 249	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   90: astore_1
    //   91: goto -70 -> 21
    //   94: aload_2
    //   95: invokevirtual 216	java/lang/Object:toString	()Ljava/lang/String;
    //   98: astore_2
    //   99: goto -24 -> 75
    //   102: astore_1
    //   103: new 218	gnu/mapping/WrongType
    //   106: dup
    //   107: aload_1
    //   108: ldc_w 273
    //   111: iconst_1
    //   112: aload_0
    //   113: invokespecial 222	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   116: athrow
    //   117: astore_1
    //   118: new 218	gnu/mapping/WrongType
    //   121: dup
    //   122: aload_1
    //   123: ldc_w 275
    //   126: iconst_1
    //   127: aload_0
    //   128: invokespecial 222	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   131: athrow
    //   132: astore_0
    //   133: new 218	gnu/mapping/WrongType
    //   136: dup
    //   137: aload_0
    //   138: ldc_w 275
    //   141: iconst_2
    //   142: aload_1
    //   143: invokespecial 222	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   146: athrow
    //   147: aload_3
    //   148: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	149	0	paramObject	Object
    //   4	87	1	localObject1	Object
    //   102	6	1	localClassCastException1	ClassCastException
    //   117	26	1	localClassCastException2	ClassCastException
    //   52	47	2	localObject2	Object
    //   16	132	3	arrayOfString	String[]
    //   9	17	4	i	int
    //   46	31	5	j	int
    //   60	4	6	k	int
    // Exception table:
    //   from	to	target	type
    //   0	5	102	java/lang/ClassCastException
    //   48	53	117	java/lang/ClassCastException
    //   53	62	132	java/lang/ClassCastException
  }
  
  public static Process makeProcess(Object paramObject1, Object paramObject2)
  {
    if (vectors.isVector(paramObject1)) {
      paramObject1 = convertVectorToStringArray(paramObject1);
    }
    for (;;)
    {
      Runtime localRuntime = Runtime.getRuntime();
      try
      {
        arrayOfString = (String[])paramObject1;
      }
      catch (ClassCastException paramObject2)
      {
        String[] arrayOfString;
        throw new WrongType((ClassCastException)paramObject2, "java.lang.Runtime.exec(java.lang.String[],java.lang.String[])", 2, paramObject1);
      }
      try
      {
        paramObject1 = (String[])paramObject2;
        return localRuntime.exec(arrayOfString, (String[])paramObject1);
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "java.lang.Runtime.exec(java.lang.String[],java.lang.String[])", 3, paramObject2);
      }
      if (lists.isList(paramObject1)) {
        paramObject1 = convertListToStringArray(paramObject1);
      } else if (strings.isString(paramObject1)) {
        paramObject1 = command$Mnparse.apply1(paramObject1);
      } else if (!(paramObject1 instanceof String[])) {
        paramObject1 = misc.error$V("invalid arguments to make-process", new Object[0]);
      }
    }
  }
  
  public static InputStream openInputPipe(Object paramObject)
  {
    return makeProcess(paramObject, null).getInputStream();
  }
  
  public static void processCommandLineAssignments() {}
  
  public static int system(Object paramObject)
  {
    return makeProcess(paramObject, null).waitFor();
  }
  
  /* Error */
  public static Object tokenizeStringToStringArray(String paramString)
  {
    // Byte code:
    //   0: new 343	java/util/StringTokenizer
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 344	java/util/StringTokenizer:<init>	(Ljava/lang/String;)V
    //   8: astore_1
    //   9: getstatic 348	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   12: astore_0
    //   13: aload_1
    //   14: invokevirtual 351	java/util/StringTokenizer:hasMoreTokens	()Z
    //   17: ifeq +15 -> 32
    //   20: aload_1
    //   21: invokevirtual 354	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   24: aload_0
    //   25: invokestatic 358	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   28: astore_0
    //   29: goto -16 -> 13
    //   32: aload_0
    //   33: checkcast 193	gnu/lists/LList
    //   36: astore_1
    //   37: aload_1
    //   38: invokestatic 199	kawa/lib/lists:length	(Lgnu/lists/LList;)I
    //   41: istore_3
    //   42: iload_3
    //   43: anewarray 201	java/lang/String
    //   46: astore_1
    //   47: iload_3
    //   48: iconst_1
    //   49: isub
    //   50: istore_3
    //   51: aload_0
    //   52: invokestatic 205	kawa/lib/lists:isNull	(Ljava/lang/Object;)Z
    //   55: ifeq +5 -> 60
    //   58: aload_1
    //   59: areturn
    //   60: aload_0
    //   61: checkcast 207	gnu/lists/Pair
    //   64: astore_2
    //   65: aload_2
    //   66: invokevirtual 210	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   69: astore_0
    //   70: aload_0
    //   71: ifnonnull +21 -> 92
    //   74: aconst_null
    //   75: astore_0
    //   76: aload_1
    //   77: iload_3
    //   78: aload_0
    //   79: aastore
    //   80: aload_2
    //   81: invokevirtual 213	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   84: astore_0
    //   85: iload_3
    //   86: iconst_1
    //   87: isub
    //   88: istore_3
    //   89: goto -38 -> 51
    //   92: aload_0
    //   93: invokevirtual 216	java/lang/Object:toString	()Ljava/lang/String;
    //   96: astore_0
    //   97: goto -21 -> 76
    //   100: astore_1
    //   101: new 218	gnu/mapping/WrongType
    //   104: dup
    //   105: aload_1
    //   106: ldc -37
    //   108: iconst_1
    //   109: aload_0
    //   110: invokespecial 222	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   113: athrow
    //   114: astore_1
    //   115: new 218	gnu/mapping/WrongType
    //   118: dup
    //   119: aload_1
    //   120: ldc -32
    //   122: bipush -2
    //   124: aload_0
    //   125: invokespecial 222	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   128: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	129	0	paramString	String
    //   8	69	1	localObject	Object
    //   100	6	1	localClassCastException1	ClassCastException
    //   114	6	1	localClassCastException2	ClassCastException
    //   64	17	2	localPair	gnu.lists.Pair
    //   41	48	3	i	int
    // Exception table:
    //   from	to	target	type
    //   32	37	100	java/lang/ClassCastException
    //   60	65	114	java/lang/ClassCastException
  }
  
  public static String[] tokenizeStringUsingShell(Object paramObject)
  {
    if (paramObject == null) {}
    for (paramObject = null;; paramObject = paramObject.toString()) {
      return new String[] { "/bin/sh", "-c", paramObject };
    }
  }
  
  public Object apply0(ModuleMethod paramModuleMethod)
  {
    if (paramModuleMethod.selector == 10)
    {
      processCommandLineAssignments();
      return Values.empty;
    }
    return super.apply0(paramModuleMethod);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 2: 
      return openInputPipe(paramObject);
    case 3: 
      return Integer.valueOf(system(paramObject));
    case 4: 
      return convertVectorToStringArray(paramObject);
    case 5: 
      return convertListToStringArray(paramObject);
    case 6: 
      if (paramObject == null) {}
      for (paramModuleMethod = null;; paramModuleMethod = paramObject.toString()) {
        return tokenizeStringToStringArray(paramModuleMethod);
      }
    }
    return tokenizeStringUsingShell(paramObject);
  }
  
  /* Error */
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 369	gnu/expr/ModuleMethod:selector	I
    //   4: lookupswitch	default:+28->32, 1:+36->40, 8:+42->46
    //   32: aload_0
    //   33: aload_1
    //   34: aload_2
    //   35: aload_3
    //   36: invokespecial 393	gnu/expr/ModuleBody:apply2	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   39: areturn
    //   40: aload_2
    //   41: aload_3
    //   42: invokestatic 323	kawa/lib/system:makeProcess	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Process;
    //   45: areturn
    //   46: aload_2
    //   47: checkcast 152	java/lang/CharSequence
    //   50: astore 4
    //   52: aload_3
    //   53: ifnonnull +15 -> 68
    //   56: aconst_null
    //   57: astore_1
    //   58: aload 4
    //   60: aload_1
    //   61: invokestatic 395	kawa/lib/system:compileFile	(Ljava/lang/CharSequence;Ljava/lang/String;)V
    //   64: getstatic 377	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   67: areturn
    //   68: aload_3
    //   69: invokevirtual 216	java/lang/Object:toString	()Ljava/lang/String;
    //   72: astore_1
    //   73: goto -15 -> 58
    //   76: astore_1
    //   77: new 218	gnu/mapping/WrongType
    //   80: dup
    //   81: aload_1
    //   82: ldc 54
    //   84: iconst_1
    //   85: aload_2
    //   86: invokespecial 222	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   89: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	this	system
    //   0	90	1	paramModuleMethod	ModuleMethod
    //   0	90	2	paramObject1	Object
    //   0	90	3	paramObject2	Object
    //   50	9	4	localCharSequence	CharSequence
    // Exception table:
    //   from	to	target	type
    //   46	52	76	java/lang/ClassCastException
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    if (paramModuleMethod.selector == 9) {}
    try
    {
      paramModuleMethod = (Procedure)paramObject2;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "catch", 2, paramObject2);
    }
    try
    {
      paramObject2 = (Procedure)paramObject3;
      return jdMethod_catch(paramObject1, paramModuleMethod, (Procedure)paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "catch", 3, paramObject3);
    }
    return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 10)
    {
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    }
    return super.match0(paramModuleMethod, paramCallContext);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 7: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 6: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 5: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 4: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 3: 
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
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    case 8: 
      if ((paramObject1 instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786431;
    }
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 9)
    {
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Procedure)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      if (!(paramObject3 instanceof Procedure)) {
        return -786429;
      }
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
    if (IsEqual.apply(System.getProperty("file.separator"), "/")) {}
    for (paramCallContext = tokenize$Mnstring$Mnusing$Mnshell;; paramCallContext = tokenize$Mnstring$Mnto$Mnstring$Mnarray)
    {
      command$Mnparse = paramCallContext;
      return;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\system.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */