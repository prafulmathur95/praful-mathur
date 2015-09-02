package org.acra.collector;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.acra.util.BoundedLinkedList;

class LogFileCollector
{
  public static String collectLogFile(Context paramContext, String paramString, int paramInt)
    throws IOException
  {
    BoundedLinkedList localBoundedLinkedList = new BoundedLinkedList(paramInt);
    if (paramString.contains("/")) {}
    for (paramContext = new BufferedReader(new InputStreamReader(new FileInputStream(paramString)), 1024);; paramContext = new BufferedReader(new InputStreamReader(paramContext.openFileInput(paramString)), 1024)) {
      for (paramString = paramContext.readLine(); paramString != null; paramString = paramContext.readLine()) {
        localBoundedLinkedList.add(paramString + "\n");
      }
    }
    return localBoundedLinkedList.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\collector\LogFileCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */