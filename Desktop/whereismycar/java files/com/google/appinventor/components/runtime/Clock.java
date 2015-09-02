package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.Dates;
import com.google.appinventor.components.runtime.util.TimerInternal;
import java.util.Calendar;

@DesignerComponent(category=ComponentCategory.SENSORS, description="<p>Non-visible component that provides the instant in time using the internal clock on the phone. It can fire a timer at regularly set intervals and perform time calculations, manipulations, and conversions.</p> <p>Methods to convert an instant to text are also available. Acceptable patterns are empty string, MM/DD/YYYY HH:mm:ss a, or MMM d, yyyyHH:mm. The empty string will provide the default format, which is \"MMM d, yyyy HH:mm:ss a\" for FormatDateTime \"MMM d, yyyy\" for FormatDate. To see all possible format, please see <a href=\"https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html\" _target=\"_blank\">here</a>. </p> ", iconName="images/clock.png", nonVisible=true, version=2)
@SimpleObject
public final class Clock
  extends AndroidNonvisibleComponent
  implements Component, AlarmHandler, OnStopListener, OnResumeListener, OnDestroyListener, Deleteable
{
  private static final boolean DEFAULT_ENABLED = true;
  private static final int DEFAULT_INTERVAL = 1000;
  private boolean onScreen = false;
  private boolean timerAlwaysFires = true;
  private TimerInternal timerInternal;
  
  public Clock()
  {
    super(null);
  }
  
  public Clock(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.timerInternal = new TimerInternal(this, true, 1000);
    this.form.registerForOnResume(this);
    this.form.registerForOnStop(this);
    this.form.registerForOnDestroy(this);
    if ((this.form instanceof ReplForm)) {
      this.onScreen = true;
    }
  }
  
  @SimpleFunction(description="An instant in time some days after the argument")
  public static Calendar AddDays(Calendar paramCalendar, int paramInt)
  {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 5, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description="An instant in time some hours after the argument")
  public static Calendar AddHours(Calendar paramCalendar, int paramInt)
  {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 11, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description="An instant in time some minutes after the argument")
  public static Calendar AddMinutes(Calendar paramCalendar, int paramInt)
  {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 12, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description="An instant in time some months after the argument")
  public static Calendar AddMonths(Calendar paramCalendar, int paramInt)
  {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 2, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description="An instant in time some seconds after the argument")
  public static Calendar AddSeconds(Calendar paramCalendar, int paramInt)
  {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 13, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description="An instant in time some weeks after the argument")
  public static Calendar AddWeeks(Calendar paramCalendar, int paramInt)
  {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 3, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description="An instant in time some years after the argument")
  public static Calendar AddYears(Calendar paramCalendar, int paramInt)
  {
    paramCalendar = (Calendar)paramCalendar.clone();
    Dates.DateAdd(paramCalendar, 1, paramInt);
    return paramCalendar;
  }
  
  @SimpleFunction(description="The day of the month")
  public static int DayOfMonth(Calendar paramCalendar)
  {
    return Dates.Day(paramCalendar);
  }
  
  @SimpleFunction(description="Milliseconds elapsed between instants")
  public static long Duration(Calendar paramCalendar1, Calendar paramCalendar2)
  {
    return paramCalendar2.getTimeInMillis() - paramCalendar1.getTimeInMillis();
  }
  
  @SimpleFunction(description="Text representing the date of an instant in the specified pattern")
  public static String FormatDate(Calendar paramCalendar, String paramString)
  {
    try
    {
      paramCalendar = Dates.FormatDate(paramCalendar, paramString);
      return paramCalendar;
    }
    catch (IllegalArgumentException paramCalendar)
    {
      throw new YailRuntimeError("Illegal argument for pattern in Clock.FormatDate. Acceptable values are empty string, MM/dd/YYYY, or MMM d, yyyy. For all possible patterns, see https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html", "Sorry to be so picky.");
    }
  }
  
  @SimpleFunction(description="Text representing the date and time of an instant in the specifed pattern")
  public static String FormatDateTime(Calendar paramCalendar, String paramString)
  {
    try
    {
      paramCalendar = Dates.FormatDateTime(paramCalendar, paramString);
      return paramCalendar;
    }
    catch (IllegalArgumentException paramCalendar)
    {
      throw new YailRuntimeError("Illegal argument for pattern in Clock.FormatDateTime. Acceptable values are empty string, MM/DD/YYYY HH:mm:ss a, MMM d, yyyy HH:mm For all possible patterns, see https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html", "Sorry to be so picky.");
    }
  }
  
  @SimpleFunction(description="Text representing the time of an instant")
  public static String FormatTime(Calendar paramCalendar)
  {
    return Dates.FormatTime(paramCalendar);
  }
  
  @SimpleFunction(description="The instant in time measured as milliseconds since 1970.")
  public static long GetMillis(Calendar paramCalendar)
  {
    return paramCalendar.getTimeInMillis();
  }
  
  @SimpleFunction(description="The hour of the day")
  public static int Hour(Calendar paramCalendar)
  {
    return Dates.Hour(paramCalendar);
  }
  
  @SimpleFunction(description="An instant in time specified by MM/DD/YYYY hh:mm:ss or MM/DD/YYYY or hh:mm")
  public static Calendar MakeInstant(String paramString)
  {
    try
    {
      paramString = Dates.DateValue(paramString);
      return paramString;
    }
    catch (IllegalArgumentException paramString)
    {
      throw new YailRuntimeError("Argument to MakeInstant should have form MM/DD/YYYY hh:mm:ss, or MM/DD/YYYY or hh:mm", "Sorry to be so picky.");
    }
  }
  
  @SimpleFunction(description="An instant in time specified by the milliseconds since 1970.")
  public static Calendar MakeInstantFromMillis(long paramLong)
  {
    Calendar localCalendar = Dates.Now();
    localCalendar.setTimeInMillis(paramLong);
    return localCalendar;
  }
  
  @SimpleFunction(description="The minute of the hour")
  public static int Minute(Calendar paramCalendar)
  {
    return Dates.Minute(paramCalendar);
  }
  
  @SimpleFunction(description="The month of the year represented as a number from 1 to 12)")
  public static int Month(Calendar paramCalendar)
  {
    return Dates.Month(paramCalendar) + 1;
  }
  
  @SimpleFunction(description="The name of the month")
  public static String MonthName(Calendar paramCalendar)
  {
    return Dates.MonthName(paramCalendar);
  }
  
  @SimpleFunction(description="The current instant in time read from phone's clock")
  public static Calendar Now()
  {
    return Dates.Now();
  }
  
  @SimpleFunction(description="The second of the minute")
  public static int Second(Calendar paramCalendar)
  {
    return Dates.Second(paramCalendar);
  }
  
  @SimpleFunction(description="The phone's internal time")
  public static long SystemTime()
  {
    return Dates.Timer();
  }
  
  @SimpleFunction(description="The day of the week represented as a number from 1 (Sunday) to 7 (Saturday)")
  public static int Weekday(Calendar paramCalendar)
  {
    return Dates.Weekday(paramCalendar);
  }
  
  @SimpleFunction(description="The name of the day of the week")
  public static String WeekdayName(Calendar paramCalendar)
  {
    return Dates.WeekdayName(paramCalendar);
  }
  
  @SimpleFunction(description="The year")
  public static int Year(Calendar paramCalendar)
  {
    return Dates.Year(paramCalendar);
  }
  
  @SimpleEvent(description="Timer has gone off.")
  public void Timer()
  {
    if ((this.timerAlwaysFires) || (this.onScreen)) {
      EventDispatcher.dispatchEvent(this, "Timer", new Object[0]);
    }
  }
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty
  public void TimerAlwaysFires(boolean paramBoolean)
  {
    this.timerAlwaysFires = paramBoolean;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Will fire even when application is not showing on the screen if true")
  public boolean TimerAlwaysFires()
  {
    return this.timerAlwaysFires;
  }
  
  @DesignerProperty(defaultValue="True", editorType="boolean")
  @SimpleProperty
  public void TimerEnabled(boolean paramBoolean)
  {
    this.timerInternal.Enabled(paramBoolean);
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Fires timer if true")
  public boolean TimerEnabled()
  {
    return this.timerInternal.Enabled();
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="Interval between timer events in ms")
  public int TimerInterval()
  {
    return this.timerInternal.Interval();
  }
  
  @DesignerProperty(defaultValue="1000", editorType="non_negative_integer")
  @SimpleProperty
  public void TimerInterval(int paramInt)
  {
    this.timerInternal.Interval(paramInt);
  }
  
  public void alarm()
  {
    Timer();
  }
  
  public void onDelete()
  {
    this.timerInternal.Enabled(false);
  }
  
  public void onDestroy()
  {
    this.timerInternal.Enabled(false);
  }
  
  public void onResume()
  {
    this.onScreen = true;
  }
  
  public void onStop()
  {
    this.onScreen = false;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\Clock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */