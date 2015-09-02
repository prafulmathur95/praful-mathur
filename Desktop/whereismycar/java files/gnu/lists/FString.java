package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Writer;

public class FString
  extends SimpleVector
  implements Comparable, Appendable, CharSeq, Externalizable, Consumable
{
  protected static char[] empty = new char[0];
  public char[] data;
  
  public FString()
  {
    this.data = empty;
  }
  
  public FString(int paramInt)
  {
    this.size = paramInt;
    this.data = new char[paramInt];
  }
  
  public FString(int paramInt, char paramChar)
  {
    char[] arrayOfChar = new char[paramInt];
    this.data = arrayOfChar;
    this.size = paramInt;
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      arrayOfChar[paramInt] = paramChar;
    }
  }
  
  public FString(CharSeq paramCharSeq)
  {
    this(paramCharSeq, 0, paramCharSeq.size());
  }
  
  public FString(CharSeq paramCharSeq, int paramInt1, int paramInt2)
  {
    char[] arrayOfChar = new char[paramInt2];
    paramCharSeq.getChars(paramInt1, paramInt1 + paramInt2, arrayOfChar, 0);
    this.data = arrayOfChar;
    this.size = paramInt2;
  }
  
  public FString(Sequence paramSequence)
  {
    this.data = new char[paramSequence.size()];
    addAll(paramSequence);
  }
  
  public FString(CharSequence paramCharSequence)
  {
    this(paramCharSequence, 0, paramCharSequence.length());
  }
  
  public FString(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    char[] arrayOfChar = new char[paramInt2];
    int i = paramInt2;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      arrayOfChar[i] = paramCharSequence.charAt(paramInt1 + i);
    }
    this.data = arrayOfChar;
    this.size = paramInt2;
  }
  
  public FString(String paramString)
  {
    this.data = paramString.toCharArray();
    this.size = this.data.length;
  }
  
  public FString(StringBuffer paramStringBuffer)
  {
    this(paramStringBuffer, 0, paramStringBuffer.length());
  }
  
  public FString(StringBuffer paramStringBuffer, int paramInt1, int paramInt2)
  {
    this.size = paramInt2;
    this.data = new char[paramInt2];
    if (paramInt2 > 0) {
      paramStringBuffer.getChars(paramInt1, paramInt1 + paramInt2, this.data, 0);
    }
  }
  
  public FString(char[] paramArrayOfChar)
  {
    this.size = paramArrayOfChar.length;
    this.data = paramArrayOfChar;
  }
  
  public FString(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this.size = paramInt2;
    this.data = new char[paramInt2];
    System.arraycopy(paramArrayOfChar, paramInt1, this.data, 0, paramInt2);
  }
  
  public boolean addAll(FString paramFString)
  {
    boolean bool = false;
    int i = this.size + paramFString.size;
    if (this.data.length < i) {
      setBufferLength(i);
    }
    System.arraycopy(paramFString.data, 0, this.data, this.size, paramFString.size);
    this.size = i;
    if (paramFString.size > 0) {
      bool = true;
    }
    return bool;
  }
  
  public boolean addAll(CharSequence paramCharSequence)
  {
    boolean bool = false;
    int j = paramCharSequence.length();
    int k = this.size + j;
    if (this.data.length < k) {
      setBufferLength(k);
    }
    if ((paramCharSequence instanceof FString)) {
      System.arraycopy(((FString)paramCharSequence).data, 0, this.data, this.size, j);
    }
    for (;;)
    {
      this.size = k;
      if (j > 0) {
        bool = true;
      }
      return bool;
      if ((paramCharSequence instanceof String))
      {
        ((String)paramCharSequence).getChars(0, j, this.data, this.size);
      }
      else
      {
        if (!(paramCharSequence instanceof CharSeq)) {
          break;
        }
        ((CharSeq)paramCharSequence).getChars(0, j, this.data, this.size);
      }
    }
    int i = j;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      this.data[(this.size + i)] = paramCharSequence.charAt(i);
    }
  }
  
  public void addAllStrings(Object[] paramArrayOfObject, int paramInt)
  {
    int j = this.size;
    int i = paramInt;
    while (i < paramArrayOfObject.length)
    {
      j += ((CharSequence)paramArrayOfObject[i]).length();
      i += 1;
    }
    if (this.data.length < j) {
      setBufferLength(j);
    }
    while (paramInt < paramArrayOfObject.length)
    {
      addAll((CharSequence)paramArrayOfObject[paramInt]);
      paramInt += 1;
    }
  }
  
  public FString append(char paramChar)
  {
    int i = this.size;
    if (i >= this.data.length) {
      ensureBufferLength(i + 1);
    }
    this.data[i] = paramChar;
    this.size = (i + 1);
    return this;
  }
  
  public FString append(CharSequence paramCharSequence)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null) {
      localObject = "null";
    }
    return append((CharSequence)localObject, 0, ((CharSequence)localObject).length());
  }
  
  public FString append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null) {
      localObject = "null";
    }
    int i = paramInt2 - paramInt1;
    int j = this.size;
    if (j + i > this.data.length) {
      ensureBufferLength(j + i);
    }
    paramCharSequence = this.data;
    if ((localObject instanceof String)) {
      ((String)localObject).getChars(paramInt1, paramInt2, paramCharSequence, j);
    }
    for (;;)
    {
      this.size = j;
      return this;
      if ((localObject instanceof CharSeq))
      {
        ((CharSeq)localObject).getChars(paramInt1, paramInt2, paramCharSequence, j);
      }
      else
      {
        i = paramInt1;
        paramInt1 = j;
        while (i < paramInt2)
        {
          paramCharSequence[paramInt1] = ((CharSequence)localObject).charAt(i);
          i += 1;
          paramInt1 += 1;
        }
      }
    }
  }
  
  public final char charAt(int paramInt)
  {
    if (paramInt >= this.size) {
      throw new StringIndexOutOfBoundsException(paramInt);
    }
    return this.data[paramInt];
  }
  
  public final char charAtBuffer(int paramInt)
  {
    return this.data[paramInt];
  }
  
  protected void clearBuffer(int paramInt1, int paramInt2)
  {
    char[] arrayOfChar = this.data;
    for (;;)
    {
      paramInt2 -= 1;
      if (paramInt2 < 0) {
        break;
      }
      arrayOfChar[paramInt1] = '\000';
      paramInt1 += 1;
    }
  }
  
  public int compareTo(Object paramObject)
  {
    paramObject = (FString)paramObject;
    char[] arrayOfChar1 = this.data;
    char[] arrayOfChar2 = ((FString)paramObject).data;
    int k = this.size;
    int m = ((FString)paramObject).size;
    int i;
    int j;
    if (k > m)
    {
      i = m;
      j = 0;
    }
    for (;;)
    {
      if (j >= i) {
        break label83;
      }
      int n = arrayOfChar1[j] - arrayOfChar2[j];
      if (n != 0)
      {
        return n;
        i = k;
        break;
      }
      j += 1;
    }
    label83:
    return k - m;
  }
  
  public void consume(Consumer paramConsumer)
  {
    paramConsumer.write(this.data, 0, this.data.length);
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer)
  {
    paramInt >>>= 1;
    if (paramInt >= this.size) {
      return false;
    }
    paramConsumer.write(this.data[paramInt]);
    return true;
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer)
  {
    if (paramConsumer.ignoring()) {}
    int i;
    do
    {
      return;
      i = paramInt1 >>> 1;
      paramInt2 >>>= 1;
      paramInt1 = paramInt2;
      if (paramInt2 > this.size) {
        paramInt1 = this.size;
      }
    } while (paramInt1 <= i);
    paramConsumer.write(this.data, i, paramInt1 - i);
  }
  
  public FString copy(int paramInt1, int paramInt2)
  {
    char[] arrayOfChar1 = new char[paramInt2 - paramInt1];
    char[] arrayOfChar2 = this.data;
    int i = paramInt1;
    while (i < paramInt2)
    {
      arrayOfChar1[(i - paramInt1)] = arrayOfChar2[i];
      i += 1;
    }
    return new FString(arrayOfChar1);
  }
  
  public void ensureBufferLength(int paramInt)
  {
    if (paramInt > this.data.length) {
      if (paramInt >= 60) {
        break label39;
      }
    }
    label39:
    for (int i = 120;; i = paramInt * 2)
    {
      char[] arrayOfChar = new char[i];
      System.arraycopy(this.data, 0, arrayOfChar, 0, paramInt);
      this.data = arrayOfChar;
      return;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof FString))) {}
    int i;
    do
    {
      return false;
      paramObject = ((FString)paramObject).data;
      i = this.size;
    } while ((paramObject == null) || (paramObject.length != i));
    char[] arrayOfChar = this.data;
    int j;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
    } while (arrayOfChar[j] == paramObject[j]);
    return false;
    return true;
  }
  
  public final void fill(char paramChar)
  {
    char[] arrayOfChar = this.data;
    int i = this.size;
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
    if ((paramInt1 < 0) || (paramInt2 > this.size)) {
      throw new IndexOutOfBoundsException();
    }
    char[] arrayOfChar = this.data;
    while (paramInt1 < paramInt2)
    {
      arrayOfChar[paramInt1] = paramChar;
      paramInt1 += 1;
    }
  }
  
  public final Object get(int paramInt)
  {
    if (paramInt >= this.size) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return Convert.toObject(this.data[paramInt]);
  }
  
  protected Object getBuffer()
  {
    return this.data;
  }
  
  public final Object getBuffer(int paramInt)
  {
    return Convert.toObject(this.data[paramInt]);
  }
  
  public int getBufferLength()
  {
    return this.data.length;
  }
  
  public void getChars(int paramInt1, int paramInt2, StringBuffer paramStringBuffer)
  {
    if ((paramInt1 < 0) || (paramInt1 > paramInt2)) {
      throw new StringIndexOutOfBoundsException(paramInt1);
    }
    if (paramInt2 > this.size) {
      throw new StringIndexOutOfBoundsException(paramInt2);
    }
    if (paramInt1 < paramInt2) {
      paramStringBuffer.append(this.data, paramInt1, paramInt2 - paramInt1);
    }
  }
  
  public void getChars(int paramInt1, int paramInt2, char[] paramArrayOfChar, int paramInt3)
  {
    if ((paramInt1 < 0) || (paramInt1 > paramInt2)) {
      throw new StringIndexOutOfBoundsException(paramInt1);
    }
    if (paramInt2 > this.size) {
      throw new StringIndexOutOfBoundsException(paramInt2);
    }
    if (paramInt3 + paramInt2 - paramInt1 > paramArrayOfChar.length) {
      throw new StringIndexOutOfBoundsException(paramInt3);
    }
    if (paramInt1 < paramInt2) {
      System.arraycopy(this.data, paramInt1, paramArrayOfChar, paramInt3, paramInt2 - paramInt1);
    }
  }
  
  public void getChars(StringBuffer paramStringBuffer)
  {
    paramStringBuffer.append(this.data, 0, this.size);
  }
  
  public int getElementKind()
  {
    return 29;
  }
  
  public int hashCode()
  {
    char[] arrayOfChar = this.data;
    int k = this.size;
    int j = 0;
    int i = 0;
    while (i < k)
    {
      j = j * 31 + arrayOfChar[i];
      i += 1;
    }
    return j;
  }
  
  public int length()
  {
    return this.size;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    int j = paramObjectInput.readInt();
    char[] arrayOfChar = new char[j];
    int i = 0;
    while (i < j)
    {
      arrayOfChar[i] = paramObjectInput.readChar();
      i += 1;
    }
    this.data = arrayOfChar;
    this.size = j;
  }
  
  public void replace(int paramInt, String paramString)
  {
    paramString.getChars(0, paramString.length(), this.data, paramInt);
  }
  
  public void replace(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
  {
    System.arraycopy(paramArrayOfChar, paramInt2, this.data, paramInt1, paramInt3);
  }
  
  public final Object setBuffer(int paramInt, Object paramObject)
  {
    Object localObject = Convert.toObject(this.data[paramInt]);
    this.data[paramInt] = Convert.toChar(paramObject);
    return localObject;
  }
  
  public void setBufferLength(int paramInt)
  {
    int i = this.data.length;
    char[] arrayOfChar1;
    char[] arrayOfChar2;
    if (i != paramInt)
    {
      arrayOfChar1 = new char[paramInt];
      arrayOfChar2 = this.data;
      if (i >= paramInt) {
        break label45;
      }
      paramInt = i;
    }
    label45:
    for (;;)
    {
      System.arraycopy(arrayOfChar2, 0, arrayOfChar1, 0, paramInt);
      this.data = arrayOfChar1;
      return;
    }
  }
  
  public void setCharAt(int paramInt, char paramChar)
  {
    if ((paramInt < 0) || (paramInt >= this.size)) {
      throw new StringIndexOutOfBoundsException(paramInt);
    }
    this.data[paramInt] = paramChar;
  }
  
  public void setCharAtBuffer(int paramInt, char paramChar)
  {
    this.data[paramInt] = paramChar;
  }
  
  public void shift(int paramInt1, int paramInt2, int paramInt3)
  {
    System.arraycopy(this.data, paramInt1, this.data, paramInt2, paramInt3);
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2)
  {
    return new FString(this.data, paramInt1, paramInt2 - paramInt1);
  }
  
  public String substring(int paramInt1, int paramInt2)
  {
    return new String(this.data, paramInt1, paramInt2 - paramInt1);
  }
  
  public char[] toCharArray()
  {
    int i = this.data.length;
    int j = this.size;
    if (j == i) {
      return this.data;
    }
    char[] arrayOfChar = new char[j];
    System.arraycopy(this.data, 0, arrayOfChar, 0, j);
    return arrayOfChar;
  }
  
  public String toString()
  {
    return new String(this.data, 0, this.size);
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    int j = this.size;
    paramObjectOutput.writeInt(j);
    char[] arrayOfChar = this.data;
    int i = 0;
    while (i < j)
    {
      paramObjectOutput.writeChar(arrayOfChar[i]);
      i += 1;
    }
  }
  
  public void writeTo(int paramInt1, int paramInt2, Appendable paramAppendable)
    throws IOException
  {
    if ((paramAppendable instanceof Writer)) {
      try
      {
        ((Writer)paramAppendable).write(this.data, paramInt1, paramInt2);
        return;
      }
      catch (IOException paramAppendable)
      {
        throw new RuntimeException(paramAppendable);
      }
    }
    paramAppendable.append(this, paramInt1, paramInt1 + paramInt2);
  }
  
  public void writeTo(Appendable paramAppendable)
    throws IOException
  {
    writeTo(0, this.size, paramAppendable);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\FString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */