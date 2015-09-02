package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.kawa.reflect.FieldLocation;
import gnu.kawa.util.AbstractWeakHashTable;
import gnu.mapping.Location;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class ModuleInfo
{
  static ClassToInfoMap mapClassToInfo = new ClassToInfoMap();
  private String className;
  Compilation comp;
  ModuleInfo[] dependencies;
  ModuleExp exp;
  public long lastCheckedTime;
  public long lastModifiedTime;
  Class moduleClass;
  int numDependencies;
  Path sourceAbsPath;
  String sourceAbsPathname;
  public String sourcePath;
  String uri;
  
  public static Path absPath(String paramString)
  {
    return Path.valueOf(paramString).getCanonical();
  }
  
  public static ModuleInfo find(ClassType paramClassType)
  {
    if (paramClassType.isExisting()) {
      try
      {
        ModuleInfo localModuleInfo = ModuleManager.findWithClass(paramClassType.getReflectClass());
        return localModuleInfo;
      }
      catch (Exception localException) {}
    }
    return ModuleManager.getInstance().findWithClassName(paramClassType.getName());
  }
  
  public static ModuleInfo findFromInstance(Object paramObject)
  {
    return ModuleContext.getContext().findFromInstance(paramObject);
  }
  
  static void makeDeclInModule2(ModuleExp paramModuleExp, Declaration paramDeclaration)
  {
    Object localObject = paramDeclaration.getConstantValue();
    if ((localObject instanceof FieldLocation))
    {
      FieldLocation localFieldLocation = (FieldLocation)localObject;
      Declaration localDeclaration = localFieldLocation.getDeclaration();
      localObject = new ReferenceExp(localDeclaration);
      paramDeclaration.setAlias(true);
      ((ReferenceExp)localObject).setDontDereference(true);
      paramDeclaration.setValue((Expression)localObject);
      if (localDeclaration.isProcedureDecl()) {
        paramDeclaration.setProcedureDecl(true);
      }
      if (localDeclaration.getFlag(32768L)) {
        paramDeclaration.setSyntax();
      }
      if (!paramDeclaration.getFlag(2048L)) {
        paramDeclaration = localFieldLocation.getDeclaringClass().getName();
      }
    }
    for (paramModuleExp = paramModuleExp.firstDecl();; paramModuleExp = paramModuleExp.nextDecl()) {
      if (paramModuleExp != null)
      {
        if ((paramDeclaration.equals(paramModuleExp.getType().getName())) && (paramModuleExp.getFlag(1073741824L))) {
          ((ReferenceExp)localObject).setContextDecl(paramModuleExp);
        }
      }
      else {
        return;
      }
    }
  }
  
  public static void register(Object paramObject)
  {
    ModuleContext.getContext().setInstance(paramObject);
  }
  
  /* Error */
  public void addDependency(ModuleInfo paramModuleInfo)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 178	gnu/expr/ModuleInfo:dependencies	[Lgnu/expr/ModuleInfo;
    //   6: ifnonnull +36 -> 42
    //   9: aload_0
    //   10: bipush 8
    //   12: anewarray 2	gnu/expr/ModuleInfo
    //   15: putfield 178	gnu/expr/ModuleInfo:dependencies	[Lgnu/expr/ModuleInfo;
    //   18: aload_0
    //   19: getfield 178	gnu/expr/ModuleInfo:dependencies	[Lgnu/expr/ModuleInfo;
    //   22: astore_2
    //   23: aload_0
    //   24: getfield 180	gnu/expr/ModuleInfo:numDependencies	I
    //   27: istore_3
    //   28: aload_0
    //   29: iload_3
    //   30: iconst_1
    //   31: iadd
    //   32: putfield 180	gnu/expr/ModuleInfo:numDependencies	I
    //   35: aload_2
    //   36: iload_3
    //   37: aload_1
    //   38: aastore
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: aload_0
    //   43: getfield 180	gnu/expr/ModuleInfo:numDependencies	I
    //   46: aload_0
    //   47: getfield 178	gnu/expr/ModuleInfo:dependencies	[Lgnu/expr/ModuleInfo;
    //   50: arraylength
    //   51: if_icmpne -33 -> 18
    //   54: aload_0
    //   55: getfield 180	gnu/expr/ModuleInfo:numDependencies	I
    //   58: iconst_2
    //   59: imul
    //   60: anewarray 2	gnu/expr/ModuleInfo
    //   63: astore_2
    //   64: aload_0
    //   65: getfield 178	gnu/expr/ModuleInfo:dependencies	[Lgnu/expr/ModuleInfo;
    //   68: iconst_0
    //   69: aload_2
    //   70: iconst_0
    //   71: aload_0
    //   72: getfield 180	gnu/expr/ModuleInfo:numDependencies	I
    //   75: invokestatic 186	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   78: aload_0
    //   79: aload_2
    //   80: putfield 178	gnu/expr/ModuleInfo:dependencies	[Lgnu/expr/ModuleInfo;
    //   83: goto -65 -> 18
    //   86: astore_1
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	ModuleInfo
    //   0	91	1	paramModuleInfo	ModuleInfo
    //   22	58	2	arrayOfModuleInfo	ModuleInfo[]
    //   27	10	3	i	int
    // Exception table:
    //   from	to	target	type
    //   2	18	86	finally
    //   18	35	86	finally
    //   42	83	86	finally
  }
  
  public boolean checkCurrent(ModuleManager paramModuleManager, long paramLong)
  {
    if (this.sourceAbsPath == null) {
      return true;
    }
    if (this.lastCheckedTime + paramModuleManager.lastModifiedCacheTime >= paramLong) {
      return this.moduleClass != null;
    }
    long l3 = this.sourceAbsPath.getLastModified();
    long l2 = this.lastModifiedTime;
    this.lastModifiedTime = l3;
    this.lastCheckedTime = paramLong;
    if (this.className == null) {
      return false;
    }
    if (this.moduleClass == null) {}
    long l1;
    try
    {
      this.moduleClass = ClassType.getContextClass(this.className);
      l1 = l2;
      if (l2 != 0L) {
        break label226;
      }
      l1 = l2;
      if (this.moduleClass == null) {
        break label226;
      }
      localObject2 = this.className;
      i = ((String)localObject2).lastIndexOf('.');
      localObject1 = localObject2;
      if (i >= 0) {
        localObject1 = ((String)localObject2).substring(i + 1);
      }
      localObject1 = (String)localObject1 + ".class";
      localObject2 = this.moduleClass.getResource((String)localObject1);
      localObject1 = localObject2;
      l1 = l2;
      if (localObject2 == null) {}
    }
    catch (ClassNotFoundException paramModuleManager)
    {
      Object localObject2;
      Object localObject1;
      label204:
      return false;
    }
    try
    {
      l1 = ((URL)localObject2).openConnection().getLastModified();
      localObject1 = localObject2;
    }
    catch (IOException localIOException)
    {
      localModuleInfo = null;
      l1 = l2;
      break label204;
    }
    if (localObject1 == null) {
      return true;
    }
    ModuleInfo localModuleInfo;
    label226:
    if (l3 > l1)
    {
      this.moduleClass = null;
      return false;
    }
    int i = this.numDependencies;
    do
    {
      int j;
      do
      {
        j = i - 1;
        if (j < 0) {
          break;
        }
        localModuleInfo = this.dependencies[j];
        i = j;
      } while (localModuleInfo.comp != null);
      i = j;
    } while (localModuleInfo.checkCurrent(paramModuleManager, paramLong));
    this.moduleClass = null;
    return false;
    return true;
  }
  
  public void cleanupAfterCompilation()
  {
    if (this.comp != null) {
      this.comp.cleanupAfterCompilation();
    }
  }
  
  public void clearClass()
  {
    this.moduleClass = null;
    this.numDependencies = 0;
    this.dependencies = null;
  }
  
  /* Error */
  public String getClassName()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 209	gnu/expr/ModuleInfo:className	Ljava/lang/String;
    //   6: ifnonnull +21 -> 27
    //   9: aload_0
    //   10: getfield 201	gnu/expr/ModuleInfo:moduleClass	Ljava/lang/Class;
    //   13: ifnull +23 -> 36
    //   16: aload_0
    //   17: aload_0
    //   18: getfield 201	gnu/expr/ModuleInfo:moduleClass	Ljava/lang/Class;
    //   21: invokevirtual 260	java/lang/Class:getName	()Ljava/lang/String;
    //   24: putfield 209	gnu/expr/ModuleInfo:className	Ljava/lang/String;
    //   27: aload_0
    //   28: getfield 209	gnu/expr/ModuleInfo:className	Ljava/lang/String;
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: areturn
    //   36: aload_0
    //   37: getfield 250	gnu/expr/ModuleInfo:comp	Lgnu/expr/Compilation;
    //   40: ifnull -13 -> 27
    //   43: aload_0
    //   44: getfield 250	gnu/expr/ModuleInfo:comp	Lgnu/expr/Compilation;
    //   47: getfield 264	gnu/expr/Compilation:mainClass	Lgnu/bytecode/ClassType;
    //   50: ifnull -23 -> 27
    //   53: aload_0
    //   54: aload_0
    //   55: getfield 250	gnu/expr/ModuleInfo:comp	Lgnu/expr/Compilation;
    //   58: getfield 264	gnu/expr/Compilation:mainClass	Lgnu/bytecode/ClassType;
    //   61: invokevirtual 78	gnu/bytecode/ClassType:getName	()Ljava/lang/String;
    //   64: putfield 209	gnu/expr/ModuleInfo:className	Ljava/lang/String;
    //   67: goto -40 -> 27
    //   70: astore_1
    //   71: aload_0
    //   72: monitorexit
    //   73: aload_1
    //   74: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	this	ModuleInfo
    //   31	4	1	str	String
    //   70	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	27	70	finally
    //   27	32	70	finally
    //   36	67	70	finally
  }
  
  /* Error */
  public ClassType getClassType()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 201	gnu/expr/ModuleInfo:moduleClass	Ljava/lang/Class;
    //   6: ifnull +18 -> 24
    //   9: aload_0
    //   10: getfield 201	gnu/expr/ModuleInfo:moduleClass	Ljava/lang/Class;
    //   13: invokestatic 269	gnu/bytecode/Type:make	(Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   16: checkcast 56	gnu/bytecode/ClassType
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: areturn
    //   24: aload_0
    //   25: getfield 250	gnu/expr/ModuleInfo:comp	Lgnu/expr/Compilation;
    //   28: ifnull +24 -> 52
    //   31: aload_0
    //   32: getfield 250	gnu/expr/ModuleInfo:comp	Lgnu/expr/Compilation;
    //   35: getfield 264	gnu/expr/Compilation:mainClass	Lgnu/bytecode/ClassType;
    //   38: ifnull +14 -> 52
    //   41: aload_0
    //   42: getfield 250	gnu/expr/ModuleInfo:comp	Lgnu/expr/Compilation;
    //   45: getfield 264	gnu/expr/Compilation:mainClass	Lgnu/bytecode/ClassType;
    //   48: astore_1
    //   49: goto -29 -> 20
    //   52: aload_0
    //   53: getfield 209	gnu/expr/ModuleInfo:className	Ljava/lang/String;
    //   56: invokestatic 272	gnu/bytecode/ClassType:make	(Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   59: astore_1
    //   60: goto -40 -> 20
    //   63: astore_1
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	this	ModuleInfo
    //   19	41	1	localClassType	ClassType
    //   63	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	20	63	finally
    //   24	49	63	finally
    //   52	60	63	finally
  }
  
  public Compilation getCompilation()
  {
    return this.comp;
  }
  
  public Object getInstance()
  {
    return ModuleContext.getContext().findInstance(this);
  }
  
  /* Error */
  public Class getModuleClass()
    throws ClassNotFoundException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 201	gnu/expr/ModuleInfo:moduleClass	Ljava/lang/Class;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull +7 -> 15
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: areturn
    //   15: aload_0
    //   16: getfield 209	gnu/expr/ModuleInfo:className	Ljava/lang/String;
    //   19: invokestatic 213	gnu/bytecode/ClassType:getContextClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   22: astore_1
    //   23: aload_0
    //   24: aload_1
    //   25: putfield 201	gnu/expr/ModuleInfo:moduleClass	Ljava/lang/Class;
    //   28: goto -17 -> 11
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	36	0	this	ModuleInfo
    //   6	19	1	localClass	Class
    //   31	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	31	finally
    //   15	28	31	finally
  }
  
  public Class getModuleClassRaw()
  {
    return this.moduleClass;
  }
  
  /* Error */
  public ModuleExp getModuleExp()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 285	gnu/expr/ModuleInfo:exp	Lgnu/expr/ModuleExp;
    //   6: astore_2
    //   7: aload_2
    //   8: astore_1
    //   9: aload_2
    //   10: ifnonnull +73 -> 83
    //   13: aload_0
    //   14: getfield 250	gnu/expr/ModuleInfo:comp	Lgnu/expr/Compilation;
    //   17: ifnull +15 -> 32
    //   20: aload_0
    //   21: getfield 250	gnu/expr/ModuleInfo:comp	Lgnu/expr/Compilation;
    //   24: getfield 288	gnu/expr/Compilation:mainLambda	Lgnu/expr/ModuleExp;
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: areturn
    //   32: aload_0
    //   33: getfield 209	gnu/expr/ModuleInfo:className	Ljava/lang/String;
    //   36: invokestatic 272	gnu/bytecode/ClassType:make	(Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   39: astore_2
    //   40: new 145	gnu/expr/ModuleExp
    //   43: dup
    //   44: invokespecial 289	gnu/expr/ModuleExp:<init>	()V
    //   47: astore_1
    //   48: aload_1
    //   49: aload_2
    //   50: putfield 292	gnu/expr/ModuleExp:type	Lgnu/bytecode/ClassType;
    //   53: aload_1
    //   54: aload_2
    //   55: invokevirtual 78	gnu/bytecode/ClassType:getName	()Ljava/lang/String;
    //   58: invokevirtual 296	gnu/expr/ModuleExp:setName	(Ljava/lang/String;)V
    //   61: aload_1
    //   62: aload_1
    //   63: getfield 299	gnu/expr/ModuleExp:flags	I
    //   66: ldc_w 300
    //   69: ior
    //   70: putfield 299	gnu/expr/ModuleExp:flags	I
    //   73: aload_1
    //   74: aload_0
    //   75: putfield 304	gnu/expr/ModuleExp:info	Lgnu/expr/ModuleInfo;
    //   78: aload_0
    //   79: aload_1
    //   80: putfield 285	gnu/expr/ModuleInfo:exp	Lgnu/expr/ModuleExp;
    //   83: goto -55 -> 28
    //   86: astore_1
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	ModuleInfo
    //   8	72	1	localObject1	Object
    //   86	4	1	localObject2	Object
    //   6	49	2	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	86	finally
    //   13	28	86	finally
    //   32	83	86	finally
  }
  
  public String getNamespaceUri()
  {
    return this.uri;
  }
  
  public Object getRunInstance()
  {
    Object localObject = getInstance();
    if ((localObject instanceof Runnable)) {
      ((Runnable)localObject).run();
    }
    return localObject;
  }
  
  public Path getSourceAbsPath()
  {
    return this.sourceAbsPath;
  }
  
  public String getSourceAbsPathname()
  {
    String str2 = this.sourceAbsPathname;
    String str1 = str2;
    if (str2 == null)
    {
      str1 = str2;
      if (this.sourceAbsPath != null)
      {
        str1 = this.sourceAbsPath.toString();
        this.sourceAbsPathname = str1;
      }
    }
    return str1;
  }
  
  public int getState()
  {
    if (this.comp == null) {
      return 14;
    }
    return this.comp.getState();
  }
  
  public void loadByStages(int paramInt)
  {
    if (getState() + 1 >= paramInt) {}
    int i;
    do
    {
      do
      {
        return;
        loadByStages(paramInt - 2);
        i = getState();
      } while (i >= paramInt);
      this.comp.setState(i + 1);
      int j = this.numDependencies;
      i = 0;
      while (i < j)
      {
        this.dependencies[i].loadByStages(paramInt);
        i += 1;
      }
      i = getState();
    } while (i >= paramInt);
    this.comp.setState(i & 0xFFFFFFFE);
    this.comp.process(paramInt);
  }
  
  public boolean loadEager(int paramInt)
  {
    boolean bool = true;
    if ((this.comp == null) && (this.className != null)) {}
    int j;
    do
    {
      return false;
      j = getState();
      if (j >= paramInt) {
        return true;
      }
    } while ((j & 0x1) != 0);
    this.comp.setState(j + 1);
    int k = this.numDependencies;
    int i = 0;
    for (;;)
    {
      if (i >= k) {
        break label101;
      }
      if (!this.dependencies[i].loadEager(paramInt))
      {
        if (getState() != j + 1) {
          break;
        }
        this.comp.setState(j);
        return false;
      }
      i += 1;
    }
    label101:
    if (getState() == j + 1) {
      this.comp.setState(j);
    }
    this.comp.process(paramInt);
    if (getState() == paramInt) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public void setClassName(String paramString)
  {
    this.className = paramString;
  }
  
  public void setCompilation(Compilation paramCompilation)
  {
    paramCompilation.minfo = this;
    this.comp = paramCompilation;
    paramCompilation = paramCompilation.mainLambda;
    this.exp = paramCompilation;
    if (paramCompilation != null)
    {
      paramCompilation = paramCompilation.getFileName();
      this.sourcePath = paramCompilation;
      this.sourceAbsPath = absPath(paramCompilation);
    }
  }
  
  public void setModuleClass(Class paramClass)
  {
    this.moduleClass = paramClass;
    this.className = paramClass.getName();
    mapClassToInfo.put(paramClass, this);
  }
  
  public void setNamespaceUri(String paramString)
  {
    this.uri = paramString;
  }
  
  public void setSourceAbsPath(Path paramPath)
  {
    this.sourceAbsPath = paramPath;
    this.sourceAbsPathname = null;
  }
  
  public ModuleExp setupModuleExp()
  {
    for (;;)
    {
      ModuleExp localModuleExp;
      try
      {
        localModuleExp = getModuleExp();
        int i = localModuleExp.flags;
        if ((i & 0x80000) == 0) {
          return localModuleExp;
        }
        localModuleExp.setFlag(false, 524288);
        Class localClass;
        Object localObject3;
        Language localLanguage;
        gnu.bytecode.Field localField;
        if (this.moduleClass != null)
        {
          localClass = this.moduleClass;
          localObject1 = (ClassType)Type.make(localClass);
          localObject3 = null;
          localLanguage = Language.getDefaultLanguage();
          localField = ((ClassType)localObject1).getFields();
          if (localField == null) {
            break label229;
          }
          i = localField.getFlags();
          if ((i & 0x1) == 0)
          {
            localObject1 = localObject3;
            localField = localField.getNext();
            localObject3 = localObject1;
            continue;
          }
        }
        else
        {
          localObject1 = ClassType.make(this.className);
          localClass = ((ClassType)localObject1).getReflectClass();
          continue;
        }
        Object localObject1 = localObject3;
        if ((i & 0x8) == 0)
        {
          localObject1 = localObject3;
          if (localObject3 != null) {}
        }
        Declaration localDeclaration2;
        try
        {
          localObject1 = getInstance();
          localObject3 = localClass.getField(localField.getName()).get(localObject1);
          localDeclaration2 = localLanguage.declFromField(localModuleExp, localObject3, localField);
          if (((i & 0x10) != 0) && ((!(localObject3 instanceof Location)) || ((localObject3 instanceof FieldLocation)))) {
            localDeclaration2.noteValue(new QuoteExp(localObject3));
          }
        }
        catch (Exception localException)
        {
          throw new WrappedException(localException);
        }
        localDeclaration2.noteValue(null);
      }
      finally {}
      continue;
      label229:
      for (Declaration localDeclaration1 = localModuleExp.firstDecl(); localDeclaration1 != null; localDeclaration1 = localDeclaration1.nextDecl()) {
        makeDeclInModule2(localModuleExp, localDeclaration1);
      }
    }
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("ModuleInfo[");
    if (this.moduleClass != null)
    {
      localStringBuffer.append("class: ");
      localStringBuffer.append(this.moduleClass);
    }
    for (;;)
    {
      localStringBuffer.append(']');
      return localStringBuffer.toString();
      if (this.className != null)
      {
        localStringBuffer.append("class-name: ");
        localStringBuffer.append(this.className);
      }
    }
  }
  
  static class ClassToInfoMap
    extends AbstractWeakHashTable<Class, ModuleInfo>
  {
    protected Class getKeyFromValue(ModuleInfo paramModuleInfo)
    {
      return paramModuleInfo.moduleClass;
    }
    
    protected boolean matches(Class paramClass1, Class paramClass2)
    {
      return paramClass1 == paramClass2;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ModuleInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */