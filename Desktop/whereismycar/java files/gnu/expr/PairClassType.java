package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;

public class PairClassType
  extends ClassType
{
  public ClassType instanceType;
  Object staticLink;
  
  public PairClassType() {}
  
  PairClassType(Class paramClass1, Class paramClass2)
  {
    super(paramClass1.getName());
    setExisting(true);
    this.reflectClass = paramClass1;
    Type.registerTypeForClass(paramClass1, this);
    this.instanceType = ((ClassType)Type.make(paramClass2));
  }
  
  public static Object extractStaticLink(ClassType paramClassType)
  {
    return ((PairClassType)paramClassType).staticLink;
  }
  
  public static PairClassType make(Class paramClass1, Class paramClass2)
  {
    return new PairClassType(paramClass1, paramClass2);
  }
  
  public static PairClassType make(Class paramClass1, Class paramClass2, Object paramObject)
  {
    paramClass1 = new PairClassType(paramClass1, paramClass2);
    paramClass1.staticLink = paramObject;
    return paramClass1;
  }
  
  public Object getStaticLink()
  {
    return this.staticLink;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\PairClassType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */