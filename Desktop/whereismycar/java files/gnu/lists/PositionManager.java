package gnu.lists;

public class PositionManager
{
  static final PositionManager manager = new PositionManager();
  int freeListHead = -1;
  int[] ivals = new int[50];
  SeqPosition[] positions = new SeqPosition[50];
  
  public PositionManager()
  {
    addToFreeList(this.ivals, 1, this.ivals.length);
  }
  
  private void addToFreeList(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int i = this.freeListHead;
    while (paramInt1 < paramInt2)
    {
      paramArrayOfInt[paramInt1] = i;
      i = paramInt1;
      paramInt1 += 1;
    }
    this.freeListHead = i;
  }
  
  private int getFreeSlot()
  {
    int j = this.freeListHead;
    int i = j;
    if (j < 0)
    {
      i = this.positions.length;
      SeqPosition[] arrayOfSeqPosition = new SeqPosition[i * 2];
      int[] arrayOfInt = new int[i * 2];
      System.arraycopy(this.positions, 0, arrayOfSeqPosition, 0, i);
      System.arraycopy(this.ivals, 0, arrayOfInt, 0, i);
      this.positions = arrayOfSeqPosition;
      this.ivals = arrayOfInt;
      addToFreeList(arrayOfInt, i, i * 2);
      i = this.freeListHead;
    }
    this.freeListHead = this.ivals[i];
    return i;
  }
  
  public static SeqPosition getPositionObject(int paramInt)
  {
    synchronized (manager)
    {
      SeqPosition localSeqPosition = ???.positions[paramInt];
      return localSeqPosition;
    }
  }
  
  public int register(SeqPosition paramSeqPosition)
  {
    try
    {
      int i = getFreeSlot();
      this.positions[i] = paramSeqPosition;
      this.ivals[i] = -1;
      return i;
    }
    finally
    {
      paramSeqPosition = finally;
      throw paramSeqPosition;
    }
  }
  
  public void release(int paramInt)
  {
    try
    {
      SeqPosition localSeqPosition = this.positions[paramInt];
      if ((localSeqPosition instanceof ExtPosition)) {
        ((ExtPosition)localSeqPosition).position = -1;
      }
      this.positions[paramInt] = null;
      this.ivals[paramInt] = this.freeListHead;
      this.freeListHead = paramInt;
      localSeqPosition.release();
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\PositionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */