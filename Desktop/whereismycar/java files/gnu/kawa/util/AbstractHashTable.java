package gnu.kawa.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class AbstractHashTable<Entry extends Map.Entry<K, V>, K, V>
  extends AbstractMap<K, V>
{
  public static final int DEFAULT_INITIAL_SIZE = 64;
  protected int mask;
  protected int num_bindings;
  protected Entry[] table;
  
  public AbstractHashTable()
  {
    this(64);
  }
  
  public AbstractHashTable(int paramInt)
  {
    int i = 4;
    while (paramInt > 1 << i) {
      i += 1;
    }
    paramInt = 1 << i;
    this.table = allocEntries(paramInt);
    this.mask = (paramInt - 1);
  }
  
  protected abstract Entry[] allocEntries(int paramInt);
  
  public void clear()
  {
    Map.Entry[] arrayOfEntry = this.table;
    int i = arrayOfEntry.length;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      Map.Entry localEntry;
      for (Object localObject = arrayOfEntry[i]; localObject != null; localObject = localEntry)
      {
        localEntry = getEntryNext((Map.Entry)localObject);
        setEntryNext((Map.Entry)localObject, null);
      }
      arrayOfEntry[i] = null;
    }
    this.num_bindings = 0;
  }
  
  public Set<Map.Entry<K, V>> entrySet()
  {
    return new AbstractEntrySet(this);
  }
  
  public V get(Object paramObject)
  {
    return (V)get(paramObject, null);
  }
  
  public V get(Object paramObject, V paramV)
  {
    paramObject = getNode(paramObject);
    if (paramObject == null) {
      return paramV;
    }
    return (V)((Map.Entry)paramObject).getValue();
  }
  
  protected abstract int getEntryHashCode(Entry paramEntry);
  
  protected abstract Entry getEntryNext(Entry paramEntry);
  
  public Entry getNode(Object paramObject)
  {
    int i = hash(paramObject);
    int j = hashToIndex(i);
    for (Map.Entry localEntry = this.table[j]; localEntry != null; localEntry = getEntryNext(localEntry)) {
      if (matches(paramObject, i, localEntry)) {
        return localEntry;
      }
    }
    return null;
  }
  
  public int hash(Object paramObject)
  {
    if (paramObject == null) {
      return 0;
    }
    return paramObject.hashCode();
  }
  
  protected int hashToIndex(int paramInt)
  {
    return this.mask & (paramInt ^ paramInt >>> 15);
  }
  
  protected abstract Entry makeEntry(K paramK, int paramInt, V paramV);
  
  protected boolean matches(Object paramObject, int paramInt, Entry paramEntry)
  {
    return (getEntryHashCode(paramEntry) == paramInt) && (matches(paramEntry.getKey(), paramObject));
  }
  
  protected boolean matches(K paramK, Object paramObject)
  {
    return (paramK == paramObject) || ((paramK != null) && (paramK.equals(paramObject)));
  }
  
  public V put(K paramK, int paramInt, V paramV)
  {
    int i = hashToIndex(paramInt);
    Map.Entry localEntry1 = this.table[i];
    for (Map.Entry localEntry2 = localEntry1;; localEntry2 = getEntryNext(localEntry2))
    {
      if (localEntry2 == null)
      {
        int j = this.num_bindings + 1;
        this.num_bindings = j;
        if (j >= this.table.length)
        {
          rehash();
          i = hashToIndex(paramInt);
          localEntry1 = this.table[i];
        }
        paramK = makeEntry(paramK, paramInt, paramV);
        setEntryNext(paramK, localEntry1);
        this.table[i] = paramK;
        return null;
      }
      if (matches(paramK, paramInt, localEntry2))
      {
        paramK = localEntry2.getValue();
        localEntry2.setValue(paramV);
        return paramK;
      }
    }
  }
  
  public V put(K paramK, V paramV)
  {
    return (V)put(paramK, hash(paramK), paramV);
  }
  
  protected void rehash()
  {
    Map.Entry[] arrayOfEntry1 = this.table;
    int i = arrayOfEntry1.length;
    int j = i * 2;
    Map.Entry[] arrayOfEntry2 = allocEntries(j);
    this.table = arrayOfEntry2;
    this.mask = (j - 1);
    j = i - 1;
    if (j >= 0)
    {
      Object localObject2 = arrayOfEntry1[j];
      Object localObject1 = localObject2;
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (getEntryNext((Map.Entry)localObject2) != null)
        {
          Object localObject3 = null;
          Map.Entry localEntry;
          do
          {
            localObject1 = localObject2;
            localEntry = getEntryNext((Map.Entry)localObject1);
            setEntryNext((Map.Entry)localObject1, (Map.Entry)localObject3);
            localObject2 = localEntry;
            localObject3 = localObject1;
          } while (localEntry != null);
        }
      }
      for (;;)
      {
        i = j;
        if (localObject1 == null) {
          break;
        }
        localObject2 = getEntryNext((Map.Entry)localObject1);
        i = hashToIndex(getEntryHashCode((Map.Entry)localObject1));
        setEntryNext((Map.Entry)localObject1, arrayOfEntry2[i]);
        arrayOfEntry2[i] = localObject1;
        localObject1 = localObject2;
      }
    }
  }
  
  public V remove(Object paramObject)
  {
    int i = hash(paramObject);
    int j = hashToIndex(i);
    Object localObject2 = null;
    Map.Entry localEntry;
    for (Object localObject1 = this.table[j]; localObject1 != null; localObject1 = localEntry)
    {
      localEntry = getEntryNext((Map.Entry)localObject1);
      if (matches(paramObject, i, (Map.Entry)localObject1))
      {
        if (localObject2 == null) {
          this.table[j] = localEntry;
        }
        for (;;)
        {
          this.num_bindings -= 1;
          return (V)((Map.Entry)localObject1).getValue();
          setEntryNext((Map.Entry)localObject2, localEntry);
        }
      }
      localObject2 = localObject1;
    }
    return null;
  }
  
  protected abstract void setEntryNext(Entry paramEntry1, Entry paramEntry2);
  
  public int size()
  {
    return this.num_bindings;
  }
  
  static class AbstractEntrySet<Entry extends Map.Entry<K, V>, K, V>
    extends AbstractSet<Entry>
  {
    AbstractHashTable<Entry, K, V> htable;
    
    public AbstractEntrySet(AbstractHashTable<Entry, K, V> paramAbstractHashTable)
    {
      this.htable = paramAbstractHashTable;
    }
    
    public Iterator<Entry> iterator()
    {
      new Iterator()
      {
        int curIndex = -1;
        Entry currentEntry;
        Entry nextEntry;
        int nextIndex;
        Entry previousEntry;
        
        private void advance()
        {
          while (this.nextEntry == null)
          {
            int i = this.nextIndex - 1;
            this.nextIndex = i;
            if (i < 0) {
              break;
            }
            this.nextEntry = AbstractHashTable.AbstractEntrySet.this.htable.table[this.nextIndex];
          }
        }
        
        public boolean hasNext()
        {
          if (this.curIndex < 0)
          {
            this.nextIndex = AbstractHashTable.AbstractEntrySet.this.htable.table.length;
            this.curIndex = this.nextIndex;
            advance();
          }
          return this.nextEntry != null;
        }
        
        public Entry next()
        {
          if (this.nextEntry == null) {
            throw new NoSuchElementException();
          }
          this.previousEntry = this.currentEntry;
          this.currentEntry = this.nextEntry;
          this.curIndex = this.nextIndex;
          this.nextEntry = AbstractHashTable.AbstractEntrySet.this.htable.getEntryNext(this.currentEntry);
          advance();
          return this.currentEntry;
        }
        
        public void remove()
        {
          if (this.previousEntry == this.currentEntry) {
            throw new IllegalStateException();
          }
          if (this.previousEntry == null) {
            AbstractHashTable.AbstractEntrySet.this.htable.table[this.curIndex] = this.nextEntry;
          }
          for (;;)
          {
            AbstractHashTable localAbstractHashTable = AbstractHashTable.AbstractEntrySet.this.htable;
            localAbstractHashTable.num_bindings -= 1;
            this.previousEntry = this.currentEntry;
            return;
            AbstractHashTable.AbstractEntrySet.this.htable.setEntryNext(this.previousEntry, this.nextEntry);
          }
        }
      };
    }
    
    public int size()
    {
      return this.htable.size();
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\util\AbstractHashTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */