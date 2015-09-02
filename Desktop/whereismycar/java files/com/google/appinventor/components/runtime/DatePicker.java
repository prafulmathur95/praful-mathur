package com.google.appinventor.components.runtime;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

@DesignerComponent(category=ComponentCategory.USERINTERFACE, description="<p>A button that, when clicked on, launches a popup dialog to allow the user to select a date.</p>", version=2)
@SimpleObject
public class DatePicker
  extends ButtonBase
{
  private Handler androidUIHandler;
  private boolean customDate = false;
  private DatePickerDialog date;
  private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener()
  {
    public void onDateSet(android.widget.DatePicker paramAnonymousDatePicker, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      if (paramAnonymousDatePicker.isShown())
      {
        DatePicker.access$002(DatePicker.this, paramAnonymousInt1);
        DatePicker.access$102(DatePicker.this, paramAnonymousInt2);
        DatePicker.access$202(DatePicker.this, DatePicker.this.javaMonth + 1);
        DatePicker.access$302(DatePicker.this, paramAnonymousInt3);
        DatePicker.this.date.updateDate(DatePicker.this.year, DatePicker.this.javaMonth, DatePicker.this.day);
        DatePicker.this.androidUIHandler.post(new Runnable()
        {
          public void run()
          {
            DatePicker.this.AfterDateSet();
          }
        });
      }
    }
  };
  private int day;
  private Form form;
  private int javaMonth;
  private String[] localizedMonths = new DateFormatSymbols().getMonths();
  private int month;
  private int year;
  
  public DatePicker(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer);
    this.form = paramComponentContainer.$form();
    paramComponentContainer = Calendar.getInstance();
    this.year = paramComponentContainer.get(1);
    this.javaMonth = paramComponentContainer.get(2);
    this.month = (this.javaMonth + 1);
    this.day = paramComponentContainer.get(5);
    this.date = new DatePickerDialog(this.container.$context(), this.datePickerListener, this.year, this.javaMonth, this.day);
    this.androidUIHandler = new Handler();
  }
  
  @SimpleEvent(description="Event that runs after the user chooses a Date in the dialog")
  public void AfterDateSet()
  {
    EventDispatcher.dispatchEvent(this, "AfterDateSet", new Object[0]);
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="the Day of the month that was last picked using the DatePicker.")
  public int Day()
  {
    return this.day;
  }
  
  @SimpleFunction(description="Launches the DatePicker popup.")
  public void LaunchPicker()
  {
    click();
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="the number of the Month that was last picked using the DatePicker. Note that months start in 1 = January, 12 = December.")
  public int Month()
  {
    return this.month;
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="Returns the name of the Month that was last picked using the DatePicker, in textual format.")
  public String MonthInText()
  {
    return this.localizedMonths[this.javaMonth];
  }
  
  @SimpleFunction(description="Allows the user to set the date to be displayed when the date picker opens.\nValid values for the month field are 1-12 and 1-31 for the day field.\n")
  public void SetDateToDisplay(int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt2 -= 1;
    try
    {
      GregorianCalendar localGregorianCalendar = new GregorianCalendar(paramInt1, paramInt2, paramInt3);
      localGregorianCalendar.setLenient(false);
      localGregorianCalendar.getTime();
      this.date.updateDate(paramInt1, paramInt2, paramInt3);
      this.customDate = true;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        this.form.dispatchErrorOccurredEvent(this, "SetDateToDisplay", 2401, new Object[0]);
      }
    }
  }
  
  @SimpleProperty(category=PropertyCategory.APPEARANCE, description="the Year that was last picked using the DatePicker")
  public int Year()
  {
    return this.year;
  }
  
  public void click()
  {
    if (!this.customDate)
    {
      Calendar localCalendar = Calendar.getInstance();
      int i = localCalendar.get(1);
      int j = localCalendar.get(2);
      int k = localCalendar.get(5);
      this.date.updateDate(i, j, k);
    }
    for (;;)
    {
      this.date.show();
      return;
      this.customDate = false;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\DatePicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */