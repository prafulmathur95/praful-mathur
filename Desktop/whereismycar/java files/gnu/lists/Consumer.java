package gnu.lists;

public abstract interface Consumer
  extends Appendable
{
  public abstract Consumer append(char paramChar);
  
  public abstract Consumer append(CharSequence paramCharSequence);
  
  public abstract Consumer append(CharSequence paramCharSequence, int paramInt1, int paramInt2);
  
  public abstract void endAttribute();
  
  public abstract void endDocument();
  
  public abstract void endElement();
  
  public abstract boolean ignoring();
  
  public abstract void startAttribute(Object paramObject);
  
  public abstract void startDocument();
  
  public abstract void startElement(Object paramObject);
  
  public abstract void write(int paramInt);
  
  public abstract void write(CharSequence paramCharSequence, int paramInt1, int paramInt2);
  
  public abstract void write(String paramString);
  
  public abstract void write(char[] paramArrayOfChar, int paramInt1, int paramInt2);
  
  public abstract void writeBoolean(boolean paramBoolean);
  
  public abstract void writeDouble(double paramDouble);
  
  public abstract void writeFloat(float paramFloat);
  
  public abstract void writeInt(int paramInt);
  
  public abstract void writeLong(long paramLong);
  
  public abstract void writeObject(Object paramObject);
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\Consumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */