package com.google.appinventor.components.runtime;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import java.util.Calendar;

@DesignerComponent(category=ComponentCategory.USERINTERFACE, description="<p>A button that, when clicked on, launches  a popup dialog to allow the user to select a time.</p>", version=2)
@SimpleObject
public class TimePicker
  extends ButtonBase
{
  private Handler androidUIHandler;
  private boolean customTime = false;
  private Form form;
  private int hour = 0;
  private int minute = 0;
  private TimePickerDialog time;
  private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener()
  {
    public void onTimeSet(android.widget.TimePicker paramAnonymousTimePicker, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      if (paramAnonymousTimePicker.isShown())
      {
        TimePicker.access$002(TimePicker.this, paramAnonymousInt1);
        TimePicker.access$102(TimePicker.this, paramAnonymousInt2);
        TimePicker.this.androidUIHandler.post(new Runnable()
        {
          public void run()
          {
            TimePicker.this.AfterTimeSet();
          }
        });
      }
    }
  };
  
  public TimePicker(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer);
    this.form = paramComponentContainer.$form();
    paramComponentContainer = Calendar.getInstance();
    this.hour = paramComponentContainer.get(11);
    this.minute = paramComponentContainer.get(12);
    this.time = new TimePickerDialog(this.container.$context(), this.timePickerListener, this.hour, this.minute, false);
    this.androidUIHandler = new Handler();
  }
  
  @SimpleEvent(description="This event is run when a user has set the time in the popup dialog.")
  public void AfterTimeSet()
  {
    EventDispatcher.dispatchEvent(this, "AfterTimeSet", new Object[0]);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="The hour of the last time set using the time picker. The hour is in a 24 hour format. If the last time set was 11:53 pm, this property will return 23.")
  public int Hour()
  {
    return this.hour;
  }
  
  @SimpleFunction(description="Launches the TimePicker popup.")
  public void LaunchPicker()
  {
    click();
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="The minute of the last time set using the time picker")
  public int Minute()
  {
    return this.minute;
  }
  
  @SimpleFunction(description="Set the time to be shown in the Time Picker popup. Current time is shown by default.")
  public void SetTimeToDisplay(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) || (paramInt1 > 23))
    {
      this.form.dispatchErrorOccurredEvent(this, "SetTimeToDisplay", 2301, new Object[0]);
      return;
    }
    if ((paramInt2 < 0) || (paramInt2 > 59))
    {
      this.form.dispatchErrorOccurredEvent(this, "SetTimeToDisplay", 2302, new Object[0]);
      return;
    }
    this.time.updateTime(paramInt1, paramInt2);
    this.customTime = true;
  }
  
  public void click()
  {
    if (!this.customTime)
    {
      Calendar localCalendar = Calendar.getInstance();
      int i = localCalendar.get(11);
      int j = localCalendar.get(12);
      this.time.updateTime(i, j);
    }
    for (;;)
    {
      this.time.show();
      return;
      this.customTime = false;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\TimePicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */