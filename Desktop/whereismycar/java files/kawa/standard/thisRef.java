package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.ThisExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class thisRef
  extends Syntax
{
  public static final thisRef thisSyntax = new thisRef();
  
  static
  {
    thisSyntax.setName("this");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    if (paramPair.getCdr() == LList.Empty)
    {
      LambdaExp localLambdaExp = paramTranslator.curMethodLambda;
      Object localObject;
      if (localLambdaExp == null)
      {
        paramPair = null;
        if (paramPair != null)
        {
          localObject = paramPair;
          if (paramPair.isThisParameter()) {}
        }
        else
        {
          localObject = null;
          if ((localLambdaExp != null) && (localLambdaExp.nameDecl != null)) {
            break label77;
          }
          paramTranslator.error('e', "use of 'this' not in a named method");
        }
      }
      for (;;)
      {
        return new ThisExp((Declaration)localObject);
        paramPair = localLambdaExp.firstDecl();
        break;
        label77:
        if (localLambdaExp.nameDecl.isStatic())
        {
          paramTranslator.error('e', "use of 'this' in a static method");
        }
        else
        {
          localObject = new Declaration(ThisExp.THIS_NAME);
          localLambdaExp.add(null, (Declaration)localObject);
          localLambdaExp.nameDecl.setFlag(4096L);
        }
      }
    }
    return paramTranslator.syntaxError("this with parameter not implemented");
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\thisRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */