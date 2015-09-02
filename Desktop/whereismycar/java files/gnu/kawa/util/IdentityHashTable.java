package gnu.kawa.util;

public class IdentityHashTable<K, V>
  extends GeneralHashTable<K, V>
{
  public IdentityHashTable() {}
  
  public IdentityHashTable(int paramInt)
  {
    super(paramInt);
  }
  
  public int hash(Object paramObject)
  {
    return System.identityHashCode(paramObject);
  }
  
  public boolean matches(K paramK, Object paramObject)
  {
    return paramK == paramObject;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\util\IdentityHashTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */