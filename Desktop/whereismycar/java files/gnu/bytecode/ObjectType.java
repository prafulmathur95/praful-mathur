package gnu.bytecode;

import java.util.List;
import java.util.Vector;

public class ObjectType
  extends Type
{
  static final int ADD_ENCLOSING_DONE = 8;
  static final int ADD_FIELDS_DONE = 1;
  static final int ADD_MEMBERCLASSES_DONE = 4;
  static final int ADD_METHODS_DONE = 2;
  static final int EXISTING_CLASS = 16;
  static final int HAS_OUTER_LINK = 32;
  public int flags;
  
  protected ObjectType()
  {
    this.size = 4;
  }
  
  public ObjectType(String paramString)
  {
    this.this_name = paramString;
    this.size = 4;
  }
  
  public static Class getContextClass(String paramString)
    throws ClassNotFoundException
  {
    try
    {
      Class localClass = Class.forName(paramString, false, ObjectType.class.getClassLoader());
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    return Class.forName(paramString, false, getContextClassLoader());
  }
  
  public static ClassLoader getContextClassLoader()
  {
    try
    {
      ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
      return localClassLoader;
    }
    catch (SecurityException localSecurityException) {}
    return ObjectType.class.getClassLoader();
  }
  
  public Object coerceFromObject(Object paramObject)
  {
    Object localObject = paramObject;
    if (paramObject != null)
    {
      if (this != Type.toStringType) {
        break label20;
      }
      localObject = paramObject.toString();
    }
    label20:
    Class localClass1;
    Class localClass2;
    do
    {
      return localObject;
      localClass1 = getReflectClass();
      localClass2 = paramObject.getClass();
      localObject = paramObject;
    } while (localClass1.isAssignableFrom(localClass2));
    throw new ClassCastException("don't know how to coerce " + localClass2.getName() + " to " + getName());
  }
  
  public int compare(Type paramType)
  {
    if (paramType == nullType) {
      return 0;
    }
    return -1;
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr)
  {
    if (this == Type.toStringType)
    {
      paramCodeAttr.emitDup();
      paramCodeAttr.emitIfNull();
      paramCodeAttr.emitPop(1);
      paramCodeAttr.emitPushNull();
      paramCodeAttr.emitElse();
      paramCodeAttr.emitInvokeVirtual(Type.toString_method);
      paramCodeAttr.emitFi();
    }
    while (this == Type.objectType) {
      return;
    }
    paramCodeAttr.emitCheckcast(this);
  }
  
  public Field getField(String paramString, int paramInt)
  {
    return null;
  }
  
  public Type getImplementationType()
  {
    Object localObject;
    if (this == nullType) {
      localObject = objectType;
    }
    do
    {
      return (Type)localObject;
      localObject = this;
    } while (this != toStringType);
    return javalangStringType;
  }
  
  public String getInternalName()
  {
    return getName().replace('.', '/');
  }
  
  public Method getMethod(String paramString, Type[] paramArrayOfType)
  {
    return Type.objectType.getMethod(paramString, paramArrayOfType);
  }
  
  public int getMethods(Filter paramFilter, int paramInt, List<Method> paramList)
  {
    return Type.objectType.getMethods(paramFilter, paramInt, paramList);
  }
  
  public final int getMethods(Filter paramFilter, int paramInt, Vector paramVector, String paramString)
  {
    return Type.objectType.getMethods(paramFilter, paramInt, paramVector, paramString);
  }
  
  public Class getReflectClass()
  {
    try
    {
      if (this.reflectClass == null) {
        this.reflectClass = getContextClass(getInternalName().replace('/', '.'));
      }
      this.flags |= 0x10;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      while ((this.flags & 0x10) == 0) {}
      RuntimeException localRuntimeException = new RuntimeException("no such class: " + getName());
      localRuntimeException.initCause(localClassNotFoundException);
      throw localRuntimeException;
    }
    return this.reflectClass;
  }
  
  public final boolean isExisting()
  {
    boolean bool = false;
    Type localType2 = getImplementationType();
    Type localType1 = localType2;
    if ((localType2 instanceof ArrayType)) {
      localType1 = ((ArrayType)localType2).getComponentType();
    }
    if (localType1 == this) {
      return (this.flags & 0x10) != 0;
    }
    if ((!(localType1 instanceof ObjectType)) || (((ObjectType)localType1).isExisting())) {
      bool = true;
    }
    return bool;
  }
  
  public boolean isInstance(Object paramObject)
  {
    if (this == nullType) {
      return paramObject == null;
    }
    return super.isInstance(paramObject);
  }
  
  public Type promote()
  {
    Object localObject = this;
    if (this == nullType) {
      localObject = objectType;
    }
    return (Type)localObject;
  }
  
  public final void setExisting(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x10;
      return;
    }
    this.flags &= 0xFFFFFFEF;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\ObjectType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */