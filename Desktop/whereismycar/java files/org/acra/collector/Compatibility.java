package org.acra.collector;

import android.content.Context;
import android.os.Build.VERSION;
import java.lang.reflect.Field;

class Compatibility
{
  public static int getAPILevel()
  {
    try
    {
      int i = Build.VERSION.class.getField("SDK_INT").getInt(null);
      return i;
    }
    catch (SecurityException localSecurityException)
    {
      return Integer.parseInt(Build.VERSION.SDK);
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      return Integer.parseInt(Build.VERSION.SDK);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      return Integer.parseInt(Build.VERSION.SDK);
    }
    catch (IllegalAccessException localIllegalAccessException) {}
    return Integer.parseInt(Build.VERSION.SDK);
  }
  
  public static String getDropBoxServiceName()
    throws NoSuchFieldException, IllegalAccessException
  {
    String str = null;
    Field localField = Context.class.getField("DROPBOX_SERVICE");
    if (localField != null) {
      str = (String)localField.get(null);
    }
    return str;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\collector\Compatibility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */