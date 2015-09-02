package gnu.xml;

import gnu.lists.Consumer;
import gnu.text.LineBufferedReader;
import gnu.text.LineInputStreamReader;
import gnu.text.Path;
import gnu.text.SourceMessages;
import java.io.IOException;
import java.io.InputStream;

public class XMLParser
{
  private static final int ATTRIBUTE_SEEN_EQ_STATE = 11;
  private static final int ATTRIBUTE_SEEN_NAME_STATE = 8;
  static final String BAD_ENCODING_SYNTAX = "bad 'encoding' declaration";
  static final String BAD_STANDALONE_SYNTAX = "bad 'standalone' declaration";
  private static final int BEGIN_ELEMENT_STATE = 2;
  private static final int DOCTYPE_NAME_SEEN_STATE = 16;
  private static final int DOCTYPE_SEEN_STATE = 13;
  private static final int END_ELEMENT_STATE = 4;
  private static final int EXPECT_NAME_MODIFIER = 1;
  private static final int EXPECT_RIGHT_STATE = 27;
  private static final int INIT_LEFT_QUEST_STATE = 30;
  private static final int INIT_LEFT_STATE = 34;
  private static final int INIT_STATE = 0;
  private static final int INIT_TEXT_STATE = 31;
  private static final int INVALID_VERSION_DECL = 35;
  private static final int MAYBE_ATTRIBUTE_STATE = 10;
  private static final int PREV_WAS_CR_STATE = 28;
  private static final int SAW_AMP_SHARP_STATE = 26;
  private static final int SAW_AMP_STATE = 25;
  private static final int SAW_ENTITY_REF = 6;
  private static final int SAW_EOF_ERROR = 37;
  private static final int SAW_ERROR = 36;
  private static final int SAW_LEFT_EXCL_MINUS_STATE = 22;
  private static final int SAW_LEFT_EXCL_STATE = 20;
  private static final int SAW_LEFT_QUEST_STATE = 21;
  private static final int SAW_LEFT_SLASH_STATE = 19;
  private static final int SAW_LEFT_STATE = 14;
  private static final int SKIP_SPACES_MODIFIER = 2;
  private static final int TEXT_STATE = 1;
  
  public static LineInputStreamReader XMLStreamReader(InputStream paramInputStream)
    throws IOException
  {
    int k = -1;
    LineInputStreamReader localLineInputStreamReader = new LineInputStreamReader(paramInputStream);
    int m = localLineInputStreamReader.getByte();
    int i;
    int j;
    if (m < 0)
    {
      i = -1;
      if (i >= 0) {
        break label81;
      }
      j = -1;
      label32:
      if ((m != 239) || (i != 187) || (j != 191)) {
        break label90;
      }
      localLineInputStreamReader.resetStart(3);
      localLineInputStreamReader.setCharset("UTF-8");
    }
    for (;;)
    {
      localLineInputStreamReader.setKeepFullLines(false);
      return localLineInputStreamReader;
      i = localLineInputStreamReader.getByte();
      break;
      label81:
      j = localLineInputStreamReader.getByte();
      break label32;
      label90:
      if ((m == 255) && (i == 254) && (j != 0))
      {
        localLineInputStreamReader.resetStart(2);
        localLineInputStreamReader.setCharset("UTF-16LE");
      }
      else if ((m == 254) && (i == 255) && (j != 0))
      {
        localLineInputStreamReader.resetStart(2);
        localLineInputStreamReader.setCharset("UTF-16BE");
      }
      else
      {
        if (j < 0) {}
        while ((m == 76) && (i == 111) && (j == 167) && (k == 148))
        {
          throw new RuntimeException("XMLParser: EBCDIC encodings not supported");
          k = localLineInputStreamReader.getByte();
        }
        localLineInputStreamReader.resetStart(0);
        if (((m == 60) && (((i == 63) && (j == 120) && (k == 109)) || ((i == 0) && (j == 63) && (k == 0)))) || ((m == 0) && (i == 60) && (j == 0) && (k == 63)))
        {
          char[] arrayOfChar = localLineInputStreamReader.buffer;
          paramInputStream = arrayOfChar;
          if (arrayOfChar == null)
          {
            paramInputStream = new char[' '];
            localLineInputStreamReader.buffer = paramInputStream;
          }
          j = 0;
          m = 0;
          int n;
          do
          {
            n = localLineInputStreamReader.getByte();
          } while (n == 0);
          if (n < 0) {}
          for (;;)
          {
            localLineInputStreamReader.pos = 0;
            localLineInputStreamReader.limit = j;
            break;
            k = j + 1;
            paramInputStream[j] = ((char)(n & 0xFF));
            if (m != 0) {
              break label406;
            }
            if (n != 62) {
              break label376;
            }
            j = k;
          }
          label376:
          if (n != 39)
          {
            i = m;
            if (n != 34) {}
          }
          else
          {
            i = n;
          }
          for (;;)
          {
            j = k;
            m = i;
            break;
            label406:
            i = m;
            if (n == m) {
              i = 0;
            }
          }
        }
        localLineInputStreamReader.setCharset("UTF-8");
      }
    }
  }
  
  public static void parse(LineBufferedReader paramLineBufferedReader, SourceMessages paramSourceMessages, Consumer paramConsumer)
    throws IOException
  {
    paramConsumer = new XMLFilter(paramConsumer);
    paramConsumer.setMessages(paramSourceMessages);
    paramConsumer.setSourceLocator(paramLineBufferedReader);
    paramConsumer.startDocument();
    paramSourceMessages = paramLineBufferedReader.getPath();
    if (paramSourceMessages != null) {
      paramConsumer.writeDocumentUri(paramSourceMessages);
    }
    parse(paramLineBufferedReader, paramConsumer);
    paramConsumer.endDocument();
  }
  
  public static void parse(LineBufferedReader paramLineBufferedReader, SourceMessages paramSourceMessages, XMLFilter paramXMLFilter)
    throws IOException
  {
    paramXMLFilter.setMessages(paramSourceMessages);
    paramXMLFilter.setSourceLocator(paramLineBufferedReader);
    paramXMLFilter.startDocument();
    paramSourceMessages = paramLineBufferedReader.getPath();
    if (paramSourceMessages != null) {
      paramXMLFilter.writeDocumentUri(paramSourceMessages);
    }
    parse(paramLineBufferedReader, paramXMLFilter);
    paramXMLFilter.endDocument();
    paramLineBufferedReader.close();
  }
  
  public static void parse(LineBufferedReader paramLineBufferedReader, XMLFilter paramXMLFilter)
  {
    char[] arrayOfChar = paramLineBufferedReader.buffer;
    int i2 = paramLineBufferedReader.pos;
    int i9 = paramLineBufferedReader.limit;
    int i3 = 0;
    int i15 = 60;
    int i14 = 14;
    int i = 32;
    int i12 = 0;
    int i6 = -1;
    Object localObject1 = null;
    int i8 = i9;
    for (;;)
    {
      Object localObject2 = localObject1;
      int i4 = i2;
      label220:
      int i13;
      int i18;
      int i11;
      int i10;
      int i7;
      label248:
      label523:
      label533:
      label602:
      label665:
      int k;
      label903:
      label941:
      label1003:
      label1009:
      int i16;
      switch (i3)
      {
      case 18: 
      case 22: 
      default: 
        i13 = i15;
        i4 = i3;
        localObject2 = localObject1;
        i18 = i14;
        i11 = i6;
        i10 = i12;
        i7 = i8;
      case 0: 
      case 31: 
      case 34: 
      case 35: 
      case 36: 
      case 37: 
      case 1: 
      case 28: 
      case 12: 
      case 15: 
      case 23: 
      case 29: 
      case 32: 
      case 3: 
      case 5: 
      case 7: 
      case 9: 
      case 17: 
      case 24: 
      case 33: 
        for (;;)
        {
          if (i2 < i9)
          {
            i3 = i2 + 1;
            i = arrayOfChar[i2];
            i8 = i7;
            i12 = i10;
            i6 = i11;
            i14 = i18;
            localObject1 = localObject2;
            i2 = i3;
            i3 = i4;
            i15 = i13;
            break;
            i4 = 31;
            i7 = i8;
            i10 = i12;
            i11 = i6;
            i18 = i14;
            localObject2 = localObject1;
            i13 = i15;
            continue;
            if (i == 60)
            {
              i4 = 34;
              i7 = i8;
              i10 = i12;
              i11 = i6;
              i18 = i14;
              localObject2 = localObject1;
              i13 = i15;
            }
            else
            {
              i3 = 1;
              break;
              if (i == 63)
              {
                i7 = i2;
                i4 = 33;
                i10 = i12;
                i11 = i6;
                i18 = i14;
                localObject2 = localObject1;
                i13 = i15;
              }
              else
              {
                i3 = 14;
                break;
                i4 = i6;
                localObject2 = "invalid xml version specifier";
                paramLineBufferedReader.pos = i4;
                paramXMLFilter.error('e', (String)localObject2);
                do
                {
                  if (i4 >= i9) {
                    return;
                  }
                  i2 = i4 + 1;
                  i = arrayOfChar[i4];
                  i4 = i2;
                } while (i != 62);
                i4 = 1;
                i7 = i8;
                i10 = i12;
                i11 = i6;
                i18 = i14;
                i13 = i15;
                continue;
                paramLineBufferedReader.pos = i2;
                paramXMLFilter.error('f', "unexpected end-of-file");
                return;
                i7 = i2 - 1;
                i4 = i2;
                for (;;)
                {
                  if (i == i15) {}
                  for (i3 = i14;; i3 = 25)
                  {
                    i10 = i2 - i4;
                    if (i10 > 0)
                    {
                      paramLineBufferedReader.pos = i2;
                      paramXMLFilter.textFromParser(arrayOfChar, i7, i10);
                    }
                    i7 = arrayOfChar.length;
                    i11 = i6;
                    i18 = i14;
                    localObject2 = localObject1;
                    i4 = i3;
                    i13 = i15;
                    break;
                    if (i != 38) {
                      break label602;
                    }
                  }
                  if (i == 13)
                  {
                    i10 = i2 - i4;
                    paramLineBufferedReader.pos = i2;
                    if (i10 > 0) {
                      paramXMLFilter.textFromParser(arrayOfChar, i7, i10);
                    }
                    if (i2 < i9)
                    {
                      i = arrayOfChar[i2];
                      if (i == 10)
                      {
                        i4 = i2 + 1;
                        i11 = i4;
                        paramLineBufferedReader.incrLineNumber(1, i4);
                        i10 = i4;
                        k = i;
                        i8 = i2;
                      }
                    }
                  }
                  for (;;)
                  {
                    if (i10 != i9) {
                      break label903;
                    }
                    i4 = i11 - 1;
                    i2 = i10;
                    i7 = i8;
                    i = k;
                    break label533;
                    paramXMLFilter.linefeedFromParser();
                    if (i == 133)
                    {
                      i4 = i2 + 1;
                      i11 = i4 + 1;
                      break label665;
                    }
                    paramLineBufferedReader.incrLineNumber(1, i2);
                    i7 = i2;
                    i2 += 1;
                    i4 = i2;
                    break label523;
                    paramXMLFilter.linefeedFromParser();
                    i4 = 28;
                    i11 = i6;
                    i18 = i14;
                    localObject2 = localObject1;
                    i13 = i15;
                    break;
                    if ((i == 133) || (i == 8232))
                    {
                      i4 = i2 - i4;
                      paramLineBufferedReader.pos = (i2 - 1);
                      if (i4 > 0) {
                        paramXMLFilter.textFromParser(arrayOfChar, i7, i4);
                      }
                      paramXMLFilter.linefeedFromParser();
                      paramLineBufferedReader.incrLineNumber(1, i2);
                      i11 = i2 + 1;
                      i8 = i2;
                      k = i;
                      i10 = i2;
                    }
                    else
                    {
                      i8 = i7;
                      i11 = i4;
                      k = i;
                      i10 = i2;
                      if (i == 10)
                      {
                        paramLineBufferedReader.incrLineNumber(1, i2);
                        i8 = i7;
                        i11 = i4;
                        k = i;
                        i10 = i2;
                      }
                    }
                  }
                  i = arrayOfChar[i10];
                  i2 = i10 + 1;
                  i7 = i8;
                  i4 = i11;
                }
                i13 = 1;
                i7 = 1;
                if (i == 10)
                {
                  i3 = 1;
                  if (i != 133) {
                    break label1003;
                  }
                }
                for (i4 = 1;; i4 = 0)
                {
                  if ((i4 | i3) == 0) {
                    break label1009;
                  }
                  paramLineBufferedReader.incrLineNumber(1, i2);
                  i7 = i8;
                  i10 = i12;
                  i11 = i6;
                  i18 = i14;
                  localObject2 = localObject1;
                  i4 = i13;
                  i13 = i15;
                  break;
                  i3 = 0;
                  break label941;
                }
                paramLineBufferedReader.incrLineNumber(1, i2 - 1);
                i3 = i7;
                break;
                if (i == 32) {
                  break label220;
                }
                if (i == 9)
                {
                  i7 = i8;
                  i10 = i12;
                  i11 = i6;
                  i18 = i14;
                  localObject2 = localObject1;
                  i4 = i3;
                  i13 = i15;
                }
                else if ((i == 10) || (i == 13) || (i == 133) || (i == 8232))
                {
                  paramLineBufferedReader.incrLineNumber(1, i2);
                  i7 = i8;
                  i10 = i12;
                  i11 = i6;
                  i18 = i14;
                  localObject2 = localObject1;
                  i4 = i3;
                  i13 = i15;
                }
                else
                {
                  i3 -= 2;
                  break;
                  i16 = i8 + 1;
                  i12 = i2;
                  for (;;)
                  {
                    if (((i < 97) || (i > 122)) && ((i < 65) || (i > 90)) && (i != 95) && (i != 58) && ((i < 192) || ((i > 767) && ((i < 880) || (((i > 8191) || (i == 894)) && ((i < 8204) || ((i > 8205) && ((i < 8304) || (i > 8591)) && ((i < 11264) || (i > 12271)) && ((i < 12289) || (i > 55295)) && ((i < 63744) || (i > 65533)))))))) && ((i12 <= i16) || (i < 48) || (i > 57)) && (i != 46) && (i != 45) && (i != 183) && ((i <= 768) || ((i > 879) && ((i < 8255) || (i > 8256))))) {
                      break label1409;
                    }
                    i7 = i8;
                    i10 = i16;
                    i11 = i6;
                    i18 = i14;
                    localObject2 = localObject1;
                    i2 = i12;
                    i4 = i3;
                    i13 = i15;
                    if (i12 >= i9) {
                      break;
                    }
                    i = arrayOfChar[i12];
                    i12 += 1;
                  }
                  label1409:
                  i3 -= 1;
                  i4 = i12 - i16;
                  if (i4 != 0) {
                    break label5194;
                  }
                  if (i3 == 8) {
                    localObject1 = "missing or invalid attribute name";
                  }
                  for (;;)
                  {
                    i3 = 36;
                    i2 = i12;
                    i12 = i4;
                    break;
                    if ((i3 == 2) || (i3 == 4)) {
                      localObject1 = "missing or invalid element name";
                    } else {
                      localObject1 = "missing or invalid name";
                    }
                  }
                  label1479:
                  if ((i == 120) && (i6 == 0))
                  {
                    i6 = 16;
                    i16 = i10;
                    i7 = i8;
                    i10 = i16;
                    i11 = i6;
                    i18 = i14;
                    localObject2 = localObject1;
                    i2 = i12;
                    i4 = i3;
                    i13 = i15;
                    if (i12 >= i9) {
                      continue;
                    }
                    i = arrayOfChar[i12];
                    i12 += 1;
                    i10 = i16;
                  }
                }
              }
            }
          }
        }
      }
      for (;;)
      {
        if (i != 59) {
          break label1479;
        }
        paramLineBufferedReader.pos = i12;
        paramXMLFilter.emitCharacterReference(i10, arrayOfChar, i8, i12 - 1 - i8);
        i4 = 1;
        i7 = i8;
        i11 = i6;
        i18 = i14;
        localObject2 = localObject1;
        i2 = i12;
        i13 = i15;
        break label248;
        if (i10 >= 134217728)
        {
          label1619:
          paramLineBufferedReader.pos = i12;
          paramXMLFilter.error('e', "invalid character reference");
          i4 = 1;
          i7 = i8;
          i11 = i6;
          i18 = i14;
          localObject2 = localObject1;
          i2 = i12;
          i13 = i15;
          break label248;
        }
        if (i6 == 0) {}
        for (i2 = 10;; i2 = i6)
        {
          i4 = Character.digit(i, i2);
          if (i4 < 0) {
            break label1619;
          }
          i16 = i10 * i2 + i4;
          break;
        }
        if (i == 35)
        {
          i4 = 26;
          i7 = i2;
          i10 = 0;
          i11 = 0;
          i18 = i14;
          localObject2 = localObject1;
          i13 = i15;
          break label248;
        }
        i8 = i2 - 1;
        i3 = 7;
        break;
        paramLineBufferedReader.pos = i2;
        if (i != 59) {
          paramXMLFilter.error('w', "missing ';'");
        }
        paramXMLFilter.emitEntityReference(arrayOfChar, i8, i12);
        i7 = i9;
        i4 = 1;
        i10 = i12;
        i11 = i6;
        i18 = i14;
        localObject2 = localObject1;
        i13 = i15;
        break label248;
        if (i == 47)
        {
          i4 = 19;
          i7 = i8;
          i10 = i12;
          i11 = i6;
          i18 = i14;
          localObject2 = localObject1;
          i13 = i15;
          break label248;
        }
        if (i == 63)
        {
          i7 = i2;
          i4 = 24;
          i10 = i12;
          i11 = i6;
          i18 = i14;
          localObject2 = localObject1;
          i13 = i15;
          break label248;
        }
        if (i == 33)
        {
          i4 = 20;
          i7 = i2;
          i10 = i12;
          i11 = i6;
          i18 = i14;
          localObject2 = localObject1;
          i13 = i15;
          break label248;
        }
        i8 = i2 - 1;
        i3 = 3;
        break;
        paramLineBufferedReader.pos = (i2 - i12);
        paramXMLFilter.emitStartElement(arrayOfChar, i8, i12);
        i3 = 12;
        i8 = i9;
        break;
        label1981:
        int j;
        if (i6 < 0)
        {
          i16 = i2 - 1;
          i6 = i2;
          for (;;)
          {
            if (i == 62)
            {
              i4 = i6 - 2;
              if ((arrayOfChar[i4] == '?') && (i4 >= i16))
              {
                paramLineBufferedReader.pos = i6;
                if ((i12 == 3) && (arrayOfChar[i8] == 'x') && (arrayOfChar[(i8 + 1)] == 'm') && (arrayOfChar[(i8 + 2)] == 'l'))
                {
                  if (i3 == 30)
                  {
                    if ((i4 <= i16 + 7) || (arrayOfChar[i16] != 'v') || (arrayOfChar[(i16 + 1)] != 'e') || (arrayOfChar[(i16 + 2)] != 'r') || (arrayOfChar[(i16 + 3)] != 's') || (arrayOfChar[(i16 + 4)] != 'i') || (arrayOfChar[(i16 + 5)] != 'o') || (arrayOfChar[(i16 + 6)] != 'n'))
                    {
                      i2 = i16;
                      localObject1 = "xml declaration without version";
                      i3 = 36;
                      i6 = i16;
                      break;
                    }
                    i3 = i16 + 7;
                    for (j = arrayOfChar[i3];; j = arrayOfChar[i3])
                    {
                      i2 = i3;
                      if (!Character.isWhitespace(j)) {
                        break;
                      }
                      i3 += 1;
                      i2 = i3;
                      if (i3 >= i4) {
                        break;
                      }
                    }
                    if (j != 61)
                    {
                      i4 = 35;
                      i3 = i6;
                      i6 = i2;
                      i2 = i3;
                      i3 = i4;
                      break;
                    }
                    i3 = i2 + 1;
                    for (j = arrayOfChar[i3];; j = arrayOfChar[i3])
                    {
                      i2 = i3;
                      if (!Character.isWhitespace(j)) {
                        break;
                      }
                      i3 += 1;
                      i2 = i3;
                      if (i3 >= i4) {
                        break;
                      }
                    }
                    if ((j != 39) && (j != 34))
                    {
                      i4 = 35;
                      i3 = i6;
                      i6 = i2;
                      i2 = i3;
                      i3 = i4;
                      break;
                    }
                    i2 += 1;
                    i3 = i2;
                    k = j;
                    for (;;)
                    {
                      if (i3 == i4)
                      {
                        i4 = 35;
                        i3 = i6;
                        i6 = i2;
                        j = k;
                        i2 = i3;
                        i3 = i4;
                        break;
                      }
                      int m = arrayOfChar[i3];
                      if (m == j)
                      {
                        j = m;
                        if (i3 == i2 + 3)
                        {
                          j = m;
                          if (arrayOfChar[i2] == '1')
                          {
                            j = m;
                            if (arrayOfChar[(i2 + 1)] == '.')
                            {
                              n = arrayOfChar[(i2 + 2)];
                              j = n;
                              if (n == 48) {
                                break label2454;
                              }
                              j = n;
                            }
                          }
                        }
                        if (j != 49) {
                          break label2496;
                        }
                        label2454:
                        i2 = i3 + 1;
                        while ((i2 < i4) && (Character.isWhitespace(arrayOfChar[i2]))) {
                          i2 += 1;
                        }
                      }
                      i3 += 1;
                    }
                    label2496:
                    i4 = 35;
                    i3 = i6;
                    i6 = i2;
                    i2 = i3;
                    i3 = i4;
                    break;
                    i3 = i2;
                    n = j;
                    if (i4 > i2 + 7)
                    {
                      i3 = i2;
                      n = j;
                      if (arrayOfChar[i2] == 'e')
                      {
                        i3 = i2;
                        n = j;
                        if (arrayOfChar[(i2 + 1)] == 'n')
                        {
                          i3 = i2;
                          n = j;
                          if (arrayOfChar[(i2 + 2)] == 'c')
                          {
                            i3 = i2;
                            n = j;
                            if (arrayOfChar[(i2 + 3)] == 'o')
                            {
                              i3 = i2;
                              n = j;
                              if (arrayOfChar[(i2 + 4)] == 'd')
                              {
                                i3 = i2;
                                n = j;
                                if (arrayOfChar[(i2 + 5)] == 'i')
                                {
                                  i3 = i2;
                                  n = j;
                                  if (arrayOfChar[(i2 + 6)] == 'n')
                                  {
                                    i3 = i2;
                                    n = j;
                                    if (arrayOfChar[(i2 + 7)] == 'g')
                                    {
                                      i3 = i2 + 8;
                                      for (j = arrayOfChar[i3];; j = arrayOfChar[i3])
                                      {
                                        i2 = i3;
                                        if (!Character.isWhitespace(j)) {
                                          break;
                                        }
                                        i3 += 1;
                                        i2 = i3;
                                        if (i3 >= i4) {
                                          break;
                                        }
                                      }
                                      if (j != 61)
                                      {
                                        localObject1 = "bad 'encoding' declaration";
                                        i4 = 36;
                                        i3 = i6;
                                        i6 = i2;
                                        i2 = i3;
                                        i3 = i4;
                                        break;
                                      }
                                      i3 = i2 + 1;
                                      for (j = arrayOfChar[i3];; j = arrayOfChar[i3])
                                      {
                                        i2 = i3;
                                        if (!Character.isWhitespace(j)) {
                                          break;
                                        }
                                        i3 += 1;
                                        i2 = i3;
                                        if (i3 >= i4) {
                                          break;
                                        }
                                      }
                                      if ((j != 39) && (j != 34))
                                      {
                                        localObject1 = "bad 'encoding' declaration";
                                        i4 = 36;
                                        i3 = i6;
                                        i6 = i2;
                                        i2 = i3;
                                        i3 = i4;
                                        break;
                                      }
                                      i2 += 1;
                                      i3 = i2;
                                      int i1;
                                      for (n = j;; n = i1)
                                      {
                                        if (i3 == i4)
                                        {
                                          localObject1 = "bad 'encoding' declaration";
                                          i4 = 36;
                                          i3 = i6;
                                          i6 = i2;
                                          j = n;
                                          i2 = i3;
                                          i3 = i4;
                                          break;
                                        }
                                        i1 = arrayOfChar[i3];
                                        if (i1 == j)
                                        {
                                          localObject2 = new String(arrayOfChar, i2, i3 - i2);
                                          if ((paramLineBufferedReader instanceof LineInputStreamReader)) {
                                            ((LineInputStreamReader)paramLineBufferedReader).setCharset((String)localObject2);
                                          }
                                          i2 = i3 + 1;
                                          for (;;)
                                          {
                                            i3 = i2;
                                            n = i1;
                                            if (i2 >= i4) {
                                              break;
                                            }
                                            i3 = i2;
                                            n = i1;
                                            if (!Character.isWhitespace(arrayOfChar[i2])) {
                                              break;
                                            }
                                            i2 += 1;
                                          }
                                        }
                                        i3 += 1;
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                    i2 = i3;
                    j = n;
                    if (i4 > i3 + 9)
                    {
                      i2 = i3;
                      j = n;
                      if (arrayOfChar[i3] == 's')
                      {
                        i2 = i3;
                        j = n;
                        if (arrayOfChar[(i3 + 1)] == 't')
                        {
                          i2 = i3;
                          j = n;
                          if (arrayOfChar[(i3 + 2)] == 'a')
                          {
                            i2 = i3;
                            j = n;
                            if (arrayOfChar[(i3 + 3)] == 'n')
                            {
                              i2 = i3;
                              j = n;
                              if (arrayOfChar[(i3 + 4)] == 'd')
                              {
                                i2 = i3;
                                j = n;
                                if (arrayOfChar[(i3 + 5)] == 'a')
                                {
                                  i2 = i3;
                                  j = n;
                                  if (arrayOfChar[(i3 + 6)] == 'l')
                                  {
                                    i2 = i3;
                                    j = n;
                                    if (arrayOfChar[(i3 + 7)] == 'o')
                                    {
                                      i2 = i3;
                                      j = n;
                                      if (arrayOfChar[(i3 + 8)] == 'n')
                                      {
                                        i2 = i3;
                                        j = n;
                                        if (arrayOfChar[(i3 + 9)] == 'e')
                                        {
                                          i3 += 10;
                                          for (j = arrayOfChar[i3];; j = arrayOfChar[i3])
                                          {
                                            i2 = i3;
                                            if (!Character.isWhitespace(j)) {
                                              break;
                                            }
                                            i3 += 1;
                                            i2 = i3;
                                            if (i3 >= i4) {
                                              break;
                                            }
                                          }
                                          if (j != 61)
                                          {
                                            localObject1 = "bad 'standalone' declaration";
                                            i4 = 36;
                                            i3 = i6;
                                            i6 = i2;
                                            i2 = i3;
                                            i3 = i4;
                                            break;
                                          }
                                          i3 = i2 + 1;
                                          for (j = arrayOfChar[i3];; j = arrayOfChar[i3])
                                          {
                                            i2 = i3;
                                            if (!Character.isWhitespace(j)) {
                                              break;
                                            }
                                            i3 += 1;
                                            i2 = i3;
                                            if (i3 >= i4) {
                                              break;
                                            }
                                          }
                                          if ((j != 39) && (j != 34))
                                          {
                                            localObject1 = "bad 'standalone' declaration";
                                            i4 = 36;
                                            i3 = i6;
                                            i6 = i2;
                                            i2 = i3;
                                            i3 = i4;
                                            break;
                                          }
                                          i2 += 1;
                                          i3 = i2;
                                          n = j;
                                          if (i3 == i4)
                                          {
                                            localObject1 = "bad 'standalone' declaration";
                                            i4 = 36;
                                            i3 = i6;
                                            i6 = i2;
                                            j = n;
                                            i2 = i3;
                                            i3 = i4;
                                            break;
                                          }
                                          n = arrayOfChar[i3];
                                          if (n == j) {
                                            if ((i3 != i2 + 3) || (arrayOfChar[i2] != 'y') || (arrayOfChar[(i2 + 1)] != 'e') || (arrayOfChar[(i2 + 2)] != 's')) {
                                              break label3529;
                                            }
                                          }
                                          label3529:
                                          while ((i3 == i2 + 2) && (arrayOfChar[i2] == 'n') && (arrayOfChar[(i2 + 1)] == 'o'))
                                          {
                                            i3 += 1;
                                            for (;;)
                                            {
                                              i2 = i3;
                                              j = n;
                                              if (i3 >= i4) {
                                                break;
                                              }
                                              i2 = i3;
                                              j = n;
                                              if (!Character.isWhitespace(arrayOfChar[i3])) {
                                                break;
                                              }
                                              i3 += 1;
                                            }
                                            i3 += 1;
                                            break;
                                          }
                                          localObject1 = "bad 'standalone' declaration";
                                          i4 = 36;
                                          i3 = i6;
                                          i6 = i2;
                                          j = n;
                                          i2 = i3;
                                          i3 = i4;
                                          break;
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                    n = j;
                    if (i4 == i2) {
                      break label3663;
                    }
                    localObject1 = "junk at end of xml declaration";
                    i3 = i2;
                    i4 = 36;
                    i6 = i2;
                    i2 = i3;
                    i3 = i4;
                    break;
                  }
                  localObject1 = "<?xml must be at start of file";
                  i3 = 36;
                  i2 = i6;
                  i6 = i16;
                  break;
                }
                paramXMLFilter.processingInstructionFromParser(arrayOfChar, i8, i12, i16, i4 - i16);
                int n = j;
                label3663:
                i7 = i9;
                i11 = -1;
                i4 = 1;
                i10 = i12;
                j = n;
                i18 = i14;
                localObject2 = localObject1;
                i2 = i6;
                i13 = i15;
                break label248;
              }
            }
            i7 = i8;
            i10 = i12;
            i11 = i16;
            i18 = i14;
            localObject2 = localObject1;
            i2 = i6;
            i4 = i3;
            i13 = i15;
            if (i6 >= i9) {
              break label248;
            }
            j = arrayOfChar[i6];
            i6 += 1;
          }
          label3752:
          i7 = i8;
          i10 = i16;
          i11 = i6;
          i18 = i14;
          localObject2 = localObject1;
          i2 = i12;
          i4 = i3;
          i13 = i15;
          if (i12 >= i9) {
            break label248;
          }
          j = arrayOfChar[i12];
          i12 += 1;
          i10 = i16;
        }
        for (;;)
        {
          if (j == 62)
          {
            i10 = i12 - 1 - i8;
            if ((i10 >= 4) && (arrayOfChar[i8] == '-') && (arrayOfChar[(i8 + 1)] == '-'))
            {
              i16 = i10;
              if (arrayOfChar[(i12 - 2)] != '-') {
                break label3752;
              }
              i16 = i10;
              if (arrayOfChar[(i12 - 3)] != '-') {
                break label3752;
              }
              paramLineBufferedReader.pos = i12;
              paramXMLFilter.commentFromParser(arrayOfChar, i8 + 2, i10 - 4);
            }
            for (;;)
            {
              i7 = i9;
              i4 = 1;
              i11 = i6;
              i18 = i14;
              localObject2 = localObject1;
              i2 = i12;
              i13 = i15;
              break;
              if ((i10 >= 6) && (arrayOfChar[i8] == '[') && (arrayOfChar[(i8 + 1)] == 'C') && (arrayOfChar[(i8 + 2)] == 'D') && (arrayOfChar[(i8 + 3)] == 'A') && (arrayOfChar[(i8 + 4)] == 'T') && (arrayOfChar[(i8 + 5)] == 'A') && (arrayOfChar[(i8 + 6)] == '['))
              {
                i16 = i10;
                if (arrayOfChar[(i12 - 2)] != ']') {
                  break label3752;
                }
                i16 = i10;
                if (arrayOfChar[(i12 - 3)] != ']') {
                  break label3752;
                }
                paramLineBufferedReader.pos = i12;
                paramXMLFilter.writeCDATA(arrayOfChar, i8 + 7, i12 - 10 - i8);
              }
            }
          }
          i16 = i10;
          if (i12 != i8 + 7) {
            break label3752;
          }
          i16 = i10;
          if (arrayOfChar[i8] != 'D') {
            break label3752;
          }
          i16 = i10;
          if (arrayOfChar[(i8 + 1)] != 'O') {
            break label3752;
          }
          i16 = i10;
          if (arrayOfChar[(i8 + 2)] != 'C') {
            break label3752;
          }
          i16 = i10;
          if (arrayOfChar[(i8 + 3)] != 'T') {
            break label3752;
          }
          i16 = i10;
          if (arrayOfChar[(i8 + 4)] != 'Y') {
            break label3752;
          }
          i16 = i10;
          if (arrayOfChar[(i8 + 5)] != 'P') {
            break label3752;
          }
          i16 = i10;
          if (j != 69) {
            break label3752;
          }
          i7 = i9;
          i4 = 15;
          i11 = i6;
          i18 = i14;
          localObject2 = localObject1;
          i2 = i12;
          i13 = i15;
          break label248;
          i3 = 17;
          i8 = i2 - 1;
          break;
          if (i6 < 0)
          {
            i7 = i2 - 1 - i8 << 1;
            i4 = 0;
            i6 = i2;
            i2 = i7;
          }
          label4271:
          label4433:
          int i5;
          for (;;)
          {
            if ((j == 39) || (j == 34)) {
              if (i4 == 0)
              {
                i16 = j;
                i15 = i2;
              }
            }
            do
            {
              do
              {
                for (;;)
                {
                  i7 = i8;
                  i10 = i12;
                  i11 = i15;
                  i18 = i14;
                  localObject2 = localObject1;
                  i2 = i6;
                  i4 = i3;
                  i13 = i16;
                  if (i6 >= i9) {
                    break;
                  }
                  j = arrayOfChar[i6];
                  i6 += 1;
                  i2 = i15;
                  i4 = i16;
                  break label4271;
                  i15 = i2;
                  i16 = i4;
                  if (i4 == j)
                  {
                    i17 = 0;
                    i15 = i2;
                    continue;
                    i15 = i2;
                    i17 = i4;
                    if (i4 == 0) {
                      if (j == 91)
                      {
                        i15 = i2 | 0x1;
                        i17 = i4;
                      }
                      else
                      {
                        if (j != 93) {
                          break label4433;
                        }
                        i15 = i2 & 0xFFFFFFFE;
                        i17 = i4;
                      }
                    }
                  }
                }
                i15 = i2;
                i17 = i4;
              } while (j != 62);
              i15 = i2;
              i17 = i4;
            } while ((i2 & 0x1) != 0);
            paramLineBufferedReader.pos = i6;
            i2 = (i2 >> 1) + i8;
            paramXMLFilter.emitDoctypeDecl(arrayOfChar, i8, i12, i2, i6 - 1 - i2);
            i13 = 60;
            i7 = i9;
            i11 = -1;
            i5 = 1;
            i10 = i12;
            i18 = i14;
            localObject2 = localObject1;
            i2 = i6;
            break label248;
            i13 = 60;
            i14 = 14;
            if (j == 47)
            {
              paramLineBufferedReader.pos = i2;
              paramXMLFilter.emitEndAttributes();
              paramXMLFilter.emitEndElement(null, 0, 0);
              i5 = 27;
              i7 = i8;
              i10 = i12;
              i11 = i6;
              i18 = i14;
              localObject2 = localObject1;
              break label248;
            }
            if (j == 62)
            {
              paramLineBufferedReader.pos = i2;
              paramXMLFilter.emitEndAttributes();
              i5 = 1;
              i7 = i8;
              i10 = i12;
              i11 = i6;
              i18 = i14;
              localObject2 = localObject1;
              break label248;
            }
            i8 = i2 - 1;
            i3 = 9;
            i15 = i13;
            break;
            if ((j == 32) || (j == 9) || (j == 13) || (j == 10) || (j == 133)) {
              break label220;
            }
            if (j == 8232)
            {
              i7 = i8;
              i10 = i12;
              i11 = i6;
              i18 = i14;
              localObject2 = localObject1;
              i5 = i3;
              i13 = i15;
              break label248;
            }
            paramLineBufferedReader.pos = (i2 - i12);
            paramXMLFilter.emitStartAttribute(arrayOfChar, i8, i12);
            i7 = i9;
            if (j == 61)
            {
              i5 = 11;
              i10 = i12;
              i11 = i6;
              i18 = i14;
              localObject2 = localObject1;
              i13 = i15;
              break label248;
            }
            paramXMLFilter.emitEndAttributes();
            localObject1 = "missing or misplaced '=' after attribute name";
            i3 = 36;
            i8 = i7;
            break;
            if ((j == 39) || (j == 34))
            {
              i13 = j;
              i18 = 12;
              i5 = 1;
              i7 = i8;
              i10 = i12;
              i11 = i6;
              localObject2 = localObject1;
              break label248;
            }
            if ((j == 32) || (j == 9) || (j == 13) || (j == 10) || (j == 133)) {
              break label220;
            }
            if (j == 8232)
            {
              i7 = i8;
              i10 = i12;
              i11 = i6;
              i18 = i14;
              localObject2 = localObject1;
              i5 = i3;
              i13 = i15;
              break label248;
            }
            localObject1 = "missing or unquoted attribute value";
            i3 = 36;
            break;
            i8 = i2 - 1;
            i3 = 5;
            break;
            paramLineBufferedReader.pos = i2;
            paramXMLFilter.emitEndElement(arrayOfChar, i8, i12);
            i8 = i9;
            i3 = 29;
            break;
            if (j != 62)
            {
              localObject1 = "missing '>'";
              i3 = 36;
              break;
            }
            i5 = 1;
            i7 = i8;
            i10 = i12;
            i11 = i6;
            i18 = i14;
            localObject2 = localObject1;
            i13 = i15;
            break label248;
            i3 = i2 - i7;
            if (i3 > 0) {}
            label5157:
            for (;;)
            {
              try
              {
                paramLineBufferedReader.pos = i7;
                paramLineBufferedReader.mark(i3 + 1);
                paramLineBufferedReader.pos = i2;
                if (paramLineBufferedReader.read() < 0)
                {
                  if (i5 == 1) {
                    break label5227;
                  }
                  if (i5 != 28) {
                    break label5228;
                  }
                  break label5227;
                }
                if (i3 > 0)
                {
                  paramLineBufferedReader.reset();
                  paramLineBufferedReader.skip(i3);
                  i2 = paramLineBufferedReader.pos;
                  arrayOfChar = paramLineBufferedReader.buffer;
                  i9 = paramLineBufferedReader.limit;
                  if (i3 <= 0) {
                    break label5157;
                  }
                  i8 = i2 - i3;
                  j = arrayOfChar[i2];
                  i2 += 1;
                  i12 = i10;
                  i6 = i11;
                  i14 = i18;
                  localObject1 = localObject2;
                  i3 = i5;
                  i15 = i13;
                  break;
                }
                paramLineBufferedReader.unread_quick();
                continue;
                i8 = i9;
              }
              catch (IOException paramLineBufferedReader)
              {
                throw new RuntimeException(paramLineBufferedReader.getMessage());
              }
            }
            i5 = i2;
            i2 = i6;
            i6 = i5;
            i5 = i15;
          }
          int i17 = i6;
          i6 = i2;
          break label1981;
          label5194:
          i2 = i12;
          i12 = i5;
          break;
          i10 = i12;
          i12 = i2;
        }
        i10 = i12;
        i12 = i2;
      }
      label5227:
      return;
      label5228:
      i3 = 37;
      i8 = i7;
      i12 = i10;
      i6 = i11;
      i14 = i18;
      localObject1 = localObject2;
      i15 = i13;
    }
  }
  
  public static void parse(InputStream paramInputStream, Object paramObject, SourceMessages paramSourceMessages, Consumer paramConsumer)
    throws IOException
  {
    paramInputStream = XMLStreamReader(paramInputStream);
    if (paramObject != null) {
      paramInputStream.setName(paramObject);
    }
    parse(paramInputStream, paramSourceMessages, paramConsumer);
    paramInputStream.close();
  }
  
  public static void parse(Object paramObject, SourceMessages paramSourceMessages, Consumer paramConsumer)
    throws IOException
  {
    parse(Path.openInputStream(paramObject), paramObject, paramSourceMessages, paramConsumer);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\xml\XMLParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */