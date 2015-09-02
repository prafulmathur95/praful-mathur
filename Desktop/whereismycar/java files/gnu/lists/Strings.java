package gnu.lists;

import java.io.PrintWriter;

public class Strings
{
  public static void makeCapitalize(CharSeq paramCharSeq)
  {
    char c1 = ' ';
    int j = paramCharSeq.length();
    int i = 0;
    if (i < j)
    {
      char c2 = paramCharSeq.charAt(i);
      if (!Character.isLetterOrDigit(c1)) {}
      for (c1 = Character.toTitleCase(c2);; c1 = Character.toLowerCase(c2))
      {
        paramCharSeq.setCharAt(i, c1);
        i += 1;
        break;
      }
    }
  }
  
  public static void makeLowerCase(CharSeq paramCharSeq)
  {
    int i = paramCharSeq.length();
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      paramCharSeq.setCharAt(i, Character.toLowerCase(paramCharSeq.charAt(i)));
    }
  }
  
  public static void makeUpperCase(CharSeq paramCharSeq)
  {
    int i = paramCharSeq.length();
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      paramCharSeq.setCharAt(i, Character.toUpperCase(paramCharSeq.charAt(i)));
    }
  }
  
  public static void printQuoted(CharSequence paramCharSequence, PrintWriter paramPrintWriter, int paramInt)
  {
    int j = paramCharSequence.length();
    paramPrintWriter.print('"');
    int i = 0;
    if (i < j)
    {
      char c = paramCharSequence.charAt(i);
      if ((c == '\\') || (c == '"'))
      {
        paramPrintWriter.print('\\');
        label51:
        paramPrintWriter.print(c);
      }
      for (;;)
      {
        i += 1;
        break;
        if (paramInt <= 0) {
          break label51;
        }
        if (c == '\n')
        {
          paramPrintWriter.print("\\n");
        }
        else if (c == '\r')
        {
          paramPrintWriter.print("\\r");
        }
        else if (c == '\t')
        {
          paramPrintWriter.print("\\t");
        }
        else if (c == '\007')
        {
          paramPrintWriter.print("\\a");
        }
        else if (c == '\b')
        {
          paramPrintWriter.print("\\b");
        }
        else if (c == '\013')
        {
          paramPrintWriter.print("\\v");
        }
        else
        {
          if (c != '\f') {
            break label51;
          }
          paramPrintWriter.print("\\f");
        }
      }
    }
    paramPrintWriter.print('"');
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\Strings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */