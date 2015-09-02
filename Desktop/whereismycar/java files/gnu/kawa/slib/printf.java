package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.CharSeq;
import gnu.lists.FString;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Complex;
import gnu.math.IntNum;
import gnu.math.RealNum;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;
import kawa.standard.append;

public class printf
  extends ModuleBody
{
  public static final printf $instance;
  static final IntNum Lit0;
  static final IntNum Lit1;
  static final PairWithPosition Lit10;
  static final Char Lit11;
  static final Char Lit12;
  static final Char Lit13;
  static final IntNum Lit14;
  static final IntNum Lit15;
  static final IntNum Lit16;
  static final IntNum Lit17;
  static final Char Lit18;
  static final Char Lit19;
  static final PairWithPosition Lit2;
  static final Char Lit20;
  static final Char Lit21;
  static final Char Lit22;
  static final Char Lit23;
  static final Char Lit24;
  static final Char Lit25;
  static final Char Lit26;
  static final Char Lit27;
  static final Char Lit28;
  static final Char Lit29;
  static final Char Lit3;
  static final Char Lit30;
  static final Char Lit31;
  static final Char Lit32;
  static final PairWithPosition Lit33;
  static final SimpleSymbol Lit34;
  static final Char Lit35;
  static final Char Lit36;
  static final Char Lit37;
  static final Char Lit38;
  static final Char Lit39;
  static final Char Lit4;
  static final Char Lit40;
  static final Char Lit41;
  static final Char Lit42;
  static final Char Lit43;
  static final Char Lit44;
  static final IntNum Lit45;
  static final Char Lit46;
  static final Char Lit47;
  static final IntNum Lit48;
  static final Char Lit49;
  static final Char Lit5;
  static final IntNum Lit50;
  static final Char Lit51;
  static final Char Lit52;
  static final Char Lit53;
  static final Char Lit54;
  static final Char Lit55;
  static final Char Lit56;
  static final Char Lit57;
  static final Char Lit58;
  static final IntNum Lit59;
  static final Char Lit6;
  static final IntNum Lit60;
  static final IntNum Lit61;
  static final FVector Lit62;
  static final PairWithPosition Lit63;
  static final SimpleSymbol Lit64;
  static final Char Lit65;
  static final Char Lit66;
  static final SimpleSymbol Lit67;
  static final SimpleSymbol Lit68;
  static final SimpleSymbol Lit69;
  static final IntNum Lit7;
  static final SimpleSymbol Lit70;
  static final SimpleSymbol Lit71;
  static final SimpleSymbol Lit72 = (SimpleSymbol)new SimpleSymbol("fprintf").readResolve();
  static final Char Lit8;
  static final Char Lit9;
  public static final ModuleMethod fprintf;
  public static final ModuleMethod printf;
  public static final ModuleMethod sprintf;
  public static final boolean stdio$Clhex$Mnupper$Mncase$Qu = false;
  public static final ModuleMethod stdio$Cliprintf;
  public static final ModuleMethod stdio$Clparse$Mnfloat;
  public static final ModuleMethod stdio$Clround$Mnstring;
  
  static
  {
    Lit71 = (SimpleSymbol)new SimpleSymbol("stdio:iprintf").readResolve();
    Lit70 = (SimpleSymbol)new SimpleSymbol("stdio:round-string").readResolve();
    Lit69 = (SimpleSymbol)new SimpleSymbol("stdio:parse-float").readResolve();
    Lit68 = (SimpleSymbol)new SimpleSymbol("sprintf").readResolve();
    Lit67 = (SimpleSymbol)new SimpleSymbol("pad").readResolve();
    Lit66 = Char.make(42);
    Lit65 = Char.make(63);
    Lit64 = (SimpleSymbol)new SimpleSymbol("format-real").readResolve();
    Lit63 = PairWithPosition.make("i", LList.Empty, "printf.scm", 1634315);
    Lit62 = FVector.make(new Object[] { "y", "z", "a", "f", "p", "n", "u", "m", "", "k", "M", "G", "T", "P", "E", "Z", "Y" });
    Lit61 = IntNum.make(3);
    Lit60 = IntNum.make(-10);
    Lit59 = IntNum.make(6);
    Lit58 = Char.make(75);
    Lit57 = Char.make(107);
    Lit56 = Char.make(71);
    Lit55 = Char.make(103);
    Lit54 = Char.make(69);
    Lit53 = Char.make(66);
    Lit52 = Char.make(98);
    Lit51 = Char.make(88);
    Lit50 = IntNum.make(16);
    Lit49 = Char.make(120);
    Lit48 = IntNum.make(8);
    Lit47 = Char.make(79);
    Lit46 = Char.make(111);
    Lit45 = IntNum.make(10);
    Lit44 = Char.make(85);
    Lit43 = Char.make(117);
    Lit42 = Char.make(73);
    Lit41 = Char.make(68);
    Lit40 = Char.make(65);
    Lit39 = Char.make(97);
    Lit38 = Char.make(83);
    Lit37 = Char.make(115);
    Lit36 = Char.make(67);
    Lit35 = Char.make(99);
    Lit34 = (SimpleSymbol)new SimpleSymbol("printf").readResolve();
    Object localObject = Lit35;
    Char localChar1 = Lit37;
    Char localChar2 = Lit39;
    Char localChar3 = Char.make(100);
    Lit12 = localChar3;
    Char localChar4 = Char.make(105);
    Lit3 = localChar4;
    Char localChar5 = Lit43;
    Char localChar6 = Lit46;
    Char localChar7 = Lit49;
    Char localChar8 = Lit52;
    Char localChar9 = Char.make(102);
    Lit25 = localChar9;
    Char localChar10 = Char.make(101);
    Lit13 = localChar10;
    Lit33 = PairWithPosition.make(localObject, PairWithPosition.make(localChar1, PairWithPosition.make(localChar2, PairWithPosition.make(localChar3, PairWithPosition.make(localChar4, PairWithPosition.make(localChar5, PairWithPosition.make(localChar6, PairWithPosition.make(localChar7, PairWithPosition.make(localChar8, PairWithPosition.make(localChar9, PairWithPosition.make(localChar10, PairWithPosition.make(Lit55, PairWithPosition.make(Lit57, LList.Empty, "printf.scm", 1781780), "printf.scm", 1781776), "printf.scm", 1781772), "printf.scm", 1781768), "printf.scm", 1777704), "printf.scm", 1777700), "printf.scm", 1777696), "printf.scm", 1777692), "printf.scm", 1777688), "printf.scm", 1777684), "printf.scm", 1777680), "printf.scm", 1777676), "printf.scm", 1777671);
    Lit32 = Char.make(104);
    Lit31 = Char.make(76);
    Lit30 = Char.make(108);
    Lit29 = Char.make(32);
    Lit28 = Char.make(37);
    Lit27 = Char.make(12);
    Lit26 = Char.make(70);
    Lit24 = Char.make(9);
    Lit23 = Char.make(84);
    Lit22 = Char.make(116);
    Lit21 = Char.make(10);
    Lit20 = Char.make(78);
    Lit19 = Char.make(110);
    Lit18 = Char.make(92);
    Lit17 = IntNum.make(-1);
    Lit16 = IntNum.make(9);
    Lit15 = IntNum.make(5);
    Lit14 = IntNum.make(2);
    Lit11 = Char.make(46);
    Lit10 = PairWithPosition.make(Lit13, PairWithPosition.make(Lit37, PairWithPosition.make(Lit25, PairWithPosition.make(Lit12, PairWithPosition.make(Lit30, PairWithPosition.make(Lit54, PairWithPosition.make(Lit38, PairWithPosition.make(Lit26, PairWithPosition.make(Lit41, PairWithPosition.make(Lit31, LList.Empty, "printf.scm", 266284), "printf.scm", 266280), "printf.scm", 266276), "printf.scm", 266272), "printf.scm", 266268), "printf.scm", 266264), "printf.scm", 266260), "printf.scm", 266256), "printf.scm", 266252), "printf.scm", 266247);
    Lit9 = Char.make(48);
    Lit8 = Char.make(35);
    Lit7 = IntNum.make(1);
    Lit6 = Char.make(43);
    Lit5 = Char.make(45);
    Lit4 = Char.make(64);
    Lit2 = PairWithPosition.make(Lit6, PairWithPosition.make(Lit5, LList.Empty, "printf.scm", 446503), "printf.scm", 446498);
    Lit1 = IntNum.make(0);
    Lit0 = IntNum.make(-15);
    $instance = new printf();
    localObject = $instance;
    stdio$Clparse$Mnfloat = new ModuleMethod((ModuleBody)localObject, 22, Lit69, 8194);
    stdio$Clround$Mnstring = new ModuleMethod((ModuleBody)localObject, 23, Lit70, 12291);
    stdio$Cliprintf = new ModuleMethod((ModuleBody)localObject, 24, Lit71, 61442);
    fprintf = new ModuleMethod((ModuleBody)localObject, 25, Lit72, 61442);
    printf = new ModuleMethod((ModuleBody)localObject, 26, Lit34, 61441);
    sprintf = new ModuleMethod((ModuleBody)localObject, 27, Lit68, 61442);
    $instance.run();
  }
  
  public printf()
  {
    ModuleInfo.register(this);
  }
  
  public static Object fprintf$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
  {
    frame12 localframe12 = new frame12();
    localframe12.port = paramObject1;
    paramObject1 = LList.makeList(paramArrayOfObject, 0);
    localframe12.cnt = Lit1;
    Scheme.apply.apply4(stdio$Cliprintf, localframe12.lambda$Fn18, paramObject2, paramObject1);
    return localframe12.cnt;
  }
  
  public static Object printf$V(Object paramObject, Object[] paramArrayOfObject)
  {
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    return Scheme.apply.apply4(fprintf, ports.current$Mnoutput$Mnport.apply0(), paramObject, paramArrayOfObject);
  }
  
  /* Error */
  public static Object sprintf$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
  {
    // Byte code:
    //   0: new 24	gnu/kawa/slib/printf$frame13
    //   3: dup
    //   4: invokespecial 478	gnu/kawa/slib/printf$frame13:<init>	()V
    //   7: astore_3
    //   8: aload_3
    //   9: aload_0
    //   10: putfield 481	gnu/kawa/slib/printf$frame13:str	Ljava/lang/Object;
    //   13: aload_2
    //   14: iconst_0
    //   15: invokestatic 445	gnu/lists/LList:makeList	([Ljava/lang/Object;I)Lgnu/lists/LList;
    //   18: astore_2
    //   19: aload_3
    //   20: getstatic 400	gnu/kawa/slib/printf:Lit1	Lgnu/math/IntNum;
    //   23: putfield 482	gnu/kawa/slib/printf$frame13:cnt	Ljava/lang/Object;
    //   26: aload_3
    //   27: getfield 481	gnu/kawa/slib/printf$frame13:str	Ljava/lang/Object;
    //   30: invokestatic 488	kawa/lib/strings:isString	(Ljava/lang/Object;)Z
    //   33: ifeq +67 -> 100
    //   36: aload_3
    //   37: getfield 481	gnu/kawa/slib/printf$frame13:str	Ljava/lang/Object;
    //   40: astore_0
    //   41: aload_3
    //   42: aload_0
    //   43: putfield 491	gnu/kawa/slib/printf$frame13:s	Ljava/lang/Object;
    //   46: aload_3
    //   47: getfield 491	gnu/kawa/slib/printf$frame13:s	Ljava/lang/Object;
    //   50: astore_0
    //   51: aload_0
    //   52: checkcast 493	java/lang/CharSequence
    //   55: astore 4
    //   57: aload_3
    //   58: aload 4
    //   60: invokestatic 497	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
    //   63: invokestatic 503	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   66: putfield 506	gnu/kawa/slib/printf$frame13:end	Ljava/lang/Object;
    //   69: getstatic 454	kawa/standard/Scheme:apply	Lgnu/kawa/functions/Apply;
    //   72: getstatic 417	gnu/kawa/slib/printf:stdio$Cliprintf	Lgnu/expr/ModuleMethod;
    //   75: aload_3
    //   76: getfield 509	gnu/kawa/slib/printf$frame13:lambda$Fn19	Lgnu/expr/ModuleMethod;
    //   79: aload_1
    //   80: aload_2
    //   81: invokevirtual 463	gnu/mapping/Procedure:apply4	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   84: pop
    //   85: aload_3
    //   86: getfield 481	gnu/kawa/slib/printf$frame13:str	Ljava/lang/Object;
    //   89: invokestatic 488	kawa/lib/strings:isString	(Ljava/lang/Object;)Z
    //   92: ifeq +87 -> 179
    //   95: aload_3
    //   96: getfield 482	gnu/kawa/slib/printf$frame13:cnt	Ljava/lang/Object;
    //   99: areturn
    //   100: aload_3
    //   101: getfield 481	gnu/kawa/slib/printf$frame13:str	Ljava/lang/Object;
    //   104: invokestatic 514	kawa/lib/numbers:isNumber	(Ljava/lang/Object;)Z
    //   107: ifeq +26 -> 133
    //   110: aload_3
    //   111: getfield 481	gnu/kawa/slib/printf$frame13:str	Ljava/lang/Object;
    //   114: astore_0
    //   115: aload_0
    //   116: checkcast 516	java/lang/Number
    //   119: invokevirtual 520	java/lang/Number:intValue	()I
    //   122: istore 5
    //   124: iload 5
    //   126: invokestatic 524	kawa/lib/strings:makeString	(I)Ljava/lang/CharSequence;
    //   129: astore_0
    //   130: goto -89 -> 41
    //   133: aload_3
    //   134: getfield 481	gnu/kawa/slib/printf$frame13:str	Ljava/lang/Object;
    //   137: getstatic 530	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   140: if_acmpne +12 -> 152
    //   143: bipush 100
    //   145: invokestatic 524	kawa/lib/strings:makeString	(I)Ljava/lang/CharSequence;
    //   148: astore_0
    //   149: goto -108 -> 41
    //   152: getstatic 169	gnu/kawa/slib/printf:Lit68	Lgnu/mapping/SimpleSymbol;
    //   155: iconst_2
    //   156: anewarray 207	java/lang/Object
    //   159: dup
    //   160: iconst_0
    //   161: ldc_w 532
    //   164: aastore
    //   165: dup
    //   166: iconst_1
    //   167: aload_3
    //   168: getfield 481	gnu/kawa/slib/printf$frame13:str	Ljava/lang/Object;
    //   171: aastore
    //   172: invokestatic 537	kawa/lib/misc:error$V	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   175: astore_0
    //   176: goto -135 -> 41
    //   179: getstatic 541	kawa/standard/Scheme:isEqv	Lgnu/kawa/functions/IsEqv;
    //   182: aload_3
    //   183: getfield 506	gnu/kawa/slib/printf$frame13:end	Ljava/lang/Object;
    //   186: aload_3
    //   187: getfield 482	gnu/kawa/slib/printf$frame13:cnt	Ljava/lang/Object;
    //   190: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   193: getstatic 530	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   196: if_acmpeq +8 -> 204
    //   199: aload_3
    //   200: getfield 491	gnu/kawa/slib/printf$frame13:s	Ljava/lang/Object;
    //   203: areturn
    //   204: aload_3
    //   205: getfield 491	gnu/kawa/slib/printf$frame13:s	Ljava/lang/Object;
    //   208: astore_0
    //   209: aload_0
    //   210: checkcast 493	java/lang/CharSequence
    //   213: astore_1
    //   214: aload_3
    //   215: getfield 482	gnu/kawa/slib/printf$frame13:cnt	Ljava/lang/Object;
    //   218: astore_0
    //   219: aload_0
    //   220: checkcast 516	java/lang/Number
    //   223: invokevirtual 520	java/lang/Number:intValue	()I
    //   226: istore 5
    //   228: aload_1
    //   229: iconst_0
    //   230: iload 5
    //   232: invokestatic 549	kawa/lib/strings:substring	(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
    //   235: areturn
    //   236: astore_1
    //   237: new 551	gnu/mapping/WrongType
    //   240: dup
    //   241: aload_1
    //   242: ldc_w 553
    //   245: iconst_1
    //   246: aload_0
    //   247: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   250: athrow
    //   251: astore_1
    //   252: new 551	gnu/mapping/WrongType
    //   255: dup
    //   256: aload_1
    //   257: ldc_w 558
    //   260: iconst_1
    //   261: aload_0
    //   262: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   265: athrow
    //   266: astore_1
    //   267: new 551	gnu/mapping/WrongType
    //   270: dup
    //   271: aload_1
    //   272: ldc_w 559
    //   275: iconst_1
    //   276: aload_0
    //   277: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   280: athrow
    //   281: astore_1
    //   282: new 551	gnu/mapping/WrongType
    //   285: dup
    //   286: aload_1
    //   287: ldc_w 559
    //   290: iconst_3
    //   291: aload_0
    //   292: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   295: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	296	0	paramObject1	Object
    //   0	296	1	paramObject2	Object
    //   0	296	2	paramArrayOfObject	Object[]
    //   7	208	3	localframe13	frame13
    //   55	4	4	localCharSequence	CharSequence
    //   122	109	5	i	int
    // Exception table:
    //   from	to	target	type
    //   115	124	236	java/lang/ClassCastException
    //   51	57	251	java/lang/ClassCastException
    //   209	214	266	java/lang/ClassCastException
    //   219	228	281	java/lang/ClassCastException
  }
  
  public static Object stdio$ClIprintf$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
  {
    frame9 localframe9 = new frame9();
    localframe9.out = paramObject1;
    localframe9.format$Mnstring = paramObject2;
    localframe9.args = LList.makeList(paramArrayOfObject, 0);
    if (!IsEqual.apply("", localframe9.format$Mnstring))
    {
      paramObject1 = Lit17;
      paramObject2 = localframe9.format$Mnstring;
      for (;;)
      {
        try
        {
          paramArrayOfObject = (CharSequence)paramObject2;
          i = strings.stringLength(paramArrayOfObject);
          paramObject2 = localframe9.format$Mnstring;
        }
        catch (ClassCastException paramObject1)
        {
          int i;
          boolean bool;
          Object localObject2;
          Object localObject3;
          Object localObject4;
          throw new WrongType((ClassCastException)paramObject1, "string-length", 1, paramObject2);
        }
        try
        {
          paramArrayOfObject = (CharSequence)paramObject2;
          localframe9.fc = Char.make(strings.stringRef(paramArrayOfObject, 0));
          localframe9.fl = i;
          localframe9.pos = paramObject1;
          paramObject1 = localframe9.args;
          paramArrayOfObject = new frame10();
          paramArrayOfObject.staticLink = localframe9;
          paramArrayOfObject.args = paramObject1;
          localframe9.pos = AddOp.$Pl.apply2(Lit7, localframe9.pos);
          if (Scheme.numGEq.apply2(localframe9.pos, Integer.valueOf(localframe9.fl)) != Boolean.FALSE)
          {
            localframe9.fc = Boolean.FALSE;
            bool = localframe9.lambda19isEndOfFormat();
            if (!bool) {
              continue;
            }
            if (bool)
            {
              paramObject1 = Boolean.TRUE;
              return paramObject1;
            }
          }
          else
          {
            paramObject1 = localframe9.format$Mnstring;
          }
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, paramObject2);
        }
        try
        {
          paramObject2 = (CharSequence)paramObject1;
          paramObject1 = localframe9.pos;
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
        }
        try
        {
          i = ((Number)paramObject1).intValue();
          localframe9.fc = Char.make(strings.stringRef((CharSequence)paramObject2, i));
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "string-ref", 2, paramObject1);
        }
        paramObject1 = Boolean.FALSE;
        continue;
        if (Scheme.isEqv.apply2(Lit18, localframe9.fc) != Boolean.FALSE)
        {
          localframe9.lambda18mustAdvance();
          paramObject1 = localframe9.fc;
          paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit19);
          if (paramObject2 != Boolean.FALSE)
          {
            if (paramObject2 != Boolean.FALSE)
            {
              paramObject1 = Scheme.applyToArgs.apply2(localframe9.out, Lit21);
              if (paramObject1 == Boolean.FALSE) {
                continue;
              }
              paramObject1 = paramArrayOfObject.args;
            }
          }
          else {
            if (Scheme.isEqv.apply2(paramObject1, Lit20) != Boolean.FALSE) {
              continue;
            }
          }
          paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit22);
          if (paramObject2 != Boolean.FALSE)
          {
            if (paramObject2 != Boolean.FALSE) {
              paramObject1 = Scheme.applyToArgs.apply2(localframe9.out, Lit24);
            }
          }
          else {
            if (Scheme.isEqv.apply2(paramObject1, Lit23) != Boolean.FALSE) {
              continue;
            }
          }
          paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit25);
          if (paramObject2 != Boolean.FALSE)
          {
            if (paramObject2 != Boolean.FALSE) {
              paramObject1 = Scheme.applyToArgs.apply2(localframe9.out, Lit27);
            }
          }
          else {
            if (Scheme.isEqv.apply2(paramObject1, Lit26) != Boolean.FALSE) {
              continue;
            }
          }
          if (Scheme.isEqv.apply2(paramObject1, Lit21) != Boolean.FALSE)
          {
            paramObject1 = Boolean.TRUE;
          }
          else
          {
            paramObject1 = Scheme.applyToArgs.apply2(localframe9.out, localframe9.fc);
            continue;
            return paramObject1;
          }
        }
        else if (Scheme.isEqv.apply2(Lit28, localframe9.fc) != Boolean.FALSE)
        {
          localframe9.lambda18mustAdvance();
          paramObject1 = Boolean.FALSE;
          paramObject2 = Boolean.FALSE;
          localObject1 = Boolean.FALSE;
          localObject2 = Boolean.FALSE;
          localObject3 = Boolean.FALSE;
          localObject4 = Lit1;
          paramArrayOfObject.precision = Lit17;
          paramArrayOfObject.width = localObject4;
          paramArrayOfObject.leading$Mn0s = localObject3;
          paramArrayOfObject.alternate$Mnform = localObject2;
          paramArrayOfObject.blank = localObject1;
          paramArrayOfObject.signed = paramObject2;
          paramArrayOfObject.left$Mnadjust = paramObject1;
          paramArrayOfObject.pad = paramArrayOfObject.pad;
          paramObject1 = localframe9.fc;
          if (Scheme.isEqv.apply2(paramObject1, Lit5) != Boolean.FALSE)
          {
            paramArrayOfObject.left$Mnadjust = Boolean.TRUE;
            localframe9.lambda18mustAdvance();
            continue;
          }
          if (Scheme.isEqv.apply2(paramObject1, Lit6) != Boolean.FALSE)
          {
            paramArrayOfObject.signed = Boolean.TRUE;
            continue;
          }
          if (Scheme.isEqv.apply2(paramObject1, Lit29) != Boolean.FALSE)
          {
            paramArrayOfObject.blank = Boolean.TRUE;
            continue;
          }
          if (Scheme.isEqv.apply2(paramObject1, Lit8) != Boolean.FALSE)
          {
            paramArrayOfObject.alternate$Mnform = Boolean.TRUE;
            continue;
          }
          if (Scheme.isEqv.apply2(paramObject1, Lit9) != Boolean.FALSE)
          {
            paramArrayOfObject.leading$Mn0s = Boolean.TRUE;
            continue;
          }
          if (paramArrayOfObject.left$Mnadjust != Boolean.FALSE) {
            paramArrayOfObject.leading$Mn0s = Boolean.FALSE;
          }
          if (paramArrayOfObject.signed != Boolean.FALSE) {
            paramArrayOfObject.blank = Boolean.FALSE;
          }
          paramArrayOfObject.width = paramArrayOfObject.lambda22readFormatNumber();
          paramObject1 = paramArrayOfObject.width;
        }
        try
        {
          paramObject2 = LangObjType.coerceRealNum(paramObject1);
          if (numbers.isNegative((RealNum)paramObject2))
          {
            paramArrayOfObject.left$Mnadjust = Boolean.TRUE;
            paramArrayOfObject.width = AddOp.$Mn.apply1(paramArrayOfObject.width);
          }
          if (Scheme.isEqv.apply2(Lit11, localframe9.fc) != Boolean.FALSE)
          {
            localframe9.lambda18mustAdvance();
            paramArrayOfObject.precision = paramArrayOfObject.lambda22readFormatNumber();
          }
          paramObject1 = localframe9.fc;
          paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit30);
          if (paramObject2 != Boolean.FALSE)
          {
            if (paramObject2 != Boolean.FALSE) {
              localframe9.lambda18mustAdvance();
            }
            if (lists.isNull(paramArrayOfObject.args)) {
              paramObject1 = localframe9.fc;
            }
          }
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "negative?", 1, paramObject1);
        }
        try
        {
          paramObject2 = (Char)paramObject1;
          if (lists.memv(unicode.charDowncase((Char)paramObject2), Lit33) != Boolean.FALSE) {
            misc.error$V(Lit34, new Object[] { "wrong number of arguments", Integer.valueOf(lists.length(localframe9.args)), localframe9.format$Mnstring });
          }
          paramObject1 = localframe9.fc;
          paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit35);
          if (paramObject2 != Boolean.FALSE)
          {
            if (paramObject2 != Boolean.FALSE)
            {
              paramObject2 = Scheme.applyToArgs;
              localObject1 = localframe9.out;
              paramObject1 = lists.car.apply1(paramArrayOfObject.args);
              if (!(paramObject1 instanceof Object[])) {
                continue;
              }
              paramObject1 = (Object[])paramObject1;
              paramObject2 = ((Procedure)paramObject2).apply2(localObject1, strings.$make$string$((Object[])paramObject1));
              paramObject1 = paramObject2;
              if (paramObject2 == Boolean.FALSE) {
                continue;
              }
              paramObject1 = lists.cdr.apply1(paramArrayOfObject.args);
              continue;
              paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit31);
              if (paramObject2 != Boolean.FALSE)
              {
                if (paramObject2 == Boolean.FALSE) {
                  continue;
                }
                continue;
              }
              if (Scheme.isEqv.apply2(paramObject1, Lit32) == Boolean.FALSE) {
                continue;
              }
              continue;
            }
          }
          else {
            if (Scheme.isEqv.apply2(paramObject1, Lit36) != Boolean.FALSE) {
              continue;
            }
          }
          paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit37);
          if (paramObject2 != Boolean.FALSE)
          {
            if (paramObject2 == Boolean.FALSE) {
              continue;
            }
            if (!misc.isSymbol(lists.car.apply1(paramArrayOfObject.args))) {
              continue;
            }
            paramObject1 = lists.car.apply1(paramArrayOfObject.args);
          }
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "char-downcase", 1, paramObject1);
        }
        try
        {
          paramObject2 = (Symbol)paramObject1;
          paramObject1 = misc.symbol$To$String((Symbol)paramObject2);
          paramObject2 = paramArrayOfObject.precision;
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "symbol->string", 1, paramObject1);
        }
        try
        {
          localObject1 = LangObjType.coerceRealNum(paramObject2);
          bool = numbers.isNegative((RealNum)localObject1);
          if (bool)
          {
            paramObject2 = paramObject1;
            if (bool) {}
          }
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "negative?", 1, paramObject2);
        }
        try
        {
          paramObject2 = (CharSequence)paramObject1;
          paramObject1 = paramArrayOfObject.precision;
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "substring", 1, paramObject1);
        }
        try
        {
          i = ((Number)paramObject1).intValue();
          paramObject2 = strings.substring((CharSequence)paramObject2, 0, i);
          paramObject1 = Scheme.numLEq;
          localObject1 = paramArrayOfObject.width;
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "substring", 3, paramObject1);
        }
        try
        {
          localObject2 = (CharSequence)paramObject2;
          if (((Procedure)paramObject1).apply2(localObject1, Integer.valueOf(strings.stringLength((CharSequence)localObject2))) != Boolean.FALSE)
          {
            paramObject2 = localframe9.lambda21out$St(paramObject2);
            paramObject1 = paramObject2;
            if (paramObject2 == Boolean.FALSE) {
              continue;
            }
            paramObject1 = lists.cdr.apply1(paramArrayOfObject.args);
            continue;
            paramObject1 = new Object[] { paramObject1 };
            continue;
            if (Scheme.isEqv.apply2(paramObject1, Lit38) != Boolean.FALSE) {
              continue;
            }
            paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit39);
            if (paramObject2 == Boolean.FALSE) {
              continue;
            }
            if (paramObject2 == Boolean.FALSE) {
              continue;
            }
            paramArrayOfObject.pr = paramArrayOfObject.precision;
            paramArrayOfObject.os = "";
            localObject1 = lists.car.apply1(paramArrayOfObject.args);
            if (paramArrayOfObject.alternate$Mnform == Boolean.FALSE) {
              continue;
            }
            paramObject2 = Boolean.FALSE;
            localObject2 = Boolean.FALSE;
            paramObject1 = paramArrayOfObject.left$Mnadjust;
            if (paramObject1 == Boolean.FALSE) {
              continue;
            }
            paramObject1 = paramArrayOfObject.pr;
          }
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-length", 1, paramObject2);
        }
        try
        {
          localObject3 = LangObjType.coerceRealNum(paramObject1);
          if (numbers.isNegative((RealNum)localObject3))
          {
            paramArrayOfObject.pr = Lit1;
            paramObject1 = paramArrayOfObject.lambda$Fn13;
            genwrite.genericWrite(localObject1, paramObject2, localObject2, paramObject1);
            paramObject1 = paramArrayOfObject.left$Mnadjust;
            if (paramObject1 == Boolean.FALSE) {
              continue;
            }
            paramObject1 = paramArrayOfObject.precision;
          }
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "negative?", 1, paramObject1);
        }
        try
        {
          paramObject2 = LangObjType.coerceRealNum(paramObject1);
          if (numbers.isNegative((RealNum)paramObject2)) {
            if (Scheme.numGrt.apply2(paramArrayOfObject.width, paramArrayOfObject.pr) != Boolean.FALSE)
            {
              paramObject2 = Scheme.applyToArgs;
              localObject1 = localframe9.out;
              paramObject1 = AddOp.$Mn.apply2(paramArrayOfObject.width, paramArrayOfObject.pr);
            }
          }
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "negative?", 1, paramObject1);
        }
        try
        {
          i = ((Number)paramObject1).intValue();
          ((Procedure)paramObject2).apply2(localObject1, strings.makeString(i, Lit29));
          paramObject1 = lists.cdr.apply1(paramArrayOfObject.args);
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "make-string", 1, paramObject1);
        }
        if (lists.car.apply1(paramArrayOfObject.args) == Boolean.FALSE)
        {
          paramObject1 = "(NULL)";
        }
        else
        {
          paramObject1 = lists.car.apply1(paramArrayOfObject.args);
          continue;
          localObject1 = Scheme.numGEq;
          localObject2 = paramArrayOfObject.precision;
        }
        try
        {
          localObject3 = (CharSequence)paramObject1;
          paramObject2 = paramObject1;
          if (((Procedure)localObject1).apply2(localObject2, Integer.valueOf(strings.stringLength((CharSequence)localObject3))) != Boolean.FALSE) {
            continue;
          }
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "string-length", 1, paramObject1);
        }
        if (paramArrayOfObject.left$Mnadjust != Boolean.FALSE)
        {
          paramObject1 = AddOp.$Mn;
          localObject1 = paramArrayOfObject.width;
        }
        try
        {
          localObject2 = (CharSequence)paramObject2;
          paramObject1 = ((Procedure)paramObject1).apply2(localObject1, Integer.valueOf(strings.stringLength((CharSequence)localObject2)));
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-length", 1, paramObject2);
        }
        try
        {
          i = ((Number)paramObject1).intValue();
          paramObject2 = LList.list2(paramObject2, strings.makeString(i, Lit29));
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "make-string", 1, paramObject1);
        }
        paramObject1 = AddOp.$Mn;
        localObject1 = paramArrayOfObject.width;
        try
        {
          localObject2 = (CharSequence)paramObject2;
          paramObject1 = ((Procedure)paramObject1).apply2(localObject1, Integer.valueOf(strings.stringLength((CharSequence)localObject2)));
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-length", 1, paramObject2);
        }
        try
        {
          i = ((Number)paramObject1).intValue();
          if (paramArrayOfObject.leading$Mn0s != Boolean.FALSE)
          {
            paramObject1 = Lit9;
            paramObject2 = LList.list2(strings.makeString(i, paramObject1), paramObject2);
            continue;
          }
          paramObject1 = Lit29;
          continue;
          if (Scheme.isEqv.apply2(paramObject1, Lit40) != Boolean.FALSE) {
            continue;
          }
          paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit12);
          if (paramObject2 != Boolean.FALSE)
          {
            if (paramObject2 == Boolean.FALSE) {
              continue;
            }
            paramObject2 = localframe9.lambda21out$St(paramArrayOfObject.lambda24integerConvert(lists.car.apply1(paramArrayOfObject.args), Lit45, Boolean.FALSE));
            paramObject1 = paramObject2;
            if (paramObject2 == Boolean.FALSE) {
              continue;
            }
            paramObject1 = lists.cdr.apply1(paramArrayOfObject.args);
            continue;
            paramObject2 = Boolean.TRUE;
            continue;
            if (paramObject1 != Boolean.FALSE) {
              continue;
            }
            if (paramArrayOfObject.left$Mnadjust != Boolean.FALSE)
            {
              paramObject1 = paramArrayOfObject.lambda$Fn14;
              continue;
            }
            paramObject1 = paramArrayOfObject.pr;
          }
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "make-string", 1, paramObject1);
        }
        try
        {
          localObject3 = LangObjType.coerceRealNum(paramObject1);
          if (numbers.isNegative((RealNum)localObject3))
          {
            paramArrayOfObject.pr = paramArrayOfObject.width;
            paramObject1 = paramArrayOfObject.lambda$Fn15;
            continue;
          }
          paramObject1 = paramArrayOfObject.lambda$Fn16;
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "negative?", 1, paramObject1);
        }
        if (paramObject1 == Boolean.FALSE) {
          if (paramArrayOfObject.left$Mnadjust != Boolean.FALSE)
          {
            if (Scheme.numGrt.apply2(paramArrayOfObject.width, AddOp.$Mn.apply2(paramArrayOfObject.precision, paramArrayOfObject.pr)) == Boolean.FALSE) {
              continue;
            }
            paramObject2 = Scheme.applyToArgs;
            localObject1 = localframe9.out;
            paramObject1 = AddOp.$Mn.apply2(paramArrayOfObject.width, AddOp.$Mn.apply2(paramArrayOfObject.precision, paramArrayOfObject.pr));
          }
        }
        try
        {
          i = ((Number)paramObject1).intValue();
          ((Procedure)paramObject2).apply2(localObject1, strings.makeString(i, Lit29));
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "make-string", 1, paramObject1);
        }
        paramObject1 = paramArrayOfObject.os;
        try
        {
          paramObject2 = Boolean.FALSE;
          if (paramObject1 != paramObject2)
          {
            i = 1;
            if ((i + 1 & 0x1) != 0) {
              continue;
            }
            paramObject2 = Scheme.numLEq;
            localObject1 = paramArrayOfObject.width;
            paramObject1 = paramArrayOfObject.os;
          }
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "x", -2, paramObject1);
        }
        try
        {
          localObject2 = (CharSequence)paramObject1;
          if (((Procedure)paramObject2).apply2(localObject1, Integer.valueOf(strings.stringLength((CharSequence)localObject2))) != Boolean.FALSE)
          {
            Scheme.applyToArgs.apply2(localframe9.out, paramArrayOfObject.os);
            continue;
            i = 0;
            continue;
          }
          paramObject1 = Scheme.applyToArgs;
          paramObject2 = localframe9.out;
          localObject2 = AddOp.$Mn;
          localObject3 = paramArrayOfObject.width;
          localObject1 = paramArrayOfObject.os;
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "string-length", 1, paramObject1);
        }
        try
        {
          localObject4 = (CharSequence)localObject1;
          localObject1 = ((Procedure)localObject2).apply2(localObject3, Integer.valueOf(strings.stringLength((CharSequence)localObject4)));
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-length", 1, localObject1);
        }
        try
        {
          i = ((Number)localObject1).intValue();
          if (((Procedure)paramObject1).apply2(paramObject2, strings.makeString(i, Lit29)) == Boolean.FALSE) {
            continue;
          }
          Scheme.applyToArgs.apply2(localframe9.out, paramArrayOfObject.os);
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "make-string", 1, localObject1);
        }
        paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit41);
        if (paramObject2 != Boolean.FALSE)
        {
          if (paramObject2 != Boolean.FALSE) {
            continue;
          }
          paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit46);
          if (paramObject2 != Boolean.FALSE)
          {
            if (paramObject2 == Boolean.FALSE) {
              continue;
            }
            paramObject2 = localframe9.lambda21out$St(paramArrayOfObject.lambda24integerConvert(lists.car.apply1(paramArrayOfObject.args), Lit48, Boolean.FALSE));
            paramObject1 = paramObject2;
            if (paramObject2 == Boolean.FALSE) {
              continue;
            }
            paramObject1 = lists.cdr.apply1(paramArrayOfObject.args);
          }
        }
        else
        {
          paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit3);
          if (paramObject2 != Boolean.FALSE)
          {
            if (paramObject2 == Boolean.FALSE) {
              continue;
            }
            continue;
          }
          paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit42);
          if (paramObject2 != Boolean.FALSE)
          {
            if (paramObject2 == Boolean.FALSE) {
              continue;
            }
            continue;
          }
          paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit43);
          if (paramObject2 != Boolean.FALSE)
          {
            if (paramObject2 == Boolean.FALSE) {
              continue;
            }
            continue;
          }
          if (Scheme.isEqv.apply2(paramObject1, Lit44) == Boolean.FALSE) {
            continue;
          }
          continue;
        }
        if (Scheme.isEqv.apply2(paramObject1, Lit47) == Boolean.FALSE) {
          if (Scheme.isEqv.apply2(paramObject1, Lit49) != Boolean.FALSE)
          {
            paramObject2 = lists.car.apply1(paramArrayOfObject.args);
            localObject1 = Lit50;
            if (stdio$Clhex$Mnupper$Mncase$Qu)
            {
              paramObject1 = unicode.string$Mndowncase;
              paramObject2 = localframe9.lambda21out$St(paramArrayOfObject.lambda24integerConvert(paramObject2, localObject1, paramObject1));
              paramObject1 = paramObject2;
              if (paramObject2 != Boolean.FALSE) {
                paramObject1 = lists.cdr.apply1(paramArrayOfObject.args);
              }
            }
            else
            {
              paramObject1 = Boolean.FALSE;
            }
          }
          else if (Scheme.isEqv.apply2(paramObject1, Lit51) != Boolean.FALSE)
          {
            paramObject2 = lists.car.apply1(paramArrayOfObject.args);
            localObject1 = Lit50;
            if (stdio$Clhex$Mnupper$Mncase$Qu)
            {
              paramObject1 = Boolean.FALSE;
              paramObject2 = localframe9.lambda21out$St(paramArrayOfObject.lambda24integerConvert(paramObject2, localObject1, paramObject1));
              paramObject1 = paramObject2;
              if (paramObject2 != Boolean.FALSE) {
                paramObject1 = lists.cdr.apply1(paramArrayOfObject.args);
              }
            }
            else
            {
              paramObject1 = unicode.string$Mnupcase;
            }
          }
          else
          {
            paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit52);
            if (paramObject2 != Boolean.FALSE)
            {
              if (paramObject2 != Boolean.FALSE)
              {
                paramObject2 = localframe9.lambda21out$St(paramArrayOfObject.lambda24integerConvert(lists.car.apply1(paramArrayOfObject.args), Lit14, Boolean.FALSE));
                paramObject1 = paramObject2;
                if (paramObject2 == Boolean.FALSE) {
                  continue;
                }
                paramObject1 = lists.cdr.apply1(paramArrayOfObject.args);
              }
            }
            else {
              if (Scheme.isEqv.apply2(paramObject1, Lit53) != Boolean.FALSE) {
                continue;
              }
            }
            if (Scheme.isEqv.apply2(paramObject1, Lit28) != Boolean.FALSE)
            {
              paramObject2 = Scheme.applyToArgs.apply2(localframe9.out, Lit28);
              paramObject1 = paramObject2;
              if (paramObject2 != Boolean.FALSE) {
                paramObject1 = paramArrayOfObject.args;
              }
            }
            else
            {
              paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit25);
              if (paramObject2 != Boolean.FALSE)
              {
                if (paramObject2 == Boolean.FALSE) {
                  continue;
                }
                paramObject1 = lists.car.apply1(paramArrayOfObject.args);
                localObject1 = localframe9.fc;
                paramObject2 = new frame11();
                ((frame11)paramObject2).staticLink = paramArrayOfObject;
                ((frame11)paramObject2).fc = localObject1;
                localObject1 = paramArrayOfObject.precision;
              }
            }
          }
        }
        try
        {
          localObject2 = LangObjType.coerceRealNum(localObject1);
          if (numbers.isNegative((RealNum)localObject2))
          {
            paramArrayOfObject.precision = Lit59;
            if (!numbers.isNumber(paramObject1)) {
              continue;
            }
          }
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "negative?", 1, localObject1);
        }
        try
        {
          localObject1 = (Number)paramObject1;
          paramObject1 = numbers.number$To$String(numbers.exact$To$Inexact((Number)localObject1));
          ((frame11)paramObject2).format$Mnreal = ((frame11)paramObject2).format$Mnreal;
          paramObject1 = stdio$ClParseFloat(paramObject1, ((frame11)paramObject2).lambda$Fn17);
          if (paramObject1 != Boolean.FALSE)
          {
            paramObject2 = localframe9.lambda21out$St(paramObject1);
            paramObject1 = paramObject2;
            if (paramObject2 == Boolean.FALSE) {
              continue;
            }
            paramObject1 = lists.cdr.apply1(paramArrayOfObject.args);
            continue;
            paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit26);
            if (paramObject2 != Boolean.FALSE)
            {
              if (paramObject2 != Boolean.FALSE) {
                continue;
              }
              if (!localframe9.lambda19isEndOfFormat()) {
                continue;
              }
              return localframe9.lambda20incomplete();
            }
            paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit13);
            if (paramObject2 != Boolean.FALSE)
            {
              if (paramObject2 == Boolean.FALSE) {
                continue;
              }
              continue;
            }
            paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit54);
            if (paramObject2 != Boolean.FALSE)
            {
              if (paramObject2 == Boolean.FALSE) {
                continue;
              }
              continue;
            }
            paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit55);
            if (paramObject2 != Boolean.FALSE)
            {
              if (paramObject2 == Boolean.FALSE) {
                continue;
              }
              continue;
            }
            paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit56);
            if (paramObject2 != Boolean.FALSE)
            {
              if (paramObject2 == Boolean.FALSE) {
                continue;
              }
              continue;
            }
            paramObject2 = Scheme.isEqv.apply2(paramObject1, Lit57);
            if (paramObject2 != Boolean.FALSE)
            {
              if (paramObject2 == Boolean.FALSE) {
                continue;
              }
              continue;
            }
            if (Scheme.isEqv.apply2(paramObject1, Lit58) == Boolean.FALSE) {
              continue;
            }
            continue;
            localObject1 = paramArrayOfObject.precision;
          }
          try
          {
            localObject2 = (Number)localObject1;
            bool = numbers.isZero((Number)localObject2);
            if (bool) {
              localObject1 = ((frame11)paramObject2).fc;
            }
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "zero?", 1, localObject1);
          }
          try
          {
            localObject2 = (Char)localObject1;
            if (!unicode.isCharCi$Eq((Char)localObject2, Lit55)) {
              continue;
            }
            paramArrayOfObject.precision = Lit7;
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "char-ci=?", 1, localObject1);
          }
          if (!bool) {
            continue;
          }
          continue;
          if (strings.isString(paramObject1)) {
            continue;
          }
          if (!misc.isSymbol(paramObject1)) {}
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "exact->inexact", 1, paramObject1);
        }
        try
        {
          localObject1 = (Symbol)paramObject1;
          paramObject1 = misc.symbol$To$String((Symbol)localObject1);
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "symbol->string", 1, paramObject1);
        }
        paramObject1 = "???";
        continue;
        paramObject1 = paramArrayOfObject.lambda23pad$V("???", new Object[0]);
        continue;
        paramObject2 = Scheme.applyToArgs.apply2(localframe9.out, Lit28);
        paramObject1 = paramObject2;
        if (paramObject2 != Boolean.FALSE)
        {
          paramObject2 = Scheme.applyToArgs.apply2(localframe9.out, localframe9.fc);
          paramObject1 = paramObject2;
          if (paramObject2 != Boolean.FALSE)
          {
            paramObject2 = Scheme.applyToArgs.apply2(localframe9.out, Lit65);
            paramObject1 = paramObject2;
            if (paramObject2 != Boolean.FALSE)
            {
              paramObject1 = paramArrayOfObject.args;
              continue;
              paramObject2 = Scheme.applyToArgs.apply2(localframe9.out, localframe9.fc);
              paramObject1 = paramObject2;
              if (paramObject2 != Boolean.FALSE) {
                paramObject1 = paramArrayOfObject.args;
              }
            }
          }
        }
      }
    }
    return Values.empty;
  }
  
  public static Object stdio$ClParseFloat(Object paramObject1, Object paramObject2)
  {
    frame localframe = new frame();
    localframe.str = paramObject1;
    localframe.proc = paramObject2;
    paramObject1 = localframe.str;
    try
    {
      paramObject2 = (CharSequence)paramObject1;
      localframe.n = strings.stringLength((CharSequence)paramObject2);
      return localframe.lambda4real(Lit1, localframe.lambda$Fn1);
    }
    catch (ClassCastException paramObject2)
    {
      throw new WrongType((ClassCastException)paramObject2, "string-length", 1, paramObject1);
    }
  }
  
  /* Error */
  public static Object stdio$ClRoundString(CharSequence paramCharSequence, Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: new 45	gnu/kawa/slib/printf$frame8
    //   3: dup
    //   4: invokespecial 835	gnu/kawa/slib/printf$frame8:<init>	()V
    //   7: astore 4
    //   9: aload 4
    //   11: aload_0
    //   12: putfield 838	gnu/kawa/slib/printf$frame8:str	Ljava/lang/CharSequence;
    //   15: aload 4
    //   17: getfield 838	gnu/kawa/slib/printf$frame8:str	Ljava/lang/CharSequence;
    //   20: invokestatic 497	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
    //   23: iconst_1
    //   24: isub
    //   25: istore 7
    //   27: getstatic 841	kawa/standard/Scheme:numLss	Lgnu/kawa/functions/NumberCompare;
    //   30: aload_1
    //   31: getstatic 400	gnu/kawa/slib/printf:Lit1	Lgnu/math/IntNum;
    //   34: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   37: getstatic 530	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   40: if_acmpeq +93 -> 133
    //   43: ldc -31
    //   45: astore_0
    //   46: aload_0
    //   47: astore_1
    //   48: aload_2
    //   49: getstatic 530	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   52: if_acmpeq +79 -> 131
    //   55: aload_0
    //   56: checkcast 493	java/lang/CharSequence
    //   59: astore_1
    //   60: aload_1
    //   61: invokestatic 497	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
    //   64: iconst_1
    //   65: isub
    //   66: invokestatic 503	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   69: astore_1
    //   70: getstatic 709	kawa/standard/Scheme:numLEq	Lgnu/kawa/functions/NumberCompare;
    //   73: aload_1
    //   74: aload_2
    //   75: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   78: astore_3
    //   79: aload_3
    //   80: checkcast 526	java/lang/Boolean
    //   83: invokevirtual 844	java/lang/Boolean:booleanValue	()Z
    //   86: istore 9
    //   88: iload 9
    //   90: ifeq +547 -> 637
    //   93: iload 9
    //   95: ifeq +578 -> 673
    //   98: aload_0
    //   99: checkcast 493	java/lang/CharSequence
    //   102: astore_2
    //   103: getstatic 602	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   106: aload_1
    //   107: getstatic 388	gnu/kawa/slib/printf:Lit7	Lgnu/math/IntNum;
    //   110: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   113: astore_0
    //   114: aload_0
    //   115: checkcast 516	java/lang/Number
    //   118: invokevirtual 520	java/lang/Number:intValue	()I
    //   121: istore 7
    //   123: aload_2
    //   124: iconst_0
    //   125: iload 7
    //   127: invokestatic 549	kawa/lib/strings:substring	(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
    //   130: astore_1
    //   131: aload_1
    //   132: areturn
    //   133: getstatic 847	kawa/standard/Scheme:numEqu	Lgnu/kawa/functions/NumberCompare;
    //   136: iload 7
    //   138: invokestatic 503	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   141: aload_1
    //   142: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   145: getstatic 530	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   148: if_acmpeq +12 -> 160
    //   151: aload 4
    //   153: getfield 838	gnu/kawa/slib/printf$frame8:str	Ljava/lang/CharSequence;
    //   156: astore_0
    //   157: goto -111 -> 46
    //   160: getstatic 841	kawa/standard/Scheme:numLss	Lgnu/kawa/functions/NumberCompare;
    //   163: iload 7
    //   165: invokestatic 503	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   168: aload_1
    //   169: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   172: getstatic 530	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   175: if_acmpeq +139 -> 314
    //   178: getstatic 400	gnu/kawa/slib/printf:Lit1	Lgnu/math/IntNum;
    //   181: astore_3
    //   182: getstatic 660	gnu/kawa/functions/AddOp:$Mn	Lgnu/kawa/functions/AddOp;
    //   185: astore 5
    //   187: aload_1
    //   188: astore_0
    //   189: aload_2
    //   190: getstatic 530	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   193: if_acmpeq +5 -> 198
    //   196: aload_2
    //   197: astore_0
    //   198: iconst_2
    //   199: anewarray 207	java/lang/Object
    //   202: dup
    //   203: iconst_0
    //   204: aload_3
    //   205: aastore
    //   206: dup
    //   207: iconst_1
    //   208: aload 5
    //   210: aload_0
    //   211: iload 7
    //   213: invokestatic 503	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   216: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   219: aastore
    //   220: invokestatic 851	kawa/lib/numbers:max	([Ljava/lang/Object;)Ljava/lang/Object;
    //   223: astore_0
    //   224: aload_0
    //   225: checkcast 516	java/lang/Number
    //   228: astore_1
    //   229: aload_1
    //   230: invokestatic 788	kawa/lib/numbers:isZero	(Ljava/lang/Number;)Z
    //   233: ifeq +12 -> 245
    //   236: aload 4
    //   238: getfield 838	gnu/kawa/slib/printf$frame8:str	Ljava/lang/CharSequence;
    //   241: astore_0
    //   242: goto -196 -> 46
    //   245: aload 4
    //   247: getfield 838	gnu/kawa/slib/printf$frame8:str	Ljava/lang/CharSequence;
    //   250: astore_1
    //   251: aload_0
    //   252: checkcast 516	java/lang/Number
    //   255: invokevirtual 520	java/lang/Number:intValue	()I
    //   258: istore 8
    //   260: aload 4
    //   262: getfield 838	gnu/kawa/slib/printf$frame8:str	Ljava/lang/CharSequence;
    //   265: iload 7
    //   267: invokestatic 579	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
    //   270: invokestatic 179	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   273: invokestatic 855	kawa/lib/rnrs/unicode:isCharNumeric	(Lgnu/text/Char;)Z
    //   276: ifeq +31 -> 307
    //   279: getstatic 384	gnu/kawa/slib/printf:Lit9	Lgnu/text/Char;
    //   282: astore_0
    //   283: iconst_2
    //   284: anewarray 207	java/lang/Object
    //   287: dup
    //   288: iconst_0
    //   289: aload_1
    //   290: aastore
    //   291: dup
    //   292: iconst_1
    //   293: iload 8
    //   295: aload_0
    //   296: invokestatic 732	kawa/lib/strings:makeString	(ILjava/lang/Object;)Ljava/lang/CharSequence;
    //   299: aastore
    //   300: invokestatic 859	kawa/lib/strings:stringAppend	([Ljava/lang/Object;)Lgnu/lists/FString;
    //   303: astore_0
    //   304: goto -62 -> 242
    //   307: getstatic 386	gnu/kawa/slib/printf:Lit8	Lgnu/text/Char;
    //   310: astore_0
    //   311: goto -28 -> 283
    //   314: aload 4
    //   316: getfield 838	gnu/kawa/slib/printf$frame8:str	Ljava/lang/CharSequence;
    //   319: astore_3
    //   320: getstatic 602	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   323: aload_1
    //   324: getstatic 388	gnu/kawa/slib/printf:Lit7	Lgnu/math/IntNum;
    //   327: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   330: astore_0
    //   331: aload_0
    //   332: checkcast 516	java/lang/Number
    //   335: invokevirtual 520	java/lang/Number:intValue	()I
    //   338: istore 8
    //   340: aload_3
    //   341: iconst_0
    //   342: iload 8
    //   344: invokestatic 549	kawa/lib/strings:substring	(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
    //   347: astore_3
    //   348: aload 4
    //   350: getstatic 602	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   353: getstatic 388	gnu/kawa/slib/printf:Lit7	Lgnu/math/IntNum;
    //   356: aload_1
    //   357: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   360: invokevirtual 862	gnu/kawa/slib/printf$frame8:lambda17dig	(Ljava/lang/Object;)Ljava/lang/Object;
    //   363: astore 5
    //   365: getstatic 729	kawa/standard/Scheme:numGrt	Lgnu/kawa/functions/NumberCompare;
    //   368: aload 5
    //   370: getstatic 366	gnu/kawa/slib/printf:Lit15	Lgnu/math/IntNum;
    //   373: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   376: astore_0
    //   377: aload_0
    //   378: checkcast 526	java/lang/Boolean
    //   381: invokevirtual 844	java/lang/Boolean:booleanValue	()Z
    //   384: istore 9
    //   386: iload 9
    //   388: ifeq +89 -> 477
    //   391: aload_3
    //   392: astore_0
    //   393: iload 9
    //   395: ifeq -349 -> 46
    //   398: aload_1
    //   399: astore_0
    //   400: aload 4
    //   402: aload_0
    //   403: invokevirtual 862	gnu/kawa/slib/printf$frame8:lambda17dig	(Ljava/lang/Object;)Ljava/lang/Object;
    //   406: astore 5
    //   408: getstatic 841	kawa/standard/Scheme:numLss	Lgnu/kawa/functions/NumberCompare;
    //   411: aload 5
    //   413: getstatic 364	gnu/kawa/slib/printf:Lit16	Lgnu/math/IntNum;
    //   416: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   419: getstatic 530	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   422: if_acmpeq +179 -> 601
    //   425: aload_3
    //   426: checkcast 864	gnu/lists/CharSeq
    //   429: astore_1
    //   430: aload_0
    //   431: checkcast 516	java/lang/Number
    //   434: invokevirtual 520	java/lang/Number:intValue	()I
    //   437: istore 7
    //   439: getstatic 602	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   442: aload 5
    //   444: getstatic 388	gnu/kawa/slib/printf:Lit7	Lgnu/math/IntNum;
    //   447: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   450: astore_0
    //   451: aload_0
    //   452: checkcast 516	java/lang/Number
    //   455: astore 4
    //   457: aload_1
    //   458: iload 7
    //   460: aload 4
    //   462: invokestatic 772	kawa/lib/numbers:number$To$String	(Ljava/lang/Number;)Ljava/lang/CharSequence;
    //   465: iconst_0
    //   466: invokestatic 579	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
    //   469: invokestatic 868	kawa/lib/strings:stringSet$Ex	(Lgnu/lists/CharSeq;IC)V
    //   472: aload_3
    //   473: astore_0
    //   474: goto -428 -> 46
    //   477: getstatic 847	kawa/standard/Scheme:numEqu	Lgnu/kawa/functions/NumberCompare;
    //   480: aload 5
    //   482: getstatic 366	gnu/kawa/slib/printf:Lit15	Lgnu/math/IntNum;
    //   485: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   488: astore_0
    //   489: aload_0
    //   490: checkcast 526	java/lang/Boolean
    //   493: invokevirtual 844	java/lang/Boolean:booleanValue	()Z
    //   496: istore 9
    //   498: iload 9
    //   500: ifeq +91 -> 591
    //   503: getstatic 602	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   506: getstatic 368	gnu/kawa/slib/printf:Lit14	Lgnu/math/IntNum;
    //   509: aload_1
    //   510: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   513: astore_0
    //   514: getstatic 729	kawa/standard/Scheme:numGrt	Lgnu/kawa/functions/NumberCompare;
    //   517: aload_0
    //   518: iload 7
    //   520: invokestatic 503	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   523: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   526: getstatic 530	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   529: if_acmpeq +25 -> 554
    //   532: aload_3
    //   533: astore_0
    //   534: aload 4
    //   536: aload_1
    //   537: invokevirtual 862	gnu/kawa/slib/printf$frame8:lambda17dig	(Ljava/lang/Object;)Ljava/lang/Object;
    //   540: checkcast 516	java/lang/Number
    //   543: invokevirtual 520	java/lang/Number:intValue	()I
    //   546: iconst_1
    //   547: iand
    //   548: ifeq -502 -> 46
    //   551: goto -153 -> 398
    //   554: aload 4
    //   556: aload_0
    //   557: invokevirtual 862	gnu/kawa/slib/printf$frame8:lambda17dig	(Ljava/lang/Object;)Ljava/lang/Object;
    //   560: astore 5
    //   562: aload 5
    //   564: checkcast 516	java/lang/Number
    //   567: astore 6
    //   569: aload 6
    //   571: invokestatic 788	kawa/lib/numbers:isZero	(Ljava/lang/Number;)Z
    //   574: ifeq -176 -> 398
    //   577: getstatic 602	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   580: aload_0
    //   581: getstatic 388	gnu/kawa/slib/printf:Lit7	Lgnu/math/IntNum;
    //   584: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   587: astore_0
    //   588: goto -74 -> 514
    //   591: aload_3
    //   592: astore_0
    //   593: iload 9
    //   595: ifeq -549 -> 46
    //   598: goto -200 -> 398
    //   601: aload_3
    //   602: checkcast 864	gnu/lists/CharSeq
    //   605: astore_1
    //   606: aload_0
    //   607: checkcast 516	java/lang/Number
    //   610: invokevirtual 520	java/lang/Number:intValue	()I
    //   613: istore 7
    //   615: aload_1
    //   616: iload 7
    //   618: bipush 48
    //   620: invokestatic 868	kawa/lib/strings:stringSet$Ex	(Lgnu/lists/CharSeq;IC)V
    //   623: getstatic 660	gnu/kawa/functions/AddOp:$Mn	Lgnu/kawa/functions/AddOp;
    //   626: aload_0
    //   627: getstatic 388	gnu/kawa/slib/printf:Lit7	Lgnu/math/IntNum;
    //   630: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   633: astore_0
    //   634: goto -234 -> 400
    //   637: getstatic 384	gnu/kawa/slib/printf:Lit9	Lgnu/text/Char;
    //   640: astore_3
    //   641: aload_0
    //   642: checkcast 493	java/lang/CharSequence
    //   645: astore 4
    //   647: aload_1
    //   648: checkcast 516	java/lang/Number
    //   651: invokevirtual 520	java/lang/Number:intValue	()I
    //   654: istore 7
    //   656: aload_3
    //   657: aload 4
    //   659: iload 7
    //   661: invokestatic 579	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
    //   664: invokestatic 179	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   667: invokestatic 873	kawa/lib/characters:isChar$Eq	(Lgnu/text/Char;Lgnu/text/Char;)Z
    //   670: ifeq -572 -> 98
    //   673: getstatic 660	gnu/kawa/functions/AddOp:$Mn	Lgnu/kawa/functions/AddOp;
    //   676: aload_1
    //   677: getstatic 388	gnu/kawa/slib/printf:Lit7	Lgnu/math/IntNum;
    //   680: invokevirtual 545	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   683: astore_1
    //   684: goto -614 -> 70
    //   687: astore_1
    //   688: new 551	gnu/mapping/WrongType
    //   691: dup
    //   692: aload_1
    //   693: ldc_w 815
    //   696: iconst_1
    //   697: aload_0
    //   698: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   701: athrow
    //   702: astore_1
    //   703: new 551	gnu/mapping/WrongType
    //   706: dup
    //   707: aload_1
    //   708: ldc_w 553
    //   711: iconst_1
    //   712: aload_0
    //   713: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   716: athrow
    //   717: astore_1
    //   718: new 551	gnu/mapping/WrongType
    //   721: dup
    //   722: aload_1
    //   723: ldc_w 559
    //   726: iconst_3
    //   727: aload_0
    //   728: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   731: athrow
    //   732: astore_1
    //   733: new 551	gnu/mapping/WrongType
    //   736: dup
    //   737: aload_1
    //   738: ldc_w 813
    //   741: bipush -2
    //   743: aload_0
    //   744: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   747: athrow
    //   748: astore_1
    //   749: new 551	gnu/mapping/WrongType
    //   752: dup
    //   753: aload_1
    //   754: ldc_w 813
    //   757: bipush -2
    //   759: aload_0
    //   760: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   763: athrow
    //   764: astore_0
    //   765: new 551	gnu/mapping/WrongType
    //   768: dup
    //   769: aload_0
    //   770: ldc_w 815
    //   773: iconst_1
    //   774: aload 5
    //   776: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   779: athrow
    //   780: astore_0
    //   781: new 551	gnu/mapping/WrongType
    //   784: dup
    //   785: aload_0
    //   786: ldc_w 875
    //   789: iconst_1
    //   790: aload_3
    //   791: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   794: athrow
    //   795: astore_1
    //   796: new 551	gnu/mapping/WrongType
    //   799: dup
    //   800: aload_1
    //   801: ldc_w 875
    //   804: iconst_2
    //   805: aload_0
    //   806: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   809: athrow
    //   810: astore_1
    //   811: new 551	gnu/mapping/WrongType
    //   814: dup
    //   815: aload_1
    //   816: ldc_w 877
    //   819: iconst_1
    //   820: aload_0
    //   821: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   824: athrow
    //   825: astore_0
    //   826: new 551	gnu/mapping/WrongType
    //   829: dup
    //   830: aload_0
    //   831: ldc_w 875
    //   834: iconst_1
    //   835: aload_3
    //   836: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   839: athrow
    //   840: astore_1
    //   841: new 551	gnu/mapping/WrongType
    //   844: dup
    //   845: aload_1
    //   846: ldc_w 875
    //   849: iconst_2
    //   850: aload_0
    //   851: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   854: athrow
    //   855: astore_1
    //   856: new 551	gnu/mapping/WrongType
    //   859: dup
    //   860: aload_1
    //   861: ldc_w 558
    //   864: iconst_1
    //   865: aload_0
    //   866: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   869: athrow
    //   870: astore_0
    //   871: new 551	gnu/mapping/WrongType
    //   874: dup
    //   875: aload_0
    //   876: ldc_w 813
    //   879: bipush -2
    //   881: aload_3
    //   882: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   885: athrow
    //   886: astore_1
    //   887: new 551	gnu/mapping/WrongType
    //   890: dup
    //   891: aload_1
    //   892: ldc_w 805
    //   895: iconst_1
    //   896: aload_0
    //   897: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   900: athrow
    //   901: astore_0
    //   902: new 551	gnu/mapping/WrongType
    //   905: dup
    //   906: aload_0
    //   907: ldc_w 805
    //   910: iconst_2
    //   911: aload_1
    //   912: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   915: athrow
    //   916: astore_1
    //   917: new 551	gnu/mapping/WrongType
    //   920: dup
    //   921: aload_1
    //   922: ldc_w 559
    //   925: iconst_1
    //   926: aload_0
    //   927: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   930: athrow
    //   931: astore_1
    //   932: new 551	gnu/mapping/WrongType
    //   935: dup
    //   936: aload_1
    //   937: ldc_w 559
    //   940: iconst_3
    //   941: aload_0
    //   942: invokespecial 556	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   945: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	946	0	paramCharSequence	CharSequence
    //   0	946	1	paramObject1	Object
    //   0	946	2	paramObject2	Object
    //   78	804	3	localObject1	Object
    //   7	651	4	localObject2	Object
    //   185	590	5	localObject3	Object
    //   567	3	6	localNumber	Number
    //   25	635	7	i	int
    //   258	85	8	j	int
    //   86	508	9	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   224	229	687	java/lang/ClassCastException
    //   251	260	702	java/lang/ClassCastException
    //   331	340	717	java/lang/ClassCastException
    //   377	386	732	java/lang/ClassCastException
    //   489	498	748	java/lang/ClassCastException
    //   562	569	764	java/lang/ClassCastException
    //   425	430	780	java/lang/ClassCastException
    //   430	439	795	java/lang/ClassCastException
    //   451	457	810	java/lang/ClassCastException
    //   601	606	825	java/lang/ClassCastException
    //   606	615	840	java/lang/ClassCastException
    //   55	60	855	java/lang/ClassCastException
    //   79	88	870	java/lang/ClassCastException
    //   641	647	886	java/lang/ClassCastException
    //   647	656	901	java/lang/ClassCastException
    //   98	103	916	java/lang/ClassCastException
    //   114	123	931	java/lang/ClassCastException
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    if (paramModuleMethod.selector == 22) {
      return stdio$ClParseFloat(paramObject1, paramObject2);
    }
    return super.apply2(paramModuleMethod, paramObject1, paramObject2);
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    if (paramModuleMethod.selector == 23) {}
    try
    {
      paramModuleMethod = (CharSequence)paramObject1;
      return stdio$ClRoundString(paramModuleMethod, paramObject2, paramObject3);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "stdio:round-string", 1, paramObject1);
    }
    return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.applyN(paramModuleMethod, paramArrayOfObject);
    case 24: 
      paramModuleMethod = paramArrayOfObject[0];
      localObject = paramArrayOfObject[1];
      i = paramArrayOfObject.length - 2;
      arrayOfObject = new Object[i];
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          return stdio$ClIprintf$V(paramModuleMethod, localObject, arrayOfObject);
        }
        arrayOfObject[i] = paramArrayOfObject[(i + 2)];
      }
    case 25: 
      paramModuleMethod = paramArrayOfObject[0];
      localObject = paramArrayOfObject[1];
      i = paramArrayOfObject.length - 2;
      arrayOfObject = new Object[i];
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          return fprintf$V(paramModuleMethod, localObject, arrayOfObject);
        }
        arrayOfObject[i] = paramArrayOfObject[(i + 2)];
      }
    case 26: 
      paramModuleMethod = paramArrayOfObject[0];
      i = paramArrayOfObject.length - 1;
      localObject = new Object[i];
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          return printf$V(paramModuleMethod, (Object[])localObject);
        }
        localObject[i] = paramArrayOfObject[(i + 1)];
      }
    }
    paramModuleMethod = paramArrayOfObject[0];
    Object localObject = paramArrayOfObject[1];
    int i = paramArrayOfObject.length - 2;
    Object[] arrayOfObject = new Object[i];
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        return sprintf$V(paramModuleMethod, localObject, arrayOfObject);
      }
      arrayOfObject[i] = paramArrayOfObject[(i + 2)];
    }
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 22)
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
    if (paramModuleMethod.selector == 23)
    {
      if ((paramObject1 instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      }
      return -786431;
    }
    return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
    case 27: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 26: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 25: 
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
    stdio$Clhex$Mnupper$Mncase$Qu = strings.isString$Eq("-F", numbers.number$To$String(Lit0, 16));
  }
  
  public class frame
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn1;
    int n;
    Object proc;
    Object str;
    
    public frame()
    {
      this$1 = new ModuleMethod(this, 12, null, 16388);
      this$1.setProperty("source-location", "printf.scm:106");
      this.lambda$Fn1 = this$1;
    }
    
    public static Boolean lambda1parseError()
    {
      return Boolean.FALSE;
    }
    
    public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    {
      if (paramModuleMethod.selector == 12) {
        return lambda5(paramObject1, paramObject2, paramObject3, paramObject4);
      }
      return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
    }
    
    public Object lambda2sign(Object paramObject1, Object paramObject2)
    {
      if (Scheme.numLss.apply2(paramObject1, Integer.valueOf(this.n)) != Boolean.FALSE) {
        localObject = this.str;
      }
      for (;;)
      {
        try
        {
          localCharSequence = (CharSequence)localObject;
        }
        catch (ClassCastException paramObject1)
        {
          CharSequence localCharSequence;
          int i;
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, localObject);
        }
        try
        {
          i = ((Number)paramObject1).intValue();
          i = strings.stringRef(localCharSequence, i);
          localObject = Scheme.isEqv.apply2(Char.make(i), printf.Lit5);
          if (localObject != Boolean.FALSE)
          {
            if (localObject != Boolean.FALSE) {
              return Scheme.applyToArgs.apply3(paramObject2, AddOp.$Pl.apply2(paramObject1, printf.Lit7), Char.make(i));
            }
          }
          else {
            if (Scheme.isEqv.apply2(Char.make(i), printf.Lit6) != Boolean.FALSE) {
              continue;
            }
          }
          return Scheme.applyToArgs.apply3(paramObject2, paramObject1, printf.Lit6);
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "string-ref", 2, paramObject1);
        }
      }
      return Values.empty;
    }
    
    public Object lambda3digits(Object paramObject1, Object paramObject2)
    {
      localObject1 = paramObject1;
      localObject2 = Scheme.numGEq.apply2(localObject1, Integer.valueOf(this.n));
      for (;;)
      {
        try
        {
          bool = ((Boolean)localObject2).booleanValue();
          if (bool)
          {
            if (bool) {
              continue;
            }
            localObject1 = AddOp.$Pl.apply2(localObject1, printf.Lit7);
            break;
          }
          localObject2 = this.str;
        }
        catch (ClassCastException paramObject1)
        {
          try
          {
            boolean bool;
            localCharSequence = (CharSequence)localObject3;
          }
          catch (ClassCastException paramObject1)
          {
            Object localObject3;
            int i;
            CharSequence localCharSequence;
            int j;
            throw new WrongType((ClassCastException)paramObject1, "substring", 1, localObject3);
          }
          try
          {
            i = ((Number)paramObject1).intValue();
          }
          catch (ClassCastException paramObject2)
          {
            throw new WrongType((ClassCastException)paramObject2, "substring", 2, paramObject1);
          }
          try
          {
            j = ((Number)localObject1).intValue();
            paramObject1 = strings.substring(localCharSequence, i, j);
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "substring", 3, localObject1);
          }
          paramObject1 = paramObject1;
          throw new WrongType((ClassCastException)paramObject1, "x", -2, localObject2);
        }
        try
        {
          localObject3 = (CharSequence)localObject2;
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, localObject2);
        }
        try
        {
          i = ((Number)localObject1).intValue();
          bool = unicode.isCharNumeric(Char.make(strings.stringRef((CharSequence)localObject3, i)));
          if (bool)
          {
            if (bool) {
              continue;
            }
            localObject2 = Scheme.applyToArgs;
            if (Scheme.numEqu.apply2(paramObject1, localObject1) != Boolean.FALSE)
            {
              paramObject1 = "0";
              return ((Procedure)localObject2).apply3(paramObject2, localObject1, paramObject1);
            }
          }
          else
          {
            localObject3 = printf.Lit8;
            localObject2 = this.str;
          }
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject1);
        }
        try
        {
          localCharSequence = (CharSequence)localObject2;
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, localObject2);
        }
        try
        {
          i = ((Number)localObject1).intValue();
          if (!characters.isChar$Eq((Char)localObject3, Char.make(strings.stringRef(localCharSequence, i)))) {}
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject1);
        }
      }
      localObject3 = this.str;
    }
    
    public Object lambda4real(Object paramObject1, Object paramObject2)
    {
      localObject1 = new printf.frame2();
      ((printf.frame2)localObject1).staticLink = this;
      ((printf.frame2)localObject1).cont = paramObject2;
      paramObject2 = ((printf.frame2)localObject1).lambda$Fn5;
      for (;;)
      {
        localObject1 = Scheme.numLss.apply2(paramObject1, Integer.valueOf(this.n - 1));
        try
        {
          bool = ((Boolean)localObject1).booleanValue();
          if (bool)
          {
            localObject2 = printf.Lit8;
            localObject1 = this.str;
          }
        }
        catch (ClassCastException paramObject1)
        {
          for (;;)
          {
            try
            {
              localCharSequence = (CharSequence)localObject1;
            }
            catch (ClassCastException paramObject1)
            {
              boolean bool;
              Object localObject2;
              CharSequence localCharSequence;
              int i;
              label97:
              label166:
              label223:
              label252:
              throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, localObject1);
            }
            try
            {
              i = ((Number)paramObject1).intValue();
              if (characters.isChar$Eq((Char)localObject2, Char.make(strings.stringRef(localCharSequence, i)))) {
                localObject1 = this.str;
              }
            }
            catch (ClassCastException paramObject2)
            {
              throw new WrongType((ClassCastException)paramObject2, "string-ref", 2, paramObject1);
            }
          }
          try
          {
            localObject2 = (CharSequence)localObject1;
            localObject1 = AddOp.$Pl.apply2(paramObject1, printf.Lit7);
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, localObject1);
          }
          try
          {
            i = ((Number)localObject1).intValue();
            i = strings.stringRef((CharSequence)localObject2, i);
            localObject1 = Scheme.isEqv.apply2(Char.make(i), printf.Lit12);
            if (localObject1 != Boolean.FALSE)
            {
              if (localObject1 != Boolean.FALSE)
              {
                paramObject1 = AddOp.$Pl.apply2(paramObject1, printf.Lit14);
                continue;
                if (bool) {
                  break label97;
                }
                return Scheme.applyToArgs.apply2(paramObject2, paramObject1);
              }
            }
            else
            {
              localObject1 = Scheme.isEqv.apply2(Char.make(i), printf.Lit3);
              if (localObject1 == Boolean.FALSE) {
                break label252;
              }
              if (localObject1 != Boolean.FALSE) {
                break label166;
              }
            }
            if (Scheme.isEqv.apply2(Char.make(i), printf.Lit11) != Boolean.FALSE)
            {
              return Scheme.applyToArgs.apply2(paramObject2, paramObject1);
              if (Scheme.isEqv.apply2(Char.make(i), printf.Lit13) == Boolean.FALSE) {
                break label223;
              }
              break label166;
            }
            return lambda1parseError();
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject1);
          }
          paramObject1 = paramObject1;
          throw new WrongType((ClassCastException)paramObject1, "x", -2, localObject1);
        }
      }
    }
    
    Object lambda5(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    {
      printf.frame0 localframe0 = new printf.frame0();
      localframe0.staticLink = this;
      localframe0.sgn = paramObject2;
      localframe0.digs = paramObject3;
      localframe0.ex = paramObject4;
      if (Scheme.numEqu.apply2(paramObject1, Integer.valueOf(this.n)) != Boolean.FALSE) {
        return Scheme.applyToArgs.apply4(this.proc, localframe0.sgn, localframe0.digs, localframe0.ex);
      }
      paramObject2 = this.str;
      for (;;)
      {
        try
        {
          paramObject3 = (CharSequence)paramObject2;
        }
        catch (ClassCastException paramObject1)
        {
          int i;
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, paramObject2);
        }
        try
        {
          i = ((Number)paramObject1).intValue();
          if (lists.memv(Char.make(strings.stringRef((CharSequence)paramObject3, i)), printf.Lit2) != Boolean.FALSE) {
            return lambda4real(paramObject1, localframe0.lambda$Fn2);
          }
          paramObject3 = Scheme.isEqv;
          paramObject2 = this.str;
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "string-ref", 2, paramObject1);
        }
        try
        {
          paramObject4 = (CharSequence)paramObject2;
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, paramObject2);
        }
        try
        {
          i = ((Number)paramObject1).intValue();
          if (((Procedure)paramObject3).apply2(Char.make(strings.stringRef((CharSequence)paramObject4, i)), printf.Lit4) != Boolean.FALSE) {
            paramObject1 = this.str;
          }
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "string-ref", 2, paramObject1);
        }
        try
        {
          paramObject2 = (CharSequence)paramObject1;
          localframe0.num = numbers.string$To$Number((CharSequence)paramObject2);
          if (localframe0.num != Boolean.FALSE) {
            paramObject1 = localframe0.num;
          }
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "string->number", 1, paramObject1);
        }
      }
      try
      {
        paramObject2 = (Complex)paramObject1;
        return printf.stdio$ClParseFloat(numbers.number$To$String(numbers.realPart((Complex)paramObject2)), localframe0.lambda$Fn3);
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "real-part", 1, paramObject1);
      }
      return lambda1parseError();
      return Boolean.FALSE;
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
  }
  
  public class frame0
    extends ModuleBody
  {
    Object digs;
    Object ex;
    final ModuleMethod lambda$Fn2;
    final ModuleMethod lambda$Fn3;
    Object num;
    Object sgn;
    printf.frame staticLink;
    
    public frame0()
    {
      this$1 = new ModuleMethod(this, 2, null, 16388);
      this$1.setProperty("source-location", "printf.scm:111");
      this.lambda$Fn2 = this$1;
      this$1 = new ModuleMethod(this, 3, null, 12291);
      this$1.setProperty("source-location", "printf.scm:123");
      this.lambda$Fn3 = this$1;
    }
    
    public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
    {
      if (paramModuleMethod.selector == 3) {
        return lambda7(paramObject1, paramObject2, paramObject3);
      }
      return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
    }
    
    public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    {
      if (paramModuleMethod.selector == 2) {
        return lambda6(paramObject1, paramObject2, paramObject3, paramObject4);
      }
      return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
    }
    
    /* Error */
    Object lambda6(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    {
      // Byte code:
      //   0: getstatic 77	kawa/standard/Scheme:numEqu	Lgnu/kawa/functions/NumberCompare;
      //   3: aload_1
      //   4: aload_0
      //   5: getfield 79	gnu/kawa/slib/printf$frame0:staticLink	Lgnu/kawa/slib/printf$frame;
      //   8: getfield 82	gnu/kawa/slib/printf$frame:n	I
      //   11: iconst_1
      //   12: isub
      //   13: invokestatic 88	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   16: invokevirtual 94	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   19: astore 5
      //   21: aload 5
      //   23: checkcast 96	java/lang/Boolean
      //   26: invokevirtual 100	java/lang/Boolean:booleanValue	()Z
      //   29: istore 9
      //   31: iload 9
      //   33: ifeq +108 -> 141
      //   36: getstatic 104	gnu/kawa/slib/printf:Lit3	Lgnu/text/Char;
      //   39: astore 6
      //   41: aload_0
      //   42: getfield 79	gnu/kawa/slib/printf$frame0:staticLink	Lgnu/kawa/slib/printf$frame;
      //   45: getfield 107	gnu/kawa/slib/printf$frame:str	Ljava/lang/Object;
      //   48: astore 5
      //   50: aload 5
      //   52: checkcast 109	java/lang/CharSequence
      //   55: astore 7
      //   57: aload_1
      //   58: checkcast 111	java/lang/Number
      //   61: invokevirtual 115	java/lang/Number:intValue	()I
      //   64: istore 8
      //   66: aload 6
      //   68: aload 7
      //   70: iload 8
      //   72: invokestatic 121	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
      //   75: invokestatic 127	gnu/text/Char:make	(I)Lgnu/text/Char;
      //   78: invokestatic 133	kawa/lib/rnrs/unicode:isCharCi$Eq	(Lgnu/text/Char;Lgnu/text/Char;)Z
      //   81: ifeq +65 -> 146
      //   84: getstatic 137	kawa/standard/Scheme:applyToArgs	Lgnu/kawa/functions/ApplyToArgs;
      //   87: bipush 7
      //   89: anewarray 139	java/lang/Object
      //   92: dup
      //   93: iconst_0
      //   94: aload_0
      //   95: getfield 79	gnu/kawa/slib/printf$frame0:staticLink	Lgnu/kawa/slib/printf$frame;
      //   98: getfield 142	gnu/kawa/slib/printf$frame:proc	Ljava/lang/Object;
      //   101: aastore
      //   102: dup
      //   103: iconst_1
      //   104: aload_0
      //   105: getfield 144	gnu/kawa/slib/printf$frame0:sgn	Ljava/lang/Object;
      //   108: aastore
      //   109: dup
      //   110: iconst_2
      //   111: aload_0
      //   112: getfield 146	gnu/kawa/slib/printf$frame0:digs	Ljava/lang/Object;
      //   115: aastore
      //   116: dup
      //   117: iconst_3
      //   118: aload_0
      //   119: getfield 148	gnu/kawa/slib/printf$frame0:ex	Ljava/lang/Object;
      //   122: aastore
      //   123: dup
      //   124: iconst_4
      //   125: aload_2
      //   126: aastore
      //   127: dup
      //   128: iconst_5
      //   129: aload_3
      //   130: aastore
      //   131: dup
      //   132: bipush 6
      //   134: aload 4
      //   136: aastore
      //   137: invokevirtual 152	gnu/mapping/Procedure:applyN	([Ljava/lang/Object;)Ljava/lang/Object;
      //   140: areturn
      //   141: iload 9
      //   143: ifne -59 -> 84
      //   146: invokestatic 156	gnu/kawa/slib/printf$frame:lambda1parseError	()Ljava/lang/Boolean;
      //   149: areturn
      //   150: astore_1
      //   151: new 158	gnu/mapping/WrongType
      //   154: dup
      //   155: aload_1
      //   156: ldc -96
      //   158: bipush -2
      //   160: aload 5
      //   162: invokespecial 163	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   165: athrow
      //   166: astore_1
      //   167: new 158	gnu/mapping/WrongType
      //   170: dup
      //   171: aload_1
      //   172: ldc -91
      //   174: iconst_1
      //   175: aload 5
      //   177: invokespecial 163	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   180: athrow
      //   181: astore_2
      //   182: new 158	gnu/mapping/WrongType
      //   185: dup
      //   186: aload_2
      //   187: ldc -91
      //   189: iconst_2
      //   190: aload_1
      //   191: invokespecial 163	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   194: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	195	0	this	frame0
      //   0	195	1	paramObject1	Object
      //   0	195	2	paramObject2	Object
      //   0	195	3	paramObject3	Object
      //   0	195	4	paramObject4	Object
      //   19	157	5	localObject	Object
      //   39	28	6	localChar	Char
      //   55	14	7	localCharSequence	CharSequence
      //   64	7	8	i	int
      //   29	113	9	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   21	31	150	java/lang/ClassCastException
      //   50	57	166	java/lang/ClassCastException
      //   57	66	181	java/lang/ClassCastException
    }
    
    Object lambda7(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      printf.frame1 localframe1 = new printf.frame1();
      localframe1.staticLink = this;
      localframe1.sgn = paramObject1;
      localframe1.digs = paramObject2;
      localframe1.ex = paramObject3;
      paramObject1 = this.num;
      try
      {
        paramObject2 = (Complex)paramObject1;
        return printf.stdio$ClParseFloat(numbers.number$To$String(numbers.imagPart((Complex)paramObject2)), localframe1.lambda$Fn4);
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "imag-part", 1, paramObject1);
      }
    }
    
    public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 3)
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
    
    public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 2)
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
  }
  
  public class frame1
    extends ModuleBody
  {
    Object digs;
    Object ex;
    final ModuleMethod lambda$Fn4;
    Object sgn;
    printf.frame0 staticLink;
    
    public frame1()
    {
      this$1 = new ModuleMethod(this, 1, null, 12291);
      this$1.setProperty("source-location", "printf.scm:126");
      this.lambda$Fn4 = this$1;
    }
    
    public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
    {
      if (paramModuleMethod.selector == 1) {
        return lambda8(paramObject1, paramObject2, paramObject3);
      }
      return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
    }
    
    Object lambda8(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.proc, this.sgn, this.digs, this.ex, paramObject1, paramObject2, paramObject3 });
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
  }
  
  public class frame10
    extends ModuleBody
  {
    Object alternate$Mnform;
    Object args;
    Object blank;
    final ModuleMethod lambda$Fn13;
    final ModuleMethod lambda$Fn14;
    final ModuleMethod lambda$Fn15;
    final ModuleMethod lambda$Fn16;
    Object leading$Mn0s;
    Object left$Mnadjust;
    Object os;
    Procedure pad = new ModuleMethod(this, 15, printf.Lit67, 61441);
    Object pr;
    Object precision;
    Object signed;
    printf.frame9 staticLink;
    Object width;
    
    public frame10()
    {
      this$1 = new ModuleMethod(this, 16, null, 4097);
      this$1.setProperty("source-location", "printf.scm:472");
      this.lambda$Fn13 = this$1;
      this$1 = new ModuleMethod(this, 17, null, 4097);
      this$1.setProperty("source-location", "printf.scm:476");
      this.lambda$Fn14 = this$1;
      this$1 = new ModuleMethod(this, 18, null, 4097);
      this$1.setProperty("source-location", "printf.scm:484");
      this.lambda$Fn15 = this$1;
      this$1 = new ModuleMethod(this, 19, null, 4097);
      this$1.setProperty("source-location", "printf.scm:494");
      this.lambda$Fn16 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.apply1(paramModuleMethod, paramObject);
      case 16: 
        return lambda25(paramObject);
      case 17: 
        if (lambda26(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      case 18: 
        return lambda27(paramObject);
      }
      if (lambda28(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    
    public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
    {
      if (paramModuleMethod.selector == 15)
      {
        paramModuleMethod = paramArrayOfObject[0];
        int i = paramArrayOfObject.length - 1;
        Object[] arrayOfObject = new Object[i];
        for (;;)
        {
          i -= 1;
          if (i < 0) {
            return lambda23pad$V(paramModuleMethod, arrayOfObject);
          }
          arrayOfObject[i] = paramArrayOfObject[(i + 1)];
        }
      }
      return super.applyN(paramModuleMethod, paramArrayOfObject);
    }
    
    public Object lambda22readFormatNumber()
    {
      if (Scheme.isEqv.apply2(printf.Lit66, this.staticLink.fc) != Boolean.FALSE)
      {
        this.staticLink.lambda18mustAdvance();
        localObject1 = lists.car.apply1(this.args);
        this.args = lists.cdr.apply1(this.args);
        return localObject1;
      }
      Object localObject1 = this.staticLink.fc;
      Object localObject2 = printf.Lit1;
      for (;;)
      {
        Object localObject3 = this.staticLink.fc;
        try
        {
          Object localObject4 = (Char)localObject3;
          if (unicode.isCharNumeric((Char)localObject4))
          {
            this.staticLink.lambda18mustAdvance();
            localObject3 = this.staticLink.fc;
            localObject4 = AddOp.$Pl;
            localObject2 = MultiplyOp.$St.apply2(localObject2, printf.Lit45);
            if ((localObject1 instanceof Object[])) {}
            for (localObject1 = (Object[])localObject1;; localObject1 = new Object[] { localObject1 })
            {
              localObject2 = ((Procedure)localObject4).apply2(localObject2, numbers.string$To$Number(strings.$make$string$((Object[])localObject1)));
              localObject1 = localObject3;
              break;
            }
          }
          return localObject2;
        }
        catch (ClassCastException localClassCastException)
        {
          throw new WrongType(localClassCastException, "char-numeric?", 1, localObject3);
        }
      }
    }
    
    public Object lambda23pad$V(Object paramObject, Object[] paramArrayOfObject)
    {
      LList localLList = LList.makeList(paramArrayOfObject, 0);
      try
      {
        paramArrayOfObject = (CharSequence)paramObject;
        localObject1 = Integer.valueOf(strings.stringLength(paramArrayOfObject));
        paramArrayOfObject = localLList;
      }
      catch (ClassCastException paramArrayOfObject)
      {
        try
        {
          localCharSequence = (CharSequence)localObject2;
          Object localObject1 = localAddOp.apply2(localObject1, Integer.valueOf(strings.stringLength(localCharSequence)));
          paramArrayOfObject = lists.cdr.apply1(paramArrayOfObject);
        }
        catch (ClassCastException paramObject)
        {
          Object localObject2;
          throw new WrongType((ClassCastException)paramObject, "string-length", 1, localObject2);
        }
        paramArrayOfObject = paramArrayOfObject;
        throw new WrongType(paramArrayOfObject, "string-length", 1, paramObject);
      }
      if (Scheme.numGEq.apply2(localObject1, this.width) != Boolean.FALSE) {
        return lists.cons(paramObject, localLList);
      }
      if (lists.isNull(paramArrayOfObject)) {
        if (this.left$Mnadjust != Boolean.FALSE) {
          paramArrayOfObject = AddOp.$Mn.apply2(this.width, localObject1);
        }
      }
      try
      {
        i = ((Number)paramArrayOfObject).intValue();
        return lists.cons(paramObject, append.append$V(new Object[] { localLList, LList.list1(strings.makeString(i, printf.Lit29)) }));
      }
      catch (ClassCastException paramObject)
      {
        int i;
        throw new WrongType((ClassCastException)paramObject, "make-string", 1, paramArrayOfObject);
      }
      if (this.leading$Mn0s != Boolean.FALSE) {
        paramArrayOfObject = AddOp.$Mn.apply2(this.width, localObject1);
      }
      try
      {
        i = ((Number)paramArrayOfObject).intValue();
        return lists.cons(paramObject, lists.cons(strings.makeString(i, printf.Lit9), localLList));
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "make-string", 1, paramArrayOfObject);
      }
      paramArrayOfObject = AddOp.$Mn.apply2(this.width, localObject1);
      try
      {
        i = ((Number)paramArrayOfObject).intValue();
        return lists.cons(strings.makeString(i, printf.Lit29), lists.cons(paramObject, localLList));
      }
      catch (ClassCastException paramObject)
      {
        AddOp localAddOp;
        CharSequence localCharSequence;
        throw new WrongType((ClassCastException)paramObject, "make-string", 1, paramArrayOfObject);
      }
      localAddOp = AddOp.$Pl;
      localObject2 = lists.car.apply1(paramArrayOfObject);
    }
    
    /* Error */
    public Object lambda24integerConvert(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 272	gnu/kawa/slib/printf$frame10:precision	Ljava/lang/Object;
      //   4: astore 4
      //   6: aload 4
      //   8: invokestatic 278	gnu/kawa/lispexpr/LangObjType:coerceRealNum	(Ljava/lang/Object;)Lgnu/math/RealNum;
      //   11: astore 5
      //   13: aload 5
      //   15: invokestatic 282	kawa/lib/numbers:isNegative	(Lgnu/math/RealNum;)Z
      //   18: ifne +691 -> 709
      //   21: aload_0
      //   22: getstatic 92	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   25: putfield 261	gnu/kawa/slib/printf$frame10:leading$Mn0s	Ljava/lang/Object;
      //   28: aload_0
      //   29: getfield 272	gnu/kawa/slib/printf$frame10:precision	Ljava/lang/Object;
      //   32: astore 4
      //   34: aload 4
      //   36: checkcast 238	java/lang/Number
      //   39: astore 5
      //   41: aload 5
      //   43: invokestatic 286	kawa/lib/numbers:isZero	(Ljava/lang/Number;)Z
      //   46: istore 7
      //   48: iload 7
      //   50: ifeq +182 -> 232
      //   53: aload_1
      //   54: astore 4
      //   56: getstatic 119	kawa/standard/Scheme:isEqv	Lgnu/kawa/functions/IsEqv;
      //   59: getstatic 156	gnu/kawa/slib/printf:Lit1	Lgnu/math/IntNum;
      //   62: aload_1
      //   63: invokevirtual 136	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   66: getstatic 92	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   69: if_acmpeq +8 -> 77
      //   72: ldc_w 288
      //   75: astore 4
      //   77: aload 4
      //   79: astore_1
      //   80: aload_1
      //   81: invokestatic 293	kawa/lib/misc:isSymbol	(Ljava/lang/Object;)Z
      //   84: ifeq +159 -> 243
      //   87: aload_1
      //   88: checkcast 295	gnu/mapping/Symbol
      //   91: astore 4
      //   93: aload 4
      //   95: invokestatic 299	kawa/lib/misc:symbol$To$String	(Lgnu/mapping/Symbol;)Ljava/lang/String;
      //   98: astore_1
      //   99: aload_1
      //   100: astore 4
      //   102: aload_3
      //   103: getstatic 92	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   106: if_acmpeq +13 -> 119
      //   109: getstatic 303	kawa/standard/Scheme:applyToArgs	Lgnu/kawa/functions/ApplyToArgs;
      //   112: aload_3
      //   113: aload_1
      //   114: invokevirtual 136	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   117: astore 4
      //   119: ldc_w 288
      //   122: aload 4
      //   124: invokestatic 309	gnu/kawa/functions/IsEqual:apply	(Ljava/lang/Object;Ljava/lang/Object;)Z
      //   127: ifeq +218 -> 345
      //   130: ldc_w 288
      //   133: astore_1
      //   134: getstatic 312	kawa/standard/Scheme:numLss	Lgnu/kawa/functions/NumberCompare;
      //   137: astore_2
      //   138: aload 4
      //   140: checkcast 208	java/lang/CharSequence
      //   143: astore_3
      //   144: aload_2
      //   145: aload_3
      //   146: invokestatic 212	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
      //   149: invokestatic 218	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   152: aload_0
      //   153: getfield 272	gnu/kawa/slib/printf$frame10:precision	Ljava/lang/Object;
      //   156: invokevirtual 136	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   159: getstatic 92	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   162: if_acmpeq +352 -> 514
      //   165: getstatic 236	gnu/kawa/functions/AddOp:$Mn	Lgnu/kawa/functions/AddOp;
      //   168: astore_2
      //   169: aload_0
      //   170: getfield 272	gnu/kawa/slib/printf$frame10:precision	Ljava/lang/Object;
      //   173: astore_3
      //   174: aload 4
      //   176: checkcast 208	java/lang/CharSequence
      //   179: astore 5
      //   181: aload_2
      //   182: aload_3
      //   183: aload 5
      //   185: invokestatic 212	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
      //   188: invokestatic 218	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   191: invokevirtual 136	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   194: astore_2
      //   195: aload_2
      //   196: checkcast 238	java/lang/Number
      //   199: invokevirtual 242	java/lang/Number:intValue	()I
      //   202: istore 6
      //   204: iload 6
      //   206: getstatic 264	gnu/kawa/slib/printf:Lit9	Lgnu/text/Char;
      //   209: invokestatic 249	kawa/lib/strings:makeString	(ILjava/lang/Object;)Ljava/lang/CharSequence;
      //   212: astore_2
      //   213: aload_0
      //   214: aload_1
      //   215: iconst_2
      //   216: anewarray 103	java/lang/Object
      //   219: dup
      //   220: iconst_0
      //   221: aload_2
      //   222: aastore
      //   223: dup
      //   224: iconst_1
      //   225: aload 4
      //   227: aastore
      //   228: invokevirtual 107	gnu/kawa/slib/printf$frame10:lambda23pad$V	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   231: areturn
      //   232: aload_1
      //   233: astore 4
      //   235: iload 7
      //   237: ifeq -160 -> 77
      //   240: goto -168 -> 72
      //   243: aload_1
      //   244: invokestatic 315	kawa/lib/numbers:isNumber	(Ljava/lang/Object;)Z
      //   247: ifeq +29 -> 276
      //   250: aload_1
      //   251: checkcast 238	java/lang/Number
      //   254: astore 4
      //   256: aload_2
      //   257: checkcast 238	java/lang/Number
      //   260: invokevirtual 242	java/lang/Number:intValue	()I
      //   263: istore 6
      //   265: aload 4
      //   267: iload 6
      //   269: invokestatic 319	kawa/lib/numbers:number$To$String	(Ljava/lang/Number;I)Ljava/lang/CharSequence;
      //   272: astore_1
      //   273: goto -174 -> 99
      //   276: getstatic 92	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   279: astore 4
      //   281: aload_1
      //   282: aload 4
      //   284: if_acmpeq +31 -> 315
      //   287: iconst_1
      //   288: istore 6
      //   290: iload 6
      //   292: iconst_1
      //   293: iadd
      //   294: iconst_1
      //   295: iand
      //   296: istore 6
      //   298: iload 6
      //   300: ifeq +21 -> 321
      //   303: iload 6
      //   305: ifeq +23 -> 328
      //   308: ldc_w 321
      //   311: astore_1
      //   312: goto -213 -> 99
      //   315: iconst_0
      //   316: istore 6
      //   318: goto -28 -> 290
      //   321: aload_1
      //   322: invokestatic 231	kawa/lib/lists:isNull	(Ljava/lang/Object;)Z
      //   325: ifne -17 -> 308
      //   328: aload_1
      //   329: invokestatic 324	kawa/lib/strings:isString	(Ljava/lang/Object;)Z
      //   332: ifeq +6 -> 338
      //   335: goto -236 -> 99
      //   338: ldc_w 326
      //   341: astore_1
      //   342: goto -243 -> 99
      //   345: getstatic 119	kawa/standard/Scheme:isEqv	Lgnu/kawa/functions/IsEqv;
      //   348: astore_1
      //   349: getstatic 329	gnu/kawa/slib/printf:Lit5	Lgnu/text/Char;
      //   352: astore_3
      //   353: aload 4
      //   355: checkcast 208	java/lang/CharSequence
      //   358: astore 5
      //   360: aload_1
      //   361: aload_3
      //   362: aload 5
      //   364: iconst_0
      //   365: invokestatic 333	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
      //   368: invokestatic 337	gnu/text/Char:make	(I)Lgnu/text/Char;
      //   371: invokevirtual 136	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   374: getstatic 92	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   377: if_acmpeq +33 -> 410
      //   380: aload 4
      //   382: checkcast 208	java/lang/CharSequence
      //   385: astore_1
      //   386: aload 4
      //   388: checkcast 208	java/lang/CharSequence
      //   391: astore_2
      //   392: aload_1
      //   393: iconst_1
      //   394: aload_2
      //   395: invokestatic 212	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
      //   398: invokestatic 341	kawa/lib/strings:substring	(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
      //   401: astore 4
      //   403: ldc_w 343
      //   406: astore_1
      //   407: goto -273 -> 134
      //   410: aload_0
      //   411: getfield 345	gnu/kawa/slib/printf$frame10:signed	Ljava/lang/Object;
      //   414: getstatic 92	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   417: if_acmpeq +10 -> 427
      //   420: ldc_w 347
      //   423: astore_1
      //   424: goto -290 -> 134
      //   427: aload_0
      //   428: getfield 349	gnu/kawa/slib/printf$frame10:blank	Ljava/lang/Object;
      //   431: getstatic 92	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   434: if_acmpeq +10 -> 444
      //   437: ldc_w 351
      //   440: astore_1
      //   441: goto -307 -> 134
      //   444: aload_0
      //   445: getfield 353	gnu/kawa/slib/printf$frame10:alternate$Mnform	Ljava/lang/Object;
      //   448: getstatic 92	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   451: if_acmpeq +56 -> 507
      //   454: getstatic 119	kawa/standard/Scheme:isEqv	Lgnu/kawa/functions/IsEqv;
      //   457: aload_2
      //   458: getstatic 356	gnu/kawa/slib/printf:Lit48	Lgnu/math/IntNum;
      //   461: invokevirtual 136	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   464: getstatic 92	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   467: if_acmpeq +10 -> 477
      //   470: ldc_w 321
      //   473: astore_1
      //   474: goto -340 -> 134
      //   477: getstatic 119	kawa/standard/Scheme:isEqv	Lgnu/kawa/functions/IsEqv;
      //   480: aload_2
      //   481: getstatic 359	gnu/kawa/slib/printf:Lit50	Lgnu/math/IntNum;
      //   484: invokevirtual 136	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   487: getstatic 92	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   490: if_acmpeq +10 -> 500
      //   493: ldc_w 361
      //   496: astore_1
      //   497: goto -23 -> 474
      //   500: ldc_w 288
      //   503: astore_1
      //   504: goto -30 -> 474
      //   507: ldc_w 288
      //   510: astore_1
      //   511: goto -377 -> 134
      //   514: ldc_w 288
      //   517: astore_2
      //   518: goto -305 -> 213
      //   521: astore_1
      //   522: new 195	gnu/mapping/WrongType
      //   525: dup
      //   526: aload_1
      //   527: ldc_w 363
      //   530: iconst_1
      //   531: aload 4
      //   533: invokespecial 200	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   536: athrow
      //   537: astore_1
      //   538: new 195	gnu/mapping/WrongType
      //   541: dup
      //   542: aload_1
      //   543: ldc_w 365
      //   546: iconst_1
      //   547: aload 4
      //   549: invokespecial 200	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   552: athrow
      //   553: astore_2
      //   554: new 195	gnu/mapping/WrongType
      //   557: dup
      //   558: aload_2
      //   559: ldc_w 367
      //   562: iconst_1
      //   563: aload_1
      //   564: invokespecial 200	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   567: athrow
      //   568: astore_2
      //   569: new 195	gnu/mapping/WrongType
      //   572: dup
      //   573: aload_2
      //   574: ldc_w 369
      //   577: iconst_1
      //   578: aload_1
      //   579: invokespecial 200	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   582: athrow
      //   583: astore_1
      //   584: new 195	gnu/mapping/WrongType
      //   587: dup
      //   588: aload_1
      //   589: ldc_w 369
      //   592: iconst_2
      //   593: aload_2
      //   594: invokespecial 200	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   597: athrow
      //   598: astore_2
      //   599: new 195	gnu/mapping/WrongType
      //   602: dup
      //   603: aload_2
      //   604: ldc_w 371
      //   607: bipush -2
      //   609: aload_1
      //   610: invokespecial 200	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   613: athrow
      //   614: astore_1
      //   615: new 195	gnu/mapping/WrongType
      //   618: dup
      //   619: aload_1
      //   620: ldc_w 373
      //   623: iconst_1
      //   624: aload 4
      //   626: invokespecial 200	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   629: athrow
      //   630: astore_1
      //   631: new 195	gnu/mapping/WrongType
      //   634: dup
      //   635: aload_1
      //   636: ldc_w 374
      //   639: iconst_1
      //   640: aload 4
      //   642: invokespecial 200	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   645: athrow
      //   646: astore_1
      //   647: new 195	gnu/mapping/WrongType
      //   650: dup
      //   651: aload_1
      //   652: ldc_w 266
      //   655: iconst_1
      //   656: aload 4
      //   658: invokespecial 200	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   661: athrow
      //   662: astore_1
      //   663: new 195	gnu/mapping/WrongType
      //   666: dup
      //   667: aload_1
      //   668: ldc_w 266
      //   671: iconst_1
      //   672: aload 4
      //   674: invokespecial 200	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   677: athrow
      //   678: astore_1
      //   679: new 195	gnu/mapping/WrongType
      //   682: dup
      //   683: aload_1
      //   684: ldc_w 266
      //   687: iconst_1
      //   688: aload 4
      //   690: invokespecial 200	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   693: athrow
      //   694: astore_1
      //   695: new 195	gnu/mapping/WrongType
      //   698: dup
      //   699: aload_1
      //   700: ldc_w 268
      //   703: iconst_1
      //   704: aload_2
      //   705: invokespecial 200	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   708: athrow
      //   709: goto -629 -> 80
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	712	0	this	frame10
      //   0	712	1	paramObject1	Object
      //   0	712	2	paramObject2	Object
      //   0	712	3	paramObject3	Object
      //   4	685	4	localObject1	Object
      //   11	352	5	localObject2	Object
      //   202	115	6	i	int
      //   46	190	7	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   6	13	521	java/lang/ClassCastException
      //   34	41	537	java/lang/ClassCastException
      //   87	93	553	java/lang/ClassCastException
      //   250	256	568	java/lang/ClassCastException
      //   256	265	583	java/lang/ClassCastException
      //   276	281	598	java/lang/ClassCastException
      //   353	360	614	java/lang/ClassCastException
      //   380	386	630	java/lang/ClassCastException
      //   386	392	646	java/lang/ClassCastException
      //   138	144	662	java/lang/ClassCastException
      //   174	181	678	java/lang/ClassCastException
      //   195	204	694	java/lang/ClassCastException
    }
    
    Object lambda25(Object paramObject)
    {
      AddOp localAddOp = AddOp.$Pl;
      Object localObject = this.pr;
      try
      {
        CharSequence localCharSequence = (CharSequence)paramObject;
        this.pr = localAddOp.apply2(localObject, Integer.valueOf(strings.stringLength(localCharSequence)));
        return Scheme.applyToArgs.apply2(this.staticLink.out, paramObject);
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "string-length", 1, paramObject);
      }
    }
    
    boolean lambda26(Object paramObject)
    {
      Object localObject1 = Special.undefined;
      localObject1 = AddOp.$Mn;
      Object localObject2 = this.pr;
      for (;;)
      {
        try
        {
          localObject3 = (CharSequence)paramObject;
          localObject1 = ((Procedure)localObject1).apply2(localObject2, Integer.valueOf(strings.stringLength((CharSequence)localObject3)));
        }
        catch (ClassCastException localClassCastException1)
        {
          Object localObject3;
          CharSequence localCharSequence;
          int i;
          throw new WrongType(localClassCastException1, "string-length", 1, paramObject);
        }
        try
        {
          localObject2 = LangObjType.coerceRealNum(localObject1);
          if (numbers.isNegative((RealNum)localObject2))
          {
            localObject2 = Scheme.applyToArgs;
            localObject3 = this.staticLink.out;
          }
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "negative?", 1, localClassCastException1);
        }
        try
        {
          localCharSequence = (CharSequence)paramObject;
          paramObject = this.pr;
        }
        catch (ClassCastException localClassCastException2)
        {
          throw new WrongType(localClassCastException2, "substring", 1, paramObject);
        }
        try
        {
          i = ((Number)paramObject).intValue();
          ((Procedure)localObject2).apply2(localObject3, strings.substring(localCharSequence, 0, i));
          paramObject = printf.Lit1;
          this.pr = paramObject;
        }
        catch (ClassCastException localClassCastException3)
        {
          throw new WrongType(localClassCastException3, "substring", 3, paramObject);
        }
        try
        {
          paramObject = LangObjType.coerceRealNum(localObject1);
          return numbers.isPositive((RealNum)paramObject);
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "positive?", 1, localClassCastException3);
        }
        Scheme.applyToArgs.apply2(this.staticLink.out, paramObject);
        paramObject = localObject1;
      }
    }
    
    Boolean lambda27(Object paramObject)
    {
      localObject1 = AddOp.$Mn;
      localObject2 = this.pr;
      try
      {
        CharSequence localCharSequence = (CharSequence)paramObject;
        this.pr = ((Procedure)localObject1).apply2(localObject2, Integer.valueOf(strings.stringLength(localCharSequence)));
        if (this.os != Boolean.FALSE) {
          break label61;
        }
        Scheme.applyToArgs.apply2(this.staticLink.out, paramObject);
      }
      catch (ClassCastException localClassCastException)
      {
        try
        {
          for (;;)
          {
            label61:
            localObject2 = LangObjType.coerceRealNum(localObject1);
            if (!numbers.isNegative((RealNum)localObject2)) {
              break;
            }
            Scheme.applyToArgs.apply2(this.staticLink.out, this.os);
            this.os = Boolean.FALSE;
            Scheme.applyToArgs.apply2(this.staticLink.out, paramObject);
          }
          this.os = strings.stringAppend(new Object[] { this.os, paramObject });
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "negative?", 1, localClassCastException);
        }
        localClassCastException = localClassCastException;
        throw new WrongType(localClassCastException, "string-length", 1, paramObject);
      }
      return Boolean.TRUE;
      localObject1 = this.pr;
    }
    
    boolean lambda28(Object paramObject)
    {
      Object localObject1 = Special.undefined;
      localObject1 = AddOp.$Mn;
      Object localObject2 = this.pr;
      for (;;)
      {
        try
        {
          localCharSequence = (CharSequence)paramObject;
          localObject1 = ((Procedure)localObject1).apply2(localObject2, Integer.valueOf(strings.stringLength(localCharSequence)));
        }
        catch (ClassCastException localClassCastException1)
        {
          CharSequence localCharSequence;
          int i;
          throw new WrongType(localClassCastException1, "string-length", 1, paramObject);
        }
        try
        {
          localObject2 = LangObjType.coerceRealNum(localObject1);
          if (numbers.isNegative((RealNum)localObject2)) {
            localObject2 = this.os;
          }
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "negative?", 1, localClassCastException1);
        }
        try
        {
          localCharSequence = (CharSequence)paramObject;
          paramObject = this.pr;
        }
        catch (ClassCastException localClassCastException2)
        {
          throw new WrongType(localClassCastException2, "substring", 1, paramObject);
        }
        try
        {
          i = ((Number)paramObject).intValue();
          this.os = strings.stringAppend(new Object[] { localObject2, strings.substring(localCharSequence, 0, i) });
          this.pr = localObject1;
        }
        catch (ClassCastException localClassCastException3)
        {
          throw new WrongType(localClassCastException3, "substring", 3, paramObject);
        }
        try
        {
          paramObject = LangObjType.coerceRealNum(localObject1);
          return numbers.isPositive((RealNum)paramObject);
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "positive?", 1, localClassCastException3);
        }
        this.os = strings.stringAppend(new Object[] { this.os, paramObject });
      }
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 19: 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 18: 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 17: 
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
    
    public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 15)
      {
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      }
      return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
    }
  }
  
  public class frame11
    extends ModuleBody
  {
    Object fc;
    Procedure format$Mnreal = new ModuleMethod(this, 13, printf.Lit64, 61444);
    final ModuleMethod lambda$Fn17;
    printf.frame10 staticLink;
    
    public frame11()
    {
      this$1 = new ModuleMethod(this, 14, null, 61443);
      this$1.setProperty("source-location", "printf.scm:401");
      this.lambda$Fn17 = this$1;
    }
    
    public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 13: 
        paramModuleMethod = paramArrayOfObject[0];
        localObject1 = paramArrayOfObject[1];
        localObject2 = paramArrayOfObject[2];
        localObject3 = paramArrayOfObject[3];
        i = paramArrayOfObject.length - 4;
        Object[] arrayOfObject = new Object[i];
        for (;;)
        {
          i -= 1;
          if (i < 0) {
            return lambda30formatReal$V(paramModuleMethod, localObject1, localObject2, localObject3, arrayOfObject);
          }
          arrayOfObject[i] = paramArrayOfObject[(i + 4)];
        }
      }
      paramModuleMethod = paramArrayOfObject[0];
      Object localObject1 = paramArrayOfObject[1];
      Object localObject2 = paramArrayOfObject[2];
      int i = paramArrayOfObject.length - 3;
      Object localObject3 = new Object[i];
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          return lambda31$V(paramModuleMethod, localObject1, localObject2, (Object[])localObject3);
        }
        localObject3[i] = paramArrayOfObject[(i + 3)];
      }
    }
    
    public Object lambda29f(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      for (;;)
      {
        try
        {
          localObject1 = (CharSequence)paramObject1;
          localObject2 = AddOp.$Pl.apply2(paramObject2, this.staticLink.precision);
          if (paramObject3 != Boolean.FALSE)
          {
            paramObject1 = paramObject2;
            localObject1 = printf.stdio$ClRoundString((CharSequence)localObject1, localObject2, paramObject1);
            if (Scheme.numGEq.apply2(paramObject2, printf.Lit1) == Boolean.FALSE) {
              continue;
            }
          }
        }
        catch (ClassCastException paramObject2)
        {
          Object localObject2;
          int i;
          int j;
          boolean bool;
          throw new WrongType((ClassCastException)paramObject2, "stdio:round-string", 0, paramObject1);
        }
        try
        {
          paramObject1 = (Number)paramObject2;
          if (numbers.isZero((Number)paramObject1))
          {
            paramObject1 = printf.Lit1;
            paramObject3 = numbers.max(new Object[] { printf.Lit7, AddOp.$Pl.apply2(printf.Lit7, paramObject2) });
          }
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "zero?", 1, paramObject2);
        }
        try
        {
          paramObject2 = (CharSequence)localObject1;
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 1, localObject1);
        }
        try
        {
          i = ((Number)paramObject1).intValue();
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "substring", 2, paramObject1);
        }
        try
        {
          j = ((Number)paramObject3).intValue();
          paramObject2 = strings.substring((CharSequence)paramObject2, i, j);
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 3, paramObject3);
        }
        try
        {
          paramObject1 = (CharSequence)localObject1;
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 1, localObject1);
        }
        try
        {
          i = ((Number)paramObject3).intValue();
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 2, paramObject3);
        }
        try
        {
          paramObject3 = (CharSequence)localObject1;
          paramObject1 = strings.substring((CharSequence)paramObject1, i, strings.stringLength((CharSequence)paramObject3));
          bool = strings.isString$Eq(paramObject1, "");
          if (bool)
          {
            if (this.staticLink.alternate$Mnform != Boolean.FALSE) {
              continue;
            }
            paramObject1 = LList.Empty;
            paramObject3 = lists.cons(paramObject2, paramObject1);
            return paramObject3;
            paramObject1 = paramObject3;
            continue;
            paramObject1 = printf.Lit9;
          }
          try
          {
            paramObject3 = (CharSequence)localObject1;
            if (characters.isChar$Eq((Char)paramObject1, Char.make(strings.stringRef((CharSequence)paramObject3, 0))))
            {
              paramObject1 = printf.Lit7;
              continue;
            }
            paramObject1 = printf.Lit1;
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, localObject1);
          }
          if (bool) {
            continue;
          }
          paramObject1 = LList.list2(".", paramObject1);
          continue;
          paramObject1 = this.staticLink.precision;
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-length", 1, localObject1);
        }
        try
        {
          localObject2 = (Number)paramObject1;
          if (numbers.isZero((Number)localObject2))
          {
            if (this.staticLink.alternate$Mnform != Boolean.FALSE)
            {
              paramObject1 = "0.";
              return LList.list1(paramObject1);
            }
            paramObject1 = "0";
            continue;
          }
          if (paramObject3 != Boolean.FALSE)
          {
            bool = strings.isString$Eq(localObject1, "");
            if (bool)
            {
              paramObject1 = LList.list1("0");
              paramObject3 = paramObject1;
              if (paramObject1 != Boolean.FALSE) {
                continue;
              }
              paramObject1 = numbers.min(new Object[] { this.staticLink.precision, AddOp.$Mn.apply2(printf.Lit17, paramObject2) });
            }
          }
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "zero?", 1, paramObject1);
        }
        try
        {
          i = ((Number)paramObject1).intValue();
          return LList.list3("0.", strings.makeString(i, printf.Lit9), localObject1);
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "make-string", 1, paramObject1);
        }
        if (bool)
        {
          paramObject1 = Boolean.TRUE;
        }
        else
        {
          paramObject1 = Boolean.FALSE;
          continue;
          paramObject1 = paramObject3;
        }
      }
    }
    
    public Object lambda30formatReal$V(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object[] paramArrayOfObject)
    {
      paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
      if (lists.isNull(paramArrayOfObject))
      {
        paramArrayOfObject = printf.Lit5;
        for (;;)
        {
          try
          {
            localObject = (Char)paramObject2;
            if (characters.isChar$Eq(paramArrayOfObject, (Char)localObject))
            {
              paramObject2 = "-";
              paramObject1 = Scheme.isEqv.apply2(this.fc, printf.Lit13);
              if (paramObject1 == Boolean.FALSE) {
                continue;
              }
              if (paramObject1 == Boolean.FALSE) {
                continue;
              }
              paramObject1 = Boolean.FALSE;
            }
          }
          catch (ClassCastException paramObject1)
          {
            int i;
            CharSequence localCharSequence;
            boolean bool;
            throw new WrongType((ClassCastException)paramObject1, "char=?", 2, paramObject2);
          }
          try
          {
            paramArrayOfObject = (CharSequence)paramObject3;
            localObject = AddOp.$Pl.apply2(printf.Lit7, this.staticLink.precision);
            paramObject3 = paramObject1;
            if (paramObject1 != Boolean.FALSE) {
              paramObject3 = printf.Lit1;
            }
            paramObject3 = printf.stdio$ClRoundString(paramArrayOfObject, localObject, paramObject3);
            paramObject1 = printf.Lit9;
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "stdio:round-string", 0, paramObject3);
          }
          try
          {
            paramArrayOfObject = (CharSequence)paramObject3;
            if (characters.isChar$Eq((Char)paramObject1, Char.make(strings.stringRef(paramArrayOfObject, 0)))) {
              paramObject1 = printf.Lit7;
            }
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, paramObject3);
          }
          try
          {
            paramArrayOfObject = (CharSequence)paramObject3;
            i = ((Number)paramObject1).intValue();
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "substring", 1, paramObject3);
          }
          try
          {
            localObject = (CharSequence)paramObject3;
            localCharSequence = strings.substring(paramArrayOfObject, i + 1, strings.stringLength((CharSequence)localObject));
            if (!numbers.isZero((Number)paramObject1)) {}
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "string-length", 1, paramObject3);
          }
          try
          {
            paramArrayOfObject = (CharSequence)paramObject3;
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "substring", 1, paramObject3);
          }
          try
          {
            i = ((Number)paramObject1).intValue();
            localObject = LList.list1(strings.substring(paramArrayOfObject, i, ((Number)paramObject1).intValue() + 1));
            bool = strings.isString$Eq(localCharSequence, "");
            if (bool)
            {
              if (this.staticLink.alternate$Mnform != Boolean.FALSE) {
                continue;
              }
              paramObject1 = "";
              paramObject3 = this.fc;
            }
          }
          catch (ClassCastException paramObject2)
          {
            throw new WrongType((ClassCastException)paramObject2, "substring", 2, paramObject1);
          }
          try
          {
            paramArrayOfObject = (Char)paramObject3;
            if (unicode.isCharUpperCase(paramArrayOfObject)) {
              paramObject3 = "E";
            }
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "char-upper-case?", 1, paramObject3);
          }
          try
          {
            paramArrayOfObject = LangObjType.coerceRealNum(paramObject4);
            if (numbers.isNegative(paramArrayOfObject))
            {
              paramArrayOfObject = "-";
              paramObject3 = LList.chain4((Pair)localObject, paramObject1, localCharSequence, paramObject3, paramArrayOfObject);
              if (Scheme.numLss.apply3(printf.Lit60, paramObject4, printf.Lit45) == Boolean.FALSE) {
                continue;
              }
              paramObject1 = "0";
              paramObject1 = LList.chain1((Pair)paramObject3, paramObject1);
            }
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "negative?", 1, paramObject4);
          }
          try
          {
            paramObject3 = (Number)paramObject4;
            LList.chain1((Pair)paramObject1, numbers.number$To$String(numbers.abs((Number)paramObject3)));
            paramObject1 = localObject;
            return lists.cons(paramObject2, paramObject1);
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "abs", 1, paramObject4);
          }
          if (paramObject1 != Boolean.FALSE)
          {
            paramObject2 = "+";
          }
          else if (this.staticLink.blank != Boolean.FALSE)
          {
            paramObject2 = " ";
          }
          else
          {
            paramObject2 = "";
            continue;
            if (Scheme.isEqv.apply2(this.fc, printf.Lit54) == Boolean.FALSE)
            {
              paramObject1 = Scheme.isEqv.apply2(this.fc, printf.Lit25);
              if (paramObject1 != Boolean.FALSE)
              {
                if (paramObject1 != Boolean.FALSE)
                {
                  paramObject1 = lambda29f(paramObject3, paramObject4, Boolean.FALSE);
                  continue;
                  paramObject1 = printf.Lit1;
                  continue;
                  paramObject4 = AddOp.$Mn.apply2(paramObject4, printf.Lit7);
                  continue;
                  if (bool) {
                    continue;
                  }
                  paramObject1 = ".";
                  continue;
                  paramObject3 = "e";
                  continue;
                  paramArrayOfObject = "+";
                  continue;
                  paramObject1 = "";
                  continue;
                }
              }
              else {
                if (Scheme.isEqv.apply2(this.fc, printf.Lit26) != Boolean.FALSE) {
                  continue;
                }
              }
              paramObject1 = Scheme.isEqv.apply2(this.fc, printf.Lit55);
              if (paramObject1 != Boolean.FALSE)
              {
                if (paramObject1 == Boolean.FALSE) {
                  continue;
                }
                paramObject1 = this.staticLink.alternate$Mnform;
              }
            }
          }
          try
          {
            paramArrayOfObject = Boolean.FALSE;
            if (paramObject1 != paramArrayOfObject)
            {
              i = 1;
              i = i + 1 & 0x1;
              this.staticLink.alternate$Mnform = Boolean.FALSE;
              if (Scheme.numLEq.apply3(AddOp.$Mn.apply2(printf.Lit7, this.staticLink.precision), paramObject4, this.staticLink.precision) == Boolean.FALSE) {
                continue;
              }
              this.staticLink.precision = AddOp.$Mn.apply2(this.staticLink.precision, paramObject4);
              if (i == 0) {
                continue;
              }
              paramObject1 = Boolean.TRUE;
              paramObject1 = lambda29f(paramObject3, paramObject4, paramObject1);
              continue;
              if (Scheme.isEqv.apply2(this.fc, printf.Lit56) != Boolean.FALSE) {
                continue;
              }
              if (Scheme.isEqv.apply2(this.fc, printf.Lit57) == Boolean.FALSE) {
                continue;
              }
              paramArrayOfObject = "";
            }
          }
          catch (ClassCastException paramObject2)
          {
            throw new WrongType((ClassCastException)paramObject2, "strip-0s", -2, paramObject1);
          }
          try
          {
            paramObject1 = LangObjType.coerceRealNum(paramObject4);
            if (numbers.isNegative((RealNum)paramObject1))
            {
              paramObject1 = DivideOp.quotient.apply2(AddOp.$Mn.apply2(paramObject4, printf.Lit61), printf.Lit61);
              localObject = Scheme.numLss.apply3(printf.Lit17, AddOp.$Pl.apply2(paramObject1, printf.Lit48), Integer.valueOf(vectors.vectorLength(printf.Lit62)));
            }
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "negative?", 1, paramObject4);
          }
          try
          {
            bool = ((Boolean)localObject).booleanValue();
            if (bool)
            {
              if (paramObject1 == Boolean.FALSE) {
                continue;
              }
              paramObject4 = AddOp.$Mn.apply2(paramObject4, MultiplyOp.$St.apply2(printf.Lit61, paramObject1));
              this.staticLink.precision = numbers.max(new Object[] { printf.Lit1, AddOp.$Mn.apply2(this.staticLink.precision, paramObject4) });
              paramObject3 = lambda29f(paramObject3, paramObject4, Boolean.FALSE);
              paramObject4 = printf.Lit62;
              paramObject1 = AddOp.$Pl.apply2(paramObject1, printf.Lit48);
            }
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "x", -2, localObject);
          }
          try
          {
            i = ((Number)paramObject1).intValue();
            paramObject1 = append.append$V(new Object[] { paramObject3, LList.list2(paramArrayOfObject, vectors.vectorRef((FVector)paramObject4, i)) });
          }
          catch (ClassCastException paramObject2)
          {
            throw new WrongType((ClassCastException)paramObject2, "vector-ref", 2, paramObject1);
          }
          i = 0;
          continue;
          paramObject1 = Boolean.FALSE;
          continue;
          this.staticLink.precision = AddOp.$Mn.apply2(this.staticLink.precision, printf.Lit7);
          if (i != 0)
          {
            paramObject1 = Boolean.TRUE;
          }
          else
          {
            paramObject1 = Boolean.FALSE;
            continue;
            paramObject1 = DivideOp.quotient.apply2(AddOp.$Mn.apply2(paramObject4, printf.Lit7), printf.Lit61);
            continue;
            if (bool)
            {
              paramObject1 = Boolean.TRUE;
            }
            else
            {
              paramObject1 = Boolean.FALSE;
              continue;
              continue;
              if (Scheme.isEqv.apply2(this.fc, printf.Lit58) != Boolean.FALSE) {
                paramArrayOfObject = " ";
              } else {
                paramObject1 = Values.empty;
              }
            }
          }
        }
      }
      return append.append$V(new Object[] { lambda30formatReal$V(paramObject1, paramObject2, paramObject3, paramObject4, new Object[0]), Scheme.apply.apply3(this.format$Mnreal, Boolean.TRUE, paramArrayOfObject), printf.Lit63 });
    }
    
    Object lambda31$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject)
    {
      paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
      return Scheme.apply.apply2(this.staticLink.pad, Scheme.apply.applyN(new Object[] { this.format$Mnreal, this.staticLink.signed, paramObject1, paramObject2, paramObject3, paramArrayOfObject }));
    }
    
    public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 14: 
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
  
  public class frame12
    extends ModuleBody
  {
    Object cnt;
    final ModuleMethod lambda$Fn18;
    Object port;
    
    public frame12()
    {
      this$1 = new ModuleMethod(this, 20, null, 4097);
      this$1.setProperty("source-location", "printf.scm:546");
      this.lambda$Fn18 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 20) {
        return lambda32(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Boolean lambda32(Object paramObject)
    {
      AddOp localAddOp;
      if (strings.isString(paramObject)) {
        localAddOp = AddOp.$Pl;
      }
      try
      {
        CharSequence localCharSequence = (CharSequence)paramObject;
        this.cnt = localAddOp.apply2(Integer.valueOf(strings.stringLength(localCharSequence)), this.cnt);
        ports.display(paramObject, this.port);
        return Boolean.TRUE;
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "string-length", 1, paramObject);
      }
      this.cnt = AddOp.$Pl.apply2(printf.Lit7, this.cnt);
      ports.display(paramObject, this.port);
      return Boolean.TRUE;
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 20)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame13
    extends ModuleBody
  {
    Object cnt;
    Object end;
    final ModuleMethod lambda$Fn19;
    Object s;
    Object str;
    
    public frame13()
    {
      this$1 = new ModuleMethod(this, 21, null, 4097);
      this$1.setProperty("source-location", "printf.scm:564");
      this.lambda$Fn19 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 21)
      {
        if (lambda33(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda33(Object paramObject)
    {
      Object localObject2;
      if (strings.isString(paramObject)) {
        if (this.str == Boolean.FALSE)
        {
          localObject1 = Scheme.numGEq;
          localObject2 = AddOp.$Mn.apply2(this.end, this.cnt);
        }
      }
      for (;;)
      {
        Object localObject4;
        try
        {
          localObject3 = (CharSequence)paramObject;
          if (((Procedure)localObject1).apply2(localObject2, Integer.valueOf(strings.stringLength((CharSequence)localObject3))) == Boolean.FALSE) {}
        }
        catch (ClassCastException localClassCastException1)
        {
          Object localObject3;
          int i;
          int j;
          throw new WrongType(localClassCastException1, "string-length", 1, paramObject);
        }
        try
        {
          localObject1 = (CharSequence)paramObject;
          localObject2 = numbers.min(new Object[] { Integer.valueOf(strings.stringLength((CharSequence)localObject1)), AddOp.$Mn.apply2(this.end, this.cnt) });
          localObject1 = printf.Lit1;
          if (Scheme.numGEq.apply2(localObject1, localObject2) == Boolean.FALSE) {
            localObject4 = this.s;
          }
        }
        catch (ClassCastException localClassCastException2)
        {
          throw new WrongType(localClassCastException2, "string-length", 1, paramObject);
        }
        try
        {
          localObject3 = (CharSeq)localObject4;
          localObject4 = this.cnt;
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "string-set!", 1, localObject4);
        }
        try
        {
          i = ((Number)localObject4).intValue();
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "string-set!", 2, localObject4);
        }
        try
        {
          localObject4 = (CharSequence)paramObject;
        }
        catch (ClassCastException localClassCastException3)
        {
          throw new WrongType(localClassCastException3, "string-ref", 1, paramObject);
        }
        try
        {
          j = ((Number)localObject1).intValue();
          strings.stringSet$Ex((CharSeq)localObject3, i, strings.stringRef((CharSequence)localObject4, j));
          this.cnt = AddOp.$Pl.apply2(this.cnt, printf.Lit7);
          localObject1 = AddOp.$Pl.apply2(localObject1, printf.Lit7);
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "string-ref", 2, localClassCastException3);
        }
      }
      Object localObject1 = this.s;
      for (;;)
      {
        try
        {
          localObject2 = (CharSequence)localObject1;
          localObject1 = this.cnt;
        }
        catch (ClassCastException paramObject)
        {
          char c;
          throw new WrongType((ClassCastException)paramObject, "substring", 1, localClassCastException3);
        }
        try
        {
          i = ((Number)localObject1).intValue();
          this.s = strings.stringAppend(new Object[] { strings.substring((CharSequence)localObject2, 0, i), paramObject });
          paramObject = this.s;
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "substring", 3, localClassCastException3);
        }
        try
        {
          localObject1 = (CharSequence)paramObject;
          this.cnt = Integer.valueOf(strings.stringLength((CharSequence)localObject1));
          this.end = this.cnt;
          if (this.str != Boolean.FALSE) {
            if (Scheme.numGEq.apply2(this.cnt, this.end) != Boolean.FALSE)
            {
              i = 1;
              return i + 1 & 0x1;
              if (this.str != Boolean.FALSE)
              {
                localObject1 = Scheme.numGEq.apply2(this.cnt, this.end);
                if (localObject1 != Boolean.FALSE) {
                  continue;
                }
                localObject1 = this.str;
              }
            }
          }
        }
        catch (ClassCastException localClassCastException4)
        {
          throw new WrongType(localClassCastException4, "string-length", 1, paramObject);
        }
        try
        {
          localObject2 = Boolean.FALSE;
          if (localObject1 != localObject2)
          {
            i = 1;
            i = i + 1 & 0x1;
            if (i == 0) {
              continue;
            }
            if (Scheme.numGEq.apply2(this.cnt, this.end) != Boolean.FALSE)
            {
              this.s = strings.stringAppend(new Object[] { this.s, strings.makeString(100) });
              localObject1 = this.s;
            }
          }
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "x", -2, localClassCastException4);
        }
        try
        {
          localObject2 = (CharSequence)localObject1;
          this.end = Integer.valueOf(strings.stringLength((CharSequence)localObject2));
          localObject1 = this.s;
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "string-length", 1, localClassCastException4);
        }
        try
        {
          localObject2 = (CharSeq)localObject1;
          localObject1 = this.cnt;
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "string-set!", 1, localClassCastException4);
        }
        try
        {
          i = ((Number)localObject1).intValue();
          if (!characters.isChar(paramObject)) {}
        }
        catch (ClassCastException paramObject)
        {
          throw new WrongType((ClassCastException)paramObject, "string-set!", 2, localClassCastException4);
        }
        try
        {
          c = ((Char)paramObject).charValue();
          strings.stringSet$Ex((CharSeq)localObject2, i, c);
          this.cnt = AddOp.$Pl.apply2(this.cnt, printf.Lit7);
        }
        catch (ClassCastException localClassCastException5)
        {
          throw new WrongType(localClassCastException5, "string-set!", 3, paramObject);
        }
        localObject1 = this.str;
        continue;
        i = 0;
        continue;
        if (i != 0)
        {
          continue;
          c = '?';
          continue;
          i = 0;
          continue;
          if (this.str != Boolean.FALSE) {
            i = 1;
          } else {
            i = 0;
          }
        }
      }
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 21)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame2
    extends ModuleBody
  {
    Object cont;
    final ModuleMethod lambda$Fn5;
    final ModuleMethod lambda$Fn6;
    printf.frame staticLink;
    
    public frame2()
    {
      this$1 = new ModuleMethod(this, 10, null, 8194);
      this$1.setProperty("source-location", "printf.scm:81");
      this.lambda$Fn6 = this$1;
      this$1 = new ModuleMethod(this, 11, null, 4097);
      this$1.setProperty("source-location", "printf.scm:78");
      this.lambda$Fn5 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 11) {
        return lambda9(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 10) {
        return lambda10(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda10(Object paramObject1, Object paramObject2)
    {
      printf.frame3 localframe3 = new printf.frame3();
      localframe3.staticLink = this;
      localframe3.sgn = paramObject2;
      return this.staticLink.lambda3digits(paramObject1, localframe3.lambda$Fn7);
    }
    
    Object lambda9(Object paramObject)
    {
      return this.staticLink.lambda2sign(paramObject, this.lambda$Fn6);
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
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 10)
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
  
  public class frame3
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn7;
    Object sgn;
    printf.frame2 staticLink;
    
    public frame3()
    {
      this$1 = new ModuleMethod(this, 9, null, 8194);
      this$1.setProperty("source-location", "printf.scm:84");
      this.lambda$Fn7 = this$1;
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 9) {
        return lambda11(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    /* Error */
    Object lambda11(Object paramObject1, Object paramObject2)
    {
      // Byte code:
      //   0: new 15	gnu/kawa/slib/printf$frame4
      //   3: dup
      //   4: invokespecial 68	gnu/kawa/slib/printf$frame4:<init>	()V
      //   7: astore_3
      //   8: aload_3
      //   9: aload_0
      //   10: putfield 71	gnu/kawa/slib/printf$frame4:staticLink	Lgnu/kawa/slib/printf$frame3;
      //   13: aload_3
      //   14: aload_2
      //   15: putfield 74	gnu/kawa/slib/printf$frame4:idigs	Ljava/lang/Object;
      //   18: aload_3
      //   19: getfield 77	gnu/kawa/slib/printf$frame4:lambda$Fn8	Lgnu/expr/ModuleMethod;
      //   22: astore_2
      //   23: getstatic 83	kawa/standard/Scheme:numLss	Lgnu/kawa/functions/NumberCompare;
      //   26: aload_1
      //   27: aload_0
      //   28: getfield 85	gnu/kawa/slib/printf$frame3:staticLink	Lgnu/kawa/slib/printf$frame2;
      //   31: getfield 88	gnu/kawa/slib/printf$frame2:staticLink	Lgnu/kawa/slib/printf$frame;
      //   34: getfield 91	gnu/kawa/slib/printf$frame:n	I
      //   37: invokestatic 97	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   40: invokevirtual 101	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   43: astore_3
      //   44: aload_3
      //   45: checkcast 103	java/lang/Boolean
      //   48: invokevirtual 107	java/lang/Boolean:booleanValue	()Z
      //   51: istore 7
      //   53: iload 7
      //   55: ifeq +70 -> 125
      //   58: getstatic 111	gnu/kawa/slib/printf:Lit11	Lgnu/text/Char;
      //   61: astore 4
      //   63: aload_0
      //   64: getfield 85	gnu/kawa/slib/printf$frame3:staticLink	Lgnu/kawa/slib/printf$frame2;
      //   67: getfield 88	gnu/kawa/slib/printf$frame2:staticLink	Lgnu/kawa/slib/printf$frame;
      //   70: getfield 114	gnu/kawa/slib/printf$frame:str	Ljava/lang/Object;
      //   73: astore_3
      //   74: aload_3
      //   75: checkcast 116	java/lang/CharSequence
      //   78: astore 5
      //   80: aload_1
      //   81: checkcast 118	java/lang/Number
      //   84: invokevirtual 122	java/lang/Number:intValue	()I
      //   87: istore 6
      //   89: aload 4
      //   91: aload 5
      //   93: iload 6
      //   95: invokestatic 128	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
      //   98: invokestatic 134	gnu/text/Char:make	(I)Lgnu/text/Char;
      //   101: invokestatic 140	kawa/lib/characters:isChar$Eq	(Lgnu/text/Char;Lgnu/text/Char;)Z
      //   104: ifeq +26 -> 130
      //   107: getstatic 144	kawa/standard/Scheme:applyToArgs	Lgnu/kawa/functions/ApplyToArgs;
      //   110: aload_2
      //   111: getstatic 150	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
      //   114: aload_1
      //   115: getstatic 154	gnu/kawa/slib/printf:Lit7	Lgnu/math/IntNum;
      //   118: invokevirtual 101	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   121: invokevirtual 101	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   124: areturn
      //   125: iload 7
      //   127: ifne -20 -> 107
      //   130: getstatic 144	kawa/standard/Scheme:applyToArgs	Lgnu/kawa/functions/ApplyToArgs;
      //   133: aload_2
      //   134: aload_1
      //   135: invokevirtual 101	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   138: areturn
      //   139: astore_1
      //   140: new 156	gnu/mapping/WrongType
      //   143: dup
      //   144: aload_1
      //   145: ldc -98
      //   147: bipush -2
      //   149: aload_3
      //   150: invokespecial 161	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   153: athrow
      //   154: astore_1
      //   155: new 156	gnu/mapping/WrongType
      //   158: dup
      //   159: aload_1
      //   160: ldc -93
      //   162: iconst_1
      //   163: aload_3
      //   164: invokespecial 161	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   167: athrow
      //   168: astore_2
      //   169: new 156	gnu/mapping/WrongType
      //   172: dup
      //   173: aload_2
      //   174: ldc -93
      //   176: iconst_2
      //   177: aload_1
      //   178: invokespecial 161	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   181: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	182	0	this	frame3
      //   0	182	1	paramObject1	Object
      //   0	182	2	paramObject2	Object
      //   7	157	3	localObject	Object
      //   61	29	4	localChar	Char
      //   78	14	5	localCharSequence	CharSequence
      //   87	7	6	i	int
      //   51	75	7	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   44	53	139	java/lang/ClassCastException
      //   74	80	154	java/lang/ClassCastException
      //   80	89	168	java/lang/ClassCastException
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 9)
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
  
  public class frame4
    extends ModuleBody
  {
    Object idigs;
    final ModuleMethod lambda$Fn8;
    final ModuleMethod lambda$Fn9;
    printf.frame3 staticLink;
    
    public frame4()
    {
      this$1 = new ModuleMethod(this, 7, null, 8194);
      this$1.setProperty("source-location", "printf.scm:90");
      this.lambda$Fn9 = this$1;
      this$1 = new ModuleMethod(this, 8, null, 4097);
      this$1.setProperty("source-location", "printf.scm:87");
      this.lambda$Fn8 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 8) {
        return lambda12(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 7) {
        return lambda13(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda12(Object paramObject)
    {
      return this.staticLink.staticLink.staticLink.lambda3digits(paramObject, this.lambda$Fn9);
    }
    
    /* Error */
    Object lambda13(Object paramObject1, Object paramObject2)
    {
      // Byte code:
      //   0: new 18	gnu/kawa/slib/printf$frame5
      //   3: dup
      //   4: invokespecial 92	gnu/kawa/slib/printf$frame5:<init>	()V
      //   7: astore_3
      //   8: aload_3
      //   9: aload_0
      //   10: putfield 95	gnu/kawa/slib/printf$frame5:staticLink	Lgnu/kawa/slib/printf$frame4;
      //   13: aload_3
      //   14: aload_2
      //   15: putfield 98	gnu/kawa/slib/printf$frame5:fdigs	Ljava/lang/Object;
      //   18: aload_3
      //   19: getfield 101	gnu/kawa/slib/printf$frame5:lambda$Fn10	Lgnu/expr/ModuleMethod;
      //   22: astore_3
      //   23: aload_0
      //   24: getfield 80	gnu/kawa/slib/printf$frame4:staticLink	Lgnu/kawa/slib/printf$frame3;
      //   27: getfield 83	gnu/kawa/slib/printf$frame3:staticLink	Lgnu/kawa/slib/printf$frame2;
      //   30: getfield 86	gnu/kawa/slib/printf$frame2:staticLink	Lgnu/kawa/slib/printf$frame;
      //   33: astore 4
      //   35: new 21	gnu/kawa/slib/printf$frame6
      //   38: dup
      //   39: invokespecial 102	gnu/kawa/slib/printf$frame6:<init>	()V
      //   42: astore_2
      //   43: aload_2
      //   44: aload 4
      //   46: putfield 103	gnu/kawa/slib/printf$frame6:staticLink	Lgnu/kawa/slib/printf$frame;
      //   49: aload_2
      //   50: aload_3
      //   51: putfield 106	gnu/kawa/slib/printf$frame6:cont	Ljava/lang/Object;
      //   54: getstatic 112	kawa/standard/Scheme:numGEq	Lgnu/kawa/functions/NumberCompare;
      //   57: aload_1
      //   58: aload_0
      //   59: getfield 80	gnu/kawa/slib/printf$frame4:staticLink	Lgnu/kawa/slib/printf$frame3;
      //   62: getfield 83	gnu/kawa/slib/printf$frame3:staticLink	Lgnu/kawa/slib/printf$frame2;
      //   65: getfield 86	gnu/kawa/slib/printf$frame2:staticLink	Lgnu/kawa/slib/printf$frame;
      //   68: getfield 115	gnu/kawa/slib/printf$frame:n	I
      //   71: invokestatic 121	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   74: invokevirtual 125	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   77: getstatic 131	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   80: if_acmpeq +18 -> 98
      //   83: getstatic 135	kawa/standard/Scheme:applyToArgs	Lgnu/kawa/functions/ApplyToArgs;
      //   86: aload_2
      //   87: getfield 106	gnu/kawa/slib/printf$frame6:cont	Ljava/lang/Object;
      //   90: aload_1
      //   91: getstatic 139	gnu/kawa/slib/printf:Lit1	Lgnu/math/IntNum;
      //   94: invokevirtual 143	gnu/mapping/Procedure:apply3	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   97: areturn
      //   98: aload_0
      //   99: getfield 80	gnu/kawa/slib/printf$frame4:staticLink	Lgnu/kawa/slib/printf$frame3;
      //   102: getfield 83	gnu/kawa/slib/printf$frame3:staticLink	Lgnu/kawa/slib/printf$frame2;
      //   105: getfield 86	gnu/kawa/slib/printf$frame2:staticLink	Lgnu/kawa/slib/printf$frame;
      //   108: getfield 146	gnu/kawa/slib/printf$frame:str	Ljava/lang/Object;
      //   111: astore_3
      //   112: aload_3
      //   113: checkcast 148	java/lang/CharSequence
      //   116: astore 4
      //   118: aload_1
      //   119: checkcast 150	java/lang/Number
      //   122: invokevirtual 154	java/lang/Number:intValue	()I
      //   125: istore 5
      //   127: aload 4
      //   129: iload 5
      //   131: invokestatic 160	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
      //   134: invokestatic 166	gnu/text/Char:make	(I)Lgnu/text/Char;
      //   137: getstatic 170	gnu/kawa/slib/printf:Lit10	Lgnu/lists/PairWithPosition;
      //   140: invokestatic 175	kawa/lib/lists:memv	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   143: getstatic 131	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   146: if_acmpeq +31 -> 177
      //   149: aload_0
      //   150: getfield 80	gnu/kawa/slib/printf$frame4:staticLink	Lgnu/kawa/slib/printf$frame3;
      //   153: getfield 83	gnu/kawa/slib/printf$frame3:staticLink	Lgnu/kawa/slib/printf$frame2;
      //   156: getfield 86	gnu/kawa/slib/printf$frame2:staticLink	Lgnu/kawa/slib/printf$frame;
      //   159: getstatic 181	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
      //   162: aload_1
      //   163: getstatic 184	gnu/kawa/slib/printf:Lit7	Lgnu/math/IntNum;
      //   166: invokevirtual 125	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   169: aload_2
      //   170: getfield 187	gnu/kawa/slib/printf$frame6:lambda$Fn11	Lgnu/expr/ModuleMethod;
      //   173: invokevirtual 190	gnu/kawa/slib/printf$frame:lambda2sign	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   176: areturn
      //   177: getstatic 135	kawa/standard/Scheme:applyToArgs	Lgnu/kawa/functions/ApplyToArgs;
      //   180: aload_2
      //   181: getfield 106	gnu/kawa/slib/printf$frame6:cont	Ljava/lang/Object;
      //   184: aload_1
      //   185: getstatic 139	gnu/kawa/slib/printf:Lit1	Lgnu/math/IntNum;
      //   188: invokevirtual 143	gnu/mapping/Procedure:apply3	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   191: areturn
      //   192: astore_1
      //   193: new 192	gnu/mapping/WrongType
      //   196: dup
      //   197: aload_1
      //   198: ldc -62
      //   200: iconst_1
      //   201: aload_3
      //   202: invokespecial 197	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   205: athrow
      //   206: astore_2
      //   207: new 192	gnu/mapping/WrongType
      //   210: dup
      //   211: aload_2
      //   212: ldc -62
      //   214: iconst_2
      //   215: aload_1
      //   216: invokespecial 197	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   219: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	220	0	this	frame4
      //   0	220	1	paramObject1	Object
      //   0	220	2	paramObject2	Object
      //   7	195	3	localObject1	Object
      //   33	95	4	localObject2	Object
      //   125	5	5	i	int
      // Exception table:
      //   from	to	target	type
      //   112	118	192	java/lang/ClassCastException
      //   118	127	206	java/lang/ClassCastException
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
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 7)
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
  
  public class frame5
    extends ModuleBody
  {
    Object fdigs;
    final ModuleMethod lambda$Fn10;
    printf.frame4 staticLink;
    
    public frame5()
    {
      this$1 = new ModuleMethod(this, 6, null, 8194);
      this$1.setProperty("source-location", "printf.scm:92");
      this.lambda$Fn10 = this$1;
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 6) {
        return lambda14(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda14(Object paramObject1, Object paramObject2)
    {
      localFString = strings.stringAppend(new Object[] { "0", this.staticLink.idigs, this.fdigs });
      i = strings.stringLength(localFString);
      localObject1 = printf.Lit7;
      localObject3 = AddOp.$Pl;
      localObject2 = this.staticLink.idigs;
      try
      {
        localObject4 = (CharSequence)localObject2;
        paramObject2 = ((Procedure)localObject3).apply2(paramObject2, Integer.valueOf(strings.stringLength((CharSequence)localObject4)));
      }
      catch (ClassCastException paramObject1)
      {
        try
        {
          for (;;)
          {
            j = ((Number)localObject1).intValue();
            if (!characters.isChar$Eq((Char)localObject2, Char.make(strings.stringRef(localFString, j)))) {
              break;
            }
            localObject1 = AddOp.$Pl.apply2(localObject1, printf.Lit7);
            paramObject2 = AddOp.$Mn.apply2(paramObject2, printf.Lit7);
          }
          localObject2 = Scheme.applyToArgs;
          localObject3 = this.staticLink.staticLink.staticLink.cont;
          localObject4 = this.staticLink.staticLink.sgn;
          localObject1 = AddOp.$Mn.apply2(localObject1, printf.Lit7);
        }
        catch (ClassCastException paramObject1)
        {
          Object localObject4;
          int j;
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject1);
        }
        try
        {
          j = ((Number)localObject1).intValue();
          return ((Procedure)localObject2).applyN(new Object[] { localObject3, paramObject1, localObject4, strings.substring(localFString, j, i), paramObject2 });
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 2, localObject1);
        }
        paramObject1 = paramObject1;
        throw new WrongType((ClassCastException)paramObject1, "string-length", 1, localObject2);
      }
      if (Scheme.numGEq.apply2(localObject1, Integer.valueOf(i)) != Boolean.FALSE) {
        return Scheme.applyToArgs.applyN(new Object[] { this.staticLink.staticLink.staticLink.cont, paramObject1, this.staticLink.staticLink.sgn, "0", printf.Lit7 });
      }
      localObject2 = printf.Lit9;
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 6)
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
  
  public class frame6
    extends ModuleBody
  {
    Object cont;
    final ModuleMethod lambda$Fn11;
    printf.frame staticLink;
    
    public frame6()
    {
      this$1 = new ModuleMethod(this, 5, null, 8194);
      this$1.setProperty("source-location", "printf.scm:67");
      this.lambda$Fn11 = this$1;
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 5) {
        return lambda15(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda15(Object paramObject1, Object paramObject2)
    {
      printf.frame7 localframe7 = new printf.frame7();
      localframe7.staticLink = this;
      localframe7.sgn = paramObject2;
      return this.staticLink.lambda3digits(paramObject1, localframe7.lambda$Fn12);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 5)
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
  
  public class frame7
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn12;
    Object sgn;
    printf.frame6 staticLink;
    
    public frame7()
    {
      this$1 = new ModuleMethod(this, 4, null, 8194);
      this$1.setProperty("source-location", "printf.scm:69");
      this.lambda$Fn12 = this$1;
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 4) {
        return lambda16(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    /* Error */
    Object lambda16(Object paramObject1, Object paramObject2)
    {
      // Byte code:
      //   0: getstatic 70	kawa/standard/Scheme:applyToArgs	Lgnu/kawa/functions/ApplyToArgs;
      //   3: astore_3
      //   4: aload_0
      //   5: getfield 72	gnu/kawa/slib/printf$frame7:staticLink	Lgnu/kawa/slib/printf$frame6;
      //   8: getfield 75	gnu/kawa/slib/printf$frame6:cont	Ljava/lang/Object;
      //   11: astore 4
      //   13: getstatic 79	gnu/kawa/slib/printf:Lit5	Lgnu/text/Char;
      //   16: astore 6
      //   18: aload_0
      //   19: getfield 81	gnu/kawa/slib/printf$frame7:sgn	Ljava/lang/Object;
      //   22: astore 5
      //   24: aload 5
      //   26: checkcast 83	gnu/text/Char
      //   29: astore 7
      //   31: aload 6
      //   33: aload 7
      //   35: invokestatic 89	kawa/lib/characters:isChar$Eq	(Lgnu/text/Char;Lgnu/text/Char;)Z
      //   38: ifeq +34 -> 72
      //   41: getstatic 95	gnu/kawa/functions/AddOp:$Mn	Lgnu/kawa/functions/AddOp;
      //   44: astore 5
      //   46: aload_2
      //   47: checkcast 97	java/lang/CharSequence
      //   50: astore 6
      //   52: aload 5
      //   54: aload 6
      //   56: invokestatic 103	kawa/lib/numbers:string$To$Number	(Ljava/lang/CharSequence;)Ljava/lang/Object;
      //   59: invokevirtual 109	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
      //   62: astore_2
      //   63: aload_3
      //   64: aload 4
      //   66: aload_1
      //   67: aload_2
      //   68: invokevirtual 113	gnu/mapping/Procedure:apply3	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   71: areturn
      //   72: aload_2
      //   73: checkcast 97	java/lang/CharSequence
      //   76: astore 5
      //   78: aload 5
      //   80: invokestatic 103	kawa/lib/numbers:string$To$Number	(Ljava/lang/CharSequence;)Ljava/lang/Object;
      //   83: astore_2
      //   84: goto -21 -> 63
      //   87: astore_1
      //   88: new 115	gnu/mapping/WrongType
      //   91: dup
      //   92: aload_1
      //   93: ldc 117
      //   95: iconst_2
      //   96: aload 5
      //   98: invokespecial 120	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   101: athrow
      //   102: astore_1
      //   103: new 115	gnu/mapping/WrongType
      //   106: dup
      //   107: aload_1
      //   108: ldc 122
      //   110: iconst_1
      //   111: aload_2
      //   112: invokespecial 120	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   115: athrow
      //   116: astore_1
      //   117: new 115	gnu/mapping/WrongType
      //   120: dup
      //   121: aload_1
      //   122: ldc 122
      //   124: iconst_1
      //   125: aload_2
      //   126: invokespecial 120	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   129: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	130	0	this	frame7
      //   0	130	1	paramObject1	Object
      //   0	130	2	paramObject2	Object
      //   3	61	3	localApplyToArgs	gnu.kawa.functions.ApplyToArgs
      //   11	54	4	localObject1	Object
      //   22	75	5	localObject2	Object
      //   16	39	6	localObject3	Object
      //   29	5	7	localChar	Char
      // Exception table:
      //   from	to	target	type
      //   24	31	87	java/lang/ClassCastException
      //   46	52	102	java/lang/ClassCastException
      //   72	78	116	java/lang/ClassCastException
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 4)
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
  
  public class frame8
    extends ModuleBody
  {
    CharSequence str;
    
    public Object lambda17dig(Object paramObject)
    {
      CharSequence localCharSequence = this.str;
      try
      {
        int i = ((Number)paramObject).intValue();
        i = strings.stringRef(localCharSequence, i);
        if (unicode.isCharNumeric(Char.make(i))) {
          return numbers.string$To$Number(strings.$make$string$(new Object[] { Char.make(i) }));
        }
        return printf.Lit1;
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "string-ref", 2, paramObject);
      }
    }
  }
  
  public class frame9
    extends ModuleBody
  {
    LList args;
    Object fc;
    int fl;
    Object format$Mnstring;
    Object out;
    Object pos;
    
    /* Error */
    public Object lambda18mustAdvance()
    {
      // Byte code:
      //   0: aload_0
      //   1: getstatic 31	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
      //   4: getstatic 35	gnu/kawa/slib/printf:Lit7	Lgnu/math/IntNum;
      //   7: aload_0
      //   8: getfield 37	gnu/kawa/slib/printf$frame9:pos	Ljava/lang/Object;
      //   11: invokevirtual 43	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   14: putfield 37	gnu/kawa/slib/printf$frame9:pos	Ljava/lang/Object;
      //   17: getstatic 49	kawa/standard/Scheme:numGEq	Lgnu/kawa/functions/NumberCompare;
      //   20: aload_0
      //   21: getfield 37	gnu/kawa/slib/printf$frame9:pos	Ljava/lang/Object;
      //   24: aload_0
      //   25: getfield 51	gnu/kawa/slib/printf$frame9:fl	I
      //   28: invokestatic 57	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   31: invokevirtual 43	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   34: getstatic 63	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
      //   37: if_acmpeq +8 -> 45
      //   40: aload_0
      //   41: invokevirtual 66	gnu/kawa/slib/printf$frame9:lambda20incomplete	()Ljava/lang/Object;
      //   44: areturn
      //   45: aload_0
      //   46: getfield 68	gnu/kawa/slib/printf$frame9:format$Mnstring	Ljava/lang/Object;
      //   49: astore_1
      //   50: aload_1
      //   51: checkcast 70	java/lang/CharSequence
      //   54: astore_2
      //   55: aload_0
      //   56: getfield 37	gnu/kawa/slib/printf$frame9:pos	Ljava/lang/Object;
      //   59: astore_1
      //   60: aload_1
      //   61: checkcast 72	java/lang/Number
      //   64: invokevirtual 76	java/lang/Number:intValue	()I
      //   67: istore_3
      //   68: aload_0
      //   69: aload_2
      //   70: iload_3
      //   71: invokestatic 82	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
      //   74: invokestatic 88	gnu/text/Char:make	(I)Lgnu/text/Char;
      //   77: putfield 90	gnu/kawa/slib/printf$frame9:fc	Ljava/lang/Object;
      //   80: getstatic 96	gnu/mapping/Values:empty	Lgnu/mapping/Values;
      //   83: areturn
      //   84: astore_2
      //   85: new 98	gnu/mapping/WrongType
      //   88: dup
      //   89: aload_2
      //   90: ldc 100
      //   92: iconst_1
      //   93: aload_1
      //   94: invokespecial 103	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   97: athrow
      //   98: astore_2
      //   99: new 98	gnu/mapping/WrongType
      //   102: dup
      //   103: aload_2
      //   104: ldc 100
      //   106: iconst_2
      //   107: aload_1
      //   108: invokespecial 103	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   111: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	112	0	this	frame9
      //   49	59	1	localObject	Object
      //   54	16	2	localCharSequence	CharSequence
      //   84	6	2	localClassCastException1	ClassCastException
      //   98	6	2	localClassCastException2	ClassCastException
      //   67	4	3	i	int
      // Exception table:
      //   from	to	target	type
      //   50	55	84	java/lang/ClassCastException
      //   60	68	98	java/lang/ClassCastException
    }
    
    public boolean lambda19isEndOfFormat()
    {
      return ((Boolean)Scheme.numGEq.apply2(this.pos, Integer.valueOf(this.fl))).booleanValue();
    }
    
    public Object lambda20incomplete()
    {
      return misc.error$V(printf.Lit34, new Object[] { "conversion specification incomplete", this.format$Mnstring });
    }
    
    public Object lambda21out$St(Object paramObject)
    {
      Object localObject = paramObject;
      if (strings.isString(paramObject)) {
        return Scheme.applyToArgs.apply2(this.out, paramObject);
      }
      boolean bool;
      do
      {
        paramObject = Scheme.applyToArgs.apply2(this.out, lists.car.apply1(localObject));
        if (paramObject == Boolean.FALSE) {
          break;
        }
        localObject = lists.cdr.apply1(localObject);
        bool = lists.isNull(localObject);
      } while (!bool);
      if (bool) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
      return paramObject;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\printf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */