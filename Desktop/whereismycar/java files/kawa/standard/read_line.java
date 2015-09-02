package kawa.standard;

import gnu.expr.Special;
import gnu.lists.FString;
import gnu.mapping.Values;
import gnu.text.LineBufferedReader;
import java.io.IOException;

public class read_line
{
  public static Object apply(LineBufferedReader paramLineBufferedReader, String paramString)
    throws IOException
  {
    if (paramLineBufferedReader.read() < 0)
    {
      paramLineBufferedReader = Special.eof;
      return paramLineBufferedReader;
    }
    int n = paramLineBufferedReader.pos - 1;
    int i1 = paramLineBufferedReader.limit;
    Object localObject = paramLineBufferedReader.buffer;
    int k = -1;
    int j;
    for (int i = n;; i = j)
    {
      int m;
      if (i < i1)
      {
        j = i + 1;
        int i2 = localObject[i];
        if ((i2 == 13) || (i2 == 10))
        {
          j -= 1;
          if ((paramString == "trim") || (paramString == "peek"))
          {
            i = k;
            if (paramString == "peek") {
              i = 0;
            }
            if (i2 == 10) {
              i = 1;
            }
          }
          for (paramLineBufferedReader.pos = (j + i);; paramLineBufferedReader.pos = j)
          {
            return new FString((char[])localObject, n, j - n);
            m = j;
            if (j + 1 >= i1) {
              break label226;
            }
            if (localObject[(j + 1)] == '\n') {}
            for (i = 2;; i = 1) {
              break;
            }
            i = k;
            m = j;
            if (paramString != "concat") {
              break label226;
            }
            i = k;
            m = j;
            if (i2 != 10) {
              break label226;
            }
            j += 1;
          }
        }
      }
      else
      {
        m = i;
        i = k;
        label226:
        StringBuffer localStringBuffer = new StringBuffer(100);
        if (m > n) {
          localStringBuffer.append((char[])localObject, n, m - n);
        }
        paramLineBufferedReader.pos = m;
        char c;
        if (paramString == "peek") {
          c = 'P';
        }
        for (;;)
        {
          paramLineBufferedReader.readLine(localStringBuffer, c);
          k = localStringBuffer.length();
          j = k;
          if (paramString == "split")
          {
            if (k != 0) {
              break label389;
            }
            i = 0;
            j = k;
          }
          localObject = new FString(localStringBuffer, 0, j);
          paramLineBufferedReader = (LineBufferedReader)localObject;
          if (paramString != "split") {
            break;
          }
          return new Values(new Object[] { localObject, new FString(localStringBuffer, j - i, i) });
          if ((paramString == "concat") || (paramString == "split")) {
            c = 'A';
          } else {
            c = 'I';
          }
        }
        label389:
        i = localStringBuffer.charAt(k - 1);
        if (i == 13) {
          i = 1;
        }
        for (;;)
        {
          j = k - i;
          break;
          if (i != 10) {
            i = 0;
          } else if ((i > 2) && (localStringBuffer.charAt(k - 2) == '\r')) {
            i = 2;
          } else {
            i = 1;
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\read_line.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */