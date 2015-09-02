package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.LocationProc;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.Scheme;

public class parameters
  extends ModuleBody
{
  public static final ModuleMethod $Prvt$as$Mnlocation$Pc;
  public static final Macro $Prvt$parameterize$Pc;
  public static final parameters $instance;
  static final SimpleSymbol Lit0;
  static final SimpleSymbol Lit1;
  static final SimpleSymbol Lit10;
  static final SimpleSymbol Lit11;
  static final SimpleSymbol Lit12 = (SimpleSymbol)new SimpleSymbol("save").readResolve();
  static final SimpleSymbol Lit2;
  static final SyntaxRules Lit3;
  static final SimpleSymbol Lit4;
  static final SyntaxRules Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod make$Mnparameter;
  public static final Macro parameterize;
  
  static
  {
    Lit11 = (SimpleSymbol)new SimpleSymbol("quasiquote").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("gnu.mapping.Location").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("$lookup$").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("v").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("p").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("begin").readResolve();
    Object localObject1 = (SimpleSymbol)new SimpleSymbol("parameterize").readResolve();
    Lit4 = (SimpleSymbol)localObject1;
    SyntaxRule localSyntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\b\003", new Object[0], 1), "\000", "\021\030\004\002", new Object[] { Lit6 }, 0);
    Object localObject2 = new SyntaxPattern("\f\030<,\f\007\f\017\b\023\033", new Object[0], 4);
    SimpleSymbol localSimpleSymbol1 = (SimpleSymbol)new SimpleSymbol("parameterize%").readResolve();
    Lit2 = localSimpleSymbol1;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\001\001\000\000", "\021\030\0041!\t\003\b\013\022\t\020\032", new Object[] { localSimpleSymbol1 }, 0);
    Lit5 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localSyntaxRule, localObject2 }, 4);
    localObject1 = Lit2;
    localSyntaxRule = new SyntaxRule(new SyntaxPattern("\f\030\f\b\f\007\013", new Object[0], 2), "\001\000", "\021\030\004!\021\030\f\n\b\021\030\f\003", new Object[] { (SimpleSymbol)new SimpleSymbol("try-finally").readResolve(), Lit6 }, 0);
    localObject2 = new SyntaxPattern("\f\030<,\f\007\f\017\b\023\f\037#", new Object[0], 5);
    localSimpleSymbol1 = (SimpleSymbol)new SimpleSymbol("let*").readResolve();
    SimpleSymbol localSimpleSymbol2 = Lit7;
    SimpleSymbol localSimpleSymbol3 = (SimpleSymbol)new SimpleSymbol("::").readResolve();
    SimpleSymbol localSimpleSymbol4 = (SimpleSymbol)new SimpleSymbol("<gnu.mapping.Location>").readResolve();
    SimpleSymbol localSimpleSymbol5 = (SimpleSymbol)new SimpleSymbol("as-location%").readResolve();
    Lit1 = localSimpleSymbol5;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\001\001\000\001\000", "\021\030\004Áy\021\030\f\021\030\024\021\030\034\b\021\030$\b\003)\021\030,\b\013\0304\b\021\030<\t\022!\021\030D\033\"", new Object[] { localSimpleSymbol1, localSimpleSymbol2, localSimpleSymbol3, localSimpleSymbol4, localSimpleSymbol5, Lit8, PairWithPosition.make(PairWithPosition.make(Lit12, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit9, Pair.make(Lit10, Pair.make(Pair.make(Lit11, Pair.make((SimpleSymbol)new SimpleSymbol("setWithSave").readResolve(), LList.Empty)), LList.Empty)), "parameters.scm", 122893), PairWithPosition.make(Lit7, PairWithPosition.make(Lit8, LList.Empty, "parameters.scm", 122928), "parameters.scm", 122926), "parameters.scm", 122892), LList.Empty, "parameters.scm", 122892), "parameters.scm", 122886), LList.Empty, "parameters.scm", 122886), Lit2, PairWithPosition.make(PairWithPosition.make(Lit9, Pair.make(Lit10, Pair.make(Pair.make(Lit11, Pair.make((SimpleSymbol)new SimpleSymbol("setRestore").readResolve(), LList.Empty)), LList.Empty)), "parameters.scm", 131083), PairWithPosition.make(Lit7, PairWithPosition.make(Lit12, LList.Empty, "parameters.scm", 131117), "parameters.scm", 131115), "parameters.scm", 131082) }, 0);
    Lit3 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localSyntaxRule, localObject2 }, 5);
    Lit0 = (SimpleSymbol)new SimpleSymbol("make-parameter").readResolve();
    $instance = new parameters();
    localObject1 = $instance;
    make$Mnparameter = new ModuleMethod((ModuleBody)localObject1, 1, Lit0, 8193);
    $Prvt$as$Mnlocation$Pc = new ModuleMethod((ModuleBody)localObject1, 3, Lit1, 4097);
    $Prvt$parameterize$Pc = Macro.make(Lit2, Lit3, $instance);
    parameterize = Macro.make(Lit4, Lit5, $instance);
    $instance.run();
  }
  
  public parameters()
  {
    ModuleInfo.register(this);
  }
  
  public static Location asLocation$Pc(Object paramObject)
  {
    if ((paramObject instanceof LocationProc)) {}
    for (;;)
    {
      try
      {
        localObject = (LocationProc)paramObject;
        paramObject = ((LocationProc)localObject).getLocation();
        localObject = paramObject;
        if (!(paramObject instanceof ThreadLocation)) {}
      }
      catch (ClassCastException localClassCastException1)
      {
        Object localObject;
        throw new WrongType(localClassCastException1, "gnu.mapping.LocationProc.getLocation()", 1, paramObject);
      }
      try
      {
        localObject = (ThreadLocation)paramObject;
        localObject = ((ThreadLocation)localObject).getLocation();
        return (Location)localObject;
      }
      catch (ClassCastException localClassCastException2)
      {
        throw new WrongType(localClassCastException2, "gnu.mapping.ThreadLocation.getLocation()", 1, paramObject);
      }
      paramObject = (Location)paramObject;
    }
  }
  
  public static LocationProc makeParameter(Object paramObject)
  {
    return makeParameter(paramObject, null);
  }
  
  public static LocationProc makeParameter(Object paramObject1, Object paramObject2)
  {
    Object localObject = paramObject1;
    if (paramObject2 != null) {
      localObject = Scheme.applyToArgs.apply2(paramObject2, paramObject1);
    }
    paramObject1 = new ThreadLocation();
    ((ThreadLocation)paramObject1).setGlobal(localObject);
    try
    {
      localObject = (Procedure)paramObject2;
      return new LocationProc((Location)paramObject1, (Procedure)localObject);
    }
    catch (ClassCastException paramObject1)
    {
      throw new WrongType((ClassCastException)paramObject1, "gnu.mapping.LocationProc.<init>(gnu.mapping.Location,gnu.mapping.Procedure)", 2, paramObject2);
    }
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 2: 
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 1: 
      return makeParameter(paramObject);
    }
    return asLocation$Pc(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    if (paramModuleMethod.selector == 1) {
      return makeParameter(paramObject1, paramObject2);
    }
    return super.apply2(paramModuleMethod, paramObject1, paramObject2);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 2: 
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 3: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    }
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 1)
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
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\parameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */