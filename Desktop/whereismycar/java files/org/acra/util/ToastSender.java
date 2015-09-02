package org.acra.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import org.acra.ACRA;

public final class ToastSender
{
  public static void sendToast(Context paramContext, int paramInt1, int paramInt2)
  {
    try
    {
      Toast.makeText(paramContext, paramInt1, paramInt2).show();
      return;
    }
    catch (RuntimeException paramContext)
    {
      Log.e(ACRA.LOG_TAG, "Could not send crash Toast", paramContext);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\util\ToastSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */