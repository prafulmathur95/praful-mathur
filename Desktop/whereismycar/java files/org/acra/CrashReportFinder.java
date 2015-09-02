package org.acra;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FilenameFilter;

final class CrashReportFinder
{
  private final Context context;
  
  public CrashReportFinder(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public String[] getCrashReportFiles()
  {
    Object localObject;
    if (this.context == null)
    {
      Log.e(ACRA.LOG_TAG, "Trying to get ACRA reports but ACRA is not initialized.");
      localObject = new String[0];
    }
    String[] arrayOfString;
    do
    {
      return (String[])localObject;
      localObject = this.context.getFilesDir();
      if (localObject == null)
      {
        Log.w(ACRA.LOG_TAG, "Application files directory does not exist! The application may not be installed correctly. Please try reinstalling.");
        return new String[0];
      }
      Log.d(ACRA.LOG_TAG, "Looking for error files in " + ((File)localObject).getAbsolutePath());
      arrayOfString = ((File)localObject).list(new FilenameFilter()
      {
        public boolean accept(File paramAnonymousFile, String paramAnonymousString)
        {
          return paramAnonymousString.endsWith(".stacktrace");
        }
      });
      localObject = arrayOfString;
    } while (arrayOfString != null);
    return new String[0];
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\CrashReportFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */