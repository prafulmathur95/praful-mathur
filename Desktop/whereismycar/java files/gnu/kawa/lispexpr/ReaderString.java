package gnu.kawa.lispexpr;

import gnu.mapping.InPort;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderString
  extends ReadTableEntry
{
  public static String readString(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    int j = paramLexer.tokenBufferLength;
    LineBufferedReader localLineBufferedReader = paramLexer.getPort();
    char c = '\000';
    int i = paramInt1;
    paramInt2 = i;
    if ((localLineBufferedReader instanceof InPort))
    {
      c = ((InPort)localLineBufferedReader).readState;
      ((InPort)localLineBufferedReader).readState = ((char)paramInt1);
      paramInt2 = i;
    }
    for (;;)
    {
      if (paramInt2 == 13) {}
      for (;;)
      {
        try
        {
          i = localLineBufferedReader.read();
          paramInt2 = i;
          if (i == 10) {
            break;
          }
          paramInt2 = i;
          if (paramInt2 != paramInt1) {
            break label285;
          }
          localObject1 = new String(paramLexer.tokenBuffer, j, paramLexer.tokenBufferLength - j).intern();
          return (String)localObject1;
        }
        finally
        {
          Object localObject1;
          paramLexer.tokenBufferLength = j;
          if (!(localLineBufferedReader instanceof InPort)) {
            continue;
          }
          ((InPort)localLineBufferedReader).readState = c;
        }
        if ((localLineBufferedReader.pos < localLineBufferedReader.limit) && (paramInt2 != 10))
        {
          localObject1 = localLineBufferedReader.buffer;
          paramInt2 = localLineBufferedReader.pos;
          localLineBufferedReader.pos = (paramInt2 + 1);
          paramInt2 = localObject1[paramInt2];
        }
        else
        {
          paramInt2 = localLineBufferedReader.read();
        }
      }
      label189:
      label285:
      label325:
      do
      {
        if (paramInt2 < 0) {
          paramLexer.eofError("unexpected EOF in string literal");
        }
        paramLexer.tokenBufferAppend(paramInt2);
        break;
        if (localLineBufferedReader.getConvertCR()) {
          i = 10;
        }
        for (;;)
        {
          paramLexer.tokenBufferAppend(i);
          break;
          if ((paramLexer instanceof LispReader))
          {
            i = ((LispReader)paramLexer).readEscape();
            break label325;
          }
          i = localLineBufferedReader.read();
          break label325;
          switch (paramInt2)
          {
          }
          break label189;
          i = 13;
          paramInt2 = 32;
        }
        paramInt2 = i;
      } while (i != -2);
      paramInt2 = 10;
    }
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    return readString(paramLexer, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ReaderString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */