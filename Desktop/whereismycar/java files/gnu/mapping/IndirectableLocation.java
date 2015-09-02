package gnu.mapping;

public abstract class IndirectableLocation
  extends Location
{
  protected static final Object DIRECT_ON_SET = new String("(direct-on-set)");
  protected static final Object INDIRECT_FLUIDS = new String("(indirect-fluids)");
  protected Location base;
  protected Object value;
  
  public Location getBase()
  {
    if (this.base == null) {
      return this;
    }
    return this.base.getBase();
  }
  
  public Location getBaseForce()
  {
    if (this.base == null) {
      return new PlainLocation(getKeySymbol(), getKeyProperty(), this.value);
    }
    return this.base;
  }
  
  public Environment getEnvironment()
  {
    if ((this.base instanceof NamedLocation)) {
      return ((NamedLocation)this.base).getEnvironment();
    }
    return null;
  }
  
  public Object getKeyProperty()
  {
    if (this.base != null) {
      return this.base.getKeyProperty();
    }
    return null;
  }
  
  public Symbol getKeySymbol()
  {
    if (this.base != null) {
      return this.base.getKeySymbol();
    }
    return null;
  }
  
  public boolean isConstant()
  {
    return (this.base != null) && (this.base.isConstant());
  }
  
  public void setAlias(Location paramLocation)
  {
    this.base = paramLocation;
    this.value = INDIRECT_FLUIDS;
  }
  
  public void setBase(Location paramLocation)
  {
    this.base = paramLocation;
    this.value = null;
  }
  
  public void undefine()
  {
    this.base = null;
    this.value = UNBOUND;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\IndirectableLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */