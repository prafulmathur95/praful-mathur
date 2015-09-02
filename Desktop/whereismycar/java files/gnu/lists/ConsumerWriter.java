package gnu.lists;

import java.io.Writer;

public class ConsumerWriter
  extends Writer
{
  protected Consumer out;
  
  public ConsumerWriter(Consumer paramConsumer)
  {
    this.out = paramConsumer;
  }
  
  public void close()
  {
    flush();
  }
  
  public void finalize()
  {
    close();
  }
  
  public void flush() {}
  
  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this.out.write(paramArrayOfChar, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\ConsumerWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */