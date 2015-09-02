package com.google.appinventor.components.runtime;

import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.media.MediaRecorder.OnInfoListener;
import android.os.Environment;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.FileUtil;
import java.io.File;
import java.io.IOException;

@DesignerComponent(category=ComponentCategory.MEDIA, description="<p>Multimedia component that records audio.</p>", iconName="images/soundRecorder.png", nonVisible=true, version=2)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.RECORD_AUDIO")
public final class SoundRecorder
  extends AndroidNonvisibleComponent
  implements Component, MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener
{
  private static final String TAG = "SoundRecorder";
  private RecordingController controller;
  private String savedRecording = "";
  
  public SoundRecorder(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
  }
  
  @SimpleEvent(description="Provides the location of the newly created sound.")
  public void AfterSoundRecorded(String paramString)
  {
    EventDispatcher.dispatchEvent(this, "AfterSoundRecorded", new Object[] { paramString });
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Specifies the path to the file where the recording is stored. If this proprety is the empty string, then starting a recording will create a file in an appropriate location.")
  public String SavedRecording()
  {
    return this.savedRecording;
  }
  
  @DesignerProperty(defaultValue="", editorType="string")
  @SimpleProperty
  public void SavedRecording(String paramString)
  {
    this.savedRecording = paramString;
  }
  
  @SimpleFunction
  public void Start()
  {
    if (this.controller != null)
    {
      Log.i("SoundRecorder", "Start() called, but already recording to " + this.controller.file);
      return;
    }
    Log.i("SoundRecorder", "Start() called");
    if (!Environment.getExternalStorageState().equals("mounted"))
    {
      this.form.dispatchErrorOccurredEvent(this, "Start", 705, new Object[0]);
      return;
    }
    try
    {
      this.controller = new RecordingController(this.savedRecording);
      return;
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        this.controller.start();
        StartedRecording();
        return;
      }
      catch (Throwable localThrowable2)
      {
        this.controller = null;
        this.form.dispatchErrorOccurredEvent(this, "Start", 802, new Object[] { localThrowable2.getMessage() });
      }
      localThrowable1 = localThrowable1;
      this.form.dispatchErrorOccurredEvent(this, "Start", 802, new Object[] { localThrowable1.getMessage() });
      return;
    }
  }
  
  @SimpleEvent(description="Indicates that the recorder has started, and can be stopped.")
  public void StartedRecording()
  {
    EventDispatcher.dispatchEvent(this, "StartedRecording", new Object[0]);
  }
  
  @SimpleFunction
  public void Stop()
  {
    if (this.controller == null)
    {
      Log.i("SoundRecorder", "Stop() called, but already stopped.");
      return;
    }
    try
    {
      Log.i("SoundRecorder", "Stop() called");
      Log.i("SoundRecorder", "stopping");
      this.controller.stop();
      Log.i("SoundRecorder", "Firing AfterSoundRecorded with " + this.controller.file);
      AfterSoundRecorded(this.controller.file);
      return;
    }
    catch (Throwable localThrowable)
    {
      this.form.dispatchErrorOccurredEvent(this, "Stop", 801, new Object[0]);
      return;
    }
    finally
    {
      this.controller = null;
      StoppedRecording();
    }
  }
  
  @SimpleEvent(description="Indicates that the recorder has stopped, and can be started again.")
  public void StoppedRecording()
  {
    EventDispatcher.dispatchEvent(this, "StoppedRecording", new Object[0]);
  }
  
  public void onError(MediaRecorder paramMediaRecorder, int paramInt1, int paramInt2)
  {
    if ((this.controller == null) || (paramMediaRecorder != this.controller.recorder))
    {
      Log.w("SoundRecorder", "onError called with wrong recorder. Ignoring.");
      return;
    }
    this.form.dispatchErrorOccurredEvent(this, "onError", 801, new Object[0]);
    try
    {
      this.controller.stop();
      return;
    }
    catch (Throwable paramMediaRecorder)
    {
      Log.w("SoundRecorder", paramMediaRecorder.getMessage());
      return;
    }
    finally
    {
      this.controller = null;
      StoppedRecording();
    }
  }
  
  public void onInfo(MediaRecorder paramMediaRecorder, int paramInt1, int paramInt2)
  {
    if ((this.controller == null) || (paramMediaRecorder != this.controller.recorder))
    {
      Log.w("SoundRecorder", "onInfo called with wrong recorder. Ignoring.");
      return;
    }
    Log.i("SoundRecorder", "Recoverable condition while recording. Will attempt to stop normally.");
    this.controller.recorder.stop();
  }
  
  private class RecordingController
  {
    final String file;
    final MediaRecorder recorder;
    
    RecordingController(String paramString)
      throws IOException
    {
      String str = paramString;
      if (paramString.equals("")) {
        str = FileUtil.getRecordingFile("3gp").getAbsolutePath();
      }
      this.file = str;
      this.recorder = new MediaRecorder();
      this.recorder.setAudioSource(1);
      this.recorder.setOutputFormat(1);
      this.recorder.setAudioEncoder(1);
      Log.i("SoundRecorder", "Setting output file to " + this.file);
      this.recorder.setOutputFile(this.file);
      Log.i("SoundRecorder", "preparing");
      this.recorder.prepare();
      this.recorder.setOnErrorListener(SoundRecorder.this);
      this.recorder.setOnInfoListener(SoundRecorder.this);
    }
    
    void start()
      throws IllegalStateException
    {
      Log.i("SoundRecorder", "starting");
      try
      {
        this.recorder.start();
        return;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        Log.e("SoundRecorder", "got IllegalStateException. Are there two recorders running?", localIllegalStateException);
        throw new IllegalStateException("Is there another recording running?");
      }
    }
    
    void stop()
    {
      this.recorder.setOnErrorListener(null);
      this.recorder.setOnInfoListener(null);
      this.recorder.stop();
      this.recorder.reset();
      this.recorder.release();
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\SoundRecorder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */