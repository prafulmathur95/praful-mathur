package kawa.lib;

import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.math.IntNum;
import kawa.lang.Macro;
import kawa.lang.Pattern;
import kawa.lang.Quote;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.standard.syntax_case;
import kawa.standard.syntax_error;
import kawa.standard.try_catch;

public class prim_syntax
  extends ModuleBody
{
  public static final prim_syntax $instance;
  static final SimpleSymbol Lit0;
  static final SyntaxRules Lit1;
  static final SyntaxRules Lit10;
  static final SimpleSymbol Lit11;
  static final SyntaxRules Lit12;
  static final SimpleSymbol Lit13;
  static final SyntaxPattern Lit14;
  static final SyntaxTemplate Lit15;
  static final SyntaxTemplate Lit16;
  static final SyntaxPattern Lit17;
  static final SyntaxTemplate Lit18;
  static final SyntaxTemplate Lit19;
  static final SimpleSymbol Lit2;
  static final SyntaxTemplate Lit20;
  static final SyntaxPattern Lit21;
  static final SyntaxTemplate Lit22;
  static final SyntaxPattern Lit23;
  static final SyntaxTemplate Lit24;
  static final SimpleSymbol Lit25;
  static final SyntaxPattern Lit26;
  static final SyntaxTemplate Lit27;
  static final SyntaxTemplate Lit28;
  static final SimpleSymbol Lit29;
  static final SyntaxRules Lit3;
  static final SyntaxPattern Lit30;
  static final SyntaxTemplate Lit31;
  static final SyntaxTemplate Lit32;
  static final SyntaxTemplate Lit33;
  static final SyntaxPattern Lit34;
  static final SyntaxPattern Lit35;
  static final SyntaxTemplate Lit36;
  static final SyntaxTemplate Lit37;
  static final SyntaxTemplate Lit38;
  static final SyntaxPattern Lit39;
  static final SimpleSymbol Lit4;
  static final SyntaxTemplate Lit40;
  static final SyntaxTemplate Lit41;
  static final SyntaxTemplate Lit42;
  static final SyntaxPattern Lit43;
  static final SyntaxPattern Lit44;
  static final SimpleSymbol Lit45;
  static final SimpleSymbol Lit46;
  static final SimpleSymbol Lit47;
  static final SimpleSymbol Lit48;
  static final SimpleSymbol Lit49;
  static final SyntaxRules Lit5;
  static final SimpleSymbol Lit50;
  static final IntNum Lit51;
  static final IntNum Lit52;
  static final IntNum Lit53;
  static final IntNum Lit54;
  static final IntNum Lit55;
  static final IntNum Lit56;
  static final SimpleSymbol Lit57;
  static final SimpleSymbol Lit58 = (SimpleSymbol)new SimpleSymbol("lambda").readResolve();
  static final SimpleSymbol Lit6;
  static final SyntaxRules Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final Macro define;
  public static final Macro define$Mnconstant;
  public static final Macro define$Mnprivate;
  public static final Macro define$Mnsyntax;
  public static final Macro jdField_if;
  public static final Macro letrec;
  public static final Macro syntax$Mn$Grexpression;
  public static final Macro syntax$Mnbody$Mn$Grexpression;
  public static final ModuleMethod syntax$Mnerror;
  public static final Macro try$Mncatch;
  
  static
  {
    Lit57 = (SimpleSymbol)new SimpleSymbol("%define-syntax").readResolve();
    Lit56 = IntNum.make(0);
    Lit55 = IntNum.make(1);
    Lit54 = IntNum.make(4);
    Lit53 = IntNum.make(5);
    Lit52 = IntNum.make(8);
    Lit51 = IntNum.make(9);
    Lit50 = (SimpleSymbol)new SimpleSymbol("%define").readResolve();
    Lit49 = (SimpleSymbol)new SimpleSymbol("::").readResolve();
    Lit48 = (SimpleSymbol)new SimpleSymbol("quasiquote").readResolve();
    Lit47 = (SimpleSymbol)new SimpleSymbol("kawa.lang.SyntaxForms").readResolve();
    Lit46 = (SimpleSymbol)new SimpleSymbol("$lookup$").readResolve();
    Lit45 = (SimpleSymbol)new SimpleSymbol("set!").readResolve();
    Lit44 = new SyntaxPattern("\033", new Object[0], 4);
    Lit43 = new SyntaxPattern("\034\f\037\b#", new Object[0], 5);
    Lit42 = new SyntaxTemplate("\001\001\000\001\001\001\001\000", ":", new Object[0], 0);
    Lit41 = new SyntaxTemplate("\001\001\000\001\001\001\001\000", "\021\030\004\t\033\b3", new Object[] { Lit45 }, 0);
    Lit40 = new SyntaxTemplate("\001\001\000\001\001\001\001\000", "\t\033\t#\t+\030\004", new Object[] { PairWithPosition.make(Special.undefined, LList.Empty, "prim_syntax.scm", 471102) }, 0);
    Lit39 = new SyntaxPattern("L\f\037\f'\f/\f7\b;", new Object[0], 8);
    Lit38 = new SyntaxTemplate("\001\001\000\001\001\000", "*", new Object[0], 0);
    Lit37 = new SyntaxTemplate("\001\001\000\001\001\000", "\021\030\004\t\033\b#", new Object[] { Lit45 }, 0);
    Lit36 = new SyntaxTemplate("\001\001\000\001\001\000", "\t\033\030\004", new Object[] { PairWithPosition.make(Special.undefined, LList.Empty, "prim_syntax.scm", 450594) }, 0);
    Lit35 = new SyntaxPattern(",\f\037\f'\b+", new Object[0], 6);
    Lit34 = new SyntaxPattern("\b", new Object[0], 3);
    Lit33 = new SyntaxTemplate("\001\001\000", "\022", new Object[0], 0);
    Lit32 = new SyntaxTemplate("\001\001\000", "\030\004", new Object[] { PairWithPosition.make((SimpleSymbol)new SimpleSymbol("%let").readResolve(), LList.Empty, "prim_syntax.scm", 512011) }, 0);
    Lit31 = new SyntaxTemplate("\001\001\000", "\013", new Object[0], 0);
    Lit30 = new SyntaxPattern("\f\007\f\017\023", new Object[0], 3);
    Lit29 = (SimpleSymbol)new SimpleSymbol("letrec").readResolve();
    Lit28 = new SyntaxTemplate("\001\001\003\003\002", "(\b\025A\b\t\023\021\030\004\b\033\"", new Object[] { Lit49 }, 1);
    Lit27 = new SyntaxTemplate("\001\001\003\003\002", "\013", new Object[0], 0);
    Lit26 = new SyntaxPattern("\f\007\f\017-\f\027\f\037#\020\030\b", new Object[0], 5);
    Lit25 = (SimpleSymbol)new SimpleSymbol("try-catch").readResolve();
    Lit24 = new SyntaxTemplate("\001\000", "\n", new Object[0], 0);
    Lit23 = new SyntaxPattern("\f\007\013", new Object[0], 2);
    Lit22 = new SyntaxTemplate("\001\001\001\001\001\000", "#", new Object[0], 0);
    Lit21 = new SyntaxPattern("\f\007\f\017\f\027\f\037\f'+", new Object[0], 6);
    Lit20 = new SyntaxTemplate("\001\001\001\001", "\033", new Object[0], 0);
    Lit19 = new SyntaxTemplate("\001\001\001\001", "\023", new Object[0], 0);
    Lit18 = new SyntaxTemplate("\001\001\001\001", "\013", new Object[0], 0);
    Lit17 = new SyntaxPattern("\f\007\f\017\f\027\f\037\b", new Object[0], 4);
    Lit16 = new SyntaxTemplate("\001\001\001", "\023", new Object[0], 0);
    Lit15 = new SyntaxTemplate("\001\001\001", "\013", new Object[0], 0);
    Lit14 = new SyntaxPattern("\f\007\f\017\f\027\b", new Object[0], 3);
    Lit13 = (SimpleSymbol)new SimpleSymbol("if").readResolve();
    Object localObject1 = (SimpleSymbol)new SimpleSymbol("syntax-body->expression").readResolve();
    Lit11 = (SimpleSymbol)localObject1;
    Object localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\b\003", new Object[] { PairWithPosition.make(Lit46, Pair.make(Lit47, Pair.make(Pair.make(Lit48, Pair.make((SimpleSymbol)new SimpleSymbol("rewriteBody").readResolve(), LList.Empty)), LList.Empty)), "prim_syntax.scm", 270343) }, 0);
    Lit12 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 1);
    localObject1 = (SimpleSymbol)new SimpleSymbol("syntax->expression").readResolve();
    Lit9 = (SimpleSymbol)localObject1;
    localObject2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\b", new Object[0], 1), "\001", "\021\030\004\b\003", new Object[] { PairWithPosition.make(Lit46, Pair.make(Lit47, Pair.make(Pair.make(Lit48, Pair.make((SimpleSymbol)new SimpleSymbol("rewrite").readResolve(), LList.Empty)), LList.Empty)), "prim_syntax.scm", 249863) }, 0);
    Lit10 = new SyntaxRules(new Object[] { localObject1 }, new SyntaxRule[] { localObject2 }, 1);
    Lit8 = (SimpleSymbol)new SimpleSymbol("syntax-error").readResolve();
    localObject1 = (SimpleSymbol)new SimpleSymbol("define-constant").readResolve();
    Lit6 = (SimpleSymbol)localObject1;
    localObject2 = Lit49;
    Object localObject3 = Lit46;
    SyntaxRule localSyntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\n\f\037\f'\b", new Object[] { Lit46, Lit49 }, 5), "\001\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\021\030\024\t\033\b#", new Object[] { Lit50, Lit46, Lit51 }, 0);
    Object localObject4 = new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\037\b", new Object[] { Lit46 }, 4);
    Object localObject5 = new Object[4];
    localObject5[0] = Lit50;
    localObject5[1] = Lit46;
    localObject5[2] = Lit52;
    localObject4 = new SyntaxRule((SyntaxPattern)localObject4, "\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\021\030\024\021\030\034\b\033", (Object[])localObject5, 0);
    localObject5 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3), "\001\000\000", "\021\030\004\t\003\021\030\f\021\030\024\t\n\022", new Object[] { Lit50, IntNum.make(10), Boolean.TRUE }, 0);
    SyntaxRule localSyntaxRule2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\002\f\017\f\027\b", new Object[] { Lit49 }, 3), "\001\001\001", "\021\030\004\t\003\021\030\f\t\013\b\023", new Object[] { Lit50, Lit51 }, 0);
    Object localObject6 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Lit50;
    arrayOfObject[1] = Lit52;
    localObject6 = new SyntaxRule((SyntaxPattern)localObject6, "\001\001", "\021\030\004\t\003\021\030\f\021\030\024\b\013", arrayOfObject, 0);
    Lit7 = new SyntaxRules(new Object[] { localObject1, localObject2, localObject3 }, new SyntaxRule[] { localSyntaxRule1, localObject4, localObject5, localSyntaxRule2, localObject6 }, 5);
    localObject1 = (SimpleSymbol)new SimpleSymbol("define-private").readResolve();
    Lit4 = (SimpleSymbol)localObject1;
    localObject2 = Lit49;
    localObject3 = Lit46;
    localSyntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\n\f\037\f'\b", new Object[] { Lit46, Lit49 }, 5), "\001\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\021\030\024\t\033\b#", new Object[] { Lit50, Lit46, Lit53 }, 0);
    localObject4 = new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\037\b", new Object[] { Lit46 }, 4);
    localObject5 = new Object[4];
    localObject5[0] = Lit50;
    localObject5[1] = Lit46;
    localObject5[2] = Lit54;
    localObject4 = new SyntaxRule((SyntaxPattern)localObject4, "\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\021\030\024\021\030\034\b\033", (Object[])localObject5, 0);
    localObject5 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3), "\001\000\000", "\021\030\004\t\003\021\030\f\021\030\024\t\n\022", new Object[] { Lit50, IntNum.make(6), Boolean.TRUE }, 0);
    localSyntaxRule2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\002\f\017\f\027\b", new Object[] { Lit49 }, 3), "\001\001\001", "\021\030\004\t\003\021\030\f\t\013\b\023", new Object[] { Lit50, Lit53 }, 0);
    localObject6 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
    arrayOfObject = new Object[3];
    arrayOfObject[0] = Lit50;
    arrayOfObject[1] = Lit54;
    localObject6 = new SyntaxRule((SyntaxPattern)localObject6, "\001\001", "\021\030\004\t\003\021\030\f\021\030\024\b\013", arrayOfObject, 0);
    Lit5 = new SyntaxRules(new Object[] { localObject1, localObject2, localObject3 }, new SyntaxRule[] { localSyntaxRule1, localObject4, localObject5, localSyntaxRule2, localObject6 }, 5);
    localObject1 = (SimpleSymbol)new SimpleSymbol("define").readResolve();
    Lit2 = (SimpleSymbol)localObject1;
    localObject2 = Lit49;
    localObject3 = Lit46;
    localSyntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\n\f\037\f'\b", new Object[] { Lit46, Lit49 }, 5), "\001\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\021\030\024\t\033\b#", new Object[] { Lit50, Lit46, Lit55 }, 0);
    localObject4 = new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\037\b", new Object[] { Lit46 }, 4);
    localObject5 = new Object[4];
    localObject5[0] = Lit50;
    localObject5[1] = Lit46;
    localObject5[2] = Lit56;
    localObject4 = new SyntaxRule((SyntaxPattern)localObject4, "\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\021\030\024\021\030\034\b\033", (Object[])localObject5, 0);
    localObject5 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3), "\001\000\000", "\021\030\004\t\003\021\030\f\021\030\024\t\n\022", new Object[] { Lit50, IntNum.make(2), Boolean.TRUE }, 0);
    localSyntaxRule2 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\002\f\017\f\027\b", new Object[] { Lit49 }, 3), "\001\001\001", "\021\030\004\t\003\021\030\f\t\013\b\023", new Object[] { Lit50, Lit55 }, 0);
    localObject6 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
    arrayOfObject = new Object[3];
    arrayOfObject[0] = Lit50;
    arrayOfObject[1] = Lit56;
    localObject6 = new SyntaxRule((SyntaxPattern)localObject6, "\001\001", "\021\030\004\t\003\021\030\f\021\030\024\b\013", arrayOfObject, 0);
    Lit3 = new SyntaxRules(new Object[] { localObject1, localObject2, localObject3 }, new SyntaxRule[] { localSyntaxRule1, localObject4, localObject5, localSyntaxRule2, localObject6 }, 5);
    localObject1 = (SimpleSymbol)new SimpleSymbol("define-syntax").readResolve();
    Lit0 = (SimpleSymbol)localObject1;
    localObject2 = Lit46;
    localObject3 = new SyntaxRule(new SyntaxPattern("\f\030l\\\f\002\f\007,\f\017\f\027\b\b\033#", new Object[] { Lit46 }, 5), "\001\001\001\000\000", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\b\021\030\024\t\032\"", new Object[] { Lit57, Lit46, Lit58 }, 0);
    localSyntaxRule1 = new SyntaxRule(new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\037\b", new Object[] { Lit46 }, 4), "\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\b\033", new Object[] { Lit57, Lit46 }, 0);
    localObject4 = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3), "\001\000\000", "\021\030\004\t\003\b\021\030\f\t\n\022", new Object[] { Lit57, Lit58 }, 0);
    localObject5 = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2), "\001\001", "\021\030\004\t\003\b\013", new Object[] { Lit57 }, 0);
    Lit1 = new SyntaxRules(new Object[] { localObject1, localObject2 }, new SyntaxRule[] { localObject3, localSyntaxRule1, localObject4, localObject5 }, 5);
    $instance = new prim_syntax();
    define$Mnsyntax = Macro.make(Lit0, Lit1, $instance);
    define = Macro.make(Lit2, Lit3, $instance);
    define$Mnprivate = Macro.make(Lit4, Lit5, $instance);
    define$Mnconstant = Macro.make(Lit6, Lit7, $instance);
    localObject1 = $instance;
    syntax$Mnerror = new ModuleMethod((ModuleBody)localObject1, 1, Lit8, 61441);
    syntax$Mn$Grexpression = Macro.make(Lit9, Lit10, $instance);
    syntax$Mnbody$Mn$Grexpression = Macro.make(Lit11, Lit12, $instance);
    localObject2 = Lit13;
    localObject3 = new ModuleMethod((ModuleBody)localObject1, 2, null, 4097);
    ((PropertySet)localObject3).setProperty("source-location", "prim_syntax.scm:69");
    if = Macro.make(localObject2, (Procedure)localObject3, $instance);
    localObject2 = Lit25;
    localObject3 = new ModuleMethod((ModuleBody)localObject1, 3, null, 4097);
    ((PropertySet)localObject3).setProperty("source-location", "prim_syntax.scm:89");
    try$Mncatch = Macro.make(localObject2, (Procedure)localObject3, $instance);
    localObject2 = Lit29;
    localObject1 = new ModuleMethod((ModuleBody)localObject1, 4, null, 4097);
    ((PropertySet)localObject1).setProperty("source-location", "prim_syntax.scm:98");
    letrec = Macro.make(localObject2, (Procedure)localObject1, $instance);
    $instance.run();
  }
  
  public prim_syntax()
  {
    ModuleInfo.register(this);
  }
  
  static Object lambda1(Object paramObject)
  {
    Object localObject1 = SyntaxPattern.allocVars(6, null);
    Object localObject2;
    if (Lit14.match(paramObject, (Object[])localObject1, 0))
    {
      paramObject = TemplateScope.make();
      paramObject = SyntaxForms.rewrite(Lit15.execute((Object[])localObject1, (TemplateScope)paramObject));
      localObject2 = TemplateScope.make();
      return new IfExp((Expression)paramObject, SyntaxForms.rewrite(Lit16.execute((Object[])localObject1, (TemplateScope)localObject2)), null);
    }
    if (Lit17.match(paramObject, (Object[])localObject1, 0))
    {
      paramObject = TemplateScope.make();
      paramObject = SyntaxForms.rewrite(Lit18.execute((Object[])localObject1, (TemplateScope)paramObject));
      localObject2 = TemplateScope.make();
      localObject2 = SyntaxForms.rewrite(Lit19.execute((Object[])localObject1, (TemplateScope)localObject2));
      TemplateScope localTemplateScope = TemplateScope.make();
      return new IfExp((Expression)paramObject, (Expression)localObject2, SyntaxForms.rewrite(Lit20.execute((Object[])localObject1, localTemplateScope)));
    }
    if (Lit21.match(paramObject, (Object[])localObject1, 0))
    {
      paramObject = TemplateScope.make();
      localObject1 = Lit22.execute((Object[])localObject1, (TemplateScope)paramObject);
      if (("too many expressions for 'if'" instanceof Object[])) {}
      for (paramObject = (Object[])"too many expressions for 'if'";; paramObject = new Object[] { "too many expressions for 'if'" }) {
        return syntaxError(localObject1, (Object[])paramObject);
      }
    }
    if (Lit23.match(paramObject, (Object[])localObject1, 0))
    {
      paramObject = TemplateScope.make();
      localObject1 = Lit24.execute((Object[])localObject1, (TemplateScope)paramObject);
      if (("too few expressions for 'if'" instanceof Object[])) {}
      for (paramObject = (Object[])"too few expressions for 'if'";; paramObject = new Object[] { "too few expressions for 'if'" }) {
        return syntaxError(localObject1, (Object[])paramObject);
      }
    }
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda2(Object paramObject)
  {
    Object[] arrayOfObject = SyntaxPattern.allocVars(5, null);
    if (Lit26.match(paramObject, arrayOfObject, 0))
    {
      paramObject = TemplateScope.make();
      paramObject = Lit27.execute(arrayOfObject, (TemplateScope)paramObject);
      TemplateScope localTemplateScope = TemplateScope.make();
      return try_catch.rewrite(paramObject, Lit28.execute(arrayOfObject, localTemplateScope));
    }
    return syntax_case.error("syntax-case", paramObject);
  }
  
  static Object lambda3(Object paramObject)
  {
    frame localframe = new frame();
    LList localLList = LList.Empty;
    localframe.out$Mninits = LList.Empty;
    localframe.out$Mnbindings = localLList;
    localframe.$unnamed$0 = SyntaxPattern.allocVars(3, null);
    if (Lit30.match(paramObject, localframe.$unnamed$0, 0))
    {
      paramObject = TemplateScope.make();
      localframe.lambda4processBinding(Lit31.execute(localframe.$unnamed$0, (TemplateScope)paramObject));
      localframe.out$Mnbindings = LList.reverseInPlace(localframe.out$Mnbindings);
      localframe.out$Mninits = LList.reverseInPlace(localframe.out$Mninits);
      paramObject = TemplateScope.make();
      return Quote.append$V(new Object[] { Lit32.execute(localframe.$unnamed$0, (TemplateScope)paramObject), Quote.consX$V(new Object[] { localframe.out$Mnbindings, Quote.append$V(new Object[] { localframe.out$Mninits, Lit33.execute(localframe.$unnamed$0, (TemplateScope)paramObject) }) }) });
    }
    return syntax_case.error("syntax-case", paramObject);
  }
  
  public static Expression syntaxError(Object paramObject, Object... paramVarArgs)
  {
    return syntax_error.error(paramObject, paramVarArgs);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 2: 
      return lambda1(paramObject);
    case 3: 
      return lambda2(paramObject);
    }
    return lambda3(paramObject);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject)
  {
    if (paramModuleMethod.selector == 1)
    {
      paramModuleMethod = paramArrayOfObject[0];
      int i = paramArrayOfObject.length - 1;
      Object[] arrayOfObject = new Object[i];
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          return syntaxError(paramModuleMethod, arrayOfObject);
        }
        arrayOfObject[i] = paramArrayOfObject[(i + 1)];
      }
    }
    return super.applyN(paramModuleMethod, paramArrayOfObject);
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
    }
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 1)
    {
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    }
    return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
  
  public class frame
    extends ModuleBody
  {
    Object[] $unnamed$0;
    Object out$Mnbindings;
    Object out$Mninits;
    
    public Object lambda4processBinding(Object paramObject)
    {
      Object[] arrayOfObject = SyntaxPattern.allocVars(8, this.$unnamed$0);
      if (prim_syntax.Lit34.match(paramObject, arrayOfObject, 0)) {
        return Values.empty;
      }
      if (prim_syntax.Lit35.match(paramObject, arrayOfObject, 0))
      {
        paramObject = TemplateScope.make();
        this.out$Mnbindings = new Pair(prim_syntax.Lit36.execute(arrayOfObject, (TemplateScope)paramObject), this.out$Mnbindings);
        paramObject = TemplateScope.make();
        this.out$Mninits = new Pair(prim_syntax.Lit37.execute(arrayOfObject, (TemplateScope)paramObject), this.out$Mninits);
        paramObject = TemplateScope.make();
        return lambda4processBinding(prim_syntax.Lit38.execute(arrayOfObject, (TemplateScope)paramObject));
      }
      if (prim_syntax.Lit39.match(paramObject, arrayOfObject, 0))
      {
        paramObject = TemplateScope.make();
        this.out$Mnbindings = new Pair(prim_syntax.Lit40.execute(arrayOfObject, (TemplateScope)paramObject), this.out$Mnbindings);
        paramObject = TemplateScope.make();
        this.out$Mninits = new Pair(prim_syntax.Lit41.execute(arrayOfObject, (TemplateScope)paramObject), this.out$Mninits);
        paramObject = TemplateScope.make();
        return lambda4processBinding(prim_syntax.Lit42.execute(arrayOfObject, (TemplateScope)paramObject));
      }
      if (prim_syntax.Lit43.match(paramObject, arrayOfObject, 0))
      {
        if (("missing initializion in letrec" instanceof Object[])) {}
        for (arrayOfObject = (Object[])"missing initializion in letrec";; arrayOfObject = new Object[] { "missing initializion in letrec" }) {
          return prim_syntax.syntaxError(paramObject, arrayOfObject);
        }
      }
      if (prim_syntax.Lit44.match(paramObject, arrayOfObject, 0))
      {
        if (("invalid bindings syntax in letrec" instanceof Object[])) {}
        for (arrayOfObject = (Object[])"invalid bindings syntax in letrec";; arrayOfObject = new Object[] { "invalid bindings syntax in letrec" }) {
          return prim_syntax.syntaxError(paramObject, arrayOfObject);
        }
      }
      return syntax_case.error("syntax-case", paramObject);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\prim_syntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */