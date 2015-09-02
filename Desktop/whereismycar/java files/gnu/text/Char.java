package gnu.text;

import gnu.lists.Consumer;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Char
  implements Comparable, Externalizable
{
  static Char[] ascii;
  static char[] charNameValues = { 32, 9, 10, 10, 13, 12, 8, 27, 127, 127, 127, 7, 7, 11, 0 };
  static String[] charNames = { "space", "tab", "newline", "linefeed", "return", "page", "backspace", "esc", "delete", "del", "rubout", "alarm", "bel", "vtab", "nul" };
  static CharMap hashTable = new CharMap();
  int value;
  
  static
  {
    ascii = new Char[''];
    int i = 128;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      ascii[i] = new Char(i);
    }
  }
  
  public Char() {}
  
  Char(int paramInt)
  {
    this.value = paramInt;
  }
  
  public static Char make(int paramInt)
  {
    if (paramInt < 128) {
      return ascii[paramInt];
    }
    synchronized (hashTable)
    {
      Char localChar = hashTable.get(paramInt);
      return localChar;
    }
  }
  
  public static int nameToChar(String paramString)
  {
    int i = charNames.length;
    int j;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
    } while (!charNames[j].equals(paramString));
    int k = charNameValues[j];
    return k;
    i = charNames.length;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
    } while (!charNames[j].equalsIgnoreCase(paramString));
    return charNameValues[j];
    int m = paramString.length();
    if ((m > 1) && (paramString.charAt(0) == 'u'))
    {
      i = 0;
      j = 1;
    }
    for (;;)
    {
      k = i;
      if (j == m) {
        break;
      }
      k = Character.digit(paramString.charAt(j), 16);
      if (k < 0)
      {
        if ((m != 3) || (paramString.charAt(1) != '-')) {
          break label173;
        }
        i = paramString.charAt(0);
        if ((i != 99) && (i != 67)) {
          break label173;
        }
        return paramString.charAt(2) & 0x1F;
      }
      i = (i << 4) + k;
      j += 1;
    }
    label173:
    return -1;
  }
  
  public static void print(int paramInt, Consumer paramConsumer)
  {
    if (paramInt >= 65536)
    {
      paramConsumer.write((char)((paramInt - 65536 >> 10) + 55296));
      paramConsumer.write((char)((paramInt & 0x3FF) + 56320));
      return;
    }
    paramConsumer.write((char)paramInt);
  }
  
  public static String toScmReadableString(int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer(20);
    localStringBuffer.append("#\\");
    int i = 0;
    while (i < charNameValues.length)
    {
      if ((char)paramInt == charNameValues[i])
      {
        localStringBuffer.append(charNames[i]);
        return localStringBuffer.toString();
      }
      i += 1;
    }
    if ((paramInt < 32) || (paramInt > 127))
    {
      localStringBuffer.append('x');
      localStringBuffer.append(Integer.toString(paramInt, 16));
    }
    for (;;)
    {
      return localStringBuffer.toString();
      localStringBuffer.append((char)paramInt);
    }
  }
  
  public final char charValue()
  {
    return (char)this.value;
  }
  
  public int compareTo(Object paramObject)
  {
    return this.value - ((Char)paramObject).value;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject != null) && ((paramObject instanceof Char)) && (((Char)paramObject).intValue() == this.value);
  }
  
  public int hashCode()
  {
    return this.value;
  }
  
  public final int intValue()
  {
    return this.value;
  }
  
  public void print(Consumer paramConsumer)
  {
    print(this.value, paramConsumer);
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.value = paramObjectInput.readChar();
    if ((this.value >= 55296) && (this.value < 56319))
    {
      int i = paramObjectInput.readChar();
      if ((i >= 56320) && (i <= 57343)) {
        this.value = ((this.value - 55296 << 10) + (i - 56320) + 65536);
      }
    }
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    return make(this.value);
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append('\'');
    if ((this.value >= 32) && (this.value < 127) && (this.value != 39)) {
      localStringBuffer.append((char)this.value);
    }
    for (;;)
    {
      localStringBuffer.append('\'');
      return localStringBuffer.toString();
      localStringBuffer.append('\\');
      if (this.value == 39)
      {
        localStringBuffer.append('\'');
      }
      else if (this.value == 10)
      {
        localStringBuffer.append('n');
      }
      else if (this.value == 13)
      {
        localStringBuffer.append('r');
      }
      else if (this.value == 9)
      {
        localStringBuffer.append('t');
      }
      else
      {
        String str;
        int i;
        if (this.value < 256)
        {
          str = Integer.toOctalString(this.value);
          i = 3 - str.length();
          for (;;)
          {
            i -= 1;
            if (i < 0) {
              break;
            }
            localStringBuffer.append('0');
          }
          localStringBuffer.append(str);
        }
        else
        {
          localStringBuffer.append('u');
          str = Integer.toHexString(this.value);
          i = 4 - str.length();
          for (;;)
          {
            i -= 1;
            if (i < 0) {
              break;
            }
            localStringBuffer.append('0');
          }
          localStringBuffer.append(str);
        }
      }
    }
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    if (this.value > 55296)
    {
      if (this.value <= 65535) {
        break label63;
      }
      paramObjectOutput.writeChar((this.value - 65536 >> 10) + 55296);
      this.value = ((this.value & 0x3FF) + 56320);
    }
    for (;;)
    {
      paramObjectOutput.writeChar(this.value);
      return;
      label63:
      if (this.value <= 56319)
      {
        paramObjectOutput.writeChar(this.value);
        this.value = 0;
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\Char.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */