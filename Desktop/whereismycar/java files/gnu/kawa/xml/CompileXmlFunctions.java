package gnu.kawa.xml;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.mapping.Procedure;

public class CompileXmlFunctions
{
  public static Expression validateApplyMakeUnescapedData(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramType = paramApplyExp.getArgs();
    paramInlineCalls = paramApplyExp;
    if (paramType.length == 1)
    {
      paramInlineCalls = paramApplyExp;
      if ((paramType[0] instanceof QuoteExp)) {
        paramInlineCalls = new QuoteExp(((MakeUnescapedData)paramProcedure).apply1(((QuoteExp)paramType[0]).getValue()));
      }
    }
    return paramInlineCalls;
  }
  
  public static Expression validateApplyTreeScanner(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramInlineCalls = ((TreeScanner)paramProcedure).type;
    if ((paramApplyExp.getTypeRaw() == null) && ((paramInlineCalls instanceof Type))) {
      paramApplyExp.setType(NodeSetType.getInstance((Type)paramInlineCalls));
    }
    return paramApplyExp;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\CompileXmlFunctions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */