package kawa.lib.rnrs;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.UnicodeUtils;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import gnu.text.Char;
import java.util.Locale;
import kawa.lib.misc;

public class unicode
  extends ModuleBody
{
  public static final unicode $instance;
  static final SimpleSymbol Lit0;
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
  static final SimpleSymbol Lit28 = (SimpleSymbol)new SimpleSymbol("string-normalize-nfkc").readResolve();
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod char$Mnalphabetic$Qu;
  public static final ModuleMethod char$Mnci$Eq$Qu;
  public static final ModuleMethod char$Mnci$Gr$Eq$Qu;
  public static final ModuleMethod char$Mnci$Gr$Qu;
  public static final ModuleMethod char$Mnci$Ls$Eq$Qu;
  public static final ModuleMethod char$Mnci$Ls$Qu;
  public static final ModuleMethod char$Mndowncase;
  public static final ModuleMethod char$Mnfoldcase;
  public static final ModuleMethod char$Mngeneral$Mncategory;
  public static final ModuleMethod char$Mnlower$Mncase$Qu;
  public static final ModuleMethod char$Mnnumeric$Qu;
  public static final ModuleMethod char$Mntitle$Mncase$Qu;
  public static final ModuleMethod char$Mntitlecase;
  public static final ModuleMethod char$Mnupcase;
  public static final ModuleMethod char$Mnupper$Mncase$Qu;
  public static final ModuleMethod char$Mnwhitespace$Qu;
  public static final ModuleMethod string$Mnci$Eq$Qu;
  public static final ModuleMethod string$Mnci$Gr$Eq$Qu;
  public static final ModuleMethod string$Mnci$Gr$Qu;
  public static final ModuleMethod string$Mnci$Ls$Eq$Qu;
  public static final ModuleMethod string$Mnci$Ls$Qu;
  public static final ModuleMethod string$Mndowncase;
  public static final ModuleMethod string$Mnfoldcase;
  public static final ModuleMethod string$Mnnormalize$Mnnfc;
  public static final ModuleMethod string$Mnnormalize$Mnnfd;
  public static final ModuleMethod string$Mnnormalize$Mnnfkc;
  public static final ModuleMethod string$Mnnormalize$Mnnfkd;
  public static final ModuleMethod string$Mntitlecase;
  public static final ModuleMethod string$Mnupcase;
  
  static
  {
    Lit27 = (SimpleSymbol)new SimpleSymbol("string-normalize-nfc").readResolve();
    Lit26 = (SimpleSymbol)new SimpleSymbol("string-normalize-nfkd").readResolve();
    Lit25 = (SimpleSymbol)new SimpleSymbol("string-normalize-nfd").readResolve();
    Lit24 = (SimpleSymbol)new SimpleSymbol("string-ci>=?").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("string-ci<=?").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("string-ci>?").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("string-ci<?").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("string-ci=?").readResolve();
    Lit19 = (SimpleSymbol)new SimpleSymbol("string-foldcase").readResolve();
    Lit18 = (SimpleSymbol)new SimpleSymbol("string-titlecase").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("string-downcase").readResolve();
    Lit16 = (SimpleSymbol)new SimpleSymbol("string-upcase").readResolve();
    Lit15 = (SimpleSymbol)new SimpleSymbol("char-general-category").readResolve();
    Lit14 = (SimpleSymbol)new SimpleSymbol("char-ci>=?").readResolve();
    Lit13 = (SimpleSymbol)new SimpleSymbol("char-ci<=?").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("char-ci>?").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("char-ci<?").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("char-ci=?").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("char-foldcase").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("char-title-case?").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("char-lower-case?").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("char-upper-case?").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("char-whitespace?").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("char-numeric?").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("char-alphabetic?").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("char-titlecase").readResolve();
    Lit1 = (SimpleSymbol)new SimpleSymbol("char-downcase").readResolve();
    Lit0 = (SimpleSymbol)new SimpleSymbol("char-upcase").readResolve();
    $instance = new unicode();
    unicode localunicode = $instance;
    char$Mnupcase = new ModuleMethod(localunicode, 1, Lit0, 4097);
    char$Mndowncase = new ModuleMethod(localunicode, 2, Lit1, 4097);
    char$Mntitlecase = new ModuleMethod(localunicode, 3, Lit2, 4097);
    char$Mnalphabetic$Qu = new ModuleMethod(localunicode, 4, Lit3, 4097);
    char$Mnnumeric$Qu = new ModuleMethod(localunicode, 5, Lit4, 4097);
    char$Mnwhitespace$Qu = new ModuleMethod(localunicode, 6, Lit5, 4097);
    char$Mnupper$Mncase$Qu = new ModuleMethod(localunicode, 7, Lit6, 4097);
    char$Mnlower$Mncase$Qu = new ModuleMethod(localunicode, 8, Lit7, 4097);
    char$Mntitle$Mncase$Qu = new ModuleMethod(localunicode, 9, Lit8, 4097);
    char$Mnfoldcase = new ModuleMethod(localunicode, 10, Lit9, 4097);
    char$Mnci$Eq$Qu = new ModuleMethod(localunicode, 11, Lit10, 8194);
    char$Mnci$Ls$Qu = new ModuleMethod(localunicode, 12, Lit11, 8194);
    char$Mnci$Gr$Qu = new ModuleMethod(localunicode, 13, Lit12, 8194);
    char$Mnci$Ls$Eq$Qu = new ModuleMethod(localunicode, 14, Lit13, 8194);
    char$Mnci$Gr$Eq$Qu = new ModuleMethod(localunicode, 15, Lit14, 8194);
    char$Mngeneral$Mncategory = new ModuleMethod(localunicode, 16, Lit15, 4097);
    string$Mnupcase = new ModuleMethod(localunicode, 17, Lit16, 4097);
    string$Mndowncase = new ModuleMethod(localunicode, 18, Lit17, 4097);
    string$Mntitlecase = new ModuleMethod(localunicode, 19, Lit18, 4097);
    string$Mnfoldcase = new ModuleMethod(localunicode, 20, Lit19, 4097);
    string$Mnci$Eq$Qu = new ModuleMethod(localunicode, 21, Lit20, 8194);
    string$Mnci$Ls$Qu = new ModuleMethod(localunicode, 22, Lit21, 8194);
    string$Mnci$Gr$Qu = new ModuleMethod(localunicode, 23, Lit22, 8194);
    string$Mnci$Ls$Eq$Qu = new ModuleMethod(localunicode, 24, Lit23, 8194);
    string$Mnci$Gr$Eq$Qu = new ModuleMethod(localunicode, 25, Lit24, 8194);
    string$Mnnormalize$Mnnfd = new ModuleMethod(localunicode, 26, Lit25, 4097);
    string$Mnnormalize$Mnnfkd = new ModuleMethod(localunicode, 27, Lit26, 4097);
    string$Mnnormalize$Mnnfc = new ModuleMethod(localunicode, 28, Lit27, 4097);
    string$Mnnormalize$Mnnfkc = new ModuleMethod(localunicode, 29, Lit28, 4097);
    $instance.run();
  }
  
  public unicode()
  {
    ModuleInfo.register(this);
  }
  
  public static Char charDowncase(Char paramChar)
  {
    return Char.make(Character.toLowerCase(paramChar.intValue()));
  }
  
  public static Char charFoldcase(Char paramChar)
  {
    int j = paramChar.intValue();
    int i;
    if (j == 304)
    {
      i = 1;
      if (i == 0) {
        break label29;
      }
      if (i == 0) {
        break label36;
      }
    }
    label29:
    while (j == 305)
    {
      return paramChar;
      i = 0;
      break;
    }
    label36:
    return Char.make(Character.toLowerCase(Character.toUpperCase(j)));
  }
  
  public static Symbol charGeneralCategory(Char paramChar)
  {
    return UnicodeUtils.generalCategory(paramChar.intValue());
  }
  
  public static Char charTitlecase(Char paramChar)
  {
    return Char.make(Character.toTitleCase(paramChar.intValue()));
  }
  
  public static Char charUpcase(Char paramChar)
  {
    return Char.make(Character.toUpperCase(paramChar.intValue()));
  }
  
  public static boolean isCharAlphabetic(Char paramChar)
  {
    return Character.isLetter(paramChar.intValue());
  }
  
  public static boolean isCharCi$Eq(Char paramChar1, Char paramChar2)
  {
    return Character.toUpperCase(paramChar1.intValue()) == Character.toUpperCase(paramChar2.intValue());
  }
  
  public static boolean isCharCi$Gr(Char paramChar1, Char paramChar2)
  {
    return Character.toUpperCase(paramChar1.intValue()) > Character.toUpperCase(paramChar2.intValue());
  }
  
  public static boolean isCharCi$Gr$Eq(Char paramChar1, Char paramChar2)
  {
    return Character.toUpperCase(paramChar1.intValue()) >= Character.toUpperCase(paramChar2.intValue());
  }
  
  public static boolean isCharCi$Ls(Char paramChar1, Char paramChar2)
  {
    return Character.toUpperCase(paramChar1.intValue()) < Character.toUpperCase(paramChar2.intValue());
  }
  
  public static boolean isCharCi$Ls$Eq(Char paramChar1, Char paramChar2)
  {
    return Character.toUpperCase(paramChar1.intValue()) <= Character.toUpperCase(paramChar2.intValue());
  }
  
  public static boolean isCharLowerCase(Char paramChar)
  {
    return Character.isLowerCase(paramChar.intValue());
  }
  
  public static boolean isCharNumeric(Char paramChar)
  {
    return Character.isDigit(paramChar.intValue());
  }
  
  public static boolean isCharTitleCase(Char paramChar)
  {
    return Character.isTitleCase(paramChar.intValue());
  }
  
  public static boolean isCharUpperCase(Char paramChar)
  {
    return Character.isUpperCase(paramChar.intValue());
  }
  
  public static boolean isCharWhitespace(Char paramChar)
  {
    return UnicodeUtils.isWhitespace(paramChar.intValue());
  }
  
  public static boolean isStringCi$Eq(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    return UnicodeUtils.foldCase(paramCharSequence1).equals(UnicodeUtils.foldCase(paramCharSequence2));
  }
  
  public static boolean isStringCi$Gr(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    return UnicodeUtils.foldCase(paramCharSequence1).compareTo(UnicodeUtils.foldCase(paramCharSequence2)) > 0;
  }
  
  public static boolean isStringCi$Gr$Eq(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    return UnicodeUtils.foldCase(paramCharSequence1).compareTo(UnicodeUtils.foldCase(paramCharSequence2)) >= 0;
  }
  
  public static boolean isStringCi$Ls(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    return UnicodeUtils.foldCase(paramCharSequence1).compareTo(UnicodeUtils.foldCase(paramCharSequence2)) < 0;
  }
  
  public static boolean isStringCi$Ls$Eq(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    return UnicodeUtils.foldCase(paramCharSequence1).compareTo(UnicodeUtils.foldCase(paramCharSequence2)) <= 0;
  }
  
  public static CharSequence stringDowncase(CharSequence paramCharSequence)
  {
    return new FString(paramCharSequence.toString().toLowerCase(Locale.ENGLISH));
  }
  
  public static CharSequence stringFoldcase(CharSequence paramCharSequence)
  {
    return new FString(UnicodeUtils.foldCase(paramCharSequence));
  }
  
  public static CharSequence stringNormalizeNfc(CharSequence paramCharSequence)
  {
    return (CharSequence)misc.error$V("unicode string normalization not available", new Object[0]);
  }
  
  public static CharSequence stringNormalizeNfd(CharSequence paramCharSequence)
  {
    return (CharSequence)misc.error$V("unicode string normalization not available", new Object[0]);
  }
  
  public static CharSequence stringNormalizeNfkc(CharSequence paramCharSequence)
  {
    return (CharSequence)misc.error$V("unicode string normalization not available", new Object[0]);
  }
  
  public static CharSequence stringNormalizeNfkd(CharSequence paramCharSequence)
  {
    return (CharSequence)misc.error$V("unicode string normalization not available", new Object[0]);
  }
  
  public static CharSequence stringTitlecase(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {}
    for (paramCharSequence = null;; paramCharSequence = paramCharSequence.toString()) {
      return new FString(UnicodeUtils.capitalize(paramCharSequence));
    }
  }
  
  public static CharSequence stringUpcase(CharSequence paramCharSequence)
  {
    return new FString(paramCharSequence.toString().toUpperCase(Locale.ENGLISH));
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    case 21: 
    case 22: 
    case 23: 
    case 24: 
    case 25: 
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    }
    try
    {
      paramModuleMethod = (Char)paramObject;
      return charUpcase(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      for (;;)
      {
        try
        {
          paramModuleMethod = (Char)paramObject;
          return charDowncase(paramModuleMethod);
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "char-downcase", 1, paramObject);
        }
        try
        {
          paramModuleMethod = (Char)paramObject;
          return charTitlecase(paramModuleMethod);
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "char-titlecase", 1, paramObject);
        }
        try
        {
          paramModuleMethod = (Char)paramObject;
          if (isCharAlphabetic(paramModuleMethod)) {
            return Boolean.TRUE;
          }
          return Boolean.FALSE;
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "char-alphabetic?", 1, paramObject);
        }
        try
        {
          paramModuleMethod = (Char)paramObject;
          if (isCharNumeric(paramModuleMethod)) {
            return Boolean.TRUE;
          }
          return Boolean.FALSE;
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "char-numeric?", 1, paramObject);
        }
        try
        {
          paramModuleMethod = (Char)paramObject;
          if (isCharWhitespace(paramModuleMethod)) {
            return Boolean.TRUE;
          }
          return Boolean.FALSE;
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "char-whitespace?", 1, paramObject);
        }
        try
        {
          paramModuleMethod = (Char)paramObject;
          if (isCharUpperCase(paramModuleMethod)) {
            return Boolean.TRUE;
          }
          return Boolean.FALSE;
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "char-upper-case?", 1, paramObject);
        }
        try
        {
          paramModuleMethod = (Char)paramObject;
          if (isCharLowerCase(paramModuleMethod)) {
            return Boolean.TRUE;
          }
          return Boolean.FALSE;
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "char-lower-case?", 1, paramObject);
        }
        try
        {
          paramModuleMethod = (Char)paramObject;
          if (isCharTitleCase(paramModuleMethod)) {
            return Boolean.TRUE;
          }
          return Boolean.FALSE;
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "char-title-case?", 1, paramObject);
        }
      }
      try
      {
        paramModuleMethod = (Char)paramObject;
        return charFoldcase(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "char-foldcase", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (Char)paramObject;
        return charGeneralCategory(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "char-general-category", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject;
        return stringUpcase(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-upcase", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject;
        return stringDowncase(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-downcase", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject;
        return stringTitlecase(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-titlecase", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject;
        return stringFoldcase(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-foldcase", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject;
        return stringNormalizeNfd(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-normalize-nfd", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject;
        return stringNormalizeNfkd(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-normalize-nfkd", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject;
        return stringNormalizeNfc(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-normalize-nfc", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject;
        return stringNormalizeNfkc(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-normalize-nfkc", 1, paramObject);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "char-upcase", 1, paramObject);
    }
  }
  
  /* Error */
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 413	gnu/expr/ModuleMethod:selector	I
    //   4: tableswitch	default:+76->80, 11:+84->88, 12:+110->114, 13:+136->140, 14:+162->166, 15:+188->192, 16:+76->80, 17:+76->80, 18:+76->80, 19:+76->80, 20:+76->80, 21:+214->218, 22:+240->244, 23:+266->270, 24:+292->296, 25:+318->322
    //   80: aload_0
    //   81: aload_1
    //   82: aload_2
    //   83: aload_3
    //   84: invokespecial 471	gnu/expr/ModuleBody:apply2	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   87: areturn
    //   88: aload_2
    //   89: checkcast 276	gnu/text/Char
    //   92: astore_1
    //   93: aload_3
    //   94: checkcast 276	gnu/text/Char
    //   97: astore_2
    //   98: aload_1
    //   99: aload_2
    //   100: invokestatic 473	kawa/lib/rnrs/unicode:isCharCi$Eq	(Lgnu/text/Char;Lgnu/text/Char;)Z
    //   103: ifeq +7 -> 110
    //   106: getstatic 429	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   109: areturn
    //   110: getstatic 432	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   113: areturn
    //   114: aload_2
    //   115: checkcast 276	gnu/text/Char
    //   118: astore_1
    //   119: aload_3
    //   120: checkcast 276	gnu/text/Char
    //   123: astore_2
    //   124: aload_1
    //   125: aload_2
    //   126: invokestatic 475	kawa/lib/rnrs/unicode:isCharCi$Ls	(Lgnu/text/Char;Lgnu/text/Char;)Z
    //   129: ifeq +7 -> 136
    //   132: getstatic 429	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   135: areturn
    //   136: getstatic 432	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   139: areturn
    //   140: aload_2
    //   141: checkcast 276	gnu/text/Char
    //   144: astore_1
    //   145: aload_3
    //   146: checkcast 276	gnu/text/Char
    //   149: astore_2
    //   150: aload_1
    //   151: aload_2
    //   152: invokestatic 477	kawa/lib/rnrs/unicode:isCharCi$Gr	(Lgnu/text/Char;Lgnu/text/Char;)Z
    //   155: ifeq +7 -> 162
    //   158: getstatic 429	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   161: areturn
    //   162: getstatic 432	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   165: areturn
    //   166: aload_2
    //   167: checkcast 276	gnu/text/Char
    //   170: astore_1
    //   171: aload_3
    //   172: checkcast 276	gnu/text/Char
    //   175: astore_2
    //   176: aload_1
    //   177: aload_2
    //   178: invokestatic 479	kawa/lib/rnrs/unicode:isCharCi$Ls$Eq	(Lgnu/text/Char;Lgnu/text/Char;)Z
    //   181: ifeq +7 -> 188
    //   184: getstatic 429	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   187: areturn
    //   188: getstatic 432	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   191: areturn
    //   192: aload_2
    //   193: checkcast 276	gnu/text/Char
    //   196: astore_1
    //   197: aload_3
    //   198: checkcast 276	gnu/text/Char
    //   201: astore_2
    //   202: aload_1
    //   203: aload_2
    //   204: invokestatic 481	kawa/lib/rnrs/unicode:isCharCi$Gr$Eq	(Lgnu/text/Char;Lgnu/text/Char;)Z
    //   207: ifeq +7 -> 214
    //   210: getstatic 429	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   213: areturn
    //   214: getstatic 432	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   217: areturn
    //   218: aload_2
    //   219: checkcast 365	java/lang/CharSequence
    //   222: astore_1
    //   223: aload_3
    //   224: checkcast 365	java/lang/CharSequence
    //   227: astore_2
    //   228: aload_1
    //   229: aload_2
    //   230: invokestatic 483	kawa/lib/rnrs/unicode:isStringCi$Eq	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   233: ifeq +7 -> 240
    //   236: getstatic 429	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   239: areturn
    //   240: getstatic 432	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   243: areturn
    //   244: aload_2
    //   245: checkcast 365	java/lang/CharSequence
    //   248: astore_1
    //   249: aload_3
    //   250: checkcast 365	java/lang/CharSequence
    //   253: astore_2
    //   254: aload_1
    //   255: aload_2
    //   256: invokestatic 485	kawa/lib/rnrs/unicode:isStringCi$Ls	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   259: ifeq +7 -> 266
    //   262: getstatic 429	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   265: areturn
    //   266: getstatic 432	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   269: areturn
    //   270: aload_2
    //   271: checkcast 365	java/lang/CharSequence
    //   274: astore_1
    //   275: aload_3
    //   276: checkcast 365	java/lang/CharSequence
    //   279: astore_2
    //   280: aload_1
    //   281: aload_2
    //   282: invokestatic 487	kawa/lib/rnrs/unicode:isStringCi$Gr	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   285: ifeq +7 -> 292
    //   288: getstatic 429	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   291: areturn
    //   292: getstatic 432	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   295: areturn
    //   296: aload_2
    //   297: checkcast 365	java/lang/CharSequence
    //   300: astore_1
    //   301: aload_3
    //   302: checkcast 365	java/lang/CharSequence
    //   305: astore_2
    //   306: aload_1
    //   307: aload_2
    //   308: invokestatic 489	kawa/lib/rnrs/unicode:isStringCi$Ls$Eq	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   311: ifeq +7 -> 318
    //   314: getstatic 429	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   317: areturn
    //   318: getstatic 432	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   321: areturn
    //   322: aload_2
    //   323: checkcast 365	java/lang/CharSequence
    //   326: astore_1
    //   327: aload_3
    //   328: checkcast 365	java/lang/CharSequence
    //   331: astore_2
    //   332: aload_1
    //   333: aload_2
    //   334: invokestatic 491	kawa/lib/rnrs/unicode:isStringCi$Gr$Eq	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   337: ifeq +7 -> 344
    //   340: getstatic 429	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   343: areturn
    //   344: getstatic 432	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   347: areturn
    //   348: astore_1
    //   349: new 464	gnu/mapping/WrongType
    //   352: dup
    //   353: aload_1
    //   354: ldc -104
    //   356: iconst_1
    //   357: aload_2
    //   358: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   361: athrow
    //   362: astore_1
    //   363: new 464	gnu/mapping/WrongType
    //   366: dup
    //   367: aload_1
    //   368: ldc -104
    //   370: iconst_2
    //   371: aload_3
    //   372: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   375: athrow
    //   376: astore_1
    //   377: new 464	gnu/mapping/WrongType
    //   380: dup
    //   381: aload_1
    //   382: ldc -108
    //   384: iconst_1
    //   385: aload_2
    //   386: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   389: athrow
    //   390: astore_1
    //   391: new 464	gnu/mapping/WrongType
    //   394: dup
    //   395: aload_1
    //   396: ldc -108
    //   398: iconst_2
    //   399: aload_3
    //   400: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   403: athrow
    //   404: astore_1
    //   405: new 464	gnu/mapping/WrongType
    //   408: dup
    //   409: aload_1
    //   410: ldc -112
    //   412: iconst_1
    //   413: aload_2
    //   414: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   417: athrow
    //   418: astore_1
    //   419: new 464	gnu/mapping/WrongType
    //   422: dup
    //   423: aload_1
    //   424: ldc -112
    //   426: iconst_2
    //   427: aload_3
    //   428: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   431: athrow
    //   432: astore_1
    //   433: new 464	gnu/mapping/WrongType
    //   436: dup
    //   437: aload_1
    //   438: ldc -116
    //   440: iconst_1
    //   441: aload_2
    //   442: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   445: athrow
    //   446: astore_1
    //   447: new 464	gnu/mapping/WrongType
    //   450: dup
    //   451: aload_1
    //   452: ldc -116
    //   454: iconst_2
    //   455: aload_3
    //   456: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   459: athrow
    //   460: astore_1
    //   461: new 464	gnu/mapping/WrongType
    //   464: dup
    //   465: aload_1
    //   466: ldc -120
    //   468: iconst_1
    //   469: aload_2
    //   470: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   473: athrow
    //   474: astore_1
    //   475: new 464	gnu/mapping/WrongType
    //   478: dup
    //   479: aload_1
    //   480: ldc -120
    //   482: iconst_2
    //   483: aload_3
    //   484: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   487: athrow
    //   488: astore_1
    //   489: new 464	gnu/mapping/WrongType
    //   492: dup
    //   493: aload_1
    //   494: ldc 112
    //   496: iconst_1
    //   497: aload_2
    //   498: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   501: athrow
    //   502: astore_1
    //   503: new 464	gnu/mapping/WrongType
    //   506: dup
    //   507: aload_1
    //   508: ldc 112
    //   510: iconst_2
    //   511: aload_3
    //   512: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   515: athrow
    //   516: astore_1
    //   517: new 464	gnu/mapping/WrongType
    //   520: dup
    //   521: aload_1
    //   522: ldc 108
    //   524: iconst_1
    //   525: aload_2
    //   526: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   529: athrow
    //   530: astore_1
    //   531: new 464	gnu/mapping/WrongType
    //   534: dup
    //   535: aload_1
    //   536: ldc 108
    //   538: iconst_2
    //   539: aload_3
    //   540: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   543: athrow
    //   544: astore_1
    //   545: new 464	gnu/mapping/WrongType
    //   548: dup
    //   549: aload_1
    //   550: ldc 104
    //   552: iconst_1
    //   553: aload_2
    //   554: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   557: athrow
    //   558: astore_1
    //   559: new 464	gnu/mapping/WrongType
    //   562: dup
    //   563: aload_1
    //   564: ldc 104
    //   566: iconst_2
    //   567: aload_3
    //   568: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   571: athrow
    //   572: astore_1
    //   573: new 464	gnu/mapping/WrongType
    //   576: dup
    //   577: aload_1
    //   578: ldc 100
    //   580: iconst_1
    //   581: aload_2
    //   582: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   585: athrow
    //   586: astore_1
    //   587: new 464	gnu/mapping/WrongType
    //   590: dup
    //   591: aload_1
    //   592: ldc 100
    //   594: iconst_2
    //   595: aload_3
    //   596: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   599: athrow
    //   600: astore_1
    //   601: new 464	gnu/mapping/WrongType
    //   604: dup
    //   605: aload_1
    //   606: ldc 96
    //   608: iconst_1
    //   609: aload_2
    //   610: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   613: athrow
    //   614: astore_1
    //   615: new 464	gnu/mapping/WrongType
    //   618: dup
    //   619: aload_1
    //   620: ldc 96
    //   622: iconst_2
    //   623: aload_3
    //   624: invokespecial 467	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   627: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	628	0	this	unicode
    //   0	628	1	paramModuleMethod	ModuleMethod
    //   0	628	2	paramObject1	Object
    //   0	628	3	paramObject2	Object
    // Exception table:
    //   from	to	target	type
    //   88	93	348	java/lang/ClassCastException
    //   93	98	362	java/lang/ClassCastException
    //   114	119	376	java/lang/ClassCastException
    //   119	124	390	java/lang/ClassCastException
    //   140	145	404	java/lang/ClassCastException
    //   145	150	418	java/lang/ClassCastException
    //   166	171	432	java/lang/ClassCastException
    //   171	176	446	java/lang/ClassCastException
    //   192	197	460	java/lang/ClassCastException
    //   197	202	474	java/lang/ClassCastException
    //   218	223	488	java/lang/ClassCastException
    //   223	228	502	java/lang/ClassCastException
    //   244	249	516	java/lang/ClassCastException
    //   249	254	530	java/lang/ClassCastException
    //   270	275	544	java/lang/ClassCastException
    //   275	280	558	java/lang/ClassCastException
    //   296	301	572	java/lang/ClassCastException
    //   301	306	586	java/lang/ClassCastException
    //   322	327	600	java/lang/ClassCastException
    //   327	332	614	java/lang/ClassCastException
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    case 21: 
    case 22: 
    case 23: 
    case 24: 
    case 25: 
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 29: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 28: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 27: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 26: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 20: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 19: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 18: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 17: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 16: 
      if (!(paramObject instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 10: 
      if (!(paramObject instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 9: 
      if (!(paramObject instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 8: 
      if (!(paramObject instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 7: 
      if (!(paramObject instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 6: 
      if (!(paramObject instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 5: 
      if (!(paramObject instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 4: 
      if (!(paramObject instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 3: 
      if (!(paramObject instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 2: 
      if (!(paramObject instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    }
    if (!(paramObject instanceof Char)) {
      return -786431;
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
    case 16: 
    case 17: 
    case 18: 
    case 19: 
    case 20: 
    default: 
      return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    case 25: 
      if ((paramObject1 instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject1;
        if ((paramObject2 instanceof CharSequence))
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
    case 24: 
      if ((paramObject1 instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject1;
        if ((paramObject2 instanceof CharSequence))
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
    case 23: 
      if ((paramObject1 instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject1;
        if ((paramObject2 instanceof CharSequence))
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
    case 22: 
      if ((paramObject1 instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject1;
        if ((paramObject2 instanceof CharSequence))
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
    case 21: 
      if ((paramObject1 instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject1;
        if ((paramObject2 instanceof CharSequence))
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
    case 15: 
      if (!(paramObject1 instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Char)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 14: 
      if (!(paramObject1 instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Char)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 13: 
      if (!(paramObject1 instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Char)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 12: 
      if (!(paramObject1 instanceof Char)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Char)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    }
    if (!(paramObject1 instanceof Char)) {
      return -786431;
    }
    paramCallContext.value1 = paramObject1;
    if (!(paramObject2 instanceof Char)) {
      return -786430;
    }
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\rnrs\unicode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */