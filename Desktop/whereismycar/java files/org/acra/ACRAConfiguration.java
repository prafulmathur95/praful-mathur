package org.acra;

import java.lang.annotation.Annotation;
import org.acra.annotation.ReportsCrashes;

public class ACRAConfiguration
  implements ReportsCrashes
{
  private String[] mAdditionalDropboxTags = null;
  private String[] mAdditionalSharedPreferences = null;
  private String mApplicationLogFile = null;
  private Integer mApplicationLogFileLines = null;
  private Integer mConnectionTimeout = null;
  private ReportField[] mCustomReportContent = null;
  private Boolean mDeleteOldUnsentReportsOnApplicationStart = null;
  private Boolean mDeleteUnapprovedReportsOnApplicationStart = null;
  private Boolean mDisableSSLCertValidation = null;
  private Integer mDropboxCollectionMinutes = null;
  private String[] mExcludeMatchingSharedPreferencesKeys = null;
  private Boolean mForceCloseDialogAfterToast = null;
  private String mFormKey = null;
  private String mFormUri = null;
  private String mFormUriBasicAuthLogin = null;
  private String mFormUriBasicAuthPassword = null;
  private String mGoogleFormUrlFormat = null;
  private Boolean mIncludeDropboxSystemTags = null;
  private String[] mLogcatArguments = null;
  private Boolean mLogcatFilterByPid = null;
  private String mMailTo = null;
  private Integer mMaxNumberOfRequestRetries = null;
  private ReportingInteractionMode mMode = null;
  private ReportsCrashes mReportsCrashes = null;
  private Integer mResDialogCommentPrompt = null;
  private Integer mResDialogEmailPrompt = null;
  private Integer mResDialogIcon = null;
  private Integer mResDialogOkToast = null;
  private Integer mResDialogText = null;
  private Integer mResDialogTitle = null;
  private Integer mResNotifIcon = null;
  private Integer mResNotifText = null;
  private Integer mResNotifTickerText = null;
  private Integer mResNotifTitle = null;
  private Integer mResToastText = null;
  private Boolean mSendReportsInDevMode = null;
  private Integer mSharedPreferenceMode = null;
  private String mSharedPreferenceName = null;
  private Integer mSocketTimeout = null;
  
  public ACRAConfiguration(ReportsCrashes paramReportsCrashes)
  {
    this.mReportsCrashes = paramReportsCrashes;
  }
  
  public String[] additionalDropBoxTags()
  {
    if (this.mAdditionalDropboxTags != null) {
      return this.mAdditionalDropboxTags;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.additionalDropBoxTags();
    }
    return new String[0];
  }
  
  public String[] additionalSharedPreferences()
  {
    if (this.mAdditionalSharedPreferences != null) {
      return this.mAdditionalSharedPreferences;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.additionalSharedPreferences();
    }
    return new String[0];
  }
  
  public Class<? extends Annotation> annotationType()
  {
    return this.mReportsCrashes.annotationType();
  }
  
  public String applicationLogFile()
  {
    if (this.mApplicationLogFile != null) {
      return this.mApplicationLogFile;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.applicationLogFile();
    }
    return "";
  }
  
  public int applicationLogFileLines()
  {
    if (this.mApplicationLogFileLines != null) {
      return this.mApplicationLogFileLines.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.applicationLogFileLines();
    }
    return 100;
  }
  
  public int connectionTimeout()
  {
    if (this.mConnectionTimeout != null) {
      return this.mConnectionTimeout.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.connectionTimeout();
    }
    return 3000;
  }
  
  public ReportField[] customReportContent()
  {
    if (this.mCustomReportContent != null) {
      return this.mCustomReportContent;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.customReportContent();
    }
    return new ReportField[0];
  }
  
  public boolean deleteOldUnsentReportsOnApplicationStart()
  {
    if (this.mDeleteOldUnsentReportsOnApplicationStart != null) {
      return this.mDeleteOldUnsentReportsOnApplicationStart.booleanValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.deleteOldUnsentReportsOnApplicationStart();
    }
    return true;
  }
  
  public boolean deleteUnapprovedReportsOnApplicationStart()
  {
    if (this.mDeleteUnapprovedReportsOnApplicationStart != null) {
      return this.mDeleteUnapprovedReportsOnApplicationStart.booleanValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.deleteUnapprovedReportsOnApplicationStart();
    }
    return true;
  }
  
  public boolean disableSSLCertValidation()
  {
    if (this.mDisableSSLCertValidation != null) {
      return this.mDisableSSLCertValidation.booleanValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.disableSSLCertValidation();
    }
    return false;
  }
  
  public int dropboxCollectionMinutes()
  {
    if (this.mDropboxCollectionMinutes != null) {
      return this.mDropboxCollectionMinutes.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.dropboxCollectionMinutes();
    }
    return 5;
  }
  
  public String[] excludeMatchingSharedPreferencesKeys()
  {
    if (this.mExcludeMatchingSharedPreferencesKeys != null) {
      return this.mExcludeMatchingSharedPreferencesKeys;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.excludeMatchingSharedPreferencesKeys();
    }
    return new String[0];
  }
  
  public boolean forceCloseDialogAfterToast()
  {
    if (this.mForceCloseDialogAfterToast != null) {
      return this.mForceCloseDialogAfterToast.booleanValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.forceCloseDialogAfterToast();
    }
    return false;
  }
  
  public String formKey()
  {
    if (this.mFormKey != null) {
      return this.mFormKey;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.formKey();
    }
    return "";
  }
  
  public String formUri()
  {
    if (this.mFormUri != null) {
      return this.mFormUri;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.formUri();
    }
    return "";
  }
  
  public String formUriBasicAuthLogin()
  {
    if (this.mFormUriBasicAuthLogin != null) {
      return this.mFormUriBasicAuthLogin;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.formUriBasicAuthLogin();
    }
    return "ACRA-NULL-STRING";
  }
  
  public String formUriBasicAuthPassword()
  {
    if (this.mFormUriBasicAuthPassword != null) {
      return this.mFormUriBasicAuthPassword;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.formUriBasicAuthPassword();
    }
    return "ACRA-NULL-STRING";
  }
  
  public String googleFormUrlFormat()
  {
    if (this.mGoogleFormUrlFormat != null) {
      return this.mGoogleFormUrlFormat;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.googleFormUrlFormat();
    }
    return "https://docs.google.com/spreadsheet/formResponse?formkey=%s&ifq";
  }
  
  public boolean includeDropBoxSystemTags()
  {
    if (this.mIncludeDropboxSystemTags != null) {
      return this.mIncludeDropboxSystemTags.booleanValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.includeDropBoxSystemTags();
    }
    return false;
  }
  
  public String[] logcatArguments()
  {
    if (this.mLogcatArguments != null) {
      return this.mLogcatArguments;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.logcatArguments();
    }
    return new String[] { "-t", Integer.toString(100), "-v", "time" };
  }
  
  public boolean logcatFilterByPid()
  {
    if (this.mLogcatFilterByPid != null) {
      return this.mLogcatFilterByPid.booleanValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.logcatFilterByPid();
    }
    return false;
  }
  
  public String mailTo()
  {
    if (this.mMailTo != null) {
      return this.mMailTo;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.mailTo();
    }
    return "";
  }
  
  public int maxNumberOfRequestRetries()
  {
    if (this.mMaxNumberOfRequestRetries != null) {
      return this.mMaxNumberOfRequestRetries.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.maxNumberOfRequestRetries();
    }
    return 3;
  }
  
  public ReportingInteractionMode mode()
  {
    if (this.mMode != null) {
      return this.mMode;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.mode();
    }
    return ReportingInteractionMode.SILENT;
  }
  
  public int resDialogCommentPrompt()
  {
    if (this.mResDialogCommentPrompt != null) {
      return this.mResDialogCommentPrompt.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.resDialogCommentPrompt();
    }
    return 0;
  }
  
  public int resDialogEmailPrompt()
  {
    if (this.mResDialogEmailPrompt != null) {
      return this.mResDialogEmailPrompt.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.resDialogEmailPrompt();
    }
    return 0;
  }
  
  public int resDialogIcon()
  {
    if (this.mResDialogIcon != null) {
      return this.mResDialogIcon.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.resDialogIcon();
    }
    return 17301543;
  }
  
  public int resDialogOkToast()
  {
    if (this.mResDialogOkToast != null) {
      return this.mResDialogOkToast.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.resDialogOkToast();
    }
    return 0;
  }
  
  public int resDialogText()
  {
    if (this.mResDialogText != null) {
      return this.mResDialogText.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.resDialogText();
    }
    return 0;
  }
  
  public int resDialogTitle()
  {
    if (this.mResDialogTitle != null) {
      return this.mResDialogTitle.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.resDialogTitle();
    }
    return 0;
  }
  
  public int resNotifIcon()
  {
    if (this.mResNotifIcon != null) {
      return this.mResNotifIcon.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.resNotifIcon();
    }
    return 17301624;
  }
  
  public int resNotifText()
  {
    if (this.mResNotifText != null) {
      return this.mResNotifText.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.resNotifText();
    }
    return 0;
  }
  
  public int resNotifTickerText()
  {
    if (this.mResNotifTickerText != null) {
      return this.mResNotifTickerText.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.resNotifTickerText();
    }
    return 0;
  }
  
  public int resNotifTitle()
  {
    if (this.mResNotifTitle != null) {
      return this.mResNotifTitle.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.resNotifTitle();
    }
    return 0;
  }
  
  public int resToastText()
  {
    if (this.mResToastText != null) {
      return this.mResToastText.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.resToastText();
    }
    return 0;
  }
  
  public boolean sendReportsInDevMode()
  {
    if (this.mSendReportsInDevMode != null) {
      return this.mSendReportsInDevMode.booleanValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.sendReportsInDevMode();
    }
    return true;
  }
  
  public void setAdditionalDropboxTags(String[] paramArrayOfString)
  {
    this.mAdditionalDropboxTags = paramArrayOfString;
  }
  
  public void setAdditionalSharedPreferences(String[] paramArrayOfString)
  {
    this.mAdditionalSharedPreferences = paramArrayOfString;
  }
  
  public void setApplicationLogFile(String paramString)
  {
    this.mApplicationLogFile = paramString;
  }
  
  public void setApplicationLogFileLines(int paramInt)
  {
    this.mApplicationLogFileLines = Integer.valueOf(paramInt);
  }
  
  public void setConnectionTimeout(Integer paramInteger)
  {
    this.mConnectionTimeout = paramInteger;
  }
  
  public void setCustomReportContent(ReportField[] paramArrayOfReportField)
  {
    this.mCustomReportContent = paramArrayOfReportField;
  }
  
  public void setDeleteOldUnsentReportsOnApplicationStart(Boolean paramBoolean)
  {
    this.mDeleteOldUnsentReportsOnApplicationStart = paramBoolean;
  }
  
  public void setDeleteUnapprovedReportsOnApplicationStart(Boolean paramBoolean)
  {
    this.mDeleteUnapprovedReportsOnApplicationStart = paramBoolean;
  }
  
  public void setDisableSSLCertValidation(boolean paramBoolean)
  {
    this.mDisableSSLCertValidation = Boolean.valueOf(paramBoolean);
  }
  
  public void setDropboxCollectionMinutes(Integer paramInteger)
  {
    this.mDropboxCollectionMinutes = paramInteger;
  }
  
  public void setExcludeMatchingSharedPreferencesKeys(String[] paramArrayOfString)
  {
    this.mExcludeMatchingSharedPreferencesKeys = paramArrayOfString;
  }
  
  public void setForceCloseDialogAfterToast(Boolean paramBoolean)
  {
    this.mForceCloseDialogAfterToast = paramBoolean;
  }
  
  public void setFormKey(String paramString)
  {
    this.mFormKey = paramString;
  }
  
  public void setFormUri(String paramString)
  {
    this.mFormUri = paramString;
  }
  
  public void setFormUriBasicAuthLogin(String paramString)
  {
    this.mFormUriBasicAuthLogin = paramString;
  }
  
  public void setFormUriBasicAuthPassword(String paramString)
  {
    this.mFormUriBasicAuthPassword = paramString;
  }
  
  public void setIncludeDropboxSystemTags(Boolean paramBoolean)
  {
    this.mIncludeDropboxSystemTags = paramBoolean;
  }
  
  public void setLogcatArguments(String[] paramArrayOfString)
  {
    this.mLogcatArguments = paramArrayOfString;
  }
  
  public void setLogcatFilterByPid(Boolean paramBoolean)
  {
    this.mLogcatFilterByPid = paramBoolean;
  }
  
  public void setMailTo(String paramString)
  {
    this.mMailTo = paramString;
  }
  
  public void setMaxNumberOfRequestRetries(Integer paramInteger)
  {
    this.mMaxNumberOfRequestRetries = paramInteger;
  }
  
  public void setMode(ReportingInteractionMode paramReportingInteractionMode)
    throws ACRAConfigurationException
  {
    this.mMode = paramReportingInteractionMode;
    ACRA.checkCrashResources();
  }
  
  public void setResDialogCommentPrompt(int paramInt)
  {
    this.mResDialogCommentPrompt = Integer.valueOf(paramInt);
  }
  
  public void setResDialogEmailPrompt(int paramInt)
  {
    this.mResDialogEmailPrompt = Integer.valueOf(paramInt);
  }
  
  public void setResDialogIcon(int paramInt)
  {
    this.mResDialogIcon = Integer.valueOf(paramInt);
  }
  
  public void setResDialogOkToast(int paramInt)
  {
    this.mResDialogOkToast = Integer.valueOf(paramInt);
  }
  
  public void setResDialogText(int paramInt)
  {
    this.mResDialogText = Integer.valueOf(paramInt);
  }
  
  public void setResDialogTitle(int paramInt)
  {
    this.mResDialogTitle = Integer.valueOf(paramInt);
  }
  
  public void setResNotifIcon(int paramInt)
  {
    this.mResNotifIcon = Integer.valueOf(paramInt);
  }
  
  public void setResNotifText(int paramInt)
  {
    this.mResNotifText = Integer.valueOf(paramInt);
  }
  
  public void setResNotifTickerText(int paramInt)
  {
    this.mResNotifTickerText = Integer.valueOf(paramInt);
  }
  
  public void setResNotifTitle(int paramInt)
  {
    this.mResNotifTitle = Integer.valueOf(paramInt);
  }
  
  public void setResToastText(int paramInt)
  {
    this.mResToastText = Integer.valueOf(paramInt);
  }
  
  public void setSendReportsInDevMode(Boolean paramBoolean)
  {
    this.mSendReportsInDevMode = paramBoolean;
  }
  
  public void setSharedPreferenceMode(Integer paramInteger)
  {
    this.mSharedPreferenceMode = paramInteger;
  }
  
  public void setSharedPreferenceName(String paramString)
  {
    this.mSharedPreferenceName = paramString;
  }
  
  public void setSocketTimeout(Integer paramInteger)
  {
    this.mSocketTimeout = paramInteger;
  }
  
  public int sharedPreferencesMode()
  {
    if (this.mSharedPreferenceMode != null) {
      return this.mSharedPreferenceMode.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.sharedPreferencesMode();
    }
    return 0;
  }
  
  public String sharedPreferencesName()
  {
    if (this.mSharedPreferenceName != null) {
      return this.mSharedPreferenceName;
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.sharedPreferencesName();
    }
    return "";
  }
  
  public int socketTimeout()
  {
    if (this.mSocketTimeout != null) {
      return this.mSocketTimeout.intValue();
    }
    if (this.mReportsCrashes != null) {
      return this.mReportsCrashes.socketTimeout();
    }
    return 5000;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\ACRAConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */