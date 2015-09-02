package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;

public final class RuntimeErrorAlert
{
  public static void alert(Object paramObject, String paramString1, String paramString2, String paramString3)
  {
    Log.i("RuntimeErrorAlert", "in alert");
    AlertDialog localAlertDialog = new AlertDialog.Builder((Context)paramObject).create();
    localAlertDialog.setTitle(paramString2);
    localAlertDialog.setMessage(paramString1);
    localAlertDialog.setButton(paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ((Activity)this.val$context).finish();
      }
    });
    if (paramString1 == null) {
      Log.e(RuntimeErrorAlert.class.getName(), "No error message available");
    }
    for (;;)
    {
      localAlertDialog.show();
      return;
      Log.e(RuntimeErrorAlert.class.getName(), paramString1);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\RuntimeErrorAlert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */