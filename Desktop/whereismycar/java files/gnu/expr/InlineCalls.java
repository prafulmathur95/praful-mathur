package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.text.SourceMessages;
import java.lang.reflect.InvocationTargetException;

public class InlineCalls
  extends ExpExpVisitor<Type>
{
  private static Class[] inlinerMethodArgTypes;
  
  public InlineCalls(Compilation paramCompilation)
  {
    setContext(paramCompilation);
  }
  
  public static Integer checkIntValue(Expression paramExpression)
  {
    if ((paramExpression instanceof QuoteExp))
    {
      paramExpression = (QuoteExp)paramExpression;
      Object localObject = paramExpression.getValue();
      if ((!paramExpression.isExplicitlyTyped()) && ((localObject instanceof IntNum)))
      {
        paramExpression = (IntNum)localObject;
        if (paramExpression.inIntRange()) {
          return Integer.valueOf(paramExpression.intValue());
        }
      }
    }
    return null;
  }
  
  public static Long checkLongValue(Expression paramExpression)
  {
    if ((paramExpression instanceof QuoteExp))
    {
      paramExpression = (QuoteExp)paramExpression;
      Object localObject = paramExpression.getValue();
      if ((!paramExpression.isExplicitlyTyped()) && ((localObject instanceof IntNum)))
      {
        paramExpression = (IntNum)localObject;
        if (paramExpression.inLongRange()) {
          return Long.valueOf(paramExpression.longValue());
        }
      }
    }
    return null;
  }
  
  private static Class[] getInlinerMethodArgTypes()
    throws Exception
  {
    try
    {
      Class[] arrayOfClass2 = inlinerMethodArgTypes;
      Class[] arrayOfClass1 = arrayOfClass2;
      if (arrayOfClass2 == null)
      {
        arrayOfClass1 = new Class[4];
        arrayOfClass1[0] = Class.forName("gnu.expr.ApplyExp");
        arrayOfClass1[1] = Class.forName("gnu.expr.InlineCalls");
        arrayOfClass1[2] = Class.forName("gnu.bytecode.Type");
        arrayOfClass1[3] = Class.forName("gnu.mapping.Procedure");
        inlinerMethodArgTypes = arrayOfClass1;
      }
      return arrayOfClass1;
    }
    finally {}
  }
  
  public static Expression inlineCall(LambdaExp paramLambdaExp, Expression[] paramArrayOfExpression, boolean paramBoolean)
  {
    if ((paramLambdaExp.keywords != null) || ((paramLambdaExp.nameDecl != null) && (!paramBoolean))) {
      return null;
    }
    int i;
    if (paramLambdaExp.max_args < 0) {
      i = 1;
    }
    while (((paramLambdaExp.min_args == paramLambdaExp.max_args) && (paramLambdaExp.min_args == paramArrayOfExpression.length)) || ((i != 0) && (paramLambdaExp.min_args == 0)))
    {
      Object localObject3 = null;
      int j = 0;
      Object localObject4;
      Object localObject2;
      if (paramBoolean)
      {
        localObject4 = new IdentityHashTable();
        localObject5 = Expression.deepCopy(paramArrayOfExpression, (IdentityHashTable)localObject4);
        localObject1 = localObject5;
        localObject2 = localObject4;
        if (localObject5 == null)
        {
          localObject1 = localObject5;
          localObject2 = localObject4;
          if (paramArrayOfExpression != null)
          {
            return null;
            i = 0;
          }
        }
      }
      else
      {
        localObject2 = null;
        localObject1 = paramArrayOfExpression;
      }
      if (i != 0)
      {
        localObject4 = new Expression[paramArrayOfExpression.length + 1];
        localObject4[0] = QuoteExp.getInstance(paramLambdaExp.firstDecl().type);
        System.arraycopy(paramArrayOfExpression, 0, localObject4, 1, paramArrayOfExpression.length);
        localObject1 = new Expression[1];
        localObject1[0] = new ApplyExp(Invoke.make, (Expression[])localObject4);
      }
      Object localObject5 = new LetExp((Expression[])localObject1);
      paramArrayOfExpression = paramLambdaExp.firstDecl();
      if (paramArrayOfExpression != null)
      {
        localObject4 = paramArrayOfExpression.nextDecl();
        if (paramBoolean)
        {
          localObject3 = ((LetExp)localObject5).addDeclaration(paramArrayOfExpression.symbol, paramArrayOfExpression.type);
          if (paramArrayOfExpression.typeExp != null)
          {
            ((Declaration)localObject3).typeExp = Expression.deepCopy(paramArrayOfExpression.typeExp);
            if (((Declaration)localObject3).typeExp == null) {
              return null;
            }
          }
          ((IdentityHashTable)localObject2).put(paramArrayOfExpression, localObject3);
        }
        for (;;)
        {
          if ((i == 0) && (!paramArrayOfExpression.getCanWrite())) {
            paramArrayOfExpression.setValue(localObject1[j]);
          }
          localObject3 = paramArrayOfExpression;
          paramArrayOfExpression = (Expression[])localObject4;
          j += 1;
          break;
          paramLambdaExp.remove((Declaration)localObject3, paramArrayOfExpression);
          ((LetExp)localObject5).add((Declaration)localObject3, paramArrayOfExpression);
        }
      }
      Object localObject1 = paramLambdaExp.body;
      paramArrayOfExpression = (Expression[])localObject1;
      if (paramBoolean)
      {
        localObject1 = Expression.deepCopy((Expression)localObject1, (IdentityHashTable)localObject2);
        paramArrayOfExpression = (Expression[])localObject1;
        if (localObject1 == null)
        {
          paramArrayOfExpression = (Expression[])localObject1;
          if (paramLambdaExp.body != null) {
            return null;
          }
        }
      }
      ((LetExp)localObject5).body = paramArrayOfExpression;
      return (Expression)localObject5;
    }
    return null;
  }
  
  public static Expression inlineCalls(Expression paramExpression, Compilation paramCompilation)
  {
    return new InlineCalls(paramCompilation).visit(paramExpression, null);
  }
  
  public Expression checkType(Expression paramExpression, Type paramType)
  {
    int i = 1;
    Object localObject2 = paramExpression.getType();
    Object localObject3;
    int j;
    if (((paramType instanceof ClassType)) && (((ClassType)paramType).isInterface()) && (((Type)localObject2).isSubtype(Compilation.typeProcedure)) && (!((Type)localObject2).isSubtype(paramType)))
    {
      if ((paramExpression instanceof LambdaExp))
      {
        localObject1 = ((ClassType)paramType).checkSingleAbstractMethod();
        if (localObject1 != null)
        {
          localObject2 = (LambdaExp)paramExpression;
          localObject3 = new ObjectExp();
          ((ObjectExp)localObject3).setLocation(paramExpression);
          ((ObjectExp)localObject3).supers = new Expression[] { new QuoteExp(paramType) };
          ((ObjectExp)localObject3).setTypes(getCompilation());
          paramExpression = ((gnu.bytecode.Method)localObject1).getName();
          ((ObjectExp)localObject3).addMethod((LambdaExp)localObject2, paramExpression);
          ((ObjectExp)localObject3).addDeclaration(paramExpression, Compilation.typeProcedure);
          ((ObjectExp)localObject3).firstChild = ((LambdaExp)localObject2);
          ((ObjectExp)localObject3).declareParts(this.comp);
          return visit((Expression)localObject3, paramType);
        }
      }
      j = 1;
      if (j != 0)
      {
        localObject1 = this.comp.getLanguage();
        this.comp.error('w', "type " + ((Language)localObject1).formatType((Type)localObject2) + " is incompatible with required type " + ((Language)localObject1).formatType(paramType), paramExpression);
      }
      return paramExpression;
    }
    Object localObject1 = localObject2;
    if (localObject2 == Type.toStringType) {
      localObject1 = Type.javalangStringType;
    }
    if ((paramType != null) && (paramType.compare((Type)localObject1) == -3)) {}
    for (;;)
    {
      localObject2 = localObject1;
      j = i;
      if (i == 0) {
        break;
      }
      localObject2 = localObject1;
      j = i;
      if (!(paramType instanceof TypeValue)) {
        break;
      }
      localObject3 = ((TypeValue)paramType).convertValue(paramExpression);
      localObject2 = localObject1;
      j = i;
      if (localObject3 == null) {
        break;
      }
      return (Expression)localObject3;
      i = 0;
    }
  }
  
  public QuoteExp fixIntValue(Expression paramExpression)
  {
    paramExpression = checkIntValue(paramExpression);
    if (paramExpression != null) {
      return new QuoteExp(paramExpression, this.comp.getLanguage().getTypeFor(Integer.TYPE));
    }
    return null;
  }
  
  public QuoteExp fixLongValue(Expression paramExpression)
  {
    paramExpression = checkLongValue(paramExpression);
    if (paramExpression != null) {
      return new QuoteExp(paramExpression, this.comp.getLanguage().getTypeFor(Long.TYPE));
    }
    return null;
  }
  
  public Expression maybeInline(ApplyExp paramApplyExp, Type paramType, Procedure paramProcedure)
  {
    label255:
    label280:
    for (;;)
    {
      Object localObject2;
      Object localObject1;
      try
      {
        try
        {
          localObject2 = paramProcedure.getProperty(Procedure.validateApplyKey, null);
          localObject1 = localObject2;
          if ((localObject2 instanceof String))
          {
            localObject2 = (String)localObject2;
            int i = ((String)localObject2).indexOf(':');
            localObject1 = null;
            if (i > 0)
            {
              localObject1 = ((String)localObject2).substring(0, i);
              localObject2 = ((String)localObject2).substring(i + 1);
              localObject1 = Class.forName((String)localObject1, true, paramProcedure.getClass().getClassLoader()).getDeclaredMethod((String)localObject2, getInlinerMethodArgTypes());
            }
            if (localObject1 != null) {
              break label280;
            }
            error('e', "inliner property string for " + paramProcedure + " is not of the form CLASS:METHOD");
            return null;
          }
          if (localObject1 != null)
          {
            localObject2 = new Object[4];
            localObject2[0] = paramApplyExp;
            localObject2[1] = this;
            localObject2[2] = paramType;
            localObject2[3] = paramProcedure;
            if (!(localObject1 instanceof Procedure)) {
              break label255;
            }
            paramApplyExp = (Expression)((Procedure)localObject1).applyN((Object[])localObject2);
            return paramApplyExp;
          }
        }
        finally {}
        return null;
      }
      catch (Throwable paramType)
      {
        paramApplyExp = paramType;
        if ((paramType instanceof InvocationTargetException)) {
          paramApplyExp = ((InvocationTargetException)paramType).getTargetException();
        }
        this.messages.error('e', "caught exception in inliner for " + paramProcedure + " - " + paramApplyExp, paramApplyExp);
      }
      while (!(localObject1 instanceof java.lang.reflect.Method)) {}
      paramApplyExp = (Expression)((java.lang.reflect.Method)localObject1).invoke(null, (Object[])localObject2);
      return paramApplyExp;
    }
  }
  
  public Expression visit(Expression paramExpression, Type paramType)
  {
    Expression localExpression = paramExpression;
    if (!paramExpression.getFlag(1))
    {
      paramExpression.setFlag(1);
      localExpression = (Expression)super.visit(paramExpression, paramType);
      localExpression.setFlag(1);
    }
    return checkType(localExpression, paramType);
  }
  
  protected Expression visitApplyExp(ApplyExp paramApplyExp, Type paramType)
  {
    Expression localExpression = paramApplyExp.func;
    if ((localExpression instanceof LambdaExp))
    {
      Object localObject = (LambdaExp)localExpression;
      localObject = inlineCall((LambdaExp)localExpression, paramApplyExp.args, false);
      if (localObject != null) {
        return visit((Expression)localObject, paramType);
      }
    }
    localExpression = visit(localExpression, null);
    paramApplyExp.func = localExpression;
    return localExpression.validateApply(paramApplyExp, this, paramType, null);
  }
  
  public final Expression visitApplyOnly(ApplyExp paramApplyExp, Type paramType)
  {
    return paramApplyExp.func.validateApply(paramApplyExp, this, paramType, null);
  }
  
  protected Expression visitBeginExp(BeginExp paramBeginExp, Type paramType)
  {
    int j = paramBeginExp.length - 1;
    int i = 0;
    if (i <= j)
    {
      Expression[] arrayOfExpression = paramBeginExp.exps;
      Expression localExpression = paramBeginExp.exps[i];
      if (i < j) {}
      for (Type localType = null;; localType = paramType)
      {
        arrayOfExpression[i] = visit(localExpression, localType);
        i += 1;
        break;
      }
    }
    return paramBeginExp;
  }
  
  protected Expression visitIfExp(IfExp paramIfExp, Type paramType)
  {
    Expression localExpression = (Expression)paramIfExp.test.visit(this, null);
    Object localObject1 = localExpression;
    if ((localExpression instanceof ReferenceExp))
    {
      Object localObject2 = ((ReferenceExp)localExpression).getBinding();
      localObject1 = localExpression;
      if (localObject2 != null)
      {
        localObject2 = ((Declaration)localObject2).getValue();
        localObject1 = localExpression;
        if ((localObject2 instanceof QuoteExp))
        {
          localObject1 = localExpression;
          if (localObject2 != QuoteExp.undefined_exp) {
            localObject1 = localObject2;
          }
        }
      }
    }
    paramIfExp.test = ((Expression)localObject1);
    if (this.exitValue == null) {
      paramIfExp.then_clause = visit(paramIfExp.then_clause, paramType);
    }
    if ((this.exitValue == null) && (paramIfExp.else_clause != null)) {
      paramIfExp.else_clause = visit(paramIfExp.else_clause, paramType);
    }
    if ((localObject1 instanceof QuoteExp)) {
      paramType = paramIfExp.select(this.comp.getLanguage().isTrue(((QuoteExp)localObject1).getValue()));
    }
    do
    {
      return paramType;
      paramType = paramIfExp;
    } while (!((Expression)localObject1).getType().isVoid());
    boolean bool = this.comp.getLanguage().isTrue(Values.empty);
    this.comp.error('w', "void-valued condition is always " + bool);
    return new BeginExp((Expression)localObject1, paramIfExp.select(bool));
  }
  
  protected Expression visitLambdaExp(LambdaExp paramLambdaExp, Type paramType)
  {
    Declaration localDeclaration = paramLambdaExp.firstDecl();
    if ((localDeclaration != null) && (localDeclaration.isThisParameter()) && (!paramLambdaExp.isClassMethod()) && (localDeclaration.type == null)) {
      localDeclaration.setType(this.comp.mainClass);
    }
    return visitScopeExp(paramLambdaExp, paramType);
  }
  
  protected Expression visitLetExp(LetExp paramLetExp, Type paramType)
  {
    Object localObject1 = paramLetExp.firstDecl();
    int j = paramLetExp.inits.length;
    int i = 0;
    if (i < j)
    {
      Expression localExpression = paramLetExp.inits[i];
      boolean bool = ((Declaration)localObject1).getFlag(8192L);
      if ((bool) && (localExpression != QuoteExp.undefined_exp)) {}
      for (Object localObject2 = ((Declaration)localObject1).getType();; localObject2 = null)
      {
        localObject2 = visit(localExpression, (Type)localObject2);
        paramLetExp.inits[i] = localObject2;
        if (((Declaration)localObject1).value == localExpression)
        {
          ((Declaration)localObject1).value = ((Expression)localObject2);
          if (!bool) {
            ((Declaration)localObject1).setType(((Expression)localObject2).getType());
          }
        }
        i += 1;
        localObject1 = ((Declaration)localObject1).nextDecl();
        break;
      }
    }
    if (this.exitValue == null) {
      paramLetExp.body = visit(paramLetExp.body, paramType);
    }
    if ((paramLetExp.body instanceof ReferenceExp))
    {
      paramType = (ReferenceExp)paramLetExp.body;
      localObject1 = paramType.getBinding();
      if ((localObject1 != null) && (((Declaration)localObject1).context == paramLetExp) && (!paramType.getDontDereference()) && (j == 1))
      {
        paramType = paramLetExp.inits[0];
        localObject1 = ((Declaration)localObject1).getTypeExp();
        paramLetExp = paramType;
        if (localObject1 != QuoteExp.classObjectExp) {
          paramLetExp = visitApplyOnly(Compilation.makeCoercion(paramType, (Expression)localObject1), null);
        }
        return paramLetExp;
      }
    }
    return paramLetExp;
  }
  
  protected Expression visitQuoteExp(QuoteExp paramQuoteExp, Type paramType)
  {
    Object localObject1 = paramQuoteExp;
    int i;
    if (paramQuoteExp.getRawType() == null)
    {
      localObject1 = paramQuoteExp;
      if (!paramQuoteExp.isSharedConstant())
      {
        Object localObject2 = paramQuoteExp.getValue();
        localObject1 = paramQuoteExp;
        if (localObject2 != null)
        {
          localObject2 = this.comp.getLanguage().getTypeFor(localObject2.getClass());
          localObject1 = localObject2;
          if (localObject2 == Type.toStringType) {
            localObject1 = Type.javalangStringType;
          }
          paramQuoteExp.type = ((Type)localObject1);
          localObject1 = paramQuoteExp;
          if ((paramType instanceof PrimType))
          {
            i = paramType.getSignature().charAt(0);
            if (i != 73) {
              break label110;
            }
            paramType = fixIntValue(paramQuoteExp);
          }
        }
      }
    }
    for (;;)
    {
      localObject1 = paramQuoteExp;
      if (paramType != null) {
        localObject1 = paramType;
      }
      return (Expression)localObject1;
      label110:
      if (i == 74) {
        paramType = fixLongValue(paramQuoteExp);
      } else {
        paramType = null;
      }
    }
  }
  
  protected Expression visitReferenceExp(ReferenceExp paramReferenceExp, Type paramType)
  {
    Declaration localDeclaration1 = paramReferenceExp.getBinding();
    if ((localDeclaration1 != null) && (localDeclaration1.field == null) && (!localDeclaration1.getCanWrite()))
    {
      Object localObject = localDeclaration1.getValue();
      if (((localObject instanceof QuoteExp)) && (localObject != QuoteExp.undefined_exp)) {
        return visitQuoteExp((QuoteExp)localObject, paramType);
      }
      if (((localObject instanceof ReferenceExp)) && (!localDeclaration1.isAlias()))
      {
        localObject = (ReferenceExp)localObject;
        Declaration localDeclaration2 = ((ReferenceExp)localObject).getBinding();
        Type localType = localDeclaration1.getType();
        if ((localDeclaration2 != null) && (!localDeclaration2.getCanWrite()) && ((localType == null) || (localType == Type.pointer_type) || (localType == localDeclaration2.getType())) && (!((ReferenceExp)localObject).getDontDereference())) {
          return visitReferenceExp((ReferenceExp)localObject, paramType);
        }
      }
      if ((!paramReferenceExp.isProcedureName()) && ((localDeclaration1.flags & 0x100080) == 1048704L))
      {
        this.comp.error('e', "unimplemented: reference to method " + localDeclaration1.getName() + " as variable");
        this.comp.error('e', localDeclaration1, "here is the definition of ", "");
      }
    }
    return (Expression)super.visitReferenceExp(paramReferenceExp, paramType);
  }
  
  protected Expression visitScopeExp(ScopeExp paramScopeExp, Type paramType)
  {
    paramScopeExp.visitChildren(this, null);
    visitDeclarationTypes(paramScopeExp);
    paramType = paramScopeExp.firstDecl();
    if (paramType != null)
    {
      if (paramType.type == null)
      {
        localObject = paramType.getValue();
        paramType.type = Type.objectType;
        if ((localObject == null) || (localObject == QuoteExp.undefined_exp)) {
          break label68;
        }
      }
      label68:
      for (Object localObject = ((Expression)localObject).getType();; localObject = Type.objectType)
      {
        paramType.setType((Type)localObject);
        paramType = paramType.nextDecl();
        break;
      }
    }
    return paramScopeExp;
  }
  
  protected Expression visitSetExp(SetExp paramSetExp, Type paramType)
  {
    Declaration localDeclaration = paramSetExp.getBinding();
    super.visitSetExp(paramSetExp, paramType);
    if ((!paramSetExp.isDefining()) && (localDeclaration != null) && ((localDeclaration.flags & 0x100080) == 1048704L)) {
      this.comp.error('e', "can't assign to method " + localDeclaration.getName(), paramSetExp);
    }
    if ((localDeclaration != null) && (localDeclaration.getFlag(8192L)) && (CompileReflect.checkKnownClass(localDeclaration.getType(), this.comp) < 0)) {
      localDeclaration.setType(Type.errorType);
    }
    return paramSetExp;
  }
  
  protected Expression visitSetExpValue(Expression paramExpression, Type paramType, Declaration paramDeclaration)
  {
    if ((paramDeclaration == null) || (paramDeclaration.isAlias())) {}
    for (paramType = null;; paramType = paramDeclaration.type) {
      return visit(paramExpression, paramType);
    }
  }
  
  protected Expression visitTryExp(TryExp paramTryExp, Type paramType)
  {
    if ((paramTryExp.getCatchClauses() == null) && (paramTryExp.getFinallyClause() == null)) {
      return visit(paramTryExp.try_clause, paramType);
    }
    return (Expression)super.visitTryExp(paramTryExp, paramType);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\InlineCalls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */