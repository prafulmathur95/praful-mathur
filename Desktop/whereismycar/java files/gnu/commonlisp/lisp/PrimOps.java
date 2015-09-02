package gnu.commonlisp.lisp;

import gnu.commonlisp.lang.CommonLisp;
import gnu.commonlisp.lang.Lisp2;
import gnu.commonlisp.lang.Symbols;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.lists.SimpleVector;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import gnu.mapping.PropertyLocation;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;

public class PrimOps
  extends ModuleBody
{
  public static final PrimOps $instance;
  static final SimpleSymbol Lit0;
  static final IntNum Lit1;
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
  static final SimpleSymbol Lit31 = (SimpleSymbol)new SimpleSymbol("functionp").readResolve();
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod apply;
  public static final ModuleMethod aref;
  public static final ModuleMethod arrayp;
  public static final ModuleMethod aset;
  public static final ModuleMethod boundp;
  public static final ModuleMethod car;
  public static final ModuleMethod cdr;
  public static final ModuleMethod char$Mnto$Mnstring;
  public static final ModuleMethod fillarray;
  public static final ModuleMethod fset;
  public static final ModuleMethod functionp;
  public static final ModuleMethod get;
  public static final ModuleMethod length;
  public static final ModuleMethod make$Mnstring;
  public static final ModuleMethod plist$Mnget;
  public static final ModuleMethod plist$Mnmember;
  public static final ModuleMethod plist$Mnput;
  public static final ModuleMethod plist$Mnremprop;
  public static final ModuleMethod put;
  public static final ModuleMethod set;
  public static final ModuleMethod setcar;
  public static final ModuleMethod setcdr;
  public static final ModuleMethod setplist;
  public static final ModuleMethod stringp;
  public static final ModuleMethod substring;
  public static final ModuleMethod symbol$Mnfunction;
  public static final ModuleMethod symbol$Mnname;
  public static final ModuleMethod symbol$Mnplist;
  public static final ModuleMethod symbol$Mnvalue;
  public static final ModuleMethod symbolp;
  
  static
  {
    Lit30 = (SimpleSymbol)new SimpleSymbol("char-to-string").readResolve();
    Lit29 = (SimpleSymbol)new SimpleSymbol("substring").readResolve();
    Lit28 = (SimpleSymbol)new SimpleSymbol("make-string").readResolve();
    Lit27 = (SimpleSymbol)new SimpleSymbol("stringp").readResolve();
    Lit26 = (SimpleSymbol)new SimpleSymbol("fillarray").readResolve();
    Lit25 = (SimpleSymbol)new SimpleSymbol("aset").readResolve();
    Lit24 = (SimpleSymbol)new SimpleSymbol("aref").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("arrayp").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("length").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("apply").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("fset").readResolve();
    Lit19 = (SimpleSymbol)new SimpleSymbol("symbol-function").readResolve();
    Lit18 = (SimpleSymbol)new SimpleSymbol("set").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("symbol-value").readResolve();
    Lit16 = (SimpleSymbol)new SimpleSymbol("put").readResolve();
    Lit15 = (SimpleSymbol)new SimpleSymbol("get").readResolve();
    Lit14 = (SimpleSymbol)new SimpleSymbol("plist-member").readResolve();
    Lit13 = (SimpleSymbol)new SimpleSymbol("plist-remprop").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("plist-put").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("plist-get").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("setplist").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("symbol-plist").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("symbol-name").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("symbolp").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("boundp").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("setcdr").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("setcar").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("cdr").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("car").readResolve();
    Lit1 = IntNum.make(0);
    Lit0 = (SimpleSymbol)new SimpleSymbol("t").readResolve();
    $instance = new PrimOps();
    PrimOps localPrimOps = $instance;
    car = new ModuleMethod(localPrimOps, 1, Lit2, 4097);
    cdr = new ModuleMethod(localPrimOps, 2, Lit3, 4097);
    setcar = new ModuleMethod(localPrimOps, 3, Lit4, 8194);
    setcdr = new ModuleMethod(localPrimOps, 4, Lit5, 8194);
    boundp = new ModuleMethod(localPrimOps, 5, Lit6, 4097);
    symbolp = new ModuleMethod(localPrimOps, 6, Lit7, 4097);
    symbol$Mnname = new ModuleMethod(localPrimOps, 7, Lit8, 4097);
    symbol$Mnplist = new ModuleMethod(localPrimOps, 8, Lit9, 4097);
    setplist = new ModuleMethod(localPrimOps, 9, Lit10, 8194);
    plist$Mnget = new ModuleMethod(localPrimOps, 10, Lit11, 12290);
    plist$Mnput = new ModuleMethod(localPrimOps, 12, Lit12, 12291);
    plist$Mnremprop = new ModuleMethod(localPrimOps, 13, Lit13, 8194);
    plist$Mnmember = new ModuleMethod(localPrimOps, 14, Lit14, 8194);
    get = new ModuleMethod(localPrimOps, 15, Lit15, 12290);
    put = new ModuleMethod(localPrimOps, 17, Lit16, 12291);
    symbol$Mnvalue = new ModuleMethod(localPrimOps, 18, Lit17, 4097);
    set = new ModuleMethod(localPrimOps, 19, Lit18, 8194);
    symbol$Mnfunction = new ModuleMethod(localPrimOps, 20, Lit19, 4097);
    fset = new ModuleMethod(localPrimOps, 21, Lit20, 8194);
    apply = new ModuleMethod(localPrimOps, 22, Lit21, 61441);
    length = new ModuleMethod(localPrimOps, 23, Lit22, 4097);
    arrayp = new ModuleMethod(localPrimOps, 24, Lit23, 4097);
    aref = new ModuleMethod(localPrimOps, 25, Lit24, 8194);
    aset = new ModuleMethod(localPrimOps, 26, Lit25, 12291);
    fillarray = new ModuleMethod(localPrimOps, 27, Lit26, 8194);
    stringp = new ModuleMethod(localPrimOps, 28, Lit27, 4097);
    make$Mnstring = new ModuleMethod(localPrimOps, 29, Lit28, 8194);
    substring = new ModuleMethod(localPrimOps, 30, Lit29, 12290);
    char$Mnto$Mnstring = new ModuleMethod(localPrimOps, 32, Lit30, 4097);
    functionp = new ModuleMethod(localPrimOps, 33, Lit31, 4097);
    $instance.run();
  }
  
  public PrimOps()
  {
    ModuleInfo.register(this);
  }
  
  public static Object apply(Object paramObject, Object... paramVarArgs)
  {
    if (misc.isSymbol(paramObject)) {}
    for (paramObject = (Procedure)symbolFunction(paramObject);; paramObject = (Procedure)paramObject) {
      return ((Procedure)paramObject).applyN(Apply.getArguments(paramVarArgs, 0, apply));
    }
  }
  
  public static Object aref(SimpleVector paramSimpleVector, int paramInt)
  {
    return paramSimpleVector.get(paramInt);
  }
  
  public static boolean arrayp(Object paramObject)
  {
    return paramObject instanceof SimpleVector;
  }
  
  public static Object aset(SimpleVector paramSimpleVector, int paramInt, Object paramObject)
  {
    paramSimpleVector.set(paramInt, paramObject);
    return paramObject;
  }
  
  public static boolean boundp(Object paramObject)
  {
    return Symbols.isBound(paramObject);
  }
  
  public static Object car(Object paramObject)
  {
    if (paramObject == LList.Empty) {
      return paramObject;
    }
    return ((Pair)paramObject).getCar();
  }
  
  public static Object cdr(Object paramObject)
  {
    if (paramObject == LList.Empty) {
      return paramObject;
    }
    return ((Pair)paramObject).getCdr();
  }
  
  public static FString charToString(Object paramObject)
  {
    return new FString(1, CommonLisp.asChar(paramObject));
  }
  
  public static Object fillarray(SimpleVector paramSimpleVector, Object paramObject)
  {
    paramSimpleVector.fill(paramObject);
    return paramObject;
  }
  
  public static void fset(Object paramObject1, Object paramObject2)
  {
    Symbols.setFunctionBinding(Environment.getCurrent(), paramObject1, paramObject2);
  }
  
  public static boolean functionp(Object paramObject)
  {
    return paramObject instanceof Procedure;
  }
  
  public static Object get(Symbol paramSymbol, Object paramObject)
  {
    return get(paramSymbol, paramObject, LList.Empty);
  }
  
  public static Object get(Symbol paramSymbol, Object paramObject1, Object paramObject2)
  {
    return PropertyLocation.getProperty(paramSymbol, paramObject1, paramObject2);
  }
  
  public static int length(Sequence paramSequence)
  {
    return paramSequence.size();
  }
  
  public static FString makeString(int paramInt, Object paramObject)
  {
    return new FString(paramInt, CommonLisp.asChar(paramObject));
  }
  
  public static Object plistGet(Object paramObject1, Object paramObject2)
  {
    return plistGet(paramObject1, paramObject2, Boolean.FALSE);
  }
  
  public static Object plistGet(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return PropertyLocation.plistGet(paramObject1, paramObject2, paramObject3);
  }
  
  public static Object plistMember(Object paramObject1, Object paramObject2)
  {
    if (PropertyLocation.plistGet(paramObject1, paramObject2, Values.empty) == Values.empty) {
      return LList.Empty;
    }
    return Lit0;
  }
  
  public static Object plistPut(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return PropertyLocation.plistPut(paramObject1, paramObject2, paramObject3);
  }
  
  public static Object plistRemprop(Object paramObject1, Object paramObject2)
  {
    return PropertyLocation.plistRemove(paramObject1, paramObject2);
  }
  
  public static void put(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    PropertyLocation.putProperty(paramObject1, paramObject2, paramObject3);
  }
  
  public static void set(Object paramObject1, Object paramObject2)
  {
    Environment.getCurrent().put(Symbols.getSymbol(paramObject1), paramObject2);
  }
  
  public static void setcar(Pair paramPair, Object paramObject)
  {
    lists.setCar$Ex(paramPair, paramObject);
  }
  
  public static void setcdr(Pair paramPair, Object paramObject)
  {
    lists.setCdr$Ex(paramPair, paramObject);
  }
  
  public static Object setplist(Object paramObject1, Object paramObject2)
  {
    PropertyLocation.setPropertyList(paramObject1, paramObject2);
    return paramObject2;
  }
  
  public static boolean stringp(Object paramObject)
  {
    return paramObject instanceof CharSequence;
  }
  
  public static FString substring(CharSequence paramCharSequence, Object paramObject)
  {
    return substring(paramCharSequence, paramObject, LList.Empty);
  }
  
  public static FString substring(CharSequence paramCharSequence, Object paramObject1, Object paramObject2)
  {
    Object localObject = paramObject2;
    if (paramObject2 == LList.Empty) {
      localObject = Integer.valueOf(strings.stringLength(paramCharSequence));
    }
    paramObject2 = localObject;
    if (Scheme.numLss.apply2(localObject, Lit1) != Boolean.FALSE) {
      paramObject2 = AddOp.$Mn.apply2(Integer.valueOf(strings.stringLength(paramCharSequence)), localObject);
    }
    localObject = paramObject1;
    if (Scheme.numLss.apply2(paramObject1, Lit1) != Boolean.FALSE) {
      localObject = AddOp.$Mn.apply2(Integer.valueOf(strings.stringLength(paramCharSequence)), paramObject1);
    }
    return new FString(paramCharSequence, ((Number)localObject).intValue(), ((Number)AddOp.$Mn.apply2(paramObject2, localObject)).intValue());
  }
  
  public static Object symbolFunction(Object paramObject)
  {
    return Symbols.getFunctionBinding(paramObject);
  }
  
  public static Object symbolName(Object paramObject)
  {
    return Symbols.getPrintName(paramObject);
  }
  
  public static Object symbolPlist(Object paramObject)
  {
    return PropertyLocation.getPropertyList(paramObject);
  }
  
  public static Object symbolValue(Object paramObject)
  {
    return Environment.getCurrent().get(Symbols.getSymbol(paramObject));
  }
  
  public static boolean symbolp(Object paramObject)
  {
    return Symbols.isSymbol(paramObject);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 1: 
      return car(paramObject);
    case 2: 
      return cdr(paramObject);
    case 5: 
      if (boundp(paramObject)) {
        return Lisp2.TRUE;
      }
      return LList.Empty;
    case 6: 
      if (symbolp(paramObject)) {
        return Lisp2.TRUE;
      }
      return LList.Empty;
    case 7: 
      return symbolName(paramObject);
    case 8: 
      return symbolPlist(paramObject);
    case 18: 
      return symbolValue(paramObject);
    case 20: 
      return symbolFunction(paramObject);
    }
    try
    {
      paramModuleMethod = (Sequence)paramObject;
      return Integer.valueOf(length(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "length", 1, paramObject);
    }
    if (arrayp(paramObject)) {
      return Lisp2.TRUE;
    }
    return LList.Empty;
    if (stringp(paramObject)) {
      return Lisp2.TRUE;
    }
    return LList.Empty;
    return charToString(paramObject);
    if (functionp(paramObject)) {
      return Lisp2.TRUE;
    }
    return LList.Empty;
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    switch (paramModuleMethod.selector)
    {
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 11: 
    case 12: 
    case 16: 
    case 17: 
    case 18: 
    case 20: 
    case 22: 
    case 23: 
    case 24: 
    case 26: 
    case 28: 
    default: 
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    try
    {
      paramModuleMethod = (Pair)paramObject1;
      setcar(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (SimpleVector)paramObject1;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "aref", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject2).intValue();
        return aref(paramModuleMethod, i);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "aref", 2, paramObject2);
      }
      try
      {
        paramModuleMethod = (SimpleVector)paramObject1;
        return fillarray(paramModuleMethod, paramObject2);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "fillarray", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject1).intValue();
        return makeString(i, paramObject2);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "make-string", 1, paramObject1);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject1;
        return substring(paramModuleMethod, paramObject2);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "substring", 1, paramObject1);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "setcar", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (Pair)paramObject1;
      setcdr(paramModuleMethod, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "setcdr", 1, paramObject1);
    }
    return setplist(paramObject1, paramObject2);
    return plistGet(paramObject1, paramObject2);
    return plistRemprop(paramObject1, paramObject2);
    return plistMember(paramObject1, paramObject2);
    try
    {
      paramModuleMethod = (Symbol)paramObject1;
      return get(paramModuleMethod, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      int i;
      throw new WrongType(paramModuleMethod, "get", 1, paramObject1);
    }
    set(paramObject1, paramObject2);
    return Values.empty;
    fset(paramObject1, paramObject2);
    return Values.empty;
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
    case 10: 
      return plistGet(paramObject1, paramObject2, paramObject3);
    case 12: 
      return plistPut(paramObject1, paramObject2, paramObject3);
    }
    try
    {
      paramModuleMethod = (Symbol)paramObject1;
      return get(paramModuleMethod, paramObject2, paramObject3);
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (SimpleVector)paramObject1;
      }
      catch (ClassCastException paramModuleMethod)
      {
        int i;
        throw new WrongType(paramModuleMethod, "aset", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject2).intValue();
        return aset(paramModuleMethod, i, paramObject3);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "aset", 2, paramObject2);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject1;
        return substring(paramModuleMethod, paramObject2, paramObject3);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "substring", 1, paramObject1);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "get", 1, paramObject1);
    }
    put(paramObject1, paramObject2, paramObject3);
    return Values.empty;
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    if (paramModuleMethod.selector == 22)
    {
      paramModuleMethod = paramArrayOfObject[0];
      int i = paramArrayOfObject.length - 1;
      Object[] arrayOfObject = new Object[i];
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          return apply(paramModuleMethod, arrayOfObject);
        }
        arrayOfObject[i] = paramArrayOfObject[(i + 1)];
      }
    }
    return super.applyN(paramModuleMethod, paramArrayOfObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 33: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 32: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 28: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 24: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 23: 
      if (!(paramObject instanceof Sequence)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 20: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 18: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 8: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 7: 
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
    switch (paramModuleMethod.selector)
    {
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 11: 
    case 12: 
    case 16: 
    case 17: 
    case 18: 
    case 20: 
    case 22: 
    case 23: 
    case 24: 
    case 26: 
    case 28: 
    default: 
      return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    case 30: 
      if ((paramObject1 instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786431;
    case 29: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 27: 
      if (!(paramObject1 instanceof SimpleVector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 25: 
      if (!(paramObject1 instanceof SimpleVector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 21: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 19: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 15: 
      if (!(paramObject1 instanceof Symbol)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 14: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 13: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 10: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 9: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 4: 
      if (!(paramObject1 instanceof Pair)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    }
    if (!(paramObject1 instanceof Pair)) {
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
    case 30: 
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
    case 26: 
      if (!(paramObject1 instanceof SimpleVector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 17: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 15: 
      if (!(paramObject1 instanceof Symbol)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 12: 
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
    if (paramModuleMethod.selector == 22)
    {
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    }
    return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\commonlisp\lisp\PrimOps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */