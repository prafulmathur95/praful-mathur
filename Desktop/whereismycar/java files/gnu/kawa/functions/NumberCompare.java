package gnu.kawa.functions;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangObjType;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberCompare
  extends ProcedureN
  implements Inlineable
{
  static final int RESULT_EQU = 0;
  static final int RESULT_GRT = 1;
  static final int RESULT_LSS = -1;
  static final int RESULT_NAN = -2;
  static final int RESULT_NEQ = -3;
  public static final int TRUE_IF_EQU = 8;
  public static final int TRUE_IF_GRT = 16;
  public static final int TRUE_IF_LSS = 4;
  public static final int TRUE_IF_NAN = 2;
  public static final int TRUE_IF_NEQ = 1;
  int flags;
  Language language;
  
  public static boolean $Eq(Object paramObject1, Object paramObject2)
  {
    return apply2(8, paramObject1, paramObject2);
  }
  
  public static boolean $Eq$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ($Eq(paramObject1, paramObject2))
    {
      bool1 = bool2;
      if ($Eq(paramObject2, paramObject3)) {
        if (paramArrayOfObject.length != 0)
        {
          bool1 = bool2;
          if ($Eq(paramObject3, paramArrayOfObject[0]))
          {
            bool1 = bool2;
            if (!applyN(8, paramArrayOfObject)) {}
          }
        }
        else
        {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean $Gr(Object paramObject1, Object paramObject2)
  {
    return apply2(16, paramObject1, paramObject2);
  }
  
  public static boolean $Gr$Eq(Object paramObject1, Object paramObject2)
  {
    return apply2(24, paramObject1, paramObject2);
  }
  
  public static boolean $Gr$Eq$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ($Gr$Eq(paramObject1, paramObject2))
    {
      bool1 = bool2;
      if ($Gr$Eq(paramObject2, paramObject3)) {
        if (paramArrayOfObject.length != 0)
        {
          bool1 = bool2;
          if ($Gr$Eq(paramObject3, paramArrayOfObject[0]))
          {
            bool1 = bool2;
            if (!applyN(24, paramArrayOfObject)) {}
          }
        }
        else
        {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean $Gr$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ($Gr(paramObject1, paramObject2))
    {
      bool1 = bool2;
      if ($Gr(paramObject2, paramObject3)) {
        if (paramArrayOfObject.length != 0)
        {
          bool1 = bool2;
          if ($Gr(paramObject3, paramArrayOfObject[0]))
          {
            bool1 = bool2;
            if (!applyN(16, paramArrayOfObject)) {}
          }
        }
        else
        {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean $Ls(Object paramObject1, Object paramObject2)
  {
    return apply2(4, paramObject1, paramObject2);
  }
  
  public static boolean $Ls$Eq(Object paramObject1, Object paramObject2)
  {
    return apply2(12, paramObject1, paramObject2);
  }
  
  public static boolean $Ls$Eq$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ($Ls$Eq(paramObject1, paramObject2))
    {
      bool1 = bool2;
      if ($Ls$Eq(paramObject2, paramObject3)) {
        if (paramArrayOfObject.length != 0)
        {
          bool1 = bool2;
          if ($Ls$Eq(paramObject3, paramArrayOfObject[0]))
          {
            bool1 = bool2;
            if (!applyN(12, paramArrayOfObject)) {}
          }
        }
        else
        {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean $Ls$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ($Ls(paramObject1, paramObject2))
    {
      bool1 = bool2;
      if ($Ls(paramObject2, paramObject3)) {
        if (paramArrayOfObject.length != 0)
        {
          bool1 = bool2;
          if ($Ls(paramObject3, paramArrayOfObject[0]))
          {
            bool1 = bool2;
            if (!applyN(4, paramArrayOfObject)) {}
          }
        }
        else
        {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean apply2(int paramInt, Object paramObject1, Object paramObject2)
  {
    return (1 << compare(paramObject1, paramObject2, true) + 3 & paramInt) != 0;
  }
  
  static boolean applyN(int paramInt, Object[] paramArrayOfObject)
  {
    int i = 0;
    while (i < paramArrayOfObject.length - 1)
    {
      if (!apply2(paramInt, paramArrayOfObject[i], paramArrayOfObject[(i + 1)])) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static boolean applyWithPromotion(int paramInt, Object paramObject1, Object paramObject2)
  {
    return checkCompareCode(compare(paramObject1, paramObject2, false), paramInt);
  }
  
  public static boolean checkCompareCode(int paramInt1, int paramInt2)
  {
    return (1 << paramInt1 + 3 & paramInt2) != 0;
  }
  
  static int classify(Expression paramExpression)
  {
    int j = Arithmetic.classifyType(paramExpression.getType());
    int i = j;
    int k;
    if (j == 4)
    {
      i = j;
      if ((paramExpression instanceof QuoteExp))
      {
        paramExpression = ((QuoteExp)paramExpression).getValue();
        i = j;
        if ((paramExpression instanceof IntNum))
        {
          k = ((IntNum)paramExpression).intLength();
          if (k >= 32) {
            break label59;
          }
          i = 1;
        }
      }
    }
    label59:
    do
    {
      return i;
      i = j;
    } while (k >= 64);
    return 2;
  }
  
  public static int compare(Object paramObject1, int paramInt1, Object paramObject2, int paramInt2, boolean paramBoolean)
  {
    if ((paramInt1 < 0) || (paramInt2 < 0)) {
      return -3;
    }
    int i;
    if (paramInt1 < paramInt2)
    {
      i = paramInt2;
      switch (i)
      {
      }
    }
    do
    {
      return Arithmetic.asNumeric(paramObject1).compare(Arithmetic.asNumeric(paramObject2));
      i = paramInt1;
      break;
      paramInt1 = Arithmetic.asInt(paramObject1);
      paramInt2 = Arithmetic.asInt(paramObject2);
      if (paramInt1 < paramInt2) {
        return -1;
      }
      if (paramInt1 > paramInt2) {
        return 1;
      }
      return 0;
      long l1 = Arithmetic.asLong(paramObject1);
      long l2 = Arithmetic.asLong(paramObject2);
      if (l1 < l2) {
        return -1;
      }
      if (l1 > l2) {
        return 1;
      }
      return 0;
      return Arithmetic.asBigInteger(paramObject1).compareTo(Arithmetic.asBigInteger(paramObject2));
      return IntNum.compare(Arithmetic.asIntNum(paramObject1), Arithmetic.asIntNum(paramObject2));
      return Arithmetic.asBigDecimal(paramObject1).compareTo(Arithmetic.asBigDecimal(paramObject2));
      return RatNum.compare(Arithmetic.asRatNum(paramObject1), Arithmetic.asRatNum(paramObject2));
      if ((!paramBoolean) || ((paramInt1 > 6) && (paramInt2 > 6)))
      {
        float f1 = Arithmetic.asFloat(paramObject1);
        float f2 = Arithmetic.asFloat(paramObject2);
        if (f1 > f2) {
          return 1;
        }
        if (f1 < f2) {
          return -1;
        }
        if (f1 == f2) {
          return 0;
        }
        return -2;
      }
    } while ((paramBoolean) && ((paramInt1 <= 6) || (paramInt2 <= 6)));
    double d1 = Arithmetic.asDouble(paramObject1);
    double d2 = Arithmetic.asDouble(paramObject2);
    if (d1 > d2) {
      return 1;
    }
    if (d1 < d2) {
      return -1;
    }
    if (d1 == d2) {
      return 0;
    }
    return -2;
  }
  
  public static int compare(Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    return compare(paramObject1, Arithmetic.classifyValue(paramObject1), paramObject2, Arithmetic.classifyValue(paramObject2), paramBoolean);
  }
  
  public static NumberCompare make(Language paramLanguage, String paramString, int paramInt)
  {
    NumberCompare localNumberCompare = new NumberCompare();
    localNumberCompare.language = paramLanguage;
    localNumberCompare.setName(paramString);
    localNumberCompare.flags = paramInt;
    localNumberCompare.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyNumberCompare");
    return localNumberCompare;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
  {
    return getLanguage().booleanObject(apply2(this.flags, paramObject1, paramObject2));
  }
  
  public Object applyN(Object[] paramArrayOfObject)
  {
    return getLanguage().booleanObject(applyN(this.flags, paramArrayOfObject));
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    Object localObject4 = paramApplyExp.getArgs();
    if (localObject4.length == 2)
    {
      Object localObject2 = localObject4[0];
      Object localObject3 = localObject4[1];
      int n = classify((Expression)localObject2);
      int i1 = classify((Expression)localObject3);
      CodeAttr localCodeAttr = paramCompilation.getCode();
      if ((n > 0) && (i1 > 0) && (n <= 10) && (i1 <= 10) && ((n != 6) || (i1 != 6)))
      {
        if (!(paramTarget instanceof ConditionalTarget))
        {
          IfExp.compile(paramApplyExp, QuoteExp.trueExp, QuoteExp.falseExp, paramCompilation, paramTarget);
          return;
        }
        int j = this.flags;
        int i = j;
        if (j == 1) {
          i = 20;
        }
        Object localObject1 = localObject2;
        paramApplyExp = (ApplyExp)localObject3;
        int m = n;
        int k = i1;
        j = i;
        Type[] arrayOfType;
        if (n <= 4)
        {
          localObject1 = localObject2;
          paramApplyExp = (ApplyExp)localObject3;
          m = n;
          k = i1;
          j = i;
          if (i1 <= 4) {
            if (n <= 2)
            {
              localObject1 = localObject2;
              paramApplyExp = (ApplyExp)localObject3;
              m = n;
              k = i1;
              j = i;
              if (i1 <= 2) {}
            }
            else
            {
              arrayOfType = new Type[2];
              arrayOfType[0] = Arithmetic.typeIntNum;
              if (i1 > 2) {
                break label566;
              }
              arrayOfType[1] = Type.longType;
              j = i;
              paramApplyExp = (ApplyExp)localObject4;
              localObject1 = new ApplyExp(new PrimProcedure(Arithmetic.typeIntNum.getMethod("compare", arrayOfType)), paramApplyExp);
              paramApplyExp = new QuoteExp(IntNum.zero());
              k = 1;
              m = 1;
            }
          }
        }
        if ((m <= 1) && (k <= 1))
        {
          localObject2 = Type.intType;
          label298:
          localObject3 = new StackTarget((Type)localObject2);
          localObject4 = (ConditionalTarget)paramTarget;
          localObject2 = localObject1;
          paramTarget = paramApplyExp;
          i = j;
          if ((localObject1 instanceof QuoteExp))
          {
            localObject2 = localObject1;
            paramTarget = paramApplyExp;
            i = j;
            if (!(paramApplyExp instanceof QuoteExp))
            {
              localObject2 = paramApplyExp;
              paramTarget = (Target)localObject1;
              i = j;
              if (j != 8)
              {
                localObject2 = paramApplyExp;
                paramTarget = (Target)localObject1;
                i = j;
                if (j != 20)
                {
                  i = j ^ 0x14;
                  paramTarget = (Target)localObject1;
                  localObject2 = paramApplyExp;
                }
              }
            }
          }
          if (!((ConditionalTarget)localObject4).trueBranchComesFirst) {
            break label715;
          }
          paramApplyExp = ((ConditionalTarget)localObject4).ifFalse;
          label411:
          j = i;
          if (((ConditionalTarget)localObject4).trueBranchComesFirst) {
            j = i ^ 0x1C;
          }
          switch (j)
          {
          default: 
            i = 0;
            label495:
            ((Expression)localObject2).compile(paramCompilation, (Target)localObject3);
            if ((m <= 1) && (k <= 1) && ((paramTarget instanceof QuoteExp)))
            {
              localObject1 = ((QuoteExp)paramTarget).getValue();
              if (((localObject1 instanceof IntNum)) && (((IntNum)localObject1).isZero())) {
                localCodeAttr.emitGotoIfCompare1(paramApplyExp, i);
              }
            }
            break;
          }
        }
        for (;;)
        {
          ((ConditionalTarget)localObject4).emitGotoFirstBranch(localCodeAttr);
          return;
          label566:
          if ((n <= 2) && (((localObject2 instanceof QuoteExp)) || ((localObject3 instanceof QuoteExp)) || ((localObject2 instanceof ReferenceExp)) || ((localObject3 instanceof ReferenceExp))))
          {
            arrayOfType[1] = Type.longType;
            localObject1 = new Expression[2];
            localObject1[0] = localObject3;
            localObject1[1] = localObject2;
            paramApplyExp = (ApplyExp)localObject1;
            j = i;
            if (i == 8) {
              break;
            }
            paramApplyExp = (ApplyExp)localObject1;
            j = i;
            if (i == 20) {
              break;
            }
            j = i ^ 0x14;
            paramApplyExp = (ApplyExp)localObject1;
            break;
          }
          arrayOfType[1] = Arithmetic.typeIntNum;
          paramApplyExp = (ApplyExp)localObject4;
          j = i;
          break;
          if ((m <= 2) && (k <= 2))
          {
            localObject2 = Type.longType;
            break label298;
          }
          localObject2 = Type.doubleType;
          break label298;
          label715:
          paramApplyExp = ((ConditionalTarget)localObject4).ifTrue;
          break label411;
          i = 157;
          break label495;
          i = 153;
          break label495;
          i = 155;
          break label495;
          i = 154;
          break label495;
          i = 156;
          break label495;
          i = 158;
          break label495;
          paramTarget.compile(paramCompilation, (Target)localObject3);
          localCodeAttr.emitGotoIfCompare2(paramApplyExp, i);
        }
      }
    }
    ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
  }
  
  protected final Language getLanguage()
  {
    return this.language;
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return Type.booleanType;
  }
  
  public int numArgs()
  {
    return 61442;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\NumberCompare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */