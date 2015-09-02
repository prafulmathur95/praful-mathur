package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@DesignerComponent(category=ComponentCategory.SENSORS, description="Non-visible component that can detect shaking and measure acceleration approximately in three dimensions using SI units (m/s<sup>2</sup>).  The components are: <ul>\n<li> <strong>xAccel</strong>: 0 when the phone is at rest on a flat      surface, positive when the phone is tilted to the right (i.e.,      its left side is raised), and negative when the phone is tilted      to the left (i.e., its right size is raised).</li>\n <li> <strong>yAccel</strong>: 0 when the phone is at rest on a flat      surface, positive when its bottom is raised, and negative when      its top is raised. </li>\n <li> <strong>zAccel</strong>: Equal to -9.8 (earth's gravity in meters per      second per second when the device is at rest parallel to the ground      with the display facing up,      0 when perpindicular to the ground, and +9.8 when facing down.       The value can also be affected by accelerating it with or against      gravity. </li></ul>", iconName="images/accelerometersensor.png", nonVisible=true, version=3)
@SimpleObject
public class AccelerometerSensor
  extends AndroidNonvisibleComponent
  implements OnStopListener, OnResumeListener, SensorComponent, SensorEventListener, Deleteable
{
  private static final int SENSOR_CACHE_SIZE = 10;
  private static final double moderateShakeThreshold = 13.0D;
  private static final double strongShakeThreshold = 20.0D;
  private static final double weakShakeThreshold = 5.0D;
  private final Queue<Float> X_CACHE = new LinkedList();
  private final Queue<Float> Y_CACHE = new LinkedList();
  private final Queue<Float> Z_CACHE = new LinkedList();
  private Sensor accelerometerSensor;
  private int accuracy;
  private boolean enabled;
  private int minimumInterval;
  private int sensitivity;
  private final SensorManager sensorManager;
  private long timeLastShook;
  private float xAccel;
  private float yAccel;
  private float zAccel;
  
  public AccelerometerSensor(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.form.registerForOnResume(this);
    this.form.registerForOnStop(this);
    this.enabled = true;
    this.sensorManager = ((SensorManager)paramComponentContainer.$context().getSystemService("sensor"));
    this.accelerometerSensor = this.sensorManager.getDefaultSensor(1);
    startListening();
    MinimumInterval(400);
    Sensitivity(2);
  }
  
  private void addToSensorCache(Queue<Float> paramQueue, float paramFloat)
  {
    if (paramQueue.size() >= 10) {
      paramQueue.remove();
    }
    paramQueue.add(Float.valueOf(paramFloat));
  }
  
  private boolean isShaking(Queue<Float> paramQueue, float paramFloat)
  {
    boolean bool = true;
    float f = 0.0F;
    Iterator localIterator = paramQueue.iterator();
    while (localIterator.hasNext()) {
      f += ((Float)localIterator.next()).floatValue();
    }
    f /= paramQueue.size();
    if (Sensitivity() == 1) {
      if (Math.abs(f - paramFloat) > 20.0D) {
        bool = true;
      }
    }
    do
    {
      do
      {
        for (;;)
        {
          return bool;
          bool = false;
        }
        if (Sensitivity() != 2) {
          break;
        }
      } while ((Math.abs(f - paramFloat) > 13.0D) && (Math.abs(f - paramFloat) < 20.0D));
      return false;
    } while ((Math.abs(f - paramFloat) > 5.0D) && (Math.abs(f - paramFloat) < 13.0D));
    return false;
  }
  
  private void startListening()
  {
    this.sensorManager.registerListener(this, this.accelerometerSensor, 1);
  }
  
  private void stopListening()
  {
    this.sensorManager.unregisterListener(this);
  }
  
  @SimpleEvent
  public void AccelerationChanged(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.xAccel = paramFloat1;
    this.yAccel = paramFloat2;
    this.zAccel = paramFloat3;
    addToSensorCache(this.X_CACHE, paramFloat1);
    addToSensorCache(this.Y_CACHE, paramFloat2);
    addToSensorCache(this.Z_CACHE, paramFloat3);
    long l = System.currentTimeMillis();
    if (((isShaking(this.X_CACHE, paramFloat1)) || (isShaking(this.Y_CACHE, paramFloat2)) || (isShaking(this.Z_CACHE, paramFloat3))) && ((this.timeLastShook == 0L) || (l >= this.timeLastShook + this.minimumInterval)))
    {
      this.timeLastShook = l;
      Shaking();
    }
    EventDispatcher.dispatchEvent(this, "AccelerationChanged", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3) });
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean Available()
  {
    return this.sensorManager.getSensorList(1).size() > 0;
  }
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty
  public void Enabled(boolean paramBoolean)
  {
    if (this.enabled == paramBoolean) {
      return;
    }
    this.enabled = paramBoolean;
    if (paramBoolean)
    {
      startListening();
      return;
    }
    stopListening();
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean Enabled()
  {
    return this.enabled;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The minimum interval between phone shakes")
  public int MinimumInterval()
  {
    return this.minimumInterval;
  }
  
  @DesignerProperty(defaultValue="400", editorType="non_negative_integer")
  @SimpleProperty
  public void MinimumInterval(int paramInt)
  {
    this.minimumInterval = paramInt;
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="A number that encodes how sensitive the accelerometer is. The choices are: 1 = weak, 2 = moderate,  3 = strong.")
  public int Sensitivity()
  {
    return this.sensitivity;
  }
  
  @DesignerProperty(defaultValue="2", editorType="accelerometer_sensitivity")
  @SimpleProperty
  public void Sensitivity(int paramInt)
  {
    if ((paramInt == 1) || (paramInt == 2) || (paramInt == 3))
    {
      this.sensitivity = paramInt;
      return;
    }
    this.form.dispatchErrorOccurredEvent(this, "Sensitivity", 1901, new Object[] { Integer.valueOf(paramInt) });
  }
  
  @SimpleEvent
  public void Shaking()
  {
    EventDispatcher.dispatchEvent(this, "Shaking", new Object[0]);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public float XAccel()
  {
    return this.xAccel;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public float YAccel()
  {
    return this.yAccel;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public float ZAccel()
  {
    return this.zAccel;
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onDelete()
  {
    if (this.enabled) {
      stopListening();
    }
  }
  
  public void onResume()
  {
    if (this.enabled) {
      startListening();
    }
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    if (this.enabled)
    {
      float[] arrayOfFloat = paramSensorEvent.values;
      this.xAccel = arrayOfFloat[0];
      this.yAccel = arrayOfFloat[1];
      this.zAccel = arrayOfFloat[2];
      this.accuracy = paramSensorEvent.accuracy;
      AccelerationChanged(this.xAccel, this.yAccel, this.zAccel);
    }
  }
  
  public void onStop()
  {
    if (this.enabled) {
      stopListening();
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\AccelerometerSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */