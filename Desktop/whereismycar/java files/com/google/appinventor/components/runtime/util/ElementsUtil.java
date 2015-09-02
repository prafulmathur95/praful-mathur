package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.YailRuntimeError;

public class ElementsUtil
{
  public static YailList elements(YailList paramYailList, String paramString)
  {
    String[] arrayOfString = paramYailList.toStringArray();
    int i = 0;
    while (i < arrayOfString.length)
    {
      if (!(arrayOfString[i] instanceof String)) {
        throw new YailRuntimeError("Items passed to " + paramString + " must be Strings", "Error");
      }
      i += 1;
    }
    return paramYailList;
  }
  
  public static YailList elementsFromString(String paramString)
  {
    YailList localYailList = new YailList();
    if (paramString.length() > 0) {
      localYailList = YailList.makeList((Object[])paramString.split(" *, *"));
    }
    return localYailList;
  }
  
  public static int selectionIndex(int paramInt, YailList paramYailList)
  {
    int i;
    if (paramInt > 0)
    {
      i = paramInt;
      if (paramInt <= paramYailList.size()) {}
    }
    else
    {
      i = 0;
    }
    return i;
  }
  
  public static int setSelectedIndexFromValue(String paramString, YailList paramYailList)
  {
    int i = 0;
    while (i < paramYailList.size())
    {
      if (paramYailList.getString(i).equals(paramString)) {
        return i + 1;
      }
      i += 1;
    }
    return 0;
  }
  
  public static String setSelectionFromIndex(int paramInt, YailList paramYailList)
  {
    if (paramInt == 0) {
      return "";
    }
    return paramYailList.getString(paramInt - 1);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\ElementsUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */