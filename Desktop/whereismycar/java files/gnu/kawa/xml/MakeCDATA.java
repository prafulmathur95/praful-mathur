package gnu.kawa.xml;

import gnu.mapping.MethodProc;

public class MakeCDATA
  extends MethodProc
{
  public static final MakeCDATA makeCDATA = new MakeCDATA();
  
  /* Error */
  public void apply(gnu.mapping.CallContext paramCallContext)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 23	gnu/mapping/CallContext:consumer	Lgnu/lists/Consumer;
    //   4: astore_2
    //   5: aload_1
    //   6: invokestatic 29	gnu/kawa/xml/NodeConstructor:pushNodeContext	(Lgnu/mapping/CallContext;)Lgnu/xml/XMLFilter;
    //   9: astore_3
    //   10: new 31	java/lang/StringBuffer
    //   13: dup
    //   14: invokespecial 32	java/lang/StringBuffer:<init>	()V
    //   17: astore 4
    //   19: getstatic 38	gnu/mapping/Location:UNBOUND	Ljava/lang/String;
    //   22: astore 5
    //   24: aload_1
    //   25: aload 5
    //   27: invokevirtual 42	gnu/mapping/CallContext:getNextArg	(Ljava/lang/Object;)Ljava/lang/Object;
    //   30: astore 6
    //   32: aload 6
    //   34: aload 5
    //   36: if_acmpne +44 -> 80
    //   39: aload 4
    //   41: invokevirtual 46	java/lang/StringBuffer:length	()I
    //   44: istore 7
    //   46: iload 7
    //   48: newarray <illegal type>
    //   50: astore 5
    //   52: aload 4
    //   54: iconst_0
    //   55: iload 7
    //   57: aload 5
    //   59: iconst_0
    //   60: invokevirtual 50	java/lang/StringBuffer:getChars	(II[CI)V
    //   63: aload_3
    //   64: aload 5
    //   66: iconst_0
    //   67: iload 7
    //   69: invokeinterface 56 4 0
    //   74: aload_2
    //   75: aload_1
    //   76: invokestatic 60	gnu/kawa/xml/NodeConstructor:popNodeContext	(Lgnu/lists/Consumer;Lgnu/mapping/CallContext;)V
    //   79: return
    //   80: aload 6
    //   82: aload 4
    //   84: invokestatic 66	gnu/xml/TextUtils:stringValue	(Ljava/lang/Object;Ljava/lang/StringBuffer;)V
    //   87: goto -63 -> 24
    //   90: astore_3
    //   91: aload_2
    //   92: aload_1
    //   93: invokestatic 60	gnu/kawa/xml/NodeConstructor:popNodeContext	(Lgnu/lists/Consumer;Lgnu/mapping/CallContext;)V
    //   96: aload_3
    //   97: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	98	0	this	MakeCDATA
    //   0	98	1	paramCallContext	gnu.mapping.CallContext
    //   4	88	2	localConsumer	gnu.lists.Consumer
    //   9	55	3	localXMLFilter	gnu.xml.XMLFilter
    //   90	7	3	localObject1	Object
    //   17	66	4	localStringBuffer	StringBuffer
    //   22	43	5	localObject2	Object
    //   30	51	6	localObject3	Object
    //   44	24	7	i	int
    // Exception table:
    //   from	to	target	type
    //   10	24	90	finally
    //   24	32	90	finally
    //   39	74	90	finally
    //   80	87	90	finally
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\MakeCDATA.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */