package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.Type;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.MethodProc;
import java.lang.reflect.Array;

class Closure
  extends MethodProc
{
  Object[][] evalFrames;
  LambdaExp lambda;
  
  public Closure(LambdaExp paramLambdaExp, CallContext paramCallContext)
  {
    this.lambda = paramLambdaExp;
    paramLambdaExp = paramCallContext.evalFrames;
    if (paramLambdaExp != null)
    {
      int i = paramLambdaExp.length;
      while ((i > 0) && (paramLambdaExp[(i - 1)] == null)) {
        i -= 1;
      }
      this.evalFrames = new Object[i][];
      System.arraycopy(paramLambdaExp, 0, this.evalFrames, 0, i);
    }
    setSymbol(this.lambda.getSymbol());
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    int k = ScopeExp.nesting(this.lambda);
    Object localObject1 = paramCallContext.values;
    Object[][] arrayOfObject = paramCallContext.evalFrames;
    if (this.evalFrames == null) {}
    for (int i = 0;; i = this.evalFrames.length)
    {
      int j = i;
      if (k >= i) {
        j = k;
      }
      Object localObject3 = new Object[j + 10][];
      if (this.evalFrames != null) {
        System.arraycopy(this.evalFrames, 0, localObject3, 0, this.evalFrames.length);
      }
      localObject3[k] = localObject1;
      paramCallContext.evalFrames = ((Object[][])localObject3);
      try
      {
        if (this.lambda.body != null) {
          break;
        }
        StringBuffer localStringBuffer = new StringBuffer("procedure ");
        localObject3 = this.lambda.getName();
        localObject1 = localObject3;
        if (localObject3 == null) {
          localObject1 = "<anonymous>";
        }
        localStringBuffer.append((String)localObject1);
        i = this.lambda.getLineNumber();
        if (i > 0)
        {
          localStringBuffer.append(" at line ");
          localStringBuffer.append(i);
        }
        localStringBuffer.append(" was called before it was expanded");
        throw new RuntimeException(localStringBuffer.toString());
      }
      finally
      {
        paramCallContext.evalFrames = arrayOfObject;
      }
    }
    this.lambda.body.apply(paramCallContext);
    paramCallContext.evalFrames = arrayOfObject;
  }
  
  public Object getProperty(Object paramObject1, Object paramObject2)
  {
    Object localObject2 = super.getProperty(paramObject1, paramObject2);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = this.lambda.getProperty(paramObject1, paramObject2);
    }
    return localObject1;
  }
  
  public int match0(CallContext paramCallContext)
  {
    return matchN(new Object[0], paramCallContext);
  }
  
  public int match1(Object paramObject, CallContext paramCallContext)
  {
    return matchN(new Object[] { paramObject }, paramCallContext);
  }
  
  public int match2(Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    return matchN(new Object[] { paramObject1, paramObject2 }, paramCallContext);
  }
  
  public int match3(Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    return matchN(new Object[] { paramObject1, paramObject2, paramObject3 }, paramCallContext);
  }
  
  public int match4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
  {
    return matchN(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 }, paramCallContext);
  }
  
  public int matchN(Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    int i = numArgs();
    int i1 = paramArrayOfObject.length;
    int j = i & 0xFFF;
    if (i1 < j) {
      return 0xFFF10000 | j;
    }
    i >>= 12;
    if ((i1 > i) && (i >= 0)) {
      return 0xFFF20000 | i;
    }
    Object[] arrayOfObject = new Object[this.lambda.frameSize];
    int m;
    label94:
    int k;
    int i2;
    Declaration localDeclaration;
    int n;
    Object localObject1;
    if (this.lambda.keywords == null)
    {
      i = 0;
      if (this.lambda.defaultArgs != null) {
        break label225;
      }
      m = 0;
      k = 0;
      i2 = this.lambda.min_args;
      localDeclaration = this.lambda.firstDecl();
      j = 0;
      i = 0;
      if (localDeclaration == null) {
        break label520;
      }
      if (i >= i2) {
        break label241;
      }
      n = i + 1;
      localObject1 = paramArrayOfObject[i];
      i = n;
    }
    for (;;)
    {
      Object localObject2 = localObject1;
      if (localDeclaration.type != null) {}
      try
      {
        localObject2 = localDeclaration.type.coerceFromObject(localObject1);
        localObject1 = localObject2;
        if (localDeclaration.isIndirectBinding())
        {
          localObject1 = localDeclaration.makeIndirectLocationFor();
          ((Location)localObject1).set(localObject2);
        }
        arrayOfObject[localDeclaration.evalIndex] = localObject1;
        localDeclaration = localDeclaration.nextDecl();
      }
      catch (ClassCastException paramArrayOfObject)
      {
        int i3;
        Type localType;
        return 0xFFF40000 | i;
      }
      i = this.lambda.keywords.length;
      break;
      label225:
      m = this.lambda.defaultArgs.length - i;
      break label94;
      label241:
      if (i < i2 + m)
      {
        if (i < i1)
        {
          n = i + 1;
          localObject1 = paramArrayOfObject[i];
          i = n;
        }
        for (;;)
        {
          k += 1;
          break;
          localObject1 = this.lambda.evalDefaultArg(k, paramCallContext);
        }
      }
      if ((this.lambda.max_args < 0) && (i == i2 + m))
      {
        if ((localDeclaration.type instanceof ArrayType))
        {
          i3 = i1 - i;
          localType = ((ArrayType)localDeclaration.type).getComponentType();
          if (localType == Type.objectType)
          {
            localObject1 = new Object[i3];
            System.arraycopy(paramArrayOfObject, i, localObject1, 0, i3);
          }
          else
          {
            localObject2 = Array.newInstance(localType.getReflectClass(), i3);
            n = 0;
            for (;;)
            {
              localObject1 = localObject2;
              if (n >= i3) {
                break;
              }
              try
              {
                localObject1 = localType.coerceFromObject(paramArrayOfObject[(i + n)]);
                Array.set(localObject2, n, localObject1);
                n += 1;
              }
              catch (ClassCastException paramArrayOfObject)
              {
                return 0xFFF40000 | i + n;
              }
            }
          }
        }
        else
        {
          localObject1 = LList.makeList(paramArrayOfObject, i);
        }
      }
      else
      {
        localObject1 = this.lambda.keywords;
        n = j + 1;
        localObject2 = Keyword.searchForKeyword(paramArrayOfObject, i2 + m, localObject1[j]);
        localObject1 = localObject2;
        if (localObject2 == Special.dfault) {
          localObject1 = this.lambda.evalDefaultArg(k, paramCallContext);
        }
        k += 1;
        j = n;
      }
    }
    label520:
    paramCallContext.values = arrayOfObject;
    paramCallContext.where = 0;
    paramCallContext.next = 0;
    paramCallContext.proc = this;
    return 0;
  }
  
  public int numArgs()
  {
    return this.lambda.min_args | this.lambda.max_args << 12;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\Closure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */