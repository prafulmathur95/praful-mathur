package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.expr.ThisExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Macro;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define_syntax
  extends Syntax
{
  public static final define_syntax define_macro = new define_syntax("%define-macro", false);
  public static final define_syntax define_syntax = new define_syntax("%define-syntax", true);
  static PrimProcedure makeHygienic;
  static PrimProcedure makeNonHygienic;
  static PrimProcedure setCapturedScope;
  static ClassType typeMacro = ClassType.make("kawa.lang.Macro");
  boolean hygienic;
  
  static
  {
    makeHygienic = new PrimProcedure(typeMacro.getDeclaredMethod("make", 3));
    makeNonHygienic = new PrimProcedure(typeMacro.getDeclaredMethod("makeNonHygienic", 3));
    setCapturedScope = new PrimProcedure(typeMacro.getDeclaredMethod("setCapturedScope", 1));
    makeHygienic.setSideEffectFree();
    makeNonHygienic.setSideEffectFree();
  }
  
  public define_syntax()
  {
    this.hygienic = true;
  }
  
  public define_syntax(Object paramObject, boolean paramBoolean)
  {
    super(paramObject);
    this.hygienic = paramBoolean;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    return paramTranslator.syntaxError("define-syntax not in a body");
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    Object localObject1 = null;
    for (Object localObject2 = paramPair.getCdr(); (localObject2 instanceof SyntaxForm); localObject2 = ((SyntaxForm)localObject1).getDatum()) {
      localObject1 = (SyntaxForm)localObject2;
    }
    Object localObject3 = localObject2;
    if ((localObject3 instanceof Pair))
    {
      localObject3 = (Pair)localObject3;
      localObject2 = ((Pair)localObject3).getCar();
      localObject3 = ((Pair)localObject3).getCdr();
    }
    for (;;)
    {
      localObject4 = localObject1;
      while ((localObject2 instanceof SyntaxForm))
      {
        localObject4 = (SyntaxForm)localObject2;
        localObject2 = ((SyntaxForm)localObject4).getDatum();
      }
      localObject2 = null;
    }
    Object localObject5 = paramTranslator.namespaceResolve(localObject2);
    if (!(localObject5 instanceof Symbol))
    {
      paramTranslator.formStack.addElement(paramTranslator.syntaxError("missing macro name for " + Translator.safeCar(paramPair)));
      return;
    }
    if ((localObject3 == null) || (Translator.safeCdr(localObject3) != LList.Empty))
    {
      paramTranslator.formStack.addElement(paramTranslator.syntaxError("invalid syntax for " + getName()));
      return;
    }
    localObject2 = paramTranslator.define(localObject5, (SyntaxForm)localObject4, paramScopeExp);
    ((Declaration)localObject2).setType(typeMacro);
    paramTranslator.push((Declaration)localObject2);
    paramPair = paramTranslator.currentMacroDefinition;
    Object localObject4 = Macro.make((Declaration)localObject2);
    ((Macro)localObject4).setHygienic(this.hygienic);
    paramTranslator.currentMacroDefinition = ((Macro)localObject4);
    localObject1 = paramTranslator.rewrite_car((Pair)localObject3, (SyntaxForm)localObject1);
    paramTranslator.currentMacroDefinition = paramPair;
    ((Macro)localObject4).expander = localObject1;
    if ((localObject1 instanceof LambdaExp)) {
      ((LambdaExp)localObject1).setFlag(256);
    }
    localObject3 = new QuoteExp(localObject5);
    localObject4 = ThisExp.makeGivingContext(paramScopeExp);
    if (this.hygienic) {}
    for (paramPair = makeHygienic;; paramPair = makeNonHygienic)
    {
      paramPair = new ApplyExp(paramPair, new Expression[] { localObject3, localObject1, localObject4 });
      ((Declaration)localObject2).noteValue(paramPair);
      ((Declaration)localObject2).setProcedureDecl(true);
      if (!(((Declaration)localObject2).context instanceof ModuleExp)) {
        break;
      }
      paramPair = new SetExp((Declaration)localObject2, paramPair);
      paramPair.setDefining(true);
      if (paramTranslator.getLanguage().hasSeparateFunctionNamespace()) {
        paramPair.setFuncDef(true);
      }
      paramTranslator.formStack.addElement(paramPair);
      if (!paramTranslator.immediate) {
        break;
      }
      paramPair = new ReferenceExp((Declaration)localObject2);
      paramScopeExp = new QuoteExp(paramScopeExp);
      paramTranslator.formStack.addElement(new ApplyExp(setCapturedScope, new Expression[] { paramPair, paramScopeExp }));
      return;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\define_syntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */