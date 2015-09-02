package gnu.lists;

public class VoidConsumer
  extends FilterConsumer
{
  public static VoidConsumer instance = new VoidConsumer();
  
  public VoidConsumer()
  {
    super(null);
    this.skipping = true;
  }
  
  public static VoidConsumer getInstance()
  {
    return instance;
  }
  
  public boolean ignoring()
  {
    return true;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\VoidConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */