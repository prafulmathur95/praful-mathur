package gnu.bytecode;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class VarEnumerator
  implements Enumeration
{
  Scope currentScope;
  Variable next;
  Scope topScope;
  
  public VarEnumerator(Scope paramScope)
  {
    this.topScope = paramScope;
    reset();
  }
  
  private void fixup()
  {
    if (this.next == null) {
      if (this.currentScope.firstChild == null) {}
    }
    for (this.currentScope = this.currentScope.firstChild;; this.currentScope = this.currentScope.nextSibling)
    {
      this.next = this.currentScope.firstVar();
      break;
      do
      {
        this.currentScope = this.currentScope.parent;
        if (this.currentScope.nextSibling != null) {
          break;
        }
      } while (this.currentScope != this.topScope);
      return;
    }
  }
  
  public final boolean hasMoreElements()
  {
    return this.next != null;
  }
  
  public Object nextElement()
  {
    Variable localVariable = nextVar();
    if (localVariable == null) {
      throw new NoSuchElementException("VarEnumerator");
    }
    return localVariable;
  }
  
  public final Variable nextVar()
  {
    Variable localVariable = this.next;
    if (localVariable != null)
    {
      this.next = localVariable.nextVar();
      if (this.next == null) {
        fixup();
      }
    }
    return localVariable;
  }
  
  public final void reset()
  {
    this.currentScope = this.topScope;
    if (this.topScope != null)
    {
      this.next = this.currentScope.firstVar();
      if (this.next == null) {
        fixup();
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\VarEnumerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */