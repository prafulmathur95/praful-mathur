package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.expr.Compilation;
import gnu.lists.TreePosition;
import gnu.math.IntNum;

public final class Focus
  extends TreePosition
{
  public static final ClassType TYPE = ClassType.make("gnu.kawa.xml.Focus");
  static ThreadLocal current = new ThreadLocal();
  static final Method getCurrentFocusMethod = TYPE.getDeclaredMethod("getCurrent", 0);
  IntNum contextPosition;
  public long position;
  
  public static void compileGetCurrent(Compilation paramCompilation)
  {
    paramCompilation.getCode().emitInvoke(getCurrentFocusMethod);
  }
  
  public static Focus getCurrent()
  {
    Object localObject2 = current.get();
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new Focus();
      current.set(localObject1);
    }
    return (Focus)localObject1;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\Focus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */