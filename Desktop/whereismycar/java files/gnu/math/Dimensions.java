package gnu.math;

public class Dimensions
{
  public static Dimensions Empty = new Dimensions();
  private static Dimensions[] hashTable = new Dimensions[100];
  BaseUnit[] bases;
  private Dimensions chain;
  int hash_code;
  short[] powers;
  
  private Dimensions()
  {
    this.bases = new BaseUnit[1];
    this.bases[0] = Unit.Empty;
    enterHash(0);
  }
  
  Dimensions(BaseUnit paramBaseUnit)
  {
    this.bases = new BaseUnit[2];
    this.powers = new short[1];
    this.bases[0] = paramBaseUnit;
    this.bases[1] = Unit.Empty;
    this.powers[0] = 1;
    enterHash(paramBaseUnit.index);
  }
  
  private Dimensions(Dimensions paramDimensions1, int paramInt1, Dimensions paramDimensions2, int paramInt2, int paramInt3)
  {
    this.hash_code = paramInt3;
    int i = 0;
    while (paramDimensions1.bases[i] != Unit.Empty) {
      i += 1;
    }
    int j = 0;
    while (paramDimensions2.bases[j] != Unit.Empty) {
      j += 1;
    }
    i = i + j + 1;
    this.bases = new BaseUnit[i];
    this.powers = new short[i];
    int m = 0;
    i = 0;
    j = 0;
    for (;;)
    {
      Object localObject = paramDimensions1.bases[j];
      BaseUnit localBaseUnit = paramDimensions2.bases[i];
      int k;
      if (((BaseUnit)localObject).index < localBaseUnit.index)
      {
        k = paramDimensions1.powers[j] * paramInt1;
        j += 1;
      }
      for (;;)
      {
        if ((short)k != k)
        {
          throw new ArithmeticException("overflow in dimensions");
          if (localBaseUnit.index < ((BaseUnit)localObject).index)
          {
            localObject = localBaseUnit;
            k = paramDimensions2.powers[i] * paramInt2;
            i += 1;
          }
          else
          {
            if (localBaseUnit == Unit.Empty)
            {
              this.bases[m] = Unit.Empty;
              enterHash(paramInt3);
              return;
            }
            int i2 = paramDimensions1.powers[j] * paramInt1 + paramDimensions2.powers[i] * paramInt2;
            int n = j + 1;
            int i1 = i + 1;
            j = n;
            i = i1;
            k = i2;
            if (i2 == 0)
            {
              j = n;
              i = i1;
              break;
            }
          }
        }
      }
      this.bases[m] = localObject;
      this.powers[m] = ((short)k);
      m += 1;
    }
  }
  
  private void enterHash(int paramInt)
  {
    this.hash_code = paramInt;
    paramInt = (0x7FFFFFFF & paramInt) % hashTable.length;
    this.chain = hashTable[paramInt];
    hashTable[paramInt] = this;
  }
  
  private boolean matchesProduct(Dimensions paramDimensions1, int paramInt1, Dimensions paramDimensions2, int paramInt2)
  {
    int j = 0;
    int i = 0;
    int m = 0;
    for (;;)
    {
      Object localObject = paramDimensions1.bases[j];
      BaseUnit localBaseUnit = paramDimensions2.bases[i];
      int k;
      if (((BaseUnit)localObject).index < localBaseUnit.index)
      {
        k = paramDimensions1.powers[j] * paramInt1;
        j += 1;
      }
      for (;;)
      {
        if ((this.bases[m] != localObject) || (this.powers[m] != k))
        {
          do
          {
            return false;
            if (localBaseUnit.index < ((BaseUnit)localObject).index)
            {
              localObject = localBaseUnit;
              k = paramDimensions2.powers[i] * paramInt2;
              i += 1;
              break;
            }
            if (localBaseUnit != Unit.Empty) {
              break label143;
            }
          } while (this.bases[m] != localBaseUnit);
          return true;
          label143:
          int i2 = paramDimensions1.powers[j] * paramInt1 + paramDimensions2.powers[i] * paramInt2;
          int n = j + 1;
          int i1 = i + 1;
          j = n;
          i = i1;
          k = i2;
          if (i2 == 0)
          {
            j = n;
            i = i1;
            break;
          }
        }
      }
      m += 1;
    }
  }
  
  public static Dimensions product(Dimensions paramDimensions1, int paramInt1, Dimensions paramDimensions2, int paramInt2)
  {
    int i = paramDimensions1.hashCode() * paramInt1 + paramDimensions2.hashCode() * paramInt2;
    int j = hashTable.length;
    for (Dimensions localDimensions = hashTable[((0x7FFFFFFF & i) % j)]; localDimensions != null; localDimensions = localDimensions.chain) {
      if ((localDimensions.hash_code == i) && (localDimensions.matchesProduct(paramDimensions1, paramInt1, paramDimensions2, paramInt2))) {
        return localDimensions;
      }
    }
    return new Dimensions(paramDimensions1, paramInt1, paramDimensions2, paramInt2, i);
  }
  
  public int getPower(BaseUnit paramBaseUnit)
  {
    int i = 0;
    while (this.bases[i].index <= paramBaseUnit.index)
    {
      if (this.bases[i] == paramBaseUnit) {
        return this.powers[i];
      }
      i += 1;
    }
    return 0;
  }
  
  public final int hashCode()
  {
    return this.hash_code;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (this.bases[i] != Unit.Empty)
    {
      if (i > 0) {
        localStringBuffer.append('*');
      }
      localStringBuffer.append(this.bases[i]);
      int j = this.powers[i];
      if (j != 1)
      {
        localStringBuffer.append('^');
        localStringBuffer.append(j);
      }
      i += 1;
    }
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\Dimensions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */