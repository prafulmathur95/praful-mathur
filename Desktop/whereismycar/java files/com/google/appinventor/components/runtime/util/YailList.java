package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Collection;
import java.util.List;
import org.json.JSONException;

public class YailList
  extends Pair
{
  private static final String LOG_TAG = "YailList";
  
  public YailList()
  {
    super(YailConstants.YAIL_HEADER, LList.Empty);
  }
  
  private YailList(Object paramObject)
  {
    super(YailConstants.YAIL_HEADER, paramObject);
  }
  
  public static String YailListElementToString(Object paramObject)
  {
    if (Number.class.isInstance(paramObject)) {
      return YailNumberToString.format(((Number)paramObject).doubleValue());
    }
    return String.valueOf(paramObject);
  }
  
  public static YailList makeEmptyList()
  {
    return new YailList();
  }
  
  public static YailList makeList(Collection paramCollection)
  {
    return new YailList(Pair.makeList(paramCollection.toArray(), 0));
  }
  
  public static YailList makeList(List paramList)
  {
    return new YailList(Pair.makeList(paramList));
  }
  
  public static YailList makeList(Object[] paramArrayOfObject)
  {
    return new YailList(Pair.makeList(paramArrayOfObject, 0));
  }
  
  public Object getObject(int paramInt)
  {
    return get(paramInt + 1);
  }
  
  public String getString(int paramInt)
  {
    return get(paramInt + 1).toString();
  }
  
  public int size()
  {
    return super.size() - 1;
  }
  
  public Object[] toArray()
  {
    if ((this.cdr instanceof Pair)) {
      return ((Pair)this.cdr).toArray();
    }
    if ((this.cdr instanceof LList)) {
      return ((LList)this.cdr).toArray();
    }
    throw new YailRuntimeError("YailList cannot be represented as an array", "YailList Error.");
  }
  
  public String toJSONString()
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      String str = "";
      localStringBuilder.append('[');
      int j = size();
      int i = 1;
      while (i <= j)
      {
        Object localObject = get(i);
        localStringBuilder.append(str).append(JsonUtil.getJsonRepresentation(localObject));
        str = ",";
        i += 1;
      }
      localStringBuilder.append(']');
      str = localStringBuilder.toString();
      return str;
    }
    catch (JSONException localJSONException)
    {
      throw new YailRuntimeError("List failed to convert to JSON.", "JSON Creation Error.");
    }
  }
  
  public String toString()
  {
    if ((this.cdr instanceof Pair)) {
      return ((Pair)this.cdr).toString();
    }
    if ((this.cdr instanceof LList)) {
      return ((LList)this.cdr).toString();
    }
    throw new RuntimeException("YailList cannot be represented as a String");
  }
  
  public String[] toStringArray()
  {
    int j = size();
    String[] arrayOfString = new String[j];
    int i = 1;
    while (i <= j)
    {
      arrayOfString[(i - 1)] = YailListElementToString(get(i));
      i += 1;
    }
    return arrayOfString;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\YailList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */