package org.acra;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;
import org.acra.annotation.ReportsCrashes;
import org.acra.log.ACRALog;
import org.acra.log.AndroidLogDelegate;

public class ACRA
{
  public static final ReportField[] DEFAULT_MAIL_REPORT_FIELDS = { ReportField.USER_COMMENT, ReportField.ANDROID_VERSION, ReportField.APP_VERSION_NAME, ReportField.BRAND, ReportField.PHONE_MODEL, ReportField.CUSTOM_DATA, ReportField.STACK_TRACE };
  public static final ReportField[] DEFAULT_REPORT_FIELDS = { ReportField.REPORT_ID, ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.PACKAGE_NAME, ReportField.FILE_PATH, ReportField.PHONE_MODEL, ReportField.BRAND, ReportField.PRODUCT, ReportField.ANDROID_VERSION, ReportField.BUILD, ReportField.TOTAL_MEM_SIZE, ReportField.AVAILABLE_MEM_SIZE, ReportField.CUSTOM_DATA, ReportField.IS_SILENT, ReportField.STACK_TRACE, ReportField.INITIAL_CONFIGURATION, ReportField.CRASH_CONFIGURATION, ReportField.DISPLAY, ReportField.USER_COMMENT, ReportField.USER_EMAIL, ReportField.USER_APP_START_DATE, ReportField.USER_CRASH_DATE, ReportField.DUMPSYS_MEMINFO, ReportField.LOGCAT, ReportField.INSTALLATION_ID, ReportField.DEVICE_FEATURES, ReportField.ENVIRONMENT, ReportField.SHARED_PREFERENCES, ReportField.SETTINGS_SYSTEM, ReportField.SETTINGS_SECURE };
  public static final boolean DEV_LOGGING = false;
  public static final String LOG_TAG = ACRA.class.getSimpleName();
  public static final String PREF_ALWAYS_ACCEPT = "acra.alwaysaccept";
  public static final String PREF_DISABLE_ACRA = "acra.disable";
  public static final String PREF_ENABLE_ACRA = "acra.enable";
  public static final String PREF_ENABLE_DEVICE_ID = "acra.deviceid.enable";
  public static final String PREF_ENABLE_SYSTEM_LOGS = "acra.syslog.enable";
  public static final String PREF_LAST_VERSION_NR = "acra.lastVersionNr";
  public static final String PREF_USER_EMAIL_ADDRESS = "acra.user.email";
  private static ACRAConfiguration configProxy;
  private static ErrorReporter errorReporterSingleton;
  public static ACRALog log = new AndroidLogDelegate();
  private static Application mApplication;
  private static SharedPreferences.OnSharedPreferenceChangeListener mPrefListener;
  private static ReportsCrashes mReportsCrashes;
  
  static void checkCrashResources()
    throws ACRAConfigurationException
  {
    ACRAConfiguration localACRAConfiguration = getConfig();
    switch (localACRAConfiguration.mode())
    {
    }
    do
    {
      do
      {
        do
        {
          return;
        } while (localACRAConfiguration.resToastText() != 0);
        throw new ACRAConfigurationException("TOAST mode: you have to define the resToastText parameter in your application @ReportsCrashes() annotation.");
      } while ((localACRAConfiguration.resNotifTickerText() != 0) && (localACRAConfiguration.resNotifTitle() != 0) && (localACRAConfiguration.resNotifText() != 0) && (localACRAConfiguration.resDialogText() != 0));
      throw new ACRAConfigurationException("NOTIFICATION mode: you have to define at least the resNotifTickerText, resNotifTitle, resNotifText, resDialogText parameters in your application @ReportsCrashes() annotation.");
    } while (localACRAConfiguration.resDialogText() != 0);
    throw new ACRAConfigurationException("DIALOG mode: you have to define at least the resDialogText parameters in your application @ReportsCrashes() annotation.");
  }
  
  public static SharedPreferences getACRASharedPreferences()
  {
    ACRAConfiguration localACRAConfiguration = getConfig();
    if (!"".equals(localACRAConfiguration.sharedPreferencesName())) {
      return mApplication.getSharedPreferences(localACRAConfiguration.sharedPreferencesName(), localACRAConfiguration.sharedPreferencesMode());
    }
    return PreferenceManager.getDefaultSharedPreferences(mApplication);
  }
  
  static Application getApplication()
  {
    return mApplication;
  }
  
  public static ACRAConfiguration getConfig()
  {
    if (configProxy == null)
    {
      if (mApplication == null) {
        log.w(LOG_TAG, "Calling ACRA.getConfig() before ACRA.init() gives you an empty configuration instance. You might prefer calling ACRA.getNewDefaultConfig(Application) to get an instance with default values taken from a @ReportsCrashes annotation.");
      }
      configProxy = getNewDefaultConfig(mApplication);
    }
    return configProxy;
  }
  
  public static ErrorReporter getErrorReporter()
  {
    if (errorReporterSingleton == null) {
      throw new IllegalStateException("Cannot access ErrorReporter before ACRA#init");
    }
    return errorReporterSingleton;
  }
  
  public static ACRAConfiguration getNewDefaultConfig(Application paramApplication)
  {
    if (paramApplication != null) {
      return new ACRAConfiguration((ReportsCrashes)paramApplication.getClass().getAnnotation(ReportsCrashes.class));
    }
    return new ACRAConfiguration(null);
  }
  
  public static void init(Application paramApplication)
  {
    if (mApplication != null) {
      throw new IllegalStateException("ACRA#init called more than once");
    }
    mApplication = paramApplication;
    mReportsCrashes = (ReportsCrashes)mApplication.getClass().getAnnotation(ReportsCrashes.class);
    if (mReportsCrashes == null)
    {
      log.e(LOG_TAG, "ACRA#init called but no ReportsCrashes annotation on Application " + mApplication.getPackageName());
      return;
    }
    paramApplication = getACRASharedPreferences();
    for (;;)
    {
      try
      {
        checkCrashResources();
        log.d(LOG_TAG, "ACRA is enabled for " + mApplication.getPackageName() + ", intializing...");
        if (shouldDisableACRA(paramApplication)) {
          continue;
        }
        bool = true;
        ErrorReporter localErrorReporter = new ErrorReporter(mApplication.getApplicationContext(), paramApplication, bool);
        localErrorReporter.setDefaultReportSenders();
        errorReporterSingleton = localErrorReporter;
      }
      catch (ACRAConfigurationException localACRAConfigurationException)
      {
        boolean bool;
        log.w(LOG_TAG, "Error : ", localACRAConfigurationException);
        continue;
      }
      mPrefListener = new SharedPreferences.OnSharedPreferenceChangeListener()
      {
        public void onSharedPreferenceChanged(SharedPreferences paramAnonymousSharedPreferences, String paramAnonymousString)
        {
          if (("acra.disable".equals(paramAnonymousString)) || ("acra.enable".equals(paramAnonymousString))) {
            if (ACRA.shouldDisableACRA(paramAnonymousSharedPreferences)) {
              break label35;
            }
          }
          label35:
          for (boolean bool = true;; bool = false)
          {
            ACRA.getErrorReporter().setEnabled(bool);
            return;
          }
        }
      };
      paramApplication.registerOnSharedPreferenceChangeListener(mPrefListener);
      return;
      bool = false;
    }
  }
  
  static boolean isDebuggable()
  {
    boolean bool = false;
    PackageManager localPackageManager = mApplication.getPackageManager();
    try
    {
      int i = localPackageManager.getApplicationInfo(mApplication.getPackageName(), 0).flags;
      if ((i & 0x2) > 0) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return false;
  }
  
  public static void setConfig(ACRAConfiguration paramACRAConfiguration)
  {
    configProxy = paramACRAConfiguration;
  }
  
  public static void setLog(ACRALog paramACRALog)
  {
    log = paramACRALog;
  }
  
  private static boolean shouldDisableACRA(SharedPreferences paramSharedPreferences)
  {
    boolean bool = true;
    try
    {
      if (!paramSharedPreferences.getBoolean("acra.enable", true)) {}
      for (;;)
      {
        bool = paramSharedPreferences.getBoolean("acra.disable", bool);
        return bool;
        bool = false;
      }
      return false;
    }
    catch (Exception paramSharedPreferences) {}
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\ACRA.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */