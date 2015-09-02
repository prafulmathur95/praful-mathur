package gnu.kawa.functions;

import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.math.IntNum;
import gnu.text.CaseConvertFormat;
import gnu.text.Char;
import gnu.text.CompoundFormat;
import gnu.text.FlushFormat;
import gnu.text.LiteralFormat;
import gnu.text.ReportFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Stack;
import java.util.Vector;

public class LispFormat
  extends CompoundFormat
{
  public static final String paramFromCount = "<from count>";
  public static final String paramFromList = "<from list>";
  public static final String paramUnspecified = "<unspecified>";
  
  public LispFormat(String paramString)
    throws ParseException
  {
    this(paramString.toCharArray());
  }
  
  public LispFormat(char[] paramArrayOfChar)
    throws ParseException
  {
    this(paramArrayOfChar, 0, paramArrayOfChar.length);
  }
  
  public LispFormat(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws ParseException
  {
    super(null, 0);
    int k = -1;
    int j = 0;
    StringBuffer localStringBuffer = new StringBuffer(100);
    Stack localStack = new Stack();
    int i1 = paramInt1 + paramInt2;
    int i = paramInt1;
    paramInt1 = k;
    paramInt2 = j;
    for (;;)
    {
      if (((i >= i1) || (paramArrayOfChar[i] == '~')) && (localStringBuffer.length() > 0))
      {
        localStack.push(new LiteralFormat(localStringBuffer));
        localStringBuffer.setLength(0);
      }
      if (i >= i1)
      {
        if (i <= i1) {
          break label2948;
        }
        throw new IndexOutOfBoundsException();
      }
      k = i + 1;
      c = paramArrayOfChar[i];
      if (c == '~') {
        break;
      }
      localStringBuffer.append(c);
      i = k;
    }
    j = localStack.size();
    i = k + 1;
    char c = paramArrayOfChar[k];
    label166:
    if (c == '#')
    {
      localStack.push("<from count>");
      c = paramArrayOfChar[i];
      i += 1;
    }
    label193:
    label200:
    boolean bool2;
    boolean bool1;
    for (;;)
    {
      if (c != ',')
      {
        bool2 = false;
        bool1 = false;
        k = i;
        while (c == ':')
        {
          bool2 = true;
          label220:
          c = paramArrayOfChar[k];
          k += 1;
        }
        if ((c == 'v') || (c == 'V'))
        {
          localStack.push("<from list>");
          c = paramArrayOfChar[i];
          i += 1;
        }
        else if ((c == '-') || (Character.digit(c, 10) >= 0))
        {
          if (c == '-')
          {
            bool1 = true;
            label299:
            if (!bool1) {
              break label3008;
            }
            k = i + 1;
            c = paramArrayOfChar[i];
            i = k;
          }
        }
      }
    }
    label327:
    label418:
    label900:
    label987:
    label1250:
    label1349:
    label2119:
    label2753:
    label2782:
    label2905:
    label2948:
    label3002:
    label3008:
    for (;;)
    {
      k = 0;
      int m = i;
      int n = Character.digit(c, 10);
      if (n < 0)
      {
        if (m - i >= 8) {
          break label418;
        }
        i = k;
        if (bool1) {
          i = -k;
        }
      }
      for (Object localObject = IntNum.make(i);; localObject = IntNum.valueOf(paramArrayOfChar, i, m - i, 10, bool1))
      {
        localStack.push(localObject);
        i = m;
        break;
        bool1 = false;
        break label299;
        k = k * 10 + n;
        c = paramArrayOfChar[m];
        m += 1;
        break label327;
      }
      if (c == '\'')
      {
        k = i + 1;
        localStack.push(Char.make(paramArrayOfChar[i]));
        i = k + 1;
        c = paramArrayOfChar[k];
        break label193;
      }
      if (c == ',')
      {
        localStack.push("<unspecified>");
        break label193;
        c = paramArrayOfChar[i];
        i += 1;
        break label166;
        if (c == '@')
        {
          bool1 = true;
          break label220;
        }
        c = Character.toUpperCase(c);
        i = localStack.size() - j;
        int i2;
        int i3;
        switch (c)
        {
        default: 
          throw new ParseException("unrecognized format specifier ~" + c, k);
        case 'B': 
        case 'D': 
        case 'O': 
        case 'R': 
        case 'X': 
          m = j;
          if (c == 'R')
          {
            i = getParam(localStack, m);
            m += 1;
            i2 = getParam(localStack, m);
            i3 = getParam(localStack, m + 1);
            int i4 = getParam(localStack, m + 2);
            int i5 = getParam(localStack, m + 3);
            m = 0;
            if (bool2) {
              m = 0x0 | 0x1;
            }
            n = m;
            if (bool1) {
              n = m | 0x2;
            }
            localObject = IntegerFormat.getInstance(i, i2, i3, i4, i5, n);
          }
          break;
        }
        for (;;)
        {
          localStack.setSize(j);
          localStack.push(localObject);
          i = k;
          break;
          if (c == 'D')
          {
            i = 10;
            break label900;
          }
          if (c == 'O')
          {
            i = 8;
            break label900;
          }
          if (c == 'X')
          {
            i = 16;
            break label900;
          }
          i = 2;
          break label900;
          localObject = LispPluralFormat.getInstance(bool2, bool1);
          continue;
          localObject = new LispRealFormat();
          ((LispRealFormat)localObject).op = c;
          ((LispRealFormat)localObject).arg1 = getParam(localStack, j);
          ((LispRealFormat)localObject).arg2 = getParam(localStack, j + 1);
          ((LispRealFormat)localObject).arg3 = getParam(localStack, j + 2);
          ((LispRealFormat)localObject).arg4 = getParam(localStack, j + 3);
          if (c != '$')
          {
            ((LispRealFormat)localObject).arg5 = getParam(localStack, j + 4);
            if ((c == 'E') || (c == 'G'))
            {
              ((LispRealFormat)localObject).arg6 = getParam(localStack, j + 5);
              ((LispRealFormat)localObject).arg7 = getParam(localStack, j + 6);
            }
          }
          ((LispRealFormat)localObject).showPlus = bool1;
          ((LispRealFormat)localObject).internalPad = bool2;
          if (((LispRealFormat)localObject).argsUsed == 0)
          {
            localObject = ((LispRealFormat)localObject).resolve(null, 0);
          }
          else
          {
            continue;
            if (c != 'A')
            {
              bool2 = true;
              localObject = ObjectFormat.getInstance(bool2);
              if (i <= 0) {
                break label3002;
              }
              m = getParam(localStack, j);
              n = getParam(localStack, j + 1);
              i2 = getParam(localStack, j + 2);
              i3 = getParam(localStack, j + 3);
              localObject = (ReportFormat)localObject;
              if (!bool1) {
                break label1349;
              }
            }
            for (i = 0;; i = 100)
            {
              localObject = new LispObjectFormat((ReportFormat)localObject, m, n, i2, i3, i);
              break;
              bool2 = false;
              break label1250;
            }
            if (i > 0) {}
            for (i = getParam(localStack, j);; i = -1610612736)
            {
              localObject = LispCharacterFormat.getInstance(i, 1, bool1, bool2);
              break;
            }
            localObject = new LispRepositionFormat(getParam(localStack, j), bool2, bool1);
            continue;
            if (bool2) {
              if (bool1) {
                c = 'U';
              }
            }
            for (;;)
            {
              localObject = new CaseConvertFormat(null, c);
              localStack.setSize(j);
              localStack.push(localObject);
              localStack.push(IntNum.make(paramInt1));
              paramInt1 = j;
              i = k;
              break;
              c = 'C';
              continue;
              if (bool1) {
                c = 'T';
              } else {
                c = 'L';
              }
            }
            if ((paramInt1 < 0) || (!(localStack.elementAt(paramInt1) instanceof CaseConvertFormat))) {
              throw new ParseException("saw ~) without matching ~(", k);
            }
            ((CaseConvertFormat)localStack.elementAt(paramInt1)).setBaseFormat(popFormats(localStack, paramInt1 + 2, j));
            paramInt1 = ((IntNum)localStack.pop()).intValue();
            i = k;
            break;
            localObject = new LispIterationFormat();
            ((LispIterationFormat)localObject).seenAt = bool1;
            ((LispIterationFormat)localObject).maxIterations = 1;
            ((LispIterationFormat)localObject).atLeastOnce = true;
            continue;
            localObject = new LispIterationFormat();
            ((LispIterationFormat)localObject).seenAt = bool1;
            ((LispIterationFormat)localObject).seenColon = bool2;
            ((LispIterationFormat)localObject).maxIterations = getParam(localStack, j);
            localStack.setSize(j);
            localStack.push(localObject);
            localStack.push(IntNum.make(paramInt1));
            paramInt1 = j;
            i = k;
            break;
            if ((paramInt1 < 0) || (!(localStack.elementAt(paramInt1) instanceof LispIterationFormat))) {
              throw new ParseException("saw ~} without matching ~{", k);
            }
            localObject = (LispIterationFormat)localStack.elementAt(paramInt1);
            ((LispIterationFormat)localObject).atLeastOnce = bool2;
            if (j > paramInt1 + 2) {
              ((LispIterationFormat)localObject).body = popFormats(localStack, paramInt1 + 2, j);
            }
            paramInt1 = ((IntNum)localStack.pop()).intValue();
            i = k;
            break;
            localObject = new LispPrettyFormat();
            ((LispPrettyFormat)localObject).seenAt = bool1;
            if (bool2) {
              ((LispPrettyFormat)localObject).prefix = "(";
            }
            for (((LispPrettyFormat)localObject).suffix = ")";; ((LispPrettyFormat)localObject).suffix = "")
            {
              localStack.setSize(j);
              localStack.push(localObject);
              localStack.push(IntNum.make(paramInt1));
              localStack.push(IntNum.make(paramInt2));
              paramInt1 = j;
              paramInt2 = 0;
              i = k;
              break;
              ((LispPrettyFormat)localObject).prefix = "";
            }
            if ((paramInt1 < 0) || (!(localStack.elementAt(paramInt1) instanceof LispPrettyFormat))) {
              throw new ParseException("saw ~> without matching ~<", k);
            }
            localStack.push(popFormats(localStack, paramInt1 + 3 + paramInt2, j));
            localObject = (LispPrettyFormat)localStack.elementAt(paramInt1);
            ((LispPrettyFormat)localObject).segments = getFormats(localStack, paramInt1 + 3, localStack.size());
            localStack.setSize(paramInt1 + 3);
            ((IntNum)localStack.pop()).intValue();
            j = ((IntNum)localStack.pop()).intValue();
            if (bool2)
            {
              m = ((LispPrettyFormat)localObject).segments.length;
              if (m > 3) {
                throw new ParseException("too many segments in Logical Block format", k);
              }
              if (m >= 2)
              {
                if (!(localObject.segments[0] instanceof LiteralFormat)) {
                  throw new ParseException("prefix segment is not literal", k);
                }
                ((LispPrettyFormat)localObject).prefix = ((LiteralFormat)localObject.segments[0]).content();
              }
              for (((LispPrettyFormat)localObject).body = localObject.segments[1];; ((LispPrettyFormat)localObject).body = localObject.segments[0])
              {
                i = k;
                paramInt1 = j;
                if (m < 3) {
                  break;
                }
                if ((localObject.segments[2] instanceof LiteralFormat)) {
                  break label2119;
                }
                throw new ParseException("suffix segment is not literal", k);
              }
              ((LispPrettyFormat)localObject).suffix = ((LiteralFormat)localObject.segments[2]).content();
              i = k;
              paramInt1 = j;
              break;
            }
            throw new ParseException("not implemented: justfication i.e. ~<...~>", k);
            localObject = new LispChoiceFormat();
            ((LispChoiceFormat)localObject).param = getParam(localStack, j);
            if (((LispChoiceFormat)localObject).param == -1073741824) {
              ((LispChoiceFormat)localObject).param = -1610612736;
            }
            if (bool2) {
              ((LispChoiceFormat)localObject).testBoolean = true;
            }
            if (bool1) {
              ((LispChoiceFormat)localObject).skipIfFalse = true;
            }
            localStack.setSize(j);
            localStack.push(localObject);
            localStack.push(IntNum.make(paramInt1));
            localStack.push(IntNum.make(paramInt2));
            paramInt1 = j;
            paramInt2 = 0;
            i = k;
            break;
            if (paramInt1 >= 0)
            {
              if ((localStack.elementAt(paramInt1) instanceof LispChoiceFormat))
              {
                localObject = (LispChoiceFormat)localStack.elementAt(paramInt1);
                if (bool2) {
                  ((LispChoiceFormat)localObject).lastIsDefault = true;
                }
                localStack.push(popFormats(localStack, paramInt1 + 3 + paramInt2, j));
                paramInt2 += 1;
                i = k;
                break;
              }
              if ((localStack.elementAt(paramInt1) instanceof LispPrettyFormat))
              {
                localObject = (LispPrettyFormat)localStack.elementAt(paramInt1);
                if (bool1) {
                  ((LispPrettyFormat)localObject).perLine = true;
                }
                localStack.push(popFormats(localStack, paramInt1 + 3 + paramInt2, j));
                paramInt2 += 1;
                i = k;
                break;
              }
            }
            throw new ParseException("saw ~; without matching ~[ or ~<", k);
            if ((paramInt1 < 0) || (!(localStack.elementAt(paramInt1) instanceof LispChoiceFormat))) {
              throw new ParseException("saw ~] without matching ~[", k);
            }
            localStack.push(popFormats(localStack, paramInt1 + 3 + paramInt2, j));
            ((LispChoiceFormat)localStack.elementAt(paramInt1)).choices = getFormats(localStack, paramInt1 + 3, localStack.size());
            localStack.setSize(paramInt1 + 3);
            paramInt2 = ((IntNum)localStack.pop()).intValue();
            paramInt1 = ((IntNum)localStack.pop()).intValue();
            i = k;
            break;
            localObject = new LispEscapeFormat(getParam(localStack, j), getParam(localStack, j + 1), getParam(localStack, j + 2));
            continue;
            if (bool1) {
              localStringBuffer.append(c);
            }
            i = k;
            if (bool2) {
              break;
            }
            for (;;)
            {
              i = k;
              if (k >= i1) {
                break;
              }
              i = k + 1;
              if (!Character.isWhitespace(paramArrayOfChar[k]))
              {
                i -= 1;
                break;
                localObject = FlushFormat.getInstance();
                break label987;
                localObject = new LispTabulateFormat(getParam(localStack, j), getParam(localStack, j + 1), getParam(localStack, j + 2), bool1);
                break label987;
                localObject = new LispFreshlineFormat(getParam(localStack, j));
                break label987;
                m = getParam(localStack, j);
                i = m;
                if (m == -1073741824) {
                  i = 0;
                }
                localObject = LispIndentFormat.getInstance(i, bool2);
                break label987;
                i = getParam(localStack, j);
                m = i;
                if (i == -1073741824) {
                  m = 1;
                }
                if ((bool2) && (bool1))
                {
                  if ((!bool1) || (!bool2)) {
                    break label2782;
                  }
                  i = 82;
                }
                for (;;)
                {
                  localObject = LispNewlineFormat.getInstance(m, i);
                  break;
                  break label2753;
                  if (bool1) {
                    i = 77;
                  } else if (bool2) {
                    i = 70;
                  } else {
                    i = 78;
                  }
                }
                if (i == 0)
                {
                  localStringBuffer.append(c);
                  i = k;
                  break;
                }
                i = getParam(localStack, j);
                m = i;
                if (i == -1073741824) {
                  m = 1;
                }
                n = getParam(localStack, j + 1);
                i = n;
                if (n == -1073741824) {
                  if (c != '|') {
                    break label2905;
                  }
                }
                for (i = 12;; i = 126)
                {
                  localObject = LispCharacterFormat.getInstance(i, m, false, false);
                  break;
                }
                m = getParam(localStack, j);
                i = m;
                if (m == -1073741824) {
                  i = 1;
                }
                localObject = LispNewlineFormat.getInstance(i, 76);
                break label987;
                if (paramInt1 >= 0) {
                  throw new ParseException("missing ~] or ~}", i);
                }
                this.length = localStack.size();
                this.formats = new Format[this.length];
                localStack.copyInto(this.formats);
                return;
              }
              k = i;
            }
          }
        }
      }
      break label200;
    }
  }
  
  public static Object[] asArray(Object paramObject)
  {
    if ((paramObject instanceof Object[])) {
      return (Object[])paramObject;
    }
    if (!(paramObject instanceof Sequence)) {
      return null;
    }
    int k = ((Sequence)paramObject).size();
    Object[] arrayOfObject = new Object[k];
    int i = 0;
    while ((paramObject instanceof Pair))
    {
      paramObject = (Pair)paramObject;
      arrayOfObject[i] = ((Pair)paramObject).getCar();
      paramObject = ((Pair)paramObject).getCdr();
      i += 1;
    }
    if (i < k)
    {
      if (!(paramObject instanceof Sequence)) {
        return null;
      }
      paramObject = (Sequence)paramObject;
      int j = i;
      while (j < k)
      {
        arrayOfObject[j] = ((Sequence)paramObject).get(i + j);
        j += 1;
      }
    }
    return arrayOfObject;
  }
  
  static Format[] getFormats(Vector paramVector, int paramInt1, int paramInt2)
  {
    Format[] arrayOfFormat = new Format[paramInt2 - paramInt1];
    int i = paramInt1;
    while (i < paramInt2)
    {
      arrayOfFormat[(i - paramInt1)] = ((Format)paramVector.elementAt(i));
      i += 1;
    }
    return arrayOfFormat;
  }
  
  public static int getParam(Vector paramVector, int paramInt)
  {
    if (paramInt >= paramVector.size()) {}
    do
    {
      return -1073741824;
      paramVector = paramVector.elementAt(paramInt);
      if (paramVector == "<from list>") {
        return -1610612736;
      }
      if (paramVector == "<from count>") {
        return -1342177280;
      }
    } while (paramVector == "<unspecified>");
    return getParam(paramVector, -1073741824);
  }
  
  static Format popFormats(Vector paramVector, int paramInt1, int paramInt2)
  {
    if (paramInt2 == paramInt1 + 1) {}
    for (Object localObject = (Format)paramVector.elementAt(paramInt1);; localObject = new CompoundFormat(getFormats(paramVector, paramInt1, paramInt2)))
    {
      paramVector.setSize(paramInt1);
      return (Format)localObject;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\LispFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */