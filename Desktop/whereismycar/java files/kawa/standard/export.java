package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class export
  extends Syntax
{
  public static final export export;
  public static final export module_export = new export();
  
  static
  {
    module_export.setName("module-export");
    export = new export();
    module_export.setName("export");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    return null;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    paramVector = paramPair.getCdr();
    Object localObject3 = paramTranslator.pushPositionOf(paramPair);
    for (;;)
    {
      Object localObject1;
      try
      {
        if ((paramScopeExp instanceof ModuleExp))
        {
          ((ModuleExp)paramScopeExp).setFlag(16384);
          paramPair = null;
          if (paramVector == LList.Empty) {
            continue;
          }
          paramTranslator.pushPositionOf(paramVector);
          if ((paramVector instanceof SyntaxForm))
          {
            paramPair = (SyntaxForm)paramVector;
            paramVector = paramPair.getDatum();
            continue;
          }
        }
        else
        {
          paramTranslator.error('e', "'" + getName() + "' not at module level");
          return true;
        }
        localObject1 = paramPair;
        if ((paramVector instanceof Pair))
        {
          Pair localPair = (Pair)paramVector;
          paramVector = localPair.getCar();
          if ((paramVector instanceof SyntaxForm))
          {
            localObject1 = (SyntaxForm)paramVector;
            paramVector = ((SyntaxForm)localObject1).getDatum();
            continue;
          }
          Object localObject2 = paramVector;
          if ((paramVector instanceof String))
          {
            String str = (String)paramVector;
            localObject2 = paramVector;
            if (str.startsWith("namespace:"))
            {
              paramTranslator.error('w', "'namespace:' prefix ignored");
              localObject2 = str.substring(10).intern();
            }
          }
          if ((localObject2 instanceof String)) {
            break label327;
          }
          if ((localObject2 instanceof Symbol))
          {
            break label327;
            paramVector = paramScopeExp.getNoDefine(localObject2);
            if (paramVector.getFlag(512L)) {
              Translator.setLine(paramVector, localPair);
            }
            paramVector.setFlag(1024L);
            paramVector = localPair.getCdr();
          }
        }
        paramTranslator.error('e', "invalid syntax in '" + getName() + '\'');
        return false;
        return true;
      }
      finally
      {
        paramTranslator.popPositionOf(localObject3);
      }
      label327:
      if (localObject1 == null) {}
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\export.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */