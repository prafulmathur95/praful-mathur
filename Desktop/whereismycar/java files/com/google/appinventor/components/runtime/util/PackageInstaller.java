package com.google.appinventor.components.runtime.util;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class PackageInstaller
{
  private static final String LOG_TAG = "PackageInstaller(AppInventor)";
  private static final String REPL_ASSET_DIR = "/sdcard/AppInventor/assets/";
  
  public static void doPackageInstall(final Form paramForm, String paramString)
  {
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        FileOutputStream localFileOutputStream;
        try
        {
          localObject = new URL(this.val$inurl).openConnection();
          File localFile = new File("/sdcard/AppInventor/assets/");
          localObject = new BufferedInputStream(((URLConnection)localObject).getInputStream());
          localFileOutputStream = new FileOutputStream(new File(localFile + "/package.apk"));
          byte[] arrayOfByte = new byte[32768];
          for (;;)
          {
            int i = ((InputStream)localObject).read(arrayOfByte, 0, 32768);
            if (i <= 0) {
              break;
            }
            localFileOutputStream.write(arrayOfByte, 0, i);
          }
          ((InputStream)localObject).close();
        }
        catch (Exception localException)
        {
          paramForm.dispatchErrorOccurredEvent(paramForm, "PackageInstaller", 1101, new Object[] { this.val$inurl });
          return;
        }
        localFileOutputStream.close();
        Log.d("PackageInstaller(AppInventor)", "About to Install package from " + this.val$inurl);
        Object localObject = new Intent("android.intent.action.VIEW");
        ((Intent)localObject).setDataAndType(Uri.fromFile(new File(localException + "/package.apk")), "application/vnd.android.package-archive");
        paramForm.startActivity((Intent)localObject);
      }
    });
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\PackageInstaller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */