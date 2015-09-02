package gnu.kawa.lispexpr;

import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class ResolveNamespace
  extends Syntax
{
  public static final ResolveNamespace resolveNamespace = new ResolveNamespace("$resolve-namespace$", false);
  public static final ResolveNamespace resolveQName = new ResolveNamespace("$resolve-qname", true);
  boolean resolvingQName;
  
  static
  {
    resolveNamespace.setName("$resolve-namespace$");
  }
  
  public ResolveNamespace(String paramString, boolean paramBoolean)
  {
    super(paramString);
    this.resolvingQName = paramBoolean;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    Pair localPair = (Pair)paramPair.getCdr();
    Object localObject = paramTranslator.namespaceResolvePrefix(paramTranslator.rewrite_car(localPair, false));
    paramPair = (Pair)localObject;
    if (localObject == null)
    {
      paramPair = localPair.getCar().toString();
      if (paramPair != "[default-element-namespace]") {
        break label79;
      }
    }
    for (paramPair = Namespace.EmptyNamespace; this.resolvingQName; paramPair = Namespace.valueOf(paramPair, paramPair))
    {
      return new QuoteExp(paramPair.getSymbol(((Pair)localPair.getCdr()).getCar().toString()));
      label79:
      localObject = paramTranslator.pushPositionOf(localPair);
      paramTranslator.error('e', "unknown namespace prefix " + paramPair);
      paramTranslator.popPositionOf(localObject);
    }
    return new QuoteExp(paramPair);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ResolveNamespace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */