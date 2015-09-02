package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.mapping.Values;

public class SingletonType
  extends ObjectType
{
  static final SingletonType instance = new SingletonType("singleton");
  
  public SingletonType(String paramString)
  {
    super(paramString);
  }
  
  public static Object coerceToSingleton(Object paramObject)
  {
    Object localObject = paramObject;
    if ((paramObject instanceof Values)) {
      localObject = ((Values)paramObject).canonicalize();
    }
    if ((localObject == null) || ((localObject instanceof Values))) {
      throw new ClassCastException("value is not a singleton");
    }
    return localObject;
  }
  
  public static final SingletonType getInstance()
  {
    return instance;
  }
  
  public Object coerceFromObject(Object paramObject)
  {
    return coerceToSingleton(paramObject);
  }
  
  public int compare(Type paramType)
  {
    int j = -1;
    int k = OccurrenceType.itemCountRange(paramType);
    int i = k & 0xFFF;
    k >>= 12;
    if ((k == 0) || (i > 1)) {
      i = -3;
    }
    do
    {
      do
      {
        return i;
        if ((i == 1) && (k == 1)) {
          return Type.pointer_type.compare(paramType);
        }
        k = Type.pointer_type.compare(paramType);
        i = j;
      } while (k == 0);
      i = j;
    } while (k == -1);
    return -2;
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr)
  {
    paramCodeAttr.emitInvokeStatic(ClassType.make("gnu.kawa.reflect.SingletonType").getDeclaredMethod("coerceToSingleton", 1));
  }
  
  public Type getImplementationType()
  {
    return Type.pointer_type;
  }
  
  public Class getReflectClass()
  {
    return getImplementationType().getReflectClass();
  }
  
  public boolean isInstance(Object paramObject)
  {
    return (paramObject != null) && (!(paramObject instanceof Values));
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\SingletonType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */