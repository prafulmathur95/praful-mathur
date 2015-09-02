package com.google.appinventor.components.runtime.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.Texting;

public class SmsBroadcastReceiver
  extends BroadcastReceiver
{
  public static final int NOTIFICATION_ID = 8647;
  public static final String TAG = "SmsBroadcastReceiver";
  
  private String getMessage(Intent paramIntent)
  {
    String str = "";
    if (paramIntent.getAction().equals("com.google.android.apps.googlevoice.SMS_RECEIVED")) {
      paramIntent = paramIntent.getExtras().getString("com.google.android.apps.googlevoice.TEXT");
    }
    for (;;)
    {
      return paramIntent;
      Object[] arrayOfObject = (Object[])paramIntent.getExtras().get("pdus");
      int j = arrayOfObject.length;
      int i = 0;
      paramIntent = str;
      while (i < j)
      {
        paramIntent = SmsMessage.createFromPdu((byte[])arrayOfObject[i]).getMessageBody();
        i += 1;
      }
    }
  }
  
  private String getPhoneNumber(Intent paramIntent)
  {
    String str = "";
    if (paramIntent.getAction().equals("com.google.android.apps.googlevoice.SMS_RECEIVED")) {
      paramIntent = PhoneNumberUtils.formatNumber(paramIntent.getExtras().getString("com.google.android.apps.googlevoice.PHONE_NUMBER"));
    }
    for (;;)
    {
      return paramIntent;
      Object[] arrayOfObject = (Object[])paramIntent.getExtras().get("pdus");
      int j = arrayOfObject.length;
      int i = 0;
      paramIntent = str;
      while (i < j)
      {
        paramIntent = PhoneNumberUtils.formatNumber(SmsMessage.createFromPdu((byte[])arrayOfObject[i]).getOriginatingAddress());
        i += 1;
      }
    }
  }
  
  private boolean isRepl(Context paramContext)
  {
    boolean bool1 = false;
    try
    {
      paramContext = paramContext.getPackageName();
      boolean bool2 = Class.forName(paramContext + ".Screen1").getSuperclass().equals(ReplForm.class);
      if (bool2) {
        bool1 = true;
      }
      return bool1;
    }
    catch (ClassNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  private void sendNotification(Context paramContext, String paramString1, String paramString2)
  {
    Log.i("SmsBroadcastReceiver", "sendingNotification " + paramString1 + ":" + paramString2);
    String str = paramContext.getPackageName();
    Log.i("SmsBroadcastReceiver", "Package name : " + str);
    try
    {
      str = str + ".Screen1";
      Object localObject = new Intent(paramContext, Class.forName(str));
      NotificationManager localNotificationManager;
      Notification localNotification;
      paramContext.printStackTrace();
    }
    catch (ClassNotFoundException paramContext)
    {
      try
      {
        ((Intent)localObject).setAction("android.intent.action.MAIN");
        ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
        ((Intent)localObject).addFlags(805306368);
        localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
        localNotification = new Notification(17301648, paramString1 + " : " + paramString2, System.currentTimeMillis());
        localNotification.flags |= 0x10;
        localNotification.defaults |= 0x1;
        localObject = PendingIntent.getActivity(paramContext, 0, (Intent)localObject, 134217728);
        localNotification.setLatestEventInfo(paramContext, "Sms from " + paramString1, paramString2, (PendingIntent)localObject);
        localNotification.number = Texting.getCachedMsgCount();
        localNotificationManager.notify(null, 8647, localNotification);
        Log.i("SmsBroadcastReceiver", "Notification sent, classname: " + str);
        return;
      }
      catch (ClassNotFoundException paramContext)
      {
        for (;;) {}
      }
      paramContext = paramContext;
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.i("SmsBroadcastReceiver", "onReceive");
    String str = getPhoneNumber(paramIntent);
    paramIntent = getMessage(paramIntent);
    Log.i("SmsBroadcastReceiver", "Received " + str + " : " + paramIntent);
    int i = Texting.isReceivingEnabled(paramContext);
    if (i == 1)
    {
      Log.i("SmsBroadcastReceiver", paramContext.getApplicationInfo().packageName + " Receiving is not enabled, ignoring message.");
      return;
    }
    if (((i == 2) || (isRepl(paramContext))) && (!Texting.isRunning()))
    {
      Log.i("SmsBroadcastReceiver", paramContext.getApplicationInfo().packageName + " Texting isn't running, and either receivingEnabled is FOREGROUND or we are the repl.");
      return;
    }
    Texting.handledReceivedMessage(paramContext, str, paramIntent);
    if (Texting.isRunning())
    {
      Log.i("SmsBroadcastReceiver", paramContext.getApplicationInfo().packageName + " App in Foreground, delivering message.");
      return;
    }
    Log.i("SmsBroadcastReceiver", paramContext.getApplicationInfo().packageName + " Texting isn't running, but receivingEnabled == 2, sending notification.");
    sendNotification(paramContext, str, paramIntent);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\SmsBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */