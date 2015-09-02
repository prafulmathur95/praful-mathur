package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class syntax_error
  extends Syntax
{
  public static final syntax_error syntax_error = new syntax_error();
  
  static
  {
    syntax_error.setName("%syntax-error");
  }
  
  public static Expression error(Object paramObject, Object[] paramArrayOfObject)
  {
    Object localObject1 = new StringBuffer();
    int j = paramArrayOfObject.length;
    if ((paramArrayOfObject == null) || (j == 0)) {
      ((StringBuffer)localObject1).append("invalid syntax");
    }
    for (;;)
    {
      paramArrayOfObject = (Translator)Compilation.getCurrent();
      if (paramArrayOfObject != null) {
        break;
      }
      throw new RuntimeException(((StringBuffer)localObject1).toString());
      int i = 0;
      while (i < j)
      {
        ((StringBuffer)localObject1).append(paramArrayOfObject[i]);
        i += 1;
      }
    }
    paramObject = paramArrayOfObject.pushPositionOf(paramObject);
    try
    {
      localObject1 = paramArrayOfObject.syntaxError(((StringBuffer)localObject1).toString());
      return (Expression)localObject1;
    }
    finally
    {
      paramArrayOfObject.popPositionOf(paramObject);
    }
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while ((paramObject instanceof Pair))
    {
      paramObject = (Pair)paramObject;
      if (i > 0) {
        localStringBuffer.append(' ');
      }
      localStringBuffer.append(((Pair)paramObject).getCar());
      paramObject = ((Pair)paramObject).getCdr();
      i += 1;
    }
    if (paramObject != LList.Empty)
    {
      if (i > 0) {
        localStringBuffer.append(' ');
      }
      localStringBuffer.append(paramObject);
    }
    return paramTranslator.syntaxError(localStringBuffer.toString());
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\syntax_error.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */