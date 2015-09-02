package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.F32Vector;
import gnu.lists.F64Vector;
import gnu.lists.LList;
import gnu.lists.S16Vector;
import gnu.lists.S32Vector;
import gnu.lists.S64Vector;
import gnu.lists.S8Vector;
import gnu.lists.U16Vector;
import gnu.lists.U32Vector;
import gnu.lists.U64Vector;
import gnu.lists.U8Vector;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;

public class uniform
  extends ModuleBody
{
  public static final uniform $instance;
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
  static final SimpleSymbol Lit2;
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
  static final SimpleSymbol Lit63;
  static final SimpleSymbol Lit64;
  static final SimpleSymbol Lit65;
  static final SimpleSymbol Lit66;
  static final SimpleSymbol Lit67;
  static final SimpleSymbol Lit68;
  static final SimpleSymbol Lit69;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit70;
  static final SimpleSymbol Lit71;
  static final SimpleSymbol Lit72;
  static final SimpleSymbol Lit73;
  static final SimpleSymbol Lit74;
  static final SimpleSymbol Lit75;
  static final SimpleSymbol Lit76;
  static final SimpleSymbol Lit77;
  static final SimpleSymbol Lit78;
  static final SimpleSymbol Lit79;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit80 = (SimpleSymbol)new SimpleSymbol("list->f64vector").readResolve();
  static final SimpleSymbol Lit9;
  public static final ModuleMethod f32vector;
  public static final ModuleMethod f32vector$Mn$Grlist;
  public static final ModuleMethod f32vector$Mnlength;
  public static final ModuleMethod f32vector$Mnref;
  public static final ModuleMethod f32vector$Mnset$Ex;
  public static final ModuleMethod f32vector$Qu;
  public static final ModuleMethod f64vector;
  public static final ModuleMethod f64vector$Mn$Grlist;
  public static final ModuleMethod f64vector$Mnlength;
  public static final ModuleMethod f64vector$Mnref;
  public static final ModuleMethod f64vector$Mnset$Ex;
  public static final ModuleMethod f64vector$Qu;
  public static final ModuleMethod list$Mn$Grf32vector;
  public static final ModuleMethod list$Mn$Grf64vector;
  public static final ModuleMethod list$Mn$Grs16vector;
  public static final ModuleMethod list$Mn$Grs32vector;
  public static final ModuleMethod list$Mn$Grs64vector;
  public static final ModuleMethod list$Mn$Grs8vector;
  public static final ModuleMethod list$Mn$Gru16vector;
  public static final ModuleMethod list$Mn$Gru32vector;
  public static final ModuleMethod list$Mn$Gru64vector;
  public static final ModuleMethod list$Mn$Gru8vector;
  public static final ModuleMethod make$Mnf32vector;
  public static final ModuleMethod make$Mnf64vector;
  public static final ModuleMethod make$Mns16vector;
  public static final ModuleMethod make$Mns32vector;
  public static final ModuleMethod make$Mns64vector;
  public static final ModuleMethod make$Mns8vector;
  public static final ModuleMethod make$Mnu16vector;
  public static final ModuleMethod make$Mnu32vector;
  public static final ModuleMethod make$Mnu64vector;
  public static final ModuleMethod make$Mnu8vector;
  public static final ModuleMethod s16vector;
  public static final ModuleMethod s16vector$Mn$Grlist;
  public static final ModuleMethod s16vector$Mnlength;
  public static final ModuleMethod s16vector$Mnref;
  public static final ModuleMethod s16vector$Mnset$Ex;
  public static final ModuleMethod s16vector$Qu;
  public static final ModuleMethod s32vector;
  public static final ModuleMethod s32vector$Mn$Grlist;
  public static final ModuleMethod s32vector$Mnlength;
  public static final ModuleMethod s32vector$Mnref;
  public static final ModuleMethod s32vector$Mnset$Ex;
  public static final ModuleMethod s32vector$Qu;
  public static final ModuleMethod s64vector;
  public static final ModuleMethod s64vector$Mn$Grlist;
  public static final ModuleMethod s64vector$Mnlength;
  public static final ModuleMethod s64vector$Mnref;
  public static final ModuleMethod s64vector$Mnset$Ex;
  public static final ModuleMethod s64vector$Qu;
  public static final ModuleMethod s8vector;
  public static final ModuleMethod s8vector$Mn$Grlist;
  public static final ModuleMethod s8vector$Mnlength;
  public static final ModuleMethod s8vector$Mnref;
  public static final ModuleMethod s8vector$Mnset$Ex;
  public static final ModuleMethod s8vector$Qu;
  public static final ModuleMethod u16vector;
  public static final ModuleMethod u16vector$Mn$Grlist;
  public static final ModuleMethod u16vector$Mnlength;
  public static final ModuleMethod u16vector$Mnref;
  public static final ModuleMethod u16vector$Mnset$Ex;
  public static final ModuleMethod u16vector$Qu;
  public static final ModuleMethod u32vector;
  public static final ModuleMethod u32vector$Mn$Grlist;
  public static final ModuleMethod u32vector$Mnlength;
  public static final ModuleMethod u32vector$Mnref;
  public static final ModuleMethod u32vector$Mnset$Ex;
  public static final ModuleMethod u32vector$Qu;
  public static final ModuleMethod u64vector;
  public static final ModuleMethod u64vector$Mn$Grlist;
  public static final ModuleMethod u64vector$Mnlength;
  public static final ModuleMethod u64vector$Mnref;
  public static final ModuleMethod u64vector$Mnset$Ex;
  public static final ModuleMethod u64vector$Qu;
  public static final ModuleMethod u8vector;
  public static final ModuleMethod u8vector$Mn$Grlist;
  public static final ModuleMethod u8vector$Mnlength;
  public static final ModuleMethod u8vector$Mnref;
  public static final ModuleMethod u8vector$Mnset$Ex;
  public static final ModuleMethod u8vector$Qu;
  
  static
  {
    Lit79 = (SimpleSymbol)new SimpleSymbol("f64vector->list").readResolve();
    Lit78 = (SimpleSymbol)new SimpleSymbol("f64vector-set!").readResolve();
    Lit77 = (SimpleSymbol)new SimpleSymbol("f64vector-ref").readResolve();
    Lit76 = (SimpleSymbol)new SimpleSymbol("f64vector-length").readResolve();
    Lit75 = (SimpleSymbol)new SimpleSymbol("f64vector").readResolve();
    Lit74 = (SimpleSymbol)new SimpleSymbol("make-f64vector").readResolve();
    Lit73 = (SimpleSymbol)new SimpleSymbol("f64vector?").readResolve();
    Lit72 = (SimpleSymbol)new SimpleSymbol("list->f32vector").readResolve();
    Lit71 = (SimpleSymbol)new SimpleSymbol("f32vector->list").readResolve();
    Lit70 = (SimpleSymbol)new SimpleSymbol("f32vector-set!").readResolve();
    Lit69 = (SimpleSymbol)new SimpleSymbol("f32vector-ref").readResolve();
    Lit68 = (SimpleSymbol)new SimpleSymbol("f32vector-length").readResolve();
    Lit67 = (SimpleSymbol)new SimpleSymbol("f32vector").readResolve();
    Lit66 = (SimpleSymbol)new SimpleSymbol("make-f32vector").readResolve();
    Lit65 = (SimpleSymbol)new SimpleSymbol("f32vector?").readResolve();
    Lit64 = (SimpleSymbol)new SimpleSymbol("list->u64vector").readResolve();
    Lit63 = (SimpleSymbol)new SimpleSymbol("u64vector->list").readResolve();
    Lit62 = (SimpleSymbol)new SimpleSymbol("u64vector-set!").readResolve();
    Lit61 = (SimpleSymbol)new SimpleSymbol("u64vector-ref").readResolve();
    Lit60 = (SimpleSymbol)new SimpleSymbol("u64vector-length").readResolve();
    Lit59 = (SimpleSymbol)new SimpleSymbol("u64vector").readResolve();
    Lit58 = (SimpleSymbol)new SimpleSymbol("make-u64vector").readResolve();
    Lit57 = (SimpleSymbol)new SimpleSymbol("u64vector?").readResolve();
    Lit56 = (SimpleSymbol)new SimpleSymbol("list->s64vector").readResolve();
    Lit55 = (SimpleSymbol)new SimpleSymbol("s64vector->list").readResolve();
    Lit54 = (SimpleSymbol)new SimpleSymbol("s64vector-set!").readResolve();
    Lit53 = (SimpleSymbol)new SimpleSymbol("s64vector-ref").readResolve();
    Lit52 = (SimpleSymbol)new SimpleSymbol("s64vector-length").readResolve();
    Lit51 = (SimpleSymbol)new SimpleSymbol("s64vector").readResolve();
    Lit50 = (SimpleSymbol)new SimpleSymbol("make-s64vector").readResolve();
    Lit49 = (SimpleSymbol)new SimpleSymbol("s64vector?").readResolve();
    Lit48 = (SimpleSymbol)new SimpleSymbol("list->u32vector").readResolve();
    Lit47 = (SimpleSymbol)new SimpleSymbol("u32vector->list").readResolve();
    Lit46 = (SimpleSymbol)new SimpleSymbol("u32vector-set!").readResolve();
    Lit45 = (SimpleSymbol)new SimpleSymbol("u32vector-ref").readResolve();
    Lit44 = (SimpleSymbol)new SimpleSymbol("u32vector-length").readResolve();
    Lit43 = (SimpleSymbol)new SimpleSymbol("u32vector").readResolve();
    Lit42 = (SimpleSymbol)new SimpleSymbol("make-u32vector").readResolve();
    Lit41 = (SimpleSymbol)new SimpleSymbol("u32vector?").readResolve();
    Lit40 = (SimpleSymbol)new SimpleSymbol("list->s32vector").readResolve();
    Lit39 = (SimpleSymbol)new SimpleSymbol("s32vector->list").readResolve();
    Lit38 = (SimpleSymbol)new SimpleSymbol("s32vector-set!").readResolve();
    Lit37 = (SimpleSymbol)new SimpleSymbol("s32vector-ref").readResolve();
    Lit36 = (SimpleSymbol)new SimpleSymbol("s32vector-length").readResolve();
    Lit35 = (SimpleSymbol)new SimpleSymbol("s32vector").readResolve();
    Lit34 = (SimpleSymbol)new SimpleSymbol("make-s32vector").readResolve();
    Lit33 = (SimpleSymbol)new SimpleSymbol("s32vector?").readResolve();
    Lit32 = (SimpleSymbol)new SimpleSymbol("list->u16vector").readResolve();
    Lit31 = (SimpleSymbol)new SimpleSymbol("u16vector->list").readResolve();
    Lit30 = (SimpleSymbol)new SimpleSymbol("u16vector-set!").readResolve();
    Lit29 = (SimpleSymbol)new SimpleSymbol("u16vector-ref").readResolve();
    Lit28 = (SimpleSymbol)new SimpleSymbol("u16vector-length").readResolve();
    Lit27 = (SimpleSymbol)new SimpleSymbol("u16vector").readResolve();
    Lit26 = (SimpleSymbol)new SimpleSymbol("make-u16vector").readResolve();
    Lit25 = (SimpleSymbol)new SimpleSymbol("u16vector?").readResolve();
    Lit24 = (SimpleSymbol)new SimpleSymbol("list->s16vector").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("s16vector->list").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("s16vector-set!").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("s16vector-ref").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("s16vector-length").readResolve();
    Lit19 = (SimpleSymbol)new SimpleSymbol("s16vector").readResolve();
    Lit18 = (SimpleSymbol)new SimpleSymbol("make-s16vector").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("s16vector?").readResolve();
    Lit16 = (SimpleSymbol)new SimpleSymbol("list->u8vector").readResolve();
    Lit15 = (SimpleSymbol)new SimpleSymbol("u8vector->list").readResolve();
    Lit14 = (SimpleSymbol)new SimpleSymbol("u8vector-set!").readResolve();
    Lit13 = (SimpleSymbol)new SimpleSymbol("u8vector-ref").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("u8vector-length").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("u8vector").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("make-u8vector").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("u8vector?").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("list->s8vector").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("s8vector->list").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("s8vector-set!").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("s8vector-ref").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("s8vector-length").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("s8vector").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("make-s8vector").readResolve();
    Lit1 = (SimpleSymbol)new SimpleSymbol("s8vector?").readResolve();
    Lit0 = IntNum.make(0);
    $instance = new uniform();
    uniform localuniform = $instance;
    s8vector$Qu = new ModuleMethod(localuniform, 1, Lit1, 4097);
    make$Mns8vector = new ModuleMethod(localuniform, 2, Lit2, 8193);
    s8vector = new ModuleMethod(localuniform, 4, Lit3, 61440);
    s8vector$Mnlength = new ModuleMethod(localuniform, 5, Lit4, 4097);
    s8vector$Mnref = new ModuleMethod(localuniform, 6, Lit5, 8194);
    s8vector$Mnset$Ex = new ModuleMethod(localuniform, 7, Lit6, 12291);
    s8vector$Mn$Grlist = new ModuleMethod(localuniform, 8, Lit7, 4097);
    list$Mn$Grs8vector = new ModuleMethod(localuniform, 9, Lit8, 4097);
    u8vector$Qu = new ModuleMethod(localuniform, 10, Lit9, 4097);
    make$Mnu8vector = new ModuleMethod(localuniform, 11, Lit10, 8193);
    u8vector = new ModuleMethod(localuniform, 13, Lit11, 61440);
    u8vector$Mnlength = new ModuleMethod(localuniform, 14, Lit12, 4097);
    u8vector$Mnref = new ModuleMethod(localuniform, 15, Lit13, 8194);
    u8vector$Mnset$Ex = new ModuleMethod(localuniform, 16, Lit14, 12291);
    u8vector$Mn$Grlist = new ModuleMethod(localuniform, 17, Lit15, 4097);
    list$Mn$Gru8vector = new ModuleMethod(localuniform, 18, Lit16, 4097);
    s16vector$Qu = new ModuleMethod(localuniform, 19, Lit17, 4097);
    make$Mns16vector = new ModuleMethod(localuniform, 20, Lit18, 8193);
    s16vector = new ModuleMethod(localuniform, 22, Lit19, 61440);
    s16vector$Mnlength = new ModuleMethod(localuniform, 23, Lit20, 4097);
    s16vector$Mnref = new ModuleMethod(localuniform, 24, Lit21, 8194);
    s16vector$Mnset$Ex = new ModuleMethod(localuniform, 25, Lit22, 12291);
    s16vector$Mn$Grlist = new ModuleMethod(localuniform, 26, Lit23, 4097);
    list$Mn$Grs16vector = new ModuleMethod(localuniform, 27, Lit24, 4097);
    u16vector$Qu = new ModuleMethod(localuniform, 28, Lit25, 4097);
    make$Mnu16vector = new ModuleMethod(localuniform, 29, Lit26, 8193);
    u16vector = new ModuleMethod(localuniform, 31, Lit27, 61440);
    u16vector$Mnlength = new ModuleMethod(localuniform, 32, Lit28, 4097);
    u16vector$Mnref = new ModuleMethod(localuniform, 33, Lit29, 8194);
    u16vector$Mnset$Ex = new ModuleMethod(localuniform, 34, Lit30, 12291);
    u16vector$Mn$Grlist = new ModuleMethod(localuniform, 35, Lit31, 4097);
    list$Mn$Gru16vector = new ModuleMethod(localuniform, 36, Lit32, 4097);
    s32vector$Qu = new ModuleMethod(localuniform, 37, Lit33, 4097);
    make$Mns32vector = new ModuleMethod(localuniform, 38, Lit34, 8193);
    s32vector = new ModuleMethod(localuniform, 40, Lit35, 61440);
    s32vector$Mnlength = new ModuleMethod(localuniform, 41, Lit36, 4097);
    s32vector$Mnref = new ModuleMethod(localuniform, 42, Lit37, 8194);
    s32vector$Mnset$Ex = new ModuleMethod(localuniform, 43, Lit38, 12291);
    s32vector$Mn$Grlist = new ModuleMethod(localuniform, 44, Lit39, 4097);
    list$Mn$Grs32vector = new ModuleMethod(localuniform, 45, Lit40, 4097);
    u32vector$Qu = new ModuleMethod(localuniform, 46, Lit41, 4097);
    make$Mnu32vector = new ModuleMethod(localuniform, 47, Lit42, 8193);
    u32vector = new ModuleMethod(localuniform, 49, Lit43, 61440);
    u32vector$Mnlength = new ModuleMethod(localuniform, 50, Lit44, 4097);
    u32vector$Mnref = new ModuleMethod(localuniform, 51, Lit45, 8194);
    u32vector$Mnset$Ex = new ModuleMethod(localuniform, 52, Lit46, 12291);
    u32vector$Mn$Grlist = new ModuleMethod(localuniform, 53, Lit47, 4097);
    list$Mn$Gru32vector = new ModuleMethod(localuniform, 54, Lit48, 4097);
    s64vector$Qu = new ModuleMethod(localuniform, 55, Lit49, 4097);
    make$Mns64vector = new ModuleMethod(localuniform, 56, Lit50, 8193);
    s64vector = new ModuleMethod(localuniform, 58, Lit51, 61440);
    s64vector$Mnlength = new ModuleMethod(localuniform, 59, Lit52, 4097);
    s64vector$Mnref = new ModuleMethod(localuniform, 60, Lit53, 8194);
    s64vector$Mnset$Ex = new ModuleMethod(localuniform, 61, Lit54, 12291);
    s64vector$Mn$Grlist = new ModuleMethod(localuniform, 62, Lit55, 4097);
    list$Mn$Grs64vector = new ModuleMethod(localuniform, 63, Lit56, 4097);
    u64vector$Qu = new ModuleMethod(localuniform, 64, Lit57, 4097);
    make$Mnu64vector = new ModuleMethod(localuniform, 65, Lit58, 8193);
    u64vector = new ModuleMethod(localuniform, 67, Lit59, 61440);
    u64vector$Mnlength = new ModuleMethod(localuniform, 68, Lit60, 4097);
    u64vector$Mnref = new ModuleMethod(localuniform, 69, Lit61, 8194);
    u64vector$Mnset$Ex = new ModuleMethod(localuniform, 70, Lit62, 12291);
    u64vector$Mn$Grlist = new ModuleMethod(localuniform, 71, Lit63, 4097);
    list$Mn$Gru64vector = new ModuleMethod(localuniform, 72, Lit64, 4097);
    f32vector$Qu = new ModuleMethod(localuniform, 73, Lit65, 4097);
    make$Mnf32vector = new ModuleMethod(localuniform, 74, Lit66, 8193);
    f32vector = new ModuleMethod(localuniform, 76, Lit67, 61440);
    f32vector$Mnlength = new ModuleMethod(localuniform, 77, Lit68, 4097);
    f32vector$Mnref = new ModuleMethod(localuniform, 78, Lit69, 8194);
    f32vector$Mnset$Ex = new ModuleMethod(localuniform, 79, Lit70, 12291);
    f32vector$Mn$Grlist = new ModuleMethod(localuniform, 80, Lit71, 4097);
    list$Mn$Grf32vector = new ModuleMethod(localuniform, 81, Lit72, 4097);
    f64vector$Qu = new ModuleMethod(localuniform, 82, Lit73, 4097);
    make$Mnf64vector = new ModuleMethod(localuniform, 83, Lit74, 8193);
    f64vector = new ModuleMethod(localuniform, 85, Lit75, 61440);
    f64vector$Mnlength = new ModuleMethod(localuniform, 86, Lit76, 4097);
    f64vector$Mnref = new ModuleMethod(localuniform, 87, Lit77, 8194);
    f64vector$Mnset$Ex = new ModuleMethod(localuniform, 88, Lit78, 12291);
    f64vector$Mn$Grlist = new ModuleMethod(localuniform, 89, Lit79, 4097);
    list$Mn$Grf64vector = new ModuleMethod(localuniform, 90, Lit80, 4097);
    $instance.run();
  }
  
  public uniform()
  {
    ModuleInfo.register(this);
  }
  
  public static LList f32vector$To$List(F32Vector paramF32Vector)
  {
    return LList.makeList(paramF32Vector);
  }
  
  public static F32Vector f32vector$V(Object[] paramArrayOfObject)
  {
    return list$To$F32vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int f32vectorLength(F32Vector paramF32Vector)
  {
    return paramF32Vector.size();
  }
  
  public static float f32vectorRef(F32Vector paramF32Vector, int paramInt)
  {
    return paramF32Vector.floatAt(paramInt);
  }
  
  public static void f32vectorSet$Ex(F32Vector paramF32Vector, int paramInt, float paramFloat)
  {
    paramF32Vector.setFloatAt(paramInt, paramFloat);
  }
  
  public static LList f64vector$To$List(F64Vector paramF64Vector)
  {
    return LList.makeList(paramF64Vector);
  }
  
  public static F64Vector f64vector$V(Object[] paramArrayOfObject)
  {
    return list$To$F64vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int f64vectorLength(F64Vector paramF64Vector)
  {
    return paramF64Vector.size();
  }
  
  public static double f64vectorRef(F64Vector paramF64Vector, int paramInt)
  {
    return paramF64Vector.doubleAt(paramInt);
  }
  
  public static void f64vectorSet$Ex(F64Vector paramF64Vector, int paramInt, double paramDouble)
  {
    paramF64Vector.setDoubleAt(paramInt, paramDouble);
  }
  
  public static boolean isF32vector(Object paramObject)
  {
    return paramObject instanceof F32Vector;
  }
  
  public static boolean isF64vector(Object paramObject)
  {
    return paramObject instanceof F64Vector;
  }
  
  public static boolean isS16vector(Object paramObject)
  {
    return paramObject instanceof S16Vector;
  }
  
  public static boolean isS32vector(Object paramObject)
  {
    return paramObject instanceof S32Vector;
  }
  
  public static boolean isS64vector(Object paramObject)
  {
    return paramObject instanceof S64Vector;
  }
  
  public static boolean isS8vector(Object paramObject)
  {
    return paramObject instanceof S8Vector;
  }
  
  public static boolean isU16vector(Object paramObject)
  {
    return paramObject instanceof U16Vector;
  }
  
  public static boolean isU32vector(Object paramObject)
  {
    return paramObject instanceof U32Vector;
  }
  
  public static boolean isU64vector(Object paramObject)
  {
    return paramObject instanceof U64Vector;
  }
  
  public static boolean isU8vector(Object paramObject)
  {
    return paramObject instanceof U8Vector;
  }
  
  public static F32Vector list$To$F32vector(LList paramLList)
  {
    return new F32Vector(paramLList);
  }
  
  public static F64Vector list$To$F64vector(LList paramLList)
  {
    return new F64Vector(paramLList);
  }
  
  public static S16Vector list$To$S16vector(LList paramLList)
  {
    return new S16Vector(paramLList);
  }
  
  public static S32Vector list$To$S32vector(LList paramLList)
  {
    return new S32Vector(paramLList);
  }
  
  public static S64Vector list$To$S64vector(LList paramLList)
  {
    return new S64Vector(paramLList);
  }
  
  public static S8Vector list$To$S8vector(LList paramLList)
  {
    return new S8Vector(paramLList);
  }
  
  public static U16Vector list$To$U16vector(LList paramLList)
  {
    return new U16Vector(paramLList);
  }
  
  public static U32Vector list$To$U32vector(LList paramLList)
  {
    return new U32Vector(paramLList);
  }
  
  public static U64Vector list$To$U64vector(LList paramLList)
  {
    return new U64Vector(paramLList);
  }
  
  public static U8Vector list$To$U8vector(LList paramLList)
  {
    return new U8Vector(paramLList);
  }
  
  public static F32Vector makeF32vector(int paramInt)
  {
    return makeF32vector(paramInt, 0.0F);
  }
  
  public static F32Vector makeF32vector(int paramInt, float paramFloat)
  {
    return new F32Vector(paramInt, paramFloat);
  }
  
  public static F64Vector makeF64vector(int paramInt)
  {
    return makeF64vector(paramInt, 0.0D);
  }
  
  public static F64Vector makeF64vector(int paramInt, double paramDouble)
  {
    return new F64Vector(paramInt, paramDouble);
  }
  
  public static S16Vector makeS16vector(int paramInt)
  {
    return makeS16vector(paramInt, 0);
  }
  
  public static S16Vector makeS16vector(int paramInt1, int paramInt2)
  {
    return new S16Vector(paramInt1, (short)paramInt2);
  }
  
  public static S32Vector makeS32vector(int paramInt)
  {
    return makeS32vector(paramInt, 0);
  }
  
  public static S32Vector makeS32vector(int paramInt1, int paramInt2)
  {
    return new S32Vector(paramInt1, paramInt2);
  }
  
  public static S64Vector makeS64vector(int paramInt)
  {
    return makeS64vector(paramInt, 0L);
  }
  
  public static S64Vector makeS64vector(int paramInt, long paramLong)
  {
    return new S64Vector(paramInt, paramLong);
  }
  
  public static S8Vector makeS8vector(int paramInt)
  {
    return makeS8vector(paramInt, 0);
  }
  
  public static S8Vector makeS8vector(int paramInt1, int paramInt2)
  {
    return new S8Vector(paramInt1, (byte)paramInt2);
  }
  
  public static U16Vector makeU16vector(int paramInt)
  {
    return makeU16vector(paramInt, 0);
  }
  
  public static U16Vector makeU16vector(int paramInt1, int paramInt2)
  {
    return new U16Vector(paramInt1, (short)paramInt2);
  }
  
  public static U32Vector makeU32vector(int paramInt)
  {
    return makeU32vector(paramInt, 0L);
  }
  
  public static U32Vector makeU32vector(int paramInt, long paramLong)
  {
    return new U32Vector(paramInt, (int)paramLong);
  }
  
  public static U64Vector makeU64vector(int paramInt)
  {
    return makeU64vector(paramInt, Lit0);
  }
  
  public static U64Vector makeU64vector(int paramInt, IntNum paramIntNum)
  {
    try
    {
      long l = paramIntNum.longValue();
      return new U64Vector(paramInt, l);
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "gnu.lists.U64Vector.<init>(int,long)", 2, paramIntNum);
    }
  }
  
  public static U8Vector makeU8vector(int paramInt)
  {
    return makeU8vector(paramInt, 0);
  }
  
  public static U8Vector makeU8vector(int paramInt1, int paramInt2)
  {
    return new U8Vector(paramInt1, (byte)paramInt2);
  }
  
  public static LList s16vector$To$List(S16Vector paramS16Vector)
  {
    return LList.makeList(paramS16Vector);
  }
  
  public static S16Vector s16vector$V(Object[] paramArrayOfObject)
  {
    return list$To$S16vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int s16vectorLength(S16Vector paramS16Vector)
  {
    return paramS16Vector.size();
  }
  
  public static int s16vectorRef(S16Vector paramS16Vector, int paramInt)
  {
    return paramS16Vector.intAt(paramInt);
  }
  
  public static void s16vectorSet$Ex(S16Vector paramS16Vector, int paramInt1, int paramInt2)
  {
    paramS16Vector.setShortAt(paramInt1, (short)paramInt2);
  }
  
  public static LList s32vector$To$List(S32Vector paramS32Vector)
  {
    return LList.makeList(paramS32Vector);
  }
  
  public static S32Vector s32vector$V(Object[] paramArrayOfObject)
  {
    return list$To$S32vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int s32vectorLength(S32Vector paramS32Vector)
  {
    return paramS32Vector.size();
  }
  
  public static int s32vectorRef(S32Vector paramS32Vector, int paramInt)
  {
    return paramS32Vector.intAt(paramInt);
  }
  
  public static void s32vectorSet$Ex(S32Vector paramS32Vector, int paramInt1, int paramInt2)
  {
    paramS32Vector.setIntAt(paramInt1, paramInt2);
  }
  
  public static LList s64vector$To$List(S64Vector paramS64Vector)
  {
    return LList.makeList(paramS64Vector);
  }
  
  public static S64Vector s64vector$V(Object[] paramArrayOfObject)
  {
    return list$To$S64vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int s64vectorLength(S64Vector paramS64Vector)
  {
    return paramS64Vector.size();
  }
  
  public static long s64vectorRef(S64Vector paramS64Vector, int paramInt)
  {
    return paramS64Vector.longAt(paramInt);
  }
  
  public static void s64vectorSet$Ex(S64Vector paramS64Vector, int paramInt, long paramLong)
  {
    paramS64Vector.setLongAt(paramInt, paramLong);
  }
  
  public static LList s8vector$To$List(S8Vector paramS8Vector)
  {
    return LList.makeList(paramS8Vector);
  }
  
  public static S8Vector s8vector$V(Object[] paramArrayOfObject)
  {
    return list$To$S8vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int s8vectorLength(S8Vector paramS8Vector)
  {
    return paramS8Vector.size();
  }
  
  public static int s8vectorRef(S8Vector paramS8Vector, int paramInt)
  {
    return paramS8Vector.intAt(paramInt);
  }
  
  public static void s8vectorSet$Ex(S8Vector paramS8Vector, int paramInt1, int paramInt2)
  {
    paramS8Vector.setByteAt(paramInt1, (byte)paramInt2);
  }
  
  public static LList u16vector$To$List(U16Vector paramU16Vector)
  {
    return LList.makeList(paramU16Vector);
  }
  
  public static U16Vector u16vector$V(Object[] paramArrayOfObject)
  {
    return list$To$U16vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int u16vectorLength(U16Vector paramU16Vector)
  {
    return paramU16Vector.size();
  }
  
  public static int u16vectorRef(U16Vector paramU16Vector, int paramInt)
  {
    return paramU16Vector.intAt(paramInt);
  }
  
  public static void u16vectorSet$Ex(U16Vector paramU16Vector, int paramInt1, int paramInt2)
  {
    paramU16Vector.setShortAt(paramInt1, (short)paramInt2);
  }
  
  public static LList u32vector$To$List(U32Vector paramU32Vector)
  {
    return LList.makeList(paramU32Vector);
  }
  
  public static U32Vector u32vector$V(Object[] paramArrayOfObject)
  {
    return list$To$U32vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int u32vectorLength(U32Vector paramU32Vector)
  {
    return paramU32Vector.size();
  }
  
  public static long u32vectorRef(U32Vector paramU32Vector, int paramInt)
  {
    return ((Number)paramU32Vector.get(paramInt)).longValue();
  }
  
  public static void u32vectorSet$Ex(U32Vector paramU32Vector, int paramInt, long paramLong)
  {
    paramU32Vector.setIntAt(paramInt, (int)paramLong);
  }
  
  public static LList u64vector$To$List(U64Vector paramU64Vector)
  {
    return LList.makeList(paramU64Vector);
  }
  
  public static U64Vector u64vector$V(Object[] paramArrayOfObject)
  {
    return list$To$U64vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int u64vectorLength(U64Vector paramU64Vector)
  {
    return paramU64Vector.size();
  }
  
  public static IntNum u64vectorRef(U64Vector paramU64Vector, int paramInt)
  {
    return LangObjType.coerceIntNum(paramU64Vector.get(paramInt));
  }
  
  public static void u64vectorSet$Ex(U64Vector paramU64Vector, int paramInt, IntNum paramIntNum)
  {
    try
    {
      long l = paramIntNum.longValue();
      paramU64Vector.setLongAt(paramInt, l);
      return;
    }
    catch (ClassCastException paramU64Vector)
    {
      throw new WrongType(paramU64Vector, "gnu.lists.U64Vector.setLongAt(int,long)", 3, paramIntNum);
    }
  }
  
  public static LList u8vector$To$List(U8Vector paramU8Vector)
  {
    return LList.makeList(paramU8Vector);
  }
  
  public static U8Vector u8vector$V(Object[] paramArrayOfObject)
  {
    return list$To$U8vector(LList.makeList(paramArrayOfObject, 0));
  }
  
  public static int u8vectorLength(U8Vector paramU8Vector)
  {
    return paramU8Vector.size();
  }
  
  public static int u8vectorRef(U8Vector paramU8Vector, int paramInt)
  {
    return paramU8Vector.intAt(paramInt);
  }
  
  public static void u8vectorSet$Ex(U8Vector paramU8Vector, int paramInt1, int paramInt2)
  {
    paramU8Vector.setByteAt(paramInt1, (byte)paramInt2);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 3: 
    case 4: 
    case 6: 
    case 7: 
    case 12: 
    case 13: 
    case 15: 
    case 16: 
    case 21: 
    case 22: 
    case 24: 
    case 25: 
    case 30: 
    case 31: 
    case 33: 
    case 34: 
    case 39: 
    case 40: 
    case 42: 
    case 43: 
    case 48: 
    case 49: 
    case 51: 
    case 52: 
    case 57: 
    case 58: 
    case 60: 
    case 61: 
    case 66: 
    case 67: 
    case 69: 
    case 70: 
    case 75: 
    case 76: 
    case 78: 
    case 79: 
    case 84: 
    case 85: 
    case 87: 
    case 88: 
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 1: 
      if (isS8vector(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    try
    {
      i = ((Number)paramObject).intValue();
      return makeS8vector(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        int i = ((Number)paramObject).intValue();
        return makeF64vector(i);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "make-f64vector", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (F64Vector)paramObject;
        return Integer.valueOf(f64vectorLength(paramModuleMethod));
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "f64vector-length", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (F64Vector)paramObject;
        return f64vector$To$List(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "f64vector->list", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (LList)paramObject;
        return list$To$F64vector(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "list->f64vector", 1, paramObject);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "make-s8vector", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (S8Vector)paramObject;
      return Integer.valueOf(s8vectorLength(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "s8vector-length", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (S8Vector)paramObject;
      return s8vector$To$List(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "s8vector->list", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (LList)paramObject;
      return list$To$S8vector(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "list->s8vector", 1, paramObject);
    }
    if (isU8vector(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    try
    {
      i = ((Number)paramObject).intValue();
      return makeU8vector(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-u8vector", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (U8Vector)paramObject;
      return Integer.valueOf(u8vectorLength(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "u8vector-length", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (U8Vector)paramObject;
      return u8vector$To$List(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "u8vector->list", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (LList)paramObject;
      return list$To$U8vector(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "list->u8vector", 1, paramObject);
    }
    if (isS16vector(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    try
    {
      i = ((Number)paramObject).intValue();
      return makeS16vector(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-s16vector", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (S16Vector)paramObject;
      return Integer.valueOf(s16vectorLength(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "s16vector-length", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (S16Vector)paramObject;
      return s16vector$To$List(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "s16vector->list", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (LList)paramObject;
      return list$To$S16vector(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "list->s16vector", 1, paramObject);
    }
    if (isU16vector(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    try
    {
      i = ((Number)paramObject).intValue();
      return makeU16vector(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-u16vector", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (U16Vector)paramObject;
      return Integer.valueOf(u16vectorLength(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "u16vector-length", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (U16Vector)paramObject;
      return u16vector$To$List(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "u16vector->list", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (LList)paramObject;
      return list$To$U16vector(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "list->u16vector", 1, paramObject);
    }
    if (isS32vector(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    try
    {
      i = ((Number)paramObject).intValue();
      return makeS32vector(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-s32vector", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (S32Vector)paramObject;
      return Integer.valueOf(s32vectorLength(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "s32vector-length", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (S32Vector)paramObject;
      return s32vector$To$List(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "s32vector->list", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (LList)paramObject;
      return list$To$S32vector(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "list->s32vector", 1, paramObject);
    }
    if (isU32vector(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    try
    {
      i = ((Number)paramObject).intValue();
      return makeU32vector(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-u32vector", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (U32Vector)paramObject;
      return Integer.valueOf(u32vectorLength(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "u32vector-length", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (U32Vector)paramObject;
      return u32vector$To$List(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "u32vector->list", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (LList)paramObject;
      return list$To$U32vector(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "list->u32vector", 1, paramObject);
    }
    if (isS64vector(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    try
    {
      i = ((Number)paramObject).intValue();
      return makeS64vector(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-s64vector", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (S64Vector)paramObject;
      return Integer.valueOf(s64vectorLength(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "s64vector-length", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (S64Vector)paramObject;
      return s64vector$To$List(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "s64vector->list", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (LList)paramObject;
      return list$To$S64vector(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "list->s64vector", 1, paramObject);
    }
    if (isU64vector(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    try
    {
      i = ((Number)paramObject).intValue();
      return makeU64vector(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-u64vector", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (U64Vector)paramObject;
      return Integer.valueOf(u64vectorLength(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "u64vector-length", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (U64Vector)paramObject;
      return u64vector$To$List(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "u64vector->list", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (LList)paramObject;
      return list$To$U64vector(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "list->u64vector", 1, paramObject);
    }
    if (isF32vector(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    try
    {
      i = ((Number)paramObject).intValue();
      return makeF32vector(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-f32vector", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (F32Vector)paramObject;
      return Integer.valueOf(f32vectorLength(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "f32vector-length", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (F32Vector)paramObject;
      return f32vector$To$List(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "f32vector->list", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (LList)paramObject;
      return list$To$F32vector(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "list->f32vector", 1, paramObject);
    }
    if (isF64vector(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
  
  /* Error */
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 1033	gnu/expr/ModuleMethod:selector	I
    //   4: lookupswitch	default:+172->176, 2:+180->184, 6:+206->210, 11:+230->234, 15:+256->260, 20:+280->284, 24:+306->310, 29:+330->334, 33:+356->360, 38:+380->384, 42:+406->410, 47:+430->434, 51:+456->460, 56:+480->484, 60:+506->510, 65:+530->534, 69:+551->555, 74:+572->576, 78:+598->602, 83:+622->626, 87:+648->652
    //   176: aload_0
    //   177: aload_1
    //   178: aload_2
    //   179: aload_3
    //   180: invokespecial 1137	gnu/expr/ModuleBody:apply2	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   183: areturn
    //   184: aload_2
    //   185: checkcast 864	java/lang/Number
    //   188: invokevirtual 1049	java/lang/Number:intValue	()I
    //   191: istore 7
    //   193: aload_3
    //   194: checkcast 864	java/lang/Number
    //   197: invokevirtual 1049	java/lang/Number:intValue	()I
    //   200: istore 8
    //   202: iload 7
    //   204: iload 8
    //   206: invokestatic 840	kawa/lib/uniform:makeS8vector	(II)Lgnu/lists/S8Vector;
    //   209: areturn
    //   210: aload_2
    //   211: checkcast 757	gnu/lists/S8Vector
    //   214: astore_1
    //   215: aload_3
    //   216: checkcast 864	java/lang/Number
    //   219: invokevirtual 1049	java/lang/Number:intValue	()I
    //   222: istore 7
    //   224: aload_1
    //   225: iload 7
    //   227: invokestatic 1139	kawa/lib/uniform:s8vectorRef	(Lgnu/lists/S8Vector;I)I
    //   230: invokestatic 1059	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   233: areturn
    //   234: aload_2
    //   235: checkcast 864	java/lang/Number
    //   238: invokevirtual 1049	java/lang/Number:intValue	()I
    //   241: istore 7
    //   243: aload_3
    //   244: checkcast 864	java/lang/Number
    //   247: invokevirtual 1049	java/lang/Number:intValue	()I
    //   250: istore 8
    //   252: iload 7
    //   254: iload 8
    //   256: invokestatic 881	kawa/lib/uniform:makeU8vector	(II)Lgnu/lists/U8Vector;
    //   259: areturn
    //   260: aload_2
    //   261: checkcast 769	gnu/lists/U8Vector
    //   264: astore_1
    //   265: aload_3
    //   266: checkcast 864	java/lang/Number
    //   269: invokevirtual 1049	java/lang/Number:intValue	()I
    //   272: istore 7
    //   274: aload_1
    //   275: iload 7
    //   277: invokestatic 1141	kawa/lib/uniform:u8vectorRef	(Lgnu/lists/U8Vector;I)I
    //   280: invokestatic 1059	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   283: areturn
    //   284: aload_2
    //   285: checkcast 864	java/lang/Number
    //   288: invokevirtual 1049	java/lang/Number:intValue	()I
    //   291: istore 7
    //   293: aload_3
    //   294: checkcast 864	java/lang/Number
    //   297: invokevirtual 1049	java/lang/Number:intValue	()I
    //   300: istore 8
    //   302: iload 7
    //   304: iload 8
    //   306: invokestatic 816	kawa/lib/uniform:makeS16vector	(II)Lgnu/lists/S16Vector;
    //   309: areturn
    //   310: aload_2
    //   311: checkcast 748	gnu/lists/S16Vector
    //   314: astore_1
    //   315: aload_3
    //   316: checkcast 864	java/lang/Number
    //   319: invokevirtual 1049	java/lang/Number:intValue	()I
    //   322: istore 7
    //   324: aload_1
    //   325: iload 7
    //   327: invokestatic 1143	kawa/lib/uniform:s16vectorRef	(Lgnu/lists/S16Vector;I)I
    //   330: invokestatic 1059	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   333: areturn
    //   334: aload_2
    //   335: checkcast 864	java/lang/Number
    //   338: invokevirtual 1049	java/lang/Number:intValue	()I
    //   341: istore 7
    //   343: aload_3
    //   344: checkcast 864	java/lang/Number
    //   347: invokevirtual 1049	java/lang/Number:intValue	()I
    //   350: istore 8
    //   352: iload 7
    //   354: iload 8
    //   356: invokestatic 848	kawa/lib/uniform:makeU16vector	(II)Lgnu/lists/U16Vector;
    //   359: areturn
    //   360: aload_2
    //   361: checkcast 760	gnu/lists/U16Vector
    //   364: astore_1
    //   365: aload_3
    //   366: checkcast 864	java/lang/Number
    //   369: invokevirtual 1049	java/lang/Number:intValue	()I
    //   372: istore 7
    //   374: aload_1
    //   375: iload 7
    //   377: invokestatic 1145	kawa/lib/uniform:u16vectorRef	(Lgnu/lists/U16Vector;I)I
    //   380: invokestatic 1059	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   383: areturn
    //   384: aload_2
    //   385: checkcast 864	java/lang/Number
    //   388: invokevirtual 1049	java/lang/Number:intValue	()I
    //   391: istore 7
    //   393: aload_3
    //   394: checkcast 864	java/lang/Number
    //   397: invokevirtual 1049	java/lang/Number:intValue	()I
    //   400: istore 8
    //   402: iload 7
    //   404: iload 8
    //   406: invokestatic 824	kawa/lib/uniform:makeS32vector	(II)Lgnu/lists/S32Vector;
    //   409: areturn
    //   410: aload_2
    //   411: checkcast 751	gnu/lists/S32Vector
    //   414: astore_1
    //   415: aload_3
    //   416: checkcast 864	java/lang/Number
    //   419: invokevirtual 1049	java/lang/Number:intValue	()I
    //   422: istore 7
    //   424: aload_1
    //   425: iload 7
    //   427: invokestatic 1147	kawa/lib/uniform:s32vectorRef	(Lgnu/lists/S32Vector;I)I
    //   430: invokestatic 1059	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   433: areturn
    //   434: aload_2
    //   435: checkcast 864	java/lang/Number
    //   438: invokevirtual 1049	java/lang/Number:intValue	()I
    //   441: istore 7
    //   443: aload_3
    //   444: checkcast 864	java/lang/Number
    //   447: invokevirtual 868	java/lang/Number:longValue	()J
    //   450: lstore 9
    //   452: iload 7
    //   454: lload 9
    //   456: invokestatic 854	kawa/lib/uniform:makeU32vector	(IJ)Lgnu/lists/U32Vector;
    //   459: areturn
    //   460: aload_2
    //   461: checkcast 763	gnu/lists/U32Vector
    //   464: astore_1
    //   465: aload_3
    //   466: checkcast 864	java/lang/Number
    //   469: invokevirtual 1049	java/lang/Number:intValue	()I
    //   472: istore 7
    //   474: aload_1
    //   475: iload 7
    //   477: invokestatic 1149	kawa/lib/uniform:u32vectorRef	(Lgnu/lists/U32Vector;I)J
    //   480: invokestatic 1154	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   483: areturn
    //   484: aload_2
    //   485: checkcast 864	java/lang/Number
    //   488: invokevirtual 1049	java/lang/Number:intValue	()I
    //   491: istore 7
    //   493: aload_3
    //   494: checkcast 864	java/lang/Number
    //   497: invokevirtual 868	java/lang/Number:longValue	()J
    //   500: lstore 9
    //   502: iload 7
    //   504: lload 9
    //   506: invokestatic 832	kawa/lib/uniform:makeS64vector	(IJ)Lgnu/lists/S64Vector;
    //   509: areturn
    //   510: aload_2
    //   511: checkcast 754	gnu/lists/S64Vector
    //   514: astore_1
    //   515: aload_3
    //   516: checkcast 864	java/lang/Number
    //   519: invokevirtual 1049	java/lang/Number:intValue	()I
    //   522: istore 7
    //   524: aload_1
    //   525: iload 7
    //   527: invokestatic 1156	kawa/lib/uniform:s64vectorRef	(Lgnu/lists/S64Vector;I)J
    //   530: invokestatic 1154	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   533: areturn
    //   534: aload_2
    //   535: checkcast 864	java/lang/Number
    //   538: invokevirtual 1049	java/lang/Number:intValue	()I
    //   541: istore 7
    //   543: aload_3
    //   544: invokestatic 1007	gnu/kawa/lispexpr/LangObjType:coerceIntNum	(Ljava/lang/Object;)Lgnu/math/IntNum;
    //   547: astore_1
    //   548: iload 7
    //   550: aload_1
    //   551: invokestatic 860	kawa/lib/uniform:makeU64vector	(ILgnu/math/IntNum;)Lgnu/lists/U64Vector;
    //   554: areturn
    //   555: aload_2
    //   556: checkcast 766	gnu/lists/U64Vector
    //   559: astore_1
    //   560: aload_3
    //   561: checkcast 864	java/lang/Number
    //   564: invokevirtual 1049	java/lang/Number:intValue	()I
    //   567: istore 7
    //   569: aload_1
    //   570: iload 7
    //   572: invokestatic 1158	kawa/lib/uniform:u64vectorRef	(Lgnu/lists/U64Vector;I)Lgnu/math/IntNum;
    //   575: areturn
    //   576: aload_2
    //   577: checkcast 864	java/lang/Number
    //   580: invokevirtual 1049	java/lang/Number:intValue	()I
    //   583: istore 7
    //   585: aload_3
    //   586: checkcast 864	java/lang/Number
    //   589: invokevirtual 1162	java/lang/Number:floatValue	()F
    //   592: fstore 6
    //   594: iload 7
    //   596: fload 6
    //   598: invokestatic 802	kawa/lib/uniform:makeF32vector	(IF)Lgnu/lists/F32Vector;
    //   601: areturn
    //   602: aload_2
    //   603: checkcast 701	gnu/lists/F32Vector
    //   606: astore_1
    //   607: aload_3
    //   608: checkcast 864	java/lang/Number
    //   611: invokevirtual 1049	java/lang/Number:intValue	()I
    //   614: istore 7
    //   616: aload_1
    //   617: iload 7
    //   619: invokestatic 1164	kawa/lib/uniform:f32vectorRef	(Lgnu/lists/F32Vector;I)F
    //   622: invokestatic 1169	java/lang/Float:valueOf	(F)Ljava/lang/Float;
    //   625: areturn
    //   626: aload_2
    //   627: checkcast 864	java/lang/Number
    //   630: invokevirtual 1049	java/lang/Number:intValue	()I
    //   633: istore 7
    //   635: aload_3
    //   636: checkcast 864	java/lang/Number
    //   639: invokevirtual 1173	java/lang/Number:doubleValue	()D
    //   642: dstore 4
    //   644: iload 7
    //   646: dload 4
    //   648: invokestatic 809	kawa/lib/uniform:makeF64vector	(ID)Lgnu/lists/F64Vector;
    //   651: areturn
    //   652: aload_2
    //   653: checkcast 729	gnu/lists/F64Vector
    //   656: astore_1
    //   657: aload_3
    //   658: checkcast 864	java/lang/Number
    //   661: invokevirtual 1049	java/lang/Number:intValue	()I
    //   664: istore 7
    //   666: aload_1
    //   667: iload 7
    //   669: invokestatic 1175	kawa/lib/uniform:f64vectorRef	(Lgnu/lists/F64Vector;I)D
    //   672: invokestatic 1180	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   675: areturn
    //   676: astore_1
    //   677: new 871	gnu/mapping/WrongType
    //   680: dup
    //   681: aload_1
    //   682: ldc_w 486
    //   685: iconst_1
    //   686: aload_2
    //   687: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   690: athrow
    //   691: astore_1
    //   692: new 871	gnu/mapping/WrongType
    //   695: dup
    //   696: aload_1
    //   697: ldc_w 486
    //   700: iconst_2
    //   701: aload_3
    //   702: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   705: athrow
    //   706: astore_1
    //   707: new 871	gnu/mapping/WrongType
    //   710: dup
    //   711: aload_1
    //   712: ldc_w 475
    //   715: iconst_1
    //   716: aload_2
    //   717: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   720: athrow
    //   721: astore_1
    //   722: new 871	gnu/mapping/WrongType
    //   725: dup
    //   726: aload_1
    //   727: ldc_w 475
    //   730: iconst_2
    //   731: aload_3
    //   732: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   735: athrow
    //   736: astore_1
    //   737: new 871	gnu/mapping/WrongType
    //   740: dup
    //   741: aload_1
    //   742: ldc_w 455
    //   745: iconst_1
    //   746: aload_2
    //   747: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   750: athrow
    //   751: astore_1
    //   752: new 871	gnu/mapping/WrongType
    //   755: dup
    //   756: aload_1
    //   757: ldc_w 455
    //   760: iconst_2
    //   761: aload_3
    //   762: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   765: athrow
    //   766: astore_1
    //   767: new 871	gnu/mapping/WrongType
    //   770: dup
    //   771: aload_1
    //   772: ldc_w 444
    //   775: iconst_1
    //   776: aload_2
    //   777: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   780: athrow
    //   781: astore_1
    //   782: new 871	gnu/mapping/WrongType
    //   785: dup
    //   786: aload_1
    //   787: ldc_w 444
    //   790: iconst_2
    //   791: aload_3
    //   792: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   795: athrow
    //   796: astore_1
    //   797: new 871	gnu/mapping/WrongType
    //   800: dup
    //   801: aload_1
    //   802: ldc_w 424
    //   805: iconst_1
    //   806: aload_2
    //   807: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   810: athrow
    //   811: astore_1
    //   812: new 871	gnu/mapping/WrongType
    //   815: dup
    //   816: aload_1
    //   817: ldc_w 424
    //   820: iconst_2
    //   821: aload_3
    //   822: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   825: athrow
    //   826: astore_1
    //   827: new 871	gnu/mapping/WrongType
    //   830: dup
    //   831: aload_1
    //   832: ldc_w 413
    //   835: iconst_1
    //   836: aload_2
    //   837: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   840: athrow
    //   841: astore_1
    //   842: new 871	gnu/mapping/WrongType
    //   845: dup
    //   846: aload_1
    //   847: ldc_w 413
    //   850: iconst_2
    //   851: aload_3
    //   852: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   855: athrow
    //   856: astore_1
    //   857: new 871	gnu/mapping/WrongType
    //   860: dup
    //   861: aload_1
    //   862: ldc_w 393
    //   865: iconst_1
    //   866: aload_2
    //   867: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   870: athrow
    //   871: astore_1
    //   872: new 871	gnu/mapping/WrongType
    //   875: dup
    //   876: aload_1
    //   877: ldc_w 393
    //   880: iconst_2
    //   881: aload_3
    //   882: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   885: athrow
    //   886: astore_1
    //   887: new 871	gnu/mapping/WrongType
    //   890: dup
    //   891: aload_1
    //   892: ldc_w 382
    //   895: iconst_1
    //   896: aload_2
    //   897: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   900: athrow
    //   901: astore_1
    //   902: new 871	gnu/mapping/WrongType
    //   905: dup
    //   906: aload_1
    //   907: ldc_w 382
    //   910: iconst_2
    //   911: aload_3
    //   912: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   915: athrow
    //   916: astore_1
    //   917: new 871	gnu/mapping/WrongType
    //   920: dup
    //   921: aload_1
    //   922: ldc_w 362
    //   925: iconst_1
    //   926: aload_2
    //   927: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   930: athrow
    //   931: astore_1
    //   932: new 871	gnu/mapping/WrongType
    //   935: dup
    //   936: aload_1
    //   937: ldc_w 362
    //   940: iconst_2
    //   941: aload_3
    //   942: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   945: athrow
    //   946: astore_1
    //   947: new 871	gnu/mapping/WrongType
    //   950: dup
    //   951: aload_1
    //   952: ldc_w 351
    //   955: iconst_1
    //   956: aload_2
    //   957: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   960: athrow
    //   961: astore_1
    //   962: new 871	gnu/mapping/WrongType
    //   965: dup
    //   966: aload_1
    //   967: ldc_w 351
    //   970: iconst_2
    //   971: aload_3
    //   972: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   975: athrow
    //   976: astore_1
    //   977: new 871	gnu/mapping/WrongType
    //   980: dup
    //   981: aload_1
    //   982: ldc_w 331
    //   985: iconst_1
    //   986: aload_2
    //   987: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   990: athrow
    //   991: astore_1
    //   992: new 871	gnu/mapping/WrongType
    //   995: dup
    //   996: aload_1
    //   997: ldc_w 331
    //   1000: iconst_2
    //   1001: aload_3
    //   1002: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1005: athrow
    //   1006: astore_1
    //   1007: new 871	gnu/mapping/WrongType
    //   1010: dup
    //   1011: aload_1
    //   1012: ldc_w 320
    //   1015: iconst_1
    //   1016: aload_2
    //   1017: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1020: athrow
    //   1021: astore_1
    //   1022: new 871	gnu/mapping/WrongType
    //   1025: dup
    //   1026: aload_1
    //   1027: ldc_w 320
    //   1030: iconst_2
    //   1031: aload_3
    //   1032: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1035: athrow
    //   1036: astore_1
    //   1037: new 871	gnu/mapping/WrongType
    //   1040: dup
    //   1041: aload_1
    //   1042: ldc_w 300
    //   1045: iconst_1
    //   1046: aload_2
    //   1047: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1050: athrow
    //   1051: astore_1
    //   1052: new 871	gnu/mapping/WrongType
    //   1055: dup
    //   1056: aload_1
    //   1057: ldc_w 300
    //   1060: iconst_2
    //   1061: aload_3
    //   1062: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1065: athrow
    //   1066: astore_1
    //   1067: new 871	gnu/mapping/WrongType
    //   1070: dup
    //   1071: aload_1
    //   1072: ldc_w 289
    //   1075: iconst_1
    //   1076: aload_2
    //   1077: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1080: athrow
    //   1081: astore_1
    //   1082: new 871	gnu/mapping/WrongType
    //   1085: dup
    //   1086: aload_1
    //   1087: ldc_w 289
    //   1090: iconst_2
    //   1091: aload_3
    //   1092: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1095: athrow
    //   1096: astore_1
    //   1097: new 871	gnu/mapping/WrongType
    //   1100: dup
    //   1101: aload_1
    //   1102: ldc_w 269
    //   1105: iconst_1
    //   1106: aload_2
    //   1107: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1110: athrow
    //   1111: astore_1
    //   1112: new 871	gnu/mapping/WrongType
    //   1115: dup
    //   1116: aload_1
    //   1117: ldc_w 269
    //   1120: iconst_2
    //   1121: aload_3
    //   1122: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1125: athrow
    //   1126: astore_1
    //   1127: new 871	gnu/mapping/WrongType
    //   1130: dup
    //   1131: aload_1
    //   1132: ldc_w 258
    //   1135: iconst_1
    //   1136: aload_2
    //   1137: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1140: athrow
    //   1141: astore_1
    //   1142: new 871	gnu/mapping/WrongType
    //   1145: dup
    //   1146: aload_1
    //   1147: ldc_w 258
    //   1150: iconst_2
    //   1151: aload_3
    //   1152: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1155: athrow
    //   1156: astore_1
    //   1157: new 871	gnu/mapping/WrongType
    //   1160: dup
    //   1161: aload_1
    //   1162: ldc -18
    //   1164: iconst_1
    //   1165: aload_2
    //   1166: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1169: athrow
    //   1170: astore_1
    //   1171: new 871	gnu/mapping/WrongType
    //   1174: dup
    //   1175: aload_1
    //   1176: ldc -18
    //   1178: iconst_2
    //   1179: aload_3
    //   1180: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1183: athrow
    //   1184: astore_1
    //   1185: new 871	gnu/mapping/WrongType
    //   1188: dup
    //   1189: aload_1
    //   1190: ldc -29
    //   1192: iconst_1
    //   1193: aload_2
    //   1194: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1197: athrow
    //   1198: astore_1
    //   1199: new 871	gnu/mapping/WrongType
    //   1202: dup
    //   1203: aload_1
    //   1204: ldc -29
    //   1206: iconst_2
    //   1207: aload_3
    //   1208: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1211: athrow
    //   1212: astore_1
    //   1213: new 871	gnu/mapping/WrongType
    //   1216: dup
    //   1217: aload_1
    //   1218: ldc -49
    //   1220: iconst_1
    //   1221: aload_2
    //   1222: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1225: athrow
    //   1226: astore_1
    //   1227: new 871	gnu/mapping/WrongType
    //   1230: dup
    //   1231: aload_1
    //   1232: ldc -49
    //   1234: iconst_2
    //   1235: aload_3
    //   1236: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1239: athrow
    //   1240: astore_1
    //   1241: new 871	gnu/mapping/WrongType
    //   1244: dup
    //   1245: aload_1
    //   1246: ldc -60
    //   1248: iconst_1
    //   1249: aload_2
    //   1250: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1253: athrow
    //   1254: astore_1
    //   1255: new 871	gnu/mapping/WrongType
    //   1258: dup
    //   1259: aload_1
    //   1260: ldc -60
    //   1262: iconst_2
    //   1263: aload_3
    //   1264: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   1267: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1268	0	this	uniform
    //   0	1268	1	paramModuleMethod	ModuleMethod
    //   0	1268	2	paramObject1	Object
    //   0	1268	3	paramObject2	Object
    //   642	5	4	d	double
    //   592	5	6	f	float
    //   191	477	7	i	int
    //   200	205	8	j	int
    //   450	55	9	l	long
    // Exception table:
    //   from	to	target	type
    //   184	193	676	java/lang/ClassCastException
    //   193	202	691	java/lang/ClassCastException
    //   210	215	706	java/lang/ClassCastException
    //   215	224	721	java/lang/ClassCastException
    //   234	243	736	java/lang/ClassCastException
    //   243	252	751	java/lang/ClassCastException
    //   260	265	766	java/lang/ClassCastException
    //   265	274	781	java/lang/ClassCastException
    //   284	293	796	java/lang/ClassCastException
    //   293	302	811	java/lang/ClassCastException
    //   310	315	826	java/lang/ClassCastException
    //   315	324	841	java/lang/ClassCastException
    //   334	343	856	java/lang/ClassCastException
    //   343	352	871	java/lang/ClassCastException
    //   360	365	886	java/lang/ClassCastException
    //   365	374	901	java/lang/ClassCastException
    //   384	393	916	java/lang/ClassCastException
    //   393	402	931	java/lang/ClassCastException
    //   410	415	946	java/lang/ClassCastException
    //   415	424	961	java/lang/ClassCastException
    //   434	443	976	java/lang/ClassCastException
    //   443	452	991	java/lang/ClassCastException
    //   460	465	1006	java/lang/ClassCastException
    //   465	474	1021	java/lang/ClassCastException
    //   484	493	1036	java/lang/ClassCastException
    //   493	502	1051	java/lang/ClassCastException
    //   510	515	1066	java/lang/ClassCastException
    //   515	524	1081	java/lang/ClassCastException
    //   534	543	1096	java/lang/ClassCastException
    //   543	548	1111	java/lang/ClassCastException
    //   555	560	1126	java/lang/ClassCastException
    //   560	569	1141	java/lang/ClassCastException
    //   576	585	1156	java/lang/ClassCastException
    //   585	594	1170	java/lang/ClassCastException
    //   602	607	1184	java/lang/ClassCastException
    //   607	616	1198	java/lang/ClassCastException
    //   626	635	1212	java/lang/ClassCastException
    //   635	644	1226	java/lang/ClassCastException
    //   652	657	1240	java/lang/ClassCastException
    //   657	666	1254	java/lang/ClassCastException
  }
  
  /* Error */
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 1033	gnu/expr/ModuleMethod:selector	I
    //   4: lookupswitch	default:+92->96, 7:+102->106, 16:+138->142, 25:+174->178, 34:+210->214, 43:+246->250, 52:+282->286, 61:+318->322, 70:+354->358, 79:+385->389, 88:+421->425
    //   96: aload_0
    //   97: aload_1
    //   98: aload_2
    //   99: aload_3
    //   100: aload 4
    //   102: invokespecial 1184	gnu/expr/ModuleBody:apply3	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   105: areturn
    //   106: aload_2
    //   107: checkcast 757	gnu/lists/S8Vector
    //   110: astore_1
    //   111: aload_3
    //   112: checkcast 864	java/lang/Number
    //   115: invokevirtual 1049	java/lang/Number:intValue	()I
    //   118: istore 8
    //   120: aload 4
    //   122: checkcast 864	java/lang/Number
    //   125: invokevirtual 1049	java/lang/Number:intValue	()I
    //   128: istore 9
    //   130: aload_1
    //   131: iload 8
    //   133: iload 9
    //   135: invokestatic 1186	kawa/lib/uniform:s8vectorSet$Ex	(Lgnu/lists/S8Vector;II)V
    //   138: getstatic 1192	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   141: areturn
    //   142: aload_2
    //   143: checkcast 769	gnu/lists/U8Vector
    //   146: astore_1
    //   147: aload_3
    //   148: checkcast 864	java/lang/Number
    //   151: invokevirtual 1049	java/lang/Number:intValue	()I
    //   154: istore 8
    //   156: aload 4
    //   158: checkcast 864	java/lang/Number
    //   161: invokevirtual 1049	java/lang/Number:intValue	()I
    //   164: istore 9
    //   166: aload_1
    //   167: iload 8
    //   169: iload 9
    //   171: invokestatic 1194	kawa/lib/uniform:u8vectorSet$Ex	(Lgnu/lists/U8Vector;II)V
    //   174: getstatic 1192	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   177: areturn
    //   178: aload_2
    //   179: checkcast 748	gnu/lists/S16Vector
    //   182: astore_1
    //   183: aload_3
    //   184: checkcast 864	java/lang/Number
    //   187: invokevirtual 1049	java/lang/Number:intValue	()I
    //   190: istore 8
    //   192: aload 4
    //   194: checkcast 864	java/lang/Number
    //   197: invokevirtual 1049	java/lang/Number:intValue	()I
    //   200: istore 9
    //   202: aload_1
    //   203: iload 8
    //   205: iload 9
    //   207: invokestatic 1196	kawa/lib/uniform:s16vectorSet$Ex	(Lgnu/lists/S16Vector;II)V
    //   210: getstatic 1192	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   213: areturn
    //   214: aload_2
    //   215: checkcast 760	gnu/lists/U16Vector
    //   218: astore_1
    //   219: aload_3
    //   220: checkcast 864	java/lang/Number
    //   223: invokevirtual 1049	java/lang/Number:intValue	()I
    //   226: istore 8
    //   228: aload 4
    //   230: checkcast 864	java/lang/Number
    //   233: invokevirtual 1049	java/lang/Number:intValue	()I
    //   236: istore 9
    //   238: aload_1
    //   239: iload 8
    //   241: iload 9
    //   243: invokestatic 1198	kawa/lib/uniform:u16vectorSet$Ex	(Lgnu/lists/U16Vector;II)V
    //   246: getstatic 1192	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   249: areturn
    //   250: aload_2
    //   251: checkcast 751	gnu/lists/S32Vector
    //   254: astore_1
    //   255: aload_3
    //   256: checkcast 864	java/lang/Number
    //   259: invokevirtual 1049	java/lang/Number:intValue	()I
    //   262: istore 8
    //   264: aload 4
    //   266: checkcast 864	java/lang/Number
    //   269: invokevirtual 1049	java/lang/Number:intValue	()I
    //   272: istore 9
    //   274: aload_1
    //   275: iload 8
    //   277: iload 9
    //   279: invokestatic 1200	kawa/lib/uniform:s32vectorSet$Ex	(Lgnu/lists/S32Vector;II)V
    //   282: getstatic 1192	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   285: areturn
    //   286: aload_2
    //   287: checkcast 763	gnu/lists/U32Vector
    //   290: astore_1
    //   291: aload_3
    //   292: checkcast 864	java/lang/Number
    //   295: invokevirtual 1049	java/lang/Number:intValue	()I
    //   298: istore 8
    //   300: aload 4
    //   302: checkcast 864	java/lang/Number
    //   305: invokevirtual 868	java/lang/Number:longValue	()J
    //   308: lstore 10
    //   310: aload_1
    //   311: iload 8
    //   313: lload 10
    //   315: invokestatic 1202	kawa/lib/uniform:u32vectorSet$Ex	(Lgnu/lists/U32Vector;IJ)V
    //   318: getstatic 1192	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   321: areturn
    //   322: aload_2
    //   323: checkcast 754	gnu/lists/S64Vector
    //   326: astore_1
    //   327: aload_3
    //   328: checkcast 864	java/lang/Number
    //   331: invokevirtual 1049	java/lang/Number:intValue	()I
    //   334: istore 8
    //   336: aload 4
    //   338: checkcast 864	java/lang/Number
    //   341: invokevirtual 868	java/lang/Number:longValue	()J
    //   344: lstore 10
    //   346: aload_1
    //   347: iload 8
    //   349: lload 10
    //   351: invokestatic 1204	kawa/lib/uniform:s64vectorSet$Ex	(Lgnu/lists/S64Vector;IJ)V
    //   354: getstatic 1192	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   357: areturn
    //   358: aload_2
    //   359: checkcast 766	gnu/lists/U64Vector
    //   362: astore_1
    //   363: aload_3
    //   364: checkcast 864	java/lang/Number
    //   367: invokevirtual 1049	java/lang/Number:intValue	()I
    //   370: istore 8
    //   372: aload 4
    //   374: invokestatic 1007	gnu/kawa/lispexpr/LangObjType:coerceIntNum	(Ljava/lang/Object;)Lgnu/math/IntNum;
    //   377: astore_2
    //   378: aload_1
    //   379: iload 8
    //   381: aload_2
    //   382: invokestatic 1206	kawa/lib/uniform:u64vectorSet$Ex	(Lgnu/lists/U64Vector;ILgnu/math/IntNum;)V
    //   385: getstatic 1192	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   388: areturn
    //   389: aload_2
    //   390: checkcast 701	gnu/lists/F32Vector
    //   393: astore_1
    //   394: aload_3
    //   395: checkcast 864	java/lang/Number
    //   398: invokevirtual 1049	java/lang/Number:intValue	()I
    //   401: istore 8
    //   403: aload 4
    //   405: checkcast 864	java/lang/Number
    //   408: invokevirtual 1162	java/lang/Number:floatValue	()F
    //   411: fstore 7
    //   413: aload_1
    //   414: iload 8
    //   416: fload 7
    //   418: invokestatic 1208	kawa/lib/uniform:f32vectorSet$Ex	(Lgnu/lists/F32Vector;IF)V
    //   421: getstatic 1192	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   424: areturn
    //   425: aload_2
    //   426: checkcast 729	gnu/lists/F64Vector
    //   429: astore_1
    //   430: aload_3
    //   431: checkcast 864	java/lang/Number
    //   434: invokevirtual 1049	java/lang/Number:intValue	()I
    //   437: istore 8
    //   439: aload 4
    //   441: checkcast 864	java/lang/Number
    //   444: invokevirtual 1173	java/lang/Number:doubleValue	()D
    //   447: dstore 5
    //   449: aload_1
    //   450: iload 8
    //   452: dload 5
    //   454: invokestatic 1210	kawa/lib/uniform:f64vectorSet$Ex	(Lgnu/lists/F64Vector;ID)V
    //   457: getstatic 1192	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   460: areturn
    //   461: astore_1
    //   462: new 871	gnu/mapping/WrongType
    //   465: dup
    //   466: aload_1
    //   467: ldc_w 471
    //   470: iconst_1
    //   471: aload_2
    //   472: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   475: athrow
    //   476: astore_1
    //   477: new 871	gnu/mapping/WrongType
    //   480: dup
    //   481: aload_1
    //   482: ldc_w 471
    //   485: iconst_2
    //   486: aload_3
    //   487: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   490: athrow
    //   491: astore_1
    //   492: new 871	gnu/mapping/WrongType
    //   495: dup
    //   496: aload_1
    //   497: ldc_w 471
    //   500: iconst_3
    //   501: aload 4
    //   503: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   506: athrow
    //   507: astore_1
    //   508: new 871	gnu/mapping/WrongType
    //   511: dup
    //   512: aload_1
    //   513: ldc_w 440
    //   516: iconst_1
    //   517: aload_2
    //   518: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   521: athrow
    //   522: astore_1
    //   523: new 871	gnu/mapping/WrongType
    //   526: dup
    //   527: aload_1
    //   528: ldc_w 440
    //   531: iconst_2
    //   532: aload_3
    //   533: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   536: athrow
    //   537: astore_1
    //   538: new 871	gnu/mapping/WrongType
    //   541: dup
    //   542: aload_1
    //   543: ldc_w 440
    //   546: iconst_3
    //   547: aload 4
    //   549: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   552: athrow
    //   553: astore_1
    //   554: new 871	gnu/mapping/WrongType
    //   557: dup
    //   558: aload_1
    //   559: ldc_w 409
    //   562: iconst_1
    //   563: aload_2
    //   564: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   567: athrow
    //   568: astore_1
    //   569: new 871	gnu/mapping/WrongType
    //   572: dup
    //   573: aload_1
    //   574: ldc_w 409
    //   577: iconst_2
    //   578: aload_3
    //   579: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   582: athrow
    //   583: astore_1
    //   584: new 871	gnu/mapping/WrongType
    //   587: dup
    //   588: aload_1
    //   589: ldc_w 409
    //   592: iconst_3
    //   593: aload 4
    //   595: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   598: athrow
    //   599: astore_1
    //   600: new 871	gnu/mapping/WrongType
    //   603: dup
    //   604: aload_1
    //   605: ldc_w 378
    //   608: iconst_1
    //   609: aload_2
    //   610: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   613: athrow
    //   614: astore_1
    //   615: new 871	gnu/mapping/WrongType
    //   618: dup
    //   619: aload_1
    //   620: ldc_w 378
    //   623: iconst_2
    //   624: aload_3
    //   625: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   628: athrow
    //   629: astore_1
    //   630: new 871	gnu/mapping/WrongType
    //   633: dup
    //   634: aload_1
    //   635: ldc_w 378
    //   638: iconst_3
    //   639: aload 4
    //   641: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   644: athrow
    //   645: astore_1
    //   646: new 871	gnu/mapping/WrongType
    //   649: dup
    //   650: aload_1
    //   651: ldc_w 347
    //   654: iconst_1
    //   655: aload_2
    //   656: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   659: athrow
    //   660: astore_1
    //   661: new 871	gnu/mapping/WrongType
    //   664: dup
    //   665: aload_1
    //   666: ldc_w 347
    //   669: iconst_2
    //   670: aload_3
    //   671: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   674: athrow
    //   675: astore_1
    //   676: new 871	gnu/mapping/WrongType
    //   679: dup
    //   680: aload_1
    //   681: ldc_w 347
    //   684: iconst_3
    //   685: aload 4
    //   687: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   690: athrow
    //   691: astore_1
    //   692: new 871	gnu/mapping/WrongType
    //   695: dup
    //   696: aload_1
    //   697: ldc_w 316
    //   700: iconst_1
    //   701: aload_2
    //   702: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   705: athrow
    //   706: astore_1
    //   707: new 871	gnu/mapping/WrongType
    //   710: dup
    //   711: aload_1
    //   712: ldc_w 316
    //   715: iconst_2
    //   716: aload_3
    //   717: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   720: athrow
    //   721: astore_1
    //   722: new 871	gnu/mapping/WrongType
    //   725: dup
    //   726: aload_1
    //   727: ldc_w 316
    //   730: iconst_3
    //   731: aload 4
    //   733: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   736: athrow
    //   737: astore_1
    //   738: new 871	gnu/mapping/WrongType
    //   741: dup
    //   742: aload_1
    //   743: ldc_w 285
    //   746: iconst_1
    //   747: aload_2
    //   748: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   751: athrow
    //   752: astore_1
    //   753: new 871	gnu/mapping/WrongType
    //   756: dup
    //   757: aload_1
    //   758: ldc_w 285
    //   761: iconst_2
    //   762: aload_3
    //   763: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   766: athrow
    //   767: astore_1
    //   768: new 871	gnu/mapping/WrongType
    //   771: dup
    //   772: aload_1
    //   773: ldc_w 285
    //   776: iconst_3
    //   777: aload 4
    //   779: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   782: athrow
    //   783: astore_1
    //   784: new 871	gnu/mapping/WrongType
    //   787: dup
    //   788: aload_1
    //   789: ldc -2
    //   791: iconst_1
    //   792: aload_2
    //   793: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   796: athrow
    //   797: astore_1
    //   798: new 871	gnu/mapping/WrongType
    //   801: dup
    //   802: aload_1
    //   803: ldc -2
    //   805: iconst_2
    //   806: aload_3
    //   807: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   810: athrow
    //   811: astore_1
    //   812: new 871	gnu/mapping/WrongType
    //   815: dup
    //   816: aload_1
    //   817: ldc -2
    //   819: iconst_3
    //   820: aload 4
    //   822: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   825: athrow
    //   826: astore_1
    //   827: new 871	gnu/mapping/WrongType
    //   830: dup
    //   831: aload_1
    //   832: ldc -33
    //   834: iconst_1
    //   835: aload_2
    //   836: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   839: athrow
    //   840: astore_1
    //   841: new 871	gnu/mapping/WrongType
    //   844: dup
    //   845: aload_1
    //   846: ldc -33
    //   848: iconst_2
    //   849: aload_3
    //   850: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   853: athrow
    //   854: astore_1
    //   855: new 871	gnu/mapping/WrongType
    //   858: dup
    //   859: aload_1
    //   860: ldc -33
    //   862: iconst_3
    //   863: aload 4
    //   865: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   868: athrow
    //   869: astore_1
    //   870: new 871	gnu/mapping/WrongType
    //   873: dup
    //   874: aload_1
    //   875: ldc -64
    //   877: iconst_1
    //   878: aload_2
    //   879: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   882: athrow
    //   883: astore_1
    //   884: new 871	gnu/mapping/WrongType
    //   887: dup
    //   888: aload_1
    //   889: ldc -64
    //   891: iconst_2
    //   892: aload_3
    //   893: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   896: athrow
    //   897: astore_1
    //   898: new 871	gnu/mapping/WrongType
    //   901: dup
    //   902: aload_1
    //   903: ldc -64
    //   905: iconst_3
    //   906: aload 4
    //   908: invokespecial 876	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   911: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	912	0	this	uniform
    //   0	912	1	paramModuleMethod	ModuleMethod
    //   0	912	2	paramObject1	Object
    //   0	912	3	paramObject2	Object
    //   0	912	4	paramObject3	Object
    //   447	6	5	d	double
    //   411	6	7	f	float
    //   118	333	8	i	int
    //   128	150	9	j	int
    //   308	42	10	l	long
    // Exception table:
    //   from	to	target	type
    //   106	111	461	java/lang/ClassCastException
    //   111	120	476	java/lang/ClassCastException
    //   120	130	491	java/lang/ClassCastException
    //   142	147	507	java/lang/ClassCastException
    //   147	156	522	java/lang/ClassCastException
    //   156	166	537	java/lang/ClassCastException
    //   178	183	553	java/lang/ClassCastException
    //   183	192	568	java/lang/ClassCastException
    //   192	202	583	java/lang/ClassCastException
    //   214	219	599	java/lang/ClassCastException
    //   219	228	614	java/lang/ClassCastException
    //   228	238	629	java/lang/ClassCastException
    //   250	255	645	java/lang/ClassCastException
    //   255	264	660	java/lang/ClassCastException
    //   264	274	675	java/lang/ClassCastException
    //   286	291	691	java/lang/ClassCastException
    //   291	300	706	java/lang/ClassCastException
    //   300	310	721	java/lang/ClassCastException
    //   322	327	737	java/lang/ClassCastException
    //   327	336	752	java/lang/ClassCastException
    //   336	346	767	java/lang/ClassCastException
    //   358	363	783	java/lang/ClassCastException
    //   363	372	797	java/lang/ClassCastException
    //   372	378	811	java/lang/ClassCastException
    //   389	394	826	java/lang/ClassCastException
    //   394	403	840	java/lang/ClassCastException
    //   403	413	854	java/lang/ClassCastException
    //   425	430	869	java/lang/ClassCastException
    //   430	439	883	java/lang/ClassCastException
    //   439	449	897	java/lang/ClassCastException
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.applyN(paramModuleMethod, paramArrayOfObject);
    case 4: 
      return s8vector$V(paramArrayOfObject);
    case 13: 
      return u8vector$V(paramArrayOfObject);
    case 22: 
      return s16vector$V(paramArrayOfObject);
    case 31: 
      return u16vector$V(paramArrayOfObject);
    case 40: 
      return s32vector$V(paramArrayOfObject);
    case 49: 
      return u32vector$V(paramArrayOfObject);
    case 58: 
      return s64vector$V(paramArrayOfObject);
    case 67: 
      return u64vector$V(paramArrayOfObject);
    case 76: 
      return f32vector$V(paramArrayOfObject);
    }
    return f64vector$V(paramArrayOfObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 3: 
    case 4: 
    case 6: 
    case 7: 
    case 12: 
    case 13: 
    case 15: 
    case 16: 
    case 21: 
    case 22: 
    case 24: 
    case 25: 
    case 30: 
    case 31: 
    case 33: 
    case 34: 
    case 39: 
    case 40: 
    case 42: 
    case 43: 
    case 48: 
    case 49: 
    case 51: 
    case 52: 
    case 57: 
    case 58: 
    case 60: 
    case 61: 
    case 66: 
    case 67: 
    case 69: 
    case 70: 
    case 75: 
    case 76: 
    case 78: 
    case 79: 
    case 84: 
    case 85: 
    case 87: 
    case 88: 
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 90: 
      if ((paramObject instanceof LList))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 89: 
      if (!(paramObject instanceof F64Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 86: 
      if (!(paramObject instanceof F64Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 83: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 82: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 81: 
      if ((paramObject instanceof LList))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 80: 
      if (!(paramObject instanceof F32Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 77: 
      if (!(paramObject instanceof F32Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 74: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 73: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 72: 
      if ((paramObject instanceof LList))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 71: 
      if (!(paramObject instanceof U64Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 68: 
      if (!(paramObject instanceof U64Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 65: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 64: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 63: 
      if ((paramObject instanceof LList))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 62: 
      if (!(paramObject instanceof S64Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 59: 
      if (!(paramObject instanceof S64Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 56: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 55: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 54: 
      if ((paramObject instanceof LList))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 53: 
      if (!(paramObject instanceof U32Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 50: 
      if (!(paramObject instanceof U32Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 47: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 46: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 45: 
      if ((paramObject instanceof LList))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 44: 
      if (!(paramObject instanceof S32Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 41: 
      if (!(paramObject instanceof S32Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 38: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 37: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 36: 
      if ((paramObject instanceof LList))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 35: 
      if (!(paramObject instanceof U16Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 32: 
      if (!(paramObject instanceof U16Vector)) {
        return -786431;
      }
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
      if ((paramObject instanceof LList))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 26: 
      if (!(paramObject instanceof S16Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 23: 
      if (!(paramObject instanceof S16Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 20: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 19: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 18: 
      if ((paramObject instanceof LList))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 17: 
      if (!(paramObject instanceof U8Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 14: 
      if (!(paramObject instanceof U8Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 11: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 10: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 9: 
      if ((paramObject instanceof LList))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 8: 
      if (!(paramObject instanceof S8Vector)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 5: 
      if (!(paramObject instanceof S8Vector)) {
        return -786431;
      }
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
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    default: 
      i = super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    case 87: 
    case 83: 
    case 78: 
    case 74: 
    case 69: 
    case 65: 
    case 60: 
    case 56: 
    case 51: 
    case 47: 
    case 42: 
    case 38: 
    case 33: 
    case 29: 
    case 24: 
    case 20: 
    case 15: 
    case 11: 
    case 6: 
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
                          return i;
                        } while (!(paramObject1 instanceof F64Vector));
                        paramCallContext.value1 = paramObject1;
                        paramCallContext.value2 = paramObject2;
                        paramCallContext.proc = paramModuleMethod;
                        paramCallContext.pc = 2;
                        return 0;
                        paramCallContext.value1 = paramObject1;
                        paramCallContext.value2 = paramObject2;
                        paramCallContext.proc = paramModuleMethod;
                        paramCallContext.pc = 2;
                        return 0;
                      } while (!(paramObject1 instanceof F32Vector));
                      paramCallContext.value1 = paramObject1;
                      paramCallContext.value2 = paramObject2;
                      paramCallContext.proc = paramModuleMethod;
                      paramCallContext.pc = 2;
                      return 0;
                      paramCallContext.value1 = paramObject1;
                      paramCallContext.value2 = paramObject2;
                      paramCallContext.proc = paramModuleMethod;
                      paramCallContext.pc = 2;
                      return 0;
                    } while (!(paramObject1 instanceof U64Vector));
                    paramCallContext.value1 = paramObject1;
                    paramCallContext.value2 = paramObject2;
                    paramCallContext.proc = paramModuleMethod;
                    paramCallContext.pc = 2;
                    return 0;
                    paramCallContext.value1 = paramObject1;
                    if (IntNum.asIntNumOrNull(paramObject2) != null)
                    {
                      paramCallContext.value2 = paramObject2;
                      paramCallContext.proc = paramModuleMethod;
                      paramCallContext.pc = 2;
                      return 0;
                    }
                    return -786430;
                  } while (!(paramObject1 instanceof S64Vector));
                  paramCallContext.value1 = paramObject1;
                  paramCallContext.value2 = paramObject2;
                  paramCallContext.proc = paramModuleMethod;
                  paramCallContext.pc = 2;
                  return 0;
                  paramCallContext.value1 = paramObject1;
                  paramCallContext.value2 = paramObject2;
                  paramCallContext.proc = paramModuleMethod;
                  paramCallContext.pc = 2;
                  return 0;
                } while (!(paramObject1 instanceof U32Vector));
                paramCallContext.value1 = paramObject1;
                paramCallContext.value2 = paramObject2;
                paramCallContext.proc = paramModuleMethod;
                paramCallContext.pc = 2;
                return 0;
                paramCallContext.value1 = paramObject1;
                paramCallContext.value2 = paramObject2;
                paramCallContext.proc = paramModuleMethod;
                paramCallContext.pc = 2;
                return 0;
              } while (!(paramObject1 instanceof S32Vector));
              paramCallContext.value1 = paramObject1;
              paramCallContext.value2 = paramObject2;
              paramCallContext.proc = paramModuleMethod;
              paramCallContext.pc = 2;
              return 0;
              paramCallContext.value1 = paramObject1;
              paramCallContext.value2 = paramObject2;
              paramCallContext.proc = paramModuleMethod;
              paramCallContext.pc = 2;
              return 0;
            } while (!(paramObject1 instanceof U16Vector));
            paramCallContext.value1 = paramObject1;
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
            paramCallContext.value1 = paramObject1;
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } while (!(paramObject1 instanceof S16Vector));
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } while (!(paramObject1 instanceof U8Vector));
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      } while (!(paramObject1 instanceof S8Vector));
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    }
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    default: 
      i = super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
    }
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
                        return i;
                      } while (!(paramObject1 instanceof F64Vector));
                      paramCallContext.value1 = paramObject1;
                      paramCallContext.value2 = paramObject2;
                      paramCallContext.value3 = paramObject3;
                      paramCallContext.proc = paramModuleMethod;
                      paramCallContext.pc = 3;
                      return 0;
                    } while (!(paramObject1 instanceof F32Vector));
                    paramCallContext.value1 = paramObject1;
                    paramCallContext.value2 = paramObject2;
                    paramCallContext.value3 = paramObject3;
                    paramCallContext.proc = paramModuleMethod;
                    paramCallContext.pc = 3;
                    return 0;
                  } while (!(paramObject1 instanceof U64Vector));
                  paramCallContext.value1 = paramObject1;
                  paramCallContext.value2 = paramObject2;
                  if (IntNum.asIntNumOrNull(paramObject3) != null)
                  {
                    paramCallContext.value3 = paramObject3;
                    paramCallContext.proc = paramModuleMethod;
                    paramCallContext.pc = 3;
                    return 0;
                  }
                  return -786429;
                } while (!(paramObject1 instanceof S64Vector));
                paramCallContext.value1 = paramObject1;
                paramCallContext.value2 = paramObject2;
                paramCallContext.value3 = paramObject3;
                paramCallContext.proc = paramModuleMethod;
                paramCallContext.pc = 3;
                return 0;
              } while (!(paramObject1 instanceof U32Vector));
              paramCallContext.value1 = paramObject1;
              paramCallContext.value2 = paramObject2;
              paramCallContext.value3 = paramObject3;
              paramCallContext.proc = paramModuleMethod;
              paramCallContext.pc = 3;
              return 0;
            } while (!(paramObject1 instanceof S32Vector));
            paramCallContext.value1 = paramObject1;
            paramCallContext.value2 = paramObject2;
            paramCallContext.value3 = paramObject3;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 3;
            return 0;
          } while (!(paramObject1 instanceof U16Vector));
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } while (!(paramObject1 instanceof S16Vector));
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      } while (!(paramObject1 instanceof U8Vector));
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    } while (!(paramObject1 instanceof S8Vector));
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.value3 = paramObject3;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 3;
    return 0;
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
    case 85: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 76: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 67: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 58: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 49: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 40: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 31: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 22: 
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
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\uniform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */