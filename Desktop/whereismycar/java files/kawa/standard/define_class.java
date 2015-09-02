package kawa.standard;

import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define_class
  extends Syntax
{
  public static final define_class define_class = new define_class("define-class", false);
  public static final define_class define_simple_class = new define_class("define-simple-class", true);
  boolean isSimple;
  object objectSyntax;
  
  define_class(String paramString, boolean paramBoolean)
  {
    super(paramString);
    this.objectSyntax = object.objectSyntax;
    this.isSimple = paramBoolean;
  }
  
  define_class(object paramobject, boolean paramBoolean)
  {
    this.objectSyntax = paramobject;
    this.isSimple = paramBoolean;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    Object localObject1 = null;
    Object localObject2 = paramPair.getCdr();
    if ((localObject2 instanceof Pair))
    {
      paramPair = (Pair)localObject2;
      localObject1 = paramPair.getCar();
      if (!(localObject1 instanceof Declaration)) {
        return paramTranslator.syntaxError(getName() + " can only be used in <body>");
      }
      localObject1 = (Declaration)localObject1;
    }
    localObject2 = (ClassExp)((Declaration)localObject1).getValue();
    this.objectSyntax.rewriteClassDef((Object[])paramPair.getCdr(), paramTranslator);
    paramPair = new SetExp((Declaration)localObject1, (Expression)localObject2);
    paramPair.setDefining(true);
    return paramPair;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    Object localObject1 = paramPair.getCdr();
    SyntaxForm localSyntaxForm = null;
    while ((localObject1 instanceof SyntaxForm))
    {
      localSyntaxForm = (SyntaxForm)localObject1;
      localObject1 = localSyntaxForm.getDatum();
    }
    if (!(localObject1 instanceof Pair)) {
      return super.scanForDefinitions(paramPair, paramVector, paramScopeExp, paramTranslator);
    }
    Pair localPair = (Pair)localObject1;
    for (localObject1 = localPair.getCar(); (localObject1 instanceof SyntaxForm); localObject1 = localSyntaxForm.getDatum()) {
      localSyntaxForm = (SyntaxForm)localObject1;
    }
    localObject1 = paramTranslator.namespaceResolve(localObject1);
    if ((!(localObject1 instanceof String)) && (!(localObject1 instanceof Symbol)))
    {
      paramTranslator.error('e', "missing class name");
      return false;
    }
    Declaration localDeclaration = paramTranslator.define(localObject1, localSyntaxForm, paramScopeExp);
    if ((localPair instanceof PairWithPosition)) {
      localDeclaration.setLocation((PairWithPosition)localPair);
    }
    Object localObject2 = new ClassExp(this.isSimple);
    localDeclaration.noteValue((Expression)localObject2);
    localDeclaration.setFlag(536887296L);
    if (this.isSimple)
    {
      paramScopeExp = Compilation.typeClass;
      localDeclaration.setType(paramScopeExp);
      paramTranslator.mustCompileHere();
      if (!(localObject1 instanceof Symbol)) {
        break label327;
      }
    }
    label327:
    for (paramScopeExp = ((Symbol)localObject1).getName();; paramScopeExp = localObject1.toString())
    {
      int i = paramScopeExp.length();
      localObject1 = paramScopeExp;
      if (i > 2)
      {
        localObject1 = paramScopeExp;
        if (paramScopeExp.charAt(0) == '<')
        {
          localObject1 = paramScopeExp;
          if (paramScopeExp.charAt(i - 1) == '>') {
            localObject1 = paramScopeExp.substring(1, i - 1);
          }
        }
      }
      ((ClassExp)localObject2).setName((String)localObject1);
      for (paramScopeExp = localPair.getCdr(); (paramScopeExp instanceof SyntaxForm); paramScopeExp = localSyntaxForm.getDatum()) {
        localSyntaxForm = (SyntaxForm)paramScopeExp;
      }
      paramScopeExp = Compilation.typeClassType;
      break;
    }
    if (!(paramScopeExp instanceof Pair))
    {
      paramTranslator.error('e', "missing class members");
      return false;
    }
    paramScopeExp = (Pair)paramScopeExp;
    localObject1 = paramTranslator.currentScope();
    if (localSyntaxForm != null) {
      paramTranslator.setCurrentScope(localSyntaxForm.getScope());
    }
    localObject2 = this.objectSyntax.scanClassDef(paramScopeExp, (ClassExp)localObject2, paramTranslator);
    if (localSyntaxForm != null) {
      paramTranslator.setCurrentScope((ScopeExp)localObject1);
    }
    if (localObject2 == null) {
      return false;
    }
    paramVector.addElement(Translator.makePair(paramPair, this, Translator.makePair(paramScopeExp, localDeclaration, localObject2)));
    return true;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\define_class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */