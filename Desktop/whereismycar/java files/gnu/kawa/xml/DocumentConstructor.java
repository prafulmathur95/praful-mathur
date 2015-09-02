package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.xml.XMLFilter;

public class DocumentConstructor
  extends NodeConstructor
{
  public static final DocumentConstructor documentConstructor = new DocumentConstructor();
  static final Method endDocumentMethod = Compilation.typeConsumer.getDeclaredMethod("endDocument", 0);
  static final Method startDocumentMethod = Compilation.typeConsumer.getDeclaredMethod("startDocument", 0);
  
  public void apply(CallContext paramCallContext)
  {
    Consumer localConsumer = paramCallContext.consumer;
    XMLFilter localXMLFilter = pushNodeContext(paramCallContext);
    for (;;)
    {
      try
      {
        String str = Location.UNBOUND;
        localXMLFilter.startDocument();
        Object localObject2 = paramCallContext.getNextArg(str);
        if (localObject2 == str)
        {
          localXMLFilter.endDocument();
          return;
        }
        if ((localObject2 instanceof Consumable)) {
          ((Consumable)localObject2).consume(localXMLFilter);
        } else {
          ((Consumer)localObject1).writeObject(localObject2);
        }
      }
      finally
      {
        popNodeContext(localConsumer, paramCallContext);
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
    localCodeAttr.emitInvokeInterface(startDocumentMethod);
    int i = 0;
    while (i < j)
    {
      compileChild(paramApplyExp[i], paramCompilation, paramConsumerTarget);
      i += 1;
    }
    localCodeAttr.emitLoad(localVariable);
    localCodeAttr.emitInvokeInterface(endDocumentMethod);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\DocumentConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */