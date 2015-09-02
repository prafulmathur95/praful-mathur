package gnu.text;

import java.io.Writer;
import java.lang.reflect.Method;

public class WriterManager
  implements Runnable
{
  public static final WriterManager instance = new WriterManager();
  WriterRef first;
  
  public WriterRef register(Writer paramWriter)
  {
    try
    {
      paramWriter = new WriterRef(paramWriter);
      WriterRef localWriterRef = this.first;
      if (localWriterRef != null)
      {
        paramWriter.next = localWriterRef.next;
        localWriterRef.prev = paramWriter;
      }
      this.first = paramWriter;
      return paramWriter;
    }
    finally {}
  }
  
  public boolean registerShutdownHook()
  {
    try
    {
      Runtime localRuntime = Runtime.getRuntime();
      localRuntime.getClass().getDeclaredMethod("addShutdownHook", new Class[] { Thread.class }).invoke(localRuntime, new Object[] { new Thread(this) });
      return true;
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 28	gnu/text/WriterManager:first	Lgnu/text/WriterRef;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull +27 -> 35
    //   11: aload_1
    //   12: invokevirtual 74	gnu/text/WriterRef:get	()Ljava/lang/Object;
    //   15: astore_2
    //   16: aload_2
    //   17: ifnull +10 -> 27
    //   20: aload_2
    //   21: checkcast 76	java/io/Writer
    //   24: invokevirtual 79	java/io/Writer:close	()V
    //   27: aload_1
    //   28: getfield 31	gnu/text/WriterRef:next	Lgnu/text/WriterRef;
    //   31: astore_1
    //   32: goto -25 -> 7
    //   35: aload_0
    //   36: aconst_null
    //   37: putfield 28	gnu/text/WriterManager:first	Lgnu/text/WriterRef;
    //   40: aload_0
    //   41: monitorexit
    //   42: return
    //   43: astore_1
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_1
    //   47: athrow
    //   48: astore_2
    //   49: goto -22 -> 27
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	this	WriterManager
    //   6	26	1	localWriterRef	WriterRef
    //   43	4	1	localObject1	Object
    //   15	6	2	localObject2	Object
    //   48	1	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   2	7	43	finally
    //   11	16	43	finally
    //   20	27	43	finally
    //   27	32	43	finally
    //   35	40	43	finally
    //   20	27	48	java/lang/Exception
  }
  
  public void unregister(Object paramObject)
  {
    if (paramObject == null) {}
    for (;;)
    {
      return;
      try
      {
        paramObject = (WriterRef)paramObject;
        WriterRef localWriterRef1 = ((WriterRef)paramObject).next;
        WriterRef localWriterRef2 = ((WriterRef)paramObject).prev;
        if (localWriterRef1 != null) {
          localWriterRef1.prev = localWriterRef2;
        }
        if (localWriterRef2 != null) {
          localWriterRef2.next = localWriterRef1;
        }
        if (paramObject != this.first) {
          continue;
        }
        this.first = localWriterRef1;
      }
      finally {}
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\WriterManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */