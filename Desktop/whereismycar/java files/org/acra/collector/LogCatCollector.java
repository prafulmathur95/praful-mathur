package org.acra.collector;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.acra.ACRA;
import org.acra.ACRAConfiguration;
import org.acra.util.BoundedLinkedList;

class LogCatCollector
{
  private static final int DEFAULT_TAIL_COUNT = 100;
  
  public static String collectLogCat(String paramString)
  {
    int i = android.os.Process.myPid();
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (ACRA.getConfig().logcatFilterByPid())
    {
      localObject1 = localObject2;
      if (i > 0) {
        localObject1 = Integer.toString(i) + "):";
      }
    }
    localObject2 = new ArrayList();
    ((List)localObject2).add("logcat");
    if (paramString != null)
    {
      ((List)localObject2).add("-b");
      ((List)localObject2).add(paramString);
    }
    Object localObject3 = new ArrayList(Arrays.asList(ACRA.getConfig().logcatArguments()));
    int k = ((List)localObject3).indexOf("-t");
    if ((k > -1) && (k < ((List)localObject3).size()))
    {
      int j = Integer.parseInt((String)((List)localObject3).get(k + 1));
      i = j;
      if (Compatibility.getAPILevel() < 8)
      {
        ((List)localObject3).remove(k + 1);
        ((List)localObject3).remove(k);
        ((List)localObject3).add("-d");
        i = j;
      }
      if (i <= 0) {
        break label291;
      }
      label199:
      paramString = new BoundedLinkedList(i);
      ((List)localObject2).addAll((Collection)localObject3);
    }
    for (;;)
    {
      try
      {
        localObject2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec((String[])((List)localObject2).toArray(new String[((List)localObject2).size()])).getInputStream()), 8192);
        Log.d(ACRA.LOG_TAG, "Retrieving logcat output...");
        localObject3 = ((BufferedReader)localObject2).readLine();
        if (localObject3 != null) {
          continue;
        }
      }
      catch (IOException localIOException)
      {
        label291:
        Log.e(ACRA.LOG_TAG, "LogCatCollector.collectLogCat could not retrieve data.", localIOException);
        continue;
      }
      return paramString.toString();
      i = -1;
      break;
      i = 100;
      break label199;
      if ((localObject1 == null) || (((String)localObject3).contains((CharSequence)localObject1))) {
        paramString.add((String)localObject3 + "\n");
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\collector\LogCatCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */