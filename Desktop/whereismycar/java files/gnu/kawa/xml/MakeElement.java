package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.Special;
import gnu.expr.Target;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Symbol;
import gnu.xml.NamespaceBinding;
import gnu.xml.XMLFilter;
import gnu.xml.XName;

public class MakeElement
  extends NodeConstructor
{
  static final Method endElementMethod = typeMakeElement.getDeclaredMethod("endElement", 2);
  public static final MakeElement makeElement = new MakeElement();
  static final Method startElementMethod3;
  static final Method startElementMethod4;
  static final ClassType typeMakeElement = ClassType.make("gnu.kawa.xml.MakeElement");
  public int copyNamespacesMode = 1;
  private boolean handlingKeywordParameters;
  NamespaceBinding namespaceNodes;
  public Symbol tag;
  
  static
  {
    startElementMethod3 = typeMakeElement.getDeclaredMethod("startElement", 3);
    startElementMethod4 = typeMakeElement.getDeclaredMethod("startElement", 4);
  }
  
  public static void endElement(Consumer paramConsumer, Object paramObject)
  {
    paramConsumer.endElement();
  }
  
  public static Symbol getTagName(ApplyExp paramApplyExp)
  {
    paramApplyExp = paramApplyExp.getArgs();
    if (paramApplyExp.length > 0)
    {
      paramApplyExp = paramApplyExp[0];
      if ((paramApplyExp instanceof QuoteExp))
      {
        paramApplyExp = ((QuoteExp)paramApplyExp).getValue();
        if ((paramApplyExp instanceof Symbol)) {
          return (Symbol)paramApplyExp;
        }
      }
    }
    return null;
  }
  
  public static void startElement(Consumer paramConsumer, Object paramObject, int paramInt)
  {
    if ((paramObject instanceof Symbol)) {}
    for (paramObject = (Symbol)paramObject;; paramObject = Symbol.make("", paramObject.toString(), ""))
    {
      if ((paramConsumer instanceof XMLFilter)) {
        ((XMLFilter)paramConsumer).copyNamespacesMode = paramInt;
      }
      paramConsumer.startElement(paramObject);
      return;
    }
  }
  
  public static void startElement(Consumer paramConsumer, Object paramObject, int paramInt, NamespaceBinding paramNamespaceBinding)
  {
    if ((paramObject instanceof Symbol)) {}
    for (paramObject = new XName((Symbol)paramObject, paramNamespaceBinding);; paramObject = new XName(Symbol.make("", paramObject.toString(), ""), paramNamespaceBinding))
    {
      if ((paramConsumer instanceof XMLFilter)) {
        ((XMLFilter)paramConsumer).copyNamespacesMode = paramInt;
      }
      paramConsumer.startElement(paramObject);
      return;
    }
  }
  
  public void apply(CallContext paramCallContext)
  {
    Consumer localConsumer = paramCallContext.consumer;
    XMLFilter localXMLFilter = pushNodeContext(paramCallContext);
    label105:
    label142:
    label149:
    for (;;)
    {
      Object localObject3;
      try
      {
        Object localObject1;
        if (this.tag != null)
        {
          localObject1 = this.tag;
          if (this.namespaceNodes != null)
          {
            startElement(localXMLFilter, localObject1, this.copyNamespacesMode, this.namespaceNodes);
            Special localSpecial = Special.dfault;
            localObject3 = paramCallContext.getNextArg(localSpecial);
            if (localObject3 != localSpecial) {
              break label105;
            }
            endElement(localXMLFilter, localObject1);
          }
        }
        else
        {
          localObject1 = paramCallContext.getNextArg();
          continue;
        }
        startElement(localXMLFilter, localObject1, this.copyNamespacesMode);
        continue;
        if (!(localObject3 instanceof Consumable)) {
          break label142;
        }
      }
      finally
      {
        popNodeContext(localConsumer, paramCallContext);
      }
      ((Consumable)localObject3).consume(localXMLFilter);
      for (;;)
      {
        if (!isHandlingKeywordParameters()) {
          break label149;
        }
        localXMLFilter.endAttribute();
        break;
        paramCallContext.writeValue(localObject3);
      }
    }
  }
  
  public void compileToNode(ApplyExp paramApplyExp, Compilation paramCompilation, ConsumerTarget paramConsumerTarget)
  {
    Variable localVariable = paramConsumerTarget.getConsumerVariable();
    paramApplyExp = paramApplyExp.getArgs();
    int j = paramApplyExp.length;
    CodeAttr localCodeAttr = paramCompilation.getCode();
    localCodeAttr.emitLoad(localVariable);
    localCodeAttr.emitDup();
    int i;
    if (this.tag == null)
    {
      paramApplyExp[0].compile(paramCompilation, Target.pushObject);
      i = 1;
      localCodeAttr.emitDup(1, 1);
      localCodeAttr.emitPushInt(this.copyNamespacesMode);
      if (this.namespaceNodes == null) {
        break label159;
      }
      paramCompilation.compileConstant(this.namespaceNodes, Target.pushObject);
      localCodeAttr.emitInvokeStatic(startElementMethod4);
    }
    for (;;)
    {
      if (i >= j) {
        break label170;
      }
      compileChild(paramApplyExp[i], paramCompilation, paramConsumerTarget);
      if (isHandlingKeywordParameters())
      {
        localCodeAttr.emitLoad(localVariable);
        localCodeAttr.emitInvokeInterface(MakeAttribute.endAttributeMethod);
      }
      i += 1;
      continue;
      paramCompilation.compileConstant(this.tag, Target.pushObject);
      i = 0;
      break;
      label159:
      localCodeAttr.emitInvokeStatic(startElementMethod3);
    }
    label170:
    localCodeAttr.emitInvokeStatic(endElementMethod);
  }
  
  public NamespaceBinding getNamespaceNodes()
  {
    return this.namespaceNodes;
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return Compilation.typeObject;
  }
  
  public boolean isHandlingKeywordParameters()
  {
    return this.handlingKeywordParameters;
  }
  
  public int numArgs()
  {
    if (this.tag == null) {
      return 61441;
    }
    return 61440;
  }
  
  public void setHandlingKeywordParameters(boolean paramBoolean)
  {
    this.handlingKeywordParameters = paramBoolean;
  }
  
  public void setNamespaceNodes(NamespaceBinding paramNamespaceBinding)
  {
    this.namespaceNodes = paramNamespaceBinding;
  }
  
  public String toString()
  {
    return "makeElement[" + this.tag + "]";
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\MakeElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */