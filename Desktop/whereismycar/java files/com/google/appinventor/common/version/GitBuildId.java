package com.google.appinventor.common.version;

public final class GitBuildId
{
  public static final String ACRA_URI = "${acra.uri}";
  public static final String ANT_BUILD_DATE = "June 30 2015";
  public static final String GIT_BUILD_FINGERPRINT = "c00b90eb65a991294b591ca582991bdbeb7b8b6c";
  public static final String GIT_BUILD_VERSION = "nb144";
  
  public static String getAcraUri()
  {
    if ("${acra.uri}".equals("${acra.uri}")) {
      return "";
    }
    return "${acra.uri}".trim();
  }
  
  public static String getDate()
  {
    return "June 30 2015";
  }
  
  public static String getFingerprint()
  {
    return "c00b90eb65a991294b591ca582991bdbeb7b8b6c";
  }
  
  public static String getVersion()
  {
    String str = "nb144";
    if (("nb144" == "") || ("nb144".contains(" "))) {
      str = "none";
    }
    return str;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\common\version\GitBuildId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */