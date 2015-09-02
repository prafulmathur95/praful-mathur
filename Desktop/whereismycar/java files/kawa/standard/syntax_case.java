package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BlockExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ExitExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.math.IntNum;
import java.util.Vector;
import kawa.lang.Pattern;
import kawa.lang.PatternScope;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxPattern;
import kawa.lang.Translator;

public class syntax_case
  extends Syntax
{
  public static final syntax_case syntax_case = new syntax_case();
  PrimProcedure call_error;
  
  static
  {
    syntax_case.setName("syntax-case");
  }
  
  public static Object error(String paramString, Object paramObject)
  {
    paramObject = (Translator)Compilation.getCurrent();
    if (paramObject == null) {
      throw new RuntimeException("no match in syntax-case");
    }
    paramString = ((Translator)paramObject).getCurrentSyntax();
    if (paramString == null) {}
    for (paramString = "some syntax";; paramString = paramString.getName()) {
      return ((Translator)paramObject).syntaxError("no matching case while expanding " + paramString);
    }
  }
  
  Expression rewriteClauses(Object paramObject, syntax_case_work paramsyntax_case_work, Translator paramTranslator)
  {
    Object localObject4 = paramTranslator.getLanguage();
    Object localObject2;
    if (paramObject == LList.Empty)
    {
      paramObject = new QuoteExp("syntax-case");
      paramsyntax_case_work = new ReferenceExp(paramsyntax_case_work.inputExpression);
      if (this.call_error == null)
      {
        paramTranslator = ClassType.make("kawa.standard.syntax_case");
        localObject1 = Compilation.javaStringType;
        localObject2 = Type.objectType;
        localObject3 = Type.objectType;
        this.call_error = new PrimProcedure(paramTranslator.addMethod("error", new Type[] { localObject1, localObject2 }, (Type)localObject3, 9), (Language)localObject4);
      }
      return new ApplyExp(this.call_error, new Expression[] { paramObject, paramsyntax_case_work });
    }
    Object localObject3 = paramTranslator.pushPositionOf(paramObject);
    Object localObject5;
    PatternScope localPatternScope;
    try
    {
      if ((paramObject instanceof Pair))
      {
        localObject1 = ((Pair)paramObject).getCar();
        if ((localObject1 instanceof Pair)) {}
      }
      else
      {
        paramObject = paramTranslator.syntaxError("syntax-case:  bad clause list");
        return (Expression)paramObject;
      }
      localObject5 = (Pair)localObject1;
      localPatternScope = PatternScope.push(paramTranslator);
      localPatternScope.matchArray = paramTranslator.matchArray;
      paramTranslator.push(localPatternScope);
      localObject1 = null;
      for (localObject2 = ((Pair)localObject5).getCdr(); (localObject2 instanceof SyntaxForm); localObject2 = ((SyntaxForm)localObject1).getDatum()) {
        localObject1 = (SyntaxForm)localObject2;
      }
      if (!(localObject2 instanceof Pair))
      {
        paramObject = paramTranslator.syntaxError("missing syntax-case output expression");
        return (Expression)paramObject;
      }
      int i = localPatternScope.pattern_names.size();
      localObject6 = new SyntaxPattern(((Pair)localObject5).getCar(), paramsyntax_case_work.literal_identifiers, paramTranslator);
      int j = ((SyntaxPattern)localObject6).varCount();
      if (j > paramsyntax_case_work.maxVars) {
        paramsyntax_case_work.maxVars = j;
      }
      localObject5 = new BlockExp();
      localObject6 = new QuoteExp(localObject6);
      ReferenceExp localReferenceExp1 = new ReferenceExp(paramsyntax_case_work.inputExpression);
      ReferenceExp localReferenceExp2 = new ReferenceExp(paramTranslator.matchArray);
      QuoteExp localQuoteExp = new QuoteExp(IntNum.zero());
      localObject4 = new ApplyExp(new PrimProcedure(Pattern.matchPatternMethod, (Language)localObject4), new Expression[] { localObject6, localReferenceExp1, localReferenceExp2, localQuoteExp });
      i = j - i;
      localObject6 = new Expression[i];
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          break;
        }
        localObject6[i] = QuoteExp.undefined_exp;
      }
      localPatternScope.inits = ((Expression[])localObject6);
    }
    finally
    {
      paramTranslator.popPositionOf(localObject3);
    }
    Object localObject6 = (Pair)localObject2;
    if (((Pair)localObject6).getCdr() == LList.Empty) {}
    for (Object localObject1 = paramTranslator.rewrite_car((Pair)localObject6, (SyntaxForm)localObject1);; localObject1 = new IfExp((Expression)localObject2, paramTranslator.rewrite_car((Pair)localObject6, (SyntaxForm)localObject1), new ExitExp((BlockExp)localObject5)))
    {
      localPatternScope.setBody((Expression)localObject1);
      paramTranslator.pop(localPatternScope);
      PatternScope.pop(paramTranslator);
      ((BlockExp)localObject5).setBody(new IfExp((Expression)localObject4, localPatternScope, new ExitExp((BlockExp)localObject5)), rewriteClauses(((Pair)paramObject).getCdr(), paramsyntax_case_work, paramTranslator));
      paramTranslator.popPositionOf(localObject3);
      return (Expression)localObject5;
      localObject2 = paramTranslator.rewrite_car((Pair)localObject6, (SyntaxForm)localObject1);
      if ((((Pair)localObject6).getCdr() instanceof Pair))
      {
        localObject6 = (Pair)((Pair)localObject6).getCdr();
        if (((Pair)localObject6).getCdr() == LList.Empty) {}
      }
      else
      {
        paramObject = paramTranslator.syntaxError("syntax-case:  bad clause");
        paramTranslator.popPositionOf(localObject3);
        return (Expression)paramObject;
      }
    }
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    syntax_case_work localsyntax_case_work = new syntax_case_work();
    Object localObject = paramPair.getCdr();
    if (((localObject instanceof Pair)) && ((((Pair)localObject).getCdr() instanceof Pair)))
    {
      paramPair = new Expression[2];
      LetExp localLetExp = new LetExp(paramPair);
      localsyntax_case_work.inputExpression = localLetExp.addDeclaration((String)null);
      Declaration localDeclaration1 = paramTranslator.matchArray;
      Declaration localDeclaration2 = localLetExp.addDeclaration((String)null);
      localDeclaration2.setType(Compilation.objArrayType);
      localDeclaration2.setCanRead(true);
      paramTranslator.matchArray = localDeclaration2;
      localsyntax_case_work.inputExpression.setCanRead(true);
      paramTranslator.push(localLetExp);
      localObject = (Pair)localObject;
      paramPair[0] = paramTranslator.rewrite(((Pair)localObject).getCar());
      localsyntax_case_work.inputExpression.noteValue(paramPair[0]);
      localObject = (Pair)((Pair)localObject).getCdr();
      localsyntax_case_work.literal_identifiers = SyntaxPattern.getLiteralsList(((Pair)localObject).getCar(), null, paramTranslator);
      localLetExp.body = rewriteClauses(((Pair)localObject).getCdr(), localsyntax_case_work, paramTranslator);
      paramTranslator.pop(localLetExp);
      localObject = ClassType.make("kawa.lang.SyntaxPattern").getDeclaredMethod("allocVars", 2);
      Expression[] arrayOfExpression = new Expression[2];
      arrayOfExpression[0] = new QuoteExp(IntNum.make(localsyntax_case_work.maxVars));
      if (localDeclaration1 == null) {
        arrayOfExpression[1] = QuoteExp.nullExp;
      }
      for (;;)
      {
        paramPair[1] = new ApplyExp((Method)localObject, arrayOfExpression);
        localDeclaration2.noteValue(paramPair[1]);
        paramTranslator.matchArray = localDeclaration1;
        return localLetExp;
        arrayOfExpression[1] = new ReferenceExp(localDeclaration1);
      }
    }
    return paramTranslator.syntaxError("insufficiant arguments to syntax-case");
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\syntax_case.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */