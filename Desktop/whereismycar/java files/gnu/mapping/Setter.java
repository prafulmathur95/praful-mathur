package gnu.mapping;

public class Setter
  extends ProcedureN
{
  protected Procedure getter;
  
  public Setter(Procedure paramProcedure)
  {
    this.getter = paramProcedure;
    paramProcedure = paramProcedure.getName();
    if (paramProcedure != null) {
      setName("(setter " + paramProcedure + ")");
    }
  }
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    this.getter.setN(paramArrayOfObject);
    return Values.empty;
  }
  
  public int numArgs()
  {
    int i = this.getter.numArgs();
    if (i < 0) {
      return i + 1;
    }
    return i + 4097;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\Setter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */