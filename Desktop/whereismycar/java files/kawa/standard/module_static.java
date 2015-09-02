package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_static
  extends Syntax
{
  public static final module_static module_static = new module_static();
  
  static
  {
    module_static.setName("module-static");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    return null;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    paramPair = paramPair.getCdr();
    if (!(paramScopeExp instanceof ModuleExp))
    {
      paramTranslator.error('e', "'" + getName() + "' not at module level");
      return true;
    }
    paramVector = (ModuleExp)paramScopeExp;
    Object localObject;
    if ((paramPair instanceof Pair))
    {
      localObject = (Pair)paramPair;
      if ((((Pair)localObject).getCdr() == LList.Empty) && ((((Pair)localObject).getCar() instanceof Boolean))) {
        if (((Pair)localObject).getCar() == Boolean.FALSE) {
          paramVector.setFlag(65536);
        }
      }
    }
    for (;;)
    {
      if ((paramVector.getFlag(65536)) && (paramVector.getFlag(32768)))
      {
        paramTranslator.error('e', "inconsistent module-static specifiers");
        return true;
        paramVector.setFlag(32768);
        continue;
        if (!(paramPair instanceof Pair)) {
          break label292;
        }
        localObject = (Pair)paramPair;
        if ((((Pair)localObject).getCdr() != LList.Empty) || (!(((Pair)localObject).getCar() instanceof Pair))) {
          break label292;
        }
        localObject = (Pair)((Pair)localObject).getCar();
        if (!paramTranslator.matches(((Pair)localObject).getCar(), "quote")) {
          break label292;
        }
        paramPair = (Pair)((Pair)localObject).getCdr();
        if ((paramPair != LList.Empty) && ((paramPair.getCar() instanceof SimpleSymbol)) && (paramPair.getCar().toString() == "init-run"))
        {
          paramVector.setFlag(32768);
          paramVector.setFlag(262144);
        }
      }
      else
      {
        break;
      }
      paramTranslator.error('e', "invalid quoted symbol for '" + getName() + '\'');
      return false;
      label292:
      paramVector.setFlag(65536);
      while (paramPair != LList.Empty)
      {
        if ((paramPair instanceof Pair))
        {
          paramPair = (Pair)paramPair;
          if ((paramPair.getCar() instanceof Symbol)) {}
        }
        else
        {
          paramTranslator.error('e', "invalid syntax in '" + getName() + '\'');
          return false;
        }
        localObject = paramScopeExp.getNoDefine((Symbol)paramPair.getCar());
        if (((Declaration)localObject).getFlag(512L)) {
          Translator.setLine((Declaration)localObject, paramPair);
        }
        ((Declaration)localObject).setFlag(2048L);
        paramPair = paramPair.getCdr();
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\module_static.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */