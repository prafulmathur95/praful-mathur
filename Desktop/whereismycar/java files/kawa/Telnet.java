package kawa;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Telnet
  implements Runnable
{
  public static final int DO = 253;
  public static final int DONT = 254;
  public static final int ECHO = 1;
  static final int EOF = 236;
  static final int IAC = 255;
  static final int IP = 244;
  static final int LINEMODE = 34;
  static final int NAWS = 31;
  static final int NOP = 241;
  static final int OPTION_NO = 0;
  static final int OPTION_WANTNO = 1;
  static final int OPTION_WANTNO_OPPOSITE = 2;
  static final int OPTION_WANTYES = 3;
  static final int OPTION_WANTYES_OPPOSITE = 4;
  static final int OPTION_YES = 5;
  static final int SB = 250;
  static final int SE = 240;
  public static final int SUPPRESS_GO_AHEAD = 3;
  static final int TM = 6;
  static final int TTYPE = 24;
  public static final int WILL = 251;
  public static final int WONT = 252;
  TelnetInputStream in;
  boolean isServer;
  final byte[] optionsState = new byte['Ā'];
  TelnetOutputStream out;
  final byte preferredLineMode = 3;
  InputStream sin;
  OutputStream sout;
  public byte[] terminalType;
  public short windowHeight;
  public short windowWidth;
  
  public Telnet(Socket paramSocket, boolean paramBoolean)
    throws IOException
  {
    this.sin = paramSocket.getInputStream();
    this.sout = paramSocket.getOutputStream();
    this.out = new TelnetOutputStream(this.sout);
    this.in = new TelnetInputStream(this.sin, this);
    this.isServer = paramBoolean;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    if (paramArrayOfString.length == 0) {
      usage();
    }
    Object localObject = paramArrayOfString[0];
    int i = 23;
    if (paramArrayOfString.length > 1) {
      i = Integer.parseInt(paramArrayOfString[1]);
    }
    try
    {
      localObject = new Telnet(new Socket((String)localObject, i), false);
      paramArrayOfString = ((Telnet)localObject).getOutputStream();
      localObject = new Thread((Runnable)localObject);
      ((Thread)localObject).setPriority(Thread.currentThread().getPriority() + 1);
      ((Thread)localObject).start();
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        i = System.in.read();
        if (i < 0)
        {
          ((Thread)localObject).stop();
          return;
        }
        arrayOfByte[0] = ((byte)i);
        i = System.in.available();
        int j = i;
        if (i > 0)
        {
          j = i;
          if (i > arrayOfByte.length - 1) {
            j = arrayOfByte.length - 1;
          }
          j = System.in.read(arrayOfByte, 1, j);
        }
        paramArrayOfString.write(arrayOfByte, 0, j + 1);
      }
      return;
    }
    catch (Exception paramArrayOfString)
    {
      System.err.println(paramArrayOfString);
    }
  }
  
  static void usage()
  {
    System.err.println("Usage:  [java] kawa.Telnet HOST [PORT#]");
    System.exit(-1);
  }
  
  boolean change(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 6) {}
    do
    {
      do
      {
        return true;
      } while ((this.isServer) && (paramInt2 == 31));
      if ((this.isServer) && (paramInt1 == 251) && (paramInt2 == 34)) {
        try
        {
          this.out.writeSubCommand(34, new byte[] { 1, 3 });
          return true;
        }
        catch (IOException localIOException1)
        {
          return true;
        }
      }
      if ((this.isServer) && (paramInt1 == 251) && (paramInt2 == 24)) {
        try
        {
          this.out.writeSubCommand(paramInt2, new byte[] { 1 });
          return true;
        }
        catch (IOException localIOException2)
        {
          return true;
        }
      }
      if ((this.isServer) || (paramInt2 != 1)) {
        break;
      }
      if (paramInt1 == 253) {
        return false;
      }
    } while (paramInt1 == 251);
    return false;
  }
  
  public TelnetInputStream getInputStream()
  {
    return this.in;
  }
  
  public TelnetOutputStream getOutputStream()
  {
    return this.out;
  }
  
  void handle(int paramInt1, int paramInt2)
    throws IOException
  {
    int n = 1;
    int m = 254;
    int i1 = 253;
    int k;
    label29:
    int j;
    if (paramInt1 < 253)
    {
      k = 1;
      if ((paramInt1 & 0x1) == 0) {
        break label138;
      }
      int i2 = this.optionsState[paramInt2];
      j = i2;
      if (k != 0) {
        j = (byte)(i2 >> 3);
      }
      switch (j >> 3 & 0x7)
      {
      default: 
        paramInt1 = j;
        label103:
        if (k == 0) {
          break;
        }
      }
    }
    for (int i = (byte)(this.optionsState[paramInt2] & 0xC7 | paramInt1 << 3);; i = (byte)(this.optionsState[paramInt2] & 0xF8 | paramInt1))
    {
      this.optionsState[paramInt2] = i;
      label138:
      do
      {
        do
        {
          return;
          k = 0;
          break;
          n = 0;
          break label29;
        } while (n != 0);
        j = 0;
        change(paramInt1, paramInt2);
        localTelnetOutputStream = this.out;
        if (k != 0) {}
        for (paramInt1 = 254;; paramInt1 = 252)
        {
          localTelnetOutputStream.writeCommand(paramInt1, paramInt2);
          paramInt1 = j;
          break;
        }
      } while (n == 0);
      if (change(paramInt1, paramInt2))
      {
        j = 5;
        localTelnetOutputStream = this.out;
        if (k != 0) {}
        for (paramInt1 = 253;; paramInt1 = 251)
        {
          localTelnetOutputStream.writeCommand(paramInt1, paramInt2);
          paramInt1 = j;
          break;
        }
      }
      TelnetOutputStream localTelnetOutputStream = this.out;
      if (k != 0) {}
      for (;;)
      {
        localTelnetOutputStream.writeCommand(m, paramInt2);
        paramInt1 = j;
        break;
        m = 252;
      }
      paramInt1 = 0;
      break label103;
      j = 3;
      localTelnetOutputStream = this.out;
      if (k != 0) {}
      for (paramInt1 = i1;; paramInt1 = 251)
      {
        localTelnetOutputStream.writeCommand(paramInt1, paramInt2);
        paramInt1 = j;
        break;
      }
      if (n != 0)
      {
        j = 5;
        change(paramInt1, paramInt2);
        paramInt1 = j;
        break label103;
      }
      paramInt1 = 0;
      break label103;
      if (n != 0)
      {
        paramInt1 = 1;
        localTelnetOutputStream = this.out;
        if (k != 0) {}
        for (;;)
        {
          localTelnetOutputStream.writeCommand(m, paramInt2);
          break;
          m = 252;
        }
      }
      paramInt1 = 0;
      break label103;
    }
  }
  
  public void request(int paramInt1, int paramInt2)
    throws IOException
  {
    int n = 1;
    int m;
    label19:
    int k;
    int j;
    if (paramInt1 >= 253)
    {
      m = 1;
      if ((paramInt1 & 0x1) == 0) {
        break label128;
      }
      k = this.optionsState[paramInt2];
      j = k;
      if (m != 0) {
        j = (byte)(k >> 3);
      }
      k = j;
      switch (j & 0x7)
      {
      default: 
        label92:
        if (m == 0) {
          break;
        }
      }
    }
    for (int i = (byte)(this.optionsState[paramInt2] & 0xC7 | j << 3);; i = (byte)(this.optionsState[paramInt2] & 0xF8 | j))
    {
      this.optionsState[paramInt2] = i;
      return;
      m = 0;
      break;
      label128:
      n = 0;
      break label19;
      if (n == 0) {
        break label92;
      }
      j = 3;
      this.out.writeCommand(paramInt1, paramInt2);
      break label92;
      if (n != 0) {
        break label92;
      }
      j = 1;
      this.out.writeCommand(paramInt1, paramInt2);
      break label92;
      if (n == 0) {
        break label92;
      }
      j = 2;
      break label92;
      if (n != 0) {
        break label92;
      }
      j = 1;
      break label92;
      k = j;
      if (n == 0) {
        k = 4;
      }
      j = k;
      if (n == 0) {
        break label92;
      }
      j = 3;
      break label92;
    }
  }
  
  public void run()
  {
    try
    {
      TelnetInputStream localTelnetInputStream = getInputStream();
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i = localTelnetInputStream.read();
        if (i < 0) {
          return;
        }
        arrayOfByte[0] = ((byte)i);
        i = localTelnetInputStream.available();
        int j = i;
        if (i > 0)
        {
          j = i;
          if (i > arrayOfByte.length - 1) {
            j = arrayOfByte.length - 1;
          }
          j = localTelnetInputStream.read(arrayOfByte, 1, j);
        }
        System.out.write(arrayOfByte, 0, j + 1);
      }
      return;
    }
    catch (IOException localIOException)
    {
      System.err.println(localIOException);
      System.exit(-1);
    }
  }
  
  public void subCommand(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    switch (paramArrayOfByte[paramInt1])
    {
    }
    for (;;)
    {
      return;
      if (paramInt2 == 5)
      {
        this.windowWidth = ((short)((paramArrayOfByte[1] << 8) + (paramArrayOfByte[2] & 0xFF)));
        this.windowHeight = ((short)((paramArrayOfByte[3] << 8) + (paramArrayOfByte[4] & 0xFF)));
        return;
        byte[] arrayOfByte = new byte[paramInt2 - 1];
        System.arraycopy(paramArrayOfByte, 1, arrayOfByte, 0, paramInt2 - 1);
        this.terminalType = arrayOfByte;
        System.err.println("terminal type: '" + new String(arrayOfByte) + "'");
        return;
        System.err.println("SBCommand LINEMODE " + paramArrayOfByte[1] + " len:" + paramInt2);
        if (paramArrayOfByte[1] == 3)
        {
          paramInt1 = 2;
          while (paramInt1 + 2 < paramInt2)
          {
            System.err.println("  " + paramArrayOfByte[paramInt1] + "," + paramArrayOfByte[(paramInt1 + 1)] + "," + paramArrayOfByte[(paramInt1 + 2)]);
            paramInt1 += 3;
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\Telnet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */