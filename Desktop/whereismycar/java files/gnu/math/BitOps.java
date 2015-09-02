package gnu.math;

public class BitOps
{
  static final byte[] bit4_count = { 0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4 };
  
  public static IntNum and(IntNum paramIntNum, int paramInt)
  {
    if (paramIntNum.words == null) {
      return IntNum.make(paramIntNum.ival & paramInt);
    }
    if (paramInt >= 0) {
      return IntNum.make(paramIntNum.words[0] & paramInt);
    }
    int i = paramIntNum.ival;
    int[] arrayOfInt = new int[i];
    arrayOfInt[0] = (paramIntNum.words[0] & paramInt);
    paramInt = i;
    for (;;)
    {
      paramInt -= 1;
      if (paramInt <= 0) {
        break;
      }
      arrayOfInt[paramInt] = paramIntNum.words[paramInt];
    }
    return IntNum.make(arrayOfInt, paramIntNum.ival);
  }
  
  public static IntNum and(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    if (paramIntNum2.words == null) {
      return and(paramIntNum1, paramIntNum2.ival);
    }
    if (paramIntNum1.words == null) {
      return and(paramIntNum2, paramIntNum1.ival);
    }
    IntNum localIntNum2 = paramIntNum1;
    IntNum localIntNum1 = paramIntNum2;
    if (paramIntNum1.ival < paramIntNum2.ival)
    {
      localIntNum1 = paramIntNum1;
      localIntNum2 = paramIntNum2;
    }
    if (localIntNum1.isNegative()) {}
    int k;
    for (int j = localIntNum2.ival;; j = localIntNum1.ival)
    {
      paramIntNum1 = new int[j];
      int i = 0;
      for (;;)
      {
        k = i;
        if (i >= localIntNum1.ival) {
          break;
        }
        paramIntNum1[i] = (localIntNum2.words[i] & localIntNum1.words[i]);
        i += 1;
      }
    }
    while (k < j)
    {
      paramIntNum1[k] = localIntNum2.words[k];
      k += 1;
    }
    return IntNum.make(paramIntNum1, j);
  }
  
  public static int bitCount(int paramInt)
  {
    int i = 0;
    while (paramInt != 0)
    {
      i += bit4_count[(paramInt & 0xF)];
      paramInt >>>= 4;
    }
    return i;
  }
  
  public static int bitCount(IntNum paramIntNum)
  {
    int[] arrayOfInt = paramIntNum.words;
    int j;
    if (arrayOfInt == null) {
      j = 1;
    }
    for (int i = bitCount(paramIntNum.ival);; i = bitCount(arrayOfInt, j))
    {
      int k = i;
      if (paramIntNum.isNegative()) {
        k = j * 32 - i;
      }
      return k;
      j = paramIntNum.ival;
    }
  }
  
  public static int bitCount(int[] paramArrayOfInt, int paramInt)
  {
    int j = 0;
    int i = paramInt;
    paramInt = j;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      paramInt += bitCount(paramArrayOfInt[i]);
    }
    return paramInt;
  }
  
  public static IntNum bitOp(int paramInt, IntNum paramIntNum1, IntNum paramIntNum2)
  {
    IntNum localIntNum = paramIntNum1;
    switch (paramInt)
    {
    default: 
      localIntNum = new IntNum();
      setBitOp(localIntNum, paramInt, paramIntNum1, paramIntNum2);
      localIntNum = localIntNum.canonicalize();
    case 3: 
      return localIntNum;
    case 0: 
      return IntNum.zero();
    case 1: 
      return and(paramIntNum1, paramIntNum2);
    case 5: 
      return paramIntNum2;
    }
    return IntNum.minusOne();
  }
  
  public static boolean bitValue(IntNum paramIntNum, int paramInt)
  {
    int i = paramIntNum.ival;
    if (paramIntNum.words == null) {
      if (paramInt >= 32) {
        if (i >= 0) {}
      }
    }
    int j;
    do
    {
      do
      {
        do
        {
          return true;
          return false;
        } while ((i >> paramInt & 0x1) != 0);
        return false;
        j = paramInt >> 5;
        if (j < i) {
          break;
        }
      } while (paramIntNum.words[(i - 1)] < 0);
      return false;
    } while ((paramIntNum.words[j] >> paramInt & 0x1) != 0);
    return false;
  }
  
  static int[] dataBufferFor(IntNum paramIntNum, int paramInt)
  {
    int i = paramIntNum.ival;
    int j = paramInt + 1 >> 5;
    int[] arrayOfInt;
    if (paramIntNum.words == null)
    {
      paramInt = j;
      if (j == 0) {
        paramInt = 1;
      }
      arrayOfInt = new int[paramInt];
      arrayOfInt[0] = i;
      paramIntNum = arrayOfInt;
      if (i < 0)
      {
        i = 1;
        for (;;)
        {
          paramIntNum = arrayOfInt;
          if (i >= paramInt) {
            break;
          }
          arrayOfInt[i] = -1;
          i += 1;
        }
      }
    }
    else
    {
      j = paramInt + 1 >> 5;
      if (j > i) {}
      for (paramInt = j;; paramInt = i)
      {
        arrayOfInt = new int[paramInt];
        paramInt = i;
        for (;;)
        {
          paramInt -= 1;
          if (paramInt < 0) {
            break;
          }
          arrayOfInt[paramInt] = paramIntNum.words[paramInt];
        }
      }
      paramIntNum = arrayOfInt;
      if (arrayOfInt[(i - 1)] < 0) {
        for (;;)
        {
          paramIntNum = arrayOfInt;
          if (i >= j) {
            break;
          }
          arrayOfInt[i] = -1;
          i += 1;
        }
      }
    }
    return paramIntNum;
  }
  
  public static IntNum extract(IntNum paramIntNum, int paramInt1, int paramInt2)
  {
    if (paramInt2 < 32) {
      if (paramIntNum.words == null)
      {
        i = paramIntNum.ival;
        localObject = IntNum.make(((-1 << paramInt2 ^ 0xFFFFFFFF) & i) >> paramInt1);
      }
    }
    do
    {
      return (IntNum)localObject;
      i = paramIntNum.words[0];
      break;
      if (paramIntNum.words != null) {
        break label189;
      }
      if (paramIntNum.ival >= 0)
      {
        if (paramInt1 >= 31) {}
        for (paramInt1 = 0;; paramInt1 = paramIntNum.ival >> paramInt1) {
          return IntNum.make(paramInt1);
        }
      }
      i = 1;
      boolean bool = paramIntNum.isNegative();
      if (paramInt2 <= i * 32) {
        break label198;
      }
      k = i * 32;
      j = i;
      paramInt2 = k;
      if (bool) {
        break label133;
      }
      localObject = paramIntNum;
    } while (paramInt1 == 0);
    paramInt2 = k;
    int j = i;
    label133:
    int i = paramInt2 - paramInt1;
    if (i < 64)
    {
      if (paramIntNum.words == null)
      {
        paramInt2 = paramIntNum.ival;
        if (paramInt1 >= 32) {
          paramInt1 = 31;
        }
      }
      label166:
      for (long l = paramInt2 >> paramInt1;; l = MPN.rshift_long(paramIntNum.words, j, paramInt1))
      {
        return IntNum.make((-1L << i ^ 0xFFFFFFFFFFFFFFFF) & l);
        label189:
        i = paramIntNum.ival;
        break;
        label198:
        j = paramInt2 + 31 >> 5;
        break label133;
        break label166;
      }
    }
    int k = paramInt1 >> 5;
    Object localObject = new int[(paramInt2 >> 5) + 1 - k];
    if (paramIntNum.words == null) {
      if (paramInt1 >= 32)
      {
        paramInt1 = -1;
        localObject[0] = paramInt1;
      }
    }
    for (;;)
    {
      paramInt1 = i >> 5;
      localObject[paramInt1] &= (-1 << i ^ 0xFFFFFFFF);
      return IntNum.make((int[])localObject, paramInt1 + 1);
      paramInt1 = paramIntNum.ival >> paramInt1;
      break;
      MPN.rshift0((int[])localObject, paramIntNum.words, k, j - k, paramInt1 & 0x1F);
    }
  }
  
  public static IntNum ior(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    return bitOp(7, paramIntNum1, paramIntNum2);
  }
  
  public static int lowestBitSet(int paramInt)
  {
    int i;
    if (paramInt == 0) {
      i = -1;
    }
    int k;
    do
    {
      return i;
      i = 0;
      int j = paramInt;
      for (;;)
      {
        paramInt = i;
        k = j;
        if ((j & 0xFF) != 0) {
          break;
        }
        j >>>= 8;
        i += 8;
      }
      while ((k & 0x3) == 0)
      {
        k >>>= 2;
        paramInt += 2;
      }
      i = paramInt;
    } while ((k & 0x1) != 0);
    return paramInt + 1;
  }
  
  public static int lowestBitSet(IntNum paramIntNum)
  {
    int[] arrayOfInt = paramIntNum.words;
    if (arrayOfInt == null) {
      return lowestBitSet(paramIntNum.ival);
    }
    int i = paramIntNum.ival;
    while (i < 0)
    {
      int j = lowestBitSet(arrayOfInt[0]);
      if (j >= 0) {
        return j + 0;
      }
    }
    return -1;
  }
  
  public static IntNum not(IntNum paramIntNum)
  {
    return bitOp(12, paramIntNum, IntNum.zero());
  }
  
  public static IntNum reverseBits(IntNum paramIntNum, int paramInt1, int paramInt2)
  {
    int i = paramIntNum.ival;
    if ((paramIntNum.words == null) && (paramInt2 < 63))
    {
      long l = i;
      paramInt2 -= 1;
      while (paramInt1 < paramInt2)
      {
        l = (l >> paramInt1 & 1L) << paramInt2 | l & ((1L << paramInt1 | 1L << paramInt2) ^ 0xFFFFFFFFFFFFFFFF) | (l >> paramInt2 & 1L) << paramInt1;
        paramInt1 += 1;
        paramInt2 -= 1;
      }
      return IntNum.make(l);
    }
    paramIntNum = dataBufferFor(paramIntNum, paramInt2 - 1);
    i = paramInt1;
    paramInt1 = paramInt2 - 1;
    paramInt2 = i;
    if (paramInt2 < paramInt1)
    {
      int j = paramInt2 >> 5;
      int k = paramInt1 >> 5;
      i = paramIntNum[j];
      int m = i >> paramInt2 & 0x1;
      if (j == k) {
        i = m << paramInt1 | (int)(i & ((1L << paramInt2 | 1L << paramInt1) ^ 0xFFFFFFFFFFFFFFFF)) | (i >> paramInt1 & 0x1) << paramInt2;
      }
      for (;;)
      {
        paramIntNum[j] = i;
        paramInt2 += 1;
        paramInt1 -= 1;
        break;
        int n = paramIntNum[k];
        i = i & (1 << (paramInt2 & 0x1F) ^ 0xFFFFFFFF) | (n >> (paramInt1 & 0x1F) & 0x1) << (paramInt2 & 0x1F);
        paramIntNum[k] = (n & (1 << (paramInt1 & 0x1F) ^ 0xFFFFFFFF) | m << (paramInt1 & 0x1F));
      }
    }
    return IntNum.make(paramIntNum, paramIntNum.length);
  }
  
  public static void setBitOp(IntNum paramIntNum1, int paramInt, IntNum paramIntNum2, IntNum paramIntNum3)
  {
    IntNum localIntNum2;
    IntNum localIntNum1;
    int k;
    label16:
    int j;
    label33:
    int i;
    int m;
    label51:
    int i1;
    if (paramIntNum3.words == null)
    {
      localIntNum2 = paramIntNum3;
      localIntNum1 = paramIntNum2;
      k = paramInt;
      if (localIntNum2.words != null) {
        break label244;
      }
      paramInt = localIntNum2.ival;
      j = 1;
      if (localIntNum1.words != null) {
        break label262;
      }
      i = localIntNum1.ival;
      m = 1;
      if (m > 1) {
        paramIntNum1.realloc(m);
      }
      paramIntNum2 = paramIntNum1.words;
      i1 = 0;
    }
    label196:
    label244:
    label262:
    label293:
    int i2;
    int n;
    switch (k)
    {
    default: 
      i = -1;
      paramInt = 0;
    case 0: 
      for (j = i1;; j = i1)
      {
        if (paramInt + 1 == m) {
          j = 0;
        }
        switch (j)
        {
        default: 
          paramIntNum1.ival = paramInt;
          return;
          if (paramIntNum2.words != null)
          {
            k = paramInt;
            localIntNum1 = paramIntNum2;
            localIntNum2 = paramIntNum3;
            if (paramIntNum2.ival >= paramIntNum3.ival) {
              break label16;
            }
          }
          k = swappedOp(paramInt);
          localIntNum1 = paramIntNum3;
          localIntNum2 = paramIntNum2;
          break label16;
          paramInt = localIntNum2.words[0];
          j = localIntNum2.ival;
          break label33;
          i = localIntNum1.words[0];
          m = localIntNum1.ival;
          break label51;
          i = 0;
          paramInt = 0;
        }
      }
      paramInt = k + 1;
      paramIntNum2[k] = i2;
      i = localIntNum1.words[paramInt];
      n = localIntNum2.words[paramInt];
      k = paramInt;
    }
    for (;;)
    {
      i2 = i & n;
      if (k + 1 < j) {
        break label293;
      }
      j = i1;
      paramInt = k;
      i = i2;
      if (n >= 0) {
        break;
      }
      j = 1;
      paramInt = k;
      i = i2;
      break;
      label370:
      paramInt = k + 1;
      paramIntNum2[k] = i2;
      i = localIntNum1.words[paramInt];
      n = localIntNum2.words[paramInt];
      k = paramInt;
      for (;;)
      {
        i2 = i & (n ^ 0xFFFFFFFF);
        if (k + 1 < j) {
          break label370;
        }
        j = i1;
        paramInt = k;
        i = i2;
        if (n < 0) {
          break;
        }
        j = 1;
        paramInt = k;
        i = i2;
        break;
        j = 1;
        paramInt = 0;
        break;
        label457:
        paramInt = k + 1;
        paramIntNum2[k] = i2;
        i = localIntNum1.words[paramInt];
        n = localIntNum2.words[paramInt];
        k = paramInt;
        for (;;)
        {
          i2 = (i ^ 0xFFFFFFFF) & n;
          if (k + 1 < j) {
            break label457;
          }
          j = i1;
          paramInt = k;
          i = i2;
          if (n >= 0) {
            break;
          }
          j = 2;
          paramInt = k;
          i = i2;
          break;
          label536:
          paramInt = i + 1;
          paramIntNum2[i] = k;
          i = localIntNum1.words[paramInt];
          k = localIntNum2.words[paramInt];
          i = paramInt;
          paramInt = k;
          for (;;)
          {
            k = paramInt;
            if (i + 1 < j) {
              break label536;
            }
            j = i1;
            paramInt = i;
            i = k;
            break;
            label597:
            paramInt = k + 1;
            paramIntNum2[k] = i;
            i = localIntNum1.words[paramInt];
            n = localIntNum2.words[paramInt];
            k = paramInt;
            paramInt = n;
            for (;;)
            {
              i ^= paramInt;
              if (k + 1 < j) {
                break label597;
              }
              if (paramInt < 0) {}
              for (j = 2;; j = 1)
              {
                paramInt = k;
                break;
              }
              label666:
              paramInt = k + 1;
              paramIntNum2[k] = i2;
              i = localIntNum1.words[paramInt];
              n = localIntNum2.words[paramInt];
              k = paramInt;
              for (;;)
              {
                i2 = i | n;
                if (k + 1 < j) {
                  break label666;
                }
                j = i1;
                paramInt = k;
                i = i2;
                if (n < 0) {
                  break;
                }
                j = 1;
                paramInt = k;
                i = i2;
                break;
                label743:
                paramInt = k + 1;
                paramIntNum2[k] = i2;
                i = localIntNum1.words[paramInt];
                n = localIntNum2.words[paramInt];
                k = paramInt;
                for (;;)
                {
                  i2 = (i | n) ^ 0xFFFFFFFF;
                  if (k + 1 < j) {
                    break label743;
                  }
                  j = i1;
                  paramInt = k;
                  i = i2;
                  if (n < 0) {
                    break;
                  }
                  j = 2;
                  paramInt = k;
                  i = i2;
                  break;
                  label822:
                  paramInt = k + 1;
                  paramIntNum2[k] = i;
                  i = localIntNum1.words[paramInt];
                  n = localIntNum2.words[paramInt];
                  k = paramInt;
                  paramInt = n;
                  for (;;)
                  {
                    i = i ^ paramInt ^ 0xFFFFFFFF;
                    if (k + 1 < j) {
                      break label822;
                    }
                    if (paramInt >= 0) {}
                    for (j = 2;; j = 1)
                    {
                      paramInt = k;
                      break;
                    }
                    label893:
                    paramInt = i + 1;
                    paramIntNum2[i] = k;
                    i = localIntNum1.words[paramInt];
                    k = localIntNum2.words[paramInt];
                    i = paramInt;
                    paramInt = k;
                    for (;;)
                    {
                      k = paramInt ^ 0xFFFFFFFF;
                      if (i + 1 < j) {
                        break label893;
                      }
                      j = i1;
                      paramInt = i;
                      i = k;
                      break;
                      label956:
                      paramInt = k + 1;
                      paramIntNum2[k] = i2;
                      i = localIntNum1.words[paramInt];
                      n = localIntNum2.words[paramInt];
                      k = paramInt;
                      for (;;)
                      {
                        i2 = i | n ^ 0xFFFFFFFF;
                        if (k + 1 < j) {
                          break label956;
                        }
                        j = i1;
                        paramInt = k;
                        i = i2;
                        if (n >= 0) {
                          break;
                        }
                        j = 1;
                        paramInt = k;
                        i = i2;
                        break;
                        i ^= 0xFFFFFFFF;
                        j = 2;
                        paramInt = 0;
                        break;
                        label1049:
                        paramInt = k + 1;
                        paramIntNum2[k] = i2;
                        i = localIntNum1.words[paramInt];
                        n = localIntNum2.words[paramInt];
                        k = paramInt;
                        for (;;)
                        {
                          i2 = i ^ 0xFFFFFFFF | n;
                          if (k + 1 < j) {
                            break label1049;
                          }
                          j = i1;
                          paramInt = k;
                          i = i2;
                          if (n < 0) {
                            break;
                          }
                          j = 2;
                          paramInt = k;
                          i = i2;
                          break;
                          label1128:
                          paramInt = k + 1;
                          paramIntNum2[k] = i2;
                          i = localIntNum1.words[paramInt];
                          n = localIntNum2.words[paramInt];
                          k = paramInt;
                          for (;;)
                          {
                            i2 = i & n ^ 0xFFFFFFFF;
                            if (k + 1 < j) {
                              break label1128;
                            }
                            j = i1;
                            paramInt = k;
                            i = i2;
                            if (n >= 0) {
                              break;
                            }
                            j = 2;
                            paramInt = k;
                            i = i2;
                            break;
                            if ((paramInt == 0) && (paramIntNum2 == null))
                            {
                              paramIntNum1.ival = i;
                              return;
                            }
                            j = paramInt + 1;
                            paramIntNum2[paramInt] = i;
                            paramInt = j;
                            break label196;
                            paramIntNum2[paramInt] = i;
                            for (;;)
                            {
                              i = paramInt + 1;
                              paramInt = i;
                              if (i >= m) {
                                break;
                              }
                              paramIntNum2[i] = localIntNum1.words[i];
                              paramInt = i;
                            }
                            paramIntNum2[paramInt] = i;
                            for (;;)
                            {
                              i = paramInt + 1;
                              paramInt = i;
                              if (i >= m) {
                                break;
                              }
                              paramIntNum2[i] = (localIntNum1.words[i] ^ 0xFFFFFFFF);
                              paramInt = i;
                            }
                            k = 0;
                            n = paramInt;
                          }
                          k = 0;
                          n = paramInt;
                        }
                        k = 0;
                        n = paramInt;
                      }
                      i = 0;
                    }
                    k = 0;
                  }
                  k = 0;
                  n = paramInt;
                }
                k = 0;
                n = paramInt;
              }
              k = 0;
            }
            i = 0;
          }
          k = 0;
          n = paramInt;
        }
        k = 0;
        n = paramInt;
      }
      k = 0;
      n = paramInt;
    }
  }
  
  public static IntNum setBitValue(IntNum paramIntNum, int paramInt1, int paramInt2)
  {
    int i = 31;
    int j = paramInt2 & 0x1;
    int k = paramIntNum.ival;
    if (paramIntNum.words == null)
    {
      paramInt2 = i;
      if (paramInt1 < 31) {
        paramInt2 = paramInt1;
      }
      if ((k >> paramInt2 & 0x1) != j) {}
    }
    for (;;)
    {
      return paramIntNum;
      if (paramInt1 < 63)
      {
        long l = k;
        return IntNum.make(1 << paramInt1 ^ l);
        paramInt2 = paramInt1 >> 5;
        if (paramInt2 < k) {
          break label131;
        }
        if (paramIntNum.words[(k - 1)] >= 0) {
          break label126;
        }
        paramInt2 = 1;
      }
      while (paramInt2 != j)
      {
        paramIntNum = dataBufferFor(paramIntNum, paramInt1);
        paramInt2 = paramInt1 >> 5;
        paramIntNum[paramInt2] = (1 << (paramInt1 & 0x1F) ^ paramIntNum[paramInt2]);
        return IntNum.make(paramIntNum, paramIntNum.length);
        label126:
        paramInt2 = 0;
        continue;
        label131:
        paramInt2 = paramIntNum.words[paramInt2] >> paramInt1 & 0x1;
      }
    }
  }
  
  public static int swappedOp(int paramInt)
  {
    return "\000\001\004\005\002\003\006\007\b\t\f\r\n\013\016\017".charAt(paramInt);
  }
  
  public static boolean test(IntNum paramIntNum, int paramInt)
  {
    boolean bool = false;
    if (paramIntNum.words == null) {
      return (paramIntNum.ival & paramInt) != 0;
    }
    if ((paramInt < 0) || ((paramIntNum.words[0] & paramInt) != 0)) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean test(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    if (paramIntNum2.words == null) {
      return test(paramIntNum1, paramIntNum2.ival);
    }
    if (paramIntNum1.words == null) {
      return test(paramIntNum2, paramIntNum1.ival);
    }
    IntNum localIntNum2 = paramIntNum1;
    IntNum localIntNum1 = paramIntNum2;
    if (paramIntNum1.ival < paramIntNum2.ival)
    {
      localIntNum1 = paramIntNum1;
      localIntNum2 = paramIntNum2;
    }
    int i = 0;
    while (i < localIntNum1.ival)
    {
      if ((localIntNum2.words[i] & localIntNum1.words[i]) != 0) {
        return true;
      }
      i += 1;
    }
    return localIntNum1.isNegative();
  }
  
  public static IntNum xor(IntNum paramIntNum1, IntNum paramIntNum2)
  {
    return bitOp(6, paramIntNum1, paramIntNum2);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\BitOps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */