package com.google.appinventor.components.runtime.util;

import android.util.Log;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NanoHTTPD
{
  public static final String HTTP_BADREQUEST = "400 Bad Request";
  public static final String HTTP_FORBIDDEN = "403 Forbidden";
  public static final String HTTP_INTERNALERROR = "500 Internal Server Error";
  public static final String HTTP_NOTFOUND = "404 Not Found";
  public static final String HTTP_NOTIMPLEMENTED = "501 Not Implemented";
  public static final String HTTP_NOTMODIFIED = "304 Not Modified";
  public static final String HTTP_OK = "200 OK";
  public static final String HTTP_PARTIALCONTENT = "206 Partial Content";
  public static final String HTTP_RANGE_NOT_SATISFIABLE = "416 Requested Range Not Satisfiable";
  public static final String HTTP_REDIRECT = "301 Moved Permanently";
  private static final String LICENCE = "Copyright (C) 2001,2005-2011 by Jarno Elonen <elonen@iki.fi>\nand Copyright (C) 2010 by Konstantinos Togias <info@ktogias.gr>\n\nRedistribution and use in source and binary forms, with or without\nmodification, are permitted provided that the following conditions\nare met:\n\nRedistributions of source code must retain the above copyright notice,\nthis list of conditions and the following disclaimer. Redistributions in\nbinary form must reproduce the above copyright notice, this list of\nconditions and the following disclaimer in the documentation and/or other\nmaterials provided with the distribution. The name of the author may not\nbe used to endorse or promote products derived from this software without\nspecific prior written permission. \n \nTHIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR\nIMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES\nOF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\nIN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,\nINCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT\nNOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\nDATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\nTHEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\nOF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.";
  private static final String LOG_TAG = "AppInvHTTPD";
  public static final String MIME_DEFAULT_BINARY = "application/octet-stream";
  public static final String MIME_HTML = "text/html";
  public static final String MIME_PLAINTEXT = "text/plain";
  public static final String MIME_XML = "text/xml";
  private static final int REPL_STACK_SIZE = 262144;
  private static SimpleDateFormat gmtFrmt;
  protected static PrintStream myErr;
  protected static PrintStream myOut;
  private static int theBufferSize;
  private static Hashtable theMimeTypes = new Hashtable();
  private ThreadPoolExecutor myExecutor = new ThreadPoolExecutor(2, 10, 5L, TimeUnit.SECONDS, new SynchronousQueue(), new myThreadFactory(null));
  private File myRootDir;
  private final ServerSocket myServerSocket;
  private int myTcpPort;
  private Thread myThread;
  
  static
  {
    StringTokenizer localStringTokenizer = new StringTokenizer("css            text/css htm            text/html html           text/html xml            text/xml txt            text/plain asc            text/plain gif            image/gif jpg            image/jpeg jpeg           image/jpeg png            image/png mp3            audio/mpeg m3u            audio/mpeg-url mp4            video/mp4 ogv            video/ogg flv            video/x-flv mov            video/quicktime swf            application/x-shockwave-flash js                     application/javascript pdf            application/pdf doc            application/msword ogg            application/x-ogg zip            application/octet-stream exe            application/octet-stream class          application/octet-stream ");
    while (localStringTokenizer.hasMoreTokens()) {
      theMimeTypes.put(localStringTokenizer.nextToken(), localStringTokenizer.nextToken());
    }
    theBufferSize = 16384;
    myOut = System.out;
    myErr = System.err;
    gmtFrmt = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
    gmtFrmt.setTimeZone(TimeZone.getTimeZone("GMT"));
  }
  
  public NanoHTTPD(int paramInt, File paramFile)
    throws IOException
  {
    this.myTcpPort = paramInt;
    this.myRootDir = paramFile;
    this.myServerSocket = new ServerSocket(this.myTcpPort);
    this.myThread = new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          for (;;)
          {
            new NanoHTTPD.HTTPSession(NanoHTTPD.this, NanoHTTPD.this.myServerSocket.accept());
          }
          return;
        }
        catch (IOException localIOException) {}
      }
    });
    this.myThread.setDaemon(true);
    this.myThread.start();
  }
  
  private String encodeUri(String paramString)
  {
    String str = "";
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "/ ", true);
    paramString = str;
    while (localStringTokenizer.hasMoreTokens())
    {
      str = localStringTokenizer.nextToken();
      if (str.equals("/")) {
        paramString = paramString + "/";
      } else if (str.equals(" ")) {
        paramString = paramString + "%20";
      } else {
        paramString = paramString + URLEncoder.encode(str);
      }
    }
    return paramString;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    myOut.println("NanoHTTPD 1.25 (C) 2001,2005-2011 Jarno Elonen and (C) 2010 Konstantinos Togias\n(Command line options: [-p port] [-d root-dir] [--licence])\n");
    int j = 80;
    Object localObject1 = new File(".").getAbsoluteFile();
    int i = 0;
    if (i < paramArrayOfString.length)
    {
      int k;
      Object localObject2;
      if (paramArrayOfString[i].equalsIgnoreCase("-p"))
      {
        k = Integer.parseInt(paramArrayOfString[(i + 1)]);
        localObject2 = localObject1;
      }
      label107:
      do
      {
        for (;;)
        {
          i += 1;
          j = k;
          localObject1 = localObject2;
          break;
          if (!paramArrayOfString[i].equalsIgnoreCase("-d")) {
            break label107;
          }
          localObject2 = new File(paramArrayOfString[(i + 1)]).getAbsoluteFile();
          k = j;
        }
        k = j;
        localObject2 = localObject1;
      } while (!paramArrayOfString[i].toLowerCase().endsWith("licence"));
      myOut.println("Copyright (C) 2001,2005-2011 by Jarno Elonen <elonen@iki.fi>\nand Copyright (C) 2010 by Konstantinos Togias <info@ktogias.gr>\n\nRedistribution and use in source and binary forms, with or without\nmodification, are permitted provided that the following conditions\nare met:\n\nRedistributions of source code must retain the above copyright notice,\nthis list of conditions and the following disclaimer. Redistributions in\nbinary form must reproduce the above copyright notice, this list of\nconditions and the following disclaimer in the documentation and/or other\nmaterials provided with the distribution. The name of the author may not\nbe used to endorse or promote products derived from this software without\nspecific prior written permission. \n \nTHIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR\nIMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES\nOF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\nIN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,\nINCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT\nNOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\nDATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\nTHEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\nOF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n");
    }
    try
    {
      new NanoHTTPD(j, (File)localObject1);
      myOut.println("Now serving files in port " + j + " from \"" + localObject1 + "\"");
      myOut.println("Hit Enter to stop.\n");
    }
    catch (IOException paramArrayOfString)
    {
      for (;;)
      {
        try
        {
          System.in.read();
          return;
        }
        catch (Throwable paramArrayOfString) {}
        paramArrayOfString = paramArrayOfString;
        myErr.println("Couldn't start server:\n" + paramArrayOfString);
        System.exit(-1);
      }
    }
  }
  
  public Response serve(String paramString1, String paramString2, Properties paramProperties1, Properties paramProperties2, Properties paramProperties3, Socket paramSocket)
  {
    myOut.println(paramString2 + " '" + paramString1 + "' ");
    paramString2 = paramProperties1.propertyNames();
    while (paramString2.hasMoreElements())
    {
      paramSocket = (String)paramString2.nextElement();
      myOut.println("  HDR: '" + paramSocket + "' = '" + paramProperties1.getProperty(paramSocket) + "'");
    }
    paramString2 = paramProperties2.propertyNames();
    while (paramString2.hasMoreElements())
    {
      paramSocket = (String)paramString2.nextElement();
      myOut.println("  PRM: '" + paramSocket + "' = '" + paramProperties2.getProperty(paramSocket) + "'");
    }
    paramString2 = paramProperties3.propertyNames();
    while (paramString2.hasMoreElements())
    {
      paramProperties2 = (String)paramString2.nextElement();
      myOut.println("  UPLOADED: '" + paramProperties2 + "' = '" + paramProperties3.getProperty(paramProperties2) + "'");
    }
    return serveFile(paramString1, paramProperties1, this.myRootDir, true);
  }
  
  public Response serveFile(String paramString, Properties paramProperties, File paramFile, boolean paramBoolean)
  {
    Object localObject2 = null;
    if (!paramFile.isDirectory()) {
      localObject2 = new Response("500 Internal Server Error", "text/plain", "INTERNAL ERRROR: serveFile(): given homeDir is not a directory.");
    }
    Object localObject3 = localObject2;
    Object localObject1 = paramString;
    if (localObject2 == null)
    {
      localObject1 = paramString.trim().replace(File.separatorChar, '/');
      paramString = (String)localObject1;
      if (((String)localObject1).indexOf('?') >= 0) {
        paramString = ((String)localObject1).substring(0, ((String)localObject1).indexOf('?'));
      }
      if ((!paramString.startsWith("..")) && (!paramString.endsWith("..")))
      {
        localObject3 = localObject2;
        localObject1 = paramString;
        if (paramString.indexOf("../") < 0) {}
      }
      else
      {
        localObject3 = new Response("403 Forbidden", "text/plain", "FORBIDDEN: Won't serve ../ for security reasons.");
        localObject1 = paramString;
      }
    }
    File localFile = new File(paramFile, (String)localObject1);
    paramString = (String)localObject3;
    if (localObject3 == null)
    {
      paramString = (String)localObject3;
      if (!localFile.exists()) {
        paramString = new Response("404 Not Found", "text/plain", "Error 404, file not found.");
      }
    }
    localObject2 = paramString;
    if (paramString == null)
    {
      localObject2 = paramString;
      if (localFile.isDirectory())
      {
        localObject3 = localObject1;
        if (!((String)localObject1).endsWith("/"))
        {
          localObject3 = (String)localObject1 + "/";
          paramString = new Response("301 Moved Permanently", "text/html", "<html><body>Redirected: <a href=\"" + (String)localObject3 + "\">" + (String)localObject3 + "</a></body></html>");
          paramString.addHeader("Location", (String)localObject3);
        }
        localObject2 = paramString;
        if (paramString == null) {
          if (new File(localFile, "index.html").exists())
          {
            paramFile = new File(paramFile, (String)localObject3 + "/index.html");
            if (paramString != null) {
              break label1660;
            }
            paramString = null;
          }
        }
      }
    }
    for (;;)
    {
      try
      {
        i = paramFile.getCanonicalPath().lastIndexOf('.');
        if (i < 0) {
          break label1672;
        }
        paramString = (String)theMimeTypes.get(paramFile.getCanonicalPath().substring(i + 1).toLowerCase());
      }
      catch (IOException paramString)
      {
        int i;
        long l3;
        long l1;
        continue;
      }
      localObject3 = Integer.toHexString((paramFile.getAbsolutePath() + paramFile.lastModified() + "" + paramFile.length()).hashCode());
      l3 = 0L;
      long l4 = -1L;
      localObject2 = paramProperties.getProperty("range");
      final long l2 = l4;
      paramString = (String)localObject2;
      l1 = l3;
      if (localObject2 != null)
      {
        l2 = l4;
        paramString = (String)localObject2;
        l1 = l3;
        if (((String)localObject2).startsWith("bytes="))
        {
          localObject2 = ((String)localObject2).substring("bytes=".length());
          i = ((String)localObject2).indexOf('-');
          l2 = l4;
          paramString = (String)localObject2;
          l1 = l3;
          if (i > 0) {
            l1 = l3;
          }
        }
      }
      try
      {
        l2 = Long.parseLong(((String)localObject2).substring(0, i));
        l1 = l2;
        l3 = Long.parseLong(((String)localObject2).substring(i + 1));
        l1 = l2;
        paramString = (String)localObject2;
        l2 = l3;
      }
      catch (NumberFormatException paramString)
      {
        long l5;
        l2 = l4;
        paramString = (String)localObject2;
        continue;
      }
      l5 = paramFile.length();
      if ((paramString != null) && (l1 >= 0L))
      {
        if (l1 >= l5) {
          paramString = new Response("416 Requested Range Not Satisfiable", "text/plain", "");
        }
        try
        {
          paramString.addHeader("Content-Range", "bytes 0-0/" + l5);
          paramString.addHeader("ETag", (String)localObject3);
          paramString.addHeader("Accept-Ranges", "bytes");
          return paramString;
        }
        catch (IOException paramString) {}
        if (new File(localFile, "index.htm").exists())
        {
          paramFile = new File(paramFile, (String)localObject3 + "/index.htm");
          break;
        }
        if ((paramBoolean) && (localFile.canRead()))
        {
          localObject1 = localFile.list();
          paramFile = "<html><body><h1>Directory " + (String)localObject3 + "</h1><br/>";
          paramString = paramFile;
          if (((String)localObject3).length() > 1)
          {
            localObject2 = ((String)localObject3).substring(0, ((String)localObject3).length() - 1);
            i = ((String)localObject2).lastIndexOf('/');
            paramString = paramFile;
            if (i >= 0)
            {
              paramString = paramFile;
              if (i < ((String)localObject2).length()) {
                paramString = paramFile + "<b><a href=\"" + ((String)localObject3).substring(0, i + 1) + "\">..</a></b><br/>";
              }
            }
          }
          paramFile = paramString;
          if (localObject1 != null)
          {
            i = 0;
            paramFile = paramString;
            if (i < localObject1.length)
            {
              localObject2 = new File(localFile, localObject1[i]);
              paramBoolean = ((File)localObject2).isDirectory();
              paramFile = paramString;
              if (paramBoolean)
              {
                paramFile = paramString + "<b>";
                localObject1[i] = (localObject1[i] + "/");
              }
              paramFile = paramFile + "<a href=\"" + encodeUri(new StringBuilder().append((String)localObject3).append(localObject1[i]).toString()) + "\">" + localObject1[i] + "</a>";
              paramString = paramFile;
              if (((File)localObject2).isFile())
              {
                l1 = ((File)localObject2).length();
                paramString = paramFile + " &nbsp;<font size=2>(";
                if (l1 < 1024L)
                {
                  paramString = paramString + l1 + " bytes";
                  paramString = paramString + ")</font>";
                }
              }
              else
              {
                paramFile = paramString + "<br/>";
                paramString = paramFile;
                if (paramBoolean) {
                  paramString = paramFile + "</b>";
                }
                i += 1;
                continue;
              }
              if (l1 < 1048576L)
              {
                paramString = paramString + l1 / 1024L + "." + l1 % 1024L / 10L % 100L + " KB";
                continue;
              }
              paramString = paramString + l1 / 1048576L + "." + l1 % 1048576L / 10L % 100L + " MB";
              continue;
            }
          }
          paramString = new Response("200 OK", "text/html", paramFile + "</body></html>");
          paramFile = localFile;
          break;
        }
        paramString = new Response("403 Forbidden", "text/plain", "FORBIDDEN: No directory listing.");
        paramFile = localFile;
        break;
        l3 = l2;
        if (l2 < 0L) {
          l3 = l5 - 1L;
        }
        l4 = l3 - l1 + 1L;
        l2 = l4;
        if (l4 < 0L) {
          l2 = 0L;
        }
        paramString = new FileInputStream(paramFile)
        {
          public int available()
            throws IOException
          {
            return (int)l2;
          }
        };
        paramString.skip(l1);
        paramString = new Response("206 Partial Content", (String)localObject1, paramString);
        paramString.addHeader("Content-Length", "" + l2);
        paramString.addHeader("Content-Range", "bytes " + l1 + "-" + l3 + "/" + l5);
        paramString.addHeader("ETag", (String)localObject3);
        continue;
        paramString = new Response("403 Forbidden", "text/plain", "FORBIDDEN: Reading file failed.");
        continue;
      }
      if (((String)localObject3).equals(paramProperties.getProperty("if-none-match")))
      {
        paramString = new Response("304 Not Modified", (String)localObject1, "");
      }
      else
      {
        paramString = new Response("200 OK", (String)localObject1, new FileInputStream(paramFile));
        paramString.addHeader("Content-Length", "" + l5);
        paramString.addHeader("ETag", (String)localObject3);
        continue;
        label1660:
        continue;
        paramString = (String)localObject2;
        paramFile = localFile;
        break;
        label1672:
        localObject1 = paramString;
        if (paramString == null) {
          localObject1 = "application/octet-stream";
        }
      }
    }
  }
  
  public void stop()
  {
    try
    {
      this.myServerSocket.close();
      this.myThread.join();
      return;
    }
    catch (InterruptedException localInterruptedException) {}catch (IOException localIOException) {}
  }
  
  private class HTTPSession
    implements Runnable
  {
    private Socket mySocket;
    
    public HTTPSession(Socket paramSocket)
    {
      this.mySocket = paramSocket;
      Log.d("AppInvHTTPD", "NanoHTTPD: getPoolSize() = " + NanoHTTPD.this.myExecutor.getPoolSize());
      NanoHTTPD.this.myExecutor.execute(this);
    }
    
    private void decodeHeader(BufferedReader paramBufferedReader, Properties paramProperties1, Properties paramProperties2, Properties paramProperties3)
      throws InterruptedException
    {
      try
      {
        Object localObject = paramBufferedReader.readLine();
        if (localObject == null) {
          return;
        }
        localObject = new StringTokenizer((String)localObject);
        if (!((StringTokenizer)localObject).hasMoreTokens()) {
          sendError("400 Bad Request", "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
        }
        paramProperties1.put("method", ((StringTokenizer)localObject).nextToken());
        if (!((StringTokenizer)localObject).hasMoreTokens()) {
          sendError("400 Bad Request", "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
        }
        String str = ((StringTokenizer)localObject).nextToken();
        int i = str.indexOf('?');
        if (i >= 0) {
          decodeParms(str.substring(i + 1), paramProperties2);
        }
        for (paramProperties2 = decodePercent(str.substring(0, i)); ((StringTokenizer)localObject).hasMoreTokens(); paramProperties2 = decodePercent(str)) {
          for (localObject = paramBufferedReader.readLine(); (localObject != null) && (((String)localObject).trim().length() > 0); localObject = paramBufferedReader.readLine())
          {
            i = ((String)localObject).indexOf(':');
            if (i >= 0) {
              paramProperties3.put(((String)localObject).substring(0, i).trim().toLowerCase(), ((String)localObject).substring(i + 1).trim());
            }
          }
        }
        paramProperties1.put("uri", paramProperties2);
        return;
      }
      catch (IOException paramBufferedReader)
      {
        sendError("500 Internal Server Error", "SERVER INTERNAL ERROR: IOException: " + paramBufferedReader.getMessage());
      }
    }
    
    private void decodeMultipartData(String paramString, byte[] paramArrayOfByte, BufferedReader paramBufferedReader, Properties paramProperties1, Properties paramProperties2)
      throws InterruptedException
    {
      for (;;)
      {
        int[] arrayOfInt;
        int j;
        try
        {
          arrayOfInt = getBoundaryPositions(paramArrayOfByte, paramString.getBytes());
          i = 1;
          localObject3 = paramBufferedReader.readLine();
          if (localObject3 != null)
          {
            if (((String)localObject3).indexOf(paramString) == -1) {
              sendError("400 Bad Request", "BAD REQUEST: Content type is multipart/form-data but next chunk does not start with boundary. Usage: GET /example/file.html");
            }
            j = i + 1;
            localObject4 = new Properties();
            localObject1 = paramBufferedReader.readLine();
            if ((localObject1 != null) && (((String)localObject1).trim().length() > 0))
            {
              i = ((String)localObject1).indexOf(':');
              if (i != -1) {
                ((Properties)localObject4).put(((String)localObject1).substring(0, i).trim().toLowerCase(), ((String)localObject1).substring(i + 1).trim());
              }
              localObject1 = paramBufferedReader.readLine();
              continue;
            }
            i = j;
            localObject3 = localObject1;
            if (localObject1 == null) {
              continue;
            }
            localObject2 = ((Properties)localObject4).getProperty("content-disposition");
            if (localObject2 == null) {
              sendError("400 Bad Request", "BAD REQUEST: Content type is multipart/form-data but no content-disposition info found. Usage: GET /example/file.html");
            }
            localObject2 = new StringTokenizer((String)localObject2, "; ");
            localObject3 = new Properties();
            if (((StringTokenizer)localObject2).hasMoreTokens())
            {
              str = ((StringTokenizer)localObject2).nextToken();
              i = str.indexOf('=');
              if (i == -1) {
                continue;
              }
              ((Properties)localObject3).put(str.substring(0, i).trim().toLowerCase(), str.substring(i + 1).trim());
              continue;
            }
          }
          else
          {
            return;
          }
        }
        catch (IOException paramString)
        {
          sendError("500 Internal Server Error", "SERVER INTERNAL ERROR: IOException: " + paramString.getMessage());
        }
        Object localObject2 = ((Properties)localObject3).getProperty("name");
        String str = ((String)localObject2).substring(1, ((String)localObject2).length() - 1);
        localObject2 = "";
        if (((Properties)localObject4).getProperty("content-type") == null) {
          for (;;)
          {
            localObject3 = localObject1;
            localObject4 = localObject2;
            if (localObject1 == null) {
              break;
            }
            localObject3 = localObject1;
            localObject4 = localObject2;
            if (((String)localObject1).indexOf(paramString) != -1) {
              break;
            }
            localObject3 = paramBufferedReader.readLine();
            localObject1 = localObject3;
            if (localObject3 != null)
            {
              i = ((String)localObject3).indexOf(paramString);
              if (i == -1)
              {
                localObject2 = (String)localObject2 + (String)localObject3;
                localObject1 = localObject3;
              }
              else
              {
                localObject2 = (String)localObject2 + ((String)localObject3).substring(0, i - 2);
                localObject1 = localObject3;
              }
            }
          }
        }
        if (j > arrayOfInt.length) {
          sendError("500 Internal Server Error", "Error processing request");
        }
        int i = stripMultipartHeaders(paramArrayOfByte, arrayOfInt[(j - 2)]);
        paramProperties2.put(str, saveTmpFile(paramArrayOfByte, i, arrayOfInt[(j - 1)] - i - 4));
        Object localObject1 = ((Properties)localObject3).getProperty("filename");
        localObject1 = ((String)localObject1).substring(1, ((String)localObject1).length() - 1);
        do
        {
          localObject2 = paramBufferedReader.readLine();
          localObject3 = localObject2;
          localObject4 = localObject1;
          if (localObject2 == null) {
            break;
          }
        } while (((String)localObject2).indexOf(paramString) == -1);
        Object localObject4 = localObject1;
        Object localObject3 = localObject2;
        paramProperties1.put(str, localObject4);
        i = j;
      }
    }
    
    private void decodeParms(String paramString, Properties paramProperties)
      throws InterruptedException
    {
      if (paramString == null) {}
      for (;;)
      {
        return;
        paramString = new StringTokenizer(paramString, "&");
        while (paramString.hasMoreTokens())
        {
          String str = paramString.nextToken();
          int i = str.indexOf('=');
          if (i >= 0) {
            paramProperties.put(decodePercent(str.substring(0, i)).trim(), decodePercent(str.substring(i + 1)));
          }
        }
      }
    }
    
    private String decodePercent(String paramString)
      throws InterruptedException
    {
      for (;;)
      {
        StringBuffer localStringBuffer;
        int i;
        try
        {
          localStringBuffer = new StringBuffer();
          i = 0;
          if (i >= paramString.length()) {
            break label118;
          }
          char c = paramString.charAt(i);
          switch (c)
          {
          case '+': 
            localStringBuffer.append(c);
          }
        }
        catch (Exception paramString)
        {
          sendError("400 Bad Request", "BAD REQUEST: Bad percent-encoding.");
          return null;
        }
        localStringBuffer.append(' ');
        break label128;
        localStringBuffer.append((char)Integer.parseInt(paramString.substring(i + 1, i + 3), 16));
        i += 2;
        break label128;
        label118:
        paramString = localStringBuffer.toString();
        return paramString;
        continue;
        label128:
        i += 1;
      }
    }
    
    private String saveTmpFile(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      Object localObject = "";
      if (paramInt2 > 0) {
        localObject = System.getProperty("java.io.tmpdir");
      }
      try
      {
        localObject = File.createTempFile("NanoHTTPD", "", new File((String)localObject));
        FileOutputStream localFileOutputStream = new FileOutputStream((File)localObject);
        localFileOutputStream.write(paramArrayOfByte, paramInt1, paramInt2);
        localFileOutputStream.close();
        localObject = ((File)localObject).getAbsolutePath();
        return (String)localObject;
      }
      catch (Exception paramArrayOfByte)
      {
        NanoHTTPD.myErr.println("Error: " + paramArrayOfByte.getMessage());
      }
      return "";
    }
    
    private void sendError(String paramString1, String paramString2)
      throws InterruptedException
    {
      sendResponse(paramString1, "text/plain", null, new ByteArrayInputStream(paramString2.getBytes()));
      throw new InterruptedException();
    }
    
    private void sendResponse(String paramString1, String paramString2, Properties paramProperties, InputStream paramInputStream)
    {
      if (paramString1 == null) {
        try
        {
          throw new Error("sendResponse(): Status can't be null.");
        }
        catch (IOException paramString1) {}
      }
      try
      {
        this.mySocket.close();
        return;
      }
      catch (Throwable paramString1)
      {
        OutputStream localOutputStream;
        PrintWriter localPrintWriter;
        return;
      }
      localOutputStream = this.mySocket.getOutputStream();
      localPrintWriter = new PrintWriter(localOutputStream);
      localPrintWriter.print("HTTP/1.0 " + paramString1 + " \r\n");
      if (paramString2 != null) {
        localPrintWriter.print("Content-Type: " + paramString2 + "\r\n");
      }
      if ((paramProperties == null) || (paramProperties.getProperty("Date") == null)) {
        localPrintWriter.print("Date: " + NanoHTTPD.gmtFrmt.format(new Date()) + "\r\n");
      }
      if (paramProperties != null)
      {
        paramString1 = paramProperties.keys();
        while (paramString1.hasMoreElements())
        {
          paramString2 = (String)paramString1.nextElement();
          String str = paramProperties.getProperty(paramString2);
          localPrintWriter.print(paramString2 + ": " + str + "\r\n");
        }
      }
      localPrintWriter.print("\r\n");
      localPrintWriter.flush();
      int i;
      if (paramInputStream != null)
      {
        i = paramInputStream.available();
        paramString1 = new byte[NanoHTTPD.theBufferSize];
        label272:
        if (i > 0) {
          if (i <= NanoHTTPD.theBufferSize) {
            break label348;
          }
        }
      }
      label348:
      for (int j = NanoHTTPD.theBufferSize;; j = i)
      {
        j = paramInputStream.read(paramString1, 0, j);
        if (j <= 0)
        {
          localOutputStream.flush();
          localOutputStream.close();
          if (paramInputStream == null) {
            break;
          }
          paramInputStream.close();
          return;
        }
        localOutputStream.write(paramString1, 0, j);
        i -= j;
        break label272;
      }
    }
    
    private int stripMultipartHeaders(byte[] paramArrayOfByte, int paramInt)
    {
      for (int i = paramInt;; i = paramInt + 1)
      {
        paramInt = i;
        if (i < paramArrayOfByte.length)
        {
          paramInt = i;
          if (paramArrayOfByte[i] == 13)
          {
            i += 1;
            paramInt = i;
            if (paramArrayOfByte[i] == 10)
            {
              i += 1;
              paramInt = i;
              if (paramArrayOfByte[i] == 13)
              {
                i += 1;
                paramInt = i;
                if (paramArrayOfByte[i] == 10) {
                  paramInt = i;
                }
              }
            }
          }
        }
        else
        {
          return paramInt + 1;
        }
      }
    }
    
    public int[] getBoundaryPositions(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    {
      int j = 0;
      int k = -1;
      Vector localVector = new Vector();
      int i = 0;
      if (i < paramArrayOfByte1.length)
      {
        int n;
        if (paramArrayOfByte1[i] == paramArrayOfByte2[j])
        {
          int m = k;
          if (j == 0) {
            m = i;
          }
          int i1 = j + 1;
          n = i;
          k = m;
          j = i1;
          if (i1 == paramArrayOfByte2.length)
          {
            localVector.addElement(new Integer(m));
            j = 0;
            k = -1;
            n = i;
          }
        }
        for (;;)
        {
          i = n + 1;
          break;
          n = i - j;
          j = 0;
          k = -1;
        }
      }
      paramArrayOfByte1 = new int[localVector.size()];
      i = 0;
      while (i < paramArrayOfByte1.length)
      {
        paramArrayOfByte1[i] = ((Integer)localVector.elementAt(i)).intValue();
        i += 1;
      }
      return paramArrayOfByte1;
    }
    
    public void run()
    {
      for (;;)
      {
        try
        {
          localInputStream = this.mySocket.getInputStream();
          if (localInputStream == null) {
            return;
          }
          arrayOfByte1 = new byte[' '];
          m = localInputStream.read(arrayOfByte1, 0, 8192);
          if (m <= 0) {
            continue;
          }
          localObject2 = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(arrayOfByte1, 0, m)));
          localObject3 = new Properties();
          localProperties1 = new Properties();
          localProperties2 = new Properties();
          localProperties3 = new Properties();
          decodeHeader((BufferedReader)localObject2, (Properties)localObject3, localProperties1, localProperties2);
          localObject2 = ((Properties)localObject3).getProperty("method");
          localObject3 = ((Properties)localObject3).getProperty("uri");
          l1 = Long.MAX_VALUE;
          localObject4 = localProperties2.getProperty("content-length");
          l2 = l1;
          if (localObject4 == null) {}
        }
        catch (IOException localIOException)
        {
          InputStream localInputStream;
          int m;
          Object localObject2;
          Object localObject3;
          Properties localProperties1;
          Properties localProperties2;
          Properties localProperties3;
          Object localObject4;
          long l2;
          try
          {
            byte[] arrayOfByte1;
            int n;
            int k;
            sendError("500 Internal Server Error", "SERVER INTERNAL ERROR: IOException: " + localIOException.getMessage());
            return;
          }
          catch (Throwable localThrowable)
          {
            return;
          }
          int j = i + 1;
          continue;
          if (i != 0)
          {
            l1 = l2;
            if (l2 != Long.MAX_VALUE) {
              continue;
            }
          }
          long l1 = 0L;
          continue;
          byte[] arrayOfByte2 = ((ByteArrayOutputStream)localObject4).toByteArray();
          BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(arrayOfByte2)));
          if (((String)localObject2).equalsIgnoreCase("POST"))
          {
            localObject1 = "";
            localObject5 = new StringTokenizer(localProperties2.getProperty("content-type"), "; ");
            if (((StringTokenizer)localObject5).hasMoreTokens()) {
              localObject1 = ((StringTokenizer)localObject5).nextToken();
            }
            if (((String)localObject1).equalsIgnoreCase("multipart/form-data"))
            {
              if (!((StringTokenizer)localObject5).hasMoreTokens()) {
                sendError("400 Bad Request", "BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html");
              }
              localObject1 = new StringTokenizer(((StringTokenizer)localObject5).nextToken(), "=");
              if (((StringTokenizer)localObject1).countTokens() != 2) {
                sendError("400 Bad Request", "BAD REQUEST: Content type is multipart/form-data but boundary syntax error. Usage: GET /example/file.html");
              }
              ((StringTokenizer)localObject1).nextToken();
              decodeMultipartData(((StringTokenizer)localObject1).nextToken(), arrayOfByte2, localBufferedReader, localProperties1, localProperties3);
            }
          }
          else
          {
            if (((String)localObject2).equalsIgnoreCase("PUT")) {
              localProperties3.put("content", saveTmpFile(arrayOfByte2, 0, ((ByteArrayOutputStream)localObject4).size()));
            }
            localObject1 = NanoHTTPD.this.serve((String)localObject3, (String)localObject2, localProperties2, localProperties1, localProperties3, this.mySocket);
            if (localObject1 != null) {
              continue;
            }
            sendError("500 Internal Server Error", "SERVER INTERNAL ERROR: Serve() returned a null response.");
            localBufferedReader.close();
            localInputStream.close();
            return;
          }
          Object localObject1 = "";
          Object localObject5 = new char['Ȁ'];
          int i = localBufferedReader.read((char[])localObject5);
          if ((i >= 0) && (!((String)localObject1).endsWith("\r\n")))
          {
            localObject1 = (String)localObject1 + String.valueOf((char[])localObject5, 0, i);
            i = localBufferedReader.read((char[])localObject5);
            continue;
          }
          decodeParms(((String)localObject1).trim(), localProperties1);
          continue;
          sendResponse(((NanoHTTPD.Response)localObject1).status, ((NanoHTTPD.Response)localObject1).mimeType, ((NanoHTTPD.Response)localObject1).header, ((NanoHTTPD.Response)localObject1).data);
          continue;
          return;
          if (j >= m) {
            continue;
          }
          l1 = l2 - (m - j + 1);
          continue;
        }
        catch (InterruptedException localInterruptedException) {}
        try
        {
          i = Integer.parseInt((String)localObject4);
          l2 = i;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          l2 = l1;
        }
      }
      j = 0;
      n = 0;
      i = n;
      k = j;
      if (j < m)
      {
        i = j;
        if (arrayOfByte1[j] == 13)
        {
          j += 1;
          i = j;
          if (arrayOfByte1[j] == 10)
          {
            j += 1;
            i = j;
            if (arrayOfByte1[j] == 13)
            {
              k = j + 1;
              i = k;
              if (arrayOfByte1[k] == 10) {
                i = 1;
              }
            }
          }
        }
      }
      else
      {
        j = k + 1;
        localObject4 = new ByteArrayOutputStream();
        if (j >= m) {
          break label784;
        }
        ((ByteArrayOutputStream)localObject4).write(arrayOfByte1, j, m - j);
        break label784;
        arrayOfByte1 = new byte['Ȁ'];
        i = m;
        while ((i >= 0) && (l1 > 0L))
        {
          j = localInputStream.read(arrayOfByte1, 0, 512);
          l2 = l1 - j;
          i = j;
          l1 = l2;
          if (j > 0)
          {
            ((ByteArrayOutputStream)localObject4).write(arrayOfByte1, 0, j);
            i = j;
            l1 = l2;
          }
        }
      }
      label784:
    }
  }
  
  public class Response
  {
    public InputStream data;
    public Properties header = new Properties();
    public String mimeType;
    public String status;
    
    public Response()
    {
      this.status = "200 OK";
    }
    
    public Response(String paramString1, String paramString2, InputStream paramInputStream)
    {
      this.status = paramString1;
      this.mimeType = paramString2;
      this.data = paramInputStream;
    }
    
    public Response(String paramString1, String paramString2, String paramString3)
    {
      this.status = paramString1;
      this.mimeType = paramString2;
      try
      {
        this.data = new ByteArrayInputStream(paramString3.getBytes("UTF-8"));
        return;
      }
      catch (UnsupportedEncodingException this$1)
      {
        NanoHTTPD.this.printStackTrace();
      }
    }
    
    public void addHeader(String paramString1, String paramString2)
    {
      this.header.put(paramString1, paramString2);
    }
  }
  
  private class myThreadFactory
    implements ThreadFactory
  {
    private myThreadFactory() {}
    
    public Thread newThread(Runnable paramRunnable)
    {
      paramRunnable = new Thread(new ThreadGroup("biggerstack"), paramRunnable, "HTTPD Session", 262144L);
      paramRunnable.setDaemon(true);
      return paramRunnable;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\NanoHTTPD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */