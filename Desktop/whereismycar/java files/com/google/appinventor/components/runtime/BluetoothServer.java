package com.google.appinventor.components.runtime;

import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.BluetoothReflection;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@DesignerComponent(category=ComponentCategory.CONNECTIVITY, description="Bluetooth server component", iconName="images/bluetooth.png", nonVisible=true, version=5)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.BLUETOOTH, android.permission.BLUETOOTH_ADMIN")
public final class BluetoothServer
  extends BluetoothConnectionBase
{
  private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
  private final Handler androidUIHandler = new Handler();
  private final AtomicReference<Object> arBluetoothServerSocket = new AtomicReference();
  
  public BluetoothServer(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer, "BluetoothServer");
  }
  
  /* Error */
  private void accept(final String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: invokestatic 67	com/google/appinventor/components/runtime/util/BluetoothReflection:getBluetoothAdapter	()Ljava/lang/Object;
    //   3: astore 4
    //   5: aload 4
    //   7: ifnonnull +20 -> 27
    //   10: aload_0
    //   11: getfield 71	com/google/appinventor/components/runtime/BluetoothServer:form	Lcom/google/appinventor/components/runtime/Form;
    //   14: aload_0
    //   15: aload_1
    //   16: sipush 501
    //   19: iconst_0
    //   20: anewarray 73	java/lang/Object
    //   23: invokevirtual 79	com/google/appinventor/components/runtime/Form:dispatchErrorOccurredEvent	(Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   26: return
    //   27: aload 4
    //   29: invokestatic 83	com/google/appinventor/components/runtime/util/BluetoothReflection:isBluetoothEnabled	(Ljava/lang/Object;)Z
    //   32: ifne +20 -> 52
    //   35: aload_0
    //   36: getfield 71	com/google/appinventor/components/runtime/BluetoothServer:form	Lcom/google/appinventor/components/runtime/Form;
    //   39: aload_0
    //   40: aload_1
    //   41: sipush 502
    //   44: iconst_0
    //   45: anewarray 73	java/lang/Object
    //   48: invokevirtual 79	com/google/appinventor/components/runtime/Form:dispatchErrorOccurredEvent	(Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   51: return
    //   52: aload_3
    //   53: invokestatic 89	java/util/UUID:fromString	(Ljava/lang/String;)Ljava/util/UUID;
    //   56: astore 5
    //   58: aload_0
    //   59: getfield 93	com/google/appinventor/components/runtime/BluetoothServer:secure	Z
    //   62: ifne +63 -> 125
    //   65: invokestatic 99	com/google/appinventor/components/runtime/util/SdkLevel:getLevel	()I
    //   68: bipush 10
    //   70: if_icmplt +55 -> 125
    //   73: aload 4
    //   75: aload_2
    //   76: aload 5
    //   78: invokestatic 103	com/google/appinventor/components/runtime/util/BluetoothReflection:listenUsingInsecureRfcommWithServiceRecord	(Ljava/lang/Object;Ljava/lang/String;Ljava/util/UUID;)Ljava/lang/Object;
    //   81: astore_2
    //   82: aload_0
    //   83: getfield 54	com/google/appinventor/components/runtime/BluetoothServer:arBluetoothServerSocket	Ljava/util/concurrent/atomic/AtomicReference;
    //   86: aload_2
    //   87: invokevirtual 107	java/util/concurrent/atomic/AtomicReference:set	(Ljava/lang/Object;)V
    //   90: new 6	com/google/appinventor/components/runtime/BluetoothServer$1
    //   93: dup
    //   94: aload_0
    //   95: aload_1
    //   96: invokespecial 110	com/google/appinventor/components/runtime/BluetoothServer$1:<init>	(Lcom/google/appinventor/components/runtime/BluetoothServer;Ljava/lang/String;)V
    //   99: invokestatic 116	com/google/appinventor/components/runtime/util/AsynchUtil:runAsynchronously	(Ljava/lang/Runnable;)V
    //   102: return
    //   103: astore_2
    //   104: aload_0
    //   105: getfield 71	com/google/appinventor/components/runtime/BluetoothServer:form	Lcom/google/appinventor/components/runtime/Form;
    //   108: aload_0
    //   109: aload_1
    //   110: sipush 506
    //   113: iconst_1
    //   114: anewarray 73	java/lang/Object
    //   117: dup
    //   118: iconst_0
    //   119: aload_3
    //   120: aastore
    //   121: invokevirtual 79	com/google/appinventor/components/runtime/Form:dispatchErrorOccurredEvent	(Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   124: return
    //   125: aload 4
    //   127: aload_2
    //   128: aload 5
    //   130: invokestatic 119	com/google/appinventor/components/runtime/util/BluetoothReflection:listenUsingRfcommWithServiceRecord	(Ljava/lang/Object;Ljava/lang/String;Ljava/util/UUID;)Ljava/lang/Object;
    //   133: astore_2
    //   134: goto -52 -> 82
    //   137: astore_2
    //   138: aload_0
    //   139: getfield 71	com/google/appinventor/components/runtime/BluetoothServer:form	Lcom/google/appinventor/components/runtime/Form;
    //   142: aload_0
    //   143: aload_1
    //   144: sipush 508
    //   147: iconst_0
    //   148: anewarray 73	java/lang/Object
    //   151: invokevirtual 79	com/google/appinventor/components/runtime/Form:dispatchErrorOccurredEvent	(Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   154: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	155	0	this	BluetoothServer
    //   0	155	1	paramString1	String
    //   0	155	2	paramString2	String
    //   0	155	3	paramString3	String
    //   3	123	4	localObject	Object
    //   56	73	5	localUUID	java.util.UUID
    // Exception table:
    //   from	to	target	type
    //   52	58	103	java/lang/IllegalArgumentException
    //   58	82	137	java/io/IOException
    //   82	90	137	java/io/IOException
    //   125	134	137	java/io/IOException
  }
  
  @SimpleFunction(description="Accept an incoming connection with the Serial Port Profile (SPP).")
  public void AcceptConnection(String paramString)
  {
    accept("AcceptConnection", paramString, "00001101-0000-1000-8000-00805F9B34FB");
  }
  
  @SimpleFunction(description="Accept an incoming connection with a specific UUID.")
  public void AcceptConnectionWithUUID(String paramString1, String paramString2)
  {
    accept("AcceptConnectionWithUUID", paramString1, paramString2);
  }
  
  @SimpleEvent(description="Indicates that a bluetooth connection has been accepted.")
  public void ConnectionAccepted()
  {
    Log.i(this.logTag, "Successfullly accepted bluetooth connection.");
    EventDispatcher.dispatchEvent(this, "ConnectionAccepted", new Object[0]);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public final boolean IsAccepting()
  {
    return this.arBluetoothServerSocket.get() != null;
  }
  
  @SimpleFunction(description="Stop accepting an incoming connection.")
  public void StopAccepting()
  {
    Object localObject = this.arBluetoothServerSocket.getAndSet(null);
    if (localObject != null) {}
    try
    {
      BluetoothReflection.closeBluetoothServerSocket(localObject);
      return;
    }
    catch (IOException localIOException)
    {
      Log.w(this.logTag, "Error while closing bluetooth server socket: " + localIOException.getMessage());
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\BluetoothServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */