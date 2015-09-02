package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;

public class CheckedTarget
  extends StackTarget
{
  static Method initWrongTypeProcMethod;
  static Method initWrongTypeStringMethod;
  static ClassType typeClassCastException;
  static ClassType typeWrongType;
  int argno;
  LambdaExp proc;
  String procname;
  
  public CheckedTarget(Type paramType)
  {
    super(paramType);
    this.argno = -4;
  }
  
  public CheckedTarget(Type paramType, LambdaExp paramLambdaExp, int paramInt)
  {
    super(paramType);
    this.proc = paramLambdaExp;
    this.procname = paramLambdaExp.getName();
    this.argno = paramInt;
  }
  
  public CheckedTarget(Type paramType, String paramString, int paramInt)
  {
    super(paramType);
    this.procname = paramString;
    this.argno = paramInt;
  }
  
  public static void emitCheckedCoerce(Compilation paramCompilation, LambdaExp paramLambdaExp, int paramInt, Type paramType)
  {
    emitCheckedCoerce(paramCompilation, paramLambdaExp, paramLambdaExp.getName(), paramInt, paramType, null);
  }
  
  public static void emitCheckedCoerce(Compilation paramCompilation, LambdaExp paramLambdaExp, int paramInt, Type paramType, Variable paramVariable)
  {
    emitCheckedCoerce(paramCompilation, paramLambdaExp, paramLambdaExp.getName(), paramInt, paramType, paramVariable);
  }
  
  static void emitCheckedCoerce(Compilation paramCompilation, LambdaExp paramLambdaExp, String paramString, int paramInt, Type paramType, Variable paramVariable)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    boolean bool = localCodeAttr.isInTry();
    initWrongType();
    Label localLabel1 = new Label(localCodeAttr);
    Scope localScope;
    if ((paramVariable == null) && (paramType != Type.toStringType))
    {
      localScope = localCodeAttr.pushScope();
      paramVariable = localCodeAttr.addLocal(Type.objectType);
      localCodeAttr.emitDup(1);
      localCodeAttr.emitStore(paramVariable);
    }
    for (;;)
    {
      i = localCodeAttr.getPC();
      localLabel1.define(localCodeAttr);
      emitCoerceFromObject(paramType, paramCompilation);
      if ((localCodeAttr.getPC() != i) && (paramType != Type.toStringType)) {
        break;
      }
      if (localScope != null) {
        localCodeAttr.popScope();
      }
      return;
      localScope = null;
    }
    Label localLabel2 = new Label(localCodeAttr);
    localLabel2.define(localCodeAttr);
    paramType = new Label(localCodeAttr);
    paramType.setTypes(localCodeAttr);
    if (bool) {
      localCodeAttr.emitGoto(paramType);
    }
    int i = 0;
    localCodeAttr.setUnreachable();
    if (!bool) {
      i = localCodeAttr.beginFragment(paramType);
    }
    localCodeAttr.addHandler(localLabel1, localLabel2, typeClassCastException);
    int k = 0;
    int j = k;
    if (paramLambdaExp != null)
    {
      j = k;
      if (paramLambdaExp.isClassGenerated())
      {
        j = k;
        if (!paramCompilation.method.getStaticFlag())
        {
          j = k;
          if (paramCompilation.method.getDeclaringClass() == paramLambdaExp.getCompiledClassType(paramCompilation)) {
            j = 1;
          }
        }
      }
    }
    k = paramCompilation.getLineNumber();
    if (k > 0) {
      localCodeAttr.putLineNumber(k);
    }
    localCodeAttr.emitNew(typeWrongType);
    localCodeAttr.emitDupX();
    localCodeAttr.emitSwap();
    if (j != 0)
    {
      localCodeAttr.emitPushThis();
      localCodeAttr.emitPushInt(paramInt);
      localCodeAttr.emitLoad(paramVariable);
      if (j == 0) {
        break label395;
      }
    }
    label395:
    for (paramCompilation = initWrongTypeProcMethod;; paramCompilation = initWrongTypeStringMethod)
    {
      localCodeAttr.emitInvokeSpecial(paramCompilation);
      if (localScope != null) {
        localCodeAttr.popScope();
      }
      localCodeAttr.emitThrow();
      if (!bool) {
        break label402;
      }
      paramType.define(localCodeAttr);
      return;
      paramCompilation = paramString;
      if (paramString == null)
      {
        paramCompilation = paramString;
        if (paramInt != -4) {
          paramCompilation = "lambda";
        }
      }
      localCodeAttr.emitPushString(paramCompilation);
      break;
    }
    label402:
    localCodeAttr.endFragment(i);
  }
  
  public static void emitCheckedCoerce(Compilation paramCompilation, String paramString, int paramInt, Type paramType)
  {
    emitCheckedCoerce(paramCompilation, null, paramString, paramInt, paramType, null);
  }
  
  public static Target getInstance(Type paramType)
  {
    if (paramType == Type.objectType) {
      return Target.pushObject;
    }
    return new CheckedTarget(paramType);
  }
  
  public static Target getInstance(Type paramType, LambdaExp paramLambdaExp, int paramInt)
  {
    if (paramType == Type.objectType) {
      return Target.pushObject;
    }
    return new CheckedTarget(paramType, paramLambdaExp, paramInt);
  }
  
  public static Target getInstance(Type paramType, String paramString, int paramInt)
  {
    if (paramType == Type.objectType) {
      return Target.pushObject;
    }
    return new CheckedTarget(paramType, paramString, paramInt);
  }
  
  public static Target getInstance(Declaration paramDeclaration)
  {
    return getInstance(paramDeclaration.getType(), paramDeclaration.getName(), -2);
  }
  
  private static void initWrongType()
  {
    if (typeClassCastException == null) {
      typeClassCastException = ClassType.make("java.lang.ClassCastException");
    }
    if (typeWrongType == null)
    {
      typeWrongType = ClassType.make("gnu.mapping.WrongType");
      ClassType localClassType1 = typeClassCastException;
      ClassType localClassType2 = Compilation.javaStringType;
      PrimType localPrimType1 = Type.intType;
      ClassType localClassType3 = Type.objectType;
      ClassType localClassType4 = typeWrongType;
      PrimType localPrimType2 = Type.voidType;
      initWrongTypeStringMethod = localClassType4.addMethod("<init>", 1, new Type[] { localClassType1, localClassType2, localPrimType1, localClassType3 }, localPrimType2);
      localClassType1 = typeClassCastException;
      localClassType2 = Compilation.typeProcedure;
      localPrimType1 = Type.intType;
      localClassType3 = Type.objectType;
      localClassType4 = typeWrongType;
      localPrimType2 = Type.voidType;
      initWrongTypeProcMethod = localClassType4.addMethod("<init>", 1, new Type[] { localClassType1, localClassType2, localPrimType1, localClassType3 }, localPrimType2);
    }
  }
  
  public void compileFromStack(Compilation paramCompilation, Type paramType)
  {
    if (!compileFromStack0(paramCompilation, paramType)) {
      emitCheckedCoerce(paramCompilation, this.proc, this.procname, this.argno, this.type, null);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\CheckedTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */