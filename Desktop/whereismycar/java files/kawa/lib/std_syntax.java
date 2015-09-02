package kawa.lib;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.QuoteExp;
import gnu.expr.Symbols;
import gnu.kawa.functions.AddOp;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.Eval;
import kawa.lang.Macro;
import kawa.lang.Pattern;
import kawa.lang.Quote;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;
import kawa.standard.Scheme;
import kawa.standard.syntax_case;

public class std_syntax
  extends ModuleBody
{
  public static final Macro $Prvt$$Pccase;
  public static final Macro $Prvt$$Pccase$Mnmatch;
  public static final Macro $Prvt$$Pcdo$Mninit;
  public static final Macro $Prvt$$Pcdo$Mnlambda1;
  public static final Macro $Prvt$$Pcdo$Mnlambda2;
  public static final Macro $Prvt$$Pcdo$Mnstep;
  public static final Macro $Prvt$$Pclet$Mninit;
  public static final Macro $Prvt$$Pclet$Mnlambda1;
  public static final Macro $Prvt$$Pclet$Mnlambda2;
  public static final Location $Prvt$define;
  public static final Location $Prvt$define$Mnconstant;
  public static final Location $Prvt$if;
  public static final Location $Prvt$letrec;
  public static final std_syntax $instance;
  static final IntNum Lit0;
  static final IntNum Lit1;
  static final SimpleSymbol Lit10;
  static final SyntaxPattern Lit11;
  static final SyntaxPattern Lit12;
  static final SyntaxTemplate Lit13;
  static final SyntaxPattern Lit14;
  static final SyntaxTemplate Lit15;
  static final SimpleSymbol Lit16;
  static final SyntaxPattern Lit17;
  static final SyntaxPattern Lit18;
  static final SyntaxTemplate Lit19;
  static final SimpleSymbol Lit2;
  static final SyntaxPattern Lit20;
  static final SyntaxTemplate Lit21;
  static final SimpleSymbol Lit22;
  static final SyntaxRules Lit23;
  static final SimpleSymbol Lit24;
  static final SyntaxRules Lit25;
  static final SimpleSymbol Lit26;
  static final SyntaxRules Lit27;
  static final SimpleSymbol Lit28;
  static final SyntaxRules Lit29;
  static final SyntaxRules Lit3;
  static final SimpleSymbol Lit30;
  static final SyntaxRules Lit31;
  static final SimpleSymbol Lit32;
  static final SyntaxRules Lit33;
  static final SimpleSymbol Lit34;
  static final SyntaxRules Lit35;
  static final SimpleSymbol Lit36;
  static final SyntaxRules Lit37;
  static final SimpleSymbol Lit38;
  static final SyntaxRules Lit39;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit40;
  static final SyntaxRules Lit41;
  static final SimpleSymbol Lit42;
  static final SyntaxRules Lit43;
  static final SimpleSymbol Lit44;
  static final SyntaxRules Lit45;
  static final SimpleSymbol Lit46;
  static final SimpleSymbol Lit47;
  static final SimpleSymbol Lit48;
  static final SimpleSymbol Lit49;
  static final SyntaxRules Lit5;
  static final SimpleSymbol Lit50;
  static final SimpleSymbol Lit51;
  static final SimpleSymbol Lit52;
  static final SimpleSymbol Lit53;
  static final SimpleSymbol Lit54;
  static final SyntaxPattern Lit55;
  static final SimpleSymbol Lit56;
  static final SyntaxTemplate Lit57;
  static final SyntaxTemplate Lit58;
  static final SimpleSymbol Lit59;
  static final SimpleSymbol Lit6;
  static final SyntaxRules Lit60;
  static final SimpleSymbol Lit61;
  static final SyntaxRules Lit62;
  static final SimpleSymbol Lit63;
  static final SimpleSymbol Lit64;
  static final SimpleSymbol Lit65;
  static final SimpleSymbol Lit66;
  static final SimpleSymbol Lit67;
  static final SimpleSymbol Lit68;
  static final SimpleSymbol Lit69;
  static final SyntaxRules Lit7;
  static final SimpleSymbol Lit70;
  static final SimpleSymbol Lit71;
  static final SimpleSymbol Lit72;
  static final SimpleSymbol Lit73;
  static final SimpleSymbol Lit74;
  static final SimpleSymbol Lit75;
  static final SimpleSymbol Lit76;
  static final SimpleSymbol Lit77 = (SimpleSymbol)new SimpleSymbol("temp").readResolve();
  static final SimpleSymbol Lit8;
  static final SyntaxRules Lit9;
  public static final Macro and;
  public static final Macro begin$Mnfor$Mnsyntax;
  public static final Macro jdField_case;
  public static final Macro cond;
  public static final ModuleMethod datum$Mn$Grsyntax$Mnobject;
  public static final Macro define$Mnfor$Mnsyntax;
  public static final Macro define$Mnprocedure;
  public static final Macro delay;
  public static final Macro jdField_do;
  public static final ModuleMethod free$Mnidentifier$Eq$Qu;
  public static final ModuleMethod generate$Mntemporaries;
  public static final ModuleMethod identifier$Qu;
  public static final Macro let;
  public static final Macro let$St;
  public static final Macro or;
  public static final ModuleMethod syntax$Mncolumn;
  public static final ModuleMethod syntax$Mnline;
  public static final ModuleMethod syntax$Mnobject$Mn$Grdatum;
  public static final ModuleMethod syntax$Mnsource;
  public static final Macro with$Mnsyntax;
  
  static
  {
    Lit76 = (SimpleSymbol)new SimpleSymbol("=>").readResolve();
    Lit75 = (SimpleSymbol)new SimpleSymbol("else").readResolve();
    Lit74 = (SimpleSymbol)new SimpleSymbol("eqv?").readResolve();
    Lit73 = (SimpleSymbol)new SimpleSymbol("x").readResolve();
    Lit72 = (SimpleSymbol)new SimpleSymbol("if").readResolve();
    Lit71 = (SimpleSymbol)new SimpleSymbol("letrec").readResolve();
    Lit70 = (SimpleSymbol)new SimpleSymbol("%let").readResolve();
    Lit69 = (SimpleSymbol)new SimpleSymbol("%syntax-error").readResolve();
    Lit68 = (SimpleSymbol)new SimpleSymbol("lambda").readResolve();
    Lit67 = (SimpleSymbol)new SimpleSymbol("make").readResolve();
    Lit66 = (SimpleSymbol)new SimpleSymbol("quote").readResolve();
    Lit65 = (SimpleSymbol)new SimpleSymbol("<gnu.expr.GenericProc>").readResolve();
    Lit64 = (SimpleSymbol)new SimpleSymbol("::").readResolve();
    Lit63 = (SimpleSymbol)new SimpleSymbol("syntax-case").readResolve();
    Object localObject1 = (SimpleSymbol)new SimpleSymbol("with-syntax").readResolve();
    Lit61 = (SimpleSymbol)localObject1;
    Object localObject2 = new SyntaxPattern("\f\030\f\b\f\007\r\017\b\b\b", new Object[0], 2);
    Object localObject3 = (SimpleSymbol)new SimpleSymbol("begin").readResolve();
    Lit56 = (SimpleSymbol)localObject3;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\001\003", "\021\030\004\t\003\b\r\013", new Object[] { localObject3 }, 1);
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030<,\f\007\f\017\b\b\f\027\r\037\030\b\b", new Object[0], 4), "\001\001\001\003", "\021\030\004\t\013\t\020\b\t\003\b\021\030\f\t\023\b\035\033", new Object[] { Lit63, Lit56 }, 1);
    Object localObject4 = new SyntaxRule(new SyntaxPattern("\f\030L-\f\007\f\017\b\000\020\b\f\027\r\037\030\b\b", new Object[0], 4), "\003\003\001\003", "\021\030\0041\021\030\f\b\r\013\t\020\b\031\b\005\003\b\021\030\024\t\023\b\035\033", new Object[] { Lit63, (SimpleSymbol)new SimpleSymbol("list").readResolve(), Lit56 }, 1);
    Lit62 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2, localObject3, localObject4 }, 4);
    localObject1 = (SimpleSymbol)new SimpleSymbol("define-for-syntax").readResolve();
    Lit59 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxPattern("\f\030\003", new Object[0], 1);
    localObject3 = (SimpleSymbol)new SimpleSymbol("begin-for-syntax").readResolve();
    Lit54 = (SimpleSymbol)localObject3;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\000", "\021\030\004\b\021\030\f\002", new Object[] { localObject3, (SimpleSymbol)new SimpleSymbol("define").readResolve() }, 0);
    Lit60 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 1);
    Lit58 = new SyntaxTemplate("\001\000", "\030\004", new Object[] { Values.empty }, 0);
    Lit57 = new SyntaxTemplate("\001\000", "\n", new Object[0], 0);
    Lit55 = new SyntaxPattern("\f\007\013", new Object[0], 2);
    Lit53 = (SimpleSymbol)new SimpleSymbol("syntax-column").readResolve();
    Lit52 = (SimpleSymbol)new SimpleSymbol("syntax-line").readResolve();
    Lit51 = (SimpleSymbol)new SimpleSymbol("syntax-source").readResolve();
    Lit50 = (SimpleSymbol)new SimpleSymbol("free-identifier=?").readResolve();
    Lit49 = (SimpleSymbol)new SimpleSymbol("identifier?").readResolve();
    Lit48 = (SimpleSymbol)new SimpleSymbol("generate-temporaries").readResolve();
    Lit47 = (SimpleSymbol)new SimpleSymbol("datum->syntax-object").readResolve();
    Lit46 = (SimpleSymbol)new SimpleSymbol("syntax-object->datum").readResolve();
    localObject1 = (SimpleSymbol)new SimpleSymbol("define-procedure").readResolve();
    Lit44 = (SimpleSymbol)localObject1;
    localObject2 = Lit64;
    localObject3 = Lit65;
    localObject4 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\r\017\b\b\b", new Object[0], 2), "\001\003", "\021\030\004Á\021\030\f\t\003\021\030\024\021\030\034\b\021\030$\021\030\034\b\021\030,\b\003\b\021\0304\t\003\021\030<\b\021\030D\b\r\013", new Object[] { Lit56, (SimpleSymbol)new SimpleSymbol("define-constant").readResolve(), Lit64, Lit65, Lit67, Lit66, (SimpleSymbol)new SimpleSymbol("invoke").readResolve(), PairWithPosition.make(Lit66, PairWithPosition.make((SimpleSymbol)new SimpleSymbol("setProperties").readResolve(), LList.Empty, "std_syntax.scm", 1024020), "std_syntax.scm", 1024020), (SimpleSymbol)new SimpleSymbol("java.lang.Object[]").readResolve() }, 1);
    Lit45 = new SyntaxRules(new Object[] { localObject1, localObject2, localObject3 }, new SyntaxRule[] { localObject4 }, 2);
    localObject1 = (SimpleSymbol)new SimpleSymbol("delay").readResolve();
    Lit42 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\021\030\f\b\021\030\024\t\020\b\003", new Object[] { Lit67, (SimpleSymbol)new SimpleSymbol("<kawa.lang.Promise>").readResolve(), Lit68 }, 0);
    Lit43 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 1);
    localObject1 = (SimpleSymbol)new SimpleSymbol("do").readResolve();
    Lit40 = (SimpleSymbol)localObject1;
    localObject2 = Lit64;
    localObject3 = new SyntaxPattern("\f\030,\r\007\000\b\b\034\f\017\023\r\037\030\b\b", new Object[0], 4);
    localObject4 = Lit71;
    Object localObject5 = (SimpleSymbol)new SimpleSymbol("%do%loop").readResolve();
    Object localObject6 = (SimpleSymbol)new SimpleSymbol("%do-lambda1").readResolve();
    Lit36 = (SimpleSymbol)localObject6;
    Object localObject7 = Lit72;
    Object localObject8 = (SimpleSymbol)new SimpleSymbol("not").readResolve();
    Object localObject9 = Lit56;
    Object localObject10 = (SimpleSymbol)new SimpleSymbol("%do-step").readResolve();
    Lit32 = (SimpleSymbol)localObject10;
    Object localObject11 = Values.empty;
    SimpleSymbol localSimpleSymbol = (SimpleSymbol)new SimpleSymbol("%do-init").readResolve();
    Lit34 = localSimpleSymbol;
    localObject3 = new SyntaxRule((SyntaxPattern)localObject3, "\003\001\000\003", "\021\030\004Ɖ\b\021\030\f\b\021\030\024\031\b\005\003\t\020\b\021\030\034)\021\030$\b\013\021\030,\021\035\033\b\021\030\f\b\005\021\0304\003\b\021\030,\021\030<\022\b\021\030\f\b\005\021\030D\b\003", new Object[] { localObject4, localObject5, localObject6, localObject7, localObject8, localObject9, localObject10, localObject11, localSimpleSymbol }, 1);
    Lit41 = new SyntaxRules(new Object[] { localObject1, localObject2 }, new SyntaxRule[] { localObject3 }, 4);
    localObject1 = (SimpleSymbol)new SimpleSymbol("%do-lambda2").readResolve();
    Lit38 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\013\f\027\f\037\b", new Object[0], 4), "\001\000\001\001", "\021\030\004\t\n\031\t\003\023\b\033", new Object[] { Lit38 }, 0);
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030\f\b\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\b\013", new Object[] { Lit68 }, 0);
    Lit39 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2, localObject3 }, 4);
    localObject1 = Lit36;
    localObject2 = Lit64;
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030l\\\f\007\f\002\f\017\f\027\f\037\b#\f/\f7\b", new Object[] { Lit64 }, 7), "\001\001\001\001\000\001\001", "\021\030\004\t\"I9\t\003\021\030\f\b\013+\b3", new Object[] { Lit36, Lit64 }, 0);
    localObject4 = new SyntaxRule(new SyntaxPattern("\f\030\\L\f\007\f\002\f\017\f\027\b\033\f'\f/\b", new Object[] { Lit64 }, 6), "\001\001\001\000\001\001", "\021\030\004\t\032I9\t\003\021\030\f\b\013#\b+", new Object[] { Lit36, Lit64 }, 0);
    localObject5 = new SyntaxRule(new SyntaxPattern("\f\030L<\f\007\f\017\f\027\b\033\f'\f/\b", new Object[0], 6), "\001\001\001\000\001\001", "\021\030\004\t\032\031\t\003#\b+", new Object[] { Lit36 }, 0);
    localObject6 = new SyntaxRule(new SyntaxPattern("\f\030<,\f\007\f\017\b\023\f\037\f'\b", new Object[0], 5), "\001\001\000\001\001", "\021\030\004\t\022\031\t\003\033\b#", new Object[] { Lit36 }, 0);
    localObject7 = new SyntaxRule(new SyntaxPattern("\f\030\f\b\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\t\020\b\013", new Object[] { Lit38 }, 0);
    Lit37 = new SyntaxRules(new Object[] { localObject1, localObject2 }, new SyntaxRule[] { localObject3, localObject4, localObject5, localObject6, localObject7 }, 7);
    localObject1 = Lit34;
    localObject2 = Lit64;
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030\\\f\007\f\002\f\017\f\027\f\037\b\b", new Object[] { Lit64 }, 4), "\001\001\001\001", "\023", new Object[0], 0);
    localObject4 = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\002\f\017\f\027\b\b", new Object[] { Lit64 }, 3), "\001\001\001", "\023", new Object[0], 0);
    localObject5 = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\017\f\027\b\b", new Object[0], 3), "\001\001\001", "\013", new Object[0], 0);
    localObject6 = new SyntaxRule(new SyntaxPattern("\f\030,\f\007\f\017\b\b", new Object[0], 2), "\001\001", "\013", new Object[0], 0);
    localObject7 = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\017\f\027\b\b", new Object[0], 3), "\001\001\001", "\023", new Object[0], 0);
    localObject8 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\b\b", new Object[0], 1), "\001", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("do binding with no value", LList.Empty, "std_syntax.scm", 794643), "std_syntax.scm", 794628) }, 0);
    localObject9 = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\017\f\027\f\037\b\b", new Object[0], 4), "\001\001\001\001", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("do binding must have syntax: (var [:: type] init [step])", LList.Empty, "std_syntax.scm", 806917), "std_syntax.scm", 802820) }, 0);
    Lit35 = new SyntaxRules(new Object[] { localObject1, localObject2 }, new SyntaxRule[] { localObject3, localObject4, localObject5, localObject6, localObject7, localObject8, localObject9 }, 4);
    localObject1 = Lit32;
    localObject2 = Lit64;
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\002\f\017\f\027\f\037\b", new Object[] { Lit64 }, 4), "\001\001\001\001", "\033", new Object[0], 0);
    localObject4 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\002\f\017\f\027\b", new Object[] { Lit64 }, 3), "\001\001\001", "\003", new Object[0], 0);
    localObject5 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3), "\001\001\001", "\023", new Object[0], 0);
    localObject6 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2), "\001\001", "\003", new Object[0], 0);
    Lit33 = new SyntaxRules(new Object[] { localObject1, localObject2 }, new SyntaxRule[] { localObject3, localObject4, localObject5, localObject6 }, 4);
    localObject1 = (SimpleSymbol)new SimpleSymbol("let*").readResolve();
    Lit30 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\f\b\003", new Object[0], 1), "\000", "\021\030\004\t\020\002", new Object[] { Lit70 }, 0);
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\b\013", new Object[0], 2), "\001\000", "\021\030\004\021\b\003\n", new Object[] { Lit70 }, 0);
    localObject4 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3), "\001\000\000", "\021\030\004\021\b\003\b\021\030\f\t\n\022", new Object[] { Lit70, Lit30 }, 0);
    localObject5 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\013", new Object[0], 2), "\001\000", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("invalid bindings list in let*", LList.Empty, "std_syntax.scm", 679943), "std_syntax.scm", 675846) }, 0);
    localObject6 = new SyntaxRule(new SyntaxPattern("\f\030\003", new Object[0], 1), "\000", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("missing bindings list in let*", LList.Empty, "std_syntax.scm", 692231), "std_syntax.scm", 688134) }, 0);
    Lit31 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2, localObject3, localObject4, localObject5, localObject6 }, 3);
    localObject1 = (SimpleSymbol)new SimpleSymbol("let").readResolve();
    Lit28 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030,\r\007\000\b\b\013", new Object[0], 2), "\003\000", "\021\030\004\031\b\005\003\n", new Object[] { Lit70 }, 1);
    localObject3 = new SyntaxPattern("\f\030\f\007,\r\017\b\b\b\023", new Object[0], 3);
    localObject4 = Lit71;
    localObject5 = (SimpleSymbol)new SimpleSymbol("%let-lambda1").readResolve();
    Lit22 = (SimpleSymbol)localObject5;
    localObject6 = (SimpleSymbol)new SimpleSymbol("%let-init").readResolve();
    Lit26 = (SimpleSymbol)localObject6;
    localObject3 = new SyntaxRule((SyntaxPattern)localObject3, "\001\003\000", "©\021\030\004y\b\t\003\b\021\030\f\031\b\r\013\t\020\b\022\b\003\b\r\021\030\024\b\013", new Object[] { localObject4, localObject5, localObject6 }, 1);
    Lit29 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2, localObject3 }, 3);
    localObject1 = Lit26;
    localObject2 = Lit64;
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030,\f\007\f\017\b\b", new Object[0], 2), "\001\001", "\013", new Object[0], 0);
    localObject4 = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\002\f\017\f\027\b\b", new Object[] { Lit64 }, 3), "\001\001\001", "\023", new Object[0], 0);
    localObject5 = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\017\f\027\b\b", new Object[0], 3), "\001\001\001", "\023", new Object[0], 0);
    localObject6 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\b\b", new Object[0], 1), "\001", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("let binding with no value", LList.Empty, "std_syntax.scm", 552979), "std_syntax.scm", 552964) }, 0);
    localObject7 = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\017\f\027\f\037\b\b", new Object[0], 4), "\001\001\001\001", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("let binding must have syntax: (var [type] init)", LList.Empty, "std_syntax.scm", 565253), "std_syntax.scm", 561156) }, 0);
    Lit27 = new SyntaxRules(new Object[] { localObject1, localObject2 }, new SyntaxRule[] { localObject3, localObject4, localObject5, localObject6, localObject7 }, 4);
    localObject1 = (SimpleSymbol)new SimpleSymbol("%let-lambda2").readResolve();
    Lit24 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\013\f\027\f\037\b", new Object[0], 4), "\001\000\001\001", "\021\030\004\t\n\031\t\003\023\b\033", new Object[] { Lit24 }, 0);
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030\f\b\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\013", new Object[] { Lit68 }, 0);
    Lit25 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2, localObject3 }, 4);
    localObject1 = Lit22;
    localObject2 = Lit64;
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030L<\f\007\f\017\f\027\b\033\f'\f/\b", new Object[0], 6), "\001\001\001\000\001\001", "\021\030\004\t\0321!\t\003\b\013#\b+", new Object[] { Lit22 }, 0);
    localObject4 = new SyntaxRule(new SyntaxPattern("\f\030\\L\f\007\f\002\f\017\f\027\b\033\f'\f/\b", new Object[] { Lit64 }, 6), "\001\001\001\000\001\001", "\021\030\004\t\0321!\t\003\b\013#\b+", new Object[] { Lit22 }, 0);
    localObject5 = new SyntaxRule(new SyntaxPattern("\f\030<,\f\007\f\017\b\023\f\037\f'\b", new Object[0], 5), "\001\001\000\001\001", "\021\030\004\t\022\031\t\003\033\b#", new Object[] { Lit22 }, 0);
    localObject6 = new SyntaxRule(new SyntaxPattern("\f\030\f\b\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\t\020\b\013", new Object[] { Lit24 }, 0);
    Lit23 = new SyntaxRules(new Object[] { localObject1, localObject2 }, new SyntaxRule[] { localObject3, localObject4, localObject5, localObject6 }, 6);
    Lit21 = new SyntaxTemplate("\001\001\003", "\021\030\0041\b\021\030\f\b\013\b\021\030\024\021\030\f\021\030\f\b\t\003\b\025\023", new Object[] { Lit70, Lit73, Lit72 }, 1);
    Lit20 = new SyntaxPattern("\f\007\f\017\r\027\020\b\b", new Object[0], 3);
    Lit19 = new SyntaxTemplate("\001\001", "\013", new Object[0], 0);
    Lit18 = new SyntaxPattern("\f\007\f\017\b", new Object[0], 2);
    Lit17 = new SyntaxPattern("\f\007\b", new Object[0], 1);
    Lit16 = (SimpleSymbol)new SimpleSymbol("or").readResolve();
    Lit15 = new SyntaxTemplate("\001\001\003", "\021\030\0041\b\021\030\f\b\013\b\021\030\024\021\030\f)\t\003\b\025\023\030\034", new Object[] { Lit70, Lit73, Lit72, PairWithPosition.make(Lit73, LList.Empty, "std_syntax.scm", 385052) }, 1);
    Lit14 = new SyntaxPattern("\f\007\f\017\r\027\020\b\b", new Object[0], 3);
    Lit13 = new SyntaxTemplate("\001\001", "\013", new Object[0], 0);
    Lit12 = new SyntaxPattern("\f\007\f\017\b", new Object[0], 2);
    Lit11 = new SyntaxPattern("\f\007\b", new Object[0], 1);
    Lit10 = (SimpleSymbol)new SimpleSymbol("and").readResolve();
    localObject1 = (SimpleSymbol)new SimpleSymbol("%case-match").readResolve();
    Lit8 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\b\021\030\f\b\013", new Object[] { Lit74, Lit66 }, 0);
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\r\027\020\b\b", new Object[0], 3), "\001\001\003", "\021\030\004Y\021\030\f\t\003\b\021\030\024\b\013\b\021\030\034\t\003\b\025\023", new Object[] { Lit16, Lit74, Lit66, Lit8 }, 1);
    Lit9 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2, localObject3 }, 3);
    localObject1 = (SimpleSymbol)new SimpleSymbol("%case").readResolve();
    Lit6 = (SimpleSymbol)localObject1;
    localObject2 = Lit75;
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030\f\007<\f\002\r\017\b\b\b\b", new Object[] { Lit75 }, 2), "\001\003", "\021\030\004\b\r\013", new Object[] { Lit56 }, 1);
    localObject4 = new SyntaxRule(new SyntaxPattern("\f\030\f\007<\f\002\r\017\b\b\b\023", new Object[] { Lit75 }, 3), "\001\003\000", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("junk following else (in case)", LList.Empty, "std_syntax.scm", 241674), "std_syntax.scm", 237577) }, 0);
    localObject5 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\\,\r\017\b\b\b\r\027\020\b\b\b", new Object[0], 3), "\001\003\003", "\021\030\004A\021\030\f\t\003\b\r\013\b\021\030\024\b\025\023", new Object[] { Lit72, Lit8, Lit56 }, 1);
    localObject6 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\\,\r\017\b\b\b\r\027\020\b\b\f\037\r' \b\b", new Object[0], 5), "\001\003\003\001\003", "\021\030\004A\021\030\f\t\003\b\r\0131\021\030\024\b\025\023\b\021\030\034\t\003\t\033\b%#", new Object[] { Lit72, Lit8, Lit56, Lit6 }, 1);
    Lit7 = new SyntaxRules(new Object[] { localObject1, localObject2 }, new SyntaxRule[] { localObject3, localObject4, localObject5, localObject6 }, 5);
    localObject1 = (SimpleSymbol)new SimpleSymbol("case").readResolve();
    Lit4 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\r\017\b\b\b", new Object[0], 2), "\001\003", "\021\030\0041\b\021\030\f\b\003\b\021\030\024\021\030\f\b\r\013", new Object[] { Lit70, (SimpleSymbol)new SimpleSymbol("tmp").readResolve(), Lit6 }, 1);
    Lit5 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 2);
    localObject1 = (SimpleSymbol)new SimpleSymbol("cond").readResolve();
    Lit2 = (SimpleSymbol)localObject1;
    localObject2 = Lit75;
    localObject3 = Lit76;
    localObject4 = new SyntaxRule(new SyntaxPattern("\f\030L\f\002\f\007\r\017\b\b\b\b", new Object[] { Lit75 }, 2), "\001\003", "\021\030\004\t\003\b\r\013", new Object[] { Lit56 }, 1);
    localObject5 = new SyntaxRule(new SyntaxPattern("\f\030L\f\002\f\007\r\017\b\b\b\r\027\020\b\b", new Object[] { Lit75 }, 3), "\001\003\003", "\030\004", new Object[] { PairWithPosition.make(Lit69, PairWithPosition.make("else clause must be last clause of cond", LList.Empty, "std_syntax.scm", 86035), "std_syntax.scm", 86020) }, 0);
    localObject6 = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\002\f\017\b\b", new Object[] { Lit76 }, 2), "\001\001", "\021\030\0041\b\021\030\f\b\003\b\021\030\024\021\030\f\b\t\013\030\034", new Object[] { Lit70, Lit77, Lit72, PairWithPosition.make(Lit77, LList.Empty, "std_syntax.scm", 102423) }, 0);
    localObject7 = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\002\f\017\b\f\027\r\037\030\b\b", new Object[] { Lit76 }, 4), "\001\001\001\003", "\021\030\0041\b\021\030\f\b\003\b\021\030\024\021\030\f!\t\013\030\034\b\021\030$\t\023\b\035\033", new Object[] { Lit70, Lit77, Lit72, PairWithPosition.make(Lit77, LList.Empty, "std_syntax.scm", 122898), Lit2 }, 1);
    localObject8 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\b\b", new Object[0], 1), "\001", "\003", new Object[0], 0);
    localObject9 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\b\f\017\r\027\020\b\b", new Object[0], 3), "\001\001\003", "\021\030\004\t\003\b\021\030\f\t\013\b\025\023", new Object[] { Lit16, Lit2 }, 1);
    localObject10 = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\017\r\027\020\b\b\b", new Object[0], 3), "\001\001\003", "\021\030\004\t\003\b\021\030\f\t\013\b\025\023", new Object[] { Lit72, Lit56 }, 1);
    localObject11 = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\017\r\027\020\b\b\f\037\r' \b\b", new Object[0], 5), "\001\001\003\001\003", "\021\030\004\t\003A\021\030\f\t\013\b\025\023\b\021\030\024\t\033\b%#", new Object[] { Lit72, Lit56, Lit2 }, 1);
    Lit3 = new SyntaxRules(new Object[] { localObject1, localObject2, localObject3 }, new SyntaxRule[] { localObject4, localObject5, localObject6, localObject7, localObject8, localObject9, localObject10, localObject11 }, 5);
    Lit1 = IntNum.make(1);
    Lit0 = IntNum.make(0);
    $instance = new std_syntax();
    $Prvt$define = StaticFieldLocation.make("kawa.lib.prim_syntax", "define");
    $Prvt$define$Mnconstant = StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnconstant");
    $Prvt$if = StaticFieldLocation.make("kawa.lib.prim_syntax", "if");
    $Prvt$letrec = StaticFieldLocation.make("kawa.lib.prim_syntax", "letrec");
    cond = Macro.make(Lit2, Lit3, $instance);
    case = Macro.make(Lit4, Lit5, $instance);
    $Prvt$$Pccase = Macro.make(Lit6, Lit7, $instance);
    $Prvt$$Pccase$Mnmatch = Macro.make(Lit8, Lit9, $instance);
    localObject2 = Lit10;
    localObject1 = $instance;
    and = Macro.make(localObject2, new ModuleMethod((ModuleBody)localObject1, 1, null, 4097), $instance);
    or = Macro.make(Lit16, new ModuleMethod((ModuleBody)localObject1, 2, null, 4097), $instance);
    $Prvt$$Pclet$Mnlambda1 = Macro.make(Lit22, Lit23, $instance);
    $Prvt$$Pclet$Mnlambda2 = Macro.make(Lit24, Lit25, $instance);
    $Prvt$$Pclet$Mninit = Macro.make(Lit26, Lit27, $instance);
    let = Macro.make(Lit28, Lit29, $instance);
    let$St = Macro.make(Lit30, Lit31, $instance);
    $Prvt$$Pcdo$Mnstep = Macro.make(Lit32, Lit33, $instance);
    $Prvt$$Pcdo$Mninit = Macro.make(Lit34, Lit35, $instance);
    $Prvt$$Pcdo$Mnlambda1 = Macro.make(Lit36, Lit37, $instance);
    $Prvt$$Pcdo$Mnlambda2 = Macro.make(Lit38, Lit39, $instance);
    do = Macro.make(Lit40, Lit41, $instance);
    delay = Macro.make(Lit42, Lit43, $instance);
    define$Mnprocedure = Macro.make(Lit44, Lit45, $instance);
    syntax$Mnobject$Mn$Grdatum = new ModuleMethod((ModuleBody)localObject1, 3, Lit46, 4097);
    datum$Mn$Grsyntax$Mnobject = new ModuleMethod((ModuleBody)localObject1, 4, Lit47, 8194);
    generate$Mntemporaries = new ModuleMethod((ModuleBody)localObject1, 5, Lit48, 4097);
    identifier$Qu = new ModuleMethod((ModuleBody)localObject1, 6, Lit49, 4097);
    free$Mnidentifier$Eq$Qu = new ModuleMethod((ModuleBody)localObject1, 7, Lit50, 8194);
    syntax$Mnsource = new ModuleMethod((ModuleBody)localObject1, 8, Lit51, 4097);
    syntax$Mnline = new ModuleMethod((ModuleBody)localObject1, 9, Lit52, 4097);
    syntax$Mncolumn = new ModuleMethod((ModuleBody)localObject1, 10, Lit53, 4097);
    localObject2 = Lit54;
    localObject1 = new ModuleMethod((ModuleBody)localObject1, 11, null, 4097);
    ((PropertySet)localObject1).setProperty("source-location", "std_syntax.scm:298");
    begin$Mnfor$Mnsyntax = Macro.make(localObject2, (Procedure)localObject1, $instance);
    define$Mnfor$Mnsyntax = Macro.make(Lit59, Lit60, $instance);
    with$Mnsyntax = Macro.make(Lit61, Lit62, $instance);
    $instance.run();
  }
  
  public std_syntax()
  {
    ModuleInfo.register(this);
  }
  
  public static Object datum$To$SyntaxObject(Object paramObject1, Object paramObject2)
  {
    return SyntaxForms.makeWithTemplate(paramObject1, paramObject2);
  }
  
  public static Object generateTemporaries(Object paramObject)
  {
    Object localObject2 = Integer.valueOf(Translator.listLength(paramObject));
    for (Object localObject1 = LList.Empty;; localObject1 = new Pair(datum$To$SyntaxObject(paramObject, Symbols.gentemp()), localObject1))
    {
      if (Scheme.numEqu.apply2(localObject2, Lit0) != Boolean.FALSE) {
        return localObject1;
      }
      localObject2 = AddOp.$Mn.apply2(localObject2, Lit1);
    }
  }
  
  /* Error */
  public static boolean isFreeIdentifier$Eq(Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_0
    //   1: checkcast 860	kawa/lang/SyntaxForm
    //   4: astore_2
    //   5: aload_1
    //   6: checkcast 860	kawa/lang/SyntaxForm
    //   9: astore_0
    //   10: aload_2
    //   11: aload_0
    //   12: invokestatic 864	kawa/lang/SyntaxForms:freeIdentifierEquals	(Lkawa/lang/SyntaxForm;Lkawa/lang/SyntaxForm;)Z
    //   15: ireturn
    //   16: astore_1
    //   17: new 866	gnu/mapping/WrongType
    //   20: dup
    //   21: aload_1
    //   22: ldc_w 868
    //   25: iconst_1
    //   26: aload_0
    //   27: invokespecial 871	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   30: athrow
    //   31: astore_0
    //   32: new 866	gnu/mapping/WrongType
    //   35: dup
    //   36: aload_0
    //   37: ldc_w 868
    //   40: iconst_2
    //   41: aload_1
    //   42: invokespecial 871	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   45: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	46	0	paramObject1	Object
    //   0	46	1	paramObject2	Object
    //   4	7	2	localSyntaxForm	SyntaxForm
    // Exception table:
    //   from	to	target	type
    //   0	5	16	java/lang/ClassCastException
    //   5	10	31	java/lang/ClassCastException
  }
  
  public static boolean isIdentifier(Object paramObject)
  {
    boolean bool1 = paramObject instanceof Symbol;
    if (bool1) {}
    boolean bool2;
    do
    {
      return bool1;
      bool2 = paramObject instanceof SyntaxForm;
      bool1 = bool2;
    } while (!bool2);
    try
    {
      SyntaxForm localSyntaxForm = (SyntaxForm)paramObject;
      return SyntaxForms.isIdentifier(localSyntaxForm);
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "kawa.lang.SyntaxForms.isIdentifier(kawa.lang.SyntaxForm)", 1, paramObject);
    }
  }
  
  static Object lambda1(Object paramObject)
  {
    Object[] arrayOfObject = SyntaxPattern.allocVars(3, null);
    if (Lit11.match(paramObject, arrayOfObject, 0)) {
      return new QuoteExp(Language.getDefaultLanguage().booleanObject(true));
    }
    if (Lit12.match(paramObject, arrayOfObject, 0))
    {
      paramObject = TemplateScope.make();
      return Lit13.execute(arrayOfObject, (TemplateScope)paramObject);
    }
    if (Lit14.match(paramObject, arrayOfObject, 0))
    {
      paramObject = TemplateScope.make();
      return Lit15.execute(arrayOfObject, (TemplateScope)paramObject);
    }
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda2(Object paramObject)
  {
    Object[] arrayOfObject = SyntaxPattern.allocVars(3, null);
    if (Lit17.match(paramObject, arrayOfObject, 0)) {
      return new QuoteExp(Language.getDefaultLanguage().booleanObject(false));
    }
    if (Lit18.match(paramObject, arrayOfObject, 0))
    {
      paramObject = TemplateScope.make();
      return Lit19.execute(arrayOfObject, (TemplateScope)paramObject);
    }
    if (Lit20.match(paramObject, arrayOfObject, 0))
    {
      paramObject = TemplateScope.make();
      return Lit21.execute(arrayOfObject, (TemplateScope)paramObject);
    }
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda3(Object paramObject)
  {
    Object[] arrayOfObject = SyntaxPattern.allocVars(2, null);
    if (Lit55.match(paramObject, arrayOfObject, 0))
    {
      Eval localEval = Eval.eval;
      SimpleSymbol localSimpleSymbol = Lit56;
      TemplateScope localTemplateScope = TemplateScope.make();
      if (localEval.apply1(syntaxObject$To$Datum(new Pair(localSimpleSymbol, Lit57.execute(arrayOfObject, localTemplateScope)))) != Boolean.FALSE)
      {
        paramObject = TemplateScope.make();
        return Lit58.execute(arrayOfObject, (TemplateScope)paramObject);
      }
    }
    return syntax_case.error("syntax-case", paramObject);
  }
  
  public static Object syntaxColumn(Object paramObject)
  {
    if ((paramObject instanceof SyntaxForm)) {
      return syntaxLine(((SyntaxForm)paramObject).getDatum());
    }
    if ((paramObject instanceof PairWithPosition)) {
      return Integer.valueOf(((PairWithPosition)paramObject).getColumnNumber() + 0);
    }
    return Boolean.FALSE;
  }
  
  public static Object syntaxLine(Object paramObject)
  {
    if ((paramObject instanceof SyntaxForm)) {
      return syntaxLine(((SyntaxForm)paramObject).getDatum());
    }
    if ((paramObject instanceof PairWithPosition)) {
      return Integer.valueOf(((PairWithPosition)paramObject).getLineNumber());
    }
    return Boolean.FALSE;
  }
  
  public static Object syntaxObject$To$Datum(Object paramObject)
  {
    return Quote.quote(paramObject);
  }
  
  public static Object syntaxSource(Object paramObject)
  {
    if ((paramObject instanceof SyntaxForm)) {
      paramObject = syntaxSource(((SyntaxForm)paramObject).getDatum());
    }
    String str;
    do
    {
      return paramObject;
      if (!(paramObject instanceof PairWithPosition)) {
        break;
      }
      str = ((PairWithPosition)paramObject).getFileName();
      paramObject = str;
    } while (str != null);
    return Boolean.FALSE;
    return Boolean.FALSE;
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 4: 
    case 7: 
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 3: 
      return syntaxObject$To$Datum(paramObject);
    case 5: 
      return generateTemporaries(paramObject);
    case 6: 
      if (isIdentifier(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 8: 
      return syntaxSource(paramObject);
    case 9: 
      return syntaxLine(paramObject);
    case 10: 
      return syntaxColumn(paramObject);
    case 1: 
      return lambda1(paramObject);
    case 2: 
      return lambda2(paramObject);
    }
    return lambda3(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    switch (paramModuleMethod.selector)
    {
    case 5: 
    case 6: 
    default: 
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    case 4: 
      return datum$To$SyntaxObject(paramObject1, paramObject2);
    }
    if (isFreeIdentifier$Eq(paramObject1, paramObject2)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 4: 
    case 7: 
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 11: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 2: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 1: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 10: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 9: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 8: 
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
    case 5: 
    case 6: 
    default: 
      return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    case 7: 
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
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\std_syntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */