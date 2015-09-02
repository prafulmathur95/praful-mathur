package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.HtmlEntities;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.FileUtil.FileException;
import com.google.appinventor.components.runtime.util.GingerbreadUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

@DesignerComponent(category=ComponentCategory.CONNECTIVITY, description="Non-visible component that provides functions for HTTP GET, POST, PUT, and DELETE requests.", iconName="images/web.png", nonVisible=true, version=4)
@SimpleObject
@UsesLibraries(libraries="json.jar")
@UsesPermissions(permissionNames="android.permission.INTERNET")
public class Web
  extends AndroidNonvisibleComponent
  implements Component
{
  private static final String LOG_TAG = "Web";
  private static final Map<String, String> mimeTypeToExtension = ;
  private final Activity activity;
  private boolean allowCookies;
  private final CookieHandler cookieHandler;
  private YailList requestHeaders = new YailList();
  private String responseFileName = "";
  private boolean saveResponse;
  private String urlString = "";
  
  static
  {
    mimeTypeToExtension.put("application/pdf", "pdf");
    mimeTypeToExtension.put("application/zip", "zip");
    mimeTypeToExtension.put("audio/mpeg", "mpeg");
    mimeTypeToExtension.put("audio/mp3", "mp3");
    mimeTypeToExtension.put("audio/mp4", "mp4");
    mimeTypeToExtension.put("image/gif", "gif");
    mimeTypeToExtension.put("image/jpeg", "jpg");
    mimeTypeToExtension.put("image/png", "png");
    mimeTypeToExtension.put("image/tiff", "tiff");
    mimeTypeToExtension.put("text/plain", "txt");
    mimeTypeToExtension.put("text/html", "html");
    mimeTypeToExtension.put("text/xml", "xml");
  }
  
  protected Web()
  {
    super(null);
    this.activity = null;
    this.cookieHandler = null;
  }
  
  public Web(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.activity = paramComponentContainer.$context();
    if (SdkLevel.getLevel() >= 9) {}
    for (paramComponentContainer = GingerbreadUtil.newCookieManager();; paramComponentContainer = null)
    {
      this.cookieHandler = paramComponentContainer;
      return;
    }
  }
  
  private CapturedProperties capturePropertyValues(String paramString)
  {
    try
    {
      CapturedProperties localCapturedProperties = new CapturedProperties(this);
      return localCapturedProperties;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      this.form.dispatchErrorOccurredEvent(this, paramString, 1109, new Object[] { this.urlString });
      return null;
    }
    catch (InvalidRequestHeadersException localInvalidRequestHeadersException)
    {
      for (;;)
      {
        this.form.dispatchErrorOccurredEvent(this, paramString, localInvalidRequestHeadersException.errorNumber, new Object[] { Integer.valueOf(localInvalidRequestHeadersException.index) });
      }
    }
  }
  
  private static File createFile(String paramString1, String paramString2)
    throws IOException, FileUtil.FileException
  {
    if (!TextUtils.isEmpty(paramString1)) {
      return FileUtil.getExternalFile(paramString1);
    }
    int i = paramString2.indexOf(';');
    paramString1 = paramString2;
    if (i != -1) {
      paramString1 = paramString2.substring(0, i);
    }
    paramString2 = (String)mimeTypeToExtension.get(paramString1);
    paramString1 = paramString2;
    if (paramString2 == null) {
      paramString1 = "tmp";
    }
    return FileUtil.getDownloadFile(paramString1);
  }
  
  static Object decodeJsonText(String paramString)
    throws IllegalArgumentException
  {
    try
    {
      paramString = JsonUtil.getObjectFromJson(paramString);
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new IllegalArgumentException("jsonText is not a legal JSON value");
    }
  }
  
  private static InputStream getConnectionStream(HttpURLConnection paramHttpURLConnection)
  {
    try
    {
      InputStream localInputStream = paramHttpURLConnection.getInputStream();
      return localInputStream;
    }
    catch (IOException localIOException) {}
    return paramHttpURLConnection.getErrorStream();
  }
  
  private static String getResponseContent(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    Object localObject2 = paramHttpURLConnection.getContentEncoding();
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "UTF-8";
    }
    localObject1 = new InputStreamReader(getConnectionStream(paramHttpURLConnection), (String)localObject1);
    for (;;)
    {
      try
      {
        int i = paramHttpURLConnection.getContentLength();
        if (i != -1)
        {
          paramHttpURLConnection = new StringBuilder(i);
          localObject2 = new char['Ѐ'];
          i = ((InputStreamReader)localObject1).read((char[])localObject2);
          if (i == -1) {
            break;
          }
          paramHttpURLConnection.append((char[])localObject2, 0, i);
          continue;
        }
        paramHttpURLConnection = new StringBuilder();
      }
      finally
      {
        ((InputStreamReader)localObject1).close();
      }
    }
    paramHttpURLConnection = paramHttpURLConnection.toString();
    ((InputStreamReader)localObject1).close();
    return paramHttpURLConnection;
  }
  
  private static String getResponseType(HttpURLConnection paramHttpURLConnection)
  {
    paramHttpURLConnection = paramHttpURLConnection.getContentType();
    if (paramHttpURLConnection != null) {
      return paramHttpURLConnection;
    }
    return "";
  }
  
  private static HttpURLConnection openConnection(CapturedProperties paramCapturedProperties, String paramString)
    throws IOException, ClassCastException, ProtocolException
  {
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)paramCapturedProperties.url.openConnection();
    if ((paramString.equals("PUT")) || (paramString.equals("DELETE"))) {
      localHttpURLConnection.setRequestMethod(paramString);
    }
    paramString = paramCapturedProperties.requestHeaders.entrySet().iterator();
    Object localObject1;
    while (paramString.hasNext())
    {
      Object localObject2 = (Map.Entry)paramString.next();
      localObject1 = (String)((Map.Entry)localObject2).getKey();
      localObject2 = ((List)((Map.Entry)localObject2).getValue()).iterator();
      while (((Iterator)localObject2).hasNext()) {
        localHttpURLConnection.addRequestProperty((String)localObject1, (String)((Iterator)localObject2).next());
      }
    }
    if (paramCapturedProperties.cookies != null)
    {
      paramCapturedProperties = paramCapturedProperties.cookies.entrySet().iterator();
      while (paramCapturedProperties.hasNext())
      {
        localObject1 = (Map.Entry)paramCapturedProperties.next();
        paramString = (String)((Map.Entry)localObject1).getKey();
        localObject1 = ((List)((Map.Entry)localObject1).getValue()).iterator();
        while (((Iterator)localObject1).hasNext()) {
          localHttpURLConnection.addRequestProperty(paramString, (String)((Iterator)localObject1).next());
        }
      }
    }
    return localHttpURLConnection;
  }
  
  private void performRequest(final CapturedProperties paramCapturedProperties, final byte[] paramArrayOfByte, final String paramString1, String paramString2)
    throws IOException
  {
    paramString2 = openConnection(paramCapturedProperties, paramString2);
    if ((paramString2 == null) || (paramArrayOfByte != null)) {}
    for (;;)
    {
      final int i;
      try
      {
        writeRequestData(paramString2, paramArrayOfByte);
        i = paramString2.getResponseCode();
        paramArrayOfByte = getResponseType(paramString2);
        processResponseCookies(paramString2);
        if (!this.saveResponse) {
          break label108;
        }
        paramString1 = saveResponseContent(paramString2, paramCapturedProperties.responseFileName, paramArrayOfByte);
        this.activity.runOnUiThread(new Runnable()
        {
          public void run()
          {
            Web.this.GotFile(paramCapturedProperties.urlString, i, paramArrayOfByte, paramString1);
          }
        });
        return;
      }
      finally
      {
        paramString2.disconnect();
      }
      if (paramString1 != null)
      {
        writeRequestFile(paramString2, paramString1);
        continue;
        label108:
        paramString1 = getResponseContent(paramString2);
        this.activity.runOnUiThread(new Runnable()
        {
          public void run()
          {
            Web.this.GotText(paramCapturedProperties.urlString, i, paramArrayOfByte, paramString1);
          }
        });
      }
    }
  }
  
  private static Map<String, List<String>> processRequestHeaders(YailList paramYailList)
    throws Web.InvalidRequestHeadersException
  {
    HashMap localHashMap = Maps.newHashMap();
    int i = 0;
    while (i < paramYailList.size())
    {
      Object localObject1 = paramYailList.getObject(i);
      if ((localObject1 instanceof YailList))
      {
        Object localObject2 = (YailList)localObject1;
        if (((YailList)localObject2).size() == 2)
        {
          localObject1 = ((YailList)localObject2).getObject(0).toString();
          Object localObject3 = ((YailList)localObject2).getObject(1);
          localObject2 = Lists.newArrayList();
          if ((localObject3 instanceof YailList))
          {
            localObject3 = (YailList)localObject3;
            int j = 0;
            while (j < ((YailList)localObject3).size())
            {
              ((List)localObject2).add(((YailList)localObject3).getObject(j).toString());
              j += 1;
            }
          }
          ((List)localObject2).add(localObject3.toString());
          localHashMap.put(localObject1, localObject2);
          i += 1;
        }
        else
        {
          throw new InvalidRequestHeadersException(1111, i + 1);
        }
      }
      else
      {
        throw new InvalidRequestHeadersException(1110, i + 1);
      }
    }
    return localHashMap;
  }
  
  private void processResponseCookies(HttpURLConnection paramHttpURLConnection)
  {
    if ((this.allowCookies) && (this.cookieHandler != null)) {}
    try
    {
      Map localMap = paramHttpURLConnection.getHeaderFields();
      this.cookieHandler.put(paramHttpURLConnection.getURL().toURI(), localMap);
      return;
    }
    catch (IOException paramHttpURLConnection) {}catch (URISyntaxException paramHttpURLConnection) {}
  }
  
  private void requestTextImpl(final String paramString1, final String paramString2, final String paramString3, final String paramString4)
  {
    final CapturedProperties localCapturedProperties = capturePropertyValues(paramString3);
    if (localCapturedProperties == null) {
      return;
    }
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          try
          {
            if ((paramString2 == null) || (paramString2.length() == 0)) {
              arrayOfByte = paramString1.getBytes("UTF-8");
            }
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException)
          {
            byte[] arrayOfByte;
            Web.this.form.dispatchErrorOccurredEvent(Web.this, paramString3, 1102, new Object[] { paramString2 });
            return;
          }
          try
          {
            Web.this.performRequest(localCapturedProperties, arrayOfByte, null, paramString4);
            return;
          }
          catch (FileUtil.FileException localFileException)
          {
            Web.this.form.dispatchErrorOccurredEvent(Web.this, paramString3, localFileException.getErrorMessageNumber(), new Object[0]);
            return;
          }
          catch (Exception localException)
          {
            Web.this.form.dispatchErrorOccurredEvent(Web.this, paramString3, 1103, new Object[] { paramString1, localCapturedProperties.urlString });
          }
          arrayOfByte = paramString1.getBytes(paramString2);
        }
      }
    });
  }
  
  /* Error */
  private static String saveResponseContent(HttpURLConnection paramHttpURLConnection, String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: invokestatic 498	com/google/appinventor/components/runtime/Web:createFile	(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;
    //   5: astore_1
    //   6: new 500	java/io/BufferedInputStream
    //   9: dup
    //   10: aload_0
    //   11: invokestatic 308	com/google/appinventor/components/runtime/Web:getConnectionStream	(Ljava/net/HttpURLConnection;)Ljava/io/InputStream;
    //   14: sipush 4096
    //   17: invokespecial 503	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;I)V
    //   20: astore_0
    //   21: new 505	java/io/BufferedOutputStream
    //   24: dup
    //   25: new 507	java/io/FileOutputStream
    //   28: dup
    //   29: aload_1
    //   30: invokespecial 510	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   33: sipush 4096
    //   36: invokespecial 513	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   39: astore_2
    //   40: aload_0
    //   41: invokevirtual 515	java/io/BufferedInputStream:read	()I
    //   44: istore_3
    //   45: iload_3
    //   46: iconst_m1
    //   47: if_icmpne +20 -> 67
    //   50: aload_2
    //   51: invokevirtual 518	java/io/BufferedOutputStream:flush	()V
    //   54: aload_2
    //   55: invokevirtual 519	java/io/BufferedOutputStream:close	()V
    //   58: aload_0
    //   59: invokevirtual 520	java/io/BufferedInputStream:close	()V
    //   62: aload_1
    //   63: invokevirtual 525	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   66: areturn
    //   67: aload_2
    //   68: iload_3
    //   69: invokevirtual 528	java/io/BufferedOutputStream:write	(I)V
    //   72: goto -32 -> 40
    //   75: astore_1
    //   76: aload_2
    //   77: invokevirtual 519	java/io/BufferedOutputStream:close	()V
    //   80: aload_1
    //   81: athrow
    //   82: astore_1
    //   83: aload_0
    //   84: invokevirtual 520	java/io/BufferedInputStream:close	()V
    //   87: aload_1
    //   88: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	89	0	paramHttpURLConnection	HttpURLConnection
    //   0	89	1	paramString1	String
    //   0	89	2	paramString2	String
    //   44	25	3	i	int
    // Exception table:
    //   from	to	target	type
    //   40	45	75	finally
    //   50	54	75	finally
    //   67	72	75	finally
    //   21	40	82	finally
    //   54	58	82	finally
    //   76	82	82	finally
  }
  
  private static void writeRequestData(HttpURLConnection paramHttpURLConnection, byte[] paramArrayOfByte)
    throws IOException
  {
    paramHttpURLConnection.setDoOutput(true);
    paramHttpURLConnection.setFixedLengthStreamingMode(paramArrayOfByte.length);
    paramHttpURLConnection = new BufferedOutputStream(paramHttpURLConnection.getOutputStream());
    try
    {
      paramHttpURLConnection.write(paramArrayOfByte, 0, paramArrayOfByte.length);
      paramHttpURLConnection.flush();
      return;
    }
    finally
    {
      paramHttpURLConnection.close();
    }
  }
  
  /* Error */
  private void writeRequestFile(HttpURLConnection paramHttpURLConnection, String paramString)
    throws IOException
  {
    // Byte code:
    //   0: new 500	java/io/BufferedInputStream
    //   3: dup
    //   4: aload_0
    //   5: getfield 213	com/google/appinventor/components/runtime/Web:form	Lcom/google/appinventor/components/runtime/Form;
    //   8: aload_2
    //   9: invokestatic 551	com/google/appinventor/components/runtime/util/MediaUtil:openMedia	(Lcom/google/appinventor/components/runtime/Form;Ljava/lang/String;)Ljava/io/InputStream;
    //   12: invokespecial 554	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   15: astore_2
    //   16: aload_1
    //   17: iconst_1
    //   18: invokevirtual 532	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   21: aload_1
    //   22: iconst_0
    //   23: invokevirtual 557	java/net/HttpURLConnection:setChunkedStreamingMode	(I)V
    //   26: new 505	java/io/BufferedOutputStream
    //   29: dup
    //   30: aload_1
    //   31: invokevirtual 539	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   34: invokespecial 542	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   37: astore_1
    //   38: aload_2
    //   39: invokevirtual 515	java/io/BufferedInputStream:read	()I
    //   42: istore 4
    //   44: iload 4
    //   46: iconst_m1
    //   47: if_icmpne +16 -> 63
    //   50: aload_1
    //   51: invokevirtual 518	java/io/BufferedOutputStream:flush	()V
    //   54: aload_1
    //   55: invokevirtual 519	java/io/BufferedOutputStream:close	()V
    //   58: aload_2
    //   59: invokevirtual 520	java/io/BufferedInputStream:close	()V
    //   62: return
    //   63: aload_1
    //   64: iload 4
    //   66: invokevirtual 528	java/io/BufferedOutputStream:write	(I)V
    //   69: goto -31 -> 38
    //   72: astore_3
    //   73: aload_1
    //   74: invokevirtual 519	java/io/BufferedOutputStream:close	()V
    //   77: aload_3
    //   78: athrow
    //   79: astore_1
    //   80: aload_2
    //   81: invokevirtual 520	java/io/BufferedInputStream:close	()V
    //   84: aload_1
    //   85: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	this	Web
    //   0	86	1	paramHttpURLConnection	HttpURLConnection
    //   0	86	2	paramString	String
    //   72	6	3	localObject	Object
    //   42	23	4	i	int
    // Exception table:
    //   from	to	target	type
    //   38	44	72	finally
    //   50	54	72	finally
    //   63	69	72	finally
    //   16	38	79	finally
    //   54	58	79	finally
    //   73	79	79	finally
  }
  
  @DesignerProperty(defaultValue="false", editorType="boolean")
  @SimpleProperty
  public void AllowCookies(boolean paramBoolean)
  {
    this.allowCookies = paramBoolean;
    if ((paramBoolean) && (this.cookieHandler == null)) {
      this.form.dispatchErrorOccurredEvent(this, "AllowCookies", 4, new Object[0]);
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Whether the cookies from a response should be saved and used in subsequent requests. Cookies are only supported on Android version 2.3 or greater.")
  public boolean AllowCookies()
  {
    return this.allowCookies;
  }
  
  @SimpleFunction
  public String BuildRequestData(YailList paramYailList)
  {
    try
    {
      paramYailList = buildRequestData(paramYailList);
      return paramYailList;
    }
    catch (BuildRequestDataException paramYailList)
    {
      this.form.dispatchErrorOccurredEvent(this, "BuildRequestData", paramYailList.errorNumber, new Object[] { Integer.valueOf(paramYailList.index) });
    }
    return "";
  }
  
  @SimpleFunction(description="Clears all cookies for this Web component.")
  public void ClearCookies()
  {
    if (this.cookieHandler != null)
    {
      GingerbreadUtil.clearCookies(this.cookieHandler);
      return;
    }
    this.form.dispatchErrorOccurredEvent(this, "ClearCookies", 4, new Object[0]);
  }
  
  @SimpleFunction
  public void Delete()
  {
    final CapturedProperties localCapturedProperties = capturePropertyValues("Delete");
    if (localCapturedProperties == null) {
      return;
    }
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        try
        {
          Web.this.performRequest(localCapturedProperties, null, null, "DELETE");
          return;
        }
        catch (FileUtil.FileException localFileException)
        {
          Web.this.form.dispatchErrorOccurredEvent(Web.this, "Delete", localFileException.getErrorMessageNumber(), new Object[0]);
          return;
        }
        catch (Exception localException)
        {
          Web.this.form.dispatchErrorOccurredEvent(Web.this, "Delete", 1114, new Object[] { localCapturedProperties.urlString });
        }
      }
    });
  }
  
  @SimpleFunction
  public void Get()
  {
    final CapturedProperties localCapturedProperties = capturePropertyValues("Get");
    if (localCapturedProperties == null) {
      return;
    }
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        try
        {
          Web.this.performRequest(localCapturedProperties, null, null, "GET");
          return;
        }
        catch (FileUtil.FileException localFileException)
        {
          Web.this.form.dispatchErrorOccurredEvent(Web.this, "Get", localFileException.getErrorMessageNumber(), new Object[0]);
          return;
        }
        catch (Exception localException)
        {
          Web.this.form.dispatchErrorOccurredEvent(Web.this, "Get", 1101, new Object[] { localCapturedProperties.urlString });
        }
      }
    });
  }
  
  @SimpleEvent
  public void GotFile(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    EventDispatcher.dispatchEvent(this, "GotFile", new Object[] { paramString1, Integer.valueOf(paramInt), paramString2, paramString3 });
  }
  
  @SimpleEvent
  public void GotText(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    EventDispatcher.dispatchEvent(this, "GotText", new Object[] { paramString1, Integer.valueOf(paramInt), paramString2, paramString3 });
  }
  
  @SimpleFunction(description="Decodes the given HTML text value. HTML character entities such as &amp;amp;, &amp;lt;, &amp;gt;, &amp;apos;, and &amp;quot; are changed to &amp;, &lt;, &gt;, &#39;, and &quot;. Entities such as &amp;#xhhhh, and &amp;#nnnn are changed to the appropriate characters.")
  public String HtmlTextDecode(String paramString)
  {
    try
    {
      String str = HtmlEntities.decodeHtmlText(paramString);
      return str;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      this.form.dispatchErrorOccurredEvent(this, "HtmlTextDecode", 1106, new Object[] { paramString });
    }
    return "";
  }
  
  @SimpleFunction
  public Object JsonTextDecode(String paramString)
  {
    try
    {
      Object localObject = decodeJsonText(paramString);
      return localObject;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      this.form.dispatchErrorOccurredEvent(this, "JsonTextDecode", 1105, new Object[] { paramString });
    }
    return "";
  }
  
  @SimpleFunction(description="Performs an HTTP POST request using the Url property and data from the specified file.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
  public void PostFile(final String paramString)
  {
    final CapturedProperties localCapturedProperties = capturePropertyValues("PostFile");
    if (localCapturedProperties == null) {
      return;
    }
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        try
        {
          Web.this.performRequest(localCapturedProperties, null, paramString, "POST");
          return;
        }
        catch (FileUtil.FileException localFileException)
        {
          Web.this.form.dispatchErrorOccurredEvent(Web.this, "PostFile", localFileException.getErrorMessageNumber(), new Object[0]);
          return;
        }
        catch (Exception localException)
        {
          Web.this.form.dispatchErrorOccurredEvent(Web.this, "PostFile", 1104, new Object[] { paramString, localCapturedProperties.urlString });
        }
      }
    });
  }
  
  @SimpleFunction(description="Performs an HTTP POST request using the Url property and the specified text.<br>The characters of the text are encoded using UTF-8 encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The responseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
  public void PostText(String paramString)
  {
    requestTextImpl(paramString, "UTF-8", "PostText", "POST");
  }
  
  @SimpleFunction(description="Performs an HTTP POST request using the Url property and the specified text.<br>The characters of the text are encoded using the given encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
  public void PostTextWithEncoding(String paramString1, String paramString2)
  {
    requestTextImpl(paramString1, paramString2, "PostTextWithEncoding", "POST");
  }
  
  @SimpleFunction(description="Performs an HTTP PUT request using the Url property and data from the specified file.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
  public void PutFile(final String paramString)
  {
    final CapturedProperties localCapturedProperties = capturePropertyValues("PutFile");
    if (localCapturedProperties == null) {
      return;
    }
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        try
        {
          Web.this.performRequest(localCapturedProperties, null, paramString, "PUT");
          return;
        }
        catch (FileUtil.FileException localFileException)
        {
          Web.this.form.dispatchErrorOccurredEvent(Web.this, "PutFile", localFileException.getErrorMessageNumber(), new Object[0]);
          return;
        }
        catch (Exception localException)
        {
          Web.this.form.dispatchErrorOccurredEvent(Web.this, "PutFile", 1104, new Object[] { paramString, localCapturedProperties.urlString });
        }
      }
    });
  }
  
  @SimpleFunction(description="Performs an HTTP PUT request using the Url property and the specified text.<br>The characters of the text are encoded using UTF-8 encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The responseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
  public void PutText(String paramString)
  {
    requestTextImpl(paramString, "UTF-8", "PutText", "PUT");
  }
  
  @SimpleFunction(description="Performs an HTTP PUT request using the Url property and the specified text.<br>The characters of the text are encoded using the given encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
  public void PutTextWithEncoding(String paramString1, String paramString2)
  {
    requestTextImpl(paramString1, paramString2, "PutTextWithEncoding", "PUT");
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The request headers, as a list of two-element sublists. The first element of each sublist represents the request header field name. The second element of each sublist represents the request header field values, either a single value or a list containing multiple values.")
  public YailList RequestHeaders()
  {
    return this.requestHeaders;
  }
  
  @SimpleProperty
  public void RequestHeaders(YailList paramYailList)
  {
    try
    {
      processRequestHeaders(paramYailList);
      this.requestHeaders = paramYailList;
      return;
    }
    catch (InvalidRequestHeadersException paramYailList)
    {
      this.form.dispatchErrorOccurredEvent(this, "RequestHeaders", paramYailList.errorNumber, new Object[] { Integer.valueOf(paramYailList.index) });
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The name of the file where the response should be saved. If SaveResponse is true and ResponseFileName is empty, then a new file name will be generated.")
  public String ResponseFileName()
  {
    return this.responseFileName;
  }
  
  @DesignerProperty(defaultValue="", editorType="string")
  @SimpleProperty
  public void ResponseFileName(String paramString)
  {
    this.responseFileName = paramString;
  }
  
  @DesignerProperty(defaultValue="false", editorType="boolean")
  @SimpleProperty
  public void SaveResponse(boolean paramBoolean)
  {
    this.saveResponse = paramBoolean;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Whether the response should be saved in a file.")
  public boolean SaveResponse()
  {
    return this.saveResponse;
  }
  
  @SimpleFunction
  public String UriEncode(String paramString)
  {
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      Log.e("Web", "UTF-8 is unsupported?", paramString);
    }
    return "";
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The URL for the web request.")
  public String Url()
  {
    return this.urlString;
  }
  
  @DesignerProperty(defaultValue="", editorType="string")
  @SimpleProperty
  public void Url(String paramString)
  {
    this.urlString = paramString;
  }
  
  @SimpleFunction(description="Decodes the given XML string to produce a list structure.  See the App Inventor documentation on \"Other topics, notes, and details\" for information.")
  public Object XMLTextDecode(String paramString)
  {
    try
    {
      paramString = JsonTextDecode(XML.toJSONObject(paramString).toString());
      return paramString;
    }
    catch (JSONException paramString)
    {
      Log.e("Exception in XMLTextDecode", paramString.getMessage());
      this.form.dispatchErrorOccurredEvent(this, "XMLTextDecode", 1105, new Object[] { paramString.getMessage() });
    }
    return YailList.makeEmptyList();
  }
  
  String buildRequestData(YailList paramYailList)
    throws Web.BuildRequestDataException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = "";
    int i = 0;
    while (i < paramYailList.size())
    {
      Object localObject1 = paramYailList.getObject(i);
      if ((localObject1 instanceof YailList))
      {
        Object localObject2 = (YailList)localObject1;
        if (((YailList)localObject2).size() == 2)
        {
          localObject1 = ((YailList)localObject2).getObject(0).toString();
          localObject2 = ((YailList)localObject2).getObject(1).toString();
          localStringBuilder.append(str).append(UriEncode((String)localObject1)).append('=').append(UriEncode((String)localObject2));
          str = "&";
          i += 1;
        }
        else
        {
          throw new BuildRequestDataException(1113, i + 1);
        }
      }
      else
      {
        throw new BuildRequestDataException(1112, i + 1);
      }
    }
    return localStringBuilder.toString();
  }
  
  static class BuildRequestDataException
    extends Exception
  {
    final int errorNumber;
    final int index;
    
    BuildRequestDataException(int paramInt1, int paramInt2)
    {
      this.errorNumber = paramInt1;
      this.index = paramInt2;
    }
  }
  
  private static class CapturedProperties
  {
    final boolean allowCookies;
    final Map<String, List<String>> cookies;
    final Map<String, List<String>> requestHeaders;
    final String responseFileName;
    final boolean saveResponse;
    final URL url;
    final String urlString;
    
    CapturedProperties(Web paramWeb)
      throws MalformedURLException, Web.InvalidRequestHeadersException
    {
      this.urlString = paramWeb.urlString;
      this.url = new URL(this.urlString);
      this.allowCookies = paramWeb.allowCookies;
      this.saveResponse = paramWeb.saveResponse;
      this.responseFileName = paramWeb.responseFileName;
      this.requestHeaders = Web.processRequestHeaders(paramWeb.requestHeaders);
      Object localObject2 = null;
      Object localObject1 = localObject2;
      if (this.allowCookies)
      {
        localObject1 = localObject2;
        if (paramWeb.cookieHandler == null) {}
      }
      try
      {
        localObject1 = paramWeb.cookieHandler.get(this.url.toURI(), this.requestHeaders);
        this.cookies = ((Map)localObject1);
        return;
      }
      catch (IOException paramWeb)
      {
        for (;;)
        {
          localObject1 = localObject2;
        }
      }
      catch (URISyntaxException paramWeb)
      {
        for (;;)
        {
          localObject1 = localObject2;
        }
      }
    }
  }
  
  private static class InvalidRequestHeadersException
    extends Exception
  {
    final int errorNumber;
    final int index;
    
    InvalidRequestHeadersException(int paramInt1, int paramInt2)
    {
      this.errorNumber = paramInt1;
      this.index = paramInt2;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\Web.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */