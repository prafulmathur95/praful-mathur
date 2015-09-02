package com.google.appinventor.components.runtime;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.IOException;

@SimpleObject
@UsesPermissions(permissionNames="android.permission.INTERNET")
public abstract class ButtonBase
  extends AndroidViewComponent
  implements View.OnClickListener, View.OnFocusChangeListener, View.OnLongClickListener, View.OnTouchListener
{
  private static final String LOG_TAG = "ButtonBase";
  private static final float[] ROUNDED_CORNERS_ARRAY = { 10.0F, 10.0F, 10.0F, 10.0F, 10.0F, 10.0F, 10.0F, 10.0F };
  private static final float ROUNDED_CORNERS_RADIUS = 10.0F;
  private static final int SHAPED_DEFAULT_BACKGROUND_COLOR = -3355444;
  private int backgroundColor;
  private Drawable backgroundImageDrawable;
  private boolean bold;
  private Drawable defaultButtonDrawable;
  private ColorStateList defaultColorStateList;
  private int fontTypeface;
  private String imagePath = "";
  private boolean italic;
  private int shape;
  private boolean showFeedback = true;
  private int textAlignment;
  private int textColor;
  private final Button view;
  
  public ButtonBase(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer);
    this.view = new Button(paramComponentContainer.$context());
    this.defaultButtonDrawable = this.view.getBackground();
    this.defaultColorStateList = this.view.getTextColors();
    paramComponentContainer.$add(this);
    this.view.setOnClickListener(this);
    this.view.setOnFocusChangeListener(this);
    this.view.setOnLongClickListener(this);
    this.view.setOnTouchListener(this);
    TextAlignment(1);
    BackgroundColor(0);
    Image("");
    Enabled(true);
    this.fontTypeface = 0;
    TextViewUtil.setFontTypeface(this.view, this.fontTypeface, this.bold, this.italic);
    FontSize(14.0F);
    Text("");
    TextColor(0);
    Shape(0);
  }
  
  private void setShape()
  {
    ShapeDrawable localShapeDrawable = new ShapeDrawable();
    Paint localPaint = localShapeDrawable.getPaint();
    if (this.backgroundColor == 0) {}
    for (int i = -3355444;; i = this.backgroundColor)
    {
      localPaint.setColor(i);
      switch (this.shape)
      {
      default: 
        throw new IllegalArgumentException();
      }
    }
    localShapeDrawable.setShape(new RoundRectShape(ROUNDED_CORNERS_ARRAY, null, null));
    for (;;)
    {
      this.view.setBackgroundDrawable(localShapeDrawable);
      this.view.invalidate();
      return;
      localShapeDrawable.setShape(new RectShape());
      continue;
      localShapeDrawable.setShape(new OvalShape());
    }
  }
  
  private void updateAppearance()
  {
    if (this.backgroundImageDrawable == null)
    {
      if (this.shape == 0)
      {
        if (this.backgroundColor == 0)
        {
          ViewUtil.setBackgroundDrawable(this.view, this.defaultButtonDrawable);
          return;
        }
        ViewUtil.setBackgroundDrawable(this.view, null);
        TextViewUtil.setBackgroundColor(this.view, this.backgroundColor);
        return;
      }
      setShape();
      return;
    }
    ViewUtil.setBackgroundImage(this.view, this.backgroundImageDrawable);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Returns the button's background color")
  public int BackgroundColor()
  {
    return this.backgroundColor;
  }
  
  @DesignerProperty(defaultValue="&H00000000", editorType="color")
  @SimpleProperty(description="Specifies the button's background color. The background color will not be visible if an Image is being displayed.")
  public void BackgroundColor(int paramInt)
  {
    this.backgroundColor = paramInt;
    updateAppearance();
  }
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty
  public void Enabled(boolean paramBoolean)
  {
    TextViewUtil.setEnabled(this.view, paramBoolean);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="If set, user can tap check box to cause action.")
  public boolean Enabled()
  {
    return TextViewUtil.isEnabled(this.view);
  }
  
  @DesignerProperty(defaultValue="False", editorType="boolean")
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public void FontBold(boolean paramBoolean)
  {
    this.bold = paramBoolean;
    TextViewUtil.setFontTypeface(this.view, this.fontTypeface, paramBoolean, this.italic);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="If set, button text is displayed in bold.")
  public boolean FontBold()
  {
    return this.bold;
  }
  
  @DesignerProperty(defaultValue="False", editorType="boolean")
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public void FontItalic(boolean paramBoolean)
  {
    this.italic = paramBoolean;
    TextViewUtil.setFontTypeface(this.view, this.fontTypeface, this.bold, paramBoolean);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="If set, button text is displayed in italics.")
  public boolean FontItalic()
  {
    return this.italic;
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Point size for button text.")
  public float FontSize()
  {
    return TextViewUtil.getFontSize(this.view);
  }
  
  @DesignerProperty(defaultValue="14.0", editorType="non_negative_float")
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public void FontSize(float paramFloat)
  {
    TextViewUtil.setFontSize(this.view, paramFloat);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Font family for button text.", userVisible=false)
  public int FontTypeface()
  {
    return this.fontTypeface;
  }
  
  @DesignerProperty(defaultValue="0", editorType="typeface")
  @SimpleProperty(userVisible=false)
  public void FontTypeface(int paramInt)
  {
    this.fontTypeface = paramInt;
    TextViewUtil.setFontTypeface(this.view, this.fontTypeface, this.bold, this.italic);
  }
  
  @SimpleEvent(description="Indicates the cursor moved over the button so it is now possible to click it.")
  public void GotFocus()
  {
    EventDispatcher.dispatchEvent(this, "GotFocus", new Object[0]);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Image to display on button.")
  public String Image()
  {
    return this.imagePath;
  }
  
  @DesignerProperty(defaultValue="", editorType="asset")
  @SimpleProperty(description="Specifies the path of the button's image.  If there is both an Image and a BackgroundColor, only the Image will be visible.")
  public void Image(String paramString)
  {
    if ((paramString.equals(this.imagePath)) && (this.backgroundImageDrawable != null)) {
      return;
    }
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    this.imagePath = str;
    this.backgroundImageDrawable = null;
    if (this.imagePath.length() > 0) {}
    try
    {
      this.backgroundImageDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), this.imagePath);
      updateAppearance();
      return;
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        Log.e("ButtonBase", "Unable to load " + this.imagePath);
      }
    }
  }
  
  @SimpleEvent(description="Indicates the cursor moved away from the button so it is now no longer possible to click it.")
  public void LostFocus()
  {
    EventDispatcher.dispatchEvent(this, "LostFocus", new Object[0]);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, userVisible=false)
  public int Shape()
  {
    return this.shape;
  }
  
  @DesignerProperty(defaultValue="0", editorType="button_shape")
  @SimpleProperty(description="Specifies the button's shape (default, rounded, rectangular, oval). The shape will not be visible if an Image is being displayed.", userVisible=false)
  public void Shape(int paramInt)
  {
    this.shape = paramInt;
    updateAppearance();
  }
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty(description="Specifies if a visual feedback should be shown  for a button that as an image as background.")
  public void ShowFeedback(boolean paramBoolean)
  {
    this.showFeedback = paramBoolean;
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Returns the button's visual feedback state")
  public boolean ShowFeedback()
  {
    return this.showFeedback;
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Text to display on button.")
  public String Text()
  {
    return TextViewUtil.getText(this.view);
  }
  
  @DesignerProperty(defaultValue="", editorType="string")
  @SimpleProperty
  public void Text(String paramString)
  {
    TextViewUtil.setText(this.view, paramString);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Left, center, or right.", userVisible=false)
  public int TextAlignment()
  {
    return this.textAlignment;
  }
  
  @DesignerProperty(defaultValue="1", editorType="textalignment")
  @SimpleProperty(userVisible=false)
  public void TextAlignment(int paramInt)
  {
    this.textAlignment = paramInt;
    TextViewUtil.setAlignment(this.view, paramInt, true);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Color for button text.")
  public int TextColor()
  {
    return this.textColor;
  }
  
  @DesignerProperty(defaultValue="&H00000000", editorType="color")
  @SimpleProperty
  public void TextColor(int paramInt)
  {
    this.textColor = paramInt;
    if (paramInt != 0)
    {
      TextViewUtil.setTextColor(this.view, paramInt);
      return;
    }
    TextViewUtil.setTextColors(this.view, this.defaultColorStateList);
  }
  
  @SimpleEvent(description="Indicates that the button was pressed down.")
  public void TouchDown()
  {
    EventDispatcher.dispatchEvent(this, "TouchDown", new Object[0]);
  }
  
  @SimpleEvent(description="Indicates that a button has been released.")
  public void TouchUp()
  {
    EventDispatcher.dispatchEvent(this, "TouchUp", new Object[0]);
  }
  
  public abstract void click();
  
  public View getView()
  {
    return this.view;
  }
  
  public boolean longClick()
  {
    return false;
  }
  
  public void onClick(View paramView)
  {
    click();
  }
  
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      GotFocus();
      return;
    }
    LostFocus();
  }
  
  public boolean onLongClick(View paramView)
  {
    return longClick();
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0)
    {
      if (ShowFeedback())
      {
        paramView.getBackground().setAlpha(70);
        paramView.invalidate();
      }
      TouchDown();
    }
    for (;;)
    {
      return false;
      if ((paramMotionEvent.getAction() == 1) || (paramMotionEvent.getAction() == 3))
      {
        if (ShowFeedback())
        {
          paramView.getBackground().setAlpha(255);
          paramView.invalidate();
        }
        TouchUp();
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\ButtonBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */