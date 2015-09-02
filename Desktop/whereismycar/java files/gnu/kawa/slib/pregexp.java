package gnu.kawa.slib;

import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.append;

public class pregexp
  extends ModuleBody
{
  public static Char $Stpregexp$Mncomment$Mnchar$St;
  public static Object $Stpregexp$Mnnul$Mnchar$Mnint$St;
  public static Object $Stpregexp$Mnreturn$Mnchar$St;
  public static Object $Stpregexp$Mnspace$Mnsensitive$Qu$St;
  public static Object $Stpregexp$Mntab$Mnchar$St;
  public static IntNum $Stpregexp$Mnversion$St;
  public static final pregexp $instance;
  static final IntNum Lit0;
  static final Char Lit1;
  static final SimpleSymbol Lit10;
  static final SimpleSymbol Lit100;
  static final SimpleSymbol Lit101;
  static final SimpleSymbol Lit102;
  static final SimpleSymbol Lit103;
  static final SimpleSymbol Lit104;
  static final SimpleSymbol Lit105;
  static final PairWithPosition Lit106;
  static final SimpleSymbol Lit107;
  static final PairWithPosition Lit108;
  static final SimpleSymbol Lit109;
  static final Char Lit11;
  static final SimpleSymbol Lit110;
  static final SimpleSymbol Lit111;
  static final SimpleSymbol Lit112;
  static final Char Lit113;
  static final SimpleSymbol Lit114;
  static final SimpleSymbol Lit115;
  static final PairWithPosition Lit116;
  static final SimpleSymbol Lit117;
  static final SimpleSymbol Lit118;
  static final SimpleSymbol Lit119;
  static final SimpleSymbol Lit12;
  static final SimpleSymbol Lit120;
  static final SimpleSymbol Lit121;
  static final SimpleSymbol Lit122;
  static final SimpleSymbol Lit123;
  static final SimpleSymbol Lit124;
  static final SimpleSymbol Lit125;
  static final SimpleSymbol Lit126;
  static final SimpleSymbol Lit127;
  static final SimpleSymbol Lit128;
  static final SimpleSymbol Lit129;
  static final Char Lit13;
  static final SimpleSymbol Lit130;
  static final SimpleSymbol Lit131;
  static final SimpleSymbol Lit132;
  static final SimpleSymbol Lit133;
  static final SimpleSymbol Lit134;
  static final SimpleSymbol Lit135 = (SimpleSymbol)new SimpleSymbol("pregexp-quote").readResolve();
  static final SimpleSymbol Lit14;
  static final Char Lit15;
  static final IntNum Lit16;
  static final SimpleSymbol Lit17;
  static final Char Lit18;
  static final Char Lit19;
  static final Char Lit2;
  static final SimpleSymbol Lit20;
  static final SimpleSymbol Lit21;
  static final SimpleSymbol Lit22;
  static final SimpleSymbol Lit23;
  static final Char Lit24;
  static final Char Lit25;
  static final SimpleSymbol Lit26;
  static final Char Lit27;
  static final SimpleSymbol Lit28;
  static final Char Lit29;
  static final Char Lit3;
  static final SimpleSymbol Lit30;
  static final Char Lit31;
  static final PairWithPosition Lit32;
  static final Char Lit33;
  static final Char Lit34;
  static final Char Lit35;
  static final SimpleSymbol Lit36;
  static final Char Lit37;
  static final PairWithPosition Lit38;
  static final Char Lit39;
  static final SimpleSymbol Lit4;
  static final Char Lit40;
  static final SimpleSymbol Lit41;
  static final Char Lit42;
  static final PairWithPosition Lit43;
  static final Char Lit44;
  static final SimpleSymbol Lit45;
  static final Char Lit46;
  static final Char Lit47;
  static final Char Lit48;
  static final PairWithPosition Lit49;
  static final SimpleSymbol Lit5;
  static final Char Lit50;
  static final PairWithPosition Lit51;
  static final Char Lit52;
  static final PairWithPosition Lit53;
  static final Char Lit54;
  static final PairWithPosition Lit55;
  static final PairWithPosition Lit56;
  static final SimpleSymbol Lit57;
  static final Char Lit58;
  static final Char Lit59;
  static final Char Lit6;
  static final SimpleSymbol Lit60;
  static final SimpleSymbol Lit61;
  static final Char Lit62;
  static final PairWithPosition Lit63;
  static final SimpleSymbol Lit64;
  static final Char Lit65;
  static final Char Lit66;
  static final Char Lit67;
  static final SimpleSymbol Lit68;
  static final SimpleSymbol Lit69;
  static final Char Lit7;
  static final SimpleSymbol Lit70;
  static final SimpleSymbol Lit71;
  static final SimpleSymbol Lit72;
  static final IntNum Lit73;
  static final SimpleSymbol Lit74;
  static final SimpleSymbol Lit75;
  static final SimpleSymbol Lit76;
  static final Char Lit77;
  static final Char Lit78;
  static final SimpleSymbol Lit79;
  static final IntNum Lit8;
  static final SimpleSymbol Lit80;
  static final SimpleSymbol Lit81;
  static final SimpleSymbol Lit82;
  static final SimpleSymbol Lit83;
  static final Char Lit84;
  static final SimpleSymbol Lit85;
  static final SimpleSymbol Lit86;
  static final SimpleSymbol Lit87;
  static final SimpleSymbol Lit88;
  static final SimpleSymbol Lit89;
  static final Char Lit9;
  static final SimpleSymbol Lit90;
  static final SimpleSymbol Lit91;
  static final SimpleSymbol Lit92;
  static final SimpleSymbol Lit93;
  static final SimpleSymbol Lit94;
  static final SimpleSymbol Lit95;
  static final Char Lit96;
  static final Char Lit97;
  static final Char Lit98;
  static final SimpleSymbol Lit99;
  static final ModuleMethod lambda$Fn1;
  static final ModuleMethod lambda$Fn10;
  static final ModuleMethod lambda$Fn6;
  static final ModuleMethod lambda$Fn7;
  static final ModuleMethod lambda$Fn8;
  static final ModuleMethod lambda$Fn9;
  public static final ModuleMethod pregexp;
  public static final ModuleMethod pregexp$Mnat$Mnword$Mnboundary$Qu;
  public static final ModuleMethod pregexp$Mnchar$Mnword$Qu;
  public static final ModuleMethod pregexp$Mncheck$Mnif$Mnin$Mnchar$Mnclass$Qu;
  public static final ModuleMethod pregexp$Mnerror;
  public static final ModuleMethod pregexp$Mninvert$Mnchar$Mnlist;
  public static final ModuleMethod pregexp$Mnlist$Mnref;
  public static final ModuleMethod pregexp$Mnmake$Mnbackref$Mnlist;
  public static final ModuleMethod pregexp$Mnmatch;
  public static final ModuleMethod pregexp$Mnmatch$Mnpositions;
  public static final ModuleMethod pregexp$Mnmatch$Mnpositions$Mnaux;
  public static final ModuleMethod pregexp$Mnquote;
  public static final ModuleMethod pregexp$Mnread$Mnbranch;
  public static final ModuleMethod pregexp$Mnread$Mnchar$Mnlist;
  public static final ModuleMethod pregexp$Mnread$Mncluster$Mntype;
  public static final ModuleMethod pregexp$Mnread$Mnescaped$Mnchar;
  public static final ModuleMethod pregexp$Mnread$Mnescaped$Mnnumber;
  public static final ModuleMethod pregexp$Mnread$Mnnums;
  public static final ModuleMethod pregexp$Mnread$Mnpattern;
  public static final ModuleMethod pregexp$Mnread$Mnpiece;
  public static final ModuleMethod pregexp$Mnread$Mnposix$Mnchar$Mnclass;
  public static final ModuleMethod pregexp$Mnread$Mnsubpattern;
  public static final ModuleMethod pregexp$Mnreplace;
  public static final ModuleMethod pregexp$Mnreplace$Mnaux;
  public static final ModuleMethod pregexp$Mnreplace$St;
  public static final ModuleMethod pregexp$Mnreverse$Ex;
  public static final ModuleMethod pregexp$Mnsplit;
  public static final ModuleMethod pregexp$Mnstring$Mnmatch;
  public static final ModuleMethod pregexp$Mnwrap$Mnquantifier$Mnif$Mnany;
  
  static
  {
    Lit134 = (SimpleSymbol)new SimpleSymbol("pregexp-replace*").readResolve();
    Lit133 = (SimpleSymbol)new SimpleSymbol("pregexp-replace").readResolve();
    Lit132 = (SimpleSymbol)new SimpleSymbol("pregexp-split").readResolve();
    Lit131 = (SimpleSymbol)new SimpleSymbol("pregexp-match").readResolve();
    Lit130 = (SimpleSymbol)new SimpleSymbol("pregexp").readResolve();
    Lit129 = (SimpleSymbol)new SimpleSymbol("pregexp-replace-aux").readResolve();
    Lit128 = (SimpleSymbol)new SimpleSymbol("pregexp-make-backref-list").readResolve();
    Lit127 = (SimpleSymbol)new SimpleSymbol("pregexp-list-ref").readResolve();
    Lit126 = (SimpleSymbol)new SimpleSymbol("pregexp-at-word-boundary?").readResolve();
    Lit125 = (SimpleSymbol)new SimpleSymbol("pregexp-char-word?").readResolve();
    Lit124 = (SimpleSymbol)new SimpleSymbol("pregexp-string-match").readResolve();
    Lit123 = (SimpleSymbol)new SimpleSymbol("pregexp-invert-char-list").readResolve();
    Lit122 = (SimpleSymbol)new SimpleSymbol("pregexp-read-escaped-char").readResolve();
    Lit121 = (SimpleSymbol)new SimpleSymbol("pregexp-read-escaped-number").readResolve();
    Lit120 = (SimpleSymbol)new SimpleSymbol("pregexp-read-branch").readResolve();
    Lit119 = (SimpleSymbol)new SimpleSymbol("pregexp-read-pattern").readResolve();
    Lit118 = (SimpleSymbol)new SimpleSymbol("pregexp-error").readResolve();
    Lit117 = (SimpleSymbol)new SimpleSymbol("pregexp-reverse!").readResolve();
    Object localObject1 = Char.make(92);
    Lit19 = (Char)localObject1;
    Object localObject2 = Char.make(46);
    Lit13 = (Char)localObject2;
    Object localObject3 = Char.make(63);
    Lit47 = (Char)localObject3;
    Object localObject4 = Char.make(42);
    Lit65 = (Char)localObject4;
    Object localObject5 = Char.make(43);
    Lit66 = (Char)localObject5;
    Char localChar1 = Char.make(124);
    Lit7 = localChar1;
    Char localChar2 = Char.make(94);
    Lit9 = localChar2;
    Char localChar3 = Char.make(36);
    Lit11 = localChar3;
    Char localChar4 = Char.make(91);
    Lit15 = localChar4;
    Char localChar5 = Char.make(93);
    Lit46 = localChar5;
    Char localChar6 = Char.make(123);
    Lit67 = localChar6;
    Char localChar7 = Char.make(125);
    Lit78 = localChar7;
    Char localChar8 = Char.make(40);
    Lit18 = localChar8;
    Char localChar9 = Char.make(41);
    Lit6 = localChar9;
    Lit116 = PairWithPosition.make(localObject1, PairWithPosition.make(localObject2, PairWithPosition.make(localObject3, PairWithPosition.make(localObject4, PairWithPosition.make(localObject5, PairWithPosition.make(localChar1, PairWithPosition.make(localChar2, PairWithPosition.make(localChar3, PairWithPosition.make(localChar4, PairWithPosition.make(localChar5, PairWithPosition.make(localChar6, PairWithPosition.make(localChar7, PairWithPosition.make(localChar8, PairWithPosition.make(localChar9, LList.Empty, "pregexp.scm", 3153977), "pregexp.scm", 3153973), "pregexp.scm", 3153969), "pregexp.scm", 3153965), "pregexp.scm", 3153961), "pregexp.scm", 3153957), "pregexp.scm", 3149885), "pregexp.scm", 3149881), "pregexp.scm", 3149877), "pregexp.scm", 3149873), "pregexp.scm", 3149869), "pregexp.scm", 3149865), "pregexp.scm", 3149861), "pregexp.scm", 3149856);
    Lit115 = (SimpleSymbol)new SimpleSymbol("pattern-must-be-compiled-or-string-regexp").readResolve();
    Lit114 = (SimpleSymbol)new SimpleSymbol("pregexp-match-positions").readResolve();
    Lit113 = Char.make(38);
    Lit112 = (SimpleSymbol)new SimpleSymbol("identity").readResolve();
    Lit111 = (SimpleSymbol)new SimpleSymbol("fk").readResolve();
    Lit110 = (SimpleSymbol)new SimpleSymbol("greedy-quantifier-operand-could-be-empty").readResolve();
    Lit109 = (SimpleSymbol)new SimpleSymbol(":no-backtrack").readResolve();
    localObject1 = (SimpleSymbol)new SimpleSymbol(":between").readResolve();
    Lit68 = (SimpleSymbol)localObject1;
    localObject2 = Boolean.FALSE;
    localObject3 = IntNum.make(0);
    Lit73 = (IntNum)localObject3;
    localObject4 = Boolean.FALSE;
    localObject5 = (SimpleSymbol)new SimpleSymbol(":any").readResolve();
    Lit14 = (SimpleSymbol)localObject5;
    Lit108 = PairWithPosition.make(localObject1, PairWithPosition.make(localObject2, PairWithPosition.make(localObject3, PairWithPosition.make(localObject4, PairWithPosition.make(localObject5, LList.Empty, "pregexp.scm", 2338881), "pregexp.scm", 2338878), "pregexp.scm", 2338876), "pregexp.scm", 2338873), "pregexp.scm", 2338863);
    Lit107 = (SimpleSymbol)new SimpleSymbol(":neg-lookbehind").readResolve();
    Lit106 = PairWithPosition.make(Lit68, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make(Lit73, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make(Lit14, LList.Empty, "pregexp.scm", 2302017), "pregexp.scm", 2302014), "pregexp.scm", 2302012), "pregexp.scm", 2302009), "pregexp.scm", 2301999);
    Lit105 = (SimpleSymbol)new SimpleSymbol(":lookbehind").readResolve();
    Lit104 = (SimpleSymbol)new SimpleSymbol(":neg-lookahead").readResolve();
    Lit103 = (SimpleSymbol)new SimpleSymbol(":lookahead").readResolve();
    Lit102 = (SimpleSymbol)new SimpleSymbol("non-existent-backref").readResolve();
    Lit101 = (SimpleSymbol)new SimpleSymbol("pregexp-match-positions-aux").readResolve();
    Lit100 = (SimpleSymbol)new SimpleSymbol(":sub").readResolve();
    Lit99 = (SimpleSymbol)new SimpleSymbol("pregexp-check-if-in-char-class?").readResolve();
    Lit98 = Char.make(102);
    Lit97 = Char.make(101);
    Lit96 = Char.make(99);
    Lit95 = (SimpleSymbol)new SimpleSymbol(":xdigit").readResolve();
    Lit94 = (SimpleSymbol)new SimpleSymbol(":upper").readResolve();
    Lit93 = (SimpleSymbol)new SimpleSymbol(":punct").readResolve();
    Lit92 = (SimpleSymbol)new SimpleSymbol(":print").readResolve();
    Lit91 = (SimpleSymbol)new SimpleSymbol(":lower").readResolve();
    Lit90 = (SimpleSymbol)new SimpleSymbol(":graph").readResolve();
    Lit89 = (SimpleSymbol)new SimpleSymbol(":cntrl").readResolve();
    Lit88 = (SimpleSymbol)new SimpleSymbol(":blank").readResolve();
    Lit87 = (SimpleSymbol)new SimpleSymbol(":ascii").readResolve();
    Lit86 = (SimpleSymbol)new SimpleSymbol(":alpha").readResolve();
    Lit85 = (SimpleSymbol)new SimpleSymbol(":alnum").readResolve();
    Lit84 = Char.make(95);
    Lit83 = (SimpleSymbol)new SimpleSymbol(":char-range").readResolve();
    Lit82 = (SimpleSymbol)new SimpleSymbol(":one-of-chars").readResolve();
    Lit81 = (SimpleSymbol)new SimpleSymbol("character-class-ended-too-soon").readResolve();
    Lit80 = (SimpleSymbol)new SimpleSymbol("pregexp-read-char-list").readResolve();
    Lit79 = (SimpleSymbol)new SimpleSymbol(":none-of-chars").readResolve();
    Lit77 = Char.make(44);
    Lit76 = (SimpleSymbol)new SimpleSymbol("pregexp-read-nums").readResolve();
    Lit75 = (SimpleSymbol)new SimpleSymbol("left-brace-must-be-followed-by-number").readResolve();
    Lit74 = (SimpleSymbol)new SimpleSymbol("pregexp-wrap-quantifier-if-any").readResolve();
    Lit72 = (SimpleSymbol)new SimpleSymbol("next-i").readResolve();
    Lit71 = (SimpleSymbol)new SimpleSymbol("at-most").readResolve();
    Lit70 = (SimpleSymbol)new SimpleSymbol("at-least").readResolve();
    Lit69 = (SimpleSymbol)new SimpleSymbol("minimal?").readResolve();
    Lit64 = (SimpleSymbol)new SimpleSymbol("pregexp-read-subpattern").readResolve();
    Lit63 = PairWithPosition.make(Lit100, LList.Empty, "pregexp.scm", 942102);
    Lit62 = Char.make(120);
    Lit61 = (SimpleSymbol)new SimpleSymbol(":case-insensitive").readResolve();
    Lit60 = (SimpleSymbol)new SimpleSymbol(":case-sensitive").readResolve();
    Lit59 = Char.make(105);
    Lit58 = Char.make(45);
    Lit57 = (SimpleSymbol)new SimpleSymbol("pregexp-read-cluster-type").readResolve();
    Lit56 = PairWithPosition.make(Lit107, LList.Empty, "pregexp.scm", 876575);
    Lit55 = PairWithPosition.make(Lit105, LList.Empty, "pregexp.scm", 872479);
    Lit54 = Char.make(60);
    Lit53 = PairWithPosition.make(Lit109, LList.Empty, "pregexp.scm", 860188);
    Lit52 = Char.make(62);
    Lit51 = PairWithPosition.make(Lit104, LList.Empty, "pregexp.scm", 856092);
    Lit50 = Char.make(33);
    Lit49 = PairWithPosition.make(Lit103, LList.Empty, "pregexp.scm", 851996);
    Lit48 = Char.make(61);
    Lit45 = (SimpleSymbol)new SimpleSymbol("pregexp-read-posix-char-class").readResolve();
    Lit44 = Char.make(58);
    localObject1 = (SimpleSymbol)new SimpleSymbol(":neg-char").readResolve();
    Lit17 = (SimpleSymbol)localObject1;
    localObject2 = (SimpleSymbol)new SimpleSymbol(":word").readResolve();
    Lit41 = (SimpleSymbol)localObject2;
    Lit43 = PairWithPosition.make(localObject1, PairWithPosition.make(localObject2, LList.Empty, "pregexp.scm", 696359), "pregexp.scm", 696348);
    Lit42 = Char.make(87);
    Lit40 = Char.make(119);
    Lit39 = Char.make(116);
    localObject1 = Lit17;
    localObject2 = (SimpleSymbol)new SimpleSymbol(":space").readResolve();
    Lit36 = (SimpleSymbol)localObject2;
    Lit38 = PairWithPosition.make(localObject1, PairWithPosition.make(localObject2, LList.Empty, "pregexp.scm", 684071), "pregexp.scm", 684060);
    Lit37 = Char.make(83);
    Lit35 = Char.make(115);
    Lit34 = Char.make(114);
    Lit33 = Char.make(110);
    localObject1 = Lit17;
    localObject2 = (SimpleSymbol)new SimpleSymbol(":digit").readResolve();
    Lit30 = (SimpleSymbol)localObject2;
    Lit32 = PairWithPosition.make(localObject1, PairWithPosition.make(localObject2, LList.Empty, "pregexp.scm", 667687), "pregexp.scm", 667676);
    Lit31 = Char.make(68);
    Lit29 = Char.make(100);
    Lit28 = (SimpleSymbol)new SimpleSymbol(":not-wbdry").readResolve();
    Lit27 = Char.make(66);
    Lit26 = (SimpleSymbol)new SimpleSymbol(":wbdry").readResolve();
    Lit25 = Char.make(98);
    Lit24 = Char.make(10);
    Lit23 = (SimpleSymbol)new SimpleSymbol(":empty").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("backslash").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("pregexp-read-piece").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol(":backref").readResolve();
    Lit16 = IntNum.make(2);
    Lit12 = (SimpleSymbol)new SimpleSymbol(":eos").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol(":bos").readResolve();
    Lit8 = IntNum.make(1);
    Lit5 = (SimpleSymbol)new SimpleSymbol(":seq").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol(":or").readResolve();
    Lit3 = Char.make(32);
    Lit2 = Char.make(97);
    Lit1 = Char.make(59);
    Lit0 = IntNum.make(20050502);
    $instance = new pregexp();
    localObject1 = $instance;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 16, Lit117, 4097);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:47");
    pregexp$Mnreverse$Ex = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 17, Lit118, 61440);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:57");
    pregexp$Mnerror = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 18, Lit119, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:65");
    pregexp$Mnread$Mnpattern = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 19, Lit120, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:79");
    pregexp$Mnread$Mnbranch = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 20, Lit21, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:91");
    pregexp$Mnread$Mnpiece = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 21, Lit121, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:138");
    pregexp$Mnread$Mnescaped$Mnnumber = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 22, Lit122, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:155");
    pregexp$Mnread$Mnescaped$Mnchar = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 23, Lit45, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:174");
    pregexp$Mnread$Mnposix$Mnchar$Mnclass = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 24, Lit57, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:200");
    pregexp$Mnread$Mncluster$Mntype = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 25, Lit64, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:233");
    pregexp$Mnread$Mnsubpattern = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 26, Lit74, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:254");
    pregexp$Mnwrap$Mnquantifier$Mnif$Mnany = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 27, Lit76, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:300");
    pregexp$Mnread$Mnnums = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 28, Lit123, 4097);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:323");
    pregexp$Mninvert$Mnchar$Mnlist = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 29, Lit80, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:330");
    pregexp$Mnread$Mnchar$Mnlist = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 30, Lit124, 24582);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:368");
    pregexp$Mnstring$Mnmatch = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 31, Lit125, 4097);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:379");
    pregexp$Mnchar$Mnword$Qu = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 32, Lit126, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:387");
    pregexp$Mnat$Mnword$Mnboundary$Qu = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 33, Lit99, 8194);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:399");
    pregexp$Mncheck$Mnif$Mnin$Mnchar$Mnclass$Qu = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 34, Lit127, 8194);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:429");
    pregexp$Mnlist$Mnref = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 35, Lit128, 4097);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:448");
    pregexp$Mnmake$Mnbackref$Mnlist = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 36, null, 0);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:463");
    lambda$Fn1 = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 37, null, 0);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:551");
    lambda$Fn6 = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 38, null, 0);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:556");
    lambda$Fn7 = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 39, null, 0);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:564");
    lambda$Fn8 = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 40, null, 0);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:573");
    lambda$Fn9 = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 41, null, 0);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:578");
    lambda$Fn10 = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 42, Lit101, 24582);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:459");
    pregexp$Mnmatch$Mnpositions$Mnaux = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 43, Lit129, 16388);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:639");
    pregexp$Mnreplace$Mnaux = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 44, Lit130, 4097);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:665");
    pregexp = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 45, Lit114, 61442);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:670");
    pregexp$Mnmatch$Mnpositions = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 46, Lit131, 61442);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:690");
    pregexp$Mnmatch = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 47, Lit132, 8194);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:700");
    pregexp$Mnsplit = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 48, Lit133, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:723");
    pregexp$Mnreplace = (ModuleMethod)localObject2;
    localObject2 = new ModuleMethod((ModuleBody)localObject1, 49, Lit134, 12291);
    ((PropertySet)localObject2).setProperty("source-location", "pregexp.scm:736");
    pregexp$Mnreplace$St = (ModuleMethod)localObject2;
    localObject1 = new ModuleMethod((ModuleBody)localObject1, 50, Lit135, 4097);
    ((PropertySet)localObject1).setProperty("source-location", "pregexp.scm:764");
    pregexp$Mnquote = (ModuleMethod)localObject1;
    $instance.run();
  }
  
  public pregexp()
  {
    ModuleInfo.register(this);
  }
  
  /* Error */
  public static Object isPregexpAtWordBoundary(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    // Byte code:
    //   0: getstatic 892	kawa/standard/Scheme:numEqu	Lgnu/kawa/functions/NumberCompare;
    //   3: aload_1
    //   4: getstatic 403	gnu/kawa/slib/pregexp:Lit73	Lgnu/math/IntNum;
    //   7: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   10: astore_3
    //   11: aload_3
    //   12: checkcast 392	java/lang/Boolean
    //   15: invokevirtual 902	java/lang/Boolean:booleanValue	()Z
    //   18: istore 6
    //   20: iload 6
    //   22: ifeq +16 -> 38
    //   25: iload 6
    //   27: ifeq +7 -> 34
    //   30: getstatic 905	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   33: areturn
    //   34: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   37: areturn
    //   38: getstatic 908	kawa/standard/Scheme:numGEq	Lgnu/kawa/functions/NumberCompare;
    //   41: aload_1
    //   42: aload_2
    //   43: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   46: astore_2
    //   47: aload_2
    //   48: checkcast 392	java/lang/Boolean
    //   51: invokevirtual 902	java/lang/Boolean:booleanValue	()Z
    //   54: istore 6
    //   56: iload 6
    //   58: ifeq +16 -> 74
    //   61: iload 6
    //   63: ifeq +7 -> 70
    //   66: getstatic 905	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   69: areturn
    //   70: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   73: areturn
    //   74: aload_0
    //   75: checkcast 910	java/lang/CharSequence
    //   78: astore_2
    //   79: aload_1
    //   80: checkcast 912	java/lang/Number
    //   83: invokevirtual 916	java/lang/Number:intValue	()I
    //   86: istore 4
    //   88: aload_2
    //   89: iload 4
    //   91: invokestatic 922	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
    //   94: istore 4
    //   96: aload_0
    //   97: checkcast 910	java/lang/CharSequence
    //   100: astore_2
    //   101: getstatic 928	gnu/kawa/functions/AddOp:$Mn	Lgnu/kawa/functions/AddOp;
    //   104: aload_1
    //   105: getstatic 697	gnu/kawa/slib/pregexp:Lit8	Lgnu/math/IntNum;
    //   108: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   111: astore_0
    //   112: aload_0
    //   113: checkcast 912	java/lang/Number
    //   116: invokevirtual 916	java/lang/Number:intValue	()I
    //   119: istore 5
    //   121: aload_2
    //   122: iload 5
    //   124: invokestatic 922	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
    //   127: istore 5
    //   129: iload 4
    //   131: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   134: getstatic 617	gnu/kawa/slib/pregexp:Lit41	Lgnu/mapping/SimpleSymbol;
    //   137: invokestatic 931	gnu/kawa/slib/pregexp:isPregexpCheckIfInCharClass	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   140: astore_1
    //   141: iload 5
    //   143: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   146: getstatic 617	gnu/kawa/slib/pregexp:Lit41	Lgnu/mapping/SimpleSymbol;
    //   149: invokestatic 931	gnu/kawa/slib/pregexp:isPregexpCheckIfInCharClass	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   152: astore_2
    //   153: aload_1
    //   154: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   157: if_acmpeq +30 -> 187
    //   160: aload_2
    //   161: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   164: if_acmpeq +16 -> 180
    //   167: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   170: astore_0
    //   171: aload_0
    //   172: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   175: if_acmpeq +17 -> 192
    //   178: aload_0
    //   179: areturn
    //   180: getstatic 905	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   183: astore_0
    //   184: goto -13 -> 171
    //   187: aload_1
    //   188: astore_0
    //   189: goto -18 -> 171
    //   192: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   195: astore_0
    //   196: aload_1
    //   197: aload_0
    //   198: if_acmpeq +21 -> 219
    //   201: iconst_1
    //   202: istore 4
    //   204: iload 4
    //   206: iconst_1
    //   207: iadd
    //   208: iconst_1
    //   209: iand
    //   210: istore 4
    //   212: iload 4
    //   214: ifeq +11 -> 225
    //   217: aload_2
    //   218: areturn
    //   219: iconst_0
    //   220: istore 4
    //   222: goto -18 -> 204
    //   225: iload 4
    //   227: ifeq +7 -> 234
    //   230: getstatic 905	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   233: areturn
    //   234: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   237: areturn
    //   238: astore_0
    //   239: new 933	gnu/mapping/WrongType
    //   242: dup
    //   243: aload_0
    //   244: ldc_w 935
    //   247: bipush -2
    //   249: aload_3
    //   250: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   253: athrow
    //   254: astore_0
    //   255: new 933	gnu/mapping/WrongType
    //   258: dup
    //   259: aload_0
    //   260: ldc_w 935
    //   263: bipush -2
    //   265: aload_2
    //   266: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   269: athrow
    //   270: astore_1
    //   271: new 933	gnu/mapping/WrongType
    //   274: dup
    //   275: aload_1
    //   276: ldc_w 940
    //   279: iconst_1
    //   280: aload_0
    //   281: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   284: athrow
    //   285: astore_0
    //   286: new 933	gnu/mapping/WrongType
    //   289: dup
    //   290: aload_0
    //   291: ldc_w 940
    //   294: iconst_2
    //   295: aload_1
    //   296: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   299: athrow
    //   300: astore_1
    //   301: new 933	gnu/mapping/WrongType
    //   304: dup
    //   305: aload_1
    //   306: ldc_w 940
    //   309: iconst_1
    //   310: aload_0
    //   311: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   314: athrow
    //   315: astore_1
    //   316: new 933	gnu/mapping/WrongType
    //   319: dup
    //   320: aload_1
    //   321: ldc_w 940
    //   324: iconst_2
    //   325: aload_0
    //   326: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   329: athrow
    //   330: astore_0
    //   331: new 933	gnu/mapping/WrongType
    //   334: dup
    //   335: aload_0
    //   336: ldc_w 935
    //   339: bipush -2
    //   341: aload_1
    //   342: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   345: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	346	0	paramObject1	Object
    //   0	346	1	paramObject2	Object
    //   0	346	2	paramObject3	Object
    //   10	240	3	localObject	Object
    //   86	140	4	i	int
    //   119	23	5	j	int
    //   18	44	6	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   11	20	238	java/lang/ClassCastException
    //   47	56	254	java/lang/ClassCastException
    //   74	79	270	java/lang/ClassCastException
    //   79	88	285	java/lang/ClassCastException
    //   96	101	300	java/lang/ClassCastException
    //   112	121	315	java/lang/ClassCastException
    //   192	196	330	java/lang/ClassCastException
  }
  
  public static boolean isPregexpCharWord(Object paramObject)
  {
    try
    {
      localChar = (Char)paramObject;
      bool1 = unicode.isCharAlphabetic(localChar);
      if (!bool1) {
        break label16;
      }
    }
    catch (ClassCastException localClassCastException1)
    {
      try
      {
        label16:
        boolean bool2;
        do
        {
          localChar = (Char)paramObject;
          bool2 = unicode.isCharNumeric(localChar);
          boolean bool1 = bool2;
        } while (bool2);
      }
      catch (ClassCastException localClassCastException2)
      {
        Char localChar;
        throw new WrongType(localClassCastException2, "char-numeric?", 1, paramObject);
      }
      try
      {
        localChar = (Char)paramObject;
        return characters.isChar$Eq(localChar, Lit84);
      }
      catch (ClassCastException localClassCastException3)
      {
        throw new WrongType(localClassCastException3, "char=?", 1, paramObject);
      }
      localClassCastException1 = localClassCastException1;
      throw new WrongType(localClassCastException1, "char-alphabetic?", 1, paramObject);
    }
    return bool1;
  }
  
  public static Object isPregexpCheckIfInCharClass(Object paramObject1, Object paramObject2)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public static Object lambda1sub(Object paramObject)
  {
    if (lists.isPair(paramObject))
    {
      Object localObject1 = lists.car.apply1(paramObject);
      Object localObject2 = lambda1sub(lists.cdr.apply1(paramObject));
      if (Scheme.isEqv.apply2(localObject1, Lit100) != Boolean.FALSE) {
        return lists.cons(lists.cons(paramObject, Boolean.FALSE), localObject2);
      }
      return append.append$V(new Object[] { lambda1sub(localObject1), localObject2 });
    }
    return LList.Empty;
  }
  
  public static Pair pregexp(Object paramObject)
  {
    $Stpregexp$Mnspace$Mnsensitive$Qu$St = Boolean.TRUE;
    SimpleSymbol localSimpleSymbol = Lit100;
    GenericProc localGenericProc = lists.car;
    IntNum localIntNum = Lit73;
    try
    {
      CharSequence localCharSequence = (CharSequence)paramObject;
      return LList.list2(localSimpleSymbol, localGenericProc.apply1(pregexpReadPattern(paramObject, localIntNum, Integer.valueOf(strings.stringLength(localCharSequence)))));
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "string-length", 1, paramObject);
    }
  }
  
  public static Object pregexpError$V(Object[] paramArrayOfObject)
  {
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    ports.display("Error:");
    for (;;)
    {
      if (paramArrayOfObject == LList.Empty)
      {
        ports.newline();
        return misc.error$V("pregexp-error", new Object[0]);
      }
      try
      {
        Pair localPair = (Pair)paramArrayOfObject;
        paramArrayOfObject = localPair.getCar();
        ports.display(Lit3);
        ports.write(paramArrayOfObject);
        paramArrayOfObject = localPair.getCdr();
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "arg0", -2, paramArrayOfObject);
      }
    }
  }
  
  public static Object pregexpInvertCharList(Object paramObject)
  {
    Object localObject = lists.car.apply1(paramObject);
    try
    {
      Pair localPair = (Pair)localObject;
      lists.setCar$Ex(localPair, Lit79);
      return paramObject;
    }
    catch (ClassCastException paramObject)
    {
      throw new WrongType((ClassCastException)paramObject, "set-car!", 1, localObject);
    }
  }
  
  public static Object pregexpListRef(Object paramObject1, Object paramObject2)
  {
    IntNum localIntNum = Lit73;
    Object localObject = paramObject1;
    for (paramObject1 = localIntNum;; paramObject1 = AddOp.$Pl.apply2(paramObject1, Lit8))
    {
      if (lists.isNull(localObject)) {
        return Boolean.FALSE;
      }
      if (Scheme.numEqu.apply2(paramObject1, paramObject2) != Boolean.FALSE) {
        return lists.car.apply1(localObject);
      }
      localObject = lists.cdr.apply1(localObject);
    }
  }
  
  public static Object pregexpMakeBackrefList(Object paramObject)
  {
    return lambda1sub(paramObject);
  }
  
  public static Object pregexpMatch$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
  {
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    paramObject1 = Scheme.apply.apply4(pregexp$Mnmatch$Mnpositions, paramObject1, paramObject2, paramArrayOfObject);
    paramArrayOfObject = (Object[])paramObject1;
    Object localObject1;
    if (paramObject1 != Boolean.FALSE)
    {
      localObject1 = LList.Empty;
      paramArrayOfObject = (Object[])paramObject1;
      paramObject1 = localObject1;
    }
    for (;;)
    {
      if (paramArrayOfObject == LList.Empty)
      {
        paramArrayOfObject = LList.reverseInPlace(paramObject1);
        return paramArrayOfObject;
      }
      try
      {
        localObject2 = (Pair)paramArrayOfObject;
        localObject1 = ((Pair)localObject2).getCdr();
        localObject2 = ((Pair)localObject2).getCar();
        paramArrayOfObject = (Object[])localObject2;
        if (localObject2 == Boolean.FALSE) {}
      }
      catch (ClassCastException paramObject1)
      {
        try
        {
          paramArrayOfObject = (CharSequence)paramObject2;
          localObject3 = lists.car.apply1(localObject2);
        }
        catch (ClassCastException paramObject1)
        {
          int i;
          int j;
          throw new WrongType((ClassCastException)paramObject1, "substring", 1, paramObject2);
        }
        try
        {
          i = ((Number)localObject3).intValue();
          localObject2 = lists.cdr.apply1(localObject2);
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 2, localObject3);
        }
        try
        {
          j = ((Number)localObject2).intValue();
          paramArrayOfObject = strings.substring(paramArrayOfObject, i, j);
          paramObject1 = Pair.make(paramArrayOfObject, paramObject1);
          paramArrayOfObject = (Object[])localObject1;
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 3, localObject2);
        }
        paramObject1 = paramObject1;
        throw new WrongType((ClassCastException)paramObject1, "arg0", -2, paramArrayOfObject);
      }
    }
  }
  
  public static Object pregexpMatchPositions$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
  {
    Object localObject1 = LList.makeList(paramArrayOfObject, 0);
    if (strings.isString(paramObject1)) {
      paramArrayOfObject = pregexp(paramObject1);
    }
    try
    {
      paramObject1 = (CharSequence)paramObject2;
      i = strings.stringLength((CharSequence)paramObject1);
      if (lists.isNull(localObject1))
      {
        paramObject1 = Lit73;
        if (!lists.isNull(localObject1)) {
          break label173;
        }
        localObject1 = Integer.valueOf(i);
        localObject2 = paramObject1;
        localObject3 = Scheme.numLEq.apply2(localObject2, localObject1);
      }
    }
    catch (ClassCastException paramObject1)
    {
      try
      {
        boolean bool;
        for (;;)
        {
          int i;
          bool = ((Boolean)localObject3).booleanValue();
          if (!bool) {
            break;
          }
          localObject3 = pregexpMatchPositionsAux(paramArrayOfObject, paramObject2, Integer.valueOf(i), paramObject1, localObject1, localObject2);
          if (localObject3 != Boolean.FALSE)
          {
            return localObject3;
            paramArrayOfObject = (Object[])paramObject1;
            if (lists.isPair(paramObject1)) {
              continue;
            }
            pregexpError$V(new Object[] { Lit114, Lit115, paramObject1 });
            paramArrayOfObject = (Object[])paramObject1;
            continue;
            paramObject1 = lists.car.apply1(localObject1);
            localObject2 = lists.cdr.apply1(localObject1);
          }
          try
          {
            localObject1 = (LList)localObject2;
          }
          catch (ClassCastException paramObject1)
          {
            label173:
            throw new WrongType((ClassCastException)paramObject1, "opt-args", -2, localObject2);
          }
          localObject1 = lists.car.apply1(localObject1);
          continue;
          localObject2 = AddOp.$Pl.apply2(localObject2, Lit8);
        }
        if (bool) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramObject1)
      {
        Object localObject2;
        Object localObject3;
        throw new WrongType((ClassCastException)paramObject1, "x", -2, localObject3);
      }
      paramObject1 = paramObject1;
      throw new WrongType((ClassCastException)paramObject1, "string-length", 1, paramObject2);
    }
  }
  
  public static Object pregexpMatchPositionsAux(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6)
  {
    frame localframe = new frame();
    localframe.s = paramObject2;
    localframe.sn = paramObject3;
    localframe.start = paramObject4;
    localframe.n = paramObject5;
    paramObject2 = localframe.identity;
    paramObject3 = pregexpMakeBackrefList(paramObject1);
    localframe.case$Mnsensitive$Qu = Boolean.TRUE;
    localframe.backrefs = paramObject3;
    localframe.identity = ((Procedure)paramObject2);
    localframe.lambda3sub(paramObject1, paramObject6, localframe.identity, lambda$Fn1);
    paramObject1 = localframe.backrefs;
    paramObject2 = LList.Empty;
    for (;;)
    {
      if (paramObject1 == LList.Empty)
      {
        paramObject1 = LList.reverseInPlace(paramObject2);
        paramObject2 = lists.car.apply1(paramObject1);
        if (paramObject2 == Boolean.FALSE) {
          break label149;
        }
        return paramObject1;
      }
      try
      {
        paramObject3 = (Pair)paramObject1;
        paramObject1 = ((Pair)paramObject3).getCdr();
        paramObject2 = Pair.make(lists.cdr.apply1(((Pair)paramObject3).getCar()), paramObject2);
      }
      catch (ClassCastException paramObject2)
      {
        label149:
        throw new WrongType((ClassCastException)paramObject2, "arg0", -2, paramObject1);
      }
    }
    return paramObject2;
  }
  
  /* Error */
  public static Object pregexpQuote(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: checkcast 910	java/lang/CharSequence
    //   4: astore_1
    //   5: aload_1
    //   6: invokestatic 1012	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
    //   9: iconst_1
    //   10: isub
    //   11: invokestatic 1018	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   14: astore_2
    //   15: getstatic 337	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   18: astore_1
    //   19: getstatic 1144	kawa/standard/Scheme:numLss	Lgnu/kawa/functions/NumberCompare;
    //   22: aload_2
    //   23: getstatic 403	gnu/kawa/slib/pregexp:Lit73	Lgnu/math/IntNum;
    //   26: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   29: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   32: if_acmpeq +13 -> 45
    //   35: aload_1
    //   36: checkcast 333	gnu/lists/LList
    //   39: astore_0
    //   40: aload_0
    //   41: invokestatic 1148	kawa/lib/strings:list$To$String	(Lgnu/lists/LList;)Ljava/lang/CharSequence;
    //   44: areturn
    //   45: getstatic 928	gnu/kawa/functions/AddOp:$Mn	Lgnu/kawa/functions/AddOp;
    //   48: aload_2
    //   49: getstatic 697	gnu/kawa/slib/pregexp:Lit8	Lgnu/math/IntNum;
    //   52: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   55: astore_3
    //   56: aload_0
    //   57: checkcast 910	java/lang/CharSequence
    //   60: astore 4
    //   62: aload_2
    //   63: checkcast 912	java/lang/Number
    //   66: invokevirtual 916	java/lang/Number:intValue	()I
    //   69: istore 5
    //   71: aload 4
    //   73: iload 5
    //   75: invokestatic 922	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
    //   78: istore 5
    //   80: iload 5
    //   82: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   85: getstatic 360	gnu/kawa/slib/pregexp:Lit116	Lgnu/lists/PairWithPosition;
    //   88: invokestatic 1151	kawa/lib/lists:memv	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   91: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   94: if_acmpeq +24 -> 118
    //   97: getstatic 305	gnu/kawa/slib/pregexp:Lit19	Lgnu/text/Char;
    //   100: iload 5
    //   102: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   105: aload_1
    //   106: invokestatic 997	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   109: invokestatic 997	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   112: astore_1
    //   113: aload_3
    //   114: astore_2
    //   115: goto -96 -> 19
    //   118: iload 5
    //   120: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   123: aload_1
    //   124: invokestatic 997	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   127: astore_1
    //   128: goto -15 -> 113
    //   131: astore_1
    //   132: new 933	gnu/mapping/WrongType
    //   135: dup
    //   136: aload_1
    //   137: ldc_w 1026
    //   140: iconst_1
    //   141: aload_0
    //   142: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   145: athrow
    //   146: astore_0
    //   147: new 933	gnu/mapping/WrongType
    //   150: dup
    //   151: aload_0
    //   152: ldc_w 1153
    //   155: iconst_1
    //   156: aload_1
    //   157: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   160: athrow
    //   161: astore_1
    //   162: new 933	gnu/mapping/WrongType
    //   165: dup
    //   166: aload_1
    //   167: ldc_w 940
    //   170: iconst_1
    //   171: aload_0
    //   172: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   175: athrow
    //   176: astore_0
    //   177: new 933	gnu/mapping/WrongType
    //   180: dup
    //   181: aload_0
    //   182: ldc_w 940
    //   185: iconst_2
    //   186: aload_2
    //   187: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   190: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	191	0	paramObject	Object
    //   4	124	1	localObject1	Object
    //   131	26	1	localClassCastException1	ClassCastException
    //   161	6	1	localClassCastException2	ClassCastException
    //   14	173	2	localObject2	Object
    //   55	59	3	localObject3	Object
    //   60	12	4	localCharSequence	CharSequence
    //   69	50	5	i	int
    // Exception table:
    //   from	to	target	type
    //   0	5	131	java/lang/ClassCastException
    //   35	40	146	java/lang/ClassCastException
    //   56	62	161	java/lang/ClassCastException
    //   62	71	176	java/lang/ClassCastException
  }
  
  public static Object pregexpReadBranch(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Object localObject2 = LList.Empty;
    localObject1 = paramObject2;
    paramObject2 = localObject2;
    for (;;)
    {
      if (Scheme.numGEq.apply2(localObject1, paramObject3) != Boolean.FALSE) {
        return LList.list2(lists.cons(Lit5, pregexpReverse$Ex(paramObject2)), localObject1);
      }
      try
      {
        localObject2 = (CharSequence)paramObject1;
      }
      catch (ClassCastException paramObject2)
      {
        try
        {
          int i = ((Number)localObject1).intValue();
          i = strings.stringRef((CharSequence)localObject2, i);
          boolean bool = characters.isChar$Eq(Char.make(i), Lit7);
          if (bool)
          {
            if (!bool) {}
          }
          else {
            while (characters.isChar$Eq(Char.make(i), Lit6)) {
              return LList.list2(lists.cons(Lit5, pregexpReverse$Ex(paramObject2)), localObject1);
            }
          }
          localObject1 = pregexpReadPiece(paramObject1, localObject1, paramObject3);
          paramObject2 = lists.cons(lists.car.apply1(localObject1), paramObject2);
          localObject1 = lists.cadr.apply1(localObject1);
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject1);
        }
        paramObject2 = paramObject2;
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
      }
    }
  }
  
  public static Object pregexpReadCharList(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    localObject2 = LList.Empty;
    localObject1 = paramObject2;
    paramObject2 = localObject2;
    for (;;)
    {
      if (Scheme.numGEq.apply2(localObject1, paramObject3) != Boolean.FALSE) {
        return pregexpError$V(new Object[] { Lit80, Lit81 });
      }
      for (;;)
      {
        try
        {
          localObject2 = (CharSequence)paramObject1;
        }
        catch (ClassCastException paramObject2)
        {
          int i;
          boolean bool;
          int j;
          SimpleSymbol localSimpleSymbol;
          CharSequence localCharSequence;
          throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
        }
        try
        {
          i = ((Number)localObject1).intValue();
          i = strings.stringRef((CharSequence)localObject2, i);
          if (Scheme.isEqv.apply2(Char.make(i), Lit46) != Boolean.FALSE)
          {
            if (lists.isNull(paramObject2))
            {
              paramObject2 = lists.cons(Char.make(i), paramObject2);
              localObject1 = AddOp.$Pl.apply2(localObject1, Lit8);
              break;
            }
            return LList.list2(lists.cons(Lit82, pregexpReverse$Ex(paramObject2)), AddOp.$Pl.apply2(localObject1, Lit8));
          }
          if (Scheme.isEqv.apply2(Char.make(i), Lit19) != Boolean.FALSE)
          {
            localObject1 = pregexpReadEscapedChar(paramObject1, localObject1, paramObject3);
            if (localObject1 != Boolean.FALSE)
            {
              paramObject2 = lists.cons(lists.car.apply1(localObject1), paramObject2);
              localObject1 = lists.cadr.apply1(localObject1);
              break;
            }
            return pregexpError$V(new Object[] { Lit80, Lit22 });
          }
          if (Scheme.isEqv.apply2(Char.make(i), Lit58) != Boolean.FALSE)
          {
            bool = lists.isNull(paramObject2);
            if (bool)
            {
              if (!bool) {
                continue;
              }
              paramObject2 = lists.cons(Char.make(i), paramObject2);
              localObject1 = AddOp.$Pl.apply2(localObject1, Lit8);
              break;
            }
            localObject2 = AddOp.$Pl.apply2(localObject1, Lit8);
            localObject3 = Scheme.numLss.apply2(localObject2, paramObject3);
          }
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject1);
        }
        try
        {
          bool = ((Boolean)localObject3).booleanValue();
          if (!bool) {}
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "x", -2, localObject3);
        }
        try
        {
          localObject3 = (CharSequence)paramObject1;
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
        }
        try
        {
          j = ((Number)localObject2).intValue();
          if (characters.isChar$Eq(Char.make(strings.stringRef((CharSequence)localObject3, j)), Lit46)) {
            continue;
          }
          localObject3 = lists.car.apply1(paramObject2);
          if (characters.isChar(localObject3)) {
            localSimpleSymbol = Lit83;
          }
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject2);
        }
        try
        {
          localCharSequence = (CharSequence)paramObject1;
          localObject2 = AddOp.$Pl.apply2(localObject1, Lit8);
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
        }
        try
        {
          i = ((Number)localObject2).intValue();
          paramObject2 = lists.cons(LList.list3(localSimpleSymbol, localObject3, Char.make(strings.stringRef(localCharSequence, i))), lists.cdr.apply1(paramObject2));
          localObject1 = AddOp.$Pl.apply2(localObject1, Lit16);
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject2);
        }
        if (!bool) {}
      }
      paramObject2 = lists.cons(Char.make(i), paramObject2);
      localObject1 = AddOp.$Pl.apply2(localObject1, Lit8);
      continue;
      if (Scheme.isEqv.apply2(Char.make(i), Lit15) != Boolean.FALSE) {}
      try
      {
        localObject3 = (CharSequence)paramObject1;
        localObject2 = AddOp.$Pl.apply2(localObject1, Lit8);
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
      }
      try
      {
        j = ((Number)localObject2).intValue();
        if (characters.isChar$Eq(Char.make(strings.stringRef((CharSequence)localObject3, j)), Lit44))
        {
          localObject1 = pregexpReadPosixCharClass(paramObject1, AddOp.$Pl.apply2(localObject1, Lit16), paramObject3);
          paramObject2 = lists.cons(lists.car.apply1(localObject1), paramObject2);
          localObject1 = lists.cadr.apply1(localObject1);
          continue;
        }
        paramObject2 = lists.cons(Char.make(i), paramObject2);
        localObject1 = AddOp.$Pl.apply2(localObject1, Lit8);
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject2);
      }
      paramObject2 = lists.cons(Char.make(i), paramObject2);
      localObject1 = AddOp.$Pl.apply2(localObject1, Lit8);
    }
  }
  
  public static Object pregexpReadClusterType(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    for (;;)
    {
      try
      {
        paramObject3 = (CharSequence)paramObject1;
      }
      catch (ClassCastException paramObject2)
      {
        int i;
        Object localObject;
        CharSequence localCharSequence;
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject2).intValue();
        i = strings.stringRef((CharSequence)paramObject3, i);
        if (Scheme.isEqv.apply2(Char.make(i), Lit47) != Boolean.FALSE) {
          paramObject3 = AddOp.$Pl.apply2(paramObject2, Lit8);
        }
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, paramObject2);
      }
      try
      {
        paramObject2 = (CharSequence)paramObject1;
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject3).intValue();
        i = strings.stringRef((CharSequence)paramObject2, i);
        if (Scheme.isEqv.apply2(Char.make(i), Lit44) != Boolean.FALSE) {
          return LList.list2(LList.Empty, AddOp.$Pl.apply2(paramObject3, Lit8));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit48) != Boolean.FALSE) {
          return LList.list2(Lit49, AddOp.$Pl.apply2(paramObject3, Lit8));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit50) != Boolean.FALSE) {
          return LList.list2(Lit51, AddOp.$Pl.apply2(paramObject3, Lit8));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit52) != Boolean.FALSE) {
          return LList.list2(Lit53, AddOp.$Pl.apply2(paramObject3, Lit8));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit54) == Boolean.FALSE) {}
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, paramObject3);
      }
      try
      {
        paramObject2 = (CharSequence)paramObject1;
        paramObject1 = AddOp.$Pl.apply2(paramObject3, Lit8);
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject1).intValue();
        i = strings.stringRef((CharSequence)paramObject2, i);
        if (Scheme.isEqv.apply2(Char.make(i), Lit48) != Boolean.FALSE)
        {
          paramObject1 = Lit55;
          return LList.list2(paramObject1, AddOp.$Pl.apply2(paramObject3, Lit16));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit50) != Boolean.FALSE)
        {
          paramObject1 = Lit56;
          continue;
        }
        paramObject1 = pregexpError$V(new Object[] { Lit57 });
        continue;
        localObject = LList.Empty;
        paramObject2 = Boolean.FALSE;
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 2, paramObject1);
      }
      try
      {
        localCharSequence = (CharSequence)paramObject1;
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject3).intValue();
        i = strings.stringRef(localCharSequence, i);
        if (Scheme.isEqv.apply2(Char.make(i), Lit58) != Boolean.FALSE)
        {
          paramObject3 = AddOp.$Pl.apply2(paramObject3, Lit8);
          paramObject2 = Boolean.TRUE;
        }
        else if (Scheme.isEqv.apply2(Char.make(i), Lit59) != Boolean.FALSE)
        {
          paramObject3 = AddOp.$Pl.apply2(paramObject3, Lit8);
          if (paramObject2 != Boolean.FALSE)
          {
            paramObject2 = Lit60;
            localObject = lists.cons(paramObject2, localObject);
            paramObject2 = Boolean.FALSE;
          }
          else
          {
            paramObject2 = Lit61;
          }
        }
        else if (Scheme.isEqv.apply2(Char.make(i), Lit62) != Boolean.FALSE)
        {
          $Stpregexp$Mnspace$Mnsensitive$Qu$St = paramObject2;
          paramObject3 = AddOp.$Pl.apply2(paramObject3, Lit8);
          paramObject2 = Boolean.FALSE;
        }
        else
        {
          if (Scheme.isEqv.apply2(Char.make(i), Lit44) != Boolean.FALSE) {
            return LList.list2(localObject, AddOp.$Pl.apply2(paramObject3, Lit8));
          }
          return pregexpError$V(new Object[] { Lit57 });
        }
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, paramObject3);
      }
    }
    return LList.list2(Lit63, paramObject2);
  }
  
  public static Object pregexpReadEscapedChar(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    paramObject3 = Scheme.numLss.apply2(AddOp.$Pl.apply2(paramObject2, Lit8), paramObject3);
    for (;;)
    {
      try
      {
        bool = ((Boolean)paramObject3).booleanValue();
        if (!bool) {}
      }
      catch (ClassCastException paramObject1)
      {
        boolean bool;
        int i;
        throw new WrongType((ClassCastException)paramObject1, "x", -2, paramObject3);
      }
      try
      {
        paramObject3 = (CharSequence)paramObject1;
        paramObject1 = AddOp.$Pl.apply2(paramObject2, Lit8);
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject1).intValue();
        i = strings.stringRef((CharSequence)paramObject3, i);
        if (Scheme.isEqv.apply2(Char.make(i), Lit25) != Boolean.FALSE) {
          return LList.list2(Lit26, AddOp.$Pl.apply2(paramObject2, Lit16));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit27) != Boolean.FALSE) {
          return LList.list2(Lit28, AddOp.$Pl.apply2(paramObject2, Lit16));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit29) != Boolean.FALSE) {
          return LList.list2(Lit30, AddOp.$Pl.apply2(paramObject2, Lit16));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit31) != Boolean.FALSE) {
          return LList.list2(Lit32, AddOp.$Pl.apply2(paramObject2, Lit16));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit33) != Boolean.FALSE) {
          return LList.list2(Lit24, AddOp.$Pl.apply2(paramObject2, Lit16));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit34) != Boolean.FALSE) {
          return LList.list2($Stpregexp$Mnreturn$Mnchar$St, AddOp.$Pl.apply2(paramObject2, Lit16));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit35) != Boolean.FALSE) {
          return LList.list2(Lit36, AddOp.$Pl.apply2(paramObject2, Lit16));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit37) != Boolean.FALSE) {
          return LList.list2(Lit38, AddOp.$Pl.apply2(paramObject2, Lit16));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit39) != Boolean.FALSE) {
          return LList.list2($Stpregexp$Mntab$Mnchar$St, AddOp.$Pl.apply2(paramObject2, Lit16));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit40) != Boolean.FALSE) {
          return LList.list2(Lit41, AddOp.$Pl.apply2(paramObject2, Lit16));
        }
        if (Scheme.isEqv.apply2(Char.make(i), Lit42) != Boolean.FALSE) {
          return LList.list2(Lit43, AddOp.$Pl.apply2(paramObject2, Lit16));
        }
        return LList.list2(Char.make(i), AddOp.$Pl.apply2(paramObject2, Lit16));
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 2, paramObject1);
      }
    }
    if (bool) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
  
  public static Object pregexpReadEscapedNumber(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    localObject = Scheme.numLss.apply2(AddOp.$Pl.apply2(paramObject2, Lit8), paramObject3);
    for (;;)
    {
      try
      {
        bool = ((Boolean)localObject).booleanValue();
        if (!bool) {}
      }
      catch (ClassCastException paramObject1)
      {
        boolean bool;
        CharSequence localCharSequence;
        int i;
        throw new WrongType((ClassCastException)paramObject1, "x", -2, localObject);
      }
      try
      {
        localCharSequence = (CharSequence)paramObject1;
        localObject = AddOp.$Pl.apply2(paramObject2, Lit8);
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
      }
      try
      {
        i = ((Number)localObject).intValue();
        i = strings.stringRef(localCharSequence, i);
        bool = unicode.isCharNumeric(Char.make(i));
        if (bool)
        {
          paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit16);
          localObject = LList.list1(Char.make(i));
          if (Scheme.numGEq.apply2(paramObject2, paramObject3) != Boolean.FALSE) {
            paramObject1 = pregexpReverse$Ex(localObject);
          }
        }
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject);
      }
      try
      {
        paramObject3 = (LList)paramObject1;
        return LList.list2(numbers.string$To$Number(strings.list$To$String((LList)paramObject3)), paramObject2);
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "list->string", 1, paramObject1);
      }
      try
      {
        localCharSequence = (CharSequence)paramObject1;
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject2).intValue();
        i = strings.stringRef(localCharSequence, i);
        if (unicode.isCharNumeric(Char.make(i)))
        {
          paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit8);
          localObject = lists.cons(Char.make(i), localObject);
        }
        else
        {
          paramObject1 = pregexpReverse$Ex(localObject);
        }
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, paramObject2);
      }
    }
    try
    {
      paramObject3 = (LList)paramObject1;
      return LList.list2(numbers.string$To$Number(strings.list$To$String((LList)paramObject3)), paramObject2);
    }
    catch (ClassCastException paramObject2)
    {
      throw new WrongType((ClassCastException)paramObject2, "list->string", 1, paramObject1);
    }
    if (bool) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    if (bool) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
  
  public static Object pregexpReadNums(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Object localObject2 = LList.Empty;
    Object localObject1 = LList.Empty;
    IntNum localIntNum = Lit8;
    if (Scheme.numGEq.apply2(paramObject2, paramObject3) != Boolean.FALSE) {
      pregexpError$V(new Object[] { Lit76 });
    }
    for (;;)
    {
      try
      {
        localCharSequence = (CharSequence)paramObject1;
      }
      catch (ClassCastException paramObject2)
      {
        CharSequence localCharSequence;
        int i;
        boolean bool;
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject2).intValue();
        i = strings.stringRef(localCharSequence, i);
        if (unicode.isCharNumeric(Char.make(i)))
        {
          if (Scheme.numEqu.apply2(localIntNum, Lit8) != Boolean.FALSE)
          {
            localObject2 = lists.cons(Char.make(i), localObject2);
            paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit8);
            localIntNum = Lit8;
            break;
          }
          localObject1 = lists.cons(Char.make(i), localObject1);
          paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit8);
          localIntNum = Lit16;
          break;
        }
        bool = unicode.isCharWhitespace(Char.make(i));
        if (bool)
        {
          if ($Stpregexp$Mnspace$Mnsensitive$Qu$St != Boolean.FALSE) {
            continue;
          }
          paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit8);
          break;
        }
        if (bool) {
          continue;
        }
        bool = characters.isChar$Eq(Char.make(i), Lit77);
        if (bool)
        {
          if (Scheme.numEqu.apply2(localIntNum, Lit8) == Boolean.FALSE) {
            continue;
          }
          paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit8);
          localIntNum = Lit16;
          break;
        }
        if (bool) {
          continue;
        }
        if (characters.isChar$Eq(Char.make(i), Lit78)) {
          paramObject1 = pregexpReverse$Ex(localObject2);
        }
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, paramObject2);
      }
      try
      {
        paramObject3 = (LList)paramObject1;
        paramObject1 = numbers.string$To$Number(strings.list$To$String((LList)paramObject3));
        paramObject3 = pregexpReverse$Ex(localObject1);
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "list->string", 1, paramObject1);
      }
      try
      {
        localObject1 = (LList)paramObject3;
        paramObject3 = numbers.string$To$Number(strings.list$To$String((LList)localObject1));
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "list->string", 1, paramObject3);
      }
      try
      {
        localObject1 = Boolean.FALSE;
        if (paramObject1 != localObject1)
        {
          i = 1;
          i = i + 1 & 0x1;
          if (i != 0)
          {
            if (Scheme.numEqu.apply2(localIntNum, Lit8) == Boolean.FALSE) {
              continue;
            }
            return LList.list3(Lit73, Boolean.FALSE, paramObject2);
          }
        }
        else
        {
          i = 0;
          continue;
        }
        if (i == 0)
        {
          if (Scheme.numEqu.apply2(localIntNum, Lit8) != Boolean.FALSE) {
            return LList.list3(paramObject1, paramObject1, paramObject2);
          }
          return LList.list3(paramObject1, paramObject3, paramObject2);
        }
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "x", -2, paramObject1);
      }
    }
    return Boolean.FALSE;
  }
  
  public static Object pregexpReadPattern(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    if (Scheme.numGEq.apply2(paramObject2, paramObject3) != Boolean.FALSE) {
      return LList.list2(LList.list2(Lit4, LList.list1(Lit5)), paramObject2);
    }
    Object localObject1 = LList.Empty;
    for (;;)
    {
      Object localObject2 = Scheme.numGEq.apply2(paramObject2, paramObject3);
      try
      {
        boolean bool = ((Boolean)localObject2).booleanValue();
        if (bool)
        {
          if (!bool) {
            break label114;
          }
          return LList.list2(lists.cons(Lit4, pregexpReverse$Ex(localObject1)), paramObject2);
        }
      }
      catch (ClassCastException paramObject1)
      {
        for (;;)
        {
          try
          {
            localObject2 = (CharSequence)paramObject1;
          }
          catch (ClassCastException paramObject2)
          {
            int i;
            label114:
            CharSequence localCharSequence;
            throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
          }
          try
          {
            i = ((Number)paramObject2).intValue();
            if (characters.isChar$Eq(Char.make(strings.stringRef((CharSequence)localObject2, i)), Lit6)) {}
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, paramObject2);
          }
        }
        try
        {
          localCharSequence = (CharSequence)paramObject1;
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
        }
        try
        {
          i = ((Number)paramObject2).intValue();
          localObject2 = paramObject2;
          if (characters.isChar$Eq(Char.make(strings.stringRef(localCharSequence, i)), Lit7)) {
            localObject2 = AddOp.$Pl.apply2(paramObject2, Lit8);
          }
          paramObject2 = pregexpReadBranch(paramObject1, localObject2, paramObject3);
          localObject1 = lists.cons(lists.car.apply1(paramObject2), localObject1);
          paramObject2 = lists.cadr.apply1(paramObject2);
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, paramObject2);
        }
        paramObject1 = paramObject1;
        throw new WrongType((ClassCastException)paramObject1, "x", -2, localObject2);
      }
    }
  }
  
  public static Object pregexpReadPiece(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public static Object pregexpReadPosixCharClass(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Boolean localBoolean = Boolean.FALSE;
    Pair localPair = LList.list1(Lit44);
    if (Scheme.numGEq.apply2(paramObject2, paramObject3) != Boolean.FALSE) {
      return pregexpError$V(new Object[] { Lit45 });
    }
    for (;;)
    {
      try
      {
        localCharSequence = (CharSequence)paramObject1;
      }
      catch (ClassCastException paramObject2)
      {
        CharSequence localCharSequence;
        int i;
        boolean bool;
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject2).intValue();
        i = strings.stringRef(localCharSequence, i);
        if (characters.isChar$Eq(Char.make(i), Lit9))
        {
          localBoolean = Boolean.TRUE;
          paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit8);
          break;
        }
        if (unicode.isCharAlphabetic(Char.make(i)))
        {
          paramObject2 = AddOp.$Pl.apply2(paramObject2, Lit8);
          localPair = lists.cons(Char.make(i), localPair);
          break;
        }
        if (characters.isChar$Eq(Char.make(i), Lit44)) {
          paramObject3 = Scheme.numGEq.apply2(AddOp.$Pl.apply2(paramObject2, Lit8), paramObject3);
        }
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, paramObject2);
      }
      try
      {
        bool = ((Boolean)paramObject3).booleanValue();
        if (bool)
        {
          if (!bool) {
            continue;
          }
          return pregexpError$V(new Object[] { Lit45 });
        }
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "x", -2, paramObject3);
      }
      try
      {
        paramObject3 = (CharSequence)paramObject1;
        paramObject1 = AddOp.$Pl.apply2(paramObject2, Lit8);
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject1).intValue();
        if (!characters.isChar$Eq(Char.make(strings.stringRef((CharSequence)paramObject3, i)), Lit46)) {
          continue;
        }
        paramObject1 = pregexpReverse$Ex(localPair);
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "string-ref", 2, paramObject1);
      }
      try
      {
        paramObject3 = (LList)paramObject1;
        paramObject3 = misc.string$To$Symbol(strings.list$To$String((LList)paramObject3));
        paramObject1 = paramObject3;
        if (localBoolean != Boolean.FALSE) {
          paramObject1 = LList.list2(Lit17, paramObject3);
        }
        return LList.list2(paramObject1, AddOp.$Pl.apply2(paramObject2, Lit16));
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "list->string", 1, paramObject1);
      }
    }
    return pregexpError$V(new Object[] { Lit45 });
  }
  
  /* Error */
  public static Object pregexpReadSubpattern(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    // Byte code:
    //   0: getstatic 1008	gnu/kawa/slib/pregexp:$Stpregexp$Mnspace$Mnsensitive$Qu$St	Ljava/lang/Object;
    //   3: astore 4
    //   5: aload_0
    //   6: aload_1
    //   7: aload_2
    //   8: invokestatic 1205	gnu/kawa/slib/pregexp:pregexpReadClusterType	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   11: astore_1
    //   12: getstatic 981	kawa/lib/lists:car	Lgnu/expr/GenericProc;
    //   15: aload_1
    //   16: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   19: astore_3
    //   20: aload_0
    //   21: getstatic 1163	kawa/lib/lists:cadr	Lgnu/expr/GenericProc;
    //   24: aload_1
    //   25: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   28: aload_2
    //   29: invokestatic 1021	gnu/kawa/slib/pregexp:pregexpReadPattern	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   32: astore 5
    //   34: aload 4
    //   36: putstatic 1008	gnu/kawa/slib/pregexp:$Stpregexp$Mnspace$Mnsensitive$Qu$St	Ljava/lang/Object;
    //   39: getstatic 981	kawa/lib/lists:car	Lgnu/expr/GenericProc;
    //   42: aload 5
    //   44: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   47: astore_1
    //   48: getstatic 1163	kawa/lib/lists:cadr	Lgnu/expr/GenericProc;
    //   51: aload 5
    //   53: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   56: astore 4
    //   58: getstatic 1144	kawa/standard/Scheme:numLss	Lgnu/kawa/functions/NumberCompare;
    //   61: aload 4
    //   63: aload_2
    //   64: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   67: astore_2
    //   68: aload_2
    //   69: checkcast 392	java/lang/Boolean
    //   72: invokevirtual 902	java/lang/Boolean:booleanValue	()Z
    //   75: istore 7
    //   77: iload 7
    //   79: ifeq +61 -> 140
    //   82: aload_0
    //   83: checkcast 910	java/lang/CharSequence
    //   86: astore_2
    //   87: aload 4
    //   89: checkcast 912	java/lang/Number
    //   92: invokevirtual 916	java/lang/Number:intValue	()I
    //   95: istore 6
    //   97: aload_2
    //   98: iload 6
    //   100: invokestatic 922	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
    //   103: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   106: getstatic 331	gnu/kawa/slib/pregexp:Lit6	Lgnu/text/Char;
    //   109: invokestatic 957	kawa/lib/characters:isChar$Eq	(Lgnu/text/Char;Lgnu/text/Char;)Z
    //   112: ifeq +35 -> 147
    //   115: aload_3
    //   116: astore_0
    //   117: aload_0
    //   118: invokestatic 1071	kawa/lib/lists:isNull	(Ljava/lang/Object;)Z
    //   121: ifeq +40 -> 161
    //   124: aload_1
    //   125: getstatic 1074	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   128: aload 4
    //   130: getstatic 697	gnu/kawa/slib/pregexp:Lit8	Lgnu/math/IntNum;
    //   133: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   136: invokestatic 1024	gnu/lists/LList:list2	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   139: areturn
    //   140: aload_3
    //   141: astore_0
    //   142: iload 7
    //   144: ifne -27 -> 117
    //   147: iconst_1
    //   148: anewarray 999	java/lang/Object
    //   151: dup
    //   152: iconst_0
    //   153: getstatic 559	gnu/kawa/slib/pregexp:Lit64	Lgnu/mapping/SimpleSymbol;
    //   156: aastore
    //   157: invokestatic 1111	gnu/kawa/slib/pregexp:pregexpError$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   160: areturn
    //   161: getstatic 987	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   164: aload_0
    //   165: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   168: astore_2
    //   169: getstatic 981	kawa/lib/lists:car	Lgnu/expr/GenericProc;
    //   172: aload_0
    //   173: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   176: aload_1
    //   177: invokestatic 1024	gnu/lists/LList:list2	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   180: astore_1
    //   181: aload_2
    //   182: astore_0
    //   183: goto -66 -> 117
    //   186: astore_0
    //   187: new 933	gnu/mapping/WrongType
    //   190: dup
    //   191: aload_0
    //   192: ldc_w 935
    //   195: bipush -2
    //   197: aload_2
    //   198: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   201: athrow
    //   202: astore_1
    //   203: new 933	gnu/mapping/WrongType
    //   206: dup
    //   207: aload_1
    //   208: ldc_w 940
    //   211: iconst_1
    //   212: aload_0
    //   213: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   216: athrow
    //   217: astore_0
    //   218: new 933	gnu/mapping/WrongType
    //   221: dup
    //   222: aload_0
    //   223: ldc_w 940
    //   226: iconst_2
    //   227: aload 4
    //   229: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   232: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	233	0	paramObject1	Object
    //   0	233	1	paramObject2	Object
    //   0	233	2	paramObject3	Object
    //   19	122	3	localObject1	Object
    //   3	225	4	localObject2	Object
    //   32	20	5	localObject3	Object
    //   95	4	6	i	int
    //   75	68	7	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   68	77	186	java/lang/ClassCastException
    //   82	87	202	java/lang/ClassCastException
    //   87	97	217	java/lang/ClassCastException
  }
  
  /* Error */
  public static Object pregexpReplace(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    // Byte code:
    //   0: aload_1
    //   1: checkcast 910	java/lang/CharSequence
    //   4: astore_3
    //   5: aload_3
    //   6: invokestatic 1012	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
    //   9: istore 6
    //   11: aload_0
    //   12: aload_1
    //   13: iconst_2
    //   14: anewarray 999	java/lang/Object
    //   17: dup
    //   18: iconst_0
    //   19: getstatic 403	gnu/kawa/slib/pregexp:Lit73	Lgnu/math/IntNum;
    //   22: aastore
    //   23: dup
    //   24: iconst_1
    //   25: iload 6
    //   27: invokestatic 1018	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   30: aastore
    //   31: invokestatic 1208	gnu/kawa/slib/pregexp:pregexpMatchPositions$V	(Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   34: astore_3
    //   35: aload_3
    //   36: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   39: if_acmpne +5 -> 44
    //   42: aload_1
    //   43: areturn
    //   44: aload_2
    //   45: checkcast 910	java/lang/CharSequence
    //   48: astore_0
    //   49: aload_0
    //   50: invokestatic 1012	kawa/lib/strings:stringLength	(Ljava/lang/CharSequence;)I
    //   53: istore 7
    //   55: getstatic 1211	kawa/lib/lists:caar	Lgnu/expr/GenericProc;
    //   58: aload_3
    //   59: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   62: astore 4
    //   64: getstatic 1214	kawa/lib/lists:cdar	Lgnu/expr/GenericProc;
    //   67: aload_3
    //   68: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   71: astore_0
    //   72: aload_1
    //   73: checkcast 910	java/lang/CharSequence
    //   76: astore 5
    //   78: aload 4
    //   80: checkcast 912	java/lang/Number
    //   83: invokevirtual 916	java/lang/Number:intValue	()I
    //   86: istore 8
    //   88: aload 5
    //   90: iconst_0
    //   91: iload 8
    //   93: invokestatic 1093	kawa/lib/strings:substring	(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
    //   96: astore 4
    //   98: aload_1
    //   99: aload_2
    //   100: iload 7
    //   102: invokestatic 1018	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   105: aload_3
    //   106: invokestatic 1217	gnu/kawa/slib/pregexp:pregexpReplaceAux	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   109: astore_2
    //   110: aload_1
    //   111: checkcast 910	java/lang/CharSequence
    //   114: astore_3
    //   115: aload_0
    //   116: checkcast 912	java/lang/Number
    //   119: invokevirtual 916	java/lang/Number:intValue	()I
    //   122: istore 7
    //   124: iconst_3
    //   125: anewarray 999	java/lang/Object
    //   128: dup
    //   129: iconst_0
    //   130: aload 4
    //   132: aastore
    //   133: dup
    //   134: iconst_1
    //   135: aload_2
    //   136: aastore
    //   137: dup
    //   138: iconst_2
    //   139: aload_3
    //   140: iload 7
    //   142: iload 6
    //   144: invokestatic 1093	kawa/lib/strings:substring	(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
    //   147: aastore
    //   148: invokestatic 1221	kawa/lib/strings:stringAppend	([Ljava/lang/Object;)Lgnu/lists/FString;
    //   151: areturn
    //   152: astore_0
    //   153: new 933	gnu/mapping/WrongType
    //   156: dup
    //   157: aload_0
    //   158: ldc_w 1026
    //   161: iconst_1
    //   162: aload_1
    //   163: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   166: athrow
    //   167: astore_0
    //   168: new 933	gnu/mapping/WrongType
    //   171: dup
    //   172: aload_0
    //   173: ldc_w 1026
    //   176: iconst_1
    //   177: aload_2
    //   178: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   181: athrow
    //   182: astore_0
    //   183: new 933	gnu/mapping/WrongType
    //   186: dup
    //   187: aload_0
    //   188: ldc_w 1096
    //   191: iconst_1
    //   192: aload_1
    //   193: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   196: athrow
    //   197: astore_0
    //   198: new 933	gnu/mapping/WrongType
    //   201: dup
    //   202: aload_0
    //   203: ldc_w 1096
    //   206: iconst_3
    //   207: aload 4
    //   209: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   212: athrow
    //   213: astore_0
    //   214: new 933	gnu/mapping/WrongType
    //   217: dup
    //   218: aload_0
    //   219: ldc_w 1096
    //   222: iconst_1
    //   223: aload_1
    //   224: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   227: athrow
    //   228: astore_1
    //   229: new 933	gnu/mapping/WrongType
    //   232: dup
    //   233: aload_1
    //   234: ldc_w 1096
    //   237: iconst_2
    //   238: aload_0
    //   239: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   242: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	243	0	paramObject1	Object
    //   0	243	1	paramObject2	Object
    //   0	243	2	paramObject3	Object
    //   4	136	3	localObject1	Object
    //   62	146	4	localObject2	Object
    //   76	13	5	localCharSequence	CharSequence
    //   9	134	6	i	int
    //   53	88	7	j	int
    //   86	6	8	k	int
    // Exception table:
    //   from	to	target	type
    //   0	5	152	java/lang/ClassCastException
    //   44	49	167	java/lang/ClassCastException
    //   72	78	182	java/lang/ClassCastException
    //   78	88	197	java/lang/ClassCastException
    //   110	115	213	java/lang/ClassCastException
    //   115	124	228	java/lang/ClassCastException
  }
  
  public static Object pregexpReplace$St(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Object localObject1 = paramObject1;
    if (strings.isString(paramObject1)) {
      localObject1 = pregexp(paramObject1);
    }
    for (;;)
    {
      try
      {
        paramObject1 = (CharSequence)paramObject2;
        i = strings.stringLength((CharSequence)paramObject1);
      }
      catch (ClassCastException paramObject1)
      {
        try
        {
          int i;
          localCharSequence = (CharSequence)paramObject2;
        }
        catch (ClassCastException paramObject1)
        {
          int j;
          Object localObject2;
          Object localObject4;
          Object localObject3;
          CharSequence localCharSequence;
          int k;
          int m;
          throw new WrongType((ClassCastException)paramObject1, "substring", 1, paramObject2);
        }
        try
        {
          k = ((Number)paramObject1).intValue();
          paramObject1 = lists.caar.apply1(localObject4);
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "substring", 2, paramObject1);
        }
        try
        {
          m = ((Number)paramObject1).intValue();
          localObject2 = strings.stringAppend(new Object[] { localObject2, strings.substring(localCharSequence, k, m), pregexpReplaceAux(paramObject2, paramObject3, Integer.valueOf(j), localObject4) });
          paramObject1 = localObject3;
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "substring", 3, paramObject1);
        }
        paramObject1 = paramObject1;
        throw new WrongType((ClassCastException)paramObject1, "string-length", 1, paramObject2);
      }
      try
      {
        paramObject1 = (CharSequence)paramObject3;
        j = strings.stringLength((CharSequence)paramObject1);
        paramObject1 = Lit73;
        localObject2 = "";
        if (Scheme.numGEq.apply2(paramObject1, Integer.valueOf(i)) != Boolean.FALSE) {
          return localObject2;
        }
        localObject4 = pregexpMatchPositions$V(localObject1, paramObject2, new Object[] { paramObject1, Integer.valueOf(i) });
        if (localObject4 == Boolean.FALSE) {
          if (Scheme.numEqu.apply2(paramObject1, Lit73) != Boolean.FALSE) {
            return paramObject2;
          }
        }
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "string-length", 1, paramObject3);
      }
      try
      {
        paramObject3 = (CharSequence)paramObject2;
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "substring", 1, paramObject2);
      }
      try
      {
        j = ((Number)paramObject1).intValue();
        paramObject2 = strings.stringAppend(new Object[] { localObject2, strings.substring((CharSequence)paramObject3, j, i) });
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "substring", 2, paramObject1);
      }
    }
    localObject3 = lists.cdar.apply1(localObject4);
  }
  
  public static Object pregexpReplaceAux(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    localObject2 = Lit73;
    for (Object localObject1 = "";; localObject1 = strings.stringAppend(new Object[] { localObject1, strings.$make$string$(new Object[] { Char.make(i) }) }))
    {
      for (;;)
      {
        if (Scheme.numGEq.apply2(localObject2, paramObject3) != Boolean.FALSE) {
          return localObject1;
        }
        Object localObject3;
        Object localObject4;
        Object localObject5;
        for (;;)
        {
          try
          {
            localObject3 = (CharSequence)paramObject2;
          }
          catch (ClassCastException paramObject1)
          {
            int i;
            throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, paramObject2);
          }
          try
          {
            i = ((Number)localObject2).intValue();
            i = strings.stringRef((CharSequence)localObject3, i);
            if (characters.isChar$Eq(Char.make(i), Lit19))
            {
              localObject4 = pregexpReadEscapedNumber(paramObject2, localObject2, paramObject3);
              if (localObject4 != Boolean.FALSE)
              {
                localObject3 = lists.car.apply1(localObject4);
                if (localObject4 == Boolean.FALSE) {
                  continue;
                }
                localObject2 = lists.cadr.apply1(localObject4);
                if (localObject3 != Boolean.FALSE) {
                  continue;
                }
              }
            }
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject2);
          }
          try
          {
            localObject3 = (CharSequence)paramObject2;
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, paramObject2);
          }
          try
          {
            i = ((Number)localObject2).intValue();
            i = strings.stringRef((CharSequence)localObject3, i);
            localObject2 = AddOp.$Pl.apply2(localObject2, Lit8);
            if (characters.isChar$Eq(Char.make(i), Lit11)) {
              break;
            }
            try
            {
              localObject5 = (CharSequence)paramObject2;
              localObject3 = AddOp.$Pl.apply2(localObject2, Lit8);
            }
            catch (ClassCastException paramObject1)
            {
              int j;
              throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, paramObject2);
            }
            try
            {
              i = ((Number)localObject3).intValue();
              if (characters.isChar$Eq(Char.make(strings.stringRef((CharSequence)localObject5, i)), Lit113))
              {
                localObject3 = Lit73;
                continue;
              }
              localObject3 = Boolean.FALSE;
            }
            catch (ClassCastException paramObject1)
            {
              throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject3);
            }
            if (localObject3 != Boolean.FALSE)
            {
              localObject2 = AddOp.$Pl.apply2(localObject2, Lit16);
              continue;
            }
            localObject2 = AddOp.$Pl.apply2(localObject2, Lit8);
          }
          catch (ClassCastException paramObject1)
          {
            throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject2);
          }
          localObject1 = strings.stringAppend(new Object[] { localObject1, strings.$make$string$(new Object[] { Char.make(i) }) });
          continue;
          localObject4 = pregexpListRef(paramObject4, localObject3);
          localObject3 = localObject1;
          if (localObject4 == Boolean.FALSE) {}
        }
        try
        {
          localObject3 = (CharSequence)paramObject1;
          localObject5 = lists.car.apply1(localObject4);
        }
        catch (ClassCastException paramObject2)
        {
          throw new WrongType((ClassCastException)paramObject2, "substring", 1, paramObject1);
        }
        try
        {
          i = ((Number)localObject5).intValue();
          localObject4 = lists.cdr.apply1(localObject4);
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 2, localObject5);
        }
        try
        {
          j = ((Number)localObject4).intValue();
          localObject3 = strings.stringAppend(new Object[] { localObject1, strings.substring((CharSequence)localObject3, i, j) });
          localObject1 = localObject3;
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 3, localObject4);
        }
      }
      localObject2 = AddOp.$Pl.apply2(localObject2, Lit8);
    }
  }
  
  public static Object pregexpReverse$Ex(Object paramObject)
  {
    Object localObject1 = LList.Empty;
    for (;;)
    {
      if (lists.isNull(paramObject)) {
        return localObject1;
      }
      Object localObject2 = lists.cdr.apply1(paramObject);
      try
      {
        Pair localPair = (Pair)paramObject;
        lists.setCdr$Ex(localPair, localObject1);
        localObject1 = paramObject;
        paramObject = localObject2;
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "set-cdr!", 1, paramObject);
      }
    }
  }
  
  public static Object pregexpSplit(Object paramObject1, Object paramObject2)
  {
    for (;;)
    {
      try
      {
        localObject1 = (CharSequence)paramObject2;
        i = strings.stringLength((CharSequence)localObject1);
        localObject1 = Lit73;
        localObject3 = LList.Empty;
        localObject2 = Boolean.FALSE;
      }
      catch (ClassCastException paramObject1)
      {
        try
        {
          bool = ((Boolean)localObject6).booleanValue();
          if (bool)
          {
            if (localObject2 != Boolean.FALSE)
            {
              localObject2 = Boolean.FALSE;
              localObject1 = localObject4;
            }
          }
          else {
            if (bool) {
              continue;
            }
          }
        }
        catch (ClassCastException paramObject1)
        {
          int i;
          Object localObject3;
          Object localObject2;
          Object localObject4;
          Object localObject6;
          throw new WrongType((ClassCastException)paramObject1, "x", -2, localObject6);
        }
        try
        {
          localObject2 = (CharSequence)paramObject2;
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 1, paramObject2);
        }
        try
        {
          j = ((Number)localObject1).intValue();
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 2, localObject1);
        }
        try
        {
          k = ((Number)localObject5).intValue();
          localObject3 = lists.cons(strings.substring((CharSequence)localObject2, j, k), localObject3);
          localObject2 = Boolean.FALSE;
          localObject1 = localObject4;
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 3, localObject5);
        }
        try
        {
          localObject2 = (CharSequence)paramObject2;
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 1, paramObject2);
        }
        try
        {
          j = ((Number)localObject1).intValue();
          localObject3 = lists.cons(strings.substring((CharSequence)localObject2, j, i), localObject3);
          localObject2 = Boolean.FALSE;
          localObject1 = Integer.valueOf(i);
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "substring", 2, localObject1);
        }
        paramObject1 = paramObject1;
        throw new WrongType((ClassCastException)paramObject1, "string-length", 1, paramObject2);
      }
      if (Scheme.numGEq.apply2(localObject1, Integer.valueOf(i)) != Boolean.FALSE) {
        return pregexpReverse$Ex(localObject3);
      }
      localObject4 = pregexpMatchPositions$V(paramObject1, paramObject2, new Object[] { localObject1, Integer.valueOf(i) });
      if (localObject4 == Boolean.FALSE) {
        continue;
      }
      localObject4 = lists.car.apply1(localObject4);
      localObject5 = lists.car.apply1(localObject4);
      localObject4 = lists.cdr.apply1(localObject4);
      if (Scheme.numEqu.apply2(localObject5, localObject4) != Boolean.FALSE) {
        localObject2 = AddOp.$Pl.apply2(localObject4, Lit8);
      }
      try
      {
        localObject4 = (CharSequence)paramObject2;
      }
      catch (ClassCastException paramObject1)
      {
        int j;
        int k;
        boolean bool;
        throw new WrongType((ClassCastException)paramObject1, "substring", 1, paramObject2);
      }
      try
      {
        j = ((Number)localObject1).intValue();
        localObject1 = AddOp.$Pl.apply2(localObject5, Lit8);
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "substring", 2, localObject1);
      }
      try
      {
        k = ((Number)localObject1).intValue();
        localObject3 = lists.cons(strings.substring((CharSequence)localObject4, j, k), localObject3);
        localObject4 = Boolean.TRUE;
        localObject1 = localObject2;
        localObject2 = localObject4;
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "substring", 3, localObject1);
      }
    }
    localObject6 = Scheme.numEqu.apply2(localObject5, localObject1);
  }
  
  public static Object pregexpStringMatch(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6)
  {
    try
    {
      localObject1 = (CharSequence)paramObject1;
      i = strings.stringLength((CharSequence)localObject1);
      if (Scheme.numGrt.apply2(Integer.valueOf(i), paramObject4) != Boolean.FALSE) {
        return Scheme.applyToArgs.apply1(paramObject6);
      }
      localObject1 = Lit73;
    }
    catch (ClassCastException paramObject2)
    {
      for (;;)
      {
        Object localObject1;
        try
        {
          int i;
          localObject2 = (CharSequence)paramObject1;
        }
        catch (ClassCastException paramObject2)
        {
          Object localObject2;
          int j;
          CharSequence localCharSequence;
          throw new WrongType((ClassCastException)paramObject2, "string-ref", 1, paramObject1);
        }
        try
        {
          j = ((Number)localObject1).intValue();
          localObject2 = Char.make(strings.stringRef((CharSequence)localObject2, j));
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, localObject1);
        }
        try
        {
          localCharSequence = (CharSequence)paramObject2;
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 1, paramObject2);
        }
        try
        {
          j = ((Number)paramObject3).intValue();
          if (characters.isChar$Eq((Char)localObject2, Char.make(strings.stringRef(localCharSequence, j))))
          {
            localObject1 = AddOp.$Pl.apply2(localObject1, Lit8);
            paramObject3 = AddOp.$Pl.apply2(paramObject3, Lit8);
          }
          else
          {
            return Scheme.applyToArgs.apply1(paramObject6);
          }
        }
        catch (ClassCastException paramObject1)
        {
          throw new WrongType((ClassCastException)paramObject1, "string-ref", 2, paramObject3);
        }
      }
      paramObject2 = paramObject2;
      throw new WrongType((ClassCastException)paramObject2, "string-length", 1, paramObject1);
    }
    if (Scheme.numGEq.apply2(localObject1, Integer.valueOf(i)) != Boolean.FALSE) {
      return Scheme.applyToArgs.apply2(paramObject5, paramObject3);
    }
    if (Scheme.numGEq.apply2(paramObject3, paramObject4) != Boolean.FALSE) {
      return Scheme.applyToArgs.apply1(paramObject6);
    }
  }
  
  /* Error */
  public static Object pregexpWrapQuantifierIfAny(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    // Byte code:
    //   0: getstatic 981	kawa/lib/lists:car	Lgnu/expr/GenericProc;
    //   3: aload_0
    //   4: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   7: astore 4
    //   9: getstatic 1163	kawa/lib/lists:cadr	Lgnu/expr/GenericProc;
    //   12: aload_0
    //   13: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   16: astore_3
    //   17: getstatic 908	kawa/standard/Scheme:numGEq	Lgnu/kawa/functions/NumberCompare;
    //   20: aload_3
    //   21: aload_2
    //   22: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   25: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   28: if_acmpeq +5 -> 33
    //   31: aload_0
    //   32: areturn
    //   33: aload_1
    //   34: checkcast 910	java/lang/CharSequence
    //   37: astore 5
    //   39: aload_3
    //   40: checkcast 912	java/lang/Number
    //   43: invokevirtual 916	java/lang/Number:intValue	()I
    //   46: istore 7
    //   48: aload 5
    //   50: iload 7
    //   52: invokestatic 922	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
    //   55: istore 7
    //   57: iload 7
    //   59: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   62: invokestatic 1196	kawa/lib/rnrs/unicode:isCharWhitespace	(Lgnu/text/Char;)Z
    //   65: istore 8
    //   67: iload 8
    //   69: ifeq +26 -> 95
    //   72: getstatic 1008	gnu/kawa/slib/pregexp:$Stpregexp$Mnspace$Mnsensitive$Qu$St	Ljava/lang/Object;
    //   75: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   78: if_acmpne +22 -> 100
    //   81: getstatic 1074	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   84: aload_3
    //   85: getstatic 697	gnu/kawa/slib/pregexp:Lit8	Lgnu/math/IntNum;
    //   88: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   91: astore_3
    //   92: goto -75 -> 17
    //   95: iload 8
    //   97: ifne -16 -> 81
    //   100: getstatic 993	kawa/standard/Scheme:isEqv	Lgnu/kawa/functions/IsEqv;
    //   103: iload 7
    //   105: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   108: getstatic 311	gnu/kawa/slib/pregexp:Lit65	Lgnu/text/Char;
    //   111: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: astore 5
    //   116: aload 5
    //   118: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   121: if_acmpeq +182 -> 303
    //   124: aload 5
    //   126: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   129: if_acmpeq -98 -> 31
    //   132: getstatic 390	gnu/kawa/slib/pregexp:Lit68	Lgnu/mapping/SimpleSymbol;
    //   135: invokestatic 1186	gnu/lists/LList:list1	(Ljava/lang/Object;)Lgnu/lists/Pair;
    //   138: astore 5
    //   140: aload 5
    //   142: getstatic 555	gnu/kawa/slib/pregexp:Lit69	Lgnu/mapping/SimpleSymbol;
    //   145: getstatic 551	gnu/kawa/slib/pregexp:Lit70	Lgnu/mapping/SimpleSymbol;
    //   148: getstatic 547	gnu/kawa/slib/pregexp:Lit71	Lgnu/mapping/SimpleSymbol;
    //   151: aload 4
    //   153: invokestatic 1251	gnu/lists/LList:chain4	(Lgnu/lists/Pair;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   156: pop
    //   157: aload 5
    //   159: getstatic 543	gnu/kawa/slib/pregexp:Lit72	Lgnu/mapping/SimpleSymbol;
    //   162: invokestatic 1024	gnu/lists/LList:list2	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   165: astore 4
    //   167: getstatic 993	kawa/standard/Scheme:isEqv	Lgnu/kawa/functions/IsEqv;
    //   170: iload 7
    //   172: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   175: getstatic 311	gnu/kawa/slib/pregexp:Lit65	Lgnu/text/Char;
    //   178: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   181: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   184: if_acmpeq +212 -> 396
    //   187: getstatic 1254	kawa/lib/lists:cddr	Lgnu/expr/GenericProc;
    //   190: aload 5
    //   192: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   195: astore_0
    //   196: aload_0
    //   197: checkcast 1049	gnu/lists/Pair
    //   200: astore 6
    //   202: aload 6
    //   204: getstatic 403	gnu/kawa/slib/pregexp:Lit73	Lgnu/math/IntNum;
    //   207: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   210: getstatic 1257	kawa/lib/lists:cdddr	Lgnu/expr/GenericProc;
    //   213: aload 5
    //   215: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   218: astore_0
    //   219: aload_0
    //   220: checkcast 1049	gnu/lists/Pair
    //   223: astore 6
    //   225: aload 6
    //   227: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   230: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   233: aload_3
    //   234: astore_0
    //   235: getstatic 1074	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   238: aload_0
    //   239: getstatic 697	gnu/kawa/slib/pregexp:Lit8	Lgnu/math/IntNum;
    //   242: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   245: astore_0
    //   246: getstatic 908	kawa/standard/Scheme:numGEq	Lgnu/kawa/functions/NumberCompare;
    //   249: aload_0
    //   250: aload_2
    //   251: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   254: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   257: if_acmpeq +411 -> 668
    //   260: getstatic 987	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   263: aload 5
    //   265: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   268: astore_1
    //   269: aload_1
    //   270: checkcast 1049	gnu/lists/Pair
    //   273: astore_2
    //   274: aload_2
    //   275: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   278: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   281: getstatic 987	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   284: aload 4
    //   286: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   289: astore_1
    //   290: aload_1
    //   291: checkcast 1049	gnu/lists/Pair
    //   294: astore_2
    //   295: aload_2
    //   296: aload_0
    //   297: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   300: aload 4
    //   302: areturn
    //   303: getstatic 993	kawa/standard/Scheme:isEqv	Lgnu/kawa/functions/IsEqv;
    //   306: iload 7
    //   308: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   311: getstatic 313	gnu/kawa/slib/pregexp:Lit66	Lgnu/text/Char;
    //   314: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   317: astore 5
    //   319: aload 5
    //   321: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   324: if_acmpeq +14 -> 338
    //   327: aload 5
    //   329: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   332: if_acmpeq -301 -> 31
    //   335: goto -203 -> 132
    //   338: getstatic 993	kawa/standard/Scheme:isEqv	Lgnu/kawa/functions/IsEqv;
    //   341: iload 7
    //   343: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   346: getstatic 309	gnu/kawa/slib/pregexp:Lit47	Lgnu/text/Char;
    //   349: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   352: astore 5
    //   354: aload 5
    //   356: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   359: if_acmpeq +14 -> 373
    //   362: aload 5
    //   364: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   367: if_acmpeq -336 -> 31
    //   370: goto -238 -> 132
    //   373: getstatic 993	kawa/standard/Scheme:isEqv	Lgnu/kawa/functions/IsEqv;
    //   376: iload 7
    //   378: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   381: getstatic 325	gnu/kawa/slib/pregexp:Lit67	Lgnu/text/Char;
    //   384: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   387: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   390: if_acmpeq -359 -> 31
    //   393: goto -261 -> 132
    //   396: getstatic 993	kawa/standard/Scheme:isEqv	Lgnu/kawa/functions/IsEqv;
    //   399: iload 7
    //   401: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   404: getstatic 313	gnu/kawa/slib/pregexp:Lit66	Lgnu/text/Char;
    //   407: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   410: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   413: if_acmpeq +54 -> 467
    //   416: getstatic 1254	kawa/lib/lists:cddr	Lgnu/expr/GenericProc;
    //   419: aload 5
    //   421: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   424: astore_0
    //   425: aload_0
    //   426: checkcast 1049	gnu/lists/Pair
    //   429: astore 6
    //   431: aload 6
    //   433: getstatic 697	gnu/kawa/slib/pregexp:Lit8	Lgnu/math/IntNum;
    //   436: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   439: getstatic 1257	kawa/lib/lists:cdddr	Lgnu/expr/GenericProc;
    //   442: aload 5
    //   444: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   447: astore_0
    //   448: aload_0
    //   449: checkcast 1049	gnu/lists/Pair
    //   452: astore 6
    //   454: aload 6
    //   456: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   459: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   462: aload_3
    //   463: astore_0
    //   464: goto -229 -> 235
    //   467: getstatic 993	kawa/standard/Scheme:isEqv	Lgnu/kawa/functions/IsEqv;
    //   470: iload 7
    //   472: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   475: getstatic 309	gnu/kawa/slib/pregexp:Lit47	Lgnu/text/Char;
    //   478: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   481: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   484: if_acmpeq +54 -> 538
    //   487: getstatic 1254	kawa/lib/lists:cddr	Lgnu/expr/GenericProc;
    //   490: aload 5
    //   492: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   495: astore_0
    //   496: aload_0
    //   497: checkcast 1049	gnu/lists/Pair
    //   500: astore 6
    //   502: aload 6
    //   504: getstatic 403	gnu/kawa/slib/pregexp:Lit73	Lgnu/math/IntNum;
    //   507: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   510: getstatic 1257	kawa/lib/lists:cdddr	Lgnu/expr/GenericProc;
    //   513: aload 5
    //   515: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   518: astore_0
    //   519: aload_0
    //   520: checkcast 1049	gnu/lists/Pair
    //   523: astore 6
    //   525: aload 6
    //   527: getstatic 697	gnu/kawa/slib/pregexp:Lit8	Lgnu/math/IntNum;
    //   530: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   533: aload_3
    //   534: astore_0
    //   535: goto -300 -> 235
    //   538: aload_3
    //   539: astore_0
    //   540: getstatic 993	kawa/standard/Scheme:isEqv	Lgnu/kawa/functions/IsEqv;
    //   543: iload 7
    //   545: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   548: getstatic 325	gnu/kawa/slib/pregexp:Lit67	Lgnu/text/Char;
    //   551: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   554: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   557: if_acmpeq -322 -> 235
    //   560: aload_1
    //   561: getstatic 1074	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   564: aload_3
    //   565: getstatic 697	gnu/kawa/slib/pregexp:Lit8	Lgnu/math/IntNum;
    //   568: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   571: aload_2
    //   572: invokestatic 1259	gnu/kawa/slib/pregexp:pregexpReadNums	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   575: astore_0
    //   576: aload_0
    //   577: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   580: if_acmpne +23 -> 603
    //   583: iconst_2
    //   584: anewarray 999	java/lang/Object
    //   587: dup
    //   588: iconst_0
    //   589: getstatic 539	gnu/kawa/slib/pregexp:Lit74	Lgnu/mapping/SimpleSymbol;
    //   592: aastore
    //   593: dup
    //   594: iconst_1
    //   595: getstatic 535	gnu/kawa/slib/pregexp:Lit75	Lgnu/mapping/SimpleSymbol;
    //   598: aastore
    //   599: invokestatic 1111	gnu/kawa/slib/pregexp:pregexpError$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   602: pop
    //   603: getstatic 1254	kawa/lib/lists:cddr	Lgnu/expr/GenericProc;
    //   606: aload 5
    //   608: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   611: astore_3
    //   612: aload_3
    //   613: checkcast 1049	gnu/lists/Pair
    //   616: astore 6
    //   618: aload 6
    //   620: getstatic 981	kawa/lib/lists:car	Lgnu/expr/GenericProc;
    //   623: aload_0
    //   624: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   627: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   630: getstatic 1257	kawa/lib/lists:cdddr	Lgnu/expr/GenericProc;
    //   633: aload 5
    //   635: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   638: astore_3
    //   639: aload_3
    //   640: checkcast 1049	gnu/lists/Pair
    //   643: astore 6
    //   645: aload 6
    //   647: getstatic 1163	kawa/lib/lists:cadr	Lgnu/expr/GenericProc;
    //   650: aload_0
    //   651: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   654: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   657: getstatic 1262	kawa/lib/lists:caddr	Lgnu/expr/GenericProc;
    //   660: aload_0
    //   661: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   664: astore_0
    //   665: goto -430 -> 235
    //   668: aload_1
    //   669: checkcast 910	java/lang/CharSequence
    //   672: astore_3
    //   673: aload_0
    //   674: checkcast 912	java/lang/Number
    //   677: invokevirtual 916	java/lang/Number:intValue	()I
    //   680: istore 7
    //   682: aload_3
    //   683: iload 7
    //   685: invokestatic 922	kawa/lib/strings:stringRef	(Ljava/lang/CharSequence;I)C
    //   688: istore 7
    //   690: iload 7
    //   692: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   695: invokestatic 1196	kawa/lib/rnrs/unicode:isCharWhitespace	(Lgnu/text/Char;)Z
    //   698: istore 8
    //   700: iload 8
    //   702: ifeq +26 -> 728
    //   705: getstatic 1008	gnu/kawa/slib/pregexp:$Stpregexp$Mnspace$Mnsensitive$Qu$St	Ljava/lang/Object;
    //   708: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   711: if_acmpne +22 -> 733
    //   714: getstatic 1074	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   717: aload_0
    //   718: getstatic 697	gnu/kawa/slib/pregexp:Lit8	Lgnu/math/IntNum;
    //   721: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   724: astore_0
    //   725: goto -479 -> 246
    //   728: iload 8
    //   730: ifne -16 -> 714
    //   733: iload 7
    //   735: invokestatic 303	gnu/text/Char:make	(I)Lgnu/text/Char;
    //   738: getstatic 309	gnu/kawa/slib/pregexp:Lit47	Lgnu/text/Char;
    //   741: invokestatic 957	kawa/lib/characters:isChar$Eq	(Lgnu/text/Char;Lgnu/text/Char;)Z
    //   744: ifeq +55 -> 799
    //   747: getstatic 987	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   750: aload 5
    //   752: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   755: astore_1
    //   756: aload_1
    //   757: checkcast 1049	gnu/lists/Pair
    //   760: astore_2
    //   761: aload_2
    //   762: getstatic 905	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   765: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   768: getstatic 987	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   771: aload 4
    //   773: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   776: astore_1
    //   777: aload_1
    //   778: checkcast 1049	gnu/lists/Pair
    //   781: astore_2
    //   782: aload_2
    //   783: getstatic 1074	gnu/kawa/functions/AddOp:$Pl	Lgnu/kawa/functions/AddOp;
    //   786: aload_0
    //   787: getstatic 697	gnu/kawa/slib/pregexp:Lit8	Lgnu/math/IntNum;
    //   790: invokevirtual 898	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   793: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   796: goto -496 -> 300
    //   799: getstatic 987	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   802: aload 5
    //   804: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   807: astore_1
    //   808: aload_1
    //   809: checkcast 1049	gnu/lists/Pair
    //   812: astore_2
    //   813: aload_2
    //   814: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   817: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   820: getstatic 987	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   823: aload 4
    //   825: invokevirtual 984	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   828: astore_1
    //   829: aload_1
    //   830: checkcast 1049	gnu/lists/Pair
    //   833: astore_2
    //   834: aload_2
    //   835: aload_0
    //   836: invokestatic 1065	kawa/lib/lists:setCar$Ex	(Lgnu/lists/Pair;Ljava/lang/Object;)V
    //   839: goto -539 -> 300
    //   842: astore_0
    //   843: new 933	gnu/mapping/WrongType
    //   846: dup
    //   847: aload_0
    //   848: ldc_w 940
    //   851: iconst_1
    //   852: aload_1
    //   853: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   856: athrow
    //   857: astore_0
    //   858: new 933	gnu/mapping/WrongType
    //   861: dup
    //   862: aload_0
    //   863: ldc_w 940
    //   866: iconst_2
    //   867: aload_3
    //   868: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   871: athrow
    //   872: astore_1
    //   873: new 933	gnu/mapping/WrongType
    //   876: dup
    //   877: aload_1
    //   878: ldc_w 1067
    //   881: iconst_1
    //   882: aload_0
    //   883: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   886: athrow
    //   887: astore_1
    //   888: new 933	gnu/mapping/WrongType
    //   891: dup
    //   892: aload_1
    //   893: ldc_w 1067
    //   896: iconst_1
    //   897: aload_0
    //   898: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   901: athrow
    //   902: astore_1
    //   903: new 933	gnu/mapping/WrongType
    //   906: dup
    //   907: aload_1
    //   908: ldc_w 1067
    //   911: iconst_1
    //   912: aload_0
    //   913: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   916: athrow
    //   917: astore_1
    //   918: new 933	gnu/mapping/WrongType
    //   921: dup
    //   922: aload_1
    //   923: ldc_w 1067
    //   926: iconst_1
    //   927: aload_0
    //   928: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   931: athrow
    //   932: astore_1
    //   933: new 933	gnu/mapping/WrongType
    //   936: dup
    //   937: aload_1
    //   938: ldc_w 1067
    //   941: iconst_1
    //   942: aload_0
    //   943: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   946: athrow
    //   947: astore_1
    //   948: new 933	gnu/mapping/WrongType
    //   951: dup
    //   952: aload_1
    //   953: ldc_w 1067
    //   956: iconst_1
    //   957: aload_0
    //   958: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   961: athrow
    //   962: astore_0
    //   963: new 933	gnu/mapping/WrongType
    //   966: dup
    //   967: aload_0
    //   968: ldc_w 1067
    //   971: iconst_1
    //   972: aload_3
    //   973: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   976: athrow
    //   977: astore_0
    //   978: new 933	gnu/mapping/WrongType
    //   981: dup
    //   982: aload_0
    //   983: ldc_w 1067
    //   986: iconst_1
    //   987: aload_3
    //   988: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   991: athrow
    //   992: astore_0
    //   993: new 933	gnu/mapping/WrongType
    //   996: dup
    //   997: aload_0
    //   998: ldc_w 1067
    //   1001: iconst_1
    //   1002: aload_1
    //   1003: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1006: athrow
    //   1007: astore_0
    //   1008: new 933	gnu/mapping/WrongType
    //   1011: dup
    //   1012: aload_0
    //   1013: ldc_w 1067
    //   1016: iconst_1
    //   1017: aload_1
    //   1018: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1021: athrow
    //   1022: astore_0
    //   1023: new 933	gnu/mapping/WrongType
    //   1026: dup
    //   1027: aload_0
    //   1028: ldc_w 940
    //   1031: iconst_1
    //   1032: aload_1
    //   1033: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1036: athrow
    //   1037: astore_1
    //   1038: new 933	gnu/mapping/WrongType
    //   1041: dup
    //   1042: aload_1
    //   1043: ldc_w 940
    //   1046: iconst_2
    //   1047: aload_0
    //   1048: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1051: athrow
    //   1052: astore_0
    //   1053: new 933	gnu/mapping/WrongType
    //   1056: dup
    //   1057: aload_0
    //   1058: ldc_w 1067
    //   1061: iconst_1
    //   1062: aload_1
    //   1063: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1066: athrow
    //   1067: astore_0
    //   1068: new 933	gnu/mapping/WrongType
    //   1071: dup
    //   1072: aload_0
    //   1073: ldc_w 1067
    //   1076: iconst_1
    //   1077: aload_1
    //   1078: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1081: athrow
    //   1082: astore_0
    //   1083: new 933	gnu/mapping/WrongType
    //   1086: dup
    //   1087: aload_0
    //   1088: ldc_w 1067
    //   1091: iconst_1
    //   1092: aload_1
    //   1093: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1096: athrow
    //   1097: astore_0
    //   1098: new 933	gnu/mapping/WrongType
    //   1101: dup
    //   1102: aload_0
    //   1103: ldc_w 1067
    //   1106: iconst_1
    //   1107: aload_1
    //   1108: invokespecial 938	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1111: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1112	0	paramObject1	Object
    //   0	1112	1	paramObject2	Object
    //   0	1112	2	paramObject3	Object
    //   16	972	3	localObject1	Object
    //   7	817	4	localObject2	Object
    //   37	766	5	localObject3	Object
    //   200	446	6	localPair	Pair
    //   46	688	7	i	int
    //   65	664	8	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   33	39	842	java/lang/ClassCastException
    //   39	48	857	java/lang/ClassCastException
    //   196	202	872	java/lang/ClassCastException
    //   219	225	887	java/lang/ClassCastException
    //   425	431	902	java/lang/ClassCastException
    //   448	454	917	java/lang/ClassCastException
    //   496	502	932	java/lang/ClassCastException
    //   519	525	947	java/lang/ClassCastException
    //   612	618	962	java/lang/ClassCastException
    //   639	645	977	java/lang/ClassCastException
    //   269	274	992	java/lang/ClassCastException
    //   290	295	1007	java/lang/ClassCastException
    //   668	673	1022	java/lang/ClassCastException
    //   673	682	1037	java/lang/ClassCastException
    //   756	761	1052	java/lang/ClassCastException
    //   777	782	1067	java/lang/ClassCastException
    //   808	813	1082	java/lang/ClassCastException
    //   829	834	1097	java/lang/ClassCastException
  }
  
  public Object apply0(ModuleMethod paramModuleMethod)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply0(paramModuleMethod);
    case 36: 
      return frame.lambda4();
    case 37: 
      return frame0.lambda13();
    case 38: 
      return frame0.lambda14();
    case 39: 
      return frame0.lambda15();
    case 40: 
      return frame0.lambda16();
    }
    return frame0.lambda17();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 16: 
      return pregexpReverse$Ex(paramObject);
    case 28: 
      return pregexpInvertCharList(paramObject);
    case 31: 
      if (isPregexpCharWord(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 35: 
      return pregexpMakeBackrefList(paramObject);
    case 44: 
      return pregexp(paramObject);
    }
    return pregexpQuote(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    case 33: 
      return isPregexpCheckIfInCharClass(paramObject1, paramObject2);
    case 34: 
      return pregexpListRef(paramObject1, paramObject2);
    }
    return pregexpSplit(paramObject1, paramObject2);
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    switch (paramModuleMethod.selector)
    {
    case 28: 
    case 30: 
    case 31: 
    case 33: 
    case 34: 
    case 35: 
    case 36: 
    case 37: 
    case 38: 
    case 39: 
    case 40: 
    case 41: 
    case 42: 
    case 43: 
    case 44: 
    case 45: 
    case 46: 
    case 47: 
    default: 
      return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
    case 18: 
      return pregexpReadPattern(paramObject1, paramObject2, paramObject3);
    case 19: 
      return pregexpReadBranch(paramObject1, paramObject2, paramObject3);
    case 20: 
      return pregexpReadPiece(paramObject1, paramObject2, paramObject3);
    case 21: 
      return pregexpReadEscapedNumber(paramObject1, paramObject2, paramObject3);
    case 22: 
      return pregexpReadEscapedChar(paramObject1, paramObject2, paramObject3);
    case 23: 
      return pregexpReadPosixCharClass(paramObject1, paramObject2, paramObject3);
    case 24: 
      return pregexpReadClusterType(paramObject1, paramObject2, paramObject3);
    case 25: 
      return pregexpReadSubpattern(paramObject1, paramObject2, paramObject3);
    case 26: 
      return pregexpWrapQuantifierIfAny(paramObject1, paramObject2, paramObject3);
    case 27: 
      return pregexpReadNums(paramObject1, paramObject2, paramObject3);
    case 29: 
      return pregexpReadCharList(paramObject1, paramObject2, paramObject3);
    case 32: 
      return isPregexpAtWordBoundary(paramObject1, paramObject2, paramObject3);
    case 48: 
      return pregexpReplace(paramObject1, paramObject2, paramObject3);
    }
    return pregexpReplace$St(paramObject1, paramObject2, paramObject3);
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    if (paramModuleMethod.selector == 43) {
      return pregexpReplaceAux(paramObject1, paramObject2, paramObject3, paramObject4);
    }
    return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.applyN(paramModuleMethod, paramArrayOfObject);
    case 17: 
      return pregexpError$V(paramArrayOfObject);
    case 30: 
      return pregexpStringMatch(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramArrayOfObject[4], paramArrayOfObject[5]);
    case 42: 
      return pregexpMatchPositionsAux(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramArrayOfObject[4], paramArrayOfObject[5]);
    case 45: 
      paramModuleMethod = paramArrayOfObject[0];
      localObject = paramArrayOfObject[1];
      i = paramArrayOfObject.length - 2;
      arrayOfObject = new Object[i];
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          return pregexpMatchPositions$V(paramModuleMethod, localObject, arrayOfObject);
        }
        arrayOfObject[i] = paramArrayOfObject[(i + 2)];
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
        return pregexpMatch$V(paramModuleMethod, localObject, arrayOfObject);
      }
      arrayOfObject[i] = paramArrayOfObject[(i + 2)];
    }
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match0(paramModuleMethod, paramCallContext);
    case 41: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 40: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 39: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 38: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 37: 
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
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 50: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 44: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 35: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 31: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 28: 
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
    case 47: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 34: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
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
    case 28: 
    case 30: 
    case 31: 
    case 33: 
    case 34: 
    case 35: 
    case 36: 
    case 37: 
    case 38: 
    case 39: 
    case 40: 
    case 41: 
    case 42: 
    case 43: 
    case 44: 
    case 45: 
    case 46: 
    case 47: 
    default: 
      return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
    case 49: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 48: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 32: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 29: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 27: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 26: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 25: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 24: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 23: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 22: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 21: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 20: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 19: 
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
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 43)
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
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
    case 46: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 45: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 42: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 30: 
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
    $Stpregexp$Mnversion$St = Lit0;
    $Stpregexp$Mncomment$Mnchar$St = Lit1;
    $Stpregexp$Mnnul$Mnchar$Mnint$St = Integer.valueOf(characters.char$To$Integer(Lit2) - 97);
    $Stpregexp$Mnreturn$Mnchar$St = characters.integer$To$Char(((Number)$Stpregexp$Mnnul$Mnchar$Mnint$St).intValue() + 13);
    $Stpregexp$Mntab$Mnchar$St = characters.integer$To$Char(((Number)$Stpregexp$Mnnul$Mnchar$Mnint$St).intValue() + 9);
    $Stpregexp$Mnspace$Mnsensitive$Qu$St = Boolean.TRUE;
  }
  
  public class frame
    extends ModuleBody
  {
    Object backrefs;
    Object case$Mnsensitive$Qu;
    Procedure identity;
    Object n;
    Object s;
    Object sn;
    Object start;
    
    public frame()
    {
      this$1 = new ModuleMethod(this, 15, pregexp.Lit112, 4097);
      this$1.setProperty("source-location", "pregexp.scm:460");
      this.identity = this$1;
    }
    
    public static Object lambda2identity(Object paramObject)
    {
      return paramObject;
    }
    
    static Boolean lambda4()
    {
      return Boolean.FALSE;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 15) {
        return lambda2identity(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    public Object lambda3sub(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 15)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame0
    extends ModuleBody
  {
    boolean could$Mnloop$Mninfinitely$Qu;
    Object fk;
    Object i;
    final ModuleMethod lambda$Fn11;
    final ModuleMethod lambda$Fn12;
    final ModuleMethod lambda$Fn2;
    final ModuleMethod lambda$Fn3;
    final ModuleMethod lambda$Fn4;
    final ModuleMethod lambda$Fn5;
    boolean maximal$Qu;
    Object old;
    Object p;
    Object q;
    Object re;
    Object re$1;
    Object sk;
    pregexp.frame staticLink;
    
    public frame0()
    {
      this$1 = new ModuleMethod(this, 9, null, 4097);
      this$1.setProperty("source-location", "pregexp.scm:513");
      this.lambda$Fn2 = this$1;
      this$1 = new ModuleMethod(this, 10, null, 0);
      this$1.setProperty("source-location", "pregexp.scm:514");
      this.lambda$Fn3 = this$1;
      this$1 = new ModuleMethod(this, 11, null, 4097);
      this$1.setProperty("source-location", "pregexp.scm:541");
      this.lambda$Fn4 = this$1;
      this$1 = new ModuleMethod(this, 12, null, 4097);
      this$1.setProperty("source-location", "pregexp.scm:545");
      this.lambda$Fn5 = this$1;
      this$1 = new ModuleMethod(this, 13, null, 4097);
      this$1.setProperty("source-location", "pregexp.scm:587");
      this.lambda$Fn11 = this$1;
      this$1 = new ModuleMethod(this, 14, null, 0);
      this$1.setProperty("source-location", "pregexp.scm:590");
      this.lambda$Fn12 = this$1;
    }
    
    static Boolean lambda13()
    {
      return Boolean.FALSE;
    }
    
    static Boolean lambda14()
    {
      return Boolean.FALSE;
    }
    
    static Boolean lambda15()
    {
      return Boolean.FALSE;
    }
    
    static Boolean lambda16()
    {
      return Boolean.FALSE;
    }
    
    static Boolean lambda17()
    {
      return Boolean.FALSE;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.apply0(paramModuleMethod);
      case 10: 
        return lambda10();
      }
      return lambda19();
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      switch (paramModuleMethod.selector)
      {
      case 10: 
      default: 
        return super.apply1(paramModuleMethod, paramObject);
      case 9: 
        return lambda9(paramObject);
      case 11: 
        return lambda11(paramObject);
      case 12: 
        return lambda12(paramObject);
      }
      return lambda18(paramObject);
    }
    
    Object lambda10()
    {
      return Scheme.applyToArgs.apply2(this.sk, AddOp.$Pl.apply2(this.i, pregexp.Lit8));
    }
    
    Object lambda11(Object paramObject)
    {
      return Scheme.applyToArgs.apply2(this.sk, paramObject);
    }
    
    Object lambda12(Object paramObject)
    {
      Object localObject = lists.assv(this.re$1, this.staticLink.backrefs);
      try
      {
        Pair localPair = (Pair)localObject;
        lists.setCdr$Ex(localPair, lists.cons(this.i, paramObject));
        return Scheme.applyToArgs.apply2(this.sk, paramObject);
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "set-cdr!", 1, localObject);
      }
    }
    
    Object lambda18(Object paramObject)
    {
      this.staticLink.case$Mnsensitive$Qu = this.old;
      return Scheme.applyToArgs.apply2(this.sk, paramObject);
    }
    
    Object lambda19()
    {
      this.staticLink.case$Mnsensitive$Qu = this.old;
      return Scheme.applyToArgs.apply1(this.fk);
    }
    
    public Object lambda5loupOneOfChars(Object paramObject)
    {
      pregexp.frame1 localframe1 = new pregexp.frame1();
      localframe1.staticLink = this;
      localframe1.chars = paramObject;
      if (lists.isNull(localframe1.chars)) {
        return Scheme.applyToArgs.apply1(this.fk);
      }
      return this.staticLink.lambda3sub(lists.car.apply1(localframe1.chars), this.i, this.sk, localframe1.lambda$Fn13);
    }
    
    public Object lambda6loupSeq(Object paramObject1, Object paramObject2)
    {
      pregexp.frame2 localframe2 = new pregexp.frame2();
      localframe2.staticLink = this;
      localframe2.res = paramObject1;
      if (lists.isNull(localframe2.res)) {
        return Scheme.applyToArgs.apply2(this.sk, paramObject2);
      }
      return this.staticLink.lambda3sub(lists.car.apply1(localframe2.res), paramObject2, localframe2.lambda$Fn14, this.fk);
    }
    
    public Object lambda7loupOr(Object paramObject)
    {
      pregexp.frame3 localframe3 = new pregexp.frame3();
      localframe3.staticLink = this;
      localframe3.res = paramObject;
      if (lists.isNull(localframe3.res)) {
        return Scheme.applyToArgs.apply1(this.fk);
      }
      return this.staticLink.lambda3sub(lists.car.apply1(localframe3.res), this.i, localframe3.lambda$Fn15, localframe3.lambda$Fn16);
    }
    
    public Object lambda8loupP(Object paramObject1, Object paramObject2)
    {
      pregexp.frame4 localframe4 = new pregexp.frame4();
      localframe4.staticLink = this;
      localframe4.k = paramObject1;
      localframe4.i = paramObject2;
      if (Scheme.numLss.apply2(localframe4.k, this.p) != Boolean.FALSE) {
        return this.staticLink.lambda3sub(this.re, localframe4.i, localframe4.lambda$Fn17, this.fk);
      }
      if (this.q != Boolean.FALSE) {}
      for (paramObject1 = AddOp.$Mn.apply2(this.q, this.p);; paramObject1 = this.q)
      {
        localframe4.q = paramObject1;
        return localframe4.lambda24loupQ(pregexp.Lit73, localframe4.i);
      }
    }
    
    Object lambda9(Object paramObject)
    {
      return Scheme.applyToArgs.apply1(this.fk);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.match0(paramModuleMethod, paramCallContext);
      case 14: 
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
      case 10: 
      default: 
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 13: 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 12: 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 11: 
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
    Object chars;
    final ModuleMethod lambda$Fn13;
    pregexp.frame0 staticLink;
    
    public frame1()
    {
      this$1 = new ModuleMethod(this, 1, null, 0);
      this$1.setProperty("source-location", "pregexp.scm:508");
      this.lambda$Fn13 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 1) {
        return lambda20();
      }
      return super.apply0(paramModuleMethod);
    }
    
    Object lambda20()
    {
      return this.staticLink.lambda5loupOneOfChars(lists.cdr.apply1(this.chars));
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 1)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
  }
  
  public class frame2
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn14;
    Object res;
    pregexp.frame0 staticLink;
    
    public frame2()
    {
      this$1 = new ModuleMethod(this, 2, null, 4097);
      this$1.setProperty("source-location", "pregexp.scm:519");
      this.lambda$Fn14 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 2) {
        return lambda21(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda21(Object paramObject)
    {
      return this.staticLink.lambda6loupSeq(lists.cdr.apply1(this.res), paramObject);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 2)
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
    final ModuleMethod lambda$Fn15;
    final ModuleMethod lambda$Fn16;
    Object res;
    pregexp.frame0 staticLink;
    
    public frame3()
    {
      this$1 = new ModuleMethod(this, 3, null, 4097);
      this$1.setProperty("source-location", "pregexp.scm:526");
      this.lambda$Fn15 = this$1;
      this$1 = new ModuleMethod(this, 4, null, 0);
      this$1.setProperty("source-location", "pregexp.scm:529");
      this.lambda$Fn16 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 4) {
        return lambda23();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 3) {
        return lambda22(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda22(Object paramObject)
    {
      paramObject = Scheme.applyToArgs.apply2(this.staticLink.sk, paramObject);
      if (paramObject != Boolean.FALSE) {
        return paramObject;
      }
      return this.staticLink.lambda7loupOr(lists.cdr.apply1(this.res));
    }
    
    Object lambda23()
    {
      return this.staticLink.lambda7loupOr(lists.cdr.apply1(this.res));
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 4)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
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
  }
  
  public class frame4
    extends ModuleBody
  {
    Object i;
    Object k;
    final ModuleMethod lambda$Fn17;
    Object q;
    pregexp.frame0 staticLink;
    
    public frame4()
    {
      this$1 = new ModuleMethod(this, 8, null, 4097);
      this$1.setProperty("source-location", "pregexp.scm:602");
      this.lambda$Fn17 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 8) {
        return lambda25(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    public Object lambda24loupQ(Object paramObject1, Object paramObject2)
    {
      pregexp.frame5 localframe5 = new pregexp.frame5();
      localframe5.staticLink = this;
      localframe5.k = paramObject1;
      localframe5.i = paramObject2;
      localframe5.fk = localframe5.fk;
      if (this.q != Boolean.FALSE)
      {
        if (Scheme.numGEq.apply2(localframe5.k, this.q) == Boolean.FALSE) {
          break label78;
        }
        paramObject1 = localframe5.lambda26fk();
      }
      label78:
      do
      {
        return paramObject1;
        if (this.q != Boolean.FALSE) {
          break;
        }
        if (this.staticLink.maximal$Qu) {
          return this.staticLink.staticLink.lambda3sub(this.staticLink.re, localframe5.i, localframe5.lambda$Fn18, localframe5.fk);
        }
        paramObject2 = localframe5.lambda26fk();
        paramObject1 = paramObject2;
      } while (paramObject2 != Boolean.FALSE);
      return this.staticLink.staticLink.lambda3sub(this.staticLink.re, localframe5.i, localframe5.lambda$Fn19, localframe5.fk);
    }
    
    Object lambda25(Object paramObject)
    {
      if (this.staticLink.could$Mnloop$Mninfinitely$Qu) {
        if (Scheme.numEqu.apply2(paramObject, this.i) == Boolean.FALSE) {}
      }
      for (;;)
      {
        pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 });
        do
        {
          return this.staticLink.lambda8loupP(AddOp.$Pl.apply2(this.k, pregexp.Lit8), paramObject);
        } while (!this.staticLink.could$Mnloop$Mninfinitely$Qu);
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
  
  public class frame5
    extends ModuleBody
  {
    Procedure fk;
    Object i;
    Object k;
    final ModuleMethod lambda$Fn18;
    final ModuleMethod lambda$Fn19;
    pregexp.frame4 staticLink;
    
    public frame5()
    {
      this$1 = new ModuleMethod(this, 5, pregexp.Lit111, 0);
      this$1.setProperty("source-location", "pregexp.scm:612");
      this.fk = this$1;
      this$1 = new ModuleMethod(this, 6, null, 4097);
      this$1.setProperty("source-location", "pregexp.scm:617");
      this.lambda$Fn18 = this$1;
      this$1 = new ModuleMethod(this, 7, null, 4097);
      this$1.setProperty("source-location", "pregexp.scm:628");
      this.lambda$Fn19 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 5) {
        return lambda26fk();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.apply1(paramModuleMethod, paramObject);
      case 6: 
        return lambda27(paramObject);
      }
      return lambda28(paramObject);
    }
    
    public Object lambda26fk()
    {
      return Scheme.applyToArgs.apply2(this.staticLink.staticLink.sk, this.i);
    }
    
    Object lambda27(Object paramObject)
    {
      if (this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu) {
        if (Scheme.numEqu.apply2(paramObject, this.i) == Boolean.FALSE) {}
      }
      for (;;)
      {
        pregexp.pregexpError$V(new Object[] { pregexp.Lit101, pregexp.Lit110 });
        do
        {
          paramObject = this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), paramObject);
          if (paramObject == Boolean.FALSE) {
            break;
          }
          return paramObject;
        } while (!this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu);
      }
      return lambda26fk();
    }
    
    Object lambda28(Object paramObject)
    {
      return this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.k, pregexp.Lit8), paramObject);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 5)
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
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\pregexp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */