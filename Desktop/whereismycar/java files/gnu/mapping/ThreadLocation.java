package gnu.mapping;

public class ThreadLocation
  extends NamedLocation
  implements Named
{
  public static final String ANONYMOUS = new String("(dynamic)");
  static int counter;
  static SimpleEnvironment env;
  SharedLocation global;
  private int hash;
  private ThreadLocal<NamedLocation> thLocal;
  
  public ThreadLocation()
  {
    this("param#" + nextCounter());
  }
  
  private ThreadLocation(Symbol paramSymbol)
  {
    super(paramSymbol, ANONYMOUS);
    this.thLocal = new InheritingLocation();
    if (paramSymbol == null) {}
    for (paramSymbol = null;; paramSymbol = paramSymbol.toString())
    {
      this.global = new SharedLocation(Symbol.makeUninterned(paramSymbol), null, 0);
      return;
    }
  }
  
  public ThreadLocation(Symbol paramSymbol, Object paramObject, SharedLocation paramSharedLocation)
  {
    super(paramSymbol, paramObject);
    this.hash = (paramSymbol.hashCode() ^ System.identityHashCode(paramObject));
    this.global = paramSharedLocation;
  }
  
  public ThreadLocation(String paramString)
  {
    super(Symbol.makeUninterned(paramString), ANONYMOUS);
    this.thLocal = new InheritingLocation();
    this.global = new SharedLocation(this.name, null, 0);
  }
  
  /* Error */
  public static ThreadLocation getInstance(Symbol paramSymbol, Object paramObject)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 99	gnu/mapping/ThreadLocation:env	Lgnu/mapping/SimpleEnvironment;
    //   6: ifnonnull +15 -> 21
    //   9: new 101	gnu/mapping/SimpleEnvironment
    //   12: dup
    //   13: ldc 103
    //   15: invokespecial 104	gnu/mapping/SimpleEnvironment:<init>	(Ljava/lang/String;)V
    //   18: putstatic 99	gnu/mapping/ThreadLocation:env	Lgnu/mapping/SimpleEnvironment;
    //   21: getstatic 99	gnu/mapping/ThreadLocation:env	Lgnu/mapping/SimpleEnvironment;
    //   24: aload_0
    //   25: aload_1
    //   26: invokevirtual 108	gnu/mapping/SimpleEnvironment:getLocation	(Lgnu/mapping/Symbol;Ljava/lang/Object;)Lgnu/mapping/Location;
    //   29: checkcast 110	gnu/mapping/IndirectableLocation
    //   32: astore_2
    //   33: aload_2
    //   34: getfield 114	gnu/mapping/IndirectableLocation:base	Lgnu/mapping/Location;
    //   37: ifnull +16 -> 53
    //   40: aload_2
    //   41: getfield 114	gnu/mapping/IndirectableLocation:base	Lgnu/mapping/Location;
    //   44: checkcast 2	gnu/mapping/ThreadLocation
    //   47: astore_0
    //   48: ldc 2
    //   50: monitorexit
    //   51: aload_0
    //   52: areturn
    //   53: new 2	gnu/mapping/ThreadLocation
    //   56: dup
    //   57: aload_0
    //   58: aload_1
    //   59: aconst_null
    //   60: invokespecial 116	gnu/mapping/ThreadLocation:<init>	(Lgnu/mapping/Symbol;Ljava/lang/Object;Lgnu/mapping/SharedLocation;)V
    //   63: astore_0
    //   64: aload_2
    //   65: aload_0
    //   66: putfield 114	gnu/mapping/IndirectableLocation:base	Lgnu/mapping/Location;
    //   69: goto -21 -> 48
    //   72: astore_0
    //   73: ldc 2
    //   75: monitorexit
    //   76: aload_0
    //   77: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	paramSymbol	Symbol
    //   0	78	1	paramObject	Object
    //   32	33	2	localIndirectableLocation	IndirectableLocation
    // Exception table:
    //   from	to	target	type
    //   3	21	72	finally
    //   21	48	72	finally
    //   53	69	72	finally
  }
  
  public static ThreadLocation makeAnonymous(Symbol paramSymbol)
  {
    return new ThreadLocation(paramSymbol);
  }
  
  public static ThreadLocation makeAnonymous(String paramString)
  {
    return new ThreadLocation(paramString);
  }
  
  private static int nextCounter()
  {
    try
    {
      int i = counter + 1;
      counter = i;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public Object get(Object paramObject)
  {
    return getLocation().get(paramObject);
  }
  
  public NamedLocation getLocation()
  {
    if (this.property != ANONYMOUS) {
      localObject = Environment.getCurrent().getLocation(this.name, this.property, this.hash, true);
    }
    NamedLocation localNamedLocation;
    do
    {
      return (NamedLocation)localObject;
      localNamedLocation = (NamedLocation)this.thLocal.get();
      localObject = localNamedLocation;
    } while (localNamedLocation != null);
    Object localObject = new SharedLocation(this.name, this.property, 0);
    if (this.global != null) {
      ((NamedLocation)localObject).setBase(this.global);
    }
    this.thLocal.set(localObject);
    return (NamedLocation)localObject;
  }
  
  public String getName()
  {
    if (this.name == null) {
      return null;
    }
    return this.name.toString();
  }
  
  public Object getSymbol()
  {
    if ((this.name != null) && (this.property == ANONYMOUS) && (this.global.getKeySymbol() == this.name)) {
      return this.name.toString();
    }
    return this.name;
  }
  
  public void set(Object paramObject)
  {
    getLocation().set(paramObject);
  }
  
  public void setGlobal(Object paramObject)
  {
    try
    {
      if (this.global == null) {
        this.global = new SharedLocation(this.name, null, 0);
      }
      this.global.set(paramObject);
      return;
    }
    finally {}
  }
  
  public void setName(String paramString)
  {
    throw new RuntimeException("setName not allowed");
  }
  
  public void setRestore(Object paramObject)
  {
    getLocation().setRestore(paramObject);
  }
  
  public Object setWithSave(Object paramObject)
  {
    return getLocation().setWithSave(paramObject);
  }
  
  public class InheritingLocation
    extends InheritableThreadLocal<NamedLocation>
  {
    public InheritingLocation() {}
    
    protected SharedLocation childValue(NamedLocation paramNamedLocation)
    {
      if (ThreadLocation.this.property != ThreadLocation.ANONYMOUS) {
        throw new Error();
      }
      Object localObject = paramNamedLocation;
      if (paramNamedLocation == null) {
        localObject = (SharedLocation)ThreadLocation.this.getLocation();
      }
      paramNamedLocation = (NamedLocation)localObject;
      if (((NamedLocation)localObject).base == null)
      {
        paramNamedLocation = new SharedLocation(ThreadLocation.this.name, ThreadLocation.this.property, 0);
        paramNamedLocation.value = ((NamedLocation)localObject).value;
        ((NamedLocation)localObject).base = paramNamedLocation;
        ((NamedLocation)localObject).value = null;
      }
      localObject = new SharedLocation(ThreadLocation.this.name, ThreadLocation.this.property, 0);
      ((SharedLocation)localObject).value = null;
      ((SharedLocation)localObject).base = paramNamedLocation;
      return (SharedLocation)localObject;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\ThreadLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */