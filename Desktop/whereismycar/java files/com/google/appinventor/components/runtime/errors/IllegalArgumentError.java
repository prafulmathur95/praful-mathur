package com.google.appinventor.components.runtime.errors;

import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public class IllegalArgumentError
  extends RuntimeError
{
  public IllegalArgumentError() {}
  
  public IllegalArgumentError(String paramString)
  {
    super(paramString);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\errors\IllegalArgumentError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */