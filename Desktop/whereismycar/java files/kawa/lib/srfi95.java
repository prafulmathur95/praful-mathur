package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.RealNum;
import kawa.standard.Scheme;
import kawa.standard.append;

public class srfi95
  extends ModuleBody
{
  public static final ModuleMethod $Pcsort$Mnlist;
  public static final ModuleMethod $Pcsort$Mnvector;
  public static final ModuleMethod $Pcvector$Mnsort$Ex;
  public static final srfi95 $instance;
  static final IntNum Lit0;
  static final IntNum Lit1;
  static final SimpleSymbol Lit10;
  static final SimpleSymbol Lit11;
  static final SimpleSymbol Lit12 = (SimpleSymbol)new SimpleSymbol("sort").readResolve();
  static final IntNum Lit2;
  static final IntNum Lit3;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  static final ModuleMethod identity;
  public static final ModuleMethod merge;
  public static final ModuleMethod merge$Ex;
  public static final ModuleMethod sort;
  public static final ModuleMethod sort$Ex;
  public static final ModuleMethod sorted$Qu;
  
  public static Object $PcSortList(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    localframe0 = new frame0();
    localframe0.seq = paramObject1;
    localframe0.less$Qu = paramObject2;
    localframe0.keyer = Special.undefined;
    if (paramObject3 != Boolean.FALSE)
    {
      paramObject1 = lists.car;
      localframe0.keyer = paramObject1;
      if (paramObject3 == Boolean.FALSE) {
        break label180;
      }
      paramObject1 = localframe0.seq;
      if (!lists.isNull(paramObject1)) {
        break label109;
      }
      paramObject1 = localframe0.seq;
    }
    for (;;)
    {
      try
      {
        paramObject2 = (LList)paramObject1;
        localframe0.seq = localframe0.lambda2step(Integer.valueOf(lists.length((LList)paramObject2)));
        paramObject1 = localframe0.seq;
        if (lists.isNull(paramObject1))
        {
          return localframe0.seq;
          paramObject1 = identity;
          break;
        }
        try
        {
          label109:
          paramObject2 = (Pair)paramObject1;
          lists.setCar$Ex((Pair)paramObject2, lists.cons(Scheme.applyToArgs.apply2(paramObject3, lists.car.apply1(paramObject1)), lists.car.apply1(paramObject1)));
          paramObject1 = lists.cdr.apply1(paramObject1);
        }
        catch (ClassCastException paramObject2)
        {
          try
          {
            label180:
            paramObject2 = (LList)paramObject1;
            return localframe0.lambda2step(Integer.valueOf(lists.length((LList)paramObject2)));
          }
          catch (ClassCastException paramObject2)
          {
            throw new WrongType((ClassCastException)paramObject2, "length", 1, paramObject1);
          }
          paramObject2 = paramObject2;
          throw new WrongType((ClassCastException)paramObject2, "set-car!", 1, paramObject1);
        }
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "length", 1, paramObject1);
      }
      try
      {
        paramObject2 = (Pair)paramObject1;
        lists.setCar$Ex((Pair)paramObject2, lists.cdar.apply1(paramObject1));
        paramObject1 = lists.cdr.apply1(paramObject1);
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "set-car!", 1, paramObject1);
      }
    }
    paramObject1 = localframe0.seq;
  }
  
  public static void $PcSortVector(Sequence paramSequence, Object paramObject)
  {
    $PcSortVector(paramSequence, paramObject, Boolean.FALSE);
  }
  
  public static void $PcSortVector(Sequence paramSequence, Object paramObject1, Object paramObject2)
  {
    FVector localFVector = vectors.makeVector(paramSequence.size());
    paramObject1 = $PcSortList(rank$Mn1Array$To$List(paramSequence), paramObject1, paramObject2);
    paramSequence = Lit3;
    while (!lists.isNull(paramObject1)) {
      try
      {
        int i = ((Number)paramSequence).intValue();
        vectors.vectorSet$Ex(localFVector, i, lists.car.apply1(paramObject1));
        paramObject1 = lists.cdr.apply1(paramObject1);
        paramSequence = AddOp.$Pl.apply2(paramSequence, Lit2);
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "vector-set!", 2, paramSequence);
      }
    }
  }
  
  public static Object $PcVectorSort$Ex(Sequence paramSequence, Object paramObject1, Object paramObject2)
  {
    paramObject2 = $PcSortList(rank$Mn1Array$To$List(paramSequence), paramObject1, paramObject2);
    for (paramObject1 = Lit3; !lists.isNull(paramObject2); paramObject1 = AddOp.$Pl.apply2(paramObject1, Lit2))
    {
      paramSequence.set(((Number)paramObject1).intValue(), lists.car.apply1(paramObject2));
      paramObject2 = lists.cdr.apply1(paramObject2);
    }
    return paramSequence;
  }
  
  static
  {
    Lit11 = (SimpleSymbol)new SimpleSymbol("%sort-vector").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("%vector-sort!").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("sort!").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("%sort-list").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("merge!").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("merge").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("sorted?").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("identity").readResolve();
    Lit3 = IntNum.make(0);
    Lit2 = IntNum.make(1);
    Lit1 = IntNum.make(2);
    Lit0 = IntNum.make(-1);
    $instance = new srfi95();
    srfi95 localsrfi95 = $instance;
    identity = new ModuleMethod(localsrfi95, 1, Lit4, 4097);
    sorted$Qu = new ModuleMethod(localsrfi95, 2, Lit5, 12290);
    merge = new ModuleMethod(localsrfi95, 4, Lit6, 16387);
    merge$Ex = new ModuleMethod(localsrfi95, 6, Lit7, 16387);
    $Pcsort$Mnlist = new ModuleMethod(localsrfi95, 8, Lit8, 12291);
    sort$Ex = new ModuleMethod(localsrfi95, 9, Lit9, 12290);
    $Pcvector$Mnsort$Ex = new ModuleMethod(localsrfi95, 11, Lit10, 12291);
    $Pcsort$Mnvector = new ModuleMethod(localsrfi95, 12, Lit11, 12290);
    sort = new ModuleMethod(localsrfi95, 14, Lit12, 12290);
    $instance.run();
  }
  
  public srfi95()
  {
    ModuleInfo.register(this);
  }
  
  static Object identity(Object paramObject)
  {
    return paramObject;
  }
  
  public static Object isSorted(Object paramObject1, Object paramObject2)
  {
    return isSorted(paramObject1, paramObject2, identity);
  }
  
  public static Object isSorted(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    if (lists.isNull(paramObject1)) {
      return Boolean.TRUE;
    }
    if ((paramObject1 instanceof Sequence)) {}
    for (;;)
    {
      try
      {
        localObject3 = (Sequence)paramObject1;
        int j = ((Sequence)localObject3).size() - 1;
        if (j <= 1)
        {
          i = 1;
          if (i == 0) {
            continue;
          }
          if (i != 0) {
            return Boolean.TRUE;
          }
        }
        else
        {
          i = 0;
          continue;
        }
        return Boolean.FALSE;
        paramObject1 = Integer.valueOf(j - 1);
        localObject1 = Scheme.applyToArgs.apply2(paramObject3, ((Sequence)localObject3).get(j));
      }
      catch (ClassCastException paramObject2)
      {
        try
        {
          int i;
          Object localObject2;
          boolean bool;
          Object localObject3 = Boolean.FALSE;
          if (localObject1 != localObject3)
          {
            i = 1;
            i = i + 1 & 0x1;
            if (i != 0)
            {
              paramObject1 = lists.cdr.apply1(paramObject1);
              localObject1 = localObject2;
            }
          }
          else
          {
            i = 0;
            continue;
          }
          if (i != 0) {
            return Boolean.TRUE;
          }
          return Boolean.FALSE;
        }
        catch (ClassCastException paramObject1)
        {
          Object localObject1;
          throw new WrongType((ClassCastException)paramObject1, "x", -2, localObject1);
        }
        paramObject2 = paramObject2;
        throw new WrongType((ClassCastException)paramObject2, "arr", -2, paramObject1);
      }
      try
      {
        localObject2 = LangObjType.coerceRealNum(paramObject1);
        bool = numbers.isNegative((RealNum)localObject2);
        if (bool)
        {
          if (bool) {
            return Boolean.TRUE;
          }
          return Boolean.FALSE;
        }
        localObject2 = Scheme.applyToArgs;
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "negative?", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject1).intValue();
        localObject2 = ((Procedure)localObject2).apply2(paramObject3, ((Sequence)localObject3).get(i));
        localObject1 = Scheme.applyToArgs.apply3(paramObject2, localObject2, localObject1);
        if (localObject1 != Boolean.FALSE)
        {
          paramObject1 = AddOp.$Pl.apply2(Lit0, paramObject1);
          localObject1 = localObject2;
        }
        else
        {
          return localObject1;
        }
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "gnu.lists.Sequence.get(int)", 2, paramObject1);
      }
    }
    if (lists.isNull(lists.cdr.apply1(paramObject1))) {
      return Boolean.TRUE;
    }
    localObject1 = Scheme.applyToArgs.apply2(paramObject3, lists.car.apply1(paramObject1));
    paramObject1 = lists.cdr.apply1(paramObject1);
    bool = lists.isNull(paramObject1);
    if (bool)
    {
      if (bool) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    localObject2 = Scheme.applyToArgs.apply2(paramObject3, lists.car.apply1(paramObject1));
    localObject1 = Scheme.applyToArgs.apply3(paramObject2, localObject2, localObject1);
  }
  
  public static Object merge(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return merge(paramObject1, paramObject2, paramObject3, identity);
  }
  
  public static Object merge(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    frame localframe = new frame();
    localframe.less$Qu = paramObject3;
    localframe.key = paramObject4;
    if (lists.isNull(paramObject1)) {
      return paramObject2;
    }
    if (lists.isNull(paramObject2)) {
      return paramObject1;
    }
    return localframe.lambda1loop(lists.car.apply1(paramObject1), Scheme.applyToArgs.apply2(localframe.key, lists.car.apply1(paramObject1)), lists.cdr.apply1(paramObject1), lists.car.apply1(paramObject2), Scheme.applyToArgs.apply2(localframe.key, lists.car.apply1(paramObject2)), lists.cdr.apply1(paramObject2));
  }
  
  public static Object merge$Ex(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return merge$Ex(paramObject1, paramObject2, paramObject3, identity);
  }
  
  public static Object merge$Ex(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    return sort$ClMerge$Ex(paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  static Object rank$Mn1Array$To$List(Sequence paramSequence)
  {
    int i = paramSequence.size() - 1;
    Object localObject = LList.Empty;
    for (;;)
    {
      if (i < 0) {
        return localObject;
      }
      localObject = lists.cons(paramSequence.get(i), localObject);
      i -= 1;
    }
  }
  
  public static Object sort(Sequence paramSequence, Object paramObject)
  {
    return sort(paramSequence, paramObject, Boolean.FALSE);
  }
  
  public static Object sort(Sequence paramSequence, Object paramObject1, Object paramObject2)
  {
    if (lists.isList(paramSequence)) {
      return $PcSortList(append.append$V(new Object[] { paramSequence, LList.Empty }), paramObject1, paramObject2);
    }
    $PcSortVector(paramSequence, paramObject1, paramObject2);
    return Values.empty;
  }
  
  static Object sort$ClMerge$Ex(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    frame1 localframe1 = new frame1();
    localframe1.less$Qu = paramObject3;
    localframe1.key = paramObject4;
    if (lists.isNull(paramObject1)) {
      return paramObject2;
    }
    if (lists.isNull(paramObject2)) {
      return paramObject1;
    }
    paramObject3 = Scheme.applyToArgs.apply2(localframe1.key, lists.car.apply1(paramObject1));
    paramObject4 = Scheme.applyToArgs.apply2(localframe1.key, lists.car.apply1(paramObject2));
    if ((Scheme.applyToArgs.apply3(localframe1.less$Qu, paramObject4, paramObject3) == Boolean.FALSE) || (lists.isNull(lists.cdr.apply1(paramObject2)))) {}
    try
    {
      paramObject3 = (Pair)paramObject2;
      lists.setCdr$Ex((Pair)paramObject3, paramObject1);
      return paramObject2;
    }
    catch (ClassCastException paramObject1)
    {
      throw new WrongType((ClassCastException)paramObject1, "set-cdr!", 1, paramObject2);
    }
    localframe1.lambda3loop(paramObject2, paramObject1, paramObject3, lists.cdr.apply1(paramObject2), Scheme.applyToArgs.apply2(localframe1.key, lists.cadr.apply1(paramObject2)));
    return paramObject2;
    if (lists.isNull(lists.cdr.apply1(paramObject1))) {}
    for (;;)
    {
      try
      {
        paramObject3 = (Pair)paramObject1;
        lists.setCdr$Ex((Pair)paramObject3, paramObject2);
        return paramObject1;
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "set-cdr!", 1, paramObject1);
      }
      localframe1.lambda3loop(paramObject1, lists.cdr.apply1(paramObject1), Scheme.applyToArgs.apply2(localframe1.key, lists.cadr.apply1(paramObject1)), paramObject2, paramObject4);
    }
  }
  
  public static Object sort$Ex(Sequence paramSequence, Object paramObject)
  {
    return sort$Ex(paramSequence, paramObject, Boolean.FALSE);
  }
  
  public static Object sort$Ex(Sequence paramSequence, Object paramObject1, Object paramObject2)
  {
    if (lists.isList(paramSequence))
    {
      paramObject2 = $PcSortList(paramSequence, paramObject1, paramObject2);
      if (paramObject2 != paramSequence) {
        for (paramObject1 = paramObject2; lists.cdr.apply1(paramObject1) != paramSequence; paramObject1 = lists.cdr.apply1(paramObject1)) {}
      }
    }
    try
    {
      localObject = (Pair)paramObject1;
      lists.setCdr$Ex((Pair)localObject, paramObject2);
      localObject = lists.car.apply1(paramSequence);
      paramObject1 = lists.cdr.apply1(paramSequence);
    }
    catch (ClassCastException paramSequence)
    {
      Object localObject;
      Pair localPair;
      throw new WrongType(paramSequence, "set-cdr!", 1, paramObject1);
    }
    try
    {
      localPair = (Pair)paramSequence;
      lists.setCar$Ex(localPair, lists.car.apply1(paramObject2));
    }
    catch (ClassCastException paramObject1)
    {
      throw new WrongType((ClassCastException)paramObject1, "set-car!", 1, paramSequence);
    }
    try
    {
      localPair = (Pair)paramSequence;
      lists.setCdr$Ex(localPair, lists.cdr.apply1(paramObject2));
    }
    catch (ClassCastException paramObject1)
    {
      throw new WrongType((ClassCastException)paramObject1, "set-cdr!", 1, paramSequence);
    }
    try
    {
      localPair = (Pair)paramObject2;
      lists.setCar$Ex(localPair, localObject);
    }
    catch (ClassCastException paramSequence)
    {
      throw new WrongType(paramSequence, "set-car!", 1, paramObject2);
    }
    try
    {
      localObject = (Pair)paramObject2;
      lists.setCdr$Ex((Pair)localObject, paramObject1);
      return paramSequence;
    }
    catch (ClassCastException paramSequence)
    {
      throw new WrongType(paramSequence, "set-cdr!", 1, paramObject2);
    }
    return $PcVectorSort$Ex(paramSequence, paramObject1, paramObject2);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    if (paramModuleMethod.selector == 1) {
      return identity(paramObject);
    }
    return super.apply1(paramModuleMethod, paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    case 2: 
      return isSorted(paramObject1, paramObject2);
    }
    try
    {
      paramModuleMethod = (Sequence)paramObject1;
      return sort$Ex(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (Sequence)paramObject1;
        $PcSortVector(paramModuleMethod, paramObject2);
        return Values.empty;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "%sort-vector", 1, paramObject1);
      }
      try
      {
        paramModuleMethod = (Sequence)paramObject1;
        return sort(paramModuleMethod, paramObject2);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "sort", 1, paramObject1);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "sort!", 1, paramObject1);
    }
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    switch (paramModuleMethod.selector)
    {
    case 3: 
    case 5: 
    case 7: 
    case 10: 
    case 13: 
    default: 
      return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
    case 2: 
      return isSorted(paramObject1, paramObject2, paramObject3);
    case 4: 
      return merge(paramObject1, paramObject2, paramObject3);
    case 6: 
      return merge$Ex(paramObject1, paramObject2, paramObject3);
    case 8: 
      return $PcSortList(paramObject1, paramObject2, paramObject3);
    }
    try
    {
      paramModuleMethod = (Sequence)paramObject1;
      return sort$Ex(paramModuleMethod, paramObject2, paramObject3);
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (Sequence)paramObject1;
        return $PcVectorSort$Ex(paramModuleMethod, paramObject2, paramObject3);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "%vector-sort!", 1, paramObject1);
      }
      try
      {
        paramModuleMethod = (Sequence)paramObject1;
        $PcSortVector(paramModuleMethod, paramObject2, paramObject3);
        return Values.empty;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "%sort-vector", 1, paramObject1);
      }
      try
      {
        paramModuleMethod = (Sequence)paramObject1;
        return sort(paramModuleMethod, paramObject2, paramObject3);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "sort", 1, paramObject1);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "sort!", 1, paramObject1);
    }
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    switch (paramModuleMethod.selector)
    {
    case 5: 
    default: 
      return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
    case 4: 
      return merge(paramObject1, paramObject2, paramObject3, paramObject4);
    }
    return merge$Ex(paramObject1, paramObject2, paramObject3, paramObject4);
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
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    default: 
      i = super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    case 14: 
    case 12: 
    case 9: 
      do
      {
        do
        {
          do
          {
            return i;
          } while (!(paramObject1 instanceof Sequence));
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } while (!(paramObject1 instanceof Sequence));
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      } while (!(paramObject1 instanceof Sequence));
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
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    case 3: 
    case 5: 
    case 7: 
    case 10: 
    case 13: 
    default: 
      i = super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
    case 14: 
    case 12: 
    case 11: 
    case 9: 
      do
      {
        do
        {
          do
          {
            do
            {
              return i;
            } while (!(paramObject1 instanceof Sequence));
            paramCallContext.value1 = paramObject1;
            paramCallContext.value2 = paramObject2;
            paramCallContext.value3 = paramObject3;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 3;
            return 0;
          } while (!(paramObject1 instanceof Sequence));
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } while (!(paramObject1 instanceof Sequence));
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      } while (!(paramObject1 instanceof Sequence));
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
    case 6: 
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
    switch (paramModuleMethod.selector)
    {
    case 5: 
    default: 
      return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
    case 6: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 4;
      return 0;
    }
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.value3 = paramObject3;
    paramCallContext.value4 = paramObject4;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 4;
    return 0;
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
  
  public class frame
    extends ModuleBody
  {
    Object key;
    Object less$Qu;
    
    public Object lambda1loop(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5, Object paramObject6)
    {
      if (Scheme.applyToArgs.apply3(this.less$Qu, paramObject5, paramObject2) != Boolean.FALSE)
      {
        if (lists.isNull(paramObject6)) {
          return lists.cons(paramObject4, lists.cons(paramObject1, paramObject3));
        }
        return lists.cons(paramObject4, lambda1loop(paramObject1, paramObject2, paramObject3, lists.car.apply1(paramObject6), Scheme.applyToArgs.apply2(this.key, lists.car.apply1(paramObject6)), lists.cdr.apply1(paramObject6)));
      }
      if (lists.isNull(paramObject3)) {
        return lists.cons(paramObject1, lists.cons(paramObject4, paramObject6));
      }
      return lists.cons(paramObject1, lambda1loop(lists.car.apply1(paramObject3), Scheme.applyToArgs.apply2(this.key, lists.car.apply1(paramObject3)), lists.cdr.apply1(paramObject3), paramObject4, paramObject5, paramObject6));
    }
  }
  
  public class frame0
    extends ModuleBody
  {
    Object keyer;
    Object less$Qu;
    Object seq;
    
    public Object lambda2step(Object paramObject)
    {
      Object localObject1;
      if (Scheme.numGrt.apply2(paramObject, srfi95.Lit1) != Boolean.FALSE)
      {
        localObject1 = DivideOp.quotient.apply2(paramObject, srfi95.Lit1);
        return srfi95.sort$ClMerge$Ex(lambda2step(localObject1), lambda2step(AddOp.$Mn.apply2(paramObject, localObject1)), this.less$Qu, this.keyer);
      }
      if (Scheme.numEqu.apply2(paramObject, srfi95.Lit1) != Boolean.FALSE)
      {
        localObject1 = lists.car.apply1(this.seq);
        localObject2 = lists.cadr.apply1(this.seq);
        paramObject = this.seq;
        this.seq = lists.cddr.apply1(this.seq);
        if (Scheme.applyToArgs.apply3(this.less$Qu, Scheme.applyToArgs.apply2(this.keyer, localObject2), Scheme.applyToArgs.apply2(this.keyer, localObject1)) == Boolean.FALSE) {}
      }
      try
      {
        localPair = (Pair)paramObject;
        lists.setCar$Ex(localPair, localObject2);
        localObject2 = lists.cdr.apply1(paramObject);
      }
      catch (ClassCastException localClassCastException1)
      {
        Pair localPair;
        throw new WrongType(localClassCastException1, "set-car!", 1, paramObject);
      }
      try
      {
        localPair = (Pair)localObject2;
        lists.setCar$Ex(localPair, localObject1);
        localObject1 = lists.cdr.apply1(paramObject);
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "set-car!", 1, localObject2);
      }
      try
      {
        localObject2 = (Pair)localObject1;
        lists.setCdr$Ex((Pair)localObject2, LList.Empty);
        return paramObject;
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "set-cdr!", 1, localClassCastException1);
      }
      if (Scheme.numEqu.apply2(paramObject, srfi95.Lit2) != Boolean.FALSE)
      {
        paramObject = this.seq;
        this.seq = lists.cdr.apply1(this.seq);
      }
      try
      {
        localObject1 = (Pair)paramObject;
        lists.setCdr$Ex((Pair)localObject1, LList.Empty);
        return paramObject;
      }
      catch (ClassCastException localClassCastException2)
      {
        throw new WrongType(localClassCastException2, "set-cdr!", 1, paramObject);
      }
      return LList.Empty;
    }
  }
  
  public class frame1
    extends ModuleBody
  {
    Object key;
    Object less$Qu;
    
    public Object lambda3loop(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5)
    {
      if (Scheme.applyToArgs.apply3(this.less$Qu, paramObject5, paramObject3) != Boolean.FALSE) {}
      try
      {
        paramObject5 = (Pair)paramObject1;
        lists.setCdr$Ex((Pair)paramObject5, paramObject4);
        if (!lists.isNull(lists.cdr.apply1(paramObject4))) {}
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "set-cdr!", 1, paramObject1);
      }
      try
      {
        paramObject1 = (Pair)paramObject4;
        lists.setCdr$Ex((Pair)paramObject1, paramObject2);
        return Values.empty;
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "set-cdr!", 1, paramObject4);
      }
      return lambda3loop(paramObject4, paramObject2, paramObject3, lists.cdr.apply1(paramObject4), Scheme.applyToArgs.apply2(this.key, lists.cadr.apply1(paramObject4)));
      try
      {
        paramObject3 = (Pair)paramObject1;
        lists.setCdr$Ex((Pair)paramObject3, paramObject2);
        if (!lists.isNull(lists.cdr.apply1(paramObject2))) {}
      }
      catch (ClassCastException paramObject2)
      {
        throw new WrongType((ClassCastException)paramObject2, "set-cdr!", 1, paramObject1);
      }
      try
      {
        paramObject1 = (Pair)paramObject2;
        lists.setCdr$Ex((Pair)paramObject1, paramObject4);
        return Values.empty;
      }
      catch (ClassCastException paramObject1)
      {
        throw new WrongType((ClassCastException)paramObject1, "set-cdr!", 1, paramObject2);
      }
      return lambda3loop(paramObject2, lists.cdr.apply1(paramObject2), Scheme.applyToArgs.apply2(this.key, lists.cadr.apply1(paramObject2)), paramObject4, paramObject5);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\srfi95.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */