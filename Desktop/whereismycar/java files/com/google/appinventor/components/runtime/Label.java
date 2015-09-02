package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.TextViewUtil;

@DesignerComponent(category=ComponentCategory.USERINTERFACE, description="A Label displays a piece of text, which is specified through the <code>Text</code> property.  Other properties, all of which can be set in the Designer or Blocks Editor, control the appearance and placement of the text.", version=3)
@SimpleObject
public final class Label
  extends AndroidViewComponent
{
  private static final int DEFAULT_LABEL_MARGIN = 2;
  private int backgroundColor;
  private boolean bold;
  private int defaultLabelMarginInDp = 0;
  private int fontTypeface;
  private boolean hasMargins;
  private boolean italic;
  private final LinearLayout.LayoutParams linearLayoutParams;
  private int textAlignment;
  private int textColor;
  private final TextView view;
  
  public Label(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer);
    this.view = new TextView(paramComponentContainer.$context());
    paramComponentContainer.$add(this);
    paramComponentContainer = this.view.getLayoutParams();
    if ((paramComponentContainer instanceof LinearLayout.LayoutParams))
    {
      this.linearLayoutParams = ((LinearLayout.LayoutParams)paramComponentContainer);
      this.defaultLabelMarginInDp = dpToPx(this.view, 2);
    }
    for (;;)
    {
      TextAlignment(0);
      BackgroundColor(16777215);
      this.fontTypeface = 0;
      TextViewUtil.setFontTypeface(this.view, this.fontTypeface, this.bold, this.italic);
      FontSize(14.0F);
      Text("");
      TextColor(-16777216);
      HasMargins(true);
      return;
      this.defaultLabelMarginInDp = 0;
      this.linearLayoutParams = null;
      Log.e("Label", "Error: The label's view does not have linear layout parameters");
      new RuntimeException().printStackTrace();
    }
  }
  
  private static int dpToPx(View paramView, int paramInt)
  {
    float f = paramView.getContext().getResources().getDisplayMetrics().density;
    return Math.round(paramInt * f);
  }
  
  private void setLabelMargins(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = this.defaultLabelMarginInDp;; i = 0)
    {
      this.linearLayoutParams.setMargins(i, i, i, i);
      this.view.invalidate();
      return;
    }
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
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty(userVisible=true)
  public void HasMargins(boolean paramBoolean)
  {
    this.hasMargins = paramBoolean;
    setLabelMargins(paramBoolean);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Reports whether or not the label appears with margins.  All four margins (left, right, top, bottom) are the same.  This property has no effect in the designer, where labels are always shown with margins.", userVisible=true)
  public boolean HasMargins()
  {
    return this.hasMargins;
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
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, userVisible=false)
  public int TextAlignment()
  {
    return this.textAlignment;
  }
  
  @DesignerProperty(defaultValue="0", editorType="textalignment")
  @SimpleProperty(userVisible=false)
  public void TextAlignment(int paramInt)
  {
    this.textAlignment = paramInt;
    TextViewUtil.setAlignment(this.view, paramInt, false);
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
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\Label.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */