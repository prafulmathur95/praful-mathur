package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

public enum ComponentCategory
{
  private static final Map<String, String> DOC_MAP;
  private String name;
  
  static
  {
    LAYOUT = new ComponentCategory("LAYOUT", 1, "Layout");
    MEDIA = new ComponentCategory("MEDIA", 2, "Media");
    ANIMATION = new ComponentCategory("ANIMATION", 3, "Drawing and Animation");
    SENSORS = new ComponentCategory("SENSORS", 4, "Sensors");
    SOCIAL = new ComponentCategory("SOCIAL", 5, "Social");
    STORAGE = new ComponentCategory("STORAGE", 6, "Storage");
    CONNECTIVITY = new ComponentCategory("CONNECTIVITY", 7, "Connectivity");
    LEGOMINDSTORMS = new ComponentCategory("LEGOMINDSTORMS", 8, "LEGO® MINDSTORMS®");
    INTERNAL = new ComponentCategory("INTERNAL", 9, "For internal use only");
    UNINITIALIZED = new ComponentCategory("UNINITIALIZED", 10, "Uninitialized");
    $VALUES = new ComponentCategory[] { USERINTERFACE, LAYOUT, MEDIA, ANIMATION, SENSORS, SOCIAL, STORAGE, CONNECTIVITY, LEGOMINDSTORMS, INTERNAL, UNINITIALIZED };
    DOC_MAP = new HashMap();
    DOC_MAP.put("User Interface", "userinterface");
    DOC_MAP.put("Layout", "layout");
    DOC_MAP.put("media", "media");
    DOC_MAP.put("Drawing and Animation", "animation");
    DOC_MAP.put("Sensors", "sensors");
    DOC_MAP.put("Social", "social");
    DOC_MAP.put("Storage", "storage");
    DOC_MAP.put("Connectivity", "connectivity");
    DOC_MAP.put("LEGO® MINDSTORMS®", "legomindstorms");
  }
  
  private ComponentCategory(String paramString)
  {
    this.name = paramString;
  }
  
  public String getDocName()
  {
    return (String)DOC_MAP.get(this.name);
  }
  
  public String getName()
  {
    return this.name;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\common\ComponentCategory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */