package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.KeyPair;
import gnu.mapping.Symbol;
import gnu.text.SourceLocator;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class FindCapturedVars
  extends ExpExpVisitor<Void>
{
  int backJumpPossible = 0;
  ModuleExp currentModule = null;
  Hashtable unknownDecls = null;
  
  static Expression checkInlineable(LambdaExp paramLambdaExp, Set<LambdaExp> paramSet)
  {
    if (paramLambdaExp.returnContinuation == LambdaExp.unknownContinuation) {
      return paramLambdaExp.returnContinuation;
    }
    if (paramSet.contains(paramLambdaExp)) {
      return paramLambdaExp.returnContinuation;
    }
    if ((paramLambdaExp.getCanRead()) || (paramLambdaExp.isClassMethod()) || (paramLambdaExp.min_args != paramLambdaExp.max_args))
    {
      paramLambdaExp.returnContinuation = LambdaExp.unknownContinuation;
      return LambdaExp.unknownContinuation;
    }
    paramSet.add(paramLambdaExp);
    Object localObject1 = paramLambdaExp.returnContinuation;
    Object localObject2 = localObject1;
    if (paramLambdaExp.tailCallers != null)
    {
      Iterator localIterator = paramLambdaExp.tailCallers.iterator();
      label174:
      do
      {
        LambdaExp localLambdaExp;
        do
        {
          for (;;)
          {
            localObject2 = localObject1;
            if (!localIterator.hasNext()) {
              return localObject2;
            }
            localLambdaExp = (LambdaExp)localIterator.next();
            localObject2 = checkInlineable(localLambdaExp, paramSet);
            if (localObject2 != LambdaExp.unknownContinuation) {
              break label174;
            }
            if ((localObject1 != null) && (localObject1 != localLambdaExp.body)) {
              break;
            }
            localObject1 = localLambdaExp.body;
            paramLambdaExp.inlineHome = localLambdaExp;
          }
          paramLambdaExp.returnContinuation = LambdaExp.unknownContinuation;
          return (Expression)localObject2;
          if (localObject1 != null) {
            break;
          }
          localObject1 = localObject2;
        } while (paramLambdaExp.inlineHome != null);
        if (paramLambdaExp.nestedIn(localLambdaExp)) {}
        for (localObject1 = localLambdaExp;; localObject1 = localLambdaExp.inlineHome)
        {
          paramLambdaExp.inlineHome = ((LambdaExp)localObject1);
          localObject1 = localObject2;
          break;
        }
      } while (((localObject2 == null) || (localObject1 == localObject2)) && (!paramLambdaExp.getFlag(32)));
      paramLambdaExp.returnContinuation = LambdaExp.unknownContinuation;
      return LambdaExp.unknownContinuation;
    }
    return (Expression)localObject2;
  }
  
  public static void findCapturedVars(Expression paramExpression, Compilation paramCompilation)
  {
    FindCapturedVars localFindCapturedVars = new FindCapturedVars();
    localFindCapturedVars.setContext(paramCompilation);
    paramExpression.visit(localFindCapturedVars, null);
  }
  
  Declaration allocUnboundDecl(Object paramObject, boolean paramBoolean)
  {
    Object localObject1 = paramObject;
    Object localObject2 = localObject1;
    boolean bool = paramBoolean;
    if (paramBoolean)
    {
      localObject2 = localObject1;
      bool = paramBoolean;
      if ((paramObject instanceof Symbol))
      {
        if (getCompilation().getLanguage().hasSeparateFunctionNamespace()) {
          break label165;
        }
        bool = false;
        localObject2 = localObject1;
      }
    }
    if (this.unknownDecls == null) {
      this.unknownDecls = new Hashtable(100);
    }
    for (localObject1 = null;; localObject1 = (Declaration)this.unknownDecls.get(localObject2))
    {
      Object localObject3 = localObject1;
      if (localObject1 == null)
      {
        localObject3 = this.currentModule.addDeclaration(paramObject);
        ((Declaration)localObject3).setSimple(false);
        ((Declaration)localObject3).setPrivate(true);
        if (bool) {
          ((Declaration)localObject3).setProcedureDecl(true);
        }
        if (this.currentModule.isStatic()) {
          ((Declaration)localObject3).setFlag(2048L);
        }
        ((Declaration)localObject3).setCanRead(true);
        ((Declaration)localObject3).setCanWrite(true);
        ((Declaration)localObject3).setFlag(327680L);
        ((Declaration)localObject3).setIndirectBinding(true);
        this.unknownDecls.put(localObject2, localObject3);
      }
      return (Declaration)localObject3;
      label165:
      localObject2 = new KeyPair((Symbol)paramObject, EnvironmentKey.FUNCTION);
      bool = paramBoolean;
      break;
    }
  }
  
  public void capture(Declaration paramDeclaration)
  {
    if ((!paramDeclaration.getCanRead()) && (!paramDeclaration.getCanCall())) {}
    label14:
    Object localObject1;
    Object localObject2;
    LambdaExp localLambdaExp2;
    Object localObject4;
    label201:
    label219:
    label349:
    label359:
    do
    {
      do
      {
        break label14;
        do
        {
          return;
        } while (((paramDeclaration.field != null) && (paramDeclaration.field.getStaticFlag())) || ((this.comp.immediate) && (paramDeclaration.hasConstantValue())));
        localObject1 = getCurrentLambda();
        localObject2 = paramDeclaration.getContext();
        if (localObject2 == null) {
          throw new Error("null context for " + paramDeclaration + " curL:" + localObject1);
        }
        localLambdaExp2 = ((ScopeExp)localObject2).currentLambda();
        localObject4 = null;
        localObject2 = null;
        while ((localObject1 != localLambdaExp2) && (((LambdaExp)localObject1).getInlineOnly()))
        {
          LambdaExp localLambdaExp1 = ((LambdaExp)localObject1).outerLambda();
          localObject3 = localObject4;
          if (localLambdaExp1 != localObject4)
          {
            localObject2 = localLambdaExp1.firstChild;
            localObject3 = localLambdaExp1;
          }
          if ((localObject2 == null) || (((LambdaExp)localObject1).inlineHome == null))
          {
            ((LambdaExp)localObject1).setCanCall(false);
            return;
          }
          localObject1 = ((LambdaExp)localObject1).getCaller();
          localObject2 = ((LambdaExp)localObject2).nextSibling;
          localObject4 = localObject3;
        }
        if (!this.comp.usingCPStyle()) {
          break;
        }
      } while ((localObject1 instanceof ModuleExp));
      localObject2 = paramDeclaration.getValue();
      if ((localObject2 == null) || (!(localObject2 instanceof LambdaExp)))
      {
        localObject2 = null;
        if (!paramDeclaration.getFlag(65536L)) {}
      }
      for (localObject3 = localObject1;; localObject3 = ((LambdaExp)localObject3).outerLambda())
      {
        if (localObject3 == localLambdaExp2) {}
        for (;;)
        {
          if (paramDeclaration.base == null) {
            break label359;
          }
          paramDeclaration.base.setCanRead(true);
          capture(paramDeclaration.base);
          return;
          if (localObject1 != localLambdaExp2) {
            break label201;
          }
          return;
          localObject3 = (LambdaExp)localObject2;
          if (((LambdaExp)localObject3).getInlineOnly()) {
            break;
          }
          if (((LambdaExp)localObject3).isHandlingTailCalls())
          {
            localObject2 = null;
            break label219;
          }
          localObject2 = localObject3;
          if (localObject3 != localObject1) {
            break label219;
          }
          localObject2 = localObject3;
          if (paramDeclaration.getCanRead()) {
            break label219;
          }
          return;
          if ((((LambdaExp)localObject3).nameDecl == null) || (!((LambdaExp)localObject3).nameDecl.getFlag(2048L))) {
            break label349;
          }
          paramDeclaration.setFlag(2048L);
        }
      }
    } while ((!paramDeclaration.getCanRead()) && (!paramDeclaration.getCanCall()) && (localObject2 != null));
    if (!paramDeclaration.isStatic()) {
      if (!paramDeclaration.isFluid()) {
        ((LambdaExp)localObject1).setImportsLexVars();
      }
    }
    for (Object localObject3 = ((LambdaExp)localObject1).outerLambda();; localObject3 = ((LambdaExp)localObject3).outerLambda())
    {
      if ((localObject3 == localLambdaExp2) || (localObject3 == null) || ((!paramDeclaration.getCanRead()) && (localObject2 == localObject3)))
      {
        if (localLambdaExp2 != null) {
          break;
        }
        System.err.println("null declLambda for " + paramDeclaration + " curL:" + localObject1);
        for (localObject1 = paramDeclaration.context; localObject1 != null; localObject1 = ((ScopeExp)localObject1).outer) {
          System.err.println("- context:" + localObject1);
        }
      }
      localObject4 = ((LambdaExp)localObject3).nameDecl;
      if ((localObject4 != null) && (((Declaration)localObject4).getFlag(2048L))) {
        this.comp.error('e', "static " + ((LambdaExp)localObject3).getName() + " references non-static " + paramDeclaration.getName());
      }
      if (((localObject3 instanceof ClassExp)) && (((LambdaExp)localObject3).getName() != null) && (((ClassExp)localObject3).isSimple())) {
        this.comp.error('w', ((LambdaExp)localObject3).nameDecl, "simple class ", " requiring lexical link (because of reference to " + paramDeclaration.getName() + ") - use define-class instead");
      }
      ((LambdaExp)localObject3).setNeedsStaticLink();
    }
    localLambdaExp2.capture(paramDeclaration);
  }
  
  void capture(Declaration paramDeclaration1, Declaration paramDeclaration2)
  {
    Declaration localDeclaration1 = paramDeclaration2;
    if (paramDeclaration2.isAlias())
    {
      localDeclaration1 = paramDeclaration2;
      if ((paramDeclaration2.value instanceof ReferenceExp))
      {
        ReferenceExp localReferenceExp = (ReferenceExp)paramDeclaration2.value;
        Declaration localDeclaration2 = localReferenceExp.binding;
        localDeclaration1 = paramDeclaration2;
        if (localDeclaration2 != null) {
          if (paramDeclaration1 != null)
          {
            localDeclaration1 = paramDeclaration2;
            if (localDeclaration2.needsContext()) {}
          }
          else
          {
            capture(localReferenceExp.contextDecl(), localDeclaration2);
            return;
          }
        }
      }
    }
    while ((localDeclaration1.isFluid()) && ((localDeclaration1.context instanceof FluidLetExp))) {
      localDeclaration1 = localDeclaration1.base;
    }
    if ((paramDeclaration1 != null) && (localDeclaration1.needsContext()))
    {
      capture(paramDeclaration1);
      return;
    }
    capture(localDeclaration1);
  }
  
  void maybeWarnNoDeclarationSeen(Object paramObject, Compilation paramCompilation, SourceLocator paramSourceLocator)
  {
    if (paramCompilation.warnUndefinedVariable()) {
      paramCompilation.error('w', "no declaration seen for " + paramObject, paramSourceLocator);
    }
  }
  
  protected Expression visitApplyExp(ApplyExp paramApplyExp, Void paramVoid)
  {
    int n = this.backJumpPossible;
    int m = 0;
    int k = 0;
    Object localObject1;
    int j;
    int i;
    if (((paramApplyExp.func instanceof ReferenceExp)) && (Compilation.defaultCallConvention <= 1))
    {
      localObject1 = Declaration.followAliases(((ReferenceExp)paramApplyExp.func).binding);
      j = k;
      i = m;
      if (localObject1 != null)
      {
        j = k;
        i = m;
        if ((((Declaration)localObject1).context instanceof ModuleExp))
        {
          j = k;
          i = m;
          if (!((Declaration)localObject1).isPublic())
          {
            j = k;
            i = m;
            if (!((Declaration)localObject1).getFlag(4096L))
            {
              localObject1 = ((Declaration)localObject1).getValue();
              j = k;
              i = m;
              if ((localObject1 instanceof LambdaExp))
              {
                j = k;
                i = m;
                if (!((LambdaExp)localObject1).getNeedsClosureEnv())
                {
                  i = 1;
                  j = k;
                }
              }
            }
          }
        }
      }
    }
    for (;;)
    {
      if (i == 0) {
        paramApplyExp.func = ((Expression)paramApplyExp.func.visit(this, paramVoid));
      }
      if ((this.exitValue == null) && (j == 0)) {
        paramApplyExp.args = visitExps(paramApplyExp.args, paramVoid);
      }
      if (this.backJumpPossible > n) {
        paramApplyExp.setFlag(8);
      }
      return paramApplyExp;
      j = k;
      i = m;
      if ((paramApplyExp.func instanceof QuoteExp))
      {
        j = k;
        i = m;
        if (paramApplyExp.getArgCount() > 0)
        {
          Object localObject2 = ((QuoteExp)paramApplyExp.func).getValue();
          localObject1 = paramApplyExp.getArg(0);
          j = k;
          i = m;
          if ((localObject2 instanceof PrimProcedure))
          {
            j = k;
            i = m;
            if ((localObject1 instanceof ReferenceExp))
            {
              localObject2 = (PrimProcedure)localObject2;
              localObject1 = Declaration.followAliases(((ReferenceExp)localObject1).binding);
              j = k;
              i = m;
              if (localObject1 != null)
              {
                j = k;
                i = m;
                if ((((Declaration)localObject1).context instanceof ModuleExp))
                {
                  j = k;
                  i = m;
                  if (!((Declaration)localObject1).getFlag(4096L))
                  {
                    localObject2 = ((Declaration)localObject1).getValue();
                    j = k;
                    i = m;
                    if ((localObject2 instanceof ClassExp))
                    {
                      Expression[] arrayOfExpression = paramApplyExp.getArgs();
                      j = k;
                      i = m;
                      if (!((LambdaExp)localObject2).getNeedsClosureEnv())
                      {
                        paramApplyExp.nextCall = ((Declaration)localObject1).firstCall;
                        ((Declaration)localObject1).firstCall = paramApplyExp;
                        i = 1;
                        while (i < arrayOfExpression.length)
                        {
                          arrayOfExpression[i].visit(this, paramVoid);
                          i += 1;
                        }
                        j = 1;
                        i = 1;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  protected Expression visitClassExp(ClassExp paramClassExp, Void paramVoid)
  {
    Expression localExpression = (Expression)super.visitClassExp(paramClassExp, paramVoid);
    if ((!paramClassExp.explicitInit) && (!paramClassExp.instanceType.isInterface())) {
      Compilation.getConstructor(paramClassExp.instanceType, paramClassExp);
    }
    for (;;)
    {
      if ((paramClassExp.isSimple()) && (paramClassExp.getNeedsClosureEnv()) && (paramClassExp.nameDecl != null) && (paramClassExp.nameDecl.getType() == Compilation.typeClass)) {
        paramClassExp.nameDecl.setType(Compilation.typeClassType);
      }
      return localExpression;
      if (paramClassExp.getNeedsClosureEnv()) {
        for (paramVoid = paramClassExp.firstChild; paramVoid != null; paramVoid = paramVoid.nextSibling) {
          if ("*init*".equals(paramVoid.getName())) {
            paramVoid.setNeedsStaticLink(true);
          }
        }
      }
    }
  }
  
  public void visitDefaultArgs(LambdaExp paramLambdaExp, Void paramVoid)
  {
    if (paramLambdaExp.defaultArgs == null) {}
    for (;;)
    {
      return;
      super.visitDefaultArgs(paramLambdaExp, paramVoid);
      for (paramVoid = paramLambdaExp.firstDecl(); paramVoid != null; paramVoid = paramVoid.nextDecl()) {
        if (!paramVoid.isSimple())
        {
          paramLambdaExp.setFlag(true, 512);
          return;
        }
      }
    }
  }
  
  protected Expression visitFluidLetExp(FluidLetExp paramFluidLetExp, Void paramVoid)
  {
    for (Declaration localDeclaration1 = paramFluidLetExp.firstDecl(); localDeclaration1 != null; localDeclaration1 = localDeclaration1.nextDecl()) {
      if (localDeclaration1.base == null)
      {
        Object localObject = localDeclaration1.getSymbol();
        Declaration localDeclaration2 = allocUnboundDecl(localObject, false);
        maybeWarnNoDeclarationSeen(localObject, this.comp, paramFluidLetExp);
        capture(localDeclaration2);
        localDeclaration1.base = localDeclaration2;
      }
    }
    return (Expression)super.visitLetExp(paramFluidLetExp, paramVoid);
  }
  
  protected Expression visitLambdaExp(LambdaExp paramLambdaExp, Void paramVoid)
  {
    if ((checkInlineable(paramLambdaExp, new LinkedHashSet()) != LambdaExp.unknownContinuation) && ((!(paramLambdaExp.outer instanceof ModuleExp)) || (paramLambdaExp.nameDecl == null)))
    {
      paramLambdaExp.setInlineOnly(true);
      this.backJumpPossible += 1;
    }
    return (Expression)super.visitLambdaExp(paramLambdaExp, paramVoid);
  }
  
  protected Expression visitLetExp(LetExp paramLetExp, Void paramVoid)
  {
    if ((paramLetExp.body instanceof BeginExp))
    {
      Expression[] arrayOfExpression1 = paramLetExp.inits;
      int m = arrayOfExpression1.length;
      Expression[] arrayOfExpression2 = ((BeginExp)paramLetExp.body).exps;
      int j = 0;
      Object localObject1 = paramLetExp.firstDecl();
      int i = 0;
      while ((i < arrayOfExpression2.length) && (j < m))
      {
        Object localObject3 = arrayOfExpression2[i];
        Object localObject2 = localObject1;
        int k = j;
        if ((localObject3 instanceof SetExp))
        {
          localObject3 = (SetExp)localObject3;
          localObject2 = localObject1;
          k = j;
          if (((SetExp)localObject3).binding == localObject1)
          {
            localObject2 = localObject1;
            k = j;
            if (arrayOfExpression1[j] == QuoteExp.nullExp)
            {
              localObject2 = localObject1;
              k = j;
              if (((SetExp)localObject3).isDefining())
              {
                localObject2 = ((SetExp)localObject3).new_value;
                if ((((localObject2 instanceof QuoteExp)) || ((localObject2 instanceof LambdaExp))) && (((Declaration)localObject1).getValue() == localObject2))
                {
                  arrayOfExpression1[j] = localObject2;
                  arrayOfExpression2[i] = QuoteExp.voidExp;
                }
                k = j + 1;
                localObject2 = ((Declaration)localObject1).nextDecl();
              }
            }
          }
        }
        i += 1;
        localObject1 = localObject2;
        j = k;
      }
    }
    return (Expression)super.visitLetExp(paramLetExp, paramVoid);
  }
  
  protected Expression visitModuleExp(ModuleExp paramModuleExp, Void paramVoid)
  {
    ModuleExp localModuleExp = this.currentModule;
    Hashtable localHashtable = this.unknownDecls;
    this.currentModule = paramModuleExp;
    this.unknownDecls = null;
    try
    {
      paramModuleExp = visitLambdaExp(paramModuleExp, paramVoid);
      return paramModuleExp;
    }
    finally
    {
      this.currentModule = localModuleExp;
      this.unknownDecls = localHashtable;
    }
  }
  
  protected Expression visitReferenceExp(ReferenceExp paramReferenceExp, Void paramVoid)
  {
    Declaration localDeclaration = paramReferenceExp.getBinding();
    paramVoid = localDeclaration;
    if (localDeclaration == null)
    {
      paramVoid = allocUnboundDecl(paramReferenceExp.getSymbol(), paramReferenceExp.isProcedureName());
      paramReferenceExp.setBinding(paramVoid);
    }
    if ((paramVoid.getFlag(65536L)) && (this.comp.resolve(paramReferenceExp.getSymbol(), paramReferenceExp.isProcedureName()) == null)) {
      maybeWarnNoDeclarationSeen(paramReferenceExp.getSymbol(), this.comp, paramReferenceExp);
    }
    capture(paramReferenceExp.contextDecl(), paramVoid);
    return paramReferenceExp;
  }
  
  protected Expression visitSetExp(SetExp paramSetExp, Void paramVoid)
  {
    Object localObject2 = paramSetExp.binding;
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = allocUnboundDecl(paramSetExp.getSymbol(), paramSetExp.isFuncDef());
      paramSetExp.binding = ((Declaration)localObject1);
    }
    if (!((Declaration)localObject1).ignorable())
    {
      localObject2 = localObject1;
      if (!paramSetExp.isDefining()) {
        localObject2 = Declaration.followAliases((Declaration)localObject1);
      }
      capture(paramSetExp.contextDecl(), (Declaration)localObject2);
    }
    return (Expression)super.visitSetExp(paramSetExp, paramVoid);
  }
  
  protected Expression visitThisExp(ThisExp paramThisExp, Void paramVoid)
  {
    if (paramThisExp.isForContext())
    {
      getCurrentLambda().setImportsLexVars();
      return paramThisExp;
    }
    return visitReferenceExp(paramThisExp, paramVoid);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\FindCapturedVars.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */