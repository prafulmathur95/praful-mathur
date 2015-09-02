package gnu.kawa.models;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class DrawShape
  implements Paintable
{
  Shape shape;
  
  public DrawShape(Shape paramShape)
  {
    this.shape = paramShape;
  }
  
  public Rectangle2D getBounds2D()
  {
    return this.shape.getBounds2D();
  }
  
  public void paint(Graphics2D paramGraphics2D)
  {
    paramGraphics2D.draw(this.shape);
  }
  
  public Paintable transform(AffineTransform paramAffineTransform)
  {
    return new DrawShape(paramAffineTransform.createTransformedShape(this.shape));
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\models\DrawShape.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */