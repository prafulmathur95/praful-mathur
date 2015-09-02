package gnu.mapping;

import java.util.Hashtable;

public abstract class Environment
  extends PropertySet
{
  static final int CAN_DEFINE = 1;
  static final int CAN_IMPLICITLY_DEFINE = 4;
  static final int CAN_REDEFINE = 2;
  static final int DIRECT_INHERITED_ON_SET = 16;
  public static final int INDIRECT_DEFINES = 32;
  static final int THREAD_SAFE = 8;
  protected static final InheritedLocal curEnvironment = new InheritedLocal();
  static final Hashtable envTable = new Hashtable(50);
  static Environment global;
  int flags = 23;
  
  public static Environment current()
  {
    return getCurrent();
  }
  
  public static Environment getCurrent()
  {
    Environment localEnvironment = (Environment)curEnvironment.get();
    Object localObject = localEnvironment;
    if (localEnvironment == null)
    {
      localObject = make(Thread.currentThread().getName(), global);
      ((Environment)localObject).flags |= 0x8;
      curEnvironment.set(localObject);
    }
    return (Environment)localObject;
  }
  
  public static Environment getGlobal()
  {
    return global;
  }
  
  public static Environment getInstance(String arg0)
  {
    String str = ???;
    if (??? == null) {
      str = "";
    }
    synchronized (envTable)
    {
      Object localObject2 = (Environment)envTable.get(str);
      if (localObject2 != null) {
        return (Environment)localObject2;
      }
      localObject2 = new SimpleEnvironment();
      ((Environment)localObject2).setName(str);
      envTable.put(str, localObject2);
      return (Environment)localObject2;
    }
  }
  
  public static InheritingEnvironment make(String paramString, Environment paramEnvironment)
  {
    return new InheritingEnvironment(paramString, paramEnvironment);
  }
  
  public static SimpleEnvironment make()
  {
    return new SimpleEnvironment();
  }
  
  public static SimpleEnvironment make(String paramString)
  {
    return new SimpleEnvironment(paramString);
  }
  
  public static void restoreCurrent(Environment paramEnvironment)
  {
    curEnvironment.set(paramEnvironment);
  }
  
  public static void setCurrent(Environment paramEnvironment)
  {
    curEnvironment.set(paramEnvironment);
  }
  
  public static void setGlobal(Environment paramEnvironment)
  {
    global = paramEnvironment;
  }
  
  public static Environment setSaveCurrent(Environment paramEnvironment)
  {
    Environment localEnvironment = (Environment)curEnvironment.get();
    curEnvironment.set(paramEnvironment);
    return localEnvironment;
  }
  
  public static Environment user()
  {
    return getCurrent();
  }
  
  public abstract NamedLocation addLocation(Symbol paramSymbol, Object paramObject, Location paramLocation);
  
  public final void addLocation(EnvironmentKey paramEnvironmentKey, Location paramLocation)
  {
    addLocation(paramEnvironmentKey.getKeySymbol(), paramEnvironmentKey.getKeyProperty(), paramLocation);
  }
  
  public final void addLocation(NamedLocation paramNamedLocation)
  {
    addLocation(paramNamedLocation.getKeySymbol(), paramNamedLocation.getKeyProperty(), paramNamedLocation);
  }
  
  SimpleEnvironment cloneForThread()
  {
    InheritingEnvironment localInheritingEnvironment = new InheritingEnvironment(null, this);
    Object localObject1;
    if ((this instanceof InheritingEnvironment))
    {
      localObject1 = (InheritingEnvironment)this;
      int j = ((InheritingEnvironment)localObject1).numInherited;
      localInheritingEnvironment.numInherited = j;
      localInheritingEnvironment.inherited = new Environment[j];
      int i = 0;
      while (i < j)
      {
        localInheritingEnvironment.inherited[i] = localObject1.inherited[i];
        i += 1;
      }
    }
    LocationEnumeration localLocationEnumeration = enumerateLocations();
    while (localLocationEnumeration.hasMoreElements())
    {
      localObject1 = localLocationEnumeration.nextLocation();
      Symbol localSymbol = ((Location)localObject1).getKeySymbol();
      Object localObject2 = ((Location)localObject1).getKeyProperty();
      if ((localSymbol != null) && ((localObject1 instanceof NamedLocation)))
      {
        NamedLocation localNamedLocation = (NamedLocation)localObject1;
        localObject1 = localNamedLocation;
        if (localNamedLocation.base == null)
        {
          localObject1 = new SharedLocation(localSymbol, localObject2, 0);
          ((SharedLocation)localObject1).value = localNamedLocation.value;
          localNamedLocation.base = ((Location)localObject1);
          localNamedLocation.value = null;
        }
        localInheritingEnvironment.addUnboundLocation(localSymbol, localObject2, localSymbol.hashCode() ^ System.identityHashCode(localObject2)).base = ((Location)localObject1);
      }
    }
    return localInheritingEnvironment;
  }
  
  public final boolean containsKey(Object paramObject)
  {
    Object localObject1 = null;
    Object localObject2 = paramObject;
    if ((paramObject instanceof EnvironmentKey))
    {
      paramObject = (EnvironmentKey)paramObject;
      localObject2 = ((EnvironmentKey)paramObject).getKeySymbol();
      localObject1 = ((EnvironmentKey)paramObject).getKeyProperty();
    }
    if ((localObject2 instanceof Symbol)) {}
    for (paramObject = (Symbol)localObject2;; paramObject = getSymbol((String)localObject2)) {
      return isBound((Symbol)paramObject, localObject1);
    }
  }
  
  public Namespace defaultNamespace()
  {
    return Namespace.getDefault();
  }
  
  public abstract void define(Symbol paramSymbol, Object paramObject1, Object paramObject2);
  
  public abstract LocationEnumeration enumerateAllLocations();
  
  public abstract LocationEnumeration enumerateLocations();
  
  public final Object get(EnvironmentKey paramEnvironmentKey, Object paramObject)
  {
    return get(paramEnvironmentKey.getKeySymbol(), paramEnvironmentKey.getKeyProperty(), paramObject);
  }
  
  public Object get(Symbol paramSymbol)
  {
    String str = Location.UNBOUND;
    Object localObject = get(paramSymbol, null, str);
    if (localObject == str) {
      throw new UnboundLocationException(paramSymbol);
    }
    return localObject;
  }
  
  public Object get(Symbol paramSymbol, Object paramObject1, Object paramObject2)
  {
    paramSymbol = lookup(paramSymbol, paramObject1);
    if (paramSymbol == null) {
      return paramObject2;
    }
    return paramSymbol.get(paramObject2);
  }
  
  public final Object get(Object paramObject)
  {
    Object localObject1 = null;
    Object localObject2 = paramObject;
    if ((paramObject instanceof EnvironmentKey))
    {
      paramObject = (EnvironmentKey)paramObject;
      localObject2 = ((EnvironmentKey)paramObject).getKeySymbol();
      localObject1 = ((EnvironmentKey)paramObject).getKeyProperty();
    }
    if ((localObject2 instanceof Symbol)) {}
    for (paramObject = (Symbol)localObject2;; paramObject = getSymbol((String)localObject2)) {
      return get((Symbol)paramObject, localObject1, null);
    }
  }
  
  public final Object get(String paramString, Object paramObject)
  {
    return get(getSymbol(paramString), null, paramObject);
  }
  
  public boolean getCanDefine()
  {
    return (this.flags & 0x1) != 0;
  }
  
  public boolean getCanRedefine()
  {
    return (this.flags & 0x2) != 0;
  }
  
  public final Object getChecked(String paramString)
  {
    Object localObject = get(paramString, Location.UNBOUND);
    if (localObject == Location.UNBOUND) {
      throw new UnboundLocationException(paramString + " in " + this);
    }
    return localObject;
  }
  
  public int getFlags()
  {
    return this.flags;
  }
  
  public final Object getFunction(Symbol paramSymbol)
  {
    String str = Location.UNBOUND;
    Object localObject = get(paramSymbol, EnvironmentKey.FUNCTION, str);
    if (localObject == str) {
      throw new UnboundLocationException(paramSymbol);
    }
    return localObject;
  }
  
  public final Object getFunction(Symbol paramSymbol, Object paramObject)
  {
    return get(paramSymbol, EnvironmentKey.FUNCTION, paramObject);
  }
  
  public final Location getLocation(Symbol paramSymbol)
  {
    return getLocation(paramSymbol, null, true);
  }
  
  public final Location getLocation(Symbol paramSymbol, Object paramObject)
  {
    return getLocation(paramSymbol, paramObject, true);
  }
  
  public final Location getLocation(Object paramObject, boolean paramBoolean)
  {
    Object localObject1 = null;
    Object localObject2 = paramObject;
    if ((paramObject instanceof EnvironmentKey))
    {
      paramObject = (EnvironmentKey)paramObject;
      localObject2 = ((EnvironmentKey)paramObject).getKeySymbol();
      localObject1 = ((EnvironmentKey)paramObject).getKeyProperty();
    }
    if ((localObject2 instanceof Symbol)) {}
    for (paramObject = (Symbol)localObject2;; paramObject = getSymbol((String)localObject2)) {
      return getLocation((Symbol)paramObject, localObject1, paramBoolean);
    }
  }
  
  public abstract NamedLocation getLocation(Symbol paramSymbol, Object paramObject, int paramInt, boolean paramBoolean);
  
  public final NamedLocation getLocation(Symbol paramSymbol, Object paramObject, boolean paramBoolean)
  {
    return getLocation(paramSymbol, paramObject, paramSymbol.hashCode() ^ System.identityHashCode(paramObject), paramBoolean);
  }
  
  public Symbol getSymbol(String paramString)
  {
    return defaultNamespace().getSymbol(paramString);
  }
  
  protected abstract boolean hasMoreElements(LocationEnumeration paramLocationEnumeration);
  
  public final boolean isBound(Symbol paramSymbol)
  {
    return isBound(paramSymbol, null);
  }
  
  public boolean isBound(Symbol paramSymbol, Object paramObject)
  {
    paramSymbol = lookup(paramSymbol, paramObject);
    if (paramSymbol == null) {
      return false;
    }
    return paramSymbol.isBound();
  }
  
  public final boolean isLocked()
  {
    return (this.flags & 0x3) == 0;
  }
  
  public final Location lookup(Symbol paramSymbol)
  {
    return getLocation(paramSymbol, null, false);
  }
  
  public final Location lookup(Symbol paramSymbol, Object paramObject)
  {
    return getLocation(paramSymbol, paramObject, false);
  }
  
  public abstract NamedLocation lookup(Symbol paramSymbol, Object paramObject, int paramInt);
  
  public final Object put(Object paramObject1, Object paramObject2)
  {
    paramObject1 = getLocation(paramObject1, true);
    Object localObject = ((Location)paramObject1).get(null);
    ((Location)paramObject1).set(paramObject2);
    return localObject;
  }
  
  public final Object put(String paramString, Object paramObject)
  {
    return put(paramString, paramObject);
  }
  
  public final void put(Symbol paramSymbol, Object paramObject)
  {
    put(paramSymbol, null, paramObject);
  }
  
  public void put(Symbol paramSymbol, Object paramObject1, Object paramObject2)
  {
    Location localLocation = getLocation(paramSymbol, paramObject1);
    if (localLocation.isConstant())
    {
      define(paramSymbol, paramObject1, paramObject2);
      return;
    }
    localLocation.set(paramObject2);
  }
  
  public final void putFunction(Symbol paramSymbol, Object paramObject)
  {
    put(paramSymbol, EnvironmentKey.FUNCTION, paramObject);
  }
  
  public final Object remove(EnvironmentKey paramEnvironmentKey)
  {
    Symbol localSymbol = paramEnvironmentKey.getKeySymbol();
    paramEnvironmentKey = paramEnvironmentKey.getKeyProperty();
    return remove(localSymbol, paramEnvironmentKey, localSymbol.hashCode() ^ System.identityHashCode(paramEnvironmentKey));
  }
  
  public final Object remove(Symbol paramSymbol, Object paramObject)
  {
    return remove(paramSymbol, paramObject, paramSymbol.hashCode() ^ System.identityHashCode(paramObject));
  }
  
  public Object remove(Symbol paramSymbol, Object paramObject, int paramInt)
  {
    paramSymbol = unlink(paramSymbol, paramObject, paramInt);
    if (paramSymbol == null) {
      return null;
    }
    paramObject = paramSymbol.get(null);
    paramSymbol.undefine();
    return paramObject;
  }
  
  public final Object remove(Object paramObject)
  {
    if ((paramObject instanceof EnvironmentKey))
    {
      paramObject = (EnvironmentKey)paramObject;
      return remove(((EnvironmentKey)paramObject).getKeySymbol(), ((EnvironmentKey)paramObject).getKeyProperty());
    }
    if ((paramObject instanceof Symbol)) {}
    for (paramObject = (Symbol)paramObject;; paramObject = getSymbol((String)paramObject)) {
      return remove((Symbol)paramObject, null, ((Symbol)paramObject).hashCode() ^ System.identityHashCode(null));
    }
  }
  
  public final void remove(Symbol paramSymbol)
  {
    remove(paramSymbol, null, paramSymbol.hashCode());
  }
  
  public final void removeFunction(Symbol paramSymbol)
  {
    remove(paramSymbol, EnvironmentKey.FUNCTION);
  }
  
  public void setCanDefine(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x1;
      return;
    }
    this.flags &= 0xFFFFFFFE;
  }
  
  public void setCanRedefine(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x2;
      return;
    }
    this.flags &= 0xFFFFFFFD;
  }
  
  public void setFlag(boolean paramBoolean, int paramInt)
  {
    if (paramBoolean)
    {
      this.flags |= paramInt;
      return;
    }
    this.flags &= (paramInt ^ 0xFFFFFFFF);
  }
  
  public final void setIndirectDefines()
  {
    this.flags |= 0x20;
    ((InheritingEnvironment)this).baseTimestamp = Integer.MAX_VALUE;
  }
  
  public void setLocked()
  {
    this.flags &= 0xFFFFFFF8;
  }
  
  public String toString()
  {
    return "#<environment " + getName() + '>';
  }
  
  public String toStringVerbose()
  {
    return toString();
  }
  
  public Location unlink(Symbol paramSymbol, Object paramObject, int paramInt)
  {
    throw new RuntimeException("unsupported operation: unlink (aka undefine)");
  }
  
  static class InheritedLocal
    extends InheritableThreadLocal<Environment>
  {
    protected Environment childValue(Environment paramEnvironment)
    {
      Environment localEnvironment = paramEnvironment;
      if (paramEnvironment == null) {
        localEnvironment = Environment.getCurrent();
      }
      paramEnvironment = localEnvironment.cloneForThread();
      paramEnvironment.flags |= 0x8;
      paramEnvironment.flags &= 0xFFFFFFEF;
      return paramEnvironment;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\Environment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */