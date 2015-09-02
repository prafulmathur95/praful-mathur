package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.lists.FString;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;

public class CompileReflect
{
  public static int checkKnownClass(Type paramType, Compilation paramCompilation)
  {
    if (((paramType instanceof ClassType)) && (paramType.isExisting())) {
      try
      {
        paramType.getReflectClass();
        return 1;
      }
      catch (Exception localException)
      {
        paramCompilation.error('e', "unknown class: " + paramType.getName());
        return -1;
      }
    }
    return 0;
  }
  
  public static ApplyExp inlineClassName(ApplyExp paramApplyExp, int paramInt, InlineCalls paramInlineCalls)
  {
    Object localObject1 = paramInlineCalls.getCompilation();
    Object localObject2 = ((Compilation)localObject1).getLanguage();
    paramInlineCalls = paramApplyExp.getArgs();
    if (paramInlineCalls.length > paramInt)
    {
      localObject2 = ((Language)localObject2).getTypeFor(paramInlineCalls[paramInt]);
      if ((localObject2 instanceof Type)) {}
    }
    else
    {
      return paramApplyExp;
    }
    checkKnownClass((Type)localObject2, (Compilation)localObject1);
    localObject1 = new Expression[paramInlineCalls.length];
    System.arraycopy(paramInlineCalls, 0, localObject1, 0, paramInlineCalls.length);
    localObject1[paramInt] = new QuoteExp(localObject2);
    paramInlineCalls = new ApplyExp(paramApplyExp.getFunction(), (Expression[])localObject1);
    paramInlineCalls.setLine(paramApplyExp);
    return paramInlineCalls;
  }
  
  public static Expression validateApplyInstanceOf(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramType = inlineClassName(paramApplyExp, 1, paramInlineCalls);
    paramApplyExp = paramType.getArgs();
    if (paramApplyExp.length == 2)
    {
      paramProcedure = paramApplyExp[0];
      paramApplyExp = paramApplyExp[1];
      if ((paramApplyExp instanceof QuoteExp))
      {
        paramApplyExp = ((QuoteExp)paramApplyExp).getValue();
        if ((paramApplyExp instanceof Type))
        {
          paramInlineCalls = (Type)paramApplyExp;
          paramApplyExp = paramInlineCalls;
          if ((paramInlineCalls instanceof PrimType)) {
            paramApplyExp = ((PrimType)paramInlineCalls).boxedType();
          }
          if ((paramProcedure instanceof QuoteExp))
          {
            if (paramApplyExp.isInstance(((QuoteExp)paramProcedure).getValue())) {
              return QuoteExp.trueExp;
            }
            return QuoteExp.falseExp;
          }
          if (!paramProcedure.side_effects())
          {
            int i = paramApplyExp.compare(paramProcedure.getType());
            if ((i == 1) || (i == 0)) {
              return QuoteExp.trueExp;
            }
            if (i == -3) {
              return QuoteExp.falseExp;
            }
          }
        }
      }
    }
    return paramType;
  }
  
  public static Expression validateApplySlotGet(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    Object localObject4 = paramInlineCalls.getCompilation();
    Language localLanguage = ((Compilation)localObject4).getLanguage();
    boolean bool1 = ((SlotGet)paramProcedure).isStatic;
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    Object localObject3 = arrayOfExpression[0];
    Object localObject5 = arrayOfExpression[1];
    paramType = ((Expression)localObject5).valueIfConstant();
    String str;
    int i;
    if ((!(paramType instanceof String)) && (!(paramType instanceof FString)))
    {
      localObject2 = paramApplyExp;
      if (!(paramType instanceof Symbol)) {}
    }
    else
    {
      str = paramType.toString();
      if (!bool1) {
        break label357;
      }
      localObject2 = localLanguage.getTypeFor((Expression)localObject3);
      i = checkKnownClass((Type)localObject2, (Compilation)localObject4);
      if (i >= 0) {
        break label115;
      }
      localObject2 = paramApplyExp;
    }
    label115:
    do
    {
      return (Expression)localObject2;
      if ("class".equals(str))
      {
        if (i > 0) {
          return QuoteExp.getInstance(((Type)localObject2).getReflectClass());
        }
        return new ApplyExp(Compilation.typeType.getDeclaredMethod("getReflectClass", 0), new Expression[] { localObject3 });
      }
      localObject1 = localObject2;
      paramType = paramApplyExp;
      if (localObject2 != null)
      {
        paramType = new QuoteExp(localObject2);
        paramType = new ApplyExp(paramApplyExp.getFunction(), new Expression[] { paramType, localObject5 });
        paramType.setLine(paramApplyExp);
        localObject1 = localObject2;
      }
      localObject2 = paramType;
    } while ((localObject1 instanceof ArrayType));
    if ((localObject1 instanceof ObjectType))
    {
      localObject2 = (ObjectType)localObject1;
      label261:
      Object localObject6;
      if (((Compilation)localObject4).curClass != null)
      {
        paramApplyExp = ((Compilation)localObject4).curClass;
        localObject5 = SlotGet.lookupMember((ObjectType)localObject2, str, paramApplyExp);
        if (!(localObject5 instanceof Field)) {
          break label450;
        }
        localObject6 = (Field)localObject5;
        if ((((Field)localObject6).getModifiers() & 0x8) == 0) {
          break label378;
        }
      }
      label357:
      label378:
      for (i = 1;; i = 0)
      {
        if ((!bool1) || (i != 0)) {
          break label384;
        }
        return new ErrorExp("cannot access non-static field `" + str + "' using `" + paramProcedure.getName() + '\'', (Compilation)localObject4);
        localObject1 = ((Expression)localObject3).getType();
        paramType = paramApplyExp;
        break;
        paramApplyExp = ((Compilation)localObject4).mainClass;
        break label261;
      }
      label384:
      if ((paramApplyExp != null) && (!paramApplyExp.isAccessible((Member)localObject6, (ObjectType)localObject2)))
      {
        return new ErrorExp("field " + ((Field)localObject6).getDeclaringClass().getName() + '.' + str + " is not accessible here", (Compilation)localObject4);
        label450:
        if ((localObject5 instanceof Method))
        {
          localObject6 = (Method)localObject5;
          ClassType localClassType = ((Method)localObject6).getDeclaringClass();
          i = ((Method)localObject6).getModifiers();
          boolean bool2 = ((Method)localObject6).getStaticFlag();
          if ((bool1) && (!bool2)) {
            return new ErrorExp("cannot call non-static getter method `" + str + "' using `" + paramProcedure.getName() + '\'', (Compilation)localObject4);
          }
          if ((paramApplyExp != null) && (!paramApplyExp.isAccessible(localClassType, (ObjectType)localObject2, i))) {
            return new ErrorExp("method " + localObject6 + " is not accessible here", (Compilation)localObject4);
          }
        }
      }
      if (localObject5 != null)
      {
        paramApplyExp = new QuoteExp(localObject5);
        paramApplyExp = new ApplyExp(paramType.getFunction(), new Expression[] { localObject3, paramApplyExp });
        paramApplyExp.setLine(paramType);
        return paramApplyExp;
      }
      if ((localObject1 != Type.pointer_type) && (((Compilation)localObject4).warnUnknownMember())) {
        ((Compilation)localObject4).error('e', "no slot `" + str + "' in " + ((ObjectType)localObject2).getName());
      }
    }
    paramProcedure = Compilation.mangleNameIfNeeded(str).intern();
    Object localObject1 = ClassExp.slotToMethodName("get", str);
    Object localObject2 = ClassExp.slotToMethodName("is", str);
    localObject3 = Invoke.invokeStatic;
    localObject4 = QuoteExp.getInstance("gnu.kawa.reflect.SlotGet");
    localObject5 = QuoteExp.getInstance("getSlotValue");
    if (bool1) {}
    for (paramApplyExp = QuoteExp.trueExp;; paramApplyExp = QuoteExp.falseExp)
    {
      paramApplyExp = new ApplyExp((Procedure)localObject3, new Expression[] { localObject4, localObject5, paramApplyExp, arrayOfExpression[0], QuoteExp.getInstance(str), QuoteExp.getInstance(paramProcedure), QuoteExp.getInstance(localObject1), QuoteExp.getInstance(localObject2), QuoteExp.getInstance(localLanguage) });
      paramApplyExp.setLine(paramType);
      return paramInlineCalls.visitApplyOnly(paramApplyExp, null);
    }
  }
  
  public static Expression validateApplySlotSet(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramProcedure = (SlotSet)paramProcedure;
    paramType = paramApplyExp;
    if (paramProcedure.isStatic)
    {
      paramType = paramApplyExp;
      if (paramInlineCalls.getCompilation().mustCompile) {
        paramType = inlineClassName(paramApplyExp, 0, paramInlineCalls);
      }
    }
    if ((paramProcedure.returnSelf) && (paramType.getArgCount() == 3)) {}
    for (paramApplyExp = paramType.getArg(0).getType();; paramApplyExp = Type.voidType)
    {
      paramType.setType(paramApplyExp);
      return paramType;
    }
  }
  
  public static Expression validateApplyTypeSwitch(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramType = paramApplyExp.getArgs();
    int i = 1;
    while (i < paramType.length)
    {
      if ((paramType[i] instanceof LambdaExp))
      {
        paramProcedure = (LambdaExp)paramType[i];
        paramProcedure.setInlineOnly(true);
        paramProcedure.returnContinuation = paramApplyExp;
        paramProcedure.inlineHome = paramInlineCalls.getCurrentLambda();
      }
      i += 1;
    }
    return paramApplyExp;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\CompileReflect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */