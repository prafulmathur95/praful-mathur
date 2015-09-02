package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.text.Printable;

public abstract class Pattern
  implements Printable
{
  private static Type[] matchArgs = { Type.pointer_type, Compilation.objArrayType, Type.intType };
  public static final Method matchPatternMethod = typePattern.addMethod("match", matchArgs, Type.booleanType, 1);
  public static ClassType typePattern = ClassType.make("kawa.lang.Pattern");
  
  public abstract boolean match(Object paramObject, Object[] paramArrayOfObject, int paramInt);
  
  public Object[] match(Object paramObject)
  {
    Object[] arrayOfObject = new Object[varCount()];
    if (match(paramObject, arrayOfObject, 0)) {
      return arrayOfObject;
    }
    return null;
  }
  
  public abstract int varCount();
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\Pattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */