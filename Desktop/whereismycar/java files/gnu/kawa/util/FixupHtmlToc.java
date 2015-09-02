package gnu.kawa.util;

import java.io.File;
import java.io.PrintStream;

public class FixupHtmlToc
{
  static FileInfo[] argFiles;
  
  public static void main(String[] paramArrayOfString)
  {
    for (;;)
    {
      try
      {
        argFiles = new FileInfo[paramArrayOfString.length];
        i = 0;
        if (i < paramArrayOfString.length)
        {
          FileInfo localFileInfo = FileInfo.find(new File(paramArrayOfString[i]));
          localFileInfo.writeNeeded = true;
          argFiles[i] = localFileInfo;
          i += 1;
          continue;
          if (i < paramArrayOfString.length)
          {
            argFiles[i].scan();
            argFiles[i].write();
            i += 1;
            continue;
          }
          return;
        }
      }
      catch (Throwable paramArrayOfString)
      {
        System.err.println("caught " + paramArrayOfString);
        paramArrayOfString.printStackTrace();
      }
      int i = 0;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\util\FixupHtmlToc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */