package gnu.kawa.models;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.io.Serializable;

public class Spacer
  extends Model
  implements Viewable, Serializable
{
  Dimension2D maxSize;
  Dimension2D minSize;
  Dimension2D preferredSize;
  
  public static Spacer rigidArea(int paramInt1, int paramInt2)
  {
    return rigidArea(new Dimension(paramInt1, paramInt2));
  }
  
  public static Spacer rigidArea(Dimension2D paramDimension2D)
  {
    Spacer localSpacer = new Spacer();
    localSpacer.minSize = paramDimension2D;
    localSpacer.maxSize = paramDimension2D;
    localSpacer.preferredSize = paramDimension2D;
    return localSpacer;
  }
  
  public Dimension getMaximumSize()
  {
    return Display.asDimension(this.maxSize);
  }
  
  public Dimension2D getMaximumSize2D()
  {
    return this.maxSize;
  }
  
  public Dimension getMinimumSize()
  {
    return Display.asDimension(this.minSize);
  }
  
  public Dimension2D getMinimumSize2D()
  {
    return this.minSize;
  }
  
  public Dimension getPreferredSize()
  {
    return Display.asDimension(this.preferredSize);
  }
  
  public Dimension2D getPreferredSize2D()
  {
    return this.preferredSize;
  }
  
  public boolean isRigid()
  {
    if (this.minSize == this.maxSize) {}
    while ((this.minSize != null) && (this.maxSize != null) && (this.minSize.getWidth() == this.maxSize.getWidth()) && (this.minSize.getHeight() == this.maxSize.getHeight())) {
      return true;
    }
    return false;
  }
  
  public void makeView(Display paramDisplay, Object paramObject)
  {
    paramDisplay.addSpacer(this, paramObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\models\Spacer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */