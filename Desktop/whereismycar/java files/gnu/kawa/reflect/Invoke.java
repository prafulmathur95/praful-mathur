package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.Language;
import gnu.expr.PairClassType;
import gnu.expr.PrimProcedure;
import gnu.expr.TypeValue;
import gnu.kawa.lispexpr.ClassNamespace;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import java.lang.reflect.Array;

public class Invoke
  extends ProcedureN
{
  public static final Invoke invoke = new Invoke("invoke", '*');
  public static final Invoke invokeSpecial = new Invoke("invoke-special", 'P');
  public static final Invoke invokeStatic = new Invoke("invoke-static", 'S');
  public static final Invoke make = new Invoke("make", 'N');
  char kind;
  Language language;
  
  public Invoke(String paramString, char paramChar)
  {
    this(paramString, paramChar, Language.getDefaultLanguage());
  }
  
  public Invoke(String paramString, char paramChar, Language paramLanguage)
  {
    super(paramString);
    this.kind = paramChar;
    this.language = paramLanguage;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileInvoke:validateApplyInvoke");
  }
  
  public static PrimProcedure getStaticMethod(ClassType paramClassType, String paramString, Expression[] paramArrayOfExpression)
  {
    try
    {
      paramClassType = CompileInvoke.getStaticMethod(paramClassType, paramString, paramArrayOfExpression);
      return paramClassType;
    }
    finally
    {
      paramClassType = finally;
      throw paramClassType;
    }
  }
  
  public static Object invoke$V(Object[] paramArrayOfObject)
    throws Throwable
  {
    return invoke.applyN(paramArrayOfObject);
  }
  
  public static Object invokeStatic$V(Object[] paramArrayOfObject)
    throws Throwable
  {
    return invokeStatic.applyN(paramArrayOfObject);
  }
  
  public static Object make$V(Object[] paramArrayOfObject)
    throws Throwable
  {
    return make.applyN(paramArrayOfObject);
  }
  
  public static ApplyExp makeInvokeStatic(ClassType paramClassType, String paramString, Expression[] paramArrayOfExpression)
  {
    PrimProcedure localPrimProcedure;
    try
    {
      localPrimProcedure = getStaticMethod(paramClassType, paramString, paramArrayOfExpression);
      if (localPrimProcedure == null) {
        throw new RuntimeException("missing or ambiguous method `" + paramString + "' in " + paramClassType.getName());
      }
    }
    finally {}
    paramClassType = new ApplyExp(localPrimProcedure, paramArrayOfExpression);
    return paramClassType;
  }
  
  private static ObjectType typeFrom(Object paramObject, Invoke paramInvoke)
  {
    Object localObject = paramObject;
    if ((paramObject instanceof Class)) {
      localObject = Type.make((Class)paramObject);
    }
    if ((localObject instanceof ObjectType)) {
      return (ObjectType)localObject;
    }
    if (((localObject instanceof String)) || ((localObject instanceof FString))) {
      return ClassType.make(localObject.toString());
    }
    if ((localObject instanceof Symbol)) {
      return ClassType.make(((Symbol)localObject).getName());
    }
    if ((localObject instanceof ClassNamespace)) {
      return ((ClassNamespace)localObject).getClassType();
    }
    throw new WrongType(paramInvoke, 0, localObject, "class-specifier");
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    Object[] arrayOfObject1 = paramCallContext.getArgs();
    if ((this.kind == 'S') || (this.kind == 'V') || (this.kind == 's') || (this.kind == '*'))
    {
      int j = arrayOfObject1.length;
      Procedure.checkArgCount(this, j);
      Object localObject = arrayOfObject1[0];
      if ((this.kind == 'S') || (this.kind == 's'))
      {
        localObject = typeFrom(localObject, this);
        localObject = lookupMethods((ObjectType)localObject, arrayOfObject1[1]);
        if (this.kind != 'S') {
          break label178;
        }
      }
      label178:
      for (int i = 2;; i = 1)
      {
        Object[] arrayOfObject2 = new Object[j - i];
        i = 0;
        if ((this.kind == 'V') || (this.kind == '*'))
        {
          arrayOfObject2[0] = arrayOfObject1[0];
          i = 0 + 1;
        }
        System.arraycopy(arrayOfObject1, 2, arrayOfObject2, i, j - 2);
        ((Procedure)localObject).checkN(arrayOfObject2, paramCallContext);
        return;
        localObject = Type.make(localObject.getClass());
        break;
      }
    }
    paramCallContext.writeValue(applyN(arrayOfObject1));
  }
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    if (this.kind == 'P') {
      throw new RuntimeException(getName() + ": invoke-special not allowed at run time");
    }
    int m = paramArrayOfObject.length;
    Procedure.checkArgCount(this, m);
    Object localObject1 = paramArrayOfObject[0];
    if ((this.kind != 'V') && (this.kind != '*')) {
      localObject1 = typeFrom(localObject1, this);
    }
    Object localObject4;
    int j;
    while (this.kind == 'N')
    {
      localObject3 = null;
      if ((localObject1 instanceof TypeValue))
      {
        localObject2 = ((TypeValue)localObject1).getConstructor();
        if (localObject2 != null)
        {
          i = m - 1;
          localObject1 = new Object[i];
          System.arraycopy(paramArrayOfObject, 1, localObject1, 0, i);
          localObject1 = ((Procedure)localObject2).applyN((Object[])localObject1);
          return localObject1;
          localObject1 = (ObjectType)Type.make(localObject1.getClass());
          continue;
        }
      }
      localObject2 = localObject1;
      if ((localObject1 instanceof PairClassType)) {
        localObject2 = ((PairClassType)localObject1).instanceType;
      }
      localObject1 = localObject2;
      if (!(localObject2 instanceof ArrayType)) {
        break label440;
      }
      localObject4 = ((ArrayType)localObject2).getComponentType();
      int i1 = paramArrayOfObject.length - 1;
      if ((i1 >= 2) && ((paramArrayOfObject[1] instanceof Keyword)))
      {
        localObject1 = ((Keyword)paramArrayOfObject[1]).getName();
        if (("length".equals(localObject1)) || ("size".equals(localObject1)))
        {
          j = ((Number)paramArrayOfObject[2]).intValue();
          i = 3;
        }
      }
      for (k = 1;; k = 0)
      {
        localObject2 = Array.newInstance(((Type)localObject4).getReflectClass(), j);
        j = 0;
        for (;;)
        {
          localObject1 = localObject2;
          if (i > i1) {
            break;
          }
          localObject3 = paramArrayOfObject[i];
          localObject1 = localObject3;
          m = i;
          int n = j;
          if (k != 0)
          {
            localObject1 = localObject3;
            m = i;
            n = j;
            if ((localObject3 instanceof Keyword))
            {
              localObject1 = localObject3;
              m = i;
              n = j;
              if (i < i1) {
                localObject1 = ((Keyword)localObject3).getName();
              }
            }
          }
          try
          {
            n = Integer.parseInt((String)localObject1);
            m = i + 1;
            localObject1 = paramArrayOfObject[m];
            Array.set(localObject2, n, ((Type)localObject4).coerceFromObject(localObject1));
            j = n + 1;
            i = m + 1;
          }
          catch (Throwable paramArrayOfObject)
          {
            throw new RuntimeException("non-integer keyword '" + (String)localObject1 + "' in array constructor");
          }
        }
        j = i1;
        i = 1;
      }
    }
    Object localObject3 = paramArrayOfObject[1];
    label440:
    localObject3 = lookupMethods((ObjectType)localObject1, localObject3);
    if (this.kind != 'N')
    {
      if ((this.kind == 'S') || (this.kind == 's')) {}
      for (i = 2;; i = 1)
      {
        localObject1 = new Object[m - i];
        i = 0;
        if ((this.kind == 'V') || (this.kind == '*'))
        {
          localObject1[0] = paramArrayOfObject[0];
          i = 0 + 1;
        }
        System.arraycopy(paramArrayOfObject, 2, localObject1, i, m - 2);
        return ((MethodProc)localObject3).applyN((Object[])localObject1);
      }
    }
    Object localObject2 = CallContext.getInstance();
    int i = 0;
    while ((i < paramArrayOfObject.length) && (!(paramArrayOfObject[i] instanceof Keyword))) {
      i += 1;
    }
    int k = -1;
    Object localObject5;
    if (i == paramArrayOfObject.length)
    {
      j = ((MethodProc)localObject3).matchN(paramArrayOfObject, (CallContext)localObject2);
      if (j == 0) {
        return ((CallContext)localObject2).runUntilValue();
      }
      localObject4 = ClassMethods.apply((ClassType)localObject1, "valueOf", '\000', this.language);
      if (localObject4 != null)
      {
        localObject5 = new Object[m - 1];
        System.arraycopy(paramArrayOfObject, 1, localObject5, 0, m - 1);
        k = ((MethodProc)localObject4).matchN((Object[])localObject5, (CallContext)localObject2);
        j = k;
        if (k == 0) {
          return ((CallContext)localObject2).runUntilValue();
        }
      }
      localObject2 = ((MethodProc)localObject3).apply1(paramArrayOfObject[0]);
      k = j;
      j = i;
    }
    for (;;)
    {
      if (j + 1 < paramArrayOfObject.length)
      {
        localObject4 = paramArrayOfObject[j];
        if ((localObject4 instanceof Keyword)) {}
      }
      else
      {
        if (i == paramArrayOfObject.length) {
          j = 1;
        }
        if (j == paramArrayOfObject.length) {
          return localObject2;
        }
        localObject1 = ClassMethods.apply((ClassType)localObject1, "add", '\000', this.language);
        if (localObject1 != null) {
          break label821;
        }
        throw MethodProc.matchFailAsException(k, (Procedure)localObject3, paramArrayOfObject);
        localObject2 = new Object[i];
        System.arraycopy(paramArrayOfObject, 0, localObject2, 0, i);
        localObject2 = ((MethodProc)localObject3).applyN((Object[])localObject2);
        break;
      }
      localObject4 = (Keyword)localObject4;
      localObject5 = paramArrayOfObject[(j + 1)];
      SlotSet.apply(false, localObject2, ((Keyword)localObject4).getName(), localObject5);
      j += 2;
    }
    label821:
    while (j < paramArrayOfObject.length)
    {
      ((MethodProc)localObject1).apply2(localObject2, paramArrayOfObject[j]);
      j += 1;
    }
    return localObject2;
  }
  
  protected MethodProc lookupMethods(ObjectType paramObjectType, Object paramObject)
  {
    char c = 'P';
    if (this.kind == 'N')
    {
      paramObject = "<init>";
      if (this.kind != 'P') {
        break label146;
      }
    }
    MethodProc localMethodProc;
    for (;;)
    {
      localMethodProc = ClassMethods.apply(paramObjectType, (String)paramObject, c, this.language);
      if (localMethodProc != null) {
        break label175;
      }
      throw new RuntimeException(getName() + ": no method named `" + (String)paramObject + "' in class " + paramObjectType.getName());
      if (((paramObject instanceof String)) || ((paramObject instanceof FString))) {}
      for (paramObject = paramObject.toString();; paramObject = ((Symbol)paramObject).getName())
      {
        paramObject = Compilation.mangleName((String)paramObject);
        break;
        if (!(paramObject instanceof Symbol)) {
          break label135;
        }
      }
      label135:
      throw new WrongType(this, 1, null);
      label146:
      if ((this.kind == '*') || (this.kind == 'V')) {
        c = 'V';
      } else {
        c = '\000';
      }
    }
    label175:
    return localMethodProc;
  }
  
  public int numArgs()
  {
    if (this.kind == 'N') {}
    for (int i = 1;; i = 2) {
      return i | 0xF000;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\Invoke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */