package org.acra.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import org.acra.ACRA;

public final class ReportUtils
{
  public static String getApplicationFilePath(Context paramContext)
  {
    File localFile = paramContext.getFilesDir();
    if (localFile != null) {
      return localFile.getAbsolutePath();
    }
    Log.w(ACRA.LOG_TAG, "Couldn't retrieve ApplicationFilePath for : " + paramContext.getPackageName());
    return "Couldn't retrieve ApplicationFilePath";
  }
  
  public static long getAvailableInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  public static String getDeviceId(Context paramContext)
  {
    try
    {
      String str = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      return str;
    }
    catch (RuntimeException localRuntimeException)
    {
      Log.w(ACRA.LOG_TAG, "Couldn't retrieve DeviceId for : " + paramContext.getPackageName(), localRuntimeException);
    }
    return null;
  }
  
  public static String getDisplayDetails(Context paramContext)
  {
    try
    {
      Object localObject = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((Display)localObject).getMetrics(localDisplayMetrics);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("width=").append(((Display)localObject).getWidth()).append('\n');
      localStringBuilder.append("height=").append(((Display)localObject).getHeight()).append('\n');
      localStringBuilder.append("pixelFormat=").append(((Display)localObject).getPixelFormat()).append('\n');
      localStringBuilder.append("refreshRate=").append(((Display)localObject).getRefreshRate()).append("fps").append('\n');
      localStringBuilder.append("metrics.density=x").append(localDisplayMetrics.density).append('\n');
      localStringBuilder.append("metrics.scaledDensity=x").append(localDisplayMetrics.scaledDensity).append('\n');
      localStringBuilder.append("metrics.widthPixels=").append(localDisplayMetrics.widthPixels).append('\n');
      localStringBuilder.append("metrics.heightPixels=").append(localDisplayMetrics.heightPixels).append('\n');
      localStringBuilder.append("metrics.xdpi=").append(localDisplayMetrics.xdpi).append('\n');
      localStringBuilder.append("metrics.ydpi=").append(localDisplayMetrics.ydpi);
      localObject = localStringBuilder.toString();
      return (String)localObject;
    }
    catch (RuntimeException localRuntimeException)
    {
      Log.w(ACRA.LOG_TAG, "Couldn't retrieve DisplayDetails for : " + paramContext.getPackageName(), localRuntimeException);
    }
    return "Couldn't retrieve Display Details";
  }
  
  public static long getTotalInternalMemorySize()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getBlockCount() * l;
  }
  
  public static String sparseArrayToString(SparseArray<?> paramSparseArray)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramSparseArray == null) {
      return "null";
    }
    localStringBuilder.append('{');
    int i = 0;
    if (i < paramSparseArray.size())
    {
      localStringBuilder.append(paramSparseArray.keyAt(i));
      localStringBuilder.append(" => ");
      if (paramSparseArray.valueAt(i) == null) {
        localStringBuilder.append("null");
      }
      for (;;)
      {
        if (i < paramSparseArray.size() - 1) {
          localStringBuilder.append(", ");
        }
        i += 1;
        break;
        localStringBuilder.append(paramSparseArray.valueAt(i).toString());
      }
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\util\ReportUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */