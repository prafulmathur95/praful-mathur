package gnu.mapping;

public class ReadOnlyLocation
  extends ConstrainedLocation
{
  public static ReadOnlyLocation make(Location paramLocation)
  {
    ReadOnlyLocation localReadOnlyLocation = new ReadOnlyLocation();
    localReadOnlyLocation.base = paramLocation;
    return localReadOnlyLocation;
  }
  
  protected Object coerce(Object paramObject)
  {
    paramObject = new StringBuffer("attempt to modify read-only location");
    Symbol localSymbol = getKeySymbol();
    if (localSymbol != null)
    {
      ((StringBuffer)paramObject).append(": ");
      ((StringBuffer)paramObject).append(localSymbol);
    }
    throw new IllegalStateException(((StringBuffer)paramObject).toString());
  }
  
  public boolean isConstant()
  {
    return true;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\ReadOnlyLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */