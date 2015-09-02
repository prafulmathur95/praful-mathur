package com.google.appinventor.components.runtime.util;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.VideoView;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.VideoPlayer;
import java.io.IOException;

public class FullScreenVideoUtil
  implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener
{
  public static final String ACTION_DATA = "ActionData";
  public static final String ACTION_SUCESS = "ActionSuccess";
  public static final int FULLSCREEN_VIDEO_ACTION_DURATION = 196;
  public static final int FULLSCREEN_VIDEO_ACTION_FULLSCREEN = 195;
  public static final int FULLSCREEN_VIDEO_ACTION_PAUSE = 192;
  public static final int FULLSCREEN_VIDEO_ACTION_PLAY = 191;
  public static final int FULLSCREEN_VIDEO_ACTION_SEEK = 190;
  public static final int FULLSCREEN_VIDEO_ACTION_SOURCE = 194;
  public static final int FULLSCREEN_VIDEO_ACTION_STOP = 193;
  public static final int FULLSCREEN_VIDEO_DIALOG_FLAG = 189;
  public static final String VIDEOPLAYER_FULLSCREEN = "FullScreenKey";
  public static final String VIDEOPLAYER_PLAYING = "PlayingKey";
  public static final String VIDEOPLAYER_POSITION = "PositionKey";
  public static final String VIDEOPLAYER_SOURCE = "SourceKey";
  private Form mForm;
  private VideoPlayer mFullScreenPlayer = null;
  private Bundle mFullScreenVideoBundle;
  private CustomMediaController mFullScreenVideoController;
  private Dialog mFullScreenVideoDialog;
  private FrameLayout mFullScreenVideoHolder;
  private VideoView mFullScreenVideoView;
  private Handler mHandler;
  private FrameLayout.LayoutParams mMediaControllerParams = new FrameLayout.LayoutParams(-1, -2, 80);
  
  public FullScreenVideoUtil(Form paramForm, Handler paramHandler)
  {
    this.mForm = paramForm;
    this.mHandler = paramHandler;
    if (SdkLevel.getLevel() > 4)
    {
      this.mFullScreenVideoDialog = new Dialog(this.mForm, 16973831)
      {
        public void onBackPressed()
        {
          Bundle localBundle = new Bundle();
          localBundle.putInt("PositionKey", FullScreenVideoUtil.this.mFullScreenVideoView.getCurrentPosition());
          localBundle.putBoolean("PlayingKey", FullScreenVideoUtil.this.mFullScreenVideoView.isPlaying());
          localBundle.putString("SourceKey", FullScreenVideoUtil.this.mFullScreenVideoBundle.getString("SourceKey"));
          FullScreenVideoUtil.this.mFullScreenPlayer.fullScreenKilled(localBundle);
          super.onBackPressed();
        }
        
        public void onStart()
        {
          super.onStart();
          FullScreenVideoUtil.this.startDialog();
        }
      };
      return;
    }
    this.mFullScreenVideoDialog = new Dialog(this.mForm, 16973831)
    {
      public void onStart()
      {
        super.onStart();
        FullScreenVideoUtil.this.startDialog();
      }
      
      protected void onStop()
      {
        Bundle localBundle = new Bundle();
        localBundle.putInt("PositionKey", FullScreenVideoUtil.this.mFullScreenVideoView.getCurrentPosition());
        localBundle.putBoolean("PlayingKey", FullScreenVideoUtil.this.mFullScreenVideoView.isPlaying());
        localBundle.putString("SourceKey", FullScreenVideoUtil.this.mFullScreenVideoBundle.getString("SourceKey"));
        FullScreenVideoUtil.this.mFullScreenPlayer.fullScreenKilled(localBundle);
        super.onStop();
      }
    };
  }
  
  private Bundle doFullScreenVideoAction(VideoPlayer paramVideoPlayer, Bundle paramBundle)
  {
    Log.i("Form.doFullScreenVideoAction", "Source:" + paramVideoPlayer + " Data:" + paramBundle);
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("ActionSuccess", true);
    if (paramBundle.getBoolean("FullScreenKey") == true)
    {
      this.mFullScreenPlayer = paramVideoPlayer;
      this.mFullScreenVideoBundle = paramBundle;
      if (!this.mFullScreenVideoDialog.isShowing())
      {
        this.mForm.showDialog(189);
        return localBundle;
      }
      this.mFullScreenVideoView.pause();
      localBundle.putBoolean("ActionSuccess", setSource(this.mFullScreenVideoBundle.getString("SourceKey"), false));
      return localBundle;
    }
    if (showing())
    {
      localBundle.putBoolean("PlayingKey", this.mFullScreenVideoView.isPlaying());
      localBundle.putInt("PositionKey", this.mFullScreenVideoView.getCurrentPosition());
      localBundle.putString("SourceKey", this.mFullScreenVideoBundle.getString("SourceKey"));
      this.mFullScreenPlayer = null;
      this.mFullScreenVideoBundle = null;
      this.mForm.dismissDialog(189);
      return localBundle;
    }
    localBundle.putBoolean("ActionSuccess", false);
    return localBundle;
  }
  
  public Dialog createFullScreenVideoDialog()
  {
    if (this.mFullScreenVideoBundle == null) {
      Log.i("Form.createFullScreenVideoDialog", "mFullScreenVideoBundle is null");
    }
    this.mFullScreenVideoView = new VideoView(this.mForm);
    this.mFullScreenVideoHolder = new FrameLayout(this.mForm);
    this.mFullScreenVideoController = new CustomMediaController(this.mForm);
    this.mFullScreenVideoView.setId(this.mFullScreenVideoView.hashCode());
    this.mFullScreenVideoHolder.setId(this.mFullScreenVideoHolder.hashCode());
    this.mFullScreenVideoView.setMediaController(this.mFullScreenVideoController);
    this.mFullScreenVideoView.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        Log.i("FullScreenVideoUtil..onTouch", "Video Touched!!");
        return false;
      }
    });
    this.mFullScreenVideoController.setAnchorView(this.mFullScreenVideoView);
    String str = this.mForm.ScreenOrientation();
    if ((str.equals("landscape")) || (str.equals("sensorLandscape")) || (str.equals("reverseLandscape"))) {
      this.mFullScreenVideoView.setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 17));
    }
    for (;;)
    {
      this.mFullScreenVideoHolder.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
      this.mFullScreenVideoHolder.addView(this.mFullScreenVideoView);
      this.mFullScreenVideoController.addTo(this.mFullScreenVideoHolder, this.mMediaControllerParams);
      this.mFullScreenVideoDialog.setContentView(this.mFullScreenVideoHolder);
      return this.mFullScreenVideoDialog;
      this.mFullScreenVideoView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 17));
    }
  }
  
  public boolean dialogInitialized()
  {
    return this.mFullScreenVideoDialog != null;
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    if (this.mFullScreenPlayer != null) {
      this.mFullScreenPlayer.Completed();
    }
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    Log.i("FullScreenVideoUtil..onPrepared", "Seeking to:" + this.mFullScreenVideoBundle.getInt("PositionKey"));
    this.mFullScreenVideoView.seekTo(this.mFullScreenVideoBundle.getInt("PositionKey"));
    if (this.mFullScreenVideoBundle.getBoolean("PlayingKey"))
    {
      this.mFullScreenVideoView.start();
      return;
    }
    this.mFullScreenVideoView.start();
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        FullScreenVideoUtil.this.mFullScreenVideoView.pause();
      }
    }, 100L);
  }
  
  public Bundle performAction(int paramInt, VideoPlayer paramVideoPlayer, Object paramObject)
  {
    for (;;)
    {
      Bundle localBundle;
      try
      {
        Log.i("Form.fullScreenVideoAction", "Actions:" + paramInt + " Source:" + paramVideoPlayer + ": Current Source:" + this.mFullScreenPlayer + " Data:" + paramObject);
        localBundle = new Bundle();
        localBundle.putBoolean("ActionSuccess", true);
        if (paramVideoPlayer != this.mFullScreenPlayer) {
          break label388;
        }
        switch (paramInt)
        {
        case 195: 
          localBundle.putBoolean("ActionSuccess", false);
          paramVideoPlayer = localBundle;
          return paramVideoPlayer;
        }
      }
      finally {}
      paramVideoPlayer = doFullScreenVideoAction(paramVideoPlayer, (Bundle)paramObject);
      continue;
      if (showing())
      {
        this.mFullScreenVideoView.pause();
        paramVideoPlayer = localBundle;
      }
      else
      {
        localBundle.putBoolean("ActionSuccess", false);
        paramVideoPlayer = localBundle;
        continue;
        if (showing())
        {
          this.mFullScreenVideoView.start();
          paramVideoPlayer = localBundle;
        }
        else
        {
          localBundle.putBoolean("ActionSuccess", false);
          paramVideoPlayer = localBundle;
          continue;
          if (showing())
          {
            this.mFullScreenVideoView.seekTo(((Integer)paramObject).intValue());
            paramVideoPlayer = localBundle;
          }
          else
          {
            localBundle.putBoolean("ActionSuccess", false);
            paramVideoPlayer = localBundle;
            continue;
            if (showing())
            {
              this.mFullScreenVideoView.stopPlayback();
              paramVideoPlayer = localBundle;
            }
            else
            {
              localBundle.putBoolean("ActionSuccess", false);
              paramVideoPlayer = localBundle;
              continue;
              if (showing())
              {
                localBundle.putBoolean("ActionSuccess", setSource((String)paramObject, true));
                paramVideoPlayer = localBundle;
              }
              else
              {
                localBundle.putBoolean("ActionSuccess", false);
                paramVideoPlayer = localBundle;
                continue;
                if (showing())
                {
                  localBundle.putInt("ActionData", this.mFullScreenVideoView.getDuration());
                  paramVideoPlayer = localBundle;
                }
                else
                {
                  localBundle.putBoolean("ActionSuccess", false);
                  paramVideoPlayer = localBundle;
                  continue;
                  label388:
                  if (paramInt == 195)
                  {
                    if ((showing()) && (this.mFullScreenPlayer != null))
                    {
                      localBundle = new Bundle();
                      localBundle.putInt("PositionKey", this.mFullScreenVideoView.getCurrentPosition());
                      localBundle.putBoolean("PlayingKey", this.mFullScreenVideoView.isPlaying());
                      localBundle.putString("SourceKey", this.mFullScreenVideoBundle.getString("SourceKey"));
                      this.mFullScreenPlayer.fullScreenKilled(localBundle);
                    }
                    paramVideoPlayer = doFullScreenVideoAction(paramVideoPlayer, (Bundle)paramObject);
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public void prepareFullScreenVideoDialog(Dialog paramDialog)
  {
    this.mFullScreenVideoView.setOnPreparedListener(this);
    this.mFullScreenVideoView.setOnCompletionListener(this);
  }
  
  public boolean setSource(String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      this.mFullScreenVideoBundle.putInt("PositionKey", 0);
      MediaUtil.loadVideoView(this.mFullScreenVideoView, this.mForm, paramString);
      this.mFullScreenVideoBundle.putString("SourceKey", paramString);
      return true;
    }
    catch (IOException localIOException)
    {
      this.mForm.dispatchErrorOccurredEvent(this.mFullScreenPlayer, "Source", 701, new Object[] { paramString });
    }
    return false;
  }
  
  public boolean showing()
  {
    return (dialogInitialized()) && (this.mFullScreenVideoDialog.isShowing());
  }
  
  public void startDialog()
  {
    try
    {
      MediaUtil.loadVideoView(this.mFullScreenVideoView, this.mForm, this.mFullScreenVideoBundle.getString("SourceKey"));
      return;
    }
    catch (IOException localIOException)
    {
      this.mForm.dispatchErrorOccurredEvent(this.mFullScreenPlayer, "Source", 701, new Object[] { this.mFullScreenVideoBundle.getString("SourceKey") });
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\FullScreenVideoUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */