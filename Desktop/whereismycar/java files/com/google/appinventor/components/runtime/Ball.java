package com.google.appinventor.components.runtime;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.PaintUtil;

@DesignerComponent(category=ComponentCategory.ANIMATION, description="<p>A round 'sprite' that can be placed on a <code>Canvas</code>, where it can react to touches and drags, interact with other sprites (<code>ImageSprite</code>s and other <code>Ball</code>s) and the edge of the Canvas, and move according to its property values.</p><p>For example, to have a <code>Ball</code> move 4 pixels toward the top of a <code>Canvas</code> every 500 milliseconds (half second), you would set the <code>Speed</code> property to 4 [pixels], the <code>Interval</code> property to 500 [milliseconds], the <code>Heading</code> property to 90 [degrees], and the <code>Enabled</code> property to <code>True</code>.  These and its other properties can be changed at any time.</p><p>The difference between a Ball and an <code>ImageSprite</code> is that the latter can get its appearance from an image file, while a Ball's appearance can only be changed by varying its <code>PaintColor</code> and <code>Radius</code> properties.</p>", version=5)
@SimpleObject
public final class Ball
  extends Sprite
{
  static final int DEFAULT_RADIUS = 5;
  private Paint paint = new Paint();
  private int paintColor;
  private int radius;
  
  public Ball(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer);
    PaintColor(-16777216);
    Radius(5);
  }
  
  public int Height()
  {
    return this.radius * 2;
  }
  
  public void Height(int paramInt) {}
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public int PaintColor()
  {
    return this.paintColor;
  }
  
  @DesignerProperty(defaultValue="&HFF000000", editorType="color")
  @SimpleProperty
  public void PaintColor(int paramInt)
  {
    this.paintColor = paramInt;
    if (paramInt != 0) {
      PaintUtil.changePaint(this.paint, paramInt);
    }
    for (;;)
    {
      registerChange();
      return;
      PaintUtil.changePaint(this.paint, -16777216);
    }
  }
  
  @SimpleProperty
  public int Radius()
  {
    return this.radius;
  }
  
  @DesignerProperty(defaultValue="5", editorType="non_negative_integer")
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public void Radius(int paramInt)
  {
    this.radius = paramInt;
    registerChange();
  }
  
  public int Width()
  {
    return this.radius * 2;
  }
  
  public void Width(int paramInt) {}
  
  public boolean containsPoint(double paramDouble1, double paramDouble2)
  {
    double d1 = this.xLeft + this.radius;
    double d2 = this.yTop + this.radius;
    return (paramDouble1 - d1) * (paramDouble1 - d1) + (paramDouble2 - d2) * (paramDouble2 - d2) <= this.radius * this.radius;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (this.visible) {
      paramCanvas.drawCircle((float)this.xLeft + this.radius, (float)this.yTop + this.radius, this.radius, this.paint);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\Ball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */