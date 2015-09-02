package kawa.lib;

import gnu.expr.Compilation;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.GetModuleClass;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import kawa.lang.Macro;
import kawa.lang.Pattern;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.standard.syntax_case;

public class misc_syntax
  extends ModuleBody
{
  public static final Location $Prvt$define$Mnconstant;
  public static final misc_syntax $instance;
  static final SimpleSymbol Lit0;
  static final SyntaxPattern Lit1;
  static final SimpleSymbol Lit10;
  static final SyntaxRules Lit11;
  static final SimpleSymbol Lit12;
  static final SyntaxPattern Lit13;
  static final SyntaxTemplate Lit14;
  static final SyntaxTemplate Lit15;
  static final SyntaxPattern Lit16;
  static final SyntaxTemplate Lit17;
  static final SimpleSymbol Lit18;
  static final SyntaxPattern Lit19;
  static final SyntaxTemplate Lit2;
  static final SyntaxTemplate Lit20;
  static final SyntaxTemplate Lit21;
  static final SyntaxTemplate Lit22;
  static final SimpleSymbol Lit23;
  static final SimpleSymbol Lit24;
  static final SimpleSymbol Lit25;
  static final SimpleSymbol Lit26;
  static final SimpleSymbol Lit27;
  static final SimpleSymbol Lit28;
  static final SimpleSymbol Lit29;
  static final SyntaxTemplate Lit3;
  static final SimpleSymbol Lit30;
  static final SimpleSymbol Lit31 = (SimpleSymbol)new SimpleSymbol("%test-begin").readResolve();
  static final SyntaxTemplate Lit4;
  static final SyntaxPattern Lit5;
  static final SimpleSymbol Lit6;
  static final SyntaxRules Lit7;
  static final SimpleSymbol Lit8;
  static final SyntaxPattern Lit9;
  public static final Macro include;
  public static final Macro include$Mnrelative;
  public static final Macro module$Mnuri;
  public static final Macro provide;
  public static final Macro resource$Mnurl;
  public static final Macro test$Mnbegin;
  
  static
  {
    Lit30 = (SimpleSymbol)new SimpleSymbol("quote").readResolve();
    Lit29 = (SimpleSymbol)new SimpleSymbol("require").readResolve();
    Lit28 = (SimpleSymbol)new SimpleSymbol("else").readResolve();
    Lit27 = (SimpleSymbol)new SimpleSymbol("cond-expand").readResolve();
    Lit26 = (SimpleSymbol)new SimpleSymbol("srfi-64").readResolve();
    Lit25 = (SimpleSymbol)new SimpleSymbol("begin").readResolve();
    Lit24 = (SimpleSymbol)new SimpleSymbol("quasiquote").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("$lookup$").readResolve();
    Lit22 = new SyntaxTemplate("\001\001", "\013", new Object[0], 0);
    Lit21 = new SyntaxTemplate("\001\001", "\013", new Object[0], 0);
    Lit20 = new SyntaxTemplate("\001\001", "\b\013", new Object[0], 0);
    Lit19 = new SyntaxPattern("\f\007\f\017\b", new Object[0], 2);
    Lit18 = (SimpleSymbol)new SimpleSymbol("include-relative").readResolve();
    Lit17 = new SyntaxTemplate("\001\001\003", "\021\030\004\b\025\023", new Object[] { Lit25 }, 1);
    Lit16 = new SyntaxPattern("\r\027\020\b\b", new Object[0], 3);
    Lit15 = new SyntaxTemplate("\001\001", "\003", new Object[0], 0);
    Lit14 = new SyntaxTemplate("\001\001", "\013", new Object[0], 0);
    Lit13 = new SyntaxPattern("\f\007\f\017\b", new Object[0], 2);
    Lit12 = (SimpleSymbol)new SimpleSymbol("include").readResolve();
    Object localObject1 = (SimpleSymbol)new SimpleSymbol("resource-url").readResolve();
    Lit10 = (SimpleSymbol)localObject1;
    Object localObject2 = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
    Object localObject3 = PairWithPosition.make(Lit23, Pair.make((SimpleSymbol)new SimpleSymbol("gnu.text.URLPath").readResolve(), Pair.make(Pair.make(Lit24, Pair.make((SimpleSymbol)new SimpleSymbol("valueOf").readResolve(), LList.Empty)), LList.Empty)), "misc_syntax.scm", 155655);
    SimpleSymbol localSimpleSymbol1 = Lit23;
    SimpleSymbol localSimpleSymbol2 = Lit23;
    SimpleSymbol localSimpleSymbol3 = (SimpleSymbol)new SimpleSymbol("module-uri").readResolve();
    Lit8 = localSimpleSymbol3;
    localObject2 = new SyntaxRule((SyntaxPattern)localObject2, "\001", "\021\030\004\b\b\021\030\f\b\021\030\fa\b\021\030\f)\021\030\024\b\003\030\034\030$\030,", new Object[] { localObject3, localSimpleSymbol1, PairWithPosition.make(localSimpleSymbol2, Pair.make(PairWithPosition.make(localSimpleSymbol3, LList.Empty, "misc_syntax.scm", 159755), Pair.make(Pair.make(Lit24, Pair.make((SimpleSymbol)new SimpleSymbol("resolve").readResolve(), LList.Empty)), LList.Empty)), "misc_syntax.scm", 159755), Pair.make(Pair.make(Lit24, Pair.make((SimpleSymbol)new SimpleSymbol("toURL").readResolve(), LList.Empty)), LList.Empty), Pair.make(Pair.make(Lit24, Pair.make((SimpleSymbol)new SimpleSymbol("openConnection").readResolve(), LList.Empty)), LList.Empty), Pair.make(Pair.make(Lit24, Pair.make((SimpleSymbol)new SimpleSymbol("getURL").readResolve(), LList.Empty)), LList.Empty) }, 0);
    Lit11 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 1);
    Lit9 = new SyntaxPattern("\f\007\b", new Object[0], 1);
    localObject1 = (SimpleSymbol)new SimpleSymbol("test-begin").readResolve();
    Lit6 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\021\030\f\b\021\030\024\t\003\030\034", new Object[] { Lit25, PairWithPosition.make(Lit27, PairWithPosition.make(PairWithPosition.make(Lit26, PairWithPosition.make(Values.empty, LList.Empty, "misc_syntax.scm", 86046), "misc_syntax.scm", 86037), PairWithPosition.make(PairWithPosition.make(Lit28, PairWithPosition.make(PairWithPosition.make(Lit29, PairWithPosition.make(PairWithPosition.make(Lit30, PairWithPosition.make(Lit26, LList.Empty, "misc_syntax.scm", 86070), "misc_syntax.scm", 86070), LList.Empty, "misc_syntax.scm", 86069), "misc_syntax.scm", 86060), LList.Empty, "misc_syntax.scm", 86060), "misc_syntax.scm", 86054), LList.Empty, "misc_syntax.scm", 86054), "misc_syntax.scm", 86037), "misc_syntax.scm", 86024), Lit31, PairWithPosition.make(Boolean.FALSE, LList.Empty, "misc_syntax.scm", 90144) }, 0);
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\021\030\f\b\021\030\024\t\003\b\013", new Object[] { Lit25, PairWithPosition.make(Lit27, PairWithPosition.make(PairWithPosition.make(Lit26, PairWithPosition.make(Values.empty, LList.Empty, "misc_syntax.scm", 102430), "misc_syntax.scm", 102421), PairWithPosition.make(PairWithPosition.make(Lit28, PairWithPosition.make(PairWithPosition.make(Lit29, PairWithPosition.make(PairWithPosition.make(Lit30, PairWithPosition.make(Lit26, LList.Empty, "misc_syntax.scm", 102454), "misc_syntax.scm", 102454), LList.Empty, "misc_syntax.scm", 102453), "misc_syntax.scm", 102444), LList.Empty, "misc_syntax.scm", 102444), "misc_syntax.scm", 102438), LList.Empty, "misc_syntax.scm", 102438), "misc_syntax.scm", 102421), "misc_syntax.scm", 102408), Lit31 }, 0);
    Lit7 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2, localObject3 }, 2);
    Lit5 = new SyntaxPattern("\f\007\013", new Object[0], 2);
    Lit4 = new SyntaxTemplate("\001\001\001", "\030\004", new Object[] { PairWithPosition.make((SimpleSymbol)new SimpleSymbol("::").readResolve(), PairWithPosition.make((SimpleSymbol)new SimpleSymbol("<int>").readResolve(), PairWithPosition.make(IntNum.make(123), LList.Empty, "misc_syntax.scm", 53270), "misc_syntax.scm", 53264), "misc_syntax.scm", 53260) }, 0);
    Lit3 = new SyntaxTemplate("\001\001\001", "\023", new Object[0], 0);
    Lit2 = new SyntaxTemplate("\001\001\001", "\030\004", new Object[] { (SimpleSymbol)new SimpleSymbol("define-constant").readResolve() }, 0);
    Lit1 = new SyntaxPattern("\f\007,\f\017\f\027\b\b", new Object[0], 3);
    Lit0 = (SimpleSymbol)new SimpleSymbol("provide").readResolve();
    $instance = new misc_syntax();
    $Prvt$define$Mnconstant = StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnconstant");
    localObject2 = Lit0;
    localObject1 = $instance;
    provide = Macro.make(localObject2, new ModuleMethod((ModuleBody)localObject1, 1, null, 4097), $instance);
    test$Mnbegin = Macro.make(Lit6, Lit7, $instance);
    localObject2 = Lit8;
    localObject3 = new ModuleMethod((ModuleBody)localObject1, 2, null, 4097);
    ((PropertySet)localObject3).setProperty("source-location", "misc_syntax.scm:29");
    module$Mnuri = Macro.make(localObject2, (Procedure)localObject3, $instance);
    resource$Mnurl = Macro.make(Lit10, Lit11, $instance);
    localObject2 = Lit12;
    localObject3 = new ModuleMethod((ModuleBody)localObject1, 3, null, 4097);
    ((PropertySet)localObject3).setProperty("source-location", "misc_syntax.scm:54");
    include = Macro.make(localObject2, (Procedure)localObject3, $instance);
    include$Mnrelative = Macro.make(Lit18, new ModuleMethod((ModuleBody)localObject1, 4, null, 4097), $instance);
    $instance.run();
  }
  
  public misc_syntax()
  {
    ModuleInfo.register(this);
  }
  
  static Object lambda1(Object paramObject)
  {
    Object[] arrayOfObject = SyntaxPattern.allocVars(3, null);
    Object localObject1;
    if (Lit1.match(paramObject, arrayOfObject, 0))
    {
      localObject1 = TemplateScope.make();
      localObject1 = Lit2.execute(arrayOfObject, (TemplateScope)localObject1);
      localObject2 = TemplateScope.make();
      localObject2 = std_syntax.syntaxObject$To$Datum(Lit3.execute(arrayOfObject, (TemplateScope)localObject2));
    }
    try
    {
      Symbol localSymbol = (Symbol)localObject2;
      paramObject = std_syntax.datum$To$SyntaxObject(paramObject, misc.string$To$Symbol(strings.stringAppend(new Object[] { "%provide%", misc.symbol$To$String(localSymbol) })));
      localObject2 = TemplateScope.make();
      return lists.cons(localObject1, lists.cons(paramObject, Lit4.execute(arrayOfObject, (TemplateScope)localObject2)));
    }
    catch (ClassCastException paramObject)
    {
      throw new WrongType((ClassCastException)paramObject, "symbol->string", 1, localObject2);
    }
    if (Lit5.match(paramObject, arrayOfObject, 0))
    {
      if (("provide requires a quoted feature-name" instanceof Object[])) {}
      for (arrayOfObject = (Object[])"provide requires a quoted feature-name";; arrayOfObject = new Object[] { "provide requires a quoted feature-name" }) {
        return prim_syntax.syntaxError(paramObject, arrayOfObject);
      }
    }
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda2(Object paramObject)
  {
    Object[] arrayOfObject = SyntaxPattern.allocVars(1, null);
    if (Lit9.match(paramObject, arrayOfObject, 0)) {
      return GetModuleClass.getModuleClassURI(Compilation.getCurrent());
    }
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda3(Object paramObject)
  {
    Object[] arrayOfObject = SyntaxPattern.allocVars(2, null);
    Object localObject1;
    Object localObject2;
    if (Lit13.match(paramObject, arrayOfObject, 0))
    {
      paramObject = TemplateScope.make();
      paramObject = std_syntax.syntaxObject$To$Datum(Lit14.execute(arrayOfObject, (TemplateScope)paramObject));
      localObject1 = TemplateScope.make();
      localObject2 = Lit15.execute(arrayOfObject, (TemplateScope)localObject1);
      localObject1 = new frame();
      ((frame)localObject1).k = localObject2;
    }
    try
    {
      localObject2 = Path.valueOf(paramObject);
      ((frame)localObject1).p = ports.openInputFile((Path)localObject2);
      paramObject = ((frame)localObject1).lambda4f();
      arrayOfObject = SyntaxPattern.allocVars(3, arrayOfObject);
      if (Lit16.match(paramObject, arrayOfObject, 0))
      {
        paramObject = TemplateScope.make();
        return Lit17.execute(arrayOfObject, (TemplateScope)paramObject);
      }
      return syntax_case.error("syntax-case", paramObject);
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "open-input-file", 1, paramObject);
    }
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda5(Object paramObject)
  {
    Object[] arrayOfObject = SyntaxPattern.allocVars(2, null);
    if (Lit19.match(paramObject, arrayOfObject, 0))
    {
      paramObject = TemplateScope.make();
      paramObject = std_syntax.syntaxObject$To$Datum(Lit20.execute(arrayOfObject, (TemplateScope)paramObject));
    }
    try
    {
      Object localObject1 = (PairWithPosition)paramObject;
      paramObject = Path.valueOf(((PairWithPosition)localObject1).getFileName());
      localObject1 = ((PairWithPosition)localObject1).getCar().toString();
      Object localObject2 = TemplateScope.make();
      localObject2 = std_syntax.datum$To$SyntaxObject(Lit21.execute(arrayOfObject, (TemplateScope)localObject2), Lit12);
      TemplateScope localTemplateScope = TemplateScope.make();
      return LList.list2(localObject2, std_syntax.datum$To$SyntaxObject(Lit22.execute(arrayOfObject, localTemplateScope), ((Path)paramObject).resolve((String)localObject1).toString()));
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "path-pair", -2, paramObject);
    }
    return syntax_case.error("syntax-case", paramObject);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 1: 
      return lambda1(paramObject);
    case 2: 
      return lambda2(paramObject);
    case 3: 
      return lambda3(paramObject);
    }
    return lambda5(paramObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 4: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 3: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 2: 
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
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
  
  public class frame
    extends ModuleBody
  {
    Object k;
    InPort p;
    
    public Object lambda4f()
    {
      Object localObject = ports.read(this.p);
      if (ports.isEofObject(localObject))
      {
        ports.closeInputPort(this.p);
        return LList.Empty;
      }
      return new Pair(std_syntax.datum$To$SyntaxObject(this.k, localObject), lambda4f());
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\misc_syntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */