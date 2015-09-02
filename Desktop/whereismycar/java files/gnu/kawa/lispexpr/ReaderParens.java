package gnu.kawa.lispexpr;

import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderParens
  extends ReadTableEntry
{
  private static ReaderParens instance;
  char close;
  Object command;
  int kind;
  char open;
  
  public ReaderParens(char paramChar1, char paramChar2, int paramInt, Object paramObject)
  {
    this.open = paramChar1;
    this.close = paramChar2;
    this.kind = paramInt;
    this.command = paramObject;
  }
  
  public static ReaderParens getInstance(char paramChar1, char paramChar2)
  {
    return getInstance(paramChar1, paramChar2, 5);
  }
  
  public static ReaderParens getInstance(char paramChar1, char paramChar2, int paramInt)
  {
    if ((paramChar1 == '(') && (paramChar2 == ')') && (paramInt == 5))
    {
      if (instance == null) {
        instance = new ReaderParens(paramChar1, paramChar2, paramInt, null);
      }
      return instance;
    }
    return new ReaderParens(paramChar1, paramChar2, paramInt, null);
  }
  
  public static ReaderParens getInstance(char paramChar1, char paramChar2, int paramInt, Object paramObject)
  {
    if (paramObject == null) {
      return getInstance(paramChar1, paramChar2, paramInt);
    }
    return new ReaderParens(paramChar1, paramChar2, paramInt, paramObject);
  }
  
  public static Object readList(LispReader paramLispReader, int paramInt1, int paramInt2, int paramInt3)
    throws IOException, SyntaxException
  {
    LineBufferedReader localLineBufferedReader = paramLispReader.getPort();
    char c;
    int m;
    int i1;
    Object localObject4;
    if (paramInt3 == 93)
    {
      c = '[';
      c = paramLispReader.pushNesting(c);
      m = localLineBufferedReader.getLineNumber();
      i1 = localLineBufferedReader.getColumnNumber();
      localObject4 = null;
    }
    int n;
    int i;
    int k;
    int j;
    Object localObject5;
    label240:
    Object localObject3;
    Object localObject6;
    for (;;)
    {
      ReadTable localReadTable;
      try
      {
        Object localObject1 = paramLispReader.makeNil();
        paramInt2 = 0;
        paramInt1 = 0;
        localReadTable = ReadTable.getCurrent();
        n = localLineBufferedReader.getLineNumber();
        i = localLineBufferedReader.getColumnNumber();
        k = localLineBufferedReader.read();
        if (k == paramInt3)
        {
          return localObject1;
          c = '(';
          break;
        }
        if (k < 0) {
          paramLispReader.eofError("unexpected EOF in list starting here", m + 1, i1);
        }
        if (k != 46) {
          break label311;
        }
        j = localLineBufferedReader.peek();
        localObject5 = localReadTable.lookup(j);
        k = ((ReadTableEntry)localObject5).getKind();
        if ((k != 1) && (k != 5) && (k != 0)) {
          break label293;
        }
        localLineBufferedReader.skip();
        i += 1;
        if (j == paramInt3)
        {
          paramLispReader.error("unexpected '" + (char)paramInt3 + "' after '.'");
          continue;
        }
        if (j >= 0) {
          break label240;
        }
      }
      finally
      {
        paramLispReader.popNesting(c);
      }
      paramLispReader.eofError("unexpected EOF in list starting here", m + 1, i1);
      if (paramInt2 == 0) {
        break label399;
      }
      paramLispReader.error("multiple '.' in list");
      paramInt1 = 0;
      localObject3 = paramLispReader.makeNil();
      localObject4 = null;
      break label399;
      for (;;)
      {
        localObject4 = paramLispReader.readValues(paramInt2, (ReadTableEntry)localObject6, localReadTable);
        if (localObject4 != Values.empty) {
          break label333;
        }
        localObject4 = localObject5;
        paramInt2 = j;
        break;
        label293:
        k = 46;
        localObject5 = ReadTableEntry.getConstituentInstance();
        j = paramInt2;
        paramInt2 = k;
        break label409;
        label311:
        localObject6 = localReadTable.lookup(k);
        localObject5 = localObject4;
        j = paramInt2;
        paramInt2 = k;
      }
      label333:
      localObject4 = paramLispReader.handlePostfix(localObject4, localReadTable, n, i);
      if (paramInt1 == 0) {
        break label420;
      }
      paramLispReader.error("multiple values after '.'");
      localObject4 = null;
      localObject3 = paramLispReader.makeNil();
      paramInt1 = 0;
      paramInt2 = j;
    }
    for (;;)
    {
      localObject4 = paramLispReader.makePair(localObject4, paramInt2, i);
      label399:
      label409:
      label420:
      while (localObject5 != null)
      {
        paramLispReader.setCdr(localObject5, localObject4);
        break label436;
        k = 1;
        paramInt2 = j;
        j = k;
        localObject6 = localObject5;
        localObject5 = localObject4;
        break;
        if (j == 0) {
          break label442;
        }
        paramInt1 = 1;
      }
      localObject3 = localObject4;
      label436:
      paramInt2 = j;
      break;
      label442:
      paramInt2 = n;
      if (localObject5 == null)
      {
        paramInt2 = m;
        i = i1 - 1;
      }
    }
  }
  
  public int getKind()
  {
    return this.kind;
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    Object localObject2 = readList((LispReader)paramLexer, paramInt1, paramInt2, this.close);
    Object localObject1 = localObject2;
    if (this.command != null)
    {
      localObject1 = paramLexer.getPort();
      paramInt1 = ((LineBufferedReader)localObject1).getLineNumber();
      paramInt2 = ((LineBufferedReader)localObject1).getColumnNumber();
      localObject1 = ((LispReader)paramLexer).makePair(this.command, paramInt1, paramInt2);
      ((LispReader)paramLexer).setCdr(localObject1, localObject2);
    }
    return localObject1;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ReaderParens.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */