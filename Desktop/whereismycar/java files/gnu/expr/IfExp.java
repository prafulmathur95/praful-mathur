package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.ExitableBlock;
import gnu.bytecode.Label;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Values;

public class IfExp
  extends Expression
{
  Expression else_clause;
  Expression test;
  Expression then_clause;
  
  public IfExp(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3)
  {
    this.test = paramExpression1;
    this.then_clause = paramExpression2;
    this.else_clause = paramExpression3;
  }
  
  public static void compile(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, Compilation paramCompilation, Target paramTarget)
  {
    Object localObject3 = paramCompilation.getLanguage();
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Object localObject2 = null;
    int i;
    Object localObject1;
    int j;
    if (((paramTarget instanceof ConditionalTarget)) && ((paramExpression3 instanceof QuoteExp)))
    {
      i = 1;
      if (((Language)localObject3).isTrue(((QuoteExp)paramExpression3).getValue()))
      {
        localObject1 = ((ConditionalTarget)paramTarget).ifTrue;
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new Label(localCodeAttr);
        }
        if ((paramExpression1 != paramExpression2) || (!(paramTarget instanceof ConditionalTarget)) || (!(paramExpression2 instanceof ReferenceExp))) {
          break label324;
        }
        j = 1;
        localObject1 = ((ConditionalTarget)paramTarget).ifTrue;
        label111:
        localObject3 = new ConditionalTarget((Label)localObject1, (Label)localObject2, (Language)localObject3);
        if (j != 0) {
          ((ConditionalTarget)localObject3).trueBranchComesFirst = false;
        }
        paramExpression1.compile(paramCompilation, (Target)localObject3);
        localCodeAttr.emitIfThen();
        if (j == 0)
        {
          ((Label)localObject1).define(localCodeAttr);
          paramExpression1 = paramCompilation.callContextVar;
          paramExpression2.compileWithPosition(paramCompilation, paramTarget);
          paramCompilation.callContextVar = paramExpression1;
        }
        if (i != 0) {
          break label351;
        }
        localCodeAttr.emitElse();
        ((Label)localObject2).define(localCodeAttr);
        paramExpression1 = paramCompilation.callContextVar;
        if (paramExpression3 != null) {
          break label341;
        }
        paramCompilation.compileConstant(Values.empty, paramTarget);
        label213:
        paramCompilation.callContextVar = paramExpression1;
      }
    }
    for (;;)
    {
      localCodeAttr.emitFi();
      return;
      localObject1 = ((ConditionalTarget)paramTarget).ifFalse;
      break;
      localObject1 = localObject2;
      if ((paramExpression3 instanceof ExitExp))
      {
        localObject1 = localObject2;
        if ((((ExitExp)paramExpression3).result instanceof QuoteExp))
        {
          BlockExp localBlockExp = ((ExitExp)paramExpression3).block;
          localObject1 = localObject2;
          if ((localBlockExp.exitTarget instanceof IgnoreTarget))
          {
            localObject2 = localBlockExp.exitableBlock.exitIsGoto();
            localObject1 = localObject2;
            if (localObject2 != null)
            {
              i = 1;
              localObject1 = localObject2;
              break;
            }
          }
        }
      }
      i = 0;
      break;
      label324:
      j = 0;
      localObject1 = new Label(localCodeAttr);
      break label111;
      label341:
      paramExpression3.compileWithPosition(paramCompilation, paramTarget);
      break label213;
      label351:
      localCodeAttr.setUnreachable();
    }
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    if (getLanguage().isTrue(this.test.eval(paramCallContext))) {
      this.then_clause.apply(paramCallContext);
    }
    while (this.else_clause == null) {
      return;
    }
    this.else_clause.apply(paramCallContext);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    Expression localExpression1 = this.test;
    Expression localExpression2 = this.then_clause;
    if (this.else_clause == null) {}
    for (Object localObject = QuoteExp.voidExp;; localObject = this.else_clause)
    {
      compile(localExpression1, localExpression2, (Expression)localObject, paramCompilation, paramTarget);
      return;
    }
  }
  
  public Expression getElseClause()
  {
    return this.else_clause;
  }
  
  protected final Language getLanguage()
  {
    return Language.getDefaultLanguage();
  }
  
  public Expression getTest()
  {
    return this.test;
  }
  
  public Expression getThenClause()
  {
    return this.then_clause;
  }
  
  public Type getType()
  {
    Type localType = this.then_clause.getType();
    if (this.else_clause == null) {}
    for (Object localObject = Type.voidType;; localObject = this.else_clause.getType()) {
      return Language.unionType(localType, (Type)localObject);
    }
  }
  
  protected boolean mustCompile()
  {
    return false;
  }
  
  public void print(OutPort paramOutPort)
  {
    paramOutPort.startLogicalBlock("(If ", false, ")");
    paramOutPort.setIndentation(-2, false);
    this.test.print(paramOutPort);
    paramOutPort.writeSpaceLinear();
    this.then_clause.print(paramOutPort);
    if (this.else_clause != null)
    {
      paramOutPort.writeSpaceLinear();
      this.else_clause.print(paramOutPort);
    }
    paramOutPort.endLogicalBlock(")");
  }
  
  Expression select(boolean paramBoolean)
  {
    if (paramBoolean) {
      return this.then_clause;
    }
    if (this.else_clause == null) {
      return QuoteExp.voidExp;
    }
    return this.else_clause;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitIfExp(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    this.test = paramExpVisitor.visitAndUpdate(this.test, paramD);
    if (paramExpVisitor.exitValue == null) {
      this.then_clause = paramExpVisitor.visitAndUpdate(this.then_clause, paramD);
    }
    if ((paramExpVisitor.exitValue == null) && (this.else_clause != null)) {
      this.else_clause = paramExpVisitor.visitAndUpdate(this.else_clause, paramD);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\IfExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */