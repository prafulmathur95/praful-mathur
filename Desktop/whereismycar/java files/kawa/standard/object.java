package kawa.standard;

import gnu.bytecode.Type;
import gnu.expr.BeginExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.ObjectExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.expr.ThisExp;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;

public class object
  extends Syntax
{
  public static final Keyword accessKeyword;
  public static final Keyword allocationKeyword;
  public static final Keyword classNameKeyword;
  static final Symbol coloncolon = Namespace.EmptyNamespace.getSymbol("::");
  static final Keyword initKeyword;
  static final Keyword init_formKeyword;
  static final Keyword init_keywordKeyword;
  static final Keyword init_valueKeyword;
  static final Keyword initformKeyword;
  public static final Keyword interfaceKeyword;
  public static final object objectSyntax = new object(SchemeCompilation.lambda);
  public static final Keyword throwsKeyword;
  static final Keyword typeKeyword;
  Lambda lambda;
  
  static
  {
    objectSyntax.setName("object");
    accessKeyword = Keyword.make("access");
    classNameKeyword = Keyword.make("class-name");
    interfaceKeyword = Keyword.make("interface");
    throwsKeyword = Keyword.make("throws");
    typeKeyword = Keyword.make("type");
    allocationKeyword = Keyword.make("allocation");
    initKeyword = Keyword.make("init");
    initformKeyword = Keyword.make("initform");
    init_formKeyword = Keyword.make("init-form");
    init_valueKeyword = Keyword.make("init-value");
    init_keywordKeyword = Keyword.make("init-keyword");
  }
  
  public object(Lambda paramLambda)
  {
    this.lambda = paramLambda;
  }
  
  static long addAccessFlags(Object paramObject, long paramLong1, long paramLong2, String paramString, Translator paramTranslator)
  {
    long l = matchAccess(paramObject, paramTranslator);
    if (l == 0L) {
      paramTranslator.error('e', "unknown access specifier " + paramObject);
    }
    for (;;)
    {
      return paramLong1 | l;
      if (((0xFFFFFFFFFFFFFFFF ^ paramLong2) & l) != 0L) {
        paramTranslator.error('e', "invalid " + paramString + " access specifier " + paramObject);
      } else if ((paramLong1 & l) != 0L) {
        paramTranslator.error('w', "duplicate " + paramString + " access specifiers " + paramObject);
      }
    }
  }
  
  static long matchAccess(Object paramObject, Translator paramTranslator)
  {
    while ((paramObject instanceof SyntaxForm)) {
      paramObject = ((SyntaxForm)paramObject).getDatum();
    }
    Object localObject = paramObject;
    if ((paramObject instanceof Pair))
    {
      localObject = (Pair)paramObject;
      paramObject = paramTranslator.matchQuoted((Pair)paramObject);
      localObject = paramObject;
      if ((paramObject instanceof Pair)) {
        return matchAccess2((Pair)paramObject, paramTranslator);
      }
    }
    return matchAccess1(localObject, paramTranslator);
  }
  
  private static long matchAccess1(Object paramObject, Translator paramTranslator)
  {
    if ((paramObject instanceof Keyword)) {
      paramTranslator = ((Keyword)paramObject).getName();
    }
    while ("private".equals(paramTranslator))
    {
      return 16777216L;
      if ((paramObject instanceof FString))
      {
        paramTranslator = ((FString)paramObject).toString();
      }
      else
      {
        paramTranslator = (Translator)paramObject;
        if ((paramObject instanceof SimpleSymbol)) {
          paramTranslator = paramObject.toString();
        }
      }
    }
    if ("protected".equals(paramTranslator)) {
      return 33554432L;
    }
    if ("public".equals(paramTranslator)) {
      return 67108864L;
    }
    if ("package".equals(paramTranslator)) {
      return 134217728L;
    }
    if ("volatile".equals(paramTranslator)) {
      return 2147483648L;
    }
    if ("transient".equals(paramTranslator)) {
      return 4294967296L;
    }
    if ("enum".equals(paramTranslator)) {
      return 8589934592L;
    }
    if ("final".equals(paramTranslator)) {
      return 17179869184L;
    }
    return 0L;
  }
  
  private static long matchAccess2(Pair paramPair, Translator paramTranslator)
  {
    long l1 = matchAccess1(paramPair.getCar(), paramTranslator);
    paramPair = paramPair.getCdr();
    if ((paramPair == LList.Empty) || (l1 == 0L)) {
      return l1;
    }
    if ((paramPair instanceof Pair))
    {
      long l2 = matchAccess2((Pair)paramPair, paramTranslator);
      if (l2 != 0L) {
        return l1 | l2;
      }
    }
    return 0L;
  }
  
  static boolean matches(Object paramObject, String paramString, Translator paramTranslator)
  {
    boolean bool2 = false;
    if ((paramObject instanceof Keyword)) {
      paramObject = ((Keyword)paramObject).getName();
    }
    for (;;)
    {
      boolean bool1;
      if (paramString != null)
      {
        bool1 = bool2;
        if (!paramString.equals(paramObject)) {}
      }
      else
      {
        bool1 = true;
      }
      do
      {
        do
        {
          return bool1;
          if ((paramObject instanceof FString))
          {
            paramObject = ((FString)paramObject).toString();
            break;
          }
          bool1 = bool2;
        } while (!(paramObject instanceof Pair));
        paramObject = paramTranslator.matchQuoted((Pair)paramObject);
        bool1 = bool2;
      } while (!(paramObject instanceof SimpleSymbol));
      paramObject = paramObject.toString();
    }
  }
  
  private static void rewriteInit(Object paramObject, ClassExp paramClassExp, Pair paramPair, Translator paramTranslator, SyntaxForm paramSyntaxForm)
  {
    boolean bool;
    LambdaExp localLambdaExp1;
    label30:
    LambdaExp localLambdaExp2;
    if ((paramObject instanceof Declaration))
    {
      bool = ((Declaration)paramObject).getFlag(2048L);
      if (!bool) {
        break label207;
      }
      localLambdaExp1 = paramClassExp.clinitMethod;
      localLambdaExp2 = localLambdaExp1;
      if (localLambdaExp1 == null)
      {
        localLambdaExp2 = new LambdaExp(new BeginExp());
        localLambdaExp2.setClassMethod(true);
        localLambdaExp2.setReturnType(Type.voidType);
        if (!bool) {
          break label216;
        }
        localLambdaExp2.setName("$clinit$");
        paramClassExp.clinitMethod = localLambdaExp2;
        label88:
        localLambdaExp2.nextSibling = paramClassExp.firstChild;
        paramClassExp.firstChild = localLambdaExp2;
      }
      paramTranslator.push(localLambdaExp2);
      paramClassExp = paramTranslator.curMethodLambda;
      paramTranslator.curMethodLambda = localLambdaExp2;
      paramPair = paramTranslator.rewrite_car(paramPair, paramSyntaxForm);
      if (!(paramObject instanceof Declaration)) {
        break label249;
      }
      paramSyntaxForm = (Declaration)paramObject;
      paramObject = new SetExp(paramSyntaxForm, paramPair);
      ((SetExp)paramObject).setLocation(paramSyntaxForm);
      paramSyntaxForm.noteValue(null);
    }
    for (;;)
    {
      ((BeginExp)localLambdaExp2.body).add((Expression)paramObject);
      paramTranslator.curMethodLambda = paramClassExp;
      paramTranslator.pop(localLambdaExp2);
      return;
      if (paramObject == Boolean.TRUE)
      {
        bool = true;
        break;
      }
      bool = false;
      break;
      label207:
      localLambdaExp1 = paramClassExp.initMethod;
      break label30;
      label216:
      localLambdaExp2.setName("$finit$");
      paramClassExp.initMethod = localLambdaExp2;
      localLambdaExp2.add(null, new Declaration(ThisExp.THIS_NAME));
      break label88;
      label249:
      paramObject = Compilation.makeCoercion(paramPair, new QuoteExp(Type.voidType));
    }
  }
  
  public void rewriteClassDef(Object[] paramArrayOfObject, Translator paramTranslator)
  {
    ClassExp localClassExp = (ClassExp)paramArrayOfObject[0];
    Object localObject1 = paramArrayOfObject[1];
    Vector localVector = (Vector)paramArrayOfObject[2];
    Object localObject7 = (LambdaExp)paramArrayOfObject[3];
    Object localObject2 = paramArrayOfObject[4];
    Object localObject4 = paramArrayOfObject[5];
    localClassExp.firstChild = ((LambdaExp)localObject7);
    int j = Translator.listLength(localObject2);
    int i = j;
    if (j < 0)
    {
      paramTranslator.error('e', "object superclass specification not a list");
      i = 0;
    }
    Object localObject3 = new Expression[i];
    j = 0;
    paramArrayOfObject = (Object[])localObject2;
    while (j < i)
    {
      while ((paramArrayOfObject instanceof SyntaxForm)) {
        paramArrayOfObject = ((SyntaxForm)paramArrayOfObject).getDatum();
      }
      paramArrayOfObject = (Pair)paramArrayOfObject;
      localObject3[j] = paramTranslator.rewrite_car(paramArrayOfObject, false);
      if ((localObject3[j] instanceof ReferenceExp))
      {
        localObject2 = Declaration.followAliases(((ReferenceExp)localObject3[j]).getBinding());
        if (localObject2 != null)
        {
          localObject2 = ((Declaration)localObject2).getValue();
          if ((localObject2 instanceof ClassExp)) {
            ((ClassExp)localObject2).setFlag(131072);
          }
        }
      }
      paramArrayOfObject = paramArrayOfObject.getCdr();
      j += 1;
    }
    if (localObject4 != null)
    {
      paramArrayOfObject = paramTranslator.rewrite_car((Pair)localObject4, false).valueIfConstant();
      if (!(paramArrayOfObject instanceof CharSequence)) {
        break label316;
      }
      paramArrayOfObject = paramArrayOfObject.toString();
      if (paramArrayOfObject.length() <= 0) {
        break label316;
      }
      localClassExp.classNameSpecifier = paramArrayOfObject;
    }
    for (;;)
    {
      localClassExp.supers = ((Expression[])localObject3);
      localClassExp.setTypes(paramTranslator);
      j = localVector.size();
      i = 0;
      while (i < j)
      {
        paramArrayOfObject = localVector.elementAt(i + 1);
        if (paramArrayOfObject != null) {
          rewriteInit(localVector.elementAt(i), localClassExp, (Pair)paramArrayOfObject, paramTranslator, null);
        }
        i += 2;
      }
      label316:
      paramArrayOfObject = paramTranslator.pushPositionOf(localObject4);
      paramTranslator.error('e', "class-name specifier must be a non-empty string literal");
      paramTranslator.popPositionOf(paramArrayOfObject);
    }
    paramTranslator.push(localClassExp);
    i = 0;
    localObject4 = null;
    paramArrayOfObject = (Object[])localObject1;
    Object localObject11;
    Object localObject9;
    Object localObject8;
    label554:
    Object localObject5;
    Object localObject6;
    int k;
    Pair localPair;
    Object localObject10;
    if (paramArrayOfObject != LList.Empty)
    {
      while ((paramArrayOfObject instanceof SyntaxForm))
      {
        localObject4 = (SyntaxForm)paramArrayOfObject;
        paramArrayOfObject = ((SyntaxForm)localObject4).getDatum();
      }
      localObject2 = (Pair)paramArrayOfObject;
      localObject11 = paramTranslator.pushPositionOf(localObject2);
      localObject1 = ((Pair)localObject2).getCar();
      paramArrayOfObject = (Object[])localObject4;
      while ((localObject1 instanceof SyntaxForm))
      {
        paramArrayOfObject = (SyntaxForm)localObject1;
        localObject1 = paramArrayOfObject.getDatum();
      }
      for (;;)
      {
        Object localObject12;
        label709:
        try
        {
          localObject9 = ((Pair)localObject2).getCdr();
          if (((localObject1 instanceof Keyword)) && ((localObject9 instanceof Pair)))
          {
            paramArrayOfObject = ((Pair)localObject9).getCdr();
            paramTranslator.popPositionOf(localObject11);
            break;
          }
          localObject3 = (Pair)localObject1;
          localObject2 = ((Pair)localObject3).getCar();
          localObject1 = paramArrayOfObject;
          if ((localObject2 instanceof SyntaxForm))
          {
            localObject1 = (SyntaxForm)localObject2;
            localObject2 = ((SyntaxForm)localObject1).getDatum();
            continue;
          }
          if ((!(localObject2 instanceof String)) && (!(localObject2 instanceof Symbol)) && (!(localObject2 instanceof Keyword))) {
            break label982;
          }
          localObject8 = null;
          j = 0;
          if ((localObject2 instanceof Keyword))
          {
            localObject1 = localObject3;
            break label1217;
            if (localObject1 == LList.Empty) {
              break label863;
            }
            if ((localObject1 instanceof SyntaxForm))
            {
              paramArrayOfObject = (SyntaxForm)localObject1;
              localObject1 = paramArrayOfObject.getDatum();
              continue;
            }
          }
          else
          {
            localObject1 = ((Pair)localObject3).getCdr();
            break label1217;
          }
          localObject1 = (Pair)localObject1;
          localObject5 = ((Pair)localObject1).getCar();
          if ((localObject5 instanceof SyntaxForm))
          {
            localObject5 = ((SyntaxForm)localObject5).getDatum();
            continue;
          }
          localObject12 = paramTranslator.pushPositionOf(localObject1);
          localObject6 = ((Pair)localObject1).getCdr();
          if (((localObject5 != coloncolon) && (!(localObject5 instanceof Keyword))) || (!(localObject6 instanceof Pair))) {
            break label777;
          }
          k = j + 1;
          localPair = (Pair)localObject6;
          localObject6 = localPair.getCar();
          localObject10 = localPair.getCdr();
          if (localObject5 == coloncolon) {
            break label1226;
          }
          if (localObject5 != typeKeyword) {
            break label731;
          }
          break label1226;
        }
        finally {}
        paramTranslator.popPositionOf(localObject12);
        localObject8 = localObject6;
        continue;
        label723:
        paramTranslator.popPositionOf(localObject11);
        throw paramArrayOfObject;
        label731:
        if ((localObject5 == initKeyword) || (localObject5 == initformKeyword) || (localObject5 == init_formKeyword)) {
          break label1236;
        }
        localObject1 = localObject10;
        j = k;
        localObject6 = localObject8;
        if (localObject5 == init_valueKeyword)
        {
          break label1236;
          label777:
          if ((localObject6 == LList.Empty) && (localObject2 == null))
          {
            localObject2 = localObject1;
            localObject3 = paramArrayOfObject;
            localObject1 = localObject6;
            localObject6 = localObject8;
          }
          else
          {
            if ((!(localObject6 instanceof Pair)) || (j != 0) || (localObject2 != null) || (localObject8 != null)) {
              break label863;
            }
            localObject1 = (Pair)localObject6;
            if (((Pair)localObject1).getCdr() != LList.Empty) {
              break label863;
            }
            localObject2 = localObject1;
            localObject3 = paramArrayOfObject;
            localObject1 = ((Pair)localObject1).getCdr();
            localObject6 = localObject5;
          }
        }
      }
      label863:
      paramArrayOfObject = (Object[])localObject7;
      j = i;
      if (localObject2 != null) {
        k = i + 1;
      }
    }
    for (;;)
    {
      try
      {
        localObject1 = localVector.elementAt(i);
        if ((localObject1 instanceof Declaration))
        {
          ((Declaration)localObject1).getFlag(2048L);
          i = k + 1;
          paramArrayOfObject = (Object[])localObject7;
          j = i;
          if (localVector.elementAt(k) == null)
          {
            rewriteInit(localObject1, localClassExp, (Pair)localObject2, paramTranslator, (SyntaxForm)localObject3);
            j = i;
            paramArrayOfObject = (Object[])localObject7;
          }
          paramTranslator.popPositionOf(localObject11);
          localObject7 = paramArrayOfObject;
          i = j;
          paramArrayOfObject = (Object[])localObject9;
          break;
        }
        paramArrayOfObject = Boolean.TRUE;
        if (localObject1 == paramArrayOfObject) {
          continue;
        }
        continue;
        label982:
        if ((localObject2 instanceof Pair))
        {
          localObject5 = paramTranslator.currentScope();
          if (paramArrayOfObject != null) {
            paramTranslator.setCurrentScope(paramArrayOfObject.getScope());
          }
          if ("*init*".equals(((LambdaExp)localObject7).getName())) {
            ((LambdaExp)localObject7).setReturnType(Type.voidType);
          }
          Translator.setLine((Expression)localObject7, localObject3);
          localObject6 = paramTranslator.curMethodLambda;
          paramTranslator.curMethodLambda = ((LambdaExp)localObject7);
          localObject8 = this.lambda;
          localObject2 = ((Pair)localObject2).getCdr();
          localObject3 = ((Pair)localObject3).getCdr();
          if ((localObject1 == null) || ((paramArrayOfObject != null) && (((SyntaxForm)localObject1).getScope() == paramArrayOfObject.getScope()))) {
            break label1257;
          }
          localObject1 = ((SyntaxForm)localObject1).getScope();
          ((Lambda)localObject8).rewrite((LambdaExp)localObject7, localObject2, localObject3, paramTranslator, (TemplateScope)localObject1);
          paramTranslator.curMethodLambda = ((LambdaExp)localObject6);
          if (paramArrayOfObject != null) {
            paramTranslator.setCurrentScope((ScopeExp)localObject5);
          }
          paramArrayOfObject = ((LambdaExp)localObject7).nextSibling;
          j = i;
          continue;
        }
        paramTranslator.syntaxError("invalid field/method definition");
        paramArrayOfObject = (Object[])localObject7;
        j = i;
        continue;
        if (localClassExp.initMethod != null) {
          localClassExp.initMethod.outer = localClassExp;
        }
        if (localClassExp.clinitMethod != null) {
          localClassExp.clinitMethod.outer = localClassExp;
        }
        paramTranslator.pop(localClassExp);
        localClassExp.declareParts(paramTranslator);
        return;
      }
      finally {}
      break label723;
      label1217:
      localObject2 = null;
      localObject3 = null;
      break label554;
      label1226:
      localObject1 = localObject10;
      j = k;
      break label709;
      label1236:
      localObject2 = localPair;
      localObject3 = paramArrayOfObject;
      localObject1 = localObject10;
      j = k;
      localObject6 = localObject8;
      break label709;
      label1257:
      localObject1 = null;
    }
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    if (!(paramPair.getCdr() instanceof Pair)) {
      paramPair = paramTranslator.syntaxError("missing superclass specification in object");
    }
    Object localObject;
    ObjectExp localObjectExp;
    do
    {
      return paramPair;
      localObject = (Pair)paramPair.getCdr();
      localObjectExp = new ObjectExp();
      paramPair = (Pair)localObject;
      if ((((Pair)localObject).getCar() instanceof FString))
      {
        if (!(((Pair)localObject).getCdr() instanceof Pair)) {
          return paramTranslator.syntaxError("missing superclass specification after object class name");
        }
        paramPair = (Pair)((Pair)localObject).getCdr();
      }
      localObject = scanClassDef(paramPair, localObjectExp, paramTranslator);
      paramPair = localObjectExp;
    } while (localObject == null);
    rewriteClassDef((Object[])localObject, paramTranslator);
    return localObjectExp;
  }
  
  public Object[] scanClassDef(Pair paramPair, ClassExp paramClassExp, Translator paramTranslator)
  {
    paramTranslator.mustCompileHere();
    Object localObject10 = paramPair.getCar();
    Object localObject8 = paramPair.getCdr();
    Pair localPair3 = null;
    Pair localPair1 = null;
    Pair localPair4 = null;
    long l1 = 0L;
    Vector localVector = new Vector(20);
    paramPair = (Pair)localObject8;
    while (paramPair != LList.Empty)
    {
      while ((paramPair instanceof SyntaxForm)) {
        paramPair = ((SyntaxForm)paramPair).getDatum();
      }
      if (!(paramPair instanceof Pair))
      {
        paramTranslator.error('e', "object member not a list");
        return null;
      }
      Pair localPair2 = (Pair)paramPair;
      for (Object localObject1 = localPair2.getCar(); (localObject1 instanceof SyntaxForm); localObject1 = ((SyntaxForm)localObject1).getDatum()) {}
      paramPair = localPair2.getCdr();
      Object localObject11 = paramTranslator.pushPositionOf(localPair2);
      localPair2 = paramPair;
      if ((localObject1 instanceof Keyword))
      {
        while ((paramPair instanceof SyntaxForm)) {
          paramPair = ((SyntaxForm)paramPair).getDatum();
        }
        localPair2 = paramPair;
        if ((paramPair instanceof Pair))
        {
          if (localObject1 == interfaceKeyword)
          {
            if (((Pair)paramPair).getCar() == Boolean.FALSE) {
              paramClassExp.setFlag(65536);
            }
            for (;;)
            {
              paramPair = ((Pair)paramPair).getCdr();
              paramTranslator.popPositionOf(localObject11);
              break;
              paramClassExp.setFlag(32768);
            }
          }
          if (localObject1 == classNameKeyword)
          {
            if (localPair3 != null) {
              paramTranslator.error('e', "duplicate class-name specifiers");
            }
            localPair3 = paramPair;
            paramPair = ((Pair)paramPair).getCdr();
            paramTranslator.popPositionOf(localObject11);
            continue;
          }
          localPair2 = paramPair;
          if (localObject1 == accessKeyword)
          {
            localObject1 = paramTranslator.pushPositionOf(paramPair);
            l1 = addAccessFlags(((Pair)paramPair).getCar(), l1, 25820135424L, "class", paramTranslator);
            if (paramClassExp.nameDecl == null) {
              paramTranslator.error('e', "access specifier for anonymous class");
            }
            paramTranslator.popPositionOf(localObject1);
            paramPair = ((Pair)paramPair).getCdr();
            paramTranslator.popPositionOf(localObject11);
            continue;
          }
        }
      }
      if (!(localObject1 instanceof Pair))
      {
        paramTranslator.error('e', "object member not a list");
        return null;
      }
      paramPair = (Pair)localObject1;
      for (Object localObject4 = paramPair.getCar(); (localObject4 instanceof SyntaxForm); localObject4 = ((SyntaxForm)localObject4).getDatum()) {}
      Object localObject2;
      if (((localObject4 instanceof String)) || ((localObject4 instanceof Symbol)) || ((localObject4 instanceof Keyword)))
      {
        Object localObject5 = null;
        int m = 0;
        long l2 = 0L;
        if ((localObject4 instanceof Keyword)) {
          localObject2 = null;
        }
        int n;
        int k;
        Object localObject6;
        for (;;)
        {
          n = 0;
          k = 0;
          localObject6 = null;
          localObject1 = paramPair;
          if (paramPair == LList.Empty) {
            break label1363;
          }
          while ((paramPair instanceof SyntaxForm)) {
            paramPair = ((SyntaxForm)paramPair).getDatum();
          }
          localObject2 = paramClassExp.addDeclaration(localObject4);
          ((Declaration)localObject2).setSimple(false);
          ((Declaration)localObject2).setFlag(1048576L);
          Translator.setLine((Declaration)localObject2, paramPair);
          paramPair = paramPair.getCdr();
        }
        paramPair = (Pair)paramPair;
        for (Object localObject7 = paramPair.getCar(); (localObject7 instanceof SyntaxForm); localObject7 = ((SyntaxForm)localObject7).getDatum()) {}
        Object localObject12 = paramTranslator.pushPositionOf(paramPair);
        Object localObject3 = paramPair.getCdr();
        int i1;
        Pair localPair5;
        Object localObject13;
        Object localObject9;
        int i;
        long l3;
        int j;
        if (((localObject7 == coloncolon) || ((localObject7 instanceof Keyword))) && ((localObject3 instanceof Pair)))
        {
          i1 = n + 1;
          localPair5 = (Pair)localObject3;
          localObject13 = localPair5.getCar();
          localObject9 = localPair5.getCdr();
          if ((localObject7 == coloncolon) || (localObject7 == typeKeyword))
          {
            localObject3 = localPair5;
            i = k;
            n = i1;
            localObject1 = localObject6;
            paramPair = (Pair)localObject9;
            l3 = l2;
            j = m;
          }
        }
        for (;;)
        {
          paramTranslator.popPositionOf(localObject12);
          m = j;
          l2 = l3;
          localObject6 = localObject1;
          k = i;
          localObject5 = localObject3;
          break;
          if (localObject7 == allocationKeyword)
          {
            if (m != 0) {
              paramTranslator.error('e', "duplicate allocation: specification");
            }
            if ((matches(localObject13, "class", paramTranslator)) || (matches(localObject13, "static", paramTranslator)))
            {
              j = 2048;
              l3 = l2;
              paramPair = (Pair)localObject9;
              localObject1 = localObject6;
              n = i1;
              i = k;
              localObject3 = localObject5;
            }
            else if (matches(localObject13, "instance", paramTranslator))
            {
              j = 4096;
              l3 = l2;
              paramPair = (Pair)localObject9;
              localObject1 = localObject6;
              n = i1;
              i = k;
              localObject3 = localObject5;
            }
            else
            {
              paramTranslator.error('e', "unknown allocation kind '" + localObject13 + "'");
              j = m;
              l3 = l2;
              paramPair = (Pair)localObject9;
              localObject1 = localObject6;
              n = i1;
              i = k;
              localObject3 = localObject5;
            }
          }
          else if ((localObject7 == initKeyword) || (localObject7 == initformKeyword) || (localObject7 == init_formKeyword) || (localObject7 == init_valueKeyword))
          {
            if (k != 0) {
              paramTranslator.error('e', "duplicate initialization");
            }
            k = 1;
            j = m;
            l3 = l2;
            paramPair = (Pair)localObject9;
            localObject1 = localObject6;
            n = i1;
            i = k;
            localObject3 = localObject5;
            if (localObject7 != initKeyword)
            {
              localObject1 = localPair5;
              j = m;
              l3 = l2;
              paramPair = (Pair)localObject9;
              n = i1;
              i = k;
              localObject3 = localObject5;
            }
          }
          else if (localObject7 == init_keywordKeyword)
          {
            if (!(localObject13 instanceof Keyword))
            {
              paramTranslator.error('e', "invalid 'init-keyword' - not a keyword");
              j = m;
              l3 = l2;
              paramPair = (Pair)localObject9;
              localObject1 = localObject6;
              n = i1;
              i = k;
              localObject3 = localObject5;
            }
            else
            {
              j = m;
              l3 = l2;
              paramPair = (Pair)localObject9;
              localObject1 = localObject6;
              n = i1;
              i = k;
              localObject3 = localObject5;
              if (((Keyword)localObject13).getName() != localObject4.toString())
              {
                paramTranslator.error('w', "init-keyword option ignored");
                j = m;
                l3 = l2;
                paramPair = (Pair)localObject9;
                localObject1 = localObject6;
                n = i1;
                i = k;
                localObject3 = localObject5;
              }
            }
          }
          else if (localObject7 == accessKeyword)
          {
            paramPair = paramTranslator.pushPositionOf(localPair5);
            l3 = addAccessFlags(localObject13, l2, 32463912960L, "field", paramTranslator);
            paramTranslator.popPositionOf(paramPair);
            j = m;
            paramPair = (Pair)localObject9;
            localObject1 = localObject6;
            n = i1;
            i = k;
            localObject3 = localObject5;
          }
          else
          {
            paramTranslator.error('w', "unknown slot keyword '" + localObject7 + "'");
            j = m;
            l3 = l2;
            paramPair = (Pair)localObject9;
            localObject1 = localObject6;
            n = i1;
            i = k;
            localObject3 = localObject5;
            continue;
            if ((localObject3 == LList.Empty) && (k == 0))
            {
              localObject1 = paramPair;
              i = 1;
              j = m;
              l3 = l2;
              paramPair = (Pair)localObject3;
              localObject3 = localObject5;
            }
            else
            {
              if ((!(localObject3 instanceof Pair)) || (n != 0) || (k != 0) || (localObject5 != null)) {
                break label1360;
              }
              localObject7 = (Pair)localObject3;
              if (((Pair)localObject7).getCdr() != LList.Empty) {
                break label1360;
              }
              localObject3 = paramPair;
              localObject1 = localObject7;
              paramPair = ((Pair)localObject7).getCdr();
              i = 1;
              j = m;
              l3 = l2;
            }
          }
        }
        label1360:
        localObject1 = null;
        label1363:
        if (localObject1 != LList.Empty)
        {
          paramClassExp = new StringBuilder().append("invalid argument list for slot '").append(localObject4).append('\'').append(" args:");
          if (localObject1 == null) {}
          for (paramPair = "null";; paramPair = localObject1.getClass().getName())
          {
            paramTranslator.error('e', paramPair);
            return null;
          }
        }
        if (k != 0)
        {
          if (m != 2048) {
            break label1496;
          }
          i = 1;
          if (localObject2 == null) {
            break label1502;
          }
          paramPair = (Pair)localObject2;
        }
        for (;;)
        {
          localVector.addElement(paramPair);
          localVector.addElement(localObject6);
          if (localObject2 != null) {
            break label1521;
          }
          if (k != 0) {
            break label1576;
          }
          paramTranslator.error('e', "missing field name");
          return null;
          label1496:
          i = 0;
          break;
          label1502:
          if (i != 0) {
            paramPair = Boolean.TRUE;
          } else {
            paramPair = Boolean.FALSE;
          }
        }
        label1521:
        if (localObject5 != null) {
          ((Declaration)localObject2).setType(paramTranslator.exp2Type((Pair)localObject5));
        }
        if (m != 0) {
          ((Declaration)localObject2).setFlag(m);
        }
        if (l2 != 0L) {
          ((Declaration)localObject2).setFlag(l2);
        }
        ((Declaration)localObject2).setCanRead(true);
        ((Declaration)localObject2).setCanWrite(true);
      }
      for (;;)
      {
        label1576:
        paramTranslator.popPositionOf(localObject11);
        paramPair = localPair2;
        break;
        if ((localObject4 instanceof Pair))
        {
          localObject1 = (Pair)localObject4;
          localObject2 = ((Pair)localObject1).getCar();
          if ((!(localObject2 instanceof String)) && (!(localObject2 instanceof Symbol)))
          {
            paramTranslator.error('e', "missing method name");
            return null;
          }
          paramPair = new LambdaExp();
          Translator.setLine(paramClassExp.addMethod(paramPair, localObject2), localObject1);
          if (localPair4 == null) {
            localPair1 = paramPair;
          }
          for (;;)
          {
            localPair4 = paramPair;
            break;
            localPair4.nextSibling = paramPair;
          }
        }
        paramTranslator.error('e', "invalid field/method definition");
      }
    }
    if (l1 != 0L) {
      paramClassExp.nameDecl.setFlag(l1);
    }
    return new Object[] { paramClassExp, localObject8, localVector, localPair1, localObject10, localPair3 };
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\object.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */