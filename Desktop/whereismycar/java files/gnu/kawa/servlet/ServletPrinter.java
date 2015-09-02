package gnu.kawa.servlet;

import gnu.kawa.xml.HttpPrinter;
import java.io.IOException;

public class ServletPrinter
  extends HttpPrinter
{
  HttpRequestContext hctx;
  
  public ServletPrinter(HttpRequestContext paramHttpRequestContext, int paramInt)
    throws IOException
  {
    super(new HttpOutputStream(paramHttpRequestContext, paramInt));
    this.hctx = paramHttpRequestContext;
  }
  
  public void addHeader(String paramString1, String paramString2)
  {
    if (paramString1.equalsIgnoreCase("Content-type"))
    {
      this.sawContentType = paramString2;
      this.hctx.setContentType(paramString2);
    }
    int j;
    int i;
    char c;
    for (;;)
    {
      return;
      if (!paramString1.equalsIgnoreCase("Status")) {
        break label146;
      }
      k = paramString2.length();
      j = 0;
      i = 0;
      while (i < k)
      {
        if (i >= k)
        {
          this.hctx.statusCode = j;
          return;
        }
        c = paramString2.charAt(i);
        int m = Character.digit(c, 10);
        if (m < 0) {
          break label107;
        }
        j = j * 10 + m;
        i += 1;
      }
    }
    label107:
    int k = i;
    if (c == ' ') {
      k = i + 1;
    }
    this.hctx.statusCode = j;
    this.hctx.statusReasonPhrase = paramString2.substring(k);
    return;
    label146:
    this.hctx.setResponseHeader(paramString1, paramString2);
  }
  
  public void printHeaders() {}
  
  public boolean reset(boolean paramBoolean)
  {
    return ((HttpOutputStream)this.ostream).reset() & super.reset(paramBoolean);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\servlet\ServletPrinter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */