package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.reflect.InstanceOf;
import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.mapping.Procedure;
import gnu.xml.NodeTree;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class NodeType
  extends ObjectType
  implements TypeValue, NodePredicate, Externalizable
{
  public static final int ATTRIBUTE_OK = 4;
  public static final int COMMENT_OK = 16;
  public static final int DOCUMENT_OK = 8;
  public static final int ELEMENT_OK = 2;
  public static final int PI_OK = 32;
  public static final int TEXT_OK = 1;
  public static final NodeType anyNodeTest = new NodeType("node");
  static final Method coerceMethod;
  static final Method coerceOrNullMethod;
  public static final NodeType commentNodeTest;
  public static final NodeType documentNodeTest;
  public static final NodeType nodeType;
  public static final NodeType textNodeTest;
  public static final ClassType typeKNode = ClassType.make("gnu.kawa.xml.KNode");
  public static final ClassType typeNodeType = ClassType.make("gnu.kawa.xml.NodeType");
  int kinds = -1;
  
  static
  {
    nodeType = new NodeType("gnu.kawa.xml.KNode");
    coerceMethod = typeNodeType.getDeclaredMethod("coerceForce", 2);
    coerceOrNullMethod = typeNodeType.getDeclaredMethod("coerceOrNull", 2);
    documentNodeTest = new NodeType("document-node", 8);
    textNodeTest = new NodeType("text", 1);
    commentNodeTest = new NodeType("comment", 16);
  }
  
  public NodeType(String paramString)
  {
    this(paramString, -1);
  }
  
  public NodeType(String paramString, int paramInt)
  {
    super(paramString);
    this.kinds = paramInt;
  }
  
  public static KNode coerceForce(Object paramObject, int paramInt)
  {
    KNode localKNode = coerceOrNull(paramObject, paramInt);
    if (localKNode == null) {
      throw new ClassCastException("coerce from " + paramObject.getClass());
    }
    return localKNode;
  }
  
  public static KNode coerceOrNull(Object paramObject, int paramInt)
  {
    Object localObject = null;
    if ((paramObject instanceof NodeTree))
    {
      paramObject = KNode.make((NodeTree)paramObject);
      if (!isInstance(((KNode)paramObject).sequence, ((KNode)paramObject).ipos, paramInt)) {
        break label51;
      }
    }
    for (;;)
    {
      localObject = paramObject;
      do
      {
        return (KNode)localObject;
      } while (!(paramObject instanceof KNode));
      paramObject = (KNode)paramObject;
      break;
      label51:
      paramObject = null;
    }
  }
  
  public static boolean isInstance(AbstractSequence paramAbstractSequence, int paramInt1, int paramInt2)
  {
    paramInt1 = paramAbstractSequence.getNextKind(paramInt1);
    if (paramInt2 < 0) {
      if (paramInt1 == 0) {}
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return true;
                return false;
                switch (paramInt1)
                {
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: 
                case 8: 
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 13: 
                case 14: 
                case 15: 
                case 16: 
                case 30: 
                case 31: 
                default: 
                  return true;
                case 0: 
                  return false;
                }
              } while ((paramInt2 & 0x1) != 0);
              return false;
            } while ((paramInt2 & 0x2) != 0);
            return false;
          } while ((paramInt2 & 0x4) != 0);
          return false;
        } while ((paramInt2 & 0x8) != 0);
        return false;
      } while ((paramInt2 & 0x10) != 0);
      return false;
    } while ((paramInt2 & 0x20) != 0);
    return false;
  }
  
  public Object coerceFromObject(Object paramObject)
  {
    return coerceForce(paramObject, this.kinds);
  }
  
  public int compare(Type paramType)
  {
    return getImplementationType().compare(paramType);
  }
  
  public Expression convertValue(Expression paramExpression)
  {
    paramExpression = new ApplyExp(coerceMethod, new Expression[] { paramExpression });
    paramExpression.setType(this);
    return paramExpression;
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr)
  {
    paramCodeAttr.emitPushInt(this.kinds);
    paramCodeAttr.emitInvokeStatic(coerceMethod);
  }
  
  protected void emitCoerceOrNullMethod(Variable paramVariable, Compilation paramCompilation)
  {
    paramCompilation = paramCompilation.getCode();
    if (paramVariable != null) {
      paramCompilation.emitLoad(paramVariable);
    }
    paramCompilation.emitPushInt(this.kinds);
    paramCompilation.emitInvokeStatic(coerceOrNullMethod);
  }
  
  public void emitIsInstance(Variable paramVariable, Compilation paramCompilation, Target paramTarget)
  {
    if ((paramTarget instanceof ConditionalTarget))
    {
      paramTarget = (ConditionalTarget)paramTarget;
      emitCoerceOrNullMethod(paramVariable, paramCompilation);
      paramVariable = paramCompilation.getCode();
      if (paramTarget.trueBranchComesFirst) {
        paramVariable.emitGotoIfCompare1(paramTarget.ifFalse, 198);
      }
      for (;;)
      {
        paramTarget.emitGotoFirstBranch(paramVariable);
        return;
        paramVariable.emitGotoIfCompare1(paramTarget.ifTrue, 199);
      }
    }
    InstanceOf.emitIsInstance(this, paramVariable, paramCompilation, paramTarget);
  }
  
  public void emitTestIf(Variable paramVariable, Declaration paramDeclaration, Compilation paramCompilation)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    emitCoerceOrNullMethod(paramVariable, paramCompilation);
    if (paramDeclaration != null)
    {
      localCodeAttr.emitDup();
      paramDeclaration.compileStore(paramCompilation);
    }
    localCodeAttr.emitIfNotNull();
  }
  
  public Procedure getConstructor()
  {
    return null;
  }
  
  public Type getImplementationType()
  {
    return typeKNode;
  }
  
  public boolean isInstance(Object paramObject)
  {
    if ((paramObject instanceof KNode))
    {
      paramObject = (KNode)paramObject;
      return isInstancePos(((KNode)paramObject).sequence, ((KNode)paramObject).getPos());
    }
    return false;
  }
  
  public boolean isInstancePos(AbstractSequence paramAbstractSequence, int paramInt)
  {
    return isInstance(paramAbstractSequence, paramInt, this.kinds);
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    String str = paramObjectInput.readUTF();
    if (str.length() > 0) {
      setName(str);
    }
    this.kinds = paramObjectInput.readInt();
  }
  
  public String toString()
  {
    return "NodeType " + getName();
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
    paramObjectOutput.writeInt(this.kinds);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\NodeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */