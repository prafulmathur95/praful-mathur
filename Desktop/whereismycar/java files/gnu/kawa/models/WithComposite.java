package gnu.kawa.models;

import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class WithComposite
  implements Paintable
{
  Composite[] composite;
  Paintable[] paintable;
  
  public static WithComposite make(Paintable paramPaintable, Composite paramComposite)
  {
    WithComposite localWithComposite = new WithComposite();
    localWithComposite.paintable = new Paintable[] { paramPaintable };
    localWithComposite.composite = new Composite[] { paramComposite };
    return localWithComposite;
  }
  
  public static WithComposite make(Paintable[] paramArrayOfPaintable, Composite[] paramArrayOfComposite)
  {
    WithComposite localWithComposite = new WithComposite();
    localWithComposite.paintable = paramArrayOfPaintable;
    localWithComposite.composite = paramArrayOfComposite;
    return localWithComposite;
  }
  
  public static WithComposite make(Object[] paramArrayOfObject)
  {
    int j = 0;
    int i = paramArrayOfObject.length;
    for (;;)
    {
      int k = i - 1;
      if (k < 0) {
        break;
      }
      i = k;
      if ((paramArrayOfObject[k] instanceof Paintable))
      {
        j += 1;
        i = k;
      }
    }
    Paintable[] arrayOfPaintable = new Paintable[j];
    Composite[] arrayOfComposite = new Composite[j];
    Composite localComposite = null;
    j = 0;
    i = 0;
    if (i < paramArrayOfObject.length)
    {
      Object localObject = paramArrayOfObject[i];
      if ((localObject instanceof Paintable))
      {
        arrayOfPaintable[j] = ((Paintable)paramArrayOfObject[i]);
        arrayOfComposite[j] = localComposite;
        j += 1;
      }
      for (;;)
      {
        i += 1;
        break;
        localComposite = (Composite)localObject;
      }
    }
    return make(arrayOfPaintable, arrayOfComposite);
  }
  
  public Rectangle2D getBounds2D()
  {
    int j = this.paintable.length;
    Object localObject;
    if (j == 0)
    {
      localObject = null;
      return (Rectangle2D)localObject;
    }
    Rectangle2D localRectangle2D = this.paintable[0].getBounds2D();
    int i = 1;
    for (;;)
    {
      localObject = localRectangle2D;
      if (i >= j) {
        break;
      }
      localRectangle2D = localRectangle2D.createUnion(this.paintable[i].getBounds2D());
      i += 1;
    }
  }
  
  public void paint(Graphics2D paramGraphics2D)
  {
    Composite localComposite1 = paramGraphics2D.getComposite();
    Object localObject2 = localComposite1;
    Object localObject1 = localObject2;
    try
    {
      int j = this.paintable.length;
      int i = 0;
      while (i < j)
      {
        localObject1 = localObject2;
        Composite localComposite2 = this.composite[i];
        Object localObject4 = localObject2;
        if (localComposite2 != null)
        {
          localObject4 = localObject2;
          if (localComposite2 != localObject2)
          {
            localObject1 = localObject2;
            paramGraphics2D.setComposite(localComposite2);
            localObject4 = localComposite2;
          }
        }
        localObject1 = localObject4;
        this.paintable[i].paint(paramGraphics2D);
        i += 1;
        localObject2 = localObject4;
      }
      return;
    }
    finally
    {
      if (localObject1 != localComposite1) {
        paramGraphics2D.setComposite(localComposite1);
      }
    }
  }
  
  public Paintable transform(AffineTransform paramAffineTransform)
  {
    int j = this.paintable.length;
    Paintable[] arrayOfPaintable = new Paintable[j];
    int i = 0;
    while (i < j)
    {
      arrayOfPaintable[i] = this.paintable[i].transform(paramAffineTransform);
      i += 1;
    }
    return make(arrayOfPaintable, this.composite);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\models\WithComposite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */