package gnu.mapping;

import java.lang.ref.WeakReference;

class Entry
{
  Entry chain;
  Object key1;
  Object key2;
  Object value;
  
  public Object getKey1()
  {
    if ((this.key1 instanceof WeakReference)) {
      return ((WeakReference)this.key1).get();
    }
    return this.key1;
  }
  
  public Object getKey2()
  {
    if ((this.key2 instanceof WeakReference)) {
      return ((WeakReference)this.key2).get();
    }
    return this.key2;
  }
  
  public Object getValue()
  {
    if (this.value == this) {
      return null;
    }
    return this.value;
  }
  
  public boolean matches(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == getKey1()) && (paramObject2 == getKey2());
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\Entry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */