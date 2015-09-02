package kawa.standard;

import gnu.expr.CatchClause;
import gnu.expr.Compilation;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.TryExp;
import gnu.lists.FVector;
import kawa.lang.Lambda;
import kawa.lang.Translator;

public class try_catch
{
  public static Expression rewrite(Object paramObject1, Object paramObject2)
  {
    Translator localTranslator = (Translator)Compilation.getCurrent();
    Expression localExpression = localTranslator.rewrite(paramObject1);
    Object localObject = null;
    paramObject1 = null;
    FVector localFVector = (FVector)paramObject2;
    int j = localFVector.size();
    int i = 0;
    paramObject2 = localObject;
    if (i < j)
    {
      localObject = SchemeCompilation.lambda.rewrite(localFVector.get(i), localTranslator);
      if ((localObject instanceof ErrorExp)) {
        return (Expression)localObject;
      }
      if (!(localObject instanceof LambdaExp)) {
        return localTranslator.syntaxError("internal error with try-catch");
      }
      localObject = new CatchClause((LambdaExp)localObject);
      if (paramObject2 == null) {
        paramObject1 = localObject;
      }
      for (;;)
      {
        paramObject2 = localObject;
        i += 1;
        break;
        ((CatchClause)paramObject2).setNext((CatchClause)localObject);
      }
    }
    if ((localExpression instanceof ErrorExp)) {
      return localExpression;
    }
    paramObject2 = new TryExp(localExpression, null);
    ((TryExp)paramObject2).setCatchClauses((CatchClause)paramObject1);
    return (Expression)paramObject2;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\try_catch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */