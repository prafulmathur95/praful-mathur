package kawa.lang;

import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.text.ReportFormat;

public class ListPat
  extends Pattern
{
  Object default_value;
  int max_length;
  int min_length;
  
  public ListPat(int paramInt)
  {
    this.min_length = paramInt;
    this.max_length = paramInt;
  }
  
  public ListPat(int paramInt1, int paramInt2)
  {
    this.min_length = paramInt1;
    this.max_length = paramInt2;
  }
  
  public ListPat(int paramInt1, int paramInt2, Object paramObject)
  {
    this.min_length = paramInt1;
    this.max_length = paramInt2;
    this.default_value = paramObject;
  }
  
  public static boolean match(int paramInt1, int paramInt2, Object paramObject1, Object paramObject2, Object[] paramArrayOfObject, int paramInt3)
  {
    int i = 0;
    while (i < paramInt2) {
      if ((paramObject2 instanceof Pair))
      {
        paramObject2 = (Pair)paramObject2;
        paramArrayOfObject[(paramInt3 + i)] = ((Pair)paramObject2).getCar();
        paramObject2 = ((Pair)paramObject2).getCdr();
        i += 1;
      }
      else
      {
        if (i >= paramInt1) {
          break;
        }
      }
    }
    do
    {
      return false;
      paramInt1 = i;
      if (i != paramInt2) {
        break;
      }
    } while (paramObject2 != LList.Empty);
    paramInt1 = i;
    while (paramInt1 < paramInt2)
    {
      paramArrayOfObject[(paramInt3 + paramInt1)] = paramObject1;
      paramInt1 += 1;
    }
    return true;
  }
  
  public static Object[] match(int paramInt1, int paramInt2, Object paramObject1, Object paramObject2)
  {
    Object[] arrayOfObject = new Object[paramInt2];
    if (match(paramInt1, paramInt2, paramObject1, paramObject2, arrayOfObject, 0)) {
      return arrayOfObject;
    }
    return null;
  }
  
  public boolean match(Object paramObject, Object[] paramArrayOfObject, int paramInt)
  {
    return match(this.min_length, this.max_length, this.default_value, paramObject, paramArrayOfObject, paramInt);
  }
  
  public void print(Consumer paramConsumer)
  {
    paramConsumer.write("#<list-pattern min:");
    paramConsumer.write(Integer.toString(this.min_length));
    paramConsumer.write(" max:");
    paramConsumer.write(Integer.toString(this.max_length));
    paramConsumer.write(" default:");
    ReportFormat.print(this.default_value, paramConsumer);
    paramConsumer.write(62);
  }
  
  public int varCount()
  {
    return this.max_length;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\ListPat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */