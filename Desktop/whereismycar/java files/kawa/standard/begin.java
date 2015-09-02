package kawa.standard;

import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class begin
  extends Syntax
{
  public static final begin begin = new begin();
  
  static
  {
    begin.setName("begin");
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator)
  {
    return paramTranslator.rewrite_body(paramObject);
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    paramScopeExp = paramTranslator.scanBody(paramPair.getCdr(), paramScopeExp, true);
    if (paramScopeExp != LList.Empty) {
      paramTranslator.formStack.add(Translator.makePair(paramPair, paramPair.getCar(), paramScopeExp));
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\begin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */