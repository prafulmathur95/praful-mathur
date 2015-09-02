package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category=ComponentCategory.LAYOUT, description="<p>A formatting element in which to place components that should be displayed from left to right.  If you wish to have components displayed one over another, use <code>VerticalArrangement</code> instead.</p>", version=2)
@SimpleObject
public class HorizontalArrangement
  extends HVArrangement
{
  public HorizontalArrangement(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer, 0);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\HorizontalArrangement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */