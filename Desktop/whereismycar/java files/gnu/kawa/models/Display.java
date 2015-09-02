package gnu.kawa.models;

import gnu.lists.FString;
import gnu.mapping.ThreadLocation;
import gnu.mapping.WrappedException;
import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.lang.reflect.Method;

public abstract class Display
{
  public static ThreadLocation myDisplay = new ThreadLocation("my-display");
  
  public static Dimension asDimension(Dimension2D paramDimension2D)
  {
    if (((paramDimension2D instanceof Dimension)) || (paramDimension2D == null)) {
      return (Dimension)paramDimension2D;
    }
    return new Dimension((int)(paramDimension2D.getWidth() + 0.5D), (int)(paramDimension2D.getHeight() + 0.5D));
  }
  
  public static Display getInstance()
  {
    Object localObject4 = myDisplay.get(null);
    if ((localObject4 instanceof Display)) {
      return (Display)localObject4;
    }
    Object localObject1;
    if (localObject4 == null) {
      localObject1 = "swing";
    }
    for (;;)
    {
      int i = ((String)localObject1).indexOf(',');
      String str = null;
      Object localObject3 = localObject1;
      if (i >= 0)
      {
        str = ((String)localObject1).substring(i + 1);
        localObject3 = ((String)localObject1).substring(0, i);
      }
      if (((String)localObject3).equals("swing")) {
        localObject1 = "gnu.kawa.swingviews.SwingDisplay";
      }
      try
      {
        for (;;)
        {
          localObject1 = (Display)Class.forName((String)localObject1).getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
          return (Display)localObject1;
          localObject1 = localObject4.toString();
          break;
          if (((String)localObject3).equals("swt"))
          {
            localObject1 = "gnu.kawa.swtviews.SwtDisplay";
          }
          else
          {
            localObject1 = localObject3;
            if (((String)localObject3).equals("echo2")) {
              localObject1 = "gnu.kawa.echo2.Echo2Display";
            }
          }
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        if (str == null) {
          throw new RuntimeException("no display toolkit: " + localObject4);
        }
        Object localObject2 = str;
      }
      catch (Throwable localThrowable)
      {
        throw WrappedException.wrapIfNeeded(localThrowable);
      }
    }
  }
  
  public abstract void addBox(Box paramBox, Object paramObject);
  
  public abstract void addButton(Button paramButton, Object paramObject);
  
  public abstract void addImage(DrawImage paramDrawImage, Object paramObject);
  
  public abstract void addLabel(Label paramLabel, Object paramObject);
  
  public void addSpacer(Spacer paramSpacer, Object paramObject)
  {
    throw new Error("makeView called on Spacer");
  }
  
  public void addText(Text paramText, Object paramObject)
  {
    throw new Error("makeView called on Text");
  }
  
  public abstract void addView(Object paramObject1, Object paramObject2);
  
  public Model coerceToModel(Object paramObject)
  {
    if (((paramObject instanceof FString)) || ((paramObject instanceof String))) {
      return new Label(paramObject.toString());
    }
    return (Model)paramObject;
  }
  
  public abstract Window makeWindow();
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\models\Display.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */