package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.IsEqual;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import kawa.lang.Eval;
import kawa.lang.Macro;
import kawa.lang.Pattern;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.parameters;
import kawa.lib.ports;
import kawa.lib.std_syntax;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.readchar;
import kawa.standard.syntax_case;

public class testing
  extends ModuleBody
{
  public static final ModuleMethod $Pctest$Mnbegin;
  static final ModuleMethod $Pctest$Mnnull$Mncallback;
  public static final ModuleMethod $Prvt$$Pctest$Mnapproximimate$Eq;
  public static final ModuleMethod $Prvt$$Pctest$Mnas$Mnspecifier;
  public static final Macro $Prvt$$Pctest$Mncomp1body;
  public static final Macro $Prvt$$Pctest$Mncomp2body;
  public static final ModuleMethod $Prvt$$Pctest$Mnend;
  public static final Macro $Prvt$$Pctest$Mnerror;
  public static final Macro $Prvt$$Pctest$Mnevaluate$Mnwith$Mncatch;
  public static final ModuleMethod $Prvt$$Pctest$Mnmatch$Mnall;
  public static final ModuleMethod $Prvt$$Pctest$Mnmatch$Mnany;
  public static final ModuleMethod $Prvt$$Pctest$Mnmatch$Mnnth;
  public static final ModuleMethod $Prvt$$Pctest$Mnon$Mntest$Mnbegin;
  public static final ModuleMethod $Prvt$$Pctest$Mnon$Mntest$Mnend;
  public static final ModuleMethod $Prvt$$Pctest$Mnreport$Mnresult;
  public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist;
  public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist$Ex;
  public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist;
  public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist$Ex;
  public static final ModuleMethod $Prvt$$Pctest$Mnshould$Mnexecute;
  public static final Macro $Prvt$test$Mngroup;
  public static final testing $instance;
  static final IntNum Lit0;
  static final SimpleSymbol Lit1;
  static final PairWithPosition Lit10;
  static final SyntaxPattern Lit100;
  static final SyntaxTemplate Lit101;
  static final SyntaxPattern Lit102;
  static final SyntaxTemplate Lit103;
  static final SimpleSymbol Lit104;
  static final SyntaxTemplate Lit105;
  static final SimpleSymbol Lit106;
  static final SyntaxTemplate Lit107;
  static final SimpleSymbol Lit108;
  static final SyntaxTemplate Lit109;
  static final PairWithPosition Lit11;
  static final SimpleSymbol Lit110;
  static final SyntaxPattern Lit111;
  static final SyntaxTemplate Lit112;
  static final SyntaxPattern Lit113;
  static final SyntaxTemplate Lit114;
  static final SimpleSymbol Lit115;
  static final SyntaxRules Lit116;
  static final SimpleSymbol Lit117;
  static final SyntaxPattern Lit118;
  static final SyntaxTemplate Lit119;
  static final SimpleSymbol Lit12;
  static final SyntaxPattern Lit120;
  static final SyntaxTemplate Lit121;
  static final SyntaxPattern Lit122;
  static final SyntaxTemplate Lit123;
  static final SimpleSymbol Lit124;
  static final SimpleSymbol Lit125;
  static final SyntaxRules Lit126;
  static final SimpleSymbol Lit127;
  static final SimpleSymbol Lit128;
  static final SyntaxRules Lit129;
  static final IntNum Lit13;
  static final SimpleSymbol Lit130;
  static final SimpleSymbol Lit131;
  static final SyntaxRules Lit132;
  static final SimpleSymbol Lit133;
  static final SimpleSymbol Lit134;
  static final SyntaxRules Lit135;
  static final SimpleSymbol Lit136;
  static final SimpleSymbol Lit137;
  static final SyntaxRules Lit138;
  static final SimpleSymbol Lit139;
  static final SimpleSymbol Lit14;
  static final SyntaxRules Lit140;
  static final SimpleSymbol Lit141;
  static final SimpleSymbol Lit142;
  static final SimpleSymbol Lit143;
  static final SimpleSymbol Lit144;
  static final SimpleSymbol Lit145;
  static final SimpleSymbol Lit146;
  static final SimpleSymbol Lit147;
  static final SimpleSymbol Lit148;
  static final SimpleSymbol Lit149;
  static final SimpleSymbol Lit15;
  static final SimpleSymbol Lit150;
  static final SimpleSymbol Lit151;
  static final SimpleSymbol Lit152;
  static final SimpleSymbol Lit153;
  static final SimpleSymbol Lit154;
  static final SimpleSymbol Lit155;
  static final SimpleSymbol Lit156;
  static final SimpleSymbol Lit157;
  static final SimpleSymbol Lit158;
  static final SimpleSymbol Lit159;
  static final SyntaxPattern Lit16;
  static final SimpleSymbol Lit160;
  static final SimpleSymbol Lit161;
  static final SimpleSymbol Lit162;
  static final SimpleSymbol Lit163;
  static final SimpleSymbol Lit164;
  static final SimpleSymbol Lit165 = (SimpleSymbol)new SimpleSymbol("dynamic-wind").readResolve();
  static final SyntaxTemplate Lit17;
  static final SyntaxPattern Lit18;
  static final SyntaxTemplate Lit19;
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
  static final SimpleSymbol Lit35;
  static final SimpleSymbol Lit36;
  static final SimpleSymbol Lit37;
  static final SimpleSymbol Lit38;
  static final SimpleSymbol Lit39;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit40;
  static final SimpleSymbol Lit41;
  static final SimpleSymbol Lit42;
  static final SimpleSymbol Lit43;
  static final SimpleSymbol Lit44;
  static final SimpleSymbol Lit45;
  static final SimpleSymbol Lit46;
  static final SimpleSymbol Lit47;
  static final SimpleSymbol Lit48;
  static final SimpleSymbol Lit49;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit50;
  static final SimpleSymbol Lit51;
  static final SimpleSymbol Lit52;
  static final SimpleSymbol Lit53;
  static final SimpleSymbol Lit54;
  static final SimpleSymbol Lit55;
  static final SimpleSymbol Lit56;
  static final SimpleSymbol Lit57;
  static final SimpleSymbol Lit58;
  static final SimpleSymbol Lit59;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit60;
  static final SimpleSymbol Lit61;
  static final SimpleSymbol Lit62;
  static final SimpleSymbol Lit63;
  static final SimpleSymbol Lit64;
  static final SimpleSymbol Lit65;
  static final SimpleSymbol Lit66;
  static final SimpleSymbol Lit67;
  static final SimpleSymbol Lit68;
  static final SimpleSymbol Lit69;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit70;
  static final SyntaxRules Lit71;
  static final SimpleSymbol Lit72;
  static final SyntaxRules Lit73;
  static final SimpleSymbol Lit74;
  static final SimpleSymbol Lit75;
  static final SyntaxRules Lit76;
  static final SimpleSymbol Lit77;
  static final SimpleSymbol Lit78;
  static final SimpleSymbol Lit79;
  static final PairWithPosition Lit8;
  static final SimpleSymbol Lit80;
  static final SimpleSymbol Lit81;
  static final SimpleSymbol Lit82;
  static final SimpleSymbol Lit83;
  static final SimpleSymbol Lit84;
  static final SyntaxRules Lit85;
  static final SimpleSymbol Lit86;
  static final SimpleSymbol Lit87;
  static final SimpleSymbol Lit88;
  static final SimpleSymbol Lit89;
  static final SimpleSymbol Lit9;
  static final SyntaxRules Lit90;
  static final SimpleSymbol Lit91;
  static final SimpleSymbol Lit92;
  static final SyntaxRules Lit93;
  static final SimpleSymbol Lit94;
  static final SyntaxPattern Lit95;
  static final SyntaxTemplate Lit96;
  static final SyntaxPattern Lit97;
  static final SyntaxTemplate Lit98;
  static final SimpleSymbol Lit99;
  static final ModuleMethod lambda$Fn1;
  static final ModuleMethod lambda$Fn2;
  static final ModuleMethod lambda$Fn3;
  public static final ModuleMethod test$Mnapply;
  public static final Macro test$Mnapproximate;
  public static final Macro test$Mnassert;
  public static final Macro test$Mnend;
  public static final Macro test$Mneq;
  public static final Macro test$Mnequal;
  public static final Macro test$Mneqv;
  public static final Macro test$Mnerror;
  public static final Macro test$Mnexpect$Mnfail;
  public static final Macro test$Mngroup$Mnwith$Mncleanup;
  public static Boolean test$Mnlog$Mnto$Mnfile;
  public static final Macro test$Mnmatch$Mnall;
  public static final Macro test$Mnmatch$Mnany;
  public static final ModuleMethod test$Mnmatch$Mnname;
  public static final Macro test$Mnmatch$Mnnth;
  public static final ModuleMethod test$Mnon$Mnbad$Mncount$Mnsimple;
  public static final ModuleMethod test$Mnon$Mnbad$Mnend$Mnname$Mnsimple;
  public static final ModuleMethod test$Mnon$Mnfinal$Mnsimple;
  public static final ModuleMethod test$Mnon$Mngroup$Mnbegin$Mnsimple;
  public static final ModuleMethod test$Mnon$Mngroup$Mnend$Mnsimple;
  static final ModuleMethod test$Mnon$Mntest$Mnbegin$Mnsimple;
  public static final ModuleMethod test$Mnon$Mntest$Mnend$Mnsimple;
  public static final ModuleMethod test$Mnpassed$Qu;
  public static final ModuleMethod test$Mnread$Mneval$Mnstring;
  public static final ModuleMethod test$Mnresult$Mnalist;
  public static final ModuleMethod test$Mnresult$Mnalist$Ex;
  public static final ModuleMethod test$Mnresult$Mnclear;
  public static final ModuleMethod test$Mnresult$Mnkind;
  public static final Macro test$Mnresult$Mnref;
  public static final ModuleMethod test$Mnresult$Mnremove;
  public static final ModuleMethod test$Mnresult$Mnset$Ex;
  static final Class test$Mnrunner;
  public static final ModuleMethod test$Mnrunner$Mnaux$Mnvalue;
  public static final ModuleMethod test$Mnrunner$Mnaux$Mnvalue$Ex;
  public static final ModuleMethod test$Mnrunner$Mncreate;
  public static Object test$Mnrunner$Mncurrent;
  public static Object test$Mnrunner$Mnfactory;
  public static final ModuleMethod test$Mnrunner$Mnfail$Mncount;
  public static final ModuleMethod test$Mnrunner$Mnfail$Mncount$Ex;
  public static final ModuleMethod test$Mnrunner$Mnget;
  public static final ModuleMethod test$Mnrunner$Mngroup$Mnpath;
  public static final ModuleMethod test$Mnrunner$Mngroup$Mnstack;
  public static final ModuleMethod test$Mnrunner$Mngroup$Mnstack$Ex;
  public static final ModuleMethod test$Mnrunner$Mnnull;
  public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mncount;
  public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mncount$Ex;
  public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mnend$Mnname;
  public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mnend$Mnname$Ex;
  public static final ModuleMethod test$Mnrunner$Mnon$Mnfinal;
  public static final ModuleMethod test$Mnrunner$Mnon$Mnfinal$Ex;
  public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnbegin;
  public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnbegin$Ex;
  public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnend;
  public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnend$Ex;
  public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnbegin;
  public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnbegin$Ex;
  public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnend;
  public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnend$Ex;
  public static final ModuleMethod test$Mnrunner$Mnpass$Mncount;
  public static final ModuleMethod test$Mnrunner$Mnpass$Mncount$Ex;
  public static final ModuleMethod test$Mnrunner$Mnreset;
  public static final ModuleMethod test$Mnrunner$Mnsimple;
  public static final ModuleMethod test$Mnrunner$Mnskip$Mncount;
  public static final ModuleMethod test$Mnrunner$Mnskip$Mncount$Ex;
  public static final ModuleMethod test$Mnrunner$Mntest$Mnname;
  public static final ModuleMethod test$Mnrunner$Mnxfail$Mncount;
  public static final ModuleMethod test$Mnrunner$Mnxfail$Mncount$Ex;
  public static final ModuleMethod test$Mnrunner$Mnxpass$Mncount;
  public static final ModuleMethod test$Mnrunner$Mnxpass$Mncount$Ex;
  public static final ModuleMethod test$Mnrunner$Qu;
  public static final Macro test$Mnskip;
  public static final Macro test$Mnwith$Mnrunner;
  
  static Object $PcTestAnySpecifierMatches(Object paramObject1, Object paramObject2)
  {
    Boolean localBoolean = Boolean.FALSE;
    for (;;)
    {
      if (lists.isNull(paramObject1)) {
        return localBoolean;
      }
      if ($PcTestSpecificierMatches(lists.car.apply1(paramObject1), paramObject2) != Boolean.FALSE) {
        localBoolean = Boolean.TRUE;
      }
      paramObject1 = lists.cdr.apply1(paramObject1);
    }
  }
  
  public static Procedure $PcTestApproximimate$Eq(Object paramObject)
  {
    frame0 localframe0 = new frame0();
    localframe0.error = paramObject;
    return localframe0.lambda$Fn4;
  }
  
  public static Object $PcTestAsSpecifier(Object paramObject)
  {
    if (misc.isProcedure(paramObject)) {
      return paramObject;
    }
    if (numbers.isInteger(paramObject)) {
      return $PcTestMatchNth(Lit13, paramObject);
    }
    if (strings.isString(paramObject)) {
      return testMatchName(paramObject);
    }
    return misc.error$V("not a valid test specifier", new Object[0]);
  }
  
  /* Error */
  public static void $PcTestBegin(Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: getstatic 387	gnu/kawa/slib/testing:test$Mnrunner$Mncurrent	Ljava/lang/Object;
    //   3: checkcast 322	gnu/mapping/Procedure
    //   6: invokevirtual 391	gnu/mapping/Procedure:apply0	()Ljava/lang/Object;
    //   9: getstatic 310	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   12: if_acmpne +16 -> 28
    //   15: getstatic 387	gnu/kawa/slib/testing:test$Mnrunner$Mncurrent	Ljava/lang/Object;
    //   18: checkcast 322	gnu/mapping/Procedure
    //   21: invokestatic 394	gnu/kawa/slib/testing:testRunnerCreate	()Ljava/lang/Object;
    //   24: invokevirtual 326	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   27: pop
    //   28: getstatic 387	gnu/kawa/slib/testing:test$Mnrunner$Mncurrent	Ljava/lang/Object;
    //   31: checkcast 322	gnu/mapping/Procedure
    //   34: invokevirtual 391	gnu/mapping/Procedure:apply0	()Ljava/lang/Object;
    //   37: astore_2
    //   38: getstatic 400	kawa/standard/Scheme:applyToArgs	Lgnu/kawa/functions/ApplyToArgs;
    //   41: astore_3
    //   42: aload_2
    //   43: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   46: astore 4
    //   48: aload_3
    //   49: aload 4
    //   51: invokestatic 404	gnu/kawa/slib/testing:testRunnerOnGroupBegin	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   54: aload_2
    //   55: aload_0
    //   56: aload_1
    //   57: invokevirtual 408	gnu/mapping/Procedure:apply4	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   60: pop
    //   61: aload_2
    //   62: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   65: astore_3
    //   66: aload_2
    //   67: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   70: astore 4
    //   72: aload 4
    //   74: invokestatic 411	gnu/kawa/slib/testing:$PcTestRunnerSkipList	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   77: astore 4
    //   79: aload_2
    //   80: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   83: astore 5
    //   85: aload_3
    //   86: aload 4
    //   88: aload 5
    //   90: invokestatic 414	gnu/kawa/slib/testing:$PcTestRunnerSkipSave	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   93: invokestatic 418	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   96: invokestatic 422	gnu/kawa/slib/testing:$PcTestRunnerSkipSave$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   99: aload_2
    //   100: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   103: astore_3
    //   104: aload_2
    //   105: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   108: astore 4
    //   110: aload 4
    //   112: invokestatic 425	gnu/kawa/slib/testing:$PcTestRunnerFailList	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   115: astore 4
    //   117: aload_2
    //   118: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   121: astore 5
    //   123: aload_3
    //   124: aload 4
    //   126: aload 5
    //   128: invokestatic 428	gnu/kawa/slib/testing:$PcTestRunnerFailSave	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   131: invokestatic 418	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   134: invokestatic 431	gnu/kawa/slib/testing:$PcTestRunnerFailSave$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   137: aload_2
    //   138: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   141: astore_3
    //   142: aload_2
    //   143: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   146: astore 4
    //   148: aload 4
    //   150: invokestatic 434	gnu/kawa/slib/testing:$PcTestRunnerTotalCount	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   153: aload_1
    //   154: invokestatic 418	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   157: astore_1
    //   158: aload_2
    //   159: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   162: astore 4
    //   164: aload_3
    //   165: aload_1
    //   166: aload 4
    //   168: invokestatic 437	gnu/kawa/slib/testing:$PcTestRunnerCountList	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   171: invokestatic 418	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   174: invokestatic 440	gnu/kawa/slib/testing:$PcTestRunnerCountList$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   177: aload_2
    //   178: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   181: astore_1
    //   182: aload_2
    //   183: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   186: astore_3
    //   187: aload_1
    //   188: aload_0
    //   189: aload_3
    //   190: invokestatic 443	gnu/kawa/slib/testing:testRunnerGroupStack	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   193: invokestatic 418	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   196: invokestatic 446	gnu/kawa/slib/testing:testRunnerGroupStack$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   199: return
    //   200: astore_0
    //   201: new 448	gnu/mapping/WrongType
    //   204: dup
    //   205: aload_0
    //   206: ldc_w 450
    //   209: iconst_0
    //   210: aload_2
    //   211: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   214: athrow
    //   215: astore_0
    //   216: new 448	gnu/mapping/WrongType
    //   219: dup
    //   220: aload_0
    //   221: ldc_w 455
    //   224: iconst_0
    //   225: aload_2
    //   226: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   229: athrow
    //   230: astore_0
    //   231: new 448	gnu/mapping/WrongType
    //   234: dup
    //   235: aload_0
    //   236: ldc_w 457
    //   239: iconst_0
    //   240: aload_2
    //   241: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   244: athrow
    //   245: astore_0
    //   246: new 448	gnu/mapping/WrongType
    //   249: dup
    //   250: aload_0
    //   251: ldc_w 459
    //   254: iconst_0
    //   255: aload_2
    //   256: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   259: athrow
    //   260: astore_0
    //   261: new 448	gnu/mapping/WrongType
    //   264: dup
    //   265: aload_0
    //   266: ldc_w 461
    //   269: iconst_0
    //   270: aload_2
    //   271: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   274: athrow
    //   275: astore_0
    //   276: new 448	gnu/mapping/WrongType
    //   279: dup
    //   280: aload_0
    //   281: ldc_w 463
    //   284: iconst_0
    //   285: aload_2
    //   286: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   289: athrow
    //   290: astore_0
    //   291: new 448	gnu/mapping/WrongType
    //   294: dup
    //   295: aload_0
    //   296: ldc_w 465
    //   299: iconst_0
    //   300: aload_2
    //   301: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   304: athrow
    //   305: astore_0
    //   306: new 448	gnu/mapping/WrongType
    //   309: dup
    //   310: aload_0
    //   311: ldc_w 467
    //   314: iconst_0
    //   315: aload_2
    //   316: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   319: athrow
    //   320: astore_0
    //   321: new 448	gnu/mapping/WrongType
    //   324: dup
    //   325: aload_0
    //   326: ldc_w 469
    //   329: iconst_0
    //   330: aload_2
    //   331: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   334: athrow
    //   335: astore_0
    //   336: new 448	gnu/mapping/WrongType
    //   339: dup
    //   340: aload_0
    //   341: ldc_w 471
    //   344: iconst_0
    //   345: aload_2
    //   346: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   349: athrow
    //   350: astore_0
    //   351: new 448	gnu/mapping/WrongType
    //   354: dup
    //   355: aload_0
    //   356: ldc_w 473
    //   359: iconst_0
    //   360: aload_2
    //   361: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   364: athrow
    //   365: astore_0
    //   366: new 448	gnu/mapping/WrongType
    //   369: dup
    //   370: aload_0
    //   371: ldc_w 475
    //   374: iconst_0
    //   375: aload_2
    //   376: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   379: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	380	0	paramObject1	Object
    //   0	380	1	paramObject2	Object
    //   37	339	2	localObject1	Object
    //   41	149	3	localObject2	Object
    //   46	121	4	localObject3	Object
    //   83	44	5	localMnrunner	test.Mnrunner
    // Exception table:
    //   from	to	target	type
    //   42	48	200	java/lang/ClassCastException
    //   61	66	215	java/lang/ClassCastException
    //   66	72	230	java/lang/ClassCastException
    //   79	85	245	java/lang/ClassCastException
    //   99	104	260	java/lang/ClassCastException
    //   104	110	275	java/lang/ClassCastException
    //   117	123	290	java/lang/ClassCastException
    //   137	142	305	java/lang/ClassCastException
    //   142	148	320	java/lang/ClassCastException
    //   158	164	335	java/lang/ClassCastException
    //   177	182	350	java/lang/ClassCastException
    //   182	187	365	java/lang/ClassCastException
  }
  
  static Object $PcTestComp2(Object paramObject1, Object paramObject2)
  {
    paramObject2 = LList.list3(paramObject2, LList.list2(Lit15, $PcTestSourceLine2(paramObject2)), paramObject1);
    paramObject1 = SyntaxPattern.allocVars(6, null);
    if (Lit16.match(paramObject2, (Object[])paramObject1, 0))
    {
      paramObject2 = TemplateScope.make();
      return Lit17.execute((Object[])paramObject1, (TemplateScope)paramObject2);
    }
    if (Lit18.match(paramObject2, (Object[])paramObject1, 0))
    {
      paramObject2 = TemplateScope.make();
      return Lit19.execute((Object[])paramObject1, (TemplateScope)paramObject2);
    }
    return syntax_case.error("syntax-case", paramObject2);
  }
  
  public static Object $PcTestEnd(Object paramObject1, Object paramObject2)
  {
    localObject1 = testRunnerGet();
    for (;;)
    {
      try
      {
        localObject2 = (test.Mnrunner)localObject1;
        localObject2 = testRunnerGroupStack((test.Mnrunner)localObject2);
        localObject3 = $PcTestFormatLine(localObject1);
      }
      catch (ClassCastException paramObject1)
      {
        Object localObject2;
        Object localObject3;
        test.Mnrunner localMnrunner;
        throw new WrongType((ClassCastException)paramObject1, "test-runner-group-stack", 0, localObject1);
      }
      try
      {
        localMnrunner = (test.Mnrunner)localObject1;
        testResultAlist$Ex(localMnrunner, paramObject2);
        if (lists.isNull(localObject2)) {
          misc.error$V(strings.stringAppend(new Object[] { localObject3, "test-end not in a group" }), new Object[0]);
        }
        if (paramObject1 != Boolean.FALSE) {
          if (!IsEqual.apply(paramObject1, lists.car.apply1(localObject2))) {
            paramObject2 = Scheme.applyToArgs;
          }
        }
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "test-result-alist!", 0, localObject1);
      }
      try
      {
        localObject3 = (test.Mnrunner)localObject1;
        ((Procedure)paramObject2).apply4(testRunnerOnBadEndName((test.Mnrunner)localObject3), localObject1, paramObject1, lists.car.apply1(localObject2));
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "test-runner-on-bad-end-name", 0, localObject1);
      }
      try
      {
        paramObject1 = (test.Mnrunner)localObject1;
        paramObject1 = $PcTestRunnerCountList((test.Mnrunner)paramObject1);
        paramObject2 = lists.cdar.apply1(paramObject1);
        localObject2 = lists.caar.apply1(paramObject1);
        localObject3 = AddOp.$Mn;
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "%test-runner-count-list", 0, localObject1);
      }
      try
      {
        localMnrunner = (test.Mnrunner)localObject1;
        localObject2 = ((Procedure)localObject3).apply2($PcTestRunnerTotalCount(localMnrunner), localObject2);
        if (paramObject2 != Boolean.FALSE) {
          if (Scheme.numEqu.apply2(paramObject2, localObject2) == Boolean.FALSE) {
            localObject3 = Scheme.applyToArgs;
          }
        }
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "%test-runner-total-count", 0, localObject1);
      }
      try
      {
        localMnrunner = (test.Mnrunner)localObject1;
        ((Procedure)localObject3).apply4(testRunnerOnBadCount(localMnrunner), localObject1, localObject2, paramObject2);
        paramObject2 = Scheme.applyToArgs;
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "test-runner-on-bad-count", 0, localObject1);
      }
      try
      {
        localObject2 = (test.Mnrunner)localObject1;
        ((Procedure)paramObject2).apply2(testRunnerOnGroupEnd((test.Mnrunner)localObject2), localObject1);
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "test-runner-on-group-end", 0, localObject1);
      }
      try
      {
        paramObject2 = (test.Mnrunner)localObject1;
        localObject2 = lists.cdr;
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "test-runner-group-stack!", 0, localObject1);
      }
      try
      {
        localObject3 = (test.Mnrunner)localObject1;
        testRunnerGroupStack$Ex((test.Mnrunner)paramObject2, ((Procedure)localObject2).apply1(testRunnerGroupStack((test.Mnrunner)localObject3)));
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "test-runner-group-stack", 0, localObject1);
      }
      try
      {
        paramObject2 = (test.Mnrunner)localObject1;
        localObject2 = lists.car;
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "%test-runner-skip-list!", 0, localObject1);
      }
      try
      {
        localObject3 = (test.Mnrunner)localObject1;
        $PcTestRunnerSkipList$Ex((test.Mnrunner)paramObject2, ((Procedure)localObject2).apply1($PcTestRunnerSkipSave((test.Mnrunner)localObject3)));
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "%test-runner-skip-save", 0, localObject1);
      }
      try
      {
        paramObject2 = (test.Mnrunner)localObject1;
        localObject2 = lists.cdr;
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "%test-runner-skip-save!", 0, localObject1);
      }
      try
      {
        localObject3 = (test.Mnrunner)localObject1;
        $PcTestRunnerSkipSave$Ex((test.Mnrunner)paramObject2, ((Procedure)localObject2).apply1($PcTestRunnerSkipSave((test.Mnrunner)localObject3)));
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "%test-runner-skip-save", 0, localObject1);
      }
      try
      {
        paramObject2 = (test.Mnrunner)localObject1;
        localObject2 = lists.car;
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "%test-runner-fail-list!", 0, localObject1);
      }
      try
      {
        localObject3 = (test.Mnrunner)localObject1;
        $PcTestRunnerFailList$Ex((test.Mnrunner)paramObject2, ((Procedure)localObject2).apply1($PcTestRunnerFailSave((test.Mnrunner)localObject3)));
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "%test-runner-fail-save", 0, localObject1);
      }
      try
      {
        paramObject2 = (test.Mnrunner)localObject1;
        localObject2 = lists.cdr;
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "%test-runner-fail-save!", 0, localObject1);
      }
      try
      {
        localObject3 = (test.Mnrunner)localObject1;
        $PcTestRunnerFailSave$Ex((test.Mnrunner)paramObject2, ((Procedure)localObject2).apply1($PcTestRunnerFailSave((test.Mnrunner)localObject3)));
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "%test-runner-fail-save", 0, localObject1);
      }
      try
      {
        paramObject2 = (test.Mnrunner)localObject1;
        $PcTestRunnerCountList$Ex((test.Mnrunner)paramObject2, lists.cdr.apply1(paramObject1));
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "%test-runner-count-list!", 0, localObject1);
      }
      try
      {
        paramObject1 = (test.Mnrunner)localObject1;
        if (lists.isNull(testRunnerGroupStack((test.Mnrunner)paramObject1))) {
          paramObject1 = Scheme.applyToArgs;
        }
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "test-runner-group-stack", 0, localObject1);
      }
      try
      {
        paramObject2 = (test.Mnrunner)localObject1;
        return ((Procedure)paramObject1).apply2(testRunnerOnFinal((test.Mnrunner)paramObject2), localObject1);
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "test-runner-on-final", 0, localObject1);
      }
      if (paramObject1 != Boolean.FALSE)
      {
        continue;
        if (paramObject2 == Boolean.FALSE) {}
      }
    }
    return Values.empty;
  }
  
  static void $PcTestFinalReport1(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    if (Scheme.numGrt.apply2(paramObject1, Lit0) != Boolean.FALSE)
    {
      ports.display(paramObject2, paramObject3);
      ports.display(paramObject1, paramObject3);
      ports.newline(paramObject3);
    }
  }
  
  /* Error */
  static void $PcTestFinalReportSimple(Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_0
    //   1: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   4: astore_2
    //   5: aload_2
    //   6: invokestatic 629	gnu/kawa/slib/testing:testRunnerPassCount	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   9: ldc_w 631
    //   12: aload_1
    //   13: invokestatic 633	gnu/kawa/slib/testing:$PcTestFinalReport1	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   16: aload_0
    //   17: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   20: astore_2
    //   21: aload_2
    //   22: invokestatic 636	gnu/kawa/slib/testing:testRunnerXfailCount	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   25: ldc_w 638
    //   28: aload_1
    //   29: invokestatic 633	gnu/kawa/slib/testing:$PcTestFinalReport1	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   32: aload_0
    //   33: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   36: astore_2
    //   37: aload_2
    //   38: invokestatic 641	gnu/kawa/slib/testing:testRunnerXpassCount	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   41: ldc_w 643
    //   44: aload_1
    //   45: invokestatic 633	gnu/kawa/slib/testing:$PcTestFinalReport1	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   48: aload_0
    //   49: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   52: astore_2
    //   53: aload_2
    //   54: invokestatic 646	gnu/kawa/slib/testing:testRunnerFailCount	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   57: ldc_w 648
    //   60: aload_1
    //   61: invokestatic 633	gnu/kawa/slib/testing:$PcTestFinalReport1	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   64: aload_0
    //   65: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   68: astore_2
    //   69: aload_2
    //   70: invokestatic 651	gnu/kawa/slib/testing:testRunnerSkipCount	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   73: ldc_w 653
    //   76: aload_1
    //   77: invokestatic 633	gnu/kawa/slib/testing:$PcTestFinalReport1	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   80: return
    //   81: astore_1
    //   82: new 448	gnu/mapping/WrongType
    //   85: dup
    //   86: aload_1
    //   87: ldc_w 655
    //   90: iconst_0
    //   91: aload_0
    //   92: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   95: athrow
    //   96: astore_1
    //   97: new 448	gnu/mapping/WrongType
    //   100: dup
    //   101: aload_1
    //   102: ldc_w 657
    //   105: iconst_0
    //   106: aload_0
    //   107: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   110: athrow
    //   111: astore_1
    //   112: new 448	gnu/mapping/WrongType
    //   115: dup
    //   116: aload_1
    //   117: ldc_w 659
    //   120: iconst_0
    //   121: aload_0
    //   122: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   125: athrow
    //   126: astore_1
    //   127: new 448	gnu/mapping/WrongType
    //   130: dup
    //   131: aload_1
    //   132: ldc_w 661
    //   135: iconst_0
    //   136: aload_0
    //   137: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   140: athrow
    //   141: astore_1
    //   142: new 448	gnu/mapping/WrongType
    //   145: dup
    //   146: aload_1
    //   147: ldc_w 663
    //   150: iconst_0
    //   151: aload_0
    //   152: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   155: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	156	0	paramObject1	Object
    //   0	156	1	paramObject2	Object
    //   4	66	2	localMnrunner	test.Mnrunner
    // Exception table:
    //   from	to	target	type
    //   0	5	81	java/lang/ClassCastException
    //   16	21	96	java/lang/ClassCastException
    //   32	37	111	java/lang/ClassCastException
    //   48	53	126	java/lang/ClassCastException
    //   64	69	141	java/lang/ClassCastException
  }
  
  static Object $PcTestFormatLine(Object paramObject)
  {
    for (;;)
    {
      try
      {
        localObject = (test.Mnrunner)paramObject;
        localObject = testResultAlist((test.Mnrunner)localObject);
        paramObject = lists.assq(Lit4, localObject);
        localObject = lists.assq(Lit5, localObject);
        if (paramObject != Boolean.FALSE)
        {
          paramObject = lists.cdr.apply1(paramObject);
          if (localObject == Boolean.FALSE) {
            continue;
          }
          localObject = lists.cdr.apply1(localObject);
        }
      }
      catch (ClassCastException localClassCastException)
      {
        Object localObject;
        Number localNumber;
        throw new WrongType(localClassCastException, "test-result-alist", 0, paramObject);
      }
      try
      {
        localNumber = (Number)localObject;
        return strings.stringAppend(new Object[] { paramObject, ":", numbers.number$To$String(localNumber), ": " });
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "number->string", 1, localClassCastException);
      }
      paramObject = "";
    }
    return "";
  }
  
  public static Procedure $PcTestMatchAll$V(Object[] paramArrayOfObject)
  {
    frame3 localframe3 = new frame3();
    localframe3.pred$Mnlist = LList.makeList(paramArrayOfObject, 0);
    return localframe3.lambda$Fn12;
  }
  
  public static Procedure $PcTestMatchAny$V(Object[] paramArrayOfObject)
  {
    frame4 localframe4 = new frame4();
    localframe4.pred$Mnlist = LList.makeList(paramArrayOfObject, 0);
    return localframe4.lambda$Fn13;
  }
  
  public static Procedure $PcTestMatchNth(Object paramObject1, Object paramObject2)
  {
    frame2 localframe2 = new frame2();
    localframe2.n = paramObject1;
    localframe2.count = paramObject2;
    localframe2.i = Lit0;
    return localframe2.lambda$Fn11;
  }
  
  static Boolean $PcTestNullCallback(Object paramObject)
  {
    return Boolean.FALSE;
  }
  
  static void $PcTestOnBadCountWrite(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    ports.display("*** Total number of tests was ", paramObject4);
    ports.display(paramObject2, paramObject4);
    ports.display(" but should be ", paramObject4);
    ports.display(paramObject3, paramObject4);
    ports.display(". ***", paramObject4);
    ports.newline(paramObject4);
    ports.display("*** Discrepancy indicates testsuite error or exceptions. ***", paramObject4);
    ports.newline(paramObject4);
  }
  
  /* Error */
  public static boolean $PcTestOnTestBegin(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 738	gnu/kawa/slib/testing:$PcTestShouldExecute	(Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: getstatic 400	kawa/standard/Scheme:applyToArgs	Lgnu/kawa/functions/ApplyToArgs;
    //   8: astore_1
    //   9: aload_0
    //   10: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   13: astore_2
    //   14: aload_1
    //   15: aload_2
    //   16: invokestatic 741	gnu/kawa/slib/testing:testRunnerOnTestBegin	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   19: aload_0
    //   20: invokevirtual 570	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   23: pop
    //   24: getstatic 743	gnu/kawa/slib/testing:Lit2	Lgnu/mapping/SimpleSymbol;
    //   27: astore_1
    //   28: getstatic 745	gnu/kawa/slib/testing:Lit1	Lgnu/mapping/SimpleSymbol;
    //   31: astore_2
    //   32: aload_0
    //   33: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   36: astore_3
    //   37: aload_2
    //   38: aload_3
    //   39: invokestatic 666	gnu/kawa/slib/testing:testResultAlist	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   42: invokestatic 671	kawa/lib/lists:assq	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   45: astore_0
    //   46: aload_0
    //   47: getstatic 310	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   50: if_acmpeq +26 -> 76
    //   53: getstatic 335	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   56: aload_0
    //   57: invokevirtual 326	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   60: astore_0
    //   61: aload_1
    //   62: aload_0
    //   63: if_acmpne +20 -> 83
    //   66: iconst_1
    //   67: istore 4
    //   69: iload 4
    //   71: iconst_1
    //   72: iadd
    //   73: iconst_1
    //   74: iand
    //   75: ireturn
    //   76: getstatic 310	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   79: astore_0
    //   80: goto -19 -> 61
    //   83: iconst_0
    //   84: istore 4
    //   86: goto -17 -> 69
    //   89: astore_1
    //   90: new 448	gnu/mapping/WrongType
    //   93: dup
    //   94: aload_1
    //   95: ldc_w 747
    //   98: iconst_0
    //   99: aload_0
    //   100: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   103: athrow
    //   104: astore_1
    //   105: new 448	gnu/mapping/WrongType
    //   108: dup
    //   109: aload_1
    //   110: ldc_w 687
    //   113: iconst_0
    //   114: aload_0
    //   115: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   118: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	119	0	paramObject	Object
    //   8	54	1	localObject1	Object
    //   89	6	1	localClassCastException1	ClassCastException
    //   104	6	1	localClassCastException2	ClassCastException
    //   13	25	2	localObject2	Object
    //   36	3	3	localMnrunner	test.Mnrunner
    //   67	18	4	i	int
    // Exception table:
    //   from	to	target	type
    //   9	14	89	java/lang/ClassCastException
    //   32	37	104	java/lang/ClassCastException
  }
  
  /* Error */
  public static Object $PcTestOnTestEnd(Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: getstatic 745	gnu/kawa/slib/testing:Lit1	Lgnu/mapping/SimpleSymbol;
    //   3: astore_3
    //   4: getstatic 745	gnu/kawa/slib/testing:Lit1	Lgnu/mapping/SimpleSymbol;
    //   7: astore_2
    //   8: aload_0
    //   9: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   12: astore 4
    //   14: aload_2
    //   15: aload 4
    //   17: invokestatic 666	gnu/kawa/slib/testing:testResultAlist	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   20: invokestatic 671	kawa/lib/lists:assq	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   23: astore_2
    //   24: aload_2
    //   25: getstatic 310	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   28: if_acmpeq +36 -> 64
    //   31: getstatic 335	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   34: aload_2
    //   35: invokevirtual 326	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   38: astore_2
    //   39: aload_2
    //   40: getstatic 750	gnu/kawa/slib/testing:Lit3	Lgnu/mapping/SimpleSymbol;
    //   43: if_acmpne +35 -> 78
    //   46: aload_1
    //   47: getstatic 310	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   50: if_acmpeq +21 -> 71
    //   53: getstatic 752	gnu/kawa/slib/testing:Lit9	Lgnu/mapping/SimpleSymbol;
    //   56: astore_1
    //   57: aload_0
    //   58: aload_3
    //   59: aload_1
    //   60: invokestatic 756	gnu/kawa/slib/testing:testResultSet$Ex	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   63: areturn
    //   64: getstatic 310	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   67: astore_2
    //   68: goto -29 -> 39
    //   71: getstatic 750	gnu/kawa/slib/testing:Lit3	Lgnu/mapping/SimpleSymbol;
    //   74: astore_1
    //   75: goto -18 -> 57
    //   78: aload_1
    //   79: getstatic 310	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   82: if_acmpeq +10 -> 92
    //   85: getstatic 758	gnu/kawa/slib/testing:Lit12	Lgnu/mapping/SimpleSymbol;
    //   88: astore_1
    //   89: goto -32 -> 57
    //   92: getstatic 760	gnu/kawa/slib/testing:Lit14	Lgnu/mapping/SimpleSymbol;
    //   95: astore_1
    //   96: goto -39 -> 57
    //   99: astore_1
    //   100: new 448	gnu/mapping/WrongType
    //   103: dup
    //   104: aload_1
    //   105: ldc_w 687
    //   108: iconst_0
    //   109: aload_0
    //   110: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   113: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	114	0	paramObject1	Object
    //   0	114	1	paramObject2	Object
    //   7	61	2	localObject	Object
    //   3	56	3	localSimpleSymbol	SimpleSymbol
    //   12	4	4	localMnrunner	test.Mnrunner
    // Exception table:
    //   from	to	target	type
    //   8	14	99	java/lang/ClassCastException
  }
  
  public static Object $PcTestReportResult()
  {
    localObject1 = testRunnerGet();
    localObject2 = testResultKind$V(new Object[] { localObject1 });
    if (Scheme.isEqv.apply2(localObject2, Lit12) != Boolean.FALSE) {}
    for (;;)
    {
      try
      {
        localObject2 = (test.Mnrunner)localObject1;
        localObject3 = AddOp.$Pl;
        localIntNum = Lit13;
      }
      catch (ClassCastException localClassCastException1)
      {
        try
        {
          localObject2 = (test.Mnrunner)localObject1;
          localObject3 = AddOp.$Pl;
          localIntNum = Lit13;
        }
        catch (ClassCastException localClassCastException7)
        {
          Object localObject3;
          IntNum localIntNum;
          test.Mnrunner localMnrunner;
          throw new WrongType(localClassCastException7, "test-runner-xfail-count!", 0, localObject1);
        }
        try
        {
          localMnrunner = (test.Mnrunner)localObject1;
          testRunnerXfailCount$Ex((test.Mnrunner)localObject2, ((Procedure)localObject3).apply2(localIntNum, testRunnerXfailCount(localMnrunner)));
        }
        catch (ClassCastException localClassCastException8)
        {
          throw new WrongType(localClassCastException8, "test-runner-xfail-count", 0, localObject1);
        }
        try
        {
          localObject2 = (test.Mnrunner)localObject1;
          localObject3 = AddOp.$Pl;
          localIntNum = Lit13;
        }
        catch (ClassCastException localClassCastException9)
        {
          throw new WrongType(localClassCastException9, "test-runner-skip-count!", 0, localObject1);
        }
        try
        {
          localMnrunner = (test.Mnrunner)localObject1;
          testRunnerSkipCount$Ex((test.Mnrunner)localObject2, ((Procedure)localObject3).apply2(localIntNum, testRunnerSkipCount(localMnrunner)));
        }
        catch (ClassCastException localClassCastException10)
        {
          throw new WrongType(localClassCastException10, "test-runner-skip-count", 0, localObject1);
        }
        localClassCastException1 = localClassCastException1;
        throw new WrongType(localClassCastException1, "test-runner-pass-count!", 0, localObject1);
      }
      try
      {
        localMnrunner = (test.Mnrunner)localObject1;
        testRunnerPassCount$Ex((test.Mnrunner)localObject2, ((Procedure)localObject3).apply2(localIntNum, testRunnerPassCount(localMnrunner)));
      }
      catch (ClassCastException localClassCastException2)
      {
        throw new WrongType(localClassCastException2, "test-runner-pass-count", 0, localObject1);
      }
      try
      {
        localObject2 = (test.Mnrunner)localObject1;
        localObject3 = AddOp.$Pl;
        localIntNum = Lit13;
      }
      catch (ClassCastException localClassCastException11)
      {
        throw new WrongType(localClassCastException11, "%test-runner-total-count!", 0, localObject1);
      }
      try
      {
        localMnrunner = (test.Mnrunner)localObject1;
        $PcTestRunnerTotalCount$Ex((test.Mnrunner)localObject2, ((Procedure)localObject3).apply2(localIntNum, $PcTestRunnerTotalCount(localMnrunner)));
        localObject2 = Scheme.applyToArgs;
      }
      catch (ClassCastException localClassCastException12)
      {
        throw new WrongType(localClassCastException12, "%test-runner-total-count", 0, localObject1);
      }
      try
      {
        localObject3 = (test.Mnrunner)localObject1;
        return ((Procedure)localObject2).apply2(testRunnerOnTestEnd((test.Mnrunner)localObject3), localObject1);
      }
      catch (ClassCastException localClassCastException13)
      {
        throw new WrongType(localClassCastException13, "test-runner-on-test-end", 0, localObject1);
      }
      if (Scheme.isEqv.apply2(localObject2, Lit14) != Boolean.FALSE) {}
      try
      {
        localObject2 = (test.Mnrunner)localObject1;
        localObject3 = AddOp.$Pl;
        localIntNum = Lit13;
      }
      catch (ClassCastException localClassCastException3)
      {
        throw new WrongType(localClassCastException3, "test-runner-fail-count!", 0, localObject1);
      }
      try
      {
        localMnrunner = (test.Mnrunner)localObject1;
        testRunnerFailCount$Ex((test.Mnrunner)localObject2, ((Procedure)localObject3).apply2(localIntNum, testRunnerFailCount(localMnrunner)));
      }
      catch (ClassCastException localClassCastException4)
      {
        throw new WrongType(localClassCastException4, "test-runner-fail-count", 0, localObject1);
      }
      if (Scheme.isEqv.apply2(localObject2, Lit9) != Boolean.FALSE) {}
      try
      {
        localObject2 = (test.Mnrunner)localObject1;
        localObject3 = AddOp.$Pl;
        localIntNum = Lit13;
      }
      catch (ClassCastException localClassCastException5)
      {
        throw new WrongType(localClassCastException5, "test-runner-xpass-count!", 0, localObject1);
      }
      try
      {
        localMnrunner = (test.Mnrunner)localObject1;
        testRunnerXpassCount$Ex((test.Mnrunner)localObject2, ((Procedure)localObject3).apply2(localIntNum, testRunnerXpassCount(localMnrunner)));
      }
      catch (ClassCastException localClassCastException6)
      {
        throw new WrongType(localClassCastException6, "test-runner-xpass-count", 0, localObject1);
      }
    }
    if (Scheme.isEqv.apply2(localObject2, Lit3) == Boolean.FALSE) {}
  }
  
  static test.Mnrunner $PcTestRunnerAlloc()
  {
    return new test.Mnrunner();
  }
  
  static Object $PcTestRunnerCountList(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.count$Mnlist;
  }
  
  static void $PcTestRunnerCountList$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.count$Mnlist = paramObject;
  }
  
  public static Object $PcTestRunnerFailList(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.fail$Mnlist;
  }
  
  public static void $PcTestRunnerFailList$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.fail$Mnlist = paramObject;
  }
  
  static Object $PcTestRunnerFailSave(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.fail$Mnsave;
  }
  
  static void $PcTestRunnerFailSave$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.fail$Mnsave = paramObject;
  }
  
  static Object $PcTestRunnerRunList(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.run$Mnlist;
  }
  
  static void $PcTestRunnerRunList$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.run$Mnlist = paramObject;
  }
  
  public static Object $PcTestRunnerSkipList(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.skip$Mnlist;
  }
  
  public static void $PcTestRunnerSkipList$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.skip$Mnlist = paramObject;
  }
  
  static Object $PcTestRunnerSkipSave(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.skip$Mnsave;
  }
  
  static void $PcTestRunnerSkipSave$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.skip$Mnsave = paramObject;
  }
  
  static Object $PcTestRunnerTotalCount(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.total$Mncount;
  }
  
  static void $PcTestRunnerTotalCount$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.total$Mncount = paramObject;
  }
  
  public static Object $PcTestShouldExecute(Object paramObject)
  {
    for (;;)
    {
      try
      {
        localObject = (test.Mnrunner)paramObject;
        localObject = $PcTestRunnerRunList((test.Mnrunner)localObject);
        if (localObject != Boolean.TRUE) {
          continue;
        }
        i = 1;
        if (i == 0) {
          continue;
        }
      }
      catch (ClassCastException localClassCastException1)
      {
        try
        {
          Boolean localBoolean = Boolean.FALSE;
          if (localObject != localBoolean)
          {
            i = 1;
            continue;
          }
          int i = 0;
        }
        catch (ClassCastException paramObject)
        {
          Object localObject;
          throw new WrongType((ClassCastException)paramObject, "x", -2, localClassCastException1);
        }
        try
        {
          localObject = (test.Mnrunner)paramObject;
          if ($PcTestAnySpecifierMatches($PcTestRunnerSkipList((test.Mnrunner)localObject), paramObject) != Boolean.FALSE) {
            continue;
          }
        }
        catch (ClassCastException localClassCastException2)
        {
          throw new WrongType(localClassCastException2, "%test-runner-skip-list", 0, paramObject);
        }
        try
        {
          localObject = (test.Mnrunner)paramObject;
          if ($PcTestAnySpecifierMatches($PcTestRunnerFailList((test.Mnrunner)localObject), paramObject) != Boolean.FALSE)
          {
            testResultSet$Ex(paramObject, Lit1, Lit3);
            return Lit3;
          }
          return Boolean.TRUE;
        }
        catch (ClassCastException localClassCastException3)
        {
          throw new WrongType(localClassCastException3, "%test-runner-fail-list", 0, paramObject);
        }
        localClassCastException1 = localClassCastException1;
        throw new WrongType(localClassCastException1, "%test-runner-run-list", 0, paramObject);
      }
      i = i + 1 & 0x1;
      if (i == 0) {
        continue;
      }
      if (i == 0) {
        continue;
      }
      testResultSet$Ex(paramObject, Lit1, Lit2);
      return Boolean.FALSE;
      i = 0;
    }
    localObject = $PcTestAnySpecifierMatches(localObject, paramObject);
  }
  
  static Pair $PcTestSourceLine2(Object paramObject)
  {
    Object localObject1 = std_syntax.syntaxLine(paramObject);
    Object localObject2 = $PcTestSyntaxFile(paramObject);
    if (localObject1 != Boolean.FALSE) {}
    for (localObject1 = LList.list1(lists.cons(Lit5, localObject1));; localObject1 = LList.Empty)
    {
      Pair localPair = lists.cons(Lit6, std_syntax.syntaxObject$To$Datum(paramObject));
      paramObject = localObject1;
      if (localObject2 != Boolean.FALSE) {
        paramObject = lists.cons(lists.cons(Lit4, localObject2), localObject1);
      }
      return lists.cons(localPair, paramObject);
    }
  }
  
  static Object $PcTestSpecificierMatches(Object paramObject1, Object paramObject2)
  {
    return Scheme.applyToArgs.apply2(paramObject1, paramObject2);
  }
  
  static Object $PcTestSyntaxFile(Object paramObject)
  {
    return std_syntax.syntaxSource(paramObject);
  }
  
  static Object $PcTestWriteResult1(Object paramObject1, Object paramObject2)
  {
    ports.display("  ", paramObject2);
    ports.display(lists.car.apply1(paramObject1), paramObject2);
    ports.display(": ", paramObject2);
    ports.write(lists.cdr.apply1(paramObject1), paramObject2);
    ports.newline(paramObject2);
    return Values.empty;
  }
  
  static
  {
    Lit164 = (SimpleSymbol)new SimpleSymbol("p").readResolve();
    Lit163 = (SimpleSymbol)new SimpleSymbol("exp").readResolve();
    Lit162 = (SimpleSymbol)new SimpleSymbol("res").readResolve();
    Lit161 = (SimpleSymbol)new SimpleSymbol("if").readResolve();
    Lit160 = (SimpleSymbol)new SimpleSymbol("name").readResolve();
    Lit159 = (SimpleSymbol)new SimpleSymbol("instance?").readResolve();
    Lit158 = (SimpleSymbol)new SimpleSymbol("cond").readResolve();
    Lit157 = (SimpleSymbol)new SimpleSymbol("actual-error").readResolve();
    Lit156 = (SimpleSymbol)new SimpleSymbol("<java.lang.Throwable>").readResolve();
    Lit155 = (SimpleSymbol)new SimpleSymbol("actual-value").readResolve();
    Lit154 = (SimpleSymbol)new SimpleSymbol("try-catch").readResolve();
    Lit153 = (SimpleSymbol)new SimpleSymbol("et").readResolve();
    Lit152 = (SimpleSymbol)new SimpleSymbol("expected-error").readResolve();
    Lit151 = (SimpleSymbol)new SimpleSymbol("ex").readResolve();
    Lit150 = (SimpleSymbol)new SimpleSymbol("let*").readResolve();
    Lit149 = (SimpleSymbol)new SimpleSymbol("r").readResolve();
    Lit148 = (SimpleSymbol)new SimpleSymbol("saved-runner").readResolve();
    Lit147 = (SimpleSymbol)new SimpleSymbol("lambda").readResolve();
    Lit146 = (SimpleSymbol)new SimpleSymbol("test-runner-current").readResolve();
    Lit145 = (SimpleSymbol)new SimpleSymbol("cons").readResolve();
    Lit144 = (SimpleSymbol)new SimpleSymbol("let").readResolve();
    Lit143 = (SimpleSymbol)new SimpleSymbol("runner").readResolve();
    Lit142 = (SimpleSymbol)new SimpleSymbol("test-read-eval-string").readResolve();
    Lit141 = (SimpleSymbol)new SimpleSymbol("test-match-name").readResolve();
    Object localObject1 = (SimpleSymbol)new SimpleSymbol("test-expect-fail").readResolve();
    Lit139 = (SimpleSymbol)localObject1;
    Object localObject2 = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
    Object localObject3 = Lit144;
    Object localObject4 = Lit143;
    SimpleSymbol localSimpleSymbol1 = (SimpleSymbol)new SimpleSymbol("test-runner-get").readResolve();
    Lit60 = localSimpleSymbol1;
    localObject4 = PairWithPosition.make(PairWithPosition.make(localObject4, PairWithPosition.make(PairWithPosition.make(localSimpleSymbol1, LList.Empty, "testing.scm", 3952660), LList.Empty, "testing.scm", 3952660), "testing.scm", 3952652), LList.Empty, "testing.scm", 3952651);
    localSimpleSymbol1 = (SimpleSymbol)new SimpleSymbol("%test-runner-fail-list!").readResolve();
    Lit34 = localSimpleSymbol1;
    Object localObject5 = Lit143;
    SimpleSymbol localSimpleSymbol2 = Lit145;
    SimpleSymbol localSimpleSymbol3 = (SimpleSymbol)new SimpleSymbol("test-match-all").readResolve();
    Lit131 = localSimpleSymbol3;
    Object localObject6 = (SimpleSymbol)new SimpleSymbol("%test-as-specifier").readResolve();
    Lit136 = (SimpleSymbol)localObject6;
    Object localObject7 = (SimpleSymbol)new SimpleSymbol("%test-runner-fail-list").readResolve();
    Lit33 = (SimpleSymbol)localObject7;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\003", "\021\030\004\021\030\f\b\021\030\024\021\030\034\b\021\030$Q\021\030,\b\005\021\0304\b\003\030<", new Object[] { localObject3, localObject4, localSimpleSymbol1, localObject5, localSimpleSymbol2, localSimpleSymbol3, localObject6, PairWithPosition.make(PairWithPosition.make(localObject7, PairWithPosition.make(Lit143, LList.Empty, "testing.scm", 3964958), "testing.scm", 3964934), LList.Empty, "testing.scm", 3964934) }, 1);
    Lit140 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 1);
    localObject1 = (SimpleSymbol)new SimpleSymbol("test-skip").readResolve();
    Lit137 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
    localObject3 = Lit144;
    localObject4 = PairWithPosition.make(PairWithPosition.make(Lit143, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 3919892), LList.Empty, "testing.scm", 3919892), "testing.scm", 3919884), LList.Empty, "testing.scm", 3919883);
    localSimpleSymbol1 = (SimpleSymbol)new SimpleSymbol("%test-runner-skip-list!").readResolve();
    Lit32 = localSimpleSymbol1;
    localObject5 = Lit143;
    localSimpleSymbol2 = Lit145;
    localSimpleSymbol3 = Lit131;
    localObject6 = Lit136;
    localObject7 = (SimpleSymbol)new SimpleSymbol("%test-runner-skip-list").readResolve();
    Lit31 = (SimpleSymbol)localObject7;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\003", "\021\030\004\021\030\f\b\021\030\024\021\030\034\b\021\030$Q\021\030,\b\005\021\0304\b\003\030<", new Object[] { localObject3, localObject4, localSimpleSymbol1, localObject5, localSimpleSymbol2, localSimpleSymbol3, localObject6, PairWithPosition.make(PairWithPosition.make(localObject7, PairWithPosition.make(Lit143, LList.Empty, "testing.scm", 3932190), "testing.scm", 3932166), LList.Empty, "testing.scm", 3932166) }, 1);
    Lit138 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 1);
    localObject1 = (SimpleSymbol)new SimpleSymbol("test-match-any").readResolve();
    Lit134 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
    localObject3 = (SimpleSymbol)new SimpleSymbol("%test-match-any").readResolve();
    Lit133 = (SimpleSymbol)localObject3;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\003", "\021\030\004\b\005\021\030\f\b\003", new Object[] { localObject3, Lit136 }, 1);
    Lit135 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 1);
    localObject1 = Lit131;
    localObject2 = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
    localObject3 = (SimpleSymbol)new SimpleSymbol("%test-match-all").readResolve();
    Lit130 = (SimpleSymbol)localObject3;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\003", "\021\030\004\b\005\021\030\f\b\003", new Object[] { localObject3, Lit136 }, 1);
    Lit132 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 1);
    localObject1 = (SimpleSymbol)new SimpleSymbol("test-match-nth").readResolve();
    Lit128 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
    localObject3 = Lit128;
    localObject4 = IntNum.make(1);
    Lit13 = (IntNum)localObject4;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\001", "\021\030\004\t\003\030\f", new Object[] { localObject3, PairWithPosition.make(localObject4, LList.Empty, "testing.scm", 3727384) }, 0);
    localObject3 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
    localObject4 = (SimpleSymbol)new SimpleSymbol("%test-match-nth").readResolve();
    Lit127 = (SimpleSymbol)localObject4;
    localObject3 = new SyntaxRule((SyntaxPattern)localObject3, "\001\001", "\021\030\004\t\003\b\013", new Object[] { localObject4 }, 0);
    Lit129 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2, localObject3 }, 2);
    localObject1 = (SimpleSymbol)new SimpleSymbol("test-with-runner").readResolve();
    Lit125 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\r\017\b\b\b", new Object[0], 2), "\001\003", "\021\030\004\021\030\f\b\021\030\024Y\021\030\034\t\020\b\021\030$\b\003A\021\030\034\t\020\b\r\013\030,", new Object[] { Lit144, PairWithPosition.make(PairWithPosition.make(Lit148, PairWithPosition.make(PairWithPosition.make(Lit146, LList.Empty, "testing.scm", 3657754), LList.Empty, "testing.scm", 3657754), "testing.scm", 3657740), LList.Empty, "testing.scm", 3657739), Lit165, Lit147, Lit146, PairWithPosition.make(PairWithPosition.make(Lit147, PairWithPosition.make(LList.Empty, PairWithPosition.make(PairWithPosition.make(Lit146, PairWithPosition.make(Lit148, LList.Empty, "testing.scm", 3674156), "testing.scm", 3674135), LList.Empty, "testing.scm", 3674135), "testing.scm", 3674132), "testing.scm", 3674124), LList.Empty, "testing.scm", 3674124) }, 1);
    Lit126 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 2);
    Lit124 = (SimpleSymbol)new SimpleSymbol("test-apply").readResolve();
    localObject1 = Lit150;
    localObject2 = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 3514382), LList.Empty, "testing.scm", 3514382), "testing.scm", 3514379), LList.Empty, "testing.scm", 3514378);
    localObject3 = (SimpleSymbol)new SimpleSymbol("test-result-alist!").readResolve();
    Lit52 = (SimpleSymbol)localObject3;
    localObject4 = Lit149;
    localSimpleSymbol1 = (SimpleSymbol)new SimpleSymbol("%test-error").readResolve();
    Lit115 = localSimpleSymbol1;
    Lit123 = new SyntaxTemplate("\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b\023\b\021\030$\021\030\034\021\030,\b\013", new Object[] { localObject1, localObject2, localObject3, localObject4, localSimpleSymbol1, Boolean.TRUE }, 0);
    Lit122 = new SyntaxPattern(",\f\007\f\017\b\f\027\b", new Object[0], 3);
    Lit121 = new SyntaxTemplate("\001\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b\033\b\021\030$\021\030\034\t\013\b\023", new Object[] { Lit150, PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 3493902), LList.Empty, "testing.scm", 3493902), "testing.scm", 3493899), LList.Empty, "testing.scm", 3493898), Lit52, Lit149, Lit115 }, 0);
    Lit120 = new SyntaxPattern("<\f\007\f\017\f\027\b\f\037\b", new Object[0], 4);
    localObject1 = Lit150;
    localObject2 = PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 3469326), LList.Empty, "testing.scm", 3469326), "testing.scm", 3469323);
    localObject3 = Lit160;
    localObject4 = Lit52;
    localSimpleSymbol1 = Lit149;
    localObject5 = Lit145;
    localSimpleSymbol2 = (SimpleSymbol)new SimpleSymbol("quote").readResolve();
    Lit15 = localSimpleSymbol2;
    localSimpleSymbol3 = (SimpleSymbol)new SimpleSymbol("test-name").readResolve();
    Lit7 = localSimpleSymbol3;
    Lit119 = new SyntaxTemplate("\001\001\001\001\001", "\021\030\004I\021\030\f\b\021\030\024\b\013©\021\030\034\021\030$\b\021\030,A\021\030,\021\0304\b\013\b#\b\021\030<\021\030$\t\023\b\033", new Object[] { localObject1, localObject2, localObject3, localObject4, localSimpleSymbol1, localObject5, PairWithPosition.make(localSimpleSymbol2, PairWithPosition.make(localSimpleSymbol3, LList.Empty, "testing.scm", 3477545), "testing.scm", 3477545), Lit115 }, 0);
    Lit118 = new SyntaxPattern("L\f\007\f\017\f\027\f\037\b\f'\b", new Object[0], 5);
    Lit117 = (SimpleSymbol)new SimpleSymbol("test-error").readResolve();
    localObject1 = Lit115;
    localObject2 = new SyntaxPattern("\f\030\f\007\f\002\f\017\b", new Object[] { Boolean.TRUE }, 2);
    localObject3 = Lit158;
    localObject4 = (SimpleSymbol)new SimpleSymbol("%test-on-test-begin").readResolve();
    Lit86 = (SimpleSymbol)localObject4;
    localSimpleSymbol1 = (SimpleSymbol)new SimpleSymbol("test-result-set!").readResolve();
    Lit78 = localSimpleSymbol1;
    localObject5 = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit152, LList.Empty, "testing.scm", 3223581), "testing.scm", 3223581), PairWithPosition.make(Boolean.TRUE, LList.Empty, "testing.scm", 3223596), "testing.scm", 3223580);
    localSimpleSymbol2 = (SimpleSymbol)new SimpleSymbol("%test-on-test-end").readResolve();
    Lit87 = localSimpleSymbol2;
    localSimpleSymbol3 = Lit154;
    localObject6 = Lit144;
    localObject7 = PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "testing.scm", 3239966), "testing.scm", 3239966);
    Object localObject8 = PairWithPosition.make(Boolean.FALSE, LList.Empty, "testing.scm", 3244041);
    SimpleSymbol localSimpleSymbol4 = Lit151;
    SimpleSymbol localSimpleSymbol5 = Lit156;
    PairWithPosition localPairWithPosition1 = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit157, LList.Empty, "testing.scm", 3252256), "testing.scm", 3252256), PairWithPosition.make(Lit151, LList.Empty, "testing.scm", 3252269), "testing.scm", 3252255);
    PairWithPosition localPairWithPosition2 = PairWithPosition.make(Boolean.TRUE, LList.Empty, "testing.scm", 3256331);
    SimpleSymbol localSimpleSymbol6 = (SimpleSymbol)new SimpleSymbol("%test-report-result").readResolve();
    Lit83 = localSimpleSymbol6;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\001\001", "\021\030\004\b)\021\030\f\b\0039\021\030\024\t\003\030\034ũ\021\030$\t\003\b\021\030,\021\0304\t\020Q\021\030\024\t\003\021\030<\b\013\030D\b\021\030L\021\030T9\021\030\024\t\003\030\\\030d\030l", new Object[] { localObject3, localObject4, localSimpleSymbol1, localObject5, localSimpleSymbol2, localSimpleSymbol3, localObject6, localObject7, localObject8, localSimpleSymbol4, localSimpleSymbol5, localPairWithPosition1, localPairWithPosition2, PairWithPosition.make(PairWithPosition.make(localSimpleSymbol6, LList.Empty, "testing.scm", 3260424), LList.Empty, "testing.scm", 3260424) }, 0);
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3), "\001\001\001", "\021\030\004)\021\030\f\b\003\b\021\030\0241\b\021\030\034\b\0139\021\030$\t\003\030,ũ\021\0304\t\003\b\021\030<\021\030\024\t\020Q\021\030$\t\003\021\030D\b\023\030L\b\021\030T\021\030\\9\021\030$\t\003\030d\030l\030t", new Object[] { Lit161, Lit86, Lit144, Lit153, Lit78, PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit152, LList.Empty, "testing.scm", 3276828), "testing.scm", 3276828), PairWithPosition.make(Lit153, LList.Empty, "testing.scm", 3276843), "testing.scm", 3276827), Lit87, Lit154, PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "testing.scm", 3293213), "testing.scm", 3293213), PairWithPosition.make(Boolean.FALSE, LList.Empty, "testing.scm", 3297288), Lit151, Lit156, PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit157, LList.Empty, "testing.scm", 3305503), "testing.scm", 3305503), PairWithPosition.make(Lit151, LList.Empty, "testing.scm", 3305516), "testing.scm", 3305502), PairWithPosition.make(PairWithPosition.make(Lit158, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make((SimpleSymbol)new SimpleSymbol("and").readResolve(), PairWithPosition.make(PairWithPosition.make(Lit159, PairWithPosition.make(Lit153, PairWithPosition.make((SimpleSymbol)new SimpleSymbol("<gnu.bytecode.ClassType>").readResolve(), LList.Empty, "testing.scm", 3309604), "testing.scm", 3309601), "testing.scm", 3309590), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make((SimpleSymbol)new SimpleSymbol("$lookup$").readResolve(), Pair.make((SimpleSymbol)new SimpleSymbol("gnu.bytecode.ClassType").readResolve(), Pair.make(Pair.make((SimpleSymbol)new SimpleSymbol("quasiquote").readResolve(), Pair.make((SimpleSymbol)new SimpleSymbol("isSubclass").readResolve(), LList.Empty)), LList.Empty)), "testing.scm", 3313673), PairWithPosition.make(Lit153, PairWithPosition.make(Lit156, LList.Empty, "testing.scm", 3313710), "testing.scm", 3313707), "testing.scm", 3313672), LList.Empty, "testing.scm", 3313672), "testing.scm", 3309590), "testing.scm", 3309585), PairWithPosition.make(PairWithPosition.make(Lit159, PairWithPosition.make(Lit151, PairWithPosition.make(Lit153, LList.Empty, "testing.scm", 3317784), "testing.scm", 3317781), "testing.scm", 3317770), LList.Empty, "testing.scm", 3317770), "testing.scm", 3309584), PairWithPosition.make(PairWithPosition.make((SimpleSymbol)new SimpleSymbol("else").readResolve(), PairWithPosition.make(Boolean.TRUE, LList.Empty, "testing.scm", 3321871), "testing.scm", 3321865), LList.Empty, "testing.scm", 3321865), "testing.scm", 3309584), "testing.scm", 3309578), LList.Empty, "testing.scm", 3309578), PairWithPosition.make(PairWithPosition.make(Lit83, LList.Empty, "testing.scm", 3325959), LList.Empty, "testing.scm", 3325959) }, 0);
    Lit116 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2, localObject3 }, 3);
    localObject1 = Lit150;
    localObject2 = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 2916364), LList.Empty, "testing.scm", 2916364), "testing.scm", 2916361), LList.Empty, "testing.scm", 2916360);
    localObject3 = Lit52;
    localObject4 = Lit149;
    localSimpleSymbol1 = (SimpleSymbol)new SimpleSymbol("%test-comp2body").readResolve();
    Lit89 = localSimpleSymbol1;
    localObject5 = (SimpleSymbol)new SimpleSymbol("%test-approximimate=").readResolve();
    Lit91 = (SimpleSymbol)localObject5;
    Lit114 = new SyntaxTemplate("\001\001\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b#\b\021\030$\021\030\034)\021\030,\b\033\t\013\b\023", new Object[] { localObject1, localObject2, localObject3, localObject4, localSimpleSymbol1, localObject5 }, 0);
    Lit113 = new SyntaxPattern("L\f\007\f\017\f\027\f\037\b\f'\b", new Object[0], 5);
    Lit112 = new SyntaxTemplate("\001\001\001\001\001\001", "\021\030\004I\021\030\f\b\021\030\024\b\013©\021\030\034\021\030$\b\021\030,A\021\030,\021\0304\b\013\b+\b\021\030<\021\030$)\021\030D\b#\t\023\b\033", new Object[] { Lit150, PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 2891788), LList.Empty, "testing.scm", 2891788), "testing.scm", 2891785), Lit160, Lit52, Lit149, Lit145, PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "testing.scm", 2900007), "testing.scm", 2900007), Lit89, Lit91 }, 0);
    Lit111 = new SyntaxPattern("\\\f\007\f\017\f\027\f\037\f'\b\f/\b", new Object[0], 6);
    Lit110 = (SimpleSymbol)new SimpleSymbol("test-approximate").readResolve();
    Lit109 = new SyntaxTemplate("", "\030\004", new Object[] { (SimpleSymbol)new SimpleSymbol("equal?").readResolve() }, 0);
    Lit108 = (SimpleSymbol)new SimpleSymbol("test-equal").readResolve();
    Lit107 = new SyntaxTemplate("", "\030\004", new Object[] { (SimpleSymbol)new SimpleSymbol("eq?").readResolve() }, 0);
    Lit106 = (SimpleSymbol)new SimpleSymbol("test-eq").readResolve();
    Lit105 = new SyntaxTemplate("", "\030\004", new Object[] { (SimpleSymbol)new SimpleSymbol("eqv?").readResolve() }, 0);
    Lit104 = (SimpleSymbol)new SimpleSymbol("test-eqv").readResolve();
    localObject1 = Lit150;
    localObject2 = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 2781198), LList.Empty, "testing.scm", 2781198), "testing.scm", 2781195), LList.Empty, "testing.scm", 2781194);
    localObject3 = Lit52;
    localObject4 = Lit149;
    localSimpleSymbol1 = (SimpleSymbol)new SimpleSymbol("%test-comp1body").readResolve();
    Lit92 = localSimpleSymbol1;
    Lit103 = new SyntaxTemplate("\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b\023\b\021\030$\021\030\034\b\013", new Object[] { localObject1, localObject2, localObject3, localObject4, localSimpleSymbol1 }, 0);
    Lit102 = new SyntaxPattern(",\f\007\f\017\b\f\027\b", new Object[0], 3);
    Lit101 = new SyntaxTemplate("\001\001\001\001", "\021\030\004I\021\030\f\b\021\030\024\b\013©\021\030\034\021\030$\b\021\030,A\021\030,\021\0304\b\013\b\033\b\021\030<\021\030$\b\023", new Object[] { Lit150, PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 2756622), LList.Empty, "testing.scm", 2756622), "testing.scm", 2756619), Lit160, Lit52, Lit149, Lit145, PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "testing.scm", 2764841), "testing.scm", 2764841), Lit92 }, 0);
    Lit100 = new SyntaxPattern("<\f\007\f\017\f\027\b\f\037\b", new Object[0], 4);
    Lit99 = (SimpleSymbol)new SimpleSymbol("test-assert").readResolve();
    localObject1 = (SimpleSymbol)new SimpleSymbol("%test-end").readResolve();
    Lit69 = (SimpleSymbol)localObject1;
    Lit98 = new SyntaxTemplate("\001\001", "\021\030\004\021\030\f\b\013", new Object[] { localObject1, Boolean.FALSE }, 0);
    Lit97 = new SyntaxPattern("\034\f\007\b\f\017\b", new Object[0], 2);
    Lit96 = new SyntaxTemplate("\001\001\001", "\021\030\004\t\013\b\023", new Object[] { Lit69 }, 0);
    Lit95 = new SyntaxPattern(",\f\007\f\017\b\f\027\b", new Object[0], 3);
    Lit94 = (SimpleSymbol)new SimpleSymbol("test-end").readResolve();
    localObject1 = Lit92;
    localObject2 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
    localObject3 = Lit144;
    localObject4 = Lit161;
    localSimpleSymbol1 = Lit86;
    localObject5 = Lit162;
    localSimpleSymbol2 = (SimpleSymbol)new SimpleSymbol("%test-evaluate-with-catch").readResolve();
    Lit84 = localSimpleSymbol2;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\001\001", "\021\030\004\t\020ű\021\030\f)\021\030\024\b\003\b\021\030\004\t\020\b\021\030\004Q\b\021\030\034\b\021\030$\b\0139\021\030,\t\003\0304\b\021\030<\t\003\030D\030L", new Object[] { localObject3, localObject4, localSimpleSymbol1, localObject5, localSimpleSymbol2, Lit78, PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "testing.scm", 2666526), "testing.scm", 2666526), PairWithPosition.make(Lit162, LList.Empty, "testing.scm", 2666539), "testing.scm", 2666525), Lit87, PairWithPosition.make(Lit162, LList.Empty, "testing.scm", 2670622), PairWithPosition.make(PairWithPosition.make(Lit83, LList.Empty, "testing.scm", 2674696), LList.Empty, "testing.scm", 2674696) }, 0);
    Lit93 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 2);
    localObject1 = Lit89;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\f\037\b", new Object[0], 4), "\001\001\001\001", "\021\030\004\t\020Ǳ\021\030\f)\021\030\024\b\003\b\021\030\0041\b\021\030\034\b\0239\021\030$\t\003\030,\b\021\030\004Q\b\021\0304\b\021\030<\b\0339\021\030$\t\003\030D\b\021\030L\t\003\b\t\013\030T\030\\", new Object[] { Lit144, Lit161, Lit86, Lit163, Lit78, PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make((SimpleSymbol)new SimpleSymbol("expected-value").readResolve(), LList.Empty, "testing.scm", 2592794), "testing.scm", 2592794), PairWithPosition.make(Lit163, LList.Empty, "testing.scm", 2592809), "testing.scm", 2592793), Lit162, Lit84, PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "testing.scm", 2600988), "testing.scm", 2600988), PairWithPosition.make(Lit162, LList.Empty, "testing.scm", 2601001), "testing.scm", 2600987), Lit87, PairWithPosition.make(Lit163, PairWithPosition.make(Lit162, LList.Empty, "testing.scm", 2605094), "testing.scm", 2605090), PairWithPosition.make(PairWithPosition.make(Lit83, LList.Empty, "testing.scm", 2609158), LList.Empty, "testing.scm", 2609158) }, 0);
    Lit90 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 4);
    Lit88 = (SimpleSymbol)new SimpleSymbol("test-runner-test-name").readResolve();
    localObject1 = Lit84;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\t\003\030\f", new Object[] { Lit154, PairWithPosition.make(PairWithPosition.make(Lit151, PairWithPosition.make(Lit156, PairWithPosition.make(PairWithPosition.make(Lit78, PairWithPosition.make(PairWithPosition.make(Lit146, LList.Empty, "testing.scm", 2347035), PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit157, LList.Empty, "testing.scm", 2347058), "testing.scm", 2347058), PairWithPosition.make(Lit151, LList.Empty, "testing.scm", 2347071), "testing.scm", 2347057), "testing.scm", 2347035), "testing.scm", 2347017), PairWithPosition.make(Boolean.FALSE, LList.Empty, "testing.scm", 2351113), "testing.scm", 2347017), "testing.scm", 2342921), "testing.scm", 2342917), LList.Empty, "testing.scm", 2342917) }, 0);
    Lit85 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 1);
    Lit82 = (SimpleSymbol)new SimpleSymbol("test-passed?").readResolve();
    Lit81 = (SimpleSymbol)new SimpleSymbol("test-result-kind").readResolve();
    Lit80 = (SimpleSymbol)new SimpleSymbol("test-result-remove").readResolve();
    Lit79 = (SimpleSymbol)new SimpleSymbol("test-result-clear").readResolve();
    Lit77 = (SimpleSymbol)new SimpleSymbol("test-on-test-end-simple").readResolve();
    localObject1 = (SimpleSymbol)new SimpleSymbol("test-result-ref").readResolve();
    Lit75 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\t\013\030\f", new Object[] { Lit75, PairWithPosition.make(Boolean.FALSE, LList.Empty, "testing.scm", 1933348) }, 0);
    localObject3 = new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3);
    localObject4 = Lit144;
    localSimpleSymbol1 = Lit164;
    localObject5 = (SimpleSymbol)new SimpleSymbol("assq").readResolve();
    localSimpleSymbol2 = (SimpleSymbol)new SimpleSymbol("test-result-alist").readResolve();
    Lit51 = localSimpleSymbol2;
    localObject3 = new SyntaxRule((SyntaxPattern)localObject3, "\001\001\001", "\021\030\004\b\021\030\f\b\021\030\024\t\013\b\021\030\034\b\003\b\021\030$\021\030\f\021\030,\b\023", new Object[] { localObject4, localSimpleSymbol1, localObject5, localSimpleSymbol2, Lit161, PairWithPosition.make((SimpleSymbol)new SimpleSymbol("cdr").readResolve(), PairWithPosition.make(Lit164, LList.Empty, "testing.scm", 1945619), "testing.scm", 1945614) }, 0);
    Lit76 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2, localObject3 }, 3);
    Lit74 = (SimpleSymbol)new SimpleSymbol("test-on-test-begin-simple").readResolve();
    localObject1 = (SimpleSymbol)new SimpleSymbol("test-group-with-cleanup").readResolve();
    Lit72 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3);
    localObject3 = (SimpleSymbol)new SimpleSymbol("test-group").readResolve();
    Lit70 = (SimpleSymbol)localObject3;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\001\001\001", "\021\030\004\t\003\b\021\030\f\021\030\0249\021\030\034\t\020\b\013\b\021\030\034\t\020\b\023", new Object[] { localObject3, Lit165, PairWithPosition.make(Lit147, PairWithPosition.make(LList.Empty, PairWithPosition.make(Boolean.FALSE, LList.Empty, "testing.scm", 1826831), "testing.scm", 1826828), "testing.scm", 1826820), Lit147 }, 0);
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\021\030\f\b\013", new Object[] { Lit72, Boolean.FALSE }, 0);
    localObject4 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\f\037#", new Object[0], 5), "\001\001\001\001\000", "\021\030\004\t\0039\021\030\f\t\013\b\023\t\033\"", new Object[] { Lit72, (SimpleSymbol)new SimpleSymbol("begin").readResolve() }, 0);
    Lit73 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2, localObject3, localObject4 }, 5);
    localObject1 = Lit70;
    localObject2 = new SyntaxPattern("\f\030\f\007\013", new Object[0], 2);
    localObject3 = Lit144;
    localObject4 = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit146, LList.Empty, "testing.scm", 1769487), LList.Empty, "testing.scm", 1769487), "testing.scm", 1769484), LList.Empty, "testing.scm", 1769483);
    localSimpleSymbol1 = Lit52;
    localObject5 = Lit149;
    localSimpleSymbol2 = (SimpleSymbol)new SimpleSymbol("list").readResolve();
    localSimpleSymbol3 = Lit145;
    localObject6 = PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "testing.scm", 1777707), "testing.scm", 1777707);
    localObject7 = Lit161;
    localObject8 = (SimpleSymbol)new SimpleSymbol("%test-should-execute").readResolve();
    Lit62 = (SimpleSymbol)localObject8;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\001\000", "\021\030\004\021\030\f\021\030\024\021\030\034\b\021\030$\b\021\030,\021\0304\b\003\b\021\030<\021\030D\b\021\030LY\021\030T\t\020\b\021\030\\\b\0031\021\030T\t\020\n\b\021\030T\t\020\b\021\030d\b\003", new Object[] { localObject3, localObject4, localSimpleSymbol1, localObject5, localSimpleSymbol2, localSimpleSymbol3, localObject6, localObject7, PairWithPosition.make(localObject8, PairWithPosition.make(Lit149, LList.Empty, "testing.scm", 1781794), "testing.scm", 1781772), Lit165, Lit147, (SimpleSymbol)new SimpleSymbol("test-begin").readResolve(), Lit94 }, 0);
    Lit71 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 2);
    Lit68 = (SimpleSymbol)new SimpleSymbol("test-on-final-simple").readResolve();
    Lit67 = (SimpleSymbol)new SimpleSymbol("test-on-bad-end-name-simple").readResolve();
    Lit66 = (SimpleSymbol)new SimpleSymbol("test-on-bad-count-simple").readResolve();
    Lit65 = (SimpleSymbol)new SimpleSymbol("test-on-group-end-simple").readResolve();
    Lit64 = (SimpleSymbol)new SimpleSymbol("test-on-group-begin-simple").readResolve();
    Lit63 = (SimpleSymbol)new SimpleSymbol("%test-begin").readResolve();
    Lit61 = (SimpleSymbol)new SimpleSymbol("test-runner-create").readResolve();
    Lit59 = (SimpleSymbol)new SimpleSymbol("test-runner-simple").readResolve();
    Lit58 = (SimpleSymbol)new SimpleSymbol("test-runner-null").readResolve();
    Lit57 = (SimpleSymbol)new SimpleSymbol("%test-null-callback").readResolve();
    Lit56 = (SimpleSymbol)new SimpleSymbol("test-runner-group-path").readResolve();
    Lit55 = (SimpleSymbol)new SimpleSymbol("test-runner-reset").readResolve();
    Lit54 = (SimpleSymbol)new SimpleSymbol("test-runner-aux-value!").readResolve();
    Lit53 = (SimpleSymbol)new SimpleSymbol("test-runner-aux-value").readResolve();
    Lit50 = (SimpleSymbol)new SimpleSymbol("test-runner-on-bad-end-name!").readResolve();
    Lit49 = (SimpleSymbol)new SimpleSymbol("test-runner-on-bad-end-name").readResolve();
    Lit48 = (SimpleSymbol)new SimpleSymbol("test-runner-on-bad-count!").readResolve();
    Lit47 = (SimpleSymbol)new SimpleSymbol("test-runner-on-bad-count").readResolve();
    Lit46 = (SimpleSymbol)new SimpleSymbol("test-runner-on-final!").readResolve();
    Lit45 = (SimpleSymbol)new SimpleSymbol("test-runner-on-final").readResolve();
    Lit44 = (SimpleSymbol)new SimpleSymbol("test-runner-on-group-end!").readResolve();
    Lit43 = (SimpleSymbol)new SimpleSymbol("test-runner-on-group-end").readResolve();
    Lit42 = (SimpleSymbol)new SimpleSymbol("test-runner-on-group-begin!").readResolve();
    Lit41 = (SimpleSymbol)new SimpleSymbol("test-runner-on-group-begin").readResolve();
    Lit40 = (SimpleSymbol)new SimpleSymbol("test-runner-on-test-end!").readResolve();
    Lit39 = (SimpleSymbol)new SimpleSymbol("test-runner-on-test-end").readResolve();
    Lit38 = (SimpleSymbol)new SimpleSymbol("test-runner-on-test-begin!").readResolve();
    Lit37 = (SimpleSymbol)new SimpleSymbol("test-runner-on-test-begin").readResolve();
    Lit36 = (SimpleSymbol)new SimpleSymbol("test-runner-group-stack!").readResolve();
    Lit35 = (SimpleSymbol)new SimpleSymbol("test-runner-group-stack").readResolve();
    Lit30 = (SimpleSymbol)new SimpleSymbol("test-runner-skip-count!").readResolve();
    Lit29 = (SimpleSymbol)new SimpleSymbol("test-runner-skip-count").readResolve();
    Lit28 = (SimpleSymbol)new SimpleSymbol("test-runner-xfail-count!").readResolve();
    Lit27 = (SimpleSymbol)new SimpleSymbol("test-runner-xfail-count").readResolve();
    Lit26 = (SimpleSymbol)new SimpleSymbol("test-runner-xpass-count!").readResolve();
    Lit25 = (SimpleSymbol)new SimpleSymbol("test-runner-xpass-count").readResolve();
    Lit24 = (SimpleSymbol)new SimpleSymbol("test-runner-fail-count!").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("test-runner-fail-count").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("test-runner-pass-count!").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("test-runner-pass-count").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("test-runner?").readResolve();
    Lit19 = new SyntaxTemplate("\001\001\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b\033\b\021\030$\021\030\034\t#\t\013\b\023", new Object[] { Lit150, PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 2834444), LList.Empty, "testing.scm", 2834444), "testing.scm", 2834441), LList.Empty, "testing.scm", 2834440), Lit52, Lit149, Lit89 }, 0);
    Lit18 = new SyntaxPattern("<\f\007\f\017\f\027\b\f\037\f'\b", new Object[0], 5);
    Lit17 = new SyntaxTemplate("\001\001\001\001\001\001", "\021\030\004I\021\030\f\b\021\030\024\b\013©\021\030\034\021\030$\b\021\030,A\021\030,\021\0304\b\013\b#\b\021\030<\021\030$\t+\t\023\b\033", new Object[] { Lit150, PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 2809868), LList.Empty, "testing.scm", 2809868), "testing.scm", 2809865), Lit160, Lit52, Lit149, Lit145, PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "testing.scm", 2818087), "testing.scm", 2818087), Lit89 }, 0);
    Lit16 = new SyntaxPattern("L\f\007\f\017\f\027\f\037\b\f'\f/\b", new Object[0], 6);
    Lit14 = (SimpleSymbol)new SimpleSymbol("fail").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("pass").readResolve();
    localObject1 = Lit12;
    localObject2 = (SimpleSymbol)new SimpleSymbol("xpass").readResolve();
    Lit9 = (SimpleSymbol)localObject2;
    Lit11 = PairWithPosition.make(localObject1, PairWithPosition.make(localObject2, LList.Empty, "testing.scm", 2220088), "testing.scm", 2220082);
    localObject1 = Lit7;
    localObject2 = (SimpleSymbol)new SimpleSymbol("source-file").readResolve();
    Lit4 = (SimpleSymbol)localObject2;
    localObject3 = (SimpleSymbol)new SimpleSymbol("source-line").readResolve();
    Lit5 = (SimpleSymbol)localObject3;
    localObject4 = (SimpleSymbol)new SimpleSymbol("source-form").readResolve();
    Lit6 = (SimpleSymbol)localObject4;
    Lit10 = PairWithPosition.make(localObject1, PairWithPosition.make(localObject2, PairWithPosition.make(localObject3, PairWithPosition.make(localObject4, LList.Empty, "testing.scm", 2072618), "testing.scm", 2072606), "testing.scm", 2072594), "testing.scm", 2072583);
    Lit8 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit9, LList.Empty, "testing.scm", 1966107), "testing.scm", 1966101);
    Lit3 = (SimpleSymbol)new SimpleSymbol("xfail").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("skip").readResolve();
    Lit1 = (SimpleSymbol)new SimpleSymbol("result-kind").readResolve();
    Lit0 = IntNum.make(0);
    $instance = new testing();
    test$Mnrunner = test.Mnrunner.class;
    localObject1 = $instance;
    test$Mnrunner$Qu = new ModuleMethod((ModuleBody)localObject1, 12, Lit20, 4097);
    test$Mnrunner$Mnpass$Mncount = new ModuleMethod((ModuleBody)localObject1, 13, Lit21, 4097);
    test$Mnrunner$Mnpass$Mncount$Ex = new ModuleMethod((ModuleBody)localObject1, 14, Lit22, 8194);
    test$Mnrunner$Mnfail$Mncount = new ModuleMethod((ModuleBody)localObject1, 15, Lit23, 4097);
    test$Mnrunner$Mnfail$Mncount$Ex = new ModuleMethod((ModuleBody)localObject1, 16, Lit24, 8194);
    test$Mnrunner$Mnxpass$Mncount = new ModuleMethod((ModuleBody)localObject1, 17, Lit25, 4097);
    test$Mnrunner$Mnxpass$Mncount$Ex = new ModuleMethod((ModuleBody)localObject1, 18, Lit26, 8194);
    test$Mnrunner$Mnxfail$Mncount = new ModuleMethod((ModuleBody)localObject1, 19, Lit27, 4097);
    test$Mnrunner$Mnxfail$Mncount$Ex = new ModuleMethod((ModuleBody)localObject1, 20, Lit28, 8194);
    test$Mnrunner$Mnskip$Mncount = new ModuleMethod((ModuleBody)localObject1, 21, Lit29, 4097);
    test$Mnrunner$Mnskip$Mncount$Ex = new ModuleMethod((ModuleBody)localObject1, 22, Lit30, 8194);
    $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist = new ModuleMethod((ModuleBody)localObject1, 23, Lit31, 4097);
    $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist$Ex = new ModuleMethod((ModuleBody)localObject1, 24, Lit32, 8194);
    $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist = new ModuleMethod((ModuleBody)localObject1, 25, Lit33, 4097);
    $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist$Ex = new ModuleMethod((ModuleBody)localObject1, 26, Lit34, 8194);
    test$Mnrunner$Mngroup$Mnstack = new ModuleMethod((ModuleBody)localObject1, 27, Lit35, 4097);
    test$Mnrunner$Mngroup$Mnstack$Ex = new ModuleMethod((ModuleBody)localObject1, 28, Lit36, 8194);
    test$Mnrunner$Mnon$Mntest$Mnbegin = new ModuleMethod((ModuleBody)localObject1, 29, Lit37, 4097);
    test$Mnrunner$Mnon$Mntest$Mnbegin$Ex = new ModuleMethod((ModuleBody)localObject1, 30, Lit38, 8194);
    test$Mnrunner$Mnon$Mntest$Mnend = new ModuleMethod((ModuleBody)localObject1, 31, Lit39, 4097);
    test$Mnrunner$Mnon$Mntest$Mnend$Ex = new ModuleMethod((ModuleBody)localObject1, 32, Lit40, 8194);
    test$Mnrunner$Mnon$Mngroup$Mnbegin = new ModuleMethod((ModuleBody)localObject1, 33, Lit41, 4097);
    test$Mnrunner$Mnon$Mngroup$Mnbegin$Ex = new ModuleMethod((ModuleBody)localObject1, 34, Lit42, 8194);
    test$Mnrunner$Mnon$Mngroup$Mnend = new ModuleMethod((ModuleBody)localObject1, 35, Lit43, 4097);
    test$Mnrunner$Mnon$Mngroup$Mnend$Ex = new ModuleMethod((ModuleBody)localObject1, 36, Lit44, 8194);
    test$Mnrunner$Mnon$Mnfinal = new ModuleMethod((ModuleBody)localObject1, 37, Lit45, 4097);
    test$Mnrunner$Mnon$Mnfinal$Ex = new ModuleMethod((ModuleBody)localObject1, 38, Lit46, 8194);
    test$Mnrunner$Mnon$Mnbad$Mncount = new ModuleMethod((ModuleBody)localObject1, 39, Lit47, 4097);
    test$Mnrunner$Mnon$Mnbad$Mncount$Ex = new ModuleMethod((ModuleBody)localObject1, 40, Lit48, 8194);
    test$Mnrunner$Mnon$Mnbad$Mnend$Mnname = new ModuleMethod((ModuleBody)localObject1, 41, Lit49, 4097);
    test$Mnrunner$Mnon$Mnbad$Mnend$Mnname$Ex = new ModuleMethod((ModuleBody)localObject1, 42, Lit50, 8194);
    test$Mnresult$Mnalist = new ModuleMethod((ModuleBody)localObject1, 43, Lit51, 4097);
    test$Mnresult$Mnalist$Ex = new ModuleMethod((ModuleBody)localObject1, 44, Lit52, 8194);
    test$Mnrunner$Mnaux$Mnvalue = new ModuleMethod((ModuleBody)localObject1, 45, Lit53, 4097);
    test$Mnrunner$Mnaux$Mnvalue$Ex = new ModuleMethod((ModuleBody)localObject1, 46, Lit54, 8194);
    test$Mnrunner$Mnreset = new ModuleMethod((ModuleBody)localObject1, 47, Lit55, 4097);
    test$Mnrunner$Mngroup$Mnpath = new ModuleMethod((ModuleBody)localObject1, 48, Lit56, 4097);
    $Pctest$Mnnull$Mncallback = new ModuleMethod((ModuleBody)localObject1, 49, Lit57, 4097);
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 50, null, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "testing.scm:182");
    lambda$Fn1 = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 51, null, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "testing.scm:187");
    lambda$Fn2 = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 52, null, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "testing.scm:188");
    lambda$Fn3 = (ModuleMethod)localObject2;
    test$Mnrunner$Mnnull = new ModuleMethod((ModuleBody)localObject1, 53, Lit58, 0);
    test$Mnrunner$Mnsimple = new ModuleMethod((ModuleBody)localObject1, 54, Lit59, 0);
    test$Mnrunner$Mnget = new ModuleMethod((ModuleBody)localObject1, 55, Lit60, 0);
    test$Mnrunner$Mncreate = new ModuleMethod((ModuleBody)localObject1, 56, Lit61, 0);
    $Prvt$$Pctest$Mnshould$Mnexecute = new ModuleMethod((ModuleBody)localObject1, 57, Lit62, 4097);
    $Pctest$Mnbegin = new ModuleMethod((ModuleBody)localObject1, 58, Lit63, 8194);
    test$Mnon$Mngroup$Mnbegin$Mnsimple = new ModuleMethod((ModuleBody)localObject1, 59, Lit64, 12291);
    test$Mnon$Mngroup$Mnend$Mnsimple = new ModuleMethod((ModuleBody)localObject1, 60, Lit65, 4097);
    test$Mnon$Mnbad$Mncount$Mnsimple = new ModuleMethod((ModuleBody)localObject1, 61, Lit66, 12291);
    test$Mnon$Mnbad$Mnend$Mnname$Mnsimple = new ModuleMethod((ModuleBody)localObject1, 62, Lit67, 12291);
    test$Mnon$Mnfinal$Mnsimple = new ModuleMethod((ModuleBody)localObject1, 63, Lit68, 4097);
    $Prvt$$Pctest$Mnend = new ModuleMethod((ModuleBody)localObject1, 64, Lit69, 8194);
    $Prvt$test$Mngroup = Macro.make(Lit70, Lit71, $instance);
    test$Mngroup$Mnwith$Mncleanup = Macro.make(Lit72, Lit73, $instance);
    test$Mnon$Mntest$Mnbegin$Mnsimple = new ModuleMethod((ModuleBody)localObject1, 65, Lit74, 4097);
    test$Mnresult$Mnref = Macro.make(Lit75, Lit76, $instance);
    test$Mnon$Mntest$Mnend$Mnsimple = new ModuleMethod((ModuleBody)localObject1, 66, Lit77, 4097);
    test$Mnresult$Mnset$Ex = new ModuleMethod((ModuleBody)localObject1, 67, Lit78, 12291);
    test$Mnresult$Mnclear = new ModuleMethod((ModuleBody)localObject1, 68, Lit79, 4097);
    test$Mnresult$Mnremove = new ModuleMethod((ModuleBody)localObject1, 69, Lit80, 8194);
    test$Mnresult$Mnkind = new ModuleMethod((ModuleBody)localObject1, 70, Lit81, 61440);
    test$Mnpassed$Qu = new ModuleMethod((ModuleBody)localObject1, 71, Lit82, 61440);
    $Prvt$$Pctest$Mnreport$Mnresult = new ModuleMethod((ModuleBody)localObject1, 72, Lit83, 0);
    $Prvt$$Pctest$Mnevaluate$Mnwith$Mncatch = Macro.make(Lit84, Lit85, $instance);
    $Prvt$$Pctest$Mnon$Mntest$Mnbegin = new ModuleMethod((ModuleBody)localObject1, 73, Lit86, 4097);
    $Prvt$$Pctest$Mnon$Mntest$Mnend = new ModuleMethod((ModuleBody)localObject1, 74, Lit87, 8194);
    test$Mnrunner$Mntest$Mnname = new ModuleMethod((ModuleBody)localObject1, 75, Lit88, 4097);
    $Prvt$$Pctest$Mncomp2body = Macro.make(Lit89, Lit90, $instance);
    $Prvt$$Pctest$Mnapproximimate$Eq = new ModuleMethod((ModuleBody)localObject1, 76, Lit91, 4097);
    $Prvt$$Pctest$Mncomp1body = Macro.make(Lit92, Lit93, $instance);
    localObject2 = Lit94;
    localObject3 = new ModuleMethod((ModuleBody)localObject1, 77, null, 4097);
    ((PropertySet)localObject3).setProperty("source-location", "testing.scm:660");
    test$Mnend = Macro.make(localObject2, (Procedure)localObject3, $instance);
    localObject2 = Lit99;
    localObject3 = new ModuleMethod((ModuleBody)localObject1, 78, null, 4097);
    ((PropertySet)localObject3).setProperty("source-location", "testing.scm:669");
    test$Mnassert = Macro.make(localObject2, (Procedure)localObject3, $instance);
    localObject2 = Lit104;
    localObject3 = new ModuleMethod((ModuleBody)localObject1, 79, null, 4097);
    ((PropertySet)localObject3).setProperty("source-location", "testing.scm:696");
    test$Mneqv = Macro.make(localObject2, (Procedure)localObject3, $instance);
    localObject2 = Lit106;
    localObject3 = new ModuleMethod((ModuleBody)localObject1, 80, null, 4097);
    ((PropertySet)localObject3).setProperty("source-location", "testing.scm:698");
    test$Mneq = Macro.make(localObject2, (Procedure)localObject3, $instance);
    localObject2 = Lit108;
    localObject3 = new ModuleMethod((ModuleBody)localObject1, 81, null, 4097);
    ((PropertySet)localObject3).setProperty("source-location", "testing.scm:700");
    test$Mnequal = Macro.make(localObject2, (Procedure)localObject3, $instance);
    localObject2 = Lit110;
    localObject3 = new ModuleMethod((ModuleBody)localObject1, 82, null, 4097);
    ((PropertySet)localObject3).setProperty("source-location", "testing.scm:702");
    test$Mnapproximate = Macro.make(localObject2, (Procedure)localObject3, $instance);
    $Prvt$$Pctest$Mnerror = Macro.make(Lit115, Lit116, $instance);
    localObject2 = Lit117;
    localObject3 = new ModuleMethod((ModuleBody)localObject1, 83, null, 4097);
    ((PropertySet)localObject3).setProperty("source-location", "testing.scm:843");
    test$Mnerror = Macro.make(localObject2, (Procedure)localObject3, $instance);
    test$Mnapply = new ModuleMethod((ModuleBody)localObject1, 84, Lit124, 61441);
    test$Mnwith$Mnrunner = Macro.make(Lit125, Lit126, $instance);
    $Prvt$$Pctest$Mnmatch$Mnnth = new ModuleMethod((ModuleBody)localObject1, 85, Lit127, 8194);
    test$Mnmatch$Mnnth = Macro.make(Lit128, Lit129, $instance);
    $Prvt$$Pctest$Mnmatch$Mnall = new ModuleMethod((ModuleBody)localObject1, 86, Lit130, 61440);
    test$Mnmatch$Mnall = Macro.make(Lit131, Lit132, $instance);
    $Prvt$$Pctest$Mnmatch$Mnany = new ModuleMethod((ModuleBody)localObject1, 87, Lit133, 61440);
    test$Mnmatch$Mnany = Macro.make(Lit134, Lit135, $instance);
    $Prvt$$Pctest$Mnas$Mnspecifier = new ModuleMethod((ModuleBody)localObject1, 88, Lit136, 4097);
    test$Mnskip = Macro.make(Lit137, Lit138, $instance);
    test$Mnexpect$Mnfail = Macro.make(Lit139, Lit140, $instance);
    test$Mnmatch$Mnname = new ModuleMethod((ModuleBody)localObject1, 89, Lit141, 4097);
    test$Mnread$Mneval$Mnstring = new ModuleMethod((ModuleBody)localObject1, 90, Lit142, 4097);
    $instance.run();
  }
  
  public testing()
  {
    ModuleInfo.register(this);
  }
  
  /* Error */
  public static Object isTestPassed$V(Object[] paramArrayOfObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: invokestatic 696	gnu/lists/LList:makeList	([Ljava/lang/Object;I)Lgnu/lists/LList;
    //   5: astore_0
    //   6: aload_0
    //   7: invokestatic 1901	kawa/lib/lists:isPair	(Ljava/lang/Object;)Z
    //   10: ifeq +52 -> 62
    //   13: getstatic 320	kawa/lib/lists:car	Lgnu/expr/GenericProc;
    //   16: aload_0
    //   17: invokevirtual 326	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   20: astore_0
    //   21: getstatic 745	gnu/kawa/slib/testing:Lit1	Lgnu/mapping/SimpleSymbol;
    //   24: astore_1
    //   25: aload_0
    //   26: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   29: astore_2
    //   30: aload_1
    //   31: aload_2
    //   32: invokestatic 666	gnu/kawa/slib/testing:testResultAlist	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   35: invokestatic 671	kawa/lib/lists:assq	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   38: astore_0
    //   39: aload_0
    //   40: getstatic 310	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   43: if_acmpeq +26 -> 69
    //   46: getstatic 335	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   49: aload_0
    //   50: invokevirtual 326	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   53: astore_0
    //   54: aload_0
    //   55: getstatic 1640	gnu/kawa/slib/testing:Lit11	Lgnu/lists/PairWithPosition;
    //   58: invokestatic 1904	kawa/lib/lists:memq	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   61: areturn
    //   62: invokestatic 534	gnu/kawa/slib/testing:testRunnerGet	()Ljava/lang/Object;
    //   65: astore_0
    //   66: goto -45 -> 21
    //   69: getstatic 310	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   72: astore_0
    //   73: goto -19 -> 54
    //   76: astore_1
    //   77: new 448	gnu/mapping/WrongType
    //   80: dup
    //   81: aload_1
    //   82: ldc_w 687
    //   85: iconst_0
    //   86: aload_0
    //   87: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	paramArrayOfObject	Object[]
    //   24	7	1	localSimpleSymbol	SimpleSymbol
    //   76	6	1	localClassCastException	ClassCastException
    //   29	3	2	localMnrunner	test.Mnrunner
    // Exception table:
    //   from	to	target	type
    //   25	30	76	java/lang/ClassCastException
  }
  
  public static boolean isTestRunner(Object paramObject)
  {
    return paramObject instanceof test.Mnrunner;
  }
  
  static Boolean lambda1(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return Boolean.FALSE;
  }
  
  static Object lambda16(Object paramObject)
  {
    Object localObject = LList.list2(paramObject, LList.list2(Lit15, $PcTestSourceLine2(paramObject)));
    paramObject = SyntaxPattern.allocVars(3, null);
    if (Lit95.match(localObject, (Object[])paramObject, 0))
    {
      localObject = TemplateScope.make();
      return Lit96.execute((Object[])paramObject, (TemplateScope)localObject);
    }
    if (Lit97.match(localObject, (Object[])paramObject, 0))
    {
      localObject = TemplateScope.make();
      return Lit98.execute((Object[])paramObject, (TemplateScope)localObject);
    }
    return syntax_case.error("syntax-case", localObject);
  }
  
  static Object lambda17(Object paramObject)
  {
    Object localObject = LList.list2(paramObject, LList.list2(Lit15, $PcTestSourceLine2(paramObject)));
    paramObject = SyntaxPattern.allocVars(4, null);
    if (Lit100.match(localObject, (Object[])paramObject, 0))
    {
      localObject = TemplateScope.make();
      return Lit101.execute((Object[])paramObject, (TemplateScope)localObject);
    }
    if (Lit102.match(localObject, (Object[])paramObject, 0))
    {
      localObject = TemplateScope.make();
      return Lit103.execute((Object[])paramObject, (TemplateScope)localObject);
    }
    return syntax_case.error("syntax-case", localObject);
  }
  
  static Object lambda18(Object paramObject)
  {
    TemplateScope localTemplateScope = TemplateScope.make();
    return $PcTestComp2(Lit105.execute(null, localTemplateScope), paramObject);
  }
  
  static Object lambda19(Object paramObject)
  {
    TemplateScope localTemplateScope = TemplateScope.make();
    return $PcTestComp2(Lit107.execute(null, localTemplateScope), paramObject);
  }
  
  static Boolean lambda2(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return Boolean.FALSE;
  }
  
  static Object lambda20(Object paramObject)
  {
    TemplateScope localTemplateScope = TemplateScope.make();
    return $PcTestComp2(Lit109.execute(null, localTemplateScope), paramObject);
  }
  
  static Object lambda21(Object paramObject)
  {
    Object localObject = LList.list2(paramObject, LList.list2(Lit15, $PcTestSourceLine2(paramObject)));
    paramObject = SyntaxPattern.allocVars(6, null);
    if (Lit111.match(localObject, (Object[])paramObject, 0))
    {
      localObject = TemplateScope.make();
      return Lit112.execute((Object[])paramObject, (TemplateScope)localObject);
    }
    if (Lit113.match(localObject, (Object[])paramObject, 0))
    {
      localObject = TemplateScope.make();
      return Lit114.execute((Object[])paramObject, (TemplateScope)localObject);
    }
    return syntax_case.error("syntax-case", localObject);
  }
  
  static Object lambda22(Object paramObject)
  {
    Object localObject = LList.list2(paramObject, LList.list2(Lit15, $PcTestSourceLine2(paramObject)));
    paramObject = SyntaxPattern.allocVars(5, null);
    if (Lit118.match(localObject, (Object[])paramObject, 0))
    {
      localObject = TemplateScope.make();
      return Lit119.execute((Object[])paramObject, (TemplateScope)localObject);
    }
    if (Lit120.match(localObject, (Object[])paramObject, 0))
    {
      localObject = TemplateScope.make();
      return Lit121.execute((Object[])paramObject, (TemplateScope)localObject);
    }
    if (Lit122.match(localObject, (Object[])paramObject, 0))
    {
      localObject = TemplateScope.make();
      return Lit123.execute((Object[])paramObject, (TemplateScope)localObject);
    }
    return syntax_case.error("syntax-case", localObject);
  }
  
  static Boolean lambda3(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return Boolean.FALSE;
  }
  
  public static Object testApply$V(Object paramObject, Object[] paramArrayOfObject)
  {
    localframe1 = new frame1();
    localframe1.first = paramObject;
    localframe1.rest = LList.makeList(paramArrayOfObject, 0);
    if (isTestRunner(localframe1.first))
    {
      localframe1.saved$Mnrunner$1 = ((Procedure)test$Mnrunner$Mncurrent).apply0();
      return misc.dynamicWind(localframe1.lambda$Fn5, localframe1.lambda$Fn6, localframe1.lambda$Fn7);
    }
    paramArrayOfObject = ((Procedure)test$Mnrunner$Mncurrent).apply0();
    if (paramArrayOfObject != Boolean.FALSE) {
      for (;;)
      {
        try
        {
          paramObject = (test.Mnrunner)paramArrayOfObject;
          localObject = $PcTestRunnerRunList((test.Mnrunner)paramObject);
          if (!lists.isNull(localframe1.rest)) {}
        }
        catch (ClassCastException paramObject)
        {
          try
          {
            test.Mnrunner localMnrunner;
            localObject = (test.Mnrunner)paramObject;
            return paramArrayOfObject.apply2(testRunnerOnFinal((test.Mnrunner)localObject), localframe1.r);
          }
          catch (ClassCastException paramArrayOfObject)
          {
            Object localObject;
            throw new WrongType(paramArrayOfObject, "test-runner-on-final", 0, paramObject);
          }
          paramObject = paramObject;
          throw new WrongType((ClassCastException)paramObject, "%test-runner-run-list", 0, paramArrayOfObject);
        }
        try
        {
          paramObject = (test.Mnrunner)paramArrayOfObject;
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "%test-runner-run-list!", 0, paramArrayOfObject);
        }
        try
        {
          paramArrayOfObject = (LList)localObject;
          $PcTestRunnerRunList$Ex((test.Mnrunner)paramObject, lists.reverse$Ex(paramArrayOfObject));
          return Scheme.applyToArgs.apply1(localframe1.first);
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "reverse!", 1, localObject);
        }
        try
        {
          localMnrunner = (test.Mnrunner)paramArrayOfObject;
          if (localObject == Boolean.TRUE)
          {
            paramObject = LList.list1(localframe1.first);
            $PcTestRunnerRunList$Ex(localMnrunner, paramObject);
            Scheme.apply.apply2(test$Mnapply, localframe1.rest);
          }
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "%test-runner-run-list!", 0, paramArrayOfObject);
        }
        try
        {
          paramObject = (test.Mnrunner)paramArrayOfObject;
          $PcTestRunnerRunList$Ex((test.Mnrunner)paramObject, localObject);
          return Values.empty;
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "%test-runner-run-list!", 0, paramArrayOfObject);
        }
        paramObject = lists.cons(localframe1.first, localObject);
      }
    }
    localframe1.r = testRunnerCreate();
    localframe1.saved$Mnrunner = ((Procedure)test$Mnrunner$Mncurrent).apply0();
    misc.dynamicWind(localframe1.lambda$Fn8, localframe1.lambda$Fn9, localframe1.lambda$Fn10);
    paramArrayOfObject = Scheme.applyToArgs;
    paramObject = localframe1.r;
  }
  
  public static Procedure testMatchName(Object paramObject)
  {
    frame5 localframe5 = new frame5();
    localframe5.name = paramObject;
    return localframe5.lambda$Fn14;
  }
  
  public static void testOnBadCountSimple(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    $PcTestOnBadCountWrite(paramObject1, paramObject2, paramObject3, ports.current$Mnoutput$Mnport.apply0());
    try
    {
      Object localObject = (test.Mnrunner)paramObject1;
      localObject = testRunnerAuxValue((test.Mnrunner)localObject);
      if (ports.isOutputPort(localObject)) {
        $PcTestOnBadCountWrite(paramObject1, paramObject2, paramObject3, localObject);
      }
      return;
    }
    catch (ClassCastException paramObject2)
    {
      throw new WrongType((ClassCastException)paramObject2, "test-runner-aux-value", 0, paramObject1);
    }
  }
  
  public static Object testOnBadEndNameSimple(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return misc.error$V(strings.stringAppend(new Object[] { $PcTestFormatLine(paramObject1), "test-end ", paramObject2, " does not match test-begin ", paramObject3 }), new Object[0]);
  }
  
  public static void testOnFinalSimple(Object paramObject)
  {
    $PcTestFinalReportSimple(paramObject, ports.current$Mnoutput$Mnport.apply0());
    try
    {
      Object localObject = (test.Mnrunner)paramObject;
      localObject = testRunnerAuxValue((test.Mnrunner)localObject);
      if (ports.isOutputPort(localObject)) {
        $PcTestFinalReportSimple(paramObject, localObject);
      }
      return;
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "test-runner-aux-value", 0, paramObject);
    }
  }
  
  public static Boolean testOnGroupBeginSimple(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    for (;;)
    {
      try
      {
        paramObject3 = (test.Mnrunner)paramObject1;
        if (lists.isNull(testRunnerGroupStack((test.Mnrunner)paramObject3)))
        {
          ports.display("%%%% Starting test ");
          ports.display(paramObject2);
          if (!strings.isString(Boolean.TRUE)) {
            continue;
          }
          paramObject3 = Boolean.TRUE;
        }
      }
      catch (ClassCastException paramObject2)
      {
        Object localObject;
        test.Mnrunner localMnrunner;
        throw new WrongType((ClassCastException)paramObject2, "test-runner-group-stack", 0, paramObject1);
      }
      try
      {
        localObject = Path.valueOf(paramObject3);
        localObject = ports.openOutputFile((Path)localObject);
        ports.display("%%%% Starting test ", localObject);
        ports.display(paramObject2, localObject);
        ports.newline(localObject);
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "open-output-file", 1, paramObject3);
      }
      try
      {
        localMnrunner = (test.Mnrunner)paramObject1;
        testRunnerAuxValue$Ex(localMnrunner, localObject);
        ports.display("  (Writing full log to \"");
        ports.display(paramObject3);
        ports.display("\")");
        ports.newline();
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "test-runner-aux-value!", 0, paramObject1);
      }
      try
      {
        paramObject3 = (test.Mnrunner)paramObject1;
        paramObject1 = testRunnerAuxValue((test.Mnrunner)paramObject3);
        if (ports.isOutputPort(paramObject1))
        {
          ports.display("Group begin: ", paramObject1);
          ports.display(paramObject2, paramObject1);
          ports.newline(paramObject1);
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "test-runner-aux-value", 0, paramObject1);
      }
      paramObject3 = strings.stringAppend(new Object[] { paramObject2, ".log" });
    }
  }
  
  /* Error */
  public static Boolean testOnGroupEndSimple(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   4: astore_1
    //   5: aload_1
    //   6: invokestatic 1986	gnu/kawa/slib/testing:testRunnerAuxValue	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   9: astore_1
    //   10: aload_1
    //   11: invokestatic 1989	kawa/lib/ports:isOutputPort	(Ljava/lang/Object;)Z
    //   14: ifeq +35 -> 49
    //   17: ldc_w 2030
    //   20: aload_1
    //   21: invokestatic 621	kawa/lib/ports:display	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   24: getstatic 320	kawa/lib/lists:car	Lgnu/expr/GenericProc;
    //   27: astore_2
    //   28: aload_0
    //   29: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   32: astore_3
    //   33: aload_2
    //   34: aload_3
    //   35: invokestatic 443	gnu/kawa/slib/testing:testRunnerGroupStack	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   38: invokevirtual 326	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   41: aload_1
    //   42: invokestatic 621	kawa/lib/ports:display	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   45: aload_1
    //   46: invokestatic 625	kawa/lib/ports:newline	(Ljava/lang/Object;)V
    //   49: getstatic 310	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   52: areturn
    //   53: astore_1
    //   54: new 448	gnu/mapping/WrongType
    //   57: dup
    //   58: aload_1
    //   59: ldc_w 1544
    //   62: iconst_0
    //   63: aload_0
    //   64: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   67: athrow
    //   68: astore_1
    //   69: new 448	gnu/mapping/WrongType
    //   72: dup
    //   73: aload_1
    //   74: ldc_w 475
    //   77: iconst_0
    //   78: aload_0
    //   79: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   82: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	paramObject	Object
    //   4	42	1	localObject	Object
    //   53	6	1	localClassCastException1	ClassCastException
    //   68	6	1	localClassCastException2	ClassCastException
    //   27	7	2	localGenericProc	gnu.expr.GenericProc
    //   32	3	3	localMnrunner	test.Mnrunner
    // Exception table:
    //   from	to	target	type
    //   0	5	53	java/lang/ClassCastException
    //   28	33	68	java/lang/ClassCastException
  }
  
  static Object testOnTestBeginSimple(Object paramObject)
  {
    for (;;)
    {
      try
      {
        localObject1 = (test.Mnrunner)paramObject;
        localObject1 = testRunnerAuxValue((test.Mnrunner)localObject1);
        if (!ports.isOutputPort(localObject1)) {}
      }
      catch (ClassCastException localClassCastException1)
      {
        Object localObject1;
        Object localObject2;
        Object localObject4;
        Object localObject3;
        throw new WrongType(localClassCastException1, "test-runner-aux-value", 0, paramObject);
      }
      try
      {
        localObject2 = (test.Mnrunner)paramObject;
        localObject4 = testResultAlist((test.Mnrunner)localObject2);
        paramObject = lists.assq(Lit4, localObject4);
        localObject2 = lists.assq(Lit5, localObject4);
        localObject3 = lists.assq(Lit6, localObject4);
        localObject4 = lists.assq(Lit7, localObject4);
        ports.display("Test begin:", localObject1);
        ports.newline(localObject1);
        if (localObject4 != Boolean.FALSE) {
          $PcTestWriteResult1(localObject4, localObject1);
        }
        if (paramObject != Boolean.FALSE) {
          $PcTestWriteResult1(paramObject, localObject1);
        }
        if (localObject2 != Boolean.FALSE) {
          $PcTestWriteResult1(localObject2, localObject1);
        }
        if (paramObject != Boolean.FALSE) {
          return $PcTestWriteResult1(localObject3, localObject1);
        }
        return Values.empty;
      }
      catch (ClassCastException localClassCastException2)
      {
        throw new WrongType(localClassCastException2, "test-result-alist", 0, paramObject);
      }
    }
    return Values.empty;
  }
  
  public static Object testOnTestEndSimple(Object paramObject)
  {
    for (;;)
    {
      try
      {
        localObject1 = (test.Mnrunner)paramObject;
        localObject2 = testRunnerAuxValue((test.Mnrunner)localObject1);
        localObject1 = Lit1;
      }
      catch (ClassCastException localClassCastException1)
      {
        Object localObject1;
        Object localObject2;
        Object localObject3;
        Object localObject4;
        Object localObject5;
        throw new WrongType(localClassCastException1, "test-runner-aux-value", 0, paramObject);
      }
      try
      {
        localObject3 = (test.Mnrunner)paramObject;
        localObject1 = lists.assq(localObject1, testResultAlist((test.Mnrunner)localObject3));
        if (localObject1 != Boolean.FALSE)
        {
          localObject1 = lists.cdr.apply1(localObject1);
          if (lists.memq(localObject1, Lit8) == Boolean.FALSE) {}
        }
      }
      catch (ClassCastException localClassCastException2)
      {
        throw new WrongType(localClassCastException2, "test-result-alist", 0, paramObject);
      }
      try
      {
        localObject3 = (test.Mnrunner)paramObject;
        localObject3 = testResultAlist((test.Mnrunner)localObject3);
        localObject4 = lists.assq(Lit4, localObject3);
        localObject5 = lists.assq(Lit5, localObject3);
        localObject3 = lists.assq(Lit7, localObject3);
        if ((localObject4 != Boolean.FALSE) || (localObject5 != Boolean.FALSE))
        {
          if (localObject4 != Boolean.FALSE) {
            ports.display(lists.cdr.apply1(localObject4));
          }
          ports.display(":");
          if (localObject5 != Boolean.FALSE) {
            ports.display(lists.cdr.apply1(localObject5));
          }
          ports.display(": ");
        }
        if (localObject1 == Lit9)
        {
          localObject1 = "XPASS";
          ports.display(localObject1);
          if (localObject3 != Boolean.FALSE)
          {
            ports.display(" ");
            ports.display(lists.cdr.apply1(localObject3));
          }
          ports.newline();
          if (!ports.isOutputPort(localObject2)) {
            continue;
          }
          ports.display("Test end:", localObject2);
          ports.newline(localObject2);
        }
      }
      catch (ClassCastException localClassCastException3)
      {
        throw new WrongType(localClassCastException3, "test-result-alist", 0, paramObject);
      }
      try
      {
        localObject1 = (test.Mnrunner)paramObject;
        paramObject = testResultAlist((test.Mnrunner)localObject1);
        if (lists.isPair(paramObject))
        {
          localObject1 = lists.car.apply1(paramObject);
          if (lists.memq(lists.car.apply1(localObject1), Lit10) == Boolean.FALSE) {
            $PcTestWriteResult1(localObject1, localObject2);
          }
          paramObject = lists.cdr.apply1(paramObject);
          continue;
          localObject1 = Boolean.FALSE;
          continue;
          localObject1 = "FAIL";
        }
        else
        {
          return Values.empty;
        }
      }
      catch (ClassCastException localClassCastException4)
      {
        throw new WrongType(localClassCastException4, "test-result-alist", 0, paramObject);
      }
    }
    return Values.empty;
  }
  
  public static Object testReadEvalString(Object paramObject)
  {
    try
    {
      Object localObject = (CharSequence)paramObject;
      paramObject = ports.openInputString((CharSequence)localObject);
      localObject = ports.read((InPort)paramObject);
      if (ports.isEofObject(readchar.readChar.apply1(paramObject))) {
        return Eval.eval.apply1(localObject);
      }
      return misc.error$V("(not at eof)", new Object[0]);
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "open-input-string", 1, paramObject);
    }
  }
  
  public static Object testResultAlist(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.result$Mnalist;
  }
  
  public static void testResultAlist$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.result$Mnalist = paramObject;
  }
  
  public static void testResultClear(Object paramObject)
  {
    try
    {
      test.Mnrunner localMnrunner = (test.Mnrunner)paramObject;
      testResultAlist$Ex(localMnrunner, LList.Empty);
      return;
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "test-result-alist!", 0, paramObject);
    }
  }
  
  public static Object testResultKind$V(Object[] paramArrayOfObject)
  {
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(paramArrayOfObject)) {
      paramArrayOfObject = lists.car.apply1(paramArrayOfObject);
    }
    for (;;)
    {
      SimpleSymbol localSimpleSymbol = Lit1;
      try
      {
        test.Mnrunner localMnrunner = (test.Mnrunner)paramArrayOfObject;
        paramArrayOfObject = lists.assq(localSimpleSymbol, testResultAlist(localMnrunner));
        if (paramArrayOfObject != Boolean.FALSE)
        {
          return lists.cdr.apply1(paramArrayOfObject);
          paramArrayOfObject = ((Procedure)test$Mnrunner$Mncurrent).apply0();
          continue;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "test-result-alist", 0, paramArrayOfObject);
      }
    }
  }
  
  /* Error */
  public static void testResultRemove(Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: new 9	gnu/kawa/slib/testing$frame
    //   3: dup
    //   4: invokespecial 2080	gnu/kawa/slib/testing$frame:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   12: astore_3
    //   13: aload_3
    //   14: invokestatic 666	gnu/kawa/slib/testing:testResultAlist	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   17: astore_3
    //   18: aload_2
    //   19: aload_1
    //   20: aload_3
    //   21: invokestatic 671	kawa/lib/lists:assq	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   24: putfield 2082	gnu/kawa/slib/testing$frame:p	Ljava/lang/Object;
    //   27: aload_2
    //   28: getfield 2082	gnu/kawa/slib/testing$frame:p	Ljava/lang/Object;
    //   31: getstatic 310	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   34: if_acmpeq +17 -> 51
    //   37: aload_0
    //   38: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   41: astore_1
    //   42: aload_1
    //   43: aload_2
    //   44: aload_3
    //   45: invokevirtual 2085	gnu/kawa/slib/testing$frame:lambda4loop	(Ljava/lang/Object;)Ljava/lang/Object;
    //   48: invokestatic 540	gnu/kawa/slib/testing:testResultAlist$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   51: return
    //   52: astore_1
    //   53: new 448	gnu/mapping/WrongType
    //   56: dup
    //   57: aload_1
    //   58: ldc_w 687
    //   61: iconst_0
    //   62: aload_0
    //   63: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   66: athrow
    //   67: astore_1
    //   68: new 448	gnu/mapping/WrongType
    //   71: dup
    //   72: aload_1
    //   73: ldc_w 597
    //   76: iconst_0
    //   77: aload_0
    //   78: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   81: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	82	0	paramObject1	Object
    //   0	82	1	paramObject2	Object
    //   7	37	2	localframe	frame
    //   12	33	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   8	13	52	java/lang/ClassCastException
    //   37	42	67	java/lang/ClassCastException
  }
  
  /* Error */
  public static Object testResultSet$Ex(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    // Byte code:
    //   0: aload_0
    //   1: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   4: astore_3
    //   5: aload_3
    //   6: invokestatic 666	gnu/kawa/slib/testing:testResultAlist	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   9: astore 4
    //   11: aload_1
    //   12: aload 4
    //   14: invokestatic 671	kawa/lib/lists:assq	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   17: astore_3
    //   18: aload_3
    //   19: getstatic 310	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   22: if_acmpeq +17 -> 39
    //   25: aload_3
    //   26: checkcast 1231	gnu/lists/Pair
    //   29: astore_0
    //   30: aload_0
    //   31: aload_2
    //   32: invokestatic 2089	kawa/lib/lists:setCdr$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   35: getstatic 595	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   38: areturn
    //   39: aload_0
    //   40: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   43: astore_3
    //   44: aload_3
    //   45: aload_1
    //   46: aload_2
    //   47: invokestatic 418	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   50: aload 4
    //   52: invokestatic 418	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   55: invokestatic 540	gnu/kawa/slib/testing:testResultAlist$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   58: getstatic 595	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   61: areturn
    //   62: astore_1
    //   63: new 448	gnu/mapping/WrongType
    //   66: dup
    //   67: aload_1
    //   68: ldc_w 687
    //   71: iconst_0
    //   72: aload_0
    //   73: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   76: athrow
    //   77: astore_0
    //   78: new 448	gnu/mapping/WrongType
    //   81: dup
    //   82: aload_0
    //   83: ldc_w 2091
    //   86: iconst_1
    //   87: aload_3
    //   88: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   91: athrow
    //   92: astore_1
    //   93: new 448	gnu/mapping/WrongType
    //   96: dup
    //   97: aload_1
    //   98: ldc_w 597
    //   101: iconst_0
    //   102: aload_0
    //   103: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   106: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	107	0	paramObject1	Object
    //   0	107	1	paramObject2	Object
    //   0	107	2	paramObject3	Object
    //   4	84	3	localObject1	Object
    //   9	42	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   0	5	62	java/lang/ClassCastException
    //   25	30	77	java/lang/ClassCastException
    //   39	44	92	java/lang/ClassCastException
  }
  
  public static Object testRunnerAuxValue(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.aux$Mnvalue;
  }
  
  public static void testRunnerAuxValue$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.aux$Mnvalue = paramObject;
  }
  
  public static Object testRunnerCreate()
  {
    return Scheme.applyToArgs.apply1(((Procedure)test$Mnrunner$Mnfactory).apply0());
  }
  
  public static Object testRunnerFailCount(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.fail$Mncount;
  }
  
  public static void testRunnerFailCount$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.fail$Mncount = paramObject;
  }
  
  public static Object testRunnerGet()
  {
    Object localObject = ((Procedure)test$Mnrunner$Mncurrent).apply0();
    if (localObject == Boolean.FALSE) {
      misc.error$V("test-runner not initialized - test-begin missing?", new Object[0]);
    }
    return localObject;
  }
  
  /* Error */
  public static LList testRunnerGroupPath(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   4: astore_1
    //   5: aload_1
    //   6: invokestatic 443	gnu/kawa/slib/testing:testRunnerGroupStack	(Lgnu/kawa/slib/test$Mnrunner;)Ljava/lang/Object;
    //   9: astore_0
    //   10: aload_0
    //   11: checkcast 484	gnu/lists/LList
    //   14: astore_1
    //   15: aload_1
    //   16: invokestatic 2106	kawa/lib/lists:reverse	(Lgnu/lists/LList;)Lgnu/lists/LList;
    //   19: areturn
    //   20: astore_1
    //   21: new 448	gnu/mapping/WrongType
    //   24: dup
    //   25: aload_1
    //   26: ldc_w 475
    //   29: iconst_0
    //   30: aload_0
    //   31: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   34: athrow
    //   35: astore_1
    //   36: new 448	gnu/mapping/WrongType
    //   39: dup
    //   40: aload_1
    //   41: ldc_w 2107
    //   44: iconst_1
    //   45: aload_0
    //   46: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   49: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	paramObject	Object
    //   4	12	1	localObject	Object
    //   20	6	1	localClassCastException1	ClassCastException
    //   35	6	1	localClassCastException2	ClassCastException
    // Exception table:
    //   from	to	target	type
    //   0	5	20	java/lang/ClassCastException
    //   10	15	35	java/lang/ClassCastException
  }
  
  public static Object testRunnerGroupStack(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.group$Mnstack;
  }
  
  public static void testRunnerGroupStack$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.group$Mnstack = paramObject;
  }
  
  public static test.Mnrunner testRunnerNull()
  {
    test.Mnrunner localMnrunner = $PcTestRunnerAlloc();
    testRunnerReset(localMnrunner);
    testRunnerOnGroupBegin$Ex(localMnrunner, lambda$Fn1);
    testRunnerOnGroupEnd$Ex(localMnrunner, $Pctest$Mnnull$Mncallback);
    testRunnerOnFinal$Ex(localMnrunner, $Pctest$Mnnull$Mncallback);
    testRunnerOnTestBegin$Ex(localMnrunner, $Pctest$Mnnull$Mncallback);
    testRunnerOnTestEnd$Ex(localMnrunner, $Pctest$Mnnull$Mncallback);
    testRunnerOnBadCount$Ex(localMnrunner, lambda$Fn2);
    testRunnerOnBadEndName$Ex(localMnrunner, lambda$Fn3);
    return localMnrunner;
  }
  
  public static Object testRunnerOnBadCount(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.on$Mnbad$Mncount;
  }
  
  public static void testRunnerOnBadCount$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.on$Mnbad$Mncount = paramObject;
  }
  
  public static Object testRunnerOnBadEndName(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.on$Mnbad$Mnend$Mnname;
  }
  
  public static void testRunnerOnBadEndName$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.on$Mnbad$Mnend$Mnname = paramObject;
  }
  
  public static Object testRunnerOnFinal(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.on$Mnfinal;
  }
  
  public static void testRunnerOnFinal$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.on$Mnfinal = paramObject;
  }
  
  public static Object testRunnerOnGroupBegin(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.on$Mngroup$Mnbegin;
  }
  
  public static void testRunnerOnGroupBegin$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.on$Mngroup$Mnbegin = paramObject;
  }
  
  public static Object testRunnerOnGroupEnd(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.on$Mngroup$Mnend;
  }
  
  public static void testRunnerOnGroupEnd$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.on$Mngroup$Mnend = paramObject;
  }
  
  public static Object testRunnerOnTestBegin(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.on$Mntest$Mnbegin;
  }
  
  public static void testRunnerOnTestBegin$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.on$Mntest$Mnbegin = paramObject;
  }
  
  public static Object testRunnerOnTestEnd(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.on$Mntest$Mnend;
  }
  
  public static void testRunnerOnTestEnd$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.on$Mntest$Mnend = paramObject;
  }
  
  public static Object testRunnerPassCount(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.pass$Mncount;
  }
  
  public static void testRunnerPassCount$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.pass$Mncount = paramObject;
  }
  
  /* Error */
  public static void testRunnerReset(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   4: astore_1
    //   5: aload_1
    //   6: getstatic 860	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   9: invokestatic 540	gnu/kawa/slib/testing:testResultAlist$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   12: aload_0
    //   13: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   16: astore_1
    //   17: aload_1
    //   18: getstatic 616	gnu/kawa/slib/testing:Lit0	Lgnu/math/IntNum;
    //   21: invokestatic 775	gnu/kawa/slib/testing:testRunnerPassCount$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   24: aload_0
    //   25: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   28: astore_1
    //   29: aload_1
    //   30: getstatic 616	gnu/kawa/slib/testing:Lit0	Lgnu/math/IntNum;
    //   33: invokestatic 784	gnu/kawa/slib/testing:testRunnerFailCount$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   36: aload_0
    //   37: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   40: astore_1
    //   41: aload_1
    //   42: getstatic 616	gnu/kawa/slib/testing:Lit0	Lgnu/math/IntNum;
    //   45: invokestatic 787	gnu/kawa/slib/testing:testRunnerXpassCount$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   48: aload_0
    //   49: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   52: astore_1
    //   53: aload_1
    //   54: getstatic 616	gnu/kawa/slib/testing:Lit0	Lgnu/math/IntNum;
    //   57: invokestatic 790	gnu/kawa/slib/testing:testRunnerXfailCount$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   60: aload_0
    //   61: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   64: astore_1
    //   65: aload_1
    //   66: getstatic 616	gnu/kawa/slib/testing:Lit0	Lgnu/math/IntNum;
    //   69: invokestatic 793	gnu/kawa/slib/testing:testRunnerSkipCount$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   72: aload_0
    //   73: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   76: astore_1
    //   77: aload_1
    //   78: getstatic 616	gnu/kawa/slib/testing:Lit0	Lgnu/math/IntNum;
    //   81: invokestatic 778	gnu/kawa/slib/testing:$PcTestRunnerTotalCount$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   84: aload_0
    //   85: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   88: astore_1
    //   89: aload_1
    //   90: getstatic 860	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   93: invokestatic 440	gnu/kawa/slib/testing:$PcTestRunnerCountList$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   96: aload_0
    //   97: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   100: astore_1
    //   101: aload_1
    //   102: getstatic 332	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   105: invokestatic 1949	gnu/kawa/slib/testing:$PcTestRunnerRunList$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   108: aload_0
    //   109: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   112: astore_1
    //   113: aload_1
    //   114: getstatic 860	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   117: invokestatic 583	gnu/kawa/slib/testing:$PcTestRunnerSkipList$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   120: aload_0
    //   121: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   124: astore_1
    //   125: aload_1
    //   126: getstatic 860	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   129: invokestatic 586	gnu/kawa/slib/testing:$PcTestRunnerFailList$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   132: aload_0
    //   133: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   136: astore_1
    //   137: aload_1
    //   138: getstatic 860	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   141: invokestatic 422	gnu/kawa/slib/testing:$PcTestRunnerSkipSave$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   144: aload_0
    //   145: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   148: astore_1
    //   149: aload_1
    //   150: getstatic 860	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   153: invokestatic 431	gnu/kawa/slib/testing:$PcTestRunnerFailSave$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   156: aload_0
    //   157: checkcast 6	gnu/kawa/slib/test$Mnrunner
    //   160: astore_1
    //   161: aload_1
    //   162: getstatic 860	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   165: invokestatic 446	gnu/kawa/slib/testing:testRunnerGroupStack$Ex	(Lgnu/kawa/slib/test$Mnrunner;Ljava/lang/Object;)V
    //   168: return
    //   169: astore_1
    //   170: new 448	gnu/mapping/WrongType
    //   173: dup
    //   174: aload_1
    //   175: ldc_w 597
    //   178: iconst_0
    //   179: aload_0
    //   180: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   183: athrow
    //   184: astore_1
    //   185: new 448	gnu/mapping/WrongType
    //   188: dup
    //   189: aload_1
    //   190: ldc_w 795
    //   193: iconst_0
    //   194: aload_0
    //   195: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   198: athrow
    //   199: astore_1
    //   200: new 448	gnu/mapping/WrongType
    //   203: dup
    //   204: aload_1
    //   205: ldc_w 797
    //   208: iconst_0
    //   209: aload_0
    //   210: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   213: athrow
    //   214: astore_1
    //   215: new 448	gnu/mapping/WrongType
    //   218: dup
    //   219: aload_1
    //   220: ldc_w 799
    //   223: iconst_0
    //   224: aload_0
    //   225: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   228: athrow
    //   229: astore_1
    //   230: new 448	gnu/mapping/WrongType
    //   233: dup
    //   234: aload_1
    //   235: ldc_w 801
    //   238: iconst_0
    //   239: aload_0
    //   240: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   243: athrow
    //   244: astore_1
    //   245: new 448	gnu/mapping/WrongType
    //   248: dup
    //   249: aload_1
    //   250: ldc_w 803
    //   253: iconst_0
    //   254: aload_0
    //   255: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   258: athrow
    //   259: astore_1
    //   260: new 448	gnu/mapping/WrongType
    //   263: dup
    //   264: aload_1
    //   265: ldc_w 805
    //   268: iconst_0
    //   269: aload_0
    //   270: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   273: athrow
    //   274: astore_1
    //   275: new 448	gnu/mapping/WrongType
    //   278: dup
    //   279: aload_1
    //   280: ldc_w 467
    //   283: iconst_0
    //   284: aload_0
    //   285: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   288: athrow
    //   289: astore_1
    //   290: new 448	gnu/mapping/WrongType
    //   293: dup
    //   294: aload_1
    //   295: ldc_w 1968
    //   298: iconst_0
    //   299: aload_0
    //   300: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   303: athrow
    //   304: astore_1
    //   305: new 448	gnu/mapping/WrongType
    //   308: dup
    //   309: aload_1
    //   310: ldc_w 605
    //   313: iconst_0
    //   314: aload_0
    //   315: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   318: athrow
    //   319: astore_1
    //   320: new 448	gnu/mapping/WrongType
    //   323: dup
    //   324: aload_1
    //   325: ldc_w 607
    //   328: iconst_0
    //   329: aload_0
    //   330: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   333: athrow
    //   334: astore_1
    //   335: new 448	gnu/mapping/WrongType
    //   338: dup
    //   339: aload_1
    //   340: ldc_w 455
    //   343: iconst_0
    //   344: aload_0
    //   345: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   348: athrow
    //   349: astore_1
    //   350: new 448	gnu/mapping/WrongType
    //   353: dup
    //   354: aload_1
    //   355: ldc_w 461
    //   358: iconst_0
    //   359: aload_0
    //   360: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   363: athrow
    //   364: astore_1
    //   365: new 448	gnu/mapping/WrongType
    //   368: dup
    //   369: aload_1
    //   370: ldc_w 473
    //   373: iconst_0
    //   374: aload_0
    //   375: invokespecial 453	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   378: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	379	0	paramObject	Object
    //   4	158	1	localMnrunner	test.Mnrunner
    //   169	6	1	localClassCastException1	ClassCastException
    //   184	6	1	localClassCastException2	ClassCastException
    //   199	6	1	localClassCastException3	ClassCastException
    //   214	6	1	localClassCastException4	ClassCastException
    //   229	6	1	localClassCastException5	ClassCastException
    //   244	6	1	localClassCastException6	ClassCastException
    //   259	6	1	localClassCastException7	ClassCastException
    //   274	6	1	localClassCastException8	ClassCastException
    //   289	6	1	localClassCastException9	ClassCastException
    //   304	6	1	localClassCastException10	ClassCastException
    //   319	6	1	localClassCastException11	ClassCastException
    //   334	6	1	localClassCastException12	ClassCastException
    //   349	6	1	localClassCastException13	ClassCastException
    //   364	6	1	localClassCastException14	ClassCastException
    // Exception table:
    //   from	to	target	type
    //   0	5	169	java/lang/ClassCastException
    //   12	17	184	java/lang/ClassCastException
    //   24	29	199	java/lang/ClassCastException
    //   36	41	214	java/lang/ClassCastException
    //   48	53	229	java/lang/ClassCastException
    //   60	65	244	java/lang/ClassCastException
    //   72	77	259	java/lang/ClassCastException
    //   84	89	274	java/lang/ClassCastException
    //   96	101	289	java/lang/ClassCastException
    //   108	113	304	java/lang/ClassCastException
    //   120	125	319	java/lang/ClassCastException
    //   132	137	334	java/lang/ClassCastException
    //   144	149	349	java/lang/ClassCastException
    //   156	161	364	java/lang/ClassCastException
  }
  
  public static test.Mnrunner testRunnerSimple()
  {
    test.Mnrunner localMnrunner = $PcTestRunnerAlloc();
    testRunnerReset(localMnrunner);
    testRunnerOnGroupBegin$Ex(localMnrunner, test$Mnon$Mngroup$Mnbegin$Mnsimple);
    testRunnerOnGroupEnd$Ex(localMnrunner, test$Mnon$Mngroup$Mnend$Mnsimple);
    testRunnerOnFinal$Ex(localMnrunner, test$Mnon$Mnfinal$Mnsimple);
    testRunnerOnTestBegin$Ex(localMnrunner, test$Mnon$Mntest$Mnbegin$Mnsimple);
    testRunnerOnTestEnd$Ex(localMnrunner, test$Mnon$Mntest$Mnend$Mnsimple);
    testRunnerOnBadCount$Ex(localMnrunner, test$Mnon$Mnbad$Mncount$Mnsimple);
    testRunnerOnBadEndName$Ex(localMnrunner, test$Mnon$Mnbad$Mnend$Mnname$Mnsimple);
    return localMnrunner;
  }
  
  public static Object testRunnerSkipCount(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.skip$Mncount;
  }
  
  public static void testRunnerSkipCount$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.skip$Mncount = paramObject;
  }
  
  public static Object testRunnerTestName(Object paramObject)
  {
    SimpleSymbol localSimpleSymbol = Lit7;
    try
    {
      test.Mnrunner localMnrunner = (test.Mnrunner)paramObject;
      paramObject = lists.assq(localSimpleSymbol, testResultAlist(localMnrunner));
      if (paramObject != Boolean.FALSE) {
        return lists.cdr.apply1(paramObject);
      }
      return "";
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "test-result-alist", 0, paramObject);
    }
  }
  
  public static Object testRunnerXfailCount(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.xfail$Mncount;
  }
  
  public static void testRunnerXfailCount$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.xfail$Mncount = paramObject;
  }
  
  public static Object testRunnerXpassCount(test.Mnrunner paramMnrunner)
  {
    return paramMnrunner.xpass$Mncount;
  }
  
  public static void testRunnerXpassCount$Ex(test.Mnrunner paramMnrunner, Object paramObject)
  {
    paramMnrunner.xpass$Mncount = paramObject;
  }
  
  public Object apply0(ModuleMethod paramModuleMethod)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply0(paramModuleMethod);
    case 53: 
      return testRunnerNull();
    case 54: 
      return testRunnerSimple();
    case 55: 
      return testRunnerGet();
    case 56: 
      return testRunnerCreate();
    }
    return $PcTestReportResult();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 14: 
    case 16: 
    case 18: 
    case 20: 
    case 22: 
    case 24: 
    case 26: 
    case 28: 
    case 30: 
    case 32: 
    case 34: 
    case 36: 
    case 38: 
    case 40: 
    case 42: 
    case 44: 
    case 46: 
    case 50: 
    case 51: 
    case 52: 
    case 53: 
    case 54: 
    case 55: 
    case 56: 
    case 58: 
    case 59: 
    case 61: 
    case 62: 
    case 64: 
    case 67: 
    case 69: 
    case 70: 
    case 71: 
    case 72: 
    case 74: 
    case 84: 
    case 85: 
    case 86: 
    case 87: 
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 12: 
      if (isTestRunner(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerPassCount(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-pass-count", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerFailCount(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-fail-count", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerXpassCount(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-xpass-count", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerXfailCount(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-xfail-count", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerSkipCount(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-skip-count", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return $PcTestRunnerSkipList(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "%test-runner-skip-list", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return $PcTestRunnerFailList(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "%test-runner-fail-list", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerGroupStack(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-group-stack", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerOnTestBegin(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-test-begin", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerOnTestEnd(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-test-end", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerOnGroupBegin(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-group-begin", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerOnGroupEnd(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-group-end", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerOnFinal(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-final", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerOnBadCount(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-bad-count", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerOnBadEndName(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-bad-end-name", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testResultAlist(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-result-alist", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject;
      return testRunnerAuxValue(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-aux-value", 1, paramObject);
    }
    testRunnerReset(paramObject);
    return Values.empty;
    return testRunnerGroupPath(paramObject);
    return $PcTestNullCallback(paramObject);
    return $PcTestShouldExecute(paramObject);
    return testOnGroupEndSimple(paramObject);
    testOnFinalSimple(paramObject);
    return Values.empty;
    return testOnTestBeginSimple(paramObject);
    return testOnTestEndSimple(paramObject);
    testResultClear(paramObject);
    return Values.empty;
    if ($PcTestOnTestBegin(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    return testRunnerTestName(paramObject);
    return $PcTestApproximimate$Eq(paramObject);
    return $PcTestAsSpecifier(paramObject);
    return testMatchName(paramObject);
    return testReadEvalString(paramObject);
    return lambda16(paramObject);
    return lambda17(paramObject);
    return lambda18(paramObject);
    return lambda19(paramObject);
    return lambda20(paramObject);
    return lambda21(paramObject);
    return lambda22(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerPassCount$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-pass-count!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerFailCount$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-fail-count!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerXpassCount$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-xpass-count!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerXfailCount$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-xfail-count!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerSkipCount$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-skip-count!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      $PcTestRunnerSkipList$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "%test-runner-skip-list!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      $PcTestRunnerFailList$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "%test-runner-fail-list!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerGroupStack$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-group-stack!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerOnTestBegin$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-test-begin!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerOnTestEnd$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-test-end!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerOnGroupBegin$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-group-begin!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerOnGroupEnd$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-group-end!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerOnFinal$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-final!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerOnBadCount$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-bad-count!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerOnBadEndName$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-on-bad-end-name!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testResultAlist$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-result-alist!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (test.Mnrunner)paramObject1;
      testRunnerAuxValue$Ex(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "test-runner-aux-value!", 1, paramObject1);
    }
    $PcTestBegin(paramObject1, paramObject2);
    return Values.empty;
    return $PcTestEnd(paramObject1, paramObject2);
    testResultRemove(paramObject1, paramObject2);
    return Values.empty;
    return $PcTestOnTestEnd(paramObject1, paramObject2);
    return $PcTestMatchNth(paramObject1, paramObject2);
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
    case 50: 
      return lambda1(paramObject1, paramObject2, paramObject3);
    case 51: 
      return lambda2(paramObject1, paramObject2, paramObject3);
    case 52: 
      return lambda3(paramObject1, paramObject2, paramObject3);
    case 59: 
      return testOnGroupBeginSimple(paramObject1, paramObject2, paramObject3);
    case 61: 
      testOnBadCountSimple(paramObject1, paramObject2, paramObject3);
      return Values.empty;
    case 62: 
      return testOnBadEndNameSimple(paramObject1, paramObject2, paramObject3);
    }
    return testResultSet$Ex(paramObject1, paramObject2, paramObject3);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.applyN(paramModuleMethod, paramArrayOfObject);
    case 70: 
      return testResultKind$V(paramArrayOfObject);
    case 71: 
      return isTestPassed$V(paramArrayOfObject);
    case 84: 
      paramModuleMethod = paramArrayOfObject[0];
      int i = paramArrayOfObject.length - 1;
      Object[] arrayOfObject = new Object[i];
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          return testApply$V(paramModuleMethod, arrayOfObject);
        }
        arrayOfObject[i] = paramArrayOfObject[(i + 1)];
      }
    case 86: 
      return $PcTestMatchAll$V(paramArrayOfObject);
    }
    return $PcTestMatchAny$V(paramArrayOfObject);
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match0(paramModuleMethod, paramCallContext);
    case 72: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 56: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 55: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 54: 
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
    switch (paramModuleMethod.selector)
    {
    case 14: 
    case 16: 
    case 18: 
    case 20: 
    case 22: 
    case 24: 
    case 26: 
    case 28: 
    case 30: 
    case 32: 
    case 34: 
    case 36: 
    case 38: 
    case 40: 
    case 42: 
    case 44: 
    case 46: 
    case 50: 
    case 51: 
    case 52: 
    case 53: 
    case 54: 
    case 55: 
    case 56: 
    case 58: 
    case 59: 
    case 61: 
    case 62: 
    case 64: 
    case 67: 
    case 69: 
    case 70: 
    case 71: 
    case 72: 
    case 74: 
    case 84: 
    case 85: 
    case 86: 
    case 87: 
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 83: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 82: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 81: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 80: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 79: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 78: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 77: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 90: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 89: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 88: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 76: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 75: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 73: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 68: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 66: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 65: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 63: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 60: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 57: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 49: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 48: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 47: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 45: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 43: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 41: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 39: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 37: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 35: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 33: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 31: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 29: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 27: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 25: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 23: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 21: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 19: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 17: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 15: 
      if (!(paramObject instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 13: 
      if (!(paramObject instanceof test.Mnrunner)) {
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
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    case 85: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 74: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 69: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 64: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 58: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 46: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 44: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 42: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 40: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 38: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 36: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 34: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 32: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 30: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 28: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 26: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 24: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 22: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 20: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 18: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 16: 
      if (!(paramObject1 instanceof test.Mnrunner)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    }
    if (!(paramObject1 instanceof test.Mnrunner)) {
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
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
    case 67: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 62: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 61: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 59: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 52: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 51: 
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
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
    case 87: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 86: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 84: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 71: 
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
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
    test$Mnlog$Mnto$Mnfile = Boolean.TRUE;
    test$Mnrunner$Mncurrent = parameters.makeParameter(Boolean.FALSE);
    test$Mnrunner$Mnfactory = parameters.makeParameter(test$Mnrunner$Mnsimple);
  }
  
  public class frame
    extends ModuleBody
  {
    Object p;
    
    public Object lambda4loop(Object paramObject)
    {
      if (paramObject == this.p) {
        return lists.cdr.apply1(paramObject);
      }
      return lists.cons(lists.car.apply1(paramObject), lambda4loop(lists.cdr.apply1(paramObject)));
    }
  }
  
  public class frame0
    extends ModuleBody
  {
    Object error;
    final ModuleMethod lambda$Fn4;
    
    public frame0()
    {
      this$1 = new ModuleMethod(this, 1, null, 8194);
      this$1.setProperty("source-location", "testing.scm:640");
      this.lambda$Fn4 = this$1;
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 1)
      {
        if (lambda5(paramObject1, paramObject2)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    boolean lambda5(Object paramObject1, Object paramObject2)
    {
      Object localObject = Scheme.numGEq.apply2(paramObject1, AddOp.$Mn.apply2(paramObject2, this.error));
      try
      {
        boolean bool1 = ((Boolean)localObject).booleanValue();
        boolean bool2 = bool1;
        if (bool1) {
          bool2 = ((Boolean)Scheme.numLEq.apply2(paramObject1, AddOp.$Pl.apply2(paramObject2, this.error))).booleanValue();
        }
        return bool2;
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "x", -2, localObject);
      }
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
  }
  
  public class frame1
    extends ModuleBody
  {
    Object first;
    final ModuleMethod lambda$Fn10;
    final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 2, null, 0);
    final ModuleMethod lambda$Fn6 = new ModuleMethod(this, 3, null, 0);
    final ModuleMethod lambda$Fn7;
    final ModuleMethod lambda$Fn8;
    final ModuleMethod lambda$Fn9;
    Object r;
    LList rest;
    Object saved$Mnrunner;
    Object saved$Mnrunner$1;
    
    public frame1()
    {
      this$1 = new ModuleMethod(this, 4, null, 0);
      this$1.setProperty("source-location", "testing.scm:897");
      this.lambda$Fn7 = this$1;
      this.lambda$Fn8 = new ModuleMethod(this, 5, null, 0);
      this.lambda$Fn9 = new ModuleMethod(this, 6, null, 0);
      this$1 = new ModuleMethod(this, 7, null, 0);
      this$1.setProperty("source-location", "testing.scm:897");
      this.lambda$Fn10 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.apply0(paramModuleMethod);
      case 2: 
        return lambda6();
      case 3: 
        return lambda7();
      case 4: 
        return lambda8();
      case 5: 
        return lambda9();
      case 6: 
        return lambda10();
      }
      return lambda11();
    }
    
    Object lambda10()
    {
      return Scheme.apply.apply3(testing.test$Mnapply, this.first, this.rest);
    }
    
    Object lambda11()
    {
      return ((Procedure)testing.test$Mnrunner$Mncurrent).apply1(this.saved$Mnrunner);
    }
    
    Object lambda6()
    {
      return ((Procedure)testing.test$Mnrunner$Mncurrent).apply1(this.first);
    }
    
    Object lambda7()
    {
      return Scheme.apply.apply2(testing.test$Mnapply, this.rest);
    }
    
    Object lambda8()
    {
      return ((Procedure)testing.test$Mnrunner$Mncurrent).apply1(this.saved$Mnrunner$1);
    }
    
    Object lambda9()
    {
      return ((Procedure)testing.test$Mnrunner$Mncurrent).apply1(this.r);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.match0(paramModuleMethod, paramCallContext);
      case 7: 
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 6: 
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 5: 
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 4: 
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 3: 
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    }
  }
  
  public class frame2
    extends ModuleBody
  {
    Object count;
    Object i;
    final ModuleMethod lambda$Fn11;
    Object n;
    
    public frame2()
    {
      this$1 = new ModuleMethod(this, 8, null, 4097);
      this$1.setProperty("source-location", "testing.scm:903");
      this.lambda$Fn11 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 8)
      {
        if (lambda12(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda12(Object paramObject)
    {
      this.i = AddOp.$Pl.apply2(this.i, testing.Lit13);
      paramObject = Scheme.numGEq.apply2(this.i, this.n);
      try
      {
        boolean bool1 = ((Boolean)paramObject).booleanValue();
        boolean bool2 = bool1;
        if (bool1) {
          bool2 = ((Boolean)Scheme.numLss.apply2(this.i, AddOp.$Pl.apply2(this.n, this.count))).booleanValue();
        }
        return bool2;
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "x", -2, paramObject);
      }
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 8)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame3
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn12;
    LList pred$Mnlist;
    
    public frame3()
    {
      this$1 = new ModuleMethod(this, 9, null, 4097);
      this$1.setProperty("source-location", "testing.scm:915");
      this.lambda$Fn12 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 9) {
        return lambda13(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda13(Object paramObject)
    {
      Boolean localBoolean = Boolean.TRUE;
      for (Object localObject = this.pred$Mnlist;; localObject = lists.cdr.apply1(localObject))
      {
        if (lists.isNull(localObject)) {
          return localBoolean;
        }
        if (Scheme.applyToArgs.apply2(lists.car.apply1(localObject), paramObject) == Boolean.FALSE) {
          localBoolean = Boolean.FALSE;
        }
      }
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 9)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame4
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn13;
    LList pred$Mnlist;
    
    public frame4()
    {
      this$1 = new ModuleMethod(this, 10, null, 4097);
      this$1.setProperty("source-location", "testing.scm:931");
      this.lambda$Fn13 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 10) {
        return lambda14(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda14(Object paramObject)
    {
      Boolean localBoolean = Boolean.FALSE;
      for (Object localObject = this.pred$Mnlist;; localObject = lists.cdr.apply1(localObject))
      {
        if (lists.isNull(localObject)) {
          return localBoolean;
        }
        if (Scheme.applyToArgs.apply2(lists.car.apply1(localObject), paramObject) != Boolean.FALSE) {
          localBoolean = Boolean.TRUE;
        }
      }
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 10)
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
    final ModuleMethod lambda$Fn14;
    Object name;
    
    public frame5()
    {
      this$1 = new ModuleMethod(this, 11, null, 4097);
      this$1.setProperty("source-location", "testing.scm:971");
      this.lambda$Fn14 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 11)
      {
        if (lambda15(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda15(Object paramObject)
    {
      return IsEqual.apply(this.name, testing.testRunnerTestName(paramObject));
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 11)
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


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\testing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */