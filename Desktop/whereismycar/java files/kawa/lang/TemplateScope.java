package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.LetExp;
import gnu.expr.ScopeExp;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TemplateScope
  extends LetExp
  implements Externalizable
{
  Declaration macroContext;
  private Syntax syntax;
  
  public TemplateScope()
  {
    super(null);
  }
  
  public TemplateScope(ScopeExp paramScopeExp)
  {
    super(null);
    this.outer = paramScopeExp;
  }
  
  public static TemplateScope make()
  {
    return make((Translator)Compilation.getCurrent());
  }
  
  public static TemplateScope make(Translator paramTranslator)
  {
    TemplateScope localTemplateScope = new TemplateScope();
    Syntax localSyntax = paramTranslator.getCurrentSyntax();
    if ((localSyntax instanceof Macro))
    {
      localTemplateScope.outer = ((Macro)localSyntax).getCapturedScope();
      localTemplateScope.macroContext = paramTranslator.macroContext;
    }
    localTemplateScope.syntax = localSyntax;
    return localTemplateScope;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.outer = ((ScopeExp)paramObjectInput.readObject());
  }
  
  public String toString()
  {
    return super.toString() + "(for " + this.syntax + ")";
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.outer);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\TemplateScope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */