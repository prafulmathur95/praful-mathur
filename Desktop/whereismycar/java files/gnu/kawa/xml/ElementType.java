package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import gnu.lists.ElementPredicate;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.xml.NamespaceBinding;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.xml.namespace.QName;

public class ElementType
  extends NodeType
  implements TypeValue, Externalizable, ElementPredicate
{
  public static final String MATCH_ANY_LOCALNAME = "";
  public static final Symbol MATCH_ANY_QNAME = new Symbol(null, "");
  public static final ElementType anyElement = make(null, null);
  static final Method coerceMethod = typeElementType.getDeclaredMethod("coerce", 3);
  static final Method coerceOrNullMethod = typeElementType.getDeclaredMethod("coerceOrNull", 3);
  public static final ClassType typeElementType = ClassType.make("gnu.kawa.xml.ElementType");
  NamespaceBinding namespaceNodes;
  Symbol qname;
  
  public ElementType(Symbol paramSymbol)
  {
    this(null, paramSymbol);
  }
  
  public ElementType(String paramString, Symbol paramSymbol) {}
  
  public static KElement coerce(Object paramObject, String paramString1, String paramString2)
  {
    paramObject = coerceOrNull(paramObject, paramString1, paramString2);
    if (paramObject == null) {
      throw new ClassCastException();
    }
    return (KElement)paramObject;
  }
  
  public static KElement coerceOrNull(Object paramObject, String paramString1, String paramString2)
  {
    KElement localKElement = (KElement)NodeType.coerceOrNull(paramObject, 2);
    if (localKElement == null)
    {
      paramObject = null;
      return (KElement)paramObject;
    }
    String str = paramString2;
    if (paramString2 != null)
    {
      str = paramString2;
      if (paramString2.length() == 0) {
        str = null;
      }
    }
    paramObject = localKElement.getNextTypeObject();
    if ((paramObject instanceof Symbol))
    {
      paramObject = (Symbol)paramObject;
      paramString2 = ((Symbol)paramObject).getNamespaceURI();
      paramObject = ((Symbol)paramObject).getLocalName();
    }
    for (;;)
    {
      if ((str == paramObject) || (str == null))
      {
        paramObject = localKElement;
        if (paramString1 == paramString2) {
          break;
        }
        paramObject = localKElement;
        if (paramString1 == null) {
          break;
        }
      }
      return null;
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
  }
  
  public static ElementType make(Symbol paramSymbol)
  {
    return new ElementType(paramSymbol);
  }
  
  public static ElementType make(String paramString1, String paramString2)
  {
    if (paramString1 != null) {
      paramString1 = Symbol.make(paramString1, paramString2);
    }
    for (;;)
    {
      return new ElementType(paramString1);
      if (paramString2 == "") {
        paramString1 = MATCH_ANY_QNAME;
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
  
  public Procedure getConstructor()
  {
    MakeElement localMakeElement = new MakeElement();
    localMakeElement.tag = this.qname;
    localMakeElement.setHandlingKeywordParameters(true);
    if (this.namespaceNodes != null) {
      localMakeElement.setNamespaceNodes(this.namespaceNodes);
    }
    return localMakeElement;
  }
  
  public Type getImplementationType()
  {
    return ClassType.make("gnu.kawa.xml.KElement");
  }
  
  public final String getLocalName()
  {
    return this.qname.getLocalName();
  }
  
  public NamespaceBinding getNamespaceNodes()
  {
    return this.namespaceNodes;
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
    if (i == 33) {
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
  
  public void setNamespaceNodes(NamespaceBinding paramNamespaceBinding)
  {
    this.namespaceNodes = paramNamespaceBinding;
  }
  
  public String toString()
  {
    return "ElementType " + this.qname;
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


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\ElementType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */