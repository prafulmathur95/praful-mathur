package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.UnboundLocationException;

public class ReferenceExp
  extends AccessExp
{
  public static final int DONT_DEREFERENCE = 2;
  public static final int PREFER_BINDING2 = 8;
  public static final int PROCEDURE_NAME = 4;
  public static final int TYPE_NAME = 16;
  static int counter;
  int id;
  
  public ReferenceExp(Declaration paramDeclaration)
  {
    this(paramDeclaration.getSymbol(), paramDeclaration);
  }
  
  public ReferenceExp(Object paramObject)
  {
    int i = counter + 1;
    counter = i;
    this.id = i;
    this.symbol = paramObject;
  }
  
  public ReferenceExp(Object paramObject, Declaration paramDeclaration)
  {
    int i = counter + 1;
    counter = i;
    this.id = i;
    this.symbol = paramObject;
    this.binding = paramDeclaration;
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    Object localObject3 = null;
    Object localObject1;
    if ((this.binding != null) && (this.binding.isAlias()) && (!getDontDereference()) && ((this.binding.value instanceof ReferenceExp)))
    {
      localObject1 = (ReferenceExp)this.binding.value;
      if ((((ReferenceExp)localObject1).getDontDereference()) && (((ReferenceExp)localObject1).binding != null))
      {
        localObject1 = ((ReferenceExp)localObject1).binding.getValue();
        if (((localObject1 instanceof QuoteExp)) || ((localObject1 instanceof ReferenceExp)) || ((localObject1 instanceof LambdaExp)))
        {
          ((Expression)localObject1).apply(paramCallContext);
          return;
        }
      }
      localObject1 = this.binding.value.eval(paramCallContext);
    }
    for (;;)
    {
      Object localObject2 = localObject1;
      if (!getDontDereference())
      {
        localObject2 = localObject1;
        if (this.binding.isIndirectBinding()) {
          localObject2 = ((Location)localObject1).get();
        }
      }
      paramCallContext.writeValue(localObject2);
      return;
      if ((this.binding != null) && (this.binding.field != null) && (this.binding.field.getDeclaringClass().isExisting()) && ((!getDontDereference()) || (this.binding.isIndirectBinding())))
      {
        try
        {
          if (this.binding.field.getStaticFlag()) {}
          for (localObject1 = null;; localObject1 = contextDecl().getValue().eval(paramCallContext))
          {
            localObject1 = this.binding.field.getReflectField().get(localObject1);
            break;
          }
          if (this.binding == null) {
            break label379;
          }
        }
        catch (Exception paramCallContext)
        {
          throw new UnboundLocationException("exception evaluating " + this.symbol + " from " + this.binding.field + " - " + paramCallContext, this);
        }
      }
      else if ((((this.binding.value instanceof QuoteExp)) || ((this.binding.value instanceof LambdaExp))) && (this.binding.value != QuoteExp.undefined_exp) && ((!getDontDereference()) || (this.binding.isIndirectBinding())))
      {
        localObject1 = this.binding.value.eval(paramCallContext);
      }
      else
      {
        label379:
        if ((this.binding == null) || (((this.binding.context instanceof ModuleExp)) && (!this.binding.isPrivate())))
        {
          Environment localEnvironment = Environment.getCurrent();
          if ((this.symbol instanceof Symbol))
          {
            localObject1 = (Symbol)this.symbol;
            localObject2 = localObject3;
            if (getFlag(8))
            {
              localObject2 = localObject3;
              if (isProcedureName()) {
                localObject2 = EnvironmentKey.FUNCTION;
              }
            }
            if (!getDontDereference()) {
              break label495;
            }
            localObject2 = localEnvironment.getLocation((Symbol)localObject1, localObject2);
          }
          label495:
          String str;
          do
          {
            paramCallContext.writeValue(localObject2);
            return;
            localObject1 = localEnvironment.getSymbol(this.symbol.toString());
            break;
            str = Location.UNBOUND;
            localObject3 = localEnvironment.get((Symbol)localObject1, localObject2, str);
            localObject2 = localObject3;
          } while (localObject3 != str);
          throw new UnboundLocationException(localObject1, this);
        }
        localObject1 = paramCallContext.evalFrames[ScopeExp.nesting(this.binding.context)][this.binding.evalIndex];
      }
    }
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    if ((!(paramTarget instanceof ConsumerTarget)) || (!((ConsumerTarget)paramTarget).compileWrite(this, paramCompilation))) {
      this.binding.load(this, this.flags, paramCompilation, paramTarget);
    }
  }
  
  protected Expression deepCopy(IdentityHashTable paramIdentityHashTable)
  {
    Declaration localDeclaration = (Declaration)paramIdentityHashTable.get(this.binding, this.binding);
    paramIdentityHashTable = new ReferenceExp(paramIdentityHashTable.get(this.symbol, this.symbol), localDeclaration);
    paramIdentityHashTable.flags = getFlags();
    return paramIdentityHashTable;
  }
  
  public final boolean getDontDereference()
  {
    return (this.flags & 0x2) != 0;
  }
  
  public Type getType()
  {
    Object localObject1 = this.binding;
    Object localObject2;
    if ((localObject1 == null) || (((Declaration)localObject1).isFluid())) {
      localObject2 = Type.pointer_type;
    }
    do
    {
      return (Type)localObject2;
      if (getDontDereference()) {
        return Compilation.typeLocation;
      }
      Declaration localDeclaration = Declaration.followAliases((Declaration)localObject1);
      localObject2 = localDeclaration.getType();
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (localObject2 != Type.pointer_type) {}
      }
      else
      {
        Expression localExpression = localDeclaration.getValue();
        localObject1 = localObject2;
        if (localExpression != null)
        {
          localObject1 = localObject2;
          if (localExpression != QuoteExp.undefined_exp)
          {
            localObject2 = localDeclaration.value;
            localDeclaration.value = null;
            localObject1 = localExpression.getType();
            localDeclaration.value = ((Expression)localObject2);
          }
        }
      }
      localObject2 = localObject1;
    } while (localObject1 != Type.toStringType);
    return Type.javalangStringType;
  }
  
  public final boolean isProcedureName()
  {
    return (this.flags & 0x4) != 0;
  }
  
  public boolean isSingleValue()
  {
    if ((this.binding != null) && (this.binding.getFlag(262144L))) {
      return true;
    }
    return super.isSingleValue();
  }
  
  public final boolean isUnknown()
  {
    return Declaration.isUnknown(this.binding);
  }
  
  protected boolean mustCompile()
  {
    return false;
  }
  
  public void print(OutPort paramOutPort)
  {
    paramOutPort.print("(Ref/");
    paramOutPort.print(this.id);
    if ((this.symbol != null) && ((this.binding == null) || (this.symbol.toString() != this.binding.getName())))
    {
      paramOutPort.print('/');
      paramOutPort.print(this.symbol);
    }
    if (this.binding != null)
    {
      paramOutPort.print('/');
      paramOutPort.print(this.binding);
    }
    paramOutPort.print(")");
  }
  
  public final void setDontDereference(boolean paramBoolean)
  {
    setFlag(paramBoolean, 2);
  }
  
  public final void setProcedureName(boolean paramBoolean)
  {
    setFlag(paramBoolean, 4);
  }
  
  public boolean side_effects()
  {
    return (this.binding == null) || (!this.binding.isLexical());
  }
  
  public String toString()
  {
    return "RefExp/" + this.symbol + '/' + this.id + '/';
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration)
  {
    paramDeclaration = this.binding;
    if ((paramDeclaration != null) && (!paramDeclaration.getFlag(65536L)))
    {
      paramDeclaration = Declaration.followAliases(paramDeclaration);
      if (!paramDeclaration.isIndirectBinding())
      {
        Expression localExpression = paramDeclaration.getValue();
        if (localExpression != null) {
          return localExpression.validateApply(paramApplyExp, paramInlineCalls, paramType, paramDeclaration);
        }
      }
    }
    else if ((getSymbol() instanceof Symbol))
    {
      paramDeclaration = (Symbol)getSymbol();
      paramDeclaration = Environment.getCurrent().getFunction(paramDeclaration, null);
      if ((paramDeclaration instanceof Procedure)) {
        return new QuoteExp(paramDeclaration).validateApply(paramApplyExp, paramInlineCalls, paramType, null);
      }
    }
    paramApplyExp.visitArgs(paramInlineCalls);
    return paramApplyExp;
  }
  
  public final Object valueIfConstant()
  {
    if (this.binding != null)
    {
      Expression localExpression = this.binding.getValue();
      if (localExpression != null) {
        return localExpression.valueIfConstant();
      }
    }
    return null;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitReferenceExp(this, paramD);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ReferenceExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */