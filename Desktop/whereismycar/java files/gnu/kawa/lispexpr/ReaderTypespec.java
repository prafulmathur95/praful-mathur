package gnu.kawa.lispexpr;

import gnu.mapping.InPort;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderTypespec
  extends ReadTableEntry
{
  public int getKind()
  {
    return 6;
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    int j = paramLexer.tokenBufferLength;
    LineBufferedReader localLineBufferedReader = paramLexer.getPort();
    ReadTable localReadTable = ReadTable.getCurrent();
    char c = '\000';
    paramLexer.tokenBufferAppend(paramInt1);
    paramInt2 = paramInt1;
    if ((localLineBufferedReader instanceof InPort))
    {
      c = ((InPort)localLineBufferedReader).readState;
      ((InPort)localLineBufferedReader).readState = ((char)paramInt1);
    }
    paramInt1 = 0;
    for (;;)
    {
      try
      {
        if ((localLineBufferedReader.pos < localLineBufferedReader.limit) && (paramInt2 != 10))
        {
          char[] arrayOfChar = localLineBufferedReader.buffer;
          paramInt2 = localLineBufferedReader.pos;
          localLineBufferedReader.pos = (paramInt2 + 1);
          paramInt2 = arrayOfChar[paramInt2];
          if (paramInt2 != 92) {
            break label251;
          }
          if ((paramLexer instanceof LispReader)) {
            paramInt2 = ((LispReader)paramLexer).readEscape();
          }
        }
        else
        {
          paramInt2 = localLineBufferedReader.read();
          continue;
        }
        paramInt2 = localLineBufferedReader.read();
        continue;
        if (localReadTable.lookup(paramInt2).getKind() == 2)
        {
          paramLexer.tokenBufferAppend(paramInt2);
          continue;
        }
        paramLexer.unread(paramInt2);
      }
      finally
      {
        paramLexer.tokenBufferLength = j;
        if ((localLineBufferedReader instanceof InPort)) {
          ((InPort)localLineBufferedReader).readState = c;
        }
      }
      String str = new String(paramLexer.tokenBuffer, j, paramLexer.tokenBufferLength - j).intern();
      paramLexer.tokenBufferLength = j;
      if ((localLineBufferedReader instanceof InPort)) {
        ((InPort)localLineBufferedReader).readState = c;
      }
      return str;
      label251:
      int i = paramInt1;
      if (paramInt1 == 0)
      {
        i = paramInt1;
        if (paramInt2 == 91)
        {
          i = 1;
          paramInt1 = 1;
          if (1 == 1) {
            continue;
          }
        }
      }
      paramInt1 = i;
      if (i != 0)
      {
        paramInt1 = i;
        if (paramInt2 == 93)
        {
          paramInt1 = 0;
          i = 0;
          if (0 != 0) {
            paramInt1 = i;
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ReaderTypespec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */