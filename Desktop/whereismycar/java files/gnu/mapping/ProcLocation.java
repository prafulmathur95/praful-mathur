package gnu.mapping;

public class ProcLocation
  extends Location
{
  Object[] args;
  Procedure proc;
  
  public ProcLocation(Procedure paramProcedure, Object[] paramArrayOfObject)
  {
    this.proc = paramProcedure;
    this.args = paramArrayOfObject;
  }
  
  public Object get(Object paramObject)
  {
    try
    {
      paramObject = this.proc.applyN(this.args);
      return paramObject;
    }
    catch (RuntimeException paramObject)
    {
      throw ((Throwable)paramObject);
    }
    catch (Error paramObject)
    {
      throw ((Throwable)paramObject);
    }
    catch (Throwable paramObject)
    {
      throw new WrappedException((Throwable)paramObject);
    }
  }
  
  public boolean isBound()
  {
    return true;
  }
  
  public void set(Object paramObject)
  {
    int i = this.args.length;
    Object[] arrayOfObject = new Object[i + 1];
    arrayOfObject[i] = paramObject;
    System.arraycopy(this.args, 0, arrayOfObject, 0, i);
    try
    {
      this.proc.setN(arrayOfObject);
      return;
    }
    catch (RuntimeException paramObject)
    {
      throw ((Throwable)paramObject);
    }
    catch (Error paramObject)
    {
      throw ((Throwable)paramObject);
    }
    catch (Throwable paramObject)
    {
      throw new WrappedException((Throwable)paramObject);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\ProcLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */