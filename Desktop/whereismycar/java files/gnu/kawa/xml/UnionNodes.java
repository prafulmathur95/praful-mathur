package gnu.kawa.xml;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.kawa.functions.AppendValues;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class UnionNodes
  extends Procedure2
  implements Inlineable
{
  public static final UnionNodes unionNodes = new UnionNodes();
  
  public Object apply2(Object paramObject1, Object paramObject2)
  {
    SortedNodes localSortedNodes = new SortedNodes();
    Values.writeValues(paramObject1, localSortedNodes);
    Values.writeValues(paramObject2, localSortedNodes);
    return localSortedNodes;
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    ConsumerTarget.compileUsingConsumer(new ApplyExp(AppendValues.appendValues, paramApplyExp.getArgs()), paramCompilation, paramTarget, SortNodes.makeSortedNodesMethod, null);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return Compilation.typeObject;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\UnionNodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */