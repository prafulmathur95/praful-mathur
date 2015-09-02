package org.acra.collector;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.acra.ACRA;

final class DeviceFeaturesCollector
{
  public static String getFeatures(Context paramContext)
  {
    if (Compatibility.getAPILevel() < 5) {
      return "Data available only with API Level >= 5";
    }
    localStringBuilder = new StringBuilder();
    try
    {
      Object localObject1 = paramContext.getPackageManager();
      localObject1 = (Object[])PackageManager.class.getMethod("getSystemAvailableFeatures", (Class[])null).invoke(localObject1, new Object[0]);
      int j = localObject1.length;
      int i = 0;
      if (i < j)
      {
        Object localObject2 = localObject1[i];
        String str = (String)localObject2.getClass().getField("name").get(localObject2);
        if (str != null) {
          localStringBuilder.append(str);
        }
        for (;;)
        {
          localStringBuilder.append("\n");
          i += 1;
          break;
          localObject2 = (String)localObject2.getClass().getMethod("getGlEsVersion", (Class[])null).invoke(localObject2, new Object[0]);
          localStringBuilder.append("glEsVersion = ");
          localStringBuilder.append((String)localObject2);
        }
      }
      return localStringBuilder.toString();
    }
    catch (Throwable localThrowable)
    {
      Log.w(ACRA.LOG_TAG, "Couldn't retrieve DeviceFeatures for " + paramContext.getPackageName(), localThrowable);
      localStringBuilder.append("Could not retrieve data: ");
      localStringBuilder.append(localThrowable.getMessage());
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\collector\DeviceFeaturesCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */