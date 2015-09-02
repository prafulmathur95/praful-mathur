package com.google.appinventor.components.runtime.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class YailNumberToString
{
  private static final double BIGBOUND = 1000000.0D;
  static final String LOG_TAG = "YailNumberToString";
  private static final double SMALLBOUND = 1.0E-6D;
  private static final String decPattern = "#####0.0####";
  static DecimalFormat decimalFormat = new DecimalFormat("#####0.0####", symbols);
  static Locale locale = Locale.US;
  static DecimalFormat sciFormat = new DecimalFormat("0.####E0", symbols);
  private static final String sciPattern = "0.####E0";
  static DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
  
  public static String format(double paramDouble)
  {
    if (paramDouble == Math.rint(paramDouble)) {
      return String.valueOf((int)paramDouble);
    }
    double d = Math.abs(paramDouble);
    if ((d < 1000000.0D) && (d > 1.0E-6D)) {
      return decimalFormat.format(paramDouble);
    }
    return sciFormat.format(paramDouble);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\YailNumberToString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */