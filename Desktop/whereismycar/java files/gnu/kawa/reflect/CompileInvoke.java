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
import gnu.expr.BeginExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Keyword;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.PairClassType;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.TypeValue;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;

public class CompileInvoke
{
  private static void append(PrimProcedure[] paramArrayOfPrimProcedure, int paramInt, StringBuffer paramStringBuffer)
  {
    int i = 0;
    while (i < paramInt)
    {
      paramStringBuffer.append("\n  candidate: ");
      paramStringBuffer.append(paramArrayOfPrimProcedure[i]);
      i += 1;
    }
  }
  
  static Object[] checkKeywords(ObjectType paramObjectType, Expression[] paramArrayOfExpression, int paramInt, ClassType paramClassType)
  {
    int j = paramArrayOfExpression.length;
    int i = 0;
    while ((i * 2 + paramInt + 1 < j) && ((paramArrayOfExpression[(i * 2 + paramInt)].valueIfConstant() instanceof Keyword))) {
      i += 1;
    }
    Object[] arrayOfObject = new Object[i];
    j = 0;
    if (j < i)
    {
      String str = ((Keyword)paramArrayOfExpression[(j * 2 + paramInt)].valueIfConstant()).getName();
      Member localMember = SlotSet.lookupMember(paramObjectType, str, paramClassType);
      Object localObject = localMember;
      if (localMember == null) {
        localObject = paramObjectType.getMethod(ClassExp.slotToMethodName("add", str), SlotSet.type1Array);
      }
      if (localObject != null) {}
      for (;;)
      {
        arrayOfObject[j] = localObject;
        j += 1;
        break;
        localObject = str;
      }
    }
    return arrayOfObject;
  }
  
  private static String getMethodName(Expression[] paramArrayOfExpression, char paramChar)
  {
    if (paramChar == 'N') {
      return "<init>";
    }
    if (paramChar == 'P') {}
    for (paramChar = '\002'; paramArrayOfExpression.length >= paramChar + '\001'; paramChar = '\001') {
      return ClassMethods.checkName(paramArrayOfExpression[paramChar], false);
    }
    return null;
  }
  
  protected static PrimProcedure[] getMethods(ObjectType paramObjectType, String paramString, ClassType paramClassType, Invoke paramInvoke)
  {
    char c = 'P';
    int i = paramInvoke.kind;
    if (i == 80) {}
    for (;;)
    {
      return ClassMethods.getMethods(paramObjectType, paramString, c, paramClassType, paramInvoke.language);
      if ((i == 42) || (i == 86)) {
        c = 'V';
      } else {
        c = '\000';
      }
    }
  }
  
  public static PrimProcedure getStaticMethod(ClassType paramClassType, String paramString, Expression[] paramArrayOfExpression)
  {
    for (;;)
    {
      try
      {
        paramString = getMethods(paramClassType, paramString, null, Invoke.invokeStatic);
        long l = selectApplicable(paramString, paramClassType, paramArrayOfExpression, paramArrayOfExpression.length, 0, -1);
        i = (int)(l >> 32);
        int j = (int)l;
        if (paramString == null)
        {
          i = -1;
          if (i < 0)
          {
            paramClassType = null;
            return paramClassType;
          }
        }
        else
        {
          if (i <= 0) {
            break label80;
          }
          i = MethodProc.mostSpecific(paramString, i);
          continue;
        }
        paramClassType = paramString[i];
        continue;
        if (j != 1) {
          break label91;
        }
      }
      finally {}
      label80:
      int i = 0;
      continue;
      label91:
      i = -1;
    }
  }
  
  static int hasKeywordArgument(int paramInt, Expression[] paramArrayOfExpression)
  {
    while (paramInt < paramArrayOfExpression.length)
    {
      if ((paramArrayOfExpression[paramInt].valueIfConstant() instanceof Keyword)) {
        return paramInt;
      }
      paramInt += 1;
    }
    return paramArrayOfExpression.length;
  }
  
  private static long selectApplicable(PrimProcedure[] paramArrayOfPrimProcedure, ObjectType paramObjectType, Expression[] paramArrayOfExpression, int paramInt1, int paramInt2, int paramInt3)
  {
    Type[] arrayOfType = new Type[paramInt1];
    paramInt1 = 0;
    if (paramInt3 >= 0)
    {
      arrayOfType[0] = paramObjectType;
      paramInt1 = 0 + 1;
    }
    if ((paramInt2 < paramArrayOfExpression.length) && (paramInt1 < arrayOfType.length))
    {
      Expression localExpression = paramArrayOfExpression[paramInt2];
      paramObjectType = null;
      if (InlineCalls.checkIntValue(localExpression) != null) {
        paramObjectType = Type.intType;
      }
      for (;;)
      {
        arrayOfType[paramInt1] = paramObjectType;
        paramInt2 += 1;
        paramInt1 += 1;
        break;
        if (InlineCalls.checkLongValue(localExpression) != null) {
          paramObjectType = Type.longType;
        } else if (0 == 0) {
          paramObjectType = localExpression.getType();
        }
      }
    }
    return ClassMethods.selectApplicable(paramArrayOfPrimProcedure, arrayOfType);
  }
  
  public static Expression validateApplyInvoke(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    Invoke localInvoke = (Invoke)paramProcedure;
    char c = localInvoke.kind;
    Compilation localCompilation = paramInlineCalls.getCompilation();
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int i5 = arrayOfExpression.length;
    if ((!localCompilation.mustCompile) || (i5 == 0) || (((c == 'V') || (c == '*')) && (i5 == 1)))
    {
      paramApplyExp.visitArgs(paramInlineCalls);
      return paramApplyExp;
    }
    Expression localExpression = paramInlineCalls.visit(arrayOfExpression[0], null);
    arrayOfExpression[0] = localExpression;
    Object localObject2;
    label130:
    Object localObject4;
    int j;
    int i;
    int k;
    if ((c == 'V') || (c == '*'))
    {
      paramProcedure = localExpression.getType();
      if ((!(paramProcedure instanceof PairClassType)) || (c != 'N')) {
        break label388;
      }
      localObject2 = ((PairClassType)paramProcedure).instanceType;
      localObject4 = getMethodName(arrayOfExpression, c);
      if ((c != 'V') && (c != '*')) {
        break label410;
      }
      j = i5 - 1;
      i = 2;
      k = 0;
    }
    Object localObject1;
    Object localObject3;
    label388:
    label410:
    int m;
    int n;
    Object localObject5;
    for (;;)
    {
      if ((c == 'N') && ((localObject2 instanceof ArrayType)))
      {
        localObject2 = (ArrayType)localObject2;
        localObject1 = ((ArrayType)localObject2).getComponentType();
        paramProcedure = null;
        i = 0;
        k = i;
        paramType = paramProcedure;
        if (arrayOfExpression.length >= 3)
        {
          k = i;
          paramType = paramProcedure;
          if ((arrayOfExpression[1] instanceof QuoteExp))
          {
            localObject3 = ((QuoteExp)arrayOfExpression[1]).getValue();
            k = i;
            paramType = paramProcedure;
            if ((localObject3 instanceof Keyword))
            {
              localObject3 = ((Keyword)localObject3).getName();
              if (!"length".equals(localObject3))
              {
                k = i;
                paramType = paramProcedure;
                if (!"size".equals(localObject3)) {}
              }
              else
              {
                paramType = arrayOfExpression[2];
                k = 1;
              }
            }
          }
        }
        paramProcedure = paramType;
        if (paramType == null) {
          paramProcedure = QuoteExp.getInstance(new Integer(arrayOfExpression.length - 1));
        }
        paramType = paramInlineCalls.visit(paramProcedure, Type.intType);
        paramType = new ApplyExp(new ArrayNew((Type)localObject1), new Expression[] { paramType });
        paramType.setType((Type)localObject2);
        if ((k != 0) && (arrayOfExpression.length == 3))
        {
          return paramType;
          paramProcedure = localInvoke.language.getTypeFor(localExpression);
          break;
          if ((paramProcedure instanceof ObjectType))
          {
            localObject2 = (ObjectType)paramProcedure;
            break label130;
          }
          localObject2 = null;
          break label130;
          if (c == 'N')
          {
            j = i5;
            i = 0;
            k = -1;
            continue;
          }
          if ((c == 'S') || (c == 's'))
          {
            j = i5 - 2;
            i = 2;
            k = -1;
            continue;
          }
          if (c == 'P')
          {
            j = i5 - 2;
            i = 3;
            k = 1;
            continue;
          }
          paramApplyExp.visitArgs(paramInlineCalls);
          return paramApplyExp;
        }
        localObject3 = new LetExp(new Expression[] { paramType });
        localObject2 = ((LetExp)localObject3).addDeclaration((String)null, (Type)localObject2);
        ((Declaration)localObject2).noteValue(paramType);
        localObject4 = new BeginExp();
        j = 0;
        if (k != 0) {}
        for (i = 3; i < arrayOfExpression.length; i = 1)
        {
          paramProcedure = arrayOfExpression[i];
          paramType = paramProcedure;
          m = i;
          n = j;
          if (k != 0)
          {
            paramType = paramProcedure;
            m = i;
            n = j;
            if (i + 1 < arrayOfExpression.length)
            {
              paramType = paramProcedure;
              m = i;
              n = j;
              if ((paramProcedure instanceof QuoteExp))
              {
                localObject5 = ((QuoteExp)paramProcedure).getValue();
                paramType = paramProcedure;
                m = i;
                n = j;
                if ((localObject5 instanceof Keyword)) {
                  paramType = ((Keyword)localObject5).getName();
                }
              }
            }
          }
          try
          {
            n = Integer.parseInt(paramType);
            m = i + 1;
            paramType = arrayOfExpression[m];
            paramType = paramInlineCalls.visit(paramType, (Type)localObject1);
            ((BeginExp)localObject4).add(new ApplyExp(new ArraySet((Type)localObject1), new Expression[] { new ReferenceExp((Declaration)localObject2), QuoteExp.getInstance(new Integer(n)), paramType }));
            j = n + 1;
            i = m + 1;
          }
          catch (Throwable paramInlineCalls)
          {
            localCompilation.error('e', "non-integer keyword '" + paramType + "' in array constructor");
            return paramApplyExp;
          }
        }
        ((BeginExp)localObject4).add(new ReferenceExp((Declaration)localObject2));
        ((LetExp)localObject3).body = ((Expression)localObject4);
        return (Expression)localObject3;
      }
    }
    if ((localObject2 != null) && (localObject4 != null))
    {
      if (((localObject2 instanceof TypeValue)) && (c == 'N'))
      {
        paramProcedure = ((TypeValue)localObject2).getConstructor();
        if (paramProcedure != null)
        {
          paramApplyExp = new Expression[i5 - 1];
          System.arraycopy(arrayOfExpression, 1, paramApplyExp, 0, i5 - 1);
          return paramInlineCalls.visit(new ApplyExp(paramProcedure, paramApplyExp), paramType);
        }
      }
      if (localCompilation == null) {
        localObject3 = null;
      }
      int i7;
      int i6;
      Object[] arrayOfObject;
      for (;;)
      {
        try
        {
          localObject5 = getMethods((ObjectType)localObject2, (String)localObject4, (ClassType)localObject3, localInvoke);
          i7 = ClassMethods.selectApplicable((PrimProcedure[])localObject5, j);
          i6 = -1;
          if (c != 'N') {
            break label1646;
          }
          m = hasKeywordArgument(1, arrayOfExpression);
          if (m >= arrayOfExpression.length)
          {
            if (i7 > 0) {
              break label1646;
            }
            if (ClassMethods.selectApplicable((PrimProcedure[])localObject5, new Type[] { Compilation.typeClassType }) >> 32 != 1L) {
              break label1646;
            }
          }
          arrayOfObject = checkKeywords((ObjectType)localObject2, arrayOfExpression, m, (ClassType)localObject3);
          if ((arrayOfObject.length * 2 != arrayOfExpression.length - m) && (ClassMethods.selectApplicable(ClassMethods.getMethods((ObjectType)localObject2, "add", 'V', null, localInvoke.language), 2) <= 0)) {
            break label1646;
          }
          paramProcedure = null;
          i = 0;
          if (i >= arrayOfObject.length) {
            break;
          }
          localObject1 = paramProcedure;
          if ((arrayOfObject[i] instanceof String))
          {
            if (paramProcedure == null)
            {
              paramProcedure = new StringBuffer();
              paramProcedure.append("no field or setter ");
              paramProcedure.append('`');
              paramProcedure.append(arrayOfObject[i]);
              paramProcedure.append('\'');
              localObject1 = paramProcedure;
            }
          }
          else
          {
            i += 1;
            paramProcedure = (Procedure)localObject1;
            continue;
            if (localCompilation.curClass != null)
            {
              localObject3 = localCompilation.curClass;
              continue;
            }
            localObject3 = localCompilation.mainClass;
            continue;
          }
          paramProcedure.append(", ");
        }
        catch (Exception paramInlineCalls)
        {
          localCompilation.error('w', "unknown class: " + ((ObjectType)localObject2).getName());
          return paramApplyExp;
        }
      }
      if (paramProcedure != null)
      {
        paramProcedure.append(" in class ");
        paramProcedure.append(((ObjectType)localObject2).getName());
        localCompilation.error('w', paramProcedure.toString());
        return paramApplyExp;
      }
      if (m < arrayOfExpression.length)
      {
        paramProcedure = new Expression[m];
        System.arraycopy(arrayOfExpression, 0, paramProcedure, 0, m);
        paramProcedure = (ApplyExp)paramInlineCalls.visit(new ApplyExp(paramApplyExp.getFunction(), paramProcedure), (Type)localObject2);
        paramProcedure.setType((Type)localObject2);
        localObject1 = paramProcedure;
        if (arrayOfExpression.length <= 0) {
          break label1634;
        }
        i = 0;
        localObject1 = paramProcedure;
        label1276:
        if (i >= arrayOfObject.length) {
          break label1453;
        }
        localObject4 = arrayOfObject[i];
        if (!(localObject4 instanceof Method)) {
          break label1428;
        }
        paramProcedure = ((Method)localObject4).getParameterTypes()[0];
      }
      for (;;)
      {
        localObject3 = paramProcedure;
        if (paramProcedure != null) {
          localObject3 = localInvoke.language.getLangTypeFor(paramProcedure);
        }
        paramProcedure = paramInlineCalls.visit(arrayOfExpression[(i * 2 + m + 1)], (Type)localObject3);
        localObject3 = new QuoteExp(localObject4);
        localObject1 = new ApplyExp(SlotSet.setFieldReturnObject, new Expression[] { localObject1, localObject3, paramProcedure });
        ((ApplyExp)localObject1).setType((Type)localObject2);
        i += 1;
        break label1276;
        paramProcedure = new ApplyExp(localObject5[0], new Expression[] { localExpression });
        break;
        label1428:
        if ((localObject4 instanceof Field)) {
          paramProcedure = ((Field)localObject4).getType();
        } else {
          paramProcedure = null;
        }
      }
      label1453:
      if (m == arrayOfExpression.length) {}
      for (i = 1;; i = arrayOfObject.length * 2 + m)
      {
        paramProcedure = (Procedure)localObject1;
        localObject1 = paramProcedure;
        if (i >= arrayOfExpression.length) {
          break label1634;
        }
        localObject1 = new LetExp(new Expression[] { paramProcedure });
        localObject2 = ((LetExp)localObject1).addDeclaration((String)null, (Type)localObject2);
        ((Declaration)localObject2).noteValue(paramProcedure);
        paramProcedure = new BeginExp();
        while (i < arrayOfExpression.length)
        {
          localObject3 = new ReferenceExp((Declaration)localObject2);
          localObject4 = QuoteExp.getInstance("add");
          localObject5 = arrayOfExpression[i];
          paramProcedure.add(paramInlineCalls.visit(new ApplyExp(Invoke.invoke, new Expression[] { localObject3, localObject4, localObject5 }), null));
          i += 1;
        }
      }
      paramProcedure.add(new ReferenceExp((Declaration)localObject2));
      ((LetExp)localObject1).body = paramProcedure;
      label1634:
      return paramInlineCalls.checkType(((Expression)localObject1).setLine(paramApplyExp), paramType);
      label1646:
      label1678:
      label1738:
      label1758:
      int i1;
      label1773:
      int i2;
      label1776:
      int i3;
      label1811:
      label1862:
      label1875:
      int i4;
      if (i7 >= 0)
      {
        m = 1;
        if (m < i5)
        {
          paramProcedure = null;
          localObject1 = null;
          if (m == i5 - 1)
          {
            n = 1;
            if (((c != 'P') || (m != 2)) && ((c == 'N') || (m != 1))) {
              break label1738;
            }
            localObject1 = null;
          }
          do
          {
            do
            {
              for (;;)
              {
                arrayOfExpression[m] = paramInlineCalls.visit(arrayOfExpression[m], (Type)localObject1);
                m += 1;
                break;
                n = 0;
                break label1678;
                if ((c != 'P') || (m != 1)) {
                  break label1758;
                }
                localObject1 = localObject2;
              }
            } while (i7 <= 0);
            if (c != 'N') {
              break label1862;
            }
            i1 = 1;
            i2 = 0;
            localObject1 = paramProcedure;
          } while (i2 >= i7);
          localObject1 = localObject5[i2];
          if ((c != 'S') && (((PrimProcedure)localObject1).takesTarget()))
          {
            i3 = 1;
            i3 = m - i1 + i3;
            if ((n == 0) || (!((PrimProcedure)localObject1).takesVarArgs()) || (i3 != ((PrimProcedure)localObject1).minArgs())) {
              break label1875;
            }
            paramProcedure = null;
          }
          for (;;)
          {
            localObject1 = paramProcedure;
            if (paramProcedure == null) {
              break;
            }
            i2 += 1;
            break label1776;
            i1 = i;
            break label1773;
            i3 = 0;
            break label1811;
            localObject1 = ((PrimProcedure)localObject1).getParameterType(i3);
            if (i2 == 0) {
              paramProcedure = (Procedure)localObject1;
            } else if (localObject1 instanceof PrimType != paramProcedure instanceof PrimType) {
              paramProcedure = null;
            } else {
              paramProcedure = Type.lowestCommonSuperType(paramProcedure, (Type)localObject1);
            }
          }
        }
        long l = selectApplicable((PrimProcedure[])localObject5, (ObjectType)localObject2, arrayOfExpression, j, i, k);
        i1 = (int)(l >> 32);
        n = (int)l;
        int i8 = localObject5.length;
        paramProcedure = (Procedure)localObject5;
        i3 = j;
        m = i;
        i4 = n;
        i2 = i1;
        if (i1 + n == 0)
        {
          paramProcedure = (Procedure)localObject5;
          i3 = j;
          m = i;
          i4 = n;
          i2 = i1;
          if (c == 'N')
          {
            paramProcedure = getMethods((ObjectType)localObject2, "valueOf", (ClassType)localObject3, Invoke.invokeStatic);
            m = 1;
            i3 = i5 - 1;
            l = selectApplicable(paramProcedure, (ObjectType)localObject2, arrayOfExpression, i3, 1, -1);
            i2 = (int)(l >> 32);
            i4 = (int)l;
          }
        }
        if (i2 + i4 != 0) {
          break label2359;
        }
        if (c != 'P')
        {
          j = i6;
          if (!localCompilation.warnInvokeUnknownMethod()) {}
        }
        else
        {
          localObject1 = localObject4;
          if (c == 'N') {
            localObject1 = (String)localObject4 + "/valueOf";
          }
          localObject3 = new StringBuilder();
          if (paramProcedure.length + i8 != 0) {
            break label2300;
          }
          ((StringBuilder)localObject3).append("no accessible method '");
          label2147:
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append("' in ");
          ((StringBuilder)localObject3).append(((ObjectType)localObject2).getName());
          if (c != 'P') {
            break label2352;
          }
          c = 'e';
          label2186:
          localCompilation.error(c, ((StringBuilder)localObject3).toString());
          j = i6;
        }
      }
      for (;;)
      {
        label2202:
        if (j >= 0)
        {
          localObject1 = new Expression[i3];
          paramProcedure = paramProcedure[j];
          paramProcedure.takesVarArgs();
          i = 0;
          if (k >= 0)
          {
            localObject1[0] = arrayOfExpression[k];
            i = 0 + 1;
          }
          j = m;
          for (;;)
          {
            if ((j < arrayOfExpression.length) && (i < localObject1.length))
            {
              localObject1[i] = arrayOfExpression[j];
              j += 1;
              i += 1;
              continue;
              i1 = 0;
              n = 0;
              break;
              label2300:
              if (i7 == -983040)
              {
                ((StringBuilder)localObject3).append("too few arguments for method '");
                break label2147;
              }
              if (i7 == -917504)
              {
                ((StringBuilder)localObject3).append("too many arguments for method '");
                break label2147;
              }
              ((StringBuilder)localObject3).append("no possibly applicable method '");
              break label2147;
              label2352:
              c = 'w';
              break label2186;
              label2359:
              if ((i2 == 1) || ((i2 == 0) && (i4 == 1)))
              {
                j = 0;
                break label2202;
              }
              if (i2 > 0)
              {
                n = MethodProc.mostSpecific(paramProcedure, i2);
                i = n;
                if (n < 0)
                {
                  i = n;
                  if (c == 'S')
                  {
                    j = 0;
                    label2418:
                    i = n;
                    if (j < i2)
                    {
                      i = n;
                      if (!paramProcedure[j].getStaticFlag()) {
                        break label2567;
                      }
                      if (n < 0) {
                        break label2563;
                      }
                      i = -1;
                    }
                  }
                }
                j = i;
                if (i >= 0) {
                  break label2202;
                }
                if (c != 'P')
                {
                  j = i;
                  if (!localCompilation.warnInvokeUnknownMethod()) {
                    break label2202;
                  }
                }
                localObject1 = new StringBuffer();
                ((StringBuffer)localObject1).append("more than one definitely applicable method `");
                ((StringBuffer)localObject1).append((String)localObject4);
                ((StringBuffer)localObject1).append("' in ");
                ((StringBuffer)localObject1).append(((ObjectType)localObject2).getName());
                append(paramProcedure, i2, (StringBuffer)localObject1);
                if (c == 'P') {}
                for (c = 'e';; c = 'w')
                {
                  localCompilation.error(c, ((StringBuffer)localObject1).toString());
                  j = i;
                  break;
                  label2563:
                  i = j;
                  label2567:
                  j += 1;
                  n = i;
                  break label2418;
                }
              }
              if (c != 'P')
              {
                j = i6;
                if (!localCompilation.warnInvokeUnknownMethod()) {
                  break label2202;
                }
              }
              localObject1 = new StringBuffer();
              ((StringBuffer)localObject1).append("more than one possibly applicable method '");
              ((StringBuffer)localObject1).append((String)localObject4);
              ((StringBuffer)localObject1).append("' in ");
              ((StringBuffer)localObject1).append(((ObjectType)localObject2).getName());
              append(paramProcedure, i4, (StringBuffer)localObject1);
              if (c == 'P') {}
              for (c = 'e';; c = 'w')
              {
                localCompilation.error(c, ((StringBuffer)localObject1).toString());
                j = i6;
                break;
              }
            }
          }
          paramProcedure = new ApplyExp(paramProcedure, (Expression[])localObject1);
          paramProcedure.setLine(paramApplyExp);
          return paramInlineCalls.visitApplyOnly(paramProcedure, paramType);
        }
      }
    }
    paramApplyExp.visitArgs(paramInlineCalls);
    return paramApplyExp;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\CompileInvoke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */