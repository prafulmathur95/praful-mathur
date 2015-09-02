package gnu.kawa.slib;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LetExp;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Convert;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.reflect.SlotGet;
import gnu.lists.EofClass;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;

public class syntaxutils
  extends ModuleBody
{
  public static final Macro $Prvt$$Ex;
  public static final Macro $Prvt$typecase$Pc;
  public static final syntaxutils $instance;
  static final Keyword Lit0;
  static final PairWithPosition Lit1;
  static final PairWithPosition Lit10;
  static final PairWithPosition Lit11;
  static final PairWithPosition Lit12;
  static final SimpleSymbol Lit13;
  static final SyntaxRules Lit14;
  static final SimpleSymbol Lit15;
  static final SyntaxRules Lit16;
  static final SimpleSymbol Lit17;
  static final SimpleSymbol Lit18;
  static final SimpleSymbol Lit19;
  static final Keyword Lit2;
  static final SimpleSymbol Lit20;
  static final SimpleSymbol Lit21;
  static final SimpleSymbol Lit22;
  static final SimpleSymbol Lit23;
  static final SimpleSymbol Lit24;
  static final SimpleSymbol Lit25;
  static final SimpleSymbol Lit26 = (SimpleSymbol)new SimpleSymbol("lambda").readResolve();
  static final PairWithPosition Lit3;
  static final PairWithPosition Lit4;
  static final PairWithPosition Lit5;
  static final PairWithPosition Lit6;
  static final IntNum Lit7;
  static final IntNum Lit8;
  static final PairWithPosition Lit9;
  public static final ModuleMethod expand;
  
  static
  {
    Lit25 = (SimpleSymbol)new SimpleSymbol("as").readResolve();
    Lit24 = (SimpleSymbol)new SimpleSymbol("else").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("let").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("cond").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("begin").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("or").readResolve();
    Lit19 = (SimpleSymbol)new SimpleSymbol("quote").readResolve();
    Lit18 = (SimpleSymbol)new SimpleSymbol("eql").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("expand").readResolve();
    SimpleSymbol localSimpleSymbol1 = (SimpleSymbol)new SimpleSymbol("!").readResolve();
    Lit15 = localSimpleSymbol1;
    Object localObject = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\r\027\020\b\b", new Object[0], 3), "\001\001\003", "\021\030\004\t\013)\021\030\f\b\003\b\025\023", new Object[] { (SimpleSymbol)new SimpleSymbol("invoke").readResolve(), Lit19 }, 1);
    Lit16 = new SyntaxRules(new Object[] { localSimpleSymbol1 }, new SyntaxRule[] { localObject }, 3);
    localSimpleSymbol1 = (SimpleSymbol)new SimpleSymbol("typecase%").readResolve();
    Lit13 = localSimpleSymbol1;
    localObject = Lit18;
    SimpleSymbol localSimpleSymbol2 = Lit20;
    SyntaxRule localSyntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\f\007<\f\002\r\017\b\b\b\r\027\020\b\b", new Object[] { Boolean.TRUE }, 3), "\001\003\003", "\021\030\004\b\r\013", new Object[] { Lit21 }, 1);
    SyntaxRule localSyntaxRule2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\\,\f\002\f\017\b\r\027\020\b\b\r\037\030\b\b", new Object[] { Lit18 }, 4), "\001\001\003\003", "\021\030\004yY\021\030\f\t\003\b\021\030\024\b\013\b\025\023\b\021\030\034\b\021\030$\t\003\b\035\033", new Object[] { Lit22, (SimpleSymbol)new SimpleSymbol("eqv?").readResolve(), Lit19, Lit24, Lit13 }, 1);
    SyntaxRule localSyntaxRule3 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\\,\f\002\f\017\b\r\027\020\b\b\r\037\030\b\b", new Object[] { Lit20 }, 4), "\001\001\003\003", "\021\030\004\t\003)\t\013\b\025\023\b\035\033", new Object[] { Lit13 }, 1);
    SyntaxRule localSyntaxRule4 = new SyntaxRule(new SyntaxPattern("\f\030\f\007l<\f\002\r\017\b\b\b\r\027\020\b\b\r\037\030\b\b", new Object[] { Lit20 }, 4), "\001\003\003\003", "\021\030\004\b\021\030\f\b\021\030\024\021\b\003\b\021\030\034\b\025\023\b\021\030$\t\003I\r\t\013\b\021\030\f\b\003\b\021\030,\b\021\030$\t\003\b\035\033", new Object[] { Lit23, (SimpleSymbol)new SimpleSymbol("f").readResolve(), Lit26, Lit21, Lit13, Boolean.TRUE }, 1);
    SyntaxRule localSyntaxRule5 = new SyntaxRule(new SyntaxPattern("\f\030\f\007<\f\017\r\027\020\b\b\r\037\030\b\b", new Object[0], 4), "\001\001\003\003", "\021\030\004ñ9\021\030\f\t\003\b\013\b\021\030\024Q\b\t\003\021\030\034\t\013\b\003\b\021\030$\b\025\023\b\021\030,\b\021\0304\t\003\b\035\033", new Object[] { Lit22, (SimpleSymbol)new SimpleSymbol("instance?").readResolve(), Lit23, (SimpleSymbol)new SimpleSymbol("::").readResolve(), Lit21, Lit24, Lit13 }, 1);
    SyntaxRule localSyntaxRule6 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\021\030\f\t\003\b\021\030\024\021\030\034\b\021\030$\021\030,\b\003", new Object[] { (SimpleSymbol)new SimpleSymbol("error").readResolve(), "typecase% failed", Lit15, (SimpleSymbol)new SimpleSymbol("getClass").readResolve(), Lit25, (SimpleSymbol)new SimpleSymbol("<object>").readResolve() }, 0);
    Lit14 = new SyntaxRules(new Object[] { localSimpleSymbol1, localObject, localSimpleSymbol2 }, new SyntaxRule[] { localSyntaxRule1, localSyntaxRule2, localSyntaxRule3, localSyntaxRule4, localSyntaxRule5, localSyntaxRule6 }, 4);
    Lit12 = PairWithPosition.make((SimpleSymbol)new SimpleSymbol(":").readResolve(), LList.Empty, "syntaxutils.scm", 634896);
    Lit11 = PairWithPosition.make(Lit25, LList.Empty, "syntaxutils.scm", 626704);
    Lit10 = PairWithPosition.make(Lit19, LList.Empty, "syntaxutils.scm", 552972);
    Lit9 = PairWithPosition.make(Lit23, LList.Empty, "syntaxutils.scm", 479236);
    Lit8 = IntNum.make(1);
    Lit7 = IntNum.make(0);
    Lit6 = PairWithPosition.make((SimpleSymbol)new SimpleSymbol("if").readResolve(), LList.Empty, "syntaxutils.scm", 417799);
    Lit5 = PairWithPosition.make(Lit21, LList.Empty, "syntaxutils.scm", 409627);
    Lit4 = PairWithPosition.make(Lit26, LList.Empty, "syntaxutils.scm", 376839);
    Lit3 = PairWithPosition.make((SimpleSymbol)new SimpleSymbol("set").readResolve(), LList.Empty, "syntaxutils.scm", 368647);
    Lit2 = Keyword.make("lang");
    Lit1 = PairWithPosition.make(Lit21, LList.Empty, "syntaxutils.scm", 278557);
    Lit0 = Keyword.make("env");
    $instance = new syntaxutils();
    $Prvt$typecase$Pc = Macro.make(Lit13, Lit14, $instance);
    $Prvt$$Ex = Macro.make(Lit15, Lit16, $instance);
    expand = new ModuleMethod($instance, 1, Lit17, 61441);
    $instance.run();
  }
  
  public syntaxutils()
  {
    ModuleInfo.register(this);
  }
  
  public static Object expand$V(Object paramObject, Object[] paramArrayOfObject)
  {
    Object localObject = Keyword.searchForKeyword(paramArrayOfObject, 0, Lit0);
    paramArrayOfObject = (Object[])localObject;
    if (localObject == Special.dfault) {
      paramArrayOfObject = misc.interactionEnvironment();
    }
    return unrewrite(rewriteForm$V(Quote.append$V(new Object[] { Lit1, Quote.consX$V(new Object[] { paramObject, LList.Empty }) }), new Object[] { Lit0, paramArrayOfObject }));
  }
  
  /* Error */
  static Expression rewriteForm$V(Object paramObject, Object[] paramArrayOfObject)
  {
    // Byte code:
    //   0: aload_1
    //   1: iconst_0
    //   2: getstatic 254	gnu/kawa/slib/syntaxutils:Lit2	Lgnu/expr/Keyword;
    //   5: invokestatic 298	gnu/expr/Keyword:searchForKeyword	([Ljava/lang/Object;ILjava/lang/Object;)Ljava/lang/Object;
    //   8: astore_3
    //   9: aload_3
    //   10: astore_2
    //   11: aload_3
    //   12: getstatic 304	gnu/expr/Special:dfault	Lgnu/expr/Special;
    //   15: if_acmpne +7 -> 22
    //   18: invokestatic 335	gnu/expr/Language:getDefaultLanguage	()Lgnu/expr/Language;
    //   21: astore_2
    //   22: aload_1
    //   23: iconst_0
    //   24: getstatic 261	gnu/kawa/slib/syntaxutils:Lit0	Lgnu/expr/Keyword;
    //   27: invokestatic 298	gnu/expr/Keyword:searchForKeyword	([Ljava/lang/Object;ILjava/lang/Object;)Ljava/lang/Object;
    //   30: astore_3
    //   31: aload_3
    //   32: astore_1
    //   33: aload_3
    //   34: getstatic 304	gnu/expr/Special:dfault	Lgnu/expr/Special;
    //   37: if_acmpne +7 -> 44
    //   40: invokestatic 310	kawa/lib/misc:interactionEnvironment	()Lgnu/mapping/Environment;
    //   43: astore_1
    //   44: aload_1
    //   45: checkcast 337	gnu/mapping/Environment
    //   48: astore_3
    //   49: aload_2
    //   50: checkcast 331	gnu/expr/Language
    //   53: astore_1
    //   54: aload_3
    //   55: aload_1
    //   56: invokestatic 343	gnu/expr/NameLookup:getInstance	(Lgnu/mapping/Environment;Lgnu/expr/Language;)Lgnu/expr/NameLookup;
    //   59: astore_1
    //   60: new 345	gnu/text/SourceMessages
    //   63: dup
    //   64: invokespecial 346	gnu/text/SourceMessages:<init>	()V
    //   67: astore_3
    //   68: aload_2
    //   69: checkcast 331	gnu/expr/Language
    //   72: astore 4
    //   74: new 348	kawa/lang/Translator
    //   77: dup
    //   78: aload 4
    //   80: aload_3
    //   81: aload_1
    //   82: invokespecial 351	kawa/lang/Translator:<init>	(Lgnu/expr/Language;Lgnu/text/SourceMessages;Lgnu/expr/NameLookup;)V
    //   85: astore_2
    //   86: aload_2
    //   87: aconst_null
    //   88: invokevirtual 355	kawa/lang/Translator:pushNewModule	(Ljava/lang/String;)Lgnu/expr/ModuleExp;
    //   91: pop
    //   92: aload_2
    //   93: invokestatic 361	gnu/expr/Compilation:setSaveCurrent	(Lgnu/expr/Compilation;)Lgnu/expr/Compilation;
    //   96: astore_1
    //   97: aload_2
    //   98: aload_0
    //   99: invokevirtual 365	kawa/lang/Translator:rewrite	(Ljava/lang/Object;)Lgnu/expr/Expression;
    //   102: astore_0
    //   103: aload_1
    //   104: invokestatic 369	gnu/expr/Compilation:restoreCurrent	(Lgnu/expr/Compilation;)V
    //   107: aload_0
    //   108: areturn
    //   109: astore_0
    //   110: aload_1
    //   111: invokestatic 369	gnu/expr/Compilation:restoreCurrent	(Lgnu/expr/Compilation;)V
    //   114: aload_0
    //   115: athrow
    //   116: astore_0
    //   117: new 371	gnu/mapping/WrongType
    //   120: dup
    //   121: aload_0
    //   122: ldc_w 373
    //   125: iconst_1
    //   126: aload_1
    //   127: invokespecial 376	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   130: athrow
    //   131: astore_0
    //   132: new 371	gnu/mapping/WrongType
    //   135: dup
    //   136: aload_0
    //   137: ldc_w 373
    //   140: iconst_2
    //   141: aload_2
    //   142: invokespecial 376	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   145: athrow
    //   146: astore_0
    //   147: new 371	gnu/mapping/WrongType
    //   150: dup
    //   151: aload_0
    //   152: ldc_w 378
    //   155: iconst_1
    //   156: aload_2
    //   157: invokespecial 376	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   160: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	161	0	paramObject	Object
    //   0	161	1	paramArrayOfObject	Object[]
    //   10	147	2	localObject1	Object
    //   8	73	3	localObject2	Object
    //   72	7	4	localLanguage	gnu.expr.Language
    // Exception table:
    //   from	to	target	type
    //   97	103	109	finally
    //   44	49	116	java/lang/ClassCastException
    //   49	54	131	java/lang/ClassCastException
    //   68	74	146	java/lang/ClassCastException
  }
  
  /* Error */
  static Object unrewrite(Expression paramExpression)
  {
    // Byte code:
    //   0: new 6	gnu/kawa/slib/syntaxutils$frame
    //   3: dup
    //   4: invokespecial 379	gnu/kawa/slib/syntaxutils$frame:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: instanceof 381
    //   12: ifeq +15 -> 27
    //   15: aload_0
    //   16: checkcast 381	gnu/expr/LetExp
    //   19: astore_1
    //   20: aload_1
    //   21: invokestatic 385	gnu/kawa/slib/syntaxutils:unrewriteLet	(Lgnu/expr/LetExp;)Ljava/lang/Object;
    //   24: astore_1
    //   25: aload_1
    //   26: areturn
    //   27: aload_0
    //   28: instanceof 387
    //   31: ifeq +13 -> 44
    //   34: aload_0
    //   35: checkcast 387	gnu/expr/QuoteExp
    //   38: astore_1
    //   39: aload_1
    //   40: invokestatic 391	gnu/kawa/slib/syntaxutils:unrewriteQuote	(Lgnu/expr/QuoteExp;)Ljava/lang/Object;
    //   43: areturn
    //   44: aload_0
    //   45: instanceof 393
    //   48: ifeq +65 -> 113
    //   51: aload_0
    //   52: checkcast 393	gnu/expr/SetExp
    //   55: astore_1
    //   56: iconst_2
    //   57: anewarray 115	java/lang/Object
    //   60: dup
    //   61: iconst_0
    //   62: getstatic 245	gnu/kawa/slib/syntaxutils:Lit3	Lgnu/lists/PairWithPosition;
    //   65: aastore
    //   66: dup
    //   67: iconst_1
    //   68: iconst_2
    //   69: anewarray 115	java/lang/Object
    //   72: dup
    //   73: iconst_0
    //   74: aload_1
    //   75: invokevirtual 396	gnu/expr/SetExp:getSymbol	()Ljava/lang/Object;
    //   78: aastore
    //   79: dup
    //   80: iconst_1
    //   81: iconst_2
    //   82: anewarray 115	java/lang/Object
    //   85: dup
    //   86: iconst_0
    //   87: aload_1
    //   88: invokevirtual 400	gnu/expr/SetExp:getNewValue	()Lgnu/expr/Expression;
    //   91: invokestatic 327	gnu/kawa/slib/syntaxutils:unrewrite	(Lgnu/expr/Expression;)Ljava/lang/Object;
    //   94: aastore
    //   95: dup
    //   96: iconst_1
    //   97: getstatic 200	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   100: aastore
    //   101: invokestatic 316	kawa/lang/Quote:consX$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   104: aastore
    //   105: invokestatic 316	kawa/lang/Quote:consX$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   108: aastore
    //   109: invokestatic 319	kawa/lang/Quote:append$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   112: areturn
    //   113: aload_0
    //   114: instanceof 402
    //   117: ifeq +109 -> 226
    //   120: aload_0
    //   121: checkcast 402	gnu/expr/LambdaExp
    //   124: astore_2
    //   125: getstatic 240	gnu/kawa/slib/syntaxutils:Lit4	Lgnu/lists/PairWithPosition;
    //   128: astore_3
    //   129: aload_1
    //   130: getstatic 200	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   133: putfield 405	gnu/kawa/slib/syntaxutils$frame:pack	Lgnu/lists/LList;
    //   136: aload_2
    //   137: invokevirtual 409	gnu/expr/LambdaExp:firstDecl	()Lgnu/expr/Declaration;
    //   140: astore_0
    //   141: aload_0
    //   142: ifnull +26 -> 168
    //   145: aload_1
    //   146: aload_0
    //   147: invokevirtual 412	gnu/expr/Declaration:getSymbol	()Ljava/lang/Object;
    //   150: aload_1
    //   151: getfield 405	gnu/kawa/slib/syntaxutils$frame:pack	Lgnu/lists/LList;
    //   154: invokestatic 418	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   157: putfield 405	gnu/kawa/slib/syntaxutils$frame:pack	Lgnu/lists/LList;
    //   160: aload_0
    //   161: invokevirtual 421	gnu/expr/Declaration:nextDecl	()Lgnu/expr/Declaration;
    //   164: astore_0
    //   165: goto -24 -> 141
    //   168: iconst_2
    //   169: anewarray 115	java/lang/Object
    //   172: dup
    //   173: iconst_0
    //   174: aload_3
    //   175: aastore
    //   176: dup
    //   177: iconst_1
    //   178: iconst_2
    //   179: anewarray 115	java/lang/Object
    //   182: dup
    //   183: iconst_0
    //   184: aload_1
    //   185: getfield 405	gnu/kawa/slib/syntaxutils$frame:pack	Lgnu/lists/LList;
    //   188: invokestatic 425	kawa/lib/lists:reverse$Ex	(Lgnu/lists/LList;)Lgnu/lists/LList;
    //   191: aastore
    //   192: dup
    //   193: iconst_1
    //   194: iconst_2
    //   195: anewarray 115	java/lang/Object
    //   198: dup
    //   199: iconst_0
    //   200: aload_2
    //   201: getfield 429	gnu/expr/LambdaExp:body	Lgnu/expr/Expression;
    //   204: invokestatic 327	gnu/kawa/slib/syntaxutils:unrewrite	(Lgnu/expr/Expression;)Ljava/lang/Object;
    //   207: aastore
    //   208: dup
    //   209: iconst_1
    //   210: getstatic 200	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   213: aastore
    //   214: invokestatic 316	kawa/lang/Quote:consX$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   217: aastore
    //   218: invokestatic 316	kawa/lang/Quote:consX$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   221: aastore
    //   222: invokestatic 319	kawa/lang/Quote:append$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   225: areturn
    //   226: aload_0
    //   227: instanceof 431
    //   230: ifeq +13 -> 243
    //   233: aload_0
    //   234: checkcast 431	gnu/expr/ReferenceExp
    //   237: astore_1
    //   238: aload_1
    //   239: invokevirtual 432	gnu/expr/ReferenceExp:getSymbol	()Ljava/lang/Object;
    //   242: areturn
    //   243: aload_0
    //   244: instanceof 434
    //   247: ifeq +13 -> 260
    //   250: aload_0
    //   251: checkcast 434	gnu/expr/ApplyExp
    //   254: astore_1
    //   255: aload_1
    //   256: invokestatic 438	gnu/kawa/slib/syntaxutils:unrewriteApply	(Lgnu/expr/ApplyExp;)Ljava/lang/Object;
    //   259: areturn
    //   260: aload_0
    //   261: instanceof 440
    //   264: ifeq +32 -> 296
    //   267: aload_0
    //   268: checkcast 440	gnu/expr/BeginExp
    //   271: astore_1
    //   272: iconst_2
    //   273: anewarray 115	java/lang/Object
    //   276: dup
    //   277: iconst_0
    //   278: getstatic 237	gnu/kawa/slib/syntaxutils:Lit5	Lgnu/lists/PairWithPosition;
    //   281: aastore
    //   282: dup
    //   283: iconst_1
    //   284: aload_1
    //   285: invokevirtual 444	gnu/expr/BeginExp:getExpressions	()[Lgnu/expr/Expression;
    //   288: invokestatic 448	gnu/kawa/slib/syntaxutils:unrewrite$St	([Lgnu/expr/Expression;)Ljava/lang/Object;
    //   291: aastore
    //   292: invokestatic 319	kawa/lang/Quote:append$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   295: areturn
    //   296: aload_0
    //   297: astore_1
    //   298: aload_0
    //   299: instanceof 450
    //   302: ifeq -277 -> 25
    //   305: aload_0
    //   306: checkcast 450	gnu/expr/IfExp
    //   309: astore 4
    //   311: getstatic 234	gnu/kawa/slib/syntaxutils:Lit6	Lgnu/lists/PairWithPosition;
    //   314: astore_1
    //   315: aload 4
    //   317: invokevirtual 453	gnu/expr/IfExp:getTest	()Lgnu/expr/Expression;
    //   320: invokestatic 327	gnu/kawa/slib/syntaxutils:unrewrite	(Lgnu/expr/Expression;)Ljava/lang/Object;
    //   323: astore_2
    //   324: aload 4
    //   326: invokevirtual 456	gnu/expr/IfExp:getThenClause	()Lgnu/expr/Expression;
    //   329: invokestatic 327	gnu/kawa/slib/syntaxutils:unrewrite	(Lgnu/expr/Expression;)Ljava/lang/Object;
    //   332: astore_3
    //   333: aload 4
    //   335: invokevirtual 459	gnu/expr/IfExp:getElseClause	()Lgnu/expr/Expression;
    //   338: astore_0
    //   339: aload_0
    //   340: ifnonnull +67 -> 407
    //   343: getstatic 200	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   346: astore_0
    //   347: iconst_2
    //   348: anewarray 115	java/lang/Object
    //   351: dup
    //   352: iconst_0
    //   353: aload_1
    //   354: aastore
    //   355: dup
    //   356: iconst_1
    //   357: iconst_2
    //   358: anewarray 115	java/lang/Object
    //   361: dup
    //   362: iconst_0
    //   363: aload_2
    //   364: aastore
    //   365: dup
    //   366: iconst_1
    //   367: iconst_2
    //   368: anewarray 115	java/lang/Object
    //   371: dup
    //   372: iconst_0
    //   373: aload_3
    //   374: aastore
    //   375: dup
    //   376: iconst_1
    //   377: iconst_2
    //   378: anewarray 115	java/lang/Object
    //   381: dup
    //   382: iconst_0
    //   383: aload_0
    //   384: aastore
    //   385: dup
    //   386: iconst_1
    //   387: getstatic 200	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   390: aastore
    //   391: invokestatic 319	kawa/lang/Quote:append$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   394: aastore
    //   395: invokestatic 316	kawa/lang/Quote:consX$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   398: aastore
    //   399: invokestatic 316	kawa/lang/Quote:consX$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   402: aastore
    //   403: invokestatic 319	kawa/lang/Quote:append$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   406: areturn
    //   407: aload_0
    //   408: invokestatic 327	gnu/kawa/slib/syntaxutils:unrewrite	(Lgnu/expr/Expression;)Ljava/lang/Object;
    //   411: invokestatic 463	gnu/lists/LList:list1	(Ljava/lang/Object;)Lgnu/lists/Pair;
    //   414: astore_0
    //   415: goto -68 -> 347
    //   418: astore_1
    //   419: new 371	gnu/mapping/WrongType
    //   422: dup
    //   423: aload_1
    //   424: ldc_w 465
    //   427: bipush -2
    //   429: aload_0
    //   430: invokespecial 376	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   433: athrow
    //   434: astore_1
    //   435: new 371	gnu/mapping/WrongType
    //   438: dup
    //   439: aload_1
    //   440: ldc_w 465
    //   443: bipush -2
    //   445: aload_0
    //   446: invokespecial 376	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   449: athrow
    //   450: astore_1
    //   451: new 371	gnu/mapping/WrongType
    //   454: dup
    //   455: aload_1
    //   456: ldc_w 465
    //   459: bipush -2
    //   461: aload_0
    //   462: invokespecial 376	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   465: athrow
    //   466: astore_1
    //   467: new 371	gnu/mapping/WrongType
    //   470: dup
    //   471: aload_1
    //   472: ldc_w 465
    //   475: bipush -2
    //   477: aload_0
    //   478: invokespecial 376	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   481: athrow
    //   482: astore_1
    //   483: new 371	gnu/mapping/WrongType
    //   486: dup
    //   487: aload_1
    //   488: ldc_w 465
    //   491: bipush -2
    //   493: aload_0
    //   494: invokespecial 376	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   497: athrow
    //   498: astore_1
    //   499: new 371	gnu/mapping/WrongType
    //   502: dup
    //   503: aload_1
    //   504: ldc_w 465
    //   507: bipush -2
    //   509: aload_0
    //   510: invokespecial 376	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   513: athrow
    //   514: astore_1
    //   515: new 371	gnu/mapping/WrongType
    //   518: dup
    //   519: aload_1
    //   520: ldc_w 465
    //   523: bipush -2
    //   525: aload_0
    //   526: invokespecial 376	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   529: athrow
    //   530: astore_1
    //   531: new 371	gnu/mapping/WrongType
    //   534: dup
    //   535: aload_1
    //   536: ldc_w 465
    //   539: bipush -2
    //   541: aload_0
    //   542: invokespecial 376	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   545: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	546	0	paramExpression	Expression
    //   7	347	1	localObject1	Object
    //   418	6	1	localClassCastException1	ClassCastException
    //   434	6	1	localClassCastException2	ClassCastException
    //   450	6	1	localClassCastException3	ClassCastException
    //   466	6	1	localClassCastException4	ClassCastException
    //   482	6	1	localClassCastException5	ClassCastException
    //   498	6	1	localClassCastException6	ClassCastException
    //   514	6	1	localClassCastException7	ClassCastException
    //   530	6	1	localClassCastException8	ClassCastException
    //   124	240	2	localObject2	Object
    //   128	246	3	localObject3	Object
    //   309	25	4	localIfExp	gnu.expr.IfExp
    // Exception table:
    //   from	to	target	type
    //   15	20	418	java/lang/ClassCastException
    //   34	39	434	java/lang/ClassCastException
    //   51	56	450	java/lang/ClassCastException
    //   120	125	466	java/lang/ClassCastException
    //   233	238	482	java/lang/ClassCastException
    //   250	255	498	java/lang/ClassCastException
    //   267	272	514	java/lang/ClassCastException
    //   305	311	530	java/lang/ClassCastException
  }
  
  static Object unrewrite$St(Expression[] paramArrayOfExpression)
  {
    frame0 localframe0 = new frame0();
    localframe0.pack = LList.Empty;
    int i = paramArrayOfExpression.length;
    for (Object localObject = Lit7; Scheme.numEqu.apply2(localObject, Integer.valueOf(i)) == Boolean.FALSE; localObject = AddOp.$Pl.apply2(localObject, Lit8)) {
      localframe0.pack = lists.cons(unrewrite(paramArrayOfExpression[((Number)localObject).intValue()]), localframe0.pack);
    }
    return lists.reverse$Ex(localframe0.pack);
  }
  
  static Object unrewriteApply(ApplyExp paramApplyExp)
  {
    Expression localExpression = paramApplyExp.getFunction();
    Object localObject2 = unrewrite$St(paramApplyExp.getArgs());
    if ((localExpression instanceof ReferenceExp)) {}
    try
    {
      Object localObject1 = (ReferenceExp)localExpression;
      localObject1 = ((ReferenceExp)localObject1).getBinding();
      Declaration localDeclaration = Declaration.getDeclarationFromStatic("kawa.standard.Scheme", "applyToArgs");
      paramApplyExp = paramApplyExp.getFunctionValue();
      int i;
      if (localObject1 == null)
      {
        i = 1;
        label53:
        i = i + 1 & 0x1;
        if (i == 0) {
          break label173;
        }
        if (localDeclaration != null) {
          break label128;
        }
        i = 1;
        label74:
        i = i + 1 & 0x1;
        if (i == 0) {
          break label134;
        }
        if (SlotGet.getSlotValue(false, localObject1, "field", "field", "getField", "isField", Scheme.instance) != localDeclaration.field) {
          break label139;
        }
      }
      label128:
      label134:
      while (i != 0)
      {
        return localObject2;
        localObject1 = null;
        break;
        i = 0;
        break label53;
        i = 0;
        break label74;
      }
      label139:
      if ((paramApplyExp instanceof Convert)) {
        paramApplyExp = Quote.append$V(new Object[] { Lit11, localObject2 });
      }
      for (;;)
      {
        if (paramApplyExp == Boolean.FALSE) {
          break label215;
        }
        return paramApplyExp;
        label173:
        if (i == 0) {
          break;
        }
        return localObject2;
        if ((paramApplyExp instanceof GetNamedPart)) {
          paramApplyExp = Quote.append$V(new Object[] { Lit12, localObject2 });
        } else {
          paramApplyExp = Boolean.FALSE;
        }
      }
      label215:
      return Quote.consX$V(new Object[] { unrewrite(localExpression), localObject2 });
    }
    catch (ClassCastException paramApplyExp)
    {
      throw new WrongType(paramApplyExp, "fun", -2, localExpression);
    }
  }
  
  static Object unrewriteLet(LetExp paramLetExp)
  {
    frame1 localframe1 = new frame1();
    PairWithPosition localPairWithPosition = Lit9;
    localframe1.pack = LList.Empty;
    Declaration localDeclaration = paramLetExp.firstDecl();
    for (Object localObject = Lit7; localDeclaration != null; localObject = AddOp.$Pl.apply2(localObject, Lit8))
    {
      localframe1.pack = lists.cons(LList.list2(localDeclaration.getSymbol(), unrewrite(paramLetExp.inits[((Number)localObject).intValue()])), localframe1.pack);
      localDeclaration = localDeclaration.nextDecl();
    }
    return Quote.append$V(new Object[] { localPairWithPosition, Quote.consX$V(new Object[] { lists.reverse$Ex(localframe1.pack), Quote.consX$V(new Object[] { unrewrite(paramLetExp.body), LList.Empty }) }) });
  }
  
  static Object unrewriteQuote(QuoteExp paramQuoteExp)
  {
    localObject = paramQuoteExp.getValue();
    if (Numeric.asNumericOrNull(localObject) != null) {}
    label52:
    do
    {
      do
      {
        try
        {
          paramQuoteExp = LangObjType.coerceNumeric(localObject);
          return paramQuoteExp;
        }
        catch (ClassCastException paramQuoteExp)
        {
          throw new WrongType(paramQuoteExp, "val", -2, localObject);
        }
        if ((localObject instanceof Boolean)) {}
        try
        {
          paramQuoteExp = Boolean.FALSE;
          int i;
          if (localObject != paramQuoteExp)
          {
            i = 1;
            if (i == 0) {
              break label52;
            }
          }
          for (paramQuoteExp = Boolean.TRUE;; paramQuoteExp = Boolean.FALSE)
          {
            return paramQuoteExp;
            i = 0;
            break;
          }
          if (!(localObject instanceof Char)) {}
        }
        catch (ClassCastException paramQuoteExp)
        {
          throw new WrongType(paramQuoteExp, "val", -2, localObject);
        }
        try
        {
          paramQuoteExp = (Char)localObject;
          return paramQuoteExp;
        }
        catch (ClassCastException paramQuoteExp)
        {
          throw new WrongType(paramQuoteExp, "val", -2, localObject);
        }
        if ((localObject instanceof Keyword)) {}
        try
        {
          paramQuoteExp = (Keyword)localObject;
          return paramQuoteExp;
        }
        catch (ClassCastException paramQuoteExp)
        {
          throw new WrongType(paramQuoteExp, "val", -2, localObject);
        }
        if ((localObject instanceof CharSequence)) {}
        try
        {
          paramQuoteExp = (CharSequence)localObject;
          return paramQuoteExp;
        }
        catch (ClassCastException paramQuoteExp)
        {
          throw new WrongType(paramQuoteExp, "val", -2, localObject);
        }
        paramQuoteExp = (QuoteExp)localObject;
      } while (localObject == Special.undefined);
      paramQuoteExp = (QuoteExp)localObject;
    } while (localObject == EofClass.eofValue);
    if ((localObject instanceof Type)) {}
    for (;;)
    {
      try
      {
        paramQuoteExp = (Type)localObject;
        paramQuoteExp = paramQuoteExp.getName();
        return misc.string$To$Symbol(Format.formatToString(0, new Object[] { "<~a>", paramQuoteExp }));
      }
      catch (ClassCastException paramQuoteExp)
      {
        throw new WrongType(paramQuoteExp, "val", -2, localObject);
      }
      if ((localObject instanceof Class)) {}
      try
      {
        paramQuoteExp = (Class)localObject;
        paramQuoteExp = paramQuoteExp.getName();
      }
      catch (ClassCastException paramQuoteExp)
      {
        throw new WrongType(paramQuoteExp, "val", -2, localObject);
      }
    }
    return Quote.append$V(new Object[] { Lit10, Quote.consX$V(new Object[] { localObject, LList.Empty }) });
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    if (paramModuleMethod.selector == 1)
    {
      paramModuleMethod = paramArrayOfObject[0];
      int i = paramArrayOfObject.length - 1;
      Object[] arrayOfObject = new Object[i];
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          return expand$V(paramModuleMethod, arrayOfObject);
        }
        arrayOfObject[i] = paramArrayOfObject[(i + 1)];
      }
    }
    return super.applyN(paramModuleMethod, paramArrayOfObject);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 1)
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
    LList pack;
  }
  
  public class frame0
    extends ModuleBody
  {
    LList pack;
  }
  
  public class frame1
    extends ModuleBody
  {
    LList pack;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\syntaxutils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */