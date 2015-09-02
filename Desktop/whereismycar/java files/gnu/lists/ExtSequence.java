package gnu.lists;

public abstract class ExtSequence
  extends AbstractSequence
{
  public int copyPos(int paramInt)
  {
    if (paramInt <= 0) {
      return paramInt;
    }
    return PositionManager.manager.register(PositionManager.getPositionObject(paramInt).copy());
  }
  
  protected boolean isAfterPos(int paramInt)
  {
    if (paramInt <= 0) {
      if (paramInt >= 0) {}
    }
    while ((PositionManager.getPositionObject(paramInt).ipos & 0x1) != 0)
    {
      return true;
      return false;
    }
    return false;
  }
  
  protected int nextIndex(int paramInt)
  {
    if (paramInt == -1) {
      return size();
    }
    if (paramInt == 0) {
      return 0;
    }
    return PositionManager.getPositionObject(paramInt).nextIndex();
  }
  
  protected void releasePos(int paramInt)
  {
    if (paramInt > 0) {
      PositionManager.manager.release(paramInt);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\ExtSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */