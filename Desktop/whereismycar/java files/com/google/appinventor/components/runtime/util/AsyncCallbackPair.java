package com.google.appinventor.components.runtime.util;

public abstract interface AsyncCallbackPair<T>
{
  public abstract void onFailure(String paramString);
  
  public abstract void onSuccess(T paramT);
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\AsyncCallbackPair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */