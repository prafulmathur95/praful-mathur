package gnu.kawa.models;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class FillShape
  implements Paintable
{
  Shape shape;
  
  public FillShape(Shape paramShape)
  {
    this.shape = paramShape;
  }
  
  public Rectangle2D getBounds2D()
  {
    return this.shape.getBounds2D();
  }
  
  public void paint(Graphics2D paramGraphics2D)
  {
    paramGraphics2D.fill(this.shape);
  }
  
  public Paintable transform(AffineTransform paramAffineTransform)
  {
    return new FillShape(paramAffineTransform.createTransformedShape(this.shape));
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\models\FillShape.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */