package com.google.appinventor.components.runtime.util;

import android.os.Handler;

public class AsynchUtil
{
  public static void runAsynchronously(final Handler paramHandler, Runnable paramRunnable1, final Runnable paramRunnable2)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        this.val$call.run();
        if (paramRunnable2 != null) {
          paramHandler.post(new Runnable()
          {
            public void run()
            {
              AsynchUtil.1.this.val$callback.run();
            }
          });
        }
      }
    }).start();
  }
  
  public static void runAsynchronously(Runnable paramRunnable)
  {
    new Thread(paramRunnable).start();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\AsynchUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */