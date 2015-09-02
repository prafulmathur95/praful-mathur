package gnu.lists;

public class GeneralArray1
  extends GeneralArray
  implements Sequence
{
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer)
  {
    if (paramConsumer.ignoring()) {}
    for (;;)
    {
      return;
      while (!equals(paramInt1, paramInt2))
      {
        if (!hasNext(paramInt1)) {
          throw new RuntimeException();
        }
        this.base.consume(this.offset + this.strides[0] * (paramInt1 >>> 1), 1, paramConsumer);
        paramInt1 = nextPos(paramInt1);
      }
    }
  }
  
  public int createPos(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0) {
      return i | paramInt << 1;
    }
  }
  
  protected int nextIndex(int paramInt)
  {
    if (paramInt == -1) {
      return size();
    }
    return paramInt >>> 1;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\GeneralArray1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */