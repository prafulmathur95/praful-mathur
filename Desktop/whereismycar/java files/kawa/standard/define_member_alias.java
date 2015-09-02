package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.reflect.Invoke;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_member_alias
  extends Syntax
{
  public static final define_member_alias define_member_alias = new define_member_alias();
  
  static
  {
    define_member_alias.setName("define-member-alias");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    paramPair = paramPair.getCdr();
    if ((paramPair instanceof Pair))
    {
      paramPair = (Pair)paramPair;
      if (((paramPair.getCar() instanceof String)) || ((paramPair.getCar() instanceof Declaration))) {}
    }
    else
    {
      return paramTranslator.syntaxError("missing name in " + getName());
    }
    if ((paramPair.getCdr() instanceof Pair))
    {
      Object localObject1 = paramPair.getCar();
      Object localObject2;
      Expression localExpression;
      Object localObject3;
      if ((localObject1 instanceof Declaration))
      {
        localObject1 = ((Declaration)localObject1).getName();
        paramPair = (Pair)paramPair.getCdr();
        localObject2 = null;
        localExpression = paramTranslator.rewrite(paramPair.getCar());
        localObject3 = paramPair.getCdr();
        if (localObject3 != LList.Empty) {
          break label188;
        }
        paramPair = new QuoteExp(Compilation.mangleName((String)localObject1));
      }
      for (;;)
      {
        if (paramPair == null) {
          break label233;
        }
        return Invoke.makeInvokeStatic(ClassType.make("gnu.kawa.reflect.ClassMemberConstraint"), "define", new Expression[] { new QuoteExp(localObject1), localExpression, paramPair });
        localObject1 = (String)localObject1;
        break;
        label188:
        paramPair = (Pair)localObject2;
        if ((localObject3 instanceof Pair))
        {
          localObject3 = (Pair)localObject3;
          paramPair = (Pair)localObject2;
          if (((Pair)localObject3).getCdr() == LList.Empty) {
            paramPair = paramTranslator.rewrite(((Pair)localObject3).getCar());
          }
        }
      }
    }
    label233:
    return paramTranslator.syntaxError("invalid syntax for " + getName());
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    Pair localPair;
    if (((paramPair.getCdr() instanceof Pair)) && (!(paramTranslator.currentScope() instanceof ModuleExp)))
    {
      localPair = (Pair)paramPair.getCdr();
      if ((localPair.getCar() instanceof String)) {}
    }
    else
    {
      return super.scanForDefinitions(paramPair, paramVector, paramScopeExp, paramTranslator);
    }
    paramScopeExp = paramScopeExp.addDeclaration((String)localPair.getCar(), Compilation.typeSymbol);
    paramScopeExp.setIndirectBinding(true);
    paramVector.addElement(Translator.makePair(paramPair, this, Translator.makePair(localPair, paramScopeExp, localPair.getCdr())));
    return true;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\define_member_alias.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */