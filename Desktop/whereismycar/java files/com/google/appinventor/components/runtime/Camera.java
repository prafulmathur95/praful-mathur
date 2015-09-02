package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import java.io.File;
import java.util.Date;

@DesignerComponent(category=ComponentCategory.MEDIA, description="A component to take a picture using the device's camera. After the picture is taken, the name of the file on the phone containing the picture is available as an argument to the AfterPicture event. The file name can be used, for example, to set the Picture property of an Image component.", iconName="images/camera.png", nonVisible=true, version=2)
@SimpleObject
public class Camera
  extends AndroidNonvisibleComponent
  implements ActivityResultListener, Component
{
  private static final String CAMERA_INTENT = "android.media.action.IMAGE_CAPTURE";
  private static final String CAMERA_OUTPUT = "output";
  private final ComponentContainer container;
  private Uri imageFile;
  private int requestCode;
  private boolean useFront;
  
  public Camera(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.container = paramComponentContainer;
    UseFront(false);
  }
  
  private void deleteFile(Uri paramUri)
  {
    File localFile = new File(paramUri.getPath());
    try
    {
      if (localFile.delete())
      {
        Log.i("CameraComponent", "Deleted file " + paramUri.toString());
        return;
      }
      Log.i("CameraComponent", "Could not delete file " + paramUri.toString());
      return;
    }
    catch (SecurityException localSecurityException)
    {
      Log.i("CameraComponent", "Got security exception trying to delete file " + paramUri.toString());
    }
  }
  
  @SimpleEvent
  public void AfterPicture(String paramString)
  {
    EventDispatcher.dispatchEvent(this, "AfterPicture", new Object[] { paramString });
  }
  
  @SimpleFunction
  public void TakePicture()
  {
    Object localObject1 = new Date();
    Object localObject2 = Environment.getExternalStorageState();
    if ("mounted".equals(localObject2))
    {
      Log.i("CameraComponent", "External storage is available and writable");
      this.imageFile = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "/Pictures/app_inventor_" + ((Date)localObject1).getTime() + ".jpg"));
      localObject1 = new ContentValues();
      ((ContentValues)localObject1).put("_data", this.imageFile.getPath());
      ((ContentValues)localObject1).put("mime_type", "image/jpeg");
      ((ContentValues)localObject1).put("title", this.imageFile.getLastPathSegment());
      if (this.requestCode == 0) {
        this.requestCode = this.form.registerForActivityResult(this);
      }
      localObject1 = this.container.$context().getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, (ContentValues)localObject1);
      localObject2 = new Intent("android.media.action.IMAGE_CAPTURE");
      ((Intent)localObject2).putExtra("output", (Parcelable)localObject1);
      if (this.useFront) {
        ((Intent)localObject2).putExtra("android.intent.extras.CAMERA_FACING", 1);
      }
      this.container.$context().startActivityForResult((Intent)localObject2, this.requestCode);
      return;
    }
    if ("mounted_ro".equals(localObject2))
    {
      this.form.dispatchErrorOccurredEvent(this, "TakePicture", 704, new Object[0]);
      return;
    }
    this.form.dispatchErrorOccurredEvent(this, "TakePicture", 705, new Object[0]);
  }
  
  @DesignerProperty(defaultValue="False", editorType="boolean")
  @SimpleProperty(description="Specifies whether the front-facing camera should be used (when available). If the device does not have a front-facing camera, this option will be ignored and the camera will open normally.")
  public void UseFront(boolean paramBoolean)
  {
    this.useFront = paramBoolean;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean UseFront()
  {
    return this.useFront;
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Log.i("CameraComponent", "Returning result. Request code = " + paramInt1 + ", result code = " + paramInt2);
    if ((paramInt1 == this.requestCode) && (paramInt2 == -1))
    {
      if (new File(this.imageFile.getPath()).length() != 0L)
      {
        AfterPicture(this.imageFile.toString());
        return;
      }
      deleteFile(this.imageFile);
      if ((paramIntent != null) && (paramIntent.getData() != null))
      {
        paramIntent = paramIntent.getData();
        Log.i("CameraComponent", "Calling Camera.AfterPicture with image path " + paramIntent.toString());
        AfterPicture(paramIntent.toString());
        return;
      }
      Log.i("CameraComponent", "Couldn't find an image file from the Camera result");
      this.form.dispatchErrorOccurredEvent(this, "TakePicture", 201, new Object[0]);
      return;
    }
    deleteFile(this.imageFile);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\Camera.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */