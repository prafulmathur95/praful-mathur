package kawa.lang;

import gnu.expr.Compilation;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.IdentityHashMap;
import java.util.Vector;

public class SyntaxTemplate
  implements Externalizable
{
  static final int BUILD_CONS = 1;
  static final int BUILD_DOTS = 5;
  static final int BUILD_LIST1 = 8;
  static final int BUILD_LITERAL = 4;
  static final int BUILD_MISC = 0;
  static final int BUILD_NIL = 16;
  static final int BUILD_SYNTAX = 24;
  static final int BUILD_VAR = 2;
  static final int BUILD_VAR_CAR = 3;
  static final int BUILD_VECTOR = 40;
  static final int BUILD_WIDE = 7;
  static final String dots3 = "...";
  Object[] literal_values;
  int max_nesting;
  String patternNesting;
  String template_program;
  
  protected SyntaxTemplate() {}
  
  public SyntaxTemplate(Object paramObject, SyntaxForm paramSyntaxForm, Translator paramTranslator)
  {
    if ((paramTranslator == null) || (paramTranslator.patternScope == null)) {}
    for (Object localObject = "";; localObject = paramTranslator.patternScope.patternNesting.toString())
    {
      this.patternNesting = ((String)localObject);
      localObject = new StringBuffer();
      Vector localVector = new Vector();
      convert_template(paramObject, paramSyntaxForm, (StringBuffer)localObject, 0, localVector, new IdentityHashMap(), false, paramTranslator);
      this.template_program = ((StringBuffer)localObject).toString();
      this.literal_values = new Object[localVector.size()];
      localVector.copyInto(this.literal_values);
      return;
    }
  }
  
  public SyntaxTemplate(String paramString1, String paramString2, Object[] paramArrayOfObject, int paramInt)
  {
    this.patternNesting = paramString1;
    this.template_program = paramString2;
    this.literal_values = paramArrayOfObject;
    this.max_nesting = paramInt;
  }
  
  private int get_count(Object paramObject, int paramInt, int[] paramArrayOfInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      paramObject = ((Object[])(Object[])paramObject)[paramArrayOfInt[i]];
      i += 1;
    }
    return ((Object[])paramObject).length;
  }
  
  static int indexOf(Vector paramVector, Object paramObject)
  {
    int j = paramVector.size();
    int i = 0;
    while (i < j)
    {
      if (paramVector.elementAt(i) == paramObject) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public int convert_template(Object paramObject1, SyntaxForm paramSyntaxForm, StringBuffer paramStringBuffer, int paramInt, Vector paramVector, Object paramObject2, boolean paramBoolean, Translator paramTranslator)
  {
    while ((paramObject1 instanceof SyntaxForm))
    {
      paramSyntaxForm = (SyntaxForm)paramObject1;
      paramObject1 = paramSyntaxForm.getDatum();
    }
    Object localObject1;
    if (((paramObject1 instanceof Pair)) || ((paramObject1 instanceof FVector)))
    {
      localObject1 = (IdentityHashMap)paramObject2;
      if (((IdentityHashMap)localObject1).containsKey(paramObject1))
      {
        paramTranslator.syntaxError("self-referential (cyclic) syntax template");
        paramInt = -2;
        return paramInt;
      }
      ((IdentityHashMap)localObject1).put(paramObject1, paramObject1);
    }
    int j;
    int i1;
    Object localObject2;
    Object localObject3;
    label166:
    int i;
    if ((paramObject1 instanceof Pair))
    {
      localObject1 = (Pair)paramObject1;
      j = -2;
      i1 = paramStringBuffer.length();
      localObject2 = ((Pair)localObject1).getCar();
      if (paramTranslator.matches(localObject2, "..."))
      {
        localObject3 = Translator.stripSyntax(((Pair)localObject1).getCdr());
        if ((localObject3 instanceof Pair))
        {
          localObject3 = (Pair)localObject3;
          if ((((Pair)localObject3).getCar() == "...") && (((Pair)localObject3).getCdr() == LList.Empty))
          {
            paramSyntaxForm = "...";
            i = indexOf(paramVector, paramSyntaxForm);
            paramInt = i;
            if (i < 0)
            {
              paramInt = paramVector.size();
              paramVector.addElement(paramSyntaxForm);
            }
            if ((paramSyntaxForm instanceof Symbol)) {
              paramTranslator.noteAccess(paramSyntaxForm, paramTranslator.currentScope());
            }
            if ((!(paramSyntaxForm instanceof SyntaxForm)) && (paramSyntaxForm != "...")) {
              paramStringBuffer.append('\030');
            }
            paramStringBuffer.append((char)(paramInt * 8 + 4));
            if (paramSyntaxForm != "...") {
              break label746;
            }
          }
        }
      }
    }
    label740:
    label746:
    for (paramInt = -1;; paramInt = -2)
    {
      return paramInt;
      int i2 = paramVector.size();
      paramStringBuffer.append('\b');
      i = 0;
      localObject1 = ((Pair)localObject1).getCdr();
      int m;
      for (;;)
      {
        if ((localObject1 instanceof Pair))
        {
          localObject3 = (Pair)localObject1;
          if (paramTranslator.matches(((Pair)localObject3).getCar(), "...")) {}
        }
        else
        {
          m = convert_template(localObject2, paramSyntaxForm, paramStringBuffer, paramInt + i, paramVector, paramObject2, false, paramTranslator);
          if (localObject1 != LList.Empty)
          {
            paramStringBuffer.setCharAt(i1, (char)((paramStringBuffer.length() - i1 - 1 << 3) + 1));
            j = convert_template(localObject1, paramSyntaxForm, paramStringBuffer, paramInt, paramVector, paramObject2, paramBoolean, paramTranslator);
          }
          if (i <= 0) {
            break;
          }
          if (m < 0) {
            paramTranslator.syntaxError("... follows template with no suitably-nested pattern variable");
          }
          k = i;
          for (;;)
          {
            int n = k - 1;
            if (n < 0) {
              break;
            }
            paramStringBuffer.setCharAt(i1 + n + 1, (char)((m << 3) + 5));
            int i3 = paramInt + i;
            k = n;
            if (i3 >= this.max_nesting)
            {
              this.max_nesting = i3;
              k = n;
            }
          }
        }
        i += 1;
        localObject1 = ((Pair)localObject3).getCdr();
        paramStringBuffer.append('\005');
      }
      paramInt = m;
      if (m >= 0) {
        break;
      }
      if (j >= 0) {
        return j;
      }
      if ((m == -1) || (j == -1)) {
        return -1;
      }
      if (paramBoolean) {
        return -2;
      }
      paramVector.setSize(i2);
      paramStringBuffer.setLength(i1);
      paramSyntaxForm = (SyntaxForm)paramObject1;
      break label166;
      if ((paramObject1 instanceof FVector))
      {
        paramStringBuffer.append('(');
        return convert_template(LList.makeList((FVector)paramObject1), paramSyntaxForm, paramStringBuffer, paramInt, paramVector, paramObject2, true, paramTranslator);
      }
      if (paramObject1 == LList.Empty)
      {
        paramStringBuffer.append('\020');
        return -2;
      }
      paramSyntaxForm = (SyntaxForm)paramObject1;
      if (!(paramObject1 instanceof Symbol)) {
        break label166;
      }
      paramSyntaxForm = (SyntaxForm)paramObject1;
      if (paramTranslator == null) {
        break label166;
      }
      paramSyntaxForm = (SyntaxForm)paramObject1;
      if (paramTranslator.patternScope == null) {
        break label166;
      }
      j = indexOf(paramTranslator.patternScope.pattern_names, paramObject1);
      paramSyntaxForm = (SyntaxForm)paramObject1;
      if (j < 0) {
        break label166;
      }
      int k = this.patternNesting.charAt(j);
      if ((k & 0x1) != 0)
      {
        i = 3;
        k >>= 1;
        if (k > paramInt) {
          paramTranslator.syntaxError("inconsistent ... nesting of " + paramObject1);
        }
        paramStringBuffer.append((char)(j * 8 + i));
        if (k != paramInt) {
          break label740;
        }
      }
      for (paramInt = j;; paramInt = -1)
      {
        return paramInt;
        i = 2;
        break;
      }
    }
  }
  
  Object execute(int paramInt1, Object[] paramArrayOfObject, int paramInt2, int[] paramArrayOfInt, Translator paramTranslator, TemplateScope paramTemplateScope)
  {
    int j = this.template_program.charAt(paramInt1);
    int i = paramInt1;
    Object localObject1;
    for (paramInt1 = j; (paramInt1 & 0x7) == 7; paramInt1 = paramInt1 - 7 << 13 | ((String)localObject1).charAt(i))
    {
      localObject1 = this.template_program;
      i += 1;
    }
    if (paramInt1 == 8) {
      paramArrayOfObject = executeToList(i + 1, paramArrayOfObject, paramInt2, paramArrayOfInt, paramTranslator, paramTemplateScope);
    }
    do
    {
      return paramArrayOfObject;
      if (paramInt1 == 16) {
        return LList.Empty;
      }
      if (paramInt1 != 24) {
        break;
      }
      paramArrayOfInt = execute(i + 1, paramArrayOfObject, paramInt2, paramArrayOfInt, paramTranslator, paramTemplateScope);
      paramArrayOfObject = paramArrayOfInt;
    } while (paramArrayOfInt == LList.Empty);
    return SyntaxForms.makeForm(paramArrayOfInt, paramTemplateScope);
    if ((paramInt1 & 0x7) == 1)
    {
      Object localObject3 = null;
      Object localObject4 = null;
      Object localObject2;
      int k;
      do
      {
        i += 1;
        Object localObject5 = executeToList(i, paramArrayOfObject, paramInt2, paramArrayOfInt, paramTranslator, paramTemplateScope);
        if (localObject3 == null)
        {
          localObject1 = localObject5;
          localObject2 = localObject3;
        }
        while ((localObject5 instanceof Pair))
        {
          localObject2 = (Pair)localObject5;
          localObject5 = ((Pair)localObject2).getCdr();
          continue;
          ((Pair)localObject3).setCdrBackdoor(localObject5);
          localObject2 = localObject3;
          localObject1 = localObject4;
        }
        j = i + (paramInt1 >> 3);
        k = this.template_program.charAt(j);
        paramInt1 = k;
        localObject3 = localObject2;
        localObject4 = localObject1;
        i = j;
      } while ((k & 0x7) == 1);
      paramArrayOfObject = execute(j, paramArrayOfObject, paramInt2, paramArrayOfInt, paramTranslator, paramTemplateScope);
      if (localObject2 == null) {}
      for (;;)
      {
        return paramArrayOfObject;
        ((Pair)localObject2).setCdrBackdoor(paramArrayOfObject);
        paramArrayOfObject = (Object[])localObject1;
      }
    }
    if (paramInt1 == 40) {
      return new FVector((LList)execute(i + 1, paramArrayOfObject, paramInt2, paramArrayOfInt, paramTranslator, paramTemplateScope));
    }
    if ((paramInt1 & 0x7) == 4) {
      return this.literal_values[(paramInt1 >> 3)];
    }
    if ((paramInt1 & 0x6) == 2)
    {
      paramArrayOfInt = get_var(paramInt1 >> 3, paramArrayOfObject, paramArrayOfInt);
      paramArrayOfObject = paramArrayOfInt;
      if ((paramInt1 & 0x7) == 3) {
        paramArrayOfObject = ((Pair)paramArrayOfInt).getCar();
      }
      return paramArrayOfObject;
    }
    throw new Error("unknown template code: " + paramInt1 + " at " + i);
  }
  
  public Object execute(Object[] paramArrayOfObject, TemplateScope paramTemplateScope)
  {
    return execute(0, paramArrayOfObject, 0, new int[this.max_nesting], (Translator)Compilation.getCurrent(), paramTemplateScope);
  }
  
  public Object execute(Object[] paramArrayOfObject, Translator paramTranslator, TemplateScope paramTemplateScope)
  {
    return execute(0, paramArrayOfObject, 0, new int[this.max_nesting], paramTranslator, paramTemplateScope);
  }
  
  LList executeToList(int paramInt1, Object[] paramArrayOfObject, int paramInt2, int[] paramArrayOfInt, Translator paramTranslator, TemplateScope paramTemplateScope)
  {
    int j = this.template_program.charAt(paramInt1);
    int i = paramInt1;
    Object localObject1;
    while ((j & 0x7) == 7)
    {
      localObject1 = this.template_program;
      i += 1;
      j = j - 7 << 13 | ((String)localObject1).charAt(i);
    }
    Object localObject2;
    if ((j & 0x7) == 3)
    {
      paramArrayOfObject = (Pair)get_var(j >> 3, paramArrayOfObject, paramArrayOfInt);
      localObject2 = Translator.makePair(paramArrayOfObject, paramArrayOfObject.getCar(), LList.Empty);
      return (LList)localObject2;
    }
    if ((j & 0x7) == 5)
    {
      j = get_count(paramArrayOfObject[(j >> 3)], paramInt2, paramArrayOfInt);
      localObject1 = LList.Empty;
      Pair localPair = null;
      paramInt1 = 0;
      for (;;)
      {
        localObject2 = localObject1;
        if (paramInt1 >= j) {
          break;
        }
        paramArrayOfInt[paramInt2] = paramInt1;
        localObject2 = executeToList(i + 1, paramArrayOfObject, paramInt2 + 1, paramArrayOfInt, paramTranslator, paramTemplateScope);
        if (localPair == null) {
          localObject1 = localObject2;
        }
        while ((localObject2 instanceof Pair))
        {
          localPair = (Pair)localObject2;
          localObject2 = (LList)localPair.getCdr();
          continue;
          localPair.setCdrBackdoor(localObject2);
        }
        paramInt1 += 1;
      }
    }
    return new Pair(execute(paramInt1, paramArrayOfObject, paramInt2, paramArrayOfInt, paramTranslator, paramTemplateScope), LList.Empty);
  }
  
  Object get_var(int paramInt, Object[] paramArrayOfObject, int[] paramArrayOfInt)
  {
    paramArrayOfObject = paramArrayOfObject[paramInt];
    Object[] arrayOfObject = paramArrayOfObject;
    if (paramInt < this.patternNesting.length())
    {
      int i = this.patternNesting.charAt(paramInt);
      paramInt = 0;
      for (;;)
      {
        arrayOfObject = paramArrayOfObject;
        if (paramInt >= i >> 1) {
          break;
        }
        paramArrayOfObject = ((Object[])(Object[])paramArrayOfObject)[paramArrayOfInt[paramInt]];
        paramInt += 1;
      }
    }
    return arrayOfObject;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.patternNesting = ((String)paramObjectInput.readObject());
    this.template_program = ((String)paramObjectInput.readObject());
    this.literal_values = ((Object[])paramObjectInput.readObject());
    this.max_nesting = paramObjectInput.readInt();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.patternNesting);
    paramObjectOutput.writeObject(this.template_program);
    paramObjectOutput.writeObject(this.literal_values);
    paramObjectOutput.writeInt(this.max_nesting);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\SyntaxTemplate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */