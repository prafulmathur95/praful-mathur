package com.google.appinventor.components.runtime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;
import com.google.appinventor.components.runtime.util.AppInvHTTPD;
import com.google.appinventor.components.runtime.util.RetValManager;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class ReplForm
  extends Form
{
  private static final String REPL_ASSET_DIR = "/sdcard/AppInventor/assets/";
  public static ReplForm topform;
  private boolean IsUSBRepl = false;
  private boolean assetsLoaded = false;
  private AppInvHTTPD httpdServer = null;
  private boolean isDirect = false;
  private Object replResult = null;
  private String replResultFormName = null;
  
  public ReplForm()
  {
    topform = this;
  }
  
  private void checkAssetDir()
  {
    File localFile = new File("/sdcard/AppInventor/assets/");
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
  }
  
  void HandleReturnValues()
  {
    Log.d("ReplForm", "HandleReturnValues() Called, replResult = " + this.replResult);
    if (this.replResult != null)
    {
      OtherScreenClosed(this.replResultFormName, this.replResult);
      Log.d("ReplForm", "Called OtherScreenClosed");
      this.replResult = null;
    }
  }
  
  public void addSettingsButton(Menu paramMenu)
  {
    paramMenu.add(0, 0, 3, "Settings").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramAnonymousMenuItem)
      {
        PhoneStatus.doSettings();
        return true;
      }
    }).setIcon(17301651);
  }
  
  protected void closeApplicationFromBlocks()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(ReplForm.this, "Closing forms is not currently supported during development.", 1).show();
      }
    });
  }
  
  protected void closeForm(Intent paramIntent)
  {
    RetValManager.popScreen("Not Yet");
  }
  
  public boolean isAssetsLoaded()
  {
    return this.assetsLoaded;
  }
  
  public boolean isDirect()
  {
    return this.isDirect;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Log.d("ReplForm", "onCreate");
    processExtras(getIntent(), false);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    addSettingsButton(paramMenu);
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.httpdServer != null)
    {
      this.httpdServer.stop();
      this.httpdServer = null;
    }
    finish();
    System.exit(0);
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    Log.d("ReplForm", "onNewIntent Called");
    processExtras(paramIntent, true);
  }
  
  protected void onResume()
  {
    super.onResume();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  protected void processExtras(Intent paramIntent, boolean paramBoolean)
  {
    paramIntent = paramIntent.getExtras();
    if (paramIntent != null)
    {
      Log.d("ReplForm", "extras: " + paramIntent);
      Iterator localIterator = paramIntent.keySet().iterator();
      while (localIterator.hasNext()) {
        Log.d("ReplForm", "Extra Key: " + (String)localIterator.next());
      }
    }
    if ((paramIntent != null) && (paramIntent.getBoolean("rundirect")))
    {
      Log.d("ReplForm", "processExtras rundirect is true and restart is " + paramBoolean);
      this.isDirect = true;
      this.assetsLoaded = true;
      if (paramBoolean)
      {
        clear();
        if (this.httpdServer == null) {
          break label160;
        }
        this.httpdServer.resetSeq();
      }
    }
    return;
    label160:
    startHTTPD(true);
    paramIntent = this.httpdServer;
    AppInvHTTPD.setHmacKey("emulator");
  }
  
  public void setAssetsLoaded()
  {
    this.assetsLoaded = true;
  }
  
  public void setFormName(String paramString)
  {
    this.formName = paramString;
    Log.d("ReplForm", "formName is now " + paramString);
  }
  
  public void setIsUSBrepl()
  {
    this.IsUSBRepl = true;
  }
  
  protected void setResult(Object paramObject)
  {
    Log.d("ReplForm", "setResult: " + paramObject);
    this.replResult = paramObject;
    this.replResultFormName = this.formName;
  }
  
  public void startHTTPD(boolean paramBoolean)
  {
    try
    {
      if (this.httpdServer == null)
      {
        checkAssetDir();
        this.httpdServer = new AppInvHTTPD(8001, new File("/sdcard/AppInventor/assets/"), paramBoolean, this);
        Log.i("ReplForm", "started AppInvHTTPD");
      }
      return;
    }
    catch (IOException localIOException)
    {
      Log.e("ReplForm", "Setting up NanoHTTPD: " + localIOException.toString());
    }
  }
  
  protected void startNewForm(String paramString, Object paramObject)
  {
    if (paramObject != null) {
      this.startupValue = jsonEncodeForForm(paramObject, "open another screen with start value");
    }
    RetValManager.pushScreen(paramString, paramObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\ReplForm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */