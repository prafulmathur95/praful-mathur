package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts.People;
import android.provider.Contacts.Phones;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.HoneycombUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.util.ArrayList;
import java.util.List;

@DesignerComponent(category=ComponentCategory.SOCIAL, description="A button that, when clicked on, displays a list of the contacts' phone numbers to choose among. After the user has made a selection, the following properties will be set to information about the chosen contact: <ul>\n<li> <code>ContactName</code>: the contact's name </li>\n <li> <code>PhoneNumber</code>: the contact's phone number </li>\n <li> <code>EmailAddress</code>: the contact's email address </li> <li> <code>Picture</code>: the name of the file containing the contact's image, which can be used as a <code>Picture</code> property value for the <code>Image</code> or <code>ImageSprite</code> component.</li></ul>\n</p><p>Other properties affect the appearance of the button (<code>TextAlignment</code>, <code>BackgroundColor</code>, etc.) and whether it can be clicked on (<code>Enabled</code>).</p>\n<p>The PhoneNumberPicker component may not work on all Android devices. For example, on Android systems before system 3.0, the returned lists of phone numbers and email addresses will be empty.\n", version=4)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.READ_CONTACTS")
public class PhoneNumberPicker
  extends ContactPicker
{
  private static String[] DATA_PROJECTION;
  private static final int EMAIL_INDEX = 3;
  private static final String LOG_TAG = "PhoneNumberPicker";
  private static final int NAME_INDEX = 0;
  private static String[] NAME_PROJECTION;
  private static final int NUMBER_INDEX = 1;
  private static final int PERSON_INDEX = 2;
  private static final String[] PROJECTION = { "name", "number", "person", "primary_email" };
  
  public PhoneNumberPicker(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer, Contacts.Phones.CONTENT_URI);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public String PhoneNumber()
  {
    return ensureNotNull(this.phoneNumber);
  }
  
  public void postHoneycombGetContactEmailsAndPhones(Cursor paramCursor)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    if (paramCursor.moveToFirst())
    {
      int i = HoneycombUtil.getPhoneIndex(paramCursor);
      int j = HoneycombUtil.getEmailIndex(paramCursor);
      int k = HoneycombUtil.getMimeIndex(paramCursor);
      String str1 = HoneycombUtil.getPhoneType();
      String str2 = HoneycombUtil.getEmailType();
      if (!paramCursor.isAfterLast())
      {
        String str3 = guardCursorGetString(paramCursor, k);
        if (str3.contains(str1)) {
          localArrayList1.add(guardCursorGetString(paramCursor, i));
        }
        for (;;)
        {
          paramCursor.moveToNext();
          break;
          if (str3.contains(str2)) {
            localArrayList2.add(guardCursorGetString(paramCursor, j));
          } else {
            Log.i("ContactPicker", "Type mismatch: " + str3 + " not " + str1 + " or " + str2);
          }
        }
      }
      this.phoneNumberList = localArrayList1;
      this.emailAddressList = localArrayList2;
      if (!this.emailAddressList.isEmpty()) {
        this.emailAddress = ((String)this.emailAddressList.get(0));
      }
    }
    else
    {
      return;
    }
    this.emailAddress = "";
  }
  
  public String postHoneycombGetContactNameAndPicture(Cursor paramCursor)
  {
    String str = "";
    if (paramCursor.moveToFirst())
    {
      int i = HoneycombUtil.getContactIdIndex(paramCursor);
      int j = HoneycombUtil.getNameIndex(paramCursor);
      int k = HoneycombUtil.getThumbnailIndex(paramCursor);
      this.phoneNumber = guardCursorGetString(paramCursor, HoneycombUtil.getPhoneIndex(paramCursor));
      str = guardCursorGetString(paramCursor, i);
      this.contactName = guardCursorGetString(paramCursor, j);
      this.contactPictureUri = guardCursorGetString(paramCursor, k);
    }
    return str;
  }
  
  public void preHoneycombGetContactInfo(Cursor paramCursor)
  {
    if (paramCursor.moveToFirst())
    {
      this.contactName = guardCursorGetString(paramCursor, 0);
      this.phoneNumber = guardCursorGetString(paramCursor, 1);
      int i = paramCursor.getInt(2);
      this.contactPictureUri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, i).toString();
      this.emailAddress = getEmailAddress(guardCursorGetString(paramCursor, 3));
    }
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Uri localUri;
    Object localObject6;
    Cursor localCursor2;
    Object localObject4;
    Object localObject5;
    String str;
    if ((paramInt1 == this.requestCode) && (paramInt2 == -1))
    {
      Log.i("PhoneNumberPicker", "received intent is " + paramIntent);
      localUri = paramIntent.getData();
      if (SdkLevel.getLevel() < 12) {
        break label365;
      }
      paramIntent = "//com.android.contacts/data";
      if (checkContactUri(localUri, paramIntent))
      {
        localObject6 = null;
        localCursor2 = null;
        localObject4 = null;
        localObject5 = null;
        str = null;
        localCursor1 = localCursor2;
        localObject3 = localObject4;
        paramIntent = (Intent)localObject6;
        localObject2 = localObject5;
      }
    }
    for (;;)
    {
      try
      {
        if (SdkLevel.getLevel() < 12) {
          continue;
        }
        localCursor1 = localCursor2;
        localObject3 = localObject4;
        paramIntent = (Intent)localObject6;
        localObject2 = localObject5;
        NAME_PROJECTION = HoneycombUtil.getNameProjection();
        localCursor1 = localCursor2;
        localObject3 = localObject4;
        paramIntent = (Intent)localObject6;
        localObject2 = localObject5;
        localCursor2 = this.activityContext.getContentResolver().query(localUri, NAME_PROJECTION, null, null, null);
        localCursor1 = localCursor2;
        localObject3 = localObject4;
        paramIntent = localCursor2;
        localObject2 = localObject5;
        str = postHoneycombGetContactNameAndPicture(localCursor2);
        localCursor1 = localCursor2;
        localObject3 = localObject4;
        paramIntent = localCursor2;
        localObject2 = localObject5;
        DATA_PROJECTION = HoneycombUtil.getDataProjection();
        localCursor1 = localCursor2;
        localObject3 = localObject4;
        paramIntent = localCursor2;
        localObject2 = localObject5;
        localObject4 = HoneycombUtil.getDataCursor(str, this.activityContext, DATA_PROJECTION);
        localCursor1 = localCursor2;
        localObject3 = localObject4;
        paramIntent = localCursor2;
        localObject2 = localObject4;
        postHoneycombGetContactEmailsAndPhones((Cursor)localObject4);
        localCursor1 = localCursor2;
        localObject3 = localObject4;
        paramIntent = localCursor2;
        localObject2 = localObject4;
        Log.i("PhoneNumberPicker", "Contact name = " + this.contactName + ", phone number = " + this.phoneNumber + ", emailAddress = " + this.emailAddress + ", contactPhotoUri = " + this.contactPictureUri);
      }
      catch (Exception localException)
      {
        label365:
        paramIntent = localCursor1;
        localObject2 = localObject3;
        Log.e("PhoneNumberPicker", "Exception in resultReturned", localException);
        paramIntent = localCursor1;
        localObject2 = localObject3;
        puntContactSelection(1107);
        if (localCursor1 == null) {
          continue;
        }
        localCursor1.close();
        if (localObject3 == null) {
          continue;
        }
        ((Cursor)localObject3).close();
        continue;
      }
      finally
      {
        if (paramIntent == null) {
          continue;
        }
        paramIntent.close();
        if (localObject2 == null) {
          continue;
        }
        ((Cursor)localObject2).close();
      }
      AfterPicking();
      return;
      paramIntent = "//contacts/phones";
      break;
      localCursor1 = localCursor2;
      localObject3 = localObject4;
      paramIntent = (Intent)localObject6;
      localObject2 = localObject5;
      localCursor2 = this.activityContext.getContentResolver().query(localUri, PROJECTION, null, null, null);
      localCursor1 = localCursor2;
      localObject3 = localObject4;
      paramIntent = localCursor2;
      localObject2 = localObject5;
      preHoneycombGetContactInfo(localCursor2);
      localObject4 = str;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\PhoneNumberPicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */