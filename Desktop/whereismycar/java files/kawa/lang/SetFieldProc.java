package kawa.lang;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class SetFieldProc
  extends Procedure2
  implements Inlineable
{
  ClassType ctype;
  gnu.bytecode.Field field;
  
  public SetFieldProc(ClassType paramClassType, String paramString)
  {
    this.ctype = paramClassType;
    this.field = gnu.bytecode.Field.searchField(paramClassType.getFields(), paramString);
  }
  
  public SetFieldProc(ClassType paramClassType, String paramString, Type paramType, int paramInt)
  {
    this.ctype = paramClassType;
    this.field = paramClassType.getField(paramString);
    if (this.field == null) {
      this.field = paramClassType.addField(paramString, paramType, paramInt);
    }
  }
  
  public SetFieldProc(Class paramClass, String paramString)
  {
    this((ClassType)Type.make(paramClass), paramString);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
  {
    try
    {
      this.field.getReflectField().set(paramObject1, this.field.getType().coerceFromObject(paramObject2));
      return Values.empty;
    }
    catch (NoSuchFieldException paramObject1)
    {
      throw new RuntimeException("no such field " + this.field.getSourceName() + " in " + this.ctype.getName());
    }
    catch (IllegalAccessException paramObject1)
    {
      throw new RuntimeException("illegal access for field " + this.field.getSourceName());
    }
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    if ((this.ctype.getReflectClass().getClassLoader() instanceof ArrayClassLoader))
    {
      ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
      return;
    }
    paramApplyExp = paramApplyExp.getArgs();
    paramApplyExp[0].compile(paramCompilation, this.ctype);
    paramApplyExp[1].compile(paramCompilation, this.field.getType());
    paramCompilation.getCode().emitPutField(this.field);
    paramCompilation.compileConstant(Values.empty, paramTarget);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return Type.voidType;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\SetFieldProc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */