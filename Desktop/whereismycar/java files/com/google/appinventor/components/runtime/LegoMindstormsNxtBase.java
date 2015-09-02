package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SimpleObject
public class LegoMindstormsNxtBase
  extends AndroidNonvisibleComponent
  implements BluetoothConnectionListener, Component, Deleteable
{
  private static final Map<Integer, String> ERROR_MESSAGES = new HashMap();
  private static final int TOY_ROBOT = 2052;
  protected BluetoothClient bluetooth;
  protected final String logTag;
  
  static
  {
    ERROR_MESSAGES.put(Integer.valueOf(32), "Pending communication transaction in progress");
    ERROR_MESSAGES.put(Integer.valueOf(64), "Specified mailbox queue is empty");
    ERROR_MESSAGES.put(Integer.valueOf(129), "No more handles");
    ERROR_MESSAGES.put(Integer.valueOf(130), "No space");
    ERROR_MESSAGES.put(Integer.valueOf(131), "No more files");
    ERROR_MESSAGES.put(Integer.valueOf(132), "End of file expected");
    ERROR_MESSAGES.put(Integer.valueOf(133), "End of file");
    ERROR_MESSAGES.put(Integer.valueOf(134), "Not a linear file");
    ERROR_MESSAGES.put(Integer.valueOf(135), "File not found");
    ERROR_MESSAGES.put(Integer.valueOf(136), "Handle already closed");
    ERROR_MESSAGES.put(Integer.valueOf(137), "No linear space");
    ERROR_MESSAGES.put(Integer.valueOf(138), "Undefined error");
    ERROR_MESSAGES.put(Integer.valueOf(139), "File is busy");
    ERROR_MESSAGES.put(Integer.valueOf(140), "No write buffers");
    ERROR_MESSAGES.put(Integer.valueOf(141), "Append not possible");
    ERROR_MESSAGES.put(Integer.valueOf(142), "File is full");
    ERROR_MESSAGES.put(Integer.valueOf(143), "File exists");
    ERROR_MESSAGES.put(Integer.valueOf(144), "Module not found");
    ERROR_MESSAGES.put(Integer.valueOf(145), "Out of boundary");
    ERROR_MESSAGES.put(Integer.valueOf(146), "Illegal file name");
    ERROR_MESSAGES.put(Integer.valueOf(147), "Illegal handle");
    ERROR_MESSAGES.put(Integer.valueOf(189), "Request failed (i.e. specified file not found)");
    ERROR_MESSAGES.put(Integer.valueOf(190), "Unknown command opcode");
    ERROR_MESSAGES.put(Integer.valueOf(191), "Insane packet");
    ERROR_MESSAGES.put(Integer.valueOf(192), "Data contains out-of-range values");
    ERROR_MESSAGES.put(Integer.valueOf(221), "Communication bus error");
    ERROR_MESSAGES.put(Integer.valueOf(222), "No free memory in communication buffer");
    ERROR_MESSAGES.put(Integer.valueOf(223), "Specified channel/connection is not valid");
    ERROR_MESSAGES.put(Integer.valueOf(224), "Specified channel/connection not configured or busy");
    ERROR_MESSAGES.put(Integer.valueOf(236), "No active program");
    ERROR_MESSAGES.put(Integer.valueOf(237), "Illegal size specified");
    ERROR_MESSAGES.put(Integer.valueOf(238), "Illegal mailbox queue ID specified");
    ERROR_MESSAGES.put(Integer.valueOf(239), "Attempted to access invalid field of a structure");
    ERROR_MESSAGES.put(Integer.valueOf(240), "Bad input or output specified");
    ERROR_MESSAGES.put(Integer.valueOf(251), "Insufficient memory available");
    ERROR_MESSAGES.put(Integer.valueOf(255), "Bad arguments");
  }
  
  protected LegoMindstormsNxtBase()
  {
    super(null);
    this.logTag = null;
  }
  
  protected LegoMindstormsNxtBase(ComponentContainer paramComponentContainer, String paramString)
  {
    super(paramComponentContainer.$form());
    this.logTag = paramString;
  }
  
  private void handleError(String paramString, int paramInt)
  {
    if (paramInt < 0) {
      return;
    }
    String str = (String)ERROR_MESSAGES.get(Integer.valueOf(paramInt));
    if (str != null)
    {
      this.form.dispatchErrorOccurredEvent(this, paramString, 404, new Object[] { str });
      return;
    }
    this.form.dispatchErrorOccurredEvent(this, paramString, 404, new Object[] { "Error code 0x" + Integer.toHexString(paramInt & 0xFF) });
  }
  
  private byte[] receiveReturnPackage(String paramString)
  {
    byte[] arrayOfByte = this.bluetooth.read(paramString, 2);
    if (arrayOfByte.length == 2)
    {
      int i = getUWORDValueFromBytes(arrayOfByte, 0);
      arrayOfByte = this.bluetooth.read(paramString, i);
      if (arrayOfByte.length >= 3) {
        return arrayOfByte;
      }
    }
    this.form.dispatchErrorOccurredEvent(this, paramString, 403, new Object[0]);
    return new byte[0];
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The BluetoothClient component that should be used for communication.", userVisible=false)
  public BluetoothClient BluetoothClient()
  {
    return this.bluetooth;
  }
  
  @DesignerProperty(defaultValue="", editorType="BluetoothClient")
  @SimpleProperty(userVisible=false)
  public void BluetoothClient(BluetoothClient paramBluetoothClient)
  {
    if (this.bluetooth != null)
    {
      this.bluetooth.removeBluetoothConnectionListener(this);
      this.bluetooth.detachComponent(this);
      this.bluetooth = null;
    }
    if (paramBluetoothClient != null)
    {
      this.bluetooth = paramBluetoothClient;
      this.bluetooth.attachComponent(this, Collections.singleton(Integer.valueOf(2052)));
      this.bluetooth.addBluetoothConnectionListener(this);
      if (this.bluetooth.IsConnected()) {
        afterConnect(this.bluetooth);
      }
    }
  }
  
  public final void Initialize() {}
  
  public void afterConnect(BluetoothConnectionBase paramBluetoothConnectionBase) {}
  
  public void beforeDisconnect(BluetoothConnectionBase paramBluetoothConnectionBase) {}
  
  protected final boolean checkBluetooth(String paramString)
  {
    if (this.bluetooth == null)
    {
      this.form.dispatchErrorOccurredEvent(this, paramString, 401, new Object[0]);
      return false;
    }
    if (!this.bluetooth.IsConnected())
    {
      this.form.dispatchErrorOccurredEvent(this, paramString, 402, new Object[0]);
      return false;
    }
    return true;
  }
  
  protected final int convertMotorPortLetterToNumber(char paramChar)
  {
    if ((paramChar == 'A') || (paramChar == 'a')) {
      return 0;
    }
    if ((paramChar == 'B') || (paramChar == 'b')) {
      return 1;
    }
    if ((paramChar == 'C') || (paramChar == 'c')) {
      return 2;
    }
    throw new IllegalArgumentException("Illegal motor port letter " + paramChar);
  }
  
  protected final int convertMotorPortLetterToNumber(String paramString)
  {
    if (paramString.length() == 1) {
      return convertMotorPortLetterToNumber(paramString.charAt(0));
    }
    throw new IllegalArgumentException("Illegal motor port letter " + paramString);
  }
  
  protected final int convertSensorPortLetterToNumber(char paramChar)
  {
    if (paramChar == '1') {
      return 0;
    }
    if (paramChar == '2') {
      return 1;
    }
    if (paramChar == '3') {
      return 2;
    }
    if (paramChar == '4') {
      return 3;
    }
    throw new IllegalArgumentException("Illegal sensor port letter " + paramChar);
  }
  
  protected final int convertSensorPortLetterToNumber(String paramString)
  {
    if (paramString.length() == 1) {
      return convertSensorPortLetterToNumber(paramString.charAt(0));
    }
    throw new IllegalArgumentException("Illegal sensor port letter " + paramString);
  }
  
  protected final void copyBooleanValueToBytes(boolean paramBoolean, byte[] paramArrayOfByte, int paramInt)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      paramArrayOfByte[paramInt] = i;
      return;
    }
  }
  
  protected final void copySBYTEValueToBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = ((byte)paramInt1);
  }
  
  protected final void copySLONGValueToBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 & 0xFF));
    paramInt1 >>= 8;
    paramArrayOfByte[(paramInt2 + 1)] = ((byte)(paramInt1 & 0xFF));
    paramInt1 >>= 8;
    paramArrayOfByte[(paramInt2 + 2)] = ((byte)(paramInt1 & 0xFF));
    paramArrayOfByte[(paramInt2 + 3)] = ((byte)(paramInt1 >> 8 & 0xFF));
  }
  
  protected final void copySWORDValueToBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 & 0xFF));
    paramArrayOfByte[(paramInt2 + 1)] = ((byte)(paramInt1 >> 8 & 0xFF));
  }
  
  protected final void copyStringValueToBytes(String paramString, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    String str = paramString;
    if (paramString.length() > paramInt2) {
      str = paramString.substring(0, paramInt2);
    }
    try
    {
      paramString = str.getBytes("ISO-8859-1");
      System.arraycopy(paramString, 0, paramArrayOfByte, paramInt1, Math.min(paramInt2, paramString.length));
      return;
    }
    catch (UnsupportedEncodingException paramString)
    {
      for (;;)
      {
        Log.w(this.logTag, "UnsupportedEncodingException: " + paramString.getMessage());
        paramString = str.getBytes();
      }
    }
  }
  
  protected final void copyUBYTEValueToBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = ((byte)paramInt1);
  }
  
  protected final void copyULONGValueToBytes(long paramLong, byte[] paramArrayOfByte, int paramInt)
  {
    paramArrayOfByte[paramInt] = ((byte)(int)(paramLong & 0xFF));
    paramLong >>= 8;
    paramArrayOfByte[(paramInt + 1)] = ((byte)(int)(paramLong & 0xFF));
    paramLong >>= 8;
    paramArrayOfByte[(paramInt + 2)] = ((byte)(int)(paramLong & 0xFF));
    paramArrayOfByte[(paramInt + 3)] = ((byte)(int)(paramLong >> 8 & 0xFF));
  }
  
  protected final void copyUWORDValueToBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 & 0xFF));
    paramArrayOfByte[(paramInt2 + 1)] = ((byte)(paramInt1 >> 8 & 0xFF));
  }
  
  protected final boolean evaluateStatus(String paramString, byte[] paramArrayOfByte, byte paramByte)
  {
    int i = getStatus(paramString, paramArrayOfByte, paramByte);
    if (i == 0) {
      return true;
    }
    handleError(paramString, i);
    return false;
  }
  
  protected final boolean getBooleanValueFromBytes(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt] != 0;
  }
  
  protected final byte[] getInputValues(String paramString, int paramInt)
  {
    byte[] arrayOfByte1 = new byte[3];
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = 7;
    copyUBYTEValueToBytes(paramInt, arrayOfByte1, 2);
    byte[] arrayOfByte2 = sendCommandAndReceiveReturnPackage(paramString, arrayOfByte1);
    if (evaluateStatus(paramString, arrayOfByte2, arrayOfByte1[1]))
    {
      if (arrayOfByte2.length == 16) {
        return arrayOfByte2;
      }
      Log.w(this.logTag, paramString + ": unexpected return package length " + arrayOfByte2.length + " (expected 16)");
    }
    return null;
  }
  
  protected final int getSBYTEValueFromBytes(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt];
  }
  
  protected final int getSLONGValueFromBytes(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt] & 0xFF | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 16 | paramArrayOfByte[(paramInt + 3)] << 24;
  }
  
  protected final int getSWORDValueFromBytes(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt] & 0xFF | paramArrayOfByte[(paramInt + 1)] << 8;
  }
  
  protected final int getStatus(String paramString, byte[] paramArrayOfByte, byte paramByte)
  {
    if (paramArrayOfByte.length >= 3)
    {
      if (paramArrayOfByte[0] != 2) {
        Log.w(this.logTag, paramString + ": unexpected return package byte 0: 0x" + Integer.toHexString(paramArrayOfByte[0] & 0xFF) + " (expected 0x02)");
      }
      if (paramArrayOfByte[1] != paramByte) {
        Log.w(this.logTag, paramString + ": unexpected return package byte 1: 0x" + Integer.toHexString(paramArrayOfByte[1] & 0xFF) + " (expected 0x" + Integer.toHexString(paramByte & 0xFF) + ")");
      }
      return getUBYTEValueFromBytes(paramArrayOfByte, 2);
    }
    Log.w(this.logTag, paramString + ": unexpected return package length " + paramArrayOfByte.length + " (expected >= 3)");
    return -1;
  }
  
  protected final String getStringValueFromBytes(byte[] paramArrayOfByte, int paramInt)
  {
    int k = 0;
    int i = paramInt;
    for (;;)
    {
      int j = k;
      if (i < paramArrayOfByte.length)
      {
        if (paramArrayOfByte[i] == 0) {
          j = i - paramInt;
        }
      }
      else {
        return getStringValueFromBytes(paramArrayOfByte, paramInt, j);
      }
      i += 1;
    }
  }
  
  protected final String getStringValueFromBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      String str = new String(paramArrayOfByte, paramInt1, paramInt2, "ISO-8859-1");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      Log.w(this.logTag, "UnsupportedEncodingException: " + localUnsupportedEncodingException.getMessage());
    }
    return new String(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  protected final int getUBYTEValueFromBytes(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt] & 0xFF;
  }
  
  protected final long getULONGValueFromBytes(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt] & 0xFF | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 16 | (paramArrayOfByte[(paramInt + 3)] & 0xFF) << 24;
  }
  
  protected final int getUWORDValueFromBytes(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt] & 0xFF | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 8;
  }
  
  protected final int lsGetStatus(String paramString, int paramInt)
  {
    int i = 0;
    byte[] arrayOfByte1 = new byte[3];
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = 14;
    copyUBYTEValueToBytes(paramInt, arrayOfByte1, 2);
    byte[] arrayOfByte2 = sendCommandAndReceiveReturnPackage(paramString, arrayOfByte1);
    paramInt = i;
    if (evaluateStatus(paramString, arrayOfByte2, arrayOfByte1[1]))
    {
      if (arrayOfByte2.length == 4) {
        paramInt = getUBYTEValueFromBytes(arrayOfByte2, 3);
      }
    }
    else {
      return paramInt;
    }
    Log.w(this.logTag, paramString + ": unexpected return package length " + arrayOfByte2.length + " (expected 4)");
    return 0;
  }
  
  protected final byte[] lsRead(String paramString, int paramInt)
  {
    byte[] arrayOfByte1 = new byte[3];
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = 16;
    copyUBYTEValueToBytes(paramInt, arrayOfByte1, 2);
    byte[] arrayOfByte2 = sendCommandAndReceiveReturnPackage(paramString, arrayOfByte1);
    if (evaluateStatus(paramString, arrayOfByte2, arrayOfByte1[1]))
    {
      if (arrayOfByte2.length == 20) {
        return arrayOfByte2;
      }
      Log.w(this.logTag, paramString + ": unexpected return package length " + arrayOfByte2.length + " (expected 20)");
    }
    return null;
  }
  
  protected final void lsWrite(String paramString, int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    if (paramArrayOfByte.length > 16) {
      throw new IllegalArgumentException("length must be <= 16");
    }
    byte[] arrayOfByte = new byte[paramArrayOfByte.length + 5];
    arrayOfByte[0] = 0;
    arrayOfByte[1] = 15;
    copyUBYTEValueToBytes(paramInt1, arrayOfByte, 2);
    copyUBYTEValueToBytes(paramArrayOfByte.length, arrayOfByte, 3);
    copyUBYTEValueToBytes(paramInt2, arrayOfByte, 4);
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 5, paramArrayOfByte.length);
    evaluateStatus(paramString, sendCommandAndReceiveReturnPackage(paramString, arrayOfByte), arrayOfByte[1]);
  }
  
  public void onDelete()
  {
    if (this.bluetooth != null)
    {
      this.bluetooth.removeBluetoothConnectionListener(this);
      this.bluetooth.detachComponent(this);
      this.bluetooth = null;
    }
  }
  
  protected final void resetInputScaledValue(String paramString, int paramInt)
  {
    byte[] arrayOfByte = new byte[3];
    arrayOfByte[0] = Byte.MIN_VALUE;
    arrayOfByte[1] = 8;
    copyUBYTEValueToBytes(paramInt, arrayOfByte, 2);
    sendCommand(paramString, arrayOfByte);
  }
  
  protected final int sanitizePower(int paramInt)
  {
    int i = paramInt;
    if (paramInt < -100)
    {
      Log.w(this.logTag, "power " + paramInt + " is invalid, using -100.");
      i = -100;
    }
    paramInt = i;
    if (i > 100)
    {
      Log.w(this.logTag, "power " + i + " is invalid, using 100.");
      paramInt = 100;
    }
    return paramInt;
  }
  
  protected final int sanitizeTurnRatio(int paramInt)
  {
    int i = paramInt;
    if (paramInt < -100)
    {
      Log.w(this.logTag, "turnRatio " + paramInt + " is invalid, using -100.");
      i = -100;
    }
    paramInt = i;
    if (i > 100)
    {
      Log.w(this.logTag, "turnRatio " + i + " is invalid, using 100.");
      paramInt = 100;
    }
    return paramInt;
  }
  
  protected final void sendCommand(String paramString, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[2];
    copyUWORDValueToBytes(paramArrayOfByte.length, arrayOfByte, 0);
    this.bluetooth.write(paramString, arrayOfByte);
    this.bluetooth.write(paramString, paramArrayOfByte);
  }
  
  protected final byte[] sendCommandAndReceiveReturnPackage(String paramString, byte[] paramArrayOfByte)
  {
    sendCommand(paramString, paramArrayOfByte);
    return receiveReturnPackage(paramString);
  }
  
  protected final void setInputMode(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    byte[] arrayOfByte = new byte[5];
    arrayOfByte[0] = Byte.MIN_VALUE;
    arrayOfByte[1] = 5;
    copyUBYTEValueToBytes(paramInt1, arrayOfByte, 2);
    copyUBYTEValueToBytes(paramInt2, arrayOfByte, 3);
    copyUBYTEValueToBytes(paramInt3, arrayOfByte, 4);
    sendCommand(paramString, arrayOfByte);
  }
  
  protected final void setOutputState(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong)
  {
    paramInt2 = sanitizePower(paramInt2);
    byte[] arrayOfByte = new byte[12];
    arrayOfByte[0] = Byte.MIN_VALUE;
    arrayOfByte[1] = 4;
    copyUBYTEValueToBytes(paramInt1, arrayOfByte, 2);
    copySBYTEValueToBytes(paramInt2, arrayOfByte, 3);
    copyUBYTEValueToBytes(paramInt3, arrayOfByte, 4);
    copyUBYTEValueToBytes(paramInt4, arrayOfByte, 5);
    copySBYTEValueToBytes(paramInt5, arrayOfByte, 6);
    copyUBYTEValueToBytes(paramInt6, arrayOfByte, 7);
    copyULONGValueToBytes(paramLong, arrayOfByte, 8);
    sendCommand(paramString, arrayOfByte);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\LegoMindstormsNxtBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */