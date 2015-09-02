package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.mapping.InPort;
import gnu.mapping.WrappedException;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CompileFile
{
  public static final Compilation read(InPort paramInPort, SourceMessages paramSourceMessages)
    throws IOException, SyntaxException
  {
    return Language.getDefaultLanguage().parse(paramInPort, paramSourceMessages, 0);
  }
  
  public static final Compilation read(String paramString, SourceMessages paramSourceMessages)
    throws IOException, SyntaxException
  {
    try
    {
      InPort localInPort = InPort.openFile(paramString);
      paramSourceMessages = read(localInPort, paramSourceMessages);
      localInPort.close();
      return paramSourceMessages;
    }
    catch (FileNotFoundException paramSourceMessages)
    {
      throw new WrappedException("compile-file: file not found: " + paramString, paramSourceMessages);
    }
    catch (IOException paramSourceMessages)
    {
      throw new WrappedException("compile-file: read-error: " + paramString, paramSourceMessages);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\CompileFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */