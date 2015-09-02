package gnu.text;

import gnu.kawa.util.AbstractWeakHashTable;
import gnu.kawa.util.AbstractWeakHashTable.WEntry;

class CharMap
  extends AbstractWeakHashTable<Char, Char>
{
  public Char get(int paramInt)
  {
    cleanup();
    int i = hashToIndex(paramInt);
    for (Object localObject = ((AbstractWeakHashTable.WEntry[])this.table)[i]; localObject != null; localObject = ((AbstractWeakHashTable.WEntry)localObject).next)
    {
      Char localChar = (Char)((AbstractWeakHashTable.WEntry)localObject).getValue();
      if (localChar.intValue() == paramInt) {
        return localChar;
      }
    }
    localObject = new Char(paramInt);
    super.put(localObject, localObject);
    return (Char)localObject;
  }
  
  protected Char getKeyFromValue(Char paramChar)
  {
    return paramChar;
  }
  
  protected boolean matches(Char paramChar1, Char paramChar2)
  {
    return paramChar1.intValue() == paramChar2.intValue();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\CharMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */