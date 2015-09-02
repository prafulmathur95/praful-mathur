package kawa;

import gnu.expr.Language;
import gnu.mapping.Future;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0;
import gnu.mapping.TtyInPort;
import gnu.text.FilePath;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TelnetRepl
  extends Procedure0
{
  Language language;
  Socket socket;
  
  public TelnetRepl(Language paramLanguage, Socket paramSocket)
  {
    this.language = paramLanguage;
    this.socket = paramSocket;
  }
  
  public static void serve(Language paramLanguage, Socket paramSocket)
    throws IOException
  {
    Object localObject2 = new Telnet(paramSocket, true);
    Object localObject1 = ((Telnet)localObject2).getOutputStream();
    localObject2 = ((Telnet)localObject2).getInputStream();
    localObject1 = new OutPort((OutputStream)localObject1, FilePath.valueOf("/dev/stdout"));
    localObject2 = new TtyInPort((InputStream)localObject2, FilePath.valueOf("/dev/stdin"), (OutPort)localObject1);
    new Future(new TelnetRepl(paramLanguage, paramSocket), (InPort)localObject2, (OutPort)localObject1, (OutPort)localObject1).start();
  }
  
  /* Error */
  public Object apply0()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 15	kawa/TelnetRepl:language	Lgnu/expr/Language;
    //   4: invokestatic 75	gnu/mapping/Environment:getCurrent	()Lgnu/mapping/Environment;
    //   7: invokestatic 81	kawa/Shell:run	(Lgnu/expr/Language;Lgnu/mapping/Environment;)Z
    //   10: pop
    //   11: getstatic 87	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   14: astore_1
    //   15: aload_0
    //   16: getfield 17	kawa/TelnetRepl:socket	Ljava/net/Socket;
    //   19: invokevirtual 92	java/net/Socket:close	()V
    //   22: aload_1
    //   23: areturn
    //   24: astore_1
    //   25: aload_0
    //   26: getfield 17	kawa/TelnetRepl:socket	Ljava/net/Socket;
    //   29: invokevirtual 92	java/net/Socket:close	()V
    //   32: aload_1
    //   33: athrow
    //   34: astore_2
    //   35: goto -3 -> 32
    //   38: astore_2
    //   39: aload_1
    //   40: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	41	0	this	TelnetRepl
    //   14	9	1	localValues	gnu.mapping.Values
    //   24	16	1	localObject	Object
    //   34	1	2	localIOException1	IOException
    //   38	1	2	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   0	15	24	finally
    //   25	32	34	java/io/IOException
    //   15	22	38	java/io/IOException
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\TelnetRepl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */