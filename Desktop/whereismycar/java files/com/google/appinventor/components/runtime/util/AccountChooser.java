package com.google.appinventor.components.runtime.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

public class AccountChooser
{
  private static final String ACCOUNT_PREFERENCE = "account";
  private static final String ACCOUNT_TYPE = "com.google";
  private static final String LOG_TAG = "AccountChooser";
  private static final String NO_ACCOUNT = "";
  private AccountManager accountManager;
  private Activity activity;
  private String chooseAccountPrompt;
  private String preferencesKey;
  private String service;
  
  public AccountChooser(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    this.activity = paramActivity;
    this.service = paramString1;
    this.chooseAccountPrompt = paramString2;
    this.preferencesKey = paramString3;
    this.accountManager = AccountManager.get(paramActivity);
  }
  
  private Account chooseAccount(String paramString, Account[] paramArrayOfAccount)
  {
    int j = paramArrayOfAccount.length;
    int i = 0;
    while (i < j)
    {
      Account localAccount = paramArrayOfAccount[i];
      if (localAccount.name.equals(paramString))
      {
        Log.i("AccountChooser", "chose account: " + paramString);
        return localAccount;
      }
      i += 1;
    }
    return null;
  }
  
  private String createAccount()
  {
    Log.i("AccountChooser", "Adding auth token account ...");
    Object localObject = this.accountManager.addAccount("com.google", this.service, null, null, this.activity, null, null);
    try
    {
      localObject = ((Bundle)((AccountManagerFuture)localObject).getResult()).getString("authAccount");
      Log.i("AccountChooser", "created: " + (String)localObject);
      return (String)localObject;
    }
    catch (OperationCanceledException localOperationCanceledException)
    {
      localOperationCanceledException.printStackTrace();
      return null;
    }
    catch (AuthenticatorException localAuthenticatorException)
    {
      for (;;)
      {
        localAuthenticatorException.printStackTrace();
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
    }
  }
  
  private String getPersistedAccountName()
  {
    return getPreferences().getString("account", null);
  }
  
  private SharedPreferences getPreferences()
  {
    return this.activity.getSharedPreferences(this.preferencesKey, 0);
  }
  
  private void persistAccountName(String paramString)
  {
    Log.i("AccountChooser", "persisting account: " + paramString);
    getPreferences().edit().putString("account", paramString).commit();
  }
  
  private String selectAccount(Account[] paramArrayOfAccount)
  {
    Object localObject = new SynchronousQueue();
    new SelectAccount(paramArrayOfAccount, (SynchronousQueue)localObject).start();
    Log.i("AccountChooser", "Select: waiting for user...");
    paramArrayOfAccount = null;
    try
    {
      localObject = (String)((SynchronousQueue)localObject).take();
      paramArrayOfAccount = (Account[])localObject;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
      }
    }
    Log.i("AccountChooser", "Selected: " + paramArrayOfAccount);
    localObject = paramArrayOfAccount;
    if (paramArrayOfAccount == "") {
      localObject = null;
    }
    return (String)localObject;
  }
  
  public Account findAccount()
  {
    Account[] arrayOfAccount = this.accountManager.getAccountsByType("com.google");
    if (arrayOfAccount.length == 1)
    {
      persistAccountName(arrayOfAccount[0].name);
      localObject = arrayOfAccount[0];
    }
    Account localAccount;
    do
    {
      return (Account)localObject;
      if (arrayOfAccount.length == 0)
      {
        localObject = createAccount();
        if (localObject != null)
        {
          persistAccountName((String)localObject);
          return this.accountManager.getAccountsByType("com.google")[0];
        }
        Log.i("AccountChooser", "User failed to create a valid account");
        return null;
      }
      localObject = getPersistedAccountName();
      if (localObject == null) {
        break;
      }
      localAccount = chooseAccount((String)localObject, arrayOfAccount);
      localObject = localAccount;
    } while (localAccount != null);
    Object localObject = selectAccount(arrayOfAccount);
    if (localObject != null)
    {
      persistAccountName((String)localObject);
      return chooseAccount((String)localObject, arrayOfAccount);
    }
    Log.i("AccountChooser", "User failed to choose an account");
    return null;
  }
  
  public void forgetAccountName()
  {
    getPreferences().edit().remove("account").commit();
  }
  
  class SelectAccount
    extends Thread
    implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener
  {
    private String[] accountNames;
    private SynchronousQueue<String> queue;
    
    SelectAccount(SynchronousQueue<String> paramSynchronousQueue)
    {
      SynchronousQueue localSynchronousQueue;
      this.queue = localSynchronousQueue;
      this.accountNames = new String[paramSynchronousQueue.length];
      int i = 0;
      while (i < paramSynchronousQueue.length)
      {
        this.accountNames[i] = paramSynchronousQueue[i].name;
        i += 1;
      }
    }
    
    public void onCancel(DialogInterface paramDialogInterface)
    {
      Log.i("AccountChooser", "Chose: canceled");
      onClick(paramDialogInterface, -1);
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      if (paramInt >= 0) {}
      try
      {
        String str = this.accountNames[paramInt];
        Log.i("AccountChooser", "Chose: " + str);
        this.queue.put(str);
        for (;;)
        {
          paramDialogInterface.dismiss();
          return;
          this.queue.put("");
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
    
    public void run()
    {
      AccountChooser.this.activity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          new AlertDialog.Builder(AccountChooser.this.activity).setTitle(Html.fromHtml(AccountChooser.this.chooseAccountPrompt)).setOnCancelListener(AccountChooser.SelectAccount.this).setSingleChoiceItems(AccountChooser.SelectAccount.this.accountNames, -1, AccountChooser.SelectAccount.this).show();
          Log.i("AccountChooser", "Dialog showing!");
        }
      });
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\AccountChooser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */