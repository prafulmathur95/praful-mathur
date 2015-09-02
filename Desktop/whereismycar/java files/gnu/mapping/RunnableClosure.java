package gnu.mapping;

import java.util.concurrent.Callable;

public class RunnableClosure
  implements Callable<Object>, Runnable
{
  static int nrunnables = 0;
  Procedure action;
  CallContext context;
  private OutPort err;
  Throwable exception;
  private InPort in;
  String name;
  private OutPort out;
  Object result;
  
  public RunnableClosure(Procedure paramProcedure)
  {
    this(paramProcedure, CallContext.getInstance());
  }
  
  public RunnableClosure(Procedure paramProcedure, CallContext paramCallContext)
  {
    paramCallContext = new StringBuilder().append("r");
    int i = nrunnables;
    nrunnables = i + 1;
    setName(i);
    this.action = paramProcedure;
  }
  
  public RunnableClosure(Procedure paramProcedure, InPort paramInPort, OutPort paramOutPort1, OutPort paramOutPort2)
  {
    this(paramProcedure, CallContext.getInstance());
    this.in = paramInPort;
    this.out = paramOutPort1;
    this.err = paramOutPort2;
  }
  
  public Object call()
    throws Exception
  {
    run();
    Throwable localThrowable = this.exception;
    if (localThrowable != null)
    {
      if ((localThrowable instanceof Exception)) {
        throw ((Exception)localThrowable);
      }
      if ((localThrowable instanceof Error)) {
        throw ((Error)localThrowable);
      }
      throw new RuntimeException(localThrowable);
    }
    return this.result;
  }
  
  public final CallContext getCallContext()
  {
    return this.context;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  Object getResult()
    throws Throwable
  {
    Throwable localThrowable = this.exception;
    if (localThrowable != null) {
      throw localThrowable;
    }
    return this.result;
  }
  
  public void run()
  {
    try
    {
      Environment localEnvironment = Environment.getCurrent();
      String str = getName();
      if ((localEnvironment != null) && (localEnvironment.getSymbol() == null) && (str != null)) {
        localEnvironment.setName(str);
      }
      if (this.context == null) {
        this.context = CallContext.getInstance();
      }
      for (;;)
      {
        if (this.in != null) {
          InPort.setInDefault(this.in);
        }
        if (this.out != null) {
          OutPort.setOutDefault(this.out);
        }
        if (this.err != null) {
          OutPort.setErrDefault(this.err);
        }
        this.result = this.action.apply0();
        return;
        CallContext.setInstance(this.context);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      this.exception = localThrowable;
    }
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("#<runnable ");
    localStringBuffer.append(getName());
    localStringBuffer.append(">");
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\RunnableClosure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */