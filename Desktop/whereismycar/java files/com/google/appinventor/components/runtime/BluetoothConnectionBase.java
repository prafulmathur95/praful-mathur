package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.BluetoothReflection;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SimpleObject
public abstract class BluetoothConnectionBase
  extends AndroidNonvisibleComponent
  implements Component, OnDestroyListener, Deleteable
{
  private final List<BluetoothConnectionListener> bluetoothConnectionListeners = new ArrayList();
  private ByteOrder byteOrder;
  private Object connectedBluetoothSocket;
  private byte delimiter;
  private String encoding;
  private InputStream inputStream;
  protected final String logTag;
  private OutputStream outputStream;
  protected boolean secure;
  
  protected BluetoothConnectionBase(ComponentContainer paramComponentContainer, String paramString)
  {
    this(paramComponentContainer.$form(), paramString);
    this.form.registerForOnDestroy(this);
  }
  
  private BluetoothConnectionBase(Form paramForm, String paramString)
  {
    super(paramForm);
    this.logTag = paramString;
    HighByteFirst(false);
    CharacterEncoding("UTF-8");
    DelimiterByte(0);
    Secure(true);
  }
  
  protected BluetoothConnectionBase(OutputStream paramOutputStream, InputStream paramInputStream)
  {
    this((Form)null, (String)null);
    this.connectedBluetoothSocket = "Not Null";
    this.outputStream = paramOutputStream;
    this.inputStream = paramInputStream;
  }
  
  private void fireAfterConnectEvent()
  {
    Iterator localIterator = this.bluetoothConnectionListeners.iterator();
    while (localIterator.hasNext()) {
      ((BluetoothConnectionListener)localIterator.next()).afterConnect(this);
    }
  }
  
  private void fireBeforeDisconnectEvent()
  {
    Iterator localIterator = this.bluetoothConnectionListeners.iterator();
    while (localIterator.hasNext()) {
      ((BluetoothConnectionListener)localIterator.next()).beforeDisconnect(this);
    }
  }
  
  private void prepareToDie()
  {
    if (this.connectedBluetoothSocket != null) {
      Disconnect();
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Whether Bluetooth is available on the device")
  public boolean Available()
  {
    return BluetoothReflection.getBluetoothAdapter() != null;
  }
  
  @SimpleEvent(description="The BluetoothError event is no longer used. Please use the Screen.ErrorOccurred event instead.", userVisible=false)
  public void BluetoothError(String paramString1, String paramString2) {}
  
  @SimpleFunction(description="Returns an estimate of the number of bytes that can be received without blocking")
  public int BytesAvailableToReceive()
  {
    if (!IsConnected())
    {
      bluetoothError("BytesAvailableToReceive", 515, new Object[0]);
      return 0;
    }
    try
    {
      int i = this.inputStream.available();
      return i;
    }
    catch (IOException localIOException)
    {
      bluetoothError("BytesAvailableToReceive", 517, new Object[] { localIOException.getMessage() });
    }
    return 0;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public String CharacterEncoding()
  {
    return this.encoding;
  }
  
  @DesignerProperty(defaultValue="UTF-8", editorType="string")
  @SimpleProperty
  public void CharacterEncoding(String paramString)
  {
    try
    {
      "check".getBytes(paramString);
      this.encoding = paramString;
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      bluetoothError("CharacterEncoding", 519, new Object[] { paramString });
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public int DelimiterByte()
  {
    return this.delimiter;
  }
  
  @DesignerProperty(defaultValue="0", editorType="non_negative_integer")
  @SimpleProperty
  public void DelimiterByte(int paramInt)
  {
    byte b = (byte)paramInt;
    int i = paramInt >> 8;
    if ((i != 0) && (i != -1))
    {
      bluetoothError("DelimiterByte", 511, new Object[] { Integer.valueOf(paramInt) });
      return;
    }
    this.delimiter = b;
  }
  
  @SimpleFunction(description="Disconnect from the connected Bluetooth device.")
  public final void Disconnect()
  {
    if (this.connectedBluetoothSocket != null) {
      fireBeforeDisconnectEvent();
    }
    try
    {
      BluetoothReflection.closeBluetoothSocket(this.connectedBluetoothSocket);
      Log.i(this.logTag, "Disconnected from Bluetooth device.");
      this.connectedBluetoothSocket = null;
      this.inputStream = null;
      this.outputStream = null;
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Log.w(this.logTag, "Error while disconnecting: " + localIOException.getMessage());
      }
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Whether Bluetooth is enabled")
  public boolean Enabled()
  {
    Object localObject = BluetoothReflection.getBluetoothAdapter();
    return (localObject != null) && (BluetoothReflection.isBluetoothEnabled(localObject));
  }
  
  @DesignerProperty(defaultValue="False", editorType="boolean")
  @SimpleProperty
  public void HighByteFirst(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (ByteOrder localByteOrder = ByteOrder.BIG_ENDIAN;; localByteOrder = ByteOrder.LITTLE_ENDIAN)
    {
      this.byteOrder = localByteOrder;
      return;
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public boolean HighByteFirst()
  {
    return this.byteOrder == ByteOrder.BIG_ENDIAN;
  }
  
  public final void Initialize() {}
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public final boolean IsConnected()
  {
    return this.connectedBluetoothSocket != null;
  }
  
  @SimpleFunction(description="Receive a signed 1-byte number from the connected Bluetooth device.")
  public int ReceiveSigned1ByteNumber()
  {
    byte[] arrayOfByte = read("ReceiveSigned1ByteNumber", 1);
    if (arrayOfByte.length != 1) {
      return 0;
    }
    return arrayOfByte[0];
  }
  
  @SimpleFunction(description="Receive a signed 2-byte number from the connected Bluetooth device.")
  public int ReceiveSigned2ByteNumber()
  {
    byte[] arrayOfByte = read("ReceiveSigned2ByteNumber", 2);
    if (arrayOfByte.length != 2) {
      return 0;
    }
    if (this.byteOrder == ByteOrder.BIG_ENDIAN)
    {
      int i = arrayOfByte[1];
      return arrayOfByte[0] << 8 | i & 0xFF;
    }
    return arrayOfByte[0] & 0xFF | arrayOfByte[1] << 8;
  }
  
  @SimpleFunction(description="Receive a signed 4-byte number from the connected Bluetooth device.")
  public long ReceiveSigned4ByteNumber()
  {
    byte[] arrayOfByte = read("ReceiveSigned4ByteNumber", 4);
    if (arrayOfByte.length != 4) {
      return 0L;
    }
    if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
      return arrayOfByte[3] & 0xFF | (arrayOfByte[2] & 0xFF) << 8 | (arrayOfByte[1] & 0xFF) << 16 | arrayOfByte[0] << 24;
    }
    return arrayOfByte[0] & 0xFF | (arrayOfByte[1] & 0xFF) << 8 | (arrayOfByte[2] & 0xFF) << 16 | arrayOfByte[3] << 24;
  }
  
  @SimpleFunction(description="Receive multiple signed byte values from the connected Bluetooth device. If numberOfBytes is less than 0, read until a delimiter byte value is received.")
  public List<Integer> ReceiveSignedBytes(int paramInt)
  {
    byte[] arrayOfByte = read("ReceiveSignedBytes", paramInt);
    ArrayList localArrayList = new ArrayList();
    paramInt = 0;
    while (paramInt < arrayOfByte.length)
    {
      localArrayList.add(Integer.valueOf(arrayOfByte[paramInt]));
      paramInt += 1;
    }
    return localArrayList;
  }
  
  @SimpleFunction(description="Receive text from the connected Bluetooth device. If numberOfBytes is less than 0, read until a delimiter byte value is received.")
  public String ReceiveText(int paramInt)
  {
    byte[] arrayOfByte = read("ReceiveText", paramInt);
    if (paramInt < 0) {}
    try
    {
      return new String(arrayOfByte, 0, arrayOfByte.length - 1, this.encoding);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      String str;
      Log.w(this.logTag, "UnsupportedEncodingException: " + localUnsupportedEncodingException.getMessage());
    }
    str = new String(arrayOfByte, this.encoding);
    return str;
    return new String(arrayOfByte);
  }
  
  @SimpleFunction(description="Receive an unsigned 1-byte number from the connected Bluetooth device.")
  public int ReceiveUnsigned1ByteNumber()
  {
    byte[] arrayOfByte = read("ReceiveUnsigned1ByteNumber", 1);
    if (arrayOfByte.length != 1) {
      return 0;
    }
    return arrayOfByte[0] & 0xFF;
  }
  
  @SimpleFunction(description="Receive a unsigned 2-byte number from the connected Bluetooth device.")
  public int ReceiveUnsigned2ByteNumber()
  {
    byte[] arrayOfByte = read("ReceiveUnsigned2ByteNumber", 2);
    if (arrayOfByte.length != 2) {
      return 0;
    }
    if (this.byteOrder == ByteOrder.BIG_ENDIAN)
    {
      int i = arrayOfByte[1];
      return (arrayOfByte[0] & 0xFF) << 8 | i & 0xFF;
    }
    return arrayOfByte[0] & 0xFF | (arrayOfByte[1] & 0xFF) << 8;
  }
  
  @SimpleFunction(description="Receive a unsigned 4-byte number from the connected Bluetooth device.")
  public long ReceiveUnsigned4ByteNumber()
  {
    byte[] arrayOfByte = read("ReceiveUnsigned4ByteNumber", 4);
    if (arrayOfByte.length != 4) {
      return 0L;
    }
    if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
      return arrayOfByte[3] & 0xFF | (arrayOfByte[2] & 0xFF) << 8 | (arrayOfByte[1] & 0xFF) << 16 | (arrayOfByte[0] & 0xFF) << 24;
    }
    return arrayOfByte[0] & 0xFF | (arrayOfByte[1] & 0xFF) << 8 | (arrayOfByte[2] & 0xFF) << 16 | (arrayOfByte[3] & 0xFF) << 24;
  }
  
  @SimpleFunction(description="Receive multiple unsigned byte values from the connected Bluetooth device. If numberOfBytes is less than 0, read until a delimiter byte value is received.")
  public List<Integer> ReceiveUnsignedBytes(int paramInt)
  {
    byte[] arrayOfByte = read("ReceiveUnsignedBytes", paramInt);
    ArrayList localArrayList = new ArrayList();
    paramInt = 0;
    while (paramInt < arrayOfByte.length)
    {
      localArrayList.add(Integer.valueOf(arrayOfByte[paramInt] & 0xFF));
      paramInt += 1;
    }
    return localArrayList;
  }
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty
  public void Secure(boolean paramBoolean)
  {
    this.secure = paramBoolean;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Whether to invoke SSP (Simple Secure Pairing), which is supported on devices with Bluetooth v2.1 or higher. When working with embedded Bluetooth devices, this property may need to be set to False. For Android 2.0-2.2, this property setting will be ignored.")
  public boolean Secure()
  {
    return this.secure;
  }
  
  @SimpleFunction(description="Send a 1-byte number to the connected Bluetooth device.")
  public void Send1ByteNumber(String paramString)
  {
    byte b;
    try
    {
      int i = Integer.decode(paramString).intValue();
      b = (byte)i;
      i >>= 8;
      if ((i != 0) && (i != -1))
      {
        bluetoothError("Send1ByteNumber", 511, new Object[] { paramString });
        return;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      bluetoothError("Send1ByteNumber", 510, new Object[] { paramString });
      return;
    }
    write("Send1ByteNumber", b);
  }
  
  @SimpleFunction(description="Send a 2-byte number to the connected Bluetooth device.")
  public void Send2ByteNumber(String paramString)
  {
    for (;;)
    {
      int i;
      try
      {
        i = Integer.decode(paramString).intValue();
        byte[] arrayOfByte = new byte[2];
        if (this.byteOrder == ByteOrder.BIG_ENDIAN)
        {
          arrayOfByte[1] = ((byte)(i & 0xFF));
          i >>= 8;
          arrayOfByte[0] = ((byte)(i & 0xFF));
          i >>= 8;
          if ((i == 0) || (i == -1)) {
            break;
          }
          bluetoothError("Send2ByteNumber", 512, new Object[] { paramString, Integer.valueOf(2) });
          return;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        bluetoothError("Send2ByteNumber", 510, new Object[] { paramString });
        return;
      }
      localNumberFormatException[0] = ((byte)(i & 0xFF));
      i >>= 8;
      localNumberFormatException[1] = ((byte)(i & 0xFF));
    }
    write("Send2ByteNumber", localNumberFormatException);
  }
  
  @SimpleFunction(description="Send a 4-byte number to the connected Bluetooth device.")
  public void Send4ByteNumber(String paramString)
  {
    for (;;)
    {
      long l;
      try
      {
        l = Long.decode(paramString).longValue();
        byte[] arrayOfByte = new byte[4];
        if (this.byteOrder == ByteOrder.BIG_ENDIAN)
        {
          arrayOfByte[3] = ((byte)(int)(l & 0xFF));
          l >>= 8;
          arrayOfByte[2] = ((byte)(int)(l & 0xFF));
          l >>= 8;
          arrayOfByte[1] = ((byte)(int)(l & 0xFF));
          l >>= 8;
          arrayOfByte[0] = ((byte)(int)(l & 0xFF));
          l >>= 8;
          if ((l == 0L) || (l == -1L)) {
            break;
          }
          bluetoothError("Send4ByteNumber", 512, new Object[] { paramString, Integer.valueOf(4) });
          return;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        bluetoothError("Send4ByteNumber", 510, new Object[] { paramString });
        return;
      }
      localNumberFormatException[0] = ((byte)(int)(l & 0xFF));
      l >>= 8;
      localNumberFormatException[1] = ((byte)(int)(l & 0xFF));
      l >>= 8;
      localNumberFormatException[2] = ((byte)(int)(l & 0xFF));
      l >>= 8;
      localNumberFormatException[3] = ((byte)(int)(l & 0xFF));
    }
    write("Send4ByteNumber", localNumberFormatException);
  }
  
  @SimpleFunction(description="Send a list of byte values to the connected Bluetooth device.")
  public void SendBytes(YailList paramYailList)
  {
    paramYailList = paramYailList.toArray();
    byte[] arrayOfByte = new byte[paramYailList.length];
    int i = 0;
    while (i < paramYailList.length)
    {
      String str = paramYailList[i].toString();
      try
      {
        int j = Integer.decode(str).intValue();
        arrayOfByte[i] = ((byte)(j & 0xFF));
        j >>= 8;
        if ((j != 0) && (j != -1))
        {
          bluetoothError("SendBytes", 514, new Object[] { Integer.valueOf(i + 1) });
          return;
        }
      }
      catch (NumberFormatException paramYailList)
      {
        bluetoothError("SendBytes", 513, new Object[] { Integer.valueOf(i + 1) });
        return;
      }
      i += 1;
    }
    write("SendBytes", arrayOfByte);
  }
  
  @SimpleFunction(description="Send text to the connected Bluetooth device.")
  public void SendText(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes(this.encoding);
      paramString = arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        Log.w(this.logTag, "UnsupportedEncodingException: " + localUnsupportedEncodingException.getMessage());
        paramString = paramString.getBytes();
      }
    }
    write("SendText", paramString);
  }
  
  void addBluetoothConnectionListener(BluetoothConnectionListener paramBluetoothConnectionListener)
  {
    this.bluetoothConnectionListeners.add(paramBluetoothConnectionListener);
  }
  
  protected void bluetoothError(String paramString, int paramInt, Object... paramVarArgs)
  {
    this.form.dispatchErrorOccurredEvent(this, paramString, paramInt, paramVarArgs);
  }
  
  public void onDelete()
  {
    prepareToDie();
  }
  
  public void onDestroy()
  {
    prepareToDie();
  }
  
  protected final byte[] read(String paramString, int paramInt)
  {
    if (!IsConnected())
    {
      bluetoothError(paramString, 515, new Object[0]);
      return new byte[0];
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int i;
    if (paramInt >= 0)
    {
      byte[] arrayOfByte = new byte[paramInt];
      i = 0;
      if (i < paramInt) {}
      try
      {
        j = this.inputStream.read(arrayOfByte, i, arrayOfByte.length - i);
        if (j != -1) {
          break label100;
        }
        bluetoothError(paramString, 518, new Object[0]);
      }
      catch (IOException localIOException2)
      {
        for (;;)
        {
          int j;
          bluetoothError(paramString, 517, new Object[] { localIOException2.getMessage() });
        }
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    for (;;)
    {
      return localByteArrayOutputStream.toByteArray();
      label100:
      i += j;
      break;
      label188:
      do
      {
        try
        {
          paramInt = this.inputStream.read();
          if (paramInt != -1) {
            break label188;
          }
          bluetoothError(paramString, 518, new Object[0]);
        }
        catch (IOException localIOException1)
        {
          bluetoothError(paramString, 517, new Object[] { localIOException1.getMessage() });
        }
        break;
        localByteArrayOutputStream.write(paramInt);
        i = this.delimiter;
      } while (paramInt != i);
    }
  }
  
  void removeBluetoothConnectionListener(BluetoothConnectionListener paramBluetoothConnectionListener)
  {
    this.bluetoothConnectionListeners.remove(paramBluetoothConnectionListener);
  }
  
  protected final void setConnection(Object paramObject)
    throws IOException
  {
    this.connectedBluetoothSocket = paramObject;
    this.inputStream = new BufferedInputStream(BluetoothReflection.getInputStream(this.connectedBluetoothSocket));
    this.outputStream = new BufferedOutputStream(BluetoothReflection.getOutputStream(this.connectedBluetoothSocket));
    fireAfterConnectEvent();
  }
  
  protected void write(String paramString, byte paramByte)
  {
    if (!IsConnected())
    {
      bluetoothError(paramString, 515, new Object[0]);
      return;
    }
    try
    {
      this.outputStream.write(paramByte);
      this.outputStream.flush();
      return;
    }
    catch (IOException localIOException)
    {
      bluetoothError(paramString, 516, new Object[] { localIOException.getMessage() });
    }
  }
  
  protected void write(String paramString, byte[] paramArrayOfByte)
  {
    if (!IsConnected())
    {
      bluetoothError(paramString, 515, new Object[0]);
      return;
    }
    try
    {
      this.outputStream.write(paramArrayOfByte);
      this.outputStream.flush();
      return;
    }
    catch (IOException paramArrayOfByte)
    {
      bluetoothError(paramString, 516, new Object[] { paramArrayOfByte.getMessage() });
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\BluetoothConnectionBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */