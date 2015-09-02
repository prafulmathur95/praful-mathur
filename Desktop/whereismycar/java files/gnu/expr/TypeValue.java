package gnu.expr;

import gnu.bytecode.Variable;
import gnu.mapping.Procedure;

public abstract interface TypeValue
  extends java.lang.reflect.Type
{
  public abstract Expression convertValue(Expression paramExpression);
  
  public abstract void emitIsInstance(Variable paramVariable, Compilation paramCompilation, Target paramTarget);
  
  public abstract void emitTestIf(Variable paramVariable, Declaration paramDeclaration, Compilation paramCompilation);
  
  public abstract Procedure getConstructor();
  
  public abstract gnu.bytecode.Type getImplementationType();
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\TypeValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */