package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderVector
  extends ReadTableEntry
{
  char close;
  
  public ReaderVector(char paramChar)
  {
    this.close = paramChar;
  }
  
  /* Error */
  public static gnu.lists.FVector readVector(LispReader paramLispReader, gnu.text.LineBufferedReader paramLineBufferedReader, int paramInt, char paramChar)
    throws IOException, SyntaxException
  {
    // Byte code:
    //   0: bipush 32
    //   2: istore 4
    //   4: aload_1
    //   5: instanceof 22
    //   8: ifeq +39 -> 47
    //   11: aload_1
    //   12: checkcast 22	gnu/mapping/InPort
    //   15: getfield 25	gnu/mapping/InPort:readState	C
    //   18: istore 5
    //   20: aload_1
    //   21: checkcast 22	gnu/mapping/InPort
    //   24: astore 6
    //   26: iload_3
    //   27: bipush 93
    //   29: if_icmpne +94 -> 123
    //   32: bipush 91
    //   34: istore 4
    //   36: aload 6
    //   38: iload 4
    //   40: putfield 25	gnu/mapping/InPort:readState	C
    //   43: iload 5
    //   45: istore 4
    //   47: new 27	java/util/Vector
    //   50: dup
    //   51: invokespecial 28	java/util/Vector:<init>	()V
    //   54: astore 8
    //   56: invokestatic 34	gnu/kawa/lispexpr/ReadTable:getCurrent	()Lgnu/kawa/lispexpr/ReadTable;
    //   59: astore 9
    //   61: aload_0
    //   62: invokevirtual 40	gnu/kawa/lispexpr/LispReader:read	()I
    //   65: istore_2
    //   66: iload_2
    //   67: ifge +9 -> 76
    //   70: aload_0
    //   71: ldc 42
    //   73: invokevirtual 46	gnu/kawa/lispexpr/LispReader:eofError	(Ljava/lang/String;)V
    //   76: iload_2
    //   77: iload_3
    //   78: if_icmpne +52 -> 130
    //   81: aload 8
    //   83: invokevirtual 49	java/util/Vector:size	()I
    //   86: anewarray 51	java/lang/Object
    //   89: astore_0
    //   90: aload 8
    //   92: aload_0
    //   93: invokevirtual 55	java/util/Vector:copyInto	([Ljava/lang/Object;)V
    //   96: new 57	gnu/lists/FVector
    //   99: dup
    //   100: aload_0
    //   101: invokespecial 59	gnu/lists/FVector:<init>	([Ljava/lang/Object;)V
    //   104: astore_0
    //   105: aload_1
    //   106: instanceof 22
    //   109: ifeq +12 -> 121
    //   112: aload_1
    //   113: checkcast 22	gnu/mapping/InPort
    //   116: iload 4
    //   118: putfield 25	gnu/mapping/InPort:readState	C
    //   121: aload_0
    //   122: areturn
    //   123: bipush 40
    //   125: istore 4
    //   127: goto -91 -> 36
    //   130: aload_0
    //   131: iload_2
    //   132: aload 9
    //   134: invokevirtual 63	gnu/kawa/lispexpr/LispReader:readValues	(ILgnu/kawa/lispexpr/ReadTable;)Ljava/lang/Object;
    //   137: astore 7
    //   139: aload 7
    //   141: instanceof 65
    //   144: ifeq +42 -> 186
    //   147: aload 7
    //   149: checkcast 65	gnu/mapping/Values
    //   152: invokevirtual 69	gnu/mapping/Values:getValues	()[Ljava/lang/Object;
    //   155: astore 6
    //   157: aload 6
    //   159: arraylength
    //   160: istore 10
    //   162: iconst_0
    //   163: istore_2
    //   164: iload_2
    //   165: iload 10
    //   167: if_icmpge -106 -> 61
    //   170: aload 8
    //   172: aload 6
    //   174: iload_2
    //   175: aaload
    //   176: invokevirtual 73	java/util/Vector:addElement	(Ljava/lang/Object;)V
    //   179: iload_2
    //   180: iconst_1
    //   181: iadd
    //   182: istore_2
    //   183: goto -19 -> 164
    //   186: aload 7
    //   188: astore 6
    //   190: aload 7
    //   192: getstatic 79	gnu/expr/QuoteExp:voidExp	Lgnu/expr/QuoteExp;
    //   195: if_acmpne +8 -> 203
    //   198: getstatic 83	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   201: astore 6
    //   203: aload 8
    //   205: aload 6
    //   207: invokevirtual 73	java/util/Vector:addElement	(Ljava/lang/Object;)V
    //   210: goto -149 -> 61
    //   213: astore_0
    //   214: aload_1
    //   215: instanceof 22
    //   218: ifeq +12 -> 230
    //   221: aload_1
    //   222: checkcast 22	gnu/mapping/InPort
    //   225: iload 4
    //   227: putfield 25	gnu/mapping/InPort:readState	C
    //   230: aload_0
    //   231: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	232	0	paramLispReader	LispReader
    //   0	232	1	paramLineBufferedReader	gnu.text.LineBufferedReader
    //   0	232	2	paramInt	int
    //   0	232	3	paramChar	char
    //   2	224	4	c1	char
    //   18	26	5	c2	char
    //   24	182	6	localObject1	Object
    //   137	54	7	localObject2	Object
    //   54	150	8	localVector	java.util.Vector
    //   59	74	9	localReadTable	ReadTable
    //   160	8	10	i	int
    // Exception table:
    //   from	to	target	type
    //   47	61	213	finally
    //   61	66	213	finally
    //   70	76	213	finally
    //   81	105	213	finally
    //   130	162	213	finally
    //   170	179	213	finally
    //   190	203	213	finally
    //   203	210	213	finally
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    return readVector((LispReader)paramLexer, paramLexer.getPort(), paramInt2, this.close);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ReaderVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */