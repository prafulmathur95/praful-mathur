package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.FileUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

@DesignerComponent(category=ComponentCategory.STORAGE, description="Non-visible component for storing and retrieving files. Use this component to write or read files on your device. The default behaviour is to write files to the private data directory associated with your App. The Companion is special cased to write files to /sdcard/AppInventor/data to facilitate debugging. If the file path starts with a slash (/), then the file is created relative to /sdcard. For example writing a file to /myFile.txt will write the file in /sdcard/myFile.txt.", iconName="images/file.png", nonVisible=true, version=1)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.WRITE_EXTERNAL_STORAGE, android.permission.READ_EXTERNAL_STORAGE")
public class File
  extends AndroidNonvisibleComponent
  implements Component
{
  private static final String LOG_TAG = "FileComponent";
  public static final String NO_ASSETS = "No_Assets";
  private final int BUFFER_LENGTH = 4096;
  private final Activity activity;
  private boolean isRepl = false;
  
  public File(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    if ((this.form instanceof ReplForm)) {
      this.isRepl = true;
    }
    this.activity = paramComponentContainer.$context();
  }
  
  private String AbsoluteFileName(String paramString)
  {
    if (paramString.startsWith("/")) {
      return Environment.getExternalStorageDirectory().getPath() + paramString;
    }
    Object localObject = this.activity.getFilesDir();
    if (this.isRepl)
    {
      java.io.File localFile = new java.io.File(Environment.getExternalStorageDirectory().getPath() + "/AppInventor/data/");
      localObject = localFile;
      if (!localFile.exists())
      {
        localFile.mkdirs();
        localObject = localFile;
      }
    }
    return ((java.io.File)localObject).getPath() + "/" + paramString;
  }
  
  /* Error */
  private void AsyncRead(InputStream paramInputStream, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aconst_null
    //   6: astore 5
    //   8: new 127	java/io/InputStreamReader
    //   11: dup
    //   12: aload_1
    //   13: invokespecial 130	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   16: astore_1
    //   17: new 132	java/io/StringWriter
    //   20: dup
    //   21: invokespecial 133	java/io/StringWriter:<init>	()V
    //   24: astore_3
    //   25: sipush 4096
    //   28: newarray <illegal type>
    //   30: astore 4
    //   32: aload_1
    //   33: aload 4
    //   35: iconst_0
    //   36: sipush 4096
    //   39: invokevirtual 137	java/io/InputStreamReader:read	([CII)I
    //   42: istore 6
    //   44: iload 6
    //   46: ifle +61 -> 107
    //   49: aload_3
    //   50: aload 4
    //   52: iconst_0
    //   53: iload 6
    //   55: invokevirtual 141	java/io/StringWriter:write	([CII)V
    //   58: goto -26 -> 32
    //   61: astore 4
    //   63: aload_1
    //   64: astore_3
    //   65: ldc 31
    //   67: ldc -113
    //   69: aload 4
    //   71: invokestatic 149	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   74: pop
    //   75: aload_1
    //   76: astore_3
    //   77: aload_0
    //   78: getfield 59	com/google/appinventor/components/runtime/File:form	Lcom/google/appinventor/components/runtime/Form;
    //   81: aload_0
    //   82: ldc -105
    //   84: sipush 2101
    //   87: iconst_1
    //   88: anewarray 153	java/lang/Object
    //   91: dup
    //   92: iconst_0
    //   93: aload_2
    //   94: aastore
    //   95: invokevirtual 159	com/google/appinventor/components/runtime/Form:dispatchErrorOccurredEvent	(Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   98: aload_1
    //   99: ifnull +7 -> 106
    //   102: aload_1
    //   103: invokevirtual 162	java/io/InputStreamReader:close	()V
    //   106: return
    //   107: aload_0
    //   108: aload_3
    //   109: invokevirtual 163	java/io/StringWriter:toString	()Ljava/lang/String;
    //   112: invokespecial 166	com/google/appinventor/components/runtime/File:normalizeNewLines	(Ljava/lang/String;)Ljava/lang/String;
    //   115: astore_3
    //   116: aload_0
    //   117: getfield 67	com/google/appinventor/components/runtime/File:activity	Landroid/app/Activity;
    //   120: new 12	com/google/appinventor/components/runtime/File$3
    //   123: dup
    //   124: aload_0
    //   125: aload_3
    //   126: invokespecial 169	com/google/appinventor/components/runtime/File$3:<init>	(Lcom/google/appinventor/components/runtime/File;Ljava/lang/String;)V
    //   129: invokevirtual 173	android/app/Activity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   132: aload_1
    //   133: ifnull +101 -> 234
    //   136: aload_1
    //   137: invokevirtual 162	java/io/InputStreamReader:close	()V
    //   140: return
    //   141: astore_1
    //   142: return
    //   143: astore_3
    //   144: aload 4
    //   146: astore_1
    //   147: aload_3
    //   148: astore 4
    //   150: aload_1
    //   151: astore_3
    //   152: ldc 31
    //   154: ldc -81
    //   156: aload 4
    //   158: invokestatic 149	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   161: pop
    //   162: aload_1
    //   163: astore_3
    //   164: aload_0
    //   165: getfield 59	com/google/appinventor/components/runtime/File:form	Lcom/google/appinventor/components/runtime/Form;
    //   168: aload_0
    //   169: ldc -105
    //   171: sipush 2102
    //   174: iconst_1
    //   175: anewarray 153	java/lang/Object
    //   178: dup
    //   179: iconst_0
    //   180: aload_2
    //   181: aastore
    //   182: invokevirtual 159	com/google/appinventor/components/runtime/Form:dispatchErrorOccurredEvent	(Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   185: aload_1
    //   186: ifnull -80 -> 106
    //   189: aload_1
    //   190: invokevirtual 162	java/io/InputStreamReader:close	()V
    //   193: return
    //   194: astore_1
    //   195: return
    //   196: astore_1
    //   197: aload_3
    //   198: ifnull +7 -> 205
    //   201: aload_3
    //   202: invokevirtual 162	java/io/InputStreamReader:close	()V
    //   205: aload_1
    //   206: athrow
    //   207: astore_1
    //   208: return
    //   209: astore_2
    //   210: goto -5 -> 205
    //   213: astore_2
    //   214: aload_1
    //   215: astore_3
    //   216: aload_2
    //   217: astore_1
    //   218: goto -21 -> 197
    //   221: astore 4
    //   223: goto -73 -> 150
    //   226: astore 4
    //   228: aload 5
    //   230: astore_1
    //   231: goto -168 -> 63
    //   234: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	235	0	this	File
    //   0	235	1	paramInputStream	InputStream
    //   0	235	2	paramString	String
    //   4	122	3	localObject1	Object
    //   143	5	3	localIOException1	IOException
    //   151	65	3	localInputStream	InputStream
    //   1	50	4	arrayOfChar	char[]
    //   61	84	4	localFileNotFoundException1	FileNotFoundException
    //   148	9	4	localObject2	Object
    //   221	1	4	localIOException2	IOException
    //   226	1	4	localFileNotFoundException2	FileNotFoundException
    //   6	223	5	localObject3	Object
    //   42	12	6	i	int
    // Exception table:
    //   from	to	target	type
    //   17	32	61	java/io/FileNotFoundException
    //   32	44	61	java/io/FileNotFoundException
    //   49	58	61	java/io/FileNotFoundException
    //   107	132	61	java/io/FileNotFoundException
    //   136	140	141	java/io/IOException
    //   8	17	143	java/io/IOException
    //   189	193	194	java/io/IOException
    //   8	17	196	finally
    //   65	75	196	finally
    //   77	98	196	finally
    //   152	162	196	finally
    //   164	185	196	finally
    //   102	106	207	java/io/IOException
    //   201	205	209	java/io/IOException
    //   17	32	213	finally
    //   32	44	213	finally
    //   49	58	213	finally
    //   107	132	213	finally
    //   17	32	221	java/io/IOException
    //   32	44	221	java/io/IOException
    //   49	58	221	java/io/IOException
    //   107	132	221	java/io/IOException
    //   8	17	226	java/io/FileNotFoundException
  }
  
  private void Write(final String paramString1, final String paramString2, final boolean paramBoolean)
  {
    if (paramString1.startsWith("//"))
    {
      if (paramBoolean)
      {
        this.form.dispatchErrorOccurredEvent(this, "AppendTo", 2106, new Object[] { paramString1 });
        return;
      }
      this.form.dispatchErrorOccurredEvent(this, "SaveFile", 2106, new Object[] { paramString1 });
      return;
    }
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        str = File.this.AbsoluteFileName(paramString1);
        Object localObject = new java.io.File(str);
        if (!((java.io.File)localObject).exists()) {}
        try
        {
          ((java.io.File)localObject).createNewFile();
          OutputStreamWriter localOutputStreamWriter;
          return;
        }
        catch (IOException localIOException1)
        {
          try
          {
            localObject = new FileOutputStream((java.io.File)localObject, paramBoolean);
            localOutputStreamWriter = new OutputStreamWriter((OutputStream)localObject);
            localOutputStreamWriter.write(paramString2);
            localOutputStreamWriter.flush();
            localOutputStreamWriter.close();
            ((FileOutputStream)localObject).close();
            return;
          }
          catch (IOException localIOException2)
          {
            if (!paramBoolean) {
              break label176;
            }
            File.this.form.dispatchErrorOccurredEvent(File.this, "AppendTo", 2104, new Object[] { str });
            return;
            File.this.form.dispatchErrorOccurredEvent(File.this, "SaveFile", 2104, new Object[] { str });
          }
          localIOException1 = localIOException1;
          if (paramBoolean)
          {
            File.this.form.dispatchErrorOccurredEvent(File.this, "AppendTo", 2103, new Object[] { str });
            return;
          }
          File.this.form.dispatchErrorOccurredEvent(File.this, "SaveFile", 2103, new Object[] { str });
          return;
        }
      }
    });
  }
  
  private String normalizeNewLines(String paramString)
  {
    return paramString.replaceAll("\r\n", "\n");
  }
  
  @SimpleFunction(description="Appends text to the end of a file storage, creating the file if it does not exist. See the help text under SaveFile for information about where files are written.")
  public void AppendToFile(String paramString1, String paramString2)
  {
    if (paramString2.startsWith("/")) {
      FileUtil.checkExternalStorageWriteable();
    }
    Write(paramString2, paramString1, true);
  }
  
  @SimpleFunction(description="Deletes a file from storage. Prefix the filename with / to delete a specific file in the SD card, for instance /myFile.txt. will delete the file /sdcard/myFile.txt. If the file does not begin with a /, then the file located in the programs private storage will be deleted. Starting the file with // is an error because assets files cannot be deleted.")
  public void Delete(String paramString)
  {
    if (paramString.startsWith("//"))
    {
      this.form.dispatchErrorOccurredEvent(this, "DeleteFile", 2105, new Object[] { paramString });
      return;
    }
    new java.io.File(AbsoluteFileName(paramString)).delete();
  }
  
  @SimpleEvent(description="Event indicating that the contents from the file have been read.")
  public void GotText(String paramString)
  {
    EventDispatcher.dispatchEvent(this, "GotText", new Object[] { paramString });
  }
  
  @SimpleFunction(description="Reads text from a file in storage. Prefix the filename with / to read from a specific file on the SD card. for instance /myFile.txt will read the file /sdcard/myFile.txt. To read assets packaged with an application (also works for the Companion) start the filename with // (two slashes). If a filename does not start with a slash, it will be read from the applications private storage (for packaged apps) and from /sdcard/AppInventor/data for the Companion.")
  public void ReadFrom(final String paramString)
  {
    try
    {
      final Object localObject;
      if (paramString.startsWith("//")) {
        if (this.isRepl) {
          localObject = new FileInputStream(Environment.getExternalStorageDirectory().getPath() + "/AppInventor/assets/" + paramString);
        }
      }
      for (;;)
      {
        AsynchUtil.runAsynchronously(new Runnable()
        {
          public void run()
          {
            File.this.AsyncRead(localObject, paramString);
          }
        });
        return;
        localObject = this.form.getAssets().open(paramString.substring(2));
        continue;
        localObject = AbsoluteFileName(paramString);
        Log.d("FileComponent", "filepath = " + (String)localObject);
        localObject = new FileInputStream((String)localObject);
      }
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      Log.e("FileComponent", "FileNotFoundException", localFileNotFoundException);
      this.form.dispatchErrorOccurredEvent(this, "ReadFrom", 2101, new Object[] { paramString });
      return;
    }
    catch (IOException localIOException)
    {
      Log.e("FileComponent", "IOException", localIOException);
      this.form.dispatchErrorOccurredEvent(this, "ReadFrom", 2101, new Object[] { paramString });
    }
  }
  
  @SimpleFunction(description="Saves text to a file. If the filename begins with a slash (/) the file is written to the sdcard. For example writing to /myFile.txt will write the file to /sdcard/myFile.txt. If the filename does not start with a slash, it will be written in the programs private data directory where it will not be accessible to other programs on the phone. There is a special exception for the AI Companion where these files are written to /sdcard/AppInventor/data to facilitate debugging. Note that this block will overwrite a file if it already exists.\n\nIf you want to add content to a file use the append block.")
  public void SaveFile(String paramString1, String paramString2)
  {
    if (paramString2.startsWith("/")) {
      FileUtil.checkExternalStorageWriteable();
    }
    Write(paramString2, paramString1, false);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\File.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */