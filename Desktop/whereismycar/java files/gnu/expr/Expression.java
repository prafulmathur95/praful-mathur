package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.kawa.reflect.OccurrenceType;
import gnu.kawa.util.IdentityHashTable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0;
import gnu.text.Printable;
import gnu.text.SourceLocator;
import java.io.PrintWriter;

public abstract class Expression
  extends Procedure0
  implements Printable, SourceLocator
{
  protected static final int NEXT_AVAIL_FLAG = 2;
  public static final int VALIDATED = 1;
  public static final Expression[] noExpressions = new Expression[0];
  String filename;
  protected int flags;
  int position;
  
  public static void compileButFirst(Expression paramExpression, Compilation paramCompilation)
  {
    int j;
    if ((paramExpression instanceof BeginExp))
    {
      paramExpression = (BeginExp)paramExpression;
      j = paramExpression.length;
      if (j != 0) {
        break label22;
      }
    }
    for (;;)
    {
      return;
      label22:
      paramExpression = paramExpression.exps;
      compileButFirst(paramExpression[0], paramCompilation);
      int i = 1;
      while (i < j)
      {
        paramExpression[i].compileWithPosition(paramCompilation, Target.Ignore);
        i += 1;
      }
    }
  }
  
  protected static Expression deepCopy(Expression paramExpression)
  {
    return deepCopy(paramExpression, new IdentityHashTable());
  }
  
  public static Expression deepCopy(Expression paramExpression, IdentityHashTable paramIdentityHashTable)
  {
    if (paramExpression == null) {
      return null;
    }
    Object localObject = paramIdentityHashTable.get(paramExpression);
    if (localObject != null) {
      return (Expression)localObject;
    }
    localObject = paramExpression.deepCopy(paramIdentityHashTable);
    paramIdentityHashTable.put(paramExpression, localObject);
    return (Expression)localObject;
  }
  
  public static Expression[] deepCopy(Expression[] paramArrayOfExpression, IdentityHashTable paramIdentityHashTable)
  {
    Object localObject;
    if (paramArrayOfExpression == null)
    {
      localObject = null;
      return (Expression[])localObject;
    }
    int j = paramArrayOfExpression.length;
    Expression[] arrayOfExpression = new Expression[j];
    int i = 0;
    for (;;)
    {
      localObject = arrayOfExpression;
      if (i >= j) {
        break;
      }
      localObject = paramArrayOfExpression[i];
      Expression localExpression = deepCopy((Expression)localObject, paramIdentityHashTable);
      if ((localExpression == null) && (localObject != null)) {
        return null;
      }
      arrayOfExpression[i] = localExpression;
      i += 1;
    }
  }
  
  public static Expression makeWhile(Object paramObject1, Object paramObject2, Compilation paramCompilation)
  {
    Expression[] arrayOfExpression = new Expression[1];
    LetExp localLetExp = new LetExp(arrayOfExpression);
    Declaration localDeclaration = localLetExp.addDeclaration("%do%loop");
    ApplyExp localApplyExp = new ApplyExp(new ReferenceExp(localDeclaration), noExpressions);
    LambdaExp localLambdaExp = new LambdaExp();
    paramCompilation.push(localLambdaExp);
    localLambdaExp.body = new IfExp(paramCompilation.parse(paramObject1), new BeginExp(paramCompilation.parse(paramObject2), localApplyExp), QuoteExp.voidExp);
    localLambdaExp.setName("%do%loop");
    paramCompilation.pop(localLambdaExp);
    arrayOfExpression[0] = localLambdaExp;
    localDeclaration.noteValue(localLambdaExp);
    localLetExp.setBody(new ApplyExp(new ReferenceExp(localDeclaration), noExpressions));
    return localLetExp;
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    throw new RuntimeException("internal error - " + getClass() + ".eval called");
  }
  
  public final Object apply0()
    throws Throwable
  {
    CallContext localCallContext = CallContext.getInstance();
    check0(localCallContext);
    return localCallContext.runUntilValue();
  }
  
  public final void compile(Compilation paramCompilation, Type paramType)
  {
    compile(paramCompilation, StackTarget.getInstance(paramType));
  }
  
  public final void compile(Compilation paramCompilation, Declaration paramDeclaration)
  {
    compile(paramCompilation, CheckedTarget.getInstance(paramDeclaration));
  }
  
  public abstract void compile(Compilation paramCompilation, Target paramTarget);
  
  public final void compileNotePosition(Compilation paramCompilation, Target paramTarget, Expression paramExpression)
  {
    String str = paramCompilation.getFileName();
    int i = paramCompilation.getLineNumber();
    int j = paramCompilation.getColumnNumber();
    paramCompilation.setLine(paramExpression);
    compile(paramCompilation, paramTarget);
    paramCompilation.setLine(str, i, j);
  }
  
  public final void compileWithPosition(Compilation paramCompilation, Target paramTarget)
  {
    int i = getLineNumber();
    if (i > 0)
    {
      paramCompilation.getCode().putLineNumber(getFileName(), i);
      compileNotePosition(paramCompilation, paramTarget, this);
      return;
    }
    compile(paramCompilation, paramTarget);
  }
  
  public final void compileWithPosition(Compilation paramCompilation, Target paramTarget, Expression paramExpression)
  {
    int i = paramExpression.getLineNumber();
    if (i > 0)
    {
      paramCompilation.getCode().putLineNumber(paramExpression.getFileName(), i);
      compileNotePosition(paramCompilation, paramTarget, paramExpression);
      return;
    }
    compile(paramCompilation, paramTarget);
  }
  
  protected Expression deepCopy(IdentityHashTable paramIdentityHashTable)
  {
    return null;
  }
  
  public final Object eval(CallContext paramCallContext)
    throws Throwable
  {
    int i = paramCallContext.startFromContext();
    try
    {
      match0(paramCallContext);
      Object localObject = paramCallContext.getFromContext(i);
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      paramCallContext.cleanupFromContext(i);
      throw localThrowable;
    }
  }
  
  public final Object eval(Environment paramEnvironment)
    throws Throwable
  {
    Object localObject1 = CallContext.getInstance();
    paramEnvironment = Environment.setSaveCurrent(paramEnvironment);
    try
    {
      localObject1 = eval((CallContext)localObject1);
      return localObject1;
    }
    finally
    {
      Environment.restoreCurrent(paramEnvironment);
    }
  }
  
  public final int getColumnNumber()
  {
    int j = this.position & 0xFFF;
    int i = j;
    if (j == 0) {
      i = -1;
    }
    return i;
  }
  
  public final String getFileName()
  {
    return this.filename;
  }
  
  public boolean getFlag(int paramInt)
  {
    return (this.flags & paramInt) != 0;
  }
  
  public int getFlags()
  {
    return this.flags;
  }
  
  public final int getLineNumber()
  {
    int j = this.position >> 12;
    int i = j;
    if (j == 0) {
      i = -1;
    }
    return i;
  }
  
  public String getPublicId()
  {
    return null;
  }
  
  public String getSystemId()
  {
    return this.filename;
  }
  
  public Type getType()
  {
    return Type.pointer_type;
  }
  
  public boolean isSingleValue()
  {
    return OccurrenceType.itemCountIsOne(getType());
  }
  
  public boolean isStableSourceLocation()
  {
    return true;
  }
  
  public final int match0(CallContext paramCallContext)
  {
    paramCallContext.proc = this;
    paramCallContext.pc = 0;
    return 0;
  }
  
  protected abstract boolean mustCompile();
  
  public final void print(Consumer paramConsumer)
  {
    if ((paramConsumer instanceof OutPort))
    {
      print((OutPort)paramConsumer);
      return;
    }
    if ((paramConsumer instanceof PrintWriter))
    {
      paramConsumer = new OutPort((PrintWriter)paramConsumer);
      print(paramConsumer);
      paramConsumer.close();
      return;
    }
    CharArrayOutPort localCharArrayOutPort = new CharArrayOutPort();
    print(localCharArrayOutPort);
    localCharArrayOutPort.close();
    localCharArrayOutPort.writeTo(paramConsumer);
  }
  
  public abstract void print(OutPort paramOutPort);
  
  public void printLineColumn(OutPort paramOutPort)
  {
    int i = getLineNumber();
    if (i > 0)
    {
      paramOutPort.print("line:");
      paramOutPort.print(i);
      i = getColumnNumber();
      if (i > 0)
      {
        paramOutPort.print(':');
        paramOutPort.print(i);
      }
      paramOutPort.writeSpaceFill();
    }
  }
  
  public final void setFile(String paramString)
  {
    this.filename = paramString;
  }
  
  public void setFlag(int paramInt)
  {
    this.flags |= paramInt;
  }
  
  public void setFlag(boolean paramBoolean, int paramInt)
  {
    if (paramBoolean)
    {
      this.flags |= paramInt;
      return;
    }
    this.flags &= (paramInt ^ 0xFFFFFFFF);
  }
  
  public final Expression setLine(Expression paramExpression)
  {
    setLocation(paramExpression);
    return this;
  }
  
  public final void setLine(int paramInt)
  {
    setLine(paramInt, 0);
  }
  
  public final void setLine(int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt1 < 0) {
      i = 0;
    }
    paramInt1 = paramInt2;
    if (paramInt2 < 0) {
      paramInt1 = 0;
    }
    this.position = ((i << 12) + paramInt1);
  }
  
  public void setLine(Compilation paramCompilation)
  {
    int i = paramCompilation.getLineNumber();
    if (i > 0)
    {
      setFile(paramCompilation.getFileName());
      setLine(i, paramCompilation.getColumnNumber());
    }
  }
  
  public final void setLocation(SourceLocator paramSourceLocator)
  {
    this.filename = paramSourceLocator.getFileName();
    setLine(paramSourceLocator.getLineNumber(), paramSourceLocator.getColumnNumber());
  }
  
  public boolean side_effects()
  {
    return true;
  }
  
  public String toString()
  {
    String str2 = getClass().getName();
    String str1 = str2;
    if (str2.startsWith("gnu.expr.")) {
      str1 = str2.substring(9);
    }
    return str1 + "@" + Integer.toHexString(hashCode());
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration)
  {
    paramApplyExp.args = paramInlineCalls.visitExps(paramApplyExp.args, null);
    return paramApplyExp;
  }
  
  public Object valueIfConstant()
  {
    return null;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitExpression(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD) {}
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\Expression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */