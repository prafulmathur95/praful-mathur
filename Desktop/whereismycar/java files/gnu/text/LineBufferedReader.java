package gnu.text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class LineBufferedReader
  extends Reader
{
  public static final int BUFFER_SIZE = 8192;
  private static final int CONVERT_CR = 1;
  private static final int DONT_KEEP_FULL_LINES = 8;
  private static final int PREV_WAS_CR = 4;
  private static final int USER_BUFFER = 2;
  public char[] buffer;
  private int flags;
  int highestPos;
  protected Reader in;
  public int limit;
  protected int lineNumber;
  private int lineStartPos;
  protected int markPos;
  Path path;
  public int pos;
  protected int readAheadLimit = 0;
  public char readState = '\n';
  
  public LineBufferedReader(InputStream paramInputStream)
  {
    this.in = new InputStreamReader(paramInputStream);
  }
  
  public LineBufferedReader(Reader paramReader)
  {
    this.in = paramReader;
  }
  
  private void clearMark()
  {
    int i = 0;
    this.readAheadLimit = 0;
    if (this.lineStartPos < 0) {}
    for (;;)
    {
      int j = i + 1;
      if (j >= this.pos)
      {
        return;
        i = this.lineStartPos;
      }
      else
      {
        int k = this.buffer[(j - 1)];
        if (k != 10)
        {
          i = j;
          if (k != 13) {
            continue;
          }
          if (getConvertCR())
          {
            i = j;
            if (this.buffer[j] == '\n') {
              continue;
            }
          }
        }
        this.lineNumber += 1;
        this.lineStartPos = j;
        i = j;
      }
    }
  }
  
  static int countLines(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i = 0;
    int k;
    for (int j = 0; paramInt1 < paramInt2; j = k)
    {
      k = paramArrayOfChar[paramInt1];
      if ((k != 10) || (j == 13))
      {
        j = i;
        if (k != 13) {}
      }
      else
      {
        j = i + 1;
      }
      paramInt1 += 1;
      i = j;
    }
    return i;
  }
  
  private void reserve(char[] paramArrayOfChar, int paramInt)
    throws IOException
  {
    int j = paramInt + this.limit;
    if (j <= paramArrayOfChar.length)
    {
      paramInt = 0;
      if (this.limit > 0) {
        System.arraycopy(this.buffer, paramInt, paramArrayOfChar, 0, this.limit);
      }
      this.buffer = paramArrayOfChar;
      return;
    }
    paramInt = this.pos;
    int i = paramInt;
    if (this.readAheadLimit > 0)
    {
      i = paramInt;
      if (this.markPos < this.pos)
      {
        if ((this.pos - this.markPos <= this.readAheadLimit) && (((this.flags & 0x2) == 0) || (j - this.markPos <= paramArrayOfChar.length))) {
          break label215;
        }
        clearMark();
        i = paramInt;
      }
    }
    label117:
    j -= paramArrayOfChar.length;
    char[] arrayOfChar;
    if (j <= i)
    {
      paramInt = i;
      arrayOfChar = paramArrayOfChar;
      if (i > this.lineStartPos)
      {
        if ((this.flags & 0x8) == 0) {
          break label224;
        }
        arrayOfChar = paramArrayOfChar;
        paramInt = i;
      }
    }
    for (;;)
    {
      this.lineStartPos -= paramInt;
      this.limit -= paramInt;
      this.markPos -= paramInt;
      this.pos -= paramInt;
      this.highestPos -= paramInt;
      paramArrayOfChar = arrayOfChar;
      break;
      label215:
      i = this.markPos;
      break label117;
      label224:
      if ((j <= this.lineStartPos) && (i > this.lineStartPos))
      {
        paramInt = this.lineStartPos;
        arrayOfChar = paramArrayOfChar;
      }
      else if ((this.flags & 0x2) != 0)
      {
        paramInt = i - (i - j >> 2);
        arrayOfChar = paramArrayOfChar;
      }
      else
      {
        paramInt = i;
        if (this.lineStartPos >= 0) {
          paramInt = this.lineStartPos;
        }
        arrayOfChar = new char[paramArrayOfChar.length * 2];
      }
    }
  }
  
  public void close()
    throws IOException
  {
    this.in.close();
  }
  
  public int fill(int paramInt)
    throws IOException
  {
    return this.in.read(this.buffer, this.pos, paramInt);
  }
  
  public int getColumnNumber()
  {
    int i = 0;
    if (this.pos > 0)
    {
      j = this.buffer[(this.pos - 1)];
      if ((j == 10) || (j == 13)) {
        return 0;
      }
    }
    if (this.readAheadLimit <= 0) {
      return this.pos - this.lineStartPos;
    }
    if (this.lineStartPos < 0) {}
    for (;;)
    {
      int k = i;
      j = i;
      while (k < this.pos)
      {
        char[] arrayOfChar = this.buffer;
        i = k + 1;
        k = arrayOfChar[k];
        if ((k == 10) || (k == 13)) {
          j = i;
        }
        k = i;
      }
      i = this.lineStartPos;
    }
    int j = this.pos - j;
    i = j;
    if (this.lineStartPos < 0) {
      i = j - this.lineStartPos;
    }
    return i;
  }
  
  public final boolean getConvertCR()
  {
    return (this.flags & 0x1) != 0;
  }
  
  public int getLineNumber()
  {
    int j = this.lineNumber;
    if (this.readAheadLimit == 0)
    {
      i = j;
      if (this.pos > 0)
      {
        i = j;
        if (this.pos > this.lineStartPos)
        {
          int k = this.buffer[(this.pos - 1)];
          if (k != 10)
          {
            i = j;
            if (k != 13) {}
          }
          else
          {
            i = j + 1;
          }
        }
      }
      return i;
    }
    char[] arrayOfChar = this.buffer;
    if (this.lineStartPos < 0) {}
    for (int i = 0;; i = this.lineStartPos) {
      return j + countLines(arrayOfChar, i, this.pos);
    }
  }
  
  public String getName()
  {
    if (this.path == null) {
      return null;
    }
    return this.path.toString();
  }
  
  public Path getPath()
  {
    return this.path;
  }
  
  public char getReadState()
  {
    return this.readState;
  }
  
  public void incrLineNumber(int paramInt1, int paramInt2)
  {
    this.lineNumber += paramInt1;
    this.lineStartPos = paramInt2;
  }
  
  public void lineStart(boolean paramBoolean)
    throws IOException
  {}
  
  public void mark(int paramInt)
  {
    try
    {
      if (this.readAheadLimit > 0) {
        clearMark();
      }
      this.readAheadLimit = paramInt;
      this.markPos = this.pos;
      return;
    }
    finally {}
  }
  
  public boolean markSupported()
  {
    return true;
  }
  
  public int peek()
    throws IOException
  {
    if ((this.pos < this.limit) && (this.pos > 0))
    {
      i = this.buffer[(this.pos - 1)];
      if ((i != 10) && (i != 13))
      {
        int j = this.buffer[this.pos];
        i = j;
        if (j == 13)
        {
          i = j;
          if (getConvertCR()) {
            i = 10;
          }
        }
        return i;
      }
    }
    int i = read();
    if (i >= 0) {
      unread_quick();
    }
    return i;
  }
  
  public int read()
    throws IOException
  {
    int i;
    boolean bool;
    label92:
    label115:
    label121:
    label163:
    label186:
    int j;
    if (this.pos > 0)
    {
      i = this.buffer[(this.pos - 1)];
      if ((i == 13) || (i == 10))
      {
        if ((this.lineStartPos < this.pos) && ((this.readAheadLimit == 0) || (this.pos <= this.markPos)))
        {
          this.lineStartPos = this.pos;
          this.lineNumber += 1;
        }
        if (this.pos >= this.highestPos) {
          break label242;
        }
        bool = true;
        if (i == 10)
        {
          if (this.pos > 1) {
            break label248;
          }
          if ((this.flags & 0x4) != 0) {}
        }
        else
        {
          lineStart(bool);
        }
        if (!bool) {
          this.highestPos = (this.pos + 1);
        }
      }
      if (this.pos < this.limit) {
        break label315;
      }
      if (this.buffer != null) {
        break label267;
      }
      this.buffer = new char[' '];
      if (this.pos == 0)
      {
        if (i != 13) {
          break label291;
        }
        this.flags |= 0x4;
      }
      j = fill(this.buffer.length - this.pos);
      if (j > 0) {
        break label305;
      }
      j = -1;
    }
    label242:
    label248:
    label267:
    label291:
    label305:
    label315:
    label401:
    do
    {
      int k;
      do
      {
        do
        {
          do
          {
            return j;
            if ((this.flags & 0x4) != 0)
            {
              i = 13;
              break;
            }
            if (this.lineStartPos >= 0)
            {
              i = 10;
              break;
            }
            i = 0;
            break;
            bool = false;
            break label92;
            if (this.buffer[(this.pos - 2)] == '\r') {
              break label121;
            }
            break label115;
            if (this.limit != this.buffer.length) {
              break label163;
            }
            reserve(this.buffer, 1);
            break label163;
            this.flags &= 0xFFFFFFFB;
            break label186;
            this.limit += j;
            char[] arrayOfChar = this.buffer;
            j = this.pos;
            this.pos = (j + 1);
            k = arrayOfChar[j];
            if (k != 10) {
              break label401;
            }
            j = k;
          } while (i != 13);
          if (this.lineStartPos == this.pos - 1)
          {
            this.lineNumber -= 1;
            this.lineStartPos -= 1;
          }
          j = k;
        } while (!getConvertCR());
        return read();
        j = k;
      } while (k != 13);
      j = k;
    } while (!getConvertCR());
    return 10;
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    int i;
    int j;
    int k;
    if (this.pos >= this.limit)
    {
      i = 0;
      j = paramInt2;
      k = paramInt1;
      paramInt1 = j;
      j = k;
    }
    for (;;)
    {
      if (paramInt1 > 0)
      {
        if ((this.pos >= this.limit) || (i == 10) || (i == 13))
        {
          if ((this.pos >= this.limit) && (paramInt1 < paramInt2))
          {
            return paramInt2 - paramInt1;
            if (this.pos > 0)
            {
              i = this.buffer[(this.pos - 1)];
              break;
            }
            if (((this.flags & 0x4) != 0) || (this.lineStartPos >= 0))
            {
              i = 10;
              break;
            }
            i = 0;
            break;
          }
          i = read();
          if (i < 0)
          {
            paramInt1 = paramInt2 - paramInt1;
            if (paramInt1 <= 0) {
              return -1;
            }
            return paramInt1;
          }
          paramArrayOfChar[j] = ((char)i);
          paramInt1 -= 1;
          j += 1;
          continue;
        }
        int i2 = this.pos;
        int i3 = this.limit;
        k = i;
        int n = i3;
        int m = j;
        int i1 = i2;
        if (paramInt1 < i3 - i2)
        {
          n = i2 + paramInt1;
          i1 = i2;
          m = j;
          k = i;
        }
        for (;;)
        {
          i = k;
          if (i1 < n)
          {
            k = this.buffer[i1];
            i = k;
            if (k != 10)
            {
              if (k != 13) {
                break label291;
              }
              i = k;
            }
          }
          paramInt1 -= i1 - this.pos;
          this.pos = i1;
          j = m;
          break;
          label291:
          paramArrayOfChar[m] = ((char)k);
          i1 += 1;
          m += 1;
        }
      }
    }
    return paramInt2;
  }
  
  public String readLine()
    throws IOException
  {
    int i = read();
    if (i < 0) {
      return null;
    }
    if ((i == 13) || (i == 10)) {
      return "";
    }
    i = this.pos - 1;
    int k;
    while (this.pos < this.limit)
    {
      localObject = this.buffer;
      int j = this.pos;
      this.pos = (j + 1);
      j = localObject[j];
      if ((j == 13) || (j == 10))
      {
        k = this.pos;
        if ((j == 10) || (getConvertCR())) {
          break label179;
        }
        if (this.pos < this.limit) {
          break label155;
        }
        this.pos -= 1;
      }
    }
    Object localObject = new StringBuffer(100);
    ((StringBuffer)localObject).append(this.buffer, i, this.pos - i);
    readLine((StringBuffer)localObject, 'I');
    return ((StringBuffer)localObject).toString();
    label155:
    if (this.buffer[this.pos] == '\n') {
      this.pos += 1;
    }
    label179:
    return new String(this.buffer, i, k - 1 - i);
  }
  
  public void readLine(StringBuffer paramStringBuffer, char paramChar)
    throws IOException
  {
    for (;;)
    {
      if (read() < 0) {}
      int i;
      do
      {
        do
        {
          do
          {
            return;
            i = this.pos - 1;
            this.pos = i;
            int j;
            do
            {
              if (this.pos >= this.limit) {
                break;
              }
              char[] arrayOfChar = this.buffer;
              j = this.pos;
              this.pos = (j + 1);
              j = arrayOfChar[j];
            } while ((j != 13) && (j != 10));
            paramStringBuffer.append(this.buffer, i, this.pos - 1 - i);
            if (paramChar == 'P')
            {
              this.pos -= 1;
              return;
            }
            if ((!getConvertCR()) && (j != 10)) {
              break;
            }
          } while (paramChar == 'I');
          paramStringBuffer.append('\n');
          return;
          if (paramChar != 'I') {
            paramStringBuffer.append('\r');
          }
          i = read();
          if (i != 10) {
            break;
          }
        } while (paramChar == 'I');
        paramStringBuffer.append('\n');
        return;
      } while (i < 0);
      unread_quick();
      return;
      paramStringBuffer.append(this.buffer, i, this.pos - i);
    }
  }
  
  public boolean ready()
    throws IOException
  {
    return (this.pos < this.limit) || (this.in.ready());
  }
  
  public void reset()
    throws IOException
  {
    if (this.readAheadLimit <= 0) {
      throw new IOException("mark invalid");
    }
    if (this.pos > this.highestPos) {
      this.highestPos = this.pos;
    }
    this.pos = this.markPos;
    this.readAheadLimit = 0;
  }
  
  public void setBuffer(char[] paramArrayOfChar)
    throws IOException
  {
    if (paramArrayOfChar == null)
    {
      if (this.buffer != null)
      {
        paramArrayOfChar = new char[this.buffer.length];
        System.arraycopy(this.buffer, 0, paramArrayOfChar, 0, this.buffer.length);
        this.buffer = paramArrayOfChar;
      }
      this.flags &= 0xFFFFFFFD;
      return;
    }
    if (this.limit - this.pos > paramArrayOfChar.length) {
      throw new IOException("setBuffer - too short");
    }
    this.flags |= 0x2;
    reserve(paramArrayOfChar, 0);
  }
  
  public final void setConvertCR(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x1;
      return;
    }
    this.flags &= 0xFFFFFFFE;
  }
  
  public void setKeepFullLines(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags &= 0xFFFFFFF7;
      return;
    }
    this.flags |= 0x8;
  }
  
  public void setLineNumber(int paramInt)
  {
    this.lineNumber += paramInt - getLineNumber();
  }
  
  public void setName(Object paramObject)
  {
    setPath(Path.valueOf(paramObject));
  }
  
  public void setPath(Path paramPath)
  {
    this.path = paramPath;
  }
  
  public int skip(int paramInt)
    throws IOException
  {
    int i;
    if (paramInt < 0)
    {
      i = -paramInt;
      while ((i > 0) && (this.pos > 0))
      {
        unread();
        i -= 1;
      }
      k = paramInt + i;
      return k;
    }
    int j = paramInt;
    if (this.pos >= this.limit) {
      i = 0;
    }
    for (;;)
    {
      k = paramInt;
      if (j <= 0) {
        break;
      }
      if ((i != 10) && (i != 13) && (this.pos < this.limit)) {
        break label151;
      }
      i = read();
      if (i < 0)
      {
        return paramInt - j;
        if (this.pos > 0) {
          i = this.buffer[(this.pos - 1)];
        } else if (((this.flags & 0x4) != 0) || (this.lineStartPos >= 0)) {
          i = 10;
        } else {
          i = 0;
        }
      }
      else
      {
        j -= 1;
      }
    }
    label151:
    int i1 = this.pos;
    int i2 = this.limit;
    int k = i;
    int n = i2;
    int m = i1;
    if (j < i2 - i1)
    {
      n = i1 + j;
      m = i1;
      k = i;
    }
    for (;;)
    {
      i = k;
      if (m < n)
      {
        k = this.buffer[m];
        i = k;
        if (k != 10)
        {
          if (k != 13) {
            break label254;
          }
          i = k;
        }
      }
      j -= m - this.pos;
      this.pos = m;
      break;
      label254:
      m += 1;
    }
  }
  
  public void skip()
    throws IOException
  {
    read();
  }
  
  public void skipRestOfLine()
    throws IOException
  {
    int i;
    do
    {
      i = read();
      if (i < 0) {}
      do
      {
        return;
        if (i != 13) {
          break;
        }
        i = read();
      } while ((i < 0) || (i == 10));
      unread();
      return;
    } while (i != 10);
  }
  
  public final void skip_quick()
    throws IOException
  {
    this.pos += 1;
  }
  
  public void unread()
    throws IOException
  {
    if (this.pos == 0) {
      throw new IOException("unread too much");
    }
    this.pos -= 1;
    int i = this.buffer[this.pos];
    if ((i == 10) || (i == 13))
    {
      if ((this.pos > 0) && (i == 10) && (getConvertCR()) && (this.buffer[(this.pos - 1)] == '\r')) {
        this.pos -= 1;
      }
      if (this.pos < this.lineStartPos)
      {
        this.lineNumber -= 1;
        i = this.pos;
        int j;
        int k;
        do
        {
          j = i;
          if (i <= 0) {
            break label161;
          }
          char[] arrayOfChar = this.buffer;
          j = i - 1;
          k = arrayOfChar[j];
          if (k == 13) {
            break;
          }
          i = j;
        } while (k != 10);
        j += 1;
        label161:
        this.lineStartPos = j;
      }
    }
  }
  
  public void unread_quick()
  {
    this.pos -= 1;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\LineBufferedReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */