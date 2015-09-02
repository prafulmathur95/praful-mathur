package gnu.bytecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class ListCodeSize
{
  public static final void main(String[] paramArrayOfString)
  {
    if (paramArrayOfString.length == 0) {
      usage();
    }
    String str = paramArrayOfString[0];
    for (;;)
    {
      try
      {
        Object localObject = new FileInputStream(str);
        ClassType localClassType = new ClassType();
        new ClassFileInput(localClassType, (InputStream)localObject);
        if (paramArrayOfString.length == 1)
        {
          paramArrayOfString = localClassType.getMethods();
          if (paramArrayOfString != null)
          {
            print(paramArrayOfString);
            paramArrayOfString = paramArrayOfString.getNext();
            continue;
            if (i < paramArrayOfString.length)
            {
              localObject = localClassType.getMethods();
              if (localObject != null)
              {
                StringBuffer localStringBuffer = new StringBuffer();
                localStringBuffer.append(((Method)localObject).getName());
                ((Method)localObject).listParameters(localStringBuffer);
                localStringBuffer.append(((Method)localObject).getReturnType().getName());
                if (localStringBuffer.toString().startsWith(paramArrayOfString[i])) {
                  print((Method)localObject);
                }
                localObject = ((Method)localObject).getNext();
                continue;
              }
              i += 1;
              continue;
            }
          }
        }
        int i = 1;
      }
      catch (FileNotFoundException paramArrayOfString)
      {
        System.err.println("File " + str + " not found");
        System.exit(-1);
        return;
      }
      catch (IOException paramArrayOfString)
      {
        System.err.println(paramArrayOfString);
        paramArrayOfString.printStackTrace();
        System.exit(-1);
        return;
      }
    }
  }
  
  static void print(Method paramMethod)
  {
    System.out.print(paramMethod);
    paramMethod = paramMethod.getCode();
    if (paramMethod == null) {
      System.out.print(": no code");
    }
    for (;;)
    {
      System.out.println();
      return;
      System.out.print(": ");
      System.out.print(paramMethod.getPC());
      System.out.print(" bytes");
    }
  }
  
  public static void usage()
  {
    System.err.println("Usage: class methodname ...");
    System.exit(-1);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\ListCodeSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */