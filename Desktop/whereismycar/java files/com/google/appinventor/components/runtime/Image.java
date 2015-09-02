package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AnimationUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.IOException;

@DesignerComponent(category=ComponentCategory.USERINTERFACE, description="Component for displaying images.  The picture to display, and other aspects of the Image's appearance, can be specified in the Designer or in the Blocks Editor.", version=1)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.INTERNET")
public final class Image
  extends AndroidViewComponent
{
  private String picturePath = "";
  private final ImageView view;
  
  public Image(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer);
    this.view = new ImageView(paramComponentContainer.$context())
    {
      public boolean verifyDrawable(Drawable paramAnonymousDrawable)
      {
        super.verifyDrawable(paramAnonymousDrawable);
        return true;
      }
    };
    paramComponentContainer.$add(this);
    this.view.setFocusable(true);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="This is a limited form of animation that can attach a small number of motion types to images.  The allowable motions are ScrollRightSlow, ScrollRight, ScrollRightFast, ScrollLeftSlow, ScrollLeft, ScrollLeftFast, and Stop")
  public void Animation(String paramString)
  {
    AnimationUtil.ApplyAnimation(this.view, paramString);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public String Picture()
  {
    return this.picturePath;
  }
  
  @DesignerProperty(defaultValue="", editorType="asset")
  @SimpleProperty
  public void Picture(String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    this.picturePath = str;
    try
    {
      paramString = MediaUtil.getBitmapDrawable(this.container.$form(), this.picturePath);
      ViewUtil.setImage(this.view, paramString);
      return;
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        Log.e("Image", "Unable to load " + this.picturePath);
        paramString = null;
      }
    }
  }
  
  public View getView()
  {
    return this.view;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\Image.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */