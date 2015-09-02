package gnu.kawa.models;

import java.lang.ref.WeakReference;

public class WeakListener
  extends WeakReference
{
  WeakListener next;
  
  public WeakListener(Object paramObject)
  {
    super(paramObject);
  }
  
  public WeakListener(Object paramObject, WeakListener paramWeakListener)
  {
    super(paramObject);
    this.next = paramWeakListener;
  }
  
  public void update(Object paramObject1, Model paramModel, Object paramObject2)
  {
    ((ModelListener)paramObject1).modelUpdated(paramModel, paramObject2);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\models\WeakListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */