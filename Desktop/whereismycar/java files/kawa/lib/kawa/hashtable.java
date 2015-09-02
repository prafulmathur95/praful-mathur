package kawa.lib.kawa;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.SetNamedPart;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.AbstractHashTable;
import gnu.kawa.util.GeneralHashTable;
import gnu.kawa.util.HashNode;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.thisRef;

public class hashtable
  extends ModuleBody
{
  public static final Location $Prvt$do;
  public static final Class $Prvt$hashnode;
  public static final Location $Prvt$let$St;
  public static final hashtable $instance;
  static final SimpleSymbol Lit0;
  static final SimpleSymbol Lit1 = (SimpleSymbol)new SimpleSymbol("hashtable-check-mutable").readResolve();
  public static final Class hashtable;
  public static final ModuleMethod hashtable$Mncheck$Mnmutable;
  
  static
  {
    Lit0 = (SimpleSymbol)new SimpleSymbol("mutable").readResolve();
    $Prvt$hashnode = HashNode.class;
    $instance = new hashtable();
    $Prvt$let$St = StaticFieldLocation.make("kawa.lib.std_syntax", "let$St");
    $Prvt$do = StaticFieldLocation.make("kawa.lib.std_syntax", "do");
    hashtable = HashTable.class;
    hashtable$Mncheck$Mnmutable = new ModuleMethod($instance, 1, Lit1, 4097);
    $instance.run();
  }
  
  public hashtable()
  {
    ModuleInfo.register(this);
  }
  
  public static void hashtableCheckMutable(HashTable paramHashTable)
  {
    if (!paramHashTable.mutable) {
      misc.error$V("cannot modify non-mutable hashtable", new Object[0]);
    }
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    if (paramModuleMethod.selector == 1) {}
    try
    {
      paramModuleMethod = (HashTable)paramObject;
      hashtableCheckMutable(paramModuleMethod);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "hashtable-check-mutable", 1, paramObject);
    }
    return super.apply1(paramModuleMethod, paramObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 1)
    {
      if (!(paramObject instanceof HashTable)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    }
    return super.match1(paramModuleMethod, paramObject, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
  
  public class HashTable
    extends GeneralHashTable
  {
    public Procedure equivalenceFunction;
    public Procedure hashFunction;
    public boolean mutable;
    
    private void $finit$()
    {
      this.mutable = true;
    }
    
    public HashTable(Procedure paramProcedure)
    {
      $finit$();
      this.equivalenceFunction = this$1;
      this.hashFunction = paramProcedure;
    }
    
    public HashTable(Procedure paramProcedure, int paramInt)
    {
      super();
      $finit$();
      this.equivalenceFunction = this$1;
      this.hashFunction = paramProcedure;
    }
    
    public HashTable(boolean paramBoolean)
    {
      $finit$();
      Invoke.invokeSpecial.applyN(new Object[] { hashtable.hashtable, this, this$1.equivalenceFunction.apply0(), this$1.hashFunction.apply0(), Integer.valueOf(this$1.size() + 100) });
      putAll(this$1);
      SetNamedPart localSetNamedPart = SetNamedPart.setNamedPart;
      thisRef localthisRef = thisRef.thisSyntax;
      SimpleSymbol localSimpleSymbol = hashtable.Lit0;
      if (paramBoolean) {}
      for (this$1 = Boolean.TRUE;; this$1 = Boolean.FALSE)
      {
        localSetNamedPart.apply3(localthisRef, localSimpleSymbol, this$1);
        return;
      }
    }
    
    public Object clone()
    {
      return new HashTable(this, true);
    }
    
    public Pair entriesVectorPair()
    {
      FVector localFVector1 = new FVector();
      FVector localFVector2 = new FVector();
      Object localObject = this.table;
      try
      {
        HashNode[] arrayOfHashNode = (HashNode[])localObject;
        int i = arrayOfHashNode.length - 1;
        while (i >= 0)
        {
          for (localObject = arrayOfHashNode[i]; localObject != null; localObject = getEntryNext((HashNode)localObject))
          {
            localFVector1.add(((HashNode)localObject).getKey());
            localFVector2.add(((HashNode)localObject).getValue());
          }
          i -= 1;
        }
        return lists.cons(localFVector1, localFVector2);
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "table", -2, localObject);
      }
    }
    
    public Object fold(Procedure paramProcedure, Object paramObject)
    {
      Object localObject = this.table;
      try
      {
        HashNode[] arrayOfHashNode = (HashNode[])localObject;
        int i = arrayOfHashNode.length - 1;
        while (i >= 0)
        {
          HashNode localHashNode = arrayOfHashNode[i];
          localObject = paramObject;
          for (paramObject = localHashNode; paramObject != null; paramObject = getEntryNext((HashNode)paramObject)) {
            localObject = paramProcedure.apply3(((HashNode)paramObject).getKey(), ((HashNode)paramObject).getValue(), localObject);
          }
          i -= 1;
          paramObject = localObject;
        }
        return paramObject;
      }
      catch (ClassCastException paramProcedure)
      {
        throw new WrongType(paramProcedure, "table", -2, localObject);
      }
    }
    
    public int hash(Object paramObject)
    {
      return ((Number)this.hashFunction.apply1(paramObject)).intValue();
    }
    
    public FVector keysVector()
    {
      FVector localFVector = new FVector();
      Object localObject = this.table;
      try
      {
        HashNode[] arrayOfHashNode = (HashNode[])localObject;
        int i = arrayOfHashNode.length - 1;
        while (i >= 0)
        {
          for (localObject = arrayOfHashNode[i]; localObject != null; localObject = getEntryNext((HashNode)localObject)) {
            localFVector.add(((HashNode)localObject).getKey());
          }
          i -= 1;
        }
        return localClassCastException;
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "table", -2, localObject);
      }
    }
    
    public boolean matches(Object paramObject1, Object paramObject2)
    {
      return this.equivalenceFunction.apply2(paramObject1, paramObject2) != Boolean.FALSE;
    }
    
    public void putAll(HashTable paramHashTable)
    {
      Object localObject = paramHashTable.table;
      try
      {
        HashNode[] arrayOfHashNode = (HashNode[])localObject;
        int i = arrayOfHashNode.length - 1;
        while (i >= 0)
        {
          for (localObject = arrayOfHashNode[i]; localObject != null; localObject = paramHashTable.getEntryNext((HashNode)localObject)) {
            put(((HashNode)localObject).getKey(), ((HashNode)localObject).getValue());
          }
          i -= 1;
        }
        return;
      }
      catch (ClassCastException paramHashTable)
      {
        throw new WrongType(paramHashTable, "table", -2, localObject);
      }
    }
    
    public Object toAlist()
    {
      Object localObject2 = LList.Empty;
      Object localObject1 = this.table;
      try
      {
        HashNode[] arrayOfHashNode = (HashNode[])localObject1;
        int i = arrayOfHashNode.length - 1;
        while (i >= 0)
        {
          for (localObject1 = arrayOfHashNode[i]; localObject1 != null; localObject1 = getEntryNext((HashNode)localObject1)) {
            localObject2 = lists.cons(lists.cons(((HashNode)localObject1).getKey(), ((HashNode)localObject1).getValue()), localObject2);
          }
          i -= 1;
        }
        return localClassCastException;
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "table", -2, localObject1);
      }
    }
    
    public HashNode[] toNodeArray()
    {
      HashNode[] arrayOfHashNode1 = new HashNode[size()];
      int i = 0;
      Object localObject = this.table;
      try
      {
        HashNode[] arrayOfHashNode2 = (HashNode[])localObject;
        int j = arrayOfHashNode2.length - 1;
        while (j >= 0)
        {
          localObject = arrayOfHashNode2[j];
          while (localObject != null)
          {
            arrayOfHashNode1[i] = localObject;
            localObject = getEntryNext((HashNode)localObject);
            i += 1;
          }
          j -= 1;
        }
        return localClassCastException;
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "table", -2, localObject);
      }
    }
    
    public LList toNodeList()
    {
      Object localObject2 = LList.Empty;
      Object localObject1 = this.table;
      try
      {
        HashNode[] arrayOfHashNode = (HashNode[])localObject1;
        int i = arrayOfHashNode.length - 1;
        while (i >= 0)
        {
          for (localObject1 = arrayOfHashNode[i]; localObject1 != null; localObject1 = getEntryNext((HashNode)localObject1)) {
            localObject2 = lists.cons(localObject1, localObject2);
          }
          i -= 1;
        }
        return (LList)localObject2;
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "table", -2, localObject1);
      }
    }
    
    public void walk(Procedure paramProcedure)
    {
      Object localObject = this.table;
      try
      {
        HashNode[] arrayOfHashNode = (HashNode[])localObject;
        int i = arrayOfHashNode.length - 1;
        while (i >= 0)
        {
          for (localObject = arrayOfHashNode[i]; localObject != null; localObject = getEntryNext((HashNode)localObject)) {
            paramProcedure.apply2(((HashNode)localObject).getKey(), ((HashNode)localObject).getValue());
          }
          i -= 1;
        }
        return;
      }
      catch (ClassCastException paramProcedure)
      {
        throw new WrongType(paramProcedure, "table", -2, localObject);
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\kawa\hashtable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */