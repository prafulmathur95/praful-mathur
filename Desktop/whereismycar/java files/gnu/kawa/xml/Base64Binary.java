package gnu.kawa.xml;

public class Base64Binary
  extends BinaryObject
{
  public static final String ENCODING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
  
  public Base64Binary(String paramString)
  {
    int i2 = paramString.length();
    int j = 0;
    int i = 0;
    char c;
    while (i < i2)
    {
      c = paramString.charAt(i);
      k = j;
      if (!Character.isWhitespace(c))
      {
        k = j;
        if (c != '=') {
          k = j + 1;
        }
      }
      i += 1;
      j = k;
    }
    byte[] arrayOfByte = new byte[j * 3 / 4];
    int i1 = 0;
    int k = 0;
    int n = 0;
    int m = 0;
    j = 0;
    if (m < i2)
    {
      c = paramString.charAt(m);
      if ((c >= 'A') && (c <= 'Z')) {
        i = c - 'A';
      }
      for (;;)
      {
        label126:
        if ((i < 0) || (n > 0))
        {
          throw new IllegalArgumentException("illegal character in base64Binary string at position " + m);
          if ((c >= 'a') && (c <= 'z')) {
            i = c - 'a' + 26;
          } else if ((c >= '0') && (c <= '9')) {
            i = c - '0' + 52;
          } else if (c == '+') {
            i = 62;
          } else if (c == '/') {
            i = 63;
          } else if (Character.isWhitespace(c)) {
            i = j;
          }
        }
      }
    }
    for (;;)
    {
      m += 1;
      j = i;
      break;
      if (c == '=')
      {
        n += 1;
        i = j;
      }
      else
      {
        i = -1;
        break label126;
        i1 = (i1 << 6) + i;
        k += 1;
        if (k == 4)
        {
          i = j + 1;
          arrayOfByte[j] = ((byte)(i1 >> 16));
          j = i + 1;
          arrayOfByte[i] = ((byte)(i1 >> 8));
          i = j + 1;
          arrayOfByte[j] = ((byte)i1);
          k = 0;
          continue;
          if (k + n > 0)
          {
            if ((k + n == 4) && (((1 << n) - 1 & i1) == 0) && (j + 3 - n == arrayOfByte.length)) {}
          }
          else {
            while (j != arrayOfByte.length) {
              throw new IllegalArgumentException();
            }
          }
          switch (n)
          {
          }
          for (;;)
          {
            this.data = arrayOfByte;
            return;
            i = j + 1;
            arrayOfByte[j] = ((byte)(i1 << 10));
            arrayOfByte[i] = ((byte)(i1 >> 2));
            continue;
            arrayOfByte[j] = ((byte)(i1 >> 4));
          }
        }
        else
        {
          i = j;
        }
      }
    }
  }
  
  public Base64Binary(byte[] paramArrayOfByte)
  {
    this.data = paramArrayOfByte;
  }
  
  public static Base64Binary valueOf(String paramString)
  {
    return new Base64Binary(paramString);
  }
  
  public String toString()
  {
    return toString(new StringBuffer()).toString();
  }
  
  public StringBuffer toString(StringBuffer paramStringBuffer)
  {
    byte[] arrayOfByte = this.data;
    int n = arrayOfByte.length;
    int i = 0;
    int j = 0;
    while (j < n)
    {
      int k = i << 8 | arrayOfByte[j] & 0xFF;
      int m = j + 1;
      j = m;
      i = k;
      if (m % 3 == 0)
      {
        paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(k >> 18 & 0x3F));
        paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(k >> 12 & 0x3F));
        paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(k >> 6 & 0x3F));
        paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(k & 0x3F));
        j = m;
        i = k;
      }
    }
    switch (n % 3)
    {
    default: 
      return paramStringBuffer;
    case 1: 
      paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(i >> 2 & 0x3F));
      paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(i << 4 & 0x3F));
      paramStringBuffer.append("==");
      return paramStringBuffer;
    }
    paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(i >> 10 & 0x3F));
    paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(i >> 4 & 0x3F));
    paramStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(i << 2 & 0x3F));
    paramStringBuffer.append('=');
    return paramStringBuffer;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\Base64Binary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */