package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure0;

public class StaticGet
  extends Procedure0
  implements Inlineable
{
  ClassType ctype;
  gnu.bytecode.Field field;
  String fname;
  java.lang.reflect.Field reflectField;
  
  public StaticGet(ClassType paramClassType, String paramString, Type paramType, int paramInt)
  {
    this.ctype = paramClassType;
    this.fname = paramString;
    this.field = paramClassType.getField(paramString);
    if (this.field == null) {
      this.field = paramClassType.addField(paramString, paramType, paramInt);
    }
  }
  
  StaticGet(Class paramClass, String paramString)
  {
    this.ctype = ((ClassType)Type.make(paramClass));
    this.fname = paramString;
  }
  
  private gnu.bytecode.Field getField()
  {
    if (this.field == null)
    {
      this.field = this.ctype.getField(this.fname);
      if (this.field == null) {
        this.field = this.ctype.addField(this.fname, Type.make(this.reflectField.getType()), this.reflectField.getModifiers());
      }
    }
    return this.field;
  }
  
  /* Error */
  public Object apply0()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 46	gnu/kawa/reflect/StaticGet:reflectField	Ljava/lang/reflect/Field;
    //   4: ifnonnull +23 -> 27
    //   7: aload_0
    //   8: getfield 21	gnu/kawa/reflect/StaticGet:ctype	Lgnu/bytecode/ClassType;
    //   11: invokevirtual 65	gnu/bytecode/ClassType:getReflectClass	()Ljava/lang/Class;
    //   14: astore_1
    //   15: aload_0
    //   16: aload_1
    //   17: aload_0
    //   18: getfield 23	gnu/kawa/reflect/StaticGet:fname	Ljava/lang/String;
    //   21: invokevirtual 70	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   24: putfield 46	gnu/kawa/reflect/StaticGet:reflectField	Ljava/lang/reflect/Field;
    //   27: aload_0
    //   28: getfield 46	gnu/kawa/reflect/StaticGet:reflectField	Ljava/lang/reflect/Field;
    //   31: aconst_null
    //   32: invokevirtual 74	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   35: astore_1
    //   36: aload_1
    //   37: areturn
    //   38: astore_2
    //   39: new 76	java/lang/RuntimeException
    //   42: dup
    //   43: new 78	java/lang/StringBuilder
    //   46: dup
    //   47: invokespecial 79	java/lang/StringBuilder:<init>	()V
    //   50: ldc 81
    //   52: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: aload_0
    //   56: getfield 23	gnu/kawa/reflect/StaticGet:fname	Ljava/lang/String;
    //   59: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: ldc 87
    //   64: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: aload_1
    //   68: invokevirtual 91	java/lang/Class:getName	()Ljava/lang/String;
    //   71: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   77: invokespecial 97	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   80: athrow
    //   81: astore_1
    //   82: new 76	java/lang/RuntimeException
    //   85: dup
    //   86: new 78	java/lang/StringBuilder
    //   89: dup
    //   90: invokespecial 79	java/lang/StringBuilder:<init>	()V
    //   93: ldc 99
    //   95: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: aload_0
    //   99: getfield 23	gnu/kawa/reflect/StaticGet:fname	Ljava/lang/String;
    //   102: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: invokespecial 97	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   111: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	this	StaticGet
    //   14	54	1	localObject	Object
    //   81	1	1	localIllegalAccessException	IllegalAccessException
    //   38	1	2	localNoSuchFieldException	NoSuchFieldException
    // Exception table:
    //   from	to	target	type
    //   15	27	38	java/lang/NoSuchFieldException
    //   27	36	81	java/lang/IllegalAccessException
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    getField();
    paramCompilation.getCode().emitGetStatic(this.field);
    paramTarget.compileFromStack(paramCompilation, this.field.getType());
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return getField().getType();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\StaticGet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */