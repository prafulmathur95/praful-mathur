package com.google.appinventor.components.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface SimpleProperty
{
  PropertyCategory category() default PropertyCategory.UNSET;
  
  String description() default "";
  
  boolean userVisible() default true;
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\annotations\SimpleProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */