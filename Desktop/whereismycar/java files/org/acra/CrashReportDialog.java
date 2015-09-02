package org.acra;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.IOException;
import org.acra.collector.CrashReportData;
import org.acra.util.ToastSender;

public final class CrashReportDialog
  extends Activity
{
  private static final String STATE_COMMENT = "comment";
  private static final String STATE_EMAIL = "email";
  String mReportFileName;
  private SharedPreferences prefs;
  private EditText userComment;
  private EditText userEmail;
  
  protected void cancelNotification()
  {
    ((NotificationManager)getSystemService("notification")).cancel(666);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mReportFileName = getIntent().getStringExtra("REPORT_FILE_NAME");
    Log.d(ACRA.LOG_TAG, "Opening CrashReportDialog for " + this.mReportFileName);
    if (this.mReportFileName == null) {
      finish();
    }
    requestWindowFeature(3);
    LinearLayout localLinearLayout1 = new LinearLayout(this);
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setPadding(10, 10, 10, 10);
    localLinearLayout1.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    localLinearLayout1.setFocusable(true);
    localLinearLayout1.setFocusableInTouchMode(true);
    final ScrollView localScrollView = new ScrollView(this);
    localLinearLayout1.addView(localScrollView, new LinearLayout.LayoutParams(-1, -1, 1.0F));
    LinearLayout localLinearLayout2 = new LinearLayout(this);
    localLinearLayout2.setOrientation(1);
    localScrollView.addView(localLinearLayout2);
    Object localObject = new TextView(this);
    ((TextView)localObject).setText(getText(ACRA.getConfig().resDialogText()));
    localLinearLayout2.addView((View)localObject);
    int i = ACRA.getConfig().resDialogCommentPrompt();
    if (i != 0)
    {
      localObject = new TextView(this);
      ((TextView)localObject).setText(getText(i));
      ((TextView)localObject).setPadding(((TextView)localObject).getPaddingLeft(), 10, ((TextView)localObject).getPaddingRight(), ((TextView)localObject).getPaddingBottom());
      localLinearLayout2.addView((View)localObject, new LinearLayout.LayoutParams(-1, -2));
      this.userComment = new EditText(this);
      this.userComment.setLines(2);
      if (paramBundle != null)
      {
        localObject = paramBundle.getString("comment");
        if (localObject != null) {
          this.userComment.setText((CharSequence)localObject);
        }
      }
      localLinearLayout2.addView(this.userComment);
    }
    i = ACRA.getConfig().resDialogEmailPrompt();
    if (i != 0)
    {
      localObject = new TextView(this);
      ((TextView)localObject).setText(getText(i));
      ((TextView)localObject).setPadding(((TextView)localObject).getPaddingLeft(), 10, ((TextView)localObject).getPaddingRight(), ((TextView)localObject).getPaddingBottom());
      localLinearLayout2.addView((View)localObject);
      this.userEmail = new EditText(this);
      this.userEmail.setSingleLine();
      this.userEmail.setInputType(33);
      this.prefs = getSharedPreferences(ACRA.getConfig().sharedPreferencesName(), ACRA.getConfig().sharedPreferencesMode());
      localObject = null;
      if (paramBundle != null) {
        localObject = paramBundle.getString("email");
      }
      if (localObject == null) {
        break label649;
      }
      this.userEmail.setText((CharSequence)localObject);
    }
    for (;;)
    {
      localLinearLayout2.addView(this.userEmail);
      paramBundle = new LinearLayout(this);
      paramBundle.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
      paramBundle.setPadding(paramBundle.getPaddingLeft(), 10, paramBundle.getPaddingRight(), paramBundle.getPaddingBottom());
      localObject = new Button(this);
      ((Button)localObject).setText(17039379);
      ((Button)localObject).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (CrashReportDialog.this.userComment != null) {
            paramAnonymousView = CrashReportDialog.this.userComment.getText().toString();
          }
          for (;;)
          {
            String str;
            Object localObject;
            if ((CrashReportDialog.this.prefs != null) && (CrashReportDialog.this.userEmail != null))
            {
              str = CrashReportDialog.this.userEmail.getText().toString();
              localObject = CrashReportDialog.this.prefs.edit();
              ((SharedPreferences.Editor)localObject).putString("acra.user.email", str);
              ((SharedPreferences.Editor)localObject).commit();
              localObject = new CrashReportPersister(CrashReportDialog.this.getApplicationContext());
            }
            try
            {
              Log.d(ACRA.LOG_TAG, "Add user comment to " + CrashReportDialog.this.mReportFileName);
              CrashReportData localCrashReportData = ((CrashReportPersister)localObject).load(CrashReportDialog.this.mReportFileName);
              localCrashReportData.put(ReportField.USER_COMMENT, paramAnonymousView);
              localCrashReportData.put(ReportField.USER_EMAIL, str);
              ((CrashReportPersister)localObject).store(localCrashReportData, CrashReportDialog.this.mReportFileName);
              Log.v(ACRA.LOG_TAG, "About to start SenderWorker from CrashReportDialog");
              ACRA.getErrorReporter().startSendingReports(false, true);
              int i = ACRA.getConfig().resDialogOkToast();
              if (i != 0) {
                ToastSender.sendToast(CrashReportDialog.this.getApplicationContext(), i, 1);
              }
              CrashReportDialog.this.finish();
              return;
              paramAnonymousView = "";
              continue;
              str = "";
            }
            catch (IOException paramAnonymousView)
            {
              for (;;)
              {
                Log.w(ACRA.LOG_TAG, "User comment not added: ", paramAnonymousView);
              }
            }
          }
        }
      });
      paramBundle.addView((View)localObject, new LinearLayout.LayoutParams(-1, -2, 1.0F));
      localObject = new Button(this);
      ((Button)localObject).setText(17039369);
      ((Button)localObject).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ACRA.getErrorReporter().deletePendingNonApprovedReports(false);
          CrashReportDialog.this.finish();
        }
      });
      paramBundle.addView((View)localObject, new LinearLayout.LayoutParams(-1, -2, 1.0F));
      localLinearLayout1.addView(paramBundle, new LinearLayout.LayoutParams(-1, -2));
      setContentView(localLinearLayout1);
      i = ACRA.getConfig().resDialogTitle();
      if (i != 0) {
        setTitle(i);
      }
      getWindow().setFeatureDrawableResource(3, ACRA.getConfig().resDialogIcon());
      cancelNotification();
      localScrollView.post(new Runnable()
      {
        public void run()
        {
          localScrollView.scrollTo(0, 0);
        }
      });
      return;
      label649:
      this.userEmail.setText(this.prefs.getString("acra.user.email", ""));
    }
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4) {
      ACRA.getErrorReporter().deletePendingNonApprovedReports(false);
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if ((this.userComment != null) && (this.userComment.getText() != null)) {
      paramBundle.putString("comment", this.userComment.getText().toString());
    }
    if ((this.userEmail != null) && (this.userEmail.getText() != null)) {
      paramBundle.putString("email", this.userEmail.getText().toString());
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\CrashReportDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */