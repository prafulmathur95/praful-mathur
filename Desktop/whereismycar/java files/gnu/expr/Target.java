package gnu.expr;

import gnu.bytecode.Type;

public abstract class Target
{
  public static final Target Ignore = new IgnoreTarget();
  public static final Target pushObject = new StackTarget(Type.pointer_type);
  
  public static Target pushValue(Type paramType)
  {
    if (paramType.isVoid()) {
      return Ignore;
    }
    return StackTarget.getInstance(paramType);
  }
  
  public abstract void compileFromStack(Compilation paramCompilation, Type paramType);
  
  public abstract Type getType();
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\Target.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */