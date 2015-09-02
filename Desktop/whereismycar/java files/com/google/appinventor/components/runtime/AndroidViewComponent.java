package com.google.appinventor.components.runtime;

import android.view.View;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.SimplePropertyCopier;

@SimpleObject
public abstract class AndroidViewComponent
  extends VisibleComponent
{
  private int column = -1;
  protected final ComponentContainer container;
  private int lastSetHeight = -3;
  private int lastSetWidth = -3;
  private int row = -1;
  
  protected AndroidViewComponent(ComponentContainer paramComponentContainer)
  {
    this.container = paramComponentContainer;
  }
  
  @SimpleProperty(userVisible=false)
  public int Column()
  {
    return this.column;
  }
  
  @SimpleProperty(userVisible=false)
  public void Column(int paramInt)
  {
    this.column = paramInt;
  }
  
  @SimplePropertyCopier
  public void CopyHeight(AndroidViewComponent paramAndroidViewComponent)
  {
    Height(paramAndroidViewComponent.lastSetHeight);
  }
  
  @SimplePropertyCopier
  public void CopyWidth(AndroidViewComponent paramAndroidViewComponent)
  {
    Width(paramAndroidViewComponent.lastSetWidth);
  }
  
  @SimpleProperty
  public int Height()
  {
    return getView().getHeight();
  }
  
  @SimpleProperty
  public void Height(int paramInt)
  {
    this.container.setChildHeight(this, paramInt);
    this.lastSetHeight = paramInt;
  }
  
  @SimpleProperty(userVisible=false)
  public int Row()
  {
    return this.row;
  }
  
  @SimpleProperty(userVisible=false)
  public void Row(int paramInt)
  {
    this.row = paramInt;
  }
  
  @DesignerProperty(defaultValue="True", editorType="visibility")
  @SimpleProperty(description="Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.")
  public void Visible(Boolean paramBoolean)
  {
    View localView = getView();
    if (paramBoolean.booleanValue()) {}
    for (int i = 0;; i = 8)
    {
      localView.setVisibility(i);
      return;
    }
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public boolean Visible()
  {
    return getView().getVisibility() == 0;
  }
  
  @SimpleProperty
  public int Width()
  {
    return getView().getWidth();
  }
  
  @SimpleProperty
  public void Width(int paramInt)
  {
    this.container.setChildWidth(this, paramInt);
    this.lastSetWidth = paramInt;
  }
  
  public HandlesEventDispatching getDispatchDelegate()
  {
    return this.container.$form();
  }
  
  public abstract View getView();
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\AndroidViewComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */