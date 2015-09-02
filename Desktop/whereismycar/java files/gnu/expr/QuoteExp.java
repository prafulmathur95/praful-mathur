package gnu.expr;

import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.lists.AbstractFormat;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.mapping.WrongArguments;
import gnu.text.SourceLocator;

public class QuoteExp
  extends Expression
{
  public static final int EXPLICITLY_TYPED = 2;
  public static final int SHARED_CONSTANT = 4;
  public static QuoteExp abstractExp;
  public static final QuoteExp classObjectExp = makeShared(Type.objectType);
  public static QuoteExp falseExp;
  public static QuoteExp nullExp;
  public static QuoteExp trueExp;
  public static QuoteExp undefined_exp = makeShared(Special.undefined);
  public static QuoteExp voidExp;
  protected Type type;
  Object value;
  
  static
  {
    abstractExp = makeShared(Special.abstractSpecial);
    voidExp = makeShared(Values.empty, Type.voidType);
    trueExp = makeShared(Boolean.TRUE);
    falseExp = makeShared(Boolean.FALSE);
    nullExp = makeShared(null, Type.nullType);
  }
  
  public QuoteExp(Object paramObject)
  {
    this.value = paramObject;
  }
  
  public QuoteExp(Object paramObject, Type paramType)
  {
    this.value = paramObject;
    setType(paramType);
  }
  
  public static QuoteExp getInstance(Object paramObject)
  {
    return getInstance(paramObject, null);
  }
  
  public static QuoteExp getInstance(Object paramObject, SourceLocator paramSourceLocator)
  {
    if (paramObject == null) {
      return nullExp;
    }
    if (paramObject == Type.pointer_type) {
      return classObjectExp;
    }
    if (paramObject == Special.undefined) {
      return undefined_exp;
    }
    if (paramObject == Values.empty) {
      return voidExp;
    }
    if ((paramObject instanceof Boolean))
    {
      if (((Boolean)paramObject).booleanValue()) {
        return trueExp;
      }
      return falseExp;
    }
    paramObject = new QuoteExp(paramObject);
    if (paramSourceLocator != null) {
      ((QuoteExp)paramObject).setLocation(paramSourceLocator);
    }
    return (QuoteExp)paramObject;
  }
  
  static QuoteExp makeShared(Object paramObject)
  {
    paramObject = new QuoteExp(paramObject);
    ((QuoteExp)paramObject).setFlag(4);
    return (QuoteExp)paramObject;
  }
  
  static QuoteExp makeShared(Object paramObject, Type paramType)
  {
    paramObject = new QuoteExp(paramObject, paramType);
    ((QuoteExp)paramObject).setFlag(4);
    return (QuoteExp)paramObject;
  }
  
  public void apply(CallContext paramCallContext)
  {
    paramCallContext.writeValue(this.value);
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    if ((this.type == null) || (this.type == Type.pointer_type) || ((paramTarget instanceof IgnoreTarget)) || (((this.type instanceof ObjectType)) && (this.type.isInstance(this.value))))
    {
      paramCompilation.compileConstant(this.value, paramTarget);
      return;
    }
    paramCompilation.compileConstant(this.value, StackTarget.getInstance(this.type));
    paramTarget.compileFromStack(paramCompilation, this.type);
  }
  
  public Expression deepCopy(IdentityHashTable paramIdentityHashTable)
  {
    return this;
  }
  
  public final Type getRawType()
  {
    return this.type;
  }
  
  public final Type getType()
  {
    if (this.type != null) {
      return this.type;
    }
    if (this.value == Values.empty) {
      return Type.voidType;
    }
    if (this.value == null) {
      return Type.nullType;
    }
    if (this == undefined_exp) {
      return Type.pointer_type;
    }
    return Type.make(this.value.getClass());
  }
  
  public final Object getValue()
  {
    return this.value;
  }
  
  public boolean isExplicitlyTyped()
  {
    return getFlag(2);
  }
  
  public boolean isSharedConstant()
  {
    return getFlag(4);
  }
  
  protected boolean mustCompile()
  {
    return false;
  }
  
  public void print(OutPort paramOutPort)
  {
    paramOutPort.startLogicalBlock("(Quote", ")", 2);
    paramOutPort.writeSpaceLinear();
    Object localObject3 = this.value;
    Object localObject1 = localObject3;
    if ((localObject3 instanceof Expression)) {
      localObject1 = localObject3.toString();
    }
    localObject3 = paramOutPort.objectFormat;
    try
    {
      paramOutPort.objectFormat = Language.getDefaultLanguage().getFormat(true);
      paramOutPort.print(localObject1);
      if (this.type != null)
      {
        paramOutPort.print(" ::");
        paramOutPort.print(this.type.getName());
      }
      paramOutPort.objectFormat = ((AbstractFormat)localObject3);
      paramOutPort.endLogicalBlock(")");
      return;
    }
    finally
    {
      paramOutPort.objectFormat = ((AbstractFormat)localObject3);
    }
  }
  
  public void setType(Type paramType)
  {
    this.type = paramType;
    setFlag(2);
  }
  
  public boolean side_effects()
  {
    return false;
  }
  
  public String toString()
  {
    return "QuoteExp[" + this.value + "]";
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration)
  {
    if (this == undefined_exp) {}
    int j;
    label162:
    label265:
    label271:
    do
    {
      Procedure localProcedure;
      do
      {
        do
        {
          return paramApplyExp;
          Object localObject1 = getValue();
          if (!(localObject1 instanceof Procedure))
          {
            if ((paramDeclaration == null) || (localObject1 == null)) {}
            for (paramApplyExp = "called value is not a procedure";; paramApplyExp = "calling " + paramDeclaration.getName() + " which is a " + localObject1.getClass().getName()) {
              return paramInlineCalls.noteError(paramApplyExp);
            }
          }
          localProcedure = (Procedure)localObject1;
          j = paramApplyExp.getArgCount();
          localObject1 = WrongArguments.checkArgCount(localProcedure, j);
          if (localObject1 != null) {
            return paramInlineCalls.noteError((String)localObject1);
          }
          localObject1 = paramInlineCalls.maybeInline(paramApplyExp, paramType, localProcedure);
          if (localObject1 != null) {
            return (Expression)localObject1;
          }
          Expression[] arrayOfExpression = paramApplyExp.args;
          MethodProc localMethodProc;
          int i;
          if ((localProcedure instanceof MethodProc))
          {
            localMethodProc = (MethodProc)localProcedure;
            i = 0;
            if (i >= j) {
              break label271;
            }
            if (localMethodProc == null) {
              break label265;
            }
          }
          for (localObject1 = localMethodProc.getParameterType(i);; localObject1 = null)
          {
            Object localObject2 = localObject1;
            if (i == j - 1)
            {
              localObject2 = localObject1;
              if (localObject1 != null)
              {
                localObject2 = localObject1;
                if (localMethodProc.maxArgs() < 0)
                {
                  localObject2 = localObject1;
                  if (i == localMethodProc.minArgs()) {
                    localObject2 = null;
                  }
                }
              }
            }
            arrayOfExpression[i] = paramInlineCalls.visit(arrayOfExpression[i], (Type)localObject2);
            i += 1;
            break label162;
            localMethodProc = null;
            break;
          }
          if (paramApplyExp.getFlag(4))
          {
            localObject1 = paramApplyExp.inlineIfConstant(localProcedure, paramInlineCalls);
            if (localObject1 != paramApplyExp) {
              return paramInlineCalls.visit((Expression)localObject1, paramType);
            }
          }
          paramInlineCalls = paramInlineCalls.getCompilation();
        } while (!paramInlineCalls.inlineOk(localProcedure));
        if (ApplyExp.asInlineable(localProcedure) == null) {
          break;
        }
      } while (paramApplyExp.getFunction() == this);
      return new ApplyExp(this, paramApplyExp.getArgs()).setLine(paramApplyExp);
      paramInlineCalls = PrimProcedure.getMethodFor(localProcedure, paramDeclaration, paramApplyExp.args, paramInlineCalls.getLanguage());
    } while (paramInlineCalls == null);
    if ((paramInlineCalls.getStaticFlag()) || (paramDeclaration == null)) {}
    for (paramInlineCalls = new ApplyExp(paramInlineCalls, paramApplyExp.args);; paramInlineCalls = new ApplyExp(paramInlineCalls, paramType))
    {
      return paramInlineCalls.setLine(paramApplyExp);
      if (paramDeclaration.base == null) {
        break;
      }
      paramType = new Expression[j + 1];
      System.arraycopy(paramApplyExp.getArgs(), 0, paramType, 1, j);
      paramType[0] = new ReferenceExp(paramDeclaration.base);
    }
  }
  
  public final Object valueIfConstant()
  {
    return this.value;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitQuoteExp(this, paramD);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\QuoteExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */