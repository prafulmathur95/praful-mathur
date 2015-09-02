package kawa.standard;

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

public class define_variable
  extends Syntax
{
  public static final define_variable define_variable = new define_variable();
  
  static
  {
    define_variable.setName("define-variable");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    Object localObject3 = paramPair.getCdr();
    SetExp localSetExp = null;
    Object localObject2 = null;
    paramPair = (Pair)localObject2;
    Object localObject1 = localSetExp;
    Object localObject4;
    if ((localObject3 instanceof Pair))
    {
      localObject3 = (Pair)localObject3;
      localObject4 = ((Pair)localObject3).getCar();
      if (((localObject4 instanceof String)) || ((localObject4 instanceof Symbol))) {
        paramTranslator = paramTranslator.syntaxError(getName() + " is only allowed in a <body>");
      }
    }
    label179:
    do
    {
      return paramTranslator;
      paramPair = (Pair)localObject2;
      localObject1 = localSetExp;
      if ((localObject4 instanceof Declaration))
      {
        paramPair = (Declaration)((Pair)localObject3).getCar();
        localObject2 = ((Pair)localObject3).getCdr();
        if (!(localObject2 instanceof Pair)) {
          break label179;
        }
        localObject1 = (Pair)localObject2;
        if (((Pair)localObject1).getCdr() != LList.Empty) {
          break label179;
        }
        localObject1 = paramTranslator.rewrite(((Pair)localObject1).getCar());
      }
      while (paramPair == null)
      {
        return paramTranslator.syntaxError("invalid syntax for " + getName());
        localObject1 = localSetExp;
        if (localObject2 != LList.Empty)
        {
          paramPair = null;
          localObject1 = localSetExp;
        }
      }
      if (localObject1 == null) {
        return QuoteExp.voidExp;
      }
      localSetExp = new SetExp(paramPair, (Expression)localObject1);
      localSetExp.setDefining(true);
      localSetExp.setSetIfUnbound(true);
      paramTranslator = localSetExp;
    } while (paramPair == null);
    localSetExp.setBinding(paramPair);
    paramTranslator = (Translator)localObject1;
    if ((paramPair.context instanceof ModuleExp))
    {
      paramTranslator = (Translator)localObject1;
      if (paramPair.getCanWrite()) {
        paramTranslator = null;
      }
    }
    paramPair.noteValue(paramTranslator);
    return localSetExp;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    if (!(paramPair.getCdr() instanceof Pair)) {
      return super.scanForDefinitions(paramPair, paramVector, paramScopeExp, paramTranslator);
    }
    Pair localPair2 = (Pair)paramPair.getCdr();
    Object localObject = localPair2.getCar();
    Pair localPair1;
    if (!(localObject instanceof String))
    {
      localPair1 = paramPair;
      if (!(localObject instanceof Symbol)) {}
    }
    else
    {
      if (paramScopeExp.lookup(localObject) != null) {
        paramTranslator.error('e', "duplicate declaration for '" + localObject + "'");
      }
      paramScopeExp = paramScopeExp.addDeclaration(localObject);
      paramTranslator.push(paramScopeExp);
      paramScopeExp.setSimple(false);
      paramScopeExp.setPrivate(true);
      paramScopeExp.setFlag(268697600L);
      paramScopeExp.setCanRead(true);
      paramScopeExp.setCanWrite(true);
      paramScopeExp.setIndirectBinding(true);
      localPair1 = Translator.makePair(paramPair, this, Translator.makePair(localPair2, paramScopeExp, localPair2.getCdr()));
    }
    paramVector.addElement(localPair1);
    return true;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\define_variable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */