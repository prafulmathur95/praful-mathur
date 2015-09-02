package gnu.kawa.models;

public abstract interface Window
{
  public abstract Display getDisplay();
  
  public abstract String getTitle();
  
  public abstract void open();
  
  public abstract void setContent(Object paramObject);
  
  public abstract void setMenuBar(Object paramObject);
  
  public abstract void setTitle(String paramString);
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\models\Window.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */