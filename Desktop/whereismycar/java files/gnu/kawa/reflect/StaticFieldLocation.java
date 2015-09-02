package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.expr.Declaration;
import gnu.mapping.Environment;
import gnu.mapping.Symbol;
import kawa.lang.Macro;

public class StaticFieldLocation
  extends FieldLocation
{
  public StaticFieldLocation(ClassType paramClassType, String paramString)
  {
    super(null, paramClassType, paramString);
  }
  
  public StaticFieldLocation(String paramString1, String paramString2)
  {
    super(null, ClassType.make(paramString1), paramString2);
  }
  
  public StaticFieldLocation(java.lang.reflect.Field paramField)
  {
    super(null, paramField);
  }
  
  public static StaticFieldLocation define(Environment paramEnvironment, Symbol paramSymbol, Object paramObject, String paramString1, String paramString2)
  {
    paramString1 = new StaticFieldLocation(paramString1, paramString2);
    paramEnvironment.addLocation(paramSymbol, paramObject, paramString1);
    return paramString1;
  }
  
  public static StaticFieldLocation make(Declaration paramDeclaration)
  {
    Object localObject = paramDeclaration.field;
    localObject = new StaticFieldLocation(((gnu.bytecode.Field)localObject).getDeclaringClass(), ((gnu.bytecode.Field)localObject).getName());
    ((StaticFieldLocation)localObject).setDeclaration(paramDeclaration);
    return (StaticFieldLocation)localObject;
  }
  
  public static StaticFieldLocation make(String paramString1, String paramString2)
  {
    return new StaticFieldLocation(paramString1, paramString2);
  }
  
  public Object get(Object paramObject)
  {
    paramObject = super.get(paramObject);
    if ((paramObject instanceof Macro)) {
      getDeclaration();
    }
    return paramObject;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\StaticFieldLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */