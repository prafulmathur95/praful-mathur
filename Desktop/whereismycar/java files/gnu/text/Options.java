package gnu.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class Options
{
  public static final int BOOLEAN_OPTION = 1;
  public static final int STRING_OPTION = 2;
  public static final String UNKNOWN = "unknown option name";
  OptionInfo first;
  HashMap<String, OptionInfo> infoTable;
  OptionInfo last;
  Options previous;
  HashMap<String, Object> valueTable;
  
  public Options() {}
  
  public Options(Options paramOptions)
  {
    this.previous = paramOptions;
  }
  
  private void error(String paramString, SourceMessages paramSourceMessages)
  {
    if (paramSourceMessages == null) {
      throw new RuntimeException(paramString);
    }
    paramSourceMessages.error('e', paramString);
  }
  
  static Object valueOf(OptionInfo paramOptionInfo, String paramString)
  {
    Object localObject = paramString;
    if ((paramOptionInfo.kind & 0x1) != 0)
    {
      if ((paramString == null) || (paramString.equals("1")) || (paramString.equals("on")) || (paramString.equals("yes")) || (paramString.equals("true"))) {
        localObject = Boolean.TRUE;
      }
    }
    else {
      return localObject;
    }
    if ((paramString.equals("0")) || (paramString.equals("off")) || (paramString.equals("no")) || (paramString.equals("false"))) {
      return Boolean.FALSE;
    }
    return null;
  }
  
  public OptionInfo add(String paramString1, int paramInt, Object paramObject, String paramString2)
  {
    OptionInfo localOptionInfo;
    if (this.infoTable == null)
    {
      this.infoTable = new HashMap();
      localOptionInfo = new OptionInfo();
      localOptionInfo.key = paramString1;
      localOptionInfo.kind = paramInt;
      localOptionInfo.defaultValue = paramObject;
      localOptionInfo.documentation = paramString2;
      if (this.first != null) {
        break label123;
      }
      this.first = localOptionInfo;
    }
    for (;;)
    {
      this.last = localOptionInfo;
      this.infoTable.put(paramString1, localOptionInfo);
      return localOptionInfo;
      if (this.infoTable.get(paramString1) == null) {
        break;
      }
      throw new RuntimeException("duplicate option key: " + paramString1);
      label123:
      this.last.next = localOptionInfo;
    }
  }
  
  public OptionInfo add(String paramString1, int paramInt, String paramString2)
  {
    return add(paramString1, paramInt, null, paramString2);
  }
  
  public Object get(OptionInfo paramOptionInfo)
  {
    return get(paramOptionInfo, null);
  }
  
  public Object get(OptionInfo paramOptionInfo, Object paramObject)
  {
    Object localObject2 = this;
    Object localObject1 = paramObject;
    for (paramObject = localObject2; paramObject != null; paramObject = ((Options)paramObject).previous)
    {
      for (localObject2 = paramOptionInfo;; localObject2 = (OptionInfo)((OptionInfo)localObject2).defaultValue)
      {
        if (((Options)paramObject).valueTable == null) {}
        for (Object localObject3 = null; localObject3 != null; localObject3 = ((Options)paramObject).valueTable.get(((OptionInfo)localObject2).key)) {
          return localObject3;
        }
        if (!(((OptionInfo)localObject2).defaultValue instanceof OptionInfo)) {
          break;
        }
      }
      if (((OptionInfo)localObject2).defaultValue != null) {
        localObject1 = ((OptionInfo)localObject2).defaultValue;
      }
    }
    return localObject1;
  }
  
  public Object get(String paramString, Object paramObject)
  {
    OptionInfo localOptionInfo = getInfo(paramString);
    if (localOptionInfo == null) {
      throw new RuntimeException("invalid option key: " + paramString);
    }
    return get(localOptionInfo, paramObject);
  }
  
  public boolean getBoolean(OptionInfo paramOptionInfo)
  {
    paramOptionInfo = get(paramOptionInfo, null);
    if (paramOptionInfo == null) {
      return false;
    }
    return ((Boolean)paramOptionInfo).booleanValue();
  }
  
  public boolean getBoolean(OptionInfo paramOptionInfo, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (Boolean localBoolean = Boolean.TRUE;; localBoolean = Boolean.FALSE) {
      return ((Boolean)get(paramOptionInfo, localBoolean)).booleanValue();
    }
  }
  
  public boolean getBoolean(String paramString)
  {
    return ((Boolean)get(paramString, Boolean.FALSE)).booleanValue();
  }
  
  public boolean getBoolean(String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (Boolean localBoolean = Boolean.TRUE;; localBoolean = Boolean.FALSE) {
      return ((Boolean)get(paramString, localBoolean)).booleanValue();
    }
  }
  
  public String getDoc(String paramString)
  {
    OptionInfo localOptionInfo = getInfo(paramString);
    if (paramString == null) {
      return null;
    }
    return localOptionInfo.documentation;
  }
  
  public OptionInfo getInfo(String paramString)
  {
    if (this.infoTable == null) {}
    for (OptionInfo localOptionInfo1 = null;; localOptionInfo1 = (OptionInfo)this.infoTable.get(paramString))
    {
      OptionInfo localOptionInfo2 = localOptionInfo1;
      if (localOptionInfo1 == null)
      {
        localOptionInfo2 = localOptionInfo1;
        if (this.previous != null) {
          localOptionInfo2 = this.previous.getInfo(paramString);
        }
      }
      return (OptionInfo)localOptionInfo2;
    }
  }
  
  public Object getLocal(String paramString)
  {
    if (this.valueTable == null) {
      return null;
    }
    return this.valueTable.get(paramString);
  }
  
  public ArrayList<String> keys()
  {
    ArrayList localArrayList = new ArrayList();
    for (Options localOptions = this; localOptions != null; localOptions = localOptions.previous) {
      if (localOptions.infoTable != null)
      {
        Iterator localIterator = localOptions.infoTable.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          if (!localArrayList.contains(str)) {
            localArrayList.add(str);
          }
        }
      }
    }
    return localArrayList;
  }
  
  public void popOptionValues(Vector paramVector)
  {
    int i = paramVector.size();
    for (;;)
    {
      i -= 3;
      if (i < 0) {
        break;
      }
      String str = (String)paramVector.elementAt(i);
      Object localObject = paramVector.elementAt(i + 1);
      paramVector.setElementAt(null, i + 1);
      reset(str, localObject);
    }
  }
  
  public void pushOptionValues(Vector paramVector)
  {
    int j = paramVector.size();
    int i = 0;
    while (i < j)
    {
      int k = i + 1;
      String str = (String)paramVector.elementAt(i);
      Object localObject = paramVector.elementAt(k);
      i = k + 1;
      paramVector.setElementAt(localObject, k);
      set(str, paramVector.elementAt(i));
      i += 1;
    }
  }
  
  public void reset(String paramString, Object paramObject)
  {
    if (this.valueTable == null) {
      this.valueTable = new HashMap();
    }
    if (paramObject == null)
    {
      this.valueTable.remove(paramString);
      return;
    }
    this.valueTable.put(paramString, paramObject);
  }
  
  public String set(String paramString1, String paramString2)
  {
    OptionInfo localOptionInfo = getInfo(paramString1);
    if (localOptionInfo == null) {
      return "unknown option name";
    }
    paramString2 = valueOf(localOptionInfo, paramString2);
    if ((paramString2 == null) && ((localOptionInfo.kind & 0x1) != 0)) {
      return "value of option " + paramString1 + " must be yes/no/true/false/on/off/1/0";
    }
    if (this.valueTable == null) {
      this.valueTable = new HashMap();
    }
    this.valueTable.put(paramString1, paramString2);
    return null;
  }
  
  public void set(String paramString, Object paramObject)
  {
    set(paramString, paramObject, null);
  }
  
  public void set(String paramString, Object paramObject, SourceMessages paramSourceMessages)
  {
    Object localObject2 = getInfo(paramString);
    if (localObject2 == null)
    {
      error("invalid option key: " + paramString, paramSourceMessages);
      return;
    }
    if ((((OptionInfo)localObject2).kind & 0x1) != 0)
    {
      Object localObject1 = paramObject;
      if ((paramObject instanceof String)) {
        localObject1 = valueOf((OptionInfo)localObject2, (String)paramObject);
      }
      localObject2 = localObject1;
      if (!(localObject1 instanceof Boolean)) {
        error("value for option " + paramString + " must be boolean or yes/no/true/false/on/off/1/0", paramSourceMessages);
      }
    }
    else
    {
      localObject2 = paramObject;
      if (paramObject == null) {
        localObject2 = "";
      }
    }
    if (this.valueTable == null) {
      this.valueTable = new HashMap();
    }
    this.valueTable.put(paramString, localObject2);
  }
  
  public static final class OptionInfo
  {
    Object defaultValue;
    String documentation;
    String key;
    int kind;
    OptionInfo next;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\Options.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */