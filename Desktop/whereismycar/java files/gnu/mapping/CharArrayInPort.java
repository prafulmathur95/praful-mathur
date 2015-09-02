package gnu.mapping;

import gnu.lists.CharSeq;
import gnu.lists.FString;
import gnu.text.NullReader;
import gnu.text.Path;
import java.io.IOException;

public class CharArrayInPort
  extends InPort
{
  static final Path stringPath = Path.valueOf("<string>");
  
  public CharArrayInPort(String paramString)
  {
    this(paramString.toCharArray());
  }
  
  public CharArrayInPort(char[] paramArrayOfChar)
  {
    this(paramArrayOfChar, paramArrayOfChar.length);
  }
  
  public CharArrayInPort(char[] paramArrayOfChar, int paramInt)
  {
    super(NullReader.nullReader, stringPath);
    try
    {
      setBuffer(paramArrayOfChar);
      this.limit = paramInt;
      return;
    }
    catch (IOException paramArrayOfChar)
    {
      throw new Error(paramArrayOfChar.toString());
    }
  }
  
  public CharArrayInPort make(CharSequence paramCharSequence)
  {
    if ((paramCharSequence instanceof FString))
    {
      paramCharSequence = (FString)paramCharSequence;
      return new CharArrayInPort(paramCharSequence.data, paramCharSequence.size);
    }
    int j = paramCharSequence.length();
    char[] arrayOfChar = new char[j];
    if ((paramCharSequence instanceof String)) {
      ((String)paramCharSequence).getChars(0, j, arrayOfChar, 0);
    }
    for (;;)
    {
      return new CharArrayInPort(arrayOfChar, j);
      if (!(paramCharSequence instanceof CharSeq))
      {
        int i = j;
        for (;;)
        {
          i -= 1;
          if (i < 0) {
            break;
          }
          arrayOfChar[i] = paramCharSequence.charAt(i);
        }
      }
      ((CharSeq)paramCharSequence).getChars(0, j, arrayOfChar, 0);
    }
  }
  
  public int read()
    throws IOException
  {
    if (this.pos >= this.limit) {
      return -1;
    }
    return super.read();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\CharArrayInPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */