package kawa.lang;

import gnu.lists.Consumer;
import gnu.mapping.Procedure;
import gnu.text.Printable;

public class Promise
  implements Printable
{
  Object result;
  Procedure thunk;
  
  public Promise(Procedure paramProcedure)
  {
    this.thunk = paramProcedure;
  }
  
  public static Object force(Object paramObject)
    throws Throwable
  {
    Object localObject;
    if ((paramObject instanceof Promise)) {
      localObject = ((Promise)paramObject).force();
    }
    do
    {
      return localObject;
      if ((paramObject instanceof gnu.mapping.Future)) {
        return ((gnu.mapping.Future)paramObject).waitForResult();
      }
      localObject = paramObject;
    } while (!(paramObject instanceof java.util.concurrent.Future));
    return ((java.util.concurrent.Future)paramObject).get();
  }
  
  public Object force()
    throws Throwable
  {
    if (this.result == null)
    {
      Object localObject = this.thunk.apply0();
      if (this.result == null) {
        this.result = localObject;
      }
    }
    return this.result;
  }
  
  public void print(Consumer paramConsumer)
  {
    if (this.result == null)
    {
      paramConsumer.write("#<promise - not forced yet>");
      return;
    }
    paramConsumer.write("#<promise - forced to a ");
    paramConsumer.write(this.result.getClass().getName());
    paramConsumer.write(62);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\Promise.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */