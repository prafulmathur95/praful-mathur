package gnu.mapping;

public class KeyPair
  implements EnvironmentKey
{
  Symbol name;
  Object property;
  
  public KeyPair(Symbol paramSymbol, Object paramObject)
  {
    this.name = paramSymbol;
    this.property = paramObject;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof KeyPair)) {}
    do
    {
      do
      {
        return false;
        paramObject = (KeyPair)paramObject;
      } while (this.property != ((KeyPair)paramObject).property);
      if (this.name != null) {
        break;
      }
    } while (((KeyPair)paramObject).name != null);
    for (;;)
    {
      return true;
      if (!this.name.equals(((KeyPair)paramObject).name)) {
        break;
      }
    }
  }
  
  public Object getKeyProperty()
  {
    return this.property;
  }
  
  public Symbol getKeySymbol()
  {
    return this.name;
  }
  
  public int hashCode()
  {
    return this.name.hashCode() ^ System.identityHashCode(this.property);
  }
  
  public final boolean matches(EnvironmentKey paramEnvironmentKey)
  {
    return (Symbol.equals(paramEnvironmentKey.getKeySymbol(), this.name)) && (paramEnvironmentKey.getKeyProperty() == this.property);
  }
  
  public final boolean matches(Symbol paramSymbol, Object paramObject)
  {
    return (Symbol.equals(paramSymbol, this.name)) && (paramObject == this.property);
  }
  
  public String toString()
  {
    return "KeyPair[sym:" + this.name + " prop:" + this.property + "]";
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\KeyPair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */