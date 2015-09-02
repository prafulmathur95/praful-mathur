package org.acra;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.acra.collector.CrashReportData;

final class CrashReportPersister
{
  private static final int CONTINUE = 3;
  private static final int IGNORE = 5;
  private static final int KEY_DONE = 4;
  private static final String LINE_SEPARATOR = "\n";
  private static final int NONE = 0;
  private static final int SLASH = 1;
  private static final int UNICODE = 2;
  private final Context context;
  
  CrashReportPersister(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private void dumpString(StringBuilder paramStringBuilder, String paramString, boolean paramBoolean)
  {
    int j = 0;
    int i = j;
    if (!paramBoolean)
    {
      i = j;
      if (paramString.length() < 0)
      {
        i = j;
        if (paramString.charAt(0) == ' ')
        {
          paramStringBuilder.append("\\ ");
          i = 0 + 1;
        }
      }
    }
    if (i < paramString.length())
    {
      char c = paramString.charAt(i);
      switch (c)
      {
      case '\013': 
      default: 
        if (("\\#!=:".indexOf(c) >= 0) || ((paramBoolean) && (c == ' '))) {
          paramStringBuilder.append('\\');
        }
        if ((c >= ' ') && (c <= '~')) {
          paramStringBuilder.append(c);
        }
        break;
      }
      for (;;)
      {
        i += 1;
        break;
        paramStringBuilder.append("\\t");
        continue;
        paramStringBuilder.append("\\n");
        continue;
        paramStringBuilder.append("\\f");
        continue;
        paramStringBuilder.append("\\r");
        continue;
        String str = Integer.toHexString(c);
        paramStringBuilder.append("\\u");
        j = 0;
        while (j < 4 - str.length())
        {
          paramStringBuilder.append("0");
          j += 1;
        }
        paramStringBuilder.append(str);
      }
    }
  }
  
  private boolean isEbcdic(BufferedInputStream paramBufferedInputStream)
    throws IOException
  {
    int i;
    do
    {
      i = (byte)paramBufferedInputStream.read();
      if ((i == -1) || (i == 35) || (i == 10) || (i == 61)) {
        return false;
      }
    } while (i != 21);
    return true;
  }
  
  private CrashReportData load(Reader paramReader)
    throws IOException
  {
    int i = 0;
    int i1 = 0;
    int n = 0;
    Object localObject1;
    int j;
    int i4;
    CrashReportData localCrashReportData;
    Object localObject2;
    int m;
    int k;
    char c2;
    int i6;
    int i2;
    int i5;
    label221:
    int i3;
    for (;;)
    {
      try
      {
        localObject1 = new char[40];
        j = -1;
        i4 = 1;
        localCrashReportData = new CrashReportData();
        localObject2 = new BufferedReader(paramReader, 8192);
        m = 0;
        paramReader = (Reader)localObject1;
        k = ((BufferedReader)localObject2).read();
        if (k == -1)
        {
          if ((i != 2) || (n > 4)) {
            break label1162;
          }
          throw new IllegalArgumentException("luni.08");
        }
      }
      finally {}
      c2 = (char)k;
      localObject1 = paramReader;
      if (m == paramReader.length)
      {
        localObject1 = new char[paramReader.length * 2];
        System.arraycopy(paramReader, 0, localObject1, 0, m);
      }
      i6 = n;
      i2 = i;
      k = m;
      i5 = i1;
      if (i != 2) {
        break label552;
      }
      k = Character.digit(c2, 16);
      if (k >= 0)
      {
        k = (i1 << 4) + k;
        i2 = n + 1;
        paramReader = (Reader)localObject1;
        n = i2;
        i1 = k;
        if (i2 >= 4)
        {
          i = i2;
          i1 = k;
          break;
        }
      }
      else
      {
        i = n;
        if (n > 4) {
          break;
        }
        throw new IllegalArgumentException("luni.09");
        i3 = i2;
        if (!Character.isWhitespace(c2)) {
          break label1136;
        }
        i3 = i2;
        if (i2 != 3) {
          break label871;
        }
        i3 = 5;
        break label871;
        do
        {
          i3 = ((BufferedReader)localObject2).read();
          paramReader = (Reader)localObject1;
          n = i6;
          i = i2;
          m = k;
          i1 = i5;
          if (i3 == -1) {
            break;
          }
          i3 = (char)i3;
          paramReader = (Reader)localObject1;
          n = i6;
          i = i2;
          m = k;
          i1 = i5;
          if (i3 == 13) {
            break;
          }
          paramReader = (Reader)localObject1;
          n = i6;
          i = i2;
          m = k;
          i1 = i5;
          if (i3 == 10) {
            break;
          }
        } while (i3 != 133);
        paramReader = (Reader)localObject1;
        n = i6;
        i = i2;
        m = k;
        i1 = i5;
      }
    }
    label367:
    paramReader = new String((char[])localObject1, 0, k);
    localCrashReportData.put(Enum.valueOf(ReportField.class, paramReader.substring(0, i)), paramReader.substring(i));
    for (;;)
    {
      if (k >= 0)
      {
        paramReader = new String(paramReader, 0, m);
        localObject2 = (ReportField)Enum.valueOf(ReportField.class, paramReader.substring(0, k));
        localObject1 = paramReader.substring(k);
        paramReader = (Reader)localObject1;
        if (i == 1) {
          paramReader = (String)localObject1 + "\000";
        }
        localCrashReportData.put((Enum)localObject2, paramReader);
      }
      return localCrashReportData;
      i2 = 0;
      i3 = 0;
      k = m + 1;
      localObject1[m] = ((char)i1);
      if ((c2 != '\n') && (c2 != ''))
      {
        paramReader = (Reader)localObject1;
        n = i;
        i = i3;
        m = k;
        break;
      }
      i5 = i1;
      i6 = i;
      label552:
      char c1;
      if (i2 == 1)
      {
        i = 0;
        switch (c2)
        {
        default: 
          c1 = c2;
        }
      }
      for (;;)
      {
        i4 = 0;
        m = i;
        if (i == 4)
        {
          j = k;
          m = 0;
        }
        localObject1[k] = c1;
        k += 1;
        paramReader = (Reader)localObject1;
        n = i6;
        i = m;
        m = k;
        i1 = i5;
        break;
        i = 3;
        paramReader = (Reader)localObject1;
        n = i6;
        m = k;
        i1 = i5;
        break;
        i = 5;
        paramReader = (Reader)localObject1;
        n = i6;
        m = k;
        i1 = i5;
        break;
        c1 = '\b';
        continue;
        c1 = '\f';
        continue;
        c1 = '\n';
        continue;
        c1 = '\r';
        continue;
        c1 = '\t';
        continue;
        i = 2;
        n = 0;
        i1 = 0;
        paramReader = (Reader)localObject1;
        m = k;
        break;
        switch (c2)
        {
        default: 
          break label221;
          paramReader = (Reader)localObject1;
          n = i6;
          i = i3;
          m = k;
          i1 = i5;
          if (k == 0) {
            break;
          }
          paramReader = (Reader)localObject1;
          n = i6;
          i = i3;
          m = k;
          i1 = i5;
          if (k == j) {
            break;
          }
          paramReader = (Reader)localObject1;
          n = i6;
          i = i3;
          m = k;
          i1 = i5;
          if (i3 == 5) {
            break;
          }
          if (j == -1)
          {
            i = 4;
            paramReader = (Reader)localObject1;
            n = i6;
            m = k;
            i1 = i5;
          }
          break;
        case '!': 
        case '#': 
          if (i4 == 0) {
            break label221;
          }
          break;
        case '\n': 
          if (i2 == 3)
          {
            i = 5;
            paramReader = (Reader)localObject1;
            n = i6;
            m = k;
            i1 = i5;
          }
          break;
        case '\r': 
        case '': 
          m = 0;
          i4 = 1;
          if ((k > 0) || ((k == 0) && (j == 0)))
          {
            i = j;
            if (j != -1) {
              break label367;
            }
            i = k;
            break label367;
          }
          j = -1;
          k = 0;
          paramReader = (Reader)localObject1;
          n = i6;
          i = m;
          m = k;
          i1 = i5;
          break;
        case '\\': 
          if (i2 == 4) {
            j = k;
          }
          i = 1;
          paramReader = (Reader)localObject1;
          n = i6;
          m = k;
          i1 = i5;
          break;
        case ':': 
        case '=': 
          label871:
          if (j != -1) {
            break label221;
          }
          i = 0;
          j = k;
          paramReader = (Reader)localObject1;
          n = i6;
          m = k;
          i1 = i5;
          break;
          label1136:
          if (i3 != 5)
          {
            i = i3;
            c1 = c2;
            if (i3 != 3) {}
          }
          else
          {
            i = 0;
            c1 = c2;
          }
          break;
        }
      }
      label1162:
      k = j;
      if (j == -1)
      {
        k = j;
        if (m > 0) {
          k = m;
        }
      }
    }
  }
  
  public CrashReportData load(String paramString)
    throws IOException
  {
    FileInputStream localFileInputStream = this.context.openFileInput(paramString);
    if (localFileInputStream == null) {
      throw new IllegalArgumentException("Invalid crash report fileName : " + paramString);
    }
    try
    {
      paramString = new BufferedInputStream(localFileInputStream, 8192);
      paramString.mark(Integer.MAX_VALUE);
      boolean bool = isEbcdic(paramString);
      paramString.reset();
      if (!bool)
      {
        paramString = load(new InputStreamReader(paramString, "ISO8859-1"));
        return paramString;
      }
      paramString = load(new InputStreamReader(paramString));
      return paramString;
    }
    finally
    {
      localFileInputStream.close();
    }
  }
  
  public void store(CrashReportData paramCrashReportData, String paramString)
    throws IOException
  {
    paramString = this.context.openFileOutput(paramString, 0);
    OutputStreamWriter localOutputStreamWriter;
    try
    {
      StringBuilder localStringBuilder = new StringBuilder(200);
      localOutputStreamWriter = new OutputStreamWriter(paramString, "ISO8859_1");
      paramCrashReportData = paramCrashReportData.entrySet().iterator();
      while (paramCrashReportData.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramCrashReportData.next();
        dumpString(localStringBuilder, ((ReportField)localEntry.getKey()).toString(), true);
        localStringBuilder.append('=');
        dumpString(localStringBuilder, (String)localEntry.getValue(), false);
        localStringBuilder.append("\n");
        localOutputStreamWriter.write(localStringBuilder.toString());
        localStringBuilder.setLength(0);
      }
    }
    finally
    {
      paramString.close();
    }
    paramString.close();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\org\acra\CrashReportPersister.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */