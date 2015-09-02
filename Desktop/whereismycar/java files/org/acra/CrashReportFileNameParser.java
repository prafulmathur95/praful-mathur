package org.acra;

final class CrashReportFileNameParser
{
  public boolean isApproved(String paramString)
  {
    return (isSilent(paramString)) || (paramString.contains("-approved"));
  }
  
  public boolean isSilent(String paramString)
  {
    return paramString.contains(ACRAConstants.SILENT_SUFFIX);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\CrashReportFileNameParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */