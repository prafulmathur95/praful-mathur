package gnu.ecmascript;

import gnu.expr.QuoteExp;
import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Hashtable;

public class Lexer
  extends gnu.text.Lexer
{
  public static final Char colonToken;
  public static final Char commaToken;
  public static final Char condToken;
  public static final Char dotToken;
  public static final Reserved elseToken = new Reserved("else", 38);
  public static final Object eofToken;
  public static final Object eolToken;
  public static final Char equalToken;
  public static final Char lbraceToken;
  public static final Char lbracketToken;
  public static final Char lparenToken = Char.make(40);
  public static final Reserved newToken = new Reserved("new", 39);
  public static final Char notToken;
  public static final Char rbraceToken;
  public static final Char rbracketToken;
  static Hashtable reserved;
  public static final Char rparenToken = Char.make(41);
  public static final Char semicolonToken;
  public static final Char tildeToken;
  private boolean prevWasCR = false;
  
  static
  {
    lbraceToken = Char.make(123);
    rbraceToken = Char.make(125);
    lbracketToken = Char.make(91);
    rbracketToken = Char.make(93);
    dotToken = Char.make(46);
    condToken = Char.make(63);
    commaToken = Char.make(44);
    colonToken = Char.make(58);
    equalToken = Char.make(61);
    tildeToken = Char.make(126);
    notToken = Char.make(33);
    semicolonToken = Char.make(59);
    eolToken = Char.make(10);
    eofToken = Sequence.eofValue;
  }
  
  public Lexer(InPort paramInPort)
  {
    super(paramInPort);
  }
  
  public static Object checkReserved(String paramString)
  {
    if (reserved == null) {
      initReserved();
    }
    return reserved.get(paramString);
  }
  
  public static Object getToken(InPort paramInPort)
    throws IOException, SyntaxException
  {
    return new Lexer(paramInPort).getToken();
  }
  
  static void initReserved()
  {
    try
    {
      if (reserved == null)
      {
        reserved = new Hashtable(20);
        reserved.put("null", new QuoteExp(null));
        reserved.put("true", new QuoteExp(Boolean.TRUE));
        reserved.put("false", new QuoteExp(Boolean.FALSE));
        reserved.put("var", new Reserved("var", 30));
        reserved.put("if", new Reserved("if", 31));
        reserved.put("while", new Reserved("while", 32));
        reserved.put("for", new Reserved("for", 33));
        reserved.put("continue", new Reserved("continue", 34));
        reserved.put("break", new Reserved("break", 35));
        reserved.put("return", new Reserved("return", 36));
        reserved.put("with", new Reserved("with", 37));
        reserved.put("function", new Reserved("function", 41));
        reserved.put("this", new Reserved("this", 40));
        reserved.put("else", elseToken);
        reserved.put("new", newToken);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = new Lexer(InPort.inDefault());
    try
    {
      Object localObject1;
      Object localObject2;
      do
      {
        localObject1 = paramArrayOfString.getToken();
        localObject2 = OutPort.outDefault();
        ((OutPort)localObject2).print("token:");
        ((OutPort)localObject2).print(localObject1);
        ((OutPort)localObject2).println(" [class:" + localObject1.getClass() + "]");
        localObject2 = Sequence.eofValue;
      } while (localObject1 != localObject2);
      return;
    }
    catch (Exception paramArrayOfString)
    {
      System.err.println("caught exception:" + paramArrayOfString);
    }
  }
  
  public String getIdentifier(int paramInt)
    throws IOException
  {
    paramInt = this.port.pos;
    int i = paramInt - 1;
    int j = this.port.limit;
    char[] arrayOfChar = this.port.buffer;
    while ((paramInt < j) && (Character.isJavaIdentifierPart(arrayOfChar[paramInt]))) {
      paramInt += 1;
    }
    this.port.pos = paramInt;
    if (paramInt < j) {
      return new String(arrayOfChar, i, paramInt - i);
    }
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(arrayOfChar, i, paramInt - i);
    paramInt = this.port.read();
    if (paramInt < 0) {}
    for (;;)
    {
      return localStringBuffer.toString();
      if (Character.isJavaIdentifierPart((char)paramInt))
      {
        localStringBuffer.append((char)paramInt);
        break;
      }
      this.port.unread_quick();
    }
  }
  
  public Double getNumericLiteral(int paramInt)
    throws IOException
  {
    int k = 10;
    int i = k;
    int j = paramInt;
    long l;
    if (paramInt == 48)
    {
      paramInt = read();
      if ((paramInt == 120) || (paramInt == 88))
      {
        i = 16;
        j = read();
      }
    }
    else
    {
      paramInt = this.port.pos;
      k = paramInt;
      if (j >= 0) {
        k = paramInt - 1;
      }
      this.port.pos = k;
      l = readDigitsInBuffer(this.port, i);
      if (this.port.pos <= k) {
        break label222;
      }
      paramInt = 1;
      label99:
      if ((paramInt == 0) || (this.port.pos >= this.port.limit)) {
        break label259;
      }
      j = this.port.buffer[this.port.pos];
      if ((Character.isLetterOrDigit((char)j)) || (j == 46)) {
        break label259;
      }
      if (l < 0L) {
        break label227;
      }
    }
    label222:
    label227:
    for (double d = l;; d = IntNum.valueOf(this.port.buffer, k, this.port.pos - k, i, false).doubleValue())
    {
      return new Double(d);
      i = k;
      j = paramInt;
      if (paramInt == 46) {
        break;
      }
      i = k;
      j = paramInt;
      if (paramInt == 101) {
        break;
      }
      i = k;
      j = paramInt;
      if (paramInt == 69) {
        break;
      }
      i = 8;
      j = paramInt;
      break;
      paramInt = 0;
      break label99;
    }
    label259:
    if (i != 10) {
      error("invalid character in non-decimal number");
    }
    StringBuffer localStringBuffer = new StringBuffer(20);
    if (paramInt != 0) {
      localStringBuffer.append(this.port.buffer, k, this.port.pos - k);
    }
    j = -1;
    int m = 0;
    int n;
    for (;;)
    {
      n = this.port.read();
      if (Character.digit((char)n, i) < 0) {
        break;
      }
      paramInt = 1;
      localStringBuffer.append((char)n);
    }
    switch (n)
    {
    default: 
      k = n;
      j = m;
    }
    for (;;)
    {
      if (k >= 0) {
        this.port.unread();
      }
      if (j != 0)
      {
        localStringBuffer.append('e');
        localStringBuffer.append(j);
      }
      return new Double(localStringBuffer.toString());
      if (j >= 0)
      {
        error("duplicate '.' in number");
        break;
      }
      j = localStringBuffer.length();
      localStringBuffer.append('.');
      break;
      j = m;
      k = n;
      if (i == 10)
      {
        i = this.port.peek();
        if ((i != 43) && (i != 45))
        {
          j = m;
          k = n;
          if (Character.digit((char)i, 10) < 0) {}
        }
        else
        {
          if (paramInt == 0) {
            error("mantissa with no digits");
          }
          j = readOptionalExponent();
          k = read();
        }
      }
    }
  }
  
  public String getStringLiteral(char paramChar)
    throws IOException, SyntaxException
  {
    char c2 = this.port.pos;
    char c3 = this.port.limit;
    char[] arrayOfChar = this.port.buffer;
    char c1 = c2;
    char c4;
    StringBuffer localStringBuffer;
    for (;;)
    {
      if (c1 < c3)
      {
        c4 = arrayOfChar[c1];
        if (c4 == paramChar)
        {
          this.port.pos = (c1 + '\001');
          return new String(arrayOfChar, c2, c1 - c2);
        }
        if ((c4 != '\\') && (c4 != '\n') && (c4 != '\r')) {}
      }
      else
      {
        this.port.pos = c1;
        localStringBuffer = new StringBuffer();
        localStringBuffer.append(arrayOfChar, c2, c1 - c2);
        c2 = this.port.read();
        if (c2 != paramChar) {
          break;
        }
        return localStringBuffer.toString();
      }
      c1 += '\001';
    }
    if (c2 < 0) {
      eofError("unterminated string literal");
    }
    if ((c2 == '\n') || (c2 == '\r')) {
      fatal("string literal not terminated before end of line");
    }
    c1 = c2;
    if (c2 == '\\')
    {
      c3 = this.port.read();
      c1 = c3;
    }
    switch (c3)
    {
    default: 
      c1 = c3;
      if (c3 >= '0')
      {
        if (c3 <= '7') {
          break label572;
        }
        c1 = c3;
      }
    case '"': 
    case '\'': 
    case '\\': 
    case '￿': 
    case '\n': 
    case '\r': 
    case 'b': 
    case 't': 
    case 'n': 
    case 'f': 
    case 'r': 
      for (;;)
      {
        localStringBuffer.append((char)c1);
        break;
        eofError("eof following '\\' in string");
        fatal("line terminator following '\\' in string");
        c1 = c3;
        continue;
        c1 = '\b';
        continue;
        c1 = '\t';
        continue;
        c1 = '\n';
        continue;
        c1 = '\f';
        continue;
        c1 = '\r';
      }
    }
    c1 = '\000';
    if (c3 == 'x') {
      c2 = '\002';
    }
    for (;;)
    {
      c4 = c2 - '\001';
      c2 = c1;
      if (c4 >= 0)
      {
        c2 = this.port.read();
        if (c2 < 0) {
          eofError("eof following '\\" + (char)c3 + "' in string");
        }
        c2 = Character.forDigit((char)c2, 16);
        if (c2 < 0)
        {
          error("invalid char following '\\" + (char)c3 + "' in string");
          c2 = '?';
        }
      }
      else
      {
        c1 = c2;
        break;
        c2 = '\004';
        continue;
      }
      c1 = c1 * '\020' + c2;
      c2 = c4;
    }
    label572:
    c1 = '\000';
    c2 = '\003';
    for (;;)
    {
      c2 -= '\001';
      if (c2 >= 0)
      {
        c3 = this.port.read();
        if (c3 < 0) {
          eofError("eof in octal escape in string literal");
        }
        c3 = Character.forDigit((char)c3, 8);
        if (c3 < 0) {
          this.port.unread_quick();
        }
      }
      else
      {
        break;
      }
      c1 = c1 * '\b' + c3;
    }
  }
  
  public Object getToken()
    throws IOException, SyntaxException
  {
    for (int i = read();; i = read())
    {
      Object localObject1;
      if (i < 0) {
        localObject1 = eofToken;
      }
      String str;
      Object localObject2;
      do
      {
        return localObject1;
        if (Character.isWhitespace((char)i)) {
          break;
        }
        switch (i)
        {
        default: 
          if (!Character.isJavaIdentifierStart((char)i)) {
            break label894;
          }
          str = getIdentifier(i).intern();
          localObject2 = checkReserved(str);
          localObject1 = localObject2;
        }
      } while (localObject2 != null);
      return str;
      if (i == 13)
      {
        this.prevWasCR = true;
        return eolToken;
      }
      if ((i == 10) && (!this.prevWasCR)) {
        return eolToken;
      }
      this.prevWasCR = false;
    }
    i = this.port.peek();
    if ((i >= 48) && (i <= 57)) {
      return new QuoteExp(getNumericLiteral(46));
    }
    return dotToken;
    return new QuoteExp(getNumericLiteral(i));
    return new QuoteExp(getStringLiteral((char)i));
    return lparenToken;
    return rparenToken;
    return lbracketToken;
    return rbracketToken;
    return lbraceToken;
    return rbraceToken;
    return condToken;
    return colonToken;
    return semicolonToken;
    return commaToken;
    if (this.port.peek() == 61)
    {
      this.port.skip_quick();
      return Reserved.opEqual;
    }
    return equalToken;
    if (this.port.peek() == 61)
    {
      this.port.skip_quick();
      return Reserved.opNotEqual;
    }
    return notToken;
    return tildeToken;
    return maybeAssignment(Reserved.opTimes);
    return maybeAssignment(Reserved.opDivide);
    return maybeAssignment(Reserved.opBitXor);
    return maybeAssignment(Reserved.opRemainder);
    if (this.port.peek() == 43)
    {
      this.port.skip_quick();
      return maybeAssignment(Reserved.opPlusPlus);
    }
    return maybeAssignment(Reserved.opPlus);
    if (this.port.peek() == 45)
    {
      this.port.skip_quick();
      return maybeAssignment(Reserved.opMinusMinus);
    }
    return maybeAssignment(Reserved.opMinus);
    if (this.port.peek() == 38)
    {
      this.port.skip_quick();
      return maybeAssignment(Reserved.opBoolAnd);
    }
    return maybeAssignment(Reserved.opBitAnd);
    if (this.port.peek() == 124)
    {
      this.port.skip_quick();
      return maybeAssignment(Reserved.opBoolOr);
    }
    return maybeAssignment(Reserved.opBitOr);
    switch (this.port.peek())
    {
    default: 
      return Reserved.opGreater;
    case 62: 
      this.port.skip_quick();
      if (this.port.peek() == 62)
      {
        this.port.skip_quick();
        return maybeAssignment(Reserved.opRshiftUnsigned);
      }
      return maybeAssignment(Reserved.opRshiftSigned);
    }
    this.port.skip_quick();
    return Reserved.opGreaterEqual;
    switch (this.port.peek())
    {
    default: 
      return Reserved.opLess;
    case 60: 
      this.port.skip_quick();
      return maybeAssignment(Reserved.opLshift);
    }
    this.port.skip_quick();
    return Reserved.opLessEqual;
    label894:
    return Char.make((char)i);
  }
  
  public Object maybeAssignment(Object paramObject)
    throws IOException, SyntaxException
  {
    int i = read();
    if (i == 61) {
      error("assignment operation not implemented");
    }
    if (i >= 0) {
      this.port.unread_quick();
    }
    return paramObject;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\ecmascript\Lexer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */