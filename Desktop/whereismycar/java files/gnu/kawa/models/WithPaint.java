package gnu.kawa.models;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class WithPaint
  implements Paintable
{
  Paint paint;
  Paintable paintable;
  
  public WithPaint(Paintable paramPaintable, Paint paramPaint)
  {
    this.paintable = paramPaintable;
    this.paint = paramPaint;
  }
  
  public Rectangle2D getBounds2D()
  {
    return this.paintable.getBounds2D();
  }
  
  public void paint(Graphics2D paramGraphics2D)
  {
    Paint localPaint = paramGraphics2D.getPaint();
    try
    {
      paramGraphics2D.setPaint(this.paint);
      this.paintable.paint(paramGraphics2D);
      return;
    }
    finally
    {
      paramGraphics2D.setPaint(localPaint);
    }
  }
  
  public Paintable transform(AffineTransform paramAffineTransform)
  {
    return new WithPaint(this.paintable.transform(paramAffineTransform), this.paint);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\models\WithPaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */