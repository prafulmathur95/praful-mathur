package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Symbol;
import gnu.text.Char;

public class IsEqv
  extends Procedure2
{
  IsEq isEq;
  Language language;
  
  public IsEqv(Language paramLanguage, String paramString, IsEq paramIsEq)
  {
    this.language = paramLanguage;
    this.isEq = paramIsEq;
    setName(paramString);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompilationHelpers:validateIsEqv");
  }
  
  public static boolean apply(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2) {
      return true;
    }
    if (((paramObject1 instanceof Number)) && ((paramObject2 instanceof Number))) {
      return IsEqual.numberEquals((Number)paramObject1, (Number)paramObject2);
    }
    if (((paramObject1 instanceof Char)) || ((paramObject1 instanceof Symbol))) {
      return paramObject1.equals(paramObject2);
    }
    return false;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
  {
    return this.language.booleanObject(apply(paramObject1, paramObject2));
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\IsEqv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */