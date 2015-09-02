package gnu.kawa.slib;

import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.Pattern;
import kawa.lang.Quote;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.prim_syntax;
import kawa.lib.std_syntax;
import kawa.standard.syntax_case;

public class enums
  extends ModuleBody
{
  public static final Macro $Prvt$$Pcdefine$Mnenum;
  public static final enums $instance;
  static final PairWithPosition Lit0;
  static final PairWithPosition Lit1;
  static final PairWithPosition Lit10;
  static final SimpleSymbol Lit11;
  static final SyntaxPattern Lit12;
  static final SyntaxTemplate Lit13;
  static final SyntaxTemplate Lit14;
  static final SyntaxPattern Lit15;
  static final SyntaxTemplate Lit16;
  static final SyntaxPattern Lit17;
  static final SyntaxPattern Lit18;
  static final SyntaxPattern Lit19;
  static final PairWithPosition Lit2;
  static final SyntaxTemplate Lit20;
  static final SimpleSymbol Lit21;
  static final SyntaxPattern Lit22;
  static final SyntaxTemplate Lit23;
  static final SimpleSymbol Lit24;
  static final SyntaxTemplate Lit25;
  static final SyntaxTemplate Lit26;
  static final SyntaxTemplate Lit27;
  static final SyntaxTemplate Lit28;
  static final SyntaxTemplate Lit29;
  static final PairWithPosition Lit3;
  static final SyntaxTemplate Lit30;
  static final SyntaxTemplate Lit31;
  static final SyntaxTemplate Lit32;
  static final SyntaxTemplate Lit33;
  static final SyntaxTemplate Lit34;
  static final SyntaxTemplate Lit35;
  static final SyntaxTemplate Lit36;
  static final SyntaxTemplate Lit37;
  static final SyntaxTemplate Lit38;
  static final SyntaxTemplate Lit39;
  static final PairWithPosition Lit4;
  static final SimpleSymbol Lit40;
  static final SimpleSymbol Lit41;
  static final SimpleSymbol Lit42;
  static final SimpleSymbol Lit43;
  static final SimpleSymbol Lit44;
  static final SimpleSymbol Lit45;
  static final Keyword Lit46;
  static final SimpleSymbol Lit47;
  static final Keyword Lit48;
  static final SimpleSymbol Lit49;
  static final PairWithPosition Lit5;
  static final SimpleSymbol Lit50;
  static final SimpleSymbol Lit51;
  static final SimpleSymbol Lit52;
  static final SimpleSymbol Lit53 = (SimpleSymbol)new SimpleSymbol("final").readResolve();
  static final PairWithPosition Lit6;
  static final PairWithPosition Lit7;
  static final PairWithPosition Lit8;
  static final PairWithPosition Lit9;
  public static final Macro define$Mnenum;
  
  static
  {
    Lit52 = (SimpleSymbol)new SimpleSymbol("enum").readResolve();
    Lit51 = (SimpleSymbol)new SimpleSymbol("num").readResolve();
    Lit50 = (SimpleSymbol)new SimpleSymbol("str").readResolve();
    Lit49 = (SimpleSymbol)new SimpleSymbol("*init*").readResolve();
    Lit48 = Keyword.make("access");
    Lit47 = (SimpleSymbol)new SimpleSymbol("String").readResolve();
    Lit46 = Keyword.make("allocation");
    Lit45 = (SimpleSymbol)new SimpleSymbol("static").readResolve();
    Lit44 = (SimpleSymbol)new SimpleSymbol("java.lang.Enum").readResolve();
    Lit43 = (SimpleSymbol)new SimpleSymbol("quote").readResolve();
    Lit42 = (SimpleSymbol)new SimpleSymbol("::").readResolve();
    Lit41 = (SimpleSymbol)new SimpleSymbol("s").readResolve();
    Lit40 = (SimpleSymbol)new SimpleSymbol("valueOf").readResolve();
    Lit39 = new SyntaxTemplate("\001\001\001\003\003", "\020", new Object[0], 0);
    Lit38 = new SyntaxTemplate("\001\001\001\003\003", "\020", new Object[0], 0);
    Lit37 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit41, LList.Empty, "enums.scm", 290882) }, 0);
    Lit36 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make((SimpleSymbol)new SimpleSymbol("$lookup$").readResolve(), Pair.make(Lit44, Pair.make(Pair.make((SimpleSymbol)new SimpleSymbol("quasiquote").readResolve(), Pair.make(Lit40, LList.Empty)), LList.Empty)), "enums.scm", 290823) }, 0);
    Lit35 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit43, PairWithPosition.make(Lit45, LList.Empty, "enums.scm", 286739), "enums.scm", 286739) }, 0);
    Lit34 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit46, LList.Empty, "enums.scm", 286726) }, 0);
    Lit33 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit42, LList.Empty, "enums.scm", 282649) }, 0);
    Lit32 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit40, PairWithPosition.make(Lit41, PairWithPosition.make(Lit42, PairWithPosition.make(Lit47, LList.Empty, "enums.scm", 282642), "enums.scm", 282640), "enums.scm", 282639), "enums.scm", 282630) }, 0);
    Lit31 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit43, PairWithPosition.make(PairWithPosition.make(Lit52, PairWithPosition.make(Lit53, LList.Empty, "enums.scm", 266284), "enums.scm", 266278), LList.Empty, "enums.scm", 266278), "enums.scm", 266278) }, 0);
    Lit30 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit48, LList.Empty, "enums.scm", 266269) }, 0);
    Lit29 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit44, LList.Empty, "enums.scm", 266252) }, 0);
    Lit28 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", new Object[] { PairWithPosition.make((SimpleSymbol)new SimpleSymbol("define-simple-class").readResolve(), LList.Empty, "enums.scm", 262154) }, 0);
    Lit27 = new SyntaxTemplate("\001\001\001\003\003", "\b%#", new Object[0], 1);
    Lit26 = new SyntaxTemplate("\001\001\001\003\003", "\023", new Object[0], 0);
    Lit25 = new SyntaxTemplate("\001\001\001\003\003", "\b\035\033", new Object[0], 1);
    Lit24 = (SimpleSymbol)new SimpleSymbol("[]").readResolve();
    Lit23 = new SyntaxTemplate("\001\001\001\003\003", "\013", new Object[0], 0);
    Lit22 = new SyntaxPattern("\f\007\f\017\f\027,\r\037\030\b\b\r' \b\b", new Object[0], 5);
    Lit21 = (SimpleSymbol)new SimpleSymbol("%define-enum").readResolve();
    SimpleSymbol localSimpleSymbol = (SimpleSymbol)new SimpleSymbol("define-enum").readResolve();
    Lit11 = localSimpleSymbol;
    Lit20 = new SyntaxTemplate("\001\001\003", "\021\030\004\021\030\f\t\013\t\020\b\025\023", new Object[] { localSimpleSymbol, "findkeywords" }, 1);
    Lit19 = new SyntaxPattern("\f\007\f\017\r\027\020\b\b", new Object[0], 3);
    Lit18 = new SyntaxPattern("\f\007\f\017\b", new Object[0], 2);
    Lit17 = new SyntaxPattern("\f\007\b", new Object[0], 1);
    Lit16 = new SyntaxTemplate("\001\001\003\003", "\021\030\004\t\013\031\b\025\023\b\035\033", new Object[] { Lit21 }, 1);
    Lit15 = new SyntaxPattern("\f\007\f\002\f\017,\r\027\020\b\b\r\037\030\b\b", new Object[] { "findkeywords" }, 4);
    Lit14 = new SyntaxTemplate("\001\001\003\001\001\003", "\021\030\004\021\030\f\t\0139\t\033\t#\b\025\023\b-+", new Object[] { Lit11, "findkeywords" }, 1);
    Lit13 = new SyntaxTemplate("\001\001\003\001\001\003", "\033", new Object[0], 0);
    Lit12 = new SyntaxPattern("\f\007\f\002\f\017,\r\027\020\b\b\f\037\f'\r/(\b\b", new Object[] { "findkeywords" }, 6);
    Lit10 = PairWithPosition.make(Lit43, PairWithPosition.make(Lit45, LList.Empty, "enums.scm", 127013), "enums.scm", 127013);
    Lit9 = PairWithPosition.make(Lit46, LList.Empty, "enums.scm", 127000);
    Lit8 = PairWithPosition.make(Lit42, LList.Empty, "enums.scm", 126990);
    Lit7 = PairWithPosition.make((SimpleSymbol)new SimpleSymbol("values").readResolve(), LList.Empty, "enums.scm", 126981);
    Lit6 = PairWithPosition.make(PairWithPosition.make(Lit49, PairWithPosition.make(PairWithPosition.make(Lit50, PairWithPosition.make(Lit42, PairWithPosition.make(Lit47, LList.Empty, "enums.scm", 90133), "enums.scm", 90130), "enums.scm", 90125), PairWithPosition.make(PairWithPosition.make(Lit51, PairWithPosition.make(Lit42, PairWithPosition.make((SimpleSymbol)new SimpleSymbol("int").readResolve(), LList.Empty, "enums.scm", 90149), "enums.scm", 90146), "enums.scm", 90141), LList.Empty, "enums.scm", 90141), "enums.scm", 90125), "enums.scm", 90117), PairWithPosition.make(Lit48, PairWithPosition.make(PairWithPosition.make(Lit43, PairWithPosition.make((SimpleSymbol)new SimpleSymbol("private").readResolve(), LList.Empty, "enums.scm", 94222), "enums.scm", 94222), PairWithPosition.make(PairWithPosition.make((SimpleSymbol)new SimpleSymbol("invoke-special").readResolve(), PairWithPosition.make(Lit44, PairWithPosition.make(PairWithPosition.make((SimpleSymbol)new SimpleSymbol("this").readResolve(), LList.Empty, "enums.scm", 98340), PairWithPosition.make(PairWithPosition.make(Lit43, PairWithPosition.make(Lit49, LList.Empty, "enums.scm", 98348), "enums.scm", 98348), PairWithPosition.make(Lit50, PairWithPosition.make(Lit51, LList.Empty, "enums.scm", 98359), "enums.scm", 98355), "enums.scm", 98347), "enums.scm", 98340), "enums.scm", 98325), "enums.scm", 98309), LList.Empty, "enums.scm", 98309), "enums.scm", 94221), "enums.scm", 94213), "enums.scm", 90116);
    Lit5 = PairWithPosition.make(Keyword.make("init"), LList.Empty, "enums.scm", 73741);
    Lit4 = PairWithPosition.make(Lit43, PairWithPosition.make(PairWithPosition.make(Lit52, PairWithPosition.make(Lit53, LList.Empty, "enums.scm", 69680), "enums.scm", 69674), LList.Empty, "enums.scm", 69674), "enums.scm", 69674);
    Lit3 = PairWithPosition.make(Lit48, LList.Empty, "enums.scm", 69665);
    Lit2 = PairWithPosition.make(Lit43, PairWithPosition.make(Lit45, LList.Empty, "enums.scm", 69658), "enums.scm", 69658);
    Lit1 = PairWithPosition.make(Lit46, LList.Empty, "enums.scm", 69645);
    Lit0 = PairWithPosition.make(Lit42, LList.Empty, "enums.scm", 65549);
    $instance = new enums();
    localSimpleSymbol = Lit11;
    enums localenums = $instance;
    define$Mnenum = Macro.make(localSimpleSymbol, new ModuleMethod(localenums, 1, null, 4097), $instance);
    $Prvt$$Pcdefine$Mnenum = Macro.make(Lit21, new ModuleMethod(localenums, 2, null, 4097), $instance);
    $instance.run();
  }
  
  public enums()
  {
    ModuleInfo.register(this);
  }
  
  static Object lambda1(Object paramObject)
  {
    Object[] arrayOfObject = SyntaxPattern.allocVars(6, null);
    if (Lit12.match(paramObject, arrayOfObject, 0))
    {
      TemplateScope localTemplateScope = TemplateScope.make();
      if (std_syntax.isIdentifier(Lit13.execute(arrayOfObject, localTemplateScope)))
      {
        paramObject = TemplateScope.make();
        return Lit14.execute(arrayOfObject, (TemplateScope)paramObject);
      }
    }
    if (Lit15.match(paramObject, arrayOfObject, 0))
    {
      paramObject = TemplateScope.make();
      return Lit16.execute(arrayOfObject, (TemplateScope)paramObject);
    }
    if (Lit17.match(paramObject, arrayOfObject, 0))
    {
      if (("no enum type name given" instanceof Object[])) {}
      for (arrayOfObject = (Object[])"no enum type name given";; arrayOfObject = new Object[] { "no enum type name given" }) {
        return prim_syntax.syntaxError(paramObject, arrayOfObject);
      }
    }
    if (Lit18.match(paramObject, arrayOfObject, 0))
    {
      if (("no enum constants given" instanceof Object[])) {}
      for (arrayOfObject = (Object[])"no enum constants given";; arrayOfObject = new Object[] { "no enum constants given" }) {
        return prim_syntax.syntaxError(paramObject, arrayOfObject);
      }
    }
    if (Lit19.match(paramObject, arrayOfObject, 0))
    {
      paramObject = TemplateScope.make();
      return Lit20.execute(arrayOfObject, (TemplateScope)paramObject);
    }
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda2(Object paramObject)
  {
    Object[] arrayOfObject = SyntaxPattern.allocVars(5, null);
    Object localObject1;
    if (Lit22.match(paramObject, arrayOfObject, 0))
    {
      localObject1 = TemplateScope.make();
      localObject2 = Lit23.execute(arrayOfObject, (TemplateScope)localObject1);
    }
    try
    {
      localObject1 = (Symbol)localObject2;
      localObject3 = symbolAppend$V(new Object[] { localObject1, Lit24 });
      localObject2 = TemplateScope.make();
      localObject2 = Lit25.execute(arrayOfObject, (TemplateScope)localObject2);
    }
    catch (ClassCastException paramObject)
    {
      Object localObject3;
      Object localObject4;
      PairWithPosition localPairWithPosition;
      LList localLList;
      throw new WrongType((ClassCastException)paramObject, "t-name", -2, localObject2);
    }
    try
    {
      localObject4 = (LList)localObject2;
      lists.length((LList)localObject4);
      localObject2 = mapNames((Symbol)localObject1, (LList)localObject4, 0);
      localPairWithPosition = makeInit();
      localObject3 = makeValues((Symbol)localObject3, (LList)localObject4);
      localObject4 = TemplateScope.make();
      localObject5 = Lit26.execute(arrayOfObject, (TemplateScope)localObject4);
    }
    catch (ClassCastException paramObject)
    {
      throw new WrongType((ClassCastException)paramObject, "e-names", -2, localObject2);
    }
    try
    {
      localObject4 = (LList)localObject5;
      localObject5 = TemplateScope.make();
      localObject5 = Lit27.execute(arrayOfObject, (TemplateScope)localObject5);
    }
    catch (ClassCastException paramObject)
    {
      throw new WrongType((ClassCastException)paramObject, "opts", -2, localObject5);
    }
    try
    {
      localLList = (LList)localObject5;
      localObject5 = TemplateScope.make();
      return Quote.append$V(new Object[] { Lit28.execute(arrayOfObject, (TemplateScope)localObject5), Quote.consX$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, localObject1), Pair.make(Lit29.execute(arrayOfObject, (TemplateScope)localObject5), Quote.append$V(new Object[] { Lit30.execute(arrayOfObject, (TemplateScope)localObject5), Pair.make(Lit31.execute(arrayOfObject, (TemplateScope)localObject5), Quote.append$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, localObject4), Quote.consX$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, localPairWithPosition), Quote.consX$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, localObject3), Pair.make(Pair.make(Lit32.execute(arrayOfObject, (TemplateScope)localObject5), Quote.append$V(new Object[] { Lit33.execute(arrayOfObject, (TemplateScope)localObject5), Quote.consX$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, localObject1), Quote.append$V(new Object[] { Lit34.execute(arrayOfObject, (TemplateScope)localObject5), Pair.make(Lit35.execute(arrayOfObject, (TemplateScope)localObject5), Pair.make(Pair.make(Lit36.execute(arrayOfObject, (TemplateScope)localObject5), Quote.consX$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, localObject1), Lit37.execute(arrayOfObject, (TemplateScope)localObject5) })), Lit38.execute(arrayOfObject, (TemplateScope)localObject5))) }) }) })), Quote.append$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, localObject2), Quote.append$V(new Object[] { std_syntax.datum$To$SyntaxObject(paramObject, localLList), Lit39.execute(arrayOfObject, (TemplateScope)localObject5) }) })) }) }) })) })) }) });
    }
    catch (ClassCastException paramObject)
    {
      throw new WrongType((ClassCastException)paramObject, "other-defs", -2, localObject5);
    }
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object makeFieldDesc(Symbol paramSymbol1, Symbol paramSymbol2, int paramInt)
  {
    return Quote.consX$V(new Object[] { paramSymbol2, Quote.append$V(new Object[] { Lit0, Quote.consX$V(new Object[] { paramSymbol1, Quote.append$V(new Object[] { Lit1, Pair.make(Lit2, Quote.append$V(new Object[] { Lit3, Pair.make(Lit4, Quote.append$V(new Object[] { Lit5, Pair.make(Quote.consX$V(new Object[] { paramSymbol1, Quote.consX$V(new Object[] { misc.symbol$To$String(paramSymbol2), Quote.consX$V(new Object[] { Integer.valueOf(paramInt), LList.Empty }) }) }), LList.Empty) })) })) }) }) }) });
  }
  
  static PairWithPosition makeInit()
  {
    return Lit6;
  }
  
  static Pair makeValues(Symbol paramSymbol, LList paramLList)
  {
    return Pair.make(Lit7, Quote.append$V(new Object[] { Lit8, Quote.consX$V(new Object[] { paramSymbol, Quote.append$V(new Object[] { Lit9, Pair.make(Lit10, Pair.make(Quote.consX$V(new Object[] { paramSymbol, Quote.append$V(new Object[] { paramLList, LList.Empty }) }), LList.Empty)) }) }) }));
  }
  
  /* Error */
  static LList mapNames(Symbol paramSymbol, LList paramLList, int paramInt)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 506	kawa/lib/lists:isNull	(Ljava/lang/Object;)Z
    //   4: ifeq +7 -> 11
    //   7: getstatic 165	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   10: areturn
    //   11: getstatic 510	kawa/lib/lists:car	Lgnu/expr/GenericProc;
    //   14: aload_1
    //   15: invokevirtual 515	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   18: astore_3
    //   19: aload_3
    //   20: checkcast 442	gnu/mapping/Symbol
    //   23: astore 4
    //   25: aload_0
    //   26: aload 4
    //   28: iload_2
    //   29: invokestatic 517	gnu/kawa/slib/enums:makeFieldDesc	(Lgnu/mapping/Symbol;Lgnu/mapping/Symbol;I)Ljava/lang/Object;
    //   32: astore_3
    //   33: getstatic 520	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   36: aload_1
    //   37: invokevirtual 515	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   40: astore_1
    //   41: aload_1
    //   42: checkcast 161	gnu/lists/LList
    //   45: astore 4
    //   47: aload_3
    //   48: aload_0
    //   49: aload 4
    //   51: iload_2
    //   52: iconst_1
    //   53: iadd
    //   54: invokestatic 456	gnu/kawa/slib/enums:mapNames	(Lgnu/mapping/Symbol;Lgnu/lists/LList;I)Lgnu/lists/LList;
    //   57: invokestatic 523	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   60: areturn
    //   61: astore_0
    //   62: new 479	gnu/mapping/WrongType
    //   65: dup
    //   66: aload_0
    //   67: ldc_w 525
    //   70: iconst_1
    //   71: aload_3
    //   72: invokespecial 484	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   75: athrow
    //   76: astore_0
    //   77: new 479	gnu/mapping/WrongType
    //   80: dup
    //   81: aload_0
    //   82: ldc_w 527
    //   85: iconst_1
    //   86: aload_1
    //   87: invokespecial 484	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	paramSymbol	Symbol
    //   0	91	1	paramLList	LList
    //   0	91	2	paramInt	int
    //   18	54	3	localObject1	Object
    //   23	27	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   19	25	61	java/lang/ClassCastException
    //   41	47	76	java/lang/ClassCastException
  }
  
  /* Error */
  static SimpleSymbol symbolAppend$V(Object[] paramArrayOfObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: invokestatic 531	gnu/lists/LList:makeList	([Ljava/lang/Object;I)Lgnu/lists/LList;
    //   5: astore_1
    //   6: getstatic 537	kawa/standard/Scheme:apply	Lgnu/kawa/functions/Apply;
    //   9: astore_2
    //   10: getstatic 543	kawa/lib/strings:string$Mnappend	Lgnu/expr/ModuleMethod;
    //   13: astore_3
    //   14: getstatic 165	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   17: astore_0
    //   18: aload_1
    //   19: getstatic 165	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   22: if_acmpne +23 -> 45
    //   25: aload_2
    //   26: aload_3
    //   27: aload_0
    //   28: invokestatic 547	gnu/lists/LList:reverseInPlace	(Ljava/lang/Object;)Lgnu/lists/LList;
    //   31: invokevirtual 550	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   34: astore_0
    //   35: aload_0
    //   36: checkcast 552	java/lang/CharSequence
    //   39: astore_1
    //   40: aload_1
    //   41: invokestatic 556	kawa/lib/misc:string$To$Symbol	(Ljava/lang/CharSequence;)Lgnu/mapping/SimpleSymbol;
    //   44: areturn
    //   45: aload_1
    //   46: checkcast 181	gnu/lists/Pair
    //   49: astore 4
    //   51: aload 4
    //   53: invokevirtual 559	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   56: astore_1
    //   57: aload 4
    //   59: invokevirtual 562	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   62: astore 4
    //   64: aload 4
    //   66: checkcast 442	gnu/mapping/Symbol
    //   69: astore 5
    //   71: aload 5
    //   73: invokestatic 498	kawa/lib/misc:symbol$To$String	(Lgnu/mapping/Symbol;)Ljava/lang/String;
    //   76: aload_0
    //   77: invokestatic 184	gnu/lists/Pair:make	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   80: astore_0
    //   81: goto -63 -> 18
    //   84: astore_0
    //   85: new 479	gnu/mapping/WrongType
    //   88: dup
    //   89: aload_0
    //   90: ldc_w 564
    //   93: bipush -2
    //   95: aload_1
    //   96: invokespecial 484	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   99: athrow
    //   100: astore_0
    //   101: new 479	gnu/mapping/WrongType
    //   104: dup
    //   105: aload_0
    //   106: ldc_w 566
    //   109: iconst_1
    //   110: aload 4
    //   112: invokespecial 484	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   115: athrow
    //   116: astore_1
    //   117: new 479	gnu/mapping/WrongType
    //   120: dup
    //   121: aload_1
    //   122: ldc_w 568
    //   125: iconst_1
    //   126: aload_0
    //   127: invokespecial 484	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   130: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	paramArrayOfObject	Object[]
    //   5	91	1	localObject1	Object
    //   116	6	1	localClassCastException	ClassCastException
    //   9	17	2	localApply	gnu.kawa.functions.Apply
    //   13	14	3	localModuleMethod	ModuleMethod
    //   49	62	4	localObject2	Object
    //   69	3	5	localSymbol	Symbol
    // Exception table:
    //   from	to	target	type
    //   45	51	84	java/lang/ClassCastException
    //   64	71	100	java/lang/ClassCastException
    //   35	40	116	java/lang/ClassCastException
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 1: 
      return lambda1(paramObject);
    }
    return lambda2(paramObject);
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
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\enums.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */