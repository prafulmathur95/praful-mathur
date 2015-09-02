package gnu.commonlisp.lang;

import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;

public class Symbols
{
  public static Object getFunctionBinding(Environment paramEnvironment, Object paramObject)
  {
    return paramEnvironment.getFunction(getSymbol(paramObject));
  }
  
  public static Object getFunctionBinding(Object paramObject)
  {
    return Environment.getCurrent().getFunction(getSymbol(paramObject));
  }
  
  public static Object getPrintName(Object paramObject)
  {
    if (paramObject == Lisp2.FALSE) {
      return "nil";
    }
    return Lisp2.getString(((Symbol)paramObject).getName());
  }
  
  public static Symbol getSymbol(Environment paramEnvironment, Object paramObject)
  {
    Object localObject = paramObject;
    if (paramObject == Lisp2.FALSE) {
      localObject = "nil";
    }
    if ((localObject instanceof Symbol)) {
      return (Symbol)localObject;
    }
    return paramEnvironment.defaultNamespace().getSymbol((String)localObject);
  }
  
  public static Symbol getSymbol(Object paramObject)
  {
    Object localObject = paramObject;
    if (paramObject == Lisp2.FALSE) {
      localObject = "nil";
    }
    if ((localObject instanceof Symbol)) {
      return (Symbol)localObject;
    }
    return Namespace.getDefaultSymbol((String)localObject);
  }
  
  public static boolean isBound(Object paramObject)
  {
    if (paramObject == Lisp2.FALSE) {}
    for (;;)
    {
      return true;
      Environment localEnvironment = Environment.getCurrent();
      if ((paramObject instanceof Symbol)) {}
      for (paramObject = (Symbol)paramObject; (paramObject == null) || (!localEnvironment.isBound((Symbol)paramObject)); paramObject = localEnvironment.defaultNamespace().lookup((String)paramObject)) {
        return false;
      }
    }
  }
  
  public static boolean isSymbol(Object paramObject)
  {
    return ((paramObject instanceof String)) || (paramObject == Lisp2.FALSE) || ((paramObject instanceof Symbol));
  }
  
  public static void setFunctionBinding(Environment paramEnvironment, Object paramObject1, Object paramObject2)
  {
    paramEnvironment.put(getSymbol(paramObject1), EnvironmentKey.FUNCTION, paramObject2);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\commonlisp\lang\Symbols.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */