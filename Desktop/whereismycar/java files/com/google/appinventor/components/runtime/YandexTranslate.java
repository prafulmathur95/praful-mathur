package com.google.appinventor.components.runtime;

import android.app.Activity;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@DesignerComponent(category=ComponentCategory.MEDIA, description="Use this component to translate words and sentences between different languages. This component needs Internet access, as it will request translations to the Yandex.Translate service. Specify the source and target language in the form source-target using two letter language codes. So\"en-es\" will translate from English to Spanish while \"es-ru\" will translate from Spanish to Russian. If you leave out the source language, the service will attempt to detect the source language. So providing just \"es\" will attempt to detect the source language and translate it to Spanish.<p /> This component is powered by the Yandex translation service.  See http://api.yandex.com/translate/ for more information, including the list of available languages and the meanings of the language codes and status codes. <p />Note: Translation happens asynchronously in the background. When the translation is complete, the \"GotTranslation\" event is triggered.", iconName="images/yandex.png", nonVisible=true, version=1)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.INTERNET")
public final class YandexTranslate
  extends AndroidNonvisibleComponent
{
  public static final String YANDEX_TRANSLATE_SERVICE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=";
  private final Activity activity;
  private final byte[] key1 = { -127, -88, 79, 80, 65, 112, -80, 87, -62, 126, -125, -25, -31, 55, 107, -42, -63, -62, 33, -122, 1, 89, -33, 23, -19, 18, -81, 37, -67, 114, 92, -60, -76, -50, -59, -49, -114, -64, -96, -75, 117, -116, 53, -8, 44, 111, 120, 48, 41, 30, 85, -116, -31, 17, 87, -89, -49, -51, 47, 92, 121, -58, -80, -25, 86, 123, -36, -9, 101, -112, -22, -28, -29, -14, -125, 46, -103, -36, 125, 114, 35, -31, 1, 123 };
  private final byte[] key2 = { -11, -38, 33, 35, 45, 94, -127, 121, -13, 80, -79, -41, -48, 3, 91, -29, -15, -9, 117, -74, 49, 105, -26, 34, -35, 72, -127, 64, -116, 69, 111, -12, -48, -81, -11, -83, -69, -12, -108, -42, 65, -72, 86, -42, 27, 12, 26, 2, 28, 122, 51, -24, -45, 36, 54, -106, -87, -3, 27, 62, 65, -16, -126, -42, 99, 77, -70, -49, 83, -12, -114, -35, -44, -109, -77, 28, -84, -66, 72, 22, 18, -126, 50, 78 };
  private final String yandexKey;
  
  public YandexTranslate(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.form.setYandexTranslateTagline();
    this.yandexKey = gk();
    this.activity = paramComponentContainer.$context();
  }
  
  private static String getResponseContent(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    Object localObject2 = paramHttpURLConnection.getContentEncoding();
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "UTF-8";
    }
    localObject1 = new InputStreamReader(paramHttpURLConnection.getInputStream(), (String)localObject1);
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
  
  private String gk()
  {
    byte[] arrayOfByte = new byte[this.key1.length];
    int i = 0;
    while (i < this.key1.length)
    {
      arrayOfByte[i] = ((byte)(this.key1[i] ^ this.key2[i]));
      i += 1;
    }
    return new String(arrayOfByte);
  }
  
  private void performRequest(String paramString1, final String paramString2)
    throws IOException, JSONException
  {
    paramString1 = (HttpURLConnection)new URL("https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + this.yandexKey + "&lang=" + paramString1 + "&text=" + URLEncoder.encode(paramString2, "UTF-8")).openConnection();
    if (paramString1 != null) {}
    try
    {
      final Object localObject = new JSONObject(getResponseContent(paramString1));
      paramString2 = ((JSONObject)localObject).getString("code");
      localObject = (String)((JSONObject)localObject).getJSONArray("text").get(0);
      this.activity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          YandexTranslate.this.GotTranslation(paramString2, localObject);
        }
      });
      return;
    }
    finally
    {
      paramString1.disconnect();
    }
  }
  
  @SimpleEvent(description="Event triggered when the Yandex.Translate service returns the translated text. This event also provides a response code for error handling. If the responseCode is not 200, then something went wrong with the call, and the translation will not be available.")
  public void GotTranslation(String paramString1, String paramString2)
  {
    EventDispatcher.dispatchEvent(this, "GotTranslation", new Object[] { paramString1, paramString2 });
  }
  
  @SimpleFunction(description="By providing a target language to translate to (for instance, 'es' for Spanish, 'en' for English, or 'ru' for Russian), and a word or sentence to translate, this method will request a translation to the Yandex.Translate service.\nOnce the text is translated by the external service, the event GotTranslation will be executed.\nNote: Yandex.Translate will attempt to detect the source language. You can also specify prepending it to the language translation. I.e., es-ru will specify Spanish to Russian translation.")
  public void RequestTranslation(final String paramString1, final String paramString2)
  {
    if (this.yandexKey.equals(""))
    {
      this.form.dispatchErrorOccurredEvent(this, "RequestTranslation", 2201, new Object[0]);
      return;
    }
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        try
        {
          YandexTranslate.this.performRequest(paramString1, paramString2);
          return;
        }
        catch (IOException localIOException)
        {
          YandexTranslate.this.form.dispatchErrorOccurredEvent(YandexTranslate.this, "RequestTranslation", 2202, new Object[0]);
          return;
        }
        catch (JSONException localJSONException)
        {
          YandexTranslate.this.form.dispatchErrorOccurredEvent(YandexTranslate.this, "RequestTranslation", 2203, new Object[0]);
        }
      }
    });
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\YandexTranslate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */