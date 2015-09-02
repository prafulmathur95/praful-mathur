package gnu.mapping;

import gnu.text.SourceLocator;

public class UnboundLocationException
  extends RuntimeException
{
  int column;
  String filename;
  int line;
  Location location;
  public Object symbol;
  
  public UnboundLocationException() {}
  
  public UnboundLocationException(Location paramLocation)
  {
    this.location = paramLocation;
  }
  
  public UnboundLocationException(Object paramObject)
  {
    this.symbol = paramObject;
  }
  
  public UnboundLocationException(Object paramObject, SourceLocator paramSourceLocator)
  {
    this.symbol = paramObject;
    if (paramSourceLocator != null)
    {
      this.filename = paramSourceLocator.getFileName();
      this.line = paramSourceLocator.getLineNumber();
      this.column = paramSourceLocator.getColumnNumber();
    }
  }
  
  public UnboundLocationException(Object paramObject, String paramString)
  {
    super(paramString);
    this.symbol = paramObject;
  }
  
  public UnboundLocationException(Object paramObject, String paramString, int paramInt1, int paramInt2)
  {
    this.symbol = paramObject;
    this.filename = paramString;
    this.line = paramInt1;
    this.column = paramInt2;
  }
  
  public String getMessage()
  {
    Object localObject = super.getMessage();
    if (localObject != null) {
      return (String)localObject;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    if ((this.filename != null) || (this.line > 0))
    {
      if (this.filename != null) {
        localStringBuffer.append(this.filename);
      }
      if (this.line >= 0)
      {
        localStringBuffer.append(':');
        localStringBuffer.append(this.line);
        if (this.column > 0)
        {
          localStringBuffer.append(':');
          localStringBuffer.append(this.column);
        }
      }
      localStringBuffer.append(": ");
    }
    if (this.location == null)
    {
      localObject = null;
      if (localObject == null) {
        break label176;
      }
      localStringBuffer.append("unbound location ");
      localStringBuffer.append(localObject);
      localObject = this.location.getKeyProperty();
      if (localObject != null)
      {
        localStringBuffer.append(" (property ");
        localStringBuffer.append(localObject);
        localStringBuffer.append(')');
      }
    }
    for (;;)
    {
      return localStringBuffer.toString();
      localObject = this.location.getKeySymbol();
      break;
      label176:
      if (this.symbol != null)
      {
        localStringBuffer.append("unbound location ");
        localStringBuffer.append(this.symbol);
      }
      else
      {
        localStringBuffer.append("unbound location");
      }
    }
  }
  
  public void setLine(String paramString, int paramInt1, int paramInt2)
  {
    this.filename = paramString;
    this.line = paramInt1;
    this.column = paramInt2;
  }
  
  public String toString()
  {
    return getMessage();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\UnboundLocationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */