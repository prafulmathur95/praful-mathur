package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import java.util.ArrayList;

@DesignerComponent(category=ComponentCategory.MEDIA, description="Component for using Voice Recognition to convert from speech to text", iconName="images/speechRecognizer.png", nonVisible=true, version=1)
@SimpleObject
public class SpeechRecognizer
  extends AndroidNonvisibleComponent
  implements Component, ActivityResultListener
{
  private final ComponentContainer container;
  private int requestCode;
  private String result;
  
  public SpeechRecognizer(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.container = paramComponentContainer;
    this.result = "";
  }
  
  @SimpleEvent
  public void AfterGettingText(String paramString)
  {
    EventDispatcher.dispatchEvent(this, "AfterGettingText", new Object[] { paramString });
  }
  
  @SimpleEvent
  public void BeforeGettingText()
  {
    EventDispatcher.dispatchEvent(this, "BeforeGettingText", new Object[0]);
  }
  
  @SimpleFunction
  public void GetText()
  {
    BeforeGettingText();
    Intent localIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
    localIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
    if (this.requestCode == 0) {
      this.requestCode = this.form.registerForActivityResult(this);
    }
    this.container.$context().startActivityForResult(localIntent, this.requestCode);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public String Result()
  {
    return this.result;
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == this.requestCode) && (paramInt2 == -1)) {
      if (!paramIntent.hasExtra("android.speech.extra.RESULTS")) {
        break label51;
      }
    }
    label51:
    for (this.result = ((String)paramIntent.getExtras().getStringArrayList("android.speech.extra.RESULTS").get(0));; this.result = "")
    {
      AfterGettingText(this.result);
      return;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\SpeechRecognizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */