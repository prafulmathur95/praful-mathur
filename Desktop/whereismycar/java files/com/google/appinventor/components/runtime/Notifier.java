package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.SdkLevel;

@DesignerComponent(category=ComponentCategory.USERINTERFACE, description="The Notifier component displays alert dialogs, messages, and temporary alerts, and creates Android log entries through the following methods: <ul><li> ShowMessageDialog: displays a message which the user must dismiss by pressing a button.</li><li> ShowChooseDialog: displays a message two buttons to let the user choose one of two responses, for example, yes or no, after which the AfterChoosing event is raised.</li><li> ShowTextDialog: lets the user enter text in response to the message, after which the AfterTextInput event is raised. <li> ShowAlert: displays a temporary  alert that goes away by itself after a short time.</li><li> ShowProgressDialog: displays an alert with a loading spinner that cannot be dismissed by the user. It can only be dismissed by using the DismissProgressDialog block.</li><li> DismissProgressDialog: Dismisses the progress dialog displayed by ShowProgressDialog.</li><li> LogError: logs an error message to the Android log. </li><li> LogInfo: logs an info message to the Android log.</li><li> LogWarning: logs a warning message to the Android log.</li><li>The messages in the dialogs (but not the alert) can be formatted using the following HTML tags:&lt;b&gt;, &lt;big&gt;, &lt;blockquote&gt;, &lt;br&gt;, &lt;cite&gt;, &lt;dfn&gt;, &lt;div&gt;, &lt;em&gt;, &lt;small&gt;, &lt;strong&gt;, &lt;sub&gt;, &lt;sup&gt;, &lt;tt&gt;. &lt;u&gt;</li><li>You can also use the font tag to specify color, for example, &lt;font color=\"blue\"&gt;.  Some of the available color names are aqua, black, blue, fuchsia, green, grey, lime, maroon, navy, olive, purple, red, silver, teal, white, and yellow</li></ul>", iconName="images/notifier.png", nonVisible=true, version=4)
@SimpleObject
public final class Notifier
  extends AndroidNonvisibleComponent
  implements Component
{
  private static final String LOG_TAG = "Notifier";
  private final Activity activity;
  private int backgroundColor = -12303292;
  private final Handler handler;
  private int notifierLength = 1;
  private ProgressDialog progressDialog;
  private int textColor = -1;
  
  public Notifier(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.activity = paramComponentContainer.$context();
    this.handler = new Handler();
    this.progressDialog = null;
  }
  
  public static void oneButtonAlert(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    Log.i("Notifier", "One button alert " + paramString1);
    paramActivity = new AlertDialog.Builder(paramActivity).create();
    paramActivity.setTitle(paramString2);
    paramActivity.setCancelable(false);
    paramActivity.setMessage(stringToHTML(paramString1));
    paramActivity.setButton(-3, paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    paramActivity.show();
  }
  
  private static SpannableString stringToHTML(String paramString)
  {
    return new SpannableString(Html.fromHtml(paramString));
  }
  
  private void textInputDialog(final String paramString1, String paramString2, boolean paramBoolean)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this.activity).create();
    localAlertDialog.setTitle(paramString2);
    localAlertDialog.setMessage(stringToHTML(paramString1));
    paramString1 = new EditText(this.activity);
    localAlertDialog.setView(paramString1);
    localAlertDialog.setCancelable(false);
    localAlertDialog.setButton(-1, "OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Notifier.this.HideKeyboard(paramString1);
        Notifier.this.AfterTextInput(paramString1.getText().toString());
      }
    });
    if (paramBoolean) {
      localAlertDialog.setButton(-2, "Cancel", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          Notifier.this.HideKeyboard(paramString1);
          Notifier.this.AfterTextInput("Cancel");
        }
      });
    }
    localAlertDialog.show();
  }
  
  private void toastNow(String paramString)
  {
    if (SdkLevel.getLevel() >= 14) {}
    for (int i = 22;; i = 15)
    {
      Toast localToast = Toast.makeText(this.activity, paramString, this.notifierLength);
      localToast.setGravity(17, localToast.getXOffset() / 2, localToast.getYOffset() / 2);
      TextView localTextView = new TextView(this.activity);
      localTextView.setBackgroundColor(this.backgroundColor);
      localTextView.setTextColor(this.textColor);
      localTextView.setTextSize(i);
      localTextView.setTypeface(Typeface.create(Typeface.SANS_SERIF, 0));
      localTextView.setPadding(10, 10, 10, 10);
      localTextView.setText(paramString + " ");
      localToast.setView(localTextView);
      localToast.show();
      return;
    }
  }
  
  public static void twoButtonDialog(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, Runnable paramRunnable1, Runnable paramRunnable2, Runnable paramRunnable3)
  {
    Log.i("Notifier", "ShowChooseDialog: " + paramString1);
    paramActivity = new AlertDialog.Builder(paramActivity).create();
    paramActivity.setTitle(paramString2);
    paramActivity.setCancelable(false);
    paramActivity.setMessage(stringToHTML(paramString1));
    paramActivity.setButton(-1, paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this.val$positiveAction.run();
      }
    });
    paramActivity.setButton(-3, paramString4, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this.val$negativeAction.run();
      }
    });
    if (paramBoolean) {
      paramActivity.setButton(-2, "Cancel", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          this.val$cancelAction.run();
        }
      });
    }
    paramActivity.show();
  }
  
  @SimpleEvent
  public void AfterChoosing(String paramString)
  {
    EventDispatcher.dispatchEvent(this, "AfterChoosing", new Object[] { paramString });
  }
  
  @SimpleEvent
  public void AfterTextInput(String paramString)
  {
    EventDispatcher.dispatchEvent(this, "AfterTextInput", new Object[] { paramString });
  }
  
  @DesignerProperty(defaultValue="&HFF444444", editorType="color")
  @SimpleProperty(description="Specifies the background color for alerts (not dialogs).")
  public void BackgroundColor(int paramInt)
  {
    this.backgroundColor = paramInt;
  }
  
  @SimpleFunction(description="Dismiss a previously displayed ProgressDialog box")
  public void DismissProgressDialog()
  {
    if (this.progressDialog != null)
    {
      this.progressDialog.dismiss();
      this.progressDialog = null;
    }
  }
  
  public void HideKeyboard(View paramView)
  {
    if (paramView != null) {
      ((InputMethodManager)this.activity.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
    }
  }
  
  @SimpleFunction(description="Writes an error message to the Android system log. See the Google Android documentation for how to access the log.")
  public void LogError(String paramString)
  {
    Log.e("Notifier", paramString);
  }
  
  @SimpleFunction(description="Writes an information message to the Android log.")
  public void LogInfo(String paramString)
  {
    Log.i("Notifier", paramString);
  }
  
  @SimpleFunction(description="Writes a warning message to the Android log. See the Google Android documentation for how to access the log.")
  public void LogWarning(String paramString)
  {
    Log.w("Notifier", paramString);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="specifies the length of time that the alert is shown -- either \"short\" or \"long\".")
  public int NotifierLength()
  {
    return this.notifierLength;
  }
  
  @DesignerProperty(defaultValue="1", editorType="toast_length")
  @SimpleProperty(userVisible=false)
  public void NotifierLength(int paramInt)
  {
    this.notifierLength = paramInt;
  }
  
  @SimpleFunction
  public void ShowAlert(final String paramString)
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        Notifier.this.toastNow(paramString);
      }
    });
  }
  
  @SimpleFunction(description="Shows a dialog box with two buttons, from which the user can choose.  If cancelable is true there will be an additional CANCEL button. Pressing a button will raise the AfterChoosing event.  The \"choice\" parameter to AfterChoosing will be the text on the button that was pressed, or \"Cancel\" if the  CANCEL button was pressed.")
  public void ShowChooseDialog(String paramString1, String paramString2, final String paramString3, final String paramString4, boolean paramBoolean)
  {
    twoButtonDialog(this.activity, paramString1, paramString2, paramString3, paramString4, paramBoolean, new Runnable()new Runnable
    {
      public void run()
      {
        Notifier.this.AfterChoosing(paramString3);
      }
    }, new Runnable()new Runnable
    {
      public void run()
      {
        Notifier.this.AfterChoosing(paramString4);
      }
    }, new Runnable()
    {
      public void run()
      {
        Notifier.this.AfterChoosing("Cancel");
      }
    });
  }
  
  @SimpleFunction
  public void ShowMessageDialog(String paramString1, String paramString2, String paramString3)
  {
    oneButtonAlert(this.activity, paramString1, paramString2, paramString3);
  }
  
  @SimpleFunction(description="Shows a dialog box with an optional title and message (use empty strings if they are not wanted). This dialog box contains a spinning artifact to indicate that the program is working. It cannot be canceled by the user but must be dismissed by the App Inventor Program by using the DismissProgressDialog block.")
  public void ShowProgressDialog(String paramString1, String paramString2)
  {
    progressDialog(paramString1, paramString2);
  }
  
  @SimpleFunction(description="Shows a dialog box where the user can enter text, after which the AfterTextInput event will be raised.  If cancelable is true there will be an additional CANCEL button. Entering text will raise the AfterTextInput event.  The \"response\" parameter to AfterTextInput will be the text that was entered, or \"Cancel\" if the CANCEL button was pressed.")
  public void ShowTextDialog(String paramString1, String paramString2, boolean paramBoolean)
  {
    textInputDialog(paramString1, paramString2, paramBoolean);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Specifies the text color for alerts (not dialogs).")
  public int TextColor()
  {
    return this.textColor;
  }
  
  @DesignerProperty(defaultValue="&HFFFFFFFF", editorType="color")
  @SimpleProperty
  public void TextColor(int paramInt)
  {
    this.textColor = paramInt;
  }
  
  public void progressDialog(String paramString1, String paramString2)
  {
    if (this.progressDialog != null) {
      DismissProgressDialog();
    }
    this.progressDialog = ProgressDialog.show(this.activity, paramString2, paramString1);
    this.progressDialog.setCancelable(false);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\Notifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */