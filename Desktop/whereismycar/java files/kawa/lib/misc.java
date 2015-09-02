package kawa.lib;

import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Symbols;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.xml.KNode;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import kawa.Version;
import kawa.lang.Promise;
import kawa.standard.Scheme;
import kawa.standard.throw_name;

public class misc
  extends ModuleBody
{
  public static final misc $instance;
  static final IntNum Lit0;
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
  static final Keyword Lit2;
  static final SimpleSymbol Lit20;
  static final SimpleSymbol Lit21;
  static final SimpleSymbol Lit22;
  static final SimpleSymbol Lit23;
  static final SimpleSymbol Lit24;
  static final SimpleSymbol Lit25;
  static final SimpleSymbol Lit26;
  static final SimpleSymbol Lit27;
  static final SimpleSymbol Lit28 = (SimpleSymbol)new SimpleSymbol("add-procedure-properties").readResolve();
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod add$Mnprocedure$Mnproperties;
  public static final ModuleMethod base$Mnuri;
  public static final ModuleMethod boolean$Qu;
  public static final ModuleMethod dynamic$Mnwind;
  public static final ModuleMethod environment$Mnbound$Qu;
  public static final ModuleMethod error;
  public static final ModuleMethod force;
  public static final ModuleMethod gentemp;
  public static final ModuleMethod interaction$Mnenvironment;
  static final ModuleMethod lambda$Fn1;
  static final ModuleMethod lambda$Fn2;
  public static final ModuleMethod namespace$Mnprefix;
  public static final ModuleMethod namespace$Mnuri;
  public static final ModuleMethod null$Mnenvironment;
  public static final GenericProc procedure$Mnproperty;
  static final ModuleMethod procedure$Mnproperty$Fn3;
  public static final ModuleMethod procedure$Qu;
  public static final ModuleMethod scheme$Mnimplementation$Mnversion;
  public static final ModuleMethod scheme$Mnreport$Mnenvironment;
  public static final ModuleMethod set$Mnprocedure$Mnproperty$Ex;
  public static final ModuleMethod string$Mn$Grsymbol;
  public static final GenericProc symbol$Eq$Qu;
  public static final ModuleMethod symbol$Mn$Grstring;
  public static final ModuleMethod symbol$Mnlocal$Mnname;
  public static final ModuleMethod symbol$Mnnamespace;
  public static final ModuleMethod symbol$Mnnamespace$Mnuri;
  public static final ModuleMethod symbol$Mnprefix;
  public static final ModuleMethod symbol$Qu;
  public static final ModuleMethod values;
  
  static
  {
    Lit27 = (SimpleSymbol)new SimpleSymbol("gentemp").readResolve();
    Lit26 = (SimpleSymbol)new SimpleSymbol("base-uri").readResolve();
    Lit25 = (SimpleSymbol)new SimpleSymbol("error").readResolve();
    Lit24 = (SimpleSymbol)new SimpleSymbol("force").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("dynamic-wind").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("procedure-property").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("set-procedure-property!").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("scheme-implementation-version").readResolve();
    Lit19 = (SimpleSymbol)new SimpleSymbol("interaction-environment").readResolve();
    Lit18 = (SimpleSymbol)new SimpleSymbol("scheme-report-environment").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("null-environment").readResolve();
    Lit16 = (SimpleSymbol)new SimpleSymbol("environment-bound?").readResolve();
    Lit15 = (SimpleSymbol)new SimpleSymbol("values").readResolve();
    Lit14 = (SimpleSymbol)new SimpleSymbol("procedure?").readResolve();
    Lit13 = (SimpleSymbol)new SimpleSymbol("string->symbol").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("namespace-prefix").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("namespace-uri").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("symbol-prefix").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("symbol-namespace-uri").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("symbol-namespace").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("symbol-local-name").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("symbol->string").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("symbol?").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("boolean?").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("misc-error").readResolve();
    Lit2 = Keyword.make("setter");
    Lit1 = IntNum.make(5);
    Lit0 = IntNum.make(4);
    $instance = new misc();
    misc localmisc = $instance;
    boolean$Qu = new ModuleMethod(localmisc, 3, Lit4, 4097);
    symbol$Qu = new ModuleMethod(localmisc, 4, Lit5, 4097);
    symbol$Mn$Grstring = new ModuleMethod(localmisc, 5, Lit6, 4097);
    ModuleMethod localModuleMethod = new ModuleMethod(localmisc, 6, null, 8194);
    localModuleMethod.setProperty("source-location", "misc.scm:25");
    lambda$Fn1 = localModuleMethod;
    localModuleMethod = new ModuleMethod(localmisc, 7, null, 61442);
    localModuleMethod.setProperty("source-location", "misc.scm:27");
    lambda$Fn2 = localModuleMethod;
    symbol$Mnlocal$Mnname = new ModuleMethod(localmisc, 8, Lit7, 4097);
    symbol$Mnnamespace = new ModuleMethod(localmisc, 9, Lit8, 4097);
    symbol$Mnnamespace$Mnuri = new ModuleMethod(localmisc, 10, Lit9, 4097);
    symbol$Mnprefix = new ModuleMethod(localmisc, 11, Lit10, 4097);
    namespace$Mnuri = new ModuleMethod(localmisc, 12, Lit11, 4097);
    namespace$Mnprefix = new ModuleMethod(localmisc, 13, Lit12, 4097);
    string$Mn$Grsymbol = new ModuleMethod(localmisc, 14, Lit13, 4097);
    procedure$Qu = new ModuleMethod(localmisc, 15, Lit14, 4097);
    values = new ModuleMethod(localmisc, 16, Lit15, 61440);
    environment$Mnbound$Qu = new ModuleMethod(localmisc, 17, Lit16, 8194);
    null$Mnenvironment = new ModuleMethod(localmisc, 18, Lit17, 4096);
    scheme$Mnreport$Mnenvironment = new ModuleMethod(localmisc, 20, Lit18, 4097);
    interaction$Mnenvironment = new ModuleMethod(localmisc, 21, Lit19, 0);
    scheme$Mnimplementation$Mnversion = new ModuleMethod(localmisc, 22, Lit20, 0);
    set$Mnprocedure$Mnproperty$Ex = new ModuleMethod(localmisc, 23, Lit21, 12291);
    procedure$Mnproperty$Fn3 = new ModuleMethod(localmisc, 24, Lit22, 12290);
    dynamic$Mnwind = new ModuleMethod(localmisc, 26, Lit23, 12291);
    force = new ModuleMethod(localmisc, 27, Lit24, 4097);
    error = new ModuleMethod(localmisc, 28, Lit25, 61441);
    base$Mnuri = new ModuleMethod(localmisc, 29, Lit26, 4096);
    gentemp = new ModuleMethod(localmisc, 31, Lit27, 0);
    add$Mnprocedure$Mnproperties = new ModuleMethod(localmisc, 32, Lit28, 61441);
    $instance.run();
  }
  
  public misc()
  {
    ModuleInfo.register(this);
  }
  
  public static void addProcedureProperties(GenericProc paramGenericProc, Object... paramVarArgs)
  {
    paramGenericProc.setProperties(paramVarArgs);
  }
  
  public static Object baseUri()
  {
    return baseUri(null);
  }
  
  public static Object baseUri(Object paramObject)
  {
    if (paramObject == null) {}
    for (paramObject = Path.currentPath();; paramObject = ((KNode)paramObject).baseURI())
    {
      Object localObject = paramObject;
      if (paramObject == Values.empty) {
        localObject = Boolean.FALSE;
      }
      return localObject;
    }
  }
  
  public static Object dynamicWind(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Scheme.applyToArgs.apply1(paramObject1);
    try
    {
      paramObject1 = Scheme.applyToArgs.apply1(paramObject2);
      return paramObject1;
    }
    finally
    {
      Scheme.applyToArgs.apply1(paramObject3);
    }
  }
  
  public static Object error$V(Object paramObject, Object[] paramArrayOfObject)
  {
    frame localframe = new frame();
    localframe.msg = paramObject;
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    localframe.msg = ports.callWithOutputString(localframe.lambda$Fn4);
    paramObject = LList.Empty;
    for (;;)
    {
      if (paramArrayOfObject == LList.Empty)
      {
        paramObject = LList.reverseInPlace(paramObject);
        return Scheme.apply.apply4(throw_name.throwName, Lit3, localframe.msg, paramObject);
      }
      try
      {
        Object localObject = (Pair)paramArrayOfObject;
        paramArrayOfObject = ((Pair)localObject).getCdr();
        localObject = ((Pair)localObject).getCar();
        frame0 localframe0 = new frame0();
        localframe0.arg = localObject;
        paramObject = Pair.make(ports.callWithOutputString(localframe0.lambda$Fn5), paramObject);
      }
      catch (ClassCastException paramObject)
      {
        throw new WrongType((ClassCastException)paramObject, "arg0", -2, paramArrayOfObject);
      }
    }
  }
  
  public static Object force(Object paramObject)
  {
    return Promise.force(paramObject);
  }
  
  public static Symbol gentemp()
  {
    return Symbols.gentemp();
  }
  
  public static Environment interactionEnvironment()
  {
    return Environment.user();
  }
  
  public static boolean isBoolean(Object paramObject)
  {
    if (paramObject == Boolean.TRUE) {}
    for (boolean bool = true; bool; bool = false) {
      return bool;
    }
    return paramObject == Boolean.FALSE;
  }
  
  public static boolean isEnvironmentBound(Environment paramEnvironment, Object paramObject)
  {
    return paramEnvironment.isBound(LispLanguage.langSymbolToSymbol(paramObject));
  }
  
  public static boolean isProcedure(Object paramObject)
  {
    boolean bool = paramObject instanceof Procedure;
    if (bool) {
      return bool;
    }
    return paramObject instanceof LangObjType;
  }
  
  public static boolean isSymbol(Object paramObject)
  {
    return paramObject instanceof Symbol;
  }
  
  static boolean lambda1(Symbol paramSymbol1, Symbol paramSymbol2)
  {
    return Symbol.equals(paramSymbol1, paramSymbol2);
  }
  
  static boolean lambda2$V(Symbol paramSymbol1, Symbol paramSymbol2, Object[] paramArrayOfObject)
  {
    boolean bool1 = false;
    paramArrayOfObject = LList.makeList(paramArrayOfObject, 0);
    boolean bool2 = Symbol.equals(paramSymbol1, paramSymbol2);
    if (bool2)
    {
      if (Scheme.apply.apply3(symbol$Eq$Qu, paramSymbol2, paramArrayOfObject) != Boolean.FALSE) {
        bool1 = true;
      }
      return bool1;
    }
    return bool2;
  }
  
  public static CharSequence namespacePrefix(Namespace paramNamespace)
  {
    return paramNamespace.getPrefix();
  }
  
  public static CharSequence namespaceUri(Namespace paramNamespace)
  {
    return paramNamespace.getName();
  }
  
  public static Environment nullEnvironment()
  {
    return nullEnvironment(Boolean.FALSE);
  }
  
  public static Environment nullEnvironment(Object paramObject)
  {
    return Scheme.nullEnvironment;
  }
  
  public static Object procedureProperty(Procedure paramProcedure, Object paramObject)
  {
    return procedureProperty(paramProcedure, paramObject, Boolean.FALSE);
  }
  
  public static Object procedureProperty(Procedure paramProcedure, Object paramObject1, Object paramObject2)
  {
    return paramProcedure.getProperty(paramObject1, paramObject2);
  }
  
  public static String schemeImplementationVersion()
  {
    return Version.getVersion();
  }
  
  public static Object schemeReportEnvironment(Object paramObject)
  {
    if (Scheme.isEqv.apply2(paramObject, Lit0) != Boolean.FALSE) {
      return Scheme.r4Environment;
    }
    if (Scheme.isEqv.apply2(paramObject, Lit1) != Boolean.FALSE) {
      return Scheme.r5Environment;
    }
    return error$V("scheme-report-environment version must be 4 or 5", new Object[0]);
  }
  
  public static void setProcedureProperty$Ex(Procedure paramProcedure, Object paramObject1, Object paramObject2)
  {
    paramProcedure.setProperty(paramObject1, paramObject2);
  }
  
  public static SimpleSymbol string$To$Symbol(CharSequence paramCharSequence)
  {
    return SimpleSymbol.valueOf(paramCharSequence.toString());
  }
  
  public static String symbol$To$String(Symbol paramSymbol)
  {
    return paramSymbol.toString();
  }
  
  public static String symbolLocalName(Symbol paramSymbol)
  {
    return paramSymbol.getLocalPart();
  }
  
  public static Namespace symbolNamespace(Symbol paramSymbol)
  {
    return paramSymbol.getNamespace();
  }
  
  public static String symbolNamespaceUri(Symbol paramSymbol)
  {
    return paramSymbol.getNamespaceURI();
  }
  
  public static String symbolPrefix(Symbol paramSymbol)
  {
    return paramSymbol.getPrefix();
  }
  
  public static Object values(Object... paramVarArgs)
  {
    return Values.make(paramVarArgs);
  }
  
  public Object apply0(ModuleMethod paramModuleMethod)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply0(paramModuleMethod);
    case 18: 
      return nullEnvironment();
    case 21: 
      return interactionEnvironment();
    case 22: 
      return schemeImplementationVersion();
    case 29: 
      return baseUri();
    }
    return gentemp();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 6: 
    case 7: 
    case 16: 
    case 17: 
    case 19: 
    case 21: 
    case 22: 
    case 23: 
    case 24: 
    case 25: 
    case 26: 
    case 28: 
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 3: 
      if (isBoolean(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 4: 
      if (isSymbol(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    try
    {
      paramModuleMethod = (Symbol)paramObject;
      return symbol$To$String(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "symbol->string", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Symbol)paramObject;
      return symbolLocalName(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "symbol-local-name", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Symbol)paramObject;
      return symbolNamespace(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "symbol-namespace", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Symbol)paramObject;
      return symbolNamespaceUri(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "symbol-namespace-uri", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Symbol)paramObject;
      return symbolPrefix(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "symbol-prefix", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Namespace)paramObject;
      return namespaceUri(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "namespace-uri", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Namespace)paramObject;
      return namespacePrefix(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "namespace-prefix", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (CharSequence)paramObject;
      return string$To$Symbol(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "string->symbol", 1, paramObject);
    }
    if (isProcedure(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    return nullEnvironment(paramObject);
    return schemeReportEnvironment(paramObject);
    return force(paramObject);
    return baseUri(paramObject);
  }
  
  /* Error */
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 559	gnu/expr/ModuleMethod:selector	I
    //   4: lookupswitch	default:+36->40, 6:+44->48, 17:+70->74, 24:+91->95
    //   40: aload_0
    //   41: aload_1
    //   42: aload_2
    //   43: aload_3
    //   44: invokespecial 602	gnu/expr/ModuleBody:apply2	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   47: areturn
    //   48: aload_2
    //   49: checkcast 451	gnu/mapping/Symbol
    //   52: astore_1
    //   53: aload_3
    //   54: checkcast 451	gnu/mapping/Symbol
    //   57: astore_2
    //   58: aload_1
    //   59: aload_2
    //   60: invokestatic 604	kawa/lib/misc:lambda1	(Lgnu/mapping/Symbol;Lgnu/mapping/Symbol;)Z
    //   63: ifeq +7 -> 70
    //   66: getstatic 433	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   69: areturn
    //   70: getstatic 322	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   73: areturn
    //   74: aload_2
    //   75: checkcast 425	gnu/mapping/Environment
    //   78: astore_1
    //   79: aload_1
    //   80: aload_3
    //   81: invokestatic 606	kawa/lib/misc:isEnvironmentBound	(Lgnu/mapping/Environment;Ljava/lang/Object;)Z
    //   84: ifeq +7 -> 91
    //   87: getstatic 433	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   90: areturn
    //   91: getstatic 322	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   94: areturn
    //   95: aload_2
    //   96: checkcast 337	gnu/mapping/Procedure
    //   99: astore_1
    //   100: aload_1
    //   101: aload_3
    //   102: invokestatic 608	kawa/lib/misc:procedureProperty	(Lgnu/mapping/Procedure;Ljava/lang/Object;)Ljava/lang/Object;
    //   105: areturn
    //   106: astore_1
    //   107: new 406	gnu/mapping/WrongType
    //   110: dup
    //   111: aload_1
    //   112: ldc_w 610
    //   115: iconst_1
    //   116: aload_2
    //   117: invokespecial 411	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   120: athrow
    //   121: astore_1
    //   122: new 406	gnu/mapping/WrongType
    //   125: dup
    //   126: aload_1
    //   127: ldc_w 610
    //   130: iconst_2
    //   131: aload_3
    //   132: invokespecial 411	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   135: athrow
    //   136: astore_1
    //   137: new 406	gnu/mapping/WrongType
    //   140: dup
    //   141: aload_1
    //   142: ldc -122
    //   144: iconst_1
    //   145: aload_2
    //   146: invokespecial 411	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   149: athrow
    //   150: astore_1
    //   151: new 406	gnu/mapping/WrongType
    //   154: dup
    //   155: aload_1
    //   156: ldc 110
    //   158: iconst_1
    //   159: aload_2
    //   160: invokespecial 411	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   163: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	164	0	this	misc
    //   0	164	1	paramModuleMethod	ModuleMethod
    //   0	164	2	paramObject1	Object
    //   0	164	3	paramObject2	Object
    // Exception table:
    //   from	to	target	type
    //   48	53	106	java/lang/ClassCastException
    //   53	58	121	java/lang/ClassCastException
    //   74	79	136	java/lang/ClassCastException
    //   95	100	150	java/lang/ClassCastException
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    switch (paramModuleMethod.selector)
    {
    case 25: 
    default: 
      return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      setProcedureProperty$Ex(paramModuleMethod, paramObject2, paramObject3);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "set-procedure-property!", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject1;
      return procedureProperty(paramModuleMethod, paramObject2, paramObject3);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "procedure-property", 1, paramObject1);
    }
    return dynamicWind(paramObject1, paramObject2, paramObject3);
  }
  
  /* Error */
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 559	gnu/expr/ModuleMethod:selector	I
    //   4: lookupswitch	default:+44->48, 7:+51->55, 16:+124->128, 28:+129->133, 32:+175->179
    //   48: aload_0
    //   49: aload_1
    //   50: aload_2
    //   51: invokespecial 621	gnu/expr/ModuleBody:applyN	(Lgnu/expr/ModuleMethod;[Ljava/lang/Object;)Ljava/lang/Object;
    //   54: areturn
    //   55: aload_2
    //   56: iconst_0
    //   57: aaload
    //   58: astore_3
    //   59: aload_3
    //   60: checkcast 451	gnu/mapping/Symbol
    //   63: astore_1
    //   64: aload_2
    //   65: iconst_1
    //   66: aaload
    //   67: astore_3
    //   68: aload_3
    //   69: checkcast 451	gnu/mapping/Symbol
    //   72: astore 4
    //   74: aload_2
    //   75: arraylength
    //   76: iconst_2
    //   77: isub
    //   78: istore 5
    //   80: iload 5
    //   82: anewarray 515	java/lang/Object
    //   85: astore_3
    //   86: iload 5
    //   88: iconst_1
    //   89: isub
    //   90: istore 5
    //   92: iload 5
    //   94: ifge +17 -> 111
    //   97: aload_1
    //   98: aload 4
    //   100: aload_3
    //   101: invokestatic 623	kawa/lib/misc:lambda2$V	(Lgnu/mapping/Symbol;Lgnu/mapping/Symbol;[Ljava/lang/Object;)Z
    //   104: ifeq +20 -> 124
    //   107: getstatic 433	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   110: areturn
    //   111: aload_3
    //   112: iload 5
    //   114: aload_2
    //   115: iload 5
    //   117: iconst_2
    //   118: iadd
    //   119: aaload
    //   120: aastore
    //   121: goto -35 -> 86
    //   124: getstatic 322	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   127: areturn
    //   128: aload_2
    //   129: invokestatic 625	kawa/lib/misc:values	([Ljava/lang/Object;)Ljava/lang/Object;
    //   132: areturn
    //   133: aload_2
    //   134: iconst_0
    //   135: aaload
    //   136: astore_1
    //   137: aload_2
    //   138: arraylength
    //   139: iconst_1
    //   140: isub
    //   141: istore 5
    //   143: iload 5
    //   145: anewarray 515	java/lang/Object
    //   148: astore_3
    //   149: iload 5
    //   151: iconst_1
    //   152: isub
    //   153: istore 5
    //   155: iload 5
    //   157: ifge +9 -> 166
    //   160: aload_1
    //   161: aload_3
    //   162: invokestatic 517	kawa/lib/misc:error$V	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   165: areturn
    //   166: aload_3
    //   167: iload 5
    //   169: aload_2
    //   170: iload 5
    //   172: iconst_1
    //   173: iadd
    //   174: aaload
    //   175: aastore
    //   176: goto -27 -> 149
    //   179: aload_2
    //   180: iconst_0
    //   181: aaload
    //   182: astore_1
    //   183: aload_1
    //   184: checkcast 296	gnu/expr/GenericProc
    //   187: astore_3
    //   188: aload_2
    //   189: arraylength
    //   190: iconst_1
    //   191: isub
    //   192: istore 5
    //   194: iload 5
    //   196: anewarray 515	java/lang/Object
    //   199: astore_1
    //   200: iload 5
    //   202: iconst_1
    //   203: isub
    //   204: istore 5
    //   206: iload 5
    //   208: ifge +12 -> 220
    //   211: aload_3
    //   212: aload_1
    //   213: invokestatic 627	kawa/lib/misc:addProcedureProperties	(Lgnu/expr/GenericProc;[Ljava/lang/Object;)V
    //   216: getstatic 316	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   219: areturn
    //   220: aload_1
    //   221: iload 5
    //   223: aload_2
    //   224: iload 5
    //   226: iconst_1
    //   227: iadd
    //   228: aaload
    //   229: aastore
    //   230: goto -30 -> 200
    //   233: astore_1
    //   234: new 406	gnu/mapping/WrongType
    //   237: dup
    //   238: aload_1
    //   239: ldc_w 610
    //   242: iconst_1
    //   243: aload_3
    //   244: invokespecial 411	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   247: athrow
    //   248: astore_1
    //   249: new 406	gnu/mapping/WrongType
    //   252: dup
    //   253: aload_1
    //   254: ldc_w 610
    //   257: iconst_2
    //   258: aload_3
    //   259: invokespecial 411	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   262: athrow
    //   263: astore_2
    //   264: new 406	gnu/mapping/WrongType
    //   267: dup
    //   268: aload_2
    //   269: ldc 81
    //   271: iconst_1
    //   272: aload_1
    //   273: invokespecial 411	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   276: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	277	0	this	misc
    //   0	277	1	paramModuleMethod	ModuleMethod
    //   0	277	2	paramArrayOfObject	Object[]
    //   58	201	3	localObject	Object
    //   72	27	4	localSymbol	Symbol
    //   78	150	5	i	int
    // Exception table:
    //   from	to	target	type
    //   59	64	233	java/lang/ClassCastException
    //   68	74	248	java/lang/ClassCastException
    //   183	188	263	java/lang/ClassCastException
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match0(paramModuleMethod, paramCallContext);
    case 31: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 29: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 22: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 21: 
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
    case 6: 
    case 7: 
    case 16: 
    case 17: 
    case 19: 
    case 21: 
    case 22: 
    case 23: 
    case 24: 
    case 25: 
    case 26: 
    case 28: 
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 29: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 27: 
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
    case 15: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 14: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 13: 
      if (!(paramObject instanceof Namespace)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 12: 
      if (!(paramObject instanceof Namespace)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 11: 
      if (!(paramObject instanceof Symbol)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 10: 
      if (!(paramObject instanceof Symbol)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 9: 
      if (!(paramObject instanceof Symbol)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 8: 
      if (!(paramObject instanceof Symbol)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 5: 
      if (!(paramObject instanceof Symbol)) {
        return -786431;
      }
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
        } while (!(paramObject1 instanceof Procedure));
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      } while (!(paramObject1 instanceof Environment));
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } while (!(paramObject1 instanceof Symbol));
    paramCallContext.value1 = paramObject1;
    if (!(paramObject2 instanceof Symbol)) {
      return -786430;
    }
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 25: 
    default: 
      return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
    case 26: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    case 24: 
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
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
    case 32: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 28: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 16: 
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
    symbol$Eq$Qu = new GenericProc("symbol=?");
    symbol$Eq$Qu.setProperties(new Object[] { lambda$Fn1, lambda$Fn2 });
    procedure$Mnproperty = new GenericProc("procedure-property");
    paramCallContext = procedure$Mnproperty;
    Keyword localKeyword = Lit2;
    ModuleMethod localModuleMethod1 = set$Mnprocedure$Mnproperty$Ex;
    ModuleMethod localModuleMethod2 = procedure$Mnproperty$Fn3;
    paramCallContext.setProperties(new Object[] { localKeyword, localModuleMethod1, procedure$Mnproperty$Fn3 });
  }
  
  public class frame
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn4;
    Object msg;
    
    public frame()
    {
      this$1 = new ModuleMethod(this, 2, null, 4097);
      this$1.setProperty("source-location", "misc.scm:104");
      this.lambda$Fn4 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 2)
      {
        lambda3(paramObject);
        return Values.empty;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    void lambda3(Object paramObject)
    {
      ports.display(this.msg, paramObject);
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
  
  public class frame0
    extends ModuleBody
  {
    Object arg;
    final ModuleMethod lambda$Fn5;
    
    public frame0()
    {
      this$1 = new ModuleMethod(this, 1, null, 4097);
      this$1.setProperty("source-location", "misc.scm:107");
      this.lambda$Fn5 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 1)
      {
        lambda4(paramObject);
        return Values.empty;
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    void lambda4(Object paramObject)
    {
      ports.write(this.arg, paramObject);
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


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\misc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */