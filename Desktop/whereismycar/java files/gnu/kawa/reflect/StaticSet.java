package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;

public class StaticSet
  extends Procedure1
  implements Inlineable
{
  ClassType ctype;
  gnu.bytecode.Field field;
  String fname;
  java.lang.reflect.Field reflectField;
  
  public StaticSet(ClassType paramClassType, String paramString, Type paramType, int paramInt)
  {
    this.ctype = paramClassType;
    this.fname = paramString;
    this.field = paramClassType.getField(paramString);
    if (this.field == null) {
      this.field = paramClassType.addField(paramString, paramType, paramInt);
    }
  }
  
  StaticSet(Class paramClass, String paramString)
  {
    this.ctype = ((ClassType)Type.make(paramClass));
    this.fname = paramString;
  }
  
  /* Error */
  public Object apply1(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 51	gnu/kawa/reflect/StaticSet:reflectField	Ljava/lang/reflect/Field;
    //   4: ifnonnull +23 -> 27
    //   7: aload_0
    //   8: getfield 21	gnu/kawa/reflect/StaticSet:ctype	Lgnu/bytecode/ClassType;
    //   11: invokevirtual 55	gnu/bytecode/ClassType:getReflectClass	()Ljava/lang/Class;
    //   14: astore_2
    //   15: aload_0
    //   16: aload_2
    //   17: aload_0
    //   18: getfield 23	gnu/kawa/reflect/StaticSet:fname	Ljava/lang/String;
    //   21: invokevirtual 60	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   24: putfield 51	gnu/kawa/reflect/StaticSet:reflectField	Ljava/lang/reflect/Field;
    //   27: aload_0
    //   28: getfield 51	gnu/kawa/reflect/StaticSet:reflectField	Ljava/lang/reflect/Field;
    //   31: aconst_null
    //   32: aload_1
    //   33: invokevirtual 66	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   36: getstatic 72	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   39: astore_1
    //   40: aload_1
    //   41: areturn
    //   42: astore_1
    //   43: new 74	java/lang/RuntimeException
    //   46: dup
    //   47: new 76	java/lang/StringBuilder
    //   50: dup
    //   51: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   54: ldc 79
    //   56: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: aload_0
    //   60: getfield 23	gnu/kawa/reflect/StaticSet:fname	Ljava/lang/String;
    //   63: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: ldc 85
    //   68: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: aload_2
    //   72: invokevirtual 89	java/lang/Class:getName	()Ljava/lang/String;
    //   75: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   81: invokespecial 95	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   84: athrow
    //   85: astore_1
    //   86: new 74	java/lang/RuntimeException
    //   89: dup
    //   90: new 76	java/lang/StringBuilder
    //   93: dup
    //   94: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   97: ldc 97
    //   99: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: aload_0
    //   103: getfield 23	gnu/kawa/reflect/StaticSet:fname	Ljava/lang/String;
    //   106: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   112: invokespecial 95	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   115: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	116	0	this	StaticSet
    //   0	116	1	paramObject	Object
    //   14	58	2	localClass	Class
    // Exception table:
    //   from	to	target	type
    //   15	27	42	java/lang/NoSuchFieldException
    //   27	40	85	java/lang/IllegalAccessException
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    if (this.field == null)
    {
      this.field = this.ctype.getField(this.fname);
      if (this.field == null) {
        this.field = this.ctype.addField(this.fname, Type.make(this.reflectField.getType()), this.reflectField.getModifiers());
      }
    }
    paramApplyExp.getArgs()[0].compile(paramCompilation, this.field.getType());
    paramCompilation.getCode().emitPutStatic(this.field);
    paramCompilation.compileConstant(Values.empty, paramTarget);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return Type.voidType;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\StaticSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */