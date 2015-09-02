package gnu.expr;

import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.LocationEnumeration;
import gnu.mapping.NamedLocation;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;

public class BuiltinEnvironment
  extends Environment
{
  static final BuiltinEnvironment instance = new BuiltinEnvironment();
  
  static
  {
    instance.setName("language-builtins");
  }
  
  public static BuiltinEnvironment getInstance()
  {
    return instance;
  }
  
  public NamedLocation addLocation(Symbol paramSymbol, Object paramObject, Location paramLocation)
  {
    throw new RuntimeException();
  }
  
  public void define(Symbol paramSymbol, Object paramObject1, Object paramObject2)
  {
    throw new RuntimeException();
  }
  
  public LocationEnumeration enumerateAllLocations()
  {
    return getLangEnvironment().enumerateAllLocations();
  }
  
  public LocationEnumeration enumerateLocations()
  {
    return getLangEnvironment().enumerateLocations();
  }
  
  public Environment getLangEnvironment()
  {
    Language localLanguage = Language.getDefaultLanguage();
    if (localLanguage == null) {
      return null;
    }
    return localLanguage.getLangEnvironment();
  }
  
  public NamedLocation getLocation(Symbol paramSymbol, Object paramObject, int paramInt, boolean paramBoolean)
  {
    throw new RuntimeException();
  }
  
  protected boolean hasMoreElements(LocationEnumeration paramLocationEnumeration)
  {
    throw new RuntimeException();
  }
  
  public NamedLocation lookup(Symbol paramSymbol, Object paramObject, int paramInt)
  {
    if (paramObject == ThreadLocation.ANONYMOUS) {}
    Language localLanguage;
    do
    {
      return null;
      localLanguage = Language.getDefaultLanguage();
    } while (localLanguage == null);
    return localLanguage.lookupBuiltin(paramSymbol, paramObject, paramInt);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\BuiltinEnvironment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */