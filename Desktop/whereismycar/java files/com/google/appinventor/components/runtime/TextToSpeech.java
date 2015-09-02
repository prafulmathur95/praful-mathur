package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.util.ExternalTextToSpeech;
import com.google.appinventor.components.runtime.util.ITextToSpeech;
import com.google.appinventor.components.runtime.util.ITextToSpeech.TextToSpeechCallback;
import com.google.appinventor.components.runtime.util.InternalTextToSpeech;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

@DesignerComponent(category=ComponentCategory.MEDIA, description="The TestToSpeech component speaks a given text aloud.  You can set the pitch and the rate of speech. <p>You can also set a language by supplying a language code.  This changes the pronounciation of words, not the actual language spoken.  For example, setting the language to French and speaking English text will sound like someone speaking English (en) with a French accent.</p> <p>You can also specify a country by supplying a country code. This can affect the pronounciation.  For example, British English (GBR) will sound different from US English (USA).  Not every country code will affect every language.</p> <p>The languages and countries available depend on the particular device, and can be listed with the AvailableLanguages and AvailableCountries properties.</p>", iconName="images/textToSpeech.png", nonVisible=true, version=4)
@SimpleObject
public class TextToSpeech
  extends AndroidNonvisibleComponent
  implements Component, OnStopListener, OnResumeListener, OnDestroyListener
{
  private static final String LOG_TAG = "TextToSpeech";
  private static final Map<String, Locale> iso3CountryToLocaleMap;
  private static final Map<String, Locale> iso3LanguageToLocaleMap = ;
  private YailList allCountries;
  private YailList allLanguages;
  private String country;
  private ArrayList<String> countryList;
  private boolean isTtsPrepared;
  private String iso2Country;
  private String iso2Language;
  private String language;
  private ArrayList<String> languageList;
  private float pitch = 1.0F;
  private boolean result = false;
  private float speechRate = 1.0F;
  private final ITextToSpeech tts;
  
  static
  {
    iso3CountryToLocaleMap = Maps.newHashMap();
    initLocaleMaps();
  }
  
  public TextToSpeech(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    Language("en");
    Country("USA");
    int i;
    Object localObject;
    if (SdkLevel.getLevel() < 4)
    {
      i = 1;
      StringBuilder localStringBuilder = new StringBuilder().append("Using ");
      if (i == 0) {
        break label204;
      }
      localObject = "external";
      label68:
      Log.v("TextToSpeech", (String)localObject + " TTS library.");
      localObject = new ITextToSpeech.TextToSpeechCallback()
      {
        public void onFailure()
        {
          TextToSpeech.access$002(TextToSpeech.this, false);
          TextToSpeech.this.AfterSpeaking(false);
        }
        
        public void onSuccess()
        {
          TextToSpeech.access$002(TextToSpeech.this, true);
          TextToSpeech.this.AfterSpeaking(true);
        }
      };
      if (i == 0) {
        break label210;
      }
    }
    label204:
    label210:
    for (paramComponentContainer = new ExternalTextToSpeech(paramComponentContainer, (ITextToSpeech.TextToSpeechCallback)localObject);; paramComponentContainer = new InternalTextToSpeech(paramComponentContainer.$context(), (ITextToSpeech.TextToSpeechCallback)localObject))
    {
      this.tts = paramComponentContainer;
      this.form.registerForOnStop(this);
      this.form.registerForOnResume(this);
      this.form.registerForOnDestroy(this);
      this.form.setVolumeControlStream(3);
      this.isTtsPrepared = false;
      this.languageList = new ArrayList();
      this.countryList = new ArrayList();
      this.allLanguages = YailList.makeList(this.languageList);
      this.allCountries = YailList.makeList(this.countryList);
      return;
      i = 0;
      break;
      localObject = "internal";
      break label68;
    }
  }
  
  private void getLanguageAndCountryLists()
  {
    if (SdkLevel.getLevel() >= 4)
    {
      Locale[] arrayOfLocale = Locale.getAvailableLocales();
      int j = arrayOfLocale.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = arrayOfLocale[i];
        if (this.tts.isLanguageAvailable((Locale)localObject) != -2)
        {
          String str = ((Locale)localObject).getLanguage();
          localObject = ((Locale)localObject).getISO3Country();
          if ((!str.equals("")) && (!this.languageList.contains(str))) {
            this.languageList.add(str);
          }
          if ((!((String)localObject).equals("")) && (!this.countryList.contains(localObject))) {
            this.countryList.add(localObject);
          }
        }
        i += 1;
      }
      Collections.sort(this.languageList);
      Collections.sort(this.countryList);
      this.allLanguages = YailList.makeList(this.languageList);
      this.allCountries = YailList.makeList(this.countryList);
    }
  }
  
  private static void initLocaleMaps()
  {
    Locale[] arrayOfLocale = Locale.getAvailableLocales();
    int j = arrayOfLocale.length;
    int i = 0;
    for (;;)
    {
      Locale localLocale;
      if (i < j) {
        localLocale = arrayOfLocale[i];
      }
      try
      {
        String str = localLocale.getISO3Country();
        if (str.length() > 0) {
          iso3CountryToLocaleMap.put(str, localLocale);
        }
        try
        {
          str = localLocale.getISO3Language();
          if (str.length() > 0) {
            iso3LanguageToLocaleMap.put(str, localLocale);
          }
          i += 1;
          continue;
          return;
        }
        catch (MissingResourceException localMissingResourceException1)
        {
          for (;;) {}
        }
      }
      catch (MissingResourceException localMissingResourceException2)
      {
        for (;;) {}
      }
    }
  }
  
  private static Locale iso3CountryToLocale(String paramString)
  {
    Locale localLocale2 = (Locale)iso3CountryToLocaleMap.get(paramString);
    Locale localLocale1 = localLocale2;
    if (localLocale2 == null) {
      localLocale1 = (Locale)iso3CountryToLocaleMap.get(paramString.toUpperCase(Locale.ENGLISH));
    }
    paramString = localLocale1;
    if (localLocale1 == null) {
      paramString = Locale.getDefault();
    }
    return paramString;
  }
  
  private static Locale iso3LanguageToLocale(String paramString)
  {
    Locale localLocale2 = (Locale)iso3LanguageToLocaleMap.get(paramString);
    Locale localLocale1 = localLocale2;
    if (localLocale2 == null) {
      localLocale1 = (Locale)iso3LanguageToLocaleMap.get(paramString.toLowerCase(Locale.ENGLISH));
    }
    paramString = localLocale1;
    if (localLocale1 == null) {
      paramString = Locale.getDefault();
    }
    return paramString;
  }
  
  @SimpleEvent
  public void AfterSpeaking(boolean paramBoolean)
  {
    EventDispatcher.dispatchEvent(this, "AfterSpeaking", new Object[] { Boolean.valueOf(paramBoolean) });
  }
  
  @SimpleProperty(description="List of the country codes available on this device for use with TextToSpeech.  Check the Android developer documentation under supported languages to find the meanings of these abbreviations.")
  public YailList AvailableCountries()
  {
    prepareLanguageAndCountryProperties();
    return this.allCountries;
  }
  
  @SimpleProperty(description="List of the languages available on this device for use with TextToSpeech.  Check the Android developer documentation under supported languages to find the meanings of these abbreviations.")
  public YailList AvailableLanguages()
  {
    prepareLanguageAndCountryProperties();
    return this.allLanguages;
  }
  
  @SimpleEvent
  public void BeforeSpeaking()
  {
    EventDispatcher.dispatchEvent(this, "BeforeSpeaking", new Object[0]);
  }
  
  @SimpleProperty
  public String Country()
  {
    return this.country;
  }
  
  @DesignerProperty(defaultValue="USA", editorType="countries")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Country code to use for speech generation.  This can affect the pronounciation.  For example, British English (GBR) will sound different from US English (USA).  Not every country code will affect every language.")
  public void Country(String paramString)
  {
    switch (paramString.length())
    {
    default: 
      paramString = Locale.getDefault();
      this.country = paramString.getCountry();
    }
    for (;;)
    {
      this.iso2Country = paramString.getCountry();
      return;
      paramString = iso3CountryToLocale(paramString);
      this.country = paramString.getISO3Country();
      continue;
      paramString = new Locale(paramString);
      this.country = paramString.getCountry();
    }
  }
  
  @SimpleProperty
  public String Language()
  {
    return this.language;
  }
  
  @DesignerProperty(defaultValue="en", editorType="languages")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Sets the language for TextToSpeech. This changes the way that words are pronounced, not the actual language that is spoken.  For example setting the language to and speaking English text with sound like someone speaking English with a Frernch accent.")
  public void Language(String paramString)
  {
    switch (paramString.length())
    {
    default: 
      paramString = Locale.getDefault();
      this.language = paramString.getLanguage();
    }
    for (;;)
    {
      this.iso2Language = paramString.getLanguage();
      return;
      paramString = iso3LanguageToLocale(paramString);
      this.language = paramString.getISO3Language();
      continue;
      paramString = new Locale(paramString);
      this.language = paramString.getLanguage();
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Returns current value of Pitch")
  public float Pitch()
  {
    return this.pitch;
  }
  
  @DesignerProperty(defaultValue="1.0", editorType="float")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Sets the Pitch for TextToSpeech The values should be between 0 and 2 where lower values lower the tone of synthesized voice and greater values raise it.")
  public void Pitch(float paramFloat)
  {
    if ((paramFloat < 0.0F) || (paramFloat > 2.0F))
    {
      Log.i("TextToSpeech", "Pitch value should be between 0 and 2, but user specified: " + paramFloat);
      return;
    }
    this.pitch = paramFloat;
    ITextToSpeech localITextToSpeech = this.tts;
    float f = paramFloat;
    if (paramFloat == 0.0F) {
      f = 0.1F;
    }
    localITextToSpeech.setPitch(f);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean Result()
  {
    return this.result;
  }
  
  @SimpleFunction
  public void Speak(String paramString)
  {
    BeforeSpeaking();
    Locale localLocale = new Locale(this.iso2Language, this.iso2Country);
    this.tts.speak(paramString, localLocale);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Returns current value of SpeechRate")
  public float SpeechRate()
  {
    return this.speechRate;
  }
  
  @DesignerProperty(defaultValue="1.0", editorType="float")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Sets the SpeechRate for TextToSpeech. The values should be between 0 and 2 where lower values slow down the pitch and greater values accelerate it.")
  public void SpeechRate(float paramFloat)
  {
    if ((paramFloat < 0.0F) || (paramFloat > 2.0F))
    {
      Log.i("TextToSpeech", "speechRate value should be between 0 and 2, but user specified: " + paramFloat);
      return;
    }
    this.speechRate = paramFloat;
    ITextToSpeech localITextToSpeech = this.tts;
    float f = paramFloat;
    if (paramFloat == 0.0F) {
      f = 0.1F;
    }
    localITextToSpeech.setSpeechRate(f);
  }
  
  public void onDestroy()
  {
    this.tts.onDestroy();
  }
  
  public void onResume()
  {
    this.tts.onResume();
  }
  
  public void onStop()
  {
    this.tts.onStop();
  }
  
  public void prepareLanguageAndCountryProperties()
  {
    if (!this.isTtsPrepared)
    {
      if (!this.tts.isInitialized())
      {
        this.form.dispatchErrorOccurredEvent(this, "TextToSpeech", 2701, new Object[0]);
        Speak("");
      }
    }
    else {
      return;
    }
    getLanguageAndCountryLists();
    this.isTtsPrepared = true;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\TextToSpeech.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */