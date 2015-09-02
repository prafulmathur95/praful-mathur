package com.google.appinventor.components.runtime.util;

public class OrientationSensorUtil
{
  static float mod(float paramFloat1, float paramFloat2)
  {
    float f = paramFloat1 % paramFloat2;
    if ((f == 0.0F) || (Math.signum(paramFloat1) == Math.signum(paramFloat2))) {
      return f;
    }
    return f + paramFloat2;
  }
  
  public static float normalizeAzimuth(float paramFloat)
  {
    return mod(paramFloat, 360.0F);
  }
  
  public static float normalizePitch(float paramFloat)
  {
    return mod(paramFloat + 180.0F, 360.0F) - 180.0F;
  }
  
  public static float normalizeRoll(float paramFloat)
  {
    paramFloat = Math.max(Math.min(paramFloat, 180.0F), -180.0F);
    if ((paramFloat >= -90.0F) && (paramFloat <= 90.0F)) {}
    float f;
    do
    {
      return paramFloat;
      f = 180.0F - paramFloat;
      paramFloat = f;
    } while (f < 270.0F);
    return f - 360.0F;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\OrientationSensorUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */