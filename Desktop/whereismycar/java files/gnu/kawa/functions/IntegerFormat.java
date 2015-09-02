package gnu.kawa.functions;

import gnu.math.IntNum;
import gnu.math.RealNum;
import gnu.text.EnglishIntegerFormat;
import gnu.text.RomanIntegerFormat;
import java.text.Format;

public class IntegerFormat
  extends gnu.text.IntegerFormat
{
  private static IntegerFormat plainDecimalFormat;
  
  public static IntegerFormat getInstance()
  {
    if (plainDecimalFormat == null) {
      plainDecimalFormat = new IntegerFormat();
    }
    return plainDecimalFormat;
  }
  
  public static Format getInstance(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    boolean bool = true;
    int i = paramInt1;
    if (paramInt1 == -1073741824)
    {
      if ((paramInt3 == -1073741824) && (paramInt3 == -1073741824) && (paramInt4 == -1073741824) && (paramInt5 == -1073741824))
      {
        if ((paramInt6 & 0x1) != 0) {}
        while ((paramInt6 & 0x2) != 0)
        {
          return RomanIntegerFormat.getInstance(bool);
          bool = false;
        }
        return EnglishIntegerFormat.getInstance(bool);
      }
      i = 10;
    }
    paramInt1 = paramInt2;
    if (paramInt2 == -1073741824) {
      paramInt1 = 1;
    }
    paramInt2 = paramInt3;
    if (paramInt3 == -1073741824) {
      paramInt2 = 32;
    }
    paramInt3 = paramInt4;
    if (paramInt4 == -1073741824) {
      paramInt3 = 44;
    }
    paramInt4 = paramInt5;
    if (paramInt5 == -1073741824) {
      paramInt4 = 3;
    }
    if ((i == 10) && (paramInt1 == 1) && (paramInt2 == 32) && (paramInt3 == 44) && (paramInt4 == 3) && (paramInt6 == 0)) {
      return getInstance();
    }
    IntegerFormat localIntegerFormat = new IntegerFormat();
    localIntegerFormat.base = i;
    localIntegerFormat.minWidth = paramInt1;
    localIntegerFormat.padChar = paramInt2;
    localIntegerFormat.commaChar = paramInt3;
    localIntegerFormat.commaInterval = paramInt4;
    localIntegerFormat.flags = paramInt6;
    return localIntegerFormat;
  }
  
  public String convertToIntegerString(Object paramObject, int paramInt)
  {
    if ((paramObject instanceof RealNum)) {
      return ((RealNum)paramObject).toExactInt(4).toString(paramInt);
    }
    return super.convertToIntegerString(paramObject, paramInt);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\IntegerFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */