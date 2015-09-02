package gnu.kawa.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map.Entry;

public abstract class AbstractWeakHashTable<K, V>
  extends AbstractHashTable<WEntry<K, V>, K, V>
{
  ReferenceQueue<V> rqueue = new ReferenceQueue();
  
  public AbstractWeakHashTable()
  {
    super(64);
  }
  
  public AbstractWeakHashTable(int paramInt)
  {
    super(paramInt);
  }
  
  static <Entry extends Map.Entry<K, V>, K, V> void cleanup(AbstractHashTable<Entry, ?, ?> paramAbstractHashTable, ReferenceQueue<?> paramReferenceQueue)
  {
    Map.Entry localEntry2 = (Map.Entry)paramReferenceQueue.poll();
    if (localEntry2 == null) {
      return;
    }
    int i = paramAbstractHashTable.hashToIndex(paramAbstractHashTable.getEntryHashCode(localEntry2));
    Object localObject2 = null;
    Map.Entry localEntry1;
    for (Object localObject1 = paramAbstractHashTable.table[i];; localObject1 = localEntry1)
    {
      if (localObject1 != null)
      {
        localEntry1 = paramAbstractHashTable.getEntryNext((Map.Entry)localObject1);
        if (localObject1 != localEntry2) {
          break label90;
        }
        if (localObject2 != null) {
          break label80;
        }
        paramAbstractHashTable.table[i] = localEntry1;
      }
      for (;;)
      {
        paramAbstractHashTable.num_bindings -= 1;
        break;
        label80:
        paramAbstractHashTable.setEntryNext((Map.Entry)localObject2, localEntry1);
      }
      label90:
      localObject2 = localObject1;
    }
  }
  
  protected WEntry<K, V>[] allocEntries(int paramInt)
  {
    return (WEntry[])new WEntry[paramInt];
  }
  
  protected void cleanup()
  {
    cleanup(this, this.rqueue);
  }
  
  public V get(Object paramObject, V paramV)
  {
    cleanup();
    return (V)super.get(paramObject, paramV);
  }
  
  protected int getEntryHashCode(WEntry<K, V> paramWEntry)
  {
    return paramWEntry.hash;
  }
  
  protected WEntry<K, V> getEntryNext(WEntry<K, V> paramWEntry)
  {
    return paramWEntry.next;
  }
  
  protected abstract K getKeyFromValue(V paramV);
  
  protected V getValueIfMatching(WEntry<K, V> paramWEntry, Object paramObject)
  {
    paramWEntry = paramWEntry.getValue();
    if ((paramWEntry != null) && (matches(getKeyFromValue(paramWEntry), paramObject))) {
      return paramWEntry;
    }
    return null;
  }
  
  public int hash(Object paramObject)
  {
    return System.identityHashCode(paramObject);
  }
  
  protected WEntry<K, V> makeEntry(K paramK, int paramInt, V paramV)
  {
    return new WEntry(paramV, this, paramInt);
  }
  
  public V put(K paramK, V paramV)
  {
    cleanup();
    int j = hash(paramK);
    int i = hashToIndex(j);
    WEntry localWEntry1 = ((WEntry[])this.table)[i];
    paramK = localWEntry1;
    K ? = null;
    Object localObject1 = null;
    if (paramK == null)
    {
      int k = this.num_bindings + 1;
      this.num_bindings = k;
      if (k >= ((WEntry[])this.table).length)
      {
        rehash();
        i = hashToIndex(j);
        localWEntry1 = ((WEntry[])this.table)[i];
      }
      paramK = makeEntry(null, j, paramV);
      paramK.next = localWEntry1;
      ((WEntry[])this.table)[i] = paramK;
      return (V)localObject1;
    }
    Object localObject2 = paramK.getValue();
    if (localObject2 == paramV) {
      return (V)localObject2;
    }
    WEntry localWEntry2 = paramK.next;
    if ((localObject2 != null) && (valuesEqual(localObject2, paramV))) {
      if (? == null)
      {
        ((WEntry[])this.table)[i] = localWEntry2;
        label173:
        localObject1 = localObject2;
      }
    }
    for (;;)
    {
      paramK = localWEntry2;
      break;
      ?.next = localWEntry2;
      break label173;
      ? = paramK;
    }
  }
  
  protected void setEntryNext(WEntry<K, V> paramWEntry1, WEntry<K, V> paramWEntry2)
  {
    paramWEntry1.next = paramWEntry2;
  }
  
  protected boolean valuesEqual(V paramV1, V paramV2)
  {
    return paramV1 == paramV2;
  }
  
  public static class WEntry<K, V>
    extends WeakReference<V>
    implements Map.Entry<K, V>
  {
    public int hash;
    AbstractWeakHashTable<K, V> htable;
    public WEntry next;
    
    public WEntry(V paramV, AbstractWeakHashTable<K, V> paramAbstractWeakHashTable, int paramInt)
    {
      super(paramAbstractWeakHashTable.rqueue);
      this.htable = paramAbstractWeakHashTable;
      this.hash = paramInt;
    }
    
    public K getKey()
    {
      Object localObject = get();
      if (localObject == null) {
        return null;
      }
      return (K)this.htable.getKeyFromValue(localObject);
    }
    
    public V getValue()
    {
      return (V)get();
    }
    
    public V setValue(V paramV)
    {
      throw new UnsupportedOperationException();
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\util\AbstractWeakHashTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */