package gnu.mapping;

import gnu.bytecode.ArrayType;
import gnu.bytecode.Type;

public abstract class MethodProc
  extends ProcedureN
{
  public static final int NO_MATCH = -1;
  public static final int NO_MATCH_AMBIGUOUS = -851968;
  public static final int NO_MATCH_BAD_TYPE = -786432;
  public static final int NO_MATCH_TOO_FEW_ARGS = -983040;
  public static final int NO_MATCH_TOO_MANY_ARGS = -917504;
  static final Type[] unknownArgTypes = { Type.pointer_type };
  protected Object argTypes;
  
  public static RuntimeException matchFailAsException(int paramInt, Procedure paramProcedure, Object[] paramArrayOfObject)
  {
    int i = (short)paramInt;
    if ((paramInt & 0xFFFF0000) != -786432) {
      return new WrongArguments(paramProcedure, paramArrayOfObject.length);
    }
    if (i > 0) {}
    for (paramArrayOfObject = paramArrayOfObject[(i - 1)];; paramArrayOfObject = null) {
      return new WrongType(paramProcedure, i, paramArrayOfObject);
    }
  }
  
  public static int mostSpecific(MethodProc[] paramArrayOfMethodProc, int paramInt)
  {
    if (paramInt <= 1)
    {
      i = paramInt - 1;
      return i;
    }
    Object localObject3 = paramArrayOfMethodProc[0];
    Object localObject2 = null;
    int j = 1;
    int i = 0;
    label26:
    MethodProc localMethodProc1;
    Object localObject1;
    if (j < paramInt)
    {
      localMethodProc1 = paramArrayOfMethodProc[j];
      if (localObject3 != null)
      {
        localObject1 = mostSpecific((MethodProc)localObject3, localMethodProc1);
        if (localObject1 == null)
        {
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = new MethodProc[paramInt];
          }
          localObject1[0] = localObject3;
          localObject1[1] = localMethodProc1;
          i = 2;
          localObject3 = null;
          localObject2 = localObject1;
          localObject1 = localObject3;
        }
      }
    }
    for (;;)
    {
      j += 1;
      localObject3 = localObject1;
      break label26;
      if (localObject1 == localMethodProc1)
      {
        localObject1 = localMethodProc1;
        i = j;
        continue;
        int k = 0;
        for (;;)
        {
          if (k >= i) {
            break label186;
          }
          localObject1 = localObject2[k];
          MethodProc localMethodProc2 = mostSpecific((MethodProc)localObject1, localMethodProc1);
          if (localMethodProc2 == localObject1)
          {
            localObject1 = localObject3;
            break;
          }
          if (localMethodProc2 == null)
          {
            k = i + 1;
            localObject2[i] = localMethodProc1;
            localObject1 = localObject3;
            i = k;
            break;
          }
          k += 1;
        }
        label186:
        localObject1 = localMethodProc1;
        i = j;
        continue;
        if (localObject3 != null) {
          break;
        }
        return -1;
      }
      localObject1 = localObject3;
    }
  }
  
  public static MethodProc mostSpecific(MethodProc paramMethodProc1, MethodProc paramMethodProc2)
  {
    int i = 0;
    int k = 0;
    int n = 0;
    int i1 = paramMethodProc1.minArgs();
    int i2 = paramMethodProc2.minArgs();
    int i4 = paramMethodProc1.maxArgs();
    int i3 = paramMethodProc2.maxArgs();
    if (((i4 >= 0) && (i4 < i2)) || ((i3 >= 0) && (i3 < i1))) {
      paramMethodProc1 = null;
    }
    int j;
    label121:
    label165:
    label205:
    do
    {
      return paramMethodProc1;
      int m = paramMethodProc1.numParameters();
      j = paramMethodProc2.numParameters();
      if (m > j)
      {
        j = n;
        if (i4 != i3)
        {
          if (i4 < 0) {
            k = 1;
          }
          i = k;
          j = n;
          if (i3 < 0)
          {
            j = 1;
            i = k;
          }
        }
        if (i1 >= i2) {
          break label165;
        }
        k = 1;
        i = 0;
      }
      for (;;)
      {
        if (i >= m) {
          break label205;
        }
        n = paramMethodProc1.getParameterType(i).compare(paramMethodProc2.getParameterType(i));
        if (n == -1)
        {
          j = 1;
          if (k != 0)
          {
            return null;
            m = j;
            break;
            k = i;
            if (i1 <= i2) {
              break label121;
            }
            j = 1;
            k = i;
            break label121;
          }
        }
        if (n == 1)
        {
          k = 1;
          if (j != 0) {
            return null;
          }
        }
        i += 1;
      }
    } while (j != 0);
    if (k != 0) {
      return paramMethodProc2;
    }
    return null;
  }
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    checkArgCount(this, paramArrayOfObject.length);
    CallContext localCallContext = CallContext.getInstance();
    checkN(paramArrayOfObject, localCallContext);
    return localCallContext.runUntilValue();
  }
  
  public Type getParameterType(int paramInt)
  {
    if (!(this.argTypes instanceof Type[])) {
      resolveParameterTypes();
    }
    Object localObject = (Type[])this.argTypes;
    if ((paramInt < localObject.length) && ((paramInt < localObject.length - 1) || (maxArgs() >= 0))) {
      return localObject[paramInt];
    }
    if (maxArgs() < 0)
    {
      localObject = localObject[(localObject.length - 1)];
      if ((localObject instanceof ArrayType)) {
        return ((ArrayType)localObject).getComponentType();
      }
    }
    return Type.objectType;
  }
  
  public int isApplicable(Type[] paramArrayOfType)
  {
    int j = paramArrayOfType.length;
    int i = numArgs();
    if ((j < (i & 0xFFF)) || ((i >= 0) && (j > i >> 12)))
    {
      j = -1;
      return j;
    }
    i = 1;
    for (;;)
    {
      int k = j - 1;
      j = i;
      if (k < 0) {
        break;
      }
      int m = getParameterType(k).compare(paramArrayOfType[k]);
      if (m == -3) {
        return -1;
      }
      j = k;
      if (m < 0)
      {
        i = 0;
        j = k;
      }
    }
  }
  
  public int numParameters()
  {
    int i = numArgs();
    int j = i >> 12;
    if (j >= 0) {
      return j;
    }
    return (i & 0xFFF) + 1;
  }
  
  protected void resolveParameterTypes()
  {
    this.argTypes = unknownArgTypes;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\MethodProc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */