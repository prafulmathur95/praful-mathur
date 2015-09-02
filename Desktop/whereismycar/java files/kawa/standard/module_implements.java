package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_implements
  extends Syntax
{
  public static final module_implements module_implements = new module_implements();
  
  static
  {
    module_implements.setName("module-implements");
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    paramPair = paramPair.getCdr();
    int j = LList.listLength(paramPair, false);
    if (j < 0)
    {
      paramTranslator.syntaxError("improper argument list for " + getName());
      return;
    }
    paramScopeExp = new ClassType[j];
    int i = 0;
    while (i < j)
    {
      paramPair = (Pair)paramPair;
      paramScopeExp[i] = ((ClassType)paramTranslator.exp2Type(paramPair));
      paramPair = paramPair.getCdr();
      i += 1;
    }
    paramPair = paramTranslator.getModule();
    paramPair.setInterfaces(paramScopeExp);
    paramPair.setFlag(131072);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\module_implements.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */