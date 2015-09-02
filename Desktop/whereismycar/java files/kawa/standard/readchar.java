package kawa.standard;

import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.Procedure0or1;
import gnu.mapping.WrongType;
import gnu.text.Char;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class readchar
  extends Procedure0or1
{
  public static final readchar peekChar = new readchar(true);
  public static final readchar readChar = new readchar(false);
  boolean peeking;
  
  public readchar(boolean paramBoolean) {}
  
  public final Object apply0()
  {
    return readChar(InPort.inDefault());
  }
  
  public final Object apply1(Object paramObject)
  {
    if ((paramObject instanceof InPort)) {
      return readChar((InPort)paramObject);
    }
    if ((paramObject instanceof Reader)) {
      return readChar((Reader)paramObject);
    }
    if ((paramObject instanceof InputStream)) {
      return readChar((InputStream)paramObject);
    }
    throw new WrongType(this, 1, paramObject, "<input-port>");
  }
  
  final Object readChar(InPort paramInPort)
  {
    try
    {
      if (this.peeking) {}
      for (int i = paramInPort.peek(); i < 0; i = paramInPort.read()) {
        return Sequence.eofValue;
      }
      paramInPort = Char.make(i);
      return paramInPort;
    }
    catch (IOException paramInPort)
    {
      throw new RuntimeException("IO Exception caught");
    }
  }
  
  final Object readChar(InputStream paramInputStream)
  {
    try
    {
      int i;
      if (this.peeking)
      {
        paramInputStream.mark(1);
        i = paramInputStream.read();
        paramInputStream.reset();
      }
      while (i < 0)
      {
        return Sequence.eofValue;
        i = paramInputStream.read();
      }
      paramInputStream = Char.make(i);
      return paramInputStream;
    }
    catch (IOException paramInputStream)
    {
      throw new RuntimeException("IO Exception caught");
    }
  }
  
  final Object readChar(Reader paramReader)
  {
    try
    {
      int i;
      if (this.peeking)
      {
        paramReader.mark(1);
        i = paramReader.read();
        paramReader.reset();
      }
      while (i < 0)
      {
        return Sequence.eofValue;
        i = paramReader.read();
      }
      paramReader = Char.make(i);
      return paramReader;
    }
    catch (IOException paramReader)
    {
      throw new RuntimeException("IO Exception caught");
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\readchar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */