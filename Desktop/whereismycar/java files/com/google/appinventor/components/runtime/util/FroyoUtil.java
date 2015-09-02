package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.http.SslError;
import android.view.Display;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.Player;
import com.google.appinventor.components.runtime.Player.State;

public class FroyoUtil
{
  public static void abandonFocus(AudioManager paramAudioManager, Object paramObject)
  {
    paramAudioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener)paramObject);
  }
  
  public static boolean focusRequestGranted(AudioManager paramAudioManager, Object paramObject)
  {
    return paramAudioManager.requestAudioFocus((AudioManager.OnAudioFocusChangeListener)paramObject, 3, 1) == 1;
  }
  
  public static int getRotation(Display paramDisplay)
  {
    return paramDisplay.getRotation();
  }
  
  public static WebViewClient getWebViewClient(final boolean paramBoolean1, boolean paramBoolean2, final Form paramForm, final Component paramComponent)
  {
    new WebViewClient()
    {
      public void onReceivedSslError(WebView paramAnonymousWebView, SslErrorHandler paramAnonymousSslErrorHandler, SslError paramAnonymousSslError)
      {
        if (paramBoolean1)
        {
          paramAnonymousSslErrorHandler.proceed();
          return;
        }
        paramAnonymousSslErrorHandler.cancel();
        paramForm.dispatchErrorOccurredEvent(paramComponent, "WebView", 2501, new Object[0]);
      }
      
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        return !this.val$followLinks;
      }
    };
  }
  
  public static Object setAudioFocusChangeListener(Player paramPlayer)
  {
    new AudioManager.OnAudioFocusChangeListener()
    {
      private boolean playbackFlag = false;
      
      public void onAudioFocusChange(int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        }
        do
        {
          do
          {
            return;
          } while ((this.val$player == null) || (this.val$player.playerState != Player.State.PLAYING));
          this.val$player.pause();
          this.playbackFlag = true;
          return;
          this.playbackFlag = false;
          this.val$player.OtherPlayerStarted();
          return;
        } while ((this.val$player == null) || (!this.playbackFlag) || (this.val$player.playerState != Player.State.PAUSED_BY_EVENT));
        this.val$player.Start();
        this.playbackFlag = false;
      }
    };
  }
  
  public static AudioManager setAudioManager(Activity paramActivity)
  {
    return (AudioManager)paramActivity.getSystemService("audio");
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\FroyoUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */