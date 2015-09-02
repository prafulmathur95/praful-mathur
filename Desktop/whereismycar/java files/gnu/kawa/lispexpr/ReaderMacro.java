package gnu.kawa.lispexpr;

import gnu.mapping.Procedure;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderMacro
  extends ReaderMisc
{
  Procedure procedure;
  
  public ReaderMacro(Procedure paramProcedure)
  {
    super(5);
    this.procedure = paramProcedure;
  }
  
  public ReaderMacro(Procedure paramProcedure, boolean paramBoolean) {}
  
  public Procedure getProcedure()
  {
    return this.procedure;
  }
  
  public boolean isNonTerminating()
  {
    return this.kind == 6;
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    Object localObject = paramLexer.getPort();
    try
    {
      localObject = this.procedure.apply2(localObject, Char.make(paramInt1));
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


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ReaderMacro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */