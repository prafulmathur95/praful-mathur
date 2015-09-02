package gnu.kawa.slib;

import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Map;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.Numeric;
import kawa.lang.Continuation;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.standard.Scheme;
import kawa.standard.append;
import kawa.standard.call_with_values;

public class srfi1
  extends ModuleBody
{
  public static final Macro $Pcevery;
  public static final int $Pcprovide$Pclist$Mnlib = 123;
  public static final int $Pcprovide$Pcsrfi$Mn1 = 123;
  public static final srfi1 $instance;
  static final IntNum Lit0;
  static final IntNum Lit1;
  static final SimpleSymbol Lit10;
  static final SimpleSymbol Lit100;
  static final SimpleSymbol Lit101;
  static final SimpleSymbol Lit102;
  static final SimpleSymbol Lit103;
  static final SimpleSymbol Lit104 = (SimpleSymbol)new SimpleSymbol("cdr").readResolve();
  static final SimpleSymbol Lit11;
  static final SimpleSymbol Lit12;
  static final SimpleSymbol Lit13;
  static final SimpleSymbol Lit14;
  static final SimpleSymbol Lit15;
  static final SimpleSymbol Lit16;
  static final SimpleSymbol Lit17;
  static final SimpleSymbol Lit18;
  static final SimpleSymbol Lit19;
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
  static final SimpleSymbol Lit71;
  static final SimpleSymbol Lit72;
  static final SimpleSymbol Lit73;
  static final SimpleSymbol Lit74;
  static final SimpleSymbol Lit75;
  static final SimpleSymbol Lit76;
  static final SimpleSymbol Lit77;
  static final SimpleSymbol Lit78;
  static final SimpleSymbol Lit79;
  static final SimpleSymbol Lit8;
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
  static final SimpleSymbol Lit90;
  static final SimpleSymbol Lit91;
  static final SimpleSymbol Lit92;
  static final SimpleSymbol Lit93;
  static final SimpleSymbol Lit94;
  static final SimpleSymbol Lit95;
  static final SimpleSymbol Lit96;
  static final SimpleSymbol Lit97;
  static final SimpleSymbol Lit98;
  static final SimpleSymbol Lit99;
  public static final ModuleMethod alist$Mncons;
  public static final ModuleMethod alist$Mncopy;
  public static final ModuleMethod alist$Mndelete;
  public static final ModuleMethod alist$Mndelete$Ex;
  public static final ModuleMethod any;
  public static final ModuleMethod append$Ex;
  public static final ModuleMethod append$Mnmap;
  public static final ModuleMethod append$Mnmap$Ex;
  public static final ModuleMethod append$Mnreverse;
  public static final ModuleMethod append$Mnreverse$Ex;
  public static final ModuleMethod jdField_break;
  public static final ModuleMethod break$Ex;
  public static final ModuleMethod car$Plcdr;
  public static final ModuleMethod circular$Mnlist;
  public static final ModuleMethod circular$Mnlist$Qu;
  public static final ModuleMethod concatenate;
  public static final ModuleMethod concatenate$Ex;
  public static final ModuleMethod cons$St;
  public static final ModuleMethod count;
  public static final ModuleMethod delete;
  public static final ModuleMethod delete$Ex;
  public static final ModuleMethod delete$Mnduplicates;
  public static final ModuleMethod delete$Mnduplicates$Ex;
  public static final ModuleMethod dotted$Mnlist$Qu;
  public static final ModuleMethod drop;
  public static final ModuleMethod drop$Mnright;
  public static final ModuleMethod drop$Mnright$Ex;
  public static final ModuleMethod drop$Mnwhile;
  public static final ModuleMethod eighth;
  public static final ModuleMethod every;
  public static final ModuleMethod fifth;
  public static final ModuleMethod filter;
  public static final ModuleMethod filter$Ex;
  public static final ModuleMethod filter$Mnmap;
  public static final ModuleMethod find;
  public static final ModuleMethod find$Mntail;
  public static GenericProc first;
  public static final ModuleMethod fold;
  public static final ModuleMethod fold$Mnright;
  public static GenericProc fourth;
  public static final ModuleMethod iota;
  static final ModuleMethod lambda$Fn64;
  static final ModuleMethod lambda$Fn78;
  public static final ModuleMethod last;
  public static final ModuleMethod last$Mnpair;
  public static final ModuleMethod length$Pl;
  public static final ModuleMethod list$Eq;
  public static final ModuleMethod list$Mncopy;
  public static final ModuleMethod list$Mnindex;
  public static final ModuleMethod list$Mntabulate;
  public static final ModuleMethod lset$Eq;
  public static final ModuleMethod lset$Ls$Eq;
  public static final ModuleMethod lset$Mnadjoin;
  public static final ModuleMethod lset$Mndiff$Plintersection;
  public static final ModuleMethod lset$Mndiff$Plintersection$Ex;
  public static final ModuleMethod lset$Mndifference;
  public static final ModuleMethod lset$Mndifference$Ex;
  public static final ModuleMethod lset$Mnintersection;
  public static final ModuleMethod lset$Mnintersection$Ex;
  public static final ModuleMethod lset$Mnunion;
  public static final ModuleMethod lset$Mnunion$Ex;
  public static final ModuleMethod lset$Mnxor;
  public static final ModuleMethod lset$Mnxor$Ex;
  public static final ModuleMethod make$Mnlist;
  public static final ModuleMethod map$Ex;
  public static Map map$Mnin$Mnorder;
  public static final ModuleMethod ninth;
  public static final ModuleMethod not$Mnpair$Qu;
  public static final ModuleMethod null$Mnlist$Qu;
  public static final ModuleMethod pair$Mnfold;
  public static final ModuleMethod pair$Mnfold$Mnright;
  public static final ModuleMethod pair$Mnfor$Mneach;
  public static final ModuleMethod partition;
  public static final ModuleMethod partition$Ex;
  public static final ModuleMethod proper$Mnlist$Qu;
  public static final ModuleMethod reduce;
  public static final ModuleMethod reduce$Mnright;
  public static final ModuleMethod remove;
  public static final ModuleMethod remove$Ex;
  public static GenericProc second;
  public static final ModuleMethod seventh;
  public static final ModuleMethod sixth;
  public static final ModuleMethod span;
  public static final ModuleMethod span$Ex;
  public static final ModuleMethod split$Mnat;
  public static final ModuleMethod split$Mnat$Ex;
  public static final ModuleMethod take;
  public static final ModuleMethod take$Ex;
  public static final ModuleMethod take$Mnright;
  public static final ModuleMethod take$Mnwhile;
  public static final ModuleMethod take$Mnwhile$Ex;
  public static final ModuleMethod tenth;
  public static GenericProc third;
  public static final ModuleMethod unfold;
  public static final ModuleMethod unfold$Mnright;
  public static final ModuleMethod unzip1;
  public static final ModuleMethod unzip2;
  public static final ModuleMethod unzip3;
  public static final ModuleMethod unzip4;
  public static final ModuleMethod unzip5;
  public static final ModuleMethod xcons;
  public static final ModuleMethod zip;
  
  static Object $PcCars$Pl(Object paramObject1, Object paramObject2)
  {
    frame56 localframe56 = new frame56();
    localframe56.last$Mnelt = paramObject2;
    return localframe56.lambda75recur(paramObject1);
  }
  
  static Object $PcCars$PlCdrs(Object paramObject)
  {
    Continuation localContinuation = new Continuation(CallContext.getInstance());
    try
    {
      frame57 localframe57 = new frame57();
      localframe57.abort = localContinuation;
      paramObject = localframe57.lambda76recur(paramObject);
      localContinuation.invoked = true;
      return paramObject;
    }
    finally {}
    return Continuation.handleException((Throwable)paramObject, localContinuation);
  }
  
  static Object $PcCars$PlCdrs$Pl(Object paramObject1, Object paramObject2)
  {
    frame62 localframe62 = new frame62();
    localframe62.cars$Mnfinal = paramObject2;
    paramObject2 = new Continuation(CallContext.getInstance());
    try
    {
      frame63 localframe63 = new frame63();
      localframe63.staticLink = localframe62;
      localframe63.abort = ((Continuation)paramObject2);
      paramObject1 = localframe63.lambda85recur(paramObject1);
      ((Continuation)paramObject2).invoked = true;
      return paramObject1;
    }
    finally {}
    return Continuation.handleException((Throwable)paramObject1, (Continuation)paramObject2);
  }
  
  static Object $PcCars$PlCdrs$SlNoTest(Object paramObject)
  {
    new frame67();
    return frame67.lambda92recur(paramObject);
  }
  
  static Object $PcCars$PlCdrs$SlNoTest$SlPair(Object paramObject)
  {
    frame71 localframe71 = new frame71();
    localframe71.lists = paramObject;
    return call_with_values.callWithValues(localframe71.lambda$Fn77, lambda$Fn78);
  }
  
  static Object $PcCars$PlCdrs$SlPair(Object paramObject)
  {
    frame61 localframe61 = new frame61();
    localframe61.lists = paramObject;
    return call_with_values.callWithValues(localframe61.lambda$Fn63, lambda$Fn64);
  }
  
  static Object $PcCdrs(Object paramObject)
  {
    Continuation localContinuation = new Continuation(CallContext.getInstance());
    try
    {
      frame55 localframe55 = new frame55();
      localframe55.abort = localContinuation;
      paramObject = localframe55.lambda74recur(paramObject);
      localContinuation.invoked = true;
      return paramObject;
    }
    finally {}
    return Continuation.handleException((Throwable)paramObject, localContinuation);
  }
  
  static Object $PcLset2$Ls$Eq(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    frame72 localframe72 = new frame72();
    localframe72.$Eq = paramObject1;
    localframe72.lis2 = paramObject3;
    return every$V(localframe72.lambda$Fn79, paramObject2, new Object[0]);
  }
  
  static
  {
    Lit103 = (SimpleSymbol)new SimpleSymbol("car").readResolve();
    Lit102 = (SimpleSymbol)new SimpleSymbol("lp").readResolve();
    Lit101 = (SimpleSymbol)new SimpleSymbol("head").readResolve();
    Lit100 = (SimpleSymbol)new SimpleSymbol("tail").readResolve();
    Lit99 = (SimpleSymbol)new SimpleSymbol("lset-diff+intersection!").readResolve();
    Lit98 = (SimpleSymbol)new SimpleSymbol("lset-diff+intersection").readResolve();
    Lit97 = (SimpleSymbol)new SimpleSymbol("lset-xor!").readResolve();
    Lit96 = (SimpleSymbol)new SimpleSymbol("lset-xor").readResolve();
    Lit95 = (SimpleSymbol)new SimpleSymbol("lset-difference!").readResolve();
    Lit94 = (SimpleSymbol)new SimpleSymbol("lset-difference").readResolve();
    Lit93 = (SimpleSymbol)new SimpleSymbol("lset-intersection!").readResolve();
    Lit92 = (SimpleSymbol)new SimpleSymbol("lset-intersection").readResolve();
    Lit91 = (SimpleSymbol)new SimpleSymbol("lset-union!").readResolve();
    Lit90 = (SimpleSymbol)new SimpleSymbol("lset-union").readResolve();
    Lit89 = (SimpleSymbol)new SimpleSymbol("lset-adjoin").readResolve();
    Lit88 = (SimpleSymbol)new SimpleSymbol("lset=").readResolve();
    Lit87 = (SimpleSymbol)new SimpleSymbol("lset<=").readResolve();
    Lit86 = (SimpleSymbol)new SimpleSymbol("list-index").readResolve();
    Object localObject1 = (SimpleSymbol)new SimpleSymbol("%every").readResolve();
    Lit84 = (SimpleSymbol)localObject1;
    Object localObject2 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
    SimpleSymbol localSimpleSymbol1 = (SimpleSymbol)new SimpleSymbol("let").readResolve();
    SimpleSymbol localSimpleSymbol2 = Lit102;
    SimpleSymbol localSimpleSymbol3 = Lit101;
    SimpleSymbol localSimpleSymbol4 = Lit103;
    SimpleSymbol localSimpleSymbol5 = Lit100;
    SimpleSymbol localSimpleSymbol6 = Lit104;
    SimpleSymbol localSimpleSymbol7 = (SimpleSymbol)new SimpleSymbol("and").readResolve();
    SimpleSymbol localSimpleSymbol8 = (SimpleSymbol)new SimpleSymbol("null-list?").readResolve();
    Lit14 = localSimpleSymbol8;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\001\001", "\021\030\004\021\030\f¡I\021\030\024\b\021\030\034\b\013\b\021\030$\b\021\030,\b\013\b\021\0304\021\030<!\t\003\030D\030L", new Object[] { localSimpleSymbol1, localSimpleSymbol2, localSimpleSymbol3, localSimpleSymbol4, localSimpleSymbol5, localSimpleSymbol6, localSimpleSymbol7, PairWithPosition.make(localSimpleSymbol8, PairWithPosition.make(Lit100, LList.Empty, "srfi1.scm", 5722136), "srfi1.scm", 5722124), PairWithPosition.make(Lit101, LList.Empty, "srfi1.scm", 5722148), PairWithPosition.make(PairWithPosition.make(Lit102, PairWithPosition.make(PairWithPosition.make(Lit103, PairWithPosition.make(Lit100, LList.Empty, "srfi1.scm", 5722163), "srfi1.scm", 5722158), PairWithPosition.make(PairWithPosition.make(Lit104, PairWithPosition.make(Lit100, LList.Empty, "srfi1.scm", 5722174), "srfi1.scm", 5722169), LList.Empty, "srfi1.scm", 5722169), "srfi1.scm", 5722158), "srfi1.scm", 5722154), LList.Empty, "srfi1.scm", 5722154) }, 0);
    Lit85 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 2);
    Lit83 = (SimpleSymbol)new SimpleSymbol("every").readResolve();
    Lit82 = (SimpleSymbol)new SimpleSymbol("any").readResolve();
    Lit81 = (SimpleSymbol)new SimpleSymbol("break!").readResolve();
    Lit80 = (SimpleSymbol)new SimpleSymbol("break").readResolve();
    Lit79 = (SimpleSymbol)new SimpleSymbol("span!").readResolve();
    Lit78 = (SimpleSymbol)new SimpleSymbol("span").readResolve();
    Lit77 = (SimpleSymbol)new SimpleSymbol("take-while!").readResolve();
    Lit76 = (SimpleSymbol)new SimpleSymbol("drop-while").readResolve();
    Lit75 = (SimpleSymbol)new SimpleSymbol("take-while").readResolve();
    Lit74 = (SimpleSymbol)new SimpleSymbol("find-tail").readResolve();
    Lit73 = (SimpleSymbol)new SimpleSymbol("find").readResolve();
    Lit72 = (SimpleSymbol)new SimpleSymbol("alist-delete!").readResolve();
    Lit71 = (SimpleSymbol)new SimpleSymbol("alist-delete").readResolve();
    Lit70 = (SimpleSymbol)new SimpleSymbol("alist-copy").readResolve();
    Lit69 = (SimpleSymbol)new SimpleSymbol("alist-cons").readResolve();
    Lit68 = (SimpleSymbol)new SimpleSymbol("delete-duplicates!").readResolve();
    Lit67 = (SimpleSymbol)new SimpleSymbol("delete-duplicates").readResolve();
    Lit66 = (SimpleSymbol)new SimpleSymbol("delete!").readResolve();
    Lit65 = (SimpleSymbol)new SimpleSymbol("delete").readResolve();
    Lit64 = (SimpleSymbol)new SimpleSymbol("remove!").readResolve();
    Lit63 = (SimpleSymbol)new SimpleSymbol("remove").readResolve();
    Lit62 = (SimpleSymbol)new SimpleSymbol("partition!").readResolve();
    Lit61 = (SimpleSymbol)new SimpleSymbol("partition").readResolve();
    Lit60 = (SimpleSymbol)new SimpleSymbol("filter!").readResolve();
    Lit59 = (SimpleSymbol)new SimpleSymbol("filter").readResolve();
    Lit58 = (SimpleSymbol)new SimpleSymbol("filter-map").readResolve();
    Lit57 = (SimpleSymbol)new SimpleSymbol("map!").readResolve();
    Lit56 = (SimpleSymbol)new SimpleSymbol("pair-for-each").readResolve();
    Lit55 = (SimpleSymbol)new SimpleSymbol("append-map!").readResolve();
    Lit54 = (SimpleSymbol)new SimpleSymbol("append-map").readResolve();
    Lit53 = (SimpleSymbol)new SimpleSymbol("reduce-right").readResolve();
    Lit52 = (SimpleSymbol)new SimpleSymbol("reduce").readResolve();
    Lit51 = (SimpleSymbol)new SimpleSymbol("pair-fold").readResolve();
    Lit50 = (SimpleSymbol)new SimpleSymbol("pair-fold-right").readResolve();
    Lit49 = (SimpleSymbol)new SimpleSymbol("fold-right").readResolve();
    Lit48 = (SimpleSymbol)new SimpleSymbol("fold").readResolve();
    Lit47 = (SimpleSymbol)new SimpleSymbol("unfold").readResolve();
    Lit46 = (SimpleSymbol)new SimpleSymbol("unfold-right").readResolve();
    Lit45 = (SimpleSymbol)new SimpleSymbol("count").readResolve();
    Lit44 = (SimpleSymbol)new SimpleSymbol("concatenate!").readResolve();
    Lit43 = (SimpleSymbol)new SimpleSymbol("concatenate").readResolve();
    Lit42 = (SimpleSymbol)new SimpleSymbol("append-reverse!").readResolve();
    Lit41 = (SimpleSymbol)new SimpleSymbol("append-reverse").readResolve();
    Lit40 = (SimpleSymbol)new SimpleSymbol("append!").readResolve();
    Lit39 = (SimpleSymbol)new SimpleSymbol("unzip5").readResolve();
    Lit38 = (SimpleSymbol)new SimpleSymbol("unzip4").readResolve();
    Lit37 = (SimpleSymbol)new SimpleSymbol("unzip3").readResolve();
    Lit36 = (SimpleSymbol)new SimpleSymbol("unzip2").readResolve();
    Lit35 = (SimpleSymbol)new SimpleSymbol("unzip1").readResolve();
    Lit34 = (SimpleSymbol)new SimpleSymbol("last-pair").readResolve();
    Lit33 = (SimpleSymbol)new SimpleSymbol("last").readResolve();
    Lit32 = (SimpleSymbol)new SimpleSymbol("split-at!").readResolve();
    Lit31 = (SimpleSymbol)new SimpleSymbol("split-at").readResolve();
    Lit30 = (SimpleSymbol)new SimpleSymbol("drop-right!").readResolve();
    Lit29 = (SimpleSymbol)new SimpleSymbol("drop-right").readResolve();
    Lit28 = (SimpleSymbol)new SimpleSymbol("take-right").readResolve();
    Lit27 = (SimpleSymbol)new SimpleSymbol("take!").readResolve();
    Lit26 = (SimpleSymbol)new SimpleSymbol("drop").readResolve();
    Lit25 = (SimpleSymbol)new SimpleSymbol("take").readResolve();
    Lit24 = (SimpleSymbol)new SimpleSymbol("car+cdr").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("tenth").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("ninth").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("eighth").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("seventh").readResolve();
    Lit19 = (SimpleSymbol)new SimpleSymbol("sixth").readResolve();
    Lit18 = (SimpleSymbol)new SimpleSymbol("fifth").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("zip").readResolve();
    Lit16 = (SimpleSymbol)new SimpleSymbol("length+").readResolve();
    Lit15 = (SimpleSymbol)new SimpleSymbol("list=").readResolve();
    Lit13 = (SimpleSymbol)new SimpleSymbol("not-pair?").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("circular-list?").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("dotted-list?").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("proper-list?").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("circular-list").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("iota").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("list-copy").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("cons*").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("list-tabulate").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("make-list").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("xcons").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("tmp").readResolve();
    Lit1 = IntNum.make(1);
    Lit0 = IntNum.make(0);
    $instance = new srfi1();
    $Pcprovide$Pcsrfi$Mn1 = 123;
    $Pcprovide$Pclist$Mnlib = 123;
    localObject1 = $instance;
    xcons = new ModuleMethod((ModuleBody)localObject1, 78, Lit3, 8194);
    make$Mnlist = new ModuleMethod((ModuleBody)localObject1, 79, Lit4, 61441);
    list$Mntabulate = new ModuleMethod((ModuleBody)localObject1, 80, Lit5, 8194);
    cons$St = new ModuleMethod((ModuleBody)localObject1, 81, Lit6, 61440);
    list$Mncopy = new ModuleMethod((ModuleBody)localObject1, 82, Lit7, 4097);
    iota = new ModuleMethod((ModuleBody)localObject1, 83, Lit8, 12289);
    circular$Mnlist = new ModuleMethod((ModuleBody)localObject1, 86, Lit9, 61441);
    proper$Mnlist$Qu = new ModuleMethod((ModuleBody)localObject1, 87, Lit10, 4097);
    dotted$Mnlist$Qu = new ModuleMethod((ModuleBody)localObject1, 88, Lit11, 4097);
    circular$Mnlist$Qu = new ModuleMethod((ModuleBody)localObject1, 89, Lit12, 4097);
    not$Mnpair$Qu = new ModuleMethod((ModuleBody)localObject1, 90, Lit13, 4097);
    null$Mnlist$Qu = new ModuleMethod((ModuleBody)localObject1, 91, Lit14, 4097);
    list$Eq = new ModuleMethod((ModuleBody)localObject1, 92, Lit15, 61441);
    length$Pl = new ModuleMethod((ModuleBody)localObject1, 93, Lit16, 4097);
    zip = new ModuleMethod((ModuleBody)localObject1, 94, Lit17, 61441);
    fifth = new ModuleMethod((ModuleBody)localObject1, 95, Lit18, 4097);
    sixth = new ModuleMethod((ModuleBody)localObject1, 96, Lit19, 4097);
    seventh = new ModuleMethod((ModuleBody)localObject1, 97, Lit20, 4097);
    eighth = new ModuleMethod((ModuleBody)localObject1, 98, Lit21, 4097);
    ninth = new ModuleMethod((ModuleBody)localObject1, 99, Lit22, 4097);
    tenth = new ModuleMethod((ModuleBody)localObject1, 100, Lit23, 4097);
    car$Plcdr = new ModuleMethod((ModuleBody)localObject1, 101, Lit24, 4097);
    take = new ModuleMethod((ModuleBody)localObject1, 102, Lit25, 8194);
    drop = new ModuleMethod((ModuleBody)localObject1, 103, Lit26, 8194);
    take$Ex = new ModuleMethod((ModuleBody)localObject1, 104, Lit27, 8194);
    take$Mnright = new ModuleMethod((ModuleBody)localObject1, 105, Lit28, 8194);
    drop$Mnright = new ModuleMethod((ModuleBody)localObject1, 106, Lit29, 8194);
    drop$Mnright$Ex = new ModuleMethod((ModuleBody)localObject1, 107, Lit30, 8194);
    split$Mnat = new ModuleMethod((ModuleBody)localObject1, 108, Lit31, 8194);
    split$Mnat$Ex = new ModuleMethod((ModuleBody)localObject1, 109, Lit32, 8194);
    last = new ModuleMethod((ModuleBody)localObject1, 110, Lit33, 4097);
    last$Mnpair = new ModuleMethod((ModuleBody)localObject1, 111, Lit34, 4097);
    unzip1 = new ModuleMethod((ModuleBody)localObject1, 112, Lit35, 4097);
    unzip2 = new ModuleMethod((ModuleBody)localObject1, 113, Lit36, 4097);
    unzip3 = new ModuleMethod((ModuleBody)localObject1, 114, Lit37, 4097);
    unzip4 = new ModuleMethod((ModuleBody)localObject1, 115, Lit38, 4097);
    unzip5 = new ModuleMethod((ModuleBody)localObject1, 116, Lit39, 4097);
    append$Ex = new ModuleMethod((ModuleBody)localObject1, 117, Lit40, 61440);
    append$Mnreverse = new ModuleMethod((ModuleBody)localObject1, 118, Lit41, 8194);
    append$Mnreverse$Ex = new ModuleMethod((ModuleBody)localObject1, 119, Lit42, 8194);
    concatenate = new ModuleMethod((ModuleBody)localObject1, 120, Lit43, 4097);
    concatenate$Ex = new ModuleMethod((ModuleBody)localObject1, 121, Lit44, 4097);
    count = new ModuleMethod((ModuleBody)localObject1, 122, Lit45, 61442);
    unfold$Mnright = new ModuleMethod((ModuleBody)localObject1, 123, Lit46, 20484);
    unfold = new ModuleMethod((ModuleBody)localObject1, 125, Lit47, 61444);
    fold = new ModuleMethod((ModuleBody)localObject1, 126, Lit48, 61443);
    fold$Mnright = new ModuleMethod((ModuleBody)localObject1, 127, Lit49, 61443);
    pair$Mnfold$Mnright = new ModuleMethod((ModuleBody)localObject1, 128, Lit50, 61443);
    pair$Mnfold = new ModuleMethod((ModuleBody)localObject1, 129, Lit51, 61443);
    reduce = new ModuleMethod((ModuleBody)localObject1, 130, Lit52, 12291);
    reduce$Mnright = new ModuleMethod((ModuleBody)localObject1, 131, Lit53, 12291);
    append$Mnmap = new ModuleMethod((ModuleBody)localObject1, 132, Lit54, 61442);
    append$Mnmap$Ex = new ModuleMethod((ModuleBody)localObject1, 133, Lit55, 61442);
    pair$Mnfor$Mneach = new ModuleMethod((ModuleBody)localObject1, 134, Lit56, 61442);
    map$Ex = new ModuleMethod((ModuleBody)localObject1, 135, Lit57, 61442);
    filter$Mnmap = new ModuleMethod((ModuleBody)localObject1, 136, Lit58, 61442);
    filter = new ModuleMethod((ModuleBody)localObject1, 137, Lit59, 8194);
    filter$Ex = new ModuleMethod((ModuleBody)localObject1, 138, Lit60, 8194);
    partition = new ModuleMethod((ModuleBody)localObject1, 139, Lit61, 8194);
    partition$Ex = new ModuleMethod((ModuleBody)localObject1, 140, Lit62, 8194);
    remove = new ModuleMethod((ModuleBody)localObject1, 141, Lit63, 8194);
    remove$Ex = new ModuleMethod((ModuleBody)localObject1, 142, Lit64, 8194);
    delete = new ModuleMethod((ModuleBody)localObject1, 143, Lit65, 12290);
    delete$Ex = new ModuleMethod((ModuleBody)localObject1, 145, Lit66, 12290);
    delete$Mnduplicates = new ModuleMethod((ModuleBody)localObject1, 147, Lit67, 8193);
    delete$Mnduplicates$Ex = new ModuleMethod((ModuleBody)localObject1, 149, Lit68, 8193);
    alist$Mncons = new ModuleMethod((ModuleBody)localObject1, 151, Lit69, 12291);
    alist$Mncopy = new ModuleMethod((ModuleBody)localObject1, 152, Lit70, 4097);
    alist$Mndelete = new ModuleMethod((ModuleBody)localObject1, 153, Lit71, 12290);
    alist$Mndelete$Ex = new ModuleMethod((ModuleBody)localObject1, 155, Lit72, 12290);
    find = new ModuleMethod((ModuleBody)localObject1, 157, Lit73, 8194);
    find$Mntail = new ModuleMethod((ModuleBody)localObject1, 158, Lit74, 8194);
    take$Mnwhile = new ModuleMethod((ModuleBody)localObject1, 159, Lit75, 8194);
    drop$Mnwhile = new ModuleMethod((ModuleBody)localObject1, 160, Lit76, 8194);
    take$Mnwhile$Ex = new ModuleMethod((ModuleBody)localObject1, 161, Lit77, 8194);
    span = new ModuleMethod((ModuleBody)localObject1, 162, Lit78, 8194);
    span$Ex = new ModuleMethod((ModuleBody)localObject1, 163, Lit79, 8194);
    break = new ModuleMethod((ModuleBody)localObject1, 164, Lit80, 8194);
    break$Ex = new ModuleMethod((ModuleBody)localObject1, 165, Lit81, 8194);
    any = new ModuleMethod((ModuleBody)localObject1, 166, Lit82, 61442);
    every = new ModuleMethod((ModuleBody)localObject1, 167, Lit83, 61442);
    $Pcevery = Macro.make(Lit84, Lit85, $instance);
    list$Mnindex = new ModuleMethod((ModuleBody)localObject1, 168, Lit86, 61442);
    lset$Ls$Eq = new ModuleMethod((ModuleBody)localObject1, 169, Lit87, 61441);
    lset$Eq = new ModuleMethod((ModuleBody)localObject1, 170, Lit88, 61441);
    lset$Mnadjoin = new ModuleMethod((ModuleBody)localObject1, 171, Lit89, 61442);
    lset$Mnunion = new ModuleMethod((ModuleBody)localObject1, 172, Lit90, 61441);
    lset$Mnunion$Ex = new ModuleMethod((ModuleBody)localObject1, 173, Lit91, 61441);
    lset$Mnintersection = new ModuleMethod((ModuleBody)localObject1, 174, Lit92, 61442);
    lset$Mnintersection$Ex = new ModuleMethod((ModuleBody)localObject1, 175, Lit93, 61442);
    lset$Mndifference = new ModuleMethod((ModuleBody)localObject1, 176, Lit94, 61442);
    lset$Mndifference$Ex = new ModuleMethod((ModuleBody)localObject1, 177, Lit95, 61442);
    lset$Mnxor = new ModuleMethod((ModuleBody)localObject1, 178, Lit96, 61441);
    lset$Mnxor$Ex = new ModuleMethod((ModuleBody)localObject1, 179, Lit97, 61441);
    lset$Mndiff$Plintersection = new ModuleMethod((ModuleBody)localObject1, 180, Lit98, 61442);
    lset$Mndiff$Plintersection$Ex = new ModuleMethod((ModuleBody)localObject1, 181, Lit99, 61442);
    lambda$Fn64 = new ModuleMethod((ModuleBody)localObject1, 182, null, 8194);
    lambda$Fn78 = new ModuleMethod((ModuleBody)localObject1, 183, null, 8194);
    $instance.run();
  }
  
  public srfi1()
  {
    ModuleInfo.register(this);
  }
  
  public static Pair alistCons(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return lists.cons(lists.cons(paramObject1, paramObject2), paramObject3);
  }
  
  public static LList alistCopy(Object paramObject)
  {
    Object localObject2 = LList.Empty;
    Object localObject1 = paramObject;
    paramObject = localObject2;
    for (;;)
    {
      if (localObject1 == LList.Empty) {
        return LList.reverseInPlace(paramObject);
      }
      try
      {
        localObject2 = (Pair)localObject1;
        localObject1 = ((Pair)localObject2).getCdr();
        localObject2 = ((Pair)localObject2).getCar();
        paramObject = Pair.make(lists.cons(lists.car.apply1(localObject2), lists.cdr.apply1(localObject2)), paramObject);
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "arg0", -2, localObject1);
      }
    }
  }
  
  public static Object alistDelete(Object paramObject1, Object paramObject2)
  {
    return alistDelete(paramObject1, paramObject2, Scheme.isEqual);
  }
  
  public static Object alistDelete(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    frame21 localframe21 = new frame21();
    localframe21.key = paramObject1;
    localframe21.maybe$Mn$Eq = paramObject3;
    return filter(localframe21.lambda$Fn18, paramObject2);
  }
  
  public static Object alistDelete$Ex(Object paramObject1, Object paramObject2)
  {
    return alistDelete$Ex(paramObject1, paramObject2, Scheme.isEqual);
  }
  
  public static Object alistDelete$Ex(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    frame22 localframe22 = new frame22();
    localframe22.key = paramObject1;
    localframe22.maybe$Mn$Eq = paramObject3;
    return filter$Ex(localframe22.lambda$Fn19, paramObject2);
  }
  
  public static Object any$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    int i = 0;
    frame26 localframe26 = new frame26();
    localframe26.pred = paramProcedure;
    localframe26.lis1 = paramObject;
    localframe26.lists = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(localframe26.lists)) {
      paramObject = call_with_values.callWithValues(localframe26.lambda$Fn22, localframe26.lambda$Fn23);
    }
    for (;;)
    {
      return paramObject;
      paramProcedure = isNullList(localframe26.lis1);
      try
      {
        paramObject = Boolean.FALSE;
        if (paramProcedure != paramObject) {
          i = 1;
        }
        i = i + 1 & 0x1;
        if (i != 0)
        {
          paramObject = lists.car.apply1(localframe26.lis1);
          for (paramProcedure = lists.cdr.apply1(localframe26.lis1);; paramProcedure = lists.cdr.apply1(paramProcedure))
          {
            if (isNullList(paramProcedure) != Boolean.FALSE) {
              return localframe26.pred.apply1(paramObject);
            }
            paramArrayOfObject = localframe26.pred.apply1(paramObject);
            paramObject = paramArrayOfObject;
            if (paramArrayOfObject != Boolean.FALSE) {
              break;
            }
            paramObject = lists.car.apply1(paramProcedure);
          }
        }
        if (i != 0) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "x", -2, paramProcedure);
      }
    }
  }
  
  public static Object append$Ex$V(Object[] paramArrayOfObject)
  {
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    localObject2 = LList.Empty;
    Object localObject1;
    for (;;)
    {
      if (!lists.isPair(paramArrayOfObject)) {
        return localObject2;
      }
      localObject2 = lists.car.apply1(paramArrayOfObject);
      localObject1 = lists.cdr.apply1(paramArrayOfObject);
      if (lists.isPair(localObject2)) {
        break;
      }
      paramArrayOfObject = (Object[])localObject1;
    }
    for (;;)
    {
      Object localObject3;
      try
      {
        paramArrayOfObject = (Pair)localObject2;
        paramArrayOfObject = lastPair(paramArrayOfObject);
        if (lists.isPair(localObject1))
        {
          localObject3 = lists.car.apply1(localObject1);
          localObject1 = lists.cdr.apply1(localObject1);
        }
      }
      catch (ClassCastException paramArrayOfObject)
      {
        Pair localPair;
        throw new WrongType(paramArrayOfObject, "last-pair", 0, localObject2);
      }
      try
      {
        localPair = (Pair)paramArrayOfObject;
        lists.setCdr$Ex(localPair, localObject3);
        if (!lists.isPair(localObject3)) {}
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "set-cdr!", 1, paramArrayOfObject);
      }
      try
      {
        paramArrayOfObject = (Pair)localObject3;
        paramArrayOfObject = lastPair(paramArrayOfObject);
      }
      catch (ClassCastException paramArrayOfObject)
      {
        throw new WrongType(paramArrayOfObject, "last-pair", 0, localObject3);
      }
    }
    return localObject2;
  }
  
  public static Object appendMap$Ex$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
  {
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(paramArrayOfObject)) {
      return Scheme.apply.apply2(append$Ex, Scheme.apply.apply4(Scheme.map, paramObject1, paramObject2, paramArrayOfObject));
    }
    Apply localApply = Scheme.apply;
    ModuleMethod localModuleMethod = append$Ex;
    paramArrayOfObject = LList.Empty;
    for (;;)
    {
      if (paramObject2 == LList.Empty) {
        return localApply.apply2(localModuleMethod, LList.reverseInPlace(paramArrayOfObject));
      }
      try
      {
        Pair localPair = (Pair)paramObject2;
        paramObject2 = localPair.getCdr();
        paramArrayOfObject = Pair.make(Scheme.applyToArgs.apply2(paramObject1, localPair.getCar()), paramArrayOfObject);
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "arg0", -2, paramObject2);
      }
    }
  }
  
  public static Object appendMap$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
  {
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(paramArrayOfObject)) {
      return Scheme.apply.apply2(append.append, Scheme.apply.apply4(Scheme.map, paramObject1, paramObject2, paramArrayOfObject));
    }
    Apply localApply = Scheme.apply;
    append localappend = append.append;
    paramArrayOfObject = LList.Empty;
    for (;;)
    {
      if (paramObject2 == LList.Empty) {
        return localApply.apply2(localappend, LList.reverseInPlace(paramArrayOfObject));
      }
      try
      {
        Pair localPair = (Pair)paramObject2;
        paramObject2 = localPair.getCdr();
        paramArrayOfObject = Pair.make(Scheme.applyToArgs.apply2(paramObject1, localPair.getCar()), paramArrayOfObject);
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "arg0", -2, paramObject2);
      }
    }
  }
  
  public static Object appendReverse(Object paramObject1, Object paramObject2)
  {
    for (;;)
    {
      if (isNullList(paramObject1) != Boolean.FALSE) {
        return paramObject2;
      }
      Object localObject = lists.cdr.apply1(paramObject1);
      paramObject2 = lists.cons(lists.car.apply1(paramObject1), paramObject2);
      paramObject1 = localObject;
    }
  }
  
  public static Object appendReverse$Ex(Object paramObject1, Object paramObject2)
  {
    for (;;)
    {
      if (isNullList(paramObject1) != Boolean.FALSE) {
        return paramObject2;
      }
      Object localObject = lists.cdr.apply1(paramObject1);
      try
      {
        Pair localPair = (Pair)paramObject1;
        lists.setCdr$Ex(localPair, paramObject2);
        paramObject2 = paramObject1;
        paramObject1 = localObject;
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "set-cdr!", 1, paramObject1);
      }
    }
  }
  
  public static Object jdMethod_break(Object paramObject1, Object paramObject2)
  {
    frame24 localframe24 = new frame24();
    localframe24.pred = paramObject1;
    return span(localframe24.lambda$Fn20, paramObject2);
  }
  
  public static Object break$Ex(Object paramObject1, Object paramObject2)
  {
    frame25 localframe25 = new frame25();
    localframe25.pred = paramObject1;
    return span$Ex(localframe25.lambda$Fn21, paramObject2);
  }
  
  public static Object car$PlCdr(Object paramObject)
  {
    return misc.values(new Object[] { lists.car.apply1(paramObject), lists.cdr.apply1(paramObject) });
  }
  
  public static Pair circularList$V(Object paramObject, Object[] paramArrayOfObject)
  {
    paramArrayOfObject = lists.cons(paramObject, LList.makeList(paramArrayOfObject, 0));
    paramObject = lastPair(paramArrayOfObject);
    try
    {
      Pair localPair = (Pair)paramObject;
      lists.setCdr$Ex(localPair, paramArrayOfObject);
      return paramArrayOfObject;
    }
    catch (ClassCastException paramArrayOfObject)
    {
      throw new WrongType(paramArrayOfObject, "set-cdr!", 1, paramObject);
    }
  }
  
  public static Object concatenate(Object paramObject)
  {
    return reduceRight(append.append, LList.Empty, paramObject);
  }
  
  public static Object concatenate$Ex(Object paramObject)
  {
    return reduceRight(append$Ex, LList.Empty, paramObject);
  }
  
  public static Object cons$St(Object... paramVarArgs)
  {
    return LList.consX(paramVarArgs);
  }
  
  public static Object count$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    Object localObject1 = LList.makeList(paramArrayOfObject, 0);
    Object localObject2;
    if (lists.isPair(localObject1))
    {
      localObject2 = Lit0;
      paramArrayOfObject = (Object[])paramObject;
      paramObject = localObject2;
      for (;;)
      {
        if (isNullList(paramArrayOfObject) != Boolean.FALSE) {
          localObject1 = paramObject;
        }
        Object localObject4;
        do
        {
          return localObject1;
          localObject1 = $PcCars$PlCdrs$SlPair(localObject1);
          localObject4 = lists.car.apply1(localObject1);
          localObject2 = lists.cdr.apply1(localObject1);
          localObject1 = paramObject;
        } while (lists.isNull(localObject4));
        Object localObject3 = lists.cdr.apply1(paramArrayOfObject);
        localObject1 = paramObject;
        if (Scheme.apply.apply3(paramProcedure, lists.car.apply1(paramArrayOfObject), localObject4) != Boolean.FALSE) {
          localObject1 = AddOp.$Pl.apply2(paramObject, Lit1);
        }
        paramArrayOfObject = (Object[])localObject2;
        localObject2 = localObject3;
        paramObject = localObject1;
        localObject1 = paramArrayOfObject;
        paramArrayOfObject = (Object[])localObject2;
      }
    }
    localObject1 = Lit0;
    paramArrayOfObject = (Object[])paramObject;
    for (paramObject = localObject1;; paramObject = localObject1)
    {
      localObject1 = paramObject;
      if (isNullList(paramArrayOfObject) != Boolean.FALSE) {
        break;
      }
      localObject2 = lists.cdr.apply1(paramArrayOfObject);
      localObject1 = paramObject;
      if (paramProcedure.apply1(lists.car.apply1(paramArrayOfObject)) != Boolean.FALSE) {
        localObject1 = AddOp.$Pl.apply2(paramObject, Lit1);
      }
      paramArrayOfObject = (Object[])localObject2;
    }
  }
  
  public static Object delete(Object paramObject1, Object paramObject2)
  {
    return delete(paramObject1, paramObject2, Scheme.isEqual);
  }
  
  public static Object delete(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    frame17 localframe17 = new frame17();
    localframe17.x = paramObject1;
    localframe17.maybe$Mn$Eq = paramObject3;
    return filter(localframe17.lambda$Fn16, paramObject2);
  }
  
  public static Object delete$Ex(Object paramObject1, Object paramObject2)
  {
    return delete$Ex(paramObject1, paramObject2, Scheme.isEqual);
  }
  
  public static Object delete$Ex(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    frame18 localframe18 = new frame18();
    localframe18.x = paramObject1;
    localframe18.maybe$Mn$Eq = paramObject3;
    return filter$Ex(localframe18.lambda$Fn17, paramObject2);
  }
  
  public static Object deleteDuplicates(Object paramObject)
  {
    return deleteDuplicates(paramObject, Scheme.isEqual);
  }
  
  public static Object deleteDuplicates(Object paramObject, Procedure paramProcedure)
  {
    frame19 localframe19 = new frame19();
    localframe19.maybe$Mn$Eq = paramProcedure;
    return localframe19.lambda30recur(paramObject);
  }
  
  public static Object deleteDuplicates$Ex(Object paramObject)
  {
    return deleteDuplicates$Ex(paramObject, Scheme.isEqual);
  }
  
  public static Object deleteDuplicates$Ex(Object paramObject, Procedure paramProcedure)
  {
    frame20 localframe20 = new frame20();
    localframe20.maybe$Mn$Eq = paramProcedure;
    return localframe20.lambda31recur(paramObject);
  }
  
  /* Error */
  public static Object drop(Object paramObject, IntNum paramIntNum)
  {
    // Byte code:
    //   0: aload_1
    //   1: checkcast 1458	java/lang/Number
    //   4: astore_2
    //   5: aload_2
    //   6: invokestatic 1464	kawa/lib/numbers:isZero	(Ljava/lang/Number;)Z
    //   9: ifeq +5 -> 14
    //   12: aload_0
    //   13: areturn
    //   14: getstatic 1253	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   17: aload_0
    //   18: invokevirtual 1251	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   21: astore_0
    //   22: getstatic 1467	gnu/kawa/functions/AddOp:$Mn	Lgnu/kawa/functions/AddOp;
    //   25: aload_1
    //   26: getstatic 1000	gnu/kawa/slib/srfi1:Lit1	Lgnu/math/IntNum;
    //   29: invokevirtual 1362	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   32: astore_1
    //   33: goto -33 -> 0
    //   36: astore_0
    //   37: new 1257	gnu/mapping/WrongType
    //   40: dup
    //   41: aload_0
    //   42: ldc_w 1469
    //   45: iconst_1
    //   46: aload_1
    //   47: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   50: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	51	0	paramObject	Object
    //   0	51	1	paramIntNum	IntNum
    //   4	2	2	localNumber	Number
    // Exception table:
    //   from	to	target	type
    //   0	5	36	java/lang/ClassCastException
  }
  
  public static Object dropRight(Object paramObject, IntNum paramIntNum)
  {
    return lambda1recur(paramObject, drop(paramObject, paramIntNum));
  }
  
  public static Object dropRight$Ex(Object paramObject, IntNum paramIntNum)
  {
    paramIntNum = drop(paramObject, paramIntNum);
    Object localObject;
    if (lists.isPair(paramIntNum))
    {
      localObject = lists.cdr.apply1(paramIntNum);
      paramIntNum = (IntNum)paramObject;
      while (lists.isPair(localObject))
      {
        paramIntNum = lists.cdr.apply1(paramIntNum);
        localObject = lists.cdr.apply1(localObject);
      }
    }
    try
    {
      localObject = (Pair)paramIntNum;
      lists.setCdr$Ex((Pair)localObject, LList.Empty);
      return paramObject;
    }
    catch (ClassCastException paramObject)
    {
      throw new WrongType((ClassCastException)paramObject, "set-cdr!", 1, paramIntNum);
    }
    return LList.Empty;
  }
  
  public static Object dropWhile(Procedure paramProcedure, Object paramObject)
  {
    for (;;)
    {
      Object localObject;
      if (isNullList(paramObject) != Boolean.FALSE) {
        localObject = LList.Empty;
      }
      do
      {
        return localObject;
        localObject = paramObject;
      } while (paramProcedure.apply1(lists.car.apply1(paramObject)) == Boolean.FALSE);
      paramObject = lists.cdr.apply1(paramObject);
    }
  }
  
  public static Object eighth(Object paramObject)
  {
    return lists.cadddr.apply1(lists.cddddr.apply1(paramObject));
  }
  
  public static Object every$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    frame27 localframe27 = new frame27();
    localframe27.pred = paramProcedure;
    localframe27.lis1 = paramObject;
    localframe27.lists = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(localframe27.lists)) {
      paramProcedure = call_with_values.callWithValues(localframe27.lambda$Fn24, localframe27.lambda$Fn25);
    }
    do
    {
      return paramProcedure;
      paramObject = isNullList(localframe27.lis1);
      paramProcedure = (Procedure)paramObject;
    } while (paramObject != Boolean.FALSE);
    paramProcedure = lists.car.apply1(localframe27.lis1);
    for (paramObject = lists.cdr.apply1(localframe27.lis1);; paramObject = lists.cdr.apply1(paramObject))
    {
      if (isNullList(paramObject) != Boolean.FALSE) {
        return localframe27.pred.apply1(paramProcedure);
      }
      paramArrayOfObject = localframe27.pred.apply1(paramProcedure);
      paramProcedure = paramArrayOfObject;
      if (paramArrayOfObject == Boolean.FALSE) {
        break;
      }
      paramProcedure = lists.car.apply1(paramObject);
    }
  }
  
  public static Object fifth(Object paramObject)
  {
    return lists.car.apply1(lists.cddddr.apply1(paramObject));
  }
  
  public static Object filter(Procedure paramProcedure, Object paramObject)
  {
    localObject1 = LList.Empty;
    for (;;)
    {
      if (isNullList(paramObject) != Boolean.FALSE) {}
      try
      {
        paramProcedure = (LList)localObject1;
        return lists.reverse$Ex(paramProcedure);
      }
      catch (ClassCastException paramProcedure)
      {
        Object localObject2;
        throw new WrongType(paramProcedure, "reverse!", 1, localObject1);
      }
      localObject2 = lists.car.apply1(paramObject);
      paramObject = lists.cdr.apply1(paramObject);
      if (paramProcedure.apply1(localObject2) != Boolean.FALSE) {
        localObject1 = lists.cons(localObject2, localObject1);
      }
    }
  }
  
  public static Object filter$Ex(Procedure paramProcedure, Object paramObject)
  {
    localObject1 = paramObject;
    if (isNullList(localObject1) != Boolean.FALSE) {}
    Object localObject3;
    for (;;)
    {
      return localObject1;
      if (paramProcedure.apply1(lists.car.apply1(localObject1)) == Boolean.FALSE)
      {
        localObject1 = lists.cdr.apply1(localObject1);
        break;
      }
      paramObject = lists.cdr.apply1(localObject1);
      localObject2 = localObject1;
      while (lists.isPair(paramObject))
      {
        if (paramProcedure.apply1(lists.car.apply1(paramObject)) == Boolean.FALSE) {
          break label93;
        }
        localObject3 = lists.cdr.apply1(paramObject);
        localObject2 = paramObject;
        paramObject = localObject3;
      }
    }
    label93:
    for (paramObject = lists.cdr.apply1(paramObject);; paramObject = lists.cdr.apply1(paramObject))
    {
      if ((!lists.isPair(paramObject)) || (paramProcedure.apply1(lists.car.apply1(paramObject)) != Boolean.FALSE)) {}
      try
      {
        localObject3 = (Pair)localObject2;
        lists.setCdr$Ex((Pair)localObject3, paramObject);
        localObject3 = lists.cdr.apply1(paramObject);
        localObject2 = paramObject;
        paramObject = localObject3;
      }
      catch (ClassCastException paramProcedure)
      {
        try
        {
          paramProcedure = (Pair)localObject2;
          lists.setCdr$Ex(paramProcedure, paramObject);
          return localObject1;
        }
        catch (ClassCastException paramProcedure)
        {
          throw new WrongType(paramProcedure, "set-cdr!", 1, localObject2);
        }
        paramProcedure = paramProcedure;
        throw new WrongType(paramProcedure, "set-cdr!", 1, localObject2);
      }
    }
  }
  
  public static Object filterMap$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    frame13 localframe13 = new frame13();
    localframe13.f = paramProcedure;
    paramProcedure = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(paramProcedure)) {
      return localframe13.lambda23recur(lists.cons(paramObject, paramProcedure), LList.Empty);
    }
    paramArrayOfObject = LList.Empty;
    paramProcedure = (Procedure)paramObject;
    paramObject = paramArrayOfObject;
    for (;;)
    {
      if (isNullList(paramProcedure) != Boolean.FALSE) {}
      try
      {
        paramProcedure = (LList)paramObject;
        return lists.reverse$Ex(paramProcedure);
      }
      catch (ClassCastException paramProcedure)
      {
        throw new WrongType(paramProcedure, "reverse!", 1, paramObject);
      }
      paramArrayOfObject = localframe13.f.apply1(lists.car.apply1(paramProcedure));
      paramProcedure = lists.cdr.apply1(paramProcedure);
      if (paramArrayOfObject != Boolean.FALSE) {
        paramObject = lists.cons(paramArrayOfObject, paramObject);
      }
    }
  }
  
  public static Object find(Object paramObject1, Object paramObject2)
  {
    try
    {
      Procedure localProcedure = (Procedure)paramObject1;
      paramObject1 = findTail(localProcedure, paramObject2);
      if (paramObject1 != Boolean.FALSE) {
        return lists.car.apply1(paramObject1);
      }
      return Boolean.FALSE;
    }
    catch (ClassCastException paramObject2)
    {
      throw new WrongType((ClassCastException)paramObject2, "find-tail", 0, paramObject1);
    }
  }
  
  public static Object findTail(Procedure paramProcedure, Object paramObject)
  {
    for (;;)
    {
      Object localObject = isNullList(paramObject);
      try
      {
        Boolean localBoolean = Boolean.FALSE;
        if (localObject != localBoolean) {}
        for (int i = 1;; i = 0)
        {
          i = i + 1 & 0x1;
          if (i == 0) {
            break label66;
          }
          if (paramProcedure.apply1(lists.car.apply1(paramObject)) == Boolean.FALSE) {
            break;
          }
          return paramObject;
        }
        paramObject = lists.cdr.apply1(paramObject);
        continue;
        label66:
        if (i != 0) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramProcedure)
      {
        throw new WrongType(paramProcedure, "x", -2, localObject);
      }
    }
  }
  
  public static Object fold$V(Procedure paramProcedure, Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
  {
    frame7 localframe7 = new frame7();
    localframe7.kons = paramProcedure;
    paramProcedure = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(paramProcedure))
    {
      paramObject2 = localframe7.lambda14lp(lists.cons(paramObject2, paramProcedure), paramObject1);
      return paramObject2;
    }
    for (paramProcedure = (Procedure)paramObject2;; paramProcedure = (Procedure)paramObject2)
    {
      paramObject2 = paramObject1;
      if (isNullList(paramProcedure) != Boolean.FALSE) {
        break;
      }
      paramObject2 = lists.cdr.apply1(paramProcedure);
      paramObject1 = localframe7.kons.apply2(lists.car.apply1(paramProcedure), paramObject1);
    }
  }
  
  public static Object foldRight$V(Procedure paramProcedure, Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
  {
    frame9 localframe9 = new frame9();
    localframe9.kons = paramProcedure;
    localframe9.knil = paramObject1;
    paramProcedure = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(paramProcedure)) {
      return localframe9.lambda17recur(lists.cons(paramObject2, paramProcedure));
    }
    return localframe9.lambda18recur(paramObject2);
  }
  
  public static Object iota(IntNum paramIntNum)
  {
    return iota(paramIntNum, Lit0, Lit1);
  }
  
  public static Object iota(IntNum paramIntNum, Numeric paramNumeric)
  {
    return iota(paramIntNum, paramNumeric, Lit1);
  }
  
  public static Object iota(IntNum paramIntNum, Numeric paramNumeric1, Numeric paramNumeric2)
  {
    if (IntNum.compare(paramIntNum, 0L) < 0) {
      misc.error$V("Negative step count", new Object[] { iota, paramIntNum });
    }
    Object localObject1 = AddOp.$Pl.apply2(paramNumeric1, MultiplyOp.$St.apply2(IntNum.add(paramIntNum, -1), paramNumeric2));
    try
    {
      paramNumeric1 = (Numeric)localObject1;
      Object localObject2 = LList.Empty;
      localObject1 = paramIntNum;
      paramIntNum = (IntNum)localObject2;
      while (Scheme.numLEq.apply2(localObject1, Lit0) == Boolean.FALSE)
      {
        localObject1 = AddOp.$Mn.apply2(localObject1, Lit1);
        localObject2 = AddOp.$Mn.apply2(paramNumeric1, paramNumeric2);
        paramIntNum = lists.cons(paramNumeric1, paramIntNum);
        paramNumeric1 = (Numeric)localObject2;
      }
      return paramIntNum;
    }
    catch (ClassCastException paramIntNum)
    {
      throw new WrongType(paramIntNum, "last-val", -2, localObject1);
    }
  }
  
  public static Object isCircularList(Object paramObject)
  {
    Object localObject1 = paramObject;
    Object localObject2 = paramObject;
    paramObject = localObject1;
    boolean bool;
    for (;;)
    {
      bool = lists.isPair(localObject2);
      if (!bool) {
        break label96;
      }
      localObject1 = lists.cdr.apply1(localObject2);
      bool = lists.isPair(localObject1);
      if (!bool) {
        break;
      }
      localObject2 = lists.cdr.apply1(localObject1);
      paramObject = lists.cdr.apply1(paramObject);
      int i;
      if (localObject2 == paramObject) {
        i = 1;
      }
      while (i != 0) {
        if (i != 0)
        {
          return Boolean.TRUE;
          i = 0;
        }
        else
        {
          return Boolean.FALSE;
        }
      }
    }
    if (bool) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    label96:
    if (bool) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
  
  public static Object isDottedList(Object paramObject)
  {
    Object localObject1 = paramObject;
    Object localObject2 = paramObject;
    paramObject = localObject1;
    if (lists.isPair(localObject2))
    {
      localObject1 = lists.cdr.apply1(localObject2);
      if (lists.isPair(localObject1))
      {
        localObject2 = lists.cdr.apply1(localObject1);
        paramObject = lists.cdr.apply1(paramObject);
        if (localObject2 == paramObject) {}
        for (int i = 1;; i = 0)
        {
          i = i + 1 & 0x1;
          if (i == 0) {
            break label69;
          }
          break;
        }
        label69:
        if (i != 0) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      if (lists.isNull(localObject1)) {
        return Boolean.FALSE;
      }
      return Boolean.TRUE;
    }
    if (lists.isNull(localObject2)) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }
  
  public static boolean isNotPair(Object paramObject)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public static Object isNullList(Object paramObject)
  {
    if ((paramObject instanceof Pair)) {
      return Boolean.FALSE;
    }
    if (paramObject == LList.Empty) {
      return Boolean.TRUE;
    }
    return misc.error$V("null-list?: argument out of domain", new Object[] { paramObject });
  }
  
  public static Object isProperList(Object paramObject)
  {
    Object localObject1 = paramObject;
    Object localObject2 = paramObject;
    paramObject = localObject1;
    if (lists.isPair(localObject2))
    {
      localObject1 = lists.cdr.apply1(localObject2);
      if (lists.isPair(localObject1))
      {
        localObject2 = lists.cdr.apply1(localObject1);
        paramObject = lists.cdr.apply1(paramObject);
        if (localObject2 == paramObject) {}
        for (int i = 1;; i = 0)
        {
          i = i + 1 & 0x1;
          if (i == 0) {
            break label69;
          }
          break;
        }
        label69:
        if (i != 0) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      if (lists.isNull(localObject1)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    if (lists.isNull(localObject2)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
  
  public static Object lambda1recur(Object paramObject1, Object paramObject2)
  {
    if (lists.isPair(paramObject2)) {
      return lists.cons(lists.car.apply1(paramObject1), lambda1recur(lists.cdr.apply1(paramObject1), lists.cdr.apply1(paramObject2)));
    }
    return LList.Empty;
  }
  
  public static Object last(Object paramObject)
  {
    GenericProc localGenericProc = lists.car;
    try
    {
      Pair localPair = (Pair)paramObject;
      return localGenericProc.apply1(lastPair(localPair));
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "last-pair", 0, paramObject);
    }
  }
  
  public static Object lastPair(Pair paramPair)
  {
    for (;;)
    {
      Object localObject = lists.cdr.apply1(paramPair);
      if (!lists.isPair(localObject)) {
        break;
      }
      paramPair = (Pair)localObject;
    }
    return paramPair;
  }
  
  public static Object length$Pl(Object paramObject)
  {
    Object localObject1 = Lit0;
    Object localObject2 = paramObject;
    Object localObject3 = paramObject;
    paramObject = localObject2;
    if (lists.isPair(localObject3))
    {
      localObject2 = lists.cdr.apply1(localObject3);
      localObject1 = AddOp.$Pl.apply2(localObject1, Lit1);
      if (lists.isPair(localObject2))
      {
        localObject3 = lists.cdr.apply1(localObject2);
        paramObject = lists.cdr.apply1(paramObject);
        localObject1 = AddOp.$Pl.apply2(localObject1, Lit1);
        if (localObject3 == paramObject) {}
        for (int i = 1;; i = 0)
        {
          i = i + 1 & 0x1;
          if (i == 0) {
            break label100;
          }
          break;
        }
        label100:
        if (i != 0) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return localObject1;
    }
    return localObject1;
  }
  
  public static Object list$Eq$V(Object paramObject, Object[] paramArrayOfObject)
  {
    Object localObject1 = LList.makeList(paramArrayOfObject, 0);
    boolean bool = lists.isNull(localObject1);
    if (bool)
    {
      if (bool) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    paramArrayOfObject = lists.car.apply1(localObject1);
    Object localObject2;
    Object localObject3;
    for (localObject1 = lists.cdr.apply1(localObject1);; localObject1 = localObject3)
    {
      bool = lists.isNull(localObject1);
      if (bool)
      {
        if (bool) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      localObject2 = lists.car.apply1(localObject1);
      localObject3 = lists.cdr.apply1(localObject1);
      if (paramArrayOfObject != localObject2) {
        break;
      }
      paramArrayOfObject = (Object[])localObject2;
    }
    localObject1 = localObject2;
    for (;;)
    {
      if (isNullList(paramArrayOfObject) != Boolean.FALSE)
      {
        paramArrayOfObject = isNullList(localObject1);
        if (paramArrayOfObject != Boolean.FALSE)
        {
          paramArrayOfObject = (Object[])localObject1;
          localObject1 = localObject3;
          break;
        }
        return paramArrayOfObject;
      }
      localObject2 = isNullList(localObject1);
      try
      {
        Boolean localBoolean = Boolean.FALSE;
        if (localObject2 != localBoolean) {}
        for (int i = 1;; i = 0)
        {
          i = i + 1 & 0x1;
          if (i == 0) {
            break label222;
          }
          localObject2 = Scheme.applyToArgs.apply3(paramObject, lists.car.apply1(paramArrayOfObject), lists.car.apply1(localObject1));
          if (localObject2 == Boolean.FALSE) {
            break label220;
          }
          paramArrayOfObject = lists.cdr.apply1(paramArrayOfObject);
          localObject1 = lists.cdr.apply1(localObject1);
          break;
        }
        label220:
        return localObject2;
        label222:
        if (i != 0) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "x", -2, localObject2);
      }
    }
  }
  
  public static LList listCopy(LList paramLList)
  {
    Object localObject2 = LList.Empty;
    Object localObject1 = LList.Empty;
    if (lists.isPair(paramLList))
    {
      Pair localPair1 = lists.cons(lists.car.apply1(paramLList), LList.Empty);
      if (localObject1 == LList.Empty) {
        localObject2 = localPair1;
      }
      for (;;)
      {
        localObject1 = localPair1;
        paramLList = (LList)lists.cdr.apply1(paramLList);
        break;
        try
        {
          Pair localPair2 = (Pair)localObject1;
          lists.setCdr$Ex(localPair2, localPair1);
        }
        catch (ClassCastException paramLList)
        {
          throw new WrongType(paramLList, "set-cdr!", 1, localObject1);
        }
      }
    }
    return (LList)localObject2;
  }
  
  public static Object listIndex$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    frame30 localframe30 = new frame30();
    localframe30.pred = paramProcedure;
    paramProcedure = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(paramProcedure)) {
      paramArrayOfObject = localframe30.lambda44lp(lists.cons(paramObject, paramProcedure), Lit0);
    }
    for (;;)
    {
      return paramArrayOfObject;
      paramProcedure = Lit0;
      label45:
      paramArrayOfObject = isNullList(paramObject);
      try
      {
        Boolean localBoolean = Boolean.FALSE;
        if (paramArrayOfObject != localBoolean) {}
        for (int i = 1;; i = 0)
        {
          i = i + 1 & 0x1;
          if (i == 0) {
            break label127;
          }
          paramArrayOfObject = paramProcedure;
          if (localframe30.pred.apply1(lists.car.apply1(paramObject)) != Boolean.FALSE) {
            break;
          }
          paramObject = lists.cdr.apply1(paramObject);
          paramProcedure = AddOp.$Pl.apply2(paramProcedure, Lit1);
          break label45;
        }
        label127:
        if (i != 0) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramProcedure)
      {
        throw new WrongType(paramProcedure, "x", -2, paramArrayOfObject);
      }
    }
  }
  
  public static Object listTabulate(Object paramObject, Procedure paramProcedure)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public static Object lset$Eq$V(Procedure paramProcedure, Object[] paramArrayOfObject)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public static Object lset$Ls$Eq$V(Procedure paramProcedure, Object[] paramArrayOfObject)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public static Object lsetAdjoin$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    frame32 localframe32 = new frame32();
    localframe32.$Eq = paramProcedure;
    paramProcedure = LList.makeList(paramArrayOfObject, 0);
    return fold$V(localframe32.lambda$Fn30, paramObject, paramProcedure, new Object[0]);
  }
  
  public static Object lsetDiff$PlIntersection$Ex$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    frame53 localframe53 = new frame53();
    localframe53.$Eq = paramProcedure;
    localframe53.lists = LList.makeList(paramArrayOfObject, 0);
    if (every$V(null$Mnlist$Qu, localframe53.lists, new Object[0]) != Boolean.FALSE) {
      return misc.values(new Object[] { paramObject, LList.Empty });
    }
    if (lists.memq(paramObject, localframe53.lists) != Boolean.FALSE) {
      return misc.values(new Object[] { LList.Empty, paramObject });
    }
    return partition$Ex(localframe53.lambda$Fn55, paramObject);
  }
  
  public static Object lsetDiff$PlIntersection$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    frame51 localframe51 = new frame51();
    localframe51.$Eq = paramProcedure;
    localframe51.lists = LList.makeList(paramArrayOfObject, 0);
    if (every$V(null$Mnlist$Qu, localframe51.lists, new Object[0]) != Boolean.FALSE) {
      return misc.values(new Object[] { paramObject, LList.Empty });
    }
    if (lists.memq(paramObject, localframe51.lists) != Boolean.FALSE) {
      return misc.values(new Object[] { LList.Empty, paramObject });
    }
    return partition(localframe51.lambda$Fn53, paramObject);
  }
  
  public static Object lsetDifference$Ex$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    frame43 localframe43 = new frame43();
    localframe43.$Eq = paramProcedure;
    paramProcedure = LList.makeList(paramArrayOfObject, 0);
    localframe43.lists = filter(lists.pair$Qu, paramProcedure);
    if (lists.isNull(localframe43.lists)) {
      return paramObject;
    }
    if (lists.memq(paramObject, localframe43.lists) != Boolean.FALSE) {
      return LList.Empty;
    }
    return filter$Ex(localframe43.lambda$Fn43, paramObject);
  }
  
  public static Object lsetDifference$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    frame41 localframe41 = new frame41();
    localframe41.$Eq = paramProcedure;
    paramProcedure = LList.makeList(paramArrayOfObject, 0);
    localframe41.lists = filter(lists.pair$Qu, paramProcedure);
    if (lists.isNull(localframe41.lists)) {
      return paramObject;
    }
    if (lists.memq(paramObject, localframe41.lists) != Boolean.FALSE) {
      return LList.Empty;
    }
    return filter(localframe41.lambda$Fn41, paramObject);
  }
  
  public static Object lsetIntersection$Ex$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    frame39 localframe39 = new frame39();
    localframe39.$Eq = paramProcedure;
    localframe39.lists = delete(paramObject, LList.makeList(paramArrayOfObject, 0), Scheme.isEq);
    if (any$V(null$Mnlist$Qu, localframe39.lists, new Object[0]) != Boolean.FALSE) {
      paramProcedure = LList.Empty;
    }
    do
    {
      return paramProcedure;
      paramProcedure = (Procedure)paramObject;
    } while (lists.isNull(localframe39.lists));
    return filter$Ex(localframe39.lambda$Fn39, paramObject);
  }
  
  public static Object lsetIntersection$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    frame37 localframe37 = new frame37();
    localframe37.$Eq = paramProcedure;
    localframe37.lists = delete(paramObject, LList.makeList(paramArrayOfObject, 0), Scheme.isEq);
    if (any$V(null$Mnlist$Qu, localframe37.lists, new Object[0]) != Boolean.FALSE) {
      paramProcedure = LList.Empty;
    }
    do
    {
      return paramProcedure;
      paramProcedure = (Procedure)paramObject;
    } while (lists.isNull(localframe37.lists));
    return filter(localframe37.lambda$Fn37, paramObject);
  }
  
  public static Object lsetUnion$Ex$V(Procedure paramProcedure, Object[] paramArrayOfObject)
  {
    frame35 localframe35 = new frame35();
    localframe35.$Eq = paramProcedure;
    paramProcedure = LList.makeList(paramArrayOfObject, 0);
    return reduce(localframe35.lambda$Fn34, LList.Empty, paramProcedure);
  }
  
  public static Object lsetUnion$V(Procedure paramProcedure, Object[] paramArrayOfObject)
  {
    frame33 localframe33 = new frame33();
    localframe33.$Eq = paramProcedure;
    paramProcedure = LList.makeList(paramArrayOfObject, 0);
    return reduce(localframe33.lambda$Fn31, LList.Empty, paramProcedure);
  }
  
  public static Object lsetXor$Ex$V(Procedure paramProcedure, Object[] paramArrayOfObject)
  {
    frame48 localframe48 = new frame48();
    localframe48.$Eq = paramProcedure;
    paramProcedure = LList.makeList(paramArrayOfObject, 0);
    return reduce(localframe48.lambda$Fn49, LList.Empty, paramProcedure);
  }
  
  public static Object lsetXor$V(Procedure paramProcedure, Object[] paramArrayOfObject)
  {
    frame45 localframe45 = new frame45();
    localframe45.$Eq = paramProcedure;
    paramProcedure = LList.makeList(paramArrayOfObject, 0);
    return reduce(localframe45.lambda$Fn45, LList.Empty, paramProcedure);
  }
  
  public static Object makeList$V(Object paramObject, Object[] paramArrayOfObject)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public static Object map$Ex$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    frame12 localframe12 = new frame12();
    localframe12.f = paramProcedure;
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(paramArrayOfObject)) {
      paramProcedure = (Procedure)paramObject;
    }
    for (;;)
    {
      if (isNullList(paramProcedure) != Boolean.FALSE) {
        break label114;
      }
      paramArrayOfObject = $PcCars$PlCdrs$SlNoTest$SlPair(paramArrayOfObject);
      Object localObject = lists.car.apply1(paramArrayOfObject);
      paramArrayOfObject = lists.cdr.apply1(paramArrayOfObject);
      try
      {
        Pair localPair = (Pair)paramProcedure;
        lists.setCar$Ex(localPair, Scheme.apply.apply3(localframe12.f, lists.car.apply1(paramProcedure), localObject));
        paramProcedure = lists.cdr.apply1(paramProcedure);
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "set-car!", 1, paramProcedure);
      }
    }
    pairForEach$V(localframe12.lambda$Fn11, paramObject, new Object[0]);
    label114:
    return paramObject;
  }
  
  public static Object ninth(Object paramObject)
  {
    return lists.car.apply1(lists.cddddr.apply1(lists.cddddr.apply1(paramObject)));
  }
  
  public static Object pairFold$V(Procedure paramProcedure, Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
  {
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(paramArrayOfObject)) {
      for (paramObject2 = lists.cons(paramObject2, paramArrayOfObject);; paramObject2 = paramArrayOfObject)
      {
        paramArrayOfObject = $PcCdrs(paramObject2);
        if (lists.isNull(paramArrayOfObject))
        {
          paramArrayOfObject = (Object[])paramObject1;
          return paramArrayOfObject;
        }
        paramObject1 = Scheme.apply.apply2(paramProcedure, append$Ex$V(new Object[] { paramObject2, LList.list1(paramObject1) }));
      }
    }
    for (;;)
    {
      paramArrayOfObject = (Object[])paramObject1;
      if (isNullList(paramObject2) != Boolean.FALSE) {
        break;
      }
      paramArrayOfObject = lists.cdr.apply1(paramObject2);
      paramObject1 = paramProcedure.apply2(paramObject2, paramObject1);
      paramObject2 = paramArrayOfObject;
    }
  }
  
  public static Object pairFoldRight$V(Procedure paramProcedure, Object paramObject1, Object paramObject2, Object[] paramArrayOfObject)
  {
    frame10 localframe10 = new frame10();
    localframe10.f = paramProcedure;
    localframe10.zero = paramObject1;
    paramProcedure = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(paramProcedure)) {
      return localframe10.lambda19recur(lists.cons(paramObject2, paramProcedure));
    }
    return localframe10.lambda20recur(paramObject2);
  }
  
  public static Object pairForEach$V(Procedure paramProcedure, Object paramObject, Object[] paramArrayOfObject)
  {
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(paramArrayOfObject))
    {
      for (paramObject = lists.cons(paramObject, paramArrayOfObject);; paramObject = paramArrayOfObject)
      {
        paramArrayOfObject = $PcCdrs(paramObject);
        if (!lists.isPair(paramArrayOfObject)) {
          break;
        }
        Scheme.apply.apply2(paramProcedure, paramObject);
      }
      return Values.empty;
    }
    while (isNullList(paramObject) == Boolean.FALSE)
    {
      paramArrayOfObject = lists.cdr.apply1(paramObject);
      paramProcedure.apply1(paramObject);
      paramObject = paramArrayOfObject;
    }
    return Values.empty;
  }
  
  public static Object partition(Procedure paramProcedure, Object paramObject)
  {
    localObject2 = LList.Empty;
    localObject1 = LList.Empty;
    for (;;)
    {
      if (isNullList(paramObject) != Boolean.FALSE) {}
      try
      {
        paramProcedure = (LList)localObject2;
        paramProcedure = lists.reverse$Ex(paramProcedure);
      }
      catch (ClassCastException paramProcedure)
      {
        Object localObject3;
        throw new WrongType(paramProcedure, "reverse!", 1, localObject2);
      }
      try
      {
        paramObject = (LList)localObject1;
        return misc.values(new Object[] { paramProcedure, lists.reverse$Ex((LList)paramObject) });
      }
      catch (ClassCastException paramProcedure)
      {
        throw new WrongType(paramProcedure, "reverse!", 1, localObject1);
      }
      localObject3 = lists.car.apply1(paramObject);
      paramObject = lists.cdr.apply1(paramObject);
      if (paramProcedure.apply1(localObject3) != Boolean.FALSE) {
        localObject2 = lists.cons(localObject3, localObject2);
      } else {
        localObject1 = lists.cons(localObject3, localObject1);
      }
    }
  }
  
  public static Object partition$Ex(Procedure paramProcedure, Object paramObject)
  {
    Pair localPair1 = lists.cons(Lit2, LList.Empty);
    Pair localPair2 = lists.cons(Lit2, LList.Empty);
    localObject2 = localPair1;
    localObject1 = localPair2;
    for (;;)
    {
      if (isNotPair(paramObject)) {}
      try
      {
        paramProcedure = (Pair)localObject2;
        lists.setCdr$Ex(paramProcedure, LList.Empty);
        try
        {
          paramProcedure = (Pair)localObject1;
          lists.setCdr$Ex(paramProcedure, LList.Empty);
          return misc.values(new Object[] { lists.cdr.apply1(localPair1), lists.cdr.apply1(localPair2) });
        }
        catch (ClassCastException paramProcedure)
        {
          Object localObject3;
          throw new WrongType(paramProcedure, "set-cdr!", 1, localObject1);
        }
        if (paramProcedure.apply1(lists.car.apply1(paramObject)) == Boolean.FALSE) {}
      }
      catch (ClassCastException paramProcedure)
      {
        try
        {
          localObject3 = (Pair)localObject2;
          lists.setCdr$Ex((Pair)localObject3, paramObject);
          localObject3 = lists.cdr.apply1(paramObject);
          localObject2 = paramObject;
          paramObject = localObject3;
        }
        catch (ClassCastException paramProcedure)
        {
          throw new WrongType(paramProcedure, "set-cdr!", 1, localObject2);
        }
        try
        {
          localObject3 = (Pair)localObject1;
          lists.setCdr$Ex((Pair)localObject3, paramObject);
          localObject3 = lists.cdr.apply1(paramObject);
          localObject1 = paramObject;
          paramObject = localObject3;
        }
        catch (ClassCastException paramProcedure)
        {
          throw new WrongType(paramProcedure, "set-cdr!", 1, localObject1);
        }
        paramProcedure = paramProcedure;
        throw new WrongType(paramProcedure, "set-cdr!", 1, localObject2);
      }
    }
  }
  
  public static Object reduce(Procedure paramProcedure, Object paramObject1, Object paramObject2)
  {
    if (isNullList(paramObject2) != Boolean.FALSE) {
      return paramObject1;
    }
    return fold$V(paramProcedure, lists.car.apply1(paramObject2), lists.cdr.apply1(paramObject2), new Object[0]);
  }
  
  public static Object reduceRight(Procedure paramProcedure, Object paramObject1, Object paramObject2)
  {
    frame11 localframe11 = new frame11();
    localframe11.f = paramProcedure;
    if (isNullList(paramObject2) != Boolean.FALSE) {
      return paramObject1;
    }
    return localframe11.lambda21recur(lists.car.apply1(paramObject2), lists.cdr.apply1(paramObject2));
  }
  
  public static Object remove(Object paramObject1, Object paramObject2)
  {
    frame15 localframe15 = new frame15();
    localframe15.pred = paramObject1;
    return filter(localframe15.lambda$Fn14, paramObject2);
  }
  
  public static Object remove$Ex(Object paramObject1, Object paramObject2)
  {
    frame16 localframe16 = new frame16();
    localframe16.pred = paramObject1;
    return filter$Ex(localframe16.lambda$Fn15, paramObject2);
  }
  
  public static Object seventh(Object paramObject)
  {
    return lists.caddr.apply1(lists.cddddr.apply1(paramObject));
  }
  
  public static Object sixth(Object paramObject)
  {
    return lists.cadr.apply1(lists.cddddr.apply1(paramObject));
  }
  
  public static Object span(Procedure paramProcedure, Object paramObject)
  {
    Object localObject2 = LList.Empty;
    localObject1 = paramObject;
    for (paramObject = localObject2;; paramObject = lists.cons(localObject2, paramObject))
    {
      if (isNullList(localObject1) != Boolean.FALSE) {}
      try
      {
        paramProcedure = (LList)paramObject;
        return misc.values(new Object[] { lists.reverse$Ex(paramProcedure), localObject1 });
      }
      catch (ClassCastException paramProcedure)
      {
        try
        {
          paramProcedure = (LList)paramObject;
          return misc.values(new Object[] { lists.reverse$Ex(paramProcedure), localObject1 });
        }
        catch (ClassCastException paramProcedure)
        {
          throw new WrongType(paramProcedure, "reverse!", 1, paramObject);
        }
        paramProcedure = paramProcedure;
        throw new WrongType(paramProcedure, "reverse!", 1, paramObject);
      }
      localObject2 = lists.car.apply1(localObject1);
      if (paramProcedure.apply1(localObject2) == Boolean.FALSE) {
        break;
      }
      localObject1 = lists.cdr.apply1(localObject1);
    }
  }
  
  public static Object span$Ex(Procedure paramProcedure, Object paramObject)
  {
    Object localObject1 = isNullList(paramObject);
    if (localObject1 != Boolean.FALSE)
    {
      if (localObject1 == Boolean.FALSE) {}
    }
    else {
      while (paramProcedure.apply1(lists.car.apply1(paramObject)) == Boolean.FALSE) {
        return misc.values(new Object[] { LList.Empty, paramObject });
      }
    }
    localObject1 = lists.cdr.apply1(paramObject);
    Object localObject2 = paramObject;
    if (isNullList(localObject1) != Boolean.FALSE) {}
    for (;;)
    {
      return misc.values(new Object[] { paramObject, localObject1 });
      if (paramProcedure.apply1(lists.car.apply1(localObject1)) != Boolean.FALSE)
      {
        Object localObject3 = lists.cdr.apply1(localObject1);
        localObject2 = localObject1;
        localObject1 = localObject3;
        break;
      }
      try
      {
        paramProcedure = (Pair)localObject2;
        lists.setCdr$Ex(paramProcedure, LList.Empty);
      }
      catch (ClassCastException paramProcedure)
      {
        throw new WrongType(paramProcedure, "set-cdr!", 1, localObject2);
      }
    }
  }
  
  public static Object splitAt(Object paramObject, IntNum paramIntNum)
  {
    Object localObject2 = LList.Empty;
    Object localObject1 = paramObject;
    paramObject = localObject2;
    for (;;)
    {
      try
      {
        localObject2 = (Number)paramIntNum;
        if (!numbers.isZero((Number)localObject2)) {}
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "zero?", 1, paramIntNum);
      }
      try
      {
        paramIntNum = (LList)paramObject;
        return misc.values(new Object[] { lists.reverse$Ex(paramIntNum), localObject1 });
      }
      catch (ClassCastException paramIntNum)
      {
        throw new WrongType(paramIntNum, "reverse!", 1, paramObject);
      }
      paramObject = lists.cons(lists.car.apply1(localObject1), paramObject);
      localObject1 = lists.cdr.apply1(localObject1);
      paramIntNum = AddOp.$Mn.apply2(paramIntNum, Lit1);
    }
  }
  
  public static Object splitAt$Ex(Object paramObject, IntNum paramIntNum)
  {
    if (numbers.isZero(paramIntNum)) {
      return misc.values(new Object[] { LList.Empty, paramObject });
    }
    paramIntNum = drop(paramObject, IntNum.add(paramIntNum, -1));
    Object localObject = lists.cdr.apply1(paramIntNum);
    try
    {
      Pair localPair = (Pair)paramIntNum;
      lists.setCdr$Ex(localPair, LList.Empty);
      return misc.values(new Object[] { paramObject, localObject });
    }
    catch (ClassCastException paramObject)
    {
      throw new WrongType((ClassCastException)paramObject, "set-cdr!", 1, paramIntNum);
    }
  }
  
  public static Object take(Object paramObject, IntNum paramIntNum)
  {
    Object localObject2 = LList.Empty;
    Object localObject1 = paramObject;
    paramObject = localObject2;
    for (;;)
    {
      try
      {
        localObject2 = (Number)paramIntNum;
        if (!numbers.isZero((Number)localObject2)) {}
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "zero?", 1, paramIntNum);
      }
      try
      {
        paramIntNum = (LList)paramObject;
        return lists.reverse$Ex(paramIntNum);
      }
      catch (ClassCastException paramIntNum)
      {
        throw new WrongType(paramIntNum, "reverse!", 1, paramObject);
      }
      localObject2 = lists.cdr.apply1(localObject1);
      paramIntNum = AddOp.$Mn.apply2(paramIntNum, Lit1);
      paramObject = lists.cons(lists.car.apply1(localObject1), paramObject);
      localObject1 = localObject2;
    }
  }
  
  public static Object take$Ex(Object paramObject, IntNum paramIntNum)
  {
    if (numbers.isZero(paramIntNum)) {
      return LList.Empty;
    }
    paramIntNum = drop(paramObject, IntNum.add(paramIntNum, -1));
    try
    {
      Pair localPair = (Pair)paramIntNum;
      lists.setCdr$Ex(localPair, LList.Empty);
      return paramObject;
    }
    catch (ClassCastException paramObject)
    {
      throw new WrongType((ClassCastException)paramObject, "set-cdr!", 1, paramIntNum);
    }
  }
  
  public static Object takeRight(Object paramObject, IntNum paramIntNum)
  {
    Object localObject = drop(paramObject, paramIntNum);
    paramIntNum = (IntNum)paramObject;
    for (paramObject = localObject; lists.isPair(paramObject); paramObject = lists.cdr.apply1(paramObject)) {
      paramIntNum = lists.cdr.apply1(paramIntNum);
    }
    return paramIntNum;
  }
  
  public static Object takeWhile(Procedure paramProcedure, Object paramObject)
  {
    frame23 localframe23 = new frame23();
    localframe23.pred = paramProcedure;
    return localframe23.lambda34recur(paramObject);
  }
  
  public static Object takeWhile$Ex(Procedure paramProcedure, Object paramObject)
  {
    Object localObject1 = isNullList(paramObject);
    Object localObject3;
    if (localObject1 != Boolean.FALSE)
    {
      if (localObject1 == Boolean.FALSE) {}
    }
    else {
      while (paramProcedure.apply1(lists.car.apply1(paramObject)) == Boolean.FALSE)
      {
        localObject3 = LList.Empty;
        return localObject3;
      }
    }
    localObject1 = lists.cdr.apply1(paramObject);
    Object localObject2 = paramObject;
    for (;;)
    {
      localObject3 = paramObject;
      if (!lists.isPair(localObject1)) {
        break;
      }
      if (paramProcedure.apply1(lists.car.apply1(localObject1)) == Boolean.FALSE) {
        break label98;
      }
      localObject3 = lists.cdr.apply1(localObject1);
      localObject2 = localObject1;
      localObject1 = localObject3;
    }
    try
    {
      label98:
      paramProcedure = (Pair)localObject2;
      lists.setCdr$Ex(paramProcedure, LList.Empty);
      return paramObject;
    }
    catch (ClassCastException paramProcedure)
    {
      throw new WrongType(paramProcedure, "set-cdr!", 1, localObject2);
    }
  }
  
  public static Object tenth(Object paramObject)
  {
    return lists.cadr.apply1(lists.cddddr.apply1(lists.cddddr.apply1(paramObject)));
  }
  
  public static Object unfold$V(Procedure paramProcedure1, Procedure paramProcedure2, Procedure paramProcedure3, Object paramObject, Object[] paramArrayOfObject)
  {
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    if (lists.isPair(paramArrayOfObject))
    {
      Object localObject2 = lists.car.apply1(paramArrayOfObject);
      if (lists.isPair(lists.cdr.apply1(paramArrayOfObject))) {
        return Scheme.apply.applyN(new Object[] { misc.error, "Too many arguments", unfold, paramProcedure1, paramProcedure2, paramProcedure3, paramObject, paramArrayOfObject });
      }
      localObject1 = LList.Empty;
      paramArrayOfObject = (Object[])paramObject;
      paramObject = localObject1;
      for (;;)
      {
        if (paramProcedure1.apply1(paramArrayOfObject) != Boolean.FALSE) {
          return appendReverse$Ex(paramObject, Scheme.applyToArgs.apply2(localObject2, paramArrayOfObject));
        }
        localObject1 = paramProcedure3.apply1(paramArrayOfObject);
        paramObject = lists.cons(paramProcedure2.apply1(paramArrayOfObject), paramObject);
        paramArrayOfObject = (Object[])localObject1;
      }
    }
    Object localObject1 = LList.Empty;
    paramArrayOfObject = (Object[])paramObject;
    paramObject = localObject1;
    for (;;)
    {
      if (paramProcedure1.apply1(paramArrayOfObject) != Boolean.FALSE) {}
      try
      {
        paramProcedure1 = (LList)paramObject;
        return lists.reverse$Ex(paramProcedure1);
      }
      catch (ClassCastException paramProcedure1)
      {
        throw new WrongType(paramProcedure1, "reverse!", 1, paramObject);
      }
      localObject1 = paramProcedure3.apply1(paramArrayOfObject);
      paramObject = lists.cons(paramProcedure2.apply1(paramArrayOfObject), paramObject);
      paramArrayOfObject = (Object[])localObject1;
    }
  }
  
  public static Object unfoldRight(Procedure paramProcedure1, Procedure paramProcedure2, Procedure paramProcedure3, Object paramObject)
  {
    return unfoldRight(paramProcedure1, paramProcedure2, paramProcedure3, paramObject, LList.Empty);
  }
  
  public static Object unfoldRight(Procedure paramProcedure1, Procedure paramProcedure2, Procedure paramProcedure3, Object paramObject1, Object paramObject2)
  {
    for (;;)
    {
      if (paramProcedure1.apply1(paramObject1) != Boolean.FALSE) {
        return paramObject2;
      }
      Object localObject = paramProcedure3.apply1(paramObject1);
      paramObject2 = lists.cons(paramProcedure2.apply1(paramObject1), paramObject2);
      paramObject1 = localObject;
    }
  }
  
  public static LList unzip1(Object paramObject)
  {
    Object localObject = LList.Empty;
    for (;;)
    {
      if (paramObject == LList.Empty) {
        return LList.reverseInPlace(localObject);
      }
      try
      {
        Pair localPair = (Pair)paramObject;
        paramObject = localPair.getCdr();
        localObject = Pair.make(lists.car.apply1(localPair.getCar()), localObject);
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "arg0", -2, paramObject);
      }
    }
  }
  
  public static Object unzip2(Object paramObject)
  {
    new frame();
    return frame.lambda2recur(paramObject);
  }
  
  public static Object unzip3(Object paramObject)
  {
    new frame1();
    return frame1.lambda5recur(paramObject);
  }
  
  public static Object unzip4(Object paramObject)
  {
    new frame3();
    return frame3.lambda8recur(paramObject);
  }
  
  public static Object unzip5(Object paramObject)
  {
    new frame5();
    return frame5.lambda11recur(paramObject);
  }
  
  public static Pair xcons(Object paramObject1, Object paramObject2)
  {
    return lists.cons(paramObject2, paramObject1);
  }
  
  public static Object zip$V(Object paramObject, Object[] paramArrayOfObject)
  {
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    return Scheme.apply.apply4(Scheme.map, LangObjType.listType, paramObject, paramArrayOfObject);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    }
    try
    {
      paramModuleMethod = (LList)paramObject;
      return listCopy(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "list-copy", 1, paramObject);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject);
      return iota(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "iota", 1, paramObject);
    }
    return isProperList(paramObject);
    return isDottedList(paramObject);
    return isCircularList(paramObject);
    if (isNotPair(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    return isNullList(paramObject);
    return length$Pl(paramObject);
    return fifth(paramObject);
    return sixth(paramObject);
    return seventh(paramObject);
    return eighth(paramObject);
    return ninth(paramObject);
    return tenth(paramObject);
    return car$PlCdr(paramObject);
    return last(paramObject);
    try
    {
      paramModuleMethod = (Pair)paramObject;
      return lastPair(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "last-pair", 1, paramObject);
    }
    return unzip1(paramObject);
    return unzip2(paramObject);
    return unzip3(paramObject);
    return unzip4(paramObject);
    return unzip5(paramObject);
    return concatenate(paramObject);
    return concatenate$Ex(paramObject);
    return deleteDuplicates(paramObject);
    return deleteDuplicates$Ex(paramObject);
    return alistCopy(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    case 78: 
      return xcons(paramObject1, paramObject2);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject2;
      return listTabulate(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "list-tabulate", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "iota", 1, paramObject1);
    }
    try
    {
      paramObject1 = LangObjType.coerceNumeric(paramObject2);
      return iota(paramModuleMethod, (Numeric)paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "iota", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject2);
      return take(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "take", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject2);
      return drop(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "drop", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject2);
      return take$Ex(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "take!", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject2);
      return takeRight(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "take-right", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject2);
      return dropRight(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "drop-right", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject2);
      return dropRight$Ex(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "drop-right!", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject2);
      return splitAt(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "split-at", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject2);
      return splitAt$Ex(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "split-at!", 2, paramObject2);
    }
    return appendReverse(paramObject1, paramObject2);
    return appendReverse$Ex(paramObject1, paramObject2);
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      return filter(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "filter", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      return filter$Ex(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "filter!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      return partition(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "partition", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      return partition$Ex(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "partition!", 1, paramObject1);
    }
    return remove(paramObject1, paramObject2);
    return remove$Ex(paramObject1, paramObject2);
    return delete(paramObject1, paramObject2);
    return delete$Ex(paramObject1, paramObject2);
    try
    {
      paramModuleMethod = (Procedure)paramObject2;
      return deleteDuplicates(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "delete-duplicates", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject2;
      return deleteDuplicates$Ex(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "delete-duplicates!", 2, paramObject2);
    }
    return alistDelete(paramObject1, paramObject2);
    return alistDelete$Ex(paramObject1, paramObject2);
    return find(paramObject1, paramObject2);
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      return findTail(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "find-tail", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      return takeWhile(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "take-while", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      return dropWhile(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "drop-while", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      return takeWhile$Ex(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "take-while!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      return span(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "span", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      return span$Ex(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "span!", 1, paramObject1);
    }
    return jdMethod_break(paramObject1, paramObject2);
    return break$Ex(paramObject1, paramObject2);
    return frame61.lambda84(paramObject1, paramObject2);
    return frame71.lambda100(paramObject1, paramObject2);
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "iota", 1, paramObject1);
    }
    try
    {
      paramObject1 = LangObjType.coerceNumeric(paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "iota", 2, paramObject2);
    }
    try
    {
      paramObject2 = LangObjType.coerceNumeric(paramObject3);
      return iota(paramModuleMethod, (Numeric)paramObject1, (Numeric)paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "iota", 3, paramObject3);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      return reduce(paramModuleMethod, paramObject2, paramObject3);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "reduce", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      return reduceRight(paramModuleMethod, paramObject2, paramObject3);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "reduce-right", 1, paramObject1);
    }
    return delete(paramObject1, paramObject2, paramObject3);
    return delete$Ex(paramObject1, paramObject2, paramObject3);
    return alistCons(paramObject1, paramObject2, paramObject3);
    return alistDelete(paramObject1, paramObject2, paramObject3);
    return alistDelete$Ex(paramObject1, paramObject2, paramObject3);
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    if (paramModuleMethod.selector == 123) {}
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "unfold-right", 1, paramObject1);
    }
    try
    {
      paramObject1 = (Procedure)paramObject2;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "unfold-right", 2, paramObject2);
    }
    try
    {
      paramObject2 = (Procedure)paramObject3;
      return unfoldRight(paramModuleMethod, (Procedure)paramObject1, (Procedure)paramObject2, paramObject4);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "unfold-right", 3, paramObject3);
    }
    return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  /* Error */
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 1805	gnu/expr/ModuleMethod:selector	I
    //   4: lookupswitch	default:+284->288, 79:+291->295, 81:+337->341, 86:+342->346, 92:+388->392, 94:+434->438, 117:+480->484, 122:+485->489, 123:+544->548, 125:+615->619, 126:+702->706, 127:+768->772, 128:+834->838, 129:+900->904, 132:+966->970, 133:+1020->1024, 134:+1074->1078, 135:+1133->1137, 136:+1192->1196, 166:+1251->1255, 167:+1310->1314, 168:+1369->1373, 169:+1428->1432, 170:+1479->1483, 171:+1530->1534, 172:+1589->1593, 173:+1640->1644, 174:+1691->1695, 175:+1750->1754, 176:+1809->1813, 177:+1868->1872, 178:+1927->1931, 179:+1978->1982, 180:+2029->2033, 181:+2088->2092
    //   288: aload_0
    //   289: aload_1
    //   290: aload_2
    //   291: invokespecial 1931	gnu/expr/ModuleBody:applyN	(Lgnu/expr/ModuleMethod;[Ljava/lang/Object;)Ljava/lang/Object;
    //   294: areturn
    //   295: aload_2
    //   296: iconst_0
    //   297: aaload
    //   298: astore_1
    //   299: aload_2
    //   300: arraylength
    //   301: iconst_1
    //   302: isub
    //   303: istore 7
    //   305: iload 7
    //   307: anewarray 553	java/lang/Object
    //   310: astore_3
    //   311: iload 7
    //   313: iconst_1
    //   314: isub
    //   315: istore 7
    //   317: iload 7
    //   319: ifge +9 -> 328
    //   322: aload_1
    //   323: aload_3
    //   324: invokestatic 1933	gnu/kawa/slib/srfi1:makeList$V	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   327: areturn
    //   328: aload_3
    //   329: iload 7
    //   331: aload_2
    //   332: iload 7
    //   334: iconst_1
    //   335: iadd
    //   336: aaload
    //   337: aastore
    //   338: goto -27 -> 311
    //   341: aload_2
    //   342: invokestatic 1935	gnu/kawa/slib/srfi1:cons$St	([Ljava/lang/Object;)Ljava/lang/Object;
    //   345: areturn
    //   346: aload_2
    //   347: iconst_0
    //   348: aaload
    //   349: astore_1
    //   350: aload_2
    //   351: arraylength
    //   352: iconst_1
    //   353: isub
    //   354: istore 7
    //   356: iload 7
    //   358: anewarray 553	java/lang/Object
    //   361: astore_3
    //   362: iload 7
    //   364: iconst_1
    //   365: isub
    //   366: istore 7
    //   368: iload 7
    //   370: ifge +9 -> 379
    //   373: aload_1
    //   374: aload_3
    //   375: invokestatic 1937	gnu/kawa/slib/srfi1:circularList$V	(Ljava/lang/Object;[Ljava/lang/Object;)Lgnu/lists/Pair;
    //   378: areturn
    //   379: aload_3
    //   380: iload 7
    //   382: aload_2
    //   383: iload 7
    //   385: iconst_1
    //   386: iadd
    //   387: aaload
    //   388: aastore
    //   389: goto -27 -> 362
    //   392: aload_2
    //   393: iconst_0
    //   394: aaload
    //   395: astore_1
    //   396: aload_2
    //   397: arraylength
    //   398: iconst_1
    //   399: isub
    //   400: istore 7
    //   402: iload 7
    //   404: anewarray 553	java/lang/Object
    //   407: astore_3
    //   408: iload 7
    //   410: iconst_1
    //   411: isub
    //   412: istore 7
    //   414: iload 7
    //   416: ifge +9 -> 425
    //   419: aload_1
    //   420: aload_3
    //   421: invokestatic 1939	gnu/kawa/slib/srfi1:list$Eq$V	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   424: areturn
    //   425: aload_3
    //   426: iload 7
    //   428: aload_2
    //   429: iload 7
    //   431: iconst_1
    //   432: iadd
    //   433: aaload
    //   434: aastore
    //   435: goto -27 -> 408
    //   438: aload_2
    //   439: iconst_0
    //   440: aaload
    //   441: astore_1
    //   442: aload_2
    //   443: arraylength
    //   444: iconst_1
    //   445: isub
    //   446: istore 7
    //   448: iload 7
    //   450: anewarray 553	java/lang/Object
    //   453: astore_3
    //   454: iload 7
    //   456: iconst_1
    //   457: isub
    //   458: istore 7
    //   460: iload 7
    //   462: ifge +9 -> 471
    //   465: aload_1
    //   466: aload_3
    //   467: invokestatic 1941	gnu/kawa/slib/srfi1:zip$V	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   470: areturn
    //   471: aload_3
    //   472: iload 7
    //   474: aload_2
    //   475: iload 7
    //   477: iconst_1
    //   478: iadd
    //   479: aaload
    //   480: aastore
    //   481: goto -27 -> 454
    //   484: aload_2
    //   485: invokestatic 1710	gnu/kawa/slib/srfi1:append$Ex$V	([Ljava/lang/Object;)Ljava/lang/Object;
    //   488: areturn
    //   489: aload_2
    //   490: iconst_0
    //   491: aaload
    //   492: astore_3
    //   493: aload_3
    //   494: checkcast 1248	gnu/mapping/Procedure
    //   497: astore_1
    //   498: aload_2
    //   499: iconst_1
    //   500: aaload
    //   501: astore_3
    //   502: aload_2
    //   503: arraylength
    //   504: iconst_2
    //   505: isub
    //   506: istore 7
    //   508: iload 7
    //   510: anewarray 553	java/lang/Object
    //   513: astore 4
    //   515: iload 7
    //   517: iconst_1
    //   518: isub
    //   519: istore 7
    //   521: iload 7
    //   523: ifge +11 -> 534
    //   526: aload_1
    //   527: aload_3
    //   528: aload 4
    //   530: invokestatic 1943	gnu/kawa/slib/srfi1:count$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   533: areturn
    //   534: aload 4
    //   536: iload 7
    //   538: aload_2
    //   539: iload 7
    //   541: iconst_2
    //   542: iadd
    //   543: aaload
    //   544: aastore
    //   545: goto -30 -> 515
    //   548: aload_2
    //   549: arraylength
    //   550: iconst_4
    //   551: isub
    //   552: istore 7
    //   554: aload_2
    //   555: iconst_0
    //   556: aaload
    //   557: astore_3
    //   558: aload_3
    //   559: checkcast 1248	gnu/mapping/Procedure
    //   562: astore_1
    //   563: aload_2
    //   564: iconst_1
    //   565: aaload
    //   566: astore 4
    //   568: aload 4
    //   570: checkcast 1248	gnu/mapping/Procedure
    //   573: astore_3
    //   574: aload_2
    //   575: iconst_2
    //   576: aaload
    //   577: astore 4
    //   579: aload 4
    //   581: checkcast 1248	gnu/mapping/Procedure
    //   584: astore 5
    //   586: aload_2
    //   587: iconst_3
    //   588: aaload
    //   589: astore 4
    //   591: iload 7
    //   593: ifgt +13 -> 606
    //   596: aload_1
    //   597: aload_3
    //   598: aload 5
    //   600: aload 4
    //   602: invokestatic 1926	gnu/kawa/slib/srfi1:unfoldRight	(Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;Ljava/lang/Object;)Ljava/lang/Object;
    //   605: areturn
    //   606: aload_1
    //   607: aload_3
    //   608: aload 5
    //   610: aload 4
    //   612: aload_2
    //   613: iconst_4
    //   614: aaload
    //   615: invokestatic 1778	gnu/kawa/slib/srfi1:unfoldRight	(Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   618: areturn
    //   619: aload_2
    //   620: iconst_0
    //   621: aaload
    //   622: astore_3
    //   623: aload_3
    //   624: checkcast 1248	gnu/mapping/Procedure
    //   627: astore_1
    //   628: aload_2
    //   629: iconst_1
    //   630: aaload
    //   631: astore 4
    //   633: aload 4
    //   635: checkcast 1248	gnu/mapping/Procedure
    //   638: astore_3
    //   639: aload_2
    //   640: iconst_2
    //   641: aaload
    //   642: astore 5
    //   644: aload 5
    //   646: checkcast 1248	gnu/mapping/Procedure
    //   649: astore 4
    //   651: aload_2
    //   652: iconst_3
    //   653: aaload
    //   654: astore 5
    //   656: aload_2
    //   657: arraylength
    //   658: iconst_4
    //   659: isub
    //   660: istore 7
    //   662: iload 7
    //   664: anewarray 553	java/lang/Object
    //   667: astore 6
    //   669: iload 7
    //   671: iconst_1
    //   672: isub
    //   673: istore 7
    //   675: iload 7
    //   677: ifge +15 -> 692
    //   680: aload_1
    //   681: aload_3
    //   682: aload 4
    //   684: aload 5
    //   686: aload 6
    //   688: invokestatic 1945	gnu/kawa/slib/srfi1:unfold$V	(Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   691: areturn
    //   692: aload 6
    //   694: iload 7
    //   696: aload_2
    //   697: iload 7
    //   699: iconst_4
    //   700: iadd
    //   701: aaload
    //   702: aastore
    //   703: goto -34 -> 669
    //   706: aload_2
    //   707: iconst_0
    //   708: aaload
    //   709: astore_3
    //   710: aload_3
    //   711: checkcast 1248	gnu/mapping/Procedure
    //   714: astore_1
    //   715: aload_2
    //   716: iconst_1
    //   717: aaload
    //   718: astore_3
    //   719: aload_2
    //   720: iconst_2
    //   721: aaload
    //   722: astore 4
    //   724: aload_2
    //   725: arraylength
    //   726: iconst_3
    //   727: isub
    //   728: istore 7
    //   730: iload 7
    //   732: anewarray 553	java/lang/Object
    //   735: astore 5
    //   737: iload 7
    //   739: iconst_1
    //   740: isub
    //   741: istore 7
    //   743: iload 7
    //   745: ifge +13 -> 758
    //   748: aload_1
    //   749: aload_3
    //   750: aload 4
    //   752: aload 5
    //   754: invokestatic 1600	gnu/kawa/slib/srfi1:fold$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   757: areturn
    //   758: aload 5
    //   760: iload 7
    //   762: aload_2
    //   763: iload 7
    //   765: iconst_3
    //   766: iadd
    //   767: aaload
    //   768: aastore
    //   769: goto -32 -> 737
    //   772: aload_2
    //   773: iconst_0
    //   774: aaload
    //   775: astore_3
    //   776: aload_3
    //   777: checkcast 1248	gnu/mapping/Procedure
    //   780: astore_1
    //   781: aload_2
    //   782: iconst_1
    //   783: aaload
    //   784: astore_3
    //   785: aload_2
    //   786: iconst_2
    //   787: aaload
    //   788: astore 4
    //   790: aload_2
    //   791: arraylength
    //   792: iconst_3
    //   793: isub
    //   794: istore 7
    //   796: iload 7
    //   798: anewarray 553	java/lang/Object
    //   801: astore 5
    //   803: iload 7
    //   805: iconst_1
    //   806: isub
    //   807: istore 7
    //   809: iload 7
    //   811: ifge +13 -> 824
    //   814: aload_1
    //   815: aload_3
    //   816: aload 4
    //   818: aload 5
    //   820: invokestatic 1947	gnu/kawa/slib/srfi1:foldRight$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   823: areturn
    //   824: aload 5
    //   826: iload 7
    //   828: aload_2
    //   829: iload 7
    //   831: iconst_3
    //   832: iadd
    //   833: aaload
    //   834: aastore
    //   835: goto -32 -> 803
    //   838: aload_2
    //   839: iconst_0
    //   840: aaload
    //   841: astore_3
    //   842: aload_3
    //   843: checkcast 1248	gnu/mapping/Procedure
    //   846: astore_1
    //   847: aload_2
    //   848: iconst_1
    //   849: aaload
    //   850: astore_3
    //   851: aload_2
    //   852: iconst_2
    //   853: aaload
    //   854: astore 4
    //   856: aload_2
    //   857: arraylength
    //   858: iconst_3
    //   859: isub
    //   860: istore 7
    //   862: iload 7
    //   864: anewarray 553	java/lang/Object
    //   867: astore 5
    //   869: iload 7
    //   871: iconst_1
    //   872: isub
    //   873: istore 7
    //   875: iload 7
    //   877: ifge +13 -> 890
    //   880: aload_1
    //   881: aload_3
    //   882: aload 4
    //   884: aload 5
    //   886: invokestatic 1949	gnu/kawa/slib/srfi1:pairFoldRight$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   889: areturn
    //   890: aload 5
    //   892: iload 7
    //   894: aload_2
    //   895: iload 7
    //   897: iconst_3
    //   898: iadd
    //   899: aaload
    //   900: aastore
    //   901: goto -32 -> 869
    //   904: aload_2
    //   905: iconst_0
    //   906: aaload
    //   907: astore_3
    //   908: aload_3
    //   909: checkcast 1248	gnu/mapping/Procedure
    //   912: astore_1
    //   913: aload_2
    //   914: iconst_1
    //   915: aaload
    //   916: astore_3
    //   917: aload_2
    //   918: iconst_2
    //   919: aaload
    //   920: astore 4
    //   922: aload_2
    //   923: arraylength
    //   924: iconst_3
    //   925: isub
    //   926: istore 7
    //   928: iload 7
    //   930: anewarray 553	java/lang/Object
    //   933: astore 5
    //   935: iload 7
    //   937: iconst_1
    //   938: isub
    //   939: istore 7
    //   941: iload 7
    //   943: ifge +13 -> 956
    //   946: aload_1
    //   947: aload_3
    //   948: aload 4
    //   950: aload 5
    //   952: invokestatic 1951	gnu/kawa/slib/srfi1:pairFold$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   955: areturn
    //   956: aload 5
    //   958: iload 7
    //   960: aload_2
    //   961: iload 7
    //   963: iconst_3
    //   964: iadd
    //   965: aaload
    //   966: aastore
    //   967: goto -32 -> 935
    //   970: aload_2
    //   971: iconst_0
    //   972: aaload
    //   973: astore_1
    //   974: aload_2
    //   975: iconst_1
    //   976: aaload
    //   977: astore_3
    //   978: aload_2
    //   979: arraylength
    //   980: iconst_2
    //   981: isub
    //   982: istore 7
    //   984: iload 7
    //   986: anewarray 553	java/lang/Object
    //   989: astore 4
    //   991: iload 7
    //   993: iconst_1
    //   994: isub
    //   995: istore 7
    //   997: iload 7
    //   999: ifge +11 -> 1010
    //   1002: aload_1
    //   1003: aload_3
    //   1004: aload 4
    //   1006: invokestatic 1953	gnu/kawa/slib/srfi1:appendMap$V	(Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1009: areturn
    //   1010: aload 4
    //   1012: iload 7
    //   1014: aload_2
    //   1015: iload 7
    //   1017: iconst_2
    //   1018: iadd
    //   1019: aaload
    //   1020: aastore
    //   1021: goto -30 -> 991
    //   1024: aload_2
    //   1025: iconst_0
    //   1026: aaload
    //   1027: astore_1
    //   1028: aload_2
    //   1029: iconst_1
    //   1030: aaload
    //   1031: astore_3
    //   1032: aload_2
    //   1033: arraylength
    //   1034: iconst_2
    //   1035: isub
    //   1036: istore 7
    //   1038: iload 7
    //   1040: anewarray 553	java/lang/Object
    //   1043: astore 4
    //   1045: iload 7
    //   1047: iconst_1
    //   1048: isub
    //   1049: istore 7
    //   1051: iload 7
    //   1053: ifge +11 -> 1064
    //   1056: aload_1
    //   1057: aload_3
    //   1058: aload 4
    //   1060: invokestatic 1955	gnu/kawa/slib/srfi1:appendMap$Ex$V	(Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1063: areturn
    //   1064: aload 4
    //   1066: iload 7
    //   1068: aload_2
    //   1069: iload 7
    //   1071: iconst_2
    //   1072: iadd
    //   1073: aaload
    //   1074: aastore
    //   1075: goto -30 -> 1045
    //   1078: aload_2
    //   1079: iconst_0
    //   1080: aaload
    //   1081: astore_3
    //   1082: aload_3
    //   1083: checkcast 1248	gnu/mapping/Procedure
    //   1086: astore_1
    //   1087: aload_2
    //   1088: iconst_1
    //   1089: aaload
    //   1090: astore_3
    //   1091: aload_2
    //   1092: arraylength
    //   1093: iconst_2
    //   1094: isub
    //   1095: istore 7
    //   1097: iload 7
    //   1099: anewarray 553	java/lang/Object
    //   1102: astore 4
    //   1104: iload 7
    //   1106: iconst_1
    //   1107: isub
    //   1108: istore 7
    //   1110: iload 7
    //   1112: ifge +11 -> 1123
    //   1115: aload_1
    //   1116: aload_3
    //   1117: aload 4
    //   1119: invokestatic 1699	gnu/kawa/slib/srfi1:pairForEach$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1122: areturn
    //   1123: aload 4
    //   1125: iload 7
    //   1127: aload_2
    //   1128: iload 7
    //   1130: iconst_2
    //   1131: iadd
    //   1132: aaload
    //   1133: aastore
    //   1134: goto -30 -> 1104
    //   1137: aload_2
    //   1138: iconst_0
    //   1139: aaload
    //   1140: astore_3
    //   1141: aload_3
    //   1142: checkcast 1248	gnu/mapping/Procedure
    //   1145: astore_1
    //   1146: aload_2
    //   1147: iconst_1
    //   1148: aaload
    //   1149: astore_3
    //   1150: aload_2
    //   1151: arraylength
    //   1152: iconst_2
    //   1153: isub
    //   1154: istore 7
    //   1156: iload 7
    //   1158: anewarray 553	java/lang/Object
    //   1161: astore 4
    //   1163: iload 7
    //   1165: iconst_1
    //   1166: isub
    //   1167: istore 7
    //   1169: iload 7
    //   1171: ifge +11 -> 1182
    //   1174: aload_1
    //   1175: aload_3
    //   1176: aload 4
    //   1178: invokestatic 1957	gnu/kawa/slib/srfi1:map$Ex$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1181: areturn
    //   1182: aload 4
    //   1184: iload 7
    //   1186: aload_2
    //   1187: iload 7
    //   1189: iconst_2
    //   1190: iadd
    //   1191: aaload
    //   1192: aastore
    //   1193: goto -30 -> 1163
    //   1196: aload_2
    //   1197: iconst_0
    //   1198: aaload
    //   1199: astore_3
    //   1200: aload_3
    //   1201: checkcast 1248	gnu/mapping/Procedure
    //   1204: astore_1
    //   1205: aload_2
    //   1206: iconst_1
    //   1207: aaload
    //   1208: astore_3
    //   1209: aload_2
    //   1210: arraylength
    //   1211: iconst_2
    //   1212: isub
    //   1213: istore 7
    //   1215: iload 7
    //   1217: anewarray 553	java/lang/Object
    //   1220: astore 4
    //   1222: iload 7
    //   1224: iconst_1
    //   1225: isub
    //   1226: istore 7
    //   1228: iload 7
    //   1230: ifge +11 -> 1241
    //   1233: aload_1
    //   1234: aload_3
    //   1235: aload 4
    //   1237: invokestatic 1959	gnu/kawa/slib/srfi1:filterMap$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1240: areturn
    //   1241: aload 4
    //   1243: iload 7
    //   1245: aload_2
    //   1246: iload 7
    //   1248: iconst_2
    //   1249: iadd
    //   1250: aaload
    //   1251: aastore
    //   1252: goto -30 -> 1222
    //   1255: aload_2
    //   1256: iconst_0
    //   1257: aaload
    //   1258: astore_3
    //   1259: aload_3
    //   1260: checkcast 1248	gnu/mapping/Procedure
    //   1263: astore_1
    //   1264: aload_2
    //   1265: iconst_1
    //   1266: aaload
    //   1267: astore_3
    //   1268: aload_2
    //   1269: arraylength
    //   1270: iconst_2
    //   1271: isub
    //   1272: istore 7
    //   1274: iload 7
    //   1276: anewarray 553	java/lang/Object
    //   1279: astore 4
    //   1281: iload 7
    //   1283: iconst_1
    //   1284: isub
    //   1285: istore 7
    //   1287: iload 7
    //   1289: ifge +11 -> 1300
    //   1292: aload_1
    //   1293: aload_3
    //   1294: aload 4
    //   1296: invokestatic 1648	gnu/kawa/slib/srfi1:any$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1299: areturn
    //   1300: aload 4
    //   1302: iload 7
    //   1304: aload_2
    //   1305: iload 7
    //   1307: iconst_2
    //   1308: iadd
    //   1309: aaload
    //   1310: aastore
    //   1311: goto -30 -> 1281
    //   1314: aload_2
    //   1315: iconst_0
    //   1316: aaload
    //   1317: astore_3
    //   1318: aload_3
    //   1319: checkcast 1248	gnu/mapping/Procedure
    //   1322: astore_1
    //   1323: aload_2
    //   1324: iconst_1
    //   1325: aaload
    //   1326: astore_3
    //   1327: aload_2
    //   1328: arraylength
    //   1329: iconst_2
    //   1330: isub
    //   1331: istore 7
    //   1333: iload 7
    //   1335: anewarray 553	java/lang/Object
    //   1338: astore 4
    //   1340: iload 7
    //   1342: iconst_1
    //   1343: isub
    //   1344: istore 7
    //   1346: iload 7
    //   1348: ifge +11 -> 1359
    //   1351: aload_1
    //   1352: aload_3
    //   1353: aload 4
    //   1355: invokestatic 557	gnu/kawa/slib/srfi1:every$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1358: areturn
    //   1359: aload 4
    //   1361: iload 7
    //   1363: aload_2
    //   1364: iload 7
    //   1366: iconst_2
    //   1367: iadd
    //   1368: aaload
    //   1369: aastore
    //   1370: goto -30 -> 1340
    //   1373: aload_2
    //   1374: iconst_0
    //   1375: aaload
    //   1376: astore_3
    //   1377: aload_3
    //   1378: checkcast 1248	gnu/mapping/Procedure
    //   1381: astore_1
    //   1382: aload_2
    //   1383: iconst_1
    //   1384: aaload
    //   1385: astore_3
    //   1386: aload_2
    //   1387: arraylength
    //   1388: iconst_2
    //   1389: isub
    //   1390: istore 7
    //   1392: iload 7
    //   1394: anewarray 553	java/lang/Object
    //   1397: astore 4
    //   1399: iload 7
    //   1401: iconst_1
    //   1402: isub
    //   1403: istore 7
    //   1405: iload 7
    //   1407: ifge +11 -> 1418
    //   1410: aload_1
    //   1411: aload_3
    //   1412: aload 4
    //   1414: invokestatic 1961	gnu/kawa/slib/srfi1:listIndex$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1417: areturn
    //   1418: aload 4
    //   1420: iload 7
    //   1422: aload_2
    //   1423: iload 7
    //   1425: iconst_2
    //   1426: iadd
    //   1427: aaload
    //   1428: aastore
    //   1429: goto -30 -> 1399
    //   1432: aload_2
    //   1433: iconst_0
    //   1434: aaload
    //   1435: astore_1
    //   1436: aload_1
    //   1437: checkcast 1248	gnu/mapping/Procedure
    //   1440: astore_3
    //   1441: aload_2
    //   1442: arraylength
    //   1443: iconst_1
    //   1444: isub
    //   1445: istore 7
    //   1447: iload 7
    //   1449: anewarray 553	java/lang/Object
    //   1452: astore_1
    //   1453: iload 7
    //   1455: iconst_1
    //   1456: isub
    //   1457: istore 7
    //   1459: iload 7
    //   1461: ifge +9 -> 1470
    //   1464: aload_3
    //   1465: aload_1
    //   1466: invokestatic 1963	gnu/kawa/slib/srfi1:lset$Ls$Eq$V	(Lgnu/mapping/Procedure;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1469: areturn
    //   1470: aload_1
    //   1471: iload 7
    //   1473: aload_2
    //   1474: iload 7
    //   1476: iconst_1
    //   1477: iadd
    //   1478: aaload
    //   1479: aastore
    //   1480: goto -27 -> 1453
    //   1483: aload_2
    //   1484: iconst_0
    //   1485: aaload
    //   1486: astore_1
    //   1487: aload_1
    //   1488: checkcast 1248	gnu/mapping/Procedure
    //   1491: astore_3
    //   1492: aload_2
    //   1493: arraylength
    //   1494: iconst_1
    //   1495: isub
    //   1496: istore 7
    //   1498: iload 7
    //   1500: anewarray 553	java/lang/Object
    //   1503: astore_1
    //   1504: iload 7
    //   1506: iconst_1
    //   1507: isub
    //   1508: istore 7
    //   1510: iload 7
    //   1512: ifge +9 -> 1521
    //   1515: aload_3
    //   1516: aload_1
    //   1517: invokestatic 1965	gnu/kawa/slib/srfi1:lset$Eq$V	(Lgnu/mapping/Procedure;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1520: areturn
    //   1521: aload_1
    //   1522: iload 7
    //   1524: aload_2
    //   1525: iload 7
    //   1527: iconst_1
    //   1528: iadd
    //   1529: aaload
    //   1530: aastore
    //   1531: goto -27 -> 1504
    //   1534: aload_2
    //   1535: iconst_0
    //   1536: aaload
    //   1537: astore_3
    //   1538: aload_3
    //   1539: checkcast 1248	gnu/mapping/Procedure
    //   1542: astore_1
    //   1543: aload_2
    //   1544: iconst_1
    //   1545: aaload
    //   1546: astore_3
    //   1547: aload_2
    //   1548: arraylength
    //   1549: iconst_2
    //   1550: isub
    //   1551: istore 7
    //   1553: iload 7
    //   1555: anewarray 553	java/lang/Object
    //   1558: astore 4
    //   1560: iload 7
    //   1562: iconst_1
    //   1563: isub
    //   1564: istore 7
    //   1566: iload 7
    //   1568: ifge +11 -> 1579
    //   1571: aload_1
    //   1572: aload_3
    //   1573: aload 4
    //   1575: invokestatic 1967	gnu/kawa/slib/srfi1:lsetAdjoin$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1578: areturn
    //   1579: aload 4
    //   1581: iload 7
    //   1583: aload_2
    //   1584: iload 7
    //   1586: iconst_2
    //   1587: iadd
    //   1588: aaload
    //   1589: aastore
    //   1590: goto -30 -> 1560
    //   1593: aload_2
    //   1594: iconst_0
    //   1595: aaload
    //   1596: astore_1
    //   1597: aload_1
    //   1598: checkcast 1248	gnu/mapping/Procedure
    //   1601: astore_3
    //   1602: aload_2
    //   1603: arraylength
    //   1604: iconst_1
    //   1605: isub
    //   1606: istore 7
    //   1608: iload 7
    //   1610: anewarray 553	java/lang/Object
    //   1613: astore_1
    //   1614: iload 7
    //   1616: iconst_1
    //   1617: isub
    //   1618: istore 7
    //   1620: iload 7
    //   1622: ifge +9 -> 1631
    //   1625: aload_3
    //   1626: aload_1
    //   1627: invokestatic 1969	gnu/kawa/slib/srfi1:lsetUnion$V	(Lgnu/mapping/Procedure;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1630: areturn
    //   1631: aload_1
    //   1632: iload 7
    //   1634: aload_2
    //   1635: iload 7
    //   1637: iconst_1
    //   1638: iadd
    //   1639: aaload
    //   1640: aastore
    //   1641: goto -27 -> 1614
    //   1644: aload_2
    //   1645: iconst_0
    //   1646: aaload
    //   1647: astore_1
    //   1648: aload_1
    //   1649: checkcast 1248	gnu/mapping/Procedure
    //   1652: astore_3
    //   1653: aload_2
    //   1654: arraylength
    //   1655: iconst_1
    //   1656: isub
    //   1657: istore 7
    //   1659: iload 7
    //   1661: anewarray 553	java/lang/Object
    //   1664: astore_1
    //   1665: iload 7
    //   1667: iconst_1
    //   1668: isub
    //   1669: istore 7
    //   1671: iload 7
    //   1673: ifge +9 -> 1682
    //   1676: aload_3
    //   1677: aload_1
    //   1678: invokestatic 1971	gnu/kawa/slib/srfi1:lsetUnion$Ex$V	(Lgnu/mapping/Procedure;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1681: areturn
    //   1682: aload_1
    //   1683: iload 7
    //   1685: aload_2
    //   1686: iload 7
    //   1688: iconst_1
    //   1689: iadd
    //   1690: aaload
    //   1691: aastore
    //   1692: goto -27 -> 1665
    //   1695: aload_2
    //   1696: iconst_0
    //   1697: aaload
    //   1698: astore_3
    //   1699: aload_3
    //   1700: checkcast 1248	gnu/mapping/Procedure
    //   1703: astore_1
    //   1704: aload_2
    //   1705: iconst_1
    //   1706: aaload
    //   1707: astore_3
    //   1708: aload_2
    //   1709: arraylength
    //   1710: iconst_2
    //   1711: isub
    //   1712: istore 7
    //   1714: iload 7
    //   1716: anewarray 553	java/lang/Object
    //   1719: astore 4
    //   1721: iload 7
    //   1723: iconst_1
    //   1724: isub
    //   1725: istore 7
    //   1727: iload 7
    //   1729: ifge +11 -> 1740
    //   1732: aload_1
    //   1733: aload_3
    //   1734: aload 4
    //   1736: invokestatic 1973	gnu/kawa/slib/srfi1:lsetIntersection$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1739: areturn
    //   1740: aload 4
    //   1742: iload 7
    //   1744: aload_2
    //   1745: iload 7
    //   1747: iconst_2
    //   1748: iadd
    //   1749: aaload
    //   1750: aastore
    //   1751: goto -30 -> 1721
    //   1754: aload_2
    //   1755: iconst_0
    //   1756: aaload
    //   1757: astore_3
    //   1758: aload_3
    //   1759: checkcast 1248	gnu/mapping/Procedure
    //   1762: astore_1
    //   1763: aload_2
    //   1764: iconst_1
    //   1765: aaload
    //   1766: astore_3
    //   1767: aload_2
    //   1768: arraylength
    //   1769: iconst_2
    //   1770: isub
    //   1771: istore 7
    //   1773: iload 7
    //   1775: anewarray 553	java/lang/Object
    //   1778: astore 4
    //   1780: iload 7
    //   1782: iconst_1
    //   1783: isub
    //   1784: istore 7
    //   1786: iload 7
    //   1788: ifge +11 -> 1799
    //   1791: aload_1
    //   1792: aload_3
    //   1793: aload 4
    //   1795: invokestatic 1975	gnu/kawa/slib/srfi1:lsetIntersection$Ex$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1798: areturn
    //   1799: aload 4
    //   1801: iload 7
    //   1803: aload_2
    //   1804: iload 7
    //   1806: iconst_2
    //   1807: iadd
    //   1808: aaload
    //   1809: aastore
    //   1810: goto -30 -> 1780
    //   1813: aload_2
    //   1814: iconst_0
    //   1815: aaload
    //   1816: astore_3
    //   1817: aload_3
    //   1818: checkcast 1248	gnu/mapping/Procedure
    //   1821: astore_1
    //   1822: aload_2
    //   1823: iconst_1
    //   1824: aaload
    //   1825: astore_3
    //   1826: aload_2
    //   1827: arraylength
    //   1828: iconst_2
    //   1829: isub
    //   1830: istore 7
    //   1832: iload 7
    //   1834: anewarray 553	java/lang/Object
    //   1837: astore 4
    //   1839: iload 7
    //   1841: iconst_1
    //   1842: isub
    //   1843: istore 7
    //   1845: iload 7
    //   1847: ifge +11 -> 1858
    //   1850: aload_1
    //   1851: aload_3
    //   1852: aload 4
    //   1854: invokestatic 1977	gnu/kawa/slib/srfi1:lsetDifference$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1857: areturn
    //   1858: aload 4
    //   1860: iload 7
    //   1862: aload_2
    //   1863: iload 7
    //   1865: iconst_2
    //   1866: iadd
    //   1867: aaload
    //   1868: aastore
    //   1869: goto -30 -> 1839
    //   1872: aload_2
    //   1873: iconst_0
    //   1874: aaload
    //   1875: astore_3
    //   1876: aload_3
    //   1877: checkcast 1248	gnu/mapping/Procedure
    //   1880: astore_1
    //   1881: aload_2
    //   1882: iconst_1
    //   1883: aaload
    //   1884: astore_3
    //   1885: aload_2
    //   1886: arraylength
    //   1887: iconst_2
    //   1888: isub
    //   1889: istore 7
    //   1891: iload 7
    //   1893: anewarray 553	java/lang/Object
    //   1896: astore 4
    //   1898: iload 7
    //   1900: iconst_1
    //   1901: isub
    //   1902: istore 7
    //   1904: iload 7
    //   1906: ifge +11 -> 1917
    //   1909: aload_1
    //   1910: aload_3
    //   1911: aload 4
    //   1913: invokestatic 1979	gnu/kawa/slib/srfi1:lsetDifference$Ex$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1916: areturn
    //   1917: aload 4
    //   1919: iload 7
    //   1921: aload_2
    //   1922: iload 7
    //   1924: iconst_2
    //   1925: iadd
    //   1926: aaload
    //   1927: aastore
    //   1928: goto -30 -> 1898
    //   1931: aload_2
    //   1932: iconst_0
    //   1933: aaload
    //   1934: astore_1
    //   1935: aload_1
    //   1936: checkcast 1248	gnu/mapping/Procedure
    //   1939: astore_3
    //   1940: aload_2
    //   1941: arraylength
    //   1942: iconst_1
    //   1943: isub
    //   1944: istore 7
    //   1946: iload 7
    //   1948: anewarray 553	java/lang/Object
    //   1951: astore_1
    //   1952: iload 7
    //   1954: iconst_1
    //   1955: isub
    //   1956: istore 7
    //   1958: iload 7
    //   1960: ifge +9 -> 1969
    //   1963: aload_3
    //   1964: aload_1
    //   1965: invokestatic 1981	gnu/kawa/slib/srfi1:lsetXor$V	(Lgnu/mapping/Procedure;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1968: areturn
    //   1969: aload_1
    //   1970: iload 7
    //   1972: aload_2
    //   1973: iload 7
    //   1975: iconst_1
    //   1976: iadd
    //   1977: aaload
    //   1978: aastore
    //   1979: goto -27 -> 1952
    //   1982: aload_2
    //   1983: iconst_0
    //   1984: aaload
    //   1985: astore_1
    //   1986: aload_1
    //   1987: checkcast 1248	gnu/mapping/Procedure
    //   1990: astore_3
    //   1991: aload_2
    //   1992: arraylength
    //   1993: iconst_1
    //   1994: isub
    //   1995: istore 7
    //   1997: iload 7
    //   1999: anewarray 553	java/lang/Object
    //   2002: astore_1
    //   2003: iload 7
    //   2005: iconst_1
    //   2006: isub
    //   2007: istore 7
    //   2009: iload 7
    //   2011: ifge +9 -> 2020
    //   2014: aload_3
    //   2015: aload_1
    //   2016: invokestatic 1983	gnu/kawa/slib/srfi1:lsetXor$Ex$V	(Lgnu/mapping/Procedure;[Ljava/lang/Object;)Ljava/lang/Object;
    //   2019: areturn
    //   2020: aload_1
    //   2021: iload 7
    //   2023: aload_2
    //   2024: iload 7
    //   2026: iconst_1
    //   2027: iadd
    //   2028: aaload
    //   2029: aastore
    //   2030: goto -27 -> 2003
    //   2033: aload_2
    //   2034: iconst_0
    //   2035: aaload
    //   2036: astore_3
    //   2037: aload_3
    //   2038: checkcast 1248	gnu/mapping/Procedure
    //   2041: astore_1
    //   2042: aload_2
    //   2043: iconst_1
    //   2044: aaload
    //   2045: astore_3
    //   2046: aload_2
    //   2047: arraylength
    //   2048: iconst_2
    //   2049: isub
    //   2050: istore 7
    //   2052: iload 7
    //   2054: anewarray 553	java/lang/Object
    //   2057: astore 4
    //   2059: iload 7
    //   2061: iconst_1
    //   2062: isub
    //   2063: istore 7
    //   2065: iload 7
    //   2067: ifge +11 -> 2078
    //   2070: aload_1
    //   2071: aload_3
    //   2072: aload 4
    //   2074: invokestatic 1985	gnu/kawa/slib/srfi1:lsetDiff$PlIntersection$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   2077: areturn
    //   2078: aload 4
    //   2080: iload 7
    //   2082: aload_2
    //   2083: iload 7
    //   2085: iconst_2
    //   2086: iadd
    //   2087: aaload
    //   2088: aastore
    //   2089: goto -30 -> 2059
    //   2092: aload_2
    //   2093: iconst_0
    //   2094: aaload
    //   2095: astore_3
    //   2096: aload_3
    //   2097: checkcast 1248	gnu/mapping/Procedure
    //   2100: astore_1
    //   2101: aload_2
    //   2102: iconst_1
    //   2103: aaload
    //   2104: astore_3
    //   2105: aload_2
    //   2106: arraylength
    //   2107: iconst_2
    //   2108: isub
    //   2109: istore 7
    //   2111: iload 7
    //   2113: anewarray 553	java/lang/Object
    //   2116: astore 4
    //   2118: iload 7
    //   2120: iconst_1
    //   2121: isub
    //   2122: istore 7
    //   2124: iload 7
    //   2126: ifge +11 -> 2137
    //   2129: aload_1
    //   2130: aload_3
    //   2131: aload 4
    //   2133: invokestatic 1987	gnu/kawa/slib/srfi1:lsetDiff$PlIntersection$Ex$V	(Lgnu/mapping/Procedure;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   2136: areturn
    //   2137: aload 4
    //   2139: iload 7
    //   2141: aload_2
    //   2142: iload 7
    //   2144: iconst_2
    //   2145: iadd
    //   2146: aaload
    //   2147: aastore
    //   2148: goto -30 -> 2118
    //   2151: astore_1
    //   2152: new 1257	gnu/mapping/WrongType
    //   2155: dup
    //   2156: aload_1
    //   2157: ldc_w 841
    //   2160: iconst_1
    //   2161: aload_3
    //   2162: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2165: athrow
    //   2166: astore_1
    //   2167: new 1257	gnu/mapping/WrongType
    //   2170: dup
    //   2171: aload_1
    //   2172: ldc_w 838
    //   2175: iconst_1
    //   2176: aload_3
    //   2177: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2180: athrow
    //   2181: astore_1
    //   2182: new 1257	gnu/mapping/WrongType
    //   2185: dup
    //   2186: aload_1
    //   2187: ldc_w 838
    //   2190: iconst_2
    //   2191: aload 4
    //   2193: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2196: athrow
    //   2197: astore_1
    //   2198: new 1257	gnu/mapping/WrongType
    //   2201: dup
    //   2202: aload_1
    //   2203: ldc_w 838
    //   2206: iconst_3
    //   2207: aload 4
    //   2209: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2212: athrow
    //   2213: astore_1
    //   2214: new 1257	gnu/mapping/WrongType
    //   2217: dup
    //   2218: aload_1
    //   2219: ldc_w 834
    //   2222: iconst_1
    //   2223: aload_3
    //   2224: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2227: athrow
    //   2228: astore_1
    //   2229: new 1257	gnu/mapping/WrongType
    //   2232: dup
    //   2233: aload_1
    //   2234: ldc_w 834
    //   2237: iconst_2
    //   2238: aload 4
    //   2240: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2243: athrow
    //   2244: astore_1
    //   2245: new 1257	gnu/mapping/WrongType
    //   2248: dup
    //   2249: aload_1
    //   2250: ldc_w 834
    //   2253: iconst_3
    //   2254: aload 5
    //   2256: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2259: athrow
    //   2260: astore_1
    //   2261: new 1257	gnu/mapping/WrongType
    //   2264: dup
    //   2265: aload_1
    //   2266: ldc_w 831
    //   2269: iconst_1
    //   2270: aload_3
    //   2271: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2274: athrow
    //   2275: astore_1
    //   2276: new 1257	gnu/mapping/WrongType
    //   2279: dup
    //   2280: aload_1
    //   2281: ldc_w 828
    //   2284: iconst_1
    //   2285: aload_3
    //   2286: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2289: athrow
    //   2290: astore_1
    //   2291: new 1257	gnu/mapping/WrongType
    //   2294: dup
    //   2295: aload_1
    //   2296: ldc_w 824
    //   2299: iconst_1
    //   2300: aload_3
    //   2301: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2304: athrow
    //   2305: astore_1
    //   2306: new 1257	gnu/mapping/WrongType
    //   2309: dup
    //   2310: aload_1
    //   2311: ldc_w 820
    //   2314: iconst_1
    //   2315: aload_3
    //   2316: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2319: athrow
    //   2320: astore_1
    //   2321: new 1257	gnu/mapping/WrongType
    //   2324: dup
    //   2325: aload_1
    //   2326: ldc_w 801
    //   2329: iconst_1
    //   2330: aload_3
    //   2331: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2334: athrow
    //   2335: astore_1
    //   2336: new 1257	gnu/mapping/WrongType
    //   2339: dup
    //   2340: aload_1
    //   2341: ldc_w 797
    //   2344: iconst_1
    //   2345: aload_3
    //   2346: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2349: athrow
    //   2350: astore_1
    //   2351: new 1257	gnu/mapping/WrongType
    //   2354: dup
    //   2355: aload_1
    //   2356: ldc_w 793
    //   2359: iconst_1
    //   2360: aload_3
    //   2361: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2364: athrow
    //   2365: astore_1
    //   2366: new 1257	gnu/mapping/WrongType
    //   2369: dup
    //   2370: aload_1
    //   2371: ldc_w 704
    //   2374: iconst_1
    //   2375: aload_3
    //   2376: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2379: athrow
    //   2380: astore_1
    //   2381: new 1257	gnu/mapping/WrongType
    //   2384: dup
    //   2385: aload_1
    //   2386: ldc_w 701
    //   2389: iconst_1
    //   2390: aload_3
    //   2391: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2394: athrow
    //   2395: astore_1
    //   2396: new 1257	gnu/mapping/WrongType
    //   2399: dup
    //   2400: aload_1
    //   2401: ldc_w 641
    //   2404: iconst_1
    //   2405: aload_3
    //   2406: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2409: athrow
    //   2410: astore_2
    //   2411: new 1257	gnu/mapping/WrongType
    //   2414: dup
    //   2415: aload_2
    //   2416: ldc_w 637
    //   2419: iconst_1
    //   2420: aload_1
    //   2421: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2424: athrow
    //   2425: astore_2
    //   2426: new 1257	gnu/mapping/WrongType
    //   2429: dup
    //   2430: aload_2
    //   2431: ldc_w 633
    //   2434: iconst_1
    //   2435: aload_1
    //   2436: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2439: athrow
    //   2440: astore_1
    //   2441: new 1257	gnu/mapping/WrongType
    //   2444: dup
    //   2445: aload_1
    //   2446: ldc_w 629
    //   2449: iconst_1
    //   2450: aload_3
    //   2451: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2454: athrow
    //   2455: astore_2
    //   2456: new 1257	gnu/mapping/WrongType
    //   2459: dup
    //   2460: aload_2
    //   2461: ldc_w 625
    //   2464: iconst_1
    //   2465: aload_1
    //   2466: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2469: athrow
    //   2470: astore_2
    //   2471: new 1257	gnu/mapping/WrongType
    //   2474: dup
    //   2475: aload_2
    //   2476: ldc_w 621
    //   2479: iconst_1
    //   2480: aload_1
    //   2481: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2484: athrow
    //   2485: astore_1
    //   2486: new 1257	gnu/mapping/WrongType
    //   2489: dup
    //   2490: aload_1
    //   2491: ldc_w 617
    //   2494: iconst_1
    //   2495: aload_3
    //   2496: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2499: athrow
    //   2500: astore_1
    //   2501: new 1257	gnu/mapping/WrongType
    //   2504: dup
    //   2505: aload_1
    //   2506: ldc_w 613
    //   2509: iconst_1
    //   2510: aload_3
    //   2511: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2514: athrow
    //   2515: astore_1
    //   2516: new 1257	gnu/mapping/WrongType
    //   2519: dup
    //   2520: aload_1
    //   2521: ldc_w 609
    //   2524: iconst_1
    //   2525: aload_3
    //   2526: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2529: athrow
    //   2530: astore_1
    //   2531: new 1257	gnu/mapping/WrongType
    //   2534: dup
    //   2535: aload_1
    //   2536: ldc_w 605
    //   2539: iconst_1
    //   2540: aload_3
    //   2541: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2544: athrow
    //   2545: astore_2
    //   2546: new 1257	gnu/mapping/WrongType
    //   2549: dup
    //   2550: aload_2
    //   2551: ldc_w 601
    //   2554: iconst_1
    //   2555: aload_1
    //   2556: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2559: athrow
    //   2560: astore_2
    //   2561: new 1257	gnu/mapping/WrongType
    //   2564: dup
    //   2565: aload_2
    //   2566: ldc_w 597
    //   2569: iconst_1
    //   2570: aload_1
    //   2571: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2574: athrow
    //   2575: astore_1
    //   2576: new 1257	gnu/mapping/WrongType
    //   2579: dup
    //   2580: aload_1
    //   2581: ldc_w 593
    //   2584: iconst_1
    //   2585: aload_3
    //   2586: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2589: athrow
    //   2590: astore_1
    //   2591: new 1257	gnu/mapping/WrongType
    //   2594: dup
    //   2595: aload_1
    //   2596: ldc_w 589
    //   2599: iconst_1
    //   2600: aload_3
    //   2601: invokespecial 1262	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   2604: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2605	0	this	srfi1
    //   0	2605	1	paramModuleMethod	ModuleMethod
    //   0	2605	2	paramArrayOfObject	Object[]
    //   310	2291	3	localObject1	Object
    //   513	1726	4	localObject2	Object
    //   584	1671	5	localObject3	Object
    //   667	26	6	arrayOfObject	Object[]
    //   303	1843	7	i	int
    // Exception table:
    //   from	to	target	type
    //   493	498	2151	java/lang/ClassCastException
    //   558	563	2166	java/lang/ClassCastException
    //   568	574	2181	java/lang/ClassCastException
    //   579	586	2197	java/lang/ClassCastException
    //   623	628	2213	java/lang/ClassCastException
    //   633	639	2228	java/lang/ClassCastException
    //   644	651	2244	java/lang/ClassCastException
    //   710	715	2260	java/lang/ClassCastException
    //   776	781	2275	java/lang/ClassCastException
    //   842	847	2290	java/lang/ClassCastException
    //   908	913	2305	java/lang/ClassCastException
    //   1082	1087	2320	java/lang/ClassCastException
    //   1141	1146	2335	java/lang/ClassCastException
    //   1200	1205	2350	java/lang/ClassCastException
    //   1259	1264	2365	java/lang/ClassCastException
    //   1318	1323	2380	java/lang/ClassCastException
    //   1377	1382	2395	java/lang/ClassCastException
    //   1436	1441	2410	java/lang/ClassCastException
    //   1487	1492	2425	java/lang/ClassCastException
    //   1538	1543	2440	java/lang/ClassCastException
    //   1597	1602	2455	java/lang/ClassCastException
    //   1648	1653	2470	java/lang/ClassCastException
    //   1699	1704	2485	java/lang/ClassCastException
    //   1758	1763	2500	java/lang/ClassCastException
    //   1817	1822	2515	java/lang/ClassCastException
    //   1876	1881	2530	java/lang/ClassCastException
    //   1935	1940	2545	java/lang/ClassCastException
    //   1986	1991	2560	java/lang/ClassCastException
    //   2037	2042	2575	java/lang/ClassCastException
    //   2096	2101	2590	java/lang/ClassCastException
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 152: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 149: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 147: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 121: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 120: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 116: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 115: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 114: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 113: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 112: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 111: 
      if (!(paramObject instanceof Pair)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 110: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 101: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 100: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 99: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 98: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 97: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 96: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 95: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 93: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 91: 
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
    case 87: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 83: 
      if (IntNum.asIntNumOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    }
    if ((paramObject instanceof LList))
    {
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    }
    return -786431;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    case 183: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 182: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 165: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 164: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 163: 
      if (!(paramObject1 instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 162: 
      if (!(paramObject1 instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 161: 
      if (!(paramObject1 instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 160: 
      if (!(paramObject1 instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 159: 
      if (!(paramObject1 instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 158: 
      if (!(paramObject1 instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 157: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 155: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 153: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 149: 
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Procedure)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 147: 
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Procedure)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 145: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 143: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 142: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 141: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 140: 
      if (!(paramObject1 instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 139: 
      if (!(paramObject1 instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 138: 
      if (!(paramObject1 instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 137: 
      if (!(paramObject1 instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 119: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 118: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 109: 
      paramCallContext.value1 = paramObject1;
      if (IntNum.asIntNumOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
    case 108: 
      paramCallContext.value1 = paramObject1;
      if (IntNum.asIntNumOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
    case 107: 
      paramCallContext.value1 = paramObject1;
      if (IntNum.asIntNumOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
    case 106: 
      paramCallContext.value1 = paramObject1;
      if (IntNum.asIntNumOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
    case 105: 
      paramCallContext.value1 = paramObject1;
      if (IntNum.asIntNumOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
    case 104: 
      paramCallContext.value1 = paramObject1;
      if (IntNum.asIntNumOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
    case 103: 
      paramCallContext.value1 = paramObject1;
      if (IntNum.asIntNumOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
    case 102: 
      paramCallContext.value1 = paramObject1;
      if (IntNum.asIntNumOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
    case 83: 
      if (IntNum.asIntNumOrNull(paramObject1) != null)
      {
        paramCallContext.value1 = paramObject1;
        if (Numeric.asNumericOrNull(paramObject2) != null)
        {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        }
      }
      else
      {
        return -786431;
      }
      return -786430;
    case 80: 
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Procedure)) {
        return -786430;
      }
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
    default: 
      return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
    case 155: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 153: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 151: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 145: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 143: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 131: 
      if (!(paramObject1 instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 130: 
      if (!(paramObject1 instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    }
    if (IntNum.asIntNumOrNull(paramObject1) != null)
    {
      paramCallContext.value1 = paramObject1;
      if (Numeric.asNumericOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        if (Numeric.asNumericOrNull(paramObject3) == null) {
          break label408;
        }
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      }
    }
    else
    {
      return -786431;
    }
    return -786430;
    label408:
    return -786429;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 123)
    {
      if (!(paramObject1 instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Procedure)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      if (!(paramObject3 instanceof Procedure)) {
        return -786429;
      }
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
    case 181: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 180: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 179: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 178: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 177: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 176: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 175: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 174: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 173: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 172: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 171: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 170: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 169: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 168: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 167: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 166: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 136: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 135: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 134: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 133: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 132: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 129: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 128: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 127: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 126: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 125: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 123: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 122: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 117: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 94: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 92: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 86: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 81: 
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
    first = lists.car;
    second = lists.cadr;
    third = lists.caddr;
    fourth = lists.cadddr;
    map$Mnin$Mnorder = Scheme.map;
  }
  
  public class frame
    extends ModuleBody
  {
    public static Object lambda2recur(Object paramObject)
    {
      srfi1.frame0 localframe0 = new srfi1.frame0();
      localframe0.lis = paramObject;
      if (srfi1.isNullList(localframe0.lis) != Boolean.FALSE) {
        return misc.values(new Object[] { localframe0.lis, localframe0.lis });
      }
      localframe0.elt = lists.car.apply1(localframe0.lis);
      return call_with_values.callWithValues(localframe0.lambda$Fn1, localframe0.lambda$Fn2);
    }
  }
  
  public class frame0
    extends ModuleBody
  {
    Object elt;
    final ModuleMethod lambda$Fn1 = new ModuleMethod(this, 1, null, 0);
    final ModuleMethod lambda$Fn2;
    Object lis;
    
    public frame0()
    {
      this$1 = new ModuleMethod(this, 2, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:627");
      this.lambda$Fn2 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 1) {
        return lambda3();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 2) {
        return lambda4(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda3()
    {
      return srfi1.frame.lambda2recur(lists.cdr.apply1(this.lis));
    }
    
    Object lambda4(Object paramObject1, Object paramObject2)
    {
      return misc.values(new Object[] { lists.cons(lists.car.apply1(this.elt), paramObject1), lists.cons(lists.cadr.apply1(this.elt), paramObject2) });
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
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 2)
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
    public static Object lambda5recur(Object paramObject)
    {
      srfi1.frame2 localframe2 = new srfi1.frame2();
      localframe2.lis = paramObject;
      if (srfi1.isNullList(localframe2.lis) != Boolean.FALSE) {
        return misc.values(new Object[] { localframe2.lis, localframe2.lis, localframe2.lis });
      }
      localframe2.elt = lists.car.apply1(localframe2.lis);
      return call_with_values.callWithValues(localframe2.lambda$Fn3, localframe2.lambda$Fn4);
    }
  }
  
  public class frame10
    extends ModuleBody
  {
    Procedure f;
    Object zero;
    
    public Object lambda19recur(Object paramObject)
    {
      Object localObject = srfi1.$PcCdrs(paramObject);
      if (lists.isNull(localObject)) {
        return this.zero;
      }
      return Scheme.apply.apply2(this.f, srfi1.append$Ex$V(new Object[] { paramObject, LList.list1(lambda19recur(localObject)) }));
    }
    
    public Object lambda20recur(Object paramObject)
    {
      if (srfi1.isNullList(paramObject) != Boolean.FALSE) {
        return this.zero;
      }
      return this.f.apply2(paramObject, lambda20recur(lists.cdr.apply1(paramObject)));
    }
  }
  
  public class frame11
    extends ModuleBody
  {
    Procedure f;
    
    public Object lambda21recur(Object paramObject1, Object paramObject2)
    {
      Object localObject = paramObject1;
      if (lists.isPair(paramObject2)) {
        localObject = this.f.apply2(paramObject1, lambda21recur(lists.car.apply1(paramObject2), lists.cdr.apply1(paramObject2)));
      }
      return localObject;
    }
  }
  
  public class frame12
    extends ModuleBody
  {
    Procedure f;
    final ModuleMethod lambda$Fn11;
    
    public frame12()
    {
      this$1 = new ModuleMethod(this, 11, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:961");
      this.lambda$Fn11 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 11)
      {
        lambda22(paramObject);
        return Values.empty;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    void lambda22(Object paramObject)
    {
      try
      {
        Pair localPair = (Pair)paramObject;
        lists.setCar$Ex(localPair, this.f.apply1(lists.car.apply1(paramObject)));
        return;
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "set-car!", 1, paramObject);
      }
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
  
  public class frame13
    extends ModuleBody
  {
    Procedure f;
    
    public Object lambda23recur(Object paramObject1, Object paramObject2)
    {
      srfi1.frame14 localframe14 = new srfi1.frame14();
      localframe14.staticLink = this;
      localframe14.lists = paramObject1;
      localframe14.res = paramObject2;
      return call_with_values.callWithValues(localframe14.lambda$Fn12, localframe14.lambda$Fn13);
    }
  }
  
  public class frame14
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 12, null, 0);
    final ModuleMethod lambda$Fn13;
    Object lists;
    Object res;
    srfi1.frame13 staticLink;
    
    public frame14()
    {
      this$1 = new ModuleMethod(this, 13, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:969");
      this.lambda$Fn13 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 12) {
        return lambda24();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 13) {
        return lambda25(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda24()
    {
      return srfi1.$PcCars$PlCdrs(this.lists);
    }
    
    Object lambda25(Object paramObject1, Object paramObject2)
    {
      if (srfi1.isNotPair(paramObject1)) {
        paramObject1 = this.res;
      }
      try
      {
        paramObject2 = (LList)paramObject1;
        return lists.reverse$Ex((LList)paramObject2);
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "reverse!", 1, paramObject1);
      }
      paramObject1 = Scheme.apply.apply2(this.staticLink.f, paramObject1);
      if (paramObject1 != Boolean.FALSE) {
        return this.staticLink.lambda23recur(paramObject2, lists.cons(paramObject1, this.res));
      }
      return this.staticLink.lambda23recur(paramObject2, this.res);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 12)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 13)
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
  
  public class frame15
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn14;
    Object pred;
    
    public frame15()
    {
      this$1 = new ModuleMethod(this, 14, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1199");
      this.lambda$Fn14 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 14)
      {
        if (lambda26(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda26(Object paramObject)
    {
      if (Scheme.applyToArgs.apply2(this.pred, paramObject) != Boolean.FALSE) {}
      for (int i = 1;; i = 0) {
        return i + 1 & 0x1;
      }
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
  
  public class frame16
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn15;
    Object pred;
    
    public frame16()
    {
      this$1 = new ModuleMethod(this, 15, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1200");
      this.lambda$Fn15 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 15)
      {
        if (lambda27(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda27(Object paramObject)
    {
      if (Scheme.applyToArgs.apply2(this.pred, paramObject) != Boolean.FALSE) {}
      for (int i = 1;; i = 0) {
        return i + 1 & 0x1;
      }
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
  
  public class frame17
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn16;
    Object maybe$Mn$Eq;
    Object x;
    
    public frame17()
    {
      this$1 = new ModuleMethod(this, 16, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1222");
      this.lambda$Fn16 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 16)
      {
        if (lambda28(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda28(Object paramObject)
    {
      if (Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.x, paramObject) != Boolean.FALSE) {}
      for (int i = 1;; i = 0) {
        return i + 1 & 0x1;
      }
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
  
  public class frame18
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn17;
    Object maybe$Mn$Eq;
    Object x;
    
    public frame18()
    {
      this$1 = new ModuleMethod(this, 17, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1225");
      this.lambda$Fn17 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 17)
      {
        if (lambda29(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda29(Object paramObject)
    {
      if (Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.x, paramObject) != Boolean.FALSE) {}
      for (int i = 1;; i = 0) {
        return i + 1 & 0x1;
      }
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 17)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame19
    extends ModuleBody
  {
    Procedure maybe$Mn$Eq;
    
    public Object lambda30recur(Object paramObject)
    {
      if (srfi1.isNullList(paramObject) != Boolean.FALSE) {}
      Object localObject1;
      Object localObject2;
      Object localObject3;
      do
      {
        return paramObject;
        localObject1 = lists.car.apply1(paramObject);
        localObject2 = lists.cdr.apply1(paramObject);
        localObject3 = lambda30recur(srfi1.delete(localObject1, localObject2, this.maybe$Mn$Eq));
      } while (localObject2 == localObject3);
      return lists.cons(localObject1, localObject3);
    }
  }
  
  public class frame2
    extends ModuleBody
  {
    Object elt;
    final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, null, 0);
    final ModuleMethod lambda$Fn4;
    Object lis;
    
    public frame2()
    {
      this$1 = new ModuleMethod(this, 4, null, 12291);
      this$1.setProperty("source-location", "srfi1.scm:635");
      this.lambda$Fn4 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 3) {
        return lambda6();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
    {
      if (paramModuleMethod.selector == 4) {
        return lambda7(paramObject1, paramObject2, paramObject3);
      }
      return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
    }
    
    Object lambda6()
    {
      return srfi1.frame1.lambda5recur(lists.cdr.apply1(this.lis));
    }
    
    Object lambda7(Object paramObject1, Object paramObject2, Object paramObject3)
    {
      return misc.values(new Object[] { lists.cons(lists.car.apply1(this.elt), paramObject1), lists.cons(lists.cadr.apply1(this.elt), paramObject2), lists.cons(lists.caddr.apply1(this.elt), paramObject3) });
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 3)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 4)
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
  
  public class frame20
    extends ModuleBody
  {
    Procedure maybe$Mn$Eq;
    
    public Object lambda31recur(Object paramObject)
    {
      if (srfi1.isNullList(paramObject) != Boolean.FALSE) {}
      Object localObject1;
      Object localObject2;
      Object localObject3;
      do
      {
        return paramObject;
        localObject1 = lists.car.apply1(paramObject);
        localObject2 = lists.cdr.apply1(paramObject);
        localObject3 = lambda31recur(srfi1.delete$Ex(localObject1, localObject2, this.maybe$Mn$Eq));
      } while (localObject2 == localObject3);
      return lists.cons(localObject1, localObject3);
    }
  }
  
  public class frame21
    extends ModuleBody
  {
    Object key;
    final ModuleMethod lambda$Fn18;
    Object maybe$Mn$Eq;
    
    public frame21()
    {
      this$1 = new ModuleMethod(this, 18, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1280");
      this.lambda$Fn18 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 18)
      {
        if (lambda32(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda32(Object paramObject)
    {
      if (Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.key, lists.car.apply1(paramObject)) != Boolean.FALSE) {}
      for (int i = 1;; i = 0) {
        return i + 1 & 0x1;
      }
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
  }
  
  public class frame22
    extends ModuleBody
  {
    Object key;
    final ModuleMethod lambda$Fn19;
    Object maybe$Mn$Eq;
    
    public frame22()
    {
      this$1 = new ModuleMethod(this, 19, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1283");
      this.lambda$Fn19 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 19)
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
      if (Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.key, lists.car.apply1(paramObject)) != Boolean.FALSE) {}
      for (int i = 1;; i = 0) {
        return i + 1 & 0x1;
      }
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 19)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame23
    extends ModuleBody
  {
    Procedure pred;
    
    public Object lambda34recur(Object paramObject)
    {
      if (srfi1.isNullList(paramObject) != Boolean.FALSE) {
        return LList.Empty;
      }
      Object localObject = lists.car.apply1(paramObject);
      if (this.pred.apply1(localObject) != Boolean.FALSE) {
        return lists.cons(localObject, lambda34recur(lists.cdr.apply1(paramObject)));
      }
      return LList.Empty;
    }
  }
  
  public class frame24
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn20;
    Object pred;
    
    public frame24()
    {
      this$1 = new ModuleMethod(this, 20, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1343");
      this.lambda$Fn20 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 20)
      {
        if (lambda35(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda35(Object paramObject)
    {
      if (Scheme.applyToArgs.apply2(this.pred, paramObject) != Boolean.FALSE) {}
      for (int i = 1;; i = 0) {
        return i + 1 & 0x1;
      }
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
  
  public class frame25
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn21;
    Object pred;
    
    public frame25()
    {
      this$1 = new ModuleMethod(this, 21, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1344");
      this.lambda$Fn21 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 21)
      {
        if (lambda36(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda36(Object paramObject)
    {
      if (Scheme.applyToArgs.apply2(this.pred, paramObject) != Boolean.FALSE) {}
      for (int i = 1;; i = 0) {
        return i + 1 & 0x1;
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
  
  public class frame26
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 22, null, 0);
    final ModuleMethod lambda$Fn23;
    Object lis1;
    LList lists;
    Procedure pred;
    
    public frame26()
    {
      this$1 = new ModuleMethod(this, 23, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1350");
      this.lambda$Fn23 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 22) {
        return lambda37();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 23) {
        return lambda38(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda37()
    {
      return srfi1.$PcCars$PlCdrs(lists.cons(this.lis1, this.lists));
    }
    
    Object lambda38(Object paramObject1, Object paramObject2)
    {
      boolean bool = lists.isPair(paramObject1);
      if (bool)
      {
        for (;;)
        {
          paramObject2 = srfi1.$PcCars$PlCdrs$SlPair(paramObject2);
          Object localObject = lists.car.apply1(paramObject2);
          paramObject2 = lists.cdr.apply1(paramObject2);
          if (!lists.isPair(localObject)) {
            break;
          }
          paramObject1 = Scheme.apply.apply2(this.pred, paramObject1);
          if (paramObject1 != Boolean.FALSE) {
            return paramObject1;
          }
          paramObject1 = localObject;
        }
        return Scheme.apply.apply2(this.pred, paramObject1);
      }
      if (bool) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 22)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 23)
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
  
  public class frame27
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 26, null, 0);
    final ModuleMethod lambda$Fn25;
    Object lis1;
    LList lists;
    Procedure pred;
    
    public frame27()
    {
      this$1 = new ModuleMethod(this, 27, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1378");
      this.lambda$Fn25 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 26) {
        return lambda39();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 27) {
        return lambda40(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda39()
    {
      return srfi1.$PcCars$PlCdrs(lists.cons(this.lis1, this.lists));
    }
    
    Object lambda40(Object paramObject1, Object paramObject2)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 26)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 27)
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
  
  public class frame28
    extends ModuleBody
  {
    srfi1.frame27 staticLink;
    
    public Object lambda41lp(Object paramObject1, Object paramObject2)
    {
      srfi1.frame29 localframe29 = new srfi1.frame29();
      localframe29.staticLink = this;
      localframe29.heads = paramObject1;
      localframe29.tails = paramObject2;
      return call_with_values.callWithValues(localframe29.lambda$Fn26, localframe29.lambda$Fn27);
    }
  }
  
  public class frame29
    extends ModuleBody
  {
    Object heads;
    final ModuleMethod lambda$Fn26 = new ModuleMethod(this, 24, null, 0);
    final ModuleMethod lambda$Fn27;
    srfi1.frame28 staticLink;
    Object tails;
    
    public frame29()
    {
      this$1 = new ModuleMethod(this, 25, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1381");
      this.lambda$Fn27 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 24) {
        return lambda42();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 25) {
        return lambda43(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda42()
    {
      return srfi1.$PcCars$PlCdrs(this.tails);
    }
    
    Object lambda43(Object paramObject1, Object paramObject2)
    {
      if (lists.isPair(paramObject1))
      {
        Object localObject2 = Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
        Object localObject1 = localObject2;
        if (localObject2 != Boolean.FALSE) {
          localObject1 = this.staticLink.lambda41lp(paramObject1, paramObject2);
        }
        return localObject1;
      }
      return Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 24)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 25)
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
    public static Object lambda8recur(Object paramObject)
    {
      srfi1.frame4 localframe4 = new srfi1.frame4();
      localframe4.lis = paramObject;
      if (srfi1.isNullList(localframe4.lis) != Boolean.FALSE) {
        return misc.values(new Object[] { localframe4.lis, localframe4.lis, localframe4.lis, localframe4.lis });
      }
      localframe4.elt = lists.car.apply1(localframe4.lis);
      return call_with_values.callWithValues(localframe4.lambda$Fn5, localframe4.lambda$Fn6);
    }
  }
  
  public class frame30
    extends ModuleBody
  {
    Procedure pred;
    
    public Object lambda44lp(Object paramObject1, Object paramObject2)
    {
      srfi1.frame31 localframe31 = new srfi1.frame31();
      localframe31.staticLink = this;
      localframe31.lists = paramObject1;
      localframe31.n = paramObject2;
      return call_with_values.callWithValues(localframe31.lambda$Fn28, localframe31.lambda$Fn29);
    }
  }
  
  public class frame31
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn28 = new ModuleMethod(this, 28, null, 0);
    final ModuleMethod lambda$Fn29;
    Object lists;
    Object n;
    srfi1.frame30 staticLink;
    
    public frame31()
    {
      this$1 = new ModuleMethod(this, 29, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1404");
      this.lambda$Fn29 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 28) {
        return lambda45();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 29) {
        return lambda46(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda45()
    {
      return srfi1.$PcCars$PlCdrs(this.lists);
    }
    
    Object lambda46(Object paramObject1, Object paramObject2)
    {
      boolean bool = lists.isPair(paramObject1);
      if (bool)
      {
        if (Scheme.apply.apply2(this.staticLink.pred, paramObject1) != Boolean.FALSE) {
          return this.n;
        }
        return this.staticLink.lambda44lp(paramObject2, AddOp.$Pl.apply2(this.n, srfi1.Lit1));
      }
      if (bool) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 28)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 29)
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
  
  public class frame32
    extends ModuleBody
  {
    Procedure $Eq;
    final ModuleMethod lambda$Fn30;
    
    public frame32()
    {
      this$1 = new ModuleMethod(this, 30, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1466");
      this.lambda$Fn30 = this$1;
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 30) {
        return lambda47(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda47(Object paramObject1, Object paramObject2)
    {
      if (lists.member(paramObject1, paramObject2, this.$Eq) != Boolean.FALSE) {
        return paramObject2;
      }
      return lists.cons(paramObject1, paramObject2);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 30)
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
  
  public class frame33
    extends ModuleBody
  {
    Procedure $Eq;
    final ModuleMethod lambda$Fn31;
    final ModuleMethod lambda$Fn32;
    
    public frame33()
    {
      this$1 = new ModuleMethod(this, 32, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1476");
      this.lambda$Fn32 = this$1;
      this$1 = new ModuleMethod(this, 33, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1471");
      this.lambda$Fn31 = this$1;
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 32: 
        return lambda49(paramObject1, paramObject2);
      }
      return lambda48(paramObject1, paramObject2);
    }
    
    Object lambda48(Object paramObject1, Object paramObject2)
    {
      if (lists.isNull(paramObject1)) {}
      do
      {
        return paramObject2;
        if (lists.isNull(paramObject2)) {
          return paramObject1;
        }
      } while (paramObject1 == paramObject2);
      return srfi1.fold$V(this.lambda$Fn32, paramObject2, paramObject1, new Object[0]);
    }
    
    Object lambda49(Object paramObject1, Object paramObject2)
    {
      srfi1.frame34 localframe34 = new srfi1.frame34();
      localframe34.staticLink = this;
      localframe34.elt = paramObject1;
      if (srfi1.any$V(localframe34.lambda$Fn33, paramObject2, new Object[0]) != Boolean.FALSE) {
        return paramObject2;
      }
      return lists.cons(localframe34.elt, paramObject2);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 33: 
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
  }
  
  public class frame34
    extends ModuleBody
  {
    Object elt;
    final ModuleMethod lambda$Fn33;
    srfi1.frame33 staticLink;
    
    public frame34()
    {
      this$1 = new ModuleMethod(this, 31, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1476");
      this.lambda$Fn33 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 31) {
        return lambda50(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda50(Object paramObject)
    {
      return this.staticLink.$Eq.apply2(paramObject, this.elt);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 31)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame35
    extends ModuleBody
  {
    Procedure $Eq;
    final ModuleMethod lambda$Fn34;
    final ModuleMethod lambda$Fn35;
    
    public frame35()
    {
      this$1 = new ModuleMethod(this, 35, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1488");
      this.lambda$Fn35 = this$1;
      this$1 = new ModuleMethod(this, 36, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1483");
      this.lambda$Fn34 = this$1;
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 35: 
        return lambda52(paramObject1, paramObject2);
      }
      return lambda51(paramObject1, paramObject2);
    }
    
    Object lambda51(Object paramObject1, Object paramObject2)
    {
      if (lists.isNull(paramObject1)) {}
      do
      {
        return paramObject2;
        if (lists.isNull(paramObject2)) {
          return paramObject1;
        }
      } while (paramObject1 == paramObject2);
      return srfi1.pairFold$V(this.lambda$Fn35, paramObject2, paramObject1, new Object[0]);
    }
    
    Object lambda52(Object paramObject1, Object paramObject2)
    {
      Object localObject = new srfi1.frame36();
      ((srfi1.frame36)localObject).staticLink = this;
      ((srfi1.frame36)localObject).elt = lists.car.apply1(paramObject1);
      if (srfi1.any$V(((srfi1.frame36)localObject).lambda$Fn36, paramObject2, new Object[0]) != Boolean.FALSE) {
        return paramObject2;
      }
      try
      {
        localObject = (Pair)paramObject1;
        lists.setCdr$Ex((Pair)localObject, paramObject2);
        return paramObject1;
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "set-cdr!", 1, paramObject1);
      }
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      switch (paramModuleMethod.selector)
      {
      default: 
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 36: 
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
  }
  
  public class frame36
    extends ModuleBody
  {
    Object elt;
    final ModuleMethod lambda$Fn36;
    srfi1.frame35 staticLink;
    
    public frame36()
    {
      this$1 = new ModuleMethod(this, 34, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1490");
      this.lambda$Fn36 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 34) {
        return lambda53(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda53(Object paramObject)
    {
      return this.staticLink.$Eq.apply2(paramObject, this.elt);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 34)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame37
    extends ModuleBody
  {
    Procedure $Eq;
    final ModuleMethod lambda$Fn37;
    Object lists;
    
    public frame37()
    {
      this$1 = new ModuleMethod(this, 38, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1501");
      this.lambda$Fn37 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 38) {
        return lambda54(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda54(Object paramObject)
    {
      srfi1.frame38 localframe38 = new srfi1.frame38();
      localframe38.staticLink = this;
      localframe38.x = paramObject;
      return srfi1.every$V(localframe38.lambda$Fn38, this.lists, new Object[0]);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 38)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame38
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn38;
    srfi1.frame37 staticLink;
    Object x;
    
    public frame38()
    {
      this$1 = new ModuleMethod(this, 37, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1502");
      this.lambda$Fn38 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 37) {
        return lambda55(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda55(Object paramObject)
    {
      return lists.member(this.x, paramObject, this.staticLink.$Eq);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 37)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame39
    extends ModuleBody
  {
    Procedure $Eq;
    final ModuleMethod lambda$Fn39;
    Object lists;
    
    public frame39()
    {
      this$1 = new ModuleMethod(this, 40, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1509");
      this.lambda$Fn39 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 40) {
        return lambda56(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda56(Object paramObject)
    {
      srfi1.frame40 localframe40 = new srfi1.frame40();
      localframe40.staticLink = this;
      localframe40.x = paramObject;
      return srfi1.every$V(localframe40.lambda$Fn40, this.lists, new Object[0]);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 40)
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
    Object elt;
    final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 5, null, 0);
    final ModuleMethod lambda$Fn6;
    Object lis;
    
    public frame4()
    {
      this$1 = new ModuleMethod(this, 6, null, 16388);
      this$1.setProperty("source-location", "srfi1.scm:644");
      this.lambda$Fn6 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 5) {
        return lambda9();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    {
      if (paramModuleMethod.selector == 6) {
        return lambda10(paramObject1, paramObject2, paramObject3, paramObject4);
      }
      return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
    }
    
    Object lambda10(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    {
      return misc.values(new Object[] { lists.cons(lists.car.apply1(this.elt), paramObject1), lists.cons(lists.cadr.apply1(this.elt), paramObject2), lists.cons(lists.caddr.apply1(this.elt), paramObject3), lists.cons(lists.cadddr.apply1(this.elt), paramObject4) });
    }
    
    Object lambda9()
    {
      return srfi1.frame3.lambda8recur(lists.cdr.apply1(this.lis));
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
    
    public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 6)
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
  
  public class frame40
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn40;
    srfi1.frame39 staticLink;
    Object x;
    
    public frame40()
    {
      this$1 = new ModuleMethod(this, 39, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1510");
      this.lambda$Fn40 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 39) {
        return lambda57(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda57(Object paramObject)
    {
      return lists.member(this.x, paramObject, this.staticLink.$Eq);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 39)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame41
    extends ModuleBody
  {
    Procedure $Eq;
    final ModuleMethod lambda$Fn41;
    Object lists;
    
    public frame41()
    {
      this$1 = new ModuleMethod(this, 42, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1518");
      this.lambda$Fn41 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 42) {
        return lambda58(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda58(Object paramObject)
    {
      srfi1.frame42 localframe42 = new srfi1.frame42();
      localframe42.staticLink = this;
      localframe42.x = paramObject;
      return srfi1.every$V(localframe42.lambda$Fn42, this.lists, new Object[0]);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 42)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame42
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn42;
    srfi1.frame41 staticLink;
    Object x;
    
    public frame42()
    {
      this$1 = new ModuleMethod(this, 41, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1519");
      this.lambda$Fn42 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 41)
      {
        if (lambda59(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda59(Object paramObject)
    {
      if (lists.member(this.x, paramObject, this.staticLink.$Eq) != Boolean.FALSE) {}
      for (int i = 1;; i = 0) {
        return i + 1 & 0x1;
      }
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 41)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame43
    extends ModuleBody
  {
    Procedure $Eq;
    final ModuleMethod lambda$Fn43;
    Object lists;
    
    public frame43()
    {
      this$1 = new ModuleMethod(this, 44, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1527");
      this.lambda$Fn43 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 44) {
        return lambda60(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda60(Object paramObject)
    {
      srfi1.frame44 localframe44 = new srfi1.frame44();
      localframe44.staticLink = this;
      localframe44.x = paramObject;
      return srfi1.every$V(localframe44.lambda$Fn44, this.lists, new Object[0]);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 44)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame44
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn44;
    srfi1.frame43 staticLink;
    Object x;
    
    public frame44()
    {
      this$1 = new ModuleMethod(this, 43, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1528");
      this.lambda$Fn44 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 43)
      {
        if (lambda61(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda61(Object paramObject)
    {
      if (lists.member(this.x, paramObject, this.staticLink.$Eq) != Boolean.FALSE) {}
      for (int i = 1;; i = 0) {
        return i + 1 & 0x1;
      }
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 43)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame45
    extends ModuleBody
  {
    Procedure $Eq;
    final ModuleMethod lambda$Fn45;
    
    public frame45()
    {
      this$1 = new ModuleMethod(this, 48, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1534");
      this.lambda$Fn45 = this$1;
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 48) {
        return lambda62(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda62(Object paramObject1, Object paramObject2)
    {
      srfi1.frame46 localframe46 = new srfi1.frame46();
      localframe46.staticLink = this;
      localframe46.b = paramObject1;
      localframe46.a = paramObject2;
      return call_with_values.callWithValues(localframe46.lambda$Fn46, localframe46.lambda$Fn47);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 48)
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
  
  public class frame46
    extends ModuleBody
  {
    Object a;
    Object b;
    final ModuleMethod lambda$Fn46 = new ModuleMethod(this, 46, null, 0);
    final ModuleMethod lambda$Fn47;
    srfi1.frame45 staticLink;
    
    public frame46()
    {
      this$1 = new ModuleMethod(this, 47, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1544");
      this.lambda$Fn47 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 46) {
        return lambda63();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 47) {
        return lambda64(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda63()
    {
      return srfi1.lsetDiff$PlIntersection$V(this.staticLink.$Eq, this.a, new Object[] { this.b });
    }
    
    Object lambda64(Object paramObject1, Object paramObject2)
    {
      srfi1.frame47 localframe47 = new srfi1.frame47();
      localframe47.staticLink = this;
      localframe47.a$Mnint$Mnb = paramObject2;
      if (lists.isNull(paramObject1)) {
        return srfi1.lsetDifference$V(this.staticLink.$Eq, this.b, new Object[] { this.a });
      }
      if (lists.isNull(localframe47.a$Mnint$Mnb)) {
        return append.append$V(new Object[] { this.b, this.a });
      }
      return srfi1.fold$V(localframe47.lambda$Fn48, paramObject1, this.b, new Object[0]);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 46)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 47)
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
  
  public class frame47
    extends ModuleBody
  {
    Object a$Mnint$Mnb;
    final ModuleMethod lambda$Fn48;
    srfi1.frame46 staticLink;
    
    public frame47()
    {
      this$1 = new ModuleMethod(this, 45, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1547");
      this.lambda$Fn48 = this$1;
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 45) {
        return lambda65(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda65(Object paramObject1, Object paramObject2)
    {
      if (lists.member(paramObject1, this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE) {
        return paramObject2;
      }
      return lists.cons(paramObject1, paramObject2);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 45)
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
  
  public class frame48
    extends ModuleBody
  {
    Procedure $Eq;
    final ModuleMethod lambda$Fn49;
    
    public frame48()
    {
      this$1 = new ModuleMethod(this, 52, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1555");
      this.lambda$Fn49 = this$1;
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 52) {
        return lambda66(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda66(Object paramObject1, Object paramObject2)
    {
      srfi1.frame49 localframe49 = new srfi1.frame49();
      localframe49.staticLink = this;
      localframe49.b = paramObject1;
      localframe49.a = paramObject2;
      return call_with_values.callWithValues(localframe49.lambda$Fn50, localframe49.lambda$Fn51);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 52)
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
  
  public class frame49
    extends ModuleBody
  {
    Object a;
    Object b;
    final ModuleMethod lambda$Fn50 = new ModuleMethod(this, 50, null, 0);
    final ModuleMethod lambda$Fn51;
    srfi1.frame48 staticLink;
    
    public frame49()
    {
      this$1 = new ModuleMethod(this, 51, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1565");
      this.lambda$Fn51 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 50) {
        return lambda67();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 51) {
        return lambda68(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda67()
    {
      return srfi1.lsetDiff$PlIntersection$Ex$V(this.staticLink.$Eq, this.a, new Object[] { this.b });
    }
    
    Object lambda68(Object paramObject1, Object paramObject2)
    {
      srfi1.frame50 localframe50 = new srfi1.frame50();
      localframe50.staticLink = this;
      localframe50.a$Mnint$Mnb = paramObject2;
      if (lists.isNull(paramObject1)) {
        return srfi1.lsetDifference$Ex$V(this.staticLink.$Eq, this.b, new Object[] { this.a });
      }
      if (lists.isNull(localframe50.a$Mnint$Mnb)) {
        return srfi1.append$Ex$V(new Object[] { this.b, this.a });
      }
      return srfi1.pairFold$V(localframe50.lambda$Fn52, paramObject1, this.b, new Object[0]);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 50)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 51)
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
    public static Object lambda11recur(Object paramObject)
    {
      srfi1.frame6 localframe6 = new srfi1.frame6();
      localframe6.lis = paramObject;
      if (srfi1.isNullList(localframe6.lis) != Boolean.FALSE) {
        return misc.values(new Object[] { localframe6.lis, localframe6.lis, localframe6.lis, localframe6.lis, localframe6.lis });
      }
      localframe6.elt = lists.car.apply1(localframe6.lis);
      return call_with_values.callWithValues(localframe6.lambda$Fn7, localframe6.lambda$Fn8);
    }
  }
  
  public class frame50
    extends ModuleBody
  {
    Object a$Mnint$Mnb;
    final ModuleMethod lambda$Fn52;
    srfi1.frame49 staticLink;
    
    public frame50()
    {
      this$1 = new ModuleMethod(this, 49, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:1568");
      this.lambda$Fn52 = this$1;
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 49) {
        return lambda69(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda69(Object paramObject1, Object paramObject2)
    {
      if (lists.member(lists.car.apply1(paramObject1), this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE) {
        return paramObject2;
      }
      try
      {
        Pair localPair = (Pair)paramObject1;
        lists.setCdr$Ex(localPair, paramObject2);
        return paramObject1;
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "set-cdr!", 1, paramObject1);
      }
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 49)
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
  
  public class frame51
    extends ModuleBody
  {
    Procedure $Eq;
    final ModuleMethod lambda$Fn53;
    LList lists;
    
    public frame51()
    {
      this$1 = new ModuleMethod(this, 54, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1579");
      this.lambda$Fn53 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 54)
      {
        if (lambda70(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda70(Object paramObject)
    {
      int i = 0;
      srfi1.frame52 localframe52 = new srfi1.frame52();
      localframe52.staticLink = this;
      localframe52.elt = paramObject;
      if (srfi1.any$V(localframe52.lambda$Fn54, this.lists, new Object[0]) != Boolean.FALSE) {
        i = 1;
      }
      return i + 1 & 0x1;
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 54)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame52
    extends ModuleBody
  {
    Object elt;
    final ModuleMethod lambda$Fn54;
    srfi1.frame51 staticLink;
    
    public frame52()
    {
      this$1 = new ModuleMethod(this, 53, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1580");
      this.lambda$Fn54 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 53) {
        return lambda71(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda71(Object paramObject)
    {
      return lists.member(this.elt, paramObject, this.staticLink.$Eq);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 53)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame53
    extends ModuleBody
  {
    Procedure $Eq;
    final ModuleMethod lambda$Fn55;
    LList lists;
    
    public frame53()
    {
      this$1 = new ModuleMethod(this, 56, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1587");
      this.lambda$Fn55 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 56)
      {
        if (lambda72(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda72(Object paramObject)
    {
      int i = 0;
      srfi1.frame54 localframe54 = new srfi1.frame54();
      localframe54.staticLink = this;
      localframe54.elt = paramObject;
      if (srfi1.any$V(localframe54.lambda$Fn56, this.lists, new Object[0]) != Boolean.FALSE) {
        i = 1;
      }
      return i + 1 & 0x1;
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 56)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame54
    extends ModuleBody
  {
    Object elt;
    final ModuleMethod lambda$Fn56;
    srfi1.frame53 staticLink;
    
    public frame54()
    {
      this$1 = new ModuleMethod(this, 55, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1588");
      this.lambda$Fn56 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 55) {
        return lambda73(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda73(Object paramObject)
    {
      return lists.member(this.elt, paramObject, this.staticLink.$Eq);
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 55)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame55
    extends ModuleBody
  {
    Continuation abort;
    
    public Object lambda74recur(Object paramObject)
    {
      if (lists.isPair(paramObject))
      {
        Object localObject = lists.car.apply1(paramObject);
        if (srfi1.isNullList(localObject) != Boolean.FALSE) {
          return this.abort.apply1(LList.Empty);
        }
        return lists.cons(lists.cdr.apply1(localObject), lambda74recur(lists.cdr.apply1(paramObject)));
      }
      return LList.Empty;
    }
  }
  
  public class frame56
    extends ModuleBody
  {
    Object last$Mnelt;
    
    public Object lambda75recur(Object paramObject)
    {
      if (lists.isPair(paramObject)) {
        return lists.cons(lists.caar.apply1(paramObject), lambda75recur(lists.cdr.apply1(paramObject)));
      }
      return LList.list1(this.last$Mnelt);
    }
  }
  
  public class frame57
    extends ModuleBody
  {
    Continuation abort;
    
    public Object lambda76recur(Object paramObject)
    {
      srfi1.frame58 localframe58 = new srfi1.frame58();
      localframe58.staticLink = this;
      localframe58.lists = paramObject;
      if (lists.isPair(localframe58.lists)) {
        return call_with_values.callWithValues(localframe58.lambda$Fn57, localframe58.lambda$Fn58);
      }
      return misc.values(new Object[] { LList.Empty, LList.Empty });
    }
  }
  
  public class frame58
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn57 = new ModuleMethod(this, 61, null, 0);
    final ModuleMethod lambda$Fn58;
    Object lists;
    srfi1.frame57 staticLink;
    
    public frame58()
    {
      this$1 = new ModuleMethod(this, 62, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:762");
      this.lambda$Fn58 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 61) {
        return lambda77();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 62) {
        return lambda78(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda77()
    {
      return srfi1.car$PlCdr(this.lists);
    }
    
    Object lambda78(Object paramObject1, Object paramObject2)
    {
      srfi1.frame59 localframe59 = new srfi1.frame59();
      localframe59.staticLink = this;
      localframe59.list = paramObject1;
      localframe59.other$Mnlists = paramObject2;
      if (srfi1.isNullList(localframe59.list) != Boolean.FALSE) {
        return this.staticLink.abort.apply2(LList.Empty, LList.Empty);
      }
      return call_with_values.callWithValues(localframe59.lambda$Fn59, localframe59.lambda$Fn60);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 61)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 62)
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
  
  public class frame59
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn59 = new ModuleMethod(this, 59, null, 0);
    final ModuleMethod lambda$Fn60;
    Object list;
    Object other$Mnlists;
    srfi1.frame58 staticLink;
    
    public frame59()
    {
      this$1 = new ModuleMethod(this, 60, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:764");
      this.lambda$Fn60 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 59) {
        return lambda79();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 60) {
        return lambda80(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda79()
    {
      return srfi1.car$PlCdr(this.list);
    }
    
    Object lambda80(Object paramObject1, Object paramObject2)
    {
      srfi1.frame60 localframe60 = new srfi1.frame60();
      localframe60.staticLink = this;
      localframe60.a = paramObject1;
      localframe60.d = paramObject2;
      return call_with_values.callWithValues(localframe60.lambda$Fn61, localframe60.lambda$Fn62);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 59)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 60)
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
    Object elt;
    final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 7, null, 0);
    final ModuleMethod lambda$Fn8;
    Object lis;
    
    public frame6()
    {
      this$1 = new ModuleMethod(this, 8, null, 20485);
      this$1.setProperty("source-location", "srfi1.scm:654");
      this.lambda$Fn8 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 7) {
        return lambda12();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
    {
      if (paramModuleMethod.selector == 8) {
        return lambda13(paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramArrayOfObject[4]);
      }
      return super.applyN(paramModuleMethod, paramArrayOfObject);
    }
    
    Object lambda12()
    {
      return srfi1.frame5.lambda11recur(lists.cdr.apply1(this.lis));
    }
    
    Object lambda13(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5)
    {
      return misc.values(new Object[] { lists.cons(lists.car.apply1(this.elt), paramObject1), lists.cons(lists.cadr.apply1(this.elt), paramObject2), lists.cons(lists.caddr.apply1(this.elt), paramObject3), lists.cons(lists.cadddr.apply1(this.elt), paramObject4), lists.cons(lists.car.apply1(lists.cddddr.apply1(this.elt)), paramObject5) });
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 7)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 8)
      {
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      }
      return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
    }
  }
  
  public class frame60
    extends ModuleBody
  {
    Object a;
    Object d;
    final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 57, null, 0);
    final ModuleMethod lambda$Fn62;
    srfi1.frame59 staticLink;
    
    public frame60()
    {
      this$1 = new ModuleMethod(this, 58, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:765");
      this.lambda$Fn62 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 57) {
        return lambda81();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 58) {
        return lambda82(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda81()
    {
      return this.staticLink.staticLink.staticLink.lambda76recur(this.staticLink.other$Mnlists);
    }
    
    Object lambda82(Object paramObject1, Object paramObject2)
    {
      return misc.values(new Object[] { lists.cons(this.a, paramObject1), lists.cons(this.d, paramObject2) });
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 57)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 58)
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
  
  public class frame61
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn63 = new ModuleMethod(this, 63, null, 0);
    Object lists;
    
    static Pair lambda84(Object paramObject1, Object paramObject2)
    {
      return lists.cons(paramObject1, paramObject2);
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 63) {
        return lambda83();
      }
      return super.apply0(paramModuleMethod);
    }
    
    Object lambda83()
    {
      return srfi1.$PcCars$PlCdrs(this.lists);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 63)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
  }
  
  public class frame62
    extends ModuleBody
  {
    Object cars$Mnfinal;
  }
  
  public class frame63
    extends ModuleBody
  {
    Continuation abort;
    srfi1.frame62 staticLink;
    
    public Object lambda85recur(Object paramObject)
    {
      srfi1.frame64 localframe64 = new srfi1.frame64();
      localframe64.staticLink = this;
      localframe64.lists = paramObject;
      if (lists.isPair(localframe64.lists)) {
        return call_with_values.callWithValues(localframe64.lambda$Fn65, localframe64.lambda$Fn66);
      }
      return misc.values(new Object[] { LList.list1(this.staticLink.cars$Mnfinal), LList.Empty });
    }
  }
  
  public class frame64
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn65 = new ModuleMethod(this, 68, null, 0);
    final ModuleMethod lambda$Fn66;
    Object lists;
    srfi1.frame63 staticLink;
    
    public frame64()
    {
      this$1 = new ModuleMethod(this, 69, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:783");
      this.lambda$Fn66 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 68) {
        return lambda86();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 69) {
        return lambda87(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda86()
    {
      return srfi1.car$PlCdr(this.lists);
    }
    
    Object lambda87(Object paramObject1, Object paramObject2)
    {
      srfi1.frame65 localframe65 = new srfi1.frame65();
      localframe65.staticLink = this;
      localframe65.list = paramObject1;
      localframe65.other$Mnlists = paramObject2;
      if (srfi1.isNullList(localframe65.list) != Boolean.FALSE) {
        return this.staticLink.abort.apply2(LList.Empty, LList.Empty);
      }
      return call_with_values.callWithValues(localframe65.lambda$Fn67, localframe65.lambda$Fn68);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 68)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 69)
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
  
  public class frame65
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn67 = new ModuleMethod(this, 66, null, 0);
    final ModuleMethod lambda$Fn68;
    Object list;
    Object other$Mnlists;
    srfi1.frame64 staticLink;
    
    public frame65()
    {
      this$1 = new ModuleMethod(this, 67, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:785");
      this.lambda$Fn68 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 66) {
        return lambda88();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 67) {
        return lambda89(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda88()
    {
      return srfi1.car$PlCdr(this.list);
    }
    
    Object lambda89(Object paramObject1, Object paramObject2)
    {
      srfi1.frame66 localframe66 = new srfi1.frame66();
      localframe66.staticLink = this;
      localframe66.a = paramObject1;
      localframe66.d = paramObject2;
      return call_with_values.callWithValues(localframe66.lambda$Fn69, localframe66.lambda$Fn70);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 66)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 67)
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
  
  public class frame66
    extends ModuleBody
  {
    Object a;
    Object d;
    final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 64, null, 0);
    final ModuleMethod lambda$Fn70;
    srfi1.frame65 staticLink;
    
    public frame66()
    {
      this$1 = new ModuleMethod(this, 65, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:786");
      this.lambda$Fn70 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 64) {
        return lambda90();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 65) {
        return lambda91(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda90()
    {
      return this.staticLink.staticLink.staticLink.lambda85recur(this.staticLink.other$Mnlists);
    }
    
    Object lambda91(Object paramObject1, Object paramObject2)
    {
      return misc.values(new Object[] { lists.cons(this.a, paramObject1), lists.cons(this.d, paramObject2) });
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 64)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 65)
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
  
  public class frame67
    extends ModuleBody
  {
    public static Object lambda92recur(Object paramObject)
    {
      srfi1.frame68 localframe68 = new srfi1.frame68();
      localframe68.lists = paramObject;
      if (lists.isPair(localframe68.lists)) {
        return call_with_values.callWithValues(localframe68.lambda$Fn71, localframe68.lambda$Fn72);
      }
      return misc.values(new Object[] { LList.Empty, LList.Empty });
    }
  }
  
  public class frame68
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn71 = new ModuleMethod(this, 74, null, 0);
    final ModuleMethod lambda$Fn72;
    Object lists;
    
    public frame68()
    {
      this$1 = new ModuleMethod(this, 75, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:794");
      this.lambda$Fn72 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 74) {
        return lambda93();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 75) {
        return lambda94(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda93()
    {
      return srfi1.car$PlCdr(this.lists);
    }
    
    Object lambda94(Object paramObject1, Object paramObject2)
    {
      srfi1.frame69 localframe69 = new srfi1.frame69();
      localframe69.staticLink = this;
      localframe69.list = paramObject1;
      localframe69.other$Mnlists = paramObject2;
      return call_with_values.callWithValues(localframe69.lambda$Fn73, localframe69.lambda$Fn74);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 74)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 75)
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
  
  public class frame69
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn73 = new ModuleMethod(this, 72, null, 0);
    final ModuleMethod lambda$Fn74;
    Object list;
    Object other$Mnlists;
    srfi1.frame68 staticLink;
    
    public frame69()
    {
      this$1 = new ModuleMethod(this, 73, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:795");
      this.lambda$Fn74 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 72) {
        return lambda95();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 73) {
        return lambda96(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda95()
    {
      return srfi1.car$PlCdr(this.list);
    }
    
    Object lambda96(Object paramObject1, Object paramObject2)
    {
      srfi1.frame70 localframe70 = new srfi1.frame70();
      localframe70.staticLink = this;
      localframe70.a = paramObject1;
      localframe70.d = paramObject2;
      return call_with_values.callWithValues(localframe70.lambda$Fn75, localframe70.lambda$Fn76);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 72)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 73)
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
    Procedure kons;
    
    public Object lambda14lp(Object paramObject1, Object paramObject2)
    {
      srfi1.frame8 localframe8 = new srfi1.frame8();
      localframe8.staticLink = this;
      localframe8.lists = paramObject1;
      localframe8.ans = paramObject2;
      return call_with_values.callWithValues(localframe8.lambda$Fn9, localframe8.lambda$Fn10);
    }
  }
  
  public class frame70
    extends ModuleBody
  {
    Object a;
    Object d;
    final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 70, null, 0);
    final ModuleMethod lambda$Fn76;
    srfi1.frame69 staticLink;
    
    public frame70()
    {
      this$1 = new ModuleMethod(this, 71, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:796");
      this.lambda$Fn76 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 70) {
        return lambda97();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 71) {
        return lambda98(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda97()
    {
      return srfi1.frame67.lambda92recur(this.staticLink.other$Mnlists);
    }
    
    Object lambda98(Object paramObject1, Object paramObject2)
    {
      return misc.values(new Object[] { lists.cons(this.a, paramObject1), lists.cons(this.d, paramObject2) });
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 70)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
    
    public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 71)
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
  
  public class frame71
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn77 = new ModuleMethod(this, 76, null, 0);
    Object lists;
    
    static Pair lambda100(Object paramObject1, Object paramObject2)
    {
      return lists.cons(paramObject1, paramObject2);
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 76) {
        return lambda99();
      }
      return super.apply0(paramModuleMethod);
    }
    
    Object lambda99()
    {
      return srfi1.$PcCars$PlCdrs$SlNoTest(this.lists);
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 76)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
  }
  
  public class frame72
    extends ModuleBody
  {
    Object $Eq;
    final ModuleMethod lambda$Fn79;
    Object lis2;
    
    public frame72()
    {
      this$1 = new ModuleMethod(this, 77, null, 4097);
      this$1.setProperty("source-location", "srfi1.scm:1443");
      this.lambda$Fn79 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 77) {
        return lambda101(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Object lambda101(Object paramObject)
    {
      Object localObject2 = this.lis2;
      Object localObject1 = this.$Eq;
      try
      {
        Procedure localProcedure = (Procedure)localObject1;
        return lists.member(paramObject, localObject2, localProcedure);
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "member", 3, localObject1);
      }
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 77)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
  
  public class frame8
    extends ModuleBody
  {
    Object ans;
    final ModuleMethod lambda$Fn10;
    final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 9, null, 0);
    Object lists;
    srfi1.frame7 staticLink;
    
    public frame8()
    {
      this$1 = new ModuleMethod(this, 10, null, 8194);
      this$1.setProperty("source-location", "srfi1.scm:859");
      this.lambda$Fn10 = this$1;
    }
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 9) {
        return lambda15();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    {
      if (paramModuleMethod.selector == 10) {
        return lambda16(paramObject1, paramObject2);
      }
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    
    Object lambda15()
    {
      return srfi1.$PcCars$PlCdrs$Pl(this.lists, this.ans);
    }
    
    Object lambda16(Object paramObject1, Object paramObject2)
    {
      if (lists.isNull(paramObject1)) {
        return this.ans;
      }
      return this.staticLink.lambda14lp(paramObject2, Scheme.apply.apply2(this.staticLink.kons, paramObject1));
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
  
  public class frame9
    extends ModuleBody
  {
    Object knil;
    Procedure kons;
    
    public Object lambda17recur(Object paramObject)
    {
      Object localObject = srfi1.$PcCdrs(paramObject);
      if (lists.isNull(localObject)) {
        return this.knil;
      }
      return Scheme.apply.apply2(this.kons, srfi1.$PcCars$Pl(paramObject, lambda17recur(localObject)));
    }
    
    public Object lambda18recur(Object paramObject)
    {
      if (srfi1.isNullList(paramObject) != Boolean.FALSE) {
        return this.knil;
      }
      Object localObject = lists.car.apply1(paramObject);
      return this.kons.apply2(localObject, lambda18recur(lists.cdr.apply1(paramObject)));
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\srfi1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */