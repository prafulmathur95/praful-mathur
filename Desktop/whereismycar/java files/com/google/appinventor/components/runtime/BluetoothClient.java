package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.BluetoothReflection;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@DesignerComponent(category=ComponentCategory.CONNECTIVITY, description="Bluetooth client component", iconName="images/bluetooth.png", nonVisible=true, version=5)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.BLUETOOTH, android.permission.BLUETOOTH_ADMIN")
public final class BluetoothClient
  extends BluetoothConnectionBase
{
  private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
  private Set<Integer> acceptableDeviceClasses;
  private final List<Component> attachedComponents = new ArrayList();
  
  public BluetoothClient(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer, "BluetoothClient");
  }
  
  private void connect(Object paramObject, UUID paramUUID)
    throws IOException
  {
    if ((!this.secure) && (SdkLevel.getLevel() >= 10)) {}
    for (paramUUID = BluetoothReflection.createInsecureRfcommSocketToServiceRecord(paramObject, paramUUID);; paramUUID = BluetoothReflection.createRfcommSocketToServiceRecord(paramObject, paramUUID))
    {
      BluetoothReflection.connectToBluetoothSocket(paramUUID);
      setConnection(paramUUID);
      Log.i(this.logTag, "Connected to Bluetooth device " + BluetoothReflection.getBluetoothDeviceAddress(paramObject) + " " + BluetoothReflection.getBluetoothDeviceName(paramObject) + ".");
      return;
    }
  }
  
  private boolean connect(String paramString1, String paramString2, String paramString3)
  {
    Object localObject2 = BluetoothReflection.getBluetoothAdapter();
    if (localObject2 == null)
    {
      this.form.dispatchErrorOccurredEvent(this, paramString1, 501, new Object[0]);
      return false;
    }
    if (!BluetoothReflection.isBluetoothEnabled(localObject2))
    {
      this.form.dispatchErrorOccurredEvent(this, paramString1, 502, new Object[0]);
      return false;
    }
    int i = paramString2.indexOf(" ");
    Object localObject1 = paramString2;
    if (i != -1) {
      localObject1 = paramString2.substring(0, i);
    }
    if (!BluetoothReflection.checkBluetoothAddress(localObject2, (String)localObject1))
    {
      this.form.dispatchErrorOccurredEvent(this, paramString1, 503, new Object[0]);
      return false;
    }
    paramString2 = BluetoothReflection.getRemoteDevice(localObject2, (String)localObject1);
    if (!BluetoothReflection.isBonded(paramString2))
    {
      this.form.dispatchErrorOccurredEvent(this, paramString1, 504, new Object[0]);
      return false;
    }
    if (!isDeviceClassAcceptable(paramString2))
    {
      this.form.dispatchErrorOccurredEvent(this, paramString1, 505, new Object[0]);
      return false;
    }
    try
    {
      localObject1 = UUID.fromString(paramString3);
      Disconnect();
      return false;
    }
    catch (IllegalArgumentException paramString2)
    {
      try
      {
        connect(paramString2, (UUID)localObject1);
        return true;
      }
      catch (IOException paramString2)
      {
        Disconnect();
        this.form.dispatchErrorOccurredEvent(this, paramString1, 507, new Object[0]);
      }
      paramString2 = paramString2;
      this.form.dispatchErrorOccurredEvent(this, paramString1, 506, new Object[] { paramString3 });
      return false;
    }
  }
  
  private boolean isDeviceClassAcceptable(Object paramObject)
  {
    if (this.acceptableDeviceClasses == null) {
      return true;
    }
    paramObject = BluetoothReflection.getBluetoothClass(paramObject);
    if (paramObject == null) {
      return false;
    }
    int i = BluetoothReflection.getDeviceClass(paramObject);
    return this.acceptableDeviceClasses.contains(Integer.valueOf(i));
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The addresses and names of paired Bluetooth devices")
  public List<String> AddressesAndNames()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = BluetoothReflection.getBluetoothAdapter();
    if ((localObject1 != null) && (BluetoothReflection.isBluetoothEnabled(localObject1)))
    {
      localObject1 = BluetoothReflection.getBondedDevices(localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = ((Iterator)localObject1).next();
        if (isDeviceClassAcceptable(localObject2))
        {
          String str = BluetoothReflection.getBluetoothDeviceName(localObject2);
          localObject2 = BluetoothReflection.getBluetoothDeviceAddress(localObject2);
          localArrayList.add((String)localObject2 + " " + str);
        }
      }
    }
    return localArrayList;
  }
  
  @SimpleFunction(description="Connect to the Bluetooth device with the specified address and the Serial Port Profile (SPP). Returns true if the connection was successful.")
  public boolean Connect(String paramString)
  {
    return connect("Connect", paramString, "00001101-0000-1000-8000-00805F9B34FB");
  }
  
  @SimpleFunction(description="Connect to the Bluetooth device with the specified address and UUID. Returns true if the connection was successful.")
  public boolean ConnectWithUUID(String paramString1, String paramString2)
  {
    return connect("ConnectWithUUID", paramString1, paramString2);
  }
  
  @SimpleFunction(description="Checks whether the Bluetooth device with the specified address is paired.")
  public boolean IsDevicePaired(String paramString)
  {
    Object localObject = BluetoothReflection.getBluetoothAdapter();
    if (localObject == null)
    {
      this.form.dispatchErrorOccurredEvent(this, "IsDevicePaired", 501, new Object[0]);
      return false;
    }
    if (!BluetoothReflection.isBluetoothEnabled(localObject))
    {
      this.form.dispatchErrorOccurredEvent(this, "IsDevicePaired", 502, new Object[0]);
      return false;
    }
    int i = paramString.indexOf(" ");
    String str = paramString;
    if (i != -1) {
      str = paramString.substring(0, i);
    }
    if (!BluetoothReflection.checkBluetoothAddress(localObject, str))
    {
      this.form.dispatchErrorOccurredEvent(this, "IsDevicePaired", 503, new Object[0]);
      return false;
    }
    return BluetoothReflection.isBonded(BluetoothReflection.getRemoteDevice(localObject, str));
  }
  
  boolean attachComponent(Component paramComponent, Set<Integer> paramSet)
  {
    boolean bool2 = false;
    if (this.attachedComponents.isEmpty()) {
      if (paramSet == null)
      {
        paramSet = null;
        this.acceptableDeviceClasses = paramSet;
      }
    }
    label26:
    do
    {
      this.attachedComponents.add(paramComponent);
      boolean bool1 = true;
      do
      {
        do
        {
          return bool1;
          paramSet = new HashSet(paramSet);
          break;
          if (this.acceptableDeviceClasses == null)
          {
            if (paramSet == null) {
              break label26;
            }
            return false;
          }
          bool1 = bool2;
        } while (paramSet == null);
        bool1 = bool2;
      } while (!this.acceptableDeviceClasses.containsAll(paramSet));
    } while (paramSet.containsAll(this.acceptableDeviceClasses));
    return false;
  }
  
  void detachComponent(Component paramComponent)
  {
    this.attachedComponents.remove(paramComponent);
    if (this.attachedComponents.isEmpty()) {
      this.acceptableDeviceClasses = null;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\BluetoothClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */