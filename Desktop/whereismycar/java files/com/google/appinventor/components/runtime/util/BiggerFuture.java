package com.google.appinventor.components.runtime.util;

import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.RunnableClosure;

public class BiggerFuture
  extends Thread
{
  public BiggerFuture(Procedure paramProcedure, InPort paramInPort, OutPort paramOutPort1, OutPort paramOutPort2, String paramString, long paramLong)
  {
    super(new ThreadGroup("biggerthreads"), new RunnableClosure(paramProcedure, paramInPort, paramOutPort1, paramOutPort2), paramString, paramLong);
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("#<future ");
    localStringBuffer.append(getName());
    localStringBuffer.append(">");
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\BiggerFuture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */