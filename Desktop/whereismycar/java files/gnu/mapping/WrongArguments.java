package gnu.mapping;

public class WrongArguments
  extends IllegalArgumentException
{
  public int number;
  Procedure proc;
  public String procname;
  public String usage;
  
  public WrongArguments(Procedure paramProcedure, int paramInt)
  {
    this.proc = paramProcedure;
    this.number = paramInt;
  }
  
  public WrongArguments(String paramString1, int paramInt, String paramString2)
  {
    this.procname = paramString1;
    this.number = paramInt;
    this.usage = paramString2;
  }
  
  public static String checkArgCount(Procedure paramProcedure, int paramInt)
  {
    int i = paramProcedure.numArgs();
    String str2 = paramProcedure.getName();
    String str1 = str2;
    if (str2 == null) {
      str1 = paramProcedure.getClass().getName();
    }
    return checkArgCount(str1, i & 0xFFF, i >> 12, paramInt);
  }
  
  public static String checkArgCount(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    StringBuffer localStringBuffer;
    if (paramInt3 < paramInt1)
    {
      i = 0;
      localStringBuffer = new StringBuffer(100);
      localStringBuffer.append("call to ");
      if (paramString != null) {
        break label120;
      }
      localStringBuffer.append("unnamed procedure");
      label39:
      if (i == 0) {
        break label146;
      }
      paramString = " has too many";
      label47:
      localStringBuffer.append(paramString);
      localStringBuffer.append(" arguments (");
      localStringBuffer.append(paramInt3);
      if (paramInt1 != paramInt2) {
        break label152;
      }
      localStringBuffer.append("; must be ");
      localStringBuffer.append(paramInt1);
    }
    for (;;)
    {
      localStringBuffer.append(')');
      return localStringBuffer.toString();
      if ((paramInt2 >= 0) && (paramInt3 > paramInt2))
      {
        i = 1;
        break;
      }
      return null;
      label120:
      localStringBuffer.append('\'');
      localStringBuffer.append(paramString);
      localStringBuffer.append('\'');
      break label39;
      label146:
      paramString = " has too few";
      break label47;
      label152:
      localStringBuffer.append("; min=");
      localStringBuffer.append(paramInt1);
      if (paramInt2 >= 0)
      {
        localStringBuffer.append(", max=");
        localStringBuffer.append(paramInt2);
      }
    }
  }
  
  public String getMessage()
  {
    if (this.proc != null)
    {
      String str = checkArgCount(this.proc, this.number);
      if (str != null) {
        return str;
      }
    }
    return super.getMessage();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\WrongArguments.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */