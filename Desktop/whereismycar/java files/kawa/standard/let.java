package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class let
  extends Syntax
{
  public static final let let = new let();
  
  static
  {
    let.setName("let");
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator)
  {
    if (!(paramObject instanceof Pair)) {
      return paramTranslator.syntaxError("missing let arguments");
    }
    Object localObject1 = (Pair)paramObject;
    Object localObject2 = ((Pair)localObject1).getCar();
    Object localObject7 = ((Pair)localObject1).getCdr();
    int m = Translator.listLength(localObject2);
    if (m < 0) {
      return paramTranslator.syntaxError("bindings not a proper list");
    }
    Expression[] arrayOfExpression = new Expression[m];
    LetExp localLetExp = new LetExp(arrayOfExpression);
    localObject1 = null;
    int i = 0;
    SyntaxForm localSyntaxForm = null;
    int j = 0;
    while (j < m)
    {
      while ((localObject2 instanceof SyntaxForm))
      {
        localSyntaxForm = (SyntaxForm)localObject2;
        localObject2 = localSyntaxForm.getDatum();
      }
      Pair localPair2 = (Pair)localObject2;
      Object localObject4 = localPair2.getCar();
      localObject2 = localSyntaxForm;
      Object localObject3 = localObject4;
      if ((localObject4 instanceof SyntaxForm))
      {
        localObject2 = (SyntaxForm)localObject4;
        localObject3 = ((SyntaxForm)localObject2).getDatum();
      }
      if (!(localObject3 instanceof Pair)) {
        return paramTranslator.syntaxError("let binding is not a pair:" + localObject3);
      }
      Object localObject5 = (Pair)localObject3;
      localObject4 = ((Pair)localObject5).getCar();
      Object localObject8;
      if ((localObject4 instanceof SyntaxForm))
      {
        localObject3 = (SyntaxForm)localObject4;
        localObject4 = ((SyntaxForm)localObject3).getDatum();
        localObject3 = ((SyntaxForm)localObject3).getScope();
        localObject8 = paramTranslator.namespaceResolve(localObject4);
        if (!(localObject8 instanceof Symbol)) {
          return paramTranslator.syntaxError("variable " + localObject8 + " in let binding is not a symbol: " + paramObject);
        }
      }
      else
      {
        if (localObject2 == null) {}
        for (localObject3 = null;; localObject3 = ((SyntaxForm)localObject2).getScope()) {
          break;
        }
      }
      Declaration localDeclaration = localLetExp.addDeclaration(localObject8);
      localDeclaration.setFlag(262144L);
      localObject4 = localObject1;
      int k = i;
      if (localObject3 != null)
      {
        localObject4 = paramTranslator.makeRenamedAlias(localDeclaration, (ScopeExp)localObject3);
        localObject3 = localObject1;
        if (localObject1 == null) {
          localObject3 = new Stack();
        }
        ((Stack)localObject3).push(localObject4);
        k = i + 1;
        localObject4 = localObject3;
      }
      localObject3 = ((Pair)localObject5).getCdr();
      localObject1 = localObject2;
      for (localObject2 = localObject3; (localObject2 instanceof SyntaxForm); localObject2 = ((SyntaxForm)localObject1).getDatum()) {
        localObject1 = (SyntaxForm)localObject2;
      }
      if (!(localObject2 instanceof Pair)) {
        return paramTranslator.syntaxError("let has no value for '" + localObject8 + "'");
      }
      Pair localPair1 = (Pair)localObject2;
      for (localObject3 = localPair1.getCdr(); (localObject3 instanceof SyntaxForm); localObject3 = ((SyntaxForm)localObject1).getDatum()) {
        localObject1 = (SyntaxForm)localObject3;
      }
      localObject2 = localPair1;
      Object localObject6 = localObject3;
      localObject5 = localObject1;
      if (paramTranslator.matches(localPair1.getCar(), "::"))
      {
        if ((localObject3 instanceof Pair))
        {
          localPair1 = (Pair)localObject3;
          if (localPair1.getCdr() != LList.Empty) {}
        }
        else
        {
          return paramTranslator.syntaxError("missing type after '::' in let");
        }
        localObject2 = localPair1.getCdr();
        localObject3 = localObject1;
        for (localObject1 = localObject2;; localObject1 = ((SyntaxForm)localObject3).getDatum())
        {
          localObject2 = localPair1;
          localObject6 = localObject1;
          localObject5 = localObject3;
          if (!(localObject1 instanceof SyntaxForm)) {
            break;
          }
          localObject3 = (SyntaxForm)localObject1;
        }
      }
      if (localObject6 == LList.Empty) {}
      for (;;)
      {
        arrayOfExpression[j] = paramTranslator.rewrite_car((Pair)localObject2, (SyntaxForm)localObject5);
        if (((Pair)localObject2).getCdr() == LList.Empty) {
          break label731;
        }
        return paramTranslator.syntaxError("junk after declaration of " + localObject8);
        if (!(localObject6 instanceof Pair)) {
          break;
        }
        localDeclaration.setType(paramTranslator.exp2Type((Pair)localObject2));
        localDeclaration.setFlag(8192L);
        localObject2 = (Pair)localObject6;
      }
      return paramTranslator.syntaxError("let binding for '" + localObject8 + "' is improper list");
      label731:
      localDeclaration.noteValue(arrayOfExpression[j]);
      localObject2 = localPair2.getCdr();
      j += 1;
      localObject1 = localObject4;
      i = k;
    }
    j = i;
    for (;;)
    {
      j -= 1;
      if (j < 0) {
        break;
      }
      paramTranslator.pushRenamedAlias((Declaration)((Stack)localObject1).pop());
    }
    paramTranslator.push(localLetExp);
    localLetExp.body = paramTranslator.rewrite_body(localObject7);
    paramTranslator.pop(localLetExp);
    paramTranslator.popRenamedAlias(i);
    return localLetExp;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\let.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */