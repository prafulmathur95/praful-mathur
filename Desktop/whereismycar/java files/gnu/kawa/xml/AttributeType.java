package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import gnu.lists.AttributePredicate;
import gnu.lists.SeqPosition;
import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.xml.namespace.QName;

public class AttributeType
  extends NodeType
  implements TypeValue, Externalizable, AttributePredicate
{
  static final Method coerceMethod = typeAttributeType.getDeclaredMethod("coerce", 3);
  static final Method coerceOrNullMethod = typeAttributeType.getDeclaredMethod("coerceOrNull", 3);
  public static final ClassType typeAttributeType = ClassType.make("gnu.kawa.xml.AttributeType");
  Symbol qname;
  
  public AttributeType(Symbol paramSymbol)
  {
    this(null, paramSymbol);
  }
  
  public AttributeType(String paramString, Symbol paramSymbol) {}
  
  public static SeqPosition coerce(Object paramObject, String paramString1, String paramString2)
  {
    paramObject = coerceOrNull(paramObject, paramString1, paramString2);
    if (paramObject == null) {
      throw new ClassCastException();
    }
    return (SeqPosition)paramObject;
  }
  
  public static KAttr coerceOrNull(Object paramObject, String paramString1, String paramString2)
  {
    KNode localKNode = NodeType.coerceOrNull(paramObject, 4);
    if (localKNode == null) {
      return null;
    }
    String str = paramString2;
    if (paramString2 != null)
    {
      str = paramString2;
      if (paramString2.length() == 0) {
        str = null;
      }
    }
    paramObject = localKNode.getNextTypeObject();
    if ((paramObject instanceof Symbol))
    {
      paramObject = (Symbol)paramObject;
      paramString2 = ((Symbol)paramObject).getNamespaceURI();
      paramObject = ((Symbol)paramObject).getLocalName();
    }
    while (((str == paramObject) || (str == null)) && ((paramString1 == paramString2) || (paramString1 == null)))
    {
      return (KAttr)localKNode;
      if ((paramObject instanceof QName))
      {
        paramObject = (QName)paramObject;
        paramString2 = ((QName)paramObject).getNamespaceURI();
        paramObject = ((QName)paramObject).getLocalPart();
      }
      else
      {
        paramString2 = "";
        paramObject = paramObject.toString().intern();
      }
    }
    return null;
  }
  
  public static AttributeType make(Symbol paramSymbol)
  {
    return new AttributeType(paramSymbol);
  }
  
  public static AttributeType make(String paramString1, String paramString2)
  {
    if (paramString1 != null) {
      paramString1 = Symbol.make(paramString1, paramString2);
    }
    for (;;)
    {
      return new AttributeType(paramString1);
      if (paramString2 == "") {
        paramString1 = ElementType.MATCH_ANY_QNAME;
      } else {
        paramString1 = new Symbol(null, paramString2);
      }
    }
  }
  
  public Object coerceFromObject(Object paramObject)
  {
    return coerce(paramObject, this.qname.getNamespaceURI(), this.qname.getLocalName());
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr)
  {
    paramCodeAttr.emitPushString(this.qname.getNamespaceURI());
    paramCodeAttr.emitPushString(this.qname.getLocalName());
    paramCodeAttr.emitInvokeStatic(coerceMethod);
  }
  
  protected void emitCoerceOrNullMethod(Variable paramVariable, Compilation paramCompilation)
  {
    paramCompilation = paramCompilation.getCode();
    if (paramVariable != null) {
      paramCompilation.emitLoad(paramVariable);
    }
    paramCompilation.emitPushString(this.qname.getNamespaceURI());
    paramCompilation.emitPushString(this.qname.getLocalName());
    paramCompilation.emitInvokeStatic(coerceOrNullMethod);
  }
  
  public Type getImplementationType()
  {
    return ClassType.make("gnu.kawa.xml.KAttr");
  }
  
  public final String getLocalName()
  {
    return this.qname.getLocalName();
  }
  
  public final String getNamespaceURI()
  {
    return this.qname.getNamespaceURI();
  }
  
  public boolean isInstance(AbstractSequence paramAbstractSequence, int paramInt, Object paramObject)
  {
    String str3 = this.qname.getNamespaceURI();
    String str2 = this.qname.getLocalName();
    if ((paramObject instanceof Symbol))
    {
      paramAbstractSequence = (Symbol)paramObject;
      paramObject = paramAbstractSequence.getNamespaceURI();
      paramAbstractSequence = paramAbstractSequence.getLocalName();
    }
    for (;;)
    {
      String str1 = str2;
      if (str2 != null)
      {
        str1 = str2;
        if (str2.length() == 0) {
          str1 = null;
        }
      }
      if (((str1 != paramAbstractSequence) && (str1 != null)) || ((str3 != paramObject) && (str3 != null))) {
        break;
      }
      return true;
      if ((paramObject instanceof QName))
      {
        paramAbstractSequence = (QName)paramObject;
        paramObject = paramAbstractSequence.getNamespaceURI();
        paramAbstractSequence = paramAbstractSequence.getLocalPart();
      }
      else
      {
        str1 = "";
        paramAbstractSequence = paramObject.toString().intern();
        paramObject = str1;
      }
    }
    return false;
  }
  
  public boolean isInstance(Object paramObject)
  {
    return coerceOrNull(paramObject, this.qname.getNamespaceURI(), this.qname.getLocalName()) != null;
  }
  
  public boolean isInstancePos(AbstractSequence paramAbstractSequence, int paramInt)
  {
    int i = paramAbstractSequence.getNextKind(paramInt);
    if (i == 35) {
      return isInstance(paramAbstractSequence, paramInt, paramAbstractSequence.getNextTypeObject(paramInt));
    }
    if (i == 32) {
      return isInstance(paramAbstractSequence.getPosNext(paramInt));
    }
    return false;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    String str = paramObjectInput.readUTF();
    if (str.length() > 0) {
      setName(str);
    }
    this.qname = ((Symbol)paramObjectInput.readObject());
  }
  
  public String toString()
  {
    return "AttributeType " + this.qname;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    String str2 = getName();
    String str1 = str2;
    if (str2 == null) {
      str1 = "";
    }
    paramObjectOutput.writeUTF(str1);
    paramObjectOutput.writeObject(this.qname);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\AttributeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */