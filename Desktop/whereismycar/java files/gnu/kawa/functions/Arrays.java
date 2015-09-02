package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.FVector;
import gnu.lists.GeneralArray;
import gnu.lists.SimpleVector;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class Arrays
{
  static final int[] shapeStrides = { 2, 1 };
  static final int[] zeros2 = new int[2];
  
  public static int effectiveIndex(Array paramArray, Procedure paramProcedure, Object[] paramArrayOfObject, int[] paramArrayOfInt)
    throws Throwable
  {
    paramProcedure = paramProcedure.applyN(paramArrayOfObject);
    if ((paramProcedure instanceof Values))
    {
      paramProcedure = (Values)paramProcedure;
      int j = 0;
      int i = 0;
      for (;;)
      {
        j = paramProcedure.nextPos(j);
        if (j == 0) {
          break;
        }
        paramArrayOfInt[i] = ((Number)paramProcedure.getPosPrevious(j)).intValue();
        i += 1;
      }
    }
    paramArrayOfInt[0] = ((Number)paramProcedure).intValue();
    return paramArray.getEffectiveIndex(paramArrayOfInt);
  }
  
  public static Array make(Array paramArray, Object paramObject)
  {
    int k = paramArray.getSize(0);
    int[] arrayOfInt = new int[k];
    Object localObject1 = null;
    int i = 1;
    int j = k;
    for (;;)
    {
      j -= 1;
      if (j < 0) {
        break;
      }
      int m = ((Number)paramArray.getRowMajor(j * 2)).intValue();
      int n = ((Number)paramArray.getRowMajor(j * 2 + 1)).intValue() - m;
      arrayOfInt[j] = n;
      Object localObject2 = localObject1;
      if (m != 0)
      {
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new int[k];
        }
        localObject2[j] = m;
      }
      i *= n;
      localObject1 = localObject2;
    }
    return GeneralArray.makeSimple((int[])localObject1, arrayOfInt, new FVector(i, paramObject));
  }
  
  public static Array makeSimple(Array paramArray, SimpleVector paramSimpleVector)
  {
    int j = paramArray.getSize(0);
    int[] arrayOfInt = new int[j];
    Object localObject1 = null;
    int i = j;
    for (;;)
    {
      int k = i - 1;
      if (k < 0) {
        break;
      }
      int m = ((Number)paramArray.getRowMajor(k * 2)).intValue();
      arrayOfInt[k] = (((Number)paramArray.getRowMajor(k * 2 + 1)).intValue() - m);
      i = k;
      if (m != 0)
      {
        Object localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new int[j];
        }
        localObject2[k] = m;
        i = k;
        localObject1 = localObject2;
      }
    }
    return GeneralArray.makeSimple((int[])localObject1, arrayOfInt, paramSimpleVector);
  }
  
  public static Array shape(Object[] paramArrayOfObject)
  {
    int i = paramArrayOfObject.length;
    if ((i & 0x1) != 0) {
      throw new RuntimeException("shape: not an even number of arguments");
    }
    paramArrayOfObject = new FVector(paramArrayOfObject);
    int[] arrayOfInt1 = zeros2;
    int[] arrayOfInt2 = shapeStrides;
    return paramArrayOfObject.transpose(arrayOfInt1, new int[] { i >> 1, 2 }, 0, arrayOfInt2);
  }
  
  public static Array shareArray(Array paramArray1, Array paramArray2, Procedure paramProcedure)
    throws Throwable
  {
    int i = paramArray2.getSize(0);
    Object[] arrayOfObject = new Object[i];
    int[] arrayOfInt1 = new int[i];
    int[] arrayOfInt2 = new int[i];
    int j = 0;
    int k = i;
    int m;
    for (;;)
    {
      m = k - 1;
      if (m < 0) {
        break;
      }
      localObject1 = paramArray2.getRowMajor(m * 2);
      arrayOfObject[m] = localObject1;
      k = ((Number)localObject1).intValue();
      arrayOfInt2[m] = k;
      int n = ((Number)paramArray2.getRowMajor(m * 2 + 1)).intValue() - k;
      arrayOfInt1[m] = n;
      k = m;
      if (n <= 0)
      {
        j = 1;
        k = m;
      }
    }
    k = paramArray1.rank();
    paramArray2 = new int[i];
    if (j != 0)
    {
      i = 0;
      return paramArray1.transpose(arrayOfInt2, arrayOfInt1, i, paramArray2);
    }
    Object localObject1 = new int[k];
    j = effectiveIndex(paramArray1, paramProcedure, arrayOfObject, (int[])localObject1);
    for (;;)
    {
      k = i - 1;
      i = j;
      if (k < 0) {
        break;
      }
      i = arrayOfInt1[k];
      m = arrayOfInt2[k];
      if (i <= 1)
      {
        paramArray2[k] = 0;
        i = k;
      }
      else
      {
        Object localObject2 = arrayOfObject[k];
        arrayOfObject[k] = IntNum.make(m + 1);
        paramArray2[k] = (effectiveIndex(paramArray1, paramProcedure, arrayOfObject, (int[])localObject1) - j);
        arrayOfObject[k] = localObject2;
        i = k;
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\Arrays.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */