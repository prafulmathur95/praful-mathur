package org.acra.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import org.acra.ACRA;

public final class PackageManagerWrapper
{
  private final Context context;
  
  public PackageManagerWrapper(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public PackageInfo getPackageInfo()
  {
    Object localObject = this.context.getPackageManager();
    if (localObject == null) {
      return null;
    }
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(this.context.getPackageName(), 0);
      return (PackageInfo)localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Log.v(ACRA.LOG_TAG, "Failed to find PackageInfo for current App : " + this.context.getPackageName());
      return null;
    }
    catch (RuntimeException localRuntimeException) {}
    return null;
  }
  
  public boolean hasPermission(String paramString)
  {
    PackageManager localPackageManager = this.context.getPackageManager();
    if (localPackageManager == null) {}
    for (;;)
    {
      return false;
      try
      {
        int i = localPackageManager.checkPermission(paramString, this.context.getPackageName());
        if (i == 0) {
          return true;
        }
      }
      catch (RuntimeException paramString) {}
    }
    return false;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\util\PackageManagerWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */