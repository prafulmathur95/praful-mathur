package kawa.lib;

import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Arithmetic;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.BitOps;
import gnu.math.Complex;
import gnu.math.DComplex;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.Quantity;
import gnu.math.RatNum;
import gnu.math.RealNum;
import gnu.math.Unit;
import java.math.BigDecimal;
import java.math.BigInteger;
import kawa.standard.Scheme;

public class numbers
  extends ModuleBody
{
  public static final numbers $instance;
  static final IntNum Lit0;
  static final SimpleSymbol Lit1;
  static final SimpleSymbol Lit10;
  static final SimpleSymbol Lit11;
  static final SimpleSymbol Lit12;
  static final SimpleSymbol Lit13;
  static final SimpleSymbol Lit14;
  static final SimpleSymbol Lit15;
  static final SimpleSymbol Lit16;
  static final SimpleSymbol Lit17;
  static final SimpleSymbol Lit18;
  static final SimpleSymbol Lit19;
  static final IntNum Lit2;
  static final SimpleSymbol Lit20;
  static final SimpleSymbol Lit21;
  static final SimpleSymbol Lit22;
  static final SimpleSymbol Lit23;
  static final SimpleSymbol Lit24;
  static final SimpleSymbol Lit25;
  static final SimpleSymbol Lit26;
  static final SimpleSymbol Lit27;
  static final SimpleSymbol Lit28;
  static final SimpleSymbol Lit29;
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit30;
  static final SimpleSymbol Lit31;
  static final SimpleSymbol Lit32;
  static final SimpleSymbol Lit33;
  static final SimpleSymbol Lit34;
  static final SimpleSymbol Lit35;
  static final SimpleSymbol Lit36;
  static final SimpleSymbol Lit37;
  static final SimpleSymbol Lit38;
  static final SimpleSymbol Lit39;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit40;
  static final SimpleSymbol Lit41;
  static final SimpleSymbol Lit42;
  static final SimpleSymbol Lit43;
  static final SimpleSymbol Lit44;
  static final SimpleSymbol Lit45;
  static final SimpleSymbol Lit46;
  static final SimpleSymbol Lit47;
  static final SimpleSymbol Lit48;
  static final SimpleSymbol Lit49;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit50;
  static final SimpleSymbol Lit51;
  static final SimpleSymbol Lit52;
  static final SimpleSymbol Lit53;
  static final SimpleSymbol Lit54;
  static final SimpleSymbol Lit55;
  static final SimpleSymbol Lit56;
  static final SimpleSymbol Lit57;
  static final SimpleSymbol Lit58;
  static final SimpleSymbol Lit59;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit60;
  static final SimpleSymbol Lit61;
  static final SimpleSymbol Lit62;
  static final SimpleSymbol Lit63 = (SimpleSymbol)new SimpleSymbol("duration").readResolve();
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod abs;
  public static final ModuleMethod acos;
  public static final ModuleMethod angle;
  public static final ModuleMethod asin;
  public static final GenericProc atan;
  public static final ModuleMethod bitwise$Mnbit$Mncount;
  public static final ModuleMethod bitwise$Mnbit$Mnfield;
  public static final ModuleMethod bitwise$Mnbit$Mnset$Qu;
  public static final ModuleMethod bitwise$Mncopy$Mnbit;
  public static final ModuleMethod bitwise$Mncopy$Mnbit$Mnfield;
  public static final ModuleMethod bitwise$Mnfirst$Mnbit$Mnset;
  public static final ModuleMethod bitwise$Mnif;
  public static final ModuleMethod bitwise$Mnlength;
  public static final ModuleMethod bitwise$Mnreverse$Mnbit$Mnfield;
  public static final ModuleMethod bitwise$Mnrotate$Mnbit$Mnfield;
  public static final ModuleMethod ceiling;
  public static final ModuleMethod complex$Qu;
  public static final ModuleMethod cos;
  public static final ModuleMethod denominator;
  public static final ModuleMethod div$Mnand$Mnmod;
  public static final ModuleMethod div0$Mnand$Mnmod0;
  public static final ModuleMethod duration;
  public static final ModuleMethod exact;
  public static final ModuleMethod exact$Mn$Grinexact;
  public static final ModuleMethod exact$Qu;
  public static final ModuleMethod exp;
  public static final ModuleMethod floor;
  public static final ModuleMethod gcd;
  public static final ModuleMethod imag$Mnpart;
  public static final ModuleMethod inexact;
  public static final ModuleMethod inexact$Mn$Grexact;
  public static final ModuleMethod inexact$Qu;
  public static final ModuleMethod integer$Qu;
  static final ModuleMethod lambda$Fn1;
  static final ModuleMethod lambda$Fn2;
  static final ModuleMethod lambda$Fn3;
  static final ModuleMethod lambda$Fn4;
  public static final ModuleMethod lcm;
  public static final ModuleMethod log;
  public static final ModuleMethod logcount;
  public static final ModuleMethod logop;
  public static final ModuleMethod logtest;
  public static final ModuleMethod magnitude;
  public static final ModuleMethod make$Mnpolar;
  public static final ModuleMethod make$Mnquantity;
  public static final ModuleMethod make$Mnrectangular;
  public static final ModuleMethod max;
  public static final ModuleMethod min;
  public static final ModuleMethod negative$Qu;
  public static final ModuleMethod number$Mn$Grstring;
  public static final ModuleMethod number$Qu;
  public static final ModuleMethod numerator;
  public static final ModuleMethod positive$Qu;
  public static final ModuleMethod quantity$Mn$Grnumber;
  public static final ModuleMethod quantity$Mn$Grunit;
  public static final ModuleMethod quantity$Qu;
  public static final ModuleMethod rational$Qu;
  public static final ModuleMethod rationalize;
  public static final ModuleMethod real$Mnpart;
  public static final ModuleMethod real$Qu;
  public static final ModuleMethod round;
  public static final ModuleMethod sin;
  public static final GenericProc sqrt;
  public static final ModuleMethod string$Mn$Grnumber;
  public static final ModuleMethod tan;
  public static final ModuleMethod truncate;
  public static final ModuleMethod zero$Qu;
  
  static
  {
    Lit62 = (SimpleSymbol)new SimpleSymbol("make-quantity").readResolve();
    Lit61 = (SimpleSymbol)new SimpleSymbol("quantity->unit").readResolve();
    Lit60 = (SimpleSymbol)new SimpleSymbol("quantity->number").readResolve();
    Lit59 = (SimpleSymbol)new SimpleSymbol("string->number").readResolve();
    Lit58 = (SimpleSymbol)new SimpleSymbol("number->string").readResolve();
    Lit57 = (SimpleSymbol)new SimpleSymbol("bitwise-reverse-bit-field").readResolve();
    Lit56 = (SimpleSymbol)new SimpleSymbol("bitwise-rotate-bit-field").readResolve();
    Lit55 = (SimpleSymbol)new SimpleSymbol("bitwise-first-bit-set").readResolve();
    Lit54 = (SimpleSymbol)new SimpleSymbol("bitwise-length").readResolve();
    Lit53 = (SimpleSymbol)new SimpleSymbol("bitwise-bit-count").readResolve();
    Lit52 = (SimpleSymbol)new SimpleSymbol("logcount").readResolve();
    Lit51 = (SimpleSymbol)new SimpleSymbol("logtest").readResolve();
    Lit50 = (SimpleSymbol)new SimpleSymbol("bitwise-if").readResolve();
    Lit49 = (SimpleSymbol)new SimpleSymbol("bitwise-bit-field").readResolve();
    Lit48 = (SimpleSymbol)new SimpleSymbol("bitwise-copy-bit-field").readResolve();
    Lit47 = (SimpleSymbol)new SimpleSymbol("bitwise-copy-bit").readResolve();
    Lit46 = (SimpleSymbol)new SimpleSymbol("bitwise-bit-set?").readResolve();
    Lit45 = (SimpleSymbol)new SimpleSymbol("logop").readResolve();
    Lit44 = (SimpleSymbol)new SimpleSymbol("inexact->exact").readResolve();
    Lit43 = (SimpleSymbol)new SimpleSymbol("exact->inexact").readResolve();
    Lit42 = (SimpleSymbol)new SimpleSymbol("exact").readResolve();
    Lit41 = (SimpleSymbol)new SimpleSymbol("inexact").readResolve();
    Lit40 = (SimpleSymbol)new SimpleSymbol("angle").readResolve();
    Lit39 = (SimpleSymbol)new SimpleSymbol("magnitude").readResolve();
    Lit38 = (SimpleSymbol)new SimpleSymbol("imag-part").readResolve();
    Lit37 = (SimpleSymbol)new SimpleSymbol("real-part").readResolve();
    Lit36 = (SimpleSymbol)new SimpleSymbol("make-polar").readResolve();
    Lit35 = (SimpleSymbol)new SimpleSymbol("make-rectangular").readResolve();
    Lit34 = (SimpleSymbol)new SimpleSymbol("acos").readResolve();
    Lit33 = (SimpleSymbol)new SimpleSymbol("asin").readResolve();
    Lit32 = (SimpleSymbol)new SimpleSymbol("tan").readResolve();
    Lit31 = (SimpleSymbol)new SimpleSymbol("cos").readResolve();
    Lit30 = (SimpleSymbol)new SimpleSymbol("sin").readResolve();
    Lit29 = (SimpleSymbol)new SimpleSymbol("log").readResolve();
    Lit28 = (SimpleSymbol)new SimpleSymbol("exp").readResolve();
    Lit27 = (SimpleSymbol)new SimpleSymbol("rationalize").readResolve();
    Lit26 = (SimpleSymbol)new SimpleSymbol("round").readResolve();
    Lit25 = (SimpleSymbol)new SimpleSymbol("truncate").readResolve();
    Lit24 = (SimpleSymbol)new SimpleSymbol("ceiling").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("floor").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("denominator").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("numerator").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("lcm").readResolve();
    Lit19 = (SimpleSymbol)new SimpleSymbol("gcd").readResolve();
    Lit18 = (SimpleSymbol)new SimpleSymbol("div0-and-mod0").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("div-and-mod").readResolve();
    Lit16 = (SimpleSymbol)new SimpleSymbol("abs").readResolve();
    Lit15 = (SimpleSymbol)new SimpleSymbol("min").readResolve();
    Lit14 = (SimpleSymbol)new SimpleSymbol("max").readResolve();
    Lit13 = (SimpleSymbol)new SimpleSymbol("negative?").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("positive?").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("zero?").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("inexact?").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("exact?").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("integer?").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("rational?").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("real?").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("complex?").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("quantity?").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("number?").readResolve();
    Lit2 = IntNum.make(1);
    Lit1 = (SimpleSymbol)new SimpleSymbol("signum").readResolve();
    Lit0 = IntNum.make(0);
    $instance = new numbers();
    numbers localnumbers = $instance;
    number$Qu = new ModuleMethod(localnumbers, 1, Lit3, 4097);
    quantity$Qu = new ModuleMethod(localnumbers, 2, Lit4, 4097);
    complex$Qu = new ModuleMethod(localnumbers, 3, Lit5, 4097);
    real$Qu = new ModuleMethod(localnumbers, 4, Lit6, 4097);
    rational$Qu = new ModuleMethod(localnumbers, 5, Lit7, 4097);
    integer$Qu = new ModuleMethod(localnumbers, 6, Lit8, 4097);
    exact$Qu = new ModuleMethod(localnumbers, 7, Lit9, 4097);
    inexact$Qu = new ModuleMethod(localnumbers, 8, Lit10, 4097);
    zero$Qu = new ModuleMethod(localnumbers, 9, Lit11, 4097);
    positive$Qu = new ModuleMethod(localnumbers, 10, Lit12, 4097);
    negative$Qu = new ModuleMethod(localnumbers, 11, Lit13, 4097);
    max = new ModuleMethod(localnumbers, 12, Lit14, 61440);
    min = new ModuleMethod(localnumbers, 13, Lit15, 61440);
    abs = new ModuleMethod(localnumbers, 14, Lit16, 4097);
    div$Mnand$Mnmod = new ModuleMethod(localnumbers, 15, Lit17, 8194);
    div0$Mnand$Mnmod0 = new ModuleMethod(localnumbers, 16, Lit18, 8194);
    gcd = new ModuleMethod(localnumbers, 17, Lit19, 61440);
    lcm = new ModuleMethod(localnumbers, 18, Lit20, 61440);
    numerator = new ModuleMethod(localnumbers, 19, Lit21, 4097);
    denominator = new ModuleMethod(localnumbers, 20, Lit22, 4097);
    floor = new ModuleMethod(localnumbers, 21, Lit23, 4097);
    ceiling = new ModuleMethod(localnumbers, 22, Lit24, 4097);
    truncate = new ModuleMethod(localnumbers, 23, Lit25, 4097);
    round = new ModuleMethod(localnumbers, 24, Lit26, 4097);
    rationalize = new ModuleMethod(localnumbers, 25, Lit27, 8194);
    exp = new ModuleMethod(localnumbers, 26, Lit28, 4097);
    log = new ModuleMethod(localnumbers, 27, Lit29, 4097);
    sin = new ModuleMethod(localnumbers, 28, Lit30, 4097);
    cos = new ModuleMethod(localnumbers, 29, Lit31, 4097);
    tan = new ModuleMethod(localnumbers, 30, Lit32, 4097);
    asin = new ModuleMethod(localnumbers, 31, Lit33, 4097);
    acos = new ModuleMethod(localnumbers, 32, Lit34, 4097);
    ModuleMethod localModuleMethod = new ModuleMethod(localnumbers, 33, null, 8194);
    localModuleMethod.setProperty("source-location", "numbers.scm:146");
    lambda$Fn1 = localModuleMethod;
    localModuleMethod = new ModuleMethod(localnumbers, 34, null, 4097);
    localModuleMethod.setProperty("source-location", "numbers.scm:148");
    lambda$Fn2 = localModuleMethod;
    localModuleMethod = new ModuleMethod(localnumbers, 35, null, 4097);
    localModuleMethod.setProperty("source-location", "numbers.scm:152");
    lambda$Fn3 = localModuleMethod;
    localModuleMethod = new ModuleMethod(localnumbers, 36, null, 4097);
    localModuleMethod.setProperty("source-location", "numbers.scm:156");
    lambda$Fn4 = localModuleMethod;
    make$Mnrectangular = new ModuleMethod(localnumbers, 37, Lit35, 8194);
    make$Mnpolar = new ModuleMethod(localnumbers, 38, Lit36, 8194);
    real$Mnpart = new ModuleMethod(localnumbers, 39, Lit37, 4097);
    imag$Mnpart = new ModuleMethod(localnumbers, 40, Lit38, 4097);
    magnitude = new ModuleMethod(localnumbers, 41, Lit39, 4097);
    angle = new ModuleMethod(localnumbers, 42, Lit40, 4097);
    inexact = new ModuleMethod(localnumbers, 43, Lit41, 4097);
    exact = new ModuleMethod(localnumbers, 44, Lit42, 4097);
    exact$Mn$Grinexact = new ModuleMethod(localnumbers, 45, Lit43, 4097);
    inexact$Mn$Grexact = new ModuleMethod(localnumbers, 46, Lit44, 4097);
    logop = new ModuleMethod(localnumbers, 47, Lit45, 12291);
    bitwise$Mnbit$Mnset$Qu = new ModuleMethod(localnumbers, 48, Lit46, 8194);
    bitwise$Mncopy$Mnbit = new ModuleMethod(localnumbers, 49, Lit47, 12291);
    bitwise$Mncopy$Mnbit$Mnfield = new ModuleMethod(localnumbers, 50, Lit48, 16388);
    bitwise$Mnbit$Mnfield = new ModuleMethod(localnumbers, 51, Lit49, 12291);
    bitwise$Mnif = new ModuleMethod(localnumbers, 52, Lit50, 12291);
    logtest = new ModuleMethod(localnumbers, 53, Lit51, 8194);
    logcount = new ModuleMethod(localnumbers, 54, Lit52, 4097);
    bitwise$Mnbit$Mncount = new ModuleMethod(localnumbers, 55, Lit53, 4097);
    bitwise$Mnlength = new ModuleMethod(localnumbers, 56, Lit54, 4097);
    bitwise$Mnfirst$Mnbit$Mnset = new ModuleMethod(localnumbers, 57, Lit55, 4097);
    bitwise$Mnrotate$Mnbit$Mnfield = new ModuleMethod(localnumbers, 58, Lit56, 16388);
    bitwise$Mnreverse$Mnbit$Mnfield = new ModuleMethod(localnumbers, 59, Lit57, 12291);
    number$Mn$Grstring = new ModuleMethod(localnumbers, 60, Lit58, 8193);
    string$Mn$Grnumber = new ModuleMethod(localnumbers, 62, Lit59, 8193);
    quantity$Mn$Grnumber = new ModuleMethod(localnumbers, 64, Lit60, 4097);
    quantity$Mn$Grunit = new ModuleMethod(localnumbers, 65, Lit61, 4097);
    make$Mnquantity = new ModuleMethod(localnumbers, 66, Lit62, 8194);
    duration = new ModuleMethod(localnumbers, 67, Lit63, 4097);
    $instance.run();
  }
  
  public numbers()
  {
    ModuleInfo.register(this);
  }
  
  public static Number abs(Number paramNumber)
  {
    Object localObject;
    if ((paramNumber instanceof Numeric)) {
      localObject = ((Numeric)paramNumber).abs();
    }
    do
    {
      return (Number)localObject;
      localObject = paramNumber;
    } while (Scheme.numGEq.apply2(paramNumber, Lit0) != Boolean.FALSE);
    return (Number)AddOp.$Mn.apply1(paramNumber);
  }
  
  public static double acos(double paramDouble)
  {
    return Math.acos(paramDouble);
  }
  
  public static RealNum angle(Complex paramComplex)
  {
    return paramComplex.angle();
  }
  
  public static double asin(double paramDouble)
  {
    return Math.asin(paramDouble);
  }
  
  public static int bitwiseBitCount(IntNum paramIntNum)
  {
    if (IntNum.compare(paramIntNum, 0L) >= 0) {
      return BitOps.bitCount(paramIntNum);
    }
    return -1 - BitOps.bitCount(BitOps.not(paramIntNum));
  }
  
  public static IntNum bitwiseBitField(IntNum paramIntNum, int paramInt1, int paramInt2)
  {
    return BitOps.extract(paramIntNum, paramInt1, paramInt2);
  }
  
  public static IntNum bitwiseCopyBit(IntNum paramIntNum, int paramInt1, int paramInt2)
  {
    return BitOps.setBitValue(paramIntNum, paramInt1, paramInt2);
  }
  
  public static IntNum bitwiseCopyBitField(IntNum paramIntNum1, int paramInt1, int paramInt2, IntNum paramIntNum2)
  {
    int i = IntNum.shift(-1, paramInt1);
    IntNum localIntNum = BitOps.not(IntNum.make(IntNum.shift(-1, paramInt2)));
    return bitwiseIf(BitOps.and(IntNum.make(i), localIntNum), IntNum.shift(paramIntNum2, paramInt1), paramIntNum1);
  }
  
  public static int bitwiseFirstBitSet(IntNum paramIntNum)
  {
    return BitOps.lowestBitSet(paramIntNum);
  }
  
  public static IntNum bitwiseIf(IntNum paramIntNum1, IntNum paramIntNum2, IntNum paramIntNum3)
  {
    return BitOps.ior(BitOps.and(paramIntNum1, paramIntNum2), BitOps.and(BitOps.not(paramIntNum1), paramIntNum3));
  }
  
  public static int bitwiseLength(IntNum paramIntNum)
  {
    return paramIntNum.intLength();
  }
  
  public static IntNum bitwiseReverseBitField(IntNum paramIntNum, int paramInt1, int paramInt2)
  {
    return BitOps.reverseBits(paramIntNum, paramInt1, paramInt2);
  }
  
  public static IntNum bitwiseRotateBitField(IntNum paramIntNum, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt2 - paramInt1;
    IntNum localIntNum = paramIntNum;
    if (i > 0)
    {
      paramInt3 %= i;
      if (paramInt3 >= 0) {
        break label64;
      }
      paramInt3 += i;
    }
    label64:
    for (;;)
    {
      localIntNum = bitwiseBitField(paramIntNum, paramInt1, paramInt2);
      localIntNum = bitwiseCopyBitField(paramIntNum, paramInt1, paramInt2, BitOps.ior(IntNum.shift(localIntNum, paramInt3), IntNum.shift(localIntNum, paramInt3 - i)));
      return localIntNum;
    }
  }
  
  public static RealNum ceiling(RealNum paramRealNum)
  {
    return paramRealNum.toInt(Numeric.CEILING);
  }
  
  public static double cos(double paramDouble)
  {
    return Math.cos(paramDouble);
  }
  
  public static IntNum denominator(RatNum paramRatNum)
  {
    return paramRatNum.denominator();
  }
  
  /* Error */
  public static Object div0AndMod0(RealNum paramRealNum1, RealNum paramRealNum2)
  {
    // Byte code:
    //   0: getstatic 691	gnu/kawa/functions/DivideOp:div0	Lgnu/kawa/functions/DivideOp;
    //   3: aload_0
    //   4: aload_1
    //   5: invokevirtual 568	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   8: astore_3
    //   9: aload_3
    //   10: invokestatic 697	gnu/kawa/lispexpr/LangObjType:coerceRealNum	(Ljava/lang/Object;)Lgnu/math/RealNum;
    //   13: astore_2
    //   14: getstatic 580	gnu/kawa/functions/AddOp:$Mn	Lgnu/kawa/functions/AddOp;
    //   17: aload_0
    //   18: getstatic 703	gnu/kawa/functions/MultiplyOp:$St	Lgnu/kawa/functions/MultiplyOp;
    //   21: aload_2
    //   22: aload_1
    //   23: invokevirtual 568	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   26: invokevirtual 568	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   29: astore_0
    //   30: aload_0
    //   31: invokestatic 697	gnu/kawa/lispexpr/LangObjType:coerceRealNum	(Ljava/lang/Object;)Lgnu/math/RealNum;
    //   34: astore_1
    //   35: iconst_2
    //   36: anewarray 705	java/lang/Object
    //   39: dup
    //   40: iconst_0
    //   41: aload_2
    //   42: aastore
    //   43: dup
    //   44: iconst_1
    //   45: aload_1
    //   46: aastore
    //   47: invokestatic 711	kawa/lib/misc:values	([Ljava/lang/Object;)Ljava/lang/Object;
    //   50: areturn
    //   51: astore_0
    //   52: new 713	gnu/mapping/WrongType
    //   55: dup
    //   56: aload_0
    //   57: ldc_w 715
    //   60: bipush -2
    //   62: aload_3
    //   63: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   66: athrow
    //   67: astore_1
    //   68: new 713	gnu/mapping/WrongType
    //   71: dup
    //   72: aload_1
    //   73: ldc_w 720
    //   76: bipush -2
    //   78: aload_0
    //   79: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   82: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	paramRealNum1	RealNum
    //   0	83	1	paramRealNum2	RealNum
    //   13	29	2	localRealNum	RealNum
    //   8	55	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   9	14	51	java/lang/ClassCastException
    //   30	35	67	java/lang/ClassCastException
  }
  
  /* Error */
  public static Object divAndMod(RealNum paramRealNum1, RealNum paramRealNum2)
  {
    // Byte code:
    //   0: getstatic 724	gnu/kawa/functions/DivideOp:div	Lgnu/kawa/functions/DivideOp;
    //   3: aload_0
    //   4: aload_1
    //   5: invokevirtual 568	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   8: astore_3
    //   9: aload_3
    //   10: invokestatic 697	gnu/kawa/lispexpr/LangObjType:coerceRealNum	(Ljava/lang/Object;)Lgnu/math/RealNum;
    //   13: astore_2
    //   14: getstatic 580	gnu/kawa/functions/AddOp:$Mn	Lgnu/kawa/functions/AddOp;
    //   17: aload_0
    //   18: getstatic 703	gnu/kawa/functions/MultiplyOp:$St	Lgnu/kawa/functions/MultiplyOp;
    //   21: aload_2
    //   22: aload_1
    //   23: invokevirtual 568	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   26: invokevirtual 568	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   29: astore_0
    //   30: aload_0
    //   31: invokestatic 697	gnu/kawa/lispexpr/LangObjType:coerceRealNum	(Ljava/lang/Object;)Lgnu/math/RealNum;
    //   34: astore_1
    //   35: iconst_2
    //   36: anewarray 705	java/lang/Object
    //   39: dup
    //   40: iconst_0
    //   41: aload_2
    //   42: aastore
    //   43: dup
    //   44: iconst_1
    //   45: aload_1
    //   46: aastore
    //   47: invokestatic 711	kawa/lib/misc:values	([Ljava/lang/Object;)Ljava/lang/Object;
    //   50: areturn
    //   51: astore_0
    //   52: new 713	gnu/mapping/WrongType
    //   55: dup
    //   56: aload_0
    //   57: ldc_w 715
    //   60: bipush -2
    //   62: aload_3
    //   63: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   66: athrow
    //   67: astore_1
    //   68: new 713	gnu/mapping/WrongType
    //   71: dup
    //   72: aload_1
    //   73: ldc_w 720
    //   76: bipush -2
    //   78: aload_0
    //   79: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   82: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	paramRealNum1	RealNum
    //   0	83	1	paramRealNum2	RealNum
    //   13	29	2	localRealNum	RealNum
    //   8	55	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   9	14	51	java/lang/ClassCastException
    //   30	35	67	java/lang/ClassCastException
  }
  
  public static Duration duration(Object paramObject)
  {
    if (paramObject == null) {}
    for (paramObject = null;; paramObject = paramObject.toString()) {
      return Duration.parseDuration((String)paramObject);
    }
  }
  
  public static Number exact(Number paramNumber)
  {
    return Arithmetic.toExact(paramNumber);
  }
  
  public static Number exact$To$Inexact(Number paramNumber)
  {
    return Arithmetic.toInexact(paramNumber);
  }
  
  public static Complex exp(Complex paramComplex)
  {
    return paramComplex.exp();
  }
  
  public static RealNum floor(RealNum paramRealNum)
  {
    return paramRealNum.toInt(Numeric.FLOOR);
  }
  
  public static IntNum gcd(IntNum... paramVarArgs)
  {
    int j = paramVarArgs.length;
    Object localObject;
    if (j == 0)
    {
      localObject = Lit0;
      return (IntNum)localObject;
    }
    IntNum localIntNum = paramVarArgs[0];
    int i = 1;
    for (;;)
    {
      localObject = localIntNum;
      if (i >= j) {
        break;
      }
      localIntNum = IntNum.gcd(localIntNum, paramVarArgs[i]);
      i += 1;
    }
  }
  
  public static RealNum imagPart(Complex paramComplex)
  {
    return paramComplex.im();
  }
  
  public static Number inexact(Number paramNumber)
  {
    return Arithmetic.toInexact(paramNumber);
  }
  
  public static Number inexact$To$Exact(Number paramNumber)
  {
    return Arithmetic.toExact(paramNumber);
  }
  
  public static boolean isBitwiseBitSet(IntNum paramIntNum, int paramInt)
  {
    return BitOps.bitValue(paramIntNum, paramInt);
  }
  
  public static boolean isComplex(Object paramObject)
  {
    return paramObject instanceof Complex;
  }
  
  public static boolean isExact(Object paramObject)
  {
    boolean bool2 = paramObject instanceof Number;
    boolean bool1 = bool2;
    if (bool2) {
      bool1 = Arithmetic.isExact((Number)paramObject);
    }
    return bool1;
  }
  
  public static boolean isInexact(Object paramObject)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  /* Error */
  public static boolean isInteger(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: instanceof 372
    //   4: istore_2
    //   5: iload_2
    //   6: ifeq +5 -> 11
    //   9: iload_2
    //   10: ireturn
    //   11: aload_0
    //   12: instanceof 781
    //   15: istore_2
    //   16: iload_2
    //   17: istore_3
    //   18: iload_2
    //   19: ifeq +25 -> 44
    //   22: aload_0
    //   23: checkcast 781	gnu/math/DFloNum
    //   26: astore_1
    //   27: aload_1
    //   28: invokevirtual 785	gnu/math/DFloNum:doubleValue	()D
    //   31: dconst_1
    //   32: invokestatic 789	java/lang/Math:IEEEremainder	(DD)D
    //   35: dconst_0
    //   36: dcmpg
    //   37: ifne +62 -> 99
    //   40: iconst_1
    //   41: istore_2
    //   42: iload_2
    //   43: istore_3
    //   44: iload_3
    //   45: istore_2
    //   46: iload_3
    //   47: ifne -38 -> 9
    //   50: aload_0
    //   51: instanceof 586
    //   54: istore_3
    //   55: iload_3
    //   56: istore_2
    //   57: iload_3
    //   58: ifeq -49 -> 9
    //   61: aload_0
    //   62: instanceof 791
    //   65: istore_3
    //   66: iload_3
    //   67: istore_2
    //   68: iload_3
    //   69: ifne -60 -> 9
    //   72: aload_0
    //   73: instanceof 793
    //   76: istore_3
    //   77: iload_3
    //   78: istore_2
    //   79: iload_3
    //   80: ifne -71 -> 9
    //   83: aload_0
    //   84: instanceof 795
    //   87: istore_3
    //   88: iload_3
    //   89: istore_2
    //   90: iload_3
    //   91: ifne -82 -> 9
    //   94: aload_0
    //   95: instanceof 797
    //   98: ireturn
    //   99: iconst_0
    //   100: istore_2
    //   101: goto -59 -> 42
    //   104: astore_1
    //   105: new 713	gnu/mapping/WrongType
    //   108: dup
    //   109: aload_1
    //   110: ldc_w 799
    //   113: iconst_1
    //   114: aload_0
    //   115: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   118: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	119	0	paramObject	Object
    //   26	2	1	localDFloNum	gnu.math.DFloNum
    //   104	6	1	localClassCastException	ClassCastException
    //   4	97	2	bool1	boolean
    //   17	74	3	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   22	27	104	java/lang/ClassCastException
  }
  
  public static boolean isNegative(RealNum paramRealNum)
  {
    return paramRealNum.isNegative();
  }
  
  public static boolean isNumber(Object paramObject)
  {
    return paramObject instanceof Number;
  }
  
  public static boolean isPositive(RealNum paramRealNum)
  {
    return paramRealNum.sign() > 0;
  }
  
  public static boolean isQuantity(Object paramObject)
  {
    return paramObject instanceof Quantity;
  }
  
  public static boolean isRational(Object paramObject)
  {
    return RatNum.asRatNumOrNull(paramObject) != null;
  }
  
  public static boolean isReal(Object paramObject)
  {
    return RealNum.asRealNumOrNull(paramObject) != null;
  }
  
  public static boolean isZero(Number paramNumber)
  {
    boolean bool = true;
    if ((paramNumber instanceof Numeric)) {
      bool = ((Numeric)paramNumber).isZero();
    }
    do
    {
      do
      {
        do
        {
          return bool;
          if (!(paramNumber instanceof BigInteger)) {
            break;
          }
        } while (Scheme.numEqu.apply2(Lit0, GetNamedPart.getNamedPart.apply2((BigInteger)paramNumber, Lit1)) != Boolean.FALSE);
        return false;
        if (!(paramNumber instanceof BigDecimal)) {
          break;
        }
      } while (Scheme.numEqu.apply2(Lit0, GetNamedPart.getNamedPart.apply2((BigDecimal)paramNumber, Lit1)) != Boolean.FALSE);
      return false;
    } while (paramNumber.doubleValue() == 0.0D);
    return false;
  }
  
  static double lambda1(double paramDouble1, double paramDouble2)
  {
    return Math.atan2(paramDouble1, paramDouble2);
  }
  
  static double lambda2(double paramDouble)
  {
    return Math.atan(paramDouble);
  }
  
  static Quantity lambda3(Quantity paramQuantity)
  {
    return Quantity.make(paramQuantity.number().sqrt(), paramQuantity.unit().sqrt());
  }
  
  static double lambda4(double paramDouble)
  {
    return Math.sqrt(paramDouble);
  }
  
  public static IntNum lcm(IntNum... paramVarArgs)
  {
    int j = paramVarArgs.length;
    Object localObject;
    if (j == 0)
    {
      localObject = Lit2;
      return (IntNum)localObject;
    }
    IntNum localIntNum = IntNum.abs(paramVarArgs[0]);
    int i = 1;
    for (;;)
    {
      localObject = localIntNum;
      if (i >= j) {
        break;
      }
      localIntNum = IntNum.lcm(localIntNum, paramVarArgs[i]);
      i += 1;
    }
  }
  
  public static Complex log(Complex paramComplex)
  {
    return paramComplex.log();
  }
  
  public static int logcount(IntNum paramIntNum)
  {
    if (IntNum.compare(paramIntNum, 0L) >= 0) {}
    for (;;)
    {
      return BitOps.bitCount(paramIntNum);
      paramIntNum = BitOps.not(paramIntNum);
    }
  }
  
  public static IntNum logop(int paramInt, IntNum paramIntNum1, IntNum paramIntNum2)
  {
    return BitOps.bitOp(paramInt, paramIntNum1, paramIntNum2);
  }
  
  public static boolean logtest(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    return BitOps.test(paramIntNum1, paramIntNum2);
  }
  
  public static Number magnitude(Number paramNumber)
  {
    return abs(paramNumber);
  }
  
  public static DComplex makePolar(double paramDouble1, double paramDouble2)
  {
    return Complex.polar(paramDouble1, paramDouble2);
  }
  
  /* Error */
  public static Quantity makeQuantity(Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_1
    //   1: instanceof 856
    //   4: ifeq +44 -> 48
    //   7: aload_1
    //   8: checkcast 856	gnu/math/Unit
    //   11: astore_2
    //   12: aload_2
    //   13: ifnonnull +57 -> 70
    //   16: new 889	java/lang/IllegalArgumentException
    //   19: dup
    //   20: iconst_0
    //   21: iconst_2
    //   22: anewarray 705	java/lang/Object
    //   25: dup
    //   26: iconst_0
    //   27: ldc_w 891
    //   30: aastore
    //   31: dup
    //   32: iconst_1
    //   33: aload_1
    //   34: aastore
    //   35: invokestatic 897	gnu/kawa/functions/Format:formatToString	(I[Ljava/lang/Object;)Ljava/lang/String;
    //   38: invokevirtual 900	java/lang/String:toString	()Ljava/lang/String;
    //   41: invokespecial 901	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   44: checkcast 903	java/lang/Throwable
    //   47: athrow
    //   48: aload_1
    //   49: ifnonnull +13 -> 62
    //   52: aconst_null
    //   53: astore_2
    //   54: aload_2
    //   55: invokestatic 907	gnu/math/Unit:lookup	(Ljava/lang/String;)Lgnu/math/NamedUnit;
    //   58: astore_2
    //   59: goto -47 -> 12
    //   62: aload_1
    //   63: invokevirtual 735	java/lang/Object:toString	()Ljava/lang/String;
    //   66: astore_2
    //   67: goto -13 -> 54
    //   70: aload_0
    //   71: checkcast 594	gnu/math/Complex
    //   74: astore_1
    //   75: aload_1
    //   76: aload_2
    //   77: invokestatic 861	gnu/math/Quantity:make	(Lgnu/math/Complex;Lgnu/math/Unit;)Lgnu/math/Quantity;
    //   80: areturn
    //   81: astore_0
    //   82: new 713	gnu/mapping/WrongType
    //   85: dup
    //   86: aload_0
    //   87: ldc_w 909
    //   90: bipush -2
    //   92: aload_1
    //   93: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   96: athrow
    //   97: astore_1
    //   98: new 713	gnu/mapping/WrongType
    //   101: dup
    //   102: aload_1
    //   103: ldc_w 911
    //   106: iconst_1
    //   107: aload_0
    //   108: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   111: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	paramObject1	Object
    //   0	112	1	paramObject2	Object
    //   11	66	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   7	12	81	java/lang/ClassCastException
    //   70	75	97	java/lang/ClassCastException
  }
  
  public static Complex makeRectangular(RealNum paramRealNum1, RealNum paramRealNum2)
  {
    return Complex.make(paramRealNum1, paramRealNum2);
  }
  
  public static Object max(Object... paramVarArgs)
  {
    int j = paramVarArgs.length;
    Object localObject = paramVarArgs[0];
    RealNum localRealNum1;
    try
    {
      localRealNum1 = LangObjType.coerceRealNum(localObject);
      i = 1;
    }
    catch (ClassCastException paramVarArgs)
    {
      try
      {
        int i;
        RealNum localRealNum2 = LangObjType.coerceRealNum(localObject);
        localRealNum1 = localRealNum1.max(localRealNum2);
        i += 1;
      }
      catch (ClassCastException paramVarArgs)
      {
        throw new WrongType(paramVarArgs, "gnu.math.RealNum.max(real)", 2, localObject);
      }
      paramVarArgs = paramVarArgs;
      throw new WrongType(paramVarArgs, "result", -2, localObject);
    }
    if (i < j) {
      localObject = paramVarArgs[i];
    }
    return localRealNum1;
  }
  
  public static Object min(Object... paramVarArgs)
  {
    int j = paramVarArgs.length;
    Object localObject = paramVarArgs[0];
    RealNum localRealNum1;
    try
    {
      localRealNum1 = LangObjType.coerceRealNum(localObject);
      i = 0;
    }
    catch (ClassCastException paramVarArgs)
    {
      try
      {
        int i;
        RealNum localRealNum2 = LangObjType.coerceRealNum(localObject);
        localRealNum1 = localRealNum1.min(localRealNum2);
        i += 1;
      }
      catch (ClassCastException paramVarArgs)
      {
        throw new WrongType(paramVarArgs, "gnu.math.RealNum.min(real)", 2, localObject);
      }
      paramVarArgs = paramVarArgs;
      throw new WrongType(paramVarArgs, "result", -2, localObject);
    }
    if (i < j) {
      localObject = paramVarArgs[i];
    }
    return localRealNum1;
  }
  
  public static CharSequence number$To$String(Number paramNumber)
  {
    return number$To$String(paramNumber, 10);
  }
  
  public static CharSequence number$To$String(Number paramNumber, int paramInt)
  {
    return new FString(Arithmetic.toString(paramNumber, paramInt));
  }
  
  public static IntNum numerator(RatNum paramRatNum)
  {
    return paramRatNum.numerator();
  }
  
  public static Complex quantity$To$Number(Quantity paramQuantity)
  {
    paramQuantity.unit();
    if (paramQuantity.doubleValue() == 1.0D) {
      return paramQuantity.number();
    }
    return Complex.make(paramQuantity.reValue(), paramQuantity.imValue());
  }
  
  public static Unit quantity$To$Unit(Quantity paramQuantity)
  {
    return paramQuantity.unit();
  }
  
  public static RealNum rationalize(RealNum paramRealNum1, RealNum paramRealNum2)
  {
    return RatNum.rationalize(LangObjType.coerceRealNum(paramRealNum1.sub(paramRealNum2)), LangObjType.coerceRealNum(paramRealNum1.add(paramRealNum2)));
  }
  
  public static RealNum realPart(Complex paramComplex)
  {
    return paramComplex.re();
  }
  
  public static RealNum round(RealNum paramRealNum)
  {
    return paramRealNum.toInt(Numeric.ROUND);
  }
  
  public static double sin(double paramDouble)
  {
    return Math.sin(paramDouble);
  }
  
  public static Object string$To$Number(CharSequence paramCharSequence)
  {
    return string$To$Number(paramCharSequence, 10);
  }
  
  public static Object string$To$Number(CharSequence paramCharSequence, int paramInt)
  {
    paramCharSequence = LispReader.parseNumber(paramCharSequence, paramInt);
    if ((paramCharSequence instanceof Numeric)) {
      return paramCharSequence;
    }
    return Boolean.FALSE;
  }
  
  public static double tan(double paramDouble)
  {
    return Math.tan(paramDouble);
  }
  
  public static RealNum truncate(RealNum paramRealNum)
  {
    return paramRealNum.toInt(Numeric.TRUNCATE);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 12: 
    case 13: 
    case 15: 
    case 16: 
    case 17: 
    case 18: 
    case 25: 
    case 33: 
    case 37: 
    case 38: 
    case 47: 
    case 48: 
    case 49: 
    case 50: 
    case 51: 
    case 52: 
    case 53: 
    case 58: 
    case 59: 
    case 61: 
    case 63: 
    case 66: 
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 1: 
      if (isNumber(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 2: 
      if (isQuantity(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 3: 
      if (isComplex(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 4: 
      if (isReal(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 5: 
      if (isRational(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 6: 
      if (isInteger(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 7: 
      if (isExact(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 8: 
      if (isInexact(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    for (;;)
    {
      try
      {
        paramModuleMethod = (Number)paramObject;
        if (isZero(paramModuleMethod)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramModuleMethod)
      {
        double d;
        throw new WrongType(paramModuleMethod, "zero?", 1, paramObject);
      }
      try
      {
        paramModuleMethod = LangObjType.coerceRealNum(paramObject);
        if (isPositive(paramModuleMethod)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "positive?", 1, paramObject);
      }
      try
      {
        paramModuleMethod = LangObjType.coerceRealNum(paramObject);
        if (isNegative(paramModuleMethod)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "negative?", 1, paramObject);
      }
    }
    try
    {
      paramModuleMethod = (Number)paramObject;
      return abs(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "abs", 1, paramObject);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceRatNum(paramObject);
      return numerator(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "numerator", 1, paramObject);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceRatNum(paramObject);
      return denominator(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "denominator", 1, paramObject);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceRealNum(paramObject);
      return floor(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "floor", 1, paramObject);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceRealNum(paramObject);
      return ceiling(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "ceiling", 1, paramObject);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceRealNum(paramObject);
      return truncate(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "truncate", 1, paramObject);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceRealNum(paramObject);
      return round(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "round", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Complex)paramObject;
      return exp(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "exp", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Complex)paramObject;
      return log(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "log", 1, paramObject);
    }
    try
    {
      d = ((Number)paramObject).doubleValue();
      return Double.valueOf(sin(d));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "sin", 1, paramObject);
    }
    try
    {
      d = ((Number)paramObject).doubleValue();
      return Double.valueOf(cos(d));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "cos", 1, paramObject);
    }
    try
    {
      d = ((Number)paramObject).doubleValue();
      return Double.valueOf(tan(d));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "tan", 1, paramObject);
    }
    try
    {
      d = ((Number)paramObject).doubleValue();
      return Double.valueOf(asin(d));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "asin", 1, paramObject);
    }
    try
    {
      d = ((Number)paramObject).doubleValue();
      return Double.valueOf(acos(d));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "acos", 1, paramObject);
    }
    try
    {
      d = ((Number)paramObject).doubleValue();
      return Double.valueOf(lambda2(d));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "lambda", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Quantity)paramObject;
      return lambda3(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "lambda", 1, paramObject);
    }
    try
    {
      d = ((Number)paramObject).doubleValue();
      return Double.valueOf(lambda4(d));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "lambda", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Complex)paramObject;
      return realPart(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "real-part", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Complex)paramObject;
      return imagPart(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "imag-part", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Number)paramObject;
      return magnitude(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "magnitude", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Complex)paramObject;
      return angle(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "angle", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Number)paramObject;
      return inexact(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "inexact", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Number)paramObject;
      return exact(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "exact", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Number)paramObject;
      return exact$To$Inexact(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "exact->inexact", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Number)paramObject;
      return inexact$To$Exact(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "inexact->exact", 1, paramObject);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject);
      return Integer.valueOf(logcount(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "logcount", 1, paramObject);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject);
      return Integer.valueOf(bitwiseBitCount(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "bitwise-bit-count", 1, paramObject);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject);
      return Integer.valueOf(bitwiseLength(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "bitwise-length", 1, paramObject);
    }
    try
    {
      paramModuleMethod = LangObjType.coerceIntNum(paramObject);
      return Integer.valueOf(bitwiseFirstBitSet(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "bitwise-first-bit-set", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Number)paramObject;
      return number$To$String(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "number->string", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (CharSequence)paramObject;
      return string$To$Number(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "string->number", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Quantity)paramObject;
      return quantity$To$Number(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "quantity->number", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Quantity)paramObject;
      return quantity$To$Unit(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "quantity->unit", 1, paramObject);
    }
    return duration(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    for (;;)
    {
      try
      {
        paramModuleMethod = LangObjType.coerceRealNum(paramObject1);
      }
      catch (ClassCastException paramModuleMethod)
      {
        double d1;
        double d2;
        int i;
        throw new WrongType(paramModuleMethod, "div-and-mod", 1, paramObject1);
      }
      try
      {
        paramObject1 = LangObjType.coerceRealNum(paramObject2);
        return divAndMod(paramModuleMethod, (RealNum)paramObject1);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "div-and-mod", 2, paramObject2);
      }
      try
      {
        paramModuleMethod = LangObjType.coerceRealNum(paramObject1);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "div0-and-mod0", 1, paramObject1);
      }
      try
      {
        paramObject1 = LangObjType.coerceRealNum(paramObject2);
        return div0AndMod0(paramModuleMethod, (RealNum)paramObject1);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "div0-and-mod0", 2, paramObject2);
      }
      try
      {
        paramModuleMethod = LangObjType.coerceRealNum(paramObject1);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "rationalize", 1, paramObject1);
      }
      try
      {
        paramObject1 = LangObjType.coerceRealNum(paramObject2);
        return rationalize(paramModuleMethod, (RealNum)paramObject1);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "rationalize", 2, paramObject2);
      }
      try
      {
        d1 = ((Number)paramObject1).doubleValue();
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "lambda", 1, paramObject1);
      }
      try
      {
        d2 = ((Number)paramObject2).doubleValue();
        return Double.valueOf(lambda1(d1, d2));
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "lambda", 2, paramObject2);
      }
      try
      {
        paramModuleMethod = LangObjType.coerceRealNum(paramObject1);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "make-rectangular", 1, paramObject1);
      }
      try
      {
        paramObject1 = LangObjType.coerceRealNum(paramObject2);
        return makeRectangular(paramModuleMethod, (RealNum)paramObject1);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "make-rectangular", 2, paramObject2);
      }
      try
      {
        d1 = ((Number)paramObject1).doubleValue();
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "make-polar", 1, paramObject1);
      }
      try
      {
        d2 = ((Number)paramObject2).doubleValue();
        return makePolar(d1, d2);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "make-polar", 2, paramObject2);
      }
      try
      {
        paramModuleMethod = LangObjType.coerceIntNum(paramObject1);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "bitwise-bit-set?", 1, paramObject1);
      }
      try
      {
        i = ((Number)paramObject2).intValue();
        if (isBitwiseBitSet(paramModuleMethod, i)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "bitwise-bit-set?", 2, paramObject2);
      }
      try
      {
        paramModuleMethod = LangObjType.coerceIntNum(paramObject1);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "logtest", 1, paramObject1);
      }
      try
      {
        paramObject1 = LangObjType.coerceIntNum(paramObject2);
        if (logtest(paramModuleMethod, (IntNum)paramObject1)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "logtest", 2, paramObject2);
      }
    }
    try
    {
      paramModuleMethod = (Number)paramObject1;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "number->string", 1, paramObject1);
    }
    try
    {
      i = ((Number)paramObject2).intValue();
      return number$To$String(paramModuleMethod, i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "number->string", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = (CharSequence)paramObject1;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "string->number", 1, paramObject1);
    }
    try
    {
      i = ((Number)paramObject2).intValue();
      return string$To$Number(paramModuleMethod, i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "string->number", 2, paramObject2);
    }
    return makeQuantity(paramObject1, paramObject2);
  }
  
  /* Error */
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 992	gnu/expr/ModuleMethod:selector	I
    //   4: lookupswitch	default:+52->56, 47:+62->66, 49:+90->94, 51:+123->127, 52:+156->160, 59:+179->183
    //   56: aload_0
    //   57: aload_1
    //   58: aload_2
    //   59: aload_3
    //   60: aload 4
    //   62: invokespecial 1127	gnu/expr/ModuleBody:apply3	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   65: areturn
    //   66: aload_2
    //   67: checkcast 586	java/lang/Number
    //   70: invokevirtual 1117	java/lang/Number:intValue	()I
    //   73: istore 5
    //   75: aload_3
    //   76: invokestatic 1075	gnu/kawa/lispexpr/LangObjType:coerceIntNum	(Ljava/lang/Object;)Lgnu/math/IntNum;
    //   79: astore_1
    //   80: aload 4
    //   82: invokestatic 1075	gnu/kawa/lispexpr/LangObjType:coerceIntNum	(Ljava/lang/Object;)Lgnu/math/IntNum;
    //   85: astore_2
    //   86: iload 5
    //   88: aload_1
    //   89: aload_2
    //   90: invokestatic 1129	kawa/lib/numbers:logop	(ILgnu/math/IntNum;Lgnu/math/IntNum;)Lgnu/math/IntNum;
    //   93: areturn
    //   94: aload_2
    //   95: invokestatic 1075	gnu/kawa/lispexpr/LangObjType:coerceIntNum	(Ljava/lang/Object;)Lgnu/math/IntNum;
    //   98: astore_1
    //   99: aload_3
    //   100: checkcast 586	java/lang/Number
    //   103: invokevirtual 1117	java/lang/Number:intValue	()I
    //   106: istore 5
    //   108: aload 4
    //   110: checkcast 586	java/lang/Number
    //   113: invokevirtual 1117	java/lang/Number:intValue	()I
    //   116: istore 6
    //   118: aload_1
    //   119: iload 5
    //   121: iload 6
    //   123: invokestatic 1131	kawa/lib/numbers:bitwiseCopyBit	(Lgnu/math/IntNum;II)Lgnu/math/IntNum;
    //   126: areturn
    //   127: aload_2
    //   128: invokestatic 1075	gnu/kawa/lispexpr/LangObjType:coerceIntNum	(Ljava/lang/Object;)Lgnu/math/IntNum;
    //   131: astore_1
    //   132: aload_3
    //   133: checkcast 586	java/lang/Number
    //   136: invokevirtual 1117	java/lang/Number:intValue	()I
    //   139: istore 5
    //   141: aload 4
    //   143: checkcast 586	java/lang/Number
    //   146: invokevirtual 1117	java/lang/Number:intValue	()I
    //   149: istore 6
    //   151: aload_1
    //   152: iload 5
    //   154: iload 6
    //   156: invokestatic 660	kawa/lib/numbers:bitwiseBitField	(Lgnu/math/IntNum;II)Lgnu/math/IntNum;
    //   159: areturn
    //   160: aload_2
    //   161: invokestatic 1075	gnu/kawa/lispexpr/LangObjType:coerceIntNum	(Ljava/lang/Object;)Lgnu/math/IntNum;
    //   164: astore_1
    //   165: aload_3
    //   166: invokestatic 1075	gnu/kawa/lispexpr/LangObjType:coerceIntNum	(Ljava/lang/Object;)Lgnu/math/IntNum;
    //   169: astore_2
    //   170: aload 4
    //   172: invokestatic 1075	gnu/kawa/lispexpr/LangObjType:coerceIntNum	(Ljava/lang/Object;)Lgnu/math/IntNum;
    //   175: astore_3
    //   176: aload_1
    //   177: aload_2
    //   178: aload_3
    //   179: invokestatic 640	kawa/lib/numbers:bitwiseIf	(Lgnu/math/IntNum;Lgnu/math/IntNum;Lgnu/math/IntNum;)Lgnu/math/IntNum;
    //   182: areturn
    //   183: aload_2
    //   184: invokestatic 1075	gnu/kawa/lispexpr/LangObjType:coerceIntNum	(Ljava/lang/Object;)Lgnu/math/IntNum;
    //   187: astore_1
    //   188: aload_3
    //   189: checkcast 586	java/lang/Number
    //   192: invokevirtual 1117	java/lang/Number:intValue	()I
    //   195: istore 5
    //   197: aload 4
    //   199: checkcast 586	java/lang/Number
    //   202: invokevirtual 1117	java/lang/Number:intValue	()I
    //   205: istore 6
    //   207: aload_1
    //   208: iload 5
    //   210: iload 6
    //   212: invokestatic 1133	kawa/lib/numbers:bitwiseReverseBitField	(Lgnu/math/IntNum;II)Lgnu/math/IntNum;
    //   215: areturn
    //   216: astore_1
    //   217: new 713	gnu/mapping/WrongType
    //   220: dup
    //   221: aload_1
    //   222: ldc -33
    //   224: iconst_1
    //   225: aload_2
    //   226: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   229: athrow
    //   230: astore_1
    //   231: new 713	gnu/mapping/WrongType
    //   234: dup
    //   235: aload_1
    //   236: ldc -33
    //   238: iconst_2
    //   239: aload_3
    //   240: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   243: athrow
    //   244: astore_1
    //   245: new 713	gnu/mapping/WrongType
    //   248: dup
    //   249: aload_1
    //   250: ldc -33
    //   252: iconst_3
    //   253: aload 4
    //   255: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   258: athrow
    //   259: astore_1
    //   260: new 713	gnu/mapping/WrongType
    //   263: dup
    //   264: aload_1
    //   265: ldc -40
    //   267: iconst_1
    //   268: aload_2
    //   269: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   272: athrow
    //   273: astore_1
    //   274: new 713	gnu/mapping/WrongType
    //   277: dup
    //   278: aload_1
    //   279: ldc -40
    //   281: iconst_2
    //   282: aload_3
    //   283: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   286: athrow
    //   287: astore_1
    //   288: new 713	gnu/mapping/WrongType
    //   291: dup
    //   292: aload_1
    //   293: ldc -40
    //   295: iconst_3
    //   296: aload 4
    //   298: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   301: athrow
    //   302: astore_1
    //   303: new 713	gnu/mapping/WrongType
    //   306: dup
    //   307: aload_1
    //   308: ldc -48
    //   310: iconst_1
    //   311: aload_2
    //   312: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   315: athrow
    //   316: astore_1
    //   317: new 713	gnu/mapping/WrongType
    //   320: dup
    //   321: aload_1
    //   322: ldc -48
    //   324: iconst_2
    //   325: aload_3
    //   326: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   329: athrow
    //   330: astore_1
    //   331: new 713	gnu/mapping/WrongType
    //   334: dup
    //   335: aload_1
    //   336: ldc -48
    //   338: iconst_3
    //   339: aload 4
    //   341: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   344: athrow
    //   345: astore_1
    //   346: new 713	gnu/mapping/WrongType
    //   349: dup
    //   350: aload_1
    //   351: ldc -52
    //   353: iconst_1
    //   354: aload_2
    //   355: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   358: athrow
    //   359: astore_1
    //   360: new 713	gnu/mapping/WrongType
    //   363: dup
    //   364: aload_1
    //   365: ldc -52
    //   367: iconst_2
    //   368: aload_3
    //   369: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   372: athrow
    //   373: astore_1
    //   374: new 713	gnu/mapping/WrongType
    //   377: dup
    //   378: aload_1
    //   379: ldc -52
    //   381: iconst_3
    //   382: aload 4
    //   384: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   387: athrow
    //   388: astore_1
    //   389: new 713	gnu/mapping/WrongType
    //   392: dup
    //   393: aload_1
    //   394: ldc -78
    //   396: iconst_1
    //   397: aload_2
    //   398: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   401: athrow
    //   402: astore_1
    //   403: new 713	gnu/mapping/WrongType
    //   406: dup
    //   407: aload_1
    //   408: ldc -78
    //   410: iconst_2
    //   411: aload_3
    //   412: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   415: athrow
    //   416: astore_1
    //   417: new 713	gnu/mapping/WrongType
    //   420: dup
    //   421: aload_1
    //   422: ldc -78
    //   424: iconst_3
    //   425: aload 4
    //   427: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   430: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	431	0	this	numbers
    //   0	431	1	paramModuleMethod	ModuleMethod
    //   0	431	2	paramObject1	Object
    //   0	431	3	paramObject2	Object
    //   0	431	4	paramObject3	Object
    //   73	136	5	i	int
    //   116	95	6	j	int
    // Exception table:
    //   from	to	target	type
    //   66	75	216	java/lang/ClassCastException
    //   75	80	230	java/lang/ClassCastException
    //   80	86	244	java/lang/ClassCastException
    //   94	99	259	java/lang/ClassCastException
    //   99	108	273	java/lang/ClassCastException
    //   108	118	287	java/lang/ClassCastException
    //   127	132	302	java/lang/ClassCastException
    //   132	141	316	java/lang/ClassCastException
    //   141	151	330	java/lang/ClassCastException
    //   160	165	345	java/lang/ClassCastException
    //   165	170	359	java/lang/ClassCastException
    //   170	176	373	java/lang/ClassCastException
    //   183	188	388	java/lang/ClassCastException
    //   188	197	402	java/lang/ClassCastException
    //   197	207	416	java/lang/ClassCastException
  }
  
  /* Error */
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 992	gnu/expr/ModuleMethod:selector	I
    //   4: lookupswitch	default:+28->32, 50:+40->44, 58:+80->84
    //   32: aload_0
    //   33: aload_1
    //   34: aload_2
    //   35: aload_3
    //   36: aload 4
    //   38: aload 5
    //   40: invokespecial 1137	gnu/expr/ModuleBody:apply4	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   43: areturn
    //   44: aload_2
    //   45: invokestatic 1075	gnu/kawa/lispexpr/LangObjType:coerceIntNum	(Ljava/lang/Object;)Lgnu/math/IntNum;
    //   48: astore_1
    //   49: aload_3
    //   50: checkcast 586	java/lang/Number
    //   53: invokevirtual 1117	java/lang/Number:intValue	()I
    //   56: istore 6
    //   58: aload 4
    //   60: checkcast 586	java/lang/Number
    //   63: invokevirtual 1117	java/lang/Number:intValue	()I
    //   66: istore 7
    //   68: aload 5
    //   70: invokestatic 1075	gnu/kawa/lispexpr/LangObjType:coerceIntNum	(Ljava/lang/Object;)Lgnu/math/IntNum;
    //   73: astore_2
    //   74: aload_1
    //   75: iload 6
    //   77: iload 7
    //   79: aload_2
    //   80: invokestatic 662	kawa/lib/numbers:bitwiseCopyBitField	(Lgnu/math/IntNum;IILgnu/math/IntNum;)Lgnu/math/IntNum;
    //   83: areturn
    //   84: aload_2
    //   85: invokestatic 1075	gnu/kawa/lispexpr/LangObjType:coerceIntNum	(Ljava/lang/Object;)Lgnu/math/IntNum;
    //   88: astore_1
    //   89: aload_3
    //   90: checkcast 586	java/lang/Number
    //   93: invokevirtual 1117	java/lang/Number:intValue	()I
    //   96: istore 6
    //   98: aload 4
    //   100: checkcast 586	java/lang/Number
    //   103: invokevirtual 1117	java/lang/Number:intValue	()I
    //   106: istore 7
    //   108: aload 5
    //   110: checkcast 586	java/lang/Number
    //   113: invokevirtual 1117	java/lang/Number:intValue	()I
    //   116: istore 8
    //   118: aload_1
    //   119: iload 6
    //   121: iload 7
    //   123: iload 8
    //   125: invokestatic 1139	kawa/lib/numbers:bitwiseRotateBitField	(Lgnu/math/IntNum;III)Lgnu/math/IntNum;
    //   128: areturn
    //   129: astore_1
    //   130: new 713	gnu/mapping/WrongType
    //   133: dup
    //   134: aload_1
    //   135: ldc -44
    //   137: iconst_1
    //   138: aload_2
    //   139: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   142: athrow
    //   143: astore_1
    //   144: new 713	gnu/mapping/WrongType
    //   147: dup
    //   148: aload_1
    //   149: ldc -44
    //   151: iconst_2
    //   152: aload_3
    //   153: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   156: athrow
    //   157: astore_1
    //   158: new 713	gnu/mapping/WrongType
    //   161: dup
    //   162: aload_1
    //   163: ldc -44
    //   165: iconst_3
    //   166: aload 4
    //   168: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   171: athrow
    //   172: astore_1
    //   173: new 713	gnu/mapping/WrongType
    //   176: dup
    //   177: aload_1
    //   178: ldc -44
    //   180: iconst_4
    //   181: aload 5
    //   183: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   186: athrow
    //   187: astore_1
    //   188: new 713	gnu/mapping/WrongType
    //   191: dup
    //   192: aload_1
    //   193: ldc -74
    //   195: iconst_1
    //   196: aload_2
    //   197: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   200: athrow
    //   201: astore_1
    //   202: new 713	gnu/mapping/WrongType
    //   205: dup
    //   206: aload_1
    //   207: ldc -74
    //   209: iconst_2
    //   210: aload_3
    //   211: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   214: athrow
    //   215: astore_1
    //   216: new 713	gnu/mapping/WrongType
    //   219: dup
    //   220: aload_1
    //   221: ldc -74
    //   223: iconst_3
    //   224: aload 4
    //   226: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   229: athrow
    //   230: astore_1
    //   231: new 713	gnu/mapping/WrongType
    //   234: dup
    //   235: aload_1
    //   236: ldc -74
    //   238: iconst_4
    //   239: aload 5
    //   241: invokespecial 718	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   244: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	245	0	this	numbers
    //   0	245	1	paramModuleMethod	ModuleMethod
    //   0	245	2	paramObject1	Object
    //   0	245	3	paramObject2	Object
    //   0	245	4	paramObject3	Object
    //   0	245	5	paramObject4	Object
    //   56	64	6	i	int
    //   66	56	7	j	int
    //   116	8	8	k	int
    // Exception table:
    //   from	to	target	type
    //   44	49	129	java/lang/ClassCastException
    //   49	58	143	java/lang/ClassCastException
    //   58	68	157	java/lang/ClassCastException
    //   68	74	172	java/lang/ClassCastException
    //   84	89	187	java/lang/ClassCastException
    //   89	98	201	java/lang/ClassCastException
    //   98	108	215	java/lang/ClassCastException
    //   108	118	230	java/lang/ClassCastException
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 14: 
    case 15: 
    case 16: 
    default: 
      return super.applyN(paramModuleMethod, paramArrayOfObject);
    case 12: 
      return max(paramArrayOfObject);
    case 13: 
      return min(paramArrayOfObject);
    case 17: 
      i = paramArrayOfObject.length;
      arrayOfIntNum = new IntNum[i];
    }
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        return gcd(arrayOfIntNum);
      }
      paramModuleMethod = paramArrayOfObject[i];
      try
      {
        localIntNum = LangObjType.coerceIntNum(paramModuleMethod);
        arrayOfIntNum[i] = localIntNum;
      }
      catch (ClassCastException paramArrayOfObject)
      {
        try
        {
          IntNum localIntNum = LangObjType.coerceIntNum(paramModuleMethod);
          arrayOfIntNum[i] = localIntNum;
        }
        catch (ClassCastException paramArrayOfObject)
        {
          throw new WrongType(paramArrayOfObject, "lcm", 0, paramModuleMethod);
        }
        paramArrayOfObject = paramArrayOfObject;
        throw new WrongType(paramArrayOfObject, "gcd", 0, paramModuleMethod);
      }
    }
    i = paramArrayOfObject.length;
    arrayOfIntNum = new IntNum[i];
    i -= 1;
    if (i < 0) {
      return lcm(arrayOfIntNum);
    }
    paramModuleMethod = paramArrayOfObject[i];
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 12: 
    case 13: 
    case 15: 
    case 16: 
    case 17: 
    case 18: 
    case 25: 
    case 33: 
    case 37: 
    case 38: 
    case 47: 
    case 48: 
    case 49: 
    case 50: 
    case 51: 
    case 52: 
    case 53: 
    case 58: 
    case 59: 
    case 61: 
    case 63: 
    case 66: 
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 67: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 65: 
      if (!(paramObject instanceof Quantity)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 64: 
      if (!(paramObject instanceof Quantity)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 62: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 60: 
      if (!(paramObject instanceof Number)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 57: 
      if (IntNum.asIntNumOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 56: 
      if (IntNum.asIntNumOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 55: 
      if (IntNum.asIntNumOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 54: 
      if (IntNum.asIntNumOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 46: 
      if (!(paramObject instanceof Number)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 45: 
      if (!(paramObject instanceof Number)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 44: 
      if (!(paramObject instanceof Number)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 43: 
      if (!(paramObject instanceof Number)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 42: 
      if (!(paramObject instanceof Complex)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 41: 
      if (!(paramObject instanceof Number)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 40: 
      if (!(paramObject instanceof Complex)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 39: 
      if (!(paramObject instanceof Complex)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 36: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 35: 
      if (!(paramObject instanceof Quantity)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 34: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 32: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 31: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 30: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 29: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 28: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 27: 
      if (!(paramObject instanceof Complex)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 26: 
      if (!(paramObject instanceof Complex)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 24: 
      if (RealNum.asRealNumOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 23: 
      if (RealNum.asRealNumOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 22: 
      if (RealNum.asRealNumOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 21: 
      if (RealNum.asRealNumOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 20: 
      if (RatNum.asRatNumOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 19: 
      if (RatNum.asRatNumOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 14: 
      if (!(paramObject instanceof Number)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 11: 
      if (RealNum.asRealNumOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 10: 
      if (RealNum.asRealNumOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 9: 
      if (!(paramObject instanceof Number)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 8: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 7: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 6: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 5: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 4: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 3: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 2: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    }
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    case 66: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 62: 
      if ((paramObject1 instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786431;
    case 60: 
      if (!(paramObject1 instanceof Number)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 53: 
      if (IntNum.asIntNumOrNull(paramObject1) != null)
      {
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null)
        {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        }
      }
      else
      {
        return -786431;
      }
      return -786430;
    case 48: 
      if (IntNum.asIntNumOrNull(paramObject1) != null)
      {
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786431;
    case 38: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 37: 
      if (RealNum.asRealNumOrNull(paramObject1) != null)
      {
        paramCallContext.value1 = paramObject1;
        if (RealNum.asRealNumOrNull(paramObject2) != null)
        {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        }
      }
      else
      {
        return -786431;
      }
      return -786430;
    case 33: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 25: 
      if (RealNum.asRealNumOrNull(paramObject1) != null)
      {
        paramCallContext.value1 = paramObject1;
        if (RealNum.asRealNumOrNull(paramObject2) != null)
        {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        }
      }
      else
      {
        return -786431;
      }
      return -786430;
    case 16: 
      if (RealNum.asRealNumOrNull(paramObject1) != null)
      {
        paramCallContext.value1 = paramObject1;
        if (RealNum.asRealNumOrNull(paramObject2) != null)
        {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        }
      }
      else
      {
        return -786431;
      }
      return -786430;
    }
    if (RealNum.asRealNumOrNull(paramObject1) != null)
    {
      paramCallContext.value1 = paramObject1;
      if (RealNum.asRealNumOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
    }
    else
    {
      return -786431;
    }
    return -786430;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
    case 59: 
      if (IntNum.asIntNumOrNull(paramObject1) != null)
      {
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      }
      return -786431;
    case 52: 
      if (IntNum.asIntNumOrNull(paramObject1) != null)
      {
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null)
        {
          paramCallContext.value2 = paramObject2;
          if (IntNum.asIntNumOrNull(paramObject3) == null) {
            break label175;
          }
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        }
      }
      else
      {
        return -786431;
      }
      return -786430;
      return -786429;
    case 51: 
      if (IntNum.asIntNumOrNull(paramObject1) != null)
      {
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      }
      return -786431;
    case 49: 
      label175:
      if (IntNum.asIntNumOrNull(paramObject1) != null)
      {
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      }
      return -786431;
    }
    paramCallContext.value1 = paramObject1;
    if (IntNum.asIntNumOrNull(paramObject2) != null)
    {
      paramCallContext.value2 = paramObject2;
      if (IntNum.asIntNumOrNull(paramObject3) != null)
      {
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      }
    }
    else
    {
      return -786430;
    }
    return -786429;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
    case 58: 
      if (IntNum.asIntNumOrNull(paramObject1) != null)
      {
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.value4 = paramObject4;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 4;
        return 0;
      }
      return -786431;
    }
    if (IntNum.asIntNumOrNull(paramObject1) != null)
    {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      if (IntNum.asIntNumOrNull(paramObject4) != null)
      {
        paramCallContext.value4 = paramObject4;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 4;
        return 0;
      }
    }
    else
    {
      return -786431;
    }
    return -786428;
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 14: 
    case 15: 
    case 16: 
    default: 
      return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
    case 18: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 17: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 13: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    }
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 5;
    return 0;
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
    atan = new GenericProc("atan");
    atan.setProperties(new Object[] { lambda$Fn1, lambda$Fn2 });
    sqrt = new GenericProc("sqrt");
    sqrt.setProperties(new Object[] { lambda$Fn3, lambda$Fn4 });
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\numbers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */