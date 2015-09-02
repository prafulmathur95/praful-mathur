package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;

public class BindingInitializer
  extends Initializer
{
  static final ClassType typeThreadLocation = ClassType.make("gnu.mapping.ThreadLocation");
  Declaration decl;
  Expression value;
  
  public BindingInitializer(Declaration paramDeclaration, Expression paramExpression)
  {
    this.decl = paramDeclaration;
    this.value = paramExpression;
    this.field = paramDeclaration.field;
  }
  
  public static void create(Declaration paramDeclaration, Expression paramExpression, Compilation paramCompilation)
  {
    paramExpression = new BindingInitializer(paramDeclaration, paramExpression);
    if (paramDeclaration.field != null)
    {
      if (!paramDeclaration.field.getStaticFlag()) {}
    }
    else {
      while (paramDeclaration.isStatic())
      {
        paramExpression.next = paramCompilation.clinitChain;
        paramCompilation.clinitChain = paramExpression;
        return;
      }
    }
    paramExpression.next = paramCompilation.mainLambda.initChain;
    paramCompilation.mainLambda.initChain = paramExpression;
  }
  
  public static Method makeLocationMethod(Object paramObject)
  {
    Type[] arrayOfType = new Type[1];
    if ((paramObject instanceof Symbol)) {
      arrayOfType[0] = Compilation.typeSymbol;
    }
    for (;;)
    {
      return Compilation.typeLocation.getDeclaredMethod("make", arrayOfType);
      arrayOfType[0] = Type.javalangStringType;
    }
  }
  
  public void emit(Compilation paramCompilation)
  {
    if (this.decl.ignorable()) {
      if (this.value != null) {
        this.value.compile(paramCompilation, Target.Ignore);
      }
    }
    CodeAttr localCodeAttr;
    Object localObject1;
    do
    {
      return;
      localCodeAttr = paramCompilation.getCode();
      if (!(this.value instanceof QuoteExp)) {
        break;
      }
      localObject1 = ((QuoteExp)this.value).getValue();
    } while ((localObject1 != null) && (!(localObject1 instanceof String)) && (paramCompilation.litTable.findLiteral(localObject1).field == this.field));
    int i = this.decl.getLineNumber();
    SourceMessages localSourceMessages = paramCompilation.getMessages();
    SourceLocator localSourceLocator = localSourceMessages.swapSourceLocator(this.decl);
    if (i > 0) {
      localCodeAttr.putLineNumber(this.decl.getFileName(), i);
    }
    if ((this.field != null) && (!this.field.getStaticFlag())) {
      localCodeAttr.emitPushThis();
    }
    Object localObject3;
    if (this.value == null) {
      if ((paramCompilation.getLanguage().hasSeparateFunctionNamespace()) && (this.decl.isProcedureDecl()))
      {
        localObject1 = EnvironmentKey.FUNCTION;
        localObject3 = this.decl.getSymbol();
        if (!this.decl.getFlag(268500992L)) {
          break label321;
        }
        Object localObject2 = localObject3;
        if ((localObject3 instanceof String)) {
          localObject2 = Namespace.EmptyNamespace.getSymbol((String)localObject3);
        }
        paramCompilation.compileConstant(localObject2, Target.pushObject);
        if (localObject1 != null) {
          break label310;
        }
        localCodeAttr.emitPushNull();
        label245:
        localCodeAttr.emitInvokeStatic(typeThreadLocation.getDeclaredMethod("getInstance", 2));
        label259:
        if (this.field != null) {
          break label446;
        }
        localObject1 = this.decl.getVariable();
        paramCompilation = (Compilation)localObject1;
        if (localObject1 == null) {
          paramCompilation = this.decl.allocateVariable(localCodeAttr);
        }
        localCodeAttr.emitStore(paramCompilation);
      }
    }
    for (;;)
    {
      localSourceMessages.swapSourceLocator(localSourceLocator);
      return;
      localObject1 = null;
      break;
      label310:
      paramCompilation.compileConstant(localObject1, Target.pushObject);
      break label245;
      label321:
      if (this.decl.isFluid())
      {
        if ((localObject3 instanceof Symbol)) {}
        for (localObject1 = Compilation.typeSymbol;; localObject1 = Type.toStringType)
        {
          paramCompilation.compileConstant(localObject3, Target.pushObject);
          localCodeAttr.emitInvokeStatic(typeThreadLocation.getDeclaredMethod("makeAnonymous", new Type[] { localObject1 }));
          break;
        }
      }
      paramCompilation.compileConstant(localObject3, Target.pushObject);
      localCodeAttr.emitInvokeStatic(makeLocationMethod(localObject3));
      break label259;
      if (this.field == null) {}
      for (localObject1 = this.decl.getType();; localObject1 = this.field.getType())
      {
        this.value.compileWithPosition(paramCompilation, StackTarget.getInstance((Type)localObject1));
        break;
      }
      label446:
      if (this.field.getStaticFlag()) {
        localCodeAttr.emitPutStatic(this.field);
      } else {
        localCodeAttr.emitPutField(this.field);
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\BindingInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */