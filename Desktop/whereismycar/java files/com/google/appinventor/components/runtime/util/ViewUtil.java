package com.google.appinventor.components.runtime.util;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableRow.LayoutParams;

public final class ViewUtil
{
  public static void setBackgroundDrawable(View paramView, Drawable paramDrawable)
  {
    paramView.setBackgroundDrawable(paramDrawable);
    paramView.invalidate();
  }
  
  public static void setBackgroundImage(View paramView, Drawable paramDrawable)
  {
    paramView.setBackgroundDrawable(paramDrawable);
    paramView.requestLayout();
  }
  
  public static void setChildHeightForHorizontalLayout(View paramView, int paramInt)
  {
    Object localObject = paramView.getLayoutParams();
    if ((localObject instanceof LinearLayout.LayoutParams))
    {
      localObject = (LinearLayout.LayoutParams)localObject;
      switch (paramInt)
      {
      default: 
        ((LinearLayout.LayoutParams)localObject).height = paramInt;
      }
      for (;;)
      {
        paramView.requestLayout();
        return;
        ((LinearLayout.LayoutParams)localObject).height = -2;
        continue;
        ((LinearLayout.LayoutParams)localObject).height = -1;
      }
    }
    Log.e("ViewUtil", "The view does not have linear layout parameters");
  }
  
  public static void setChildHeightForTableLayout(View paramView, int paramInt)
  {
    Object localObject = paramView.getLayoutParams();
    if ((localObject instanceof TableRow.LayoutParams))
    {
      localObject = (TableRow.LayoutParams)localObject;
      switch (paramInt)
      {
      default: 
        ((TableRow.LayoutParams)localObject).height = paramInt;
      }
      for (;;)
      {
        paramView.requestLayout();
        return;
        ((TableRow.LayoutParams)localObject).height = -2;
        continue;
        ((TableRow.LayoutParams)localObject).height = -1;
      }
    }
    Log.e("ViewUtil", "The view does not have table layout parameters");
  }
  
  public static void setChildHeightForVerticalLayout(View paramView, int paramInt)
  {
    Object localObject = paramView.getLayoutParams();
    if ((localObject instanceof LinearLayout.LayoutParams))
    {
      localObject = (LinearLayout.LayoutParams)localObject;
      switch (paramInt)
      {
      default: 
        ((LinearLayout.LayoutParams)localObject).height = paramInt;
        ((LinearLayout.LayoutParams)localObject).weight = 0.0F;
      }
      for (;;)
      {
        paramView.requestLayout();
        return;
        ((LinearLayout.LayoutParams)localObject).height = -2;
        ((LinearLayout.LayoutParams)localObject).weight = 0.0F;
        continue;
        ((LinearLayout.LayoutParams)localObject).height = 0;
        ((LinearLayout.LayoutParams)localObject).weight = 1.0F;
      }
    }
    Log.e("ViewUtil", "The view does not have linear layout parameters");
  }
  
  public static void setChildWidthForHorizontalLayout(View paramView, int paramInt)
  {
    Object localObject = paramView.getLayoutParams();
    if ((localObject instanceof LinearLayout.LayoutParams))
    {
      localObject = (LinearLayout.LayoutParams)localObject;
      switch (paramInt)
      {
      default: 
        ((LinearLayout.LayoutParams)localObject).width = paramInt;
        ((LinearLayout.LayoutParams)localObject).weight = 0.0F;
      }
      for (;;)
      {
        paramView.requestLayout();
        return;
        ((LinearLayout.LayoutParams)localObject).width = -2;
        ((LinearLayout.LayoutParams)localObject).weight = 0.0F;
        continue;
        ((LinearLayout.LayoutParams)localObject).width = 0;
        ((LinearLayout.LayoutParams)localObject).weight = 1.0F;
      }
    }
    Log.e("ViewUtil", "The view does not have linear layout parameters");
  }
  
  public static void setChildWidthForTableLayout(View paramView, int paramInt)
  {
    Object localObject = paramView.getLayoutParams();
    if ((localObject instanceof TableRow.LayoutParams))
    {
      localObject = (TableRow.LayoutParams)localObject;
      switch (paramInt)
      {
      default: 
        ((TableRow.LayoutParams)localObject).width = paramInt;
      }
      for (;;)
      {
        paramView.requestLayout();
        return;
        ((TableRow.LayoutParams)localObject).width = -2;
        continue;
        ((TableRow.LayoutParams)localObject).width = -1;
      }
    }
    Log.e("ViewUtil", "The view does not have table layout parameters");
  }
  
  public static void setChildWidthForVerticalLayout(View paramView, int paramInt)
  {
    Object localObject = paramView.getLayoutParams();
    if ((localObject instanceof LinearLayout.LayoutParams))
    {
      localObject = (LinearLayout.LayoutParams)localObject;
      switch (paramInt)
      {
      default: 
        ((LinearLayout.LayoutParams)localObject).width = paramInt;
      }
      for (;;)
      {
        paramView.requestLayout();
        return;
        ((LinearLayout.LayoutParams)localObject).width = -2;
        continue;
        ((LinearLayout.LayoutParams)localObject).width = -1;
      }
    }
    Log.e("ViewUtil", "The view does not have linear layout parameters");
  }
  
  public static void setImage(ImageView paramImageView, Drawable paramDrawable)
  {
    paramImageView.setImageDrawable(paramDrawable);
    if (paramDrawable != null) {
      paramImageView.setAdjustViewBounds(true);
    }
    paramImageView.requestLayout();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\ViewUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */