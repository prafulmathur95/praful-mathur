package gnu.text;

import org.xml.sax.Locator;

public abstract interface SourceLocator
  extends Locator
{
  public abstract int getColumnNumber();
  
  public abstract String getFileName();
  
  public abstract int getLineNumber();
  
  public abstract String getPublicId();
  
  public abstract String getSystemId();
  
  public abstract boolean isStableSourceLocation();
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\SourceLocator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */