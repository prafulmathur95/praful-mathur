package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.OAuth2Helper;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

@DesignerComponent(category=ComponentCategory.SOCIAL, description="<p>A component that will, when the <code>SendMessage</code> method is called, send the text message specified in the <code>Message</code> property to the phone number specified in the <code>PhoneNumber</code> property.</p> <p>If the <code>ReceivingEnabled</code> property is set to 1 messages will <b>not</b> be received. If <code>ReceivingEnabled</code> is set to 2 messages will be received only when the application is running. Finally if <code>ReceivingEnabled</code> is set to 3, messages will be received when the application is running <b>and</b> when the application is not running they will be queued and a notification displayed to the user.</p> <p>When a message arrives, the <code>MessageReceived</code> event is raised and provides the sending number and message.</p> <p> An app that includes this component will receive messages even when it is in the background (i.e. when it's not visible on the screen) and, moreso, even if the app is not running, so long as it's installed on the phone. If the phone receives a text message when the app is not in the foreground, the phone will show a notification in the notification bar.  Selecting the notification will bring up the app.  As an app developer, you'll probably want to give your users the ability to control ReceivingEnabled so that they can make the phone ignore text messages.</p> <p>If the GoogleVoiceEnabled property is true, messages can be sent over Wifi using Google Voice. This option requires that the user have a Google Voice account and that the mobile Voice app is installed on the phone. The Google Voice option works only on phones that support Android 2.0 (Eclair) or higher.</p> <p>To specify the phone number (e.g., 650-555-1212), set the <code>PhoneNumber</code> property to a Text string with the specified digits (e.g., 6505551212).  Dashes, dots, and parentheses may be included (e.g., (650)-555-1212) but will be ignored; spaces may not be included.</p> <p>Another way for an app to specify a phone number would be to include a <code>PhoneNumberPicker</code> component, which lets the users select a phone numbers from the ones stored in the the phone's contacts.</p>", iconName="images/texting.png", nonVisible=true, version=3)
@SimpleObject
@UsesLibraries(libraries="google-api-client-beta.jar,google-api-client-android2-beta.jar,google-http-client-beta.jar,google-http-client-android2-beta.jar,google-http-client-android3-beta.jar,google-oauth-client-beta.jar,guava-14.0.1.jar")
@UsesPermissions(permissionNames="android.permission.RECEIVE_SMS, android.permission.SEND_SMS, com.google.android.apps.googlevoice.permission.RECEIVE_SMS, com.google.android.apps.googlevoice.permission.SEND_SMS, android.permission.ACCOUNT_MANAGER, android.permission.MANAGE_ACCOUNTS, android.permission.GET_ACCOUNTS, android.permission.USE_CREDENTIALS")
public class Texting
  extends AndroidNonvisibleComponent
  implements Component, OnResumeListener, OnPauseListener, OnInitializeListener, OnStopListener
{
  private static final String CACHE_FILE = "textingmsgcache";
  public static final String GV_INTENT_FILTER = "com.google.android.apps.googlevoice.SMS_RECEIVED";
  public static final String GV_PACKAGE_NAME = "com.google.android.apps.googlevoice";
  private static final String GV_SERVICE = "grandcentral";
  public static final String GV_SMS_RECEIVED = "com.google.android.apps.googlevoice.SMS_RECEIVED";
  public static final String GV_SMS_SEND_URL = "https://www.google.com/voice/b/0/sms/send/";
  public static final String GV_URL = "https://www.google.com/voice/b/0";
  private static final String MESSAGE_DELIMITER = "\001";
  public static final String MESSAGE_TAG = "com.google.android.apps.googlevoice.TEXT";
  public static final String META_DATA_SMS_KEY = "sms_handler_component";
  public static final String META_DATA_SMS_VALUE = "Texting";
  public static final String PHONE_NUMBER_TAG = "com.google.android.apps.googlevoice.PHONE_NUMBER";
  private static final String PREF_FILE = "TextingState";
  private static final String PREF_GVENABLED = "gvenabled";
  private static final String PREF_RCVENABLED = "receiving2";
  private static final String PREF_RCVENABLED_LEGACY = "receiving";
  private static final String SENT = "SMS_SENT";
  private static final int SERVER_TIMEOUT_MS = 30000;
  public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
  public static final String TAG = "Texting Component";
  public static final String TELEPHONY_INTENT_FILTER = "android.provider.Telephony.SMS_RECEIVED";
  private static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13";
  private static final String UTF8 = "UTF-8";
  private static Activity activity;
  private static Object cacheLock = new Object();
  private static Component component;
  private static boolean isRunning;
  private static int messagesCached;
  private static int receivingEnabled = 2;
  private String authToken;
  private ComponentContainer container;
  private boolean googleVoiceEnabled;
  private GoogleVoiceUtil gvHelper;
  private boolean isInitialized;
  private String message;
  private Queue<String> pendingQueue = new ConcurrentLinkedQueue();
  private String phoneNumber;
  private SmsManager smsManager;
  
  public Texting(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    Log.d("Texting Component", "Texting constructor");
    this.container = paramComponentContainer;
    component = this;
    activity = paramComponentContainer.$context();
    SharedPreferences localSharedPreferences = activity.getSharedPreferences("TextingState", 0);
    if (localSharedPreferences != null)
    {
      receivingEnabled = localSharedPreferences.getInt("receiving2", -1);
      if (receivingEnabled == -1)
      {
        if (localSharedPreferences.getBoolean("receiving", true)) {
          receivingEnabled = 2;
        }
      }
      else
      {
        this.googleVoiceEnabled = localSharedPreferences.getBoolean("gvenabled", false);
        Log.i("Texting Component", "Starting with receiving Enabled=" + receivingEnabled + " GV enabled=" + this.googleVoiceEnabled);
      }
    }
    for (;;)
    {
      if (this.googleVoiceEnabled) {
        new AsyncAuthenticate().execute(new Void[0]);
      }
      this.smsManager = SmsManager.getDefault();
      PhoneNumber("");
      this.isInitialized = false;
      isRunning = false;
      paramComponentContainer.$form().registerForOnInitialize(this);
      paramComponentContainer.$form().registerForOnResume(this);
      paramComponentContainer.$form().registerForOnPause(this);
      paramComponentContainer.$form().registerForOnStop(this);
      return;
      receivingEnabled = 1;
      break;
      receivingEnabled = 2;
      this.googleVoiceEnabled = false;
    }
  }
  
  @SimpleEvent
  public static void MessageReceived(String paramString1, String paramString2)
  {
    if (receivingEnabled > 1)
    {
      Log.i("Texting Component", "MessageReceived from " + paramString1 + ":" + paramString2);
      if (EventDispatcher.dispatchEvent(component, "MessageReceived", new Object[] { paramString1, paramString2 })) {
        Log.i("Texting Component", "Dispatch successful");
      }
    }
    else
    {
      return;
    }
    Log.i("Texting Component", "Dispatch failed, caching");
    synchronized (cacheLock)
    {
      addMessageToCache(activity, paramString1, paramString2);
      return;
    }
  }
  
  private static void addMessageToCache(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramString1 = paramString1 + ":" + paramString2 + "\001";
      Log.i("Texting Component", "Caching " + paramString1);
      paramContext = paramContext.openFileOutput("textingmsgcache", 32768);
      paramContext.write(paramString1.getBytes());
      paramContext.close();
      messagesCached += 1;
      Log.i("Texting Component", "Cached " + paramString1);
      return;
    }
    catch (FileNotFoundException paramContext)
    {
      Log.e("Texting Component", "File not found error writing to cache file");
      paramContext.printStackTrace();
      return;
    }
    catch (IOException paramContext)
    {
      Log.e("Texting Component", "I/O Error writing to cache file");
      paramContext.printStackTrace();
    }
  }
  
  public static int getCachedMsgCount()
  {
    return messagesCached;
  }
  
  public static SmsMessage[] getMessagesFromIntent(Intent paramIntent)
  {
    Object localObject = (Object[])paramIntent.getSerializableExtra("pdus");
    paramIntent = new byte[localObject.length][];
    int i = 0;
    while (i < localObject.length)
    {
      paramIntent[i] = ((byte[])(byte[])localObject[i]);
      i += 1;
    }
    localObject = new byte[paramIntent.length][];
    int j = localObject.length;
    SmsMessage[] arrayOfSmsMessage = new SmsMessage[j];
    i = 0;
    while (i < j)
    {
      localObject[i] = paramIntent[i];
      arrayOfSmsMessage[i] = SmsMessage.createFromPdu(localObject[i]);
      i += 1;
    }
    return arrayOfSmsMessage;
  }
  
  private void handleSentMessage(Context paramContext, BroadcastReceiver paramBroadcastReceiver, int paramInt, String paramString)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return;
      try
      {
        Log.i("Texting Component", "Received OK, msg:" + paramString);
        Toast.makeText(activity, "Message sent", 0).show();
        continue;
      }
      finally {}
      Log.e("Texting Component", "Received generic failure, msg:" + paramString);
      Toast.makeText(activity, "Generic failure: message not sent", 0).show();
      continue;
      Log.e("Texting Component", "Received no service error, msg:" + paramString);
      Toast.makeText(activity, "No Sms service available. Message not sent.", 0).show();
      continue;
      Log.e("Texting Component", "Received null PDU error, msg:" + paramString);
      Toast.makeText(activity, "Received null PDU error. Message not sent.", 0).show();
      continue;
      Log.e("Texting Component", "Received radio off error, msg:" + paramString);
      Toast.makeText(activity, "Could not send SMS message: radio off.", 1).show();
    }
  }
  
  public static void handledReceivedMessage(Context paramContext, String paramString1, String paramString2)
  {
    if (isRunning)
    {
      MessageReceived(paramString1, paramString2);
      return;
    }
    synchronized (cacheLock)
    {
      addMessageToCache(paramContext, paramString1, paramString2);
      return;
    }
  }
  
  public static int isReceivingEnabled(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("TextingState", 0);
    int j = paramContext.getInt("receiving2", -1);
    int i = j;
    if (j == -1)
    {
      if (paramContext.getBoolean("receiving", true)) {
        i = 2;
      }
    }
    else {
      return i;
    }
    return 1;
  }
  
  public static boolean isRunning()
  {
    return isRunning;
  }
  
  private void processCachedMessages()
  {
    synchronized (cacheLock)
    {
      String[] arrayOfString = retrieveCachedMessages();
      if (arrayOfString == null) {
        return;
      }
    }
    Log.i("Texting Component", "processing " + localObject2.length + " cached messages ");
    int i = 0;
    while (i < localObject2.length)
    {
      ??? = localObject2[i];
      Log.i("Texting Component", "Message + " + i + " " + (String)???);
      int j = ((String)???).indexOf(":");
      if ((receivingEnabled > 1) && (j != -1)) {
        MessageReceived(((String)???).substring(0, j), ((String)???).substring(j + 1));
      }
      i += 1;
    }
  }
  
  private void processPendingQueue()
  {
    while (this.pendingQueue.size() != 0)
    {
      String str2 = (String)this.pendingQueue.remove();
      String str1 = str2.substring(0, str2.indexOf(":::"));
      str2 = str2.substring(str2.indexOf(":::") + 3);
      Log.i("Texting Component", "Sending queued message " + str1 + " " + str2);
      new AsyncSendMessage().execute(new String[] { str1, str2 });
    }
  }
  
  /* Error */
  private String[] retrieveCachedMessages()
  {
    // Byte code:
    //   0: ldc 103
    //   2: ldc_w 457
    //   5: invokestatic 221	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   8: pop
    //   9: getstatic 179	com/google/appinventor/components/runtime/Texting:activity	Landroid/app/Activity;
    //   12: ldc 48
    //   14: invokevirtual 461	android/app/Activity:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   17: astore_1
    //   18: sipush 8192
    //   21: newarray <illegal type>
    //   23: astore_2
    //   24: aload_1
    //   25: ifnonnull +14 -> 39
    //   28: ldc 103
    //   30: ldc_w 463
    //   33: invokestatic 352	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   36: pop
    //   37: aconst_null
    //   38: areturn
    //   39: aload_1
    //   40: aload_2
    //   41: invokevirtual 469	java/io/FileInputStream:read	([B)I
    //   44: istore_3
    //   45: ldc 103
    //   47: new 199	java/lang/StringBuilder
    //   50: dup
    //   51: invokespecial 200	java/lang/StringBuilder:<init>	()V
    //   54: ldc_w 471
    //   57: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: iload_3
    //   61: invokevirtual 209	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   64: ldc_w 473
    //   67: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: ldc 48
    //   72: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   78: invokestatic 221	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   81: pop
    //   82: new 330	java/lang/String
    //   85: dup
    //   86: aload_2
    //   87: iconst_0
    //   88: iload_3
    //   89: invokespecial 476	java/lang/String:<init>	([BII)V
    //   92: astore_2
    //   93: aload_1
    //   94: invokevirtual 477	java/io/FileInputStream:close	()V
    //   97: getstatic 179	com/google/appinventor/components/runtime/Texting:activity	Landroid/app/Activity;
    //   100: ldc 48
    //   102: invokevirtual 481	android/app/Activity:deleteFile	(Ljava/lang/String;)Z
    //   105: pop
    //   106: iconst_0
    //   107: putstatic 345	com/google/appinventor/components/runtime/Texting:messagesCached	I
    //   110: ldc 103
    //   112: new 199	java/lang/StringBuilder
    //   115: dup
    //   116: invokespecial 200	java/lang/StringBuilder:<init>	()V
    //   119: ldc_w 483
    //   122: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: aload_2
    //   126: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   132: invokestatic 221	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   135: pop
    //   136: aload_2
    //   137: ldc 67
    //   139: invokevirtual 487	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   142: areturn
    //   143: astore_1
    //   144: ldc 103
    //   146: ldc_w 489
    //   149: invokestatic 352	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   152: pop
    //   153: aconst_null
    //   154: areturn
    //   155: astore_1
    //   156: ldc 103
    //   158: ldc_w 491
    //   161: invokestatic 352	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   164: pop
    //   165: aload_1
    //   166: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   169: aconst_null
    //   170: areturn
    //   171: astore_1
    //   172: goto -16 -> 156
    //   175: astore_1
    //   176: goto -32 -> 144
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	179	0	this	Texting
    //   17	77	1	localFileInputStream	java.io.FileInputStream
    //   143	1	1	localFileNotFoundException1	FileNotFoundException
    //   155	11	1	localIOException1	IOException
    //   171	1	1	localIOException2	IOException
    //   175	1	1	localFileNotFoundException2	FileNotFoundException
    //   23	114	2	localObject	Object
    //   44	45	3	i	int
    // Exception table:
    //   from	to	target	type
    //   9	24	143	java/io/FileNotFoundException
    //   28	37	143	java/io/FileNotFoundException
    //   39	93	143	java/io/FileNotFoundException
    //   9	24	155	java/io/IOException
    //   28	37	155	java/io/IOException
    //   39	93	155	java/io/IOException
    //   93	136	171	java/io/IOException
    //   93	136	175	java/io/FileNotFoundException
  }
  
  private void sendViaSms()
  {
    Log.i("Texting Component", "Sending via built-in Sms");
    ArrayList localArrayList1 = this.smsManager.divideMessage(this.message);
    int j = localArrayList1.size();
    ArrayList localArrayList2 = new ArrayList();
    int i = 0;
    while (i < j)
    {
      localArrayList2.add(PendingIntent.getBroadcast(activity, 0, new Intent("SMS_SENT"), 0));
      i += 1;
    }
    BroadcastReceiver local1 = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        try
        {
          Texting.this.handleSentMessage(paramAnonymousContext, null, getResultCode(), Texting.this.message);
          Texting.activity.unregisterReceiver(this);
          return;
        }
        catch (Exception paramAnonymousContext)
        {
          for (;;)
          {
            Log.e("BroadcastReceiver", "Error in onReceive for msgId " + paramAnonymousIntent.getAction());
            Log.e("BroadcastReceiver", paramAnonymousContext.getMessage());
            paramAnonymousContext.printStackTrace();
          }
        }
        finally {}
      }
    };
    activity.registerReceiver(local1, new IntentFilter("SMS_SENT"));
    this.smsManager.sendMultipartTextMessage(this.phoneNumber, null, localArrayList1, localArrayList2, null);
  }
  
  @DesignerProperty(defaultValue="False", editorType="boolean")
  @SimpleProperty
  public void GoogleVoiceEnabled(boolean paramBoolean)
  {
    if (SdkLevel.getLevel() >= 5)
    {
      this.googleVoiceEnabled = paramBoolean;
      SharedPreferences.Editor localEditor = activity.getSharedPreferences("TextingState", 0).edit();
      localEditor.putBoolean("gvenabled", paramBoolean);
      localEditor.commit();
      return;
    }
    Toast.makeText(activity, "Sorry, your phone's system does not support this option.", 1).show();
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="If true, then SendMessage will attempt to send messages over Wifi using Google Voice.  This requires that the Google Voice app must be installed and set up on the phone or tablet, with a Google Voice account.  If GoogleVoiceEnabled is false, the device must have phone and texting service in order to send or receive messages with this component.")
  public boolean GoogleVoiceEnabled()
  {
    return this.googleVoiceEnabled;
  }
  
  @SimpleProperty
  public String Message()
  {
    return this.message;
  }
  
  @DesignerProperty(defaultValue="", editorType="string")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The message that will be sent when the SendMessage method is called.")
  public void Message(String paramString)
  {
    Log.i("Texting Component", "Message set: " + paramString);
    this.message = paramString;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The number that the message will be sent to when the SendMessage method is called. The number is a text string with the specified digits (e.g., 6505551212).  Dashes, dots, and parentheses may be included (e.g., (650)-555-1212) but will be ignored; spaces should not be included.")
  public String PhoneNumber()
  {
    return this.phoneNumber;
  }
  
  @DesignerProperty(defaultValue="", editorType="string")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public void PhoneNumber(String paramString)
  {
    Log.i("Texting Component", "PhoneNumber set: " + paramString);
    this.phoneNumber = paramString;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="If set to 1 (OFF) no messages will be received.  If set to 2 (FOREGROUND) or3 (ALWAYS) the component will respond to messages if it is running. If the app is not running then the message will be discarded if set to 2 (FOREGROUND). If set to 3 (ALWAYS) and the app is not running the phone will show a notification.  Selecting the notification will bring up the app and signal the MessageReceived event.  Messages received when the app is dormant will be queued, and so several MessageReceived events might appear when the app awakens.  As an app developer, it would be a good idea to give your users control over this property, so they can make their phones ignore text messages when your app is installed.")
  public int ReceivingEnabled()
  {
    return receivingEnabled;
  }
  
  @DesignerProperty(defaultValue="2", editorType="text_receiving")
  @SimpleProperty
  public void ReceivingEnabled(int paramInt)
  {
    if ((paramInt < 1) || (paramInt > 3))
    {
      this.container.$form().dispatchErrorOccurredEvent(this, "Texting", 1701, new Object[] { Integer.valueOf(paramInt) });
      return;
    }
    receivingEnabled = paramInt;
    SharedPreferences.Editor localEditor = activity.getSharedPreferences("TextingState", 0).edit();
    localEditor.putInt("receiving2", paramInt);
    localEditor.remove("receiving");
    localEditor.commit();
  }
  
  @SimpleFunction
  public void SendMessage()
  {
    Log.i("Texting Component", "Sending message " + this.message + " to " + this.phoneNumber);
    String str1 = this.phoneNumber;
    String str2 = this.message;
    if (this.googleVoiceEnabled)
    {
      if (this.authToken == null)
      {
        Log.i("Texting Component", "Need to get an authToken -- enqueing " + str1 + " " + str2);
        if (!this.pendingQueue.offer(str1 + ":::" + str2)) {
          Toast.makeText(activity, "Pending message queue full. Can't send message", 0).show();
        }
        while (this.pendingQueue.size() != 1) {
          return;
        }
        new AsyncAuthenticate().execute(new Void[0]);
        return;
      }
      Log.i("Texting Component", "Creating AsyncSendMessage");
      new AsyncSendMessage().execute(new String[] { str1, str2 });
      return;
    }
    Log.i("Texting Component", "Sending via SMS");
    sendViaSms();
  }
  
  public void onInitialize()
  {
    Log.i("Texting Component", "onInitialize()");
    this.isInitialized = true;
    isRunning = true;
    processCachedMessages();
    ((NotificationManager)activity.getSystemService("notification")).cancel(8647);
  }
  
  public void onPause()
  {
    Log.i("Texting Component", "onPause()");
    isRunning = false;
  }
  
  public void onResume()
  {
    Log.i("Texting Component", "onResume()");
    isRunning = true;
    if (this.isInitialized)
    {
      processCachedMessages();
      ((NotificationManager)activity.getSystemService("notification")).cancel(8647);
    }
  }
  
  public void onStop()
  {
    SharedPreferences.Editor localEditor = activity.getSharedPreferences("TextingState", 0).edit();
    localEditor.putInt("receiving2", receivingEnabled);
    localEditor.putBoolean("gvenabled", this.googleVoiceEnabled);
    localEditor.commit();
  }
  
  class AsyncAuthenticate
    extends AsyncTask<Void, Void, String>
  {
    AsyncAuthenticate() {}
    
    protected String doInBackground(Void... paramVarArgs)
    {
      Log.i("Texting Component", "Authenticating");
      return new OAuth2Helper().getRefreshedAuthToken(Texting.activity, "grandcentral");
    }
    
    protected void onPostExecute(String paramString)
    {
      Log.i("Texting Component", "authToken = " + paramString);
      Texting.access$302(Texting.this, paramString);
      Toast.makeText(Texting.activity, "Finished authentication", 0).show();
      Texting.this.processPendingQueue();
    }
  }
  
  class AsyncSendMessage
    extends AsyncTask<String, Void, String>
  {
    AsyncSendMessage() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      String str2 = paramVarArgs[0];
      String str3 = paramVarArgs[1];
      String str1 = "";
      Log.i("Texting Component", "Async sending phoneNumber = " + str2 + " message = " + str3);
      paramVarArgs = str1;
      try
      {
        str2 = URLEncoder.encode("phoneNumber", "UTF-8") + "=" + URLEncoder.encode(str2, "UTF-8") + "&" + URLEncoder.encode("text", "UTF-8") + "=" + URLEncoder.encode(str3, "UTF-8");
        paramVarArgs = str1;
        if (Texting.this.gvHelper == null)
        {
          paramVarArgs = str1;
          Texting.access$502(Texting.this, new Texting.GoogleVoiceUtil(Texting.this, Texting.this.authToken));
        }
        paramVarArgs = str1;
        if (Texting.this.gvHelper.isInitialized())
        {
          paramVarArgs = str1;
          str1 = Texting.GoogleVoiceUtil.access$600(Texting.this.gvHelper, str2);
          paramVarArgs = str1;
          Log.i("Texting Component", "Sent SMS, response = " + str1);
          paramVarArgs = str1;
        }
        else
        {
          return "IO Error: unable to create GvHelper";
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return paramVarArgs;
    }
    
    protected void onPostExecute(String paramString)
    {
      super.onPostExecute(paramString);
      boolean bool2 = false;
      int i = 0;
      boolean bool1 = bool2;
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        bool1 = bool2;
        bool2 = localJSONObject.getBoolean("ok");
        bool1 = bool2;
        int j = localJSONObject.getJSONObject("data").getInt("code");
        i = j;
        bool1 = bool2;
      }
      catch (JSONException localJSONException)
      {
        do
        {
          for (;;)
          {
            localJSONException.printStackTrace();
          }
          if (i == 58)
          {
            Toast.makeText(Texting.activity, "Errcode 58: SMS limit reached", 0).show();
            return;
          }
        } while (!paramString.contains("IO Error"));
        Toast.makeText(Texting.activity, paramString, 0).show();
      }
      if (bool1)
      {
        Toast.makeText(Texting.activity, "Message sent", 0).show();
        return;
      }
    }
  }
  
  class GoogleVoiceUtil
  {
    private final int MAX_REDIRECTS = 5;
    String authToken;
    String general;
    private boolean isInitialized;
    int redirectCounter;
    String rnrSEE;
    
    public GoogleVoiceUtil(String paramString)
    {
      Log.i("Texting Component", "Creating GV Util");
      this.authToken = paramString;
      try
      {
        this.general = getGeneral();
        Log.i("Texting Component", "general = " + this.general);
        setRNRSEE();
        this.isInitialized = true;
        return;
      }
      catch (IOException this$1)
      {
        Texting.this.printStackTrace();
      }
    }
    
    private String sendGvSms(String paramString)
    {
      Log.i("Texting Component", "sendGvSms()");
      String str = "";
      try
      {
        paramString = paramString + "&" + URLEncoder.encode("_rnr_se", "UTF-8") + "=" + URLEncoder.encode(this.rnrSEE, "UTF-8");
        Log.i("Texting Component", "smsData = " + paramString);
        Object localObject = new URL("https://www.google.com/voice/b/0/sms/send/").openConnection();
        ((URLConnection)localObject).setRequestProperty("Authorization", "GoogleLogin auth=" + this.authToken);
        ((URLConnection)localObject).setRequestProperty("User-agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13");
        ((URLConnection)localObject).setDoOutput(true);
        ((URLConnection)localObject).setConnectTimeout(30000);
        Log.i("Texting Component", "sms request = " + localObject);
        OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(((URLConnection)localObject).getOutputStream());
        localOutputStreamWriter.write(paramString);
        localOutputStreamWriter.flush();
        localObject = new BufferedReader(new InputStreamReader(((URLConnection)localObject).getInputStream()));
        for (paramString = str;; paramString = paramString + str + "\n\r")
        {
          str = ((BufferedReader)localObject).readLine();
          if (str == null) {
            break;
          }
        }
        Log.i("Texting Component", "sendGvSms:  Sent SMS, response = " + paramString);
        localOutputStreamWriter.close();
        ((BufferedReader)localObject).close();
        if (paramString.equals("")) {
          throw new IOException("No Response Data Received.");
        }
      }
      catch (IOException paramString)
      {
        Log.i("Texting Component", "IO Error on Send " + paramString.getMessage());
        paramString.printStackTrace();
        return "IO Error Message not sent";
      }
      return paramString;
    }
    
    private void setRNRSEE()
      throws IOException
    {
      Log.i("Texting Component", "setRNRSEE()");
      if (this.general != null)
      {
        if (this.general.contains("'_rnr_se': '"))
        {
          this.rnrSEE = this.general.split("'_rnr_se': '", 2)[1].split("',", 2)[0];
          Log.i("Texting Component", "Successfully Received rnr_se.");
          return;
        }
        Log.i("Texting Component", "Answer did not contain rnr_se! " + this.general);
        throw new IOException("Answer did not contain rnr_se! " + this.general);
      }
      Log.i("Texting Component", "setRNRSEE(): Answer was null!");
      throw new IOException("setRNRSEE(): Answer was null!");
    }
    
    String get(String paramString)
      throws IOException
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
      int j = 0;
      int i = j;
      Object localObject;
      for (;;)
      {
        try
        {
          localHttpURLConnection.setRequestProperty("Authorization", "GoogleLogin auth=" + this.authToken);
          i = j;
          localHttpURLConnection.setRequestProperty("User-agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13");
          i = j;
          localHttpURLConnection.setInstanceFollowRedirects(false);
          i = j;
          localHttpURLConnection.connect();
          i = j;
          j = localHttpURLConnection.getResponseCode();
          i = j;
          Log.i("Texting Component", paramString + " - " + localHttpURLConnection.getResponseMessage());
          if (j == 200)
          {
            InputStream localInputStream = localHttpURLConnection.getInputStream();
            this.redirectCounter = 0;
            if (localInputStream != null) {
              break;
            }
            throw new IOException(paramString + " : " + localHttpURLConnection.getResponseMessage() + "(" + j + ") : InputStream was null : exiting.");
          }
        }
        catch (Exception localException1)
        {
          throw new IOException(paramString + " : " + localHttpURLConnection.getResponseMessage() + "(" + i + ") : IO Error.");
        }
        if ((j == 301) || (j == 302) || (j == 303) || (j == 307))
        {
          this.redirectCounter += 1;
          if (this.redirectCounter > 5)
          {
            this.redirectCounter = 0;
            throw new IOException(paramString + " : " + localHttpURLConnection.getResponseMessage() + "(" + j + ") : Too many redirects. exiting.");
          }
          localObject = localHttpURLConnection.getHeaderField("Location");
          if ((localObject != null) && (!((String)localObject).equals("")))
          {
            System.out.println(paramString + " - " + j + " - new URL: " + (String)localObject);
            return get((String)localObject);
          }
          throw new IOException(paramString + " : " + localHttpURLConnection.getResponseMessage() + "(" + j + ") : Received moved answer but no Location. exiting.");
        }
        localObject = localHttpURLConnection.getErrorStream();
      }
      StringBuffer localStringBuffer;
      try
      {
        localObject = new BufferedReader(new InputStreamReader((InputStream)localObject));
        localStringBuffer = new StringBuffer();
        for (;;)
        {
          String str2 = ((BufferedReader)localObject).readLine();
          if (str2 == null) {
            break;
          }
          localStringBuffer.append(str2 + "\n\r");
        }
        localException2.close();
      }
      catch (Exception localException2)
      {
        throw new IOException(paramString + " - " + localHttpURLConnection.getResponseMessage() + "(" + j + ") - " + localException2.getLocalizedMessage());
      }
      String str1 = localStringBuffer.toString();
      return str1;
    }
    
    public String getGeneral()
      throws IOException
    {
      Log.i("Texting Component", "getGeneral()");
      return get("https://www.google.com/voice/b/0");
    }
    
    public boolean isInitialized()
    {
      return this.isInitialized;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\Texting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */