package com.google.appinventor.components.annotations;

import com.google.appinventor.components.common.ComponentCategory;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface DesignerComponent
{
  ComponentCategory category() default ComponentCategory.UNINITIALIZED;
  
  String description() default "";
  
  String designerHelpDescription() default "";
  
  String iconName() default "";
  
  boolean nonVisible() default false;
  
  boolean showOnPalette() default true;
  
  int version();
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\annotations\DesignerComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */