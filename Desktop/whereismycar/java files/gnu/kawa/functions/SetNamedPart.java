package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.kawa.reflect.SlotSet;
import gnu.mapping.Environment;
import gnu.mapping.HasSetter;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure3;
import gnu.mapping.Symbol;
import gnu.mapping.Values;

public class SetNamedPart
  extends Procedure3
  implements HasSetter
{
  public static final SetNamedPart setNamedPart = new SetNamedPart();
  
  static
  {
    setNamedPart.setName("setNamedPart");
    setNamedPart.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateSetNamedPart");
  }
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Object localObject = paramObject1;
    if ((paramObject1 instanceof Namespace))
    {
      paramObject1 = (Namespace)paramObject1;
      localObject = ((Namespace)paramObject1).getName();
      if (((String)localObject).startsWith("class:")) {
        localObject = ClassType.make(((String)localObject).substring(6));
      }
    }
    else
    {
      paramObject1 = localObject;
      if ((localObject instanceof Class)) {
        paramObject1 = (ClassType)Type.make((Class)localObject);
      }
      if (!(paramObject1 instanceof ClassType)) {
        break label117;
      }
    }
    try
    {
      SlotSet.setStaticField(paramObject1, paramObject2.toString(), paramObject3);
      localObject = Values.empty;
      return localObject;
    }
    catch (Throwable localThrowable) {}
    paramObject1 = ((Namespace)paramObject1).getSymbol(paramObject2.toString());
    Environment.getCurrent();
    Environment.getCurrent().put((Symbol)paramObject1, paramObject3);
    return Values.empty;
    label117:
    SlotSet.setField(paramObject1, paramObject2.toString(), paramObject3);
    return Values.empty;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\SetNamedPart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */