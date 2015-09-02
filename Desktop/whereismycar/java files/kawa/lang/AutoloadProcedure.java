package kawa.lang;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleContext;
import gnu.kawa.reflect.ClassMemberLocation;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.UnboundLocationException;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;
import java.lang.reflect.Field;

public class AutoloadProcedure
  extends Procedure
  implements Externalizable
{
  static final Class classModuleBody = ModuleBody.class;
  String className;
  Language language;
  Procedure loaded;
  
  public AutoloadProcedure() {}
  
  public AutoloadProcedure(String paramString1, String paramString2)
  {
    super(paramString1);
    this.className = paramString2;
  }
  
  public AutoloadProcedure(String paramString1, String paramString2, Language paramLanguage)
  {
    super(paramString1);
    this.className = paramString2;
    this.language = paramLanguage;
  }
  
  private void throw_error(String paramString)
  {
    this.loaded = null;
    String str = getName();
    StringBuilder localStringBuilder = new StringBuilder().append(paramString).append(this.className).append(" while autoloading ");
    if (str == null) {}
    for (paramString = "";; paramString = str.toString()) {
      throw new RuntimeException(paramString);
    }
  }
  
  public Object apply0()
    throws Throwable
  {
    return getLoaded().apply0();
  }
  
  public Object apply1(Object paramObject)
    throws Throwable
  {
    return getLoaded().apply1(paramObject);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
    throws Throwable
  {
    return getLoaded().apply2(paramObject1, paramObject2);
  }
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3)
    throws Throwable
  {
    return getLoaded().apply3(paramObject1, paramObject2, paramObject3);
  }
  
  public Object apply4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    throws Throwable
  {
    return getLoaded().apply4(paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    if (this.loaded == null) {
      load();
    }
    if ((this.loaded instanceof AutoloadProcedure)) {
      throw new InternalError("circularity in autoload of " + getName());
    }
    return this.loaded.applyN(paramArrayOfObject);
  }
  
  public Procedure getLoaded()
  {
    if (this.loaded == null) {
      load();
    }
    return this.loaded;
  }
  
  public Object getProperty(Object paramObject1, Object paramObject2)
  {
    Object localObject = super.getProperty(paramObject1, null);
    if (localObject != null) {
      return localObject;
    }
    return getLoaded().getProperty(paramObject1, paramObject2);
  }
  
  public Procedure getSetter()
  {
    if (this.loaded == null) {
      load();
    }
    if ((this.loaded instanceof HasSetter)) {
      return this.loaded.getSetter();
    }
    return super.getSetter();
  }
  
  void load()
  {
    Object localObject3 = null;
    Object localObject5 = getSymbol();
    Object localObject2 = this.language;
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = Language.getDefaultLanguage();
    }
    Environment localEnvironment = ((Language)localObject1).getLangEnvironment();
    if ((localObject5 instanceof Symbol)) {
      localObject2 = (Symbol)localObject5;
    }
    try
    {
      Class localClass = Class.forName(this.className);
      if (classModuleBody.isAssignableFrom(localClass))
      {
        localObject3 = ModuleContext.getContext().searchInstance(localClass);
        if (localObject3 != null) {}
      }
      for (;;)
      {
        Object localObject4;
        try
        {
          localObject3 = localClass.getDeclaredField("$instance").get(null);
          ClassMemberLocation.defineAll(localObject3, (Language)localObject1, localEnvironment);
          if ((localObject3 instanceof ModuleBody)) {
            ((ModuleBody)localObject3).run();
          }
          localObject1 = localEnvironment.getFunction((Symbol)localObject2, null);
          if ((localObject1 == null) || (!(localObject1 instanceof Procedure))) {
            throw_error("invalid ModuleBody class - does not define " + localObject5);
          }
          this.loaded = ((Procedure)localObject1);
          if ((localObject5 != null) && (this.loaded.getSymbol() == null)) {
            this.loaded.setSymbol(localObject5);
          }
          return;
          localObject2 = localEnvironment.getSymbol(localObject5.toString());
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          localObject4 = localClass.newInstance();
          continue;
        }
        this.loaded = ((Procedure)localClass.newInstance());
        if (this.loaded == this) {
          throw_error("circularity detected");
        }
        if (localObject5 != null) {
          try
          {
            if (((Language)localObject1).hasSeparateFunctionNamespace()) {
              localObject4 = EnvironmentKey.FUNCTION;
            }
            localEnvironment.put((Symbol)localObject2, localObject4, this.loaded);
          }
          catch (UnboundLocationException localUnboundLocationException) {}
        }
      }
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw_error("failed to find class ");
      return;
    }
    catch (InstantiationException localInstantiationException)
    {
      throw_error("failed to instantiate class ");
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw_error("illegal access in class ");
    }
  }
  
  public int numArgs()
  {
    return getLoaded().numArgs();
  }
  
  public void print(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.print("#<procedure ");
    String str = getName();
    if (str != null) {
      paramPrintWriter.print(str);
    }
    paramPrintWriter.print('>');
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    setName((String)paramObjectInput.readObject());
    this.className = ((String)paramObjectInput.readObject());
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(getName());
    paramObjectOutput.writeObject(this.className);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\AutoloadProcedure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */