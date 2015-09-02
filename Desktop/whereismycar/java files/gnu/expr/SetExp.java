package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.functions.AddOp;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class SetExp
  extends AccessExp
{
  public static final int BAD_SHORT = 65536;
  public static final int DEFINING_FLAG = 2;
  public static final int GLOBAL_FLAG = 4;
  public static final int HAS_VALUE = 64;
  public static final int PREFER_BINDING2 = 8;
  public static final int PROCEDURE = 16;
  public static final int SET_IF_UNBOUND = 32;
  Expression new_value;
  
  public SetExp(Declaration paramDeclaration, Expression paramExpression)
  {
    this.binding = paramDeclaration;
    this.symbol = paramDeclaration.getSymbol();
    this.new_value = paramExpression;
  }
  
  public SetExp(Object paramObject, Expression paramExpression)
  {
    this.symbol = paramObject;
    this.new_value = paramExpression;
  }
  
  public static int canUseInc(Expression paramExpression, Declaration paramDeclaration)
  {
    Object localObject1 = paramDeclaration.getVariable();
    int i;
    if ((paramDeclaration.isSimple()) && (((Variable)localObject1).getType().getImplementationType().promote() == Type.intType) && ((paramExpression instanceof ApplyExp)))
    {
      paramExpression = (ApplyExp)paramExpression;
      if (paramExpression.getArgCount() == 2)
      {
        localObject1 = paramExpression.getFunction().valueIfConstant();
        if (localObject1 != AddOp.$Pl) {
          break label137;
        }
        i = 1;
        localObject1 = paramExpression.getArg(0);
        Expression localExpression = paramExpression.getArg(1);
        Object localObject2 = localObject1;
        paramExpression = localExpression;
        if ((localObject1 instanceof QuoteExp))
        {
          localObject2 = localObject1;
          paramExpression = localExpression;
          if (i > 0)
          {
            paramExpression = (Expression)localObject1;
            localObject2 = localExpression;
          }
        }
        if ((localObject2 instanceof ReferenceExp))
        {
          localObject1 = (ReferenceExp)localObject2;
          if ((((ReferenceExp)localObject1).getBinding() == paramDeclaration) && (!((ReferenceExp)localObject1).getDontDereference())) {
            break label150;
          }
        }
      }
    }
    for (;;)
    {
      return 65536;
      label137:
      if (localObject1 == AddOp.$Mn)
      {
        i = -1;
        break;
        label150:
        paramExpression = paramExpression.valueIfConstant();
        int k;
        int j;
        if ((paramExpression instanceof Integer))
        {
          k = ((Integer)paramExpression).intValue();
          j = k;
          if (i < 0) {
            j = -k;
          }
          if ((short)j == j) {
            return j;
          }
        }
        else if ((paramExpression instanceof IntNum))
        {
          paramExpression = (IntNum)paramExpression;
          j = 32767;
          k = -'翿';
          if (i > 0) {
            k -= 1;
          }
          while ((IntNum.compare(paramExpression, k) >= 0) && (IntNum.compare(paramExpression, j) <= 0))
          {
            return i * paramExpression.intValue();
            j = '翿' + 1;
          }
        }
      }
    }
  }
  
  public static SetExp makeDefinition(Declaration paramDeclaration, Expression paramExpression)
  {
    paramDeclaration = new SetExp(paramDeclaration, paramExpression);
    paramDeclaration.setDefining(true);
    return paramDeclaration;
  }
  
  public static SetExp makeDefinition(Object paramObject, Expression paramExpression)
  {
    paramObject = new SetExp(paramObject, paramExpression);
    ((SetExp)paramObject).setDefining(true);
    return (SetExp)paramObject;
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    Environment localEnvironment = Environment.getCurrent();
    Object localObject1;
    Object localObject3;
    Object localObject2;
    if ((this.symbol instanceof Symbol))
    {
      localObject1 = (Symbol)this.symbol;
      localObject3 = null;
      Language localLanguage = Language.getDefaultLanguage();
      localObject2 = localObject3;
      if (isFuncDef())
      {
        localObject2 = localObject3;
        if (localLanguage.hasSeparateFunctionNamespace()) {
          localObject2 = EnvironmentKey.FUNCTION;
        }
      }
      if (!isSetIfUnbound()) {
        break label120;
      }
      localObject1 = localEnvironment.getLocation((Symbol)localObject1, localObject2);
      if (!((Location)localObject1).isBound()) {
        ((Location)localObject1).set(this.new_value.eval(localEnvironment));
      }
      if (getHasValue()) {
        paramCallContext.writeValue(localObject1);
      }
    }
    for (;;)
    {
      return;
      localObject1 = localEnvironment.getSymbol(this.symbol.toString());
      break;
      label120:
      localObject3 = this.new_value.eval(localEnvironment);
      if ((this.binding != null) && (!(this.binding.context instanceof ModuleExp)))
      {
        localObject2 = paramCallContext.evalFrames[ScopeExp.nesting(this.binding.context)];
        if (this.binding.isIndirectBinding())
        {
          if (isDefining()) {
            localObject2[this.binding.evalIndex] = Location.make((Symbol)localObject1);
          }
          ((Location)localObject2[this.binding.evalIndex]).set(this.new_value);
        }
      }
      while (getHasValue())
      {
        paramCallContext.writeValue(localObject3);
        return;
        localObject2[this.binding.evalIndex] = localObject3;
        continue;
        if (isDefining()) {
          localEnvironment.define((Symbol)localObject1, localObject2, localObject3);
        } else {
          localEnvironment.put((Symbol)localObject1, localObject2, localObject3);
        }
      }
    }
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    if (((this.new_value instanceof LambdaExp)) && ((paramTarget instanceof IgnoreTarget)) && (((LambdaExp)this.new_value).getInlineOnly())) {
      return;
    }
    CodeAttr localCodeAttr = paramCompilation.getCode();
    int k;
    int j;
    int n;
    int m;
    int i1;
    int i2;
    int i3;
    int i;
    Object localObject2;
    Object localObject1;
    if ((getHasValue()) && (!(paramTarget instanceof IgnoreTarget)))
    {
      k = 1;
      j = 0;
      n = 0;
      m = 0;
      i1 = 0;
      i2 = 0;
      i3 = 0;
      i = 0;
      localObject2 = this.binding;
      localObject1 = ((Declaration)localObject2).getValue();
      if ((!(localObject1 instanceof LambdaExp)) || (!(((Declaration)localObject2).context instanceof ModuleExp)) || (((Declaration)localObject2).ignorable()) || (((LambdaExp)localObject1).getName() == null) || (localObject1 != this.new_value)) {
        break label170;
      }
      ((LambdaExp)this.new_value).compileSetField(paramCompilation);
    }
    for (;;)
    {
      if ((k == 0) || (i != 0)) {
        break label1027;
      }
      throw new Error("SetExp.compile: not implemented - return value");
      k = 0;
      break;
      label170:
      if (((((Declaration)localObject2).shouldEarlyInit()) || (((Declaration)localObject2).isAlias())) && ((((Declaration)localObject2).context instanceof ModuleExp)) && (isDefining()) && (!((Declaration)localObject2).ignorable()))
      {
        if (((Declaration)localObject2).shouldEarlyInit()) {
          BindingInitializer.create((Declaration)localObject2, this.new_value, paramCompilation);
        }
        if (k != 0)
        {
          ((Declaration)localObject2).load(this, 0, paramCompilation, Target.pushObject);
          i = 1;
        }
      }
      else
      {
        localObject1 = this;
        Object localObject3 = contextDecl();
        Object localObject6 = localObject1;
        Object localObject4 = localObject2;
        Object localObject5 = localObject3;
        if (!isDefining()) {}
        for (;;)
        {
          localObject6 = localObject1;
          localObject4 = localObject2;
          localObject5 = localObject3;
          if (localObject2 != null)
          {
            localObject6 = localObject1;
            localObject4 = localObject2;
            localObject5 = localObject3;
            if (((Declaration)localObject2).isAlias())
            {
              localObject4 = ((Declaration)localObject2).getValue();
              if ((localObject4 instanceof ReferenceExp)) {
                break label361;
              }
              localObject5 = localObject3;
              localObject4 = localObject2;
              localObject6 = localObject1;
            }
          }
          label361:
          ReferenceExp localReferenceExp;
          Declaration localDeclaration;
          do
          {
            do
            {
              if (!((Declaration)localObject4).ignorable()) {
                break label432;
              }
              this.new_value.compile(paramCompilation, Target.Ignore);
              break;
              localReferenceExp = (ReferenceExp)localObject4;
              localDeclaration = localReferenceExp.binding;
              localObject6 = localObject1;
              localObject4 = localObject2;
              localObject5 = localObject3;
            } while (localDeclaration == null);
            if (localObject3 == null) {
              break label415;
            }
            localObject6 = localObject1;
            localObject4 = localObject2;
            localObject5 = localObject3;
          } while (localDeclaration.needsContext());
          label415:
          localObject3 = localReferenceExp.contextDecl();
          localObject1 = localReferenceExp;
          localObject2 = localDeclaration;
        }
        label432:
        if ((((Declaration)localObject4).isAlias()) && (isDefining()))
        {
          ((Declaration)localObject4).load(this, 2, paramCompilation, Target.pushObject);
          localObject1 = ClassType.make("gnu.mapping.IndirectableLocation");
          localCodeAttr.emitCheckcast((Type)localObject1);
          this.new_value.compile(paramCompilation, Target.pushObject);
          localCodeAttr.emitInvokeVirtual(((ClassType)localObject1).getDeclaredMethod("setAlias", 1));
        }
        else if (((Declaration)localObject4).isIndirectBinding())
        {
          ((Declaration)localObject4).load((AccessExp)localObject6, 2, paramCompilation, Target.pushObject);
          i = n;
          if (isSetIfUnbound())
          {
            i = j;
            if (k != 0)
            {
              localCodeAttr.emitDup();
              i = 1;
            }
            localCodeAttr.pushScope();
            localCodeAttr.emitDup();
            localObject1 = localCodeAttr.addLocal(Compilation.typeLocation);
            localCodeAttr.emitStore((Variable)localObject1);
            localCodeAttr.emitInvokeVirtual(Compilation.typeLocation.getDeclaredMethod("isBound", 0));
            localCodeAttr.emitIfIntEqZero();
            localCodeAttr.emitLoad((Variable)localObject1);
          }
          this.new_value.compile(paramCompilation, Target.pushObject);
          j = i;
          if (k != 0)
          {
            j = i;
            if (!isSetIfUnbound())
            {
              localCodeAttr.emitDupX();
              j = 1;
            }
          }
          localCodeAttr.emitInvokeVirtual(Compilation.typeLocation.getDeclaredMethod("set", 1));
          i = j;
          if (isSetIfUnbound())
          {
            localCodeAttr.emitFi();
            localCodeAttr.popScope();
            i = j;
          }
        }
        else if (((Declaration)localObject4).isSimple())
        {
          localObject3 = ((Declaration)localObject4).getType();
          localObject2 = ((Declaration)localObject4).getVariable();
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = ((Declaration)localObject4).allocateVariable(localCodeAttr);
          }
          j = canUseInc(this.new_value, (Declaration)localObject4);
          if (j != 65536)
          {
            paramCompilation.getCode().emitInc((Variable)localObject1, (short)j);
            if (k != 0)
            {
              localCodeAttr.emitLoad((Variable)localObject1);
              i = 1;
            }
          }
          else
          {
            this.new_value.compile(paramCompilation, (Declaration)localObject4);
            i = m;
            if (k != 0)
            {
              localCodeAttr.emitDup((Type)localObject3);
              i = 1;
            }
            localCodeAttr.emitStore((Variable)localObject1);
          }
        }
        else if (((((Declaration)localObject4).context instanceof ClassExp)) && (((Declaration)localObject4).field == null) && (!getFlag(16)) && (((ClassExp)((Declaration)localObject4).context).isMakingClassPair()))
        {
          localObject2 = ClassExp.slotToMethodName("set", ((Declaration)localObject4).getName());
          localObject1 = (ClassExp)((Declaration)localObject4).context;
          localObject2 = ((ClassExp)localObject1).type.getDeclaredMethod((String)localObject2, 1);
          ((ClassExp)localObject1).loadHeapFrame(paramCompilation);
          this.new_value.compile(paramCompilation, (Declaration)localObject4);
          i = i1;
          if (k != 0)
          {
            localCodeAttr.emitDupX();
            i = 1;
          }
          localCodeAttr.emitInvoke((Method)localObject2);
        }
        else
        {
          localObject1 = ((Declaration)localObject4).field;
          if (!((Field)localObject1).getStaticFlag()) {
            ((Declaration)localObject4).loadOwningObject((Declaration)localObject5, paramCompilation);
          }
          localObject2 = ((Field)localObject1).getType();
          this.new_value.compile(paramCompilation, (Declaration)localObject4);
          paramCompilation.usedClass(((Field)localObject1).getDeclaringClass());
          if (((Field)localObject1).getStaticFlag())
          {
            i = i2;
            if (k != 0)
            {
              localCodeAttr.emitDup((Type)localObject2);
              i = 1;
            }
            localCodeAttr.emitPutStatic((Field)localObject1);
          }
          else
          {
            i = i3;
            if (k != 0)
            {
              localCodeAttr.emitDupX();
              i = 1;
            }
            localCodeAttr.emitPutField((Field)localObject1);
          }
        }
      }
    }
    label1027:
    if (k != 0)
    {
      paramTarget.compileFromStack(paramCompilation, getType());
      return;
    }
    paramCompilation.compileConstant(Values.empty, paramTarget);
  }
  
  public final boolean getHasValue()
  {
    return (this.flags & 0x40) != 0;
  }
  
  public final Expression getNewValue()
  {
    return this.new_value;
  }
  
  public final Type getType()
  {
    if (!getHasValue()) {
      return Type.voidType;
    }
    if (this.binding == null) {
      return Type.pointer_type;
    }
    return this.binding.getType();
  }
  
  public final boolean isDefining()
  {
    return (this.flags & 0x2) != 0;
  }
  
  public final boolean isFuncDef()
  {
    return (this.flags & 0x10) != 0;
  }
  
  public final boolean isSetIfUnbound()
  {
    return (this.flags & 0x20) != 0;
  }
  
  protected boolean mustCompile()
  {
    return false;
  }
  
  public void print(OutPort paramOutPort)
  {
    if (isDefining()) {}
    for (String str = "(Define";; str = "(Set")
    {
      paramOutPort.startLogicalBlock(str, ")", 2);
      paramOutPort.writeSpaceFill();
      printLineColumn(paramOutPort);
      if ((this.binding == null) || (this.symbol.toString() != this.binding.getName()))
      {
        paramOutPort.print('/');
        paramOutPort.print(this.symbol);
      }
      if (this.binding != null)
      {
        paramOutPort.print('/');
        paramOutPort.print(this.binding);
      }
      paramOutPort.writeSpaceLinear();
      this.new_value.print(paramOutPort);
      paramOutPort.endLogicalBlock(")");
      return;
    }
  }
  
  public final void setDefining(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x2;
      return;
    }
    this.flags &= 0xFFFFFFFD;
  }
  
  public final void setFuncDef(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x10;
      return;
    }
    this.flags &= 0xFFFFFFEF;
  }
  
  public final void setHasValue(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x40;
      return;
    }
    this.flags &= 0xFFFFFFBF;
  }
  
  public final void setSetIfUnbound(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x20;
      return;
    }
    this.flags &= 0xFFFFFFDF;
  }
  
  public String toString()
  {
    return "SetExp[" + this.symbol + ":=" + this.new_value + ']';
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitSetExp(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    this.new_value = paramExpVisitor.visitAndUpdate(this.new_value, paramD);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\SetExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */