package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.util.Log;
import com.google.api.client.extensions.android2.AndroidHttp;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.GoogleKeyInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.fusiontables.Fusiontables;
import com.google.api.services.fusiontables.Fusiontables.Builder;
import com.google.api.services.fusiontables.Fusiontables.Query;
import com.google.api.services.fusiontables.Fusiontables.Query.Sql;
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
import com.google.appinventor.components.runtime.util.ClientLoginHelper;
import com.google.appinventor.components.runtime.util.IClientLoginHelper;
import com.google.appinventor.components.runtime.util.OAuth2Helper;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

@DesignerComponent(category=ComponentCategory.STORAGE, description="<p>A non-visible component that communicates with Google Fusion Tables. Fusion Tables let you store, share, query and visualize data tables; this component lets you query, create, and modify these tables.</p> <p>This component uses the <a href=\"https://developers.google.com/fusiontables/docs/v1/getting_started\" target=\"_blank\">Fusion Tables API V1.0</a>. <p>Applications using Fusion Tables must authentication to Google's servers. There are two ways this can be done. The first way uses an API Key which you the developer obtain (see below). With this approach end-users must also login to access a Fusion Table. The second approach is to use a Service Account. With this approach you create credentials and a special \"Service Account Email Address\" which you obtain from the <a href=\"https://code.google.com/apis/console/\" target=\"_blank\">Google APIs Console</a>. You then tell the Fusion Table Control the name of the Service Account Email address and upload the secret key as an asset to your application and set the KeyFile property to point at this file. Finally you check the \"UseServiceAuthentication\" checkbox in the designer. When using a Service Account, end-users do not need to login to use Fusion Tables, your service account authenticates all access.</p> <p>To get an API key, follow these instructions.</p> <ol><li>Go to your <a href=\"https://code.google.com/apis/console/\" target=\"_blank\">Google APIs Console</a> and login if necessary.</li><li>Select the <i>Services</i> item from the menu on the left.</li><li>Choose the <i>Fusiontables</i> service from the list provided and turn it on.</li><li>Go back to the main menu and select the <i>API Access</i> item. </li></ol><p>Your API Key will be near the bottom of that pane in the section called \"Simple API Access\".You will have to provide that key as the value for the <i>ApiKey</i> property in your Fusiontables app.</p><p>Once you have an API key, set the value of the <i>Query</i> property to a valid Fusiontables SQL query and call <i>SendQuery</i> to execute the query.  App Inventor will send the query to the Fusion Tables server and the <i>GotResult</i> block will fire when a result is returned from the server.Query results will be returned in CSV format, and can be converted to list format using the \"list from csv table\" or \"list from csv row\" blocks.</p><p>Note that you do not need to worry about UTF-encoding the query. But you do need to make sure the query follows the syntax described in <a href=\"https://developers.google.com/fusiontables/docs/v1/getting_started\" target=\"_blank\">the reference manual</a>, which means that things like capitalization for names of columns matters, and that single quotes must be used around column names if there are spaces in them.</p>", iconName="images/fusiontables.png", nonVisible=true, version=3)
@SimpleObject
@UsesLibraries(libraries="fusiontables.jar,google-api-client-beta.jar,google-api-client-android2-beta.jar,google-http-client-beta.jar,google-http-client-android2-beta.jar,google-http-client-android3-beta.jar,google-oauth-client-beta.jar,guava-14.0.1.jar,gson-2.1.jar")
@UsesPermissions(permissionNames="android.permission.INTERNET,android.permission.ACCOUNT_MANAGER,android.permission.MANAGE_ACCOUNTS,android.permission.GET_ACCOUNTS,android.permission.USE_CREDENTIALS")
public class FusiontablesControl
  extends AndroidNonvisibleComponent
  implements Component
{
  public static final String APP_NAME = "App Inventor";
  public static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
  public static final String AUTH_TOKEN_TYPE_FUSIONTABLES = "oauth2:https://www.googleapis.com/auth/fusiontables";
  private static final String DEFAULT_QUERY = "show tables";
  private static final String DIALOG_TEXT = "Choose an account to access FusionTables";
  public static final String FUSIONTABLES_POST = "https://www.googleapis.com/fusiontables/v1/tables";
  public static final String FUSIONTABLES_URL = "https://www.googleapis.com/fusiontables/v1/query";
  private static final String FUSIONTABLE_SERVICE = "fusiontables";
  private static final String FUSION_QUERY_URL = "http://www.google.com/fusiontables/api/query";
  private static final String LOG_TAG = "FUSION";
  private static final int SERVER_TIMEOUT_MS = 30000;
  private final Activity activity;
  private String apiKey;
  private String authTokenType = "oauth2:https://www.googleapis.com/auth/fusiontables";
  private File cachedServiceCredentials = null;
  private final ComponentContainer container;
  private String errorMessage;
  private boolean isServiceAuth = false;
  private String keyPath = "";
  private String query;
  private String queryResultStr;
  private final IClientLoginHelper requestHelper;
  private String scope = "https://www.googleapis.com/auth/fusiontables";
  private String serviceAccountEmail = "";
  private String standardErrorMessage = "Error on Fusion Tables query";
  
  public FusiontablesControl(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.container = paramComponentContainer;
    this.activity = paramComponentContainer.$context();
    this.requestHelper = createClientLoginHelper("Choose an account to access FusionTables", "fusiontables");
    this.query = "show tables";
    if (SdkLevel.getLevel() < 5) {
      showNoticeAndDie("Sorry. The Fusiontables component is not compatible with this phone.", "This application must exit.", "Rats!");
    }
  }
  
  private IClientLoginHelper createClientLoginHelper(String paramString1, String paramString2)
  {
    if (SdkLevel.getLevel() >= 5)
    {
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      HttpConnectionParams.setSoTimeout(localDefaultHttpClient.getParams(), 30000);
      HttpConnectionParams.setConnectionTimeout(localDefaultHttpClient.getParams(), 30000);
      return new ClientLoginHelper(this.activity, paramString2, paramString1, localDefaultHttpClient);
    }
    return null;
  }
  
  /* Error */
  private String doPostRequest(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 236	java/lang/String:trim	()Ljava/lang/String;
    //   4: ldc -18
    //   6: invokevirtual 241	java/lang/String:length	()I
    //   9: invokevirtual 245	java/lang/String:substring	(I)Ljava/lang/String;
    //   12: astore 4
    //   14: ldc 64
    //   16: new 247	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 248	java/lang/StringBuilder:<init>	()V
    //   23: ldc -6
    //   25: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: aload 4
    //   30: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: invokevirtual 257	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   36: invokestatic 263	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   39: pop
    //   40: new 265	org/apache/http/client/methods/HttpPost
    //   43: dup
    //   44: new 247	java/lang/StringBuilder
    //   47: dup
    //   48: invokespecial 248	java/lang/StringBuilder:<init>	()V
    //   51: ldc_w 267
    //   54: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: aload_0
    //   58: invokevirtual 270	com/google/appinventor/components/runtime/FusiontablesControl:ApiKey	()Ljava/lang/String;
    //   61: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: invokevirtual 257	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   67: invokespecial 273	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   70: astore_3
    //   71: new 275	org/apache/http/entity/StringEntity
    //   74: dup
    //   75: aload 4
    //   77: invokespecial 276	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;)V
    //   80: astore 4
    //   82: aload 4
    //   84: ldc_w 278
    //   87: invokevirtual 281	org/apache/http/entity/StringEntity:setContentType	(Ljava/lang/String;)V
    //   90: aload_3
    //   91: ldc_w 283
    //   94: new 247	java/lang/StringBuilder
    //   97: dup
    //   98: invokespecial 248	java/lang/StringBuilder:<init>	()V
    //   101: ldc 40
    //   103: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: aload_2
    //   107: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: invokevirtual 257	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   113: invokevirtual 287	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   116: aload_3
    //   117: aload 4
    //   119: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   122: new 199	org/apache/http/impl/client/DefaultHttpClient
    //   125: dup
    //   126: invokespecial 202	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   129: astore_2
    //   130: aload_2
    //   131: aload_3
    //   132: invokeinterface 295 2 0
    //   137: astore_2
    //   138: aload_2
    //   139: invokeinterface 301 1 0
    //   144: invokeinterface 306 1 0
    //   149: istore 5
    //   151: aload_2
    //   152: ifnull +296 -> 448
    //   155: iload 5
    //   157: sipush 200
    //   160: if_icmpne +288 -> 448
    //   163: aload_2
    //   164: invokestatic 310	com/google/appinventor/components/runtime/FusiontablesControl:httpApacheResponseToString	(Lorg/apache/http/HttpResponse;)Ljava/lang/String;
    //   167: astore_3
    //   168: new 312	org/json/JSONObject
    //   171: dup
    //   172: aload_3
    //   173: invokespecial 313	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   176: astore 4
    //   178: aload 4
    //   180: ldc_w 315
    //   183: invokevirtual 319	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   186: ifeq +196 -> 382
    //   189: aload_0
    //   190: new 247	java/lang/StringBuilder
    //   193: dup
    //   194: invokespecial 248	java/lang/StringBuilder:<init>	()V
    //   197: ldc_w 321
    //   200: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: aload 4
    //   205: ldc_w 315
    //   208: invokevirtual 325	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   211: invokevirtual 328	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   214: invokevirtual 257	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   217: putfield 179	com/google/appinventor/components/runtime/FusiontablesControl:queryResultStr	Ljava/lang/String;
    //   220: ldc 64
    //   222: new 247	java/lang/StringBuilder
    //   225: dup
    //   226: invokespecial 248	java/lang/StringBuilder:<init>	()V
    //   229: ldc_w 330
    //   232: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: aload_2
    //   236: invokeinterface 301 1 0
    //   241: invokevirtual 328	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   244: invokevirtual 257	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   247: invokestatic 263	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   250: pop
    //   251: ldc 64
    //   253: new 247	java/lang/StringBuilder
    //   256: dup
    //   257: invokespecial 248	java/lang/StringBuilder:<init>	()V
    //   260: ldc_w 332
    //   263: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: aload_1
    //   267: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   270: ldc_w 334
    //   273: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: aload_0
    //   277: getfield 179	com/google/appinventor/components/runtime/FusiontablesControl:queryResultStr	Ljava/lang/String;
    //   280: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   283: invokevirtual 257	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   286: invokestatic 263	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   289: pop
    //   290: aload_0
    //   291: getfield 179	com/google/appinventor/components/runtime/FusiontablesControl:queryResultStr	Ljava/lang/String;
    //   294: areturn
    //   295: astore_1
    //   296: aload_1
    //   297: invokevirtual 337	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   300: new 247	java/lang/StringBuilder
    //   303: dup
    //   304: invokespecial 248	java/lang/StringBuilder:<init>	()V
    //   307: ldc_w 339
    //   310: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   313: aload_1
    //   314: invokevirtual 342	java/io/UnsupportedEncodingException:getMessage	()Ljava/lang/String;
    //   317: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: invokevirtual 257	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   323: areturn
    //   324: astore_1
    //   325: aload_1
    //   326: invokevirtual 343	org/apache/http/client/ClientProtocolException:printStackTrace	()V
    //   329: new 247	java/lang/StringBuilder
    //   332: dup
    //   333: invokespecial 248	java/lang/StringBuilder:<init>	()V
    //   336: ldc_w 339
    //   339: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: aload_1
    //   343: invokevirtual 344	org/apache/http/client/ClientProtocolException:getMessage	()Ljava/lang/String;
    //   346: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: invokevirtual 257	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   352: areturn
    //   353: astore_1
    //   354: aload_1
    //   355: invokevirtual 345	java/io/IOException:printStackTrace	()V
    //   358: new 247	java/lang/StringBuilder
    //   361: dup
    //   362: invokespecial 248	java/lang/StringBuilder:<init>	()V
    //   365: ldc_w 339
    //   368: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   371: aload_1
    //   372: invokevirtual 346	java/io/IOException:getMessage	()Ljava/lang/String;
    //   375: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   378: invokevirtual 257	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   381: areturn
    //   382: aload_0
    //   383: aload_3
    //   384: putfield 179	com/google/appinventor/components/runtime/FusiontablesControl:queryResultStr	Ljava/lang/String;
    //   387: goto -167 -> 220
    //   390: astore_1
    //   391: aload_1
    //   392: invokevirtual 347	java/lang/IllegalStateException:printStackTrace	()V
    //   395: new 247	java/lang/StringBuilder
    //   398: dup
    //   399: invokespecial 248	java/lang/StringBuilder:<init>	()V
    //   402: ldc_w 339
    //   405: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: aload_1
    //   409: invokevirtual 348	java/lang/IllegalStateException:getMessage	()Ljava/lang/String;
    //   412: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: invokevirtual 257	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   418: areturn
    //   419: astore_1
    //   420: aload_1
    //   421: invokevirtual 349	org/json/JSONException:printStackTrace	()V
    //   424: new 247	java/lang/StringBuilder
    //   427: dup
    //   428: invokespecial 248	java/lang/StringBuilder:<init>	()V
    //   431: ldc_w 339
    //   434: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   437: aload_1
    //   438: invokevirtual 350	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   441: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: invokevirtual 257	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   447: areturn
    //   448: ldc 64
    //   450: new 247	java/lang/StringBuilder
    //   453: dup
    //   454: invokespecial 248	java/lang/StringBuilder:<init>	()V
    //   457: ldc_w 339
    //   460: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   463: aload_2
    //   464: invokeinterface 301 1 0
    //   469: invokevirtual 353	java/lang/Object:toString	()Ljava/lang/String;
    //   472: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   475: invokevirtual 257	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   478: invokestatic 263	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   481: pop
    //   482: aload_0
    //   483: aload_2
    //   484: invokeinterface 301 1 0
    //   489: invokevirtual 353	java/lang/Object:toString	()Ljava/lang/String;
    //   492: putfield 179	com/google/appinventor/components/runtime/FusiontablesControl:queryResultStr	Ljava/lang/String;
    //   495: goto -205 -> 290
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	498	0	this	FusiontablesControl
    //   0	498	1	paramString1	String
    //   0	498	2	paramString2	String
    //   70	314	3	localObject1	Object
    //   12	192	4	localObject2	Object
    //   149	12	5	i	int
    // Exception table:
    //   from	to	target	type
    //   71	82	295	java/io/UnsupportedEncodingException
    //   130	138	324	org/apache/http/client/ClientProtocolException
    //   130	138	353	java/io/IOException
    //   163	220	390	java/lang/IllegalStateException
    //   382	387	390	java/lang/IllegalStateException
    //   163	220	419	org/json/JSONException
    //   382	387	419	org/json/JSONException
  }
  
  private HttpUriRequest genFusiontablesQuery(String paramString)
    throws IOException
  {
    HttpPost localHttpPost = new HttpPost("http://www.google.com/fusiontables/api/query");
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(new BasicNameValuePair("sql", paramString));
    paramString = new UrlEncodedFormEntity(localArrayList, "UTF-8");
    paramString.setContentType("application/x-www-form-urlencoded");
    localHttpPost.setEntity(paramString);
    return localHttpPost;
  }
  
  public static String httpApacheResponseToString(org.apache.http.HttpResponse paramHttpResponse)
  {
    String str = "";
    if (paramHttpResponse != null)
    {
      if (paramHttpResponse.getStatusLine().getStatusCode() != 200) {
        str = paramHttpResponse.getStatusLine().getStatusCode() + " " + paramHttpResponse.getStatusLine().getReasonPhrase();
      }
    }
    else {
      return str;
    }
    try
    {
      paramHttpResponse = parseResponse(paramHttpResponse.getEntity().getContent());
      return paramHttpResponse;
    }
    catch (IOException paramHttpResponse)
    {
      paramHttpResponse.printStackTrace();
    }
    return "";
  }
  
  public static String httpResponseToString(com.google.api.client.http.HttpResponse paramHttpResponse)
  {
    String str = "";
    if (paramHttpResponse != null)
    {
      if (paramHttpResponse.getStatusCode() != 200) {
        str = paramHttpResponse.getStatusCode() + " " + paramHttpResponse.getStatusMessage();
      }
    }
    else {
      return str;
    }
    try
    {
      paramHttpResponse = parseResponse(paramHttpResponse.getContent());
      return paramHttpResponse;
    }
    catch (IOException paramHttpResponse)
    {
      paramHttpResponse.printStackTrace();
    }
    return "";
  }
  
  public static String parseResponse(InputStream paramInputStream)
  {
    String str1 = "";
    Object localObject = str1;
    BufferedReader localBufferedReader;
    try
    {
      localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
      localObject = str1;
      paramInputStream = new StringBuilder();
      for (;;)
      {
        localObject = str1;
        String str2 = localBufferedReader.readLine();
        if (str2 == null) {
          break;
        }
        localObject = str1;
        paramInputStream.append(str2 + "\n");
      }
      localObject = str1;
    }
    catch (IOException paramInputStream)
    {
      paramInputStream.printStackTrace();
      return (String)localObject;
    }
    paramInputStream = paramInputStream.toString();
    localObject = paramInputStream;
    Log.i("FUSION", "resultStr = " + paramInputStream);
    localObject = paramInputStream;
    localBufferedReader.close();
    return paramInputStream;
  }
  
  private String parseSqlCreateQueryToJson(String paramString)
  {
    Log.i("FUSION", "parsetoJSonSqlCreate :" + paramString);
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = paramString.trim();
    paramString = ((String)localObject).substring("create table".length(), ((String)localObject).indexOf('(')).trim();
    localObject = ((String)localObject).substring(((String)localObject).indexOf('(') + 1, ((String)localObject).indexOf(')')).split(",");
    localStringBuilder.append("{'columns':[");
    int i = 0;
    while (i < localObject.length)
    {
      String[] arrayOfString = localObject[i].split(":");
      localStringBuilder.append("{'name': '" + arrayOfString[0].trim() + "', 'type': '" + arrayOfString[1].trim() + "'}");
      if (i < localObject.length - 1) {
        localStringBuilder.append(",");
      }
      i += 1;
    }
    localStringBuilder.append("],");
    localStringBuilder.append("'isExportable':'true',");
    localStringBuilder.append("'name': '" + paramString + "'");
    localStringBuilder.append("}");
    localStringBuilder.insert(0, "CREATE TABLE ");
    Log.i("FUSION", "result = " + localStringBuilder.toString());
    return localStringBuilder.toString();
  }
  
  private void showNoticeAndDie(String paramString1, String paramString2, String paramString3)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this.activity).create();
    localAlertDialog.setTitle(paramString2);
    localAlertDialog.setCancelable(false);
    localAlertDialog.setMessage(paramString1);
    localAlertDialog.setButton(paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FusiontablesControl.this.activity.finish();
      }
    });
    localAlertDialog.show();
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Your Google API Key. For help, click on the questionmark (?) next to the FusiontablesControl component in the Palette. ")
  public String ApiKey()
  {
    return this.apiKey;
  }
  
  @DesignerProperty(defaultValue="", editorType="string")
  @SimpleProperty
  public void ApiKey(String paramString)
  {
    this.apiKey = paramString;
  }
  
  @SimpleFunction(description="DEPRECATED. This block is deprecated as of the end of 2012.  Use SendQuery.", userVisible=false)
  public void DoQuery()
  {
    if (this.requestHelper != null)
    {
      new QueryProcessor(null).execute(new String[] { this.query });
      return;
    }
    this.form.dispatchErrorOccurredEvent(this, "DoQuery", 3, new Object[0]);
  }
  
  @SimpleFunction(description="Forget end-users login credentials. Has no effect on service authentication")
  public void ForgetLogin()
  {
    OAuth2Helper.resetAccountCredential(this.activity);
  }
  
  @SimpleFunction(description="Gets all the rows from a specified fusion table. The tableId field is the id of therequired fusion table. The columns field is a comma-separeted list of the columns to retrieve.")
  public void GetRows(String paramString1, String paramString2)
  {
    this.query = ("SELECT " + paramString2 + " FROM " + paramString1);
    new QueryProcessorV1(this.activity).execute(new String[] { this.query });
  }
  
  @SimpleFunction(description="Gets all the rows from a fusion table that meet certain conditions. The tableId field isthe id of the required fusion table. The columns field is a comma-separeted list of the columns toretrieve. The conditions field specifies what rows to retrieve from the table, for example the rows in whicha particular column value is not null.")
  public void GetRowsWithConditions(String paramString1, String paramString2, String paramString3)
  {
    this.query = ("SELECT " + paramString2 + " FROM " + paramString1 + " WHERE " + paramString3);
    new QueryProcessorV1(this.activity).execute(new String[] { this.query });
  }
  
  @SimpleEvent(description="Indicates that the Fusion Tables query has finished processing, with a result.  The result of the query will generally be returned in CSV format, and can be converted to list format using the \"list from csv table\" or \"list from csv row\" blocks.")
  public void GotResult(String paramString)
  {
    EventDispatcher.dispatchEvent(this, "GotResult", new Object[] { paramString });
  }
  
  @SimpleFunction(description="Inserts a row into the specified fusion table. The tableId field is the id of thefusion table. The columns is a comma-separated list of the columns to insert values into. The values field specifies what values to insert into each column.")
  public void InsertRow(String paramString1, String paramString2, String paramString3)
  {
    this.query = ("INSERT INTO " + paramString1 + " (" + paramString2 + ")" + " VALUES " + "(" + paramString3 + ")");
    new QueryProcessorV1(this.activity).execute(new String[] { this.query });
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Specifies the path of the private key file.  This key file is used to get access to the FusionTables API.")
  public String KeyFile()
  {
    return this.keyPath;
  }
  
  @DesignerProperty(defaultValue="", editorType="asset")
  @SimpleProperty
  public void KeyFile(String paramString)
  {
    if (paramString.equals(this.keyPath)) {
      return;
    }
    if (this.cachedServiceCredentials != null)
    {
      this.cachedServiceCredentials.delete();
      this.cachedServiceCredentials = null;
    }
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    this.keyPath = str;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The query to send to the Fusion Tables API. <p>For legal query formats and examples, see the <a href=\"https://developers.google.com/fusiontables/docs/v1/getting_started\" target=\"_blank\">Fusion Tables API v1.0 reference manual</a>.</p> <p>Note that you do not need to worry about UTF-encoding the query. But you do need to make sure it follows the syntax described in the reference manual, which means that things like capitalization for names of columns matters, and that single quotes need to be used around column names if there are spaces in them.</p> ")
  public String Query()
  {
    return this.query;
  }
  
  @DesignerProperty(defaultValue="show tables", editorType="string")
  @SimpleProperty
  public void Query(String paramString)
  {
    this.query = paramString;
  }
  
  @SimpleFunction(description="Send the query to the Fusiontables server.")
  public void SendQuery()
  {
    new QueryProcessorV1(this.activity).execute(new String[] { this.query });
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The Service Account Email Address when service account authentication is in use.")
  public String ServiceAccountEmail()
  {
    return this.serviceAccountEmail;
  }
  
  @DesignerProperty(defaultValue="", editorType="string")
  @SimpleProperty
  public void ServiceAccountEmail(String paramString)
  {
    this.serviceAccountEmail = paramString;
  }
  
  @DesignerProperty(defaultValue="False", editorType="boolean")
  @SimpleProperty
  public void UseServiceAuthentication(boolean paramBoolean)
  {
    this.isServiceAuth = paramBoolean;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Indicates whether a service account should be used for authentication")
  public boolean UseServiceAuthentication()
  {
    return this.isServiceAuth;
  }
  
  public void handleOAuthError(String paramString)
  {
    Log.i("FUSION", "handleOAuthError: " + paramString);
    this.errorMessage = paramString;
  }
  
  public com.google.api.client.http.HttpResponse sendQuery(String paramString1, String paramString2)
  {
    this.errorMessage = this.standardErrorMessage;
    Log.i("FUSION", "executing " + paramString1);
    Fusiontables localFusiontables = new Fusiontables.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), new GoogleCredential()).setApplicationName("App Inventor Fusiontables/v1.0").setJsonHttpRequestInitializer(new GoogleKeyInitializer(ApiKey())).build();
    try
    {
      paramString1 = localFusiontables.query().sql(paramString1);
      paramString1.put("alt", "csv");
      paramString1.setOauthToken(paramString2);
      paramString1 = paramString1.executeUnparsed();
      return paramString1;
    }
    catch (GoogleJsonResponseException paramString1)
    {
      paramString1.printStackTrace();
      this.errorMessage = paramString1.getMessage();
      Log.e("FUSION", "JsonResponseException");
      Log.e("FUSION", "e.getMessage() is " + paramString1.getMessage());
      Log.e("FUSION", "response is " + null);
      return null;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
      this.errorMessage = paramString1.getMessage();
      Log.e("FUSION", "IOException");
      Log.e("FUSION", "e.getMessage() is " + paramString1.getMessage());
      Log.e("FUSION", "response is " + null);
    }
    return null;
  }
  
  void signalJsonResponseError(String paramString1, String paramString2)
  {
    this.form.dispatchErrorOccurredEventDialog(this, "SendQuery", 2601, new Object[] { paramString1, paramString2 });
  }
  
  private class QueryProcessor
    extends AsyncTask<String, Void, String>
  {
    private ProgressDialog progress = null;
    
    private QueryProcessor() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      try
      {
        Object localObject = FusiontablesControl.this.genFusiontablesQuery(paramVarArgs[0]);
        Log.d("FUSION", "Fetching: " + paramVarArgs[0]);
        paramVarArgs = FusiontablesControl.this.requestHelper.execute((HttpUriRequest)localObject);
        localObject = new ByteArrayOutputStream();
        paramVarArgs.getEntity().writeTo((OutputStream)localObject);
        Log.d("FUSION", "Response: " + paramVarArgs.getStatusLine().toString());
        paramVarArgs = ((ByteArrayOutputStream)localObject).toString();
        return paramVarArgs;
      }
      catch (IOException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
      }
      return paramVarArgs.getMessage();
    }
    
    protected void onPostExecute(String paramString)
    {
      this.progress.dismiss();
      FusiontablesControl.this.GotResult(paramString);
    }
    
    protected void onPreExecute()
    {
      this.progress = ProgressDialog.show(FusiontablesControl.this.activity, "Fusiontables", "processing query...", true);
    }
  }
  
  private class QueryProcessorV1
    extends AsyncTask<String, Void, String>
  {
    private static final String STAG = "FUSION_SERVICE_ACCOUNT";
    private static final String TAG = "QueryProcessorV1";
    private final Activity activity;
    private final ProgressDialog dialog;
    
    QueryProcessorV1(Activity paramActivity)
    {
      Log.i("QueryProcessorV1", "Creating AsyncFusiontablesQuery");
      this.activity = paramActivity;
      this.dialog = new ProgressDialog(paramActivity);
    }
    
    /* Error */
    private String serviceAuthRequest(String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   4: ldc 55
      //   6: invokestatic 59	com/google/appinventor/components/runtime/FusiontablesControl:access$502	(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;
      //   9: pop
      //   10: aload_0
      //   11: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   14: aload_0
      //   15: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   18: invokestatic 63	com/google/appinventor/components/runtime/FusiontablesControl:access$1000	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;
      //   21: invokestatic 66	com/google/appinventor/components/runtime/FusiontablesControl:access$902	(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;
      //   24: pop
      //   25: invokestatic 72	com/google/api/client/extensions/android2/AndroidHttp:newCompatibleTransport	()Lcom/google/api/client/http/HttpTransport;
      //   28: astore_2
      //   29: new 74	com/google/api/client/json/gson/GsonFactory
      //   32: dup
      //   33: invokespecial 75	com/google/api/client/json/gson/GsonFactory:<init>	()V
      //   36: astore_3
      //   37: ldc 12
      //   39: new 77	java/lang/StringBuilder
      //   42: dup
      //   43: invokespecial 78	java/lang/StringBuilder:<init>	()V
      //   46: ldc 80
      //   48: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   51: aload_0
      //   52: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   55: invokestatic 87	com/google/appinventor/components/runtime/FusiontablesControl:access$1100	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;
      //   58: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   61: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   64: invokestatic 35	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   67: pop
      //   68: aload_0
      //   69: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   72: invokestatic 95	com/google/appinventor/components/runtime/FusiontablesControl:access$1200	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/io/File;
      //   75: ifnonnull +33 -> 108
      //   78: aload_0
      //   79: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   82: aload_0
      //   83: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   86: invokestatic 99	com/google/appinventor/components/runtime/FusiontablesControl:access$1300	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Lcom/google/appinventor/components/runtime/ComponentContainer;
      //   89: invokeinterface 105 1 0
      //   94: aload_0
      //   95: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   98: invokestatic 87	com/google/appinventor/components/runtime/FusiontablesControl:access$1100	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;
      //   101: invokestatic 111	com/google/appinventor/components/runtime/util/MediaUtil:copyMediaToTempFile	(Lcom/google/appinventor/components/runtime/Form;Ljava/lang/String;)Ljava/io/File;
      //   104: invokestatic 115	com/google/appinventor/components/runtime/FusiontablesControl:access$1202	(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/io/File;)Ljava/io/File;
      //   107: pop
      //   108: new 117	com/google/api/services/fusiontables/Fusiontables$Builder
      //   111: dup
      //   112: aload_2
      //   113: aload_3
      //   114: new 119	com/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder
      //   117: dup
      //   118: invokespecial 120	com/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder:<init>	()V
      //   121: aload_2
      //   122: invokevirtual 124	com/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder:setTransport	(Lcom/google/api/client/http/HttpTransport;)Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;
      //   125: aload_3
      //   126: invokevirtual 128	com/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder:setJsonFactory	(Lcom/google/api/client/json/JsonFactory;)Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;
      //   129: aload_0
      //   130: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   133: invokestatic 131	com/google/appinventor/components/runtime/FusiontablesControl:access$1500	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;
      //   136: invokevirtual 135	com/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder:setServiceAccountId	(Ljava/lang/String;)Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;
      //   139: iconst_1
      //   140: anewarray 137	java/lang/String
      //   143: dup
      //   144: iconst_0
      //   145: aload_0
      //   146: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   149: invokestatic 140	com/google/appinventor/components/runtime/FusiontablesControl:access$1400	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;
      //   152: aastore
      //   153: invokevirtual 144	com/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder:setServiceAccountScopes	([Ljava/lang/String;)Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;
      //   156: aload_0
      //   157: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   160: invokestatic 95	com/google/appinventor/components/runtime/FusiontablesControl:access$1200	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/io/File;
      //   163: invokevirtual 148	com/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder:setServiceAccountPrivateKeyFromP12File	(Ljava/io/File;)Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;
      //   166: invokevirtual 152	com/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder:build	()Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential;
      //   169: invokespecial 155	com/google/api/services/fusiontables/Fusiontables$Builder:<init>	(Lcom/google/api/client/http/HttpTransport;Lcom/google/api/client/json/JsonFactory;Lcom/google/api/client/http/HttpRequestInitializer;)V
      //   172: new 157	com/google/api/client/googleapis/services/GoogleKeyInitializer
      //   175: dup
      //   176: aload_0
      //   177: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   180: invokevirtual 160	com/google/appinventor/components/runtime/FusiontablesControl:ApiKey	()Ljava/lang/String;
      //   183: invokespecial 163	com/google/api/client/googleapis/services/GoogleKeyInitializer:<init>	(Ljava/lang/String;)V
      //   186: invokevirtual 167	com/google/api/services/fusiontables/Fusiontables$Builder:setJsonHttpRequestInitializer	(Lcom/google/api/client/http/json/JsonHttpRequestInitializer;)Lcom/google/api/services/fusiontables/Fusiontables$Builder;
      //   189: invokevirtual 170	com/google/api/services/fusiontables/Fusiontables$Builder:build	()Lcom/google/api/services/fusiontables/Fusiontables;
      //   192: invokevirtual 176	com/google/api/services/fusiontables/Fusiontables:query	()Lcom/google/api/services/fusiontables/Fusiontables$Query;
      //   195: aload_1
      //   196: invokevirtual 182	com/google/api/services/fusiontables/Fusiontables$Query:sql	(Ljava/lang/String;)Lcom/google/api/services/fusiontables/Fusiontables$Query$Sql;
      //   199: astore_3
      //   200: aload_3
      //   201: ldc -72
      //   203: ldc -70
      //   205: invokevirtual 192	com/google/api/services/fusiontables/Fusiontables$Query$Sql:put	(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
      //   208: pop
      //   209: aconst_null
      //   210: astore_2
      //   211: aload_3
      //   212: invokevirtual 196	com/google/api/services/fusiontables/Fusiontables$Query$Sql:executeUnparsed	()Lcom/google/api/client/http/HttpResponse;
      //   215: astore_3
      //   216: aload_3
      //   217: astore_2
      //   218: aload_2
      //   219: ifnull +309 -> 528
      //   222: aload_0
      //   223: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   226: aload_2
      //   227: invokestatic 200	com/google/appinventor/components/runtime/FusiontablesControl:httpResponseToString	(Lcom/google/api/client/http/HttpResponse;)Ljava/lang/String;
      //   230: invokestatic 59	com/google/appinventor/components/runtime/FusiontablesControl:access$502	(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;
      //   233: pop
      //   234: ldc 12
      //   236: new 77	java/lang/StringBuilder
      //   239: dup
      //   240: invokespecial 78	java/lang/StringBuilder:<init>	()V
      //   243: ldc -54
      //   245: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   248: aload_1
      //   249: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   252: ldc -52
      //   254: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   257: aload_0
      //   258: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   261: invokestatic 207	com/google/appinventor/components/runtime/FusiontablesControl:access$500	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;
      //   264: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   267: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   270: invokestatic 35	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   273: pop
      //   274: ldc 12
      //   276: ldc -47
      //   278: invokestatic 35	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   281: pop
      //   282: ldc 12
      //   284: new 77	java/lang/StringBuilder
      //   287: dup
      //   288: invokespecial 78	java/lang/StringBuilder:<init>	()V
      //   291: ldc -45
      //   293: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   296: aload_0
      //   297: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   300: invokestatic 207	com/google/appinventor/components/runtime/FusiontablesControl:access$500	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;
      //   303: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   306: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   309: invokestatic 35	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   312: pop
      //   313: aload_0
      //   314: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   317: invokestatic 207	com/google/appinventor/components/runtime/FusiontablesControl:access$500	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;
      //   320: areturn
      //   321: astore_3
      //   322: ldc 12
      //   324: ldc -43
      //   326: invokestatic 35	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   329: pop
      //   330: aload_0
      //   331: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   334: aload_0
      //   335: aload_3
      //   336: invokevirtual 216	com/google/api/client/googleapis/json/GoogleJsonResponseException:getMessage	()Ljava/lang/String;
      //   339: invokevirtual 219	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:parseJsonResponseException	(Ljava/lang/String;)Ljava/lang/String;
      //   342: invokestatic 66	com/google/appinventor/components/runtime/FusiontablesControl:access$902	(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;
      //   345: pop
      //   346: aload_0
      //   347: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   350: aload_1
      //   351: aload_0
      //   352: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   355: invokestatic 222	com/google/appinventor/components/runtime/FusiontablesControl:access$900	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;
      //   358: invokevirtual 226	com/google/appinventor/components/runtime/FusiontablesControl:signalJsonResponseError	(Ljava/lang/String;Ljava/lang/String;)V
      //   361: goto -143 -> 218
      //   364: astore_1
      //   365: ldc 12
      //   367: ldc -28
      //   369: invokestatic 35	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   372: pop
      //   373: aload_1
      //   374: invokevirtual 231	java/lang/Throwable:printStackTrace	()V
      //   377: aload_0
      //   378: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   381: aload_1
      //   382: invokevirtual 232	java/lang/Throwable:getMessage	()Ljava/lang/String;
      //   385: invokestatic 59	com/google/appinventor/components/runtime/FusiontablesControl:access$502	(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;
      //   388: pop
      //   389: goto -107 -> 282
      //   392: astore_3
      //   393: ldc 12
      //   395: ldc -22
      //   397: invokestatic 35	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   400: pop
      //   401: ldc 12
      //   403: new 77	java/lang/StringBuilder
      //   406: dup
      //   407: invokespecial 78	java/lang/StringBuilder:<init>	()V
      //   410: ldc -20
      //   412: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   415: aload_3
      //   416: invokevirtual 242	java/lang/Object:getClass	()Ljava/lang/Class;
      //   419: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   422: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   425: invokestatic 35	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   428: pop
      //   429: ldc 12
      //   431: new 77	java/lang/StringBuilder
      //   434: dup
      //   435: invokespecial 78	java/lang/StringBuilder:<init>	()V
      //   438: ldc -9
      //   440: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   443: aload_3
      //   444: invokevirtual 248	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   447: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   450: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   453: invokestatic 35	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   456: pop
      //   457: ldc 12
      //   459: new 77	java/lang/StringBuilder
      //   462: dup
      //   463: invokespecial 78	java/lang/StringBuilder:<init>	()V
      //   466: ldc -6
      //   468: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   471: aload_3
      //   472: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   475: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   478: invokestatic 35	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   481: pop
      //   482: ldc 12
      //   484: ldc -4
      //   486: invokestatic 35	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   489: pop
      //   490: ldc 12
      //   492: ldc -2
      //   494: invokestatic 35	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   497: pop
      //   498: aload_0
      //   499: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   502: aload_3
      //   503: invokevirtual 248	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   506: invokestatic 66	com/google/appinventor/components/runtime/FusiontablesControl:access$902	(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;
      //   509: pop
      //   510: aload_0
      //   511: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   514: aload_1
      //   515: aload_0
      //   516: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   519: invokestatic 222	com/google/appinventor/components/runtime/FusiontablesControl:access$900	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;
      //   522: invokevirtual 226	com/google/appinventor/components/runtime/FusiontablesControl:signalJsonResponseError	(Ljava/lang/String;Ljava/lang/String;)V
      //   525: goto -307 -> 218
      //   528: aload_0
      //   529: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   532: aload_0
      //   533: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   536: invokestatic 222	com/google/appinventor/components/runtime/FusiontablesControl:access$900	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;
      //   539: invokestatic 59	com/google/appinventor/components/runtime/FusiontablesControl:access$502	(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;
      //   542: pop
      //   543: ldc 12
      //   545: new 77	java/lang/StringBuilder
      //   548: dup
      //   549: invokespecial 78	java/lang/StringBuilder:<init>	()V
      //   552: ldc_w 256
      //   555: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   558: aload_0
      //   559: getfield 24	com/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1:this$0	Lcom/google/appinventor/components/runtime/FusiontablesControl;
      //   562: invokestatic 222	com/google/appinventor/components/runtime/FusiontablesControl:access$900	(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;
      //   565: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   568: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   571: invokestatic 35	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   574: pop
      //   575: goto -301 -> 274
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	578	0	this	QueryProcessorV1
      //   0	578	1	paramString	String
      //   28	199	2	localObject1	Object
      //   36	181	3	localObject2	Object
      //   321	15	3	localGoogleJsonResponseException	GoogleJsonResponseException
      //   392	111	3	localException	Exception
      // Exception table:
      //   from	to	target	type
      //   211	216	321	com/google/api/client/googleapis/json/GoogleJsonResponseException
      //   68	108	364	java/lang/Throwable
      //   108	209	364	java/lang/Throwable
      //   211	216	364	java/lang/Throwable
      //   222	274	364	java/lang/Throwable
      //   274	282	364	java/lang/Throwable
      //   322	361	364	java/lang/Throwable
      //   393	525	364	java/lang/Throwable
      //   528	575	364	java/lang/Throwable
      //   211	216	392	java/lang/Exception
    }
    
    private String userAuthRequest(String paramString)
    {
      FusiontablesControl.access$502(FusiontablesControl.this, "");
      Object localObject = new OAuth2Helper().getRefreshedAuthToken(this.activity, FusiontablesControl.this.authTokenType);
      if (localObject != null)
      {
        if (paramString.toLowerCase().contains("create table"))
        {
          FusiontablesControl.access$502(FusiontablesControl.this, FusiontablesControl.this.doPostRequest(FusiontablesControl.access$700(FusiontablesControl.this, paramString), (String)localObject));
          return FusiontablesControl.this.queryResultStr;
        }
        localObject = FusiontablesControl.this.sendQuery(paramString, (String)localObject);
        if (localObject != null)
        {
          FusiontablesControl.access$502(FusiontablesControl.this, FusiontablesControl.httpResponseToString((com.google.api.client.http.HttpResponse)localObject));
          Log.i("QueryProcessorV1", "Query = " + paramString + "\nResultStr = " + FusiontablesControl.this.queryResultStr);
        }
        for (;;)
        {
          return FusiontablesControl.this.queryResultStr;
          FusiontablesControl.access$502(FusiontablesControl.this, FusiontablesControl.this.errorMessage);
          Log.i("QueryProcessorV1", "Error:  " + FusiontablesControl.this.errorMessage);
        }
      }
      return OAuth2Helper.getErrorMessage();
    }
    
    protected String doInBackground(String... paramVarArgs)
    {
      paramVarArgs = paramVarArgs[0];
      Log.i("QueryProcessorV1", "Starting doInBackground " + paramVarArgs);
      if (FusiontablesControl.this.isServiceAuth) {
        return serviceAuthRequest(paramVarArgs);
      }
      return userAuthRequest(paramVarArgs);
    }
    
    protected void onPostExecute(String paramString)
    {
      Log.i("FUSION", "Query result " + paramString);
      String str = paramString;
      if (paramString == null) {
        str = FusiontablesControl.this.errorMessage;
      }
      this.dialog.dismiss();
      FusiontablesControl.this.GotResult(str);
    }
    
    protected void onPreExecute()
    {
      this.dialog.setMessage("Fusiontables...");
      this.dialog.show();
    }
    
    String parseJsonResponseException(String paramString)
    {
      Log.i("FUSION_SERVICE_ACCOUNT", "parseJsonResponseException: " + paramString);
      return paramString;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\FusiontablesControl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */