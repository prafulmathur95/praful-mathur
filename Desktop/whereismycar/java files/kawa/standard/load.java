package kawa.standard;

import gnu.mapping.Environment;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.FileNotFoundException;
import kawa.Shell;

public class load
  extends Procedure1
{
  public static final load load = new load("load", false);
  public static final load loadRelative = new load("load-relative", true);
  boolean relative;
  
  public load(String paramString, boolean paramBoolean)
  {
    super(paramString);
    this.relative = paramBoolean;
  }
  
  public final Object apply1(Object paramObject)
    throws Throwable
  {
    return apply2(paramObject, Environment.getCurrent());
  }
  
  public final Object apply2(Object paramObject1, Object paramObject2)
    throws Throwable
  {
    try
    {
      Environment localEnvironment = (Environment)paramObject2;
      Path localPath1 = Path.valueOf(paramObject1);
      paramObject2 = localPath1;
      if (this.relative)
      {
        Path localPath2 = (Path)Shell.currentLoadPath.get();
        paramObject2 = localPath1;
        if (localPath2 != null) {
          paramObject2 = localPath2.resolve(localPath1);
        }
      }
      Shell.runFile(((Path)paramObject2).openInputStream(), (Path)paramObject2, localEnvironment, true, 0);
      paramObject2 = Values.empty;
      return paramObject2;
    }
    catch (FileNotFoundException paramObject1)
    {
      throw new RuntimeException("cannot load " + ((FileNotFoundException)paramObject1).getMessage());
    }
    catch (SyntaxException paramObject2)
    {
      throw new RuntimeException("load: errors while compiling '" + paramObject1 + "':\n" + ((SyntaxException)paramObject2).getMessages().toString(20));
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\load.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */