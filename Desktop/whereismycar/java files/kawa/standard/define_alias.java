package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ClassExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define_alias
  extends Syntax
{
  public static final define_alias define_alias = new define_alias();
  
  static
  {
    define_alias.setName("define-alias");
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator)
  {
    return paramTranslator.syntaxError("define-alias is only allowed in a <body>");
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    Object localObject1 = paramPair.getCdr();
    paramPair = null;
    while ((localObject1 instanceof SyntaxForm))
    {
      paramPair = (SyntaxForm)localObject1;
      localObject1 = paramPair.getDatum();
    }
    if ((localObject1 instanceof Pair))
    {
      Object localObject3 = (Pair)localObject1;
      Object localObject2 = paramPair;
      for (localObject1 = ((Pair)localObject3).getCar(); (localObject1 instanceof SyntaxForm); localObject1 = ((SyntaxForm)localObject2).getDatum()) {
        localObject2 = (SyntaxForm)localObject1;
      }
      for (localObject3 = ((Pair)localObject3).getCdr(); (localObject3 instanceof SyntaxForm); localObject3 = paramPair.getDatum()) {
        paramPair = (SyntaxForm)localObject3;
      }
      if ((((localObject1 instanceof String)) || ((localObject1 instanceof Symbol))) && ((localObject3 instanceof Pair)))
      {
        localObject3 = (Pair)localObject3;
        if (((Pair)localObject3).getCdr() == LList.Empty)
        {
          paramScopeExp = paramTranslator.define(localObject1, (SyntaxForm)localObject2, paramScopeExp);
          paramScopeExp.setIndirectBinding(true);
          paramScopeExp.setAlias(true);
          paramPair = paramTranslator.rewrite_car((Pair)localObject3, paramPair);
          if ((paramPair instanceof ReferenceExp))
          {
            localObject1 = (ReferenceExp)paramPair;
            localObject2 = Declaration.followAliases(((ReferenceExp)localObject1).getBinding());
            if (localObject2 != null)
            {
              localObject2 = ((Declaration)localObject2).getValue();
              if (((localObject2 instanceof ClassExp)) || ((localObject2 instanceof ModuleExp)))
              {
                paramScopeExp.setIndirectBinding(false);
                paramScopeExp.setFlag(16384L);
              }
            }
          }
          for (;;)
          {
            paramTranslator.mustCompileHere();
            paramTranslator.push(paramScopeExp);
            localObject1 = new SetExp(paramScopeExp, paramPair);
            paramTranslator.setLineOf((Expression)localObject1);
            paramScopeExp.noteValue(paramPair);
            ((SetExp)localObject1).setDefining(true);
            paramVector.addElement(localObject1);
            return true;
            ((ReferenceExp)localObject1).setDontDereference(true);
            continue;
            if ((paramPair instanceof QuoteExp))
            {
              paramScopeExp.setIndirectBinding(false);
              paramScopeExp.setFlag(16384L);
            }
            else
            {
              paramPair = location.rewrite(paramPair, paramTranslator);
              paramScopeExp.setType(ClassType.make("gnu.mapping.Location"));
            }
          }
        }
      }
    }
    paramTranslator.error('e', "invalid syntax for define-alias");
    return false;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\define_alias.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */