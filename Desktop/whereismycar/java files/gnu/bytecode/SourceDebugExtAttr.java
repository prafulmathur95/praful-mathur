package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class SourceDebugExtAttr
  extends Attribute
{
  int curFileIndex = -1;
  String curFileName;
  int curLineIndex = -1;
  byte[] data;
  private String defaultStratumId;
  int dlength;
  int fileCount;
  int[] fileIDs;
  String[] fileNames;
  int lineCount;
  int[] lines;
  int maxFileID;
  private String outputFileName;
  
  public SourceDebugExtAttr(ClassType paramClassType)
  {
    super("SourceDebugExtension");
    addToFrontOf(paramClassType);
  }
  
  private int fixLine(int paramInt1, int paramInt2)
  {
    int j = this.lines[paramInt2];
    int k = this.lines[(paramInt2 + 2)];
    int i = j;
    if (paramInt1 < j) {
      if (paramInt2 <= 0) {}
    }
    do
    {
      return -1;
      this.lines[paramInt2] = paramInt1;
      this.lines[(paramInt2 + 2)] = (j + k - 1 - paramInt1 + 1);
      this.lines[(paramInt2 + 3)] = paramInt1;
      i = paramInt1;
      j = this.lines[(paramInt2 + 3)] - i;
      if (paramInt1 < i + k) {
        return paramInt1 + j;
      }
    } while ((paramInt2 != (this.lineCount - 1) * 5) && ((paramInt2 != 0) || (paramInt1 >= this.lines[8])));
    this.lines[(paramInt2 + 2)] = (paramInt1 - i + 1);
    return paramInt1 + j;
  }
  
  void addFile(String paramString)
  {
    if ((this.curFileName == paramString) || ((paramString != null) && (paramString.equals(this.curFileName)))) {
      return;
    }
    this.curFileName = paramString;
    String str = SourceFileAttr.fixSourceFile(paramString);
    int m = str.lastIndexOf('/');
    Object localObject;
    label84:
    int k;
    int i;
    if (m >= 0)
    {
      paramString = str.substring(m + 1);
      localObject = paramString + '\n' + str;
      str = paramString;
      paramString = (String)localObject;
      if ((this.curFileIndex >= 0) && (paramString.equals(this.fileNames[this.curFileIndex]))) {
        break label161;
      }
      k = this.fileCount;
      i = 0;
    }
    for (;;)
    {
      if (i >= k) {
        break label172;
      }
      if ((i != this.curFileIndex) && (paramString.equals(this.fileNames[i])))
      {
        this.curFileIndex = i;
        this.curLineIndex = -1;
        return;
        paramString = str;
        break label84;
        label161:
        break;
      }
      i += 1;
    }
    label172:
    if (this.fileIDs == null)
    {
      this.fileIDs = new int[5];
      this.fileNames = new String[5];
    }
    for (;;)
    {
      this.fileCount += 1;
      i = this.maxFileID + 1;
      this.maxFileID = i;
      int j = i << 1;
      i = j;
      if (m >= 0) {
        i = j + 1;
      }
      this.fileNames[k] = paramString;
      if (this.outputFileName == null) {
        this.outputFileName = str;
      }
      this.fileIDs[k] = i;
      this.curFileIndex = k;
      this.curLineIndex = -1;
      return;
      if (k >= this.fileIDs.length)
      {
        localObject = new int[k * 2];
        String[] arrayOfString = new String[k * 2];
        System.arraycopy(this.fileIDs, 0, localObject, 0, k);
        System.arraycopy(this.fileNames, 0, arrayOfString, 0, k);
        this.fileIDs = ((int[])localObject);
        this.fileNames = arrayOfString;
      }
    }
  }
  
  public void addStratum(String paramString)
  {
    this.defaultStratumId = paramString;
  }
  
  public void assignConstants(ClassType paramClassType)
  {
    super.assignConstants(paramClassType);
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("SMAP\n");
    nonAsteriskString(this.outputFileName, localStringBuffer);
    localStringBuffer.append('\n');
    int i;
    label88:
    int k;
    if (this.defaultStratumId == null)
    {
      paramClassType = "Java";
      nonAsteriskString(paramClassType, localStringBuffer);
      localStringBuffer.append('\n');
      localStringBuffer.append("*S ");
      localStringBuffer.append(paramClassType);
      localStringBuffer.append('\n');
      localStringBuffer.append("*F\n");
      i = 0;
      if (i >= this.fileCount) {
        break label181;
      }
      k = this.fileIDs[i];
      if ((k & 0x1) == 0) {
        break label175;
      }
    }
    label175:
    for (int j = 1;; j = 0)
    {
      if (j != 0) {
        localStringBuffer.append("+ ");
      }
      localStringBuffer.append(k >> 1);
      localStringBuffer.append(' ');
      localStringBuffer.append(this.fileNames[i]);
      localStringBuffer.append('\n');
      i += 1;
      break label88;
      paramClassType = this.defaultStratumId;
      break;
    }
    label181:
    if (this.lineCount > 0)
    {
      k = 0;
      localStringBuffer.append("*L\n");
      i = 0;
      j = 0;
      int n;
      do
      {
        int m = this.lines[j];
        n = this.fileIDs[this.lines[(j + 1)]] >> 1;
        int i1 = this.lines[(j + 2)];
        int i2 = this.lines[(j + 3)];
        int i3 = this.lines[(j + 4)];
        localStringBuffer.append(m);
        m = k;
        if (n != k)
        {
          localStringBuffer.append('#');
          localStringBuffer.append(n);
          m = n;
        }
        if (i1 != 1)
        {
          localStringBuffer.append(',');
          localStringBuffer.append(i1);
        }
        localStringBuffer.append(':');
        localStringBuffer.append(i2);
        if (i3 != 1)
        {
          localStringBuffer.append(',');
          localStringBuffer.append(i3);
        }
        localStringBuffer.append('\n');
        j += 5;
        n = i + 1;
        i = n;
        k = m;
      } while (n < this.lineCount);
    }
    localStringBuffer.append("*E\n");
    try
    {
      this.data = localStringBuffer.toString().getBytes("UTF-8");
      this.dlength = this.data.length;
      return;
    }
    catch (Exception paramClassType)
    {
      throw new RuntimeException(paramClassType.toString());
    }
  }
  
  int fixLine(int paramInt)
  {
    if (this.curLineIndex >= 0)
    {
      i = fixLine(paramInt, this.curLineIndex);
      if (i >= 0) {
        return i;
      }
    }
    int j = 0;
    int n = this.curFileIndex;
    int i = 0;
    int k;
    while (i < this.lineCount)
    {
      if ((j != this.curLineIndex) && (n == this.lines[(j + 1)]))
      {
        k = fixLine(paramInt, j);
        if (k >= 0)
        {
          this.curLineIndex = j;
          return k;
        }
      }
      j += 5;
      i += 1;
    }
    if (this.lines == null)
    {
      this.lines = new int[20];
      if (j != 0) {
        break label231;
      }
      k = paramInt;
    }
    label231:
    int m;
    for (i = paramInt;; i = m)
    {
      this.lines[j] = paramInt;
      this.lines[(j + 1)] = n;
      this.lines[(j + 2)] = 1;
      this.lines[(j + 3)] = k;
      this.lines[(j + 4)] = 1;
      this.curLineIndex = j;
      this.lineCount += 1;
      return i;
      if (j < this.lines.length) {
        break;
      }
      int[] arrayOfInt = new int[j * 2];
      System.arraycopy(this.lines, 0, arrayOfInt, 0, j);
      this.lines = arrayOfInt;
      break;
      k = this.lines[(j - 5 + 3)] + this.lines[(j - 5 + 2)];
      i = k;
      if (j == 5)
      {
        i = k;
        if (k < 10000) {
          i = 10000;
        }
      }
      m = i;
      k = i;
    }
  }
  
  public int getLength()
  {
    return this.dlength;
  }
  
  void nonAsteriskString(String paramString, StringBuffer paramStringBuffer)
  {
    if ((paramString == null) || (paramString.length() == 0) || (paramString.charAt(0) == '*')) {
      paramStringBuffer.append(' ');
    }
    paramStringBuffer.append(paramString);
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter)
  {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.println(this.dlength);
    try
    {
      paramClassTypeWriter.print(new String(this.data, 0, this.dlength, "UTF-8"));
      if ((this.dlength > 0) && (this.data[(this.dlength - 1)] != 13) && (this.data[(this.dlength - 1)] != 10)) {
        paramClassTypeWriter.println();
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        paramClassTypeWriter.print("(Caught ");
        paramClassTypeWriter.print(localException);
        paramClassTypeWriter.println(')');
      }
    }
  }
  
  public void write(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    paramDataOutputStream.write(this.data, 0, this.dlength);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\SourceDebugExtAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */