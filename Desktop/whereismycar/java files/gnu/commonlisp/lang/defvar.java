package gnu.commonlisp.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class defvar
  extends Syntax
{
  boolean force;
  
  public defvar(boolean paramBoolean)
  {
    this.force = paramBoolean;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    Object localObject4 = paramPair.getCdr();
    Declaration localDeclaration = null;
    Pair localPair = null;
    Object localObject3 = null;
    Object localObject2 = localObject3;
    paramPair = localDeclaration;
    Object localObject1 = localPair;
    if ((localObject4 instanceof Pair))
    {
      localObject4 = (Pair)localObject4;
      localObject2 = localObject3;
      paramPair = localDeclaration;
      localObject1 = localPair;
      if ((((Pair)localObject4).getCar() instanceof Declaration))
      {
        localDeclaration = (Declaration)((Pair)localObject4).getCar();
        paramPair = localDeclaration.getSymbol();
        if (!(((Pair)localObject4).getCdr() instanceof Pair)) {
          break label156;
        }
        localPair = (Pair)((Pair)localObject4).getCdr();
        localObject1 = paramTranslator.rewrite(localPair.getCar());
        localObject2 = localDeclaration;
        if (localPair.getCdr() == LList.Empty) {}
      }
    }
    if (paramPair == null) {
      paramPair = paramTranslator.syntaxError("invalid syntax for " + getName());
    }
    label156:
    do
    {
      return paramPair;
      localObject2 = localDeclaration;
      localObject1 = localPair;
      if (((Pair)localObject4).getCdr() == LList.Empty) {
        break;
      }
      paramPair = null;
      localObject2 = localDeclaration;
      localObject1 = localPair;
      break;
      paramTranslator = (Translator)localObject1;
      if (localObject1 == null)
      {
        if (!this.force) {
          break label276;
        }
        paramTranslator = CommonLisp.nilExpr;
      }
      localObject1 = new SetExp(paramPair, paramTranslator);
      if (!this.force) {
        ((SetExp)localObject1).setSetIfUnbound(true);
      }
      ((SetExp)localObject1).setDefining(true);
      paramPair = (Pair)localObject1;
    } while (localObject2 == null);
    ((SetExp)localObject1).setBinding((Declaration)localObject2);
    paramPair = paramTranslator;
    if ((((Declaration)localObject2).context instanceof ModuleExp))
    {
      paramPair = paramTranslator;
      if (((Declaration)localObject2).getCanWrite()) {
        paramPair = null;
      }
    }
    ((Declaration)localObject2).noteValue(paramPair);
    return (Expression)localObject1;
    label276:
    return new QuoteExp(paramPair);
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    if (!(paramPair.getCdr() instanceof Pair)) {
      return super.scanForDefinitions(paramPair, paramVector, paramScopeExp, paramTranslator);
    }
    Pair localPair = (Pair)paramPair.getCdr();
    Object localObject2 = localPair.getCar();
    Object localObject1;
    if (!(localObject2 instanceof String))
    {
      localObject1 = paramPair;
      if (!(localObject2 instanceof Symbol)) {}
    }
    else
    {
      localObject1 = paramScopeExp.lookup(localObject2);
      if (localObject1 != null) {
        break label144;
      }
      paramTranslator = new Declaration(localObject2);
      paramTranslator.setFlag(268435456L);
      paramScopeExp.addDeclaration(paramTranslator);
    }
    for (;;)
    {
      paramPair = Translator.makePair(paramPair, this, Translator.makePair(localPair, paramTranslator, localPair.getCdr()));
      localObject1 = paramPair;
      if ((paramScopeExp instanceof ModuleExp))
      {
        paramTranslator.setCanRead(true);
        paramTranslator.setCanWrite(true);
        localObject1 = paramPair;
      }
      paramVector.addElement(localObject1);
      return true;
      label144:
      paramTranslator.error('w', "duplicate declaration for `" + localObject2 + "'");
      paramTranslator = (Translator)localObject1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\commonlisp\lang\defvar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */