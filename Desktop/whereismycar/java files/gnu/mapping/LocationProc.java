package gnu.mapping;

public class LocationProc
  extends Procedure0or1
  implements HasSetter
{
  Location loc;
  
  public LocationProc(Location paramLocation)
  {
    this.loc = paramLocation;
  }
  
  public LocationProc(Location paramLocation, Procedure paramProcedure)
  {
    this.loc = paramLocation;
    if (paramProcedure != null) {
      pushConverter(paramProcedure);
    }
  }
  
  public static LocationProc makeNamed(Symbol paramSymbol, Location paramLocation)
  {
    paramLocation = new LocationProc(paramLocation);
    paramLocation.setSymbol(paramSymbol);
    return paramLocation;
  }
  
  public Object apply0()
    throws Throwable
  {
    return this.loc.get();
  }
  
  public Object apply1(Object paramObject)
    throws Throwable
  {
    set0(paramObject);
    return Values.empty;
  }
  
  public final Location getLocation()
  {
    return this.loc;
  }
  
  public Procedure getSetter()
  {
    return new Setter0(this);
  }
  
  public void pushConverter(Procedure paramProcedure)
  {
    this.loc = ConstrainedLocation.make(this.loc, paramProcedure);
  }
  
  public void set0(Object paramObject)
    throws Throwable
  {
    this.loc.set(paramObject);
  }
  
  public String toString()
  {
    if (getSymbol() != null) {
      return super.toString();
    }
    return "#<location-proc " + this.loc + ">";
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\LocationProc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */