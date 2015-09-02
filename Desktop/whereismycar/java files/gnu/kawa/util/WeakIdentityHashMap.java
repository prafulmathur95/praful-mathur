package gnu.kawa.util;

import java.lang.ref.ReferenceQueue;

public class WeakIdentityHashMap<K, V>
  extends AbstractHashTable<WeakHashNode<K, V>, K, V>
{
  ReferenceQueue<K> rqueue = new ReferenceQueue();
  
  public WeakIdentityHashMap()
  {
    super(64);
  }
  
  public WeakIdentityHashMap(int paramInt)
  {
    super(paramInt);
  }
  
  protected WeakHashNode<K, V>[] allocEntries(int paramInt)
  {
    return (WeakHashNode[])new WeakHashNode[paramInt];
  }
  
  void cleanup()
  {
    AbstractWeakHashTable.cleanup(this, this.rqueue);
  }
  
  public V get(Object paramObject, V paramV)
  {
    cleanup();
    return (V)super.get(paramObject, paramV);
  }
  
  protected int getEntryHashCode(WeakHashNode<K, V> paramWeakHashNode)
  {
    return paramWeakHashNode.hash;
  }
  
  protected WeakHashNode<K, V> getEntryNext(WeakHashNode<K, V> paramWeakHashNode)
  {
    return paramWeakHashNode.next;
  }
  
  public int hash(Object paramObject)
  {
    return System.identityHashCode(paramObject);
  }
  
  protected WeakHashNode<K, V> makeEntry(K paramK, int paramInt, V paramV)
  {
    paramK = new WeakHashNode(paramK, this.rqueue, paramInt);
    paramK.value = paramV;
    return paramK;
  }
  
  protected boolean matches(K paramK, Object paramObject)
  {
    return paramK == paramObject;
  }
  
  public V put(K paramK, int paramInt, V paramV)
  {
    cleanup();
    return (V)super.put(paramK, paramInt, paramV);
  }
  
  public V remove(Object paramObject)
  {
    cleanup();
    return (V)super.remove(paramObject);
  }
  
  protected void setEntryNext(WeakHashNode<K, V> paramWeakHashNode1, WeakHashNode<K, V> paramWeakHashNode2)
  {
    paramWeakHashNode1.next = paramWeakHashNode2;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\util\WeakIdentityHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */