package gnu.kawa.android;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import kawa.lang.Macro;
import kawa.lang.Pattern;
import kawa.lang.Quote;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.lists;
import kawa.standard.syntax_case;

public class defs
  extends ModuleBody
{
  public static final ModuleMethod $Pcprocess$Mnactivity;
  public static final defs $instance;
  static final SyntaxPattern Lit0;
  static final SyntaxTemplate Lit1;
  static final SimpleSymbol Lit10;
  static final SimpleSymbol Lit11;
  static final SyntaxPattern Lit12;
  static final SyntaxTemplate Lit13;
  static final SyntaxTemplate Lit14;
  static final SyntaxTemplate Lit15;
  static final SyntaxTemplate Lit16;
  static final SimpleSymbol Lit17;
  static final SimpleSymbol Lit18;
  static final SimpleSymbol Lit19;
  static final SyntaxTemplate Lit2;
  static final SimpleSymbol Lit20;
  static final SimpleSymbol Lit21;
  static final SimpleSymbol Lit22;
  static final SimpleSymbol Lit23;
  static final SimpleSymbol Lit24;
  static final SimpleSymbol Lit25 = (SimpleSymbol)new SimpleSymbol("quote").readResolve();
  static final SyntaxPattern Lit3;
  static final SyntaxTemplate Lit4;
  static final SyntaxTemplate Lit5;
  static final SyntaxPattern Lit6;
  static final SyntaxTemplate Lit7;
  static final SyntaxTemplate Lit8;
  static final SyntaxPattern Lit9;
  public static final Macro activity;
  
  public static Object $PcProcessActivity(Object paramObject)
  {
    Object[] arrayOfObject = SyntaxPattern.allocVars(3, null);
    TemplateScope localTemplateScope;
    if (Lit0.match(paramObject, arrayOfObject, 0))
    {
      paramObject = TemplateScope.make();
      paramObject = Lit1.execute(arrayOfObject, (TemplateScope)paramObject);
      localTemplateScope = TemplateScope.make();
      return lists.cons(paramObject, $PcProcessActivity(Lit2.execute(arrayOfObject, localTemplateScope)));
    }
    if (Lit3.match(paramObject, arrayOfObject, 0))
    {
      paramObject = TemplateScope.make();
      paramObject = Lit4.execute(arrayOfObject, (TemplateScope)paramObject);
      localTemplateScope = TemplateScope.make();
      return lists.cons(paramObject, $PcProcessActivity(Lit5.execute(arrayOfObject, localTemplateScope)));
    }
    if (Lit6.match(paramObject, arrayOfObject, 0))
    {
      paramObject = TemplateScope.make();
      paramObject = Lit7.execute(arrayOfObject, (TemplateScope)paramObject);
      localTemplateScope = TemplateScope.make();
      return lists.cons(paramObject, $PcProcessActivity(Lit8.execute(arrayOfObject, localTemplateScope)));
    }
    if (Lit9.match(paramObject, arrayOfObject, 0)) {
      return LList.Empty;
    }
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static
  {
    Lit24 = (SimpleSymbol)new SimpleSymbol("invoke-special").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("void").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("android.os.Bundle").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("this").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("savedInstanceState").readResolve();
    Lit19 = (SimpleSymbol)new SimpleSymbol("onCreate").readResolve();
    Lit18 = (SimpleSymbol)new SimpleSymbol("android.app.Activity").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("::").readResolve();
    Lit16 = new SyntaxTemplate("\001\001\000", "\020", new Object[0], 0);
    Lit15 = new SyntaxTemplate("\001\001\000", "\022", new Object[0], 0);
    Lit14 = new SyntaxTemplate("\001\001\000", "\030\004", new Object[] { PairWithPosition.make(Lit18, LList.Empty, "defs.scm", 86048) }, 0);
    Lit13 = new SyntaxTemplate("\001\001\000", "\021\030\004\b\013", new Object[] { (SimpleSymbol)new SimpleSymbol("define-simple-class").readResolve() }, 0);
    Lit12 = new SyntaxPattern("\f\007\f\017\023", new Object[0], 3);
    Lit11 = (SimpleSymbol)new SimpleSymbol("activity").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("%process-activity").readResolve();
    Lit9 = new SyntaxPattern("\b", new Object[0], 0);
    Lit8 = new SyntaxTemplate("\001\000", "\n", new Object[0], 0);
    Lit7 = new SyntaxTemplate("\001\000", "\003", new Object[0], 0);
    Lit6 = new SyntaxPattern("\f\007\013", new Object[0], 2);
    Lit5 = new SyntaxTemplate("\003\001\000", "\022", new Object[0], 0);
    Lit4 = new SyntaxTemplate("\003\001\000", "\021\030\004\021\030\f\021\030\024\021\030\034\021\005\003\b\021\030$\b\013", new Object[] { PairWithPosition.make(Lit19, PairWithPosition.make(PairWithPosition.make(Lit20, PairWithPosition.make(Lit17, PairWithPosition.make(Lit22, LList.Empty, "defs.scm", 36913), "defs.scm", 36910), "defs.scm", 36890), LList.Empty, "defs.scm", 36890), "defs.scm", 36880), Lit17, Lit23, PairWithPosition.make(Lit24, PairWithPosition.make(Lit18, PairWithPosition.make(PairWithPosition.make(Lit21, LList.Empty, "defs.scm", 41006), PairWithPosition.make(PairWithPosition.make(Lit25, PairWithPosition.make(Lit19, LList.Empty, "defs.scm", 41014), "defs.scm", 41014), PairWithPosition.make(Lit20, LList.Empty, "defs.scm", 41023), "defs.scm", 41013), "defs.scm", 41006), "defs.scm", 40985), "defs.scm", 40969), PairWithPosition.make((SimpleSymbol)new SimpleSymbol("$lookup$").readResolve(), Pair.make(PairWithPosition.make(Lit21, LList.Empty, "defs.scm", 49162), Pair.make(Pair.make((SimpleSymbol)new SimpleSymbol("quasiquote").readResolve(), Pair.make((SimpleSymbol)new SimpleSymbol("setContentView").readResolve(), LList.Empty)), LList.Empty)), "defs.scm", 49162) }, 1);
    Lit3 = new SyntaxPattern("T\f\002\r\007\000\b\026\f\017\b\023", new Object[] { (SimpleSymbol)new SimpleSymbol("on-create-view").readResolve() }, 3);
    Lit2 = new SyntaxTemplate("\003\000", "\n", new Object[0], 0);
    Lit1 = new SyntaxTemplate("\003\000", "\021\030\004\021\030\f\021\030\024\021\030\034\b\005\003", new Object[] { PairWithPosition.make(Lit19, PairWithPosition.make(PairWithPosition.make(Lit20, PairWithPosition.make(Lit17, PairWithPosition.make(Lit22, LList.Empty, "defs.scm", 16433), "defs.scm", 16430), "defs.scm", 16410), LList.Empty, "defs.scm", 16410), "defs.scm", 16400), Lit17, Lit23, PairWithPosition.make(Lit24, PairWithPosition.make(Lit18, PairWithPosition.make(PairWithPosition.make(Lit21, LList.Empty, "defs.scm", 20526), PairWithPosition.make(PairWithPosition.make(Lit25, PairWithPosition.make(Lit19, LList.Empty, "defs.scm", 20534), "defs.scm", 20534), PairWithPosition.make(Lit20, LList.Empty, "defs.scm", 20543), "defs.scm", 20533), "defs.scm", 20526), "defs.scm", 20505), "defs.scm", 20489) }, 1);
    Lit0 = new SyntaxPattern("<\f\002\r\007\000\b\b\013", new Object[] { (SimpleSymbol)new SimpleSymbol("on-create").readResolve() }, 2);
    $instance = new defs();
    defs localdefs = $instance;
    $Pcprocess$Mnactivity = new ModuleMethod(localdefs, 1, Lit10, 4097);
    activity = Macro.make(Lit11, new ModuleMethod(localdefs, 2, null, 4097), $instance);
    $instance.run();
  }
  
  public defs()
  {
    ModuleInfo.register(this);
  }
  
  static Object lambda1(Object paramObject)
  {
    Object[] arrayOfObject = SyntaxPattern.allocVars(3, null);
    if (Lit12.match(paramObject, arrayOfObject, 0))
    {
      paramObject = TemplateScope.make();
      return Quote.append$V(new Object[] { Lit13.execute(arrayOfObject, (TemplateScope)paramObject), Pair.make(Lit14.execute(arrayOfObject, (TemplateScope)paramObject), Quote.append$V(new Object[] { $PcProcessActivity(Lit15.execute(arrayOfObject, (TemplateScope)paramObject)), Lit16.execute(arrayOfObject, (TemplateScope)paramObject) })) });
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
      return $PcProcessActivity(paramObject);
    }
    return lambda1(paramObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
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
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\android\defs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */