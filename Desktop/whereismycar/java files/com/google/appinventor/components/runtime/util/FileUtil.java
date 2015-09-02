package com.google.appinventor.components.runtime.util;

import android.os.Environment;
import com.google.appinventor.components.runtime.errors.RuntimeError;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

public class FileUtil
{
  private static final String DIRECTORY_DOWNLOADS = "Downloads";
  private static final String DIRECTORY_PICTURES = "Pictures";
  private static final String DIRECTORY_RECORDINGS = "Recordings";
  private static final String DOCUMENT_DIRECTORY = "My Documents/";
  private static final String FILENAME_PREFIX = "app_inventor_";
  
  public static void checkExternalStorageWriteable()
    throws FileUtil.FileException
  {
    String str = Environment.getExternalStorageState();
    if ("mounted".equals(str)) {
      return;
    }
    if ("mounted_ro".equals(str)) {
      throw new FileException(704);
    }
    throw new FileException(705);
  }
  
  private static void copy(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream = new BufferedOutputStream(paramOutputStream, 4096);
    paramInputStream = new BufferedInputStream(paramInputStream, 4096);
    for (;;)
    {
      int i = paramInputStream.read();
      if (i == -1)
      {
        paramOutputStream.flush();
        return;
      }
      paramOutputStream.write(i);
    }
  }
  
  public static String copyFile(String paramString1, String paramString2)
    throws IOException
  {
    paramString1 = new FileInputStream(paramString1);
    try
    {
      paramString2 = writeStreamToFile(paramString1, paramString2);
      return paramString2;
    }
    finally
    {
      paramString1.close();
    }
  }
  
  public static String downloadUrlToFile(String paramString1, String paramString2)
    throws IOException
  {
    paramString1 = new URL(paramString1).openStream();
    try
    {
      paramString2 = writeStreamToFile(paramString1, paramString2);
      return paramString2;
    }
    finally
    {
      paramString1.close();
    }
  }
  
  public static File getDownloadFile(String paramString)
    throws IOException, FileUtil.FileException
  {
    return getFile("Downloads", paramString);
  }
  
  public static File getExternalFile(String paramString)
    throws IOException, FileUtil.FileException
  {
    checkExternalStorageWriteable();
    paramString = new File(Environment.getExternalStorageDirectory(), paramString);
    File localFile = paramString.getParentFile();
    if ((!localFile.exists()) && (!localFile.mkdirs())) {
      throw new IOException("Unable to create directory " + localFile.getAbsolutePath());
    }
    if ((paramString.exists()) && (!paramString.delete())) {
      throw new IOException("Cannot overwrite existing file " + paramString.getAbsolutePath());
    }
    return paramString;
  }
  
  private static File getFile(String paramString1, String paramString2)
    throws IOException, FileUtil.FileException
  {
    return getExternalFile("My Documents/" + paramString1 + "/" + "app_inventor_" + System.currentTimeMillis() + "." + paramString2);
  }
  
  public static String getFileUrl(String paramString)
  {
    return new File(paramString).toURI().toString();
  }
  
  public static File getPictureFile(String paramString)
    throws IOException, FileUtil.FileException
  {
    return getFile("Pictures", paramString);
  }
  
  public static File getRecordingFile(String paramString)
    throws IOException, FileUtil.FileException
  {
    return getFile("Recordings", paramString);
  }
  
  public static byte[] readFile(String paramString)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramString = new FileInputStream(paramString);
    try
    {
      copy(paramString, localByteArrayOutputStream);
      return localByteArrayOutputStream.toByteArray();
    }
    finally
    {
      paramString.close();
    }
  }
  
  public static String writeFile(byte[] paramArrayOfByte, String paramString)
    throws IOException
  {
    paramArrayOfByte = new ByteArrayInputStream(paramArrayOfByte);
    try
    {
      paramString = writeStreamToFile(paramArrayOfByte, paramString);
      return paramString;
    }
    finally
    {
      paramArrayOfByte.close();
    }
  }
  
  public static String writeStreamToFile(InputStream paramInputStream, String paramString)
    throws IOException
  {
    File localFile = new File(paramString);
    localFile.getParentFile().mkdirs();
    paramString = new FileOutputStream(localFile);
    try
    {
      copy(paramInputStream, paramString);
      paramInputStream = localFile.toURI().toString();
      return paramInputStream;
    }
    finally
    {
      paramString.flush();
      paramString.close();
    }
  }
  
  public static class FileException
    extends RuntimeError
  {
    private final int msgNumber;
    
    public FileException(int paramInt)
    {
      this.msgNumber = paramInt;
    }
    
    public int getErrorMessageNumber()
    {
      return this.msgNumber;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\FileUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */