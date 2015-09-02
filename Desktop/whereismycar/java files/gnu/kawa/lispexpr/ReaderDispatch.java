package gnu.kawa.lispexpr;

import gnu.kawa.util.RangeTable;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderDispatch
  extends ReadTableEntry
{
  int kind;
  RangeTable table = new RangeTable();
  
  public ReaderDispatch()
  {
    this.kind = 5;
  }
  
  public ReaderDispatch(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 6;; i = 5)
    {
      this.kind = i;
      return;
    }
  }
  
  public static ReaderDispatch create(ReadTable paramReadTable)
  {
    ReaderDispatch localReaderDispatch = new ReaderDispatch();
    ReaderDispatchMisc localReaderDispatchMisc = ReaderDispatchMisc.getInstance();
    localReaderDispatch.set(58, localReaderDispatchMisc);
    localReaderDispatch.set(66, localReaderDispatchMisc);
    localReaderDispatch.set(68, localReaderDispatchMisc);
    localReaderDispatch.set(69, localReaderDispatchMisc);
    localReaderDispatch.set(70, localReaderDispatchMisc);
    localReaderDispatch.set(73, localReaderDispatchMisc);
    localReaderDispatch.set(79, localReaderDispatchMisc);
    localReaderDispatch.set(82, localReaderDispatchMisc);
    localReaderDispatch.set(83, localReaderDispatchMisc);
    localReaderDispatch.set(84, localReaderDispatchMisc);
    localReaderDispatch.set(85, localReaderDispatchMisc);
    localReaderDispatch.set(88, localReaderDispatchMisc);
    localReaderDispatch.set(124, localReaderDispatchMisc);
    localReaderDispatch.set(59, localReaderDispatchMisc);
    localReaderDispatch.set(33, localReaderDispatchMisc);
    localReaderDispatch.set(92, localReaderDispatchMisc);
    localReaderDispatch.set(61, localReaderDispatchMisc);
    localReaderDispatch.set(35, localReaderDispatchMisc);
    localReaderDispatch.set(47, localReaderDispatchMisc);
    localReaderDispatch.set(39, new ReaderQuote(paramReadTable.makeSymbol("function")));
    localReaderDispatch.set(40, new ReaderVector(')'));
    localReaderDispatch.set(60, new ReaderXmlElement());
    return localReaderDispatch;
  }
  
  public int getKind()
  {
    return this.kind;
  }
  
  public ReadTableEntry lookup(int paramInt)
  {
    return (ReadTableEntry)this.table.lookup(paramInt, null);
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    paramInt1 = -1;
    paramInt2 = paramLexer.read();
    if (paramInt2 < 0) {
      paramLexer.eofError("unexpected EOF after " + (char)paramInt2);
    }
    if (paramInt2 > 65536) {}
    ReadTableEntry localReadTableEntry;
    int i;
    for (;;)
    {
      localReadTableEntry = (ReadTableEntry)this.table.lookup(paramInt2, null);
      if (localReadTableEntry != null) {
        break label153;
      }
      paramLexer.error('e', paramLexer.getName(), paramLexer.getLineNumber() + 1, paramLexer.getColumnNumber(), "invalid dispatch character '" + (char)paramInt2 + '\'');
      return Values.empty;
      i = Character.digit((char)paramInt2, 10);
      if (i >= 0) {
        break;
      }
      paramInt2 = Character.toUpperCase((char)paramInt2);
    }
    if (paramInt1 < 0) {}
    for (paramInt1 = i;; paramInt1 = paramInt1 * 10 + i) {
      break;
    }
    label153:
    return localReadTableEntry.read(paramLexer, paramInt2, paramInt1);
  }
  
  public void set(int paramInt, Object paramObject)
  {
    this.table.set(paramInt, paramInt, paramObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ReaderDispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */