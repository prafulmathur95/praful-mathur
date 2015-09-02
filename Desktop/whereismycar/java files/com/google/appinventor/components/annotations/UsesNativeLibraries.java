package com.google.appinventor.components.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface UsesNativeLibraries
{
  String libraries() default "";
  
  String v7aLibraries() default "";
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\annotations\UsesNativeLibraries.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */