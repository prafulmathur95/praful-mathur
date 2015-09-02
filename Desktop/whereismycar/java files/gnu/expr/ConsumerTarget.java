package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.reflect.OccurrenceType;

public class ConsumerTarget
  extends Target
{
  Variable consumer;
  boolean isContextTarget;
  
  public ConsumerTarget(Variable paramVariable)
  {
    this.consumer = paramVariable;
  }
  
  public static void compileUsingConsumer(Expression paramExpression, Compilation paramCompilation, Target paramTarget)
  {
    if (((paramTarget instanceof ConsumerTarget)) || ((paramTarget instanceof IgnoreTarget)))
    {
      paramExpression.compile(paramCompilation, paramTarget);
      return;
    }
    ClassType localClassType = Compilation.typeValues;
    compileUsingConsumer(paramExpression, paramCompilation, paramTarget, localClassType.getDeclaredMethod("make", 0), localClassType.getDeclaredMethod("canonicalize", 0));
  }
  
  public static void compileUsingConsumer(Expression paramExpression, Compilation paramCompilation, Target paramTarget, Method paramMethod1, Method paramMethod2)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Scope localScope = localCodeAttr.pushScope();
    Object localObject1;
    if (paramMethod1.getName() == "<init>")
    {
      Object localObject2 = paramMethod1.getDeclaringClass();
      localObject1 = localObject2;
      localCodeAttr.emitNew((ClassType)localObject2);
      localCodeAttr.emitDup((Type)localObject1);
      localCodeAttr.emitInvoke(paramMethod1);
      paramMethod1 = (Method)localObject1;
      localObject1 = localScope.addVariable(localCodeAttr, paramMethod1, null);
      localObject2 = new ConsumerTarget((Variable)localObject1);
      localCodeAttr.emitStore((Variable)localObject1);
      paramExpression.compile(paramCompilation, (Target)localObject2);
      localCodeAttr.emitLoad((Variable)localObject1);
      if (paramMethod2 != null) {
        localCodeAttr.emitInvoke(paramMethod2);
      }
      localCodeAttr.popScope();
      if (paramMethod2 != null) {
        break label146;
      }
    }
    for (;;)
    {
      paramTarget.compileFromStack(paramCompilation, paramMethod1);
      return;
      localObject1 = paramMethod1.getReturnType();
      localCodeAttr.emitInvokeStatic(paramMethod1);
      paramMethod1 = (Method)localObject1;
      break;
      label146:
      paramMethod1 = paramMethod2.getReturnType();
    }
  }
  
  public static Target makeContextTarget(Compilation paramCompilation)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    paramCompilation.loadCallContext();
    localCodeAttr.emitGetField(Compilation.typeCallContext.getDeclaredField("consumer"));
    paramCompilation = localCodeAttr.getCurrentScope().addVariable(localCodeAttr, Compilation.typeConsumer, "$result");
    localCodeAttr.emitStore(paramCompilation);
    paramCompilation = new ConsumerTarget(paramCompilation);
    paramCompilation.isContextTarget = true;
    return paramCompilation;
  }
  
  public void compileFromStack(Compilation paramCompilation, Type paramType)
  {
    compileFromStack(paramCompilation, paramType, -1);
  }
  
  void compileFromStack(Compilation paramCompilation, Type paramType, int paramInt)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Object localObject1 = null;
    Object localObject2 = null;
    paramCompilation = null;
    int i = 0;
    Type localType = paramType.getImplementationType();
    int j;
    if ((localType instanceof PrimType))
    {
      j = localType.getSignature().charAt(0);
      switch (j)
      {
      default: 
        paramType = (Type)localObject1;
        if (paramInt < 0) {
          break;
        }
      }
    }
    for (;;)
    {
      localObject1 = localObject2;
      if (0 == 0)
      {
        localObject1 = localObject2;
        if (paramType != null) {
          localObject1 = Compilation.typeConsumer.getDeclaredMethod(paramType, new Type[] { paramCompilation });
        }
      }
      if (localObject1 != null) {
        localCodeAttr.emitInvokeInterface((Method)localObject1);
      }
      if (j == 67) {
        localCodeAttr.emitPop(1);
      }
      return;
      paramType = "writeInt";
      paramCompilation = Type.intType;
      break;
      paramType = "writeLong";
      paramCompilation = Type.longType;
      i = 1;
      break;
      paramType = "writeFloat";
      paramCompilation = Type.floatType;
      break;
      paramType = "writeDouble";
      paramCompilation = Type.doubleType;
      i = 1;
      break;
      paramType = "append";
      paramCompilation = Type.charType;
      break;
      paramType = "writeBoolean";
      paramCompilation = Type.booleanType;
      break;
      j = 0;
      if ((paramInt == 1) || (OccurrenceType.itemCountIsOne(localType)))
      {
        paramType = "writeObject";
        paramCompilation = Type.pointer_type;
        break;
      }
      paramCompilation = Compilation.typeValues.getDeclaredMethod("writeValues", 2);
      localCodeAttr.emitLoad(this.consumer);
      if (paramInt == 0) {
        localCodeAttr.emitSwap();
      }
      localCodeAttr.emitInvokeStatic(paramCompilation);
      return;
      if (i != 0)
      {
        localCodeAttr.pushScope();
        localObject1 = localCodeAttr.addLocal(localType);
        localCodeAttr.emitStore((Variable)localObject1);
        localCodeAttr.emitLoad(this.consumer);
        localCodeAttr.emitLoad((Variable)localObject1);
        localCodeAttr.popScope();
      }
      else
      {
        localCodeAttr.emitLoad(this.consumer);
        localCodeAttr.emitSwap();
      }
    }
  }
  
  public boolean compileWrite(Expression paramExpression, Compilation paramCompilation)
  {
    Type localType = paramExpression.getType().getImplementationType();
    if ((((localType instanceof PrimType)) && (!localType.isVoid())) || (OccurrenceType.itemCountIsOne(localType)))
    {
      paramCompilation.getCode().emitLoad(this.consumer);
      paramExpression.compile(paramCompilation, StackTarget.getInstance(localType));
      compileFromStack(paramCompilation, localType, 1);
      return true;
    }
    return false;
  }
  
  public Variable getConsumerVariable()
  {
    return this.consumer;
  }
  
  public Type getType()
  {
    return Compilation.scmSequenceType;
  }
  
  public final boolean isContextTarget()
  {
    return this.isContextTarget;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ConsumerTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */