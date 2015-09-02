package gnu.kawa.functions;

import gnu.expr.Keyword;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.AbstractFormat;
import gnu.lists.CharSeq;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.ConsumerWriter;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.SimpleVector;
import gnu.lists.Strings;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.math.RatNum;
import gnu.text.Char;
import gnu.text.Printable;
import gnu.xml.XMLPrinter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DisplayFormat
  extends AbstractFormat
{
  public static final ThreadLocation outBase = new ThreadLocation("out-base");
  public static final ThreadLocation outRadix = new ThreadLocation("out-radix");
  static Pattern r5rsIdentifierMinusInteriorColons = Pattern.compile("(([a-zA-Z]|[!$%&*/:<=>?^_~])([a-zA-Z]|[!$%&*/<=>?^_~]|[0-9]|([-+.@]))*[:]?)|([-+]|[.][.][.])");
  char language;
  boolean readable;
  
  static
  {
    outBase.setGlobal(IntNum.ten());
  }
  
  public DisplayFormat(boolean paramBoolean, char paramChar)
  {
    this.readable = paramBoolean;
    this.language = paramChar;
  }
  
  public static DisplayFormat getCommonLispFormat(boolean paramBoolean)
  {
    return new DisplayFormat(paramBoolean, 'C');
  }
  
  public static DisplayFormat getEmacsLispFormat(boolean paramBoolean)
  {
    return new DisplayFormat(paramBoolean, 'E');
  }
  
  public static DisplayFormat getSchemeFormat(boolean paramBoolean)
  {
    return new DisplayFormat(paramBoolean, 'S');
  }
  
  public boolean getReadableOutput()
  {
    return this.readable;
  }
  
  int write(gnu.lists.Array paramArray, int paramInt1, int paramInt2, Consumer paramConsumer)
  {
    int m = paramArray.rank();
    int j = 0;
    int k = 0;
    String str;
    label43:
    int i1;
    int i;
    if (paramInt2 > 0)
    {
      str = "(";
      if (!(paramConsumer instanceof OutPort)) {
        break label193;
      }
      ((OutPort)paramConsumer).startLogicalBlock(str, false, ")");
      if (m <= 0) {
        break label220;
      }
      int n = paramArray.getSize(paramInt2);
      i1 = paramInt2 + 1;
      paramInt2 = 0;
      i = paramInt1;
      paramInt1 = k;
      label70:
      j = paramInt1;
      if (paramInt2 >= n) {
        break label220;
      }
      if (paramInt2 > 0)
      {
        write(" ", paramConsumer);
        if ((paramConsumer instanceof OutPort)) {
          ((OutPort)paramConsumer).writeBreakFill();
        }
      }
      if (i1 != m) {
        break label204;
      }
      writeObject(paramArray.getRowMajor(i), paramConsumer);
    }
    label193:
    label204:
    for (j = 1;; j = write(paramArray, i, i1, paramConsumer))
    {
      i += j;
      paramInt1 += j;
      paramInt2 += 1;
      break label70;
      if (m == 1)
      {
        str = "#(";
        break;
      }
      str = "#" + m + "a(";
      break;
      write(str, paramConsumer);
      break label43;
    }
    label220:
    if ((paramConsumer instanceof OutPort))
    {
      ((OutPort)paramConsumer).endLogicalBlock(")");
      return j;
    }
    write(")", paramConsumer);
    return j;
  }
  
  public void write(int paramInt, Consumer paramConsumer)
  {
    if (!getReadableOutput())
    {
      Char.print(paramInt, paramConsumer);
      return;
    }
    if ((this.language == 'E') && (paramInt > 32))
    {
      paramConsumer.write(63);
      Char.print(paramInt, paramConsumer);
      return;
    }
    write(Char.toScmReadableString(paramInt), paramConsumer);
  }
  
  public void writeBoolean(boolean paramBoolean, Consumer paramConsumer)
  {
    String str;
    if (this.language == 'S') {
      if (paramBoolean) {
        str = "#t";
      }
    }
    for (;;)
    {
      write(str, paramConsumer);
      return;
      str = "#f";
      continue;
      if (paramBoolean) {
        str = "t";
      } else {
        str = "nil";
      }
    }
  }
  
  public void writeList(LList paramLList, OutPort paramOutPort)
  {
    Object localObject = paramLList;
    paramOutPort.startLogicalBlock("(", false, ")");
    while ((localObject instanceof Pair))
    {
      if (localObject != paramLList) {
        paramOutPort.writeSpaceFill();
      }
      localObject = (Pair)localObject;
      writeObject(((Pair)localObject).getCar(), paramOutPort);
      localObject = ((Pair)localObject).getCdr();
    }
    if (localObject != LList.Empty)
    {
      paramOutPort.writeSpaceFill();
      paramOutPort.write(". ");
      writeObject(LList.checkNonList(localObject), paramOutPort);
    }
    paramOutPort.endLogicalBlock(")");
  }
  
  public void writeObject(Object paramObject, Consumer paramConsumer)
  {
    int j = 0;
    int i = j;
    if ((paramConsumer instanceof OutPort))
    {
      i = j;
      if (!(paramObject instanceof UntypedAtomic))
      {
        i = j;
        if (!(paramObject instanceof Values)) {
          if (!getReadableOutput())
          {
            i = j;
            if (!(paramObject instanceof Char))
            {
              i = j;
              if (!(paramObject instanceof CharSequence))
              {
                i = j;
                if ((paramObject instanceof Character)) {}
              }
            }
          }
          else
          {
            ((OutPort)paramConsumer).writeWordStart();
            i = 1;
          }
        }
      }
    }
    writeObjectRaw(paramObject, paramConsumer);
    if (i != 0) {
      ((OutPort)paramConsumer).writeWordEnd();
    }
  }
  
  public void writeObjectRaw(Object paramObject, Consumer paramConsumer)
  {
    if ((paramObject instanceof Boolean)) {
      writeBoolean(((Boolean)paramObject).booleanValue(), paramConsumer);
    }
    Object localObject1;
    int j;
    int i;
    for (;;)
    {
      return;
      if ((paramObject instanceof Char))
      {
        write(((Char)paramObject).intValue(), paramConsumer);
        return;
      }
      if ((paramObject instanceof Character))
      {
        write(((Character)paramObject).charValue(), paramConsumer);
        return;
      }
      if ((paramObject instanceof Symbol))
      {
        paramObject = (Symbol)paramObject;
        if (((Symbol)paramObject).getNamespace() == XmlNamespace.HTML)
        {
          write("html:", paramConsumer);
          write(((Symbol)paramObject).getLocalPart(), paramConsumer);
          return;
        }
        writeSymbol((Symbol)paramObject, paramConsumer, this.readable);
        return;
      }
      if (((paramObject instanceof URI)) && (getReadableOutput()) && ((paramConsumer instanceof PrintWriter)))
      {
        write("#,(URI ", paramConsumer);
        Strings.printQuoted(paramObject.toString(), (PrintWriter)paramConsumer, 1);
        paramConsumer.write(41);
        return;
      }
      if (!(paramObject instanceof CharSequence)) {
        break;
      }
      localObject1 = (CharSequence)paramObject;
      if ((getReadableOutput()) && ((paramConsumer instanceof PrintWriter)))
      {
        Strings.printQuoted((CharSequence)localObject1, (PrintWriter)paramConsumer, 1);
        return;
      }
      if ((paramObject instanceof String))
      {
        paramConsumer.write((String)paramObject);
        return;
      }
      if ((paramObject instanceof CharSeq))
      {
        paramObject = (CharSeq)paramObject;
        ((CharSeq)paramObject).consume(0, ((CharSeq)paramObject).size(), paramConsumer);
        return;
      }
      j = ((CharSequence)localObject1).length();
      i = 0;
      while (i < j)
      {
        paramConsumer.write(((CharSequence)localObject1).charAt(i));
        i += 1;
      }
    }
    if (((paramObject instanceof LList)) && ((paramConsumer instanceof OutPort)))
    {
      writeList((LList)paramObject, (OutPort)paramConsumer);
      return;
    }
    Object localObject2;
    if ((paramObject instanceof SimpleVector))
    {
      localObject2 = (SimpleVector)paramObject;
      paramObject = ((SimpleVector)localObject2).getTag();
      if (this.language == 'E')
      {
        paramObject = "[";
        localObject1 = "]";
        if (!(paramConsumer instanceof OutPort)) {
          break label467;
        }
        ((OutPort)paramConsumer).startLogicalBlock((String)paramObject, false, (String)localObject1);
        label361:
        j = ((SimpleVector)localObject2).size();
        i = 0;
      }
      for (;;)
      {
        if (i < j << 1)
        {
          if ((i > 0) && ((paramConsumer instanceof OutPort))) {
            ((OutPort)paramConsumer).writeSpaceFill();
          }
          if (((SimpleVector)localObject2).consumeNext(i, paramConsumer)) {}
        }
        else
        {
          if (!(paramConsumer instanceof OutPort)) {
            break label485;
          }
          ((OutPort)paramConsumer).endLogicalBlock((String)localObject1);
          return;
          if (paramObject == null) {}
          for (paramObject = "#(";; paramObject = "#" + (String)paramObject + "(")
          {
            localObject1 = ")";
            break;
          }
          label467:
          write((String)paramObject, paramConsumer);
          break label361;
        }
        i += 2;
      }
      label485:
      write((String)localObject1, paramConsumer);
      return;
    }
    if ((paramObject instanceof gnu.lists.Array))
    {
      write((gnu.lists.Array)paramObject, 0, 0, paramConsumer);
      return;
    }
    if ((paramObject instanceof KNode))
    {
      if (getReadableOutput()) {
        write("#", paramConsumer);
      }
      if ((paramConsumer instanceof Writer)) {}
      for (paramConsumer = (Writer)paramConsumer;; paramConsumer = new ConsumerWriter(paramConsumer))
      {
        paramConsumer = new XMLPrinter(paramConsumer);
        paramConsumer.writeObject(paramObject);
        paramConsumer.closeThis();
        return;
      }
    }
    if ((paramObject == Values.empty) && (getReadableOutput()))
    {
      write("#!void", paramConsumer);
      return;
    }
    if ((paramObject instanceof Consumable))
    {
      ((Consumable)paramObject).consume(paramConsumer);
      return;
    }
    if ((paramObject instanceof Printable))
    {
      ((Printable)paramObject).print(paramConsumer);
      return;
    }
    if ((paramObject instanceof RatNum))
    {
      i = 10;
      int k = 0;
      localObject1 = outBase.get(null);
      localObject2 = outRadix.get(null);
      j = k;
      if (localObject2 != null) {
        if (localObject2 != Boolean.TRUE)
        {
          j = k;
          if (!"yes".equals(localObject2.toString())) {}
        }
        else
        {
          j = 1;
        }
      }
      if ((localObject1 instanceof Number))
      {
        i = ((IntNum)localObject1).intValue();
        label720:
        localObject2 = ((RatNum)paramObject).toString(i);
        if (j != 0)
        {
          if (i != 16) {
            break label802;
          }
          write("#x", paramConsumer);
        }
      }
      for (;;)
      {
        write((String)localObject2, paramConsumer);
        if ((j == 0) || (i != 10) || (!(paramObject instanceof IntNum))) {
          break;
        }
        write(".", paramConsumer);
        return;
        if (localObject1 == null) {
          break label720;
        }
        i = Integer.parseInt(localObject1.toString());
        break label720;
        label802:
        if (i == 8) {
          write("#o", paramConsumer);
        } else if (i == 2) {
          write("#b", paramConsumer);
        } else if ((i != 10) || (!(paramObject instanceof IntNum))) {
          write("#" + localObject1 + "r", paramConsumer);
        }
      }
    }
    if (((paramObject instanceof Enum)) && (getReadableOutput()))
    {
      write(paramObject.getClass().getName(), paramConsumer);
      write(":", paramConsumer);
      write(((Enum)paramObject).name(), paramConsumer);
      return;
    }
    if (paramObject == null) {}
    for (paramObject = null; paramObject == null; paramObject = paramObject.toString())
    {
      write("#!null", paramConsumer);
      return;
      if (paramObject.getClass().isArray())
      {
        j = java.lang.reflect.Array.getLength(paramObject);
        if ((paramConsumer instanceof OutPort)) {
          ((OutPort)paramConsumer).startLogicalBlock("[", false, "]");
        }
        for (;;)
        {
          i = 0;
          while (i < j)
          {
            if (i > 0)
            {
              write(" ", paramConsumer);
              if ((paramConsumer instanceof OutPort)) {
                ((OutPort)paramConsumer).writeBreakFill();
              }
            }
            writeObject(java.lang.reflect.Array.get(paramObject, i), paramConsumer);
            i += 1;
          }
          write("[", paramConsumer);
        }
        if ((paramConsumer instanceof OutPort))
        {
          ((OutPort)paramConsumer).endLogicalBlock("]");
          return;
        }
        write("]", paramConsumer);
        return;
      }
    }
    write((String)paramObject, paramConsumer);
  }
  
  void writeSymbol(Symbol paramSymbol, Consumer paramConsumer, boolean paramBoolean)
  {
    int j = 1;
    String str2 = paramSymbol.getPrefix();
    Namespace localNamespace = paramSymbol.getNamespace();
    String str1;
    int i;
    label39:
    label52:
    int m;
    int k;
    if (localNamespace == null)
    {
      str1 = null;
      if ((str1 == null) || (str1.length() <= 0)) {
        break label127;
      }
      i = 1;
      if ((str2 == null) || (str2.length() <= 0)) {
        break label133;
      }
      m = 0;
      if (localNamespace != Keyword.keywordNamespace) {
        break label145;
      }
      if ((this.language != 'C') && (this.language != 'E')) {
        break label139;
      }
      paramConsumer.write(58);
      k = m;
    }
    for (;;)
    {
      writeSymbol(paramSymbol.getName(), paramConsumer, paramBoolean);
      if (k != 0) {
        paramConsumer.write(58);
      }
      return;
      str1 = localNamespace.getName();
      break;
      label127:
      i = 0;
      break label39;
      label133:
      j = 0;
      break label52;
      label139:
      k = 1;
      continue;
      label145:
      if (j == 0)
      {
        k = m;
        if (i == 0) {}
      }
      else
      {
        if (j != 0) {
          writeSymbol(str2, paramConsumer, paramBoolean);
        }
        if ((i != 0) && ((paramBoolean) || (j == 0)))
        {
          paramConsumer.write(123);
          paramConsumer.write(str1);
          paramConsumer.write(125);
        }
        paramConsumer.write(58);
        k = m;
      }
    }
  }
  
  void writeSymbol(String paramString, Consumer paramConsumer, boolean paramBoolean)
  {
    if ((paramBoolean) && (!r5rsIdentifierMinusInteriorColons.matcher(paramString).matches()))
    {
      int m = paramString.length();
      if (m == 0) {
        write("||", paramConsumer);
      }
      int k;
      label75:
      do
      {
        return;
        k = 0;
        int j = 0;
        if (j < m)
        {
          int n = paramString.charAt(j);
          String str;
          int i;
          if (n == 124) {
            if (k != 0)
            {
              str = "|\\";
              write(str, paramConsumer);
              i = 0;
            }
          }
          for (;;)
          {
            paramConsumer.write(n);
            j += 1;
            k = i;
            break;
            str = "\\";
            break label75;
            i = k;
            if (k == 0)
            {
              paramConsumer.write(124);
              i = 1;
            }
          }
        }
      } while (k == 0);
      paramConsumer.write(124);
      return;
    }
    write(paramString, paramConsumer);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\DisplayFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */