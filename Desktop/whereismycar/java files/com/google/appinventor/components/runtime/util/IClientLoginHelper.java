package com.google.appinventor.components.runtime.util;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;

public abstract interface IClientLoginHelper
{
  public abstract HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws ClientProtocolException, IOException;
  
  public abstract void forgetAccountName();
  
  public abstract String getAuthToken()
    throws ClientProtocolException;
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\IClientLoginHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */