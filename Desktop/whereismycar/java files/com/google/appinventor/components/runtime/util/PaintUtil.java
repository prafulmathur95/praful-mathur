package com.google.appinventor.components.runtime.util;

import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;

public class PaintUtil
{
  public static void changePaint(Paint paramPaint, int paramInt)
  {
    paramPaint.setColor(0xFFFFFF & paramInt);
    paramPaint.setAlpha(paramInt >> 24 & 0xFF);
    paramPaint.setXfermode(null);
  }
  
  public static void changePaintTransparent(Paint paramPaint)
  {
    paramPaint.setAlpha(0);
    paramPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\PaintUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */