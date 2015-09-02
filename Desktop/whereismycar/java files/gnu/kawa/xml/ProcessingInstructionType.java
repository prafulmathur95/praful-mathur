package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ProcessingInstructionType
  extends NodeType
  implements TypeValue, Externalizable
{
  static final Method coerceMethod = typeProcessingInstructionType.getDeclaredMethod("coerce", 2);
  static final Method coerceOrNullMethod = typeProcessingInstructionType.getDeclaredMethod("coerceOrNull", 2);
  public static final ProcessingInstructionType piNodeTest = new ProcessingInstructionType(null);
  public static final ClassType typeProcessingInstructionType = ClassType.make("gnu.kawa.xml.ProcessingInstructionType");
  String target;
  
  public ProcessingInstructionType(String paramString) {}
  
  public static KProcessingInstruction coerce(Object paramObject, String paramString)
  {
    paramObject = coerceOrNull(paramObject, paramString);
    if (paramObject == null) {
      throw new ClassCastException();
    }
    return (KProcessingInstruction)paramObject;
  }
  
  public static KProcessingInstruction coerceOrNull(Object paramObject, String paramString)
  {
    paramObject = (KProcessingInstruction)NodeType.coerceOrNull(paramObject, 32);
    if ((paramObject != null) && ((paramString == null) || (paramString.equals(((KProcessingInstruction)paramObject).getTarget())))) {
      return (KProcessingInstruction)paramObject;
    }
    return null;
  }
  
  public static ProcessingInstructionType getInstance(String paramString)
  {
    if (paramString == null) {
      return piNodeTest;
    }
    return new ProcessingInstructionType(paramString);
  }
  
  public Object coerceFromObject(Object paramObject)
  {
    return coerce(paramObject, this.target);
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr)
  {
    paramCodeAttr.emitPushString(this.target);
    paramCodeAttr.emitInvokeStatic(coerceMethod);
  }
  
  protected void emitCoerceOrNullMethod(Variable paramVariable, Compilation paramCompilation)
  {
    paramCompilation = paramCompilation.getCode();
    if (paramVariable != null) {
      paramCompilation.emitLoad(paramVariable);
    }
    paramCompilation.emitPushString(this.target);
    paramCompilation.emitInvokeStatic(coerceOrNullMethod);
  }
  
  public Type getImplementationType()
  {
    return ClassType.make("gnu.kawa.xml.KProcessingInstruction");
  }
  
  public boolean isInstance(Object paramObject)
  {
    return coerceOrNull(paramObject, this.target) != null;
  }
  
  public boolean isInstancePos(AbstractSequence paramAbstractSequence, int paramInt)
  {
    boolean bool = false;
    int i = paramAbstractSequence.getNextKind(paramInt);
    if (i == 37) {
      if ((this.target == null) || (this.target.equals(paramAbstractSequence.getNextTypeObject(paramInt)))) {
        bool = true;
      }
    }
    while (i != 32) {
      return bool;
    }
    return isInstance(paramAbstractSequence.getPosNext(paramInt));
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.target = ((String)paramObjectInput.readObject());
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("ProcessingInstructionType ");
    if (this.target == null) {}
    for (String str = "*";; str = this.target) {
      return str;
    }
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.target);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\ProcessingInstructionType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */