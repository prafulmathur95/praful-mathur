package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Format;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;

public class genwrite
  extends ModuleBody
{
  public static final genwrite $instance;
  static final Char Lit0;
  static final IntNum Lit1;
  static final SimpleSymbol Lit10;
  static final SimpleSymbol Lit11;
  static final SimpleSymbol Lit12;
  static final SimpleSymbol Lit13;
  static final SimpleSymbol Lit14;
  static final IntNum Lit15;
  static final IntNum Lit16;
  static final IntNum Lit17;
  static final IntNum Lit18;
  static final IntNum Lit19;
  static final SimpleSymbol Lit2;
  static final SimpleSymbol Lit20;
  static final SimpleSymbol Lit21;
  static final SimpleSymbol Lit22;
  static final SimpleSymbol Lit23;
  static final SimpleSymbol Lit24;
  static final SimpleSymbol Lit25;
  static final SimpleSymbol Lit26;
  static final SimpleSymbol Lit27;
  static final SimpleSymbol Lit28;
  static final SimpleSymbol Lit29;
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit30;
  static final SimpleSymbol Lit31;
  static final SimpleSymbol Lit32;
  static final SimpleSymbol Lit33;
  static final SimpleSymbol Lit34;
  static final SimpleSymbol Lit35 = (SimpleSymbol)new SimpleSymbol("reverse-string-append").readResolve();
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod generic$Mnwrite;
  public static final ModuleMethod reverse$Mnstring$Mnappend;
  
  static
  {
    Lit34 = (SimpleSymbol)new SimpleSymbol("generic-write").readResolve();
    Lit33 = (SimpleSymbol)new SimpleSymbol("unquote-splicing").readResolve();
    Lit32 = (SimpleSymbol)new SimpleSymbol("unquote").readResolve();
    Lit31 = (SimpleSymbol)new SimpleSymbol("quasiquote").readResolve();
    Lit30 = (SimpleSymbol)new SimpleSymbol("quote").readResolve();
    Lit29 = (SimpleSymbol)new SimpleSymbol("pp-DO").readResolve();
    Lit28 = (SimpleSymbol)new SimpleSymbol("pp-BEGIN").readResolve();
    Lit27 = (SimpleSymbol)new SimpleSymbol("pp-LET").readResolve();
    Lit26 = (SimpleSymbol)new SimpleSymbol("pp-AND").readResolve();
    Lit25 = (SimpleSymbol)new SimpleSymbol("pp-CASE").readResolve();
    Lit24 = (SimpleSymbol)new SimpleSymbol("pp-COND").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("pp-IF").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("pp-LAMBDA").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("pp-expr-list").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("pp-expr").readResolve();
    Lit19 = IntNum.make(2);
    Lit18 = IntNum.make(50);
    Lit17 = IntNum.make(1);
    Lit16 = IntNum.make(8);
    Lit15 = IntNum.make(7);
    Lit14 = (SimpleSymbol)new SimpleSymbol("do").readResolve();
    Lit13 = (SimpleSymbol)new SimpleSymbol("begin").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("let").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("or").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("and").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("case").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("cond").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("set!").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("if").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("define").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("letrec").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("let*").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("lambda").readResolve();
    Lit1 = IntNum.make(0);
    Lit0 = Char.make(10);
    $instance = new genwrite();
    genwrite localgenwrite = $instance;
    generic$Mnwrite = new ModuleMethod(localgenwrite, 12, Lit34, 16388);
    reverse$Mnstring$Mnappend = new ModuleMethod(localgenwrite, 13, Lit35, 4097);
    $instance.run();
  }
  
  public genwrite()
  {
    ModuleInfo.register(this);
  }
  
  public static Object genericWrite(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    frame localframe = new frame();
    localframe.display$Qu = paramObject2;
    localframe.width = paramObject3;
    localframe.output = paramObject4;
    if (localframe.width != Boolean.FALSE)
    {
      paramObject2 = strings.makeString(1, Lit0);
      paramObject3 = Lit1;
      paramObject4 = new frame0();
      ((frame0)paramObject4).staticLink = localframe;
      Procedure localProcedure1 = ((frame0)paramObject4).pp$Mnexpr;
      Procedure localProcedure2 = ((frame0)paramObject4).pp$Mnexpr$Mnlist;
      Procedure localProcedure3 = ((frame0)paramObject4).pp$MnLAMBDA;
      Procedure localProcedure4 = ((frame0)paramObject4).pp$MnIF;
      Procedure localProcedure5 = ((frame0)paramObject4).pp$MnCOND;
      Procedure localProcedure6 = ((frame0)paramObject4).pp$MnCASE;
      Procedure localProcedure7 = ((frame0)paramObject4).pp$MnAND;
      Procedure localProcedure8 = ((frame0)paramObject4).pp$MnLET;
      Procedure localProcedure9 = ((frame0)paramObject4).pp$MnBEGIN;
      ((frame0)paramObject4).pp$MnDO = ((frame0)paramObject4).pp$MnDO;
      ((frame0)paramObject4).pp$MnBEGIN = localProcedure9;
      ((frame0)paramObject4).pp$MnLET = localProcedure8;
      ((frame0)paramObject4).pp$MnAND = localProcedure7;
      ((frame0)paramObject4).pp$MnCASE = localProcedure6;
      ((frame0)paramObject4).pp$MnCOND = localProcedure5;
      ((frame0)paramObject4).pp$MnIF = localProcedure4;
      ((frame0)paramObject4).pp$MnLAMBDA = localProcedure3;
      ((frame0)paramObject4).pp$Mnexpr$Mnlist = localProcedure2;
      ((frame0)paramObject4).pp$Mnexpr = localProcedure1;
      return localframe.lambda4out(paramObject2, ((frame0)paramObject4).lambda7pr(paramObject1, paramObject3, Lit1, ((frame0)paramObject4).pp$Mnexpr));
    }
    return localframe.lambda5wr(paramObject1, Lit1);
  }
  
  /* Error */
  public static Object lambda23revStringAppend(Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 314	kawa/lib/lists:isPair	(Ljava/lang/Object;)Z
    //   4: ifeq +180 -> 184
    //   7: getstatic 318	kawa/lib/lists:car	Lgnu/expr/GenericProc;
    //   10: aload_0
    //   11: invokevirtual 324	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   14: astore 4
    //   16: aload 4
    //   18: checkcast 326	java/lang/CharSequence
    //   21: astore_2
    //   22: aload_2
    //   23: invokestatic 330	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
    //   26: istore 7
    //   28: getstatic 333	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   31: aload_0
    //   32: invokevirtual 324	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   35: getstatic 339	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   38: aload_1
    //   39: iload 7
    //   41: invokestatic 345	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   44: invokevirtual 348	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   47: invokestatic 350	gnu/kawa/slib/genwrite:lambda23revStringAppend	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   50: astore_3
    //   51: getstatic 203	gnu/kawa/slib/genwrite:Lit1	Lgnu/math/IntNum;
    //   54: astore_2
    //   55: getstatic 353	gnu/kawa/functions/AddOp:$Mn	Lgnu/kawa/functions/AddOp;
    //   58: astore_0
    //   59: getstatic 353	gnu/kawa/functions/AddOp:$Mn	Lgnu/kawa/functions/AddOp;
    //   62: astore 5
    //   64: aload_3
    //   65: checkcast 326	java/lang/CharSequence
    //   68: astore 6
    //   70: aload_0
    //   71: aload 5
    //   73: aload 6
    //   75: invokestatic 330	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
    //   78: invokestatic 345	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   81: aload_1
    //   82: invokevirtual 348	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   85: iload 7
    //   87: invokestatic 345	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   90: invokevirtual 348	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   93: astore_0
    //   94: aload_2
    //   95: astore_1
    //   96: aload_3
    //   97: astore_2
    //   98: getstatic 359	kawa/standard/Scheme:numLss	Lgnu/kawa/functions/NumberCompare;
    //   101: aload_1
    //   102: iload 7
    //   104: invokestatic 345	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   107: invokevirtual 348	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   110: getstatic 253	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   113: if_acmpeq +86 -> 199
    //   116: aload_3
    //   117: checkcast 361	gnu/lists/CharSeq
    //   120: astore_2
    //   121: aload_0
    //   122: checkcast 363	java/lang/Number
    //   125: invokevirtual 367	java/lang/Number:intValue	()I
    //   128: istore 8
    //   130: aload 4
    //   132: checkcast 326	java/lang/CharSequence
    //   135: astore 5
    //   137: aload_1
    //   138: checkcast 363	java/lang/Number
    //   141: invokevirtual 367	java/lang/Number:intValue	()I
    //   144: istore 9
    //   146: aload_2
    //   147: iload 8
    //   149: aload 5
    //   151: iload 9
    //   153: invokestatic 371	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
    //   156: invokestatic 375	kawa/lib/strings:stringSet$Ex	(Lgnu/lists/CharSeq;IC)V
    //   159: getstatic 339	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   162: aload_1
    //   163: getstatic 145	gnu/kawa/slib/genwrite:Lit17	Lgnu/math/IntNum;
    //   166: invokevirtual 348	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   169: astore_1
    //   170: getstatic 339	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   173: aload_0
    //   174: getstatic 145	gnu/kawa/slib/genwrite:Lit17	Lgnu/math/IntNum;
    //   177: invokevirtual 348	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   180: astore_0
    //   181: goto -85 -> 96
    //   184: aload_1
    //   185: checkcast 363	java/lang/Number
    //   188: invokevirtual 367	java/lang/Number:intValue	()I
    //   191: istore 7
    //   193: iload 7
    //   195: invokestatic 378	kawa/lib/strings:makeString	(I)Ljava/lang/CharSequence;
    //   198: astore_2
    //   199: aload_2
    //   200: areturn
    //   201: astore_0
    //   202: new 380	gnu/mapping/WrongType
    //   205: dup
    //   206: aload_0
    //   207: ldc_w 382
    //   210: iconst_1
    //   211: aload 4
    //   213: invokespecial 385	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   216: athrow
    //   217: astore_0
    //   218: new 380	gnu/mapping/WrongType
    //   221: dup
    //   222: aload_0
    //   223: ldc_w 382
    //   226: iconst_1
    //   227: aload_3
    //   228: invokespecial 385	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   231: athrow
    //   232: astore_0
    //   233: new 380	gnu/mapping/WrongType
    //   236: dup
    //   237: aload_0
    //   238: ldc_w 387
    //   241: iconst_1
    //   242: aload_3
    //   243: invokespecial 385	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   246: athrow
    //   247: astore_1
    //   248: new 380	gnu/mapping/WrongType
    //   251: dup
    //   252: aload_1
    //   253: ldc_w 387
    //   256: iconst_2
    //   257: aload_0
    //   258: invokespecial 385	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   261: athrow
    //   262: astore_0
    //   263: new 380	gnu/mapping/WrongType
    //   266: dup
    //   267: aload_0
    //   268: ldc_w 389
    //   271: iconst_1
    //   272: aload 4
    //   274: invokespecial 385	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   277: athrow
    //   278: astore_0
    //   279: new 380	gnu/mapping/WrongType
    //   282: dup
    //   283: aload_0
    //   284: ldc_w 389
    //   287: iconst_2
    //   288: aload_1
    //   289: invokespecial 385	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   292: athrow
    //   293: astore_0
    //   294: new 380	gnu/mapping/WrongType
    //   297: dup
    //   298: aload_0
    //   299: ldc_w 391
    //   302: iconst_1
    //   303: aload_1
    //   304: invokespecial 385	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   307: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	308	0	paramObject1	Object
    //   0	308	1	paramObject2	Object
    //   21	179	2	localObject1	Object
    //   50	193	3	localObject2	Object
    //   14	259	4	localObject3	Object
    //   62	88	5	localObject4	Object
    //   68	6	6	localCharSequence	CharSequence
    //   26	168	7	i	int
    //   128	20	8	j	int
    //   144	8	9	k	int
    // Exception table:
    //   from	to	target	type
    //   16	22	201	java/lang/ClassCastException
    //   64	70	217	java/lang/ClassCastException
    //   116	121	232	java/lang/ClassCastException
    //   121	130	247	java/lang/ClassCastException
    //   130	137	262	java/lang/ClassCastException
    //   137	146	278	java/lang/ClassCastException
    //   184	193	293	java/lang/ClassCastException
  }
  
  public static Object reverseStringAppend(Object paramObject)
  {
    return lambda23revStringAppend(paramObject, Lit1);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    if (paramModuleMethod.selector == 13) {
      return reverseStringAppend(paramObject);
    }
    return super.apply1(paramModuleMethod, paramObject);
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    if (paramModuleMethod.selector == 12) {
      return genericWrite(paramObject1, paramObject2, paramObject3, paramObject4);
    }
    return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 13)
    {
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    }
    return super.match1(paramModuleMethod, paramObject, paramCallContext);
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 12)
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
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
  
  public class frame
    extends ModuleBody
  {
    Object display$Qu;
    Object output;
    Object width;
    
    public static Object lambda1isReadMacro(Object paramObject)
    {
      Object localObject1 = lists.car.apply1(paramObject);
      paramObject = lists.cdr.apply1(paramObject);
      Object localObject2 = Scheme.isEqv.apply2(localObject1, genwrite.Lit30);
      if (localObject2 != Boolean.FALSE) {
        if (localObject2 == Boolean.FALSE) {
          break label92;
        }
      }
      boolean bool;
      for (;;)
      {
        bool = lists.isPair(paramObject);
        if (!bool) {
          break label147;
        }
        if (!lists.isNull(lists.cdr.apply1(paramObject))) {
          break;
        }
        return Boolean.TRUE;
        localObject2 = Scheme.isEqv.apply2(localObject1, genwrite.Lit31);
        if (localObject2 != Boolean.FALSE)
        {
          if (localObject2 != Boolean.FALSE) {}
        }
        else {
          label92:
          do
          {
            do
            {
              return Boolean.FALSE;
              localObject2 = Scheme.isEqv.apply2(localObject1, genwrite.Lit32);
              if (localObject2 == Boolean.FALSE) {
                break;
              }
            } while (localObject2 == Boolean.FALSE);
            break;
          } while (Scheme.isEqv.apply2(localObject1, genwrite.Lit33) == Boolean.FALSE);
        }
      }
      return Boolean.FALSE;
      label147:
      if (bool) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    
    public static Object lambda2readMacroBody(Object paramObject)
    {
      return lists.cadr.apply1(paramObject);
    }
    
    public static Object lambda3readMacroPrefix(Object paramObject)
    {
      Object localObject = lists.car.apply1(paramObject);
      lists.cdr.apply1(paramObject);
      if (Scheme.isEqv.apply2(localObject, genwrite.Lit30) != Boolean.FALSE) {
        return "'";
      }
      if (Scheme.isEqv.apply2(localObject, genwrite.Lit31) != Boolean.FALSE) {
        return "`";
      }
      if (Scheme.isEqv.apply2(localObject, genwrite.Lit32) != Boolean.FALSE) {
        return ",";
      }
      if (Scheme.isEqv.apply2(localObject, genwrite.Lit33) != Boolean.FALSE) {
        return ",@";
      }
      return Values.empty;
    }
    
    public Object lambda4out(Object paramObject1, Object paramObject2)
    {
      Object localObject2;
      Object localObject1;
      if (paramObject2 != Boolean.FALSE)
      {
        localObject2 = Scheme.applyToArgs.apply2(this.output, paramObject1);
        localObject1 = localObject2;
        if (localObject2 != Boolean.FALSE) {
          localObject1 = AddOp.$Pl;
        }
      }
      try
      {
        localObject2 = (CharSequence)paramObject1;
        localObject1 = ((Procedure)localObject1).apply2(paramObject2, Integer.valueOf(strings.stringLength((CharSequence)localObject2)));
        return localObject1;
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "string-length", 1, paramObject1);
      }
      return paramObject2;
    }
    
    public Object lambda5wr(Object paramObject1, Object paramObject2)
    {
      Object localObject2;
      if (lists.isPair(paramObject1))
      {
        if (lambda1isReadMacro(paramObject1) != Boolean.FALSE)
        {
          localObject1 = lambda5wr(lambda2readMacroBody(paramObject1), lambda4out(lambda3readMacroPrefix(paramObject1), paramObject2));
          return localObject1;
        }
        localObject2 = paramObject2;
        localObject1 = paramObject1;
      }
      for (;;)
      {
        if (lists.isPair(localObject1))
        {
          Object localObject3 = lists.cdr.apply1(localObject1);
          paramObject2 = localObject3;
          paramObject1 = localObject2;
          if (localObject2 != Boolean.FALSE) {
            paramObject1 = lambda5wr(lists.car.apply1(localObject1), lambda4out("(", localObject2));
          }
          for (paramObject2 = localObject3;; paramObject2 = localObject1)
          {
            localObject1 = paramObject1;
            if (paramObject1 == Boolean.FALSE) {
              break;
            }
            if (!lists.isPair(paramObject2)) {
              break label143;
            }
            localObject1 = lists.cdr.apply1(paramObject2);
            paramObject1 = lambda5wr(lists.car.apply1(paramObject2), lambda4out(" ", paramObject1));
          }
          label143:
          if (lists.isNull(paramObject2)) {
            return lambda4out(")", paramObject1);
          }
          return lambda4out(")", lambda5wr(paramObject2, lambda4out(" . ", paramObject1)));
        }
        return lambda4out("()", localObject2);
        localObject1 = paramObject1;
        localObject2 = paramObject2;
        if (!lists.isNull(paramObject1)) {
          if (!vectors.isVector(paramObject1)) {}
        }
        try
        {
          localObject1 = (FVector)paramObject1;
          localObject1 = vectors.vector$To$List((FVector)localObject1);
          localObject2 = lambda4out("#", paramObject2);
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "vector->list", 1, paramObject1);
        }
      }
      if (this.display$Qu != Boolean.FALSE) {}
      for (Object localObject1 = "~a";; localObject1 = "~s") {
        return lambda4out(Format.formatToString(0, new Object[] { localObject1, paramObject1 }), paramObject2);
      }
    }
  }
  
  public class frame0
    extends ModuleBody
  {
    Procedure pp$MnAND = new ModuleMethod(this, 8, genwrite.Lit26, 12291);
    Procedure pp$MnBEGIN = new ModuleMethod(this, 10, genwrite.Lit28, 12291);
    Procedure pp$MnCASE = new ModuleMethod(this, 7, genwrite.Lit25, 12291);
    Procedure pp$MnCOND = new ModuleMethod(this, 6, genwrite.Lit24, 12291);
    Procedure pp$MnDO = new ModuleMethod(this, 11, genwrite.Lit29, 12291);
    Procedure pp$MnIF = new ModuleMethod(this, 5, genwrite.Lit23, 12291);
    Procedure pp$MnLAMBDA = new ModuleMethod(this, 4, genwrite.Lit22, 12291);
    Procedure pp$MnLET = new ModuleMethod(this, 9, genwrite.Lit27, 12291);
    Procedure pp$Mnexpr = new ModuleMethod(this, 2, genwrite.Lit20, 12291);
    Procedure pp$Mnexpr$Mnlist = new ModuleMethod(this, 3, genwrite.Lit21, 12291);
    genwrite.frame staticLink;
    
    public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 2: 
        return lambda8ppExpr(paramObject1, paramObject2, paramObject3);
      case 3: 
        return lambda13ppExprList(paramObject1, paramObject2, paramObject3);
      case 4: 
        return lambda14pp$MnLAMBDA(paramObject1, paramObject2, paramObject3);
      case 5: 
        return lambda15pp$MnIF(paramObject1, paramObject2, paramObject3);
      case 6: 
        return lambda16pp$MnCOND(paramObject1, paramObject2, paramObject3);
      case 7: 
        return lambda17pp$MnCASE(paramObject1, paramObject2, paramObject3);
      case 8: 
        return lambda18pp$MnAND(paramObject1, paramObject2, paramObject3);
      case 9: 
        return lambda19pp$MnLET(paramObject1, paramObject2, paramObject3);
      case 10: 
        return lambda20pp$MnBEGIN(paramObject1, paramObject2, paramObject3);
      }
      return lambda21pp$MnDO(paramObject1, paramObject2, paramObject3);
    }
    
    public Object lambda10ppList(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    {
      paramObject2 = this.staticLink.lambda4out("(", paramObject2);
      return lambda11ppDown(paramObject1, paramObject2, paramObject2, paramObject3, paramObject4);
    }
    
    public Object lambda11ppDown(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5)
    {
      if (paramObject2 != Boolean.FALSE)
      {
        if (lists.isPair(paramObject1))
        {
          Object localObject2 = lists.cdr.apply1(paramObject1);
          if (lists.isNull(localObject2)) {}
          for (Object localObject1 = AddOp.$Pl.apply2(paramObject4, genwrite.Lit17);; localObject1 = genwrite.Lit1)
          {
            paramObject2 = lambda7pr(lists.car.apply1(paramObject1), lambda6indent(paramObject3, paramObject2), localObject1, paramObject5);
            paramObject1 = localObject2;
            break;
          }
        }
        if (lists.isNull(paramObject1)) {
          return this.staticLink.lambda4out(")", paramObject2);
        }
        return this.staticLink.lambda4out(")", lambda7pr(paramObject1, lambda6indent(paramObject3, this.staticLink.lambda4out(".", lambda6indent(paramObject3, paramObject2))), AddOp.$Pl.apply2(paramObject4, genwrite.Lit17), paramObject5));
      }
      return paramObject2;
    }
    
    public Object lambda12ppGeneral(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6, Object paramObject7)
    {
      Object localObject2 = lists.car.apply1(paramObject1);
      Object localObject1 = lists.cdr.apply1(paramObject1);
      paramObject1 = this.staticLink.lambda5wr(localObject2, this.staticLink.lambda4out("(", paramObject2));
      Object localObject3;
      if (paramObject4 != Boolean.FALSE)
      {
        if (!lists.isPair(localObject1)) {
          break label291;
        }
        localObject2 = lists.car.apply1(localObject1);
        paramObject4 = lists.cdr.apply1(localObject1);
        paramObject1 = this.staticLink.lambda5wr(localObject2, this.staticLink.lambda4out(" ", paramObject1));
        localObject2 = AddOp.$Pl.apply2(paramObject2, genwrite.Lit19);
        localObject3 = AddOp.$Pl.apply2(paramObject1, genwrite.Lit17);
        label118:
        if (paramObject5 == Boolean.FALSE) {
          break label322;
        }
        paramObject2 = paramObject1;
        localObject1 = paramObject4;
        if (lists.isPair(paramObject4))
        {
          label140:
          Object localObject4 = lists.car.apply1(paramObject4);
          localObject1 = lists.cdr.apply1(paramObject4);
          if (!lists.isNull(localObject1)) {
            break label339;
          }
          paramObject2 = AddOp.$Pl.apply2(paramObject3, genwrite.Lit17);
          label179:
          paramObject2 = lambda7pr(localObject4, lambda6indent(localObject3, paramObject1), paramObject2, paramObject5);
        }
        label196:
        if (paramObject6 == Boolean.FALSE) {
          break label346;
        }
        if (!lists.isPair(localObject1)) {
          break label354;
        }
        label212:
        paramObject5 = lists.car.apply1(localObject1);
        paramObject4 = lists.cdr.apply1(localObject1);
        if (!lists.isNull(paramObject4)) {
          break label360;
        }
      }
      label291:
      label322:
      label339:
      label346:
      label354:
      label360:
      for (paramObject1 = AddOp.$Pl.apply2(paramObject3, genwrite.Lit17);; paramObject1 = genwrite.Lit1)
      {
        paramObject2 = lambda7pr(paramObject5, lambda6indent(localObject3, paramObject2), paramObject1, paramObject6);
        for (paramObject1 = paramObject4;; paramObject1 = localObject1)
        {
          return lambda11ppDown(paramObject1, paramObject2, localObject2, paramObject3, paramObject7);
          if (paramObject4 != Boolean.FALSE) {
            break;
          }
          localObject2 = AddOp.$Pl.apply2(paramObject2, genwrite.Lit19);
          localObject3 = AddOp.$Pl.apply2(paramObject1, genwrite.Lit17);
          paramObject4 = localObject1;
          break label118;
          paramObject2 = paramObject1;
          localObject1 = paramObject4;
          if (paramObject5 == Boolean.FALSE) {
            break label196;
          }
          break label140;
          paramObject2 = genwrite.Lit1;
          break label179;
          if (paramObject6 != Boolean.FALSE) {
            break label212;
          }
        }
      }
    }
    
    public Object lambda13ppExprList(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      return lambda10ppList(paramObject1, paramObject2, paramObject3, this.pp$Mnexpr);
    }
    
    public Object lambda14pp$MnLAMBDA(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      return lambda12ppGeneral(paramObject1, paramObject2, paramObject3, Boolean.FALSE, this.pp$Mnexpr$Mnlist, Boolean.FALSE, this.pp$Mnexpr);
    }
    
    public Object lambda15pp$MnIF(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      return lambda12ppGeneral(paramObject1, paramObject2, paramObject3, Boolean.FALSE, this.pp$Mnexpr, Boolean.FALSE, this.pp$Mnexpr);
    }
    
    public Object lambda16pp$MnCOND(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      return lambda9ppCall(paramObject1, paramObject2, paramObject3, this.pp$Mnexpr$Mnlist);
    }
    
    public Object lambda17pp$MnCASE(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      return lambda12ppGeneral(paramObject1, paramObject2, paramObject3, Boolean.FALSE, this.pp$Mnexpr, Boolean.FALSE, this.pp$Mnexpr$Mnlist);
    }
    
    public Object lambda18pp$MnAND(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      return lambda9ppCall(paramObject1, paramObject2, paramObject3, this.pp$Mnexpr);
    }
    
    public Object lambda19pp$MnLET(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      Object localObject = lists.cdr.apply1(paramObject1);
      boolean bool = lists.isPair(localObject);
      if (bool)
      {
        bool = misc.isSymbol(lists.car.apply1(localObject));
        if (!bool) {
          break label68;
        }
      }
      label68:
      for (localObject = Boolean.TRUE;; localObject = Boolean.FALSE)
      {
        return lambda12ppGeneral(paramObject1, paramObject2, paramObject3, localObject, this.pp$Mnexpr$Mnlist, Boolean.FALSE, this.pp$Mnexpr);
        break;
      }
    }
    
    public Object lambda20pp$MnBEGIN(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      return lambda12ppGeneral(paramObject1, paramObject2, paramObject3, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, this.pp$Mnexpr);
    }
    
    public Object lambda21pp$MnDO(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      return lambda12ppGeneral(paramObject1, paramObject2, paramObject3, Boolean.FALSE, this.pp$Mnexpr$Mnlist, this.pp$Mnexpr$Mnlist, this.pp$Mnexpr);
    }
    
    public Object lambda6indent(Object paramObject1, Object paramObject2)
    {
      if (paramObject2 != Boolean.FALSE)
      {
        Object localObject;
        if (Scheme.numLss.apply2(paramObject1, paramObject2) != Boolean.FALSE)
        {
          localObject = this.staticLink.lambda4out(strings.makeString(1, genwrite.Lit0), paramObject2);
          paramObject2 = localObject;
          if (localObject != Boolean.FALSE) {
            paramObject2 = genwrite.Lit1;
          }
        }
        for (;;)
        {
          localObject = paramObject2;
          if (Scheme.numGrt.apply2(paramObject1, genwrite.Lit1) != Boolean.FALSE)
          {
            if (Scheme.numGrt.apply2(paramObject1, genwrite.Lit15) != Boolean.FALSE)
            {
              paramObject1 = AddOp.$Mn.apply2(paramObject1, genwrite.Lit16);
              paramObject2 = this.staticLink.lambda4out("        ", paramObject2);
              continue;
            }
            localObject = this.staticLink;
          }
          try
          {
            int i = ((Number)paramObject1).intValue();
            localObject = ((genwrite.frame)localObject).lambda4out(strings.substring("        ", 0, i), paramObject2);
            paramObject2 = localObject;
            return paramObject2;
          }
          catch (ClassCastException paramObject2)
          {
            throw new WrongType((ClassCastException)paramObject2, "substring", 3, paramObject1);
          }
          paramObject1 = AddOp.$Mn.apply2(paramObject1, paramObject2);
        }
      }
      return paramObject2;
    }
    
    public Object lambda7pr(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    {
      genwrite.frame1 localframe1 = new genwrite.frame1();
      localframe1.staticLink = this;
      boolean bool = lists.isPair(paramObject1);
      if (bool)
      {
        if (!bool) {}
      }
      else {
        while (vectors.isVector(paramObject1))
        {
          LList localLList = LList.Empty;
          localframe1.left = numbers.min(new Object[] { AddOp.$Pl.apply2(AddOp.$Mn.apply2(AddOp.$Mn.apply2(this.staticLink.width, paramObject2), paramObject3), genwrite.Lit17), genwrite.Lit18 });
          localframe1.result = localLList;
          genwrite.genericWrite(paramObject1, this.staticLink.display$Qu, Boolean.FALSE, localframe1.lambda$Fn1);
          if (Scheme.numGrt.apply2(localframe1.left, genwrite.Lit1) == Boolean.FALSE) {
            break;
          }
          return this.staticLink.lambda4out(genwrite.reverseStringAppend(localframe1.result), paramObject2);
        }
      }
      return this.staticLink.lambda5wr(paramObject1, paramObject2);
      if (lists.isPair(paramObject1)) {
        return Scheme.applyToArgs.apply4(paramObject4, paramObject1, paramObject2, paramObject3);
      }
      try
      {
        paramObject4 = (FVector)paramObject1;
        return lambda10ppList(vectors.vector$To$List((FVector)paramObject4), this.staticLink.lambda4out("#", paramObject2), paramObject3, this.pp$Mnexpr);
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "vector->list", 1, paramObject1);
      }
    }
    
    public Object lambda8ppExpr(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      if (genwrite.frame.lambda1isReadMacro(paramObject1) != Boolean.FALSE) {
        return lambda7pr(genwrite.frame.lambda2readMacroBody(paramObject1), this.staticLink.lambda4out(genwrite.frame.lambda3readMacroPrefix(paramObject1), paramObject2), paramObject3, this.pp$Mnexpr);
      }
      localObject2 = lists.car.apply1(paramObject1);
      Object localObject1;
      if (misc.isSymbol(localObject2))
      {
        localObject1 = Scheme.isEqv.apply2(localObject2, genwrite.Lit2);
        if (localObject1 != Boolean.FALSE)
        {
          if (localObject1 == Boolean.FALSE) {
            break label137;
          }
          localObject1 = this.pp$MnLAMBDA;
        }
        for (;;)
        {
          label88:
          if (localObject1 == Boolean.FALSE) {
            break label437;
          }
          return Scheme.applyToArgs.apply4(localObject1, paramObject1, paramObject2, paramObject3);
          localObject1 = Scheme.isEqv.apply2(localObject2, genwrite.Lit3);
          if (localObject1 != Boolean.FALSE)
          {
            if (localObject1 != Boolean.FALSE) {
              break;
            }
            label137:
            localObject1 = Scheme.isEqv.apply2(localObject2, genwrite.Lit6);
            if (localObject1 == Boolean.FALSE) {
              break label227;
            }
            if (localObject1 == Boolean.FALSE) {
              break label244;
            }
          }
          label227:
          while (Scheme.isEqv.apply2(localObject2, genwrite.Lit7) != Boolean.FALSE)
          {
            localObject1 = this.pp$MnIF;
            break label88;
            localObject1 = Scheme.isEqv.apply2(localObject2, genwrite.Lit4);
            if (localObject1 != Boolean.FALSE)
            {
              if (localObject1 == Boolean.FALSE) {
                break label137;
              }
              break;
            }
            if (Scheme.isEqv.apply2(localObject2, genwrite.Lit5) == Boolean.FALSE) {
              break label137;
            }
            break;
          }
          label244:
          if (Scheme.isEqv.apply2(localObject2, genwrite.Lit8) != Boolean.FALSE)
          {
            localObject1 = this.pp$MnCOND;
          }
          else if (Scheme.isEqv.apply2(localObject2, genwrite.Lit9) != Boolean.FALSE)
          {
            localObject1 = this.pp$MnCASE;
          }
          else
          {
            localObject1 = Scheme.isEqv.apply2(localObject2, genwrite.Lit10);
            if (localObject1 != Boolean.FALSE)
            {
              if (localObject1 == Boolean.FALSE) {}
            }
            else {
              while (Scheme.isEqv.apply2(localObject2, genwrite.Lit11) != Boolean.FALSE)
              {
                localObject1 = this.pp$MnAND;
                break;
              }
            }
            if (Scheme.isEqv.apply2(localObject2, genwrite.Lit12) != Boolean.FALSE) {
              localObject1 = this.pp$MnLET;
            } else if (Scheme.isEqv.apply2(localObject2, genwrite.Lit13) != Boolean.FALSE) {
              localObject1 = this.pp$MnBEGIN;
            } else if (Scheme.isEqv.apply2(localObject2, genwrite.Lit14) != Boolean.FALSE) {
              localObject1 = this.pp$MnDO;
            } else {
              localObject1 = Boolean.FALSE;
            }
          }
        }
      }
      try
      {
        label437:
        localObject1 = (Symbol)localObject2;
        if (strings.stringLength(misc.symbol$To$String((Symbol)localObject1)) > 5) {
          return lambda12ppGeneral(paramObject1, paramObject2, paramObject3, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, this.pp$Mnexpr);
        }
        return lambda9ppCall(paramObject1, paramObject2, paramObject3, this.pp$Mnexpr);
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "symbol->string", 1, localObject2);
      }
      return lambda10ppList(paramObject1, paramObject2, paramObject3, this.pp$Mnexpr);
    }
    
    public Object lambda9ppCall(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    {
      Object localObject2 = this.staticLink.lambda5wr(lists.car.apply1(paramObject1), this.staticLink.lambda4out("(", paramObject2));
      Object localObject1 = paramObject2;
      if (paramObject2 != Boolean.FALSE) {
        localObject1 = lambda11ppDown(lists.cdr.apply1(paramObject1), localObject2, AddOp.$Pl.apply2(localObject2, genwrite.Lit17), paramObject3, paramObject4);
      }
      return localObject1;
    }
    
    public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
      case 11: 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 10: 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 9: 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 8: 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 7: 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 6: 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 5: 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 4: 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 3: 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    }
  }
  
  public class frame1
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn1;
    Object left;
    Object result;
    genwrite.frame0 staticLink;
    
    public frame1()
    {
      this$1 = new ModuleMethod(this, 1, null, 4097);
      this$1.setProperty("source-location", "genwrite.scm:72");
      this.lambda$Fn1 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 1)
      {
        if (lambda22(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda22(Object paramObject)
    {
      this.result = lists.cons(paramObject, this.result);
      AddOp localAddOp = AddOp.$Mn;
      Object localObject = this.left;
      try
      {
        CharSequence localCharSequence = (CharSequence)paramObject;
        this.left = localAddOp.apply2(localObject, Integer.valueOf(strings.stringLength(localCharSequence)));
        return ((Boolean)Scheme.numGrt.apply2(this.left, genwrite.Lit1)).booleanValue();
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "string-length", 1, paramObject);
      }
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 1)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\genwrite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */