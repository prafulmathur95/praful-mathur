package gnu.expr;

import gnu.kawa.util.GeneralHashTable;
import gnu.kawa.util.HashNode;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.Symbol;

public class NameLookup
  extends GeneralHashTable<Object, Declaration>
{
  static final Symbol KEY = Symbol.makeUninterned("<current-NameLookup>");
  Language language;
  
  public NameLookup(Language paramLanguage)
  {
    this.language = paramLanguage;
  }
  
  public static NameLookup getInstance(Environment paramEnvironment, Language paramLanguage)
  {
    paramEnvironment = paramEnvironment.getLocation(KEY);
    NameLookup localNameLookup = (NameLookup)paramEnvironment.get(null);
    if (localNameLookup == null)
    {
      paramLanguage = new NameLookup(paramLanguage);
      paramEnvironment.set(paramLanguage);
      return paramLanguage;
    }
    localNameLookup.setLanguage(paramLanguage);
    return localNameLookup;
  }
  
  public static void setInstance(Environment paramEnvironment, NameLookup paramNameLookup)
  {
    if (paramNameLookup == null)
    {
      paramEnvironment.remove(KEY);
      return;
    }
    paramEnvironment.put(KEY, null, paramNameLookup);
  }
  
  public Language getLanguage()
  {
    return this.language;
  }
  
  public Declaration lookup(Object paramObject, int paramInt)
  {
    int i = hashToIndex(hash(paramObject));
    for (HashNode localHashNode = ((HashNode[])this.table)[i]; localHashNode != null; localHashNode = localHashNode.next)
    {
      Declaration localDeclaration = (Declaration)localHashNode.getValue();
      if ((paramObject.equals(localDeclaration.getSymbol())) && (this.language.hasNamespace(localDeclaration, paramInt))) {
        return localDeclaration;
      }
    }
    return null;
  }
  
  public Declaration lookup(Object paramObject, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 2;; i = 1) {
      return lookup(paramObject, i);
    }
  }
  
  public void pop(ScopeExp paramScopeExp)
  {
    for (paramScopeExp = paramScopeExp.firstDecl(); paramScopeExp != null; paramScopeExp = paramScopeExp.nextDecl()) {
      pop(paramScopeExp);
    }
  }
  
  public boolean pop(Declaration paramDeclaration)
  {
    Object localObject1 = paramDeclaration.getSymbol();
    if (localObject1 == null) {
      return false;
    }
    int i = hash(localObject1);
    Object localObject2 = null;
    i = hashToIndex(i);
    HashNode localHashNode;
    for (localObject1 = ((HashNode[])this.table)[i]; localObject1 != null; localObject1 = localHashNode)
    {
      localHashNode = ((HashNode)localObject1).next;
      if (((HashNode)localObject1).getValue() == paramDeclaration)
      {
        if (localObject2 == null) {
          ((HashNode[])this.table)[i] = localHashNode;
        }
        for (;;)
        {
          this.num_bindings -= 1;
          return true;
          ((HashNode)localObject2).next = localHashNode;
        }
      }
      localObject2 = localObject1;
    }
    return false;
  }
  
  public void push(Declaration paramDeclaration)
  {
    Object localObject = paramDeclaration.getSymbol();
    if (localObject == null) {
      return;
    }
    int i = this.num_bindings + 1;
    this.num_bindings = i;
    if (i >= ((HashNode[])this.table).length) {
      rehash();
    }
    i = hash(localObject);
    paramDeclaration = makeEntry(localObject, i, paramDeclaration);
    i = hashToIndex(i);
    paramDeclaration.next = ((HashNode[])this.table)[i];
    ((HashNode[])this.table)[i] = paramDeclaration;
  }
  
  public void push(ScopeExp paramScopeExp)
  {
    for (paramScopeExp = paramScopeExp.firstDecl(); paramScopeExp != null; paramScopeExp = paramScopeExp.nextDecl()) {
      push(paramScopeExp);
    }
  }
  
  public void removeSubsumed(Declaration paramDeclaration)
  {
    int i = hashToIndex(hash(paramDeclaration.getSymbol()));
    Object localObject2 = null;
    Object localObject1 = ((HashNode[])this.table)[i];
    if (localObject1 != null)
    {
      HashNode localHashNode = ((HashNode)localObject1).next;
      Declaration localDeclaration = (Declaration)((HashNode)localObject1).getValue();
      if ((localDeclaration != paramDeclaration) && (subsumedBy(paramDeclaration, localDeclaration))) {
        if (localObject2 == null) {
          ((HashNode[])this.table)[i] = localHashNode;
        }
      }
      for (;;)
      {
        localObject1 = localHashNode;
        break;
        ((HashNode)localObject2).next = localHashNode;
        continue;
        localObject2 = localObject1;
      }
    }
  }
  
  public void setLanguage(Language paramLanguage)
  {
    this.language = paramLanguage;
  }
  
  protected boolean subsumedBy(Declaration paramDeclaration1, Declaration paramDeclaration2)
  {
    return (paramDeclaration1.getSymbol() == paramDeclaration2.getSymbol()) && ((this.language.getNamespaceOf(paramDeclaration1) & this.language.getNamespaceOf(paramDeclaration2)) != 0);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\NameLookup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */