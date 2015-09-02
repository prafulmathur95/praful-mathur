package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.LazyPropertyKey;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.text.SourceMessages;

public class ApplyExp
  extends Expression
{
  public static final int INLINE_IF_CONSTANT = 4;
  public static final int MAY_CONTAIN_BACK_JUMP = 8;
  public static final int TAILCALL = 2;
  Expression[] args;
  LambdaExp context;
  Expression func;
  public ApplyExp nextCall;
  protected Type type;
  
  public ApplyExp(Method paramMethod, Expression[] paramArrayOfExpression)
  {
    this.func = new QuoteExp(new PrimProcedure(paramMethod));
    this.args = paramArrayOfExpression;
  }
  
  public ApplyExp(Expression paramExpression, Expression[] paramArrayOfExpression)
  {
    this.func = paramExpression;
    this.args = paramArrayOfExpression;
  }
  
  public ApplyExp(Procedure paramProcedure, Expression[] paramArrayOfExpression)
  {
    this.func = new QuoteExp(paramProcedure);
    this.args = paramArrayOfExpression;
  }
  
  public static Inlineable asInlineable(Procedure paramProcedure)
  {
    if ((paramProcedure instanceof Inlineable)) {
      return (Inlineable)paramProcedure;
    }
    return (Inlineable)Procedure.compilerKey.get(paramProcedure);
  }
  
  public static void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    compile(paramApplyExp, paramCompilation, paramTarget, false);
  }
  
  static void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget, boolean paramBoolean)
  {
    int k = paramApplyExp.args.length;
    Expression localExpression1 = paramApplyExp.func;
    Object localObject5 = null;
    Object localObject1 = null;
    Object localObject4 = null;
    Object localObject2 = null;
    Object localObject3 = null;
    Method localMethod = null;
    if ((localExpression1 instanceof LambdaExp))
    {
      localObject4 = (LambdaExp)localExpression1;
      localObject1 = localObject4;
      if (((LambdaExp)localObject4).getName() != null) {
        break label1339;
      }
      localObject2 = null;
      localObject1 = localObject4;
    }
    for (;;)
    {
      int i;
      if ((paramBoolean) && ((localObject2 instanceof Procedure)))
      {
        localObject4 = (Procedure)localObject2;
        if (((paramTarget instanceof IgnoreTarget)) && (((Procedure)localObject4).isSideEffectFree()))
        {
          i = 0;
          for (;;)
          {
            if (i < k)
            {
              paramApplyExp.args[i].compile(paramCompilation, paramTarget);
              i += 1;
              continue;
              if ((localExpression1 instanceof ReferenceExp))
              {
                localObject3 = (ReferenceExp)localExpression1;
                localObject2 = ((ReferenceExp)localObject3).contextDecl();
                localObject3 = ((ReferenceExp)localObject3).binding;
                for (;;)
                {
                  if ((localObject3 != null) && (((Declaration)localObject3).isAlias()) && ((((Declaration)localObject3).value instanceof ReferenceExp)))
                  {
                    localObject4 = (ReferenceExp)((Declaration)localObject3).value;
                    if ((localObject2 == null) && (!((Declaration)localObject3).needsContext()) && (((ReferenceExp)localObject4).binding != null)) {}
                  }
                  else
                  {
                    localObject4 = localMethod;
                    if (!((Declaration)localObject3).getFlag(65536L))
                    {
                      Expression localExpression2 = ((Declaration)localObject3).getValue();
                      ((Declaration)localObject3).getName();
                      localObject3 = localObject5;
                      if (localExpression2 != null)
                      {
                        localObject3 = localObject5;
                        if ((localExpression2 instanceof LambdaExp)) {
                          localObject3 = (LambdaExp)localExpression2;
                        }
                      }
                      localObject1 = localObject3;
                      localObject4 = localMethod;
                      if (localExpression2 != null)
                      {
                        localObject1 = localObject3;
                        localObject4 = localMethod;
                        if ((localExpression2 instanceof QuoteExp))
                        {
                          localObject4 = ((QuoteExp)localExpression2).getValue();
                          localObject1 = localObject3;
                        }
                      }
                    }
                    localObject3 = localObject2;
                    localObject2 = localObject4;
                    break;
                  }
                  localObject3 = ((ReferenceExp)localObject4).binding;
                  localObject2 = ((ReferenceExp)localObject4).contextDecl();
                }
              }
              localObject1 = localObject4;
              if (!(localExpression1 instanceof QuoteExp)) {
                break label1339;
              }
              localObject4 = ((QuoteExp)localExpression1).getValue();
              localObject1 = localObject2;
              localObject2 = localObject4;
              break;
            }
          }
        }
        try
        {
          paramBoolean = inlineCompile((Procedure)localObject4, paramApplyExp, paramCompilation, paramTarget);
          if (paramBoolean) {
            return;
          }
        }
        catch (Throwable paramApplyExp)
        {
          paramCompilation.getMessages().error('e', "caught exception in inline-compiler for " + localObject2 + " - " + paramApplyExp, paramApplyExp);
          return;
        }
      }
      localObject4 = paramCompilation.getCode();
      int j;
      if (localObject1 != null)
      {
        if (((((LambdaExp)localObject1).max_args >= 0) && (k > ((LambdaExp)localObject1).max_args)) || (k < ((LambdaExp)localObject1).min_args)) {
          throw new Error("internal error - wrong number of parameters for " + localObject1);
        }
        i = ((LambdaExp)localObject1).getCallConvention();
        if ((paramCompilation.inlineOk((Expression)localObject1)) && ((i <= 2) || ((i == 3) && (!paramApplyExp.isTailCall()))))
        {
          localMethod = ((LambdaExp)localObject1).getMethod(k);
          if (localMethod != null)
          {
            localObject2 = new PrimProcedure(localMethod, (LambdaExp)localObject1);
            paramBoolean = localMethod.getStaticFlag();
            i = 0;
            j = 0;
            if ((!paramBoolean) || (((LambdaExp)localObject1).declareClosureEnv() != null))
            {
              i = j;
              if (paramBoolean) {
                i = 1;
              }
              if (paramCompilation.curLambda != localObject1) {
                break label655;
              }
              if (((LambdaExp)localObject1).closureEnv != null)
              {
                localObject1 = ((LambdaExp)localObject1).closureEnv;
                ((CodeAttr)localObject4).emitLoad((Variable)localObject1);
              }
            }
            else
            {
              label624:
              if (i == 0) {
                break label686;
              }
            }
            label655:
            label686:
            for (localObject1 = Type.voidType;; localObject1 = null)
            {
              ((PrimProcedure)localObject2).compile((Type)localObject1, paramApplyExp, paramCompilation, paramTarget);
              return;
              localObject1 = ((LambdaExp)localObject1).thisVariable;
              break;
              if (localObject3 != null)
              {
                ((Declaration)localObject3).load(null, 0, paramCompilation, Target.pushObject);
                break label624;
              }
              ((LambdaExp)localObject1).getOwningLambda().loadHeapFrame(paramCompilation);
              break label624;
            }
          }
        }
      }
      if ((paramApplyExp.isTailCall()) && (localObject1 != null) && (localObject1 == paramCompilation.curLambda)) {
        i = 1;
      }
      while ((localObject1 != null) && (((LambdaExp)localObject1).getInlineOnly()) && (i == 0) && (((LambdaExp)localObject1).min_args == k))
      {
        pushArgs((LambdaExp)localObject1, paramApplyExp.args, null, paramCompilation);
        if (((LambdaExp)localObject1).getFlag(128))
        {
          popParams((CodeAttr)localObject4, (LambdaExp)localObject1, null, false);
          ((CodeAttr)localObject4).emitTailCall(false, ((LambdaExp)localObject1).getVarScope());
          return;
          i = 0;
        }
        else
        {
          ((LambdaExp)localObject1).flags |= 0x80;
          paramApplyExp = paramCompilation.curLambda;
          paramCompilation.curLambda = ((LambdaExp)localObject1);
          ((LambdaExp)localObject1).allocChildClasses(paramCompilation);
          ((LambdaExp)localObject1).allocParameters(paramCompilation);
          popParams((CodeAttr)localObject4, (LambdaExp)localObject1, null, false);
          ((LambdaExp)localObject1).enterFunction(paramCompilation);
          ((LambdaExp)localObject1).body.compileWithPosition(paramCompilation, paramTarget);
          ((LambdaExp)localObject1).compileEnd(paramCompilation);
          ((LambdaExp)localObject1).generateApplyMethods(paramCompilation);
          ((CodeAttr)localObject4).popScope();
          paramCompilation.curLambda = paramApplyExp;
          return;
        }
      }
      if ((paramCompilation.curLambda.isHandlingTailCalls()) && ((paramApplyExp.isTailCall()) || ((paramTarget instanceof ConsumerTarget))) && (!paramCompilation.curLambda.getInlineOnly()))
      {
        localObject1 = Compilation.typeCallContext;
        localExpression1.compile(paramCompilation, new StackTarget(Compilation.typeProcedure));
        if (k <= 4)
        {
          i = 0;
          while (i < k)
          {
            paramApplyExp.args[i].compileWithPosition(paramCompilation, Target.pushObject);
            i += 1;
          }
          paramCompilation.loadCallContext();
          ((CodeAttr)localObject4).emitInvoke(Compilation.typeProcedure.getDeclaredMethod("check" + k, k + 1));
        }
        while (paramApplyExp.isTailCall())
        {
          ((CodeAttr)localObject4).emitReturn();
          return;
          compileToArray(paramApplyExp.args, paramCompilation);
          paramCompilation.loadCallContext();
          ((CodeAttr)localObject4).emitInvoke(Compilation.typeProcedure.getDeclaredMethod("checkN", 2));
        }
        if (((ConsumerTarget)paramTarget).isContextTarget())
        {
          paramCompilation.loadCallContext();
          ((CodeAttr)localObject4).emitInvoke(((ClassType)localObject1).getDeclaredMethod("runUntilDone", 0));
          return;
        }
        paramCompilation.loadCallContext();
        ((CodeAttr)localObject4).emitLoad(((ConsumerTarget)paramTarget).getConsumerVariable());
        ((CodeAttr)localObject4).emitInvoke(((ClassType)localObject1).getDeclaredMethod("runUntilValue", 1));
        return;
      }
      if (i == 0) {
        localExpression1.compile(paramCompilation, new StackTarget(Compilation.typeProcedure));
      }
      if (i != 0) {
        if (((LambdaExp)localObject1).min_args != ((LambdaExp)localObject1).max_args)
        {
          paramBoolean = true;
          localObject2 = null;
          if (!paramBoolean) {
            break label1215;
          }
          compileToArray(paramApplyExp.args, paramCompilation);
          paramApplyExp = Compilation.applyNmethod;
        }
      }
      for (;;)
      {
        if (!((CodeAttr)localObject4).reachableHere())
        {
          paramCompilation.error('e', "unreachable code");
          return;
          paramBoolean = false;
          break;
          if (k > 4)
          {
            paramBoolean = true;
            break;
          }
          paramBoolean = false;
          break;
          label1215:
          if (i != 0)
          {
            localObject2 = new int[paramApplyExp.args.length];
            pushArgs((LambdaExp)localObject1, paramApplyExp.args, (int[])localObject2, paramCompilation);
            paramApplyExp = null;
          }
          else
          {
            j = 0;
            for (;;)
            {
              if (j < k)
              {
                paramApplyExp.args[j].compileWithPosition(paramCompilation, Target.pushObject);
                if (((CodeAttr)localObject4).reachableHere()) {}
              }
              else
              {
                paramApplyExp = Compilation.applymethods[k];
                break;
              }
              j += 1;
            }
          }
        }
      }
      if (i != 0)
      {
        popParams((CodeAttr)localObject4, (LambdaExp)localObject1, (int[])localObject2, paramBoolean);
        ((CodeAttr)localObject4).emitTailCall(false, ((LambdaExp)localObject1).getVarScope());
        return;
      }
      ((CodeAttr)localObject4).emitInvokeVirtual(paramApplyExp);
      paramTarget.compileFromStack(paramCompilation, Type.pointer_type);
      return;
      label1339:
      localObject2 = null;
    }
  }
  
  public static void compileToArray(Expression[] paramArrayOfExpression, Compilation paramCompilation)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    if (paramArrayOfExpression.length == 0)
    {
      localCodeAttr.emitGetStatic(Compilation.noArgsField);
      return;
    }
    localCodeAttr.emitPushInt(paramArrayOfExpression.length);
    localCodeAttr.emitNewArray(Type.pointer_type);
    int i = 0;
    label34:
    Expression localExpression;
    if (i < paramArrayOfExpression.length)
    {
      localExpression = paramArrayOfExpression[i];
      if ((!paramCompilation.usingCPStyle()) || ((localExpression instanceof QuoteExp)) || ((localExpression instanceof ReferenceExp))) {
        break label115;
      }
      localExpression.compileWithPosition(paramCompilation, Target.pushObject);
      localCodeAttr.emitSwap();
      localCodeAttr.emitDup(1, 1);
      localCodeAttr.emitSwap();
      localCodeAttr.emitPushInt(i);
      localCodeAttr.emitSwap();
    }
    for (;;)
    {
      localCodeAttr.emitArrayStore(Type.pointer_type);
      i += 1;
      break label34;
      break;
      label115:
      localCodeAttr.emitDup(Compilation.objArrayType);
      localCodeAttr.emitPushInt(i);
      localExpression.compileWithPosition(paramCompilation, Target.pushObject);
    }
  }
  
  static Expression derefFunc(Expression paramExpression)
  {
    Expression localExpression = paramExpression;
    if ((paramExpression instanceof ReferenceExp))
    {
      Declaration localDeclaration = Declaration.followAliases(((ReferenceExp)paramExpression).binding);
      localExpression = paramExpression;
      if (localDeclaration != null)
      {
        localExpression = paramExpression;
        if (!localDeclaration.getFlag(65536L)) {
          localExpression = localDeclaration.getValue();
        }
      }
    }
    return localExpression;
  }
  
  static boolean inlineCompile(Procedure paramProcedure, ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
    throws Throwable
  {
    paramProcedure = asInlineable(paramProcedure);
    if (paramProcedure == null) {
      return false;
    }
    paramProcedure.compile(paramApplyExp, paramCompilation, paramTarget);
    return true;
  }
  
  private static void popParams(CodeAttr paramCodeAttr, int paramInt1, int paramInt2, int[] paramArrayOfInt, Declaration paramDeclaration, Variable paramVariable)
  {
    Declaration localDeclaration;
    if (paramInt2 > 0)
    {
      localDeclaration = paramDeclaration.nextDecl();
      if (paramDeclaration.getVariable() != null) {
        break label70;
      }
    }
    label70:
    for (Variable localVariable = paramVariable;; localVariable = paramVariable.nextVar())
    {
      popParams(paramCodeAttr, paramInt1 + 1, paramInt2 - 1, paramArrayOfInt, localDeclaration, localVariable);
      if (!paramDeclaration.ignorable())
      {
        if ((paramArrayOfInt == null) || (paramArrayOfInt[paramInt1] == 65536)) {
          break;
        }
        paramCodeAttr.emitInc(paramVariable, (short)paramArrayOfInt[paramInt1]);
      }
      return;
    }
    paramCodeAttr.emitStore(paramVariable);
  }
  
  private static void popParams(CodeAttr paramCodeAttr, LambdaExp paramLambdaExp, int[] paramArrayOfInt, boolean paramBoolean)
  {
    Object localObject2 = paramLambdaExp.getVarScope().firstVar();
    Declaration localDeclaration = paramLambdaExp.firstDecl();
    Object localObject1 = localObject2;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((Variable)localObject2).getName() == "this") {
        localObject1 = ((Variable)localObject2).nextVar();
      }
    }
    localObject2 = localObject1;
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (((Variable)localObject1).getName() == "$ctx") {
        localObject2 = ((Variable)localObject1).nextVar();
      }
    }
    localObject1 = localObject2;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((Variable)localObject2).getName() == "argsArray")
      {
        if (paramBoolean)
        {
          popParams(paramCodeAttr, 0, 1, null, localDeclaration, (Variable)localObject2);
          return;
        }
        localObject1 = ((Variable)localObject2).nextVar();
      }
    }
    popParams(paramCodeAttr, 0, paramLambdaExp.min_args, paramArrayOfInt, localDeclaration, (Variable)localObject1);
  }
  
  private static void pushArgs(LambdaExp paramLambdaExp, Expression[] paramArrayOfExpression, int[] paramArrayOfInt, Compilation paramCompilation)
  {
    paramLambdaExp = paramLambdaExp.firstDecl();
    int j = paramArrayOfExpression.length;
    int i = 0;
    if (i < j)
    {
      Expression localExpression = paramArrayOfExpression[i];
      if (paramLambdaExp.ignorable()) {
        localExpression.compile(paramCompilation, Target.Ignore);
      }
      for (;;)
      {
        paramLambdaExp = paramLambdaExp.nextDecl();
        i += 1;
        break;
        if (paramArrayOfInt != null)
        {
          int k = SetExp.canUseInc(localExpression, paramLambdaExp);
          paramArrayOfInt[i] = k;
          if (k != 65536) {}
        }
        else
        {
          localExpression.compileWithPosition(paramCompilation, StackTarget.getInstance(paramLambdaExp.getType()));
        }
      }
    }
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    Object localObject = this.func.eval(paramCallContext);
    int j = this.args.length;
    Object[] arrayOfObject = new Object[j];
    int i = 0;
    while (i < j)
    {
      arrayOfObject[i] = this.args[i].eval(paramCallContext);
      i += 1;
    }
    ((Procedure)localObject).checkN(arrayOfObject, paramCallContext);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    compile(this, paramCompilation, paramTarget, true);
  }
  
  public Expression deepCopy(IdentityHashTable paramIdentityHashTable)
  {
    Expression localExpression = deepCopy(this.func, paramIdentityHashTable);
    paramIdentityHashTable = deepCopy(this.args, paramIdentityHashTable);
    if (((localExpression == null) && (this.func != null)) || ((paramIdentityHashTable == null) && (this.args != null))) {
      return null;
    }
    paramIdentityHashTable = new ApplyExp(localExpression, paramIdentityHashTable);
    paramIdentityHashTable.flags = getFlags();
    return paramIdentityHashTable;
  }
  
  public Expression getArg(int paramInt)
  {
    return this.args[paramInt];
  }
  
  public final int getArgCount()
  {
    return this.args.length;
  }
  
  public final Expression[] getArgs()
  {
    return this.args;
  }
  
  public final Expression getFunction()
  {
    return this.func;
  }
  
  public final Object getFunctionValue()
  {
    if ((this.func instanceof QuoteExp)) {
      return ((QuoteExp)this.func).getValue();
    }
    return null;
  }
  
  public final Type getType()
  {
    if (this.type != null) {
      return this.type;
    }
    Object localObject = derefFunc(this.func);
    this.type = Type.objectType;
    if ((localObject instanceof QuoteExp))
    {
      localObject = ((QuoteExp)localObject).getValue();
      if ((localObject instanceof Procedure)) {
        this.type = ((Procedure)localObject).getReturnType(this.args);
      }
    }
    for (;;)
    {
      return this.type;
      if ((localObject instanceof LambdaExp)) {
        this.type = ((LambdaExp)localObject).getReturnType();
      }
    }
  }
  
  public final Type getTypeRaw()
  {
    return this.type;
  }
  
  public final Expression inlineIfConstant(Procedure paramProcedure, InlineCalls paramInlineCalls)
  {
    return inlineIfConstant(paramProcedure, paramInlineCalls.getMessages());
  }
  
  public final Expression inlineIfConstant(Procedure paramProcedure, SourceMessages paramSourceMessages)
  {
    int i = this.args.length;
    Object[] arrayOfObject = new Object[i];
    i -= 1;
    Object localObject;
    if (i >= 0)
    {
      Expression localExpression = this.args[i];
      localObject = localExpression;
      if ((localExpression instanceof ReferenceExp))
      {
        Declaration localDeclaration = ((ReferenceExp)localExpression).getBinding();
        localObject = localExpression;
        if (localDeclaration != null)
        {
          localExpression = localDeclaration.getValue();
          localObject = localExpression;
          if (localExpression != QuoteExp.undefined_exp) {}
        }
      }
    }
    do
    {
      do
      {
        return this;
      } while (!(localObject instanceof QuoteExp));
      arrayOfObject[i] = ((QuoteExp)localObject).getValue();
      break;
      try
      {
        localObject = new QuoteExp(paramProcedure.applyN(arrayOfObject), this.type);
        return (Expression)localObject;
      }
      catch (Throwable localThrowable) {}
    } while (paramSourceMessages == null);
    paramSourceMessages.error('w', "call to " + paramProcedure + " throws " + localThrowable);
    return this;
  }
  
  public final boolean isTailCall()
  {
    return getFlag(2);
  }
  
  protected boolean mustCompile()
  {
    return false;
  }
  
  public void print(OutPort paramOutPort)
  {
    paramOutPort.startLogicalBlock("(Apply", ")", 2);
    if (isTailCall()) {
      paramOutPort.print(" [tailcall]");
    }
    if ((this.type != null) && (this.type != Type.pointer_type))
    {
      paramOutPort.print(" => ");
      paramOutPort.print(this.type);
    }
    paramOutPort.writeSpaceFill();
    printLineColumn(paramOutPort);
    this.func.print(paramOutPort);
    int i = 0;
    while (i < this.args.length)
    {
      paramOutPort.writeSpaceLinear();
      this.args[i].print(paramOutPort);
      i += 1;
    }
    paramOutPort.endLogicalBlock(")");
  }
  
  public void setArg(int paramInt, Expression paramExpression)
  {
    this.args[paramInt] = paramExpression;
  }
  
  public void setArgs(Expression[] paramArrayOfExpression)
  {
    this.args = paramArrayOfExpression;
  }
  
  public void setFunction(Expression paramExpression)
  {
    this.func = paramExpression;
  }
  
  public final void setTailCall(boolean paramBoolean)
  {
    setFlag(paramBoolean, 2);
  }
  
  public final void setType(Type paramType)
  {
    this.type = paramType;
  }
  
  public boolean side_effects()
  {
    Object localObject = derefFunc(this.func).valueIfConstant();
    int j;
    int i;
    if (((localObject instanceof Procedure)) && (((Procedure)localObject).isSideEffectFree()))
    {
      localObject = this.args;
      j = localObject.length;
      i = 0;
    }
    while (i < j)
    {
      if (localObject[i].side_effects()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public String toString()
  {
    if (this == LambdaExp.unknownContinuation) {
      return "ApplyExp[unknownContinuation]";
    }
    StringBuilder localStringBuilder = new StringBuilder().append("ApplyExp/");
    if (this.args == null) {}
    for (int i = 0;; i = this.args.length) {
      return i + '[' + this.func + ']';
    }
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitApplyExp(this, paramD);
  }
  
  public void visitArgs(InlineCalls paramInlineCalls)
  {
    this.args = paramInlineCalls.visitExps(this.args, this.args.length, null);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    this.func = paramExpVisitor.visitAndUpdate(this.func, paramD);
    if (paramExpVisitor.exitValue == null) {
      this.args = paramExpVisitor.visitExps(this.args, this.args.length, paramD);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ApplyExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */