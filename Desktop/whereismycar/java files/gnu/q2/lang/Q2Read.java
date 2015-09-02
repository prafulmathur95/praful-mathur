package gnu.q2.lang;

import gnu.expr.Keyword;
import gnu.expr.QuoteExp;
import gnu.kawa.lispexpr.LispReader;
import gnu.kawa.xml.MakeAttribute;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.text.LineBufferedReader;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import kawa.standard.begin;

public class Q2Read
  extends LispReader
{
  int curIndentation;
  int expressionStartColumn;
  String expressionStartFile;
  int expressionStartLine;
  
  public Q2Read(InPort paramInPort)
  {
    super(paramInPort);
    init();
  }
  
  public Q2Read(InPort paramInPort, SourceMessages paramSourceMessages)
  {
    super(paramInPort, paramSourceMessages);
    init();
  }
  
  public static Object readObject(InPort paramInPort)
    throws IOException, SyntaxException
  {
    return new Q2Read(paramInPort).readObject();
  }
  
  void init()
  {
    ((InPort)this.port).readState = ' ';
  }
  
  public Object readCommand()
    throws IOException, SyntaxException
  {
    int i = skipIndentation();
    Object localObject1;
    if (i < 0) {
      localObject1 = Sequence.eofValue;
    }
    Object localObject2;
    do
    {
      return localObject1;
      this.curIndentation = i;
      localObject2 = readIndentCommand();
      localObject1 = localObject2;
    } while (this.interactive);
    this.port.reset();
    return localObject2;
  }
  
  public Object readCommand(boolean paramBoolean)
    throws IOException, SyntaxException
  {
    this.port.getLineNumber();
    int n = this.port.getColumnNumber();
    int j = n;
    Object localObject3 = LList.Empty;
    Object localObject2 = null;
    Object localObject1 = null;
    int k = read();
    Object localObject5;
    label44:
    Object localObject4;
    if (k < 0)
    {
      localObject5 = localObject1;
      localObject4 = localObject3;
      if (!paramBoolean)
      {
        if (localObject3 != localObject5) {
          break label511;
        }
        localObject4 = ((PairWithPosition)localObject5).getCar();
      }
    }
    label211:
    label286:
    label427:
    label443:
    label511:
    do
    {
      return localObject4;
      if ((k == 32) || (k == 9)) {
        break;
      }
      unread();
      localObject5 = localObject1;
      if (k == 41) {
        break label44;
      }
      int i1 = this.port.getLineNumber();
      int i = this.port.getColumnNumber();
      for (;;)
      {
        if ((k == 13) || (k == 10))
        {
          localObject4 = localObject3;
          if (singleLine()) {
            break;
          }
          read();
          skipIndentation();
          int m = this.port.getColumnNumber();
          k = peek();
          i = m;
          if (m <= n) {
            i = m;
          }
        }
      }
      if (i <= n)
      {
        localObject5 = localObject1;
        if (localObject1 != null) {
          break label44;
        }
      }
      if ((i == j) && (localObject1 != null))
      {
        localObject4 = readCommand();
        localObject5 = localObject1;
        if (localObject4 == Sequence.eofValue) {
          break label44;
        }
        j = i;
        localObject5 = this.port.getName();
        localObject4 = PairWithPosition.make(localObject4, LList.Empty, (String)localObject5, i1 + 1, i + 1);
        if (localObject1 != null) {
          break label443;
        }
        localObject2 = localObject4;
        localObject3 = localObject4;
      }
      for (;;)
      {
        localObject1 = localObject4;
        break;
        if ((i < j) && (localObject1 != null))
        {
          localObject4 = localObject2;
          for (;;)
          {
            localObject5 = ((PairWithPosition)localObject4).getCdr();
            if (localObject5 == LList.Empty) {
              localObject5 = localObject1;
            }
            PairWithPosition localPairWithPosition;
            for (;;)
            {
              localObject4 = readCommand();
              localObject1 = localObject5;
              break;
              localPairWithPosition = (PairWithPosition)localObject5;
              j = localPairWithPosition.getColumnNumber() - 1;
              if (j < i) {
                break label427;
              }
              if (j > i) {
                error('e', "some tokens on previous line indented more than current line");
              }
              Object localObject6 = localPairWithPosition.getCdr();
              localObject5 = localObject1;
              if (localObject6 != LList.Empty)
              {
                if (((PairWithPosition)localObject6).getColumnNumber() - 1 == i)
                {
                  localObject4 = (PairWithPosition)localObject6;
                  break label286;
                }
                localObject5 = (PairWithPosition)makePair(localPairWithPosition, this.port.getLineNumber(), i);
                ((PairWithPosition)localObject4).setCdrBackdoor(localObject5);
              }
            }
            localObject4 = localPairWithPosition;
          }
        }
        localObject4 = readObject();
        break label211;
        if ((((PairWithPosition)localObject1).getCar() instanceof Keyword))
        {
          localObject5 = new QuoteExp(((Keyword)((PairWithPosition)localObject1).getCar()).getName());
          ((PairWithPosition)localObject1).setCar(new PairWithPosition((SourceLocator)localObject1, MakeAttribute.makeAttribute, new PairWithPosition((SourceLocator)localObject1, localObject5, localObject4)));
          break;
        }
        ((PairWithPosition)localObject1).setCdrBackdoor(localObject4);
      }
      localObject4 = localObject3;
    } while (localObject5 != null);
    return QuoteExp.voidExp;
  }
  
  Object readIndentCommand()
    throws IOException, SyntaxException
  {
    int i = this.curIndentation;
    Object localObject1 = LList.Empty;
    Object localObject2 = LList.Empty;
    for (;;)
    {
      int j = read();
      if (j < 0) {
        localObject2 = localObject1;
      }
      do
      {
        do
        {
          return LList.reverseInPlace(localObject2);
          if ((j == 32) || (j == 9)) {
            break;
          }
          unread();
          localObject2 = localObject1;
        } while (j == 41);
        if ((j != 13) && (j != 10)) {
          break label254;
        }
        localObject2 = localObject1;
      } while (singleLine());
      read();
      this.port.mark(Integer.MAX_VALUE);
      j = skipIndentation();
      Object localObject3 = LList.Empty;
      this.curIndentation = j;
      for (;;)
      {
        if (this.curIndentation == -1) {}
        label214:
        do
        {
          for (;;)
          {
            localObject2 = localObject1;
            if (localObject3 == LList.Empty) {
              break;
            }
            localObject2 = new Pair(new Pair(begin.begin, LList.reverseInPlace(localObject3)), localObject1);
            break;
            if (j == this.curIndentation)
            {
              k = Q2.compareIndentation(j, i);
              if (k == Integer.MIN_VALUE)
              {
                error('e', "cannot compare indentation - mix of tabs and spaces");
              }
              else
              {
                if ((k != -1) && (k != 1)) {
                  break label214;
                }
                error('e', "indentation must differ by 2 or more");
              }
            }
          }
        } while (k <= 0);
        k = this.port.getLineNumber();
        int m = this.port.getColumnNumber();
        localObject3 = makePair(readIndentCommand(), localObject3, k, m);
      }
      label254:
      j = this.port.getLineNumber();
      int k = this.port.getColumnNumber();
      localObject1 = makePair(readObject(), localObject1, j, k);
    }
  }
  
  void saveExpressionStartPosition()
  {
    this.expressionStartFile = this.port.getName();
    this.expressionStartLine = this.port.getLineNumber();
    this.expressionStartColumn = this.port.getColumnNumber();
  }
  
  boolean singleLine()
  {
    return (this.interactive) && (this.nesting == 0);
  }
  
  int skipIndentation()
    throws IOException, SyntaxException
  {
    int j = 0;
    int n = 0;
    int k;
    int m;
    for (int i = this.port.read();; i = this.port.read())
    {
      k = i;
      m = n;
      if (i != 9) {
        break;
      }
      j += 1;
    }
    while (k == 32)
    {
      m += 1;
      k = this.port.read();
    }
    if (k < 0) {
      return -1;
    }
    this.port.unread();
    return (j << 16) + m;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\q2\lang\Q2Read.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */