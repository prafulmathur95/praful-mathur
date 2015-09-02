package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.IsEqual;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;
import kawa.standard.call_with_values;

public class srfi37
  extends ModuleBody
{
  public static final srfi37 $instance;
  static final IntNum Lit0;
  static final Char Lit1;
  static final SimpleSymbol Lit10;
  static final SimpleSymbol Lit11 = (SimpleSymbol)new SimpleSymbol("args-fold").readResolve();
  static final Char Lit2;
  static final IntNum Lit3;
  static final IntNum Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod args$Mnfold;
  public static final ModuleMethod option;
  public static final ModuleMethod option$Mnnames;
  public static final ModuleMethod option$Mnoptional$Mnarg$Qu;
  public static final ModuleMethod option$Mnprocessor;
  public static final ModuleMethod option$Mnrequired$Mnarg$Qu;
  static final Class option$Mntype;
  public static final ModuleMethod option$Qu;
  
  static
  {
    Lit10 = (SimpleSymbol)new SimpleSymbol("option-processor").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("option-optional-arg?").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("option-required-arg?").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("option-names").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("option").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("option?").readResolve();
    Lit4 = IntNum.make(0);
    Lit3 = IntNum.make(3);
    Lit2 = Char.make(61);
    Lit1 = Char.make(45);
    Lit0 = IntNum.make(1);
    $instance = new srfi37();
    option$Mntype = option.Mntype.class;
    srfi37 localsrfi37 = $instance;
    option$Qu = new ModuleMethod(localsrfi37, 25, Lit5, 4097);
    option = new ModuleMethod(localsrfi37, 26, Lit6, 16388);
    option$Mnnames = new ModuleMethod(localsrfi37, 27, Lit7, 4097);
    option$Mnrequired$Mnarg$Qu = new ModuleMethod(localsrfi37, 28, Lit8, 4097);
    option$Mnoptional$Mnarg$Qu = new ModuleMethod(localsrfi37, 29, Lit9, 4097);
    option$Mnprocessor = new ModuleMethod(localsrfi37, 30, Lit10, 4097);
    args$Mnfold = new ModuleMethod(localsrfi37, 31, Lit11, 61444);
    $instance.run();
  }
  
  public srfi37()
  {
    ModuleInfo.register(this);
  }
  
  public static Object argsFold$V(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object[] paramArrayOfObject)
  {
    frame localframe = new frame();
    localframe.options = paramObject2;
    localframe.unrecognized$Mnoption$Mnproc = paramObject3;
    localframe.operand$Mnproc = paramObject4;
    return localframe.lambda5scanArgs(paramObject1, LList.makeList(paramArrayOfObject, 0));
  }
  
  public static boolean isOption(Object paramObject)
  {
    return paramObject instanceof option.Mntype;
  }
  
  public static Object isOptionOptionalArg(option.Mntype paramMntype)
  {
    return paramMntype.optional$Mnarg$Qu;
  }
  
  public static Object isOptionRequiredArg(option.Mntype paramMntype)
  {
    return paramMntype.required$Mnarg$Qu;
  }
  
  public static option.Mntype option(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    option.Mntype localMntype = new option.Mntype();
    localMntype.names = paramObject1;
    localMntype.required$Mnarg$Qu = paramObject2;
    localMntype.optional$Mnarg$Qu = paramObject3;
    localMntype.processor = paramObject4;
    return localMntype;
  }
  
  public static Object optionNames(option.Mntype paramMntype)
  {
    return paramMntype.names;
  }
  
  public static Object optionProcessor(option.Mntype paramMntype)
  {
    return paramMntype.processor;
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 26: 
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 25: 
      if (isOption(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    try
    {
      paramModuleMethod = (option.Mntype)paramObject;
      return optionNames(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (option.Mntype)paramObject;
        return isOptionRequiredArg(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "option-required-arg?", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (option.Mntype)paramObject;
        return isOptionOptionalArg(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "option-optional-arg?", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (option.Mntype)paramObject;
        return optionProcessor(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "option-processor", 1, paramObject);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "option-names", 1, paramObject);
    }
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    if (paramModuleMethod.selector == 26) {
      return option(paramObject1, paramObject2, paramObject3, paramObject4);
    }
    return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    if (paramModuleMethod.selector == 31)
    {
      paramModuleMethod = paramArrayOfObject[0];
      Object localObject1 = paramArrayOfObject[1];
      Object localObject2 = paramArrayOfObject[2];
      Object localObject3 = paramArrayOfObject[3];
      int i = paramArrayOfObject.length - 4;
      Object[] arrayOfObject = new Object[i];
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          return argsFold$V(paramModuleMethod, localObject1, localObject2, localObject3, arrayOfObject);
        }
        arrayOfObject[i] = paramArrayOfObject[(i + 4)];
      }
    }
    return super.applyN(paramModuleMethod, paramArrayOfObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    case 26: 
    default: 
      i = super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 30: 
    case 29: 
    case 28: 
    case 27: 
      do
      {
        do
        {
          do
          {
            do
            {
              return i;
            } while (!(paramObject instanceof option.Mntype));
            paramCallContext.value1 = paramObject;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 1;
            return 0;
          } while (!(paramObject instanceof option.Mntype));
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } while (!(paramObject instanceof option.Mntype));
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      } while (!(paramObject instanceof option.Mntype));
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
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 26)
    {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 4;
      return 0;
    }
    return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 31)
    {
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    }
    return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
  
  public class frame
    extends ModuleBody
  {
    Object operand$Mnproc;
    Object options;
    Object unrecognized$Mnoption$Mnproc;
    
    public static Object lambda1find(Object paramObject1, Object paramObject2)
    {
      if (lists.isNull(paramObject1)) {
        return Boolean.FALSE;
      }
      if (Scheme.applyToArgs.apply2(paramObject2, lists.car.apply1(paramObject1)) != Boolean.FALSE) {
        return lists.car.apply1(paramObject1);
      }
      return lambda1find(lists.cdr.apply1(paramObject1), paramObject2);
    }
    
    public Object lambda2findOption(Object paramObject)
    {
      srfi37.frame0 localframe0 = new srfi37.frame0();
      localframe0.staticLink = this;
      localframe0.name = paramObject;
      return lambda1find(this.options, localframe0.lambda$Fn1);
    }
    
    /* Error */
    public Object lambda3scanShortOptions(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    {
      // Byte code:
      //   0: new 12	gnu/kawa/slib/srfi37$frame1
      //   3: dup
      //   4: invokespecial 95	gnu/kawa/slib/srfi37$frame1:<init>	()V
      //   7: astore 5
      //   9: aload 5
      //   11: aload_0
      //   12: putfield 96	gnu/kawa/slib/srfi37$frame1:staticLink	Lgnu/kawa/slib/srfi37$frame;
      //   15: aload 5
      //   17: aload_1
      //   18: putfield 99	gnu/kawa/slib/srfi37$frame1:index	Ljava/lang/Object;
      //   21: aload 5
      //   23: aload_2
      //   24: putfield 102	gnu/kawa/slib/srfi37$frame1:shorts	Ljava/lang/Object;
      //   27: aload 5
      //   29: aload_3
      //   30: putfield 105	gnu/kawa/slib/srfi37$frame1:args	Ljava/lang/Object;
      //   33: aload 5
      //   35: aload 4
      //   37: putfield 108	gnu/kawa/slib/srfi37$frame1:seeds	Ljava/lang/Object;
      //   40: getstatic 112	kawa/standard/Scheme:numEqu	Lgnu/kawa/functions/NumberCompare;
      //   43: astore_2
      //   44: aload 5
      //   46: getfield 99	gnu/kawa/slib/srfi37$frame1:index	Ljava/lang/Object;
      //   49: astore_3
      //   50: aload 5
      //   52: getfield 102	gnu/kawa/slib/srfi37$frame1:shorts	Ljava/lang/Object;
      //   55: astore_1
      //   56: aload_1
      //   57: checkcast 114	java/lang/CharSequence
      //   60: astore 4
      //   62: aload_2
      //   63: aload_3
      //   64: aload 4
      //   66: invokestatic 120	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
      //   69: invokestatic 126	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   72: invokevirtual 70	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   75: getstatic 51	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   78: if_acmpeq +18 -> 96
      //   81: aload_0
      //   82: aload 5
      //   84: getfield 105	gnu/kawa/slib/srfi37$frame1:args	Ljava/lang/Object;
      //   87: aload 5
      //   89: getfield 108	gnu/kawa/slib/srfi37$frame1:seeds	Ljava/lang/Object;
      //   92: invokevirtual 129	gnu/kawa/slib/srfi37$frame:lambda5scanArgs	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   95: areturn
      //   96: aload 5
      //   98: getfield 102	gnu/kawa/slib/srfi37$frame1:shorts	Ljava/lang/Object;
      //   101: astore_1
      //   102: aload_1
      //   103: checkcast 114	java/lang/CharSequence
      //   106: astore_2
      //   107: aload 5
      //   109: getfield 99	gnu/kawa/slib/srfi37$frame1:index	Ljava/lang/Object;
      //   112: astore_1
      //   113: aload_1
      //   114: checkcast 131	java/lang/Number
      //   117: invokevirtual 135	java/lang/Number:intValue	()I
      //   120: istore 6
      //   122: aload 5
      //   124: aload_2
      //   125: iload 6
      //   127: invokestatic 139	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
      //   130: putfield 142	gnu/kawa/slib/srfi37$frame1:name	C
      //   133: aload_0
      //   134: aload 5
      //   136: getfield 142	gnu/kawa/slib/srfi37$frame1:name	C
      //   139: invokestatic 148	gnu/text/Char:make	(I)Lgnu/text/Char;
      //   142: invokevirtual 150	gnu/kawa/slib/srfi37$frame:lambda2findOption	(Ljava/lang/Object;)Ljava/lang/Object;
      //   145: astore_1
      //   146: aload_1
      //   147: getstatic 51	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   150: if_acmpeq +112 -> 262
      //   153: aload 5
      //   155: aload_1
      //   156: putfield 153	gnu/kawa/slib/srfi37$frame1:option	Ljava/lang/Object;
      //   159: getstatic 156	kawa/standard/Scheme:numLss	Lgnu/kawa/functions/NumberCompare;
      //   162: astore_2
      //   163: getstatic 162	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
      //   166: aload 5
      //   168: getfield 99	gnu/kawa/slib/srfi37$frame1:index	Ljava/lang/Object;
      //   171: getstatic 166	gnu/kawa/slib/srfi37:Lit0	Lgnu/math/IntNum;
      //   174: invokevirtual 70	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   177: astore_3
      //   178: aload 5
      //   180: getfield 102	gnu/kawa/slib/srfi37$frame1:shorts	Ljava/lang/Object;
      //   183: astore_1
      //   184: aload_1
      //   185: checkcast 114	java/lang/CharSequence
      //   188: astore 4
      //   190: aload_2
      //   191: aload_3
      //   192: aload 4
      //   194: invokestatic 120	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
      //   197: invokestatic 126	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   200: invokevirtual 70	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   203: astore_1
      //   204: aload_1
      //   205: checkcast 47	java/lang/Boolean
      //   208: invokevirtual 170	java/lang/Boolean:booleanValue	()Z
      //   211: istore 7
      //   213: iload 7
      //   215: ifeq +144 -> 359
      //   218: aload 5
      //   220: getfield 153	gnu/kawa/slib/srfi37$frame1:option	Ljava/lang/Object;
      //   223: astore_1
      //   224: aload_1
      //   225: checkcast 172	gnu/kawa/slib/option$Mntype
      //   228: astore_2
      //   229: aload_2
      //   230: invokestatic 176	gnu/kawa/slib/srfi37:isOptionRequiredArg	(Lgnu/kawa/slib/option$Mntype;)Ljava/lang/Object;
      //   233: astore_1
      //   234: aload_1
      //   235: getstatic 51	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   238: if_acmpeq +52 -> 290
      //   241: aload_1
      //   242: getstatic 51	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   245: if_acmpeq +66 -> 311
      //   248: aload 5
      //   250: getfield 179	gnu/kawa/slib/srfi37$frame1:lambda$Fn3	Lgnu/expr/ModuleMethod;
      //   253: aload 5
      //   255: getfield 182	gnu/kawa/slib/srfi37$frame1:lambda$Fn4	Lgnu/expr/ModuleMethod;
      //   258: invokestatic 188	kawa/standard/call_with_values:callWithValues	(Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;)Ljava/lang/Object;
      //   261: areturn
      //   262: aload 5
      //   264: getfield 142	gnu/kawa/slib/srfi37$frame1:name	C
      //   267: invokestatic 148	gnu/text/Char:make	(I)Lgnu/text/Char;
      //   270: invokestatic 194	gnu/lists/LList:list1	(Ljava/lang/Object;)Lgnu/lists/Pair;
      //   273: getstatic 51	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   276: getstatic 51	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   279: aload_0
      //   280: getfield 196	gnu/kawa/slib/srfi37$frame:unrecognized$Mnoption$Mnproc	Ljava/lang/Object;
      //   283: invokestatic 199	gnu/kawa/slib/srfi37:option	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lgnu/kawa/slib/option$Mntype;
      //   286: astore_1
      //   287: goto -134 -> 153
      //   290: aload 5
      //   292: getfield 153	gnu/kawa/slib/srfi37$frame1:option	Ljava/lang/Object;
      //   295: astore_1
      //   296: aload_1
      //   297: checkcast 172	gnu/kawa/slib/option$Mntype
      //   300: astore_2
      //   301: aload_2
      //   302: invokestatic 202	gnu/kawa/slib/srfi37:isOptionOptionalArg	(Lgnu/kawa/slib/option$Mntype;)Ljava/lang/Object;
      //   305: getstatic 51	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   308: if_acmpne -60 -> 248
      //   311: aload 5
      //   313: getfield 153	gnu/kawa/slib/srfi37$frame1:option	Ljava/lang/Object;
      //   316: astore_1
      //   317: aload_1
      //   318: checkcast 172	gnu/kawa/slib/option$Mntype
      //   321: astore_2
      //   322: aload_2
      //   323: invokestatic 176	gnu/kawa/slib/srfi37:isOptionRequiredArg	(Lgnu/kawa/slib/option$Mntype;)Ljava/lang/Object;
      //   326: astore_1
      //   327: aload_1
      //   328: getstatic 51	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   331: if_acmpeq +36 -> 367
      //   334: aload 5
      //   336: getfield 105	gnu/kawa/slib/srfi37$frame1:args	Ljava/lang/Object;
      //   339: invokestatic 205	kawa/lib/lists:isPair	(Ljava/lang/Object;)Z
      //   342: ifeq +32 -> 374
      //   345: aload 5
      //   347: getfield 208	gnu/kawa/slib/srfi37$frame1:lambda$Fn5	Lgnu/expr/ModuleMethod;
      //   350: aload 5
      //   352: getfield 211	gnu/kawa/slib/srfi37$frame1:lambda$Fn6	Lgnu/expr/ModuleMethod;
      //   355: invokestatic 188	kawa/standard/call_with_values:callWithValues	(Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;)Ljava/lang/Object;
      //   358: areturn
      //   359: iload 7
      //   361: ifeq -50 -> 311
      //   364: goto -116 -> 248
      //   367: aload_1
      //   368: getstatic 51	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   371: if_acmpne -26 -> 345
      //   374: aload 5
      //   376: getfield 214	gnu/kawa/slib/srfi37$frame1:lambda$Fn7	Lgnu/expr/ModuleMethod;
      //   379: aload 5
      //   381: getfield 217	gnu/kawa/slib/srfi37$frame1:lambda$Fn8	Lgnu/expr/ModuleMethod;
      //   384: invokestatic 188	kawa/standard/call_with_values:callWithValues	(Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;)Ljava/lang/Object;
      //   387: areturn
      //   388: astore_2
      //   389: new 219	gnu/mapping/WrongType
      //   392: dup
      //   393: aload_2
      //   394: ldc -35
      //   396: iconst_1
      //   397: aload_1
      //   398: invokespecial 224	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   401: athrow
      //   402: astore_2
      //   403: new 219	gnu/mapping/WrongType
      //   406: dup
      //   407: aload_2
      //   408: ldc -30
      //   410: iconst_1
      //   411: aload_1
      //   412: invokespecial 224	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   415: athrow
      //   416: astore_2
      //   417: new 219	gnu/mapping/WrongType
      //   420: dup
      //   421: aload_2
      //   422: ldc -30
      //   424: iconst_2
      //   425: aload_1
      //   426: invokespecial 224	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   429: athrow
      //   430: astore_2
      //   431: new 219	gnu/mapping/WrongType
      //   434: dup
      //   435: aload_2
      //   436: ldc -35
      //   438: iconst_1
      //   439: aload_1
      //   440: invokespecial 224	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   443: athrow
      //   444: astore_2
      //   445: new 219	gnu/mapping/WrongType
      //   448: dup
      //   449: aload_2
      //   450: ldc -28
      //   452: bipush -2
      //   454: aload_1
      //   455: invokespecial 224	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   458: athrow
      //   459: astore_2
      //   460: new 219	gnu/mapping/WrongType
      //   463: dup
      //   464: aload_2
      //   465: ldc -26
      //   467: iconst_0
      //   468: aload_1
      //   469: invokespecial 224	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   472: athrow
      //   473: astore_2
      //   474: new 219	gnu/mapping/WrongType
      //   477: dup
      //   478: aload_2
      //   479: ldc -24
      //   481: iconst_0
      //   482: aload_1
      //   483: invokespecial 224	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   486: athrow
      //   487: astore_2
      //   488: new 219	gnu/mapping/WrongType
      //   491: dup
      //   492: aload_2
      //   493: ldc -26
      //   495: iconst_0
      //   496: aload_1
      //   497: invokespecial 224	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   500: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	501	0	this	frame
      //   0	501	1	paramObject1	Object
      //   0	501	2	paramObject2	Object
      //   0	501	3	paramObject3	Object
      //   0	501	4	paramObject4	Object
      //   7	373	5	localframe1	srfi37.frame1
      //   120	6	6	i	int
      //   211	149	7	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   56	62	388	java/lang/ClassCastException
      //   102	107	402	java/lang/ClassCastException
      //   113	122	416	java/lang/ClassCastException
      //   184	190	430	java/lang/ClassCastException
      //   204	213	444	java/lang/ClassCastException
      //   224	229	459	java/lang/ClassCastException
      //   296	301	473	java/lang/ClassCastException
      //   317	322	487	java/lang/ClassCastException
    }
    
    public Object lambda4scanOperands(Object paramObject1, Object paramObject2)
    {
      srfi37.frame2 localframe2 = new srfi37.frame2();
      localframe2.staticLink = this;
      localframe2.operands = paramObject1;
      localframe2.seeds = paramObject2;
      if (lists.isNull(localframe2.operands)) {
        return Scheme.apply.apply2(misc.values, localframe2.seeds);
      }
      return call_with_values.callWithValues(localframe2.lambda$Fn9, localframe2.lambda$Fn10);
    }
    
    public Object lambda5scanArgs(Object paramObject1, Object paramObject2)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }
  }
  
  public class frame0
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn1;
    final ModuleMethod lambda$Fn2;
    Object name;
    srfi37.frame staticLink;
    
    public frame0()
    {
      this$1 = new ModuleMethod(this, 1, null, 4097);
      this$1.setProperty("source-location", "srfi37.scm:75");
      this.lambda$Fn2 = this$1;
      this$1 = new ModuleMethod(this, 2, null, 4097);
      this$1.setProperty("source-location", "srfi37.scm:72");
      this.lambda$Fn1 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.apply1(paramModuleMethod, paramObject);
      case 1: 
        if (lambda7(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return lambda6(paramObject);
    }
    
    Object lambda6(Object paramObject)
    {
      try
      {
        option.Mntype localMntype = (option.Mntype)paramObject;
        return srfi37.frame.lambda1find(srfi37.optionNames(localMntype), this.lambda$Fn2);
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "option-names", 0, paramObject);
      }
    }
    
    boolean lambda7(Object paramObject)
    {
      return IsEqual.apply(this.name, paramObject);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 2: 
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
  }
  
  public class frame1
    extends ModuleBody
  {
    Object args;
    Object index;
    final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, null, 0);
    final ModuleMethod lambda$Fn4 = new ModuleMethod(this, 4, null, 61440);
    final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 5, null, 0);
    final ModuleMethod lambda$Fn6 = new ModuleMethod(this, 6, null, 61440);
    final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 7, null, 0);
    final ModuleMethod lambda$Fn8 = new ModuleMethod(this, 8, null, 61440);
    char name;
    Object option;
    Object seeds;
    Object shorts;
    srfi37.frame staticLink;
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      switch (paramModuleMethod.selector)
      {
      case 4: 
      case 6: 
      default: 
        return super.apply0(paramModuleMethod);
      case 3: 
        return lambda8();
      case 5: 
        return lambda10();
      }
      return lambda12();
    }
    
    public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
    {
      switch (paramModuleMethod.selector)
      {
      case 5: 
      case 7: 
      default: 
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 4: 
        return lambda9$V(paramArrayOfObject);
      case 6: 
        return lambda11$V(paramArrayOfObject);
      }
      return lambda13$V(paramArrayOfObject);
    }
    
    Object lambda10()
    {
      Apply localApply = Scheme.apply;
      Object localObject = this.option;
      try
      {
        option.Mntype localMntype = (option.Mntype)localObject;
        return localApply.applyN(new Object[] { srfi37.optionProcessor(localMntype), this.option, Char.make(this.name), lists.car.apply1(this.args), this.seeds });
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "option-processor", 0, localObject);
      }
    }
    
    Object lambda11$V(Object[] paramArrayOfObject)
    {
      paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
      return this.staticLink.lambda5scanArgs(lists.cdr.apply1(this.args), paramArrayOfObject);
    }
    
    Object lambda12()
    {
      Apply localApply = Scheme.apply;
      Object localObject = this.option;
      try
      {
        option.Mntype localMntype = (option.Mntype)localObject;
        return localApply.applyN(new Object[] { srfi37.optionProcessor(localMntype), this.option, Char.make(this.name), Boolean.FALSE, this.seeds });
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "option-processor", 0, localObject);
      }
    }
    
    Object lambda13$V(Object[] paramArrayOfObject)
    {
      paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
      return this.staticLink.lambda3scanShortOptions(AddOp.$Pl.apply2(this.index, srfi37.Lit0), this.shorts, this.args, paramArrayOfObject);
    }
    
    /* Error */
    Object lambda8()
    {
      // Byte code:
      //   0: getstatic 89	kawa/standard/Scheme:apply	Lgnu/kawa/functions/Apply;
      //   3: astore_1
      //   4: aload_0
      //   5: getfield 91	gnu/kawa/slib/srfi37$frame1:option	Ljava/lang/Object;
      //   8: astore_2
      //   9: aload_2
      //   10: checkcast 93	gnu/kawa/slib/option$Mntype
      //   13: astore_3
      //   14: aload_3
      //   15: invokestatic 99	gnu/kawa/slib/srfi37:optionProcessor	(Lgnu/kawa/slib/option$Mntype;)Ljava/lang/Object;
      //   18: astore_2
      //   19: aload_0
      //   20: getfield 91	gnu/kawa/slib/srfi37$frame1:option	Ljava/lang/Object;
      //   23: astore_3
      //   24: aload_0
      //   25: getfield 101	gnu/kawa/slib/srfi37$frame1:name	C
      //   28: invokestatic 107	gnu/text/Char:make	(I)Lgnu/text/Char;
      //   31: astore 4
      //   33: aload_0
      //   34: getfield 170	gnu/kawa/slib/srfi37$frame1:shorts	Ljava/lang/Object;
      //   37: astore 6
      //   39: aload 6
      //   41: checkcast 176	java/lang/CharSequence
      //   44: astore 5
      //   46: getstatic 159	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
      //   49: aload_0
      //   50: getfield 161	gnu/kawa/slib/srfi37$frame1:index	Ljava/lang/Object;
      //   53: getstatic 165	gnu/kawa/slib/srfi37:Lit0	Lgnu/math/IntNum;
      //   56: invokevirtual 168	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   59: astore 6
      //   61: aload 6
      //   63: checkcast 178	java/lang/Number
      //   66: invokevirtual 182	java/lang/Number:intValue	()I
      //   69: istore 8
      //   71: aload_0
      //   72: getfield 170	gnu/kawa/slib/srfi37$frame1:shorts	Ljava/lang/Object;
      //   75: astore 6
      //   77: aload 6
      //   79: checkcast 176	java/lang/CharSequence
      //   82: astore 7
      //   84: aload_1
      //   85: iconst_5
      //   86: anewarray 95	java/lang/Object
      //   89: dup
      //   90: iconst_0
      //   91: aload_2
      //   92: aastore
      //   93: dup
      //   94: iconst_1
      //   95: aload_3
      //   96: aastore
      //   97: dup
      //   98: iconst_2
      //   99: aload 4
      //   101: aastore
      //   102: dup
      //   103: iconst_3
      //   104: aload 5
      //   106: iload 8
      //   108: aload 7
      //   110: invokestatic 188	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
      //   113: invokestatic 192	kawa/lib/strings:substring	(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   116: aastore
      //   117: dup
      //   118: iconst_4
      //   119: aload_0
      //   120: getfield 123	gnu/kawa/slib/srfi37$frame1:seeds	Ljava/lang/Object;
      //   123: aastore
      //   124: invokevirtual 125	gnu/mapping/Procedure:applyN	([Ljava/lang/Object;)Ljava/lang/Object;
      //   127: areturn
      //   128: astore_1
      //   129: new 127	gnu/mapping/WrongType
      //   132: dup
      //   133: aload_1
      //   134: ldc -127
      //   136: iconst_0
      //   137: aload_2
      //   138: invokespecial 132	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   141: athrow
      //   142: astore_1
      //   143: new 127	gnu/mapping/WrongType
      //   146: dup
      //   147: aload_1
      //   148: ldc -63
      //   150: iconst_1
      //   151: aload 6
      //   153: invokespecial 132	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   156: athrow
      //   157: astore_1
      //   158: new 127	gnu/mapping/WrongType
      //   161: dup
      //   162: aload_1
      //   163: ldc -63
      //   165: iconst_2
      //   166: aload 6
      //   168: invokespecial 132	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   171: athrow
      //   172: astore_1
      //   173: new 127	gnu/mapping/WrongType
      //   176: dup
      //   177: aload_1
      //   178: ldc -61
      //   180: iconst_1
      //   181: aload 6
      //   183: invokespecial 132	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   186: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	187	0	this	frame1
      //   3	82	1	localApply	Apply
      //   128	6	1	localClassCastException1	ClassCastException
      //   142	6	1	localClassCastException2	ClassCastException
      //   157	6	1	localClassCastException3	ClassCastException
      //   172	6	1	localClassCastException4	ClassCastException
      //   8	130	2	localObject1	Object
      //   13	83	3	localObject2	Object
      //   31	69	4	localChar	Char
      //   44	61	5	localCharSequence1	CharSequence
      //   37	145	6	localObject3	Object
      //   82	27	7	localCharSequence2	CharSequence
      //   69	38	8	i	int
      // Exception table:
      //   from	to	target	type
      //   9	14	128	java/lang/ClassCastException
      //   39	46	142	java/lang/ClassCastException
      //   61	71	157	java/lang/ClassCastException
      //   77	84	172	java/lang/ClassCastException
    }
    
    Object lambda9$V(Object[] paramArrayOfObject)
    {
      paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
      return this.staticLink.lambda5scanArgs(this.args, paramArrayOfObject);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      switch (paramModuleMethod.selector)
      {
      case 4: 
      case 6: 
      default: 
        return super.match0(paramModuleMethod, paramCallContext);
      case 7: 
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 5: 
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    }
    
    public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
    {
      switch (paramModuleMethod.selector)
      {
      case 5: 
      case 7: 
      default: 
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 8: 
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 6: 
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      }
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    }
  }
  
  public class frame2
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn10 = new ModuleMethod(this, 10, null, 61440);
    final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 9, null, 0);
    Object operands;
    Object seeds;
    srfi37.frame staticLink;
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 9) {
        return lambda14();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
    {
      if (paramModuleMethod.selector == 10) {
        return lambda15$V(paramArrayOfObject);
      }
      return super.applyN(paramModuleMethod, paramArrayOfObject);
    }
    
    Object lambda14()
    {
      return Scheme.apply.apply3(this.staticLink.operand$Mnproc, lists.car.apply1(this.operands), this.seeds);
    }
    
    Object lambda15$V(Object[] paramArrayOfObject)
    {
      paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
      return this.staticLink.lambda4scanOperands(lists.cdr.apply1(this.operands), paramArrayOfObject);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 9)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 10)
      {
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      }
      return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
    }
  }
  
  public class frame3
    extends ModuleBody
  {
    Object arg;
    Object args;
    final ModuleMethod lambda$Fn11 = new ModuleMethod(this, 17, null, 0);
    final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 18, null, 4097);
    final ModuleMethod lambda$Fn19 = new ModuleMethod(this, 19, null, 0);
    final ModuleMethod lambda$Fn20 = new ModuleMethod(this, 20, null, 61440);
    final ModuleMethod lambda$Fn21 = new ModuleMethod(this, 21, null, 0);
    final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 22, null, 61440);
    final ModuleMethod lambda$Fn23 = new ModuleMethod(this, 23, null, 0);
    final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 24, null, 61440);
    CharSequence name;
    Object option;
    Object seeds;
    srfi37.frame staticLink;
    Object temp;
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      switch (paramModuleMethod.selector)
      {
      case 18: 
      case 20: 
      case 22: 
      default: 
        return super.apply0(paramModuleMethod);
      case 17: 
        return lambda16();
      case 19: 
        return lambda24();
      case 21: 
        return lambda26();
      }
      return lambda28();
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 18) {
        return lambda17(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
    {
      switch (paramModuleMethod.selector)
      {
      case 21: 
      case 23: 
      default: 
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 20: 
        return lambda25$V(paramArrayOfObject);
      case 22: 
        return lambda27$V(paramArrayOfObject);
      }
      return lambda29$V(paramArrayOfObject);
    }
    
    /* Error */
    CharSequence lambda16()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 112	gnu/kawa/slib/srfi37$frame3:arg	Ljava/lang/Object;
      //   4: astore_1
      //   5: aload_1
      //   6: checkcast 114	java/lang/CharSequence
      //   9: astore_2
      //   10: aload_0
      //   11: getfield 116	gnu/kawa/slib/srfi37$frame3:temp	Ljava/lang/Object;
      //   14: astore_1
      //   15: aload_1
      //   16: checkcast 118	java/lang/Number
      //   19: invokevirtual 122	java/lang/Number:intValue	()I
      //   22: istore_3
      //   23: aload_2
      //   24: iconst_2
      //   25: iload_3
      //   26: invokestatic 128	kawa/lib/strings:substring	(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   29: areturn
      //   30: astore_2
      //   31: new 130	gnu/mapping/WrongType
      //   34: dup
      //   35: aload_2
      //   36: ldc -125
      //   38: iconst_1
      //   39: aload_1
      //   40: invokespecial 134	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   43: athrow
      //   44: astore_2
      //   45: new 130	gnu/mapping/WrongType
      //   48: dup
      //   49: aload_2
      //   50: ldc -125
      //   52: iconst_3
      //   53: aload_1
      //   54: invokespecial 134	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   57: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	58	0	this	frame3
      //   4	50	1	localObject	Object
      //   9	15	2	localCharSequence	CharSequence
      //   30	6	2	localClassCastException1	ClassCastException
      //   44	6	2	localClassCastException2	ClassCastException
      //   22	4	3	i	int
      // Exception table:
      //   from	to	target	type
      //   5	10	30	java/lang/ClassCastException
      //   15	23	44	java/lang/ClassCastException
    }
    
    Object lambda17(Object paramObject)
    {
      srfi37.frame4 localframe4 = new srfi37.frame4();
      localframe4.staticLink = this;
      localframe4.x = paramObject;
      return call_with_values.callWithValues(localframe4.lambda$Fn13, localframe4.lambda$Fn14);
    }
    
    Object lambda24()
    {
      Apply localApply = Scheme.apply;
      Object localObject = this.option;
      try
      {
        option.Mntype localMntype = (option.Mntype)localObject;
        return localApply.applyN(new Object[] { srfi37.optionProcessor(localMntype), this.option, this.name, lists.car.apply1(this.args), this.seeds });
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "option-processor", 0, localObject);
      }
    }
    
    Object lambda25$V(Object[] paramArrayOfObject)
    {
      paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
      return this.staticLink.lambda5scanArgs(lists.cdr.apply1(this.args), paramArrayOfObject);
    }
    
    Object lambda26()
    {
      Apply localApply = Scheme.apply;
      Object localObject = this.option;
      try
      {
        option.Mntype localMntype = (option.Mntype)localObject;
        return localApply.applyN(new Object[] { srfi37.optionProcessor(localMntype), this.option, this.name, Boolean.FALSE, this.seeds });
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "option-processor", 0, localObject);
      }
    }
    
    Object lambda27$V(Object[] paramArrayOfObject)
    {
      paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
      return this.staticLink.lambda5scanArgs(this.args, paramArrayOfObject);
    }
    
    Object lambda28()
    {
      return Scheme.apply.apply3(this.staticLink.operand$Mnproc, this.arg, this.seeds);
    }
    
    Object lambda29$V(Object[] paramArrayOfObject)
    {
      paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
      return this.staticLink.lambda5scanArgs(this.args, paramArrayOfObject);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      switch (paramModuleMethod.selector)
      {
      case 18: 
      case 20: 
      case 22: 
      default: 
        return super.match0(paramModuleMethod, paramCallContext);
      case 23: 
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 21: 
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 19: 
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 18)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
    
    public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
    {
      switch (paramModuleMethod.selector)
      {
      case 21: 
      case 23: 
      default: 
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 24: 
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 22: 
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      }
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    }
  }
  
  public class frame4
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn13 = new ModuleMethod(this, 15, null, 0);
    final ModuleMethod lambda$Fn14 = new ModuleMethod(this, 16, null, 4097);
    srfi37.frame3 staticLink;
    Object x;
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 15) {
        return lambda18();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 16) {
        return lambda19(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    /* Error */
    CharSequence lambda18()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 64	gnu/kawa/slib/srfi37$frame4:staticLink	Lgnu/kawa/slib/srfi37$frame3;
      //   4: getfield 67	gnu/kawa/slib/srfi37$frame3:arg	Ljava/lang/Object;
      //   7: astore_2
      //   8: aload_2
      //   9: checkcast 69	java/lang/CharSequence
      //   12: astore_1
      //   13: getstatic 75	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
      //   16: aload_0
      //   17: getfield 64	gnu/kawa/slib/srfi37$frame4:staticLink	Lgnu/kawa/slib/srfi37$frame3;
      //   20: getfield 78	gnu/kawa/slib/srfi37$frame3:temp	Ljava/lang/Object;
      //   23: getstatic 82	gnu/kawa/slib/srfi37:Lit0	Lgnu/math/IntNum;
      //   26: invokevirtual 88	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   29: astore_2
      //   30: aload_2
      //   31: checkcast 90	java/lang/Number
      //   34: invokevirtual 94	java/lang/Number:intValue	()I
      //   37: istore 4
      //   39: aload_0
      //   40: getfield 64	gnu/kawa/slib/srfi37$frame4:staticLink	Lgnu/kawa/slib/srfi37$frame3;
      //   43: getfield 67	gnu/kawa/slib/srfi37$frame3:arg	Ljava/lang/Object;
      //   46: astore_2
      //   47: aload_2
      //   48: checkcast 69	java/lang/CharSequence
      //   51: astore_3
      //   52: aload_1
      //   53: iload 4
      //   55: aload_3
      //   56: invokestatic 100	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
      //   59: invokestatic 104	kawa/lib/strings:substring	(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   62: areturn
      //   63: astore_1
      //   64: new 106	gnu/mapping/WrongType
      //   67: dup
      //   68: aload_1
      //   69: ldc 107
      //   71: iconst_1
      //   72: aload_2
      //   73: invokespecial 110	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   76: athrow
      //   77: astore_1
      //   78: new 106	gnu/mapping/WrongType
      //   81: dup
      //   82: aload_1
      //   83: ldc 107
      //   85: iconst_2
      //   86: aload_2
      //   87: invokespecial 110	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   90: athrow
      //   91: astore_1
      //   92: new 106	gnu/mapping/WrongType
      //   95: dup
      //   96: aload_1
      //   97: ldc 112
      //   99: iconst_1
      //   100: aload_2
      //   101: invokespecial 110	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   104: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	105	0	this	frame4
      //   12	41	1	localCharSequence1	CharSequence
      //   63	6	1	localClassCastException1	ClassCastException
      //   77	6	1	localClassCastException2	ClassCastException
      //   91	6	1	localClassCastException3	ClassCastException
      //   7	94	2	localObject	Object
      //   51	5	3	localCharSequence2	CharSequence
      //   37	17	4	i	int
      // Exception table:
      //   from	to	target	type
      //   8	13	63	java/lang/ClassCastException
      //   30	39	77	java/lang/ClassCastException
      //   47	52	91	java/lang/ClassCastException
    }
    
    Object lambda19(Object paramObject)
    {
      srfi37.frame5 localframe5 = new srfi37.frame5();
      localframe5.staticLink = this;
      localframe5.x = paramObject;
      return call_with_values.callWithValues(localframe5.lambda$Fn15, localframe5.lambda$Fn16);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 15)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 16)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame5
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 13, null, 0);
    final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 14, null, 4097);
    srfi37.frame4 staticLink;
    Object x;
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 13) {
        return lambda20();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 14) {
        return lambda21(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda20()
    {
      Object localObject = this.staticLink.staticLink.staticLink.lambda2findOption(this.staticLink.x);
      if (localObject != Boolean.FALSE) {
        return localObject;
      }
      return srfi37.option(LList.list1(this.staticLink.x), Boolean.TRUE, Boolean.FALSE, this.staticLink.staticLink.staticLink.unrecognized$Mnoption$Mnproc);
    }
    
    Object lambda21(Object paramObject)
    {
      srfi37.frame6 localframe6 = new srfi37.frame6();
      localframe6.staticLink = this;
      localframe6.x = paramObject;
      return call_with_values.callWithValues(localframe6.lambda$Fn17, localframe6.lambda$Fn18);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 13)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 14)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame6
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
    final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, 61440);
    srfi37.frame5 staticLink;
    Object x;
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 11) {
        return lambda22();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
    {
      if (paramModuleMethod.selector == 12) {
        return lambda23$V(paramArrayOfObject);
      }
      return super.applyN(paramModuleMethod, paramArrayOfObject);
    }
    
    Object lambda22()
    {
      Apply localApply = Scheme.apply;
      Object localObject = this.x;
      try
      {
        option.Mntype localMntype = (option.Mntype)localObject;
        return localApply.applyN(new Object[] { srfi37.optionProcessor(localMntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "option-processor", 0, localObject);
      }
    }
    
    Object lambda23$V(Object[] paramArrayOfObject)
    {
      paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
      return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, paramArrayOfObject);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 11)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 12)
      {
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      }
      return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\srfi37.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */