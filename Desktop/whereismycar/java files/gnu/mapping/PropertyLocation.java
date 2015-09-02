package gnu.mapping;

import gnu.lists.LList;
import gnu.lists.Pair;

public class PropertyLocation
  extends Location
{
  Pair pair;
  
  public static Object getProperty(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return getProperty(paramObject1, paramObject2, paramObject3, Environment.getCurrent());
  }
  
  public static Object getProperty(Object paramObject1, Object paramObject2, Object paramObject3, Environment paramEnvironment)
  {
    Object localObject = paramObject1;
    if (!(paramObject1 instanceof Symbol))
    {
      if ((paramObject1 instanceof String)) {
        localObject = Namespace.getDefaultSymbol((String)paramObject1);
      }
    }
    else {
      return paramEnvironment.get((Symbol)localObject, paramObject2, paramObject3);
    }
    return plistGet(paramEnvironment.get(Symbol.PLIST, paramObject1, LList.Empty), paramObject2, paramObject3);
  }
  
  public static Object getPropertyList(Object paramObject)
  {
    return Environment.getCurrent().get(Symbol.PLIST, paramObject, LList.Empty);
  }
  
  public static Object getPropertyList(Object paramObject, Environment paramEnvironment)
  {
    return paramEnvironment.get(Symbol.PLIST, paramObject, LList.Empty);
  }
  
  public static Object plistGet(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    do
    {
      localObject = paramObject3;
      if (!(paramObject1 instanceof Pair)) {
        break;
      }
      localObject = (Pair)paramObject1;
    } while (((Pair)localObject).getCar() != paramObject2);
    Object localObject = ((Pair)((Pair)localObject).getCdr()).getCar();
    return localObject;
  }
  
  public static Object plistPut(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Pair localPair;
    for (Object localObject = paramObject1; (localObject instanceof Pair); localObject = localPair.getCdr())
    {
      localObject = (Pair)localObject;
      localPair = (Pair)((Pair)localObject).getCdr();
      if (((Pair)localObject).getCar() == paramObject2)
      {
        localPair.setCar(paramObject3);
        return paramObject1;
      }
    }
    return new Pair(paramObject2, new Pair(paramObject3, paramObject1));
  }
  
  public static Object plistRemove(Object paramObject1, Object paramObject2)
  {
    Object localObject1 = null;
    Object localObject2 = paramObject1;
    for (;;)
    {
      Object localObject3 = paramObject1;
      if ((localObject2 instanceof Pair))
      {
        Pair localPair = (Pair)localObject2;
        localObject3 = (Pair)localPair.getCdr();
        localObject2 = ((Pair)localObject3).getCdr();
        if (localPair.getCar() != paramObject2) {
          break label62;
        }
        if (localObject1 == null) {
          localObject3 = localObject2;
        }
      }
      else
      {
        return localObject3;
      }
      ((Pair)localObject1).setCdr(localObject2);
      return paramObject1;
      label62:
      localObject1 = localObject3;
    }
  }
  
  public static void putProperty(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    putProperty(paramObject1, paramObject2, paramObject3, Environment.getCurrent());
  }
  
  public static void putProperty(Object paramObject1, Object paramObject2, Object paramObject3, Environment paramEnvironment)
  {
    Object localObject = paramObject1;
    if (!(paramObject1 instanceof Symbol))
    {
      if ((paramObject1 instanceof String)) {
        localObject = Namespace.getDefaultSymbol((String)paramObject1);
      }
    }
    else
    {
      paramObject1 = paramEnvironment.lookup((Symbol)localObject, paramObject2);
      if (paramObject1 == null) {
        break label88;
      }
      paramObject1 = ((Location)paramObject1).getBase();
      if (!(paramObject1 instanceof PropertyLocation)) {
        break label88;
      }
      ((PropertyLocation)paramObject1).set(paramObject3);
      return;
    }
    paramObject1 = paramEnvironment.getLocation(Symbol.PLIST, paramObject1);
    ((Location)paramObject1).set(plistPut(((Location)paramObject1).get(LList.Empty), paramObject2, paramObject3));
    return;
    label88:
    paramObject1 = paramEnvironment.getLocation(Symbol.PLIST, localObject);
    paramObject3 = new Pair(paramObject3, ((Location)paramObject1).get(LList.Empty));
    ((Location)paramObject1).set(new Pair(paramObject2, paramObject3));
    paramObject1 = new PropertyLocation();
    ((PropertyLocation)paramObject1).pair = ((Pair)paramObject3);
    paramEnvironment.addLocation((Symbol)localObject, paramObject2, (Location)paramObject1);
  }
  
  public static boolean removeProperty(Object paramObject1, Object paramObject2)
  {
    return removeProperty(paramObject1, paramObject2, Environment.getCurrent());
  }
  
  public static boolean removeProperty(Object paramObject1, Object paramObject2, Environment paramEnvironment)
  {
    Location localLocation = paramEnvironment.lookup(Symbol.PLIST, paramObject1);
    if (localLocation == null) {}
    do
    {
      return false;
      localObject1 = localLocation.get(LList.Empty);
    } while (!(localObject1 instanceof Pair));
    Object localObject1 = (Pair)localObject1;
    Object localObject2 = null;
    label41:
    if (((Pair)localObject1).getCar() == paramObject2)
    {
      localObject1 = ((Pair)((Pair)localObject1).getCdr()).getCdr();
      if (localObject2 != null) {
        break label116;
      }
      localLocation.set(localObject1);
    }
    for (;;)
    {
      if ((paramObject1 instanceof Symbol)) {
        paramEnvironment.remove((Symbol)paramObject1, paramObject2);
      }
      return true;
      Object localObject3 = ((Pair)localObject1).getCdr();
      if (!(localObject3 instanceof Pair)) {
        break;
      }
      localObject2 = localObject1;
      localObject1 = (Pair)localObject3;
      break label41;
      label116:
      ((Pair)localObject2).setCdr(localObject1);
    }
  }
  
  public static void setPropertyList(Object paramObject1, Object paramObject2)
  {
    setPropertyList(paramObject1, paramObject2, Environment.getCurrent());
  }
  
  /* Error */
  public static void setPropertyList(Object paramObject1, Object paramObject2, Environment paramEnvironment)
  {
    // Byte code:
    //   0: aload_2
    //   1: monitorenter
    //   2: aload_2
    //   3: getstatic 40	gnu/mapping/Symbol:PLIST	Lgnu/mapping/Symbol;
    //   6: aload_0
    //   7: invokevirtual 83	gnu/mapping/Environment:lookup	(Lgnu/mapping/Symbol;Ljava/lang/Object;)Lgnu/mapping/Location;
    //   10: astore_3
    //   11: aload_0
    //   12: instanceof 24
    //   15: ifeq +33 -> 48
    //   18: aload_0
    //   19: checkcast 24	gnu/mapping/Symbol
    //   22: astore 4
    //   24: aload_3
    //   25: getstatic 46	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   28: invokevirtual 95	gnu/mapping/Location:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   31: astore_0
    //   32: aload_0
    //   33: instanceof 54
    //   36: ifne +20 -> 56
    //   39: aload_1
    //   40: astore_0
    //   41: aload_0
    //   42: instanceof 54
    //   45: ifne +55 -> 100
    //   48: aload_3
    //   49: aload_1
    //   50: invokevirtual 98	gnu/mapping/Location:set	(Ljava/lang/Object;)V
    //   53: aload_2
    //   54: monitorexit
    //   55: return
    //   56: aload_0
    //   57: checkcast 54	gnu/lists/Pair
    //   60: astore_0
    //   61: aload_0
    //   62: invokevirtual 58	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   65: astore 5
    //   67: aload_1
    //   68: aload 5
    //   70: aconst_null
    //   71: invokestatic 49	gnu/mapping/PropertyLocation:plistGet	(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   74: ifnull +12 -> 86
    //   77: aload_2
    //   78: aload 4
    //   80: aload 5
    //   82: invokevirtual 114	gnu/mapping/Environment:remove	(Lgnu/mapping/Symbol;Ljava/lang/Object;)Ljava/lang/Object;
    //   85: pop
    //   86: aload_0
    //   87: invokevirtual 61	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   90: checkcast 54	gnu/lists/Pair
    //   93: invokevirtual 61	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   96: astore_0
    //   97: goto -65 -> 32
    //   100: aload_0
    //   101: checkcast 54	gnu/lists/Pair
    //   104: astore 5
    //   106: aload 5
    //   108: invokevirtual 58	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   111: astore 6
    //   113: aload_2
    //   114: aload 4
    //   116: aload 6
    //   118: invokevirtual 83	gnu/mapping/Environment:lookup	(Lgnu/mapping/Symbol;Ljava/lang/Object;)Lgnu/mapping/Location;
    //   121: astore_0
    //   122: aload_0
    //   123: ifnull +45 -> 168
    //   126: aload_0
    //   127: invokevirtual 87	gnu/mapping/Location:getBase	()Lgnu/mapping/Location;
    //   130: astore_0
    //   131: aload_0
    //   132: instanceof 2
    //   135: ifeq +33 -> 168
    //   138: aload_0
    //   139: checkcast 2	gnu/mapping/PropertyLocation
    //   142: astore_0
    //   143: aload 5
    //   145: invokevirtual 61	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   148: checkcast 54	gnu/lists/Pair
    //   151: astore 5
    //   153: aload_0
    //   154: aload 5
    //   156: putfield 101	gnu/mapping/PropertyLocation:pair	Lgnu/lists/Pair;
    //   159: aload 5
    //   161: invokevirtual 61	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   164: astore_0
    //   165: goto -124 -> 41
    //   168: new 2	gnu/mapping/PropertyLocation
    //   171: dup
    //   172: invokespecial 99	gnu/mapping/PropertyLocation:<init>	()V
    //   175: astore_0
    //   176: aload_2
    //   177: aload 4
    //   179: aload 6
    //   181: aload_0
    //   182: invokevirtual 105	gnu/mapping/Environment:addLocation	(Lgnu/mapping/Symbol;Ljava/lang/Object;Lgnu/mapping/Location;)Lgnu/mapping/NamedLocation;
    //   185: pop
    //   186: goto -43 -> 143
    //   189: astore_0
    //   190: aload_2
    //   191: monitorexit
    //   192: aload_0
    //   193: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	194	0	paramObject1	Object
    //   0	194	1	paramObject2	Object
    //   0	194	2	paramEnvironment	Environment
    //   10	39	3	localLocation	Location
    //   22	156	4	localSymbol	Symbol
    //   65	95	5	localObject1	Object
    //   111	69	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   2	32	189	finally
    //   32	39	189	finally
    //   41	48	189	finally
    //   48	55	189	finally
    //   56	86	189	finally
    //   86	97	189	finally
    //   100	122	189	finally
    //   126	143	189	finally
    //   143	165	189	finally
    //   168	186	189	finally
    //   190	192	189	finally
  }
  
  public final Object get(Object paramObject)
  {
    return this.pair.getCar();
  }
  
  public boolean isBound()
  {
    return true;
  }
  
  public final void set(Object paramObject)
  {
    this.pair.setCar(paramObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\PropertyLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */