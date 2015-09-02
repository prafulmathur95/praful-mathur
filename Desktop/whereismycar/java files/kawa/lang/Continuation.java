package kawa.lang;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;

public class Continuation
  extends MethodProc
{
  static int counter;
  int id;
  public boolean invoked;
  
  public Continuation(CallContext paramCallContext) {}
  
  public static Object handleException(Throwable paramThrowable, Continuation paramContinuation)
    throws Throwable
  {
    CalledContinuation localCalledContinuation;
    if ((paramThrowable instanceof CalledContinuation))
    {
      localCalledContinuation = (CalledContinuation)paramThrowable;
      if (localCalledContinuation.continuation == paramContinuation) {}
    }
    else
    {
      throw paramThrowable;
    }
    paramContinuation.invoked = true;
    return Values.make(localCalledContinuation.values);
  }
  
  public static void handleException$X(Throwable paramThrowable, Continuation paramContinuation, CallContext paramCallContext)
    throws Throwable
  {
    CalledContinuation localCalledContinuation;
    if ((paramThrowable instanceof CalledContinuation))
    {
      localCalledContinuation = (CalledContinuation)paramThrowable;
      if (localCalledContinuation.continuation == paramContinuation) {}
    }
    else
    {
      throw paramThrowable;
    }
    paramContinuation.invoked = true;
    paramThrowable = localCalledContinuation.values;
    int j = paramThrowable.length;
    int i = 0;
    while (i < j)
    {
      paramCallContext.consumer.writeObject(paramThrowable[i]);
      i += 1;
    }
  }
  
  public void apply(CallContext paramCallContext)
  {
    if (this.invoked) {
      throw new GenericError("implementation restriction: continuation can only be used once");
    }
    throw new CalledContinuation(paramCallContext.values, this, paramCallContext);
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("#<continuation ").append(this.id);
    if (this.invoked) {}
    for (String str = " (invoked)>";; str = ">") {
      return str;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\Continuation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */