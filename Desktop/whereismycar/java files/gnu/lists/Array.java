package gnu.lists;

public abstract interface Array
{
  public abstract Object get(int[] paramArrayOfInt);
  
  public abstract int getEffectiveIndex(int[] paramArrayOfInt);
  
  public abstract int getLowBound(int paramInt);
  
  public abstract Object getRowMajor(int paramInt);
  
  public abstract int getSize(int paramInt);
  
  public abstract boolean isEmpty();
  
  public abstract int rank();
  
  public abstract Object set(int[] paramArrayOfInt, Object paramObject);
  
  public abstract Array transpose(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt, int[] paramArrayOfInt3);
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\Array.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */