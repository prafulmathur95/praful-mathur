package gnu.kawa.models;

public abstract class Model
  implements Viewable
{
  transient WeakListener listeners;
  
  public void addListener(ModelListener paramModelListener)
  {
    this.listeners = new WeakListener(paramModelListener, this.listeners);
  }
  
  public void addListener(WeakListener paramWeakListener)
  {
    paramWeakListener.next = this.listeners;
    this.listeners = paramWeakListener;
  }
  
  public void notifyListeners(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = this.listeners;
    if (localObject1 != null)
    {
      Object localObject3 = ((WeakListener)localObject1).get();
      WeakListener localWeakListener = ((WeakListener)localObject1).next;
      if (localObject3 == null)
      {
        localObject1 = localObject2;
        if (localObject2 != null) {
          ((WeakListener)localObject2).next = localWeakListener;
        }
      }
      for (localObject1 = localObject2;; localObject1 = localObject2)
      {
        localObject2 = localObject1;
        localObject1 = localWeakListener;
        break;
        localObject2 = localObject1;
        ((WeakListener)localObject1).update(localObject3, this, paramString);
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\models\Model.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */