package kawa.lang;

import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ListRepeatPat
  extends Pattern
  implements Printable, Externalizable
{
  Pattern element_pattern;
  
  public ListRepeatPat() {}
  
  public ListRepeatPat(Pattern paramPattern)
  {
    this.element_pattern = paramPattern;
  }
  
  public static ListRepeatPat make(Pattern paramPattern)
  {
    return new ListRepeatPat(paramPattern);
  }
  
  public boolean match(Object paramObject, Object[] paramArrayOfObject, int paramInt)
  {
    int m = LList.listLength(paramObject, false);
    if (m < 0) {
      return false;
    }
    int k = this.element_pattern.varCount();
    int i = k;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      paramArrayOfObject[(paramInt + i)] = new Object[m];
    }
    Object[] arrayOfObject = new Object[k];
    i = 0;
    while (i < m)
    {
      paramObject = (Pair)paramObject;
      if (!this.element_pattern.match(((Pair)paramObject).getCar(), arrayOfObject, 0)) {
        return false;
      }
      int j = 0;
      while (j < k)
      {
        ((Object[])paramArrayOfObject[(paramInt + j)])[i] = arrayOfObject[j];
        j += 1;
      }
      paramObject = ((Pair)paramObject).getCdr();
      i += 1;
    }
    return true;
  }
  
  public void print(Consumer paramConsumer)
  {
    paramConsumer.write("#<list-repeat-pattern ");
    this.element_pattern.print(paramConsumer);
    paramConsumer.write(62);
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.element_pattern = ((Pattern)paramObjectInput.readObject());
  }
  
  public int varCount()
  {
    return this.element_pattern.varCount();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.element_pattern);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\ListRepeatPat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */