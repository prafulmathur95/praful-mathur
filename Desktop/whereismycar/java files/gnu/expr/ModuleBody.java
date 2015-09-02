package gnu.expr;

import gnu.lists.Consumer;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.text.WriterManager;
import kawa.Shell;

public abstract class ModuleBody
  extends Procedure0
{
  private static int exitCounter;
  private static boolean mainPrintValues;
  protected boolean runDone;
  
  /* Error */
  public static void exitDecrement()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 17	gnu/expr/ModuleBody:exitCounter	I
    //   6: istore_1
    //   7: iload_1
    //   8: ifle +15 -> 23
    //   11: iload_1
    //   12: iconst_1
    //   13: isub
    //   14: istore_1
    //   15: iload_1
    //   16: ifne +11 -> 27
    //   19: iconst_0
    //   20: invokestatic 23	java/lang/System:exit	(I)V
    //   23: ldc 2
    //   25: monitorexit
    //   26: return
    //   27: iload_1
    //   28: putstatic 17	gnu/expr/ModuleBody:exitCounter	I
    //   31: goto -8 -> 23
    //   34: astore_0
    //   35: ldc 2
    //   37: monitorexit
    //   38: aload_0
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   34	5	0	localObject	Object
    //   6	22	1	i	int
    // Exception table:
    //   from	to	target	type
    //   3	7	34	finally
    //   19	23	34	finally
    //   27	31	34	finally
  }
  
  public static void exitIncrement()
  {
    try
    {
      if (exitCounter == 0) {
        exitCounter += 1;
      }
      exitCounter += 1;
      return;
    }
    finally {}
  }
  
  public static boolean getMainPrintValues()
  {
    return mainPrintValues;
  }
  
  public static void runCleanup(CallContext paramCallContext, Throwable paramThrowable, Consumer paramConsumer)
  {
    Throwable localThrowable1 = paramThrowable;
    if (paramThrowable == null) {}
    try
    {
      paramCallContext.runUntilDone();
      localThrowable1 = paramThrowable;
    }
    catch (Throwable localThrowable2)
    {
      for (;;) {}
      if (!(localThrowable2 instanceof Error)) {
        break label49;
      }
      throw ((Error)localThrowable2);
      label49:
      throw new WrappedException(localThrowable2);
    }
    paramCallContext.consumer = paramConsumer;
    if (localThrowable1 != null) {
      if ((localThrowable1 instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable1);
      }
    }
  }
  
  public static void setMainPrintValues(boolean paramBoolean)
  {
    mainPrintValues = paramBoolean;
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    if (paramCallContext.pc == 0) {
      run(paramCallContext);
    }
  }
  
  public Object apply0()
    throws Throwable
  {
    CallContext localCallContext = CallContext.getInstance();
    match0(localCallContext);
    return localCallContext.runUntilValue();
  }
  
  public Object apply0(ModuleMethod paramModuleMethod)
    throws Throwable
  {
    return applyN(paramModuleMethod, Values.noArgs);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    throws Throwable
  {
    return applyN(paramModuleMethod, new Object[] { paramObject });
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
    throws Throwable
  {
    return applyN(paramModuleMethod, new Object[] { paramObject1, paramObject2 });
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
    throws Throwable
  {
    return applyN(paramModuleMethod, new Object[] { paramObject1, paramObject2, paramObject3 });
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    throws Throwable
  {
    return applyN(paramModuleMethod, new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 });
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
    throws Throwable
  {
    int i = paramArrayOfObject.length;
    int j = paramModuleMethod.numArgs();
    if ((i >= (j & 0xFFF)) && ((j < 0) || (i <= j >> 12))) {}
    switch (i)
    {
    default: 
      throw new WrongArguments(paramModuleMethod, i);
    case 0: 
      return apply0(paramModuleMethod);
    case 1: 
      return apply1(paramModuleMethod, paramArrayOfObject[0]);
    case 2: 
      return apply2(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1]);
    case 3: 
      return apply3(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2]);
    }
    return apply4(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3]);
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
  {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 0) {
      return 0xFFF10000 | j;
    }
    if (i < 0) {
      return matchN(paramModuleMethod, ProcedureN.noArgs, paramCallContext);
    }
    paramCallContext.count = 0;
    paramCallContext.where = 0;
    paramCallContext.next = 0;
    paramCallContext.proc = paramModuleMethod;
    return 0;
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 1) {
      return 0xFFF10000 | j;
    }
    if (i >= 0)
    {
      i >>= 12;
      if (i < 1) {
        return 0xFFF20000 | i;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.count = 1;
      paramCallContext.where = 1;
      paramCallContext.next = 0;
      paramCallContext.proc = paramModuleMethod;
      return 0;
    }
    return matchN(paramModuleMethod, new Object[] { paramObject }, paramCallContext);
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 2) {
      return 0xFFF10000 | j;
    }
    if (i >= 0)
    {
      i >>= 12;
      if (i < 2) {
        return 0xFFF20000 | i;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.count = 2;
      paramCallContext.where = 33;
      paramCallContext.next = 0;
      paramCallContext.proc = paramModuleMethod;
      return 0;
    }
    return matchN(paramModuleMethod, new Object[] { paramObject1, paramObject2 }, paramCallContext);
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 3) {
      return 0xFFF10000 | j;
    }
    if (i >= 0)
    {
      i >>= 12;
      if (i < 3) {
        return 0xFFF20000 | i;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.count = 3;
      paramCallContext.where = 801;
      paramCallContext.next = 0;
      paramCallContext.proc = paramModuleMethod;
      return 0;
    }
    return matchN(paramModuleMethod, new Object[] { paramObject1, paramObject2, paramObject3 }, paramCallContext);
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
  {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 4) {
      return 0xFFF10000 | j;
    }
    if (i >= 0)
    {
      i >>= 12;
      if (i < 4) {
        return 0xFFF20000 | i;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.count = 4;
      paramCallContext.where = 17185;
      paramCallContext.next = 0;
      paramCallContext.proc = paramModuleMethod;
      return 0;
    }
    return matchN(paramModuleMethod, new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 }, paramCallContext);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (paramArrayOfObject.length < j) {
      return 0xFFF10000 | j;
    }
    if (i >= 0) {
      switch (paramArrayOfObject.length)
      {
      default: 
        i >>= 12;
        if (paramArrayOfObject.length > i) {
          return 0xFFF20000 | i;
        }
        break;
      case 0: 
        return match0(paramModuleMethod, paramCallContext);
      case 1: 
        return match1(paramModuleMethod, paramArrayOfObject[0], paramCallContext);
      case 2: 
        return match2(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramCallContext);
      case 3: 
        return match3(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramCallContext);
      case 4: 
        return match4(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramCallContext);
      }
    }
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.count = paramArrayOfObject.length;
    paramCallContext.where = 0;
    paramCallContext.next = 0;
    paramCallContext.proc = paramModuleMethod;
    return 0;
  }
  
  public void run()
  {
    try
    {
      if (this.runDone) {
        return;
      }
      this.runDone = true;
      run(VoidConsumer.instance);
      return;
    }
    finally {}
  }
  
  public void run(Consumer paramConsumer)
  {
    CallContext localCallContext = CallContext.getInstance();
    Consumer localConsumer = localCallContext.consumer;
    localCallContext.consumer = paramConsumer;
    try
    {
      run(localCallContext);
      paramConsumer = null;
    }
    catch (Throwable paramConsumer)
    {
      for (;;) {}
    }
    runCleanup(localCallContext, paramConsumer, localConsumer);
  }
  
  public void run(CallContext paramCallContext)
    throws Throwable
  {}
  
  public final void runAsMain()
  {
    WriterManager.instance.registerShutdownHook();
    try
    {
      CallContext localCallContext = CallContext.getInstance();
      if (getMainPrintValues())
      {
        OutPort localOutPort = OutPort.outDefault();
        localCallContext.consumer = Shell.getOutputConsumer(localOutPort);
        run(localCallContext);
        localCallContext.runUntilDone();
        localOutPort.freshLine();
      }
      for (;;)
      {
        OutPort.runCleanups();
        exitDecrement();
        return;
        run();
        localCallContext.runUntilDone();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      OutPort.runCleanups();
      System.exit(-1);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ModuleBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */