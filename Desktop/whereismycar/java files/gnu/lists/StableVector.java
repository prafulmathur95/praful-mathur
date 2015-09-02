package gnu.lists;

public class StableVector
  extends GapVector
{
  static final int END_POSITION = 1;
  protected static final int FREE_POSITION = -2;
  static final int START_POSITION = 0;
  protected int free;
  protected int[] positions;
  
  protected StableVector() {}
  
  public StableVector(SimpleVector paramSimpleVector)
  {
    super(paramSimpleVector);
    this.positions = new int[16];
    this.positions[0] = 0;
    this.positions[1] = (paramSimpleVector.getBufferLength() << 1 | 0x1);
    this.free = -1;
    int i = this.positions.length;
    for (;;)
    {
      i -= 1;
      if (i <= 1) {
        break;
      }
      this.positions[i] = this.free;
      this.free = i;
    }
  }
  
  protected int addPos(int paramInt, Object paramObject)
  {
    int k = this.positions[paramInt];
    int j = k >>> 1;
    int i = j;
    if (j >= this.gapStart) {
      i = j + (this.gapEnd - this.gapStart);
    }
    j = paramInt;
    if ((k & 0x1) == 0) {
      if (paramInt != 0) {
        break label70;
      }
    }
    for (j = createPos(0, true);; j = paramInt)
    {
      add(i, paramObject);
      return j;
      label70:
      this.positions[paramInt] = (k | 0x1);
    }
  }
  
  protected void adjustPositions(int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.free >= -1) {
      unchainFreelist();
    }
    int i = this.positions.length;
    for (;;)
    {
      int j = i - 1;
      if (j <= 0) {
        break;
      }
      int k = this.positions[j];
      i = j;
      if (k != -2)
      {
        int m = k ^ 0x80000000;
        i = j;
        if (m >= (paramInt1 ^ 0x80000000))
        {
          i = j;
          if (m <= (paramInt2 ^ 0x80000000))
          {
            this.positions[j] = (k + paramInt3);
            i = j;
          }
        }
      }
    }
  }
  
  protected int allocPositionIndex()
  {
    if (this.free == -2) {
      chainFreelist();
    }
    if (this.free < 0)
    {
      int j = this.positions.length;
      int[] arrayOfInt = new int[j * 2];
      System.arraycopy(this.positions, 0, arrayOfInt, 0, j);
      i = j * 2;
      for (;;)
      {
        i -= 1;
        if (i < j) {
          break;
        }
        arrayOfInt[i] = this.free;
        this.free = i;
      }
      this.positions = arrayOfInt;
    }
    int i = this.free;
    this.free = this.positions[this.free];
    return i;
  }
  
  protected void chainFreelist()
  {
    this.free = -1;
    int i = this.positions.length;
    for (;;)
    {
      int j = i - 1;
      if (j <= 1) {
        break;
      }
      i = j;
      if (this.positions[j] == -2)
      {
        this.positions[j] = this.free;
        this.free = j;
        i = j;
      }
    }
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer)
  {
    super.consumePosRange(this.positions[paramInt1], this.positions[paramInt2], paramConsumer);
  }
  
  public int copyPos(int paramInt)
  {
    int i = paramInt;
    if (paramInt > 1)
    {
      i = allocPositionIndex();
      this.positions[i] = this.positions[paramInt];
    }
    return i;
  }
  
  public int createPos(int paramInt, boolean paramBoolean)
  {
    int j = 1;
    if ((paramInt == 0) && (!paramBoolean)) {
      return 0;
    }
    if ((paramBoolean) && (paramInt == size())) {
      return 1;
    }
    int i;
    if (paramInt <= this.gapStart)
    {
      i = paramInt;
      if (paramInt == this.gapStart)
      {
        i = paramInt;
        if (!paramBoolean) {}
      }
    }
    else
    {
      i = paramInt + (this.gapEnd - this.gapStart);
    }
    int k = allocPositionIndex();
    int[] arrayOfInt = this.positions;
    if (paramBoolean) {}
    for (paramInt = j;; paramInt = 0)
    {
      arrayOfInt[k] = (paramInt | i << 1);
      return k;
    }
  }
  
  public int endPos()
  {
    return 1;
  }
  
  public void fillPosRange(int paramInt1, int paramInt2, Object paramObject)
  {
    fillPosRange(this.positions[paramInt1], this.positions[paramInt2], paramObject);
  }
  
  protected void gapReserve(int paramInt1, int paramInt2)
  {
    int i = this.gapEnd;
    int j = this.gapStart;
    if (paramInt2 > i - j)
    {
      k = this.base.size;
      super.gapReserve(paramInt1, paramInt2);
      paramInt2 = this.base.size;
      if (paramInt1 == j) {
        adjustPositions(i << 1, paramInt2 << 1 | 0x1, paramInt2 - k << 1);
      }
    }
    while (paramInt1 == this.gapStart)
    {
      int k;
      return;
      adjustPositions(i << 1, k << 1 | 0x1, j - i << 1);
      adjustPositions(this.gapStart << 1, paramInt2 << 1 | 0x1, this.gapEnd - this.gapStart << 1);
      return;
    }
    shiftGap(paramInt1);
  }
  
  public boolean hasNext(int paramInt)
  {
    int i = this.positions[paramInt] >>> 1;
    paramInt = i;
    if (i >= this.gapStart) {
      paramInt = i + (this.gapEnd - this.gapStart);
    }
    return paramInt < this.base.getBufferLength();
  }
  
  protected boolean isAfterPos(int paramInt)
  {
    return (this.positions[paramInt] & 0x1) != 0;
  }
  
  public int nextIndex(int paramInt)
  {
    int i = this.positions[paramInt] >>> 1;
    paramInt = i;
    if (i > this.gapStart) {
      paramInt = i - (this.gapEnd - this.gapStart);
    }
    return paramInt;
  }
  
  public int nextPos(int paramInt)
  {
    int k = this.positions[paramInt];
    int j = k >>> 1;
    int i = j;
    if (j >= this.gapStart) {
      i = j + (this.gapEnd - this.gapStart);
    }
    if (i >= this.base.getBufferLength())
    {
      releasePos(paramInt);
      return 0;
    }
    i = paramInt;
    if (paramInt == 0) {
      i = createPos(0, true);
    }
    this.positions[i] = (k | 0x1);
    return i;
  }
  
  public void releasePos(int paramInt)
  {
    if (paramInt >= 2)
    {
      if (this.free == -2) {
        chainFreelist();
      }
      this.positions[paramInt] = this.free;
      this.free = paramInt;
    }
  }
  
  protected void removePosRange(int paramInt1, int paramInt2)
  {
    super.removePosRange(this.positions[paramInt1], this.positions[paramInt2]);
    int i = this.gapStart;
    int j = this.gapEnd;
    if (this.free >= -1) {
      unchainFreelist();
    }
    paramInt1 = this.positions.length;
    for (;;)
    {
      paramInt2 = paramInt1 - 1;
      if (paramInt2 <= 0) {
        break;
      }
      int m = this.positions[paramInt2];
      paramInt1 = paramInt2;
      if (m != -2)
      {
        int k = m >> 1;
        if ((m & 0x1) != 0) {}
        for (paramInt1 = 1;; paramInt1 = 0)
        {
          if (paramInt1 == 0) {
            break label130;
          }
          paramInt1 = paramInt2;
          if (k < i) {
            break;
          }
          paramInt1 = paramInt2;
          if (k >= j) {
            break;
          }
          this.positions[paramInt2] = (this.gapEnd << 1 | 0x1);
          paramInt1 = paramInt2;
          break;
        }
        label130:
        paramInt1 = paramInt2;
        if (k > i)
        {
          paramInt1 = paramInt2;
          if (k <= j)
          {
            this.positions[paramInt2] = (this.gapStart << 1);
            paramInt1 = paramInt2;
          }
        }
      }
    }
  }
  
  protected void shiftGap(int paramInt)
  {
    int i = this.gapStart;
    int j = paramInt - i;
    int k;
    if (j > 0)
    {
      int m = this.gapEnd;
      i = i - m << 1;
      k = m << 1;
      j = (m + j << 1) - 1;
    }
    for (;;)
    {
      super.shiftGap(paramInt);
      adjustPositions(k, j, i);
      do
      {
        return;
      } while (paramInt == i);
      k = (paramInt << 1) + 1;
      j = i << 1;
      i = this.gapEnd - i << 1;
    }
  }
  
  public int startPos()
  {
    return 0;
  }
  
  protected void unchainFreelist()
  {
    int j;
    for (int i = this.free; i >= 0; i = j)
    {
      j = this.positions[i];
      this.positions[i] = -2;
    }
    this.free = -2;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\StableVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */