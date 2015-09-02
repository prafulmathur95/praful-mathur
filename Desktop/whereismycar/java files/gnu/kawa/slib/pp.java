package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.math.IntNum;
import kawa.lib.ports;

public class pp
  extends ModuleBody
{
  public static final pp $instance;
  static final IntNum Lit0;
  static final SimpleSymbol Lit1 = (SimpleSymbol)new SimpleSymbol("pretty-print").readResolve();
  public static final ModuleMethod pretty$Mnprint;
  
  static
  {
    Lit0 = IntNum.make(79);
    $instance = new pp();
    pretty$Mnprint = new ModuleMethod($instance, 2, Lit1, 8193);
    $instance.run();
  }
  
  public pp()
  {
    ModuleInfo.register(this);
  }
  
  public static Object prettyPrint(Object paramObject)
  {
    return prettyPrint(paramObject, ports.current$Mnoutput$Mnport.apply0());
  }
  
  public static Object prettyPrint(Object paramObject1, Object paramObject2)
  {
    frame localframe = new frame();
    localframe.port = paramObject2;
    return genwrite.genericWrite(paramObject1, Boolean.FALSE, Lit0, localframe.lambda$Fn1);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    if (paramModuleMethod.selector == 2) {
      return prettyPrint(paramObject);
    }
    return super.apply1(paramModuleMethod, paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    if (paramModuleMethod.selector == 2) {
      return prettyPrint(paramObject1, paramObject2);
    }
    return super.apply2(paramModuleMethod, paramObject1, paramObject2);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 2)
    {
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    }
    return super.match1(paramModuleMethod, paramObject, paramCallContext);
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 2)
    {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    }
    return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
  
  public class frame
    extends ModuleBody
  {
    final ModuleMethod lambda$Fn1;
    Object port;
    
    public frame()
    {
      this$1 = new ModuleMethod(this, 1, null, 4097);
      this$1.setProperty("source-location", "pp.scm:9");
      this.lambda$Fn1 = this$1;
    }
    
    public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
    {
      if (paramModuleMethod.selector == 1) {
        return lambda1(paramObject);
      }
      return super.apply1(paramModuleMethod, paramObject);
    }
    
    Boolean lambda1(Object paramObject)
    {
      ports.display(paramObject, this.port);
      return Boolean.TRUE;
    }
    
    public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
    {
      if (paramModuleMethod.selector == 1)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\pp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */