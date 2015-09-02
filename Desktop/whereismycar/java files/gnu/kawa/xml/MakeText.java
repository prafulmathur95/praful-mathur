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
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.xml.NodeTree;
import gnu.xml.TextUtils;
import gnu.xml.XMLFilter;

public class MakeText
  extends NodeConstructor
{
  public static final MakeText makeText = new MakeText();
  
  public static void text$X(Object paramObject, CallContext paramCallContext)
  {
    if ((paramObject == null) || (((paramObject instanceof Values)) && (((Values)paramObject).isEmpty()))) {
      return;
    }
    Consumer localConsumer = paramCallContext.consumer;
    XMLFilter localXMLFilter = NodeConstructor.pushNodeContext(paramCallContext);
    try
    {
      TextUtils.textValue(paramObject, localXMLFilter);
      return;
    }
    finally
    {
      NodeConstructor.popNodeContext(localConsumer, paramCallContext);
    }
  }
  
  public void apply(CallContext paramCallContext)
  {
    text$X(paramCallContext.getNextArg(null), paramCallContext);
  }
  
  public Object apply1(Object paramObject)
  {
    if ((paramObject == null) || (((paramObject instanceof Values)) && (((Values)paramObject).isEmpty()))) {
      return paramObject;
    }
    NodeTree localNodeTree = new NodeTree();
    TextUtils.textValue(paramObject, new XMLFilter(localNodeTree));
    return KText.make(localNodeTree);
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
  }
  
  public void compileToNode(ApplyExp paramApplyExp, Compilation paramCompilation, ConsumerTarget paramConsumerTarget)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Object localObject = paramApplyExp.getArgs()[0];
    paramApplyExp = paramConsumerTarget.getConsumerVariable();
    if ((localObject instanceof QuoteExp))
    {
      paramConsumerTarget = ((QuoteExp)localObject).getValue();
      if ((paramConsumerTarget instanceof String))
      {
        paramCompilation = (String)paramConsumerTarget;
        paramConsumerTarget = CodeAttr.calculateSplit(paramCompilation);
        int m = paramConsumerTarget.length();
        localObject = ((ClassType)paramApplyExp.getType()).getMethod("write", new Type[] { Type.string_type });
        int j = 0;
        int i = 0;
        while (i < m)
        {
          localCodeAttr.emitLoad(paramApplyExp);
          int k = j + paramConsumerTarget.charAt(i);
          localCodeAttr.emitPushString(paramCompilation.substring(j, k));
          localCodeAttr.emitInvoke((Method)localObject);
          j = k;
          i += 1;
        }
      }
    }
    ((Expression)localObject).compile(paramCompilation, Target.pushObject);
    localCodeAttr.emitLoad(paramApplyExp);
    localCodeAttr.emitInvokeStatic(ClassType.make("gnu.xml.TextUtils").getDeclaredMethod("textValue", 2));
  }
  
  public int numArgs()
  {
    return 4097;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\MakeText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */