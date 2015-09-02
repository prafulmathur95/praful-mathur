package gnu.q2.lang;

import gnu.bytecode.Type;
import gnu.expr.Special;
import gnu.kawa.reflect.Invoke;
import gnu.lists.Consumable;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import java.util.Vector;

public class Q2Apply
  extends MethodProc
{
  public static Q2Apply q2Apply = new Q2Apply();
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    Special localSpecial = Special.dfault;
    Object localObject2 = paramCallContext.getNextArg(localSpecial);
    Object localObject1;
    if ((!(localObject2 instanceof Procedure)) && (!(localObject2 instanceof Type)))
    {
      localObject1 = localObject2;
      if (!(localObject2 instanceof Class)) {}
    }
    else
    {
      Vector localVector = new Vector();
      if ((localObject2 instanceof Procedure)) {
        localObject1 = (Procedure)localObject2;
      }
      for (;;)
      {
        localObject2 = paramCallContext.getNextArg(localSpecial);
        if (localObject2 == localSpecial)
        {
          localObject1 = ((Procedure)localObject1).applyN(localVector.toArray());
          if (!(localObject1 instanceof Consumable)) {
            break;
          }
          ((Consumable)localObject1).consume(paramCallContext.consumer);
          return;
          localVector.add(localObject2);
          localObject1 = Invoke.make;
        }
        else if ((localObject2 instanceof Values))
        {
          localObject2 = ((Values)localObject2).getValues();
          int i = 0;
          while (i < localObject2.length)
          {
            localVector.add(localObject2[i]);
            i += 1;
          }
        }
        else
        {
          localVector.add(localObject2);
        }
      }
      paramCallContext.writeValue(localObject1);
      return;
      if (!(localObject1 instanceof Consumable)) {
        break label208;
      }
      ((Consumable)localObject1).consume(paramCallContext.consumer);
    }
    for (;;)
    {
      localObject1 = paramCallContext.getNextArg(localSpecial);
      if (localObject1 != localSpecial) {
        break;
      }
      return;
      label208:
      paramCallContext.writeValue(localObject1);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\q2\lang\Q2Apply.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */