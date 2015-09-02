package gnu.q2.lang;

import gnu.kawa.lispexpr.ReaderDispatchMisc;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

class Q2ReaderParens
  extends ReaderDispatchMisc
{
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    paramLexer = (Q2Read)paramLexer;
    char c = paramLexer.pushNesting('(');
    try
    {
      Object localObject1 = paramLexer.readCommand(true);
      if (paramLexer.getPort().read() != 41) {
        paramLexer.error("missing ')'");
      }
      return localObject1;
    }
    finally
    {
      paramLexer.popNesting(c);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\q2\lang\Q2ReaderParens.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */