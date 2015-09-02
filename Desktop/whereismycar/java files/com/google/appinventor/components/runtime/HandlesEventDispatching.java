package com.google.appinventor.components.runtime;

public abstract interface HandlesEventDispatching
{
  public abstract boolean canDispatchEvent(Component paramComponent, String paramString);
  
  public abstract boolean dispatchEvent(Component paramComponent, String paramString1, String paramString2, Object[] paramArrayOfObject);
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\HandlesEventDispatching.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */