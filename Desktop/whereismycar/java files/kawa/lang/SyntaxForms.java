package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.NameLookup;
import gnu.lists.ImmutablePair;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;

public class SyntaxForms
{
  public static final boolean DEBUGGING = true;
  
  public static boolean freeIdentifierEquals(SyntaxForm paramSyntaxForm1, SyntaxForm paramSyntaxForm2)
  {
    Translator localTranslator = (Translator)Compilation.getCurrent();
    return localTranslator.lexical.lookup(paramSyntaxForm1.getDatum(), -1) == localTranslator.lexical.lookup(paramSyntaxForm2.getDatum(), -1);
  }
  
  public static Object fromDatum(Object paramObject, SyntaxForm paramSyntaxForm)
  {
    return makeForm(paramObject, paramSyntaxForm.getScope());
  }
  
  public static Object fromDatumIfNeeded(Object paramObject, SyntaxForm paramSyntaxForm)
  {
    if (paramObject == paramSyntaxForm.getDatum()) {
      return paramSyntaxForm;
    }
    if ((paramObject instanceof SyntaxForm)) {
      return (SyntaxForm)paramObject;
    }
    return fromDatum(paramObject, paramSyntaxForm);
  }
  
  public static boolean isIdentifier(SyntaxForm paramSyntaxForm)
  {
    return paramSyntaxForm.getDatum() instanceof Symbol;
  }
  
  public static Object makeForm(Object paramObject, TemplateScope paramTemplateScope)
  {
    Object localObject;
    if ((paramObject instanceof Pair)) {
      localObject = new PairSyntaxForm((Pair)paramObject, paramTemplateScope);
    }
    do
    {
      return localObject;
      localObject = paramObject;
    } while (paramObject == LList.Empty);
    return new SimpleSyntaxForm(paramObject, paramTemplateScope);
  }
  
  public static Object makeWithTemplate(Object paramObject1, Object paramObject2)
  {
    Object localObject;
    if ((paramObject2 instanceof SyntaxForm)) {
      localObject = (SyntaxForm)paramObject2;
    }
    do
    {
      return localObject;
      localObject = paramObject2;
    } while (!(paramObject1 instanceof SyntaxForm));
    paramObject1 = (SyntaxForm)paramObject1;
    if (paramObject2 == ((SyntaxForm)paramObject1).getDatum()) {
      return paramObject1;
    }
    return fromDatum(paramObject2, (SyntaxForm)paramObject1);
  }
  
  public static Expression rewrite(Object paramObject)
  {
    return ((Translator)Compilation.getCurrent()).rewrite(paramObject);
  }
  
  public static Expression rewriteBody(Object paramObject)
  {
    return ((Translator)Compilation.getCurrent()).rewrite_body(paramObject);
  }
  
  public static String toString(SyntaxForm paramSyntaxForm, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder("#<syntax");
    if (paramString != null)
    {
      localStringBuilder.append('#');
      localStringBuilder.append(paramString);
    }
    localStringBuilder.append(' ');
    localStringBuilder.append(paramSyntaxForm.getDatum());
    paramSyntaxForm = paramSyntaxForm.getScope();
    if (paramSyntaxForm == null) {
      localStringBuilder.append(" in null");
    }
    for (;;)
    {
      localStringBuilder.append(">");
      return localStringBuilder.toString();
      localStringBuilder.append(" in #");
      localStringBuilder.append(paramSyntaxForm.id);
    }
  }
  
  static class PairSyntaxForm
    extends ImmutablePair
    implements SyntaxForm
  {
    private Pair datum;
    private TemplateScope scope;
    
    public PairSyntaxForm(Pair paramPair, TemplateScope paramTemplateScope)
    {
      this.datum = paramPair;
      this.scope = paramTemplateScope;
    }
    
    public Object getCar()
    {
      if (this.car == null) {
        this.car = SyntaxForms.makeForm(this.datum.getCar(), this.scope);
      }
      return this.car;
    }
    
    public Object getCdr()
    {
      if (this.cdr == null) {
        this.cdr = SyntaxForms.makeForm(this.datum.getCdr(), this.scope);
      }
      return this.cdr;
    }
    
    public Object getDatum()
    {
      return this.datum;
    }
    
    public TemplateScope getScope()
    {
      return this.scope;
    }
    
    public String toString()
    {
      return SyntaxForms.toString(this, null);
    }
  }
  
  static class SimpleSyntaxForm
    implements SyntaxForm
  {
    static int counter;
    private Object datum;
    int id;
    private TemplateScope scope;
    
    SimpleSyntaxForm(Object paramObject, TemplateScope paramTemplateScope)
    {
      int i = counter + 1;
      counter = i;
      this.id = i;
      this.datum = paramObject;
      this.scope = paramTemplateScope;
    }
    
    public Object getDatum()
    {
      return this.datum;
    }
    
    public TemplateScope getScope()
    {
      return this.scope;
    }
    
    public String toString()
    {
      return SyntaxForms.toString(this, Integer.toString(this.id));
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\SyntaxForms.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */