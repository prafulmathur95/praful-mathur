package kawa.standard;

import gnu.kawa.functions.ObjectFormat;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.math.IntNum;
import java.io.IOException;
import java.io.PrintWriter;

public class TracedProcedure
  extends ProcedureN
{
  static Symbol curIndentSym = Symbol.makeUninterned("current-indentation");
  static int indentationStep = 2;
  boolean enabled;
  public Procedure proc;
  
  public TracedProcedure(Procedure paramProcedure, boolean paramBoolean)
  {
    this.proc = paramProcedure;
    this.enabled = paramBoolean;
    paramProcedure = paramProcedure.getName();
    if (paramProcedure != null) {
      setName(paramProcedure);
    }
  }
  
  public static Procedure doTrace(Procedure paramProcedure, boolean paramBoolean)
  {
    if ((paramProcedure instanceof TracedProcedure))
    {
      ((TracedProcedure)paramProcedure).enabled = paramBoolean;
      return paramProcedure;
    }
    return new TracedProcedure(paramProcedure, paramBoolean);
  }
  
  static void indent(int paramInt, PrintWriter paramPrintWriter)
  {
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      paramPrintWriter.print(' ');
    }
  }
  
  static void put(Object paramObject, PrintWriter paramPrintWriter)
  {
    try
    {
      if (!ObjectFormat.format(paramObject, paramPrintWriter, 50, true)) {
        paramPrintWriter.print("...");
      }
      return;
    }
    catch (IOException paramObject)
    {
      paramPrintWriter.print("<caught ");
      paramPrintWriter.print(paramObject);
      paramPrintWriter.print('>');
    }
  }
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    if (this.enabled)
    {
      Location localLocation = Environment.getCurrent().getLocation(curIndentSym);
      Object localObject1 = localLocation.get(null);
      int i;
      if (!(localObject1 instanceof IntNum))
      {
        i = 0;
        localLocation.set(IntNum.zero());
      }
      OutPort localOutPort;
      for (;;)
      {
        localOutPort = OutPort.errDefault();
        localObject2 = getName();
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = "??";
        }
        indent(i, localOutPort);
        localOutPort.print("call to ");
        localOutPort.print((String)localObject1);
        int k = paramArrayOfObject.length;
        localOutPort.print(" (");
        int j = 0;
        while (j < k)
        {
          if (j > 0) {
            localOutPort.print(' ');
          }
          put(paramArrayOfObject[j], localOutPort);
          j += 1;
        }
        i = ((IntNum)localObject1).intValue();
      }
      localOutPort.println(")");
      Object localObject2 = localLocation.setWithSave(IntNum.make(indentationStep + i));
      try
      {
        paramArrayOfObject = this.proc.applyN(paramArrayOfObject);
        localLocation.setRestore(localObject2);
        indent(i, localOutPort);
        localOutPort.print("return from ");
        localOutPort.print((String)localObject1);
        localOutPort.print(" => ");
        put(paramArrayOfObject, localOutPort);
        localOutPort.println();
        return paramArrayOfObject;
      }
      catch (RuntimeException paramArrayOfObject)
      {
        indent(i, localOutPort);
        localOutPort.println("procedure " + (String)localObject1 + " throws exception " + paramArrayOfObject);
        throw paramArrayOfObject;
      }
      finally
      {
        localLocation.setRestore(localObject2);
      }
    }
    return this.proc.applyN(paramArrayOfObject);
  }
  
  public void print(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.print("#<procedure ");
    String str = getName();
    if (str == null)
    {
      paramPrintWriter.print("<unnamed>");
      if (!this.enabled) {
        break label45;
      }
    }
    label45:
    for (str = ", traced>";; str = ">")
    {
      paramPrintWriter.print(str);
      return;
      paramPrintWriter.print(str);
      break;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\TracedProcedure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */