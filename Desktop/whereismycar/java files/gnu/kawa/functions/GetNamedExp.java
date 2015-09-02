package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.PrimProcedure;
import gnu.expr.ReferenceExp;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.Symbol;

class GetNamedExp
  extends ApplyExp
{
  static final Declaration castDecl = Declaration.getDeclarationFromStatic("gnu.kawa.functions.Convert", "as");
  static final Declaration fieldDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.SlotGet", "field");
  static final Declaration instanceOfDecl;
  static final Declaration invokeDecl;
  static final Declaration invokeStaticDecl;
  static final Declaration makeDecl;
  static final Declaration staticFieldDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.SlotGet", "staticField");
  public String combinedName;
  char kind;
  PrimProcedure[] methods;
  
  static
  {
    makeDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "make");
    invokeDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "invoke");
    invokeStaticDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "invokeStatic");
    instanceOfDecl = Declaration.getDeclarationFromStatic("kawa.standard.Scheme", "instanceOf");
  }
  
  public GetNamedExp(Expression[] paramArrayOfExpression)
  {
    super(GetNamedPart.getNamedPart, paramArrayOfExpression);
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    if (this.combinedName != null)
    {
      Object localObject = Environment.getCurrent();
      Symbol localSymbol = ((Environment)localObject).getSymbol(this.combinedName);
      String str = Location.UNBOUND;
      localObject = ((Environment)localObject).get(localSymbol, null, str);
      if (localObject != str)
      {
        paramCallContext.writeValue(localObject);
        return;
      }
    }
    super.apply(paramCallContext);
  }
  
  protected GetNamedExp setProcedureKind(char paramChar)
  {
    this.type = Compilation.typeProcedure;
    this.kind = paramChar;
    return this;
  }
  
  public boolean side_effects()
  {
    if ((this.kind == 'S') || (this.kind == 'N') || (this.kind == 'C') || (this.kind == 'I')) {
      return false;
    }
    if (this.kind == 'M') {
      return getArgs()[0].side_effects();
    }
    return true;
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration)
  {
    Expression[] arrayOfExpression2 = getArgs();
    Expression localExpression = arrayOfExpression2[0];
    Expression[] arrayOfExpression1 = paramApplyExp.getArgs();
    Declaration localDeclaration;
    switch (this.kind)
    {
    default: 
      return paramApplyExp;
    case 'M': 
      localDeclaration = invokeDecl;
      paramDeclaration = new Expression[arrayOfExpression1.length + 2];
      paramDeclaration[0] = arrayOfExpression2[0];
      paramDeclaration[1] = arrayOfExpression2[1];
      System.arraycopy(arrayOfExpression1, 0, paramDeclaration, 2, arrayOfExpression1.length);
    }
    for (;;)
    {
      paramDeclaration = new ApplyExp(new ReferenceExp(localDeclaration), paramDeclaration);
      paramDeclaration.setLine(paramApplyExp);
      return paramInlineCalls.visit(paramDeclaration, paramType);
      localDeclaration = makeDecl;
      paramDeclaration = new Expression[arrayOfExpression1.length + 1];
      System.arraycopy(arrayOfExpression1, 0, paramDeclaration, 1, arrayOfExpression1.length);
      paramDeclaration[0] = localExpression;
      continue;
      localDeclaration = instanceOfDecl;
      paramDeclaration = new Expression[arrayOfExpression1.length + 1];
      System.arraycopy(arrayOfExpression1, 1, paramDeclaration, 2, arrayOfExpression1.length - 1);
      paramDeclaration[0] = arrayOfExpression1[0];
      paramDeclaration[1] = localExpression;
      continue;
      localDeclaration = castDecl;
      paramDeclaration = new Expression[arrayOfExpression1.length + 1];
      System.arraycopy(arrayOfExpression1, 1, paramDeclaration, 2, arrayOfExpression1.length - 1);
      paramDeclaration[0] = localExpression;
      paramDeclaration[1] = arrayOfExpression1[0];
      continue;
      localDeclaration = invokeStaticDecl;
      paramDeclaration = new Expression[arrayOfExpression1.length + 2];
      paramDeclaration[0] = localExpression;
      paramDeclaration[1] = arrayOfExpression2[1];
      System.arraycopy(arrayOfExpression1, 0, paramDeclaration, 2, arrayOfExpression1.length);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\GetNamedExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */