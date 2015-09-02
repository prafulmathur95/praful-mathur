package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.collect.Lists;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CsvUtil
{
  public static YailList fromCsvRow(String paramString)
    throws Exception
  {
    paramString = new CsvParser(new StringReader(paramString));
    if (paramString.hasNext())
    {
      YailList localYailList = YailList.makeList(paramString.next());
      if (paramString.hasNext()) {
        throw new IllegalArgumentException("CSV text has multiple rows. Expected just one row.");
      }
      paramString.throwAnyProblem();
      return localYailList;
    }
    throw new IllegalArgumentException("CSV text cannot be parsed as a row.");
  }
  
  public static YailList fromCsvTable(String paramString)
    throws Exception
  {
    paramString = new CsvParser(new StringReader(paramString));
    ArrayList localArrayList = new ArrayList();
    while (paramString.hasNext()) {
      localArrayList.add(YailList.makeList(paramString.next()));
    }
    paramString.throwAnyProblem();
    return YailList.makeList(localArrayList);
  }
  
  private static void makeCsvRow(YailList paramYailList, StringBuilder paramStringBuilder)
  {
    String str = "";
    Object[] arrayOfObject = paramYailList.toArray();
    int j = arrayOfObject.length;
    int i = 0;
    paramYailList = str;
    while (i < j)
    {
      str = arrayOfObject[i].toString().replaceAll("\"", "\"\"");
      paramStringBuilder.append(paramYailList).append("\"").append(str).append("\"");
      paramYailList = ",";
      i += 1;
    }
  }
  
  public static String toCsvRow(YailList paramYailList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    makeCsvRow(paramYailList, localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public static String toCsvTable(YailList paramYailList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramYailList = paramYailList.toArray();
    int j = paramYailList.length;
    int i = 0;
    while (i < j)
    {
      makeCsvRow((YailList)paramYailList[i], localStringBuilder);
      localStringBuilder.append("\r\n");
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private static class CsvParser
    implements Iterator<List<String>>
  {
    private final Pattern ESCAPED_QUOTE_PATTERN = Pattern.compile("\"\"");
    private final char[] buf = new char['⠀'];
    private int cellLength = -1;
    private int delimitedCellLength = -1;
    private final Reader in;
    private Exception lastException;
    private int limit;
    private boolean opened = true;
    private int pos;
    private long previouslyRead;
    
    public CsvParser(Reader paramReader)
    {
      this.in = paramReader;
    }
    
    private int checkedIndex(int paramInt)
    {
      if (paramInt < this.limit) {
        return paramInt;
      }
      return indexAfterCompactionAndFilling(paramInt);
    }
    
    private int compact(int paramInt)
    {
      int i = this.pos;
      this.pos = 0;
      int j = this.limit - i;
      if (j > 0) {
        System.arraycopy(this.buf, i, this.buf, 0, j);
      }
      this.limit -= i;
      this.previouslyRead += i;
      return paramInt - i;
    }
    
    private void fill()
    {
      int i = this.buf.length - this.limit;
      while ((this.opened) && (i > 0))
      {
        int j;
        try
        {
          j = this.in.read(this.buf, this.limit, i);
          if (j != -1) {
            break label66;
          }
          this.opened = false;
        }
        catch (IOException localIOException)
        {
          this.lastException = localIOException;
          this.opened = false;
        }
        continue;
        label66:
        this.limit += j;
        i -= j;
      }
    }
    
    private boolean findDelimOrEnd(int paramInt)
    {
      for (;;)
      {
        int i = paramInt;
        if (paramInt >= this.limit)
        {
          i = indexAfterCompactionAndFilling(paramInt);
          if (i >= this.limit) {
            break;
          }
        }
        switch (this.buf[i])
        {
        default: 
          this.lastException = new IOException("Syntax Error: non-whitespace between closing quote and delimiter or end");
          return false;
        case '\r': 
          i = checkedIndex(i + 1);
          paramInt = i;
          if (this.buf[i] == '\n') {
            paramInt = checkedIndex(i + 1);
          }
          this.delimitedCellLength = (paramInt - this.pos);
          return true;
        case '\n': 
        case ',': 
          this.delimitedCellLength = (checkedIndex(i + 1) - this.pos);
          return true;
        }
        paramInt = i + 1;
      }
      this.delimitedCellLength = (this.limit - this.pos);
      return true;
    }
    
    private boolean findUnescapedEndQuote(int paramInt)
    {
      int i = paramInt;
      for (;;)
      {
        paramInt = i;
        if (i >= this.limit)
        {
          paramInt = indexAfterCompactionAndFilling(i);
          if (paramInt >= this.limit) {
            break;
          }
        }
        i = paramInt;
        if (this.buf[paramInt] == '"')
        {
          paramInt = checkedIndex(paramInt + 1);
          if (paramInt != this.limit)
          {
            i = paramInt;
            if (this.buf[paramInt] == '"') {}
          }
          else
          {
            this.cellLength = (paramInt - this.pos);
            return findDelimOrEnd(paramInt);
          }
        }
        i += 1;
      }
      this.lastException = new IllegalArgumentException("Syntax Error. unclosed quoted cell");
      return false;
    }
    
    private boolean findUnquotedCellEnd(int paramInt)
    {
      for (;;)
      {
        i = paramInt;
        if (paramInt >= this.limit)
        {
          i = indexAfterCompactionAndFilling(paramInt);
          if (i >= this.limit) {
            break;
          }
        }
        switch (this.buf[i])
        {
        default: 
          paramInt = i + 1;
        }
      }
      this.cellLength = (i - this.pos);
      this.delimitedCellLength = (this.cellLength + 1);
      return true;
      this.cellLength = (i - this.pos);
      int i = checkedIndex(i + 1);
      paramInt = i;
      if (this.buf[i] == '\n') {
        paramInt = checkedIndex(i + 1);
      }
      this.delimitedCellLength = (paramInt - this.pos);
      return true;
      this.lastException = new IllegalArgumentException("Syntax Error: quote in unquoted cell");
      return false;
      paramInt = this.limit - this.pos;
      this.cellLength = paramInt;
      this.delimitedCellLength = paramInt;
      return true;
    }
    
    private int indexAfterCompactionAndFilling(int paramInt)
    {
      int i = paramInt;
      if (this.pos > 0) {
        i = compact(paramInt);
      }
      fill();
      return i;
    }
    
    private boolean lookingAtCell()
    {
      if (this.buf[this.pos] == '"') {
        return findUnescapedEndQuote(this.pos + 1);
      }
      return findUnquotedCellEnd(this.pos);
    }
    
    public long getCharPosition()
    {
      return this.previouslyRead + this.pos;
    }
    
    public boolean hasNext()
    {
      if (this.limit == 0) {
        fill();
      }
      return ((this.pos < this.limit) || (indexAfterCompactionAndFilling(this.pos) < this.limit)) && (lookingAtCell());
    }
    
    public ArrayList<String> next()
    {
      ArrayList localArrayList = Lists.newArrayList();
      label75:
      label193:
      label198:
      label202:
      for (;;)
      {
        int i;
        if (this.buf[this.pos] != '"')
        {
          localArrayList.add(new String(this.buf, this.pos, this.cellLength).trim());
          if ((this.delimitedCellLength <= 0) || (this.buf[(this.pos + this.delimitedCellLength - 1)] != ',')) {
            break label193;
          }
          i = 1;
          this.pos += this.delimitedCellLength;
          this.cellLength = -1;
          this.delimitedCellLength = -1;
          if ((this.pos >= this.limit) && (indexAfterCompactionAndFilling(this.pos) >= this.limit)) {
            break label198;
          }
        }
        for (int j = 1;; j = 0)
        {
          if ((i != 0) && (j != 0) && (lookingAtCell())) {
            break label202;
          }
          return localArrayList;
          String str = new String(this.buf, this.pos + 1, this.cellLength - 2);
          localArrayList.add(this.ESCAPED_QUOTE_PATTERN.matcher(str).replaceAll("\"").trim());
          break;
          i = 0;
          break label75;
        }
      }
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
    
    public void skip(long paramLong)
      throws IOException
    {
      for (;;)
      {
        int i;
        if (paramLong > 0L)
        {
          i = this.in.read(this.buf, 0, Math.min((int)paramLong, this.buf.length));
          if (i >= 0) {}
        }
        else
        {
          return;
        }
        this.previouslyRead += i;
        paramLong -= i;
      }
    }
    
    public void throwAnyProblem()
      throws Exception
    {
      if (this.lastException != null) {
        throw this.lastException;
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\CsvUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */