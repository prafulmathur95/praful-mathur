package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public final class WebViewActivity
  extends Activity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = new WebView(this);
    paramBundle.getSettings().setJavaScriptEnabled(true);
    paramBundle.setWebViewClient(new WebViewClient()
    {
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        Log.i("WebView", "Handling url " + paramAnonymousString);
        Uri localUri = Uri.parse(paramAnonymousString);
        if (localUri.getScheme().equals("appinventor"))
        {
          paramAnonymousWebView = new Intent();
          paramAnonymousWebView.setData(localUri);
          WebViewActivity.this.setResult(-1, paramAnonymousWebView);
          WebViewActivity.this.finish();
        }
        for (;;)
        {
          return true;
          paramAnonymousWebView.loadUrl(paramAnonymousString);
        }
      }
    });
    setContentView(paramBundle);
    Object localObject = getIntent();
    if ((localObject != null) && (((Intent)localObject).getData() != null))
    {
      localObject = ((Intent)localObject).getData();
      String str1 = ((Uri)localObject).getScheme();
      String str2 = ((Uri)localObject).getHost();
      Log.i("WebView", "Got intent with URI: " + localObject + ", scheme=" + str1 + ", host=" + str2);
      paramBundle.loadUrl(((Uri)localObject).toString());
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\WebViewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */