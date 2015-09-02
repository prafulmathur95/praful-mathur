package kawa.standard;

import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import kawa.lang.GenericError;
import kawa.lang.NamedException;

public class throw_name
  extends ProcedureN
{
  public static final throw_name throwName = new throw_name();
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    Object localObject;
    if (paramArrayOfObject.length > 0)
    {
      localObject = paramArrayOfObject[0];
      if (!(localObject instanceof Throwable)) {
        break label36;
      }
      if (paramArrayOfObject.length == 1) {
        prim_throw.throw_it(localObject);
      }
    }
    label36:
    while (!(localObject instanceof Symbol)) {
      throw new GenericError("bad arguments to throw");
    }
    throw new NamedException((Symbol)localObject, paramArrayOfObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\throw_name.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */