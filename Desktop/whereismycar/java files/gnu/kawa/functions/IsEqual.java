package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure2;

public class IsEqual
  extends Procedure2
{
  Language language;
  
  public IsEqual(Language paramLanguage, String paramString)
  {
    this.language = paramLanguage;
    setName(paramString);
  }
  
  public static boolean apply(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2) {
      return true;
    }
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return false;
    }
    if (((paramObject1 instanceof Number)) && ((paramObject2 instanceof Number))) {
      return numberEquals((Number)paramObject1, (Number)paramObject2);
    }
    int i;
    int j;
    if ((paramObject1 instanceof CharSequence))
    {
      if (!(paramObject2 instanceof CharSequence)) {
        return false;
      }
      paramObject1 = (CharSequence)paramObject1;
      paramObject2 = (CharSequence)paramObject2;
      i = ((CharSequence)paramObject1).length();
      if (i != ((CharSequence)paramObject2).length()) {
        return false;
      }
      do
      {
        j = i - 1;
        if (j < 0) {
          break;
        }
        i = j;
      } while (((CharSequence)paramObject1).charAt(j) == ((CharSequence)paramObject2).charAt(j));
      return false;
      return true;
    }
    if ((paramObject1 instanceof FVector))
    {
      if (!(paramObject2 instanceof FVector)) {
        return false;
      }
      paramObject1 = (FVector)paramObject1;
      paramObject2 = (FVector)paramObject2;
      i = ((FVector)paramObject1).size;
      if ((((FVector)paramObject2).data == null) || (((FVector)paramObject2).size != i)) {
        return false;
      }
      paramObject1 = ((FVector)paramObject1).data;
      paramObject2 = ((FVector)paramObject2).data;
      do
      {
        j = i - 1;
        if (j < 0) {
          break;
        }
        i = j;
      } while (apply(paramObject1[j], paramObject2[j]));
      return false;
      return true;
    }
    if ((paramObject1 instanceof LList))
    {
      if ((!(paramObject1 instanceof Pair)) || (!(paramObject2 instanceof Pair))) {
        return false;
      }
      Pair localPair = (Pair)paramObject1;
      paramObject1 = (Pair)paramObject2;
      paramObject2 = localPair;
      for (;;)
      {
        if (!apply(((Pair)paramObject2).getCar(), ((Pair)paramObject1).getCar())) {
          return false;
        }
        paramObject2 = ((Pair)paramObject2).getCdr();
        paramObject1 = ((Pair)paramObject1).getCdr();
        if (paramObject2 == paramObject1) {
          return true;
        }
        if ((paramObject2 == null) || (paramObject1 == null)) {
          return false;
        }
        if ((!(paramObject2 instanceof Pair)) || (!(paramObject1 instanceof Pair))) {
          return apply(paramObject2, paramObject1);
        }
        paramObject2 = (Pair)paramObject2;
        paramObject1 = (Pair)paramObject1;
      }
    }
    return paramObject1.equals(paramObject2);
  }
  
  public static boolean numberEquals(Number paramNumber1, Number paramNumber2)
  {
    boolean bool1 = Arithmetic.isExact(paramNumber1);
    boolean bool2 = Arithmetic.isExact(paramNumber2);
    if ((bool1) && (bool2)) {
      return NumberCompare.$Eq(paramNumber1, paramNumber2);
    }
    return (bool1 == bool2) && (paramNumber1.equals(paramNumber2));
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
  {
    return this.language.booleanObject(apply(paramObject1, paramObject2));
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\IsEqual.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */