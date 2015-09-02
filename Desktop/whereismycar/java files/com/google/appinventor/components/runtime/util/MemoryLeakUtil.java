package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.runtime.collect.Maps;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryLeakUtil
{
  private static final String LOG_TAG = "MemoryLeakUtil";
  private static final Map<String, WeakReference<Object>> TRACKED_OBJECTS = Maps.newTreeMap();
  private static final AtomicInteger prefixGenerator = new AtomicInteger(0);
  
  public static void checkAllTrackedObjects(boolean paramBoolean1, boolean paramBoolean2)
  {
    Log.i("MemoryLeakUtil", "Checking Tracked Objects ----------------------------------------");
    System.gc();
    int i = 0;
    int j = 0;
    Iterator localIterator = TRACKED_OBJECTS.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (Map.Entry)localIterator.next();
      Object localObject2 = (String)((Map.Entry)localObject1).getKey();
      localObject1 = ((WeakReference)((Map.Entry)localObject1).getValue()).get();
      int m;
      int k;
      if (localObject1 != null)
      {
        m = i + 1;
        k = j;
        label88:
        j = k;
        i = m;
        if (!paramBoolean1) {
          continue;
        }
        localObject2 = ((String)localObject2).substring(((String)localObject2).indexOf("_") + 1);
        localObject2 = new StringBuilder().append("Object with tag ").append((String)localObject2).append(" has ");
        if (localObject1 == null) {
          break label213;
        }
      }
      label213:
      for (localObject1 = "not ";; localObject1 = "")
      {
        Log.i("MemoryLeakUtil", (String)localObject1 + "been garbage collected.");
        j = k;
        i = m;
        break;
        j += 1;
        k = j;
        m = i;
        if (!paramBoolean2) {
          break label88;
        }
        localIterator.remove();
        k = j;
        m = i;
        break label88;
      }
    }
    Log.i("MemoryLeakUtil", "summary: collected " + j);
    Log.i("MemoryLeakUtil", "summary: remaining " + i);
    Log.i("MemoryLeakUtil", "-----------------------------------------------------------------");
  }
  
  public static boolean isTrackedObjectCollected(String paramString, boolean paramBoolean)
  {
    System.gc();
    Object localObject1 = (WeakReference)TRACKED_OBJECTS.get(paramString);
    if (localObject1 != null)
    {
      Object localObject2 = ((WeakReference)localObject1).get();
      localObject1 = paramString.substring(paramString.indexOf("_") + 1);
      StringBuilder localStringBuilder = new StringBuilder().append("Object with tag ").append((String)localObject1).append(" has ");
      if (localObject2 != null) {}
      for (localObject1 = "not ";; localObject1 = "")
      {
        Log.i("MemoryLeakUtil", (String)localObject1 + "been garbage collected.");
        if ((paramBoolean) && (localObject2 == null)) {
          TRACKED_OBJECTS.remove(paramString);
        }
        if (localObject2 != null) {
          break;
        }
        return true;
      }
      return false;
    }
    throw new IllegalArgumentException("key not found");
  }
  
  public static String trackObject(String paramString, Object paramObject)
  {
    if (paramString == null) {}
    for (paramString = prefixGenerator.incrementAndGet() + "_";; paramString = prefixGenerator.incrementAndGet() + "_" + paramString)
    {
      TRACKED_OBJECTS.put(paramString, new WeakReference(paramObject));
      return paramString;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\MemoryLeakUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */