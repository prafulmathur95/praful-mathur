package gnu.mapping;

public class PropertyKey<T>
{
  String name;
  
  public PropertyKey(String paramString)
  {
    this.name = paramString;
  }
  
  public final T get(PropertySet paramPropertySet)
  {
    return (T)get(paramPropertySet, null);
  }
  
  public T get(PropertySet paramPropertySet, T paramT)
  {
    return (T)paramPropertySet.getProperty(this, paramT);
  }
  
  public void set(PropertySet paramPropertySet, T paramT)
  {
    paramPropertySet.setProperty(this, paramT);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\PropertyKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */