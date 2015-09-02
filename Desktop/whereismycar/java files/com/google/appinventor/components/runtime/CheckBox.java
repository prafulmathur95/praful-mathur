package com.google.appinventor.components.runtime;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.TextViewUtil;

@DesignerComponent(category=ComponentCategory.USERINTERFACE, description="Checkbox that raises an event when the user clicks on it. There are many properties affecting its appearance that can be set in the Designer or Blocks Editor.", version=2)
@SimpleObject
public final class CheckBox
  extends AndroidViewComponent
  implements CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener
{
  private int backgroundColor;
  private boolean bold;
  private int fontTypeface;
  private boolean italic;
  private int textColor;
  private final android.widget.CheckBox view;
  
  public CheckBox(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer);
    this.view = new android.widget.CheckBox(paramComponentContainer.$context());
    this.view.setOnFocusChangeListener(this);
    this.view.setOnCheckedChangeListener(this);
    paramComponentContainer.$add(this);
    BackgroundColor(16777215);
    Enabled(true);
    this.fontTypeface = 0;
    TextViewUtil.setFontTypeface(this.view, this.fontTypeface, this.bold, this.italic);
    FontSize(14.0F);
    Text("");
    TextColor(-16777216);
    Checked(false);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public int BackgroundColor()
  {
    return this.backgroundColor;
  }
  
  @DesignerProperty(defaultValue="&H00FFFFFF", editorType="color")
  @SimpleProperty
  public void BackgroundColor(int paramInt)
  {
    this.backgroundColor = paramInt;
    if (paramInt != 0)
    {
      TextViewUtil.setBackgroundColor(this.view, paramInt);
      return;
    }
    TextViewUtil.setBackgroundColor(this.view, 16777215);
  }
  
  @SimpleEvent
  public void Changed()
  {
    EventDispatcher.dispatchEvent(this, "Changed", new Object[0]);
  }
  
  @DesignerProperty(defaultValue="False", editorType="boolean")
  @SimpleProperty
  public void Checked(boolean paramBoolean)
  {
    this.view.setChecked(paramBoolean);
    this.view.invalidate();
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean Checked()
  {
    return this.view.isChecked();
  }
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty
  public void Enabled(boolean paramBoolean)
  {
    TextViewUtil.setEnabled(this.view, paramBoolean);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean Enabled()
  {
    return this.view.isEnabled();
  }
  
  @DesignerProperty(defaultValue="False", editorType="boolean")
  @SimpleProperty(userVisible=false)
  public void FontBold(boolean paramBoolean)
  {
    this.bold = paramBoolean;
    TextViewUtil.setFontTypeface(this.view, this.fontTypeface, paramBoolean, this.italic);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, userVisible=false)
  public boolean FontBold()
  {
    return this.bold;
  }
  
  @DesignerProperty(defaultValue="False", editorType="boolean")
  @SimpleProperty(userVisible=false)
  public void FontItalic(boolean paramBoolean)
  {
    this.italic = paramBoolean;
    TextViewUtil.setFontTypeface(this.view, this.fontTypeface, this.bold, paramBoolean);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, userVisible=false)
  public boolean FontItalic()
  {
    return this.italic;
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public float FontSize()
  {
    return TextViewUtil.getFontSize(this.view);
  }
  
  @DesignerProperty(defaultValue="14.0", editorType="non_negative_float")
  @SimpleProperty
  public void FontSize(float paramFloat)
  {
    TextViewUtil.setFontSize(this.view, paramFloat);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, userVisible=false)
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
  
  @SimpleEvent
  public void GotFocus()
  {
    EventDispatcher.dispatchEvent(this, "GotFocus", new Object[0]);
  }
  
  @SimpleEvent
  public void LostFocus()
  {
    EventDispatcher.dispatchEvent(this, "LostFocus", new Object[0]);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
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
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public int TextColor()
  {
    return this.textColor;
  }
  
  @DesignerProperty(defaultValue="&HFF000000", editorType="color")
  @SimpleProperty
  public void TextColor(int paramInt)
  {
    this.textColor = paramInt;
    if (paramInt != 0)
    {
      TextViewUtil.setTextColor(this.view, paramInt);
      return;
    }
    TextViewUtil.setTextColor(this.view, -16777216);
  }
  
  public View getView()
  {
    return this.view;
  }
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    Changed();
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
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\CheckBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */