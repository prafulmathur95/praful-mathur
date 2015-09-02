package gnu.kawa.functions;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Language;
import gnu.expr.NameLookup;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.HasNamedParts;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import kawa.lang.Translator;

public class CompileNamedPart
{
  static final ClassType typeHasNamedParts = ClassType.make("gnu.mapping.HasNamedParts");
  
  public static String combineName(Expression paramExpression1, Expression paramExpression2)
  {
    Object localObject = paramExpression2.valueIfConstant();
    if ((localObject instanceof SimpleSymbol))
    {
      if ((paramExpression1 instanceof ReferenceExp))
      {
        String str = ((ReferenceExp)paramExpression1).getSimpleName();
        paramExpression2 = str;
        if (str != null) {}
      }
      else
      {
        if (!(paramExpression1 instanceof GetNamedExp)) {
          break label79;
        }
        paramExpression2 = ((GetNamedExp)paramExpression1).combinedName;
        if (paramExpression2 == null) {
          break label79;
        }
      }
      return (paramExpression2 + ':' + localObject).intern();
    }
    label79:
    return null;
  }
  
  public static Expression makeExp(Type paramType, String paramString)
  {
    return makeExp(new QuoteExp(paramType), new QuoteExp(paramString));
  }
  
  public static Expression makeExp(Expression paramExpression1, Expression paramExpression2)
  {
    String str = combineName(paramExpression1, paramExpression2);
    Environment localEnvironment = Environment.getCurrent();
    Object localObject1;
    if (str != null)
    {
      localObject2 = (Translator)Compilation.getCurrent();
      localObject1 = Namespace.EmptyNamespace.getSymbol(str);
      localObject2 = ((Translator)localObject2).lexical.lookup(localObject1, false);
      if (!Declaration.isUnknown((Declaration)localObject2)) {
        return new ReferenceExp((Declaration)localObject2);
      }
      if ((localObject1 != null) && (localEnvironment.isBound((Symbol)localObject1, null))) {
        return new ReferenceExp(str);
      }
    }
    Object localObject2 = paramExpression1;
    ReferenceExp localReferenceExp;
    if ((paramExpression1 instanceof ReferenceExp))
    {
      localReferenceExp = (ReferenceExp)paramExpression1;
      localObject2 = paramExpression1;
      if (localReferenceExp.isUnknown())
      {
        localObject1 = localReferenceExp.getSymbol();
        if (!(localObject1 instanceof Symbol)) {
          break label183;
        }
        localObject1 = (Symbol)localObject1;
      }
    }
    for (;;)
    {
      localObject2 = paramExpression1;
      if (localEnvironment.get((EnvironmentKey)localObject1, null) == null) {
        localObject1 = localReferenceExp.getName();
      }
      try
      {
        localObject2 = QuoteExp.getInstance(Type.make(ClassType.getContextClass((String)localObject1)));
        paramExpression1 = new GetNamedExp(new Expression[] { localObject2, paramExpression2 });
        paramExpression1.combinedName = str;
        return paramExpression1;
        label183:
        localObject1 = localEnvironment.getSymbol(localObject1.toString());
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          localObject2 = paramExpression1;
        }
      }
    }
  }
  
  public static Expression makeExp(Expression paramExpression, String paramString)
  {
    return makeExp(paramExpression, new QuoteExp(paramString));
  }
  
  public static Expression makeGetNamedInstancePartExp(Expression paramExpression)
  {
    if ((paramExpression instanceof QuoteExp))
    {
      localObject = ((QuoteExp)paramExpression).getValue();
      if ((localObject instanceof SimpleSymbol)) {
        return QuoteExp.getInstance(new GetNamedInstancePart(localObject.toString()));
      }
    }
    Object localObject = new QuoteExp(ClassType.make("gnu.kawa.functions.GetNamedInstancePart"));
    return new ApplyExp(Invoke.make, new Expression[] { localObject, paramExpression });
  }
  
  public static Expression validateGetNamedInstancePart(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramApplyExp = paramApplyExp.getArgs();
    GetNamedInstancePart localGetNamedInstancePart = (GetNamedInstancePart)paramProcedure;
    if (localGetNamedInstancePart.isField)
    {
      paramProcedure = new Expression[2];
      paramProcedure[0] = paramApplyExp[0];
      paramProcedure[1] = new QuoteExp(localGetNamedInstancePart.pname);
    }
    for (paramApplyExp = SlotGet.field;; paramApplyExp = Invoke.invoke)
    {
      return paramInlineCalls.visitApplyOnly(new ApplyExp(paramApplyExp, paramProcedure), paramType);
      int i = paramApplyExp.length;
      paramProcedure = new Expression[i + 1];
      paramProcedure[0] = paramApplyExp[0];
      paramProcedure[1] = new QuoteExp(localGetNamedInstancePart.pname);
      System.arraycopy(paramApplyExp, 1, paramProcedure, 2, i - 1);
    }
  }
  
  public static Expression validateGetNamedPart(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if ((arrayOfExpression.length != 2) || (!(arrayOfExpression[1] instanceof QuoteExp)) || (!(paramApplyExp instanceof GetNamedExp))) {}
    String str;
    Type localType;
    Compilation localCompilation;
    label177:
    label203:
    label268:
    do
    {
      Object localObject1;
      Object localObject2;
      Object localObject3;
      GetNamedExp localGetNamedExp;
      do
      {
        do
        {
          return paramApplyExp;
          paramProcedure = arrayOfExpression[0];
          localObject1 = null;
          if ((paramProcedure instanceof ReferenceExp))
          {
            localObject1 = (ReferenceExp)paramProcedure;
            if ("*".equals(((ReferenceExp)localObject1).getName())) {
              return makeGetNamedInstancePartExp(arrayOfExpression[1]);
            }
            localObject1 = ((ReferenceExp)localObject1).getBinding();
          }
          str = ((QuoteExp)arrayOfExpression[1]).getValue().toString();
          localType = paramProcedure.getType();
          if (paramProcedure == QuoteExp.nullExp)
          {
            localCompilation = paramInlineCalls.getCompilation();
            localObject2 = localCompilation.getLanguage();
            localObject3 = ((Language)localObject2).getTypeFor(paramProcedure, false);
            if (localCompilation != null) {
              break label177;
            }
            paramProcedure = null;
          }
          for (;;)
          {
            localGetNamedExp = (GetNamedExp)paramApplyExp;
            if (localObject3 == null) {
              break label268;
            }
            if (!str.equals("<>")) {
              break label203;
            }
            return new QuoteExp(localObject3);
            break;
            if (localCompilation.curClass != null) {
              paramProcedure = localCompilation.curClass;
            } else {
              paramProcedure = localCompilation.mainClass;
            }
          }
          if ((localObject3 instanceof ObjectType))
          {
            if (str.equals("new")) {
              return localGetNamedExp.setProcedureKind('N');
            }
            if (str.equals("instance?")) {
              return localGetNamedExp.setProcedureKind('I');
            }
            if (str.equals("@")) {
              return localGetNamedExp.setProcedureKind('C');
            }
          }
          if (!(localObject3 instanceof ObjectType)) {
            break;
          }
          if ((str.length() > 1) && (str.charAt(0) == '.')) {
            return new QuoteExp(new NamedPart(localObject3, str, 'D'));
          }
        } while (CompileReflect.checkKnownClass((Type)localObject3, localCompilation) < 0);
        paramProcedure = ClassMethods.getMethods((ObjectType)localObject3, Compilation.mangleName(str), '\000', paramProcedure, (Language)localObject2);
        if ((paramProcedure != null) && (paramProcedure.length > 0))
        {
          localGetNamedExp.methods = paramProcedure;
          return localGetNamedExp.setProcedureKind('S');
        }
        paramProcedure = new ApplyExp(SlotGet.staticField, arrayOfExpression);
        paramProcedure.setLine(paramApplyExp);
        return paramInlineCalls.visitApplyOnly(paramProcedure, paramType);
      } while ((localObject3 != null) && ((localType.isSubtype(Compilation.typeClassType)) || (localType.isSubtype(Type.javalangClassType))));
      if ((localType instanceof ObjectType))
      {
        localObject3 = (ObjectType)localType;
        localObject2 = ClassMethods.getMethods((ObjectType)localObject3, Compilation.mangleName(str), 'V', paramProcedure, (Language)localObject2);
        if ((localObject2 != null) && (localObject2.length > 0))
        {
          localGetNamedExp.methods = ((PrimProcedure[])localObject2);
          return localGetNamedExp.setProcedureKind('M');
        }
        if (localType.isSubtype(typeHasNamedParts))
        {
          if (localObject1 != null)
          {
            paramInlineCalls = Declaration.followAliases((Declaration)localObject1).getConstantValue();
            if (paramInlineCalls != null)
            {
              paramInlineCalls = (HasNamedParts)paramInlineCalls;
              if (paramInlineCalls.isConstant(str)) {
                return QuoteExp.getInstance(paramInlineCalls.get(str));
              }
            }
          }
          paramInlineCalls = arrayOfExpression[0];
          paramType = QuoteExp.getInstance(str);
          return new ApplyExp(typeHasNamedParts.getDeclaredMethod("get", 1), new Expression[] { paramInlineCalls, paramType }).setLine(paramApplyExp);
        }
        if ((SlotGet.lookupMember((ObjectType)localObject3, str, paramProcedure) != null) || ((str.equals("length")) && ((localType instanceof ArrayType))))
        {
          paramProcedure = new ApplyExp(SlotGet.field, arrayOfExpression);
          paramProcedure.setLine(paramApplyExp);
          return paramInlineCalls.visitApplyOnly(paramProcedure, paramType);
        }
      }
    } while (!localCompilation.warnUnknownMember());
    localCompilation.error('w', "no known slot '" + str + "' in " + localType.getName());
    return paramApplyExp;
  }
  
  public static Expression validateNamedPart(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression2 = paramApplyExp.getArgs();
    paramProcedure = (NamedPart)paramProcedure;
    switch (paramProcedure.kind)
    {
    default: 
      return paramApplyExp;
    }
    String str = paramProcedure.member.toString().substring(1);
    Expression[] arrayOfExpression1 = new Expression[2];
    arrayOfExpression1[1] = QuoteExp.getInstance(str);
    if (arrayOfExpression2.length > 0) {
      arrayOfExpression1[0] = Compilation.makeCoercion(arrayOfExpression2[0], new QuoteExp(paramProcedure.container));
    }
    for (paramProcedure = SlotGet.field;; paramProcedure = SlotGet.staticField)
    {
      paramProcedure = new ApplyExp(paramProcedure, arrayOfExpression1);
      paramProcedure.setLine(paramApplyExp);
      return paramInlineCalls.visitApplyOnly(paramProcedure, paramType);
      arrayOfExpression1[0] = QuoteExp.getInstance(paramProcedure.container);
    }
  }
  
  public static Expression validateNamedPartSetter(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    NamedPart localNamedPart = (NamedPart)((NamedPartSetter)paramProcedure).getGetter();
    paramProcedure = paramApplyExp;
    Expression[] arrayOfExpression;
    if (localNamedPart.kind == 'D')
    {
      arrayOfExpression = new Expression[3];
      arrayOfExpression[1] = QuoteExp.getInstance(localNamedPart.member.toString().substring(1));
      arrayOfExpression[2] = paramApplyExp.getArgs()[0];
      if (paramApplyExp.getArgCount() != 1) {
        break label114;
      }
      arrayOfExpression[0] = QuoteExp.getInstance(localNamedPart.container);
    }
    for (paramProcedure = SlotSet.set$Mnstatic$Mnfield$Ex;; paramProcedure = SlotSet.set$Mnfield$Ex)
    {
      paramProcedure = new ApplyExp(paramProcedure, arrayOfExpression);
      paramProcedure.setLine(paramApplyExp);
      paramProcedure = paramInlineCalls.visitApplyOnly(paramProcedure, paramType);
      label114:
      do
      {
        return paramProcedure;
        paramProcedure = paramApplyExp;
      } while (paramApplyExp.getArgCount() != 2);
      arrayOfExpression[0] = Compilation.makeCoercion(paramApplyExp.getArgs()[0], new QuoteExp(localNamedPart.container));
    }
  }
  
  public static Expression validateSetNamedInstancePart(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramApplyExp = paramApplyExp.getArgs();
    Object localObject = ((SetNamedInstancePart)paramProcedure).pname;
    paramProcedure = paramApplyExp[0];
    localObject = new QuoteExp(localObject);
    paramApplyExp = paramApplyExp[1];
    return paramInlineCalls.visitApplyOnly(new ApplyExp(SlotSet.set$Mnfield$Ex, new Expression[] { paramProcedure, localObject, paramApplyExp }), paramType);
  }
  
  public static Expression validateSetNamedPart(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramProcedure = paramApplyExp.getArgs();
    if ((paramProcedure.length != 3) || (!(paramProcedure[1] instanceof QuoteExp))) {
      return paramApplyExp;
    }
    paramType = paramProcedure[0];
    String str = ((QuoteExp)paramProcedure[1]).getValue().toString();
    Type localType = paramType.getType();
    Compilation localCompilation = paramInlineCalls.getCompilation();
    paramInlineCalls = localCompilation.getLanguage().getTypeFor(paramType);
    if (localCompilation == null)
    {
      paramType = null;
      if (!(paramInlineCalls instanceof ClassType)) {
        break label139;
      }
      paramInlineCalls = new ApplyExp(SlotSet.set$Mnstatic$Mnfield$Ex, paramProcedure);
    }
    for (;;)
    {
      if (paramInlineCalls != paramApplyExp) {
        paramInlineCalls.setLine(paramApplyExp);
      }
      paramInlineCalls.setType(Type.voidType);
      return paramInlineCalls;
      if (localCompilation.curClass != null)
      {
        paramType = localCompilation.curClass;
        break;
      }
      paramType = localCompilation.mainClass;
      break;
      label139:
      paramInlineCalls = paramApplyExp;
      if ((localType instanceof ClassType))
      {
        paramInlineCalls = paramApplyExp;
        if (SlotSet.lookupMember((ClassType)localType, str, paramType) != null) {
          paramInlineCalls = new ApplyExp(SlotSet.set$Mnfield$Ex, paramProcedure);
        }
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\CompileNamedPart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */