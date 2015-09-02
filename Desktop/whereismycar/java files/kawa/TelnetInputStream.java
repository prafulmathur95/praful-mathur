package kawa;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class TelnetInputStream
  extends FilterInputStream
{
  static final int SB_IAC = 400;
  protected byte[] buf = new byte['Ȁ'];
  Telnet connection;
  int count;
  int pos;
  int state = 0;
  int subCommandLength = 0;
  
  public TelnetInputStream(InputStream paramInputStream, Telnet paramTelnet)
    throws IOException
  {
    super(paramInputStream);
    this.connection = paramTelnet;
  }
  
  public int read()
    throws IOException
  {
    for (;;)
    {
      int j;
      int i;
      if (this.pos >= this.count)
      {
        j = this.in.available();
        if (j <= 0)
        {
          i = 1;
          i = this.in.read(this.buf, this.subCommandLength, i);
          this.pos = this.subCommandLength;
          this.count = i;
          if (i > 0) {
            break label93;
          }
          i = -1;
        }
      }
      label93:
      byte[] arrayOfByte;
      do
      {
        return i;
        i = j;
        if (j <= this.buf.length - this.subCommandLength) {
          break;
        }
        i = this.buf.length - this.subCommandLength;
        break;
        arrayOfByte = this.buf;
        i = this.pos;
        this.pos = (i + 1);
        j = arrayOfByte[i] & 0xFF;
        if (this.state != 0) {
          break label144;
        }
        i = j;
      } while (j != 255);
      this.state = 255;
      continue;
      label144:
      if (this.state == 255)
      {
        if (j == 255)
        {
          this.state = 0;
          return 255;
        }
        if ((j == 251) || (j == 252) || (j == 253) || (j == 254) || (j == 250))
        {
          this.state = j;
        }
        else if (j == 244)
        {
          System.err.println("Interrupt Process");
          this.state = 0;
        }
        else
        {
          if (j == 236) {
            return -1;
          }
          this.state = 0;
        }
      }
      else if ((this.state == 251) || (this.state == 252) || (this.state == 253) || (this.state == 254))
      {
        this.connection.handle(this.state, j);
        this.state = 0;
      }
      else if (this.state == 250)
      {
        if (j == 255)
        {
          this.state = 400;
        }
        else
        {
          arrayOfByte = this.buf;
          i = this.subCommandLength;
          this.subCommandLength = (i + 1);
          arrayOfByte[i] = ((byte)j);
        }
      }
      else if (this.state == 400)
      {
        if (j == 255)
        {
          arrayOfByte = this.buf;
          i = this.subCommandLength;
          this.subCommandLength = (i + 1);
          arrayOfByte[i] = ((byte)j);
          this.state = 250;
        }
        else if (j == 240)
        {
          this.connection.subCommand(this.buf, 0, this.subCommandLength);
          this.state = 0;
          this.subCommandLength = 0;
        }
        else
        {
          this.state = 0;
          this.subCommandLength = 0;
        }
      }
      else
      {
        System.err.println("Bad state " + this.state);
      }
    }
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 <= 0) {
      j = 0;
    }
    do
    {
      return j;
      j = 0;
      if (this.state == 0)
      {
        k = paramInt1;
        if (this.pos < this.count) {
          break;
        }
      }
      k = read();
      j = k;
    } while (k < 0);
    paramArrayOfByte[paramInt1] = ((byte)k);
    int j = 0 + 1;
    int k = paramInt1 + 1;
    paramInt1 = j;
    if (this.state == 0) {}
    for (;;)
    {
      paramInt1 = j;
      int i;
      if (this.pos < this.count)
      {
        paramInt1 = j;
        if (j < paramInt2)
        {
          i = this.buf[this.pos];
          if (i != -1) {
            break label120;
          }
          paramInt1 = j;
        }
      }
      return paramInt1;
      label120:
      paramArrayOfByte[k] = i;
      j += 1;
      this.pos += 1;
      k += 1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\TelnetInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */