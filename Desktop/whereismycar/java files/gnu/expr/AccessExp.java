package gnu.expr;

import gnu.mapping.Symbol;

public abstract class AccessExp
  extends Expression
{
  Declaration binding;
  private Declaration context;
  Object symbol;
  
  public final Declaration contextDecl()
  {
    return this.context;
  }
  
  public final Declaration getBinding()
  {
    return this.binding;
  }
  
  public final String getName()
  {
    if ((this.symbol instanceof Symbol)) {
      return ((Symbol)this.symbol).getName();
    }
    return this.symbol.toString();
  }
  
  public final String getSimpleName()
  {
    if ((this.symbol instanceof String)) {
      return (String)this.symbol;
    }
    if ((this.symbol instanceof Symbol))
    {
      Symbol localSymbol = (Symbol)this.symbol;
      if (localSymbol.hasEmptyNamespace()) {
        return localSymbol.getLocalName();
      }
    }
    return null;
  }
  
  public final Object getSymbol()
  {
    return this.symbol;
  }
  
  public final void setBinding(Declaration paramDeclaration)
  {
    this.binding = paramDeclaration;
  }
  
  public final void setContextDecl(Declaration paramDeclaration)
  {
    this.context = paramDeclaration;
  }
  
  public String string_name()
  {
    return this.symbol.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\AccessExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */