package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.SimplePropertyCopier;
import com.google.appinventor.components.runtime.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertyUtil
{
  public static Component copyComponentProperties(Component paramComponent1, Component paramComponent2)
    throws Throwable
  {
    if (!paramComponent1.getClass().equals(paramComponent2.getClass())) {
      throw new IllegalArgumentException("Source and target classes must be identical");
    }
    Class localClass = paramComponent1.getClass();
    Method[] arrayOfMethod = localClass.getMethods();
    int j = arrayOfMethod.length;
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        Method localMethod = arrayOfMethod[i];
        if ((localMethod.isAnnotationPresent(SimpleProperty.class)) && (localMethod.getParameterTypes().length == 1)) {
          try
          {
            Object localObject1 = localMethod.getName();
            Object localObject2 = getPropertyCopierMethod("Copy" + (String)localObject1, localClass);
            if (localObject2 != null)
            {
              ((Method)localObject2).invoke(paramComponent2, new Object[] { paramComponent1 });
            }
            else
            {
              localObject1 = localClass.getMethod((String)localObject1, new Class[0]);
              localObject2 = localMethod.getParameterTypes()[0];
              if ((((Method)localObject1).isAnnotationPresent(SimpleProperty.class)) && (((Class)localObject2).isAssignableFrom(((Method)localObject1).getReturnType()))) {
                localMethod.invoke(paramComponent2, new Object[] { ((Method)localObject1).invoke(paramComponent1, new Object[0]) });
              }
            }
          }
          catch (NoSuchMethodException localNoSuchMethodException) {}catch (InvocationTargetException paramComponent1)
          {
            throw paramComponent1.getCause();
          }
        }
      }
      else
      {
        return paramComponent2;
      }
      i += 1;
    }
  }
  
  private static Method getPropertyCopierMethod(String paramString, Class paramClass)
  {
    Class localClass;
    do
    {
      try
      {
        Method localMethod = paramClass.getMethod(paramString, new Class[] { paramClass });
        boolean bool = localMethod.isAnnotationPresent(SimplePropertyCopier.class);
        if (bool) {
          return localMethod;
        }
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        localClass = paramClass.getSuperclass();
        paramClass = localClass;
      }
    } while (localClass != null);
    return null;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\PropertyUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */