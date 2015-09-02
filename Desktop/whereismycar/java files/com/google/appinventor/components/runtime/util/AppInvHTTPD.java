package com.google.appinventor.components.runtime.util;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.runtime.ReplForm;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Properties;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kawa.standard.Scheme;

public class AppInvHTTPD
  extends NanoHTTPD
{
  private static final String LOG_TAG = "AppInvHTTPD";
  private static final String MIME_JSON = "application/json";
  private static final int YAV_SKEW_BACKWARD = 4;
  private static final int YAV_SKEW_FORWARD = 1;
  private static byte[] hmacKey;
  private static int seq;
  private final Handler androidUIHandler = new Handler();
  private ReplForm form;
  private File rootDir;
  private Language scheme;
  private boolean secure;
  
  public AppInvHTTPD(int paramInt, File paramFile, boolean paramBoolean, ReplForm paramReplForm)
    throws IOException
  {
    super(paramInt, paramFile);
    this.rootDir = paramFile;
    this.scheme = Scheme.getInstance("scheme");
    this.form = paramReplForm;
    this.secure = paramBoolean;
    ModuleExp.mustNeverCompile();
  }
  
  private void copyFile(File paramFile1, File paramFile2)
  {
    try
    {
      paramFile1 = new FileInputStream(paramFile1);
      paramFile2 = new FileOutputStream(paramFile2);
      byte[] arrayOfByte = new byte[32768];
      for (;;)
      {
        int i = paramFile1.read(arrayOfByte);
        if (i <= 0) {
          break;
        }
        paramFile2.write(arrayOfByte, 0, i);
      }
      paramFile1.close();
    }
    catch (IOException paramFile1)
    {
      paramFile1.printStackTrace();
      return;
    }
    paramFile2.close();
  }
  
  private void doPackageUpdate(String paramString)
  {
    PackageInstaller.doPackageInstall(this.form, paramString);
  }
  
  public static void setHmacKey(String paramString)
  {
    hmacKey = paramString.getBytes();
    seq = 1;
  }
  
  public void resetSeq()
  {
    seq = 1;
  }
  
  public NanoHTTPD.Response serve(String paramString1, String paramString2, Properties paramProperties1, Properties paramProperties2, Properties paramProperties3, Socket paramSocket)
  {
    Log.d("AppInvHTTPD", paramString2 + " '" + paramString1 + "' ");
    if (this.secure)
    {
      paramSocket = paramSocket.getInetAddress().getHostAddress();
      if (!paramSocket.equals("127.0.0.1"))
      {
        Log.d("AppInvHTTPD", "Debug: hostAddress = " + paramSocket + " while in secure mode, closing connection.");
        paramString2 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid Source Location " + paramSocket + "\"}");
        paramString2.addHeader("Access-Control-Allow-Origin", "*");
        paramString2.addHeader("Access-Control-Allow-Headers", "origin, content-type");
        paramString2.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
        paramString2.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
        return paramString2;
      }
    }
    if (paramString2.equals("OPTIONS"))
    {
      paramString1 = paramProperties1.propertyNames();
      while (paramString1.hasMoreElements())
      {
        paramString2 = (String)paramString1.nextElement();
        Log.d("AppInvHTTPD", "  HDR: '" + paramString2 + "' = '" + paramProperties1.getProperty(paramString2) + "'");
      }
      paramString1 = new NanoHTTPD.Response(this, "200 OK", "text/plain", "OK");
      paramString1.addHeader("Access-Control-Allow-Origin", "*");
      paramString1.addHeader("Access-Control-Allow-Headers", "origin, content-type");
      paramString1.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
      paramString1.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
      return paramString1;
    }
    int j;
    int i;
    if (paramString1.equals("/_newblocks"))
    {
      paramProperties1 = paramProperties2.getProperty("seq", "0");
      j = Integer.parseInt(paramProperties1);
      paramString2 = paramProperties2.getProperty("blockid");
      paramString1 = paramProperties2.getProperty("code");
      paramProperties2 = paramProperties2.getProperty("mac", "no key provided");
      if (hmacKey != null)
      {
        try
        {
          paramProperties3 = Mac.getInstance("HmacSHA1");
          paramProperties3.init(new SecretKeySpec(hmacKey, "RAW"));
          paramProperties3 = paramProperties3.doFinal((paramString1 + paramProperties1 + paramString2).getBytes());
          paramSocket = new StringBuffer(paramProperties3.length * 2);
          Formatter localFormatter = new Formatter(paramSocket);
          int k = paramProperties3.length;
          i = 0;
          while (i < k)
          {
            localFormatter.format("%02x", new Object[] { Byte.valueOf(paramProperties3[i]) });
            i += 1;
          }
          paramProperties3 = paramSocket.toString();
          Log.d("AppInvHTTPD", "Incoming Mac = " + paramProperties2);
          Log.d("AppInvHTTPD", "Computed Mac = " + paramProperties3);
          Log.d("AppInvHTTPD", "Incoming seq = " + paramProperties1);
          Log.d("AppInvHTTPD", "Computed seq = " + seq);
          Log.d("AppInvHTTPD", "blockid = " + paramString2);
          if (!paramProperties2.equals(paramProperties3))
          {
            Log.e("AppInvHTTPD", "Hmac does not match");
            this.form.dispatchErrorOccurredEvent(this.form, "AppInvHTTPD", 1801, new Object[] { "Invalid HMAC" });
            return new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid MAC\"}");
          }
        }
        catch (Exception paramString1)
        {
          Log.e("AppInvHTTPD", "Error working with hmac", paramString1);
          this.form.dispatchErrorOccurredEvent(this.form, "AppInvHTTPD", 1801, new Object[] { "Exception working on HMAC" });
          return new NanoHTTPD.Response(this, "200 OK", "text/plain", "NOT");
        }
        if ((seq != j) && (seq != j + 1))
        {
          Log.e("AppInvHTTPD", "Seq does not match");
          this.form.dispatchErrorOccurredEvent(this.form, "AppInvHTTPD", 1801, new Object[] { "Invalid Seq" });
          return new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid Seq\"}");
        }
        if (seq == j + 1) {
          Log.e("AppInvHTTPD", "Seq Fixup Invoked");
        }
        seq = j + 1;
        paramProperties1 = "(begin (require <com.google.youngandroid.runtime>) (process-repl-input " + paramString2 + " (begin " + paramString1 + " )))";
        Log.d("AppInvHTTPD", "To Eval: " + paramProperties1);
      }
      for (;;)
      {
        try
        {
          if (!paramString1.equals("#f")) {
            continue;
          }
          Log.e("AppInvHTTPD", "Skipping evaluation of #f");
          paramString1 = new NanoHTTPD.Response(this, "200 OK", "application/json", RetValManager.fetch(false));
        }
        catch (Throwable paramString1)
        {
          Log.e("AppInvHTTPD", "newblocks: Scheme Failure", paramString1);
          RetValManager.appendReturnValue(paramString2, "BAD", paramString1.toString());
          paramString1 = new NanoHTTPD.Response(this, "200 OK", "application/json", RetValManager.fetch(false));
          continue;
        }
        paramString1.addHeader("Access-Control-Allow-Origin", "*");
        paramString1.addHeader("Access-Control-Allow-Headers", "origin, content-type");
        paramString1.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
        paramString1.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
        return paramString1;
        Log.e("AppInvHTTPD", "No HMAC Key");
        this.form.dispatchErrorOccurredEvent(this.form, "AppInvHTTPD", 1801, new Object[] { "No HMAC Key" });
        return new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"BAD\", \"message\" : \"Security Error: No HMAC Key\"}");
        this.scheme.eval(paramProperties1);
      }
    }
    if (paramString1.equals("/_values"))
    {
      paramString1 = new NanoHTTPD.Response(this, "200 OK", "application/json", RetValManager.fetch(true));
      paramString1.addHeader("Access-Control-Allow-Origin", "*");
      paramString1.addHeader("Access-Control-Allow-Headers", "origin, content-type");
      paramString1.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
      paramString1.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
      return paramString1;
    }
    if (paramString1.equals("/_getversion")) {
      for (;;)
      {
        try
        {
          paramProperties1 = this.form.getPackageName();
          paramString2 = this.form.getPackageManager().getPackageInfo(paramProperties1, 0);
          if (SdkLevel.getLevel() < 5) {
            continue;
          }
          paramString1 = EclairUtil.getInstallerPackageName("edu.mit.appinventor.aicompanion3", this.form);
          paramProperties2 = paramString2.versionName;
          paramString2 = paramString1;
          if (paramString1 == null) {
            paramString2 = "Not Known";
          }
          paramString1 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"version\" : \"" + paramProperties2 + "\", \"fingerprint\" : \"" + Build.FINGERPRINT + "\"," + " \"installer\" : \"" + paramString2 + "\", \"package\" : \"" + paramProperties1 + "\" }");
        }
        catch (PackageManager.NameNotFoundException paramString1)
        {
          paramString1.printStackTrace();
          paramString1 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"verison\" : \"Unknown\"");
          continue;
        }
        paramString1.addHeader("Access-Control-Allow-Origin", "*");
        paramString1.addHeader("Access-Control-Allow-Headers", "origin, content-type");
        paramString1.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
        paramString1.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
        paramString2 = paramString1;
        if (!this.secure) {
          break;
        }
        seq = 1;
        this.androidUIHandler.post(new Runnable()
        {
          public void run()
          {
            AppInvHTTPD.this.form.clear();
          }
        });
        return paramString1;
        paramString1 = "Not Known";
      }
    }
    if ((paramString1.equals("/_update")) || (paramString1.equals("/_install")))
    {
      paramString1 = paramProperties2.getProperty("url", "");
      paramString2 = paramProperties2.getProperty("mac", "");
      if ((!paramString1.equals("")) && (hmacKey != null) && (!paramString2.equals("")))
      {
        try
        {
          paramProperties1 = new SecretKeySpec(hmacKey, "RAW");
          paramProperties2 = Mac.getInstance("HmacSHA1");
          paramProperties2.init(paramProperties1);
          paramProperties1 = paramProperties2.doFinal(paramString1.getBytes());
          paramProperties2 = new StringBuffer(paramProperties1.length * 2);
          paramProperties3 = new Formatter(paramProperties2);
          j = paramProperties1.length;
          i = 0;
          while (i < j)
          {
            paramProperties3.format("%02x", new Object[] { Byte.valueOf(paramProperties1[i]) });
            i += 1;
          }
          paramProperties1 = paramProperties2.toString();
          Log.d("AppInvHTTPD", "Incoming Mac (update) = " + paramString2);
          Log.d("AppInvHTTPD", "Computed Mac (update) = " + paramProperties1);
          if (!paramString2.equals(paramProperties1))
          {
            Log.e("AppInvHTTPD", "Hmac does not match");
            this.form.dispatchErrorOccurredEvent(this.form, "AppInvHTTPD", 1801, new Object[] { "Invalid HMAC (update)" });
            paramString1 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid MAC\"}");
            paramString1.addHeader("Access-Control-Allow-Origin", "*");
            paramString1.addHeader("Access-Control-Allow-Headers", "origin, content-type");
            paramString1.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
            paramString1.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
            return paramString1;
          }
        }
        catch (Exception paramString1)
        {
          Log.e("AppInvHTTPD", "Error verifying update", paramString1);
          this.form.dispatchErrorOccurredEvent(this.form, "AppInvHTTPD", 1801, new Object[] { "Exception working on HMAC for update" });
          paramString1 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"BAD\", \"message\" : \"Security Error: Exception processing MAC\"}");
          paramString1.addHeader("Access-Control-Allow-Origin", "*");
          paramString1.addHeader("Access-Control-Allow-Headers", "origin, content-type");
          paramString1.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
          paramString1.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
          return paramString1;
        }
        doPackageUpdate(paramString1);
        paramString1 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"OK\", \"message\" : \"Update Should Happen\"}");
        paramString1.addHeader("Access-Control-Allow-Origin", "*");
        paramString1.addHeader("Access-Control-Allow-Headers", "origin, content-type");
        paramString1.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
        paramString1.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
        return paramString1;
      }
      paramString1 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"BAD\", \"message\" : \"Missing Parameters\"}");
      paramString1.addHeader("Access-Control-Allow-Origin", "*");
      paramString1.addHeader("Access-Control-Allow-Headers", "origin, content-type");
      paramString1.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
      paramString1.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
      return paramString1;
    }
    if (paramString1.equals("/_package"))
    {
      paramString1 = paramProperties2.getProperty("package", null);
      if (paramString1 == null) {
        return new NanoHTTPD.Response(this, "200 OK", "text/plain", "NOT OK");
      }
      Log.d("AppInvHTTPD", this.rootDir + "/" + paramString1);
      paramString2 = new Intent("android.intent.action.VIEW");
      paramString2.setDataAndType(Uri.fromFile(new File(this.rootDir + "/" + paramString1)), "application/vnd.android.package-archive");
      this.form.startActivity(paramString2);
      return new NanoHTTPD.Response(this, "200 OK", "text/plain", "OK");
    }
    if (paramString2.equals("PUT"))
    {
      paramString2 = Boolean.valueOf(false);
      paramString1 = paramProperties3.getProperty("content", null);
      if (paramString1 != null)
      {
        paramProperties3 = new File(paramString1);
        paramProperties1 = paramProperties2.getProperty("filename", null);
        paramString1 = paramProperties1;
        if (paramProperties1 != null) {
          if ((!paramProperties1.startsWith("..")) && (!paramProperties1.endsWith("..")))
          {
            paramString1 = paramProperties1;
            if (paramProperties1.indexOf("../") < 0) {}
          }
          else
          {
            Log.d("AppInvHTTPD", " Ignoring invalid filename: " + paramProperties1);
            paramString1 = null;
          }
        }
        if (paramString1 != null)
        {
          paramProperties1 = new File(this.rootDir + "/" + paramString1);
          paramString1 = paramString2;
          if (!paramProperties3.renameTo(paramProperties1))
          {
            copyFile(paramProperties3, paramProperties1);
            paramProperties3.delete();
            paramString1 = paramString2;
          }
        }
      }
      while (paramString1.booleanValue())
      {
        paramString1 = new NanoHTTPD.Response(this, "200 OK", "text/plain", "NOTOK");
        paramString1.addHeader("Access-Control-Allow-Origin", "*");
        paramString1.addHeader("Access-Control-Allow-Headers", "origin, content-type");
        paramString1.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
        paramString1.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
        return paramString1;
        paramProperties3.delete();
        Log.e("AppInvHTTPD", "Received content without a file name!");
        paramString1 = Boolean.valueOf(true);
        continue;
        Log.e("AppInvHTTPD", "Received PUT without content.");
        paramString1 = Boolean.valueOf(true);
      }
      paramString1 = new NanoHTTPD.Response(this, "200 OK", "text/plain", "OK");
      paramString1.addHeader("Access-Control-Allow-Origin", "*");
      paramString1.addHeader("Access-Control-Allow-Headers", "origin, content-type");
      paramString1.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
      paramString1.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
      return paramString1;
    }
    paramString2 = paramProperties1.propertyNames();
    while (paramString2.hasMoreElements())
    {
      paramSocket = (String)paramString2.nextElement();
      Log.d("AppInvHTTPD", "  HDR: '" + paramSocket + "' = '" + paramProperties1.getProperty(paramSocket) + "'");
    }
    paramString2 = paramProperties2.propertyNames();
    while (paramString2.hasMoreElements())
    {
      paramSocket = (String)paramString2.nextElement();
      Log.d("AppInvHTTPD", "  PRM: '" + paramSocket + "' = '" + paramProperties2.getProperty(paramSocket) + "'");
    }
    paramString2 = paramProperties3.propertyNames();
    if (paramString2.hasMoreElements())
    {
      paramString1 = (String)paramString2.nextElement();
      paramProperties1 = paramProperties3.getProperty(paramString1);
      paramString2 = paramProperties2.getProperty(paramString1);
      if ((!paramString2.startsWith("..")) && (!paramString2.endsWith("..")))
      {
        paramString1 = paramString2;
        if (paramString2.indexOf("../") < 0) {}
      }
      else
      {
        Log.d("AppInvHTTPD", " Ignoring invalid filename: " + paramString2);
        paramString1 = null;
      }
      paramString2 = new File(paramProperties1);
      if (paramString1 == null) {
        paramString2.delete();
      }
      for (;;)
      {
        Log.d("AppInvHTTPD", " UPLOADED: '" + paramString1 + "' was at '" + paramProperties1 + "'");
        paramString1 = new NanoHTTPD.Response(this, "200 OK", "text/plain", "OK");
        paramString1.addHeader("Access-Control-Allow-Origin", "*");
        paramString1.addHeader("Access-Control-Allow-Headers", "origin, content-type");
        paramString1.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
        paramString1.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
        return paramString1;
        paramProperties2 = new File(this.rootDir + "/" + paramString1);
        if (!paramString2.renameTo(paramProperties2))
        {
          copyFile(paramString2, paramProperties2);
          paramString2.delete();
        }
      }
    }
    return serveFile(paramString1, paramProperties1, this.rootDir, true);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\AppInvHTTPD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */