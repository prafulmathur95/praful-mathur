package gnu.text;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

public class Lexer
  extends Reader
{
  protected boolean interactive;
  SourceMessages messages = null;
  protected int nesting;
  protected LineBufferedReader port;
  private int saveTokenBufferLength = -1;
  public char[] tokenBuffer = new char[100];
  public int tokenBufferLength = 0;
  
  public Lexer(LineBufferedReader paramLineBufferedReader)
  {
    this.port = paramLineBufferedReader;
  }
  
  public Lexer(LineBufferedReader paramLineBufferedReader, SourceMessages paramSourceMessages)
  {
    this.port = paramLineBufferedReader;
    this.messages = paramSourceMessages;
  }
  
  public static long readDigitsInBuffer(LineBufferedReader paramLineBufferedReader, int paramInt)
  {
    long l1 = 0L;
    int i = 0;
    long l3 = Long.MAX_VALUE / paramInt;
    int j = paramLineBufferedReader.pos;
    int k = j;
    if (j >= paramLineBufferedReader.limit) {
      return 0L;
    }
    j = Character.digit(paramLineBufferedReader.buffer[k], paramInt);
    if (j < 0)
    {
      label47:
      paramLineBufferedReader.pos = k;
      if (i != 0) {
        return -1L;
      }
    }
    else
    {
      if (l1 > l3) {
        i = 1;
      }
      for (long l2 = l1;; l2 = paramInt * l1 + j)
      {
        j = i;
        if (l2 < 0L) {
          j = 1;
        }
        int m = k + 1;
        k = m;
        i = j;
        l1 = l2;
        if (m < paramLineBufferedReader.limit) {
          break;
        }
        k = m;
        i = j;
        l1 = l2;
        break label47;
      }
    }
    return l1;
  }
  
  public boolean checkErrors(PrintWriter paramPrintWriter, int paramInt)
  {
    return (this.messages != null) && (this.messages.checkErrors(paramPrintWriter, paramInt));
  }
  
  public boolean checkNext(char paramChar)
    throws IOException
  {
    char c = this.port.read();
    if (c == paramChar) {
      return true;
    }
    if (c >= 0) {
      this.port.unread_quick();
    }
    return false;
  }
  
  public void clearErrors()
  {
    if (this.messages != null) {
      this.messages.clearErrors();
    }
  }
  
  public void close()
    throws IOException
  {
    this.port.close();
  }
  
  public void eofError(String paramString)
    throws SyntaxException
  {
    fatal(paramString);
  }
  
  public void eofError(String paramString, int paramInt1, int paramInt2)
    throws SyntaxException
  {
    error('f', this.port.getName(), paramInt1, paramInt2, paramString);
    throw new SyntaxException(this.messages);
  }
  
  public void error(char paramChar, String paramString)
  {
    int j = this.port.getLineNumber();
    int i = this.port.getColumnNumber();
    String str = this.port.getName();
    if (i >= 0) {
      i += 1;
    }
    for (;;)
    {
      error(paramChar, str, j + 1, i, paramString);
      return;
      i = 0;
    }
  }
  
  public void error(char paramChar, String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    if (this.messages == null) {
      this.messages = new SourceMessages();
    }
    this.messages.error(paramChar, paramString1, paramInt1, paramInt2, paramString2);
  }
  
  public void error(String paramString)
  {
    error('e', paramString);
  }
  
  public void fatal(String paramString)
    throws SyntaxException
  {
    error('f', paramString);
    throw new SyntaxException(this.messages);
  }
  
  public int getColumnNumber()
  {
    return this.port.getColumnNumber();
  }
  
  public SourceError getErrors()
  {
    if (this.messages == null) {
      return null;
    }
    return this.messages.getErrors();
  }
  
  public int getLineNumber()
  {
    return this.port.getLineNumber();
  }
  
  public SourceMessages getMessages()
  {
    return this.messages;
  }
  
  public String getName()
  {
    return this.port.getName();
  }
  
  public final LineBufferedReader getPort()
  {
    return this.port;
  }
  
  public boolean isInteractive()
  {
    return this.interactive;
  }
  
  public void mark()
    throws IOException
  {
    if (this.saveTokenBufferLength >= 0) {
      throw new Error("internal error: recursive call to mark not allowed");
    }
    this.port.mark(Integer.MAX_VALUE);
    this.saveTokenBufferLength = this.tokenBufferLength;
  }
  
  public int peek()
    throws IOException
  {
    return this.port.peek();
  }
  
  public void popNesting(char paramChar)
  {
    getPort().readState = paramChar;
    this.nesting -= 1;
  }
  
  public char pushNesting(char paramChar)
  {
    this.nesting += 1;
    LineBufferedReader localLineBufferedReader = getPort();
    char c = localLineBufferedReader.readState;
    localLineBufferedReader.readState = paramChar;
    return c;
  }
  
  public int read()
    throws IOException
  {
    return this.port.read();
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    return this.port.read(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public boolean readDelimited(String paramString)
    throws IOException, SyntaxException
  {
    this.tokenBufferLength = 0;
    int k = paramString.length();
    int m = paramString.charAt(k - 1);
    for (;;)
    {
      int n = read();
      if (n < 0) {
        return false;
      }
      if (n == m)
      {
        int j = this.tokenBufferLength;
        int i = k - 1;
        int i1 = j - i;
        if (i1 >= 0) {
          do
          {
            if (i == 0)
            {
              this.tokenBufferLength = i1;
              return true;
            }
            j = i - 1;
            i = j;
          } while (this.tokenBuffer[(i1 + j)] == paramString.charAt(j));
        }
      }
      tokenBufferAppend((char)n);
    }
  }
  
  public int readOptionalExponent()
    throws IOException
  {
    int m = read();
    int k = 0;
    int n = 0;
    if ((m == 43) || (m == 45)) {
      i = read();
    }
    int j;
    int i1;
    for (;;)
    {
      if (i >= 0)
      {
        j = Character.digit((char)i, 10);
        if (j >= 0) {
          break;
        }
      }
      if (m != 0) {
        error("exponent sign not followed by digit");
      }
      j = 1;
      i1 = i;
      if (i1 >= 0) {
        unread(i1);
      }
      i = j;
      if (m == 45) {
        i = -j;
      }
      if (n == 0) {
        return i;
      }
      if (m != 45) {
        break label156;
      }
      return Integer.MIN_VALUE;
      i = m;
      m = 0;
    }
    int i2;
    for (int i = j;; i = i * 10 + i2)
    {
      i1 = read();
      i2 = Character.digit((char)i1, 10);
      n = k;
      j = i;
      if (i2 < 0) {
        break;
      }
      if (i > 214748363) {
        k = 1;
      }
    }
    label156:
    return Integer.MAX_VALUE;
    return i;
  }
  
  public int readUnicodeChar()
    throws IOException
  {
    int j = this.port.read();
    int i = j;
    if (j >= 55296)
    {
      i = j;
      if (j < 56319)
      {
        int k = this.port.read();
        i = j;
        if (k >= 56320)
        {
          i = j;
          if (k <= 57343) {
            i = (j - 55296 << 10) + (j - 56320) + 65536;
          }
        }
      }
    }
    return i;
  }
  
  public void reset()
    throws IOException
  {
    if (this.saveTokenBufferLength < 0) {
      throw new Error("internal error: reset called without prior mark");
    }
    this.port.reset();
    this.saveTokenBufferLength = -1;
  }
  
  public boolean seenErrors()
  {
    return (this.messages != null) && (this.messages.seenErrors());
  }
  
  public void setInteractive(boolean paramBoolean)
  {
    this.interactive = paramBoolean;
  }
  
  public void setMessages(SourceMessages paramSourceMessages)
  {
    this.messages = paramSourceMessages;
  }
  
  public void skip()
    throws IOException
  {
    this.port.skip();
  }
  
  protected void skip_quick()
    throws IOException
  {
    this.port.skip_quick();
  }
  
  public void tokenBufferAppend(int paramInt)
  {
    int i = paramInt;
    if (paramInt >= 65536)
    {
      tokenBufferAppend((paramInt - 65536 >> 10) + 55296);
      i = (paramInt & 0x3FF) + 56320;
    }
    paramInt = this.tokenBufferLength;
    char[] arrayOfChar2 = this.tokenBuffer;
    char[] arrayOfChar1 = arrayOfChar2;
    if (paramInt == this.tokenBuffer.length)
    {
      this.tokenBuffer = new char[paramInt * 2];
      System.arraycopy(arrayOfChar2, 0, this.tokenBuffer, 0, paramInt);
      arrayOfChar1 = this.tokenBuffer;
    }
    arrayOfChar1[paramInt] = ((char)i);
    this.tokenBufferLength = (paramInt + 1);
  }
  
  public String tokenBufferString()
  {
    return new String(this.tokenBuffer, 0, this.tokenBufferLength);
  }
  
  protected void unread()
    throws IOException
  {
    this.port.unread();
  }
  
  public void unread(int paramInt)
    throws IOException
  {
    if (paramInt >= 0) {
      this.port.unread();
    }
  }
  
  protected void unread_quick()
    throws IOException
  {
    this.port.unread_quick();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\Lexer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */