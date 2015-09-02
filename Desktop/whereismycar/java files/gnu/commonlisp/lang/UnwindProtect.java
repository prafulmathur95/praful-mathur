package gnu.commonlisp.lang;

import gnu.expr.Expression;
import gnu.expr.TryExp;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class UnwindProtect
  extends Syntax
{
  public Expression rewrite(Object paramObject, Translator paramTranslator)
  {
    if (!(paramObject instanceof Pair)) {
      return paramTranslator.syntaxError("invalid syntax for unwind-protect");
    }
    paramObject = (Pair)paramObject;
    return new TryExp(paramTranslator.rewrite(((Pair)paramObject).getCar()), paramTranslator.rewrite_body(((Pair)paramObject).getCdr()));
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\commonlisp\lang\UnwindProtect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */