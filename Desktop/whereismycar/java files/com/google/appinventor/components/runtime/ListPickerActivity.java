package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.google.appinventor.components.runtime.util.AnimationUtil;

public class ListPickerActivity
  extends Activity
  implements AdapterView.OnItemClickListener
{
  static int backgroundColor;
  static int itemColor;
  MyAdapter adapter;
  private String closeAnim = "";
  private ListView listView;
  EditText txtSearchBox;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = new LinearLayout(this);
    paramBundle.setOrientation(1);
    Object localObject1 = getIntent();
    if (((Intent)localObject1).hasExtra(ListPicker.LIST_ACTIVITY_ANIM_TYPE)) {
      this.closeAnim = ((Intent)localObject1).getStringExtra(ListPicker.LIST_ACTIVITY_ANIM_TYPE);
    }
    Object localObject2;
    if (((Intent)localObject1).hasExtra(ListPicker.LIST_ACTIVITY_ORIENTATION_TYPE))
    {
      localObject2 = ((Intent)localObject1).getStringExtra(ListPicker.LIST_ACTIVITY_ORIENTATION_TYPE).toLowerCase();
      if (((String)localObject2).equals("portrait")) {
        setRequestedOrientation(1);
      }
    }
    else
    {
      if (((Intent)localObject1).hasExtra(ListPicker.LIST_ACTIVITY_TITLE)) {
        setTitle(((Intent)localObject1).getStringExtra(ListPicker.LIST_ACTIVITY_TITLE));
      }
      if (!((Intent)localObject1).hasExtra(ListPicker.LIST_ACTIVITY_ARG_NAME)) {
        break label377;
      }
      localObject2 = getIntent().getStringArrayExtra(ListPicker.LIST_ACTIVITY_ARG_NAME);
      this.listView = new ListView(this);
      this.listView.setOnItemClickListener(this);
      this.listView.setScrollingCacheEnabled(false);
      itemColor = ((Intent)localObject1).getIntExtra(ListPicker.LIST_ACTIVITY_ITEM_TEXT_COLOR, -1);
      backgroundColor = ((Intent)localObject1).getIntExtra(ListPicker.LIST_ACTIVITY_BACKGROUND_COLOR, -16777216);
      paramBundle.setBackgroundColor(backgroundColor);
      this.adapter = new MyAdapter(this, (String[])localObject2);
      this.listView.setAdapter(this.adapter);
      localObject1 = ((Intent)localObject1).getStringExtra(ListPicker.LIST_ACTIVITY_SHOW_SEARCH_BAR);
      this.txtSearchBox = new EditText(this);
      this.txtSearchBox.setSingleLine(true);
      this.txtSearchBox.setWidth(-2);
      this.txtSearchBox.setPadding(10, 10, 10, 10);
      this.txtSearchBox.setHint("Search list...");
      if ((localObject1 == null) || (!((String)localObject1).equalsIgnoreCase("true"))) {
        this.txtSearchBox.setVisibility(8);
      }
      this.txtSearchBox.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable) {}
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          ListPickerActivity.this.adapter.getFilter().filter(paramAnonymousCharSequence);
        }
      });
    }
    for (;;)
    {
      paramBundle.addView(this.txtSearchBox);
      paramBundle.addView(this.listView);
      setContentView(paramBundle);
      paramBundle.requestLayout();
      ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
      getWindow().setSoftInputMode(3);
      return;
      if (!((String)localObject2).equals("landscape")) {
        break;
      }
      setRequestedOrientation(0);
      break;
      label377:
      setResult(0);
      finish();
      AnimationUtil.ApplyCloseScreenAnimation(this, this.closeAnim);
    }
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (String)paramAdapterView.getAdapter().getItem(paramInt);
    paramView = new Intent();
    paramView.putExtra(ListPicker.LIST_ACTIVITY_RESULT_NAME, paramAdapterView);
    paramView.putExtra(ListPicker.LIST_ACTIVITY_RESULT_INDEX, paramInt + 1);
    this.closeAnim = paramAdapterView;
    setResult(-1, paramView);
    finish();
    AnimationUtil.ApplyCloseScreenAnimation(this, this.closeAnim);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      boolean bool = super.onKeyDown(paramInt, paramKeyEvent);
      AnimationUtil.ApplyCloseScreenAnimation(this, this.closeAnim);
      return bool;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  private static class MyAdapter
    extends ArrayAdapter<String>
  {
    private final Context mContext;
    
    public MyAdapter(Context paramContext, String[] paramArrayOfString)
    {
      super(17367040, paramArrayOfString);
      this.mContext = paramContext;
    }
    
    public long getItemId(int paramInt)
    {
      return ((String)getItem(paramInt)).hashCode();
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      TextView localTextView = (TextView)paramView;
      paramView = localTextView;
      if (localTextView == null) {
        paramView = (TextView)LayoutInflater.from(this.mContext).inflate(17367043, paramViewGroup, false);
      }
      paramView.setText((CharSequence)getItem(paramInt));
      paramView.setTextColor(ListPickerActivity.itemColor);
      return paramView;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\ListPickerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */