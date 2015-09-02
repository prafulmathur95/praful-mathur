package gnu.mapping;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LocationEnumeration
  implements Iterator<Location>, Enumeration<Location>
{
  NamedLocation[] bindings;
  SimpleEnvironment env;
  int index;
  LocationEnumeration inherited;
  NamedLocation nextLoc;
  NamedLocation prevLoc;
  
  public LocationEnumeration(SimpleEnvironment paramSimpleEnvironment)
  {
    this(paramSimpleEnvironment.table, 1 << paramSimpleEnvironment.log2Size);
  }
  
  public LocationEnumeration(NamedLocation[] paramArrayOfNamedLocation, int paramInt)
  {
    this.bindings = paramArrayOfNamedLocation;
    this.index = paramInt;
  }
  
  public boolean hasMoreElements()
  {
    return this.env.hasMoreElements(this);
  }
  
  public boolean hasNext()
  {
    return hasMoreElements();
  }
  
  public Location next()
  {
    return nextElement();
  }
  
  public Location nextElement()
  {
    return nextLocation();
  }
  
  public Location nextLocation()
  {
    if ((this.nextLoc == null) && (!hasMoreElements())) {
      throw new NoSuchElementException();
    }
    NamedLocation localNamedLocation = this.prevLoc;
    if (this.prevLoc == null)
    {
      localNamedLocation = this.bindings[this.index];
      if (this.nextLoc == localNamedLocation) {}
    }
    for (this.prevLoc = localNamedLocation; (this.prevLoc != null) && (this.prevLoc.next != this.nextLoc); this.prevLoc = this.prevLoc.next) {}
    localNamedLocation = this.nextLoc;
    this.nextLoc = this.nextLoc.next;
    return localNamedLocation;
  }
  
  public void remove()
  {
    if (this.prevLoc != null) {}
    for (Object localObject = this.prevLoc.next; (localObject == null) || (((NamedLocation)localObject).next != this.nextLoc); localObject = this.bindings[this.index]) {
      throw new IllegalStateException();
    }
    ((NamedLocation)localObject).next = null;
    if (this.prevLoc != null) {
      this.prevLoc.next = this.nextLoc;
    }
    for (;;)
    {
      localObject = this.env;
      ((SimpleEnvironment)localObject).num_bindings -= 1;
      return;
      this.bindings[this.index] = this.nextLoc;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\LocationEnumeration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */