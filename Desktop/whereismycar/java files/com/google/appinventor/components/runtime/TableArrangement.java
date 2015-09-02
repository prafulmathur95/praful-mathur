package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ViewUtil;

@DesignerComponent(category=ComponentCategory.LAYOUT, description="<p>A formatting element in which to place components that should be displayed in tabular form.</p>", version=1)
@SimpleObject
public class TableArrangement
  extends AndroidViewComponent
  implements Component, ComponentContainer
{
  private final Activity context;
  private final TableLayout viewLayout;
  
  public TableArrangement(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer);
    this.context = paramComponentContainer.$context();
    this.viewLayout = new TableLayout(this.context, 2, 2);
    paramComponentContainer.$add(this);
  }
  
  public void $add(AndroidViewComponent paramAndroidViewComponent)
  {
    this.viewLayout.add(paramAndroidViewComponent);
  }
  
  public Activity $context()
  {
    return this.context;
  }
  
  public Form $form()
  {
    return this.container.$form();
  }
  
  @SimpleProperty(userVisible=false)
  public int Columns()
  {
    return this.viewLayout.getNumColumns();
  }
  
  @DesignerProperty(defaultValue="2", editorType="non_negative_integer")
  @SimpleProperty(userVisible=false)
  public void Columns(int paramInt)
  {
    this.viewLayout.setNumColumns(paramInt);
  }
  
  @SimpleProperty(userVisible=false)
  public int Rows()
  {
    return this.viewLayout.getNumRows();
  }
  
  @DesignerProperty(defaultValue="2", editorType="non_negative_integer")
  @SimpleProperty(userVisible=false)
  public void Rows(int paramInt)
  {
    this.viewLayout.setNumRows(paramInt);
  }
  
  public View getView()
  {
    return this.viewLayout.getLayoutManager();
  }
  
  public void setChildHeight(AndroidViewComponent paramAndroidViewComponent, int paramInt)
  {
    ViewUtil.setChildHeightForTableLayout(paramAndroidViewComponent.getView(), paramInt);
  }
  
  public void setChildWidth(AndroidViewComponent paramAndroidViewComponent, int paramInt)
  {
    ViewUtil.setChildWidthForTableLayout(paramAndroidViewComponent.getView(), paramInt);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\TableArrangement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */