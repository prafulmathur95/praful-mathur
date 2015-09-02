package kawa.standard;

import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.functions.CompilationHelpers;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class set_b
  extends Syntax
{
  public static final set_b set = new set_b();
  
  static
  {
    set.setName("set!");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    Object localObject1 = paramPair.getCdr();
    paramPair = null;
    while ((localObject1 instanceof SyntaxForm))
    {
      paramPair = (SyntaxForm)localObject1;
      localObject1 = paramPair.getDatum();
    }
    if (!(localObject1 instanceof Pair)) {
      paramPair = paramTranslator.syntaxError("missing name");
    }
    Object localObject2;
    do
    {
      do
      {
        do
        {
          do
          {
            Expression localExpression;
            do
            {
              return paramPair;
              localObject1 = (Pair)localObject1;
              localObject2 = paramTranslator.rewrite_car((Pair)localObject1, paramPair);
              for (localObject1 = ((Pair)localObject1).getCdr(); (localObject1 instanceof SyntaxForm); localObject1 = paramPair.getDatum()) {
                paramPair = (SyntaxForm)localObject1;
              }
              if ((localObject1 instanceof Pair))
              {
                localObject1 = (Pair)localObject1;
                if (((Pair)localObject1).getCdr() == LList.Empty) {}
              }
              else
              {
                return paramTranslator.syntaxError("missing or extra arguments to set!");
              }
              localExpression = paramTranslator.rewrite_car((Pair)localObject1, paramPair);
              if ((localObject2 instanceof ApplyExp))
              {
                paramPair = (ApplyExp)localObject2;
                localObject1 = paramPair.getArgs();
                int k = localObject1.length;
                int m = 0;
                paramTranslator = paramPair.getFunction();
                paramPair = paramTranslator;
                int j = k;
                int i = m;
                if (localObject1.length > 0)
                {
                  paramPair = paramTranslator;
                  j = k;
                  i = m;
                  if ((paramTranslator instanceof ReferenceExp))
                  {
                    paramPair = paramTranslator;
                    j = k;
                    i = m;
                    if (((ReferenceExp)paramTranslator).getBinding() == Scheme.applyFieldDecl)
                    {
                      i = 1;
                      j = k - 1;
                      paramPair = localObject1[0];
                    }
                  }
                }
                paramTranslator = new Expression[j + 1];
                System.arraycopy(localObject1, i, paramTranslator, 0, j);
                paramTranslator[j] = localExpression;
                return new ApplyExp(new ApplyExp(new ReferenceExp(CompilationHelpers.setterDecl), new Expression[] { paramPair }), paramTranslator);
              }
              if (!(localObject2 instanceof ReferenceExp)) {
                return paramTranslator.syntaxError("first set! argument is not a variable name");
              }
              paramPair = (ReferenceExp)localObject2;
              localObject2 = paramPair.getBinding();
              localObject1 = new SetExp(paramPair.getSymbol(), localExpression);
              ((SetExp)localObject1).setContextDecl(paramPair.contextDecl());
              paramPair = (Pair)localObject1;
            } while (localObject2 == null);
            ((Declaration)localObject2).setCanWrite(true);
            ((SetExp)localObject1).setBinding((Declaration)localObject2);
            localObject2 = Declaration.followAliases((Declaration)localObject2);
            if (localObject2 != null) {
              ((Declaration)localObject2).noteValue(localExpression);
            }
            if (((Declaration)localObject2).getFlag(16384L)) {
              return paramTranslator.syntaxError("constant variable " + ((Declaration)localObject2).getName() + " is set!");
            }
            paramPair = (Pair)localObject1;
          } while (((Declaration)localObject2).context == paramTranslator.mainLambda);
          paramPair = (Pair)localObject1;
        } while (!(((Declaration)localObject2).context instanceof ModuleExp));
        paramPair = (Pair)localObject1;
      } while (((Declaration)localObject2).getFlag(268435456L));
      paramPair = (Pair)localObject1;
    } while (((Declaration)localObject2).context.getFlag(1048576));
    paramTranslator.error('w', (Declaration)localObject2, "imported variable ", " is set!");
    return (Expression)localObject1;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\set_b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */