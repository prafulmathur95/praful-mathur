package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;

public class MakeList
  extends ProcedureN
  implements Inlineable
{
  public static final MakeList list = new MakeList();
  
  static
  {
    list.setName("list");
  }
  
  public static void compile(Expression[] paramArrayOfExpression, int paramInt, Compilation paramCompilation)
  {
    int j = paramArrayOfExpression.length - paramInt;
    CodeAttr localCodeAttr = paramCompilation.getCode();
    if (j == 0)
    {
      new QuoteExp(LList.Empty).compile(paramCompilation, Target.pushObject);
      return;
    }
    if (j <= 4)
    {
      i = 0;
      while (i < j)
      {
        paramArrayOfExpression[(paramInt + i)].compile(paramCompilation, Target.pushObject);
        i += 1;
      }
      localCodeAttr.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("list" + j, null));
      return;
    }
    paramArrayOfExpression[paramInt].compile(paramCompilation, Target.pushObject);
    localCodeAttr.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("list1", null));
    localCodeAttr.emitDup(1);
    paramInt += 1;
    int i = j - 1;
    int k;
    for (;;)
    {
      j = i;
      k = paramInt;
      if (i < 4) {
        break;
      }
      paramArrayOfExpression[paramInt].compile(paramCompilation, Target.pushObject);
      paramArrayOfExpression[(paramInt + 1)].compile(paramCompilation, Target.pushObject);
      paramArrayOfExpression[(paramInt + 2)].compile(paramCompilation, Target.pushObject);
      paramArrayOfExpression[(paramInt + 3)].compile(paramCompilation, Target.pushObject);
      i -= 4;
      paramInt += 4;
      localCodeAttr.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("chain4", null));
    }
    while (j > 0)
    {
      paramArrayOfExpression[k].compile(paramCompilation, Target.pushObject);
      j -= 1;
      k += 1;
      localCodeAttr.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("chain1", null));
    }
    localCodeAttr.emitPop(1);
  }
  
  public static Object list$V(Object[] paramArrayOfObject)
  {
    Object localObject = LList.Empty;
    int i = paramArrayOfObject.length;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      localObject = new Pair(paramArrayOfObject[i], localObject);
    }
    return localObject;
  }
  
  public Object applyN(Object[] paramArrayOfObject)
  {
    return list$V(paramArrayOfObject);
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    compile(paramApplyExp.getArgs(), 0, paramCompilation);
    paramTarget.compileFromStack(paramCompilation, paramApplyExp.getType());
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    if (paramArrayOfExpression.length > 0) {
      return Compilation.typePair;
    }
    return LangObjType.listType;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\MakeList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */