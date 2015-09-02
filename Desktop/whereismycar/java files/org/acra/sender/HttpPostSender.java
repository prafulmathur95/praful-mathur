package org.acra.sender;

import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.acra.ACRA;
import org.acra.ACRAConfiguration;
import org.acra.ReportField;
import org.acra.collector.CrashReportData;
import org.acra.util.HttpRequest;

public class HttpPostSender
  implements ReportSender
{
  private final Uri mFormUri;
  private final Map<ReportField, String> mMapping;
  
  public HttpPostSender(String paramString, Map<ReportField, String> paramMap)
  {
    this.mFormUri = Uri.parse(paramString);
    this.mMapping = paramMap;
  }
  
  public HttpPostSender(Map<ReportField, String> paramMap)
  {
    this.mFormUri = null;
    this.mMapping = paramMap;
  }
  
  private static boolean isNull(String paramString)
  {
    return (paramString == null) || ("ACRA-NULL-STRING".equals(paramString));
  }
  
  private Map<String, String> remap(Map<ReportField, String> paramMap)
  {
    Object localObject2 = ACRA.getConfig().customReportContent();
    Object localObject1 = localObject2;
    if (localObject2.length == 0) {
      localObject1 = ACRA.DEFAULT_REPORT_FIELDS;
    }
    localObject2 = new HashMap(paramMap.size());
    int j = localObject1.length;
    int i = 0;
    if (i < j)
    {
      Object localObject3 = localObject1[i];
      if ((this.mMapping == null) || (this.mMapping.get(localObject3) == null)) {
        ((Map)localObject2).put(((ReportField)localObject3).toString(), paramMap.get(localObject3));
      }
      for (;;)
      {
        i += 1;
        break;
        ((Map)localObject2).put(this.mMapping.get(localObject3), paramMap.get(localObject3));
      }
    }
    return (Map<String, String>)localObject2;
  }
  
  public void send(CrashReportData paramCrashReportData)
    throws ReportSenderException
  {
    String str2 = null;
    for (;;)
    {
      try
      {
        Map localMap = remap(paramCrashReportData);
        if (this.mFormUri == null)
        {
          paramCrashReportData = new URL(ACRA.getConfig().formUri());
          Log.d(ACRA.LOG_TAG, "Connect to " + paramCrashReportData.toString());
          if (isNull(ACRA.getConfig().formUriBasicAuthLogin()))
          {
            str1 = null;
            if (!isNull(ACRA.getConfig().formUriBasicAuthPassword())) {
              break label188;
            }
            HttpRequest localHttpRequest = new HttpRequest();
            localHttpRequest.setConnectionTimeOut(ACRA.getConfig().connectionTimeout());
            localHttpRequest.setSocketTimeOut(ACRA.getConfig().socketTimeout());
            localHttpRequest.setMaxNrRetries(ACRA.getConfig().maxNumberOfRequestRetries());
            localHttpRequest.setLogin(str1);
            localHttpRequest.setPassword(str2);
            localHttpRequest.sendPost(paramCrashReportData, localMap);
          }
        }
        else
        {
          paramCrashReportData = new URL(this.mFormUri.toString());
          continue;
        }
        String str1 = ACRA.getConfig().formUriBasicAuthLogin();
      }
      catch (IOException paramCrashReportData)
      {
        throw new ReportSenderException("Error while sending report to Http Post Form.", paramCrashReportData);
      }
      continue;
      label188:
      str2 = ACRA.getConfig().formUriBasicAuthPassword();
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\sender\HttpPostSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */