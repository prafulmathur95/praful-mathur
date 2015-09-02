package com.google.appinventor.components.runtime;

import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.AsyncCallbackPair;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.WebServiceUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

@DesignerComponent(category=ComponentCategory.STORAGE, description="Non-visible component that communicates with a Web service to store and retrieve information.", iconName="images/tinyWebDB.png", nonVisible=true, version=2)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.INTERNET")
public class TinyWebDB
  extends AndroidNonvisibleComponent
  implements Component
{
  private static final String GETVALUE_COMMAND = "getvalue";
  private static final String LOG_TAG = "TinyWebDB";
  private static final String STOREAVALUE_COMMAND = "storeavalue";
  private static final String TAG_PARAMETER = "tag";
  private static final String VALUE_PARAMETER = "value";
  private Handler androidUIHandler = new Handler();
  private String serviceURL = "http://appinvtinywebdb.appspot.com/";
  
  public TinyWebDB(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
  }
  
  private void postGetValue(final String paramString)
  {
    AsyncCallbackPair local4 = new AsyncCallbackPair()
    {
      public void onFailure(final String paramAnonymousString)
      {
        TinyWebDB.this.androidUIHandler.post(new Runnable()
        {
          public void run()
          {
            TinyWebDB.this.WebServiceError(paramAnonymousString);
          }
        });
      }
      
      public void onSuccess(final JSONArray paramAnonymousJSONArray)
      {
        if (paramAnonymousJSONArray == null)
        {
          TinyWebDB.this.androidUIHandler.post(new Runnable()
          {
            public void run()
            {
              TinyWebDB.this.WebServiceError("The Web server did not respond to the get value request for the tag " + TinyWebDB.4.this.val$tag + ".");
            }
          });
          return;
        }
        for (;;)
        {
          try
          {
            final String str = paramAnonymousJSONArray.getString(1);
            paramAnonymousJSONArray = paramAnonymousJSONArray.getString(2);
            if (paramAnonymousJSONArray.length() == 0)
            {
              paramAnonymousJSONArray = "";
              TinyWebDB.this.androidUIHandler.post(new Runnable()
              {
                public void run()
                {
                  TinyWebDB.this.GotValue(str, paramAnonymousJSONArray);
                }
              });
              return;
            }
          }
          catch (JSONException paramAnonymousJSONArray)
          {
            TinyWebDB.this.androidUIHandler.post(new Runnable()
            {
              public void run()
              {
                TinyWebDB.this.WebServiceError("The Web server returned a garbled value for the tag " + TinyWebDB.4.this.val$tag + ".");
              }
            });
            return;
          }
          paramAnonymousJSONArray = JsonUtil.getObjectFromJson(paramAnonymousJSONArray);
        }
      }
    };
    WebServiceUtil.getInstance().postCommandReturningArray(this.serviceURL, "getvalue", Lists.newArrayList(new NameValuePair[] { new BasicNameValuePair("tag", paramString) }), local4);
  }
  
  private void postStoreValue(String paramString, Object paramObject)
  {
    AsyncCallbackPair local2 = new AsyncCallbackPair()
    {
      public void onFailure(final String paramAnonymousString)
      {
        TinyWebDB.this.androidUIHandler.post(new Runnable()
        {
          public void run()
          {
            TinyWebDB.this.WebServiceError(paramAnonymousString);
          }
        });
      }
      
      public void onSuccess(String paramAnonymousString)
      {
        TinyWebDB.this.androidUIHandler.post(new Runnable()
        {
          public void run()
          {
            TinyWebDB.this.ValueStored();
          }
        });
      }
    };
    try
    {
      WebServiceUtil.getInstance().postCommand(this.serviceURL, "storeavalue", Lists.newArrayList(new NameValuePair[] { new BasicNameValuePair("tag", paramString), new BasicNameValuePair("value", JsonUtil.getJsonRepresentation(paramObject)) }), local2);
      return;
    }
    catch (JSONException paramString)
    {
      throw new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
    }
  }
  
  @SimpleFunction
  public void GetValue(final String paramString)
  {
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        TinyWebDB.this.postGetValue(paramString);
      }
    });
  }
  
  @SimpleEvent
  public void GotValue(String paramString, Object paramObject)
  {
    EventDispatcher.dispatchEvent(this, "GotValue", new Object[] { paramString, paramObject });
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public String ServiceURL()
  {
    return this.serviceURL;
  }
  
  @DesignerProperty(defaultValue="http://appinvtinywebdb.appspot.com", editorType="string")
  @SimpleProperty
  public void ServiceURL(String paramString)
  {
    this.serviceURL = paramString;
  }
  
  @SimpleFunction
  public void StoreValue(final String paramString, final Object paramObject)
  {
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        TinyWebDB.this.postStoreValue(paramString, paramObject);
      }
    });
  }
  
  @SimpleEvent
  public void ValueStored()
  {
    EventDispatcher.dispatchEvent(this, "ValueStored", new Object[0]);
  }
  
  @SimpleEvent
  public void WebServiceError(String paramString)
  {
    EventDispatcher.dispatchEvent(this, "WebServiceError", new Object[] { paramString });
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\TinyWebDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */