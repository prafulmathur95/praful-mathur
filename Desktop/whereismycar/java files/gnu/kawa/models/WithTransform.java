package gnu.kawa.models;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class WithTransform
  implements Paintable
{
  Paintable paintable;
  AffineTransform transform;
  
  public WithTransform(Paintable paramPaintable, AffineTransform paramAffineTransform)
  {
    this.paintable = paramPaintable;
    this.transform = paramAffineTransform;
  }
  
  public Rectangle2D getBounds2D()
  {
    return this.transform.createTransformedShape(this.paintable.getBounds2D()).getBounds2D();
  }
  
  public void paint(Graphics2D paramGraphics2D)
  {
    AffineTransform localAffineTransform = paramGraphics2D.getTransform();
    try
    {
      paramGraphics2D.transform(this.transform);
      this.paintable.paint(paramGraphics2D);
      return;
    }
    finally
    {
      paramGraphics2D.setTransform(localAffineTransform);
    }
  }
  
  public Paintable transform(AffineTransform paramAffineTransform)
  {
    AffineTransform localAffineTransform = new AffineTransform(this.transform);
    localAffineTransform.concatenate(paramAffineTransform);
    return new WithTransform(this.paintable, localAffineTransform);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\models\WithTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */