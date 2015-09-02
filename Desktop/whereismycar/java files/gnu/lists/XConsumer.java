package gnu.lists;

public abstract interface XConsumer
  extends Consumer
{
  public abstract void beginEntity(Object paramObject);
  
  public abstract void endEntity();
  
  public abstract void writeCDATA(char[] paramArrayOfChar, int paramInt1, int paramInt2);
  
  public abstract void writeComment(char[] paramArrayOfChar, int paramInt1, int paramInt2);
  
  public abstract void writeProcessingInstruction(String paramString, char[] paramArrayOfChar, int paramInt1, int paramInt2);
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\XConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */