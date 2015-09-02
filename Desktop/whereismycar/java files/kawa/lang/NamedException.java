package kawa.lang;

import gnu.mapping.Procedure;
import gnu.mapping.Symbol;

public class NamedException
  extends RuntimeException
{
  Object[] args;
  Symbol name;
  
  public NamedException(Symbol paramSymbol, Object[] paramArrayOfObject)
  {
    this.name = paramSymbol;
    this.args = paramArrayOfObject;
  }
  
  public Object applyHandler(Object paramObject, Procedure paramProcedure)
    throws Throwable
  {
    checkMatch(paramObject);
    return paramProcedure.applyN(this.args);
  }
  
  public void checkMatch(Object paramObject)
  {
    if ((paramObject != this.name) && (paramObject != Boolean.TRUE)) {
      throw this;
    }
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("#<ERROR");
    int j = 0;
    int k = this.args.length;
    int i = j;
    if (k > 1)
    {
      i = j;
      if (this.args[0] == "misc-error") {
        i = 0 + 1;
      }
    }
    while (i < k)
    {
      localStringBuffer.append(' ');
      localStringBuffer.append(this.args[i]);
      i += 1;
    }
    localStringBuffer.append(">");
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\NamedException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */