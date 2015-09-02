package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.expr.Compilation;
import gnu.expr.PrimProcedure;
import gnu.expr.Special;
import gnu.kawa.xml.CommentConstructor;
import gnu.kawa.xml.MakeAttribute;
import gnu.kawa.xml.MakeCDATA;
import gnu.kawa.xml.MakeProcInst;
import gnu.kawa.xml.MakeText;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Namespace;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SourceLocator;
import gnu.text.SyntaxException;
import gnu.xml.XName;
import java.io.IOException;

public class ReaderXmlElement
  extends ReadTableEntry
{
  static final String DEFAULT_ELEMENT_NAMESPACE = "[default-element-namespace]";
  
  public static void namedEntity(LispReader paramLispReader, String paramString)
  {
    paramString = paramString.intern();
    int i = 63;
    if (paramString == "lt") {
      i = 60;
    }
    for (;;)
    {
      paramLispReader.tokenBufferAppend(i);
      return;
      if (paramString == "gt") {
        i = 62;
      } else if (paramString == "amp") {
        i = 38;
      } else if (paramString == "quot") {
        i = 34;
      } else if (paramString == "apos") {
        i = 39;
      } else {
        paramLispReader.error("unknown enity reference: '" + paramString + "'");
      }
    }
  }
  
  public static Pair quote(Object paramObject)
  {
    return LList.list2(Namespace.EmptyNamespace.getSymbol("quote"), paramObject);
  }
  
  static void readCharRef(LispReader paramLispReader)
    throws IOException, SyntaxException
  {
    int i = paramLispReader.read();
    int j;
    int k;
    if (i == 120)
    {
      j = 16;
      i = paramLispReader.read();
      k = 0;
    }
    for (;;)
    {
      int m;
      if (i >= 0)
      {
        m = Character.digit((char)i, j);
        if (m >= 0) {
          break label62;
        }
      }
      label62:
      while (k >= 134217728)
      {
        if (i == 59) {
          break label83;
        }
        paramLispReader.unread(i);
        paramLispReader.error("invalid character reference");
        return;
        j = 10;
        break;
      }
      k = k * j + m;
      i = paramLispReader.read();
    }
    label83:
    if (((k > 0) && (k <= 55295)) || ((k >= 57344) && (k <= 65533)) || ((k >= 65536) && (k <= 1114111)))
    {
      paramLispReader.tokenBufferAppend(k);
      return;
    }
    paramLispReader.error("invalid character value " + k);
  }
  
  public static Pair readContent(LispReader paramLispReader, char paramChar, Pair paramPair)
    throws IOException, SyntaxException
  {
    paramLispReader.tokenBufferLength = 0;
    int j = 0;
    Object localObject2 = paramPair;
    for (;;)
    {
      Object localObject6 = null;
      paramPair = null;
      Object localObject5 = null;
      Object localObject7 = null;
      Object localObject4 = null;
      Object localObject3 = null;
      Object localObject8 = null;
      Object localObject1 = null;
      int k = paramLispReader.getLineNumber();
      int m = paramLispReader.getColumnNumber();
      char c = paramLispReader.read();
      int i;
      if (c < 0)
      {
        paramLispReader.error("unexpected end-of-file");
        paramPair = Special.eof;
        i = j;
      }
      for (;;)
      {
        localObject3 = localObject1;
        if (paramPair != null)
        {
          localObject3 = localObject1;
          if (paramLispReader.tokenBufferLength > 0)
          {
            localObject3 = paramLispReader.tokenBufferString();
            paramLispReader.tokenBufferLength = 0;
          }
        }
        localObject1 = localObject2;
        if (localObject3 != null)
        {
          localObject1 = PairWithPosition.make(Pair.list2(MakeText.makeText, localObject3), paramLispReader.makeNil(), null, -1, -1);
          ((Pair)localObject2).setCdrBackdoor(localObject1);
        }
        if (paramPair != Special.eof) {
          break;
        }
        return (Pair)localObject1;
        if (c == paramChar)
        {
          if (paramChar == '<')
          {
            localObject1 = localObject7;
            if (paramLispReader.tokenBufferLength > 0)
            {
              localObject1 = paramLispReader.tokenBufferString();
              paramLispReader.tokenBufferLength = 0;
            }
            i = paramLispReader.read();
            if (i == 47) {
              paramPair = Special.eof;
            }
          }
          for (;;)
          {
            i = 0;
            break;
            paramPair = readXMLConstructor(paramLispReader, i, true);
            continue;
            if (paramLispReader.checkNext(paramChar))
            {
              paramLispReader.tokenBufferAppend(paramChar);
              paramPair = (Pair)localObject6;
              localObject1 = localObject4;
            }
            else
            {
              paramPair = Special.eof;
              localObject1 = localObject4;
            }
          }
        }
        if (c == '&')
        {
          i = paramLispReader.read();
          if (i == 35)
          {
            readCharRef(paramLispReader);
            localObject1 = localObject3;
          }
          for (;;)
          {
            i = 1;
            break;
            if ((i == 40) || (i == 123))
            {
              if (paramLispReader.tokenBufferLength <= 0)
              {
                localObject1 = localObject8;
                if (j == 0) {}
              }
              else
              {
                localObject1 = paramLispReader.tokenBufferString();
              }
              paramLispReader.tokenBufferLength = 0;
              paramPair = readEscapedExpression(paramLispReader, i);
            }
            else
            {
              localObject4 = readEntity(paramLispReader, i);
              paramPair = (Pair)localObject4;
              localObject1 = localObject3;
              if (j != 0)
              {
                paramPair = (Pair)localObject4;
                localObject1 = localObject3;
                if (paramLispReader.tokenBufferLength == 0)
                {
                  localObject1 = "";
                  paramPair = (Pair)localObject4;
                }
              }
            }
          }
        }
        i = c;
        if (paramChar != '<') {
          if ((c != '\t') && (c != '\n'))
          {
            i = c;
            if (c != '\r') {}
          }
          else
          {
            i = 32;
          }
        }
        if (i == 60) {
          paramLispReader.error('e', "'<' must be quoted in a direct attribute value");
        }
        paramLispReader.tokenBufferAppend((char)i);
        paramPair = (Pair)localObject5;
        i = j;
      }
      j = i;
      localObject2 = localObject1;
      if (paramPair != null)
      {
        localObject2 = PairWithPosition.make(paramPair, paramLispReader.makeNil(), null, k + 1, m);
        ((Pair)localObject1).setCdrBackdoor(localObject2);
        j = i;
      }
    }
  }
  
  public static Object readElementConstructor(LispReader paramLispReader, int paramInt)
    throws IOException, SyntaxException
  {
    int m = paramLispReader.getLineNumber() + 1;
    int n = paramLispReader.getColumnNumber() - 2;
    Object localObject1 = readQNameExpression(paramLispReader, paramInt, true);
    if (paramLispReader.tokenBufferLength == 0) {}
    PairWithPosition localPairWithPosition1;
    Object localObject3;
    Object localObject2;
    int i;
    for (String str = null;; str = paramLispReader.tokenBufferString())
    {
      localPairWithPosition1 = PairWithPosition.make(localObject1, LList.Empty, paramLispReader.getName(), m, n);
      localObject3 = localPairWithPosition1;
      localObject2 = LList.Empty;
      i = 0;
      paramInt = paramLispReader.readUnicodeChar();
      while ((paramInt >= 0) && (Character.isWhitespace(paramInt)))
      {
        paramInt = paramLispReader.read();
        i = 1;
      }
    }
    int k;
    int j;
    if ((paramInt < 0) || (paramInt == 62) || (paramInt == 47))
    {
      k = 0;
      i = k;
      j = paramInt;
      if (paramInt == 47)
      {
        j = paramLispReader.read();
        if (j != 62) {
          break label465;
        }
        i = 1;
      }
    }
    for (;;)
    {
      if (i == 0)
      {
        if (j != 62)
        {
          paramLispReader.error("missing '>' after start element");
          return Boolean.FALSE;
          if (i == 0) {
            paramLispReader.error("missing space before attribute");
          }
          Object localObject4 = readQNameExpression(paramLispReader, paramInt, false);
          paramLispReader.getLineNumber();
          paramLispReader.getColumnNumber();
          paramInt = paramLispReader.tokenBufferLength;
          PairWithPosition localPairWithPosition2 = null;
          localObject1 = localPairWithPosition2;
          if (paramLispReader.tokenBufferLength >= 5)
          {
            localObject1 = localPairWithPosition2;
            if (paramLispReader.tokenBuffer[0] == 'x')
            {
              localObject1 = localPairWithPosition2;
              if (paramLispReader.tokenBuffer[1] == 'm')
              {
                localObject1 = localPairWithPosition2;
                if (paramLispReader.tokenBuffer[2] == 'l')
                {
                  localObject1 = localPairWithPosition2;
                  if (paramLispReader.tokenBuffer[3] == 'n')
                  {
                    localObject1 = localPairWithPosition2;
                    if (paramLispReader.tokenBuffer[4] == 's')
                    {
                      if (paramLispReader.tokenBufferLength != 5) {
                        break label402;
                      }
                      localObject1 = "";
                    }
                  }
                }
              }
            }
          }
          for (;;)
          {
            if (skipSpace(paramLispReader, 32) != 61) {
              paramLispReader.error("missing '=' after attribute");
            }
            paramInt = skipSpace(paramLispReader, 32);
            localPairWithPosition2 = PairWithPosition.make(MakeAttribute.makeAttribute, LList.Empty, paramLispReader.getName(), m, n);
            localObject4 = PairWithPosition.make(localObject4, LList.Empty, paramLispReader.getName(), m, n);
            paramLispReader.setCdr(localPairWithPosition2, localObject4);
            readContent(paramLispReader, (char)paramInt, (Pair)localObject4);
            if (localObject1 == null) {
              break label440;
            }
            localObject2 = new PairWithPosition((SourceLocator)localObject4, Pair.make(localObject1, ((PairWithPosition)localObject4).getCdr()), localObject2);
            break;
            label402:
            localObject1 = localPairWithPosition2;
            if (paramLispReader.tokenBuffer[5] == ':') {
              localObject1 = new String(paramLispReader.tokenBuffer, 6, paramLispReader.tokenBufferLength - 6);
            }
          }
          label440:
          localObject1 = PairWithPosition.make(localPairWithPosition2, paramLispReader.makeNil(), null, -1, -1);
          ((Pair)localObject3).setCdrBackdoor(localObject1);
          localObject3 = localObject1;
          break;
          label465:
          paramLispReader.unread(j);
          i = k;
          continue;
        }
        readContent(paramLispReader, '<', (Pair)localObject3);
        paramInt = paramLispReader.readUnicodeChar();
        i = paramInt;
        if (XName.isNameStart(paramInt))
        {
          paramLispReader.tokenBufferLength = 0;
          i = paramInt;
          do
          {
            do
            {
              paramLispReader.tokenBufferAppend(i);
              paramInt = paramLispReader.readUnicodeChar();
              i = paramInt;
            } while (XName.isNamePart(paramInt));
            i = paramInt;
          } while (paramInt == 58);
          localObject1 = paramLispReader.tokenBufferString();
          if ((str == null) || (!((String)localObject1).equals(str)))
          {
            localObject3 = paramLispReader.getName();
            i = paramLispReader.getLineNumber();
            j = paramLispReader.getColumnNumber();
            k = paramLispReader.tokenBufferLength;
            if (str != null) {
              break label681;
            }
          }
        }
      }
    }
    label681:
    for (localObject1 = "computed start tag closed by '</" + (String)localObject1 + ">'";; localObject1 = "'<" + str + ">' closed by '</" + (String)localObject1 + ">'")
    {
      paramLispReader.error('e', (String)localObject3, i + 1, j - k, (String)localObject1);
      paramLispReader.tokenBufferLength = 0;
      i = paramInt;
      if (skipSpace(paramLispReader, i) != 62) {
        paramLispReader.error("missing '>' after end element");
      }
      localObject1 = LList.reverseInPlace(localObject2);
      return PairWithPosition.make(MakeXmlElement.makeXml, Pair.make(localObject1, localPairWithPosition1), paramLispReader.getName(), m, n);
    }
  }
  
  static Object readEntity(LispReader paramLispReader, int paramInt)
    throws IOException, SyntaxException
  {
    int i = paramLispReader.tokenBufferLength;
    for (;;)
    {
      int j;
      if (paramInt >= 0)
      {
        j = (char)paramInt;
        if (XName.isNamePart(j)) {}
      }
      else
      {
        if (paramInt == 59) {
          break;
        }
        paramLispReader.unread(paramInt);
        paramLispReader.error("invalid entity reference");
        return "?";
      }
      paramLispReader.tokenBufferAppend(j);
      paramInt = paramLispReader.read();
    }
    String str = new String(paramLispReader.tokenBuffer, i, paramLispReader.tokenBufferLength - i);
    paramLispReader.tokenBufferLength = i;
    namedEntity(paramLispReader, str);
    return null;
  }
  
  static Object readEscapedExpression(LispReader paramLispReader, int paramInt)
    throws IOException, SyntaxException
  {
    if (paramInt == 40)
    {
      paramLispReader.unread(paramInt);
      return paramLispReader.readObject();
    }
    LineBufferedReader localLineBufferedReader = paramLispReader.getPort();
    char c = paramLispReader.pushNesting('{');
    paramInt = localLineBufferedReader.getLineNumber();
    int i = localLineBufferedReader.getColumnNumber();
    try
    {
      Pair localPair = paramLispReader.makePair(new PrimProcedure(Compilation.typeValues.getDeclaredMethod("values", 1)), paramInt, i);
      Object localObject1 = localPair;
      ReadTable localReadTable = ReadTable.getCurrent();
      for (;;)
      {
        int j = localLineBufferedReader.getLineNumber();
        int k = localLineBufferedReader.getColumnNumber();
        int m = localLineBufferedReader.read();
        if (m == 125)
        {
          paramLispReader.tokenBufferLength = 0;
          if (localObject1 != localPair.getCdr()) {
            break;
          }
          localObject1 = ((Pair)localObject1).getCar();
          return localObject1;
        }
        if (m < 0) {
          paramLispReader.eofError("unexpected EOF in list starting here", paramInt + 1, i);
        }
        Object localObject3 = paramLispReader.readValues(m, localReadTable.lookup(m), localReadTable);
        if (localObject3 != Values.empty)
        {
          localObject3 = paramLispReader.makePair(paramLispReader.handlePostfix(localObject3, localReadTable, j, k), j, k);
          paramLispReader.setCdr(localObject1, localObject3);
          localObject1 = localObject3;
        }
      }
      return localPair;
    }
    finally
    {
      paramLispReader.popNesting(c);
    }
  }
  
  public static Object readQNameExpression(LispReader paramLispReader, int paramInt, boolean paramBoolean)
    throws IOException, SyntaxException
  {
    paramLispReader.getName();
    int k = paramLispReader.getLineNumber();
    int m = paramLispReader.getColumnNumber();
    paramLispReader.tokenBufferLength = 0;
    if (XName.isNameStart(paramInt))
    {
      int j = -1;
      int i = paramInt;
      paramInt = j;
      do
      {
        for (;;)
        {
          paramLispReader.tokenBufferAppend(i);
          j = paramLispReader.readUnicodeChar();
          if ((j != 58) || (paramInt >= 0)) {
            break;
          }
          paramInt = paramLispReader.tokenBufferLength;
          i = j;
        }
        i = j;
      } while (XName.isNamePart(j));
      paramLispReader.unread(j);
      if ((paramInt >= 0) || (paramBoolean))
      {
        i = paramLispReader.tokenBufferLength;
        String str = new String(paramLispReader.tokenBuffer, paramInt + 1, i - paramInt - 1).intern();
        if (paramInt < 0) {}
        for (Object localObject = "[default-element-namespace]";; localObject = new String(paramLispReader.tokenBuffer, 0, paramInt).intern())
        {
          localObject = Namespace.EmptyNamespace.getSymbol((String)localObject);
          return new Pair(ResolveNamespace.resolveQName, PairWithPosition.make(localObject, new Pair(str, LList.Empty), paramLispReader.getName(), k + 1, m));
        }
      }
      return quote(Namespace.getDefaultSymbol(paramLispReader.tokenBufferString().intern()));
    }
    if ((paramInt == 123) || (paramInt == 40)) {
      return readEscapedExpression(paramLispReader, paramInt);
    }
    paramLispReader.error("missing element name");
    return null;
  }
  
  static Object readXMLConstructor(LispReader paramLispReader, int paramInt, boolean paramBoolean)
    throws IOException, SyntaxException
  {
    int j = paramLispReader.getLineNumber() + 1;
    int k = paramLispReader.getColumnNumber() - 2;
    int i;
    if (paramInt == 33)
    {
      paramInt = paramLispReader.read();
      i = paramInt;
      if (paramInt == 45)
      {
        paramInt = paramLispReader.peek();
        i = paramInt;
        if (paramInt == 45)
        {
          paramLispReader.skip();
          if (!paramLispReader.readDelimited("-->")) {
            paramLispReader.error('f', paramLispReader.getName(), j, k, "unexpected end-of-file in XML comment starting here - expected \"-->\"");
          }
          paramLispReader = paramLispReader.tokenBufferString();
          return LList.list2(CommentConstructor.commentConstructor, paramLispReader);
        }
      }
      paramInt = i;
      if (i == 91)
      {
        i = paramLispReader.read();
        paramInt = i;
        if (i == 67)
        {
          i = paramLispReader.read();
          paramInt = i;
          if (i == 68)
          {
            i = paramLispReader.read();
            paramInt = i;
            if (i == 65)
            {
              i = paramLispReader.read();
              paramInt = i;
              if (i == 84)
              {
                i = paramLispReader.read();
                paramInt = i;
                if (i == 65)
                {
                  i = paramLispReader.read();
                  paramInt = i;
                  if (i == 91)
                  {
                    if (!paramLispReader.readDelimited("]]>")) {
                      paramLispReader.error('f', paramLispReader.getName(), j, k, "unexpected end-of-file in CDATA starting here - expected \"]]>\"");
                    }
                    paramLispReader = paramLispReader.tokenBufferString();
                    return LList.list2(MakeCDATA.makeCDATA, paramLispReader);
                  }
                }
              }
            }
          }
        }
      }
      paramLispReader.error('f', paramLispReader.getName(), j, k, "'<!' must be followed by '--' or '[CDATA['");
      while ((paramInt >= 0) && (paramInt != 62) && (paramInt != 10) && (paramInt != 13)) {
        paramInt = paramLispReader.read();
      }
      return null;
    }
    if (paramInt == 63)
    {
      i = paramLispReader.readUnicodeChar();
      if (i >= 0)
      {
        paramInt = i;
        if (XName.isNameStart(i)) {}
      }
      else
      {
        paramLispReader.error("missing target after '<?'");
        paramInt = i;
      }
      do
      {
        paramLispReader.tokenBufferAppend(paramInt);
        i = paramLispReader.readUnicodeChar();
        paramInt = i;
      } while (XName.isNamePart(i));
      String str = paramLispReader.tokenBufferString();
      paramInt = 0;
      while ((i >= 0) && (Character.isWhitespace(i)))
      {
        paramInt += 1;
        i = paramLispReader.read();
      }
      paramLispReader.unread(i);
      char c = paramLispReader.pushNesting('?');
      try
      {
        if (!paramLispReader.readDelimited("?>")) {
          paramLispReader.error('f', paramLispReader.getName(), j, k, "unexpected end-of-file looking for \"?>\"");
        }
        paramLispReader.popNesting(c);
        if ((paramInt == 0) && (paramLispReader.tokenBufferLength > 0)) {
          paramLispReader.error("target must be followed by space or '?>'");
        }
        paramLispReader = paramLispReader.tokenBufferString();
        return LList.list3(MakeProcInst.makeProcInst, str, paramLispReader);
      }
      finally
      {
        paramLispReader.popNesting(c);
      }
    }
    return readElementConstructor(paramLispReader, paramInt);
  }
  
  static int skipSpace(LispReader paramLispReader, int paramInt)
    throws IOException, SyntaxException
  {
    for (;;)
    {
      if ((paramInt < 0) || (!Character.isWhitespace(paramInt))) {
        return paramInt;
      }
      paramInt = paramLispReader.readUnicodeChar();
    }
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2)
    throws IOException, SyntaxException
  {
    paramLexer = (LispReader)paramLexer;
    return readXMLConstructor(paramLexer, paramLexer.readUnicodeChar(), false);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ReaderXmlElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */