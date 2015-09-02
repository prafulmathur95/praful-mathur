package gnu.kawa.models;

import java.io.Serializable;

public class Label
  extends Model
  implements Viewable, Serializable
{
  String text;
  
  public Label() {}
  
  public Label(String paramString)
  {
    this.text = paramString;
  }
  
  public String getText()
  {
    return this.text;
  }
  
  public void makeView(Display paramDisplay, Object paramObject)
  {
    paramDisplay.addLabel(this, paramObject);
  }
  
  public void setText(String paramString)
  {
    this.text = paramString;
    notifyListeners("text");
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\models\Label.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */