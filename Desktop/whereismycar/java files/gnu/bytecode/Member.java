package gnu.bytecode;

public abstract interface Member
{
  public abstract ClassType getDeclaringClass();
  
  public abstract int getModifiers();
  
  public abstract String getName();
  
  public abstract boolean getStaticFlag();
  
  public abstract void setName(String paramString);
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\Member.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */