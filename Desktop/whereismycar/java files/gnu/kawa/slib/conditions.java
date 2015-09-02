package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.IsEq;
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
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;
import kawa.standard.append;

public class conditions
  extends ModuleBody
{
  public static Object $Amcondition;
  public static Object $Amerror;
  public static Object $Ammessage;
  public static Object $Amserious;
  static final Class $Lscondition$Mntype$Gr;
  public static final Class $Prvt$$Lscondition$Gr;
  public static final ModuleMethod $Prvt$type$Mnfield$Mnalist$Mn$Grcondition;
  public static final conditions $instance;
  static final SimpleSymbol Lit0;
  static final SimpleSymbol Lit1;
  static final SimpleSymbol Lit10;
  static final SimpleSymbol Lit11;
  static final SimpleSymbol Lit12;
  static final SimpleSymbol Lit13;
  static final SyntaxRules Lit14;
  static final SimpleSymbol Lit15;
  static final SimpleSymbol Lit16;
  static final SimpleSymbol Lit17;
  static final SimpleSymbol Lit18;
  static final SyntaxRules Lit19;
  static final PairWithPosition Lit2;
  static final SimpleSymbol Lit20;
  static final SimpleSymbol Lit21;
  static final SimpleSymbol Lit22 = (SimpleSymbol)new SimpleSymbol("thing").readResolve();
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SyntaxRules Lit9;
  public static final Macro condition;
  public static final ModuleMethod condition$Mnhas$Mntype$Qu;
  public static final ModuleMethod condition$Mnref;
  static final Macro condition$Mntype$Mnfield$Mnalist;
  public static final ModuleMethod condition$Mntype$Qu;
  public static final ModuleMethod condition$Qu;
  public static final Macro define$Mncondition$Mntype;
  public static final ModuleMethod extract$Mncondition;
  public static final ModuleMethod make$Mncompound$Mncondition;
  public static final ModuleMethod make$Mncondition;
  public static final ModuleMethod make$Mncondition$Mntype;
  
  static
  {
    Lit21 = (SimpleSymbol)new SimpleSymbol("quote").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("type-field-alist->condition").readResolve();
    Object localObject1 = (SimpleSymbol)new SimpleSymbol("condition").readResolve();
    Lit18 = (SimpleSymbol)localObject1;
    Object localObject2 = new SyntaxRule(new SyntaxPattern("\f\030]\f\007-\f\017\f\027\b\b\020\b\000\030\b", new Object[0], 3), "\003\005\005", "\021\030\004\b\021\030\f\b\005\021\030\024\t\003\b\021\030\f\b\r\021\030\024)\021\030\034\b\013\b\023", new Object[] { Lit20, (SimpleSymbol)new SimpleSymbol("list").readResolve(), (SimpleSymbol)new SimpleSymbol("cons").readResolve(), Lit21 }, 2);
    Lit19 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 3);
    Lit17 = (SimpleSymbol)new SimpleSymbol("extract-condition").readResolve();
    Lit16 = (SimpleSymbol)new SimpleSymbol("make-compound-condition").readResolve();
    Lit15 = (SimpleSymbol)new SimpleSymbol("condition-ref").readResolve();
    localObject1 = (SimpleSymbol)new SimpleSymbol("condition-type-field-alist").readResolve();
    Lit13 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\b\021\030\f\021\030\024\b\003", new Object[] { PairWithPosition.make((SimpleSymbol)new SimpleSymbol("$lookup$").readResolve(), Pair.make((SimpleSymbol)new SimpleSymbol("*").readResolve(), Pair.make(Pair.make((SimpleSymbol)new SimpleSymbol("quasiquote").readResolve(), Pair.make((SimpleSymbol)new SimpleSymbol(".type-field-alist").readResolve(), LList.Empty)), LList.Empty)), "conditions.scm", 581639), (SimpleSymbol)new SimpleSymbol("as").readResolve(), (SimpleSymbol)new SimpleSymbol("<condition>").readResolve() }, 0);
    Lit14 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 1);
    Lit12 = (SimpleSymbol)new SimpleSymbol("condition-has-type?").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("make-condition").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("condition?").readResolve();
    localObject1 = (SimpleSymbol)new SimpleSymbol("define-condition-type").readResolve();
    Lit8 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxPattern("\f\030\f\007\f\017\f\027-\f\037\f'\b\030\020\b", new Object[0], 5);
    SimpleSymbol localSimpleSymbol1 = (SimpleSymbol)new SimpleSymbol("begin").readResolve();
    SimpleSymbol localSimpleSymbol2 = (SimpleSymbol)new SimpleSymbol("define").readResolve();
    SimpleSymbol localSimpleSymbol3 = (SimpleSymbol)new SimpleSymbol("make-condition-type").readResolve();
    Lit7 = localSimpleSymbol3;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\001\001\001\003\003", "\021\030\004É\021\030\f\t\003\b\021\030\024)\021\030\034\b\003\t\013\b\021\030\034\b\b\035\033Á\021\030\f!\t\023\030$\b\021\030,\021\0304\b\021\030<\021\030D\b\003\b%\021\030\f!\t#\030L\b\021\030TA\021\030\\\021\030d\b\003\b\021\030\034\b\033", new Object[] { localSimpleSymbol1, localSimpleSymbol2, localSimpleSymbol3, Lit21, PairWithPosition.make(Lit22, LList.Empty, "conditions.scm", 327708), (SimpleSymbol)new SimpleSymbol("and").readResolve(), PairWithPosition.make(Lit10, PairWithPosition.make(Lit22, LList.Empty, "conditions.scm", 331803), "conditions.scm", 331791), Lit12, Lit22, PairWithPosition.make(Lit18, LList.Empty, "conditions.scm", 339996), Lit15, Lit17, Lit18 }, 1);
    Lit9 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 5);
    Lit6 = (SimpleSymbol)new SimpleSymbol("condition-type?").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("message").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("&error").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("&serious").readResolve();
    Lit2 = PairWithPosition.make(Lit5, LList.Empty, "conditions.scm", 925699);
    Lit1 = (SimpleSymbol)new SimpleSymbol("&message").readResolve();
    Lit0 = (SimpleSymbol)new SimpleSymbol("&condition").readResolve();
    $instance = new conditions();
    $Lscondition$Mntype$Gr = condition.Mntype.class;
    localObject1 = $instance;
    condition$Mntype$Qu = new ModuleMethod((ModuleBody)localObject1, 2, Lit6, 4097);
    make$Mncondition$Mntype = new ModuleMethod((ModuleBody)localObject1, 3, Lit7, 12291);
    define$Mncondition$Mntype = Macro.make(Lit8, Lit9, $instance);
    $Prvt$$Lscondition$Gr = condition.class;
    condition$Qu = new ModuleMethod((ModuleBody)localObject1, 4, Lit10, 4097);
    make$Mncondition = new ModuleMethod((ModuleBody)localObject1, 5, Lit11, 61441);
    condition$Mnhas$Mntype$Qu = new ModuleMethod((ModuleBody)localObject1, 6, Lit12, 8194);
    condition$Mntype$Mnfield$Mnalist = Macro.make(Lit13, Lit14, $instance);
    condition$Mnref = new ModuleMethod((ModuleBody)localObject1, 7, Lit15, 8194);
    make$Mncompound$Mncondition = new ModuleMethod((ModuleBody)localObject1, 8, Lit16, 61441);
    extract$Mncondition = new ModuleMethod((ModuleBody)localObject1, 9, Lit17, 8194);
    condition = Macro.make(Lit18, Lit19, $instance);
    $Prvt$type$Mnfield$Mnalist$Mn$Grcondition = new ModuleMethod((ModuleBody)localObject1, 10, Lit20, 4097);
    $instance.run();
  }
  
  public conditions()
  {
    ModuleInfo.register(this);
  }
  
  static Object checkConditionTypeFieldAlist(Object paramObject)
  {
    Object localObject1 = paramObject;
    Object localObject3;
    if (!lists.isNull(localObject1))
    {
      localObject3 = lists.car.apply1(localObject1);
      localObject2 = lists.car.apply1(localObject3);
    }
    for (;;)
    {
      Object localObject4;
      Object localObject6;
      try
      {
        localMntype1 = (condition.Mntype)localObject2;
        localObject2 = lists.cdr.apply1(localObject3);
        localObject3 = LList.Empty;
        if (localObject2 == LList.Empty)
        {
          localObject2 = LList.reverseInPlace(localObject3);
          localObject3 = localMntype1.all$Mnfields;
          localObject2 = srfi1.lsetDifference$V(Scheme.isEq, localObject3, new Object[] { localObject2 });
          if (localObject2 != LList.Empty) {
            continue;
          }
          localObject1 = lists.cdr.apply1(localObject1);
        }
      }
      catch (ClassCastException paramObject)
      {
        condition.Mntype localMntype1;
        Object localObject5;
        condition.Mntype localMntype2;
        boolean bool;
        throw new WrongType((ClassCastException)paramObject, "type", -2, localObject2);
      }
      try
      {
        localObject4 = (Pair)localObject2;
        localObject2 = ((Pair)localObject4).getCdr();
        localObject3 = Pair.make(lists.car.apply1(((Pair)localObject4).getCar()), localObject3);
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "arg0", -2, localObject2);
      }
      try
      {
        localObject3 = (Pair)localObject2;
        localObject5 = ((Pair)localObject3).getCar();
        localObject4 = conditionTypeFieldSupertype(localMntype1, localObject5);
        localObject2 = paramObject;
        localObject6 = lists.car.apply1(lists.car.apply1(localObject2));
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "arg0", -2, localObject2);
      }
      try
      {
        localMntype2 = (condition.Mntype)localObject6;
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "condition-subtype?", 0, localObject6);
      }
      try
      {
        localObject6 = (condition.Mntype)localObject4;
        bool = isConditionSubtype(localMntype2, (condition.Mntype)localObject6);
        if (bool)
        {
          if (!bool) {
            misc.error$V("missing field in condition construction", new Object[] { localMntype1, localObject5 });
          }
          localObject2 = ((Pair)localObject3).getCdr();
        }
        else
        {
          localObject2 = lists.cdr.apply1(localObject2);
        }
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "condition-subtype?", 1, localObject4);
      }
    }
    return Values.empty;
  }
  
  /* Error */
  static Object conditionMessage(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: checkcast 261	gnu/kawa/slib/condition
    //   4: astore_1
    //   5: getstatic 375	gnu/kawa/slib/conditions:$Ammessage	Ljava/lang/Object;
    //   8: astore_0
    //   9: aload_0
    //   10: checkcast 6	gnu/kawa/slib/condition$Mntype
    //   13: astore_2
    //   14: aload_1
    //   15: aload_2
    //   16: invokestatic 379	gnu/kawa/slib/conditions:extractCondition	(Lgnu/kawa/slib/condition;Lgnu/kawa/slib/condition$Mntype;)Lgnu/kawa/slib/condition;
    //   19: getstatic 218	gnu/kawa/slib/conditions:Lit5	Lgnu/mapping/SimpleSymbol;
    //   22: invokestatic 383	gnu/kawa/slib/conditions:conditionRef	(Lgnu/kawa/slib/condition;Ljava/lang/Object;)Ljava/lang/Object;
    //   25: areturn
    //   26: astore_1
    //   27: new 363	gnu/mapping/WrongType
    //   30: dup
    //   31: aload_1
    //   32: ldc 118
    //   34: iconst_0
    //   35: aload_0
    //   36: invokespecial 368	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   39: athrow
    //   40: astore_1
    //   41: new 363	gnu/mapping/WrongType
    //   44: dup
    //   45: aload_1
    //   46: ldc 118
    //   48: iconst_1
    //   49: aload_0
    //   50: invokespecial 368	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   53: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	54	0	paramObject	Object
    //   4	11	1	localcondition	condition
    //   26	6	1	localClassCastException1	ClassCastException
    //   40	6	1	localClassCastException2	ClassCastException
    //   13	3	2	localMntype	condition.Mntype
    // Exception table:
    //   from	to	target	type
    //   0	5	26	java/lang/ClassCastException
    //   9	14	40	java/lang/ClassCastException
  }
  
  public static Object conditionRef(condition paramcondition, Object paramObject)
  {
    return typeFieldAlistRef(paramcondition.type$Mnfield$Mnalist, paramObject);
  }
  
  static Object conditionTypeFieldSupertype(condition.Mntype paramMntype, Object paramObject)
  {
    for (;;)
    {
      Object localObject;
      if (paramMntype == Boolean.FALSE) {
        localObject = Boolean.FALSE;
      }
      do
      {
        return localObject;
        localObject = paramMntype;
      } while (lists.memq(paramObject, paramMntype.fields) != Boolean.FALSE);
      paramMntype = (condition.Mntype)paramMntype.supertype;
    }
  }
  
  static Object conditionTypes(Object paramObject)
  {
    paramObject = ((condition)paramObject).type$Mnfield$Mnalist;
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
  
  public static condition extractCondition(condition paramcondition, condition.Mntype paramMntype)
  {
    Object localObject2 = new frame();
    ((frame)localObject2).type = paramMntype;
    Object localObject1 = srfi1.find(((frame)localObject2).lambda$Fn1, paramcondition.type$Mnfield$Mnalist);
    if (localObject1 == Boolean.FALSE) {
      misc.error$V("extract-condition: invalid condition type", new Object[] { paramcondition, ((frame)localObject2).type });
    }
    condition.Mntype localMntype = ((frame)localObject2).type;
    paramcondition = ((frame)localObject2).type.all$Mnfields;
    paramMntype = LList.Empty;
    for (;;)
    {
      if (paramcondition == LList.Empty) {
        return new condition(LList.list1(lists.cons(localMntype, LList.reverseInPlace(paramMntype))));
      }
      try
      {
        localObject2 = (Pair)paramcondition;
        paramcondition = ((Pair)localObject2).getCdr();
        paramMntype = Pair.make(lists.assq(((Pair)localObject2).getCar(), lists.cdr.apply1(localObject1)), paramMntype);
      }
      catch (ClassCastException paramMntype)
      {
        throw new WrongType(paramMntype, "arg0", -2, paramcondition);
      }
    }
  }
  
  public static boolean isCondition(Object paramObject)
  {
    return paramObject instanceof condition;
  }
  
  public static boolean isConditionHasType(Object paramObject, condition.Mntype paramMntype)
  {
    paramObject = conditionTypes(paramObject);
    for (;;)
    {
      Object localObject = lists.car.apply1(paramObject);
      try
      {
        condition.Mntype localMntype = (condition.Mntype)localObject;
        boolean bool = isConditionSubtype(localMntype, paramMntype);
        if (bool) {
          return bool;
        }
        paramObject = lists.cdr.apply1(paramObject);
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "condition-subtype?", 0, localObject);
      }
    }
  }
  
  static boolean isConditionSubtype(condition.Mntype paramMntype1, condition.Mntype paramMntype2)
  {
    for (;;)
    {
      if (paramMntype1 == Boolean.FALSE) {
        return false;
      }
      if (paramMntype1 == paramMntype2) {
        return true;
      }
      paramMntype1 = (condition.Mntype)paramMntype1.supertype;
    }
  }
  
  public static boolean isConditionType(Object paramObject)
  {
    return paramObject instanceof condition.Mntype;
  }
  
  static boolean isError(Object paramObject)
  {
    boolean bool2 = isCondition(paramObject);
    boolean bool1 = bool2;
    Object localObject;
    if (bool2) {
      localObject = $Amerror;
    }
    try
    {
      condition.Mntype localMntype = (condition.Mntype)localObject;
      bool1 = isConditionHasType(paramObject, localMntype);
      return bool1;
    }
    catch (ClassCastException paramObject)
    {
      throw new WrongType((ClassCastException)paramObject, "condition-has-type?", 1, localObject);
    }
  }
  
  static boolean isMessageCondition(Object paramObject)
  {
    boolean bool2 = isCondition(paramObject);
    boolean bool1 = bool2;
    Object localObject;
    if (bool2) {
      localObject = $Ammessage;
    }
    try
    {
      condition.Mntype localMntype = (condition.Mntype)localObject;
      bool1 = isConditionHasType(paramObject, localMntype);
      return bool1;
    }
    catch (ClassCastException paramObject)
    {
      throw new WrongType((ClassCastException)paramObject, "condition-has-type?", 1, localObject);
    }
  }
  
  static boolean isSeriousCondition(Object paramObject)
  {
    boolean bool2 = isCondition(paramObject);
    boolean bool1 = bool2;
    Object localObject;
    if (bool2) {
      localObject = $Amserious;
    }
    try
    {
      condition.Mntype localMntype = (condition.Mntype)localObject;
      bool1 = isConditionHasType(paramObject, localMntype);
      return bool1;
    }
    catch (ClassCastException paramObject)
    {
      throw new WrongType((ClassCastException)paramObject, "condition-has-type?", 1, localObject);
    }
  }
  
  public static Object lambda1label(Object paramObject)
  {
    if (lists.isNull(paramObject)) {
      return LList.Empty;
    }
    return lists.cons(lists.cons(lists.car.apply1(paramObject), lists.cadr.apply1(paramObject)), lambda1label(lists.cddr.apply1(paramObject)));
  }
  
  public static condition makeCompoundCondition$V(Object paramObject, Object[] paramArrayOfObject)
  {
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    Apply localApply = Scheme.apply;
    append localappend = append.append;
    paramObject = lists.cons(paramObject, paramArrayOfObject);
    paramArrayOfObject = LList.Empty;
    for (;;)
    {
      if (paramObject == LList.Empty) {
        return new condition(localApply.apply2(localappend, LList.reverseInPlace(paramArrayOfObject)));
      }
      try
      {
        Pair localPair = (Pair)paramObject;
        paramObject = localPair.getCdr();
        paramArrayOfObject = Pair.make(Scheme.applyToArgs.apply2(condition$Mntype$Mnfield$Mnalist, localPair.getCar()), paramArrayOfObject);
      }
      catch (ClassCastException paramArrayOfObject)
      {
        throw new WrongType(paramArrayOfObject, "arg0", -2, paramObject);
      }
    }
  }
  
  public static condition makeCondition$V(condition.Mntype paramMntype, Object[] paramArrayOfObject)
  {
    Object localObject2 = lambda1label(LList.makeList(paramArrayOfObject, 0));
    IsEq localIsEq = Scheme.isEq;
    Object localObject3 = paramMntype.all$Mnfields;
    Object localObject1 = LList.Empty;
    paramArrayOfObject = (Object[])localObject2;
    for (;;)
    {
      if (paramArrayOfObject == LList.Empty)
      {
        if (srfi1.lset$Eq$V(localIsEq, new Object[] { localObject3, LList.reverseInPlace(localObject1) }) == Boolean.FALSE) {
          misc.error$V("condition fields don't match condition type", new Object[0]);
        }
        return new condition(LList.list1(lists.cons(paramMntype, localObject2)));
      }
      try
      {
        Pair localPair = (Pair)paramArrayOfObject;
        paramArrayOfObject = localPair.getCdr();
        localObject1 = Pair.make(lists.car.apply1(localPair.getCar()), localObject1);
      }
      catch (ClassCastException paramMntype)
      {
        throw new WrongType(paramMntype, "arg0", -2, paramArrayOfObject);
      }
    }
  }
  
  public static condition.Mntype makeConditionType(Symbol paramSymbol, condition.Mntype paramMntype, Object paramObject)
  {
    if (!lists.isNull(srfi1.lsetIntersection$V(Scheme.isEq, paramMntype.all$Mnfields, new Object[] { paramObject }))) {
      misc.error$V("duplicate field name", new Object[0]);
    }
    return new condition.Mntype(paramSymbol, paramMntype, paramObject, append.append$V(new Object[] { paramMntype.all$Mnfields, paramObject }));
  }
  
  /* Error */
  public static condition typeFieldAlist$To$Condition(Object paramObject)
  {
    // Byte code:
    //   0: getstatic 152	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   3: astore_1
    //   4: aload_0
    //   5: astore_2
    //   6: aload_2
    //   7: getstatic 152	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   10: if_acmpne +15 -> 25
    //   13: new 261	gnu/kawa/slib/condition
    //   16: dup
    //   17: aload_1
    //   18: invokestatic 318	gnu/lists/LList:reverseInPlace	(Ljava/lang/Object;)Lgnu/lists/LList;
    //   21: invokespecial 426	gnu/kawa/slib/condition:<init>	(Ljava/lang/Object;)V
    //   24: areturn
    //   25: aload_2
    //   26: checkcast 154	gnu/lists/Pair
    //   29: astore_3
    //   30: aload_3
    //   31: invokevirtual 336	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   34: astore 4
    //   36: aload_3
    //   37: invokevirtual 339	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   40: astore 6
    //   42: getstatic 306	kawa/lib/lists:car	Lgnu/expr/GenericProc;
    //   45: aload 6
    //   47: invokevirtual 311	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   50: astore 7
    //   52: getstatic 306	kawa/lib/lists:car	Lgnu/expr/GenericProc;
    //   55: aload 6
    //   57: invokevirtual 311	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   60: checkcast 6	gnu/kawa/slib/condition$Mntype
    //   63: getfield 321	gnu/kawa/slib/condition$Mntype:all$Mnfields	Ljava/lang/Object;
    //   66: astore_3
    //   67: getstatic 152	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   70: astore_2
    //   71: aload_3
    //   72: getstatic 152	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   75: if_acmpne +23 -> 98
    //   78: aload 7
    //   80: aload_2
    //   81: invokestatic 318	gnu/lists/LList:reverseInPlace	(Ljava/lang/Object;)Lgnu/lists/LList;
    //   84: invokestatic 420	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   87: aload_1
    //   88: invokestatic 158	gnu/lists/Pair:make	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   91: astore_1
    //   92: aload 4
    //   94: astore_2
    //   95: goto -89 -> 6
    //   98: aload_3
    //   99: checkcast 154	gnu/lists/Pair
    //   102: astore 8
    //   104: aload 8
    //   106: invokevirtual 336	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   109: astore 5
    //   111: aload 8
    //   113: invokevirtual 339	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   116: astore 8
    //   118: aload 8
    //   120: getstatic 314	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   123: aload 6
    //   125: invokevirtual 311	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   128: invokestatic 429	kawa/lib/lists:assq	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   131: astore_3
    //   132: aload_3
    //   133: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   136: if_acmpeq +15 -> 151
    //   139: aload_3
    //   140: aload_2
    //   141: invokestatic 158	gnu/lists/Pair:make	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   144: astore_2
    //   145: aload 5
    //   147: astore_3
    //   148: goto -77 -> 71
    //   151: aload 8
    //   153: aload_0
    //   154: aload 8
    //   156: invokestatic 390	gnu/kawa/slib/conditions:typeFieldAlistRef	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   159: invokestatic 420	kawa/lib/lists:cons	(Ljava/lang/Object;Ljava/lang/Object;)Lgnu/lists/Pair;
    //   162: astore_3
    //   163: goto -24 -> 139
    //   166: astore_0
    //   167: new 363	gnu/mapping/WrongType
    //   170: dup
    //   171: aload_0
    //   172: ldc_w 370
    //   175: bipush -2
    //   177: aload_2
    //   178: invokespecial 368	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   181: athrow
    //   182: astore_0
    //   183: new 363	gnu/mapping/WrongType
    //   186: dup
    //   187: aload_0
    //   188: ldc_w 370
    //   191: bipush -2
    //   193: aload_3
    //   194: invokespecial 368	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   197: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	198	0	paramObject	Object
    //   3	89	1	localObject1	Object
    //   5	173	2	localObject2	Object
    //   29	165	3	localObject3	Object
    //   34	59	4	localObject4	Object
    //   109	37	5	localObject5	Object
    //   40	84	6	localObject6	Object
    //   50	29	7	localObject7	Object
    //   102	53	8	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   25	30	166	java/lang/ClassCastException
    //   98	104	182	java/lang/ClassCastException
  }
  
  static Object typeFieldAlistRef(Object paramObject1, Object paramObject2)
  {
    for (;;)
    {
      if (lists.isNull(paramObject1)) {
        return misc.error$V("type-field-alist-ref: field not found", new Object[] { paramObject1, paramObject2 });
      }
      Object localObject = lists.assq(paramObject2, lists.cdr.apply1(lists.car.apply1(paramObject1)));
      if (localObject != Boolean.FALSE) {
        return lists.cdr.apply1(localObject);
      }
      paramObject1 = lists.cdr.apply1(paramObject1);
    }
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 2: 
      if (isConditionType(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 4: 
      if (isCondition(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    return typeFieldAlist$To$Condition(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    switch (paramModuleMethod.selector)
    {
    case 8: 
    default: 
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    try
    {
      paramModuleMethod = (condition.Mntype)paramObject2;
      if (isConditionHasType(paramObject1, paramModuleMethod)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (condition)paramObject1;
        return conditionRef(paramModuleMethod, paramObject2);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "condition-ref", 1, paramObject1);
      }
      try
      {
        paramModuleMethod = (condition)paramObject1;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "extract-condition", 1, paramObject1);
      }
      try
      {
        paramObject1 = (condition.Mntype)paramObject2;
        return extractCondition(paramModuleMethod, (condition.Mntype)paramObject1);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "extract-condition", 2, paramObject2);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "condition-has-type?", 2, paramObject2);
    }
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    if (paramModuleMethod.selector == 3) {}
    try
    {
      paramModuleMethod = (Symbol)paramObject1;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-condition-type", 1, paramObject1);
    }
    try
    {
      paramObject1 = (condition.Mntype)paramObject2;
      return makeConditionType(paramModuleMethod, (condition.Mntype)paramObject1, paramObject3);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-condition-type", 2, paramObject2);
    }
    return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
  }
  
  /* Error */
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 509	gnu/expr/ModuleMethod:selector	I
    //   4: tableswitch	default:+32->36, 5:+39->43, 6:+32->36, 7:+32->36, 8:+90->94
    //   36: aload_0
    //   37: aload_1
    //   38: aload_2
    //   39: invokespecial 533	gnu/expr/ModuleBody:applyN	(Lgnu/expr/ModuleMethod;[Ljava/lang/Object;)Ljava/lang/Object;
    //   42: areturn
    //   43: aload_2
    //   44: iconst_0
    //   45: aaload
    //   46: astore_1
    //   47: aload_1
    //   48: checkcast 6	gnu/kawa/slib/condition$Mntype
    //   51: astore_3
    //   52: aload_2
    //   53: arraylength
    //   54: iconst_1
    //   55: isub
    //   56: istore 4
    //   58: iload 4
    //   60: anewarray 95	java/lang/Object
    //   63: astore_1
    //   64: iload 4
    //   66: iconst_1
    //   67: isub
    //   68: istore 4
    //   70: iload 4
    //   72: ifge +9 -> 81
    //   75: aload_3
    //   76: aload_1
    //   77: invokestatic 535	gnu/kawa/slib/conditions:makeCondition$V	(Lgnu/kawa/slib/condition$Mntype;[Ljava/lang/Object;)Lgnu/kawa/slib/condition;
    //   80: areturn
    //   81: aload_1
    //   82: iload 4
    //   84: aload_2
    //   85: iload 4
    //   87: iconst_1
    //   88: iadd
    //   89: aaload
    //   90: aastore
    //   91: goto -27 -> 64
    //   94: aload_2
    //   95: iconst_0
    //   96: aaload
    //   97: astore_1
    //   98: aload_2
    //   99: arraylength
    //   100: iconst_1
    //   101: isub
    //   102: istore 4
    //   104: iload 4
    //   106: anewarray 95	java/lang/Object
    //   109: astore_3
    //   110: iload 4
    //   112: iconst_1
    //   113: isub
    //   114: istore 4
    //   116: iload 4
    //   118: ifge +9 -> 127
    //   121: aload_1
    //   122: aload_3
    //   123: invokestatic 537	gnu/kawa/slib/conditions:makeCompoundCondition$V	(Ljava/lang/Object;[Ljava/lang/Object;)Lgnu/kawa/slib/condition;
    //   126: areturn
    //   127: aload_3
    //   128: iload 4
    //   130: aload_2
    //   131: iload 4
    //   133: iconst_1
    //   134: iadd
    //   135: aaload
    //   136: aastore
    //   137: goto -27 -> 110
    //   140: astore_2
    //   141: new 363	gnu/mapping/WrongType
    //   144: dup
    //   145: aload_2
    //   146: ldc -78
    //   148: iconst_1
    //   149: aload_1
    //   150: invokespecial 368	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   153: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	154	0	this	conditions
    //   0	154	1	paramModuleMethod	ModuleMethod
    //   0	154	2	paramArrayOfObject	Object[]
    //   51	77	3	localObject	Object
    //   56	79	4	i	int
    // Exception table:
    //   from	to	target	type
    //   47	52	140	java/lang/ClassCastException
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 10: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 4: 
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
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    case 8: 
    default: 
      i = super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    case 9: 
    case 7: 
      do
      {
        do
        {
          return i;
        } while (!(paramObject1 instanceof condition));
        paramCallContext.value1 = paramObject1;
        if (!(paramObject2 instanceof condition.Mntype)) {
          return -786430;
        }
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      } while (!(paramObject1 instanceof condition));
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    }
    paramCallContext.value1 = paramObject1;
    if (!(paramObject2 instanceof condition.Mntype)) {
      return -786430;
    }
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 3)
    {
      if (!(paramObject1 instanceof Symbol)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof condition.Mntype)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    }
    return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 6: 
    case 7: 
    default: 
      return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
    case 8: 
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
  
  /* Error */
  public final void run(CallContext paramCallContext)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 582	gnu/mapping/CallContext:consumer	Lgnu/lists/Consumer;
    //   4: astore_1
    //   5: new 6	gnu/kawa/slib/condition$Mntype
    //   8: dup
    //   9: getstatic 237	gnu/kawa/slib/conditions:Lit0	Lgnu/mapping/SimpleSymbol;
    //   12: getstatic 396	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   15: getstatic 152	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   18: getstatic 152	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   21: invokespecial 500	gnu/kawa/slib/condition$Mntype:<init>	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   24: putstatic 584	gnu/kawa/slib/conditions:$Amcondition	Ljava/lang/Object;
    //   27: getstatic 233	gnu/kawa/slib/conditions:Lit1	Lgnu/mapping/SimpleSymbol;
    //   30: astore_2
    //   31: getstatic 584	gnu/kawa/slib/conditions:$Amcondition	Ljava/lang/Object;
    //   34: astore_1
    //   35: aload_1
    //   36: checkcast 6	gnu/kawa/slib/condition$Mntype
    //   39: astore_3
    //   40: aload_2
    //   41: aload_3
    //   42: getstatic 229	gnu/kawa/slib/conditions:Lit2	Lgnu/lists/PairWithPosition;
    //   45: invokestatic 527	gnu/kawa/slib/conditions:makeConditionType	(Lgnu/mapping/Symbol;Lgnu/kawa/slib/condition$Mntype;Ljava/lang/Object;)Lgnu/kawa/slib/condition$Mntype;
    //   48: putstatic 375	gnu/kawa/slib/conditions:$Ammessage	Ljava/lang/Object;
    //   51: getstatic 226	gnu/kawa/slib/conditions:Lit3	Lgnu/mapping/SimpleSymbol;
    //   54: astore_2
    //   55: getstatic 584	gnu/kawa/slib/conditions:$Amcondition	Ljava/lang/Object;
    //   58: astore_1
    //   59: aload_1
    //   60: checkcast 6	gnu/kawa/slib/condition$Mntype
    //   63: astore_3
    //   64: aload_2
    //   65: aload_3
    //   66: getstatic 152	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   69: invokestatic 527	gnu/kawa/slib/conditions:makeConditionType	(Lgnu/mapping/Symbol;Lgnu/kawa/slib/condition$Mntype;Ljava/lang/Object;)Lgnu/kawa/slib/condition$Mntype;
    //   72: putstatic 446	gnu/kawa/slib/conditions:$Amserious	Ljava/lang/Object;
    //   75: getstatic 222	gnu/kawa/slib/conditions:Lit4	Lgnu/mapping/SimpleSymbol;
    //   78: astore_2
    //   79: getstatic 446	gnu/kawa/slib/conditions:$Amserious	Ljava/lang/Object;
    //   82: astore_1
    //   83: aload_1
    //   84: checkcast 6	gnu/kawa/slib/condition$Mntype
    //   87: astore_3
    //   88: aload_2
    //   89: aload_3
    //   90: getstatic 152	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   93: invokestatic 527	gnu/kawa/slib/conditions:makeConditionType	(Lgnu/mapping/Symbol;Lgnu/kawa/slib/condition$Mntype;Ljava/lang/Object;)Lgnu/kawa/slib/condition$Mntype;
    //   96: putstatic 440	gnu/kawa/slib/conditions:$Amerror	Ljava/lang/Object;
    //   99: return
    //   100: astore_2
    //   101: new 363	gnu/mapping/WrongType
    //   104: dup
    //   105: aload_2
    //   106: ldc -60
    //   108: iconst_1
    //   109: aload_1
    //   110: invokespecial 368	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   113: athrow
    //   114: astore_2
    //   115: new 363	gnu/mapping/WrongType
    //   118: dup
    //   119: aload_2
    //   120: ldc -60
    //   122: iconst_1
    //   123: aload_1
    //   124: invokespecial 368	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   127: athrow
    //   128: astore_2
    //   129: new 363	gnu/mapping/WrongType
    //   132: dup
    //   133: aload_2
    //   134: ldc -60
    //   136: iconst_1
    //   137: aload_1
    //   138: invokespecial 368	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   141: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	142	0	this	conditions
    //   0	142	1	paramCallContext	CallContext
    //   30	59	2	localSimpleSymbol	SimpleSymbol
    //   100	6	2	localClassCastException1	ClassCastException
    //   114	6	2	localClassCastException2	ClassCastException
    //   128	6	2	localClassCastException3	ClassCastException
    //   39	51	3	localMntype	condition.Mntype
    // Exception table:
    //   from	to	target	type
    //   35	40	100	java/lang/ClassCastException
    //   59	64	114	java/lang/ClassCastException
    //   83	88	128	java/lang/ClassCastException
  }
  
  public class frame
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn1;
    condition.Mntype type;
    
    public frame()
    {
      this$1 = new ModuleMethod(this, 1, null, 4097);
      this$1.setProperty("source-location", "conditions.scm:166");
      this.lambda$Fn1 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 1)
      {
        if (lambda2(paramObject)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    boolean lambda2(Object paramObject)
    {
      paramObject = lists.car.apply1(paramObject);
      try
      {
        condition.Mntype localMntype = (condition.Mntype)paramObject;
        return conditions.isConditionSubtype(localMntype, this.type);
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "condition-subtype?", 0, paramObject);
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


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\conditions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */