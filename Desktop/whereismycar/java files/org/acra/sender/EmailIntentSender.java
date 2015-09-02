package org.acra.sender;

import android.content.Context;
import android.content.Intent;
import org.acra.ACRA;
import org.acra.ACRAConfiguration;
import org.acra.ReportField;
import org.acra.collector.CrashReportData;

public class EmailIntentSender
  implements ReportSender
{
  private final Context mContext;
  
  public EmailIntentSender(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  private String buildBody(CrashReportData paramCrashReportData)
  {
    Object localObject2 = ACRA.getConfig().customReportContent();
    Object localObject1 = localObject2;
    if (localObject2.length == 0) {
      localObject1 = ACRA.DEFAULT_MAIL_REPORT_FIELDS;
    }
    localObject2 = new StringBuilder();
    int j = localObject1.length;
    int i = 0;
    while (i < j)
    {
      Object localObject3 = localObject1[i];
      ((StringBuilder)localObject2).append(((ReportField)localObject3).toString()).append("=");
      ((StringBuilder)localObject2).append((String)paramCrashReportData.get(localObject3));
      ((StringBuilder)localObject2).append('\n');
      i += 1;
    }
    return ((StringBuilder)localObject2).toString();
  }
  
  public void send(CrashReportData paramCrashReportData)
    throws ReportSenderException
  {
    String str = this.mContext.getPackageName() + " Crash Report";
    paramCrashReportData = buildBody(paramCrashReportData);
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.addFlags(268435456);
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.SUBJECT", str);
    localIntent.putExtra("android.intent.extra.TEXT", paramCrashReportData);
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { ACRA.getConfig().mailTo() });
    this.mContext.startActivity(localIntent);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\sender\EmailIntentSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */