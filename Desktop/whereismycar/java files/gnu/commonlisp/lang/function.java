package gnu.commonlisp.lang;

import gnu.expr.Expression;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class function
  extends Syntax
{
  Syntax lambda;
  
  public function(Syntax paramSyntax)
  {
    this.lambda = paramSyntax;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    paramPair = paramPair.getCdr();
    if ((paramPair instanceof Pair))
    {
      paramPair = (Pair)paramPair;
      if (paramPair.getCdr() != LList.Empty) {
        return paramTranslator.syntaxError("too many forms after 'function'");
      }
      paramPair = paramPair.getCar();
      if (((paramPair instanceof String)) || ((paramPair instanceof Symbol)))
      {
        paramPair = new ReferenceExp(paramPair);
        paramPair.setProcedureName(true);
        paramPair.setFlag(8);
        return paramPair;
      }
      if ((paramPair instanceof Pair))
      {
        paramPair = (Pair)paramPair;
        Object localObject = paramPair.getCar();
        if ((localObject instanceof String))
        {
          if (!"lambda".equals(localObject)) {}
        }
        else {
          while (((localObject instanceof Symbol)) && ("lambda".equals(((Symbol)localObject).getName()))) {
            return this.lambda.rewriteForm(paramPair, paramTranslator);
          }
        }
      }
    }
    return paramTranslator.syntaxError("function must be followed by name or lambda expression");
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\commonlisp\lang\function.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */