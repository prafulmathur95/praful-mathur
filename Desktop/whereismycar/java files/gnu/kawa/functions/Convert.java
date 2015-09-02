package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.mapping.LazyPropertyKey;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;

public class Convert
  extends Procedure2
{
  public static final Convert as = new Convert();
  
  static
  {
    as.setName("as");
    as.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyConvert");
    Procedure.compilerKey.set(as, "*gnu.kawa.functions.CompileMisc:forConvert");
  }
  
  public static Convert getInstance()
  {
    return as;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 instanceof Class)) {}
    for (paramObject1 = Type.make((Class)paramObject1);; paramObject1 = (Type)paramObject1) {
      return ((Type)paramObject1).coerceFromObject(paramObject2);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\Convert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */