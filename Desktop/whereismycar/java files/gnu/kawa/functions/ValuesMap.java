package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class ValuesMap
  extends MethodProc
  implements Inlineable
{
  public static final ValuesMap valuesMap = new ValuesMap(-1);
  public static final ValuesMap valuesMapWithPos = new ValuesMap(1);
  private final int startCounter;
  
  private ValuesMap(int paramInt)
  {
    this.startCounter = paramInt;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyValuesMap");
  }
  
  static LambdaExp canInline(ApplyExp paramApplyExp, ValuesMap paramValuesMap)
  {
    int i = 2;
    paramApplyExp = paramApplyExp.getArgs();
    if (paramApplyExp.length == 2)
    {
      paramApplyExp = paramApplyExp[0];
      if ((paramApplyExp instanceof LambdaExp))
      {
        paramApplyExp = (LambdaExp)paramApplyExp;
        if (paramApplyExp.min_args == paramApplyExp.max_args)
        {
          int j = paramApplyExp.min_args;
          if (paramValuesMap.startCounter >= 0) {}
          while (j == i)
          {
            return paramApplyExp;
            i = 1;
          }
        }
      }
    }
    return null;
  }
  
  public static void compileInlined(LambdaExp paramLambdaExp, Expression paramExpression, int paramInt, Method paramMethod, Compilation paramCompilation, Target paramTarget)
  {
    Object localObject2 = paramLambdaExp.firstDecl();
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Object localObject1 = localCodeAttr.pushScope();
    Type localType = ((Declaration)localObject2).getType();
    Object localObject3;
    label89:
    Object localObject4;
    if (paramInt >= 0)
    {
      localObject1 = ((Scope)localObject1).addVariable(localCodeAttr, Type.intType, "position");
      localCodeAttr.emitPushInt(paramInt);
      localCodeAttr.emitStore((Variable)localObject1);
      localObject3 = new Declaration((Variable)localObject1);
      if ((!((Declaration)localObject2).isSimple()) || (paramMethod != null)) {
        break label451;
      }
      ((Declaration)localObject2).allocateVariable(localCodeAttr);
      if (paramInt < 0) {
        break label485;
      }
      localObject4 = new Expression[2];
      localObject4[0] = new ReferenceExp((Declaration)localObject2);
      localObject4[1] = new ReferenceExp((Declaration)localObject3);
    }
    for (;;)
    {
      localObject4 = new ApplyExp(paramLambdaExp, (Expression[])localObject4);
      paramLambdaExp = (LambdaExp)localObject4;
      if (paramMethod != null)
      {
        paramLambdaExp = (LambdaExp)localObject4;
        if (((Expression)localObject4).getType().getImplementationType() != Type.booleanType) {
          paramLambdaExp = new ApplyExp(paramMethod, new Expression[] { localObject4, new ReferenceExp((Declaration)localObject3) });
        }
        paramLambdaExp = new IfExp(paramLambdaExp, new ReferenceExp((Declaration)localObject2), QuoteExp.voidExp);
      }
      paramMethod = localCodeAttr.addLocal(Type.intType);
      localObject3 = localCodeAttr.addLocal(Type.pointer_type);
      localObject4 = localCodeAttr.addLocal(Type.intType);
      paramExpression.compileWithPosition(paramCompilation, Target.pushObject);
      localCodeAttr.emitStore((Variable)localObject3);
      localCodeAttr.emitPushInt(0);
      localCodeAttr.emitStore(paramMethod);
      paramExpression = new Label(localCodeAttr);
      Label localLabel = new Label(localCodeAttr);
      paramExpression.define(localCodeAttr);
      localCodeAttr.emitLoad((Variable)localObject3);
      localCodeAttr.emitLoad(paramMethod);
      localCodeAttr.emitInvokeStatic(Compilation.typeValues.getDeclaredMethod("nextIndex", 2));
      localCodeAttr.emitDup(Type.intType);
      localCodeAttr.emitStore((Variable)localObject4);
      localCodeAttr.emitGotoIfIntLtZero(localLabel);
      localCodeAttr.emitLoad((Variable)localObject3);
      localCodeAttr.emitLoad(paramMethod);
      localCodeAttr.emitInvokeStatic(Compilation.typeValues.getDeclaredMethod("nextValue", 2));
      StackTarget.convert(paramCompilation, Type.objectType, localType);
      ((Declaration)localObject2).compileStore(paramCompilation);
      paramLambdaExp.compile(paramCompilation, paramTarget);
      if (paramInt >= 0) {
        localCodeAttr.emitInc((Variable)localObject1, (short)1);
      }
      localCodeAttr.emitLoad((Variable)localObject4);
      localCodeAttr.emitStore(paramMethod);
      localCodeAttr.emitGoto(paramExpression);
      localLabel.define(localCodeAttr);
      localCodeAttr.popScope();
      return;
      localObject1 = null;
      localObject3 = null;
      break;
      label451:
      localObject2 = Compilation.mangleNameIfNeeded(((Declaration)localObject2).getName());
      localObject2 = new Declaration(localCodeAttr.addLocal(localType.getImplementationType(), (String)localObject2));
      break label89;
      label485:
      localObject4 = new Expression[1];
      localObject4[0] = new ReferenceExp((Declaration)localObject2);
    }
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    Procedure localProcedure = (Procedure)paramCallContext.getNextArg();
    Object localObject1 = paramCallContext.consumer;
    localObject1 = paramCallContext.getNextArg();
    Procedure.checkArgCount(localProcedure, 1);
    if ((localObject1 instanceof Values))
    {
      int j = 0;
      int i = this.startCounter;
      localObject1 = (Values)localObject1;
      j = ((Values)localObject1).nextPos(j);
      if (j != 0)
      {
        Object localObject2 = ((Values)localObject1).getPosPrevious(j);
        if (this.startCounter >= 0)
        {
          localProcedure.check2(localObject2, IntNum.make(i), paramCallContext);
          i += 1;
        }
        for (;;)
        {
          paramCallContext.runUntilDone();
          break;
          localProcedure.check1(localObject2, paramCallContext);
        }
      }
    }
    else
    {
      if (this.startCounter < 0) {
        break label132;
      }
      localProcedure.check2(localObject1, IntNum.make(this.startCounter), paramCallContext);
    }
    for (;;)
    {
      paramCallContext.runUntilDone();
      return;
      label132:
      localProcedure.check1(localObject1, paramCallContext);
    }
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    LambdaExp localLambdaExp = canInline(paramApplyExp, this);
    if (localLambdaExp == null)
    {
      ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
      return;
    }
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if ((!(paramTarget instanceof IgnoreTarget)) && (!(paramTarget instanceof ConsumerTarget)))
    {
      ConsumerTarget.compileUsingConsumer(paramApplyExp, paramCompilation, paramTarget);
      return;
    }
    compileInlined(localLambdaExp, arrayOfExpression[1], this.startCounter, null, paramCompilation, paramTarget);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return Type.pointer_type;
  }
  
  public int numArgs()
  {
    return 8194;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\ValuesMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */