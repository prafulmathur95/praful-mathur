package kawa.lang;

import gnu.bytecode.Type;
import gnu.expr.BeginExp;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.LangExp;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.ThisExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.text.SourceMessages;
import kawa.standard.object;

public class Lambda
  extends Syntax
{
  public static final Keyword nameKeyword = Keyword.make("name");
  public Expression defaultDefault = QuoteExp.falseExp;
  public Object keyKeyword;
  public Object optionalKeyword;
  public Object restKeyword;
  
  private static void addParam(Declaration paramDeclaration, ScopeExp paramScopeExp, LambdaExp paramLambdaExp, Translator paramTranslator)
  {
    Declaration localDeclaration = paramDeclaration;
    if (paramScopeExp != null) {
      localDeclaration = paramTranslator.makeRenamedAlias(paramDeclaration, paramScopeExp);
    }
    paramLambdaExp.addDeclaration(localDeclaration);
    if (paramScopeExp != null) {
      localDeclaration.context = paramScopeExp;
    }
  }
  
  public void print(Consumer paramConsumer)
  {
    paramConsumer.write("#<builtin lambda>");
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator)
  {
    if (!(paramObject instanceof Pair)) {
      paramObject = paramTranslator.syntaxError("missing formals in lambda");
    }
    int i;
    do
    {
      return (Expression)paramObject;
      i = paramTranslator.getMessages().getErrorCount();
      LambdaExp localLambdaExp = new LambdaExp();
      paramObject = (Pair)paramObject;
      Translator.setLine(localLambdaExp, paramObject);
      rewrite(localLambdaExp, ((Pair)paramObject).getCar(), ((Pair)paramObject).getCdr(), paramTranslator, null);
      paramObject = localLambdaExp;
    } while (paramTranslator.getMessages().getErrorCount() <= i);
    return new ErrorExp("bad lambda expression");
  }
  
  public void rewrite(LambdaExp paramLambdaExp, Object paramObject1, Object paramObject2, Translator paramTranslator, TemplateScope paramTemplateScope)
  {
    rewriteFormals(paramLambdaExp, paramObject1, paramTranslator, paramTemplateScope);
    if ((paramObject2 instanceof PairWithPosition)) {
      paramLambdaExp.setFile(((PairWithPosition)paramObject2).getFileName());
    }
    rewriteBody(paramLambdaExp, rewriteAttrs(paramLambdaExp, paramObject2, paramTranslator), paramTranslator);
  }
  
  public Object rewriteAttrs(LambdaExp paramLambdaExp, Object paramObject, Translator paramTranslator)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    int k = 0;
    int j = 0;
    SyntaxForm localSyntaxForm = null;
    while ((paramObject instanceof SyntaxForm))
    {
      localSyntaxForm = (SyntaxForm)paramObject;
      paramObject = localSyntaxForm.getDatum();
    }
    if (!(paramObject instanceof Pair)) {}
    int i;
    Object localObject1;
    Object localObject4;
    do
    {
      for (;;)
      {
        i = k | j;
        if (i != 0) {
          paramLambdaExp.nameDecl.setFlag(i);
        }
        paramLambdaExp = (LambdaExp)paramObject;
        if (localSyntaxForm != null) {
          paramLambdaExp = SyntaxForms.fromDatumIfNeeded(paramObject, localSyntaxForm);
        }
        return paramLambdaExp;
        localObject5 = (Pair)paramObject;
        localObject1 = Translator.stripSyntax(((Pair)localObject5).getCar());
        if (paramTranslator.matches(localObject1, "::")) {
          localObject4 = null;
        }
        do
        {
          localObject1 = localSyntaxForm;
          for (localObject5 = ((Pair)localObject5).getCdr(); (localObject5 instanceof SyntaxForm); localObject5 = ((SyntaxForm)localObject1).getDatum()) {
            localObject1 = (SyntaxForm)localObject5;
          }
          localObject4 = localObject1;
        } while ((localObject1 instanceof Keyword));
      }
    } while (!(localObject5 instanceof Pair));
    Object localObject5 = (Pair)localObject5;
    int m;
    if (localObject4 == null) {
      if ((paramLambdaExp.isClassMethod()) && ("*init*".equals(paramLambdaExp.getName())))
      {
        paramTranslator.error('e', "explicit return type for '*init*' method");
        localObject1 = localObject2;
        i = j;
        paramObject = localObject3;
        m = k;
      }
    }
    for (;;)
    {
      localObject4 = ((Pair)localObject5).getCdr();
      k = m;
      localObject3 = paramObject;
      j = i;
      localObject2 = localObject1;
      paramObject = localObject4;
      break;
      paramLambdaExp.body = new LangExp(new Object[] { localObject5, localObject1 });
      m = k;
      paramObject = localObject3;
      i = j;
      localObject1 = localObject2;
      continue;
      if (localObject4 == object.accessKeyword)
      {
        paramObject = paramTranslator.rewrite_car((Pair)localObject5, (SyntaxForm)localObject1);
        if ((paramObject instanceof QuoteExp))
        {
          paramObject = ((QuoteExp)paramObject).getValue();
          if (((paramObject instanceof SimpleSymbol)) || ((paramObject instanceof CharSequence))) {}
        }
        else
        {
          paramTranslator.error('e', "access: value not a constant symbol or string");
          m = k;
          paramObject = localObject3;
          i = j;
          localObject1 = localObject2;
          continue;
        }
        if (paramLambdaExp.nameDecl == null)
        {
          paramTranslator.error('e', "access: not allowed for anonymous function");
          m = k;
          paramObject = localObject3;
          i = j;
          localObject1 = localObject2;
        }
        else
        {
          paramObject = paramObject.toString();
          if ("private".equals(paramObject)) {
            i = 16777216;
          }
          for (;;)
          {
            if ((localObject3 != null) && (paramObject != null)) {
              paramTranslator.error('e', "duplicate access specifiers - " + (String)localObject3 + " and " + (String)paramObject);
            }
            m = i;
            i = j;
            localObject1 = localObject2;
            break;
            if ("protected".equals(paramObject))
            {
              i = 33554432;
            }
            else if ("public".equals(paramObject))
            {
              i = 67108864;
            }
            else if ("package".equals(paramObject))
            {
              i = 134217728;
            }
            else
            {
              paramTranslator.error('e', "unknown access specifier");
              i = k;
            }
          }
        }
      }
      else if (localObject4 == object.allocationKeyword)
      {
        paramObject = paramTranslator.rewrite_car((Pair)localObject5, (SyntaxForm)localObject1);
        if ((paramObject instanceof QuoteExp))
        {
          paramObject = ((QuoteExp)paramObject).getValue();
          if (((paramObject instanceof SimpleSymbol)) || ((paramObject instanceof CharSequence))) {}
        }
        else
        {
          paramTranslator.error('e', "allocation: value not a constant symbol or string");
          m = k;
          paramObject = localObject3;
          i = j;
          localObject1 = localObject2;
          continue;
        }
        if (paramLambdaExp.nameDecl == null)
        {
          paramTranslator.error('e', "allocation: not allowed for anonymous function");
          m = k;
          paramObject = localObject3;
          i = j;
          localObject1 = localObject2;
        }
        else
        {
          localObject1 = paramObject.toString();
          if (("class".equals(localObject1)) || ("static".equals(localObject1))) {
            i = 2048;
          }
          for (;;)
          {
            if ((localObject2 != null) && (localObject1 != null)) {
              paramTranslator.error('e', "duplicate allocation specifiers - " + (String)localObject2 + " and " + (String)localObject1);
            }
            m = k;
            paramObject = localObject3;
            break;
            if ("instance".equals(localObject1))
            {
              i = 4096;
            }
            else
            {
              paramTranslator.error('e', "unknown allocation specifier");
              i = j;
            }
          }
        }
      }
      else if (localObject4 == object.throwsKeyword)
      {
        paramObject = ((Pair)localObject5).getCar();
        m = Translator.listLength(paramObject);
        if (m < 0)
        {
          paramTranslator.error('e', "throws: not followed by a list");
          m = k;
          paramObject = localObject3;
          i = j;
          localObject1 = localObject2;
        }
        else
        {
          localObject4 = new Expression[m];
          i = 0;
          while (i < m)
          {
            while ((paramObject instanceof SyntaxForm))
            {
              localObject1 = (SyntaxForm)paramObject;
              paramObject = ((SyntaxForm)localObject1).getDatum();
            }
            paramObject = (Pair)paramObject;
            localObject4[i] = paramTranslator.rewrite_car((Pair)paramObject, (SyntaxForm)localObject1);
            Translator.setLine(localObject4[i], paramObject);
            paramObject = ((Pair)paramObject).getCdr();
            i += 1;
          }
          paramLambdaExp.setExceptions((Expression[])localObject4);
          m = k;
          paramObject = localObject3;
          i = j;
          localObject1 = localObject2;
        }
      }
      else if (localObject4 == nameKeyword)
      {
        localObject4 = paramTranslator.rewrite_car((Pair)localObject5, (SyntaxForm)localObject1);
        m = k;
        paramObject = localObject3;
        i = j;
        localObject1 = localObject2;
        if ((localObject4 instanceof QuoteExp))
        {
          paramLambdaExp.setName(((QuoteExp)localObject4).getValue().toString());
          m = k;
          paramObject = localObject3;
          i = j;
          localObject1 = localObject2;
        }
      }
      else
      {
        paramTranslator.error('w', "unknown procedure property " + localObject4);
        m = k;
        paramObject = localObject3;
        i = j;
        localObject1 = localObject2;
      }
    }
  }
  
  public void rewriteBody(LambdaExp paramLambdaExp, Object paramObject, Translator paramTranslator)
  {
    int j = 0;
    if ((paramTranslator.curMethodLambda == null) && (paramLambdaExp.nameDecl != null) && (paramTranslator.getModule().getFlag(131072))) {
      paramTranslator.curMethodLambda = paramLambdaExp;
    }
    paramTranslator.currentScope();
    paramTranslator.pushScope(paramLambdaExp);
    Object localObject3 = null;
    if (paramLambdaExp.keywords == null)
    {
      i = 0;
      if (paramLambdaExp.defaultArgs != null) {
        break label291;
      }
    }
    label291:
    for (int i = 0;; i = paramLambdaExp.defaultArgs.length - i)
    {
      int m = 0;
      int k = 0;
      localObject1 = paramLambdaExp.firstDecl();
      while (localObject1 != null)
      {
        localObject2 = localObject1;
        int n = j;
        if (((Declaration)localObject1).isAlias())
        {
          localObject2 = Translator.getOriginalRef((Declaration)localObject1).getBinding();
          paramLambdaExp.replaceFollowing((Declaration)localObject3, (Declaration)localObject2);
          ((Declaration)localObject2).context = paramLambdaExp;
          paramTranslator.pushRenamedAlias((Declaration)localObject1);
          n = j + 1;
        }
        localObject1 = ((Declaration)localObject2).getTypeExp();
        if ((localObject1 instanceof LangExp)) {
          ((Declaration)localObject2).setType(paramTranslator.exp2Type((Pair)((LangExp)localObject1).getLangValue()));
        }
        localObject3 = localObject2;
        int i1 = k;
        if (m >= paramLambdaExp.min_args) {
          if ((m >= paramLambdaExp.min_args + i) && (paramLambdaExp.max_args < 0))
          {
            i1 = k;
            if (m == paramLambdaExp.min_args + i) {}
          }
          else
          {
            paramLambdaExp.defaultArgs[k] = paramTranslator.rewrite(paramLambdaExp.defaultArgs[k]);
            i1 = k + 1;
          }
        }
        m += 1;
        paramTranslator.lexical.push((Declaration)localObject2);
        localObject1 = ((Declaration)localObject2).nextDecl();
        j = n;
        k = i1;
      }
      i = paramLambdaExp.keywords.length;
      break;
    }
    if ((paramLambdaExp.isClassMethod()) && (!paramLambdaExp.nameDecl.getFlag(2048L))) {
      paramLambdaExp.add(null, new Declaration(ThisExp.THIS_NAME));
    }
    Object localObject2 = paramTranslator.curLambda;
    paramTranslator.curLambda = paramLambdaExp;
    Object localObject1 = paramLambdaExp.returnType;
    if ((paramLambdaExp.body instanceof LangExp))
    {
      localObject1 = (Object[])((LangExp)paramLambdaExp.body).getLangValue();
      localObject1 = paramTranslator.rewrite_car((Pair)localObject1[0], (SyntaxForm)localObject1[1]);
      localObject1 = paramTranslator.getLanguage().getTypeFor((Expression)localObject1);
    }
    paramLambdaExp.body = paramTranslator.rewrite_body(paramObject);
    paramTranslator.curLambda = ((LambdaExp)localObject2);
    if ((paramLambdaExp.body instanceof BeginExp))
    {
      paramObject = ((BeginExp)paramLambdaExp.body).getExpressions();
      i = paramObject.length;
      if (i > 1) {
        if (!(paramObject[0] instanceof ReferenceExp))
        {
          localObject2 = paramObject[0].valueIfConstant();
          if ((!(localObject2 instanceof Type)) && (!(localObject2 instanceof Class))) {}
        }
        else
        {
          localObject1 = paramObject[0];
          i -= 1;
          if (i == 1)
          {
            paramLambdaExp.body = paramObject[1];
            paramLambdaExp.setCoercedReturnValue((Expression)localObject1, paramTranslator.getLanguage());
          }
        }
      }
    }
    for (;;)
    {
      paramTranslator.pop(paramLambdaExp);
      paramLambdaExp.countDecls();
      paramTranslator.popRenamedAlias(j);
      paramLambdaExp.countDecls();
      if (paramTranslator.curMethodLambda == paramLambdaExp) {
        paramTranslator.curMethodLambda = null;
      }
      return;
      localObject2 = new Expression[i];
      System.arraycopy(paramObject, 1, localObject2, 0, i);
      paramLambdaExp.body = BeginExp.canonicalize((Expression[])localObject2);
      break;
      paramLambdaExp.setCoercedReturnType((Type)localObject1);
    }
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    paramTranslator = rewrite(paramPair.getCdr(), paramTranslator);
    Translator.setLine(paramTranslator, paramPair);
    return paramTranslator;
  }
  
  public void rewriteFormals(LambdaExp paramLambdaExp, Object paramObject, Translator paramTranslator, TemplateScope paramTemplateScope)
  {
    if (paramLambdaExp.getSymbol() == null)
    {
      localObject1 = paramLambdaExp.getFileName();
      i = paramLambdaExp.getLineNumber();
      if ((localObject1 != null) && (i > 0)) {
        paramLambdaExp.setSourceLocation((String)localObject1, i);
      }
    }
    Object localObject1 = paramObject;
    int j = -1;
    int k = -1;
    int i = -1;
    Object localObject2 = localObject1;
    if ((localObject1 instanceof SyntaxForm)) {
      localObject2 = ((SyntaxForm)localObject1).getDatum();
    }
    if (!(localObject2 instanceof Pair))
    {
      if (!(localObject2 instanceof Symbol)) {
        break label646;
      }
      if ((j >= 0) || (i >= 0) || (k >= 0)) {
        paramTranslator.syntaxError("dotted rest-arg after " + this.optionalKeyword + ", " + this.restKeyword + ", or " + this.keyKeyword);
      }
    }
    else
    {
      localObject2 = (Pair)localObject2;
      localObject3 = ((Pair)localObject2).getCar();
      localObject1 = localObject3;
      if ((localObject3 instanceof SyntaxForm)) {
        localObject1 = ((SyntaxForm)localObject3).getDatum();
      }
      if (localObject1 == this.optionalKeyword)
      {
        if (j >= 0)
        {
          paramTranslator.syntaxError("multiple " + this.optionalKeyword + " in parameter list");
          return;
        }
        if ((k >= 0) || (i >= 0))
        {
          paramTranslator.syntaxError(this.optionalKeyword.toString() + " after " + this.restKeyword + " or " + this.keyKeyword);
          return;
        }
        j = 0;
        localObject1 = localObject2;
      }
      for (;;)
      {
        ((Pair)localObject1).getCdr();
        localObject1 = ((Pair)localObject1).getCdr();
        break;
        if (localObject1 == this.restKeyword)
        {
          if (k >= 0)
          {
            paramTranslator.syntaxError("multiple " + this.restKeyword + " in parameter list");
            return;
          }
          if (i >= 0)
          {
            paramTranslator.syntaxError(this.restKeyword.toString() + " after " + this.keyKeyword);
            return;
          }
          k = 0;
          localObject1 = localObject2;
        }
        else if (localObject1 == this.keyKeyword)
        {
          if (i >= 0)
          {
            paramTranslator.syntaxError("multiple " + this.keyKeyword + " in parameter list");
            return;
          }
          i = 0;
          localObject1 = localObject2;
        }
        else if ((paramTranslator.matches(((Pair)localObject2).getCar(), "::")) && ((((Pair)localObject2).getCdr() instanceof Pair)))
        {
          localObject1 = (Pair)((Pair)localObject2).getCdr();
        }
        else if (i >= 0)
        {
          i += 1;
          localObject1 = localObject2;
        }
        else if (k >= 0)
        {
          k += 1;
          localObject1 = localObject2;
        }
        else if (j >= 0)
        {
          j += 1;
          localObject1 = localObject2;
        }
        else
        {
          paramLambdaExp.min_args += 1;
          localObject1 = localObject2;
        }
      }
    }
    k = 1;
    while (k > 1)
    {
      paramTranslator.syntaxError("multiple " + this.restKeyword + " parameters");
      return;
      label646:
      if (localObject2 != LList.Empty)
      {
        paramTranslator.syntaxError("misformed formals in lambda");
        return;
      }
    }
    int m = j;
    if (j < 0) {
      m = 0;
    }
    j = k;
    if (k < 0) {
      j = 0;
    }
    k = i;
    if (i < 0) {
      k = 0;
    }
    label709:
    TemplateScope localTemplateScope;
    if (j > 0)
    {
      paramLambdaExp.max_args = -1;
      if (m + k > 0) {
        paramLambdaExp.defaultArgs = new Expression[m + k];
      }
      if (k > 0) {
        paramLambdaExp.keywords = new Keyword[k];
      }
      localObject1 = paramObject;
      k = 0;
      i = 0;
      localTemplateScope = null;
      paramObject = paramTemplateScope;
      paramTemplateScope = (TemplateScope)localObject1;
    }
    Pair localPair1;
    Object localObject4;
    for (;;)
    {
      localObject2 = paramTemplateScope;
      if ((paramTemplateScope instanceof SyntaxForm))
      {
        paramObject = (SyntaxForm)paramTemplateScope;
        localObject2 = ((SyntaxForm)paramObject).getDatum();
        paramObject = ((SyntaxForm)paramObject).getScope();
      }
      localObject1 = paramObject;
      if (!(localObject2 instanceof Pair))
      {
        paramTemplateScope = (TemplateScope)localObject2;
        if ((localObject2 instanceof SyntaxForm))
        {
          paramObject = (SyntaxForm)localObject2;
          paramTemplateScope = ((SyntaxForm)paramObject).getDatum();
          paramObject = ((SyntaxForm)paramObject).getScope();
        }
        if (!(paramTemplateScope instanceof Symbol)) {
          break;
        }
        paramTemplateScope = new Declaration(paramTemplateScope);
        paramTemplateScope.setType(LangObjType.listType);
        paramTemplateScope.setFlag(262144L);
        paramTemplateScope.noteValue(null);
        addParam(paramTemplateScope, (ScopeExp)paramObject, paramLambdaExp, paramTranslator);
        return;
        paramLambdaExp.max_args = (paramLambdaExp.min_args + m + k * 2);
        break label709;
      }
      localPair1 = (Pair)localObject2;
      localObject3 = localPair1.getCar();
      paramTemplateScope = (TemplateScope)localObject3;
      if ((localObject3 instanceof SyntaxForm))
      {
        localObject1 = (SyntaxForm)localObject3;
        paramTemplateScope = ((SyntaxForm)localObject1).getDatum();
        localObject1 = ((SyntaxForm)localObject1).getScope();
      }
      if ((paramTemplateScope != this.optionalKeyword) && (paramTemplateScope != this.restKeyword) && (paramTemplateScope != this.keyKeyword)) {
        break label1012;
      }
      localObject4 = localPair1;
      j = k;
      localTemplateScope = paramTemplateScope;
      paramTemplateScope = ((Pair)localObject4).getCdr();
      k = j;
    }
    label1012:
    Object localObject11 = paramTranslator.pushPositionOf(localPair1);
    Object localObject9 = null;
    Expression localExpression = this.defaultDefault;
    Object localObject6 = null;
    Object localObject7 = null;
    if (paramTranslator.matches(paramTemplateScope, "::"))
    {
      paramTranslator.syntaxError("'::' must follow parameter name");
      return;
    }
    Object localObject3 = paramTranslator.namespaceResolve(paramTemplateScope);
    Object localObject10;
    Object localObject5;
    Object localObject8;
    if ((localObject3 instanceof Symbol))
    {
      localObject10 = localExpression;
      localObject5 = localObject3;
      localObject4 = localPair1;
      localObject8 = localObject1;
      paramTemplateScope = (TemplateScope)localObject7;
      if ((localPair1.getCdr() instanceof Pair))
      {
        localObject6 = (Pair)localPair1.getCdr();
        localObject10 = localExpression;
        localObject5 = localObject3;
        localObject4 = localPair1;
        localObject8 = localObject1;
        paramTemplateScope = (TemplateScope)localObject7;
        if (paramTranslator.matches(((Pair)localObject6).getCar(), "::"))
        {
          if (!(localPair1.getCdr() instanceof Pair))
          {
            paramTranslator.syntaxError("'::' not followed by a type specifier (for parameter '" + localObject3 + "')");
            return;
          }
          localObject4 = (Pair)((Pair)localObject6).getCdr();
          paramTemplateScope = (TemplateScope)localObject4;
          localObject8 = localObject1;
          localObject5 = localObject3;
          localObject10 = localExpression;
        }
      }
    }
    while (localObject5 == null)
    {
      paramTranslator.syntaxError("parameter is neither name nor (name :: type) nor (name default): " + localObject4);
      return;
      localObject10 = localExpression;
      localObject5 = localObject9;
      localObject4 = localPair1;
      localObject8 = localObject1;
      paramTemplateScope = (TemplateScope)localObject7;
      if ((localObject3 instanceof Pair))
      {
        Pair localPair2 = (Pair)localObject3;
        localObject4 = localPair2.getCar();
        paramTemplateScope = (TemplateScope)localObject4;
        localObject3 = localObject1;
        if ((localObject4 instanceof SyntaxForm))
        {
          localObject1 = (SyntaxForm)localObject4;
          paramTemplateScope = ((SyntaxForm)localObject1).getDatum();
          localObject3 = ((SyntaxForm)localObject1).getScope();
        }
        localObject1 = paramTranslator.namespaceResolve(paramTemplateScope);
        localObject10 = localExpression;
        localObject5 = localObject9;
        localObject4 = localPair1;
        localObject8 = localObject3;
        paramTemplateScope = (TemplateScope)localObject7;
        if ((localObject1 instanceof Symbol))
        {
          localObject10 = localExpression;
          localObject5 = localObject9;
          localObject4 = localPair1;
          localObject8 = localObject3;
          paramTemplateScope = (TemplateScope)localObject7;
          if ((localPair2.getCdr() instanceof Pair))
          {
            localObject9 = localObject1;
            localObject1 = (Pair)localPair2.getCdr();
            paramTemplateScope = (TemplateScope)localObject1;
            if (paramTranslator.matches(((Pair)localObject1).getCar(), "::"))
            {
              if (!(((Pair)localObject1).getCdr() instanceof Pair))
              {
                paramTranslator.syntaxError("'::' not followed by a type specifier (for parameter '" + localObject9 + "')");
                return;
              }
              paramTemplateScope = (Pair)((Pair)localObject1).getCdr();
              localObject6 = paramTemplateScope;
              if ((paramTemplateScope.getCdr() instanceof Pair)) {
                paramTemplateScope = (Pair)paramTemplateScope.getCdr();
              }
            }
            else
            {
              label1509:
              localObject7 = localExpression;
              localObject1 = paramTemplateScope;
              if (paramTemplateScope != null)
              {
                localObject7 = localExpression;
                localObject1 = paramTemplateScope;
                if (localTemplateScope != null)
                {
                  localObject7 = paramTemplateScope.getCar();
                  if (!(paramTemplateScope.getCdr() instanceof Pair)) {
                    break label1675;
                  }
                }
              }
            }
            for (localObject1 = (Pair)paramTemplateScope.getCdr();; localObject1 = null)
            {
              localObject10 = localObject7;
              localObject5 = localObject9;
              localObject4 = localPair1;
              localObject8 = localObject3;
              paramTemplateScope = (TemplateScope)localObject6;
              if (localObject1 == null) {
                break;
              }
              if (localObject6 == null) {
                break label1725;
              }
              paramTranslator.syntaxError("duplicate type specifier for parameter '" + localObject9 + '\'');
              return;
              if (paramTemplateScope.getCdr() == LList.Empty)
              {
                paramTemplateScope = null;
                break label1509;
              }
              paramTranslator.syntaxError("improper list in specifier for parameter '" + localObject9 + "')");
              return;
              label1675:
              if (paramTemplateScope.getCdr() != LList.Empty) {
                break label1692;
              }
            }
            label1692:
            paramTranslator.syntaxError("improper list in specifier for parameter '" + localObject9 + "')");
            return;
            label1725:
            paramTemplateScope = (TemplateScope)localObject1;
            localObject10 = localObject7;
            localObject5 = localObject9;
            localObject4 = localPair1;
            localObject8 = localObject3;
            if (((Pair)localObject1).getCdr() != LList.Empty)
            {
              paramTranslator.syntaxError("junk at end of specifier for parameter '" + localObject9 + '\'' + " after type " + ((Pair)localObject1).getCar());
              return;
            }
          }
        }
      }
    }
    if (localTemplateScope != this.optionalKeyword)
    {
      j = k;
      if (localTemplateScope != this.keyKeyword) {}
    }
    else
    {
      paramLambdaExp.defaultArgs[k] = new LangExp(localObject10);
      j = k + 1;
    }
    k = i;
    if (localTemplateScope == this.keyKeyword)
    {
      localObject3 = paramLambdaExp.keywords;
      if ((localObject5 instanceof Symbol))
      {
        localObject1 = ((Symbol)localObject5).getName();
        label1883:
        localObject3[i] = Keyword.make((String)localObject1);
        k = i + 1;
      }
    }
    else
    {
      localObject1 = new Declaration(localObject5);
      Translator.setLine((Declaration)localObject1, localObject2);
      if (paramTemplateScope == null) {
        break label1990;
      }
      ((Declaration)localObject1).setTypeExp(new LangExp(paramTemplateScope));
      ((Declaration)localObject1).setFlag(8192L);
    }
    for (;;)
    {
      ((Declaration)localObject1).setFlag(262144L);
      ((Declaration)localObject1).noteValue(null);
      addParam((Declaration)localObject1, (ScopeExp)localObject8, paramLambdaExp, paramTranslator);
      paramTranslator.popPositionOf(localObject11);
      i = k;
      break;
      localObject1 = localObject5.toString();
      break label1883;
      label1990:
      if (localTemplateScope == this.restKeyword) {
        ((Declaration)localObject1).setType(LangObjType.listType);
      }
    }
  }
  
  public void setKeywords(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    this.optionalKeyword = paramObject1;
    this.restKeyword = paramObject2;
    this.keyKeyword = paramObject3;
  }
  
  public Object skipAttrs(LambdaExp paramLambdaExp, Object paramObject, Translator paramTranslator)
  {
    if ((paramObject instanceof Pair))
    {
      paramLambdaExp = (Pair)paramObject;
      if ((paramLambdaExp.getCdr() instanceof Pair)) {}
    }
    else
    {
      return paramObject;
    }
    Object localObject = paramLambdaExp.getCar();
    if (paramTranslator.matches(localObject, "::")) {}
    while ((localObject instanceof Keyword))
    {
      paramObject = ((Pair)paramLambdaExp.getCdr()).getCdr();
      break;
    }
    return paramObject;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\Lambda.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */