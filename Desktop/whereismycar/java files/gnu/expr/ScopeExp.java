package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;

public abstract class ScopeExp
  extends Expression
{
  static int counter;
  Declaration decls;
  protected int frameSize;
  public int id;
  Declaration last;
  public ScopeExp outer;
  private Scope scope;
  
  public ScopeExp()
  {
    int i = counter + 1;
    counter = i;
    this.id = i;
  }
  
  public static void duplicateDeclarationError(Declaration paramDeclaration1, Declaration paramDeclaration2, Compilation paramCompilation)
  {
    paramCompilation.error('e', paramDeclaration2, "duplicate declaration of '", "'");
    paramCompilation.error('e', paramDeclaration1, "(this is the previous declaration of '", "')");
  }
  
  public static int nesting(ScopeExp paramScopeExp)
  {
    int i = 0;
    while (paramScopeExp != null)
    {
      paramScopeExp = paramScopeExp.outer;
      i += 1;
    }
    return i;
  }
  
  public void add(Declaration paramDeclaration)
  {
    if (this.last == null) {
      this.decls = paramDeclaration;
    }
    for (;;)
    {
      this.last = paramDeclaration;
      paramDeclaration.context = this;
      return;
      this.last.next = paramDeclaration;
    }
  }
  
  public void add(Declaration paramDeclaration1, Declaration paramDeclaration2)
  {
    if (paramDeclaration1 == null)
    {
      paramDeclaration2.next = this.decls;
      this.decls = paramDeclaration2;
    }
    for (;;)
    {
      if (this.last == paramDeclaration1) {
        this.last = paramDeclaration2;
      }
      paramDeclaration2.context = this;
      return;
      paramDeclaration2.next = paramDeclaration1.next;
      paramDeclaration1.next = paramDeclaration2;
    }
  }
  
  public final Declaration addDeclaration(Object paramObject)
  {
    paramObject = new Declaration(paramObject);
    add((Declaration)paramObject);
    return (Declaration)paramObject;
  }
  
  public final Declaration addDeclaration(Object paramObject, Type paramType)
  {
    paramObject = new Declaration(paramObject, paramType);
    add((Declaration)paramObject);
    return (Declaration)paramObject;
  }
  
  public final void addDeclaration(Declaration paramDeclaration)
  {
    add(paramDeclaration);
  }
  
  public int countDecls()
  {
    int i = 0;
    for (Declaration localDeclaration = firstDecl(); localDeclaration != null; localDeclaration = localDeclaration.nextDecl()) {
      i += 1;
    }
    return i;
  }
  
  public int countNonDynamicDecls()
  {
    int i = 0;
    Declaration localDeclaration = firstDecl();
    while (localDeclaration != null)
    {
      int j = i;
      if (!localDeclaration.getFlag(268435456L)) {
        j = i + 1;
      }
      localDeclaration = localDeclaration.nextDecl();
      i = j;
    }
    return i;
  }
  
  public LambdaExp currentLambda()
  {
    for (ScopeExp localScopeExp = this;; localScopeExp = localScopeExp.outer)
    {
      if (localScopeExp == null) {
        return null;
      }
      if ((localScopeExp instanceof LambdaExp)) {
        return (LambdaExp)localScopeExp;
      }
    }
  }
  
  public ModuleExp currentModule()
  {
    for (ScopeExp localScopeExp = this;; localScopeExp = localScopeExp.outer)
    {
      if (localScopeExp == null) {
        return null;
      }
      if ((localScopeExp instanceof ModuleExp)) {
        return (ModuleExp)localScopeExp;
      }
    }
  }
  
  public Declaration firstDecl()
  {
    return this.decls;
  }
  
  public Declaration getDefine(Object paramObject, char paramChar, Compilation paramCompilation)
  {
    Declaration localDeclaration = lookup(paramObject);
    if (localDeclaration == null) {
      return addDeclaration(paramObject);
    }
    if ((localDeclaration.flags & 0x10200) != 0L)
    {
      localDeclaration.flags &= 0xFFFFFFFFFFFEFDFF;
      return localDeclaration;
    }
    paramObject = addDeclaration(paramObject);
    duplicateDeclarationError(localDeclaration, (Declaration)paramObject, paramCompilation);
    return (Declaration)paramObject;
  }
  
  public Declaration getNoDefine(Object paramObject)
  {
    Declaration localDeclaration2 = lookup(paramObject);
    Declaration localDeclaration1 = localDeclaration2;
    if (localDeclaration2 == null)
    {
      localDeclaration1 = addDeclaration(paramObject);
      localDeclaration1.flags |= 0x10200;
    }
    return localDeclaration1;
  }
  
  public Scope getVarScope()
  {
    Scope localScope2 = this.scope;
    Scope localScope1 = localScope2;
    if (localScope2 == null)
    {
      localScope1 = new Scope();
      this.scope = localScope1;
    }
    return localScope1;
  }
  
  public Declaration lookup(Object paramObject)
  {
    if (paramObject != null) {
      for (Declaration localDeclaration = firstDecl(); localDeclaration != null; localDeclaration = localDeclaration.nextDecl()) {
        if (paramObject.equals(localDeclaration.symbol)) {
          return localDeclaration;
        }
      }
    }
    return null;
  }
  
  public Declaration lookup(Object paramObject, Language paramLanguage, int paramInt)
  {
    for (Declaration localDeclaration = firstDecl(); localDeclaration != null; localDeclaration = localDeclaration.nextDecl()) {
      if ((paramObject.equals(localDeclaration.symbol)) && (paramLanguage.hasNamespace(localDeclaration, paramInt))) {
        return localDeclaration;
      }
    }
    return null;
  }
  
  public boolean nestedIn(ScopeExp paramScopeExp)
  {
    for (ScopeExp localScopeExp = this; localScopeExp != null; localScopeExp = localScopeExp.outer) {
      if (localScopeExp == paramScopeExp) {
        return true;
      }
    }
    return false;
  }
  
  public void popScope(CodeAttr paramCodeAttr)
  {
    for (Declaration localDeclaration = firstDecl(); localDeclaration != null; localDeclaration = localDeclaration.nextDecl()) {
      localDeclaration.var = null;
    }
    paramCodeAttr.popScope();
    this.scope = null;
  }
  
  public void remove(Declaration paramDeclaration)
  {
    Object localObject = null;
    for (Declaration localDeclaration = firstDecl();; localDeclaration = localDeclaration.nextDecl())
    {
      if (localDeclaration != null)
      {
        if (localDeclaration == paramDeclaration) {
          remove((Declaration)localObject, paramDeclaration);
        }
      }
      else {
        return;
      }
      localObject = localDeclaration;
    }
  }
  
  public void remove(Declaration paramDeclaration1, Declaration paramDeclaration2)
  {
    if (paramDeclaration1 == null) {
      this.decls = paramDeclaration2.next;
    }
    for (;;)
    {
      if (this.last == paramDeclaration2) {
        this.last = paramDeclaration1;
      }
      return;
      paramDeclaration1.next = paramDeclaration2.next;
    }
  }
  
  public void replaceFollowing(Declaration paramDeclaration1, Declaration paramDeclaration2)
  {
    if (paramDeclaration1 == null)
    {
      paramDeclaration1 = this.decls;
      this.decls = paramDeclaration2;
    }
    for (;;)
    {
      paramDeclaration2.next = paramDeclaration1.next;
      if (this.last == paramDeclaration1) {
        this.last = paramDeclaration2;
      }
      paramDeclaration1.next = null;
      paramDeclaration2.context = this;
      return;
      Declaration localDeclaration = paramDeclaration1.next;
      paramDeclaration1.next = paramDeclaration2;
      paramDeclaration1 = localDeclaration;
    }
  }
  
  protected void setIndexes()
  {
    Declaration localDeclaration = firstDecl();
    int i = 0;
    while (localDeclaration != null)
    {
      localDeclaration.evalIndex = i;
      localDeclaration = localDeclaration.nextDecl();
      i += 1;
    }
    this.frameSize = i;
  }
  
  public String toString()
  {
    return getClass().getName() + "#" + this.id;
  }
  
  public ScopeExp topLevel()
  {
    ScopeExp localScopeExp;
    for (Object localObject = this;; localObject = localScopeExp)
    {
      localScopeExp = ((ScopeExp)localObject).outer;
      if ((localScopeExp == null) || ((localScopeExp instanceof ModuleExp))) {
        return (ScopeExp)localObject;
      }
    }
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitScopeExp(this, paramD);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ScopeExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */