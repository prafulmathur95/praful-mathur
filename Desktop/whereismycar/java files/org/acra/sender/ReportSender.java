package org.acra.sender;

import org.acra.collector.CrashReportData;

public abstract interface ReportSender
{
  public abstract void send(CrashReportData paramCrashReportData)
    throws ReportSenderException;
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\sender\ReportSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */