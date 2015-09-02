package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;

@DesignerComponent(category=ComponentCategory.MEDIA, description="A special-purpose button. When the user taps an image picker, the device's image gallery appears, and the user can choose an image. After an image is picked, it is saved, and the <code>Selected</code> property will be the name of the file where the image is stored. In order to not fill up storage, a maximum of 10 images will be stored.  Picking more images will delete previous images, in order from oldest to newest.", version=5)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.WRITE_EXTERNAL_STORAGE")
public class ImagePicker
  extends Picker
  implements ActivityResultListener
{
  private static final String FILE_PREFIX = "picked_image";
  private static final String LOG_TAG = "ImagePicker";
  private static final String imagePickerDirectoryName = "/Pictures/_app_inventor_image_picker";
  private static int maxSavedFiles = 10;
  private String selectionSavedImage = "";
  private String selectionURI;
  
  public ImagePicker(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer);
  }
  
  private void copyToExternalStorageAndDeleteSource(File paramFile, String paramString)
  {
    localFile1 = null;
    File localFile2 = new File(Environment.getExternalStorageDirectory() + "/Pictures/_app_inventor_image_picker");
    localObject = localFile1;
    for (;;)
    {
      try
      {
        localFile2.mkdirs();
        localObject = localFile1;
        localFile1 = File.createTempFile("picked_image", paramString, localFile2);
        localObject = localFile1;
        this.selectionSavedImage = localFile1.getPath();
        localObject = localFile1;
        Log.i("ImagePicker", "saved file path is: " + this.selectionSavedImage);
        localObject = localFile1;
        paramString = new FileInputStream(paramFile);
      }
      catch (IOException paramString)
      {
        continue;
      }
      try
      {
        localObject = new FileOutputStream(localFile1);
        try
        {
          byte[] arrayOfByte = new byte['Ѐ'];
          int i = paramString.read(arrayOfByte);
          if (i <= 0) {
            continue;
          }
          ((OutputStream)localObject).write(arrayOfByte, 0, i);
          continue;
          paramString = "destination is " + this.selectionSavedImage + ": " + "error is " + paramString.getMessage();
        }
        catch (IOException paramString)
        {
          localObject = localFile1;
        }
      }
      catch (IOException paramString)
      {
        localObject = localFile1;
      }
    }
    Log.i("ImagePicker", "copyFile failed. " + paramString);
    this.container.$form().dispatchErrorOccurredEvent(this, "SaveImage", 1601, new Object[] { paramString });
    this.selectionSavedImage = "";
    ((File)localObject).delete();
    for (;;)
    {
      paramFile.delete();
      trimDirectory(maxSavedFiles, localFile2);
      return;
      paramString.close();
      ((OutputStream)localObject).close();
      Log.i("ImagePicker", "Image was copied to " + this.selectionSavedImage);
    }
  }
  
  private void saveSelectedImageToExternalStorage(String paramString)
  {
    this.selectionSavedImage = "";
    try
    {
      File localFile = MediaUtil.copyMediaToTempFile(this.container.$form(), this.selectionURI);
      Log.i("ImagePicker", "temp file path is: " + localFile.getPath());
      copyToExternalStorageAndDeleteSource(localFile, paramString);
      return;
    }
    catch (IOException paramString)
    {
      Log.i("ImagePicker", "copyMediaToTempFile failed: " + paramString.getMessage());
      this.container.$form().dispatchErrorOccurredEvent(this, "ImagePicker", 1602, new Object[] { paramString.getMessage() });
    }
  }
  
  private void trimDirectory(int paramInt, File paramFile)
  {
    paramFile = paramFile.listFiles();
    Arrays.sort(paramFile, new Comparator()
    {
      public int compare(File paramAnonymousFile1, File paramAnonymousFile2)
      {
        return Long.valueOf(paramAnonymousFile1.lastModified()).compareTo(Long.valueOf(paramAnonymousFile2.lastModified()));
      }
    });
    int j = paramFile.length;
    int i = 0;
    while (i < j - paramInt)
    {
      paramFile[i].delete();
      i += 1;
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Path to the file containing the image that was selected.")
  public String Selection()
  {
    return this.selectionSavedImage;
  }
  
  protected Intent getIntent()
  {
    return new Intent("android.intent.action.PICK", MediaStore.Images.Media.INTERNAL_CONTENT_URI);
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == this.requestCode) && (paramInt2 == -1))
    {
      paramIntent = paramIntent.getData();
      this.selectionURI = paramIntent.toString();
      Log.i("ImagePicker", "selectionURI = " + this.selectionURI);
      ContentResolver localContentResolver = this.container.$context().getContentResolver();
      MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
      paramIntent = "." + localMimeTypeMap.getExtensionFromMimeType(localContentResolver.getType(paramIntent));
      Log.i("ImagePicker", "extension = " + paramIntent);
      saveSelectedImageToExternalStorage(paramIntent);
      AfterPicking();
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\ImagePicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */