package gnu.lists;

import gnu.mapping.CharArrayOutPort;
import gnu.mapping.OutPort;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public abstract class AbstractFormat
  extends Format
{
  public void endAttribute(Consumer paramConsumer)
  {
    write(" ", paramConsumer);
  }
  
  public void endElement(Consumer paramConsumer)
  {
    write(")", paramConsumer);
  }
  
  public StringBuffer format(Object paramObject, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    paramFieldPosition = new CharArrayOutPort();
    writeObject(paramObject, paramFieldPosition);
    paramStringBuffer.append(paramFieldPosition.toCharArray());
    paramFieldPosition.close();
    return paramStringBuffer;
  }
  
  public void format(Object paramObject, Consumer paramConsumer)
  {
    if ((paramConsumer instanceof OutPort))
    {
      OutPort localOutPort = (OutPort)paramConsumer;
      AbstractFormat localAbstractFormat = localOutPort.objectFormat;
      try
      {
        localOutPort.objectFormat = this;
        paramConsumer.writeObject(paramObject);
        return;
      }
      finally
      {
        localOutPort.objectFormat = localAbstractFormat;
      }
    }
    paramConsumer.writeObject(paramObject);
  }
  
  public Object parseObject(String paramString, ParsePosition paramParsePosition)
  {
    throw new Error(getClass().getName() + ".parseObject - not implemented");
  }
  
  public void startAttribute(Object paramObject, Consumer paramConsumer)
  {
    write(paramObject.toString(), paramConsumer);
    write(": ", paramConsumer);
  }
  
  public void startElement(Object paramObject, Consumer paramConsumer)
  {
    write("(", paramConsumer);
    write(paramObject.toString(), paramConsumer);
    write(" ", paramConsumer);
  }
  
  public void write(int paramInt, Consumer paramConsumer)
  {
    paramConsumer.write(paramInt);
  }
  
  protected void write(String paramString, Consumer paramConsumer)
  {
    paramConsumer.write(paramString);
  }
  
  public void writeBoolean(boolean paramBoolean, Consumer paramConsumer)
  {
    paramConsumer.writeBoolean(paramBoolean);
  }
  
  public void writeInt(int paramInt, Consumer paramConsumer)
  {
    writeLong(paramInt, paramConsumer);
  }
  
  public void writeLong(long paramLong, Consumer paramConsumer)
  {
    paramConsumer.writeLong(paramLong);
  }
  
  public abstract void writeObject(Object paramObject, Consumer paramConsumer);
  
  public final void writeObject(Object paramObject, PrintConsumer paramPrintConsumer)
  {
    writeObject(paramObject, paramPrintConsumer);
  }
  
  public final void writeObject(Object paramObject, Writer paramWriter)
  {
    if ((paramWriter instanceof Consumer))
    {
      writeObject(paramObject, (Consumer)paramWriter);
      return;
    }
    OutPort localOutPort = new OutPort(paramWriter, false, true);
    writeObject(paramObject, (Consumer)paramWriter);
    localOutPort.close();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\AbstractFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */