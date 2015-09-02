package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.reflect.Invoke;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.math.BaseUnit;
import gnu.math.Quantity;
import gnu.math.Unit;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_unit
  extends Syntax
{
  public static final define_unit define_base_unit;
  public static final define_unit define_unit = new define_unit(false);
  boolean base;
  
  static
  {
    define_unit.setName("define-unit");
    define_base_unit = new define_unit(true);
    define_base_unit.setName("define-base-unit");
  }
  
  public define_unit(boolean paramBoolean)
  {
    this.base = paramBoolean;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    paramPair = paramPair.getCdr();
    Pair localPair;
    if ((paramPair instanceof Pair))
    {
      localPair = (Pair)paramPair;
      if ((localPair.getCar() instanceof Declaration)) {}
    }
    else
    {
      return paramTranslator.syntaxError("invalid syntax for " + getName());
    }
    Declaration localDeclaration = (Declaration)localPair.getCar();
    String str = ((Symbol)localDeclaration.getSymbol()).getLocalPart();
    ClassType localClassType = ClassType.make("gnu.math.Unit");
    localDeclaration.setType(localClassType);
    paramPair = localDeclaration.getValue();
    if (((paramPair instanceof QuoteExp)) && ((((QuoteExp)paramPair).getValue() instanceof Unit))) {}
    for (;;)
    {
      paramTranslator = new SetExp(localDeclaration, paramPair);
      paramTranslator.setDefining(true);
      localDeclaration.noteValue(paramPair);
      return paramTranslator;
      if (this.base)
      {
        paramPair = null;
        if (localPair.getCdr() != LList.Empty) {
          paramPair = ((Pair)localPair.getCdr()).getCar().toString();
        }
        paramPair = new QuoteExp(BaseUnit.make(str, paramPair));
      }
      else
      {
        if (!(localPair.getCdr() instanceof Pair)) {
          return paramTranslator.syntaxError("missing value for define-unit");
        }
        paramPair = paramTranslator.rewrite(((Pair)localPair.getCdr()).getCar());
        if ((paramPair instanceof QuoteExp))
        {
          paramTranslator = ((QuoteExp)paramPair).getValue();
          if ((paramTranslator instanceof Quantity))
          {
            paramPair = new QuoteExp(Unit.make(str, (Quantity)paramTranslator));
            continue;
          }
        }
        paramPair = Invoke.makeInvokeStatic(localClassType, "make", new Expression[] { new QuoteExp(str), paramPair });
      }
    }
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    if ((paramPair.getCdr() instanceof Pair))
    {
      Pair localPair = (Pair)paramPair.getCdr();
      Object localObject1 = localPair.getCar();
      if ((localObject1 instanceof SimpleSymbol))
      {
        String str = localObject1.toString();
        localObject1 = paramScopeExp.getDefine(Scheme.unitNamespace.getSymbol(str), 'w', paramTranslator);
        paramTranslator.push((Declaration)localObject1);
        Translator.setLine((Declaration)localObject1, localPair);
        ((Declaration)localObject1).setFlag(16384L);
        if ((paramScopeExp instanceof ModuleExp)) {
          ((Declaration)localObject1).setCanRead(true);
        }
        paramTranslator = null;
        if ((this.base) && (localPair.getCdr() == LList.Empty)) {
          paramScopeExp = BaseUnit.make(str, (String)null);
        }
        for (;;)
        {
          if (paramScopeExp != null) {
            ((Declaration)localObject1).noteValue(new QuoteExp(paramScopeExp));
          }
          paramVector.addElement(Translator.makePair(paramPair, this, Translator.makePair(localPair, localObject1, localPair.getCdr())));
          return true;
          paramScopeExp = paramTranslator;
          if ((localPair.getCdr() instanceof Pair))
          {
            Object localObject2 = ((Pair)localPair.getCdr()).getCar();
            if ((this.base) && ((localObject2 instanceof CharSequence)))
            {
              paramScopeExp = BaseUnit.make(str, localObject2.toString());
            }
            else
            {
              paramScopeExp = paramTranslator;
              if (!this.base)
              {
                paramScopeExp = paramTranslator;
                if ((localObject2 instanceof Quantity)) {
                  paramScopeExp = Unit.make(str, (Quantity)localObject2);
                }
              }
            }
          }
        }
      }
    }
    paramTranslator.error('e', "missing name in define-unit");
    return false;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\define_unit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */