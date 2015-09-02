package gnu.kawa.lispexpr;

import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;

public class LispPackage
  extends Namespace
{
  Namespace exported;
  NamespaceUse imported;
  NamespaceUse importing;
  LList shadowingSymbols = LList.Empty;
  
  private void addToShadowingSymbols(Symbol paramSymbol)
  {
    for (Object localObject = this.shadowingSymbols; localObject != LList.Empty; localObject = ((Pair)localObject).getCdr())
    {
      localObject = (Pair)localObject;
      if (((Pair)localObject).getCar() == paramSymbol) {
        return;
      }
    }
    this.shadowingSymbols = new Pair(paramSymbol, this.shadowingSymbols);
  }
  
  private boolean removeFromShadowingSymbols(Symbol paramSymbol)
  {
    Object localObject1 = null;
    Object localObject2 = this.shadowingSymbols;
    while (localObject2 != LList.Empty)
    {
      Pair localPair = (Pair)localObject2;
      localObject2 = localPair.getCdr();
      if (localPair.getCar() == paramSymbol)
      {
        if (localObject1 == null) {
          this.shadowingSymbols = ((LList)localObject2);
        }
        for (;;)
        {
          return true;
          ((Pair)localObject1).setCdr(localObject2);
        }
      }
      localObject1 = localPair;
    }
    return false;
  }
  
  public boolean isPresent(String paramString)
  {
    boolean bool = false;
    if (lookupPresent(paramString, paramString.hashCode(), false) != null) {
      bool = true;
    }
    return bool;
  }
  
  public Symbol lookup(String paramString, int paramInt, boolean paramBoolean)
  {
    Object localObject = this.exported.lookup(paramString, paramInt, false);
    if (localObject != null) {
      return (Symbol)localObject;
    }
    localObject = lookupInternal(paramString, paramInt);
    if (localObject != null) {
      return (Symbol)localObject;
    }
    for (localObject = this.imported; localObject != null; localObject = ((NamespaceUse)localObject).nextImported)
    {
      Symbol localSymbol = lookup(paramString, paramInt, false);
      if (localSymbol != null) {
        return localSymbol;
      }
    }
    if (paramBoolean) {
      return add(new Symbol(this, paramString), paramInt);
    }
    return null;
  }
  
  public Symbol lookupPresent(String paramString, int paramInt, boolean paramBoolean)
  {
    Symbol localSymbol2 = this.exported.lookup(paramString, paramInt, false);
    Symbol localSymbol1 = localSymbol2;
    if (localSymbol2 == null) {
      localSymbol1 = super.lookup(paramString, paramInt, paramBoolean);
    }
    return localSymbol1;
  }
  
  public void shadow(String paramString)
  {
    addToShadowingSymbols(lookupPresent(paramString, paramString.hashCode(), true));
  }
  
  public void shadowingImport(Symbol paramSymbol)
  {
    Object localObject = paramSymbol.getName();
    ((String)localObject).hashCode();
    localObject = lookupPresent((String)localObject, ((String)localObject).hashCode(), false);
    if ((localObject != null) && (localObject != paramSymbol)) {
      unintern((Symbol)localObject);
    }
    addToShadowingSymbols(paramSymbol);
  }
  
  public boolean unintern(Symbol paramSymbol)
  {
    boolean bool = false;
    String str = paramSymbol.getName();
    int i = str.hashCode();
    if (this.exported.lookup(str, i, false) == paramSymbol) {
      this.exported.remove(paramSymbol);
    }
    for (;;)
    {
      paramSymbol.setNamespace(null);
      if (removeFromShadowingSymbols(paramSymbol)) {}
      bool = true;
      do
      {
        return bool;
      } while (super.lookup(str, i, false) != paramSymbol);
      super.remove(paramSymbol);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\LispPackage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */