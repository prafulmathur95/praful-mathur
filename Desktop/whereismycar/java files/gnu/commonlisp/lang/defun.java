package gnu.commonlisp.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class defun
  extends Syntax
{
  Lambda lambdaSyntax;
  
  public defun(Lambda paramLambda)
  {
    this.lambdaSyntax = paramLambda;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    Object localObject1 = paramPair.getCdr();
    paramPair = null;
    Declaration localDeclaration = null;
    if ((localObject1 instanceof Pair))
    {
      localObject1 = (Pair)localObject1;
      Object localObject2 = ((Pair)localObject1).getCar();
      if (((localObject2 instanceof Symbol)) || ((localObject2 instanceof String))) {
        paramPair = localObject2.toString();
      }
      while ((paramPair != null) && ((((Pair)localObject1).getCdr() instanceof Pair)))
      {
        localObject2 = (Pair)((Pair)localObject1).getCdr();
        localObject1 = new LambdaExp();
        this.lambdaSyntax.rewrite((LambdaExp)localObject1, ((Pair)localObject2).getCar(), ((Pair)localObject2).getCdr(), paramTranslator, null);
        ((LambdaExp)localObject1).setSymbol(paramPair);
        if ((localObject2 instanceof PairWithPosition)) {
          ((LambdaExp)localObject1).setLocation((PairWithPosition)localObject2);
        }
        paramTranslator = (Translator)localObject1;
        localObject1 = new SetExp(paramPair, paramTranslator);
        ((SetExp)localObject1).setDefining(true);
        ((SetExp)localObject1).setFuncDef(true);
        if (localDeclaration != null)
        {
          ((SetExp)localObject1).setBinding(localDeclaration);
          paramPair = paramTranslator;
          if ((localDeclaration.context instanceof ModuleExp))
          {
            paramPair = paramTranslator;
            if (localDeclaration.getCanWrite()) {
              paramPair = null;
            }
          }
          localDeclaration.noteValue(paramPair);
        }
        return (Expression)localObject1;
        if ((localObject2 instanceof Declaration))
        {
          localDeclaration = (Declaration)localObject2;
          paramPair = localDeclaration.getSymbol();
        }
      }
    }
    return paramTranslator.syntaxError("invalid syntax for " + getName());
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    Pair localPair;
    if ((paramPair.getCdr() instanceof Pair))
    {
      localPair = (Pair)paramPair.getCdr();
      if (((localPair.getCar() instanceof String)) || ((localPair.getCar() instanceof Symbol))) {}
    }
    else
    {
      return super.scanForDefinitions(paramPair, paramVector, paramScopeExp, paramTranslator);
    }
    Object localObject = localPair.getCar();
    Declaration localDeclaration = paramScopeExp.lookup(localObject);
    if (localDeclaration == null)
    {
      paramTranslator = new Declaration(localObject);
      paramTranslator.setProcedureDecl(true);
      paramScopeExp.addDeclaration(paramTranslator);
    }
    for (;;)
    {
      if ((paramScopeExp instanceof ModuleExp)) {
        paramTranslator.setCanRead(true);
      }
      paramVector.addElement(Translator.makePair(paramPair, this, Translator.makePair(localPair, paramTranslator, localPair.getCdr())));
      return true;
      paramTranslator.error('w', "duplicate declaration for `" + localObject + "'");
      paramTranslator = localDeclaration;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\commonlisp\lang\defun.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */