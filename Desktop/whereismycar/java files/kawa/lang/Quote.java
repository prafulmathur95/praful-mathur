package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.functions.CompileNamedPart;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Environment;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.util.IdentityHashMap;
import java.util.Vector;

public class Quote
  extends Syntax
{
  private static final Object CYCLE;
  protected static final int QUOTE_DEPTH = -1;
  private static final Object WORKING;
  static final Method appendMethod = quoteType.getDeclaredMethod("append$V", 1);
  static final Method consXMethod;
  static final Method makePairMethod = Compilation.typePair.getDeclaredMethod("make", 2);
  static final Method makeVectorMethod = ClassType.make("gnu.lists.FVector").getDeclaredMethod("make", 1);
  public static final Quote plainQuote = new Quote("quote", false);
  public static final Quote quasiQuote = new Quote("quasiquote", true);
  static final ClassType quoteType;
  static final Method vectorAppendMethod;
  protected boolean isQuasi;
  
  static
  {
    WORKING = new String("(working)");
    CYCLE = new String("(cycle)");
    vectorAppendMethod = ClassType.make("kawa.standard.vector_append").getDeclaredMethod("apply$V", 1);
    quoteType = ClassType.make("kawa.lang.Quote");
    consXMethod = quoteType.getDeclaredMethod("consX$V", 1);
  }
  
  public Quote(String paramString, boolean paramBoolean)
  {
    super(paramString);
    this.isQuasi = paramBoolean;
  }
  
  public static Object append$V(Object[] paramArrayOfObject)
  {
    int i = paramArrayOfObject.length;
    if (i == 0) {
      localObject2 = LList.Empty;
    }
    Object localObject1;
    do
    {
      return localObject2;
      localObject1 = paramArrayOfObject[(i - 1)];
      i -= 1;
      i -= 1;
      localObject2 = localObject1;
    } while (i < 0);
    Object localObject3 = paramArrayOfObject[i];
    Object localObject4 = null;
    SyntaxForm localSyntaxForm = null;
    Object localObject2 = null;
    while ((localObject3 instanceof SyntaxForm))
    {
      localSyntaxForm = (SyntaxForm)localObject3;
      localObject3 = localSyntaxForm.getDatum();
    }
    if (localObject3 == LList.Empty)
    {
      if (localObject4 == null) {
        break label188;
      }
      ((Pair)localObject4).setCdr(localObject1);
    }
    for (;;)
    {
      localObject1 = localObject2;
      break;
      Pair localPair = (Pair)localObject3;
      Object localObject5 = localPair.getCar();
      localObject3 = localObject5;
      if (localSyntaxForm != null)
      {
        localObject3 = localObject5;
        if (!(localObject5 instanceof SyntaxForm)) {
          localObject3 = SyntaxForms.makeForm(localObject5, localSyntaxForm.getScope());
        }
      }
      localObject3 = new Pair(localObject3, null);
      if (localObject4 == null) {
        localObject2 = localObject3;
      }
      for (;;)
      {
        localObject5 = localPair.getCdr();
        localObject4 = localObject3;
        localObject3 = localObject5;
        break;
        ((Pair)localObject4).setCdr(localObject3);
      }
      label188:
      localObject2 = localObject1;
    }
  }
  
  public static Object consX$V(Object[] paramArrayOfObject)
  {
    return LList.consX(paramArrayOfObject);
  }
  
  private static ApplyExp makeInvokeMakeVector(Expression[] paramArrayOfExpression)
  {
    return new ApplyExp(makeVectorMethod, paramArrayOfExpression);
  }
  
  public static Symbol makeSymbol(Namespace paramNamespace, Object paramObject)
  {
    if ((paramObject instanceof CharSequence)) {}
    for (paramObject = ((CharSequence)paramObject).toString();; paramObject = (String)paramObject) {
      return paramNamespace.getSymbol(((String)paramObject).intern());
    }
  }
  
  public static Object quote(Object paramObject)
  {
    return plainQuote.expand(paramObject, -1, (Translator)Compilation.getCurrent());
  }
  
  public static Object quote(Object paramObject, Translator paramTranslator)
  {
    return plainQuote.expand(paramObject, -1, paramTranslator);
  }
  
  protected Expression coerceExpression(Object paramObject, Translator paramTranslator)
  {
    if ((paramObject instanceof Expression)) {
      return (Expression)paramObject;
    }
    return leaf(paramObject, paramTranslator);
  }
  
  Object expand(Object paramObject1, int paramInt, SyntaxForm paramSyntaxForm, Object paramObject2, Translator paramTranslator)
  {
    IdentityHashMap localIdentityHashMap = (IdentityHashMap)paramObject2;
    Object localObject1 = localIdentityHashMap.get(paramObject1);
    if (localObject1 == WORKING) {
      localIdentityHashMap.put(paramObject1, CYCLE);
    }
    while ((localObject1 == CYCLE) || (localObject1 != null)) {
      return localObject1;
    }
    if ((paramObject1 instanceof Pair)) {
      paramSyntaxForm = expand_pair((Pair)paramObject1, paramInt, paramSyntaxForm, paramObject2, paramTranslator);
    }
    for (;;)
    {
      if ((paramObject1 != paramSyntaxForm) && (localIdentityHashMap.get(paramObject1) == CYCLE)) {
        paramTranslator.error('e', "cycle in non-literal data");
      }
      localIdentityHashMap.put(paramObject1, paramSyntaxForm);
      return paramSyntaxForm;
      if ((paramObject1 instanceof SyntaxForm))
      {
        paramSyntaxForm = (SyntaxForm)paramObject1;
        paramSyntaxForm = expand(paramSyntaxForm.getDatum(), paramInt, paramSyntaxForm, paramObject2, paramTranslator);
      }
      else
      {
        if ((paramObject1 instanceof FVector))
        {
          localObject1 = (FVector)paramObject1;
          int n = ((FVector)localObject1).size();
          Object[] arrayOfObject = new Object[n];
          byte[] arrayOfByte = new byte[n];
          int i = 0;
          int j = 0;
          if (j < n)
          {
            Object localObject2 = ((FVector)localObject1).get(j);
            int m = paramInt;
            int k = m;
            if ((localObject2 instanceof Pair))
            {
              k = m;
              if (paramInt > -1)
              {
                Pair localPair = (Pair)localObject2;
                k = m;
                if (paramTranslator.matches(localPair.getCar(), paramSyntaxForm, "unquote-splicing"))
                {
                  m -= 1;
                  k = m;
                  if (m == 0)
                  {
                    if ((localPair.getCdr() instanceof Pair))
                    {
                      localObject2 = (Pair)localPair.getCdr();
                      if (((Pair)localObject2).getCdr() == LList.Empty) {}
                    }
                    else
                    {
                      return paramTranslator.syntaxError("invalid used of " + localPair.getCar() + " in quasiquote template");
                    }
                    arrayOfObject[j] = paramTranslator.rewrite_car((Pair)localObject2, paramSyntaxForm);
                    arrayOfByte[j] = 3;
                  }
                }
              }
            }
            for (;;)
            {
              k = i;
              if (arrayOfByte[j] > i) {
                k = arrayOfByte[j];
              }
              j += 1;
              i = k;
              break;
              arrayOfObject[j] = expand(localObject2, k, paramSyntaxForm, paramObject2, paramTranslator);
              if (arrayOfObject[j] == localObject2) {
                arrayOfByte[j] = 0;
              } else if ((arrayOfObject[j] instanceof Expression)) {
                arrayOfByte[j] = 2;
              } else {
                arrayOfByte[j] = 1;
              }
            }
          }
          if (i == 0) {
            paramSyntaxForm = (SyntaxForm)localObject1;
          }
          for (;;)
          {
            break;
            if (i == 1)
            {
              paramSyntaxForm = new FVector(arrayOfObject);
            }
            else
            {
              paramSyntaxForm = new Expression[n];
              paramInt = 0;
              if (paramInt < n)
              {
                if (arrayOfByte[paramInt] == 3) {
                  paramSyntaxForm[paramInt] = ((Expression)arrayOfObject[paramInt]);
                }
                for (;;)
                {
                  paramInt += 1;
                  break;
                  if (i < 3) {
                    paramSyntaxForm[paramInt] = coerceExpression(arrayOfObject[paramInt], paramTranslator);
                  } else if (arrayOfByte[paramInt] < 2) {
                    paramSyntaxForm[paramInt] = leaf(new FVector(new Object[] { arrayOfObject[paramInt] }), paramTranslator);
                  } else {
                    paramSyntaxForm[paramInt] = makeInvokeMakeVector(new Expression[] { (Expression)arrayOfObject[paramInt] });
                  }
                }
              }
              if (i < 3) {
                paramSyntaxForm = makeInvokeMakeVector(paramSyntaxForm);
              } else {
                paramSyntaxForm = new ApplyExp(vectorAppendMethod, paramSyntaxForm);
              }
            }
          }
        }
        paramSyntaxForm = (SyntaxForm)paramObject1;
      }
    }
  }
  
  protected Object expand(Object paramObject, int paramInt, Translator paramTranslator)
  {
    return expand(paramObject, paramInt, null, new IdentityHashMap(), paramTranslator);
  }
  
  protected boolean expandColonForms()
  {
    return true;
  }
  
  Object expand_pair(Pair paramPair, int paramInt, SyntaxForm paramSyntaxForm, Object paramObject, Translator paramTranslator)
  {
    Object localObject1 = paramPair;
    Object localObject4;
    if ((expandColonForms()) && (localObject1 == paramPair) && (paramTranslator.matches(((Pair)localObject1).getCar(), paramSyntaxForm, LispLanguage.lookup_sym)) && ((((Pair)localObject1).getCdr() instanceof Pair)))
    {
      localObject2 = (Pair)((Pair)localObject1).getCdr();
      if ((localObject2 instanceof Pair))
      {
        localObject3 = (Pair)((Pair)localObject2).getCdr();
        if (((localObject3 instanceof Pair)) && (((Pair)localObject3).getCdr() == LList.Empty))
        {
          paramObject = paramTranslator.rewrite_car((Pair)localObject2, false);
          localObject3 = paramTranslator.rewrite_car((Pair)localObject3, false);
          localObject4 = paramTranslator.namespaceResolvePrefix((Expression)paramObject);
          paramSyntaxForm = paramTranslator.namespaceResolve((Namespace)localObject4, (Expression)localObject3);
          if (paramSyntaxForm != null) {
            paramObject = localObject1;
          }
        }
      }
    }
    for (;;)
    {
      if (paramPair != paramObject) {
        break label1048;
      }
      return paramSyntaxForm;
      if ((localObject4 != null) && (paramInt == 1))
      {
        paramSyntaxForm = new ApplyExp(quoteType.getDeclaredMethod("makeSymbol", 2), new Expression[] { QuoteExp.getInstance(localObject4), localObject3 });
        paramObject = localObject1;
      }
      else if (((paramObject instanceof ReferenceExp)) && ((localObject3 instanceof QuoteExp)))
      {
        paramSyntaxForm = paramTranslator.getGlobalEnvironment().getSymbol(((ReferenceExp)paramObject).getName() + ':' + ((QuoteExp)localObject3).getValue().toString());
        paramObject = localObject1;
      }
      else
      {
        paramObject = CompileNamedPart.combineName((Expression)paramObject, (Expression)localObject3);
        if (paramObject != null)
        {
          paramSyntaxForm = paramTranslator.getGlobalEnvironment().getSymbol((String)paramObject);
          paramObject = localObject1;
        }
        else
        {
          paramObject = paramTranslator.pushPositionOf(localObject1);
          paramTranslator.error('e', "'" + ((Pair)localObject2).getCar() + "' is not a valid prefix");
          paramTranslator.popPositionOf(paramObject);
          paramObject = localObject1;
          continue;
          if (paramInt < 0) {}
          for (;;)
          {
            label366:
            if ((paramInt == 1) && ((((Pair)localObject1).getCar() instanceof Pair)))
            {
              localObject3 = ((Pair)localObject1).getCar();
              localObject2 = paramSyntaxForm;
              for (;;)
              {
                if ((localObject3 instanceof SyntaxForm))
                {
                  localObject2 = (SyntaxForm)localObject3;
                  localObject3 = ((SyntaxForm)localObject2).getDatum();
                  continue;
                  if (paramTranslator.matches(((Pair)localObject1).getCar(), paramSyntaxForm, "quasiquote"))
                  {
                    paramInt += 1;
                    break label366;
                  }
                  if (paramTranslator.matches(((Pair)localObject1).getCar(), paramSyntaxForm, "unquote"))
                  {
                    i = paramInt - 1;
                    if ((((Pair)localObject1).getCdr() instanceof Pair))
                    {
                      localObject2 = (Pair)((Pair)localObject1).getCdr();
                      if (((Pair)localObject2).getCdr() == LList.Empty) {}
                    }
                    else
                    {
                      return paramTranslator.syntaxError("invalid used of " + ((Pair)localObject1).getCar() + " in quasiquote template");
                    }
                    paramInt = i;
                    if (i != 0) {
                      break label366;
                    }
                    paramSyntaxForm = paramTranslator.rewrite_car((Pair)localObject2, paramSyntaxForm);
                    paramObject = localObject1;
                    break;
                  }
                  if (!paramTranslator.matches(((Pair)localObject1).getCar(), paramSyntaxForm, "unquote-splicing")) {
                    break label366;
                  }
                  return paramTranslator.syntaxError("invalid used of " + ((Pair)localObject1).getCar() + " in quasiquote template");
                }
              }
              int j = -1;
              i = j;
              label647:
              Vector localVector;
              if ((localObject3 instanceof Pair))
              {
                localObject4 = ((Pair)localObject3).getCar();
                if (paramTranslator.matches(localObject4, (SyntaxForm)localObject2, "unquote")) {
                  i = 0;
                }
              }
              else
              {
                if (i < 0) {
                  break label880;
                }
                localObject4 = ((Pair)localObject3).getCdr();
                localVector = new Vector();
                localObject3 = localObject2;
                localObject2 = localObject4;
                label679:
                localObject4 = localObject2;
                if ((localObject2 instanceof SyntaxForm))
                {
                  localObject3 = (SyntaxForm)localObject2;
                  localObject4 = ((SyntaxForm)localObject3).getDatum();
                }
                if (localObject4 != LList.Empty) {
                  break label826;
                }
                paramInt = localVector.size() + 1;
                paramObject = expand(((Pair)localObject1).getCdr(), 1, paramSyntaxForm, paramObject, paramTranslator);
                paramSyntaxForm = (SyntaxForm)paramObject;
                if (paramInt > 1)
                {
                  localObject2 = new Expression[paramInt];
                  localVector.copyInto((Object[])localObject2);
                  localObject2[(paramInt - 1)] = coerceExpression(paramObject, paramTranslator);
                  if (i != 0) {
                    break label873;
                  }
                }
              }
              label826:
              label873:
              for (paramSyntaxForm = consXMethod;; paramSyntaxForm = appendMethod)
              {
                paramSyntaxForm = new ApplyExp(paramSyntaxForm, (Expression[])localObject2);
                paramObject = localObject1;
                break;
                i = j;
                if (!paramTranslator.matches(localObject4, (SyntaxForm)localObject2, "unquote-splicing")) {
                  break label647;
                }
                i = 1;
                break label647;
                if ((localObject4 instanceof Pair))
                {
                  localVector.addElement(paramTranslator.rewrite_car((Pair)localObject4, (SyntaxForm)localObject3));
                  localObject2 = ((Pair)localObject4).getCdr();
                  break label679;
                }
                return paramTranslator.syntaxError("improper list argument to unquote");
              }
            }
          }
          label880:
          localObject2 = expand(((Pair)localObject1).getCar(), paramInt, paramSyntaxForm, paramObject, paramTranslator);
          if (localObject2 == ((Pair)localObject1).getCar())
          {
            localObject1 = ((Pair)localObject1).getCdr();
            if ((localObject1 instanceof Pair))
            {
              localObject1 = (Pair)localObject1;
              break;
            }
            paramSyntaxForm = expand(localObject1, paramInt, paramSyntaxForm, paramObject, paramTranslator);
            paramObject = localObject1;
            continue;
          }
          paramSyntaxForm = expand(((Pair)localObject1).getCdr(), paramInt, paramSyntaxForm, paramObject, paramTranslator);
          if (((localObject2 instanceof Expression)) || ((paramSyntaxForm instanceof Expression)))
          {
            paramObject = coerceExpression(localObject2, paramTranslator);
            paramSyntaxForm = coerceExpression(paramSyntaxForm, paramTranslator);
            paramSyntaxForm = new ApplyExp(makePairMethod, new Expression[] { paramObject, paramSyntaxForm });
            paramObject = localObject1;
          }
          else
          {
            paramSyntaxForm = Translator.makePair((Pair)localObject1, localObject2, paramSyntaxForm);
            paramObject = localObject1;
          }
        }
      }
    }
    label1048:
    Object localObject2 = paramPair;
    Object localObject3 = new Pair[20];
    paramInt = 0;
    localObject1 = localObject3;
    if (paramInt >= localObject3.length)
    {
      localObject1 = new Pair[paramInt * 2];
      System.arraycopy(localObject3, 0, localObject1, 0, paramInt);
    }
    int i = paramInt + 1;
    localObject1[paramInt] = localObject2;
    if (((Pair)localObject2).getCdr() == paramObject)
    {
      if (!(paramSyntaxForm instanceof Expression)) {
        break label1177;
      }
      paramObject = LList.Empty;
    }
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break label1183;
      }
      localObject2 = localObject1[i];
      paramObject = Translator.makePair((Pair)localObject2, ((Pair)localObject2).getCar(), paramObject);
      continue;
      localObject2 = (Pair)((Pair)localObject2).getCdr();
      paramInt = i;
      localObject3 = localObject1;
      break;
      label1177:
      paramObject = paramSyntaxForm;
    }
    label1183:
    if ((paramSyntaxForm instanceof Expression))
    {
      localObject1 = new Expression[2];
      localObject1[1] = ((Expression)paramSyntaxForm);
      if (i == 1)
      {
        localObject1[0] = leaf(paramPair.getCar(), paramTranslator);
        return new ApplyExp(makePairMethod, (Expression[])localObject1);
      }
      localObject1[0] = leaf(paramObject, paramTranslator);
      return new ApplyExp(appendMethod, (Expression[])localObject1);
    }
    return paramObject;
  }
  
  protected Expression leaf(Object paramObject, Translator paramTranslator)
  {
    return new QuoteExp(paramObject);
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator)
  {
    if ((paramObject instanceof Pair))
    {
      paramObject = (Pair)paramObject;
      if (((Pair)paramObject).getCdr() == LList.Empty) {}
    }
    else
    {
      return paramTranslator.syntaxError("wrong number of arguments to quote");
    }
    paramObject = ((Pair)paramObject).getCar();
    if (this.isQuasi) {}
    for (int i = 1;; i = -1) {
      return coerceExpression(expand(paramObject, i, paramTranslator), paramTranslator);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\Quote.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */