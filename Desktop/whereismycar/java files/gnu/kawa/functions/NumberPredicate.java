package gnu.kawa.functions;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangObjType;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;
import gnu.math.IntNum;

public class NumberPredicate
  extends Procedure1
  implements Inlineable
{
  public static final int EVEN = 2;
  public static final int ODD = 1;
  Language language;
  final int op;
  
  public NumberPredicate(Language paramLanguage, String paramString, int paramInt)
  {
    super(paramString);
    this.language = paramLanguage;
    this.op = paramInt;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyNumberPredicate");
  }
  
  public Object apply1(Object paramObject)
  {
    paramObject = LangObjType.coerceIntNum(paramObject);
    switch (this.op)
    {
    default: 
      throw new Error();
    case 1: 
      bool = ((IntNum)paramObject).isOdd();
      return getLanguage().booleanObject(bool);
    }
    if (!((IntNum)paramObject).isOdd()) {}
    for (boolean bool = true;; bool = false) {
      break;
    }
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    Object localObject = paramApplyExp.getArgs();
    if ((localObject.length == 1) && ((this.op == 1) || (this.op == 2)))
    {
      localObject = localObject[0];
      if (Arithmetic.classifyType(((Expression)localObject).getType()) <= 4)
      {
        paramApplyExp = StackTarget.getInstance(Type.intType);
        CodeAttr localCodeAttr = paramCompilation.getCode();
        if (this.op == 2) {
          localCodeAttr.emitPushInt(1);
        }
        ((Expression)localObject).compile(paramCompilation, paramApplyExp);
        localCodeAttr.emitPushInt(1);
        localCodeAttr.emitAnd();
        if (this.op == 2) {
          localCodeAttr.emitSub(Type.intType);
        }
        paramTarget.compileFromStack(paramCompilation, Type.booleanType);
        return;
      }
    }
    ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
  }
  
  protected final Language getLanguage()
  {
    return this.language;
  }
  
  public int numArgs()
  {
    return 4097;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\NumberPredicate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */