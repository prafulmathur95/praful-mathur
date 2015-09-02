package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;

public class InstanceOf
  extends Procedure2
  implements Inlineable
{
  static Method instanceMethod;
  static ClassType typeType;
  protected Language language;
  
  public InstanceOf(Language paramLanguage)
  {
    this.language = paramLanguage;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplyInstanceOf");
  }
  
  public InstanceOf(Language paramLanguage, String paramString)
  {
    this(paramLanguage);
    setName(paramString);
  }
  
  public static void emitIsInstance(TypeValue paramTypeValue, Variable paramVariable, Compilation paramCompilation, Target paramTarget)
  {
    paramVariable = paramCompilation.getCode();
    paramTypeValue.emitTestIf(null, null, paramCompilation);
    paramTypeValue = null;
    if ((paramTarget instanceof ConditionalTarget))
    {
      paramTypeValue = (ConditionalTarget)paramTarget;
      paramVariable.emitGoto(paramTypeValue.ifTrue);
      paramVariable.emitElse();
      if (paramTypeValue == null) {
        break label84;
      }
      paramVariable.emitGoto(paramTypeValue.ifFalse);
    }
    for (;;)
    {
      paramVariable.emitFi();
      if (paramTypeValue == null) {
        paramTarget.compileFromStack(paramCompilation, paramCompilation.getLanguage().getTypeFor(Boolean.TYPE));
      }
      return;
      paramVariable.emitPushInt(1);
      break;
      label84:
      paramVariable.emitPushInt(0);
    }
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
  {
    Type localType = this.language.asType(paramObject2);
    paramObject2 = localType;
    if ((localType instanceof PrimType)) {
      paramObject2 = ((PrimType)localType).boxedType();
    }
    return this.language.booleanObject(((Type)paramObject2).isInstance(paramObject1));
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    CodeAttr localCodeAttr = paramCompilation.getCode();
    paramApplyExp = null;
    Object localObject = arrayOfExpression[1];
    if ((localObject instanceof QuoteExp)) {}
    for (;;)
    {
      try
      {
        localObject = this.language.asType(((QuoteExp)localObject).getValue());
        paramApplyExp = (ApplyExp)localObject;
      }
      catch (Exception localException)
      {
        paramCompilation.error('w', "unknown type spec: " + null);
        continue;
      }
      if (paramApplyExp == null) {
        break label176;
      }
      localObject = paramApplyExp;
      if ((paramApplyExp instanceof PrimType)) {
        localObject = ((PrimType)paramApplyExp).boxedType();
      }
      arrayOfExpression[0].compile(paramCompilation, Target.pushObject);
      if (!(localObject instanceof TypeValue)) {
        break;
      }
      ((TypeValue)localObject).emitIsInstance(null, paramCompilation, paramTarget);
      return;
      paramApplyExp = this.language.getTypeFor(localException);
    }
    localException.emitIsInstance(localCodeAttr);
    paramCompilation.usedClass(localException);
    for (;;)
    {
      paramTarget.compileFromStack(paramCompilation, this.language.getTypeFor(Boolean.TYPE));
      return;
      label176:
      if (typeType == null)
      {
        typeType = ClassType.make("gnu.bytecode.Type");
        instanceMethod = typeType.addMethod("isInstance", Compilation.apply1args, Type.boolean_type, 1);
      }
      arrayOfExpression[1].compile(paramCompilation, typeType);
      arrayOfExpression[0].compile(paramCompilation, Target.pushObject);
      localCodeAttr.emitInvokeVirtual(instanceMethod);
    }
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return this.language.getTypeFor(Boolean.TYPE);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\InstanceOf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */