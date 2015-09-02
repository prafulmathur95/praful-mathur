package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.IgnoreTarget;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.Procedure;
import gnu.math.IntNum;

public class CompileArith
  implements Inlineable
{
  public static CompileArith $Mn = new CompileArith(AddOp.$Mn, 2);
  public static CompileArith $Pl = new CompileArith(AddOp.$Pl, 1);
  int op;
  Procedure proc;
  
  CompileArith(Object paramObject, int paramInt)
  {
    this.proc = ((Procedure)paramObject);
    this.op = paramInt;
  }
  
  static int adjustReturnKind(int paramInt1, int paramInt2)
  {
    if ((paramInt2 >= 4) && (paramInt2 <= 7) && (paramInt1 > 0)) {
      switch (paramInt2)
      {
      }
    }
    do
    {
      do
      {
        do
        {
          return paramInt1;
        } while (paramInt1 > 4);
        return 6;
      } while ((paramInt1 > 10) || (paramInt1 == 7));
      return 8;
    } while (paramInt1 > 10);
    return 4;
  }
  
  public static boolean appropriateIntConstant(Expression[] paramArrayOfExpression, int paramInt, InlineCalls paramInlineCalls)
  {
    paramInlineCalls = paramInlineCalls.fixIntValue(paramArrayOfExpression[paramInt]);
    if (paramInlineCalls != null)
    {
      paramArrayOfExpression[paramInt] = paramInlineCalls;
      return true;
    }
    return false;
  }
  
  public static boolean appropriateLongConstant(Expression[] paramArrayOfExpression, int paramInt, InlineCalls paramInlineCalls)
  {
    paramInlineCalls = paramInlineCalls.fixLongValue(paramArrayOfExpression[paramInt]);
    if (paramInlineCalls != null)
    {
      paramArrayOfExpression[paramInt] = paramInlineCalls;
      return true;
    }
    return false;
  }
  
  public static CompileArith forBitwise(Object paramObject)
  {
    return new CompileArith(paramObject, ((BitwiseOp)paramObject).op);
  }
  
  public static CompileArith forDiv(Object paramObject)
  {
    return new CompileArith(paramObject, ((DivideOp)paramObject).op);
  }
  
  public static CompileArith forMul(Object paramObject)
  {
    return new CompileArith(paramObject, 3);
  }
  
  public static int getReturnKind(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt3 >= 9) && (paramInt3 <= 12)) {
      return paramInt1;
    }
    if (paramInt1 > 0)
    {
      paramInt3 = paramInt2;
      if (paramInt1 > paramInt2)
      {
        paramInt3 = paramInt2;
        if (paramInt2 <= 0) {}
      }
    }
    else
    {
      paramInt3 = paramInt1;
    }
    return paramInt3;
  }
  
  public static Expression pairwise(Procedure paramProcedure, Expression paramExpression, Expression[] paramArrayOfExpression, InlineCalls paramInlineCalls)
  {
    int j = paramArrayOfExpression.length;
    Object localObject = paramArrayOfExpression[0];
    int i = 1;
    if (i < j)
    {
      localObject = new ApplyExp(paramExpression, new Expression[] { localObject, paramArrayOfExpression[i] });
      Expression localExpression = paramInlineCalls.maybeInline((ApplyExp)localObject, null, paramProcedure);
      if (localExpression != null) {
        localObject = localExpression;
      }
      for (;;)
      {
        i += 1;
        break;
      }
    }
    return (Expression)localObject;
  }
  
  public static Expression validateApplyAdd(AddOp paramAddOp, ApplyExp paramApplyExp, InlineCalls paramInlineCalls)
  {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    paramInlineCalls = paramApplyExp;
    int k;
    int i;
    if (arrayOfExpression.length == 1)
    {
      paramInlineCalls = paramApplyExp;
      if (paramAddOp.plusOrMinus < 0)
      {
        paramAddOp = arrayOfExpression[0].getType();
        paramInlineCalls = paramApplyExp;
        if ((paramAddOp instanceof PrimType))
        {
          k = paramAddOp.getSignature().charAt(0);
          paramInlineCalls = null;
          int j = 0;
          i = j;
          paramAddOp = paramInlineCalls;
          if (k != 86)
          {
            i = j;
            paramAddOp = paramInlineCalls;
            if (k != 90)
            {
              if (k != 67) {
                break label115;
              }
              paramAddOp = paramInlineCalls;
              i = j;
            }
          }
        }
      }
    }
    for (;;)
    {
      paramInlineCalls = paramApplyExp;
      if (paramAddOp != null) {
        paramInlineCalls = new ApplyExp(PrimProcedure.makeBuiltinUnary(i, paramAddOp), arrayOfExpression);
      }
      return paramInlineCalls;
      label115:
      if (k == 68)
      {
        i = 119;
        paramAddOp = LangPrimType.doubleType;
      }
      else if (k == 70)
      {
        i = 118;
        paramAddOp = LangPrimType.floatType;
      }
      else if (k == 74)
      {
        i = 117;
        paramAddOp = LangPrimType.longType;
      }
      else
      {
        i = 116;
        paramAddOp = LangPrimType.intType;
      }
    }
  }
  
  public static Expression validateApplyArithOp(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    int m = ((ArithOp)paramProcedure).op;
    paramApplyExp.visitArgs(paramInlineCalls);
    paramType = paramApplyExp.getArgs();
    if (paramType.length > 2) {
      paramType = pairwise(paramProcedure, paramApplyExp.getFunction(), paramType, paramInlineCalls);
    }
    int i;
    label272:
    label343:
    do
    {
      return paramType;
      Expression localExpression = paramApplyExp.inlineIfConstant(paramProcedure, paramInlineCalls);
      if (localExpression != paramApplyExp) {
        return localExpression;
      }
      i = 0;
      int j;
      int n;
      int k;
      if ((paramType.length == 2) || (paramType.length == 1))
      {
        j = Arithmetic.classifyType(paramType[0].getType());
        if ((paramType.length != 2) || ((m >= 9) && (m <= 12))) {
          break label343;
        }
        n = Arithmetic.classifyType(paramType[1].getType());
        k = getReturnKind(j, n, m);
        i = k;
        if (k == 4)
        {
          if ((j != 1) || (!appropriateIntConstant(paramType, 1, paramInlineCalls))) {
            break label272;
          }
          i = 1;
        }
      }
      for (;;)
      {
        i = adjustReturnKind(i, m);
        paramApplyExp.setType(Arithmetic.kindType(i));
        paramType = paramApplyExp;
        if (!paramInlineCalls.getCompilation().mustCompile) {
          break;
        }
        switch (m)
        {
        case 3: 
        case 9: 
        case 10: 
        case 11: 
        case 12: 
        case 13: 
        case 14: 
        case 15: 
        default: 
          return paramApplyExp;
        case 1: 
        case 2: 
          return validateApplyAdd((AddOp)paramProcedure, paramApplyExp, paramInlineCalls);
          if ((n == 1) && (appropriateIntConstant(paramType, 0, paramInlineCalls)))
          {
            i = 1;
          }
          else if ((j == 2) && (appropriateLongConstant(paramType, 1, paramInlineCalls)))
          {
            i = 2;
          }
          else
          {
            i = k;
            if (n == 2)
            {
              i = k;
              if (appropriateLongConstant(paramType, 0, paramInlineCalls))
              {
                i = 2;
                continue;
                i = j;
              }
            }
          }
          break;
        }
      }
      return validateApplyDiv((DivideOp)paramProcedure, paramApplyExp, paramInlineCalls);
      paramType = paramApplyExp;
    } while (i <= 0);
    return validateApplyNot(paramApplyExp, i, paramInlineCalls);
  }
  
  public static Expression validateApplyDiv(DivideOp paramDivideOp, ApplyExp paramApplyExp, InlineCalls paramInlineCalls)
  {
    paramInlineCalls = paramApplyExp.getArgs();
    paramDivideOp = paramApplyExp;
    if (paramInlineCalls.length == 1)
    {
      paramDivideOp = QuoteExp.getInstance(IntNum.one());
      paramInlineCalls = paramInlineCalls[0];
      paramDivideOp = new ApplyExp(paramApplyExp.getFunction(), new Expression[] { paramDivideOp, paramInlineCalls });
    }
    return paramDivideOp;
  }
  
  public static Expression validateApplyNot(ApplyExp paramApplyExp, int paramInt, InlineCalls paramInlineCalls)
  {
    Object localObject = paramApplyExp;
    if (paramApplyExp.getArgCount() == 1)
    {
      localObject = paramApplyExp.getArg(0);
      if ((paramInt == 1) || (paramInt == 2))
      {
        paramApplyExp = QuoteExp.getInstance(IntNum.minusOne());
        localObject = paramInlineCalls.visitApplyOnly(new ApplyExp(BitwiseOp.xor, new Expression[] { localObject, paramApplyExp }), null);
      }
    }
    else
    {
      return (Expression)localObject;
    }
    if (paramInt == 4) {
      paramInlineCalls = "gnu.math.BitOps";
    }
    for (;;)
    {
      localObject = paramApplyExp;
      if (paramInlineCalls == null) {
        break;
      }
      return new ApplyExp(ClassType.make(paramInlineCalls).getDeclaredMethod("not", 1), paramApplyExp.getArgs());
      if (paramInt == 3) {
        paramInlineCalls = "java.meth.BigInteger";
      } else {
        paramInlineCalls = null;
      }
    }
  }
  
  public static Expression validateApplyNumberCompare(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    paramApplyExp.visitArgs(paramInlineCalls);
    paramInlineCalls = paramApplyExp.inlineIfConstant(paramProcedure, paramInlineCalls);
    if (paramInlineCalls != paramApplyExp) {
      return paramInlineCalls;
    }
    return paramApplyExp;
  }
  
  public static Expression validateApplyNumberPredicate(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure)
  {
    int i = ((NumberPredicate)paramProcedure).op;
    paramType = paramApplyExp.getArgs();
    paramType[0] = paramInlineCalls.visit(paramType[0], LangObjType.integerType);
    paramApplyExp.setType(Type.booleanType);
    return paramApplyExp;
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int k = arrayOfExpression.length;
    if (k == 0)
    {
      paramCompilation.compileConstant(((ArithOp)this.proc).defaultResult(), paramTarget);
      return;
    }
    if ((k == 1) || ((paramTarget instanceof IgnoreTarget)))
    {
      ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
      return;
    }
    int m = Arithmetic.classifyType(arrayOfExpression[0].getType());
    int n = Arithmetic.classifyType(arrayOfExpression[1].getType());
    int j = getReturnKind(m, n, this.op);
    Object localObject1 = Arithmetic.kindType(j);
    if ((j == 0) || (k != 2))
    {
      ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
      return;
    }
    int i = Arithmetic.classifyType(paramTarget.getType());
    if (((i == 1) || (i == 2)) && (j >= 1) && (j <= 4))
    {
      j = i;
      if (i == 1)
      {
        localObject1 = LangPrimType.intType;
        i = j;
        j = i;
        if (this.op >= 4)
        {
          j = i;
          if (this.op <= 8)
          {
            localObject2 = (DivideOp)this.proc;
            if (((DivideOp)localObject2).op != 4) {
              break label482;
            }
            j = i;
            if (i > 4)
            {
              j = i;
              if (i < 6)
              {
                if (i > 9) {
                  break label482;
                }
                j = i;
              }
            }
          }
        }
        label241:
        if ((this.op != 4) || (j > 10) || (j == 8) || (j == 7)) {
          break label665;
        }
        if ((j != 6) && (j <= 4)) {
          break label646;
        }
        if (j != 6) {
          break label639;
        }
        paramApplyExp = Arithmetic.typeRatNum;
        label294:
        localObject1 = paramApplyExp;
        localObject2 = paramApplyExp.getDeclaredMethod("divide", 2);
        paramApplyExp = (ApplyExp)localObject1;
        localObject1 = localObject2;
        label314:
        localObject2 = StackTarget.getInstance(paramApplyExp);
        arrayOfExpression[0].compile(paramCompilation, (Target)localObject2);
        arrayOfExpression[1].compile(paramCompilation, (Target)localObject2);
        paramCompilation.getCode().emitInvokeStatic((Method)localObject1);
        localObject2 = paramApplyExp;
      }
    }
    label482:
    label639:
    label646:
    label665:
    label783:
    CodeAttr localCodeAttr;
    do
    {
      for (;;)
      {
        paramTarget.compileFromStack(paramCompilation, (Type)localObject2);
        return;
        localObject1 = LangPrimType.longType;
        i = j;
        break;
        if (((i == 8) || (i == 7)) && (j > 2) && (j <= 10))
        {
          j = i;
          if (i == 7) {}
          for (localObject1 = LangPrimType.floatType;; localObject1 = LangPrimType.doubleType)
          {
            i = j;
            break;
          }
        }
        if (j == 7)
        {
          localObject1 = LangPrimType.floatType;
          i = j;
          break;
        }
        if ((j == 8) || (j == 9))
        {
          i = 8;
          localObject1 = LangPrimType.doubleType;
          break;
        }
        i = j;
        break;
        if (((((DivideOp)localObject2).op == 5) && (i <= 10) && (i != 7)) || ((((DivideOp)localObject2).op == 4) && (i == 10)))
        {
          j = 8;
          break label241;
        }
        if ((((DivideOp)localObject2).op == 7) || ((((DivideOp)localObject2).op == 6) && (i <= 4)))
        {
          j = i;
          if (((DivideOp)localObject2).getRoundingMode() == 3) {
            break label241;
          }
          j = i;
          if (i == 4) {
            break label241;
          }
          j = i;
          if (i == 7) {
            break label241;
          }
          j = i;
          if (i == 8) {
            break label241;
          }
        }
        if (((DivideOp)localObject2).op == 8)
        {
          j = i;
          if (((DivideOp)localObject2).getRoundingMode() == 3) {
            break label241;
          }
          j = i;
          if (i == 4) {
            break label241;
          }
        }
        ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
        return;
        paramApplyExp = Arithmetic.typeRealNum;
        break label294;
        paramApplyExp = Arithmetic.typeIntNum;
        localObject1 = Arithmetic.typeRatNum.getDeclaredMethod("make", 2);
        break label314;
        if ((j != 4) || ((this.op != 1) && (this.op != 3) && (this.op != 2) && (this.op != 13) && (this.op != 14) && (this.op != 15) && (this.op != 7) && (this.op != 8) && ((this.op < 9) || (this.op > 11)))) {
          break label783;
        }
        compileIntNum(arrayOfExpression[0], arrayOfExpression[1], m, n, paramCompilation);
        localObject2 = localObject1;
      }
      if ((j != 1) && (j != 2) && (((j != 7) && (j != 8)) || ((this.op > 8) && (this.op < 13)))) {
        break label1041;
      }
      paramApplyExp = StackTarget.getInstance((Type)localObject1);
      localCodeAttr = paramCompilation.getCode();
      i = 0;
      localObject2 = localObject1;
    } while (i >= k);
    Object localObject2 = paramApplyExp;
    if (i == 1)
    {
      localObject2 = paramApplyExp;
      if (this.op >= 9)
      {
        localObject2 = paramApplyExp;
        if (this.op <= 12) {
          localObject2 = StackTarget.getInstance(Type.intType);
        }
      }
    }
    arrayOfExpression[i].compile(paramCompilation, (Target)localObject2);
    if (i == 0) {}
    for (;;)
    {
      i += 1;
      paramApplyExp = (ApplyExp)localObject2;
      break;
      switch (j)
      {
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      default: 
        break;
      case 1: 
      case 2: 
      case 7: 
      case 8: 
        if (this.op == 9)
        {
          paramApplyExp = Type.intType;
          localCodeAttr.emitInvokeStatic(ClassType.make("gnu.math.IntNum").getDeclaredMethod("shift", new Type[] { localObject1, paramApplyExp }));
        }
        else
        {
          localCodeAttr.emitBinop(primitiveOpcode(), (PrimType)((Type)localObject1).getImplementationType());
        }
        break;
      }
    }
    label1041:
    ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
  }
  
  public boolean compileIntNum(Expression paramExpression1, Expression paramExpression2, int paramInt1, int paramInt2, Compilation paramCompilation)
  {
    Object localObject1;
    if ((this.op == 2) && ((paramExpression2 instanceof QuoteExp)))
    {
      localObject1 = paramExpression2.valueIfConstant();
      long l;
      boolean bool;
      if (paramInt2 <= 2)
      {
        l = ((Number)localObject1).longValue();
        if ((l > -2147483648L) && (l <= 2147483647L)) {
          bool = true;
        }
      }
      while (bool)
      {
        return $Pl.compileIntNum(paramExpression1, QuoteExp.getInstance(Integer.valueOf((int)-l)), paramInt1, 1, paramCompilation);
        bool = false;
        continue;
        if ((localObject1 instanceof IntNum))
        {
          localObject1 = (IntNum)localObject1;
          l = ((IntNum)localObject1).longValue();
          bool = ((IntNum)localObject1).inRange(-2147483647L, 2147483647L);
        }
        else
        {
          bool = false;
          l = 0L;
        }
      }
    }
    int i;
    if ((this.op == 1) || (this.op == 3))
    {
      i = 1;
      if (i == 0) {
        break label424;
      }
      i = paramInt1;
      if (InlineCalls.checkIntValue(paramExpression1) != null) {
        i = 1;
      }
      if (InlineCalls.checkIntValue(paramExpression2) != null) {
        paramInt2 = 1;
      }
      if ((i != 1) || (paramInt2 == 1)) {
        break label236;
      }
    }
    label236:
    for (paramInt1 = 1;; paramInt1 = 0)
    {
      if ((paramInt1 == 0) || ((paramExpression1.side_effects()) && (paramExpression2.side_effects()))) {
        break label241;
      }
      return compileIntNum(paramExpression2, paramExpression1, paramInt2, i, paramCompilation);
      i = 0;
      break;
    }
    label241:
    Object localObject2;
    if (i == 1)
    {
      localObject2 = Type.intType;
      label252:
      if (paramInt2 != 1) {
        break label416;
      }
      localObject1 = Type.intType;
    }
    CodeAttr localCodeAttr;
    Object localObject3;
    Object localObject4;
    LangObjType localLangObjType;
    for (;;)
    {
      paramExpression1.compile(paramCompilation, (Type)localObject2);
      paramExpression2.compile(paramCompilation, (Type)localObject1);
      localCodeAttr = paramCompilation.getCode();
      localObject3 = localObject1;
      if (paramInt1 != 0)
      {
        localCodeAttr.emitSwap();
        localObject2 = Arithmetic.typeIntNum;
        localObject3 = LangPrimType.intType;
      }
      paramExpression1 = null;
      paramExpression2 = null;
      localObject4 = null;
      localLangObjType = Arithmetic.typeIntNum;
      switch (this.op)
      {
      case 12: 
      default: 
        throw new Error();
        localObject2 = Arithmetic.typeIntNum;
        break label252;
        label416:
        localObject1 = Arithmetic.typeIntNum;
        continue;
        label424:
        if ((this.op >= 9) && (this.op <= 12))
        {
          localObject2 = Arithmetic.typeIntNum;
          localObject1 = Type.intType;
          paramInt1 = 0;
        }
        else
        {
          localObject1 = Arithmetic.typeIntNum;
          localObject2 = localObject1;
          paramInt1 = 0;
        }
        break;
      }
    }
    paramExpression1 = "add";
    paramCompilation = localLangObjType;
    paramExpression2 = (Expression)localObject4;
    for (;;)
    {
      localObject1 = paramExpression2;
      if (paramExpression2 == null)
      {
        localObject1 = new Type[2];
        localObject1[0] = localObject2;
        localObject1[1] = localObject3;
      }
      localCodeAttr.emitInvokeStatic(paramCompilation.getMethod(paramExpression1, (Type[])localObject1));
      return true;
      paramExpression1 = "sub";
      paramExpression2 = (Expression)localObject4;
      paramCompilation = localLangObjType;
      continue;
      paramExpression1 = "times";
      paramExpression2 = (Expression)localObject4;
      paramCompilation = localLangObjType;
      continue;
      paramExpression2 = "and";
      paramExpression1 = paramExpression2;
      if (paramExpression2 == null) {
        paramExpression1 = "ior";
      }
      localObject1 = paramExpression1;
      if (paramExpression1 == null) {
        localObject1 = "xor";
      }
      paramCompilation = ClassType.make("gnu.math.BitOps");
      paramExpression2 = (Expression)localObject4;
      paramExpression1 = (Expression)localObject1;
      continue;
      if (this.op == 8) {}
      DivideOp localDivideOp;
      for (localObject1 = "remainder";; localObject1 = "quotient")
      {
        localDivideOp = (DivideOp)this.proc;
        if ((this.op != 8) || (localDivideOp.rounding_mode != 1)) {
          break label655;
        }
        paramExpression1 = "modulo";
        paramExpression2 = (Expression)localObject4;
        paramCompilation = localLangObjType;
        break;
      }
      label655:
      paramExpression2 = (Expression)localObject4;
      paramCompilation = localLangObjType;
      paramExpression1 = (Expression)localObject1;
      if (localDivideOp.rounding_mode != 3)
      {
        localCodeAttr.emitPushInt(localDivideOp.rounding_mode);
        paramExpression2 = new Type[3];
        paramExpression2[0] = localObject2;
        paramExpression2[1] = localObject3;
        paramExpression2[2] = Type.intType;
        paramCompilation = localLangObjType;
        paramExpression1 = (Expression)localObject1;
        continue;
        if (this.op == 10) {}
        for (paramExpression1 = "shiftLeft";; paramExpression1 = "shiftRight")
        {
          paramCompilation = ClassType.make("gnu.kawa.functions.BitwiseOp");
          paramExpression2 = (Expression)localObject4;
          break;
        }
        paramExpression1 = "shift";
        paramExpression2 = (Expression)localObject4;
        paramCompilation = localLangObjType;
      }
    }
  }
  
  public int getReturnKind(Expression[] paramArrayOfExpression)
  {
    int n = paramArrayOfExpression.length;
    int k;
    if (n == 0)
    {
      k = 4;
      return k;
    }
    ClassType localClassType = Type.pointer_type;
    int i = 0;
    int j = 0;
    for (;;)
    {
      k = i;
      if (j >= n) {
        break;
      }
      int m = Arithmetic.classifyType(paramArrayOfExpression[j].getType());
      if ((j != 0) && (m != 0))
      {
        k = i;
        if (m <= i) {}
      }
      else
      {
        k = m;
      }
      j += 1;
      i = k;
    }
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return Arithmetic.kindType(adjustReturnKind(getReturnKind(paramArrayOfExpression), this.op));
  }
  
  public int primitiveOpcode()
  {
    switch (this.op)
    {
    case 9: 
    default: 
      return -1;
    case 1: 
      return 96;
    case 2: 
      return 100;
    case 3: 
      return 104;
    case 4: 
    case 5: 
    case 6: 
    case 7: 
      return 108;
    case 8: 
      return 112;
    case 10: 
      return 120;
    case 11: 
      return 122;
    case 12: 
      return 124;
    case 13: 
      return 126;
    case 14: 
      return 128;
    }
    return 130;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\CompileArith.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */