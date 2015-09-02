package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.Log;
import java.util.HashMap;
import java.util.Locale;

public class InternalTextToSpeech
  implements ITextToSpeech
{
  private static final String LOG_TAG = "InternalTTS";
  private final Activity activity;
  private final ITextToSpeech.TextToSpeechCallback callback;
  private volatile boolean isTtsInitialized;
  private Handler mHandler = new Handler();
  private int nextUtteranceId = 1;
  private TextToSpeech tts;
  private int ttsMaxRetries = 20;
  private int ttsRetryDelay = 500;
  
  public InternalTextToSpeech(Activity paramActivity, ITextToSpeech.TextToSpeechCallback paramTextToSpeechCallback)
  {
    this.activity = paramActivity;
    this.callback = paramTextToSpeechCallback;
    initializeTts();
  }
  
  private void initializeTts()
  {
    if (this.tts == null)
    {
      Log.d("InternalTTS", "INTERNAL TTS is reinitializing");
      this.tts = new TextToSpeech(this.activity, new TextToSpeech.OnInitListener()
      {
        public void onInit(int paramAnonymousInt)
        {
          if (paramAnonymousInt == 0) {
            InternalTextToSpeech.access$002(InternalTextToSpeech.this, true);
          }
        }
      });
    }
  }
  
  private void speak(final String paramString, final Locale paramLocale, final int paramInt)
  {
    Log.d("InternalTTS", "InternalTTS speak called, message = " + paramString);
    if (paramInt > this.ttsMaxRetries)
    {
      Log.d("InternalTTS", "max number of speak retries exceeded: speak will fail");
      this.callback.onFailure();
    }
    if (this.isTtsInitialized)
    {
      Log.d("InternalTTS", "TTS initialized after " + paramInt + " retries.");
      this.tts.setLanguage(paramLocale);
      this.tts.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener()
      {
        public void onUtteranceCompleted(String paramAnonymousString)
        {
          InternalTextToSpeech.this.activity.runOnUiThread(new Runnable()
          {
            public void run()
            {
              InternalTextToSpeech.this.callback.onSuccess();
            }
          });
        }
      });
      paramLocale = new HashMap();
      paramInt = this.nextUtteranceId;
      this.nextUtteranceId = (paramInt + 1);
      paramLocale.put("utteranceId", Integer.toString(paramInt));
      TextToSpeech localTextToSpeech1 = this.tts;
      TextToSpeech localTextToSpeech2 = this.tts;
      if (localTextToSpeech1.speak(paramString, 0, paramLocale) == -1)
      {
        Log.d("InternalTTS", "speak called and tts.speak result was an error");
        this.callback.onFailure();
      }
      return;
    }
    Log.d("InternalTTS", "speak called when TTS not initialized");
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        Log.d("InternalTTS", "delaying call to speak.  Retries is: " + paramInt + " Message is: " + paramString);
        InternalTextToSpeech.this.speak(paramString, paramLocale, paramInt + 1);
      }
    }, this.ttsRetryDelay);
  }
  
  public boolean isInitialized()
  {
    return this.isTtsInitialized;
  }
  
  public int isLanguageAvailable(Locale paramLocale)
  {
    return this.tts.isLanguageAvailable(paramLocale);
  }
  
  public void onDestroy()
  {
    Log.d("InternalTTS", "Internal TTS got onDestroy");
    if (this.tts != null)
    {
      this.tts.shutdown();
      this.isTtsInitialized = false;
      this.tts = null;
    }
  }
  
  public void onResume()
  {
    Log.d("InternalTTS", "Internal TTS got onResume");
    initializeTts();
  }
  
  public void onStop()
  {
    Log.d("InternalTTS", "Internal TTS got onStop");
  }
  
  public void setPitch(float paramFloat)
  {
    this.tts.setPitch(paramFloat);
  }
  
  public void setSpeechRate(float paramFloat)
  {
    this.tts.setSpeechRate(paramFloat);
  }
  
  public void speak(String paramString, Locale paramLocale)
  {
    Log.d("InternalTTS", "Internal TTS got speak");
    speak(paramString, paramLocale, 0);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\InternalTextToSpeech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */