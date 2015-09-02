package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.HashNode;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lib.kawa.hashtable;
import kawa.lib.kawa.hashtable.HashTable;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.rnrs.hashtables;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;

public class srfi69
  extends ModuleBody
{
  public static final srfi69 $instance;
  static final IntNum Lit0;
  static final SimpleSymbol Lit1;
  static final SimpleSymbol Lit10;
  static final SimpleSymbol Lit11;
  static final SimpleSymbol Lit12;
  static final SimpleSymbol Lit13;
  static final SimpleSymbol Lit14;
  static final SimpleSymbol Lit15;
  static final SimpleSymbol Lit16;
  static final SimpleSymbol Lit17;
  static final SimpleSymbol Lit18;
  static final SimpleSymbol Lit19 = (SimpleSymbol)new SimpleSymbol("hash-table-values").readResolve();
  static final SimpleSymbol Lit2;
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod alist$Mn$Grhash$Mntable;
  public static final ModuleMethod hash;
  public static final ModuleMethod hash$Mnby$Mnidentity;
  public static final ModuleMethod hash$Mntable$Mn$Gralist;
  public static final ModuleMethod hash$Mntable$Mncopy;
  public static final Location hash$Mntable$Mndelete$Ex;
  public static final ModuleMethod hash$Mntable$Mnequivalence$Mnfunction;
  public static final Location hash$Mntable$Mnexists$Qu;
  public static final ModuleMethod hash$Mntable$Mnfold;
  public static final ModuleMethod hash$Mntable$Mnhash$Mnfunction;
  public static final ModuleMethod hash$Mntable$Mnkeys;
  public static final ModuleMethod hash$Mntable$Mnmerge$Ex;
  public static final ModuleMethod hash$Mntable$Mnref;
  public static final ModuleMethod hash$Mntable$Mnref$Sldefault;
  public static final Location hash$Mntable$Mnset$Ex;
  public static final Location hash$Mntable$Mnsize;
  public static final ModuleMethod hash$Mntable$Mnupdate$Ex;
  public static final ModuleMethod hash$Mntable$Mnupdate$Ex$Sldefault;
  public static final ModuleMethod hash$Mntable$Mnvalues;
  public static final ModuleMethod hash$Mntable$Mnwalk;
  public static final Location hash$Mntable$Qu;
  static final ModuleMethod lambda$Fn1;
  static final ModuleMethod lambda$Fn2;
  static final ModuleMethod lambda$Fn3;
  public static final ModuleMethod make$Mnhash$Mntable;
  public static final ModuleMethod string$Mnci$Mnhash;
  public static final ModuleMethod string$Mnhash;
  
  static
  {
    Lit18 = (SimpleSymbol)new SimpleSymbol("hash-table-keys").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("hash-table-merge!").readResolve();
    Lit16 = (SimpleSymbol)new SimpleSymbol("hash-table-copy").readResolve();
    Lit15 = (SimpleSymbol)new SimpleSymbol("hash-table->alist").readResolve();
    Lit14 = (SimpleSymbol)new SimpleSymbol("alist->hash-table").readResolve();
    Lit13 = (SimpleSymbol)new SimpleSymbol("hash-table-fold").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("hash-table-walk").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("hash-table-update!/default").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("hash-table-update!").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("hash-table-ref/default").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("hash-table-ref").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("make-hash-table").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("hash-table-hash-function").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("hash-table-equivalence-function").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("hash-by-identity").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("hash").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("string-ci-hash").readResolve();
    Lit1 = (SimpleSymbol)new SimpleSymbol("string-hash").readResolve();
    Lit0 = IntNum.make(64);
    $instance = new srfi69();
    hash$Mntable$Qu = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Qu");
    hash$Mntable$Mnsize = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mnsize");
    hash$Mntable$Mnset$Ex = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mnset$Ex");
    hash$Mntable$Mndelete$Ex = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mndelete$Ex");
    hash$Mntable$Mnexists$Qu = StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mncontains$Qu");
    srfi69 localsrfi69 = $instance;
    string$Mnhash = new ModuleMethod(localsrfi69, 1, Lit1, 8193);
    string$Mnci$Mnhash = new ModuleMethod(localsrfi69, 3, Lit2, 8193);
    hash = new ModuleMethod(localsrfi69, 5, Lit3, 8193);
    hash$Mnby$Mnidentity = new ModuleMethod(localsrfi69, 7, Lit4, 8193);
    hash$Mntable$Mnequivalence$Mnfunction = new ModuleMethod(localsrfi69, 9, Lit5, 4097);
    hash$Mntable$Mnhash$Mnfunction = new ModuleMethod(localsrfi69, 10, Lit6, 4097);
    make$Mnhash$Mntable = new ModuleMethod(localsrfi69, 11, Lit7, 12288);
    hash$Mntable$Mnref = new ModuleMethod(localsrfi69, 15, Lit8, 12290);
    hash$Mntable$Mnref$Sldefault = new ModuleMethod(localsrfi69, 17, Lit9, 12291);
    hash$Mntable$Mnupdate$Ex = new ModuleMethod(localsrfi69, 18, Lit10, 16387);
    hash$Mntable$Mnupdate$Ex$Sldefault = new ModuleMethod(localsrfi69, 20, Lit11, 16388);
    hash$Mntable$Mnwalk = new ModuleMethod(localsrfi69, 21, Lit12, 8194);
    hash$Mntable$Mnfold = new ModuleMethod(localsrfi69, 22, Lit13, 12291);
    ModuleMethod localModuleMethod = new ModuleMethod(localsrfi69, 23, null, 4097);
    localModuleMethod.setProperty("source-location", "srfi69.scm:166");
    lambda$Fn1 = localModuleMethod;
    alist$Mn$Grhash$Mntable = new ModuleMethod(localsrfi69, 24, Lit14, 16385);
    hash$Mntable$Mn$Gralist = new ModuleMethod(localsrfi69, 28, Lit15, 4097);
    hash$Mntable$Mncopy = new ModuleMethod(localsrfi69, 29, Lit16, 4097);
    hash$Mntable$Mnmerge$Ex = new ModuleMethod(localsrfi69, 30, Lit17, 8194);
    localModuleMethod = new ModuleMethod(localsrfi69, 31, null, 12291);
    localModuleMethod.setProperty("source-location", "srfi69.scm:183");
    lambda$Fn2 = localModuleMethod;
    hash$Mntable$Mnkeys = new ModuleMethod(localsrfi69, 32, Lit18, 4097);
    localModuleMethod = new ModuleMethod(localsrfi69, 33, null, 12291);
    localModuleMethod.setProperty("source-location", "srfi69.scm:186");
    lambda$Fn3 = localModuleMethod;
    hash$Mntable$Mnvalues = new ModuleMethod(localsrfi69, 34, Lit19, 4097);
    $instance.run();
  }
  
  public srfi69()
  {
    ModuleInfo.register(this);
  }
  
  public static hashtable.HashTable alist$To$HashTable(Object paramObject)
  {
    return alist$To$HashTable(paramObject, Scheme.isEqual);
  }
  
  public static hashtable.HashTable alist$To$HashTable(Object paramObject1, Object paramObject2)
  {
    return alist$To$HashTable(paramObject1, paramObject2, appropriateHashFunctionFor(paramObject2));
  }
  
  public static hashtable.HashTable alist$To$HashTable(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    IntNum localIntNum = Lit0;
    try
    {
      LList localLList = (LList)paramObject1;
      return alist$To$HashTable(paramObject1, paramObject2, paramObject3, numbers.max(new Object[] { localIntNum, Integer.valueOf(lists.length(localLList) * 2) }));
    }
    catch (ClassCastException paramObject2)
    {
      throw new WrongType((ClassCastException)paramObject2, "length", 1, paramObject1);
    }
  }
  
  /* Error */
  public static hashtable.HashTable alist$To$HashTable(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    // Byte code:
    //   0: aload_1
    //   1: checkcast 310	gnu/mapping/Procedure
    //   4: astore 4
    //   6: aload_2
    //   7: checkcast 310	gnu/mapping/Procedure
    //   10: astore_1
    //   11: aload_3
    //   12: checkcast 312	java/lang/Number
    //   15: invokevirtual 316	java/lang/Number:intValue	()I
    //   18: istore 5
    //   20: aload 4
    //   22: aload_1
    //   23: iload 5
    //   25: invokestatic 320	gnu/kawa/slib/srfi69:makeHashTable	(Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;I)Lkawa/lib/kawa/hashtable$HashTable;
    //   28: astore_1
    //   29: aload_0
    //   30: getstatic 324	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   33: if_acmpne +5 -> 38
    //   36: aload_1
    //   37: areturn
    //   38: aload_0
    //   39: checkcast 326	gnu/lists/Pair
    //   42: astore_2
    //   43: aload_2
    //   44: invokevirtual 329	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   47: astore_0
    //   48: aload_1
    //   49: getstatic 333	kawa/lib/lists:car	Lgnu/expr/GenericProc;
    //   52: aload_0
    //   53: invokevirtual 337	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   56: getstatic 226	gnu/kawa/slib/srfi69:lambda$Fn1	Lgnu/expr/ModuleMethod;
    //   59: getstatic 340	kawa/lib/lists:cdr	Lgnu/expr/GenericProc;
    //   62: aload_0
    //   63: invokevirtual 337	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   66: invokestatic 344	gnu/kawa/slib/srfi69:hashTableUpdate$Ex$SlDefault	(Lkawa/lib/kawa/hashtable$HashTable;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   69: aload_2
    //   70: invokevirtual 347	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   73: astore_0
    //   74: goto -45 -> 29
    //   77: astore_0
    //   78: new 304	gnu/mapping/WrongType
    //   81: dup
    //   82: aload_0
    //   83: ldc 119
    //   85: iconst_0
    //   86: aload_1
    //   87: invokespecial 308	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   90: athrow
    //   91: astore_0
    //   92: new 304	gnu/mapping/WrongType
    //   95: dup
    //   96: aload_0
    //   97: ldc 119
    //   99: iconst_1
    //   100: aload_2
    //   101: invokespecial 308	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   104: athrow
    //   105: astore_0
    //   106: new 304	gnu/mapping/WrongType
    //   109: dup
    //   110: aload_0
    //   111: ldc 119
    //   113: iconst_2
    //   114: aload_3
    //   115: invokespecial 308	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   118: athrow
    //   119: astore_1
    //   120: new 304	gnu/mapping/WrongType
    //   123: dup
    //   124: aload_1
    //   125: ldc_w 349
    //   128: bipush -2
    //   130: aload_0
    //   131: invokespecial 308	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   134: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	135	0	paramObject1	Object
    //   0	135	1	paramObject2	Object
    //   0	135	2	paramObject3	Object
    //   0	135	3	paramObject4	Object
    //   4	17	4	localProcedure	Procedure
    //   18	6	5	i	int
    // Exception table:
    //   from	to	target	type
    //   0	6	77	java/lang/ClassCastException
    //   6	11	91	java/lang/ClassCastException
    //   11	20	105	java/lang/ClassCastException
    //   38	43	119	java/lang/ClassCastException
  }
  
  static Procedure appropriateHashFunctionFor(Object paramObject)
  {
    int i;
    Object localObject;
    if (paramObject == Scheme.isEq)
    {
      i = 1;
      if (i == 0) {
        break label34;
      }
      localObject = hash$Mnby$Mnidentity;
    }
    for (;;)
    {
      if (localObject == Boolean.FALSE) {
        break label52;
      }
      return (Procedure)localObject;
      i = 0;
      break;
      label34:
      if (i != 0) {
        localObject = Boolean.TRUE;
      } else {
        localObject = Boolean.FALSE;
      }
    }
    label52:
    if (paramObject == strings.string$Eq$Qu)
    {
      i = 1;
      if (i == 0) {
        break label86;
      }
      localObject = string$Mnhash;
    }
    for (;;)
    {
      if (localObject == Boolean.FALSE) {
        break label104;
      }
      return (Procedure)localObject;
      i = 0;
      break;
      label86:
      if (i != 0) {
        localObject = Boolean.TRUE;
      } else {
        localObject = Boolean.FALSE;
      }
    }
    label104:
    if (paramObject == unicode.string$Mnci$Eq$Qu)
    {
      i = 1;
      if (i == 0) {
        break label138;
      }
      paramObject = string$Mnci$Mnhash;
    }
    for (;;)
    {
      if (paramObject == Boolean.FALSE) {
        break label156;
      }
      return (Procedure)paramObject;
      i = 0;
      break;
      label138:
      if (i != 0) {
        paramObject = Boolean.TRUE;
      } else {
        paramObject = Boolean.FALSE;
      }
    }
    label156:
    return hash;
  }
  
  public static Object hash(Object paramObject)
  {
    return hash(paramObject, null);
  }
  
  public static Object hash(Object paramObject, IntNum paramIntNum)
  {
    if (paramObject == null) {}
    for (int i = 0; paramIntNum == null; i = paramObject.hashCode()) {
      return Integer.valueOf(i);
    }
    return IntNum.modulo(IntNum.make(i), paramIntNum);
  }
  
  public static Object hashByIdentity(Object paramObject)
  {
    return hashByIdentity(paramObject, null);
  }
  
  public static Object hashByIdentity(Object paramObject, IntNum paramIntNum)
  {
    int i = System.identityHashCode(paramObject);
    if (paramIntNum == null) {
      return Integer.valueOf(i);
    }
    return IntNum.modulo(IntNum.make(i), paramIntNum);
  }
  
  public static Object hashTable$To$Alist(hashtable.HashTable paramHashTable)
  {
    return paramHashTable.toAlist();
  }
  
  public static hashtable.HashTable hashTableCopy(hashtable.HashTable paramHashTable)
  {
    return new hashtable.HashTable(paramHashTable, true);
  }
  
  public static Procedure hashTableEquivalenceFunction(hashtable.HashTable paramHashTable)
  {
    return paramHashTable.equivalenceFunction;
  }
  
  public static Object hashTableFold(hashtable.HashTable paramHashTable, Procedure paramProcedure, Object paramObject)
  {
    return paramHashTable.fold(paramProcedure, paramObject);
  }
  
  public static Procedure hashTableHashFunction(hashtable.HashTable paramHashTable)
  {
    return paramHashTable.hashFunction;
  }
  
  public static Object hashTableKeys(hashtable.HashTable paramHashTable)
  {
    return hashTableFold(paramHashTable, lambda$Fn2, LList.Empty);
  }
  
  public static void hashTableMerge$Ex(hashtable.HashTable paramHashTable1, hashtable.HashTable paramHashTable2)
  {
    paramHashTable1.putAll(paramHashTable2);
  }
  
  public static Object hashTableRef(hashtable.HashTable paramHashTable, Object paramObject)
  {
    return hashTableRef(paramHashTable, paramObject, Boolean.FALSE);
  }
  
  public static Object hashTableRef(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2)
  {
    paramHashTable = paramHashTable.getNode(paramObject1);
    if (paramHashTable == null)
    {
      if (paramObject2 != Boolean.FALSE) {
        return Scheme.applyToArgs.apply1(paramObject2);
      }
      return misc.error$V("hash-table-ref: no value associated with", new Object[] { paramObject1 });
    }
    return paramHashTable.getValue();
  }
  
  public static Object hashTableRef$SlDefault(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2)
  {
    return paramHashTable.get(paramObject1, paramObject2);
  }
  
  public static void hashTableUpdate$Ex(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2)
  {
    hashTableUpdate$Ex(paramHashTable, paramObject1, paramObject2, Boolean.FALSE);
  }
  
  public static void hashTableUpdate$Ex(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    hashtable.hashtableCheckMutable(paramHashTable);
    HashNode localHashNode = paramHashTable.getNode(paramObject1);
    if (localHashNode == null)
    {
      if (paramObject3 != Boolean.FALSE)
      {
        hashtables.hashtableSet$Ex(paramHashTable, paramObject1, Scheme.applyToArgs.apply2(paramObject2, Scheme.applyToArgs.apply1(paramObject3)));
        return;
      }
      misc.error$V("hash-table-update!: no value exists for key", new Object[] { paramObject1 });
      return;
    }
    localHashNode.setValue(Scheme.applyToArgs.apply2(paramObject2, localHashNode.getValue()));
  }
  
  public static void hashTableUpdate$Ex$SlDefault(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    hashtable.hashtableCheckMutable(paramHashTable);
    HashNode localHashNode = paramHashTable.getNode(paramObject1);
    if (localHashNode == null)
    {
      hashtables.hashtableSet$Ex(paramHashTable, paramObject1, Scheme.applyToArgs.apply2(paramObject2, paramObject3));
      return;
    }
    localHashNode.setValue(Scheme.applyToArgs.apply2(paramObject2, localHashNode.getValue()));
  }
  
  public static Object hashTableValues(hashtable.HashTable paramHashTable)
  {
    return hashTableFold(paramHashTable, lambda$Fn3, LList.Empty);
  }
  
  public static void hashTableWalk(hashtable.HashTable paramHashTable, Procedure paramProcedure)
  {
    paramHashTable.walk(paramProcedure);
  }
  
  static Object lambda1(Object paramObject)
  {
    return paramObject;
  }
  
  static Pair lambda2(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return lists.cons(paramObject1, paramObject3);
  }
  
  static Pair lambda3(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return lists.cons(paramObject2, paramObject3);
  }
  
  public static hashtable.HashTable makeHashTable()
  {
    return makeHashTable(Scheme.isEqual);
  }
  
  public static hashtable.HashTable makeHashTable(Procedure paramProcedure)
  {
    return makeHashTable(paramProcedure, appropriateHashFunctionFor(paramProcedure), 64);
  }
  
  public static hashtable.HashTable makeHashTable(Procedure paramProcedure1, Procedure paramProcedure2)
  {
    return makeHashTable(paramProcedure1, paramProcedure2, 64);
  }
  
  public static hashtable.HashTable makeHashTable(Procedure paramProcedure1, Procedure paramProcedure2, int paramInt)
  {
    return new hashtable.HashTable(paramProcedure1, paramProcedure2, paramInt);
  }
  
  public static Object stringCiHash(Object paramObject)
  {
    return stringCiHash(paramObject, null);
  }
  
  public static Object stringCiHash(Object paramObject, IntNum paramIntNum)
  {
    int i = paramObject.toString().toLowerCase().hashCode();
    if (paramIntNum == null) {
      return Integer.valueOf(i);
    }
    return IntNum.modulo(IntNum.make(i), paramIntNum);
  }
  
  public static Object stringHash(CharSequence paramCharSequence)
  {
    return stringHash(paramCharSequence, null);
  }
  
  public static Object stringHash(CharSequence paramCharSequence, IntNum paramIntNum)
  {
    int i = paramCharSequence.hashCode();
    if (paramIntNum == null) {
      return Integer.valueOf(i);
    }
    return IntNum.modulo(IntNum.make(i), paramIntNum);
  }
  
  static Object symbolHash(Symbol paramSymbol)
  {
    return symbolHash(paramSymbol, null);
  }
  
  static Object symbolHash(Symbol paramSymbol, IntNum paramIntNum)
  {
    int i = paramSymbol.hashCode();
    if (paramIntNum == null) {
      return Integer.valueOf(i);
    }
    return IntNum.modulo(IntNum.make(i), paramIntNum);
  }
  
  static Object vectorHash(Object paramObject)
  {
    return vectorHash(paramObject, null);
  }
  
  static Object vectorHash(Object paramObject, IntNum paramIntNum)
  {
    int i = paramObject.hashCode();
    if (paramIntNum == null) {
      return Integer.valueOf(i);
    }
    return IntNum.modulo(IntNum.make(i), paramIntNum);
  }
  
  public Object apply0(ModuleMethod paramModuleMethod)
  {
    if (paramModuleMethod.selector == 11) {
      return makeHashTable();
    }
    return super.apply0(paramModuleMethod);
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
      paramModuleMethod = (CharSequence)paramObject;
      return stringHash(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (hashtable.HashTable)paramObject;
        return hashTable$To$Alist(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "hash-table->alist", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (hashtable.HashTable)paramObject;
        return hashTableCopy(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "hash-table-copy", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (hashtable.HashTable)paramObject;
        return hashTableKeys(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "hash-table-keys", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (hashtable.HashTable)paramObject;
        return hashTableValues(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "hash-table-values", 1, paramObject);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "string-hash", 1, paramObject);
    }
    return stringCiHash(paramObject);
    return hash(paramObject);
    return hashByIdentity(paramObject);
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject;
      return hashTableEquivalenceFunction(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash-table-equivalence-function", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject;
      return hashTableHashFunction(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash-table-hash-function", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject;
      return makeHashTable(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-hash-table", 1, paramObject);
    }
    return lambda1(paramObject);
    return alist$To$HashTable(paramObject);
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
      paramModuleMethod = (CharSequence)paramObject1;
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (hashtable.HashTable)paramObject1;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "hash-table-merge!", 1, paramObject1);
      }
      try
      {
        paramObject1 = (hashtable.HashTable)paramObject2;
        hashTableMerge$Ex(paramModuleMethod, (hashtable.HashTable)paramObject1);
        return Values.empty;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "hash-table-merge!", 2, paramObject2);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "string-hash", 1, paramObject1);
    }
    try
    {
      paramObject1 = LangObjType.coerceIntNum(paramObject2);
      return stringHash(paramModuleMethod, (IntNum)paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "string-hash", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject2);
      return stringCiHash(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "string-ci-hash", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject2);
      return hash(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject2);
      return hashByIdentity(paramObject1, paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash-by-identity", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-hash-table", 1, paramObject1);
    }
    try
    {
      paramObject1 = (Procedure)paramObject2;
      return makeHashTable(paramModuleMethod, (Procedure)paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-hash-table", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject1;
      return hashTableRef(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash-table-ref", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject1;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash-table-walk", 1, paramObject1);
    }
    try
    {
      paramObject1 = (Procedure)paramObject2;
      hashTableWalk(paramModuleMethod, (Procedure)paramObject1);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash-table-walk", 2, paramObject2);
    }
    return alist$To$HashTable(paramObject1, paramObject2);
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
      paramModuleMethod = (Procedure)paramObject1;
    }
    catch (ClassCastException paramModuleMethod)
    {
      int i;
      throw new WrongType(paramModuleMethod, "make-hash-table", 1, paramObject1);
    }
    try
    {
      paramObject1 = (Procedure)paramObject2;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-hash-table", 2, paramObject2);
    }
    try
    {
      i = ((Number)paramObject3).intValue();
      return makeHashTable(paramModuleMethod, (Procedure)paramObject1, i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-hash-table", 3, paramObject3);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject1;
      return hashTableRef(paramModuleMethod, paramObject2, paramObject3);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash-table-ref", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject1;
      return hashTableRef$SlDefault(paramModuleMethod, paramObject2, paramObject3);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash-table-ref/default", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject1;
      hashTableUpdate$Ex(paramModuleMethod, paramObject2, paramObject3);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash-table-update!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject1;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash-table-fold", 1, paramObject1);
    }
    try
    {
      paramObject1 = (Procedure)paramObject2;
      return hashTableFold(paramModuleMethod, (Procedure)paramObject1, paramObject3);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash-table-fold", 2, paramObject2);
    }
    return alist$To$HashTable(paramObject1, paramObject2, paramObject3);
    return lambda2(paramObject1, paramObject2, paramObject3);
    return lambda3(paramObject1, paramObject2, paramObject3);
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject1;
      hashTableUpdate$Ex(paramModuleMethod, paramObject2, paramObject3, paramObject4);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash-table-update!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject1;
      hashTableUpdate$Ex$SlDefault(paramModuleMethod, paramObject2, paramObject3, paramObject4);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hash-table-update!/default", 1, paramObject1);
    }
    return alist$To$HashTable(paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 11)
    {
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    }
    return super.match0(paramModuleMethod, paramCallContext);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    default: 
      i = super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    return i;
                  } while (!(paramObject instanceof hashtable.HashTable));
                  paramCallContext.value1 = paramObject;
                  paramCallContext.proc = paramModuleMethod;
                  paramCallContext.pc = 1;
                  return 0;
                } while (!(paramObject instanceof hashtable.HashTable));
                paramCallContext.value1 = paramObject;
                paramCallContext.proc = paramModuleMethod;
                paramCallContext.pc = 1;
                return 0;
              } while (!(paramObject instanceof hashtable.HashTable));
              paramCallContext.value1 = paramObject;
              paramCallContext.proc = paramModuleMethod;
              paramCallContext.pc = 1;
              return 0;
            } while (!(paramObject instanceof hashtable.HashTable));
            paramCallContext.value1 = paramObject;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 1;
            return 0;
            paramCallContext.value1 = paramObject;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 1;
            return 0;
            paramCallContext.value1 = paramObject;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 1;
            return 0;
          } while (!(paramObject instanceof Procedure));
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } while (!(paramObject instanceof hashtable.HashTable));
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      } while (!(paramObject instanceof hashtable.HashTable));
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    } while (!(paramObject instanceof CharSequence));
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
    default: 
      i = super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return i;
            } while (!(paramObject1 instanceof hashtable.HashTable));
            paramCallContext.value1 = paramObject1;
            if (!(paramObject2 instanceof hashtable.HashTable)) {
              return -786430;
            }
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
            paramCallContext.value1 = paramObject1;
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } while (!(paramObject1 instanceof hashtable.HashTable));
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Procedure)) {
            return -786430;
          }
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } while (!(paramObject1 instanceof hashtable.HashTable));
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      } while (!(paramObject1 instanceof Procedure));
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Procedure)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
      paramCallContext.value1 = paramObject1;
      if (IntNum.asIntNumOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
      paramCallContext.value1 = paramObject1;
      if (IntNum.asIntNumOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
      paramCallContext.value1 = paramObject1;
      if (IntNum.asIntNumOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
    } while (!(paramObject1 instanceof CharSequence));
    paramCallContext.value1 = paramObject1;
    if (IntNum.asIntNumOrNull(paramObject2) != null)
    {
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    }
    return -786430;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
    case 33: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 31: 
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
    case 22: 
      if (!(paramObject1 instanceof hashtable.HashTable)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Procedure)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 18: 
      if (!(paramObject1 instanceof hashtable.HashTable)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 17: 
      if (!(paramObject1 instanceof hashtable.HashTable)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 15: 
      if (!(paramObject1 instanceof hashtable.HashTable)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    }
    if (!(paramObject1 instanceof Procedure)) {
      return -786431;
    }
    paramCallContext.value1 = paramObject1;
    if (!(paramObject2 instanceof Procedure)) {
      return -786430;
    }
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
    default: 
      return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
    case 24: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 4;
      return 0;
    case 20: 
      if (!(paramObject1 instanceof hashtable.HashTable)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 4;
      return 0;
    }
    if (!(paramObject1 instanceof hashtable.HashTable)) {
      return -786431;
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
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\srfi69.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */