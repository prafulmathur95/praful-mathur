package com.google.appinventor.components.runtime;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public final class LinearLayout
  implements Layout
{
  private final android.widget.LinearLayout layoutManager;
  
  LinearLayout(Context paramContext, int paramInt)
  {
    this(paramContext, paramInt, null, null);
  }
  
  LinearLayout(Context paramContext, int paramInt, final Integer paramInteger1, final Integer paramInteger2)
  {
    if (((paramInteger1 == null) && (paramInteger2 != null)) || ((paramInteger1 != null) && (paramInteger2 == null))) {
      throw new IllegalArgumentException("LinearLayout - preferredEmptyWidth and preferredEmptyHeight must be either both null or both not null");
    }
    this.layoutManager = new android.widget.LinearLayout(paramContext)
    {
      private int getSize(int paramAnonymousInt1, int paramAnonymousInt2)
      {
        int j = View.MeasureSpec.getMode(paramAnonymousInt1);
        int i = View.MeasureSpec.getSize(paramAnonymousInt1);
        if (j == 1073741824) {
          paramAnonymousInt1 = i;
        }
        do
        {
          return paramAnonymousInt1;
          paramAnonymousInt1 = paramAnonymousInt2;
        } while (j != Integer.MIN_VALUE);
        return Math.min(paramAnonymousInt2, i);
      }
      
      protected void onMeasure(int paramAnonymousInt1, int paramAnonymousInt2)
      {
        if ((paramInteger1 == null) || (paramInteger2 == null))
        {
          super.onMeasure(paramAnonymousInt1, paramAnonymousInt2);
          return;
        }
        if (getChildCount() != 0)
        {
          super.onMeasure(paramAnonymousInt1, paramAnonymousInt2);
          return;
        }
        setMeasuredDimension(getSize(paramAnonymousInt1, paramInteger1.intValue()), getSize(paramAnonymousInt2, paramInteger2.intValue()));
      }
    };
    paramContext = this.layoutManager;
    if (paramInt == 0) {}
    for (paramInt = 0;; paramInt = 1)
    {
      paramContext.setOrientation(paramInt);
      return;
    }
  }
  
  public void add(AndroidViewComponent paramAndroidViewComponent)
  {
    this.layoutManager.addView(paramAndroidViewComponent.getView(), new LinearLayout.LayoutParams(-2, -2, 0.0F));
  }
  
  public ViewGroup getLayoutManager()
  {
    return this.layoutManager;
  }
  
  public void setHorizontalGravity(int paramInt)
  {
    this.layoutManager.setHorizontalGravity(paramInt);
  }
  
  public void setVerticalGravity(int paramInt)
  {
    this.layoutManager.setVerticalGravity(paramInt);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\LinearLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */