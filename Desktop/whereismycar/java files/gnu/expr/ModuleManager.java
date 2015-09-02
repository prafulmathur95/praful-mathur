package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import gnu.text.URLPath;
import java.io.File;
import java.net.URL;

public class ModuleManager
{
  public static final long LAST_MODIFIED_CACHE_TIME = 1000L;
  static ModuleManager instance = new ModuleManager();
  private String compilationDirectory = "";
  public long lastModifiedCacheTime = 1000L;
  ModuleInfo[] modules;
  int numModules;
  ModuleSet packageInfoChain;
  
  /* Error */
  private void add(ModuleInfo paramModuleInfo)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 38	gnu/expr/ModuleManager:modules	[Lgnu/expr/ModuleInfo;
    //   6: ifnonnull +36 -> 42
    //   9: aload_0
    //   10: bipush 10
    //   12: anewarray 40	gnu/expr/ModuleInfo
    //   15: putfield 38	gnu/expr/ModuleManager:modules	[Lgnu/expr/ModuleInfo;
    //   18: aload_0
    //   19: getfield 38	gnu/expr/ModuleManager:modules	[Lgnu/expr/ModuleInfo;
    //   22: astore_2
    //   23: aload_0
    //   24: getfield 42	gnu/expr/ModuleManager:numModules	I
    //   27: istore_3
    //   28: aload_0
    //   29: iload_3
    //   30: iconst_1
    //   31: iadd
    //   32: putfield 42	gnu/expr/ModuleManager:numModules	I
    //   35: aload_2
    //   36: iload_3
    //   37: aload_1
    //   38: aastore
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: aload_0
    //   43: getfield 42	gnu/expr/ModuleManager:numModules	I
    //   46: aload_0
    //   47: getfield 38	gnu/expr/ModuleManager:modules	[Lgnu/expr/ModuleInfo;
    //   50: arraylength
    //   51: if_icmpne -33 -> 18
    //   54: aload_0
    //   55: getfield 42	gnu/expr/ModuleManager:numModules	I
    //   58: iconst_2
    //   59: imul
    //   60: anewarray 40	gnu/expr/ModuleInfo
    //   63: astore_2
    //   64: aload_0
    //   65: getfield 38	gnu/expr/ModuleManager:modules	[Lgnu/expr/ModuleInfo;
    //   68: iconst_0
    //   69: aload_2
    //   70: iconst_0
    //   71: aload_0
    //   72: getfield 42	gnu/expr/ModuleManager:numModules	I
    //   75: invokestatic 48	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   78: aload_0
    //   79: aload_2
    //   80: putfield 38	gnu/expr/ModuleManager:modules	[Lgnu/expr/ModuleInfo;
    //   83: goto -65 -> 18
    //   86: astore_1
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	ModuleManager
    //   0	91	1	paramModuleInfo	ModuleInfo
    //   22	58	2	arrayOfModuleInfo	ModuleInfo[]
    //   27	10	3	i	int
    // Exception table:
    //   from	to	target	type
    //   2	18	86	finally
    //   18	35	86	finally
    //   42	83	86	finally
  }
  
  public static ModuleInfo findWithClass(Class paramClass)
  {
    try
    {
      ModuleInfo localModuleInfo2 = (ModuleInfo)ModuleInfo.mapClassToInfo.get(paramClass);
      ModuleInfo localModuleInfo1 = localModuleInfo2;
      if (localModuleInfo2 == null)
      {
        localModuleInfo1 = new ModuleInfo();
        localModuleInfo1.setModuleClass(paramClass);
      }
      return localModuleInfo1;
    }
    finally {}
  }
  
  public static ModuleManager getInstance()
  {
    return instance;
  }
  
  /* Error */
  private ModuleInfo searchWithAbsSourcePath(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 42	gnu/expr/ModuleManager:numModules	I
    //   6: istore_3
    //   7: iload_3
    //   8: iconst_1
    //   9: isub
    //   10: istore_3
    //   11: iload_3
    //   12: iflt +31 -> 43
    //   15: aload_0
    //   16: getfield 38	gnu/expr/ModuleManager:modules	[Lgnu/expr/ModuleInfo;
    //   19: iload_3
    //   20: aaload
    //   21: astore_2
    //   22: aload_1
    //   23: aload_2
    //   24: invokevirtual 73	gnu/expr/ModuleInfo:getSourceAbsPathname	()Ljava/lang/String;
    //   27: invokevirtual 79	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   30: istore 4
    //   32: iload 4
    //   34: ifeq -27 -> 7
    //   37: aload_2
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: areturn
    //   43: aconst_null
    //   44: astore_1
    //   45: goto -6 -> 39
    //   48: astore_1
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_1
    //   52: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	53	0	this	ModuleManager
    //   0	53	1	paramString	String
    //   21	17	2	localModuleInfo	ModuleInfo
    //   6	14	3	i	int
    //   30	3	4	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	7	48	finally
    //   15	32	48	finally
  }
  
  public void clear()
  {
    try
    {
      ModuleSet localModuleSet;
      for (Object localObject1 = this.packageInfoChain; localObject1 != null; localObject1 = localModuleSet)
      {
        localModuleSet = ((ModuleSet)localObject1).next;
        ((ModuleSet)localObject1).next = null;
      }
      this.packageInfoChain = null;
      this.modules = null;
      this.numModules = 0;
      return;
    }
    finally {}
  }
  
  public ModuleInfo find(Compilation paramCompilation)
  {
    try
    {
      ModuleExp localModuleExp = paramCompilation.getModule();
      ClassType localClassType = localModuleExp.classFor(paramCompilation);
      Object localObject = localModuleExp.getFileName();
      localObject = findWithSourcePath(ModuleInfo.absPath((String)localObject), (String)localObject);
      ((ModuleInfo)localObject).setClassName(localClassType.getName());
      ((ModuleInfo)localObject).exp = localModuleExp;
      paramCompilation.minfo = ((ModuleInfo)localObject);
      ((ModuleInfo)localObject).comp = paramCompilation;
      return (ModuleInfo)localObject;
    }
    finally
    {
      paramCompilation = finally;
      throw paramCompilation;
    }
  }
  
  public ModuleInfo findWithClassName(String paramString)
  {
    ModuleInfo localModuleInfo = searchWithClassName(paramString);
    if (localModuleInfo != null) {
      return localModuleInfo;
    }
    try
    {
      paramString = findWithClass(ClassType.getContextClass(paramString));
      return paramString;
    }
    catch (Throwable paramString)
    {
      throw WrappedException.wrapIfNeeded(paramString);
    }
  }
  
  public ModuleInfo findWithSourcePath(Path paramPath, String paramString)
  {
    try
    {
      String str = paramPath.toString();
      ModuleInfo localModuleInfo2 = searchWithAbsSourcePath(str);
      ModuleInfo localModuleInfo1 = localModuleInfo2;
      if (localModuleInfo2 == null)
      {
        localModuleInfo1 = new ModuleInfo();
        localModuleInfo1.sourcePath = paramString;
        localModuleInfo1.sourceAbsPath = paramPath;
        localModuleInfo1.sourceAbsPathname = str;
        add(localModuleInfo1);
      }
      return localModuleInfo1;
    }
    finally {}
  }
  
  public ModuleInfo findWithSourcePath(String paramString)
  {
    try
    {
      paramString = findWithSourcePath(ModuleInfo.absPath(paramString), paramString);
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public ModuleInfo findWithURL(URL paramURL)
  {
    try
    {
      paramURL = findWithSourcePath(URLPath.valueOf(paramURL), paramURL.toExternalForm());
      return paramURL;
    }
    finally
    {
      paramURL = finally;
      throw paramURL;
    }
  }
  
  public String getCompilationDirectory()
  {
    return this.compilationDirectory;
  }
  
  /* Error */
  public ModuleInfo getModule(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 42	gnu/expr/ModuleManager:numModules	I
    //   6: istore_3
    //   7: iload_1
    //   8: iload_3
    //   9: if_icmplt +9 -> 18
    //   12: aconst_null
    //   13: astore_2
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_2
    //   17: areturn
    //   18: aload_0
    //   19: getfield 38	gnu/expr/ModuleManager:modules	[Lgnu/expr/ModuleInfo;
    //   22: iload_1
    //   23: aaload
    //   24: astore_2
    //   25: goto -11 -> 14
    //   28: astore_2
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_2
    //   32: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	33	0	this	ModuleManager
    //   0	33	1	paramInt	int
    //   13	12	2	localModuleInfo	ModuleInfo
    //   28	4	2	localObject	Object
    //   6	4	3	i	int
    // Exception table:
    //   from	to	target	type
    //   2	7	28	finally
    //   18	25	28	finally
  }
  
  public void loadPackageInfo(String paramString)
    throws ClassNotFoundException, InstantiationException, IllegalAccessException
  {
    try
    {
      String str = paramString + "." + "$ModulesMap$";
      for (paramString = this.packageInfoChain; paramString != null; paramString = paramString.next) {
        if (!paramString.getClass().getName().equals(str)) {}
      }
      paramString = (ModuleSet)Class.forName(str).newInstance();
      paramString.next = this.packageInfoChain;
      this.packageInfoChain = paramString;
      paramString.register(this);
      return;
    }
    finally {}
  }
  
  public void register(String paramString1, String paramString2, String paramString3)
  {
    for (;;)
    {
      try
      {
        ModuleInfo localModuleInfo = searchWithClassName(paramString1);
        if (localModuleInfo != null) {
          return;
        }
        Object localObject = Path.valueOf(paramString2);
        String str = ((Path)localObject).getCanonical().toString();
        if (searchWithAbsSourcePath(str) == null)
        {
          localModuleInfo = new ModuleInfo();
          if (((Path)localObject).isAbsolute())
          {
            localModuleInfo.sourceAbsPath = ((Path)localObject);
            localModuleInfo.sourceAbsPathname = str;
            localModuleInfo.setClassName(paramString1);
            localModuleInfo.sourcePath = paramString2;
            localModuleInfo.uri = paramString3;
            add(localModuleInfo);
          }
          else
          {
            try
            {
              localObject = this.packageInfoChain.getClass();
              str = ((Class)localObject).getName().replace('.', '/') + ".class";
              localObject = URLPath.valueOf(((Class)localObject).getClassLoader().getResource(str)).resolve(paramString2);
              localModuleInfo.sourceAbsPath = ((Path)localObject);
              localModuleInfo.sourceAbsPathname = localObject.toString();
            }
            catch (Throwable paramString1) {}
          }
        }
      }
      finally {}
    }
  }
  
  /* Error */
  public ModuleInfo searchWithClassName(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 42	gnu/expr/ModuleManager:numModules	I
    //   6: istore_3
    //   7: iload_3
    //   8: iconst_1
    //   9: isub
    //   10: istore_3
    //   11: iload_3
    //   12: iflt +31 -> 43
    //   15: aload_0
    //   16: getfield 38	gnu/expr/ModuleManager:modules	[Lgnu/expr/ModuleInfo;
    //   19: iload_3
    //   20: aaload
    //   21: astore_2
    //   22: aload_1
    //   23: aload_2
    //   24: invokevirtual 260	gnu/expr/ModuleInfo:getClassName	()Ljava/lang/String;
    //   27: invokevirtual 79	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   30: istore 4
    //   32: iload 4
    //   34: ifeq -27 -> 7
    //   37: aload_2
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: areturn
    //   43: aconst_null
    //   44: astore_1
    //   45: goto -6 -> 39
    //   48: astore_1
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_1
    //   52: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	53	0	this	ModuleManager
    //   0	53	1	paramString	String
    //   21	17	2	localModuleInfo	ModuleInfo
    //   6	14	3	i	int
    //   30	3	4	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	7	48	finally
    //   15	32	48	finally
  }
  
  public void setCompilationDirectory(String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    int i = str.length();
    paramString = str;
    if (i > 0)
    {
      char c = File.separatorChar;
      paramString = str;
      if (str.charAt(i - 1) != c) {
        paramString = str + c;
      }
    }
    this.compilationDirectory = paramString;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ModuleManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */