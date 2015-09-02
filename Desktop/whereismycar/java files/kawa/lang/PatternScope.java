package kawa.lang;

import gnu.expr.Declaration;
import gnu.expr.LetExp;
import java.util.Vector;

public class PatternScope
  extends LetExp
{
  public Declaration matchArray;
  public StringBuffer patternNesting;
  public Vector pattern_names;
  PatternScope previousSyntax;
  
  public PatternScope()
  {
    super(null);
  }
  
  public static void pop(Translator paramTranslator)
  {
    paramTranslator.patternScope = paramTranslator.patternScope.previousSyntax;
  }
  
  public static PatternScope push(Translator paramTranslator)
  {
    PatternScope localPatternScope1 = new PatternScope();
    PatternScope localPatternScope2 = paramTranslator.patternScope;
    localPatternScope1.previousSyntax = localPatternScope2;
    paramTranslator.patternScope = localPatternScope1;
    if (localPatternScope2 == null) {
      localPatternScope1.pattern_names = new Vector();
    }
    for (localPatternScope1.patternNesting = new StringBuffer();; localPatternScope1.patternNesting = new StringBuffer(localPatternScope2.patternNesting.toString()))
    {
      localPatternScope1.outer = paramTranslator.currentScope();
      return localPatternScope1;
      localPatternScope1.pattern_names = ((Vector)localPatternScope2.pattern_names.clone());
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\PatternScope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */