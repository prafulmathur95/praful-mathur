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
import gnu.xml.XMLFilter;

public class MakeAttribute
  extends NodeConstructor
{
  static final Method endAttributeMethod = Compilation.typeConsumer.getDeclaredMethod("endAttribute", 0);
  public static final MakeAttribute makeAttribute = new MakeAttribute();
  public static final QuoteExp makeAttributeExp = new QuoteExp(makeAttribute);
  static final Method startAttributeMethod;
  static final ClassType typeMakeAttribute = ClassType.make("gnu.kawa.xml.MakeAttribute");
  
  static
  {
    startAttributeMethod = typeMakeAttribute.getDeclaredMethod("startAttribute", 2);
  }
  
  public static void startAttribute(Consumer paramConsumer, Object paramObject)
  {
    paramConsumer.startAttribute(paramObject);
  }
  
  public void apply(CallContext paramCallContext)
  {
    Consumer localConsumer = paramCallContext.consumer;
    XMLFilter localXMLFilter = pushNodeContext(paramCallContext);
    for (;;)
    {
      try
      {
        startAttribute(localXMLFilter, paramCallContext.getNextArg());
        Special localSpecial = Special.dfault;
        Object localObject2 = paramCallContext.getNextArg(localSpecial);
        if (localObject2 == localSpecial)
        {
          localXMLFilter.endAttribute();
          return;
        }
        if ((localObject2 instanceof Consumable)) {
          ((Consumable)localObject2).consume(localXMLFilter);
        } else {
          paramCallContext.writeValue(localObject2);
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
    localCodeAttr.emitDup();
    paramApplyExp[0].compile(paramCompilation, Target.pushObject);
    localCodeAttr.emitInvokeStatic(startAttributeMethod);
    int i = 1;
    while (i < j)
    {
      compileChild(paramApplyExp[i], paramCompilation, paramConsumerTarget);
      i += 1;
    }
    localCodeAttr.emitInvokeInterface(endAttributeMethod);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return Compilation.typeObject;
  }
  
  public int numArgs()
  {
    return 61441;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\MakeAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */