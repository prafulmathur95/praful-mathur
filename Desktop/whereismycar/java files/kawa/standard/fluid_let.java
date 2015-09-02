package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.FluidLetExp;
import gnu.expr.NameLookup;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class fluid_let
  extends Syntax
{
  public static final fluid_let fluid_let = new fluid_let();
  Expression defaultInit;
  boolean star;
  
  static
  {
    fluid_let.setName("fluid-set");
  }
  
  public fluid_let()
  {
    this.star = false;
  }
  
  public fluid_let(boolean paramBoolean, Expression paramExpression)
  {
    this.star = paramBoolean;
    this.defaultInit = paramExpression;
  }
  
  public Expression rewrite(Object paramObject1, Object paramObject2, Translator paramTranslator)
  {
    int i;
    if (this.star) {
      i = 1;
    }
    FluidLetExp localFluidLetExp;
    for (;;)
    {
      Expression[] arrayOfExpression = new Expression[i];
      localFluidLetExp = new FluidLetExp(arrayOfExpression);
      int j = 0;
      label31:
      if (j < i)
      {
        Pair localPair = (Pair)paramObject1;
        Object localObject3 = paramTranslator.pushPositionOf(localPair);
        try
        {
          Object localObject1 = localPair.getCar();
          if (((localObject1 instanceof String)) || ((localObject1 instanceof Symbol))) {
            paramObject1 = this.defaultInit;
          }
          for (;;)
          {
            Declaration localDeclaration = localFluidLetExp.addDeclaration(localObject1);
            Object localObject2 = paramTranslator.lexical.lookup(localObject1, false);
            if (localObject2 != null)
            {
              ((Declaration)localObject2).maybeIndirectBinding(paramTranslator);
              localDeclaration.base = ((Declaration)localObject2);
              ((Declaration)localObject2).setFluid(true);
              ((Declaration)localObject2).setCanWrite(true);
            }
            localDeclaration.setCanWrite(true);
            localDeclaration.setFluid(true);
            localDeclaration.setIndirectBinding(true);
            localObject2 = paramObject1;
            if (paramObject1 == null) {
              localObject2 = new ReferenceExp(localObject1);
            }
            arrayOfExpression[j] = localObject2;
            localDeclaration.noteValue(null);
            paramObject1 = localPair.getCdr();
            paramTranslator.popPositionOf(localObject3);
            j += 1;
            break label31;
            i = LList.length(paramObject1);
            break;
            if (!(localObject1 instanceof Pair)) {
              break label409;
            }
            localObject2 = (Pair)localObject1;
            if ((!(((Pair)localObject2).getCar() instanceof String)) && (!(((Pair)localObject2).getCar() instanceof Symbol)) && (!(((Pair)localObject2).getCar() instanceof SyntaxForm))) {
              break label409;
            }
            localObject1 = ((Pair)localObject2).getCar();
            paramObject1 = localObject1;
            if ((localObject1 instanceof SyntaxForm)) {
              paramObject1 = ((SyntaxForm)localObject1).getDatum();
            }
            if (((Pair)localObject2).getCdr() == LList.Empty)
            {
              localObject2 = this.defaultInit;
              localObject1 = paramObject1;
              paramObject1 = localObject2;
            }
            else
            {
              if ((((Pair)localObject2).getCdr() instanceof Pair))
              {
                localObject1 = (Pair)((Pair)localObject2).getCdr();
                if (((Pair)localObject1).getCdr() == LList.Empty) {}
              }
              else
              {
                paramObject1 = paramTranslator.syntaxError("bad syntax for value of " + paramObject1 + " in " + getName());
                return (Expression)paramObject1;
              }
              localObject2 = paramTranslator.rewrite(((Pair)localObject1).getCar());
              localObject1 = paramObject1;
              paramObject1 = localObject2;
            }
          }
          label409:
          paramObject1 = paramTranslator.syntaxError("invalid " + getName() + " syntax");
          return (Expression)paramObject1;
        }
        finally
        {
          paramTranslator.popPositionOf(localObject3);
        }
      }
    }
    paramTranslator.push(localFluidLetExp);
    if ((this.star) && (paramObject1 != LList.Empty)) {}
    for (localFluidLetExp.body = rewrite(paramObject1, paramObject2, paramTranslator);; localFluidLetExp.body = paramTranslator.rewrite_body(paramObject2))
    {
      paramTranslator.pop(localFluidLetExp);
      return localFluidLetExp;
    }
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator)
  {
    if (!(paramObject instanceof Pair)) {
      return paramTranslator.syntaxError("missing let arguments");
    }
    paramObject = (Pair)paramObject;
    return rewrite(((Pair)paramObject).getCar(), ((Pair)paramObject).getCdr(), paramTranslator);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\fluid_let.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */