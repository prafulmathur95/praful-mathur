package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;

public class ClassInitializer
  extends Initializer
{
  ClassExp cexp;
  
  public ClassInitializer(ClassExp paramClassExp, Compilation paramCompilation)
  {
    this.field = paramClassExp.allocFieldFor(paramCompilation);
    paramClassExp.compileMembers(paramCompilation);
    this.cexp = paramClassExp;
    if (this.field.getStaticFlag())
    {
      this.next = paramCompilation.clinitChain;
      paramCompilation.clinitChain = this;
      return;
    }
    paramClassExp = paramClassExp.getOwningLambda();
    this.next = paramClassExp.initChain;
    paramClassExp.initChain = this;
  }
  
  public void emit(Compilation paramCompilation)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    if (!this.field.getStaticFlag()) {
      localCodeAttr.emitPushThis();
    }
    this.cexp.compilePushClass(paramCompilation, Target.pushValue(Compilation.typeClassType));
    if (this.field.getStaticFlag())
    {
      localCodeAttr.emitPutStatic(this.field);
      return;
    }
    localCodeAttr.emitPutField(this.field);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ClassInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */