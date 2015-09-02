package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager;
import android.webkit.GeolocationPermissions;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.appinventor.components.runtime.WebViewer;
import java.io.File;

public class EclairUtil
{
  public static void clearWebViewGeoLoc()
  {
    GeolocationPermissions.getInstance().clearAll();
  }
  
  public static String getInstallerPackageName(String paramString, Activity paramActivity)
  {
    return paramActivity.getPackageManager().getInstallerPackageName(paramString);
  }
  
  public static void overridePendingTransitions(Activity paramActivity, int paramInt1, int paramInt2)
  {
    paramActivity.overridePendingTransition(paramInt1, paramInt2);
  }
  
  public static void setupWebViewGeoLoc(WebViewer paramWebViewer, WebView paramWebView, final Activity paramActivity)
  {
    paramWebView.getSettings().setGeolocationDatabasePath(paramActivity.getFilesDir().getAbsolutePath());
    paramWebView.getSettings().setDatabaseEnabled(true);
    paramWebView.setWebChromeClient(new WebChromeClient()
    {
      public void onGeolocationPermissionsShowPrompt(final String paramAnonymousString, final GeolocationPermissions.Callback paramAnonymousCallback)
      {
        if (!this.val$caller.PromptforPermission())
        {
          paramAnonymousCallback.invoke(paramAnonymousString, true, true);
          return;
        }
        AlertDialog localAlertDialog = new AlertDialog.Builder(paramActivity).create();
        localAlertDialog.setTitle("Permission Request");
        String str = paramAnonymousString;
        if (paramAnonymousString.equals("file://")) {
          str = "This Application";
        }
        localAlertDialog.setMessage(str + " would like to access your location.");
        localAlertDialog.setButton(-1, "Allow", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymousCallback.invoke(paramAnonymousString, true, true);
          }
        });
        localAlertDialog.setButton(-2, "Refuse", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymousCallback.invoke(paramAnonymousString, false, true);
          }
        });
        localAlertDialog.show();
      }
    });
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\EclairUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */