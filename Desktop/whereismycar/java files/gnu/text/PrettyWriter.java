package gnu.text;

import gnu.lists.LList;
import gnu.mapping.ThreadLocation;
import java.io.IOException;
import java.io.Writer;

public class PrettyWriter
  extends Writer
{
  private static final int BLOCK_PER_LINE_PREFIX_END = -3;
  private static final int BLOCK_PREFIX_LENGTH = -4;
  private static final int BLOCK_SECTION_COLUMN = -2;
  private static final int BLOCK_SECTION_START_LINE = -6;
  private static final int BLOCK_START_COLUMN = -1;
  private static final int BLOCK_SUFFIX_LENGTH = -5;
  private static final int LOGICAL_BLOCK_LENGTH = 6;
  public static final int NEWLINE_FILL = 70;
  public static final int NEWLINE_LINEAR = 78;
  public static final int NEWLINE_LITERAL = 76;
  public static final int NEWLINE_MANDATORY = 82;
  public static final int NEWLINE_MISER = 77;
  public static final int NEWLINE_SPACE = 83;
  static final int QITEM_BASE_SIZE = 2;
  static final int QITEM_BLOCK_END_SIZE = 2;
  static final int QITEM_BLOCK_END_TYPE = 5;
  static final int QITEM_BLOCK_START_BLOCK_END = 4;
  static final int QITEM_BLOCK_START_PREFIX = 5;
  static final int QITEM_BLOCK_START_SIZE = 7;
  static final int QITEM_BLOCK_START_SUFFIX = 6;
  static final int QITEM_BLOCK_START_TYPE = 4;
  static final int QITEM_INDENTATION_AMOUNT = 3;
  static final char QITEM_INDENTATION_BLOCK = 'B';
  static final char QITEM_INDENTATION_CURRENT = 'C';
  static final int QITEM_INDENTATION_KIND = 2;
  static final int QITEM_INDENTATION_SIZE = 4;
  static final int QITEM_INDENTATION_TYPE = 3;
  static final int QITEM_NEWLINE_KIND = 4;
  static final int QITEM_NEWLINE_SIZE = 5;
  static final int QITEM_NEWLINE_TYPE = 2;
  static final int QITEM_NOP_TYPE = 0;
  static final int QITEM_POSN = 1;
  static final int QITEM_SECTION_START_DEPTH = 2;
  static final int QITEM_SECTION_START_SECTION_END = 3;
  static final int QITEM_SECTION_START_SIZE = 4;
  static final int QITEM_TAB_COLINC = 4;
  static final int QITEM_TAB_COLNUM = 3;
  static final int QITEM_TAB_FLAGS = 2;
  static final int QITEM_TAB_IS_RELATIVE = 2;
  static final int QITEM_TAB_IS_SECTION = 1;
  static final int QITEM_TAB_SIZE = 5;
  static final int QITEM_TAB_TYPE = 6;
  static final int QITEM_TYPE_AND_SIZE = 0;
  static final int QUEUE_INIT_ALLOC_SIZE = 300;
  public static ThreadLocation indentLoc = new ThreadLocation("indent");
  public static int initialBufferSize = 126;
  public static ThreadLocation lineLengthLoc = new ThreadLocation("line-length");
  public static ThreadLocation miserWidthLoc = new ThreadLocation("miser-width");
  int blockDepth = 6;
  int[] blocks = new int[60];
  public char[] buffer = new char[initialBufferSize];
  public int bufferFillPointer;
  int bufferOffset;
  int bufferStartColumn;
  int currentBlock = -1;
  int lineLength = 80;
  int lineNumber;
  int miserWidth = 40;
  protected Writer out;
  public int pendingBlocksCount;
  char[] prefix = new char[initialBufferSize];
  int prettyPrintingMode;
  int[] queueInts = new int['Ĭ'];
  int queueSize;
  String[] queueStrings = new String['Ĭ'];
  int queueTail;
  char[] suffix = new char[initialBufferSize];
  boolean wordEndSeen;
  
  public PrettyWriter(Writer paramWriter)
  {
    this.out = paramWriter;
    this.prettyPrintingMode = 1;
  }
  
  public PrettyWriter(Writer paramWriter, int paramInt)
  {
    this.out = paramWriter;
    this.lineLength = paramInt;
    if (paramInt > 1) {}
    for (paramInt = i;; paramInt = 0)
    {
      this.prettyPrintingMode = paramInt;
      return;
    }
  }
  
  public PrettyWriter(Writer paramWriter, boolean paramBoolean)
  {
    this.out = paramWriter;
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      this.prettyPrintingMode = i;
      return;
    }
  }
  
  private static int enoughSpace(int paramInt1, int paramInt2)
  {
    int i = paramInt1 * 2;
    paramInt1 += (paramInt2 * 5 >> 2);
    if (i > paramInt1) {
      return i;
    }
    return paramInt1;
  }
  
  private int getPerLinePrefixEnd()
  {
    return this.blocks[(this.blockDepth - 3)];
  }
  
  private int getPrefixLength()
  {
    return this.blocks[(this.blockDepth - 4)];
  }
  
  private int getQueueSize(int paramInt)
  {
    return this.queueInts[paramInt] >> 16;
  }
  
  private int getQueueType(int paramInt)
  {
    return this.queueInts[paramInt] & 0xFF;
  }
  
  private int getSectionColumn()
  {
    return this.blocks[(this.blockDepth - 2)];
  }
  
  private int getSectionStartLine()
  {
    return this.blocks[(this.blockDepth - 6)];
  }
  
  private int getStartColumn()
  {
    return this.blocks[(this.blockDepth - 1)];
  }
  
  private int getSuffixLength()
  {
    return this.blocks[(this.blockDepth - 5)];
  }
  
  private int indexPosn(int paramInt)
  {
    return this.bufferOffset + paramInt;
  }
  
  private int posnColumn(int paramInt)
  {
    return indexColumn(posnIndex(paramInt));
  }
  
  private int posnIndex(int paramInt)
  {
    return paramInt - this.bufferOffset;
  }
  
  private void pushLogicalBlock(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    int i = this.blockDepth + 6;
    if (i >= this.blocks.length)
    {
      int[] arrayOfInt = new int[this.blocks.length * 2];
      System.arraycopy(this.blocks, 0, arrayOfInt, 0, this.blockDepth);
      this.blocks = arrayOfInt;
    }
    this.blockDepth = i;
    this.blocks[(this.blockDepth - 1)] = paramInt1;
    this.blocks[(this.blockDepth - 2)] = paramInt1;
    this.blocks[(this.blockDepth - 3)] = paramInt2;
    this.blocks[(this.blockDepth - 4)] = paramInt3;
    this.blocks[(this.blockDepth - 5)] = paramInt4;
    this.blocks[(this.blockDepth - 6)] = paramInt5;
  }
  
  public void addIndentation(int paramInt, boolean paramBoolean)
  {
    if (this.prettyPrintingMode > 0) {
      if (!paramBoolean) {
        break label22;
      }
    }
    label22:
    for (char c = 'C';; c = 'B')
    {
      enqueueIndent(c, paramInt);
      return;
    }
  }
  
  public void clearBuffer()
  {
    this.bufferStartColumn = 0;
    this.bufferFillPointer = 0;
    this.lineNumber = 0;
    this.bufferOffset = 0;
    this.blockDepth = 6;
    this.queueTail = 0;
    this.queueSize = 0;
    this.pendingBlocksCount = 0;
  }
  
  public void clearWordEnd()
  {
    this.wordEndSeen = false;
  }
  
  public void close()
    throws IOException
  {
    if (this.out != null)
    {
      forcePrettyOutput();
      this.out.close();
      this.out = null;
    }
    this.buffer = null;
  }
  
  public void closeThis()
    throws IOException
  {
    if (this.out != null)
    {
      forcePrettyOutput();
      this.out = null;
    }
    this.buffer = null;
  }
  
  int computeTabSize(int paramInt1, int paramInt2, int paramInt3)
  {
    int k = 0;
    int j = this.queueInts[(paramInt1 + 2)];
    int i;
    if ((j & 0x1) != 0)
    {
      i = 1;
      if ((j & 0x2) == 0) {
        break label98;
      }
    }
    label98:
    for (j = 1;; j = 0)
    {
      if (i != 0) {
        k = paramInt2;
      }
      paramInt2 = this.queueInts[(paramInt1 + 3)];
      i = this.queueInts[(paramInt1 + 4)];
      if (j == 0) {
        break label104;
      }
      paramInt1 = paramInt2;
      if (i > 1)
      {
        paramInt3 = (paramInt3 + paramInt2) % i;
        paramInt1 = paramInt2;
        if (paramInt3 != 0) {
          paramInt1 = paramInt2 + paramInt3;
        }
      }
      return paramInt1;
      i = 0;
      break;
    }
    label104:
    if (paramInt3 <= paramInt2 + k) {
      return paramInt3 + k - paramInt3;
    }
    return i - (paramInt3 - k) % i;
  }
  
  public void endLogicalBlock()
  {
    int m = enqueue(5, 2);
    this.pendingBlocksCount -= 1;
    int i;
    int j;
    if (this.currentBlock < 0)
    {
      i = this.blocks[(this.blockDepth - 5)];
      j = this.blocks[(this.blockDepth - 6 - 5)];
      if (i > j) {
        write(this.suffix, this.suffix.length - i, i - j);
      }
      this.currentBlock = -1;
      return;
    }
    int k = this.currentBlock;
    int n = this.queueInts[(k + 4)];
    if (n == 0) {
      this.currentBlock = -1;
    }
    for (;;)
    {
      String str = this.queueStrings[(k + 6)];
      if (str != null) {
        write(str);
      }
      j = m - k;
      i = j;
      if (j < 0) {
        i = j + this.queueInts.length;
      }
      this.queueInts[(k + 4)] = i;
      return;
      j = this.queueTail - k;
      i = j;
      if (j > 0) {
        i = j - this.queueInts.length;
      }
      if (n < i)
      {
        this.currentBlock = -1;
      }
      else
      {
        j = n + k;
        i = j;
        if (j < 0) {
          i = j + this.queueInts.length;
        }
        this.currentBlock = i;
      }
    }
  }
  
  public void endLogicalBlock(String paramString)
  {
    if (this.prettyPrintingMode > 0) {
      endLogicalBlock();
    }
    while (paramString == null) {
      return;
    }
    write(paramString);
  }
  
  public int enqueue(int paramInt1, int paramInt2)
  {
    int i = this.queueInts.length;
    int j = i - this.queueTail - this.queueSize;
    if ((j > 0) && (paramInt2 > j)) {
      enqueue(0, j);
    }
    if (this.queueSize + paramInt2 > i)
    {
      j = enoughSpace(i, paramInt2);
      int[] arrayOfInt = new int[j];
      String[] arrayOfString = new String[j];
      int k = this.queueTail + this.queueSize - i;
      if (k > 0)
      {
        System.arraycopy(this.queueInts, 0, arrayOfInt, 0, k);
        System.arraycopy(this.queueStrings, 0, arrayOfString, 0, k);
      }
      k = i - this.queueTail;
      i = j - i;
      System.arraycopy(this.queueInts, this.queueTail, arrayOfInt, this.queueTail + i, k);
      System.arraycopy(this.queueStrings, this.queueTail, arrayOfString, this.queueTail + i, k);
      this.queueInts = arrayOfInt;
      this.queueStrings = arrayOfString;
      if (this.currentBlock >= this.queueTail) {
        this.currentBlock += i;
      }
      this.queueTail += i;
    }
    j = this.queueTail + this.queueSize;
    i = j;
    if (j >= this.queueInts.length) {
      i = j - this.queueInts.length;
    }
    this.queueInts[(i + 0)] = (paramInt2 << 16 | paramInt1);
    if (paramInt2 > 1) {
      this.queueInts[(i + 1)] = indexPosn(this.bufferFillPointer);
    }
    this.queueSize += paramInt2;
    return i;
  }
  
  public int enqueueIndent(char paramChar, int paramInt)
  {
    int i = enqueue(3, 4);
    this.queueInts[(i + 2)] = paramChar;
    this.queueInts[(i + 3)] = paramInt;
    return i;
  }
  
  public void enqueueNewline(int paramInt)
  {
    this.wordEndSeen = false;
    int n = this.pendingBlocksCount;
    int i1 = enqueue(2, 5);
    this.queueInts[(i1 + 4)] = paramInt;
    this.queueInts[(i1 + 2)] = this.pendingBlocksCount;
    this.queueInts[(i1 + 3)] = 0;
    int i = this.queueTail;
    int j = this.queueSize;
    int k;
    if (j > 0)
    {
      k = i;
      if (i == this.queueInts.length) {
        k = 0;
      }
      if (k != i1) {}
    }
    else
    {
      if ((paramInt != 76) && (paramInt != 82)) {
        break label208;
      }
    }
    label208:
    for (boolean bool = true;; bool = false)
    {
      maybeOutput(bool, false);
      return;
      i = getQueueType(k);
      if (((i == 2) || (i == 4)) && (this.queueInts[(k + 3)] == 0) && (n <= this.queueInts[(k + 2)]))
      {
        int m = i1 - k;
        i = m;
        if (m < 0) {
          i = m + this.queueInts.length;
        }
        this.queueInts[(k + 3)] = i;
      }
      i = getQueueSize(k);
      j -= i;
      i = k + i;
      break;
    }
  }
  
  int enqueueTab(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = enqueue(6, 5);
    this.queueInts[(i + 2)] = paramInt1;
    this.queueInts[(i + 3)] = paramInt2;
    this.queueInts[(i + 4)] = paramInt3;
    return i;
  }
  
  int ensureSpaceInBuffer(int paramInt)
  {
    char[] arrayOfChar1 = this.buffer;
    int j = arrayOfChar1.length;
    int i = this.bufferFillPointer;
    int k = j - i;
    if (k > 0) {
      return k;
    }
    if ((this.prettyPrintingMode > 0) && (i > this.lineLength))
    {
      if (!maybeOutput(false, false)) {
        outputPartialLine();
      }
      return ensureSpaceInBuffer(paramInt);
    }
    j = enoughSpace(j, paramInt);
    char[] arrayOfChar2 = new char[j];
    this.buffer = arrayOfChar2;
    paramInt = i;
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      arrayOfChar2[paramInt] = arrayOfChar1[paramInt];
    }
    return j - i;
  }
  
  void expandTabs(int paramInt)
  {
    int j = 0;
    int i = 0;
    int i1 = this.bufferStartColumn;
    int n = getSectionColumn();
    int k = this.queueTail;
    int m = this.queueSize;
    int i6 = this.pendingBlocksCount * 6;
    int i2;
    char[] arrayOfChar;
    Object localObject;
    if (m > 0)
    {
      i2 = k;
      if (k == this.queueInts.length) {
        i2 = 0;
      }
      if (i2 != paramInt) {}
    }
    else
    {
      if (j <= 0) {
        return;
      }
      k = this.bufferFillPointer;
      m = k + i;
      arrayOfChar = this.buffer;
      localObject = arrayOfChar;
      n = arrayOfChar.length;
      paramInt = k;
      if (m > n)
      {
        localObject = new char[enoughSpace(k, i)];
        this.buffer = ((char[])localObject);
      }
      this.bufferFillPointer = m;
      this.bufferOffset -= i;
    }
    for (;;)
    {
      j -= 1;
      if (j < 0) {
        break label505;
      }
      k = this.blocks[(j * 2 + i6)];
      m = this.blocks[(j * 2 + i6 + 1)];
      n = k + i;
      System.arraycopy(arrayOfChar, k, localObject, n, paramInt - k);
      paramInt = n - m;
      while (paramInt < n)
      {
        localObject[paramInt] = 32;
        paramInt += 1;
        continue;
        int i5;
        int i4;
        int i3;
        if (getQueueType(i2) == 6)
        {
          int i8 = posnIndex(this.queueInts[(i2 + 1)]);
          int i7 = computeTabSize(i2, n, i1 + i8);
          i5 = i;
          i4 = i1;
          k = j;
          i3 = n;
          if (i7 != 0)
          {
            if (j * 2 + i6 + 1 >= this.blocks.length)
            {
              localObject = new int[this.blocks.length * 2];
              System.arraycopy(this.blocks, 0, localObject, 0, this.blocks.length);
              this.blocks = ((int[])localObject);
            }
            this.blocks[(j * 2 + i6)] = i8;
            this.blocks[(j * 2 + i6 + 1)] = i7;
            k = j + 1;
            i5 = i + i7;
            i4 = i1 + i7;
            i3 = n;
          }
        }
        for (;;)
        {
          i = getQueueSize(i2);
          m -= i;
          n = i2 + i;
          i = i5;
          i1 = i4;
          j = k;
          k = n;
          n = i3;
          break;
          if (i2 != 2)
          {
            i5 = i;
            i4 = i1;
            k = j;
            i3 = n;
            if (i2 != 4) {}
          }
          else
          {
            i3 = i1 + posnIndex(this.queueInts[(i2 + 1)]);
            i5 = i;
            i4 = i1;
            k = j;
          }
        }
      }
      i -= m;
      paramInt = k;
    }
    label505:
    if (localObject != arrayOfChar) {
      System.arraycopy(arrayOfChar, 0, localObject, 0, paramInt);
    }
  }
  
  int fitsOnLine(int paramInt, boolean paramBoolean)
  {
    int k = -1;
    int j = this.lineLength;
    int i = j;
    if (!printReadably())
    {
      i = j;
      if (getMaxLines() == this.lineNumber) {
        i = j - 3 - getSuffixLength();
      }
    }
    if (paramInt >= 0)
    {
      j = k;
      if (posnColumn(this.queueInts[(paramInt + 1)]) <= i) {
        j = 1;
      }
    }
    do
    {
      do
      {
        return j;
        j = k;
      } while (paramBoolean);
      j = k;
    } while (indexColumn(this.bufferFillPointer) > i);
    return 0;
  }
  
  public void flush()
  {
    if (this.out == null) {
      return;
    }
    try
    {
      forcePrettyOutput();
      this.out.flush();
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException.toString());
    }
  }
  
  public void forcePrettyOutput()
    throws IOException
  {
    maybeOutput(false, true);
    if (this.bufferFillPointer > 0) {
      outputPartialLine();
    }
    expandTabs(-1);
    this.bufferStartColumn = getColumnNumber();
    this.out.write(this.buffer, 0, this.bufferFillPointer);
    this.bufferFillPointer = 0;
    this.queueSize = 0;
  }
  
  public int getColumnNumber()
  {
    int i = this.bufferFillPointer;
    int j;
    int k;
    do
    {
      j = i - 1;
      if (j < 0) {
        return this.bufferStartColumn + this.bufferFillPointer;
      }
      k = this.buffer[j];
      if (k == 10) {
        break;
      }
      i = j;
    } while (k != 13);
    return this.bufferFillPointer - (j + 1);
  }
  
  int getMaxLines()
  {
    return -1;
  }
  
  protected int getMiserWidth()
  {
    return this.miserWidth;
  }
  
  public int getPrettyPrintingMode()
  {
    return this.prettyPrintingMode;
  }
  
  int indexColumn(int paramInt)
  {
    int m = this.bufferStartColumn;
    int k = getSectionColumn();
    int i2 = indexPosn(paramInt);
    int i = this.queueTail;
    int j = this.queueSize;
    int n;
    int i3;
    int i1;
    if (j > 0)
    {
      n = i;
      if (i >= this.queueInts.length) {
        n = 0;
      }
      i3 = getQueueType(n);
      i = m;
      i1 = k;
      if (i3 == 0) {
        break label120;
      }
      i = this.queueInts[(n + 1)];
      if (i < i2) {}
    }
    else
    {
      return m + paramInt;
    }
    if (i3 == 6)
    {
      i = m + computeTabSize(n, k, posnIndex(i) + m);
      i1 = k;
    }
    for (;;)
    {
      label120:
      k = getQueueSize(n);
      j -= k;
      k = n + k;
      m = i;
      i = k;
      k = i1;
      break;
      if (i3 != 2)
      {
        i = m;
        i1 = k;
        if (i3 != 4) {}
      }
      else
      {
        i1 = m + posnIndex(this.queueInts[(n + 1)]);
        i = m;
      }
    }
  }
  
  boolean isMisering()
  {
    int i = getMiserWidth();
    return (i > 0) && (this.lineLength - getStartColumn() <= i);
  }
  
  public boolean isPrettyPrinting()
  {
    return this.prettyPrintingMode > 0;
  }
  
  public void lineAbbreviationHappened() {}
  
  boolean maybeOutput(boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool2 = false;
    if (this.queueSize > 0)
    {
      if (this.queueTail >= this.queueInts.length) {
        this.queueTail = 0;
      }
      int j = this.queueTail;
      boolean bool3;
      int i;
      switch (getQueueType(j))
      {
      default: 
        bool3 = bool2;
        i = j;
      }
      for (;;)
      {
        j = getQueueSize(this.queueTail);
        this.queueSize -= j;
        this.queueTail = (i + j);
        bool2 = bool3;
        break;
        int k = -1;
        boolean bool1;
        switch (this.queueInts[(j + 4)])
        {
        default: 
          bool1 = true;
        }
        for (;;)
        {
          i = j;
          bool3 = bool2;
          if (!bool1) {
            break;
          }
          bool3 = true;
          if ((!paramBoolean2) || (k != 0)) {
            break label334;
          }
          try
          {
            outputPartialLine();
            i = j;
          }
          catch (IOException localIOException)
          {
            throw new RuntimeException(localIOException);
          }
          bool1 = isMisering();
          continue;
          if ((isMisering()) || (this.lineNumber > getSectionStartLine()))
          {
            bool1 = true;
          }
          else
          {
            i = this.queueInts[(j + 3)];
            if (i == 0) {
              i = -1;
            }
            for (;;)
            {
              k = fitsOnLine(i, paramBoolean1);
              if (k <= 0) {
                break label319;
              }
              bool1 = false;
              break;
              k = i + j;
              i = k;
              if (k >= this.queueInts.length) {
                i = k - this.queueInts.length;
              }
            }
            label319:
            if ((k >= 0) && (!paramBoolean2)) {
              break label661;
            }
            bool1 = true;
          }
        }
        label334:
        outputLine(j);
        i = j;
        continue;
        i = j;
        bool3 = bool2;
        if (!isMisering())
        {
          i = this.queueInts[(j + 2)];
          k = this.queueInts[(j + 3)];
          if (i == 66) {}
          for (i = k + getStartColumn();; i = k + posnColumn(this.queueInts[(j + 1)]))
          {
            setIndentation(i);
            i = j;
            bool3 = bool2;
            break;
          }
          i = this.queueInts[(j + 3)];
          if (i > 0)
          {
            i = (i + j) % this.queueInts.length;
            label467:
            i = fitsOnLine(i, paramBoolean1);
            if (i <= 0) {
              break label571;
            }
            k = this.queueInts[(j + 4)];
            i = (k + j) % this.queueInts.length;
            expandTabs(i);
            this.queueTail = i;
            this.queueSize -= k;
          }
          for (;;)
          {
            k = i;
            i = k;
            bool3 = bool2;
            if (this.currentBlock != j) {
              break;
            }
            this.currentBlock = -1;
            i = k;
            bool3 = bool2;
            break;
            i = -1;
            break label467;
            label571:
            if ((i >= 0) && (!paramBoolean2)) {
              break label661;
            }
            String str1 = this.queueStrings[(j + 5)];
            String str2 = this.queueStrings[(j + 6)];
            reallyStartLogicalBlock(posnColumn(this.queueInts[(j + 1)]), str1, str2);
            i = j;
          }
          reallyEndLogicalBlock();
          i = j;
          bool3 = bool2;
          continue;
          expandTabs(j);
          i = j;
          bool3 = bool2;
        }
      }
    }
    label661:
    return bool2;
  }
  
  void outputLine(int paramInt)
    throws IOException
  {
    char[] arrayOfChar2 = this.buffer;
    int i;
    int j;
    label43:
    int k;
    char[] arrayOfChar1;
    int m;
    if (this.queueInts[(paramInt + 4)] == 76)
    {
      i = 1;
      j = posnIndex(this.queueInts[(paramInt + 1)]);
      if (i == 0) {
        break label287;
      }
      paramInt = j;
      this.out.write(arrayOfChar2, 0, paramInt);
      k = this.lineNumber + 1;
      if (!printReadably())
      {
        paramInt = getMaxLines();
        if ((paramInt > 0) && (k >= paramInt))
        {
          this.out.write(" ..");
          paramInt = getSuffixLength();
          if (paramInt != 0)
          {
            arrayOfChar1 = this.suffix;
            m = arrayOfChar1.length;
            this.out.write(arrayOfChar1, m - paramInt, paramInt);
          }
          lineAbbreviationHappened();
        }
      }
      this.lineNumber = k;
      this.out.write(10);
      this.bufferStartColumn = 0;
      m = this.bufferFillPointer;
      if (i == 0) {
        break label325;
      }
    }
    label287:
    label325:
    for (paramInt = getPerLinePrefixEnd();; paramInt = getPrefixLength())
    {
      int n = j - paramInt;
      int i1 = m - n;
      arrayOfChar1 = arrayOfChar2;
      int i2 = arrayOfChar2.length;
      if (i1 > i2)
      {
        arrayOfChar1 = new char[enoughSpace(i2, i1 - i2)];
        this.buffer = arrayOfChar1;
      }
      System.arraycopy(arrayOfChar2, j, arrayOfChar1, paramInt, m - j);
      System.arraycopy(this.prefix, 0, arrayOfChar1, 0, paramInt);
      this.bufferFillPointer = i1;
      this.bufferOffset += n;
      if (i == 0)
      {
        this.blocks[(this.blockDepth - 2)] = paramInt;
        this.blocks[(this.blockDepth - 6)] = k;
      }
      return;
      i = 0;
      break;
      paramInt = j;
      do
      {
        k = paramInt - 1;
        if (k < 0)
        {
          paramInt = 0;
          break;
        }
        paramInt = k;
      } while (arrayOfChar2[k] == ' ');
      paramInt = k + 1;
      break label43;
    }
  }
  
  void outputPartialLine()
  {
    int i = this.queueTail;
    while ((this.queueSize > 0) && (getQueueType(i) == 0))
    {
      j = getQueueSize(i);
      this.queueSize -= j;
      j = i + j;
      i = j;
      if (j == this.queueInts.length) {
        i = 0;
      }
      this.queueTail = i;
    }
    int j = this.bufferFillPointer;
    if (this.queueSize > 0) {}
    for (i = posnIndex(this.queueInts[(i + 1)]);; i = j)
    {
      j -= i;
      if (i > 0) {
        break;
      }
      throw new Error("outputPartialLine called when nothing can be output.");
    }
    try
    {
      this.out.write(this.buffer, 0, i);
      this.bufferFillPointer = i;
      this.bufferStartColumn = getColumnNumber();
      System.arraycopy(this.buffer, i, this.buffer, 0, j);
      this.bufferFillPointer = j;
      this.bufferOffset += i;
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  boolean printReadably()
  {
    return true;
  }
  
  void reallyEndLogicalBlock()
  {
    int i = getPrefixLength();
    this.blockDepth -= 6;
    int j = getPrefixLength();
    if (j > i) {
      while (i < j)
      {
        this.prefix[i] = ' ';
        i += 1;
      }
    }
  }
  
  void reallyStartLogicalBlock(int paramInt, String paramString1, String paramString2)
  {
    int i = getPerLinePrefixEnd();
    int k = getPrefixLength();
    int j = getSuffixLength();
    pushLogicalBlock(paramInt, i, k, j, this.lineNumber);
    setIndentation(paramInt);
    if (paramString1 != null)
    {
      this.blocks[(this.blockDepth - 3)] = paramInt;
      i = paramString1.length();
      paramString1.getChars(0, i, this.suffix, paramInt - i);
    }
    if (paramString2 != null)
    {
      paramString1 = this.suffix;
      i = paramString1.length;
      k = paramString2.length();
      int m = j + k;
      paramInt = i;
      if (m > i)
      {
        paramInt = enoughSpace(i, k);
        this.suffix = new char[paramInt];
        System.arraycopy(paramString1, i - j, this.suffix, paramInt - j, j);
      }
      paramString2.getChars(0, k, paramString1, paramInt - m);
      this.blocks[(this.blockDepth - 5)] = m;
    }
  }
  
  public void setColumnNumber(int paramInt)
  {
    this.bufferStartColumn += paramInt - getColumnNumber();
  }
  
  public void setIndentation(int paramInt)
  {
    char[] arrayOfChar = this.prefix;
    int m = arrayOfChar.length;
    int j = getPrefixLength();
    int k = getPerLinePrefixEnd();
    int i = paramInt;
    if (k > paramInt) {
      i = k;
    }
    if (i > m)
    {
      arrayOfChar = new char[enoughSpace(m, i - m)];
      System.arraycopy(this.prefix, 0, arrayOfChar, 0, j);
      this.prefix = arrayOfChar;
    }
    if (i > j)
    {
      paramInt = j;
      while (paramInt < i)
      {
        arrayOfChar[paramInt] = ' ';
        paramInt += 1;
      }
    }
    this.blocks[(this.blockDepth - 4)] = i;
  }
  
  public void setPrettyPrinting(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 0;; i = 1)
    {
      this.prettyPrintingMode = i;
      return;
    }
  }
  
  public void setPrettyPrintingMode(int paramInt)
  {
    this.prettyPrintingMode = paramInt;
  }
  
  public void startLogicalBlock(String paramString1, boolean paramBoolean, String paramString2)
  {
    if ((this.queueSize == 0) && (this.bufferFillPointer == 0))
    {
      localObject = lineLengthLoc.get(null);
      if (localObject != null) {
        break label94;
      }
      this.lineLength = 80;
      localObject = miserWidthLoc.get(null);
      if ((localObject != null) && (localObject != Boolean.FALSE) && (localObject != LList.Empty)) {
        break label109;
      }
    }
    label94:
    label109:
    for (this.miserWidth = -1;; this.miserWidth = Integer.parseInt(localObject.toString()))
    {
      indentLoc.get(null);
      if (paramString1 != null) {
        write(paramString1);
      }
      if (this.prettyPrintingMode != 0) {
        break label124;
      }
      return;
      this.lineLength = Integer.parseInt(localObject.toString());
      break;
    }
    label124:
    int k = enqueue(4, 7);
    this.queueInts[(k + 2)] = this.pendingBlocksCount;
    Object localObject = this.queueStrings;
    int i;
    if (paramBoolean)
    {
      localObject[(k + 5)] = paramString1;
      this.queueStrings[(k + 6)] = paramString2;
      this.pendingBlocksCount += 1;
      i = this.currentBlock;
      if (i >= 0) {
        break label232;
      }
      i = 0;
    }
    for (;;)
    {
      this.queueInts[(k + 4)] = i;
      this.queueInts[(k + 3)] = 0;
      this.currentBlock = k;
      return;
      paramString1 = null;
      break;
      label232:
      int j = i - k;
      i = j;
      if (j > 0) {
        i = j - this.queueInts.length;
      }
    }
  }
  
  public void write(int paramInt)
  {
    this.wordEndSeen = false;
    if ((paramInt == 10) && (this.prettyPrintingMode > 0)) {
      enqueueNewline(76);
    }
    do
    {
      return;
      ensureSpaceInBuffer(1);
      int i = this.bufferFillPointer;
      this.buffer[i] = ((char)paramInt);
      this.bufferFillPointer = (i + 1);
    } while ((paramInt != 32) || (this.prettyPrintingMode <= 1) || (this.currentBlock >= 0));
    enqueueNewline(83);
  }
  
  public void write(String paramString)
  {
    write(paramString, 0, paramString.length());
  }
  
  public void write(String paramString, int paramInt1, int paramInt2)
  {
    this.wordEndSeen = false;
    int j = paramInt2;
    paramInt2 = paramInt1;
    while (j > 0)
    {
      int k = j;
      int m = ensureSpaceInBuffer(j);
      paramInt1 = k;
      if (k > m) {
        paramInt1 = m;
      }
      m = this.bufferFillPointer;
      k = j - paramInt1;
      j = m;
      m = paramInt1 - 1;
      if (m >= 0)
      {
        int i = paramString.charAt(paramInt2);
        if ((i == 10) && (this.prettyPrintingMode > 0))
        {
          this.bufferFillPointer = j;
          enqueueNewline(76);
          paramInt1 = this.bufferFillPointer;
        }
        for (;;)
        {
          j = paramInt1;
          paramInt2 += 1;
          paramInt1 = m;
          break;
          char[] arrayOfChar = this.buffer;
          int n = j + 1;
          arrayOfChar[j] = i;
          paramInt1 = n;
          if (i == 32)
          {
            paramInt1 = n;
            if (this.prettyPrintingMode > 1)
            {
              paramInt1 = n;
              if (this.currentBlock < 0)
              {
                this.bufferFillPointer = n;
                enqueueNewline(83);
                paramInt1 = this.bufferFillPointer;
              }
            }
          }
        }
      }
      this.bufferFillPointer = j;
      j = k;
    }
  }
  
  public void write(char[] paramArrayOfChar)
  {
    write(paramArrayOfChar, 0, paramArrayOfChar.length);
  }
  
  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this.wordEndSeen = false;
    int m = paramInt1 + paramInt2;
    while (paramInt2 > 0)
    {
      int k = paramInt1;
      int j;
      int i;
      for (;;)
      {
        j = paramInt1;
        i = paramInt2;
        if (k >= m) {
          break label108;
        }
        if (this.prettyPrintingMode > 0)
        {
          i = paramArrayOfChar[k];
          if ((i == 10) || ((i == 32) && (this.currentBlock < 0)))
          {
            write(paramArrayOfChar, paramInt1, k - paramInt1);
            write(i);
            paramInt1 = k + 1;
            paramInt2 = m - paramInt1;
            break;
          }
        }
        k += 1;
      }
      label108:
      do
      {
        i = paramInt2;
        j = paramInt1;
        paramInt2 = ensureSpaceInBuffer(i);
        if (paramInt2 < i) {}
        int n;
        for (;;)
        {
          k = this.bufferFillPointer;
          n = k + paramInt2;
          paramInt1 = j;
          while (k < n)
          {
            this.buffer[k] = paramArrayOfChar[paramInt1];
            k += 1;
            paramInt1 += 1;
          }
          paramInt2 = i;
        }
        this.bufferFillPointer = n;
        paramInt2 = i - paramInt2;
      } while (paramInt2 != 0);
    }
  }
  
  public final void writeBreak(int paramInt)
  {
    if (this.prettyPrintingMode > 0) {
      enqueueNewline(paramInt);
    }
  }
  
  public void writeWordEnd()
  {
    this.wordEndSeen = true;
  }
  
  public void writeWordStart()
  {
    if (this.wordEndSeen) {
      write(32);
    }
    this.wordEndSeen = false;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\text\PrettyWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */