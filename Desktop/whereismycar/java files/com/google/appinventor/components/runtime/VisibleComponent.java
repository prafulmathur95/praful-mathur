package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;

@SimpleObject
public abstract class VisibleComponent
  implements Component
{
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public abstract int Height();
  
  @SimpleProperty
  public abstract void Height(int paramInt);
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public abstract int Width();
  
  @SimpleProperty
  public abstract void Width(int paramInt);
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\VisibleComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */