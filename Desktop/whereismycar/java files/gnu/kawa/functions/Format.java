package gnu.kawa.functions;

import gnu.lists.FString;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.MessageFormat;

public class Format
  extends ProcedureN
{
  public static final Format format = new Format();
  
  static
  {
    format.setName("format");
    format.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyFormat");
  }
  
  static Object[] drop2(Object[] paramArrayOfObject)
  {
    int i = paramArrayOfObject.length - 2;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(paramArrayOfObject, 2, arrayOfObject, 0, i);
    return arrayOfObject;
  }
  
  public static Object format(Object... paramVarArgs)
  {
    Object localObject = paramVarArgs[0];
    if (localObject == Boolean.TRUE)
    {
      format(OutPort.outDefault(), paramVarArgs, 1);
      return Values.empty;
    }
    if (localObject == Boolean.FALSE) {
      return formatToString(1, paramVarArgs);
    }
    if (((localObject instanceof MessageFormat)) || ((localObject instanceof CharSequence)) || ((localObject instanceof ReportFormat))) {
      return formatToString(0, paramVarArgs);
    }
    if ((localObject instanceof Writer))
    {
      format((Writer)localObject, paramVarArgs, 1);
      return Values.empty;
    }
    if ((localObject instanceof OutputStream))
    {
      formatToOutputStream((OutputStream)localObject, paramVarArgs[1], drop2(paramVarArgs));
      return Values.empty;
    }
    throw new RuntimeException("bad first argument to format");
  }
  
  public static void format(Writer paramWriter, Object[] paramArrayOfObject, int paramInt)
  {
    int i = paramInt + 1;
    Object localObject = paramArrayOfObject[paramInt];
    Object[] arrayOfObject = new Object[paramArrayOfObject.length - i];
    System.arraycopy(paramArrayOfObject, i, arrayOfObject, 0, arrayOfObject.length);
    formatToWriter(paramWriter, localObject, arrayOfObject);
  }
  
  public static FString formatToFString(char paramChar, Object paramObject, Object[] paramArrayOfObject)
  {
    ReportFormat localReportFormat = ParseFormat.asFormat(paramObject, paramChar);
    paramObject = new CharArrayOutPort();
    try
    {
      localReportFormat.format(paramArrayOfObject, 0, (Writer)paramObject, null);
      paramArrayOfObject = ((CharArrayOutPort)paramObject).toCharArray();
      ((CharArrayOutPort)paramObject).close();
      return new FString(paramArrayOfObject);
    }
    catch (IOException paramObject)
    {
      throw new RuntimeException("Error in format: " + paramObject);
    }
  }
  
  public static void formatToOutputStream(OutputStream paramOutputStream, Object paramObject, Object... paramVarArgs)
  {
    paramOutputStream = new OutPort(paramOutputStream);
    format(new Object[] { paramOutputStream, paramObject, paramVarArgs });
    paramOutputStream.closeThis();
  }
  
  public static String formatToString(int paramInt, Object... paramVarArgs)
  {
    CharArrayOutPort localCharArrayOutPort = new CharArrayOutPort();
    format(localCharArrayOutPort, paramVarArgs, paramInt);
    paramVarArgs = localCharArrayOutPort.toString();
    localCharArrayOutPort.close();
    return paramVarArgs;
  }
  
  public static void formatToWriter(Writer paramWriter, Object paramObject, Object... paramVarArgs)
  {
    Object localObject = paramWriter;
    if (paramWriter == null) {
      localObject = OutPort.outDefault();
    }
    try
    {
      if ((paramObject instanceof MessageFormat))
      {
        ((Writer)localObject).write(((MessageFormat)paramObject).format(paramVarArgs));
        return;
      }
      paramWriter = (Writer)paramObject;
      if (!(paramObject instanceof ReportFormat)) {
        paramWriter = ParseFormat.parseFormat.apply1(paramObject);
      }
      ((ReportFormat)paramWriter).format(paramVarArgs, 0, (Writer)localObject, null);
      return;
    }
    catch (IOException paramWriter)
    {
      throw new RuntimeException("Error in format: " + paramWriter);
    }
  }
  
  public Object applyN(Object[] paramArrayOfObject)
  {
    return format(paramArrayOfObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\Format.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */