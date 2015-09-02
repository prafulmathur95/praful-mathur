package gnu.kawa.functions;

import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.text.BreakIterator;

public class UnicodeUtils
{
  static final Symbol Cc;
  static final Symbol Cf;
  static final Symbol Cn;
  static final Symbol Co;
  static final Symbol Cs;
  static final Symbol Ll;
  static final Symbol Lm;
  static final Symbol Lo;
  static final Symbol Lt;
  static final Symbol Lu;
  static final Symbol Mc;
  static final Symbol Me;
  static final Symbol Mn;
  static final Symbol Nd;
  static final Symbol Nl;
  static final Symbol No;
  static final Symbol Pc;
  static final Symbol Pd;
  static final Symbol Pe;
  static final Symbol Pf;
  static final Symbol Pi;
  static final Symbol Po;
  static final Symbol Ps;
  static final Symbol Sc;
  static final Symbol Sk;
  static final Symbol Sm;
  static final Symbol So;
  static final Symbol Zl;
  static final Symbol Zp;
  static final Symbol Zs;
  
  static
  {
    Namespace localNamespace = Namespace.EmptyNamespace;
    Mc = localNamespace.getSymbol("Mc");
    Pc = localNamespace.getSymbol("Pc");
    Cc = localNamespace.getSymbol("Cc");
    Sc = localNamespace.getSymbol("Sc");
    Pd = localNamespace.getSymbol("Pd");
    Nd = localNamespace.getSymbol("Nd");
    Me = localNamespace.getSymbol("Me");
    Pe = localNamespace.getSymbol("Pe");
    Pf = localNamespace.getSymbol("Pf");
    Cf = localNamespace.getSymbol("Cf");
    Pi = localNamespace.getSymbol("Pi");
    Nl = localNamespace.getSymbol("Nl");
    Zl = localNamespace.getSymbol("Zl");
    Ll = localNamespace.getSymbol("Ll");
    Sm = localNamespace.getSymbol("Sm");
    Lm = localNamespace.getSymbol("Lm");
    Sk = localNamespace.getSymbol("Sk");
    Mn = localNamespace.getSymbol("Mn");
    Lo = localNamespace.getSymbol("Lo");
    No = localNamespace.getSymbol("No");
    Po = localNamespace.getSymbol("Po");
    So = localNamespace.getSymbol("So");
    Zp = localNamespace.getSymbol("Zp");
    Co = localNamespace.getSymbol("Co");
    Zs = localNamespace.getSymbol("Zs");
    Ps = localNamespace.getSymbol("Ps");
    Cs = localNamespace.getSymbol("Cs");
    Lt = localNamespace.getSymbol("Lt");
    Cn = localNamespace.getSymbol("Cn");
    Lu = localNamespace.getSymbol("Lu");
  }
  
  public static String capitalize(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    BreakIterator localBreakIterator = BreakIterator.getWordInstance();
    localBreakIterator.setText(paramString);
    int j = localBreakIterator.first();
    int i = localBreakIterator.next();
    if (i != -1)
    {
      int n = 0;
      int k = j;
      label40:
      int m = n;
      if (k < i)
      {
        if (Character.isLetter(paramString.codePointAt(k))) {
          m = 1;
        }
      }
      else
      {
        if (m != 0) {
          break label99;
        }
        localStringBuilder.append(paramString, j, i);
      }
      for (;;)
      {
        j = i;
        i = localBreakIterator.next();
        break;
        k += 1;
        break label40;
        label99:
        localStringBuilder.append(Character.toTitleCase(paramString.charAt(j)));
        localStringBuilder.append(paramString.substring(j + 1, i).toLowerCase());
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String foldCase(CharSequence paramCharSequence)
  {
    int i1 = paramCharSequence.length();
    if (i1 == 0)
    {
      localObject2 = "";
      return (String)localObject2;
    }
    Object localObject2 = null;
    int k = 0;
    int j = 0;
    for (;;)
    {
      int i;
      if (j == i1)
      {
        i = -1;
        label36:
        if ((i != 931) && (i != 963) && (i != 962)) {
          break label178;
        }
      }
      Object localObject1;
      label178:
      for (int m = 1;; m = 0)
      {
        if ((i >= 0) && (i != 304) && (i != 305))
        {
          localObject1 = localObject2;
          n = k;
          if (m == 0) {
            break label208;
          }
        }
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = localObject2;
          if (i >= 0) {
            localObject1 = new StringBuilder();
          }
        }
        if (j > k)
        {
          String str = paramCharSequence.subSequence(k, j).toString().toUpperCase().toLowerCase();
          localObject2 = str;
          if (localObject1 == null) {
            break;
          }
          ((StringBuilder)localObject1).append(str);
        }
        if (i >= 0) {
          break label184;
        }
        return ((StringBuilder)localObject1).toString();
        i = paramCharSequence.charAt(j);
        break label36;
      }
      label184:
      if (m != 0) {
        i = 963;
      }
      ((StringBuilder)localObject1).append((char)i);
      int n = j + 1;
      label208:
      j += 1;
      localObject2 = localObject1;
      k = n;
    }
  }
  
  public static Symbol generalCategory(int paramInt)
  {
    switch (Character.getType(paramInt))
    {
    case 17: 
    default: 
      return Cn;
    case 8: 
      return Mc;
    case 23: 
      return Pc;
    case 15: 
      return Cc;
    case 26: 
      return Sc;
    case 20: 
      return Pd;
    case 9: 
      return Nd;
    case 7: 
      return Me;
    case 22: 
      return Pe;
    case 30: 
      return Pf;
    case 16: 
      return Cf;
    case 29: 
      return Pi;
    case 10: 
      return Nl;
    case 13: 
      return Zl;
    case 2: 
      return Ll;
    case 25: 
      return Sm;
    case 4: 
      return Lm;
    case 27: 
      return Sk;
    case 6: 
      return Mn;
    case 5: 
      return Lo;
    case 11: 
      return No;
    case 24: 
      return Po;
    case 28: 
      return So;
    case 14: 
      return Zp;
    case 18: 
      return Co;
    case 12: 
      return Zs;
    case 21: 
      return Ps;
    case 19: 
      return Cs;
    case 3: 
      return Lt;
    }
    return Lu;
  }
  
  public static boolean isWhitespace(int paramInt)
  {
    boolean bool2 = false;
    boolean bool1;
    if ((paramInt == 32) || ((paramInt >= 9) && (paramInt <= 13))) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return bool1;
            bool1 = bool2;
          } while (paramInt < 133);
          if ((paramInt == 133) || (paramInt == 160) || (paramInt == 5760) || (paramInt == 6158)) {
            return true;
          }
          bool1 = bool2;
        } while (paramInt < 8192);
        bool1 = bool2;
      } while (paramInt > 12288);
      if ((paramInt <= 8202) || (paramInt == 8232) || (paramInt == 8233) || (paramInt == 8239) || (paramInt == 8287)) {
        break;
      }
      bool1 = bool2;
    } while (paramInt != 12288);
    return true;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\UnicodeUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */