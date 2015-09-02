package org.acra.collector;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.acra.ACRA;
import org.acra.ACRAConfiguration;

final class DropBoxCollector
{
  private static final String NO_RESULT = "N/A";
  private static final String[] SYSTEM_TAGS = { "system_app_anr", "system_app_wtf", "system_app_crash", "system_server_anr", "system_server_wtf", "system_server_crash", "BATTERY_DISCHARGE_INFO", "SYSTEM_RECOVERY_LOG", "SYSTEM_BOOT", "SYSTEM_LAST_KMSG", "APANIC_CONSOLE", "APANIC_THREADS", "SYSTEM_RESTART", "SYSTEM_TOMBSTONE", "data_app_strictmode" };
  
  public static String read(Context paramContext, String[] paramArrayOfString)
  {
    for (;;)
    {
      try
      {
        localObject = Compatibility.getDropBoxServiceName();
        if (localObject == null) {
          return "N/A";
        }
        localObject = paramContext.getSystemService((String)localObject);
        localMethod1 = localObject.getClass().getMethod("getNextEntry", new Class[] { String.class, Long.TYPE });
        if (localMethod1 == null) {
          return "";
        }
        localTime = new Time();
        localTime.setToNow();
        localTime.minute -= ACRA.getConfig().dropboxCollectionMinutes();
        localTime.normalize(false);
        l1 = localTime.toMillis(false);
        paramContext = new ArrayList();
        if (ACRA.getConfig().includeDropBoxSystemTags()) {
          paramContext.addAll(Arrays.asList(SYSTEM_TAGS));
        }
        if ((paramArrayOfString != null) && (paramArrayOfString.length > 0)) {
          paramContext.addAll(Arrays.asList(paramArrayOfString));
        }
        if (paramContext.isEmpty()) {
          return "No tag configured for collection.";
        }
        paramArrayOfString = new StringBuilder();
        localIterator = paramContext.iterator();
      }
      catch (SecurityException paramContext)
      {
        Object localObject;
        Method localMethod1;
        Time localTime;
        long l1;
        Iterator localIterator;
        String str1;
        Log.i(ACRA.LOG_TAG, "DropBoxManager not available.");
        return "N/A";
        Method localMethod2 = paramContext.getClass().getMethod("getText", new Class[] { Integer.TYPE });
        Method localMethod3 = paramContext.getClass().getMethod("getTimeMillis", (Class[])null);
        Method localMethod4 = paramContext.getClass().getMethod("close", (Class[])null);
        if (paramContext == null) {
          continue;
        }
        long l2 = ((Long)localMethod3.invoke(paramContext, (Object[])null)).longValue();
        localTime.set(l2);
        paramArrayOfString.append("@").append(localTime.format2445()).append('\n');
        String str2 = (String)localMethod2.invoke(paramContext, new Object[] { Integer.valueOf(500) });
        if (str2 == null) {
          continue;
        }
        paramArrayOfString.append("Text: ").append(str2).append('\n');
        localMethod4.invoke(paramContext, (Object[])null);
        paramContext = localMethod1.invoke(localObject, new Object[] { str1, Long.valueOf(l2) });
        continue;
        paramArrayOfString.append("Not Text!").append('\n');
        continue;
      }
      catch (NoSuchMethodException paramContext)
      {
        Log.i(ACRA.LOG_TAG, "DropBoxManager not available.");
        continue;
        paramContext = paramArrayOfString.toString();
        return paramContext;
      }
      catch (IllegalArgumentException paramContext)
      {
        Log.i(ACRA.LOG_TAG, "DropBoxManager not available.");
        continue;
      }
      catch (IllegalAccessException paramContext)
      {
        Log.i(ACRA.LOG_TAG, "DropBoxManager not available.");
        continue;
      }
      catch (InvocationTargetException paramContext)
      {
        Log.i(ACRA.LOG_TAG, "DropBoxManager not available.");
        continue;
      }
      catch (NoSuchFieldException paramContext)
      {
        Log.i(ACRA.LOG_TAG, "DropBoxManager not available.");
        continue;
      }
      if (!localIterator.hasNext()) {
        continue;
      }
      str1 = (String)localIterator.next();
      paramArrayOfString.append("Tag: ").append(str1).append('\n');
      paramContext = localMethod1.invoke(localObject, new Object[] { str1, Long.valueOf(l1) });
      if (paramContext != null) {
        continue;
      }
      paramArrayOfString.append("Nothing.").append('\n');
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\collector\DropBoxCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */