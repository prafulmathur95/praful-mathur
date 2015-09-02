package gnu.mapping;

import java.io.PrintWriter;

public abstract class Location
{
  public static final String UNBOUND = new String("(unbound)");
  
  public static IndirectableLocation make(Symbol paramSymbol)
  {
    paramSymbol = new PlainLocation(paramSymbol, null);
    paramSymbol.base = null;
    paramSymbol.value = UNBOUND;
    return paramSymbol;
  }
  
  public static IndirectableLocation make(String paramString)
  {
    paramString = new PlainLocation(Namespace.EmptyNamespace.getSymbol(paramString.intern()), null);
    paramString.base = null;
    paramString.value = UNBOUND;
    return paramString;
  }
  
  public static Location make(Object paramObject, String paramString)
  {
    paramString = new ThreadLocation(paramString);
    paramString.setGlobal(paramObject);
    return paramString;
  }
  
  public boolean entered()
  {
    return false;
  }
  
  public final Object get()
  {
    String str = UNBOUND;
    Object localObject = get(str);
    if (localObject == str) {
      throw new UnboundLocationException(this);
    }
    return localObject;
  }
  
  public abstract Object get(Object paramObject);
  
  public Location getBase()
  {
    return this;
  }
  
  public Object getKeyProperty()
  {
    return null;
  }
  
  public Symbol getKeySymbol()
  {
    return null;
  }
  
  public final Object getValue()
  {
    return get(null);
  }
  
  public boolean isBound()
  {
    String str = UNBOUND;
    return get(str) != str;
  }
  
  public boolean isConstant()
  {
    return false;
  }
  
  public void print(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.print("#<location ");
    Object localObject1 = getKeySymbol();
    if (localObject1 != null) {
      paramPrintWriter.print(localObject1);
    }
    localObject1 = UNBOUND;
    Object localObject2 = get(localObject1);
    if (localObject2 != localObject1)
    {
      paramPrintWriter.print(" -> ");
      paramPrintWriter.print(localObject2);
    }
    for (;;)
    {
      paramPrintWriter.print('>');
      return;
      paramPrintWriter.print("(unbound)");
    }
  }
  
  public abstract void set(Object paramObject);
  
  public void setRestore(Object paramObject)
  {
    set(paramObject);
  }
  
  public final Object setValue(Object paramObject)
  {
    Object localObject = get(null);
    set(paramObject);
    return localObject;
  }
  
  public Object setWithSave(Object paramObject)
  {
    Object localObject = get(UNBOUND);
    set(paramObject);
    return localObject;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(getClass().getName());
    Object localObject = getKeySymbol();
    localStringBuffer.append('[');
    if (localObject != null)
    {
      localStringBuffer.append(localObject);
      localObject = getKeyProperty();
      if ((localObject != null) && (localObject != this))
      {
        localStringBuffer.append('/');
        localStringBuffer.append(localObject);
      }
    }
    localStringBuffer.append("]");
    return localStringBuffer.toString();
  }
  
  public void undefine()
  {
    set(UNBOUND);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\Location.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */