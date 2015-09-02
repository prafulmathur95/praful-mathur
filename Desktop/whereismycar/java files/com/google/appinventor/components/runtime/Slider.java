package com.google.appinventor.components.runtime;

import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category=ComponentCategory.USERINTERFACE, description="A Slider is a progress bar that adds a draggable thumb. You can touch the thumb and drag left or right to set the slider thumb position. As the Slider thumb is dragged, it will trigger the PositionChanged event, reporting the position of the Slider thumb. The reported position of the Slider thumb can be used to dynamically update another component attribute, such as the font size of a TextBox or the radius of a Ball.", version=2)
@SimpleObject
public class Slider
  extends AndroidViewComponent
  implements SeekBar.OnSeekBarChangeListener
{
  private static final boolean DEBUG = false;
  private static final String LOG_TAG = "Slider";
  private static final int initialLeftColor = -14336;
  private static final String initialLeftColorString = "&HFFFFC800";
  private static final int initialRightColor = -7829368;
  private static final String initialRightColorString = "&HFF888888";
  private ClipDrawable beforeThumb;
  private LayerDrawable fullBar;
  private int leftColor;
  private float maxValue;
  private float minValue;
  private int rightColor;
  private final SeekBar seekbar;
  private boolean thumbEnabled;
  private float thumbPosition;
  
  public Slider(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer);
    this.seekbar = new SeekBar(paramComponentContainer.$context());
    this.fullBar = ((LayerDrawable)this.seekbar.getProgressDrawable());
    this.beforeThumb = ((ClipDrawable)this.fullBar.findDrawableByLayerId(16908301));
    this.leftColor = 51200;
    this.rightColor = -7829368;
    setSliderColors();
    paramComponentContainer.$add(this);
    this.minValue = 10.0F;
    this.maxValue = 50.0F;
    this.thumbPosition = 30.0F;
    this.thumbEnabled = true;
    this.seekbar.setOnSeekBarChangeListener(this);
    this.seekbar.setMax(100);
    setSeekbarPosition();
  }
  
  private void setSeekbarPosition()
  {
    float f = (this.thumbPosition - this.minValue) / (this.maxValue - this.minValue);
    this.seekbar.setProgress((int)(f * 100.0F));
  }
  
  private void setSliderColors()
  {
    this.fullBar.setColorFilter(this.rightColor, PorterDuff.Mode.SRC);
    this.beforeThumb.setColorFilter(this.leftColor, PorterDuff.Mode.SRC);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="The color of slider to the left of the thumb.")
  public int ColorLeft()
  {
    return this.leftColor;
  }
  
  @DesignerProperty(defaultValue="&HFFFFC800", editorType="color")
  @SimpleProperty
  public void ColorLeft(int paramInt)
  {
    this.leftColor = paramInt;
    setSliderColors();
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="The color of slider to the left of the thumb.")
  public int ColorRight()
  {
    return this.rightColor;
  }
  
  @DesignerProperty(defaultValue="&HFF888888", editorType="color")
  @SimpleProperty
  public void ColorRight(int paramInt)
  {
    this.rightColor = paramInt;
    setSliderColors();
  }
  
  public int Height()
  {
    return getView().getHeight();
  }
  
  public void Height(int paramInt)
  {
    this.container.setChildHeight(this, paramInt);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Returns the slider max value.", userVisible=true)
  public float MaxValue()
  {
    return this.maxValue;
  }
  
  @DesignerProperty(defaultValue="50.0", editorType="float")
  @SimpleProperty(description="Sets the maximum value of slider.  Changing the maximum value also resets Thumbposition to be halfway between the minimum and the (new) maximum. If the new maximum is less than the current minimum, then minimum and maximum will both be set to this value.  Setting MaxValue resets the thumb position to halfway between MinValue and MaxValue and signals the PositionChanged event.", userVisible=true)
  public void MaxValue(float paramFloat)
  {
    this.maxValue = paramFloat;
    this.minValue = Math.min(paramFloat, this.minValue);
    ThumbPosition((this.minValue + this.maxValue) / 2.0F);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Returns the value of slider min value.", userVisible=true)
  public float MinValue()
  {
    return this.minValue;
  }
  
  @DesignerProperty(defaultValue="10.0", editorType="float")
  @SimpleProperty(description="Sets the minimum value of slider.  Changing the minimum value also resets Thumbposition to be halfway between the (new) minimum and the maximum. If the new minimum is greater than the current maximum, then minimum and maximum will both be set to this value.  Setting MinValue resets the thumb position to halfway between MinValue and MaxValue and signals the PositionChanged event.", userVisible=true)
  public void MinValue(float paramFloat)
  {
    this.minValue = paramFloat;
    this.maxValue = Math.max(paramFloat, this.maxValue);
    ThumbPosition((this.minValue + this.maxValue) / 2.0F);
  }
  
  @SimpleEvent
  public void PositionChanged(float paramFloat)
  {
    EventDispatcher.dispatchEvent(this, "PositionChanged", new Object[] { Float.valueOf(paramFloat) });
  }
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty(description="Sets whether or not to display the slider thumb.", userVisible=true)
  public void ThumbEnabled(boolean paramBoolean)
  {
    this.thumbEnabled = paramBoolean;
    if (this.thumbEnabled) {}
    for (int i = 255;; i = 0)
    {
      this.seekbar.getThumb().mutate().setAlpha(i);
      this.seekbar.setEnabled(this.thumbEnabled);
      return;
    }
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Returns whether or not the slider thumb is being be shown", userVisible=true)
  public boolean ThumbEnabled()
  {
    return this.thumbEnabled;
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Returns the position of slider thumb", userVisible=true)
  public float ThumbPosition()
  {
    return this.thumbPosition;
  }
  
  @DesignerProperty(defaultValue="30.0", editorType="float")
  @SimpleProperty(description="Sets the position of the slider thumb. If this value is greater than MaxValue, then it will be set to same value as MaxValue. If this value is less than MinValue, then it will be set to same value as MinValue.", userVisible=true)
  public void ThumbPosition(float paramFloat)
  {
    this.thumbPosition = Math.max(Math.min(paramFloat, this.maxValue), this.minValue);
    setSeekbarPosition();
    PositionChanged(this.thumbPosition);
  }
  
  public View getView()
  {
    return this.seekbar;
  }
  
  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    this.thumbPosition = ((this.maxValue - this.minValue) * paramInt / 100.0F + this.minValue);
    PositionChanged(this.thumbPosition);
  }
  
  public void onStartTrackingTouch(SeekBar paramSeekBar) {}
  
  public void onStopTrackingTouch(SeekBar paramSeekBar) {}
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\Slider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */