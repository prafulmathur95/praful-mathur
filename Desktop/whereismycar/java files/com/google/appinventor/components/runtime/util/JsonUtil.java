package com.google.appinventor.components.runtime.util;

import gnu.lists.FString;
import gnu.math.IntFraction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonUtil
{
  public static Object convertJsonItem(Object paramObject)
    throws JSONException
  {
    Object localObject;
    if (paramObject == null) {
      localObject = "null";
    }
    do
    {
      return localObject;
      if ((paramObject instanceof JSONObject)) {
        return getListFromJsonObject((JSONObject)paramObject);
      }
      if ((paramObject instanceof JSONArray)) {
        return getListFromJsonArray((JSONArray)paramObject);
      }
      if ((paramObject.equals(Boolean.FALSE)) || (((paramObject instanceof String)) && (((String)paramObject).equalsIgnoreCase("false")))) {
        return Boolean.valueOf(false);
      }
      if ((paramObject.equals(Boolean.TRUE)) || (((paramObject instanceof String)) && (((String)paramObject).equalsIgnoreCase("true")))) {
        return Boolean.valueOf(true);
      }
      localObject = paramObject;
    } while ((paramObject instanceof Number));
    return paramObject.toString();
  }
  
  public static String getJsonRepresentation(Object paramObject)
    throws JSONException
  {
    if ((paramObject == null) || (paramObject.equals(null))) {
      return "null";
    }
    if ((paramObject instanceof FString)) {
      return JSONObject.quote(paramObject.toString());
    }
    if ((paramObject instanceof YailList)) {
      return ((YailList)paramObject).toJSONString();
    }
    if ((paramObject instanceof IntFraction)) {
      return JSONObject.numberToString(Double.valueOf(((IntFraction)paramObject).doubleValue()));
    }
    if ((paramObject instanceof Number)) {
      return JSONObject.numberToString((Number)paramObject);
    }
    if ((paramObject instanceof Boolean)) {
      return paramObject.toString();
    }
    if (paramObject.getClass().isArray())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      String str = "";
      Object[] arrayOfObject = (Object[])paramObject;
      int j = arrayOfObject.length;
      int i = 0;
      paramObject = str;
      while (i < j)
      {
        str = arrayOfObject[i];
        localStringBuilder.append((String)paramObject).append(getJsonRepresentation(str));
        paramObject = ",";
        i += 1;
      }
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
    return JSONObject.quote(paramObject.toString());
  }
  
  public static List<Object> getListFromJsonArray(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localArrayList.add(convertJsonItem(paramJSONArray.get(i)));
      i += 1;
    }
    return localArrayList;
  }
  
  public static List<Object> getListFromJsonObject(JSONObject paramJSONObject)
    throws JSONException
  {
    ArrayList localArrayList1 = new ArrayList();
    Iterator localIterator = paramJSONObject.keys();
    Object localObject = new ArrayList();
    while (localIterator.hasNext()) {
      ((List)localObject).add(localIterator.next());
    }
    Collections.sort((List)localObject);
    localIterator = ((List)localObject).iterator();
    while (localIterator.hasNext())
    {
      localObject = (String)localIterator.next();
      ArrayList localArrayList2 = new ArrayList();
      localArrayList2.add(localObject);
      localArrayList2.add(convertJsonItem(paramJSONObject.get((String)localObject)));
      localArrayList1.add(localArrayList2);
    }
    return localArrayList1;
  }
  
  public static Object getObjectFromJson(String paramString)
    throws JSONException
  {
    if ((paramString == null) || (paramString.equals(""))) {
      paramString = "";
    }
    Object localObject;
    do
    {
      do
      {
        do
        {
          return paramString;
          localObject = new JSONTokener(paramString).nextValue();
          if ((localObject == null) || (localObject.equals(null))) {
            return null;
          }
          paramString = (String)localObject;
        } while ((localObject instanceof String));
        paramString = (String)localObject;
      } while ((localObject instanceof Number));
      paramString = (String)localObject;
    } while ((localObject instanceof Boolean));
    if ((localObject instanceof JSONArray)) {
      return getListFromJsonArray((JSONArray)localObject);
    }
    if ((localObject instanceof JSONObject)) {
      return getListFromJsonObject((JSONObject)localObject);
    }
    throw new JSONException("Invalid JSON string.");
  }
  
  public static List<String> getStringListFromJsonArray(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localArrayList.add(paramJSONArray.getString(i));
      i += 1;
    }
    return localArrayList;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\JsonUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */