package org.acra.collector;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.acra.ACRA;
import org.acra.ACRAConfiguration;

final class SharedPreferencesCollector
{
  public static String collect(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    TreeMap localTreeMap = new TreeMap();
    localTreeMap.put("default", PreferenceManager.getDefaultSharedPreferences(paramContext));
    Object localObject1 = ACRA.getConfig().additionalSharedPreferences();
    Object localObject2;
    if (localObject1 != null)
    {
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        localObject2 = localObject1[i];
        localTreeMap.put(localObject2, paramContext.getSharedPreferences((String)localObject2, 0));
        i += 1;
      }
    }
    paramContext = localTreeMap.keySet().iterator();
    if (paramContext.hasNext())
    {
      localObject1 = (String)paramContext.next();
      localStringBuilder.append((String)localObject1).append("\n");
      localObject1 = (SharedPreferences)localTreeMap.get(localObject1);
      if (localObject1 != null)
      {
        localObject1 = ((SharedPreferences)localObject1).getAll();
        if ((localObject1 != null) && (((Map)localObject1).size() > 0)) {
          localObject2 = ((Map)localObject1).keySet().iterator();
        }
        while (((Iterator)localObject2).hasNext())
        {
          String str = (String)((Iterator)localObject2).next();
          if (!filteredKey(str)) {
            if (((Map)localObject1).get(str) != null)
            {
              localStringBuilder.append(str).append("=").append(((Map)localObject1).get(str).toString()).append("\n");
            }
            else
            {
              localStringBuilder.append(str).append("=").append("null\n");
              continue;
              localStringBuilder.append("empty\n");
            }
          }
        }
      }
      for (;;)
      {
        localStringBuilder.append("\n");
        break;
        localStringBuilder.append("null\n");
      }
    }
    return localStringBuilder.toString();
  }
  
  private static boolean filteredKey(String paramString)
  {
    String[] arrayOfString = ACRA.getConfig().excludeMatchingSharedPreferencesKeys();
    if (arrayOfString.length < 0) {
      return paramString.matches(arrayOfString[0]);
    }
    return false;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\collector\SharedPreferencesCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */