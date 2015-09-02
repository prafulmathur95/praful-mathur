package gnu.text;

import gnu.lists.CharSeq;
import java.io.Reader;

public class QueueReader
  extends Reader
  implements Appendable
{
  boolean EOFseen;
  char[] buffer;
  int limit;
  int mark;
  int pos;
  int readAheadLimit;
  
  public QueueReader append(char paramChar)
  {
    try
    {
      reserveSpace(1);
      char[] arrayOfChar = this.buffer;
      int i = this.limit;
      this.limit = (i + 1);
      arrayOfChar[i] = paramChar;
      notifyAll();
      return this;
    }
    finally {}
  }
  
  public QueueReader append(CharSequence paramCharSequence)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null) {
      localObject = "null";
    }
    return append((CharSequence)localObject, 0, ((CharSequence)localObject).length());
  }
  
  public QueueReader append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    Object localObject = paramCharSequence;
    if (paramCharSequence == null) {
      localObject = "null";
    }
    int k = paramInt2 - paramInt1;
    for (;;)
    {
      int j;
      try
      {
        reserveSpace(k);
        j = this.limit;
        paramCharSequence = this.buffer;
        if ((localObject instanceof String))
        {
          ((String)localObject).getChars(paramInt1, paramInt2, paramCharSequence, j);
          this.limit = (j + k);
          notifyAll();
          return this;
        }
        if ((localObject instanceof CharSeq))
        {
          ((CharSeq)localObject).getChars(paramInt1, paramInt2, paramCharSequence, j);
          continue;
        }
        i = paramInt1;
      }
      finally {}
      int i;
      paramInt1 = j;
      while (i < paramInt2)
      {
        paramCharSequence[paramInt1] = ((CharSequence)localObject).charAt(i);
        i += 1;
        paramInt1 += 1;
      }
    }
  }
  
  public void append(char[] paramArrayOfChar)
  {
    append(paramArrayOfChar, 0, paramArrayOfChar.length);
  }
  
  public void append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    try
    {
      reserveSpace(paramInt2);
      System.arraycopy(paramArrayOfChar, paramInt1, this.buffer, this.limit, paramInt2);
      this.limit += paramInt2;
      notifyAll();
      return;
    }
    finally
    {
      paramArrayOfChar = finally;
      throw paramArrayOfChar;
    }
  }
  
  public void appendEOF()
  {
    try
    {
      this.EOFseen = true;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void checkAvailable() {}
  
  public void close()
  {
    try
    {
      this.pos = 0;
      this.limit = 0;
      this.mark = 0;
      this.EOFseen = true;
      this.buffer = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void mark(int paramInt)
  {
    try
    {
      this.readAheadLimit = paramInt;
      this.mark = this.pos;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean markSupported()
  {
    return true;
  }
  
  /* Error */
  public int read()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 87	gnu/text/QueueReader:pos	I
    //   6: aload_0
    //   7: getfield 30	gnu/text/QueueReader:limit	I
    //   10: if_icmplt +33 -> 43
    //   13: aload_0
    //   14: getfield 83	gnu/text/QueueReader:EOFseen	Z
    //   17: istore_3
    //   18: iload_3
    //   19: ifeq +9 -> 28
    //   22: iconst_m1
    //   23: istore_2
    //   24: aload_0
    //   25: monitorexit
    //   26: iload_2
    //   27: ireturn
    //   28: aload_0
    //   29: invokevirtual 98	gnu/text/QueueReader:checkAvailable	()V
    //   32: aload_0
    //   33: invokevirtual 101	java/lang/Object:wait	()V
    //   36: goto -34 -> 2
    //   39: astore_1
    //   40: goto -38 -> 2
    //   43: aload_0
    //   44: getfield 28	gnu/text/QueueReader:buffer	[C
    //   47: astore_1
    //   48: aload_0
    //   49: getfield 87	gnu/text/QueueReader:pos	I
    //   52: istore_2
    //   53: aload_0
    //   54: iload_2
    //   55: iconst_1
    //   56: iadd
    //   57: putfield 87	gnu/text/QueueReader:pos	I
    //   60: aload_1
    //   61: iload_2
    //   62: caload
    //   63: istore_2
    //   64: goto -40 -> 24
    //   67: astore_1
    //   68: aload_0
    //   69: monitorexit
    //   70: aload_1
    //   71: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	72	0	this	QueueReader
    //   39	1	1	localInterruptedException	InterruptedException
    //   47	14	1	arrayOfChar	char[]
    //   67	4	1	localObject	Object
    //   23	41	2	i	int
    //   17	2	3	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   32	36	39	java/lang/InterruptedException
    //   2	18	67	finally
    //   28	32	67	finally
    //   32	36	67	finally
    //   43	60	67	finally
  }
  
  /* Error */
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_3
    //   3: ifne +17 -> 20
    //   6: iconst_0
    //   7: istore_2
    //   8: aload_0
    //   9: monitorexit
    //   10: iload_2
    //   11: ireturn
    //   12: aload_0
    //   13: invokevirtual 98	gnu/text/QueueReader:checkAvailable	()V
    //   16: aload_0
    //   17: invokevirtual 101	java/lang/Object:wait	()V
    //   20: aload_0
    //   21: getfield 87	gnu/text/QueueReader:pos	I
    //   24: aload_0
    //   25: getfield 30	gnu/text/QueueReader:limit	I
    //   28: if_icmplt +15 -> 43
    //   31: aload_0
    //   32: getfield 83	gnu/text/QueueReader:EOFseen	Z
    //   35: ifeq -23 -> 12
    //   38: iconst_m1
    //   39: istore_2
    //   40: goto -32 -> 8
    //   43: aload_0
    //   44: getfield 30	gnu/text/QueueReader:limit	I
    //   47: aload_0
    //   48: getfield 87	gnu/text/QueueReader:pos	I
    //   51: isub
    //   52: istore 6
    //   54: iload_3
    //   55: istore 5
    //   57: iload_3
    //   58: iload 6
    //   60: if_icmple +7 -> 67
    //   63: iload 6
    //   65: istore 5
    //   67: aload_0
    //   68: getfield 28	gnu/text/QueueReader:buffer	[C
    //   71: aload_0
    //   72: getfield 87	gnu/text/QueueReader:pos	I
    //   75: aload_1
    //   76: iload_2
    //   77: iload 5
    //   79: invokestatic 80	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   82: aload_0
    //   83: aload_0
    //   84: getfield 87	gnu/text/QueueReader:pos	I
    //   87: iload 5
    //   89: iadd
    //   90: putfield 87	gnu/text/QueueReader:pos	I
    //   93: iload 5
    //   95: istore_2
    //   96: goto -88 -> 8
    //   99: astore 4
    //   101: goto -81 -> 20
    //   104: astore_1
    //   105: aload_0
    //   106: monitorexit
    //   107: aload_1
    //   108: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	109	0	this	QueueReader
    //   0	109	1	paramArrayOfChar	char[]
    //   0	109	2	paramInt1	int
    //   0	109	3	paramInt2	int
    //   99	1	4	localInterruptedException	InterruptedException
    //   55	39	5	i	int
    //   52	12	6	j	int
    // Exception table:
    //   from	to	target	type
    //   16	20	99	java/lang/InterruptedException
    //   12	16	104	finally
    //   16	20	104	finally
    //   20	38	104	finally
    //   43	54	104	finally
    //   67	93	104	finally
  }
  
  /* Error */
  public boolean ready()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 87	gnu/text/QueueReader:pos	I
    //   6: aload_0
    //   7: getfield 30	gnu/text/QueueReader:limit	I
    //   10: if_icmplt +12 -> 22
    //   13: aload_0
    //   14: getfield 83	gnu/text/QueueReader:EOFseen	Z
    //   17: istore_2
    //   18: iload_2
    //   19: ifeq +9 -> 28
    //   22: iconst_1
    //   23: istore_2
    //   24: aload_0
    //   25: monitorexit
    //   26: iload_2
    //   27: ireturn
    //   28: iconst_0
    //   29: istore_2
    //   30: goto -6 -> 24
    //   33: astore_1
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_1
    //   37: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	38	0	this	QueueReader
    //   33	4	1	localObject	Object
    //   17	13	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	18	33	finally
  }
  
  protected void reserveSpace(int paramInt)
  {
    if (this.buffer == null) {
      this.buffer = new char[paramInt + 100];
    }
    while (this.buffer.length >= this.limit + paramInt) {
      return;
    }
    resize(paramInt);
  }
  
  public void reset()
  {
    try
    {
      if (this.readAheadLimit > 0) {
        this.pos = this.mark;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  void resize(int paramInt)
  {
    int i = this.limit - this.pos;
    if ((this.readAheadLimit > 0) && (this.pos - this.mark <= this.readAheadLimit))
    {
      i = this.limit - this.mark;
      if (this.buffer.length >= i + paramInt) {
        break label116;
      }
    }
    label116:
    for (char[] arrayOfChar = new char[i * 2 + paramInt];; arrayOfChar = this.buffer)
    {
      System.arraycopy(this.buffer, this.mark, arrayOfChar, 0, i);
      this.buffer = arrayOfChar;
      this.pos -= this.mark;
      this.mark = 0;
      this.limit = i;
      return;
      this.mark = this.pos;
      break;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\QueueReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */