package gnu.mapping;

import java.lang.ref.WeakReference;

public class Table2D
{
  private static Table2D instance = new Table2D();
  int log2Size;
  int mask;
  int num_bindings;
  Entry[] table;
  
  public Table2D()
  {
    this(64);
  }
  
  public Table2D(int paramInt)
  {
    for (this.log2Size = 4; paramInt > 1 << this.log2Size; this.log2Size += 1) {}
    paramInt = 1 << this.log2Size;
    this.table = new Entry[paramInt];
    this.mask = (paramInt - 1);
  }
  
  public static final Table2D getInstance()
  {
    return instance;
  }
  
  public Object get(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    paramObject1 = lookup(paramObject1, paramObject2, System.identityHashCode(paramObject1), System.identityHashCode(paramObject2), false);
    if ((paramObject1 == null) || (((Entry)paramObject1).value == paramObject1)) {
      return paramObject3;
    }
    return ((Entry)paramObject1).value;
  }
  
  public boolean isBound(Object paramObject1, Object paramObject2)
  {
    boolean bool2 = false;
    paramObject1 = lookup(paramObject1, paramObject2, System.identityHashCode(paramObject1), System.identityHashCode(paramObject2), false);
    boolean bool1 = bool2;
    if (paramObject1 != null)
    {
      bool1 = bool2;
      if (((Entry)paramObject1).value != paramObject1) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  protected Entry lookup(Object paramObject1, Object paramObject2, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    paramInt2 = (paramInt1 ^ paramInt2) & this.mask;
    Object localObject2 = null;
    Entry localEntry = this.table[paramInt2];
    Object localObject1 = localEntry;
    if (localObject1 != null)
    {
      Object localObject4 = ((Entry)localObject1).key1;
      Object localObject5 = ((Entry)localObject1).key2;
      paramInt1 = 0;
      Object localObject3 = localObject4;
      if ((localObject4 instanceof WeakReference))
      {
        localObject3 = ((WeakReference)localObject4).get();
        if (localObject3 == null) {
          paramInt1 = 1;
        }
      }
      else
      {
        label77:
        localObject4 = localObject5;
        if ((localObject5 instanceof WeakReference))
        {
          localObject4 = ((WeakReference)localObject5).get();
          if (localObject4 != null) {
            break label160;
          }
          label104:
          paramInt1 = 1;
        }
        localObject5 = ((Entry)localObject1).chain;
        if (paramInt1 == 0) {
          break label173;
        }
        if (localObject2 != null) {
          break label163;
        }
        this.table[paramInt2] = localObject5;
        label131:
        this.num_bindings -= 1;
        ((Entry)localObject1).value = localObject1;
      }
      for (;;)
      {
        localObject1 = localObject5;
        break;
        paramInt1 = 0;
        break label77;
        label160:
        break label104;
        label163:
        ((Entry)localObject2).chain = ((Entry)localObject5);
        break label131;
        label173:
        if ((localObject3 == paramObject1) && (localObject4 == paramObject2)) {
          return (Entry)localObject1;
        }
        localObject2 = localObject1;
      }
    }
    if (paramBoolean)
    {
      localObject1 = new Entry();
      paramObject1 = wrapReference(paramObject1);
      paramObject2 = wrapReference(paramObject2);
      ((Entry)localObject1).key1 = paramObject1;
      ((Entry)localObject1).key2 = paramObject2;
      this.num_bindings += 1;
      ((Entry)localObject1).chain = localEntry;
      this.table[paramInt2] = localObject1;
      ((Entry)localObject1).value = localObject1;
      return (Entry)localObject1;
    }
    return null;
  }
  
  public Object put(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    paramObject1 = lookup(paramObject1, paramObject2, System.identityHashCode(paramObject1), System.identityHashCode(paramObject2), true);
    paramObject2 = ((Entry)paramObject1).getValue();
    ((Entry)paramObject1).value = paramObject3;
    return paramObject2;
  }
  
  void rehash()
  {
    Entry[] arrayOfEntry1 = this.table;
    int i = arrayOfEntry1.length;
    int j = i * 2;
    Entry[] arrayOfEntry2 = new Entry[j];
    int k = j - 1;
    this.num_bindings = 0;
    Object localObject1;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      localObject1 = arrayOfEntry1[j];
      i = j;
    } while (localObject1 == null);
    Object localObject3 = ((Entry)localObject1).key1;
    Object localObject4 = ((Entry)localObject1).key2;
    i = 0;
    Object localObject2 = localObject3;
    if ((localObject3 instanceof WeakReference))
    {
      localObject2 = ((WeakReference)localObject3).get();
      if (localObject2 == null) {
        i = 1;
      }
    }
    else
    {
      label98:
      localObject3 = localObject4;
      if ((localObject4 instanceof WeakReference))
      {
        localObject3 = ((WeakReference)localObject4).get();
        if (localObject3 != null) {
          break label153;
        }
        i = 1;
      }
      label125:
      localObject4 = ((Entry)localObject1).chain;
      if (i == 0) {
        break label159;
      }
      ((Entry)localObject1).value = localObject1;
    }
    for (;;)
    {
      localObject1 = localObject4;
      break;
      i = 0;
      break label98;
      label153:
      i = 0;
      break label125;
      label159:
      i = (System.identityHashCode(localObject2) ^ System.identityHashCode(localObject3)) & k;
      ((Entry)localObject1).chain = arrayOfEntry2[i];
      arrayOfEntry2[i] = localObject1;
      this.num_bindings += 1;
    }
    this.table = arrayOfEntry2;
    this.log2Size += 1;
    this.mask = k;
  }
  
  public Object remove(Object paramObject1, Object paramObject2)
  {
    return remove(paramObject1, paramObject2, System.identityHashCode(paramObject1) ^ System.identityHashCode(paramObject2));
  }
  
  public Object remove(Object paramObject1, Object paramObject2, int paramInt)
  {
    return remove(paramObject1, paramObject2, paramInt);
  }
  
  public Object remove(Object paramObject1, Object paramObject2, int paramInt1, int paramInt2)
  {
    int i = (paramInt1 ^ paramInt2) & this.mask;
    Object localObject2 = null;
    Object localObject1 = this.table[i];
    if (localObject1 != null)
    {
      Object localObject4 = ((Entry)localObject1).key1;
      Object localObject5 = ((Entry)localObject1).key2;
      paramInt1 = 0;
      Object localObject3 = localObject4;
      label73:
      label102:
      Object localObject6;
      if ((localObject4 instanceof WeakReference))
      {
        localObject3 = ((WeakReference)localObject4).get();
        if (localObject3 == null) {
          paramInt1 = 1;
        }
      }
      else
      {
        localObject4 = localObject5;
        if ((localObject5 instanceof WeakReference))
        {
          localObject4 = ((WeakReference)localObject5).get();
          if (localObject4 != null) {
            break label183;
          }
          paramInt1 = 1;
        }
        localObject5 = ((Entry)localObject1).chain;
        localObject6 = ((Entry)localObject1).value;
        if ((localObject3 != paramObject1) || (localObject4 != paramObject2)) {
          break label188;
        }
        paramInt2 = 1;
        label131:
        if ((paramInt1 == 0) && (paramInt2 == 0)) {
          break label204;
        }
        if (localObject2 != null) {
          break label194;
        }
        this.table[i] = localObject5;
        label154:
        this.num_bindings -= 1;
        ((Entry)localObject1).value = localObject1;
      }
      for (;;)
      {
        localObject1 = localObject5;
        break;
        paramInt1 = 0;
        break label73;
        label183:
        paramInt1 = 0;
        break label102;
        label188:
        paramInt2 = 0;
        break label131;
        label194:
        ((Entry)localObject2).chain = ((Entry)localObject5);
        break label154;
        label204:
        if (paramInt2 != 0) {
          return localObject6;
        }
        localObject2 = localObject1;
      }
    }
    return null;
  }
  
  protected Object wrapReference(Object paramObject)
  {
    if ((paramObject == null) || ((paramObject instanceof Symbol))) {
      return paramObject;
    }
    return new WeakReference(paramObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\Table2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */