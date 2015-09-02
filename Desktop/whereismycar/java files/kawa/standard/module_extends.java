package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_extends
  extends Syntax
{
  public static final module_extends module_extends = new module_extends();
  
  static
  {
    module_extends.setName("module-extends");
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    paramPair = paramTranslator.exp2Type((Pair)paramPair.getCdr());
    paramScopeExp = paramTranslator.getModule();
    paramScopeExp.setSuperType((ClassType)paramPair);
    paramScopeExp.setFlag(131072);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\module_extends.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */