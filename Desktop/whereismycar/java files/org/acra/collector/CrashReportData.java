package org.acra.collector;

import java.util.EnumMap;
import org.acra.ReportField;

public final class CrashReportData
  extends EnumMap<ReportField, String>
{
  private static final long serialVersionUID = 4112578634029874840L;
  
  public CrashReportData()
  {
    super(ReportField.class);
  }
  
  public String getProperty(ReportField paramReportField)
  {
    return (String)super.get(paramReportField);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\collector\CrashReportData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */