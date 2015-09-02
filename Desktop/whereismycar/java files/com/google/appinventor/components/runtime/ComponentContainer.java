package com.google.appinventor.components.runtime;

import android.app.Activity;

public abstract interface ComponentContainer
{
  public abstract void $add(AndroidViewComponent paramAndroidViewComponent);
  
  public abstract Activity $context();
  
  public abstract Form $form();
  
  public abstract void setChildHeight(AndroidViewComponent paramAndroidViewComponent, int paramInt);
  
  public abstract void setChildWidth(AndroidViewComponent paramAndroidViewComponent, int paramInt);
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\ComponentContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */