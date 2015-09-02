package gnu.kawa.lispexpr;

import gnu.bytecode.Type;
import gnu.expr.Keyword;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.util.GeneralHashTable;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.util.regex.Pattern;

public class ReaderDispatchMisc
  extends ReadTableEntry
{
  private static ReaderDispatchMisc instance = new ReaderDispatchMisc();
  protected int code;
  
  public ReaderDispatchMisc()
  {
    this.code = -1;
  }
  
  public ReaderDispatchMisc(int paramInt)
  {
    this.code = paramInt;
  }
  
  public static ReaderDispatchMisc getInstance()
  {
    return instance;
  }
  
  public static Pattern readRegex(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    int k = paramLexer.tokenBufferLength;
    LineBufferedReader localLineBufferedReader = paramLexer.getPort();
    char c = '\000';
    int j = 0;
    if ((localLineBufferedReader instanceof InPort))
    {
      c = ((InPort)localLineBufferedReader).readState;
      ((InPort)localLineBufferedReader).readState = '/';
    }
    for (;;)
    {
      try
      {
        paramInt2 = localLineBufferedReader.read();
        if (paramInt2 < 0) {
          paramLexer.eofError("unexpected EOF in regex literal");
        }
        if (paramInt2 != paramInt1) {
          break label145;
        }
        String str1 = new String(paramLexer.tokenBuffer, k, paramLexer.tokenBufferLength - k);
        paramInt1 = j;
        paramInt2 = paramLexer.peek();
        if (paramInt2 == 105) {
          break label327;
        }
        if (paramInt2 != 73) {
          break label335;
        }
      }
      finally
      {
        paramLexer.tokenBufferLength = k;
        if (!(localLineBufferedReader instanceof InPort)) {
          continue;
        }
        ((InPort)localLineBufferedReader).readState = c;
      }
      paramLexer.skip();
      continue;
      label145:
      int i = paramInt2;
      if (paramInt2 == 92)
      {
        i = localLineBufferedReader.read();
        if ((i != 32) && (i != 9) && (i != 13))
        {
          paramInt2 = i;
          if (i != 10) {}
        }
        else
        {
          paramInt2 = i;
          if ((paramLexer instanceof LispReader))
          {
            paramInt2 = ((LispReader)paramLexer).readEscape(i);
            if (paramInt2 == -2) {
              continue;
            }
          }
        }
        if (paramInt2 < 0) {
          paramLexer.eofError("unexpected EOF in regex literal");
        }
        i = paramInt2;
        if (paramInt2 != paramInt1)
        {
          paramLexer.tokenBufferAppend(92);
          i = paramInt2;
        }
      }
      paramLexer.tokenBufferAppend(i);
      continue;
      label327:
      label335:
      do
      {
        if (Character.isLetter(paramInt2))
        {
          paramLexer.error("unrecognized regex option '" + (char)paramInt2 + '\'');
          break;
        }
        Pattern localPattern = Pattern.compile(str2, paramInt1);
        paramLexer.tokenBufferLength = k;
        if ((localLineBufferedReader instanceof InPort)) {
          ((InPort)localLineBufferedReader).readState = c;
        }
        return localPattern;
        paramInt1 |= 0x42;
        break;
        if ((paramInt2 == 115) || (paramInt2 == 83))
        {
          paramInt1 |= 0x20;
          break;
        }
      } while ((paramInt2 != 109) && (paramInt2 != 77));
      paramInt1 |= 0x8;
    }
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    Object localObject1 = (LispReader)paramLexer;
    char c2 = '\000';
    char c1 = '\000';
    if (this.code >= 0) {
      paramInt1 = this.code;
    }
    switch (paramInt1)
    {
    default: 
      paramLexer.error("An invalid #-construct was read.");
      localObject1 = Values.empty;
    }
    Object localObject6;
    do
    {
      do
      {
        return localObject1;
        paramInt1 = ((LispReader)localObject1).tokenBufferLength;
        ((LispReader)localObject1).readToken(((LispReader)localObject1).read(), 'P', ReadTable.getCurrent());
        paramInt2 = ((LispReader)localObject1).tokenBufferLength;
        paramLexer = new String(((LispReader)localObject1).tokenBuffer, paramInt1, paramInt2 - paramInt1);
        ((LispReader)localObject1).tokenBufferLength = paramInt1;
        return Keyword.make(paramLexer.intern());
        return LispReader.readCharacter((LispReader)localObject1);
        return LispReader.readSpecial((LispReader)localObject1);
        return Boolean.TRUE;
        if (Character.isDigit((char)paramLexer.peek())) {
          return LispReader.readSimpleVector((LispReader)localObject1, 'F');
        }
        return Boolean.FALSE;
        return LispReader.readSimpleVector((LispReader)localObject1, (char)paramInt1);
        paramInt1 = paramInt2;
        if (paramInt2 > 36)
        {
          paramLexer.error("the radix " + paramInt2 + " is too big (max is 36)");
          paramInt1 = 36;
        }
        return LispReader.readNumberWithRadix(0, (LispReader)localObject1, paramInt1);
        return LispReader.readNumberWithRadix(0, (LispReader)localObject1, 16);
        return LispReader.readNumberWithRadix(0, (LispReader)localObject1, 10);
        return LispReader.readNumberWithRadix(0, (LispReader)localObject1, 8);
        return LispReader.readNumberWithRadix(0, (LispReader)localObject1, 2);
        ((LispReader)localObject1).tokenBufferAppend(35);
        ((LispReader)localObject1).tokenBufferAppend(paramInt1);
        return LispReader.readNumberWithRadix(2, (LispReader)localObject1, 0);
        return readRegex(paramLexer, paramInt1, paramInt2);
        paramLexer = ((LispReader)localObject1).getPort();
        if ((paramLexer instanceof InPort))
        {
          c1 = ((InPort)paramLexer).readState;
          ((InPort)paramLexer).readState = '|';
        }
        try
        {
          ((LispReader)localObject1).readNestedComment('#', '|');
          return Values.empty;
        }
        finally
        {
          if ((paramLexer instanceof InPort)) {
            ((InPort)paramLexer).readState = c1;
          }
        }
        paramLexer = ((LispReader)localObject2).getPort();
        c1 = c2;
        if ((paramLexer instanceof InPort))
        {
          c1 = ((InPort)paramLexer).readState;
          ((InPort)paramLexer).readState = ';';
        }
        try
        {
          ((LispReader)localObject2).readObject();
          return Values.empty;
        }
        finally
        {
          if ((paramLexer instanceof InPort)) {
            ((InPort)paramLexer).readState = c1;
          }
        }
        Object localObject4;
        if (((LispReader)localObject3).getPort().peek() == 40)
        {
          localObject4 = ((LispReader)localObject3).readObject();
          paramInt1 = LList.listLength(localObject4, false);
          if ((paramInt1 > 0) && ((((Pair)localObject4).getCar() instanceof Symbol)))
          {
            localObject6 = ((Pair)localObject4).getCar().toString();
            localObject7 = ReadTable.getCurrent().getReaderCtor((String)localObject6);
            if (localObject7 == null) {
              paramLexer.error("unknown reader constructor " + (String)localObject6);
            }
          }
        }
        for (;;)
        {
          return Boolean.FALSE;
          if ((!(localObject7 instanceof Procedure)) && (!(localObject7 instanceof Type)))
          {
            paramLexer.error("reader constructor must be procedure or type name");
          }
          else
          {
            int i = paramInt1 - 1;
            if ((localObject7 instanceof Type)) {}
            Object[] arrayOfObject;
            for (paramInt1 = 1;; paramInt1 = 0)
            {
              arrayOfObject = new Object[paramInt1 + i];
              localObject4 = ((Pair)localObject4).getCdr();
              paramInt2 = 0;
              while (paramInt2 < i)
              {
                localObject4 = (Pair)localObject4;
                arrayOfObject[(paramInt1 + paramInt2)] = ((Pair)localObject4).getCar();
                localObject4 = ((Pair)localObject4).getCdr();
                paramInt2 += 1;
              }
            }
            if (paramInt1 > 0) {
              arrayOfObject[0] = localObject7;
            }
            try
            {
              return Invoke.make.applyN(arrayOfObject);
            }
            catch (Throwable localThrowable)
            {
              paramLexer.error("caught " + localThrowable + " applying reader constructor " + (String)localObject6);
            }
            localObject4 = ((Procedure)localObject7).applyN(arrayOfObject);
            return localObject4;
            continue;
            paramLexer.error("a non-empty list starting with a symbol must follow #,");
          }
        }
        localObject6 = localThrowable.readObject();
        localObject5 = localObject6;
      } while (!(paramLexer instanceof LispReader));
      Object localObject7 = (LispReader)paramLexer;
      Object localObject5 = ((LispReader)localObject7).sharedStructureTable;
      paramLexer = (Lexer)localObject5;
      if (localObject5 == null)
      {
        paramLexer = new GeneralHashTable();
        ((LispReader)localObject7).sharedStructureTable = paramLexer;
      }
      paramLexer.put(Integer.valueOf(paramInt2), localObject6);
      return localObject6;
      if (!(paramLexer instanceof LispReader)) {
        break;
      }
      localObject5 = ((LispReader)paramLexer).sharedStructureTable;
      if (localObject5 == null) {
        break;
      }
      localObject6 = ((GeneralHashTable)localObject5).get(Integer.valueOf(paramInt2), paramLexer);
      localObject5 = localObject6;
    } while (localObject6 != paramLexer);
    paramLexer.error("an unrecognized #n# back-reference was read");
    return Values.empty;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ReaderDispatchMisc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */