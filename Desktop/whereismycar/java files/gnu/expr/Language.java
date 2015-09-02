package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.kawa.lispexpr.ClassNamespace;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.AbstractFormat;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.Convert;
import gnu.lists.FString;
import gnu.lists.PrintConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.InPort;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.NamedLocation;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kawa.repl;

public abstract class Language
{
  public static final int FUNCTION_NAMESPACE = 2;
  public static final int NAMESPACE_PREFIX_NAMESPACE = 4;
  public static final int PARSE_CURRENT_NAMES = 2;
  public static final int PARSE_EXPLICIT = 64;
  public static final int PARSE_FOR_APPLET = 16;
  public static final int PARSE_FOR_EVAL = 3;
  public static final int PARSE_FOR_SERVLET = 32;
  public static final int PARSE_IMMEDIATE = 1;
  public static final int PARSE_ONE_LINE = 4;
  public static final int PARSE_PROLOG = 8;
  public static final int VALUE_NAMESPACE = 1;
  protected static final InheritableThreadLocal<Language> current = new InheritableThreadLocal();
  static int envCounter;
  protected static int env_counter = 0;
  protected static Language global;
  static String[][] languages;
  public static boolean requirePedantic;
  protected Environment environ;
  protected Environment userEnv;
  
  static
  {
    Environment.setGlobal(BuiltinEnvironment.getInstance());
    String[] arrayOfString1 = { "scheme", ".scm", ".sc", "kawa.standard.Scheme" };
    String[] arrayOfString2 = { "brl", ".brl", "gnu.kawa.brl.BRL" };
    String[] arrayOfString3 = { "emacs", "elisp", "emacs-lisp", ".el", "gnu.jemacs.lang.ELisp" };
    String[] arrayOfString4 = { "xquery", ".xquery", ".xq", ".xql", "gnu.xquery.lang.XQuery" };
    String[] arrayOfString5 = { "xslt", "xsl", ".xsl", "gnu.kawa.xslt.XSLT" };
    languages = new String[][] { arrayOfString1, { "krl", ".krl", "gnu.kawa.brl.BRL" }, arrayOfString2, arrayOfString3, arrayOfString4, { "q2", ".q2", "gnu.q2.lang.Q2" }, arrayOfString5, { "commonlisp", "common-lisp", "clisp", "lisp", ".lisp", ".lsp", ".cl", "gnu.commonlisp.lang.CommonLisp" } };
  }
  
  protected Language()
  {
    Convert.setInstance(KawaConvert.getInstance());
  }
  
  public static Language detect(InPort paramInPort)
    throws IOException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramInPort.mark(300);
    paramInPort.readLine(localStringBuffer, 'P');
    paramInPort.reset();
    return detect(localStringBuffer.toString());
  }
  
  public static Language detect(InputStream paramInputStream)
    throws IOException
  {
    if (!paramInputStream.markSupported()) {
      return null;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    paramInputStream.mark(200);
    for (;;)
    {
      if (localStringBuffer.length() >= 200) {}
      int i;
      do
      {
        paramInputStream.reset();
        return detect(localStringBuffer.toString());
        i = paramInputStream.read();
      } while ((i < 0) || (i == 10) || (i == 13));
      localStringBuffer.append((char)i);
    }
  }
  
  public static Language detect(String paramString)
  {
    paramString = paramString.trim();
    int i = paramString.indexOf("kawa:");
    if (i >= 0)
    {
      int j = i + 5;
      i = j;
      while ((i < paramString.length()) && (Character.isJavaIdentifierPart(paramString.charAt(i)))) {
        i += 1;
      }
      if (i > j)
      {
        Language localLanguage = getInstance(paramString.substring(j, i));
        if (localLanguage != null) {
          return localLanguage;
        }
      }
    }
    if (paramString.indexOf("-*- scheme -*-") >= 0) {
      return getInstance("scheme");
    }
    if (paramString.indexOf("-*- xquery -*-") >= 0) {
      return getInstance("xquery");
    }
    if (paramString.indexOf("-*- emacs-lisp -*-") >= 0) {
      return getInstance("elisp");
    }
    if ((paramString.indexOf("-*- common-lisp -*-") >= 0) || (paramString.indexOf("-*- lisp -*-") >= 0)) {
      return getInstance("common-lisp");
    }
    if (((paramString.charAt(0) == '(') && (paramString.charAt(1) == ':')) || ((paramString.length() >= 7) && (paramString.substring(0, 7).equals("xquery ")))) {
      return getInstance("xquery");
    }
    if ((paramString.charAt(0) == ';') && (paramString.charAt(1) == ';')) {
      return getInstance("scheme");
    }
    return null;
  }
  
  public static Language getDefaultLanguage()
  {
    Language localLanguage = (Language)current.get();
    if (localLanguage != null) {
      return localLanguage;
    }
    return global;
  }
  
  public static Language getInstance(String paramString)
  {
    int n = languages.length;
    int i = 0;
    while (i < n)
    {
      String[] arrayOfString = languages[i];
      int k = arrayOfString.length - 1;
      int j = k;
      int m;
      do
      {
        m = j - 1;
        if (m < 0) {
          break label77;
        }
        if (paramString == null) {
          break;
        }
        j = m;
      } while (!arrayOfString[m].equalsIgnoreCase(paramString));
      try
      {
        Class localClass = Class.forName(arrayOfString[k]);
        return getInstance(arrayOfString[0], localClass);
      }
      catch (ClassNotFoundException localClassNotFoundException) {}
      label77:
      i += 1;
    }
    return null;
  }
  
  public static Language getInstance(String paramString, Class paramClass)
  {
    try
    {
      Class[] arrayOfClass = new Class[0];
      try
      {
        paramString = Character.toTitleCase(paramString.charAt(0)) + paramString.substring(1).toLowerCase();
        paramString = paramClass.getDeclaredMethod("get" + paramString + "Instance", arrayOfClass);
        return (Language)paramString.invoke(null, Values.noArgs);
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          paramString = paramClass.getDeclaredMethod("getInstance", arrayOfClass);
        }
      }
      paramString = ((InvocationTargetException)paramString).getTargetException();
    }
    catch (Exception paramString)
    {
      paramClass = paramClass.getName();
      if (!(paramString instanceof InvocationTargetException)) {}
    }
    for (;;)
    {
      throw new WrappedException("getInstance for '" + paramClass + "' failed", paramString);
    }
  }
  
  public static Language getInstanceFromFilenameExtension(String paramString)
  {
    int i = paramString.lastIndexOf('.');
    if (i > 0)
    {
      paramString = getInstance(paramString.substring(i));
      if (paramString != null) {
        return paramString;
      }
    }
    return null;
  }
  
  public static String[][] getLanguages()
  {
    return languages;
  }
  
  public static void registerLanguage(String[] paramArrayOfString)
  {
    String[][] arrayOfString = new String[languages.length + 1][];
    System.arraycopy(languages, 0, arrayOfString, 0, languages.length);
    arrayOfString[(arrayOfString.length - 1)] = paramArrayOfString;
    languages = arrayOfString;
  }
  
  public static void restoreCurrent(Language paramLanguage)
  {
    current.set(paramLanguage);
  }
  
  public static void setCurrentLanguage(Language paramLanguage)
  {
    current.set(paramLanguage);
  }
  
  public static void setDefaults(Language paramLanguage)
  {
    try
    {
      setCurrentLanguage(paramLanguage);
      global = paramLanguage;
      if (Environment.getGlobal() == BuiltinEnvironment.getInstance()) {
        Environment.setGlobal(Environment.getCurrent());
      }
      return;
    }
    finally
    {
      paramLanguage = finally;
      throw paramLanguage;
    }
  }
  
  public static Language setSaveCurrent(Language paramLanguage)
  {
    Language localLanguage = (Language)current.get();
    current.set(paramLanguage);
    return localLanguage;
  }
  
  public static Type string2Type(String paramString)
  {
    if (paramString.endsWith("[]"))
    {
      paramString = string2Type(paramString.substring(0, paramString.length() - 2));
      if (paramString == null) {
        return null;
      }
      return ArrayType.make(paramString);
    }
    if (Type.isValidJavaTypeName(paramString)) {
      return Type.getType(paramString);
    }
    return null;
  }
  
  public static Type unionType(Type paramType1, Type paramType2)
  {
    Object localObject = paramType1;
    if (paramType1 == Type.toStringType) {
      localObject = Type.javalangStringType;
    }
    paramType1 = paramType2;
    if (paramType2 == Type.toStringType) {
      paramType1 = Type.javalangStringType;
    }
    if (localObject == paramType1) {}
    int i;
    int j;
    do
    {
      do
      {
        do
        {
          return (Type)localObject;
          if ((!(localObject instanceof PrimType)) || (!(paramType1 instanceof PrimType))) {
            break;
          }
          i = ((Type)localObject).getSignature().charAt(0);
          j = paramType1.getSignature().charAt(0);
        } while (i == j);
        if (((i == 66) || (i == 83) || (i == 73)) && ((j == 73) || (j == 74))) {
          return paramType1;
        }
      } while (((j == 66) || (j == 83) || (j == 73)) && ((i == 73) || (i == 74)));
      if ((i == 70) && (j == 68)) {
        return paramType1;
      }
    } while ((j == 70) && (i == 68));
    return Type.objectType;
    return Type.objectType;
  }
  
  public final Type asType(Object paramObject)
  {
    Type localType = getTypeFor(paramObject, true);
    if (localType == null) {
      return (Type)paramObject;
    }
    return localType;
  }
  
  public Object booleanObject(boolean paramBoolean)
  {
    if (paramBoolean) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
  
  public Object coerceFromObject(Class paramClass, Object paramObject)
  {
    return getTypeFor(paramClass).coerceFromObject(paramObject);
  }
  
  public Object coerceToObject(Class paramClass, Object paramObject)
  {
    return getTypeFor(paramClass).coerceToObject(paramObject);
  }
  
  public Declaration declFromField(ModuleExp paramModuleExp, Object paramObject, gnu.bytecode.Field paramField)
  {
    Object localObject = paramField.getName();
    Type localType = paramField.getType();
    boolean bool1 = localType.isSubtype(Compilation.typeLocation);
    int k = 0;
    int i = 0;
    int j;
    boolean bool2;
    if ((paramField.getModifiers() & 0x10) != 0)
    {
      j = 1;
      bool2 = ((String)localObject).endsWith("$instance");
      if (!bool2) {
        break label231;
      }
      paramObject = localObject;
      label59:
      localObject = paramObject;
      if ((paramObject instanceof String))
      {
        localObject = paramModuleExp.getNamespaceUri();
        paramObject = (String)paramObject;
        if (localObject != null) {
          break label301;
        }
        localObject = SimpleSymbol.valueOf((String)paramObject);
      }
      label91:
      if (!bool1) {
        break label312;
      }
      paramObject = Type.objectType;
      label100:
      paramModuleExp = paramModuleExp.addDeclaration(localObject, (Type)paramObject);
      if ((paramField.getModifiers() & 0x8) == 0) {
        break label325;
      }
      k = 1;
      label121:
      if (!bool1) {
        break label331;
      }
      paramModuleExp.setIndirectBinding(true);
      if (((localType instanceof ClassType)) && (((ClassType)localType).isSubclass("gnu.mapping.ThreadLocation"))) {
        paramModuleExp.setFlag(268435456L);
      }
    }
    for (;;)
    {
      if (k != 0) {
        paramModuleExp.setFlag(2048L);
      }
      paramModuleExp.field = paramField;
      if ((j != 0) && (!bool1)) {
        paramModuleExp.setFlag(16384L);
      }
      if (bool2) {
        paramModuleExp.setFlag(1073741824L);
      }
      paramModuleExp.setSimple(false);
      if (i != 0) {
        paramModuleExp.setFlag(524320L);
      }
      return paramModuleExp;
      j = 0;
      break;
      label231:
      if ((j != 0) && ((paramObject instanceof Named)))
      {
        paramObject = ((Named)paramObject).getSymbol();
        break label59;
      }
      i = k;
      paramObject = localObject;
      if (((String)localObject).startsWith("$Prvt$"))
      {
        i = 1;
        paramObject = ((String)localObject).substring("$Prvt$".length());
      }
      paramObject = Compilation.demangleName((String)paramObject, true).intern();
      break label59;
      label301:
      localObject = Symbol.make(localObject, (String)paramObject);
      break label91;
      label312:
      paramObject = getTypeFor(localType.getReflectClass());
      break label100;
      label325:
      k = 0;
      break label121;
      label331:
      if ((j != 0) && ((localType instanceof ClassType))) {
        if (localType.isSubtype(Compilation.typeProcedure)) {
          paramModuleExp.setProcedureDecl(true);
        } else if (((ClassType)localType).isSubclass("gnu.mapping.Namespace")) {
          paramModuleExp.setFlag(2097152L);
        }
      }
    }
  }
  
  protected void defAliasStFld(String paramString1, String paramString2, String paramString3)
  {
    StaticFieldLocation.define(this.environ, getSymbol(paramString1), null, paramString2, paramString3);
  }
  
  protected void defProcStFld(String paramString1, String paramString2)
  {
    defProcStFld(paramString1, paramString2, Compilation.mangleNameIfNeeded(paramString1));
  }
  
  protected void defProcStFld(String paramString1, String paramString2, String paramString3)
  {
    if (hasSeparateFunctionNamespace()) {}
    for (Object localObject = EnvironmentKey.FUNCTION;; localObject = null)
    {
      paramString1 = getSymbol(paramString1);
      StaticFieldLocation.define(this.environ, paramString1, localObject, paramString2, paramString3).setProcedure();
      return;
    }
  }
  
  public void define(String paramString, Object paramObject)
  {
    paramString = getSymbol(paramString);
    this.environ.define(paramString, null, paramObject);
  }
  
  public final void defineFunction(Named paramNamed)
  {
    Object localObject1 = paramNamed.getSymbol();
    if ((localObject1 instanceof Symbol))
    {
      localObject1 = (Symbol)localObject1;
      if (!hasSeparateFunctionNamespace()) {
        break label53;
      }
    }
    label53:
    for (Object localObject2 = EnvironmentKey.FUNCTION;; localObject2 = null)
    {
      this.environ.define((Symbol)localObject1, localObject2, paramNamed);
      return;
      localObject1 = getSymbol(localObject1.toString());
      break;
    }
  }
  
  public void defineFunction(String paramString, Object paramObject)
  {
    if (hasSeparateFunctionNamespace()) {}
    for (Object localObject = EnvironmentKey.FUNCTION;; localObject = null)
    {
      this.environ.define(getSymbol(paramString), localObject, paramObject);
      return;
    }
  }
  
  public void emitCoerceToBoolean(CodeAttr paramCodeAttr)
  {
    emitPushBoolean(false, paramCodeAttr);
    paramCodeAttr.emitIfNEq();
    paramCodeAttr.emitPushInt(1);
    paramCodeAttr.emitElse();
    paramCodeAttr.emitPushInt(0);
    paramCodeAttr.emitFi();
  }
  
  public void emitPushBoolean(boolean paramBoolean, CodeAttr paramCodeAttr)
  {
    if (paramBoolean) {}
    for (gnu.bytecode.Field localField = Compilation.trueConstant;; localField = Compilation.falseConstant)
    {
      paramCodeAttr.emitGetStatic(localField);
      return;
    }
  }
  
  public final Object eval(InPort paramInPort)
    throws Throwable
  {
    CallContext localCallContext = CallContext.getInstance();
    int i = localCallContext.startFromContext();
    try
    {
      eval(paramInPort, localCallContext);
      paramInPort = localCallContext.getFromContext(i);
      return paramInPort;
    }
    catch (Throwable paramInPort)
    {
      localCallContext.cleanupFromContext(i);
      throw paramInPort;
    }
  }
  
  public final Object eval(Reader paramReader)
    throws Throwable
  {
    if ((paramReader instanceof InPort)) {}
    for (paramReader = (InPort)paramReader;; paramReader = new InPort(paramReader)) {
      return eval(paramReader);
    }
  }
  
  public final Object eval(String paramString)
    throws Throwable
  {
    return eval(new CharArrayInPort(paramString));
  }
  
  public void eval(InPort paramInPort, CallContext paramCallContext)
    throws Throwable
  {
    SourceMessages localSourceMessages = new SourceMessages();
    Language localLanguage = setSaveCurrent(this);
    try
    {
      paramInPort = parse(paramInPort, localSourceMessages, 3);
      ModuleExp.evalModule(getEnvironment(), paramCallContext, paramInPort, null, null);
      restoreCurrent(localLanguage);
      if (localSourceMessages.seenErrors()) {
        throw new RuntimeException("invalid syntax in eval form:\n" + localSourceMessages.toString(20));
      }
    }
    finally
    {
      restoreCurrent(localLanguage);
    }
  }
  
  public void eval(Reader paramReader, Consumer paramConsumer)
    throws Throwable
  {
    if ((paramReader instanceof InPort)) {}
    for (paramReader = (InPort)paramReader;; paramReader = new InPort(paramReader))
    {
      CallContext localCallContext = CallContext.getInstance();
      Consumer localConsumer = localCallContext.consumer;
      try
      {
        localCallContext.consumer = paramConsumer;
        eval(paramReader, localCallContext);
        return;
      }
      finally
      {
        localCallContext.consumer = localConsumer;
      }
    }
  }
  
  public final void eval(Reader paramReader, Writer paramWriter)
    throws Throwable
  {
    eval(paramReader, getOutputConsumer(paramWriter));
  }
  
  public final void eval(String paramString, Consumer paramConsumer)
    throws Throwable
  {
    eval(new CharArrayInPort(paramString), paramConsumer);
  }
  
  public final void eval(String paramString, PrintConsumer paramPrintConsumer)
    throws Throwable
  {
    eval(paramString, getOutputConsumer(paramPrintConsumer));
  }
  
  public final void eval(String paramString, Writer paramWriter)
    throws Throwable
  {
    eval(new CharArrayInPort(paramString), paramWriter);
  }
  
  public String formatType(Type paramType)
  {
    return paramType.getName();
  }
  
  public Compilation getCompilation(Lexer paramLexer, SourceMessages paramSourceMessages, NameLookup paramNameLookup)
  {
    return new Compilation(this, paramSourceMessages, paramNameLookup);
  }
  
  public Object getEnvPropertyFor(Declaration paramDeclaration)
  {
    if ((hasSeparateFunctionNamespace()) && (paramDeclaration.isProcedureDecl())) {
      return EnvironmentKey.FUNCTION;
    }
    return null;
  }
  
  public Object getEnvPropertyFor(java.lang.reflect.Field paramField, Object paramObject)
  {
    if (!hasSeparateFunctionNamespace()) {}
    while (!Compilation.typeProcedure.getReflectClass().isAssignableFrom(paramField.getType())) {
      return null;
    }
    return EnvironmentKey.FUNCTION;
  }
  
  public final Environment getEnvironment()
  {
    if (this.userEnv != null) {
      return this.userEnv;
    }
    return Environment.getCurrent();
  }
  
  public AbstractFormat getFormat(boolean paramBoolean)
  {
    return null;
  }
  
  public Environment getLangEnvironment()
  {
    return this.environ;
  }
  
  public final Type getLangTypeFor(Type paramType)
  {
    Type localType = paramType;
    if (paramType.isExisting())
    {
      Class localClass = paramType.getReflectClass();
      localType = paramType;
      if (localClass != null) {
        localType = getTypeFor(localClass);
      }
    }
    return localType;
  }
  
  public abstract Lexer getLexer(InPort paramInPort, SourceMessages paramSourceMessages);
  
  public String getName()
  {
    String str2 = getClass().getName();
    int i = str2.lastIndexOf('.');
    String str1 = str2;
    if (i >= 0) {
      str1 = str2.substring(i + 1);
    }
    return str1;
  }
  
  public int getNamespaceOf(Declaration paramDeclaration)
  {
    return 1;
  }
  
  public final Environment getNewEnvironment()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("environment-");
    int i = envCounter + 1;
    envCounter = i;
    return Environment.make(i, this.environ);
  }
  
  public Consumer getOutputConsumer(Writer paramWriter)
  {
    if ((paramWriter instanceof OutPort)) {}
    for (paramWriter = (OutPort)paramWriter;; paramWriter = new OutPort(paramWriter))
    {
      paramWriter.objectFormat = getFormat(false);
      return paramWriter;
    }
  }
  
  public Procedure getPrompter()
  {
    Object localObject = null;
    if (hasSeparateFunctionNamespace()) {
      localObject = EnvironmentKey.FUNCTION;
    }
    localObject = (Procedure)getEnvironment().get(getSymbol("default-prompter"), localObject, null);
    if (localObject != null) {
      return (Procedure)localObject;
    }
    return new SimplePrompter();
  }
  
  public Symbol getSymbol(String paramString)
  {
    return this.environ.getSymbol(paramString);
  }
  
  public final Type getTypeFor(Expression paramExpression)
  {
    return getTypeFor(paramExpression, true);
  }
  
  public Type getTypeFor(Expression paramExpression, boolean paramBoolean)
  {
    Object localObject2 = null;
    Object localObject1;
    if ((paramExpression instanceof QuoteExp))
    {
      paramExpression = ((QuoteExp)paramExpression).getValue();
      if ((paramExpression instanceof Type)) {
        localObject1 = (Type)paramExpression;
      }
    }
    label266:
    label360:
    do
    {
      int i;
      do
      {
        do
        {
          do
          {
            Object localObject3;
            Expression localExpression;
            do
            {
              return (Type)localObject1;
              if ((paramExpression instanceof Class)) {
                return Type.make((Class)paramExpression);
              }
              return getTypeFor(paramExpression, paramBoolean);
              if (!(paramExpression instanceof ReferenceExp)) {
                break label360;
              }
              paramExpression = (ReferenceExp)paramExpression;
              localObject3 = Declaration.followAliases(paramExpression.getBinding());
              localObject1 = paramExpression.getName();
              paramExpression = (Expression)localObject1;
              if (localObject3 == null) {
                break;
              }
              localExpression = ((Declaration)localObject3).getValue();
              if (((localExpression instanceof QuoteExp)) && (((Declaration)localObject3).getFlag(16384L)) && (!((Declaration)localObject3).isIndirectBinding())) {
                return getTypeFor(((QuoteExp)localExpression).getValue(), paramBoolean);
              }
              if (((localExpression instanceof ClassExp)) || ((localExpression instanceof ModuleExp)))
              {
                ((Declaration)localObject3).setCanRead(true);
                return ((LambdaExp)localExpression).getClassType();
              }
              if ((!((Declaration)localObject3).isAlias()) || (!(localExpression instanceof QuoteExp))) {
                break label266;
              }
              localObject3 = ((QuoteExp)localExpression).getValue();
              paramExpression = (Expression)localObject1;
              if (!(localObject3 instanceof Location)) {
                break;
              }
              paramExpression = (Location)localObject3;
              if (paramExpression.isBound()) {
                return getTypeFor(paramExpression.get(), paramBoolean);
              }
              localObject1 = localObject2;
            } while (!(paramExpression instanceof Named));
            paramExpression = ((Named)paramExpression).getName();
            do
            {
              localObject1 = getEnvironment().get(paramExpression);
              if (!(localObject1 instanceof Type)) {
                break;
              }
              return (Type)localObject1;
              paramExpression = (Expression)localObject1;
            } while (((Declaration)localObject3).getFlag(65536L));
            return getTypeFor(localExpression, paramBoolean);
            if ((localObject1 instanceof ClassNamespace)) {
              return ((ClassNamespace)localObject1).getClassType();
            }
            i = paramExpression.length();
            localObject1 = localObject2;
          } while (i <= 2);
          localObject1 = localObject2;
        } while (paramExpression.charAt(0) != '<');
        localObject1 = localObject2;
      } while (paramExpression.charAt(i - 1) != '>');
      return getTypeFor(paramExpression.substring(1, i - 1));
      if ((paramExpression instanceof ClassExp)) {
        break;
      }
      localObject1 = localObject2;
    } while (!(paramExpression instanceof ModuleExp));
    return ((LambdaExp)paramExpression).getClassType();
  }
  
  public Type getTypeFor(Class paramClass)
  {
    return Type.make(paramClass);
  }
  
  public final Type getTypeFor(Object paramObject, boolean paramBoolean)
  {
    if ((paramObject instanceof Type)) {
      return (Type)paramObject;
    }
    if ((paramObject instanceof Class)) {
      return getTypeFor((Class)paramObject);
    }
    if ((paramBoolean) && (((paramObject instanceof FString)) || ((paramObject instanceof String)) || (((paramObject instanceof Symbol)) && (((Symbol)paramObject).hasEmptyNamespace())) || ((paramObject instanceof CharSeq)))) {
      return getTypeFor(paramObject.toString());
    }
    if ((paramObject instanceof Namespace))
    {
      paramObject = ((Namespace)paramObject).getName();
      if ((paramObject != null) && (((String)paramObject).startsWith("class:"))) {
        return getLangTypeFor(string2Type(((String)paramObject).substring(6)));
      }
    }
    return null;
  }
  
  public Type getTypeFor(String paramString)
  {
    return string2Type(paramString);
  }
  
  public boolean hasNamespace(Declaration paramDeclaration, int paramInt)
  {
    return (getNamespaceOf(paramDeclaration) & paramInt) != 0;
  }
  
  public boolean hasSeparateFunctionNamespace()
  {
    return false;
  }
  
  public boolean isTrue(Object paramObject)
  {
    return paramObject != Boolean.FALSE;
  }
  
  /* Error */
  public void loadClass(String paramString)
    throws ClassNotFoundException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 258	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   4: astore_2
    //   5: aload_2
    //   6: invokevirtual 841	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   9: astore_2
    //   10: aload_2
    //   11: aload_0
    //   12: invokestatic 353	gnu/mapping/Environment:getCurrent	()Lgnu/mapping/Environment;
    //   15: invokestatic 847	gnu/kawa/reflect/ClassMemberLocation:defineAll	(Ljava/lang/Object;Lgnu/expr/Language;Lgnu/mapping/Environment;)V
    //   18: aload_2
    //   19: instanceof 849
    //   22: ifeq +10 -> 32
    //   25: aload_2
    //   26: checkcast 849	gnu/expr/ModuleBody
    //   29: invokevirtual 852	gnu/expr/ModuleBody:run	()V
    //   32: return
    //   33: astore_1
    //   34: aload_1
    //   35: athrow
    //   36: astore_2
    //   37: new 314	gnu/mapping/WrappedException
    //   40: dup
    //   41: new 265	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 266	java/lang/StringBuilder:<init>	()V
    //   48: ldc_w 854
    //   51: invokevirtual 282	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: aload_1
    //   55: invokevirtual 282	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: invokevirtual 283	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   61: aload_2
    //   62: invokespecial 321	gnu/mapping/WrappedException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	Language
    //   0	66	1	paramString	String
    //   4	22	2	localObject	Object
    //   36	26	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   0	5	33	java/lang/ClassNotFoundException
    //   5	32	36	java/lang/Exception
  }
  
  public Object lookup(String paramString)
  {
    return this.environ.get(paramString);
  }
  
  public NamedLocation lookupBuiltin(Symbol paramSymbol, Object paramObject, int paramInt)
  {
    if (this.environ == null) {
      return null;
    }
    return this.environ.lookup(paramSymbol, paramObject, paramInt);
  }
  
  public Object noValue()
  {
    return Values.empty;
  }
  
  public final Compilation parse(InPort paramInPort, SourceMessages paramSourceMessages, int paramInt)
    throws IOException, SyntaxException
  {
    return parse(getLexer(paramInPort, paramSourceMessages), paramInt, null);
  }
  
  public final Compilation parse(InPort paramInPort, SourceMessages paramSourceMessages, int paramInt, ModuleInfo paramModuleInfo)
    throws IOException, SyntaxException
  {
    return parse(getLexer(paramInPort, paramSourceMessages), paramInt, paramModuleInfo);
  }
  
  public final Compilation parse(InPort paramInPort, SourceMessages paramSourceMessages, ModuleInfo paramModuleInfo)
    throws IOException, SyntaxException
  {
    return parse(getLexer(paramInPort, paramSourceMessages), 8, paramModuleInfo);
  }
  
  public final Compilation parse(Lexer paramLexer, int paramInt, ModuleInfo paramModuleInfo)
    throws IOException, SyntaxException
  {
    SourceMessages localSourceMessages = paramLexer.getMessages();
    Object localObject;
    boolean bool;
    if ((paramInt & 0x2) != 0)
    {
      localObject = NameLookup.getInstance(getEnvironment(), this);
      if ((paramInt & 0x1) == 0) {
        break label148;
      }
      bool = true;
      label31:
      localObject = getCompilation(paramLexer, localSourceMessages, (NameLookup)localObject);
      if (requirePedantic) {
        ((Compilation)localObject).pedantic = true;
      }
      if (!bool) {
        ((Compilation)localObject).mustCompile = true;
      }
      ((Compilation)localObject).immediate = bool;
      ((Compilation)localObject).langOptions = paramInt;
      if ((paramInt & 0x40) != 0) {
        ((Compilation)localObject).explicit = true;
      }
      if ((paramInt & 0x8) != 0) {
        ((Compilation)localObject).setState(1);
      }
      ((Compilation)localObject).pushNewModule(paramLexer);
      if (paramModuleInfo != null) {
        paramModuleInfo.setCompilation((Compilation)localObject);
      }
      if (parse((Compilation)localObject, paramInt)) {
        break label154;
      }
      paramLexer = null;
    }
    label148:
    label154:
    do
    {
      return paramLexer;
      localObject = new NameLookup(this);
      break;
      bool = false;
      break label31;
      paramLexer = (Lexer)localObject;
    } while (((Compilation)localObject).getState() != 1);
    ((Compilation)localObject).setState(2);
    return (Compilation)localObject;
  }
  
  public abstract boolean parse(Compilation paramCompilation, int paramInt)
    throws IOException, SyntaxException;
  
  public void resolve(Compilation paramCompilation) {}
  
  public void runAsApplication(String[] paramArrayOfString)
  {
    setDefaults(this);
    repl.main(paramArrayOfString);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\Language.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */