package gnu.mapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class LazyPropertyKey<T>
  extends PropertyKey<T>
{
  public LazyPropertyKey(String paramString)
  {
    super(paramString);
  }
  
  public T get(PropertySet paramPropertySet, T paramT)
  {
    paramT = paramPropertySet.getProperty(this, paramT);
    if ((paramT instanceof String))
    {
      Object localObject1 = (String)paramT;
      if (((String)localObject1).charAt(0) == '*') {}
      int j;
      for (int i = 1;; i = 0)
      {
        j = ((String)localObject1).indexOf(':');
        if ((j > i) && (j < ((String)localObject1).length() - 1)) {
          break;
        }
        throw new RuntimeException("lazy property " + this + " must have the form \"ClassName:fieldName\" or \"ClassName:staticMethodName\"");
      }
      Object localObject2 = ((String)localObject1).substring(i, j);
      paramT = ((String)localObject1).substring(j + 1);
      try
      {
        localObject2 = Class.forName((String)localObject2, true, paramPropertySet.getClass().getClassLoader());
        if (i == 0) {}
        for (paramT = ((Class)localObject2).getField(paramT).get(null);; paramT = ((Class)localObject2).getDeclaredMethod(paramT, new Class[] { Object.class }).invoke(null, new Object[] { paramPropertySet }))
        {
          paramPropertySet.setProperty(this, paramT);
          return paramT;
        }
        paramPropertySet = "field";
      }
      catch (Throwable paramT)
      {
        localObject1 = new StringBuilder().append("lazy property ").append(this).append(" has specifier \"").append((String)localObject1).append("\" but there is no such ");
        if (i != 0) {}
      }
      for (;;)
      {
        throw new RuntimeException(paramPropertySet, paramT);
        paramPropertySet = "method";
      }
    }
    return paramT;
  }
  
  public void set(PropertySet paramPropertySet, String paramString)
  {
    paramPropertySet.setProperty(this, paramString);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\LazyPropertyKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */