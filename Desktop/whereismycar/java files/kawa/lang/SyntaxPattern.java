package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;
import java.util.Vector;

public class SyntaxPattern
  extends Pattern
  implements Externalizable
{
  static final int MATCH_ANY = 3;
  static final int MATCH_ANY_CAR = 7;
  static final int MATCH_EQUALS = 2;
  static final int MATCH_IGNORE = 24;
  static final int MATCH_LENGTH = 6;
  static final int MATCH_LREPEAT = 5;
  static final int MATCH_MISC = 0;
  static final int MATCH_NIL = 8;
  static final int MATCH_PAIR = 4;
  static final int MATCH_VECTOR = 16;
  static final int MATCH_WIDE = 1;
  Object[] literals;
  String program;
  int varCount;
  
  public SyntaxPattern(Object paramObject, Object[] paramArrayOfObject, Translator paramTranslator)
  {
    this(new StringBuffer(), paramObject, null, paramArrayOfObject, paramTranslator);
  }
  
  public SyntaxPattern(String paramString, Object[] paramArrayOfObject, int paramInt)
  {
    this.program = paramString;
    this.literals = paramArrayOfObject;
    this.varCount = paramInt;
  }
  
  SyntaxPattern(StringBuffer paramStringBuffer, Object paramObject, SyntaxForm paramSyntaxForm, Object[] paramArrayOfObject, Translator paramTranslator)
  {
    paramSyntaxForm = new Vector();
    translate(paramObject, paramStringBuffer, paramArrayOfObject, 0, paramSyntaxForm, null, '\000', paramTranslator);
    this.program = paramStringBuffer.toString();
    this.literals = new Object[paramSyntaxForm.size()];
    paramSyntaxForm.copyInto(this.literals);
    this.varCount = paramTranslator.patternScope.pattern_names.size();
  }
  
  private static void addInt(StringBuffer paramStringBuffer, int paramInt)
  {
    if (paramInt > 65535) {
      addInt(paramStringBuffer, (paramInt << 13) + 1);
    }
    paramStringBuffer.append((char)paramInt);
  }
  
  public static Object[] allocVars(int paramInt, Object[] paramArrayOfObject)
  {
    Object[] arrayOfObject = new Object[paramInt];
    if (paramArrayOfObject != null) {
      System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramArrayOfObject.length);
    }
    return arrayOfObject;
  }
  
  public static Object[] getLiteralsList(Object paramObject, SyntaxForm paramSyntaxForm, Translator paramTranslator)
  {
    Object localObject = paramTranslator.pushPositionOf(paramObject);
    int j = Translator.listLength(paramObject);
    int i = j;
    if (j < 0)
    {
      paramTranslator.error('e', "missing or malformed literals list");
      i = 0;
    }
    Object[] arrayOfObject = new Object[i + 1];
    j = 1;
    if (j <= i)
    {
      while ((paramObject instanceof SyntaxForm)) {
        paramObject = ((SyntaxForm)paramObject).getDatum();
      }
      Pair localPair = (Pair)paramObject;
      paramTranslator.pushPositionOf(localPair);
      paramSyntaxForm = localPair.getCar();
      paramObject = paramSyntaxForm;
      if ((paramObject instanceof SyntaxForm)) {
        paramObject = ((SyntaxForm)paramObject).getDatum();
      }
      for (;;)
      {
        if (!(paramObject instanceof Symbol)) {
          paramTranslator.error('e', "non-symbol '" + paramObject + "' in literals list");
        }
        arrayOfObject[j] = paramSyntaxForm;
        paramObject = localPair.getCdr();
        j += 1;
        break;
      }
    }
    paramTranslator.popPositionOf(localObject);
    return arrayOfObject;
  }
  
  private static int insertInt(int paramInt1, StringBuffer paramStringBuffer, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt2 > 65535) {
      i = paramInt1 + insertInt(paramInt1, paramStringBuffer, (paramInt2 << 13) + 1);
    }
    paramStringBuffer.insert(i, (char)paramInt2);
    return i + 1;
  }
  
  public static boolean literalIdentifierEq(Object paramObject1, ScopeExp paramScopeExp1, Object paramObject2, ScopeExp paramScopeExp2)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((paramObject1 != paramObject2) && ((paramObject1 == null) || (paramObject2 == null) || (!paramObject1.equals(paramObject2)))) {
      bool1 = false;
    }
    do
    {
      return bool1;
      bool1 = bool2;
    } while (paramScopeExp1 == paramScopeExp2);
    Declaration localDeclaration1 = null;
    Object localObject = null;
    ScopeExp localScopeExp2 = paramScopeExp1;
    label48:
    Declaration localDeclaration2 = localDeclaration1;
    paramScopeExp1 = (ScopeExp)localObject;
    ScopeExp localScopeExp1 = paramScopeExp2;
    if (localScopeExp2 != null)
    {
      localDeclaration2 = localDeclaration1;
      paramScopeExp1 = (ScopeExp)localObject;
      localScopeExp1 = paramScopeExp2;
      if (!(localScopeExp2 instanceof ModuleExp))
      {
        localDeclaration1 = localScopeExp2.lookup(paramObject1);
        if (localDeclaration1 == null) {
          break label146;
        }
        localScopeExp1 = paramScopeExp2;
        paramScopeExp1 = (ScopeExp)localObject;
        localDeclaration2 = localDeclaration1;
      }
    }
    for (;;)
    {
      paramObject1 = paramScopeExp1;
      if (localScopeExp1 != null)
      {
        paramObject1 = paramScopeExp1;
        if (!(localScopeExp1 instanceof ModuleExp))
        {
          paramScopeExp1 = localScopeExp1.lookup(paramObject2);
          if (paramScopeExp1 == null) {
            break label156;
          }
          paramObject1 = paramScopeExp1;
        }
      }
      bool1 = bool2;
      if (localDeclaration2 == paramObject1) {
        break;
      }
      return false;
      label146:
      localScopeExp2 = localScopeExp2.outer;
      break label48;
      label156:
      localScopeExp1 = localScopeExp1.outer;
    }
  }
  
  public void disassemble()
  {
    disassemble(OutPort.errDefault(), (Translator)Compilation.getCurrent(), 0, this.program.length());
  }
  
  public void disassemble(PrintWriter paramPrintWriter, Translator paramTranslator)
  {
    disassemble(paramPrintWriter, paramTranslator, 0, this.program.length());
  }
  
  void disassemble(PrintWriter paramPrintWriter, Translator paramTranslator, int paramInt1, int paramInt2)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramTranslator != null)
    {
      localObject1 = localObject2;
      if (paramTranslator.patternScope != null) {
        localObject1 = paramTranslator.patternScope.pattern_names;
      }
    }
    int j = 0;
    int i = paramInt1;
    paramInt1 = j;
    if (i < paramInt2)
    {
      j = this.program.charAt(i);
      paramPrintWriter.print(" " + i + ": " + j);
      i += 1;
      int k = j & 0x7;
      paramInt1 = paramInt1 << 13 | j >> 3;
      switch (k)
      {
      default: 
        label164:
        paramPrintWriter.println(" - " + k + '/' + paramInt1);
      }
      for (;;)
      {
        paramInt1 = 0;
        break;
        paramPrintWriter.println(" - WIDE " + paramInt1);
        break;
        paramPrintWriter.print(" - EQUALS[" + paramInt1 + "]");
        if ((this.literals != null) && (paramInt1 >= 0) && (paramInt1 < this.literals.length)) {
          paramPrintWriter.print(this.literals[paramInt1]);
        }
        paramPrintWriter.println();
        continue;
        Object localObject3 = new StringBuilder();
        if (k == 3) {}
        for (localObject2 = " - ANY[";; localObject2 = " - ANY_CAR[")
        {
          paramPrintWriter.print((String)localObject2 + paramInt1 + "]");
          if ((localObject1 != null) && (paramInt1 >= 0) && (paramInt1 < ((Vector)localObject1).size())) {
            paramPrintWriter.print(((Vector)localObject1).elementAt(paramInt1));
          }
          paramPrintWriter.println();
          break;
        }
        paramPrintWriter.println(" - PAIR[" + paramInt1 + "]");
        continue;
        paramPrintWriter.println(" - LREPEAT[" + paramInt1 + "]");
        disassemble(paramPrintWriter, paramTranslator, i, i + paramInt1);
        i += paramInt1;
        localObject2 = new StringBuilder().append(" ").append(i).append(": - repeat first var:");
        localObject3 = this.program;
        paramInt1 = i + 1;
        paramPrintWriter.println(((String)localObject3).charAt(i) >> '\003');
        localObject2 = new StringBuilder().append(" ").append(paramInt1).append(": - repeast nested vars:");
        localObject3 = this.program;
        i = paramInt1 + 1;
        paramPrintWriter.println(((String)localObject3).charAt(paramInt1) >> '\003');
        continue;
        localObject3 = new StringBuilder().append(" - LENGTH ").append(paramInt1 >> 1).append(" pairs. ");
        if ((paramInt1 & 0x1) == 0) {}
        for (localObject2 = "pure list";; localObject2 = "impure list")
        {
          paramPrintWriter.println((String)localObject2);
          break;
        }
        paramPrintWriter.print("[misc ch:" + j + " n:" + 8 + "]");
        if (j == 8)
        {
          paramPrintWriter.println(" - NIL");
        }
        else if (j == 16)
        {
          paramPrintWriter.println(" - VECTOR");
        }
        else
        {
          if (j != 24) {
            break label164;
          }
          paramPrintWriter.println(" - IGNORE");
        }
      }
    }
  }
  
  public boolean match(Object paramObject, Object[] paramArrayOfObject, int paramInt)
  {
    return match(paramObject, paramArrayOfObject, paramInt, 0, null);
  }
  
  public boolean match(Object paramObject, Object[] paramArrayOfObject, int paramInt1, int paramInt2, SyntaxForm paramSyntaxForm)
  {
    int j = 0;
    int i = paramInt2;
    paramInt2 = j;
    label367:
    int n;
    label726:
    label738:
    do
    {
      int k;
      int i1;
      do
      {
        for (;;)
        {
          if ((paramObject instanceof SyntaxForm))
          {
            paramSyntaxForm = (SyntaxForm)paramObject;
            paramObject = paramSyntaxForm.getDatum();
          }
          else
          {
            localObject1 = this.program;
            k = i + 1;
            i = ((String)localObject1).charAt(i);
            j = paramInt2 << 13 | i >> 3;
            boolean bool;
            switch (i & 0x7)
            {
            case 7: 
            default: 
              disassemble();
              throw new Error("unrecognized pattern opcode @pc:" + k);
            case 1: 
              i = k;
              paramInt2 = j;
              break;
            case 0: 
              if (i == 8)
              {
                if (paramObject == LList.Empty) {}
                for (bool = true;; bool = false) {
                  return bool;
                }
              }
              if (i == 16)
              {
                if (!(paramObject instanceof FVector)) {
                  return false;
                }
                return match(LList.makeList((FVector)paramObject), paramArrayOfObject, paramInt1, k, paramSyntaxForm);
              }
              if (i == 24) {
                return true;
              }
              throw new Error("unknwon pattern opcode");
            case 8: 
              if (paramObject == LList.Empty) {}
              for (bool = true;; bool = false) {
                return bool;
              }
            case 6: 
              localObject1 = paramObject;
              paramInt2 = 0;
              for (;;)
              {
                if ((localObject1 instanceof SyntaxForm))
                {
                  localObject1 = ((SyntaxForm)localObject1).getDatum();
                }
                else
                {
                  if (paramInt2 == j >> 1)
                  {
                    if ((j & 0x1) == 0)
                    {
                      if (localObject1 == LList.Empty) {}
                    }
                    else {
                      while ((localObject1 instanceof Pair)) {
                        return false;
                      }
                    }
                    paramInt2 = 0;
                    i = k;
                    break;
                  }
                  if (!(localObject1 instanceof Pair)) {
                    break label367;
                  }
                  localObject1 = ((Pair)localObject1).getCdr();
                  paramInt2 += 1;
                }
              }
              return false;
            case 4: 
              if (!(paramObject instanceof Pair)) {
                return false;
              }
              paramObject = (Pair)paramObject;
              if (!match_car((Pair)paramObject, paramArrayOfObject, paramInt1, k, paramSyntaxForm)) {
                return false;
              }
              i = k + j;
              paramInt2 = 0;
              paramObject = ((Pair)paramObject).getCdr();
            }
          }
        }
        paramInt2 = k + j;
        localObject1 = this.program;
        i = paramInt2 + 1;
        paramInt2 = ((String)localObject1).charAt(paramInt2);
        j = paramInt2 >> 3;
        while ((paramInt2 & 0x7) == 1)
        {
          paramInt2 = this.program.charAt(i);
          j = j << 13 | paramInt2 >> 3;
          i += 1;
        }
        int i2 = j + paramInt1;
        int m = this.program.charAt(i) >> '\003';
        j = i + 1;
        i = paramInt2;
        paramInt2 = j;
        while ((i & 0x7) == 1)
        {
          i = this.program.charAt(paramInt2);
          m = m << 13 | i >> 3;
          paramInt2 += 1;
        }
        localObject1 = this.program;
        j = paramInt2 + 1;
        i = ((String)localObject1).charAt(paramInt2);
        n = 1;
        paramInt2 = 1;
        if (i == 8)
        {
          i1 = 0;
          n = paramInt2;
          i = Translator.listLength(paramObject);
          if (i < 0) {
            break label726;
          }
          paramInt2 = 1;
        }
        for (;;)
        {
          if ((i >= i1) && ((n == 0) || (paramInt2 != 0))) {
            break label738;
          }
          return false;
          i1 = i >> 3;
          paramInt2 = j;
          j = i1;
          while ((i & 0x7) == 1)
          {
            i = this.program.charAt(paramInt2);
            j = j << 13 | i >> 3;
            paramInt2 += 1;
          }
          i = n;
          if ((j & 0x1) != 0) {
            i = 0;
          }
          i1 = j >> 1;
          n = i;
          j = paramInt2;
          break;
          paramInt2 = 0;
          i = -1 - i;
        }
        int i3 = i - i1;
        localObject3 = new Object[m][];
        paramInt2 = 0;
        while (paramInt2 < m)
        {
          localObject3[paramInt2] = new Object[i3];
          paramInt2 += 1;
        }
        paramInt2 = 0;
        localObject2 = paramSyntaxForm;
        localObject1 = paramObject;
        while (paramInt2 < i3)
        {
          paramObject = localObject2;
          while ((localObject1 instanceof SyntaxForm))
          {
            paramObject = (SyntaxForm)localObject1;
            localObject1 = ((SyntaxForm)paramObject).getDatum();
          }
          paramSyntaxForm = (Pair)localObject1;
          if (!match_car(paramSyntaxForm, paramArrayOfObject, paramInt1, k, (SyntaxForm)paramObject)) {
            return false;
          }
          localObject1 = paramSyntaxForm.getCdr();
          i = 0;
          while (i < m)
          {
            localObject3[i][paramInt2] = paramArrayOfObject[(i2 + i)];
            i += 1;
          }
          paramInt2 += 1;
          localObject2 = paramObject;
        }
        paramInt2 = 0;
        while (paramInt2 < m)
        {
          paramArrayOfObject[(i2 + paramInt2)] = localObject3[paramInt2];
          paramInt2 += 1;
        }
        k = 0;
        paramInt2 = k;
        paramObject = localObject1;
        i = j;
        paramSyntaxForm = (SyntaxForm)localObject2;
      } while (i1 != 0);
      paramInt2 = k;
      paramObject = localObject1;
      i = j;
      paramSyntaxForm = (SyntaxForm)localObject2;
    } while (n == 0);
    return true;
    Object localObject1 = this.literals[j];
    Object localObject3 = (Translator)Compilation.getCurrent();
    if ((localObject1 instanceof SyntaxForm))
    {
      paramArrayOfObject = (SyntaxForm)localObject1;
      localObject1 = paramArrayOfObject.getDatum();
      paramArrayOfObject = paramArrayOfObject.getScope();
      if ((paramObject instanceof SyntaxForm))
      {
        paramObject = (SyntaxForm)paramObject;
        localObject2 = ((SyntaxForm)paramObject).getDatum();
        paramObject = ((SyntaxForm)paramObject).getScope();
        return literalIdentifierEq(localObject1, paramArrayOfObject, localObject2, (ScopeExp)paramObject);
      }
    }
    else
    {
      paramArrayOfObject = ((Translator)localObject3).getCurrentSyntax();
      if ((paramArrayOfObject instanceof Macro)) {}
      for (paramArrayOfObject = ((Macro)paramArrayOfObject).getCapturedScope();; paramArrayOfObject = null) {
        break;
      }
    }
    Object localObject2 = paramObject;
    if (paramSyntaxForm == null) {}
    for (paramObject = ((Translator)localObject3).currentScope();; paramObject = paramSyntaxForm.getScope()) {
      break;
    }
    localObject1 = paramObject;
    if (paramSyntaxForm != null) {
      localObject1 = SyntaxForms.fromDatum(paramObject, paramSyntaxForm);
    }
    paramArrayOfObject[(paramInt1 + j)] = localObject1;
    return true;
  }
  
  boolean match_car(Pair paramPair, Object[] paramArrayOfObject, int paramInt1, int paramInt2, SyntaxForm paramSyntaxForm)
  {
    Object localObject = this.program;
    int i = paramInt2 + 1;
    int k = ((String)localObject).charAt(paramInt2);
    int j = k >> 3;
    while ((k & 0x7) == 1)
    {
      k = this.program.charAt(i);
      j = j << 13 | k >> 3;
      i += 1;
    }
    if ((k & 0x7) == 7)
    {
      localObject = paramPair;
      if (paramSyntaxForm != null)
      {
        localObject = paramPair;
        if (!(paramPair.getCar() instanceof SyntaxForm)) {
          localObject = Translator.makePair(paramPair, SyntaxForms.fromDatum(paramPair.getCar(), paramSyntaxForm), paramPair.getCdr());
        }
      }
      paramArrayOfObject[(paramInt1 + j)] = localObject;
      return true;
    }
    return match(paramPair.getCar(), paramArrayOfObject, paramInt1, paramInt2, paramSyntaxForm);
  }
  
  public void print(Consumer paramConsumer)
  {
    paramConsumer.write("#<syntax-pattern>");
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.literals = ((Object[])paramObjectInput.readObject());
    this.program = ((String)paramObjectInput.readObject());
    this.varCount = paramObjectInput.readInt();
  }
  
  void translate(Object paramObject, StringBuffer paramStringBuffer, Object[] paramArrayOfObject, int paramInt, Vector paramVector, SyntaxForm paramSyntaxForm, char paramChar, Translator paramTranslator)
  {
    PatternScope localPatternScope = paramTranslator.patternScope;
    Vector localVector = localPatternScope.pattern_names;
    SyntaxForm localSyntaxForm;
    for (;;)
    {
      localSyntaxForm = paramSyntaxForm;
      if (!(paramObject instanceof SyntaxForm)) {
        break;
      }
      paramSyntaxForm = (SyntaxForm)paramObject;
      paramObject = paramSyntaxForm.getDatum();
    }
    Object localObject3;
    if ((paramObject instanceof Pair)) {
      localObject3 = paramTranslator.pushPositionOf(paramObject);
    }
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      char c1;
      try
      {
        char c3 = paramStringBuffer.length();
        paramStringBuffer.append('\004');
        Pair localPair = (Pair)paramObject;
        localObject1 = localPair.getCdr();
        localObject2 = localSyntaxForm;
        if ((localObject1 instanceof SyntaxForm))
        {
          localObject2 = (SyntaxForm)localObject1;
          localObject1 = ((SyntaxForm)localObject2).getDatum();
          continue;
        }
        c2 = '\000';
        paramObject = localObject1;
        i = c2;
        paramSyntaxForm = (SyntaxForm)localObject2;
        if ((localObject1 instanceof Pair))
        {
          paramObject = localObject1;
          i = c2;
          paramSyntaxForm = (SyntaxForm)localObject2;
          if (paramTranslator.matches(((Pair)localObject1).getCar(), "..."))
          {
            c2 = '\001';
            localObject1 = ((Pair)localObject1).getCdr();
            paramSyntaxForm = (SyntaxForm)localObject2;
            paramObject = localObject1;
            i = c2;
            if ((localObject1 instanceof SyntaxForm))
            {
              paramSyntaxForm = (SyntaxForm)localObject1;
              localObject1 = paramSyntaxForm.getDatum();
              continue;
            }
          }
        }
        int j = localVector.size();
        c2 = paramChar;
        if (paramChar == 'P') {
          c2 = '\000';
        }
        localObject1 = localPair.getCar();
        int k;
        if (i != 0)
        {
          paramChar = paramInt + 1;
          break label901;
          translate(localObject1, paramStringBuffer, paramArrayOfObject, paramChar, paramVector, localSyntaxForm, c1, paramTranslator);
          k = localVector.size();
          int m = paramStringBuffer.length();
          if (i != 0)
          {
            paramChar = '\005';
            m = m - c3 - 1 << 3 | paramChar;
            paramChar = c3;
            if (m > 65535) {
              paramChar = c3 + insertInt(c3, paramStringBuffer, (m >> 13) + 1);
            }
            paramStringBuffer.setCharAt(paramChar, (char)m);
            paramChar = Translator.listLength(paramObject);
            if (paramChar != Integer.MIN_VALUE) {
              continue;
            }
            paramTranslator.syntaxError("cyclic pattern list");
          }
        }
        else
        {
          paramChar = paramInt;
          break label901;
          c1 = 'P';
          continue;
        }
        paramChar = '\004';
        continue;
        if (i != 0)
        {
          addInt(paramStringBuffer, j << 3);
          addInt(paramStringBuffer, k - j << 3);
          if (paramObject == LList.Empty)
          {
            paramStringBuffer.append('\b');
            return;
          }
          if (paramChar >= 0)
          {
            paramChar <<= '\001';
            addInt(paramStringBuffer, paramChar << '\003' | 0x6);
          }
        }
        else
        {
          paramTranslator.popPositionOf(localObject3);
          paramChar = c2;
          break;
        }
        paramChar = (-paramChar << 1) - 1;
        continue;
        if (!(paramObject instanceof Symbol)) {
          break label812;
        }
      }
      finally
      {
        paramTranslator.popPositionOf(localObject3);
      }
      int i = paramArrayOfObject.length;
      label647:
      label669:
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          break;
        }
        paramSyntaxForm = paramTranslator.currentScope();
        if (localSyntaxForm == null)
        {
          localObject1 = paramSyntaxForm;
          localObject2 = paramArrayOfObject[i];
          if (!(localObject2 instanceof SyntaxForm)) {
            break label647;
          }
          paramSyntaxForm = (SyntaxForm)localObject2;
          localObject2 = paramSyntaxForm.getDatum();
          paramSyntaxForm = paramSyntaxForm.getScope();
        }
        for (;;)
        {
          if (!literalIdentifierEq(paramObject, (ScopeExp)localObject1, localObject2, paramSyntaxForm)) {
            break label669;
          }
          paramChar = SyntaxTemplate.indexOf(paramVector, paramObject);
          paramInt = paramChar;
          if (paramChar < 0)
          {
            paramInt = paramVector.size();
            paramVector.addElement(paramObject);
          }
          addInt(paramStringBuffer, paramInt << 3 | 0x2);
          return;
          localObject1 = localSyntaxForm.getScope();
          break;
          if (paramTranslator.currentMacroDefinition != null) {
            paramSyntaxForm = paramTranslator.currentMacroDefinition.getCapturedScope();
          }
        }
      }
      if (localVector.contains(paramObject)) {
        paramTranslator.syntaxError("duplicated pattern variable " + paramObject);
      }
      char c2 = localVector.size();
      localVector.addElement(paramObject);
      if (paramChar == 'P')
      {
        paramChar = '\001';
        if (paramChar == 0) {
          break label800;
        }
        i = 1;
        label737:
        localPatternScope.patternNesting.append((char)((paramInt << 1) + i));
        paramObject = localPatternScope.addDeclaration(paramObject);
        ((Declaration)paramObject).setLocation(paramTranslator);
        paramTranslator.push((Declaration)paramObject);
        if (paramChar == 0) {
          break label806;
        }
      }
      label800:
      label806:
      for (paramInt = 7;; paramInt = 3)
      {
        addInt(paramStringBuffer, paramInt | c2 << '\003');
        return;
        paramChar = '\000';
        break;
        i = 0;
        break label737;
      }
      label812:
      if (paramObject == LList.Empty)
      {
        paramStringBuffer.append('\b');
        return;
      }
      if ((paramObject instanceof FVector))
      {
        paramStringBuffer.append('\020');
        paramObject = LList.makeList((FVector)paramObject);
        paramChar = 'V';
        paramSyntaxForm = localSyntaxForm;
        break;
      }
      paramChar = SyntaxTemplate.indexOf(paramVector, paramObject);
      paramInt = paramChar;
      if (paramChar < 0)
      {
        paramInt = paramVector.size();
        paramVector.addElement(paramObject);
      }
      addInt(paramStringBuffer, paramInt << 3 | 0x2);
      return;
      label901:
      if (c2 == 'V') {
        c1 = '\000';
      }
    }
  }
  
  public int varCount()
  {
    return this.varCount;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.program);
    paramObjectOutput.writeObject(this.literals);
    paramObjectOutput.writeInt(this.varCount);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\SyntaxPattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */