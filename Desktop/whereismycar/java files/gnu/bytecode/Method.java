package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Stack;

public class Method
  implements AttrContainer, Member
{
  int access_flags;
  Type[] arg_types;
  Attribute attributes;
  ClassType classfile;
  CodeAttr code;
  ExceptionsAttr exceptions;
  private String name;
  int name_index;
  Method next;
  Type return_type;
  String signature;
  int signature_index;
  
  private Method() {}
  
  Method(ClassType paramClassType, int paramInt)
  {
    if (paramClassType.last_method == null) {
      paramClassType.methods = this;
    }
    for (;;)
    {
      paramClassType.last_method = this;
      paramClassType.methods_count += 1;
      this.access_flags = paramInt;
      this.classfile = paramClassType;
      return;
      paramClassType.last_method.next = this;
    }
  }
  
  public Method(Method paramMethod, ClassType paramClassType)
  {
    this.arg_types = paramMethod.arg_types;
    this.return_type = paramMethod.return_type;
    this.name = paramMethod.name;
    this.access_flags = paramMethod.access_flags;
    this.classfile = paramClassType;
  }
  
  public static Method makeCloneMethod(Type paramType)
  {
    Method localMethod = new Method();
    localMethod.name = "clone";
    localMethod.access_flags = 1;
    localMethod.arg_types = Type.typeArray0;
    localMethod.return_type = paramType;
    localMethod.classfile = Type.pointer_type;
    return localMethod;
  }
  
  public static String makeSignature(Type[] paramArrayOfType, Type paramType)
  {
    StringBuilder localStringBuilder = new StringBuilder(100);
    int j = paramArrayOfType.length;
    localStringBuilder.append('(');
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(paramArrayOfType[i].getSignature());
      i += 1;
    }
    localStringBuilder.append(')');
    localStringBuilder.append(paramType.getSignature());
    return localStringBuilder.toString();
  }
  
  public void allocate_local(Variable paramVariable)
  {
    paramVariable.allocateLocal(this.code);
  }
  
  void assignConstants()
  {
    ConstantPool localConstantPool = getConstants();
    if ((this.name_index == 0) && (this.name != null)) {
      this.name_index = localConstantPool.addUtf8(this.name).index;
    }
    if (this.signature_index == 0) {
      this.signature_index = localConstantPool.addUtf8(getSignature()).index;
    }
    Attribute.assignConstants(this, this.classfile);
  }
  
  public void cleanupAfterCompilation()
  {
    this.attributes = null;
    this.exceptions = null;
    this.code = null;
  }
  
  public void compile_checkcast(Type paramType)
  {
    this.code.emitCheckcast(paramType);
  }
  
  public void compile_push_this()
  {
    this.code.emitPushThis();
  }
  
  public void compile_push_value(Variable paramVariable)
  {
    this.code.emitLoad(paramVariable);
  }
  
  public void compile_store_value(Variable paramVariable)
  {
    this.code.emitStore(paramVariable);
  }
  
  public final Attribute getAttributes()
  {
    return this.attributes;
  }
  
  public final CodeAttr getCode()
  {
    return this.code;
  }
  
  public final ConstantPool getConstants()
  {
    return this.classfile.constants;
  }
  
  public ClassType getDeclaringClass()
  {
    return this.classfile;
  }
  
  public final ExceptionsAttr getExceptionAttr()
  {
    return this.exceptions;
  }
  
  public final ClassType[] getExceptions()
  {
    if (this.exceptions == null) {
      return null;
    }
    return this.exceptions.getExceptions();
  }
  
  public int getModifiers()
  {
    return this.access_flags;
  }
  
  public final String getName()
  {
    return this.name;
  }
  
  public final Method getNext()
  {
    return this.next;
  }
  
  public final Type[] getParameterTypes()
  {
    return this.arg_types;
  }
  
  public final Type getReturnType()
  {
    return this.return_type;
  }
  
  public String getSignature()
  {
    if (this.signature == null) {
      this.signature = makeSignature(this.arg_types, this.return_type);
    }
    return this.signature;
  }
  
  public final boolean getStaticFlag()
  {
    return (this.access_flags & 0x8) != 0;
  }
  
  public void initCode()
  {
    if (this.classfile.constants == null) {
      this.classfile.constants = new ConstantPool();
    }
    prepareCode(0);
    this.code.sourceDbgExt = this.classfile.sourceDbgExt;
    this.code.noteParamTypes();
    this.code.pushScope();
  }
  
  public void init_param_slots()
  {
    startCode();
  }
  
  void instruction_start_hook(int paramInt)
  {
    prepareCode(paramInt);
  }
  
  public final boolean isAbstract()
  {
    return (this.access_flags & 0x400) != 0;
  }
  
  void kill_local(Variable paramVariable)
  {
    paramVariable.freeLocal(this.code);
  }
  
  public void listParameters(StringBuffer paramStringBuffer)
  {
    int j = this.arg_types.length;
    paramStringBuffer.append('(');
    int i = 0;
    while (i < j)
    {
      if (i > 0) {
        paramStringBuffer.append(',');
      }
      paramStringBuffer.append(this.arg_types[i].getName());
      i += 1;
    }
    paramStringBuffer.append(')');
  }
  
  public void maybe_compile_checkcast(Type paramType)
  {
    if (paramType != this.code.topType()) {
      this.code.emitCheckcast(paramType);
    }
  }
  
  public Scope popScope()
  {
    return this.code.popScope();
  }
  
  final Type pop_stack_type()
  {
    return this.code.popType();
  }
  
  void prepareCode(int paramInt)
  {
    if (this.code == null) {
      this.code = new CodeAttr(this);
    }
    this.code.reserve(paramInt);
  }
  
  public Scope pushScope()
  {
    prepareCode(0);
    return this.code.pushScope();
  }
  
  final void push_stack_type(Type paramType)
  {
    this.code.pushType(paramType);
  }
  
  public void push_var(Variable paramVariable)
  {
    this.code.emitLoad(paramVariable);
  }
  
  public final boolean reachableHere()
  {
    return this.code.reachableHere();
  }
  
  public final void setAttributes(Attribute paramAttribute)
  {
    this.attributes = paramAttribute;
  }
  
  public void setExceptions(ClassType[] paramArrayOfClassType)
  {
    if (this.exceptions == null) {
      this.exceptions = new ExceptionsAttr(this);
    }
    this.exceptions.setExceptions(paramArrayOfClassType);
  }
  
  public void setExceptions(short[] paramArrayOfShort)
  {
    if (this.exceptions == null) {
      this.exceptions = new ExceptionsAttr(this);
    }
    this.exceptions.setExceptions(paramArrayOfShort, this.classfile);
  }
  
  public void setModifiers(int paramInt)
  {
    this.access_flags = paramInt;
  }
  
  public final void setName(int paramInt)
  {
    if (paramInt <= 0) {}
    for (this.name = null;; this.name = ((CpoolUtf8)getConstants().getForced(paramInt, 1)).string)
    {
      this.name_index = paramInt;
      return;
    }
  }
  
  public final void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setSignature(int paramInt)
  {
    CpoolUtf8 localCpoolUtf8 = (CpoolUtf8)getConstants().getForced(paramInt, 1);
    this.signature_index = paramInt;
    setSignature(localCpoolUtf8.string);
  }
  
  public void setSignature(String paramString)
  {
    int k = paramString.length();
    if ((k < 3) || (paramString.charAt(0) != '(')) {
      throw new ClassFormatError("bad method signature");
    }
    int i = 1;
    Stack localStack = new Stack();
    for (;;)
    {
      int j = Type.signatureLength(paramString, i);
      if (j < 0)
      {
        if ((i < k) && (paramString.charAt(i) == ')'))
        {
          this.arg_types = new Type[localStack.size()];
          j = localStack.size();
          for (;;)
          {
            j -= 1;
            if (j < 0) {
              break;
            }
            this.arg_types[j] = ((Type)localStack.pop());
          }
        }
        throw new ClassFormatError("bad method signature");
      }
      localStack.push(Type.signatureToType(paramString, i, j));
      i += j;
    }
    this.return_type = Type.signatureToType(paramString, i + 1, k - i - 1);
  }
  
  public final void setStaticFlag(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.access_flags |= 0x8;
      return;
    }
    this.access_flags ^= 0xFFFFFFF7;
  }
  
  public CodeAttr startCode()
  {
    initCode();
    this.code.addParamLocals();
    return this.code;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    localStringBuffer.append(getDeclaringClass().getName());
    localStringBuffer.append('.');
    localStringBuffer.append(this.name);
    if (this.arg_types != null)
    {
      listParameters(localStringBuffer);
      localStringBuffer.append(this.return_type.getName());
    }
    return localStringBuffer.toString();
  }
  
  void write(DataOutputStream paramDataOutputStream, ClassType paramClassType)
    throws IOException
  {
    paramDataOutputStream.writeShort(this.access_flags);
    paramDataOutputStream.writeShort(this.name_index);
    paramDataOutputStream.writeShort(this.signature_index);
    Attribute.writeAll(this, paramDataOutputStream);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\Method.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */