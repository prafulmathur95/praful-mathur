package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.SdkLevel;

@DesignerComponent(category=ComponentCategory.SENSORS, description="Component for using the Barcode Scanner to read a barcode", iconName="images/barcodeScanner.png", nonVisible=true, version=2)
@SimpleObject
@UsesLibraries(libraries="Barcode.jar,core.jar")
@UsesPermissions(permissionNames="android.permission.CAMERA")
public class BarcodeScanner
  extends AndroidNonvisibleComponent
  implements ActivityResultListener, Component
{
  private static final String LOCAL_SCAN = "com.google.zxing.client.android.AppInvCaptureActivity";
  private static final String SCANNER_RESULT_NAME = "SCAN_RESULT";
  private static final String SCAN_INTENT = "com.google.zxing.client.android.SCAN";
  private final ComponentContainer container;
  private int requestCode;
  private String result = "";
  private boolean useExternalScanner = true;
  
  public BarcodeScanner(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.container = paramComponentContainer;
  }
  
  @SimpleEvent
  public void AfterScan(String paramString)
  {
    EventDispatcher.dispatchEvent(this, "AfterScan", new Object[] { paramString });
  }
  
  @SimpleFunction(description="Begins a barcode scan, using the camera. When the scan is complete, the AfterScan event will be raised.")
  public void DoScan()
  {
    Intent localIntent = new Intent("com.google.zxing.client.android.SCAN");
    if ((!this.useExternalScanner) && (SdkLevel.getLevel() >= 5)) {
      localIntent.setComponent(new ComponentName(this.container.$form().getPackageName(), "com.google.zxing.client.android.AppInvCaptureActivity"));
    }
    if (this.requestCode == 0) {
      this.requestCode = this.form.registerForActivityResult(this);
    }
    try
    {
      this.container.$context().startActivityForResult(localIntent, this.requestCode);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      localActivityNotFoundException.printStackTrace();
      this.container.$form().dispatchErrorOccurredEvent(this, "BarcodeScanner", 1501, new Object[] { "" });
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Text result of the previous scan.")
  public String Result()
  {
    return this.result;
  }
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty
  public void UseExternalScanner(boolean paramBoolean)
  {
    this.useExternalScanner = paramBoolean;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="If true App Inventor will look for and use an external scanning program such as \"Bar Code Scanner.\"")
  public boolean UseExternalScanner()
  {
    return this.useExternalScanner;
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == this.requestCode) && (paramInt2 == -1)) {
      if (!paramIntent.hasExtra("SCAN_RESULT")) {
        break label41;
      }
    }
    label41:
    for (this.result = paramIntent.getStringExtra("SCAN_RESULT");; this.result = "")
    {
      AfterScan(this.result);
      return;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\BarcodeScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */