package kawa.standard;

import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class constant_fold
  extends Syntax
{
  public static final constant_fold constant_fold = new constant_fold();
  
  static
  {
    constant_fold.setName("constant-fold");
  }
  
  static Object checkConstant(Expression paramExpression, Translator paramTranslator)
  {
    paramTranslator = null;
    if ((paramExpression instanceof QuoteExp)) {
      paramTranslator = ((QuoteExp)paramExpression).getValue();
    }
    while (!(paramExpression instanceof ReferenceExp)) {
      return paramTranslator;
    }
    paramExpression = (ReferenceExp)paramExpression;
    paramTranslator = paramExpression.getBinding();
    if ((paramTranslator == null) || (paramTranslator.getFlag(65536L))) {
      return Environment.user().get(paramExpression.getName(), null);
    }
    return Declaration.followAliases(paramTranslator).getConstantValue();
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator)
  {
    paramObject = paramTranslator.rewrite(paramObject);
    if (!(paramObject instanceof ApplyExp)) {
      return (Expression)paramObject;
    }
    Object localObject2 = (ApplyExp)paramObject;
    Object localObject1 = checkConstant(((ApplyExp)localObject2).getFunction(), paramTranslator);
    if (!(localObject1 instanceof Procedure)) {
      return (Expression)paramObject;
    }
    localObject2 = ((ApplyExp)localObject2).getArgs();
    int i = localObject2.length;
    Object[] arrayOfObject = new Object[i];
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      Object localObject3 = checkConstant(localObject2[i], paramTranslator);
      if (localObject3 == null) {
        return (Expression)paramObject;
      }
      arrayOfObject[i] = localObject3;
    }
    try
    {
      paramObject = new QuoteExp(((Procedure)localObject1).applyN(arrayOfObject));
      return (Expression)paramObject;
    }
    catch (Throwable paramObject)
    {
      localObject1 = paramTranslator.syntaxError("caught exception in constant-fold:");
      paramTranslator.syntaxError(((Throwable)paramObject).toString());
    }
    return (Expression)localObject1;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\constant_fold.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */