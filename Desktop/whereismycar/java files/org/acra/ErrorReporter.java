package org.acra;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.os.Looper;
import android.os.Process;
import android.text.format.Time;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.acra.annotation.ReportsCrashes;
import org.acra.collector.ConfigurationCollector;
import org.acra.collector.CrashReportData;
import org.acra.collector.CrashReportDataFactory;
import org.acra.sender.EmailIntentSender;
import org.acra.sender.GoogleFormSender;
import org.acra.sender.HttpPostSender;
import org.acra.sender.ReportSender;
import org.acra.util.PackageManagerWrapper;
import org.acra.util.ToastSender;

public class ErrorReporter
  implements Thread.UncaughtExceptionHandler
{
  private static boolean toastWaitEnded = true;
  private Thread brokenThread;
  private final CrashReportDataFactory crashReportDataFactory;
  private boolean enabled = false;
  private final CrashReportFileNameParser fileNameParser = new CrashReportFileNameParser();
  private final Context mContext;
  private final Thread.UncaughtExceptionHandler mDfltExceptionHandler;
  private final List<ReportSender> mReportSenders = new ArrayList();
  private final SharedPreferences prefs;
  private Throwable unhandledThrowable;
  
  ErrorReporter(Context paramContext, SharedPreferences paramSharedPreferences, boolean paramBoolean)
  {
    this.mContext = paramContext;
    this.prefs = paramSharedPreferences;
    this.enabled = paramBoolean;
    paramContext = ConfigurationCollector.collectConfiguration(this.mContext);
    Time localTime = new Time();
    localTime.setToNow();
    this.crashReportDataFactory = new CrashReportDataFactory(this.mContext, paramSharedPreferences, localTime, paramContext);
    this.mDfltExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
    checkReportsOnApplicationStart();
  }
  
  private boolean containsOnlySilentOrApprovedReports(String[] paramArrayOfString)
  {
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = paramArrayOfString[i];
      if (!this.fileNameParser.isApproved(str)) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private void deletePendingReports(boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    String[] arrayOfString = new CrashReportFinder(this.mContext).getCrashReportFiles();
    Arrays.sort(arrayOfString);
    if (arrayOfString != null)
    {
      int i = 0;
      while (i < arrayOfString.length - paramInt)
      {
        Object localObject = arrayOfString[i];
        boolean bool = this.fileNameParser.isApproved((String)localObject);
        if (((bool) && (paramBoolean1)) || ((!bool) && (paramBoolean2)))
        {
          localObject = new File(this.mContext.getFilesDir(), (String)localObject);
          if (!((File)localObject).delete()) {
            Log.e(ACRA.LOG_TAG, "Could not delete report : " + localObject);
          }
        }
        i += 1;
      }
    }
  }
  
  private void endApplication()
  {
    if ((ACRA.getConfig().mode() == ReportingInteractionMode.SILENT) || ((ACRA.getConfig().mode() == ReportingInteractionMode.TOAST) && (ACRA.getConfig().forceCloseDialogAfterToast())))
    {
      this.mDfltExceptionHandler.uncaughtException(this.brokenThread, this.unhandledThrowable);
      return;
    }
    Log.e(ACRA.LOG_TAG, this.mContext.getPackageName() + " fatal error : " + this.unhandledThrowable.getMessage(), this.unhandledThrowable);
    Process.killProcess(Process.myPid());
    System.exit(10);
  }
  
  public static ErrorReporter getInstance()
  {
    return ACRA.getErrorReporter();
  }
  
  private String getLatestNonSilentReport(String[] paramArrayOfString)
  {
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      int i = paramArrayOfString.length - 1;
      while (i >= 0)
      {
        if (!this.fileNameParser.isSilent(paramArrayOfString[i])) {
          return paramArrayOfString[i];
        }
        i -= 1;
      }
      return paramArrayOfString[(paramArrayOfString.length - 1)];
    }
    return null;
  }
  
  private String getReportFileName(CrashReportData paramCrashReportData)
  {
    Object localObject = new Time();
    ((Time)localObject).setToNow();
    long l = ((Time)localObject).toMillis(false);
    paramCrashReportData = paramCrashReportData.getProperty(ReportField.IS_SILENT);
    localObject = new StringBuilder().append("").append(l);
    if (paramCrashReportData != null) {}
    for (paramCrashReportData = ACRAConstants.SILENT_SUFFIX;; paramCrashReportData = "") {
      return paramCrashReportData + ".stacktrace";
    }
  }
  
  private void handleException(final Throwable paramThrowable, ReportingInteractionMode paramReportingInteractionMode, final boolean paramBoolean1, final boolean paramBoolean2)
  {
    boolean bool2 = true;
    if (!this.enabled) {
      return;
    }
    boolean bool3 = false;
    ReportingInteractionMode localReportingInteractionMode;
    boolean bool1;
    int i;
    label83:
    final String str;
    if (paramReportingInteractionMode == null)
    {
      localReportingInteractionMode = ACRA.getConfig().mode();
      bool1 = bool3;
      paramReportingInteractionMode = paramThrowable;
      if (paramThrowable == null) {
        paramReportingInteractionMode = new Exception("Report requested by developer");
      }
      if ((localReportingInteractionMode != ReportingInteractionMode.TOAST) && ((ACRA.getConfig().resToastText() == 0) || ((localReportingInteractionMode != ReportingInteractionMode.NOTIFICATION) && (localReportingInteractionMode != ReportingInteractionMode.DIALOG)))) {
        break label286;
      }
      i = 1;
      if (i != 0) {
        new Thread()
        {
          public void run()
          {
            Looper.prepare();
            ToastSender.sendToast(ErrorReporter.this.mContext, ACRA.getConfig().resToastText(), 1);
            Looper.loop();
          }
        }.start();
      }
      paramThrowable = this.crashReportDataFactory.createCrashData(paramReportingInteractionMode, paramBoolean1, this.brokenThread);
      str = getReportFileName(paramThrowable);
      saveCrashReportFile(str, paramThrowable);
      paramReportingInteractionMode = null;
      if ((localReportingInteractionMode != ReportingInteractionMode.SILENT) && (localReportingInteractionMode != ReportingInteractionMode.TOAST) && (!this.prefs.getBoolean("acra.alwaysaccept", false))) {
        break label292;
      }
      Log.d(ACRA.LOG_TAG, "About to start ReportSenderWorker from #handleException");
      paramThrowable = startSendingReports(bool1, true);
      label179:
      if (i != 0)
      {
        toastWaitEnded = false;
        new Thread()
        {
          public void run()
          {
            Time localTime2 = new Time();
            Time localTime1 = new Time();
            localTime2.setToNow();
            long l2 = localTime2.toMillis(false);
            long l1 = 0L;
            for (;;)
            {
              if (l1 < 3000L) {
                try
                {
                  Thread.sleep(3000L);
                  localTime1.setToNow();
                  l1 = localTime1.toMillis(false) - l2;
                }
                catch (InterruptedException localInterruptedException)
                {
                  for (;;)
                  {
                    Log.d(ACRA.LOG_TAG, "Interrupted while waiting for Toast to end.", localInterruptedException);
                  }
                }
              }
            }
            ErrorReporter.access$102(true);
          }
        }.start();
      }
      if ((localReportingInteractionMode != ReportingInteractionMode.DIALOG) || (this.prefs.getBoolean("acra.alwaysaccept", false))) {
        break label323;
      }
    }
    label286:
    label292:
    label323:
    for (paramBoolean1 = bool2;; paramBoolean1 = false)
    {
      new Thread()
      {
        public void run()
        {
          Log.d(ACRA.LOG_TAG, "Waiting for Toast + worker...");
          while ((!ErrorReporter.toastWaitEnded) || ((paramThrowable != null) && (paramThrowable.isAlive()))) {
            try
            {
              Thread.sleep(100L);
            }
            catch (InterruptedException localInterruptedException)
            {
              Log.e(ACRA.LOG_TAG, "Error : ", localInterruptedException);
            }
          }
          if (paramBoolean1)
          {
            Log.d(ACRA.LOG_TAG, "About to create DIALOG from #handleException");
            ErrorReporter.this.notifyDialog(str);
          }
          Log.d(ACRA.LOG_TAG, "Wait for Toast + worker ended. Kill Application ? " + paramBoolean2);
          if (paramBoolean2) {
            ErrorReporter.this.endApplication();
          }
        }
      }.start();
      return;
      bool1 = bool3;
      localReportingInteractionMode = paramReportingInteractionMode;
      if (paramReportingInteractionMode != ReportingInteractionMode.SILENT) {
        break;
      }
      bool1 = bool3;
      localReportingInteractionMode = paramReportingInteractionMode;
      if (ACRA.getConfig().mode() == ReportingInteractionMode.SILENT) {
        break;
      }
      bool1 = true;
      localReportingInteractionMode = paramReportingInteractionMode;
      break;
      i = 0;
      break label83;
      paramThrowable = paramReportingInteractionMode;
      if (localReportingInteractionMode != ReportingInteractionMode.NOTIFICATION) {
        break label179;
      }
      Log.d(ACRA.LOG_TAG, "About to send status bar notification from #handleException");
      notifySendReport(str);
      paramThrowable = paramReportingInteractionMode;
      break label179;
    }
  }
  
  private void notifySendReport(String paramString)
  {
    NotificationManager localNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
    Object localObject = ACRA.getConfig();
    Notification localNotification = new Notification(((ReportsCrashes)localObject).resNotifIcon(), this.mContext.getText(((ReportsCrashes)localObject).resNotifTickerText()), System.currentTimeMillis());
    CharSequence localCharSequence = this.mContext.getText(((ReportsCrashes)localObject).resNotifTitle());
    localObject = this.mContext.getText(((ReportsCrashes)localObject).resNotifText());
    Intent localIntent = new Intent(this.mContext, CrashReportDialog.class);
    Log.d(ACRA.LOG_TAG, "Creating Notification for " + paramString);
    localIntent.putExtra("REPORT_FILE_NAME", paramString);
    paramString = PendingIntent.getActivity(this.mContext, 0, localIntent, 134217728);
    localNotification.setLatestEventInfo(this.mContext, localCharSequence, (CharSequence)localObject, paramString);
    localNotificationManager.cancelAll();
    localNotificationManager.notify(666, localNotification);
  }
  
  private void saveCrashReportFile(String paramString, CrashReportData paramCrashReportData)
  {
    try
    {
      Log.d(ACRA.LOG_TAG, "Writing crash report file " + paramString + ".");
      new CrashReportPersister(this.mContext).store(paramCrashReportData, paramString);
      return;
    }
    catch (Exception paramString)
    {
      Log.e(ACRA.LOG_TAG, "An error occurred while writing the report file...", paramString);
    }
  }
  
  @Deprecated
  public void addCustomData(String paramString1, String paramString2)
  {
    this.crashReportDataFactory.putCustomData(paramString1, paramString2);
  }
  
  public void addReportSender(ReportSender paramReportSender)
  {
    this.mReportSenders.add(paramReportSender);
  }
  
  public void checkReportsOnApplicationStart()
  {
    long l = this.prefs.getInt("acra.lastVersionNr", 0);
    Object localObject1 = new PackageManagerWrapper(this.mContext).getPackageInfo();
    int i;
    if ((localObject1 != null) && (((PackageInfo)localObject1).versionCode > l))
    {
      i = 1;
      if (i != 0)
      {
        if (ACRA.getConfig().deleteOldUnsentReportsOnApplicationStart()) {
          deletePendingReports();
        }
        localObject2 = this.prefs.edit();
        ((SharedPreferences.Editor)localObject2).putInt("acra.lastVersionNr", ((PackageInfo)localObject1).versionCode);
        ((SharedPreferences.Editor)localObject2).commit();
      }
      if (((ACRA.getConfig().mode() == ReportingInteractionMode.NOTIFICATION) || (ACRA.getConfig().mode() == ReportingInteractionMode.DIALOG)) && (ACRA.getConfig().deleteUnapprovedReportsOnApplicationStart())) {
        deletePendingNonApprovedReports(true);
      }
      localObject1 = new CrashReportFinder(this.mContext);
      Object localObject2 = ((CrashReportFinder)localObject1).getCrashReportFiles();
      if ((localObject2 != null) && (localObject2.length > 0))
      {
        localObject2 = ACRA.getConfig().mode();
        localObject1 = ((CrashReportFinder)localObject1).getCrashReportFiles();
        boolean bool = containsOnlySilentOrApprovedReports((String[])localObject1);
        if ((localObject2 != ReportingInteractionMode.SILENT) && (localObject2 != ReportingInteractionMode.TOAST) && ((!bool) || ((localObject2 != ReportingInteractionMode.NOTIFICATION) && (localObject2 != ReportingInteractionMode.DIALOG)))) {
          break label261;
        }
        if ((localObject2 == ReportingInteractionMode.TOAST) && (!bool)) {
          ToastSender.sendToast(this.mContext, ACRA.getConfig().resToastText(), 1);
        }
        Log.v(ACRA.LOG_TAG, "About to start ReportSenderWorker from #checkReportOnApplicationStart");
        startSendingReports(false, false);
      }
    }
    label261:
    do
    {
      return;
      i = 0;
      break;
      if (ACRA.getConfig().mode() == ReportingInteractionMode.NOTIFICATION)
      {
        notifySendReport(getLatestNonSilentReport((String[])localObject1));
        return;
      }
    } while (ACRA.getConfig().mode() != ReportingInteractionMode.DIALOG);
    notifyDialog(getLatestNonSilentReport((String[])localObject1));
  }
  
  void deletePendingNonApprovedReports(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      deletePendingReports(false, true, i);
      return;
    }
  }
  
  void deletePendingReports()
  {
    deletePendingReports(true, true, 0);
  }
  
  public String getCustomData(String paramString)
  {
    return this.crashReportDataFactory.getCustomData(paramString);
  }
  
  public void handleException(Throwable paramThrowable)
  {
    handleException(paramThrowable, ACRA.getConfig().mode(), false, false);
  }
  
  public void handleException(Throwable paramThrowable, boolean paramBoolean)
  {
    handleException(paramThrowable, ACRA.getConfig().mode(), false, paramBoolean);
  }
  
  public void handleSilentException(Throwable paramThrowable)
  {
    if (this.enabled)
    {
      handleException(paramThrowable, ReportingInteractionMode.SILENT, true, false);
      Log.d(ACRA.LOG_TAG, "ACRA sent Silent report.");
      return;
    }
    Log.d(ACRA.LOG_TAG, "ACRA is disabled. Silent report not sent.");
  }
  
  void notifyDialog(String paramString)
  {
    Log.d(ACRA.LOG_TAG, "Creating Dialog for " + paramString);
    Intent localIntent = new Intent(this.mContext, CrashReportDialog.class);
    localIntent.putExtra("REPORT_FILE_NAME", paramString);
    localIntent.setFlags(268435456);
    this.mContext.startActivity(localIntent);
  }
  
  public String putCustomData(String paramString1, String paramString2)
  {
    return this.crashReportDataFactory.putCustomData(paramString1, paramString2);
  }
  
  public void removeAllReportSenders()
  {
    this.mReportSenders.clear();
  }
  
  public String removeCustomData(String paramString)
  {
    return this.crashReportDataFactory.removeCustomData(paramString);
  }
  
  public void removeReportSender(ReportSender paramReportSender)
  {
    this.mReportSenders.remove(paramReportSender);
  }
  
  public void removeReportSenders(Class<?> paramClass)
  {
    if (ReportSender.class.isAssignableFrom(paramClass))
    {
      Iterator localIterator = this.mReportSenders.iterator();
      while (localIterator.hasNext())
      {
        ReportSender localReportSender = (ReportSender)localIterator.next();
        if (paramClass.isInstance(localReportSender)) {
          this.mReportSenders.remove(localReportSender);
        }
      }
    }
  }
  
  public void setDefaultReportSenders()
  {
    ACRAConfiguration localACRAConfiguration = ACRA.getConfig();
    Application localApplication = ACRA.getApplication();
    removeAllReportSenders();
    if (!"".equals(localACRAConfiguration.mailTo()))
    {
      Log.w(ACRA.LOG_TAG, localApplication.getPackageName() + " reports will be sent by email (if accepted by user).");
      setReportSender(new EmailIntentSender(localApplication));
    }
    do
    {
      return;
      if (!new PackageManagerWrapper(localApplication).hasPermission("android.permission.INTERNET"))
      {
        Log.e(ACRA.LOG_TAG, localApplication.getPackageName() + " should be granted permission " + "android.permission.INTERNET" + " if you want your crash reports to be sent. If you don't want to add this permission to your application you can also enable sending reports by email. If this is your will then provide your email address in @ReportsCrashes(mailTo=\"your.account@domain.com\"");
        return;
      }
      if ((localACRAConfiguration.formUri() != null) && (!"".equals(localACRAConfiguration.formUri())))
      {
        setReportSender(new HttpPostSender(null));
        return;
      }
    } while ((localACRAConfiguration.formKey() == null) || ("".equals(localACRAConfiguration.formKey().trim())));
    addReportSender(new GoogleFormSender());
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    String str2 = ACRA.LOG_TAG;
    StringBuilder localStringBuilder = new StringBuilder().append("ACRA is ");
    if (paramBoolean) {}
    for (String str1 = "enabled";; str1 = "disabled")
    {
      Log.i(str2, str1 + " for " + this.mContext.getPackageName());
      this.enabled = paramBoolean;
      return;
    }
  }
  
  public void setReportSender(ReportSender paramReportSender)
  {
    removeAllReportSenders();
    addReportSender(paramReportSender);
  }
  
  SendWorker startSendingReports(boolean paramBoolean1, boolean paramBoolean2)
  {
    SendWorker localSendWorker = new SendWorker(this.mContext, this.mReportSenders, paramBoolean1, paramBoolean2);
    localSendWorker.start();
    return localSendWorker;
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    try
    {
      if (!this.enabled)
      {
        if (this.mDfltExceptionHandler != null)
        {
          Log.e(ACRA.LOG_TAG, "ACRA is disabled for " + this.mContext.getPackageName() + " - forwarding uncaught Exception on to default ExceptionHandler");
          this.mDfltExceptionHandler.uncaughtException(paramThread, paramThrowable);
          return;
        }
        Log.e(ACRA.LOG_TAG, "ACRA is disabled for " + this.mContext.getPackageName() + " - no default ExceptionHandler");
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      if (this.mDfltExceptionHandler != null)
      {
        this.mDfltExceptionHandler.uncaughtException(paramThread, paramThrowable);
        return;
        this.brokenThread = paramThread;
        this.unhandledThrowable = paramThrowable;
        Log.e(ACRA.LOG_TAG, "ACRA caught a " + paramThrowable.getClass().getSimpleName() + " exception for " + this.mContext.getPackageName() + ". Building report.");
        handleException(paramThrowable, ACRA.getConfig().mode(), false, true);
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\ErrorReporter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */