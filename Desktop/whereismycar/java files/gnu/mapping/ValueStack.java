package gnu.mapping;

import gnu.lists.Sequence;

public class ValueStack
  extends Values
  implements Sequence
{
  public void clear()
  {
    this.oindex = 0;
    super.clear();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\ValueStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */