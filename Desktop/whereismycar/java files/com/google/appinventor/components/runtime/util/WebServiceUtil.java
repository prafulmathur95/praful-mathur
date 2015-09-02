package com.google.appinventor.components.runtime.util;

import android.util.Log;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebServiceUtil
{
  private static final WebServiceUtil INSTANCE = new WebServiceUtil();
  private static final String LOG_TAG = "WebServiceUtil";
  private static HttpClient httpClient = null;
  private static Object httpClientSynchronizer = new Object();
  
  public static WebServiceUtil getInstance()
  {
    synchronized (httpClientSynchronizer)
    {
      if (httpClient == null)
      {
        SchemeRegistry localSchemeRegistry = new SchemeRegistry();
        localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        localSchemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(localBasicHttpParams, 20000);
        ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 20);
        httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
      }
      return INSTANCE;
    }
  }
  
  public void postCommand(String paramString1, String paramString2, List<NameValuePair> paramList, AsyncCallbackPair<String> paramAsyncCallbackPair)
  {
    Log.d("WebServiceUtil", "Posting " + paramString2 + " to " + paramString1 + " with arguments " + paramList);
    if ((paramString1 == null) || (paramString1.equals(""))) {
      paramAsyncCallbackPair.onFailure("No service url to post command to.");
    }
    paramString2 = new HttpPost(paramString1 + "/" + paramString2);
    paramString1 = paramList;
    if (paramList == null) {
      paramString1 = new ArrayList();
    }
    try
    {
      paramList = new BasicResponseHandler();
      paramString2.setEntity(new UrlEncodedFormEntity(paramString1, "UTF-8"));
      paramString2.setHeader("Accept", "application/json");
      paramAsyncCallbackPair.onSuccess((String)httpClient.execute(paramString2, paramList));
      return;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      Log.w("WebServiceUtil", paramString1);
      paramAsyncCallbackPair.onFailure("Failed to encode params for web service call.");
      return;
    }
    catch (ClientProtocolException paramString1)
    {
      Log.w("WebServiceUtil", paramString1);
      paramAsyncCallbackPair.onFailure("Communication with the web service encountered a protocol exception.");
      return;
    }
    catch (IOException paramString1)
    {
      Log.w("WebServiceUtil", paramString1);
      paramAsyncCallbackPair.onFailure("Communication with the web service timed out.");
    }
  }
  
  public void postCommandReturningArray(String paramString1, String paramString2, List<NameValuePair> paramList, final AsyncCallbackPair<JSONArray> paramAsyncCallbackPair)
  {
    postCommand(paramString1, paramString2, paramList, new AsyncCallbackPair()
    {
      public void onFailure(String paramAnonymousString)
      {
        paramAsyncCallbackPair.onFailure(paramAnonymousString);
      }
      
      public void onSuccess(String paramAnonymousString)
      {
        try
        {
          paramAsyncCallbackPair.onSuccess(new JSONArray(paramAnonymousString));
          return;
        }
        catch (JSONException paramAnonymousString)
        {
          paramAsyncCallbackPair.onFailure(paramAnonymousString.getMessage());
        }
      }
    });
  }
  
  public void postCommandReturningObject(String paramString1, String paramString2, List<NameValuePair> paramList, final AsyncCallbackPair<JSONObject> paramAsyncCallbackPair)
  {
    postCommand(paramString1, paramString2, paramList, new AsyncCallbackPair()
    {
      public void onFailure(String paramAnonymousString)
      {
        paramAsyncCallbackPair.onFailure(paramAnonymousString);
      }
      
      public void onSuccess(String paramAnonymousString)
      {
        try
        {
          paramAsyncCallbackPair.onSuccess(new JSONObject(paramAnonymousString));
          return;
        }
        catch (JSONException paramAnonymousString)
        {
          paramAsyncCallbackPair.onFailure(paramAnonymousString.getMessage());
        }
      }
    });
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\WebServiceUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */