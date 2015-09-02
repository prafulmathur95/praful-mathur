package gnu.lists;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.Writer;

public class CharBuffer
  extends StableVector
  implements CharSeq, Serializable
{
  private FString string;
  
  protected CharBuffer() {}
  
  public CharBuffer(int paramInt)
  {
    this(new FString(paramInt));
  }
  
  public CharBuffer(FString paramFString)
  {
    super(paramFString);
    this.string = paramFString;
  }
  
  public char charAt(int paramInt)
  {
    int i = paramInt;
    if (paramInt >= this.gapStart) {
      i = paramInt + (this.gapEnd - this.gapStart);
    }
    return this.string.charAt(i);
  }
  
  public void consume(int paramInt1, int paramInt2, Consumer paramConsumer)
  {
    char[] arrayOfChar = this.string.data;
    int j = paramInt1;
    int i = paramInt2;
    if (paramInt1 < this.gapStart)
    {
      j = this.gapStart - paramInt1;
      i = j;
      if (j > paramInt2) {
        i = paramInt2;
      }
      paramConsumer.write(arrayOfChar, paramInt1, i);
      i = paramInt2 - i;
      j = paramInt1 + i;
    }
    if (i > 0) {
      paramConsumer.write(arrayOfChar, j + (this.gapEnd - this.gapStart), i);
    }
  }
  
  public void delete(int paramInt1, int paramInt2)
  {
    paramInt1 = createPos(paramInt1, false);
    removePos(paramInt1, paramInt2);
    releasePos(paramInt1);
  }
  
  public void dump()
  {
    int i = 0;
    System.err.println("Buffer Content dump.  size:" + size() + "  buffer:" + getArray().length);
    System.err.print("before gap: \"");
    System.err.print(new String(getArray(), 0, this.gapStart));
    System.err.println("\" (gapStart:" + this.gapStart + " gapEnd:" + this.gapEnd + ')');
    System.err.print("after gap: \"");
    System.err.print(new String(getArray(), this.gapEnd, getArray().length - this.gapEnd));
    System.err.println("\"");
    if (this.positions == null) {}
    Object localObject;
    for (;;)
    {
      System.err.println("Positions (size: " + i + " free:" + this.free + "):");
      localObject = null;
      if (this.free == -2) {
        break;
      }
      boolean[] arrayOfBoolean = new boolean[this.positions.length];
      for (j = this.free;; j = this.positions[j])
      {
        localObject = arrayOfBoolean;
        if (j < 0) {
          break;
        }
        arrayOfBoolean[j] = true;
      }
      i = this.positions.length;
    }
    int j = 0;
    if (j < i)
    {
      int k = this.positions[j];
      if (this.free == -2) {
        if (k == -2) {}
      }
      for (;;)
      {
        System.err.println("position#" + j + ": " + (k >> 1) + " isAfter:" + (k & 0x1));
        do
        {
          j += 1;
          break;
        } while (localObject[j] != 0);
      }
    }
  }
  
  public final void fill(char paramChar)
  {
    char[] arrayOfChar = this.string.data;
    int i = arrayOfChar.length;
    for (;;)
    {
      i -= 1;
      if (i < this.gapEnd) {
        break;
      }
      arrayOfChar[i] = paramChar;
    }
    i = this.gapStart;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      arrayOfChar[i] = paramChar;
    }
  }
  
  public void fill(int paramInt1, int paramInt2, char paramChar)
  {
    char[] arrayOfChar = this.string.data;
    int i = paramInt1;
    if (this.gapStart < paramInt2) {
      paramInt1 = this.gapStart;
    }
    while (i < paramInt1)
    {
      arrayOfChar[i] = paramChar;
      i += 1;
      continue;
      paramInt1 = paramInt2;
    }
    i = paramInt1 + (this.gapEnd - this.gapStart);
    while (i < paramInt1 + paramInt2)
    {
      arrayOfChar[i] = paramChar;
      i += 1;
    }
  }
  
  public char[] getArray()
  {
    return (char[])this.base.getBuffer();
  }
  
  public void getChars(int paramInt1, int paramInt2, char[] paramArrayOfChar, int paramInt3)
  {
    char[] arrayOfChar = this.string.data;
    int j = paramInt1;
    int i = paramInt3;
    if (paramInt1 < this.gapStart) {
      if (paramInt2 >= this.gapStart) {
        break label116;
      }
    }
    label116:
    for (i = paramInt2;; i = this.gapStart)
    {
      int k = i - paramInt1;
      j = paramInt1;
      i = paramInt3;
      if (k > 0)
      {
        System.arraycopy(arrayOfChar, paramInt1, paramArrayOfChar, paramInt3, k);
        j = paramInt1 + k;
        i = paramInt3 + k;
      }
      paramInt1 = this.gapEnd - this.gapStart;
      paramInt3 = j + paramInt1;
      paramInt1 = paramInt2 + paramInt1 - paramInt3;
      if (paramInt1 > 0) {
        System.arraycopy(arrayOfChar, paramInt3, paramArrayOfChar, i, paramInt1);
      }
      return;
    }
  }
  
  public int indexOf(int paramInt1, int paramInt2)
  {
    int i;
    int j;
    char[] arrayOfChar;
    int k;
    int m;
    if (paramInt1 >= 65536)
    {
      i = (char)((paramInt1 - 65536 >> 10) + 55296);
      j = (char)((paramInt1 & 0x3FF) + 56320);
      arrayOfChar = getArray();
      k = paramInt2;
      m = this.gapStart;
      paramInt1 = k;
      paramInt2 = m;
      if (k >= m) {
        paramInt1 = k + (this.gapEnd - this.gapStart);
      }
    }
    for (paramInt2 = arrayOfChar.length;; paramInt2 = k)
    {
      m = paramInt1;
      k = paramInt2;
      if (paramInt1 == paramInt2)
      {
        k = arrayOfChar.length;
        if (paramInt1 >= k) {
          break label200;
        }
        m = this.gapEnd;
      }
      if (arrayOfChar[m] == i)
      {
        if (j != 0)
        {
          if (m + 1 >= k) {
            break label166;
          }
          if (arrayOfChar[(m + 1)] != j) {
            break label186;
          }
        }
        label166:
        while ((this.gapEnd < arrayOfChar.length) && (arrayOfChar[this.gapEnd] == j))
        {
          if (m <= this.gapStart) {
            break label197;
          }
          return m - (this.gapEnd - this.gapStart);
          i = (char)paramInt1;
          j = 0;
          break;
        }
      }
      label186:
      paramInt1 = m + 1;
    }
    label197:
    return m;
    label200:
    return -1;
  }
  
  public void insert(int paramInt, String paramString, boolean paramBoolean)
  {
    int i = paramString.length();
    gapReserve(paramInt, i);
    paramString.getChars(0, i, this.string.data, paramInt);
    this.gapStart += i;
  }
  
  public int lastIndexOf(int paramInt1, int paramInt2)
  {
    int j;
    int i;
    if (paramInt1 >= 65536)
    {
      j = (char)((paramInt1 - 65536 >> 10) + 55296);
      i = (char)((paramInt1 & 0x3FF) + 56320);
      paramInt1 = j;
    }
    do
    {
      do
      {
        for (;;)
        {
          j = paramInt2 - 1;
          if (j < 0) {
            break label104;
          }
          paramInt2 = j;
          if (charAt(j) == i)
          {
            if (paramInt1 != 0) {
              break;
            }
            return j;
            i = 0;
            j = (char)paramInt1;
            paramInt1 = i;
            i = j;
          }
        }
        paramInt2 = j;
      } while (j <= 0);
      paramInt2 = j;
    } while (charAt(j - 1) != paramInt1);
    return j - 1;
    label104:
    return -1;
  }
  
  public int length()
  {
    return size();
  }
  
  public void setCharAt(int paramInt, char paramChar)
  {
    int i = paramInt;
    if (paramInt >= this.gapStart) {
      i = paramInt + (this.gapEnd - this.gapStart);
    }
    this.string.setCharAt(i, paramChar);
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2)
  {
    int i = size();
    if ((paramInt1 < 0) || (paramInt2 < paramInt1) || (paramInt2 > i)) {
      throw new IndexOutOfBoundsException();
    }
    return new SubCharSeq(this, this.base.createPos(paramInt1, false), this.base.createPos(paramInt2, true));
  }
  
  public String substring(int paramInt1, int paramInt2)
  {
    int i = size();
    if ((paramInt1 < 0) || (paramInt2 < paramInt1) || (paramInt2 > i)) {
      throw new IndexOutOfBoundsException();
    }
    paramInt2 -= paramInt1;
    paramInt1 = getSegment(paramInt1, paramInt2);
    return new String(getArray(), paramInt1, paramInt2);
  }
  
  public String toString()
  {
    int i = size();
    int j = getSegment(0, i);
    return new String(getArray(), j, i);
  }
  
  public void writeTo(int paramInt1, int paramInt2, Writer paramWriter)
    throws IOException
  {
    char[] arrayOfChar = this.string.data;
    int j = paramInt1;
    int i = paramInt2;
    if (paramInt1 < this.gapStart)
    {
      j = this.gapStart - paramInt1;
      i = j;
      if (j > paramInt2) {
        i = paramInt2;
      }
      paramWriter.write(arrayOfChar, paramInt1, i);
      i = paramInt2 - i;
      j = paramInt1 + i;
    }
    if (i > 0) {
      paramWriter.write(arrayOfChar, j + (this.gapEnd - this.gapStart), i);
    }
  }
  
  public void writeTo(int paramInt1, int paramInt2, Appendable paramAppendable)
    throws IOException
  {
    if ((paramAppendable instanceof Writer))
    {
      writeTo(paramInt1, paramInt2, (Writer)paramAppendable);
      return;
    }
    paramAppendable.append(this, paramInt1, paramInt1 + paramInt2);
  }
  
  public void writeTo(Writer paramWriter)
    throws IOException
  {
    char[] arrayOfChar = this.string.data;
    paramWriter.write(arrayOfChar, 0, this.gapStart);
    paramWriter.write(arrayOfChar, this.gapEnd, arrayOfChar.length - this.gapEnd);
  }
  
  public void writeTo(Appendable paramAppendable)
    throws IOException
  {
    writeTo(0, size(), paramAppendable);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\CharBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */