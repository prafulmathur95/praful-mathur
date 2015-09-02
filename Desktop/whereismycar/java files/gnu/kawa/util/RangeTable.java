package gnu.kawa.util;

import java.util.Hashtable;

public class RangeTable
  implements Cloneable
{
  Hashtable hash = new Hashtable(200);
  Object[] index = new Object[''];
  
  public Object clone()
  {
    return copy();
  }
  
  public RangeTable copy()
  {
    RangeTable localRangeTable = new RangeTable();
    localRangeTable.index = ((Object[])this.index.clone());
    localRangeTable.hash = ((Hashtable)this.hash.clone());
    return localRangeTable;
  }
  
  public Object lookup(int paramInt, Object paramObject)
  {
    if ((paramInt & 0x7F) == paramInt) {
      return this.index[paramInt];
    }
    return this.hash.get(new Integer(paramInt));
  }
  
  public void remove(int paramInt)
  {
    remove(paramInt, paramInt);
  }
  
  public void remove(int paramInt1, int paramInt2)
  {
    if (paramInt1 > paramInt2) {}
    label50:
    for (;;)
    {
      return;
      if ((paramInt1 & 0x7F) == paramInt1) {
        this.index[paramInt1] = null;
      }
      for (;;)
      {
        if (paramInt1 == paramInt2) {
          break label50;
        }
        paramInt1 += 1;
        break;
        this.hash.remove(new Integer(paramInt1));
      }
    }
  }
  
  public void set(int paramInt1, int paramInt2, Object paramObject)
  {
    if (paramInt1 > paramInt2) {}
    label51:
    for (;;)
    {
      return;
      if ((paramInt1 & 0x7F) == paramInt1) {
        this.index[paramInt1] = paramObject;
      }
      for (;;)
      {
        if (paramInt1 == paramInt2) {
          break label51;
        }
        paramInt1 += 1;
        break;
        this.hash.put(new Integer(paramInt1), paramObject);
      }
    }
  }
  
  public void set(int paramInt, Object paramObject)
  {
    set(paramInt, paramInt, paramObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\util\RangeTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */