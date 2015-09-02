package org.acra.collector;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.acra.ACRA;

final class DumpSysCollector
{
  public static String collectMemInfo()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      try
      {
        Object localObject = new ArrayList();
        ((List)localObject).add("dumpsys");
        ((List)localObject).add("meminfo");
        ((List)localObject).add(Integer.toString(android.os.Process.myPid()));
        localObject = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec((String[])((List)localObject).toArray(new String[((List)localObject).size()])).getInputStream()), 8192);
        str = ((BufferedReader)localObject).readLine();
        if (str != null) {
          continue;
        }
      }
      catch (IOException localIOException)
      {
        String str;
        Log.e(ACRA.LOG_TAG, "DumpSysCollector.meminfo could not retrieve data", localIOException);
        continue;
      }
      return localStringBuilder.toString();
      localStringBuilder.append(str);
      localStringBuilder.append("\n");
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\collector\DumpSysCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */