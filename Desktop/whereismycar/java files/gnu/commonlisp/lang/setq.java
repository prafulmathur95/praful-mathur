package gnu.commonlisp.lang;

import gnu.expr.BeginExp;
import gnu.expr.Expression;
import gnu.expr.SetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class setq
  extends Syntax
{
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    Object localObject2 = paramPair.getCdr();
    Object localObject1;
    for (paramPair = null; localObject2 != LList.Empty; paramPair = (Pair)localObject1)
    {
      if (!(localObject2 instanceof Pair)) {
        localObject1 = paramTranslator.syntaxError("invalid syntax for setq");
      }
      label102:
      Object localObject3;
      do
      {
        return (Expression)localObject1;
        localObject2 = (Pair)localObject2;
        localObject1 = ((Pair)localObject2).getCar();
        if (((localObject1 instanceof Symbol)) || ((localObject1 instanceof String))) {}
        for (;;)
        {
          localObject2 = ((Pair)localObject2).getCdr();
          if ((localObject2 instanceof Pair)) {
            break label102;
          }
          return paramTranslator.syntaxError("wrong number of arguments for setq");
          if (localObject1 != CommonLisp.FALSE) {
            break;
          }
          localObject1 = "nil";
        }
        return paramTranslator.syntaxError("invalid variable name in setq");
        localObject2 = (Pair)localObject2;
        localObject3 = paramTranslator.rewrite(((Pair)localObject2).getCar());
        localObject2 = ((Pair)localObject2).getCdr();
        localObject3 = new SetExp(localObject1, (Expression)localObject3);
        ((SetExp)localObject3).setFlag(8);
        if (localObject2 != LList.Empty) {
          break;
        }
        ((SetExp)localObject3).setHasValue(true);
        localObject1 = localObject3;
      } while (paramPair == null);
      localObject1 = paramPair;
      if (paramPair == null) {
        localObject1 = new Vector(10);
      }
      ((Vector)localObject1).addElement(localObject3);
    }
    if (paramPair == null) {
      return CommonLisp.nilExpr;
    }
    paramTranslator = new Expression[paramPair.size()];
    paramPair.copyInto(paramTranslator);
    return new BeginExp(paramTranslator);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\commonlisp\lang\setq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */