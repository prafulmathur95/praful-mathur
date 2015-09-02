package gnu.commonlisp.lang;

import gnu.expr.BeginExp;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class prog1
  extends Syntax
{
  public static final prog1 prog1 = new prog1("prog1", 1);
  public static final prog1 prog2 = new prog1("prog2", 2);
  int index;
  
  public prog1(String paramString, int paramInt)
  {
    this.index = paramInt;
    setName(paramString);
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator)
  {
    int j = LList.length(paramObject);
    if (j < this.index) {
      return paramTranslator.syntaxError("too few expressions in " + getName());
    }
    if (this.index == 2)
    {
      paramObject = (Pair)paramObject;
      return new BeginExp(paramTranslator.rewrite(((Pair)paramObject).getCar()), prog1.rewrite(((Pair)paramObject).getCdr(), paramTranslator));
    }
    Expression[] arrayOfExpression2 = new Expression[1];
    LetExp localLetExp = new LetExp(arrayOfExpression2);
    Expression[] arrayOfExpression1 = new Expression[j];
    paramObject = (Pair)paramObject;
    arrayOfExpression2[0] = paramTranslator.rewrite(((Pair)paramObject).getCar());
    paramObject = ((Pair)paramObject).getCdr();
    int i = 0;
    while (i < j - 1)
    {
      paramObject = (Pair)paramObject;
      arrayOfExpression1[i] = paramTranslator.rewrite(((Pair)paramObject).getCar());
      paramObject = ((Pair)paramObject).getCdr();
      i += 1;
    }
    arrayOfExpression1[(j - 1)] = new ReferenceExp(localLetExp.addDeclaration((Object)null));
    localLetExp.body = BeginExp.canonicalize(arrayOfExpression1);
    paramTranslator.mustCompileHere();
    return localLetExp;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\commonlisp\lang\prog1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */