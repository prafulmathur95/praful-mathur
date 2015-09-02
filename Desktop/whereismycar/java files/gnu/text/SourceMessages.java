package gnu.text;

import java.io.PrintStream;
import java.io.PrintWriter;

public class SourceMessages
  implements SourceLocator
{
  public static boolean debugStackTraceOnError = false;
  public static boolean debugStackTraceOnWarning = false;
  int current_column;
  String current_filename;
  int current_line;
  private int errorCount = 0;
  SourceError firstError;
  SourceError lastError;
  SourceError lastPrevFilename = null;
  SourceLocator locator;
  public boolean sortMessages;
  
  public boolean checkErrors(PrintStream paramPrintStream, int paramInt)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.firstError != null)
    {
      printAll(paramPrintStream, paramInt);
      this.lastError = null;
      this.firstError = null;
      paramInt = this.errorCount;
      this.errorCount = 0;
      bool1 = bool2;
      if (paramInt > 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean checkErrors(PrintWriter paramPrintWriter, int paramInt)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.firstError != null)
    {
      printAll(paramPrintWriter, paramInt);
      this.lastError = null;
      this.firstError = null;
      paramInt = this.errorCount;
      this.errorCount = 0;
      bool1 = bool2;
      if (paramInt > 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public void clear()
  {
    this.lastError = null;
    this.firstError = null;
    this.errorCount = 0;
  }
  
  public void clearErrors()
  {
    this.errorCount = 0;
  }
  
  public void error(char paramChar, SourceLocator paramSourceLocator, String paramString)
  {
    error(new SourceError(paramChar, paramSourceLocator, paramString));
  }
  
  public void error(char paramChar, SourceLocator paramSourceLocator, String paramString1, String paramString2)
  {
    paramSourceLocator = new SourceError(paramChar, paramSourceLocator, paramString1);
    paramSourceLocator.code = paramString2;
    error(paramSourceLocator);
  }
  
  public void error(char paramChar, String paramString)
  {
    error(new SourceError(paramChar, this.current_filename, this.current_line, this.current_column, paramString));
  }
  
  public void error(char paramChar, String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    error(new SourceError(paramChar, paramString1, paramInt1, paramInt2, paramString2));
  }
  
  public void error(char paramChar, String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3)
  {
    paramString1 = new SourceError(paramChar, paramString1, paramInt1, paramInt2, paramString2);
    paramString1.code = paramString3;
    error(paramString1);
  }
  
  public void error(char paramChar, String paramString1, String paramString2)
  {
    paramString1 = new SourceError(paramChar, this.current_filename, this.current_line, this.current_column, paramString1);
    paramString1.code = paramString2;
    error(paramString1);
  }
  
  public void error(char paramChar, String paramString, Throwable paramThrowable)
  {
    paramString = new SourceError(paramChar, this.current_filename, this.current_line, this.current_column, paramString);
    paramString.fakeException = paramThrowable;
    error(paramString);
  }
  
  public void error(SourceError paramSourceError)
  {
    Object localObject1;
    Object localObject2;
    if (paramSourceError.severity == 'f')
    {
      this.errorCount = 1000;
      if (((debugStackTraceOnError) && ((paramSourceError.severity == 'e') || (paramSourceError.severity == 'f'))) || ((debugStackTraceOnWarning) && (paramSourceError.severity == 'w'))) {
        paramSourceError.fakeException = new Throwable();
      }
      if ((this.lastError != null) && (this.lastError.filename != null) && (!this.lastError.filename.equals(paramSourceError.filename))) {
        this.lastPrevFilename = this.lastError;
      }
      localObject1 = this.lastPrevFilename;
      if ((this.sortMessages) && (paramSourceError.severity != 'f')) {
        break label192;
      }
      localObject2 = this.lastError;
      if (localObject2 != null) {
        break label286;
      }
      paramSourceError.next = this.firstError;
      this.firstError = paramSourceError;
    }
    for (;;)
    {
      if (localObject2 == this.lastError) {
        this.lastError = paramSourceError;
      }
      return;
      if (paramSourceError.severity == 'w') {
        break;
      }
      this.errorCount += 1;
      break;
      label190:
      localObject1 = localSourceError;
      label192:
      if (localObject1 == null) {}
      for (SourceError localSourceError = this.firstError;; localSourceError = ((SourceError)localObject1).next)
      {
        localObject2 = localObject1;
        if (localSourceError == null) {
          break;
        }
        if ((paramSourceError.line == 0) || (localSourceError.line == 0)) {
          break label190;
        }
        localObject2 = localObject1;
        if (paramSourceError.line < localSourceError.line) {
          break;
        }
        if ((paramSourceError.line != localSourceError.line) || (paramSourceError.column == 0) || (localSourceError.column == 0) || (paramSourceError.column >= localSourceError.column)) {
          break label190;
        }
        localObject2 = localObject1;
        break;
      }
      label286:
      paramSourceError.next = ((SourceError)localObject2).next;
      ((SourceError)localObject2).next = paramSourceError;
    }
  }
  
  public final int getColumnNumber()
  {
    if (this.locator == null) {
      return this.current_column;
    }
    return this.locator.getColumnNumber();
  }
  
  public int getErrorCount()
  {
    return this.errorCount;
  }
  
  public SourceError getErrors()
  {
    return this.firstError;
  }
  
  public final String getFileName()
  {
    return this.current_filename;
  }
  
  public final int getLineNumber()
  {
    if (this.locator == null) {
      return this.current_line;
    }
    return this.locator.getLineNumber();
  }
  
  public String getPublicId()
  {
    if (this.locator == null) {
      return null;
    }
    return this.locator.getPublicId();
  }
  
  public String getSystemId()
  {
    if (this.locator == null) {
      return this.current_filename;
    }
    return this.locator.getSystemId();
  }
  
  public boolean isStableSourceLocation()
  {
    return false;
  }
  
  public void printAll(PrintStream paramPrintStream, int paramInt)
  {
    for (SourceError localSourceError = this.firstError; localSourceError != null; localSourceError = localSourceError.next)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      localSourceError.println(paramPrintStream);
    }
  }
  
  public void printAll(PrintWriter paramPrintWriter, int paramInt)
  {
    for (SourceError localSourceError = this.firstError; localSourceError != null; localSourceError = localSourceError.next)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      localSourceError.println(paramPrintWriter);
    }
  }
  
  public boolean seenErrors()
  {
    return this.errorCount > 0;
  }
  
  public boolean seenErrorsOrWarnings()
  {
    return this.firstError != null;
  }
  
  public void setColumn(int paramInt)
  {
    this.current_column = paramInt;
  }
  
  public void setFile(String paramString)
  {
    this.current_filename = paramString;
  }
  
  public void setLine(int paramInt)
  {
    this.current_line = paramInt;
  }
  
  public void setLine(String paramString, int paramInt1, int paramInt2)
  {
    this.current_filename = paramString;
    this.current_line = paramInt1;
    this.current_column = paramInt2;
  }
  
  public final void setLocation(SourceLocator paramSourceLocator)
  {
    this.locator = null;
    this.current_line = paramSourceLocator.getLineNumber();
    this.current_column = paramSourceLocator.getColumnNumber();
    this.current_filename = paramSourceLocator.getFileName();
  }
  
  public final void setSourceLocator(SourceLocator paramSourceLocator)
  {
    SourceLocator localSourceLocator = paramSourceLocator;
    if (paramSourceLocator == this) {
      localSourceLocator = null;
    }
    this.locator = localSourceLocator;
  }
  
  public final SourceLocator swapSourceLocator(SourceLocator paramSourceLocator)
  {
    SourceLocator localSourceLocator = this.locator;
    this.locator = paramSourceLocator;
    return localSourceLocator;
  }
  
  public String toString(int paramInt)
  {
    if (this.firstError == null) {
      return null;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    for (SourceError localSourceError = this.firstError; localSourceError != null; localSourceError = localSourceError.next)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      localStringBuffer.append(localSourceError);
      localStringBuffer.append('\n');
    }
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\SourceMessages.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */