package com.google.appinventor.components.runtime;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.errors.AssertionFailure;
import com.google.appinventor.components.runtime.errors.IllegalArgumentError;
import com.google.appinventor.components.runtime.util.BoundingBox;
import com.google.appinventor.components.runtime.util.TimerInternal;
import java.util.HashSet;
import java.util.Set;

@SimpleObject
public abstract class Sprite
  extends VisibleComponent
  implements AlarmHandler, OnDestroyListener, Deleteable
{
  private static final boolean DEFAULT_ENABLED = true;
  private static final int DEFAULT_HEADING = 0;
  private static final int DEFAULT_INTERVAL = 100;
  private static final float DEFAULT_SPEED = 0.0F;
  private static final boolean DEFAULT_VISIBLE = true;
  private static final double DEFAULT_Z = 1.0D;
  private static final String LOG_TAG = "Sprite";
  private final Handler androidUIHandler;
  protected final Canvas canvas;
  protected double heading;
  protected double headingCos;
  protected double headingRadians;
  protected double headingSin;
  protected boolean initialized = false;
  protected int interval;
  private final Set<Sprite> registeredCollisions;
  protected float speed;
  private final TimerInternal timerInternal;
  protected double userHeading;
  protected boolean visible = true;
  protected double xLeft;
  protected double yTop;
  protected double zLayer;
  
  protected Sprite(ComponentContainer paramComponentContainer)
  {
    this(paramComponentContainer, new Handler());
  }
  
  protected Sprite(ComponentContainer paramComponentContainer, Handler paramHandler)
  {
    this.androidUIHandler = paramHandler;
    if (!(paramComponentContainer instanceof Canvas)) {
      throw new IllegalArgumentError("Sprite constructor called with container " + paramComponentContainer);
    }
    this.canvas = ((Canvas)paramComponentContainer);
    this.canvas.addSprite(this);
    this.registeredCollisions = new HashSet();
    this.timerInternal = new TimerInternal(this, true, 100, paramHandler);
    Heading(0.0D);
    Enabled(true);
    Interval(100);
    Speed(0.0F);
    Visible(true);
    Z(1.0D);
    paramComponentContainer.$form().registerForOnDestroy(this);
  }
  
  public static boolean colliding(Sprite paramSprite1, Sprite paramSprite2)
  {
    BoundingBox localBoundingBox = paramSprite1.getBoundingBox(1);
    if (!localBoundingBox.intersectDestructively(paramSprite2.getBoundingBox(1))) {}
    for (;;)
    {
      return false;
      for (double d1 = localBoundingBox.getLeft(); d1 <= localBoundingBox.getRight(); d1 += 1.0D) {
        for (double d2 = localBoundingBox.getTop(); d2 <= localBoundingBox.getBottom(); d2 += 1.0D) {
          if ((paramSprite1.containsPoint(d1, d2)) && (paramSprite2.containsPoint(d1, d2))) {
            return true;
          }
        }
      }
    }
  }
  
  private final boolean overEastEdge(int paramInt)
  {
    return this.xLeft + Width() > paramInt;
  }
  
  private final boolean overNorthEdge()
  {
    return this.yTop < 0.0D;
  }
  
  private final boolean overSouthEdge(int paramInt)
  {
    return this.yTop + Height() > paramInt;
  }
  
  private final boolean overWestEdge()
  {
    return this.xLeft < 0.0D;
  }
  
  @SimpleFunction(description="Makes this sprite bounce, as if off a wall.  For normal bouncing, the edge argument should be the one returned by EdgeReached.")
  public void Bounce(int paramInt)
  {
    MoveIntoBounds();
    double d2 = this.userHeading % 360.0D;
    double d1 = d2;
    if (d2 < 0.0D) {
      d1 = d2 + 360.0D;
    }
    if (((paramInt == 3) && ((d1 < 90.0D) || (d1 > 270.0D))) || ((paramInt == -3) && (d1 > 90.0D) && (d1 < 270.0D))) {
      Heading(180.0D - d1);
    }
    do
    {
      return;
      if (((paramInt == 1) && (d1 > 0.0D) && (d1 < 180.0D)) || ((paramInt == -1) && (d1 > 180.0D)))
      {
        Heading(360.0D - d1);
        return;
      }
    } while (((paramInt != 2) || (d1 <= 0.0D) || (d1 >= 90.0D)) && ((paramInt != -4) || (d1 <= 90.0D) || (d1 >= 180.0D)) && ((paramInt != -2) || (d1 <= 180.0D) || (d1 >= 270.0D)) && ((paramInt != 4) || (d1 <= 270.0D)));
    Heading(180.0D + d1);
  }
  
  @SimpleEvent
  public void CollidedWith(Sprite paramSprite)
  {
    if (this.registeredCollisions.contains(paramSprite))
    {
      Log.e("Sprite", "Collision between sprites " + this + " and " + paramSprite + " re-registered");
      return;
    }
    this.registeredCollisions.add(paramSprite);
    postEvent(this, "CollidedWith", new Object[] { paramSprite });
  }
  
  @SimpleFunction
  public boolean CollidingWith(Sprite paramSprite)
  {
    return this.registeredCollisions.contains(paramSprite);
  }
  
  @SimpleEvent
  public void Dragged(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    postEvent(this, "Dragged", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3), Float.valueOf(paramFloat4), Float.valueOf(paramFloat5), Float.valueOf(paramFloat6) });
  }
  
  @SimpleEvent(description="Event handler called when the sprite reaches an edge of the screen. If Bounce is then called with that edge, the sprite will appear to bounce off of the edge it reached.  Edge here is represented as an integer that indicates one of eight directions north(1), northeast(2), east(3), southeast(4), south (-1), southwest(-2), west(-3), and northwest(-4).")
  public void EdgeReached(int paramInt)
  {
    if ((paramInt == 0) || (paramInt < -4) || (paramInt > 4)) {
      throw new IllegalArgumentException("Illegal argument " + paramInt + " to Sprite.EdgeReached()");
    }
    postEvent(this, "EdgeReached", new Object[] { Integer.valueOf(paramInt) });
  }
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty
  public void Enabled(boolean paramBoolean)
  {
    this.timerInternal.Enabled(paramBoolean);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Controls whether the sprite moves when its speed is non-zero.")
  public boolean Enabled()
  {
    return this.timerInternal.Enabled();
  }
  
  @SimpleEvent
  public void Flung(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    postEvent(this, "Flung", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3), Float.valueOf(paramFloat4), Float.valueOf(paramFloat5), Float.valueOf(paramFloat6) });
  }
  
  @SimpleProperty(description="Returns the sprite's heading in degrees above the positive x-axis.  Zero degrees is toward the right of the screen; 90 degrees is toward the top of the screen.")
  public double Heading()
  {
    return this.userHeading;
  }
  
  @DesignerProperty(defaultValue="0", editorType="float")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public void Heading(double paramDouble)
  {
    this.userHeading = paramDouble;
    this.heading = (-paramDouble);
    this.headingRadians = Math.toRadians(this.heading);
    this.headingCos = Math.cos(this.headingRadians);
    this.headingSin = Math.sin(this.headingRadians);
    registerChange();
  }
  
  public void Initialize()
  {
    this.initialized = true;
    this.canvas.registerChange(this);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The interval in milliseconds at which the sprite's position is updated.  For example, if the interval is 50 and the speed is 10, then the sprite will move 10 pixels every 50 milliseconds.")
  public int Interval()
  {
    return this.timerInternal.Interval();
  }
  
  @DesignerProperty(defaultValue="100", editorType="non_negative_integer")
  @SimpleProperty
  public void Interval(int paramInt)
  {
    this.timerInternal.Interval(paramInt);
  }
  
  @SimpleFunction
  public void MoveIntoBounds()
  {
    moveIntoBounds(this.canvas.Width(), this.canvas.Height());
  }
  
  @SimpleFunction(description="Moves the sprite so that its left top corner is at the specfied x and y coordinates.")
  public void MoveTo(double paramDouble1, double paramDouble2)
  {
    this.xLeft = paramDouble1;
    this.yTop = paramDouble2;
    registerChange();
  }
  
  @SimpleEvent(description="Event indicating that a pair of sprites are no longer colliding.")
  public void NoLongerCollidingWith(Sprite paramSprite)
  {
    if (!this.registeredCollisions.contains(paramSprite)) {
      Log.e("Sprite", "Collision between sprites " + this + " and " + paramSprite + " removed but not present");
    }
    this.registeredCollisions.remove(paramSprite);
    postEvent(this, "NoLongerCollidingWith", new Object[] { paramSprite });
  }
  
  @SimpleFunction(description="Turns the sprite to point towards the point with coordinates as (x, y).")
  public void PointInDirection(double paramDouble1, double paramDouble2)
  {
    Heading(-Math.toDegrees(Math.atan2(paramDouble2 - Y() - Height() / 2, paramDouble1 - X() - Width() / 2)));
  }
  
  @SimpleFunction(description="Turns the sprite to point towards a designated target sprite. The new heading will be parallel to the line joining the centerpoints of the two sprites.")
  public void PointTowards(Sprite paramSprite)
  {
    Heading(-Math.toDegrees(Math.atan2(paramSprite.Y() - Y() + (paramSprite.Height() - Height()) / 2, paramSprite.X() - X() + (paramSprite.Width() - Width()) / 2)));
  }
  
  @SimpleProperty(description="he speed at which the sprite moves.  The sprite moves this many pixels every interval.")
  public float Speed()
  {
    return this.speed;
  }
  
  @DesignerProperty(defaultValue="0.0", editorType="float")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public void Speed(float paramFloat)
  {
    this.speed = paramFloat;
  }
  
  @SimpleEvent
  public void TouchDown(float paramFloat1, float paramFloat2)
  {
    postEvent(this, "TouchDown", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) });
  }
  
  @SimpleEvent
  public void TouchUp(float paramFloat1, float paramFloat2)
  {
    postEvent(this, "TouchUp", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) });
  }
  
  @SimpleEvent
  public void Touched(float paramFloat1, float paramFloat2)
  {
    postEvent(this, "Touched", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) });
  }
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty
  public void Visible(boolean paramBoolean)
  {
    this.visible = paramBoolean;
    registerChange();
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="True if the sprite is visible.")
  public boolean Visible()
  {
    return this.visible;
  }
  
  @SimpleProperty(description="The horizontal coordinate of the left edge of the sprite, increasing as the sprite moves to the right.")
  public double X()
  {
    return this.xLeft;
  }
  
  @DesignerProperty(defaultValue="0.0", editorType="float")
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public void X(double paramDouble)
  {
    this.xLeft = paramDouble;
    registerChange();
  }
  
  @SimpleProperty(description="The vertical coordinate of the top of the sprite, increasing as the sprite moves down.")
  public double Y()
  {
    return this.yTop;
  }
  
  @DesignerProperty(defaultValue="0.0", editorType="float")
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public void Y(double paramDouble)
  {
    this.yTop = paramDouble;
    registerChange();
  }
  
  @SimpleProperty(description="How the sprite should be layered relative to other sprits, with higher-numbered layers in front of lower-numbered layers.")
  public double Z()
  {
    return this.zLayer;
  }
  
  @DesignerProperty(defaultValue="1.0", editorType="float")
  @SimpleProperty(category=PropertyCategory.APPEARANCE)
  public void Z(double paramDouble)
  {
    this.zLayer = paramDouble;
    this.canvas.changeSpriteLayer(this);
  }
  
  public void alarm()
  {
    if ((this.initialized) && (this.speed != 0.0F))
    {
      updateCoordinates();
      registerChange();
    }
  }
  
  public boolean containsPoint(double paramDouble1, double paramDouble2)
  {
    return (paramDouble1 >= this.xLeft) && (paramDouble1 < this.xLeft + Width()) && (paramDouble2 >= this.yTop) && (paramDouble2 < this.yTop + Height());
  }
  
  public BoundingBox getBoundingBox(int paramInt)
  {
    return new BoundingBox(X() - paramInt, Y() - paramInt, X() + Width() - 1.0D + paramInt, Y() + Height() - 1.0D + paramInt);
  }
  
  public HandlesEventDispatching getDispatchDelegate()
  {
    return this.canvas.$form();
  }
  
  protected int hitEdge()
  {
    if (!this.canvas.ready()) {
      return 0;
    }
    return hitEdge(this.canvas.Width(), this.canvas.Height());
  }
  
  protected int hitEdge(int paramInt1, int paramInt2)
  {
    boolean bool1 = overWestEdge();
    boolean bool2 = overNorthEdge();
    boolean bool3 = overEastEdge(paramInt1);
    boolean bool4 = overSouthEdge(paramInt2);
    if ((!bool2) && (!bool4) && (!bool3) && (!bool1)) {
      return 0;
    }
    MoveIntoBounds();
    if (bool1)
    {
      if (bool2) {
        return -4;
      }
      if (bool4) {
        return -2;
      }
      return -3;
    }
    if (bool3)
    {
      if (bool2) {
        return 2;
      }
      if (bool4) {
        return 4;
      }
      return 3;
    }
    if (bool2) {
      return 1;
    }
    if (bool4) {
      return -1;
    }
    throw new AssertionFailure("Unreachable code hit in Sprite.hitEdge()");
  }
  
  public boolean intersectsWith(BoundingBox paramBoundingBox)
  {
    BoundingBox localBoundingBox = getBoundingBox(0);
    if (!localBoundingBox.intersectDestructively(paramBoundingBox)) {}
    for (;;)
    {
      return false;
      for (double d1 = localBoundingBox.getLeft(); d1 < localBoundingBox.getRight(); d1 += 1.0D) {
        for (double d2 = localBoundingBox.getTop(); d2 < localBoundingBox.getBottom(); d2 += 1.0D) {
          if (containsPoint(d1, d2)) {
            return true;
          }
        }
      }
    }
  }
  
  @SimpleFunction
  protected final void moveIntoBounds(int paramInt1, int paramInt2)
  {
    int i = 0;
    if (Width() > paramInt1)
    {
      if (this.xLeft != 0.0D)
      {
        this.xLeft = 0.0D;
        i = 1;
      }
      if (Height() <= paramInt2) {
        break label100;
      }
      if (this.yTop != 0.0D)
      {
        this.yTop = 0.0D;
        i = 1;
      }
    }
    for (;;)
    {
      if (i != 0) {
        registerChange();
      }
      return;
      if (overWestEdge())
      {
        this.xLeft = 0.0D;
        i = 1;
        break;
      }
      if (!overEastEdge(paramInt1)) {
        break;
      }
      this.xLeft = (paramInt1 - Width());
      i = 1;
      break;
      label100:
      if (overNorthEdge())
      {
        this.yTop = 0.0D;
        i = 1;
      }
      else if (overSouthEdge(paramInt2))
      {
        this.yTop = (paramInt2 - Height());
        i = 1;
      }
    }
  }
  
  public void onDelete()
  {
    this.timerInternal.Enabled(false);
    this.canvas.removeSprite(this);
  }
  
  public void onDestroy()
  {
    this.timerInternal.Enabled(false);
  }
  
  protected abstract void onDraw(android.graphics.Canvas paramCanvas);
  
  protected void postEvent(final Sprite paramSprite, final String paramString, final Object... paramVarArgs)
  {
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        EventDispatcher.dispatchEvent(paramSprite, paramString, paramVarArgs);
      }
    });
  }
  
  protected void registerChange()
  {
    if (!this.initialized)
    {
      this.canvas.getView().invalidate();
      return;
    }
    int i = hitEdge();
    if (i != 0) {
      EdgeReached(i);
    }
    this.canvas.registerChange(this);
  }
  
  protected void updateCoordinates()
  {
    this.xLeft += this.speed * this.headingCos;
    this.yTop += this.speed * this.headingSin;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\Sprite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */