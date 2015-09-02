package com.google.appinventor.components.runtime;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.provider.Contacts.ContactMethods;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import com.google.appinventor.components.runtime.util.HoneycombUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;

public class EmailAddressAdapter
  extends ResourceCursorAdapter
{
  private static final boolean DEBUG = false;
  private static final String[] POST_HONEYCOMB_PROJECTION = HoneycombUtil.getEmailAdapterProjection();
  public static final int PRE_HONEYCOMB_DATA_INDEX = 2;
  public static final int PRE_HONEYCOMB_NAME_INDEX = 1;
  private static final String[] PRE_HONEYCOMB_PROJECTION = { "_id", "name", "data" };
  private static String SORT_ORDER;
  private static final String TAG = "EmailAddressAdapter";
  private ContentResolver contentResolver;
  private Context context;
  
  public EmailAddressAdapter(Context paramContext)
  {
    super(paramContext, 17367050, null);
    this.contentResolver = paramContext.getContentResolver();
    this.context = paramContext;
    if (SdkLevel.getLevel() >= 12)
    {
      SORT_ORDER = HoneycombUtil.getTimesContacted() + " DESC, " + HoneycombUtil.getDisplayName();
      return;
    }
    SORT_ORDER = "times_contacted DESC, name";
  }
  
  private final String makeDisplayString(Cursor paramCursor)
  {
    int j = paramCursor.getColumnIndex(HoneycombUtil.getDisplayName());
    int k = paramCursor.getColumnIndex(HoneycombUtil.getEmailAddress());
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    String str;
    if (SdkLevel.getLevel() >= 12) {
      str = paramCursor.getString(j);
    }
    for (paramCursor = paramCursor.getString(k);; paramCursor = paramCursor.getString(2))
    {
      if (!TextUtils.isEmpty(str))
      {
        localStringBuilder.append(str);
        i = 1;
      }
      if (i != 0) {
        localStringBuilder.append(" <");
      }
      localStringBuilder.append(paramCursor);
      if (i != 0) {
        localStringBuilder.append(">");
      }
      return localStringBuilder.toString();
      str = paramCursor.getString(1);
    }
  }
  
  public final void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    ((TextView)paramView).setText(makeDisplayString(paramCursor));
  }
  
  public final String convertToString(Cursor paramCursor)
  {
    int i = paramCursor.getColumnIndex(HoneycombUtil.getDisplayName());
    int j = paramCursor.getColumnIndex(HoneycombUtil.getEmailAddress());
    String str;
    if (SdkLevel.getLevel() >= 12) {
      str = paramCursor.getString(i);
    }
    for (paramCursor = paramCursor.getString(j);; paramCursor = paramCursor.getString(2))
    {
      return new Rfc822Token(str, paramCursor, null).toString();
      str = paramCursor.getString(1);
    }
  }
  
  public Cursor runQueryOnBackgroundThread(CharSequence paramCharSequence)
  {
    Uri localUri = null;
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramCharSequence != null)
    {
      paramCharSequence = DatabaseUtils.sqlEscapeString(paramCharSequence.toString() + '%');
      if (SdkLevel.getLevel() < 12) {
        break label153;
      }
      localUri = HoneycombUtil.getDataContentUri();
      localStringBuilder.append("(" + HoneycombUtil.getDataMimeType() + "='" + HoneycombUtil.getEmailType() + "')");
      localStringBuilder.append(" AND ");
      localStringBuilder.append("(display_name LIKE ");
      localStringBuilder.append(paramCharSequence);
      localStringBuilder.append(")");
    }
    for (;;)
    {
      paramCharSequence = localStringBuilder.toString();
      if (SdkLevel.getLevel() < 12) {
        break;
      }
      return this.contentResolver.query(localUri, POST_HONEYCOMB_PROJECTION, paramCharSequence, null, SORT_ORDER);
      label153:
      localUri = Contacts.ContactMethods.CONTENT_EMAIL_URI;
      localStringBuilder.append("(name LIKE ");
      localStringBuilder.append(paramCharSequence);
      localStringBuilder.append(") OR (display_name LIKE ");
      localStringBuilder.append(paramCharSequence);
      localStringBuilder.append(")");
    }
    return this.contentResolver.query(localUri, PRE_HONEYCOMB_PROJECTION, paramCharSequence, null, SORT_ORDER);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\EmailAddressAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */