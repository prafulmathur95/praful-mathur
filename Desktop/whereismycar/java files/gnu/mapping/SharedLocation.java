package gnu.mapping;

public class SharedLocation
  extends NamedLocation
{
  int timestamp;
  
  public SharedLocation(Symbol paramSymbol, Object paramObject, int paramInt)
  {
    super(paramSymbol, paramObject);
    this.timestamp = paramInt;
  }
  
  /* Error */
  public final Object get(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 20	gnu/mapping/SharedLocation:base	Lgnu/mapping/Location;
    //   6: ifnull +16 -> 22
    //   9: aload_0
    //   10: getfield 20	gnu/mapping/SharedLocation:base	Lgnu/mapping/Location;
    //   13: aload_1
    //   14: invokevirtual 24	gnu/mapping/Location:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   17: astore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_1
    //   21: areturn
    //   22: aload_0
    //   23: getfield 28	gnu/mapping/SharedLocation:value	Ljava/lang/Object;
    //   26: getstatic 32	gnu/mapping/Location:UNBOUND	Ljava/lang/String;
    //   29: if_acmpeq -11 -> 18
    //   32: aload_0
    //   33: getfield 28	gnu/mapping/SharedLocation:value	Ljava/lang/Object;
    //   36: astore_1
    //   37: goto -19 -> 18
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	this	SharedLocation
    //   0	45	1	paramObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	18	40	finally
    //   22	37	40	finally
  }
  
  /* Error */
  public boolean isBound()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 20	gnu/mapping/SharedLocation:base	Lgnu/mapping/Location;
    //   6: ifnull +15 -> 21
    //   9: aload_0
    //   10: getfield 20	gnu/mapping/SharedLocation:base	Lgnu/mapping/Location;
    //   13: invokevirtual 36	gnu/mapping/Location:isBound	()Z
    //   16: istore_3
    //   17: aload_0
    //   18: monitorexit
    //   19: iload_3
    //   20: ireturn
    //   21: aload_0
    //   22: getfield 28	gnu/mapping/SharedLocation:value	Ljava/lang/Object;
    //   25: astore_1
    //   26: getstatic 32	gnu/mapping/Location:UNBOUND	Ljava/lang/String;
    //   29: astore_2
    //   30: aload_1
    //   31: aload_2
    //   32: if_acmpeq +8 -> 40
    //   35: iconst_1
    //   36: istore_3
    //   37: goto -20 -> 17
    //   40: iconst_0
    //   41: istore_3
    //   42: goto -25 -> 17
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	this	SharedLocation
    //   25	6	1	localObject1	Object
    //   45	4	1	localObject2	Object
    //   29	3	2	str	String
    //   16	26	3	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	17	45	finally
    //   21	30	45	finally
  }
  
  public final void set(Object paramObject)
  {
    for (;;)
    {
      try
      {
        if (this.base == null)
        {
          this.value = paramObject;
          return;
        }
        if (this.value == DIRECT_ON_SET)
        {
          this.base = null;
          this.value = paramObject;
          continue;
        }
        if (!this.base.isConstant()) {
          break label74;
        }
      }
      finally {}
      getEnvironment().put(getKeySymbol(), getKeyProperty(), paramObject);
      continue;
      label74:
      this.base.set(paramObject);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\SharedLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */