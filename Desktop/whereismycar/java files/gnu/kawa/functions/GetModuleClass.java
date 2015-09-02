package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BindingInitializer;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.Target;
import gnu.mapping.Namespace;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.text.URLPath;

public class GetModuleClass
  extends ProcedureN
  implements Inlineable
{
  private static Symbol CLASS_RESOURCE_NAME = Namespace.getDefaultSymbol("$class_resource_URL$");
  public static final GetModuleClass getModuleClass = new GetModuleClass();
  public static final GetModuleClass getModuleUri = new GetModuleClass();
  public static final GetModuleClass getModuleUriDummy = new GetModuleClass();
  static final Method maker;
  static final ClassType typeURLPath = ClassType.make("gnu.text.URLPath");
  
  static
  {
    maker = typeURLPath.getDeclaredMethod("classResourcePath", 1);
  }
  
  public static Expression getModuleClassURI(Compilation paramCompilation)
  {
    Object localObject2 = paramCompilation.mainLambda.lookup(CLASS_RESOURCE_NAME);
    Object localObject1 = localObject2;
    Declaration localDeclaration;
    if (localObject2 == null)
    {
      localDeclaration = new Declaration(CLASS_RESOURCE_NAME, typeURLPath);
      localDeclaration.setFlag(536889344L);
      if (!paramCompilation.immediate) {
        break label119;
      }
      localObject2 = paramCompilation.minfo.getSourceAbsPath();
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = Path.currentPath();
      }
      localObject2 = localObject1;
      if (!(localObject1 instanceof URLPath)) {
        localObject2 = URLPath.valueOf(((Path)localObject1).toURL());
      }
    }
    for (localObject1 = QuoteExp.getInstance(localObject2);; localObject1 = new ApplyExp(maker, new Expression[] { localObject1 }))
    {
      localDeclaration.setValue((Expression)localObject1);
      paramCompilation.mainLambda.add(null, localDeclaration);
      localObject1 = localDeclaration;
      localObject1 = new ReferenceExp((Declaration)localObject1);
      if (!paramCompilation.immediate) {
        break;
      }
      return (Expression)localObject1;
      label119:
      localObject1 = new ApplyExp(getModuleClass, Expression.noExpressions);
    }
    return new ApplyExp(getModuleUriDummy, new Expression[] { localObject1 });
  }
  
  public Object applyN(Object[] paramArrayOfObject)
  {
    throw new Error("get-module-class must be inlined");
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    if (this == getModuleUriDummy)
    {
      paramApplyExp = (ReferenceExp)paramApplyExp.getArgs()[0];
      paramApplyExp.compile(paramCompilation, paramTarget);
      paramApplyExp = paramApplyExp.getBinding();
      paramTarget = paramApplyExp.getValue();
      if (paramTarget != null)
      {
        BindingInitializer.create(paramApplyExp, paramTarget, paramCompilation);
        paramApplyExp.setValue(null);
      }
      return;
    }
    paramCompilation.loadClassRef(paramCompilation.mainClass);
    if (this == getModuleUri) {
      paramCompilation.getCode().emitInvoke(maker);
    }
    paramTarget.compileFromStack(paramCompilation, paramApplyExp.getType());
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    if (this == getModuleClass) {
      return Type.javalangClassType;
    }
    return typeURLPath;
  }
  
  public int numArgs()
  {
    if (this == getModuleUriDummy) {
      return 4097;
    }
    return 0;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\GetModuleClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */