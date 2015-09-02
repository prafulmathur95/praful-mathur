package kawa.lib.kawa;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kawa.lib.lists;
import kawa.standard.Scheme;

public class regex
  extends ModuleBody
{
  public static final regex $instance;
  static final SimpleSymbol Lit0;
  static final SimpleSymbol Lit1;
  static final SimpleSymbol Lit2;
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7 = (SimpleSymbol)new SimpleSymbol("regex-replace*").readResolve();
  public static final ModuleMethod regex$Mnmatch;
  public static final ModuleMethod regex$Mnmatch$Mnpositions;
  public static final ModuleMethod regex$Mnmatch$Qu;
  public static final ModuleMethod regex$Mnquote;
  public static final ModuleMethod regex$Mnreplace;
  public static final ModuleMethod regex$Mnreplace$St;
  public static final ModuleMethod regex$Mnsplit;
  
  static
  {
    Lit6 = (SimpleSymbol)new SimpleSymbol("regex-replace").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("regex-split").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("regex-match-positions").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("regex-match").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("regex-match?").readResolve();
    Lit1 = (SimpleSymbol)new SimpleSymbol("regex-quote").readResolve();
    Lit0 = (SimpleSymbol)new SimpleSymbol("loop").readResolve();
    $instance = new regex();
    regex localregex = $instance;
    regex$Mnquote = new ModuleMethod(localregex, 2, Lit1, 4097);
    regex$Mnmatch$Qu = new ModuleMethod(localregex, 3, Lit2, 16386);
    regex$Mnmatch = new ModuleMethod(localregex, 6, Lit3, 16386);
    regex$Mnmatch$Mnpositions = new ModuleMethod(localregex, 9, Lit4, 16386);
    regex$Mnsplit = new ModuleMethod(localregex, 12, Lit5, 8194);
    regex$Mnreplace = new ModuleMethod(localregex, 13, Lit6, 12291);
    regex$Mnreplace$St = new ModuleMethod(localregex, 14, Lit7, 12291);
    $instance.run();
  }
  
  public regex()
  {
    ModuleInfo.register(this);
  }
  
  public static boolean isRegexMatch(Object paramObject, CharSequence paramCharSequence)
  {
    return isRegexMatch(paramObject, paramCharSequence, 0);
  }
  
  public static boolean isRegexMatch(Object paramObject, CharSequence paramCharSequence, int paramInt)
  {
    return isRegexMatch(paramObject, paramCharSequence, paramInt, paramCharSequence.length());
  }
  
  public static boolean isRegexMatch(Object paramObject, CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if ((paramObject instanceof Pattern)) {}
    for (;;)
    {
      try
      {
        Pattern localPattern = (Pattern)paramObject;
        paramObject = localPattern;
        paramObject = ((Pattern)paramObject).matcher(paramCharSequence);
        ((Matcher)paramObject).region(paramInt1, paramInt2);
        return ((Matcher)paramObject).find();
      }
      catch (ClassCastException paramCharSequence)
      {
        throw new WrongType(paramCharSequence, "rex", -2, paramObject);
      }
      paramObject = Pattern.compile(paramObject.toString());
    }
  }
  
  public static Object regexMatch(Object paramObject, CharSequence paramCharSequence)
  {
    return regexMatch(paramObject, paramCharSequence, 0);
  }
  
  public static Object regexMatch(Object paramObject, CharSequence paramCharSequence, int paramInt)
  {
    return regexMatch(paramObject, paramCharSequence, paramInt, paramCharSequence.length());
  }
  
  public static Object regexMatch(Object paramObject, CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if ((paramObject instanceof Pattern)) {}
    try
    {
      Object localObject = (Pattern)paramObject;
      Matcher localMatcher;
      for (paramObject = localObject;; paramObject = Pattern.compile(paramObject.toString()))
      {
        localMatcher = ((Pattern)paramObject).matcher(paramCharSequence);
        localMatcher.region(paramInt1, paramInt2);
        if (!localMatcher.find()) {
          break label114;
        }
        paramInt1 = localMatcher.groupCount();
        paramObject = LList.Empty;
        if (paramInt1 >= 0) {
          break;
        }
        return paramObject;
      }
      paramInt2 = localMatcher.start(paramInt1);
      if (paramInt2 < 0) {}
      for (localObject = Boolean.FALSE;; localObject = paramCharSequence.subSequence(paramInt2, localMatcher.end(paramInt1)))
      {
        paramObject = lists.cons(localObject, paramObject);
        paramInt1 -= 1;
        break;
      }
      label114:
      return Boolean.FALSE;
    }
    catch (ClassCastException paramCharSequence)
    {
      throw new WrongType(paramCharSequence, "rex", -2, paramObject);
    }
  }
  
  public static Object regexMatchPositions(Object paramObject, CharSequence paramCharSequence)
  {
    return regexMatchPositions(paramObject, paramCharSequence, 0);
  }
  
  public static Object regexMatchPositions(Object paramObject, CharSequence paramCharSequence, int paramInt)
  {
    return regexMatchPositions(paramObject, paramCharSequence, paramInt, paramCharSequence.length());
  }
  
  public static Object regexMatchPositions(Object paramObject, CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if ((paramObject instanceof Pattern)) {}
    try
    {
      Object localObject = (Pattern)paramObject;
      for (paramObject = localObject;; paramObject = Pattern.compile(paramObject.toString()))
      {
        localObject = ((Pattern)paramObject).matcher(paramCharSequence);
        ((Matcher)localObject).region(paramInt1, paramInt2);
        if (!((Matcher)localObject).find()) {
          break label114;
        }
        paramInt1 = ((Matcher)localObject).groupCount();
        paramObject = LList.Empty;
        if (paramInt1 >= 0) {
          break;
        }
        return paramObject;
      }
      paramInt2 = ((Matcher)localObject).start(paramInt1);
      if (paramInt2 < 0) {}
      for (paramCharSequence = Boolean.FALSE;; paramCharSequence = lists.cons(Integer.valueOf(paramInt2), Integer.valueOf(((Matcher)localObject).end(paramInt1))))
      {
        paramObject = lists.cons(paramCharSequence, paramObject);
        paramInt1 -= 1;
        break;
      }
      label114:
      return Boolean.FALSE;
    }
    catch (ClassCastException paramCharSequence)
    {
      throw new WrongType(paramCharSequence, "rex", -2, paramObject);
    }
  }
  
  public static String regexQuote(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {}
    for (paramCharSequence = null;; paramCharSequence = paramCharSequence.toString()) {
      return Pattern.quote(paramCharSequence);
    }
  }
  
  /* Error */
  public static CharSequence regexReplace(Object paramObject1, CharSequence paramCharSequence, Object paramObject2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aload_0
    //   6: instanceof 122
    //   9: ifeq +88 -> 97
    //   12: aload_0
    //   13: checkcast 122	java/util/regex/Pattern
    //   16: astore 5
    //   18: aload 5
    //   20: astore_0
    //   21: aload_0
    //   22: aload_1
    //   23: invokevirtual 126	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   26: astore 5
    //   28: aload 5
    //   30: invokevirtual 136	java/util/regex/Matcher:find	()Z
    //   33: ifeq +62 -> 95
    //   36: new 214	java/lang/StringBuffer
    //   39: dup
    //   40: invokespecial 215	java/lang/StringBuffer:<init>	()V
    //   43: astore_1
    //   44: aload_2
    //   45: invokestatic 221	kawa/lib/misc:isProcedure	(Ljava/lang/Object;)Z
    //   48: ifeq +68 -> 116
    //   51: getstatic 227	kawa/standard/Scheme:applyToArgs	Lgnu/kawa/functions/ApplyToArgs;
    //   54: aload_2
    //   55: aload 5
    //   57: invokevirtual 230	java/util/regex/Matcher:group	()Ljava/lang/String;
    //   60: invokevirtual 236	gnu/mapping/Procedure:apply2	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   63: astore_0
    //   64: aload_0
    //   65: ifnonnull +43 -> 108
    //   68: aload_3
    //   69: astore_0
    //   70: aload_0
    //   71: invokestatic 239	java/util/regex/Matcher:quoteReplacement	(Ljava/lang/String;)Ljava/lang/String;
    //   74: astore_0
    //   75: aload 5
    //   77: aload_1
    //   78: aload_0
    //   79: invokevirtual 243	java/util/regex/Matcher:appendReplacement	(Ljava/lang/StringBuffer;Ljava/lang/String;)Ljava/util/regex/Matcher;
    //   82: pop
    //   83: aload 5
    //   85: aload_1
    //   86: invokevirtual 247	java/util/regex/Matcher:appendTail	(Ljava/lang/StringBuffer;)Ljava/lang/StringBuffer;
    //   89: pop
    //   90: aload_1
    //   91: invokevirtual 248	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   94: astore_1
    //   95: aload_1
    //   96: areturn
    //   97: aload_0
    //   98: invokevirtual 142	java/lang/Object:toString	()Ljava/lang/String;
    //   101: invokestatic 146	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   104: astore_0
    //   105: goto -84 -> 21
    //   108: aload_0
    //   109: invokevirtual 142	java/lang/Object:toString	()Ljava/lang/String;
    //   112: astore_0
    //   113: goto -43 -> 70
    //   116: aload 4
    //   118: astore_0
    //   119: aload_2
    //   120: ifnull -45 -> 75
    //   123: aload_2
    //   124: invokevirtual 142	java/lang/Object:toString	()Ljava/lang/String;
    //   127: astore_0
    //   128: goto -53 -> 75
    //   131: astore_1
    //   132: new 148	gnu/mapping/WrongType
    //   135: dup
    //   136: aload_1
    //   137: ldc -106
    //   139: bipush -2
    //   141: aload_0
    //   142: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   145: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	146	0	paramObject1	Object
    //   0	146	1	paramCharSequence	CharSequence
    //   0	146	2	paramObject2	Object
    //   4	65	3	localObject1	Object
    //   1	116	4	localObject2	Object
    //   16	68	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   12	18	131	java/lang/ClassCastException
  }
  
  /* Error */
  public static CharSequence regexReplace$St(Object paramObject1, CharSequence paramCharSequence, Object paramObject2)
  {
    // Byte code:
    //   0: new 6	kawa/lib/kawa/regex$frame
    //   3: dup
    //   4: invokespecial 250	kawa/lib/kawa/regex$frame:<init>	()V
    //   7: astore_3
    //   8: aload_3
    //   9: aload_2
    //   10: putfield 254	kawa/lib/kawa/regex$frame:repl	Ljava/lang/Object;
    //   13: aload_0
    //   14: instanceof 122
    //   17: ifeq +53 -> 70
    //   20: aload_0
    //   21: checkcast 122	java/util/regex/Pattern
    //   24: astore_2
    //   25: aload_2
    //   26: astore_0
    //   27: aload_3
    //   28: aload_0
    //   29: aload_1
    //   30: invokevirtual 126	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   33: putfield 257	kawa/lib/kawa/regex$frame:matcher	Ljava/util/regex/Matcher;
    //   36: aload_3
    //   37: new 214	java/lang/StringBuffer
    //   40: dup
    //   41: invokespecial 215	java/lang/StringBuffer:<init>	()V
    //   44: putfield 261	kawa/lib/kawa/regex$frame:sbuf	Ljava/lang/StringBuffer;
    //   47: aload_3
    //   48: getfield 254	kawa/lib/kawa/regex$frame:repl	Ljava/lang/Object;
    //   51: invokestatic 221	kawa/lib/misc:isProcedure	(Ljava/lang/Object;)Z
    //   54: ifeq +27 -> 81
    //   57: aload_3
    //   58: aload_3
    //   59: getfield 263	kawa/lib/kawa/regex$frame:loop	Ljava/lang/Object;
    //   62: putfield 263	kawa/lib/kawa/regex$frame:loop	Ljava/lang/Object;
    //   65: aload_3
    //   66: invokevirtual 266	kawa/lib/kawa/regex$frame:lambda1loop	()Ljava/lang/String;
    //   69: areturn
    //   70: aload_0
    //   71: invokevirtual 142	java/lang/Object:toString	()Ljava/lang/String;
    //   74: invokestatic 146	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   77: astore_0
    //   78: goto -51 -> 27
    //   81: aload_3
    //   82: getfield 257	kawa/lib/kawa/regex$frame:matcher	Ljava/util/regex/Matcher;
    //   85: astore_1
    //   86: aload_3
    //   87: getfield 254	kawa/lib/kawa/regex$frame:repl	Ljava/lang/Object;
    //   90: astore_0
    //   91: aload_0
    //   92: ifnonnull +11 -> 103
    //   95: aconst_null
    //   96: astore_0
    //   97: aload_1
    //   98: aload_0
    //   99: invokevirtual 269	java/util/regex/Matcher:replaceAll	(Ljava/lang/String;)Ljava/lang/String;
    //   102: areturn
    //   103: aload_0
    //   104: invokevirtual 142	java/lang/Object:toString	()Ljava/lang/String;
    //   107: astore_0
    //   108: goto -11 -> 97
    //   111: astore_1
    //   112: new 148	gnu/mapping/WrongType
    //   115: dup
    //   116: aload_1
    //   117: ldc -106
    //   119: bipush -2
    //   121: aload_0
    //   122: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   125: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	paramObject1	Object
    //   0	126	1	paramCharSequence	CharSequence
    //   0	126	2	paramObject2	Object
    //   7	80	3	localframe	frame
    // Exception table:
    //   from	to	target	type
    //   20	25	111	java/lang/ClassCastException
  }
  
  public static LList regexSplit(Object paramObject, CharSequence paramCharSequence)
  {
    if ((paramObject instanceof Pattern)) {}
    for (;;)
    {
      try
      {
        Pattern localPattern = (Pattern)paramObject;
        paramObject = localPattern;
        return LList.makeList(((Pattern)paramObject).split(paramCharSequence, -1), 0);
      }
      catch (ClassCastException paramCharSequence)
      {
        throw new WrongType(paramCharSequence, "rex", -2, paramObject);
      }
      paramObject = Pattern.compile(paramObject.toString());
    }
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    if (paramModuleMethod.selector == 2) {}
    try
    {
      paramModuleMethod = (CharSequence)paramObject;
      return regexQuote(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "regex-quote", 1, paramObject);
    }
    return super.apply1(paramModuleMethod, paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    try
    {
      paramModuleMethod = (CharSequence)paramObject2;
      if (isRegexMatch(paramObject1, paramModuleMethod)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (CharSequence)paramObject2;
        return regexMatch(paramObject1, paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "regex-match", 2, paramObject2);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject2;
        return regexMatchPositions(paramObject1, paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "regex-match-positions", 2, paramObject2);
      }
      try
      {
        paramModuleMethod = (CharSequence)paramObject2;
        return regexSplit(paramObject1, paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "regex-split", 2, paramObject2);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "regex-match?", 2, paramObject2);
    }
  }
  
  /* Error */
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 285	gnu/expr/ModuleMethod:selector	I
    //   4: lookupswitch	default:+52->56, 3:+62->66, 6:+95->99, 9:+118->122, 13:+141->145, 14:+154->158
    //   56: aload_0
    //   57: aload_1
    //   58: aload_2
    //   59: aload_3
    //   60: aload 4
    //   62: invokespecial 307	gnu/expr/ModuleBody:apply3	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   65: areturn
    //   66: aload_3
    //   67: checkcast 111	java/lang/CharSequence
    //   70: astore_1
    //   71: aload 4
    //   73: checkcast 309	java/lang/Number
    //   76: invokevirtual 312	java/lang/Number:intValue	()I
    //   79: istore 5
    //   81: aload_2
    //   82: aload_1
    //   83: iload 5
    //   85: invokestatic 109	kawa/lib/kawa/regex:isRegexMatch	(Ljava/lang/Object;Ljava/lang/CharSequence;I)Z
    //   88: ifeq +7 -> 95
    //   91: getstatic 297	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   94: areturn
    //   95: getstatic 180	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   98: areturn
    //   99: aload_3
    //   100: checkcast 111	java/lang/CharSequence
    //   103: astore_1
    //   104: aload 4
    //   106: checkcast 309	java/lang/Number
    //   109: invokevirtual 312	java/lang/Number:intValue	()I
    //   112: istore 5
    //   114: aload_2
    //   115: aload_1
    //   116: iload 5
    //   118: invokestatic 158	kawa/lib/kawa/regex:regexMatch	(Ljava/lang/Object;Ljava/lang/CharSequence;I)Ljava/lang/Object;
    //   121: areturn
    //   122: aload_3
    //   123: checkcast 111	java/lang/CharSequence
    //   126: astore_1
    //   127: aload 4
    //   129: checkcast 309	java/lang/Number
    //   132: invokevirtual 312	java/lang/Number:intValue	()I
    //   135: istore 5
    //   137: aload_2
    //   138: aload_1
    //   139: iload 5
    //   141: invokestatic 196	kawa/lib/kawa/regex:regexMatchPositions	(Ljava/lang/Object;Ljava/lang/CharSequence;I)Ljava/lang/Object;
    //   144: areturn
    //   145: aload_3
    //   146: checkcast 111	java/lang/CharSequence
    //   149: astore_1
    //   150: aload_2
    //   151: aload_1
    //   152: aload 4
    //   154: invokestatic 314	kawa/lib/kawa/regex:regexReplace	(Ljava/lang/Object;Ljava/lang/CharSequence;Ljava/lang/Object;)Ljava/lang/CharSequence;
    //   157: areturn
    //   158: aload_3
    //   159: checkcast 111	java/lang/CharSequence
    //   162: astore_1
    //   163: aload_2
    //   164: aload_1
    //   165: aload 4
    //   167: invokestatic 316	kawa/lib/kawa/regex:regexReplace$St	(Ljava/lang/Object;Ljava/lang/CharSequence;Ljava/lang/Object;)Ljava/lang/CharSequence;
    //   170: areturn
    //   171: astore_1
    //   172: new 148	gnu/mapping/WrongType
    //   175: dup
    //   176: aload_1
    //   177: ldc 60
    //   179: iconst_2
    //   180: aload_3
    //   181: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   184: athrow
    //   185: astore_1
    //   186: new 148	gnu/mapping/WrongType
    //   189: dup
    //   190: aload_1
    //   191: ldc 60
    //   193: iconst_3
    //   194: aload 4
    //   196: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   199: athrow
    //   200: astore_1
    //   201: new 148	gnu/mapping/WrongType
    //   204: dup
    //   205: aload_1
    //   206: ldc 56
    //   208: iconst_2
    //   209: aload_3
    //   210: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   213: athrow
    //   214: astore_1
    //   215: new 148	gnu/mapping/WrongType
    //   218: dup
    //   219: aload_1
    //   220: ldc 56
    //   222: iconst_3
    //   223: aload 4
    //   225: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   228: athrow
    //   229: astore_1
    //   230: new 148	gnu/mapping/WrongType
    //   233: dup
    //   234: aload_1
    //   235: ldc 52
    //   237: iconst_2
    //   238: aload_3
    //   239: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   242: athrow
    //   243: astore_1
    //   244: new 148	gnu/mapping/WrongType
    //   247: dup
    //   248: aload_1
    //   249: ldc 52
    //   251: iconst_3
    //   252: aload 4
    //   254: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   257: athrow
    //   258: astore_1
    //   259: new 148	gnu/mapping/WrongType
    //   262: dup
    //   263: aload_1
    //   264: ldc 44
    //   266: iconst_2
    //   267: aload_3
    //   268: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   271: athrow
    //   272: astore_1
    //   273: new 148	gnu/mapping/WrongType
    //   276: dup
    //   277: aload_1
    //   278: ldc 32
    //   280: iconst_2
    //   281: aload_3
    //   282: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   285: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	286	0	this	regex
    //   0	286	1	paramModuleMethod	ModuleMethod
    //   0	286	2	paramObject1	Object
    //   0	286	3	paramObject2	Object
    //   0	286	4	paramObject3	Object
    //   79	61	5	i	int
    // Exception table:
    //   from	to	target	type
    //   66	71	171	java/lang/ClassCastException
    //   71	81	185	java/lang/ClassCastException
    //   99	104	200	java/lang/ClassCastException
    //   104	114	214	java/lang/ClassCastException
    //   122	127	229	java/lang/ClassCastException
    //   127	137	243	java/lang/ClassCastException
    //   145	150	258	java/lang/ClassCastException
    //   158	163	272	java/lang/ClassCastException
  }
  
  /* Error */
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 285	gnu/expr/ModuleMethod:selector	I
    //   4: lookupswitch	default:+36->40, 3:+48->52, 6:+93->97, 9:+128->132
    //   40: aload_0
    //   41: aload_1
    //   42: aload_2
    //   43: aload_3
    //   44: aload 4
    //   46: aload 5
    //   48: invokespecial 320	gnu/expr/ModuleBody:apply4	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   51: areturn
    //   52: aload_3
    //   53: checkcast 111	java/lang/CharSequence
    //   56: astore_1
    //   57: aload 4
    //   59: checkcast 309	java/lang/Number
    //   62: invokevirtual 312	java/lang/Number:intValue	()I
    //   65: istore 6
    //   67: aload 5
    //   69: checkcast 309	java/lang/Number
    //   72: invokevirtual 312	java/lang/Number:intValue	()I
    //   75: istore 7
    //   77: aload_2
    //   78: aload_1
    //   79: iload 6
    //   81: iload 7
    //   83: invokestatic 118	kawa/lib/kawa/regex:isRegexMatch	(Ljava/lang/Object;Ljava/lang/CharSequence;II)Z
    //   86: ifeq +7 -> 93
    //   89: getstatic 297	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   92: areturn
    //   93: getstatic 180	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   96: areturn
    //   97: aload_3
    //   98: checkcast 111	java/lang/CharSequence
    //   101: astore_1
    //   102: aload 4
    //   104: checkcast 309	java/lang/Number
    //   107: invokevirtual 312	java/lang/Number:intValue	()I
    //   110: istore 6
    //   112: aload 5
    //   114: checkcast 309	java/lang/Number
    //   117: invokevirtual 312	java/lang/Number:intValue	()I
    //   120: istore 7
    //   122: aload_2
    //   123: aload_1
    //   124: iload 6
    //   126: iload 7
    //   128: invokestatic 161	kawa/lib/kawa/regex:regexMatch	(Ljava/lang/Object;Ljava/lang/CharSequence;II)Ljava/lang/Object;
    //   131: areturn
    //   132: aload_3
    //   133: checkcast 111	java/lang/CharSequence
    //   136: astore_1
    //   137: aload 4
    //   139: checkcast 309	java/lang/Number
    //   142: invokevirtual 312	java/lang/Number:intValue	()I
    //   145: istore 6
    //   147: aload 5
    //   149: checkcast 309	java/lang/Number
    //   152: invokevirtual 312	java/lang/Number:intValue	()I
    //   155: istore 7
    //   157: aload_2
    //   158: aload_1
    //   159: iload 6
    //   161: iload 7
    //   163: invokestatic 198	kawa/lib/kawa/regex:regexMatchPositions	(Ljava/lang/Object;Ljava/lang/CharSequence;II)Ljava/lang/Object;
    //   166: areturn
    //   167: astore_1
    //   168: new 148	gnu/mapping/WrongType
    //   171: dup
    //   172: aload_1
    //   173: ldc 60
    //   175: iconst_2
    //   176: aload_3
    //   177: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   180: athrow
    //   181: astore_1
    //   182: new 148	gnu/mapping/WrongType
    //   185: dup
    //   186: aload_1
    //   187: ldc 60
    //   189: iconst_3
    //   190: aload 4
    //   192: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   195: athrow
    //   196: astore_1
    //   197: new 148	gnu/mapping/WrongType
    //   200: dup
    //   201: aload_1
    //   202: ldc 60
    //   204: iconst_4
    //   205: aload 5
    //   207: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   210: athrow
    //   211: astore_1
    //   212: new 148	gnu/mapping/WrongType
    //   215: dup
    //   216: aload_1
    //   217: ldc 56
    //   219: iconst_2
    //   220: aload_3
    //   221: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   224: athrow
    //   225: astore_1
    //   226: new 148	gnu/mapping/WrongType
    //   229: dup
    //   230: aload_1
    //   231: ldc 56
    //   233: iconst_3
    //   234: aload 4
    //   236: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   239: athrow
    //   240: astore_1
    //   241: new 148	gnu/mapping/WrongType
    //   244: dup
    //   245: aload_1
    //   246: ldc 56
    //   248: iconst_4
    //   249: aload 5
    //   251: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   254: athrow
    //   255: astore_1
    //   256: new 148	gnu/mapping/WrongType
    //   259: dup
    //   260: aload_1
    //   261: ldc 52
    //   263: iconst_2
    //   264: aload_3
    //   265: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   268: athrow
    //   269: astore_1
    //   270: new 148	gnu/mapping/WrongType
    //   273: dup
    //   274: aload_1
    //   275: ldc 52
    //   277: iconst_3
    //   278: aload 4
    //   280: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   283: athrow
    //   284: astore_1
    //   285: new 148	gnu/mapping/WrongType
    //   288: dup
    //   289: aload_1
    //   290: ldc 52
    //   292: iconst_4
    //   293: aload 5
    //   295: invokespecial 153	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   298: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	299	0	this	regex
    //   0	299	1	paramModuleMethod	ModuleMethod
    //   0	299	2	paramObject1	Object
    //   0	299	3	paramObject2	Object
    //   0	299	4	paramObject3	Object
    //   0	299	5	paramObject4	Object
    //   65	95	6	i	int
    //   75	87	7	j	int
    // Exception table:
    //   from	to	target	type
    //   52	57	167	java/lang/ClassCastException
    //   57	67	181	java/lang/ClassCastException
    //   67	77	196	java/lang/ClassCastException
    //   97	102	211	java/lang/ClassCastException
    //   102	112	225	java/lang/ClassCastException
    //   112	122	240	java/lang/ClassCastException
    //   132	137	255	java/lang/ClassCastException
    //   137	147	269	java/lang/ClassCastException
    //   147	157	284	java/lang/ClassCastException
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 2)
    {
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    }
    return super.match1(paramModuleMethod, paramObject, paramCallContext);
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    case 12: 
      paramCallContext.value1 = paramObject1;
      if ((paramObject2 instanceof CharSequence))
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
    case 9: 
      paramCallContext.value1 = paramObject1;
      if ((paramObject2 instanceof CharSequence))
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
    case 6: 
      paramCallContext.value1 = paramObject1;
      if ((paramObject2 instanceof CharSequence))
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
      return -786430;
    }
    paramCallContext.value1 = paramObject1;
    if ((paramObject2 instanceof CharSequence))
    {
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    }
    return -786430;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
    case 14: 
      paramCallContext.value1 = paramObject1;
      if ((paramObject2 instanceof CharSequence))
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      }
      return -786430;
    case 13: 
      paramCallContext.value1 = paramObject1;
      if ((paramObject2 instanceof CharSequence))
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      }
      return -786430;
    case 9: 
      paramCallContext.value1 = paramObject1;
      if ((paramObject2 instanceof CharSequence))
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      }
      return -786430;
    case 6: 
      paramCallContext.value1 = paramObject1;
      if ((paramObject2 instanceof CharSequence))
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      }
      return -786430;
    }
    paramCallContext.value1 = paramObject1;
    if ((paramObject2 instanceof CharSequence))
    {
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    }
    return -786430;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
    case 9: 
      paramCallContext.value1 = paramObject1;
      if ((paramObject2 instanceof CharSequence))
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.value4 = paramObject4;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 4;
        return 0;
      }
      return -786430;
    case 6: 
      paramCallContext.value1 = paramObject1;
      if ((paramObject2 instanceof CharSequence))
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.value4 = paramObject4;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 4;
        return 0;
      }
      return -786430;
    }
    paramCallContext.value1 = paramObject1;
    if ((paramObject2 instanceof CharSequence))
    {
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 4;
      return 0;
    }
    return -786430;
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
  
  public class frame
    extends ModuleBody
  {
    Object loop = new ModuleMethod(this, 1, regex.Lit0, 0);
    Matcher matcher;
    Object repl;
    StringBuffer sbuf;
    
    public Object apply0(ModuleMethod paramModuleMethod)
    {
      if (paramModuleMethod.selector == 1) {
        return lambda1loop();
      }
      return super.apply0(paramModuleMethod);
    }
    
    public String lambda1loop()
    {
      Matcher localMatcher;
      StringBuffer localStringBuffer;
      if (this.matcher.find())
      {
        localMatcher = this.matcher;
        localStringBuffer = this.sbuf;
        localObject = Scheme.applyToArgs.apply2(this.repl, this.matcher.group());
        if (localObject != null) {
          break label74;
        }
      }
      label74:
      for (Object localObject = null;; localObject = localObject.toString())
      {
        localMatcher.appendReplacement(localStringBuffer, Matcher.quoteReplacement((String)localObject));
        this.matcher.appendTail(this.sbuf);
        return this.sbuf.toString();
      }
    }
    
    public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 1)
      {
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      }
      return super.match0(paramModuleMethod, paramCallContext);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\kawa\regex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */