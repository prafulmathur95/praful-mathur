package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderColon
  extends ReadTableEntry
{
  public int getKind()
  {
    return 6;
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    paramLexer = (LispReader)paramLexer;
    ReadTable localReadTable = ReadTable.getCurrent();
    int i = paramLexer.tokenBufferLength;
    paramInt2 = paramInt1;
    if (paramInt1 == localReadTable.postfixLookupOperator)
    {
      paramInt2 = paramLexer.read();
      if (paramInt2 == 58) {
        return localReadTable.makeSymbol("::");
      }
      paramLexer.tokenBufferAppend(paramInt1);
    }
    return paramLexer.readAndHandleToken(paramInt2, i, localReadTable);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ReaderColon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */