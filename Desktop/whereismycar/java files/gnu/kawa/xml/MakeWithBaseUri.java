package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.lists.TreeList;
import gnu.lists.XConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.xml.XMLFilter;

public class MakeWithBaseUri
  extends NodeConstructor
{
  static final Method beginEntityMethod = typeXConsumer.getDeclaredMethod("beginEntity", 1);
  static final Method endEntityMethod = typeXConsumer.getDeclaredMethod("endEntity", 0);
  public static final MakeWithBaseUri makeWithBaseUri = new MakeWithBaseUri();
  static final ClassType typeXConsumer = ClassType.make("gnu.lists.XConsumer");
  
  public void apply(CallContext paramCallContext)
  {
    Consumer localConsumer = paramCallContext.consumer;
    XMLFilter localXMLFilter = NodeConstructor.pushNodeContext(paramCallContext);
    Object localObject1 = paramCallContext.getNextArg();
    Object localObject3 = paramCallContext.getNextArg();
    if ((localXMLFilter instanceof XConsumer)) {
      ((XConsumer)localXMLFilter).beginEntity(localObject1);
    }
    try
    {
      Values.writeValues(localObject3, localXMLFilter);
      return;
    }
    finally
    {
      if ((localXMLFilter instanceof XConsumer)) {
        ((XConsumer)localXMLFilter).endEntity();
      }
      if ((localXMLFilter instanceof TreeList)) {
        ((TreeList)localXMLFilter).dump();
      }
      NodeConstructor.popNodeContext(localConsumer, paramCallContext);
    }
  }
  
  public void compileToNode(ApplyExp paramApplyExp, Compilation paramCompilation, ConsumerTarget paramConsumerTarget)
  {
    Variable localVariable = paramConsumerTarget.getConsumerVariable();
    paramApplyExp = paramApplyExp.getArgs();
    int i = paramApplyExp.length;
    CodeAttr localCodeAttr = paramCompilation.getCode();
    localCodeAttr.emitLoad(localVariable);
    paramApplyExp[0].compile(paramCompilation, Target.pushObject);
    localCodeAttr.emitInvokeInterface(beginEntityMethod);
    compileChild(paramApplyExp[1], paramCompilation, paramConsumerTarget);
    localCodeAttr.emitLoad(localVariable);
    localCodeAttr.emitInvokeInterface(endEntityMethod);
  }
  
  public int numArgs()
  {
    return 8194;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\MakeWithBaseUri.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */