package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;

public class ThisExp
  extends ReferenceExp
{
  static int EVAL_TO_CONTEXT = 2;
  public static final String THIS_NAME = new String("$this$");
  ScopeExp context;
  
  public ThisExp()
  {
    super(THIS_NAME);
  }
  
  public ThisExp(ClassType paramClassType)
  {
    this(new Declaration(THIS_NAME, paramClassType));
  }
  
  public ThisExp(Declaration paramDeclaration)
  {
    super(THIS_NAME, paramDeclaration);
  }
  
  public ThisExp(ScopeExp paramScopeExp)
  {
    super(THIS_NAME);
    this.context = paramScopeExp;
  }
  
  public static ThisExp makeGivingContext(ScopeExp paramScopeExp)
  {
    paramScopeExp = new ThisExp(paramScopeExp);
    paramScopeExp.flags |= EVAL_TO_CONTEXT;
    return paramScopeExp;
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    if (isForContext())
    {
      paramCallContext.writeValue(this.context);
      return;
    }
    super.apply(paramCallContext);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    if ((paramTarget instanceof IgnoreTarget)) {
      return;
    }
    if (isForContext())
    {
      CodeAttr localCodeAttr = paramCompilation.getCode();
      if (paramCompilation.method.getStaticFlag()) {
        localCodeAttr.emitGetStatic(paramCompilation.moduleInstanceMainField);
      }
      for (;;)
      {
        paramTarget.compileFromStack(paramCompilation, getType());
        return;
        localCodeAttr.emitPushThis();
      }
    }
    super.compile(paramCompilation, paramTarget);
  }
  
  public ScopeExp getContextScope()
  {
    return this.context;
  }
  
  public final Type getType()
  {
    if (this.binding != null) {
      return this.binding.getType();
    }
    if (((this.context instanceof ClassExp)) || ((this.context instanceof ModuleExp))) {
      return this.context.getType();
    }
    return Type.pointer_type;
  }
  
  public final boolean isForContext()
  {
    return (this.flags & EVAL_TO_CONTEXT) != 0;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitThisExp(this, paramD);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ThisExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */