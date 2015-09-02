package gnu.kawa.lispexpr;

import gnu.mapping.Procedure;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderDispatchMacro
  extends ReaderMisc
{
  Procedure procedure;
  
  public ReaderDispatchMacro(Procedure paramProcedure)
  {
    super(5);
    this.procedure = paramProcedure;
  }
  
  public Procedure getProcedure()
  {
    return this.procedure;
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    Object localObject = paramLexer.getPort();
    try
    {
      localObject = this.procedure.apply3(localObject, Char.make(paramInt1), IntNum.make(paramInt2));
      return localObject;
    }
    catch (IOException paramLexer)
    {
      throw paramLexer;
    }
    catch (SyntaxException paramLexer)
    {
      throw paramLexer;
    }
    catch (Throwable localThrowable)
    {
      paramLexer.fatal("reader macro '" + this.procedure + "' threw: " + localThrowable);
    }
    return null;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ReaderDispatchMacro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */