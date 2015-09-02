package gnu.kawa.models;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public abstract interface Paintable
{
  public abstract Rectangle2D getBounds2D();
  
  public abstract void paint(Graphics2D paramGraphics2D);
  
  public abstract Paintable transform(AffineTransform paramAffineTransform);
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\models\Paintable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */