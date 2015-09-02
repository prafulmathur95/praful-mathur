package org.acra.util;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;
import org.acra.ACRA;

public class Installation
{
  private static final String INSTALLATION = "ACRA-INSTALLATION";
  private static String sID;
  
  public static String id(Context paramContext)
  {
    try
    {
      File localFile;
      if (sID == null) {
        localFile = new File(paramContext.getFilesDir(), "ACRA-INSTALLATION");
      }
      try
      {
        if (!localFile.exists()) {
          writeInstallationFile(localFile);
        }
        sID = readInstallationFile(localFile);
        paramContext = sID;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          Log.w(ACRA.LOG_TAG, "Couldn't retrieve InstallationId for " + paramContext.getPackageName(), localIOException);
          paramContext = "Couldn't retrieve InstallationId";
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        for (;;)
        {
          Log.w(ACRA.LOG_TAG, "Couldn't retrieve InstallationId for " + paramContext.getPackageName(), localRuntimeException);
          paramContext = "Couldn't retrieve InstallationId";
        }
      }
      return paramContext;
    }
    finally {}
  }
  
  private static String readInstallationFile(File paramFile)
    throws IOException
  {
    paramFile = new RandomAccessFile(paramFile, "r");
    byte[] arrayOfByte = new byte[(int)paramFile.length()];
    try
    {
      paramFile.readFully(arrayOfByte);
      return new String(arrayOfByte);
    }
    finally
    {
      paramFile.close();
    }
  }
  
  private static void writeInstallationFile(File paramFile)
    throws IOException
  {
    paramFile = new FileOutputStream(paramFile);
    try
    {
      paramFile.write(UUID.randomUUID().toString().getBytes());
      return;
    }
    finally
    {
      paramFile.close();
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\util\Installation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */