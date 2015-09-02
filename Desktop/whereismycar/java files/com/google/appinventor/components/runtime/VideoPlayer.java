package com.google.appinventor.components.runtime;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.MediaController;
import android.widget.VideoView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.IOException;

@DesignerComponent(category=ComponentCategory.MEDIA, description="A multimedia component capable of playing videos. When the application is run, the VideoPlayer will be displayed as a rectangle on-screen.  If the user touches the rectangle, controls will appear to play/pause, skip ahead, and skip backward within the video.  The application can also control behavior by calling the <code>Start</code>, <code>Pause</code>, and <code>SeekTo</code> methods.  <p>Video files should be in 3GPP (.3gp) or MPEG-4 (.mp4) formats.  For more details about legal formats, see <a href=\"http://developer.android.com/guide/appendix/media-formats.html\" target=\"_blank\">Android Supported Media Formats</a>.</p><p>App Inventor for Android only permits video files under 1 MB and limits the total size of an application to 5 MB, not all of which is available for media (video, audio, and sound) files.  If your media files are too large, you may get errors when packaging or installing your application, in which case you should reduce the number of media files or their sizes.  Most video editing software, such as Windows Movie Maker and Apple iMovie, can help you decrease the size of videos by shortening them or re-encoding the video into a more compact format.</p><p>You can also set the media source to a URL that points to a streaming video, but the URL must point to the video file itself, not to a program that plays the video.", version=5)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.INTERNET")
public final class VideoPlayer
  extends AndroidViewComponent
  implements OnDestroyListener, Deleteable, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener
{
  private boolean delayedStart = false;
  private boolean inFullScreen = false;
  private MediaPlayer mPlayer;
  private boolean mediaReady = false;
  private String sourcePath;
  private final ResizableVideoView videoView;
  
  public VideoPlayer(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer);
    paramComponentContainer.$form().registerForOnDestroy(this);
    this.videoView = new ResizableVideoView(paramComponentContainer.$context());
    this.videoView.setMediaController(new MediaController(paramComponentContainer.$context()));
    this.videoView.setOnCompletionListener(this);
    this.videoView.setOnErrorListener(this);
    this.videoView.setOnPreparedListener(this);
    paramComponentContainer.$add(this);
    paramComponentContainer.setChildWidth(this, 176);
    paramComponentContainer.setChildHeight(this, 144);
    paramComponentContainer.$form().setVolumeControlStream(3);
    this.sourcePath = "";
  }
  
  private void prepareToDie()
  {
    if (this.videoView.isPlaying()) {
      this.videoView.stopPlayback();
    }
    this.videoView.setVideoURI(null);
    this.videoView.clearAnimation();
    this.delayedStart = false;
    this.mediaReady = false;
    if (this.inFullScreen)
    {
      Bundle localBundle = new Bundle();
      localBundle.putBoolean("FullScreenKey", false);
      this.container.$form().fullScreenVideoAction(195, this, localBundle);
    }
  }
  
  @SimpleEvent
  public void Completed()
  {
    EventDispatcher.dispatchEvent(this, "Completed", new Object[0]);
  }
  
  @SimpleProperty(userVisible=true)
  public void FullScreen(boolean paramBoolean)
  {
    if ((paramBoolean) && (SdkLevel.getLevel() <= 4)) {
      this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen(true)", 1303, new Object[0]);
    }
    while (paramBoolean == this.inFullScreen) {
      return;
    }
    if (paramBoolean)
    {
      localBundle = new Bundle();
      localBundle.putInt("PositionKey", this.videoView.getCurrentPosition());
      localBundle.putBoolean("PlayingKey", this.videoView.isPlaying());
      this.videoView.pause();
      localBundle.putBoolean("FullScreenKey", true);
      localBundle.putString("SourceKey", this.sourcePath);
      if (this.container.$form().fullScreenVideoAction(195, this, localBundle).getBoolean("ActionSuccess"))
      {
        this.inFullScreen = true;
        return;
      }
      this.inFullScreen = false;
      this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen", 1301, new Object[] { "" });
      return;
    }
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("FullScreenKey", false);
    localBundle = this.container.$form().fullScreenVideoAction(195, this, localBundle);
    if (localBundle.getBoolean("ActionSuccess"))
    {
      fullScreenKilled(localBundle);
      return;
    }
    this.inFullScreen = true;
    this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen", 1302, new Object[] { "" });
  }
  
  @SimpleProperty
  public boolean FullScreen()
  {
    return this.inFullScreen;
  }
  
  @SimpleFunction(description="Returns duration of the video in milliseconds.")
  public int GetDuration()
  {
    Log.i("VideoPlayer", "Calling GetDuration");
    if (this.inFullScreen)
    {
      Bundle localBundle = this.container.$form().fullScreenVideoAction(196, this, null);
      if (localBundle.getBoolean("ActionSuccess")) {
        return localBundle.getInt("ActionData");
      }
      return 0;
    }
    return this.videoView.getDuration();
  }
  
  @SimpleProperty
  public int Height()
  {
    return super.Height();
  }
  
  @SimpleProperty(userVisible=true)
  public void Height(int paramInt)
  {
    super.Height(paramInt);
    this.videoView.changeVideoSize(this.videoView.forcedWidth, paramInt);
  }
  
  @SimpleFunction(description="Pauses playback of the video.  Playback can be resumed at the same location by calling the <code>Start</code> method.")
  public void Pause()
  {
    Log.i("VideoPlayer", "Calling Pause");
    if (this.inFullScreen)
    {
      this.container.$form().fullScreenVideoAction(192, this, null);
      this.delayedStart = false;
      return;
    }
    this.delayedStart = false;
    this.videoView.pause();
  }
  
  @SimpleFunction(description="Seeks to the requested time (specified in milliseconds) in the video. Note that if the video is paused, the frame shown will not be updated by the seek. ")
  public void SeekTo(int paramInt)
  {
    Log.i("VideoPlayer", "Calling SeekTo");
    int i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    if (this.inFullScreen)
    {
      this.container.$form().fullScreenVideoAction(190, this, Integer.valueOf(i));
      return;
    }
    this.videoView.seekTo(i);
  }
  
  @DesignerProperty(defaultValue="", editorType="asset")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The \"path\" to the video.  Usually, this will be the name of the video file, which should be added in the Designer.")
  public void Source(String paramString)
  {
    if (this.inFullScreen) {
      this.container.$form().fullScreenVideoAction(194, this, paramString);
    }
    do
    {
      return;
      String str = paramString;
      if (paramString == null) {
        str = "";
      }
      this.sourcePath = str;
      this.videoView.invalidateMediaPlayer(true);
      if (this.videoView.isPlaying()) {
        this.videoView.stopPlayback();
      }
      this.videoView.setVideoURI(null);
      this.videoView.clearAnimation();
    } while (this.sourcePath.length() <= 0);
    Log.i("VideoPlayer", "Source path is " + this.sourcePath);
    try
    {
      this.mediaReady = false;
      MediaUtil.loadVideoView(this.videoView, this.container.$form(), this.sourcePath);
      Log.i("VideoPlayer", "loading video succeeded");
      return;
    }
    catch (IOException paramString)
    {
      this.container.$form().dispatchErrorOccurredEvent(this, "Source", 701, new Object[] { this.sourcePath });
    }
  }
  
  @SimpleFunction(description="Starts playback of the video.")
  public void Start()
  {
    Log.i("VideoPlayer", "Calling Start");
    if (this.inFullScreen)
    {
      this.container.$form().fullScreenVideoAction(191, this, null);
      return;
    }
    if (this.mediaReady)
    {
      this.videoView.start();
      return;
    }
    this.delayedStart = true;
  }
  
  @SimpleEvent(description="The VideoPlayerError event is no longer used. Please use the Screen.ErrorOccurred event instead.", userVisible=false)
  public void VideoPlayerError(String paramString) {}
  
  @DesignerProperty(defaultValue="50", editorType="non_negative_float")
  @SimpleProperty(description="Sets the volume to a number between 0 and 100. Values less than 0 will be treated as 0, and values greater than 100 will be treated as 100.")
  public void Volume(int paramInt)
  {
    paramInt = Math.min(Math.max(paramInt, 0), 100);
    if (this.mPlayer != null) {
      this.mPlayer.setVolume(paramInt / 100.0F, paramInt / 100.0F);
    }
  }
  
  @SimpleProperty
  public int Width()
  {
    return super.Width();
  }
  
  @SimpleProperty(userVisible=true)
  public void Width(int paramInt)
  {
    super.Width(paramInt);
    this.videoView.changeVideoSize(paramInt, this.videoView.forcedHeight);
  }
  
  public void delayedStart()
  {
    this.delayedStart = true;
    Start();
  }
  
  public void fullScreenKilled(Bundle paramBundle)
  {
    this.inFullScreen = false;
    String str = paramBundle.getString("SourceKey");
    if (!str.equals(this.sourcePath)) {
      Source(str);
    }
    this.videoView.setVisibility(0);
    this.videoView.requestLayout();
    SeekTo(paramBundle.getInt("PositionKey"));
    if (paramBundle.getBoolean("PlayingKey")) {
      Start();
    }
  }
  
  public int getPassedHeight()
  {
    return this.videoView.forcedHeight;
  }
  
  public int getPassedWidth()
  {
    return this.videoView.forcedWidth;
  }
  
  public View getView()
  {
    return this.videoView;
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    Completed();
  }
  
  public void onDelete()
  {
    prepareToDie();
  }
  
  public void onDestroy()
  {
    prepareToDie();
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    this.videoView.invalidateMediaPlayer(true);
    this.delayedStart = false;
    this.mediaReady = false;
    Log.e("VideoPlayer", "onError: what is " + paramInt1 + " 0x" + Integer.toHexString(paramInt1) + ", extra is " + paramInt2 + " 0x" + Integer.toHexString(paramInt2));
    this.container.$form().dispatchErrorOccurredEvent(this, "Source", 701, new Object[] { this.sourcePath });
    return true;
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    this.mediaReady = true;
    this.delayedStart = false;
    this.mPlayer = paramMediaPlayer;
    this.videoView.setMediaPlayer(this.mPlayer, true);
    if (this.delayedStart) {
      Start();
    }
  }
  
  class ResizableVideoView
    extends VideoView
  {
    public int forcedHeight = -1;
    public int forcedWidth = -1;
    private Boolean mFoundMediaPlayer = Boolean.valueOf(false);
    private MediaPlayer mVideoPlayer;
    
    public ResizableVideoView(Context paramContext)
    {
      super();
    }
    
    public void changeVideoSize(int paramInt1, int paramInt2)
    {
      this.forcedWidth = paramInt1;
      this.forcedHeight = paramInt2;
      forceLayout();
      invalidate();
    }
    
    public void invalidateMediaPlayer(boolean paramBoolean)
    {
      this.mFoundMediaPlayer = Boolean.valueOf(false);
      this.mVideoPlayer = null;
      if (paramBoolean)
      {
        forceLayout();
        invalidate();
      }
    }
    
    public void onMeasure(int paramInt1, int paramInt2)
    {
      Log.i("VideoPlayer..onMeasure", "AI setting dimensions as:" + this.forcedWidth + ":" + this.forcedHeight);
      Log.i("VideoPlayer..onMeasure", "Dimenions from super>>" + View.MeasureSpec.getSize(paramInt1) + ":" + View.MeasureSpec.getSize(paramInt2));
      int j = 176;
      int i = 144;
      switch (this.forcedWidth)
      {
      default: 
        paramInt1 = this.forcedWidth;
        switch (this.forcedHeight)
        {
        default: 
          paramInt2 = this.forcedHeight;
        }
        break;
      }
      for (;;)
      {
        Log.i("VideoPlayer.onMeasure", "Setting dimensions to:" + paramInt1 + "x" + paramInt2);
        getHolder().setFixedSize(paramInt1, paramInt2);
        setMeasuredDimension(paramInt1, paramInt2);
        return;
        switch (View.MeasureSpec.getMode(paramInt1))
        {
        default: 
          paramInt1 = j;
          break;
        case 1073741824: 
        case -2147483648: 
          paramInt1 = View.MeasureSpec.getSize(paramInt1);
          break;
        case 0: 
          try
          {
            paramInt1 = ((View)getParent()).getMeasuredWidth();
          }
          catch (ClassCastException localClassCastException)
          {
            paramInt1 = 176;
          }
          catch (NullPointerException localNullPointerException1)
          {
            paramInt1 = 176;
          }
          break;
          paramInt1 = j;
          if (!this.mFoundMediaPlayer.booleanValue()) {
            break;
          }
          try
          {
            paramInt1 = this.mVideoPlayer.getVideoWidth();
            Log.i("VideoPlayer.onMeasure", "Got width from MediaPlayer>" + paramInt1);
          }
          catch (NullPointerException localNullPointerException2)
          {
            Log.e("VideoPlayer..onMeasure", "Failed to get MediaPlayer for width:\n" + localNullPointerException2.getMessage());
            paramInt1 = 176;
          }
          break;
          switch (View.MeasureSpec.getMode(paramInt2))
          {
          default: 
            paramInt2 = i;
            break;
          case 1073741824: 
          case -2147483648: 
            paramInt2 = View.MeasureSpec.getSize(paramInt2);
            continue;
            paramInt2 = i;
            if (this.mFoundMediaPlayer.booleanValue()) {
              try
              {
                paramInt2 = this.mVideoPlayer.getVideoHeight();
                Log.i("VideoPlayer.onMeasure", "Got height from MediaPlayer>" + paramInt2);
              }
              catch (NullPointerException localNullPointerException3)
              {
                Log.e("VideoPlayer..onMeasure", "Failed to get MediaPlayer for height:\n" + localNullPointerException3.getMessage());
                paramInt2 = 144;
              }
            }
            break;
          }
          break;
        }
      }
    }
    
    public void setMediaPlayer(MediaPlayer paramMediaPlayer, boolean paramBoolean)
    {
      this.mVideoPlayer = paramMediaPlayer;
      this.mFoundMediaPlayer = Boolean.valueOf(true);
      if (paramBoolean)
      {
        forceLayout();
        invalidate();
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\VideoPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */