package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.CharSeq;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Strings;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Char;

public class strings
  extends ModuleBody
{
  public static final strings $instance;
  public static final ModuleMethod $make$string$;
  static final Char Lit0;
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
  static final SimpleSymbol Lit22 = (SimpleSymbol)new SimpleSymbol("string-append/shared").readResolve();
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod list$Mn$Grstring;
  public static final ModuleMethod make$Mnstring;
  public static final ModuleMethod string$Eq$Qu;
  public static final ModuleMethod string$Gr$Eq$Qu;
  public static final ModuleMethod string$Gr$Qu;
  public static final ModuleMethod string$Ls$Eq$Qu;
  public static final ModuleMethod string$Ls$Qu;
  public static final ModuleMethod string$Mn$Grlist;
  public static final ModuleMethod string$Mnappend;
  public static final ModuleMethod string$Mnappend$Slshared;
  public static final ModuleMethod string$Mncapitalize;
  public static final ModuleMethod string$Mncapitalize$Ex;
  public static final ModuleMethod string$Mncopy;
  public static final ModuleMethod string$Mndowncase$Ex;
  public static final ModuleMethod string$Mnfill$Ex;
  public static final ModuleMethod string$Mnlength;
  public static final ModuleMethod string$Mnref;
  public static final ModuleMethod string$Mnset$Ex;
  public static final ModuleMethod string$Mnupcase$Ex;
  public static final ModuleMethod string$Qu;
  public static final ModuleMethod substring;
  
  public static CharSequence $make$string$(Object... paramVarArgs)
  {
    int j = paramVarArgs.length;
    FString localFString = new FString(j);
    int i = 0;
    while (i < j)
    {
      localFString.setCharAt(i, ((Char)paramVarArgs[i]).charValue());
      i += 1;
    }
    return localFString;
  }
  
  static
  {
    Lit21 = (SimpleSymbol)new SimpleSymbol("string-append").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("string-capitalize").readResolve();
    Lit19 = (SimpleSymbol)new SimpleSymbol("string-capitalize!").readResolve();
    Lit18 = (SimpleSymbol)new SimpleSymbol("string-downcase!").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("string-upcase!").readResolve();
    Lit16 = (SimpleSymbol)new SimpleSymbol("string-fill!").readResolve();
    Lit15 = (SimpleSymbol)new SimpleSymbol("string-copy").readResolve();
    Lit14 = (SimpleSymbol)new SimpleSymbol("list->string").readResolve();
    Lit13 = (SimpleSymbol)new SimpleSymbol("string->list").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("substring").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("string>=?").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("string<=?").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("string>?").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("string<?").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("string=?").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("string-set!").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("string-ref").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("string-length").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("$make$string$").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("make-string").readResolve();
    Lit1 = (SimpleSymbol)new SimpleSymbol("string?").readResolve();
    Lit0 = Char.make(32);
    $instance = new strings();
    strings localstrings = $instance;
    string$Qu = new ModuleMethod(localstrings, 1, Lit1, 4097);
    make$Mnstring = new ModuleMethod(localstrings, 2, Lit2, 8193);
    $make$string$ = new ModuleMethod(localstrings, 4, Lit3, 61440);
    string$Mnlength = new ModuleMethod(localstrings, 5, Lit4, 4097);
    string$Mnref = new ModuleMethod(localstrings, 6, Lit5, 8194);
    string$Mnset$Ex = new ModuleMethod(localstrings, 7, Lit6, 12291);
    string$Eq$Qu = new ModuleMethod(localstrings, 8, Lit7, 8194);
    string$Ls$Qu = new ModuleMethod(localstrings, 9, Lit8, 8194);
    string$Gr$Qu = new ModuleMethod(localstrings, 10, Lit9, 8194);
    string$Ls$Eq$Qu = new ModuleMethod(localstrings, 11, Lit10, 8194);
    string$Gr$Eq$Qu = new ModuleMethod(localstrings, 12, Lit11, 8194);
    substring = new ModuleMethod(localstrings, 13, Lit12, 12291);
    string$Mn$Grlist = new ModuleMethod(localstrings, 14, Lit13, 4097);
    list$Mn$Grstring = new ModuleMethod(localstrings, 15, Lit14, 4097);
    string$Mncopy = new ModuleMethod(localstrings, 16, Lit15, 4097);
    string$Mnfill$Ex = new ModuleMethod(localstrings, 17, Lit16, 8194);
    string$Mnupcase$Ex = new ModuleMethod(localstrings, 18, Lit17, 4097);
    string$Mndowncase$Ex = new ModuleMethod(localstrings, 19, Lit18, 4097);
    string$Mncapitalize$Ex = new ModuleMethod(localstrings, 20, Lit19, 4097);
    string$Mncapitalize = new ModuleMethod(localstrings, 21, Lit20, 4097);
    string$Mnappend = new ModuleMethod(localstrings, 22, Lit21, 61440);
    string$Mnappend$Slshared = new ModuleMethod(localstrings, 23, Lit22, 61440);
    $instance.run();
  }
  
  public strings()
  {
    ModuleInfo.register(this);
  }
  
  public static boolean isString(Object paramObject)
  {
    return paramObject instanceof CharSequence;
  }
  
  public static boolean isString$Eq(Object paramObject1, Object paramObject2)
  {
    return paramObject1.toString().equals(paramObject2.toString());
  }
  
  public static boolean isString$Gr(Object paramObject1, Object paramObject2)
  {
    return paramObject1.toString().compareTo(paramObject2.toString()) > 0;
  }
  
  public static boolean isString$Gr$Eq(Object paramObject1, Object paramObject2)
  {
    return paramObject1.toString().compareTo(paramObject2.toString()) >= 0;
  }
  
  public static boolean isString$Ls(Object paramObject1, Object paramObject2)
  {
    return paramObject1.toString().compareTo(paramObject2.toString()) < 0;
  }
  
  public static boolean isString$Ls$Eq(Object paramObject1, Object paramObject2)
  {
    return paramObject1.toString().compareTo(paramObject2.toString()) <= 0;
  }
  
  /* Error */
  public static CharSequence list$To$String(LList paramLList)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 273	kawa/lib/lists:length	(Lgnu/lists/LList;)I
    //   4: istore 6
    //   6: new 57	gnu/lists/FString
    //   9: dup
    //   10: iload 6
    //   12: invokespecial 61	gnu/lists/FString:<init>	(I)V
    //   15: astore_2
    //   16: iconst_0
    //   17: istore 5
    //   19: iload 5
    //   21: iload 6
    //   23: if_icmpge +114 -> 137
    //   26: aload_0
    //   27: checkcast 275	gnu/lists/Pair
    //   30: astore_3
    //   31: aload_2
    //   32: checkcast 277	gnu/lists/CharSeq
    //   35: astore 4
    //   37: aload_3
    //   38: invokevirtual 280	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   41: astore_0
    //   42: aload_0
    //   43: checkcast 63	gnu/text/Char
    //   46: invokevirtual 67	gnu/text/Char:charValue	()C
    //   49: istore_1
    //   50: aload 4
    //   52: iload 5
    //   54: iload_1
    //   55: invokestatic 284	kawa/lib/strings:stringSet$Ex	(Lgnu/lists/CharSeq;IC)V
    //   58: aload_3
    //   59: invokevirtual 287	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   62: astore_3
    //   63: aload_3
    //   64: checkcast 289	gnu/lists/LList
    //   67: astore_0
    //   68: iload 5
    //   70: iconst_1
    //   71: iadd
    //   72: istore 5
    //   74: goto -55 -> 19
    //   77: astore_2
    //   78: new 291	gnu/mapping/WrongType
    //   81: dup
    //   82: aload_2
    //   83: ldc_w 293
    //   86: bipush -2
    //   88: aload_0
    //   89: invokespecial 296	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   92: athrow
    //   93: astore_0
    //   94: new 291	gnu/mapping/WrongType
    //   97: dup
    //   98: aload_0
    //   99: ldc -108
    //   101: iconst_0
    //   102: aload_2
    //   103: invokespecial 296	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   106: athrow
    //   107: astore_2
    //   108: new 291	gnu/mapping/WrongType
    //   111: dup
    //   112: aload_2
    //   113: ldc -108
    //   115: iconst_2
    //   116: aload_0
    //   117: invokespecial 296	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   120: athrow
    //   121: astore_0
    //   122: new 291	gnu/mapping/WrongType
    //   125: dup
    //   126: aload_0
    //   127: ldc_w 298
    //   130: bipush -2
    //   132: aload_3
    //   133: invokespecial 296	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   136: athrow
    //   137: aload_2
    //   138: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	paramLList	LList
    //   49	6	1	c	char
    //   15	17	2	localFString	FString
    //   77	26	2	localClassCastException1	ClassCastException
    //   107	31	2	localClassCastException2	ClassCastException
    //   30	103	3	localObject	Object
    //   35	16	4	localCharSeq	CharSeq
    //   17	56	5	i	int
    //   4	20	6	j	int
    // Exception table:
    //   from	to	target	type
    //   26	31	77	java/lang/ClassCastException
    //   31	37	93	java/lang/ClassCastException
    //   42	50	107	java/lang/ClassCastException
    //   63	68	121	java/lang/ClassCastException
  }
  
  public static CharSequence makeString(int paramInt)
  {
    return makeString(paramInt, Lit0);
  }
  
  public static CharSequence makeString(int paramInt, Object paramObject)
  {
    try
    {
      char c = ((Char)paramObject).charValue();
      return new FString(paramInt, c);
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "gnu.lists.FString.<init>(int,char)", 2, paramObject);
    }
  }
  
  public static LList string$To$List(CharSequence paramCharSequence)
  {
    Object localObject = LList.Empty;
    int i = stringLength(paramCharSequence);
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        return (LList)localObject;
      }
      localObject = new Pair(Char.make(stringRef(paramCharSequence, i)), localObject);
    }
  }
  
  public static FString stringAppend(Object... paramVarArgs)
  {
    FString localFString = new FString();
    localFString.addAllStrings(paramVarArgs, 0);
    return localFString;
  }
  
  public static CharSequence stringAppend$SlShared(Object... paramVarArgs)
  {
    if (paramVarArgs.length == 0) {
      return new FString();
    }
    localObject2 = paramVarArgs[0];
    if ((localObject2 instanceof FString)) {}
    try
    {
      localObject1 = (FString)localObject2;
      ((FString)localObject1).addAllStrings(paramVarArgs, 1);
      return (CharSequence)localObject1;
    }
    catch (ClassCastException paramVarArgs)
    {
      try
      {
        Object localObject1 = (CharSequence)localObject2;
        localObject1 = stringCopy((CharSequence)localObject1);
      }
      catch (ClassCastException paramVarArgs)
      {
        throw new WrongType(paramVarArgs, "string-copy", 0, localObject2);
      }
      paramVarArgs = paramVarArgs;
      throw new WrongType(paramVarArgs, "fstr", -2, localObject2);
    }
  }
  
  public static CharSequence stringCapitalize(CharSequence paramCharSequence)
  {
    paramCharSequence = stringCopy(paramCharSequence);
    Strings.makeCapitalize(paramCharSequence);
    return paramCharSequence;
  }
  
  public static CharSequence stringCapitalize$Ex(CharSeq paramCharSeq)
  {
    Strings.makeCapitalize(paramCharSeq);
    return paramCharSeq;
  }
  
  public static FString stringCopy(CharSequence paramCharSequence)
  {
    return new FString(paramCharSequence);
  }
  
  public static CharSequence stringDowncase$Ex(CharSeq paramCharSeq)
  {
    Strings.makeLowerCase(paramCharSeq);
    return paramCharSeq;
  }
  
  public static void stringFill$Ex(CharSeq paramCharSeq, char paramChar)
  {
    paramCharSeq.fill(paramChar);
  }
  
  public static int stringLength(CharSequence paramCharSequence)
  {
    return paramCharSequence.length();
  }
  
  public static char stringRef(CharSequence paramCharSequence, int paramInt)
  {
    return paramCharSequence.charAt(paramInt);
  }
  
  public static void stringSet$Ex(CharSeq paramCharSeq, int paramInt, char paramChar)
  {
    paramCharSeq.setCharAt(paramInt, paramChar);
  }
  
  public static CharSequence stringUpcase$Ex(CharSeq paramCharSeq)
  {
    Strings.makeUpperCase(paramCharSeq);
    return paramCharSeq;
  }
  
  public static CharSequence substring(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    return new FString(paramCharSequence, paramInt1, paramInt2 - paramInt1);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 3: 
    case 4: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 17: 
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 1: 
      if (isString(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    try
    {
      int i = ((Number)paramObject).intValue();
      return makeString(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (CharSequence)paramObject;
        return Integer.valueOf(stringLength(paramModuleMethod));
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-length", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject;
        return string$To$List(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string->list", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (LList)paramObject;
        return list$To$String(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "list->string", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject;
        return stringCopy(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-copy", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSeq)paramObject;
        return stringUpcase$Ex(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-upcase!", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSeq)paramObject;
        return stringDowncase$Ex(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-downcase!", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSeq)paramObject;
        return stringCapitalize$Ex(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-capitalize!", 1, paramObject);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject;
        return stringCapitalize(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-capitalize", 1, paramObject);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "make-string", 1, paramObject);
    }
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    switch (paramModuleMethod.selector)
    {
    case 3: 
    case 4: 
    case 5: 
    case 7: 
    case 13: 
    case 14: 
    case 15: 
    case 16: 
    default: 
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    try
    {
      i = ((Number)paramObject1).intValue();
      return makeString(i, paramObject2);
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        int i;
        paramModuleMethod = (CharSeq)paramObject1;
      }
      catch (ClassCastException paramModuleMethod)
      {
        char c;
        throw new WrongType(paramModuleMethod, "string-fill!", 1, paramObject1);
      }
      try
      {
        c = ((Char)paramObject2).charValue();
        stringFill$Ex(paramModuleMethod, c);
        return Values.empty;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "string-fill!", 2, paramObject2);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "make-string", 1, paramObject1);
    }
    try
    {
      paramModuleMethod = (CharSequence)paramObject1;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "string-ref", 1, paramObject1);
    }
    try
    {
      i = ((Number)paramObject2).intValue();
      return Char.make(stringRef(paramModuleMethod, i));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "string-ref", 2, paramObject2);
    }
    if (isString$Eq(paramObject1, paramObject2)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    if (isString$Ls(paramObject1, paramObject2)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    if (isString$Gr(paramObject1, paramObject2)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    if (isString$Ls$Eq(paramObject1, paramObject2)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    if (isString$Gr$Eq(paramObject1, paramObject2)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
  
  /* Error */
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 383	gnu/expr/ModuleMethod:selector	I
    //   4: lookupswitch	default:+28->32, 7:+38->42, 13:+74->78
    //   32: aload_0
    //   33: aload_1
    //   34: aload_2
    //   35: aload_3
    //   36: aload 4
    //   38: invokespecial 447	gnu/expr/ModuleBody:apply3	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   41: areturn
    //   42: aload_2
    //   43: checkcast 277	gnu/lists/CharSeq
    //   46: astore_1
    //   47: aload_3
    //   48: checkcast 398	java/lang/Number
    //   51: invokevirtual 401	java/lang/Number:intValue	()I
    //   54: istore 6
    //   56: aload 4
    //   58: checkcast 63	gnu/text/Char
    //   61: invokevirtual 67	gnu/text/Char:charValue	()C
    //   64: istore 5
    //   66: aload_1
    //   67: iload 6
    //   69: iload 5
    //   71: invokestatic 284	kawa/lib/strings:stringSet$Ex	(Lgnu/lists/CharSeq;IC)V
    //   74: getstatic 443	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   77: areturn
    //   78: aload_2
    //   79: checkcast 242	java/lang/CharSequence
    //   82: astore_1
    //   83: aload_3
    //   84: checkcast 398	java/lang/Number
    //   87: invokevirtual 401	java/lang/Number:intValue	()I
    //   90: istore 6
    //   92: aload 4
    //   94: checkcast 398	java/lang/Number
    //   97: invokevirtual 401	java/lang/Number:intValue	()I
    //   100: istore 7
    //   102: aload_1
    //   103: iload 6
    //   105: iload 7
    //   107: invokestatic 449	kawa/lib/strings:substring	(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
    //   110: areturn
    //   111: astore_1
    //   112: new 291	gnu/mapping/WrongType
    //   115: dup
    //   116: aload_1
    //   117: ldc -108
    //   119: iconst_1
    //   120: aload_2
    //   121: invokespecial 296	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   124: athrow
    //   125: astore_1
    //   126: new 291	gnu/mapping/WrongType
    //   129: dup
    //   130: aload_1
    //   131: ldc -108
    //   133: iconst_2
    //   134: aload_3
    //   135: invokespecial 296	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   138: athrow
    //   139: astore_1
    //   140: new 291	gnu/mapping/WrongType
    //   143: dup
    //   144: aload_1
    //   145: ldc -108
    //   147: iconst_3
    //   148: aload 4
    //   150: invokespecial 296	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   153: athrow
    //   154: astore_1
    //   155: new 291	gnu/mapping/WrongType
    //   158: dup
    //   159: aload_1
    //   160: ldc 124
    //   162: iconst_1
    //   163: aload_2
    //   164: invokespecial 296	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   167: athrow
    //   168: astore_1
    //   169: new 291	gnu/mapping/WrongType
    //   172: dup
    //   173: aload_1
    //   174: ldc 124
    //   176: iconst_2
    //   177: aload_3
    //   178: invokespecial 296	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   181: athrow
    //   182: astore_1
    //   183: new 291	gnu/mapping/WrongType
    //   186: dup
    //   187: aload_1
    //   188: ldc 124
    //   190: iconst_3
    //   191: aload 4
    //   193: invokespecial 296	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   196: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	197	0	this	strings
    //   0	197	1	paramModuleMethod	ModuleMethod
    //   0	197	2	paramObject1	Object
    //   0	197	3	paramObject2	Object
    //   0	197	4	paramObject3	Object
    //   64	6	5	c	char
    //   54	50	6	i	int
    //   100	6	7	j	int
    // Exception table:
    //   from	to	target	type
    //   42	47	111	java/lang/ClassCastException
    //   47	56	125	java/lang/ClassCastException
    //   56	66	139	java/lang/ClassCastException
    //   78	83	154	java/lang/ClassCastException
    //   83	92	168	java/lang/ClassCastException
    //   92	102	182	java/lang/ClassCastException
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.applyN(paramModuleMethod, paramArrayOfObject);
    case 4: 
      return $make$string$(paramArrayOfObject);
    case 22: 
      return stringAppend(paramArrayOfObject);
    }
    return stringAppend$SlShared(paramArrayOfObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 3: 
    case 4: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 17: 
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 21: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 20: 
      if (!(paramObject instanceof CharSeq)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 19: 
      if (!(paramObject instanceof CharSeq)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 18: 
      if (!(paramObject instanceof CharSeq)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 16: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 15: 
      if ((paramObject instanceof LList))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 14: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 5: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
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
    case 3: 
    case 4: 
    case 5: 
    case 7: 
    case 13: 
    case 14: 
    case 15: 
    case 16: 
    default: 
      i = super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    case 17: 
    case 12: 
    case 11: 
    case 10: 
    case 9: 
    case 8: 
    case 6: 
      do
      {
        do
        {
          return i;
        } while (!(paramObject1 instanceof CharSeq));
        paramCallContext.value1 = paramObject1;
        if ((paramObject2 instanceof Char))
        {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        }
        return -786430;
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
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      } while (!(paramObject1 instanceof CharSequence));
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
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
    case 13: 
      if ((paramObject1 instanceof CharSequence))
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
    if (!(paramObject1 instanceof CharSeq)) {
      return -786431;
    }
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    if ((paramObject3 instanceof Char))
    {
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    }
    return -786429;
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
    case 23: 
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    case 22: 
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


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\strings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */