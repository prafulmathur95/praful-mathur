package gnu.kawa.lispexpr;

import gnu.expr.Keyword;
import gnu.expr.QuoteExp;
import gnu.expr.Special;
import gnu.kawa.util.GeneralHashTable;
import gnu.lists.Convert;
import gnu.lists.F32Vector;
import gnu.lists.F64Vector;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.S16Vector;
import gnu.lists.S32Vector;
import gnu.lists.S64Vector;
import gnu.lists.S8Vector;
import gnu.lists.Sequence;
import gnu.lists.SimpleVector;
import gnu.lists.U16Vector;
import gnu.lists.U32Vector;
import gnu.lists.U64Vector;
import gnu.lists.U8Vector;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.math.Complex;
import gnu.math.DComplex;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.RatNum;
import gnu.math.RealNum;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.math.BigDecimal;

public class LispReader
  extends Lexer
{
  static final int SCM_COMPLEX = 1;
  public static final int SCM_NUMBERS = 1;
  public static final char TOKEN_ESCAPE_CHAR = '￿';
  protected boolean seenEscapes;
  GeneralHashTable<Integer, Object> sharedStructureTable;
  
  public LispReader(LineBufferedReader paramLineBufferedReader)
  {
    super(paramLineBufferedReader);
  }
  
  public LispReader(LineBufferedReader paramLineBufferedReader, SourceMessages paramSourceMessages)
  {
    super(paramLineBufferedReader, paramSourceMessages);
  }
  
  static char getReadCase()
  {
    try
    {
      char c = Environment.getCurrent().get("symbol-read-case", "P").toString().charAt(0);
      if (c == 'P') {}
      do
      {
        return c;
        if (c == 'u') {
          return 'U';
        }
        if ((c == 'd') || (c == 'l') || (c == 'L')) {
          return 'D';
        }
      } while (c != 'i');
      return 'I';
    }
    catch (Exception localException) {}
    return 'P';
  }
  
  private boolean isPotentialNumber(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    boolean bool = true;
    int j = 0;
    int i = paramInt1;
    if (i < paramInt2)
    {
      char c = paramArrayOfChar[i];
      if (Character.isDigit(c)) {
        j += 1;
      }
      label68:
      label114:
      do
      {
        do
        {
          do
          {
            i += 1;
            break;
            if ((c != '-') && (c != '+')) {
              break label68;
            }
          } while (i + 1 != paramInt2);
          return false;
          if (c == '#') {
            return true;
          }
          if ((!Character.isLetter(c)) && (c != '/') && (c != '_') && (c != '^')) {
            break label114;
          }
        } while (i != paramInt1);
        return false;
      } while (c == '.');
      return false;
    }
    if (j > 0) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public static Object parseNumber(CharSequence paramCharSequence, int paramInt)
  {
    if ((paramCharSequence instanceof FString)) {}
    for (char[] arrayOfChar = ((FString)paramCharSequence).data;; arrayOfChar = paramCharSequence.toString().toCharArray()) {
      return parseNumber(arrayOfChar, 0, paramCharSequence.length(), '\000', paramInt, 1);
    }
  }
  
  public static Object parseNumber(char[] paramArrayOfChar, int paramInt1, int paramInt2, char paramChar, int paramInt3, int paramInt4)
  {
    int i3 = paramInt1 + paramInt2;
    if (paramInt1 >= i3)
    {
      paramArrayOfChar = "no digits";
      return paramArrayOfChar;
    }
    int j = paramInt1 + 1;
    char c1 = paramArrayOfChar[paramInt1];
    int i = paramInt3;
    int k;
    while (c1 == '#')
    {
      if (j >= i3) {
        return "no digits";
      }
      paramInt3 = j + 1;
      c1 = paramArrayOfChar[j];
      switch (c1)
      {
      default: 
        j = 0;
        k = Character.digit(c1, 10);
        if (k < 0)
        {
          if ((c1 != 'R') && (c1 != 'r')) {
            break label353;
          }
          if (i == 0) {
            break label330;
          }
          return "duplicate radix specifier";
        }
        break;
      case 'B': 
      case 'b': 
        if (i != 0) {
          return "duplicate radix specifier";
        }
        i = 2;
      }
      for (;;)
      {
        if (paramInt3 >= i3)
        {
          return "no digits";
          if (i != 0) {
            return "duplicate radix specifier";
          }
          i = 8;
          continue;
          if (i != 0) {
            return "duplicate radix specifier";
          }
          i = 10;
          continue;
          if (i != 0) {
            return "duplicate radix specifier";
          }
          i = 16;
          continue;
          if (paramChar != 0)
          {
            if (paramChar == ' ') {
              return "non-prefix exactness specifier";
            }
            return "duplicate exactness specifier";
          }
          paramChar = c1;
          continue;
          j = j * 10 + k;
          if (paramInt3 >= i3) {
            return "missing letter after '#'";
          }
          c1 = paramArrayOfChar[paramInt3];
          paramInt3 += 1;
          break;
          label330:
          if ((j < 2) || (j > 35)) {
            return "invalid radix specifier";
          }
          i = j;
          continue;
          label353:
          return "unknown modifier '#" + c1 + '\'';
        }
      }
      c1 = paramArrayOfChar[paramInt3];
      j = paramInt3 + 1;
    }
    char c2 = paramChar;
    if (paramChar == 0) {
      c2 = ' ';
    }
    paramInt3 = i;
    label428:
    boolean bool2;
    label438:
    int m;
    if (i == 0)
    {
      paramInt3 = paramInt2 - 1;
      if (paramInt3 < 0) {
        paramInt3 = 10;
      }
    }
    else
    {
      if (c1 != '-') {
        break label491;
      }
      bool2 = true;
      if ((c1 != '-') && (c1 != '+')) {
        break label497;
      }
      m = 1;
    }
    for (;;)
    {
      if (m != 0)
      {
        if (j >= i3)
        {
          return "no digits following sign";
          paramInt2 = paramInt3;
          if (paramArrayOfChar[(paramInt1 + paramInt3)] != '.') {
            break;
          }
          paramInt3 = 10;
          break label428;
          label491:
          bool2 = false;
          break label438;
          label497:
          m = 0;
          continue;
        }
        paramInt2 = j + 1;
        c1 = paramArrayOfChar[j];
      }
    }
    for (;;)
    {
      double d1;
      if (((c1 == 'i') || (c1 == 'I')) && (paramInt2 == i3) && (paramInt1 == paramInt2 - 2) && ((paramInt4 & 0x1) != 0))
      {
        paramInt1 = paramArrayOfChar[paramInt1];
        if ((paramInt1 != 43) && (paramInt1 != 45)) {
          return "no digits";
        }
        if ((c2 == 'i') || (c2 == 'I'))
        {
          if (bool2) {}
          for (d1 = -1.0D;; d1 = 1.0D) {
            return new DComplex(0.0D, d1);
          }
        }
        if (bool2) {}
        for (paramArrayOfChar = Complex.imMinusOne();; paramArrayOfChar = Complex.imOne()) {
          return paramArrayOfChar;
        }
      }
      int i5 = 0;
      int n = -1;
      i = -1;
      k = -1;
      int i4 = 0;
      Object localObject1 = null;
      long l1 = 0L;
      paramInt1 = paramInt2;
      boolean bool1 = bool2;
      paramInt2 = i;
      label656:
      j = Character.digit(c1, paramInt3);
      int i6;
      Object localObject2;
      int i7;
      long l2;
      boolean bool3;
      if (j >= 0)
      {
        if ((i5 != 0) && (k < 0)) {
          return "digit after '#' in number";
        }
        i = paramInt2;
        if (paramInt2 < 0) {
          i = paramInt1 - 1;
        }
        l1 = paramInt3 * l1 + j;
        j = k;
        paramInt2 = i;
        if (paramInt1 != i3) {
          break label1337;
        }
        i6 = i4;
        localObject2 = localObject1;
        i7 = i5;
        l2 = l1;
        bool3 = bool1;
        i = paramInt2;
        label743:
        paramInt2 = 0;
        k = 0;
        if (i >= 0) {
          break label2260;
        }
        paramInt2 = k;
        if (m != 0)
        {
          paramInt2 = k;
          if (paramInt1 + 4 < i3)
          {
            paramInt2 = k;
            if (paramArrayOfChar[(paramInt1 + 3)] == '.')
            {
              paramInt2 = k;
              if (paramArrayOfChar[(paramInt1 + 4)] == '0')
              {
                if ((paramArrayOfChar[paramInt1] != 'i') || (paramArrayOfChar[(paramInt1 + 1)] != 'n') || (paramArrayOfChar[(paramInt1 + 2)] != 'f')) {
                  break label1353;
                }
                paramInt2 = 105;
              }
            }
          }
        }
      }
      int i1;
      for (;;)
      {
        if (paramInt2 != 0) {
          break label1396;
        }
        return "no digits";
        switch (c1)
        {
        default: 
          paramInt1 -= 1;
          i = paramInt2;
          bool3 = bool1;
          l2 = l1;
          j = k;
          i7 = i5;
          localObject2 = localObject1;
          i6 = i4;
          break;
        case '.': 
          if (k >= 0) {
            return "duplicate '.' in number";
          }
          if (paramInt3 != 10) {
            return "'.' in non-decimal number";
          }
          j = paramInt1 - 1;
          break;
        case 'D': 
        case 'E': 
        case 'F': 
        case 'L': 
        case 'S': 
        case 'd': 
        case 'e': 
        case 'f': 
        case 'l': 
        case 's': 
          if ((paramInt1 == i3) || (paramInt3 != 10))
          {
            paramInt1 -= 1;
            i = paramInt2;
            bool3 = bool1;
            l2 = l1;
            j = k;
            i7 = i5;
            localObject2 = localObject1;
            i6 = i4;
            break label743;
          }
          paramChar = paramArrayOfChar[paramInt1];
          if ((paramChar == '+') || (paramChar == '-'))
          {
            j = paramInt1 + 1;
            if (j < i3)
            {
              i = j;
              if (Character.digit(paramArrayOfChar[j], 10) >= 0) {}
            }
            else
            {
              return "missing exponent digits";
            }
          }
          else
          {
            i = paramInt1;
            if (Character.digit(paramChar, 10) < 0)
            {
              paramInt1 -= 1;
              i = paramInt2;
              bool3 = bool1;
              l2 = l1;
              j = k;
              i7 = i5;
              localObject2 = localObject1;
              i6 = i4;
              break label743;
            }
          }
          if (-1 >= 0) {
            return "duplicate exponent";
          }
          if (paramInt3 != 10) {
            return "exponent in non-decimal number";
          }
          if (paramInt2 < 0) {
            return "mantissa with no digits";
          }
          int i2 = paramInt1 - 1;
          do
          {
            i1 = i + 1;
            i = paramInt2;
            bool3 = bool1;
            l2 = l1;
            paramInt1 = i1;
            j = k;
            n = i2;
            i7 = i5;
            localObject2 = localObject1;
            i6 = i4;
            if (i1 >= i3) {
              break;
            }
            i = i1;
          } while (Character.digit(paramArrayOfChar[i1], 10) >= 0);
          i = paramInt2;
          bool3 = bool1;
          l2 = l1;
          paramInt1 = i1;
          j = k;
          n = i2;
          i7 = i5;
          localObject2 = localObject1;
          i6 = i4;
          break;
        case '/': 
          if (localObject1 != null) {
            return "multiple fraction symbol '/'";
          }
          if (paramInt2 < 0) {
            return "no digits before fraction symbol '/'";
          }
          if ((-1 >= 0) || (k >= 0)) {
            return "fraction symbol '/' following exponent or '.'";
          }
          localObject1 = valueOf(paramArrayOfChar, paramInt2, paramInt1 - paramInt2, paramInt3, bool1, l1);
          paramInt2 = -1;
          l1 = 0L;
          bool1 = false;
          i5 = 0;
          i4 = 0;
          j = k;
          break;
          label1337:
          c1 = paramArrayOfChar[paramInt1];
          paramInt1 += 1;
          k = j;
          break label656;
          label1353:
          paramInt2 = k;
          if (paramArrayOfChar[paramInt1] == 'n')
          {
            paramInt2 = k;
            if (paramArrayOfChar[(paramInt1 + 1)] == 'a')
            {
              paramInt2 = k;
              if (paramArrayOfChar[(paramInt1 + 2)] == 'n') {
                paramInt2 = 110;
              }
            }
          }
          break;
        }
      }
      label1396:
      label1439:
      label1460:
      label1580:
      label1588:
      label1782:
      label1856:
      label1923:
      label2162:
      label2254:
      label2260:
      for (k = paramInt1 + 5;; k = paramInt1)
      {
        double d2;
        if (((i7 != 0) || (i6 == 0)) || ((c2 == 'i') || (c2 == 'I') || ((c2 == ' ') && (i7 != 0))))
        {
          paramInt1 = 1;
          m = 0;
          i1 = 0;
          if (paramInt2 == 0) {
            break label1588;
          }
          if (paramInt2 != 105) {
            break label1580;
          }
          d1 = Double.POSITIVE_INFINITY;
          d2 = d1;
          if (bool3) {
            d2 = -d1;
          }
          localObject1 = new DFloNum(d2);
          paramInt1 = i1;
        }
        for (;;)
        {
          if (c2 != 'e')
          {
            localObject2 = localObject1;
            if (c2 != 'E') {}
          }
          else
          {
            localObject2 = ((RealNum)localObject1).toExact();
          }
          if (k < i3)
          {
            paramInt1 = k + 1;
            paramChar = paramArrayOfChar[k];
            if (paramChar == '@')
            {
              localObject1 = parseNumber(paramArrayOfChar, paramInt1, i3 - paramInt1, c2, 10, paramInt4);
              paramArrayOfChar = (char[])localObject1;
              if ((localObject1 instanceof String)) {
                break;
              }
              if (!(localObject1 instanceof RealNum))
              {
                return "invalid complex polar constant";
                paramInt1 = 0;
                break label1439;
                d1 = NaN.0D;
                break label1460;
                if ((n >= 0) || (j >= 0))
                {
                  paramInt2 = i;
                  if (i > j)
                  {
                    paramInt2 = i;
                    if (j >= 0) {
                      paramInt2 = j;
                    }
                  }
                  if (localObject2 != null) {
                    return "floating-point number after fraction symbol '/'";
                  }
                  localObject2 = new String(paramArrayOfChar, paramInt2, k - paramInt2);
                  paramInt1 = m;
                  localObject1 = localObject2;
                  if (n >= 0)
                  {
                    paramInt3 = Character.toLowerCase(paramArrayOfChar[n]);
                    paramInt1 = paramInt3;
                    localObject1 = localObject2;
                    if (paramInt3 != 101)
                    {
                      paramInt1 = n - paramInt2;
                      localObject1 = ((String)localObject2).substring(0, paramInt1) + 'e' + ((String)localObject2).substring(paramInt1 + 1);
                      paramInt1 = paramInt3;
                    }
                  }
                  d2 = Convert.parseDouble((String)localObject1);
                  d1 = d2;
                  if (bool3) {
                    d1 = -d2;
                  }
                  localObject1 = new DFloNum(d1);
                  continue;
                }
                localObject1 = valueOf(paramArrayOfChar, i, k - i, paramInt3, bool3, l2);
                if (localObject2 == null)
                {
                  if ((paramInt1 == 0) || (!((RealNum)localObject1).isExact())) {
                    break label2254;
                  }
                  if ((!bool2) || (!((RealNum)localObject1).isZero())) {
                    break label1923;
                  }
                }
                for (d1 = 0.0D;; d1 = ((RealNum)localObject1).doubleValue())
                {
                  localObject1 = new DFloNum(d1);
                  paramInt1 = i1;
                  break;
                  if (((IntNum)localObject1).isZero())
                  {
                    bool1 = ((IntNum)localObject2).isZero();
                    if (paramInt1 != 0) {
                      if (bool1) {
                        d1 = NaN.0D;
                      }
                    }
                    for (localObject1 = new DFloNum(d1);; localObject1 = RatNum.make((IntNum)localObject2, (IntNum)localObject1))
                    {
                      break;
                      if (bool2)
                      {
                        d1 = Double.NEGATIVE_INFINITY;
                        break label1856;
                      }
                      d1 = Double.POSITIVE_INFINITY;
                      break label1856;
                      if (bool1) {
                        return "0/0 is undefined";
                      }
                    }
                  }
                  localObject1 = RatNum.make((IntNum)localObject2, (IntNum)localObject1);
                  break label1782;
                }
              }
              paramArrayOfChar = (RealNum)localObject1;
              if ((((RealNum)localObject2).isZero()) && (!paramArrayOfChar.isExact())) {
                return new DFloNum(0.0D);
              }
              return Complex.polar((RealNum)localObject2, paramArrayOfChar);
            }
            if ((paramChar == '-') || (paramChar == '+'))
            {
              paramInt1 -= 1;
              paramArrayOfChar = parseNumber(paramArrayOfChar, paramInt1, i3 - paramInt1, c2, 10, paramInt4);
              if ((paramArrayOfChar instanceof String)) {
                return paramArrayOfChar;
              }
              if (!(paramArrayOfChar instanceof Complex)) {
                return "invalid numeric constant (" + paramArrayOfChar + ")";
              }
              paramArrayOfChar = (Complex)paramArrayOfChar;
              if (!paramArrayOfChar.re().isZero()) {
                return "invalid numeric constant";
              }
              return Complex.make((RealNum)localObject2, paramArrayOfChar.im());
            }
            for (paramInt2 = 0;; paramInt2 = paramInt4)
            {
              if (!Character.isLetter(paramChar))
              {
                paramInt1 -= 1;
                paramInt3 = paramInt2;
                paramInt2 = paramInt1;
              }
              do
              {
                if (paramInt3 != 1) {
                  break label2162;
                }
                paramInt1 = paramArrayOfChar[(paramInt2 - 1)];
                if ((paramInt1 != 105) && (paramInt1 != 73)) {
                  break label2162;
                }
                if (paramInt2 >= i3) {
                  break;
                }
                return "junk after imaginary suffix 'i'";
                paramInt4 = paramInt2 + 1;
                paramInt2 = paramInt1;
                paramInt3 = paramInt4;
              } while (paramInt1 == i3);
              paramChar = paramArrayOfChar[paramInt1];
              paramInt1 += 1;
            }
            return Complex.make(IntNum.zero(), (RealNum)localObject2);
            return "excess junk after number";
          }
          if (((localObject2 instanceof DFloNum)) && (paramInt1 > 0) && (paramInt1 != 101)) {
            d1 = ((RealNum)localObject2).doubleValue();
          }
          switch (paramInt1)
          {
          default: 
            return localObject2;
          case 102: 
          case 115: 
            return Float.valueOf((float)d1);
          case 100: 
            return Double.valueOf(d1);
          }
          return BigDecimal.valueOf(d1);
          paramInt1 = i1;
        }
      }
      paramInt2 = j;
    }
  }
  
  public static Object readCharacter(LispReader paramLispReader)
    throws IOException, SyntaxException
  {
    int i = paramLispReader.read();
    if (i < 0) {
      paramLispReader.eofError("unexpected EOF in character literal");
    }
    int k = paramLispReader.tokenBufferLength;
    paramLispReader.tokenBufferAppend(i);
    paramLispReader.readToken(paramLispReader.read(), 'D', ReadTable.getCurrent());
    char[] arrayOfChar = paramLispReader.tokenBuffer;
    int m = paramLispReader.tokenBufferLength - k;
    if (m == 1) {
      return Char.make(arrayOfChar[k]);
    }
    String str = new String(arrayOfChar, k, m);
    i = Char.nameToChar(str);
    if (i >= 0) {
      return Char.make(i);
    }
    int n = arrayOfChar[k];
    int j;
    int i1;
    if ((n == 120) || (n == 88))
    {
      j = 0;
      i = 1;
      if (i == m) {
        return Char.make(j);
      }
      i1 = Character.digit(arrayOfChar[(k + i)], 16);
      if (i1 >= 0) {}
    }
    else
    {
      label150:
      j = Character.digit(n, 8);
      if (j < 0) {
        break label221;
      }
      i = 1;
    }
    for (;;)
    {
      if (i == m)
      {
        return Char.make(j);
        j = j * 16 + i1;
        if (j > 1114111) {
          break label150;
        }
        i += 1;
        break;
      }
      n = Character.digit(arrayOfChar[(k + i)], 8);
      if (n < 0)
      {
        label221:
        paramLispReader.error("unknown character name: " + str);
        return Char.make(63);
      }
      j = j * 8 + n;
      i += 1;
    }
  }
  
  public static Object readNumberWithRadix(int paramInt1, LispReader paramLispReader, int paramInt2)
    throws IOException, SyntaxException
  {
    paramInt1 = paramLispReader.tokenBufferLength - paramInt1;
    paramLispReader.readToken(paramLispReader.read(), 'P', ReadTable.getCurrent());
    int i = paramLispReader.tokenBufferLength;
    Object localObject1;
    if (paramInt1 == i)
    {
      paramLispReader.error("missing numeric token");
      localObject1 = IntNum.zero();
    }
    Object localObject2;
    do
    {
      return localObject1;
      localObject2 = parseNumber(paramLispReader.tokenBuffer, paramInt1, i - paramInt1, '\000', paramInt2, 0);
      if ((localObject2 instanceof String))
      {
        paramLispReader.error((String)localObject2);
        return IntNum.zero();
      }
      localObject1 = localObject2;
    } while (localObject2 != null);
    paramLispReader.error("invalid numeric constant");
    return IntNum.zero();
  }
  
  public static SimpleVector readSimpleVector(LispReader paramLispReader, char paramChar)
    throws IOException, SyntaxException
  {
    int k;
    for (int i = 0;; i = i * 10 + k)
    {
      int j = paramLispReader.read();
      if (j < 0) {
        paramLispReader.eofError("unexpected EOF reading uniform vector");
      }
      k = Character.digit((char)j, 10);
      if (k < 0)
      {
        if (((i == 8) || (i == 16) || (i == 32) || (i == 64)) && ((paramChar != 'F') || (i >= 32)) && (j == 40)) {
          break;
        }
        paramLispReader.error("invalid uniform vector syntax");
        return null;
      }
    }
    Object localObject = ReaderParens.readList(paramLispReader, 40, -1, 41);
    if (LList.listLength(localObject, false) < 0)
    {
      paramLispReader.error("invalid elements in uniform vector syntax");
      return null;
    }
    paramLispReader = (Sequence)localObject;
    switch (paramChar)
    {
    default: 
      return null;
    case 'F': 
      switch (i)
      {
      }
    case 'S': 
      switch (i)
      {
      }
      break;
    }
    switch (i)
    {
    default: 
      return null;
    case 8: 
      return new U8Vector(paramLispReader);
      return new F32Vector(paramLispReader);
      return new F64Vector(paramLispReader);
      return new S8Vector(paramLispReader);
      return new S16Vector(paramLispReader);
      return new S32Vector(paramLispReader);
      return new S64Vector(paramLispReader);
    case 16: 
      return new U16Vector(paramLispReader);
    case 32: 
      return new U32Vector(paramLispReader);
    }
    return new U64Vector(paramLispReader);
  }
  
  public static Object readSpecial(LispReader paramLispReader)
    throws IOException, SyntaxException
  {
    Values localValues = null;
    int j = paramLispReader.read();
    if (j < 0) {
      paramLispReader.eofError("unexpected EOF in #! special form");
    }
    if ((j == 47) && (paramLispReader.getLineNumber() == 0) && (paramLispReader.getColumnNumber() == 3))
    {
      ReaderIgnoreRestOfLine.getInstance().read(paramLispReader, 35, 1);
      localValues = Values.empty;
    }
    String str;
    do
    {
      return localValues;
      int i = paramLispReader.tokenBufferLength;
      paramLispReader.tokenBufferAppend(j);
      paramLispReader.readToken(paramLispReader.read(), 'D', ReadTable.getCurrent());
      j = paramLispReader.tokenBufferLength;
      str = new String(paramLispReader.tokenBuffer, i, j - i);
      if (str.equals("optional")) {
        return Special.optional;
      }
      if (str.equals("rest")) {
        return Special.rest;
      }
      if (str.equals("key")) {
        return Special.key;
      }
      if (str.equals("eof")) {
        return Special.eof;
      }
      if (str.equals("void")) {
        return QuoteExp.voidExp;
      }
      if (str.equals("default")) {
        return Special.dfault;
      }
      if (str.equals("undefined")) {
        return Special.undefined;
      }
      if (str.equals("abstract")) {
        return Special.abstractSpecial;
      }
    } while (str.equals("null"));
    paramLispReader.error("unknown named constant #!" + str);
    return null;
  }
  
  private static IntNum valueOf(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong)
  {
    if (paramInt2 + paramInt3 <= 28)
    {
      long l = paramLong;
      if (paramBoolean) {
        l = -paramLong;
      }
      return IntNum.make(l);
    }
    return IntNum.valueOf(paramArrayOfChar, paramInt1, paramInt2, paramInt3, paramBoolean);
  }
  
  Object handlePostfix(Object paramObject, ReadTable paramReadTable, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    Object localObject = paramObject;
    if (paramObject == QuoteExp.voidExp) {}
    for (localObject = Values.empty;; localObject = PairWithPosition.make(LispLanguage.lookup_sym, paramObject, this.port.getName(), paramInt1 + 1, paramInt2 + 1))
    {
      int i = this.port.peek();
      if ((i < 0) || (i != paramReadTable.postfixLookupOperator)) {
        return localObject;
      }
      this.port.read();
      if (!validPostfixLookupStart(this.port.peek(), paramReadTable))
      {
        unread();
        return localObject;
      }
      i = this.port.read();
      paramObject = readValues(i, paramReadTable.lookup(i), paramReadTable);
      paramObject = LList.list2(localObject, LList.list2(paramReadTable.makeSymbol("quasiquote"), paramObject));
    }
  }
  
  protected Object makeNil()
  {
    return LList.Empty;
  }
  
  protected Pair makePair(Object paramObject, int paramInt1, int paramInt2)
  {
    return makePair(paramObject, LList.Empty, paramInt1, paramInt2);
  }
  
  protected Pair makePair(Object paramObject1, Object paramObject2, int paramInt1, int paramInt2)
  {
    String str = this.port.getName();
    if ((str != null) && (paramInt1 >= 0)) {
      return PairWithPosition.make(paramObject1, paramObject2, str, paramInt1 + 1, paramInt2 + 1);
    }
    return Pair.make(paramObject1, paramObject2);
  }
  
  protected Object readAndHandleToken(int paramInt1, int paramInt2, ReadTable paramReadTable)
    throws IOException, SyntaxException
  {
    readToken(paramInt1, getReadCase(), paramReadTable);
    int i9 = this.tokenBufferLength;
    Object localObject;
    if (!this.seenEscapes)
    {
      localObject = parseNumber(this.tokenBuffer, paramInt2, i9 - paramInt2, '\000', 0, 1);
      if ((localObject != null) && (!(localObject instanceof String))) {
        return localObject;
      }
    }
    paramInt1 = getReadCase();
    int m = paramInt1;
    int n;
    int i;
    int i2;
    int i3;
    int i1;
    int i8;
    label242:
    int i6;
    int i4;
    label259:
    int k;
    int i7;
    if (paramInt1 == 73)
    {
      m = 0;
      n = 0;
      paramInt1 = paramInt2;
      if (paramInt1 < i9)
      {
        i = this.tokenBuffer[paramInt1];
        if (i == 65535)
        {
          i2 = paramInt1 + 1;
          i3 = m;
          i1 = n;
        }
        for (;;)
        {
          paramInt1 = i2 + 1;
          n = i1;
          m = i3;
          break;
          if (Character.isLowerCase(i))
          {
            i1 = n + 1;
            i2 = paramInt1;
            i3 = m;
          }
          else
          {
            i2 = paramInt1;
            i1 = n;
            i3 = m;
            if (Character.isUpperCase(i))
            {
              i3 = m + 1;
              i2 = paramInt1;
              i1 = n;
            }
          }
        }
      }
      if (n == 0) {
        m = 68;
      }
    }
    else
    {
      if ((i9 < paramInt2 + 2) || (this.tokenBuffer[(i9 - 1)] != '}') || (this.tokenBuffer[(i9 - 2)] == 65535) || (peek() != 58)) {
        break label364;
      }
      i8 = 1;
      i2 = -1;
      n = -1;
      i3 = -1;
      i6 = 0;
      i4 = paramInt2;
      paramInt1 = paramInt2;
      if (i4 >= i9) {
        break label646;
      }
      k = this.tokenBuffer[i4];
      if (k != 65535) {
        break label370;
      }
      i4 += 1;
      if (i4 >= i9) {
        break label878;
      }
      localObject = this.tokenBuffer;
      i1 = paramInt1 + 1;
      localObject[paramInt1] = this.tokenBuffer[i4];
      i7 = n;
      paramInt1 = i1;
      i1 = i6;
    }
    for (;;)
    {
      i4 += 1;
      i6 = i1;
      n = i7;
      break label259;
      if (m == 0)
      {
        m = 85;
        break;
      }
      m = 80;
      break;
      label364:
      i8 = 0;
      break label242;
      label370:
      i1 = i6;
      i7 = n;
      int i5 = i3;
      if (i8 != 0)
      {
        if (k != 123) {
          break label472;
        }
        if (n < 0)
        {
          i7 = paramInt1;
          label402:
          i1 = i6 + 1;
          i5 = i3;
        }
      }
      else
      {
        label412:
        if (i1 <= 0) {
          break label568;
        }
        n = i2;
        i = k;
      }
      for (;;)
      {
        localObject = this.tokenBuffer;
        i2 = paramInt1 + 1;
        localObject[paramInt1] = i;
        paramInt1 = i2;
        i2 = n;
        i3 = i5;
        break;
        i7 = n;
        if (i6 != 0) {
          break label402;
        }
        i7 = n;
        break label402;
        label472:
        i1 = i6;
        i7 = n;
        i5 = i3;
        if (k != 125) {
          break label412;
        }
        i6 -= 1;
        if (i6 < 0)
        {
          i1 = i6;
          i7 = n;
          i5 = i3;
          break label412;
        }
        i1 = i6;
        i7 = n;
        i5 = i3;
        if (i6 != 0) {
          break label412;
        }
        if (i3 < 0)
        {
          i5 = paramInt1;
          i1 = i6;
          i7 = n;
          break label412;
        }
        i1 = i6;
        i7 = n;
        i5 = i3;
        break label412;
        label568:
        if (k == 58)
        {
          if (i2 >= 0) {}
          for (n = -1;; n = paramInt1)
          {
            i = k;
            break;
          }
        }
        int j;
        if (m == 85)
        {
          j = Character.toUpperCase(k);
          n = i2;
        }
        else
        {
          j = k;
          n = i2;
          if (m == 68)
          {
            j = Character.toLowerCase(k);
            n = i2;
          }
        }
      }
      label646:
      m = paramInt1 - paramInt2;
      if ((n >= 0) && (i3 > n))
      {
        if (n > 0) {}
        for (localObject = new String(this.tokenBuffer, paramInt2, n - paramInt2);; localObject = null)
        {
          paramInt1 = n + 1;
          String str = new String(this.tokenBuffer, paramInt1, i3 - paramInt1);
          read();
          paramInt1 = read();
          paramReadTable = readValues(paramInt1, paramReadTable.lookup(paramInt1), paramReadTable);
          if (!(paramReadTable instanceof SimpleSymbol)) {
            error("expected identifier in symbol after '{URI}:'");
          }
          return Symbol.valueOf(paramReadTable.toString(), str, (String)localObject);
        }
      }
      if ((paramReadTable.initialColonIsKeyword) && (i2 == paramInt2) && (m > 1))
      {
        paramInt2 += 1;
        return Keyword.make(new String(this.tokenBuffer, paramInt2, paramInt1 - paramInt2).intern());
      }
      if ((paramReadTable.finalColonIsKeyword) && (i2 == paramInt1 - 1) && ((m > 1) || (this.seenEscapes))) {
        return Keyword.make(new String(this.tokenBuffer, paramInt2, m - 1).intern());
      }
      return paramReadTable.makeSymbol(new String(this.tokenBuffer, paramInt2, m));
      label878:
      i1 = i6;
      i7 = n;
    }
  }
  
  public Object readCommand()
    throws IOException, SyntaxException
  {
    return readObject();
  }
  
  public int readEscape()
    throws IOException, SyntaxException
  {
    int i = read();
    if (i < 0)
    {
      eofError("unexpected EOF in character literal");
      return -1;
    }
    return readEscape(i);
  }
  
  public final int readEscape(int paramInt)
    throws IOException, SyntaxException
  {
    int i = paramInt;
    label326:
    int j;
    switch ((char)paramInt)
    {
    default: 
    case 'a': 
    case 'b': 
    case 't': 
    case 'n': 
    case 'v': 
    case 'f': 
    case 'r': 
    case 'e': 
    case '"': 
    case '\\': 
    case '\t': 
    case '\n': 
    case '\r': 
    case ' ': 
    case 'M': 
    case 'C': 
    case '^': 
    case '0': 
    case '1': 
    case '2': 
    case '3': 
    case '4': 
    case '5': 
    case '6': 
    case '7': 
      for (;;)
      {
        return paramInt;
        paramInt = 7;
        continue;
        paramInt = 8;
        continue;
        paramInt = 9;
        continue;
        paramInt = 10;
        continue;
        paramInt = 11;
        continue;
        paramInt = 12;
        continue;
        paramInt = 13;
        continue;
        paramInt = 27;
        continue;
        paramInt = 34;
        continue;
        paramInt = 92;
        continue;
        i = read();
        if (i < 0)
        {
          eofError("unexpected EOF in literal");
          return -1;
        }
        if (i == 10)
        {
          paramInt = i;
          if (i != 10) {}
        }
        else
        {
          do
          {
            paramInt = read();
            if (paramInt < 0)
            {
              eofError("unexpected EOF in literal");
              return -1;
              if (i == 13)
              {
                if (peek() == 10) {
                  skip();
                }
                i = 10;
                break label326;
              }
              if ((i == 32) || (i == 9)) {
                break;
              }
              unread(i);
              break label326;
            }
          } while ((paramInt == 32) || (paramInt == 9));
          unread(paramInt);
          return -2;
          if (read() != 45)
          {
            error("Invalid escape character syntax");
            return 63;
          }
          i = read();
          paramInt = i;
          if (i == 92) {
            paramInt = readEscape();
          }
          return paramInt | 0x80;
          if (read() != 45)
          {
            error("Invalid escape character syntax");
            return 63;
          }
          i = read();
          paramInt = i;
          if (i == 92) {
            paramInt = readEscape();
          }
          if (paramInt == 63) {
            return 127;
          }
          return paramInt & 0x9F;
          i = paramInt - 48;
          int k;
          for (paramInt = 0;; paramInt = j)
          {
            j = paramInt + 1;
            paramInt = i;
            if (j >= 3) {
              break;
            }
            k = read();
            paramInt = Character.digit((char)k, 8);
            if (paramInt < 0) {
              break label560;
            }
            i = (i << 3) + paramInt;
          }
          paramInt = i;
          if (k >= 0)
          {
            unread(k);
            paramInt = i;
          }
        }
      }
    case 'u': 
      label560:
      i = 0;
      for (paramInt = 4;; paramInt = j)
      {
        j = paramInt - 1;
        paramInt = i;
        if (j < 0) {
          break;
        }
        paramInt = read();
        if (paramInt < 0) {
          eofError("premature EOF in \\u escape");
        }
        paramInt = Character.digit((char)paramInt, 16);
        if (paramInt < 0) {
          error("non-hex character following \\u");
        }
        i = i * 16 + paramInt;
      }
    }
    return readHexEscape();
  }
  
  public int readHexEscape()
    throws IOException, SyntaxException
  {
    int j;
    int k;
    for (int i = 0;; i = (i << 4) + k)
    {
      j = read();
      k = Character.digit((char)j, 16);
      if (k < 0) {
        break;
      }
    }
    if ((j != 59) && (j >= 0)) {
      unread(j);
    }
    return i;
  }
  
  public final void readNestedComment(char paramChar1, char paramChar2)
    throws IOException, SyntaxException
  {
    int k = 1;
    int m = this.port.getLineNumber();
    int n = this.port.getColumnNumber();
    int j;
    do
    {
      char c = read();
      int i;
      if (c == '|')
      {
        c = read();
        i = c;
        j = k;
        if (c == paramChar1)
        {
          j = k - 1;
          i = c;
        }
      }
      while (i < 0)
      {
        eofError("unexpected end-of-file in " + paramChar1 + paramChar2 + " comment starting here", m + 1, n - 1);
        return;
        i = c;
        j = k;
        if (c == paramChar1)
        {
          c = read();
          i = c;
          j = k;
          if (c == paramChar2)
          {
            j = k + 1;
            i = c;
          }
        }
      }
      k = j;
    } while (j > 0);
  }
  
  public Object readObject()
    throws IOException, SyntaxException
  {
    char c = ((InPort)this.port).readState;
    int i = this.tokenBufferLength;
    ((InPort)this.port).readState = ' ';
    try
    {
      Object localObject1 = ReadTable.getCurrent();
      int j;
      int k;
      Object localObject3;
      do
      {
        j = this.port.getLineNumber();
        k = this.port.getColumnNumber();
        int m = this.port.read();
        if (m < 0)
        {
          localObject1 = Sequence.eofValue;
          return localObject1;
        }
        localObject3 = readValues(m, (ReadTable)localObject1);
      } while (localObject3 == Values.empty);
      localObject1 = handlePostfix(localObject3, (ReadTable)localObject1, j, k);
      return localObject1;
    }
    finally
    {
      this.tokenBufferLength = i;
      ((InPort)this.port).readState = c;
    }
  }
  
  public final Object readObject(int paramInt)
    throws IOException, SyntaxException
  {
    unread(paramInt);
    return readObject();
  }
  
  void readToken(int paramInt, char paramChar, ReadTable paramReadTable)
    throws IOException, SyntaxException
  {
    char c1 = '\000';
    paramChar = '\000';
    char c2 = paramInt;
    paramInt = paramChar;
    ReadTableEntry localReadTableEntry;
    if (c2 < 0)
    {
      if (c1 != 0) {
        eofError("unexpected EOF between escapes");
      }
    }
    else
    {
      localReadTableEntry = paramReadTable.lookup(c2);
      paramChar = localReadTableEntry.getKind();
      if (paramChar != 0) {
        break label111;
      }
      if (c1 != 0)
      {
        tokenBufferAppend(65535);
        tokenBufferAppend(c2);
        paramChar = c1;
      }
    }
    for (;;)
    {
      c2 = read();
      c1 = paramChar;
      break;
      if (c2 == '}')
      {
        paramInt -= 1;
        if (paramInt >= 0)
        {
          tokenBufferAppend(c2);
          paramChar = c1;
          continue;
        }
      }
      unread(c2);
      return;
      label111:
      int i = paramChar;
      if (c2 == paramReadTable.postfixLookupOperator)
      {
        i = paramChar;
        if (c1 == 0)
        {
          int j = this.port.peek();
          if (j == paramReadTable.postfixLookupOperator)
          {
            unread(c2);
            return;
          }
          i = paramChar;
          if (validPostfixLookupStart(j, paramReadTable)) {
            i = 5;
          }
        }
      }
      if (i == 3)
      {
        c2 = read();
        if (c2 < 0) {
          eofError("unexpected EOF after single escape");
        }
        paramChar = c2;
        if (paramReadTable.hexEscapeAfterBackslash) {
          if (c2 != 'x')
          {
            paramChar = c2;
            if (c2 != 'X') {}
          }
          else
          {
            paramChar = readHexEscape();
          }
        }
        tokenBufferAppend(65535);
        tokenBufferAppend(paramChar);
        this.seenEscapes = true;
        paramChar = c1;
      }
      else
      {
        if (i == 4)
        {
          if (c1 == 0) {}
          for (paramChar = '\001';; paramChar = '\000')
          {
            this.seenEscapes = true;
            break;
          }
        }
        if (c1 != 0)
        {
          tokenBufferAppend(65535);
          tokenBufferAppend(c2);
          paramChar = c1;
        }
        else
        {
          paramChar = paramInt;
          switch (i)
          {
          case 3: 
          default: 
            paramChar = c1;
            break;
          case 1: 
            unread(c2);
            return;
          case 2: 
            paramChar = paramInt;
            if (c2 == '{')
            {
              paramChar = paramInt;
              if (localReadTableEntry == ReadTableEntry.brace) {
                paramChar = paramInt + 1;
              }
            }
          case 6: 
            tokenBufferAppend(c2);
            paramInt = paramChar;
            paramChar = c1;
            break;
          case 4: 
            paramChar = '\001';
            this.seenEscapes = true;
          }
        }
      }
    }
    unread(c2);
  }
  
  public Object readValues(int paramInt, ReadTable paramReadTable)
    throws IOException, SyntaxException
  {
    return readValues(paramInt, paramReadTable.lookup(paramInt), paramReadTable);
  }
  
  public Object readValues(int paramInt, ReadTableEntry paramReadTableEntry, ReadTable paramReadTable)
    throws IOException, SyntaxException
  {
    int i = this.tokenBufferLength;
    this.seenEscapes = false;
    switch (paramReadTableEntry.getKind())
    {
    case 2: 
    case 3: 
    case 4: 
    default: 
      return readAndHandleToken(paramInt, i, paramReadTable);
    case 0: 
      paramReadTableEntry = "invalid character #\\" + (char)paramInt;
      if (this.interactive) {
        fatal(paramReadTableEntry);
      }
      for (;;)
      {
        return Values.empty;
        error(paramReadTableEntry);
      }
    case 1: 
      return Values.empty;
    }
    return paramReadTableEntry.read(this, paramInt, -1);
  }
  
  protected void setCdr(Object paramObject1, Object paramObject2)
  {
    ((Pair)paramObject1).setCdrBackdoor(paramObject2);
  }
  
  protected boolean validPostfixLookupStart(int paramInt, ReadTable paramReadTable)
    throws IOException
  {
    if ((paramInt < 0) || (paramInt == 58) || (paramInt == paramReadTable.postfixLookupOperator)) {}
    do
    {
      return false;
      if (paramInt == 44) {
        return true;
      }
      paramInt = paramReadTable.lookup(paramInt).getKind();
    } while ((paramInt != 2) && (paramInt != 6) && (paramInt != 4) && (paramInt != 3));
    return true;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\LispReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */