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

public class GoogleFormSender
  implements ReportSender
{
  private final Uri mFormUri;
  
  public GoogleFormSender()
  {
    this.mFormUri = null;
  }
  
  public GoogleFormSender(String paramString)
  {
    this.mFormUri = Uri.parse(String.format(ACRA.getConfig().googleFormUrlFormat(), new Object[] { paramString }));
  }
  
  private Map<String, String> remap(Map<ReportField, String> paramMap)
  {
    Object localObject2 = ACRA.getConfig().customReportContent();
    Object localObject1 = localObject2;
    if (localObject2.length == 0) {
      localObject1 = ACRA.DEFAULT_REPORT_FIELDS;
    }
    int j = 0;
    localObject2 = new HashMap();
    int k = localObject1.length;
    int i = 0;
    if (i < k)
    {
      Object localObject3 = localObject1[i];
      switch (localObject3)
      {
      default: 
        ((Map)localObject2).put("entry." + j + ".single", paramMap.get(localObject3));
      }
      for (;;)
      {
        j += 1;
        i += 1;
        break;
        ((Map)localObject2).put("entry." + j + ".single", "'" + (String)paramMap.get(localObject3));
        continue;
        ((Map)localObject2).put("entry." + j + ".single", "'" + (String)paramMap.get(localObject3));
      }
    }
    return (Map<String, String>)localObject2;
  }
  
  public void send(CrashReportData paramCrashReportData)
    throws ReportSenderException
  {
    if (this.mFormUri == null) {}
    for (Object localObject = Uri.parse(String.format(ACRA.getConfig().googleFormUrlFormat(), new Object[] { ACRA.getConfig().formKey() }));; localObject = this.mFormUri)
    {
      Map localMap = remap(paramCrashReportData);
      localMap.put("pageNumber", "0");
      localMap.put("backupCache", "");
      localMap.put("submit", "Envoyer");
      try
      {
        localObject = new URL(((Uri)localObject).toString());
        Log.d(ACRA.LOG_TAG, "Sending report " + (String)paramCrashReportData.get(ReportField.REPORT_ID));
        Log.d(ACRA.LOG_TAG, "Connect to " + localObject);
        paramCrashReportData = new HttpRequest();
        paramCrashReportData.setConnectionTimeOut(ACRA.getConfig().connectionTimeout());
        paramCrashReportData.setSocketTimeOut(ACRA.getConfig().socketTimeout());
        paramCrashReportData.setMaxNrRetries(ACRA.getConfig().maxNumberOfRequestRetries());
        paramCrashReportData.sendPost((URL)localObject, localMap);
        return;
      }
      catch (IOException paramCrashReportData)
      {
        throw new ReportSenderException("Error while sending report to Google Form.", paramCrashReportData);
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\sender\GoogleFormSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */