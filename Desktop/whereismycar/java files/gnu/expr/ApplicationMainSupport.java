package gnu.expr;

import gnu.lists.FString;
import gnu.lists.FVector;
import gnu.mapping.Environment;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class ApplicationMainSupport
{
  public static String[] commandLineArgArray;
  public static FVector commandLineArguments;
  public static boolean processCommandLinePropertyAssignments;
  static String[][] propertyFields;
  
  static
  {
    String[] arrayOfString1 = { "out:doctype-system", "gnu.xml.XMLPrinter", "doctypeSystem" };
    String[] arrayOfString2 = { "out:doctype-public", "gnu.xml.XMLPrinter", "doctypePublic" };
    String[] arrayOfString3 = { "out:right-margin", "gnu.text.PrettyWriter", "lineLengthLoc" };
    String[] arrayOfString4 = { "out:xml-indent", "gnu.xml.XMLPrinter", "indentLoc" };
    propertyFields = new String[][] { arrayOfString1, arrayOfString2, { "out:base", "gnu.kawa.functions.DisplayFormat", "outBase" }, { "out:radix", "gnu.kawa.functions.DisplayFormat", "outRadix" }, { "out:line-length", "gnu.text.PrettyWriter", "lineLengthLoc" }, arrayOfString3, { "out:miser-width", "gnu.text.PrettyWriter", "miserWidthLoc" }, arrayOfString4, { "display:toolkit", "gnu.kawa.models.Display", "myDisplay" }, null };
  }
  
  public static void processArgs(String[] paramArrayOfString)
  {
    int j = 0;
    int i = 0;
    if (processCommandLinePropertyAssignments) {
      for (;;)
      {
        j = i;
        if (i >= paramArrayOfString.length) {
          break;
        }
        j = i;
        if (!processSetProperty(paramArrayOfString[i])) {
          break;
        }
        i += 1;
      }
    }
    setArgs(paramArrayOfString, j);
  }
  
  public static void processSetProperties()
  {
    String[] arrayOfString = commandLineArgArray;
    if (arrayOfString == null) {
      processCommandLinePropertyAssignments = true;
    }
    int i;
    do
    {
      return;
      i = 0;
      while ((i < arrayOfString.length) && (processSetProperty(arrayOfString[i]))) {
        i += 1;
      }
    } while (i == 0);
    setArgs(arrayOfString, i);
  }
  
  public static boolean processSetProperty(String paramString)
  {
    int i = paramString.indexOf('=');
    if (i <= 0) {
      return false;
    }
    Object localObject1 = paramString.substring(0, i);
    paramString = paramString.substring(i + 1);
    i = 0;
    for (;;)
    {
      Object localObject2 = propertyFields[i];
      if (localObject2 == null) {}
      for (;;)
      {
        localObject1 = Symbol.parse((String)localObject1);
        Language.getDefaultLanguage();
        Environment.getCurrent().define((Symbol)localObject1, null, paramString);
        return true;
        if (((String)localObject1).equals(localObject2[0]))
        {
          String str = localObject2[1];
          localObject2 = localObject2[2];
          try
          {
            ((ThreadLocation)Class.forName(str).getDeclaredField((String)localObject2).get(null)).setGlobal(paramString);
          }
          catch (Throwable localThrowable)
          {
            System.err.println("error setting property " + (String)localObject1 + " field " + str + '.' + (String)localObject2 + ": " + localThrowable);
            System.exit(-1);
          }
        }
      }
      i += 1;
    }
  }
  
  public static void setArgs(String[] paramArrayOfString, int paramInt)
  {
    int i = paramArrayOfString.length - paramInt;
    Object[] arrayOfObject = new Object[i];
    if (paramInt == 0) {
      commandLineArgArray = paramArrayOfString;
    }
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      arrayOfObject[i] = new FString(paramArrayOfString[(i + paramInt)]);
      continue;
      String[] arrayOfString = new String[i];
      int j = i;
      for (;;)
      {
        j -= 1;
        if (j < 0) {
          break;
        }
        arrayOfString[j] = paramArrayOfString[(j + paramInt)];
      }
      commandLineArgArray = arrayOfString;
    }
    commandLineArguments = new FVector(arrayOfObject);
    Environment.getCurrent().put("command-line-arguments", commandLineArguments);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ApplicationMainSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */