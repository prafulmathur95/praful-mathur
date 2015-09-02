package com.google.appinventor.components.runtime;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import com.google.appinventor.components.annotations.SimpleObject;

@SimpleObject
public class TableLayout
  implements Layout
{
  private final Handler handler;
  private final android.widget.TableLayout layoutManager;
  private int numColumns;
  private int numRows;
  
  TableLayout(Context paramContext, int paramInt1, int paramInt2)
  {
    this.layoutManager = new android.widget.TableLayout(paramContext);
    this.numColumns = paramInt1;
    this.numRows = paramInt2;
    this.handler = new Handler();
    int i = 0;
    while (i < paramInt2)
    {
      TableRow localTableRow = new TableRow(paramContext);
      int j = 0;
      while (j < paramInt1)
      {
        localTableRow.addView(newEmptyCellView(), j, newEmptyCellLayoutParams());
        j += 1;
      }
      this.layoutManager.addView(localTableRow, i, new TableLayout.LayoutParams());
      i += 1;
    }
  }
  
  private void addChild(AndroidViewComponent paramAndroidViewComponent)
  {
    int i = paramAndroidViewComponent.Row();
    int j = paramAndroidViewComponent.Column();
    if ((i == -1) || (j == -1))
    {
      addChildLater(paramAndroidViewComponent);
      return;
    }
    if ((i >= 0) && (i < this.numRows))
    {
      if ((j >= 0) && (j < this.numColumns))
      {
        TableRow localTableRow = (TableRow)this.layoutManager.getChildAt(i);
        localTableRow.removeViewAt(j);
        paramAndroidViewComponent = paramAndroidViewComponent.getView();
        localTableRow.addView(paramAndroidViewComponent, j, paramAndroidViewComponent.getLayoutParams());
        return;
      }
      Log.e("TableLayout", "Child has illegal Column property: " + paramAndroidViewComponent);
      return;
    }
    Log.e("TableLayout", "Child has illegal Row property: " + paramAndroidViewComponent);
  }
  
  private void addChildLater(final AndroidViewComponent paramAndroidViewComponent)
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        TableLayout.this.addChild(paramAndroidViewComponent);
      }
    });
  }
  
  private static TableRow.LayoutParams newCellLayoutParams()
  {
    return new TableRow.LayoutParams();
  }
  
  private static TableRow.LayoutParams newEmptyCellLayoutParams()
  {
    return new TableRow.LayoutParams(0, 0);
  }
  
  private View newEmptyCellView()
  {
    return new TextView(this.layoutManager.getContext());
  }
  
  public void add(AndroidViewComponent paramAndroidViewComponent)
  {
    paramAndroidViewComponent.getView().setLayoutParams(newCellLayoutParams());
    addChildLater(paramAndroidViewComponent);
  }
  
  public ViewGroup getLayoutManager()
  {
    return this.layoutManager;
  }
  
  int getNumColumns()
  {
    return this.numColumns;
  }
  
  int getNumRows()
  {
    return this.numRows;
  }
  
  void setNumColumns(int paramInt)
  {
    if (paramInt > this.numColumns)
    {
      this.layoutManager.getContext();
      i = 0;
      while (i < this.numRows)
      {
        localTableRow = (TableRow)this.layoutManager.getChildAt(i);
        j = this.numColumns;
        while (j < paramInt)
        {
          localTableRow.addView(newEmptyCellView(), j, newEmptyCellLayoutParams());
          j += 1;
        }
        i += 1;
      }
      this.numColumns = paramInt;
    }
    while (paramInt >= this.numColumns)
    {
      TableRow localTableRow;
      int j;
      return;
    }
    int i = 0;
    while (i < this.numRows)
    {
      ((TableRow)this.layoutManager.getChildAt(i)).removeViews(paramInt, this.numColumns - paramInt);
      i += 1;
    }
    this.numColumns = paramInt;
  }
  
  void setNumRows(int paramInt)
  {
    if (paramInt > this.numRows)
    {
      localContext = this.layoutManager.getContext();
      i = this.numRows;
      while (i < paramInt)
      {
        localTableRow = new TableRow(localContext);
        j = 0;
        while (j < this.numColumns)
        {
          localTableRow.addView(newEmptyCellView(), j, newEmptyCellLayoutParams());
          j += 1;
        }
        this.layoutManager.addView(localTableRow, i, new TableLayout.LayoutParams());
        i += 1;
      }
      this.numRows = paramInt;
    }
    while (paramInt >= this.numRows)
    {
      Context localContext;
      int i;
      TableRow localTableRow;
      int j;
      return;
    }
    this.layoutManager.removeViews(paramInt, this.numRows - paramInt);
    this.numRows = paramInt;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\TableLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */