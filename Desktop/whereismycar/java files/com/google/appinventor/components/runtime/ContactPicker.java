package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.ContentResolver;
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
import java.util.Arrays;
import java.util.List;

@DesignerComponent(category=ComponentCategory.SOCIAL, description="A button that, when clicked on, displays a list of the contacts to choose among. After the user has made a selection, the following properties will be set to information about the chosen contact: <ul>\n<li> <code>ContactName</code>: the contact's name </li>\n <li> <code>EmailAddress</code>: the contact's primary email address </li>\n <li> <code>EmailAddressList</code>: a list of the contact's email addresses </li>\n <li> <code>PhoneNumber</code>: the contact's primary phone number (on Later Android Verisons)</li>\n <li> <code>PhoneNumberList</code>: a list of the contact's phone numbers (on Later Android Versions)</li>\n <li> <code>Picture</code>: the name of the file containing the contact's image, which can be used as a <code>Picture</code> property value for the <code>Image</code> or <code>ImageSprite</code> component.</li></ul>\n</p><p>Other properties affect the appearance of the button (<code>TextAlignment</code>, <code>BackgroundColor</code>, etc.) and whether it can be clicked on (<code>Enabled</code>).\n</p><p>The ContactPicker component might not work on all phones. For example, on Android systems before system 3.0, it cannot pick phone numbers, and the list of email addresses will contain only one email.", version=5)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.READ_CONTACTS")
public class ContactPicker
  extends Picker
  implements ActivityResultListener
{
  private static String[] CONTACT_PROJECTION;
  private static String[] DATA_PROJECTION;
  private static final int EMAIL_INDEX = 1;
  private static final int NAME_INDEX = 0;
  private static final int PHONE_INDEX = 2;
  private static final String[] PROJECTION = { "name", "primary_email" };
  protected final Activity activityContext;
  protected String contactName;
  protected String contactPictureUri;
  protected String emailAddress;
  protected List emailAddressList;
  private final Uri intentUri;
  protected String phoneNumber;
  protected List phoneNumberList;
  
  public ContactPicker(ComponentContainer paramComponentContainer)
  {
    this(paramComponentContainer, Contacts.People.CONTENT_URI);
  }
  
  protected ContactPicker(ComponentContainer paramComponentContainer, Uri paramUri)
  {
    super(paramComponentContainer);
    this.activityContext = paramComponentContainer.$context();
    if ((SdkLevel.getLevel() >= 12) && (paramUri.equals(Contacts.People.CONTENT_URI)))
    {
      this.intentUri = HoneycombUtil.getContentUri();
      return;
    }
    if ((SdkLevel.getLevel() >= 12) && (paramUri.equals(Contacts.Phones.CONTENT_URI)))
    {
      this.intentUri = HoneycombUtil.getPhoneContentUri();
      return;
    }
    this.intentUri = paramUri;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public String ContactName()
  {
    return ensureNotNull(this.contactName);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public String EmailAddress()
  {
    return ensureNotNull(this.emailAddress);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public List EmailAddressList()
  {
    return ensureNotNull(this.emailAddressList);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public String PhoneNumber()
  {
    return ensureNotNull(this.phoneNumber);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public List PhoneNumberList()
  {
    return ensureNotNull(this.phoneNumberList);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR)
  public String Picture()
  {
    return ensureNotNull(this.contactPictureUri);
  }
  
  protected boolean checkContactUri(Uri paramUri, String paramString)
  {
    Log.i("ContactPicker", "contactUri is " + paramUri);
    if ((paramUri == null) || (!"content".equals(paramUri.getScheme())))
    {
      Log.i("ContactPicker", "checkContactUri failed: A");
      puntContactSelection(1107);
      return false;
    }
    if (!paramUri.getSchemeSpecificPart().startsWith(paramString))
    {
      Log.i("ContactPicker", "checkContactUri failed: C");
      Log.i("ContactPicker", paramUri.getPath());
      puntContactSelection(1107);
      return false;
    }
    return true;
  }
  
  protected String ensureNotNull(String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    return str;
  }
  
  protected List ensureNotNull(List paramList)
  {
    Object localObject = paramList;
    if (paramList == null) {
      localObject = new ArrayList();
    }
    return (List)localObject;
  }
  
  /* Error */
  protected String getEmailAddress(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 193	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   4: istore_3
    //   5: ldc -75
    //   7: astore_1
    //   8: new 135	java/lang/StringBuilder
    //   11: dup
    //   12: invokespecial 137	java/lang/StringBuilder:<init>	()V
    //   15: ldc -61
    //   17: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: iload_3
    //   21: invokevirtual 198	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   24: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   27: astore_2
    //   28: aload_0
    //   29: getfield 72	com/google/appinventor/components/runtime/ContactPicker:activityContext	Landroid/app/Activity;
    //   32: invokevirtual 204	android/app/Activity:getContentResolver	()Landroid/content/ContentResolver;
    //   35: getstatic 209	android/provider/Contacts$ContactMethods:CONTENT_EMAIL_URI	Landroid/net/Uri;
    //   38: iconst_1
    //   39: anewarray 45	java/lang/String
    //   42: dup
    //   43: iconst_0
    //   44: ldc -45
    //   46: aastore
    //   47: aload_2
    //   48: aconst_null
    //   49: aconst_null
    //   50: invokevirtual 217	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   53: astore_2
    //   54: aload_2
    //   55: invokeinterface 223 1 0
    //   60: ifeq +10 -> 70
    //   63: aload_0
    //   64: aload_2
    //   65: iconst_0
    //   66: invokevirtual 227	com/google/appinventor/components/runtime/ContactPicker:guardCursorGetString	(Landroid/database/Cursor;I)Ljava/lang/String;
    //   69: astore_1
    //   70: aload_2
    //   71: invokeinterface 230 1 0
    //   76: aload_0
    //   77: aload_1
    //   78: invokevirtual 109	com/google/appinventor/components/runtime/ContactPicker:ensureNotNull	(Ljava/lang/String;)Ljava/lang/String;
    //   81: areturn
    //   82: astore_1
    //   83: ldc -75
    //   85: areturn
    //   86: astore_1
    //   87: aload_2
    //   88: invokeinterface 230 1 0
    //   93: aload_1
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	ContactPicker
    //   0	95	1	paramString	String
    //   27	61	2	localObject	Object
    //   4	17	3	i	int
    // Exception table:
    //   from	to	target	type
    //   0	5	82	java/lang/NumberFormatException
    //   54	70	86	finally
  }
  
  protected Intent getIntent()
  {
    return new Intent("android.intent.action.PICK", this.intentUri);
  }
  
  protected String guardCursorGetString(Cursor paramCursor, int paramInt)
  {
    try
    {
      paramCursor = paramCursor.getString(paramInt);
      return ensureNotNull(paramCursor);
    }
    catch (Exception paramCursor)
    {
      for (;;)
      {
        paramCursor = "";
      }
    }
  }
  
  public void postHoneycombGetContactEmailAndPhone(Cursor paramCursor)
  {
    this.phoneNumber = "";
    this.emailAddress = "";
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
    }
    if (!localArrayList1.isEmpty()) {
      this.phoneNumber = ((String)localArrayList1.get(0));
    }
    if (!localArrayList2.isEmpty()) {
      this.emailAddress = ((String)localArrayList2.get(0));
    }
    this.phoneNumberList = localArrayList1;
    this.emailAddressList = localArrayList2;
  }
  
  public String postHoneycombGetContactNameAndPicture(Cursor paramCursor)
  {
    String str = "";
    if (paramCursor.moveToFirst())
    {
      int i = HoneycombUtil.getIdIndex(paramCursor);
      int j = HoneycombUtil.getNameIndex(paramCursor);
      int k = HoneycombUtil.getThumbnailIndex(paramCursor);
      int m = HoneycombUtil.getPhotoIndex(paramCursor);
      str = guardCursorGetString(paramCursor, i);
      this.contactName = guardCursorGetString(paramCursor, j);
      this.contactPictureUri = guardCursorGetString(paramCursor, k);
      Log.i("ContactPicker", "photo_uri=" + guardCursorGetString(paramCursor, m));
    }
    return str;
  }
  
  public void preHoneycombGetContactInfo(Cursor paramCursor, Uri paramUri)
  {
    if (paramCursor.moveToFirst())
    {
      this.contactName = guardCursorGetString(paramCursor, 0);
      this.emailAddress = getEmailAddress(guardCursorGetString(paramCursor, 1));
      this.contactPictureUri = paramUri.toString();
      if (!this.emailAddress.equals("")) {
        break label67;
      }
    }
    label67:
    for (paramCursor = new ArrayList();; paramCursor = Arrays.asList(new String[] { this.emailAddress }))
    {
      this.emailAddressList = paramCursor;
      return;
    }
  }
  
  protected void puntContactSelection(int paramInt)
  {
    this.contactName = "";
    this.emailAddress = "";
    this.contactPictureUri = "";
    this.container.$form().dispatchErrorOccurredEvent(this, "", paramInt, new Object[0]);
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Uri localUri;
    Object localObject6;
    Cursor localCursor;
    Object localObject4;
    Object localObject5;
    String str;
    Object localObject1;
    if ((paramInt1 == this.requestCode) && (paramInt2 == -1))
    {
      Log.i("ContactPicker", "received intent is " + paramIntent);
      localUri = paramIntent.getData();
      if (SdkLevel.getLevel() < 12) {
        break label367;
      }
      paramIntent = "//com.android.contacts/contact";
      if (checkContactUri(localUri, paramIntent))
      {
        localObject6 = null;
        localCursor = null;
        localObject4 = null;
        localObject5 = null;
        str = null;
        paramIntent = localCursor;
        localObject2 = localObject4;
        localObject1 = localObject6;
        localObject3 = localObject5;
      }
    }
    for (;;)
    {
      try
      {
        if (SdkLevel.getLevel() < 12) {
          continue;
        }
        paramIntent = localCursor;
        localObject2 = localObject4;
        localObject1 = localObject6;
        localObject3 = localObject5;
        CONTACT_PROJECTION = HoneycombUtil.getContactProjection();
        paramIntent = localCursor;
        localObject2 = localObject4;
        localObject1 = localObject6;
        localObject3 = localObject5;
        localCursor = this.activityContext.getContentResolver().query(localUri, CONTACT_PROJECTION, null, null, null);
        paramIntent = localCursor;
        localObject2 = localObject4;
        localObject1 = localCursor;
        localObject3 = localObject5;
        str = postHoneycombGetContactNameAndPicture(localCursor);
        paramIntent = localCursor;
        localObject2 = localObject4;
        localObject1 = localCursor;
        localObject3 = localObject5;
        DATA_PROJECTION = HoneycombUtil.getDataProjection();
        paramIntent = localCursor;
        localObject2 = localObject4;
        localObject1 = localCursor;
        localObject3 = localObject5;
        localObject4 = HoneycombUtil.getDataCursor(str, this.activityContext, DATA_PROJECTION);
        paramIntent = localCursor;
        localObject2 = localObject4;
        localObject1 = localCursor;
        localObject3 = localObject4;
        postHoneycombGetContactEmailAndPhone((Cursor)localObject4);
        paramIntent = localCursor;
        localObject2 = localObject4;
        localObject1 = localCursor;
        localObject3 = localObject4;
        Log.i("ContactPicker", "Contact name = " + this.contactName + ", email address = " + this.emailAddress + ", phone number = " + this.phoneNumber + ", contactPhotoUri = " + this.contactPictureUri);
      }
      catch (Exception localException)
      {
        label367:
        localIntent = paramIntent;
        localObject3 = localObject2;
        Log.i("ContactPicker", "checkContactUri failed: D");
        localIntent = paramIntent;
        localObject3 = localObject2;
        puntContactSelection(1107);
        if (paramIntent == null) {
          continue;
        }
        paramIntent.close();
        if (localObject2 == null) {
          continue;
        }
        ((Cursor)localObject2).close();
        continue;
      }
      finally
      {
        Intent localIntent;
        if (localIntent == null) {
          continue;
        }
        localIntent.close();
        if (localObject3 == null) {
          continue;
        }
        ((Cursor)localObject3).close();
      }
      AfterPicking();
      return;
      paramIntent = "//contacts/people";
      break;
      paramIntent = localCursor;
      localObject2 = localObject4;
      localObject1 = localObject6;
      localObject3 = localObject5;
      localCursor = this.activityContext.getContentResolver().query(localUri, PROJECTION, null, null, null);
      paramIntent = localCursor;
      localObject2 = localObject4;
      localObject1 = localCursor;
      localObject3 = localObject5;
      preHoneycombGetContactInfo(localCursor, localUri);
      localObject4 = str;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\ContactPicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */