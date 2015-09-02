package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.Target;
import gnu.mapping.Procedure2;

public class IsEq
  extends Procedure2
  implements Inlineable
{
  Language language;
  
  public IsEq(Language paramLanguage, String paramString)
  {
    this.language = paramLanguage;
    setName(paramString);
  }
  
  public static void compile(Expression[] paramArrayOfExpression, Compilation paramCompilation, Target paramTarget, Language paramLanguage)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    paramArrayOfExpression[0].compile(paramCompilation, Target.pushObject);
    paramArrayOfExpression[1].compile(paramCompilation, Target.pushObject);
    if ((paramTarget instanceof ConditionalTarget))
    {
      paramArrayOfExpression = (ConditionalTarget)paramTarget;
      if (paramArrayOfExpression.trueBranchComesFirst) {
        localCodeAttr.emitGotoIfNE(paramArrayOfExpression.ifFalse);
      }
      for (;;)
      {
        paramArrayOfExpression.emitGotoFirstBranch(localCodeAttr);
        return;
        localCodeAttr.emitGotoIfEq(paramArrayOfExpression.ifTrue);
      }
    }
    localCodeAttr.emitIfEq();
    if ((paramTarget.getType() instanceof ClassType))
    {
      paramArrayOfExpression = paramLanguage.booleanObject(true);
      paramLanguage = paramLanguage.booleanObject(false);
      paramCompilation.compileConstant(paramArrayOfExpression, Target.pushObject);
      localCodeAttr.emitElse();
      paramCompilation.compileConstant(paramLanguage, Target.pushObject);
      if (((paramArrayOfExpression instanceof Boolean)) && ((paramLanguage instanceof Boolean))) {
        paramArrayOfExpression = Compilation.scmBooleanType;
      }
    }
    for (;;)
    {
      localCodeAttr.emitFi();
      paramTarget.compileFromStack(paramCompilation, paramArrayOfExpression);
      return;
      paramArrayOfExpression = Type.pointer_type;
      continue;
      localCodeAttr.emitPushInt(1);
      localCodeAttr.emitElse();
      localCodeAttr.emitPushInt(0);
      paramArrayOfExpression = paramLanguage.getTypeFor(Boolean.TYPE);
    }
  }
  
  public boolean apply(Object paramObject1, Object paramObject2)
  {
    return paramObject1 == paramObject2;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
  {
    Language localLanguage = this.language;
    if (paramObject1 == paramObject2) {}
    for (boolean bool = true;; bool = false) {
      return localLanguage.booleanObject(bool);
    }
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    compile(paramApplyExp.getArgs(), paramCompilation, paramTarget, this.language);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return this.language.getTypeFor(Boolean.TYPE);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\IsEq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */