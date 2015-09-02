package gnu.mapping;

public class InheritingEnvironment
  extends SimpleEnvironment
{
  int baseTimestamp;
  Environment[] inherited;
  Namespace[] namespaceMap;
  int numInherited;
  Object[] propertyMap;
  
  public InheritingEnvironment(String paramString, Environment paramEnvironment)
  {
    super(paramString);
    addParent(paramEnvironment);
    if ((paramEnvironment instanceof SimpleEnvironment))
    {
      paramString = (SimpleEnvironment)paramEnvironment;
      int i = paramString.currentTimestamp + 1;
      paramString.currentTimestamp = i;
      this.baseTimestamp = i;
      this.currentTimestamp = i;
    }
  }
  
  public void addParent(Environment paramEnvironment)
  {
    if (this.numInherited == 0) {
      this.inherited = new Environment[4];
    }
    for (;;)
    {
      this.inherited[this.numInherited] = paramEnvironment;
      this.numInherited += 1;
      return;
      if (this.numInherited <= this.inherited.length)
      {
        Environment[] arrayOfEnvironment = new Environment[this.numInherited * 2];
        System.arraycopy(this.inherited, 0, arrayOfEnvironment, 0, this.numInherited);
        this.inherited = arrayOfEnvironment;
      }
    }
  }
  
  public LocationEnumeration enumerateAllLocations()
  {
    LocationEnumeration localLocationEnumeration = new LocationEnumeration(this.table, 1 << this.log2Size);
    localLocationEnumeration.env = this;
    if ((this.inherited != null) && (this.inherited.length > 0))
    {
      localLocationEnumeration.inherited = this.inherited[0].enumerateAllLocations();
      localLocationEnumeration.index = 0;
    }
    return localLocationEnumeration;
  }
  
  public NamedLocation getLocation(Symbol paramSymbol, Object paramObject, int paramInt, boolean paramBoolean)
  {
    NamedLocation localNamedLocation2 = null;
    for (;;)
    {
      NamedLocation localNamedLocation1;
      try
      {
        localNamedLocation1 = lookupDirect(paramSymbol, paramObject, paramInt);
        if (localNamedLocation1 != null) {
          if (!paramBoolean)
          {
            boolean bool = localNamedLocation1.isBound();
            if (!bool) {}
          }
          else
          {
            paramSymbol = localNamedLocation1;
            return paramSymbol;
          }
        }
        if (((this.flags & 0x20) != 0) && (paramBoolean))
        {
          localNamedLocation1 = this.inherited[0].getLocation(paramSymbol, paramObject, paramInt, true);
          if (localNamedLocation1 == null) {
            break label221;
          }
          if (!paramBoolean) {
            break label245;
          }
          localNamedLocation2 = addUnboundLocation(paramSymbol, paramObject, paramInt);
          if (((this.flags & 0x1) == 0) && (localNamedLocation1.isBound())) {
            redefineError(paramSymbol, paramObject, localNamedLocation2);
          }
          localNamedLocation2.base = localNamedLocation1;
          if (localNamedLocation1.value != IndirectableLocation.INDIRECT_FLUIDS) {
            break label191;
          }
          localNamedLocation2.value = localNamedLocation1.value;
          paramSymbol = localNamedLocation2;
          if (!(localNamedLocation2 instanceof SharedLocation)) {
            continue;
          }
          ((SharedLocation)localNamedLocation2).timestamp = this.baseTimestamp;
          paramSymbol = localNamedLocation2;
          continue;
        }
        localNamedLocation1 = lookupInherited(paramSymbol, paramObject, paramInt);
      }
      finally {}
      continue;
      label191:
      if ((this.flags & 0x10) != 0)
      {
        localNamedLocation2.value = IndirectableLocation.DIRECT_ON_SET;
      }
      else
      {
        localNamedLocation2.value = null;
        continue;
        label221:
        localNamedLocation1 = localNamedLocation2;
        if (paramBoolean) {
          localNamedLocation1 = addUnboundLocation(paramSymbol, paramObject, paramInt);
        }
        paramSymbol = localNamedLocation1;
        continue;
        label245:
        paramSymbol = localNamedLocation1;
      }
    }
  }
  
  public final int getNumParents()
  {
    return this.numInherited;
  }
  
  public final Environment getParent(int paramInt)
  {
    return this.inherited[paramInt];
  }
  
  protected boolean hasMoreElements(LocationEnumeration paramLocationEnumeration)
  {
    if (paramLocationEnumeration.inherited != null) {}
    for (;;)
    {
      for (NamedLocation localNamedLocation = paramLocationEnumeration.nextLoc;; localNamedLocation = localNamedLocation.next)
      {
        paramLocationEnumeration.inherited.nextLoc = localNamedLocation;
        if (!paramLocationEnumeration.inherited.hasMoreElements())
        {
          paramLocationEnumeration.prevLoc = null;
          paramLocationEnumeration.nextLoc = paramLocationEnumeration.inherited.nextLoc;
          int i = paramLocationEnumeration.index + 1;
          paramLocationEnumeration.index = i;
          if (i != this.numInherited) {
            break;
          }
          paramLocationEnumeration.inherited = null;
          paramLocationEnumeration.bindings = this.table;
          paramLocationEnumeration.index = (1 << this.log2Size);
          return super.hasMoreElements(paramLocationEnumeration);
        }
        localNamedLocation = paramLocationEnumeration.inherited.nextLoc;
        if (lookup(localNamedLocation.name, localNamedLocation.property) == localNamedLocation)
        {
          paramLocationEnumeration.nextLoc = localNamedLocation;
          return true;
        }
      }
      paramLocationEnumeration.inherited = this.inherited[paramLocationEnumeration.index].enumerateAllLocations();
    }
  }
  
  public NamedLocation lookup(Symbol paramSymbol, Object paramObject, int paramInt)
  {
    NamedLocation localNamedLocation = super.lookup(paramSymbol, paramObject, paramInt);
    if ((localNamedLocation != null) && (localNamedLocation.isBound())) {
      return localNamedLocation;
    }
    return lookupInherited(paramSymbol, paramObject, paramInt);
  }
  
  public NamedLocation lookupInherited(Symbol paramSymbol, Object paramObject, int paramInt)
  {
    int i = 0;
    if (i < this.numInherited)
    {
      Object localObject3 = paramSymbol;
      Object localObject1 = paramObject;
      Object localObject2 = localObject3;
      Object localObject4;
      Object localObject5;
      if (this.namespaceMap != null)
      {
        localObject2 = localObject3;
        if (this.namespaceMap.length > i * 2)
        {
          localObject4 = this.namespaceMap[(i * 2)];
          localObject5 = this.namespaceMap[(i * 2 + 1)];
          if (localObject4 == null)
          {
            localObject2 = localObject3;
            if (localObject5 == null) {}
          }
          else if (paramSymbol.getNamespace() == localObject5) {}
        }
      }
      label187:
      do
      {
        do
        {
          i += 1;
          break;
          localObject2 = Symbol.make(localObject4, paramSymbol.getName());
          localObject3 = localObject1;
          if (this.propertyMap == null) {
            break label187;
          }
          localObject3 = localObject1;
          if (this.propertyMap.length <= i * 2) {
            break label187;
          }
          localObject4 = this.propertyMap[(i * 2)];
          localObject5 = this.propertyMap[(i * 2 + 1)];
          if (localObject4 == null)
          {
            localObject3 = localObject1;
            if (localObject5 == null) {
              break label187;
            }
          }
        } while (paramObject != localObject5);
        localObject3 = localObject4;
        localObject1 = this.inherited[i].lookup((Symbol)localObject2, localObject3, paramInt);
      } while ((localObject1 == null) || (!((NamedLocation)localObject1).isBound()) || (((localObject1 instanceof SharedLocation)) && (((SharedLocation)localObject1).timestamp >= this.baseTimestamp)));
      return (NamedLocation)localObject1;
    }
    return null;
  }
  
  protected void toStringBase(StringBuffer paramStringBuffer)
  {
    paramStringBuffer.append(" baseTs:");
    paramStringBuffer.append(this.baseTimestamp);
    int i = 0;
    while (i < this.numInherited)
    {
      paramStringBuffer.append(" base:");
      paramStringBuffer.append(this.inherited[i].toStringVerbose());
      i += 1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\InheritingEnvironment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */