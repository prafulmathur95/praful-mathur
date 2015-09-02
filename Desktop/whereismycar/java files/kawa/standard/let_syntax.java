package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.LetExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Macro;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class let_syntax
  extends Syntax
{
  public static final let_syntax let_syntax = new let_syntax(false, "let-syntax");
  public static final let_syntax letrec_syntax = new let_syntax(true, "letrec-syntax");
  boolean recursive;
  
  public let_syntax(boolean paramBoolean, String paramString)
  {
    super(paramString);
    this.recursive = paramBoolean;
  }
  
  private void push(LetExp paramLetExp, Translator paramTranslator, Stack paramStack)
  {
    paramTranslator.push(paramLetExp);
    if (paramStack != null)
    {
      int i = paramStack.size();
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          break;
        }
        paramTranslator.pushRenamedAlias((Declaration)paramStack.pop());
      }
    }
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator)
  {
    if (!(paramObject instanceof Pair)) {
      return paramTranslator.syntaxError("missing let-syntax arguments");
    }
    Object localObject1 = (Pair)paramObject;
    paramObject = ((Pair)localObject1).getCar();
    Object localObject7 = ((Pair)localObject1).getCdr();
    int m = Translator.listLength(paramObject);
    if (m < 0) {
      return paramTranslator.syntaxError("bindings not a proper list");
    }
    localObject1 = null;
    int i = 0;
    Expression[] arrayOfExpression = new Expression[m];
    Declaration[] arrayOfDeclaration = new Declaration[m];
    Macro[] arrayOfMacro = new Macro[m];
    Pair[] arrayOfPair = new Pair[m];
    SyntaxForm[] arrayOfSyntaxForm = new SyntaxForm[m];
    LetExp localLetExp = new LetExp(arrayOfExpression);
    Object localObject2 = null;
    int j = 0;
    Object localObject3;
    Object localObject4;
    if (j < m)
    {
      for (localObject3 = paramObject; (localObject3 instanceof SyntaxForm); localObject3 = ((SyntaxForm)localObject2).getDatum()) {
        localObject2 = (SyntaxForm)localObject3;
      }
      paramObject = localObject2;
      Pair localPair = (Pair)localObject3;
      localObject4 = localPair.getCar();
      localObject3 = localObject4;
      if ((localObject4 instanceof SyntaxForm))
      {
        paramObject = (SyntaxForm)localObject4;
        localObject3 = ((SyntaxForm)paramObject).getDatum();
      }
      if (!(localObject3 instanceof Pair)) {
        return paramTranslator.syntaxError(getName() + " binding is not a pair");
      }
      localObject3 = (Pair)localObject3;
      Object localObject5 = ((Pair)localObject3).getCar();
      localObject4 = paramObject;
      while ((localObject5 instanceof SyntaxForm))
      {
        localObject4 = (SyntaxForm)localObject5;
        localObject5 = ((SyntaxForm)localObject4).getDatum();
      }
      if ((!(localObject5 instanceof String)) && (!(localObject5 instanceof Symbol))) {
        return paramTranslator.syntaxError("variable in " + getName() + " binding is not a symbol");
      }
      Object localObject6 = ((Pair)localObject3).getCdr();
      localObject3 = paramObject;
      while ((localObject6 instanceof SyntaxForm))
      {
        localObject3 = (SyntaxForm)localObject6;
        localObject6 = ((SyntaxForm)localObject3).getDatum();
      }
      if (!(localObject6 instanceof Pair)) {
        return paramTranslator.syntaxError(getName() + " has no value for '" + localObject5 + "'");
      }
      paramObject = (Pair)localObject6;
      if (((Pair)paramObject).getCdr() != LList.Empty) {
        return paramTranslator.syntaxError("let binding for '" + localObject5 + "' is improper list");
      }
      localObject5 = new Declaration(localObject5);
      localObject6 = Macro.make((Declaration)localObject5);
      arrayOfMacro[j] = localObject6;
      arrayOfPair[j] = paramObject;
      arrayOfSyntaxForm[j] = localObject3;
      localLetExp.addDeclaration((Declaration)localObject5);
      label492:
      int k;
      if (localObject4 == null)
      {
        paramObject = null;
        localObject4 = localObject1;
        k = i;
        if (paramObject != null)
        {
          localObject4 = paramTranslator.makeRenamedAlias((Declaration)localObject5, (ScopeExp)paramObject);
          paramObject = localObject1;
          if (localObject1 == null) {
            paramObject = new Stack();
          }
          ((Stack)paramObject).push(localObject4);
          k = i + 1;
          localObject4 = paramObject;
        }
        if (localObject3 == null) {
          break label609;
        }
        paramObject = ((SyntaxForm)localObject3).getScope();
      }
      for (;;)
      {
        ((Macro)localObject6).setCapturedScope((ScopeExp)paramObject);
        arrayOfDeclaration[j] = localObject5;
        arrayOfExpression[j] = QuoteExp.nullExp;
        paramObject = localPair.getCdr();
        j += 1;
        localObject1 = localObject4;
        i = k;
        break;
        paramObject = ((SyntaxForm)localObject4).getScope();
        break label492;
        label609:
        if (this.recursive) {
          paramObject = localLetExp;
        } else {
          paramObject = paramTranslator.currentScope();
        }
      }
    }
    if (this.recursive) {
      push(localLetExp, paramTranslator, (Stack)localObject1);
    }
    paramObject = paramTranslator.currentMacroDefinition;
    j = 0;
    while (j < m)
    {
      localObject3 = arrayOfMacro[j];
      paramTranslator.currentMacroDefinition = ((Macro)localObject3);
      localObject4 = paramTranslator.rewrite_car(arrayOfPair[j], arrayOfSyntaxForm[j]);
      arrayOfExpression[j] = localObject4;
      localObject2 = arrayOfDeclaration[j];
      ((Macro)localObject3).expander = localObject4;
      ((Declaration)localObject2).noteValue(new QuoteExp(localObject3));
      if ((localObject4 instanceof LambdaExp))
      {
        localObject3 = (LambdaExp)localObject4;
        ((LambdaExp)localObject3).nameDecl = ((Declaration)localObject2);
        ((LambdaExp)localObject3).setSymbol(((Declaration)localObject2).getSymbol());
      }
      j += 1;
    }
    paramTranslator.currentMacroDefinition = ((Macro)paramObject);
    if (!this.recursive) {
      push(localLetExp, paramTranslator, (Stack)localObject1);
    }
    paramObject = paramTranslator.rewrite_body(localObject7);
    paramTranslator.pop(localLetExp);
    paramTranslator.popRenamedAlias(i);
    return (Expression)paramObject;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\let_syntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */