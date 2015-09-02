package gnu.math;

class MPN
{
  public static int add_1(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2)
  {
    long l = paramInt2 & 0xFFFFFFFF;
    paramInt2 = 0;
    while (paramInt2 < paramInt1)
    {
      l += (paramArrayOfInt2[paramInt2] & 0xFFFFFFFF);
      paramArrayOfInt1[paramInt2] = ((int)l);
      l >>= 32;
      paramInt2 += 1;
    }
    return (int)l;
  }
  
  public static int add_n(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int paramInt)
  {
    long l = 0L;
    int i = 0;
    while (i < paramInt)
    {
      l += (paramArrayOfInt2[i] & 0xFFFFFFFF) + (paramArrayOfInt3[i] & 0xFFFFFFFF);
      paramArrayOfInt1[i] = ((int)l);
      l >>>= 32;
      i += 1;
    }
    return (int)l;
  }
  
  public static int chars_per_word(int paramInt)
  {
    int i = 16;
    if (paramInt < 10)
    {
      if (paramInt < 8)
      {
        if (paramInt <= 2) {
          i = 32;
        }
        do
        {
          return i;
          if (paramInt == 3) {
            return 20;
          }
        } while (paramInt == 4);
        return 18 - paramInt;
      }
      return 10;
    }
    if (paramInt < 12) {
      return 9;
    }
    if (paramInt <= 16) {
      return 8;
    }
    if (paramInt <= 23) {
      return 7;
    }
    if (paramInt <= 40) {
      return 6;
    }
    if (paramInt <= 256) {
      return 4;
    }
    return 1;
  }
  
  public static int cmp(int[] paramArrayOfInt1, int paramInt1, int[] paramArrayOfInt2, int paramInt2)
  {
    if (paramInt1 > paramInt2) {
      return 1;
    }
    if (paramInt1 < paramInt2) {
      return -1;
    }
    return cmp(paramArrayOfInt1, paramArrayOfInt2, paramInt1);
  }
  
  public static int cmp(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    int i;
    int j;
    do
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      i = paramArrayOfInt1[paramInt];
      j = paramArrayOfInt2[paramInt];
    } while (i == j);
    if ((i ^ 0x80000000) > (0x80000000 ^ j)) {
      return 1;
    }
    return -1;
    return 0;
  }
  
  public static int count_leading_zeros(int paramInt)
  {
    if (paramInt == 0) {
      k = 32;
    }
    int i;
    int j;
    do
    {
      return k;
      k = 0;
      i = 16;
      j = paramInt;
      paramInt = k;
      k = paramInt;
    } while (i <= 0);
    int k = j >>> i;
    if (k == 0) {
      paramInt += i;
    }
    for (;;)
    {
      i >>= 1;
      break;
      j = k;
    }
  }
  
  public static void divide(int[] paramArrayOfInt1, int paramInt1, int[] paramArrayOfInt2, int paramInt2)
  {
    int i = paramInt1;
    label191:
    do
    {
      int j;
      long l;
      if (paramArrayOfInt1[i] == paramArrayOfInt2[(paramInt2 - 1)])
      {
        paramInt1 = -1;
        j = paramInt1;
        if (paramInt1 != 0)
        {
          j = submul_1(paramArrayOfInt1, i - paramInt2, paramArrayOfInt2, paramInt2, paramInt1);
          l = (paramArrayOfInt1[i] & 0xFFFFFFFF) - (j & 0xFFFFFFFF);
        }
      }
      else
      {
        for (;;)
        {
          j = paramInt1;
          if (l == 0L) {
            break label191;
          }
          j = paramInt1 - 1;
          l = 0L;
          paramInt1 = 0;
          for (;;)
          {
            if (paramInt1 < paramInt2)
            {
              l += (paramArrayOfInt1[(i - paramInt2 + paramInt1)] & 0xFFFFFFFF) + (paramArrayOfInt2[paramInt1] & 0xFFFFFFFF);
              paramArrayOfInt1[(i - paramInt2 + paramInt1)] = ((int)l);
              l >>>= 32;
              paramInt1 += 1;
              continue;
              paramInt1 = (int)udiv_qrnnd((paramArrayOfInt1[i] << 32) + (paramArrayOfInt1[(i - 1)] & 0xFFFFFFFF), paramArrayOfInt2[(paramInt2 - 1)]);
              break;
            }
          }
          paramArrayOfInt1[i] = ((int)(paramArrayOfInt1[i] + l));
          l -= 1L;
          paramInt1 = j;
        }
      }
      paramArrayOfInt1[i] = j;
      paramInt1 = i - 1;
      i = paramInt1;
    } while (paramInt1 >= paramInt2);
  }
  
  public static int divmod_1(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2)
  {
    paramInt1 -= 1;
    long l = paramArrayOfInt2[paramInt1];
    if ((0xFFFFFFFF & l) >= (paramInt2 & 0xFFFFFFFF)) {
      l = 0L;
    }
    while (paramInt1 >= 0)
    {
      l = udiv_qrnnd(0xFFFFFFFF00000000 & l | paramArrayOfInt2[paramInt1] & 0xFFFFFFFF, paramInt2);
      paramArrayOfInt1[paramInt1] = ((int)l);
      paramInt1 -= 1;
      continue;
      paramArrayOfInt1[paramInt1] = 0;
      l <<= 32;
      paramInt1 -= 1;
    }
    return (int)(l >> 32);
  }
  
  static int findLowestBit(int paramInt)
  {
    int j = 0;
    int i = paramInt;
    paramInt = j;
    while ((i & 0xF) == 0)
    {
      i >>= 4;
      paramInt += 4;
    }
    j = paramInt;
    int k = i;
    if ((i & 0x3) == 0)
    {
      k = i >> 2;
      j = paramInt + 2;
    }
    paramInt = j;
    if ((k & 0x1) == 0) {
      paramInt = j + 1;
    }
    return paramInt;
  }
  
  static int findLowestBit(int[] paramArrayOfInt)
  {
    int i = 0;
    for (;;)
    {
      if (paramArrayOfInt[i] != 0) {
        return i * 32 + findLowestBit(paramArrayOfInt[i]);
      }
      i += 1;
    }
  }
  
  public static int gcd(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    int i = 0;
    int j = paramArrayOfInt1[i] | paramArrayOfInt2[i];
    int n;
    Object localObject2;
    Object localObject1;
    if (j != 0)
    {
      n = findLowestBit(j);
      paramInt -= i;
      rshift0(paramArrayOfInt1, paramArrayOfInt1, i, paramInt, n);
      rshift0(paramArrayOfInt2, paramArrayOfInt2, i, paramInt, n);
      if ((paramArrayOfInt1[0] & 0x1) == 0) {
        break label95;
      }
      localObject2 = paramArrayOfInt1;
      localObject1 = paramArrayOfInt2;
      paramArrayOfInt2 = (int[])localObject2;
    }
    int k;
    for (;;)
    {
      k = 0;
      while (localObject1[k] == 0) {
        k += 1;
      }
      i += 1;
      break;
      label95:
      localObject1 = paramArrayOfInt1;
    }
    if (k > 0)
    {
      j = 0;
      int m;
      for (;;)
      {
        m = j;
        if (j >= paramInt - k) {
          break;
        }
        localObject1[j] = localObject1[(j + k)];
        j += 1;
      }
      while (m < paramInt)
      {
        localObject1[m] = 0;
        m += 1;
      }
    }
    j = findLowestBit(localObject1[0]);
    if (j > 0) {
      rshift((int[])localObject1, (int[])localObject1, 0, paramInt, j);
    }
    j = cmp(paramArrayOfInt2, (int[])localObject1, paramInt);
    if (j == 0)
    {
      j = paramInt;
      if (i + n <= 0) {
        break label395;
      }
      if (n > 0)
      {
        k = lshift(paramArrayOfInt1, i, paramArrayOfInt1, paramInt, n);
        j = paramInt;
        if (k != 0)
        {
          paramArrayOfInt1[(paramInt + i)] = k;
          j = paramInt + 1;
        }
        paramInt = i;
        for (;;)
        {
          paramInt -= 1;
          if (paramInt < 0) {
            break;
          }
          paramArrayOfInt1[paramInt] = 0;
        }
      }
    }
    else
    {
      Object localObject3;
      if (j > 0)
      {
        sub_n(paramArrayOfInt2, paramArrayOfInt2, (int[])localObject1, paramInt);
        j = paramInt;
        localObject2 = paramArrayOfInt2;
        localObject3 = localObject1;
      }
      for (;;)
      {
        paramArrayOfInt2 = (int[])localObject3;
        localObject1 = localObject2;
        paramInt = j;
        if (localObject3[(j - 1)] != 0) {
          break;
        }
        paramArrayOfInt2 = (int[])localObject3;
        localObject1 = localObject2;
        paramInt = j;
        if (localObject2[(j - 1)] != 0) {
          break;
        }
        j -= 1;
        continue;
        sub_n((int[])localObject1, (int[])localObject1, paramArrayOfInt2, paramInt);
        localObject3 = paramArrayOfInt2;
        localObject2 = localObject1;
        j = paramInt;
      }
    }
    for (j = paramInt;; j = k)
    {
      k = j - 1;
      j = paramInt;
      if (k < 0) {
        break;
      }
      paramArrayOfInt1[(k + i)] = paramArrayOfInt1[k];
    }
    j += i;
    label395:
    return j;
  }
  
  public static int intLength(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = paramInt ^ 0xFFFFFFFF;
    }
    return 32 - count_leading_zeros(i);
  }
  
  public static int intLength(int[] paramArrayOfInt, int paramInt)
  {
    paramInt -= 1;
    return intLength(paramArrayOfInt[paramInt]) + paramInt * 32;
  }
  
  public static int lshift(int[] paramArrayOfInt1, int paramInt1, int[] paramArrayOfInt2, int paramInt2, int paramInt3)
  {
    int k = 32 - paramInt3;
    paramInt2 -= 1;
    int i = paramArrayOfInt2[paramInt2];
    int m = paramInt1 + 1;
    int j;
    for (paramInt1 = i;; paramInt1 = j)
    {
      paramInt2 -= 1;
      if (paramInt2 < 0) {
        break;
      }
      j = paramArrayOfInt2[paramInt2];
      paramArrayOfInt1[(m + paramInt2)] = (paramInt1 << paramInt3 | j >>> k);
    }
    paramArrayOfInt1[(m + paramInt2)] = (paramInt1 << paramInt3);
    return i >>> k;
  }
  
  public static void mul(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int[] paramArrayOfInt3, int paramInt2)
  {
    paramArrayOfInt1[paramInt1] = mul_1(paramArrayOfInt1, paramArrayOfInt2, paramInt1, paramArrayOfInt3[0]);
    int i = 1;
    while (i < paramInt2)
    {
      long l2 = paramArrayOfInt3[i];
      long l1 = 0L;
      int j = 0;
      while (j < paramInt1)
      {
        l1 += (paramArrayOfInt2[j] & 0xFFFFFFFF) * (l2 & 0xFFFFFFFF) + (paramArrayOfInt1[(i + j)] & 0xFFFFFFFF);
        paramArrayOfInt1[(i + j)] = ((int)l1);
        l1 >>>= 32;
        j += 1;
      }
      paramArrayOfInt1[(i + paramInt1)] = ((int)l1);
      i += 1;
    }
  }
  
  public static int mul_1(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2)
  {
    long l2 = paramInt2;
    long l1 = 0L;
    paramInt2 = 0;
    while (paramInt2 < paramInt1)
    {
      l1 += (paramArrayOfInt2[paramInt2] & 0xFFFFFFFF) * (l2 & 0xFFFFFFFF);
      paramArrayOfInt1[paramInt2] = ((int)l1);
      l1 >>>= 32;
      paramInt2 += 1;
    }
    return (int)l1;
  }
  
  public static int rshift(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3)
  {
    int n = 32 - paramInt3;
    int k = paramArrayOfInt2[paramInt1];
    int j = 1;
    int i = k;
    while (j < paramInt2)
    {
      int m = paramArrayOfInt2[(paramInt1 + j)];
      paramArrayOfInt1[(j - 1)] = (i >>> paramInt3 | m << n);
      i = m;
      j += 1;
    }
    paramArrayOfInt1[(j - 1)] = (i >>> paramInt3);
    return k << n;
  }
  
  public static void rshift0(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt3 > 0) {
      rshift(paramArrayOfInt1, paramArrayOfInt2, paramInt1, paramInt2, paramInt3);
    }
    for (;;)
    {
      return;
      paramInt3 = 0;
      while (paramInt3 < paramInt2)
      {
        paramArrayOfInt1[paramInt3] = paramArrayOfInt2[(paramInt3 + paramInt1)];
        paramInt3 += 1;
      }
    }
  }
  
  public static long rshift_long(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int j = paramInt2 >> 5;
    int n = paramInt2 & 0x1F;
    int i;
    label29:
    int i1;
    label44:
    int m;
    int k;
    if (paramArrayOfInt[(paramInt1 - 1)] < 0)
    {
      paramInt2 = -1;
      if (j < paramInt1) {
        break label120;
      }
      i = paramInt2;
      i1 = j + 1;
      if (i1 < paramInt1) {
        break label128;
      }
      j = paramInt2;
      m = i;
      k = j;
      if (n != 0)
      {
        k = i1 + 1;
        if (k < paramInt1) {
          break label137;
        }
      }
    }
    label120:
    label128:
    label137:
    for (paramInt1 = paramInt2;; paramInt1 = paramArrayOfInt[k])
    {
      m = i >>> n | j << 32 - n;
      k = j >>> n | paramInt1 << 32 - n;
      return k << 32 | m & 0xFFFFFFFF;
      paramInt2 = 0;
      break;
      i = paramArrayOfInt[j];
      break label29;
      j = paramArrayOfInt[i1];
      break label44;
    }
  }
  
  public static int set_str(int[] paramArrayOfInt, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int m;
    int j;
    int i;
    int k;
    int n;
    if ((paramInt2 - 1 & paramInt2) == 0)
    {
      m = 0;
      j = 0;
      for (;;)
      {
        paramInt2 >>= 1;
        if (paramInt2 == 0) {
          break;
        }
        j += 1;
      }
      i = 0;
      k = paramInt1;
      paramInt1 = 0;
      paramInt2 = m;
      k -= 1;
      if (k >= 0)
      {
        n = paramArrayOfByte[k];
        m = i | n << paramInt2;
        paramInt2 += j;
        if (paramInt2 < 32) {
          break label290;
        }
        i = paramInt1 + 1;
        paramArrayOfInt[paramInt1] = m;
        paramInt2 -= 32;
      }
    }
    for (paramInt1 = n >> j - paramInt2;; paramInt1 = m)
    {
      m = i;
      i = paramInt1;
      paramInt1 = m;
      break;
      k = paramInt1;
      if (i != 0)
      {
        paramArrayOfInt[paramInt1] = i;
        return paramInt1 + 1;
        int i1 = chars_per_word(paramInt2);
        j = 0;
        i = 0;
        k = i;
        if (j < paramInt1)
        {
          m = paramInt1 - j;
          k = m;
          if (m > i1) {
            k = i1;
          }
          m = paramArrayOfByte[j];
          n = paramInt2;
          j += 1;
          for (;;)
          {
            k -= 1;
            if (k <= 0) {
              break;
            }
            m = m * paramInt2 + paramArrayOfByte[j];
            n *= paramInt2;
            j += 1;
          }
          if (i == 0)
          {
            k = m;
            label236:
            if (k == 0) {
              break label284;
            }
            m = i + 1;
            paramArrayOfInt[i] = k;
            i = m;
          }
          label284:
          for (;;)
          {
            break;
            k = mul_1(paramArrayOfInt, paramArrayOfInt, i, n) + add_1(paramArrayOfInt, paramArrayOfInt, i, m);
            break label236;
          }
        }
      }
      return k;
      label290:
      i = paramInt1;
    }
  }
  
  public static int sub_n(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int paramInt)
  {
    int j = 0;
    int i = 0;
    if (i < paramInt)
    {
      int m = paramArrayOfInt3[i];
      int k = paramArrayOfInt2[i];
      m += j;
      if ((m ^ 0x80000000) < (j ^ 0x80000000))
      {
        j = 1;
        label47:
        m = k - m;
        if ((m ^ 0x80000000) <= (k ^ 0x80000000)) {
          break label98;
        }
      }
      label98:
      for (k = 1;; k = 0)
      {
        j += k;
        paramArrayOfInt1[i] = m;
        i += 1;
        break;
        j = 0;
        break label47;
      }
    }
    return j;
  }
  
  public static int submul_1(int[] paramArrayOfInt1, int paramInt1, int[] paramArrayOfInt2, int paramInt2, int paramInt3)
  {
    long l1 = paramInt3;
    int j = 0;
    int i = 0;
    long l2 = (paramArrayOfInt2[i] & 0xFFFFFFFF) * (l1 & 0xFFFFFFFF);
    paramInt3 = (int)l2;
    int m = (int)(l2 >> 32);
    int k = paramInt3 + j;
    if ((0x80000000 ^ k) < (0x80000000 ^ j)) {}
    for (paramInt3 = 1;; paramInt3 = 0)
    {
      j = paramInt3 + m;
      m = paramArrayOfInt1[(paramInt1 + i)];
      k = m - k;
      paramInt3 = j;
      if ((0x80000000 ^ k) > (0x80000000 ^ m)) {
        paramInt3 = j + 1;
      }
      paramArrayOfInt1[(paramInt1 + i)] = k;
      k = i + 1;
      j = paramInt3;
      i = k;
      if (k < paramInt2) {
        break;
      }
      return paramInt3;
    }
  }
  
  public static long udiv_qrnnd(long paramLong, int paramInt)
  {
    long l2 = paramLong >>> 32;
    long l3 = paramLong & 0xFFFFFFFF;
    long l1;
    if (paramInt >= 0) {
      if (l2 < (paramInt - l2 - (l3 >>> 31) & 0xFFFFFFFF))
      {
        l1 = paramLong / paramInt;
        paramLong %= paramInt;
      }
    }
    for (;;)
    {
      return paramLong << 32 | 0xFFFFFFFF & l1;
      paramLong -= (paramInt << 31);
      l1 = paramLong / paramInt;
      paramLong %= paramInt;
      l1 -= 2147483648L;
      continue;
      l1 = paramInt >>> 1;
      paramLong >>>= 1;
      if ((l2 < l1) || (l2 >> 1 < l1))
      {
        if (l2 < l1) {
          l2 = paramLong / l1;
        }
        for (paramLong %= l1;; paramLong = l1 - 1L - paramLong % l1)
        {
          l3 = 2L * paramLong + (1L & l3);
          l1 = l2;
          paramLong = l3;
          if ((paramInt & 0x1) == 0) {
            break;
          }
          if (l3 < l2) {
            break label206;
          }
          paramLong = l3 - l2;
          l1 = l2;
          break;
          paramLong = paramLong - (l1 << 32) ^ 0xFFFFFFFFFFFFFFFF;
          l2 = (0xFFFFFFFFFFFFFFFF ^ paramLong / l1) & 0xFFFFFFFF;
        }
        label206:
        if (l2 - l3 <= (paramInt & 0xFFFFFFFF))
        {
          paramLong = l3 - l2 + paramInt;
          l1 = l2 - 1L;
        }
        else
        {
          paramLong = l3 - l2 + paramInt + paramInt;
          l1 = l2 - 2L;
        }
      }
      else if (l3 >= (-paramInt & 0xFFFFFFFF))
      {
        l1 = -1L;
        paramLong = l3 + paramInt;
      }
      else
      {
        l1 = -2L;
        paramLong = paramInt + l3 + paramInt;
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\MPN.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */