package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import java.lang.reflect.Array;
import java.util.List;

@DesignerComponent(category=ComponentCategory.INTERNAL, description="Component that can count steps.", iconName="images/pedometer.png", nonVisible=true, version=1)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.ACCESS_FINE_LOCATION")
public class Pedometer
  extends AndroidNonvisibleComponent
  implements Component, LocationListener, SensorEventListener, Deleteable
{
  private static final int DIMENSIONS = 3;
  private static final int INTERVAL_VARIATION = 250;
  private static final int MIN_SATELLITES = 4;
  private static final int NUM_INTERVALS = 2;
  private static final float PEAK_VALLEY_RANGE = 4.0F;
  private static final String PREFS_NAME = "PedometerPrefs";
  private static final float STRIDE_LENGTH = 0.73F;
  private static final String TAG = "Pedometer";
  private static final int WIN_SIZE = 20;
  private boolean calibrateSteps = true;
  private final Context context;
  private Location currentLocation;
  private float distWhenGPSLost = 0.0F;
  private long elapsedTimestamp = 0L;
  private boolean firstGpsReading = true;
  private boolean foundNonStep = true;
  private boolean[] foundValley = new boolean[3];
  private boolean gpsAvailable = false;
  private float gpsDistance = 0.0F;
  private long gpsStepTime = 0L;
  private int intervalPos = 0;
  private int lastNumSteps = 0;
  private float[] lastValley = new float[3];
  private float[][] lastValues = (float[][])Array.newInstance(Float.TYPE, new int[] { 20, 3 });
  private final LocationManager locationManager;
  private Location locationWhenGPSLost;
  private int numStepsRaw = 0;
  private int numStepsWithFilter = 0;
  private int[] peak = new int[3];
  private boolean pedometerPaused = true;
  private float[] prevDiff = new float[3];
  private Location prevLocation;
  private long prevStopClockTime = 0L;
  private final SensorManager sensorManager;
  private boolean startPeaking = false;
  private long startTime = 0L;
  private boolean statusMoving = false;
  private long[] stepInterval = new long[2];
  private long stepTimestamp = 0L;
  private int stopDetectionTimeout = 2000;
  private float strideLength = 0.73F;
  private float totalDistance = 0.0F;
  private boolean useGps = true;
  private int[] valley = new int[3];
  private int winPos = 0;
  
  public Pedometer(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.context = paramComponentContainer.$context();
    this.winPos = 0;
    this.startPeaking = false;
    this.numStepsWithFilter = 0;
    this.numStepsRaw = 0;
    this.firstGpsReading = true;
    this.gpsDistance = 0.0F;
    int i = 0;
    while (i < 3)
    {
      this.foundValley[i] = true;
      this.lastValley[i] = 0.0F;
      i += 1;
    }
    this.sensorManager = ((SensorManager)this.context.getSystemService("sensor"));
    this.locationManager = ((LocationManager)this.context.getSystemService("location"));
    this.locationManager.requestLocationUpdates("gps", 0L, 0.0F, this);
    paramComponentContainer = this.context.getSharedPreferences("PedometerPrefs", 0);
    this.strideLength = paramComponentContainer.getFloat("Pedometer.stridelength", 0.73F);
    this.totalDistance = paramComponentContainer.getFloat("Pedometer.distance", 0.0F);
    this.numStepsRaw = paramComponentContainer.getInt("Pedometer.prevStepCount", 0);
    this.prevStopClockTime = paramComponentContainer.getLong("Pedometer.clockTime", 0L);
    this.numStepsWithFilter = this.numStepsRaw;
    this.startTime = System.currentTimeMillis();
    Log.d("Pedometer", "Pedometer Created");
  }
  
  private boolean areStepsEquallySpaced()
  {
    float f1 = 0.0F;
    int j = 0;
    long[] arrayOfLong = this.stepInterval;
    int m = arrayOfLong.length;
    int i = 0;
    while (i < m)
    {
      long l = arrayOfLong[i];
      float f2 = f1;
      int k = j;
      if (l > 0L)
      {
        k = j + 1;
        f2 = f1 + (float)l;
      }
      i += 1;
      f1 = f2;
      j = k;
    }
    f1 /= j;
    arrayOfLong = this.stepInterval;
    j = arrayOfLong.length;
    i = 0;
    while (i < j)
    {
      if (Math.abs((float)arrayOfLong[i] - f1) > 250.0F) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private void getPeak()
  {
    int k = (this.winPos + 10) % 20;
    int i = 0;
    if (i < 3)
    {
      this.peak[i] = k;
      int j = 0;
      for (;;)
      {
        if (j < 20)
        {
          if ((j != k) && (this.lastValues[j][i] >= this.lastValues[k][i])) {
            this.peak[i] = -1;
          }
        }
        else
        {
          i += 1;
          break;
        }
        j += 1;
      }
    }
  }
  
  private void getValley()
  {
    int k = (this.winPos + 10) % 20;
    int i = 0;
    if (i < 3)
    {
      this.valley[i] = k;
      int j = 0;
      for (;;)
      {
        if (j < 20)
        {
          if ((j != k) && (this.lastValues[j][i] <= this.lastValues[k][i])) {
            this.valley[i] = -1;
          }
        }
        else
        {
          i += 1;
          break;
        }
        j += 1;
      }
    }
  }
  
  private void setGpsAvailable(boolean paramBoolean)
  {
    if ((!this.gpsAvailable) && (paramBoolean))
    {
      this.gpsAvailable = true;
      GPSAvailable();
    }
    while ((!this.gpsAvailable) || (paramBoolean)) {
      return;
    }
    this.gpsAvailable = false;
    GPSLost();
  }
  
  @DesignerProperty(defaultValue="true", editorType="boolean")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public void CalibrateStrideLength(boolean paramBoolean)
  {
    if ((!this.gpsAvailable) && (paramBoolean))
    {
      CalibrationFailed();
      return;
    }
    if (paramBoolean) {
      this.useGps = true;
    }
    this.calibrateSteps = paramBoolean;
  }
  
  @SimpleProperty
  public boolean CalibrateStrideLength()
  {
    return this.calibrateSteps;
  }
  
  @SimpleEvent
  public void CalibrationFailed()
  {
    EventDispatcher.dispatchEvent(this, "CalibrationFailed", new Object[0]);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public float Distance()
  {
    return this.totalDistance;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public long ElapsedTime()
  {
    if (this.pedometerPaused) {
      return this.prevStopClockTime;
    }
    return this.prevStopClockTime + (System.currentTimeMillis() - this.startTime);
  }
  
  @SimpleEvent
  public void GPSAvailable()
  {
    EventDispatcher.dispatchEvent(this, "GPSAvailable", new Object[0]);
  }
  
  @SimpleEvent
  public void GPSLost()
  {
    EventDispatcher.dispatchEvent(this, "GPSLost", new Object[0]);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean Moving()
  {
    return this.statusMoving;
  }
  
  @SimpleFunction
  public void Pause()
  {
    if (!this.pedometerPaused)
    {
      this.pedometerPaused = true;
      this.statusMoving = false;
      this.sensorManager.unregisterListener(this);
      Log.d("Pedometer", "Unregistered listener on pause");
      this.prevStopClockTime += System.currentTimeMillis() - this.startTime;
    }
  }
  
  @SimpleFunction
  public void Reset()
  {
    this.numStepsWithFilter = 0;
    this.numStepsRaw = 0;
    this.totalDistance = 0.0F;
    this.calibrateSteps = false;
    this.prevStopClockTime = 0L;
    this.startTime = System.currentTimeMillis();
  }
  
  @SimpleFunction
  public void Resume()
  {
    Start();
  }
  
  @SimpleFunction(description="Saves the pedometer state to the phone")
  public void Save()
  {
    SharedPreferences.Editor localEditor = this.context.getSharedPreferences("PedometerPrefs", 0).edit();
    localEditor.putFloat("Pedometer.stridelength", this.strideLength);
    localEditor.putFloat("Pedometer.distance", this.totalDistance);
    localEditor.putInt("Pedometer.prevStepCount", this.numStepsRaw);
    if (this.pedometerPaused) {
      localEditor.putLong("Pedometer.clockTime", this.prevStopClockTime);
    }
    for (;;)
    {
      localEditor.putLong("Pedometer.closeTime", System.currentTimeMillis());
      localEditor.commit();
      Log.d("Pedometer", "Pedometer state saved.");
      return;
      localEditor.putLong("Pedometer.clockTime", this.prevStopClockTime + (System.currentTimeMillis() - this.startTime));
    }
  }
  
  @SimpleEvent(description="This event is run when a raw step is detected")
  public void SimpleStep(int paramInt, float paramFloat)
  {
    EventDispatcher.dispatchEvent(this, "SimpleStep", new Object[] { Integer.valueOf(paramInt), Float.valueOf(paramFloat) });
  }
  
  @SimpleFunction
  public void Start()
  {
    if (this.pedometerPaused)
    {
      this.pedometerPaused = false;
      this.sensorManager.registerListener(this, (Sensor)this.sensorManager.getSensorList(1).get(0), 0);
      this.startTime = System.currentTimeMillis();
    }
  }
  
  @SimpleEvent
  public void StartedMoving()
  {
    EventDispatcher.dispatchEvent(this, "StartedMoving", new Object[0]);
  }
  
  @SimpleFunction
  public void Stop()
  {
    Pause();
    this.locationManager.removeUpdates(this);
    this.useGps = false;
    this.calibrateSteps = false;
    setGpsAvailable(false);
  }
  
  @SimpleProperty
  public int StopDetectionTimeout()
  {
    return this.stopDetectionTimeout;
  }
  
  @DesignerProperty(defaultValue="2000", editorType="non_negative_integer")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public void StopDetectionTimeout(int paramInt)
  {
    this.stopDetectionTimeout = paramInt;
  }
  
  @SimpleEvent
  public void StoppedMoving()
  {
    EventDispatcher.dispatchEvent(this, "StoppedMoving", new Object[0]);
  }
  
  @SimpleProperty
  public float StrideLength()
  {
    return this.strideLength;
  }
  
  @DesignerProperty(defaultValue="0.73", editorType="non_negative_float")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public void StrideLength(float paramFloat)
  {
    CalibrateStrideLength(false);
    this.strideLength = paramFloat;
  }
  
  @DesignerProperty(defaultValue="true", editorType="boolean")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public void UseGPS(boolean paramBoolean)
  {
    if ((!paramBoolean) && (this.useGps)) {
      this.locationManager.removeUpdates(this);
    }
    for (;;)
    {
      this.useGps = paramBoolean;
      return;
      if ((paramBoolean) && (!this.useGps)) {
        this.locationManager.requestLocationUpdates("gps", 0L, 0.0F, this);
      }
    }
  }
  
  @SimpleProperty
  public boolean UseGPS()
  {
    return this.useGps;
  }
  
  @SimpleEvent(description="This event is run when a walking step is detected")
  public void WalkStep(int paramInt, float paramFloat)
  {
    EventDispatcher.dispatchEvent(this, "WalkStep", new Object[] { Integer.valueOf(paramInt), Float.valueOf(paramFloat) });
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt)
  {
    Log.d("Pedometer", "Accelerometer accuracy changed.");
  }
  
  public void onDelete()
  {
    this.sensorManager.unregisterListener(this);
    this.locationManager.removeUpdates(this);
  }
  
  public void onLocationChanged(Location paramLocation)
  {
    if ((!this.statusMoving) || (this.pedometerPaused) || (!this.useGps)) {
      return;
    }
    float f1 = 0.0F;
    this.currentLocation = paramLocation;
    if (this.currentLocation.getAccuracy() > 10.0F)
    {
      setGpsAvailable(false);
      return;
    }
    setGpsAvailable(true);
    float f2;
    if (this.prevLocation != null)
    {
      f2 = this.currentLocation.distanceTo(this.prevLocation);
      f1 = f2;
      if (f2 > 0.1D)
      {
        f1 = f2;
        if (f2 < 10.0F)
        {
          this.totalDistance += f2;
          this.prevLocation = this.currentLocation;
          f1 = f2;
        }
      }
    }
    while (this.calibrateSteps) {
      if (!this.firstGpsReading)
      {
        this.gpsDistance += f1;
        int i = this.numStepsRaw;
        int j = this.lastNumSteps;
        this.strideLength = (this.gpsDistance / (i - j));
        return;
        if (this.locationWhenGPSLost != null)
        {
          f2 = this.currentLocation.distanceTo(this.locationWhenGPSLost);
          this.totalDistance = (this.distWhenGPSLost + (this.totalDistance - this.distWhenGPSLost + f2) / 2.0F);
        }
        this.gpsStepTime = System.currentTimeMillis();
        this.prevLocation = this.currentLocation;
      }
      else
      {
        this.firstGpsReading = false;
        this.lastNumSteps = this.numStepsRaw;
        return;
      }
    }
    this.firstGpsReading = true;
    this.gpsDistance = 0.0F;
  }
  
  public void onProviderDisabled(String paramString)
  {
    this.distWhenGPSLost = this.totalDistance;
    this.locationWhenGPSLost = this.currentLocation;
    this.firstGpsReading = true;
    this.prevLocation = null;
    setGpsAvailable(false);
  }
  
  public void onProviderEnabled(String paramString)
  {
    setGpsAvailable(true);
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    if (paramSensorEvent.sensor.getType() != 1) {
      return;
    }
    paramSensorEvent = paramSensorEvent.values;
    if (this.startPeaking)
    {
      getPeak();
      getValley();
    }
    int j;
    if (this.prevDiff[0] > this.prevDiff[1])
    {
      i = 0;
      j = i;
      if (this.prevDiff[2] > this.prevDiff[i]) {
        j = 2;
      }
      i = 0;
      label72:
      if (i >= 3) {
        break label422;
      }
      if ((this.startPeaking) && (this.peak[i] >= 0))
      {
        if ((this.foundValley[i] == 0) || (this.lastValues[this.peak[i]][i] - this.lastValley[i] <= 4.0F)) {
          break label412;
        }
        if (j == i)
        {
          long l = System.currentTimeMillis();
          this.stepInterval[this.intervalPos] = (l - this.stepTimestamp);
          this.intervalPos = ((this.intervalPos + 1) % 2);
          this.stepTimestamp = l;
          if (!areStepsEquallySpaced()) {
            break label404;
          }
          if (this.foundNonStep)
          {
            this.numStepsWithFilter += 2;
            if (!this.gpsAvailable) {
              this.totalDistance += this.strideLength * 2.0F;
            }
            this.foundNonStep = false;
          }
          this.numStepsWithFilter += 1;
          WalkStep(this.numStepsWithFilter, this.totalDistance);
          if (!this.gpsAvailable) {
            this.totalDistance += this.strideLength;
          }
          label265:
          this.numStepsRaw += 1;
          SimpleStep(this.numStepsRaw, this.totalDistance);
          if (!this.statusMoving)
          {
            this.statusMoving = true;
            StartedMoving();
          }
        }
        this.foundValley[i] = false;
        this.prevDiff[i] = (this.lastValues[this.peak[i]][i] - this.lastValley[i]);
      }
    }
    for (;;)
    {
      if ((this.startPeaking) && (this.valley[i] >= 0))
      {
        this.foundValley[i] = true;
        this.lastValley[i] = this.lastValues[this.valley[i]][i];
      }
      this.lastValues[this.winPos][i] = paramSensorEvent[i];
      i += 1;
      break label72;
      i = 1;
      break;
      label404:
      this.foundNonStep = true;
      break label265;
      label412:
      this.prevDiff[i] = 0.0F;
    }
    label422:
    this.elapsedTimestamp = System.currentTimeMillis();
    if (this.elapsedTimestamp - this.stepTimestamp > this.stopDetectionTimeout)
    {
      if (this.statusMoving)
      {
        this.statusMoving = false;
        StoppedMoving();
      }
      this.stepTimestamp = this.elapsedTimestamp;
    }
    if (this.winPos - 1 < 0) {}
    for (int i = 19;; i = this.winPos - 1)
    {
      j = 0;
      while (j < 3)
      {
        if (this.lastValues[i][j] == this.lastValues[this.winPos][j])
        {
          paramSensorEvent = this.lastValues[this.winPos];
          paramSensorEvent[j] = ((float)(paramSensorEvent[j] + 0.001D));
        }
        j += 1;
      }
    }
    if ((this.winPos == 19) && (!this.startPeaking)) {
      this.startPeaking = true;
    }
    this.winPos = ((this.winPos + 1) % 20);
  }
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\Pedometer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */