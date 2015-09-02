package gnu.mapping;

import java.util.Map.Entry;

public abstract class NamedLocation
  extends IndirectableLocation
  implements Map.Entry, EnvironmentKey
{
  final Symbol name;
  NamedLocation next;
  final Object property;
  
  public NamedLocation(NamedLocation paramNamedLocation)
  {
    this.name = paramNamedLocation.name;
    this.property = paramNamedLocation.property;
  }
  
  public NamedLocation(Symbol paramSymbol, Object paramObject)
  {
    this.name = paramSymbol;
    this.property = paramObject;
  }
  
  public boolean entered()
  {
    return this.next != null;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof NamedLocation)) {
      break label28;
    }
    Object localObject;
    label28:
    do
    {
      do
      {
        do
        {
          return false;
          localObject = (NamedLocation)paramObject;
          if (this.name != null) {
            break;
          }
        } while (((NamedLocation)localObject).name != null);
      } while (this.property != ((NamedLocation)localObject).property);
      paramObject = getValue();
      localObject = ((NamedLocation)localObject).getValue();
      if (paramObject == localObject)
      {
        return true;
        if (this.name.equals(((NamedLocation)localObject).name)) {
          break;
        }
        return false;
      }
    } while ((paramObject == null) || (localObject == null));
    return paramObject.equals(localObject);
  }
  
  public Environment getEnvironment()
  {
    for (NamedLocation localNamedLocation = this; localNamedLocation != null; localNamedLocation = localNamedLocation.next) {
      if (localNamedLocation.name == null)
      {
        Environment localEnvironment = (Environment)localNamedLocation.value;
        if (localEnvironment != null) {
          return localEnvironment;
        }
      }
    }
    return super.getEnvironment();
  }
  
  public final Object getKey()
  {
    Object localObject = this;
    if (this.property == null) {
      localObject = this.name;
    }
    return localObject;
  }
  
  public final Object getKeyProperty()
  {
    return this.property;
  }
  
  public final Symbol getKeySymbol()
  {
    return this.name;
  }
  
  public int hashCode()
  {
    int j = this.name.hashCode() ^ System.identityHashCode(this.property);
    Object localObject = getValue();
    int i = j;
    if (localObject != null) {
      i = j ^ localObject.hashCode();
    }
    return i;
  }
  
  public final boolean matches(EnvironmentKey paramEnvironmentKey)
  {
    return (Symbol.equals(paramEnvironmentKey.getKeySymbol(), this.name)) && (paramEnvironmentKey.getKeyProperty() == this.property);
  }
  
  public final boolean matches(Symbol paramSymbol, Object paramObject)
  {
    return (Symbol.equals(paramSymbol, this.name)) && (paramObject == this.property);
  }
  
  public void setRestore(Object paramObject)
  {
    for (;;)
    {
      try
      {
        if (this.value == INDIRECT_FLUIDS)
        {
          this.base.setRestore(paramObject);
          return;
        }
        if ((paramObject instanceof Location))
        {
          this.value = null;
          this.base = ((Location)paramObject);
          continue;
        }
        this.value = paramObject;
      }
      finally {}
      this.base = null;
    }
  }
  
  /* Error */
  public Object setWithSave(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 47	gnu/mapping/NamedLocation:value	Ljava/lang/Object;
    //   6: getstatic 81	gnu/mapping/NamedLocation:INDIRECT_FLUIDS	Ljava/lang/Object;
    //   9: if_acmpne +16 -> 25
    //   12: aload_0
    //   13: getfield 85	gnu/mapping/NamedLocation:base	Lgnu/mapping/Location;
    //   16: aload_1
    //   17: invokevirtual 93	gnu/mapping/Location:setWithSave	(Ljava/lang/Object;)Ljava/lang/Object;
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: areturn
    //   25: aload_0
    //   26: getfield 21	gnu/mapping/NamedLocation:name	Lgnu/mapping/Symbol;
    //   29: invokestatic 99	gnu/mapping/ThreadLocation:makeAnonymous	(Lgnu/mapping/Symbol;)Lgnu/mapping/ThreadLocation;
    //   32: astore_2
    //   33: aload_2
    //   34: getfield 103	gnu/mapping/ThreadLocation:global	Lgnu/mapping/SharedLocation;
    //   37: aload_0
    //   38: getfield 85	gnu/mapping/NamedLocation:base	Lgnu/mapping/Location;
    //   41: putfield 106	gnu/mapping/SharedLocation:base	Lgnu/mapping/Location;
    //   44: aload_2
    //   45: getfield 103	gnu/mapping/ThreadLocation:global	Lgnu/mapping/SharedLocation;
    //   48: aload_0
    //   49: getfield 47	gnu/mapping/NamedLocation:value	Ljava/lang/Object;
    //   52: putfield 107	gnu/mapping/SharedLocation:value	Ljava/lang/Object;
    //   55: aload_0
    //   56: aload_2
    //   57: invokevirtual 111	gnu/mapping/NamedLocation:setAlias	(Lgnu/mapping/Location;)V
    //   60: aload_2
    //   61: invokevirtual 115	gnu/mapping/ThreadLocation:getLocation	()Lgnu/mapping/NamedLocation;
    //   64: astore_3
    //   65: aload_3
    //   66: aload_1
    //   67: putfield 47	gnu/mapping/NamedLocation:value	Ljava/lang/Object;
    //   70: aload_3
    //   71: aconst_null
    //   72: putfield 85	gnu/mapping/NamedLocation:base	Lgnu/mapping/Location;
    //   75: aload_2
    //   76: getfield 103	gnu/mapping/ThreadLocation:global	Lgnu/mapping/SharedLocation;
    //   79: astore_1
    //   80: goto -59 -> 21
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	this	NamedLocation
    //   0	88	1	paramObject	Object
    //   32	44	2	localThreadLocation	ThreadLocation
    //   64	7	3	localNamedLocation	NamedLocation
    // Exception table:
    //   from	to	target	type
    //   2	21	83	finally
    //   25	80	83	finally
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\NamedLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */