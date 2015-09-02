package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.lists.XConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.xml.TextUtils;
import gnu.xml.XMLFilter;

public class MakeProcInst
  extends NodeConstructor
{
  public static final MakeProcInst makeProcInst = new MakeProcInst();
  
  public static void procInst$C(Object paramObject1, Object paramObject2, Consumer paramConsumer)
  {
    paramObject1 = KNode.atomicValue(paramObject1);
    if ((!(paramObject1 instanceof String)) && (!(paramObject1 instanceof UntypedAtomic))) {
      throw new ClassCastException("invalid type of processing-instruction target [XPTY0004]");
    }
    if (!(paramConsumer instanceof XConsumer)) {
      return;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    if ((paramObject2 instanceof Values))
    {
      paramObject2 = ((Values)paramObject2).getValues();
      i = 0;
      while (i < paramObject2.length)
      {
        if (i > 0) {
          localStringBuffer.append(' ');
        }
        TextUtils.stringValue(paramObject2[i], localStringBuffer);
        i += 1;
      }
    }
    TextUtils.stringValue(paramObject2, localStringBuffer);
    int j = localStringBuffer.length();
    int i = 0;
    while ((i < j) && (Character.isWhitespace(localStringBuffer.charAt(i)))) {
      i += 1;
    }
    paramObject2 = new char[j - i];
    localStringBuffer.getChars(i, j, (char[])paramObject2, 0);
    ((XConsumer)paramConsumer).writeProcessingInstruction(paramObject1.toString(), (char[])paramObject2, 0, paramObject2.length);
  }
  
  public static void procInst$X(Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    Consumer localConsumer = paramCallContext.consumer;
    XMLFilter localXMLFilter = NodeConstructor.pushNodeContext(paramCallContext);
    try
    {
      procInst$C(paramObject1, paramObject2, localXMLFilter);
      return;
    }
    finally
    {
      NodeConstructor.popNodeContext(localConsumer, paramCallContext);
    }
  }
  
  public void apply(CallContext paramCallContext)
  {
    procInst$X(paramCallContext.getNextArg(null), paramCallContext.getNextArg(null), paramCallContext);
  }
  
  public void compileToNode(ApplyExp paramApplyExp, Compilation paramCompilation, ConsumerTarget paramConsumerTarget)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    paramApplyExp = paramApplyExp.getArgs();
    paramApplyExp[0].compile(paramCompilation, Target.pushObject);
    paramApplyExp[1].compile(paramCompilation, Target.pushObject);
    localCodeAttr.emitLoad(paramConsumerTarget.getConsumerVariable());
    localCodeAttr.emitInvokeStatic(ClassType.make("gnu.kawa.xml.MakeProcInst").getDeclaredMethod("procInst$C", 3));
  }
  
  public int numArgs()
  {
    return 8194;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\MakeProcInst.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */