package com.google.appinventor.components.runtime;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@DesignerComponent(category=ComponentCategory.SENSORS, description="Non-visible component providing location information, including longitude, latitude, altitude (if supported by the device), and address.  This can also perform \"geocoding\", converting a given address (not necessarily the current one) to a latitude (with the <code>LatitudeFromAddress</code> method) and a longitude (with the <code>LongitudeFromAddress</code> method).</p>\n<p>In order to function, the component must have its <code>Enabled</code> property set to True, and the device must have location sensing enabled through wireless networks or GPS satellites (if outdoors).</p>\nLocation information might not be immediately available when an app starts.  You'll have to wait a short time for a location provider to be found and used, or wait for the OnLocationChanged event", iconName="images/locationSensor.png", nonVisible=true, version=2)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.ACCESS_FINE_LOCATION,android.permission.ACCESS_COARSE_LOCATION,android.permission.ACCESS_MOCK_LOCATION,android.permission.ACCESS_LOCATION_EXTRA_COMMANDS")
public class LocationSensor
  extends AndroidNonvisibleComponent
  implements Component, OnStopListener, OnResumeListener, Deleteable
{
  public static final int UNKNOWN_VALUE = 0;
  private List<String> allProviders;
  private double altitude = 0.0D;
  private int distanceInterval;
  private boolean enabled = true;
  private Geocoder geocoder;
  private final Handler handler = new Handler();
  private boolean hasAltitude = false;
  private boolean hasLocationData = false;
  private Location lastLocation;
  private double latitude = 0.0D;
  private boolean listening = false;
  private final Criteria locationCriteria;
  private final LocationManager locationManager;
  private LocationProvider locationProvider;
  private double longitude = 0.0D;
  private MyLocationListener myLocationListener;
  private boolean providerLocked = false;
  private String providerName;
  private int timeInterval;
  
  public LocationSensor(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.form.registerForOnResume(this);
    this.form.registerForOnStop(this);
    this.timeInterval = 60000;
    this.distanceInterval = 5;
    paramComponentContainer = paramComponentContainer.$context();
    this.geocoder = new Geocoder(paramComponentContainer);
    this.locationManager = ((LocationManager)paramComponentContainer.getSystemService("location"));
    this.locationCriteria = new Criteria();
    this.myLocationListener = new MyLocationListener(null);
    this.allProviders = new ArrayList();
    Enabled(this.enabled);
  }
  
  private boolean empty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  private boolean startProvider(String paramString)
  {
    this.providerName = paramString;
    LocationProvider localLocationProvider = this.locationManager.getProvider(paramString);
    if (localLocationProvider == null)
    {
      Log.d("LocationSensor", "getProvider(" + paramString + ") returned null");
      return false;
    }
    stopListening();
    this.locationProvider = localLocationProvider;
    this.locationManager.requestLocationUpdates(paramString, this.timeInterval, this.distanceInterval, this.myLocationListener);
    this.listening = true;
    return true;
  }
  
  private void stopListening()
  {
    if (this.listening)
    {
      this.locationManager.removeUpdates(this.myLocationListener);
      this.locationProvider = null;
      this.listening = false;
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public double Accuracy()
  {
    if ((this.lastLocation != null) && (this.lastLocation.hasAccuracy())) {
      return this.lastLocation.getAccuracy();
    }
    if (this.locationProvider != null) {
      return this.locationProvider.getAccuracy();
    }
    return 0.0D;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public double Altitude()
  {
    return this.altitude;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public List<String> AvailableProviders()
  {
    return this.allProviders;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public String CurrentAddress()
  {
    if (((this.hasLocationData) && (this.latitude <= 90.0D) && (this.latitude >= -90.0D) && (this.longitude <= 180.0D)) || (this.longitude >= -180.0D))
    {
      try
      {
        Object localObject = this.geocoder.getFromLocation(this.latitude, this.longitude, 1);
        if ((localObject == null) || (((List)localObject).size() != 1)) {
          break label198;
        }
        localObject = (Address)((List)localObject).get(0);
        if (localObject == null) {
          break label198;
        }
        StringBuilder localStringBuilder = new StringBuilder();
        int i = 0;
        while (i <= ((Address)localObject).getMaxAddressLineIndex())
        {
          localStringBuilder.append(((Address)localObject).getAddressLine(i));
          localStringBuilder.append("\n");
          i += 1;
        }
        localObject = localStringBuilder.toString();
        return (String)localObject;
      }
      catch (Exception localException)
      {
        if ((localException instanceof IllegalArgumentException)) {
          break label169;
        }
      }
      if ((!(localException instanceof IOException)) && (!(localException instanceof IndexOutOfBoundsException))) {
        break label202;
      }
      label169:
      Log.e("LocationSensor", "Exception thrown by getting current address " + localException.getMessage());
    }
    for (;;)
    {
      label198:
      return "No address available";
      label202:
      Log.e("LocationSensor", "Unexpected exception thrown by getting current address " + localException.getMessage());
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Determines the minimum distance interval, in meters, that the sensor will try to use for sending out location updates. For example, if this is set to 5, then the sensor will fire a LocationChanged event only after 5 meters have been traversed. However, the sensor does not guarantee that an update will be received at exactly the distance interval. It may take more than 5 meters to fire an event, for instance.")
  public int DistanceInterval()
  {
    return this.distanceInterval;
  }
  
  @DesignerProperty(defaultValue="5", editorType="sensor_dist_interval")
  @SimpleProperty
  public void DistanceInterval(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 1000)) {}
    do
    {
      return;
      this.distanceInterval = paramInt;
    } while (!this.enabled);
    RefreshProvider();
  }
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty
  public void Enabled(boolean paramBoolean)
  {
    this.enabled = paramBoolean;
    if (!paramBoolean)
    {
      stopListening();
      return;
    }
    RefreshProvider();
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean Enabled()
  {
    return this.enabled;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean HasAccuracy()
  {
    return (Accuracy() != 0.0D) && (this.enabled);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean HasAltitude()
  {
    return (this.hasAltitude) && (this.enabled);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean HasLongitudeLatitude()
  {
    return (this.hasLocationData) && (this.enabled);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public double Latitude()
  {
    return this.latitude;
  }
  
  @SimpleFunction(description="Derives latitude of given address")
  public double LatitudeFromAddress(String paramString)
  {
    try
    {
      List localList = this.geocoder.getFromLocationName(paramString, 1);
      Log.i("LocationSensor", "latitude addressObjs size is " + localList.size() + " for " + paramString);
      if ((localList == null) || (localList.size() == 0)) {
        throw new IOException("");
      }
    }
    catch (IOException localIOException)
    {
      this.form.dispatchErrorOccurredEvent(this, "LatitudeFromAddress", 101, new Object[] { paramString });
      return 0.0D;
    }
    double d = ((Address)localIOException.get(0)).getLatitude();
    return d;
  }
  
  @SimpleEvent
  public void LocationChanged(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    if (this.enabled) {
      EventDispatcher.dispatchEvent(this, "LocationChanged", new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2), Double.valueOf(paramDouble3) });
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public double Longitude()
  {
    return this.longitude;
  }
  
  @SimpleFunction(description="Derives longitude of given address")
  public double LongitudeFromAddress(String paramString)
  {
    try
    {
      List localList = this.geocoder.getFromLocationName(paramString, 1);
      Log.i("LocationSensor", "longitude addressObjs size is " + localList.size() + " for " + paramString);
      if ((localList == null) || (localList.size() == 0)) {
        throw new IOException("");
      }
    }
    catch (IOException localIOException)
    {
      this.form.dispatchErrorOccurredEvent(this, "LongitudeFromAddress", 102, new Object[] { paramString });
      return 0.0D;
    }
    double d = ((Address)localIOException.get(0)).getLongitude();
    return d;
  }
  
  @SimpleProperty
  public void ProviderLocked(boolean paramBoolean)
  {
    this.providerLocked = paramBoolean;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean ProviderLocked()
  {
    return this.providerLocked;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public String ProviderName()
  {
    if (this.providerName == null) {
      return "NO PROVIDER";
    }
    return this.providerName;
  }
  
  @SimpleProperty
  public void ProviderName(String paramString)
  {
    this.providerName = paramString;
    if ((!empty(paramString)) && (startProvider(paramString))) {
      return;
    }
    RefreshProvider();
  }
  
  public void RefreshProvider()
  {
    stopListening();
    if ((this.providerLocked) && (!empty(this.providerName)))
    {
      this.listening = startProvider(this.providerName);
      return;
    }
    String str;
    do
    {
      Object localObject;
      do
      {
        this.allProviders = this.locationManager.getProviders(true);
        localObject = this.locationManager.getBestProvider(this.locationCriteria, true);
        if ((localObject != null) && (!((String)localObject).equals(this.allProviders.get(0)))) {
          this.allProviders.add(0, localObject);
        }
        localObject = this.allProviders.iterator();
      } while (!((Iterator)localObject).hasNext());
      str = (String)((Iterator)localObject).next();
      this.listening = startProvider(str);
      if (!this.listening) {
        break;
      }
    } while (this.providerLocked);
    this.providerName = str;
  }
  
  @SimpleEvent
  public void StatusChanged(String paramString1, String paramString2)
  {
    if (this.enabled) {
      EventDispatcher.dispatchEvent(this, "StatusChanged", new Object[] { paramString1, paramString2 });
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Determines the minimum time interval, in milliseconds, that the sensor will try to use for sending out location updates. However, location updates will only be received when the location of the phone actually changes, and use of the specified time interval is not guaranteed. For example, if 1000 is used as the time interval, location updates will never be fired sooner than 1000ms, but they may be fired anytime after.")
  public int TimeInterval()
  {
    return this.timeInterval;
  }
  
  @DesignerProperty(defaultValue="60000", editorType="sensor_time_interval")
  @SimpleProperty
  public void TimeInterval(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 1000000)) {}
    do
    {
      return;
      this.timeInterval = paramInt;
    } while (!this.enabled);
    RefreshProvider();
  }
  
  public void onDelete()
  {
    stopListening();
  }
  
  public void onResume()
  {
    if (this.enabled) {
      RefreshProvider();
    }
  }
  
  public void onStop()
  {
    stopListening();
  }
  
  private class MyLocationListener
    implements LocationListener
  {
    private MyLocationListener() {}
    
    public void onLocationChanged(Location paramLocation)
    {
      LocationSensor.access$002(LocationSensor.this, paramLocation);
      LocationSensor.access$102(LocationSensor.this, paramLocation.getLongitude());
      LocationSensor.access$202(LocationSensor.this, paramLocation.getLatitude());
      if (paramLocation.hasAltitude())
      {
        LocationSensor.access$302(LocationSensor.this, true);
        LocationSensor.access$402(LocationSensor.this, paramLocation.getAltitude());
      }
      LocationSensor.access$502(LocationSensor.this, true);
      LocationSensor.this.LocationChanged(LocationSensor.this.latitude, LocationSensor.this.longitude, LocationSensor.this.altitude);
    }
    
    public void onProviderDisabled(String paramString)
    {
      LocationSensor.this.StatusChanged(paramString, "Disabled");
      LocationSensor.this.stopListening();
      if (LocationSensor.this.enabled) {
        LocationSensor.this.RefreshProvider();
      }
    }
    
    public void onProviderEnabled(String paramString)
    {
      LocationSensor.this.StatusChanged(paramString, "Enabled");
      LocationSensor.this.RefreshProvider();
    }
    
    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
    {
      switch (paramInt)
      {
      }
      do
      {
        do
        {
          return;
          LocationSensor.this.StatusChanged(paramString, "TEMPORARILY_UNAVAILABLE");
          return;
          LocationSensor.this.StatusChanged(paramString, "OUT_OF_SERVICE");
        } while (!paramString.equals(LocationSensor.this.providerName));
        LocationSensor.this.stopListening();
        LocationSensor.this.RefreshProvider();
        return;
        LocationSensor.this.StatusChanged(paramString, "AVAILABLE");
      } while ((paramString.equals(LocationSensor.this.providerName)) || (LocationSensor.this.allProviders.contains(paramString)));
      LocationSensor.this.RefreshProvider();
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\LocationSensor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */