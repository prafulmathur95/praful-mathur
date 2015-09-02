package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import kawa.lang.PatternScope;
import kawa.lang.Quote;
import kawa.lang.SyntaxTemplate;
import kawa.lang.Translator;

public class syntax
  extends Quote
{
  static final Method makeTemplateScopeMethod = typeTemplateScope.getDeclaredMethod("make", 0);
  public static final syntax quasiSyntax;
  public static final syntax syntax = new syntax("syntax", false);
  static final ClassType typeTemplateScope;
  
  static
  {
    quasiSyntax = new syntax("quasisyntax", true);
    typeTemplateScope = ClassType.make("kawa.lang.TemplateScope");
  }
  
  public syntax(String paramString, boolean paramBoolean)
  {
    super(paramString, paramBoolean);
  }
  
  static Expression makeSyntax(Object paramObject, Translator paramTranslator)
  {
    SyntaxTemplate localSyntaxTemplate = new SyntaxTemplate(paramObject, null, paramTranslator);
    QuoteExp localQuoteExp = QuoteExp.nullExp;
    PatternScope localPatternScope = paramTranslator.patternScope;
    paramObject = localQuoteExp;
    if (localPatternScope != null)
    {
      paramObject = localQuoteExp;
      if (localPatternScope.matchArray != null) {
        paramObject = new ReferenceExp(localPatternScope.matchArray);
      }
    }
    localQuoteExp = new QuoteExp(localSyntaxTemplate);
    paramTranslator = new ReferenceExp(paramTranslator.templateScopeDecl);
    return new ApplyExp(ClassType.make("kawa.lang.SyntaxTemplate").getDeclaredMethod("execute", 2), new Expression[] { localQuoteExp, paramObject, paramTranslator });
  }
  
  protected boolean expandColonForms()
  {
    return false;
  }
  
  protected Expression leaf(Object paramObject, Translator paramTranslator)
  {
    return makeSyntax(paramObject, paramTranslator);
  }
  
  /* Error */
  public Expression rewriteForm(gnu.lists.Pair paramPair, Translator paramTranslator)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 103	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   4: instanceof 99
    //   7: ifeq +24 -> 31
    //   10: aload_1
    //   11: invokevirtual 103	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   14: checkcast 99	gnu/lists/Pair
    //   17: checkcast 99	gnu/lists/Pair
    //   20: astore_1
    //   21: aload_1
    //   22: invokevirtual 103	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   25: getstatic 109	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   28: if_acmpeq +10 -> 38
    //   31: aload_2
    //   32: ldc 111
    //   34: invokevirtual 115	kawa/lang/Translator:syntaxError	(Ljava/lang/String;)Lgnu/expr/Expression;
    //   37: areturn
    //   38: aload_2
    //   39: getfield 79	kawa/lang/Translator:templateScopeDecl	Lgnu/expr/Declaration;
    //   42: astore 4
    //   44: aload 4
    //   46: ifnonnull +44 -> 90
    //   49: aload_2
    //   50: invokevirtual 118	kawa/lang/Translator:letStart	()V
    //   53: new 81	gnu/expr/ApplyExp
    //   56: dup
    //   57: getstatic 41	kawa/standard/syntax:makeTemplateScopeMethod	Lgnu/bytecode/Method;
    //   60: getstatic 122	gnu/expr/Expression:noExpressions	[Lgnu/expr/Expression;
    //   63: invokespecial 90	gnu/expr/ApplyExp:<init>	(Lgnu/bytecode/Method;[Lgnu/expr/Expression;)V
    //   66: astore_3
    //   67: aload_2
    //   68: aconst_null
    //   69: getstatic 34	kawa/standard/syntax:typeTemplateScope	Lgnu/bytecode/ClassType;
    //   72: aload_3
    //   73: invokevirtual 126	kawa/lang/Translator:letVariable	(Ljava/lang/Object;Lgnu/bytecode/Type;Lgnu/expr/Expression;)Lgnu/expr/Declaration;
    //   76: astore_3
    //   77: aload_3
    //   78: invokevirtual 131	gnu/expr/Declaration:setCanRead	()V
    //   81: aload_2
    //   82: aload_3
    //   83: putfield 79	kawa/lang/Translator:templateScopeDecl	Lgnu/expr/Declaration;
    //   86: aload_2
    //   87: invokevirtual 134	kawa/lang/Translator:letEnter	()V
    //   90: aload_1
    //   91: invokevirtual 137	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   94: astore_1
    //   95: aload_0
    //   96: getfield 141	kawa/standard/syntax:isQuasi	Z
    //   99: ifeq +41 -> 140
    //   102: iconst_1
    //   103: istore 5
    //   105: aload_0
    //   106: aload_0
    //   107: aload_1
    //   108: iload 5
    //   110: aload_2
    //   111: invokevirtual 145	kawa/standard/syntax:expand	(Ljava/lang/Object;ILkawa/lang/Translator;)Ljava/lang/Object;
    //   114: aload_2
    //   115: invokevirtual 148	kawa/standard/syntax:coerceExpression	(Ljava/lang/Object;Lkawa/lang/Translator;)Lgnu/expr/Expression;
    //   118: astore_3
    //   119: aload_3
    //   120: astore_1
    //   121: aload 4
    //   123: ifnonnull +9 -> 132
    //   126: aload_2
    //   127: aload_3
    //   128: invokevirtual 152	kawa/lang/Translator:letDone	(Lgnu/expr/Expression;)Lgnu/expr/LetExp;
    //   131: astore_1
    //   132: aload_2
    //   133: aload 4
    //   135: putfield 79	kawa/lang/Translator:templateScopeDecl	Lgnu/expr/Declaration;
    //   138: aload_1
    //   139: areturn
    //   140: iconst_m1
    //   141: istore 5
    //   143: goto -38 -> 105
    //   146: astore_1
    //   147: aload_2
    //   148: aload 4
    //   150: putfield 79	kawa/lang/Translator:templateScopeDecl	Lgnu/expr/Declaration;
    //   153: aload_1
    //   154: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	155	0	this	syntax
    //   0	155	1	paramPair	gnu.lists.Pair
    //   0	155	2	paramTranslator	Translator
    //   66	62	3	localObject	Object
    //   42	107	4	localDeclaration	gnu.expr.Declaration
    //   103	39	5	i	int
    // Exception table:
    //   from	to	target	type
    //   90	102	146	finally
    //   105	119	146	finally
    //   126	132	146	finally
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\syntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */