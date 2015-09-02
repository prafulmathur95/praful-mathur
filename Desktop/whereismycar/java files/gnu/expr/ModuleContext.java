package gnu.expr;

import gnu.kawa.util.AbstractWeakHashTable;
import gnu.mapping.WrappedException;
import java.lang.reflect.Field;

public class ModuleContext
{
  public static int IN_HTTP_SERVER = 1;
  public static int IN_SERVLET = 2;
  static ModuleContext global = new ModuleContext(ModuleManager.instance);
  int flags;
  ModuleManager manager;
  private ClassToInstanceMap table = new ClassToInstanceMap();
  
  public ModuleContext(ModuleManager paramModuleManager)
  {
    this.manager = paramModuleManager;
  }
  
  public static ModuleContext getContext()
  {
    return global;
  }
  
  public void addFlags(int paramInt)
  {
    this.flags |= paramInt;
  }
  
  public void clear()
  {
    try
    {
      this.table.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public ModuleInfo findFromInstance(Object paramObject)
  {
    Object localObject = paramObject.getClass();
    try
    {
      ModuleManager localModuleManager = this.manager;
      localObject = ModuleManager.findWithClass((Class)localObject);
      setInstance(paramObject);
      return (ModuleInfo)localObject;
    }
    finally {}
  }
  
  public Object findInstance(ModuleInfo paramModuleInfo)
  {
    try
    {
      Class localClass = paramModuleInfo.getModuleClass();
      paramModuleInfo = findInstance(localClass);
      return paramModuleInfo;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      paramModuleInfo = paramModuleInfo.getClassName();
      throw new WrappedException("cannot find module " + paramModuleInfo, localClassNotFoundException);
    }
    finally {}
  }
  
  public Object findInstance(Class paramClass)
  {
    try
    {
      Object localObject1 = this.table.get(paramClass);
      Object localObject3 = localObject1;
      if (localObject1 == null) {}
      try
      {
        localObject1 = paramClass.getDeclaredField("$instance").get(null);
        paramClass = (Class)localObject1;
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        for (;;)
        {
          Object localObject2 = paramClass.newInstance();
          paramClass = (Class)localObject2;
        }
      }
      catch (Throwable localThrowable)
      {
        throw new WrappedException("exception while initializing module " + paramClass.getName(), localThrowable);
      }
      setInstance(paramClass);
      localObject3 = paramClass;
      return localObject3;
    }
    finally {}
  }
  
  public int getFlags()
  {
    return this.flags;
  }
  
  public ModuleManager getManager()
  {
    return this.manager;
  }
  
  public Object searchInstance(Class paramClass)
  {
    try
    {
      paramClass = this.table.get(paramClass);
      return paramClass;
    }
    finally
    {
      paramClass = finally;
      throw paramClass;
    }
  }
  
  public void setFlags(int paramInt)
  {
    this.flags = paramInt;
  }
  
  public void setInstance(Object paramObject)
  {
    try
    {
      this.table.put(paramObject.getClass(), paramObject);
      return;
    }
    finally
    {
      paramObject = finally;
      throw ((Throwable)paramObject);
    }
  }
  
  static class ClassToInstanceMap
    extends AbstractWeakHashTable<Class, Object>
  {
    protected Class getKeyFromValue(Object paramObject)
    {
      return paramObject.getClass();
    }
    
    protected boolean matches(Class paramClass1, Class paramClass2)
    {
      return paramClass1 == paramClass2;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ModuleContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */