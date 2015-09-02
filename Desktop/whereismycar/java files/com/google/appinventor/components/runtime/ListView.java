package com.google.appinventor.components.runtime;

import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ElementsUtil;
import com.google.appinventor.components.runtime.util.YailList;

@DesignerComponent(category=ComponentCategory.USERINTERFACE, description="<p>This is a visible component that displays a list of text elements. <br> The list can be set using the ElementsFromString property or using the Elements block in the blocks editor. </p>", iconName="images/listView.png", nonVisible=false, version=5)
@SimpleObject
public final class ListView
  extends AndroidViewComponent
  implements AdapterView.OnItemClickListener
{
  private static final int DEFAULT_BACKGROUND_COLOR = -16777216;
  private static final boolean DEFAULT_ENABLED = false;
  private static final int DEFAULT_SELECTION_COLOR = -3355444;
  private static final int DEFAULT_TEXT_COLOR = -1;
  private static final int DEFAULT_TEXT_SIZE = 22;
  private static final String LOG_TAG = "ListView";
  private ArrayAdapter<Spannable> adapter;
  private int backgroundColor;
  protected final ComponentContainer container;
  private YailList items;
  private final LinearLayout listViewLayout;
  private String selection;
  private int selectionColor;
  private int selectionIndex;
  private boolean showFilter = false;
  private int textColor;
  private int textSize;
  private EditText txtSearchBox;
  private final android.widget.ListView view;
  
  public ListView(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer);
    this.container = paramComponentContainer;
    this.items = YailList.makeEmptyList();
    this.view = new android.widget.ListView(paramComponentContainer.$context());
    this.view.setOnItemClickListener(this);
    this.view.setChoiceMode(1);
    this.view.setScrollingCacheEnabled(false);
    this.listViewLayout = new LinearLayout(paramComponentContainer.$context());
    this.listViewLayout.setOrientation(1);
    this.txtSearchBox = new EditText(paramComponentContainer.$context());
    this.txtSearchBox.setSingleLine(true);
    this.txtSearchBox.setWidth(-2);
    this.txtSearchBox.setPadding(10, 10, 10, 10);
    this.txtSearchBox.setHint("Search list...");
    this.txtSearchBox.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable) {}
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        ListView.this.adapter.getFilter().filter(paramAnonymousCharSequence);
      }
    });
    if (this.showFilter) {
      this.txtSearchBox.setVisibility(0);
    }
    for (;;)
    {
      Width(-2);
      BackgroundColor(-16777216);
      SelectionColor(-3355444);
      this.textColor = -1;
      TextColor(this.textColor);
      this.textSize = 22;
      TextSize(this.textSize);
      ElementsFromString("");
      this.listViewLayout.addView(this.txtSearchBox);
      this.listViewLayout.addView(this.view);
      this.listViewLayout.requestLayout();
      paramComponentContainer.$add(this);
      return;
      this.txtSearchBox.setVisibility(8);
    }
  }
  
  @SimpleEvent(description="Simple event to be raised after the an element has been chosen in the list. The selected element is available in the Selection property.")
  public void AfterPicking()
  {
    EventDispatcher.dispatchEvent(this, "AfterPicking", new Object[0]);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="The color of the listview background.")
  public int BackgroundColor()
  {
    return this.backgroundColor;
  }
  
  @DesignerProperty(defaultValue="&HFF000000", editorType="color")
  @SimpleProperty
  public void BackgroundColor(int paramInt)
  {
    this.backgroundColor = paramInt;
    setBackgroundColor(this.backgroundColor);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public YailList Elements()
  {
    return this.items;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="List of text elements to show in the ListView.  This willsignal an error if the elements are not text strings.")
  public void Elements(YailList paramYailList)
  {
    this.items = ElementsUtil.elements(paramYailList, "Listview");
    setAdapterData();
  }
  
  @DesignerProperty(defaultValue="", editorType="string")
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The TextView elements specified as a string with the items separated by commas such as: Cheese,Fruit,Bacon,Radish. Each word before the comma will be an element in the list.")
  public void ElementsFromString(String paramString)
  {
    this.items = ElementsUtil.elementsFromString(paramString);
    setAdapterData();
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Determines the height of the list on the view.")
  public void Height(int paramInt)
  {
    int i = paramInt;
    if (paramInt == -1) {
      i = -2;
    }
    super.Height(i);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Returns the text last selected in the ListView.")
  public String Selection()
  {
    return this.selection;
  }
  
  @DesignerProperty(defaultValue="", editorType="string")
  @SimpleProperty
  public void Selection(String paramString)
  {
    this.selection = paramString;
    this.selectionIndex = ElementsUtil.setSelectedIndexFromValue(paramString, this.items);
  }
  
  @SimpleProperty(description="The color of the item when it is selected.")
  public int SelectionColor()
  {
    return this.selectionColor;
  }
  
  @DesignerProperty(defaultValue="&HFFCCCCCC", editorType="color")
  @SimpleProperty
  public void SelectionColor(int paramInt)
  {
    this.selectionColor = paramInt;
    this.view.setSelector(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] { paramInt, paramInt }));
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The index of the currently selected item, starting at 1.  If no item is selected, the value will be 0.  If an attempt is made to set this to a number less than 1 or greater than the number of items in the ListView, SelectionIndex will be set to 0, and Selection will be set to the empty text.")
  public int SelectionIndex()
  {
    return this.selectionIndex;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Specifies the position of the selected item in the ListView. This could be used to retrievethe text at the chosen position. If an attempt is made to set this to a number less than 1 or greater than the number of items in the ListView, SelectionIndex will be set to 0, and Selection will be set to the empty text.")
  public void SelectionIndex(int paramInt)
  {
    this.selectionIndex = ElementsUtil.selectionIndex(paramInt, this.items);
    this.selection = ElementsUtil.setSelectionFromIndex(paramInt, this.items);
  }
  
  @DesignerProperty(defaultValue="False", editorType="boolean")
  @SimpleProperty(description="Sets visibility of ShowFilterBar. True will show the bar, False will hide it.")
  public void ShowFilterBar(boolean paramBoolean)
  {
    this.showFilter = paramBoolean;
    if (paramBoolean)
    {
      this.txtSearchBox.setVisibility(0);
      return;
    }
    this.txtSearchBox.setVisibility(8);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Returns current state of ShowFilterBar for visibility.")
  public boolean ShowFilterBar()
  {
    return this.showFilter;
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="The text color of the listview items.")
  public int TextColor()
  {
    return this.textColor;
  }
  
  @DesignerProperty(defaultValue="&HFFFFFFFF", editorType="color")
  @SimpleProperty
  public void TextColor(int paramInt)
  {
    this.textColor = paramInt;
    setAdapterData();
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="The text size of the listview items.")
  public int TextSize()
  {
    return this.textSize;
  }
  
  @DesignerProperty(defaultValue="22", editorType="non_negative_integer")
  @SimpleProperty
  public void TextSize(int paramInt)
  {
    if (paramInt > 1000) {}
    for (this.textSize = 999;; this.textSize = paramInt)
    {
      setAdapterData();
      return;
    }
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Determines the width of the list on the view.")
  public void Width(int paramInt)
  {
    int i = paramInt;
    if (paramInt == -1) {
      i = -2;
    }
    super.Width(i);
  }
  
  public View getView()
  {
    return this.listViewLayout;
  }
  
  public Spannable[] itemsToColoredText()
  {
    int j = this.items.size();
    Spannable[] arrayOfSpannable = new Spannable[j];
    int i = 1;
    while (i <= j)
    {
      SpannableString localSpannableString = new SpannableString(YailList.YailListElementToString(this.items.get(i)));
      localSpannableString.setSpan(new ForegroundColorSpan(this.textColor), 0, localSpannableString.length(), 0);
      localSpannableString.setSpan(new AbsoluteSizeSpan(this.textSize), 0, localSpannableString.length(), 0);
      arrayOfSpannable[(i - 1)] = localSpannableString;
      i += 1;
    }
    return arrayOfSpannable;
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.selection = paramAdapterView.getAdapter().getItem(paramInt).toString();
    this.selectionIndex = (paramInt + 1);
    AfterPicking();
  }
  
  public void setAdapterData()
  {
    this.adapter = new ArrayAdapter(this.container.$context(), 17367043, itemsToColoredText());
    this.view.setAdapter(this.adapter);
  }
  
  public void setBackgroundColor(int paramInt)
  {
    this.backgroundColor = paramInt;
    this.view.setBackgroundColor(this.backgroundColor);
    this.listViewLayout.setBackgroundColor(this.backgroundColor);
    this.view.setCacheColorHint(this.backgroundColor);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\ListView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */