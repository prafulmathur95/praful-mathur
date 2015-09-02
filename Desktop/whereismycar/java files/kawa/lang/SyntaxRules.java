package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.ErrorExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure1;
import gnu.text.Printable;
import gnu.text.ReportFormat;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class SyntaxRules
  extends Procedure1
  implements Printable, Externalizable
{
  Object[] literal_identifiers;
  int maxVars = 0;
  SyntaxRule[] rules;
  
  public SyntaxRules() {}
  
  public SyntaxRules(Object[] paramArrayOfObject, Object paramObject, Translator paramTranslator)
  {
    this.literal_identifiers = paramArrayOfObject;
    int j = Translator.listLength(paramObject);
    int i = j;
    if (j < 0)
    {
      i = 0;
      paramTranslator.syntaxError("missing or invalid syntax-rules");
    }
    this.rules = new SyntaxRule[i];
    SyntaxForm localSyntaxForm = null;
    j = 0;
    int k;
    while (j < i)
    {
      while ((paramObject instanceof SyntaxForm))
      {
        localSyntaxForm = (SyntaxForm)paramObject;
        paramObject = localSyntaxForm.getDatum();
      }
      Pair localPair = (Pair)paramObject;
      paramObject = localSyntaxForm;
      for (Object localObject2 = localPair.getCar(); (localObject2 instanceof SyntaxForm); localObject2 = ((SyntaxForm)paramObject).getDatum()) {
        paramObject = (SyntaxForm)localObject2;
      }
      if (!(localObject2 instanceof Pair))
      {
        paramTranslator.syntaxError("missing pattern in " + j + "'th syntax rule");
        return;
      }
      Object localObject1 = paramObject;
      localObject2 = (Pair)localObject2;
      Object localObject3 = ((Pair)localObject2).getCar();
      String str = paramTranslator.getFileName();
      k = paramTranslator.getLineNumber();
      int m = paramTranslator.getColumnNumber();
      try
      {
        paramTranslator.setLine(localObject2);
        for (localObject2 = ((Pair)localObject2).getCdr(); (localObject2 instanceof SyntaxForm); localObject2 = ((SyntaxForm)paramObject).getDatum()) {
          paramObject = (SyntaxForm)localObject2;
        }
        if (!(localObject2 instanceof Pair))
        {
          paramTranslator.syntaxError("missing template in " + j + "'th syntax rule");
          return;
        }
        localObject2 = (Pair)localObject2;
        if (((Pair)localObject2).getCdr() != LList.Empty)
        {
          paramTranslator.syntaxError("junk after " + j + "'th syntax rule");
          return;
        }
        Object localObject4 = ((Pair)localObject2).getCar();
        paramTranslator.push(PatternScope.push(paramTranslator));
        for (localObject2 = localObject3; (localObject2 instanceof SyntaxForm); localObject2 = ((SyntaxForm)localObject1).getDatum()) {
          localObject1 = (SyntaxForm)localObject2;
        }
        localObject3 = new StringBuffer();
        if ((localObject2 instanceof Pair))
        {
          paramArrayOfObject[0] = ((Pair)localObject2).getCar();
          localObject2 = (Pair)localObject2;
          ((StringBuffer)localObject3).append('\f');
          ((StringBuffer)localObject3).append('\030');
          localObject1 = new SyntaxPattern((StringBuffer)localObject3, ((Pair)localObject2).getCdr(), (SyntaxForm)localObject1, paramArrayOfObject, paramTranslator);
          this.rules[j] = new SyntaxRule((SyntaxPattern)localObject1, localObject4, (SyntaxForm)paramObject, paramTranslator);
          PatternScope.pop(paramTranslator);
          paramTranslator.pop();
          paramTranslator.setLine(str, k, m);
          j += 1;
          paramObject = localPair.getCdr();
        }
        else
        {
          paramTranslator.syntaxError("pattern does not start with name");
          return;
        }
      }
      finally
      {
        paramTranslator.setLine(str, k, m);
      }
    }
    i = this.rules.length;
    for (;;)
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      k = this.rules[j].patternNesting.length();
      i = j;
      if (k > this.maxVars)
      {
        this.maxVars = k;
        i = j;
      }
    }
  }
  
  public SyntaxRules(Object[] paramArrayOfObject, SyntaxRule[] paramArrayOfSyntaxRule, int paramInt)
  {
    this.literal_identifiers = paramArrayOfObject;
    this.rules = paramArrayOfSyntaxRule;
    this.maxVars = paramInt;
  }
  
  public Object apply1(Object paramObject)
  {
    if ((paramObject instanceof SyntaxForm))
    {
      Object localObject1 = (SyntaxForm)paramObject;
      paramObject = (Translator)Compilation.getCurrent();
      ScopeExp localScopeExp = ((Translator)paramObject).currentScope();
      ((Translator)paramObject).setCurrentScope(((SyntaxForm)localObject1).getScope());
      try
      {
        localObject1 = expand(localObject1, (Translator)paramObject);
        return localObject1;
      }
      finally
      {
        ((Translator)paramObject).setCurrentScope(localScopeExp);
      }
    }
    return expand(paramObject, (Translator)Compilation.getCurrent());
  }
  
  public Object expand(Object paramObject, Translator paramTranslator)
  {
    Object[] arrayOfObject = new Object[this.maxVars];
    Macro localMacro = (Macro)paramTranslator.getCurrentSyntax();
    int i = 0;
    while (i < this.rules.length)
    {
      SyntaxRule localSyntaxRule = this.rules[i];
      if (localSyntaxRule == null) {
        return new ErrorExp("error defining " + localMacro);
      }
      if (localSyntaxRule.pattern.match(paramObject, arrayOfObject, 0)) {
        return localSyntaxRule.execute(arrayOfObject, paramTranslator, TemplateScope.make(paramTranslator));
      }
      i += 1;
    }
    return paramTranslator.syntaxError("no matching syntax-rule for " + this.literal_identifiers[0]);
  }
  
  public void print(Consumer paramConsumer)
  {
    paramConsumer.write("#<macro ");
    ReportFormat.print(this.literal_identifiers[0], paramConsumer);
    paramConsumer.write(62);
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.literal_identifiers = ((Object[])paramObjectInput.readObject());
    this.rules = ((SyntaxRule[])paramObjectInput.readObject());
    this.maxVars = paramObjectInput.readInt();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.literal_identifiers);
    paramObjectOutput.writeObject(this.rules);
    paramObjectOutput.writeInt(this.maxVars);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\SyntaxRules.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */