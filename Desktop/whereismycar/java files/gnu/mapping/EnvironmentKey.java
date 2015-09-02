package gnu.mapping;

public abstract interface EnvironmentKey
{
  public static final Object FUNCTION = Symbol.FUNCTION;
  
  public abstract Object getKeyProperty();
  
  public abstract Symbol getKeySymbol();
  
  public abstract boolean matches(EnvironmentKey paramEnvironmentKey);
  
  public abstract boolean matches(Symbol paramSymbol, Object paramObject);
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\EnvironmentKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */