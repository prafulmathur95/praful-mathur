package gnu.kawa.xml;

public class HexBinary
  extends BinaryObject
{
  public HexBinary(byte[] paramArrayOfByte)
  {
    this.data = paramArrayOfByte;
  }
  
  static char forHexDigit(int paramInt)
  {
    if (paramInt < 10) {
      return (char)(paramInt + 48);
    }
    return (char)(paramInt - 10 + 65);
  }
  
  static byte[] parseHexBinary(String paramString)
  {
    paramString = paramString.trim();
    int i = paramString.length();
    if ((i & 0x1) != 0) {
      throw new IllegalArgumentException("hexBinary string length not a multiple of 2");
    }
    int k = i >> 1;
    byte[] arrayOfByte = new byte[k];
    int j = 0;
    while (j < k)
    {
      int m = Character.digit(paramString.charAt(j * 2), 16);
      int n = Character.digit(paramString.charAt(j * 2 + 1), 16);
      i = -1;
      if (m < 0) {
        i = j * 2;
      }
      while (i >= 0)
      {
        throw new IllegalArgumentException("invalid hexBinary character at position " + i);
        if (n < 0) {
          i = j * 2 + 1;
        }
      }
      arrayOfByte[j] = ((byte)(m * 16 + n));
      j += 1;
    }
    return arrayOfByte;
  }
  
  static HexBinary valueOf(String paramString)
  {
    return new HexBinary(parseHexBinary(paramString));
  }
  
  public String toString()
  {
    return toString(new StringBuffer()).toString();
  }
  
  public StringBuffer toString(StringBuffer paramStringBuffer)
  {
    byte[] arrayOfByte = this.data;
    int j = arrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      int k = arrayOfByte[i];
      paramStringBuffer.append(forHexDigit(k >> 4 & 0xF));
      paramStringBuffer.append(forHexDigit(k & 0xF));
      i += 1;
    }
    return paramStringBuffer;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\HexBinary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */