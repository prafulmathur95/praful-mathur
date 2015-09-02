package gnu.expr;

import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.WrongType;

public class GenericProc
  extends MethodProc
{
  int count;
  int maxArgs;
  protected MethodProc[] methods;
  int minArgs;
  
  public GenericProc() {}
  
  public GenericProc(String paramString)
  {
    setName(paramString);
  }
  
  public static GenericProc make(Object[] paramArrayOfObject)
  {
    GenericProc localGenericProc = new GenericProc();
    localGenericProc.setProperties(paramArrayOfObject);
    return localGenericProc;
  }
  
  public static GenericProc makeWithoutSorting(Object... paramVarArgs)
  {
    GenericProc localGenericProc = new GenericProc();
    int j = paramVarArgs.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = paramVarArgs[i];
      if ((localObject instanceof Keyword))
      {
        localObject = (Keyword)localObject;
        i += 1;
        localGenericProc.setProperty((Keyword)localObject, paramVarArgs[i]);
      }
      for (;;)
      {
        i += 1;
        break;
        localGenericProc.addAtEnd((MethodProc)localObject);
      }
    }
    return localGenericProc;
  }
  
  /* Error */
  public void add(MethodProc paramMethodProc)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 40	gnu/expr/GenericProc:count	I
    //   6: istore_3
    //   7: aload_0
    //   8: aload_1
    //   9: invokevirtual 37	gnu/expr/GenericProc:addAtEnd	(Lgnu/mapping/MethodProc;)V
    //   12: iconst_0
    //   13: istore_2
    //   14: iload_2
    //   15: iload_3
    //   16: if_icmpge +42 -> 58
    //   19: aload_1
    //   20: aload_0
    //   21: getfield 42	gnu/expr/GenericProc:methods	[Lgnu/mapping/MethodProc;
    //   24: iload_2
    //   25: aaload
    //   26: invokestatic 46	gnu/mapping/MethodProc:mostSpecific	(Lgnu/mapping/MethodProc;Lgnu/mapping/MethodProc;)Lgnu/mapping/MethodProc;
    //   29: aload_1
    //   30: if_acmpne +31 -> 61
    //   33: aload_0
    //   34: getfield 42	gnu/expr/GenericProc:methods	[Lgnu/mapping/MethodProc;
    //   37: iload_2
    //   38: aload_0
    //   39: getfield 42	gnu/expr/GenericProc:methods	[Lgnu/mapping/MethodProc;
    //   42: iload_2
    //   43: iconst_1
    //   44: iadd
    //   45: iload_3
    //   46: iload_2
    //   47: isub
    //   48: invokestatic 52	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   51: aload_0
    //   52: getfield 42	gnu/expr/GenericProc:methods	[Lgnu/mapping/MethodProc;
    //   55: iload_2
    //   56: aload_1
    //   57: aastore
    //   58: aload_0
    //   59: monitorexit
    //   60: return
    //   61: iload_2
    //   62: iconst_1
    //   63: iadd
    //   64: istore_2
    //   65: goto -51 -> 14
    //   68: astore_1
    //   69: aload_0
    //   70: monitorexit
    //   71: aload_1
    //   72: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	this	GenericProc
    //   0	73	1	paramMethodProc	MethodProc
    //   13	52	2	i	int
    //   6	42	3	j	int
    // Exception table:
    //   from	to	target	type
    //   2	12	68	finally
    //   19	58	68	finally
  }
  
  protected void add(MethodProc[] paramArrayOfMethodProc)
  {
    for (;;)
    {
      int j;
      int i;
      try
      {
        j = paramArrayOfMethodProc.length;
        if (this.methods != null) {
          break label50;
        }
        this.methods = new MethodProc[j];
      }
      finally {}
      if (i < j)
      {
        add(paramArrayOfMethodProc[i]);
        i += 1;
      }
      else
      {
        return;
        label50:
        i = 0;
      }
    }
  }
  
  /* Error */
  public void addAtEnd(MethodProc paramMethodProc)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 40	gnu/expr/GenericProc:count	I
    //   6: istore_3
    //   7: aload_0
    //   8: getfield 42	gnu/expr/GenericProc:methods	[Lgnu/mapping/MethodProc;
    //   11: ifnonnull +84 -> 95
    //   14: aload_0
    //   15: bipush 8
    //   17: anewarray 4	gnu/mapping/MethodProc
    //   20: putfield 42	gnu/expr/GenericProc:methods	[Lgnu/mapping/MethodProc;
    //   23: aload_0
    //   24: getfield 42	gnu/expr/GenericProc:methods	[Lgnu/mapping/MethodProc;
    //   27: iload_3
    //   28: aload_1
    //   29: aastore
    //   30: aload_1
    //   31: invokevirtual 58	gnu/mapping/MethodProc:minArgs	()I
    //   34: istore 4
    //   36: iload 4
    //   38: aload_0
    //   39: getfield 60	gnu/expr/GenericProc:minArgs	I
    //   42: if_icmplt +10 -> 52
    //   45: aload_0
    //   46: getfield 40	gnu/expr/GenericProc:count	I
    //   49: ifne +9 -> 58
    //   52: aload_0
    //   53: iload 4
    //   55: putfield 60	gnu/expr/GenericProc:minArgs	I
    //   58: aload_1
    //   59: invokevirtual 62	gnu/mapping/MethodProc:maxArgs	()I
    //   62: istore 4
    //   64: iload 4
    //   66: iconst_m1
    //   67: if_icmpeq +12 -> 79
    //   70: iload 4
    //   72: aload_0
    //   73: getfield 64	gnu/expr/GenericProc:maxArgs	I
    //   76: if_icmple +9 -> 85
    //   79: aload_0
    //   80: iload 4
    //   82: putfield 64	gnu/expr/GenericProc:maxArgs	I
    //   85: aload_0
    //   86: iload_3
    //   87: iconst_1
    //   88: iadd
    //   89: putfield 40	gnu/expr/GenericProc:count	I
    //   92: aload_0
    //   93: monitorexit
    //   94: return
    //   95: iload_3
    //   96: aload_0
    //   97: getfield 42	gnu/expr/GenericProc:methods	[Lgnu/mapping/MethodProc;
    //   100: arraylength
    //   101: if_icmplt -78 -> 23
    //   104: aload_0
    //   105: getfield 42	gnu/expr/GenericProc:methods	[Lgnu/mapping/MethodProc;
    //   108: arraylength
    //   109: iconst_2
    //   110: imul
    //   111: anewarray 4	gnu/mapping/MethodProc
    //   114: astore_2
    //   115: aload_0
    //   116: getfield 42	gnu/expr/GenericProc:methods	[Lgnu/mapping/MethodProc;
    //   119: iconst_0
    //   120: aload_2
    //   121: iconst_0
    //   122: iload_3
    //   123: invokestatic 52	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   126: aload_0
    //   127: aload_2
    //   128: putfield 42	gnu/expr/GenericProc:methods	[Lgnu/mapping/MethodProc;
    //   131: goto -108 -> 23
    //   134: astore_1
    //   135: aload_0
    //   136: monitorexit
    //   137: aload_1
    //   138: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	GenericProc
    //   0	139	1	paramMethodProc	MethodProc
    //   114	14	2	arrayOfMethodProc	MethodProc[]
    //   6	117	3	i	int
    //   34	47	4	j	int
    // Exception table:
    //   from	to	target	type
    //   2	23	134	finally
    //   23	52	134	finally
    //   52	58	134	finally
    //   58	64	134	finally
    //   70	79	134	finally
    //   79	85	134	finally
    //   85	92	134	finally
    //   95	131	134	finally
  }
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    if (this.count == 1) {
      return this.methods[0].applyN(paramArrayOfObject);
    }
    checkArgCount(this, paramArrayOfObject.length);
    CallContext localCallContext = CallContext.getInstance();
    int i = 0;
    while (i < this.count)
    {
      if (this.methods[i].matchN(paramArrayOfObject, localCallContext) == 0) {
        return localCallContext.runUntilValue();
      }
      i += 1;
    }
    throw new WrongType(this, -1, null);
  }
  
  public MethodProc getMethod(int paramInt)
  {
    if (paramInt >= this.count) {
      return null;
    }
    return this.methods[paramInt];
  }
  
  public int getMethodCount()
  {
    return this.count;
  }
  
  public int isApplicable(Type[] paramArrayOfType)
  {
    int i = -1;
    int j = this.count;
    for (;;)
    {
      int k = j - 1;
      j = i;
      int m;
      if (k >= 0)
      {
        m = this.methods[k].isApplicable(paramArrayOfType);
        if (m == 1) {
          j = 1;
        }
      }
      else
      {
        return j;
      }
      j = k;
      if (m == 0)
      {
        i = 0;
        j = k;
      }
    }
  }
  
  public int match0(CallContext paramCallContext)
  {
    int k = 0;
    int i;
    if (this.count == 1)
    {
      i = this.methods[0].match0(paramCallContext);
      return i;
    }
    int j = 0;
    for (;;)
    {
      if (j >= this.count) {
        break label57;
      }
      i = k;
      if (this.methods[j].match0(paramCallContext) == 0) {
        break;
      }
      j += 1;
    }
    label57:
    paramCallContext.proc = null;
    return -1;
  }
  
  public int match1(Object paramObject, CallContext paramCallContext)
  {
    int k = 0;
    int i;
    if (this.count == 1)
    {
      i = this.methods[0].match1(paramObject, paramCallContext);
      return i;
    }
    int j = 0;
    for (;;)
    {
      if (j >= this.count) {
        break label64;
      }
      i = k;
      if (this.methods[j].match1(paramObject, paramCallContext) == 0) {
        break;
      }
      j += 1;
    }
    label64:
    paramCallContext.proc = null;
    return -1;
  }
  
  public int match2(Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    int k = 0;
    int i;
    if (this.count == 1)
    {
      i = this.methods[0].match2(paramObject1, paramObject2, paramCallContext);
      return i;
    }
    int j = 0;
    for (;;)
    {
      if (j >= this.count) {
        break label69;
      }
      i = k;
      if (this.methods[j].match2(paramObject1, paramObject2, paramCallContext) == 0) {
        break;
      }
      j += 1;
    }
    label69:
    paramCallContext.proc = null;
    return -1;
  }
  
  public int match3(Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    int k = 0;
    int i;
    if (this.count == 1)
    {
      i = this.methods[0].match3(paramObject1, paramObject2, paramObject3, paramCallContext);
      return i;
    }
    int j = 0;
    for (;;)
    {
      if (j >= this.count) {
        break label73;
      }
      i = k;
      if (this.methods[j].match3(paramObject1, paramObject2, paramObject3, paramCallContext) == 0) {
        break;
      }
      j += 1;
    }
    label73:
    paramCallContext.proc = null;
    return -1;
  }
  
  public int match4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
  {
    if (this.count == 1) {
      return this.methods[0].match4(paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
    }
    int i = 0;
    while (i < this.count)
    {
      if (this.methods[i].match4(paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext) == 0) {
        return 0;
      }
      i += 1;
    }
    paramCallContext.proc = null;
    return -1;
  }
  
  public int matchN(Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    if (this.count == 1) {
      return this.methods[0].matchN(paramArrayOfObject, paramCallContext);
    }
    int j = paramArrayOfObject.length;
    Type[] arrayOfType = new Type[j];
    Language localLanguage = Language.getDefaultLanguage();
    int i = 0;
    if (i < j)
    {
      localObject = paramArrayOfObject[i];
      if (localObject == null) {
        localObject = Type.nullType;
      }
      for (;;)
      {
        arrayOfType[i] = localObject;
        i += 1;
        break;
        localObject = localObject.getClass();
        if (localLanguage != null) {
          localObject = localLanguage.getTypeFor((Class)localObject);
        } else {
          localObject = Type.make((Class)localObject);
        }
      }
    }
    Object localObject = new int[this.count];
    j = 0;
    int k = 0;
    int i2 = -1;
    i = 0;
    if (i < this.count)
    {
      int i3 = this.methods[i].isApplicable(arrayOfType);
      int m = i2;
      if (j == 0)
      {
        m = i2;
        if (i3 >= 0) {
          m = i;
        }
      }
      int n;
      int i1;
      if (i3 > 0)
      {
        n = j + 1;
        i1 = k;
      }
      for (;;)
      {
        localObject[i] = i3;
        i += 1;
        i2 = m;
        j = n;
        k = i1;
        break;
        n = j;
        i1 = k;
        if (i3 == 0)
        {
          i1 = k + 1;
          n = j;
        }
      }
    }
    if ((j == 1) || ((j == 0) && (k == 1))) {
      return this.methods[i2].matchN(paramArrayOfObject, paramCallContext);
    }
    i = 0;
    if (i < this.count)
    {
      k = localObject[i];
      if ((k < 0) || ((k == 0) && (j > 0))) {}
      while (this.methods[i].matchN(paramArrayOfObject, paramCallContext) != 0)
      {
        i += 1;
        break;
      }
      return 0;
    }
    paramCallContext.proc = null;
    return -1;
  }
  
  public int numArgs()
  {
    return this.minArgs | this.maxArgs << 12;
  }
  
  public final void setProperties(Object[] paramArrayOfObject)
  {
    int j = paramArrayOfObject.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = paramArrayOfObject[i];
      if ((localObject instanceof Keyword))
      {
        localObject = (Keyword)localObject;
        i += 1;
        setProperty((Keyword)localObject, paramArrayOfObject[i]);
      }
      for (;;)
      {
        i += 1;
        break;
        add((MethodProc)localObject);
      }
    }
  }
  
  public void setProperty(Keyword paramKeyword, Object paramObject)
  {
    String str = paramKeyword.getName();
    if (str == "name")
    {
      setName(paramObject.toString());
      return;
    }
    if (str == "method")
    {
      add((MethodProc)paramObject);
      return;
    }
    super.setProperty(paramKeyword.asSymbol(), paramObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\GenericProc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */