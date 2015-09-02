package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class IntNum
  extends RatNum
  implements Externalizable
{
  static final int maxFixNum = 1024;
  static final int minFixNum = -100;
  static final int numFixNum = 1125;
  static final IntNum[] smallFixNums = new IntNum['ѥ'];
  public int ival;
  public int[] words;
  
  static
  {
    int i = 1125;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      smallFixNums[i] = new IntNum(i - 100);
    }
  }
  
  public IntNum() {}
  
  public IntNum(int paramInt)
  {
    this.ival = paramInt;
  }
  
  public static IntNum abs(IntNum paramIntNum)
  {
    IntNum localIntNum = paramIntNum;
    if (paramIntNum.isNegative()) {
      localIntNum = neg(paramIntNum);
    }
    return localIntNum;
  }
  
  public static final IntNum add(int paramInt1, int paramInt2)
  {
    return make(paramInt1 + paramInt2);
  }
  
  public static IntNum add(IntNum paramIntNum, int paramInt)
  {
    if (paramIntNum.words == null) {
      return add(paramIntNum.ival, paramInt);
    }
    IntNum localIntNum = new IntNum(0);
    localIntNum.setAdd(paramIntNum, paramInt);
    return localIntNum.canonicalize();
  }
  
  public static IntNum add(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    return add(paramIntNum1, paramIntNum2, 1);
  }
  
  public static IntNum add(IntNum paramIntNum1, IntNum paramIntNum2, int paramInt)
  {
    if ((paramIntNum1.words == null) && (paramIntNum2.words == null)) {
      return make(paramInt * paramIntNum2.ival + paramIntNum1.ival);
    }
    IntNum localIntNum1 = paramIntNum2;
    if (paramInt != 1) {
      if (paramInt != -1) {
        break label65;
      }
    }
    label65:
    for (localIntNum1 = neg(paramIntNum2); paramIntNum1.words == null; localIntNum1 = times(paramIntNum2, make(paramInt))) {
      return add(localIntNum1, paramIntNum1.ival);
    }
    if (localIntNum1.words == null) {
      return add(paramIntNum1, localIntNum1.ival);
    }
    IntNum localIntNum2 = paramIntNum1;
    paramIntNum2 = localIntNum1;
    if (localIntNum1.ival > paramIntNum1.ival)
    {
      paramIntNum2 = paramIntNum1;
      localIntNum2 = localIntNum1;
    }
    paramIntNum1 = alloc(localIntNum2.ival + 1);
    paramInt = paramIntNum2.ival;
    long l2 = MPN.add_n(paramIntNum1.words, localIntNum2.words, paramIntNum2.words, paramInt);
    long l1;
    if (paramIntNum2.words[(paramInt - 1)] < 0) {
      l1 = 4294967295L;
    }
    while (paramInt < localIntNum2.ival)
    {
      l2 += (localIntNum2.words[paramInt] & 0xFFFFFFFF) + l1;
      paramIntNum1.words[paramInt] = ((int)l2);
      l2 >>>= 32;
      paramInt += 1;
      continue;
      l1 = 0L;
    }
    long l3 = l1;
    if (localIntNum2.words[(paramInt - 1)] < 0) {
      l3 = l1 - 1L;
    }
    paramIntNum1.words[paramInt] = ((int)(l2 + l3));
    paramIntNum1.ival = (paramInt + 1);
    return paramIntNum1.canonicalize();
  }
  
  public static IntNum alloc(int paramInt)
  {
    if (paramInt <= 1) {
      return new IntNum();
    }
    IntNum localIntNum = new IntNum();
    localIntNum.words = new int[paramInt];
    return localIntNum;
  }
  
  public static IntNum asIntNumOrNull(Object paramObject)
  {
    if ((paramObject instanceof IntNum)) {
      return (IntNum)paramObject;
    }
    if ((paramObject instanceof BigInteger)) {
      return valueOf(paramObject.toString(), 10);
    }
    if (((paramObject instanceof Number)) && (((paramObject instanceof Integer)) || ((paramObject instanceof Long)) || ((paramObject instanceof Short)) || ((paramObject instanceof Byte)))) {
      return make(((Number)paramObject).longValue());
    }
    return null;
  }
  
  public static int compare(IntNum paramIntNum, long paramLong)
  {
    long l;
    if (paramIntNum.words == null)
    {
      l = paramIntNum.ival;
      if (l >= paramLong) {}
    }
    else
    {
      boolean bool2;
      label58:
      label92:
      do
      {
        return -1;
        bool2 = paramIntNum.isNegative();
        if (paramLong < 0L) {}
        for (boolean bool1 = true;; bool1 = false)
        {
          if (bool2 == bool1) {
            break label58;
          }
          if (bool2) {
            break;
          }
          return 1;
        }
        if (paramIntNum.words == null) {}
        for (int i = 1;; i = paramIntNum.ival)
        {
          if (i != 1) {
            break label92;
          }
          l = paramIntNum.words[0];
          break;
        }
        if (i == 2)
        {
          l = paramIntNum.longValue();
          break;
        }
      } while (bool2);
      return 1;
    }
    if (l > paramLong) {
      return 1;
    }
    return 0;
  }
  
  public static int compare(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    boolean bool1 = false;
    int k = 1;
    if ((paramIntNum1.words == null) && (paramIntNum2.words == null)) {
      if (paramIntNum1.ival >= paramIntNum2.ival) {}
    }
    boolean bool2;
    do
    {
      return -1;
      if (paramIntNum1.ival > paramIntNum2.ival) {
        return 1;
      }
      return 0;
      bool2 = paramIntNum1.isNegative();
      if (bool2 == paramIntNum2.isNegative()) {
        break;
      }
    } while (bool2);
    return 1;
    int j;
    if (paramIntNum1.words == null)
    {
      i = 1;
      if (paramIntNum2.words != null) {
        break label121;
      }
      j = 1;
      label88:
      if (i == j) {
        break label134;
      }
      if (i > j) {
        bool1 = true;
      }
      if (bool1 == bool2) {
        break label129;
      }
    }
    label121:
    label129:
    for (int i = k;; i = -1)
    {
      return i;
      i = paramIntNum1.ival;
      break;
      j = paramIntNum2.ival;
      break label88;
    }
    label134:
    return MPN.cmp(paramIntNum1.words, paramIntNum2.words, i);
  }
  
  public static void divide(long paramLong1, long paramLong2, IntNum paramIntNum1, IntNum paramIntNum2, int paramInt)
  {
    int i = paramInt;
    if (paramInt == 5)
    {
      if (paramLong2 < 0L) {
        i = 2;
      }
    }
    else
    {
      if (paramLong1 >= 0L) {
        break label109;
      }
      paramInt = 1;
      if (paramLong1 != Long.MIN_VALUE) {
        break label60;
      }
      divide(make(paramLong1), make(paramLong2), paramIntNum1, paramIntNum2, i);
    }
    label60:
    int j;
    label109:
    label115:
    label133:
    long l1;
    long l2;
    int m;
    int k;
    do
    {
      return;
      i = 1;
      break;
      paramLong1 = -paramLong1;
      for (;;)
      {
        if (paramLong2 >= 0L) {
          break label292;
        }
        j = 1;
        if (paramLong2 != Long.MIN_VALUE) {
          break label133;
        }
        if (i != 3) {
          break label115;
        }
        if (paramIntNum1 != null) {
          paramIntNum1.set(0);
        }
        if (paramIntNum2 == null) {
          break;
        }
        paramIntNum2.set(paramLong1);
        return;
        paramInt = 0;
      }
      divide(make(paramLong1), make(paramLong2), paramIntNum1, paramIntNum2, i);
      return;
      paramLong2 = -paramLong2;
      l1 = paramLong1 / paramLong2;
      l2 = paramLong1 % paramLong2;
      m = paramInt ^ j;
      k = 0;
      j = k;
      if (l2 != 0L) {
        j = k;
      }
      switch (i)
      {
      default: 
        j = k;
      case 3: 
        if (paramIntNum1 != null)
        {
          paramLong1 = l1;
          if (j != 0) {
            paramLong1 = l1 + 1L;
          }
          l1 = paramLong1;
          if (m != 0) {
            l1 = -paramLong1;
          }
          paramIntNum1.set(l1);
        }
        break;
      }
    } while (paramIntNum2 == null);
    paramLong1 = l2;
    i = paramInt;
    if (j != 0)
    {
      paramLong1 = paramLong2 - l2;
      if (paramInt != 0) {
        break label356;
      }
    }
    label292:
    label356:
    for (i = 1;; i = 0)
    {
      paramLong2 = paramLong1;
      if (i != 0) {
        paramLong2 = -paramLong1;
      }
      paramIntNum2.set(paramLong2);
      return;
      j = 0;
      break;
      if (i == 1) {}
      for (i = 1;; i = 0)
      {
        j = k;
        if (m != i) {
          break;
        }
        j = 1;
        break;
      }
      if (l2 > paramLong2 - (1L & l1) >> 1) {}
      for (j = 1;; j = 0) {
        break;
      }
    }
  }
  
  public static void divide(IntNum paramIntNum1, IntNum paramIntNum2, IntNum paramIntNum3, IntNum paramIntNum4, int paramInt)
  {
    if (((paramIntNum1.words == null) || (paramIntNum1.ival <= 2)) && ((paramIntNum2.words == null) || (paramIntNum2.ival <= 2)))
    {
      long l1 = paramIntNum1.longValue();
      long l2 = paramIntNum2.longValue();
      if ((l1 != Long.MIN_VALUE) && (l2 != Long.MIN_VALUE))
      {
        divide(l1, l2, paramIntNum3, paramIntNum4, paramInt);
        return;
      }
    }
    boolean bool1 = paramIntNum1.isNegative();
    boolean bool2 = paramIntNum2.isNegative();
    int i2 = bool1 ^ bool2;
    if (paramIntNum2.words == null) {}
    int[] arrayOfInt;
    for (int k = 1;; k = paramIntNum2.ival)
    {
      arrayOfInt = new int[k];
      paramIntNum2.getAbsolute(arrayOfInt);
      while ((k > 1) && (arrayOfInt[(k - 1)] == 0)) {
        k -= 1;
      }
    }
    if (paramIntNum1.words == null) {}
    Object localObject2;
    for (int i = 1;; i = paramIntNum1.ival)
    {
      localObject2 = new int[i + 2];
      paramIntNum1.getAbsolute((int[])localObject2);
      while ((i > 1) && (localObject2[(i - 1)] == 0)) {
        i -= 1;
      }
    }
    int j = MPN.cmp((int[])localObject2, i, arrayOfInt, k);
    Object localObject1;
    if (j < 0)
    {
      localObject1 = arrayOfInt;
      paramIntNum1 = (IntNum)localObject2;
      j = 1;
      localObject1[0] = 0;
    }
    label238:
    int n;
    while ((i > 1) && (paramIntNum1[(i - 1)] == 0))
    {
      i -= 1;
      continue;
      if (j == 0)
      {
        localObject2[0] = 1;
        j = 1;
        arrayOfInt[0] = 0;
        i = 1;
        localObject1 = localObject2;
        paramIntNum1 = arrayOfInt;
      }
      else if (k == 1)
      {
        j = i;
        k = 1;
        arrayOfInt[0] = MPN.divmod_1((int[])localObject2, (int[])localObject2, i, arrayOfInt[0]);
        i = k;
        localObject1 = localObject2;
        paramIntNum1 = arrayOfInt;
      }
      else
      {
        n = MPN.count_leading_zeros(arrayOfInt[(k - 1)]);
        j = i;
        if (n != 0)
        {
          MPN.lshift(arrayOfInt, 0, arrayOfInt, k, n);
          localObject2[i] = MPN.lshift((int[])localObject2, 0, (int[])localObject2, i, n);
          j = i + 1;
        }
        if (j != k) {
          break label936;
        }
        i = j + 1;
        localObject2[j] = 0;
      }
    }
    for (;;)
    {
      MPN.divide((int[])localObject2, i, arrayOfInt, k);
      int m = k;
      MPN.rshift0(arrayOfInt, (int[])localObject2, 0, m, n);
      int i1 = i + 1 - k;
      j = i1;
      i = m;
      localObject1 = localObject2;
      paramIntNum1 = arrayOfInt;
      if (paramIntNum3 == null) {
        break label238;
      }
      n = 0;
      for (;;)
      {
        j = i1;
        i = m;
        localObject1 = localObject2;
        paramIntNum1 = arrayOfInt;
        if (n >= i1) {
          break;
        }
        localObject2[n] = localObject2[(n + k)];
        n += 1;
      }
      m = i;
      if (paramIntNum1[(i - 1)] < 0)
      {
        paramIntNum1[i] = 0;
        m = i + 1;
      }
      n = 0;
      if (m <= 1)
      {
        i = n;
        if (paramIntNum1[0] == 0) {}
      }
      else
      {
        k = paramInt;
        if (paramInt == 5)
        {
          if (!bool2) {
            break label725;
          }
          k = 2;
        }
        i = n;
      }
      switch (k)
      {
      default: 
        i = n;
      case 3: 
        if (paramIntNum3 != null)
        {
          paramInt = j;
          if (localObject1[(j - 1)] < 0)
          {
            localObject1[j] = 0;
            paramInt = j + 1;
          }
          paramIntNum3.set((int[])localObject1, paramInt);
          if (i2 == 0) {
            break label869;
          }
          if (i != 0) {
            paramIntNum3.setInvert();
          }
        }
        else
        {
          label668:
          if (paramIntNum4 == null) {
            break;
          }
          paramIntNum4.set(paramIntNum1, m);
          if (i == 0) {
            break label926;
          }
          if (paramIntNum2.words != null) {
            break label895;
          }
          paramIntNum3 = paramIntNum4;
          if (!bool2) {
            break label882;
          }
        }
        break;
      }
      label725:
      label776:
      label856:
      label869:
      label882:
      for (paramInt = paramIntNum1[0] + paramIntNum2.ival;; paramInt = paramIntNum1[0] - paramIntNum2.ival)
      {
        paramIntNum3.set(paramInt);
        if (!bool1) {
          break label920;
        }
        paramIntNum4.setNegative(paramIntNum3);
        return;
        k = 1;
        break;
        if (k == 1) {}
        for (paramInt = 1;; paramInt = 0)
        {
          i = n;
          if (i2 != paramInt) {
            break;
          }
          i = 1;
          break;
        }
        if (paramIntNum4 == null)
        {
          localObject2 = new IntNum();
          ((IntNum)localObject2).set(paramIntNum1, m);
          localObject2 = shift((IntNum)localObject2, 1);
          if (bool2) {
            ((IntNum)localObject2).setNegative();
          }
          i = compare((IntNum)localObject2, paramIntNum2);
          paramInt = i;
          if (bool2) {
            paramInt = -i;
          }
          if ((paramInt != 1) && ((paramInt != 0) || ((localObject1[0] & 0x1) == 0))) {
            break label856;
          }
        }
        for (i = 1;; i = 0)
        {
          break;
          localObject2 = paramIntNum4;
          break label776;
        }
        paramIntNum3.setNegative();
        break label668;
        if (i == 0) {
          break label668;
        }
        paramIntNum3.setAdd(1);
        break label668;
      }
      label895:
      if (bool2) {}
      for (paramInt = 1;; paramInt = -1)
      {
        paramIntNum3 = add(paramIntNum4, paramIntNum2, paramInt);
        break;
      }
      label920:
      paramIntNum4.set(paramIntNum3);
      return;
      label926:
      if (!bool1) {
        break;
      }
      paramIntNum4.setNegative();
      return;
      label936:
      i = j;
    }
  }
  
  public static boolean equals(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    if ((paramIntNum1.words == null) && (paramIntNum2.words == null)) {
      return paramIntNum1.ival == paramIntNum2.ival;
    }
    if ((paramIntNum1.words == null) || (paramIntNum2.words == null) || (paramIntNum1.ival != paramIntNum2.ival)) {
      return false;
    }
    int i = paramIntNum1.ival;
    int j;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
    } while (paramIntNum1.words[j] == paramIntNum2.words[j]);
    return false;
  }
  
  public static final int gcd(int paramInt1, int paramInt2)
  {
    int j = paramInt1;
    int i = paramInt2;
    if (paramInt2 > paramInt1)
    {
      i = paramInt1;
      j = paramInt2;
    }
    for (;;)
    {
      if (i == 0) {
        return j;
      }
      if (i == 1) {
        return i;
      }
      paramInt1 = j % i;
      j = i;
      i = paramInt1;
    }
  }
  
  public static IntNum gcd(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    int j = paramIntNum1.ival;
    int k = paramIntNum2.ival;
    int i = j;
    if (paramIntNum1.words == null)
    {
      if (j == 0) {
        return abs(paramIntNum2);
      }
      if ((paramIntNum2.words == null) && (j != Integer.MIN_VALUE) && (k != Integer.MIN_VALUE))
      {
        i = j;
        if (j < 0) {
          i = -j;
        }
        j = k;
        if (k < 0) {
          j = -k;
        }
        return make(gcd(i, j));
      }
      i = 1;
    }
    j = k;
    if (paramIntNum2.words == null)
    {
      if (k == 0) {
        return abs(paramIntNum1);
      }
      j = 1;
    }
    if (i > j) {}
    for (;;)
    {
      int[] arrayOfInt1 = new int[i];
      int[] arrayOfInt2 = new int[i];
      paramIntNum1.getAbsolute(arrayOfInt1);
      paramIntNum2.getAbsolute(arrayOfInt2);
      j = MPN.gcd(arrayOfInt1, arrayOfInt2, i);
      paramIntNum1 = new IntNum(0);
      i = j;
      if (arrayOfInt1[(j - 1)] < 0)
      {
        arrayOfInt1[j] = 0;
        i = j + 1;
      }
      paramIntNum1.ival = i;
      paramIntNum1.words = arrayOfInt1;
      return paramIntNum1.canonicalize();
      i = j;
    }
  }
  
  public static int intValue(Object paramObject)
  {
    paramObject = (IntNum)paramObject;
    if (((IntNum)paramObject).words != null) {
      throw new ClassCastException("integer too large");
    }
    return ((IntNum)paramObject).ival;
  }
  
  public static IntNum lcm(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    if ((paramIntNum1.isZero()) || (paramIntNum2.isZero())) {
      return zero();
    }
    paramIntNum1 = abs(paramIntNum1);
    paramIntNum2 = abs(paramIntNum2);
    IntNum localIntNum = new IntNum();
    divide(times(paramIntNum1, paramIntNum2), gcd(paramIntNum1, paramIntNum2), localIntNum, null, 3);
    return localIntNum.canonicalize();
  }
  
  public static IntNum make(int paramInt)
  {
    if ((paramInt >= -100) && (paramInt <= 1024)) {
      return smallFixNums[(paramInt + 100)];
    }
    return new IntNum(paramInt);
  }
  
  public static IntNum make(long paramLong)
  {
    if ((paramLong >= -100L) && (paramLong <= 1024L)) {
      return smallFixNums[((int)paramLong + 100)];
    }
    int i = (int)paramLong;
    if (i == paramLong) {
      return new IntNum(i);
    }
    IntNum localIntNum = alloc(2);
    localIntNum.ival = 2;
    localIntNum.words[0] = i;
    localIntNum.words[1] = ((int)(paramLong >> 32));
    return localIntNum;
  }
  
  public static IntNum make(int[] paramArrayOfInt)
  {
    return make(paramArrayOfInt, paramArrayOfInt.length);
  }
  
  public static IntNum make(int[] paramArrayOfInt, int paramInt)
  {
    if (paramArrayOfInt == null) {
      return make(paramInt);
    }
    paramInt = wordsNeeded(paramArrayOfInt, paramInt);
    if (paramInt <= 1)
    {
      if (paramInt == 0) {
        return zero();
      }
      return make(paramArrayOfInt[0]);
    }
    IntNum localIntNum = new IntNum();
    localIntNum.words = paramArrayOfInt;
    localIntNum.ival = paramInt;
    return localIntNum;
  }
  
  public static IntNum makeU(long paramLong)
  {
    if (paramLong >= 0L) {
      return make(paramLong);
    }
    IntNum localIntNum = alloc(3);
    localIntNum.ival = 3;
    localIntNum.words[0] = ((int)paramLong);
    localIntNum.words[1] = ((int)(paramLong >> 32));
    localIntNum.words[2] = 0;
    return localIntNum;
  }
  
  public static IntNum minusOne()
  {
    return smallFixNums[99];
  }
  
  public static IntNum modulo(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    return remainder(paramIntNum1, paramIntNum2, 1);
  }
  
  public static IntNum neg(IntNum paramIntNum)
  {
    if ((paramIntNum.words == null) && (paramIntNum.ival != Integer.MIN_VALUE)) {
      return make(-paramIntNum.ival);
    }
    IntNum localIntNum = new IntNum(0);
    localIntNum.setNegative(paramIntNum);
    return localIntNum.canonicalize();
  }
  
  public static boolean negate(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    long l = 1L;
    if (paramArrayOfInt2[(paramInt - 1)] < 0) {}
    for (int i = 1;; i = 0)
    {
      int j = 0;
      while (j < paramInt)
      {
        l += ((paramArrayOfInt2[j] ^ 0xFFFFFFFF) & 0xFFFFFFFF);
        paramArrayOfInt1[j] = ((int)l);
        l >>= 32;
        j += 1;
      }
    }
    return (i != 0) && (paramArrayOfInt1[(paramInt - 1)] < 0);
  }
  
  public static final IntNum one()
  {
    return smallFixNums[101];
  }
  
  public static IntNum power(IntNum paramIntNum, int paramInt)
  {
    Object localObject1;
    if (paramInt <= 0) {
      if (paramInt == 0) {
        localObject1 = one();
      }
    }
    do
    {
      return (IntNum)localObject1;
      throw new Error("negative exponent");
      localObject1 = paramIntNum;
    } while (paramIntNum.isZero());
    int k;
    if (paramIntNum.words == null)
    {
      i = 1;
      k = (paramIntNum.intLength() * paramInt >> 5) + i * 2;
      if ((!paramIntNum.isNegative()) || ((paramInt & 0x1) == 0)) {
        break label181;
      }
    }
    Object localObject2;
    Object localObject3;
    int n;
    int m;
    Object localObject4;
    label181:
    for (int j = 1;; j = 0)
    {
      localObject1 = new int[k];
      localObject2 = new int[k];
      localObject3 = new int[k];
      paramIntNum.getAbsolute((int[])localObject1);
      n = 1;
      localObject2[0] = 1;
      k = paramInt;
      paramIntNum = (IntNum)localObject3;
      m = i;
      paramInt = n;
      localObject4 = localObject2;
      localObject3 = paramIntNum;
      if ((k & 0x1) == 0) {
        break label187;
      }
      MPN.mul(paramIntNum, (int[])localObject1, m, (int[])localObject2, n);
      i = n + m;
      for (;;)
      {
        paramInt = i;
        localObject4 = paramIntNum;
        localObject3 = localObject2;
        if (paramIntNum[(i - 1)] != 0) {
          break;
        }
        i -= 1;
      }
      i = paramIntNum.ival;
      break;
    }
    label187:
    int i1 = k >> 1;
    if (i1 == 0)
    {
      i = paramInt;
      if (localObject4[(paramInt - 1)] < 0) {
        i = paramInt + 1;
      }
      if (j != 0) {
        negate((int[])localObject4, (int[])localObject4, i);
      }
      return make((int[])localObject4, i);
    }
    MPN.mul((int[])localObject3, (int[])localObject1, m, (int[])localObject1, m);
    Object localObject5 = localObject1;
    int i = m * 2;
    for (;;)
    {
      m = i;
      localObject1 = localObject3;
      n = paramInt;
      localObject2 = localObject4;
      paramIntNum = (IntNum)localObject5;
      k = i1;
      if (localObject3[(i - 1)] != 0) {
        break;
      }
      i -= 1;
    }
  }
  
  public static IntNum quotient(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    return quotient(paramIntNum1, paramIntNum2, 3);
  }
  
  public static IntNum quotient(IntNum paramIntNum1, IntNum paramIntNum2, int paramInt)
  {
    IntNum localIntNum = new IntNum();
    divide(paramIntNum1, paramIntNum2, localIntNum, null, paramInt);
    return localIntNum.canonicalize();
  }
  
  public static IntNum remainder(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    return remainder(paramIntNum1, paramIntNum2, 3);
  }
  
  public static IntNum remainder(IntNum paramIntNum1, IntNum paramIntNum2, int paramInt)
  {
    if (paramIntNum2.isZero()) {
      return paramIntNum1;
    }
    IntNum localIntNum = new IntNum();
    divide(paramIntNum1, paramIntNum2, null, localIntNum, paramInt);
    return localIntNum.canonicalize();
  }
  
  public static int shift(int paramInt1, int paramInt2)
  {
    if (paramInt2 >= 32) {}
    do
    {
      return 0;
      if (paramInt2 >= 0) {
        return paramInt1 << paramInt2;
      }
      paramInt2 = -paramInt2;
      if (paramInt2 < 32) {
        break;
      }
    } while (paramInt1 >= 0);
    return -1;
    return paramInt1 >> paramInt2;
  }
  
  public static long shift(long paramLong, int paramInt)
  {
    if (paramInt >= 32) {}
    do
    {
      return 0L;
      if (paramInt >= 0) {
        return paramLong << paramInt;
      }
      paramInt = -paramInt;
      if (paramInt < 32) {
        break;
      }
    } while (paramLong >= 0L);
    return -1L;
    return paramLong >> paramInt;
  }
  
  public static IntNum shift(IntNum paramIntNum, int paramInt)
  {
    int i = 0;
    if (paramIntNum.words == null) {
      if (paramInt <= 0) {
        if (paramInt > -32)
        {
          paramInt = paramIntNum.ival >> -paramInt;
          localIntNum = make(paramInt);
        }
      }
    }
    do
    {
      return localIntNum;
      paramInt = i;
      if (paramIntNum.ival >= 0) {
        break;
      }
      paramInt = -1;
      break;
      if (paramInt < 32) {
        return make(paramIntNum.ival << paramInt);
      }
      localIntNum = paramIntNum;
    } while (paramInt == 0);
    IntNum localIntNum = new IntNum(0);
    localIntNum.setShift(paramIntNum, paramInt);
    return localIntNum.canonicalize();
  }
  
  public static IntNum sub(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    return add(paramIntNum1, paramIntNum2, -1);
  }
  
  public static final IntNum ten()
  {
    return smallFixNums[110];
  }
  
  public static final IntNum times(int paramInt1, int paramInt2)
  {
    return make(paramInt1 * paramInt2);
  }
  
  public static final IntNum times(IntNum paramIntNum, int paramInt)
  {
    if (paramInt == 0) {
      localObject = zero();
    }
    do
    {
      return (IntNum)localObject;
      localObject = paramIntNum;
    } while (paramInt == 1);
    Object localObject = paramIntNum.words;
    int m = paramIntNum.ival;
    if (localObject == null) {
      return make(m * paramInt);
    }
    IntNum localIntNum = alloc(m + 1);
    int k;
    int j;
    if (localObject[(m - 1)] < 0)
    {
      i = 1;
      negate(localIntNum.words, (int[])localObject, m);
      paramIntNum = localIntNum.words;
      k = i;
      j = paramInt;
      if (paramInt < 0) {
        if (i != 0) {
          break label154;
        }
      }
    }
    label154:
    for (int i = 1;; i = 0)
    {
      j = -paramInt;
      k = i;
      localIntNum.words[m] = MPN.mul_1(localIntNum.words, paramIntNum, m, j);
      localIntNum.ival = (m + 1);
      if (k != 0) {
        localIntNum.setNegative();
      }
      return localIntNum.canonicalize();
      i = 0;
      paramIntNum = (IntNum)localObject;
      break;
    }
  }
  
  public static final IntNum times(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    if (paramIntNum2.words == null) {
      return times(paramIntNum1, paramIntNum2.ival);
    }
    if (paramIntNum1.words == null) {
      return times(paramIntNum2, paramIntNum1.ival);
    }
    int j = paramIntNum1.ival;
    int k = paramIntNum2.ival;
    int i;
    Object localObject;
    if (paramIntNum1.isNegative())
    {
      i = 1;
      localObject = new int[j];
      negate((int[])localObject, paramIntNum1.words, j);
      paramIntNum1 = (IntNum)localObject;
      if (!paramIntNum2.isNegative()) {
        break label198;
      }
      if (i != 0) {
        break label192;
      }
      i = 1;
      label87:
      localObject = new int[k];
      negate((int[])localObject, paramIntNum2.words, k);
    }
    label192:
    label198:
    for (paramIntNum2 = (IntNum)localObject;; paramIntNum2 = paramIntNum2.words)
    {
      int n = j;
      IntNum localIntNum = paramIntNum1;
      int m = k;
      localObject = paramIntNum2;
      if (j < k)
      {
        localObject = paramIntNum1;
        m = j;
        localIntNum = paramIntNum2;
        n = k;
      }
      paramIntNum1 = alloc(n + m);
      MPN.mul(paramIntNum1.words, localIntNum, n, (int[])localObject, m);
      paramIntNum1.ival = (n + m);
      if (i != 0) {
        paramIntNum1.setNegative();
      }
      return paramIntNum1.canonicalize();
      i = 0;
      paramIntNum1 = paramIntNum1.words;
      break;
      i = 0;
      break label87;
    }
  }
  
  public static IntNum valueOf(String paramString)
    throws NumberFormatException
  {
    return valueOf(paramString, 10);
  }
  
  public static IntNum valueOf(String paramString, int paramInt)
    throws NumberFormatException
  {
    int m = paramString.length();
    if (m + paramInt <= 28)
    {
      localObject = paramString;
      if (m > 1)
      {
        localObject = paramString;
        if (paramString.charAt(0) == '+')
        {
          localObject = paramString;
          if (Character.digit(paramString.charAt(1), paramInt) >= 0) {
            localObject = paramString.substring(1);
          }
        }
      }
      return make(Long.parseLong((String)localObject, paramInt));
    }
    Object localObject = new byte[m];
    boolean bool = false;
    int j = 0;
    int i = 0;
    char c;
    if (j < m)
    {
      c = paramString.charAt(j);
      if ((c == '-') && (j == 0)) {
        bool = true;
      }
    }
    for (;;)
    {
      j += 1;
      break;
      if ((c != '+') || (j != 0)) {
        if (c != '_') {
          if (i == 0)
          {
            if (c != ' ') {
              if (c == '\t') {}
            }
          }
          else
          {
            int n = Character.digit(c, paramInt);
            if (n < 0) {
              throw new NumberFormatException("For input string: \"" + paramString + '"');
            }
            int k = i + 1;
            localObject[i] = ((byte)n);
            i = k;
            continue;
            return valueOf((byte[])localObject, i, bool, paramInt);
          }
        }
      }
    }
  }
  
  public static IntNum valueOf(byte[] paramArrayOfByte, int paramInt1, boolean paramBoolean, int paramInt2)
  {
    int[] arrayOfInt = new int[paramInt1 / MPN.chars_per_word(paramInt2) + 1];
    paramInt2 = MPN.set_str(arrayOfInt, paramArrayOfByte, paramInt1, paramInt2);
    if (paramInt2 == 0) {
      return zero();
    }
    paramInt1 = paramInt2;
    if (arrayOfInt[(paramInt2 - 1)] < 0)
    {
      arrayOfInt[paramInt2] = 0;
      paramInt1 = paramInt2 + 1;
    }
    if (paramBoolean) {
      negate(arrayOfInt, arrayOfInt, paramInt1);
    }
    return make(arrayOfInt, paramInt1);
  }
  
  public static IntNum valueOf(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    byte[] arrayOfByte = new byte[paramInt2];
    int j = 0;
    int i = 0;
    char c;
    if (j < paramInt2)
    {
      c = paramArrayOfChar[(paramInt1 + j)];
      if (c == '-') {
        paramBoolean = true;
      }
    }
    for (;;)
    {
      j += 1;
      break;
      if (c != '_') {
        if (i == 0)
        {
          if (c != ' ') {
            if (c == '\t') {}
          }
        }
        else
        {
          int m = Character.digit(c, paramInt3);
          if (m < 0) {
            return valueOf(arrayOfByte, i, paramBoolean, paramInt3);
          }
          int k = i + 1;
          arrayOfByte[i] = ((byte)m);
          i = k;
        }
      }
    }
  }
  
  public static int wordsNeeded(int[] paramArrayOfInt, int paramInt)
  {
    int i = paramInt;
    paramInt = i;
    int j;
    if (i > 0)
    {
      paramInt = i - 1;
      int k = paramArrayOfInt[paramInt];
      i = paramInt;
      j = k;
      if (k == -1)
      {
        i = paramInt;
        do
        {
          paramInt = i;
          if (i <= 0) {
            break;
          }
          j = paramArrayOfInt[(i - 1)];
          paramInt = i;
          if (j >= 0) {
            break;
          }
          paramInt = i - 1;
          i = paramInt;
        } while (j == -1);
      }
    }
    else
    {
      return paramInt + 1;
    }
    for (;;)
    {
      paramInt = i;
      if (j != 0) {
        break;
      }
      paramInt = i;
      if (i <= 0) {
        break;
      }
      j = paramArrayOfInt[(i - 1)];
      paramInt = i;
      if (j < 0) {
        break;
      }
      i -= 1;
    }
  }
  
  public static final IntNum zero()
  {
    return smallFixNums[100];
  }
  
  public Numeric add(Object paramObject, int paramInt)
  {
    if ((paramObject instanceof IntNum)) {
      return add(this, (IntNum)paramObject, paramInt);
    }
    if (!(paramObject instanceof Numeric)) {
      throw new IllegalArgumentException();
    }
    return ((Numeric)paramObject).addReversed(this, paramInt);
  }
  
  public BigDecimal asBigDecimal()
  {
    if (this.words == null) {
      return new BigDecimal(this.ival);
    }
    if (this.ival <= 2) {
      return BigDecimal.valueOf(longValue());
    }
    return new BigDecimal(toString());
  }
  
  public BigInteger asBigInteger()
  {
    if ((this.words == null) || (this.ival <= 2)) {
      return BigInteger.valueOf(longValue());
    }
    return new BigInteger(toString());
  }
  
  public IntNum canonicalize()
  {
    if (this.words != null)
    {
      int i = wordsNeeded(this.words, this.ival);
      this.ival = i;
      if (i <= 1)
      {
        if (this.ival == 1) {
          this.ival = this.words[0];
        }
        this.words = null;
      }
    }
    IntNum localIntNum = this;
    if (this.words == null)
    {
      localIntNum = this;
      if (this.ival >= -100)
      {
        localIntNum = this;
        if (this.ival <= 1024) {
          localIntNum = smallFixNums[(this.ival + 100)];
        }
      }
    }
    return localIntNum;
  }
  
  boolean checkBits(int paramInt)
  {
    boolean bool = true;
    if (paramInt <= 0) {}
    do
    {
      return false;
      if (this.words != null) {
        break;
      }
    } while ((paramInt <= 31) && ((this.ival & (1 << paramInt) - 1) == 0));
    return true;
    int i = 0;
    while (i < paramInt >> 5)
    {
      if (this.words[i] != 0) {
        return true;
      }
      i += 1;
    }
    if (((paramInt & 0x1F) != 0) && ((this.words[i] & (1 << (paramInt & 0x1F)) - 1) != 0)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public int compare(Object paramObject)
  {
    if ((paramObject instanceof IntNum)) {
      return compare(this, (IntNum)paramObject);
    }
    return ((RealNum)paramObject).compareReversed(this);
  }
  
  public final IntNum denominator()
  {
    return one();
  }
  
  public Numeric div(Object paramObject)
  {
    if ((paramObject instanceof RatNum))
    {
      paramObject = (RatNum)paramObject;
      return RatNum.make(times(this, ((RatNum)paramObject).denominator()), ((RatNum)paramObject).numerator());
    }
    if (!(paramObject instanceof Numeric)) {
      throw new IllegalArgumentException();
    }
    return ((Numeric)paramObject).divReversed(this);
  }
  
  public double doubleValue()
  {
    if (this.words == null) {
      return this.ival;
    }
    if (this.ival <= 2) {
      return longValue();
    }
    if (isNegative()) {
      return neg(this).roundToDouble(0, true, false);
    }
    return roundToDouble(0, false, false);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof IntNum))) {
      return false;
    }
    return equals(this, (IntNum)paramObject);
  }
  
  public void format(int paramInt, StringBuffer paramStringBuffer)
  {
    if (paramInt == 10)
    {
      if (this.words == null)
      {
        paramStringBuffer.append(this.ival);
        return;
      }
      if (this.ival <= 2)
      {
        paramStringBuffer.append(longValue());
        return;
      }
    }
    paramStringBuffer.append(toString(paramInt));
  }
  
  public void format(int paramInt, StringBuilder paramStringBuilder)
  {
    if (this.words == null) {
      if (paramInt == 10) {
        paramStringBuilder.append(this.ival);
      }
    }
    int i;
    int n;
    label230:
    int i2;
    for (;;)
    {
      return;
      paramStringBuilder.append(Integer.toString(this.ival, paramInt));
      return;
      if (this.ival <= 2)
      {
        long l = longValue();
        if (paramInt == 10)
        {
          paramStringBuilder.append(l);
          return;
        }
        paramStringBuilder.append(Long.toString(l, paramInt));
        return;
      }
      boolean bool = isNegative();
      int[] arrayOfInt;
      if ((bool) || (paramInt != 16))
      {
        arrayOfInt = new int[this.ival];
        getAbsolute(arrayOfInt);
      }
      for (;;)
      {
        i = this.ival;
        if (paramInt != 16) {
          break label230;
        }
        if (bool) {
          paramStringBuilder.append('-');
        }
        k = paramStringBuilder.length();
        paramInt = i;
        i = paramInt - 1;
        if (i < 0) {
          break;
        }
        m = arrayOfInt[i];
        paramInt = 8;
        for (;;)
        {
          j = paramInt - 1;
          paramInt = i;
          if (j < 0) {
            break;
          }
          n = m >> j * 4 & 0xF;
          if (n <= 0)
          {
            paramInt = j;
            if (paramStringBuilder.length() <= k) {}
          }
          else
          {
            paramStringBuilder.append(Character.forDigit(n, 16));
            paramInt = j;
          }
        }
        arrayOfInt = this.words;
      }
      int j = MPN.chars_per_word(paramInt);
      int k = paramInt;
      int m = j;
      for (;;)
      {
        m -= 1;
        if (m <= 0) {
          break;
        }
        k *= paramInt;
      }
      int i1 = paramStringBuilder.length();
      do
      {
        i2 = MPN.divmod_1(arrayOfInt, arrayOfInt, i, k);
        m = i;
        while ((m > 0) && (arrayOfInt[(m - 1)] == 0)) {
          m -= 1;
        }
        n = j;
        i = i2;
        i2 = n - 1;
        if ((i2 >= 0) && ((m != 0) || (i != 0))) {
          break;
        }
        i = m;
      } while (m != 0);
      if (bool) {
        paramStringBuilder.append('-');
      }
      paramInt = paramStringBuilder.length() - 1;
      i = i1;
      while (i < paramInt)
      {
        char c = paramStringBuilder.charAt(i);
        paramStringBuilder.setCharAt(i, paramStringBuilder.charAt(paramInt));
        paramStringBuilder.setCharAt(paramInt, c);
        i += 1;
        paramInt -= 1;
      }
    }
    if (i < 0)
    {
      n = (int)((i & 0xFFFFFFFFFFFFFFFF) % paramInt);
      i /= paramInt;
    }
    for (;;)
    {
      paramStringBuilder.append(Character.forDigit(n, paramInt));
      n = i2;
      break;
      n = i % paramInt;
      i /= paramInt;
    }
  }
  
  public void getAbsolute(int[] paramArrayOfInt)
  {
    if (this.words == null)
    {
      i = 1;
      paramArrayOfInt[0] = this.ival;
      if (paramArrayOfInt[(i - 1)] < 0) {
        negate(paramArrayOfInt, paramArrayOfInt, i);
      }
      j = paramArrayOfInt.length;
      for (;;)
      {
        j -= 1;
        if (j <= i) {
          break;
        }
        paramArrayOfInt[j] = 0;
      }
    }
    int j = this.ival;
    int k;
    for (int i = j;; i = k)
    {
      k = i - 1;
      i = j;
      if (k < 0) {
        break;
      }
      paramArrayOfInt[k] = this.words[k];
    }
  }
  
  public int hashCode()
  {
    if (this.words == null) {
      return this.ival;
    }
    return this.words[0] + this.words[(this.ival - 1)];
  }
  
  public boolean inIntRange()
  {
    return inRange(-2147483648L, 2147483647L);
  }
  
  public boolean inLongRange()
  {
    return inRange(Long.MIN_VALUE, Long.MAX_VALUE);
  }
  
  public boolean inRange(long paramLong1, long paramLong2)
  {
    return (compare(this, paramLong1) >= 0) && (compare(this, paramLong2) <= 0);
  }
  
  public int intLength()
  {
    if (this.words == null) {
      return MPN.intLength(this.ival);
    }
    return MPN.intLength(this.words, this.ival);
  }
  
  public int intValue()
  {
    if (this.words == null) {
      return this.ival;
    }
    return this.words[0];
  }
  
  public final boolean isMinusOne()
  {
    return (this.words == null) && (this.ival == -1);
  }
  
  public final boolean isNegative()
  {
    if (this.words == null) {}
    for (int i = this.ival; i < 0; i = this.words[(this.ival - 1)]) {
      return true;
    }
    return false;
  }
  
  public final boolean isOdd()
  {
    boolean bool = false;
    if (this.words == null) {}
    for (int i = this.ival;; i = this.words[0])
    {
      if ((i & 0x1) != 0) {
        bool = true;
      }
      return bool;
    }
  }
  
  public final boolean isOne()
  {
    return (this.words == null) && (this.ival == 1);
  }
  
  public final boolean isZero()
  {
    return (this.words == null) && (this.ival == 0);
  }
  
  public long longValue()
  {
    if (this.words == null) {
      return this.ival;
    }
    if (this.ival == 1) {
      return this.words[0];
    }
    return (this.words[1] << 32) + (this.words[0] & 0xFFFFFFFF);
  }
  
  public Numeric mul(Object paramObject)
  {
    if ((paramObject instanceof IntNum)) {
      return times(this, (IntNum)paramObject);
    }
    if (!(paramObject instanceof Numeric)) {
      throw new IllegalArgumentException();
    }
    return ((Numeric)paramObject).mulReversed(this);
  }
  
  public Numeric neg()
  {
    return neg(this);
  }
  
  public final IntNum numerator()
  {
    return this;
  }
  
  public Numeric power(IntNum paramIntNum)
  {
    if (isOne()) {}
    do
    {
      do
      {
        return this;
        if (!isMinusOne()) {
          break;
        }
      } while (paramIntNum.isOdd());
      return one();
      if ((paramIntNum.words == null) && (paramIntNum.ival >= 0)) {
        return power(this, paramIntNum.ival);
      }
      if (!isZero()) {
        break;
      }
    } while (!paramIntNum.isNegative());
    return RatNum.infinity(-1);
    return super.power(paramIntNum);
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    int j = paramObjectInput.readInt();
    int i = j;
    if (j <= -1073741824)
    {
      i = j & 0x7FFFFFFF;
      if (i != 1) {
        break label44;
      }
      i = paramObjectInput.readInt();
    }
    for (;;)
    {
      this.ival = i;
      return;
      label44:
      int[] arrayOfInt = new int[i];
      j = i;
      for (;;)
      {
        j -= 1;
        if (j < 0) {
          break;
        }
        arrayOfInt[j] = paramObjectInput.readInt();
      }
      this.words = arrayOfInt;
    }
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    return canonicalize();
  }
  
  public void realloc(int paramInt)
  {
    if (paramInt == 0) {
      if (this.words != null)
      {
        if (this.ival > 0) {
          this.ival = this.words[0];
        }
        this.words = null;
      }
    }
    while ((this.words != null) && (this.words.length >= paramInt) && (this.words.length <= paramInt + 2)) {
      return;
    }
    int[] arrayOfInt = new int[paramInt];
    if (this.words == null)
    {
      arrayOfInt[0] = this.ival;
      this.ival = 1;
    }
    for (;;)
    {
      this.words = arrayOfInt;
      return;
      if (paramInt < this.ival) {
        this.ival = paramInt;
      }
      System.arraycopy(this.words, 0, arrayOfInt, 0, this.ival);
    }
  }
  
  public double roundToDouble(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    int k = intLength();
    int j = paramInt + (k - 1);
    if (j < 64461)
    {
      if (paramBoolean1) {
        return 0.0D;
      }
      return 0.0D;
    }
    if (j > 1023)
    {
      if (paramBoolean1) {
        return Double.NEGATIVE_INFINITY;
      }
      return Double.POSITIVE_INFINITY;
    }
    int i;
    int m;
    long l1;
    if (j >= 64514)
    {
      i = 53;
      m = k - (i + 1);
      if (m <= 0) {
        break label168;
      }
      if (this.words != null) {
        break label150;
      }
      l1 = this.ival >> m;
    }
    for (;;)
    {
      if ((j == 1023) && (l1 >> 1 == 9007199254740991L))
      {
        if ((paramBoolean2) || (checkBits(k - i)))
        {
          if (paramBoolean1)
          {
            return Double.NEGATIVE_INFINITY;
            i = j + 53 + 1022;
            break;
            label150:
            l1 = MPN.rshift_long(this.words, this.ival, m);
            continue;
            label168:
            l1 = longValue() << -m;
            continue;
          }
          return Double.POSITIVE_INFINITY;
        }
        if (paramBoolean1) {
          return -1.7976931348623157E308D;
        }
        return Double.MAX_VALUE;
      }
    }
    long l2 = l1;
    paramInt = j;
    if ((1L & l1) == 1L) {
      if (((0x2 & l1) != 2L) && (!paramBoolean2))
      {
        l2 = l1;
        paramInt = j;
        if (!checkBits(m)) {}
      }
      else
      {
        l1 += 2L;
        if ((0x40000000000000 & l1) == 0L) {
          break label316;
        }
        paramInt = j + 1;
        l2 = l1 >> 1;
      }
    }
    if (paramBoolean1)
    {
      l1 = Long.MIN_VALUE;
      label285:
      paramInt += 1023;
      if (paramInt > 0) {
        break label366;
      }
    }
    label316:
    label366:
    for (long l3 = 0L;; l3 = paramInt << 52)
    {
      return Double.longBitsToDouble(l1 | l3 | l2 >> 1 & 0xFFEFFFFFFFFFFFFF);
      l2 = l1;
      paramInt = j;
      if (i != 52) {
        break;
      }
      l2 = l1;
      paramInt = j;
      if ((0x20000000000000 & l1) == 0L) {
        break;
      }
      paramInt = j + 1;
      l2 = l1;
      break;
      l1 = 0L;
      break label285;
    }
  }
  
  public final void set(int paramInt)
  {
    this.words = null;
    this.ival = paramInt;
  }
  
  public final void set(long paramLong)
  {
    int i = (int)paramLong;
    if (i == paramLong)
    {
      this.ival = i;
      this.words = null;
      return;
    }
    realloc(2);
    this.words[0] = i;
    this.words[1] = ((int)(paramLong >> 32));
    this.ival = 2;
  }
  
  public final void set(IntNum paramIntNum)
  {
    if (paramIntNum.words == null) {
      set(paramIntNum.ival);
    }
    while (this == paramIntNum) {
      return;
    }
    realloc(paramIntNum.ival);
    System.arraycopy(paramIntNum.words, 0, this.words, 0, paramIntNum.ival);
    this.ival = paramIntNum.ival;
  }
  
  public final void set(int[] paramArrayOfInt, int paramInt)
  {
    this.ival = paramInt;
    this.words = paramArrayOfInt;
  }
  
  public final void setAdd(int paramInt)
  {
    setAdd(this, paramInt);
  }
  
  public void setAdd(IntNum paramIntNum, int paramInt)
  {
    if (paramIntNum.words == null)
    {
      set(paramIntNum.ival + paramInt);
      return;
    }
    int i = paramIntNum.ival;
    realloc(i + 1);
    long l1 = paramInt;
    paramInt = 0;
    while (paramInt < i)
    {
      l1 += (paramIntNum.words[paramInt] & 0xFFFFFFFF);
      this.words[paramInt] = ((int)l1);
      l1 >>= 32;
      paramInt += 1;
    }
    long l2 = l1;
    if (paramIntNum.words[(i - 1)] < 0) {
      l2 = l1 - 1L;
    }
    this.words[i] = ((int)l2);
    this.ival = wordsNeeded(this.words, i + 1);
  }
  
  void setInvert()
  {
    if (this.words == null)
    {
      this.ival ^= 0xFFFFFFFF;
      return;
    }
    int i = this.ival;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      this.words[i] ^= 0xFFFFFFFF;
    }
  }
  
  public final void setNegative()
  {
    setNegative(this);
  }
  
  public void setNegative(IntNum paramIntNum)
  {
    int j = paramIntNum.ival;
    if (paramIntNum.words == null)
    {
      if (j == Integer.MIN_VALUE)
      {
        set(-j);
        return;
      }
      set(-j);
      return;
    }
    realloc(j + 1);
    int i = j;
    if (negate(this.words, paramIntNum.words, j))
    {
      this.words[j] = 0;
      i = j + 1;
    }
    this.ival = i;
  }
  
  void setShift(IntNum paramIntNum, int paramInt)
  {
    if (paramInt > 0)
    {
      setShiftLeft(paramIntNum, paramInt);
      return;
    }
    setShiftRight(paramIntNum, -paramInt);
  }
  
  void setShiftLeft(IntNum paramIntNum, int paramInt)
  {
    int[] arrayOfInt;
    if (paramIntNum.words == null)
    {
      if (paramInt < 32)
      {
        set(paramIntNum.ival << paramInt);
        return;
      }
      arrayOfInt = new int[1];
      arrayOfInt[0] = paramIntNum.ival;
      i = 1;
    }
    int j;
    int m;
    for (paramIntNum = arrayOfInt;; paramIntNum = arrayOfInt)
    {
      j = paramInt >> 5;
      m = paramInt & 0x1F;
      k = i + j;
      if (m != 0) {
        break;
      }
      realloc(k);
      for (;;)
      {
        i -= 1;
        paramInt = k;
        if (i < 0) {
          break;
        }
        this.words[(i + j)] = paramIntNum[i];
      }
      arrayOfInt = paramIntNum.words;
      i = paramIntNum.ival;
    }
    paramInt = k + 1;
    realloc(paramInt);
    int i = MPN.lshift(this.words, j, paramIntNum, i, m);
    int k = 32 - m;
    this.words[(paramInt - 1)] = (i << k >> k);
    this.ival = paramInt;
    paramInt = j;
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      this.words[paramInt] = 0;
    }
  }
  
  void setShiftRight(IntNum paramIntNum, int paramInt)
  {
    int i = -1;
    if (paramIntNum.words == null) {
      if (paramInt < 32)
      {
        paramInt = paramIntNum.ival >> paramInt;
        set(paramInt);
      }
    }
    boolean bool;
    int k;
    do
    {
      return;
      paramInt = i;
      if (paramIntNum.ival < 0) {
        break;
      }
      paramInt = 0;
      break;
      if (paramInt == 0)
      {
        set(paramIntNum);
        return;
      }
      bool = paramIntNum.isNegative();
      int j = paramInt >> 5;
      paramInt &= 0x1F;
      k = paramIntNum.ival - j;
      if (k <= 0)
      {
        if (bool) {}
        for (;;)
        {
          set(i);
          return;
          i = 0;
        }
      }
      if ((this.words == null) || (this.words.length < k)) {
        realloc(k);
      }
      MPN.rshift0(this.words, paramIntNum.words, j, k, paramInt);
      this.ival = k;
    } while (!bool);
    paramIntNum = this.words;
    i = k - 1;
    paramIntNum[i] |= -2 << 31 - paramInt;
  }
  
  public int sign()
  {
    int i = this.ival;
    int[] arrayOfInt = this.words;
    if (arrayOfInt == null) {
      if (i <= 0) {}
    }
    int j;
    do
    {
      return 1;
      if (i < 0) {
        return -1;
      }
      return 0;
      i -= 1;
      j = arrayOfInt[i];
    } while (j > 0);
    if (j < 0) {
      return -1;
    }
    do
    {
      if (i == 0) {
        return 0;
      }
      j = i - 1;
      i = j;
    } while (arrayOfInt[j] == 0);
    return 1;
  }
  
  public IntNum toExactInt(int paramInt)
  {
    return this;
  }
  
  public RealNum toInt(int paramInt)
  {
    return this;
  }
  
  public String toString(int paramInt)
  {
    if (this.words == null) {
      return Integer.toString(this.ival, paramInt);
    }
    if (this.ival <= 2) {
      return Long.toString(longValue(), paramInt);
    }
    StringBuilder localStringBuilder = new StringBuilder(this.ival * (MPN.chars_per_word(paramInt) + 1));
    format(paramInt, localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    int j = 0;
    int i;
    if (this.words == null)
    {
      i = 1;
      if (i > 1) {
        break label95;
      }
      if (this.words != null) {
        break label58;
      }
      i = this.ival;
    }
    for (;;)
    {
      if (i < -1073741824) {
        break label78;
      }
      paramObjectOutput.writeInt(i);
      return;
      i = wordsNeeded(this.words, this.ival);
      break;
      label58:
      i = j;
      if (this.words.length != 0) {
        i = this.words[0];
      }
    }
    label78:
    paramObjectOutput.writeInt(-2147483647);
    paramObjectOutput.writeInt(i);
    return;
    label95:
    paramObjectOutput.writeInt(0x80000000 | i);
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      paramObjectOutput.writeInt(this.words[i]);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\IntNum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */