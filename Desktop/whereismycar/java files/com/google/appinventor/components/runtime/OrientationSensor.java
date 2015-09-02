package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.FroyoUtil;
import com.google.appinventor.components.runtime.util.OrientationSensorUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.util.List;

@DesignerComponent(category=ComponentCategory.SENSORS, description="<p>Non-visible component providing information about the device's physical orientation in three dimensions: <ul> <li> <strong>Roll</strong>: 0 degrees when the device is level, increases to      90 degrees as the device is tilted up on its left side, and      decreases to -90 degrees when the device is tilted up on its right side.      </li> <li> <strong>Pitch</strong>: 0 degrees when the device is level, up to      90 degrees as the device is tilted so its top is pointing down,      up to 180 degrees as it gets turned over.  Similarly, as the device      is tilted so its bottom points down, pitch decreases to -90      degrees, then further decreases to -180 degrees as it gets turned all the way      over.</li> <li> <strong>Azimuth</strong>: 0 degrees when the top of the device is      pointing north, 90 degrees when it is pointing east, 180 degrees      when it is pointing south, 270 degrees when it is pointing west,      etc.</li></ul>     These measurements assume that the device itself is not moving.</p>", iconName="images/orientationsensor.png", nonVisible=true, version=2)
@SimpleObject
public class OrientationSensor
  extends AndroidNonvisibleComponent
  implements SensorEventListener, Deleteable, OnPauseListener, OnResumeListener
{
  private static final int AZIMUTH = 0;
  private static final int DIMENSIONS = 3;
  private static final String LOG_TAG = "OrientationSensor";
  private static final int PITCH = 1;
  private static final int ROLL = 2;
  private final Sensor accelerometerSensor;
  private final float[] accels = new float[3];
  private boolean accelsFilled;
  private int accuracy;
  private float azimuth;
  private boolean enabled;
  private final float[] inclinationMatrix = new float[9];
  private boolean listening;
  private final Sensor magneticFieldSensor;
  private final float[] mags = new float[3];
  private boolean magsFilled;
  private float pitch;
  private float roll;
  private final float[] rotationMatrix = new float[9];
  private final SensorManager sensorManager;
  private final float[] values = new float[3];
  
  public OrientationSensor(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.sensorManager = ((SensorManager)paramComponentContainer.$context().getSystemService("sensor"));
    this.accelerometerSensor = this.sensorManager.getDefaultSensor(1);
    this.magneticFieldSensor = this.sensorManager.getDefaultSensor(2);
    this.form.registerForOnResume(this);
    this.form.registerForOnPause(this);
    Enabled(true);
  }
  
  static float computeAngle(float paramFloat1, float paramFloat2)
  {
    return (float)Math.toDegrees(Math.atan2(Math.toRadians(paramFloat1), -Math.toRadians(paramFloat2)));
  }
  
  private int getScreenRotation()
  {
    Display localDisplay = ((WindowManager)this.form.getSystemService("window")).getDefaultDisplay();
    if (SdkLevel.getLevel() >= 8) {
      return FroyoUtil.getRotation(localDisplay);
    }
    return localDisplay.getOrientation();
  }
  
  private void startListening()
  {
    if (!this.listening)
    {
      this.sensorManager.registerListener(this, this.accelerometerSensor, 3);
      this.sensorManager.registerListener(this, this.magneticFieldSensor, 3);
      this.listening = true;
    }
  }
  
  private void stopListening()
  {
    if (this.listening)
    {
      this.sensorManager.unregisterListener(this);
      this.listening = false;
      this.accelsFilled = false;
      this.magsFilled = false;
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public float Angle()
  {
    return computeAngle(this.pitch, this.roll);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean Available()
  {
    return (this.sensorManager.getSensorList(1).size() > 0) && (this.sensorManager.getSensorList(2).size() > 0);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public float Azimuth()
  {
    return this.azimuth;
  }
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty
  public void Enabled(boolean paramBoolean)
  {
    if (this.enabled != paramBoolean)
    {
      this.enabled = paramBoolean;
      if (paramBoolean) {
        startListening();
      }
    }
    else
    {
      return;
    }
    stopListening();
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean Enabled()
  {
    return this.enabled;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public float Magnitude()
  {
    double d1 = Math.toRadians(Math.min(90.0F, Math.abs(this.pitch)));
    double d2 = Math.toRadians(Math.min(90.0F, Math.abs(this.roll)));
    return (float)(1.0D - Math.cos(d1) * Math.cos(d2));
  }
  
  @SimpleEvent
  public void OrientationChanged(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    EventDispatcher.dispatchEvent(this, "OrientationChanged", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3) });
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public float Pitch()
  {
    return this.pitch;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public float Roll()
  {
    return this.roll;
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onDelete()
  {
    stopListening();
  }
  
  public void onPause()
  {
    stopListening();
  }
  
  public void onResume()
  {
    if (this.enabled) {
      startListening();
    }
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    int i;
    if (this.enabled) {
      i = paramSensorEvent.sensor.getType();
    }
    switch (i)
    {
    default: 
      Log.e("OrientationSensor", "Unexpected sensor type: " + i);
    case 1: 
      do
      {
        return;
        System.arraycopy(paramSensorEvent.values, 0, this.accels, 0, 3);
        this.accelsFilled = true;
        this.accuracy = paramSensorEvent.accuracy;
      } while ((!this.accelsFilled) || (!this.magsFilled));
      SensorManager.getRotationMatrix(this.rotationMatrix, this.inclinationMatrix, this.accels, this.mags);
      SensorManager.getOrientation(this.rotationMatrix, this.values);
      this.azimuth = OrientationSensorUtil.normalizeAzimuth((float)Math.toDegrees(this.values[0]));
      this.pitch = OrientationSensorUtil.normalizePitch((float)Math.toDegrees(this.values[1]));
      this.roll = OrientationSensorUtil.normalizeRoll((float)-Math.toDegrees(this.values[2]));
      i = getScreenRotation();
      switch (i)
      {
      default: 
        Log.e("OrientationSensor", "Illegal value for getScreenRotation(): " + i);
      }
      break;
    }
    for (;;)
    {
      OrientationChanged(this.azimuth, this.pitch, this.roll);
      return;
      System.arraycopy(paramSensorEvent.values, 0, this.mags, 0, 3);
      this.magsFilled = true;
      break;
      float f = -this.pitch;
      this.pitch = (-this.roll);
      this.roll = f;
      continue;
      this.roll = (-this.roll);
      continue;
      f = this.pitch;
      this.pitch = this.roll;
      this.roll = f;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\OrientationSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */