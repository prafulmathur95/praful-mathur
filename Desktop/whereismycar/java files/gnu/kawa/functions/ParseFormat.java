package gnu.kawa.functions;

import gnu.mapping.Procedure1;
import gnu.text.CompoundFormat;
import gnu.text.LineBufferedReader;
import gnu.text.LiteralFormat;
import gnu.text.PadFormat;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.util.Vector;

public class ParseFormat
  extends Procedure1
{
  public static final int PARAM_FROM_LIST = -1610612736;
  public static final int PARAM_UNSPECIFIED = -1073741824;
  public static final int SEEN_HASH = 16;
  public static final int SEEN_MINUS = 1;
  public static final int SEEN_PLUS = 2;
  public static final int SEEN_SPACE = 4;
  public static final int SEEN_ZERO = 8;
  public static final ParseFormat parseFormat = new ParseFormat(false);
  boolean emacsStyle = true;
  
  public ParseFormat(boolean paramBoolean)
  {
    this.emacsStyle = paramBoolean;
  }
  
  /* Error */
  public static ReportFormat asFormat(Object paramObject, char paramChar)
  {
    // Byte code:
    //   0: aload_0
    //   1: instanceof 46
    //   4: ifeq +8 -> 12
    //   7: aload_0
    //   8: checkcast 46	gnu/text/ReportFormat
    //   11: areturn
    //   12: iload_1
    //   13: bipush 126
    //   15: if_icmpne +15 -> 30
    //   18: new 48	gnu/kawa/functions/LispFormat
    //   21: dup
    //   22: aload_0
    //   23: invokevirtual 54	java/lang/Object:toString	()Ljava/lang/String;
    //   26: invokespecial 57	gnu/kawa/functions/LispFormat:<init>	(Ljava/lang/String;)V
    //   29: areturn
    //   30: aload_0
    //   31: instanceof 59
    //   34: ifeq +69 -> 103
    //   37: aload_0
    //   38: checkcast 59	gnu/lists/FString
    //   41: astore_0
    //   42: new 61	gnu/mapping/CharArrayInPort
    //   45: dup
    //   46: aload_0
    //   47: getfield 65	gnu/lists/FString:data	[C
    //   50: aload_0
    //   51: getfield 68	gnu/lists/FString:size	I
    //   54: invokespecial 71	gnu/mapping/CharArrayInPort:<init>	([CI)V
    //   57: astore_0
    //   58: aload_0
    //   59: iload_1
    //   60: invokestatic 74	gnu/kawa/functions/ParseFormat:parseFormat	(Lgnu/text/LineBufferedReader;C)Lgnu/text/ReportFormat;
    //   63: astore_2
    //   64: aload_0
    //   65: invokevirtual 79	gnu/mapping/InPort:close	()V
    //   68: aload_2
    //   69: areturn
    //   70: astore_0
    //   71: new 81	java/lang/RuntimeException
    //   74: dup
    //   75: new 83	java/lang/StringBuilder
    //   78: dup
    //   79: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   82: ldc 86
    //   84: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: aload_0
    //   88: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   91: ldc 95
    //   93: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: invokespecial 97	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   102: athrow
    //   103: new 61	gnu/mapping/CharArrayInPort
    //   106: dup
    //   107: aload_0
    //   108: invokevirtual 54	java/lang/Object:toString	()Ljava/lang/String;
    //   111: invokespecial 98	gnu/mapping/CharArrayInPort:<init>	(Ljava/lang/String;)V
    //   114: astore_0
    //   115: goto -57 -> 58
    //   118: astore_2
    //   119: aload_0
    //   120: invokevirtual 79	gnu/mapping/InPort:close	()V
    //   123: aload_2
    //   124: athrow
    //   125: astore_0
    //   126: new 81	java/lang/RuntimeException
    //   129: dup
    //   130: new 83	java/lang/StringBuilder
    //   133: dup
    //   134: invokespecial 84	java/lang/StringBuilder:<init>	()V
    //   137: ldc 100
    //   139: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: aload_0
    //   143: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   146: ldc 95
    //   148: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   154: invokespecial 97	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   157: athrow
    //   158: astore_0
    //   159: new 81	java/lang/RuntimeException
    //   162: dup
    //   163: ldc 102
    //   165: invokespecial 97	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   168: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	169	0	paramObject	Object
    //   0	169	1	paramChar	char
    //   63	6	2	localReportFormat	ReportFormat
    //   118	6	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   0	12	70	java/io/IOException
    //   18	30	70	java/io/IOException
    //   30	58	70	java/io/IOException
    //   64	68	70	java/io/IOException
    //   103	115	70	java/io/IOException
    //   119	125	70	java/io/IOException
    //   58	64	118	finally
    //   0	12	125	java/text/ParseException
    //   18	30	125	java/text/ParseException
    //   30	58	125	java/text/ParseException
    //   64	68	125	java/text/ParseException
    //   103	115	125	java/text/ParseException
    //   119	125	125	java/text/ParseException
    //   0	12	158	java/lang/IndexOutOfBoundsException
    //   18	30	158	java/lang/IndexOutOfBoundsException
    //   30	58	158	java/lang/IndexOutOfBoundsException
    //   64	68	158	java/lang/IndexOutOfBoundsException
    //   103	115	158	java/lang/IndexOutOfBoundsException
    //   119	125	158	java/lang/IndexOutOfBoundsException
  }
  
  public static ReportFormat parseFormat(LineBufferedReader paramLineBufferedReader, char paramChar)
    throws ParseException, IOException
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    char c3 = '\000';
    Vector localVector = new Vector();
    char c2;
    for (;;)
    {
      c4 = paramLineBufferedReader.read();
      c2 = c4;
      if (c4 < 0) {
        break;
      }
      if (c4 != paramChar)
      {
        localStringBuffer.append((char)c4);
      }
      else
      {
        c4 = paramLineBufferedReader.read();
        c2 = c4;
        if (c4 != paramChar) {
          break;
        }
        localStringBuffer.append((char)c4);
      }
    }
    char c4 = localStringBuffer.length();
    Object localObject;
    if (c4 > 0)
    {
      localObject = new char[c4];
      localStringBuffer.getChars(0, c4, (char[])localObject, 0);
      localStringBuffer.setLength(0);
      localVector.addElement(new LiteralFormat((char[])localObject));
    }
    int k;
    char c5;
    label327:
    int j;
    label594:
    int i;
    boolean bool;
    label645:
    label657:
    char c1;
    if (c2 < 0)
    {
      paramChar = localVector.size();
      if (paramChar == '\001')
      {
        paramLineBufferedReader = localVector.elementAt(0);
        if ((paramLineBufferedReader instanceof ReportFormat)) {
          return (ReportFormat)paramLineBufferedReader;
        }
      }
    }
    else
    {
      c4 = c2;
      k = c3;
      if (c2 == '$')
      {
        c3 = Character.digit((char)paramLineBufferedReader.read(), 10);
        c2 = c3;
        if (c3 < 0) {
          throw new ParseException("missing number (position) after '%$'", -1);
        }
        do
        {
          c2 = c2 * '\n' + c3;
          c4 = paramLineBufferedReader.read();
          c3 = Character.digit((char)c4, 10);
        } while (c3 >= 0);
        k = c2 - '\001';
      }
      c2 = '\000';
      switch ((char)c4)
      {
      default: 
        c3 = -1073741824;
        c5 = Character.digit((char)c4, 10);
        if (c5 >= 0)
        {
          c3 = c5;
          c5 = paramLineBufferedReader.read();
          c4 = Character.digit((char)c5, 10);
          if (c4 >= 0) {
            break;
          }
        }
        break;
      }
      for (;;)
      {
        c4 = -1073741824;
        j = c5;
        if (c5 == '.')
        {
          if (c5 != '*') {
            break label594;
          }
          c4 = -1610612736;
          j = c5;
        }
        switch (j)
        {
        default: 
          throw new ParseException("unknown format character '" + j + "'", -1);
          c2 |= 0x1;
          for (;;)
          {
            c4 = paramLineBufferedReader.read();
            break;
            c2 |= 0x2;
            continue;
            c2 |= 0x4;
            continue;
            c2 |= 0x8;
            continue;
            c2 |= 0x10;
          }
          c3 = c3 * '\n' + c4;
          break label327;
          c5 = c4;
          if (c4 == '*')
          {
            c3 = -1610612736;
            c5 = c4;
          }
          break;
        }
      }
      for (c5 = '\000';; c5 = c5 * '\n' + i)
      {
        j = paramLineBufferedReader.read();
        i = Character.digit((char)j, 10);
        c4 = c5;
        if (i < 0) {
          break;
        }
      }
      if (j == 83)
      {
        bool = true;
        localObject = new ObjectFormat(bool, c4);
        if (c3 <= 0) {
          break label983;
        }
        if ((c2 & 0x8) == 0) {
          break label939;
        }
        c1 = '0';
        label673:
        if ((c2 & 0x1) == 0) {
          break label945;
        }
        c2 = 'd';
        label684:
        localObject = new PadFormat((Format)localObject, c3, c1, c2);
      }
    }
    label740:
    label901:
    label908:
    label939:
    label945:
    label983:
    for (;;)
    {
      localVector.addElement(localObject);
      c3 = k + 1;
      break;
      bool = false;
      break label645;
      i = 0;
      if ((j == 100) || (j == 105))
      {
        c5 = '\n';
        if ((c2 & 0x9) != '\b') {
          break label901;
        }
      }
      for (char c6 = '0';; c6 = ' ')
      {
        j = i;
        if ((c2 & 0x10) != 0) {
          j = i | 0x8;
        }
        i = j;
        if ((c2 & 0x2) != 0) {
          i = j | 0x2;
        }
        j = i;
        if ((c2 & 0x1) != 0) {
          j = i | 0x10;
        }
        i = j;
        if ((c2 & 0x4) != 0) {
          i = j | 0x4;
        }
        if (c4 == -1073741824) {
          break label908;
        }
        c2 &= 0xFFFFFFF7;
        localObject = IntegerFormat.getInstance(c5, c4, 48, -1073741824, -1073741824, i | 0x40);
        break;
        if (j == 111)
        {
          c5 = '\b';
          break label740;
        }
        c6 = '\020';
        c5 = c6;
        if (j != 88) {
          break label740;
        }
        i = 32;
        c5 = c6;
        break label740;
      }
      localObject = IntegerFormat.getInstance(c5, c3, c6, -1073741824, -1073741824, i);
      break label657;
      localObject = new ObjectFormat(false);
      break label657;
      c1 = ' ';
      break label673;
      if (c1 == '0')
      {
        c2 = '￿';
        break label684;
      }
      c2 = '\000';
      break label684;
      paramLineBufferedReader = new Format[paramChar];
      localVector.copyInto(paramLineBufferedReader);
      return new CompoundFormat(paramLineBufferedReader);
    }
  }
  
  public Object apply1(Object paramObject)
  {
    if (this.emacsStyle) {}
    for (char c = '?';; c = '~') {
      return asFormat(paramObject, c);
    }
  }
  
  public ReportFormat parseFormat(LineBufferedReader paramLineBufferedReader)
    throws ParseException, IOException
  {
    if (this.emacsStyle) {}
    for (char c = '?';; c = '~') {
      return parseFormat(paramLineBufferedReader, c);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\ParseFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */