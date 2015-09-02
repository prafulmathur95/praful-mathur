package gnu.mapping;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Hashtable;
import java.util.Set;

public class SimpleEnvironment
  extends Environment
{
  int currentTimestamp;
  int log2Size;
  private int mask;
  int num_bindings;
  NamedLocation sharedTail;
  NamedLocation[] table;
  
  public SimpleEnvironment()
  {
    this(64);
  }
  
  public SimpleEnvironment(int paramInt)
  {
    for (this.log2Size = 4; paramInt > 1 << this.log2Size; this.log2Size += 1) {}
    paramInt = 1 << this.log2Size;
    this.table = new NamedLocation[paramInt];
    this.mask = (paramInt - 1);
    this.sharedTail = new PlainLocation(null, null, this);
  }
  
  public SimpleEnvironment(String paramString)
  {
    this();
    setName(paramString);
  }
  
  public static Location getCurrentLocation(String paramString)
  {
    return getCurrent().getLocation(paramString, true);
  }
  
  public static Object lookup_global(Symbol paramSymbol)
    throws UnboundLocationException
  {
    Location localLocation = getCurrent().lookup(paramSymbol);
    if (localLocation == null) {
      throw new UnboundLocationException(paramSymbol);
    }
    return localLocation.get();
  }
  
  NamedLocation addLocation(Symbol paramSymbol, Object paramObject, int paramInt, Location paramLocation)
  {
    Object localObject = paramLocation;
    if ((paramLocation instanceof ThreadLocation))
    {
      localObject = paramLocation;
      if (((ThreadLocation)paramLocation).property == paramObject) {
        localObject = ((ThreadLocation)paramLocation).getLocation();
      }
    }
    paramLocation = lookupDirect(paramSymbol, paramObject, paramInt);
    if (localObject == paramLocation) {
      return paramLocation;
    }
    boolean bool1;
    if (paramLocation != null)
    {
      bool1 = true;
      if (!bool1) {
        paramLocation = addUnboundLocation(paramSymbol, paramObject, paramInt);
      }
      if ((this.flags & 0x3) != 3)
      {
        boolean bool2 = bool1;
        if (bool1) {
          bool2 = paramLocation.isBound();
        }
        if (!bool2) {
          break label178;
        }
        if ((this.flags & 0x2) == 0) {
          label119:
          redefineError(paramSymbol, paramObject, paramLocation);
        }
      }
      label127:
      if ((this.flags & 0x20) == 0) {
        break label198;
      }
    }
    label178:
    label198:
    for (paramLocation.base = ((SimpleEnvironment)((InheritingEnvironment)this).getParent(0)).addLocation(paramSymbol, paramObject, paramInt, (Location)localObject);; paramLocation.base = ((Location)localObject))
    {
      paramLocation.value = IndirectableLocation.INDIRECT_FLUIDS;
      return paramLocation;
      bool1 = false;
      break;
      if (((this.flags & 0x1) != 0) || (!((Location)localObject).isBound())) {
        break label127;
      }
      break label119;
    }
  }
  
  public NamedLocation addLocation(Symbol paramSymbol, Object paramObject, Location paramLocation)
  {
    return addLocation(paramSymbol, paramObject, paramSymbol.hashCode() ^ System.identityHashCode(paramObject), paramLocation);
  }
  
  protected NamedLocation addUnboundLocation(Symbol paramSymbol, Object paramObject, int paramInt)
  {
    paramSymbol = newEntry(paramSymbol, paramObject, paramInt & this.mask);
    paramSymbol.base = null;
    paramSymbol.value = Location.UNBOUND;
    return paramSymbol;
  }
  
  public NamedLocation define(Symbol paramSymbol, Object paramObject1, int paramInt, Object paramObject2)
  {
    paramInt &= this.mask;
    for (NamedLocation localNamedLocation = this.table[paramInt];; localNamedLocation = localNamedLocation.next)
    {
      if (localNamedLocation == null)
      {
        paramSymbol = newEntry(paramSymbol, paramObject1, paramInt);
        paramSymbol.set(paramObject2);
        return paramSymbol;
      }
      if (localNamedLocation.matches(paramSymbol, paramObject1))
      {
        if (localNamedLocation.isBound()) {
          if (!getCanDefine()) {
            break label84;
          }
        }
        for (;;)
        {
          localNamedLocation.base = null;
          localNamedLocation.value = paramObject2;
          return localNamedLocation;
          if (!getCanRedefine()) {
            label84:
            redefineError(paramSymbol, paramObject1, localNamedLocation);
          }
        }
      }
    }
  }
  
  public void define(Symbol paramSymbol, Object paramObject1, Object paramObject2)
  {
    define(paramSymbol, paramObject1, paramSymbol.hashCode() ^ System.identityHashCode(paramObject1), paramObject2);
  }
  
  public Set entrySet()
  {
    return new EnvironmentMappings(this);
  }
  
  public LocationEnumeration enumerateAllLocations()
  {
    return enumerateLocations();
  }
  
  public LocationEnumeration enumerateLocations()
  {
    LocationEnumeration localLocationEnumeration = new LocationEnumeration(this.table, 1 << this.log2Size);
    localLocationEnumeration.env = this;
    return localLocationEnumeration;
  }
  
  /* Error */
  public NamedLocation getLocation(Symbol paramSymbol, Object paramObject, int paramInt, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: iload_3
    //   6: invokevirtual 183	gnu/mapping/SimpleEnvironment:lookup	(Lgnu/mapping/Symbol;Ljava/lang/Object;I)Lgnu/mapping/NamedLocation;
    //   9: astore 5
    //   11: aload 5
    //   13: ifnull +10 -> 23
    //   16: aload 5
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: areturn
    //   23: iload 4
    //   25: ifne +8 -> 33
    //   28: aconst_null
    //   29: astore_1
    //   30: goto -11 -> 19
    //   33: aload_0
    //   34: aload_1
    //   35: aload_2
    //   36: iload_3
    //   37: invokevirtual 87	gnu/mapping/SimpleEnvironment:addUnboundLocation	(Lgnu/mapping/Symbol;Ljava/lang/Object;I)Lgnu/mapping/NamedLocation;
    //   40: astore_1
    //   41: goto -22 -> 19
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	49	0	this	SimpleEnvironment
    //   0	49	1	paramSymbol	Symbol
    //   0	49	2	paramObject	Object
    //   0	49	3	paramInt	int
    //   0	49	4	paramBoolean	boolean
    //   9	8	5	localNamedLocation	NamedLocation
    // Exception table:
    //   from	to	target	type
    //   2	11	44	finally
    //   33	41	44	finally
  }
  
  protected boolean hasMoreElements(LocationEnumeration paramLocationEnumeration)
  {
    for (;;)
    {
      if (paramLocationEnumeration.nextLoc == null)
      {
        paramLocationEnumeration.prevLoc = null;
        int i = paramLocationEnumeration.index - 1;
        paramLocationEnumeration.index = i;
        if (i < 0) {
          return false;
        }
        paramLocationEnumeration.nextLoc = paramLocationEnumeration.bindings[paramLocationEnumeration.index];
        if (paramLocationEnumeration.nextLoc == null) {}
      }
      else
      {
        if (paramLocationEnumeration.nextLoc.name != null) {
          break;
        }
        paramLocationEnumeration.nextLoc = paramLocationEnumeration.nextLoc.next;
      }
    }
    return true;
  }
  
  public NamedLocation lookup(Symbol paramSymbol, Object paramObject, int paramInt)
  {
    return lookupDirect(paramSymbol, paramObject, paramInt);
  }
  
  public NamedLocation lookupDirect(Symbol paramSymbol, Object paramObject, int paramInt)
  {
    int i = this.mask;
    for (NamedLocation localNamedLocation = this.table[(paramInt & i)]; localNamedLocation != null; localNamedLocation = localNamedLocation.next) {
      if (localNamedLocation.matches(paramSymbol, paramObject)) {
        return localNamedLocation;
      }
    }
    return null;
  }
  
  NamedLocation newEntry(Symbol paramSymbol, Object paramObject, int paramInt)
  {
    NamedLocation localNamedLocation = newLocation(paramSymbol, paramObject);
    paramObject = this.table[paramInt];
    paramSymbol = (Symbol)paramObject;
    if (paramObject == null) {
      paramSymbol = this.sharedTail;
    }
    localNamedLocation.next = paramSymbol;
    this.table[paramInt] = localNamedLocation;
    this.num_bindings += 1;
    if (this.num_bindings >= this.table.length) {
      rehash();
    }
    return localNamedLocation;
  }
  
  NamedLocation newLocation(Symbol paramSymbol, Object paramObject)
  {
    if ((this.flags & 0x8) != 0) {
      return new SharedLocation(paramSymbol, paramObject, this.currentTimestamp);
    }
    return new PlainLocation(paramSymbol, paramObject);
  }
  
  public void put(Symbol paramSymbol, Object paramObject1, Object paramObject2)
  {
    if ((this.flags & 0x4) != 0) {}
    for (boolean bool = true;; bool = false)
    {
      paramObject1 = getLocation(paramSymbol, paramObject1, bool);
      if (paramObject1 != null) {
        break;
      }
      throw new UnboundLocationException(paramSymbol);
    }
    if (((Location)paramObject1).isConstant()) {
      throw new IllegalStateException("attempt to modify read-only location: " + paramSymbol + " in " + this + " loc:" + paramObject1);
    }
    ((Location)paramObject1).set(paramObject2);
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    setSymbol(paramObjectInput.readObject());
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    String str = getName();
    Environment localEnvironment = (Environment)envTable.get(str);
    if (localEnvironment != null) {
      return localEnvironment;
    }
    envTable.put(str, this);
    return this;
  }
  
  protected void redefineError(Symbol paramSymbol, Object paramObject, Location paramLocation)
  {
    throw new IllegalStateException("prohibited define/redefine of " + paramSymbol + " in " + this);
  }
  
  void rehash()
  {
    NamedLocation[] arrayOfNamedLocation1 = this.table;
    int i = arrayOfNamedLocation1.length;
    int j = i * 2;
    NamedLocation[] arrayOfNamedLocation2 = new NamedLocation[j];
    int k = j - 1;
    j = i - 1;
    if (j >= 0)
    {
      NamedLocation localNamedLocation;
      for (Object localObject1 = arrayOfNamedLocation1[j];; localObject1 = localNamedLocation)
      {
        i = j;
        if (localObject1 == null) {
          break;
        }
        i = j;
        if (localObject1 == this.sharedTail) {
          break;
        }
        localNamedLocation = ((NamedLocation)localObject1).next;
        Object localObject2 = ((NamedLocation)localObject1).name;
        Object localObject3 = ((NamedLocation)localObject1).property;
        i = (((Symbol)localObject2).hashCode() ^ System.identityHashCode(localObject3)) & k;
        localObject3 = arrayOfNamedLocation2[i];
        localObject2 = localObject3;
        if (localObject3 == null) {
          localObject2 = this.sharedTail;
        }
        ((NamedLocation)localObject1).next = ((NamedLocation)localObject2);
        arrayOfNamedLocation2[i] = localObject1;
      }
    }
    this.table = arrayOfNamedLocation2;
    this.log2Size += 1;
    this.mask = k;
  }
  
  public int size()
  {
    return this.num_bindings;
  }
  
  protected void toStringBase(StringBuffer paramStringBuffer) {}
  
  public String toStringVerbose()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    toStringBase(localStringBuffer);
    return "#<environment " + getName() + " num:" + this.num_bindings + " ts:" + this.currentTimestamp + localStringBuffer + '>';
  }
  
  public Location unlink(Symbol paramSymbol, Object paramObject, int paramInt)
  {
    paramInt &= this.mask;
    Object localObject2 = null;
    NamedLocation localNamedLocation;
    for (Object localObject1 = this.table[paramInt]; localObject1 != null; localObject1 = localNamedLocation)
    {
      localNamedLocation = ((NamedLocation)localObject1).next;
      if (((NamedLocation)localObject1).matches(paramSymbol, paramObject))
      {
        if (!getCanRedefine()) {
          redefineError(paramSymbol, paramObject, (Location)localObject1);
        }
        if (localObject2 == null) {
          this.table[paramInt] = localNamedLocation;
        }
        for (;;)
        {
          this.num_bindings -= 1;
          return (Location)localObject1;
          ((NamedLocation)localObject2).next = ((NamedLocation)localObject1);
        }
      }
      localObject2 = localObject1;
    }
    return null;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(getSymbol());
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\SimpleEnvironment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */