package gnu.lists;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class PrintConsumer
  extends PrintWriter
  implements Appendable, Consumer
{
  public PrintConsumer(Consumer paramConsumer, boolean paramBoolean) {}
  
  public PrintConsumer(OutputStream paramOutputStream, boolean paramBoolean)
  {
    super(paramOutputStream, paramBoolean);
  }
  
  public PrintConsumer(Writer paramWriter)
  {
    super(paramWriter);
  }
  
  public PrintConsumer(Writer paramWriter, boolean paramBoolean)
  {
    super(paramWriter, paramBoolean);
  }
  
  public PrintConsumer append(char paramChar)
  {
    print(paramChar);
    return this;
  }
  
  public PrintConsumer append(CharSequence paramCharSequence)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null) {
      localObject = "null";
    }
    append((CharSequence)localObject, 0, ((CharSequence)localObject).length());
    return this;
  }
  
  public PrintConsumer append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null) {
      localObject = "null";
    }
    while (paramInt1 < paramInt2)
    {
      append(((CharSequence)localObject).charAt(paramInt1));
      paramInt1 += 1;
    }
    return this;
  }
  
  public void endAttribute() {}
  
  public void endDocument() {}
  
  public void endElement() {}
  
  protected void endNumber() {}
  
  public boolean ignoring()
  {
    return false;
  }
  
  public void startAttribute(Object paramObject) {}
  
  public void startDocument() {}
  
  public void startElement(Object paramObject) {}
  
  protected void startNumber() {}
  
  public void write(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if ((paramCharSequence instanceof String)) {
      write((String)paramCharSequence, paramInt1, paramInt2);
    }
    for (;;)
    {
      return;
      while (paramInt1 < paramInt2)
      {
        write(paramCharSequence.charAt(paramInt1));
        paramInt1 += 1;
      }
    }
  }
  
  public void writeBoolean(boolean paramBoolean)
  {
    print(paramBoolean);
  }
  
  public void writeDouble(double paramDouble)
  {
    startNumber();
    print(paramDouble);
    endNumber();
  }
  
  public void writeFloat(float paramFloat)
  {
    startNumber();
    print(paramFloat);
    endNumber();
  }
  
  public void writeInt(int paramInt)
  {
    startNumber();
    print(paramInt);
    endNumber();
  }
  
  public void writeLong(long paramLong)
  {
    startNumber();
    print(paramLong);
    endNumber();
  }
  
  public void writeObject(Object paramObject)
  {
    print(paramObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\PrintConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */