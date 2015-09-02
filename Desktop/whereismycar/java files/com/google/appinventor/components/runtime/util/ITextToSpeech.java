package com.google.appinventor.components.runtime.util;

import java.util.Locale;

public abstract interface ITextToSpeech
{
  public abstract boolean isInitialized();
  
  public abstract int isLanguageAvailable(Locale paramLocale);
  
  public abstract void onDestroy();
  
  public abstract void onResume();
  
  public abstract void onStop();
  
  public abstract void setPitch(float paramFloat);
  
  public abstract void setSpeechRate(float paramFloat);
  
  public abstract void speak(String paramString, Locale paramLocale);
  
  public static abstract interface TextToSpeechCallback
  {
    public abstract void onFailure();
    
    public abstract void onSuccess();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\ITextToSpeech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */