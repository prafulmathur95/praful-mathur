package com.google.appinventor.components.runtime.util;

import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;
import java.io.IOException;

public class OAuth2Helper
{
  public static final String PREF_ACCOUNT_NAME = "accountName";
  public static final String PREF_AUTH_TOKEN = "authToken";
  public static final String TAG = "OAuthHelper";
  private static String errorMessage = "Error during OAuth";
  
  private AccountManagerFuture<Bundle> getAccountManagerResult(Activity paramActivity, GoogleCredential paramGoogleCredential, String paramString1, String paramString2)
  {
    GoogleAccountManager localGoogleAccountManager = new GoogleAccountManager(paramActivity);
    localGoogleAccountManager.invalidateAuthToken(paramGoogleCredential.getAccessToken());
    AccountManager.get(paramActivity).invalidateAuthToken(paramString1, null);
    paramGoogleCredential = localGoogleAccountManager.getAccountByName(paramString2);
    if (paramGoogleCredential != null)
    {
      Log.i("OAuthHelper", "Getting token by account");
      return localGoogleAccountManager.getAccountManager().getAuthToken(paramGoogleCredential, paramString1, true, null, null);
    }
    Log.i("OAuthHelper", "Getting token by features, possibly prompting user to select an account");
    return localGoogleAccountManager.getAccountManager().getAuthTokenByFeatures("com.google", paramString1, null, paramActivity, null, null, null, null);
  }
  
  public static String getErrorMessage()
  {
    Log.i("OAuthHelper", "getErrorMessage = " + errorMessage);
    return errorMessage;
  }
  
  private boolean isUiThread()
  {
    return Looper.getMainLooper().getThread().equals(Thread.currentThread());
  }
  
  private void persistCredentials(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    Log.i("OAuthHelper", "Persisting credentials, acct =" + paramString1);
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putString("accountName", paramString1);
    paramSharedPreferences.putString("authToken", paramString2);
    paramSharedPreferences.commit();
  }
  
  public static void resetAccountCredential(Activity paramActivity)
  {
    Log.i("OAuthHelper", "Reset credentials");
    paramActivity = paramActivity.getPreferences(0).edit();
    paramActivity.remove("authToken");
    paramActivity.remove("accountName");
    paramActivity.commit();
  }
  
  public String getRefreshedAuthToken(Activity paramActivity, String paramString)
  {
    Log.i("OAuthHelper", "getRefreshedAuthToken()");
    if (isUiThread()) {
      throw new IllegalArgumentException("Can't get authtoken from UI thread");
    }
    SharedPreferences localSharedPreferences = paramActivity.getPreferences(0);
    Object localObject1 = localSharedPreferences.getString("accountName", null);
    String str1 = localSharedPreferences.getString("authToken", null);
    Object localObject2 = new GoogleCredential();
    ((GoogleCredential)localObject2).setAccessToken(str1);
    paramString = getAccountManagerResult(paramActivity, (GoogleCredential)localObject2, paramString, (String)localObject1);
    localObject1 = str1;
    localObject2 = str1;
    String str2 = str1;
    try
    {
      Bundle localBundle = (Bundle)paramString.getResult();
      localObject1 = str1;
      localObject2 = str1;
      str2 = str1;
      paramString = localBundle.get("authtoken").toString();
      localObject1 = paramString;
      localObject2 = paramString;
      str2 = paramString;
      persistCredentials(localSharedPreferences, localBundle.getString("authAccount"), paramString);
      return paramString;
    }
    catch (OperationCanceledException paramString)
    {
      paramString.printStackTrace();
      resetAccountCredential(paramActivity);
      errorMessage = "Error: operation cancelled";
      return (String)localObject1;
    }
    catch (AuthenticatorException paramActivity)
    {
      paramActivity.printStackTrace();
      errorMessage = "Error: Authenticator error";
      return (String)localObject2;
    }
    catch (IOException paramActivity)
    {
      paramActivity.printStackTrace();
      errorMessage = "Error: I/O error";
    }
    return str2;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\OAuth2Helper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */