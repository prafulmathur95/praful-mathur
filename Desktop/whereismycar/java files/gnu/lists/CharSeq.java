package gnu.lists;

import java.io.IOException;

public abstract interface CharSeq
  extends CharSequence, Sequence
{
  public abstract char charAt(int paramInt);
  
  public abstract void consume(int paramInt1, int paramInt2, Consumer paramConsumer);
  
  public abstract void fill(char paramChar);
  
  public abstract void fill(int paramInt1, int paramInt2, char paramChar);
  
  public abstract void getChars(int paramInt1, int paramInt2, char[] paramArrayOfChar, int paramInt3);
  
  public abstract int length();
  
  public abstract void setCharAt(int paramInt, char paramChar);
  
  public abstract CharSequence subSequence(int paramInt1, int paramInt2);
  
  public abstract String toString();
  
  public abstract void writeTo(int paramInt1, int paramInt2, Appendable paramAppendable)
    throws IOException;
  
  public abstract void writeTo(Appendable paramAppendable)
    throws IOException;
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\CharSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */