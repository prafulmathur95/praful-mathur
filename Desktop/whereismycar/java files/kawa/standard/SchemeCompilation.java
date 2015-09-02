package kawa.standard;

import gnu.expr.Special;
import kawa.lang.Lambda;
import kawa.repl;

public class SchemeCompilation
{
  public static final Lambda lambda = new Lambda();
  public static final repl repl = new repl(Scheme.instance);
  
  static
  {
    lambda.setKeywords(Special.optional, Special.rest, Special.key);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\SchemeCompilation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */