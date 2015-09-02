package com.google.appinventor.components.runtime.util;

import gnu.expr.Language;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0;
import gnu.mapping.TtyInPort;
import gnu.text.FilePath;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import kawa.Telnet;

public class TelnetRepl
  extends Procedure0
{
  private static final int REPL_STACK_SIZE = 262144;
  Language language;
  Socket socket;
  
  public TelnetRepl(Language paramLanguage, Socket paramSocket)
  {
    this.language = paramLanguage;
    this.socket = paramSocket;
  }
  
  public static Thread serve(Language paramLanguage, Socket paramSocket)
    throws IOException
  {
    Object localObject2 = new Telnet(paramSocket, true);
    Object localObject1 = ((Telnet)localObject2).getOutputStream();
    localObject2 = ((Telnet)localObject2).getInputStream();
    localObject1 = new OutPort((OutputStream)localObject1, FilePath.valueOf("/dev/stdout"));
    localObject2 = new TtyInPort((InputStream)localObject2, FilePath.valueOf("/dev/stdin"), (OutPort)localObject1);
    paramLanguage = new BiggerFuture(new TelnetRepl(paramLanguage, paramSocket), (InPort)localObject2, (OutPort)localObject1, (OutPort)localObject1, "Telnet Repl Thread", 262144L);
    paramLanguage.start();
    return paramLanguage;
  }
  
  /* Error */
  public Object apply0()
  {
    // Byte code:
    //   0: invokestatic 83	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   3: astore_1
    //   4: aload_1
    //   5: invokevirtual 87	java/lang/Thread:getContextClassLoader	()Ljava/lang/ClassLoader;
    //   8: ifnonnull +12 -> 20
    //   11: aload_1
    //   12: ldc 27
    //   14: invokevirtual 92	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   17: invokevirtual 96	java/lang/Thread:setContextClassLoader	(Ljava/lang/ClassLoader;)V
    //   20: aload_0
    //   21: getfield 18	com/google/appinventor/components/runtime/util/TelnetRepl:language	Lgnu/expr/Language;
    //   24: invokestatic 102	gnu/mapping/Environment:getCurrent	()Lgnu/mapping/Environment;
    //   27: invokestatic 108	kawa/Shell:run	(Lgnu/expr/Language;Lgnu/mapping/Environment;)Z
    //   30: pop
    //   31: getstatic 114	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   34: astore_1
    //   35: aload_0
    //   36: getfield 20	com/google/appinventor/components/runtime/util/TelnetRepl:socket	Ljava/net/Socket;
    //   39: invokevirtual 119	java/net/Socket:close	()V
    //   42: aload_1
    //   43: areturn
    //   44: astore_1
    //   45: ldc 121
    //   47: new 123	java/lang/StringBuilder
    //   50: dup
    //   51: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   54: ldc 126
    //   56: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: aload_1
    //   60: invokevirtual 134	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   63: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   69: invokestatic 143	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   72: pop
    //   73: aload_1
    //   74: invokevirtual 146	java/lang/RuntimeException:printStackTrace	()V
    //   77: aload_1
    //   78: athrow
    //   79: astore_1
    //   80: aload_0
    //   81: getfield 20	com/google/appinventor/components/runtime/util/TelnetRepl:socket	Ljava/net/Socket;
    //   84: invokevirtual 119	java/net/Socket:close	()V
    //   87: aload_1
    //   88: athrow
    //   89: astore_2
    //   90: goto -3 -> 87
    //   93: astore_2
    //   94: aload_1
    //   95: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	this	TelnetRepl
    //   3	40	1	localObject1	Object
    //   44	34	1	localRuntimeException	RuntimeException
    //   79	16	1	localObject2	Object
    //   89	1	2	localIOException1	IOException
    //   93	1	2	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   20	35	44	java/lang/RuntimeException
    //   20	35	79	finally
    //   45	79	79	finally
    //   80	87	89	java/io/IOException
    //   35	42	93	java/io/IOException
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\TelnetRepl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */