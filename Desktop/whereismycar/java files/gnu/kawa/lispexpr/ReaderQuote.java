package gnu.kawa.lispexpr;

import gnu.lists.PairWithPosition;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderQuote
  extends ReadTableEntry
{
  Object magicSymbol;
  Object magicSymbol2;
  char next;
  
  public ReaderQuote(Object paramObject)
  {
    this.magicSymbol = paramObject;
  }
  
  public ReaderQuote(Object paramObject1, char paramChar, Object paramObject2)
  {
    this.next = paramChar;
    this.magicSymbol = paramObject1;
    this.magicSymbol2 = paramObject2;
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    LispReader localLispReader = (LispReader)paramLexer;
    String str = localLispReader.getName();
    paramInt1 = localLispReader.getLineNumber();
    paramInt2 = localLispReader.getColumnNumber();
    Object localObject = this.magicSymbol;
    paramLexer = (Lexer)localObject;
    int i;
    if (this.next != 0)
    {
      i = localLispReader.read();
      if (i != this.next) {
        break label112;
      }
      paramLexer = this.magicSymbol2;
    }
    for (;;)
    {
      i = localLispReader.getLineNumber();
      int j = localLispReader.getColumnNumber();
      return PairWithPosition.make(paramLexer, PairWithPosition.make(localLispReader.readObject(), localLispReader.makeNil(), str, i + 1, j + 1), str, paramInt1 + 1, paramInt2 + 1);
      label112:
      paramLexer = (Lexer)localObject;
      if (i >= 0)
      {
        localLispReader.unread(i);
        paramLexer = (Lexer)localObject;
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ReaderQuote.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */