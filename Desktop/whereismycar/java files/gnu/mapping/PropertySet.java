package gnu.mapping;

public abstract class PropertySet
  implements Named
{
  public static final Symbol nameKey = Namespace.EmptyNamespace.getSymbol("name");
  private Object[] properties;
  
  public static Object[] setProperty(Object[] paramArrayOfObject, Object paramObject1, Object paramObject2)
  {
    Object[] arrayOfObject = paramArrayOfObject;
    Object localObject;
    int j;
    if (arrayOfObject == null)
    {
      localObject = new Object[10];
      paramArrayOfObject = (Object[])localObject;
      j = 0;
    }
    for (;;)
    {
      localObject[j] = paramObject1;
      localObject[(j + 1)] = paramObject2;
      return paramArrayOfObject;
      int i = -1;
      j = arrayOfObject.length;
      for (;;)
      {
        int k = j - 2;
        if (k < 0) {
          break;
        }
        localObject = arrayOfObject[k];
        if (localObject == paramObject1)
        {
          paramObject1 = arrayOfObject[(k + 1)];
          arrayOfObject[(k + 1)] = paramObject2;
          return paramArrayOfObject;
        }
        j = k;
        if (localObject == null)
        {
          i = k;
          j = k;
        }
      }
      j = i;
      localObject = arrayOfObject;
      if (i < 0)
      {
        j = arrayOfObject.length;
        paramArrayOfObject = new Object[j * 2];
        System.arraycopy(arrayOfObject, 0, paramArrayOfObject, 0, j);
        localObject = paramArrayOfObject;
      }
    }
  }
  
  public String getName()
  {
    Object localObject = getProperty(nameKey, null);
    if (localObject == null) {
      return null;
    }
    if ((localObject instanceof Symbol)) {
      return ((Symbol)localObject).getName();
    }
    return localObject.toString();
  }
  
  public Object getProperty(Object paramObject1, Object paramObject2)
  {
    Object localObject = paramObject2;
    if (this.properties != null)
    {
      int i = this.properties.length;
      int j;
      do
      {
        j = i - 2;
        localObject = paramObject2;
        if (j < 0) {
          break;
        }
        i = j;
      } while (this.properties[j] != paramObject1);
      localObject = this.properties[(j + 1)];
    }
    return localObject;
  }
  
  public Object getSymbol()
  {
    return getProperty(nameKey, null);
  }
  
  public Object removeProperty(Object paramObject)
  {
    Object[] arrayOfObject = this.properties;
    if (arrayOfObject == null) {
      return null;
    }
    int i = arrayOfObject.length;
    int j;
    do
    {
      j = i - 2;
      if (j < 0) {
        break;
      }
      i = j;
    } while (arrayOfObject[j] != paramObject);
    paramObject = arrayOfObject[(j + 1)];
    arrayOfObject[j] = null;
    arrayOfObject[(j + 1)] = null;
    return paramObject;
    return null;
  }
  
  public final void setName(String paramString)
  {
    setProperty(nameKey, paramString);
  }
  
  public void setProperty(Object paramObject1, Object paramObject2)
  {
    try
    {
      this.properties = setProperty(this.properties, paramObject1, paramObject2);
      return;
    }
    finally
    {
      paramObject1 = finally;
      throw ((Throwable)paramObject1);
    }
  }
  
  public final void setSymbol(Object paramObject)
  {
    setProperty(nameKey, paramObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\PropertySet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */