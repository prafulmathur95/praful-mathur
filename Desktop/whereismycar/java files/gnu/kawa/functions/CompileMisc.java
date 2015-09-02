package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.ConsumerTarget;
import gnu.expr.Declaration;
import gnu.expr.ExpVisitor;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.IgnoreTarget;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.expr.TryExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.lists.LList;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.WrongArguments;
import kawa.standard.Scheme;

public class CompileMisc
  implements Inlineable
{
  static final int CONVERT = 2;
  static final int NOT = 3;
  static Method coerceMethod;
  public static final ClassType typeContinuation = ClassType.make("kawa.lang.Continuation");
  static ClassType typeType;
  int code;
  Procedure proc;
  
  public CompileMisc(Procedure paramProcedure, int paramInt)
  {
    this.proc = paramProcedure;
    this.code = paramInt;
  }
  
  private static LambdaExp canInlineCallCC(ApplyExp paramApplyExp)
  {
    paramApplyExp = paramApplyExp.getArgs();
    if (paramApplyExp.length == 1)
    {
      paramApplyExp = paramApplyExp[0];
      if ((paramApplyExp instanceof LambdaExp))
      {
        paramApplyExp = (LambdaExp)paramApplyExp;
        if ((paramApplyExp.min_args == 1) && (paramApplyExp.max_args == 1) && (!paramApplyExp.firstDecl().getCanWrite())) {
          return paramApplyExp;
        }
      }
    }
    return null;
  }
  
  public static void compileCallCC(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget, Procedure paramProcedure)
  {
    paramProcedure = canInlineCallCC(paramApplyExp);
    if (paramProcedure == null)
    {
      ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
      return;
    }
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Object localObject1 = paramProcedure.firstDecl();
    if ((((Declaration)localObject1).isSimple()) && (!((Declaration)localObject1).getCanRead()) && (!((Declaration)localObject1).getCanWrite()))
    {
      localObject2 = new CompileTimeContinuation();
      if ((paramTarget instanceof StackTarget)) {}
      for (paramApplyExp = paramTarget.getType();; paramApplyExp = null)
      {
        ((CompileTimeContinuation)localObject2).exitableBlock = localCodeAttr.startExitableBlock(paramApplyExp, ExitThroughFinallyChecker.check((Declaration)localObject1, paramProcedure.body));
        ((CompileTimeContinuation)localObject2).blockTarget = paramTarget;
        ((Declaration)localObject1).setValue(new QuoteExp(localObject2));
        paramProcedure.body.compile(paramCompilation, paramTarget);
        localCodeAttr.endExitableBlock();
        return;
      }
    }
    localObject1 = localCodeAttr.pushScope().addVariable(localCodeAttr, typeContinuation, null);
    Object localObject2 = new Declaration((Variable)localObject1);
    localCodeAttr.emitNew(typeContinuation);
    localCodeAttr.emitDup(typeContinuation);
    paramCompilation.loadCallContext();
    localCodeAttr.emitInvokeSpecial(typeContinuation.getDeclaredMethod("<init>", 1));
    localCodeAttr.emitStore((Variable)localObject1);
    if (((paramTarget instanceof IgnoreTarget)) || ((paramTarget instanceof ConsumerTarget)))
    {
      paramApplyExp = null;
      localCodeAttr.emitTryStart(false, paramApplyExp);
      new ApplyExp(paramProcedure, new Expression[] { new ReferenceExp((Declaration)localObject2) }).compile(paramCompilation, paramTarget);
      if (localCodeAttr.reachableHere())
      {
        localCodeAttr.emitLoad((Variable)localObject1);
        localCodeAttr.emitPushInt(1);
        localCodeAttr.emitPutField(typeContinuation.getField("invoked"));
      }
      localCodeAttr.emitTryEnd();
      localCodeAttr.emitCatchStart(null);
      localCodeAttr.emitLoad((Variable)localObject1);
      if (!(paramTarget instanceof ConsumerTarget)) {
        break label354;
      }
      paramCompilation.loadCallContext();
      localCodeAttr.emitInvokeStatic(typeContinuation.getDeclaredMethod("handleException$X", 3));
    }
    for (;;)
    {
      localCodeAttr.emitCatchEnd();
      localCodeAttr.emitTryCatchEnd();
      localCodeAttr.popScope();
      return;
      paramApplyExp = Type.objectType;
      break;
      label354:
      localCodeAttr.emitInvokeStatic(typeContinuation.getDeclaredMethod("handleException", 2));
      paramTarget.compileFromStack(paramCompilation, Type.objectType);
    }
  }
  
  public static void compileConvert(Convert paramConvert, ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    paramApplyExp = paramApplyExp.getArgs();
    if (paramApplyExp.length != 2) {
      throw new Error("wrong number of arguments to " + paramConvert.getName());
    }
    paramConvert = paramCompilation.getCode();
    Type localType = Scheme.getTypeValue(paramApplyExp[0]);
    if (localType != null)
    {
      paramApplyExp[1].compile(paramCompilation, Target.pushValue(localType));
      if (paramConvert.reachableHere()) {
        paramTarget.compileFromStack(paramCompilation, localType);
      }
      return;
    }
    if (typeType == null) {
      typeType = ClassType.make("gnu.bytecode.Type");
    }
    if (coerceMethod == null) {
      coerceMethod = typeType.addMethod("coerceFromObject", Compilation.apply1args, Type.pointer_type, 1);
    }
    paramApplyExp[0].compile(paramCompilation, LangObjType.typeClassType);
    paramApplyExp[1].compile(paramCompilation, Target.pushObject);
    paramConvert.emitInvokeVirtual(coerceMethod);
    paramTarget.compileFromStack(paramCompilation, Type.pointer_type);
  }
  
  public static CompileMisc forConvert(Object paramObject)
  {
    return new CompileMisc((Procedure)paramObject, 2);
  }
  
  public static CompileMisc forNot(Object paramObject)
  {
    return new CompileMisc((Procedure)paramObject, 3);
  }
  
  public static Expression validateApplyAppendValues(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramType = paramApplyExp.getArgs();
    if (paramType.length == 1) {
      paramInlineCalls = paramType[0];
    }
    do
    {
      return paramInlineCalls;
      if (paramType.length == 0) {
        return QuoteExp.voidExp;
      }
      paramType = paramApplyExp.inlineIfConstant(paramProcedure, paramInlineCalls);
      paramInlineCalls = paramType;
    } while (paramType != paramApplyExp);
    return paramApplyExp;
  }
  
  public static Expression validateApplyCallCC(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramType = canInlineCallCC(paramApplyExp);
    if (paramType != null)
    {
      paramType.setInlineOnly(true);
      paramType.returnContinuation = paramApplyExp;
      paramType.inlineHome = paramInlineCalls.getCurrentLambda();
      paramType = paramType.firstDecl();
      if (!paramType.getFlag(8192L)) {
        paramType.setType(typeContinuation);
      }
    }
    paramApplyExp.visitArgs(paramInlineCalls);
    return paramApplyExp;
  }
  
  public static Expression validateApplyConstantFunction0(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    int i = paramApplyExp.getArgCount();
    if ((i != 0) && (paramInlineCalls != null)) {
      return paramInlineCalls.noteError(WrongArguments.checkArgCount(paramProcedure, i));
    }
    return ((ConstantFunction0)paramProcedure).constant;
  }
  
  public static Expression validateApplyConvert(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramType = paramInlineCalls.getCompilation();
    Object localObject = paramType.getLanguage();
    paramProcedure = paramApplyExp.getArgs();
    if (paramProcedure.length == 2)
    {
      paramProcedure[0] = paramInlineCalls.visit(paramProcedure[0], null);
      localObject = ((Language)localObject).getTypeFor(paramProcedure[0]);
      if ((localObject instanceof Type))
      {
        paramProcedure[0] = new QuoteExp(localObject);
        paramProcedure[1] = paramInlineCalls.visit(paramProcedure[1], (Type)localObject);
        CompileReflect.checkKnownClass((Type)localObject, paramType);
        paramApplyExp.setType((Type)localObject);
        return paramApplyExp;
      }
    }
    paramApplyExp.visitArgs(paramInlineCalls);
    return paramApplyExp;
  }
  
  public static Expression validateApplyFormat(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramProcedure = Type.objectType;
    paramType = paramApplyExp.getArgs();
    paramInlineCalls = paramProcedure;
    if (paramType.length > 0)
    {
      paramInlineCalls = ClassType.make("gnu.kawa.functions.Format");
      Object localObject = paramType[0].valueIfConstant();
      Type localType = paramType[0].getType();
      if ((localObject == Boolean.FALSE) || (localType.isSubtype(LangObjType.stringType)))
      {
        if (localObject == Boolean.FALSE) {}
        for (int i = 1;; i = 0)
        {
          paramApplyExp = new Expression[paramType.length + 1 - i];
          paramApplyExp[0] = new QuoteExp(Integer.valueOf(0), Type.intType);
          System.arraycopy(paramType, i, paramApplyExp, 1, paramApplyExp.length - 1);
          paramApplyExp = new ApplyExp(paramInlineCalls.getDeclaredMethod("formatToString", 2), paramApplyExp);
          paramApplyExp.setType(Type.javalangStringType);
          return paramApplyExp;
        }
      }
      if ((localObject == Boolean.TRUE) || (localType.isSubtype(ClassType.make("java.io.Writer"))))
      {
        paramApplyExp = paramType;
        if (localObject == Boolean.TRUE)
        {
          paramApplyExp = new Expression[paramType.length];
          paramApplyExp[0] = QuoteExp.nullExp;
          System.arraycopy(paramType, 1, paramApplyExp, 1, paramType.length - 1);
        }
        paramApplyExp = new ApplyExp(paramInlineCalls.getDeclaredMethod("formatToWriter", 3), paramApplyExp);
        paramApplyExp.setType(Type.voidType);
        return paramApplyExp;
      }
      paramInlineCalls = paramProcedure;
      if (localType.isSubtype(ClassType.make("java.io.OutputStream"))) {
        paramInlineCalls = Type.voidType;
      }
    }
    paramApplyExp.setType(paramInlineCalls);
    return null;
  }
  
  public static Expression validateApplyMakeProcedure(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int n = arrayOfExpression.length;
    paramInlineCalls = null;
    int k = 0;
    paramProcedure = null;
    int j = 0;
    Object localObject;
    int i;
    if (j < n)
    {
      paramType = arrayOfExpression[j];
      String str;
      int m;
      Expression localExpression;
      if ((paramType instanceof QuoteExp))
      {
        localObject = ((QuoteExp)paramType).getValue();
        if ((localObject instanceof Keyword))
        {
          str = ((Keyword)localObject).getName();
          m = j + 1;
          localExpression = arrayOfExpression[m];
          if (str == "name")
          {
            i = k;
            j = m;
            paramType = paramInlineCalls;
            localObject = paramProcedure;
            if ((localExpression instanceof QuoteExp))
            {
              localObject = ((QuoteExp)localExpression).getValue().toString();
              paramType = paramInlineCalls;
              j = m;
              i = k;
            }
          }
        }
      }
      for (;;)
      {
        j += 1;
        k = i;
        paramInlineCalls = paramType;
        paramProcedure = (Procedure)localObject;
        break;
        i = k;
        j = m;
        paramType = paramInlineCalls;
        localObject = paramProcedure;
        if (str == "method")
        {
          i = k + 1;
          paramType = localExpression;
          j = m;
          localObject = paramProcedure;
          continue;
          i = k + 1;
          localObject = paramProcedure;
        }
      }
    }
    if ((k == 1) && ((paramInlineCalls instanceof LambdaExp)))
    {
      paramType = (LambdaExp)paramInlineCalls;
      i = 0;
      paramApplyExp = paramInlineCalls;
      if (i < n)
      {
        paramApplyExp = arrayOfExpression[i];
        j = i;
        if ((paramApplyExp instanceof QuoteExp))
        {
          paramApplyExp = ((QuoteExp)paramApplyExp).getValue();
          j = i;
          if ((paramApplyExp instanceof Keyword))
          {
            paramApplyExp = ((Keyword)paramApplyExp).getName();
            i += 1;
            localObject = arrayOfExpression[i];
            if (paramApplyExp != "name") {
              break label320;
            }
            paramType.setName(paramProcedure);
            j = i;
          }
        }
        for (;;)
        {
          i = j + 1;
          break;
          label320:
          j = i;
          if (paramApplyExp != "method")
          {
            paramType.setProperty(Namespace.EmptyNamespace.getSymbol(paramApplyExp), localObject);
            j = i;
          }
        }
      }
    }
    return paramApplyExp;
  }
  
  public static Expression validateApplyMap(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    Map localMap = (Map)paramProcedure;
    boolean bool = localMap.collect;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression1 = paramApplyExp.getArgs();
    int i = arrayOfExpression1.length;
    if (i < 2) {
      return paramApplyExp;
    }
    i -= 1;
    paramType = arrayOfExpression1[0];
    int j;
    LetExp localLetExp;
    if (!paramType.side_effects())
    {
      j = 1;
      paramProcedure = new LetExp(new Expression[] { paramType });
      localObject2 = paramProcedure.addDeclaration("%proc", Compilation.typeProcedure);
      ((Declaration)localObject2).noteValue(arrayOfExpression1[0]);
      paramApplyExp = new Expression[1];
      localLetExp = new LetExp(paramApplyExp);
      paramProcedure.setBody(localLetExp);
      if (!bool) {
        break label293;
      }
    }
    LambdaExp localLambdaExp;
    Declaration localDeclaration;
    Object localObject1;
    Declaration[] arrayOfDeclaration;
    Object localObject3;
    Object localObject4;
    label293:
    for (int k = i + 1;; k = i)
    {
      localLambdaExp = new LambdaExp(k);
      paramApplyExp[0] = localLambdaExp;
      localDeclaration = localLetExp.addDeclaration("%loop");
      localDeclaration.noteValue(localLambdaExp);
      paramApplyExp = new Expression[i];
      localObject1 = new LetExp(paramApplyExp);
      arrayOfDeclaration = new Declaration[i];
      localObject3 = new Declaration[i];
      k = 0;
      while (k < i)
      {
        localObject4 = "arg" + k;
        arrayOfDeclaration[k] = localLambdaExp.addDeclaration(localObject4);
        localObject3[k] = ((LetExp)localObject1).addDeclaration(localObject4, Compilation.typePair);
        paramApplyExp[k] = new ReferenceExp(arrayOfDeclaration[k]);
        localObject3[k].noteValue(paramApplyExp[k]);
        k += 1;
      }
      j = 0;
      break;
    }
    Expression[] arrayOfExpression2;
    if (bool)
    {
      paramApplyExp = localLambdaExp.addDeclaration("result");
      arrayOfExpression2 = new Expression[i + 1];
      if (!bool) {
        break label423;
      }
    }
    label423:
    for (k = i + 1;; k = i)
    {
      localObject4 = new Expression[k];
      k = 0;
      while (k < i)
      {
        arrayOfExpression2[(k + 1)] = paramInlineCalls.visitApplyOnly(SlotGet.makeGetField(new ReferenceExp(localObject3[k]), "car"), null);
        localObject4[k] = paramInlineCalls.visitApplyOnly(SlotGet.makeGetField(new ReferenceExp(localObject3[k]), "cdr"), null);
        k += 1;
      }
      paramApplyExp = null;
      break;
    }
    if (j == 0) {
      paramType = new ReferenceExp((Declaration)localObject2);
    }
    arrayOfExpression2[0] = paramType;
    Object localObject2 = paramInlineCalls.visitApplyOnly(new ApplyExp(new ReferenceExp(localMap.applyFieldDecl), arrayOfExpression2), null);
    if (bool)
    {
      paramType = new ReferenceExp(paramApplyExp);
      localObject4[i] = Invoke.makeInvokeStatic(Compilation.typePair, "make", new Expression[] { localObject2, paramType });
    }
    paramType = paramInlineCalls.visitApplyOnly(new ApplyExp(new ReferenceExp(localDeclaration), (Expression[])localObject4), null);
    if (bool)
    {
      localLambdaExp.body = paramType;
      ((LetExp)localObject1).setBody(localLambdaExp.body);
      localLambdaExp.body = ((Expression)localObject1);
      if (!bool) {
        break label722;
      }
      k = i + 1;
      label582:
      localObject1 = new Expression[k];
      localObject2 = new QuoteExp(LList.Empty);
      k = i;
      label605:
      k -= 1;
      if (k < 0) {
        break label736;
      }
      localObject3 = new ReferenceExp(arrayOfDeclaration[k]);
      if (!bool) {
        break label729;
      }
    }
    label722:
    label729:
    for (paramType = new ReferenceExp(paramApplyExp);; paramType = QuoteExp.voidExp)
    {
      localLambdaExp.body = new IfExp(paramInlineCalls.visitApplyOnly(new ApplyExp(localMap.isEq, new Expression[] { localObject3, localObject2 }), null), paramType, localLambdaExp.body);
      localObject1[k] = arrayOfExpression1[(k + 1)];
      break label605;
      paramType = new BeginExp((Expression)localObject2, paramType);
      break;
      k = i;
      break label582;
    }
    label736:
    if (bool) {
      localObject1[i] = localObject2;
    }
    paramInlineCalls = paramInlineCalls.visitApplyOnly(new ApplyExp(new ReferenceExp(localDeclaration), (Expression[])localObject1), null);
    paramApplyExp = paramInlineCalls;
    if (bool) {
      paramApplyExp = Invoke.makeInvokeStatic(Compilation.scmListType, "reverseInPlace", new Expression[] { paramInlineCalls });
    }
    localLetExp.setBody(paramApplyExp);
    if (j != 0) {
      return localLetExp;
    }
    return paramProcedure;
  }
  
  public static Expression validateApplyNot(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramApplyExp.setType(paramInlineCalls.getCompilation().getLanguage().getTypeFor(Boolean.TYPE));
    return paramApplyExp.inlineIfConstant(paramProcedure, paramInlineCalls);
  }
  
  public static Expression validateApplyValuesMap(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramType = ValuesMap.canInline(paramApplyExp, (ValuesMap)paramProcedure);
    if (paramType != null)
    {
      paramType.setInlineOnly(true);
      paramType.returnContinuation = paramApplyExp;
      paramType.inlineHome = paramInlineCalls.getCurrentLambda();
    }
    return paramApplyExp;
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    switch (this.code)
    {
    default: 
      throw new Error();
    case 2: 
      compileConvert((Convert)this.proc, paramApplyExp, paramCompilation, paramTarget);
      return;
    }
    compileNot((Not)this.proc, paramApplyExp, paramCompilation, paramTarget);
  }
  
  public void compileNot(Not paramNot, ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    paramApplyExp = paramApplyExp.getArgs()[0];
    paramNot = paramNot.language;
    if ((paramTarget instanceof ConditionalTarget))
    {
      paramTarget = (ConditionalTarget)paramTarget;
      paramNot = new ConditionalTarget(paramTarget.ifFalse, paramTarget.ifTrue, paramNot);
      if (!paramTarget.trueBranchComesFirst) {}
      for (boolean bool = true;; bool = false)
      {
        paramNot.trueBranchComesFirst = bool;
        paramApplyExp.compile(paramCompilation, paramNot);
        return;
      }
    }
    Object localObject = paramCompilation.getCode();
    Type localType = paramTarget.getType();
    if (((paramTarget instanceof StackTarget)) && (localType.getSignature().charAt(0) == 'Z'))
    {
      paramApplyExp.compile(paramCompilation, paramTarget);
      ((CodeAttr)localObject).emitNot(paramTarget.getType());
      return;
    }
    localObject = QuoteExp.getInstance(paramNot.booleanObject(true));
    IfExp.compile(paramApplyExp, QuoteExp.getInstance(paramNot.booleanObject(false)), (Expression)localObject, paramCompilation, paramTarget);
  }
  
  static class ExitThroughFinallyChecker
    extends ExpVisitor<Expression, TryExp>
  {
    Declaration decl;
    
    public static boolean check(Declaration paramDeclaration, Expression paramExpression)
    {
      ExitThroughFinallyChecker localExitThroughFinallyChecker = new ExitThroughFinallyChecker();
      localExitThroughFinallyChecker.decl = paramDeclaration;
      localExitThroughFinallyChecker.visit(paramExpression, null);
      return localExitThroughFinallyChecker.exitValue != null;
    }
    
    protected Expression defaultValue(Expression paramExpression, TryExp paramTryExp)
    {
      return paramExpression;
    }
    
    protected Expression visitReferenceExp(ReferenceExp paramReferenceExp, TryExp paramTryExp)
    {
      if ((this.decl == paramReferenceExp.getBinding()) && (paramTryExp != null)) {
        this.exitValue = Boolean.TRUE;
      }
      return paramReferenceExp;
    }
    
    protected Expression visitTryExp(TryExp paramTryExp1, TryExp paramTryExp2)
    {
      if (paramTryExp1.getFinallyClause() != null) {
        paramTryExp2 = paramTryExp1;
      }
      visitExpression(paramTryExp1, paramTryExp2);
      return paramTryExp1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\CompileMisc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */