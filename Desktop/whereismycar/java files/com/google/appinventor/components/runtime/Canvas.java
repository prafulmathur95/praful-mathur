package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.BoundingBox;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.FileUtil.FileException;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.PaintUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@DesignerComponent(category=ComponentCategory.ANIMATION, description="<p>A two-dimensional touch-sensitive rectangular panel on which drawing can be done and sprites can be moved.</p> <p>The <code>BackgroundColor</code>, <code>PaintColor</code>, <code>BackgroundImage</code>, <code>Width</code>, and <code>Height</code> of the Canvas can be set in either the Designer or in the Blocks Editor.  The <code>Width</code> and <code>Height</code> are measured in pixels and must be positive.</p><p>Any location on the Canvas can be specified as a pair of (X, Y) values, where <ul> <li>X is the number of pixels away from the left edge of the Canvas</li><li>Y is the number of pixels away from the top edge of the Canvas</li></ul>.</p> <p>There are events to tell when and where a Canvas has been touched or a <code>Sprite</code> (<code>ImageSprite</code> or <code>Ball</code>) has been dragged.  There are also methods for drawing points, lines, and circles.</p>", version=10)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE")
public final class Canvas
  extends AndroidViewComponent
  implements ComponentContainer
{
  private static final int DEFAULT_BACKGROUND_COLOR = -1;
  private static final float DEFAULT_LINE_WIDTH = 2.0F;
  private static final int DEFAULT_PAINT_COLOR = -16777216;
  private static final int DEFAULT_TEXTALIGNMENT = 1;
  private static final int FLING_INTERVAL = 1000;
  private static final String LOG_TAG = "Canvas";
  private static final int MIN_WIDTH_HEIGHT = 1;
  private int backgroundColor;
  private String backgroundImagePath = "";
  private final Activity context;
  private boolean drawn;
  private final GestureDetector mGestureDetector;
  private final MotionEventParser motionEventParser;
  private final Paint paint;
  private int paintColor;
  private final List<Sprite> sprites;
  private int textAlignment;
  private final CanvasView view;
  
  public Canvas(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer);
    this.context = paramComponentContainer.$context();
    this.view = new CanvasView(this.context);
    paramComponentContainer.$add(this);
    this.paint = new Paint();
    this.paint.setStrokeWidth(2.0F);
    PaintColor(-16777216);
    BackgroundColor(-1);
    TextAlignment(1);
    FontSize(14.0F);
    this.sprites = new LinkedList();
    this.motionEventParser = new MotionEventParser();
    this.mGestureDetector = new GestureDetector(this.context, new FlingGestureListener());
  }
  
  private void changePaint(Paint paramPaint, int paramInt)
  {
    if (paramInt == 0)
    {
      PaintUtil.changePaint(paramPaint, -16777216);
      return;
    }
    if (paramInt == 16777215)
    {
      PaintUtil.changePaintTransparent(paramPaint);
      return;
    }
    PaintUtil.changePaint(paramPaint, paramInt);
  }
  
  /* Error */
  private String saveFile(java.io.File paramFile, Bitmap.CompressFormat paramCompressFormat, String paramString)
  {
    // Byte code:
    //   0: new 171	java/io/FileOutputStream
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 174	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   8: astore 5
    //   10: aload_0
    //   11: getfield 82	com/google/appinventor/components/runtime/Canvas:view	Lcom/google/appinventor/components/runtime/Canvas$CanvasView;
    //   14: invokestatic 178	com/google/appinventor/components/runtime/Canvas$CanvasView:access$1200	(Lcom/google/appinventor/components/runtime/Canvas$CanvasView;)Landroid/graphics/Bitmap;
    //   17: ifnonnull +39 -> 56
    //   20: aload_0
    //   21: getfield 82	com/google/appinventor/components/runtime/Canvas:view	Lcom/google/appinventor/components/runtime/Canvas$CanvasView;
    //   24: invokestatic 181	com/google/appinventor/components/runtime/Canvas$CanvasView:access$1300	(Lcom/google/appinventor/components/runtime/Canvas$CanvasView;)Landroid/graphics/Bitmap;
    //   27: astore 4
    //   29: aload 4
    //   31: aload_2
    //   32: bipush 100
    //   34: aload 5
    //   36: invokevirtual 187	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   39: istore 6
    //   41: aload 5
    //   43: invokevirtual 190	java/io/FileOutputStream:close	()V
    //   46: iload 6
    //   48: ifeq +60 -> 108
    //   51: aload_1
    //   52: invokevirtual 196	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   55: areturn
    //   56: aload_0
    //   57: getfield 82	com/google/appinventor/components/runtime/Canvas:view	Lcom/google/appinventor/components/runtime/Canvas$CanvasView;
    //   60: invokestatic 178	com/google/appinventor/components/runtime/Canvas$CanvasView:access$1200	(Lcom/google/appinventor/components/runtime/Canvas$CanvasView;)Landroid/graphics/Bitmap;
    //   63: astore 4
    //   65: goto -36 -> 29
    //   68: astore_2
    //   69: aload 5
    //   71: invokevirtual 190	java/io/FileOutputStream:close	()V
    //   74: aload_2
    //   75: athrow
    //   76: astore_2
    //   77: aload_0
    //   78: getfield 200	com/google/appinventor/components/runtime/Canvas:container	Lcom/google/appinventor/components/runtime/ComponentContainer;
    //   81: invokeinterface 204 1 0
    //   86: aload_0
    //   87: aload_3
    //   88: sipush 707
    //   91: iconst_1
    //   92: anewarray 206	java/lang/Object
    //   95: dup
    //   96: iconst_0
    //   97: aload_1
    //   98: invokevirtual 196	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   101: aastore
    //   102: invokevirtual 212	com/google/appinventor/components/runtime/Form:dispatchErrorOccurredEvent	(Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   105: ldc 69
    //   107: areturn
    //   108: aload_0
    //   109: getfield 200	com/google/appinventor/components/runtime/Canvas:container	Lcom/google/appinventor/components/runtime/ComponentContainer;
    //   112: invokeinterface 204 1 0
    //   117: aload_0
    //   118: aload_3
    //   119: sipush 1001
    //   122: iconst_0
    //   123: anewarray 206	java/lang/Object
    //   126: invokevirtual 212	com/google/appinventor/components/runtime/Form:dispatchErrorOccurredEvent	(Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   129: goto -24 -> 105
    //   132: astore_1
    //   133: aload_0
    //   134: getfield 200	com/google/appinventor/components/runtime/Canvas:container	Lcom/google/appinventor/components/runtime/ComponentContainer;
    //   137: invokeinterface 204 1 0
    //   142: aload_0
    //   143: aload_3
    //   144: sipush 708
    //   147: iconst_1
    //   148: anewarray 206	java/lang/Object
    //   151: dup
    //   152: iconst_0
    //   153: aload_1
    //   154: invokevirtual 215	java/io/IOException:getMessage	()Ljava/lang/String;
    //   157: aastore
    //   158: invokevirtual 212	com/google/appinventor/components/runtime/Form:dispatchErrorOccurredEvent	(Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   161: goto -56 -> 105
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	164	0	this	Canvas
    //   0	164	1	paramFile	java.io.File
    //   0	164	2	paramCompressFormat	Bitmap.CompressFormat
    //   0	164	3	paramString	String
    //   27	37	4	localBitmap	Bitmap
    //   8	62	5	localFileOutputStream	java.io.FileOutputStream
    //   39	8	6	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   29	41	68	finally
    //   0	29	76	java/io/FileNotFoundException
    //   41	46	76	java/io/FileNotFoundException
    //   51	56	76	java/io/FileNotFoundException
    //   56	65	76	java/io/FileNotFoundException
    //   69	76	76	java/io/FileNotFoundException
    //   108	129	76	java/io/FileNotFoundException
    //   0	29	132	java/io/IOException
    //   41	46	132	java/io/IOException
    //   51	56	132	java/io/IOException
    //   56	65	132	java/io/IOException
    //   69	76	132	java/io/IOException
    //   108	129	132	java/io/IOException
  }
  
  public void $add(AndroidViewComponent paramAndroidViewComponent)
  {
    throw new UnsupportedOperationException("Canvas.$add() called");
  }
  
  public Activity $context()
  {
    return this.context;
  }
  
  public Form $form()
  {
    return this.container.$form();
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="The color of the canvas background.")
  public int BackgroundColor()
  {
    return this.backgroundColor;
  }
  
  @DesignerProperty(defaultValue="&HFFFFFFFF", editorType="color")
  @SimpleProperty
  public void BackgroundColor(int paramInt)
  {
    this.view.setBackgroundColor(paramInt);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="The name of a file containing the background image for the canvas")
  public String BackgroundImage()
  {
    return this.backgroundImagePath;
  }
  
  @DesignerProperty(defaultValue="", editorType="asset")
  @SimpleProperty
  public void BackgroundImage(String paramString)
  {
    this.view.setBackgroundImage(paramString);
  }
  
  @SimpleFunction(description="Clears anything drawn on this Canvas but not any background color or image.")
  public void Clear()
  {
    this.view.clearDrawingLayer();
  }
  
  @SimpleEvent
  public void Dragged(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, boolean paramBoolean)
  {
    EventDispatcher.dispatchEvent(this, "Dragged", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3), Float.valueOf(paramFloat4), Float.valueOf(paramFloat5), Float.valueOf(paramFloat6), Boolean.valueOf(paramBoolean) });
  }
  
  @SimpleFunction
  public void DrawCircle(int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean)
  {
    Paint localPaint = new Paint(this.paint);
    if (paramBoolean) {}
    for (Paint.Style localStyle = Paint.Style.FILL;; localStyle = Paint.Style.STROKE)
    {
      localPaint.setStyle(localStyle);
      this.view.canvas.drawCircle(paramInt1, paramInt2, paramFloat, localPaint);
      this.view.invalidate();
      return;
    }
  }
  
  @SimpleFunction
  public void DrawLine(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.view.canvas.drawLine(paramInt1, paramInt2, paramInt3, paramInt4, this.paint);
    this.view.invalidate();
  }
  
  @SimpleFunction
  public void DrawPoint(int paramInt1, int paramInt2)
  {
    this.view.canvas.drawPoint(paramInt1, paramInt2, this.paint);
    this.view.invalidate();
  }
  
  @SimpleFunction(description="Draws the specified text relative to the specified coordinates using the values of the FontSize and TextAlignment properties.")
  public void DrawText(String paramString, int paramInt1, int paramInt2)
  {
    this.view.canvas.drawText(paramString, paramInt1, paramInt2, this.paint);
    this.view.invalidate();
  }
  
  @SimpleFunction(description="Draws the specified text starting at the specified coordinates at the specified angle using the values of the FontSize and TextAlignment properties.")
  public void DrawTextAtAngle(String paramString, int paramInt1, int paramInt2, float paramFloat)
  {
    this.view.drawTextAtAngle(paramString, paramInt1, paramInt2, paramFloat);
  }
  
  @SimpleEvent
  public void Flung(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, boolean paramBoolean)
  {
    EventDispatcher.dispatchEvent(this, "Flung", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3), Float.valueOf(paramFloat4), Float.valueOf(paramFloat5), Float.valueOf(paramFloat6), Boolean.valueOf(paramBoolean) });
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="The font size of text drawn on the canvas.")
  public float FontSize()
  {
    return this.paint.getTextSize();
  }
  
  @DesignerProperty(defaultValue="14.0", editorType="non_negative_float")
  @SimpleProperty
  public void FontSize(float paramFloat)
  {
    this.paint.setTextSize(paramFloat);
  }
  
  @SimpleFunction(description="Gets the color of the specified point. This includes the background and any drawn points, lines, or circles but not sprites.")
  public int GetBackgroundPixelColor(int paramInt1, int paramInt2)
  {
    return this.view.getBackgroundPixelColor(paramInt1, paramInt2);
  }
  
  @SimpleFunction(description="Gets the color of the specified point.")
  public int GetPixelColor(int paramInt1, int paramInt2)
  {
    return this.view.getPixelColor(paramInt1, paramInt2);
  }
  
  @SimpleProperty
  public void Height(int paramInt)
  {
    if ((paramInt > 0) || (paramInt == -2) || (paramInt == -1))
    {
      super.Height(paramInt);
      return;
    }
    this.container.$form().dispatchErrorOccurredEvent(this, "Height", 1003, new Object[0]);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="The width of lines drawn on the canvas.")
  public float LineWidth()
  {
    return this.paint.getStrokeWidth();
  }
  
  @DesignerProperty(defaultValue="2.0", editorType="non_negative_float")
  @SimpleProperty
  public void LineWidth(float paramFloat)
  {
    this.paint.setStrokeWidth(paramFloat);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="The color in which lines are drawn")
  public int PaintColor()
  {
    return this.paintColor;
  }
  
  @DesignerProperty(defaultValue="&HFF000000", editorType="color")
  @SimpleProperty
  public void PaintColor(int paramInt)
  {
    this.paintColor = paramInt;
    changePaint(this.paint, paramInt);
  }
  
  @SimpleFunction(description="Saves a picture of this Canvas to the device's external storage. If an error occurs, the Screen's ErrorOccurred event will be called.")
  public String Save()
  {
    try
    {
      String str = saveFile(FileUtil.getPictureFile("png"), Bitmap.CompressFormat.PNG, "Save");
      return str;
    }
    catch (IOException localIOException)
    {
      this.container.$form().dispatchErrorOccurredEvent(this, "Save", 708, new Object[] { localIOException.getMessage() });
      return "";
    }
    catch (FileUtil.FileException localFileException)
    {
      for (;;)
      {
        this.container.$form().dispatchErrorOccurredEvent(this, "Save", localFileException.getErrorMessageNumber(), new Object[0]);
      }
    }
  }
  
  @SimpleFunction(description="Saves a picture of this Canvas to the device's external storage in the file named fileName. fileName must end with one of .jpg, .jpeg, or .png, which determines the file type.")
  public String SaveAs(String paramString)
  {
    Bitmap.CompressFormat localCompressFormat;
    if ((paramString.endsWith(".jpg")) || (paramString.endsWith(".jpeg"))) {
      localCompressFormat = Bitmap.CompressFormat.JPEG;
    }
    for (;;)
    {
      try
      {
        paramString = saveFile(FileUtil.getExternalFile(paramString), localCompressFormat, "SaveAs");
        return paramString;
      }
      catch (IOException paramString)
      {
        this.container.$form().dispatchErrorOccurredEvent(this, "SaveAs", 708, new Object[] { paramString.getMessage() });
        return "";
      }
      catch (FileUtil.FileException paramString)
      {
        this.container.$form().dispatchErrorOccurredEvent(this, "SaveAs", paramString.getErrorMessageNumber(), new Object[0]);
        continue;
      }
      if (paramString.endsWith(".png"))
      {
        localCompressFormat = Bitmap.CompressFormat.PNG;
      }
      else
      {
        if (paramString.contains(".")) {
          continue;
        }
        paramString = paramString + ".png";
        localCompressFormat = Bitmap.CompressFormat.PNG;
      }
    }
    this.container.$form().dispatchErrorOccurredEvent(this, "SaveAs", 706, new Object[0]);
    return "";
  }
  
  @SimpleFunction(description="Sets the color of the specified point. This differs from DrawPoint by having an argument for color.")
  public void SetBackgroundPixelColor(int paramInt1, int paramInt2, int paramInt3)
  {
    Paint localPaint = new Paint();
    PaintUtil.changePaint(localPaint, paramInt3);
    this.view.canvas.drawPoint(paramInt1, paramInt2, localPaint);
    this.view.invalidate();
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Determines the alignment of the text drawn by DrawText() or DrawAngle() with respect to the point specified by that command.", userVisible=true)
  public int TextAlignment()
  {
    return this.textAlignment;
  }
  
  @DesignerProperty(defaultValue="1", editorType="textalignment")
  @SimpleProperty(userVisible=true)
  public void TextAlignment(int paramInt)
  {
    this.textAlignment = paramInt;
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      this.paint.setTextAlign(Paint.Align.LEFT);
      return;
    case 1: 
      this.paint.setTextAlign(Paint.Align.CENTER);
      return;
    }
    this.paint.setTextAlign(Paint.Align.RIGHT);
  }
  
  @SimpleEvent
  public void TouchDown(float paramFloat1, float paramFloat2)
  {
    EventDispatcher.dispatchEvent(this, "TouchDown", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) });
  }
  
  @SimpleEvent
  public void TouchUp(float paramFloat1, float paramFloat2)
  {
    EventDispatcher.dispatchEvent(this, "TouchUp", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2) });
  }
  
  @SimpleEvent
  public void Touched(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    EventDispatcher.dispatchEvent(this, "Touched", new Object[] { Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Boolean.valueOf(paramBoolean) });
  }
  
  @SimpleProperty
  public void Width(int paramInt)
  {
    if ((paramInt > 0) || (paramInt == -2) || (paramInt == -1))
    {
      super.Width(paramInt);
      return;
    }
    this.container.$form().dispatchErrorOccurredEvent(this, "Width", 1002, new Object[0]);
  }
  
  void addSprite(Sprite paramSprite)
  {
    int i = 0;
    while (i < this.sprites.size())
    {
      if (((Sprite)this.sprites.get(i)).Z() > paramSprite.Z())
      {
        this.sprites.add(i, paramSprite);
        return;
      }
      i += 1;
    }
    this.sprites.add(paramSprite);
  }
  
  void changeSpriteLayer(Sprite paramSprite)
  {
    removeSprite(paramSprite);
    addSprite(paramSprite);
    this.view.invalidate();
  }
  
  protected void findSpriteCollisions(Sprite paramSprite)
  {
    Iterator localIterator = this.sprites.iterator();
    while (localIterator.hasNext())
    {
      Sprite localSprite = (Sprite)localIterator.next();
      if (localSprite != paramSprite) {
        if (paramSprite.CollidingWith(localSprite))
        {
          if ((!paramSprite.Visible()) || (!paramSprite.Enabled()) || (!localSprite.Visible()) || (!localSprite.Enabled()) || (!Sprite.colliding(localSprite, paramSprite)))
          {
            paramSprite.NoLongerCollidingWith(localSprite);
            localSprite.NoLongerCollidingWith(paramSprite);
          }
        }
        else if ((paramSprite.Visible()) && (paramSprite.Enabled()) && (localSprite.Visible()) && (localSprite.Enabled()) && (Sprite.colliding(localSprite, paramSprite)))
        {
          paramSprite.CollidedWith(localSprite);
          localSprite.CollidedWith(paramSprite);
        }
      }
    }
  }
  
  public View getView()
  {
    return this.view;
  }
  
  public boolean ready()
  {
    return this.drawn;
  }
  
  void registerChange(Sprite paramSprite)
  {
    this.view.invalidate();
    findSpriteCollisions(paramSprite);
  }
  
  void removeSprite(Sprite paramSprite)
  {
    this.sprites.remove(paramSprite);
  }
  
  public void setChildHeight(AndroidViewComponent paramAndroidViewComponent, int paramInt)
  {
    throw new UnsupportedOperationException("Canvas.setChildHeight() called");
  }
  
  public void setChildWidth(AndroidViewComponent paramAndroidViewComponent, int paramInt)
  {
    throw new UnsupportedOperationException("Canvas.setChildWidth() called");
  }
  
  private final class CanvasView
    extends View
  {
    private BitmapDrawable backgroundDrawable;
    private Bitmap bitmap = Bitmap.createBitmap(32, 48, Bitmap.Config.ARGB_8888);
    private android.graphics.Canvas canvas = new android.graphics.Canvas(this.bitmap);
    private Bitmap completeCache;
    private Bitmap scaledBackgroundBitmap;
    
    public CanvasView(Context paramContext)
    {
      super();
    }
    
    private Bitmap buildCache()
    {
      setDrawingCacheEnabled(true);
      destroyDrawingCache();
      Object localObject2 = getDrawingCache();
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        int i = getWidth();
        int j = getHeight();
        localObject1 = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
        localObject2 = new android.graphics.Canvas((Bitmap)localObject1);
        layout(0, 0, i, j);
        draw((android.graphics.Canvas)localObject2);
      }
      return (Bitmap)localObject1;
    }
    
    private void clearDrawingLayer()
    {
      this.canvas.drawColor(0, PorterDuff.Mode.CLEAR);
      invalidate();
    }
    
    private void drawTextAtAngle(String paramString, int paramInt1, int paramInt2, float paramFloat)
    {
      this.canvas.save();
      this.canvas.rotate(-paramFloat, paramInt1, paramInt2);
      this.canvas.drawText(paramString, paramInt1, paramInt2, Canvas.this.paint);
      this.canvas.restore();
      invalidate();
    }
    
    private int getBackgroundPixelColor(int paramInt1, int paramInt2)
    {
      int i;
      if ((paramInt1 < 0) || (paramInt1 >= this.bitmap.getWidth()) || (paramInt2 < 0) || (paramInt2 >= this.bitmap.getHeight())) {
        i = 16777215;
      }
      for (;;)
      {
        return i;
        try
        {
          int j = this.bitmap.getPixel(paramInt1, paramInt2);
          i = j;
          if (j == 0)
          {
            if (this.backgroundDrawable != null)
            {
              if (this.scaledBackgroundBitmap == null) {
                this.scaledBackgroundBitmap = Bitmap.createScaledBitmap(this.backgroundDrawable.getBitmap(), this.bitmap.getWidth(), this.bitmap.getHeight(), false);
              }
              return this.scaledBackgroundBitmap.getPixel(paramInt1, paramInt2);
            }
            if (Color.alpha(Canvas.this.backgroundColor) != 0)
            {
              paramInt1 = Canvas.this.backgroundColor;
              return paramInt1;
            }
            return 16777215;
          }
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          Log.e("Canvas", String.format("Returning COLOR_NONE (exception) from getBackgroundPixelColor.", new Object[0]));
        }
      }
      return 16777215;
    }
    
    private int getPixelColor(int paramInt1, int paramInt2)
    {
      if ((paramInt1 < 0) || (paramInt1 >= this.bitmap.getWidth()) || (paramInt2 < 0) || (paramInt2 >= this.bitmap.getHeight())) {
        return 16777215;
      }
      if (this.completeCache == null)
      {
        int j = 0;
        Iterator localIterator = Canvas.this.sprites.iterator();
        do
        {
          i = j;
          if (!localIterator.hasNext()) {
            break;
          }
        } while (!((Sprite)localIterator.next()).Visible());
        int i = 1;
        if (i == 0) {
          return getBackgroundPixelColor(paramInt1, paramInt2);
        }
        this.completeCache = buildCache();
      }
      try
      {
        paramInt1 = this.completeCache.getPixel(paramInt1, paramInt2);
        return paramInt1;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        Log.e("Canvas", String.format("Returning COLOR_NONE (exception) from getPixelColor.", new Object[0]));
      }
      return 16777215;
    }
    
    private int getSize(int paramInt1, int paramInt2)
    {
      int j = View.MeasureSpec.getMode(paramInt1);
      int i = View.MeasureSpec.getSize(paramInt1);
      if (j == 1073741824) {
        paramInt1 = i;
      }
      do
      {
        return paramInt1;
        paramInt1 = paramInt2;
      } while (j != Integer.MIN_VALUE);
      return Math.min(paramInt2, i);
    }
    
    public void onDraw(android.graphics.Canvas paramCanvas)
    {
      this.completeCache = null;
      super.onDraw(paramCanvas);
      paramCanvas.drawBitmap(this.bitmap, 0.0F, 0.0F, null);
      Iterator localIterator = Canvas.this.sprites.iterator();
      while (localIterator.hasNext()) {
        ((Sprite)localIterator.next()).onDraw(paramCanvas);
      }
      Canvas.access$102(Canvas.this, true);
    }
    
    protected void onMeasure(int paramInt1, int paramInt2)
    {
      Bitmap localBitmap;
      int j;
      if (this.backgroundDrawable != null)
      {
        localBitmap = this.backgroundDrawable.getBitmap();
        j = localBitmap.getWidth();
      }
      for (int i = localBitmap.getHeight();; i = 48)
      {
        setMeasuredDimension(getSize(paramInt1, j), getSize(paramInt2, i));
        return;
        j = 32;
      }
    }
    
    protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      paramInt3 = this.bitmap.getWidth();
      paramInt4 = this.bitmap.getHeight();
      Bitmap localBitmap;
      if ((paramInt1 != paramInt3) || (paramInt2 != paramInt4)) {
        localBitmap = this.bitmap;
      }
      try
      {
        Object localObject = Bitmap.createScaledBitmap(localBitmap, paramInt1, paramInt2, false);
        if (((Bitmap)localObject).isMutable())
        {
          this.bitmap = ((Bitmap)localObject);
          this.canvas = new android.graphics.Canvas(this.bitmap);
        }
        for (;;)
        {
          this.scaledBackgroundBitmap = null;
          return;
          this.bitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
          this.canvas = new android.graphics.Canvas(this.bitmap);
          localObject = new Rect(0, 0, paramInt3, paramInt4);
          RectF localRectF = new RectF(0.0F, 0.0F, paramInt1, paramInt2);
          this.canvas.drawBitmap(localBitmap, (Rect)localObject, localRectF, null);
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;)
        {
          Log.e("Canvas", "Bad values to createScaledBimap w = " + paramInt1 + ", h = " + paramInt2);
        }
      }
    }
    
    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      Canvas.this.container.$form().dontGrabTouchEventsForComponent();
      Canvas.this.motionEventParser.parse(paramMotionEvent);
      Canvas.this.mGestureDetector.onTouchEvent(paramMotionEvent);
      return true;
    }
    
    public void setBackgroundColor(int paramInt)
    {
      Canvas.access$502(Canvas.this, paramInt);
      if (this.backgroundDrawable == null) {
        super.setBackgroundColor(paramInt);
      }
      clearDrawingLayer();
    }
    
    void setBackgroundImage(String paramString)
    {
      Canvas localCanvas = Canvas.this;
      String str = paramString;
      if (paramString == null) {
        str = "";
      }
      Canvas.access$402(localCanvas, str);
      this.backgroundDrawable = null;
      this.scaledBackgroundBitmap = null;
      if (!TextUtils.isEmpty(Canvas.this.backgroundImagePath)) {}
      try
      {
        this.backgroundDrawable = MediaUtil.getBitmapDrawable(Canvas.this.container.$form(), Canvas.this.backgroundImagePath);
        setBackgroundDrawable(this.backgroundDrawable);
        if (this.backgroundDrawable == null) {
          super.setBackgroundColor(Canvas.this.backgroundColor);
        }
        clearDrawingLayer();
        return;
      }
      catch (IOException paramString)
      {
        for (;;)
        {
          Log.e("Canvas", "Unable to load " + Canvas.this.backgroundImagePath);
        }
      }
    }
  }
  
  class FlingGestureListener
    extends GestureDetector.SimpleOnGestureListener
  {
    FlingGestureListener() {}
    
    public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      float f1 = Math.max(0, (int)paramMotionEvent1.getX());
      float f2 = Math.max(0, (int)paramMotionEvent1.getY());
      paramFloat1 /= 1000.0F;
      paramFloat2 /= 1000.0F;
      float f3 = (float)Math.sqrt(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2);
      float f4 = (float)-Math.toDegrees(Math.atan2(paramFloat2, paramFloat1));
      int i = Canvas.this.Width();
      int j = Canvas.this.Height();
      paramMotionEvent1 = new BoundingBox(Math.max(0, (int)f1 - 12), Math.max(0, (int)f2 - 12), Math.min(i - 1, (int)f1 + 12), Math.min(j - 1, (int)f2 + 12));
      boolean bool = false;
      paramMotionEvent2 = Canvas.this.sprites.iterator();
      while (paramMotionEvent2.hasNext())
      {
        Sprite localSprite = (Sprite)paramMotionEvent2.next();
        if ((localSprite.Enabled()) && (localSprite.Visible()) && (localSprite.intersectsWith(paramMotionEvent1)))
        {
          localSprite.Flung(f1, f2, f3, f4, paramFloat1, paramFloat2);
          bool = true;
        }
      }
      Canvas.this.Flung(f1, f2, f3, f4, paramFloat1, paramFloat2, bool);
      return true;
    }
  }
  
  class MotionEventParser
  {
    public static final int FINGER_HEIGHT = 24;
    public static final int FINGER_WIDTH = 24;
    private static final int HALF_FINGER_HEIGHT = 12;
    private static final int HALF_FINGER_WIDTH = 12;
    public static final int TAP_THRESHOLD = 30;
    private static final int UNSET = -1;
    private boolean drag = false;
    private final List<Sprite> draggedSprites = new ArrayList();
    private boolean isDrag = false;
    private float lastX = -1.0F;
    private float lastY = -1.0F;
    private float startX = -1.0F;
    private float startY = -1.0F;
    
    MotionEventParser() {}
    
    void parse(MotionEvent paramMotionEvent)
    {
      int i = Canvas.this.Width();
      int j = Canvas.this.Height();
      float f1 = Math.max(0, (int)paramMotionEvent.getX());
      float f2 = Math.max(0, (int)paramMotionEvent.getY());
      Object localObject = new BoundingBox(Math.max(0, (int)f1 - 12), Math.max(0, (int)f2 - 12), Math.min(i - 1, (int)f1 + 12), Math.min(j - 1, (int)f2 + 12));
      boolean bool;
      switch (paramMotionEvent.getAction())
      {
      default: 
      case 0: 
      case 2: 
        Sprite localSprite;
        do
        {
          return;
          this.draggedSprites.clear();
          this.startX = f1;
          this.startY = f2;
          this.lastX = f1;
          this.lastY = f2;
          this.drag = false;
          this.isDrag = false;
          paramMotionEvent = Canvas.this.sprites.iterator();
          while (paramMotionEvent.hasNext())
          {
            localSprite = (Sprite)paramMotionEvent.next();
            if ((localSprite.Enabled()) && (localSprite.Visible()) && (localSprite.intersectsWith((BoundingBox)localObject)))
            {
              this.draggedSprites.add(localSprite);
              localSprite.TouchDown(this.startX, this.startY);
            }
          }
          Canvas.this.TouchDown(this.startX, this.startY);
          return;
          if ((this.startX == -1.0F) || (this.startY == -1.0F) || (this.lastX == -1.0F) || (this.lastY == -1.0F)) {
            Log.w("Canvas", "In Canvas.MotionEventParser.parse(), an ACTION_MOVE was passed without a preceding ACTION_DOWN: " + paramMotionEvent);
          }
        } while ((!this.isDrag) && (Math.abs(f1 - this.startX) < 30.0F) && (Math.abs(f2 - this.startY) < 30.0F));
        this.isDrag = true;
        this.drag = true;
        paramMotionEvent = Canvas.this.sprites.iterator();
        while (paramMotionEvent.hasNext())
        {
          localSprite = (Sprite)paramMotionEvent.next();
          if ((!this.draggedSprites.contains(localSprite)) && (localSprite.Enabled()) && (localSprite.Visible()) && (localSprite.intersectsWith((BoundingBox)localObject))) {
            this.draggedSprites.add(localSprite);
          }
        }
        bool = false;
        paramMotionEvent = this.draggedSprites.iterator();
        while (paramMotionEvent.hasNext())
        {
          localObject = (Sprite)paramMotionEvent.next();
          if ((((Sprite)localObject).Enabled()) && (((Sprite)localObject).Visible()))
          {
            ((Sprite)localObject).Dragged(this.startX, this.startY, this.lastX, this.lastY, f1, f2);
            bool = true;
          }
        }
        Canvas.this.Dragged(this.startX, this.startY, this.lastX, this.lastY, f1, f2, bool);
        this.lastX = f1;
        this.lastY = f2;
        return;
      }
      if (!this.drag)
      {
        bool = false;
        paramMotionEvent = this.draggedSprites.iterator();
        while (paramMotionEvent.hasNext())
        {
          localObject = (Sprite)paramMotionEvent.next();
          if ((((Sprite)localObject).Enabled()) && (((Sprite)localObject).Visible()))
          {
            ((Sprite)localObject).Touched(f1, f2);
            ((Sprite)localObject).TouchUp(f1, f2);
            bool = true;
          }
        }
        Canvas.this.Touched(f1, f2, bool);
      }
      for (;;)
      {
        Canvas.this.TouchUp(f1, f2);
        this.drag = false;
        this.startX = -1.0F;
        this.startY = -1.0F;
        this.lastX = -1.0F;
        this.lastY = -1.0F;
        return;
        paramMotionEvent = this.draggedSprites.iterator();
        while (paramMotionEvent.hasNext())
        {
          localObject = (Sprite)paramMotionEvent.next();
          if ((((Sprite)localObject).Enabled()) && (((Sprite)localObject).Visible()))
          {
            ((Sprite)localObject).Touched(f1, f2);
            ((Sprite)localObject).TouchUp(f1, f2);
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\Canvas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */