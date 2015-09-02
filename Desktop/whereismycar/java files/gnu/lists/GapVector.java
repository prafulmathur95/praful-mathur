package gnu.lists;

public class GapVector
  extends AbstractSequence
  implements Sequence
{
  public SimpleVector base;
  public int gapEnd;
  public int gapStart;
  
  protected GapVector() {}
  
  public GapVector(SimpleVector paramSimpleVector)
  {
    this.base = paramSimpleVector;
    this.gapStart = 0;
    this.gapEnd = paramSimpleVector.size;
  }
  
  public void add(int paramInt, Object paramObject)
  {
    gapReserve(paramInt, 1);
    this.base.set(paramInt, paramObject);
    this.gapStart += 1;
  }
  
  protected int addPos(int paramInt, Object paramObject)
  {
    int i = paramInt >>> 1;
    paramInt = i;
    if (i >= this.gapStart) {
      paramInt = i + (this.gapEnd - this.gapStart);
    }
    add(paramInt, paramObject);
    return paramInt + 1 << 1 | 0x1;
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer)
  {
    if (paramConsumer.ignoring()) {
      return;
    }
    int j = paramInt1 >>> 1;
    int k = paramInt2 >>> 1;
    if (j < this.gapStart) {
      if (k <= this.gapStart) {
        break label88;
      }
    }
    label88:
    for (int i = k;; i = this.gapStart)
    {
      consumePosRange(paramInt1, i << 1, paramConsumer);
      if (k <= this.gapEnd) {
        break;
      }
      paramInt1 = j;
      if (j < this.gapEnd) {
        paramInt1 = this.gapEnd;
      }
      consumePosRange(paramInt1 << 1, paramInt2, paramConsumer);
      return;
    }
  }
  
  public int createPos(int paramInt, boolean paramBoolean)
  {
    int i = paramInt;
    if (paramInt > this.gapStart) {
      i = paramInt + (this.gapEnd - this.gapStart);
    }
    if (paramBoolean) {}
    for (paramInt = 1;; paramInt = 0) {
      return paramInt | i << 1;
    }
  }
  
  public void fill(Object paramObject)
  {
    this.base.fill(this.gapEnd, this.base.size, paramObject);
    this.base.fill(0, this.gapStart, paramObject);
  }
  
  public void fillPosRange(int paramInt1, int paramInt2, Object paramObject)
  {
    int i;
    if (paramInt1 == -1)
    {
      i = this.base.size;
      if (paramInt2 != -1) {
        break label74;
      }
      paramInt1 = this.base.size;
      label27:
      if (this.gapStart >= paramInt1) {
        break label81;
      }
      paramInt2 = this.gapStart;
    }
    for (;;)
    {
      if (i >= paramInt2) {
        break label86;
      }
      this.base.setBuffer(i, paramObject);
      i += 1;
      continue;
      i = paramInt1 >>> 1;
      break;
      label74:
      paramInt1 = paramInt2 >>> 1;
      break label27;
      label81:
      paramInt2 = paramInt1;
    }
    label86:
    paramInt2 = this.gapEnd;
    while (paramInt2 < paramInt1)
    {
      this.base.setBuffer(paramInt2, paramObject);
      paramInt2 += 1;
    }
  }
  
  protected final void gapReserve(int paramInt)
  {
    gapReserve(this.gapStart, paramInt);
  }
  
  protected void gapReserve(int paramInt1, int paramInt2)
  {
    int i = 16;
    if (paramInt2 > this.gapEnd - this.gapStart)
    {
      j = this.base.size;
      if (j < 16)
      {
        k = j - (this.gapEnd - this.gapStart);
        j = k + paramInt2;
        paramInt2 = i;
        if (i < j) {
          paramInt2 = j;
        }
        paramInt2 = paramInt2 - k + paramInt1;
        this.base.resizeShift(this.gapStart, this.gapEnd, paramInt1, paramInt2);
        this.gapStart = paramInt1;
        this.gapEnd = paramInt2;
      }
    }
    while (paramInt1 == this.gapStart) {
      for (;;)
      {
        int j;
        int k;
        return;
        i = j * 2;
      }
    }
    shiftGap(paramInt1);
  }
  
  public Object get(int paramInt)
  {
    int i = paramInt;
    if (paramInt >= this.gapStart) {
      i = paramInt + (this.gapEnd - this.gapStart);
    }
    return this.base.get(i);
  }
  
  public int getNextKind(int paramInt)
  {
    if (hasNext(paramInt)) {
      return this.base.getElementKind();
    }
    return 0;
  }
  
  public int getSegment(int paramInt1, int paramInt2)
  {
    int j = size();
    if ((paramInt1 < 0) || (paramInt1 > j))
    {
      paramInt2 = -1;
      return paramInt2;
    }
    int i;
    if (paramInt2 < 0) {
      i = 0;
    }
    for (;;)
    {
      paramInt2 = paramInt1;
      if (paramInt1 + i <= this.gapStart) {
        break;
      }
      if (paramInt1 < this.gapStart) {
        break label76;
      }
      return paramInt1 + (this.gapEnd - this.gapStart);
      i = paramInt2;
      if (paramInt1 + paramInt2 > j) {
        i = j - paramInt1;
      }
    }
    label76:
    if (this.gapStart - paramInt1 > i >> 1)
    {
      shiftGap(paramInt1 + i);
      return paramInt1;
    }
    shiftGap(paramInt1);
    return paramInt1 + (this.gapEnd - this.gapStart);
  }
  
  public boolean hasNext(int paramInt)
  {
    int i = paramInt >>> 1;
    paramInt = i;
    if (i >= this.gapStart) {
      paramInt = i + (this.gapEnd - this.gapStart);
    }
    return paramInt < this.base.size;
  }
  
  protected boolean isAfterPos(int paramInt)
  {
    return (paramInt & 0x1) != 0;
  }
  
  protected int nextIndex(int paramInt)
  {
    if (paramInt == -1) {
      paramInt = this.base.size;
    }
    for (;;)
    {
      int i = paramInt;
      if (paramInt > this.gapStart) {
        i = paramInt - (this.gapEnd - this.gapStart);
      }
      return i;
      paramInt >>>= 1;
    }
  }
  
  protected void removePosRange(int paramInt1, int paramInt2)
  {
    paramInt1 >>>= 1;
    paramInt2 >>>= 1;
    if (paramInt1 > this.gapEnd) {
      shiftGap(paramInt1 - this.gapEnd + this.gapStart);
    }
    for (;;)
    {
      if (paramInt1 < this.gapStart)
      {
        this.base.clearBuffer(paramInt1, this.gapStart - paramInt1);
        this.gapStart = paramInt1;
      }
      if (paramInt2 > this.gapEnd)
      {
        this.base.clearBuffer(this.gapEnd, paramInt2 - this.gapEnd);
        this.gapEnd = paramInt2;
      }
      return;
      if (paramInt2 < this.gapStart) {
        shiftGap(paramInt2);
      }
    }
  }
  
  public Object set(int paramInt, Object paramObject)
  {
    int i = paramInt;
    if (paramInt >= this.gapStart) {
      i = paramInt + (this.gapEnd - this.gapStart);
    }
    return this.base.set(i, paramObject);
  }
  
  protected void shiftGap(int paramInt)
  {
    int i = paramInt - this.gapStart;
    if (i > 0) {
      this.base.shift(this.gapEnd, this.gapStart, i);
    }
    for (;;)
    {
      this.gapEnd += i;
      this.gapStart = paramInt;
      return;
      if (i < 0) {
        this.base.shift(paramInt, this.gapEnd + i, -i);
      }
    }
  }
  
  public int size()
  {
    return this.base.size - (this.gapEnd - this.gapStart);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\GapVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */