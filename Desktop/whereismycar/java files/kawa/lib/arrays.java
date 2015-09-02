package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Arrays;
import gnu.lists.Array;
import gnu.lists.FVector;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;

public class arrays
  extends ModuleBody
{
  public static final Class $Lsarray$Gr;
  public static final arrays $instance;
  static final SimpleSymbol Lit0;
  static final SimpleSymbol Lit1;
  static final SimpleSymbol Lit2;
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7 = (SimpleSymbol)new SimpleSymbol("share-array").readResolve();
  public static final ModuleMethod array;
  public static final ModuleMethod array$Mnend;
  public static final ModuleMethod array$Mnrank;
  public static final ModuleMethod array$Mnstart;
  public static final ModuleMethod array$Qu;
  public static final ModuleMethod make$Mnarray;
  public static final ModuleMethod shape;
  public static final ModuleMethod share$Mnarray;
  
  static
  {
    Lit6 = (SimpleSymbol)new SimpleSymbol("array-end").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("array-start").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("array-rank").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("array").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("make-array").readResolve();
    Lit1 = (SimpleSymbol)new SimpleSymbol("shape").readResolve();
    Lit0 = (SimpleSymbol)new SimpleSymbol("array?").readResolve();
    $Lsarray$Gr = Array.class;
    $instance = new arrays();
    arrays localarrays = $instance;
    array$Qu = new ModuleMethod(localarrays, 1, Lit0, 4097);
    shape = new ModuleMethod(localarrays, 2, Lit1, 61440);
    make$Mnarray = new ModuleMethod(localarrays, 3, Lit2, 8193);
    array = new ModuleMethod(localarrays, 5, Lit3, 61441);
    array$Mnrank = new ModuleMethod(localarrays, 6, Lit4, 4097);
    array$Mnstart = new ModuleMethod(localarrays, 7, Lit5, 8194);
    array$Mnend = new ModuleMethod(localarrays, 8, Lit6, 8194);
    share$Mnarray = new ModuleMethod(localarrays, 9, Lit7, 12291);
    $instance.run();
  }
  
  public arrays()
  {
    ModuleInfo.register(this);
  }
  
  public static Array array(Array paramArray, Object... paramVarArgs)
  {
    return Arrays.makeSimple(paramArray, new FVector(paramVarArgs));
  }
  
  public static int arrayEnd(Array paramArray, int paramInt)
  {
    return paramArray.getLowBound(paramInt) + paramArray.getSize(paramInt);
  }
  
  public static int arrayRank(Array paramArray)
  {
    return paramArray.rank();
  }
  
  public static int arrayStart(Array paramArray, int paramInt)
  {
    return paramArray.getLowBound(paramInt);
  }
  
  public static boolean isArray(Object paramObject)
  {
    return paramObject instanceof Array;
  }
  
  public static Array makeArray(Array paramArray)
  {
    return makeArray(paramArray, null);
  }
  
  public static Array makeArray(Array paramArray, Object paramObject)
  {
    return Arrays.make(paramArray, paramObject);
  }
  
  public static Array shape(Object... paramVarArgs)
  {
    return Arrays.shape(paramVarArgs);
  }
  
  public static Array shareArray(Array paramArray1, Array paramArray2, Procedure paramProcedure)
  {
    return Arrays.shareArray(paramArray1, paramArray2, paramProcedure);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 2: 
    case 4: 
    case 5: 
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 1: 
      if (isArray(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    try
    {
      paramModuleMethod = (Array)paramObject;
      return makeArray(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (Array)paramObject;
        return Integer.valueOf(arrayRank(paramModuleMethod));
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "array-rank", 1, paramObject);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "make-array", 1, paramObject);
    }
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    switch (paramModuleMethod.selector)
    {
    case 4: 
    case 5: 
    case 6: 
    default: 
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    try
    {
      paramModuleMethod = (Array)paramObject1;
      return makeArray(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (Array)paramObject1;
      }
      catch (ClassCastException paramModuleMethod)
      {
        int i;
        throw new WrongType(paramModuleMethod, "array-start", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject2).intValue();
        return Integer.valueOf(arrayStart(paramModuleMethod, i));
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "array-start", 2, paramObject2);
      }
      try
      {
        paramModuleMethod = (Array)paramObject1;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "array-end", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject2).intValue();
        return Integer.valueOf(arrayEnd(paramModuleMethod, i));
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "array-end", 2, paramObject2);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "make-array", 1, paramObject1);
    }
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    if (paramModuleMethod.selector == 9) {}
    try
    {
      paramModuleMethod = (Array)paramObject1;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "share-array", 1, paramObject1);
    }
    try
    {
      paramObject1 = (Array)paramObject2;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "share-array", 2, paramObject2);
    }
    try
    {
      paramObject2 = (Procedure)paramObject3;
      return shareArray(paramModuleMethod, (Array)paramObject1, (Procedure)paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "share-array", 3, paramObject3);
    }
    return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
  }
  
  /* Error */
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 161	gnu/expr/ModuleMethod:selector	I
    //   4: tableswitch	default:+32->36, 2:+39->43, 3:+32->36, 4:+32->36, 5:+44->48
    //   36: aload_0
    //   37: aload_1
    //   38: aload_2
    //   39: invokespecial 213	gnu/expr/ModuleBody:applyN	(Lgnu/expr/ModuleMethod;[Ljava/lang/Object;)Ljava/lang/Object;
    //   42: areturn
    //   43: aload_2
    //   44: invokestatic 214	kawa/lib/arrays:shape	([Ljava/lang/Object;)Lgnu/lists/Array;
    //   47: areturn
    //   48: aload_2
    //   49: iconst_0
    //   50: aaload
    //   51: astore_1
    //   52: aload_1
    //   53: checkcast 70	gnu/lists/Array
    //   56: astore_3
    //   57: aload_2
    //   58: arraylength
    //   59: iconst_1
    //   60: isub
    //   61: istore 4
    //   63: iload 4
    //   65: anewarray 216	java/lang/Object
    //   68: astore_1
    //   69: iload 4
    //   71: iconst_1
    //   72: isub
    //   73: istore 4
    //   75: iload 4
    //   77: ifge +9 -> 86
    //   80: aload_3
    //   81: aload_1
    //   82: invokestatic 218	kawa/lib/arrays:array	(Lgnu/lists/Array;[Ljava/lang/Object;)Lgnu/lists/Array;
    //   85: areturn
    //   86: aload_1
    //   87: iload 4
    //   89: aload_2
    //   90: iload 4
    //   92: iconst_1
    //   93: iadd
    //   94: aaload
    //   95: aastore
    //   96: goto -27 -> 69
    //   99: astore_2
    //   100: new 186	gnu/mapping/WrongType
    //   103: dup
    //   104: aload_2
    //   105: ldc 55
    //   107: iconst_1
    //   108: aload_1
    //   109: invokespecial 189	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   112: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	113	0	this	arrays
    //   0	113	1	paramModuleMethod	ModuleMethod
    //   0	113	2	paramArrayOfObject	Object[]
    //   56	25	3	localArray	Array
    //   61	33	4	i	int
    // Exception table:
    //   from	to	target	type
    //   52	57	99	java/lang/ClassCastException
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    case 2: 
    case 4: 
    case 5: 
    default: 
      i = super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 6: 
    case 3: 
      do
      {
        do
        {
          return i;
        } while (!(paramObject instanceof Array));
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      } while (!(paramObject instanceof Array));
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
    case 4: 
    case 5: 
    case 6: 
    default: 
      i = super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    }
    do
    {
      do
      {
        do
        {
          return i;
        } while (!(paramObject1 instanceof Array));
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      } while (!(paramObject1 instanceof Array));
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } while (!(paramObject1 instanceof Array));
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 9)
    {
      if (!(paramObject1 instanceof Array)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Array)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      if (!(paramObject3 instanceof Procedure)) {
        return -786429;
      }
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
    case 3: 
    case 4: 
    default: 
      return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
    case 5: 
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
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\arrays.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */