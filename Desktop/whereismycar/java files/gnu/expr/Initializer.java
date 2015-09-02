package gnu.expr;

import gnu.bytecode.Field;

public abstract class Initializer
{
  public Field field;
  Initializer next;
  
  public static Initializer reverse(Initializer paramInitializer)
  {
    Initializer localInitializer1 = null;
    while (paramInitializer != null)
    {
      Initializer localInitializer2 = paramInitializer.next;
      paramInitializer.next = localInitializer1;
      localInitializer1 = paramInitializer;
      paramInitializer = localInitializer2;
    }
    return localInitializer1;
  }
  
  public abstract void emit(Compilation paramCompilation);
  
  public void reportError(String paramString, Compilation paramCompilation)
  {
    paramCompilation.error('e', paramString + "field " + this.field);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\Initializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */