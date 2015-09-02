package gnu.expr;

import gnu.lists.Consumer;
import gnu.mapping.OutPort;
import gnu.mapping.SimpleSymbol;

public class Symbols
{
  private static int gensym_counter;
  
  static int generateInt()
  {
    try
    {
      int i = gensym_counter + 1;
      gensym_counter = i;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static final SimpleSymbol gentemp()
  {
    return SimpleSymbol.valueOf("GS." + Integer.toString(generateInt()));
  }
  
  public static final String intern(String paramString)
  {
    return make(paramString);
  }
  
  public static String make(String paramString)
  {
    return paramString.intern();
  }
  
  public static void print(String paramString, Consumer paramConsumer)
  {
    if (((paramConsumer instanceof OutPort)) && (((OutPort)paramConsumer).printReadable)) {}
    for (int i = 1; i != 0; i = 0)
    {
      int j = paramString.length();
      i = 0;
      while (i < j)
      {
        char c = paramString.charAt(i);
        if ((!Character.isLowerCase(c)) && (c != '!') && (c != '$') && (c != '%') && (c != '&') && (c != '*') && (c != '/') && (c != ':') && (c != '<') && (c != '=') && (c != '>') && (c != '?') && (c != '~') && (c != '_') && (c != '^') && (((c != '+') && (c != '-')) || ((i <= 0) && (j != 1) && ((!Character.isDigit(c)) || (i <= 0)) && ((c != '.') || ((i != 0) && (paramString.charAt(i - 1) != '.')))))) {
          paramConsumer.write(92);
        }
        paramConsumer.write(c);
        i += 1;
      }
    }
    paramConsumer.write(paramString);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\Symbols.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */