package gnu.kawa.util;

import java.util.Map.Entry;

public class HashNode<K, V>
  implements Map.Entry<K, V>
{
  int hash;
  K key;
  public HashNode<K, V> next;
  V value;
  
  public HashNode(K paramK, V paramV)
  {
    this.key = paramK;
    this.value = paramV;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof HashNode)) {}
    label28:
    do
    {
      do
      {
        return false;
        paramObject = (HashNode)paramObject;
        if (this.key != null) {
          break;
        }
      } while (((HashNode)paramObject).key != null);
      if (this.value != null) {
        break label61;
      }
    } while (((HashNode)paramObject).value != null);
    for (;;)
    {
      return true;
      if (!this.key.equals(((HashNode)paramObject).key)) {
        break;
      }
      break label28;
      label61:
      if (!this.value.equals(((HashNode)paramObject).value)) {
        break;
      }
    }
  }
  
  public V get(V paramV)
  {
    return (V)getValue();
  }
  
  public K getKey()
  {
    return (K)this.key;
  }
  
  public V getValue()
  {
    return (V)this.value;
  }
  
  public int hashCode()
  {
    int j = 0;
    int i;
    if (this.key == null)
    {
      i = 0;
      if (this.value != null) {
        break label33;
      }
    }
    for (;;)
    {
      return i ^ j;
      i = this.key.hashCode();
      break;
      label33:
      j = this.value.hashCode();
    }
  }
  
  public V setValue(V paramV)
  {
    Object localObject = this.value;
    this.value = paramV;
    return (V)localObject;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\util\HashNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */