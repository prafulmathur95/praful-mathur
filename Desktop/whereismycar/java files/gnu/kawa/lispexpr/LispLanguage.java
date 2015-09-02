package gnu.kawa.lispexpr;

import gnu.bytecode.Field;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.Sequence;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.InPort;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public abstract class LispLanguage
  extends Language
{
  public static final Symbol bracket_apply_sym;
  public static final Symbol bracket_list_sym;
  public static StaticFieldLocation getNamedPartLocation;
  public static final Symbol lookup_sym = Namespace.EmptyNamespace.getSymbol("$lookup$");
  public static final String quasiquote_sym = "quasiquote";
  public static final String quote_sym = "quote";
  public static final String unquote_sym = "unquote";
  public static final String unquotesplicing_sym = "unquote-splicing";
  public ReadTable defaultReadTable = createReadTable();
  
  static
  {
    bracket_list_sym = Namespace.EmptyNamespace.getSymbol("$bracket-list$");
    bracket_apply_sym = Namespace.EmptyNamespace.getSymbol("$bracket-apply$");
    getNamedPartLocation = new StaticFieldLocation("gnu.kawa.functions.GetNamedPart", "getNamedPart");
    getNamedPartLocation.setProcedure();
  }
  
  public static Symbol langSymbolToSymbol(Object paramObject)
  {
    return ((LispLanguage)Language.getDefaultLanguage()).fromLangSymbol(paramObject);
  }
  
  public Expression checkDefaultBinding(Symbol paramSymbol, Translator paramTranslator)
  {
    return null;
  }
  
  public abstract ReadTable createReadTable();
  
  public Declaration declFromField(ModuleExp paramModuleExp, Object paramObject, Field paramField)
  {
    paramModuleExp = super.declFromField(paramModuleExp, paramObject, paramField);
    if ((paramField.getModifiers() & 0x10) != 0) {}
    for (int i = 1;; i = 0)
    {
      if ((i != 0) && ((paramObject instanceof Syntax))) {
        paramModuleExp.setSyntax();
      }
      return paramModuleExp;
    }
  }
  
  protected void defSntxStFld(String paramString1, String paramString2)
  {
    defSntxStFld(paramString1, paramString2, Compilation.mangleNameIfNeeded(paramString1));
  }
  
  protected void defSntxStFld(String paramString1, String paramString2, String paramString3)
  {
    if (hasSeparateFunctionNamespace()) {}
    for (Object localObject = EnvironmentKey.FUNCTION;; localObject = null)
    {
      StaticFieldLocation.define(this.environ, this.environ.getSymbol(paramString1), localObject, paramString2, paramString3).setSyntax();
      return;
    }
  }
  
  protected Symbol fromLangSymbol(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      return getSymbol((String)paramObject);
    }
    return (Symbol)paramObject;
  }
  
  public Compilation getCompilation(Lexer paramLexer, SourceMessages paramSourceMessages, NameLookup paramNameLookup)
  {
    return new Translator(this, paramSourceMessages, paramNameLookup);
  }
  
  public Lexer getLexer(InPort paramInPort, SourceMessages paramSourceMessages)
  {
    return new LispReader(paramInPort, paramSourceMessages);
  }
  
  public Expression makeApply(Expression paramExpression, Expression[] paramArrayOfExpression)
  {
    return new ApplyExp(paramExpression, paramArrayOfExpression);
  }
  
  public Expression makeBody(Expression[] paramArrayOfExpression)
  {
    return new BeginExp(paramArrayOfExpression);
  }
  
  public boolean parse(Compilation paramCompilation, int paramInt)
    throws IOException, SyntaxException
  {
    Translator localTranslator = (Translator)paramCompilation;
    Lexer localLexer = localTranslator.lexer;
    ModuleExp localModuleExp = localTranslator.mainLambda;
    new Values();
    LispReader localLispReader = (LispReader)localLexer;
    paramCompilation = Compilation.setSaveCurrent(localTranslator);
    int i;
    do
    {
      try
      {
        if (localTranslator.pendingForm != null)
        {
          localTranslator.scanForm(localTranslator.pendingForm, localModuleExp);
          localTranslator.pendingForm = null;
        }
        do
        {
          Object localObject2 = localLispReader.readCommand();
          Object localObject3 = Sequence.eofValue;
          if (localObject2 == localObject3)
          {
            if ((paramInt & 0x4) != 0) {
              return false;
            }
          }
          else
          {
            localTranslator.scanForm(localObject2, localModuleExp);
            if ((paramInt & 0x4) == 0) {
              continue;
            }
            if (!localTranslator.getMessages().seenErrors()) {}
          }
          for (;;)
          {
            i = localLispReader.peek();
            if ((i < 0) || (i == 13) || (i == 10))
            {
              if (localLexer.peek() == 41) {
                localLexer.fatal("An unexpected close paren was read.");
              }
              localTranslator.finishModule(localModuleExp);
              if ((paramInt & 0x8) == 0) {
                localTranslator.firstForm = 0;
              }
              localTranslator.setState(4);
              return true;
            }
            localLispReader.skip();
          }
        } while ((paramInt & 0x8) == 0);
      }
      finally
      {
        Compilation.restoreCurrent(paramCompilation);
      }
      i = ((Translator)localObject1).getState();
    } while (i < 2);
    Compilation.restoreCurrent(paramCompilation);
    return true;
  }
  
  public void resolve(Compilation paramCompilation)
  {
    paramCompilation = (Translator)paramCompilation;
    paramCompilation.resolveModule(paramCompilation.getModule());
  }
  
  public boolean selfEvaluatingSymbol(Object paramObject)
  {
    return paramObject instanceof Keyword;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\LispLanguage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */