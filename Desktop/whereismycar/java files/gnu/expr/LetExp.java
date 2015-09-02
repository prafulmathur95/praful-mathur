package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.OutPort;

public class LetExp
  extends ScopeExp
{
  public Expression body;
  public Expression[] inits;
  
  public LetExp(Expression[] paramArrayOfExpression)
  {
    this.inits = paramArrayOfExpression;
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    setIndexes();
    int j = ScopeExp.nesting(this);
    Object[] arrayOfObject = new Object[this.frameSize];
    Object localObject2 = paramCallContext.evalFrames;
    Object localObject1;
    if (localObject2 == null)
    {
      localObject1 = new Object[j + 10][];
      paramCallContext.evalFrames = ((Object[][])localObject1);
    }
    Object localObject5;
    for (;;)
    {
      localObject5 = localObject1[j];
      localObject1[j] = arrayOfObject;
      int i = 0;
      try
      {
        Object localObject3 = firstDecl();
        label63:
        if (localObject3 != null)
        {
          if (this.inits[i] == QuoteExp.undefined_exp) {}
          for (;;)
          {
            localObject3 = ((Declaration)localObject3).nextDecl();
            i += 1;
            break label63;
            localObject1 = localObject2;
            if (j < localObject2.length) {
              break;
            }
            localObject3 = new Object[j + 10][];
            System.arraycopy(localObject2, 0, localObject3, 0, localObject2.length);
            localObject1 = localObject3;
            paramCallContext.evalFrames = ((Object[][])localObject3);
            break;
            Object localObject4 = evalVariable(i, paramCallContext);
            Type localType = ((Declaration)localObject3).type;
            localObject2 = localObject4;
            if (localType != null)
            {
              localObject2 = localObject4;
              if (localType != Type.pointer_type) {
                localObject2 = localType.coerceFromObject(localObject4);
              }
            }
            localObject4 = localObject2;
            if (((Declaration)localObject3).isIndirectBinding())
            {
              localObject4 = ((Declaration)localObject3).makeIndirectLocationFor();
              ((Location)localObject4).set(localObject2);
            }
            arrayOfObject[i] = localObject4;
          }
        }
        this.body.apply(paramCallContext);
      }
      finally
      {
        localObject1[j] = localObject5;
      }
    }
    localObject1[j] = localObject5;
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Declaration localDeclaration = firstDecl();
    int i = 0;
    if (i < this.inits.length)
    {
      Expression localExpression = this.inits[i];
      boolean bool = localDeclaration.needsInit();
      if ((bool) && (localDeclaration.isSimple())) {
        localDeclaration.allocateVariable(localCodeAttr);
      }
      Object localObject2;
      Object localObject1;
      if ((!bool) || ((localDeclaration.isIndirectBinding()) && (localExpression == QuoteExp.undefined_exp)))
      {
        localObject2 = Target.Ignore;
        localObject1 = localExpression;
      }
      for (;;)
      {
        ((Expression)localObject1).compileWithPosition(paramCompilation, (Target)localObject2);
        i += 1;
        localDeclaration = localDeclaration.nextDecl();
        break;
        Type localType = localDeclaration.getType();
        Target localTarget = CheckedTarget.getInstance(localDeclaration);
        localObject1 = localExpression;
        localObject2 = localTarget;
        if (localExpression == QuoteExp.undefined_exp) {
          if ((localType instanceof PrimType))
          {
            localObject1 = new QuoteExp(new Byte((byte)0));
            localObject2 = localTarget;
          }
          else
          {
            localObject1 = localExpression;
            localObject2 = localTarget;
            if (localType != null)
            {
              localObject1 = localExpression;
              localObject2 = localTarget;
              if (localType != Type.pointer_type)
              {
                localObject1 = QuoteExp.nullExp;
                localObject2 = localTarget;
              }
            }
          }
        }
      }
    }
    localCodeAttr.enterScope(getVarScope());
    store_rest(paramCompilation, 0, firstDecl());
    this.body.compileWithPosition(paramCompilation, paramTarget);
    popScope(localCodeAttr);
  }
  
  protected Object evalVariable(int paramInt, CallContext paramCallContext)
    throws Throwable
  {
    return this.inits[paramInt].eval(paramCallContext);
  }
  
  public Expression getBody()
  {
    return this.body;
  }
  
  public final Type getType()
  {
    return this.body.getType();
  }
  
  protected boolean mustCompile()
  {
    return false;
  }
  
  public void print(OutPort paramOutPort)
  {
    print(paramOutPort, "(Let", ")");
  }
  
  public void print(OutPort paramOutPort, String paramString1, String paramString2)
  {
    paramOutPort.startLogicalBlock(paramString1 + "#" + this.id, paramString2, 2);
    paramOutPort.writeSpaceFill();
    printLineColumn(paramOutPort);
    paramOutPort.startLogicalBlock("(", false, ")");
    paramString1 = firstDecl();
    int i = 0;
    if (paramString1 != null)
    {
      if (i > 0) {
        paramOutPort.writeSpaceFill();
      }
      paramOutPort.startLogicalBlock("(", false, ")");
      paramString1.printInfo(paramOutPort);
      int j = i;
      if (this.inits != null)
      {
        paramOutPort.writeSpaceFill();
        paramOutPort.print('=');
        paramOutPort.writeSpaceFill();
        if (i < this.inits.length) {
          break label150;
        }
        paramOutPort.print("<missing init>");
      }
      for (;;)
      {
        j = i + 1;
        paramOutPort.endLogicalBlock(")");
        paramString1 = paramString1.nextDecl();
        i = j;
        break;
        label150:
        if (this.inits[i] == null) {
          paramOutPort.print("<null>");
        } else {
          this.inits[i].print(paramOutPort);
        }
      }
    }
    paramOutPort.endLogicalBlock(")");
    paramOutPort.writeSpaceLinear();
    if (this.body == null) {
      paramOutPort.print("<null body>");
    }
    for (;;)
    {
      paramOutPort.endLogicalBlock(paramString2);
      return;
      this.body.print(paramOutPort);
    }
  }
  
  public void setBody(Expression paramExpression)
  {
    this.body = paramExpression;
  }
  
  void store_rest(Compilation paramCompilation, int paramInt, Declaration paramDeclaration)
  {
    if (paramDeclaration != null)
    {
      store_rest(paramCompilation, paramInt + 1, paramDeclaration.nextDecl());
      if (paramDeclaration.needsInit()) {
        if (paramDeclaration.isIndirectBinding())
        {
          CodeAttr localCodeAttr = paramCompilation.getCode();
          if (this.inits[paramInt] != QuoteExp.undefined_exp) {
            break label79;
          }
          Object localObject = paramDeclaration.getSymbol();
          paramCompilation.compileConstant(localObject, Target.pushObject);
          localCodeAttr.emitInvokeStatic(BindingInitializer.makeLocationMethod(localObject));
        }
      }
    }
    for (;;)
    {
      paramDeclaration.compileStore(paramCompilation);
      return;
      label79:
      paramDeclaration.pushIndirectBinding(paramCompilation);
    }
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitLetExp(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    visitInitializers(paramExpVisitor, paramD);
    if (paramExpVisitor.exitValue == null) {
      this.body = paramExpVisitor.visitAndUpdate(this.body, paramD);
    }
  }
  
  public <R, D> void visitInitializers(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    Declaration localDeclaration = firstDecl();
    int i = 0;
    while (i < this.inits.length)
    {
      Expression localExpression1 = this.inits[i];
      if (localExpression1 == null) {
        throw new Error("null1 init for " + this + " i:" + i + " d:" + localDeclaration);
      }
      Expression localExpression2 = paramExpVisitor.visitAndUpdate(localExpression1, paramD);
      if (localExpression2 == null) {
        throw new Error("null2 init for " + this + " was:" + localExpression1);
      }
      this.inits[i] = localExpression2;
      if (localDeclaration.value == localExpression1) {
        localDeclaration.value = localExpression2;
      }
      i += 1;
      localDeclaration = localDeclaration.nextDecl();
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\LetExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */