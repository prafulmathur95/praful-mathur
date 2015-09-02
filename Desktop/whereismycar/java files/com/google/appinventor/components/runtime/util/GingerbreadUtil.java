package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Parcelable;
import android.util.Log;
import com.google.appinventor.components.runtime.NearField;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.nio.charset.Charset;
import java.util.Locale;

public class GingerbreadUtil
{
  public static boolean clearCookies(CookieHandler paramCookieHandler)
  {
    if ((paramCookieHandler instanceof CookieManager))
    {
      paramCookieHandler = ((CookieManager)paramCookieHandler).getCookieStore();
      if (paramCookieHandler != null)
      {
        paramCookieHandler.removeAll();
        return true;
      }
    }
    return false;
  }
  
  public static NdefRecord createTextRecord(String paramString, boolean paramBoolean)
  {
    byte[] arrayOfByte = Locale.getDefault().getLanguage().getBytes(Charset.forName("US-ASCII"));
    Object localObject;
    if (paramBoolean)
    {
      localObject = Charset.forName("UTF-8");
      paramString = paramString.getBytes((Charset)localObject);
      if (!paramBoolean) {
        break label108;
      }
    }
    label108:
    for (int i = 0;; i = 128)
    {
      i = (char)(arrayOfByte.length + i);
      localObject = new byte[arrayOfByte.length + 1 + paramString.length];
      localObject[0] = ((byte)i);
      System.arraycopy(arrayOfByte, 0, localObject, 1, arrayOfByte.length);
      System.arraycopy(paramString, 0, localObject, arrayOfByte.length + 1, paramString.length);
      return new NdefRecord((short)1, NdefRecord.RTD_TEXT, new byte[0], (byte[])localObject);
      localObject = Charset.forName("UTF-16");
      break;
    }
  }
  
  public static void disableNFCAdapter(Activity paramActivity, NfcAdapter paramNfcAdapter)
  {
    paramNfcAdapter.disableForegroundNdefPush(paramActivity);
  }
  
  public static void enableNFCWriteMode(Activity paramActivity, NfcAdapter paramNfcAdapter, String paramString)
  {
    paramNfcAdapter.enableForegroundNdefPush(paramActivity, new NdefMessage(new NdefRecord[] { createTextRecord(paramString, true) }));
  }
  
  public static CookieHandler newCookieManager()
  {
    return new CookieManager();
  }
  
  public static NfcAdapter newNfcAdapter(Context paramContext)
  {
    return NfcAdapter.getDefaultAdapter(paramContext);
  }
  
  public static void resolveNFCIntent(Intent paramIntent, NearField paramNearField)
  {
    if ("android.nfc.action.NDEF_DISCOVERED".equals(paramIntent.getAction()))
    {
      Object localObject;
      if (paramNearField.ReadMode())
      {
        Parcelable[] arrayOfParcelable = paramIntent.getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES");
        if (arrayOfParcelable != null)
        {
          localObject = new NdefMessage[arrayOfParcelable.length];
          int i = 0;
          for (;;)
          {
            paramIntent = (Intent)localObject;
            if (i >= arrayOfParcelable.length) {
              break;
            }
            localObject[i] = ((NdefMessage)arrayOfParcelable[i]);
            i += 1;
          }
        }
        paramIntent = new byte[0];
        localObject = new NdefMessage(new NdefRecord[] { new NdefRecord(5, paramIntent, paramIntent, paramIntent) });
        paramIntent = new NdefMessage[1];
        paramIntent[0] = localObject;
        paramNearField.TagRead(new String(paramIntent[0].getRecords()[0].getPayload()).substring(3));
      }
      do
      {
        return;
        localObject = (Tag)paramIntent.getParcelableExtra("android.nfc.extra.TAG");
        paramIntent = null;
        if (paramNearField.WriteType() == 1) {
          paramIntent = new NdefMessage(new NdefRecord[] { createTextRecord(paramNearField.TextToWrite(), true) });
        }
      } while (!writeNFCTag(paramIntent, (Tag)localObject));
      paramNearField.TagWritten();
      return;
    }
    Log.e("nearfield", "Unknown intent " + paramIntent);
  }
  
  public static boolean writeNFCTag(NdefMessage paramNdefMessage, Tag paramTag)
  {
    int i = paramNdefMessage.toByteArray().length;
    try
    {
      Ndef localNdef = Ndef.get(paramTag);
      if (localNdef != null)
      {
        localNdef.connect();
        if (!localNdef.isWritable()) {
          return false;
        }
        if (localNdef.getMaxSize() >= i)
        {
          localNdef.writeNdefMessage(paramNdefMessage);
          return true;
        }
      }
      else
      {
        paramTag = NdefFormatable.get(paramTag);
        if (paramTag != null) {
          try
          {
            paramTag.connect();
            paramTag.format(paramNdefMessage);
            return true;
          }
          catch (IOException paramNdefMessage)
          {
            return false;
          }
        }
      }
      return false;
    }
    catch (Exception paramNdefMessage) {}
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\GingerbreadUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */