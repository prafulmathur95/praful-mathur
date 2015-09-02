package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.LangExp;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define
  extends Syntax
{
  public static final define defineRaw = new define(SchemeCompilation.lambda);
  Lambda lambda;
  
  public define(Lambda paramLambda)
  {
    this.lambda = paramLambda;
  }
  
  String getName(int paramInt)
  {
    if ((paramInt & 0x4) != 0) {
      return "define-private";
    }
    if ((paramInt & 0x8) != 0) {
      return "define-constant";
    }
    return "define";
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    Object localObject2 = (Pair)paramPair.getCdr();
    paramPair = (Pair)((Pair)localObject2).getCdr();
    Object localObject1 = (Pair)((Pair)paramPair.getCdr()).getCdr();
    localObject2 = Translator.stripSyntax(((Pair)localObject2).getCar());
    int j = ((Number)Translator.stripSyntax(paramPair.getCar())).intValue();
    int i;
    if ((j & 0x4) != 0)
    {
      i = 1;
      if ((localObject2 instanceof Declaration)) {
        break label112;
      }
      paramPair = paramTranslator.syntaxError(getName(j) + " is only allowed in a <body>");
    }
    label112:
    do
    {
      do
      {
        return paramPair;
        i = 0;
        break;
        localObject2 = (Declaration)localObject2;
        if (((Declaration)localObject2).getFlag(8192L))
        {
          paramPair = ((Declaration)localObject2).getTypeExp();
          if ((paramPair instanceof LangExp)) {
            ((Declaration)localObject2).setType(paramTranslator.exp2Type((Pair)((LangExp)paramPair).getLangValue()));
          }
        }
        if ((j & 0x2) == 0) {
          break label274;
        }
        paramPair = (LambdaExp)((Declaration)localObject2).getValue();
        localObject1 = ((Pair)localObject1).getCdr();
        this.lambda.rewriteBody(paramPair, localObject1, paramTranslator);
        localObject1 = paramPair;
        if (!Compilation.inlineOk)
        {
          ((Declaration)localObject2).noteValue(null);
          localObject1 = paramPair;
        }
        localObject1 = new SetExp((Declaration)localObject2, (Expression)localObject1);
        ((SetExp)localObject1).setDefining(true);
        paramPair = (Pair)localObject1;
      } while (i == 0);
      paramPair = (Pair)localObject1;
    } while ((paramTranslator.currentScope() instanceof ModuleExp));
    paramTranslator.error('w', "define-private not at top level " + paramTranslator.currentScope());
    return (Expression)localObject1;
    label274:
    paramPair = paramTranslator.rewrite(((Pair)localObject1).getCar());
    if (((((Declaration)localObject2).context instanceof ModuleExp)) && (i == 0) && (((Declaration)localObject2).getCanWrite())) {}
    for (localObject1 = null;; localObject1 = paramPair)
    {
      ((Declaration)localObject2).noteValue((Expression)localObject1);
      localObject1 = paramPair;
      break;
    }
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    Pair localPair2 = (Pair)paramPair.getCdr();
    Pair localPair1 = (Pair)localPair2.getCdr();
    Pair localPair3 = (Pair)localPair1.getCdr();
    Object localObject4 = (Pair)localPair3.getCdr();
    Object localObject1 = null;
    for (Object localObject2 = localPair2.getCar(); (localObject2 instanceof SyntaxForm); localObject2 = ((SyntaxForm)localObject1).getDatum()) {
      localObject1 = (SyntaxForm)localObject2;
    }
    int k = ((Number)Translator.stripSyntax(localPair1.getCar())).intValue();
    int i;
    if ((k & 0x4) != 0)
    {
      i = 1;
      if ((k & 0x8) == 0) {
        break label498;
      }
    }
    label498:
    for (int j = 1;; j = 0)
    {
      paramTranslator.currentScope();
      Object localObject3 = paramTranslator.namespaceResolve(localObject2);
      localObject2 = localObject3;
      if (!(localObject3 instanceof Symbol))
      {
        paramTranslator.error('e', "'" + localObject3 + "' is not a valid identifier");
        localObject2 = null;
      }
      localObject3 = paramTranslator.pushPositionOf(localPair2);
      localObject2 = paramTranslator.define(localObject2, (SyntaxForm)localObject1, paramScopeExp);
      paramTranslator.popPositionOf(localObject3);
      Object localObject5 = ((Declaration)localObject2).getSymbol();
      if (i != 0)
      {
        ((Declaration)localObject2).setFlag(16777216L);
        ((Declaration)localObject2).setPrivate(true);
      }
      if (j != 0) {
        ((Declaration)localObject2).setFlag(16384L);
      }
      ((Declaration)localObject2).setFlag(262144L);
      localObject1 = localPair1;
      if ((k & 0x2) != 0)
      {
        localObject3 = new LambdaExp();
        ((LambdaExp)localObject3).setSymbol(localObject5);
        if (Compilation.inlineOk)
        {
          ((Declaration)localObject2).setProcedureDecl(true);
          ((Declaration)localObject2).setType(Compilation.typeProcedure);
          ((LambdaExp)localObject3).nameDecl = ((Declaration)localObject2);
        }
        localObject5 = ((Pair)localObject4).getCar();
        localObject4 = ((Pair)localObject4).getCdr();
        Translator.setLine((Expression)localObject3, localPair2);
        this.lambda.rewriteFormals((LambdaExp)localObject3, localObject5, paramTranslator, null);
        Object localObject6 = this.lambda.rewriteAttrs((LambdaExp)localObject3, localObject4, paramTranslator);
        localObject1 = localPair1;
        if (localObject6 != localObject4) {
          localObject1 = new Pair(localPair1.getCar(), new Pair(localPair3.getCar(), new Pair(localObject5, localObject6)));
        }
        ((Declaration)localObject2).noteValue((Expression)localObject3);
      }
      if (((paramScopeExp instanceof ModuleExp)) && (i == 0) && ((!Compilation.inlineOk) || (paramTranslator.sharedModuleDefs()))) {
        ((Declaration)localObject2).setCanWrite(true);
      }
      if ((k & 0x1) != 0)
      {
        ((Declaration)localObject2).setTypeExp(new LangExp(localPair3));
        ((Declaration)localObject2).setFlag(8192L);
      }
      paramPair = Translator.makePair(paramPair, this, Translator.makePair(localPair2, localObject2, localObject1));
      Translator.setLine((Declaration)localObject2, localPair2);
      paramTranslator.formStack.addElement(paramPair);
      return;
      i = 0;
      break;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\define.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */