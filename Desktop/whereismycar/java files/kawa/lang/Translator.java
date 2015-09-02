package kawa.lang;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.bytecode.Member;
import gnu.bytecode.ObjectType;
import gnu.bytecode.ZipLoader;
import gnu.expr.ApplyExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.LangExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.Special;
import gnu.expr.ThisExp;
import gnu.kawa.functions.AppendValues;
import gnu.kawa.functions.CompileNamedPart;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.reflect.FieldLocation;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.xml.MakeAttribute;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.Namespace;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import gnu.xml.NamespaceBinding;
import java.util.Stack;
import java.util.Vector;
import kawa.standard.begin;
import kawa.standard.object;
import kawa.standard.require;

public class Translator
  extends Compilation
{
  private static Expression errorExp = new ErrorExp("unknown syntax error");
  public static final Declaration getNamedPartDecl = Declaration.getDeclarationFromStatic("gnu.kawa.functions.GetNamedPart", "getNamedPart");
  public LambdaExp curMethodLambda;
  public Macro currentMacroDefinition;
  Syntax currentSyntax;
  private Environment env = Environment.getCurrent();
  public int firstForm;
  public Stack formStack = new Stack();
  Declaration macroContext;
  public Declaration matchArray;
  Vector notedAccess;
  public PatternScope patternScope;
  public Object pendingForm;
  PairWithPosition positionPair;
  Stack renamedAliasStack;
  public Declaration templateScopeDecl;
  public NamespaceBinding xmlElementNamespaces = NamespaceBinding.predefinedXML;
  
  static
  {
    LispLanguage.getNamedPartLocation.setDeclaration(getNamedPartDecl);
  }
  
  public Translator(Language paramLanguage, SourceMessages paramSourceMessages, NameLookup paramNameLookup)
  {
    super(paramLanguage, paramSourceMessages, paramNameLookup);
  }
  
  static ReferenceExp getOriginalRef(Declaration paramDeclaration)
  {
    if ((paramDeclaration != null) && (paramDeclaration.isAlias()) && (!paramDeclaration.isIndirectBinding()))
    {
      paramDeclaration = paramDeclaration.getValue();
      if ((paramDeclaration instanceof ReferenceExp)) {
        return (ReferenceExp)paramDeclaration;
      }
    }
    return null;
  }
  
  public static int listLength(Object paramObject)
  {
    int i = 0;
    Object localObject1 = paramObject;
    Object localObject2 = paramObject;
    paramObject = localObject1;
    localObject1 = localObject2;
    Object localObject3;
    do
    {
      for (;;)
      {
        localObject2 = paramObject;
        if (!(localObject1 instanceof SyntaxForm)) {
          break;
        }
        localObject1 = ((SyntaxForm)localObject1).getDatum();
      }
      while ((localObject2 instanceof SyntaxForm)) {
        localObject2 = ((SyntaxForm)localObject2).getDatum();
      }
      if (localObject1 == LList.Empty) {
        return i;
      }
      if (!(localObject1 instanceof Pair)) {
        return -1 - i;
      }
      i += 1;
      for (paramObject = ((Pair)localObject1).getCdr(); (paramObject instanceof SyntaxForm); paramObject = ((SyntaxForm)paramObject).getDatum()) {}
      if (paramObject == LList.Empty) {
        return i;
      }
      if (!(paramObject instanceof Pair)) {
        return -1 - i;
      }
      localObject2 = ((Pair)localObject2).getCdr();
      localObject3 = ((Pair)paramObject).getCdr();
      i += 1;
      localObject1 = localObject3;
      paramObject = localObject2;
    } while (localObject3 != localObject2);
    return Integer.MIN_VALUE;
  }
  
  private Expression makeBody(int paramInt, ScopeExp paramScopeExp)
  {
    int j = this.formStack.size() - paramInt;
    if (j == 0) {
      return QuoteExp.voidExp;
    }
    if (j == 1) {
      return (Expression)this.formStack.pop();
    }
    Expression[] arrayOfExpression = new Expression[j];
    int i = 0;
    while (i < j)
    {
      arrayOfExpression[i] = ((Expression)this.formStack.elementAt(paramInt + i));
      i += 1;
    }
    this.formStack.setSize(paramInt);
    if ((paramScopeExp instanceof ModuleExp)) {
      return new ApplyExp(AppendValues.appendValues, arrayOfExpression);
    }
    return ((LispLanguage)getLanguage()).makeBody(arrayOfExpression);
  }
  
  public static Pair makePair(Pair paramPair, Object paramObject1, Object paramObject2)
  {
    if ((paramPair instanceof PairWithPosition)) {
      return new PairWithPosition((PairWithPosition)paramPair, paramObject1, paramObject2);
    }
    return new Pair(paramObject1, paramObject2);
  }
  
  /* Error */
  private void rewriteBody(LList paramLList)
  {
    // Byte code:
    //   0: aload_1
    //   1: getstatic 124	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   4: if_acmpeq +46 -> 50
    //   7: aload_1
    //   8: checkcast 126	gnu/lists/Pair
    //   11: astore_2
    //   12: aload_0
    //   13: aload_2
    //   14: invokevirtual 191	kawa/lang/Translator:pushPositionOf	(Ljava/lang/Object;)Ljava/lang/Object;
    //   17: astore_1
    //   18: aload_0
    //   19: aload_2
    //   20: invokevirtual 194	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   23: invokevirtual 198	kawa/lang/Translator:rewriteInBody	(Ljava/lang/Object;)V
    //   26: aload_0
    //   27: aload_1
    //   28: invokevirtual 201	kawa/lang/Translator:popPositionOf	(Ljava/lang/Object;)V
    //   31: aload_2
    //   32: invokevirtual 129	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   35: checkcast 120	gnu/lists/LList
    //   38: astore_1
    //   39: goto -39 -> 0
    //   42: astore_2
    //   43: aload_0
    //   44: aload_1
    //   45: invokevirtual 201	kawa/lang/Translator:popPositionOf	(Ljava/lang/Object;)V
    //   48: aload_2
    //   49: athrow
    //   50: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	51	0	this	Translator
    //   0	51	1	paramLList	LList
    //   11	21	2	localPair	Pair
    //   42	7	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   18	26	42	finally
  }
  
  public static Object safeCar(Object paramObject)
  {
    while ((paramObject instanceof SyntaxForm)) {
      paramObject = ((SyntaxForm)paramObject).getDatum();
    }
    if (!(paramObject instanceof Pair)) {
      return null;
    }
    return stripSyntax(((Pair)paramObject).getCar());
  }
  
  public static Object safeCdr(Object paramObject)
  {
    while ((paramObject instanceof SyntaxForm)) {
      paramObject = ((SyntaxForm)paramObject).getDatum();
    }
    if (!(paramObject instanceof Pair)) {
      return null;
    }
    return stripSyntax(((Pair)paramObject).getCdr());
  }
  
  public static void setLine(Declaration paramDeclaration, Object paramObject)
  {
    if ((paramObject instanceof SourceLocator)) {
      paramDeclaration.setLocation((SourceLocator)paramObject);
    }
  }
  
  public static void setLine(Expression paramExpression, Object paramObject)
  {
    if ((paramObject instanceof SourceLocator)) {
      paramExpression.setLocation((SourceLocator)paramObject);
    }
  }
  
  public static Object stripSyntax(Object paramObject)
  {
    while ((paramObject instanceof SyntaxForm)) {
      paramObject = ((SyntaxForm)paramObject).getDatum();
    }
    return paramObject;
  }
  
  static void vectorReverse(Vector paramVector, int paramInt1, int paramInt2)
  {
    int i = paramInt2 / 2;
    int j = paramInt1 + paramInt2 - 1;
    paramInt2 = 0;
    while (paramInt2 < i)
    {
      Object localObject = paramVector.elementAt(paramInt1 + paramInt2);
      paramVector.setElementAt(paramVector.elementAt(j - paramInt2), paramInt1 + paramInt2);
      paramVector.setElementAt(localObject, j - paramInt2);
      paramInt2 += 1;
    }
  }
  
  public static Object wrapSyntax(Object paramObject, SyntaxForm paramSyntaxForm)
  {
    if ((paramSyntaxForm == null) || ((paramObject instanceof Expression))) {
      return paramObject;
    }
    return SyntaxForms.fromDatumIfNeeded(paramObject, paramSyntaxForm);
  }
  
  Expression apply_rewrite(Syntax paramSyntax, Pair paramPair)
  {
    Object localObject = errorExp;
    localObject = this.currentSyntax;
    this.currentSyntax = paramSyntax;
    try
    {
      paramSyntax = paramSyntax.rewriteForm(paramPair, this);
      return paramSyntax;
    }
    finally
    {
      this.currentSyntax = ((Syntax)localObject);
    }
  }
  
  Syntax check_if_Syntax(Declaration paramDeclaration)
  {
    Declaration localDeclaration = Declaration.followAliases(paramDeclaration);
    Object localObject3 = null;
    Object localObject1 = localDeclaration.getValue();
    if ((localObject1 != null) && (localDeclaration.getFlag(32768L)))
    {
      try
      {
        if (!(paramDeclaration.getValue() instanceof ReferenceExp)) {
          break label149;
        }
        localDeclaration = ((ReferenceExp)paramDeclaration.getValue()).contextDecl();
        if (localDeclaration == null) {
          break label83;
        }
        this.macroContext = localDeclaration;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          localThrowable.printStackTrace();
          error('e', "unable to evaluate macro for " + paramDeclaration.getSymbol());
          localObject2 = localObject3;
          continue;
          if ((this.current_scope instanceof TemplateScope)) {
            this.macroContext = ((TemplateScope)this.current_scope).macroContext;
          }
        }
      }
      localObject1 = ((Expression)localObject1).eval(this.env);
    }
    for (;;)
    {
      if (!(localObject1 instanceof Syntax)) {
        break label209;
      }
      return (Syntax)localObject1;
      label83:
      if (!(this.current_scope instanceof TemplateScope)) {
        break;
      }
      this.macroContext = ((TemplateScope)this.current_scope).macroContext;
      break;
      label149:
      Object localObject2 = localObject3;
      if (paramDeclaration.getFlag(32768L))
      {
        localObject2 = localObject3;
        if (!paramDeclaration.needsContext()) {
          localObject2 = StaticFieldLocation.make(paramDeclaration).get(null);
        }
      }
    }
    label209:
    return null;
  }
  
  public Declaration define(Object paramObject, SyntaxForm paramSyntaxForm, ScopeExp paramScopeExp)
  {
    int i;
    if ((paramSyntaxForm != null) && (paramSyntaxForm.getScope() != currentScope()))
    {
      i = 1;
      if (i == 0) {
        break label89;
      }
    }
    label89:
    for (Object localObject = new String(paramObject.toString());; localObject = paramObject)
    {
      paramScopeExp = paramScopeExp.getDefine(localObject, 'w', this);
      if (i != 0)
      {
        paramObject = makeRenamedAlias(paramObject, paramScopeExp, paramSyntaxForm.getScope());
        paramSyntaxForm.getScope().addDeclaration((Declaration)paramObject);
      }
      push(paramScopeExp);
      return paramScopeExp;
      i = 0;
      break;
    }
  }
  
  /* Error */
  public gnu.bytecode.Type exp2Type(Pair paramPair)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 191	kawa/lang/Translator:pushPositionOf	(Ljava/lang/Object;)Ljava/lang/Object;
    //   5: astore_3
    //   6: aload_0
    //   7: aload_1
    //   8: iconst_0
    //   9: invokevirtual 347	kawa/lang/Translator:rewrite_car	(Lgnu/lists/Pair;Z)Lgnu/expr/Expression;
    //   12: aload_0
    //   13: invokestatic 353	gnu/expr/InlineCalls:inlineCalls	(Lgnu/expr/Expression;Lgnu/expr/Compilation;)Lgnu/expr/Expression;
    //   16: astore 4
    //   18: aload 4
    //   20: instanceof 62
    //   23: istore 6
    //   25: iload 6
    //   27: ifeq +10 -> 37
    //   30: aload_0
    //   31: aload_3
    //   32: invokevirtual 201	kawa/lang/Translator:popPositionOf	(Ljava/lang/Object;)V
    //   35: aconst_null
    //   36: areturn
    //   37: aload_0
    //   38: invokevirtual 172	kawa/lang/Translator:getLanguage	()Lgnu/expr/Language;
    //   41: aload 4
    //   43: invokevirtual 359	gnu/expr/Language:getTypeFor	(Lgnu/expr/Expression;)Lgnu/bytecode/Type;
    //   46: astore_2
    //   47: aload_2
    //   48: astore_1
    //   49: aload_2
    //   50: ifnonnull +31 -> 81
    //   53: aload 4
    //   55: aload_0
    //   56: getfield 95	kawa/lang/Translator:env	Lgnu/mapping/Environment;
    //   59: invokevirtual 266	gnu/expr/Expression:eval	(Lgnu/mapping/Environment;)Ljava/lang/Object;
    //   62: astore 5
    //   64: aload 5
    //   66: instanceof 361
    //   69: ifeq +73 -> 142
    //   72: aload 5
    //   74: checkcast 361	java/lang/Class
    //   77: invokestatic 366	gnu/bytecode/Type:make	(Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   80: astore_1
    //   81: aload_1
    //   82: ifnonnull +99 -> 181
    //   85: aload 4
    //   87: instanceof 110
    //   90: ifeq +71 -> 161
    //   93: aload_0
    //   94: bipush 101
    //   96: new 278	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 279	java/lang/StringBuilder:<init>	()V
    //   103: ldc_w 368
    //   106: invokevirtual 285	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload 4
    //   111: checkcast 110	gnu/expr/ReferenceExp
    //   114: invokevirtual 371	gnu/expr/ReferenceExp:getName	()Ljava/lang/String;
    //   117: invokevirtual 285	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: bipush 39
    //   122: invokevirtual 374	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   125: invokevirtual 295	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: invokevirtual 299	kawa/lang/Translator:error	(CLjava/lang/String;)V
    //   131: getstatic 378	gnu/bytecode/Type:pointer_type	Lgnu/bytecode/ClassType;
    //   134: astore_1
    //   135: aload_0
    //   136: aload_3
    //   137: invokevirtual 201	kawa/lang/Translator:popPositionOf	(Ljava/lang/Object;)V
    //   140: aload_1
    //   141: areturn
    //   142: aload_2
    //   143: astore_1
    //   144: aload 5
    //   146: instanceof 363
    //   149: ifeq -68 -> 81
    //   152: aload 5
    //   154: checkcast 363	gnu/bytecode/Type
    //   157: astore_1
    //   158: goto -77 -> 81
    //   161: aload_0
    //   162: bipush 101
    //   164: ldc_w 380
    //   167: invokevirtual 299	kawa/lang/Translator:error	(CLjava/lang/String;)V
    //   170: goto -39 -> 131
    //   173: astore_1
    //   174: aload_0
    //   175: aload_3
    //   176: invokevirtual 201	kawa/lang/Translator:popPositionOf	(Ljava/lang/Object;)V
    //   179: aload_1
    //   180: athrow
    //   181: aload_0
    //   182: aload_3
    //   183: invokevirtual 201	kawa/lang/Translator:popPositionOf	(Ljava/lang/Object;)V
    //   186: aload_1
    //   187: areturn
    //   188: astore_1
    //   189: aload_2
    //   190: astore_1
    //   191: goto -110 -> 81
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	194	0	this	Translator
    //   0	194	1	paramPair	Pair
    //   46	144	2	localType	gnu.bytecode.Type
    //   5	178	3	localObject1	Object
    //   16	94	4	localExpression	Expression
    //   62	91	5	localObject2	Object
    //   23	3	6	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   6	25	173	finally
    //   37	47	173	finally
    //   53	81	173	finally
    //   85	131	173	finally
    //   131	135	173	finally
    //   144	158	173	finally
    //   161	170	173	finally
    //   53	81	188	java/lang/Throwable
    //   144	158	188	java/lang/Throwable
  }
  
  public void finishModule(ModuleExp paramModuleExp)
  {
    boolean bool = paramModuleExp.isStatic();
    Declaration localDeclaration = paramModuleExp.firstDecl();
    if (localDeclaration != null)
    {
      String str;
      if (localDeclaration.getFlag(512L))
      {
        if (localDeclaration.getFlag(1024L))
        {
          str = "' exported but never defined";
          label39:
          error('e', localDeclaration, "'", str);
        }
      }
      else
      {
        if ((paramModuleExp.getFlag(16384)) || ((this.generateMain) && (!this.immediate)))
        {
          if (!localDeclaration.getFlag(1024L)) {
            break label163;
          }
          if (localDeclaration.isPrivate())
          {
            if (localDeclaration.getFlag(16777216L)) {
              error('e', localDeclaration, "'", "' is declared both private and exported");
            }
            localDeclaration.setPrivate(false);
          }
        }
        label119:
        if (!bool) {
          break label171;
        }
        localDeclaration.setFlag(2048L);
      }
      for (;;)
      {
        localDeclaration = localDeclaration.nextDecl();
        break;
        if (localDeclaration.getFlag(2048L))
        {
          str = "' declared static but never defined";
          break label39;
        }
        str = "' declared but never defined";
        break label39;
        label163:
        localDeclaration.setPrivate(true);
        break label119;
        label171:
        if (((paramModuleExp.getFlag(65536)) && (!localDeclaration.getFlag(2048L))) || (Compilation.moduleStatic < 0) || (paramModuleExp.getFlag(131072))) {
          localDeclaration.setFlag(4096L);
        }
      }
    }
  }
  
  public Syntax getCurrentSyntax()
  {
    return this.currentSyntax;
  }
  
  public final Environment getGlobalEnvironment()
  {
    return this.env;
  }
  
  public Declaration lookup(Object paramObject, int paramInt)
  {
    Declaration localDeclaration = this.lexical.lookup(paramObject, paramInt);
    if ((localDeclaration != null) && (getLanguage().hasNamespace(localDeclaration, paramInt))) {
      return localDeclaration;
    }
    return currentModule().lookup(paramObject, getLanguage(), paramInt);
  }
  
  public Declaration lookupGlobal(Object paramObject)
  {
    return lookupGlobal(paramObject, -1);
  }
  
  public Declaration lookupGlobal(Object paramObject, int paramInt)
  {
    ModuleExp localModuleExp = currentModule();
    Declaration localDeclaration2 = localModuleExp.lookup(paramObject, getLanguage(), paramInt);
    Declaration localDeclaration1 = localDeclaration2;
    if (localDeclaration2 == null)
    {
      localDeclaration1 = localModuleExp.getNoDefine(paramObject);
      localDeclaration1.setIndirectBinding(true);
    }
    return localDeclaration1;
  }
  
  public Declaration makeRenamedAlias(Declaration paramDeclaration, ScopeExp paramScopeExp)
  {
    if (paramScopeExp == null) {
      return paramDeclaration;
    }
    return makeRenamedAlias(paramDeclaration.getSymbol(), paramDeclaration, paramScopeExp);
  }
  
  public Declaration makeRenamedAlias(Object paramObject, Declaration paramDeclaration, ScopeExp paramScopeExp)
  {
    paramObject = new Declaration(paramObject);
    ((Declaration)paramObject).setAlias(true);
    ((Declaration)paramObject).setPrivate(true);
    ((Declaration)paramObject).context = paramScopeExp;
    paramDeclaration = new ReferenceExp(paramDeclaration);
    paramDeclaration.setDontDereference(true);
    ((Declaration)paramObject).noteValue(paramDeclaration);
    return (Declaration)paramObject;
  }
  
  public Object matchQuoted(Pair paramPair)
  {
    if ((matches(paramPair.getCar(), "quote")) && ((paramPair.getCdr() instanceof Pair)))
    {
      paramPair = (Pair)paramPair.getCdr();
      if (paramPair.getCdr() == LList.Empty) {
        return paramPair.getCar();
      }
    }
    return null;
  }
  
  public final boolean matches(Object paramObject, String paramString)
  {
    return matches(paramObject, null, paramString);
  }
  
  public boolean matches(Object paramObject, SyntaxForm paramSyntaxForm, Symbol paramSymbol)
  {
    if (paramSyntaxForm != null) {}
    paramSyntaxForm = (SyntaxForm)paramObject;
    if ((paramObject instanceof SyntaxForm)) {
      paramSyntaxForm = ((SyntaxForm)paramObject).getDatum();
    }
    paramObject = paramSyntaxForm;
    if ((paramSyntaxForm instanceof SimpleSymbol))
    {
      paramObject = paramSyntaxForm;
      if (!selfEvaluatingSymbol(paramSyntaxForm))
      {
        ReferenceExp localReferenceExp = getOriginalRef(this.lexical.lookup(paramSyntaxForm, -1));
        paramObject = paramSyntaxForm;
        if (localReferenceExp != null) {
          paramObject = localReferenceExp.getSymbol();
        }
      }
    }
    return paramObject == paramSymbol;
  }
  
  public boolean matches(Object paramObject, SyntaxForm paramSyntaxForm, String paramString)
  {
    if (paramSyntaxForm != null) {}
    paramSyntaxForm = (SyntaxForm)paramObject;
    if ((paramObject instanceof SyntaxForm)) {
      paramSyntaxForm = ((SyntaxForm)paramObject).getDatum();
    }
    paramObject = paramSyntaxForm;
    if ((paramSyntaxForm instanceof SimpleSymbol))
    {
      paramObject = paramSyntaxForm;
      if (!selfEvaluatingSymbol(paramSyntaxForm))
      {
        ReferenceExp localReferenceExp = getOriginalRef(this.lexical.lookup(paramSyntaxForm, -1));
        paramObject = paramSyntaxForm;
        if (localReferenceExp != null) {
          paramObject = localReferenceExp.getSymbol();
        }
      }
    }
    return ((paramObject instanceof SimpleSymbol)) && (((Symbol)paramObject).getLocalPart() == paramString);
  }
  
  public Symbol namespaceResolve(Expression paramExpression1, Expression paramExpression2)
  {
    return namespaceResolve(namespaceResolvePrefix(paramExpression1), paramExpression2);
  }
  
  public Symbol namespaceResolve(Namespace paramNamespace, Expression paramExpression)
  {
    if ((paramNamespace != null) && ((paramExpression instanceof QuoteExp))) {
      return paramNamespace.getSymbol(((QuoteExp)paramExpression).getValue().toString().intern());
    }
    return null;
  }
  
  public Object namespaceResolve(Object paramObject)
  {
    if ((!(paramObject instanceof SimpleSymbol)) && ((paramObject instanceof Pair)))
    {
      Object localObject1 = (Pair)paramObject;
      if ((safeCar(localObject1) == LispLanguage.lookup_sym) && ((((Pair)localObject1).getCdr() instanceof Pair)))
      {
        Object localObject2 = (Pair)((Pair)localObject1).getCdr();
        if ((((Pair)localObject2).getCdr() instanceof Pair))
        {
          localObject1 = rewrite(((Pair)localObject2).getCar());
          localObject2 = rewrite(((Pair)((Pair)localObject2).getCdr()).getCar());
          Symbol localSymbol = namespaceResolve((Expression)localObject1, (Expression)localObject2);
          if (localSymbol != null) {
            return localSymbol;
          }
          localObject1 = CompileNamedPart.combineName((Expression)localObject1, (Expression)localObject2);
          if (localObject1 != null) {
            return Namespace.EmptyNamespace.getSymbol((String)localObject1);
          }
        }
      }
    }
    return paramObject;
  }
  
  public Namespace namespaceResolvePrefix(Expression paramExpression)
  {
    if ((paramExpression instanceof ReferenceExp))
    {
      paramExpression = (ReferenceExp)paramExpression;
      Object localObject = paramExpression.getBinding();
      if ((localObject == null) || (((Declaration)localObject).getFlag(65536L)))
      {
        paramExpression = paramExpression.getSymbol();
        if ((paramExpression instanceof Symbol))
        {
          paramExpression = (Symbol)paramExpression;
          paramExpression = this.env.get(paramExpression, null);
        }
      }
      for (;;)
      {
        if (!(paramExpression instanceof Namespace)) {
          break label132;
        }
        localObject = (Namespace)paramExpression;
        String str = ((Namespace)localObject).getName();
        paramExpression = (Expression)localObject;
        if (str != null)
        {
          paramExpression = (Expression)localObject;
          if (str.startsWith("class:")) {
            paramExpression = null;
          }
        }
        return paramExpression;
        paramExpression = this.env.getSymbol(paramExpression.toString());
        break;
        if (((Declaration)localObject).isNamespaceDecl()) {
          paramExpression = ((Declaration)localObject).getConstantValue();
        } else {
          paramExpression = null;
        }
      }
    }
    label132:
    return null;
  }
  
  public void noteAccess(Object paramObject, ScopeExp paramScopeExp)
  {
    if (this.notedAccess == null) {
      this.notedAccess = new Vector();
    }
    this.notedAccess.addElement(paramObject);
    this.notedAccess.addElement(paramScopeExp);
  }
  
  public Expression parse(Object paramObject)
  {
    return rewrite(paramObject);
  }
  
  public Object popForms(int paramInt)
  {
    int j = this.formStack.size();
    if (j == paramInt) {
      return Values.empty;
    }
    Object localObject;
    if (j == paramInt + 1) {
      localObject = this.formStack.elementAt(paramInt);
    }
    for (;;)
    {
      this.formStack.setSize(paramInt);
      return localObject;
      localObject = new Values();
      int i = paramInt;
      while (i < j)
      {
        ((Values)localObject).writeObject(this.formStack.elementAt(i));
        i += 1;
      }
    }
  }
  
  public void popPositionOf(Object paramObject)
  {
    if (paramObject == null) {}
    do
    {
      return;
      setLine(paramObject);
      this.positionPair = ((PairWithPosition)paramObject);
    } while (this.positionPair.getCar() != Special.eof);
    this.positionPair = ((PairWithPosition)this.positionPair.getCdr());
  }
  
  public void popRenamedAlias(int paramInt)
  {
    for (;;)
    {
      int i = paramInt - 1;
      if (i < 0) {
        break;
      }
      ScopeExp localScopeExp = (ScopeExp)this.renamedAliasStack.pop();
      Object localObject = (Declaration)this.renamedAliasStack.pop();
      getOriginalRef((Declaration)localObject).getBinding().setSymbol(((Declaration)localObject).getSymbol());
      localScopeExp.remove((Declaration)localObject);
      localObject = this.renamedAliasStack.pop();
      paramInt = i;
      if (localObject != null)
      {
        localScopeExp.addDeclaration((Declaration)localObject);
        paramInt = i;
      }
    }
  }
  
  public void processAccesses()
  {
    if (this.notedAccess == null) {}
    ScopeExp localScopeExp1;
    do
    {
      return;
      int j = this.notedAccess.size();
      localScopeExp1 = this.current_scope;
      int i = 0;
      while (i < j)
      {
        Object localObject = this.notedAccess.elementAt(i);
        ScopeExp localScopeExp2 = (ScopeExp)this.notedAccess.elementAt(i + 1);
        if (this.current_scope != localScopeExp2) {
          setCurrentScope(localScopeExp2);
        }
        localObject = this.lexical.lookup(localObject, -1);
        if ((localObject != null) && (!((Declaration)localObject).getFlag(65536L)))
        {
          ((Declaration)localObject).getContext().currentLambda().capture((Declaration)localObject);
          ((Declaration)localObject).setCanRead(true);
          ((Declaration)localObject).setSimple(false);
          ((Declaration)localObject).setFlag(524288L);
        }
        i += 2;
      }
    } while (this.current_scope == localScopeExp1);
    setCurrentScope(localScopeExp1);
  }
  
  public Object pushPositionOf(Object paramObject)
  {
    Object localObject = paramObject;
    if ((paramObject instanceof SyntaxForm)) {
      localObject = ((SyntaxForm)paramObject).getDatum();
    }
    if (!(localObject instanceof PairWithPosition)) {
      return null;
    }
    PairWithPosition localPairWithPosition = (PairWithPosition)localObject;
    if ((this.positionPair == null) || (this.positionPair.getFileName() != getFileName()) || (this.positionPair.getLineNumber() != getLineNumber()) || (this.positionPair.getColumnNumber() != getColumnNumber())) {}
    for (paramObject = new PairWithPosition(this, Special.eof, this.positionPair);; paramObject = this.positionPair)
    {
      setLine(localObject);
      this.positionPair = localPairWithPosition;
      return paramObject;
    }
  }
  
  public void pushRenamedAlias(Declaration paramDeclaration)
  {
    Declaration localDeclaration = getOriginalRef(paramDeclaration).getBinding();
    ScopeExp localScopeExp = paramDeclaration.context;
    localDeclaration.setSymbol(null);
    localDeclaration = localScopeExp.lookup(localDeclaration.getSymbol());
    if (localDeclaration != null) {
      localScopeExp.remove(localDeclaration);
    }
    localScopeExp.addDeclaration(paramDeclaration);
    if (this.renamedAliasStack == null) {
      this.renamedAliasStack = new Stack();
    }
    this.renamedAliasStack.push(localDeclaration);
    this.renamedAliasStack.push(paramDeclaration);
    this.renamedAliasStack.push(localScopeExp);
  }
  
  public void resolveModule(ModuleExp paramModuleExp)
  {
    if (this.pendingImports == null) {}
    for (int i = 0;; i = this.pendingImports.size())
    {
      int j = 0;
      while (j < i)
      {
        localObject1 = this.pendingImports;
        int k = j + 1;
        localObject1 = (ModuleInfo)((Stack)localObject1).elementAt(j);
        Object localObject2 = this.pendingImports;
        j = k + 1;
        localObject2 = (ScopeExp)((Stack)localObject2).elementAt(k);
        Object localObject3 = this.pendingImports;
        int m = j + 1;
        localObject3 = (Expression)((Stack)localObject3).elementAt(j);
        Object localObject4 = this.pendingImports;
        k = m + 1;
        localObject4 = (Integer)((Stack)localObject4).elementAt(m);
        j = k;
        if (paramModuleExp == localObject2)
        {
          ReferenceExp localReferenceExp = new ReferenceExp((Object)null);
          localReferenceExp.setLine(this);
          setLine((Expression)localObject3);
          j = this.formStack.size();
          require.importDefinitions(null, (ModuleInfo)localObject1, null, this.formStack, (ScopeExp)localObject2, this);
          m = ((Integer)localObject4).intValue();
          if (((Integer)localObject4).intValue() != j)
          {
            int n = this.formStack.size();
            vectorReverse(this.formStack, m, j - m);
            vectorReverse(this.formStack, j, n - j);
            vectorReverse(this.formStack, m, n - m);
          }
          setLine(localReferenceExp);
          j = k;
        }
      }
    }
    this.pendingImports = null;
    processAccesses();
    setModule(paramModuleExp);
    Object localObject1 = Compilation.setSaveCurrent(this);
    try
    {
      rewriteInBody(popForms(this.firstForm));
      paramModuleExp.body = makeBody(this.firstForm, paramModuleExp);
      if (!this.immediate) {
        this.lexical.pop(paramModuleExp);
      }
      return;
    }
    finally
    {
      Compilation.restoreCurrent((Compilation)localObject1);
    }
  }
  
  public Expression rewrite(Object paramObject)
  {
    return rewrite(paramObject, false);
  }
  
  public Expression rewrite(Object paramObject, boolean paramBoolean)
  {
    if ((paramObject instanceof SyntaxForm))
    {
      Object localObject1 = (SyntaxForm)paramObject;
      paramObject = this.current_scope;
      try
      {
        setCurrentScope(((SyntaxForm)localObject1).getScope());
        localObject1 = rewrite(((SyntaxForm)localObject1).getDatum(), paramBoolean);
        return (Expression)localObject1;
      }
      finally
      {
        setCurrentScope((ScopeExp)paramObject);
      }
    }
    if ((paramObject instanceof PairWithPosition)) {
      return rewrite_with_position(paramObject, paramBoolean, (PairWithPosition)paramObject);
    }
    if ((paramObject instanceof Pair)) {
      return rewrite_pair((Pair)paramObject, paramBoolean);
    }
    Object localObject4;
    Object localObject9;
    Object localObject8;
    Object localObject3;
    Object localObject5;
    label165:
    Object localObject6;
    Object localObject7;
    Object localObject10;
    label291:
    boolean bool1;
    if (((paramObject instanceof Symbol)) && (!selfEvaluatingSymbol(paramObject)))
    {
      localObject4 = this.lexical.lookup(paramObject, paramBoolean);
      localObject9 = null;
      localObject8 = null;
      localObject3 = this.current_scope;
      int i;
      if (localObject4 == null)
      {
        i = -1;
        if ((!(paramObject instanceof Symbol)) || (!((Symbol)paramObject).hasEmptyNamespace())) {
          break label383;
        }
        localObject5 = paramObject.toString();
        if (localObject3 != null)
        {
          if ((!(localObject3 instanceof LambdaExp)) || (!(((ScopeExp)localObject3).outer instanceof ClassExp)) || (!((LambdaExp)localObject3).isClassMethod())) {
            break label492;
          }
          if (i < ScopeExp.nesting(((ScopeExp)localObject3).outer)) {
            break label392;
          }
        }
        if (localObject4 == null) {
          break label564;
        }
        localObject6 = ((Declaration)localObject4).getSymbol();
        localObject7 = null;
        localObject10 = getOriginalRef((Declaration)localObject4);
        paramObject = localObject6;
        localObject3 = localObject7;
        if (localObject10 != null)
        {
          localObject5 = ((ReferenceExp)localObject10).getBinding();
          localObject4 = localObject5;
          paramObject = localObject6;
          localObject3 = localObject7;
          if (localObject5 == null)
          {
            localObject3 = ((ReferenceExp)localObject10).getSymbol();
            paramObject = localObject3;
            localObject4 = localObject5;
          }
        }
        localObject6 = localObject3;
        localObject5 = paramObject;
        localObject10 = (Symbol)localObject6;
        bool1 = getLanguage().hasSeparateFunctionNamespace();
        if (localObject4 == null) {
          break label676;
        }
        if ((!(this.current_scope instanceof TemplateScope)) || (!((Declaration)localObject4).needsContext())) {
          break label573;
        }
        localObject3 = ((TemplateScope)this.current_scope).macroContext;
        paramObject = localObject4;
      }
      label383:
      label392:
      label461:
      label492:
      label508:
      label514:
      label564:
      label573:
      do
      {
        do
        {
          if (paramObject == null) {
            break label1057;
          }
          if ((paramBoolean) || (!(((Declaration)paramObject).getConstantValue() instanceof object))) {
            break label1013;
          }
          return QuoteExp.getInstance(Object.class);
          i = ScopeExp.nesting(((Declaration)localObject4).context);
          break;
          localObject5 = null;
          localObject3 = null;
          break label165;
          localObject6 = (LambdaExp)localObject3;
          localObject7 = (ClassExp)((ScopeExp)localObject3).outer;
          localObject10 = ((ClassExp)localObject7).getClassType();
          Member localMember = SlotGet.lookupMember((ObjectType)localObject10, (String)localObject5, (ClassType)localObject10);
          int j;
          if ((localObject6 == ((ClassExp)localObject7).clinitMethod) || ((localObject6 != ((ClassExp)localObject7).initMethod) && (((LambdaExp)localObject6).nameDecl.isStatic())))
          {
            j = 1;
            if (localMember != null) {
              break label514;
            }
            if (j == 0) {
              break label508;
            }
          }
          for (char c = 'S';; c = 'V')
          {
            if (ClassMethods.getMethods((ObjectType)localObject10, (String)localObject5, c, (ClassType)localObject10, this.language).length != 0) {
              break label514;
            }
            localObject3 = ((ScopeExp)localObject3).outer;
            break;
            j = 0;
            break label461;
          }
          if (j != 0) {}
          for (paramObject = new ReferenceExp(((ClassExp)((LambdaExp)localObject6).outer).nameDecl);; paramObject = new ThisExp(((LambdaExp)localObject6).firstDecl())) {
            return CompileNamedPart.makeExp((Expression)paramObject, QuoteExp.getInstance(localObject5));
          }
          localObject5 = paramObject;
          localObject6 = paramObject;
          break label291;
          localObject3 = localObject8;
          paramObject = localObject4;
        } while (!((Declaration)localObject4).getFlag(1048576L));
        localObject3 = localObject8;
        paramObject = localObject4;
      } while (((Declaration)localObject4).isStatic());
      for (paramObject = currentScope();; paramObject = ((ScopeExp)paramObject).outer)
      {
        if (paramObject == null) {
          throw new Error("internal error: missing " + localObject4);
        }
        if (((ScopeExp)paramObject).outer == ((Declaration)localObject4).context)
        {
          localObject3 = ((ScopeExp)paramObject).firstDecl();
          paramObject = localObject4;
          break;
        }
      }
      label676:
      localObject3 = this.env;
      if ((!paramBoolean) || (!bool1)) {
        break label1169;
      }
    }
    label1013:
    label1057:
    label1160:
    label1169:
    for (paramObject = EnvironmentKey.FUNCTION;; paramObject = null)
    {
      paramObject = ((Environment)localObject3).lookup((Symbol)localObject10, paramObject);
      localObject7 = paramObject;
      if (paramObject != null) {
        localObject7 = ((Location)paramObject).getBase();
      }
      if ((localObject7 instanceof FieldLocation))
      {
        localObject7 = (FieldLocation)localObject7;
        try
        {
          localObject4 = ((FieldLocation)localObject7).getDeclaration();
          if ((!inlineOk(null)) && (localObject4 != getNamedPartDecl) && ((!"objectSyntax".equals(((FieldLocation)localObject7).getMemberName())) || (!"kawa.standard.object".equals(((FieldLocation)localObject7).getDeclaringClass().getName())))) {
            break label1160;
          }
          if (this.immediate)
          {
            localObject3 = localObject8;
            paramObject = localObject4;
            if (((Declaration)localObject4).isStatic()) {
              break;
            }
            paramObject = new Declaration("(module-instance)");
          }
        }
        catch (Throwable paramObject)
        {
          boolean bool2;
          localObject3 = localObject9;
        }
      }
      try
      {
        ((Declaration)paramObject).setValue(new QuoteExp(((FieldLocation)localObject7).getInstance()));
        localObject3 = paramObject;
        paramObject = localObject4;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          localObject3 = paramObject;
          paramObject = localThrowable;
        }
      }
      if (((Declaration)localObject4).isStatic())
      {
        paramObject = ((FieldLocation)localObject7).getRClass();
        if (paramObject != null)
        {
          paramObject = ((Class)paramObject).getClassLoader();
          if (!(paramObject instanceof ZipLoader))
          {
            bool2 = paramObject instanceof ArrayClassLoader;
            localObject3 = localObject8;
            paramObject = localObject4;
            if (!bool2) {
              break;
            }
          }
        }
        paramObject = null;
        localObject3 = localObject8;
        break;
      }
      paramObject = null;
      localObject3 = localObject8;
      break;
      error('e', "exception loading '" + localObject6 + "' - " + ((Throwable)paramObject).getMessage());
      paramObject = null;
      break;
      if (localObject7 != null)
      {
        localObject3 = localObject8;
        paramObject = localObject4;
        if (((Location)localObject7).isBound()) {
          break;
        }
      }
      localObject6 = ((LispLanguage)getLanguage()).checkDefaultBinding((Symbol)localObject10, this);
      localObject3 = localObject8;
      paramObject = localObject4;
      if (localObject6 == null) {
        break;
      }
      return (Expression)localObject6;
      if ((((Declaration)paramObject).getContext() instanceof PatternScope)) {
        return syntaxError("reference to pattern variable " + ((Declaration)paramObject).getName() + " outside syntax template");
      }
      paramObject = new ReferenceExp(localObject5, (Declaration)paramObject);
      ((ReferenceExp)paramObject).setContextDecl((Declaration)localObject3);
      ((ReferenceExp)paramObject).setLine(this);
      if ((paramBoolean) && (bool1)) {
        ((ReferenceExp)paramObject).setFlag(8);
      }
      return (Expression)paramObject;
      if ((paramObject instanceof LangExp)) {
        return rewrite(((LangExp)paramObject).getLangValue(), paramBoolean);
      }
      if ((paramObject instanceof Expression)) {
        return (Expression)paramObject;
      }
      if (paramObject == Special.abstractSpecial) {
        return QuoteExp.abstractExp;
      }
      return QuoteExp.getInstance(Quote.quote(paramObject, this), this);
      paramObject = null;
      localObject3 = localObject8;
      break;
    }
  }
  
  public void rewriteInBody(Object paramObject)
  {
    SyntaxForm localSyntaxForm;
    if ((paramObject instanceof SyntaxForm))
    {
      localSyntaxForm = (SyntaxForm)paramObject;
      paramObject = this.current_scope;
    }
    for (;;)
    {
      try
      {
        setCurrentScope(localSyntaxForm.getScope());
        rewriteInBody(localSyntaxForm.getDatum());
        return;
      }
      finally
      {
        setCurrentScope((ScopeExp)paramObject);
      }
      if (!(paramObject instanceof Values)) {
        break;
      }
      paramObject = ((Values)paramObject).getValues();
      int i = 0;
      while (i < paramObject.length)
      {
        rewriteInBody(paramObject[i]);
        i += 1;
      }
    }
    this.formStack.add(rewrite(paramObject, false));
  }
  
  public Expression rewrite_body(Object paramObject)
  {
    Object localObject = pushPositionOf(paramObject);
    LetExp localLetExp = new LetExp(null);
    int k = this.formStack.size();
    localLetExp.outer = this.current_scope;
    this.current_scope = localLetExp;
    int j;
    try
    {
      paramObject = scanBody(paramObject, localLetExp, true);
      if (((LList)paramObject).isEmpty()) {
        this.formStack.add(syntaxError("body with no expressions"));
      }
      j = localLetExp.countNonDynamicDecls();
      if (j != 0)
      {
        Expression[] arrayOfExpression = new Expression[j];
        int i = j;
        for (;;)
        {
          i -= 1;
          if (i < 0) {
            break;
          }
          arrayOfExpression[i] = QuoteExp.undefined_exp;
        }
        localLetExp.inits = arrayOfExpression;
      }
    }
    finally
    {
      pop(localLetExp);
      popPositionOf(localObject);
    }
    rewriteBody((LList)paramObject);
    paramObject = makeBody(k, null);
    setLineOf((Expression)paramObject);
    if (j == 0)
    {
      pop(localLetExp);
      popPositionOf(localObject);
      return (Expression)paramObject;
    }
    localLetExp.body = ((Expression)paramObject);
    setLineOf(localLetExp);
    pop(localLetExp);
    popPositionOf(localObject);
    return localLetExp;
  }
  
  public final Expression rewrite_car(Pair paramPair, SyntaxForm paramSyntaxForm)
  {
    if ((paramSyntaxForm == null) || (paramSyntaxForm.getScope() == this.current_scope) || ((paramPair.getCar() instanceof SyntaxForm))) {
      return rewrite_car(paramPair, false);
    }
    ScopeExp localScopeExp = this.current_scope;
    try
    {
      setCurrentScope(paramSyntaxForm.getScope());
      paramPair = rewrite_car(paramPair, false);
      return paramPair;
    }
    finally
    {
      setCurrentScope(localScopeExp);
    }
  }
  
  public final Expression rewrite_car(Pair paramPair, boolean paramBoolean)
  {
    Object localObject = paramPair.getCar();
    if ((paramPair instanceof PairWithPosition)) {
      return rewrite_with_position(localObject, paramBoolean, (PairWithPosition)paramPair);
    }
    return rewrite(localObject, paramBoolean);
  }
  
  public Expression rewrite_pair(Pair paramPair, boolean paramBoolean)
  {
    Expression localExpression = rewrite_car(paramPair, true);
    Object localObject1;
    if ((localExpression instanceof QuoteExp))
    {
      localObject1 = localExpression.valueIfConstant();
      if ((localObject1 instanceof Syntax)) {
        return apply_rewrite((Syntax)localObject1, paramPair);
      }
    }
    Object localObject5;
    if ((localExpression instanceof ReferenceExp))
    {
      localObject4 = (ReferenceExp)localExpression;
      localObject3 = ((ReferenceExp)localObject4).getBinding();
      if (localObject3 != null) {
        break label255;
      }
      localObject1 = ((ReferenceExp)localObject4).getSymbol();
      if (((localObject1 instanceof Symbol)) && (!selfEvaluatingSymbol(localObject1)))
      {
        localObject1 = (Symbol)localObject1;
        ((Symbol)localObject1).getName();
        localObject5 = this.env;
        if (!getLanguage().hasSeparateFunctionNamespace()) {
          break label162;
        }
      }
      label162:
      for (localObject3 = EnvironmentKey.FUNCTION;; localObject3 = null)
      {
        localObject1 = ((Environment)localObject5).get((Symbol)localObject1, localObject3, null);
        if (!(localObject1 instanceof Syntax)) {
          break label168;
        }
        return apply_rewrite((Syntax)localObject1, paramPair);
        localObject1 = localObject1.toString();
        localObject1 = this.env.getSymbol((String)localObject1);
        break;
      }
      label168:
      if (!(localObject1 instanceof AutoloadProcedure)) {}
    }
    int j;
    label255:
    Object localObject2;
    do
    {
      try
      {
        ((AutoloadProcedure)localObject1).getLoaded();
        ((ReferenceExp)localObject4).setProcedureName(true);
        if (getLanguage().hasSeparateFunctionNamespace()) {
          localExpression.setFlag(8);
        }
        localObject1 = paramPair.getCdr();
        j = listLength(localObject1);
        if (j != -1) {
          break;
        }
        return syntaxError("circular list is not allowed after " + paramPair.getCar());
      }
      catch (RuntimeException localRuntimeException)
      {
        for (;;) {}
      }
      localObject2 = this.macroContext;
      localObject3 = check_if_Syntax((Declaration)localObject3);
    } while (localObject3 == null);
    paramPair = apply_rewrite((Syntax)localObject3, paramPair);
    this.macroContext = ((Declaration)localObject2);
    return paramPair;
    if (j < 0) {
      return syntaxError("dotted list [" + localObject2 + "] is not allowed after " + paramPair.getCar());
    }
    int k = 0;
    Object localObject3 = new Stack();
    Object localObject4 = this.current_scope;
    int i = 0;
    paramPair = (Pair)localObject2;
    if (i < j)
    {
      localObject2 = paramPair;
      if ((paramPair instanceof SyntaxForm))
      {
        paramPair = (SyntaxForm)paramPair;
        localObject2 = paramPair.getDatum();
        setCurrentScope(paramPair.getScope());
      }
      localObject5 = (Pair)localObject2;
      localObject2 = rewrite_car((Pair)localObject5, false);
      i += 1;
      paramPair = (Pair)localObject2;
      int m = k;
      if (k != 0)
      {
        if ((i & 0x1) != 0) {
          break label485;
        }
        paramPair = (Expression)((Stack)localObject3).pop();
        paramPair = new ApplyExp(MakeAttribute.makeAttribute, new Expression[] { paramPair, localObject2 });
        m = k;
      }
      for (;;)
      {
        ((Stack)localObject3).addElement(paramPair);
        paramPair = ((Pair)localObject5).getCdr();
        k = m;
        break;
        label485:
        if ((localObject2 instanceof QuoteExp))
        {
          paramPair = ((QuoteExp)localObject2).getValue();
          if (((paramPair instanceof Keyword)) && (i < j))
          {
            paramPair = new QuoteExp(((Keyword)paramPair).asSymbol());
            m = k;
            continue;
          }
        }
        m = 0;
        paramPair = (Pair)localObject2;
      }
    }
    paramPair = new Expression[((Stack)localObject3).size()];
    ((Stack)localObject3).copyInto(paramPair);
    if (localObject4 != this.current_scope) {
      setCurrentScope((ScopeExp)localObject4);
    }
    if (((localExpression instanceof ReferenceExp)) && (((ReferenceExp)localExpression).getBinding() == getNamedPartDecl))
    {
      localObject2 = paramPair[0];
      paramPair = paramPair[1];
      localObject3 = namespaceResolve((Expression)localObject2, paramPair);
      if (localObject3 != null) {
        return rewrite(localObject3, paramBoolean);
      }
      return CompileNamedPart.makeExp((Expression)localObject2, paramPair);
    }
    return ((LispLanguage)getLanguage()).makeApply(localExpression, paramPair);
  }
  
  public Expression rewrite_with_position(Object paramObject, boolean paramBoolean, PairWithPosition paramPairWithPosition)
  {
    localObject = pushPositionOf(paramPairWithPosition);
    if (paramObject == paramPairWithPosition) {}
    for (;;)
    {
      try
      {
        paramObject = rewrite_pair(paramPairWithPosition, paramBoolean);
        setLineOf((Expression)paramObject);
        return (Expression)paramObject;
      }
      finally
      {
        popPositionOf(localObject);
      }
      paramObject = rewrite(paramObject, paramBoolean);
    }
  }
  
  public LList scanBody(Object paramObject, ScopeExp paramScopeExp, boolean paramBoolean)
  {
    Object localObject1;
    Object localObject3;
    Object localObject2;
    if (paramBoolean)
    {
      localObject1 = LList.Empty;
      localObject3 = null;
      localObject2 = paramObject;
      paramObject = localObject1;
      localObject1 = localObject3;
    }
    for (;;)
    {
      localObject3 = paramObject;
      int i;
      if (localObject2 != LList.Empty)
      {
        if ((localObject2 instanceof SyntaxForm))
        {
          localObject3 = (SyntaxForm)localObject2;
          localObject2 = this.current_scope;
        }
      }
      else {
        try
        {
          setCurrentScope(((SyntaxForm)localObject3).getScope());
          i = this.formStack.size();
          paramScopeExp = scanBody(((SyntaxForm)localObject3).getDatum(), paramScopeExp, paramBoolean);
          if (paramBoolean)
          {
            localObject3 = (LList)SyntaxForms.fromDatumIfNeeded(paramScopeExp, (SyntaxForm)localObject3);
            if (localObject1 == null)
            {
              return (LList)localObject3;
              localObject1 = null;
              break;
            }
            ((Pair)localObject1).setCdrBackdoor(localObject3);
            return (LList)paramObject;
          }
          this.formStack.add(wrapSyntax(popForms(i), (SyntaxForm)localObject3));
          return null;
        }
        finally
        {
          setCurrentScope((ScopeExp)localObject2);
        }
      }
      if (!(localObject2 instanceof Pair)) {
        break label383;
      }
      Object localObject4 = (Pair)localObject2;
      int j = this.formStack.size();
      scanForm(((Pair)localObject4).getCar(), paramScopeExp);
      if (getState() == 2)
      {
        paramObject = localObject4;
        if (((Pair)localObject4).getCar() != this.pendingForm) {
          paramObject = makePair((Pair)localObject4, this.pendingForm, ((Pair)localObject4).getCdr());
        }
        this.pendingForm = new Pair(begin.begin, paramObject);
        return LList.Empty;
      }
      int k = this.formStack.size();
      localObject3 = localObject1;
      localObject2 = paramObject;
      if (paramBoolean)
      {
        i = j;
        if (i < k)
        {
          localObject2 = makePair((Pair)localObject4, this.formStack.elementAt(i), LList.Empty);
          if (localObject1 == null) {
            paramObject = localObject2;
          }
          for (;;)
          {
            localObject1 = localObject2;
            i += 1;
            break;
            ((Pair)localObject1).setCdrBackdoor(localObject2);
          }
        }
        this.formStack.setSize(j);
        localObject2 = paramObject;
        localObject3 = localObject1;
      }
      localObject4 = ((Pair)localObject4).getCdr();
      localObject1 = localObject3;
      paramObject = localObject2;
      localObject2 = localObject4;
    }
    label383:
    this.formStack.add(syntaxError("body is not a proper list"));
    return (LList)paramObject;
  }
  
  public void scanForm(Object paramObject, ScopeExp paramScopeExp)
  {
    Object localObject1;
    int i;
    if ((paramObject instanceof SyntaxForm))
    {
      localObject1 = (SyntaxForm)paramObject;
      paramObject = currentScope();
      try
      {
        setCurrentScope(((SyntaxForm)localObject1).getScope());
        i = this.formStack.size();
        scanForm(((SyntaxForm)localObject1).getDatum(), paramScopeExp);
        this.formStack.add(wrapSyntax(popForms(i), (SyntaxForm)localObject1));
        return;
      }
      finally
      {
        setCurrentScope((ScopeExp)paramObject);
      }
    }
    Object localObject3 = paramObject;
    Pair localPair;
    Declaration localDeclaration;
    Expression localExpression;
    ScopeExp localScopeExp;
    Object localObject4;
    if ((paramObject instanceof Values))
    {
      if (paramObject == Values.empty) {
        localObject3 = QuoteExp.voidExp;
      }
    }
    else
    {
      if (!(localObject3 instanceof Pair)) {
        break label644;
      }
      localPair = (Pair)localObject3;
      localDeclaration = this.macroContext;
      localExpression = null;
      localScopeExp = this.current_scope;
      localObject4 = pushPositionOf(localObject3);
      if (((localObject3 instanceof SourceLocator)) && (paramScopeExp.getLineNumber() < 0)) {
        paramScopeExp.setLocation((SourceLocator)localObject3);
      }
    }
    int j;
    for (;;)
    {
      try
      {
        paramObject = localPair.getCar();
        localObject1 = paramObject;
        if ((paramObject instanceof SyntaxForm))
        {
          paramObject = (SyntaxForm)localPair.getCar();
          setCurrentScope(((SyntaxForm)paramObject).getScope());
          localObject1 = ((SyntaxForm)paramObject).getDatum();
        }
        paramObject = localObject1;
        localObject2 = localExpression;
        Object localObject5;
        if ((localObject1 instanceof Pair))
        {
          localObject5 = (Pair)localObject1;
          paramObject = localObject1;
          localObject2 = localExpression;
          if (((Pair)localObject5).getCar() == LispLanguage.lookup_sym)
          {
            paramObject = localObject1;
            localObject2 = localExpression;
            if ((((Pair)localObject5).getCdr() instanceof Pair))
            {
              localObject5 = (Pair)((Pair)localObject5).getCdr();
              paramObject = localObject1;
              localObject2 = localExpression;
              if ((((Pair)localObject5).getCdr() instanceof Pair))
              {
                paramObject = rewrite(((Pair)localObject5).getCar());
                localObject1 = rewrite(((Pair)((Pair)localObject5).getCdr()).getCar());
                localObject2 = ((Expression)paramObject).valueIfConstant();
                localObject5 = ((Expression)localObject1).valueIfConstant();
                if (!(localObject2 instanceof Class)) {
                  continue;
                }
                boolean bool = localObject5 instanceof Symbol;
                if (!bool) {
                  continue;
                }
              }
            }
          }
        }
        try
        {
          localObject1 = GetNamedPart.getNamedPart(localObject2, (Symbol)localObject5);
          paramObject = localObject1;
          localObject2 = localExpression;
          if ((localObject1 instanceof Syntax))
          {
            localObject2 = (Syntax)localObject1;
            paramObject = localObject1;
          }
        }
        catch (Throwable paramObject)
        {
          paramObject = null;
          localObject2 = localExpression;
          continue;
        }
        if (((paramObject instanceof Symbol)) && (!selfEvaluatingSymbol(paramObject)))
        {
          localExpression = rewrite(paramObject, true);
          localObject1 = localObject2;
          if ((localExpression instanceof ReferenceExp))
          {
            localObject1 = ((ReferenceExp)localExpression).getBinding();
            if (localObject1 != null) {
              localObject1 = check_if_Syntax((Declaration)localObject1);
            }
          }
          else
          {
            if (localScopeExp != this.current_scope) {
              setCurrentScope(localScopeExp);
            }
            popPositionOf(localObject4);
            if (localObject1 == null) {
              break label644;
            }
            paramObject = getFileName();
            i = getLineNumber();
            j = getColumnNumber();
          }
        }
      }
      finally
      {
        Object localObject2;
        if (localScopeExp != this.current_scope) {
          setCurrentScope(localScopeExp);
        }
        popPositionOf(localObject4);
      }
      try
      {
        setLine(localPair);
        ((Syntax)localObject1).scanForm(localPair, paramScopeExp, this);
        this.macroContext = localDeclaration;
        setLine((String)paramObject, i, j);
        return;
      }
      finally
      {
        this.macroContext = localDeclaration;
        setLine((String)paramObject, i, j);
      }
      paramObject = ((Values)paramObject).getValues();
      i = 0;
      if (i < paramObject.length)
      {
        scanForm(paramObject[i], paramScopeExp);
        i += 1;
      }
      else
      {
        break;
        paramObject = namespaceResolve((Expression)paramObject, (Expression)localObject1);
        localObject2 = localExpression;
        continue;
        paramObject = resolve(paramObject, true);
        localObject1 = localObject2;
        if ((paramObject instanceof Syntax))
        {
          localObject1 = (Syntax)paramObject;
          continue;
          localObject1 = localObject2;
          if (paramObject == begin.begin) {
            localObject1 = (Syntax)paramObject;
          }
        }
      }
    }
    label644:
    this.formStack.add(localObject3);
  }
  
  public final boolean selfEvaluatingSymbol(Object paramObject)
  {
    return ((LispLanguage)getLanguage()).selfEvaluatingSymbol(paramObject);
  }
  
  public void setLineOf(Expression paramExpression)
  {
    if ((paramExpression instanceof QuoteExp)) {
      return;
    }
    paramExpression.setLocation(this);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\Translator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */