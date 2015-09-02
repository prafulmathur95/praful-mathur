package kawa.standard;

import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.text.Options;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class with_compile_options
  extends Syntax
{
  public static final with_compile_options with_compile_options = new with_compile_options();
  
  static
  {
    with_compile_options.setName("with-compile-options");
  }
  
  /* Error */
  public static Object getOptions(Object paramObject, Stack paramStack, Syntax paramSyntax, Translator paramTranslator)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 11
    //   3: aload_3
    //   4: getfield 29	kawa/lang/Translator:currentOptions	Lgnu/text/Options;
    //   7: astore 7
    //   9: aconst_null
    //   10: astore 5
    //   12: aload_0
    //   13: astore 4
    //   15: aload 5
    //   17: astore_0
    //   18: aload 4
    //   20: instanceof 31
    //   23: ifeq +20 -> 43
    //   26: aload 4
    //   28: checkcast 31	kawa/lang/SyntaxForm
    //   31: astore_0
    //   32: aload_0
    //   33: invokeinterface 35 1 0
    //   38: astore 4
    //   40: goto -22 -> 18
    //   43: aload 4
    //   45: instanceof 37
    //   48: ifne +43 -> 91
    //   51: iload 11
    //   53: ifne +31 -> 84
    //   56: aload_3
    //   57: bipush 101
    //   59: new 39	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   66: ldc 42
    //   68: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: aload_2
    //   72: invokevirtual 50	kawa/lang/Syntax:getName	()Ljava/lang/String;
    //   75: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   81: invokevirtual 57	kawa/lang/Translator:error	(CLjava/lang/String;)V
    //   84: aload 4
    //   86: aload_0
    //   87: invokestatic 61	kawa/lang/Translator:wrapSyntax	(Ljava/lang/Object;Lkawa/lang/SyntaxForm;)Ljava/lang/Object;
    //   90: areturn
    //   91: aload 4
    //   93: checkcast 37	gnu/lists/Pair
    //   96: astore 5
    //   98: aload 5
    //   100: invokevirtual 64	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   103: invokestatic 68	kawa/lang/Translator:stripSyntax	(Ljava/lang/Object;)Ljava/lang/Object;
    //   106: astore 6
    //   108: aload 6
    //   110: instanceof 70
    //   113: ifeq -62 -> 51
    //   116: aload 6
    //   118: checkcast 70	gnu/expr/Keyword
    //   121: invokevirtual 71	gnu/expr/Keyword:getName	()Ljava/lang/String;
    //   124: astore 9
    //   126: iconst_1
    //   127: istore 11
    //   129: aload_3
    //   130: aload 5
    //   132: invokevirtual 74	kawa/lang/Translator:pushPositionOf	(Ljava/lang/Object;)Ljava/lang/Object;
    //   135: astore 8
    //   137: aload 5
    //   139: invokevirtual 77	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   142: astore 5
    //   144: aload_0
    //   145: astore 4
    //   147: aload 5
    //   149: instanceof 31
    //   152: ifeq +22 -> 174
    //   155: aload 5
    //   157: checkcast 31	kawa/lang/SyntaxForm
    //   160: astore 4
    //   162: aload 4
    //   164: invokeinterface 35 1 0
    //   169: astore 5
    //   171: goto -24 -> 147
    //   174: aload 5
    //   176: instanceof 37
    //   179: ifne +46 -> 225
    //   182: aload_3
    //   183: bipush 101
    //   185: new 39	java/lang/StringBuilder
    //   188: dup
    //   189: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   192: ldc 79
    //   194: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: aload 9
    //   199: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: ldc 81
    //   204: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   210: invokevirtual 57	kawa/lang/Translator:error	(CLjava/lang/String;)V
    //   213: getstatic 87	gnu/lists/LList:Empty	Lgnu/lists/LList;
    //   216: astore_0
    //   217: aload_3
    //   218: aload 8
    //   220: invokevirtual 91	kawa/lang/Translator:popPositionOf	(Ljava/lang/Object;)V
    //   223: aload_0
    //   224: areturn
    //   225: aload 5
    //   227: checkcast 37	gnu/lists/Pair
    //   230: astore_0
    //   231: aload_0
    //   232: invokevirtual 64	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   235: invokestatic 68	kawa/lang/Translator:stripSyntax	(Ljava/lang/Object;)Ljava/lang/Object;
    //   238: astore 6
    //   240: aload_0
    //   241: invokevirtual 77	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   244: astore 5
    //   246: aload 7
    //   248: aload 9
    //   250: invokevirtual 97	gnu/text/Options:getLocal	(Ljava/lang/String;)Ljava/lang/Object;
    //   253: astore 10
    //   255: aload 7
    //   257: aload 9
    //   259: invokevirtual 101	gnu/text/Options:getInfo	(Ljava/lang/String;)Lgnu/text/Options$OptionInfo;
    //   262: ifnonnull +45 -> 307
    //   265: aload_3
    //   266: bipush 119
    //   268: new 39	java/lang/StringBuilder
    //   271: dup
    //   272: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   275: ldc 103
    //   277: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: aload 9
    //   282: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   285: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   288: invokevirtual 57	kawa/lang/Translator:error	(CLjava/lang/String;)V
    //   291: aload_3
    //   292: aload 8
    //   294: invokevirtual 91	kawa/lang/Translator:popPositionOf	(Ljava/lang/Object;)V
    //   297: aload 4
    //   299: astore_0
    //   300: aload 5
    //   302: astore 4
    //   304: goto -286 -> 18
    //   307: aload 6
    //   309: instanceof 105
    //   312: ifeq +61 -> 373
    //   315: aload 6
    //   317: invokevirtual 108	java/lang/Object:toString	()Ljava/lang/String;
    //   320: astore_0
    //   321: aload 7
    //   323: aload 9
    //   325: aload_0
    //   326: aload_3
    //   327: invokevirtual 112	kawa/lang/Translator:getMessages	()Lgnu/text/SourceMessages;
    //   330: invokevirtual 116	gnu/text/Options:set	(Ljava/lang/String;Ljava/lang/Object;Lgnu/text/SourceMessages;)V
    //   333: aload_1
    //   334: ifnull +23 -> 357
    //   337: aload_1
    //   338: aload 9
    //   340: invokevirtual 121	java/util/Stack:push	(Ljava/lang/Object;)Ljava/lang/Object;
    //   343: pop
    //   344: aload_1
    //   345: aload 10
    //   347: invokevirtual 121	java/util/Stack:push	(Ljava/lang/Object;)Ljava/lang/Object;
    //   350: pop
    //   351: aload_1
    //   352: aload_0
    //   353: invokevirtual 121	java/util/Stack:push	(Ljava/lang/Object;)Ljava/lang/Object;
    //   356: pop
    //   357: aload_3
    //   358: aload 8
    //   360: invokevirtual 91	kawa/lang/Translator:popPositionOf	(Ljava/lang/Object;)V
    //   363: aload 4
    //   365: astore_0
    //   366: aload 5
    //   368: astore 4
    //   370: goto -352 -> 18
    //   373: aload 6
    //   375: astore_0
    //   376: aload 6
    //   378: instanceof 123
    //   381: ifne -60 -> 321
    //   384: aload 6
    //   386: astore_0
    //   387: aload 6
    //   389: instanceof 125
    //   392: ifne -71 -> 321
    //   395: aconst_null
    //   396: astore_0
    //   397: aload_3
    //   398: bipush 101
    //   400: new 39	java/lang/StringBuilder
    //   403: dup
    //   404: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   407: ldc 127
    //   409: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   412: aload 9
    //   414: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   417: invokevirtual 53	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   420: invokevirtual 57	kawa/lang/Translator:error	(CLjava/lang/String;)V
    //   423: goto -102 -> 321
    //   426: astore_0
    //   427: aload_3
    //   428: aload 8
    //   430: invokevirtual 91	kawa/lang/Translator:popPositionOf	(Ljava/lang/Object;)V
    //   433: aload_0
    //   434: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	435	0	paramObject	Object
    //   0	435	1	paramStack	Stack
    //   0	435	2	paramSyntax	Syntax
    //   0	435	3	paramTranslator	Translator
    //   13	356	4	localObject1	Object
    //   10	357	5	localObject2	Object
    //   106	282	6	localObject3	Object
    //   7	315	7	localOptions	Options
    //   135	294	8	localObject4	Object
    //   124	289	9	str	String
    //   253	93	10	localObject5	Object
    //   1	127	11	i	int
    // Exception table:
    //   from	to	target	type
    //   137	144	426	finally
    //   147	171	426	finally
    //   174	217	426	finally
    //   225	291	426	finally
    //   307	321	426	finally
    //   321	333	426	finally
    //   337	357	426	finally
    //   376	384	426	finally
    //   387	395	426	finally
    //   397	423	426	finally
  }
  
  /* Error */
  public gnu.expr.Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 77	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   4: astore_3
    //   5: aload_3
    //   6: instanceof 37
    //   9: ifeq +76 -> 85
    //   12: aload_3
    //   13: checkcast 37	gnu/lists/Pair
    //   16: astore 4
    //   18: aload 4
    //   20: invokevirtual 64	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   23: instanceof 118
    //   26: ifeq +59 -> 85
    //   29: aload 4
    //   31: invokevirtual 64	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   34: checkcast 118	java/util/Stack
    //   37: astore_1
    //   38: aload 4
    //   40: invokevirtual 77	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   43: astore_3
    //   44: aload_2
    //   45: getfield 29	kawa/lang/Translator:currentOptions	Lgnu/text/Options;
    //   48: aload_1
    //   49: invokevirtual 133	gnu/text/Options:pushOptionValues	(Ljava/util/Vector;)V
    //   52: aload_2
    //   53: aload_3
    //   54: invokevirtual 137	kawa/lang/Translator:rewrite_body	(Ljava/lang/Object;)Lgnu/expr/Expression;
    //   57: astore_3
    //   58: aload_3
    //   59: instanceof 139
    //   62: ifeq +42 -> 104
    //   65: aload_3
    //   66: checkcast 139	gnu/expr/BeginExp
    //   69: astore_3
    //   70: aload_3
    //   71: aload_1
    //   72: invokevirtual 142	gnu/expr/BeginExp:setCompileOptions	(Ljava/util/Vector;)V
    //   75: aload_2
    //   76: getfield 29	kawa/lang/Translator:currentOptions	Lgnu/text/Options;
    //   79: aload_1
    //   80: invokevirtual 145	gnu/text/Options:popOptionValues	(Ljava/util/Vector;)V
    //   83: aload_3
    //   84: areturn
    //   85: new 118	java/util/Stack
    //   88: dup
    //   89: invokespecial 146	java/util/Stack:<init>	()V
    //   92: astore_1
    //   93: aload_3
    //   94: aload_1
    //   95: aload_0
    //   96: aload_2
    //   97: invokestatic 148	kawa/standard/with_compile_options:getOptions	(Ljava/lang/Object;Ljava/util/Stack;Lkawa/lang/Syntax;Lkawa/lang/Translator;)Ljava/lang/Object;
    //   100: astore_3
    //   101: goto -49 -> 52
    //   104: new 139	gnu/expr/BeginExp
    //   107: dup
    //   108: iconst_1
    //   109: anewarray 150	gnu/expr/Expression
    //   112: dup
    //   113: iconst_0
    //   114: aload_3
    //   115: aastore
    //   116: invokespecial 153	gnu/expr/BeginExp:<init>	([Lgnu/expr/Expression;)V
    //   119: astore_3
    //   120: goto -50 -> 70
    //   123: astore_3
    //   124: aload_2
    //   125: getfield 29	kawa/lang/Translator:currentOptions	Lgnu/text/Options;
    //   128: aload_1
    //   129: invokevirtual 145	gnu/text/Options:popOptionValues	(Ljava/util/Vector;)V
    //   132: aload_3
    //   133: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	this	with_compile_options
    //   0	134	1	paramPair	Pair
    //   0	134	2	paramTranslator	Translator
    //   4	116	3	localObject1	Object
    //   123	10	3	localObject2	Object
    //   16	23	4	localPair	Pair
    // Exception table:
    //   from	to	target	type
    //   52	70	123	finally
    //   70	75	123	finally
    //   104	120	123	finally
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    Stack localStack = new Stack();
    Object localObject = getOptions(paramPair.getCdr(), localStack, this, paramTranslator);
    if (localObject == LList.Empty) {
      return;
    }
    if (localObject == paramPair.getCdr())
    {
      paramTranslator.scanBody(localObject, paramScopeExp, false);
      return;
    }
    paramScopeExp = new Pair(localStack, paramTranslator.scanBody(localObject, paramScopeExp, true));
    paramTranslator.currentOptions.popOptionValues(localStack);
    paramTranslator.formStack.add(Translator.makePair(paramPair, paramPair.getCar(), paramScopeExp));
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\with_compile_options.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */