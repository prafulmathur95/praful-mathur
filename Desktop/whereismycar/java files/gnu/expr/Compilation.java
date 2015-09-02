package gnu.expr;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Scope;
import gnu.bytecode.SwitchState;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.functions.Convert;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.text.Lexer;
import gnu.text.Options;
import gnu.text.Options.OptionInfo;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import java.util.jar.JarOutputStream;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compilation
  implements SourceLocator
{
  public static final int BODY_PARSED = 4;
  public static final int CALL_WITH_CONSUMER = 2;
  public static final int CALL_WITH_CONTINUATIONS = 4;
  public static final int CALL_WITH_RETURN = 1;
  public static final int CALL_WITH_TAILCALLS = 3;
  public static final int CALL_WITH_UNSPECIFIED = 0;
  public static final int CLASS_WRITTEN = 14;
  public static final int COMPILED = 12;
  public static final int COMPILE_SETUP = 10;
  public static final int ERROR_SEEN = 100;
  public static final int MODULE_NONSTATIC = -1;
  public static final int MODULE_STATIC = 1;
  public static final int MODULE_STATIC_DEFAULT = 0;
  public static final int MODULE_STATIC_RUN = 2;
  public static final int PROLOG_PARSED = 2;
  public static final int PROLOG_PARSING = 1;
  public static final int RESOLVED = 6;
  public static final int WALKED = 8;
  public static Type[] apply0args;
  public static Method apply0method;
  public static Type[] apply1args;
  public static Method apply1method;
  public static Type[] apply2args;
  public static Method apply2method;
  public static Method apply3method;
  public static Method apply4method;
  private static Type[] applyCpsArgs;
  public static Method applyCpsMethod;
  public static Type[] applyNargs;
  public static Method applyNmethod;
  public static Method[] applymethods;
  public static gnu.bytecode.Field argsCallContextField;
  private static Compilation chainUninitialized;
  static Method checkArgCountMethod;
  public static String classPrefixDefault;
  private static final ThreadLocal<Compilation> current = new InheritableThreadLocal();
  public static boolean debugPrintExpr = false;
  public static boolean debugPrintFinalExpr;
  public static int defaultCallConvention;
  public static int defaultClassFileVersion;
  public static boolean emitSourceDebugExtAttr;
  public static final gnu.bytecode.Field falseConstant;
  public static boolean generateMainDefault;
  public static Method getCallContextInstanceMethod;
  public static Method getCurrentEnvironmentMethod;
  public static final Method getLocation1EnvironmentMethod;
  public static final Method getLocation2EnvironmentMethod;
  public static final Method getLocationMethod;
  public static final Method getProcedureBindingMethod;
  public static final Method getSymbolProcedureMethod;
  public static final Method getSymbolValueMethod;
  public static boolean inlineOk;
  public static final Type[] int1Args;
  public static ClassType javaStringType;
  static Method makeListMethod;
  public static int moduleStatic;
  public static gnu.bytecode.Field noArgsField;
  public static final ArrayType objArrayType;
  public static Options options = new Options();
  public static gnu.bytecode.Field pcCallContextField;
  public static gnu.bytecode.Field procCallContextField;
  public static ClassType scmBooleanType;
  public static ClassType scmKeywordType;
  public static ClassType scmListType;
  public static ClassType scmSequenceType;
  static final Method setNameMethod;
  public static final Type[] string1Arg;
  public static final Type[] sym1Arg;
  public static final gnu.bytecode.Field trueConstant;
  public static ClassType typeApplet;
  public static ClassType typeCallContext;
  public static ClassType typeClass;
  public static ClassType typeClassType;
  public static final ClassType typeConsumer;
  public static ClassType typeEnvironment;
  public static ClassType typeLanguage;
  public static ClassType typeLocation;
  public static ClassType typeMethodProc;
  public static ClassType typeModuleBody;
  public static ClassType typeModuleMethod;
  public static ClassType typeModuleWithContext;
  public static ClassType typeObject;
  public static ClassType typeObjectType;
  public static ClassType typePair;
  public static ClassType typeProcedure;
  public static ClassType typeProcedure0;
  public static ClassType typeProcedure1;
  public static ClassType typeProcedure2;
  public static ClassType typeProcedure3;
  public static ClassType typeProcedure4;
  public static ClassType[] typeProcedureArray;
  public static ClassType typeProcedureN;
  public static ClassType typeRunnable;
  public static ClassType typeServlet;
  public static ClassType typeString;
  public static ClassType typeSymbol;
  public static ClassType typeType;
  public static ClassType typeValues;
  public static Options.OptionInfo warnAsError;
  public static Options.OptionInfo warnInvokeUnknownMethod;
  public static Options.OptionInfo warnUndefinedVariable = options.add("warn-undefined-variable", 1, Boolean.TRUE, "warn if no compiler-visible binding for a variable");
  public static Options.OptionInfo warnUnknownMember = options.add("warn-unknown-member", 1, Boolean.TRUE, "warn if referencing an unknown method or field");
  Variable callContextVar;
  Variable callContextVarForInit;
  public String classPrefix = classPrefixDefault;
  ClassType[] classes;
  Initializer clinitChain;
  Method clinitMethod;
  public ClassType curClass;
  public LambdaExp curLambda;
  public Options currentOptions = new Options(options);
  protected ScopeExp current_scope;
  public boolean explicit;
  public Stack<Expression> exprStack;
  Method forNameHelper;
  SwitchState fswitch;
  gnu.bytecode.Field fswitchIndex;
  public boolean generateMain = generateMainDefault;
  public boolean immediate;
  private int keyUninitialized;
  int langOptions;
  protected Language language;
  public Lexer lexer;
  public NameLookup lexical;
  LitTable litTable;
  ArrayClassLoader loader;
  int localFieldIndex;
  public ClassType mainClass;
  public ModuleExp mainLambda;
  int maxSelectorValue;
  protected SourceMessages messages;
  public Method method;
  int method_counter;
  public ModuleInfo minfo;
  public ClassType moduleClass;
  gnu.bytecode.Field moduleInstanceMainField;
  Variable moduleInstanceVar;
  public boolean mustCompile = ModuleExp.alwaysCompile;
  private Compilation nextUninitialized;
  int numClasses;
  boolean pedantic;
  public Stack<Object> pendingImports;
  private int state;
  public Variable thisDecl;
  
  static
  {
    warnInvokeUnknownMethod = options.add("warn-invoke-unknown-method", 1, warnUnknownMember, "warn if invoke calls an unknown method (subsumed by warn-unknown-member)");
    warnAsError = options.add("warn-as-error", 1, Boolean.FALSE, "Make all warnings into errors");
    defaultClassFileVersion = 3211264;
    moduleStatic = 0;
    typeObject = Type.objectType;
    scmBooleanType = ClassType.make("java.lang.Boolean");
    typeString = ClassType.make("java.lang.String");
    javaStringType = typeString;
    scmKeywordType = ClassType.make("gnu.expr.Keyword");
    scmSequenceType = ClassType.make("gnu.lists.Sequence");
    scmListType = ClassType.make("gnu.lists.LList");
    typePair = ClassType.make("gnu.lists.Pair");
    objArrayType = ArrayType.make(typeObject);
    typeRunnable = ClassType.make("java.lang.Runnable");
    typeType = ClassType.make("gnu.bytecode.Type");
    typeObjectType = ClassType.make("gnu.bytecode.ObjectType");
    typeClass = Type.javalangClassType;
    typeClassType = ClassType.make("gnu.bytecode.ClassType");
    typeProcedure = ClassType.make("gnu.mapping.Procedure");
    typeLanguage = ClassType.make("gnu.expr.Language");
    typeEnvironment = ClassType.make("gnu.mapping.Environment");
    typeLocation = ClassType.make("gnu.mapping.Location");
    typeSymbol = ClassType.make("gnu.mapping.Symbol");
    getSymbolValueMethod = typeLanguage.getDeclaredMethod("getSymbolValue", 1);
    getSymbolProcedureMethod = typeLanguage.getDeclaredMethod("getSymbolProcedure", 1);
    getLocationMethod = typeLocation.addMethod("get", Type.typeArray0, Type.objectType, 1);
    getProcedureBindingMethod = typeSymbol.addMethod("getProcedure", Type.typeArray0, typeProcedure, 1);
    trueConstant = scmBooleanType.getDeclaredField("TRUE");
    falseConstant = scmBooleanType.getDeclaredField("FALSE");
    setNameMethod = typeProcedure.getDeclaredMethod("setName", 1);
    int1Args = new Type[] { Type.intType };
    string1Arg = new Type[] { javaStringType };
    sym1Arg = string1Arg;
    getLocation1EnvironmentMethod = typeEnvironment.getDeclaredMethod("getLocation", 1);
    Object localObject1 = typeSymbol;
    Object localObject2 = Type.objectType;
    ClassType localClassType1 = typeEnvironment;
    Object localObject3 = typeLocation;
    getLocation2EnvironmentMethod = localClassType1.addMethod("getLocation", new Type[] { localObject1, localObject2 }, (Type)localObject3, 17);
    localObject1 = objArrayType;
    localObject2 = Type.intType;
    localClassType1 = scmListType;
    localObject3 = scmListType;
    makeListMethod = localClassType1.addMethod("makeList", new Type[] { localObject1, localObject2 }, (Type)localObject3, 9);
    getCurrentEnvironmentMethod = typeEnvironment.addMethod("getCurrent", Type.typeArray0, typeEnvironment, 9);
    apply0args = Type.typeArray0;
    apply1args = new Type[] { typeObject };
    apply2args = new Type[] { typeObject, typeObject };
    applyNargs = new Type[] { objArrayType };
    apply0method = typeProcedure.addMethod("apply0", apply0args, typeObject, 17);
    apply1method = typeProcedure.addMethod("apply1", apply1args, typeObject, 1);
    apply2method = typeProcedure.addMethod("apply2", apply2args, typeObject, 1);
    localObject1 = typeObject;
    localObject2 = typeObject;
    localClassType1 = typeObject;
    localObject3 = typeProcedure;
    ClassType localClassType2 = typeObject;
    apply3method = ((ClassType)localObject3).addMethod("apply3", new Type[] { localObject1, localObject2, localClassType1 }, localClassType2, 1);
    localObject1 = typeObject;
    localObject2 = typeObject;
    localClassType1 = typeObject;
    localObject3 = typeObject;
    localClassType2 = typeProcedure;
    ClassType localClassType3 = typeObject;
    apply4method = localClassType2.addMethod("apply4", new Type[] { localObject1, localObject2, localClassType1, localObject3 }, localClassType3, 1);
    applyNmethod = typeProcedure.addMethod("applyN", applyNargs, typeObject, 1);
    localObject1 = typeProcedure;
    localObject2 = Type.intType;
    localClassType1 = typeProcedure;
    localObject3 = Type.voidType;
    checkArgCountMethod = localClassType1.addMethod("checkArgCount", new Type[] { localObject1, localObject2 }, (Type)localObject3, 9);
    applymethods = new Method[] { apply0method, apply1method, apply2method, apply3method, apply4method, applyNmethod };
    typeProcedure0 = ClassType.make("gnu.mapping.Procedure0");
    typeProcedure1 = ClassType.make("gnu.mapping.Procedure1");
    typeProcedure2 = ClassType.make("gnu.mapping.Procedure2");
    typeProcedure3 = ClassType.make("gnu.mapping.Procedure3");
    typeProcedure4 = ClassType.make("gnu.mapping.Procedure4");
    typeProcedureN = ClassType.make("gnu.mapping.ProcedureN");
    typeModuleBody = ClassType.make("gnu.expr.ModuleBody");
    typeModuleWithContext = ClassType.make("gnu.expr.ModuleWithContext");
    typeApplet = ClassType.make("java.applet.Applet");
    typeServlet = ClassType.make("gnu.kawa.servlet.KawaServlet");
    typeCallContext = ClassType.make("gnu.mapping.CallContext");
    typeConsumer = ClassType.make("gnu.lists.Consumer");
    getCallContextInstanceMethod = typeCallContext.getDeclaredMethod("getInstance", 0);
    typeValues = ClassType.make("gnu.mapping.Values");
    noArgsField = typeValues.getDeclaredField("noArgs");
    pcCallContextField = typeCallContext.getDeclaredField("pc");
    typeMethodProc = ClassType.make("gnu.mapping.MethodProc");
    typeModuleMethod = ClassType.make("gnu.expr.ModuleMethod");
    argsCallContextField = typeCallContext.getDeclaredField("values");
    procCallContextField = typeCallContext.getDeclaredField("proc");
    applyCpsArgs = new Type[] { typeCallContext };
    applyCpsMethod = typeProcedure.addMethod("apply", applyCpsArgs, Type.voidType, 1);
    typeProcedureArray = new ClassType[] { typeProcedure0, typeProcedure1, typeProcedure2, typeProcedure3, typeProcedure4 };
    generateMainDefault = false;
    inlineOk = true;
    classPrefixDefault = "";
    emitSourceDebugExtAttr = true;
  }
  
  public Compilation(Language paramLanguage, SourceMessages paramSourceMessages, NameLookup paramNameLookup)
  {
    this.language = paramLanguage;
    this.messages = paramSourceMessages;
    this.lexical = paramNameLookup;
  }
  
  private void checkLoop()
  {
    if (((LambdaExp)this.current_scope).getName() != "%do%loop") {
      throw new Error("internal error - bad loop state");
    }
  }
  
  public static char demangle2(char paramChar1, char paramChar2)
  {
    int i = 37;
    switch (paramChar1 << '\020' | paramChar2)
    {
    default: 
      i = 65535;
    case 5046371: 
    case 5242979: 
      return i;
    case 4259949: 
      return '&';
    case 4259956: 
      return '@';
    case 4391020: 
      return ':';
    case 4391021: 
      return ',';
    case 4456561: 
      return '"';
    case 4456564: 
      return '.';
    case 4522097: 
      return '=';
    case 4522104: 
      return '!';
    case 4653170: 
      return '>';
    case 4980802: 
      return '[';
    case 4980803: 
      return '{';
    case 4980816: 
      return '(';
    case 4980851: 
      return '<';
    case 5046382: 
      return '-';
    case 5111917: 
      return '#';
    case 5242988: 
      return '+';
    case 5308533: 
      return '?';
    case 5374018: 
      return ']';
    case 5374019: 
      return '}';
    case 5374032: 
      return ')';
    case 5439555: 
      return ';';
    case 5439596: 
      return '/';
    case 5439601: 
      return '\\';
    case 5439604: 
      return '*';
    case 5505132: 
      return '~';
    case 5570672: 
      return '^';
    }
    return '|';
  }
  
  public static String demangleName(String paramString)
  {
    return demangleName(paramString, false);
  }
  
  public static String demangleName(String paramString, boolean paramBoolean)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i7 = paramString.length();
    int m = 0;
    int i4 = 0;
    int i1 = 0;
    int n = 0;
    if (n < i7)
    {
      int k = paramString.charAt(n);
      int i = k;
      int i2 = i1;
      int j;
      if (i1 != 0)
      {
        i = k;
        i2 = i1;
        if (!paramBoolean)
        {
          j = Character.toLowerCase(k);
          i2 = 0;
        }
      }
      int i3;
      if ((!paramBoolean) && (j == 105) && (n == 0) && (i7 > 2) && (paramString.charAt(n + 1) == 's'))
      {
        k = paramString.charAt(n + 2);
        if (!Character.isLowerCase(k))
        {
          i3 = 1;
          int i5 = 1;
          int i6 = n + 1;
          if (!Character.isUpperCase(k))
          {
            i1 = i2;
            n = i6;
            m = i3;
            i4 = i5;
            if (!Character.isTitleCase(k)) {}
          }
          else
          {
            localStringBuffer.append(Character.toLowerCase(k));
            n = i6 + 1;
            i4 = i5;
            m = i3;
            i1 = i2;
          }
        }
      }
      for (;;)
      {
        n += 1;
        break;
        char c1;
        if ((j == 36) && (n + 2 < i7))
        {
          char c2 = paramString.charAt(n + 1);
          char c3 = paramString.charAt(n + 2);
          k = demangle2(c2, c3);
          if (k != 65535)
          {
            localStringBuffer.append(k);
            n += 2;
            m = 1;
            i1 = 1;
            continue;
          }
          k = j;
          i3 = m;
          if (c2 == 'T')
          {
            k = j;
            i3 = m;
            if (c3 == 'o')
            {
              k = j;
              i3 = m;
              if (n + 3 < i7)
              {
                k = j;
                i3 = m;
                if (paramString.charAt(n + 3) == '$')
                {
                  localStringBuffer.append("->");
                  n += 3;
                  m = 1;
                  i1 = 1;
                }
              }
            }
          }
        }
        else
        {
          k = j;
          i3 = m;
          if (!paramBoolean)
          {
            k = j;
            i3 = m;
            if (n > 1) {
              if (!Character.isUpperCase(j))
              {
                k = j;
                i3 = m;
                if (!Character.isTitleCase(j)) {}
              }
              else
              {
                k = j;
                i3 = m;
                if (Character.isLowerCase(paramString.charAt(n - 1)))
                {
                  localStringBuffer.append('-');
                  i3 = 1;
                  c1 = Character.toLowerCase(j);
                }
              }
            }
          }
        }
        localStringBuffer.append(c1);
        i1 = i2;
        m = i3;
      }
    }
    if (i4 != 0) {
      localStringBuffer.append('?');
    }
    if (m != 0) {
      paramString = localStringBuffer.toString();
    }
    return paramString;
  }
  
  private void dumpInitializers(Initializer paramInitializer)
  {
    for (paramInitializer = Initializer.reverse(paramInitializer); paramInitializer != null; paramInitializer = paramInitializer.next) {
      paramInitializer.emit(this);
    }
  }
  
  public static Compilation findForImmediateLiterals(int paramInt)
  {
    Object localObject3 = null;
    for (;;)
    {
      Compilation localCompilation2;
      try
      {
        Compilation localCompilation1 = chainUninitialized;
        localCompilation2 = localCompilation1.nextUninitialized;
        if (localCompilation1.keyUninitialized == paramInt)
        {
          if (localObject3 == null)
          {
            chainUninitialized = localCompilation2;
            localCompilation1.nextUninitialized = null;
            return localCompilation1;
          }
          ((Compilation)localObject3).nextUninitialized = localCompilation2;
          continue;
        }
        localObject3 = localObject1;
      }
      finally {}
      Object localObject2 = localCompilation2;
    }
  }
  
  public static final Method getConstructor(ClassType paramClassType, LambdaExp paramLambdaExp)
  {
    Object localObject = paramClassType.getDeclaredMethod("<init>", 0);
    if (localObject != null) {
      return (Method)localObject;
    }
    if (((paramLambdaExp instanceof ClassExp)) && (paramLambdaExp.staticLinkField != null))
    {
      localObject = new Type[1];
      localObject[0] = paramLambdaExp.staticLinkField.getType();
    }
    for (paramLambdaExp = (LambdaExp)localObject;; paramLambdaExp = apply0args) {
      return paramClassType.addMethod("<init>", 1, paramLambdaExp, Type.voidType);
    }
  }
  
  public static Compilation getCurrent()
  {
    return (Compilation)current.get();
  }
  
  public static boolean isValidJavaName(String paramString)
  {
    int i = paramString.length();
    if ((i == 0) || (!Character.isJavaIdentifierStart(paramString.charAt(0)))) {
      return false;
    }
    int j;
    do
    {
      j = i - 1;
      if (j <= 0) {
        break;
      }
      i = j;
    } while (Character.isJavaIdentifierPart(paramString.charAt(j)));
    return false;
    return true;
  }
  
  public static ApplyExp makeCoercion(Expression paramExpression1, Expression paramExpression2)
  {
    return new ApplyExp(new QuoteExp(Convert.getInstance()), new Expression[] { paramExpression2, paramExpression1 });
  }
  
  public static Expression makeCoercion(Expression paramExpression, Type paramType)
  {
    return makeCoercion(paramExpression, new QuoteExp(paramType));
  }
  
  public static String mangleName(String paramString)
  {
    return mangleName(paramString, -1);
  }
  
  public static String mangleName(String paramString, int paramInt)
  {
    int m;
    int n;
    String str;
    if (paramInt >= 0)
    {
      m = 1;
      n = paramString.length();
      if ((n != 6) || (!paramString.equals("*init*"))) {
        break label44;
      }
      str = "<init>";
    }
    label44:
    Object localObject;
    label158:
    label611:
    label1026:
    do
    {
      return str;
      m = 0;
      break;
      localObject = new StringBuffer(n);
      int k = 0;
      int i = 0;
      if (i < n)
      {
        char c2 = paramString.charAt(i);
        char c1 = c2;
        int j = k;
        if (k != 0)
        {
          c1 = Character.toTitleCase(c2);
          j = 0;
        }
        if (Character.isDigit(c1))
        {
          if (i == 0) {
            ((StringBuffer)localObject).append("$N");
          }
          ((StringBuffer)localObject).append(c1);
        }
        for (;;)
        {
          i += 1;
          k = j;
          break;
          if ((!Character.isLetter(c1)) && (c1 != '_')) {
            break label158;
          }
          ((StringBuffer)localObject).append(c1);
        }
        if (c1 == '$')
        {
          if (paramInt > 1) {}
          for (str = "$$";; str = "$")
          {
            ((StringBuffer)localObject).append(str);
            break;
          }
        }
        switch (c1)
        {
        default: 
          ((StringBuffer)localObject).append('$');
          ((StringBuffer)localObject).append(Character.forDigit(c1 >> '\f' & 0xF, 16));
          ((StringBuffer)localObject).append(Character.forDigit(c1 >> '\b' & 0xF, 16));
          ((StringBuffer)localObject).append(Character.forDigit(c1 >> '\004' & 0xF, 16));
          ((StringBuffer)localObject).append(Character.forDigit(c1 & 0xF, 16));
          k = i;
        }
        for (;;)
        {
          i = k;
          if (m != 0) {
            break;
          }
          j = 1;
          i = k;
          break;
          ((StringBuffer)localObject).append("$Pl");
          k = i;
          continue;
          if (m != 0)
          {
            ((StringBuffer)localObject).append("$Mn");
            k = i;
          }
          else
          {
            if (i + 1 < n) {}
            for (c1 = paramString.charAt(i + 1);; c1 = '\000')
            {
              if (c1 != '>') {
                break label611;
              }
              ((StringBuffer)localObject).append("$To$");
              k = i + 1;
              break;
            }
            k = i;
            if (!Character.isLowerCase(c1))
            {
              ((StringBuffer)localObject).append("$Mn");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$St");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Sl");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Eq");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Ls");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Gr");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$At");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Tl");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Pc");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Dt");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Cm");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$LP");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$RP");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$LB");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$RB");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$LC");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$RC");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Sq");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Dq");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Am");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Nm");
              k = i;
              continue;
              if (((StringBuffer)localObject).length() > 0) {}
              for (c1 = ((StringBuffer)localObject).charAt(0);; c1 = '\000')
              {
                if ((m != 0) || (i + 1 != n) || (!Character.isLowerCase(c1))) {
                  break label1026;
                }
                ((StringBuffer)localObject).setCharAt(0, Character.toTitleCase(c1));
                ((StringBuffer)localObject).insert(0, "is");
                k = i;
                break;
              }
              ((StringBuffer)localObject).append("$Qu");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Ex");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Cl");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$SC");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$Up");
              k = i;
              continue;
              ((StringBuffer)localObject).append("$VB");
              k = i;
            }
          }
        }
      }
      localObject = ((StringBuffer)localObject).toString();
      str = paramString;
    } while (((String)localObject).equals(paramString));
    return (String)localObject;
  }
  
  public static String mangleName(String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = -1) {
      return mangleName(paramString, i);
    }
  }
  
  public static String mangleNameIfNeeded(String paramString)
  {
    if ((paramString == null) || (isValidJavaName(paramString))) {
      return paramString;
    }
    return mangleName(paramString, 0);
  }
  
  public static String mangleURI(String paramString)
  {
    if (paramString.indexOf('/') >= 0) {}
    int k;
    for (int j = 1;; j = 0)
    {
      k = paramString.length();
      if ((k <= 6) || (!paramString.startsWith("class:"))) {
        break;
      }
      return paramString.substring(6);
    }
    String str1;
    int i;
    int m;
    int n;
    StringBuffer localStringBuffer;
    if ((k > 5) && (paramString.charAt(4) == ':') && (paramString.substring(0, 4).equalsIgnoreCase("http")))
    {
      str1 = paramString.substring(5);
      i = k - 5;
      m = 1;
      n = 0;
      localStringBuffer = new StringBuffer();
    }
    for (;;)
    {
      int i3 = str1.indexOf('/', n);
      label124:
      int i1;
      if (i3 < 0)
      {
        j = i;
        if (localStringBuffer.length() != 0) {
          break label290;
        }
        i1 = 1;
        label134:
        if ((i1 == 0) || (m == 0)) {
          break label296;
        }
        String str2 = str1.substring(n, j);
        paramString = str2;
        if (j - n > 4)
        {
          paramString = str2;
          if (str2.startsWith("www.")) {
            paramString = str2.substring(4);
          }
        }
        putURLWords(paramString, localStringBuffer);
        paramString = str1;
        k = i;
      }
      for (;;)
      {
        if (i3 >= 0) {
          break label466;
        }
        return localStringBuffer.toString();
        m = j;
        i = k;
        str1 = paramString;
        if (k <= 4) {
          break;
        }
        m = j;
        i = k;
        str1 = paramString;
        if (paramString.charAt(3) != ':') {
          break;
        }
        m = j;
        i = k;
        str1 = paramString;
        if (!paramString.substring(0, 3).equalsIgnoreCase("uri")) {
          break;
        }
        str1 = paramString.substring(4);
        i = k - 4;
        m = j;
        break;
        j = i3;
        break label124;
        label290:
        i1 = 0;
        break label134;
        label296:
        k = i;
        paramString = str1;
        if (n != j)
        {
          if (i1 == 0) {
            localStringBuffer.append('.');
          }
          int i2 = j;
          k = i;
          paramString = str1;
          if (j == i)
          {
            int i4 = str1.lastIndexOf('.', i);
            i2 = j;
            k = i;
            paramString = str1;
            if (i4 > n + 1)
            {
              i2 = j;
              k = i;
              paramString = str1;
              if (i1 == 0)
              {
                i1 = i - i4;
                if (i1 > 4)
                {
                  i2 = j;
                  k = i;
                  paramString = str1;
                  if (i1 == 5)
                  {
                    i2 = j;
                    k = i;
                    paramString = str1;
                    if (!str1.endsWith("html")) {}
                  }
                }
                else
                {
                  k = i - i1;
                  i2 = k;
                  paramString = str1.substring(0, k);
                }
              }
            }
          }
          localStringBuffer.append(paramString.substring(n, i2));
        }
      }
      label466:
      n = i3 + 1;
      i = k;
      str1 = paramString;
    }
  }
  
  private static void putURLWords(String paramString, StringBuffer paramStringBuffer)
  {
    int i = paramString.indexOf('.');
    String str = paramString;
    if (i > 0)
    {
      putURLWords(paramString.substring(i + 1), paramStringBuffer);
      paramStringBuffer.append('.');
      str = paramString.substring(0, i);
    }
    paramStringBuffer.append(str);
  }
  
  private void registerClass(ClassType paramClassType)
  {
    if (this.classes == null)
    {
      this.classes = new ClassType[20];
      if (!paramClassType.isInterface()) {
        break label133;
      }
    }
    label133:
    for (int i = 1;; i = 33)
    {
      paramClassType.addModifiers(i);
      Object localObject = paramClassType;
      if (paramClassType == this.mainClass)
      {
        localObject = paramClassType;
        if (this.numClasses > 0)
        {
          localObject = this.classes[0];
          this.classes[0] = this.mainClass;
        }
      }
      paramClassType = this.classes;
      i = this.numClasses;
      this.numClasses = (i + 1);
      paramClassType[i] = localObject;
      return;
      if (this.numClasses < this.classes.length) {
        break;
      }
      localObject = new ClassType[this.classes.length * 2];
      System.arraycopy(this.classes, 0, localObject, 0, this.numClasses);
      this.classes = ((ClassType[])localObject);
      break;
    }
  }
  
  public static int registerForImmediateLiterals(Compilation paramCompilation)
  {
    int i = 0;
    try
    {
      Compilation localCompilation = chainUninitialized;
      while (localCompilation != null)
      {
        int j = i;
        if (i <= localCompilation.keyUninitialized) {
          j = localCompilation.keyUninitialized + 1;
        }
        localCompilation = localCompilation.nextUninitialized;
        i = j;
      }
      paramCompilation.keyUninitialized = i;
      paramCompilation.nextUninitialized = chainUninitialized;
      chainUninitialized = paramCompilation;
      return i;
    }
    finally {}
  }
  
  public static void restoreCurrent(Compilation paramCompilation)
  {
    current.set(paramCompilation);
  }
  
  public static void setCurrent(Compilation paramCompilation)
  {
    current.set(paramCompilation);
  }
  
  public static Compilation setSaveCurrent(Compilation paramCompilation)
  {
    Compilation localCompilation = (Compilation)current.get();
    current.set(paramCompilation);
    return localCompilation;
  }
  
  public static void setupLiterals(int paramInt)
  {
    Compilation localCompilation = findForImmediateLiterals(paramInt);
    try
    {
      Class localClass = localCompilation.loader.loadClass(localCompilation.mainClass.getName());
      for (Literal localLiteral = localCompilation.litTable.literalsChain; localLiteral != null; localLiteral = localLiteral.next) {
        localClass.getDeclaredField(localLiteral.field.getName()).set(null, localLiteral.value);
      }
      localCompilation.litTable = null;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw new WrappedException("internal error", localThrowable);
    }
  }
  
  private Method startClassInit()
  {
    this.method = this.curClass.addMethod("<clinit>", apply0args, Type.voidType, 9);
    CodeAttr localCodeAttr = this.method.startCode();
    if ((this.generateMain) || (generatingApplet()) || (generatingServlet()))
    {
      Method localMethod = ((ClassType)Type.make(getLanguage().getClass())).getDeclaredMethod("registerEnvironment", 0);
      if (localMethod != null) {
        localCodeAttr.emitInvokeStatic(localMethod);
      }
    }
    return this.method;
  }
  
  private void varArgsToArray(LambdaExp paramLambdaExp, int paramInt, Variable paramVariable1, Type paramType, Variable paramVariable2)
  {
    CodeAttr localCodeAttr = getCode();
    Type localType = ((ArrayType)paramType).getComponentType();
    if (!"java.lang.Object".equals(localType.getName())) {}
    for (int i = 1; (paramVariable2 != null) && (i == 0); i = 0)
    {
      localCodeAttr.emitLoad(paramVariable2);
      localCodeAttr.emitPushInt(paramInt);
      localCodeAttr.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getRestArgsArray", 1));
      return;
    }
    if ((paramInt == 0) && (i == 0))
    {
      localCodeAttr.emitLoad(localCodeAttr.getArg(2));
      return;
    }
    localCodeAttr.pushScope();
    paramType = paramVariable1;
    Label localLabel;
    if (paramVariable1 == null)
    {
      paramType = localCodeAttr.addLocal(Type.intType);
      if (paramVariable2 != null)
      {
        localCodeAttr.emitLoad(paramVariable2);
        localCodeAttr.emitInvoke(typeCallContext.getDeclaredMethod("getArgCount", 0));
        if (paramInt != 0)
        {
          localCodeAttr.emitPushInt(paramInt);
          localCodeAttr.emitSub(Type.intType);
        }
        localCodeAttr.emitStore(paramType);
      }
    }
    else
    {
      localCodeAttr.emitLoad(paramType);
      localCodeAttr.emitNewArray(localType.getImplementationType());
      paramVariable1 = new Label(localCodeAttr);
      localLabel = new Label(localCodeAttr);
      localLabel.setTypes(localCodeAttr);
      localCodeAttr.emitGoto(paramVariable1);
      localLabel.define(localCodeAttr);
      localCodeAttr.emitDup(1);
      localCodeAttr.emitLoad(paramType);
      if (paramVariable2 == null) {
        break label381;
      }
      localCodeAttr.emitLoad(paramVariable2);
      label257:
      localCodeAttr.emitLoad(paramType);
      if (paramInt != 0)
      {
        localCodeAttr.emitPushInt(paramInt);
        localCodeAttr.emitAdd(Type.intType);
      }
      if (paramVariable2 == null) {
        break label395;
      }
      localCodeAttr.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getArgAsObject", 1));
    }
    for (;;)
    {
      if (i != 0) {
        CheckedTarget.emitCheckedCoerce(this, paramLambdaExp, paramLambdaExp.getName(), 0, localType, null);
      }
      localCodeAttr.emitArrayStore(localType);
      paramVariable1.define(localCodeAttr);
      localCodeAttr.emitInc(paramType, (short)-1);
      localCodeAttr.emitLoad(paramType);
      localCodeAttr.emitGotoIfIntGeZero(localLabel);
      localCodeAttr.popScope();
      return;
      localCodeAttr.emitLoad(localCodeAttr.getArg(2));
      localCodeAttr.emitArrayLength();
      break;
      label381:
      localCodeAttr.emitLoad(localCodeAttr.getArg(2));
      break label257;
      label395:
      localCodeAttr.emitArrayLoad(Type.objectType);
    }
  }
  
  public void addClass(ClassType paramClassType)
  {
    if (this.mainLambda.filename != null)
    {
      if (emitSourceDebugExtAttr) {
        paramClassType.setStratum(getLanguage().getName());
      }
      paramClassType.setSourceFile(this.mainLambda.filename);
    }
    registerClass(paramClassType);
    paramClassType.setClassfileVersion(defaultClassFileVersion);
  }
  
  public void addMainClass(ModuleExp paramModuleExp)
  {
    this.mainClass = paramModuleExp.classFor(this);
    ClassType localClassType2 = this.mainClass;
    Object localObject = paramModuleExp.getInterfaces();
    if (localObject != null) {
      localClassType2.setInterfaces((ClassType[])localObject);
    }
    ClassType localClassType1 = paramModuleExp.getSuperType();
    localObject = localClassType1;
    if (localClassType1 == null)
    {
      if (!generatingApplet()) {
        break label95;
      }
      localObject = typeApplet;
    }
    for (;;)
    {
      if (makeRunnable()) {
        localClassType2.addInterface(typeRunnable);
      }
      localClassType2.setSuper((ClassType)localObject);
      paramModuleExp.type = localClassType2;
      addClass(localClassType2);
      getConstructor(this.mainClass, paramModuleExp);
      return;
      label95:
      if (generatingServlet()) {
        localObject = typeServlet;
      } else {
        localObject = getModuleType();
      }
    }
  }
  
  public gnu.bytecode.Field allocLocalField(Type paramType, String paramString)
  {
    String str = paramString;
    if (paramString == null)
    {
      paramString = new StringBuilder().append("tmp_");
      int i = this.localFieldIndex + 1;
      this.localFieldIndex = i;
      str = i;
    }
    return this.curClass.addField(str, paramType, 0);
  }
  
  void callInitMethods(ClassType paramClassType, Vector<ClassType> paramVector)
  {
    if (paramClassType == null) {}
    do
    {
      return;
      localObject = paramClassType.getName();
    } while ("java.lang.Object".equals(localObject));
    int i = paramVector.size();
    int j;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
    } while (((ClassType)paramVector.elementAt(j)).getName() != localObject);
    return;
    paramVector.addElement(paramClassType);
    Object localObject = paramClassType.getInterfaces();
    if (localObject != null)
    {
      j = localObject.length;
      i = 0;
      while (i < j)
      {
        callInitMethods(localObject[i], paramVector);
        i += 1;
      }
    }
    i = 1;
    if (paramClassType.isInterface()) {
      if ((paramClassType instanceof PairClassType)) {
        paramClassType = ((PairClassType)paramClassType).instanceType;
      }
    }
    for (;;)
    {
      paramClassType = paramClassType.getDeclaredMethod("$finit$", i);
      if (paramClassType == null) {
        break;
      }
      paramVector = getCode();
      paramVector.emitPushThis();
      paramVector.emitInvoke(paramClassType);
      return;
      try
      {
        paramClassType = (ClassType)Type.make(Class.forName(paramClassType.getName() + "$class"));
      }
      catch (Throwable paramClassType)
      {
        return;
      }
      i = 0;
    }
  }
  
  public void cleanupAfterCompilation()
  {
    int i = 0;
    while (i < this.numClasses)
    {
      this.classes[i].cleanupAfterCompilation();
      i += 1;
    }
    this.classes = null;
    this.minfo.comp = null;
    if (this.minfo.exp != null) {
      this.minfo.exp.body = null;
    }
    this.mainLambda.body = null;
    this.mainLambda = null;
    if (!this.immediate) {
      this.litTable = null;
    }
  }
  
  public void compileConstant(Object paramObject)
  {
    CodeAttr localCodeAttr = getCode();
    if (paramObject == null)
    {
      localCodeAttr.emitPushNull();
      return;
    }
    if (((paramObject instanceof String)) && (!this.immediate))
    {
      localCodeAttr.emitPushString((String)paramObject);
      return;
    }
    localCodeAttr.emitGetStatic(compileConstantToField(paramObject));
  }
  
  public void compileConstant(Object paramObject, Target paramTarget)
  {
    if ((paramTarget instanceof IgnoreTarget)) {}
    int i;
    for (;;)
    {
      return;
      if (!(paramObject instanceof Values)) {
        break;
      }
      localObject1 = ((Values)paramObject).getValues();
      int j = localObject1.length;
      if (!(paramTarget instanceof ConsumerTarget)) {
        break;
      }
      i = 0;
      while (i < j)
      {
        compileConstant(localObject1[i], paramTarget);
        i += 1;
      }
    }
    if ((paramTarget instanceof ConditionalTarget))
    {
      localObject1 = (ConditionalTarget)paramTarget;
      paramTarget = getCode();
      if (getLanguage().isTrue(paramObject)) {}
      for (paramObject = ((ConditionalTarget)localObject1).ifTrue;; paramObject = ((ConditionalTarget)localObject1).ifFalse)
      {
        paramTarget.emitGoto((Label)paramObject);
        return;
      }
    }
    Object localObject1 = paramObject;
    Type localType;
    Object localObject4;
    if ((paramTarget instanceof StackTarget))
    {
      localType = ((StackTarget)paramTarget).getType();
      if ((localType instanceof PrimType))
      {
        try
        {
          localObject4 = localType.getSignature();
          localObject1 = getCode();
          if (localObject4 == null) {
            break label517;
          }
          if (((String)localObject4).length() == 1) {
            break label282;
          }
        }
        catch (ClassCastException localClassCastException) {}
        if ((paramObject instanceof Number))
        {
          localObject4 = (Number)paramObject;
          switch (i)
          {
          }
        }
      }
    }
    for (;;)
    {
      if (i == 67)
      {
        ((CodeAttr)localObject1).emitPushInt(((PrimType)localType).charValue(paramObject));
        return;
      }
      for (;;)
      {
        if ((localType == typeClass) && ((paramObject instanceof ClassType)))
        {
          loadClassRef((ClassType)paramObject);
          return;
          label282:
          i = ((String)localObject4).charAt(0);
          break;
          localClassCastException.emitPushInt(((Number)localObject4).intValue());
          return;
          localClassCastException.emitPushInt(((Number)localObject4).shortValue());
          return;
          localClassCastException.emitPushInt(((Number)localObject4).byteValue());
          return;
          localClassCastException.emitPushLong(((Number)localObject4).longValue());
          return;
          localClassCastException.emitPushFloat(((Number)localObject4).floatValue());
          return;
          localClassCastException.emitPushDouble(((Number)localObject4).doubleValue());
          return;
          if (i == 90)
          {
            if (PrimType.booleanValue(paramObject)) {}
            for (i = 1;; i = 0)
            {
              localClassCastException.emitPushInt(i);
              return;
            }
          }
        }
      }
      try
      {
        Object localObject2 = localType.coerceFromObject(paramObject);
        compileConstant(localObject2);
        if (localObject2 == null)
        {
          paramObject = paramTarget.getType();
          paramTarget.compileFromStack(this, (Type)paramObject);
          return;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Object localObject3 = new StringBuffer();
          if (paramObject == Values.empty)
          {
            ((StringBuffer)localObject3).append("cannot convert void to ");
            ((StringBuffer)localObject3).append(localType.getName());
            error('w', ((StringBuffer)localObject3).toString());
            localObject3 = paramObject;
          }
          else
          {
            ((StringBuffer)localObject3).append("cannot convert literal (of type ");
            if (paramObject == null) {
              ((StringBuffer)localObject3).append("<null>");
            }
            for (;;)
            {
              ((StringBuffer)localObject3).append(") to ");
              break;
              ((StringBuffer)localObject3).append(paramObject.getClass().getName());
            }
            paramObject = Type.make(localObject3.getClass());
          }
        }
      }
      label517:
      i = 32;
      break;
    }
  }
  
  public gnu.bytecode.Field compileConstantToField(Object paramObject)
  {
    paramObject = this.litTable.findLiteral(paramObject);
    if (((Literal)paramObject).field == null) {
      ((Literal)paramObject).assign(this.litTable);
    }
    return ((Literal)paramObject).field;
  }
  
  public void compileToArchive(ModuleExp paramModuleExp, String paramString)
    throws IOException
  {
    int i;
    if (paramString.endsWith(".zip"))
    {
      i = 0;
      process(12);
      paramModuleExp = new File(paramString);
      if (paramModuleExp.exists()) {
        paramModuleExp.delete();
      }
      if (i == 0) {
        break label251;
      }
    }
    label251:
    for (paramModuleExp = new JarOutputStream(new FileOutputStream(paramModuleExp));; paramModuleExp = new ZipOutputStream(new FileOutputStream(paramModuleExp)))
    {
      paramString = new byte[this.numClasses][];
      CRC32 localCRC32 = new CRC32();
      i = 0;
      while (i < this.numClasses)
      {
        Object localObject = this.classes[i];
        paramString[i] = ((ClassType)localObject).writeToArray();
        localObject = new ZipEntry(((ClassType)localObject).getName().replace('.', '/') + ".class");
        ((ZipEntry)localObject).setSize(paramString[i].length);
        localCRC32.reset();
        localCRC32.update(paramString[i], 0, paramString[i].length);
        ((ZipEntry)localObject).setCrc(localCRC32.getValue());
        paramModuleExp.putNextEntry((ZipEntry)localObject);
        paramModuleExp.write(paramString[i]);
        i += 1;
      }
      if (paramString.endsWith(".jar"))
      {
        i = 1;
        break;
      }
      paramString = paramString + ".zip";
      i = 0;
      break;
    }
    paramModuleExp.close();
  }
  
  public LambdaExp currentLambda()
  {
    return this.current_scope.currentLambda();
  }
  
  public ModuleExp currentModule()
  {
    return this.current_scope.currentModule();
  }
  
  public ScopeExp currentScope()
  {
    return this.current_scope;
  }
  
  public void error(char paramChar, Declaration paramDeclaration, String paramString1, String paramString2)
  {
    error(paramChar, paramString1 + paramDeclaration.getName() + paramString2, null, paramDeclaration);
  }
  
  public void error(char paramChar, String paramString)
  {
    char c = paramChar;
    if (paramChar == 'w')
    {
      c = paramChar;
      if (warnAsError()) {
        c = 'e';
      }
    }
    this.messages.error(c, this, paramString);
  }
  
  public void error(char paramChar, String paramString, SourceLocator paramSourceLocator)
  {
    String str = paramSourceLocator.getFileName();
    int k = paramSourceLocator.getLineNumber();
    int j = paramSourceLocator.getColumnNumber();
    int i;
    if (str != null)
    {
      paramSourceLocator = str;
      i = k;
      if (k > 0) {}
    }
    else
    {
      paramSourceLocator = getFileName();
      i = getLineNumber();
      j = getColumnNumber();
    }
    char c = paramChar;
    if (paramChar == 'w')
    {
      c = paramChar;
      if (warnAsError()) {
        c = 'e';
      }
    }
    this.messages.error(c, paramSourceLocator, i, j, paramString);
  }
  
  public void error(char paramChar, String paramString1, String paramString2, Declaration paramDeclaration)
  {
    char c = paramChar;
    if (paramChar == 'w')
    {
      c = paramChar;
      if (warnAsError()) {
        c = 'e';
      }
    }
    String str = getFileName();
    int i = getLineNumber();
    int j = getColumnNumber();
    int k = paramDeclaration.getLineNumber();
    if (k > 0)
    {
      str = paramDeclaration.getFileName();
      i = k;
      j = paramDeclaration.getColumnNumber();
    }
    this.messages.error(c, str, i, j, paramString1, paramString2);
  }
  
  public ClassType findNamedClass(String paramString)
  {
    int i = 0;
    while (i < this.numClasses)
    {
      if (paramString.equals(this.classes[i].getName())) {
        return this.classes[i];
      }
      i += 1;
    }
    return null;
  }
  
  public void freeLocalField(gnu.bytecode.Field paramField) {}
  
  public void generateApplyMethodsWithContext(LambdaExp paramLambdaExp)
  {
    if (paramLambdaExp.applyMethods == null) {}
    for (int j = 0; j == 0; j = paramLambdaExp.applyMethods.size()) {
      return;
    }
    ClassType localClassType = this.curClass;
    this.curClass = paramLambdaExp.getHeapFrameType();
    if (!this.curClass.getSuperclass().isSubtype(typeModuleWithContext)) {
      this.curClass = this.moduleClass;
    }
    Object localObject1 = typeModuleMethod;
    Method localMethod1 = this.method;
    localObject1 = typeCallContext;
    Object localObject2 = this.curClass;
    Object localObject3 = Type.voidType;
    this.method = ((ClassType)localObject2).addMethod("apply", new Type[] { localObject1 }, (Type)localObject3, 1);
    CodeAttr localCodeAttr = this.method.startCode();
    Variable localVariable = localCodeAttr.getArg(1);
    localCodeAttr.emitLoad(localVariable);
    localCodeAttr.emitGetField(pcCallContextField);
    SwitchState localSwitchState = localCodeAttr.startSwitch();
    int k = 0;
    while (k < j)
    {
      LambdaExp localLambdaExp = (LambdaExp)paramLambdaExp.applyMethods.elementAt(k);
      Method[] arrayOfMethod = localLambdaExp.primMethods;
      int i4 = arrayOfMethod.length;
      int m = 0;
      while (m < i4)
      {
        int n;
        SourceLocator localSourceLocator1;
        int i;
        Method localMethod2;
        Type[] arrayOfType;
        int i5;
        int i2;
        label404:
        int i1;
        label412:
        label426:
        int i3;
        if ((m == i4 - 1) && ((localLambdaExp.max_args < 0) || (localLambdaExp.max_args >= localLambdaExp.min_args + i4)))
        {
          n = 1;
          localSwitchState.addCase(localLambdaExp.getSelectorValue(this) + m, localCodeAttr);
          localSourceLocator1 = this.messages.swapSourceLocator(localLambdaExp);
          i = localLambdaExp.getLineNumber();
          if (i > 0) {
            localCodeAttr.putLineNumber(localLambdaExp.getFileName(), i);
          }
          localMethod2 = arrayOfMethod[m];
          arrayOfType = localMethod2.getParameterTypes();
          i5 = localLambdaExp.min_args + m;
          localObject1 = null;
          i2 = 0;
          localObject2 = localObject1;
          if (m > 4)
          {
            localObject2 = localObject1;
            if (i4 > 1)
            {
              localObject2 = localCodeAttr.addLocal(Type.intType);
              localCodeAttr.emitLoad(localVariable);
              localCodeAttr.emitInvoke(typeCallContext.getDeclaredMethod("getArgCount", 0));
              if (localLambdaExp.min_args != 0)
              {
                localCodeAttr.emitPushInt(localLambdaExp.min_args);
                localCodeAttr.emitSub(Type.intType);
              }
              localCodeAttr.emitStore((Variable)localObject2);
            }
          }
          if (!localMethod2.getStaticFlag()) {
            break label725;
          }
          i = 0;
          if (n == 0) {
            break label731;
          }
          i1 = 2;
          if (i1 + i5 >= arrayOfType.length) {
            break label737;
          }
          i1 = 1;
          if (i + i1 > 0)
          {
            localCodeAttr.emitPushThis();
            if ((this.curClass == this.moduleClass) && (this.mainClass != this.moduleClass)) {
              localCodeAttr.emitGetField(this.moduleInstanceMainField);
            }
          }
          localObject3 = localLambdaExp.firstDecl();
          localObject1 = localObject3;
          if (localObject3 != null)
          {
            localObject1 = localObject3;
            if (((Declaration)localObject3).isThisParameter()) {
              localObject1 = ((Declaration)localObject3).nextDecl();
            }
          }
          i3 = 0;
          i = i2;
          i2 = i3;
          label513:
          if (i2 >= i5) {
            break label775;
          }
          i3 = i;
          if (localObject2 != null)
          {
            i3 = i;
            if (i2 >= localLambdaExp.min_args)
            {
              localCodeAttr.emitLoad((Variable)localObject2);
              localCodeAttr.emitIfIntLEqZero();
              localCodeAttr.emitLoad(localVariable);
              localCodeAttr.emitInvoke(arrayOfMethod[(i2 - localLambdaExp.min_args)]);
              localCodeAttr.emitElse();
              i3 = i + 1;
              localCodeAttr.emitInc((Variable)localObject2, (short)-1);
            }
          }
          localCodeAttr.emitLoad(localVariable);
          if ((i2 > 4) || (n != 0) || (localLambdaExp.max_args > 4)) {
            break label743;
          }
          localCodeAttr.emitGetField(typeCallContext.getDeclaredField("value" + (i2 + 1)));
        }
        for (;;)
        {
          localObject3 = ((Declaration)localObject1).getType();
          if (localObject3 != Type.objectType)
          {
            SourceLocator localSourceLocator2 = this.messages.swapSourceLocator((SourceLocator)localObject1);
            CheckedTarget.emitCheckedCoerce(this, localLambdaExp, i2 + 1, (Type)localObject3);
            this.messages.swapSourceLocator(localSourceLocator2);
          }
          localObject1 = ((Declaration)localObject1).nextDecl();
          i2 += 1;
          i = i3;
          break label513;
          n = 0;
          break;
          label725:
          i = 1;
          break label404;
          label731:
          i1 = 1;
          break label412;
          label737:
          i1 = 0;
          break label426;
          label743:
          localCodeAttr.emitGetField(typeCallContext.getDeclaredField("values"));
          localCodeAttr.emitPushInt(i2);
          localCodeAttr.emitArrayLoad(Type.objectType);
        }
        label775:
        if (n != 0)
        {
          localObject1 = arrayOfType[(i1 + i5)];
          if (!(localObject1 instanceof ArrayType)) {
            break label841;
          }
          varArgsToArray(localLambdaExp, i5, (Variable)localObject2, (Type)localObject1, localVariable);
        }
        for (;;)
        {
          localCodeAttr.emitLoad(localVariable);
          localCodeAttr.emitInvoke(localMethod2);
          for (;;)
          {
            i -= 1;
            if (i < 0) {
              break;
            }
            localCodeAttr.emitFi();
          }
          label841:
          if ("gnu.lists.LList".equals(((Type)localObject1).getName()))
          {
            localCodeAttr.emitLoad(localVariable);
            localCodeAttr.emitPushInt(i5);
            localCodeAttr.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getRestArgsList", 1));
          }
          else
          {
            if (localObject1 != typeCallContext) {
              break;
            }
            localCodeAttr.emitLoad(localVariable);
          }
        }
        throw new RuntimeException("unsupported #!rest type:" + localObject1);
        if (defaultCallConvention < 2) {
          Target.pushObject.compileFromStack(this, localLambdaExp.getReturnType());
        }
        this.messages.swapSourceLocator(localSourceLocator1);
        localCodeAttr.emitReturn();
        m += 1;
      }
      k += 1;
    }
    localSwitchState.addDefault(localCodeAttr);
    localCodeAttr.emitInvokeStatic(typeModuleMethod.getDeclaredMethod("applyError", 0));
    localCodeAttr.emitReturn();
    localSwitchState.finish(localCodeAttr);
    this.method = localMethod1;
    this.curClass = localClassType;
  }
  
  public void generateApplyMethodsWithoutContext(LambdaExp paramLambdaExp)
  {
    if (paramLambdaExp.applyMethods == null) {}
    for (int n = 0; n == 0; n = paramLambdaExp.applyMethods.size()) {
      return;
    }
    ClassType localClassType1 = this.curClass;
    this.curClass = paramLambdaExp.getHeapFrameType();
    ClassType localClassType2 = typeModuleMethod;
    if (!this.curClass.getSuperclass().isSubtype(typeModuleBody)) {
      this.curClass = this.moduleClass;
    }
    Method localMethod1 = this.method;
    Object localObject2 = null;
    int i;
    int k;
    Object localObject3;
    Object localObject1;
    Object localObject4;
    int i1;
    label110:
    LambdaExp localLambdaExp;
    Method[] arrayOfMethod;
    int i3;
    int j;
    label170:
    int i4;
    int i2;
    int m;
    if (defaultCallConvention >= 2)
    {
      i = 5;
      if (i >= 6) {
        break label1276;
      }
      k = 0;
      localObject3 = null;
      localObject1 = null;
      localObject4 = null;
      i1 = 0;
      if (i1 >= n) {
        break label1160;
      }
      localLambdaExp = (LambdaExp)paramLambdaExp.applyMethods.elementAt(i1);
      arrayOfMethod = localLambdaExp.primMethods;
      i3 = arrayOfMethod.length;
      if ((localLambdaExp.max_args >= 0) && (localLambdaExp.max_args < localLambdaExp.min_args + i3)) {
        break label263;
      }
      j = 1;
      i4 = 0;
      i2 = 0;
      if (i >= 5) {
        break label269;
      }
      i4 = i - localLambdaExp.min_args;
      if ((i4 >= 0) && (i4 < i3))
      {
        m = i2;
        if (i4 == i3 - 1)
        {
          m = i2;
          if (j == 0) {}
        }
      }
      else
      {
        m = 1;
      }
      i3 = 1;
      i2 = 0;
      label235:
      if (m == 0) {
        break label323;
      }
      j = k;
    }
    for (;;)
    {
      i1 += 1;
      k = j;
      break label110;
      i = 0;
      break;
      label263:
      j = 0;
      break label170;
      label269:
      i2 = 5 - localLambdaExp.min_args;
      m = i4;
      if (i2 > 0)
      {
        m = i4;
        if (i3 <= i2)
        {
          m = i4;
          if (j == 0) {
            m = 1;
          }
        }
      }
      i4 = i3 - 1;
      i2 = j;
      break label235;
      label323:
      j = k;
      SourceLocator localSourceLocator1;
      Method localMethod2;
      Type[] arrayOfType;
      int i5;
      Object localObject5;
      Object localObject6;
      label653:
      label661:
      label675:
      Object localObject7;
      if (k == 0)
      {
        if (i < 5)
        {
          localObject2 = "apply" + i;
          localObject3 = new Type[i + 1];
          j = i;
          for (;;)
          {
            localObject4 = localObject3;
            localObject1 = localObject2;
            if (j <= 0) {
              break;
            }
            localObject3[j] = typeObject;
            j -= 1;
          }
        }
        localObject1 = "applyN";
        localObject4 = new Type[2];
        localObject4[1] = objArrayType;
        localObject4[0] = localClassType2;
        localObject3 = this.curClass;
        if (defaultCallConvention >= 2)
        {
          localObject2 = Type.voidType;
          this.method = ((ClassType)localObject3).addMethod((String)localObject1, (Type[])localObject4, (Type)localObject2, 1);
          localObject2 = this.method.startCode();
          ((CodeAttr)localObject2).emitLoad(((CodeAttr)localObject2).getArg(1));
          ((CodeAttr)localObject2).emitGetField(localClassType2.getField("selector"));
          localObject3 = ((CodeAttr)localObject2).startSwitch();
          j = 1;
        }
      }
      else
      {
        ((SwitchState)localObject3).addCase(localLambdaExp.getSelectorValue(this), (CodeAttr)localObject2);
        localSourceLocator1 = this.messages.swapSourceLocator(localLambdaExp);
        k = localLambdaExp.getLineNumber();
        if (k > 0) {
          ((CodeAttr)localObject2).putLineNumber(localLambdaExp.getFileName(), k);
        }
        localMethod2 = arrayOfMethod[i4];
        arrayOfType = localMethod2.getParameterTypes();
        i5 = localLambdaExp.min_args + i4;
        localObject5 = null;
        i4 = 0;
        localObject6 = localObject5;
        if (i > 4)
        {
          localObject6 = localObject5;
          if (i3 > 1)
          {
            localObject6 = ((CodeAttr)localObject2).addLocal(Type.intType);
            ((CodeAttr)localObject2).emitLoad(((CodeAttr)localObject2).getArg(2));
            ((CodeAttr)localObject2).emitArrayLength();
            if (localLambdaExp.min_args != 0)
            {
              ((CodeAttr)localObject2).emitPushInt(localLambdaExp.min_args);
              ((CodeAttr)localObject2).emitSub(Type.intType);
            }
            ((CodeAttr)localObject2).emitStore((Variable)localObject6);
          }
        }
        if (!localMethod2.getStaticFlag()) {
          break label933;
        }
        k = 0;
        if (i2 == 0) {
          break label939;
        }
        m = 1;
        if (m + i5 >= arrayOfType.length) {
          break label945;
        }
        m = 1;
        if (k + m > 0)
        {
          ((CodeAttr)localObject2).emitPushThis();
          if ((this.curClass == this.moduleClass) && (this.mainClass != this.moduleClass)) {
            ((CodeAttr)localObject2).emitGetField(this.moduleInstanceMainField);
          }
        }
        localObject7 = localLambdaExp.firstDecl();
        localObject5 = localObject7;
        if (localObject7 != null)
        {
          localObject5 = localObject7;
          if (((Declaration)localObject7).isThisParameter()) {
            localObject5 = ((Declaration)localObject7).nextDecl();
          }
        }
        i3 = 0;
        k = i4;
        label759:
        if (i3 >= i5) {
          break label976;
        }
        i4 = k;
        if (localObject6 != null)
        {
          i4 = k;
          if (i3 >= localLambdaExp.min_args)
          {
            ((CodeAttr)localObject2).emitLoad((Variable)localObject6);
            ((CodeAttr)localObject2).emitIfIntLEqZero();
            ((CodeAttr)localObject2).emitInvoke(arrayOfMethod[(i3 - localLambdaExp.min_args)]);
            ((CodeAttr)localObject2).emitElse();
            i4 = k + 1;
            ((CodeAttr)localObject2).emitInc((Variable)localObject6, (short)-1);
          }
        }
        localObject7 = null;
        if (i > 4) {
          break label951;
        }
        localObject7 = ((CodeAttr)localObject2).getArg(i3 + 2);
        ((CodeAttr)localObject2).emitLoad((Variable)localObject7);
      }
      for (;;)
      {
        Type localType = ((Declaration)localObject5).getType();
        if (localType != Type.objectType)
        {
          SourceLocator localSourceLocator2 = this.messages.swapSourceLocator((SourceLocator)localObject5);
          CheckedTarget.emitCheckedCoerce(this, localLambdaExp, i3 + 1, localType, (Variable)localObject7);
          this.messages.swapSourceLocator(localSourceLocator2);
        }
        localObject5 = ((Declaration)localObject5).nextDecl();
        i3 += 1;
        k = i4;
        break label759;
        localObject2 = Type.objectType;
        break;
        label933:
        k = 1;
        break label653;
        label939:
        m = 0;
        break label661;
        label945:
        m = 0;
        break label675;
        label951:
        ((CodeAttr)localObject2).emitLoad(((CodeAttr)localObject2).getArg(2));
        ((CodeAttr)localObject2).emitPushInt(i3);
        ((CodeAttr)localObject2).emitArrayLoad(Type.objectType);
      }
      label976:
      if (i2 != 0)
      {
        localObject5 = arrayOfType[(m + i5)];
        if (!(localObject5 instanceof ArrayType)) {
          break label1036;
        }
        varArgsToArray(localLambdaExp, i5, (Variable)localObject6, (Type)localObject5, null);
      }
      for (;;)
      {
        ((CodeAttr)localObject2).emitInvoke(localMethod2);
        for (;;)
        {
          k -= 1;
          if (k < 0) {
            break;
          }
          ((CodeAttr)localObject2).emitFi();
        }
        label1036:
        if ("gnu.lists.LList".equals(((Type)localObject5).getName()))
        {
          ((CodeAttr)localObject2).emitLoad(((CodeAttr)localObject2).getArg(2));
          ((CodeAttr)localObject2).emitPushInt(i5);
          ((CodeAttr)localObject2).emitInvokeStatic(makeListMethod);
        }
        else
        {
          if (localObject5 != typeCallContext) {
            break;
          }
          ((CodeAttr)localObject2).emitLoad(((CodeAttr)localObject2).getArg(2));
        }
      }
      throw new RuntimeException("unsupported #!rest type:" + localObject5);
      if (defaultCallConvention < 2) {
        Target.pushObject.compileFromStack(this, localLambdaExp.getReturnType());
      }
      this.messages.swapSourceLocator(localSourceLocator1);
      ((CodeAttr)localObject2).emitReturn();
    }
    label1160:
    if (k != 0)
    {
      ((SwitchState)localObject3).addDefault((CodeAttr)localObject2);
      if (defaultCallConvention < 2) {
        break label1211;
      }
      ((CodeAttr)localObject2).emitInvokeStatic(typeModuleMethod.getDeclaredMethod("applyError", 0));
    }
    for (;;)
    {
      ((CodeAttr)localObject2).emitReturn();
      ((SwitchState)localObject3).finish((CodeAttr)localObject2);
      i += 1;
      break;
      label1211:
      if (i > 4) {}
      for (j = 2;; j = i + 1)
      {
        k = 0;
        while (k < j + 1)
        {
          ((CodeAttr)localObject2).emitLoad(((CodeAttr)localObject2).getArg(k));
          k += 1;
        }
      }
      ((CodeAttr)localObject2).emitInvokeSpecial(typeModuleBody.getDeclaredMethod((String)localObject1, (Type[])localObject4));
    }
    label1276:
    this.method = localMethod1;
    this.curClass = localClassType1;
  }
  
  /* Error */
  void generateBytecode()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 1615	gnu/expr/Compilation:getModule	()Lgnu/expr/ModuleExp;
    //   4: astore 5
    //   6: getstatic 1617	gnu/expr/Compilation:debugPrintFinalExpr	Z
    //   9: ifeq +73 -> 82
    //   12: invokestatic 1623	gnu/mapping/OutPort:errDefault	()Lgnu/mapping/OutPort;
    //   15: astore_2
    //   16: aload_2
    //   17: new 1140	java/lang/StringBuilder
    //   20: dup
    //   21: invokespecial 1141	java/lang/StringBuilder:<init>	()V
    //   24: ldc_w 1625
    //   27: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: aload 5
    //   32: invokevirtual 1626	gnu/expr/ModuleExp:getName	()Ljava/lang/String;
    //   35: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: ldc_w 1628
    //   41: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: aload_0
    //   45: getfield 873	gnu/expr/Compilation:mainClass	Lgnu/bytecode/ClassType;
    //   48: invokevirtual 898	gnu/bytecode/ClassType:getName	()Ljava/lang/String;
    //   51: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: ldc_w 1630
    //   57: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: invokevirtual 1152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokevirtual 1633	gnu/mapping/OutPort:println	(Ljava/lang/String;)V
    //   66: aload 5
    //   68: aload_2
    //   69: invokevirtual 1637	gnu/expr/ModuleExp:print	(Lgnu/mapping/OutPort;)V
    //   72: aload_2
    //   73: bipush 93
    //   75: invokevirtual 1640	gnu/mapping/OutPort:println	(C)V
    //   78: aload_2
    //   79: invokevirtual 1643	gnu/mapping/OutPort:flush	()V
    //   82: aload_0
    //   83: invokevirtual 1136	gnu/expr/Compilation:getModuleType	()Lgnu/bytecode/ClassType;
    //   86: astore_2
    //   87: aload_0
    //   88: getfield 873	gnu/expr/Compilation:mainClass	Lgnu/bytecode/ClassType;
    //   91: invokevirtual 1488	gnu/bytecode/ClassType:getSuperclass	()Lgnu/bytecode/ClassType;
    //   94: aload_2
    //   95: invokevirtual 1492	gnu/bytecode/ClassType:isSubtype	(Lgnu/bytecode/Type;)Z
    //   98: ifeq +460 -> 558
    //   101: aload_0
    //   102: aload_0
    //   103: getfield 873	gnu/expr/Compilation:mainClass	Lgnu/bytecode/ClassType;
    //   106: putfield 1494	gnu/expr/Compilation:moduleClass	Lgnu/bytecode/ClassType;
    //   109: aload_0
    //   110: aload 5
    //   112: getfield 1129	gnu/expr/ModuleExp:type	Lgnu/bytecode/ClassType;
    //   115: putfield 945	gnu/expr/Compilation:curClass	Lgnu/bytecode/ClassType;
    //   118: aload_0
    //   119: getfield 1645	gnu/expr/Compilation:curLambda	Lgnu/expr/LambdaExp;
    //   122: astore 6
    //   124: aload_0
    //   125: aload 5
    //   127: putfield 1645	gnu/expr/Compilation:curLambda	Lgnu/expr/LambdaExp;
    //   130: aload 5
    //   132: invokevirtual 1648	gnu/expr/ModuleExp:isHandlingTailCalls	()Z
    //   135: ifeq +469 -> 604
    //   138: iconst_1
    //   139: anewarray 248	gnu/bytecode/Type
    //   142: astore_2
    //   143: aload_2
    //   144: iconst_0
    //   145: getstatic 485	gnu/expr/Compilation:typeCallContext	Lgnu/bytecode/ClassType;
    //   148: aastore
    //   149: aload 5
    //   151: getfield 1651	gnu/expr/ModuleExp:heapFrame	Lgnu/bytecode/Variable;
    //   154: astore 7
    //   156: aload 5
    //   158: invokevirtual 1654	gnu/expr/ModuleExp:isStatic	()Z
    //   161: istore 17
    //   163: aload_0
    //   164: aload_0
    //   165: getfield 945	gnu/expr/Compilation:curClass	Lgnu/bytecode/ClassType;
    //   168: ldc_w 1656
    //   171: aload_2
    //   172: getstatic 433	gnu/bytecode/Type:voidType	Lgnu/bytecode/PrimType;
    //   175: bipush 17
    //   177: invokevirtual 354	gnu/bytecode/ClassType:addMethod	(Ljava/lang/String;[Lgnu/bytecode/Type;Lgnu/bytecode/Type;I)Lgnu/bytecode/Method;
    //   180: putfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   183: aload_0
    //   184: getfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   187: invokevirtual 1659	gnu/bytecode/Method:initCode	()V
    //   190: aload_0
    //   191: invokevirtual 982	gnu/expr/Compilation:getCode	()Lgnu/bytecode/CodeAttr;
    //   194: astore_3
    //   195: aload_0
    //   196: getfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   199: invokevirtual 1536	gnu/bytecode/Method:getStaticFlag	()Z
    //   202: ifeq +481 -> 683
    //   205: aconst_null
    //   206: astore_2
    //   207: aload_0
    //   208: aload_2
    //   209: putfield 1661	gnu/expr/Compilation:thisDecl	Lgnu/bytecode/Variable;
    //   212: aload 5
    //   214: aload 5
    //   216: getfield 1664	gnu/expr/ModuleExp:thisVariable	Lgnu/bytecode/Variable;
    //   219: putfield 1667	gnu/expr/ModuleExp:closureEnv	Lgnu/bytecode/Variable;
    //   222: aload 5
    //   224: invokevirtual 1654	gnu/expr/ModuleExp:isStatic	()Z
    //   227: ifeq +470 -> 697
    //   230: aconst_null
    //   231: astore_2
    //   232: aload 5
    //   234: aload_2
    //   235: putfield 1651	gnu/expr/ModuleExp:heapFrame	Lgnu/bytecode/Variable;
    //   238: aload 5
    //   240: aload_0
    //   241: invokevirtual 1670	gnu/expr/ModuleExp:allocChildClasses	(Lgnu/expr/Compilation;)V
    //   244: aload 5
    //   246: invokevirtual 1648	gnu/expr/ModuleExp:isHandlingTailCalls	()Z
    //   249: ifne +10 -> 259
    //   252: aload_0
    //   253: invokevirtual 1673	gnu/expr/Compilation:usingCPStyle	()Z
    //   256: ifeq +44 -> 300
    //   259: aload_0
    //   260: new 1675	gnu/bytecode/Variable
    //   263: dup
    //   264: ldc_w 1677
    //   267: getstatic 485	gnu/expr/Compilation:typeCallContext	Lgnu/bytecode/ClassType;
    //   270: invokespecial 1680	gnu/bytecode/Variable:<init>	(Ljava/lang/String;Lgnu/bytecode/Type;)V
    //   273: putfield 1682	gnu/expr/Compilation:callContextVar	Lgnu/bytecode/Variable;
    //   276: aload 5
    //   278: invokevirtual 1685	gnu/expr/ModuleExp:getVarScope	()Lgnu/bytecode/Scope;
    //   281: aload_0
    //   282: getfield 1661	gnu/expr/Compilation:thisDecl	Lgnu/bytecode/Variable;
    //   285: aload_0
    //   286: getfield 1682	gnu/expr/Compilation:callContextVar	Lgnu/bytecode/Variable;
    //   289: invokevirtual 1691	gnu/bytecode/Scope:addVariableAfter	(Lgnu/bytecode/Variable;Lgnu/bytecode/Variable;)V
    //   292: aload_0
    //   293: getfield 1682	gnu/expr/Compilation:callContextVar	Lgnu/bytecode/Variable;
    //   296: iconst_1
    //   297: invokevirtual 1695	gnu/bytecode/Variable:setParameter	(Z)V
    //   300: aload 5
    //   302: invokevirtual 1696	gnu/expr/ModuleExp:getLineNumber	()I
    //   305: istore 15
    //   307: iload 15
    //   309: ifle +14 -> 323
    //   312: aload_3
    //   313: aload 5
    //   315: invokevirtual 1697	gnu/expr/ModuleExp:getFileName	()Ljava/lang/String;
    //   318: iload 15
    //   320: invokevirtual 1529	gnu/bytecode/CodeAttr:putLineNumber	(Ljava/lang/String;I)V
    //   323: aload 5
    //   325: aload_0
    //   326: invokevirtual 1700	gnu/expr/ModuleExp:allocParameters	(Lgnu/expr/Compilation;)V
    //   329: aload 5
    //   331: aload_0
    //   332: invokevirtual 1703	gnu/expr/ModuleExp:enterFunction	(Lgnu/expr/Compilation;)V
    //   335: aload_0
    //   336: invokevirtual 1673	gnu/expr/Compilation:usingCPStyle	()Z
    //   339: ifeq +32 -> 371
    //   342: aload_0
    //   343: invokevirtual 1706	gnu/expr/Compilation:loadCallContext	()V
    //   346: aload_3
    //   347: getstatic 505	gnu/expr/Compilation:pcCallContextField	Lgnu/bytecode/Field;
    //   350: invokevirtual 1497	gnu/bytecode/CodeAttr:emitGetField	(Lgnu/bytecode/Field;)V
    //   353: aload_0
    //   354: aload_3
    //   355: invokevirtual 1501	gnu/bytecode/CodeAttr:startSwitch	()Lgnu/bytecode/SwitchState;
    //   358: putfield 1708	gnu/expr/Compilation:fswitch	Lgnu/bytecode/SwitchState;
    //   361: aload_0
    //   362: getfield 1708	gnu/expr/Compilation:fswitch	Lgnu/bytecode/SwitchState;
    //   365: iconst_0
    //   366: aload_3
    //   367: invokevirtual 1519	gnu/bytecode/SwitchState:addCase	(ILgnu/bytecode/CodeAttr;)Z
    //   370: pop
    //   371: aload 5
    //   373: aload_0
    //   374: invokevirtual 1711	gnu/expr/ModuleExp:compileBody	(Lgnu/expr/Compilation;)V
    //   377: aload 5
    //   379: aload_0
    //   380: invokevirtual 1714	gnu/expr/ModuleExp:compileEnd	(Lgnu/expr/Compilation;)V
    //   383: aconst_null
    //   384: astore_3
    //   385: aconst_null
    //   386: astore_2
    //   387: aconst_null
    //   388: astore 4
    //   390: aload_0
    //   391: getfield 945	gnu/expr/Compilation:curClass	Lgnu/bytecode/ClassType;
    //   394: aload_0
    //   395: getfield 873	gnu/expr/Compilation:mainClass	Lgnu/bytecode/ClassType;
    //   398: if_acmpne +628 -> 1026
    //   401: aload_0
    //   402: getfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   405: astore 8
    //   407: aload_0
    //   408: getfield 1682	gnu/expr/Compilation:callContextVar	Lgnu/bytecode/Variable;
    //   411: astore 9
    //   413: aload_0
    //   414: aconst_null
    //   415: putfield 1682	gnu/expr/Compilation:callContextVar	Lgnu/bytecode/Variable;
    //   418: aload_0
    //   419: invokespecial 1716	gnu/expr/Compilation:startClassInit	()Lgnu/bytecode/Method;
    //   422: astore 4
    //   424: aload_0
    //   425: aload 4
    //   427: putfield 1718	gnu/expr/Compilation:clinitMethod	Lgnu/bytecode/Method;
    //   430: aload_0
    //   431: invokevirtual 982	gnu/expr/Compilation:getCode	()Lgnu/bytecode/CodeAttr;
    //   434: astore 10
    //   436: new 1033	gnu/bytecode/Label
    //   439: dup
    //   440: aload 10
    //   442: invokespecial 1036	gnu/bytecode/Label:<init>	(Lgnu/bytecode/CodeAttr;)V
    //   445: astore_3
    //   446: new 1033	gnu/bytecode/Label
    //   449: dup
    //   450: aload 10
    //   452: invokespecial 1036	gnu/bytecode/Label:<init>	(Lgnu/bytecode/CodeAttr;)V
    //   455: astore_2
    //   456: aload 10
    //   458: aload_2
    //   459: aload_3
    //   460: invokevirtual 1722	gnu/bytecode/CodeAttr:fixupChain	(Lgnu/bytecode/Label;Lgnu/bytecode/Label;)V
    //   463: iload 17
    //   465: ifeq +68 -> 533
    //   468: aload_0
    //   469: aload 5
    //   471: invokevirtual 1725	gnu/expr/Compilation:generateConstructor	(Lgnu/expr/LambdaExp;)V
    //   474: aload 10
    //   476: aload_0
    //   477: getfield 1494	gnu/expr/Compilation:moduleClass	Lgnu/bytecode/ClassType;
    //   480: invokevirtual 1728	gnu/bytecode/CodeAttr:emitNew	(Lgnu/bytecode/ClassType;)V
    //   483: aload 10
    //   485: aload_0
    //   486: getfield 1494	gnu/expr/Compilation:moduleClass	Lgnu/bytecode/ClassType;
    //   489: invokevirtual 1730	gnu/bytecode/CodeAttr:emitDup	(Lgnu/bytecode/Type;)V
    //   492: aload 10
    //   494: aload_0
    //   495: getfield 1494	gnu/expr/Compilation:moduleClass	Lgnu/bytecode/ClassType;
    //   498: getfield 1733	gnu/bytecode/ClassType:constructor	Lgnu/bytecode/Method;
    //   501: invokevirtual 1609	gnu/bytecode/CodeAttr:emitInvokeSpecial	(Lgnu/bytecode/Method;)V
    //   504: aload_0
    //   505: aload_0
    //   506: getfield 1494	gnu/expr/Compilation:moduleClass	Lgnu/bytecode/ClassType;
    //   509: ldc_w 1735
    //   512: aload_0
    //   513: getfield 1494	gnu/expr/Compilation:moduleClass	Lgnu/bytecode/ClassType;
    //   516: bipush 25
    //   518: invokevirtual 1156	gnu/bytecode/ClassType:addField	(Ljava/lang/String;Lgnu/bytecode/Type;I)Lgnu/bytecode/Field;
    //   521: putfield 1538	gnu/expr/Compilation:moduleInstanceMainField	Lgnu/bytecode/Field;
    //   524: aload 10
    //   526: aload_0
    //   527: getfield 1538	gnu/expr/Compilation:moduleInstanceMainField	Lgnu/bytecode/Field;
    //   530: invokevirtual 1738	gnu/bytecode/CodeAttr:emitPutStatic	(Lgnu/bytecode/Field;)V
    //   533: aload_0
    //   534: getfield 1740	gnu/expr/Compilation:clinitChain	Lgnu/expr/Initializer;
    //   537: astore 11
    //   539: aload 11
    //   541: ifnull +165 -> 706
    //   544: aload_0
    //   545: aconst_null
    //   546: putfield 1740	gnu/expr/Compilation:clinitChain	Lgnu/expr/Initializer;
    //   549: aload_0
    //   550: aload 11
    //   552: invokespecial 1742	gnu/expr/Compilation:dumpInitializers	(Lgnu/expr/Initializer;)V
    //   555: goto -22 -> 533
    //   558: aload_0
    //   559: new 257	gnu/bytecode/ClassType
    //   562: dup
    //   563: aload_0
    //   564: ldc_w 1744
    //   567: invokevirtual 1747	gnu/expr/Compilation:generateClassName	(Ljava/lang/String;)Ljava/lang/String;
    //   570: invokespecial 1748	gnu/bytecode/ClassType:<init>	(Ljava/lang/String;)V
    //   573: putfield 1494	gnu/expr/Compilation:moduleClass	Lgnu/bytecode/ClassType;
    //   576: aload_0
    //   577: getfield 1494	gnu/expr/Compilation:moduleClass	Lgnu/bytecode/ClassType;
    //   580: aload_2
    //   581: invokevirtual 1126	gnu/bytecode/ClassType:setSuper	(Lgnu/bytecode/ClassType;)V
    //   584: aload_0
    //   585: aload_0
    //   586: getfield 1494	gnu/expr/Compilation:moduleClass	Lgnu/bytecode/ClassType;
    //   589: invokevirtual 1131	gnu/expr/Compilation:addClass	(Lgnu/bytecode/ClassType;)V
    //   592: aload_0
    //   593: aload_0
    //   594: getfield 1494	gnu/expr/Compilation:moduleClass	Lgnu/bytecode/ClassType;
    //   597: aconst_null
    //   598: invokevirtual 1751	gnu/expr/Compilation:generateConstructor	(Lgnu/bytecode/ClassType;Lgnu/expr/LambdaExp;)V
    //   601: goto -492 -> 109
    //   604: aload 5
    //   606: getfield 1752	gnu/expr/ModuleExp:min_args	I
    //   609: aload 5
    //   611: getfield 1753	gnu/expr/ModuleExp:max_args	I
    //   614: if_icmpne +12 -> 626
    //   617: aload 5
    //   619: getfield 1752	gnu/expr/ModuleExp:min_args	I
    //   622: iconst_4
    //   623: if_icmple +24 -> 647
    //   626: iconst_1
    //   627: anewarray 248	gnu/bytecode/Type
    //   630: astore_2
    //   631: aload_2
    //   632: iconst_0
    //   633: new 287	gnu/bytecode/ArrayType
    //   636: dup
    //   637: getstatic 253	gnu/expr/Compilation:typeObject	Lgnu/bytecode/ClassType;
    //   640: invokespecial 1755	gnu/bytecode/ArrayType:<init>	(Lgnu/bytecode/Type;)V
    //   643: aastore
    //   644: goto -495 -> 149
    //   647: aload 5
    //   649: getfield 1752	gnu/expr/ModuleExp:min_args	I
    //   652: istore 15
    //   654: iload 15
    //   656: anewarray 248	gnu/bytecode/Type
    //   659: astore_3
    //   660: iload 15
    //   662: iconst_1
    //   663: isub
    //   664: istore 15
    //   666: aload_3
    //   667: astore_2
    //   668: iload 15
    //   670: iflt -521 -> 149
    //   673: aload_3
    //   674: iload 15
    //   676: getstatic 253	gnu/expr/Compilation:typeObject	Lgnu/bytecode/ClassType;
    //   679: aastore
    //   680: goto -20 -> 660
    //   683: aload 5
    //   685: aload 5
    //   687: getfield 1129	gnu/expr/ModuleExp:type	Lgnu/bytecode/ClassType;
    //   690: invokevirtual 1759	gnu/expr/ModuleExp:declareThis	(Lgnu/bytecode/ClassType;)Lgnu/bytecode/Variable;
    //   693: astore_2
    //   694: goto -487 -> 207
    //   697: aload 5
    //   699: getfield 1664	gnu/expr/ModuleExp:thisVariable	Lgnu/bytecode/Variable;
    //   702: astore_2
    //   703: goto -471 -> 232
    //   706: aload 5
    //   708: invokevirtual 1762	gnu/expr/ModuleExp:staticInitRun	()Z
    //   711: ifeq +27 -> 738
    //   714: aload 10
    //   716: aload_0
    //   717: getfield 1538	gnu/expr/Compilation:moduleInstanceMainField	Lgnu/bytecode/Field;
    //   720: invokevirtual 1224	gnu/bytecode/CodeAttr:emitGetStatic	(Lgnu/bytecode/Field;)V
    //   723: aload 10
    //   725: getstatic 469	gnu/expr/Compilation:typeModuleBody	Lgnu/bytecode/ClassType;
    //   728: ldc_w 1656
    //   731: iconst_0
    //   732: invokevirtual 339	gnu/bytecode/ClassType:getDeclaredMethod	(Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   735: invokevirtual 1017	gnu/bytecode/CodeAttr:emitInvoke	(Lgnu/bytecode/Method;)V
    //   738: aload 10
    //   740: invokevirtual 1586	gnu/bytecode/CodeAttr:emitReturn	()V
    //   743: aload_0
    //   744: getfield 1494	gnu/expr/Compilation:moduleClass	Lgnu/bytecode/ClassType;
    //   747: aload_0
    //   748: getfield 873	gnu/expr/Compilation:mainClass	Lgnu/bytecode/ClassType;
    //   751: if_acmpeq +263 -> 1014
    //   754: iload 17
    //   756: ifne +258 -> 1014
    //   759: aload_0
    //   760: getfield 561	gnu/expr/Compilation:generateMain	Z
    //   763: ifne +251 -> 1014
    //   766: aload_0
    //   767: getfield 1209	gnu/expr/Compilation:immediate	Z
    //   770: ifne +244 -> 1014
    //   773: aload_0
    //   774: aload_0
    //   775: getfield 945	gnu/expr/Compilation:curClass	Lgnu/bytecode/ClassType;
    //   778: ldc_w 1656
    //   781: iconst_1
    //   782: getstatic 350	gnu/bytecode/Type:typeArray0	[Lgnu/bytecode/Type;
    //   785: getstatic 433	gnu/bytecode/Type:voidType	Lgnu/bytecode/PrimType;
    //   788: invokevirtual 678	gnu/bytecode/ClassType:addMethod	(Ljava/lang/String;I[Lgnu/bytecode/Type;Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   791: putfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   794: aload_0
    //   795: getfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   798: invokevirtual 952	gnu/bytecode/Method:startCode	()Lgnu/bytecode/CodeAttr;
    //   801: astore 10
    //   803: aload 10
    //   805: getstatic 485	gnu/expr/Compilation:typeCallContext	Lgnu/bytecode/ClassType;
    //   808: invokevirtual 1012	gnu/bytecode/CodeAttr:addLocal	(Lgnu/bytecode/Type;)Lgnu/bytecode/Variable;
    //   811: astore 11
    //   813: aload 10
    //   815: getstatic 489	gnu/expr/Compilation:typeConsumer	Lgnu/bytecode/ClassType;
    //   818: invokevirtual 1012	gnu/bytecode/CodeAttr:addLocal	(Lgnu/bytecode/Type;)Lgnu/bytecode/Variable;
    //   821: astore 12
    //   823: aload 10
    //   825: getstatic 1765	gnu/bytecode/Type:javalangThrowableType	Lgnu/bytecode/ClassType;
    //   828: invokevirtual 1012	gnu/bytecode/CodeAttr:addLocal	(Lgnu/bytecode/Type;)Lgnu/bytecode/Variable;
    //   831: astore 13
    //   833: aload 10
    //   835: getstatic 493	gnu/expr/Compilation:getCallContextInstanceMethod	Lgnu/bytecode/Method;
    //   838: invokevirtual 977	gnu/bytecode/CodeAttr:emitInvokeStatic	(Lgnu/bytecode/Method;)V
    //   841: aload 10
    //   843: aload 11
    //   845: invokevirtual 1024	gnu/bytecode/CodeAttr:emitStore	(Lgnu/bytecode/Variable;)V
    //   848: getstatic 485	gnu/expr/Compilation:typeCallContext	Lgnu/bytecode/ClassType;
    //   851: ldc_w 1767
    //   854: invokevirtual 365	gnu/bytecode/ClassType:getDeclaredField	(Ljava/lang/String;)Lgnu/bytecode/Field;
    //   857: astore 14
    //   859: aload 10
    //   861: aload 11
    //   863: invokevirtual 992	gnu/bytecode/CodeAttr:emitLoad	(Lgnu/bytecode/Variable;)V
    //   866: aload 10
    //   868: aload 14
    //   870: invokevirtual 1497	gnu/bytecode/CodeAttr:emitGetField	(Lgnu/bytecode/Field;)V
    //   873: aload 10
    //   875: aload 12
    //   877: invokevirtual 1024	gnu/bytecode/CodeAttr:emitStore	(Lgnu/bytecode/Variable;)V
    //   880: aload 10
    //   882: aload 11
    //   884: invokevirtual 992	gnu/bytecode/CodeAttr:emitLoad	(Lgnu/bytecode/Variable;)V
    //   887: aload 10
    //   889: ldc_w 1769
    //   892: invokestatic 261	gnu/bytecode/ClassType:make	(Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   895: ldc_w 1771
    //   898: invokevirtual 365	gnu/bytecode/ClassType:getDeclaredField	(Ljava/lang/String;)Lgnu/bytecode/Field;
    //   901: invokevirtual 1224	gnu/bytecode/CodeAttr:emitGetStatic	(Lgnu/bytecode/Field;)V
    //   904: aload 10
    //   906: aload 14
    //   908: invokevirtual 1774	gnu/bytecode/CodeAttr:emitPutField	(Lgnu/bytecode/Field;)V
    //   911: aload 10
    //   913: iconst_0
    //   914: getstatic 433	gnu/bytecode/Type:voidType	Lgnu/bytecode/PrimType;
    //   917: invokevirtual 1778	gnu/bytecode/CodeAttr:emitTryStart	(ZLgnu/bytecode/Type;)V
    //   920: aload 10
    //   922: invokevirtual 1183	gnu/bytecode/CodeAttr:emitPushThis	()V
    //   925: aload 10
    //   927: aload 11
    //   929: invokevirtual 992	gnu/bytecode/CodeAttr:emitLoad	(Lgnu/bytecode/Variable;)V
    //   932: aload 10
    //   934: aload 8
    //   936: invokevirtual 1000	gnu/bytecode/CodeAttr:emitInvokeVirtual	(Lgnu/bytecode/Method;)V
    //   939: aload 10
    //   941: invokevirtual 1213	gnu/bytecode/CodeAttr:emitPushNull	()V
    //   944: aload 10
    //   946: aload 13
    //   948: invokevirtual 1024	gnu/bytecode/CodeAttr:emitStore	(Lgnu/bytecode/Variable;)V
    //   951: aload 10
    //   953: invokevirtual 1781	gnu/bytecode/CodeAttr:emitTryEnd	()V
    //   956: aload 10
    //   958: aload 13
    //   960: invokevirtual 1784	gnu/bytecode/CodeAttr:emitCatchStart	(Lgnu/bytecode/Variable;)V
    //   963: aload 10
    //   965: invokevirtual 1787	gnu/bytecode/CodeAttr:emitCatchEnd	()V
    //   968: aload 10
    //   970: invokevirtual 1790	gnu/bytecode/CodeAttr:emitTryCatchEnd	()V
    //   973: aload 10
    //   975: aload 11
    //   977: invokevirtual 992	gnu/bytecode/CodeAttr:emitLoad	(Lgnu/bytecode/Variable;)V
    //   980: aload 10
    //   982: aload 13
    //   984: invokevirtual 992	gnu/bytecode/CodeAttr:emitLoad	(Lgnu/bytecode/Variable;)V
    //   987: aload 10
    //   989: aload 12
    //   991: invokevirtual 992	gnu/bytecode/CodeAttr:emitLoad	(Lgnu/bytecode/Variable;)V
    //   994: aload 10
    //   996: getstatic 469	gnu/expr/Compilation:typeModuleBody	Lgnu/bytecode/ClassType;
    //   999: ldc_w 1792
    //   1002: iconst_3
    //   1003: invokevirtual 339	gnu/bytecode/ClassType:getDeclaredMethod	(Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1006: invokevirtual 977	gnu/bytecode/CodeAttr:emitInvokeStatic	(Lgnu/bytecode/Method;)V
    //   1009: aload 10
    //   1011: invokevirtual 1586	gnu/bytecode/CodeAttr:emitReturn	()V
    //   1014: aload_0
    //   1015: aload 8
    //   1017: putfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   1020: aload_0
    //   1021: aload 9
    //   1023: putfield 1682	gnu/expr/Compilation:callContextVar	Lgnu/bytecode/Variable;
    //   1026: aload 5
    //   1028: aload_0
    //   1029: invokevirtual 1795	gnu/expr/ModuleExp:generateApplyMethods	(Lgnu/expr/Compilation;)V
    //   1032: aload_0
    //   1033: aload 6
    //   1035: putfield 1645	gnu/expr/Compilation:curLambda	Lgnu/expr/LambdaExp;
    //   1038: aload 5
    //   1040: aload 7
    //   1042: putfield 1651	gnu/expr/ModuleExp:heapFrame	Lgnu/bytecode/Variable;
    //   1045: aload_0
    //   1046: invokevirtual 1673	gnu/expr/Compilation:usingCPStyle	()Z
    //   1049: ifeq +18 -> 1067
    //   1052: aload_0
    //   1053: invokevirtual 982	gnu/expr/Compilation:getCode	()Lgnu/bytecode/CodeAttr;
    //   1056: astore 5
    //   1058: aload_0
    //   1059: getfield 1708	gnu/expr/Compilation:fswitch	Lgnu/bytecode/SwitchState;
    //   1062: aload 5
    //   1064: invokevirtual 1594	gnu/bytecode/SwitchState:finish	(Lgnu/bytecode/CodeAttr;)V
    //   1067: aload_3
    //   1068: ifnonnull +10 -> 1078
    //   1071: aload_0
    //   1072: getfield 1682	gnu/expr/Compilation:callContextVar	Lgnu/bytecode/Variable;
    //   1075: ifnull +100 -> 1175
    //   1078: aload_0
    //   1079: aload 4
    //   1081: putfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   1084: aload_0
    //   1085: invokevirtual 982	gnu/expr/Compilation:getCode	()Lgnu/bytecode/CodeAttr;
    //   1088: astore 4
    //   1090: new 1033	gnu/bytecode/Label
    //   1093: dup
    //   1094: aload 4
    //   1096: invokespecial 1036	gnu/bytecode/Label:<init>	(Lgnu/bytecode/CodeAttr;)V
    //   1099: astore 5
    //   1101: aload 4
    //   1103: aload_3
    //   1104: aload 5
    //   1106: invokevirtual 1722	gnu/bytecode/CodeAttr:fixupChain	(Lgnu/bytecode/Label;Lgnu/bytecode/Label;)V
    //   1109: aload_0
    //   1110: getfield 1797	gnu/expr/Compilation:callContextVarForInit	Lgnu/bytecode/Variable;
    //   1113: ifnull +20 -> 1133
    //   1116: aload 4
    //   1118: getstatic 493	gnu/expr/Compilation:getCallContextInstanceMethod	Lgnu/bytecode/Method;
    //   1121: invokevirtual 977	gnu/bytecode/CodeAttr:emitInvokeStatic	(Lgnu/bytecode/Method;)V
    //   1124: aload 4
    //   1126: aload_0
    //   1127: getfield 1797	gnu/expr/Compilation:callContextVarForInit	Lgnu/bytecode/Variable;
    //   1130: invokevirtual 1024	gnu/bytecode/CodeAttr:emitStore	(Lgnu/bytecode/Variable;)V
    //   1133: aload_0
    //   1134: getfield 1209	gnu/expr/Compilation:immediate	Z
    //   1137: ifeq +634 -> 1771
    //   1140: aload 4
    //   1142: aload_0
    //   1143: invokestatic 1799	gnu/expr/Compilation:registerForImmediateLiterals	(Lgnu/expr/Compilation;)I
    //   1146: invokevirtual 995	gnu/bytecode/CodeAttr:emitPushInt	(I)V
    //   1149: aload 4
    //   1151: ldc_w 1801
    //   1154: invokestatic 261	gnu/bytecode/ClassType:make	(Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   1157: ldc_w 1802
    //   1160: iconst_1
    //   1161: invokevirtual 339	gnu/bytecode/ClassType:getDeclaredMethod	(Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1164: invokevirtual 977	gnu/bytecode/CodeAttr:emitInvokeStatic	(Lgnu/bytecode/Method;)V
    //   1167: aload 4
    //   1169: aload 5
    //   1171: aload_2
    //   1172: invokevirtual 1722	gnu/bytecode/CodeAttr:fixupChain	(Lgnu/bytecode/Label;Lgnu/bytecode/Label;)V
    //   1175: aload_0
    //   1176: getfield 561	gnu/expr/Compilation:generateMain	Z
    //   1179: ifeq +155 -> 1334
    //   1182: aload_0
    //   1183: getfield 945	gnu/expr/Compilation:curClass	Lgnu/bytecode/ClassType;
    //   1186: aload_0
    //   1187: getfield 873	gnu/expr/Compilation:mainClass	Lgnu/bytecode/ClassType;
    //   1190: if_acmpne +144 -> 1334
    //   1193: new 287	gnu/bytecode/ArrayType
    //   1196: dup
    //   1197: getstatic 269	gnu/expr/Compilation:javaStringType	Lgnu/bytecode/ClassType;
    //   1200: invokespecial 1755	gnu/bytecode/ArrayType:<init>	(Lgnu/bytecode/Type;)V
    //   1203: astore_2
    //   1204: aload_0
    //   1205: getfield 945	gnu/expr/Compilation:curClass	Lgnu/bytecode/ClassType;
    //   1208: astore_3
    //   1209: getstatic 433	gnu/bytecode/Type:voidType	Lgnu/bytecode/PrimType;
    //   1212: astore 4
    //   1214: aload_0
    //   1215: aload_3
    //   1216: ldc_w 1804
    //   1219: bipush 9
    //   1221: iconst_1
    //   1222: anewarray 248	gnu/bytecode/Type
    //   1225: dup
    //   1226: iconst_0
    //   1227: aload_2
    //   1228: aastore
    //   1229: aload 4
    //   1231: invokevirtual 678	gnu/bytecode/ClassType:addMethod	(Ljava/lang/String;I[Lgnu/bytecode/Type;Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   1234: putfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   1237: aload_0
    //   1238: getfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   1241: invokevirtual 952	gnu/bytecode/Method:startCode	()Lgnu/bytecode/CodeAttr;
    //   1244: astore_2
    //   1245: getstatic 1809	kawa/Shell:defaultFormatName	Ljava/lang/String;
    //   1248: ifnull +27 -> 1275
    //   1251: aload_2
    //   1252: getstatic 1809	kawa/Shell:defaultFormatName	Ljava/lang/String;
    //   1255: invokevirtual 1216	gnu/bytecode/CodeAttr:emitPushString	(Ljava/lang/String;)V
    //   1258: aload_2
    //   1259: ldc_w 1811
    //   1262: invokestatic 261	gnu/bytecode/ClassType:make	(Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   1265: ldc_w 1813
    //   1268: iconst_1
    //   1269: invokevirtual 339	gnu/bytecode/ClassType:getDeclaredMethod	(Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1272: invokevirtual 977	gnu/bytecode/CodeAttr:emitInvokeStatic	(Lgnu/bytecode/Method;)V
    //   1275: aload_2
    //   1276: aload_2
    //   1277: iconst_0
    //   1278: invokevirtual 1004	gnu/bytecode/CodeAttr:getArg	(I)Lgnu/bytecode/Variable;
    //   1281: invokevirtual 992	gnu/bytecode/CodeAttr:emitLoad	(Lgnu/bytecode/Variable;)V
    //   1284: aload_2
    //   1285: ldc_w 1815
    //   1288: invokestatic 261	gnu/bytecode/ClassType:make	(Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   1291: ldc_w 1817
    //   1294: iconst_1
    //   1295: invokevirtual 339	gnu/bytecode/ClassType:getDeclaredMethod	(Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1298: invokevirtual 977	gnu/bytecode/CodeAttr:emitInvokeStatic	(Lgnu/bytecode/Method;)V
    //   1301: aload_0
    //   1302: getfield 1538	gnu/expr/Compilation:moduleInstanceMainField	Lgnu/bytecode/Field;
    //   1305: ifnull +506 -> 1811
    //   1308: aload_2
    //   1309: aload_0
    //   1310: getfield 1538	gnu/expr/Compilation:moduleInstanceMainField	Lgnu/bytecode/Field;
    //   1313: invokevirtual 1224	gnu/bytecode/CodeAttr:emitGetStatic	(Lgnu/bytecode/Field;)V
    //   1316: aload_2
    //   1317: getstatic 469	gnu/expr/Compilation:typeModuleBody	Lgnu/bytecode/ClassType;
    //   1320: ldc_w 1819
    //   1323: iconst_0
    //   1324: invokevirtual 339	gnu/bytecode/ClassType:getDeclaredMethod	(Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1327: invokevirtual 1000	gnu/bytecode/CodeAttr:emitInvokeVirtual	(Lgnu/bytecode/Method;)V
    //   1330: aload_2
    //   1331: invokevirtual 1586	gnu/bytecode/CodeAttr:emitReturn	()V
    //   1334: aload_0
    //   1335: getfield 1195	gnu/expr/Compilation:minfo	Lgnu/expr/ModuleInfo;
    //   1338: ifnull +613 -> 1951
    //   1341: aload_0
    //   1342: getfield 1195	gnu/expr/Compilation:minfo	Lgnu/expr/ModuleInfo;
    //   1345: invokevirtual 1822	gnu/expr/ModuleInfo:getNamespaceUri	()Ljava/lang/String;
    //   1348: ifnull +603 -> 1951
    //   1351: invokestatic 1827	gnu/expr/ModuleManager:getInstance	()Lgnu/expr/ModuleManager;
    //   1354: astore 6
    //   1356: aload_0
    //   1357: getfield 873	gnu/expr/Compilation:mainClass	Lgnu/bytecode/ClassType;
    //   1360: invokevirtual 898	gnu/bytecode/ClassType:getName	()Ljava/lang/String;
    //   1363: astore_2
    //   1364: aload_2
    //   1365: bipush 46
    //   1367: invokevirtual 1829	java/lang/String:lastIndexOf	(I)I
    //   1370: istore 15
    //   1372: iload 15
    //   1374: ifge +467 -> 1841
    //   1377: ldc_w 535
    //   1380: astore_2
    //   1381: new 257	gnu/bytecode/ClassType
    //   1384: dup
    //   1385: new 1140	java/lang/StringBuilder
    //   1388: dup
    //   1389: invokespecial 1141	java/lang/StringBuilder:<init>	()V
    //   1392: aload_2
    //   1393: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1396: ldc_w 1831
    //   1399: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1402: invokevirtual 1152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1405: invokespecial 1748	gnu/bytecode/ClassType:<init>	(Ljava/lang/String;)V
    //   1408: astore_3
    //   1409: ldc_w 1833
    //   1412: invokestatic 261	gnu/bytecode/ClassType:make	(Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   1415: astore 4
    //   1417: aload_3
    //   1418: aload 4
    //   1420: invokevirtual 1126	gnu/bytecode/ClassType:setSuper	(Lgnu/bytecode/ClassType;)V
    //   1423: aload_0
    //   1424: aload_3
    //   1425: invokespecial 1096	gnu/expr/Compilation:registerClass	(Lgnu/bytecode/ClassType;)V
    //   1428: aload_0
    //   1429: aload_3
    //   1430: ldc_w 664
    //   1433: iconst_1
    //   1434: getstatic 400	gnu/expr/Compilation:apply0args	[Lgnu/bytecode/Type;
    //   1437: getstatic 433	gnu/bytecode/Type:voidType	Lgnu/bytecode/PrimType;
    //   1440: invokevirtual 678	gnu/bytecode/ClassType:addMethod	(Ljava/lang/String;I[Lgnu/bytecode/Type;Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   1443: putfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   1446: aload 4
    //   1448: ldc_w 664
    //   1451: iconst_1
    //   1452: getstatic 400	gnu/expr/Compilation:apply0args	[Lgnu/bytecode/Type;
    //   1455: getstatic 433	gnu/bytecode/Type:voidType	Lgnu/bytecode/PrimType;
    //   1458: invokevirtual 678	gnu/bytecode/ClassType:addMethod	(Ljava/lang/String;I[Lgnu/bytecode/Type;Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   1461: astore 4
    //   1463: aload_0
    //   1464: getfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   1467: invokevirtual 952	gnu/bytecode/Method:startCode	()Lgnu/bytecode/CodeAttr;
    //   1470: astore 5
    //   1472: aload 5
    //   1474: invokevirtual 1183	gnu/bytecode/CodeAttr:emitPushThis	()V
    //   1477: aload 5
    //   1479: aload 4
    //   1481: invokevirtual 1609	gnu/bytecode/CodeAttr:emitInvokeSpecial	(Lgnu/bytecode/Method;)V
    //   1484: aload 5
    //   1486: invokevirtual 1586	gnu/bytecode/CodeAttr:emitReturn	()V
    //   1489: ldc_w 1835
    //   1492: invokestatic 261	gnu/bytecode/ClassType:make	(Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   1495: astore 4
    //   1497: getstatic 433	gnu/bytecode/Type:voidType	Lgnu/bytecode/PrimType;
    //   1500: astore 5
    //   1502: aload_0
    //   1503: aload_3
    //   1504: ldc_w 1837
    //   1507: iconst_1
    //   1508: anewarray 248	gnu/bytecode/Type
    //   1511: dup
    //   1512: iconst_0
    //   1513: aload 4
    //   1515: aastore
    //   1516: aload 5
    //   1518: iconst_1
    //   1519: invokevirtual 354	gnu/bytecode/ClassType:addMethod	(Ljava/lang/String;[Lgnu/bytecode/Type;Lgnu/bytecode/Type;I)Lgnu/bytecode/Method;
    //   1522: putfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   1525: aload_0
    //   1526: getfield 948	gnu/expr/Compilation:method	Lgnu/bytecode/Method;
    //   1529: invokevirtual 952	gnu/bytecode/Method:startCode	()Lgnu/bytecode/CodeAttr;
    //   1532: astore 7
    //   1534: aload 4
    //   1536: ldc_w 1837
    //   1539: iconst_3
    //   1540: invokevirtual 339	gnu/bytecode/ClassType:getDeclaredMethod	(Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   1543: astore 8
    //   1545: aload 6
    //   1547: getfield 1840	gnu/expr/ModuleManager:numModules	I
    //   1550: istore 15
    //   1552: iload 15
    //   1554: iconst_1
    //   1555: isub
    //   1556: istore 16
    //   1558: iload 16
    //   1560: iflt +386 -> 1946
    //   1563: aload 6
    //   1565: getfield 1844	gnu/expr/ModuleManager:modules	[Lgnu/expr/ModuleInfo;
    //   1568: iload 16
    //   1570: aaload
    //   1571: astore 10
    //   1573: aload 10
    //   1575: invokevirtual 1847	gnu/expr/ModuleInfo:getClassName	()Ljava/lang/String;
    //   1578: astore_3
    //   1579: iload 16
    //   1581: istore 15
    //   1583: aload_3
    //   1584: ifnull -32 -> 1552
    //   1587: iload 16
    //   1589: istore 15
    //   1591: aload_3
    //   1592: aload_2
    //   1593: invokevirtual 829	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   1596: ifeq -44 -> 1552
    //   1599: aload 10
    //   1601: getfield 1850	gnu/expr/ModuleInfo:sourcePath	Ljava/lang/String;
    //   1604: astore 4
    //   1606: aload 10
    //   1608: invokevirtual 1822	gnu/expr/ModuleInfo:getNamespaceUri	()Ljava/lang/String;
    //   1611: astore 9
    //   1613: aload 7
    //   1615: aload 7
    //   1617: iconst_1
    //   1618: invokevirtual 1004	gnu/bytecode/CodeAttr:getArg	(I)Lgnu/bytecode/Variable;
    //   1621: invokevirtual 992	gnu/bytecode/CodeAttr:emitLoad	(Lgnu/bytecode/Variable;)V
    //   1624: aload_0
    //   1625: aload_3
    //   1626: invokevirtual 1315	gnu/expr/Compilation:compileConstant	(Ljava/lang/Object;)V
    //   1629: aload 4
    //   1631: astore_3
    //   1632: aload 4
    //   1634: invokestatic 1856	gnu/text/Path:valueOf	(Ljava/lang/Object;)Lgnu/text/Path;
    //   1637: invokevirtual 1859	gnu/text/Path:isAbsolute	()Z
    //   1640: ifne +106 -> 1746
    //   1643: getstatic 1863	java/io/File:separatorChar	C
    //   1646: istore_1
    //   1647: aload 6
    //   1649: invokevirtual 1866	gnu/expr/ModuleManager:getCompilationDirectory	()Ljava/lang/String;
    //   1652: astore_3
    //   1653: new 1140	java/lang/StringBuilder
    //   1656: dup
    //   1657: invokespecial 1141	java/lang/StringBuilder:<init>	()V
    //   1660: aload_3
    //   1661: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1664: aload_2
    //   1665: bipush 46
    //   1667: iload_1
    //   1668: invokevirtual 1390	java/lang/String:replace	(CC)Ljava/lang/String;
    //   1671: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1674: invokevirtual 1152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1677: invokestatic 1870	gnu/text/Path:toURL	(Ljava/lang/String;)Ljava/net/URL;
    //   1680: invokevirtual 1873	java/net/URL:toString	()Ljava/lang/String;
    //   1683: astore 5
    //   1685: aload 5
    //   1687: invokevirtual 604	java/lang/String:length	()I
    //   1690: istore 15
    //   1692: aload 5
    //   1694: astore_3
    //   1695: iload 15
    //   1697: ifle +39 -> 1736
    //   1700: aload 5
    //   1702: astore_3
    //   1703: aload 5
    //   1705: iload 15
    //   1707: iconst_1
    //   1708: isub
    //   1709: invokevirtual 608	java/lang/String:charAt	(I)C
    //   1712: iload_1
    //   1713: if_icmpeq +23 -> 1736
    //   1716: new 1140	java/lang/StringBuilder
    //   1719: dup
    //   1720: invokespecial 1141	java/lang/StringBuilder:<init>	()V
    //   1723: aload 5
    //   1725: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1728: iload_1
    //   1729: invokevirtual 1876	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   1732: invokevirtual 1152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1735: astore_3
    //   1736: aload 10
    //   1738: invokevirtual 1879	gnu/expr/ModuleInfo:getSourceAbsPathname	()Ljava/lang/String;
    //   1741: aload_3
    //   1742: invokestatic 1883	gnu/text/Path:relativize	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   1745: astore_3
    //   1746: aload_0
    //   1747: aload_3
    //   1748: invokevirtual 1315	gnu/expr/Compilation:compileConstant	(Ljava/lang/Object;)V
    //   1751: aload_0
    //   1752: aload 9
    //   1754: invokevirtual 1315	gnu/expr/Compilation:compileConstant	(Ljava/lang/Object;)V
    //   1757: aload 7
    //   1759: aload 8
    //   1761: invokevirtual 1000	gnu/bytecode/CodeAttr:emitInvokeVirtual	(Lgnu/bytecode/Method;)V
    //   1764: iload 16
    //   1766: istore 15
    //   1768: goto -216 -> 1552
    //   1771: aload_0
    //   1772: getfield 906	gnu/expr/Compilation:litTable	Lgnu/expr/LitTable;
    //   1775: invokevirtual 1885	gnu/expr/LitTable:emit	()V
    //   1778: goto -611 -> 1167
    //   1781: astore_3
    //   1782: aload_0
    //   1783: bipush 101
    //   1785: new 1140	java/lang/StringBuilder
    //   1788: dup
    //   1789: invokespecial 1141	java/lang/StringBuilder:<init>	()V
    //   1792: ldc_w 1887
    //   1795: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1798: aload_3
    //   1799: invokevirtual 1573	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1802: invokevirtual 1152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1805: invokevirtual 1332	gnu/expr/Compilation:error	(CLjava/lang/String;)V
    //   1808: goto -641 -> 1167
    //   1811: aload_2
    //   1812: aload_0
    //   1813: getfield 945	gnu/expr/Compilation:curClass	Lgnu/bytecode/ClassType;
    //   1816: invokevirtual 1728	gnu/bytecode/CodeAttr:emitNew	(Lgnu/bytecode/ClassType;)V
    //   1819: aload_2
    //   1820: aload_0
    //   1821: getfield 945	gnu/expr/Compilation:curClass	Lgnu/bytecode/ClassType;
    //   1824: invokevirtual 1730	gnu/bytecode/CodeAttr:emitDup	(Lgnu/bytecode/Type;)V
    //   1827: aload_2
    //   1828: aload_0
    //   1829: getfield 945	gnu/expr/Compilation:curClass	Lgnu/bytecode/ClassType;
    //   1832: getfield 1733	gnu/bytecode/ClassType:constructor	Lgnu/bytecode/Method;
    //   1835: invokevirtual 1609	gnu/bytecode/CodeAttr:emitInvokeSpecial	(Lgnu/bytecode/Method;)V
    //   1838: goto -522 -> 1316
    //   1841: aload_2
    //   1842: iconst_0
    //   1843: iload 15
    //   1845: invokevirtual 836	java/lang/String:substring	(II)Ljava/lang/String;
    //   1848: astore_3
    //   1849: aload 6
    //   1851: aload_3
    //   1852: invokevirtual 1890	gnu/expr/ModuleManager:loadPackageInfo	(Ljava/lang/String;)V
    //   1855: aload_2
    //   1856: iconst_0
    //   1857: iload 15
    //   1859: iconst_1
    //   1860: iadd
    //   1861: invokevirtual 836	java/lang/String:substring	(II)Ljava/lang/String;
    //   1864: astore_2
    //   1865: goto -484 -> 1381
    //   1868: astore 4
    //   1870: aload_0
    //   1871: bipush 101
    //   1873: new 1140	java/lang/StringBuilder
    //   1876: dup
    //   1877: invokespecial 1141	java/lang/StringBuilder:<init>	()V
    //   1880: ldc_w 1892
    //   1883: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1886: aload_3
    //   1887: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1890: ldc_w 1894
    //   1893: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1896: aload 4
    //   1898: invokevirtual 1573	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1901: invokevirtual 1152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1904: invokevirtual 1332	gnu/expr/Compilation:error	(CLjava/lang/String;)V
    //   1907: goto -52 -> 1855
    //   1910: astore_2
    //   1911: new 936	gnu/mapping/WrappedException
    //   1914: dup
    //   1915: new 1140	java/lang/StringBuilder
    //   1918: dup
    //   1919: invokespecial 1141	java/lang/StringBuilder:<init>	()V
    //   1922: ldc_w 1896
    //   1925: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1928: aload 4
    //   1930: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1933: bipush 39
    //   1935: invokevirtual 1876	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   1938: invokevirtual 1152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1941: aload_2
    //   1942: invokespecial 941	gnu/mapping/WrappedException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1945: athrow
    //   1946: aload 7
    //   1948: invokevirtual 1586	gnu/bytecode/CodeAttr:emitReturn	()V
    //   1951: return
    //   1952: astore_3
    //   1953: goto -98 -> 1855
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1956	0	this	Compilation
    //   1646	83	1	c	char
    //   15	1850	2	localObject1	Object
    //   1910	32	2	localThrowable1	Throwable
    //   194	1554	3	localObject2	Object
    //   1781	18	3	localThrowable2	Throwable
    //   1848	39	3	str	String
    //   1952	1	3	localClassNotFoundException	ClassNotFoundException
    //   388	1245	4	localObject3	Object
    //   1868	61	4	localThrowable3	Throwable
    //   4	1720	5	localObject4	Object
    //   122	1728	6	localObject5	Object
    //   154	1793	7	localObject6	Object
    //   405	1355	8	localMethod	Method
    //   411	1342	9	localObject7	Object
    //   434	1303	10	localObject8	Object
    //   537	439	11	localObject9	Object
    //   821	169	12	localVariable1	Variable
    //   831	152	13	localVariable2	Variable
    //   857	50	14	localField	gnu.bytecode.Field
    //   305	1556	15	i	int
    //   1556	209	16	j	int
    //   161	594	17	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   1133	1167	1781	java/lang/Throwable
    //   1771	1778	1781	java/lang/Throwable
    //   1849	1855	1868	java/lang/Throwable
    //   1643	1692	1910	java/lang/Throwable
    //   1703	1736	1910	java/lang/Throwable
    //   1736	1746	1910	java/lang/Throwable
    //   1849	1855	1952	java/lang/ClassNotFoundException
  }
  
  public String generateClassName(String paramString)
  {
    String str = mangleName(paramString, true);
    if (this.mainClass != null) {
      paramString = this.mainClass.getName() + '$' + str;
    }
    while (findNamedClass(paramString) == null)
    {
      return paramString;
      paramString = str;
      if (this.classPrefix != null) {
        paramString = this.classPrefix + str;
      }
    }
    int i = 0;
    for (;;)
    {
      str = paramString + i;
      if (findNamedClass(str) == null) {
        return str;
      }
      i += 1;
    }
  }
  
  public final void generateConstructor(ClassType paramClassType, LambdaExp paramLambdaExp)
  {
    Method localMethod = this.method;
    Variable localVariable = this.callContextVar;
    this.callContextVar = null;
    ClassType localClassType = this.curClass;
    this.curClass = paramClassType;
    Object localObject = getConstructor(paramClassType, paramLambdaExp);
    paramClassType.constructor = ((Method)localObject);
    this.method = ((Method)localObject);
    localObject = ((Method)localObject).startCode();
    if (((paramLambdaExp instanceof ClassExp)) && (paramLambdaExp.staticLinkField != null))
    {
      ((CodeAttr)localObject).emitPushThis();
      ((CodeAttr)localObject).emitLoad(((CodeAttr)localObject).getCurrentScope().getVariable(1));
      ((CodeAttr)localObject).emitPutField(paramLambdaExp.staticLinkField);
    }
    ClassExp.invokeDefaultSuperConstructor(paramClassType.getSuperclass(), this, paramLambdaExp);
    if ((this.curClass == this.mainClass) && (this.minfo != null) && (this.minfo.sourcePath != null))
    {
      ((CodeAttr)localObject).emitPushThis();
      ((CodeAttr)localObject).emitInvokeStatic(ClassType.make("gnu.expr.ModuleInfo").getDeclaredMethod("register", 1));
    }
    if ((paramLambdaExp != null) && (paramLambdaExp.initChain != null))
    {
      paramClassType = this.curLambda;
      this.curLambda = new LambdaExp();
      this.curLambda.closureEnv = ((CodeAttr)localObject).getArg(0);
      this.curLambda.outer = paramClassType;
      for (;;)
      {
        Initializer localInitializer = paramLambdaExp.initChain;
        if (localInitializer == null) {
          break;
        }
        paramLambdaExp.initChain = null;
        dumpInitializers(localInitializer);
      }
      this.curLambda = paramClassType;
    }
    if ((paramLambdaExp instanceof ClassExp)) {
      callInitMethods(((ClassExp)paramLambdaExp).getCompiledClassType(this), new Vector(10));
    }
    ((CodeAttr)localObject).emitReturn();
    this.method = localMethod;
    this.curClass = localClassType;
    this.callContextVar = localVariable;
  }
  
  public final void generateConstructor(LambdaExp paramLambdaExp)
  {
    generateConstructor(paramLambdaExp.getHeapFrameType(), paramLambdaExp);
  }
  
  public void generateMatchMethods(LambdaExp paramLambdaExp)
  {
    if (paramLambdaExp.applyMethods == null) {}
    for (int i = 0; i == 0; i = paramLambdaExp.applyMethods.size()) {
      return;
    }
    Method localMethod = this.method;
    ClassType localClassType1 = this.curClass;
    ClassType localClassType2 = typeModuleMethod;
    this.curClass = paramLambdaExp.getHeapFrameType();
    if (!this.curClass.getSuperclass().isSubtype(typeModuleBody)) {
      this.curClass = this.moduleClass;
    }
    CodeAttr localCodeAttr = null;
    int j = 0;
    while (j <= 5)
    {
      int m = 0;
      SwitchState localSwitchState = null;
      String str = null;
      Type[] arrayOfType = null;
      int k = i;
      int i1;
      LambdaExp localLambdaExp;
      int i3;
      label163:
      int i2;
      do
      {
        do
        {
          do
          {
            i1 = k - 1;
            if (i1 < 0) {
              break label916;
            }
            localLambdaExp = (LambdaExp)paramLambdaExp.applyMethods.elementAt(i1);
            i3 = localLambdaExp.primMethods.length;
            if ((localLambdaExp.max_args >= 0) && (localLambdaExp.max_args < localLambdaExp.min_args + i3)) {
              break;
            }
            n = 1;
            if (j >= 5) {
              break label299;
            }
            i2 = j - localLambdaExp.min_args;
            k = i1;
          } while (i2 < 0);
          k = i1;
        } while (i2 >= i3);
        if (i2 != i3 - 1) {
          break;
        }
        k = i1;
      } while (n != 0);
      for (k = i2;; k = i3 - 1)
      {
        n = m;
        if (m != 0) {
          break label412;
        }
        if (j >= 5) {
          break label671;
        }
        str = "match" + j;
        arrayOfType = new Type[j + 2];
        m = j;
        while (m >= 0)
        {
          arrayOfType[(m + 1)] = typeObject;
          m -= 1;
        }
        n = 0;
        break label163;
        label299:
        k = 5 - localLambdaExp.min_args;
        if ((k > 0) && (i3 <= k))
        {
          k = i1;
          if (n == 0) {
            break;
          }
        }
      }
      arrayOfType[(j + 1)] = typeCallContext;
      arrayOfType[0] = localClassType2;
      this.method = this.curClass.addMethod(str, arrayOfType, Type.intType, 1);
      localCodeAttr = this.method.startCode();
      localCodeAttr.emitLoad(localCodeAttr.getArg(1));
      localCodeAttr.emitGetField(localClassType2.getField("selector"));
      localSwitchState = localCodeAttr.startSwitch();
      int n = 1;
      label412:
      localSwitchState.addCase(localLambdaExp.getSelectorValue(this), localCodeAttr);
      m = localLambdaExp.getLineNumber();
      if (m > 0) {
        localCodeAttr.putLineNumber(localLambdaExp.getFileName(), m);
      }
      label459:
      Variable localVariable;
      Declaration localDeclaration;
      label484:
      Type localType;
      if (j == 5)
      {
        m = 3;
        localVariable = localCodeAttr.getArg(m);
        if (j >= 5) {
          break label769;
        }
        localDeclaration = localLambdaExp.firstDecl();
        m = 1;
        if (m > j) {
          break label801;
        }
        localCodeAttr.emitLoad(localVariable);
        localCodeAttr.emitLoad(localCodeAttr.getArg(m + 1));
        localType = localDeclaration.getType();
        if (localType != Type.objectType)
        {
          if (!(localType instanceof TypeValue)) {
            break label704;
          }
          Label localLabel1 = new Label(localCodeAttr);
          Label localLabel2 = new Label(localCodeAttr);
          ConditionalTarget localConditionalTarget = new ConditionalTarget(localLabel1, localLabel2, getLanguage());
          localCodeAttr.emitDup();
          ((TypeValue)localType).emitIsInstance(null, this, localConditionalTarget);
          localLabel2.define(localCodeAttr);
          localCodeAttr.emitPushInt(0xFFF40000 | m);
          localCodeAttr.emitReturn();
          localLabel1.define(localCodeAttr);
        }
      }
      for (;;)
      {
        localCodeAttr.emitPutField(typeCallContext.getField("value" + m));
        localDeclaration = localDeclaration.nextDecl();
        m += 1;
        break label484;
        label671:
        str = "matchN";
        arrayOfType = new Type[3];
        arrayOfType[1] = objArrayType;
        arrayOfType[2] = typeCallContext;
        break;
        m = j + 2;
        break label459;
        label704:
        if (((localType instanceof ClassType)) && (localType != Type.objectType) && (localType != Type.toStringType))
        {
          localCodeAttr.emitDup();
          localType.emitIsInstance(localCodeAttr);
          localCodeAttr.emitIfIntEqZero();
          localCodeAttr.emitPushInt(0xFFF40000 | m);
          localCodeAttr.emitReturn();
          localCodeAttr.emitFi();
        }
      }
      label769:
      localCodeAttr.emitLoad(localVariable);
      localCodeAttr.emitLoad(localCodeAttr.getArg(2));
      localCodeAttr.emitPutField(typeCallContext.getField("values"));
      label801:
      localCodeAttr.emitLoad(localVariable);
      if (defaultCallConvention < 2)
      {
        localCodeAttr.emitLoad(localCodeAttr.getArg(1));
        label826:
        localCodeAttr.emitPutField(procCallContextField);
        localCodeAttr.emitLoad(localVariable);
        if (defaultCallConvention < 2) {
          break label906;
        }
        localCodeAttr.emitPushInt(localLambdaExp.getSelectorValue(this) + k);
      }
      for (;;)
      {
        localCodeAttr.emitPutField(pcCallContextField);
        localCodeAttr.emitPushInt(0);
        localCodeAttr.emitReturn();
        k = i1;
        m = n;
        break;
        localCodeAttr.emitLoad(localCodeAttr.getArg(0));
        break label826;
        label906:
        localCodeAttr.emitPushInt(j);
      }
      label916:
      if (m != 0)
      {
        localSwitchState.addDefault(localCodeAttr);
        if (j > 4) {}
        for (k = 2;; k = j + 1)
        {
          m = 0;
          while (m <= k + 1)
          {
            localCodeAttr.emitLoad(localCodeAttr.getArg(m));
            m += 1;
          }
        }
        localCodeAttr.emitInvokeSpecial(typeModuleBody.getDeclaredMethod(str, arrayOfType.length));
        localCodeAttr.emitReturn();
        localSwitchState.finish(localCodeAttr);
      }
      j += 1;
    }
    this.method = localMethod;
    this.curClass = localClassType1;
  }
  
  public boolean generatingApplet()
  {
    return (this.langOptions & 0x10) != 0;
  }
  
  public boolean generatingServlet()
  {
    return (this.langOptions & 0x20) != 0;
  }
  
  public final boolean getBooleanOption(String paramString)
  {
    return this.currentOptions.getBoolean(paramString);
  }
  
  public final boolean getBooleanOption(String paramString, boolean paramBoolean)
  {
    return this.currentOptions.getBoolean(paramString, paramBoolean);
  }
  
  public final CodeAttr getCode()
  {
    return this.method.getCode();
  }
  
  public final int getColumnNumber()
  {
    return this.messages.getColumnNumber();
  }
  
  public final Method getConstructor(LambdaExp paramLambdaExp)
  {
    return getConstructor(paramLambdaExp.getHeapFrameType(), paramLambdaExp);
  }
  
  public final String getFileName()
  {
    return this.messages.getFileName();
  }
  
  public Method getForNameHelper()
  {
    if (this.forNameHelper == null)
    {
      Method localMethod = this.method;
      this.method = this.curClass.addMethod("class$", 9, string1Arg, typeClass);
      this.forNameHelper = this.method;
      CodeAttr localCodeAttr = this.method.startCode();
      localCodeAttr.emitLoad(localCodeAttr.getArg(0));
      localCodeAttr.emitPushInt(0);
      localCodeAttr.emitPushString(this.mainClass.getName());
      localCodeAttr.emitInvokeStatic(typeClass.getDeclaredMethod("forName", 1));
      localCodeAttr.emitInvokeVirtual(typeClass.getDeclaredMethod("getClassLoader", 0));
      localCodeAttr.emitInvokeStatic(typeClass.getDeclaredMethod("forName", 3));
      localCodeAttr.emitReturn();
      this.method = localMethod;
    }
    return this.forNameHelper;
  }
  
  public Language getLanguage()
  {
    return this.language;
  }
  
  public final int getLineNumber()
  {
    return this.messages.getLineNumber();
  }
  
  public SourceMessages getMessages()
  {
    return this.messages;
  }
  
  public final ModuleExp getModule()
  {
    return this.mainLambda;
  }
  
  public final ClassType getModuleType()
  {
    if (defaultCallConvention >= 2) {
      return typeModuleWithContext;
    }
    return typeModuleBody;
  }
  
  public String getPublicId()
  {
    return this.messages.getPublicId();
  }
  
  public int getState()
  {
    return this.state;
  }
  
  public String getSystemId()
  {
    return this.messages.getSystemId();
  }
  
  public boolean inlineOk(Expression paramExpression)
  {
    if ((paramExpression instanceof LambdaExp))
    {
      paramExpression = (LambdaExp)paramExpression;
      Declaration localDeclaration = paramExpression.nameDecl;
      if ((localDeclaration == null) || (localDeclaration.getSymbol() == null) || (!(localDeclaration.context instanceof ModuleExp))) {
        return true;
      }
      if ((this.immediate) && (localDeclaration.isPublic()) && (!paramExpression.getFlag(2048)) && ((this.curLambda == null) || (paramExpression.topLevel() != this.curLambda.topLevel()))) {
        return false;
      }
    }
    return inlineOk;
  }
  
  public boolean inlineOk(Procedure paramProcedure)
  {
    if ((this.immediate) && ((paramProcedure instanceof ModuleMethod)) && ((((ModuleMethod)paramProcedure).module.getClass().getClassLoader() instanceof ArrayClassLoader))) {
      return false;
    }
    return inlineOk;
  }
  
  public boolean isPedantic()
  {
    return this.pedantic;
  }
  
  public boolean isStableSourceLocation()
  {
    return false;
  }
  
  public boolean isStatic()
  {
    return this.mainLambda.isStatic();
  }
  
  public LetExp letDone(Expression paramExpression)
  {
    LetExp localLetExp = (LetExp)this.current_scope;
    localLetExp.body = paramExpression;
    pop(localLetExp);
    return localLetExp;
  }
  
  public void letEnter()
  {
    LetExp localLetExp = (LetExp)this.current_scope;
    Expression[] arrayOfExpression = new Expression[localLetExp.countDecls()];
    Declaration localDeclaration = localLetExp.firstDecl();
    int i = 0;
    while (localDeclaration != null)
    {
      arrayOfExpression[i] = localDeclaration.getValue();
      localDeclaration = localDeclaration.nextDecl();
      i += 1;
    }
    localLetExp.inits = arrayOfExpression;
    this.lexical.push(localLetExp);
  }
  
  public void letStart()
  {
    pushScope(new LetExp(null));
  }
  
  public Declaration letVariable(Object paramObject, Type paramType, Expression paramExpression)
  {
    paramObject = ((LetExp)this.current_scope).addDeclaration(paramObject, paramType);
    ((Declaration)paramObject).noteValue(paramExpression);
    return (Declaration)paramObject;
  }
  
  public final void loadCallContext()
  {
    CodeAttr localCodeAttr = getCode();
    if ((this.callContextVar != null) && (!this.callContextVar.dead()))
    {
      localCodeAttr.emitLoad(this.callContextVar);
      return;
    }
    if (this.method == this.clinitMethod)
    {
      this.callContextVar = new Variable("$ctx", typeCallContext);
      this.callContextVar.reserveLocal(localCodeAttr.getMaxLocals(), localCodeAttr);
      localCodeAttr.emitLoad(this.callContextVar);
      this.callContextVarForInit = this.callContextVar;
      return;
    }
    localCodeAttr.emitInvokeStatic(getCallContextInstanceMethod);
    localCodeAttr.emitDup();
    this.callContextVar = new Variable("$ctx", typeCallContext);
    localCodeAttr.getCurrentScope().addVariable(localCodeAttr, this.callContextVar);
    localCodeAttr.emitStore(this.callContextVar);
  }
  
  public void loadClassRef(ObjectType paramObjectType)
  {
    CodeAttr localCodeAttr = getCode();
    if (this.curClass.getClassfileVersion() >= 3211264)
    {
      localCodeAttr.emitPushClass(paramObjectType);
      return;
    }
    if ((paramObjectType == this.mainClass) && (this.mainLambda.isStatic()) && (this.moduleInstanceMainField != null))
    {
      localCodeAttr.emitGetStatic(this.moduleInstanceMainField);
      localCodeAttr.emitInvokeVirtual(Type.objectType.getDeclaredMethod("getClass", 0));
      return;
    }
    if ((paramObjectType instanceof ClassType)) {}
    for (paramObjectType = paramObjectType.getName();; paramObjectType = paramObjectType.getInternalName().replace('/', '.'))
    {
      localCodeAttr.emitPushString(paramObjectType);
      localCodeAttr.emitInvokeStatic(getForNameHelper());
      return;
    }
  }
  
  public Declaration lookup(Object paramObject, int paramInt)
  {
    return this.lexical.lookup(paramObject, paramInt);
  }
  
  public void loopBody(Expression paramExpression)
  {
    ((LambdaExp)this.current_scope).body = paramExpression;
  }
  
  public void loopCond(Expression paramExpression)
  {
    checkLoop();
    this.exprStack.push(paramExpression);
  }
  
  public void loopEnter()
  {
    checkLoop();
    LambdaExp localLambdaExp = (LambdaExp)this.current_scope;
    int i = localLambdaExp.min_args;
    localLambdaExp.max_args = i;
    Expression[] arrayOfExpression = new Expression[i];
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      arrayOfExpression[i] = ((Expression)this.exprStack.pop());
    }
    LetExp localLetExp = (LetExp)localLambdaExp.outer;
    localLetExp.setBody(new ApplyExp(new ReferenceExp(localLetExp.firstDecl()), arrayOfExpression));
    this.lexical.push(localLambdaExp);
  }
  
  public Expression loopRepeat()
  {
    return loopRepeat(Expression.noExpressions);
  }
  
  public Expression loopRepeat(Expression paramExpression)
  {
    return loopRepeat(new Expression[] { paramExpression });
  }
  
  public Expression loopRepeat(Expression[] paramArrayOfExpression)
  {
    LambdaExp localLambdaExp = (LambdaExp)this.current_scope;
    ScopeExp localScopeExp = localLambdaExp.outer;
    Declaration localDeclaration = localScopeExp.firstDecl();
    Expression localExpression = (Expression)this.exprStack.pop();
    paramArrayOfExpression = new ApplyExp(new ReferenceExp(localDeclaration), paramArrayOfExpression);
    localLambdaExp.body = new IfExp(localExpression, new BeginExp(localLambdaExp.body, paramArrayOfExpression), QuoteExp.voidExp);
    this.lexical.pop(localLambdaExp);
    this.current_scope = localScopeExp.outer;
    return localScopeExp;
  }
  
  public void loopStart()
  {
    LambdaExp localLambdaExp = new LambdaExp();
    LetExp localLetExp = new LetExp(new Expression[] { localLambdaExp });
    localLetExp.addDeclaration("%do%loop").noteValue(localLambdaExp);
    localLambdaExp.setName("%do%loop");
    localLetExp.outer = this.current_scope;
    localLambdaExp.outer = localLetExp;
    this.current_scope = localLambdaExp;
  }
  
  public Declaration loopVariable(Object paramObject, Type paramType, Expression paramExpression)
  {
    checkLoop();
    LambdaExp localLambdaExp = (LambdaExp)this.current_scope;
    paramObject = localLambdaExp.addDeclaration(paramObject, paramType);
    if (this.exprStack == null) {
      this.exprStack = new Stack();
    }
    this.exprStack.push(paramExpression);
    localLambdaExp.min_args += 1;
    return (Declaration)paramObject;
  }
  
  public boolean makeRunnable()
  {
    return (!generatingServlet()) && (!generatingApplet()) && (!getModule().staticInitRun());
  }
  
  public void mustCompileHere()
  {
    if ((!this.mustCompile) && (!ModuleExp.compilerAvailable))
    {
      error('w', "this expression claimed that it must be compiled, but compiler is unavailable");
      return;
    }
    this.mustCompile = true;
  }
  
  public void outputClass(String paramString)
    throws IOException
  {
    char c = File.separatorChar;
    int i = 0;
    while (i < this.numClasses)
    {
      ClassType localClassType = this.classes[i];
      String str1 = paramString + localClassType.getName().replace('.', c) + ".class";
      String str2 = new File(str1).getParent();
      if (str2 != null) {
        new File(str2).mkdirs();
      }
      localClassType.writeToFile(str1);
      i += 1;
    }
    this.minfo.cleanupAfterCompilation();
  }
  
  public Expression parse(Object paramObject)
  {
    throw new Error("unimeplemented parse");
  }
  
  public final void pop()
  {
    pop(this.current_scope);
  }
  
  public void pop(ScopeExp paramScopeExp)
  {
    this.lexical.pop(paramScopeExp);
    this.current_scope = paramScopeExp.outer;
  }
  
  /* Error */
  public void process(int paramInt)
  {
    // Byte code:
    //   0: bipush 10
    //   2: istore 7
    //   4: bipush 8
    //   6: istore 8
    //   8: bipush 6
    //   10: istore 9
    //   12: bipush 100
    //   14: istore 6
    //   16: aload_0
    //   17: invokestatic 2173	gnu/expr/Compilation:setSaveCurrent	(Lgnu/expr/Compilation;)Lgnu/expr/Compilation;
    //   20: astore_2
    //   21: aload_0
    //   22: invokevirtual 1615	gnu/expr/Compilation:getModule	()Lgnu/expr/ModuleExp;
    //   25: astore_3
    //   26: iload_1
    //   27: iconst_4
    //   28: if_icmplt +80 -> 108
    //   31: aload_0
    //   32: invokevirtual 2175	gnu/expr/Compilation:getState	()I
    //   35: iconst_3
    //   36: if_icmpge +72 -> 108
    //   39: aload_0
    //   40: iconst_3
    //   41: invokevirtual 2178	gnu/expr/Compilation:setState	(I)V
    //   44: aload_0
    //   45: getfield 565	gnu/expr/Compilation:language	Lgnu/expr/Language;
    //   48: aload_0
    //   49: iconst_0
    //   50: invokevirtual 2181	gnu/expr/Language:parse	(Lgnu/expr/Compilation;I)Z
    //   53: pop
    //   54: aload_0
    //   55: getfield 2183	gnu/expr/Compilation:lexer	Lgnu/text/Lexer;
    //   58: invokevirtual 2186	gnu/text/Lexer:close	()V
    //   61: aload_0
    //   62: aconst_null
    //   63: putfield 2183	gnu/expr/Compilation:lexer	Lgnu/text/Lexer;
    //   66: aload_0
    //   67: getfield 567	gnu/expr/Compilation:messages	Lgnu/text/SourceMessages;
    //   70: invokevirtual 2189	gnu/text/SourceMessages:seenErrors	()Z
    //   73: ifeq +29 -> 102
    //   76: bipush 100
    //   78: istore 5
    //   80: aload_0
    //   81: iload 5
    //   83: invokevirtual 2178	gnu/expr/Compilation:setState	(I)V
    //   86: aload_0
    //   87: getfield 2191	gnu/expr/Compilation:pendingImports	Ljava/util/Stack;
    //   90: astore 4
    //   92: aload 4
    //   94: ifnull +14 -> 108
    //   97: aload_2
    //   98: invokestatic 2193	gnu/expr/Compilation:restoreCurrent	(Lgnu/expr/Compilation;)V
    //   101: return
    //   102: iconst_4
    //   103: istore 5
    //   105: goto -25 -> 80
    //   108: iload_1
    //   109: bipush 6
    //   111: if_icmplt +49 -> 160
    //   114: aload_0
    //   115: invokevirtual 2175	gnu/expr/Compilation:getState	()I
    //   118: bipush 6
    //   120: if_icmpge +40 -> 160
    //   123: aload_0
    //   124: aload_3
    //   125: invokevirtual 2195	gnu/expr/Compilation:addMainClass	(Lgnu/expr/ModuleExp;)V
    //   128: aload_0
    //   129: getfield 565	gnu/expr/Compilation:language	Lgnu/expr/Language;
    //   132: aload_0
    //   133: invokevirtual 2198	gnu/expr/Language:resolve	(Lgnu/expr/Compilation;)V
    //   136: iload 9
    //   138: istore 5
    //   140: aload_0
    //   141: getfield 567	gnu/expr/Compilation:messages	Lgnu/text/SourceMessages;
    //   144: invokevirtual 2189	gnu/text/SourceMessages:seenErrors	()Z
    //   147: ifeq +7 -> 154
    //   150: bipush 100
    //   152: istore 5
    //   154: aload_0
    //   155: iload 5
    //   157: invokevirtual 2178	gnu/expr/Compilation:setState	(I)V
    //   160: aload_0
    //   161: getfield 2200	gnu/expr/Compilation:explicit	Z
    //   164: ifne +39 -> 203
    //   167: aload_0
    //   168: getfield 1209	gnu/expr/Compilation:immediate	Z
    //   171: ifne +32 -> 203
    //   174: aload_0
    //   175: getfield 1195	gnu/expr/Compilation:minfo	Lgnu/expr/ModuleInfo;
    //   178: invokestatic 1827	gnu/expr/ModuleManager:getInstance	()Lgnu/expr/ModuleManager;
    //   181: invokestatic 2203	java/lang/System:currentTimeMillis	()J
    //   184: invokevirtual 2207	gnu/expr/ModuleInfo:checkCurrent	(Lgnu/expr/ModuleManager;J)Z
    //   187: ifeq +16 -> 203
    //   190: aload_0
    //   191: getfield 1195	gnu/expr/Compilation:minfo	Lgnu/expr/ModuleInfo;
    //   194: invokevirtual 2165	gnu/expr/ModuleInfo:cleanupAfterCompilation	()V
    //   197: aload_0
    //   198: bipush 14
    //   200: invokevirtual 2178	gnu/expr/Compilation:setState	(I)V
    //   203: iload_1
    //   204: bipush 8
    //   206: if_icmplt +41 -> 247
    //   209: aload_0
    //   210: invokevirtual 2175	gnu/expr/Compilation:getState	()I
    //   213: bipush 8
    //   215: if_icmpge +32 -> 247
    //   218: aload_0
    //   219: aload_3
    //   220: invokevirtual 2210	gnu/expr/Compilation:walkModule	(Lgnu/expr/ModuleExp;)V
    //   223: iload 8
    //   225: istore 5
    //   227: aload_0
    //   228: getfield 567	gnu/expr/Compilation:messages	Lgnu/text/SourceMessages;
    //   231: invokevirtual 2189	gnu/text/SourceMessages:seenErrors	()Z
    //   234: ifeq +7 -> 241
    //   237: bipush 100
    //   239: istore 5
    //   241: aload_0
    //   242: iload 5
    //   244: invokevirtual 2178	gnu/expr/Compilation:setState	(I)V
    //   247: iload_1
    //   248: bipush 10
    //   250: if_icmplt +68 -> 318
    //   253: aload_0
    //   254: invokevirtual 2175	gnu/expr/Compilation:getState	()I
    //   257: bipush 10
    //   259: if_icmpge +59 -> 318
    //   262: aload_0
    //   263: new 908	gnu/expr/LitTable
    //   266: dup
    //   267: aload_0
    //   268: invokespecial 2212	gnu/expr/LitTable:<init>	(Lgnu/expr/Compilation;)V
    //   271: putfield 906	gnu/expr/Compilation:litTable	Lgnu/expr/LitTable;
    //   274: aload_3
    //   275: iconst_1
    //   276: invokevirtual 2215	gnu/expr/ModuleExp:setCanRead	(Z)V
    //   279: aload_3
    //   280: aload_0
    //   281: invokestatic 2221	gnu/expr/FindCapturedVars:findCapturedVars	(Lgnu/expr/Expression;Lgnu/expr/Compilation;)V
    //   284: aload_3
    //   285: aload_0
    //   286: invokevirtual 2224	gnu/expr/ModuleExp:allocFields	(Lgnu/expr/Compilation;)V
    //   289: aload_3
    //   290: aload_0
    //   291: invokevirtual 2227	gnu/expr/ModuleExp:allocChildMethods	(Lgnu/expr/Compilation;)V
    //   294: iload 7
    //   296: istore 5
    //   298: aload_0
    //   299: getfield 567	gnu/expr/Compilation:messages	Lgnu/text/SourceMessages;
    //   302: invokevirtual 2189	gnu/text/SourceMessages:seenErrors	()Z
    //   305: ifeq +7 -> 312
    //   308: bipush 100
    //   310: istore 5
    //   312: aload_0
    //   313: iload 5
    //   315: invokevirtual 2178	gnu/expr/Compilation:setState	(I)V
    //   318: iload_1
    //   319: bipush 12
    //   321: if_icmplt +57 -> 378
    //   324: aload_0
    //   325: invokevirtual 2175	gnu/expr/Compilation:getState	()I
    //   328: bipush 12
    //   330: if_icmpge +48 -> 378
    //   333: aload_0
    //   334: getfield 1209	gnu/expr/Compilation:immediate	Z
    //   337: ifeq +17 -> 354
    //   340: aload_0
    //   341: new 900	gnu/bytecode/ArrayClassLoader
    //   344: dup
    //   345: invokestatic 2230	gnu/bytecode/ObjectType:getContextClassLoader	()Ljava/lang/ClassLoader;
    //   348: invokespecial 2233	gnu/bytecode/ArrayClassLoader:<init>	(Ljava/lang/ClassLoader;)V
    //   351: putfield 897	gnu/expr/Compilation:loader	Lgnu/bytecode/ArrayClassLoader;
    //   354: aload_0
    //   355: invokevirtual 2235	gnu/expr/Compilation:generateBytecode	()V
    //   358: aload_0
    //   359: getfield 567	gnu/expr/Compilation:messages	Lgnu/text/SourceMessages;
    //   362: invokevirtual 2189	gnu/text/SourceMessages:seenErrors	()Z
    //   365: ifeq +49 -> 414
    //   368: iload 6
    //   370: istore 5
    //   372: aload_0
    //   373: iload 5
    //   375: invokevirtual 2178	gnu/expr/Compilation:setState	(I)V
    //   378: iload_1
    //   379: bipush 14
    //   381: if_icmplt +28 -> 409
    //   384: aload_0
    //   385: invokevirtual 2175	gnu/expr/Compilation:getState	()I
    //   388: bipush 14
    //   390: if_icmpge +19 -> 409
    //   393: aload_0
    //   394: invokestatic 1827	gnu/expr/ModuleManager:getInstance	()Lgnu/expr/ModuleManager;
    //   397: invokevirtual 1866	gnu/expr/ModuleManager:getCompilationDirectory	()Ljava/lang/String;
    //   400: invokevirtual 2237	gnu/expr/Compilation:outputClass	(Ljava/lang/String;)V
    //   403: aload_0
    //   404: bipush 14
    //   406: invokevirtual 2178	gnu/expr/Compilation:setState	(I)V
    //   409: aload_2
    //   410: invokestatic 2193	gnu/expr/Compilation:restoreCurrent	(Lgnu/expr/Compilation;)V
    //   413: return
    //   414: bipush 12
    //   416: istore 5
    //   418: goto -46 -> 372
    //   421: astore_3
    //   422: aload_0
    //   423: bipush 100
    //   425: invokevirtual 2178	gnu/expr/Compilation:setState	(I)V
    //   428: aload_3
    //   429: invokevirtual 2239	gnu/text/SyntaxException:getMessages	()Lgnu/text/SourceMessages;
    //   432: aload_0
    //   433: invokevirtual 2240	gnu/expr/Compilation:getMessages	()Lgnu/text/SourceMessages;
    //   436: if_acmpeq +38 -> 474
    //   439: new 1568	java/lang/RuntimeException
    //   442: dup
    //   443: new 1140	java/lang/StringBuilder
    //   446: dup
    //   447: invokespecial 1141	java/lang/StringBuilder:<init>	()V
    //   450: ldc_w 2242
    //   453: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   456: aload_3
    //   457: invokevirtual 1573	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   460: invokevirtual 1152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   463: invokespecial 1574	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   466: athrow
    //   467: astore_3
    //   468: aload_2
    //   469: invokestatic 2193	gnu/expr/Compilation:restoreCurrent	(Lgnu/expr/Compilation;)V
    //   472: aload_3
    //   473: athrow
    //   474: aload_2
    //   475: invokestatic 2193	gnu/expr/Compilation:restoreCurrent	(Lgnu/expr/Compilation;)V
    //   478: return
    //   479: astore_3
    //   480: aload_3
    //   481: invokevirtual 2245	java/io/IOException:printStackTrace	()V
    //   484: aload_0
    //   485: bipush 102
    //   487: new 1140	java/lang/StringBuilder
    //   490: dup
    //   491: invokespecial 1141	java/lang/StringBuilder:<init>	()V
    //   494: ldc_w 2247
    //   497: invokevirtual 1146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   500: aload_3
    //   501: invokevirtual 1573	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   504: invokevirtual 1152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   507: invokevirtual 1332	gnu/expr/Compilation:error	(CLjava/lang/String;)V
    //   510: aload_0
    //   511: bipush 100
    //   513: invokevirtual 2178	gnu/expr/Compilation:setState	(I)V
    //   516: aload_2
    //   517: invokestatic 2193	gnu/expr/Compilation:restoreCurrent	(Lgnu/expr/Compilation;)V
    //   520: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	521	0	this	Compilation
    //   0	521	1	paramInt	int
    //   20	497	2	localCompilation	Compilation
    //   25	265	3	localModuleExp	ModuleExp
    //   421	36	3	localSyntaxException	gnu.text.SyntaxException
    //   467	6	3	localObject	Object
    //   479	22	3	localIOException	IOException
    //   90	3	4	localStack	Stack
    //   78	339	5	i	int
    //   14	355	6	j	int
    //   2	293	7	k	int
    //   6	218	8	m	int
    //   10	127	9	n	int
    // Exception table:
    //   from	to	target	type
    //   21	26	421	gnu/text/SyntaxException
    //   31	76	421	gnu/text/SyntaxException
    //   80	92	421	gnu/text/SyntaxException
    //   114	136	421	gnu/text/SyntaxException
    //   140	150	421	gnu/text/SyntaxException
    //   154	160	421	gnu/text/SyntaxException
    //   160	203	421	gnu/text/SyntaxException
    //   209	223	421	gnu/text/SyntaxException
    //   227	237	421	gnu/text/SyntaxException
    //   241	247	421	gnu/text/SyntaxException
    //   253	294	421	gnu/text/SyntaxException
    //   298	308	421	gnu/text/SyntaxException
    //   312	318	421	gnu/text/SyntaxException
    //   324	354	421	gnu/text/SyntaxException
    //   354	368	421	gnu/text/SyntaxException
    //   372	378	421	gnu/text/SyntaxException
    //   384	409	421	gnu/text/SyntaxException
    //   21	26	467	finally
    //   31	76	467	finally
    //   80	92	467	finally
    //   114	136	467	finally
    //   140	150	467	finally
    //   154	160	467	finally
    //   160	203	467	finally
    //   209	223	467	finally
    //   227	237	467	finally
    //   241	247	467	finally
    //   253	294	467	finally
    //   298	308	467	finally
    //   312	318	467	finally
    //   324	354	467	finally
    //   354	368	467	finally
    //   372	378	467	finally
    //   384	409	467	finally
    //   422	467	467	finally
    //   480	516	467	finally
    //   21	26	479	java/io/IOException
    //   31	76	479	java/io/IOException
    //   80	92	479	java/io/IOException
    //   114	136	479	java/io/IOException
    //   140	150	479	java/io/IOException
    //   154	160	479	java/io/IOException
    //   160	203	479	java/io/IOException
    //   209	223	479	java/io/IOException
    //   227	237	479	java/io/IOException
    //   241	247	479	java/io/IOException
    //   253	294	479	java/io/IOException
    //   298	308	479	java/io/IOException
    //   312	318	479	java/io/IOException
    //   324	354	479	java/io/IOException
    //   354	368	479	java/io/IOException
    //   372	378	479	java/io/IOException
    //   384	409	479	java/io/IOException
  }
  
  public void push(Declaration paramDeclaration)
  {
    this.lexical.push(paramDeclaration);
  }
  
  public void push(ScopeExp paramScopeExp)
  {
    pushScope(paramScopeExp);
    this.lexical.push(paramScopeExp);
  }
  
  void pushChain(ScopeExp paramScopeExp1, ScopeExp paramScopeExp2)
  {
    if (paramScopeExp1 != paramScopeExp2)
    {
      pushChain(paramScopeExp1.outer, paramScopeExp2);
      pushScope(paramScopeExp1);
      this.lexical.push(paramScopeExp1);
    }
  }
  
  public ModuleExp pushNewModule(Lexer paramLexer)
  {
    this.lexer = paramLexer;
    return pushNewModule(paramLexer.getName());
  }
  
  public ModuleExp pushNewModule(String paramString)
  {
    ModuleExp localModuleExp = new ModuleExp();
    if (paramString != null) {
      localModuleExp.setFile(paramString);
    }
    if ((generatingApplet()) || (generatingServlet())) {
      localModuleExp.setFlag(131072);
    }
    if (this.immediate)
    {
      localModuleExp.setFlag(1048576);
      new ModuleInfo().setCompilation(this);
    }
    this.mainLambda = localModuleExp;
    push(localModuleExp);
    return localModuleExp;
  }
  
  public void pushPendingImport(ModuleInfo paramModuleInfo, ScopeExp paramScopeExp, int paramInt)
  {
    if (this.pendingImports == null) {
      this.pendingImports = new Stack();
    }
    this.pendingImports.push(paramModuleInfo);
    this.pendingImports.push(paramScopeExp);
    paramModuleInfo = new ReferenceExp((Object)null);
    paramModuleInfo.setLine(this);
    this.pendingImports.push(paramModuleInfo);
    this.pendingImports.push(Integer.valueOf(paramInt));
  }
  
  public final void pushScope(ScopeExp paramScopeExp)
  {
    if ((!this.mustCompile) && ((paramScopeExp.mustCompile()) || ((ModuleExp.compilerAvailable) && ((paramScopeExp instanceof LambdaExp)) && (!(paramScopeExp instanceof ModuleExp))))) {
      mustCompileHere();
    }
    paramScopeExp.outer = this.current_scope;
    this.current_scope = paramScopeExp;
  }
  
  public Object resolve(Object paramObject, boolean paramBoolean)
  {
    Environment localEnvironment = Environment.getCurrent();
    if ((paramObject instanceof String)) {}
    for (paramObject = localEnvironment.defaultNamespace().lookup((String)paramObject); paramObject == null; paramObject = (Symbol)paramObject) {
      return null;
    }
    if ((paramBoolean) && (getLanguage().hasSeparateFunctionNamespace())) {
      return localEnvironment.getFunction((Symbol)paramObject, null);
    }
    return localEnvironment.get((EnvironmentKey)paramObject, null);
  }
  
  public void setColumn(int paramInt)
  {
    this.messages.setColumn(paramInt);
  }
  
  public void setCurrentScope(ScopeExp paramScopeExp)
  {
    int j = ScopeExp.nesting(paramScopeExp);
    int i = ScopeExp.nesting(this.current_scope);
    while (i > j)
    {
      pop(this.current_scope);
      i -= 1;
    }
    ScopeExp localScopeExp1 = paramScopeExp;
    ScopeExp localScopeExp2;
    for (;;)
    {
      localScopeExp2 = localScopeExp1;
      if (j <= i) {
        break;
      }
      localScopeExp1 = localScopeExp1.outer;
      j -= 1;
    }
    while (localScopeExp2 != this.current_scope)
    {
      pop(this.current_scope);
      localScopeExp2 = localScopeExp2.outer;
    }
    pushChain(paramScopeExp, localScopeExp2);
  }
  
  public void setFile(String paramString)
  {
    this.messages.setFile(paramString);
  }
  
  public void setLine(int paramInt)
  {
    this.messages.setLine(paramInt);
  }
  
  public final void setLine(Expression paramExpression)
  {
    this.messages.setLocation(paramExpression);
  }
  
  public void setLine(Object paramObject)
  {
    if ((paramObject instanceof SourceLocator)) {
      this.messages.setLocation((SourceLocator)paramObject);
    }
  }
  
  public void setLine(String paramString, int paramInt1, int paramInt2)
  {
    this.messages.setLine(paramString, paramInt1, paramInt2);
  }
  
  public final void setLocation(SourceLocator paramSourceLocator)
  {
    this.messages.setLocation(paramSourceLocator);
  }
  
  public void setMessages(SourceMessages paramSourceMessages)
  {
    this.messages = paramSourceMessages;
  }
  
  public void setModule(ModuleExp paramModuleExp)
  {
    this.mainLambda = paramModuleExp;
  }
  
  public void setSharedModuleDefs(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.langOptions |= 0x2;
      return;
    }
    this.langOptions &= 0xFFFFFFFD;
  }
  
  public void setState(int paramInt)
  {
    this.state = paramInt;
  }
  
  public boolean sharedModuleDefs()
  {
    return (this.langOptions & 0x2) != 0;
  }
  
  public Expression syntaxError(String paramString)
  {
    error('e', paramString);
    return new ErrorExp(paramString);
  }
  
  public String toString()
  {
    return "<compilation " + this.mainLambda + ">";
  }
  
  public void usedClass(Type paramType)
  {
    while ((paramType instanceof ArrayType)) {
      paramType = ((ArrayType)paramType).getComponentType();
    }
    if ((this.immediate) && ((paramType instanceof ClassType))) {
      this.loader.addClass((ClassType)paramType);
    }
  }
  
  public boolean usingCPStyle()
  {
    return defaultCallConvention == 4;
  }
  
  public boolean usingTailCalls()
  {
    return defaultCallConvention >= 3;
  }
  
  public void walkModule(ModuleExp paramModuleExp)
  {
    if (debugPrintExpr)
    {
      OutPort localOutPort = OutPort.errDefault();
      localOutPort.println("[Module:" + paramModuleExp.getName());
      paramModuleExp.print(localOutPort);
      localOutPort.println(']');
      localOutPort.flush();
    }
    InlineCalls.inlineCalls(paramModuleExp, this);
    PushApply.pushApply(paramModuleExp);
    ChainLambdas.chainLambdas(paramModuleExp, this);
    FindTailCalls.findTailCalls(paramModuleExp, this);
  }
  
  public boolean warnAsError()
  {
    return this.currentOptions.getBoolean(warnAsError);
  }
  
  public boolean warnInvokeUnknownMethod()
  {
    return this.currentOptions.getBoolean(warnInvokeUnknownMethod);
  }
  
  public boolean warnUndefinedVariable()
  {
    return this.currentOptions.getBoolean(warnUndefinedVariable);
  }
  
  public boolean warnUnknownMember()
  {
    return this.currentOptions.getBoolean(warnUnknownMember);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\Compilation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */