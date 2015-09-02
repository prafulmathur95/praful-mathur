package com.google.appinventor.components.runtime.util;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.widget.TextView;

public class TextViewUtil
{
  public static float getFontSize(TextView paramTextView)
  {
    return paramTextView.getTextSize();
  }
  
  public static String getText(TextView paramTextView)
  {
    return paramTextView.getText().toString();
  }
  
  public static boolean isEnabled(TextView paramTextView)
  {
    return paramTextView.isEnabled();
  }
  
  public static void setAlignment(TextView paramTextView, int paramInt, boolean paramBoolean)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException();
    case 0: 
      paramInt = 3;
      if (!paramBoolean) {
        break;
      }
    }
    for (int i = 16;; i = 48)
    {
      paramTextView.setGravity(paramInt | i);
      paramTextView.invalidate();
      return;
      paramInt = 1;
      break;
      paramInt = 5;
      break;
    }
  }
  
  public static void setBackgroundColor(TextView paramTextView, int paramInt)
  {
    paramTextView.setBackgroundColor(paramInt);
    paramTextView.invalidate();
  }
  
  public static void setEnabled(TextView paramTextView, boolean paramBoolean)
  {
    paramTextView.setEnabled(paramBoolean);
    paramTextView.invalidate();
  }
  
  public static void setFontSize(TextView paramTextView, float paramFloat)
  {
    paramTextView.setTextSize(paramFloat);
    paramTextView.requestLayout();
  }
  
  public static void setFontTypeface(TextView paramTextView, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    Typeface localTypeface;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException();
    case 0: 
      localTypeface = Typeface.DEFAULT;
    }
    for (;;)
    {
      paramInt = 0;
      if (paramBoolean1) {
        paramInt = 0x0 | 0x1;
      }
      int i = paramInt;
      if (paramBoolean2) {
        i = paramInt | 0x2;
      }
      paramTextView.setTypeface(Typeface.create(localTypeface, i));
      paramTextView.requestLayout();
      return;
      localTypeface = Typeface.SERIF;
      continue;
      localTypeface = Typeface.SANS_SERIF;
      continue;
      localTypeface = Typeface.MONOSPACE;
    }
  }
  
  public static void setPadding(TextView paramTextView, int paramInt)
  {
    paramTextView.setPadding(paramInt, paramInt, 0, 0);
    paramTextView.requestLayout();
  }
  
  public static void setText(TextView paramTextView, String paramString)
  {
    paramTextView.setText(paramString);
    paramTextView.requestLayout();
  }
  
  public static void setTextColor(TextView paramTextView, int paramInt)
  {
    paramTextView.setTextColor(paramInt);
    paramTextView.invalidate();
  }
  
  public static void setTextColors(TextView paramTextView, ColorStateList paramColorStateList)
  {
    paramTextView.setTextColor(paramColorStateList);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\TextViewUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */