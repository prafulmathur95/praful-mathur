package org.acra.collector;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import org.acra.ACRA;

public final class ConfigurationCollector
{
  private static final String FIELD_MCC = "mcc";
  private static final String FIELD_MNC = "mnc";
  private static final String FIELD_SCREENLAYOUT = "screenLayout";
  private static final String FIELD_UIMODE = "uiMode";
  private static final String PREFIX_HARDKEYBOARDHIDDEN = "HARDKEYBOARDHIDDEN_";
  private static final String PREFIX_KEYBOARD = "KEYBOARD_";
  private static final String PREFIX_KEYBOARDHIDDEN = "KEYBOARDHIDDEN_";
  private static final String PREFIX_NAVIGATION = "NAVIGATION_";
  private static final String PREFIX_NAVIGATIONHIDDEN = "NAVIGATIONHIDDEN_";
  private static final String PREFIX_ORIENTATION = "ORIENTATION_";
  private static final String PREFIX_SCREENLAYOUT = "SCREENLAYOUT_";
  private static final String PREFIX_TOUCHSCREEN = "TOUCHSCREEN_";
  private static final String PREFIX_UI_MODE = "UI_MODE_";
  private static final String SUFFIX_MASK = "_MASK";
  private static SparseArray<String> mHardKeyboardHiddenValues = new SparseArray();
  private static SparseArray<String> mKeyboardHiddenValues;
  private static SparseArray<String> mKeyboardValues = new SparseArray();
  private static SparseArray<String> mNavigationHiddenValues;
  private static SparseArray<String> mNavigationValues;
  private static SparseArray<String> mOrientationValues;
  private static SparseArray<String> mScreenLayoutValues;
  private static SparseArray<String> mTouchScreenValues;
  private static SparseArray<String> mUiModeValues;
  private static final HashMap<String, SparseArray<String>> mValueArrays;
  
  static
  {
    mKeyboardHiddenValues = new SparseArray();
    mNavigationValues = new SparseArray();
    mNavigationHiddenValues = new SparseArray();
    mOrientationValues = new SparseArray();
    mScreenLayoutValues = new SparseArray();
    mTouchScreenValues = new SparseArray();
    mUiModeValues = new SparseArray();
    mValueArrays = new HashMap();
    mValueArrays.put("HARDKEYBOARDHIDDEN_", mHardKeyboardHiddenValues);
    mValueArrays.put("KEYBOARD_", mKeyboardValues);
    mValueArrays.put("KEYBOARDHIDDEN_", mKeyboardHiddenValues);
    mValueArrays.put("NAVIGATION_", mNavigationValues);
    mValueArrays.put("NAVIGATIONHIDDEN_", mNavigationHiddenValues);
    mValueArrays.put("ORIENTATION_", mOrientationValues);
    mValueArrays.put("SCREENLAYOUT_", mScreenLayoutValues);
    mValueArrays.put("TOUCHSCREEN_", mTouchScreenValues);
    mValueArrays.put("UI_MODE_", mUiModeValues);
    Field[] arrayOfField = Configuration.class.getFields();
    int j = arrayOfField.length;
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        Field localField = arrayOfField[i];
        if ((Modifier.isStatic(localField.getModifiers())) && (Modifier.isFinal(localField.getModifiers())))
        {
          String str = localField.getName();
          try
          {
            if (str.startsWith("HARDKEYBOARDHIDDEN_")) {
              mHardKeyboardHiddenValues.put(localField.getInt(null), str);
            } else if (str.startsWith("KEYBOARD_")) {
              mKeyboardValues.put(localField.getInt(null), str);
            }
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            Log.w(ACRA.LOG_TAG, "Error while inspecting device configuration: ", localIllegalArgumentException);
            break label500;
            if (str.startsWith("KEYBOARDHIDDEN_")) {
              mKeyboardHiddenValues.put(localIllegalArgumentException.getInt(null), str);
            }
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            Log.w(ACRA.LOG_TAG, "Error while inspecting device configuration: ", localIllegalAccessException);
          }
          if (str.startsWith("NAVIGATION_")) {
            mNavigationValues.put(localIllegalAccessException.getInt(null), str);
          } else if (str.startsWith("NAVIGATIONHIDDEN_")) {
            mNavigationHiddenValues.put(localIllegalAccessException.getInt(null), str);
          } else if (str.startsWith("ORIENTATION_")) {
            mOrientationValues.put(localIllegalAccessException.getInt(null), str);
          } else if (str.startsWith("SCREENLAYOUT_")) {
            mScreenLayoutValues.put(localIllegalAccessException.getInt(null), str);
          } else if (str.startsWith("TOUCHSCREEN_")) {
            mTouchScreenValues.put(localIllegalAccessException.getInt(null), str);
          } else if (str.startsWith("UI_MODE_")) {
            mUiModeValues.put(localIllegalAccessException.getInt(null), str);
          }
        }
      }
      else
      {
        return;
      }
      label500:
      i += 1;
    }
  }
  
  private static String activeFlags(SparseArray<String> paramSparseArray, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramSparseArray.size())
    {
      int j = paramSparseArray.keyAt(i);
      if (((String)paramSparseArray.get(j)).endsWith("_MASK"))
      {
        j = paramInt & j;
        if (j > 0)
        {
          if (localStringBuilder.length() > 0) {
            localStringBuilder.append('+');
          }
          localStringBuilder.append((String)paramSparseArray.get(j));
        }
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String collectConfiguration(Context paramContext)
  {
    try
    {
      String str = toString(paramContext.getResources().getConfiguration());
      return str;
    }
    catch (RuntimeException localRuntimeException)
    {
      Log.w(ACRA.LOG_TAG, "Couldn't retrieve CrashConfiguration for : " + paramContext.getPackageName(), localRuntimeException);
    }
    return "Couldn't retrieve crash config";
  }
  
  private static String getFieldValueName(Configuration paramConfiguration, Field paramField)
    throws IllegalAccessException
  {
    Object localObject = paramField.getName();
    if ((((String)localObject).equals("mcc")) || (((String)localObject).equals("mnc"))) {
      localObject = Integer.toString(paramField.getInt(paramConfiguration));
    }
    String str;
    do
    {
      return (String)localObject;
      if (((String)localObject).equals("uiMode")) {
        return activeFlags((SparseArray)mValueArrays.get("UI_MODE_"), paramField.getInt(paramConfiguration));
      }
      if (((String)localObject).equals("screenLayout")) {
        return activeFlags((SparseArray)mValueArrays.get("SCREENLAYOUT_"), paramField.getInt(paramConfiguration));
      }
      localObject = (SparseArray)mValueArrays.get(((String)localObject).toUpperCase() + '_');
      if (localObject == null) {
        return Integer.toString(paramField.getInt(paramConfiguration));
      }
      str = (String)((SparseArray)localObject).get(paramField.getInt(paramConfiguration));
      localObject = str;
    } while (str != null);
    return Integer.toString(paramField.getInt(paramConfiguration));
  }
  
  public static String toString(Configuration paramConfiguration)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Field[] arrayOfField = paramConfiguration.getClass().getFields();
    int j = arrayOfField.length;
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        Field localField = arrayOfField[i];
        try
        {
          if (!Modifier.isStatic(localField.getModifiers()))
          {
            localStringBuilder.append(localField.getName()).append('=');
            if (localField.getType().equals(Integer.TYPE)) {
              localStringBuilder.append(getFieldValueName(paramConfiguration, localField));
            }
            for (;;)
            {
              localStringBuilder.append('\n');
              break;
              if (localField.get(paramConfiguration) != null) {
                localStringBuilder.append(localField.get(paramConfiguration).toString());
              }
            }
            return localStringBuilder.toString();
          }
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          Log.e(ACRA.LOG_TAG, "Error while inspecting device configuration: ", localIllegalArgumentException);
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          Log.e(ACRA.LOG_TAG, "Error while inspecting device configuration: ", localIllegalAccessException);
        }
      }
      i += 1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\collector\ConfigurationCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */