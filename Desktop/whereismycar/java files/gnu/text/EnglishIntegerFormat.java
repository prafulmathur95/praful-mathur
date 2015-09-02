package gnu.text;

import gnu.lists.Consumer;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class EnglishIntegerFormat
  extends NumberFormat
{
  private static EnglishIntegerFormat cardinalEnglish;
  public static final String[] ones = { null, "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
  public static final String[] onesth = { null, "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelveth", "thirteenth", "fourteenth", "fifteenth", "sixteenth", "seventeenth", "eighteenth", "nineteenth" };
  private static EnglishIntegerFormat ordinalEnglish;
  public static final String[] power1000s = { null, " thousand", " million", " billion", " trillion", " quadrillion", " quintillion", " sextillion", " septillion", " octillion", " nonillion", " decillion", " undecillion", " duodecillion", " tredecillion", " quattuordecillion", " quindecillion", " sexdecillion", " septendecillion", " octodecillion", " novemdecillion", " vigintillion" };
  public static final String[] tens = { null, null, "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
  public static final String[] tensth = { null, null, "twentieth", "thirtieth", "fortieth", "fiftieth", "sixtieth", "seventieth", "eightieth", "ninetieth" };
  public boolean ordinal;
  
  public EnglishIntegerFormat(boolean paramBoolean)
  {
    this.ordinal = paramBoolean;
  }
  
  public static EnglishIntegerFormat getInstance(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (ordinalEnglish == null) {
        ordinalEnglish = new EnglishIntegerFormat(true);
      }
      return ordinalEnglish;
    }
    if (cardinalEnglish == null) {
      cardinalEnglish = new EnglishIntegerFormat(false);
    }
    return cardinalEnglish;
  }
  
  public StringBuffer format(double paramDouble, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    long l = paramDouble;
    if (l == paramDouble) {
      return format(l, paramStringBuffer, paramFieldPosition);
    }
    paramStringBuffer.append(Double.toString(paramDouble));
    return paramStringBuffer;
  }
  
  public StringBuffer format(long paramLong, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    String str;
    if (paramLong == 0L) {
      if (this.ordinal)
      {
        str = "zeroth";
        paramStringBuffer.append(str);
      }
    }
    for (;;)
    {
      if (paramFieldPosition != null) {}
      return paramStringBuffer;
      str = "zero";
      break;
      long l = paramLong;
      if (paramLong < 0L)
      {
        paramStringBuffer.append("minus ");
        l = -paramLong;
      }
      format(paramStringBuffer, l, 0, this.ordinal);
    }
  }
  
  void format(StringBuffer paramStringBuffer, long paramLong, int paramInt, boolean paramBoolean)
  {
    long l = paramLong;
    if (paramLong >= 1000L)
    {
      format(paramStringBuffer, paramLong / 1000L, paramInt + 1, false);
      paramLong %= 1000L;
      if (paramLong > 0L)
      {
        paramStringBuffer.append(", ");
        l = paramLong;
      }
    }
    else if (l > 0L)
    {
      int i = (int)l;
      if ((!paramBoolean) || (paramInt != 0)) {
        break label139;
      }
      paramBoolean = true;
      label73:
      format999(paramStringBuffer, i, paramBoolean);
      if (paramInt < power1000s.length) {
        break label145;
      }
      paramStringBuffer.append(" times ten to the ");
      format(paramStringBuffer, paramInt * 3, 0, true);
      paramStringBuffer.append(" power");
    }
    label139:
    label145:
    while (paramInt <= 0)
    {
      return;
      l = paramLong;
      if (!paramBoolean) {
        break;
      }
      paramStringBuffer.append("th");
      l = paramLong;
      break;
      paramBoolean = false;
      break label73;
    }
    paramStringBuffer.append(power1000s[paramInt]);
  }
  
  void format999(StringBuffer paramStringBuffer, int paramInt, boolean paramBoolean)
  {
    int i = paramInt;
    if (paramInt >= 100)
    {
      i = paramInt / 100;
      paramInt %= 100;
      if (i > 1)
      {
        paramStringBuffer.append(ones[i]);
        paramStringBuffer.append(' ');
      }
      paramStringBuffer.append("hundred");
      if (paramInt > 0)
      {
        paramStringBuffer.append(' ');
        i = paramInt;
      }
    }
    else
    {
      paramInt = i;
      if (i >= 20)
      {
        paramInt = i / 10;
        i %= 10;
        if ((!paramBoolean) || (i != 0)) {
          break label172;
        }
        arrayOfString = tensth;
        label102:
        paramStringBuffer.append(arrayOfString[paramInt]);
        paramInt = i;
        if (i > 0)
        {
          paramStringBuffer.append('-');
          paramInt = i;
        }
      }
      if (paramInt > 0) {
        if (!paramBoolean) {
          break label180;
        }
      }
    }
    label172:
    label180:
    for (String[] arrayOfString = onesth;; arrayOfString = ones)
    {
      paramStringBuffer.append(arrayOfString[paramInt]);
      return;
      i = paramInt;
      if (!paramBoolean) {
        break;
      }
      paramStringBuffer.append("th");
      i = paramInt;
      break;
      arrayOfString = tens;
      break label102;
    }
  }
  
  public Number parse(String paramString, ParsePosition paramParsePosition)
  {
    throw new Error("EnglishIntegerFormat.parseObject - not implemented");
  }
  
  public void writeBoolean(boolean paramBoolean, Consumer paramConsumer)
  {
    if (paramBoolean) {}
    for (long l = 1L;; l = 0L)
    {
      writeLong(l, paramConsumer);
      return;
    }
  }
  
  public void writeInt(int paramInt, Consumer paramConsumer)
  {
    writeLong(paramInt, paramConsumer);
  }
  
  public void writeLong(long paramLong, Consumer paramConsumer)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    format(paramLong, localStringBuffer, null);
    paramConsumer.write(localStringBuffer, 0, localStringBuffer.length());
  }
  
  public void writeObject(Object paramObject, Consumer paramConsumer)
  {
    writeLong(((Number)paramObject).longValue(), paramConsumer);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\EnglishIntegerFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */