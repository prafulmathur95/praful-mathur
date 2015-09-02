package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import java.io.File;

@DesignerComponent(category=ComponentCategory.MEDIA, description="A component to record a video using the device's camcorder.After the video is recorded, the name of the file on the phone containing the clip is available as an argument to the AfterRecording event. The file name can be used, for example, to set the source property of a VideoPlayer component.", iconName="images/camcorder.png", nonVisible=true, version=1)
@SimpleObject
public class Camcorder
  extends AndroidNonvisibleComponent
  implements ActivityResultListener, Component
{
  private static final String CAMCORDER_INTENT = "android.media.action.VIDEO_CAPTURE";
  private final ComponentContainer container;
  private int requestCode;
  
  public Camcorder(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.container = paramComponentContainer;
  }
  
  private void deleteFile(Uri paramUri)
  {
    File localFile = new File(paramUri.getPath());
    try
    {
      if (localFile.delete())
      {
        Log.i("CamcorderComponent", "Deleted file " + paramUri.toString());
        return;
      }
      Log.i("CamcorderComponent", "Could not delete file " + paramUri.toString());
      return;
    }
    catch (SecurityException localSecurityException)
    {
      Log.i("CamcorderComponent", "Got security exception trying to delete file " + paramUri.toString());
    }
  }
  
  @SimpleEvent
  public void AfterRecording(String paramString)
  {
    EventDispatcher.dispatchEvent(this, "AfterRecording", new Object[] { paramString });
  }
  
  @SimpleFunction
  public void RecordVideo()
  {
    Object localObject = Environment.getExternalStorageState();
    if ("mounted".equals(localObject))
    {
      Log.i("CamcorderComponent", "External storage is available and writable");
      if (this.requestCode == 0) {
        this.requestCode = this.form.registerForActivityResult(this);
      }
      localObject = new Intent("android.media.action.VIDEO_CAPTURE");
      this.container.$context().startActivityForResult((Intent)localObject, this.requestCode);
      return;
    }
    if ("mounted_ro".equals(localObject))
    {
      this.form.dispatchErrorOccurredEvent(this, "RecordVideo", 704, new Object[0]);
      return;
    }
    this.form.dispatchErrorOccurredEvent(this, "RecordVideo", 705, new Object[0]);
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Log.i("CamcorderComponent", "Returning result. Request code = " + paramInt1 + ", result code = " + paramInt2);
    if ((paramInt1 == this.requestCode) && (paramInt2 == -1))
    {
      if ((paramIntent != null) && (paramIntent.getData() != null))
      {
        paramIntent = paramIntent.getData();
        Log.i("CamcorderComponent", "Calling Camcorder.AfterPicture with clip path " + paramIntent.toString());
        AfterRecording(paramIntent.toString());
        return;
      }
      Log.i("CamcorderComponent", "Couldn't find a clip file from the Camcorder result");
      this.form.dispatchErrorOccurredEvent(this, "TakeVideo", 1201, new Object[0]);
      return;
    }
    Log.i("CamcorderComponent", "No clip filed rerturn; request failed");
    this.form.dispatchErrorOccurredEvent(this, "TakeVideo", 1201, new Object[0]);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\Camcorder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */