package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class module_name
  extends Syntax
{
  public static final module_name module_name = new module_name();
  
  static
  {
    module_name.setName("module-name");
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    paramPair = paramPair.getCdr();
    Object localObject1 = null;
    while ((paramPair instanceof SyntaxForm))
    {
      localObject1 = (SyntaxForm)paramPair;
      paramPair = ((SyntaxForm)localObject1).getDatum();
    }
    Object localObject2;
    if ((paramPair instanceof Pair))
    {
      paramPair = ((Pair)paramPair).getCar();
      localObject2 = localObject1;
    }
    while ((paramPair instanceof SyntaxForm))
    {
      localObject2 = (SyntaxForm)paramPair;
      paramPair = ((SyntaxForm)localObject2).getDatum();
      continue;
      paramPair = null;
      localObject2 = localObject1;
    }
    String str2 = null;
    String str1 = null;
    localObject1 = null;
    if ((paramPair instanceof Pair))
    {
      Pair localPair = (Pair)paramPair;
      if (localPair.getCar() == "quote")
      {
        paramPair = localPair.getCdr();
        if ((paramPair instanceof Pair))
        {
          paramPair = (Pair)paramPair;
          if ((paramPair.getCdr() == LList.Empty) && ((paramPair.getCar() instanceof String))) {}
        }
        else
        {
          paramScopeExp = "invalid quoted symbol for 'module-name'";
          paramPair = str2;
          localObject2 = localObject1;
        }
      }
    }
    while (paramScopeExp != null)
    {
      paramTranslator.formStack.add(paramTranslator.syntaxError(paramScopeExp));
      return;
      paramPair = (String)paramPair.getCar();
      localObject2 = localObject1;
      paramScopeExp = str1;
      continue;
      if (((paramPair instanceof FString)) || ((paramPair instanceof String)))
      {
        paramPair = paramPair.toString();
        localObject2 = localObject1;
        paramScopeExp = str1;
      }
      else if ((paramPair instanceof Symbol))
      {
        str2 = paramPair.toString();
        i = str2.length();
        localObject1 = str2;
        if (i > 2)
        {
          localObject1 = str2;
          if (str2.charAt(0) == '<')
          {
            localObject1 = str2;
            if (str2.charAt(i - 1) == '>') {
              localObject1 = str2.substring(1, i - 1);
            }
          }
        }
        localObject2 = paramTranslator.define(paramPair, (SyntaxForm)localObject2, paramScopeExp);
        paramScopeExp = str1;
        paramPair = (Pair)localObject1;
      }
      else
      {
        paramScopeExp = "un-implemented expression in module-name";
        localObject2 = localObject1;
        paramPair = str2;
      }
    }
    int i = paramPair.lastIndexOf('.');
    localObject1 = paramPair;
    if (i >= 0)
    {
      paramTranslator.classPrefix = paramPair.substring(0, i + 1);
      paramScopeExp = paramPair;
      paramPair = (Pair)localObject1;
      localObject1 = paramTranslator.getModule();
      if (paramTranslator.mainClass != null) {
        break label527;
      }
      paramTranslator.mainClass = new ClassType(paramPair);
    }
    for (;;)
    {
      ((ModuleExp)localObject1).setType(paramTranslator.mainClass);
      ((ModuleExp)localObject1).setName(paramScopeExp);
      if (localObject2 != null)
      {
        ((Declaration)localObject2).noteValue(new QuoteExp(paramTranslator.mainClass, Compilation.typeClass));
        ((Declaration)localObject2).setFlag(16793600L);
        if (((ModuleExp)localObject1).outer == null) {
          ((Declaration)localObject2).setFlag(2048L);
        }
        ((Declaration)localObject2).setPrivate(true);
        ((Declaration)localObject2).setType(Compilation.typeClass);
      }
      paramTranslator.mustCompileHere();
      return;
      paramScopeExp = paramTranslator.classPrefix + paramPair;
      paramPair = paramTranslator.classPrefix + Compilation.mangleName(paramScopeExp);
      break;
      label527:
      str1 = paramTranslator.mainClass.getName();
      if (str1 == null) {
        paramTranslator.mainClass.setName(paramPair);
      } else if (!str1.equals(paramPair)) {
        paramTranslator.syntaxError("duplicate module-name: old name: " + str1);
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\module_name.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */