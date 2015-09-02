package gnu.kawa.functions;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.reflect.ArrayGet;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.math.Numeric;
import gnu.text.Char;
import java.io.Externalizable;

public class CompilationHelpers
{
  public static final Declaration setterDecl;
  static final Field setterField;
  static final ClassType setterType;
  static final ClassType typeList = ClassType.make("java.util.List");
  
  static
  {
    setterType = ClassType.make("gnu.kawa.functions.Setter");
    setterField = setterType.getDeclaredField("setter");
    setterDecl = new Declaration("setter", setterField);
    setterDecl.noteValue(new QuoteExp(Setter.setter));
  }
  
  private static boolean nonNumeric(Expression paramExpression)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramExpression instanceof QuoteExp))
    {
      paramExpression = ((QuoteExp)paramExpression).getValue();
      bool1 = bool2;
      if (!(paramExpression instanceof Numeric))
      {
        bool1 = bool2;
        if (!(paramExpression instanceof Char))
        {
          bool1 = bool2;
          if (!(paramExpression instanceof Symbol)) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public static Expression validateApplyToArgs(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int i = arrayOfExpression.length - 1;
    if (i >= 0)
    {
      Object localObject1 = arrayOfExpression[0];
      paramProcedure = (Procedure)localObject1;
      if (!((Expression)localObject1).getFlag(1))
      {
        if ((localObject1 instanceof LambdaExp))
        {
          paramProcedure = new Expression[i];
          System.arraycopy(arrayOfExpression, 1, paramProcedure, 0, i);
          return paramInlineCalls.visit(new ApplyExp((Expression)localObject1, paramProcedure).setLine(paramApplyExp), paramType);
        }
        paramProcedure = paramInlineCalls.visit((Expression)localObject1, null);
        arrayOfExpression[0] = paramProcedure;
      }
      Object localObject2 = paramProcedure.getType().getRealType();
      Compilation localCompilation = paramInlineCalls.getCompilation();
      Language localLanguage = localCompilation.getLanguage();
      if (((Type)localObject2).isSubtype(Compilation.typeProcedure))
      {
        localObject1 = new Expression[i];
        System.arraycopy(arrayOfExpression, 1, localObject1, 0, i);
        localObject1 = new ApplyExp(paramProcedure, (Expression[])localObject1);
        ((ApplyExp)localObject1).setLine(paramApplyExp);
        return paramProcedure.validateApply((ApplyExp)localObject1, paramInlineCalls, paramType, null);
      }
      localObject1 = null;
      if (CompileReflect.checkKnownClass((Type)localObject2, localCompilation) < 0) {
        paramProcedure = (Procedure)localObject1;
      }
      while (paramProcedure != null)
      {
        paramProcedure.setLine(paramApplyExp);
        return paramInlineCalls.visitApplyOnly(paramProcedure, paramType);
        if ((((Type)localObject2).isSubtype(Compilation.typeType)) || (localLanguage.getTypeFor(paramProcedure, false) != null))
        {
          paramProcedure = new ApplyExp(Invoke.make, arrayOfExpression);
        }
        else if ((localObject2 instanceof ArrayType))
        {
          paramProcedure = new ApplyExp(new ArrayGet(((ArrayType)localObject2).getComponentType()), arrayOfExpression);
        }
        else
        {
          paramProcedure = (Procedure)localObject1;
          if ((localObject2 instanceof ClassType))
          {
            localObject2 = (ClassType)localObject2;
            paramProcedure = (Procedure)localObject1;
            if (((ClassType)localObject2).isSubclass(typeList))
            {
              paramProcedure = (Procedure)localObject1;
              if (i == 1) {
                paramProcedure = new ApplyExp(((ClassType)localObject2).getMethod("get", new Type[] { Type.intType }), arrayOfExpression);
              }
            }
          }
        }
      }
    }
    paramApplyExp.visitArgs(paramInlineCalls);
    return paramApplyExp;
  }
  
  public static Expression validateIsEqv(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramInlineCalls = paramApplyExp.getArgs();
    if ((nonNumeric(paramInlineCalls[0])) || (nonNumeric(paramInlineCalls[1]))) {
      paramApplyExp = new ApplyExp(((IsEqv)paramProcedure).isEq, paramInlineCalls);
    }
    return paramApplyExp;
  }
  
  public static Expression validateSetter(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramType = paramApplyExp.getArgs();
    paramInlineCalls = paramApplyExp;
    if (paramType.length == 1)
    {
      paramInlineCalls = paramType[0];
      paramProcedure = paramInlineCalls.getType();
      if (!(paramProcedure instanceof ArrayType)) {
        break label49;
      }
      paramInlineCalls = new SetArrayExp(paramInlineCalls, (ArrayType)paramProcedure);
    }
    label49:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return paramInlineCalls;
              if ((!(paramProcedure instanceof ClassType)) || (!((ClassType)paramProcedure).isSubclass(typeList))) {
                break;
              }
              paramInlineCalls = paramApplyExp;
            } while ((paramApplyExp instanceof SetListExp));
            return new SetListExp(paramApplyExp.getFunction(), paramType);
            paramType = paramInlineCalls;
            if ((paramInlineCalls instanceof ReferenceExp))
            {
              paramProcedure = ((ReferenceExp)paramInlineCalls).getBinding();
              paramType = paramInlineCalls;
              if (paramProcedure != null) {
                paramType = paramProcedure.getValue();
              }
            }
            paramInlineCalls = paramApplyExp;
          } while (!(paramType instanceof QuoteExp));
          paramType = ((QuoteExp)paramType).getValue();
          paramInlineCalls = paramApplyExp;
        } while (!(paramType instanceof Procedure));
        paramType = ((Procedure)paramType).getSetter();
        paramInlineCalls = paramApplyExp;
      } while (!(paramType instanceof Procedure));
      if ((paramType instanceof Externalizable)) {
        return new QuoteExp(paramType);
      }
      paramType = Declaration.getDeclaration((Procedure)paramType);
      paramInlineCalls = paramApplyExp;
    } while (paramType == null);
    return new ReferenceExp(paramType);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\CompilationHelpers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */