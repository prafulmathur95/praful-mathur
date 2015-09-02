package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import kawa.lang.Continuation;

public class CallCC
  extends MethodProc
  implements Inlineable
{
  public static final CallCC callcc = new CallCC();
  
  CallCC()
  {
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyCallCC");
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    Procedure localProcedure = (Procedure)paramCallContext.value1;
    Continuation localContinuation = new Continuation(paramCallContext);
    localProcedure.check1(localContinuation, paramCallContext);
    localProcedure = paramCallContext.proc;
    paramCallContext.proc = null;
    try
    {
      localProcedure.apply(paramCallContext);
      paramCallContext.runUntilDone();
      localContinuation.invoked = true;
      return;
    }
    catch (Throwable localThrowable)
    {
      Continuation.handleException$X(localThrowable, localContinuation, paramCallContext);
    }
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    CompileMisc.compileCallCC(paramApplyExp, paramCompilation, paramTarget, this);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return Type.pointer_type;
  }
  
  public int match1(Object paramObject, CallContext paramCallContext)
  {
    if (!(paramObject instanceof Procedure)) {
      return -786432;
    }
    return super.match1(paramObject, paramCallContext);
  }
  
  public int numArgs()
  {
    return 4097;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\CallCC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */