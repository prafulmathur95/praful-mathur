package kawa.lib.rnrs;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.util.AbstractHashTable;
import gnu.kawa.util.HashNode;
import gnu.lists.FVector;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.kawa.hashtable;
import kawa.lib.kawa.hashtable.HashTable;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;

public class hashtables
  extends ModuleBody
{
  public static final hashtables $instance;
  static final SimpleSymbol Lit0;
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
  static final SimpleSymbol Lit19;
  static final SimpleSymbol Lit2;
  static final SimpleSymbol Lit20;
  static final SimpleSymbol Lit21;
  static final SimpleSymbol Lit22 = (SimpleSymbol)new SimpleSymbol("symbol-hash").readResolve();
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod equal$Mnhash;
  static final ModuleMethod hash$Mnby$Mnidentity;
  static final ModuleMethod hash$Mnfor$Mneqv;
  public static final ModuleMethod hashtable$Mnclear$Ex;
  public static final ModuleMethod hashtable$Mncontains$Qu;
  public static final ModuleMethod hashtable$Mncopy;
  public static final ModuleMethod hashtable$Mndelete$Ex;
  public static final ModuleMethod hashtable$Mnentries;
  public static final ModuleMethod hashtable$Mnequivalence$Mnfunction;
  public static final ModuleMethod hashtable$Mnhash$Mnfunction;
  public static final ModuleMethod hashtable$Mnkeys;
  public static final ModuleMethod hashtable$Mnmutable$Qu;
  public static final ModuleMethod hashtable$Mnref;
  public static final ModuleMethod hashtable$Mnset$Ex;
  public static final ModuleMethod hashtable$Mnsize;
  public static final ModuleMethod hashtable$Mnupdate$Ex;
  public static final ModuleMethod hashtable$Qu;
  public static final ModuleMethod make$Mneq$Mnhashtable;
  public static final ModuleMethod make$Mneqv$Mnhashtable;
  public static final ModuleMethod make$Mnhashtable;
  public static final ModuleMethod string$Mnci$Mnhash;
  public static final ModuleMethod string$Mnhash;
  public static final ModuleMethod symbol$Mnhash;
  
  static
  {
    Lit21 = (SimpleSymbol)new SimpleSymbol("string-ci-hash").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("string-hash").readResolve();
    Lit19 = (SimpleSymbol)new SimpleSymbol("equal-hash").readResolve();
    Lit18 = (SimpleSymbol)new SimpleSymbol("hashtable-mutable?").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("hashtable-hash-function").readResolve();
    Lit16 = (SimpleSymbol)new SimpleSymbol("hashtable-equivalence-function").readResolve();
    Lit15 = (SimpleSymbol)new SimpleSymbol("hashtable-entries").readResolve();
    Lit14 = (SimpleSymbol)new SimpleSymbol("hashtable-keys").readResolve();
    Lit13 = (SimpleSymbol)new SimpleSymbol("hashtable-clear!").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("hashtable-copy").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("hashtable-update!").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("hashtable-contains?").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("hashtable-delete!").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("hashtable-set!").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("hashtable-ref").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("hashtable-size").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("hashtable?").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("make-hashtable").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("make-eqv-hashtable").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("make-eq-hashtable").readResolve();
    Lit1 = (SimpleSymbol)new SimpleSymbol("hash-for-eqv").readResolve();
    Lit0 = (SimpleSymbol)new SimpleSymbol("hash-by-identity").readResolve();
    $instance = new hashtables();
    hashtables localhashtables = $instance;
    hash$Mnby$Mnidentity = new ModuleMethod(localhashtables, 1, Lit0, 4097);
    hash$Mnfor$Mneqv = new ModuleMethod(localhashtables, 2, Lit1, 4097);
    make$Mneq$Mnhashtable = new ModuleMethod(localhashtables, 3, Lit2, 4096);
    make$Mneqv$Mnhashtable = new ModuleMethod(localhashtables, 5, Lit3, 4096);
    make$Mnhashtable = new ModuleMethod(localhashtables, 7, Lit4, 12290);
    hashtable$Qu = new ModuleMethod(localhashtables, 9, Lit5, 4097);
    hashtable$Mnsize = new ModuleMethod(localhashtables, 10, Lit6, 4097);
    hashtable$Mnref = new ModuleMethod(localhashtables, 11, Lit7, 12291);
    hashtable$Mnset$Ex = new ModuleMethod(localhashtables, 12, Lit8, 12291);
    hashtable$Mndelete$Ex = new ModuleMethod(localhashtables, 13, Lit9, 8194);
    hashtable$Mncontains$Qu = new ModuleMethod(localhashtables, 14, Lit10, 8194);
    hashtable$Mnupdate$Ex = new ModuleMethod(localhashtables, 15, Lit11, 16388);
    hashtable$Mncopy = new ModuleMethod(localhashtables, 16, Lit12, 8193);
    hashtable$Mnclear$Ex = new ModuleMethod(localhashtables, 18, Lit13, 8193);
    hashtable$Mnkeys = new ModuleMethod(localhashtables, 20, Lit14, 4097);
    hashtable$Mnentries = new ModuleMethod(localhashtables, 21, Lit15, 4097);
    hashtable$Mnequivalence$Mnfunction = new ModuleMethod(localhashtables, 22, Lit16, 4097);
    hashtable$Mnhash$Mnfunction = new ModuleMethod(localhashtables, 23, Lit17, 4097);
    hashtable$Mnmutable$Qu = new ModuleMethod(localhashtables, 24, Lit18, 4097);
    equal$Mnhash = new ModuleMethod(localhashtables, 25, Lit19, 4097);
    string$Mnhash = new ModuleMethod(localhashtables, 26, Lit20, 4097);
    string$Mnci$Mnhash = new ModuleMethod(localhashtables, 27, Lit21, 4097);
    symbol$Mnhash = new ModuleMethod(localhashtables, 28, Lit22, 4097);
    $instance.run();
  }
  
  public hashtables()
  {
    ModuleInfo.register(this);
  }
  
  public static int equalHash(Object paramObject)
  {
    return paramObject.hashCode();
  }
  
  static int hashByIdentity(Object paramObject)
  {
    return System.identityHashCode(paramObject);
  }
  
  static int hashForEqv(Object paramObject)
  {
    return paramObject.hashCode();
  }
  
  public static void hashtableClear$Ex(hashtable.HashTable paramHashTable)
  {
    hashtableClear$Ex(paramHashTable, 64);
  }
  
  public static void hashtableClear$Ex(hashtable.HashTable paramHashTable, int paramInt)
  {
    hashtable.hashtableCheckMutable(paramHashTable);
    paramHashTable.clear();
  }
  
  public static hashtable.HashTable hashtableCopy(hashtable.HashTable paramHashTable)
  {
    return hashtableCopy(paramHashTable, false);
  }
  
  public static hashtable.HashTable hashtableCopy(hashtable.HashTable paramHashTable, boolean paramBoolean)
  {
    return new hashtable.HashTable(paramHashTable, paramBoolean);
  }
  
  public static void hashtableDelete$Ex(hashtable.HashTable paramHashTable, Object paramObject)
  {
    hashtable.hashtableCheckMutable(paramHashTable);
    paramHashTable.remove(paramObject);
  }
  
  public static Object hashtableEntries(hashtable.HashTable paramHashTable)
  {
    paramHashTable = paramHashTable.entriesVectorPair();
    return misc.values(new Object[] { lists.car.apply1(paramHashTable), lists.cdr.apply1(paramHashTable) });
  }
  
  public static Procedure hashtableEquivalenceFunction(hashtable.HashTable paramHashTable)
  {
    return (Procedure)paramHashTable.equivalenceFunction.apply1(paramHashTable);
  }
  
  public static Object hashtableHashFunction(hashtable.HashTable paramHashTable)
  {
    Object localObject1 = paramHashTable.hashFunction.apply1(paramHashTable);
    Object localObject2 = Scheme.isEqv.apply2(localObject1, hash$Mnby$Mnidentity);
    if (localObject2 != Boolean.FALSE)
    {
      paramHashTable = (hashtable.HashTable)localObject1;
      if (localObject2 == Boolean.FALSE) {}
    }
    for (;;)
    {
      paramHashTable = Boolean.FALSE;
      do
      {
        return paramHashTable;
        paramHashTable = (hashtable.HashTable)localObject1;
      } while (Scheme.isEqv.apply2(localObject1, hash$Mnfor$Mneqv) == Boolean.FALSE);
    }
  }
  
  public static FVector hashtableKeys(hashtable.HashTable paramHashTable)
  {
    return paramHashTable.keysVector();
  }
  
  public static Object hashtableRef(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2)
  {
    paramHashTable = paramHashTable.getNode(paramObject1);
    if (paramHashTable == null) {
      return paramObject2;
    }
    return paramHashTable.getValue();
  }
  
  public static void hashtableSet$Ex(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2)
  {
    hashtable.hashtableCheckMutable(paramHashTable);
    paramHashTable.put(paramObject1, paramObject2);
  }
  
  public static int hashtableSize(hashtable.HashTable paramHashTable)
  {
    return paramHashTable.size();
  }
  
  public static Object hashtableUpdate$Ex(hashtable.HashTable paramHashTable, Object paramObject1, Procedure paramProcedure, Object paramObject2)
  {
    hashtable.hashtableCheckMutable(paramHashTable);
    HashNode localHashNode = paramHashTable.getNode(paramObject1);
    if (localHashNode == null)
    {
      hashtableSet$Ex(paramHashTable, paramObject1, paramProcedure.apply1(paramObject2));
      return Values.empty;
    }
    return localHashNode.setValue(paramProcedure.apply1(localHashNode.getValue()));
  }
  
  public static boolean isHashtable(Object paramObject)
  {
    return paramObject instanceof hashtable.HashTable;
  }
  
  public static boolean isHashtableContains(hashtable.HashTable paramHashTable, Object paramObject)
  {
    if (paramHashTable.getNode(paramObject) == null) {}
    for (int i = 1;; i = 0) {
      return i + 1 & 0x1;
    }
  }
  
  public static Object isHashtableMutable(hashtable.HashTable paramHashTable)
  {
    ApplyToArgs localApplyToArgs = Scheme.applyToArgs;
    if (paramHashTable.mutable) {}
    for (paramHashTable = Boolean.TRUE;; paramHashTable = Boolean.FALSE) {
      return localApplyToArgs.apply1(paramHashTable);
    }
  }
  
  public static hashtable.HashTable makeEqHashtable()
  {
    return makeEqHashtable(AbstractHashTable.DEFAULT_INITIAL_SIZE);
  }
  
  public static hashtable.HashTable makeEqHashtable(int paramInt)
  {
    return new hashtable.HashTable(Scheme.isEq, hash$Mnby$Mnidentity, AbstractHashTable.DEFAULT_INITIAL_SIZE);
  }
  
  public static hashtable.HashTable makeEqvHashtable()
  {
    return makeEqvHashtable(AbstractHashTable.DEFAULT_INITIAL_SIZE);
  }
  
  public static hashtable.HashTable makeEqvHashtable(int paramInt)
  {
    return new hashtable.HashTable(Scheme.isEqv, hash$Mnfor$Mneqv, AbstractHashTable.DEFAULT_INITIAL_SIZE);
  }
  
  public static hashtable.HashTable makeHashtable(Procedure paramProcedure1, Procedure paramProcedure2)
  {
    return makeHashtable(paramProcedure1, paramProcedure2, AbstractHashTable.DEFAULT_INITIAL_SIZE);
  }
  
  public static hashtable.HashTable makeHashtable(Procedure paramProcedure1, Procedure paramProcedure2, int paramInt)
  {
    return new hashtable.HashTable(paramProcedure1, paramProcedure2, paramInt);
  }
  
  public static int stringCiHash(CharSequence paramCharSequence)
  {
    return paramCharSequence.toString().toLowerCase().hashCode();
  }
  
  public static int stringHash(CharSequence paramCharSequence)
  {
    return paramCharSequence.hashCode();
  }
  
  public static int symbolHash(Symbol paramSymbol)
  {
    return paramSymbol.hashCode();
  }
  
  public Object apply0(ModuleMethod paramModuleMethod)
  {
    switch (paramModuleMethod.selector)
    {
    case 4: 
    default: 
      return super.apply0(paramModuleMethod);
    case 3: 
      return makeEqHashtable();
    }
    return makeEqvHashtable();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 4: 
    case 6: 
    case 7: 
    case 8: 
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    case 17: 
    case 19: 
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 1: 
      return Integer.valueOf(hashByIdentity(paramObject));
    case 2: 
      return Integer.valueOf(hashForEqv(paramObject));
    }
    try
    {
      i = ((Number)paramObject).intValue();
      return makeEqHashtable(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        int i;
        paramModuleMethod = (CharSequence)paramObject;
        return Integer.valueOf(stringHash(paramModuleMethod));
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-hash", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject;
        return Integer.valueOf(stringCiHash(paramModuleMethod));
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-ci-hash", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (Symbol)paramObject;
        return Integer.valueOf(symbolHash(paramModuleMethod));
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "symbol-hash", 1, paramObject);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "make-eq-hashtable", 1, paramObject);
    }
    try
    {
      i = ((Number)paramObject).intValue();
      return makeEqvHashtable(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-eqv-hashtable", 1, paramObject);
    }
    if (isHashtable(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject;
      return Integer.valueOf(hashtableSize(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hashtable-size", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject;
      return hashtableCopy(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hashtable-copy", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject;
      hashtableClear$Ex(paramModuleMethod);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hashtable-clear!", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject;
      return hashtableKeys(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hashtable-keys", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject;
      return hashtableEntries(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hashtable-entries", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject;
      return hashtableEquivalenceFunction(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hashtable-equivalence-function", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject;
      return hashtableHashFunction(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hashtable-hash-function", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject;
      return isHashtableMutable(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hashtable-mutable?", 1, paramObject);
    }
    return Integer.valueOf(equalHash(paramObject));
  }
  
  /* Error */
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 5
    //   3: aload_1
    //   4: getfield 428	gnu/expr/ModuleMethod:selector	I
    //   7: lookupswitch	default:+49->56, 7:+57->64, 13:+73->80, 14:+87->94, 16:+108->115, 18:+135->142
    //   56: aload_0
    //   57: aload_1
    //   58: aload_2
    //   59: aload_3
    //   60: invokespecial 488	gnu/expr/ModuleBody:apply2	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   63: areturn
    //   64: aload_2
    //   65: checkcast 282	gnu/mapping/Procedure
    //   68: astore_1
    //   69: aload_3
    //   70: checkcast 282	gnu/mapping/Procedure
    //   73: astore_2
    //   74: aload_1
    //   75: aload_2
    //   76: invokestatic 490	kawa/lib/rnrs/hashtables:makeHashtable	(Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;)Lkawa/lib/kawa/hashtable$HashTable;
    //   79: areturn
    //   80: aload_2
    //   81: checkcast 251	kawa/lib/kawa/hashtable$HashTable
    //   84: astore_1
    //   85: aload_1
    //   86: aload_3
    //   87: invokestatic 492	kawa/lib/rnrs/hashtables:hashtableDelete$Ex	(Lkawa/lib/kawa/hashtable$HashTable;Ljava/lang/Object;)V
    //   90: getstatic 357	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   93: areturn
    //   94: aload_2
    //   95: checkcast 251	kawa/lib/kawa/hashtable$HashTable
    //   98: astore_1
    //   99: aload_1
    //   100: aload_3
    //   101: invokestatic 494	kawa/lib/rnrs/hashtables:isHashtableContains	(Lkawa/lib/kawa/hashtable$HashTable;Ljava/lang/Object;)Z
    //   104: ifeq +7 -> 111
    //   107: getstatic 376	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   110: areturn
    //   111: getstatic 320	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   114: areturn
    //   115: aload_2
    //   116: checkcast 251	kawa/lib/kawa/hashtable$HashTable
    //   119: astore_1
    //   120: getstatic 320	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   123: astore_2
    //   124: aload_3
    //   125: aload_2
    //   126: if_acmpeq +10 -> 136
    //   129: aload_1
    //   130: iload 5
    //   132: invokestatic 259	kawa/lib/rnrs/hashtables:hashtableCopy	(Lkawa/lib/kawa/hashtable$HashTable;Z)Lkawa/lib/kawa/hashtable$HashTable;
    //   135: areturn
    //   136: iconst_0
    //   137: istore 5
    //   139: goto -10 -> 129
    //   142: aload_2
    //   143: checkcast 251	kawa/lib/kawa/hashtable$HashTable
    //   146: astore_1
    //   147: aload_3
    //   148: checkcast 451	java/lang/Number
    //   151: invokevirtual 454	java/lang/Number:intValue	()I
    //   154: istore 4
    //   156: aload_1
    //   157: iload 4
    //   159: invokestatic 244	kawa/lib/rnrs/hashtables:hashtableClear$Ex	(Lkawa/lib/kawa/hashtable$HashTable;I)V
    //   162: getstatic 357	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   165: areturn
    //   166: astore_1
    //   167: new 482	gnu/mapping/WrongType
    //   170: dup
    //   171: aload_1
    //   172: ldc -116
    //   174: iconst_1
    //   175: aload_2
    //   176: invokespecial 485	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   179: athrow
    //   180: astore_1
    //   181: new 482	gnu/mapping/WrongType
    //   184: dup
    //   185: aload_1
    //   186: ldc -116
    //   188: iconst_2
    //   189: aload_3
    //   190: invokespecial 485	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   193: athrow
    //   194: astore_1
    //   195: new 482	gnu/mapping/WrongType
    //   198: dup
    //   199: aload_1
    //   200: ldc 120
    //   202: iconst_1
    //   203: aload_2
    //   204: invokespecial 485	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   207: athrow
    //   208: astore_1
    //   209: new 482	gnu/mapping/WrongType
    //   212: dup
    //   213: aload_1
    //   214: ldc 116
    //   216: iconst_1
    //   217: aload_2
    //   218: invokespecial 485	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   221: athrow
    //   222: astore_1
    //   223: new 482	gnu/mapping/WrongType
    //   226: dup
    //   227: aload_1
    //   228: ldc 108
    //   230: iconst_1
    //   231: aload_2
    //   232: invokespecial 485	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   235: athrow
    //   236: astore_1
    //   237: new 482	gnu/mapping/WrongType
    //   240: dup
    //   241: aload_1
    //   242: ldc 108
    //   244: iconst_2
    //   245: aload_3
    //   246: invokespecial 485	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   249: athrow
    //   250: astore_1
    //   251: new 482	gnu/mapping/WrongType
    //   254: dup
    //   255: aload_1
    //   256: ldc 104
    //   258: iconst_1
    //   259: aload_2
    //   260: invokespecial 485	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   263: athrow
    //   264: astore_1
    //   265: new 482	gnu/mapping/WrongType
    //   268: dup
    //   269: aload_1
    //   270: ldc 104
    //   272: iconst_2
    //   273: aload_3
    //   274: invokespecial 485	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   277: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	278	0	this	hashtables
    //   0	278	1	paramModuleMethod	ModuleMethod
    //   0	278	2	paramObject1	Object
    //   0	278	3	paramObject2	Object
    //   154	4	4	i	int
    //   1	137	5	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   64	69	166	java/lang/ClassCastException
    //   69	74	180	java/lang/ClassCastException
    //   80	85	194	java/lang/ClassCastException
    //   94	99	208	java/lang/ClassCastException
    //   115	120	222	java/lang/ClassCastException
    //   120	124	236	java/lang/ClassCastException
    //   142	147	250	java/lang/ClassCastException
    //   147	156	264	java/lang/ClassCastException
  }
  
  /* Error */
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 428	gnu/expr/ModuleMethod:selector	I
    //   4: tableswitch	default:+40->44, 7:+50->54, 8:+40->44, 9:+40->44, 10:+40->44, 11:+78->82, 12:+91->95
    //   44: aload_0
    //   45: aload_1
    //   46: aload_2
    //   47: aload_3
    //   48: aload 4
    //   50: invokespecial 498	gnu/expr/ModuleBody:apply3	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   53: areturn
    //   54: aload_2
    //   55: checkcast 282	gnu/mapping/Procedure
    //   58: astore_1
    //   59: aload_3
    //   60: checkcast 282	gnu/mapping/Procedure
    //   63: astore_2
    //   64: aload 4
    //   66: checkcast 451	java/lang/Number
    //   69: invokevirtual 454	java/lang/Number:intValue	()I
    //   72: istore 5
    //   74: aload_1
    //   75: aload_2
    //   76: iload 5
    //   78: invokestatic 402	kawa/lib/rnrs/hashtables:makeHashtable	(Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;I)Lkawa/lib/kawa/hashtable$HashTable;
    //   81: areturn
    //   82: aload_2
    //   83: checkcast 251	kawa/lib/kawa/hashtable$HashTable
    //   86: astore_1
    //   87: aload_1
    //   88: aload_3
    //   89: aload 4
    //   91: invokestatic 500	kawa/lib/rnrs/hashtables:hashtableRef	(Lkawa/lib/kawa/hashtable$HashTable;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   94: areturn
    //   95: aload_2
    //   96: checkcast 251	kawa/lib/kawa/hashtable$HashTable
    //   99: astore_1
    //   100: aload_1
    //   101: aload_3
    //   102: aload 4
    //   104: invokestatic 351	kawa/lib/rnrs/hashtables:hashtableSet$Ex	(Lkawa/lib/kawa/hashtable$HashTable;Ljava/lang/Object;Ljava/lang/Object;)V
    //   107: getstatic 357	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   110: areturn
    //   111: astore_1
    //   112: new 482	gnu/mapping/WrongType
    //   115: dup
    //   116: aload_1
    //   117: ldc -116
    //   119: iconst_1
    //   120: aload_2
    //   121: invokespecial 485	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   124: athrow
    //   125: astore_1
    //   126: new 482	gnu/mapping/WrongType
    //   129: dup
    //   130: aload_1
    //   131: ldc -116
    //   133: iconst_2
    //   134: aload_3
    //   135: invokespecial 485	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   138: athrow
    //   139: astore_1
    //   140: new 482	gnu/mapping/WrongType
    //   143: dup
    //   144: aload_1
    //   145: ldc -116
    //   147: iconst_3
    //   148: aload 4
    //   150: invokespecial 485	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   153: athrow
    //   154: astore_1
    //   155: new 482	gnu/mapping/WrongType
    //   158: dup
    //   159: aload_1
    //   160: ldc -128
    //   162: iconst_1
    //   163: aload_2
    //   164: invokespecial 485	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   167: athrow
    //   168: astore_1
    //   169: new 482	gnu/mapping/WrongType
    //   172: dup
    //   173: aload_1
    //   174: ldc 124
    //   176: iconst_1
    //   177: aload_2
    //   178: invokespecial 485	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   181: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	182	0	this	hashtables
    //   0	182	1	paramModuleMethod	ModuleMethod
    //   0	182	2	paramObject1	Object
    //   0	182	3	paramObject2	Object
    //   0	182	4	paramObject3	Object
    //   72	5	5	i	int
    // Exception table:
    //   from	to	target	type
    //   54	59	111	java/lang/ClassCastException
    //   59	64	125	java/lang/ClassCastException
    //   64	74	139	java/lang/ClassCastException
    //   82	87	154	java/lang/ClassCastException
    //   95	100	168	java/lang/ClassCastException
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    if (paramModuleMethod.selector == 15) {}
    try
    {
      paramModuleMethod = (hashtable.HashTable)paramObject1;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hashtable-update!", 1, paramObject1);
    }
    try
    {
      paramObject1 = (Procedure)paramObject3;
      return hashtableUpdate$Ex(paramModuleMethod, paramObject2, (Procedure)paramObject1, paramObject4);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hashtable-update!", 3, paramObject3);
    }
    return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 4: 
    default: 
      return super.match0(paramModuleMethod, paramCallContext);
    case 5: 
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
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    case 4: 
    case 6: 
    case 7: 
    case 8: 
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    case 17: 
    case 19: 
    default: 
      i = super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 28: 
    case 27: 
    case 26: 
    case 25: 
    case 24: 
    case 23: 
    case 22: 
    case 21: 
    case 20: 
    case 18: 
    case 16: 
    case 10: 
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
                      do
                      {
                        do
                        {
                          do
                          {
                            return i;
                          } while (!(paramObject instanceof Symbol));
                          paramCallContext.value1 = paramObject;
                          paramCallContext.proc = paramModuleMethod;
                          paramCallContext.pc = 1;
                          return 0;
                        } while (!(paramObject instanceof CharSequence));
                        paramCallContext.value1 = paramObject;
                        paramCallContext.proc = paramModuleMethod;
                        paramCallContext.pc = 1;
                        return 0;
                      } while (!(paramObject instanceof CharSequence));
                      paramCallContext.value1 = paramObject;
                      paramCallContext.proc = paramModuleMethod;
                      paramCallContext.pc = 1;
                      return 0;
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
      } while (!(paramObject instanceof hashtable.HashTable));
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 9: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 5: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 3: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
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
        } while (!(paramObject1 instanceof hashtable.HashTable));
        paramCallContext.value1 = paramObject1;
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
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    case 8: 
    case 9: 
    case 10: 
    default: 
      i = super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
    }
    do
    {
      do
      {
        do
        {
          return i;
        } while (!(paramObject1 instanceof hashtable.HashTable));
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      } while (!(paramObject1 instanceof hashtable.HashTable));
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    } while (!(paramObject1 instanceof Procedure));
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
    if (paramModuleMethod.selector == 15)
    {
      if (!(paramObject1 instanceof hashtable.HashTable)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
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
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\rnrs\hashtables.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */