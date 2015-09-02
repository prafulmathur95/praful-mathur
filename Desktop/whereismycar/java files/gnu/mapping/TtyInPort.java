package gnu.mapping;

import gnu.text.Path;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class TtyInPort
  extends InPort
{
  protected boolean promptEmitted;
  protected Procedure prompter;
  protected OutPort tie;
  
  public TtyInPort(InputStream paramInputStream, Path paramPath, OutPort paramOutPort)
  {
    super(paramInputStream, paramPath);
    setConvertCR(true);
    this.tie = paramOutPort;
  }
  
  public TtyInPort(Reader paramReader, Path paramPath, OutPort paramOutPort)
  {
    super(paramReader, paramPath);
    setConvertCR(true);
    this.tie = paramOutPort;
  }
  
  public void emitPrompt(String paramString)
    throws IOException
  {
    this.tie.print(paramString);
    this.tie.flush();
    this.tie.clearBuffer();
  }
  
  public int fill(int paramInt)
    throws IOException
  {
    paramInt = this.in.read(this.buffer, this.pos, paramInt);
    if ((this.tie != null) && (paramInt > 0)) {
      this.tie.echo(this.buffer, this.pos, paramInt);
    }
    return paramInt;
  }
  
  public Procedure getPrompter()
  {
    return this.prompter;
  }
  
  public void lineStart(boolean paramBoolean)
    throws IOException
  {
    if (!paramBoolean)
    {
      if (this.tie != null) {
        this.tie.freshLine();
      }
      if (this.prompter == null) {}
    }
    try
    {
      Object localObject = this.prompter.apply1(this);
      if (localObject != null)
      {
        localObject = localObject.toString();
        if ((localObject != null) && (((String)localObject).length() > 0))
        {
          emitPrompt((String)localObject);
          this.promptEmitted = true;
        }
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw new IOException("Error when evaluating prompt:" + localThrowable);
    }
  }
  
  public int read()
    throws IOException
  {
    if (this.tie != null) {
      this.tie.flush();
    }
    int i = super.read();
    boolean bool2;
    if (i < 0)
    {
      bool2 = this.promptEmitted;
      if (this.tie == null) {
        break label57;
      }
    }
    label57:
    for (boolean bool1 = true;; bool1 = false)
    {
      if ((bool1 & bool2)) {
        this.tie.println();
      }
      this.promptEmitted = false;
      return i;
    }
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.tie != null) {
      this.tie.flush();
    }
    paramInt2 = super.read(paramArrayOfChar, paramInt1, paramInt2);
    int i;
    if (paramInt2 < 0)
    {
      i = this.promptEmitted;
      if (this.tie == null) {
        break label62;
      }
    }
    label62:
    for (paramInt1 = 1;; paramInt1 = 0)
    {
      if ((paramInt1 & i) != 0) {
        this.tie.println();
      }
      this.promptEmitted = false;
      return paramInt2;
    }
  }
  
  public void setPrompter(Procedure paramProcedure)
  {
    this.prompter = paramProcedure;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\TtyInPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */